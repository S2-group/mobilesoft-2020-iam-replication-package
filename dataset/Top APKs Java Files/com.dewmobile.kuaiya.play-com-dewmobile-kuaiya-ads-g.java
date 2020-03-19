package com.dewmobile.kuaiya.ads;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.util.ModernAsyncTask;
import android.text.TextUtils;
import com.dewmobile.kuaiya.g.a;
import com.dewmobile.kuaiya.util.q;
import com.dewmobile.library.m.o;
import com.dewmobile.library.transfer.DmTransferBean;
import com.dewmobile.library.transfer.DmTransferBean.ApkInfo;
import com.dewmobile.transfer.api.k;
import com.dewmobile.transfer.api.m;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class g
{
  private static final int a;
  private static final int b;
  private static a c;
  private static List<String> d = new LinkedList();
  
  static
  {
    a = q.a("simulation_down_install_interval", 10) * 60 * 1000;
    b = q.a("simulation_down_download_interval", 60) * 60 * 1000;
    i();
  }
  
  public static void a()
  {
    if ((!f()) || (!com.dewmobile.kuaiya.remote.a.b.b(com.dewmobile.library.d.b.a()))) {
      return;
    }
    List localList = com.dewmobile.library.d.b.a().getPackageManager().getInstalledPackages(128);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if (((localPackageInfo.applicationInfo.flags & 0x1) != 0) || (System.currentTimeMillis() - ((PackageInfo)localList.get(i)).firstInstallTime > a)) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localPackageInfo);
      }
    }
    new c(localArrayList, "z-510-0007").a();
  }
  
  private static PackageInfo b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return null;
    }
    PackageManager localPackageManager = com.dewmobile.library.d.b.a().getPackageManager();
    try
    {
      paramString = localPackageManager.getPackageInfo(paramString, 128);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static boolean f()
  {
    return q.a("simulation_down_recent_switch", 1) == 1;
  }
  
  private static boolean g()
  {
    return q.a("simulation_down_now_switch", 1) == 1;
  }
  
  /* Error */
  private static List<b> h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: invokestatic 105	java/lang/System:currentTimeMillis	()J
    //   5: lstore_1
    //   6: lload_1
    //   7: invokestatic 160	com/dewmobile/library/g/b:a	()Lcom/dewmobile/library/g/b;
    //   10: ldc -94
    //   12: lload_1
    //   13: invokevirtual 165	com/dewmobile/library/g/b:a	(Ljava/lang/String;J)J
    //   16: lsub
    //   17: ldc2_w 166
    //   20: lcmp
    //   21: ifge +13 -> 34
    //   24: invokestatic 160	com/dewmobile/library/g/b:a	()Lcom/dewmobile/library/g/b;
    //   27: ldc -87
    //   29: aconst_null
    //   30: invokevirtual 172	com/dewmobile/library/g/b:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   33: astore_3
    //   34: aload_3
    //   35: ifnonnull +270 -> 305
    //   38: getstatic 175	com/dewmobile/library/d/b:a	Landroid/content/Context;
    //   41: invokestatic 180	com/android/volley/toolbox/q:a	(Landroid/content/Context;)Lcom/android/volley/h;
    //   44: astore 4
    //   46: invokestatic 185	com/android/volley/toolbox/o:a	()Lcom/android/volley/toolbox/o;
    //   49: astore 5
    //   51: new 187	com/android/volley/toolbox/p
    //   54: dup
    //   55: iconst_0
    //   56: ldc -67
    //   58: invokestatic 194	com/dewmobile/kuaiya/remote/a/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   61: aload 5
    //   63: aload 5
    //   65: invokespecial 197	com/android/volley/toolbox/p:<init>	(ILjava/lang/String;Lcom/android/volley/i$d;Lcom/android/volley/i$c;)V
    //   68: astore 6
    //   70: aload 6
    //   72: invokestatic 58	com/dewmobile/library/d/b:a	()Landroid/content/Context;
    //   75: invokestatic 201	com/dewmobile/kuaiya/remote/a/b:o	(Landroid/content/Context;)Ljava/util/HashMap;
    //   78: invokevirtual 204	com/android/volley/toolbox/p:a	(Ljava/util/Map;)V
    //   81: aload 4
    //   83: aload 6
    //   85: invokevirtual 209	com/android/volley/h:a	(Lcom/android/volley/Request;)Lcom/android/volley/Request;
    //   88: pop
    //   89: aload 5
    //   91: ldc2_w 210
    //   94: getstatic 217	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   97: invokevirtual 220	com/android/volley/toolbox/o:get	(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    //   100: checkcast 222	java/lang/String
    //   103: astore 4
    //   105: invokestatic 160	com/dewmobile/library/g/b:a	()Lcom/dewmobile/library/g/b;
    //   108: ldc -94
    //   110: lload_1
    //   111: invokevirtual 225	com/dewmobile/library/g/b:b	(Ljava/lang/String;J)V
    //   114: invokestatic 160	com/dewmobile/library/g/b:a	()Lcom/dewmobile/library/g/b;
    //   117: ldc -87
    //   119: aload 4
    //   121: invokevirtual 228	com/dewmobile/library/g/b:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   124: aload 4
    //   126: astore_3
    //   127: new 77	java/util/ArrayList
    //   130: dup
    //   131: invokespecial 78	java/util/ArrayList:<init>	()V
    //   134: astore 4
    //   136: aload_3
    //   137: ifnull +129 -> 266
    //   140: new 230	org/json/JSONArray
    //   143: dup
    //   144: aload_3
    //   145: invokespecial 233	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   148: astore_3
    //   149: iconst_0
    //   150: istore_0
    //   151: iload_0
    //   152: aload_3
    //   153: invokevirtual 236	org/json/JSONArray:length	()I
    //   156: if_icmpge +110 -> 266
    //   159: new 11	com/dewmobile/kuaiya/ads/g$b
    //   162: dup
    //   163: invokespecial 237	com/dewmobile/kuaiya/ads/g$b:<init>	()V
    //   166: astore 5
    //   168: aload 5
    //   170: aload_3
    //   171: iload_0
    //   172: invokevirtual 241	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   175: ldc -13
    //   177: invokevirtual 248	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   180: invokevirtual 250	com/dewmobile/kuaiya/ads/g$b:b	(Ljava/lang/String;)V
    //   183: aload 5
    //   185: aload_3
    //   186: iload_0
    //   187: invokevirtual 241	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   190: ldc -4
    //   192: invokevirtual 248	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   195: invokevirtual 254	com/dewmobile/kuaiya/ads/g$b:a	(Ljava/lang/String;)V
    //   198: aload 5
    //   200: aload_3
    //   201: iload_0
    //   202: invokevirtual 241	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   205: ldc_w 256
    //   208: invokevirtual 248	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   211: invokevirtual 258	com/dewmobile/kuaiya/ads/g$b:c	(Ljava/lang/String;)V
    //   214: aload 4
    //   216: aload 5
    //   218: invokeinterface 113 2 0
    //   223: pop
    //   224: iload_0
    //   225: iconst_1
    //   226: iadd
    //   227: istore_0
    //   228: goto -77 -> 151
    //   231: astore 4
    //   233: aload 4
    //   235: invokevirtual 259	java/lang/InterruptedException:printStackTrace	()V
    //   238: goto -111 -> 127
    //   241: astore 4
    //   243: aload 4
    //   245: invokevirtual 260	java/util/concurrent/ExecutionException:printStackTrace	()V
    //   248: goto -121 -> 127
    //   251: astore 4
    //   253: aload 4
    //   255: invokevirtual 261	java/util/concurrent/TimeoutException:printStackTrace	()V
    //   258: goto -131 -> 127
    //   261: astore_3
    //   262: aload_3
    //   263: invokevirtual 262	org/json/JSONException:printStackTrace	()V
    //   266: aload 4
    //   268: areturn
    //   269: astore 5
    //   271: aload 4
    //   273: astore_3
    //   274: aload 5
    //   276: astore 4
    //   278: goto -25 -> 253
    //   281: astore 5
    //   283: aload 4
    //   285: astore_3
    //   286: aload 5
    //   288: astore 4
    //   290: goto -47 -> 243
    //   293: astore 5
    //   295: aload 4
    //   297: astore_3
    //   298: aload 5
    //   300: astore 4
    //   302: goto -69 -> 233
    //   305: goto -178 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   150	78	0	i	int
    //   5	106	1	l	long
    //   1	200	3	localObject1	Object
    //   261	2	3	localJSONException	org.json.JSONException
    //   273	25	3	localObject2	Object
    //   44	171	4	localObject3	Object
    //   231	3	4	localInterruptedException1	InterruptedException
    //   241	3	4	localExecutionException1	java.util.concurrent.ExecutionException
    //   251	21	4	localTimeoutException1	java.util.concurrent.TimeoutException
    //   276	25	4	localObject4	Object
    //   49	168	5	localObject5	Object
    //   269	6	5	localTimeoutException2	java.util.concurrent.TimeoutException
    //   281	6	5	localExecutionException2	java.util.concurrent.ExecutionException
    //   293	6	5	localInterruptedException2	InterruptedException
    //   68	16	6	localP	com.android.volley.toolbox.p
    // Exception table:
    //   from	to	target	type
    //   89	105	231	java/lang/InterruptedException
    //   89	105	241	java/util/concurrent/ExecutionException
    //   89	105	251	java/util/concurrent/TimeoutException
    //   140	149	261	org/json/JSONException
    //   151	224	261	org/json/JSONException
    //   105	124	269	java/util/concurrent/TimeoutException
    //   105	124	281	java/util/concurrent/ExecutionException
    //   105	124	293	java/lang/InterruptedException
  }
  
  private static void i()
  {
    if (c == null) {
      c = new a(null);
    }
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
    localIntentFilter.addDataScheme("package");
    com.dewmobile.library.d.b.a().registerReceiver(c, localIntentFilter);
  }
  
  private static class a
    extends BroadcastReceiver
  {
    private a() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((!g.b()) || (!com.dewmobile.kuaiya.remote.a.b.b(com.dewmobile.library.d.b.a()))) {}
      for (;;)
      {
        return;
        paramIntent.getAction();
        paramContext = paramIntent.getData();
        if (paramContext != null) {}
        for (paramContext = paramContext.getSchemeSpecificPart(); (paramContext != null) && (!g.c().contains(paramContext)); paramContext = null)
        {
          new g.c(g.a(paramContext), "z-510-0008").a();
          return;
        }
      }
    }
  }
  
  public static class b
  {
    private String a;
    private String b;
    private String c;
    
    public b() {}
    
    public String a()
    {
      return this.a;
    }
    
    public void a(String paramString)
    {
      this.a = paramString;
    }
    
    public String b()
    {
      return this.b;
    }
    
    public void b(String paramString)
    {
      this.b = paramString;
    }
    
    public String c()
    {
      return this.c;
    }
    
    public void c(String paramString)
    {
      this.c = paramString;
    }
  }
  
  private static class c
    implements Runnable
  {
    List<PackageInfo> a = new ArrayList();
    String b;
    String c;
    String d;
    
    public c(PackageInfo paramPackageInfo, String paramString)
    {
      if (paramPackageInfo == null) {
        return;
      }
      this.a.add(paramPackageInfo);
      this.d = paramString;
    }
    
    public c(List<PackageInfo> paramList, String paramString)
    {
      if (paramList == null) {
        return;
      }
      this.a.addAll(paramList);
      this.d = paramString;
    }
    
    private void a(PackageInfo paramPackageInfo)
    {
      List localList = g.e();
      if (a(com.dewmobile.library.d.b.a(), paramPackageInfo.packageName)) {
        return;
      }
      int i = 0;
      label21:
      g.b localB;
      if (i < localList.size())
      {
        localB = (g.b)localList.get(i);
        if (!TextUtils.isEmpty(localB.a())) {
          break label61;
        }
      }
      for (;;)
      {
        i += 1;
        break label21;
        break;
        label61:
        if (localB.a().equals(paramPackageInfo.packageName)) {
          if (!TextUtils.isEmpty(localB.b()))
          {
            SystemClock.elapsedRealtime();
            String str = o.a(paramPackageInfo.applicationInfo.sourceDir);
            if (!localB.b().equals(str)) {}
          }
          else
          {
            a(localB.c());
            a.a(com.dewmobile.library.d.b.a(), this.d, paramPackageInfo.packageName);
          }
        }
      }
    }
    
    private void a(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      try
      {
        paramString = (HttpURLConnection)new URL(paramString).openConnection();
        paramString.setDoInput(true);
        paramString.setUseCaches(false);
        paramString.setConnectTimeout(30000);
        paramString.setReadTimeout(10000);
        paramString.connect();
        paramString.getResponseCode();
        paramString.disconnect();
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    private boolean a(Context paramContext, String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      long l1 = System.currentTimeMillis();
      long l2 = g.d();
      Cursor localCursor = com.dewmobile.library.d.b.a().getContentResolver().query(m.b, null, "status = 0 and direction = 0 and net != 0 and apkinfo != '' and createtime >= " + (l1 - l2), null, "_id DESC");
      if (localCursor != null) {}
      for (;;)
      {
        try
        {
          k localK = k.a(localCursor);
          if (!localCursor.moveToNext()) {
            break label198;
          }
          DmTransferBean localDmTransferBean = new DmTransferBean(localCursor, localK);
          localDmTransferBean.a(paramContext, false);
          if (localDmTransferBean.y() == null) {
            continue;
          }
          bool1 = paramString.equals(localDmTransferBean.y().c);
          if (!bool1) {
            continue;
          }
          bool1 = true;
          bool2 = bool1;
          if (localCursor != null)
          {
            localCursor.close();
            bool2 = bool1;
          }
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          if (localCursor == null) {
            break label192;
          }
          localCursor.close();
          bool2 = false;
          continue;
        }
        finally
        {
          if (localCursor == null) {
            continue;
          }
          localCursor.close();
        }
        return bool2;
        label192:
        boolean bool2 = false;
        continue;
        label198:
        boolean bool1 = false;
      }
    }
    
    private void b()
    {
      List localList = g.e();
      if (a(com.dewmobile.library.d.b.a(), this.b)) {
        return;
      }
      int i = 0;
      label21:
      g.b localB;
      if (i < localList.size())
      {
        localB = (g.b)localList.get(i);
        if (!TextUtils.isEmpty(localB.a())) {
          break label59;
        }
      }
      for (;;)
      {
        i += 1;
        break label21;
        break;
        label59:
        if (localB.a().equals(this.b)) {
          if (!TextUtils.isEmpty(localB.b()))
          {
            SystemClock.elapsedRealtime();
            String str = o.a(this.c);
            if (!localB.b().equals(str)) {}
          }
          else
          {
            a(localB.c());
          }
        }
      }
    }
    
    public void a()
    {
      ModernAsyncTask.execute(this);
    }
    
    public void run()
    {
      if (((this.a == null) || (this.a.size() == 0)) && (TextUtils.isEmpty(this.b))) {}
      do
      {
        return;
        int i = 0;
        while (i < this.a.size())
        {
          a((PackageInfo)this.a.get(i));
          i += 1;
        }
      } while (TextUtils.isEmpty(this.b));
      b();
    }
  }
}
