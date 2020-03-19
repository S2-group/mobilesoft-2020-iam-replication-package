package com.lionmobi.powerclean.e;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import com.lionmobi.powerclean.ApplicationEx;
import com.lionmobi.powerclean.model.b.ai;
import com.lionmobi.powerclean.model.b.dt;
import com.lionmobi.powerclean.service.lionmobiService;
import com.lionmobi.util.az;
import com.lionmobi.util.v;
import de.greenrobot.event.c;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class j
{
  private static j b = null;
  private static String j = "/Cache_ApkInstall.txt";
  private lionmobiService a;
  private ApplicationEx c;
  private int d = 7;
  private int e = 1;
  private String f = "lastpostinstallpkginfo";
  private String g = getClass().toString();
  private boolean h = false;
  private boolean i = false;
  
  private j(lionmobiService paramLionmobiService)
  {
    this.a = paramLionmobiService;
    this.c = ((ApplicationEx)paramLionmobiService.getApplication());
  }
  
  private List a()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.a.getPackageManager();
    if (localPackageManager == null) {
      return localArrayList;
    }
    List localList = localPackageManager.getInstalledPackages(0);
    int k = 0;
    for (;;)
    {
      if (k < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(k);
        k localK = new k(this);
        try
        {
          String str1 = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
          localK.setApkName(str1);
          localK.setPkgName(localPackageInfo.packageName);
          if ((localPackageInfo.applicationInfo.flags & 0x1) != 0)
          {
            localK.setSystemMark(1);
            localArrayList.add(localK);
            k += 1;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            String str2 = "";
            continue;
            localK.setSystemMark(0);
          }
        }
      }
    }
    return localArrayList;
  }
  
  private void a(List paramList)
  {
    Object localObject = this.a.getFilesDir();
    localObject = ((File)localObject).getPath() + j;
    BufferedWriter localBufferedWriter;
    try
    {
      localObject = new File((String)localObject);
      if (!((File)localObject).exists()) {
        ((File)localObject).createNewFile();
      }
      for (;;)
      {
        localObject = new FileWriter((File)localObject);
        localBufferedWriter = new BufferedWriter((Writer)localObject);
        int k = 0;
        while (k < paramList.size())
        {
          localBufferedWriter.write(((k)paramList.get(k)).getPkgName() + "," + ((k)paramList.get(k)).getApkName() + "," + ((k)paramList.get(k)).getSystemMark());
          localBufferedWriter.newLine();
          k += 1;
        }
        ((File)localObject).delete();
        ((File)localObject).createNewFile();
      }
      localBufferedWriter.flush();
    }
    catch (Exception paramList)
    {
      paramList.printStackTrace();
      return;
    }
    localBufferedWriter.close();
    ((FileWriter)localObject).close();
  }
  
  private Set b()
  {
    HashSet localHashSet = new HashSet();
    Object localObject = this.a.getFilesDir();
    localObject = new File(((File)localObject).getPath() + j);
    if ((((File)localObject).isFile()) && (((File)localObject).exists())) {
      try
      {
        localObject = new InputStreamReader(new FileInputStream((File)localObject));
        BufferedReader localBufferedReader = new BufferedReader((Reader)localObject);
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          if (!str.contains("FileTimes=")) {
            localHashSet.add(str.split(",")[0]);
          }
        }
        return localHashSet;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    localException.close();
    return localHashSet;
  }
  
  private static JSONArray b(List paramList)
  {
    JSONArray localJSONArray = new JSONArray();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      k localK = (k)paramList.next();
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("A", localK.getApkName().toString());
        localJSONObject.put("P", localK.getPkgName().toString());
        localJSONObject.put("S", localK.getSystemMark());
        localJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localJSONArray;
  }
  
  private Boolean c()
  {
    for (;;)
    {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo == null) {
          continue;
        }
        int k = localNetworkInfo.getType();
        if (k != 1) {
          continue;
        }
        bool = true;
      }
      catch (Exception localException)
      {
        boolean bool = false;
        continue;
      }
      return Boolean.valueOf(bool);
      bool = false;
    }
  }
  
  private String d()
  {
    Object localObject = new File(this.a.getFilesDir().getPath() + "/lioncloud");
    if ((!((File)localObject).exists()) && (!((File)localObject).mkdirs())) {}
    String str;
    for (;;)
    {
      return "";
      localObject = this.a.getFilesDir().getPath() + "/lioncloud/lionrule";
      File localFile = new File((String)localObject);
      try
      {
        str = v.getLionRuledbSP_CRC32(this.a);
        if ((localFile.exists()) && (v.getCRC32((String)localObject).equalsIgnoreCase(str)) && (str != ""))
        {
          if (!v.getCRC32((String)localObject).equalsIgnoreCase(str)) {
            localFile.delete();
          }
        }
        else
        {
          v.CopyAssertFileToDisk(this.a.getAssets().open("internal"), new FileOutputStream(localFile));
          if (!localFile.exists()) {
            continue;
          }
          localObject = v.getCRC32((String)localObject);
          v.SetLionRuledbSP(this.a, 0, (String)localObject, 0L);
          return localObject;
        }
      }
      catch (Exception localException)
      {
        return "";
      }
    }
    return str;
  }
  
  public static j getInstance(lionmobiService paramLionmobiService)
  {
    if (b != null) {
      return b;
    }
    return new j(paramLionmobiService);
  }
  
  public final boolean CheckPkglistOption()
  {
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("type", "pkglist_switch");
      localHttpPost = new HttpPost("http://www.lionmobi.com/powerclean/analysis/api.php");
      localArrayList = new ArrayList();
      localArrayList.add(new BasicNameValuePair("data", ((JSONObject)localObject).toString()));
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        try
        {
          HttpPost localHttpPost;
          ArrayList localArrayList;
          localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
          localObject = new DefaultHttpClient().execute(localHttpPost);
          if (((HttpResponse)localObject).getStatusLine().getStatusCode() != 200) {
            break;
          }
          int k = new JSONObject(EntityUtils.toString(((HttpResponse)localObject).getEntity())).getInt("switch");
          if (k != 1) {
            break;
          }
          return true;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        localJSONException = localJSONException;
        localJSONException.printStackTrace();
      }
    }
    return false;
  }
  
  /* Error */
  public final void CleanCloudUpdateCacheDb()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 59	com/lionmobi/powerclean/e/j:a	Lcom/lionmobi/powerclean/service/lionmobiService;
    //   4: invokestatic 427	com/lionmobi/util/v:getLionRuledbSP_CacheDbUpTime	(Landroid/content/Context;)I
    //   7: istore_1
    //   8: aload_0
    //   9: invokespecial 429	com/lionmobi/powerclean/e/j:d	()Ljava/lang/String;
    //   12: astore 4
    //   14: invokestatic 434	com/lionmobi/util/az:getTodayDayInYear	()I
    //   17: iload_1
    //   18: isub
    //   19: ifne +7 -> 26
    //   22: iload_1
    //   23: ifne +321 -> 344
    //   26: aload_0
    //   27: invokespecial 436	com/lionmobi/powerclean/e/j:c	()Ljava/lang/Boolean;
    //   30: invokevirtual 439	java/lang/Boolean:booleanValue	()Z
    //   33: ifeq +311 -> 344
    //   36: new 268	org/json/JSONObject
    //   39: dup
    //   40: invokespecial 269	org/json/JSONObject:<init>	()V
    //   43: astore 5
    //   45: aload 5
    //   47: ldc_w 362
    //   50: ldc_w 441
    //   53: invokevirtual 276	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   56: pop
    //   57: aload 5
    //   59: ldc_w 443
    //   62: aload 4
    //   64: invokevirtual 276	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   67: pop
    //   68: new 366	org/apache/http/client/methods/HttpPost
    //   71: dup
    //   72: ldc_w 445
    //   75: invokespecial 369	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
    //   78: astore 6
    //   80: new 74	java/util/ArrayList
    //   83: dup
    //   84: invokespecial 75	java/util/ArrayList:<init>	()V
    //   87: astore 7
    //   89: aload 7
    //   91: new 371	org/apache/http/message/BasicNameValuePair
    //   94: dup
    //   95: ldc_w 373
    //   98: aload 5
    //   100: invokevirtual 374	org/json/JSONObject:toString	()Ljava/lang/String;
    //   103: invokespecial 377	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   106: invokeinterface 136 2 0
    //   111: pop
    //   112: aload 6
    //   114: new 379	org/apache/http/client/entity/UrlEncodedFormEntity
    //   117: dup
    //   118: aload 7
    //   120: ldc_w 381
    //   123: invokespecial 384	org/apache/http/client/entity/UrlEncodedFormEntity:<init>	(Ljava/util/List;Ljava/lang/String;)V
    //   126: invokevirtual 388	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
    //   129: new 390	org/apache/http/impl/client/DefaultHttpClient
    //   132: dup
    //   133: invokespecial 391	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   136: aload 6
    //   138: invokevirtual 395	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   141: astore 5
    //   143: aload 5
    //   145: invokeinterface 401 1 0
    //   150: invokeinterface 406 1 0
    //   155: istore_1
    //   156: iload_1
    //   157: sipush 200
    //   160: if_icmpne +184 -> 344
    //   163: new 268	org/json/JSONObject
    //   166: dup
    //   167: aload 5
    //   169: invokeinterface 410 1 0
    //   174: invokestatic 415	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;)Ljava/lang/String;
    //   177: invokespecial 416	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   180: astore 5
    //   182: aload 5
    //   184: ldc_w 447
    //   187: invokevirtual 450	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   190: ifeq +165 -> 355
    //   193: aload 5
    //   195: ldc_w 443
    //   198: invokevirtual 453	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   201: astore 4
    //   203: aload 5
    //   205: ldc_w 455
    //   208: invokevirtual 453	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   211: invokestatic 461	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   214: lstore_2
    //   215: aload_0
    //   216: new 145	java/lang/StringBuilder
    //   219: dup
    //   220: ldc_w 463
    //   223: invokespecial 464	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   226: aload 4
    //   228: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: ldc_w 466
    //   234: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   240: ldc_w 468
    //   243: invokevirtual 472	com/lionmobi/powerclean/e/j:saveFileFromHttp	(Ljava/lang/String;Ljava/lang/String;)Z
    //   246: ifeq +98 -> 344
    //   249: aload_0
    //   250: getfield 59	com/lionmobi/powerclean/e/j:a	Lcom/lionmobi/powerclean/service/lionmobiService;
    //   253: invokevirtual 143	com/lionmobi/powerclean/service/lionmobiService:getFilesDir	()Ljava/io/File;
    //   256: astore 5
    //   258: new 145	java/lang/StringBuilder
    //   261: dup
    //   262: invokespecial 146	java/lang/StringBuilder:<init>	()V
    //   265: aload 5
    //   267: invokevirtual 151	java/io/File:getPath	()Ljava/lang/String;
    //   270: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: ldc_w 318
    //   276: invokevirtual 155	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   279: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: astore 5
    //   284: new 148	java/io/File
    //   287: dup
    //   288: aload 5
    //   290: invokespecial 158	java/io/File:<init>	(Ljava/lang/String;)V
    //   293: invokevirtual 162	java/io/File:exists	()Z
    //   296: ifeq +48 -> 344
    //   299: aload 5
    //   301: invokestatic 328	com/lionmobi/util/v:getCRC32	(Ljava/lang/String;)Ljava/lang/String;
    //   304: astore 5
    //   306: aload 5
    //   308: ifnull +36 -> 344
    //   311: aload 4
    //   313: invokevirtual 475	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   316: aload 5
    //   318: invokevirtual 478	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   321: ifeq +23 -> 344
    //   324: aload_0
    //   325: getfield 59	com/lionmobi/powerclean/e/j:a	Lcom/lionmobi/powerclean/service/lionmobiService;
    //   328: invokestatic 434	com/lionmobi/util/az:getTodayDayInYear	()I
    //   331: aload 4
    //   333: lload_2
    //   334: invokestatic 355	com/lionmobi/util/v:SetLionRuledbSP	(Landroid/content/Context;ILjava/lang/String;J)V
    //   337: aload_0
    //   338: getfield 59	com/lionmobi/powerclean/e/j:a	Lcom/lionmobi/powerclean/service/lionmobiService;
    //   341: invokevirtual 481	com/lionmobi/powerclean/service/lionmobiService:loadCacheDb	()V
    //   344: return
    //   345: astore 6
    //   347: aload 6
    //   349: invokevirtual 287	org/json/JSONException:printStackTrace	()V
    //   352: goto -284 -> 68
    //   355: aload_0
    //   356: getfield 59	com/lionmobi/powerclean/e/j:a	Lcom/lionmobi/powerclean/service/lionmobiService;
    //   359: invokestatic 434	com/lionmobi/util/az:getTodayDayInYear	()I
    //   362: aload 4
    //   364: lconst_0
    //   365: invokestatic 355	com/lionmobi/util/v:SetLionRuledbSP	(Landroid/content/Context;ILjava/lang/String;J)V
    //   368: return
    //   369: astore 4
    //   371: aload 4
    //   373: invokevirtual 287	org/json/JSONException:printStackTrace	()V
    //   376: return
    //   377: astore 4
    //   379: aload 4
    //   381: invokevirtual 201	java/lang/Exception:printStackTrace	()V
    //   384: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	385	0	this	j
    //   7	154	1	k	int
    //   214	120	2	l	long
    //   12	351	4	str	String
    //   369	3	4	localJSONException1	JSONException
    //   377	3	4	localException	Exception
    //   43	274	5	localObject	Object
    //   78	59	6	localHttpPost	HttpPost
    //   345	3	6	localJSONException2	JSONException
    //   87	32	7	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   45	68	345	org/json/JSONException
    //   163	306	369	org/json/JSONException
    //   311	344	369	org/json/JSONException
    //   355	368	369	org/json/JSONException
    //   112	156	377	java/lang/Exception
    //   163	306	377	java/lang/Exception
    //   311	344	377	java/lang/Exception
    //   355	368	377	java/lang/Exception
    //   371	376	377	java/lang/Exception
  }
  
  public final void PostinstallApkinfo()
  {
    if (!c().booleanValue()) {}
    for (;;)
    {
      return;
      int n = this.c.getGlobalSettingPreference().getInt(this.f, 0);
      int m = az.getTodayDayInYear() - n;
      int k = m;
      if (m < 0) {
        k = m + 365;
      }
      if (((k < this.d) && (n != 0)) || (!CheckPkglistOption())) {
        continue;
      }
      Object localObject2 = a();
      if (n != 0)
      {
        new HashSet();
        localObject1 = b();
        a((List)localObject2);
        localObject3 = ((List)localObject2).iterator();
        while (((Iterator)localObject3).hasNext()) {
          if (((Set)localObject1).contains(((k)((Iterator)localObject3).next()).getPkgName())) {
            ((Iterator)localObject3).remove();
          }
        }
      }
      a((List)localObject2);
      if ((((List)localObject2).size() <= 0) || (!c().booleanValue())) {
        continue;
      }
      Object localObject1 = new JSONObject();
      Object localObject3 = Settings.Secure.getString(this.a.getContentResolver(), "android_id");
      try
      {
        ((JSONObject)localObject1).put("type", "pkglist");
        ((JSONObject)localObject1).put("aid", localObject3);
        ((JSONObject)localObject1).put("model", Build.MODEL);
        ((JSONObject)localObject1).put("client", "1");
        ((JSONObject)localObject1).put("InstallApks", b((List)localObject2));
        localObject2 = new HttpPost("http://www.lionmobi.com/powerclean/analysis/api.php");
        localObject3 = new ArrayList();
        ((List)localObject3).add(new BasicNameValuePair("data", ((JSONObject)localObject1).toString()));
        try
        {
          ((HttpPost)localObject2).setEntity(new UrlEncodedFormEntity((List)localObject3, "UTF-8"));
          if (new DefaultHttpClient().execute((HttpUriRequest)localObject2).getStatusLine().getStatusCode() != 200) {
            continue;
          }
          this.c.getGlobalSettingPreference().edit().putInt(this.f, az.getTodayDayInYear()).commit();
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  public final void Register()
  {
    if (!c.getDefault().isRegistered(this)) {
      c.getDefault().register(this);
    }
  }
  
  public final void UnRegister()
  {
    if (!c.getDefault().isRegistered(this)) {
      c.getDefault().register(this);
    }
  }
  
  public final void onEventAsync(ai paramAi)
  {
    if (!this.h)
    {
      this.h = true;
      CleanCloudUpdateCacheDb();
      this.h = false;
    }
  }
  
  public final void onEventAsync(dt paramDt)
  {
    if (!this.i)
    {
      this.i = true;
      PostinstallApkinfo();
      this.i = false;
    }
  }
  
  public final boolean saveFileFromHttp(String paramString1, String paramString2)
  {
    try
    {
      Object localObject = this.a.getFilesDir();
      localObject = ((File)localObject).getPath() + "/lioncloud/";
      File localFile = new File((String)localObject);
      if (!localFile.exists()) {
        localFile.mkdir();
      }
      paramString2 = (String)localObject + paramString2;
      localObject = new File(paramString2);
      if (((File)localObject).exists()) {
        ((File)localObject).delete();
      }
      paramString1 = new DataInputStream(((HttpURLConnection)new URL(paramString1).openConnection()).getInputStream());
      paramString2 = new DataOutputStream(new FileOutputStream(paramString2));
      localObject = new byte['က'];
      for (;;)
      {
        int k = paramString1.read((byte[])localObject);
        if (k <= 0) {
          break;
        }
        paramString2.write((byte[])localObject, 0, k);
      }
      paramString2.close();
      paramString1.close();
      return true;
    }
    catch (Exception paramString1) {}
    return false;
  }
}
