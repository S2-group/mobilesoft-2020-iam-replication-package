package com.igexin.push.core.a;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
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
import android.content.pm.ResolveInfo;
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
import java.lang.reflect.Method;
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
  
  private void D()
  {
    com.igexin.push.core.i.a().a(com.igexin.push.core.k.d);
    com.igexin.a.a.c.a.a("CoreAction onInternalReceiver: network changed");
    if ((com.igexin.push.core.f.a().j().getActiveNetworkInfo() != null) && (com.igexin.push.core.f.a().j().getActiveNetworkInfo().isAvailable() == true))
    {
      com.igexin.push.core.g.i = true;
      if (com.igexin.push.core.g.m) {
        break label86;
      }
      com.igexin.push.core.f.a().g().c(true);
    }
    for (;;)
    {
      com.igexin.a.a.c.a.a("CoreAction network changed start to check condition status....");
      if (u()) {
        t();
      }
      return;
      com.igexin.push.core.g.i = false;
      break;
      label86:
      if (System.currentTimeMillis() - com.igexin.push.core.g.R > 5000L)
      {
        com.igexin.push.core.g.R = System.currentTimeMillis();
        if (f() == -2)
        {
          com.igexin.push.core.g.m = false;
          m();
        }
      }
    }
  }
  
  private int E()
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
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
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
          localL.a(localApplicationInfo.loadLabel(localPackageManager).toString());
          localL.c(localApplicationInfo.packageName);
          localL.b(String.valueOf(localPackageInfo.versionCode));
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
  
  private boolean a(Class paramClass, Method paramMethod, String paramString)
  {
    try
    {
      paramClass = paramMethod.invoke(paramClass, new Object[] { paramString });
      return paramClass != null;
    }
    catch (Exception paramClass) {}
    return true;
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
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
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
    //   3: ldc_w 263
    //   6: invokevirtual 497	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   9: astore 4
    //   11: aload_1
    //   12: ldc_w 261
    //   15: invokevirtual 497	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   18: astore 5
    //   20: aload_1
    //   21: ldc_w 259
    //   24: invokevirtual 497	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   27: astore 8
    //   29: aload_1
    //   30: ldc_w 533
    //   33: invokevirtual 497	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   36: astore 6
    //   38: new 332	android/content/ContentValues
    //   41: dup
    //   42: invokespecial 333	android/content/ContentValues:<init>	()V
    //   45: astore 7
    //   47: new 535	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   54: ldc_w 538
    //   57: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: aload 4
    //   62: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: astore_1
    //   69: aload 7
    //   71: ldc_w 263
    //   74: aload 4
    //   76: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload 7
    //   81: ldc_w 259
    //   84: aload 8
    //   86: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload 7
    //   91: ldc_w 548
    //   94: aload_1
    //   95: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   98: aload 7
    //   100: ldc_w 550
    //   103: invokestatic 183	java/lang/System:currentTimeMillis	()J
    //   106: invokestatic 555	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   109: invokevirtual 558	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   112: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   115: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   118: ldc -43
    //   120: iconst_1
    //   121: anewarray 215	java/lang/String
    //   124: dup
    //   125: iconst_0
    //   126: ldc_w 548
    //   129: aastore
    //   130: iconst_1
    //   131: anewarray 215	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: aload_1
    //   137: aastore
    //   138: aconst_null
    //   139: aconst_null
    //   140: invokevirtual 224	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   143: astore_1
    //   144: aload_1
    //   145: ifnull +91 -> 236
    //   148: aload_1
    //   149: invokeinterface 561 1 0
    //   154: ifne +82 -> 236
    //   157: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   160: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   163: ldc -43
    //   165: aload 7
    //   167: invokevirtual 564	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   170: aload 6
    //   172: getstatic 566	com/igexin/push/core/g:e	Ljava/lang/String;
    //   175: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
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
    //   198: invokeinterface 323 1 0
    //   203: return
    //   204: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   207: ifnull +29 -> 236
    //   210: aload_0
    //   211: aload 4
    //   213: aload 5
    //   215: invokevirtual 569	com/igexin/push/core/a/e:b	(Ljava/lang/String;Ljava/lang/String;)Lcom/igexin/push/core/b;
    //   218: getstatic 574	com/igexin/push/core/b:a	Lcom/igexin/push/core/b;
    //   221: if_acmpne +15 -> 236
    //   224: aload_0
    //   225: aload 4
    //   227: aload 5
    //   229: ldc_w 576
    //   232: invokevirtual 578	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z
    //   235: pop
    //   236: aload_1
    //   237: ifnull -34 -> 203
    //   240: aload_1
    //   241: invokeinterface 323 1 0
    //   246: return
    //   247: astore_1
    //   248: aconst_null
    //   249: astore_1
    //   250: aload_1
    //   251: ifnull -48 -> 203
    //   254: aload_1
    //   255: invokeinterface 323 1 0
    //   260: return
    //   261: astore 4
    //   263: aload_3
    //   264: astore_1
    //   265: aload 4
    //   267: astore_3
    //   268: aload_1
    //   269: ifnull +9 -> 278
    //   272: aload_1
    //   273: invokeinterface 323 1 0
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
  
  private void j(String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("package:")))
    {
      paramString = paramString.substring(8);
      if (com.igexin.push.core.c.f.a().d().containsKey(paramString)) {
        com.igexin.push.core.c.f.a().d().remove(paramString);
      }
    }
  }
  
  private void k(String paramString)
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
  private void l(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 624	java/io/File
    //   5: dup
    //   6: getstatic 627	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   9: invokespecial 628	java/io/File:<init>	(Ljava/lang/String;)V
    //   12: astore_2
    //   13: aload_2
    //   14: invokevirtual 631	java/io/File:exists	()Z
    //   17: ifne +8 -> 25
    //   20: aload_2
    //   21: invokevirtual 634	java/io/File:createNewFile	()Z
    //   24: pop
    //   25: new 636	java/io/FileOutputStream
    //   28: dup
    //   29: getstatic 627	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   32: invokespecial 637	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   35: astore_2
    //   36: aload_2
    //   37: aload_1
    //   38: invokestatic 639	com/igexin/a/b/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   41: invokevirtual 643	java/lang/String:getBytes	()[B
    //   44: invokevirtual 646	java/io/FileOutputStream:write	([B)V
    //   47: aload_2
    //   48: ifnull +7 -> 55
    //   51: aload_2
    //   52: invokevirtual 647	java/io/FileOutputStream:close	()V
    //   55: return
    //   56: astore_1
    //   57: aload_3
    //   58: astore_1
    //   59: aload_1
    //   60: ifnull -5 -> 55
    //   63: aload_1
    //   64: invokevirtual 647	java/io/FileOutputStream:close	()V
    //   67: return
    //   68: astore_1
    //   69: return
    //   70: astore_1
    //   71: aconst_null
    //   72: astore_2
    //   73: aload_2
    //   74: ifnull +7 -> 81
    //   77: aload_2
    //   78: invokevirtual 647	java/io/FileOutputStream:close	()V
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
  
  private boolean m(String paramString)
  {
    try
    {
      com.igexin.push.core.g.g.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (Exception paramString) {}
    return false;
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
  
  public boolean C()
  {
    for (;;)
    {
      int i;
      try
      {
        if (!"none".equals(com.igexin.push.config.l.u))
        {
          String[] arrayOfString = com.igexin.push.config.l.u.split(",");
          i = 0;
          if (i < arrayOfString.length)
          {
            if (!m(arrayOfString[i])) {
              break label133;
            }
            return false;
          }
          if (!"none".equals(com.igexin.push.config.l.v))
          {
            arrayOfString = com.igexin.push.config.l.v.split(",");
            Class localClass = Class.forName("android.os.ServiceManager");
            Method localMethod = localClass.getMethod("getService", new Class[] { String.class });
            localMethod.setAccessible(true);
            i = 0;
            if (i < arrayOfString.length)
            {
              boolean bool = a(localClass, localMethod, arrayOfString[i]);
              if (!bool) {
                i += 1;
              }
            }
            else
            {
              return true;
            }
          }
        }
      }
      catch (Exception localException) {}
      return false;
      label133:
      i += 1;
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
    //   6: new 863	java/text/SimpleDateFormat
    //   9: dup
    //   10: ldc_w 865
    //   13: invokespecial 866	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   16: new 868	java/util/Date
    //   19: dup
    //   20: invokespecial 869	java/util/Date:<init>	()V
    //   23: invokevirtual 873	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   26: astore 13
    //   28: iload_2
    //   29: iconst_m1
    //   30: if_icmpne +83 -> 113
    //   33: new 535	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   40: aload 13
    //   42: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc_w 875
    //   48: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   54: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: ldc_w 875
    //   60: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: ldc_w 879
    //   66: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc_w 875
    //   72: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: getstatic 881	com/igexin/push/core/g:t	Ljava/lang/String;
    //   78: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: astore 13
    //   86: aload 13
    //   88: astore 11
    //   90: aload 11
    //   92: astore 13
    //   94: aload 12
    //   96: ifnull +14 -> 110
    //   99: aload 12
    //   101: invokeinterface 323 1 0
    //   106: aload 11
    //   108: astore 13
    //   110: aload 13
    //   112: areturn
    //   113: iload_2
    //   114: ifne +983 -> 1097
    //   117: iload_1
    //   118: ifeq +578 -> 696
    //   121: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   124: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   127: ldc_w 883
    //   130: iconst_1
    //   131: anewarray 215	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: ldc_w 885
    //   139: aastore
    //   140: iconst_2
    //   141: anewarray 215	java/lang/String
    //   144: dup
    //   145: iconst_0
    //   146: ldc_w 576
    //   149: aastore
    //   150: dup
    //   151: iconst_1
    //   152: ldc_w 887
    //   155: aastore
    //   156: aconst_null
    //   157: aconst_null
    //   158: invokevirtual 224	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
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
    //   181: invokeinterface 229 1 0
    //   186: ifeq +900 -> 1086
    //   189: aload 11
    //   191: aload 11
    //   193: ldc_w 889
    //   196: invokeinterface 892 2 0
    //   201: invokeinterface 297 2 0
    //   206: istore_2
    //   207: aload 11
    //   209: aload 11
    //   211: ldc_w 894
    //   214: invokeinterface 892 2 0
    //   219: invokeinterface 297 2 0
    //   224: istore_3
    //   225: aload 11
    //   227: aload 11
    //   229: ldc_w 896
    //   232: invokeinterface 892 2 0
    //   237: invokeinterface 297 2 0
    //   242: istore 4
    //   244: aload 11
    //   246: aload 11
    //   248: ldc_w 898
    //   251: invokeinterface 892 2 0
    //   256: invokeinterface 297 2 0
    //   261: istore 5
    //   263: aload 11
    //   265: aload 11
    //   267: ldc_w 900
    //   270: invokeinterface 892 2 0
    //   275: invokeinterface 297 2 0
    //   280: istore 6
    //   282: aload 11
    //   284: aload 11
    //   286: ldc_w 902
    //   289: invokeinterface 892 2 0
    //   294: invokeinterface 297 2 0
    //   299: istore 7
    //   301: aload 11
    //   303: aload 11
    //   305: ldc_w 904
    //   308: invokeinterface 892 2 0
    //   313: invokeinterface 297 2 0
    //   318: istore 8
    //   320: new 535	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   327: aload 11
    //   329: aload 11
    //   331: ldc_w 906
    //   334: invokeinterface 892 2 0
    //   339: invokeinterface 908 2 0
    //   344: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: ldc_w 910
    //   350: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: astore 13
    //   358: aload 12
    //   360: ifnonnull +379 -> 739
    //   363: new 535	java/lang/StringBuilder
    //   366: dup
    //   367: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   370: aload 13
    //   372: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: ldc_w 875
    //   378: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   384: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: ldc_w 875
    //   390: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   393: ldc_w 912
    //   396: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: ldc_w 875
    //   402: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: iload_2
    //   406: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   409: ldc_w 917
    //   412: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload 13
    //   417: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: ldc_w 875
    //   423: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   429: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: ldc_w 875
    //   435: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: ldc_w 919
    //   441: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: ldc_w 875
    //   447: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: iload_3
    //   451: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   454: ldc_w 917
    //   457: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   460: aload 13
    //   462: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: ldc_w 875
    //   468: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   474: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: ldc_w 875
    //   480: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: ldc_w 921
    //   486: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: ldc_w 875
    //   492: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: iload 4
    //   497: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   500: ldc_w 917
    //   503: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: aload 13
    //   508: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: ldc_w 875
    //   514: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   520: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   523: ldc_w 875
    //   526: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: ldc_w 923
    //   532: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   535: ldc_w 875
    //   538: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: iload 5
    //   543: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   546: ldc_w 917
    //   549: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: aload 13
    //   554: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: ldc_w 875
    //   560: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   563: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   566: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: ldc_w 875
    //   572: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: ldc_w 925
    //   578: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: ldc_w 875
    //   584: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   587: iload 6
    //   589: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   592: ldc_w 917
    //   595: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: aload 13
    //   600: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   603: ldc_w 875
    //   606: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   609: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   612: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: ldc_w 875
    //   618: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: ldc_w 927
    //   624: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   627: ldc_w 875
    //   630: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: iload 7
    //   635: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   638: ldc_w 917
    //   641: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: aload 13
    //   646: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   649: ldc_w 875
    //   652: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   655: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   658: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   661: ldc_w 875
    //   664: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: ldc_w 929
    //   670: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: ldc_w 875
    //   676: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   679: iload 8
    //   681: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   684: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   687: astore 13
    //   689: aload 13
    //   691: astore 12
    //   693: goto -518 -> 175
    //   696: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   699: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   702: ldc_w 883
    //   705: iconst_1
    //   706: anewarray 215	java/lang/String
    //   709: dup
    //   710: iconst_0
    //   711: ldc_w 885
    //   714: aastore
    //   715: iconst_1
    //   716: anewarray 215	java/lang/String
    //   719: dup
    //   720: iconst_0
    //   721: ldc_w 887
    //   724: aastore
    //   725: aconst_null
    //   726: aconst_null
    //   727: invokevirtual 224	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   730: astore 12
    //   732: aload 12
    //   734: astore 11
    //   736: goto -569 -> 167
    //   739: new 535	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   746: aload 12
    //   748: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: ldc_w 917
    //   754: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: aload 13
    //   759: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: ldc_w 875
    //   765: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   768: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   771: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   774: ldc_w 875
    //   777: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   780: ldc_w 912
    //   783: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   786: ldc_w 875
    //   789: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   792: iload_2
    //   793: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   796: ldc_w 917
    //   799: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: aload 13
    //   804: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   807: ldc_w 875
    //   810: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   813: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   816: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   819: ldc_w 875
    //   822: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   825: ldc_w 919
    //   828: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   831: ldc_w 875
    //   834: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: iload_3
    //   838: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   841: ldc_w 917
    //   844: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: aload 13
    //   849: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   852: ldc_w 875
    //   855: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   858: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   861: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   864: ldc_w 875
    //   867: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   870: ldc_w 921
    //   873: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   876: ldc_w 875
    //   879: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   882: iload 4
    //   884: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   887: ldc_w 917
    //   890: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   893: aload 13
    //   895: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   898: ldc_w 875
    //   901: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   904: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   907: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   910: ldc_w 875
    //   913: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   916: ldc_w 923
    //   919: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   922: ldc_w 875
    //   925: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   928: iload 5
    //   930: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   933: ldc_w 917
    //   936: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   939: aload 13
    //   941: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   944: ldc_w 875
    //   947: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   950: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   953: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: ldc_w 875
    //   959: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   962: ldc_w 925
    //   965: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   968: ldc_w 875
    //   971: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   974: iload 6
    //   976: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   979: ldc_w 917
    //   982: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: aload 13
    //   987: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   990: ldc_w 875
    //   993: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   996: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   999: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: ldc_w 875
    //   1005: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: ldc_w 927
    //   1011: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: ldc_w 875
    //   1017: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1020: iload 7
    //   1022: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1025: ldc_w 917
    //   1028: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: aload 13
    //   1033: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1036: ldc_w 875
    //   1039: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   1045: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1048: ldc_w 875
    //   1051: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: ldc_w 929
    //   1057: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1060: ldc_w 875
    //   1063: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1066: iload 8
    //   1068: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1071: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
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
    //   1105: getfield 931	com/igexin/push/core/i:a	J
    //   1108: lstore 9
    //   1110: getstatic 933	com/igexin/push/config/l:d	I
    //   1113: ifle +13 -> 1126
    //   1116: getstatic 933	com/igexin/push/config/l:d	I
    //   1119: sipush 1000
    //   1122: imul
    //   1123: i2l
    //   1124: lstore 9
    //   1126: new 535	java/lang/StringBuilder
    //   1129: dup
    //   1130: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   1133: getstatic 935	com/igexin/push/config/l:a	I
    //   1136: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1139: ldc_w 694
    //   1142: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1145: getstatic 937	com/igexin/push/config/l:b	I
    //   1148: invokevirtual 915	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1151: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1154: astore 14
    //   1156: new 535	java/lang/StringBuilder
    //   1159: dup
    //   1160: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   1163: aload 13
    //   1165: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1168: ldc_w 875
    //   1171: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: getstatic 940	com/igexin/push/core/g:s	Ljava/lang/String;
    //   1177: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1180: ldc_w 875
    //   1183: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1186: getstatic 942	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1189: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1192: ldc_w 875
    //   1195: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: getstatic 944	com/igexin/push/core/g:j	Z
    //   1201: invokevirtual 947	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1204: ldc_w 875
    //   1207: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: aload 14
    //   1212: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1215: ldc_w 875
    //   1218: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1221: lload 9
    //   1223: invokevirtual 950	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1226: ldc_w 875
    //   1229: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1232: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1235: astore 13
    //   1237: aload 13
    //   1239: astore 11
    //   1241: goto -1151 -> 90
    //   1244: iload_2
    //   1245: iconst_4
    //   1246: if_icmpne +57 -> 1303
    //   1249: new 535	java/lang/StringBuilder
    //   1252: dup
    //   1253: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   1256: aload 13
    //   1258: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: ldc_w 875
    //   1264: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1267: getstatic 940	com/igexin/push/core/g:s	Ljava/lang/String;
    //   1270: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1273: ldc_w 875
    //   1276: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1279: getstatic 942	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1282: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1285: ldc_w 875
    //   1288: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1291: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1294: astore 13
    //   1296: aload 13
    //   1298: astore 11
    //   1300: goto -1210 -> 90
    //   1303: iload_2
    //   1304: iconst_5
    //   1305: if_icmpne +105 -> 1410
    //   1308: new 535	java/lang/StringBuilder
    //   1311: dup
    //   1312: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   1315: aload 13
    //   1317: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1320: ldc_w 875
    //   1323: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1326: getstatic 940	com/igexin/push/core/g:s	Ljava/lang/String;
    //   1329: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1332: ldc_w 875
    //   1335: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: getstatic 942	com/igexin/push/core/g:a	Ljava/lang/String;
    //   1341: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1344: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
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
    //   1372: invokeinterface 323 1 0
    //   1377: aload 12
    //   1379: areturn
    //   1380: astore 12
    //   1382: aconst_null
    //   1383: astore 11
    //   1385: aload 11
    //   1387: ifnull +10 -> 1397
    //   1390: aload 11
    //   1392: invokeinterface 323 1 0
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
      com.igexin.a.a.c.a.a("setHeartbeatInterval heartbeatReq");
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
        break label43;
      }
    }
    label43:
    for (com.igexin.push.core.g.C = paramIntent.getStringExtra("op_app");; com.igexin.push.core.g.C = "")
    {
      if (com.igexin.push.core.g.m) {
        l();
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
                com.igexin.a.a.c.a.a("CoreAction onPushManagerMessage recevie action : sendMessage");
              } while (!com.igexin.push.config.l.i);
              str1 = paramBundle.getString("taskid");
              paramBundle = paramBundle.getByteArray("extraData");
              com.igexin.a.a.c.a.a("CoreAction receive broadcast msg data , task id : " + str1 + " ######@##@@@#");
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
        com.igexin.a.a.c.a.a("-> CoreAction onPushManagerMessage bindAlias...");
        c(paramBundle);
        return;
      }
    } while (!str1.equals("unbindAlias"));
    paramBundle = paramBundle.getString("alias");
    com.igexin.a.a.c.a.a("-> CoreAction onPushManagerMessage unbindAlias...");
    d(paramBundle);
  }
  
  public void a(PushTaskBean paramPushTaskBean)
  {
    com.igexin.push.c.c.c localC = new com.igexin.push.c.c.c();
    localC.a();
    localC.c = ("RCV" + paramPushTaskBean.getMessageId());
    localC.d = com.igexin.push.core.g.s;
    localC.a = ((int)System.currentTimeMillis());
    com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localC);
    com.igexin.a.a.c.a.a("cdnreceive|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId());
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
    com.igexin.a.a.c.a.a("feedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString1);
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
      com.igexin.push.core.c.c.a().a(new com.igexin.push.core.bean.i(l, (String)localObject, (byte)6, l));
      com.igexin.push.c.c.d localD = new com.igexin.push.c.c.d();
      localD.a();
      localD.a = ((int)l);
      localD.d = com.igexin.push.core.g.s;
      localD.e = localObject;
      localD.f = paramArrayOfByte;
      localD.g = com.igexin.push.core.g.s;
      com.igexin.push.core.f.a().g().a("C-" + com.igexin.push.core.g.s, localD);
      if ((paramString != null) && (paramString.startsWith("4T5@S_"))) {
        com.igexin.a.a.c.a.a("CoreAction sending lbs report message : " + (String)localObject);
      }
      return;
    }
    catch (JSONException paramString)
    {
      com.igexin.a.a.c.a.a("CoreAction" + paramString.toString());
    }
  }
  
  public void a(boolean paramBoolean) {}
  
  /* Error */
  public void a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 241	org/json/JSONObject
    //   5: dup
    //   6: new 215	java/lang/String
    //   9: dup
    //   10: aload_1
    //   11: iconst_0
    //   12: invokestatic 1239	android/util/Base64:decode	([BI)[B
    //   15: invokestatic 246	com/igexin/a/b/a:c	([B)[B
    //   18: invokespecial 249	java/lang/String:<init>	([B)V
    //   21: invokespecial 251	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   24: astore_1
    //   25: new 535	java/lang/StringBuilder
    //   28: dup
    //   29: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   32: ldc_w 1241
    //   35: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 1244	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 130	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   48: aload_1
    //   49: ldc_w 1209
    //   52: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   55: ifeq +1006 -> 1061
    //   58: ldc_w 1129
    //   61: aload_1
    //   62: ldc_w 1209
    //   65: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   68: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   71: ifeq +990 -> 1061
    //   74: invokestatic 183	java/lang/System:currentTimeMillis	()J
    //   77: lstore_3
    //   78: invokestatic 590	com/igexin/push/core/c/f:a	()Lcom/igexin/push/core/c/f;
    //   81: lload_3
    //   82: invokevirtual 1247	com/igexin/push/core/c/f:g	(J)Z
    //   85: pop
    //   86: aload_1
    //   87: ldc_w 1249
    //   90: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   93: ifeq +968 -> 1061
    //   96: new 241	org/json/JSONObject
    //   99: dup
    //   100: aload_1
    //   101: ldc_w 1249
    //   104: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: invokespecial 251	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   110: astore_1
    //   111: aload_1
    //   112: ldc_w 1251
    //   115: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   118: ifeq +45 -> 163
    //   121: aload_1
    //   122: ldc_w 1251
    //   125: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   128: astore 5
    //   130: aload 5
    //   132: ldc_w 1253
    //   135: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   138: ifne +14 -> 152
    //   141: aload 5
    //   143: ldc_w 1255
    //   146: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   149: ifeq +14 -> 163
    //   152: aload 5
    //   154: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   157: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   160: putstatic 1265	com/igexin/push/config/l:h	Z
    //   163: aload_1
    //   164: ldc_w 1267
    //   167: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   170: ifeq +45 -> 215
    //   173: aload_1
    //   174: ldc_w 1267
    //   177: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   180: astore 5
    //   182: aload 5
    //   184: ldc_w 1253
    //   187: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifne +14 -> 204
    //   193: aload 5
    //   195: ldc_w 1255
    //   198: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   201: ifeq +14 -> 215
    //   204: aload 5
    //   206: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   209: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   212: putstatic 1033	com/igexin/push/config/l:i	Z
    //   215: aload_1
    //   216: ldc_w 1269
    //   219: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   222: ifeq +45 -> 267
    //   225: aload_1
    //   226: ldc_w 1269
    //   229: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   232: astore 5
    //   234: aload 5
    //   236: ldc_w 1253
    //   239: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   242: ifne +14 -> 256
    //   245: aload 5
    //   247: ldc_w 1255
    //   250: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   253: ifeq +14 -> 267
    //   256: aload 5
    //   258: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   261: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   264: putstatic 1271	com/igexin/push/config/l:g	Z
    //   267: aload_1
    //   268: ldc_w 1273
    //   271: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   274: ifeq +45 -> 319
    //   277: aload_1
    //   278: ldc_w 1273
    //   281: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   284: astore 5
    //   286: aload 5
    //   288: ldc_w 1253
    //   291: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   294: ifne +14 -> 308
    //   297: aload 5
    //   299: ldc_w 1255
    //   302: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   305: ifeq +14 -> 319
    //   308: aload 5
    //   310: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   313: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   316: putstatic 1275	com/igexin/push/config/l:n	Z
    //   319: aload_1
    //   320: ldc_w 1277
    //   323: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   326: ifeq +45 -> 371
    //   329: aload_1
    //   330: ldc_w 1277
    //   333: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   336: astore 5
    //   338: aload 5
    //   340: ldc_w 1253
    //   343: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   346: ifne +14 -> 360
    //   349: aload 5
    //   351: ldc_w 1255
    //   354: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   357: ifeq +14 -> 371
    //   360: aload 5
    //   362: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   365: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   368: putstatic 1278	com/igexin/push/config/l:o	Z
    //   371: aload_1
    //   372: ldc_w 1280
    //   375: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   378: ifeq +45 -> 423
    //   381: aload_1
    //   382: ldc_w 1280
    //   385: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   388: astore 5
    //   390: aload 5
    //   392: ldc_w 1253
    //   395: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   398: ifne +14 -> 412
    //   401: aload 5
    //   403: ldc_w 1255
    //   406: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   409: ifeq +14 -> 423
    //   412: aload 5
    //   414: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   417: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   420: putstatic 1282	com/igexin/push/config/l:f	Z
    //   423: aload_1
    //   424: ldc_w 1284
    //   427: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   430: ifeq +67 -> 497
    //   433: aload_1
    //   434: ldc_w 1284
    //   437: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   440: astore 5
    //   442: aload 5
    //   444: ldc_w 1253
    //   447: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   450: ifne +14 -> 464
    //   453: aload 5
    //   455: ldc_w 1255
    //   458: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   461: ifeq +36 -> 497
    //   464: aload 5
    //   466: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   469: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   472: putstatic 1019	com/igexin/push/config/l:k	Z
    //   475: getstatic 1019	com/igexin/push/config/l:k	Z
    //   478: ifne +19 -> 497
    //   481: getstatic 937	com/igexin/push/config/l:b	I
    //   484: ifeq +13 -> 497
    //   487: aload_0
    //   488: bipush 12
    //   490: iconst_0
    //   491: ldc_w 1286
    //   494: invokevirtual 1028	com/igexin/push/core/a/e:a	(IILjava/lang/String;)V
    //   497: aload_1
    //   498: ldc_w 1288
    //   501: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   504: ifeq +21 -> 525
    //   507: aload_1
    //   508: ldc_w 1288
    //   511: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   514: astore 5
    //   516: aload 5
    //   518: invokestatic 1291	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   521: i2l
    //   522: putstatic 1293	com/igexin/push/config/l:p	J
    //   525: aload_1
    //   526: ldc_w 1295
    //   529: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   532: ifeq +45 -> 577
    //   535: aload_1
    //   536: ldc_w 1295
    //   539: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   542: astore 5
    //   544: aload 5
    //   546: ldc_w 1253
    //   549: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   552: ifne +14 -> 566
    //   555: aload 5
    //   557: ldc_w 1255
    //   560: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   563: ifeq +14 -> 577
    //   566: aload 5
    //   568: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   571: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   574: putstatic 1012	com/igexin/push/config/l:j	Z
    //   577: aload_1
    //   578: ldc_w 1297
    //   581: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   584: ifeq +45 -> 629
    //   587: aload_1
    //   588: ldc_w 1297
    //   591: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   594: astore 5
    //   596: aload 5
    //   598: ldc_w 1253
    //   601: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   604: ifne +14 -> 618
    //   607: aload 5
    //   609: ldc_w 1255
    //   612: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   615: ifeq +14 -> 629
    //   618: aload 5
    //   620: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   623: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   626: putstatic 1054	com/igexin/push/config/l:l	Z
    //   629: aload_1
    //   630: ldc_w 1299
    //   633: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   636: ifeq +45 -> 681
    //   639: aload_1
    //   640: ldc_w 1299
    //   643: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   646: astore 5
    //   648: aload 5
    //   650: ldc_w 1253
    //   653: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   656: ifne +14 -> 670
    //   659: aload 5
    //   661: ldc_w 1255
    //   664: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   667: ifeq +14 -> 681
    //   670: aload 5
    //   672: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   675: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   678: putstatic 1061	com/igexin/push/config/l:m	Z
    //   681: aload_1
    //   682: ldc_w 1301
    //   685: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   688: ifeq +45 -> 733
    //   691: aload_1
    //   692: ldc_w 1301
    //   695: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   698: astore 5
    //   700: aload 5
    //   702: ldc_w 1253
    //   705: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   708: ifne +14 -> 722
    //   711: aload 5
    //   713: ldc_w 1255
    //   716: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   719: ifeq +14 -> 733
    //   722: aload 5
    //   724: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   727: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   730: putstatic 1304	com/igexin/push/config/l:q	Z
    //   733: aload_1
    //   734: ldc_w 1306
    //   737: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   740: ifeq +45 -> 785
    //   743: aload_1
    //   744: ldc_w 1306
    //   747: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   750: astore 5
    //   752: aload 5
    //   754: ldc_w 1253
    //   757: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   760: ifne +14 -> 774
    //   763: aload 5
    //   765: ldc_w 1255
    //   768: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   771: ifeq +14 -> 785
    //   774: aload 5
    //   776: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   779: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   782: putstatic 1309	com/igexin/push/config/l:r	Z
    //   785: aload_1
    //   786: ldc_w 1311
    //   789: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   792: ifeq +45 -> 837
    //   795: aload_1
    //   796: ldc_w 1311
    //   799: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   802: astore 5
    //   804: aload 5
    //   806: ldc_w 1253
    //   809: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   812: ifne +14 -> 826
    //   815: aload 5
    //   817: ldc_w 1255
    //   820: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   823: ifeq +14 -> 837
    //   826: aload 5
    //   828: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   831: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   834: putstatic 1069	com/igexin/push/config/l:s	Z
    //   837: aload_1
    //   838: ldc_w 1313
    //   841: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   844: ifeq +13 -> 857
    //   847: aload_1
    //   848: ldc_w 1313
    //   851: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   854: putstatic 692	com/igexin/push/config/l:u	Ljava/lang/String;
    //   857: aload_1
    //   858: ldc_w 1315
    //   861: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   864: ifeq +13 -> 877
    //   867: aload_1
    //   868: ldc_w 1315
    //   871: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   874: putstatic 703	com/igexin/push/config/l:v	Ljava/lang/String;
    //   877: aload_1
    //   878: ldc_w 1317
    //   881: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   884: ifeq +45 -> 929
    //   887: aload_1
    //   888: ldc_w 1317
    //   891: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   894: astore 5
    //   896: aload 5
    //   898: ldc_w 1253
    //   901: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   904: ifne +14 -> 918
    //   907: aload 5
    //   909: ldc_w 1255
    //   912: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   915: ifeq +14 -> 929
    //   918: aload 5
    //   920: invokestatic 1260	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   923: invokevirtual 1263	java/lang/Boolean:booleanValue	()Z
    //   926: putstatic 1320	com/igexin/push/config/l:w	Z
    //   929: aload_1
    //   930: ldc_w 1322
    //   933: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   936: ifeq +119 -> 1055
    //   939: aload_1
    //   940: ldc_w 1322
    //   943: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   946: astore_1
    //   947: aload_1
    //   948: ifnull +107 -> 1055
    //   951: ldc_w 1007
    //   954: aload_1
    //   955: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   958: ifne +97 -> 1055
    //   961: new 241	org/json/JSONObject
    //   964: dup
    //   965: aload_1
    //   966: invokespecial 251	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   969: astore_1
    //   970: aload_1
    //   971: ldc_w 728
    //   974: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   977: ifeq +78 -> 1055
    //   980: aload_1
    //   981: ldc_w 728
    //   984: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   987: astore 5
    //   989: getstatic 1325	com/igexin/push/config/l:t	Lcom/igexin/push/core/bean/f;
    //   992: ifnull +87 -> 1079
    //   995: aload 5
    //   997: getstatic 1325	com/igexin/push/config/l:t	Lcom/igexin/push/core/bean/f;
    //   1000: invokevirtual 790	com/igexin/push/core/bean/f:a	()Ljava/lang/String;
    //   1003: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1006: ifeq +73 -> 1079
    //   1009: iload_2
    //   1010: ifeq +45 -> 1055
    //   1013: aload_0
    //   1014: aload_1
    //   1015: invokevirtual 1327	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;)Lcom/igexin/push/core/bean/f;
    //   1018: astore_1
    //   1019: aload_1
    //   1020: ifnull +35 -> 1055
    //   1023: new 1329	android/os/Message
    //   1026: dup
    //   1027: invokespecial 1330	android/os/Message:<init>	()V
    //   1030: astore 5
    //   1032: aload 5
    //   1034: getstatic 1332	com/igexin/push/core/a:i	I
    //   1037: putfield 1335	android/os/Message:what	I
    //   1040: aload 5
    //   1042: aload_1
    //   1043: putfield 1338	android/os/Message:obj	Ljava/lang/Object;
    //   1046: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   1049: aload 5
    //   1051: invokevirtual 1341	com/igexin/push/core/f:a	(Landroid/os/Message;)Z
    //   1054: pop
    //   1055: invokestatic 981	com/igexin/push/config/a:a	()Lcom/igexin/push/config/a;
    //   1058: invokevirtual 1343	com/igexin/push/config/a:f	()V
    //   1061: return
    //   1062: astore_1
    //   1063: aload_0
    //   1064: aload_1
    //   1065: invokevirtual 1344	java/lang/Exception:toString	()Ljava/lang/String;
    //   1068: invokevirtual 1345	com/igexin/push/core/a/e:e	(Ljava/lang/String;)V
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
      com.igexin.a.a.c.a.a("disconnected|network");
      com.igexin.push.core.i.a().a(com.igexin.push.core.k.c);
      w.d();
      if (((paramD.N instanceof ClosedChannelException)) || ((paramD.N instanceof SocketTimeoutException)) || ((paramD.N instanceof UnknownHostException)) || ((paramD.N instanceof UnresolvedAddressException)) || ((paramD.N instanceof UnknownServiceException))) {
        w.a();
      }
      if ((!com.igexin.push.core.g.j) || (!com.igexin.push.core.g.k)) {
        if (com.igexin.push.core.g.m == true)
        {
          com.igexin.push.core.g.m = false;
          m();
        }
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
      continue;
      com.igexin.a.a.c.a.a("disconnected|user");
      w.d();
      if (com.igexin.push.core.g.m == true)
      {
        com.igexin.push.core.g.m = false;
        m();
      }
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
    do
    {
      do
      {
        do
        {
          return true;
          if (!(paramObject instanceof com.igexin.a.a.b.a.a.b)) {
            break;
          }
        } while (com.igexin.push.core.g.m != true);
        com.igexin.push.core.g.m = false;
        m();
        return true;
      } while ((paramObject instanceof com.igexin.a.a.b.a.a.a));
      if ((paramObject instanceof com.igexin.push.c.b.a))
      {
        localJ.c(false);
        return true;
      }
    } while (!(paramObject instanceof com.igexin.push.c.b.b));
    localJ.c(true);
    return true;
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
        break label303;
      }
      paramJSONObject = ((JSONObject)localJSONArray.get(i)).getString("type");
      if (paramJSONObject == null) {
        break label296;
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
        break label296;
      }
      return false;
    }
    catch (JSONException paramJSONObject)
    {
      JSONArray localJSONArray;
      Object localObject;
      label113:
      JSONObject localJSONObject;
      String str;
      Iterator localIterator;
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
          break label287;
        }
        localObject = (com.igexin.push.core.a.a.a)b.get(str);
        if (localObject == null) {
          break label287;
        }
        localObject = ((com.igexin.push.core.a.a.a)localObject).a(localJSONObject);
        paramJSONObject = (JSONObject)localObject;
        if (localObject == null) {
          break label308;
        }
        ((BaseAction)localObject).setSupportExt(false);
        paramJSONObject = (JSONObject)localObject;
      }
    }
    label287:
    label296:
    label303:
    label308:
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
    //   1: ldc_w 962
    //   4: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   7: ifeq +558 -> 565
    //   10: aload_1
    //   11: ldc_w 962
    //   14: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   17: ldc_w 1449
    //   20: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   23: ifeq +542 -> 565
    //   26: aload_1
    //   27: ldc -3
    //   29: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   32: astore 10
    //   34: aload_1
    //   35: ldc_w 259
    //   38: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   41: astore 8
    //   43: aload_1
    //   44: ldc_w 261
    //   47: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   50: astore 5
    //   52: aload_1
    //   53: ldc_w 263
    //   56: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   59: astore 6
    //   61: aload_1
    //   62: ldc_w 265
    //   65: invokevirtual 257	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   68: astore 11
    //   70: aload_1
    //   71: ldc_w 1416
    //   74: invokevirtual 735	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   77: astore 9
    //   79: new 535	java/lang/StringBuilder
    //   82: dup
    //   83: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   86: ldc_w 1451
    //   89: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: aload 6
    //   94: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: ldc_w 875
    //   100: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: aload 5
    //   105: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: ldc_w 875
    //   111: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload 8
    //   116: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc_w 875
    //   122: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: iload_3
    //   126: invokevirtual 947	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   129: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 130	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   135: aload 8
    //   137: ifnull +428 -> 565
    //   140: aload 10
    //   142: ifnull +423 -> 565
    //   145: aload 5
    //   147: ifnull +418 -> 565
    //   150: aload 6
    //   152: ifnull +413 -> 565
    //   155: aload 9
    //   157: ifnull +408 -> 565
    //   160: aload 8
    //   162: getstatic 942	com/igexin/push/core/g:a	Ljava/lang/String;
    //   165: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   168: ifeq +397 -> 565
    //   171: new 273	com/igexin/push/core/bean/PushTaskBean
    //   174: dup
    //   175: invokespecial 274	com/igexin/push/core/bean/PushTaskBean:<init>	()V
    //   178: astore 7
    //   180: aload 7
    //   182: aload 8
    //   184: invokevirtual 277	com/igexin/push/core/bean/PushTaskBean:setAppid	(Ljava/lang/String;)V
    //   187: aload 7
    //   189: aload 5
    //   191: invokevirtual 280	com/igexin/push/core/bean/PushTaskBean:setMessageId	(Ljava/lang/String;)V
    //   194: aload 7
    //   196: aload 6
    //   198: invokevirtual 283	com/igexin/push/core/bean/PushTaskBean:setTaskId	(Ljava/lang/String;)V
    //   201: aload 7
    //   203: aload 10
    //   205: invokevirtual 286	com/igexin/push/core/bean/PushTaskBean:setId	(Ljava/lang/String;)V
    //   208: aload 7
    //   210: aload 11
    //   212: invokevirtual 289	com/igexin/push/core/bean/PushTaskBean:setAppKey	(Ljava/lang/String;)V
    //   215: aload 7
    //   217: iconst_1
    //   218: invokevirtual 293	com/igexin/push/core/bean/PushTaskBean:setCurrentActionid	(I)V
    //   221: aload_1
    //   222: ldc_w 302
    //   225: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   228: ifeq +15 -> 243
    //   231: aload 7
    //   233: aload_1
    //   234: ldc_w 302
    //   237: invokevirtual 309	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   240: invokevirtual 312	com/igexin/push/core/bean/PushTaskBean:setCDNType	(Z)V
    //   243: invokestatic 268	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   246: aload 6
    //   248: aload 5
    //   250: invokevirtual 271	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   253: astore 10
    //   255: iload_3
    //   256: ifeq +27 -> 283
    //   259: invokestatic 268	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   262: aload 7
    //   264: ldc -37
    //   266: invokevirtual 1081	com/igexin/push/core/a/e:a	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   269: invokestatic 268	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   272: invokestatic 183	java/lang/System:currentTimeMillis	()J
    //   275: invokevirtual 1453	com/igexin/push/core/a/e:a	(J)Z
    //   278: ifeq +5 -> 283
    //   281: iconst_1
    //   282: ireturn
    //   283: new 332	android/content/ContentValues
    //   286: dup
    //   287: invokespecial 333	android/content/ContentValues:<init>	()V
    //   290: astore 11
    //   292: aload 11
    //   294: ldc_w 261
    //   297: aload 5
    //   299: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: aload 11
    //   304: ldc_w 263
    //   307: aload 6
    //   309: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   312: aload 11
    //   314: ldc_w 259
    //   317: aload 8
    //   319: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   322: aload 11
    //   324: ldc_w 548
    //   327: new 535	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   334: ldc_w 1455
    //   337: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: aload 10
    //   342: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   345: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   348: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   351: aload 11
    //   353: ldc -25
    //   355: aload_1
    //   356: invokevirtual 852	org/json/JSONObject:toString	()Ljava/lang/String;
    //   359: invokevirtual 643	java/lang/String:getBytes	()[B
    //   362: invokestatic 1457	com/igexin/a/b/a:b	([B)[B
    //   365: invokevirtual 1459	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   368: aload 11
    //   370: ldc_w 550
    //   373: invokestatic 183	java/lang/System:currentTimeMillis	()J
    //   376: invokestatic 555	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   379: invokevirtual 558	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   382: aload_2
    //   383: ifnull +18 -> 401
    //   386: aload 11
    //   388: ldc_w 1461
    //   391: aload_2
    //   392: invokevirtual 1459	android/content/ContentValues:put	(Ljava/lang/String;[B)V
    //   395: aload 7
    //   397: aload_2
    //   398: invokevirtual 1464	com/igexin/push/core/bean/PushTaskBean:setMsgExtra	([B)V
    //   401: aload 9
    //   403: invokevirtual 738	org/json/JSONArray:length	()I
    //   406: ifle +21 -> 427
    //   409: invokestatic 268	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   412: aload_1
    //   413: aload 7
    //   415: invokevirtual 1466	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)Z
    //   418: istore 4
    //   420: iload 4
    //   422: ifne +5 -> 427
    //   425: iconst_1
    //   426: ireturn
    //   427: iload_3
    //   428: ifeq +225 -> 653
    //   431: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   434: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   437: ldc -43
    //   439: iconst_1
    //   440: anewarray 215	java/lang/String
    //   443: dup
    //   444: iconst_0
    //   445: ldc_w 263
    //   448: aastore
    //   449: iconst_1
    //   450: anewarray 215	java/lang/String
    //   453: dup
    //   454: iconst_0
    //   455: aload 6
    //   457: aastore
    //   458: aconst_null
    //   459: aconst_null
    //   460: invokevirtual 224	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   463: astore_2
    //   464: aload_2
    //   465: ifnull +90 -> 555
    //   468: aload_2
    //   469: invokeinterface 561 1 0
    //   474: ifne +167 -> 641
    //   477: aload_1
    //   478: ldc_w 314
    //   481: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   484: ifeq +83 -> 567
    //   487: aload_0
    //   488: aload_1
    //   489: aload 7
    //   491: invokespecial 317	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   494: aload 7
    //   496: getstatic 1468	com/igexin/push/core/a:k	I
    //   499: invokevirtual 300	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   502: aload 11
    //   504: ldc -39
    //   506: getstatic 1468	com/igexin/push/core/a:k	I
    //   509: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   512: invokevirtual 336	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   515: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   518: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   521: ldc -43
    //   523: aload 11
    //   525: invokevirtual 564	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   528: getstatic 201	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   531: aload 10
    //   533: aload 7
    //   535: invokeinterface 35 3 0
    //   540: pop
    //   541: aload_1
    //   542: ldc_w 314
    //   545: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   548: ifeq +61 -> 609
    //   551: aload_0
    //   552: invokevirtual 177	com/igexin/push/core/a/e:t	()V
    //   555: aload_2
    //   556: ifnull +9 -> 565
    //   559: aload_2
    //   560: invokeinterface 323 1 0
    //   565: iconst_1
    //   566: ireturn
    //   567: aload 7
    //   569: getstatic 452	com/igexin/push/core/a:l	I
    //   572: invokevirtual 300	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   575: aload 11
    //   577: ldc -39
    //   579: getstatic 452	com/igexin/push/core/a:l	I
    //   582: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   585: invokevirtual 336	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   588: goto -73 -> 515
    //   591: astore_1
    //   592: aload_2
    //   593: ifnull -28 -> 565
    //   596: aload_2
    //   597: invokeinterface 323 1 0
    //   602: goto -37 -> 565
    //   605: astore_1
    //   606: goto -41 -> 565
    //   609: invokestatic 268	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   612: aload 6
    //   614: aload 5
    //   616: getstatic 942	com/igexin/push/core/g:a	Ljava/lang/String;
    //   619: getstatic 566	com/igexin/push/core/g:e	Ljava/lang/String;
    //   622: invokevirtual 1470	com/igexin/push/core/a/e:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   625: goto -70 -> 555
    //   628: astore_1
    //   629: aload_2
    //   630: ifnull +9 -> 639
    //   633: aload_2
    //   634: invokeinterface 323 1 0
    //   639: aload_1
    //   640: athrow
    //   641: aload_2
    //   642: ifnull +64 -> 706
    //   645: aload_2
    //   646: invokeinterface 323 1 0
    //   651: iconst_1
    //   652: ireturn
    //   653: aload_1
    //   654: ldc_w 314
    //   657: invokevirtual 306	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   660: ifeq +10 -> 670
    //   663: aload_0
    //   664: aload_1
    //   665: aload 7
    //   667: invokespecial 317	com/igexin/push/core/a/e:b	(Lorg/json/JSONObject;Lcom/igexin/push/core/bean/PushTaskBean;)V
    //   670: aload 7
    //   672: getstatic 452	com/igexin/push/core/a:l	I
    //   675: invokevirtual 300	com/igexin/push/core/bean/PushTaskBean:setStatus	(I)V
    //   678: getstatic 201	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   681: aload 10
    //   683: aload 7
    //   685: invokeinterface 35 3 0
    //   690: pop
    //   691: goto -126 -> 565
    //   694: astore_1
    //   695: aconst_null
    //   696: astore_2
    //   697: goto -68 -> 629
    //   700: astore_1
    //   701: aconst_null
    //   702: astore_2
    //   703: goto -111 -> 592
    //   706: iconst_1
    //   707: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	708	0	this	e
    //   0	708	1	paramJSONObject	JSONObject
    //   0	708	2	paramArrayOfByte	byte[]
    //   0	708	3	paramBoolean	boolean
    //   418	3	4	bool	boolean
    //   50	565	5	str1	String
    //   59	554	6	str2	String
    //   178	506	7	localPushTaskBean	PushTaskBean
    //   41	277	8	str3	String
    //   77	325	9	localJSONArray	JSONArray
    //   32	650	10	str4	String
    //   68	508	11	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   468	515	591	java/lang/Exception
    //   515	555	591	java/lang/Exception
    //   567	588	591	java/lang/Exception
    //   609	625	591	java/lang/Exception
    //   0	135	605	java/lang/Exception
    //   160	243	605	java/lang/Exception
    //   243	255	605	java/lang/Exception
    //   259	281	605	java/lang/Exception
    //   283	382	605	java/lang/Exception
    //   386	401	605	java/lang/Exception
    //   401	420	605	java/lang/Exception
    //   559	565	605	java/lang/Exception
    //   596	602	605	java/lang/Exception
    //   633	639	605	java/lang/Exception
    //   639	641	605	java/lang/Exception
    //   645	651	605	java/lang/Exception
    //   653	670	605	java/lang/Exception
    //   670	691	605	java/lang/Exception
    //   468	515	628	finally
    //   515	555	628	finally
    //   567	588	628	finally
    //   609	625	628	finally
    //   431	464	694	finally
    //   431	464	700	java/lang/Exception
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
      com.igexin.a.a.c.a.a("cdnfeedback|" + paramPushTaskBean.getTaskId() + "|" + paramPushTaskBean.getMessageId() + "|" + paramString);
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
      com.igexin.a.a.c.a.a("settag");
      return;
    }
    catch (Exception paramString) {}
  }
  
  /* Error */
  public boolean b(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 535	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   7: aload_1
    //   8: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   11: ldc_w 860
    //   14: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: aload_2
    //   18: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore 4
    //   26: getstatic 201	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   29: aload 4
    //   31: invokeinterface 447 2 0
    //   36: checkcast 273	com/igexin/push/core/bean/PushTaskBean
    //   39: astore 4
    //   41: aload 4
    //   43: astore 6
    //   45: aload 4
    //   47: ifnonnull +266 -> 313
    //   50: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   53: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   56: ldc -43
    //   58: iconst_2
    //   59: anewarray 215	java/lang/String
    //   62: dup
    //   63: iconst_0
    //   64: ldc_w 263
    //   67: aastore
    //   68: dup
    //   69: iconst_1
    //   70: ldc_w 261
    //   73: aastore
    //   74: iconst_2
    //   75: anewarray 215	java/lang/String
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
    //   88: invokevirtual 224	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   91: astore 5
    //   93: aload 5
    //   95: ifnull +184 -> 279
    //   98: aload 4
    //   100: astore 6
    //   102: aload 5
    //   104: invokeinterface 561 1 0
    //   109: ifle +156 -> 265
    //   112: aload 4
    //   114: astore 6
    //   116: aload 5
    //   118: invokeinterface 229 1 0
    //   123: ifeq +170 -> 293
    //   126: aload 4
    //   128: astore 6
    //   130: aload 5
    //   132: aload 5
    //   134: ldc -25
    //   136: invokeinterface 892 2 0
    //   141: invokeinterface 239 2 0
    //   146: astore 7
    //   148: aload 4
    //   150: astore 6
    //   152: aload 5
    //   154: aload 5
    //   156: ldc_w 1461
    //   159: invokeinterface 892 2 0
    //   164: invokeinterface 239 2 0
    //   169: astore 8
    //   171: aload 4
    //   173: astore 6
    //   175: aload_0
    //   176: new 241	org/json/JSONObject
    //   179: dup
    //   180: new 215	java/lang/String
    //   183: dup
    //   184: aload 7
    //   186: invokestatic 246	com/igexin/a/b/a:c	([B)[B
    //   189: invokespecial 249	java/lang/String:<init>	([B)V
    //   192: invokespecial 251	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   195: aload 8
    //   197: iconst_0
    //   198: invokevirtual 1540	com/igexin/push/core/a/e:a	(Lorg/json/JSONObject;[BZ)Z
    //   201: pop
    //   202: aload 4
    //   204: astore 6
    //   206: getstatic 201	com/igexin/push/core/g:ai	Ljava/util/Map;
    //   209: new 535	java/lang/StringBuilder
    //   212: dup
    //   213: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   216: aload_1
    //   217: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: ldc_w 860
    //   223: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: aload_2
    //   227: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokeinterface 447 2 0
    //   238: checkcast 273	com/igexin/push/core/bean/PushTaskBean
    //   241: astore 4
    //   243: aload 4
    //   245: ifnonnull +17 -> 262
    //   248: aload 5
    //   250: ifnull +10 -> 260
    //   253: aload 5
    //   255: invokeinterface 323 1 0
    //   260: iconst_0
    //   261: ireturn
    //   262: goto -150 -> 112
    //   265: aload 5
    //   267: ifnull +10 -> 277
    //   270: aload 5
    //   272: invokeinterface 323 1 0
    //   277: iconst_0
    //   278: ireturn
    //   279: aload 5
    //   281: ifnull +10 -> 291
    //   284: aload 5
    //   286: invokeinterface 323 1 0
    //   291: iconst_0
    //   292: ireturn
    //   293: aload 4
    //   295: astore 6
    //   297: aload 5
    //   299: ifnull +14 -> 313
    //   302: aload 5
    //   304: invokeinterface 323 1 0
    //   309: aload 4
    //   311: astore 6
    //   313: invokestatic 268	com/igexin/push/core/a/e:a	()Lcom/igexin/push/core/a/e;
    //   316: aload 6
    //   318: aload_3
    //   319: invokevirtual 1081	com/igexin/push/core/a/e:a	(Lcom/igexin/push/core/bean/PushTaskBean;Ljava/lang/String;)V
    //   322: aload 6
    //   324: aload_3
    //   325: invokevirtual 1544	com/igexin/push/core/bean/PushTaskBean:getBaseAction	(Ljava/lang/String;)Lcom/igexin/push/core/bean/BaseAction;
    //   328: astore_1
    //   329: aload_1
    //   330: ifnonnull +47 -> 377
    //   333: iconst_0
    //   334: ireturn
    //   335: astore_1
    //   336: aconst_null
    //   337: astore_1
    //   338: aload 4
    //   340: astore 6
    //   342: aload_1
    //   343: ifnull -30 -> 313
    //   346: aload_1
    //   347: invokeinterface 323 1 0
    //   352: aload 4
    //   354: astore 6
    //   356: goto -43 -> 313
    //   359: astore_1
    //   360: aconst_null
    //   361: astore 5
    //   363: aload 5
    //   365: ifnull +10 -> 375
    //   368: aload 5
    //   370: invokeinterface 323 1 0
    //   375: aload_1
    //   376: athrow
    //   377: aload_1
    //   378: invokevirtual 1547	com/igexin/push/core/bean/BaseAction:isSupportExt	()Z
    //   381: ifeq +47 -> 428
    //   384: invokestatic 1421	com/igexin/push/extension/a:a	()Lcom/igexin/push/extension/a;
    //   387: invokevirtual 1424	com/igexin/push/extension/a:c	()Ljava/util/List;
    //   390: invokeinterface 1425 1 0
    //   395: astore_2
    //   396: aload_2
    //   397: invokeinterface 814 1 0
    //   402: ifeq +26 -> 428
    //   405: aload_2
    //   406: invokeinterface 818 1 0
    //   411: checkcast 1427	com/igexin/push/extension/stub/IPushExtension
    //   414: aload 6
    //   416: aload_1
    //   417: invokeinterface 1551 3 0
    //   422: iconst_1
    //   423: if_icmpne -27 -> 396
    //   426: iconst_1
    //   427: ireturn
    //   428: getstatic 52	com/igexin/push/core/a/e:b	Ljava/util/Map;
    //   431: aload_1
    //   432: invokevirtual 1482	com/igexin/push/core/bean/BaseAction:getType	()Ljava/lang/String;
    //   435: invokeinterface 447 2 0
    //   440: checkcast 1436	com/igexin/push/core/a/a/a
    //   443: astore_2
    //   444: aload_2
    //   445: ifnull +11 -> 456
    //   448: aload 6
    //   450: invokevirtual 1554	com/igexin/push/core/bean/PushTaskBean:isStop	()Z
    //   453: ifeq +5 -> 458
    //   456: iconst_0
    //   457: ireturn
    //   458: aload_2
    //   459: aload 6
    //   461: aload_1
    //   462: invokeinterface 1556 3 0
    //   467: ireturn
    //   468: astore_1
    //   469: goto -106 -> 363
    //   472: astore_1
    //   473: aload 5
    //   475: astore_1
    //   476: aload 6
    //   478: astore 4
    //   480: goto -142 -> 338
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	483	0	this	e
    //   0	483	1	paramString1	String
    //   0	483	2	paramString2	String
    //   0	483	3	paramString3	String
    //   24	455	4	localObject1	Object
    //   91	383	5	localCursor	Cursor
    //   43	434	6	localObject2	Object
    //   146	39	7	arrayOfByte1	byte[]
    //   169	27	8	arrayOfByte2	byte[]
    // Exception table:
    //   from	to	target	type
    //   50	93	335	java/lang/Exception
    //   50	93	359	finally
    //   102	112	468	finally
    //   116	126	468	finally
    //   130	148	468	finally
    //   152	171	468	finally
    //   175	202	468	finally
    //   206	243	468	finally
    //   102	112	472	java/lang/Exception
    //   116	126	472	java/lang/Exception
    //   130	148	472	java/lang/Exception
    //   152	171	472	java/lang/Exception
    //   175	202	472	java/lang/Exception
    //   206	243	472	java/lang/Exception
  }
  
  public boolean b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    com.igexin.a.a.c.a.a("startapp|broadcastPayload");
    Intent localIntent = new Intent();
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
        com.igexin.a.a.c.a.a("startapp|broadcast|payload is " + new String(paramString1));
      }
      for (;;)
      {
        localBundle.putByteArray("payload", paramString1);
        localIntent.putExtras(localBundle);
        try
        {
          com.igexin.a.a.c.a.a("startapp|broadcast|" + paramString3 + "|" + new String(paramString1, "utf-8"));
          com.igexin.push.core.g.g.sendBroadcast(localIntent);
          return true;
        }
        catch (Exception paramString1)
        {
          com.igexin.a.a.c.a.a("startapp|broadcast|error|" + paramString1.toString());
          return false;
        }
        paramString1 = a(paramString1, paramString2);
        paramString1 = (PushTaskBean)com.igexin.push.core.g.ai.get(paramString1);
        if (paramString1 == null) {
          break label316;
        }
        paramString1 = paramString1.getMsgExtra();
        break;
        com.igexin.a.a.c.a.a("startapp|broadcast|payload is empty!");
      }
      label316:
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
                D();
              }
            }
            catch (Exception paramIntent)
            {
              com.igexin.a.a.c.a.a("CoreAction" + paramIntent.toString());
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
        k(paramIntent.getDataString());
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(str))
      {
        j(paramIntent.getDataString());
        return;
      }
      if ("com.igexin.sdk.action.core.clearmsg".equals(str))
      {
        com.igexin.push.core.f.a().k().a("message", null);
        return;
      }
    } while ((!"android.net.wifi.WIFI_STATE_CHANGED".equals(str)) || (paramIntent.getIntExtra("wifi_state", 0) != 3));
    com.igexin.a.a.c.a.a("CoreAction", "wifi enable !!!");
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
      com.igexin.a.a.c.a.a("-> CoreRuntimeInfo.opAliasTimes:" + com.igexin.push.core.g.U);
      if (com.igexin.push.core.g.U < 100)
      {
        com.igexin.a.a.c.a.a("requestService bindAlias HttpTask ...");
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
    //   1: istore_3
    //   2: getstatic 944	com/igexin/push/core/g:j	Z
    //   5: ifeq +25 -> 30
    //   8: getstatic 1387	com/igexin/push/core/g:k	Z
    //   11: ifeq +19 -> 30
    //   14: aload_0
    //   15: invokestatic 183	java/lang/System:currentTimeMillis	()J
    //   18: invokevirtual 1453	com/igexin/push/core/a/e:a	(J)Z
    //   21: ifne +9 -> 30
    //   24: invokestatic 1719	com/igexin/push/f/a:b	()Z
    //   27: ifne +7 -> 34
    //   30: iconst_m1
    //   31: istore_2
    //   32: iload_2
    //   33: ireturn
    //   34: getstatic 1720	com/igexin/push/core/g:l	Z
    //   37: ifeq +45 -> 82
    //   40: getstatic 1720	com/igexin/push/core/g:l	Z
    //   43: ifne +264 -> 307
    //   46: iconst_1
    //   47: istore 4
    //   49: iload 4
    //   51: putstatic 1720	com/igexin/push/core/g:l	Z
    //   54: new 1722	java/util/Random
    //   57: dup
    //   58: invokespecial 1723	java/util/Random:<init>	()V
    //   61: invokevirtual 1726	java/util/Random:nextInt	()I
    //   64: bipush 24
    //   66: irem
    //   67: invokestatic 1731	java/lang/Math:abs	(I)I
    //   70: i2l
    //   71: ldc2_w 1732
    //   74: lmul
    //   75: invokestatic 183	java/lang/System:currentTimeMillis	()J
    //   78: ladd
    //   79: putstatic 1736	com/igexin/push/core/g:L	J
    //   82: invokestatic 1737	com/igexin/push/core/c/w:b	()V
    //   85: getstatic 1591	com/igexin/push/core/g:r	J
    //   88: lconst_0
    //   89: lcmp
    //   90: ifne +223 -> 313
    //   93: ldc_w 1739
    //   96: invokestatic 130	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   99: new 1741	com/igexin/push/c/c/f
    //   102: dup
    //   103: getstatic 1742	com/igexin/push/core/g:u	Ljava/lang/String;
    //   106: getstatic 1743	com/igexin/push/core/g:v	Ljava/lang/String;
    //   109: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   112: getstatic 942	com/igexin/push/core/g:a	Ljava/lang/String;
    //   115: invokespecial 1745	com/igexin/push/c/c/f:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   118: astore 5
    //   120: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   123: invokevirtual 164	com/igexin/push/core/f:g	()Lcom/igexin/push/d/j;
    //   126: new 535	java/lang/StringBuilder
    //   129: dup
    //   130: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   133: ldc_w 1747
    //   136: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: getstatic 877	com/igexin/push/core/g:A	Ljava/lang/String;
    //   142: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: aload 5
    //   150: invokevirtual 1117	com/igexin/push/d/j:a	(Ljava/lang/String;Lcom/igexin/push/c/c/e;)I
    //   153: ifge +584 -> 737
    //   156: iconst_0
    //   157: istore_1
    //   158: iload_3
    //   159: istore_2
    //   160: iload_1
    //   161: ifne -129 -> 32
    //   164: new 863	java/text/SimpleDateFormat
    //   167: dup
    //   168: ldc_w 1692
    //   171: invokespecial 866	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   174: new 868	java/util/Date
    //   177: dup
    //   178: invokespecial 869	java/util/Date:<init>	()V
    //   181: invokevirtual 873	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   184: astore 6
    //   186: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   189: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   192: ldc_w 883
    //   195: iconst_1
    //   196: anewarray 215	java/lang/String
    //   199: dup
    //   200: iconst_0
    //   201: ldc_w 885
    //   204: aastore
    //   205: iconst_1
    //   206: anewarray 215	java/lang/String
    //   209: dup
    //   210: iconst_0
    //   211: ldc_w 576
    //   214: aastore
    //   215: aconst_null
    //   216: aconst_null
    //   217: invokevirtual 224	com/igexin/push/b/b:a	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   220: astore 5
    //   222: aload 5
    //   224: ifnull +69 -> 293
    //   227: aload 5
    //   229: invokeinterface 561 1 0
    //   234: ifne +493 -> 727
    //   237: new 332	android/content/ContentValues
    //   240: dup
    //   241: invokespecial 333	android/content/ContentValues:<init>	()V
    //   244: astore 7
    //   246: aload 7
    //   248: ldc_w 896
    //   251: iconst_1
    //   252: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   255: invokevirtual 336	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   258: aload 7
    //   260: ldc_w 906
    //   263: aload 6
    //   265: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   268: aload 7
    //   270: ldc_w 885
    //   273: ldc_w 576
    //   276: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   279: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   282: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   285: ldc_w 883
    //   288: aload 7
    //   290: invokevirtual 564	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   293: aload 5
    //   295: ifnull +10 -> 305
    //   298: aload 5
    //   300: invokeinterface 323 1 0
    //   305: iconst_0
    //   306: ireturn
    //   307: iconst_0
    //   308: istore 4
    //   310: goto -261 -> 49
    //   313: aload_0
    //   314: invokevirtual 1749	com/igexin/push/core/a/e:c	()Lcom/igexin/push/c/c/i;
    //   317: astore 5
    //   319: new 535	java/lang/StringBuilder
    //   322: dup
    //   323: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   326: ldc_w 1751
    //   329: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: aload 5
    //   334: getfield 1592	com/igexin/push/c/c/i:a	J
    //   337: invokevirtual 950	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   340: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: invokestatic 130	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   346: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   349: invokevirtual 164	com/igexin/push/core/f:g	()Lcom/igexin/push/d/j;
    //   352: new 535	java/lang/StringBuilder
    //   355: dup
    //   356: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   359: ldc_w 1753
    //   362: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   365: getstatic 1591	com/igexin/push/core/g:r	J
    //   368: invokestatic 1220	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   371: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   377: aload 5
    //   379: invokevirtual 1117	com/igexin/push/d/j:a	(Ljava/lang/String;Lcom/igexin/push/c/c/e;)I
    //   382: ifge +350 -> 732
    //   385: iconst_0
    //   386: istore_2
    //   387: iload_2
    //   388: istore_1
    //   389: iload_2
    //   390: ifeq -232 -> 158
    //   393: new 535	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 536	java/lang/StringBuilder:<init>	()V
    //   400: ldc_w 1755
    //   403: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: getstatic 940	com/igexin/push/core/g:s	Ljava/lang/String;
    //   409: invokevirtual 542	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: invokevirtual 543	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   415: invokestatic 130	com/igexin/a/a/c/a:a	(Ljava/lang/String;)V
    //   418: iload_2
    //   419: istore_1
    //   420: goto -262 -> 158
    //   423: aload 5
    //   425: invokeinterface 229 1 0
    //   430: ifeq -137 -> 293
    //   433: aload 5
    //   435: aload 5
    //   437: ldc_w 906
    //   440: invokeinterface 892 2 0
    //   445: invokeinterface 908 2 0
    //   450: astore 8
    //   452: aload 5
    //   454: aload 5
    //   456: ldc -3
    //   458: invokeinterface 892 2 0
    //   463: invokeinterface 908 2 0
    //   468: astore 7
    //   470: aload 6
    //   472: aload 8
    //   474: invokevirtual 522	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   477: ifeq +96 -> 573
    //   480: aload 5
    //   482: aload 5
    //   484: ldc_w 896
    //   487: invokeinterface 892 2 0
    //   492: invokeinterface 297 2 0
    //   497: istore_1
    //   498: new 332	android/content/ContentValues
    //   501: dup
    //   502: invokespecial 333	android/content/ContentValues:<init>	()V
    //   505: astore 8
    //   507: aload 8
    //   509: ldc_w 896
    //   512: iload_1
    //   513: iconst_1
    //   514: iadd
    //   515: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   518: invokevirtual 336	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   521: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   524: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   527: ldc_w 883
    //   530: aload 8
    //   532: iconst_1
    //   533: anewarray 215	java/lang/String
    //   536: dup
    //   537: iconst_0
    //   538: ldc -3
    //   540: aastore
    //   541: iconst_1
    //   542: anewarray 215	java/lang/String
    //   545: dup
    //   546: iconst_0
    //   547: aload 7
    //   549: aastore
    //   550: invokevirtual 339	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;[Ljava/lang/String;[Ljava/lang/String;)V
    //   553: goto -130 -> 423
    //   556: astore 6
    //   558: aload 5
    //   560: ifnull -255 -> 305
    //   563: aload 5
    //   565: invokeinterface 323 1 0
    //   570: goto -265 -> 305
    //   573: new 332	android/content/ContentValues
    //   576: dup
    //   577: invokespecial 333	android/content/ContentValues:<init>	()V
    //   580: astore 8
    //   582: aload 8
    //   584: ldc_w 885
    //   587: ldc_w 887
    //   590: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   593: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   596: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   599: ldc_w 883
    //   602: aload 8
    //   604: iconst_1
    //   605: anewarray 215	java/lang/String
    //   608: dup
    //   609: iconst_0
    //   610: ldc -3
    //   612: aastore
    //   613: iconst_1
    //   614: anewarray 215	java/lang/String
    //   617: dup
    //   618: iconst_0
    //   619: aload 7
    //   621: aastore
    //   622: invokevirtual 339	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;[Ljava/lang/String;[Ljava/lang/String;)V
    //   625: new 332	android/content/ContentValues
    //   628: dup
    //   629: invokespecial 333	android/content/ContentValues:<init>	()V
    //   632: astore 7
    //   634: aload 7
    //   636: ldc_w 896
    //   639: iload_1
    //   640: iconst_1
    //   641: iadd
    //   642: invokestatic 26	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   645: invokevirtual 336	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   648: aload 7
    //   650: ldc_w 906
    //   653: aload 6
    //   655: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   658: aload 7
    //   660: ldc_w 885
    //   663: ldc_w 576
    //   666: invokevirtual 546	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   669: invokestatic 135	com/igexin/push/core/f:a	()Lcom/igexin/push/core/f;
    //   672: invokevirtual 211	com/igexin/push/core/f:k	()Lcom/igexin/push/b/b;
    //   675: ldc_w 883
    //   678: aload 7
    //   680: invokevirtual 564	com/igexin/push/b/b:a	(Ljava/lang/String;Landroid/content/ContentValues;)V
    //   683: goto -260 -> 423
    //   686: astore 7
    //   688: aload 5
    //   690: astore 6
    //   692: aload 7
    //   694: astore 5
    //   696: aload 6
    //   698: ifnull +10 -> 708
    //   701: aload 6
    //   703: invokeinterface 323 1 0
    //   708: aload 5
    //   710: athrow
    //   711: astore 5
    //   713: aconst_null
    //   714: astore 6
    //   716: goto -20 -> 696
    //   719: astore 5
    //   721: aconst_null
    //   722: astore 5
    //   724: goto -166 -> 558
    //   727: iconst_0
    //   728: istore_1
    //   729: goto -306 -> 423
    //   732: iconst_1
    //   733: istore_2
    //   734: goto -347 -> 387
    //   737: iconst_1
    //   738: istore_1
    //   739: goto -581 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	742	0	this	e
    //   157	582	1	i	int
    //   31	703	2	j	int
    //   1	158	3	k	int
    //   47	262	4	bool	boolean
    //   118	591	5	localObject1	Object
    //   711	1	5	localObject2	Object
    //   719	1	5	localException1	Exception
    //   722	1	5	localObject3	Object
    //   184	287	6	str	String
    //   556	98	6	localException2	Exception
    //   690	25	6	localObject4	Object
    //   244	435	7	localObject5	Object
    //   686	7	7	localObject6	Object
    //   450	153	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   227	293	556	java/lang/Exception
    //   423	553	556	java/lang/Exception
    //   573	683	556	java/lang/Exception
    //   227	293	686	finally
    //   423	553	686	finally
    //   573	683	686	finally
    //   164	222	711	finally
    //   164	222	719	java/lang/Exception
  }
  
  public void d(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (;;)
    {
      return;
      try
      {
        int j = paramIntent.getIntExtra("id", -1);
        boolean bool = paramIntent.getBooleanExtra("result", false);
        if (j == -1) {
          continue;
        }
        com.igexin.push.core.g.ar += 1;
        if (bool)
        {
          if (paramIntent.getBooleanExtra("isReload", false))
          {
            Process.killProcess(Process.myPid());
            return;
          }
          com.igexin.push.core.g.aq += 1;
          if (com.igexin.push.core.g.as == null) {
            break label486;
          }
        }
        label481:
        label486:
        for (Object localObject1 = com.igexin.push.core.g.as.b(); localObject1 != null; localObject1 = null)
        {
          int i;
          Object localObject2;
          if (com.igexin.push.config.l.t != null)
          {
            paramIntent = com.igexin.push.config.l.t.b();
            if (paramIntent == null) {
              break;
            }
            if (!paramIntent.containsKey(Integer.valueOf(j))) {
              break label481;
            }
            i = 1;
            localObject2 = (com.igexin.push.core.bean.e)paramIntent.get(Integer.valueOf(j));
            if (localObject2 != null)
            {
              localObject2 = new File(com.igexin.push.core.g.ad + "/" + ((com.igexin.push.core.bean.e)localObject2).c());
              if (((File)localObject2).exists()) {
                ((File)localObject2).delete();
              }
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
            File localFile = new File((String)localObject2);
            if (localFile.exists())
            {
              paramIntent.put(Integer.valueOf(j), localObject1);
              if (com.igexin.push.core.g.aq == com.igexin.push.core.g.ap) {
                com.igexin.push.config.l.t.a(com.igexin.push.core.g.as.a());
              }
              if ((i == 0) && (com.igexin.push.extension.a.a().a(com.igexin.push.core.g.g, (String)localObject2, ((com.igexin.push.core.bean.e)localObject1).d(), ((com.igexin.push.core.bean.e)localObject1).j(), ((com.igexin.push.core.bean.e)localObject1).c())))
              {
                com.igexin.a.a.c.a.a("CoreAction load " + ((com.igexin.push.core.bean.e)localObject1).d() + " success");
                ((com.igexin.push.core.bean.e)localObject1).b(System.currentTimeMillis());
                if (((com.igexin.push.core.bean.e)localObject1).g())
                {
                  localFile.delete();
                  paramIntent.remove(Integer.valueOf(j));
                }
              }
              com.igexin.push.config.a.a().g();
            }
            if ((com.igexin.push.core.g.ar != com.igexin.push.core.g.ap) || (!com.igexin.push.core.g.at)) {
              break;
            }
            com.igexin.a.a.c.a.a("CoreActiondown load ext success, restart service ############");
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
        return;
      }
      catch (Exception paramIntent) {}
    }
  }
  
  public void d(String paramString)
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
      if (com.igexin.push.core.g.U < 100)
      {
        com.igexin.push.core.g.T = l;
        com.igexin.push.core.c.f.a().a(com.igexin.push.core.g.U + 1);
        paramString = new com.igexin.push.e.a.c(new com.igexin.push.core.d.i(SDKUrlConfig.getAmpServiceUrl(), paramString));
        com.igexin.a.a.b.d.c().a(paramString, false, true);
      }
    }
  }
  
  public void e()
  {
    com.igexin.a.a.b.d.c().a(SDKUrlConfig.getCmAddress().replaceFirst("socket", "disConnect"), 0, null);
  }
  
  public void e(String paramString)
  {
    String str = a(true, 4);
    str = str + "2.5.0.0|sdkconfig-error|";
    paramString = (str + paramString).getBytes();
    paramString = new com.igexin.push.e.a.c(new com.igexin.push.core.d.j(SDKUrlConfig.getBiUploadServiceUrl(), paramString, 0, true));
    com.igexin.a.a.b.d.c().a(paramString, false, true);
  }
  
  public int f()
  {
    com.igexin.a.a.c.a.a("CoreAction send heart beat data ........");
    return com.igexin.push.core.f.a().g().a("H-" + com.igexin.push.core.g.s, new com.igexin.push.c.c.h());
  }
  
  public String f(String paramString)
  {
    if (com.igexin.push.core.g.c() == null) {
      return null;
    }
    return (String)com.igexin.push.core.g.c().get(paramString);
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
        com.igexin.a.a.c.a.a("freshral|" + localI.b());
      }
    }
  }
  
  public boolean g(String paramString)
  {
    Object localObject = com.igexin.push.core.g.g.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localObject = ((PackageManager)localObject).queryIntentActivities(localIntent, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
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
    com.igexin.a.a.c.a.a("deviceidReq");
  }
  
  public boolean h(String paramString)
  {
    Iterator localIterator = ((ActivityManager)com.igexin.push.core.g.g.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext()) {
      if (paramString.equals(((ActivityManager.RunningAppProcessInfo)localIterator.next()).processName)) {
        return true;
      }
    }
    return false;
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
      com.igexin.a.a.c.a.a("addphoneinfo");
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
  
  public void i(String paramString)
  {
    com.igexin.a.a.c.a.a("CoreActiondeprecated form sdk2.5.0.0, now is empty function #########");
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
    localIntent.setAction("com.igexin.sdk.action." + com.igexin.push.core.g.a);
    Bundle localBundle = new Bundle();
    localBundle.putInt("action", 10002);
    localBundle.putString("clientid", com.igexin.push.core.g.s);
    localIntent.putExtras(localBundle);
    com.igexin.a.a.c.a.a("broadcastClientid|" + com.igexin.push.core.g.s);
    com.igexin.push.core.f.a().a(localIntent);
    Log.d("PushService", "clientid is " + com.igexin.push.core.g.s);
  }
  
  public void m()
  {
    Intent localIntent = new Intent();
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
    //   0: new 624	java/io/File
    //   3: dup
    //   4: getstatic 627	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   7: invokespecial 628	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 631	java/io/File:exists	()Z
    //   13: ifeq +191 -> 204
    //   16: sipush 1024
    //   19: newarray byte
    //   21: astore_3
    //   22: new 1923	java/io/FileInputStream
    //   25: dup
    //   26: getstatic 627	com/igexin/push/core/g:aa	Ljava/lang/String;
    //   29: invokespecial 1924	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   32: astore_2
    //   33: new 1926	java/io/ByteArrayOutputStream
    //   36: dup
    //   37: invokespecial 1927	java/io/ByteArrayOutputStream:<init>	()V
    //   40: astore 4
    //   42: aload_2
    //   43: aload_3
    //   44: invokevirtual 1931	java/io/FileInputStream:read	([B)I
    //   47: istore_1
    //   48: iload_1
    //   49: iconst_m1
    //   50: if_icmpeq +40 -> 90
    //   53: aload 4
    //   55: aload_3
    //   56: iconst_0
    //   57: iload_1
    //   58: invokevirtual 1934	java/io/ByteArrayOutputStream:write	([BII)V
    //   61: goto -19 -> 42
    //   64: astore_3
    //   65: aload_2
    //   66: astore_3
    //   67: aload 4
    //   69: astore_2
    //   70: aload_3
    //   71: ifnull +7 -> 78
    //   74: aload_3
    //   75: invokevirtual 1935	java/io/FileInputStream:close	()V
    //   78: aload_2
    //   79: ifnull +125 -> 204
    //   82: aload_2
    //   83: invokevirtual 1936	java/io/ByteArrayOutputStream:close	()V
    //   86: aconst_null
    //   87: astore_2
    //   88: aload_2
    //   89: areturn
    //   90: new 215	java/lang/String
    //   93: dup
    //   94: aload 4
    //   96: invokevirtual 1939	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   99: invokespecial 249	java/lang/String:<init>	([B)V
    //   102: astore_3
    //   103: aload_2
    //   104: ifnull +7 -> 111
    //   107: aload_2
    //   108: invokevirtual 1935	java/io/FileInputStream:close	()V
    //   111: aload_3
    //   112: astore_2
    //   113: aload 4
    //   115: ifnull -27 -> 88
    //   118: aload 4
    //   120: invokevirtual 1936	java/io/ByteArrayOutputStream:close	()V
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
    //   142: invokevirtual 1935	java/io/FileInputStream:close	()V
    //   145: aload 4
    //   147: ifnull +8 -> 155
    //   150: aload 4
    //   152: invokevirtual 1936	java/io/ByteArrayOutputStream:close	()V
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
          localJSONObject.put("appid", ((com.igexin.push.core.bean.l)localArrayList.get(i)).c());
          localJSONObject.put("name", ((com.igexin.push.core.bean.l)localArrayList.get(i)).a());
          localJSONObject.put("version", ((com.igexin.push.core.bean.l)localArrayList.get(i)).b());
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
    l(p());
    com.igexin.a.a.c.a.a("reportapplist");
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
        localArrayList1.add(((com.igexin.push.core.bean.l)localArrayList2.get(i)).c());
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
    if (E() > 0)
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
    if (com.igexin.push.config.l.q == true)
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
