package com.igexin.push.core.a;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.util.Log;
import com.igexin.push.config.SDKUrlConfig;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.c.w;
import com.igexin.push.extension.stub.IPushExtension;
import java.io.File;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.UnresolvedAddressException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class e
  extends a
  implements com.igexin.push.d.k
{
  private static Map a;
  private static Map b;
  private static e c;
  
  private e()
  {
    a = new HashMap();
    a.put(Integer.valueOf(0), new h());
    a.put(Integer.valueOf(5), new i());
    a.put(Integer.valueOf(37), new k());
    a.put(Integer.valueOf(9), new o());
    a.put(Integer.valueOf(26), new g());
    a.put(Integer.valueOf(28), new d());
    b = new HashMap();
    b.put("goto", new com.igexin.push.core.a.a.g());
    b.put("notification", new com.igexin.push.core.a.a.h());
    b.put("startapp", new com.igexin.push.core.a.a.k());
    b.put("null", new com.igexin.push.core.a.a.f());
    b.put("wakeupsdk", new com.igexin.push.core.a.a.l());
    b.put("startweb", new com.igexin.push.core.a.a.j());
    b.put("checkapp", new com.igexin.push.core.a.a.b());
    b.put("cleanext", new com.igexin.push.core.a.a.c());
    b.put("enablelog", new com.igexin.push.core.a.a.e());
    b.put("disablelog", new com.igexin.push.core.a.a.d());
    b.put("reportext", new com.igexin.push.core.a.a.i());
  }
  
  private void C()
  {
    boolean bool2 = false;
    com.igexin.push.core.i.a().a(com.igexin.push.core.k.d);
    com.igexin.a.a.c.a.b("CoreAction onInternalReceiver: network changed");
    Object localObject = com.igexin.push.core.f.a().j();
    boolean bool1;
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((NetworkInfo)localObject).isAvailable()) {
          bool1 = true;
        }
      }
    }
    for (com.igexin.push.core.g.i = bool1;; com.igexin.push.core.g.i = false)
    {
      com.igexin.a.a.c.a.b("CoreAction|network changed|" + com.igexin.push.core.g.i);
      e();
      com.igexin.push.core.f.a().g().c(true);
      com.igexin.a.a.c.a.b("CoreAction network changed start to check condition status....");
      if (u()) {
        t();
      }
      return;
    }
  }
  
  private int D()
  {
    if ((com.igexin.push.core.g.ai.isEmpty()) && (com.igexin.push.core.g.o))
    {
      Cursor localCursor = com.igexin.push.core.f.a().k().a("message", new String[] { "status" }, new String[] { "0" }, null, null);
      if (localCursor != null)
      {
        while (localCursor.moveToNext())
        {
          Object localObject = localCursor.getBlob(localCursor.getColumnIndex("info"));
          try
          {
            localObject = new JSONObject(new String(com.igexin.a.b.a.c((byte[])localObject)));
            String str1 = ((JSONObject)localObject).getString("id");
            String str2 = ((JSONObject)localObject).getString("appid");
            String str3 = ((JSONObject)localObject).getString("messageid");
            String str4 = ((JSONObject)localObject).getString("taskid");
            String str5 = ((JSONObject)localObject).getString("appkey");
            String str6 = a().a(str4, str3);
            PushTaskBean localPushTaskBean = new PushTaskBean();
            localPushTaskBean.setAppid(str2);
            localPushTaskBean.setMessageId(str3);
            localPushTaskBean.setTaskId(str4);
            localPushTaskBean.setId(str1);
            localPushTaskBean.setAppKey(str5);
            localPushTaskBean.setCurrentActionid(1);
            localPushTaskBean.setStatus(localCursor.getInt(localCursor.getColumnIndex("status")));
            if (((JSONObject)localObject).has("cdnType")) {
              localPushTaskBean.setCDNType(((JSONObject)localObject).getBoolean("cdnType"));
            }
            if (((JSONObject)localObject).has("condition")) {
              b((JSONObject)localObject, localPushTaskBean);
            }
            com.igexin.push.core.g.ai.put(str6, localPushTaskBean);
          }
          catch (JSONException localJSONException) {}
        }
        localCursor.close();
      }
      com.igexin.push.core.g.o = false;
    }
    return com.igexin.push.core.g.ai.size();
  }
  
  public static e a()
  {
    if (c == null) {
      c = new e();
    }
    return c;
  }
  
  private void a(int paramInt, String paramString1, String paramString2)
  {
    paramString2 = new ContentValues();
    paramString2.put("status", Integer.valueOf(paramInt));
    com.igexin.push.core.f.a().k().a("message", paramString2, new String[] { "taskid" }, new String[] { paramString1 });
  }
  
  private void a(com.igexin.push.c.c.c paramC, PushTaskBean paramPushTaskBean, String paramString1, String paramString2)
  {
    paramC.a(new com.igexin.push.e.b.b(paramPushTaskBean, paramString1, k()));
    com.igexin.push.core.g.al.put(paramString2, paramC);
  }
  
  private void a(List paramList)
  {
    f localF = new f(this);
    PackageManager localPackageManager = com.igexin.push.core.g.g.getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i < localList.size()) {}
      try
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
        if ((localApplicationInfo.flags & 0x1) <= 0)
        {
          com.igexin.push.core.bean.l localL = new com.igexin.push.core.bean.l();
          localL.b(localApplicationInfo.loadLabel(localPackageManager).toString());
          localL.d(localApplicationInfo.packageName);
          localL.c(String.valueOf(localPackageInfo.versionCode));
          localL.a(localPackageInfo.versionName);
          paramList.add(localL);
        }
        i += 1;
        continue;
        Collections.sort(paramList, localF);
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private void b(List paramList)
  {
    if (paramList.size() > 0)
    {
      int i = 0;
      while (i < paramList.size())
      {
        String str = (String)paramList.get(i);
        PushTaskBean localPushTaskBean = (PushTaskBean)com.igexin.push.core.g.ai.get(str);
        localPushTaskBean.setStatus(com.igexin.push.core.a.l);
        com.igexin.push.core.g.ai.put(str, localPushTaskBean);
        i += 1;
      }
    }
  }
  
  private void b(JSONObject paramJSONObject, PushTaskBean paramPushTaskBean)
  {
    try
    {
      Object localObject = paramJSONObject.getJSONObject("condition");
      paramJSONObject = new HashMap();
      if (((JSONObject)localObject).has("wifi")) {
        paramJSONObject.put("wifi", ((JSONObject)localObject).getString("wifi"));
      }
      if (((JSONObject)localObject).has("screenOn")) {
        paramJSONObject.put("screenOn", ((JSONObject)localObject).getString("screenOn"));
      }
      if (((JSONObject)localObject).has("ssid"))
      {
        paramJSONObject.put("ssid", ((JSONObject)localObject).getString("ssid"));
        if (((JSONObject)localObject).has("bssid")) {
          paramJSONObject.put("bssid", ((JSONObject)localObject).getString("bssid"));
        }
      }
      if (((JSONObject)localObject).has("duration"))
      {
        String str = ((JSONObject)localObject).getString("duration");
        if (str.contains("-"))
        {
          int i = str.indexOf("-");
          localObject = str.substring(0, i);
          str = str.substring(i + 1, str.length());
          paramJSONObject.put("startTime", localObject);
          paramJSONObject.put("endTime", str);
        }
      }
      paramPushTaskBean.setConditionMap(paramJSONObject);
      return;
    }
    catch (JSONException paramJSONObject) {}
  }
  
  private void e(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("taskid");
    String str2 = paramIntent.getStringExtra("messageid");
    String str3 = paramIntent.getStringExtra("actionid");
    String str4 = paramIntent.getStringExtra("accesstoken");
    int i = paramIntent.getIntExtra("notifID", 0);
    paramIntent = (NotificationManager)com.igexin.push.core.g.g.getSystemService("notification");
    if (i != 0) {
      paramIntent.cancel(i);
    }
    while (!str4.equals(com.igexin.push.core.g.au))
    {
      return;
      if (com.igexin.push.core.g.aj.get(str1) != null) {
        paramIntent.cancel(((Integer)com.igexin.push.core.g.aj.get(str1)).intValue());
      }
    }
    b(str1, str2, str3);
  }
  
  /* Error */
  private void f(Intent paramIntent)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: ldc_w 262
    //   6: invokevirtual 488	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore 4
    //   11: aload_1
    //   12: ldc_w 260
    //   15: invokevirtual 488	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   18: astore 5
    //   20: aload_1
    //   21: ldc_w 258
    //   24: invokevirtual 488	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   27: astore 8
    //   29: aload_1
    //   30: ldc_w 525
    //   33: invokevirtual 488	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   36: astore 6
    //   38: new 328	android/content/ContentValues
    //   41: dup
    //   42: invokespecial 329	android/content/ContentValues:<init>	()V
    //   45: astore 7
    //   47: new 159	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   54: ldc_w 527
    //   57: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: aload 4
    //   62: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: astore_1
    //   69: aload 7
    //   71: ldc_w 262
    //   74: aload 4
    //   76: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload 7
    //   81: ldc_w 258
    //   84: aload 8
    //   86: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload 7
    //   91: ldc_w 532
    //   94: aload_1
    //   95: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: aload 7
    //   100: ldc_w 534
    //   103: invokestatic 539	java/lang/System:currentTimeMillis	()J
    //   106: invokestatic 544	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   109: invokevirtual 547	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   112: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   115: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   118: ldc -44
    //   120: iconst_1
    //   121: anewarray 214	java/lang/String
    //   124: dup
    //   125: iconst_0
    //   126: ldc_w 532
    //   129: aastore
    //   130: iconst_1
    //   131: anewarray 214	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: aload_1
    //   137: aastore
    //   138: aconst_null
    //   139: aconst_null
    //   140: invokevirtual 223	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   143: astore_1
    //   144: aload_1
    //   145: ifnull +91 -> 236
    //   148: aload_1
    //   149: invokeinterface 550 1 0
    //   154: ifne +82 -> 236
    //   157: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   160: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   163: ldc -44
    //   165: aload 7
    //   167: invokevirtual 553	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   170: aload 6
    //   172: getstatic 555	com/igexin/push/core/g:e	Ljava/lang/String;
    //   175: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   178: istore_2
    //   179: iload_2
    //   180: ifeq +56 -> 236
    //   183: aload 5
    //   185: ifnull +8 -> 193
    //   188: aload 4
    //   190: ifnonnull +14 -> 204
    //   193: aload_1
    //   194: ifnull +9 -> 203
    //   197: aload_1
    //   198: invokeinterface 319 1 0
    //   203: return
    //   204: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   207: ifnull +29 -> 236
    //   210: aload_0
    //   211: aload 4
    //   213: aload 5
    //   215: invokevirtual 558	com/igexin/push/core/a/e:b	(Ljava/lang/String;Ljava/lang/String;)Lcom/igexin/push/core/b;
    //   218: getstatic 563	com/igexin/push/core/b:a	Lcom/igexin/push/core/b;
    //   221: if_acmpne +15 -> 236
    //   224: aload_0
    //   225: aload 4
    //   227: aload 5
    //   229: ldc_w 565
    //   232: invokevirtual 567	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   235: pop
    //   236: aload_1
    //   237: ifnull -34 -> 203
    //   240: aload_1
    //   241: invokeinterface 319 1 0
    //   246: return
    //   247: astore_1
    //   248: aconst_null
    //   249: astore_1
    //   250: aload_1
    //   251: ifnull -48 -> 203
    //   254: aload_1
    //   255: invokeinterface 319 1 0
    //   260: return
    //   261: astore 4
    //   263: aload_3
    //   264: astore_1
    //   265: aload 4
    //   267: astore_3
    //   268: aload_1
    //   269: ifnull +9 -> 278
    //   272: aload_1
    //   273: invokeinterface 319 1 0
    //   278: aload_3
    //   279: athrow
    //   280: astore_3
    //   281: goto -13 -> 268
    //   284: astore_3
    //   285: goto -35 -> 250
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	288	0	this	e
    //   0	288	1	paramIntent	Intent
    //   178	2	2	bool	boolean
    //   1	278	3	localObject1	Object
    //   280	1	3	localObject2	Object
    //   284	1	3	localException	Exception
    //   9	217	4	str1	String
    //   261	5	4	localObject3	Object
    //   18	210	5	str2	String
    //   36	135	6	str3	String
    //   45	121	7	localContentValues	ContentValues
    //   27	58	8	str4	String
    // Exception table:
    //   from	to	target	type
    //   112	144	247	java/lang/Exception
    //   112	144	261	finally
    //   148	179	280	finally
    //   204	236	280	finally
    //   148	179	284	java/lang/Exception
    //   204	236	284	java/lang/Exception
  }
  
  private void f(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("package:")))
    {
      paramString = paramString.substring(8);
      if (com.igexin.push.core.c.f.a().d().containsKey(paramString)) {
        com.igexin.push.core.c.f.a().d().remove(paramString);
      }
    }
  }
  
  private void g(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("package:"))) {
      paramString = paramString.substring(8);
    }
    try
    {
      Object localObject1 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo(paramString, 4);
      int j;
      int i;
      if (localObject1 != null)
      {
        localObject1 = ((PackageInfo)localObject1).services;
        if (localObject1 != null)
        {
          j = localObject1.length;
          i = 0;
        }
      }
      for (;;)
      {
        if (i < j)
        {
          Object localObject2 = localObject1[i];
          if ((com.igexin.push.core.a.o.equals(localObject2.name)) || (com.igexin.push.core.a.n.equals(localObject2.name)) || (com.igexin.push.core.a.p.equals(localObject2.name))) {
            com.igexin.push.core.c.f.a().d().put(paramString, localObject2.name);
          }
        }
        else
        {
          return;
        }
        i += 1;
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
  }
  
  /* Error */
  private void h(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 614	java/io/File
    //   5: dup
    //   6: getstatic 617	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   9: invokespecial 618	java/io/File:<init>	(Ljava/lang/String;)V
    //   12: astore_2
    //   13: aload_2
    //   14: invokevirtual 621	java/io/File:exists	()Z
    //   17: ifne +8 -> 25
    //   20: aload_2
    //   21: invokevirtual 624	java/io/File:createNewFile	()Z
    //   24: pop
    //   25: new 626	java/io/FileOutputStream
    //   28: dup
    //   29: getstatic 617	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   32: invokespecial 627	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   35: astore_2
    //   36: aload_2
    //   37: aload_1
    //   38: invokestatic 629	com/igexin/a/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   41: invokevirtual 633	java/lang/String:getBytes	()[B
    //   44: invokevirtual 636	java/io/FileOutputStream:write	([B)V
    //   47: aload_2
    //   48: ifnull +7 -> 55
    //   51: aload_2
    //   52: invokevirtual 637	java/io/FileOutputStream:close	()V
    //   55: return
    //   56: astore_1
    //   57: aload_3
    //   58: astore_1
    //   59: aload_1
    //   60: ifnull -5 -> 55
    //   63: aload_1
    //   64: invokevirtual 637	java/io/FileOutputStream:close	()V
    //   67: return
    //   68: astore_1
    //   69: return
    //   70: astore_1
    //   71: aconst_null
    //   72: astore_2
    //   73: aload_2
    //   74: ifnull +7 -> 81
    //   77: aload_2
    //   78: invokevirtual 637	java/io/FileOutputStream:close	()V
    //   81: aload_1
    //   82: athrow
    //   83: astore_1
    //   84: return
    //   85: astore_2
    //   86: goto -5 -> 81
    //   89: astore_1
    //   90: goto -17 -> 73
    //   93: astore_1
    //   94: aload_2
    //   95: astore_1
    //   96: goto -37 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	e
    //   0	99	1	paramString	String
    //   12	66	2	localObject1	Object
    //   85	10	2	localException	Exception
    //   1	57	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	25	56	java/lang/Exception
    //   25	36	56	java/lang/Exception
    //   63	67	68	java/lang/Exception
    //   2	25	70	finally
    //   25	36	70	finally
    //   51	55	83	java/lang/Exception
    //   77	81	85	java/lang/Exception
    //   36	47	89	finally
    //   36	47	93	java/lang/Exception
  }
  
  public void A()
  {
    if (com.igexin.push.core.g.O < System.currentTimeMillis()) {
      com.igexin.push.core.c.f.a().a(false);
    }
  }
  
  public void B()
  {
    if (!com.igexin.push.core.g.af) {
      com.igexin.push.core.g.af = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.c.g(), false, true);
    }
    if (!com.igexin.push.core.g.ag) {
      com.igexin.push.core.g.ag = com.igexin.a.a.b.d.c().a(com.igexin.push.e.b.e.g(), true, true);
    }
    if (!com.igexin.push.core.g.ah) {
      com.igexin.push.core.f.a().c();
    }
  }
  
  public com.igexin.push.core.bean.f a(JSONObject paramJSONObject)
  {
    com.igexin.push.core.bean.f localF = new com.igexin.push.core.bean.f();
    localF.a(paramJSONObject.getString("version"));
    paramJSONObject = paramJSONObject.getJSONArray("extensions");
    if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
    {
      HashMap localHashMap = new HashMap();
      int i = 0;
      while (i < paramJSONObject.length())
      {
        JSONObject localJSONObject = (JSONObject)paramJSONObject.get(i);
        com.igexin.push.core.bean.e localE = new com.igexin.push.core.bean.e();
        localE.a(localJSONObject.getInt("id"));
        localE.a(localJSONObject.getString("version"));
        localE.b(localJSONObject.getString("name"));
        localE.c(localJSONObject.getString("cls_name"));
        localE.d(localJSONObject.getString("url"));
        localE.e(localJSONObject.getString("checksum"));
        localE.f(localJSONObject.getString("key"));
        if (localJSONObject.has("isdestroy")) {
          localE.a(localJSONObject.getBoolean("isdestroy"));
        }
        if (localJSONObject.has("effective"))
        {
          String str = localJSONObject.getString("effective");
          long l2 = 0L;
          long l1 = l2;
          if (str != null)
          {
            l1 = l2;
            if (str.length() <= 13) {
              l1 = Long.parseLong(str);
            }
          }
          localE.a(l1);
        }
        if (localJSONObject.has("loadTime")) {
          localE.b(localJSONObject.getLong("loadTime"));
        }
        localHashMap.put(Integer.valueOf(localE.a()), localE);
        i += 1;
      }
      localF.a(localHashMap);
      return localF;
    }
    localF.a(new HashMap());
    return localF;
  }
  
  public String a(com.igexin.push.core.bean.f paramF)
  {
    JSONObject localJSONObject = new JSONObject();
    label367:
    for (;;)
    {
      try
      {
        Object localObject3 = paramF.a();
        Object localObject2 = paramF.b();
        Object localObject1 = "[]";
        if (localObject3 != null) {
          localJSONObject.put("version", localObject3);
        }
        paramF = (com.igexin.push.core.bean.f)localObject1;
        if (localObject2 != null)
        {
          paramF = (com.igexin.push.core.bean.f)localObject1;
          if (((Map)localObject2).size() > 0)
          {
            paramF = "[";
            localObject1 = ((Map)localObject2).entrySet().iterator();
            if (((Iterator)localObject1).hasNext())
            {
              localObject2 = (com.igexin.push.core.bean.e)((Map.Entry)((Iterator)localObject1).next()).getValue();
              localObject3 = new JSONObject();
              ((JSONObject)localObject3).put("id", ((com.igexin.push.core.bean.e)localObject2).a());
              ((JSONObject)localObject3).put("version", ((com.igexin.push.core.bean.e)localObject2).b());
              ((JSONObject)localObject3).put("name", ((com.igexin.push.core.bean.e)localObject2).c());
              ((JSONObject)localObject3).put("cls_name", ((com.igexin.push.core.bean.e)localObject2).d());
              ((JSONObject)localObject3).put("url", ((com.igexin.push.core.bean.e)localObject2).e());
              ((JSONObject)localObject3).put("checksum", ((com.igexin.push.core.bean.e)localObject2).f());
              ((JSONObject)localObject3).put("isdestroy", ((com.igexin.push.core.bean.e)localObject2).g());
              ((JSONObject)localObject3).put("effective", ((com.igexin.push.core.bean.e)localObject2).h());
              ((JSONObject)localObject3).put("loadTime", ((com.igexin.push.core.bean.e)localObject2).i());
              ((JSONObject)localObject3).put("key", ((com.igexin.push.core.bean.e)localObject2).j());
              paramF = paramF + ((JSONObject)localObject3).toString();
              paramF = paramF + ",";
              continue;
            }
            if (!paramF.endsWith(",")) {
              break label367;
            }
            paramF = paramF.substring(0, paramF.length() - 1);
            paramF = paramF + "]";
          }
        }
        localJSONObject.put("extensions", new JSONArray(paramF));
        paramF = localJSONObject.toString();
        return paramF;
      }
      catch (JSONException paramF)
      {
        return null;
      }
    }
  }
  
  public String a(String paramString1, String paramString2)
  {
    return paramString1 + ":" + paramString2;
  }
  
  /* Error */
  public String a(boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 11
    //   3: aconst_null
    //   4: astore 12
    //   6: new 817	java/text/SimpleDateFormat
    //   9: dup
    //   10: ldc_w 819
    //   13: invokespecial 820	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   16: new 822	java/util/Date
    //   19: dup
    //   20: invokespecial 823	java/util/Date:<init>	()V
    //   23: invokevirtual 827	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   26: astore 13
    //   28: iload_2
    //   29: iconst_m1
    //   30: if_icmpne +83 -> 113
    //   33: new 159	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   40: aload 13
    //   42: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc_w 829
    //   48: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   54: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 829
    //   60: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc_w 833
    //   66: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 829
    //   72: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: getstatic 835	com/igexin/push/core/g:t	Ljava/lang/String;
    //   78: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore 13
    //   86: aload 13
    //   88: astore 11
    //   90: aload 11
    //   92: astore 13
    //   94: aload 12
    //   96: ifnull +14 -> 110
    //   99: aload 12
    //   101: invokeinterface 319 1 0
    //   106: aload 11
    //   108: astore 13
    //   110: aload 13
    //   112: areturn
    //   113: iload_2
    //   114: ifne +983 -> 1097
    //   117: iload_1
    //   118: ifeq +578 -> 696
    //   121: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   124: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   127: ldc_w 837
    //   130: iconst_1
    //   131: anewarray 214	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: ldc_w 839
    //   139: aastore
    //   140: iconst_2
    //   141: anewarray 214	java/lang/String
    //   144: dup
    //   145: iconst_0
    //   146: ldc_w 565
    //   149: aastore
    //   150: dup
    //   151: iconst_1
    //   152: ldc_w 841
    //   155: aastore
    //   156: aconst_null
    //   157: aconst_null
    //   158: invokevirtual 223	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   161: astore 12
    //   163: aload 12
    //   165: astore 11
    //   167: aload 11
    //   169: ifnull +914 -> 1083
    //   172: aconst_null
    //   173: astore 12
    //   175: aload 12
    //   177: astore 13
    //   179: aload 11
    //   181: invokeinterface 228 1 0
    //   186: ifeq +900 -> 1086
    //   189: aload 11
    //   191: aload 11
    //   193: ldc_w 843
    //   196: invokeinterface 846 2 0
    //   201: invokeinterface 296 2 0
    //   206: istore_2
    //   207: aload 11
    //   209: aload 11
    //   211: ldc_w 848
    //   214: invokeinterface 846 2 0
    //   219: invokeinterface 296 2 0
    //   224: istore_3
    //   225: aload 11
    //   227: aload 11
    //   229: ldc_w 850
    //   232: invokeinterface 846 2 0
    //   237: invokeinterface 296 2 0
    //   242: istore 4
    //   244: aload 11
    //   246: aload 11
    //   248: ldc_w 852
    //   251: invokeinterface 846 2 0
    //   256: invokeinterface 296 2 0
    //   261: istore 5
    //   263: aload 11
    //   265: aload 11
    //   267: ldc_w 854
    //   270: invokeinterface 846 2 0
    //   275: invokeinterface 296 2 0
    //   280: istore 6
    //   282: aload 11
    //   284: aload 11
    //   286: ldc_w 856
    //   289: invokeinterface 846 2 0
    //   294: invokeinterface 296 2 0
    //   299: istore 7
    //   301: aload 11
    //   303: aload 11
    //   305: ldc_w 858
    //   308: invokeinterface 846 2 0
    //   313: invokeinterface 296 2 0
    //   318: istore 8
    //   320: new 159	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   327: aload 11
    //   329: aload 11
    //   331: ldc_w 860
    //   334: invokeinterface 846 2 0
    //   339: invokeinterface 862 2 0
    //   344: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: ldc_w 864
    //   350: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: astore 13
    //   358: aload 12
    //   360: ifnonnull +379 -> 739
    //   363: new 159	java/lang/StringBuilder
    //   366: dup
    //   367: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   370: aload 13
    //   372: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: ldc_w 829
    //   378: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   384: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: ldc_w 829
    //   390: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   393: ldc_w 866
    //   396: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: ldc_w 829
    //   402: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: iload_2
    //   406: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   409: ldc_w 871
    //   412: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload 13
    //   417: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: ldc_w 829
    //   423: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   429: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: ldc_w 829
    //   435: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: ldc_w 873
    //   441: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: ldc_w 829
    //   447: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: iload_3
    //   451: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   454: ldc_w 871
    //   457: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: aload 13
    //   462: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: ldc_w 829
    //   468: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   474: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: ldc_w 829
    //   480: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: ldc_w 875
    //   486: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: ldc_w 829
    //   492: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: iload 4
    //   497: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   500: ldc_w 871
    //   503: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: aload 13
    //   508: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: ldc_w 829
    //   514: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   520: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: ldc_w 829
    //   526: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: ldc_w 877
    //   532: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   535: ldc_w 829
    //   538: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: iload 5
    //   543: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   546: ldc_w 871
    //   549: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: aload 13
    //   554: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: ldc_w 829
    //   560: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   566: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: ldc_w 829
    //   572: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: ldc_w 879
    //   578: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: ldc_w 829
    //   584: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   587: iload 6
    //   589: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   592: ldc_w 871
    //   595: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: aload 13
    //   600: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   603: ldc_w 829
    //   606: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   609: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   612: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: ldc_w 829
    //   618: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: ldc_w 881
    //   624: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: ldc_w 829
    //   630: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: iload 7
    //   635: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   638: ldc_w 871
    //   641: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: aload 13
    //   646: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: ldc_w 829
    //   652: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   658: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   661: ldc_w 829
    //   664: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: ldc_w 883
    //   670: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: ldc_w 829
    //   676: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   679: iload 8
    //   681: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   684: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   687: astore 13
    //   689: aload 13
    //   691: astore 12
    //   693: goto -518 -> 175
    //   696: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   699: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   702: ldc_w 837
    //   705: iconst_1
    //   706: anewarray 214	java/lang/String
    //   709: dup
    //   710: iconst_0
    //   711: ldc_w 839
    //   714: aastore
    //   715: iconst_1
    //   716: anewarray 214	java/lang/String
    //   719: dup
    //   720: iconst_0
    //   721: ldc_w 841
    //   724: aastore
    //   725: aconst_null
    //   726: aconst_null
    //   727: invokevirtual 223	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   730: astore 12
    //   732: aload 12
    //   734: astore 11
    //   736: goto -569 -> 167
    //   739: new 159	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   746: aload 12
    //   748: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: ldc_w 871
    //   754: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: aload 13
    //   759: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: ldc_w 829
    //   765: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   768: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   771: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   774: ldc_w 829
    //   777: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   780: ldc_w 866
    //   783: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   786: ldc_w 829
    //   789: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   792: iload_2
    //   793: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   796: ldc_w 871
    //   799: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: aload 13
    //   804: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: ldc_w 829
    //   810: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   816: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   819: ldc_w 829
    //   822: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   825: ldc_w 873
    //   828: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   831: ldc_w 829
    //   834: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: iload_3
    //   838: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   841: ldc_w 871
    //   844: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: aload 13
    //   849: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   852: ldc_w 829
    //   855: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   858: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   861: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   864: ldc_w 829
    //   867: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   870: ldc_w 875
    //   873: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   876: ldc_w 829
    //   879: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   882: iload 4
    //   884: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   887: ldc_w 871
    //   890: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: aload 13
    //   895: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   898: ldc_w 829
    //   901: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   904: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   907: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   910: ldc_w 829
    //   913: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   916: ldc_w 877
    //   919: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   922: ldc_w 829
    //   925: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   928: iload 5
    //   930: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   933: ldc_w 871
    //   936: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   939: aload 13
    //   941: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   944: ldc_w 829
    //   947: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   953: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: ldc_w 829
    //   959: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: ldc_w 879
    //   965: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   968: ldc_w 829
    //   971: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   974: iload 6
    //   976: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   979: ldc_w 871
    //   982: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: aload 13
    //   987: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   990: ldc_w 829
    //   993: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   999: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: ldc_w 829
    //   1005: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: ldc_w 881
    //   1011: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: ldc_w 829
    //   1017: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1020: iload 7
    //   1022: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1025: ldc_w 871
    //   1028: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: aload 13
    //   1033: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1036: ldc_w 829
    //   1039: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   1045: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1048: ldc_w 829
    //   1051: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: ldc_w 883
    //   1057: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1060: ldc_w 829
    //   1063: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1066: iload 8
    //   1068: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1071: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1074: astore 13
    //   1076: aload 13
    //   1078: astore 12
    //   1080: goto -905 -> 175
    //   1083: aconst_null
    //   1084: astore 13
    //   1086: aload 11
    //   1088: astore 12
    //   1090: aload 13
    //   1092: astore 11
    //   1094: goto -1004 -> 90
    //   1097: iload_2
    //   1098: iconst_1
    //   1099: if_icmpne +145 -> 1244
    //   1102: invokestatic 114	com/igexin/push/core/i:a	()Lcom/igexin/push/core/i;
    //   1105: getfield 885	com/igexin/push/core/i:a	J
    //   1108: lstore 9
    //   1110: getstatic 889	com/igexin/push/config/l:d	I
    //   1113: ifle +13 -> 1126
    //   1116: getstatic 889	com/igexin/push/config/l:d	I
    //   1119: sipush 1000
    //   1122: imul
    //   1123: i2l
    //   1124: lstore 9
    //   1126: new 159	java/lang/StringBuilder
    //   1129: dup
    //   1130: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   1133: getstatic 891	com/igexin/push/config/l:a	I
    //   1136: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1139: ldc_w 806
    //   1142: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1145: getstatic 893	com/igexin/push/config/l:b	I
    //   1148: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1151: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1154: astore 14
    //   1156: new 159	java/lang/StringBuilder
    //   1159: dup
    //   1160: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   1163: aload 13
    //   1165: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1168: ldc_w 829
    //   1171: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: getstatic 896	com/igexin/push/core/g:s	Ljava/lang/String;
    //   1177: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1180: ldc_w 829
    //   1183: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1186: getstatic 898	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1189: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1192: ldc_w 829
    //   1195: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: getstatic 900	com/igexin/push/core/g:j	Z
    //   1201: invokevirtual 169	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1204: ldc_w 829
    //   1207: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: aload 14
    //   1212: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1215: ldc_w 829
    //   1218: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1221: lload 9
    //   1223: invokevirtual 903	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1226: ldc_w 829
    //   1229: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1232: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1235: astore 13
    //   1237: aload 13
    //   1239: astore 11
    //   1241: goto -1151 -> 90
    //   1244: iload_2
    //   1245: iconst_4
    //   1246: if_icmpne +57 -> 1303
    //   1249: new 159	java/lang/StringBuilder
    //   1252: dup
    //   1253: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   1256: aload 13
    //   1258: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: ldc_w 829
    //   1264: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1267: getstatic 896	com/igexin/push/core/g:s	Ljava/lang/String;
    //   1270: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1273: ldc_w 829
    //   1276: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1279: getstatic 898	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1282: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1285: ldc_w 829
    //   1288: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1291: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1294: astore 13
    //   1296: aload 13
    //   1298: astore 11
    //   1300: goto -1210 -> 90
    //   1303: iload_2
    //   1304: iconst_5
    //   1305: if_icmpne +105 -> 1410
    //   1308: new 159	java/lang/StringBuilder
    //   1311: dup
    //   1312: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   1315: aload 13
    //   1317: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1320: ldc_w 829
    //   1323: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1326: getstatic 896	com/igexin/push/core/g:s	Ljava/lang/String;
    //   1329: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1332: ldc_w 829
    //   1335: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: getstatic 898	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1341: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1344: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1347: astore 13
    //   1349: aload 13
    //   1351: astore 11
    //   1353: goto -1263 -> 90
    //   1356: astore 12
    //   1358: aconst_null
    //   1359: astore 12
    //   1361: aload 12
    //   1363: astore 13
    //   1365: aload 11
    //   1367: ifnull -1257 -> 110
    //   1370: aload 11
    //   1372: invokeinterface 319 1 0
    //   1377: aload 12
    //   1379: areturn
    //   1380: astore 12
    //   1382: aconst_null
    //   1383: astore 11
    //   1385: aload 11
    //   1387: ifnull +10 -> 1397
    //   1390: aload 11
    //   1392: invokeinterface 319 1 0
    //   1397: aload 12
    //   1399: athrow
    //   1400: astore 12
    //   1402: goto -17 -> 1385
    //   1405: astore 13
    //   1407: goto -46 -> 1361
    //   1410: aconst_null
    //   1411: astore 11
    //   1413: goto -1323 -> 90
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1416	0	this	e
    //   0	1416	1	paramBoolean	boolean
    //   0	1416	2	paramInt	int
    //   224	614	3	i	int
    //   242	641	4	j	int
    //   261	668	5	k	int
    //   280	695	6	m	int
    //   299	722	7	n	int
    //   318	749	8	i1	int
    //   1108	114	9	l	long
    //   1	1411	11	localObject1	Object
    //   4	1085	12	localObject2	Object
    //   1356	1	12	localException1	Exception
    //   1359	19	12	str1	String
    //   1380	18	12	localObject3	Object
    //   1400	1	12	localObject4	Object
    //   26	1338	13	localObject5	Object
    //   1405	1	13	localException2	Exception
    //   1154	57	14	str2	String
    // Exception table:
    //   from	to	target	type
    //   33	86	1356	java/lang/Exception
    //   121	163	1356	java/lang/Exception
    //   696	732	1356	java/lang/Exception
    //   1102	1110	1356	java/lang/Exception
    //   1110	1126	1356	java/lang/Exception
    //   1126	1237	1356	java/lang/Exception
    //   1249	1296	1356	java/lang/Exception
    //   1308	1349	1356	java/lang/Exception
    //   33	86	1380	finally
    //   121	163	1380	finally
    //   696	732	1380	finally
    //   1102	1110	1380	finally
    //   1110	1126	1380	finally
    //   1126	1237	1380	finally
    //   1249	1296	1380	finally
    //   1308	1349	1380	finally
    //   179	358	1400	finally
    //   363	689	1400	finally
    //   739	1076	1400	finally
    //   179	358	1405	java/lang/Exception
    //   363	689	1405	java/lang/Exception
    //   739	1076	1405	java/lang/Exception
  }
  
  public void a(int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(32);
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10008);
    localBundle.putInt("pid", paramInt);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
  }
  
  public void a(int paramInt1, int paramInt2, String paramString)
  {
    com.igexin.push.config.l.a = paramInt1;
    com.igexin.push.config.l.b = paramInt2;
    com.igexin.push.config.a.a().b();
    com.igexin.push.a.a.c.c().d();
  }
  
  public void a(int paramInt, String paramString)
  {
    com.igexin.push.config.l.d = paramInt;
    com.igexin.push.config.a.a().c();
    if (com.igexin.push.core.g.m)
    {
      com.igexin.a.a.c.a.b("setHeartbeatInterval heartbeatReq");
      if (System.currentTimeMillis() - com.igexin.push.core.g.R > 5000L)
      {
        com.igexin.push.core.g.R = System.currentTimeMillis();
        f();
      }
    }
  }
  
  public void a(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      com.igexin.push.core.f.a().a(false);
      if (!paramIntent.hasExtra("op_app")) {
        break label51;
      }
    }
    label51:
    for (com.igexin.push.core.g.C = paramIntent.getStringExtra("op_app");; com.igexin.push.core.g.C = "")
    {
      com.igexin.push.core.g.n = false;
      if (com.igexin.push.core.g.m)
      {
        l();
        com.igexin.push.core.g.n = true;
      }
      return;
    }
  }
  
  public void a(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("action");
    if (str1.equals("setTag")) {
      if (com.igexin.push.config.l.j) {
        b(paramBundle.getString("tags"));
      }
    }
    do
    {
      String str2;
      String str3;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                  if (!str1.equals("setSilentTime")) {
                    break;
                  }
                } while (!com.igexin.push.config.l.k);
                a(paramBundle.getInt("beginHour", 0), paramBundle.getInt("duration", 0), com.igexin.push.core.g.g.getPackageName());
                return;
                if (!str1.equals("sendMessage")) {
                  break;
                }
                com.igexin.a.a.c.a.b("CoreAction onPushManagerMessage recevie action : sendMessage");
              } while (!com.igexin.push.config.l.i);
              str1 = paramBundle.getString("taskid");
              paramBundle = paramBundle.getByteArray("extraData");
              com.igexin.a.a.c.a.b("CoreAction receive broadcast msg data , task id : " + str1 + " ######@##@@@#");
              a(str1, paramBundle);
              return;
              if (str1.equals("stopService"))
              {
                com.igexin.push.core.f.a().a(com.igexin.push.core.g.g.getPackageName());
                return;
              }
              if (!str1.equals("setHeartbeatInterval")) {
                break;
              }
            } while (!com.igexin.push.config.l.l);
            a(paramBundle.getInt("interval", 0), com.igexin.push.core.g.g.getPackageName());
            return;
            if (!str1.equals("setSocketTimeout")) {
              break;
            }
          } while (!com.igexin.push.config.l.m);
          b(paramBundle.getInt("timeout", 0), com.igexin.push.core.g.g.getPackageName());
          return;
          if (!str1.equals("sendFeedbackMessage")) {
            break;
          }
        } while ((!com.igexin.push.config.l.s) || (com.igexin.push.core.g.an > 200));
        str1 = paramBundle.getString("taskid");
        str2 = paramBundle.getString("messageid");
        paramBundle = paramBundle.getString("actionid");
        str3 = str1 + ":" + str2 + ":" + paramBundle;
      } while (com.igexin.push.core.g.am.get(str3) != null);
      long l = System.currentTimeMillis();
      PushTaskBean localPushTaskBean = new PushTaskBean();
      localPushTaskBean.setTaskId(str1);
      localPushTaskBean.setMessageId(str2);
      localPushTaskBean.setAppid(com.igexin.push.core.g.a);
      localPushTaskBean.setAppKey(com.igexin.push.core.g.b);
      a(localPushTaskBean, paramBundle);
      com.igexin.push.core.g.an += 1;
      com.igexin.push.core.g.am.put(str3, Long.valueOf(l));
      return;
      if (str1.equals("turnOffPush"))
      {
        com.igexin.push.core.f.a().b(com.igexin.push.core.g.g.getPackageName());
        return;
      }
      if (str1.equals("bindAlias"))
      {
        paramBundle = paramBundle.getString("alias");
        com.igexin.a.a.c.a.b("-> CoreAction onPushManagerMessage bindAlias...");
        c(paramBundle);
        return;
      }
    } while (!str1.equals("unbindAlias"));
    str1 = paramBundle.getString("alias");
    boolean bool = paramBundle.getBoolean("isSeft");
    com.igexin.a.a.c.a.b("-> CoreAction onPushManagerMessage unbindAlias...");
    a(str1, bool);
  }
  
  public void a(PushTaskBean paramPushTaskBean)
  {
    com.igexin.push.c.c.c localC = new com.igexin.push.c.c.c();
    localC.a();
    localC.c = ("RCV" + paramPushTaskBean.getMessageId());
    localC.d = com.igexin.push.core.g.s;
    localC.a = ((int)System.currentTimeMillis());
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localC);
    com.igexin.a.a.c.a.b("cdnreceive|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId());
  }
  
  public void a(PushTaskBean paramPushTaskBean, String paramString)
  {
    if (paramPushTaskBean.isCDNType())
    {
      b(paramPushTaskBean, paramString);
      return;
    }
    a(paramPushTaskBean, paramString, "ok");
  }
  
  public void a(PushTaskBean paramPushTaskBean, String paramString1, String paramString2)
  {
    long l = System.currentTimeMillis();
    paramString2 = "{\"action\":\"pushmessage_feedback\",\"appid\":\"" + paramPushTaskBean.getAppid() + "\", \"id\":\"" + l + "\", \"appkey\":\"" + paramPushTaskBean.getAppKey() + "\", \"messageid\":\"" + paramPushTaskBean.getMessageId() + "\",\"taskid\":\"" + paramPushTaskBean.getTaskId() + "\",\"actionid\": \"" + paramString1 + "\",\"result\":\"" + paramString2 + "\",\"timestamp\":\"" + System.currentTimeMillis() + "\"}";
    Object localObject = new com.igexin.push.c.c.d();
    ((com.igexin.push.c.c.d)localObject).a();
    ((com.igexin.push.c.c.d)localObject).a = ((int)l);
    ((com.igexin.push.c.c.d)localObject).d = "17258000";
    ((com.igexin.push.c.c.d)localObject).e = paramString2;
    ((com.igexin.push.c.c.d)localObject).g = com.igexin.push.core.g.s;
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, (com.igexin.push.c.c.e)localObject);
    localObject = com.igexin.push.core.c.c.a();
    if (localObject != null) {
      ((com.igexin.push.core.c.c)localObject).a(new com.igexin.push.core.bean.i(l, paramString2, (byte)3, l));
    }
    com.igexin.a.a.c.a.b("feedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString1);
  }
  
  public void a(String paramString)
  {
    paramString = "{\"action\":\"received\",\"id\":\"" + paramString + "\"}";
    com.igexin.push.c.c.d localD = new com.igexin.push.c.c.d();
    localD.a();
    localD.a = ((int)System.currentTimeMillis());
    localD.d = "17258000";
    localD.e = paramString;
    localD.g = com.igexin.push.core.g.s;
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localD);
  }
  
  public void a(String paramString, com.igexin.push.c.c.a paramA, PushTaskBean paramPushTaskBean)
  {
    paramString = new com.igexin.push.e.a.a(new com.igexin.push.core.d.c(paramString, paramA, paramPushTaskBean));
    com.igexin.a.a.b.d.c().a(paramString, false, true);
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    paramString3 = new Intent("com.igexin.sdk.action.execute");
    paramString3.putExtra("taskid", paramString1);
    paramString3.putExtra("messageid", paramString2);
    paramString3.putExtra("appid", com.igexin.push.core.g.a);
    paramString3.putExtra("pkgname", com.igexin.push.core.g.e);
    com.igexin.push.core.f.a().a(paramString3);
  }
  
  public void a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(32);
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10006);
    localBundle.putString("appid", paramString1);
    localBundle.putString("taskid", paramString2);
    localBundle.putString("actionid", paramString3);
    localBundle.putString("result", paramString4);
    localBundle.putLong("timestamp", paramLong);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    if ((paramBoolean) && (com.igexin.a.b.a.b(com.igexin.push.core.g.s))) {}
    long l;
    do
    {
      do
      {
        return;
        l = System.currentTimeMillis();
      } while (l - com.igexin.push.core.g.T <= 5000L);
      String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date(l));
      if (!str.equals(com.igexin.push.core.g.S))
      {
        com.igexin.push.core.c.f.a().d(str);
        com.igexin.push.core.c.f.a().a(0);
      }
    } while (com.igexin.push.core.g.U >= 100);
    com.igexin.push.core.g.T = l;
    com.igexin.push.core.c.f.a().a(com.igexin.push.core.g.U + 1);
    paramString = new com.igexin.push.e.a.c(new com.igexin.push.core.d.i(SDKUrlConfig.getAmpServiceUrl(), paramString, paramBoolean));
    com.igexin.a.a.b.d.c().a(paramString, false, true);
  }
  
  public void a(String paramString, byte[] paramArrayOfByte)
  {
    Object localObject;
    long l;
    if (com.igexin.push.core.g.s != null)
    {
      localObject = new JSONObject();
      l = System.currentTimeMillis();
    }
    try
    {
      ((JSONObject)localObject).put("action", "sendmessage");
      ((JSONObject)localObject).put("id", String.valueOf(l));
      ((JSONObject)localObject).put("cid", com.igexin.push.core.g.s);
      ((JSONObject)localObject).put("appid", com.igexin.push.core.g.a);
      ((JSONObject)localObject).put("taskid", paramString);
      localObject = ((JSONObject)localObject).toString();
      com.igexin.push.c.c.d localD = new com.igexin.push.c.c.d();
      localD.a();
      localD.a = ((int)l);
      localD.d = com.igexin.push.core.g.s;
      localD.e = localObject;
      localD.f = paramArrayOfByte;
      localD.g = com.igexin.push.core.g.s;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localD, true);
      if ((paramString != null) && (paramString.startsWith("4T5@S_"))) {
        com.igexin.a.a.c.a.b("CoreAction sending lbs report message : " + (String)localObject);
      }
      return;
    }
    catch (JSONException paramString)
    {
      com.igexin.a.a.c.a.b("CoreAction" + paramString.toString());
    }
  }
  
  public void a(boolean paramBoolean) {}
  
  /* Error */
  public void a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 240	org/json/JSONObject
    //   5: dup
    //   6: new 214	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: iconst_0
    //   12: invokestatic 1248	android/util/Base64:decode	([BI)[B
    //   15: invokestatic 245	com/igexin/a/b/a:c	([B)[B
    //   18: invokespecial 248	java/lang/String:<init>	([B)V
    //   21: invokespecial 250	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   24: astore_1
    //   25: new 159	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   32: ldc_w 1250
    //   35: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 1253	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   48: aload_1
    //   49: ldc_w 1183
    //   52: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   55: ifeq +1006 -> 1061
    //   58: ldc_w 1103
    //   61: aload_1
    //   62: ldc_w 1183
    //   65: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   68: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   71: ifeq +990 -> 1061
    //   74: invokestatic 539	java/lang/System:currentTimeMillis	()J
    //   77: lstore_3
    //   78: invokestatic 579	com/igexin/push/core/c/f:a	()Lcom/igexin/push/core/c/f;
    //   81: lload_3
    //   82: invokevirtual 1256	com/igexin/push/core/c/f:g	(J)Z
    //   85: pop
    //   86: aload_1
    //   87: ldc_w 1258
    //   90: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   93: ifeq +968 -> 1061
    //   96: new 240	org/json/JSONObject
    //   99: dup
    //   100: aload_1
    //   101: ldc_w 1258
    //   104: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: invokespecial 250	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   110: astore_1
    //   111: aload_1
    //   112: ldc_w 1260
    //   115: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   118: ifeq +45 -> 163
    //   121: aload_1
    //   122: ldc_w 1260
    //   125: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 5
    //   130: aload 5
    //   132: ldc_w 1262
    //   135: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   138: ifne +14 -> 152
    //   141: aload 5
    //   143: ldc_w 1264
    //   146: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   149: ifeq +14 -> 163
    //   152: aload 5
    //   154: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   157: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   160: putstatic 1274	com/igexin/push/config/l:h	Z
    //   163: aload_1
    //   164: ldc_w 1276
    //   167: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   170: ifeq +45 -> 215
    //   173: aload_1
    //   174: ldc_w 1276
    //   177: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   180: astore 5
    //   182: aload 5
    //   184: ldc_w 1262
    //   187: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifne +14 -> 204
    //   193: aload 5
    //   195: ldc_w 1264
    //   198: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   201: ifeq +14 -> 215
    //   204: aload 5
    //   206: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   209: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   212: putstatic 1002	com/igexin/push/config/l:i	Z
    //   215: aload_1
    //   216: ldc_w 1278
    //   219: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   222: ifeq +45 -> 267
    //   225: aload_1
    //   226: ldc_w 1278
    //   229: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   232: astore 5
    //   234: aload 5
    //   236: ldc_w 1262
    //   239: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   242: ifne +14 -> 256
    //   245: aload 5
    //   247: ldc_w 1264
    //   250: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   253: ifeq +14 -> 267
    //   256: aload 5
    //   258: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   261: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   264: putstatic 1280	com/igexin/push/config/l:g	Z
    //   267: aload_1
    //   268: ldc_w 1282
    //   271: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   274: ifeq +45 -> 319
    //   277: aload_1
    //   278: ldc_w 1282
    //   281: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   284: astore 5
    //   286: aload 5
    //   288: ldc_w 1262
    //   291: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   294: ifne +14 -> 308
    //   297: aload 5
    //   299: ldc_w 1264
    //   302: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   305: ifeq +14 -> 319
    //   308: aload 5
    //   310: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   313: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   316: putstatic 1283	com/igexin/push/config/l:n	Z
    //   319: aload_1
    //   320: ldc_w 1285
    //   323: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   326: ifeq +45 -> 371
    //   329: aload_1
    //   330: ldc_w 1285
    //   333: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   336: astore 5
    //   338: aload 5
    //   340: ldc_w 1262
    //   343: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   346: ifne +14 -> 360
    //   349: aload 5
    //   351: ldc_w 1264
    //   354: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   357: ifeq +14 -> 371
    //   360: aload 5
    //   362: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   365: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   368: putstatic 1286	com/igexin/push/config/l:o	Z
    //   371: aload_1
    //   372: ldc_w 1288
    //   375: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   378: ifeq +45 -> 423
    //   381: aload_1
    //   382: ldc_w 1288
    //   385: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   388: astore 5
    //   390: aload 5
    //   392: ldc_w 1262
    //   395: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   398: ifne +14 -> 412
    //   401: aload 5
    //   403: ldc_w 1264
    //   406: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   409: ifeq +14 -> 423
    //   412: aload 5
    //   414: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   417: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   420: putstatic 1290	com/igexin/push/config/l:f	Z
    //   423: aload_1
    //   424: ldc_w 1292
    //   427: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   430: ifeq +67 -> 497
    //   433: aload_1
    //   434: ldc_w 1292
    //   437: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   440: astore 5
    //   442: aload 5
    //   444: ldc_w 1262
    //   447: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   450: ifne +14 -> 464
    //   453: aload 5
    //   455: ldc_w 1264
    //   458: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   461: ifeq +36 -> 497
    //   464: aload 5
    //   466: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   469: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   472: putstatic 988	com/igexin/push/config/l:k	Z
    //   475: getstatic 988	com/igexin/push/config/l:k	Z
    //   478: ifne +19 -> 497
    //   481: getstatic 893	com/igexin/push/config/l:b	I
    //   484: ifeq +13 -> 497
    //   487: aload_0
    //   488: bipush 12
    //   490: iconst_0
    //   491: ldc_w 1294
    //   494: invokevirtual 997	com/igexin/push/core/a/e:a	(IILjava/lang/String;)V
    //   497: aload_1
    //   498: ldc_w 1296
    //   501: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   504: ifeq +21 -> 525
    //   507: aload_1
    //   508: ldc_w 1296
    //   511: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   514: astore 5
    //   516: aload 5
    //   518: invokestatic 1299	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   521: i2l
    //   522: putstatic 1301	com/igexin/push/config/l:p	J
    //   525: aload_1
    //   526: ldc_w 1303
    //   529: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   532: ifeq +45 -> 577
    //   535: aload_1
    //   536: ldc_w 1303
    //   539: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   542: astore 5
    //   544: aload 5
    //   546: ldc_w 1262
    //   549: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   552: ifne +14 -> 566
    //   555: aload 5
    //   557: ldc_w 1264
    //   560: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   563: ifeq +14 -> 577
    //   566: aload 5
    //   568: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   571: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   574: putstatic 981	com/igexin/push/config/l:j	Z
    //   577: aload_1
    //   578: ldc_w 1305
    //   581: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   584: ifeq +45 -> 629
    //   587: aload_1
    //   588: ldc_w 1305
    //   591: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   594: astore 5
    //   596: aload 5
    //   598: ldc_w 1262
    //   601: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   604: ifne +14 -> 618
    //   607: aload 5
    //   609: ldc_w 1264
    //   612: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   615: ifeq +14 -> 629
    //   618: aload 5
    //   620: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   623: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   626: putstatic 1023	com/igexin/push/config/l:l	Z
    //   629: aload_1
    //   630: ldc_w 1307
    //   633: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   636: ifeq +45 -> 681
    //   639: aload_1
    //   640: ldc_w 1307
    //   643: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   646: astore 5
    //   648: aload 5
    //   650: ldc_w 1262
    //   653: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   656: ifne +14 -> 670
    //   659: aload 5
    //   661: ldc_w 1264
    //   664: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   667: ifeq +14 -> 681
    //   670: aload 5
    //   672: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   675: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   678: putstatic 1030	com/igexin/push/config/l:m	Z
    //   681: aload_1
    //   682: ldc_w 1309
    //   685: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   688: ifeq +45 -> 733
    //   691: aload_1
    //   692: ldc_w 1309
    //   695: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   698: astore 5
    //   700: aload 5
    //   702: ldc_w 1262
    //   705: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   708: ifne +14 -> 722
    //   711: aload 5
    //   713: ldc_w 1264
    //   716: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   719: ifeq +14 -> 733
    //   722: aload 5
    //   724: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   727: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   730: putstatic 1312	com/igexin/push/config/l:q	Z
    //   733: aload_1
    //   734: ldc_w 1314
    //   737: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   740: ifeq +45 -> 785
    //   743: aload_1
    //   744: ldc_w 1314
    //   747: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   750: astore 5
    //   752: aload 5
    //   754: ldc_w 1262
    //   757: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   760: ifne +14 -> 774
    //   763: aload 5
    //   765: ldc_w 1264
    //   768: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   771: ifeq +14 -> 785
    //   774: aload 5
    //   776: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   779: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   782: putstatic 1317	com/igexin/push/config/l:r	Z
    //   785: aload_1
    //   786: ldc_w 1319
    //   789: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   792: ifeq +45 -> 837
    //   795: aload_1
    //   796: ldc_w 1319
    //   799: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   802: astore 5
    //   804: aload 5
    //   806: ldc_w 1262
    //   809: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   812: ifne +14 -> 826
    //   815: aload 5
    //   817: ldc_w 1264
    //   820: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   823: ifeq +14 -> 837
    //   826: aload 5
    //   828: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   831: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   834: putstatic 1038	com/igexin/push/config/l:s	Z
    //   837: aload_1
    //   838: ldc_w 1321
    //   841: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   844: ifeq +13 -> 857
    //   847: aload_1
    //   848: ldc_w 1321
    //   851: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   854: putstatic 1323	com/igexin/push/config/l:u	Ljava/lang/String;
    //   857: aload_1
    //   858: ldc_w 1325
    //   861: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   864: ifeq +13 -> 877
    //   867: aload_1
    //   868: ldc_w 1325
    //   871: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   874: putstatic 1328	com/igexin/push/config/l:v	Ljava/lang/String;
    //   877: aload_1
    //   878: ldc_w 1330
    //   881: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   884: ifeq +45 -> 929
    //   887: aload_1
    //   888: ldc_w 1330
    //   891: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   894: astore 5
    //   896: aload 5
    //   898: ldc_w 1262
    //   901: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   904: ifne +14 -> 918
    //   907: aload 5
    //   909: ldc_w 1264
    //   912: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   915: ifeq +14 -> 929
    //   918: aload 5
    //   920: invokestatic 1269	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   923: invokevirtual 1272	java/lang/Boolean:booleanValue	()Z
    //   926: putstatic 1333	com/igexin/push/config/l:w	Z
    //   929: aload_1
    //   930: ldc_w 1335
    //   933: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   936: ifeq +119 -> 1055
    //   939: aload_1
    //   940: ldc_w 1335
    //   943: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   946: astore_1
    //   947: aload_1
    //   948: ifnull +107 -> 1055
    //   951: ldc_w 976
    //   954: aload_1
    //   955: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   958: ifne +97 -> 1055
    //   961: new 240	org/json/JSONObject
    //   964: dup
    //   965: aload_1
    //   966: invokespecial 250	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   969: astore_1
    //   970: aload_1
    //   971: ldc_w 682
    //   974: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   977: ifeq +78 -> 1055
    //   980: aload_1
    //   981: ldc_w 682
    //   984: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   987: astore 5
    //   989: getstatic 1338	com/igexin/push/config/l:t	Lcom/igexin/push/core/bean/f;
    //   992: ifnull +87 -> 1079
    //   995: aload 5
    //   997: getstatic 1338	com/igexin/push/config/l:t	Lcom/igexin/push/core/bean/f;
    //   1000: invokevirtual 743	com/igexin/push/core/bean/f:a	()Ljava/lang/String;
    //   1003: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1006: ifeq +73 -> 1079
    //   1009: iload_2
    //   1010: ifeq +45 -> 1055
    //   1013: aload_0
    //   1014: aload_1
    //   1015: invokevirtual 1340	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;)Lcom/igexin/push/core/bean/f;
    //   1018: astore_1
    //   1019: aload_1
    //   1020: ifnull +35 -> 1055
    //   1023: new 1342	android/os/Message
    //   1026: dup
    //   1027: invokespecial 1343	android/os/Message:<init>	()V
    //   1030: astore 5
    //   1032: aload 5
    //   1034: getstatic 1345	com/igexin/push/core/a:i	I
    //   1037: putfield 1348	android/os/Message:what	I
    //   1040: aload 5
    //   1042: aload_1
    //   1043: putfield 1351	android/os/Message:obj	Ljava/lang/Object;
    //   1046: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   1049: aload 5
    //   1051: invokevirtual 1354	com/igexin/push/core/f:a	(Landroid/os/Message;)Z
    //   1054: pop
    //   1055: invokestatic 938	com/igexin/push/config/a:a	()Lcom/igexin/push/config/a;
    //   1058: invokevirtual 1356	com/igexin/push/config/a:f	()V
    //   1061: return
    //   1062: astore_1
    //   1063: aload_0
    //   1064: aload_1
    //   1065: invokevirtual 1357	java/lang/Exception:toString	()Ljava/lang/String;
    //   1068: invokevirtual 1358	com/igexin/push/core/a/e:d	(Ljava/lang/String;)V
    //   1071: return
    //   1072: astore_1
    //   1073: return
    //   1074: astore 5
    //   1076: goto -551 -> 525
    //   1079: iconst_1
    //   1080: istore_2
    //   1081: goto -72 -> 1009
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1084	0	this	e
    //   0	1084	1	paramArrayOfByte	byte[]
    //   1	1080	2	i	int
    //   77	5	3	l	long
    //   128	922	5	localObject	Object
    //   1074	1	5	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	152	1062	java/lang/Exception
    //   152	163	1062	java/lang/Exception
    //   163	204	1062	java/lang/Exception
    //   204	215	1062	java/lang/Exception
    //   215	256	1062	java/lang/Exception
    //   256	267	1062	java/lang/Exception
    //   267	308	1062	java/lang/Exception
    //   308	319	1062	java/lang/Exception
    //   319	360	1062	java/lang/Exception
    //   360	371	1062	java/lang/Exception
    //   371	412	1062	java/lang/Exception
    //   412	423	1062	java/lang/Exception
    //   423	464	1062	java/lang/Exception
    //   464	497	1062	java/lang/Exception
    //   497	516	1062	java/lang/Exception
    //   525	566	1062	java/lang/Exception
    //   566	577	1062	java/lang/Exception
    //   577	618	1062	java/lang/Exception
    //   618	629	1062	java/lang/Exception
    //   629	670	1062	java/lang/Exception
    //   670	681	1062	java/lang/Exception
    //   681	722	1062	java/lang/Exception
    //   722	733	1062	java/lang/Exception
    //   733	774	1062	java/lang/Exception
    //   774	785	1062	java/lang/Exception
    //   785	826	1062	java/lang/Exception
    //   826	837	1062	java/lang/Exception
    //   837	857	1062	java/lang/Exception
    //   857	877	1062	java/lang/Exception
    //   877	918	1062	java/lang/Exception
    //   918	929	1062	java/lang/Exception
    //   929	947	1062	java/lang/Exception
    //   951	1009	1062	java/lang/Exception
    //   1013	1019	1062	java/lang/Exception
    //   1023	1055	1062	java/lang/Exception
    //   1055	1061	1062	java/lang/Exception
    //   2	152	1072	java/lang/Throwable
    //   152	163	1072	java/lang/Throwable
    //   163	204	1072	java/lang/Throwable
    //   204	215	1072	java/lang/Throwable
    //   215	256	1072	java/lang/Throwable
    //   256	267	1072	java/lang/Throwable
    //   267	308	1072	java/lang/Throwable
    //   308	319	1072	java/lang/Throwable
    //   319	360	1072	java/lang/Throwable
    //   360	371	1072	java/lang/Throwable
    //   371	412	1072	java/lang/Throwable
    //   412	423	1072	java/lang/Throwable
    //   423	464	1072	java/lang/Throwable
    //   464	497	1072	java/lang/Throwable
    //   497	516	1072	java/lang/Throwable
    //   516	525	1072	java/lang/Throwable
    //   525	566	1072	java/lang/Throwable
    //   566	577	1072	java/lang/Throwable
    //   577	618	1072	java/lang/Throwable
    //   618	629	1072	java/lang/Throwable
    //   629	670	1072	java/lang/Throwable
    //   670	681	1072	java/lang/Throwable
    //   681	722	1072	java/lang/Throwable
    //   722	733	1072	java/lang/Throwable
    //   733	774	1072	java/lang/Throwable
    //   774	785	1072	java/lang/Throwable
    //   785	826	1072	java/lang/Throwable
    //   826	837	1072	java/lang/Throwable
    //   837	857	1072	java/lang/Throwable
    //   857	877	1072	java/lang/Throwable
    //   877	918	1072	java/lang/Throwable
    //   918	929	1072	java/lang/Throwable
    //   929	947	1072	java/lang/Throwable
    //   951	1009	1072	java/lang/Throwable
    //   1013	1019	1072	java/lang/Throwable
    //   1023	1055	1072	java/lang/Throwable
    //   1055	1061	1072	java/lang/Throwable
    //   516	525	1074	java/lang/Exception
  }
  
  public boolean a(long paramLong)
  {
    Date localDate = new Date(paramLong);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(localDate);
    int k = localCalendar.get(11);
    int j = com.igexin.push.config.l.a + com.igexin.push.config.l.b;
    int i = j;
    if (j >= 24) {
      i = j - 24;
    }
    if (com.igexin.push.config.l.b == 0) {
      return false;
    }
    if (com.igexin.push.config.l.a < i)
    {
      if ((k >= com.igexin.push.config.l.a) && (k < i)) {
        return true;
      }
    }
    else if (com.igexin.push.config.l.a > i)
    {
      if ((k >= 0) && (k < i)) {
        return true;
      }
      if ((k >= com.igexin.push.config.l.a) && (k < 24)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean a(com.igexin.a.a.d.d paramD)
  {
    switch (paramD.b())
    {
    case -2046: 
    default: 
      return true;
    case -2047: 
      com.igexin.a.a.c.a.b("disconnected|network");
      com.igexin.push.core.i.a().a(com.igexin.push.core.k.c);
      w.d();
      if (((paramD.M instanceof ClosedChannelException)) || ((paramD.M instanceof SocketTimeoutException)) || ((paramD.M instanceof UnknownHostException)) || ((paramD.M instanceof UnresolvedAddressException)) || ((paramD.M instanceof UnknownServiceException))) {
        w.a();
      }
      if ((!com.igexin.push.core.g.j) || (!com.igexin.push.core.g.k))
      {
        if (com.igexin.push.core.g.m == true)
        {
          com.igexin.push.core.g.m = false;
          m();
        }
        com.igexin.a.a.b.d.c().a(com.igexin.a.a.b.a.a.g.class);
      }
      break;
    }
    for (;;)
    {
      return false;
      if (com.igexin.push.core.g.m == true)
      {
        com.igexin.push.core.g.m = false;
        m();
      }
      com.igexin.push.core.f.a().g().c(false);
      break;
      com.igexin.a.a.c.a.b("disconnected|user");
    }
  }
  
  public boolean a(com.igexin.push.c.c.e paramE)
  {
    if (paramE != null)
    {
      a localA = (a)a.get(Integer.valueOf(paramE.i));
      if (localA != null) {
        localA.a(paramE);
      }
      com.igexin.push.e.b.c.g().h();
      return true;
    }
    return false;
  }
  
  public boolean a(Object paramObject)
  {
    com.igexin.push.d.j localJ = com.igexin.push.core.f.a().g();
    if (((paramObject instanceof com.igexin.push.c.c.e)) && (localJ != null)) {
      localJ.a((com.igexin.push.c.c.e)paramObject);
    }
    for (;;)
    {
      com.igexin.a.a.c.a.b("doHandleFilter return false");
      return false;
      if ((paramObject instanceof com.igexin.a.a.b.a.a.b))
      {
        if (com.igexin.push.core.g.m == true)
        {
          com.igexin.push.core.g.m = false;
          m();
        }
      }
      else if (!(paramObject instanceof com.igexin.a.a.b.a.a.a)) {
        if ((paramObject instanceof com.igexin.push.c.b.a)) {
          localJ.c(false);
        } else if ((paramObject instanceof com.igexin.push.c.b.b)) {
          localJ.c(true);
        }
      }
    }
  }
  
  public boolean a(String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("taskid", paramString1);
    localBundle.putString("messageid", paramString2);
    localBundle.putString("actionid", paramString3);
    paramString1 = new Message();
    paramString1.what = com.igexin.push.core.a.h;
    paramString1.obj = localBundle;
    return com.igexin.push.core.f.a().a(paramString1);
  }
  
  public boolean a(JSONObject paramJSONObject, PushTaskBean paramPushTaskBean)
  {
    ArrayList localArrayList = new ArrayList();
    int i;
    int j;
    try
    {
      localJSONArray = paramJSONObject.getJSONArray("action_chains");
      i = 0;
      if (i >= localJSONArray.length()) {
        break label329;
      }
      paramJSONObject = ((JSONObject)localJSONArray.get(i)).getString("type");
      if (paramJSONObject == null) {
        break label322;
      }
      localObject = com.igexin.push.extension.a.a().c().iterator();
      do
      {
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
      } while (!((IPushExtension)((Iterator)localObject).next()).isActionSupported(paramJSONObject));
      j = 1;
      label94:
      if ((j != 0) || (b.get(paramJSONObject) != null)) {
        break label322;
      }
      return false;
    }
    catch (Throwable paramJSONObject)
    {
      JSONArray localJSONArray;
      Object localObject;
      label113:
      JSONObject localJSONObject;
      String str;
      Iterator localIterator;
      com.igexin.a.a.c.a.b("CoreAction|" + paramJSONObject.toString());
      paramPushTaskBean.setActionChains(localArrayList);
      return true;
    }
    if (i < localJSONArray.length())
    {
      localJSONObject = (JSONObject)localJSONArray.get(i);
      str = localJSONObject.getString("type");
      if (str != null)
      {
        localObject = com.igexin.push.extension.a.a().c();
        paramJSONObject = null;
        localIterator = ((List)localObject).iterator();
        while (localIterator.hasNext())
        {
          localObject = ((IPushExtension)localIterator.next()).parseAction(localJSONObject);
          paramJSONObject = (JSONObject)localObject;
          if (localObject != null) {
            paramJSONObject = (JSONObject)localObject;
          }
        }
        if (paramJSONObject != null) {
          break label313;
        }
        localObject = (com.igexin.push.core.a.a.a)b.get(str);
        if (localObject == null) {
          break label313;
        }
        localObject = ((com.igexin.push.core.a.a.a)localObject).a(localJSONObject);
        paramJSONObject = (JSONObject)localObject;
        if (localObject == null) {
          break label334;
        }
        ((BaseAction)localObject).setSupportExt(false);
        paramJSONObject = (JSONObject)localObject;
      }
    }
    label313:
    label322:
    label329:
    label334:
    while (paramJSONObject != null)
    {
      localArrayList.add(paramJSONObject);
      i += 1;
      break label113;
      continue;
      j = 0;
      break label94;
      i += 1;
      break;
      i = 0;
      break label113;
    }
    return false;
  }
  
  /* Error */
  public boolean a(JSONObject paramJSONObject, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 919
    //   4: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   7: ifeq +672 -> 679
    //   10: aload_1
    //   11: ldc_w 919
    //   14: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   17: ldc_w 1472
    //   20: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +656 -> 679
    //   26: aload_1
    //   27: ldc -4
    //   29: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   32: astore 9
    //   34: aload_1
    //   35: ldc_w 258
    //   38: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   41: astore 5
    //   43: aload_1
    //   44: ldc_w 260
    //   47: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   50: astore 6
    //   52: aload_1
    //   53: ldc_w 262
    //   56: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 7
    //   61: aload_1
    //   62: ldc_w 264
    //   65: invokevirtual 256	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   68: astore 10
    //   70: aload_1
    //   71: ldc_w 1436
    //   74: invokevirtual 689	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   77: astore 11
    //   79: new 159	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   86: ldc_w 1474
    //   89: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload 7
    //   94: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: ldc_w 829
    //   100: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload 6
    //   105: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: ldc_w 829
    //   111: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload 5
    //   116: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc_w 829
    //   122: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: iload_3
    //   126: invokevirtual 169	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   129: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   135: aload 5
    //   137: ifnull +789 -> 926
    //   140: aload 9
    //   142: ifnull +784 -> 926
    //   145: aload 6
    //   147: ifnull +779 -> 926
    //   150: aload 7
    //   152: ifnull +774 -> 926
    //   155: aload 11
    //   157: ifnull +769 -> 926
    //   160: aload 5
    //   162: getstatic 898	com/igexin/push/core/g:a	Ljava/lang/String;
    //   165: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   168: ifeq +758 -> 926
    //   171: new 272	com/igexin/push/core/bean/PushTaskBean
    //   174: dup
    //   175: invokespecial 273	com/igexin/push/core/bean/PushTaskBean:<init>	()V
    //   178: astore 8
    //   180: aload 8
    //   182: aload 5
    //   184: invokevirtual 276	com/igexin/push/core/bean/PushTaskBean:setAppid	(Ljava/lang/String;)V
    //   187: aload 8
    //   189: aload 6
    //   191: invokevirtual 279	com/igexin/push/core/bean/PushTaskBean:setMessageId	(Ljava/lang/String;)V
    //   194: aload 8
    //   196: aload 7
    //   198: invokevirtual 282	com/igexin/push/core/bean/PushTaskBean:setTaskId	(Ljava/lang/String;)V
    //   201: aload 8
    //   203: aload 9
    //   205: invokevirtual 285	com/igexin/push/core/bean/PushTaskBean:setId	(Ljava/lang/String;)V
    //   208: aload 8
    //   210: aload 10
    //   212: invokevirtual 288	com/igexin/push/core/bean/PushTaskBean:setAppKey	(Ljava/lang/String;)V
    //   215: aload 8
    //   217: iconst_1
    //   218: invokevirtual 292	com/igexin/push/core/bean/PushTaskBean:setCurrentActionid	(I)V
    //   221: aload_1
    //   222: ldc_w 301
    //   225: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   228: ifeq +15 -> 243
    //   231: aload 8
    //   233: aload_1
    //   234: ldc_w 301
    //   237: invokevirtual 308	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   240: invokevirtual 311	com/igexin/push/core/bean/PushTaskBean:setCDNType	(Z)V
    //   243: invokestatic 267	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   246: aload 7
    //   248: aload 6
    //   250: invokevirtual 270	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   253: astore 9
    //   255: iload_3
    //   256: ifeq +27 -> 283
    //   259: invokestatic 267	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   262: aload 8
    //   264: ldc -38
    //   266: invokevirtual 1050	com/igexin/push/core/a/e:a	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   269: invokestatic 267	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   272: invokestatic 539	java/lang/System:currentTimeMillis	()J
    //   275: invokevirtual 1476	com/igexin/push/core/a/e:a	(J)Z
    //   278: ifeq +5 -> 283
    //   281: iconst_1
    //   282: ireturn
    //   283: new 328	android/content/ContentValues
    //   286: dup
    //   287: invokespecial 329	android/content/ContentValues:<init>	()V
    //   290: astore 10
    //   292: aload 10
    //   294: ldc_w 260
    //   297: aload 6
    //   299: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: aload 10
    //   304: ldc_w 262
    //   307: aload 7
    //   309: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   312: aload 10
    //   314: ldc_w 258
    //   317: aload 5
    //   319: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   322: aload 10
    //   324: ldc_w 532
    //   327: new 159	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   334: ldc_w 1478
    //   337: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: aload 9
    //   342: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   348: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   351: aload 10
    //   353: ldc -26
    //   355: aload_1
    //   356: invokevirtual 804	org/json/JSONObject:toString	()Ljava/lang/String;
    //   359: invokevirtual 633	java/lang/String:getBytes	()[B
    //   362: invokestatic 1480	com/igexin/a/b/a:b	([B)[B
    //   365: invokevirtual 1482	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   368: aload 10
    //   370: ldc_w 534
    //   373: invokestatic 539	java/lang/System:currentTimeMillis	()J
    //   376: invokestatic 544	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   379: invokevirtual 547	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   382: aload_2
    //   383: ifnull +18 -> 401
    //   386: aload 10
    //   388: ldc_w 1484
    //   391: aload_2
    //   392: invokevirtual 1482	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   395: aload 8
    //   397: aload_2
    //   398: invokevirtual 1487	com/igexin/push/core/bean/PushTaskBean:setMsgExtra	([B)V
    //   401: aload 11
    //   403: invokevirtual 692	org/json/JSONArray:length	()I
    //   406: ifle +21 -> 427
    //   409: invokestatic 267	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   412: aload_1
    //   413: aload 8
    //   415: invokevirtual 1489	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)Z
    //   418: istore 4
    //   420: iload 4
    //   422: ifne +5 -> 427
    //   425: iconst_1
    //   426: ireturn
    //   427: iload_3
    //   428: ifeq +457 -> 885
    //   431: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   434: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   437: ldc -44
    //   439: iconst_1
    //   440: anewarray 214	java/lang/String
    //   443: dup
    //   444: iconst_0
    //   445: ldc_w 262
    //   448: aastore
    //   449: iconst_1
    //   450: anewarray 214	java/lang/String
    //   453: dup
    //   454: iconst_0
    //   455: aload 7
    //   457: aastore
    //   458: aconst_null
    //   459: aconst_null
    //   460: invokevirtual 223	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   463: astore 5
    //   465: aload 5
    //   467: ifnull +200 -> 667
    //   470: aload 5
    //   472: astore_2
    //   473: new 159	java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   480: ldc_w 1491
    //   483: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: aload 7
    //   488: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: ldc_w 1493
    //   494: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   497: aload 5
    //   499: invokeinterface 550 1 0
    //   504: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   507: invokevirtual 1494	java/lang/Integer:toString	()Ljava/lang/String;
    //   510: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   513: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   516: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   519: aload 5
    //   521: astore_2
    //   522: aload 5
    //   524: invokeinterface 550 1 0
    //   529: ifne +342 -> 871
    //   532: aload 5
    //   534: astore_2
    //   535: aload_1
    //   536: ldc_w 313
    //   539: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   542: ifeq +139 -> 681
    //   545: aload 5
    //   547: astore_2
    //   548: aload_0
    //   549: aload_1
    //   550: aload 8
    //   552: invokespecial 316	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   555: aload 5
    //   557: astore_2
    //   558: aload 8
    //   560: getstatic 1496	com/igexin/push/core/a:k	I
    //   563: invokevirtual 299	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   566: aload 5
    //   568: astore_2
    //   569: aload 10
    //   571: ldc -40
    //   573: getstatic 1496	com/igexin/push/core/a:k	I
    //   576: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   579: invokevirtual 332	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   582: aload 5
    //   584: astore_2
    //   585: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   588: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   591: ldc -44
    //   593: aload 10
    //   595: invokevirtual 553	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   598: aload 5
    //   600: astore_2
    //   601: getstatic 200	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   604: aload 9
    //   606: aload 8
    //   608: invokeinterface 35 3 0
    //   613: pop
    //   614: aload 5
    //   616: astore_2
    //   617: new 159	java/lang/StringBuilder
    //   620: dup
    //   621: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   624: ldc_w 1498
    //   627: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: getstatic 200	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   633: invokeinterface 322 1 0
    //   638: invokevirtual 869	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   641: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   644: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   647: aload 5
    //   649: astore_2
    //   650: aload_1
    //   651: ldc_w 313
    //   654: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   657: ifeq +129 -> 786
    //   660: aload 5
    //   662: astore_2
    //   663: aload_0
    //   664: invokevirtual 193	com/igexin/push/core/a/e:t	()V
    //   667: aload 5
    //   669: ifnull +10 -> 679
    //   672: aload 5
    //   674: invokeinterface 319 1 0
    //   679: iconst_1
    //   680: ireturn
    //   681: aload 5
    //   683: astore_2
    //   684: aload 8
    //   686: getstatic 444	com/igexin/push/core/a:l	I
    //   689: invokevirtual 299	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   692: aload 5
    //   694: astore_2
    //   695: aload 10
    //   697: ldc -40
    //   699: getstatic 444	com/igexin/push/core/a:l	I
    //   702: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   705: invokevirtual 332	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   708: goto -126 -> 582
    //   711: astore_1
    //   712: aload 5
    //   714: astore_2
    //   715: new 159	java/lang/StringBuilder
    //   718: dup
    //   719: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   722: ldc_w 1465
    //   725: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   728: aload_1
    //   729: invokevirtual 1357	java/lang/Exception:toString	()Ljava/lang/String;
    //   732: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   735: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   738: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   741: aload 5
    //   743: ifnull -64 -> 679
    //   746: aload 5
    //   748: invokeinterface 319 1 0
    //   753: goto -74 -> 679
    //   756: astore_1
    //   757: new 159	java/lang/StringBuilder
    //   760: dup
    //   761: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   764: ldc_w 1465
    //   767: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: aload_1
    //   771: invokevirtual 1357	java/lang/Exception:toString	()Ljava/lang/String;
    //   774: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   777: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   780: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   783: goto -104 -> 679
    //   786: aload 5
    //   788: astore_2
    //   789: invokestatic 267	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   792: aload 7
    //   794: aload 6
    //   796: getstatic 898	com/igexin/push/core/g:a	Ljava/lang/String;
    //   799: getstatic 555	com/igexin/push/core/g:e	Ljava/lang/String;
    //   802: invokevirtual 1500	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   805: aload 5
    //   807: astore_2
    //   808: new 159	java/lang/StringBuilder
    //   811: dup
    //   812: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   815: ldc_w 1502
    //   818: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: aload 7
    //   823: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   826: ldc_w 1504
    //   829: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   832: aload 6
    //   834: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: ldc_w 1506
    //   840: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: getstatic 555	com/igexin/push/core/g:e	Ljava/lang/String;
    //   846: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   852: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   855: goto -188 -> 667
    //   858: astore_1
    //   859: aload_2
    //   860: ifnull +9 -> 869
    //   863: aload_2
    //   864: invokeinterface 319 1 0
    //   869: aload_1
    //   870: athrow
    //   871: aload 5
    //   873: ifnull +75 -> 948
    //   876: aload 5
    //   878: invokeinterface 319 1 0
    //   883: iconst_1
    //   884: ireturn
    //   885: aload_1
    //   886: ldc_w 313
    //   889: invokevirtual 305	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   892: ifeq +10 -> 902
    //   895: aload_0
    //   896: aload_1
    //   897: aload 8
    //   899: invokespecial 316	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   902: aload 8
    //   904: getstatic 444	com/igexin/push/core/a:l	I
    //   907: invokevirtual 299	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   910: getstatic 200	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   913: aload 9
    //   915: aload 8
    //   917: invokeinterface 35 3 0
    //   922: pop
    //   923: goto -244 -> 679
    //   926: ldc_w 1508
    //   929: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   932: goto -253 -> 679
    //   935: astore_1
    //   936: aconst_null
    //   937: astore_2
    //   938: goto -79 -> 859
    //   941: astore_1
    //   942: aconst_null
    //   943: astore 5
    //   945: goto -233 -> 712
    //   948: iconst_1
    //   949: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	950	0	this	e
    //   0	950	1	paramJSONObject	JSONObject
    //   0	950	2	paramArrayOfByte	byte[]
    //   0	950	3	paramBoolean	boolean
    //   418	3	4	bool	boolean
    //   41	903	5	localObject1	Object
    //   50	783	6	str1	String
    //   59	763	7	str2	String
    //   178	738	8	localPushTaskBean	PushTaskBean
    //   32	882	9	str3	String
    //   68	628	10	localObject2	Object
    //   77	325	11	localJSONArray	JSONArray
    // Exception table:
    //   from	to	target	type
    //   473	519	711	java/lang/Exception
    //   522	532	711	java/lang/Exception
    //   535	545	711	java/lang/Exception
    //   548	555	711	java/lang/Exception
    //   558	566	711	java/lang/Exception
    //   569	582	711	java/lang/Exception
    //   585	598	711	java/lang/Exception
    //   601	614	711	java/lang/Exception
    //   617	647	711	java/lang/Exception
    //   650	660	711	java/lang/Exception
    //   663	667	711	java/lang/Exception
    //   684	692	711	java/lang/Exception
    //   695	708	711	java/lang/Exception
    //   789	805	711	java/lang/Exception
    //   808	855	711	java/lang/Exception
    //   0	135	756	java/lang/Exception
    //   160	243	756	java/lang/Exception
    //   243	255	756	java/lang/Exception
    //   259	281	756	java/lang/Exception
    //   283	382	756	java/lang/Exception
    //   386	401	756	java/lang/Exception
    //   401	420	756	java/lang/Exception
    //   672	679	756	java/lang/Exception
    //   746	753	756	java/lang/Exception
    //   863	869	756	java/lang/Exception
    //   869	871	756	java/lang/Exception
    //   876	883	756	java/lang/Exception
    //   885	902	756	java/lang/Exception
    //   902	923	756	java/lang/Exception
    //   926	932	756	java/lang/Exception
    //   473	519	858	finally
    //   522	532	858	finally
    //   535	545	858	finally
    //   548	555	858	finally
    //   558	566	858	finally
    //   569	582	858	finally
    //   585	598	858	finally
    //   601	614	858	finally
    //   617	647	858	finally
    //   650	660	858	finally
    //   663	667	858	finally
    //   684	692	858	finally
    //   695	708	858	finally
    //   715	741	858	finally
    //   789	805	858	finally
    //   808	855	858	finally
    //   431	465	935	finally
    //   431	465	941	java/lang/Exception
  }
  
  public com.igexin.push.core.b b(String paramString1, String paramString2)
  {
    Object localObject1 = com.igexin.push.core.b.a;
    paramString2 = paramString1 + ":" + paramString2;
    PushTaskBean localPushTaskBean = (PushTaskBean)com.igexin.push.core.g.ai.get(paramString2);
    if (localPushTaskBean == null)
    {
      localObject1 = com.igexin.push.core.b.c;
      return localObject1;
    }
    Iterator localIterator1 = localPushTaskBean.getActionChains().iterator();
    int i = 0;
    paramString2 = (String)localObject1;
    BaseAction localBaseAction;
    Object localObject2;
    if (localIterator1.hasNext())
    {
      localBaseAction = (BaseAction)localIterator1.next();
      localObject1 = com.igexin.push.core.b.c;
      if (localBaseAction == null) {
        return localObject1;
      }
      Iterator localIterator2 = com.igexin.push.extension.a.a().c().iterator();
      while (localIterator2.hasNext())
      {
        localObject2 = ((IPushExtension)localIterator2.next()).prepareExecuteAction(localPushTaskBean, localBaseAction);
        localObject1 = localObject2;
        if (localObject2 != com.igexin.push.core.b.c) {
          localObject1 = localObject2;
        }
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      if (localObject1 == com.igexin.push.core.b.c)
      {
        localObject2 = (com.igexin.push.core.a.a.a)b.get(localBaseAction.getType());
        if (localObject2 == null) {
          break;
        }
        localObject2 = ((com.igexin.push.core.a.a.a)localObject2).a(localPushTaskBean, localBaseAction);
        localObject1 = localObject2;
        if (localObject2 == com.igexin.push.core.b.c) {
          break;
        }
      }
      if (paramString2 == com.igexin.push.core.b.a) {
        paramString2 = (String)localObject2;
      }
      for (;;)
      {
        if (localObject2 == com.igexin.push.core.b.b) {
          i += 1;
        }
        for (;;)
        {
          break;
          localObject1 = paramString2;
          if (i != 0)
          {
            localObject1 = paramString2;
            if (!com.igexin.push.core.g.a(paramString1, Integer.valueOf(i), true)) {
              localObject1 = com.igexin.push.core.b.a;
            }
          }
          return localObject1;
        }
      }
    }
  }
  
  public void b()
  {
    d();
  }
  
  public void b(int paramInt, String paramString)
  {
    com.igexin.push.config.l.e = paramInt;
    com.igexin.push.config.a.a().d();
  }
  
  public void b(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.hasExtra("isSlave")) && (paramIntent.getBooleanExtra("isSlave", false)))
    {
      com.igexin.push.core.f.a().a(true);
      if (!paramIntent.hasExtra("op_app")) {
        break label64;
      }
    }
    label64:
    for (com.igexin.push.core.g.C = paramIntent.getStringExtra("op_app");; com.igexin.push.core.g.C = "")
    {
      if (com.igexin.push.core.g.m) {
        l();
      }
      return;
    }
  }
  
  public void b(PushTaskBean paramPushTaskBean, String paramString)
  {
    String str = paramPushTaskBean.getMessageId() + "|" + paramString;
    com.igexin.push.c.c.c localC;
    if (com.igexin.push.core.g.al.containsKey(str))
    {
      localC = (com.igexin.push.c.c.c)com.igexin.push.core.g.al.get(str);
      if (localC.c() < 2)
      {
        com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localC);
        localC.a(localC.c() + 1);
        a(localC, paramPushTaskBean, paramString, str);
      }
    }
    for (;;)
    {
      com.igexin.a.a.c.a.b("cdnfeedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString);
      return;
      localC = new com.igexin.push.c.c.c();
      long l = System.currentTimeMillis();
      localC.a();
      localC.c = ("FDB" + paramPushTaskBean.getMessageId() + "|" + paramPushTaskBean.getTaskId() + "|" + paramString + "|" + "ok" + "|" + l);
      localC.d = com.igexin.push.core.g.s;
      localC.a = ((int)l);
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localC);
      a(localC, paramPushTaskBean, paramString, str);
    }
  }
  
  public void b(String paramString)
  {
    if (com.igexin.push.core.g.s != null) {}
    try
    {
      long l = System.currentTimeMillis();
      paramString = URLEncoder.encode(paramString, "utf-8");
      paramString = "{\"action\":\"set_tag\",\"id\":\"" + l + "\", \"cid\":\"" + com.igexin.push.core.g.s + "\", \"appid\":\"" + com.igexin.push.core.g.a + "\", \"tags\":\"" + paramString + "\"}";
      Object localObject = com.igexin.push.core.c.c.a();
      if (localObject != null) {
        ((com.igexin.push.core.c.c)localObject).a(new com.igexin.push.core.bean.i(l, paramString, (byte)2, l));
      }
      localObject = new com.igexin.push.c.c.d();
      ((com.igexin.push.c.c.d)localObject).a();
      ((com.igexin.push.c.c.d)localObject).d = "17258000";
      ((com.igexin.push.c.c.d)localObject).e = paramString;
      com.igexin.a.a.b.d.c().a(SDKUrlConfig.getCmAddress(), 3, com.igexin.push.core.f.a().f(), localObject, false);
      com.igexin.a.a.c.a.b("settag");
      return;
    }
    catch (Exception paramString) {}
  }
  
  /* Error */
  public boolean b(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 159	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   7: aload_1
    //   8: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   11: ldc_w 814
    //   14: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: aload_2
    //   18: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 5
    //   26: getstatic 200	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   29: aload 5
    //   31: invokeinterface 439 2 0
    //   36: checkcast 272	com/igexin/push/core/bean/PushTaskBean
    //   39: astore 5
    //   41: aload 5
    //   43: astore 6
    //   45: aload 5
    //   47: ifnonnull +290 -> 337
    //   50: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   53: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   56: ldc -44
    //   58: iconst_2
    //   59: anewarray 214	java/lang/String
    //   62: dup
    //   63: iconst_0
    //   64: ldc_w 262
    //   67: aastore
    //   68: dup
    //   69: iconst_1
    //   70: ldc_w 260
    //   73: aastore
    //   74: iconst_2
    //   75: anewarray 214	java/lang/String
    //   78: dup
    //   79: iconst_0
    //   80: aload_1
    //   81: aastore
    //   82: dup
    //   83: iconst_1
    //   84: aload_2
    //   85: aastore
    //   86: aconst_null
    //   87: aconst_null
    //   88: invokevirtual 223	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   91: astore 7
    //   93: aload 7
    //   95: ifnull +208 -> 303
    //   98: aload 7
    //   100: astore 6
    //   102: aload 5
    //   104: astore 8
    //   106: aload 7
    //   108: invokeinterface 550 1 0
    //   113: ifle +176 -> 289
    //   116: aload 7
    //   118: astore 6
    //   120: aload 5
    //   122: astore 8
    //   124: aload 7
    //   126: invokeinterface 228 1 0
    //   131: ifeq +186 -> 317
    //   134: aload 7
    //   136: astore 6
    //   138: aload 5
    //   140: astore 8
    //   142: aload 7
    //   144: aload 7
    //   146: ldc -26
    //   148: invokeinterface 846 2 0
    //   153: invokeinterface 238 2 0
    //   158: astore 9
    //   160: aload 7
    //   162: astore 6
    //   164: aload 5
    //   166: astore 8
    //   168: aload 7
    //   170: aload 7
    //   172: ldc_w 1484
    //   175: invokeinterface 846 2 0
    //   180: invokeinterface 238 2 0
    //   185: astore 10
    //   187: aload 7
    //   189: astore 6
    //   191: aload 5
    //   193: astore 8
    //   195: aload_0
    //   196: new 240	org/json/JSONObject
    //   199: dup
    //   200: new 214	java/lang/String
    //   203: dup
    //   204: aload 9
    //   206: invokestatic 245	com/igexin/a/b/a:c	([B)[B
    //   209: invokespecial 248	java/lang/String:<init>	([B)V
    //   212: invokespecial 250	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   215: aload 10
    //   217: iconst_0
    //   218: invokevirtual 1576	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;[BZ)Z
    //   221: pop
    //   222: aload 7
    //   224: astore 6
    //   226: aload 5
    //   228: astore 8
    //   230: getstatic 200	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   233: new 159	java/lang/StringBuilder
    //   236: dup
    //   237: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   240: aload_1
    //   241: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: ldc_w 814
    //   247: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: aload_2
    //   251: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   254: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   257: invokeinterface 439 2 0
    //   262: checkcast 272	com/igexin/push/core/bean/PushTaskBean
    //   265: astore 5
    //   267: aload 5
    //   269: ifnonnull +17 -> 286
    //   272: aload 7
    //   274: ifnull +10 -> 284
    //   277: aload 7
    //   279: invokeinterface 319 1 0
    //   284: iconst_0
    //   285: ireturn
    //   286: goto -170 -> 116
    //   289: aload 7
    //   291: ifnull +10 -> 301
    //   294: aload 7
    //   296: invokeinterface 319 1 0
    //   301: iconst_0
    //   302: ireturn
    //   303: aload 7
    //   305: ifnull +10 -> 315
    //   308: aload 7
    //   310: invokeinterface 319 1 0
    //   315: iconst_0
    //   316: ireturn
    //   317: aload 5
    //   319: astore 6
    //   321: aload 7
    //   323: ifnull +14 -> 337
    //   326: aload 7
    //   328: invokeinterface 319 1 0
    //   333: aload 5
    //   335: astore 6
    //   337: invokestatic 267	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   340: aload 6
    //   342: aload_3
    //   343: invokevirtual 1050	com/igexin/push/core/a/e:a	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   346: aload 6
    //   348: aload_3
    //   349: invokevirtual 1580	com/igexin/push/core/bean/PushTaskBean:getBaseAction	(Ljava/lang/String;)Lcom/igexin/push/core/bean/BaseAction;
    //   352: astore_1
    //   353: aload_1
    //   354: ifnonnull +80 -> 434
    //   357: iconst_0
    //   358: ireturn
    //   359: astore_1
    //   360: aconst_null
    //   361: astore 7
    //   363: aload 7
    //   365: astore 6
    //   367: new 159	java/lang/StringBuilder
    //   370: dup
    //   371: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   374: ldc_w 1465
    //   377: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: aload_1
    //   381: invokevirtual 1466	java/lang/Throwable:toString	()Ljava/lang/String;
    //   384: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   390: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   393: aload 5
    //   395: astore 6
    //   397: aload 7
    //   399: ifnull -62 -> 337
    //   402: aload 7
    //   404: invokeinterface 319 1 0
    //   409: aload 5
    //   411: astore 6
    //   413: goto -76 -> 337
    //   416: astore_1
    //   417: aconst_null
    //   418: astore 6
    //   420: aload 6
    //   422: ifnull +10 -> 432
    //   425: aload 6
    //   427: invokeinterface 319 1 0
    //   432: aload_1
    //   433: athrow
    //   434: aload_1
    //   435: invokevirtual 1583	com/igexin/push/core/bean/BaseAction:isSupportExt	()Z
    //   438: ifeq +78 -> 516
    //   441: invokestatic 1441	com/igexin/push/extension/a:a	()Lcom/igexin/push/extension/a;
    //   444: invokevirtual 1444	com/igexin/push/extension/a:c	()Ljava/util/List;
    //   447: invokeinterface 1445 1 0
    //   452: astore_2
    //   453: aload_2
    //   454: invokeinterface 767 1 0
    //   459: ifeq +57 -> 516
    //   462: aload_2
    //   463: invokeinterface 771 1 0
    //   468: checkcast 1447	com/igexin/push/extension/stub/IPushExtension
    //   471: aload 6
    //   473: aload_1
    //   474: invokeinterface 1587 3 0
    //   479: ifeq -26 -> 453
    //   482: new 159	java/lang/StringBuilder
    //   485: dup
    //   486: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   489: ldc_w 1589
    //   492: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: aload_1
    //   496: invokevirtual 1592	java/lang/Object:toString	()Ljava/lang/String;
    //   499: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: ldc_w 1594
    //   505: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   508: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   514: iconst_1
    //   515: ireturn
    //   516: getstatic 52	com/igexin/push/core/a/e:b	Ljava/util/Map;
    //   519: aload_1
    //   520: invokevirtual 1520	com/igexin/push/core/bean/BaseAction:getType	()Ljava/lang/String;
    //   523: invokeinterface 439 2 0
    //   528: checkcast 1456	com/igexin/push/core/a/a/a
    //   531: astore_2
    //   532: aload_2
    //   533: ifnull +69 -> 602
    //   536: aload 6
    //   538: invokevirtual 1597	com/igexin/push/core/bean/PushTaskBean:isStop	()Z
    //   541: ifeq +6 -> 547
    //   544: goto +58 -> 602
    //   547: aload_2
    //   548: aload 6
    //   550: aload_1
    //   551: invokeinterface 1599 3 0
    //   556: istore 4
    //   558: iload 4
    //   560: ireturn
    //   561: astore_1
    //   562: new 159	java/lang/StringBuilder
    //   565: dup
    //   566: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   569: ldc_w 1465
    //   572: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: aload_1
    //   576: invokevirtual 1466	java/lang/Throwable:toString	()Ljava/lang/String;
    //   579: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   582: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   585: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   588: iconst_0
    //   589: ireturn
    //   590: astore_1
    //   591: goto -171 -> 420
    //   594: astore_1
    //   595: aload 8
    //   597: astore 5
    //   599: goto -236 -> 363
    //   602: iconst_0
    //   603: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	604	0	this	e
    //   0	604	1	paramString1	String
    //   0	604	2	paramString2	String
    //   0	604	3	paramString3	String
    //   556	3	4	bool	boolean
    //   24	574	5	localObject1	Object
    //   43	506	6	localObject2	Object
    //   91	312	7	localCursor	Cursor
    //   104	492	8	localObject3	Object
    //   158	47	9	arrayOfByte1	byte[]
    //   185	31	10	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   50	93	359	java/lang/Throwable
    //   50	93	416	finally
    //   346	353	561	java/lang/Throwable
    //   434	453	561	java/lang/Throwable
    //   453	514	561	java/lang/Throwable
    //   516	532	561	java/lang/Throwable
    //   536	544	561	java/lang/Throwable
    //   547	558	561	java/lang/Throwable
    //   106	116	590	finally
    //   124	134	590	finally
    //   142	160	590	finally
    //   168	187	590	finally
    //   195	222	590	finally
    //   230	267	590	finally
    //   367	393	590	finally
    //   106	116	594	java/lang/Throwable
    //   124	134	594	java/lang/Throwable
    //   142	160	594	java/lang/Throwable
    //   168	187	594	java/lang/Throwable
    //   195	222	594	java/lang/Throwable
    //   230	267	594	java/lang/Throwable
  }
  
  public boolean b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    com.igexin.a.a.c.a.b("startapp|broadcastPayload");
    Intent localIntent = new Intent();
    localIntent.addFlags(32);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10001);
    localBundle.putString("taskid", paramString1);
    localBundle.putString("messageid", paramString2);
    localBundle.putString("appid", paramString3);
    localBundle.putString("payloadid", paramString2 + ":" + paramString1);
    localBundle.putString("packagename", com.igexin.push.core.g.e);
    localIntent.setAction("com.igexin.sdk.action." + paramString3);
    if (paramString4 != null) {
      paramString1 = paramString4.getBytes();
    }
    for (;;)
    {
      if (paramString1 != null) {
        com.igexin.a.a.c.a.b("startapp|broadcast|payload is " + new String(paramString1));
      }
      for (;;)
      {
        localBundle.putByteArray("payload", paramString1);
        localIntent.putExtras(localBundle);
        if (paramString1 != null) {}
        try
        {
          com.igexin.a.a.c.a.b("startapp|broadcast|" + paramString3 + "|" + new String(paramString1, "utf-8"));
          com.igexin.push.core.g.g.sendBroadcast(localIntent);
          return true;
        }
        catch (Exception paramString1)
        {
          com.igexin.a.a.c.a.b("startapp|broadcast|error|" + paramString1.toString());
          return false;
        }
        paramString1 = a(paramString1, paramString2);
        paramString1 = (PushTaskBean)com.igexin.push.core.g.ai.get(paramString1);
        if (paramString1 == null) {
          break label328;
        }
        paramString1 = paramString1.getMsgExtra();
        break;
        com.igexin.a.a.c.a.b("startapp|broadcast|payload is empty!");
      }
      label328:
      paramString1 = null;
    }
  }
  
  public com.igexin.push.c.c.i c()
  {
    com.igexin.push.c.c.i localI = new com.igexin.push.c.c.i();
    localI.a = com.igexin.push.core.g.r;
    localI.b = 0;
    localI.c = 65280;
    localI.d = com.igexin.push.core.g.a;
    try
    {
      if (com.igexin.push.f.a.a())
      {
        ArrayList localArrayList = new ArrayList();
        Object localObject1 = (WifiManager)com.igexin.push.core.g.g.getSystemService("wifi");
        if ((localObject1 != null) && (((WifiManager)localObject1).isWifiEnabled()))
        {
          Object localObject2 = ((WifiManager)localObject1).getConnectionInfo();
          if (localObject2 != null)
          {
            localObject1 = ((WifiInfo)localObject2).getSSID();
            localObject2 = ((WifiInfo)localObject2).getBSSID();
            if (localObject1 != null)
            {
              com.igexin.push.c.c.j localJ = new com.igexin.push.c.c.j();
              localJ.a = 1;
              localJ.b = localObject1;
              localArrayList.add(localJ);
            }
            if (localObject2 != null)
            {
              localObject1 = new com.igexin.push.c.c.j();
              ((com.igexin.push.c.c.j)localObject1).a = 4;
              ((com.igexin.push.c.c.j)localObject1).b = localObject2;
              localArrayList.add(localObject1);
            }
          }
        }
        if (localArrayList.size() > 0) {
          localI.e = localArrayList;
        }
      }
      return localI;
    }
    catch (Exception localException) {}
    return localI;
  }
  
  public void c(Intent paramIntent)
  {
    if ((paramIntent == null) || (paramIntent.getAction() == null)) {}
    String str;
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return;
            try
            {
              str = paramIntent.getAction();
              if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str))
              {
                if (com.igexin.a.a.b.d.c() == null) {
                  continue;
                }
                C();
              }
            }
            catch (Exception paramIntent)
            {
              com.igexin.a.a.c.a.b("CoreAction" + paramIntent.toString());
              return;
            }
          }
          if (("com.igexin.sdk.action.snlrefresh".equals(str)) || (com.igexin.push.core.g.W.equals(str)) || ("com.igexin.sdk.action.snlretire".equals(str)))
          {
            com.igexin.push.core.f.a().h().a(paramIntent);
            return;
          }
          if ("com.igexin.sdk.action.execute".equals(str))
          {
            f(paramIntent);
            return;
          }
          if ("com.igexin.sdk.action.doaction".equals(str))
          {
            e(paramIntent);
            return;
          }
          if (!"android.intent.action.TIME_SET".equals(str)) {
            break;
          }
        } while (com.igexin.push.config.l.b == 0);
        com.igexin.push.a.a.c.c().d();
        return;
        if (!"android.intent.action.SCREEN_ON".equals(str)) {
          break;
        }
        com.igexin.push.core.g.q = 1;
      } while (!u());
      t();
      return;
      if ("android.intent.action.SCREEN_OFF".equals(str))
      {
        com.igexin.push.core.g.q = 0;
        return;
      }
      if ("android.intent.action.PACKAGE_ADDED".equals(str))
      {
        g(paramIntent.getDataString());
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(str))
      {
        f(paramIntent.getDataString());
        return;
      }
      if ("com.igexin.sdk.action.core.clearmsg".equals(str))
      {
        com.igexin.push.core.f.a().k().a("message", null);
        return;
      }
    } while ((!"android.net.wifi.WIFI_STATE_CHANGED".equals(str)) || (paramIntent.getIntExtra("wifi_state", 0) != 3));
    com.igexin.push.core.f.a().d();
  }
  
  public void c(String paramString)
  {
    long l = System.currentTimeMillis();
    if (l - com.igexin.push.core.g.T > 5000L)
    {
      String str = new SimpleDateFormat("yyyy-MM-dd").format(new Date(l));
      if (!str.equals(com.igexin.push.core.g.S))
      {
        com.igexin.push.core.c.f.a().d(str);
        com.igexin.push.core.c.f.a().a(0);
      }
      com.igexin.a.a.c.a.b("-> CoreRuntimeInfo.opAliasTimes:" + com.igexin.push.core.g.U);
      if (com.igexin.push.core.g.U < 100)
      {
        com.igexin.a.a.c.a.b("requestService bindAlias HttpTask ...");
        com.igexin.push.core.g.T = l;
        com.igexin.push.core.c.f.a().a(com.igexin.push.core.g.U + 1);
        paramString = new com.igexin.push.e.a.c(new com.igexin.push.core.d.b(SDKUrlConfig.getAmpServiceUrl(), paramString));
        com.igexin.a.a.b.d.c().a(paramString, false, true);
      }
    }
  }
  
  /* Error */
  public int d()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: getstatic 900	com/igexin/push/core/g:j	Z
    //   5: ifeq +25 -> 30
    //   8: getstatic 1398	com/igexin/push/core/g:k	Z
    //   11: ifeq +19 -> 30
    //   14: aload_0
    //   15: invokestatic 539	java/lang/System:currentTimeMillis	()J
    //   18: invokevirtual 1476	com/igexin/push/core/a/e:a	(J)Z
    //   21: ifne +9 -> 30
    //   24: invokestatic 1736	com/igexin/push/f/a:b	()Z
    //   27: ifne +7 -> 34
    //   30: iconst_m1
    //   31: istore_1
    //   32: iload_1
    //   33: ireturn
    //   34: getstatic 1737	com/igexin/push/core/g:l	Z
    //   37: ifeq +43 -> 80
    //   40: getstatic 1737	com/igexin/push/core/g:l	Z
    //   43: ifne +308 -> 351
    //   46: iconst_1
    //   47: istore_2
    //   48: iload_2
    //   49: putstatic 1737	com/igexin/push/core/g:l	Z
    //   52: new 1739	java/util/Random
    //   55: dup
    //   56: invokespecial 1740	java/util/Random:<init>	()V
    //   59: invokevirtual 1743	java/util/Random:nextInt	()I
    //   62: bipush 24
    //   64: irem
    //   65: invokestatic 1748	java/lang/Math:abs	(I)I
    //   68: i2l
    //   69: ldc2_w 1749
    //   72: lmul
    //   73: invokestatic 539	java/lang/System:currentTimeMillis	()J
    //   76: ladd
    //   77: putstatic 1753	com/igexin/push/core/g:L	J
    //   80: invokestatic 1754	com/igexin/push/core/c/w:b	()V
    //   83: getstatic 1634	com/igexin/push/core/g:r	J
    //   86: lconst_0
    //   87: lcmp
    //   88: ifne +268 -> 356
    //   91: new 159	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   98: ldc_w 1756
    //   101: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   107: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   113: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   116: new 1758	com/igexin/push/c/c/f
    //   119: dup
    //   120: getstatic 1759	com/igexin/push/core/g:u	Ljava/lang/String;
    //   123: getstatic 1760	com/igexin/push/core/g:v	Ljava/lang/String;
    //   126: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   129: getstatic 898	com/igexin/push/core/g:a	Ljava/lang/String;
    //   132: invokespecial 1762	com/igexin/push/c/c/f:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   135: astore_3
    //   136: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   139: invokevirtual 180	com/igexin/push/core/f:g	()Lcom/igexin/push/d/j;
    //   142: new 159	java/lang/StringBuilder
    //   145: dup
    //   146: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   149: ldc_w 1764
    //   152: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   158: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: aload_3
    //   165: iconst_1
    //   166: invokevirtual 1233	com/igexin/push/d/j:a	(Ljava/lang/String;Lcom/igexin/push/c/c/e;Z)I
    //   169: ifge +596 -> 765
    //   172: iconst_0
    //   173: istore_2
    //   174: new 159	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   181: ldc_w 1766
    //   184: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: iload_2
    //   188: invokevirtual 169	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   191: ldc_w 829
    //   194: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: getstatic 831	com/igexin/push/core/g:A	Ljava/lang/String;
    //   200: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   209: iload_2
    //   210: ifne -178 -> 32
    //   213: new 817	java/text/SimpleDateFormat
    //   216: dup
    //   217: ldc_w 1195
    //   220: invokespecial 820	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   223: new 822	java/util/Date
    //   226: dup
    //   227: invokespecial 823	java/util/Date:<init>	()V
    //   230: invokevirtual 827	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   233: astore 4
    //   235: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   238: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   241: ldc_w 837
    //   244: iconst_1
    //   245: anewarray 214	java/lang/String
    //   248: dup
    //   249: iconst_0
    //   250: ldc_w 839
    //   253: aastore
    //   254: iconst_1
    //   255: anewarray 214	java/lang/String
    //   258: dup
    //   259: iconst_0
    //   260: ldc_w 565
    //   263: aastore
    //   264: aconst_null
    //   265: aconst_null
    //   266: invokevirtual 223	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   269: astore_3
    //   270: aload_3
    //   271: ifnull +68 -> 339
    //   274: aload_3
    //   275: invokeinterface 550 1 0
    //   280: ifne +475 -> 755
    //   283: new 328	android/content/ContentValues
    //   286: dup
    //   287: invokespecial 329	android/content/ContentValues:<init>	()V
    //   290: astore 5
    //   292: aload 5
    //   294: ldc_w 850
    //   297: iconst_1
    //   298: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   301: invokevirtual 332	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   304: aload 5
    //   306: ldc_w 860
    //   309: aload 4
    //   311: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   314: aload 5
    //   316: ldc_w 839
    //   319: ldc_w 565
    //   322: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   328: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   331: ldc_w 837
    //   334: aload 5
    //   336: invokevirtual 553	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   339: aload_3
    //   340: ifnull +9 -> 349
    //   343: aload_3
    //   344: invokeinterface 319 1 0
    //   349: iconst_0
    //   350: ireturn
    //   351: iconst_0
    //   352: istore_2
    //   353: goto -305 -> 48
    //   356: aload_0
    //   357: invokevirtual 1768	com/igexin/push/core/a/e:c	()Lcom/igexin/push/c/c/i;
    //   360: astore_3
    //   361: new 159	java/lang/StringBuilder
    //   364: dup
    //   365: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   368: ldc_w 1770
    //   371: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: aload_3
    //   375: getfield 1635	com/igexin/push/c/c/i:a	J
    //   378: invokevirtual 903	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   381: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   387: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   390: invokevirtual 180	com/igexin/push/core/f:g	()Lcom/igexin/push/d/j;
    //   393: new 159	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   400: ldc_w 1772
    //   403: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: getstatic 1634	com/igexin/push/core/g:r	J
    //   409: invokestatic 1226	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   412: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   418: aload_3
    //   419: iconst_1
    //   420: invokevirtual 1233	com/igexin/push/d/j:a	(Ljava/lang/String;Lcom/igexin/push/c/c/e;Z)I
    //   423: ifge +337 -> 760
    //   426: iconst_0
    //   427: istore_2
    //   428: new 159	java/lang/StringBuilder
    //   431: dup
    //   432: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   435: ldc_w 1774
    //   438: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: iload_2
    //   442: invokevirtual 169	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   445: ldc_w 829
    //   448: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   451: getstatic 896	com/igexin/push/core/g:s	Ljava/lang/String;
    //   454: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   457: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   460: invokestatic 130	com/igexin/a/a/c/a:b	(Ljava/lang/String;)V
    //   463: goto -254 -> 209
    //   466: aload_3
    //   467: invokeinterface 228 1 0
    //   472: ifeq -133 -> 339
    //   475: aload_3
    //   476: aload_3
    //   477: ldc_w 860
    //   480: invokeinterface 846 2 0
    //   485: invokeinterface 862 2 0
    //   490: astore 6
    //   492: aload_3
    //   493: aload_3
    //   494: ldc -4
    //   496: invokeinterface 846 2 0
    //   501: invokeinterface 862 2 0
    //   506: astore 5
    //   508: aload 4
    //   510: aload 6
    //   512: invokevirtual 513	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   515: ifeq +92 -> 607
    //   518: aload_3
    //   519: aload_3
    //   520: ldc_w 850
    //   523: invokeinterface 846 2 0
    //   528: invokeinterface 296 2 0
    //   533: istore_1
    //   534: new 328	android/content/ContentValues
    //   537: dup
    //   538: invokespecial 329	android/content/ContentValues:<init>	()V
    //   541: astore 6
    //   543: aload 6
    //   545: ldc_w 850
    //   548: iload_1
    //   549: iconst_1
    //   550: iadd
    //   551: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   554: invokevirtual 332	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   557: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   560: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   563: ldc_w 837
    //   566: aload 6
    //   568: iconst_1
    //   569: anewarray 214	java/lang/String
    //   572: dup
    //   573: iconst_0
    //   574: ldc -4
    //   576: aastore
    //   577: iconst_1
    //   578: anewarray 214	java/lang/String
    //   581: dup
    //   582: iconst_0
    //   583: aload 5
    //   585: aastore
    //   586: invokevirtual 335	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;[Ljava/lang/String;[Ljava/lang/String;)V
    //   589: goto -123 -> 466
    //   592: astore 4
    //   594: aload_3
    //   595: ifnull -246 -> 349
    //   598: aload_3
    //   599: invokeinterface 319 1 0
    //   604: goto -255 -> 349
    //   607: new 328	android/content/ContentValues
    //   610: dup
    //   611: invokespecial 329	android/content/ContentValues:<init>	()V
    //   614: astore 6
    //   616: aload 6
    //   618: ldc_w 839
    //   621: ldc_w 841
    //   624: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   627: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   630: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   633: ldc_w 837
    //   636: aload 6
    //   638: iconst_1
    //   639: anewarray 214	java/lang/String
    //   642: dup
    //   643: iconst_0
    //   644: ldc -4
    //   646: aastore
    //   647: iconst_1
    //   648: anewarray 214	java/lang/String
    //   651: dup
    //   652: iconst_0
    //   653: aload 5
    //   655: aastore
    //   656: invokevirtual 335	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;[Ljava/lang/String;[Ljava/lang/String;)V
    //   659: new 328	android/content/ContentValues
    //   662: dup
    //   663: invokespecial 329	android/content/ContentValues:<init>	()V
    //   666: astore 5
    //   668: aload 5
    //   670: ldc_w 850
    //   673: iload_1
    //   674: iconst_1
    //   675: iadd
    //   676: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   679: invokevirtual 332	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   682: aload 5
    //   684: ldc_w 860
    //   687: aload 4
    //   689: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   692: aload 5
    //   694: ldc_w 839
    //   697: ldc_w 565
    //   700: invokevirtual 530	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   703: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   706: invokevirtual 210	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   709: ldc_w 837
    //   712: aload 5
    //   714: invokevirtual 553	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   717: goto -251 -> 466
    //   720: astore 5
    //   722: aload_3
    //   723: astore 4
    //   725: aload 5
    //   727: astore_3
    //   728: aload 4
    //   730: ifnull +10 -> 740
    //   733: aload 4
    //   735: invokeinterface 319 1 0
    //   740: aload_3
    //   741: athrow
    //   742: astore_3
    //   743: aconst_null
    //   744: astore 4
    //   746: goto -18 -> 728
    //   749: astore_3
    //   750: aconst_null
    //   751: astore_3
    //   752: goto -158 -> 594
    //   755: iconst_0
    //   756: istore_1
    //   757: goto -291 -> 466
    //   760: iconst_1
    //   761: istore_2
    //   762: goto -334 -> 428
    //   765: iconst_1
    //   766: istore_2
    //   767: goto -593 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	770	0	this	e
    //   1	756	1	i	int
    //   47	720	2	bool	boolean
    //   135	606	3	localObject1	Object
    //   742	1	3	localObject2	Object
    //   749	1	3	localException1	Exception
    //   751	1	3	localObject3	Object
    //   233	276	4	str	String
    //   592	96	4	localException2	Exception
    //   723	22	4	localObject4	Object
    //   290	423	5	localObject5	Object
    //   720	6	5	localObject6	Object
    //   490	147	6	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   274	339	592	java/lang/Exception
    //   466	589	592	java/lang/Exception
    //   607	717	592	java/lang/Exception
    //   274	339	720	finally
    //   466	589	720	finally
    //   607	717	720	finally
    //   213	270	742	finally
    //   213	270	749	java/lang/Exception
  }
  
  public void d(Intent paramIntent)
  {
    if (paramIntent == null) {}
    label396:
    label464:
    label473:
    for (;;)
    {
      return;
      int j;
      try
      {
        j = paramIntent.getIntExtra("id", -1);
        boolean bool = paramIntent.getBooleanExtra("result", false);
        if (j == -1) {
          continue;
        }
        com.igexin.push.core.g.ar += 1;
        if (!bool) {
          break label396;
        }
        if (paramIntent.getBooleanExtra("isReload", false))
        {
          Process.killProcess(Process.myPid());
          return;
        }
      }
      catch (Throwable paramIntent)
      {
        com.igexin.a.a.c.a.b("CoreAction|" + paramIntent.toString());
        return;
      }
      com.igexin.push.core.g.aq += 1;
      if (com.igexin.push.core.g.as != null) {}
      for (Object localObject1 = com.igexin.push.core.g.as.b();; localObject1 = null)
      {
        if (localObject1 == null) {
          break label473;
        }
        int i;
        Object localObject2;
        if (com.igexin.push.config.l.t != null)
        {
          paramIntent = com.igexin.push.config.l.t.b();
          if (paramIntent == null) {
            break;
          }
          if (!paramIntent.containsKey(Integer.valueOf(j))) {
            break label464;
          }
          i = 1;
          localObject2 = (com.igexin.push.core.bean.e)paramIntent.get(Integer.valueOf(j));
          if (localObject2 != null) {
            com.igexin.push.f.b.a(((com.igexin.push.core.bean.e)localObject2).c());
          }
          paramIntent.remove(Integer.valueOf(j));
        }
        for (;;)
        {
          localObject1 = (com.igexin.push.core.bean.e)((Map)localObject1).get(Integer.valueOf(j));
          if (localObject1 == null) {
            break;
          }
          localObject2 = com.igexin.push.core.g.ad + "/" + ((com.igexin.push.core.bean.e)localObject1).c();
          if (new File((String)localObject2).exists())
          {
            paramIntent.put(Integer.valueOf(j), localObject1);
            if (com.igexin.push.core.g.aq == com.igexin.push.core.g.ap) {
              com.igexin.push.config.l.t.a(com.igexin.push.core.g.as.a());
            }
            if ((i == 0) && (com.igexin.push.extension.a.a().a(com.igexin.push.core.g.g, (String)localObject2, ((com.igexin.push.core.bean.e)localObject1).d(), ((com.igexin.push.core.bean.e)localObject1).j(), ((com.igexin.push.core.bean.e)localObject1).c())))
            {
              com.igexin.a.a.c.a.b("CoreAction load " + ((com.igexin.push.core.bean.e)localObject1).d() + " success");
              ((com.igexin.push.core.bean.e)localObject1).b(System.currentTimeMillis());
              if (((com.igexin.push.core.bean.e)localObject1).g())
              {
                com.igexin.push.f.b.a(((com.igexin.push.core.bean.e)localObject1).c());
                paramIntent.remove(Integer.valueOf(j));
              }
            }
            com.igexin.push.config.a.a().g();
          }
          if ((com.igexin.push.core.g.ar != com.igexin.push.core.g.ap) || (!com.igexin.push.core.g.at)) {
            break;
          }
          com.igexin.a.a.c.a.b("CoreActiondown load ext success, restart service ############");
          Process.killProcess(Process.myPid());
          return;
          paramIntent = new HashMap();
          localObject2 = new com.igexin.push.core.bean.f();
          ((com.igexin.push.core.bean.f)localObject2).a("0");
          ((com.igexin.push.core.bean.f)localObject2).a(paramIntent);
          com.igexin.push.config.l.t = (com.igexin.push.core.bean.f)localObject2;
          i = 0;
          continue;
          i = 0;
        }
      }
    }
  }
  
  public void d(String paramString)
  {
    String str = a(true, 4);
    str = str + "2.7.0.0|sdkconfig-error|";
    paramString = (str + paramString).getBytes();
    paramString = new com.igexin.push.e.a.c(new com.igexin.push.core.d.j(SDKUrlConfig.getBiUploadServiceUrl(), paramString, 0, true));
    com.igexin.a.a.b.d.c().a(paramString, false, true);
  }
  
  public String e(String paramString)
  {
    if (com.igexin.push.core.g.c() == null) {
      return null;
    }
    return (String)com.igexin.push.core.g.c().get(paramString);
  }
  
  public void e()
  {
    com.igexin.a.a.c.a.a("CoreAction|do disconnect|" + SDKUrlConfig.getCmAddress().replaceFirst("socket", "disConnect"));
    com.igexin.a.a.b.d.c().a(SDKUrlConfig.getCmAddress().replaceFirst("socket", "disConnect"), 0, null);
  }
  
  public int f()
  {
    com.igexin.a.a.c.a.a("CoreAction send heart beat data ........");
    return com.igexin.push.core.f.a().g().a("H-" + com.igexin.push.core.g.s, new com.igexin.push.c.c.h(), true);
  }
  
  public void g()
  {
    Object localObject = com.igexin.push.core.c.c.a().b().iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.igexin.push.core.bean.i localI = (com.igexin.push.core.bean.i)((Iterator)localObject).next();
      if (localI.d() + 10000L <= System.currentTimeMillis())
      {
        long l = System.currentTimeMillis();
        localObject = new com.igexin.push.c.c.d();
        ((com.igexin.push.c.c.d)localObject).a();
        ((com.igexin.push.c.c.d)localObject).a = ((int)l);
        ((com.igexin.push.c.c.d)localObject).d = "17258000";
        ((com.igexin.push.c.c.d)localObject).e = localI.b();
        ((com.igexin.push.c.c.d)localObject).g = com.igexin.push.core.g.s;
        com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, (com.igexin.push.c.c.e)localObject);
        com.igexin.a.a.c.a.b("freshral|" + localI.b());
        com.igexin.push.core.c.c.a().a(localI.a());
        localI.a(localI.d() + 10000L);
        com.igexin.push.core.c.c.a().a(localI);
      }
    }
  }
  
  public void h()
  {
    long l = System.currentTimeMillis();
    String str = "{\"action\":\"request_deviceid\",\"id\":\"" + l + "\"}";
    com.igexin.push.c.c.d localD = new com.igexin.push.c.c.d();
    localD.a();
    localD.a = ((int)l);
    localD.d = "17258000";
    localD.e = str;
    localD.g = com.igexin.push.core.g.s;
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localD);
    com.igexin.a.a.c.a.b("deviceidReq");
  }
  
  public void i()
  {
    Object localObject1 = null;
    l2 = -1L;
    l1 = l2;
    try
    {
      localObject2 = new com.igexin.push.core.bean.a();
      l1 = l2;
      l2 = ((com.igexin.push.core.bean.a)localObject2).l;
      l1 = l2;
      localObject2 = com.igexin.push.core.bean.a.a((com.igexin.push.core.bean.a)localObject2);
      localObject1 = localObject2;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Object localObject2;
        l2 = l1;
      }
    }
    if (localObject1 != null)
    {
      com.igexin.a.a.c.a.b("addphoneinfo");
      localObject2 = com.igexin.push.core.c.c.a();
      if (localObject2 != null) {
        ((com.igexin.push.core.c.c)localObject2).a(new com.igexin.push.core.bean.i(l2, localObject1, (byte)5, l2));
      }
      localObject2 = new com.igexin.push.c.c.d();
      ((com.igexin.push.c.c.d)localObject2).a();
      ((com.igexin.push.c.c.d)localObject2).a = ((int)l2);
      ((com.igexin.push.c.c.d)localObject2).d = "17258000";
      ((com.igexin.push.c.c.d)localObject2).e = localObject1;
      ((com.igexin.push.c.c.d)localObject2).g = com.igexin.push.core.g.s;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, (com.igexin.push.c.c.e)localObject2);
    }
  }
  
  public void j()
  {
    long l = System.currentTimeMillis();
    String str = "{\"action\":\"request_ca_list\",\"id\":\"" + l + "\", \"appid\":\"" + com.igexin.push.core.g.a + "\", \"cid\":\"" + com.igexin.push.core.g.s + "\"}";
    com.igexin.push.c.c.d localD = new com.igexin.push.c.c.d();
    localD.a();
    localD.a = ((int)l);
    localD.d = "17258000";
    localD.e = str;
    localD.g = com.igexin.push.core.g.s;
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localD);
  }
  
  public long k()
  {
    return (new Random().nextInt(6) + 2) * 60000L;
  }
  
  public void l()
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(32);
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10002);
    localBundle.putString("clientid", com.igexin.push.core.g.s);
    localIntent.putExtras(localBundle);
    com.igexin.a.a.c.a.b("broadcastClientid|" + com.igexin.push.core.g.s);
    com.igexin.push.core.f.a().a(localIntent);
    Log.d("PushService", "clientid is " + com.igexin.push.core.g.s);
  }
  
  public void m()
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(32);
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10007);
    localBundle.putBoolean("onlineState", com.igexin.push.core.g.m);
    localIntent.putExtras(localBundle);
    com.igexin.push.core.f.a().a(localIntent);
  }
  
  /* Error */
  public String n()
  {
    // Byte code:
    //   0: new 614	java/io/File
    //   3: dup
    //   4: getstatic 617	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   7: invokespecial 618	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 621	java/io/File:exists	()Z
    //   13: ifeq +191 -> 204
    //   16: sipush 1024
    //   19: newarray byte
    //   21: astore_3
    //   22: new 1908	java/io/FileInputStream
    //   25: dup
    //   26: getstatic 617	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   29: invokespecial 1909	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   32: astore_2
    //   33: new 1911	java/io/ByteArrayOutputStream
    //   36: dup
    //   37: invokespecial 1912	java/io/ByteArrayOutputStream:<init>	()V
    //   40: astore 4
    //   42: aload_2
    //   43: aload_3
    //   44: invokevirtual 1916	java/io/FileInputStream:read	([B)I
    //   47: istore_1
    //   48: iload_1
    //   49: iconst_m1
    //   50: if_icmpeq +40 -> 90
    //   53: aload 4
    //   55: aload_3
    //   56: iconst_0
    //   57: iload_1
    //   58: invokevirtual 1919	java/io/ByteArrayOutputStream:write	([BII)V
    //   61: goto -19 -> 42
    //   64: astore_3
    //   65: aload_2
    //   66: astore_3
    //   67: aload 4
    //   69: astore_2
    //   70: aload_3
    //   71: ifnull +7 -> 78
    //   74: aload_3
    //   75: invokevirtual 1920	java/io/FileInputStream:close	()V
    //   78: aload_2
    //   79: ifnull +125 -> 204
    //   82: aload_2
    //   83: invokevirtual 1921	java/io/ByteArrayOutputStream:close	()V
    //   86: aconst_null
    //   87: astore_2
    //   88: aload_2
    //   89: areturn
    //   90: new 214	java/lang/String
    //   93: dup
    //   94: aload 4
    //   96: invokevirtual 1924	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   99: invokespecial 248	java/lang/String:<init>	([B)V
    //   102: astore_3
    //   103: aload_2
    //   104: ifnull +7 -> 111
    //   107: aload_2
    //   108: invokevirtual 1920	java/io/FileInputStream:close	()V
    //   111: aload_3
    //   112: astore_2
    //   113: aload 4
    //   115: ifnull -27 -> 88
    //   118: aload 4
    //   120: invokevirtual 1921	java/io/ByteArrayOutputStream:close	()V
    //   123: aload_3
    //   124: areturn
    //   125: astore_2
    //   126: aload_3
    //   127: areturn
    //   128: astore_2
    //   129: aconst_null
    //   130: areturn
    //   131: astore_3
    //   132: aconst_null
    //   133: astore 4
    //   135: aconst_null
    //   136: astore_2
    //   137: aload_2
    //   138: ifnull +7 -> 145
    //   141: aload_2
    //   142: invokevirtual 1920	java/io/FileInputStream:close	()V
    //   145: aload 4
    //   147: ifnull +8 -> 155
    //   150: aload 4
    //   152: invokevirtual 1921	java/io/ByteArrayOutputStream:close	()V
    //   155: aload_3
    //   156: athrow
    //   157: astore_2
    //   158: goto -47 -> 111
    //   161: astore_3
    //   162: goto -84 -> 78
    //   165: astore_2
    //   166: goto -21 -> 145
    //   169: astore_2
    //   170: goto -15 -> 155
    //   173: astore_3
    //   174: aconst_null
    //   175: astore 4
    //   177: goto -40 -> 137
    //   180: astore_3
    //   181: goto -44 -> 137
    //   184: astore_2
    //   185: aconst_null
    //   186: astore_2
    //   187: aconst_null
    //   188: astore_3
    //   189: goto -119 -> 70
    //   192: astore_3
    //   193: aconst_null
    //   194: astore 4
    //   196: aload_2
    //   197: astore_3
    //   198: aload 4
    //   200: astore_2
    //   201: goto -131 -> 70
    //   204: aconst_null
    //   205: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	e
    //   47	11	1	i	int
    //   32	81	2	localObject1	Object
    //   125	1	2	localException1	Exception
    //   128	1	2	localException2	Exception
    //   136	6	2	localObject2	Object
    //   157	1	2	localException3	Exception
    //   165	1	2	localException4	Exception
    //   169	1	2	localException5	Exception
    //   184	1	2	localException6	Exception
    //   186	15	2	localObject3	Object
    //   21	35	3	arrayOfByte	byte[]
    //   64	1	3	localException7	Exception
    //   66	61	3	localObject4	Object
    //   131	25	3	localObject5	Object
    //   161	1	3	localException8	Exception
    //   173	1	3	localObject6	Object
    //   180	1	3	localObject7	Object
    //   188	1	3	localObject8	Object
    //   192	1	3	localException9	Exception
    //   197	1	3	localObject9	Object
    //   40	159	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   42	48	64	java/lang/Exception
    //   53	61	64	java/lang/Exception
    //   90	103	64	java/lang/Exception
    //   118	123	125	java/lang/Exception
    //   82	86	128	java/lang/Exception
    //   22	33	131	finally
    //   107	111	157	java/lang/Exception
    //   74	78	161	java/lang/Exception
    //   141	145	165	java/lang/Exception
    //   150	155	169	java/lang/Exception
    //   33	42	173	finally
    //   42	48	180	finally
    //   53	61	180	finally
    //   90	103	180	finally
    //   22	33	184	java/lang/Exception
    //   33	42	192	java/lang/Exception
  }
  
  public void o()
  {
    ArrayList localArrayList = new ArrayList();
    a(localArrayList);
    int j = localArrayList.size();
    if (j <= 0) {}
    do
    {
      return;
      localObject = new JSONObject();
      try
      {
        ((JSONObject)localObject).put("action", "reportapplist");
        ((JSONObject)localObject).put("session_last", com.igexin.push.core.g.r);
        JSONArray localJSONArray = new JSONArray();
        int i = 0;
        while (i < j)
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("appid", ((com.igexin.push.core.bean.l)localArrayList.get(i)).d());
          localJSONObject.put("name", ((com.igexin.push.core.bean.l)localArrayList.get(i)).b());
          localJSONObject.put("version", ((com.igexin.push.core.bean.l)localArrayList.get(i)).c());
          localJSONObject.put("versionName", ((com.igexin.push.core.bean.l)localArrayList.get(i)).a());
          localJSONArray.put(localJSONObject);
          i += 1;
        }
        ((JSONObject)localObject).put("applist", localJSONArray);
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
      localObject = com.igexin.a.b.a.b(((JSONObject)localObject).toString().getBytes());
    } while (localObject == null);
    Object localObject = new com.igexin.push.e.a.c(new com.igexin.push.core.d.a(SDKUrlConfig.getBiUploadServiceUrl(), (byte[])localObject));
    com.igexin.a.a.b.d.c().a((com.igexin.a.a.d.d)localObject, false, true);
    h(p());
    com.igexin.a.a.c.a.b("reportapplist");
  }
  
  public String p()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    a(localArrayList2);
    int j = localArrayList2.size();
    if (j > 0)
    {
      int i = 0;
      while (i < j)
      {
        localArrayList1.add(((com.igexin.push.core.bean.l)localArrayList2.get(i)).d());
        i += 1;
      }
    }
    return localArrayList1.toString();
  }
  
  public boolean q()
  {
    if (com.igexin.push.core.g.g == null) {}
    Object localObject1 = com.igexin.push.core.g.g.getApplicationContext().getPackageName();
    int m;
    for (;;)
    {
      try
      {
        localObject2 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo((String)localObject1, 4).services;
        if (localObject2 == null) {
          break label342;
        }
        k = localObject2.length;
        j = 0;
        i = 0;
        if (j >= k) {}
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException1)
      {
        try
        {
          Object localObject2;
          localObject1 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo((String)localObject1, 2).receivers;
          if (localObject1 == null) {
            break label325;
          }
          i3 = localObject1.length;
          i2 = 0;
          n = 0;
          i = n;
          j = m;
          i1 = k;
          if (i2 >= i3) {}
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException5)
        {
          int i = 0;
          int j = m;
          i1 = k;
          continue;
          i = 0;
          j = m;
          i1 = k;
          continue;
        }
        try
        {
          i = localObject1[i2].name.indexOf("DownloadReceiver");
          if (i != -1) {
            n = 1;
          }
          i2 += 1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException6)
        {
          i = n;
          j = m;
          i1 = k;
          continue;
        }
        localNameNotFoundException1 = localNameNotFoundException1;
        i = 0;
        j = 0;
        i1 = 0;
        return (i1 != 0) && (j != 0) && (i != 0);
      }
      try
      {
        m = localObject2[j].name.indexOf("DownloadService");
        if (m != -1) {
          i = 1;
        }
        j += 1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException2)
      {
        j = 0;
        i1 = i;
        i = 0;
      }
    }
    label325:
    label342:
    for (int k = i;; k = 0)
    {
      for (;;)
      {
        try
        {
          localObject2 = com.igexin.push.core.g.g.getPackageManager().getPackageInfo((String)localObject1, 8).providers;
          if (localObject2 == null) {
            continue;
          }
          m = localObject2.length;
          j = 0;
          i = 0;
          if (j >= m) {}
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException3)
        {
          int n;
          int i3;
          int i2;
          i = 0;
          j = 0;
          int i1 = k;
          continue;
          m = 0;
          continue;
        }
        try
        {
          n = localObject2[j].name.indexOf("DownloadProvider");
          if (n != -1) {
            i = 1;
          }
          j += 1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException4)
        {
          j = i;
          i = 0;
          i1 = k;
        }
      }
      m = i;
    }
  }
  
  public void r()
  {
    long l = System.currentTimeMillis();
    com.igexin.push.core.f.a().k().a("message", "createtime <= " + (l - 604800000L));
  }
  
  public void s()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String str = localSimpleDateFormat.format(new Date());
    Object localObject3 = new File("/sdcard/libs/");
    Object localObject2 = com.igexin.push.core.g.e;
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = "unknowPacageName";
    }
    int j;
    int i;
    if (((File)localObject3).exists())
    {
      localObject2 = ((File)localObject3).list();
      j = localObject2.length;
      i = 0;
    }
    for (;;)
    {
      if (i < j)
      {
        int k = localObject2[i].length();
        if ((localObject2[i].startsWith((String)localObject1)) && (localObject2[i].endsWith(".log")) && (k > ((String)localObject1).length() + 14) && (((String)localObject1).equals(localObject2[i].substring(0, k - 15)))) {
          localObject3 = localObject2[i].substring(((String)localObject1).length() + 1, k - 4);
        }
      }
      try
      {
        localObject3 = localSimpleDateFormat.parse((String)localObject3);
        if (Math.abs((localSimpleDateFormat.parse(str).getTime() - ((Date)localObject3).getTime()) / 86400000L) > 6L)
        {
          localObject3 = new File("/sdcard/libs/" + localObject2[i]);
          if (((File)localObject3).exists()) {
            ((File)localObject3).delete();
          }
        }
        i += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public void t()
  {
    ArrayList localArrayList;
    Iterator localIterator;
    if (D() > 0)
    {
      localArrayList = new ArrayList();
      localIterator = com.igexin.push.core.g.ai.entrySet().iterator();
    }
    while (localIterator.hasNext())
    {
      String str2 = (String)((Map.Entry)localIterator.next()).getKey();
      PushTaskBean localPushTaskBean = (PushTaskBean)com.igexin.push.core.g.ai.get(str2);
      String str1 = "";
      if ((localPushTaskBean != null) && (localPushTaskBean.getStatus() == com.igexin.push.core.a.k))
      {
        String str3 = localPushTaskBean.getTaskId();
        Map localMap = localPushTaskBean.getConditionMap();
        if (localMap == null) {
          return;
        }
        if ((localMap.containsKey("endTime")) && (Long.valueOf((String)localMap.get("endTime")).longValue() < System.currentTimeMillis()))
        {
          a(com.igexin.push.core.a.m, str3, str2);
          localArrayList.add(str2);
        }
        else
        {
          int i;
          if (localMap.containsKey("wifi"))
          {
            i = Integer.valueOf((String)localMap.get("wifi")).intValue();
            w();
            if (i != com.igexin.push.core.g.p) {}
          }
          else if (localMap.containsKey("screenOn"))
          {
            i = Integer.valueOf((String)localMap.get("screenOn")).intValue();
            v();
            if (i != com.igexin.push.core.g.q) {}
          }
          else if (localMap.containsKey("ssid"))
          {
            str1 = (String)localMap.get("ssid");
            x();
            if (!com.igexin.push.core.g.ao.containsValue(str1)) {}
          }
          else if (localMap.containsKey("bssid"))
          {
            String str4 = (String)localMap.get("bssid");
            if ((!com.igexin.push.core.g.ao.containsKey(str4)) || (!((String)com.igexin.push.core.g.ao.get(str4)).equals(str1))) {}
          }
          else if ((!localMap.containsKey("startTime")) || (Long.valueOf((String)localMap.get("startTime")).longValue() <= System.currentTimeMillis()))
          {
            str1 = localPushTaskBean.getMessageId();
            a().a(str3, str1, com.igexin.push.core.g.a, com.igexin.push.core.g.e);
            a(com.igexin.push.core.a.l, str3, str2);
            localArrayList.add(str2);
          }
        }
      }
    }
    b(localArrayList);
  }
  
  public boolean u()
  {
    long l = System.currentTimeMillis();
    if (com.igexin.push.core.g.I > 0L)
    {
      if (l - com.igexin.push.core.g.I > 60000L)
      {
        com.igexin.push.core.g.I = l;
        return true;
      }
    }
    else
    {
      com.igexin.push.core.g.I = l - 60000L;
      return true;
    }
    return false;
  }
  
  public void v()
  {
    if (((PowerManager)com.igexin.push.core.g.g.getSystemService("power")).isScreenOn())
    {
      com.igexin.push.core.g.q = 1;
      return;
    }
    com.igexin.push.core.g.q = 0;
  }
  
  public void w()
  {
    NetworkInfo.State localState = ((ConnectivityManager)com.igexin.push.core.g.g.getSystemService("connectivity")).getNetworkInfo(1).getState();
    if ((localState == NetworkInfo.State.CONNECTED) || (localState == NetworkInfo.State.CONNECTING))
    {
      com.igexin.push.core.g.p = 1;
      return;
    }
    com.igexin.push.core.g.p = 0;
  }
  
  public void x()
  {
    List localList = ((WifiManager)com.igexin.push.core.g.g.getSystemService("wifi")).getScanResults();
    com.igexin.push.core.g.ao.clear();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        com.igexin.push.core.g.ao.put(((ScanResult)localList.get(i)).BSSID, ((ScanResult)localList.get(i)).SSID);
        i += 1;
      }
    }
  }
  
  public void y()
  {
    if (com.igexin.push.config.l.q)
    {
      Map localMap = com.igexin.push.core.c.f.a().d();
      if ((localMap != null) && (localMap.size() > 0))
      {
        Iterator localIterator = localMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          String str2 = (String)localMap.get(str1);
          try
          {
            Intent localIntent = new Intent();
            localIntent.setClassName(str1, str2);
            com.igexin.push.core.g.g.startService(localIntent);
          }
          catch (Exception localException) {}
        }
      }
    }
  }
  
  public void z()
  {
    int i = com.igexin.push.core.g.an - 100;
    if (i < 0) {}
    Object localObject2;
    for (com.igexin.push.core.g.an = 0;; com.igexin.push.core.g.an = i)
    {
      localObject1 = new ArrayList();
      long l = System.currentTimeMillis();
      localObject2 = com.igexin.push.core.g.am.entrySet().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject2).next();
        if (l - ((Long)localEntry.getValue()).longValue() > 3600000L) {
          ((List)localObject1).add((String)localEntry.getKey());
        }
      }
    }
    Object localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Iterator)localObject1).next();
      com.igexin.push.core.g.am.remove(localObject2);
    }
  }
}
