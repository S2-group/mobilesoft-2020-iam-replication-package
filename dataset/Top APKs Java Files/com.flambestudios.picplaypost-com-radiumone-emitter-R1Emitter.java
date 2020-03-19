package com.radiumone.emitter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.protobuf.ByteString;
import com.radiumone.emitter.dbemitter.EmitterDBHelper;
import com.radiumone.emitter.dbemitter.R1EmitterDbObject;
import com.radiumone.emitter.dbmobileconnect.R1PushDBHelper;
import com.radiumone.emitter.dbmobileconnect.R1PushDBParameter;
import com.radiumone.emitter.location.LocationHelper;
import com.radiumone.emitter.push.R1PushConfig;
import com.radiumone.emitter.utils.Utils;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartAdd;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartAdd.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartCreate;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartCreate.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartDelete;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartDelete.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartRemove;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.CartRemove.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.ConnectionType;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.DeviceIdInfo;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.DeviceIdInfo.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.DeviceInfo;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.DeviceInfo.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.DeviceType;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Event;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Event.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.EventInfo;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.EventInfo.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.FacebookConnect;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.FacebookConnect.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.InstalledAppInfo;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.InstalledAppInfo.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.KeyValue;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.KeyValue.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.LineItem;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.LineItem.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Login;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Login.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.MessageSource;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Permission;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Permission.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Registration;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Registration.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.ScreenView;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.ScreenView.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.SessionEnd;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.SessionEnd.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.TrackerMessage;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.TrackerMessage.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Transaction;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.Transaction.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.TransactionItem;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.TransactionItem.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.TwitterConnect;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.TwitterConnect.Builder;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.UserData;
import com.radiumone.engine.i10n.tracker.TrackerMessageProto.UserData.Builder;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class R1Emitter
{
  private static String a = "http";
  private static String b = "rp.gwallet.com";
  private static String c = "80";
  private static String d = "https";
  private static String e = "api.audienceactivationplatform.com";
  private static String f = "v1/events/";
  private static R1Emitter g = new R1Emitter();
  private static final String[] l = { "user_id", "user_id_sha1", "user_name_sha1", "email_md5", "email_sha1", "phone_md5", "phone_sha1", "firstName_lastName_city_state_md5", "firstName_lastName_city_state_sh1", "firstName_lastName_streetAddress_city_state_md5", "firstName_lastName_streetAddress_city_state_sh1", "firstName_lastName_zip_md5", "firstName_lastName_zip_sh1" };
  private static Context x;
  private final String h = "android.permission.ACCESS_COARSE_LOCATION";
  private final String i = "android.permission.ACCESS_FINE_LOCATION";
  private final String j = "android.permission.READ_PHONE_STATE";
  private final String k = "android.permission.ACCESS_NETWORK_STATE";
  private String m;
  private String n;
  private String o;
  private String p;
  private int q = 30;
  private Location r;
  private AsyncTask<R1EmitterDbObject[], Void, Boolean> s;
  private boolean t;
  private final ScheduledExecutorService u = Executors.newSingleThreadScheduledExecutor();
  private long v = -1L;
  private String w;
  private transient String y;
  
  private R1Emitter() {}
  
  public static R1Emitter a()
  {
    return g;
  }
  
  private TrackerMessageProto.TrackerMessage.Builder a(R1EmitterDbObject[] paramArrayOfR1EmitterDbObject)
  {
    TrackerMessageProto.TrackerMessage.Builder localBuilder = TrackerMessageProto.TrackerMessage.ah();
    localBuilder.a(TrackerMessageProto.MessageSource.a);
    localBuilder.i("2");
    localBuilder.r("2.0.0");
    if (this.w != null) {
      localBuilder.c(this.w);
    }
    a(localBuilder);
    int i1;
    Object localObject2;
    Object localObject1;
    HashMap localHashMap;
    TrackerMessageProto.EventInfo.Builder localBuilder1;
    Object localObject3;
    label341:
    label435:
    long l1;
    if (paramArrayOfR1EmitterDbObject != null)
    {
      int i3 = paramArrayOfR1EmitterDbObject.length;
      i1 = 0;
      if (i1 < i3)
      {
        localObject2 = paramArrayOfR1EmitterDbObject[i1];
        if (localObject2 != null)
        {
          localObject1 = ((R1EmitterDbObject)localObject2).b;
          if (((R1EmitterDbObject)localObject2).e != null)
          {
            localHashMap = new HashMap(((R1EmitterDbObject)localObject2).e);
            if ((localObject1 == null) || ("".equals(((String)localObject1).trim()))) {
              break label2188;
            }
            localBuilder1 = TrackerMessageProto.EventInfo.ab();
            localBuilder1.a(((R1EmitterDbObject)localObject2).b);
            if ((((R1EmitterDbObject)localObject2).f > 0L) && (((R1EmitterDbObject)localObject2).g > 0L))
            {
              localBuilder1.b(((R1EmitterDbObject)localObject2).f);
              localBuilder1.c(((R1EmitterDbObject)localObject2).g);
            }
            localBuilder1.a(((R1EmitterDbObject)localObject2).c);
            localBuilder1.a(ByteString.a(((R1EmitterDbObject)localObject2).d));
            localObject2 = (String)localHashMap.remove("Session_ID");
            if (localObject2 != null) {
              localBuilder1.b((String)localObject2);
            }
            if (!((String)localObject1).equals("Cart Add")) {
              break label435;
            }
            localObject1 = TrackerMessageProto.CartAdd.n();
            localObject2 = TrackerMessageProto.LineItem.C();
            localObject3 = (HashMap)localHashMap.get("LineItems");
            if (localObject3 != null)
            {
              a((TrackerMessageProto.LineItem.Builder)localObject2, (HashMap)localObject3);
              localHashMap.remove("LineItems");
              ((TrackerMessageProto.CartAdd.Builder)localObject1).a(((TrackerMessageProto.LineItem.Builder)localObject2).j());
            }
            localObject2 = (String)localHashMap.get("CartID");
            if (localObject2 != null)
            {
              ((TrackerMessageProto.CartAdd.Builder)localObject1).a((String)localObject2);
              localHashMap.remove("CartID");
            }
            localBuilder1.a((TrackerMessageProto.CartAdd.Builder)localObject1);
          }
          for (;;)
          {
            if (localHashMap != null)
            {
              localObject1 = localHashMap.keySet().iterator();
              for (;;)
              {
                if (((Iterator)localObject1).hasNext())
                {
                  localObject2 = ((Iterator)localObject1).next();
                  if ((localObject2 instanceof String))
                  {
                    localObject3 = localHashMap.get(localObject2);
                    localObject2 = a((String)localObject2, localObject3);
                    if (localObject2 != null)
                    {
                      localBuilder1.a((TrackerMessageProto.KeyValue)localObject2);
                      continue;
                      localHashMap = new HashMap();
                      break;
                      if (((String)localObject1).equals("Cart Create"))
                      {
                        localObject1 = TrackerMessageProto.CartCreate.l();
                        localObject2 = (String)localHashMap.get("CartID");
                        if (localObject2 != null)
                        {
                          ((TrackerMessageProto.CartCreate.Builder)localObject1).a((String)localObject2);
                          localHashMap.remove("CartID");
                        }
                        localBuilder1.a((TrackerMessageProto.CartCreate.Builder)localObject1);
                        break label341;
                      }
                      if (((String)localObject1).equals("Cart Delete"))
                      {
                        localObject1 = TrackerMessageProto.CartDelete.l();
                        localObject2 = (String)localHashMap.get("CartID");
                        if (localObject2 != null)
                        {
                          ((TrackerMessageProto.CartDelete.Builder)localObject1).a((String)localObject2);
                          localHashMap.remove("CartID");
                        }
                        localBuilder1.a((TrackerMessageProto.CartDelete.Builder)localObject1);
                        break label341;
                      }
                      if (((String)localObject1).equals("Cart Remove"))
                      {
                        localObject1 = TrackerMessageProto.CartRemove.n();
                        localObject2 = (String)localHashMap.get("CartID");
                        if (localObject2 != null)
                        {
                          ((TrackerMessageProto.CartRemove.Builder)localObject1).a((String)localObject2);
                          localHashMap.remove("CartID");
                        }
                        localObject2 = TrackerMessageProto.LineItem.C();
                        localObject3 = (HashMap)localHashMap.get("LineItems");
                        if (localObject3 != null)
                        {
                          a((TrackerMessageProto.LineItem.Builder)localObject2, (HashMap)localObject3);
                          localHashMap.remove("LineItems");
                          ((TrackerMessageProto.CartRemove.Builder)localObject1).a((TrackerMessageProto.LineItem.Builder)localObject2);
                        }
                        localBuilder1.a((TrackerMessageProto.CartRemove.Builder)localObject1);
                        break label341;
                      }
                      if (((String)localObject1).equals("Event"))
                      {
                        localObject1 = TrackerMessageProto.Event.p();
                        localObject2 = (String)localHashMap.get("Action");
                        if (localObject2 != null)
                        {
                          ((TrackerMessageProto.Event.Builder)localObject1).a((String)localObject2);
                          localHashMap.remove("Action");
                        }
                        localObject2 = (String)localHashMap.get("Label");
                        if (localObject2 != null)
                        {
                          ((TrackerMessageProto.Event.Builder)localObject1).b((String)localObject2);
                          localHashMap.remove("Label");
                        }
                        if (localHashMap.containsKey("Value"))
                        {
                          localObject2 = localHashMap.get("Value");
                          if (localObject2 != null)
                          {
                            if (!(localObject2 instanceof Long)) {
                              break label825;
                            }
                            l1 = ((Long)localObject2).longValue();
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      ((TrackerMessageProto.Event.Builder)localObject1).a(l1);
      localHashMap.remove("Value");
      localBuilder1.a((TrackerMessageProto.Event.Builder)localObject1);
      break label341;
      label825:
      if ((localObject2 instanceof Integer))
      {
        l1 = ((Integer)localObject2).longValue();
        continue;
        if (((String)localObject1).equals("Facebook Connect"))
        {
          localObject1 = TrackerMessageProto.FacebookConnect.l();
          a(localHashMap, (TrackerMessageProto.FacebookConnect.Builder)localObject1);
          localBuilder1.a((TrackerMessageProto.FacebookConnect.Builder)localObject1);
          break label341;
        }
        if ((((String)localObject1).equals("First Launch")) || (((String)localObject1).equals("First Launch After Update"))) {
          break label341;
        }
        if (((String)localObject1).equals("User Info"))
        {
          localObject1 = l;
          int i4 = localObject1.length;
          int i2 = 0;
          while (i2 < i4)
          {
            localHashMap.remove(localObject1[i2]);
            i2 += 1;
          }
          break label341;
        }
        if (((String)localObject1).equals("Login"))
        {
          localObject1 = TrackerMessageProto.Login.l();
          localObject2 = TrackerMessageProto.UserData.p();
          a((TrackerMessageProto.UserData.Builder)localObject2, localHashMap);
          ((TrackerMessageProto.Login.Builder)localObject1).a((TrackerMessageProto.UserData.Builder)localObject2);
          localBuilder1.a((TrackerMessageProto.Login.Builder)localObject1);
          break label341;
        }
        if (((String)localObject1).equals("Registration"))
        {
          localObject1 = TrackerMessageProto.Registration.C();
          localObject2 = TrackerMessageProto.UserData.p();
          a((TrackerMessageProto.UserData.Builder)localObject2, localHashMap);
          localObject3 = (String)localHashMap.get("RegCity");
          if (localObject3 != null)
          {
            ((TrackerMessageProto.Registration.Builder)localObject1).d((String)localObject3);
            localHashMap.remove("RegCity");
          }
          localObject3 = (String)localHashMap.get("RegState");
          if (localObject3 != null)
          {
            ((TrackerMessageProto.Registration.Builder)localObject1).e((String)localObject3);
            localHashMap.remove("RegState");
          }
          localObject3 = (String)localHashMap.get("RegCountry");
          if (localObject3 != null)
          {
            ((TrackerMessageProto.Registration.Builder)localObject1).g((String)localObject3);
            localHashMap.remove("RegCountry");
          }
          ((TrackerMessageProto.Registration.Builder)localObject1).a((TrackerMessageProto.UserData.Builder)localObject2);
          localBuilder1.a((TrackerMessageProto.Registration.Builder)localObject1);
          break label341;
        }
        if (((String)localObject1).equals("Resume")) {
          break label341;
        }
        if (((String)localObject1).equals("Screen View"))
        {
          localObject1 = TrackerMessageProto.ScreenView.w();
          localObject2 = (String)localHashMap.get("AppScreen");
          if (localObject2 != null)
          {
            ((TrackerMessageProto.ScreenView.Builder)localObject1).b((String)localObject2);
            localHashMap.remove("AppScreen");
          }
          localObject2 = (String)localHashMap.get("content_description");
          if (localObject2 != null)
          {
            ((TrackerMessageProto.ScreenView.Builder)localObject1).a((String)localObject2);
            localHashMap.remove("content_description");
          }
          localObject2 = (String)localHashMap.get("document_location_url");
          if (localObject2 != null)
          {
            ((TrackerMessageProto.ScreenView.Builder)localObject1).c((String)localObject2);
            localHashMap.remove("document_location_url");
          }
          localObject2 = (String)localHashMap.get("document_host_name");
          if (localObject2 != null)
          {
            ((TrackerMessageProto.ScreenView.Builder)localObject1).d((String)localObject2);
            localHashMap.remove("document_host_name");
          }
          localObject2 = (String)localHashMap.get("document_path");
          if (localObject2 != null)
          {
            ((TrackerMessageProto.ScreenView.Builder)localObject1).e((String)localObject2);
            localHashMap.remove("document_path");
          }
          localBuilder1.a((TrackerMessageProto.ScreenView.Builder)localObject1);
          break label341;
        }
        if (((String)localObject1).equals("Suspend")) {
          break label341;
        }
        if (((String)localObject1).equals("Session End"))
        {
          localObject1 = TrackerMessageProto.SessionEnd.l();
          if (!localHashMap.containsKey("session_length")) {
            break label341;
          }
          localObject2 = localHashMap.get("session_length");
          if (localObject2 == null) {
            break label2222;
          }
          if ((localObject2 instanceof Long))
          {
            l1 = ((Long)localObject2).longValue();
            label1434:
            l2 = l1;
            if (l1 > 0L) {
              ((TrackerMessageProto.SessionEnd.Builder)localObject1).a(l1);
            }
          }
        }
        label1705:
        label1801:
        label1925:
        label1946:
        label1967:
        label2188:
        label2204:
        label2210:
        label2222:
        for (long l2 = l1;; l2 = 0L)
        {
          if (l2 > 0L) {
            localBuilder1.a((TrackerMessageProto.SessionEnd.Builder)localObject1);
          }
          localHashMap.remove("session_length");
          break label341;
          if ((localObject2 instanceof Integer))
          {
            l1 = ((Integer)localObject2).longValue();
            break label1434;
            if (((String)localObject1).equals("Transaction"))
            {
              localObject1 = TrackerMessageProto.Transaction.E();
              localObject2 = (String)localHashMap.get("CartID");
              if (localObject2 != null)
              {
                ((TrackerMessageProto.Transaction.Builder)localObject1).d((String)localObject2);
                localHashMap.remove("CartID");
              }
              localObject2 = (String)localHashMap.get("storeName");
              if (localObject2 != null)
              {
                ((TrackerMessageProto.Transaction.Builder)localObject1).c((String)localObject2);
                localHashMap.remove("storeName");
              }
              localObject2 = (String)localHashMap.get("Currency");
              if (localObject2 != null)
              {
                ((TrackerMessageProto.Transaction.Builder)localObject1).f((String)localObject2);
                localHashMap.remove("Currency");
              }
              localObject2 = (String)localHashMap.get("OrderID");
              if (localObject2 != null)
              {
                ((TrackerMessageProto.Transaction.Builder)localObject1).e((String)localObject2);
                localHashMap.remove("OrderID");
              }
              if (localHashMap.containsKey("ShippingCost"))
              {
                localObject2 = localHashMap.get("ShippingCost");
                if (localObject2 != null)
                {
                  if (!(localObject2 instanceof Long)) {
                    break label1925;
                  }
                  l1 = ((Long)localObject2).longValue();
                }
              }
            }
            for (;;)
            {
              ((TrackerMessageProto.Transaction.Builder)localObject1).b(l1);
              localHashMap.remove("ShippingCost");
              localObject2 = (String)localHashMap.get("StoreID");
              if (localObject2 != null)
              {
                ((TrackerMessageProto.Transaction.Builder)localObject1).b((String)localObject2);
                localHashMap.remove("StoreID");
              }
              if (localHashMap.containsKey("TotalSale"))
              {
                localObject2 = localHashMap.get("TotalSale");
                if (localObject2 != null)
                {
                  if (!(localObject2 instanceof Long)) {
                    break label1946;
                  }
                  l1 = ((Long)localObject2).longValue();
                }
              }
              for (;;)
              {
                ((TrackerMessageProto.Transaction.Builder)localObject1).a(l1);
                localHashMap.remove("TotalSale");
                localObject2 = (String)localHashMap.get("TransactionID");
                if (localObject2 != null)
                {
                  ((TrackerMessageProto.Transaction.Builder)localObject1).a((String)localObject2);
                  localHashMap.remove("TransactionID");
                }
                if (localHashMap.containsKey("TransactionTax"))
                {
                  localObject2 = localHashMap.get("TransactionTax");
                  if (localObject2 != null)
                  {
                    if (!(localObject2 instanceof Long)) {
                      break label1967;
                    }
                    l1 = ((Long)localObject2).longValue();
                  }
                }
                for (;;)
                {
                  ((TrackerMessageProto.Transaction.Builder)localObject1).c(l1);
                  localHashMap.remove("TransactionTax");
                  localBuilder1.a((TrackerMessageProto.Transaction.Builder)localObject1);
                  break label341;
                  if (!(localObject2 instanceof Integer)) {
                    break label2210;
                  }
                  l1 = ((Integer)localObject2).longValue();
                  break label1705;
                  if (!(localObject2 instanceof Integer)) {
                    break label2204;
                  }
                  l1 = ((Integer)localObject2).longValue();
                  break label1801;
                  if ((localObject2 instanceof Integer))
                  {
                    l1 = ((Integer)localObject2).longValue();
                    continue;
                    if (((String)localObject1).equals("Transaction Item"))
                    {
                      localObject1 = TrackerMessageProto.TransactionItem.n();
                      localObject2 = (String)localHashMap.get("TransactionItemID");
                      if (localObject2 != null)
                      {
                        ((TrackerMessageProto.TransactionItem.Builder)localObject1).a((String)localObject2);
                        localHashMap.remove("TransactionItemID");
                      }
                      localObject2 = TrackerMessageProto.LineItem.C();
                      localObject3 = (HashMap)localHashMap.get("LineItems");
                      if (localObject3 != null)
                      {
                        a((TrackerMessageProto.LineItem.Builder)localObject2, (HashMap)localObject3);
                        localHashMap.remove("LineItems");
                        ((TrackerMessageProto.TransactionItem.Builder)localObject1).a((TrackerMessageProto.LineItem.Builder)localObject2);
                      }
                      localBuilder1.a((TrackerMessageProto.TransactionItem.Builder)localObject1);
                      break label341;
                    }
                    if (((String)localObject1).equals("Trial Upgrade")) {
                      break label341;
                    }
                    if (((String)localObject1).equals("Twitter Connect"))
                    {
                      localObject1 = TrackerMessageProto.TwitterConnect.l();
                      a(localHashMap, (TrackerMessageProto.TwitterConnect.Builder)localObject1);
                      localObject2 = TrackerMessageProto.UserData.p();
                      a((TrackerMessageProto.UserData.Builder)localObject2, localHashMap);
                      ((TrackerMessageProto.TwitterConnect.Builder)localObject1).a((TrackerMessageProto.UserData.Builder)localObject2);
                      localBuilder1.a((TrackerMessageProto.TwitterConnect.Builder)localObject1);
                      break label341;
                    }
                    if (!((String)localObject1).equals("Upgrade")) {
                      break label341;
                    }
                    break label341;
                    localBuilder.a(localBuilder1.j());
                    i1 += 1;
                    break;
                    return localBuilder;
                  }
                  l1 = 0L;
                }
                l1 = 0L;
              }
              l1 = 0L;
            }
          }
          l1 = 0L;
          break label1434;
        }
      }
      l1 = 0L;
    }
  }
  
  private ArrayList<ApplicationInfo> a(PackageManager paramPackageManager)
  {
    if (paramPackageManager != null)
    {
      Object localObject = paramPackageManager.getInstalledApplications(0);
      paramPackageManager = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if (localApplicationInfo != null) {
          paramPackageManager.add(localApplicationInfo);
        }
      }
      return paramPackageManager;
    }
    return null;
  }
  
  public static HttpClient a(HttpClient paramHttpClient)
  {
    try
    {
      Object localObject = KeyStore.getInstance(KeyStore.getDefaultType());
      ((KeyStore)localObject).load(null, null);
      localObject = new R1Emitter.MySSLSocketFactory((KeyStore)localObject);
      ((SSLSocketFactory)localObject).setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      ClientConnectionManager localClientConnectionManager = paramHttpClient.getConnectionManager();
      localClientConnectionManager.getSchemeRegistry().register(new Scheme("https", (SocketFactory)localObject, 443));
      paramHttpClient = new DefaultHttpClient(localClientConnectionManager, paramHttpClient.getParams());
      return paramHttpClient;
    }
    catch (Exception paramHttpClient) {}
    return null;
  }
  
  private void a(TrackerMessageProto.LineItem.Builder paramBuilder, HashMap paramHashMap)
  {
    long l2 = 0L;
    Object localObject = (String)paramHashMap.get("ProductID");
    if (localObject != null)
    {
      paramBuilder.a((String)localObject);
      paramHashMap.remove("ProductID");
    }
    String str = (String)paramHashMap.get("ProductName");
    if (localObject != null)
    {
      paramBuilder.b(str);
      paramHashMap.remove("ProductName");
    }
    int i1;
    if (paramHashMap.containsKey("Quantity"))
    {
      localObject = paramHashMap.get("Quantity");
      if (localObject != null)
      {
        if (!(localObject instanceof Long)) {
          break label333;
        }
        i1 = ((Long)localObject).intValue();
      }
    }
    for (;;)
    {
      paramBuilder.a(i1);
      paramHashMap.remove("Quantity");
      localObject = (String)paramHashMap.get("UoM");
      if (localObject != null)
      {
        paramBuilder.c((String)localObject);
        paramHashMap.remove("UoM");
      }
      long l1;
      if (paramHashMap.containsKey("MSRPrice"))
      {
        localObject = paramHashMap.get("MSRPrice");
        if (localObject != null)
        {
          if (!(localObject instanceof Long)) {
            break label353;
          }
          l1 = ((Long)localObject).longValue();
        }
      }
      for (;;)
      {
        label196:
        paramBuilder.a(l1);
        paramHashMap.remove("MSRPrice");
        if (paramHashMap.containsKey("PricePaid"))
        {
          localObject = paramHashMap.get("PricePaid");
          if (localObject != null)
          {
            if (!(localObject instanceof Long)) {
              break label374;
            }
            l1 = ((Long)localObject).longValue();
          }
        }
        for (;;)
        {
          paramBuilder.b(l1);
          paramHashMap.remove("PricePaid");
          localObject = (String)paramHashMap.get("Currency");
          if (localObject != null)
          {
            paramBuilder.d((String)localObject);
            paramHashMap.remove("Currency");
          }
          localObject = (String)paramHashMap.get("ItemCategory");
          if (localObject != null)
          {
            paramBuilder.e((String)localObject);
            paramHashMap.remove("ItemCategory");
          }
          return;
          label333:
          if (!(localObject instanceof Integer)) {
            break label405;
          }
          i1 = ((Integer)localObject).intValue();
          break;
          label353:
          if (!(localObject instanceof Integer)) {
            break label399;
          }
          l1 = ((Integer)localObject).longValue();
          break label196;
          label374:
          l1 = l2;
          if ((localObject instanceof Integer)) {
            l1 = ((Integer)localObject).longValue();
          }
        }
        label399:
        l1 = 0L;
      }
      label405:
      i1 = 0;
    }
  }
  
  private void a(TrackerMessageProto.TrackerMessage.Builder paramBuilder)
  {
    Object localObject1;
    int i1;
    label130:
    label172:
    Object localObject2;
    label242:
    Object localObject3;
    Object localObject4;
    if (this.o != null)
    {
      if (this.n != null)
      {
        localObject1 = String.format("%s (%s)", new Object[] { this.n, this.o });
        paramBuilder.a((String)localObject1);
      }
    }
    else
    {
      if (this.p != null) {
        paramBuilder.b(this.p);
      }
      if (x == null) {
        break label873;
      }
      if (x.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
        break label744;
      }
      localObject1 = (ConnectivityManager)x.getSystemService("connectivity");
      if (localObject1 != null)
      {
        localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
        if (localObject1 != null)
        {
          i1 = ((NetworkInfo)localObject1).getType();
          if (i1 != 1) {
            break label718;
          }
          paramBuilder.a(TrackerMessageProto.ConnectionType.c);
        }
      }
      if (x != null)
      {
        localObject1 = x.getResources();
        if ((localObject1 == null) || ((((Resources)localObject1).getConfiguration().screenLayout & 0xF) != 3)) {
          break label755;
        }
        paramBuilder.a(TrackerMessageProto.DeviceType.c);
      }
      localObject1 = TrackerMessageProto.DeviceInfo.G();
      ((TrackerMessageProto.DeviceInfo.Builder)localObject1).a("Android");
      ((TrackerMessageProto.DeviceInfo.Builder)localObject1).b(Build.VERSION.RELEASE);
      ((TrackerMessageProto.DeviceInfo.Builder)localObject1).e(Build.MODEL);
      ((TrackerMessageProto.DeviceInfo.Builder)localObject1).d(Build.MANUFACTURER);
      localObject2 = e();
      if (localObject2 != null)
      {
        if (localObject2.length != 4) {
          break label766;
        }
        ((TrackerMessageProto.DeviceInfo.Builder)localObject1).a(ByteString.a((byte[])localObject2));
      }
      localObject2 = Locale.getDefault();
      if (localObject2 != null)
      {
        ((TrackerMessageProto.DeviceInfo.Builder)localObject1).c(((Locale)localObject2).getLanguage());
        ((TrackerMessageProto.DeviceInfo.Builder)localObject1).f(((Locale)localObject2).getCountry());
        paramBuilder.p(((Locale)localObject2).toString());
      }
      localObject2 = HttpProtocolParams.getUserAgent(new DefaultHttpClient().getParams());
      if (localObject2 != null) {
        paramBuilder.o((String)localObject2);
      }
      localObject2 = Calendar.getInstance();
      if (localObject2 != null)
      {
        i1 = ((Calendar)localObject2).get(15);
        ((TrackerMessageProto.DeviceInfo.Builder)localObject1).a((((Calendar)localObject2).get(16) + i1) / 1000);
      }
      localObject2 = TrackerMessageProto.DeviceIdInfo.S();
      localObject3 = a((TrackerMessageProto.DeviceIdInfo.Builder)localObject2);
      if (localObject3 != null)
      {
        localObject4 = (String)((HashMap)localObject3).get("carrier_country");
        if (localObject4 != null) {
          paramBuilder.f((String)localObject4);
        }
        localObject3 = (String)((HashMap)localObject3).get("carrier_name");
        if (localObject3 != null) {
          paramBuilder.e((String)localObject3);
        }
      }
      if (x == null) {
        break label852;
      }
      localObject3 = x.getResources().getDisplayMetrics();
      if (localObject3 != null)
      {
        paramBuilder.g(((DisplayMetrics)localObject3).widthPixels + "x" + ((DisplayMetrics)localObject3).heightPixels);
        paramBuilder.h(((DisplayMetrics)localObject3).widthPixels + "x" + ((DisplayMetrics)localObject3).heightPixels);
      }
      localObject4 = x.getSharedPreferences("r1_emitter_pref", 0);
      if (localObject4 == null) {
        break label874;
      }
    }
    label718:
    label744:
    label755:
    label766:
    label852:
    label873:
    label874:
    for (boolean bool = ((SharedPreferences)localObject4).getBoolean("send_apps", true);; bool = true)
    {
      localObject3 = x.getPackageManager();
      if (bool)
      {
        if (localObject4 != null)
        {
          localObject4 = ((SharedPreferences)localObject4).edit();
          if (localObject4 != null)
          {
            ((SharedPreferences.Editor)localObject4).putBoolean("send_apps", false);
            ((SharedPreferences.Editor)localObject4).commit();
          }
        }
        localObject4 = a((PackageManager)localObject3);
        if (localObject4 != null)
        {
          localObject4 = ((ArrayList)localObject4).iterator();
          for (;;)
          {
            if (((Iterator)localObject4).hasNext())
            {
              Object localObject5 = (ApplicationInfo)((Iterator)localObject4).next();
              if (localObject5 != null)
              {
                TrackerMessageProto.InstalledAppInfo.Builder localBuilder = TrackerMessageProto.InstalledAppInfo.n();
                if (((ApplicationInfo)localObject5).packageName != null) {
                  localBuilder.b(((ApplicationInfo)localObject5).packageName);
                }
                localObject5 = ((ApplicationInfo)localObject5).loadLabel((PackageManager)localObject3);
                if (localObject5 != null)
                {
                  localObject5 = ((CharSequence)localObject5).toString();
                  if (localObject5 != null) {
                    localBuilder.a((String)localObject5);
                  }
                }
                paramBuilder.a(localBuilder);
                continue;
                localObject1 = this.o;
                break;
                if (i1 == 0)
                {
                  paramBuilder.a(TrackerMessageProto.ConnectionType.b);
                  break label130;
                }
                paramBuilder.a(TrackerMessageProto.ConnectionType.a);
                break label130;
                paramBuilder.a(TrackerMessageProto.ConnectionType.a);
                break label130;
                paramBuilder.a(TrackerMessageProto.DeviceType.b);
                break label172;
                if (localObject2.length != 16) {
                  break label242;
                }
                ((TrackerMessageProto.DeviceInfo.Builder)localObject1).b(ByteString.a((byte[])localObject2));
                break label242;
              }
            }
          }
        }
      }
      paramBuilder.l(x.getPackageName());
      if ((localObject3 != null) && (x.getApplicationInfo() != null))
      {
        localObject3 = x.getApplicationInfo().loadLabel((PackageManager)localObject3);
        if (localObject3 != null)
        {
          localObject3 = ((CharSequence)localObject3).toString();
          if (localObject3 != null) {
            paramBuilder.j((String)localObject3);
          }
        }
      }
      ((TrackerMessageProto.DeviceInfo.Builder)localObject1).a(((TrackerMessageProto.DeviceIdInfo.Builder)localObject2).j());
      paramBuilder.a(((TrackerMessageProto.DeviceInfo.Builder)localObject1).j());
      return;
    }
  }
  
  private void a(TrackerMessageProto.UserData.Builder paramBuilder, HashMap<String, Object> paramHashMap)
  {
    String str = (String)paramHashMap.get("UserID");
    if (str != null)
    {
      paramBuilder.a(str);
      paramHashMap.remove("UserID");
      paramBuilder.b(Utils.b(str));
      paramHashMap.remove("UserId_sha1");
    }
    str = (String)paramHashMap.get("UserName_sha1");
    if (str != null)
    {
      paramBuilder.c(str);
      paramHashMap.remove("UserName_sha1");
    }
  }
  
  private void a(String paramString, Map<String, Object> paramMap, long paramLong)
  {
    if ((this.t) || (TextUtils.isEmpty(paramString)) || (TextUtils.isEmpty(a(x)))) {
      return;
    }
    for (;;)
    {
      R1EmitterDbObject localR1EmitterDbObject;
      try
      {
        localR1EmitterDbObject = new R1EmitterDbObject();
        localR1EmitterDbObject.b = paramString;
        paramString = UUID.randomUUID();
        ByteBuffer localByteBuffer = ByteBuffer.wrap(new byte[16]);
        localByteBuffer.putLong(paramString.getMostSignificantBits());
        localByteBuffer.putLong(paramString.getLeastSignificantBits());
        localR1EmitterDbObject.d = localByteBuffer.array();
        if (paramMap != null)
        {
          localR1EmitterDbObject.e = ((HashMap)paramMap);
          if (!TextUtils.isEmpty(this.y)) {
            localR1EmitterDbObject.e.put("Session_ID", this.y);
          }
          localR1EmitterDbObject.c = paramLong;
          localR1EmitterDbObject.i = false;
          localR1EmitterDbObject.h = 0;
          if (x != null) {
            this.r = LocationHelper.a(x).e();
          }
          if (this.r != null)
          {
            localR1EmitterDbObject.f = ((this.r.getLatitude() * 1.0E7D));
            localR1EmitterDbObject.g = ((this.r.getLongitude() * 1.0E7D));
          }
          if (x == null) {
            break;
          }
          localR1EmitterDbObject.a(EmitterDBHelper.a(x));
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      localR1EmitterDbObject.e = new HashMap();
    }
  }
  
  private void a(HashMap<String, Object> paramHashMap, TrackerMessageProto.FacebookConnect.Builder paramBuilder)
  {
    Object[] arrayOfObject = (Object[])paramHashMap.get("SocialPermissionFacebook");
    if (arrayOfObject != null)
    {
      int i2 = arrayOfObject.length;
      int i1 = 0;
      while (i1 < i2)
      {
        Object localObject1 = arrayOfObject[i1];
        if ((localObject1 != null) && ((localObject1 instanceof HashMap)))
        {
          Object localObject2 = (HashMap)localObject1;
          localObject1 = TrackerMessageProto.Permission.n();
          String str = (String)((HashMap)localObject2).get("Name");
          if (str != null) {
            ((TrackerMessageProto.Permission.Builder)localObject1).a(str);
          }
          localObject2 = (Boolean)((HashMap)localObject2).get("Granted");
          if (localObject2 != null) {
            ((TrackerMessageProto.Permission.Builder)localObject1).a(((Boolean)localObject2).booleanValue());
          }
          paramBuilder.a((TrackerMessageProto.Permission.Builder)localObject1);
        }
        i1 += 1;
      }
    }
    paramHashMap.remove("SocialPermissionFacebook");
  }
  
  private void a(HashMap<String, Object> paramHashMap, TrackerMessageProto.TwitterConnect.Builder paramBuilder)
  {
    Object[] arrayOfObject = (Object[])paramHashMap.get("SocialPermissionsTwitter");
    if (arrayOfObject != null)
    {
      int i2 = arrayOfObject.length;
      int i1 = 0;
      while (i1 < i2)
      {
        Object localObject1 = arrayOfObject[i1];
        if ((localObject1 != null) && ((localObject1 instanceof HashMap)))
        {
          Object localObject2 = (HashMap)localObject1;
          localObject1 = TrackerMessageProto.Permission.n();
          String str = (String)((HashMap)localObject2).get("Name");
          if (str != null) {
            ((TrackerMessageProto.Permission.Builder)localObject1).a(str);
          }
          localObject2 = (Boolean)((HashMap)localObject2).get("Granted");
          if (localObject2 != null) {
            ((TrackerMessageProto.Permission.Builder)localObject1).a(((Boolean)localObject2).booleanValue());
          }
          paramBuilder.a((TrackerMessageProto.Permission.Builder)localObject1);
        }
        i1 += 1;
      }
    }
    paramHashMap.remove("SocialPermissionsTwitter");
  }
  
  private boolean a(TrackerMessageProto.KeyValue.Builder paramBuilder, Object paramObject)
  {
    if ((paramObject instanceof String))
    {
      paramBuilder.b((String)paramObject);
      return true;
    }
    Object localObject1;
    if ((paramObject instanceof HashMap))
    {
      try
      {
        paramObject = (HashMap)paramObject;
        localObject1 = paramObject.keySet();
        if ((localObject1 != null) && (((Set)localObject1).size() > 0)) {
          localObject1 = ((Set)localObject1).iterator();
        }
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = ((Iterator)localObject1).next();
          if ((localObject2 instanceof String))
          {
            Object localObject3 = paramObject.get(localObject2);
            localObject2 = a((String)localObject2, localObject3);
            if (localObject2 != null)
            {
              paramBuilder.b((TrackerMessageProto.KeyValue)localObject2);
              continue;
              return false;
            }
          }
        }
      }
      catch (ClassCastException paramBuilder)
      {
        return false;
      }
      return true;
    }
    if ((paramObject instanceof Object[]))
    {
      paramObject = (Object[])paramObject;
      int i4 = paramObject.length;
      int i1 = 0;
      int i3;
      for (int i2 = 0; i1 < i4; i2 = i3)
      {
        localObject1 = paramObject[i1];
        localObject1 = a(String.format("%d", new Object[] { Integer.valueOf(i2) }), localObject1);
        i3 = i2;
        if (localObject1 != null)
        {
          paramBuilder.b((TrackerMessageProto.KeyValue)localObject1);
          i3 = i2 + 1;
        }
        i1 += 1;
      }
      return true;
    }
    if ((paramObject instanceof Long))
    {
      paramBuilder.a(((Long)paramObject).longValue());
      return true;
    }
    if ((paramObject instanceof Integer))
    {
      paramBuilder.a(((Integer)paramObject).longValue());
      return true;
    }
    if ((paramObject instanceof Double))
    {
      paramBuilder.a(((Double)paramObject).doubleValue());
      return true;
    }
    if ((paramObject instanceof Float))
    {
      paramBuilder.a(((Float)paramObject).doubleValue());
      return true;
    }
    if ((paramObject instanceof Boolean))
    {
      paramBuilder.a(((Boolean)paramObject).booleanValue());
      return true;
    }
    return false;
  }
  
  private static URI b(HttpGet paramHttpGet, Uri.Builder paramBuilder, R1EmitterDbObject paramR1EmitterDbObject)
  {
    int i1 = 0;
    String str2;
    if (paramBuilder != null) {
      if (paramR1EmitterDbObject != null)
      {
        paramBuilder.appendQueryParameter("os", "Android");
        if (paramR1EmitterDbObject.b != null) {
          paramBuilder.appendQueryParameter("event", paramR1EmitterDbObject.b);
        }
        if (paramR1EmitterDbObject.c > 0L) {
          paramBuilder.appendQueryParameter("timestamp", String.valueOf(paramR1EmitterDbObject.c));
        }
        if (paramR1EmitterDbObject.d != null)
        {
          paramHttpGet = ByteBuffer.wrap(paramR1EmitterDbObject.d);
          paramBuilder.appendQueryParameter("transaction_id", new UUID(paramHttpGet.getLong(0), paramHttpGet.getLong(8)).toString());
        }
        str2 = Settings.Secure.getString(x.getContentResolver(), "android_id");
        paramBuilder.appendQueryParameter("aid", str2);
      }
    }
    for (;;)
    {
      try
      {
        paramHttpGet = AdvertisingIdClient.b(x);
        if (paramHttpGet == null) {
          continue;
        }
        str1 = paramHttpGet.a();
        paramHttpGet = str1;
      }
      catch (Throwable paramHttpGet)
      {
        String str1;
        paramHttpGet = null;
        continue;
        paramHttpGet = null;
        continue;
      }
      try
      {
        if (!TextUtils.isEmpty(str1))
        {
          paramBuilder.appendQueryParameter("adv_id", str1);
          paramHttpGet = str1;
        }
      }
      catch (Throwable paramHttpGet)
      {
        paramHttpGet = str1;
      }
    }
    if (TextUtils.isEmpty(paramHttpGet)) {
      paramBuilder.appendQueryParameter("dpid", str2);
    }
    for (;;)
    {
      paramBuilder.appendQueryParameter("r1_daid", R1PushConfig.a(x).e());
      if (paramR1EmitterDbObject.e == null) {
        break;
      }
      paramHttpGet = paramR1EmitterDbObject.e;
      if (paramHttpGet == null) {
        break;
      }
      paramR1EmitterDbObject = l;
      int i2 = paramR1EmitterDbObject.length;
      while (i1 < i2)
      {
        str1 = paramR1EmitterDbObject[i1];
        str2 = (String)paramHttpGet.get(str1);
        if (!TextUtils.isEmpty(str2)) {
          paramBuilder.appendQueryParameter(str1, str2);
        }
        i1 += 1;
      }
      paramBuilder.appendQueryParameter("dpid", paramHttpGet);
    }
    paramHttpGet = paramBuilder.build();
    if (paramHttpGet != null)
    {
      paramBuilder = R1PushConfig.a(x).b();
      if (!TextUtils.isEmpty(paramBuilder)) {}
    }
    else
    {
      return null;
    }
    return URIUtils.createURI(d, e, 443, f + paramBuilder + "/", paramHttpGet.getEncodedQuery(), null);
  }
  
  private static DefaultHttpClient j()
  {
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 30000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    HttpProtocolParams.setVersion((HttpParams)localObject, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset((HttpParams)localObject, "UTF-8");
    HttpProtocolParams.setUseExpectContinue((HttpParams)localObject, false);
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
    localObject = new DefaultHttpClient(new ThreadSafeClientConnManager((HttpParams)localObject, localSchemeRegistry), (HttpParams)localObject);
    ((DefaultHttpClient)localObject).addRequestInterceptor(new R1Emitter.2());
    ((DefaultHttpClient)localObject).addResponseInterceptor(new R1Emitter.3());
    return localObject;
  }
  
  private boolean k()
  {
    boolean bool3 = true;
    bool2 = false;
    bool1 = bool2;
    if (x != null) {}
    for (bool1 = bool3;; bool1 = false)
    {
      try
      {
        if (x.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0)
        {
          NetworkInfo localNetworkInfo = ((ConnectivityManager)x.getSystemService("connectivity")).getActiveNetworkInfo();
          if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable())) {
            continue;
          }
          bool1 = localNetworkInfo.isConnectedOrConnecting();
          if (!bool1) {
            continue;
          }
          bool1 = true;
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          localThrowable.printStackTrace();
          bool1 = bool2;
        }
      }
      return bool1;
    }
  }
  
  public TrackerMessageProto.KeyValue a(String paramString, Object paramObject)
  {
    TrackerMessageProto.KeyValue.Builder localBuilder = TrackerMessageProto.KeyValue.x();
    if (paramString != null) {
      localBuilder.a(paramString);
    }
    if (a(localBuilder, paramObject)) {
      return localBuilder.j();
    }
    return null;
  }
  
  public String a(Context paramContext)
  {
    if (paramContext == null) {
      return this.m;
    }
    if (x == null) {
      x = paramContext.getApplicationContext();
    }
    paramContext = Utils.a(R1PushDBHelper.a(x), null, "app_id", null);
    if ((paramContext != null) && (paramContext.d() != null)) {
      this.m = paramContext.d();
    }
    return this.m;
  }
  
  public HashMap<String, String> a(TrackerMessageProto.DeviceIdInfo.Builder paramBuilder)
  {
    HashMap localHashMap = d();
    Object localObject3;
    String str;
    if (x != null)
    {
      localObject2 = x.getSharedPreferences("r1_emitter_pref", 0);
      if (!((SharedPreferences)localObject2).getBoolean("sent_mapping", false))
      {
        localObject1 = Settings.Secure.getString(x.getContentResolver(), "android_id");
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject2 = ((SharedPreferences)localObject2).edit();
          ((SharedPreferences.Editor)localObject2).putBoolean("sent_mapping", true);
          ((SharedPreferences.Editor)localObject2).commit();
          localObject2 = new HashMap();
          ((HashMap)localObject2).put("R1_DAID", R1PushConfig.a(x).e());
          ((HashMap)localObject2).put("AndroidID", localObject1);
          a("Device Map", (Map)localObject2);
        }
      }
      localObject2 = null;
      localObject3 = null;
      str = Settings.Secure.getString(x.getContentResolver(), "android_id");
      paramBuilder.j(str);
      localObject1 = localObject2;
    }
    try
    {
      AdvertisingIdClient.Info localInfo = AdvertisingIdClient.b(x);
      localObject1 = localObject3;
      if (localInfo != null)
      {
        localObject1 = localObject2;
        localObject2 = localInfo.a();
        localObject1 = localObject2;
        paramBuilder.k((String)localObject2);
        localObject1 = localObject2;
        paramBuilder.a(localInfo.b());
        localObject1 = localObject2;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    Object localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = str;
    }
    if (localObject2 != null)
    {
      paramBuilder.a((String)localObject2);
      localObject1 = Utils.b((String)localObject2);
      if (localObject1 != null) {
        paramBuilder.b((String)localObject1);
      }
      localObject1 = Utils.a((String)localObject2);
      if (localObject1 != null) {
        paramBuilder.c((String)localObject1);
      }
    }
    Object localObject1 = R1PushConfig.a(x).e();
    if (localObject1 != null) {
      paramBuilder.n((String)localObject1);
    }
    return localHashMap;
  }
  
  public void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString != null)
    {
      this.m = paramString;
      if (!paramBoolean) {}
    }
    else
    {
      return;
    }
    Object localObject2 = Utils.a(R1PushDBHelper.a(paramContext), null, "app_id", null);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new R1PushDBParameter();
      ((R1PushDBParameter)localObject1).b("app_id");
    }
    localObject2 = ((R1PushDBParameter)localObject1).d();
    ((R1PushDBParameter)localObject1).a(paramString);
    if (!paramString.equals(localObject2)) {
      ((R1PushDBParameter)localObject1).a(true);
    }
    ((R1PushDBParameter)localObject1).a(R1PushDBHelper.a(paramContext));
  }
  
  public void a(String paramString, Map<String, Object> paramMap)
  {
    a(paramString, paramMap, System.currentTimeMillis());
  }
  
  public void a(Map<String, Object> paramMap)
  {
    a("Trial Upgrade", paramMap);
  }
  
  public String b()
  {
    return this.w;
  }
  
  public void c()
  {
    if ((x != null) && (k())) {
      try
      {
        R1EmitterDbObject[] arrayOfR1EmitterDbObject = R1EmitterDbObject.a(EmitterDBHelper.a(x), null, null);
        if ((arrayOfR1EmitterDbObject != null) && (arrayOfR1EmitterDbObject.length > 0) && ((this.s == null) || (this.s.isCancelled()) || (this.s.getStatus() == AsyncTask.Status.FINISHED)))
        {
          this.s = new R1Emitter.SendEventAsynkTask(x);
          if (Build.VERSION.SDK_INT > 10)
          {
            this.s.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new R1EmitterDbObject[][] { arrayOfR1EmitterDbObject });
            return;
          }
          this.s.execute(new R1EmitterDbObject[][] { arrayOfR1EmitterDbObject });
          return;
        }
      }
      catch (Exception localException) {}
    }
  }
  
  public HashMap<String, String> d()
  {
    if (x == null) {
      return null;
    }
    HashMap localHashMap = new HashMap();
    if (x.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0)
    {
      Object localObject = (TelephonyManager)x.getSystemService("phone");
      if (localObject != null)
      {
        String str = ((TelephonyManager)localObject).getNetworkOperatorName();
        if (!TextUtils.isEmpty(str)) {
          localHashMap.put("carrier_name", str);
        }
        localObject = ((TelephonyManager)localObject).getNetworkCountryIso();
        if (!TextUtils.isEmpty(str)) {
          localHashMap.put("carrier_country", localObject);
        }
      }
    }
    return localHashMap;
  }
  
  public byte[] e()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while ((localInetAddress == null) || (localInetAddress.isLoopbackAddress()) || (localInetAddress.isAnyLocalAddress()) || (localInetAddress.isLinkLocalAddress()));
      Object localObject = localInetAddress.getAddress();
      return localObject;
    }
    catch (SocketException localSocketException) {}
    return null;
  }
}
