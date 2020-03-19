package o;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.provider.CalendarContract.Events;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.pinger.adlib.activities.AdLibBrowser;
import com.pinger.adlib.net.base.exceptions.HandleException;
import com.pinger.adlib.net.base.exceptions.InvalidResponseException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Ou
{
  private static int ʻ;
  private static int ʼ = 1;
  private static final byte[] ʽ = { 103, -22, 77, 119, -11, 12, -12, 5, 8, 7 };
  public static final String[] ˊ = { "3gp", "mp4", "m4a", "ts", "mkv", "m4v", "webm" };
  public static final SimpleDateFormat ˋ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
  private static Integer ˎ;
  private static Integer ˏ;
  private static Float ॱ;
  private static int ᐝ;
  
  static
  {
    ʻ = 248;
    ᐝ = 0;
  }
  
  public Ou() {}
  
  /* Error */
  public static int ʻ(Context paramContext)
  {
    // Byte code:
    //   0: goto +33 -> 33
    //   3: astore_0
    //   4: aload_0
    //   5: athrow
    //   6: getstatic 37	o/Ou:ᐝ	I
    //   9: bipush 105
    //   11: iadd
    //   12: istore_1
    //   13: iload_1
    //   14: sipush 128
    //   17: irem
    //   18: putstatic 39	o/Ou:ʼ	I
    //   21: iload_1
    //   22: iconst_2
    //   23: irem
    //   24: ifne +6 -> 30
    //   27: goto +189 -> 216
    //   30: goto +85 -> 115
    //   33: iconst_0
    //   34: istore_3
    //   35: aload_0
    //   36: invokevirtual 85	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   39: astore 5
    //   41: iconst_0
    //   42: iconst_0
    //   43: iconst_0
    //   44: invokestatic 88	o/Ou:ˏ	(SIB)Ljava/lang/String;
    //   47: astore 6
    //   49: aload 6
    //   51: invokevirtual 92	java/lang/String:intern	()Ljava/lang/String;
    //   54: astore 6
    //   56: aload 5
    //   58: ldc 94
    //   60: ldc 96
    //   62: aload 6
    //   64: invokevirtual 102	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   67: istore 4
    //   69: iload 4
    //   71: ifle +6 -> 77
    //   74: goto +136 -> 210
    //   77: bipush 97
    //   79: istore_2
    //   80: goto +93 -> 173
    //   83: iload_3
    //   84: istore_1
    //   85: iload_2
    //   86: lookupswitch	default:+26->112, 21:+50->136, 52:+79->165
    //   112: goto +107 -> 219
    //   115: aload_0
    //   116: invokevirtual 85	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   119: iload 4
    //   121: invokevirtual 106	android/content/res/Resources:getDimensionPixelSize	(I)I
    //   124: i2f
    //   125: invokestatic 109	o/Ou:ˎ	()F
    //   128: fdiv
    //   129: invokestatic 115	java/lang/Math:round	(F)I
    //   132: istore_3
    //   133: goto +5 -> 138
    //   136: iload_1
    //   137: ireturn
    //   138: getstatic 37	o/Ou:ᐝ	I
    //   141: bipush 101
    //   143: iadd
    //   144: istore_1
    //   145: iload_1
    //   146: sipush 128
    //   149: irem
    //   150: putstatic 39	o/Ou:ʼ	I
    //   153: iload_1
    //   154: iconst_2
    //   155: irem
    //   156: ifne +6 -> 162
    //   159: goto +8 -> 167
    //   162: goto +57 -> 219
    //   165: iload_3
    //   166: ireturn
    //   167: bipush 52
    //   169: istore_2
    //   170: goto -87 -> 83
    //   173: iload_3
    //   174: istore_1
    //   175: iload_2
    //   176: lookupswitch	default:+28->204, 63:+-170->6, 97:+-40->136
    //   204: goto +6 -> 210
    //   207: astore_0
    //   208: aload_0
    //   209: athrow
    //   210: bipush 63
    //   212: istore_2
    //   213: goto -40 -> 173
    //   216: goto -101 -> 115
    //   219: bipush 21
    //   221: istore_2
    //   222: goto -139 -> 83
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	225	0	paramContext	Context
    //   12	163	1	i	int
    //   79	143	2	j	int
    //   34	140	3	k	int
    //   67	53	4	m	int
    //   39	18	5	localResources	Resources
    //   47	16	6	str	String
    // Exception table:
    //   from	to	target	type
    //   41	49	3	java/lang/Exception
    //   35	41	207	java/lang/Exception
    //   41	49	207	java/lang/Exception
    //   49	56	207	java/lang/Exception
    //   56	69	207	java/lang/Exception
  }
  
  public static Rect ʼ(Context paramContext)
  {
    int j = Math.round(ˊ() / ˎ());
    int k = Math.round(ॱ() / ˎ());
    int m = paramContext.getResources().getConfiguration().orientation;
    int i;
    if (m == 1) {
      i = j;
    } else {
      i = k;
    }
    if (m == 1) {
      j = k;
    }
    return new Rect(0, 0, i, j - ᐝ(paramContext) - ʻ(paramContext));
  }
  
  private static void ʼ()
  {
    Display localDisplay = ((WindowManager)Mc.ॱ().ˊ().ˏ().getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localDisplay.getMetrics(localDisplayMetrics);
    ॱ = Float.valueOf(localDisplayMetrics.density);
    if (localDisplay.getHeight() > localDisplay.getWidth())
    {
      ˏ = Integer.valueOf(localDisplay.getWidth());
      ˎ = Integer.valueOf(localDisplay.getHeight());
      return;
    }
    ˏ = Integer.valueOf(localDisplay.getHeight());
    ˎ = Integer.valueOf(localDisplay.getWidth());
  }
  
  public static boolean ʽ(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static int ˊ()
  {
    if (ˏ == null) {
      ʼ();
    }
    return ˏ.intValue();
  }
  
  public static int ˊ(int paramInt)
  {
    return (int)(paramInt / ˎ());
  }
  
  public static String ˊ(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.loadLabel(localPackageManager).toString();
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Mb.ˊ().ˏ(Mb.iF.BASIC, "Failed to compute screen size.");
    }
    return "";
  }
  
  public static String ˊ(String paramString, int paramInt)
  {
    String str = paramString;
    if (!TextUtils.isEmpty(paramString))
    {
      str = paramString;
      if (paramString.length() > paramInt) {
        str = paramString.substring(0, paramInt) + "...";
      }
    }
    return str;
  }
  
  public static List<String> ˊ(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool;
    if ((κ.ˏ) && (paramJSONArray != null)) {
      bool = true;
    } else {
      bool = false;
    }
    ν.ˎ(bool, "The provided item should be non null");
    int i = 0;
    while (i < paramJSONArray.length())
    {
      String str = paramJSONArray.optString(i, null);
      if (!TextUtils.isEmpty(str)) {
        localArrayList.add(str);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static void ˊ(DH paramDH, String paramString, int paramInt, Map<String, String> paramMap)
  {
    Object localObject1 = LY.ॱ(String.valueOf(paramInt));
    Object localObject2 = LY.ˎ(paramString);
    localObject1 = new StringBuilder().append((String)localObject1).append(" ").append((String)localObject2).append(" [").append("AdRequest-URL").append(":http://www.").append(paramString).append(".com?");
    localObject2 = paramMap.keySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      paramString = (String)((Iterator)localObject2).next();
      StringBuilder localStringBuilder = ((StringBuilder)localObject1).append(paramString).append("=").append((String)paramMap.get(paramString));
      if (((Iterator)localObject2).hasNext()) {
        paramString = "&";
      } else {
        paramString = "";
      }
      localStringBuilder.append(paramString);
    }
    ((StringBuilder)localObject1).append("]").toString();
    LY.ˏ(paramDH, ((StringBuilder)localObject1).toString());
  }
  
  public static boolean ˊ(String paramString)
  {
    try
    {
      new JSONObject(paramString);
    }
    catch (JSONException localJSONException)
    {
      try
      {
        new JSONArray(paramString);
      }
      catch (JSONException paramString)
      {
        return false;
      }
    }
    return true;
  }
  
  public static byte[] ˊ(InputStream paramInputStream)
  {
    return ˊ(paramInputStream, 1024);
  }
  
  public static byte[] ˊ(InputStream paramInputStream, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    for (;;)
    {
      paramInt = paramInputStream.read(arrayOfByte);
      if (paramInt == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
    }
    return localByteArrayOutputStream.toByteArray();
  }
  
  @SuppressLint({"NewApi"})
  public static String ˊॱ(Context paramContext)
  {
    Object localObject = null;
    try
    {
      if (Build.VERSION.SDK_INT >= 17)
      {
        paramContext = WebSettings.getDefaultUserAgent(paramContext);
        return paramContext;
      }
      Constructor localConstructor = WebSettings.class.getDeclaredConstructor(new Class[] { Context.class, WebView.class });
      localConstructor.setAccessible(true);
      try
      {
        String str = ((WebSettings)localConstructor.newInstance(new Object[] { paramContext, null })).getUserAgentString();
        localObject = str;
        localConstructor.setAccessible(false);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localConstructor.setAccessible(false);
      }
      catch (InstantiationException localInstantiationException)
      {
        localConstructor.setAccessible(false);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localConstructor.setAccessible(false);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localConstructor.setAccessible(false);
      }
      finally
      {
        localConstructor.setAccessible(false);
      }
      if (!bool) {
        return localObject;
      }
      localObject = System.getProperty("http.agent");
      boolean bool = TextUtils.isEmpty((CharSequence)localObject);
      if (!bool) {
        return localObject;
      }
      paramContext = new WebView(paramContext).getSettings().getUserAgentString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Mb.ˊ().ˏ(Mb.iF.BASIC, "Returning default user agent: Mozilla/5.0 (Linux; Android 5.1.1; SM-G920V Build/LMY47X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36");
    }
    return "Mozilla/5.0 (Linux; Android 5.1.1; SM-G920V Build/LMY47X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/46.0.2490.76 Mobile Safari/537.36";
  }
  
  public static double ˋ()
  {
    int i = ˊ();
    int j = ॱ();
    float f = ˎ();
    double d1 = i / f;
    double d2 = j / f;
    return Math.sqrt(Math.pow(d1, 2.0D) + Math.pow(d2, 2.0D));
  }
  
  public static String ˋ(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String ˋ(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = paramString.replace('\\', '/');
    int i = paramString.lastIndexOf("/");
    if (i == -1) {
      i = 0;
    } else {
      i += 1;
    }
    return paramString.substring(i, paramString.length());
  }
  
  public static String ˋ(List<String> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      return ˏ(",", (String[])paramList.toArray(new String[paramList.size()]));
    }
    return "";
  }
  
  public static List<String> ˋ(JSONArray paramJSONArray)
  {
    if (paramJSONArray != null) {
      return ˊ(paramJSONArray);
    }
    return new ArrayList();
  }
  
  public static void ˋ(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      break;
    case 200: 
    case 204: 
    case 400: 
      return;
    }
    throw new InvalidResponseException(paramInt);
  }
  
  public static void ˋ(Context paramContext, CO paramCO, Ho paramHo)
  {
    Pair localPair = Mc.ॱ().ـ();
    ˎ(paramContext, paramCO.ॱˊ());
    paramContext = new MZ(paramCO.ॱ().getType(), paramCO.ॱˊ(), paramCO.ͺ(), paramCO.ˎˏ());
    paramContext.ॱ((Location)localPair.first, (LW.If)localPair.second);
    if (paramHo != null)
    {
      paramContext.ˋ(paramHo.isLeaveApp());
      paramContext.ˎ(paramHo.getLeaveAppDestination());
    }
    paramContext.ˎˎ();
  }
  
  @SuppressLint({"NewApi"})
  public static <Params> void ˋ(AsyncTask<Params, ?, ?> paramAsyncTask, Params... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
      return;
    }
    paramAsyncTask.execute(paramVarArgs);
  }
  
  public static boolean ˋ(Context paramContext, String paramString)
  {
    Object localObject = Uri.parse(paramString);
    paramString = ˎ(paramContext, (Uri)localObject);
    if (!TextUtils.isEmpty(paramString))
    {
      localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
      ((Intent)localObject).setPackage(paramString);
      paramContext.startActivity((Intent)localObject);
      return true;
    }
    return false;
  }
  
  public static boolean ˋॱ(Context paramContext)
  {
    return ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass() <= 64;
  }
  
  public static float ˎ()
  {
    if (ॱ == null) {
      ʼ();
    }
    return ॱ.floatValue();
  }
  
  public static int ˎ(int paramInt)
  {
    return (int)(paramInt * ˎ());
  }
  
  public static int ˎ(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception paramString) {}
    return paramInt;
  }
  
  public static long ˎ(String paramString)
  {
    try
    {
      long l = new File(paramString).length();
      return l;
    }
    catch (Exception paramString) {}
    return 0L;
  }
  
  public static Bitmap ˎ(View paramView, int paramInt1, int paramInt2)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.RGB_565);
    paramView.draw(new Canvas(localBitmap));
    return localBitmap;
  }
  
  public static Pair<Integer, Integer> ˎ(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    String str = paramContext.getNetworkOperator();
    if ((paramContext.getPhoneType() == 1) && (!TextUtils.isEmpty(str)) && (!"null".equalsIgnoreCase(str)) && (str.length() >= 4)) {
      return new Pair(Integer.valueOf(Integer.parseInt(str.substring(0, 3))), Integer.valueOf(Integer.parseInt(str.substring(3))));
    }
    return null;
  }
  
  public static String ˎ(Context paramContext, Uri paramUri)
  {
    paramUri = new Intent("android.intent.action.VIEW", paramUri);
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramUri, 0).iterator();
    while (paramContext.hasNext())
    {
      paramUri = (ResolveInfo)paramContext.next();
      if ((paramUri.activityInfo.packageName.equals("com.google.market")) || (paramUri.activityInfo.packageName.equals("com.android.vending"))) {
        return paramUri.activityInfo.packageName;
      }
    }
    return null;
  }
  
  public static void ˎ(Activity paramActivity, String paramString)
  {
    Intent localIntent2 = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    Intent localIntent1 = localIntent2;
    if (!ॱ(paramString)) {
      if (!paramString.startsWith("http://"))
      {
        localIntent1 = localIntent2;
        if (!paramString.startsWith("https://")) {}
      }
      else
      {
        localIntent1 = new Intent(paramActivity, AdLibBrowser.class);
        localIntent1.putExtra("url", paramString);
        localIntent1.putExtra("send_report_click", false);
      }
    }
    try
    {
      paramActivity.startActivity(localIntent1);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      Mb.ˊ().ˋ(Mb.iF.BASIC, "Skipped to start activity because the uri schema can not be treated!");
    }
  }
  
  public static void ˎ(Context paramContext, CO paramCO, String paramString)
  {
    Object localObject = "";
    Ho localHo = new Ho(paramCO.ॱ().getType(), paramCO.ͺ(), paramCO.ˎˏ());
    localHo.setClickReport(true);
    localHo.setClickUrl(paramString);
    Uri localUri = Uri.parse(paramString);
    Intent localIntent = new Intent("android.intent.action.VIEW", localUri);
    int i = 0;
    List localList = Mc.ॱ().ˊ().ॱˋ();
    boolean bool;
    if ((κ.ˏ) && (localList != null) && (localList.size() != 0)) {
      bool = true;
    } else {
      bool = false;
    }
    ʢ.ॱ(bool, "Action link scheme has to be non null and not empty list!!!");
    if (ॱ(paramString))
    {
      i = 1;
      paramString = (String)localObject;
      localObject = localIntent;
    }
    else if (("http".equals(localUri.getScheme())) || ("https".equals(localUri.getScheme())) || (paramString.startsWith("http://www.pingeropensafari.com")))
    {
      localIntent = new Intent(paramContext, AdLibBrowser.class);
      localIntent.putExtra("url", paramString);
      localIntent.putExtra("ad_info", localHo);
      localIntent.putExtra("ad_type", paramCO.ॱˊ().getValue());
      localIntent.putExtra("expanded", paramCO.ˉ());
      localIntent.putExtra("is_vast", paramCO.ʼॱ());
      ˎ(paramContext, paramCO.ॱˊ());
      paramString = (String)localObject;
      localObject = localIntent;
    }
    else
    {
      if (paramString.startsWith("mailto:"))
      {
        paramString = "Mail";
      }
      else if (paramString.startsWith("tel:"))
      {
        paramString = "Tel";
      }
      else if (paramString.startsWith("sms:"))
      {
        paramString = "Sms";
      }
      else if (paramString.startsWith("market:"))
      {
        paramString = "Google Play Store";
      }
      else
      {
        paramString = (String)localObject;
        if (localList.contains(localUri.getScheme()))
        {
          localIntent.setPackage(paramContext.getPackageName());
          paramString = (String)localObject;
        }
      }
      i = 1;
      localObject = localIntent;
    }
    if (i != 0)
    {
      if (!TextUtils.isEmpty(paramString)) {
        bool = true;
      } else {
        bool = false;
      }
      localHo.setLeaveApp(bool);
      localHo.setLeaveAppDestination(paramString);
      ˋ(paramContext, paramCO, localHo);
    }
    try
    {
      paramContext.startActivity((Intent)localObject);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      Mb.ˊ().ˏ(Mb.iF.BASIC, "Skipped to start activity because the uri schema can not be treated!");
    }
  }
  
  public static void ˎ(Context paramContext, DH paramDH)
  {
    Ox.ˎ(paramContext, paramDH);
    Ml.ˏ().ˏ(paramDH.getValue());
    Mh.ˎ().ˏ(paramDH.getValue());
    if (Mc.ॱ().ˏॱ()) {
      Pf.ˎ("total number of ads clicked").ˊ(new Pk[] { Pf.ˋ.APPBOY }).ˎ();
    }
  }
  
  @TargetApi(9)
  public static void ˎ(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT > 8)
    {
      paramEditor.apply();
      return;
    }
    paramEditor.commit();
  }
  
  public static void ˎ(WebView paramWebView)
  {
    if (paramWebView != null)
    {
      paramWebView.onPause();
      paramWebView.clearHistory();
      paramWebView.clearCache(true);
      paramWebView.setWebChromeClient(null);
      paramWebView.setWebViewClient(null);
      paramWebView.freeMemory();
    }
  }
  
  public static void ˎ(Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      if (ᐝ())
      {
        paramRunnable.run();
        return;
      }
      Semaphore localSemaphore = new Semaphore(0, true);
      Mc.ॱ().ˏ().post(new Ot(paramRunnable, localSemaphore));
      try
      {
        localSemaphore.acquire();
        return;
      }
      catch (Exception paramRunnable)
      {
        paramRunnable.printStackTrace();
      }
    }
  }
  
  public static boolean ˎ(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean ˎ(Bitmap paramBitmap)
  {
    return (paramBitmap != null) && (paramBitmap.getWidth() > 0) && (paramBitmap.getHeight() > 0);
  }
  
  @SuppressLint({"InlinedApi", "NewApi"})
  public static Intent ˏ(Context paramContext, MB paramMB)
  {
    paramContext = new Intent("android.intent.action.INSERT", CalendarContract.Events.CONTENT_URI);
    paramContext.putExtra("title", paramMB.ˋ());
    paramContext.putExtra("eventLocation", paramMB.ˎ());
    paramContext.putExtra("description", paramMB.ˏ());
    if (paramMB.ˊ() != null) {
      paramContext.putExtra("selfAttendeeStatus", paramMB.ˊ().getStatusCode());
    }
    if (paramMB.ॱ() != null) {
      paramContext.putExtra("beginTime", paramMB.ॱ().getTime());
    }
    if (paramMB.ʻ() != null) {
      paramContext.putExtra("endTime", paramMB.ʻ().getTime());
    }
    if (paramMB.ʽ() != null)
    {
      Mb.ˊ().ˋ(Mb.iF.BASIC, "Setting recurrence rule: " + paramMB.ʽ().ˎ());
      paramContext.putExtra("rrule", paramMB.ʽ().ˎ());
    }
    if (paramMB.ʼ() != 0L)
    {
      paramContext.putExtra("hasAlarm", true);
      paramContext.putExtra("minutes", paramMB.ʼ() / 3600000L);
    }
    return paramContext;
  }
  
  public static String ˏ(Context paramContext)
  {
    String str = "UNKNOWN";
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    paramContext = str;
    if (localNetworkInfo != null)
    {
      paramContext = str;
      if (localNetworkInfo.isConnectedOrConnecting())
      {
        if (localNetworkInfo.getType() == 1) {
          return "WIFI";
        }
        paramContext = str;
        if (localNetworkInfo.getType() == 0) {
          paramContext = localNetworkInfo.getSubtypeName();
        }
      }
    }
    return paramContext;
  }
  
  public static String ˏ(String paramString)
  {
    String str = null;
    try
    {
      paramString = new URL(paramString).getFile();
      str = paramString;
      paramString = paramString.substring(paramString.lastIndexOf(47) + 1).split("\\?")[0].split("#")[0];
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
    }
    return str;
  }
  
  public static String ˏ(String paramString, String... paramVarArgs)
  {
    if ((paramVarArgs != null) && (paramVarArgs.length > 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = 0;
      while (i < paramVarArgs.length)
      {
        localStringBuilder.append(paramVarArgs[i]);
        if (i != paramVarArgs.length - 1) {
          localStringBuilder.append(paramString);
        }
        i += 1;
      }
      return localStringBuilder.toString();
    }
    return "";
  }
  
  public static String ˏ(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = paramJSONObject.getString(paramString);
    if (TextUtils.isEmpty(paramJSONObject)) {
      throw new HandleException(paramString + " is empty");
    }
    return paramJSONObject;
  }
  
  private static String ˏ(short paramShort, int paramInt, byte paramByte)
  {
    break label15;
    int j = 1;
    break label142;
    label9:
    j = 0;
    break label142;
    label15:
    int m = 0;
    int k = 0;
    short s = 3 - paramShort * 4;
    paramShort = 97 - paramByte * 2;
    byte[] arrayOfByte1 = ʽ;
    byte b3 = paramInt * 2 + 7;
    byte[] arrayOfByte2 = new byte[b3];
    label64:
    byte b2;
    if (arrayOfByte1 != null)
    {
      paramInt = k;
      paramByte = paramInt + 1;
      arrayOfByte2[paramInt] = ((byte)paramShort);
      if (paramByte == b3) {
        break label316;
      }
    }
    else
    {
      paramByte = b3;
      b2 = s;
      break label249;
    }
    for (;;)
    {
      label95:
      s = ʼ + 123;
      ᐝ = s % 128;
      label142:
      byte b1;
      int i;
      if (s % 2 == 0)
      {
        s = paramShort;
        paramShort = paramByte;
        break label64;
        b2 = paramShort;
        s += 1;
        k = arrayOfByte1[s];
        break label223;
        b1 = paramByte;
        i = b2;
        paramInt = m;
        paramShort = s;
      }
      switch (j)
      {
      default: 
        break;
      case 1: 
        for (;;)
        {
          b1 = b2;
          i = k;
          paramInt = paramByte;
          paramShort = s;
          switch (j)
          {
          default: 
            break label327;
            paramShort = ᐝ + 1;
            ʼ = paramShort % 128;
            if (paramShort % 2 != 0)
            {
              break label327;
              paramShort = ᐝ + 35;
              ʼ = paramShort % 128;
              if (paramShort % 2 == 0) {
                break;
              }
              break label9;
            }
            j = 1;
            break;
          case 1: 
            b1 = b2 + -k + 2;
            paramInt = paramByte;
            paramShort = s;
            paramByte = b1;
            break label95;
            paramShort = paramByte + -b2 + 2;
            paramInt = k;
            break label64;
            return new String(arrayOfByte2, 0);
            j = 0;
          }
        }
        s = paramShort;
        paramShort = paramByte;
        break;
      case 0: 
        label223:
        label249:
        label316:
        label327:
        paramByte = b1 + -i + 2;
      }
    }
  }
  
  public static void ˏ(Context paramContext, DH paramDH)
  {
    LX localLX = Mc.ॱ().ˊ().ˎ();
    StringBuilder localStringBuilder = new StringBuilder().append("[").append("ProfileInfo").append(":").append("[userAgent:").append(NY.ˎ().ˊˊ()).append("]").append("[ipAddress:").append(Mc.ॱ().ॱʼ()).append("]").append("[googleAdvertisingId:").append(NY.ˎ().ॱͺ()).append("]").append("[adlibVersion:").append("16.6").append("]").append("[clientOS:").append(Build.VERSION.RELEASE).append("]").append("[deviceName:").append(Build.DEVICE).append("]").append("[language:").append(Locale.getDefault().getLanguage().toUpperCase()).append("]").append("[country:").append(Mc.ॱ().ͺॱ()).append("]").append("[iabCategories:").append(Mc.ॱ().ˎˎ()).append("]").append("[keywords:").append(Mi.ˏ().ॱ()).append("]").append("[latitude:");
    if (Mc.ॱ().ॱͺ() != null) {
      localObject = Double.valueOf(Mc.ॱ().ॱͺ().getLatitude());
    } else {
      localObject = "";
    }
    localStringBuilder = localStringBuilder.append(localObject).append("]").append("[longitude:");
    if (Mc.ॱ().ॱͺ() != null) {
      localObject = Double.valueOf(Mc.ॱ().ॱͺ().getLongitude());
    } else {
      localObject = "";
    }
    localStringBuilder = localStringBuilder.append(localObject).append("]").append("[zipCode:");
    if (localLX != null) {
      localObject = Mc.ॱ().ˊ().ˎ().ॱ();
    } else {
      localObject = "";
    }
    localStringBuilder = localStringBuilder.append((String)localObject).append("]").append("[carrier:").append(ॱ(Mc.ॱ().ˊ().ˏ())).append("]").append("[appStoreUrl:").append("https://play.google.com/store/apps/details?id=").append(Mc.ॱ().ˊ().ˏ().getPackageName()).append("]").append("[bundle:").append(Mc.ॱ().ˊ().ˏ().getPackageName()).append("]").append("[make:").append(Build.MANUFACTURER).append("]").append("[model:").append(Build.MODEL).append("]").append("[os:").append("Android").append("]").append("[osVersion:").append(Build.VERSION.RELEASE).append("]").append("[appVersion:").append(ˋ(paramContext)).append("]").append("[gender:");
    if (localLX != null) {
      localObject = Integer.valueOf(localLX.ˏ());
    } else {
      localObject = "";
    }
    localStringBuilder = localStringBuilder.append(localObject).append("]").append("[age:");
    if (localLX != null) {
      localObject = Integer.valueOf(localLX.ˎ());
    } else {
      localObject = "";
    }
    localStringBuilder = localStringBuilder.append(localObject).append("]").append("[userId:");
    if (localLX != null) {
      localObject = localLX.ˋ();
    } else {
      localObject = "";
    }
    Object localObject = localStringBuilder.append((String)localObject).append("]").append("[placement:").append(paramDH).append("]").append("[device_type:");
    if (ʽ(paramContext)) {
      paramContext = "tablet";
    } else {
      paramContext = "phone";
    }
    paramContext = ((StringBuilder)localObject).append(paramContext).append("]").append("[device_resolution:").append(ˊ() + "x" + ॱ()).append("]").append("[testModeEnabled:").append(NY.ˎ().ˊ()).append("]").append("[birthday:");
    int i;
    if (localLX != null) {
      i = localLX.ˎ();
    } else {
      i = 0;
    }
    LY.ˏ(paramDH, ॱ(i) + "]" + "]");
  }
  
  public static boolean ˏ()
  {
    NetworkInfo[] arrayOfNetworkInfo = ((ConnectivityManager)Mc.ॱ().ˊ().ˏ().getSystemService("connectivity")).getAllNetworkInfo();
    boolean bool = false;
    int i = 0;
    while ((!bool) && (i < arrayOfNetworkInfo.length)) {
      if (arrayOfNetworkInfo[i].isConnected()) {
        bool = true;
      } else {
        i += 1;
      }
    }
    return bool;
  }
  
  public static boolean ˏ(int paramInt)
  {
    Object localObject = (ConnectivityManager)Mc.ॱ().ˊ().ˏ().getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(paramInt);
      if (localObject != null) {
        return ((NetworkInfo)localObject).isConnected();
      }
    }
    return false;
  }
  
  public static boolean ˏ(long paramLong1, long paramLong2)
  {
    return (paramLong1 != -1L) && (System.currentTimeMillis() - paramLong2 > 1000L * paramLong1);
  }
  
  public static int ॱ()
  {
    if (ˎ == null) {
      ʼ();
    }
    return ˎ.intValue();
  }
  
  public static long ॱ(DH paramDH, long paramLong)
  {
    switch (OB.ॱ[paramDH.ordinal()])
    {
    default: 
      break;
    case 1: 
    case 2: 
      if ((paramLong > 0L) && (paramLong > Mc.ॱ().ˍ())) {
        return paramLong;
      }
      return Mc.ॱ().ˍ();
    case 3: 
      return Mc.ॱ().ॱʽ();
    case 4: 
      if (NY.ˎ().ـ() > 0L) {
        return NY.ˎ().ـ() * 1000L;
      }
      if (paramLong > 0L) {
        return paramLong;
      }
      return 3600000L;
    }
    return 0L;
  }
  
  public static String ॱ(int paramInt)
  {
    int i = Calendar.getInstance().get(1);
    return i - paramInt + "0101";
  }
  
  public static String ॱ(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext.getNetworkType() == 0) {
      return null;
    }
    return paramContext.getNetworkOperatorName();
  }
  
  public static String ॱ(JSONObject paramJSONObject, String paramString1, String paramString2)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = paramString1;
    arrayOfString[1] = paramString2;
    int i = arrayOfString.length;
    i = 0;
    while (i < 2)
    {
      String str = paramJSONObject.optString(arrayOfString[i]);
      if (!TextUtils.isEmpty(str)) {
        return str;
      }
      i += 1;
    }
    throw new HandleException(paramString1 + " and also " + paramString2 + " field is empty");
  }
  
  public static JSONObject ॱ(List<CM> paramList)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      JSONObject localJSONObject1 = new JSONObject();
      if (paramList != null)
      {
        paramList = new ArrayList(paramList).iterator();
        while (paramList.hasNext())
        {
          CM localCM = (CM)paramList.next();
          JSONObject localJSONObject2 = new JSONObject();
          localJSONObject2.put("adId", localCM.ˋ());
          localJSONObject2.put("network", localCM.ˊ());
          localJSONObject2.put("isFilled", localCM.ॱ().getId());
          localJSONObject2.put("screenName", localCM.ᐝ());
          if ((!localCM.ˏ().equals("default")) && (!localCM.ˊ().equals(DB.Pinger.getType()))) {
            if (localCM.ʼ()) {
              localJSONObject2.put("latencyGet", localCM.ˎ() + localCM.ʽ().doubleValue());
            } else {
              localJSONObject2.put("latencyGet", localCM.ˎ());
            }
          }
          if (!localCM.ʼ()) {
            if (localCM.ॱ() == DG.TIMEOUT_SHOW) {
              localJSONObject2.put("latencyShow", localCM.ʽ());
            } else if (localCM.ॱ() == DG.FILLED) {
              localJSONObject2.put("latencyShow", localCM.ʽ().doubleValue() + localCM.ˎ());
            }
          }
          if (localCM.ˊॱ() != null) {
            localJSONObject2.put("info", localCM.ˊॱ());
          }
          if (!TextUtils.isEmpty(localCM.ʻ())) {
            localJSONObject2.put("connectionType", localCM.ʻ());
          }
          if (localCM.ͺ() > 0L) {
            localJSONObject2.put("adSize", String.valueOf(localCM.ͺ()));
          }
          localJSONArray.put(localJSONObject2);
        }
      }
      localJSONObject1.put("latencies", localJSONArray);
      return localJSONObject1;
    }
    catch (JSONException paramList) {}
    return null;
  }
  
  public static void ॱ(InputStream paramInputStream, OutputStream paramOutputStream, int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    for (;;)
    {
      paramInt = paramInputStream.read(arrayOfByte);
      if (paramInt == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, paramInt);
    }
  }
  
  public static void ॱ(DH paramDH, String paramString1, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString2)) {
      ॱ(paramDH, paramString1, Arrays.asList(paramString2.split(",")));
    }
  }
  
  public static void ॱ(DH paramDH, String paramString, List<String> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        String str = (String)paramList.next();
        if (!TextUtils.isEmpty(str))
        {
          Mb.ˊ().ˎ(paramDH, "Handling ad event [event=" + paramString + "], [url=" + str + "]");
          new MR(str, paramDH).ˏ(new Ov(paramDH, paramString));
        }
      }
    }
  }
  
  public static boolean ॱ(Context paramContext, Uri paramUri)
  {
    if (!TextUtils.isEmpty(ˎ(paramContext, paramUri))) {
      return ˎ(paramContext, paramUri.getQueryParameter("id"));
    }
    return false;
  }
  
  public static boolean ॱ(Context paramContext, LL paramLL, String paramString)
  {
    paramLL = Uri.parse(paramString);
    paramString = Mc.ॱ().ˊ().ॱˋ();
    boolean bool;
    if ((κ.ˏ) && (paramString != null) && (paramString.size() != 0)) {
      bool = true;
    } else {
      bool = false;
    }
    ʢ.ॱ(bool, "Action link scheme has to be non null and not empty list!!!");
    if (paramString.contains(paramLL.getScheme()))
    {
      paramLL = new Intent("android.intent.action.VIEW", paramLL);
      paramLL.setPackage(paramContext.getPackageName());
      paramContext.startActivity(paramLL);
      return true;
    }
    return false;
  }
  
  public static boolean ॱ(String paramString)
  {
    String[] arrayOfString = ˊ;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      if (paramString.endsWith("." + str)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean ॱ(CO paramCO)
  {
    return (paramCO != null) && (ˏ(paramCO.ˏ(), paramCO.ͺॱ()));
  }
  
  public static boolean ॱ(String... paramVarArgs)
  {
    if (paramVarArgs == null) {
      return true;
    }
    if (paramVarArgs.length == 0) {
      return true;
    }
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      if (TextUtils.isEmpty(paramVarArgs[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static int ᐝ(Context paramContext)
  {
    int i = 0;
    TypedValue localTypedValue = new TypedValue();
    if (Build.VERSION.SDK_INT < 11) {
      i = paramContext.getResources().getDimensionPixelSize(paramContext.getResources().getIdentifier("abs__action_bar_default_height", "dimen", paramContext.getPackageName()));
    } else if (paramContext.getTheme().resolveAttribute(16843499, localTypedValue, true)) {
      i = TypedValue.complexToDimensionPixelSize(localTypedValue.data, paramContext.getResources().getDisplayMetrics());
    }
    return Math.round(i / ˎ());
  }
  
  public static boolean ᐝ()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
}
