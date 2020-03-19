package jp.Adlantis.Android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AdManager
{
  private static String DEBUG_TASK = "AdManager";
  private AdNetworkConnection _adNetworkConnection;
  private HashMap<String, String> _defaultParamMap;
  private String[] _testAdRequestUrls;
  public long adDisplayInterval = 10000L;
  public long adFetchInterval = 300000L;
  public int adIndex = 0;
  private AdlantisAd[] ads;
  private AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
  private boolean connectionChangeReceiverRegistered = false;
  private String conversionTag = null;
  private boolean conversionTagSent = false;
  private boolean isDoingAdRequest;
  private String keywords;
  private String publisherID;
  private int testAdRequestUrlIndex;
  
  private AdManager()
  {
    if (isGreeSdk())
    {
      this._adNetworkConnection = new GreeAdConnection();
      return;
    }
    this._adNetworkConnection = new AdLantisConnection();
  }
  
  public static int adCountForOrientation(AdlantisAd[] paramArrayOfAdlantisAd, int paramInt)
  {
    return filteredAdsForOrientation(paramArrayOfAdlantisAd, paramInt).size();
  }
  
  public static AdlantisAd[] adsFromJSONInputStream(InputStream paramInputStream)
    throws IOException
  {
    if (1 != 0) {
      return parseJSONAdInputStreamWithSimpleJSON(paramInputStream);
    }
    return null;
  }
  
  public static String byline()
  {
    if (isGreeSdk()) {
      return "Ads by GREE";
    }
    return "Ads by AdLantis";
  }
  
  private Uri.Builder defaultRequestBuilder(Context paramContext)
  {
    return defaultRequestBuilder(paramContext, null);
  }
  
  private boolean doAdRequest(Context paramContext, AdManagerCallback paramAdManagerCallback)
  {
    if (this.isDoingAdRequest) {
      return false;
    }
    this.isDoingAdRequest = true;
    if ((this._testAdRequestUrls != null) && (this._testAdRequestUrls.length > 0))
    {
      adRequestURI(paramContext);
      paramContext = Uri.parse(this._testAdRequestUrls[this.testAdRequestUrlIndex]);
      this.testAdRequestUrlIndex = ((this.testAdRequestUrlIndex + 1) % this._testAdRequestUrls.length);
    }
    for (;;)
    {
      try
      {
        HttpRequestInterceptor local1 = new HttpRequestInterceptor()
        {
          public void process(HttpRequest paramAnonymousHttpRequest, HttpContext paramAnonymousHttpContext)
            throws HttpException, IOException
          {
            paramAnonymousHttpRequest = (AuthState)paramAnonymousHttpContext.getAttribute("http.auth.target-scope");
            CredentialsProvider localCredentialsProvider = (CredentialsProvider)paramAnonymousHttpContext.getAttribute("http.auth.credentials-provider");
            paramAnonymousHttpContext = (HttpHost)paramAnonymousHttpContext.getAttribute("http.target_host");
            if (paramAnonymousHttpRequest.getAuthScheme() == null)
            {
              paramAnonymousHttpContext = localCredentialsProvider.getCredentials(new AuthScope(paramAnonymousHttpContext.getHostName(), paramAnonymousHttpContext.getPort()));
              if (paramAnonymousHttpContext != null)
              {
                paramAnonymousHttpRequest.setAuthScheme(new BasicScheme());
                paramAnonymousHttpRequest.setCredentials(paramAnonymousHttpContext);
              }
            }
          }
        };
        HttpHost localHttpHost = new HttpHost(this._adNetworkConnection.getHost(), this._adNetworkConnection.getPort(), "http");
        paramAdManagerCallback = new DefaultHttpClient();
        paramAdManagerCallback.addRequestInterceptor(local1, 0);
        paramAdManagerCallback.getCredentialsProvider().setCredentials(new AuthScope(localHttpHost.getHostName(), localHttpHost.getPort()), new UsernamePasswordCredentials("3263", "0315"));
        paramContext = paramContext.toString();
        Log.d(DEBUG_TASK, paramContext);
        this.ads = adsFromJSONInputStream(paramAdManagerCallback.execute(new HttpGet(paramContext)).getEntity().getContent());
        if (this.ads == null) {
          continue;
        }
        int i = this.ads.length;
        if (i <= 0) {
          continue;
        }
        bool = true;
      }
      catch (MalformedURLException paramContext)
      {
        Log.e(DEBUG_TASK, paramContext.toString());
        bool = false;
        continue;
      }
      catch (IOException paramContext)
      {
        Log.e(DEBUG_TASK, paramContext.toString());
        boolean bool = false;
        continue;
      }
      this.isDoingAdRequest = false;
      return bool;
      paramContext = adRequestURI(paramContext);
      continue;
      bool = false;
    }
  }
  
  public static Vector<AdlantisAd> filteredAdsForOrientation(AdlantisAd[] paramArrayOfAdlantisAd, int paramInt)
  {
    Vector localVector = new Vector();
    if (paramArrayOfAdlantisAd == null) {}
    for (;;)
    {
      return localVector;
      int i = 0;
      while (i < paramArrayOfAdlantisAd.length)
      {
        if (paramArrayOfAdlantisAd[i].hasAdForOrientation(paramInt)) {
          localVector.addElement(paramArrayOfAdlantisAd[i]);
        }
        i += 1;
      }
    }
  }
  
  private AdlantisAd[] filteredAdsForOrientation(int paramInt)
  {
    try
    {
      Vector localVector = filteredAdsForOrientation(this.ads, paramInt);
      AdlantisAd[] arrayOfAdlantisAd = new AdlantisAd[localVector.size()];
      localVector.copyInto(arrayOfAdlantisAd);
      return arrayOfAdlantisAd;
    }
    finally {}
  }
  
  public static AdManager getInstance()
  {
    return AdManagerHolder.INSTANCE;
  }
  
  private void handleHttpClickRequest(final String paramString, final AdManagerRedirectUrlProcessedCallback paramAdManagerRedirectUrlProcessedCallback)
  {
    new Thread()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (paramAdManagerRedirectUrlProcessedCallback != null) {
          paramAdManagerRedirectUrlProcessedCallback.redirectProcessed((Uri)paramAnonymousMessage.obj);
        }
      }
    }
    {
      public boolean isRedirectRequested(HttpResponse paramAnonymousHttpResponse, HttpContext paramAnonymousHttpContext)
      {
        Object localObject2 = null;
        int i = paramAnonymousHttpResponse.getStatusLine().getStatusCode();
        Object localObject1 = localObject2;
        if (i >= 300)
        {
          localObject1 = localObject2;
          if (i < 400)
          {
            paramAnonymousHttpResponse = paramAnonymousHttpResponse.getHeaders("Location");
            localObject1 = localObject2;
            if (paramAnonymousHttpResponse.length > 0)
            {
              localObject1 = paramAnonymousHttpResponse[0].getValue();
              Log.d(AdManager.DEBUG_TASK, "location=" + (String)localObject1);
            }
          }
        }
        paramAnonymousHttpResponse = (HttpResponse)localObject1;
        if (localObject1 == null)
        {
          paramAnonymousHttpResponse = (HttpUriRequest)paramAnonymousHttpContext.getAttribute("http.request");
          paramAnonymousHttpContext = (HttpHost)paramAnonymousHttpContext.getAttribute("http.target_host");
          paramAnonymousHttpResponse = paramAnonymousHttpContext.toURI() + paramAnonymousHttpResponse.getURI();
        }
        paramAnonymousHttpResponse = this.val$handler.obtainMessage(0, Uri.parse(paramAnonymousHttpResponse));
        this.val$handler.sendMessage(paramAnonymousHttpResponse);
        return false;
      }
    }
    {
      public void run()
      {
        try
        {
          DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
          localDefaultHttpClient.setRedirectHandler(this.val$redirectHandler);
          String str = AdManager.this.defaultRequestBuilder(null, Uri.parse(paramString)).build().toString();
          Log.d(AdManager.DEBUG_TASK, "handleHttpClickRequest=" + str);
          localDefaultHttpClient.execute(new HttpGet(str));
          return;
        }
        catch (MalformedURLException localMalformedURLException)
        {
          Log.e(AdManager.DEBUG_TASK, localMalformedURLException.toString());
          return;
        }
        catch (IOException localIOException)
        {
          Log.e(AdManager.DEBUG_TASK, localIOException.toString());
        }
      }
    }.start();
  }
  
  public static List<String> installedPackages(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject).next()).packageName);
    }
    return paramContext;
  }
  
  public static boolean isGreeSdk()
  {
    return false;
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = paramContext.getAllNetworkInfo();
        if (paramContext == null) {
          continue;
        }
        int i = 0;
        while (i < paramContext.length)
        {
          boolean bool = paramContext[i].isConnected();
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      catch (Exception paramContext)
      {
        Log.e(DEBUG_TASK, paramContext.toString());
      }
    }
  }
  
  public static void main(String[] paramArrayOfString)
  {
    new AdManager().connect(null, null);
  }
  
  private String md5_uniqueID(Context paramContext)
  {
    paramContext = uniqueID(paramContext);
    if (paramContext != null) {
      return AdlantisUtils.md5(paramContext);
    }
    return null;
  }
  
  private static AdlantisAd[] parseJSONAdInputStreamWithSimpleJSON(InputStream paramInputStream)
    throws IOException
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream), 8192);
    try
    {
      paramInputStream = new JSONParser().parse(localBufferedReader);
      if (paramInputStream != null) {
        if ((paramInputStream instanceof JSONArray))
        {
          paramInputStream = (JSONArray)paramInputStream;
          if (paramInputStream == null) {
            break label145;
          }
          int j = paramInputStream.size();
          arrayOfAdlantisAd = new AdlantisAd[j];
          int i = 0;
          while (i < j)
          {
            arrayOfAdlantisAd[i] = new AdlantisAd((HashMap)paramInputStream.get(i));
            i += 1;
          }
        }
      }
    }
    catch (Exception paramInputStream)
    {
      for (;;)
      {
        AdlantisAd[] arrayOfAdlantisAd;
        Log.e(DEBUG_TASK, paramInputStream.toString());
        paramInputStream = null;
        continue;
        if ((paramInputStream instanceof JSONObject))
        {
          paramInputStream = (JSONArray)((JSONObject)paramInputStream).get("ads");
          continue;
          for (paramInputStream = arrayOfAdlantisAd;; paramInputStream = null)
          {
            localBufferedReader.close();
            return paramInputStream;
            label145:
            Log.i(DEBUG_TASK, "Adlantis: no ads received (this is not an error)");
          }
        }
        else
        {
          paramInputStream = null;
        }
      }
    }
  }
  
  public static String sdkBuild()
  {
    return "261";
  }
  
  public static String sdkFullVersion()
  {
    if (isGreeSdk()) {}
    for (String str = "GREE Ad SDK";; str = "AdLantis SDK") {
      return str + " " + sdkVersion() + " (" + sdkBuild() + ")";
    }
  }
  
  public static String sdkVersion()
  {
    return "1.3.2";
  }
  
  private void sendConversionTagInternal(final Context paramContext, final String paramString, final boolean paramBoolean)
  {
    if (!paramString.equals(this.conversionTag))
    {
      this.conversionTag = paramString;
      this.conversionTagSent = false;
    }
    if (!this.conversionTagSent) {
      new Thread()
      {
        public void run()
        {
          try
          {
            DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
            String str = AdManager.this.conversionTagRequestUri(paramContext, paramString, paramBoolean).toString();
            Log.d(AdManager.DEBUG_TASK, "sendConversionTag url=" + str);
            int i = localDefaultHttpClient.execute(new HttpGet(str)).getStatusLine().getStatusCode();
            if ((i >= 300) && (i < 501)) {
              AdManager.access$302(AdManager.this, true);
            }
            return;
          }
          catch (MalformedURLException localMalformedURLException)
          {
            Log.e(AdManager.DEBUG_TASK, "sendConversionTag exception=" + localMalformedURLException.toString());
            return;
          }
          catch (IOException localIOException)
          {
            Log.e(AdManager.DEBUG_TASK, "sendConversionTag exception=" + localIOException.toString());
          }
        }
      }.start();
    }
  }
  
  private String uniqueID(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getDeviceId();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.e(DEBUG_TASK, paramContext.toString());
    }
    return null;
  }
  
  public int adCountForOrientation(int paramInt)
  {
    return adCountForOrientation(this.ads, paramInt);
  }
  
  public Uri adRequestURI(Context paramContext)
  {
    Uri.Builder localBuilder = defaultRequestBuilder(paramContext);
    localBuilder.scheme("http");
    localBuilder.authority(this._adNetworkConnection.getHost());
    localBuilder.path("/sp/load_app_ads");
    localBuilder.appendQueryParameter("callbackid", "0");
    localBuilder.appendQueryParameter("zid", this.publisherID);
    localBuilder.appendQueryParameter("adl_app_flg", "1");
    if (this.keywords != null) {
      localBuilder.appendQueryParameter("keywords", this.keywords);
    }
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    localBuilder.appendQueryParameter("displaySize", localDisplayMetrics.widthPixels + "x" + localDisplayMetrics.heightPixels);
    localBuilder.appendQueryParameter("displayDensity", Float.toString(localDisplayMetrics.density));
    return localBuilder.build();
  }
  
  public AdlantisAd[] adsForOrientation(int paramInt)
  {
    if (paramInt == 2) {
      return filteredAdsForOrientation(2);
    }
    return filteredAdsForOrientation(1);
  }
  
  public AsyncImageLoader asyncImageLoader()
  {
    return this.asyncImageLoader;
  }
  
  public void clearAds()
  {
    try
    {
      this.ads = null;
      return;
    }
    finally {}
  }
  
  public void connect(final Context paramContext, final AdManagerCallback paramAdManagerCallback)
  {
    if (!this.connectionChangeReceiverRegistered)
    {
      IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
      paramContext.getApplicationContext().registerReceiver(new ConnectionChangeReceiver(null), localIntentFilter);
      this.connectionChangeReceiverRegistered = true;
    }
    new Thread()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (paramAdManagerCallback != null) {
          paramAdManagerCallback.adsLoaded(AdManager.this);
        }
      }
    }
    {
      public void run()
      {
        AdManager.this.doAdRequest(paramContext, paramAdManagerCallback);
        Message localMessage = this.val$adLoadedHandler.obtainMessage(0, this);
        this.val$adLoadedHandler.sendMessage(localMessage);
      }
    }.start();
  }
  
  public Uri conversionTagRequestUri(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = defaultRequestBuilder(paramContext);
    paramContext.scheme("http");
    if (paramBoolean)
    {
      paramContext.authority(getConversionTagTestHost());
      paramContext.path("/ctt");
    }
    for (;;)
    {
      paramContext.appendQueryParameter("tid", paramString);
      paramContext.appendQueryParameter("output", "js");
      return paramContext.build();
      paramContext.authority(getConversionTagHost());
      paramContext.path("/sp/conv");
    }
  }
  
  /* Error */
  HashMap<String, String> defaultParameters(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: new 401	java/util/HashMap
    //   22: dup
    //   23: invokespecial 601	java/util/HashMap:<init>	()V
    //   26: putfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   29: aload_1
    //   30: ifnull +18 -> 48
    //   33: aload_0
    //   34: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   37: ldc_w 603
    //   40: aload_1
    //   41: invokevirtual 606	android/content/Context:getPackageName	()Ljava/lang/String;
    //   44: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: aload_0
    //   49: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   52: ldc_w 612
    //   55: ldc_w 614
    //   58: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   61: pop
    //   62: getstatic 619	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   65: astore_2
    //   66: aload_2
    //   67: ifnull +38 -> 105
    //   70: aload_0
    //   71: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   74: ldc_w 621
    //   77: aload_2
    //   78: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   81: pop
    //   82: invokestatic 627	java/text/NumberFormat:getNumberInstance	()Ljava/text/NumberFormat;
    //   85: aload_2
    //   86: invokevirtual 630	java/text/NumberFormat:parse	(Ljava/lang/String;)Ljava/lang/Number;
    //   89: invokevirtual 631	java/lang/Object:toString	()Ljava/lang/String;
    //   92: astore_2
    //   93: aload_0
    //   94: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   97: ldc_w 633
    //   100: aload_2
    //   101: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   104: pop
    //   105: getstatic 638	android/os/Build:MODEL	Ljava/lang/String;
    //   108: astore_3
    //   109: aload_3
    //   110: ifnull +31 -> 141
    //   113: aload_3
    //   114: astore_2
    //   115: aload_3
    //   116: ldc_w 640
    //   119: invokevirtual 644	java/lang/String:compareTo	(Ljava/lang/String;)I
    //   122: ifne +7 -> 129
    //   125: ldc_w 646
    //   128: astore_2
    //   129: aload_0
    //   130: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   133: ldc_w 648
    //   136: aload_2
    //   137: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   140: pop
    //   141: getstatic 651	android/os/Build:BRAND	Ljava/lang/String;
    //   144: ifnull +17 -> 161
    //   147: aload_0
    //   148: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   151: ldc_w 653
    //   154: getstatic 651	android/os/Build:BRAND	Ljava/lang/String;
    //   157: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   160: pop
    //   161: getstatic 651	android/os/Build:BRAND	Ljava/lang/String;
    //   164: ifnull +17 -> 181
    //   167: aload_0
    //   168: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   171: ldc_w 655
    //   174: getstatic 658	android/os/Build:DEVICE	Ljava/lang/String;
    //   177: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   180: pop
    //   181: aload_0
    //   182: aload_1
    //   183: invokespecial 660	jp/Adlantis/Android/AdManager:md5_uniqueID	(Landroid/content/Context;)Ljava/lang/String;
    //   186: astore_1
    //   187: aload_1
    //   188: ifnull +15 -> 203
    //   191: aload_0
    //   192: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   195: ldc_w 662
    //   198: aload_1
    //   199: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   202: pop
    //   203: aload_0
    //   204: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   207: ldc_w 663
    //   210: invokestatic 436	jp/Adlantis/Android/AdManager:sdkVersion	()Ljava/lang/String;
    //   213: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   216: pop
    //   217: aload_0
    //   218: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   221: ldc_w 664
    //   224: invokestatic 440	jp/Adlantis/Android/AdManager:sdkBuild	()Ljava/lang/String;
    //   227: invokevirtual 610	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   230: pop
    //   231: aload_0
    //   232: monitorexit
    //   233: aload_0
    //   234: getfield 600	jp/Adlantis/Android/AdManager:_defaultParamMap	Ljava/util/HashMap;
    //   237: areturn
    //   238: astore_2
    //   239: aload_2
    //   240: invokevirtual 667	java/text/ParseException:printStackTrace	()V
    //   243: goto -138 -> 105
    //   246: astore_1
    //   247: aload_0
    //   248: monitorexit
    //   249: aload_1
    //   250: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	251	0	this	AdManager
    //   0	251	1	paramContext	Context
    //   65	72	2	localObject	Object
    //   238	2	2	localParseException	java.text.ParseException
    //   108	8	3	str	String
    // Exception table:
    //   from	to	target	type
    //   82	105	238	java/text/ParseException
    //   2	16	246	finally
    //   18	29	246	finally
    //   33	48	246	finally
    //   48	66	246	finally
    //   70	82	246	finally
    //   82	105	246	finally
    //   105	109	246	finally
    //   115	125	246	finally
    //   129	141	246	finally
    //   141	161	246	finally
    //   161	181	246	finally
    //   181	187	246	finally
    //   191	203	246	finally
    //   203	233	246	finally
    //   239	243	246	finally
    //   247	249	246	finally
  }
  
  public Uri.Builder defaultRequestBuilder(Context paramContext, Uri paramUri)
  {
    if (paramUri != null) {}
    for (paramUri = paramUri.buildUpon();; paramUri = new Uri.Builder())
    {
      AdlantisUtils.setUriParamsFromMap(paramUri, defaultParameters(paramContext));
      return this._adNetworkConnection.appendParameters(paramUri);
    }
  }
  
  public AdNetworkConnection getAdNetworkConnection()
  {
    return this._adNetworkConnection;
  }
  
  public String getConversionTagHost()
  {
    return this._adNetworkConnection.getConversionTagHost();
  }
  
  public String getConversionTagTestHost()
  {
    return this._adNetworkConnection.getConversionTagTestHost();
  }
  
  public String getHost()
  {
    return this._adNetworkConnection.getHost();
  }
  
  public String getPublisherID()
  {
    return this.publisherID;
  }
  
  public String[] getTestAdRequestUrls()
  {
    return this._testAdRequestUrls;
  }
  
  public void handleClickRequest(String paramString, AdManagerRedirectUrlProcessedCallback paramAdManagerRedirectUrlProcessedCallback)
  {
    Object localObject = Uri.parse(paramString);
    String str = ((Uri)localObject).getScheme();
    if ((str.compareTo("http") == 0) || (str.compareTo("https") == 0)) {
      handleHttpClickRequest(paramString, paramAdManagerRedirectUrlProcessedCallback);
    }
    do
    {
      return;
      paramString = new Handler();
      localObject = paramString.obtainMessage(0, localObject);
      paramString.sendMessage((Message)localObject);
    } while (paramAdManagerRedirectUrlProcessedCallback == null);
    paramAdManagerRedirectUrlProcessedCallback.redirectProcessed((Uri)((Message)localObject).obj);
  }
  
  public String publisherIDMetadataKey()
  {
    return this._adNetworkConnection.publisherIDMetadataKey();
  }
  
  public void sendConversionTag(Context paramContext, String paramString)
  {
    sendConversionTagInternal(paramContext, paramString, false);
  }
  
  public void sendConversionTagTest(Context paramContext, String paramString)
  {
    sendConversionTagInternal(paramContext, paramString, true);
  }
  
  public void setAdNetworkConnection(AdNetworkConnection paramAdNetworkConnection)
  {
    this._adNetworkConnection = paramAdNetworkConnection;
  }
  
  public void setGapPublisherID(String paramString)
  {
    if (isGreeSdk())
    {
      this.publisherID = paramString;
      return;
    }
    throw new AdlantisInvalidPublisherIDException();
  }
  
  public void setHost(String paramString)
  {
    this._adNetworkConnection.setHost(paramString);
  }
  
  public void setKeywords(String paramString)
  {
    this.keywords = paramString;
  }
  
  public void setPublisherID(String paramString)
  {
    if (isGreeSdk()) {
      throw new AdlantisInvalidPublisherIDException();
    }
    this.publisherID = paramString;
  }
  
  public void setTestAdRequestUrls(String[] paramArrayOfString)
  {
    Log.d(DEBUG_TASK, "setting test AdRequestUrls");
    this._testAdRequestUrls = paramArrayOfString;
  }
  
  public static abstract interface AdManagerCallback
  {
    public abstract void adsLoaded(AdManager paramAdManager);
  }
  
  private static class AdManagerHolder
  {
    private static final AdManager INSTANCE = new AdManager(null);
    
    private AdManagerHolder() {}
  }
  
  public static abstract interface AdManagerRedirectUrlProcessedCallback
  {
    public abstract void redirectProcessed(Uri paramUri);
  }
  
  public class AdlantisInvalidPublisherIDException
    extends RuntimeException
  {
    private static final long serialVersionUID = 5482554095917964689L;
    
    public AdlantisInvalidPublisherIDException() {}
  }
  
  private class ConnectionChangeReceiver
    extends BroadcastReceiver
  {
    private ConnectionChangeReceiver() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if ((AdManager.isNetworkAvailable(paramContext)) && (AdManager.this.conversionTag != null) && (!AdManager.this.conversionTagSent)) {
        AdManager.this.sendConversionTag(paramContext, AdManager.this.conversionTag);
      }
    }
  }
}
