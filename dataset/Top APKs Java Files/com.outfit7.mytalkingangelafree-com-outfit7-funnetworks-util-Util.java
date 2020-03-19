package com.outfit7.funnetworks.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView.ScaleType;
import com.outfit7.funnetworks.R.string;
import com.outfit7.funnetworks.grid.GridManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

public class Util
{
  private static String APP_TOKEN;
  public static final int COMMON_TALKING_ACTIVITY = 102;
  public static final int FLAGS_IMMERSIVE_STICKY = 4098;
  private static final HashMap<String, Typeface> FONTS_HASH_MAP = new HashMap();
  private static final String[] KNOWN_APP_IDS;
  public static final int MAX_VIEW_SIZE = 16777215;
  private static final String PREF_APP_TOKEN = "o7appToken";
  public static final String RESTORED_UID_FILE_NAME = ".uid-restored";
  public static final int SYSTEM_UI_FLAG_IMMERSIVE_STICKY = 4096;
  private static final String UDID_FILE_NAME = ".udid";
  public static final String UID_FILE_NAME = ".uid";
  private static String cachedUID;
  private static Map<String, Lock> dataFileLocks;
  private static Boolean isO7SignedAppCache = null;
  private static final Map<String, JsonNode> jsonCache;
  private static int mRequestFileCounter;
  private static Intent mRequestFileIntent;
  public static Thread mainThread;
  private static final ObjectMapper mapper;
  
  static
  {
    APP_TOKEN = null;
    KNOWN_APP_IDS = new String[] { "com.outfit7.mytalkingtomfree", "com.outfit7.mytalkingangelafree", "com.outfit7.talkingtomgoldrun", "com.outfit7.mytalkinghank", "com.outfit7.talkingangelacolorsplash", "com.outfit7.jigtyfree", "com.outfit7.talkingtom2free", "com.outfit7.talkingtompool", "com.outfit7.talkingtom", "com.outfit7.talkingangelafree", "com.outfit7.tomsbubbles", "com.outfit7.gingersbirthdayfree", "com.outfit7.tomsjetski", "com.outfit7.talkingben", "com.outfit7.movingeye.swampattack", "com.outfit7.talkinggingerfree", "com.outfit7.talkingtomcamp", "com.outfit7.talkingtomcandyrun", "com.outfit7.talkingtomjetski2", "com.outfit7.mytalkingtom2", "com.outfit7.talkingtomcakejump" };
    dataFileLocks = new HashMap();
    mapper = new ObjectMapper();
    jsonCache = new Util.2();
    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    mRequestFileCounter = 0;
  }
  
  public Util() {}
  
  public static <T> T JSONStringToObj(String paramString, Class<T> paramClass)
    throws IOException
  {
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.length() != 0) {}
    }
    else
    {
      str = "{}";
    }
    paramString = mapper.readTree(str);
    return mapper.readValue(paramString, paramClass);
  }
  
  public static void JSONToExistingObj(Context arg0, String paramString, Object paramObject)
    throws IOException
  {
    synchronized (jsonCache)
    {
      JsonNode localJsonNode = (JsonNode)jsonCache.get(paramString);
      ??? = localJsonNode;
      if (localJsonNode == null)
      {
        ??? = retrieveData(???, paramString);
        if (??? != null)
        {
          ??? = (Context)???;
          if (((String)???).length() != 0) {}
        }
        else
        {
          ??? = "{}";
        }
        ??? = mapper.readTree(???);
      }
    }
    synchronized (jsonCache)
    {
      jsonCache.put(paramString, ???);
      mapper.readerForUpdating(paramObject).readValue((JsonNode)???);
      return;
      ??? = finally;
      throw ???;
    }
  }
  
  public static <T> T JSONToObj(Context arg0, String paramString, Class<T> paramClass)
    throws IOException
  {
    synchronized (jsonCache)
    {
      JsonNode localJsonNode = (JsonNode)jsonCache.get(paramString);
      ??? = localJsonNode;
      if (localJsonNode == null)
      {
        ??? = retrieveData(???, paramString);
        if (??? != null)
        {
          ??? = (Context)???;
          if (((String)???).length() != 0) {}
        }
        else
        {
          ??? = "{}";
        }
        ??? = mapper.readTree(???);
      }
    }
    synchronized (jsonCache)
    {
      jsonCache.put(paramString, ???);
      return mapper.readValue((JsonNode)???, paramClass);
      ??? = finally;
      throw ???;
    }
  }
  
  /* Error */
  public static <T> T JSONToObjNoJsonProcessingException(Context paramContext, String paramString, Class<T> paramClass)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 124	com/outfit7/funnetworks/util/Util:jsonCache	Ljava/util/Map;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: getstatic 124	com/outfit7/funnetworks/util/Util:jsonCache	Ljava/util/Map;
    //   9: aload_1
    //   10: invokeinterface 172 2 0
    //   15: checkcast 174	org/codehaus/jackson/JsonNode
    //   18: astore 4
    //   20: aload_3
    //   21: monitorexit
    //   22: aload 4
    //   24: astore_3
    //   25: aload 4
    //   27: ifnonnull +54 -> 81
    //   30: aload_0
    //   31: aload_1
    //   32: invokestatic 178	com/outfit7/funnetworks/util/Util:retrieveData	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   35: astore_3
    //   36: aload_3
    //   37: ifnull +12 -> 49
    //   40: aload_3
    //   41: astore_0
    //   42: aload_3
    //   43: invokevirtual 151	java/lang/String:length	()I
    //   46: ifne +6 -> 52
    //   49: ldc -103
    //   51: astore_0
    //   52: getstatic 119	com/outfit7/funnetworks/util/Util:mapper	Lorg/codehaus/jackson/map/ObjectMapper;
    //   55: aload_0
    //   56: invokevirtual 157	org/codehaus/jackson/map/ObjectMapper:readTree	(Ljava/lang/String;)Lorg/codehaus/jackson/JsonNode;
    //   59: astore_0
    //   60: getstatic 124	com/outfit7/funnetworks/util/Util:jsonCache	Ljava/util/Map;
    //   63: astore_3
    //   64: aload_3
    //   65: monitorenter
    //   66: getstatic 124	com/outfit7/funnetworks/util/Util:jsonCache	Ljava/util/Map;
    //   69: aload_1
    //   70: aload_0
    //   71: invokeinterface 182 3 0
    //   76: pop
    //   77: aload_3
    //   78: monitorexit
    //   79: aload_0
    //   80: astore_3
    //   81: getstatic 119	com/outfit7/funnetworks/util/Util:mapper	Lorg/codehaus/jackson/map/ObjectMapper;
    //   84: aload_3
    //   85: aload_2
    //   86: invokevirtual 161	org/codehaus/jackson/map/ObjectMapper:readValue	(Lorg/codehaus/jackson/JsonNode;Ljava/lang/Class;)Ljava/lang/Object;
    //   89: areturn
    //   90: astore_0
    //   91: aload_3
    //   92: monitorexit
    //   93: aload_0
    //   94: athrow
    //   95: astore_0
    //   96: ldc -57
    //   98: ldc -55
    //   100: aload_0
    //   101: invokestatic 207	com/outfit7/funnetworks/util/Logger:error	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   104: getstatic 119	com/outfit7/funnetworks/util/Util:mapper	Lorg/codehaus/jackson/map/ObjectMapper;
    //   107: ldc -103
    //   109: invokevirtual 157	org/codehaus/jackson/map/ObjectMapper:readTree	(Ljava/lang/String;)Lorg/codehaus/jackson/JsonNode;
    //   112: astore_0
    //   113: goto -53 -> 60
    //   116: astore_0
    //   117: aload_3
    //   118: monitorexit
    //   119: aload_0
    //   120: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	paramContext	Context
    //   0	121	1	paramString	String
    //   0	121	2	paramClass	Class<T>
    //   18	8	4	localJsonNode	JsonNode
    // Exception table:
    //   from	to	target	type
    //   6	22	90	finally
    //   91	93	90	finally
    //   52	60	95	org/codehaus/jackson/JsonProcessingException
    //   66	79	116	finally
    //   117	119	116	finally
  }
  
  public static <T> void ObjToJSON(Context paramContext, String paramString, T paramT)
    throws IOException
  {
    storeData(paramContext, paramString, mapper.writeValueAsString(paramT));
  }
  
  public static <T> String ObjToJSONString(Context paramContext, T paramT)
    throws IOException
  {
    return ObjToJSONString(paramT);
  }
  
  public static <T> String ObjToJSONString(T paramT)
    throws IOException
  {
    return mapper.writeValueAsString(paramT);
  }
  
  public static String SHA1(String paramString)
  {
    try
    {
      paramString = SHA1(paramString.getBytes("UTF-8"));
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static String SHA1(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = convToHex(MessageDigest.getInstance("SHA-1").digest(paramArrayOfByte));
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte) {}
    return "";
  }
  
  public static boolean canTrackAnalytics(Context paramContext)
  {
    boolean bool = false;
    if (!paramContext.getSharedPreferences(paramContext.getPackageName() + "_preferences", 0).getBoolean("opt_out_analytics", false)) {
      bool = true;
    }
    return bool;
  }
  
  public static void changeLanguage(Context paramContext, Locale paramLocale)
  {
    System.currentTimeMillis();
    paramContext = paramContext.getApplicationContext().getResources();
    DisplayMetrics localDisplayMetrics = paramContext.getDisplayMetrics();
    Configuration localConfiguration = paramContext.getConfiguration();
    if (Build.VERSION.SDK_INT < 17) {
      localConfiguration.locale = paramLocale;
    }
    for (;;)
    {
      paramContext.updateConfiguration(localConfiguration, localDisplayMetrics);
      return;
      localConfiguration.setLocale(paramLocale);
    }
  }
  
  public static boolean checkAndResetGotPushNotification(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences("prefs", 0);
    if (!paramContext.getBoolean(paramString, false)) {
      return false;
    }
    paramContext = paramContext.edit();
    paramContext.putBoolean(paramString, false);
    paramContext.commit();
    return true;
  }
  
  public static boolean checkCameraHardware(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public static boolean checkFileExists(Context paramContext, URL paramURL)
  {
    return checkFileExists(paramContext, paramURL, false);
  }
  
  public static boolean checkFileExists(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    label63:
    for (;;)
    {
      try
      {
        paramURL = createFileName(paramURL);
        if (paramBoolean) {}
        try
        {
          paramURL = new File(getExternalFilesDir(paramContext), paramURL);
          if ((!paramURL.exists()) || (paramURL.length() <= 0L)) {
            break label63;
          }
          paramBoolean = true;
          return paramBoolean;
        }
        finally {}
        paramURL = paramContext.getFileStreamPath(paramURL);
        continue;
        paramBoolean = false;
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
  }
  
  public static boolean checkSdCard()
  {
    boolean bool = false;
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str)) {
      bool = true;
    }
    while (!"mounted_ro".equals(str)) {
      return bool;
    }
    return false;
  }
  
  public static void clearIsO7SignedAppCache()
  {
    isO7SignedAppCache = null;
  }
  
  public static <T> T[] concatArray(T[] paramArrayOfT1, T[] paramArrayOfT2)
  {
    Object[] arrayOfObject = Arrays.copyOf(paramArrayOfT1, paramArrayOfT1.length + paramArrayOfT2.length);
    System.arraycopy(paramArrayOfT2, 0, arrayOfObject, paramArrayOfT1.length, paramArrayOfT2.length);
    return arrayOfObject;
  }
  
  public static String convToHex(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() == 1) {
        localStringBuffer.append('0');
      }
      localStringBuffer.append(str);
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  public static int convertDpToPx(DisplayMetrics paramDisplayMetrics, float paramFloat)
  {
    return Math.round(paramDisplayMetrics.densityDpi / 160.0F * paramFloat);
  }
  
  public static float convertPxToDp(DisplayMetrics paramDisplayMetrics, int paramInt)
  {
    return paramInt / (paramDisplayMetrics.densityDpi / 160.0F);
  }
  
  public static String createFileName(URL paramURL)
  {
    return "F" + paramURL.getPath().replace('/', '_');
  }
  
  public static URL createURL(String paramString)
  {
    try
    {
      paramString = new URL("http://cdn.outfit7.com/" + paramString.replace("F_", "").replace('_', '/'));
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static Bitmap cropBitmapWithMask(Bitmap paramBitmap1, Bitmap paramBitmap2)
  {
    return cropBitmapWithMask(paramBitmap1, paramBitmap2, null);
  }
  
  public static Bitmap cropBitmapWithMask(Bitmap paramBitmap1, Bitmap paramBitmap2, Paint paramPaint)
  {
    return cropBitmapWithMask(paramBitmap1, paramBitmap2, true, paramPaint, null);
  }
  
  public static Bitmap cropBitmapWithMask(Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean, Paint paramPaint, ImageView.ScaleType paramScaleType)
  {
    Bitmap localBitmap;
    Canvas localCanvas;
    if (paramBoolean)
    {
      localBitmap = Bitmap.createBitmap(paramBitmap2.getWidth(), paramBitmap2.getHeight(), Bitmap.Config.ARGB_8888);
      localCanvas = new Canvas(localBitmap);
      if (paramPaint == null) {
        break label117;
      }
    }
    label117:
    for (paramPaint = new Paint(paramPaint);; paramPaint = new Paint())
    {
      paramPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      if (paramScaleType == null) {
        break label154;
      }
      if (!paramBoolean) {
        break label128;
      }
      paramScaleType = getScaleTypeMatrix(paramBitmap1, paramBitmap2, paramScaleType);
      localCanvas.drawBitmap(paramBitmap2, 0.0F, 0.0F, null);
      localCanvas.drawBitmap(paramBitmap1, paramScaleType, paramPaint);
      return localBitmap;
      localBitmap = Bitmap.createBitmap(paramBitmap1.getWidth(), paramBitmap1.getHeight(), Bitmap.Config.ARGB_8888);
      break;
    }
    label128:
    localCanvas.drawBitmap(paramBitmap2, getScaleTypeMatrix(paramBitmap2, paramBitmap1, paramScaleType), null);
    localCanvas.drawBitmap(paramBitmap1, 0.0F, 0.0F, paramPaint);
    return localBitmap;
    label154:
    localCanvas.drawBitmap(paramBitmap2, 0.0F, 0.0F, null);
    localCanvas.drawBitmap(paramBitmap1, 0.0F, 0.0F, paramPaint);
    return localBitmap;
  }
  
  public static boolean deleteFile(Context paramContext, URL paramURL)
  {
    return deleteFile(paramContext, paramURL, false);
  }
  
  public static boolean deleteFile(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    try
    {
      paramURL = createFileName(paramURL);
      if (paramBoolean) {}
      try
      {
        paramBoolean = new File(getExternalFilesDir(paramContext), paramURL).delete();
        return paramBoolean;
      }
      finally {}
      paramBoolean = paramContext.deleteFile(paramURL);
      return paramBoolean;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean deleteRecursive(File paramFile)
  {
    if (!paramFile.exists()) {}
    boolean bool;
    do
    {
      return false;
      if (!paramFile.isDirectory()) {
        return paramFile.delete();
      }
      bool = true;
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        bool &= deleteRecursive(arrayOfFile[i]);
        i += 1;
      }
    } while ((!bool) || (!paramFile.delete()));
    return true;
  }
  
  public static boolean deleteRecursive(String paramString)
  {
    return deleteRecursive(new File(paramString));
  }
  
  public static Drawable displayNinePatch(int paramInt, Context paramContext)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
    byte[] arrayOfByte = localBitmap.getNinePatchChunk();
    if (NinePatch.isNinePatchChunk(arrayOfByte)) {
      return new NinePatchDrawable(paramContext.getResources(), localBitmap, arrayOfByte, new Rect(), null);
    }
    return new BitmapDrawable(localBitmap);
  }
  
  public static Drawable displayNinePatch(int paramInt, Context paramContext, Rect paramRect)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
    byte[] arrayOfByte = localBitmap.getNinePatchChunk();
    if (NinePatch.isNinePatchChunk(arrayOfByte)) {
      return new NinePatchDrawable(paramContext.getResources(), localBitmap, arrayOfByte, paramRect, null);
    }
    return new BitmapDrawable(localBitmap);
  }
  
  public static float distanceBetweenPoints(PointF paramPointF1, PointF paramPointF2)
  {
    float f1 = paramPointF1.x - paramPointF2.x;
    float f2 = paramPointF1.y - paramPointF2.y;
    return (float)Math.sqrt(f1 * f1 + f2 * f2);
  }
  
  public static byte[] downloadToCacheBytes(Context paramContext, URL paramURL)
  {
    return downloadToCacheBytes(paramContext, paramURL, false);
  }
  
  /* Error */
  public static byte[] downloadToCacheBytes(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 608	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   4: astore_3
    //   5: aload_3
    //   6: sipush 5000
    //   9: invokevirtual 614	java/net/URLConnection:setReadTimeout	(I)V
    //   12: aload_3
    //   13: invokevirtual 617	java/net/URLConnection:connect	()V
    //   16: aload_3
    //   17: invokevirtual 621	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   20: astore_3
    //   21: aload_3
    //   22: invokestatic 627	org/springframework/util/FileCopyUtils:copyToByteArray	(Ljava/io/InputStream;)[B
    //   25: astore 4
    //   27: aload_1
    //   28: invokestatic 368	com/outfit7/funnetworks/util/Util:createFileName	(Ljava/net/URL;)Ljava/lang/String;
    //   31: astore_1
    //   32: aload_0
    //   33: monitorenter
    //   34: aload_0
    //   35: aload_1
    //   36: iload_2
    //   37: invokestatic 631	com/outfit7/funnetworks/util/Util:getFileOutputStream	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/io/FileOutputStream;
    //   40: astore_1
    //   41: aload 4
    //   43: aload_1
    //   44: invokestatic 635	org/springframework/util/FileCopyUtils:copy	([BLjava/io/OutputStream;)V
    //   47: aload_1
    //   48: ifnull +7 -> 55
    //   51: aload_1
    //   52: invokevirtual 640	java/io/FileOutputStream:close	()V
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_3
    //   58: ifnull +7 -> 65
    //   61: aload_3
    //   62: invokevirtual 643	java/io/InputStream:close	()V
    //   65: aload 4
    //   67: areturn
    //   68: astore 4
    //   70: aload_1
    //   71: ifnull +7 -> 78
    //   74: aload_1
    //   75: invokevirtual 640	java/io/FileOutputStream:close	()V
    //   78: aload 4
    //   80: athrow
    //   81: astore_1
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: athrow
    //   86: astore_0
    //   87: aload_3
    //   88: ifnull +7 -> 95
    //   91: aload_3
    //   92: invokevirtual 643	java/io/InputStream:close	()V
    //   95: aload_0
    //   96: athrow
    //   97: astore_0
    //   98: aconst_null
    //   99: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	100	0	paramContext	Context
    //   0	100	1	paramURL	URL
    //   0	100	2	paramBoolean	boolean
    //   4	88	3	localObject1	Object
    //   25	41	4	arrayOfByte	byte[]
    //   68	11	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   41	47	68	finally
    //   34	41	81	finally
    //   51	55	81	finally
    //   55	57	81	finally
    //   74	78	81	finally
    //   78	81	81	finally
    //   82	84	81	finally
    //   21	34	86	finally
    //   84	86	86	finally
    //   0	21	97	java/io/IOException
    //   61	65	97	java/io/IOException
    //   91	95	97	java/io/IOException
    //   95	97	97	java/io/IOException
  }
  
  public static Bitmap downloadToFile(Context paramContext, URL paramURL)
  {
    return downloadToFile(paramContext, paramURL, false);
  }
  
  /* Error */
  public static Bitmap downloadToFile(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 608	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   4: astore_3
    //   5: aload_3
    //   6: sipush 5000
    //   9: invokevirtual 614	java/net/URLConnection:setReadTimeout	(I)V
    //   12: aload_3
    //   13: invokevirtual 617	java/net/URLConnection:connect	()V
    //   16: aload_3
    //   17: invokevirtual 621	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   20: astore_3
    //   21: aload_3
    //   22: invokestatic 627	org/springframework/util/FileCopyUtils:copyToByteArray	(Ljava/io/InputStream;)[B
    //   25: astore 5
    //   27: aload 5
    //   29: invokestatic 654	com/outfit7/funnetworks/util/UnscaledBitmapLoader:decodeByteArray	([B)Landroid/graphics/Bitmap;
    //   32: astore 4
    //   34: aload 4
    //   36: ifnonnull +9 -> 45
    //   39: aload_3
    //   40: invokevirtual 643	java/io/InputStream:close	()V
    //   43: aconst_null
    //   44: areturn
    //   45: aload_1
    //   46: invokestatic 368	com/outfit7/funnetworks/util/Util:createFileName	(Ljava/net/URL;)Ljava/lang/String;
    //   49: astore_1
    //   50: aload_0
    //   51: monitorenter
    //   52: aload_0
    //   53: aload_1
    //   54: iload_2
    //   55: invokestatic 631	com/outfit7/funnetworks/util/Util:getFileOutputStream	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/io/FileOutputStream;
    //   58: astore_1
    //   59: aload 5
    //   61: aload_1
    //   62: invokestatic 635	org/springframework/util/FileCopyUtils:copy	([BLjava/io/OutputStream;)V
    //   65: aload_1
    //   66: ifnull +7 -> 73
    //   69: aload_1
    //   70: invokevirtual 640	java/io/FileOutputStream:close	()V
    //   73: aload_0
    //   74: monitorexit
    //   75: aload_3
    //   76: invokevirtual 643	java/io/InputStream:close	()V
    //   79: aload 4
    //   81: areturn
    //   82: astore_0
    //   83: aconst_null
    //   84: areturn
    //   85: astore 5
    //   87: aload_1
    //   88: ifnull +7 -> 95
    //   91: aload_1
    //   92: invokevirtual 640	java/io/FileOutputStream:close	()V
    //   95: aload 5
    //   97: athrow
    //   98: astore_1
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    //   103: astore_0
    //   104: goto -29 -> 75
    //   107: astore_0
    //   108: aload_3
    //   109: invokevirtual 643	java/io/InputStream:close	()V
    //   112: aload_0
    //   113: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	paramContext	Context
    //   0	114	1	paramURL	URL
    //   0	114	2	paramBoolean	boolean
    //   4	105	3	localObject1	Object
    //   32	48	4	localBitmap	Bitmap
    //   25	35	5	arrayOfByte	byte[]
    //   85	11	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   0	21	82	java/lang/Exception
    //   39	43	82	java/lang/Exception
    //   75	79	82	java/lang/Exception
    //   108	114	82	java/lang/Exception
    //   59	65	85	finally
    //   52	59	98	finally
    //   69	73	98	finally
    //   73	75	98	finally
    //   91	95	98	finally
    //   95	98	98	finally
    //   99	101	98	finally
    //   50	52	103	java/lang/Exception
    //   101	103	103	java/lang/Exception
    //   21	34	107	finally
    //   45	50	107	finally
    //   50	52	107	finally
    //   101	103	107	finally
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    return (int)((int)(paramInt * Double.parseDouble(paramContext.getResources().getString(R.string.scaleFactor))) * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable)) {
      return ((BitmapDrawable)paramDrawable).getBitmap();
    }
    int i = paramDrawable.getIntrinsicWidth();
    int j;
    if (i > 0)
    {
      j = paramDrawable.getIntrinsicHeight();
      if (j <= 0) {
        break label81;
      }
    }
    for (;;)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
      paramDrawable.draw(localCanvas);
      return localBitmap;
      i = 1;
      break;
      label81:
      j = 1;
    }
  }
  
  public static void enableImmersiveMode(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return;
    }
    paramActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
  }
  
  @TargetApi(19)
  public static boolean enableImmersiveMode(boolean paramBoolean, View paramView)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      if (paramBoolean) {
        paramView.setSystemUiVisibility(paramView.getSystemUiVisibility() | 0x1002);
      }
      for (;;)
      {
        return true;
        paramView.setSystemUiVisibility(paramView.getSystemUiVisibility() & 0xEFFD);
      }
    }
    return false;
  }
  
  public static void ensureNotUiThread()
  {
    if (!isUiThread()) {
      return;
    }
    throw new IllegalStateException("Running on UI thread");
  }
  
  public static void ensureUiThread()
  {
    if (isUiThread()) {
      return;
    }
    throw new IllegalStateException("Not running on UI thread");
  }
  
  public static <T, D> Collection<T> filter(Collection<T> paramCollection, D paramD, Util.IPredicate<T, D> paramIPredicate)
  {
    ArrayList localArrayList = new ArrayList();
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Object localObject = paramCollection.next();
      if (paramIPredicate.apply(localObject, paramD)) {
        localArrayList.add(localObject);
      }
    }
    return localArrayList;
  }
  
  public static String formatGCNumberString(int paramInt)
  {
    float f;
    if (paramInt >= 10000000)
    {
      f = paramInt / 1000000.0F;
      return String.format("%.1f", new Object[] { Float.valueOf(f) }) + "M";
    }
    if (paramInt >= 10000)
    {
      f = paramInt / 1000.0F;
      return String.format("%.1f", new Object[] { Float.valueOf(f) }) + "k";
    }
    return String.valueOf(paramInt);
  }
  
  public static String[] generateAppIDSet(Context paramContext)
  {
    LinkedHashSet localLinkedHashSet = new LinkedHashSet();
    paramContext = getIasJSONArray(paramContext);
    int i = 0;
    for (;;)
    {
      JSONObject localJSONObject;
      if (i < paramContext.length()) {
        localJSONObject = paramContext.optJSONObject(i);
      }
      try
      {
        localLinkedHashSet.add(localJSONObject.getString("id"));
        i += 1;
        continue;
        localLinkedHashSet.addAll(Arrays.asList(KNOWN_APP_IDS));
        return (String[])localLinkedHashSet.toArray(new String[localLinkedHashSet.size()]);
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  private static String generateUDID(Context paramContext)
  {
    Logger.debug("==UID== Generating unique UDID");
    Object localObject = paramContext.getSharedPreferences("prefs", 0).getString("generatedUid", null);
    if (localObject != null)
    {
      Logger.debug("==UID== Got UDID from SharedPreferences: " + (String)localObject);
      return localObject;
    }
    int i = 0;
    try
    {
      int j = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0).targetSdkVersion;
      i = j;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    if ((Build.VERSION.SDK_INT < 26) && ((!hasReadPhoneStatePermission(paramContext)) || ((i >= 23) && (Build.VERSION.SDK_INT >= 23))))
    {
      paramContext = obtainMarshmallowUDID(paramContext);
      Logger.debug("==UID== Generated UDID for M or N: " + paramContext);
      return paramContext;
    }
    localObject = new StringBuilder();
    if (Build.FINGERPRINT != null) {}
    for (paramContext = Build.FINGERPRINT;; paramContext = "")
    {
      paramContext = SHA1(paramContext + System.nanoTime());
      if ((paramContext == null) || (paramContext.trim().equals("")) || (paramContext.equals("null"))) {
        break;
      }
      Logger.debug("==UID== Generated UDID: " + paramContext);
      return paramContext;
    }
    paramContext = "OUTFIT7" + System.currentTimeMillis();
    Logger.debug("==UID== Returning fallback UDID: " + paramContext);
    return paramContext;
  }
  
  @Deprecated
  public static String getAndroidId(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return "";
    }
    return Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static String getAppId(String paramString, boolean paramBoolean)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("com.outfit7.talkingtom");
    localHashSet.add("com.outfit7.talkingben");
    localHashSet.add("com.outfit7.talkinggina");
    String str1 = paramString;
    if (paramString.endsWith("pro")) {
      str1 = paramString.substring(0, paramString.length() - 3);
    }
    paramString = str1;
    if (str1.endsWith("free")) {
      paramString = str1.substring(0, str1.length() - 4);
    }
    str1 = paramString;
    String str2 = paramString;
    if (!localHashSet.contains(paramString)) {
      str1 = paramString + "free";
    }
    while (paramBoolean)
    {
      return str1;
      str2 = paramString + "pro";
    }
    return str2;
  }
  
  public static SharedPreferences getAppSharedPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName() + "_preferences", 0);
  }
  
  public static String getAppToken(Context paramContext)
  {
    if (APP_TOKEN != null) {
      return APP_TOKEN;
    }
    APP_TOKEN = getAppSharedPreferences(paramContext).getString("o7appToken", null);
    if (APP_TOKEN != null) {
      return APP_TOKEN;
    }
    APP_TOKEN = UUID.randomUUID().toString();
    getAppSharedPreferences(paramContext).edit().putString("o7appToken", APP_TOKEN).commit();
    return APP_TOKEN;
  }
  
  private static int getCPUBogoMIPS()
  {
    Object localObject = new File("/proc/cpuinfo");
    if ((((File)localObject).exists()) && (((File)localObject).canRead())) {
      try
      {
        localObject = new Scanner((File)localObject);
        if (((Scanner)localObject).findWithinHorizon(Pattern.compile("BogoMIPS[\\s]*:[\\s]*(\\d+\\.\\d+)[\\s]*\n", 2), 1000) != null)
        {
          localObject = ((Scanner)localObject).match();
          if (((MatchResult)localObject).groupCount() > 0)
          {
            int i = Math.round(Float.parseFloat(((MatchResult)localObject).group(1)));
            return i;
          }
        }
      }
      catch (Exception localException)
      {
        Log.d("==010==", "" + localException, localException);
      }
    }
    return 0;
  }
  
  private static int getCPUFrequencyMax()
  {
    Object localObject1 = new File("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
    if ((((File)localObject1).exists()) && (((File)localObject1).canRead())) {
      try
      {
        localObject1 = new BufferedReader(new FileReader((File)localObject1));
        try
        {
          int i = Integer.parseInt(((BufferedReader)localObject1).readLine());
          return i;
        }
        finally
        {
          ((BufferedReader)localObject1).close();
        }
        return 0;
      }
      catch (Exception localException)
      {
        Log.d("==010==", "" + localException, localException);
      }
    }
  }
  
  public static int getCPUSpeed()
  {
    int i = getCPUFrequencyMax() * getNumCores() / 1000;
    if (i > 0) {}
    int j;
    do
    {
      return i;
      j = getCPUBogoMIPS();
      i = j;
    } while (j > 0);
    return -1;
  }
  
  public static String getCallingMethodSignature()
  {
    StackTraceElement localStackTraceElement = new Throwable().getStackTrace()[2];
    return "[(" + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber() + "): " + localStackTraceElement.getMethodName() + "()]";
  }
  
  public static int getConnSubtype(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null) {
      return paramContext.getSubtype();
    }
    return -1;
  }
  
  public static String getConnSubtypeName(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null) {
      return paramContext.getSubtypeName();
    }
    return null;
  }
  
  public static int getConnType(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null) {
      return paramContext.getType();
    }
    return -1;
  }
  
  public static String getConnTypeName(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null) {
      return paramContext.getTypeName();
    }
    return null;
  }
  
  public static String getCountryCode(Context paramContext)
  {
    Object localObject = null;
    try
    {
      paramContext = (Util.CCResponse)JSONToObj(paramContext, "jsonResponse", Util.CCResponse.class);
      localObject = paramContext;
      if (paramContext == null) {
        localObject = new Util.CCResponse();
      }
      return ((Util.CCResponse)localObject).clientCountryCode;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = (Context)localObject;
      }
    }
  }
  
  public static String getCustomPostJSON(Context paramContext)
  {
    return paramContext.getSharedPreferences("prefs", 0).getString("customPostJSON", "");
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return StringUtils.capitalize(str2);
    }
    return StringUtils.capitalize(str1) + " " + str2;
  }
  
  public static File getExternalFilesDir(Context paramContext)
  {
    paramContext = new File(Environment.getExternalStorageDirectory(), "/Android/data/" + paramContext.getPackageName() + "/files/");
    paramContext.mkdirs();
    return paramContext;
  }
  
  public static FileInputStream getFileInputStream(Context paramContext, String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {
      return new FileInputStream(new File(getExternalFilesDir(paramContext), paramString));
    }
    return paramContext.openFileInput(paramString);
  }
  
  public static FileOutputStream getFileOutputStream(Context paramContext, String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {
      return new FileOutputStream(new File(getExternalFilesDir(paramContext), paramString));
    }
    return paramContext.openFileOutput(paramString, 0);
  }
  
  public static SharedPreferences getGridSharedPreferences(Context paramContext)
  {
    return paramContext.getSharedPreferences("prefs", 0);
  }
  
  public static String getIPAddress()
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
      } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (SocketException localSocketException) {}
    return null;
  }
  
  public static String getIas(Context paramContext, String paramString)
  {
    paramString = "";
    JSONArray localJSONArray = getIasJSONArray(paramContext);
    int i = 0;
    paramContext = paramString;
    for (;;)
    {
      if (i >= localJSONArray.length()) {
        break label74;
      }
      try
      {
        paramString = localJSONArray.getJSONObject(i).getString("id");
        paramString = paramContext + paramString + ",";
        paramContext = paramString;
      }
      catch (Exception paramString)
      {
        for (;;)
        {
          paramString.printStackTrace();
        }
      }
      i += 1;
    }
    label74:
    paramString = paramContext;
    if (!paramContext.equals("")) {
      paramString = paramContext.substring(0, paramContext.length() - 1);
    }
    return paramString;
  }
  
  /* Error */
  public static JSONArray getIasJSONArray(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 1167	com/outfit7/funnetworks/util/Util:getGridSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore_3
    //   5: aload_3
    //   6: ldc_w 1169
    //   9: invokeinterface 1171 2 0
    //   14: ifeq +106 -> 120
    //   17: new 800	org/json/JSONArray
    //   20: dup
    //   21: aload_3
    //   22: ldc_w 1169
    //   25: aconst_null
    //   26: invokeinterface 842 3 0
    //   31: invokespecial 1172	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   34: astore_3
    //   35: aload_0
    //   36: invokestatic 1175	com/outfit7/funnetworks/util/Util:getO7Ias	(Landroid/content/Context;)Lorg/json/JSONArray;
    //   39: astore 4
    //   41: iconst_0
    //   42: istore_1
    //   43: iload_1
    //   44: aload_3
    //   45: invokevirtual 801	org/json/JSONArray:length	()I
    //   48: if_icmpge +103 -> 151
    //   51: aload_3
    //   52: iload_1
    //   53: invokevirtual 1176	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   56: astore 5
    //   58: aload_0
    //   59: aload 5
    //   61: invokestatic 1179	com/outfit7/funnetworks/util/Util:isAppInstalled	(Landroid/content/Context;Ljava/lang/String;)Z
    //   64: istore_2
    //   65: iload_2
    //   66: ifeq +31 -> 97
    //   69: new 809	org/json/JSONObject
    //   72: dup
    //   73: invokespecial 1180	org/json/JSONObject:<init>	()V
    //   76: astore 6
    //   78: aload 6
    //   80: ldc_w 807
    //   83: aload 5
    //   85: invokevirtual 1183	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   88: pop
    //   89: aload 4
    //   91: aload 6
    //   93: invokevirtual 1186	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   96: pop
    //   97: iload_1
    //   98: iconst_1
    //   99: iadd
    //   100: istore_1
    //   101: goto -58 -> 43
    //   104: astore_3
    //   105: aload_3
    //   106: invokevirtual 472	java/lang/Exception:printStackTrace	()V
    //   109: new 800	org/json/JSONArray
    //   112: dup
    //   113: invokespecial 1187	org/json/JSONArray:<init>	()V
    //   116: astore_3
    //   117: goto -82 -> 35
    //   120: new 800	org/json/JSONArray
    //   123: dup
    //   124: invokespecial 1187	org/json/JSONArray:<init>	()V
    //   127: astore_3
    //   128: goto -93 -> 35
    //   131: astore 5
    //   133: aload 5
    //   135: invokevirtual 472	java/lang/Exception:printStackTrace	()V
    //   138: goto -41 -> 97
    //   141: astore 5
    //   143: aload 5
    //   145: invokevirtual 472	java/lang/Exception:printStackTrace	()V
    //   148: goto -51 -> 97
    //   151: aload 4
    //   153: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	paramContext	Context
    //   42	59	1	i	int
    //   64	2	2	bool	boolean
    //   4	48	3	localObject	Object
    //   104	2	3	localException1	Exception
    //   116	12	3	localJSONArray1	JSONArray
    //   39	113	4	localJSONArray2	JSONArray
    //   56	28	5	str	String
    //   131	3	5	localException2	Exception
    //   141	3	5	localException3	Exception
    //   76	16	6	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   17	35	104	java/lang/Exception
    //   69	97	131	java/lang/Exception
    //   51	65	141	java/lang/Exception
    //   133	138	141	java/lang/Exception
  }
  
  public static Rect getIconRectBasedOnBackgroundPadding(Drawable paramDrawable)
  {
    Rect localRect = new Rect();
    paramDrawable.getPadding(localRect);
    int i = paramDrawable.getIntrinsicHeight() - localRect.top - localRect.bottom;
    localRect.top = 0;
    localRect.bottom = i;
    localRect.left = 0;
    localRect.right = i;
    return localRect;
  }
  
  public static String getLocalSha1(Activity paramActivity)
  {
    paramActivity = new File(getExternalFilesDir(paramActivity), "html/download");
    if ((!paramActivity.exists()) || (paramActivity.isFile())) {
      return "none";
    }
    paramActivity = paramActivity.listFiles(new Util.1());
    if ((paramActivity == null) || (paramActivity.length != 1)) {
      return "none";
    }
    return paramActivity[0].getName().replace(".zip", "");
  }
  
  public static long getMultiUserID(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 17)
    {
      UserHandle localUserHandle = android.os.Process.myUserHandle();
      return ((UserManager)paramContext.getSystemService("user")).getSerialNumberForUser(localUserHandle);
    }
    return 0L;
  }
  
  public static String getNetworkOperator(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
  }
  
  public static int getNumCores()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new Util.1CpuFilter()).length;
      return i;
    }
    catch (Exception localException) {}
    return Runtime.getRuntime().availableProcessors();
  }
  
  private static JSONArray getO7Ias(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    Iterator localIterator = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0).iterator();
    while (localIterator.hasNext())
    {
      localObject1 = ((ResolveInfo)localIterator.next()).activityInfo.packageName;
      if (((String)localObject1).contains("com.outfit7")) {
        for (;;)
        {
          Object localObject3;
          int i;
          try
          {
            Object localObject4 = paramContext.getPackageManager().getPackageInfo((String)localObject1, 1);
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.put("id", localObject1);
            localObject1 = "google";
            localObject3 = localObject1;
            if (((PackageInfo)localObject4).activities != null)
            {
              localObject4 = ((PackageInfo)localObject4).activities;
              int j = localObject4.length;
              i = 0;
              localObject3 = localObject1;
              if (i < j)
              {
                Object localObject5 = localObject4[i];
                localObject3 = localObject1;
                if (localObject5.name == null) {
                  break label313;
                }
                if (localObject5.name.equals("com.qihoo.gamecenter.sdk.activity.ContainerActivity")) {
                  localObject1 = "sdk360";
                }
                if (localObject5.name.equals("com.tencent.connect.common.AssistActivity")) {
                  localObject1 = "tencent";
                }
                if (localObject5.name.equals("com.duoku.platform.single.ui.DKStartDownloadActivity")) {
                  localObject1 = "baidu";
                }
                localObject3 = localObject1;
                if (((String)localObject1).equals("google"))
                {
                  localObject3 = localObject1;
                  if (localObject5.name.equals("cn.domob.android.ads.DomobActivity")) {
                    localObject3 = "cdn";
                  }
                }
                if (!localObject5.name.startsWith("com.outfit7.identify.build.")) {
                  break label313;
                }
                localObject3 = localObject5.name.replace("com.outfit7.identify.build.", "");
              }
            }
            localJSONObject.put("b", localObject3);
            localJSONArray.put(localJSONObject);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
          break;
          label313:
          i += 1;
          Object localObject2 = localObject3;
        }
      }
    }
    return localJSONArray;
  }
  
  public static String getO7LanguageCodeParam()
  {
    return Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
  }
  
  /* Error */
  public static String getReferrer(Context paramContext)
  {
    // Byte code:
    //   0: invokestatic 1354	com/outfit7/funnetworks/util/Util:checkSdCard	()Z
    //   3: ifeq +81 -> 84
    //   6: new 370	java/io/File
    //   9: dup
    //   10: aload_0
    //   11: invokestatic 1357	com/outfit7/funnetworks/util/Util:getSdCardApplicationDir	(Landroid/content/Context;)Ljava/io/File;
    //   14: ldc_w 1359
    //   17: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   20: astore_1
    //   21: aload_1
    //   22: invokevirtual 380	java/io/File:exists	()Z
    //   25: ifne +98 -> 123
    //   28: new 370	java/io/File
    //   31: dup
    //   32: new 370	java/io/File
    //   35: dup
    //   36: aload_0
    //   37: invokevirtual 1362	android/content/Context:getFilesDir	()Ljava/io/File;
    //   40: ldc_w 1364
    //   43: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   46: ldc_w 1359
    //   49: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   52: astore_0
    //   53: new 1366	java/io/DataInputStream
    //   56: dup
    //   57: new 1117	java/io/FileInputStream
    //   60: dup
    //   61: aload_0
    //   62: invokespecial 1118	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   65: invokespecial 1369	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   68: astore_0
    //   69: aload_0
    //   70: invokevirtual 1370	java/io/DataInputStream:readLine	()Ljava/lang/String;
    //   73: astore_1
    //   74: aload_0
    //   75: invokevirtual 1371	java/io/DataInputStream:close	()V
    //   78: aload_1
    //   79: ifnull +33 -> 112
    //   82: aload_1
    //   83: areturn
    //   84: new 370	java/io/File
    //   87: dup
    //   88: new 370	java/io/File
    //   91: dup
    //   92: aload_0
    //   93: invokevirtual 1362	android/content/Context:getFilesDir	()Ljava/io/File;
    //   96: ldc_w 1364
    //   99: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   102: ldc_w 1359
    //   105: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   108: astore_0
    //   109: goto -56 -> 53
    //   112: ldc -55
    //   114: areturn
    //   115: astore_0
    //   116: ldc -55
    //   118: areturn
    //   119: astore_0
    //   120: goto -4 -> 116
    //   123: aload_1
    //   124: astore_0
    //   125: goto -72 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	paramContext	Context
    //   20	104	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   0	21	115	java/lang/Exception
    //   53	78	115	java/lang/Exception
    //   84	109	115	java/lang/Exception
    //   21	53	119	java/lang/Exception
  }
  
  public static String getReportingID(Context paramContext)
  {
    try
    {
      paramContext = ((Util.ReportingIdResponse)JSONToObj(paramContext, "jsonResponse", Util.ReportingIdResponse.class)).reportingId;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String getRuid(Context paramContext)
  {
    paramContext = new File(paramContext.getFilesDir(), ".uid-restored");
    if (paramContext.exists()) {}
    try
    {
      DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(paramContext));
      String str = localDataInputStream.readLine();
      localDataInputStream.close();
      if (str.equals(cachedUID))
      {
        Logger.verbose("==UID== Restored UID equals cached UID, deleting restored UID.");
        paramContext.delete();
      }
      while ((str == null) || (str.length() <= 0)) {
        return "";
      }
      Logger.verbose("==UID== Found restored UID from cloud: " + str);
      return str;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Logger.verbose("==UID== Restored UID does not exist.");
      }
    }
  }
  
  public static String getSIMOperator(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
  }
  
  public static Matrix getScaleTypeMatrix(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ImageView.ScaleType paramScaleType)
  {
    if ((paramInt1 <= 0) || (paramInt2 <= 0) || (paramInt3 <= 0) || (paramInt4 <= 0)) {
      throw new RuntimeException("inWidth <= 0 (is '" + paramInt1 + "'), inHeight <= 0 (is '" + paramInt2 + "'), outWidth <= 0 (is '" + paramInt3 + "'), outHeight <= 0 (is '" + paramInt4 + "')");
    }
    Matrix localMatrix = new Matrix();
    if (ImageView.ScaleType.MATRIX == paramScaleType) {
      throw new RuntimeException("ScaleType.MATRIX not supported.");
    }
    if ((paramInt3 == paramInt1) && (paramInt4 == paramInt2)) {
      return localMatrix;
    }
    if (ImageView.ScaleType.FIT_XY == paramScaleType)
    {
      localMatrix.setScale(paramInt3 / paramInt1, paramInt4 / paramInt2);
      return localMatrix;
    }
    if (ImageView.ScaleType.CENTER == paramScaleType)
    {
      localMatrix.setTranslate((int)((paramInt3 - paramInt1) * 0.5F + 0.5F), (int)((paramInt4 - paramInt2) * 0.5F + 0.5F));
      return localMatrix;
    }
    float f1;
    float f2;
    float f3;
    if (ImageView.ScaleType.CENTER_CROP == paramScaleType)
    {
      f1 = 0.0F;
      f2 = 0.0F;
      if (paramInt1 * paramInt4 > paramInt3 * paramInt2)
      {
        f3 = paramInt4 / paramInt2;
        f1 = (paramInt3 - paramInt1 * f3) * 0.5F;
      }
      for (;;)
      {
        localMatrix.setScale(f3, f3);
        localMatrix.postTranslate((int)(f1 + 0.5F), (int)(f2 + 0.5F));
        return localMatrix;
        f3 = paramInt3 / paramInt1;
        f2 = (paramInt4 - paramInt2 * f3) * 0.5F;
      }
    }
    if (ImageView.ScaleType.CENTER_INSIDE == paramScaleType)
    {
      if ((paramInt1 <= paramInt3) && (paramInt2 <= paramInt4)) {}
      for (f1 = 1.0F;; f1 = Math.min(paramInt3 / paramInt1, paramInt4 / paramInt2))
      {
        f2 = (int)((paramInt3 - paramInt1 * f1) * 0.5F + 0.5F);
        f3 = (int)((paramInt4 - paramInt2 * f1) * 0.5F + 0.5F);
        localMatrix.setScale(f1, f1);
        localMatrix.postTranslate(f2, f3);
        return localMatrix;
      }
    }
    RectF localRectF1 = new RectF();
    RectF localRectF2 = new RectF();
    localRectF1.set(0.0F, 0.0F, paramInt1, paramInt2);
    localRectF2.set(0.0F, 0.0F, paramInt3, paramInt4);
    Matrix.ScaleToFit localScaleToFit1 = Matrix.ScaleToFit.FILL;
    Matrix.ScaleToFit localScaleToFit2 = Matrix.ScaleToFit.START;
    Matrix.ScaleToFit localScaleToFit3 = Matrix.ScaleToFit.CENTER;
    Matrix.ScaleToFit localScaleToFit4 = Matrix.ScaleToFit.END;
    paramInt1 = paramScaleType.ordinal();
    localMatrix.setRectToRect(localRectF1, localRectF2, new Matrix.ScaleToFit[] { localScaleToFit1, localScaleToFit2, localScaleToFit3, localScaleToFit4 }[(paramInt1 - 1)]);
    return localMatrix;
  }
  
  public static Matrix getScaleTypeMatrix(Bitmap paramBitmap, int paramInt1, int paramInt2, ImageView.ScaleType paramScaleType)
  {
    return getScaleTypeMatrix(paramBitmap.getWidth(), paramBitmap.getHeight(), paramInt1, paramInt2, paramScaleType);
  }
  
  public static Matrix getScaleTypeMatrix(Bitmap paramBitmap1, Bitmap paramBitmap2, ImageView.ScaleType paramScaleType)
  {
    return getScaleTypeMatrix(paramBitmap1.getWidth(), paramBitmap1.getHeight(), paramBitmap2.getWidth(), paramBitmap2.getHeight(), paramScaleType);
  }
  
  public static File getSdCardApplicationDir(Context paramContext)
  {
    return new File(Environment.getExternalStorageDirectory(), "/Android/data/" + paramContext.getPackageName() + "/");
  }
  
  public static long getSessionNumber(Context paramContext)
  {
    long l2 = paramContext.getSharedPreferences("prefsEventTrackerDevel", 0).getLong("prefsSessionIdKey", 0L);
    long l1 = l2;
    if (l2 > 0L) {
      l1 = l2 - 1L;
    }
    return l1;
  }
  
  public static String getUDID(Context paramContext)
  {
    return getUID(paramContext);
  }
  
  public static String getUDID(Context paramContext, boolean paramBoolean)
  {
    return getUID(paramContext);
  }
  
  public static String getUID(Context paramContext)
  {
    return cachedUID;
  }
  
  public static String getUniqueUserID(Context paramContext)
  {
    long l = getMultiUserID(paramContext);
    StringBuilder localStringBuilder = new StringBuilder().append(getUDID(paramContext, true));
    if (l == 0L) {}
    for (paramContext = "";; paramContext = "-" + l) {
      return paramContext;
    }
  }
  
  public static String getUserAgent(Context paramContext, String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        String str = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
        k = str.indexOf(" ");
        j = str.indexOf(":");
        if (k == -1)
        {
          i = j;
          paramContext = str;
          if (i != -1) {
            paramContext = str.substring(0, i);
          }
        }
      }
      catch (Exception paramContext)
      {
        int k;
        int j;
        int i;
        paramContext = "1.0x";
        continue;
      }
      try
      {
        paramContext = paramString1 + "Android" + paramString2 + "/" + paramContext;
        return paramContext;
      }
      catch (Exception paramContext) {}
      i = k;
      if (j != -1)
      {
        i = k;
        if (j < k) {
          i = j;
        }
      }
    }
    return "Android";
  }
  
  public static long getVerifiedCurrentTimestamp(Context paramContext)
  {
    paramContext = GridManager.getGts(paramContext);
    long l2 = System.currentTimeMillis();
    if (paramContext == null) {}
    for (long l1 = 0L;; l1 = paramContext.longValue()) {
      return Math.max(l2, l1);
    }
  }
  
  public static int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 1;
  }
  
  public static String getVersionName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static void handleUIDIntent(Activity paramActivity, Intent paramIntent)
  {
    if (paramIntent == null)
    {
      Logger.debug("==UID== Did not receive uri, polling again.");
      pollForUID(paramActivity);
      return;
    }
    paramIntent = paramIntent.getData();
    Logger.debug("==UID== Got file uri: " + paramIntent.getPath());
    try
    {
      paramIntent = paramActivity.getContentResolver().openFileDescriptor(paramIntent, "r");
      Logger.verbose("==UID== Successfully opened file: " + paramIntent.toString());
      paramIntent = paramIntent.getFileDescriptor();
      try
      {
        paramIntent = new DataInputStream(new FileInputStream(paramIntent));
        Logger.verbose("==UID== Saved new uid from external app!");
        saveUID(paramActivity, paramIntent.readLine());
        paramIntent.close();
        return;
      }
      catch (IOException paramActivity)
      {
        paramActivity.printStackTrace();
        return;
      }
      return;
    }
    catch (FileNotFoundException paramIntent)
    {
      paramIntent.printStackTrace();
      Log.e("==UID== ", "File not found. " + paramIntent.toString());
      pollForUID(paramActivity);
      return;
    }
    catch (SecurityException paramIntent)
    {
      paramIntent.printStackTrace();
      Log.e("==UID== ", "Security exception: " + paramIntent.toString());
      pollForUID(paramActivity);
    }
  }
  
  public static boolean hasPhoneRadio(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getPhoneType() != 0;
  }
  
  public static boolean hasReadPhoneStatePermission(Context paramContext)
  {
    return ActivityCompat.checkSelfPermission(paramContext, "android.permission.READ_PHONE_STATE") == 0;
  }
  
  public static boolean hasVFPSupport()
  {
    Object localObject = new File("/proc/cpuinfo");
    if ((((File)localObject).exists()) && (((File)localObject).canRead())) {
      try
      {
        localObject = new Scanner((File)localObject).findWithinHorizon(Pattern.compile("features.*vfp", 2), 1000);
        if (localObject != null) {
          return true;
        }
      }
      catch (Exception localException)
      {
        Log.d("==010==", "" + localException, localException);
      }
    }
    return false;
  }
  
  public static boolean haveData(Context paramContext, String paramString)
  {
    paramContext = new File(paramContext.getFilesDir(), paramString + ".data");
    paramString = initLock(paramString);
    paramString.lock();
    try
    {
      boolean bool = paramContext.exists();
      return bool;
    }
    finally
    {
      paramString.unlock();
    }
  }
  
  public static boolean hideSoftKeyboard(Context paramContext, View paramView)
  {
    return ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  private static Lock initLock(String paramString)
  {
    synchronized (dataFileLocks)
    {
      Lock localLock = (Lock)dataFileLocks.get(paramString);
      Object localObject = localLock;
      if (localLock == null) {
        localObject = new ReentrantLock();
      }
      try
      {
        dataFileLocks.put(paramString, localObject);
        return localObject;
      }
      finally {}
      throw paramString;
    }
  }
  
  public static boolean intersects(Rect paramRect1, Rect paramRect2)
  {
    return paramRect1.intersects(paramRect2.left, paramRect2.top, paramRect2.right, paramRect2.bottom);
  }
  
  public static boolean intersects(RectF paramRectF1, RectF paramRectF2)
  {
    return paramRectF1.intersects(paramRectF2.left, paramRectF2.top, paramRectF2.right, paramRectF2.bottom);
  }
  
  public static boolean isAppInForeground(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject == null) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      while (!((Iterator)localObject).hasNext())
      {
        return false;
        paramContext = paramContext.getPackageName();
        localObject = ((List)localObject).iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    } while ((localRunningAppProcessInfo.importance != 100) || (!localRunningAppProcessInfo.processName.equals(paramContext)));
    return true;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((paramString2 == null) || (paramString2.isEmpty())) {
      bool1 = isAppInstalled(paramContext, paramString1);
    }
    for (;;)
    {
      return bool1;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(0);
        bool1 = bool2;
        if (paramContext != null)
        {
          bool1 = bool2;
          if (!paramContext.isEmpty())
          {
            paramContext = paramContext.iterator();
            do
            {
              do
              {
                bool1 = bool2;
                if (!paramContext.hasNext()) {
                  break;
                }
                paramString1 = (ApplicationInfo)paramContext.next();
              } while (paramString1.packageName == null);
              bool1 = paramString1.packageName.startsWith(paramString2);
            } while (!bool1);
            return true;
          }
        }
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
  
  public static boolean isDigit(String paramString)
  {
    return Pattern.compile("^\\d+$").matcher(paramString).matches();
  }
  
  public static boolean isGender(String paramString)
  {
    return Pattern.compile("^(fe)?(male)$").matcher(paramString).matches();
  }
  
  @TargetApi(19)
  public static boolean isInImmersiveMode(View paramView)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 19)
    {
      bool1 = bool2;
      if (4098 == (paramView.getSystemUiVisibility() & 0x1002)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean isIpad()
  {
    return false;
  }
  
  public static boolean isNonARMArchitecture()
  {
    boolean bool1 = false;
    try
    {
      Object localObject = new ProcessBuilder(new String[] { "/system/bin/cat", "/proc/cpuinfo" }).start().getInputStream();
      int i = 0;
      StringBuilder localStringBuilder = new StringBuilder();
      byte[] arrayOfByte = new byte['က'];
      while (i != -1)
      {
        localStringBuilder.append(new String(arrayOfByte, 0, i));
        i = ((InputStream)localObject).read(arrayOfByte);
      }
      ((InputStream)localObject).close();
      localObject = localStringBuilder.toString();
      if (!((String)localObject).contains("GenuineIntel"))
      {
        boolean bool2 = ((String)localObject).contains("placeholder");
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static boolean isO7SignedApp(Context paramContext, String paramString)
  {
    if (isO7SignedAppCache != null) {
      return isO7SignedAppCache.booleanValue();
    }
    isO7SignedAppCache = Boolean.valueOf(false);
    try
    {
      paramContext = SHA1(paramContext.getPackageManager().getPackageInfo(paramString, 64).signatures[0].toByteArray());
      if ((paramContext.equals("769bdf7ba6c94cfa590137dbfa43515e6ebc0fa1")) || (paramContext.equals("3246bde9e58a7e0cdf779a7b403581ba958361c3")) || (paramContext.equals("3d652a97a1450eb5faefed4167c72d8f14902e0f")) || (paramContext.equals("2167bb1cf0dc1625a6ee9eca7940a830921cb7ff"))) {
        isO7SignedAppCache = Boolean.valueOf(true);
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
    return isO7SignedAppCache.booleanValue();
  }
  
  public static boolean isOnline(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }
  
  public static boolean isPhoneRooted()
  {
    return (new File("/system/bin/su").exists()) || (new File("/system/xbin/su").exists());
  }
  
  public static boolean isStoragePermissionGranted(Activity paramActivity)
  {
    return (Build.VERSION.SDK_INT < 23) || (paramActivity.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0);
  }
  
  public static boolean isUiThread()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }
  
  public static boolean isUidAvailable()
  {
    Logger.debug("==UID== isUidAvailable " + isUidSane(cachedUID));
    return isUidSane(cachedUID);
  }
  
  private static boolean isUidSane(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0);
  }
  
  public static boolean isWifi(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 1);
  }
  
  public static long lastModifiedData(Context paramContext, String paramString)
  {
    paramContext = new File(paramContext.getFilesDir(), paramString + ".data");
    paramString = initLock(paramString);
    paramString.lock();
    try
    {
      long l = paramContext.lastModified();
      return l;
    }
    finally
    {
      paramString.unlock();
    }
  }
  
  public static String loadAssetToString(String paramString, Context paramContext)
    throws IOException
  {
    return textStreamAsString(paramContext.getResources().getAssets().open(paramString));
  }
  
  public static byte[] loadBytesFromCache(Context paramContext, URL paramURL)
  {
    return loadBytesFromCache(paramContext, paramURL, false);
  }
  
  /* Error */
  public static byte[] loadBytesFromCache(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 368	com/outfit7/funnetworks/util/Util:createFileName	(Ljava/net/URL;)Ljava/lang/String;
    //   4: astore_1
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: aload_1
    //   9: iload_2
    //   10: invokestatic 1816	com/outfit7/funnetworks/util/Util:getFileInputStream	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/io/FileInputStream;
    //   13: astore_1
    //   14: aload_1
    //   15: invokestatic 627	org/springframework/util/FileCopyUtils:copyToByteArray	(Ljava/io/InputStream;)[B
    //   18: astore_3
    //   19: aload_1
    //   20: ifnull +7 -> 27
    //   23: aload_1
    //   24: invokevirtual 1817	java/io/FileInputStream:close	()V
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_3
    //   30: areturn
    //   31: astore_3
    //   32: aload_1
    //   33: ifnull +7 -> 40
    //   36: aload_1
    //   37: invokevirtual 1817	java/io/FileInputStream:close	()V
    //   40: aload_3
    //   41: athrow
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    //   47: astore_0
    //   48: aconst_null
    //   49: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	paramContext	Context
    //   0	50	1	paramURL	URL
    //   0	50	2	paramBoolean	boolean
    //   18	12	3	arrayOfByte	byte[]
    //   31	10	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	19	31	finally
    //   7	14	42	finally
    //   23	27	42	finally
    //   27	29	42	finally
    //   36	40	42	finally
    //   40	42	42	finally
    //   43	45	42	finally
    //   0	7	47	java/io/IOException
    //   45	47	47	java/io/IOException
  }
  
  public static Typeface loadCustomFont(String paramString, AssetManager paramAssetManager)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return null;
    }
    if (FONTS_HASH_MAP.containsKey(paramString)) {
      return (Typeface)FONTS_HASH_MAP.get(paramString);
    }
    try
    {
      paramAssetManager = Typeface.createFromAsset(paramAssetManager, paramString);
      FONTS_HASH_MAP.put(paramString, paramAssetManager);
      return paramAssetManager;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static BitmapDrawable loadImage(Context paramContext, URL paramURL)
  {
    return loadImage(paramContext, paramURL, false);
  }
  
  public static BitmapDrawable loadImage(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    paramContext = loadImageBitmap(paramContext, paramURL, paramBoolean);
    if (paramContext == null) {
      return null;
    }
    return new BitmapDrawable(paramContext);
  }
  
  public static Bitmap loadImageBitmap(Context paramContext, URL paramURL)
  {
    return loadImageBitmap(paramContext, paramURL, false);
  }
  
  /* Error */
  public static Bitmap loadImageBitmap(Context paramContext, URL paramURL, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 368	com/outfit7/funnetworks/util/Util:createFileName	(Ljava/net/URL;)Ljava/lang/String;
    //   4: astore_1
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: aload_1
    //   9: iload_2
    //   10: invokestatic 1816	com/outfit7/funnetworks/util/Util:getFileInputStream	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/io/FileInputStream;
    //   13: astore_1
    //   14: aload_1
    //   15: aconst_null
    //   16: invokestatic 1842	com/outfit7/funnetworks/util/UnscaledBitmapLoader:decodeStream	(Ljava/io/InputStream;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   19: astore_3
    //   20: aload_1
    //   21: invokevirtual 1817	java/io/FileInputStream:close	()V
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_3
    //   27: areturn
    //   28: astore_3
    //   29: aload_1
    //   30: invokevirtual 1817	java/io/FileInputStream:close	()V
    //   33: aload_3
    //   34: athrow
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    //   40: astore_0
    //   41: aconst_null
    //   42: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	paramContext	Context
    //   0	43	1	paramURL	URL
    //   0	43	2	paramBoolean	boolean
    //   19	8	3	localBitmap	Bitmap
    //   28	6	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   14	20	28	finally
    //   7	14	35	finally
    //   20	26	35	finally
    //   29	35	35	finally
    //   36	38	35	finally
    //   0	7	40	java/lang/Exception
    //   38	40	40	java/lang/Exception
  }
  
  /* Error */
  private static String loadLocalUID(Context paramContext)
  {
    // Byte code:
    //   0: new 370	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 1362	android/content/Context:getFilesDir	()Ljava/io/File;
    //   8: ldc 40
    //   10: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   13: astore 7
    //   15: aload 7
    //   17: invokevirtual 380	java/io/File:exists	()Z
    //   20: ifeq +178 -> 198
    //   23: new 255	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   30: ldc_w 1845
    //   33: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: aload 7
    //   38: invokevirtual 1848	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   41: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   47: invokestatic 837	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;)V
    //   50: aconst_null
    //   51: astore 5
    //   53: aconst_null
    //   54: astore 6
    //   56: aconst_null
    //   57: astore 4
    //   59: aconst_null
    //   60: astore_0
    //   61: aload_0
    //   62: astore_2
    //   63: aload 4
    //   65: astore_3
    //   66: new 1117	java/io/FileInputStream
    //   69: dup
    //   70: aload 7
    //   72: invokespecial 1118	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   75: astore 7
    //   77: aload_0
    //   78: astore_2
    //   79: aload 4
    //   81: astore_3
    //   82: aload 7
    //   84: invokevirtual 1852	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   87: lconst_0
    //   88: ldc2_w 1853
    //   91: iconst_1
    //   92: invokevirtual 1859	java/nio/channels/FileChannel:lock	(JJZ)Ljava/nio/channels/FileLock;
    //   95: astore_0
    //   96: aload_0
    //   97: astore_2
    //   98: aload_0
    //   99: astore_3
    //   100: new 1366	java/io/DataInputStream
    //   103: dup
    //   104: aload 7
    //   106: invokespecial 1369	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   109: astore 4
    //   111: aload 4
    //   113: invokevirtual 1370	java/io/DataInputStream:readLine	()Ljava/lang/String;
    //   116: astore_2
    //   117: aload_2
    //   118: ifnull +62 -> 180
    //   121: aload_2
    //   122: invokevirtual 877	java/lang/String:trim	()Ljava/lang/String;
    //   125: ldc -55
    //   127: invokevirtual 398	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   130: ifne +50 -> 180
    //   133: aload_2
    //   134: ldc_w 879
    //   137: invokevirtual 398	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   140: istore_1
    //   141: iload_1
    //   142: ifne +38 -> 180
    //   145: aload_0
    //   146: ifnull +7 -> 153
    //   149: aload_0
    //   150: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   153: aload 4
    //   155: ifnull +8 -> 163
    //   158: aload 4
    //   160: invokevirtual 1371	java/io/DataInputStream:close	()V
    //   163: aload_2
    //   164: areturn
    //   165: astore_0
    //   166: aload_0
    //   167: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   170: goto -17 -> 153
    //   173: astore_0
    //   174: aload_0
    //   175: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   178: aload_2
    //   179: areturn
    //   180: aload_0
    //   181: ifnull +7 -> 188
    //   184: aload_0
    //   185: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   188: aload 4
    //   190: ifnull +8 -> 198
    //   193: aload 4
    //   195: invokevirtual 1371	java/io/DataInputStream:close	()V
    //   198: aconst_null
    //   199: areturn
    //   200: astore_0
    //   201: aload_0
    //   202: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   205: goto -17 -> 188
    //   208: astore_0
    //   209: aload_0
    //   210: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   213: goto -15 -> 198
    //   216: astore_0
    //   217: aload_2
    //   218: astore_0
    //   219: aload 6
    //   221: astore_2
    //   222: aload_0
    //   223: ifnull +7 -> 230
    //   226: aload_0
    //   227: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   230: aload_2
    //   231: ifnull -33 -> 198
    //   234: aload_2
    //   235: invokevirtual 1371	java/io/DataInputStream:close	()V
    //   238: goto -40 -> 198
    //   241: astore_0
    //   242: aload_0
    //   243: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   246: goto -48 -> 198
    //   249: astore_0
    //   250: aload_0
    //   251: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   254: goto -24 -> 230
    //   257: astore_2
    //   258: aload_3
    //   259: astore_0
    //   260: aload 5
    //   262: astore_3
    //   263: aload_0
    //   264: ifnull +7 -> 271
    //   267: aload_0
    //   268: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   271: aload_3
    //   272: ifnull +7 -> 279
    //   275: aload_3
    //   276: invokevirtual 1371	java/io/DataInputStream:close	()V
    //   279: aload_2
    //   280: athrow
    //   281: astore_0
    //   282: aload_0
    //   283: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   286: goto -15 -> 271
    //   289: astore_0
    //   290: aload_0
    //   291: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   294: goto -15 -> 279
    //   297: astore_2
    //   298: aload 4
    //   300: astore_3
    //   301: goto -38 -> 263
    //   304: astore_2
    //   305: aload 4
    //   307: astore_2
    //   308: goto -86 -> 222
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	311	0	paramContext	Context
    //   140	2	1	bool	boolean
    //   62	173	2	localObject1	Object
    //   257	23	2	localObject2	Object
    //   297	1	2	localObject3	Object
    //   304	1	2	localException	Exception
    //   307	1	2	localDataInputStream1	DataInputStream
    //   65	236	3	localObject4	Object
    //   57	249	4	localDataInputStream2	DataInputStream
    //   51	210	5	localObject5	Object
    //   54	166	6	localObject6	Object
    //   13	92	7	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   149	153	165	java/io/IOException
    //   158	163	173	java/io/IOException
    //   184	188	200	java/io/IOException
    //   193	198	208	java/io/IOException
    //   66	77	216	java/lang/Exception
    //   82	96	216	java/lang/Exception
    //   100	111	216	java/lang/Exception
    //   234	238	241	java/io/IOException
    //   226	230	249	java/io/IOException
    //   66	77	257	finally
    //   82	96	257	finally
    //   100	111	257	finally
    //   267	271	281	java/io/IOException
    //   275	279	289	java/io/IOException
    //   111	117	297	finally
    //   121	141	297	finally
    //   111	117	304	java/lang/Exception
    //   121	141	304	java/lang/Exception
  }
  
  public static String loadRawResourceToString(int paramInt, Context paramContext)
    throws IOException
  {
    return textStreamAsString(paramContext.getResources().openRawResource(paramInt));
  }
  
  public static boolean loadUid(Activity paramActivity)
  {
    if (isUidAvailable()) {
      return true;
    }
    String str = loadLocalUID(paramActivity);
    if (isUidSane(str))
    {
      cachedUID = str;
      return true;
    }
    pollForUID(paramActivity);
    return false;
  }
  
  public static String obtainMarshmallowUDID(Context paramContext)
  {
    String str2 = Build.SERIAL;
    String str1 = getAndroidId(paramContext);
    paramContext = str1;
    if (str1 == null) {
      paramContext = "";
    }
    return SHA1(str2 + paramContext);
  }
  
  public static void onGamePlayStart(Activity paramActivity)
  {
    Logger.debug("==070==", "onGamePlayStart");
    if ((paramActivity != null) && (paramActivity.getIntent() != null))
    {
      Object localObject = paramActivity.getIntent();
      if ((((Intent)localObject).getExtras() != null) && (((Intent)localObject).getExtras().containsKey("action")))
      {
        String str = ((Intent)localObject).getExtras().getString("action");
        long l = ((Intent)localObject).getExtras().getLong("actionTs");
        localObject = paramActivity.getSharedPreferences("actionDuplicates", 0);
        if ((!((SharedPreferences)localObject).contains(str + l)) && (!TextUtils.isEmpty(str)))
        {
          ((SharedPreferences)localObject).edit().putBoolean(str + l, true).commit();
          ActionUtils.openByUri(paramActivity, Uri.parse(str));
        }
      }
    }
  }
  
  public static boolean openURL(String paramString, Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    try
    {
      localIntent.setData(Uri.parse(paramString));
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static void pollForUID(Activity paramActivity)
  {
    String[] arrayOfString = generateAppIDSet(paramActivity);
    if (mRequestFileCounter >= arrayOfString.length)
    {
      Logger.verbose("==UID== Unable to get UID from any installed apps.");
      if ((cachedUID == null) || (cachedUID.length() == 0))
      {
        Logger.verbose("==UID== generating uid ");
        if (!readAndSaveUIDLegacy(paramActivity)) {
          saveUID(paramActivity, UserIdConverter.udidToUid(generateUDID(paramActivity), paramActivity));
        }
      }
      return;
    }
    if (!paramActivity.getApplication().getPackageName().contains(arrayOfString[mRequestFileCounter]))
    {
      mRequestFileIntent = new Intent();
      mRequestFileIntent.setAction(arrayOfString[mRequestFileCounter] + ".UID");
      mRequestFileIntent.setType("text/plain");
      Logger.verbose("==UID== Checking for intent availability: " + mRequestFileIntent.getAction());
      if (mRequestFileIntent.resolveActivity(paramActivity.getPackageManager()) != null)
      {
        Logger.verbose("==UID== Polling app with intent action: " + mRequestFileIntent.getAction());
        mRequestFileCounter += 1;
        paramActivity.startActivityForResult(mRequestFileIntent, 102);
        return;
      }
    }
    mRequestFileCounter += 1;
    pollForUID(paramActivity);
  }
  
  public static void postOnMainThread(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  private static boolean readAndSaveUIDLegacy(Activity paramActivity)
  {
    Logger.verbose("==UID== Looking for legacy UID or UDID files.");
    if (isStoragePermissionGranted(paramActivity)) {
      if (checkSdCard())
      {
        Object localObject1 = new File(Environment.getExternalStorageDirectory(), "Android");
        Object localObject2 = new File((File)localObject1, ".uid");
        if (((File)localObject2).exists()) {
          try
          {
            localObject2 = new DataInputStream(new FileInputStream((File)localObject2));
            String str2 = ((DataInputStream)localObject2).readLine();
            ((DataInputStream)localObject2).close();
            if ((str2 != null) && (!str2.trim().equals("")) && (!str2.equals("null")))
            {
              Logger.verbose("==UID== Found legacy UID!");
              saveUID(paramActivity, str2);
              return true;
            }
          }
          catch (Exception localException) {}
        }
        localObject1 = new File((File)localObject1, ".udid");
        if (((File)localObject1).exists()) {
          try
          {
            localObject1 = new DataInputStream(new FileInputStream((File)localObject1));
            String str1 = ((DataInputStream)localObject1).readLine();
            ((DataInputStream)localObject1).close();
            if ((str1 != null) && (!str1.trim().equals("")) && (!str1.equals("null")))
            {
              Logger.verbose("==UID== Found legacy UDID, encrypting!");
              saveUID(paramActivity, UserIdConverter.udidToUid(str1, paramActivity));
              return true;
            }
          }
          catch (Exception paramActivity) {}
        }
      }
    }
    for (;;)
    {
      return false;
      Logger.verbose("==UID== Storage permission missing.");
    }
  }
  
  public static Locale readLanguage(Context paramContext)
    throws Exception
  {
    try
    {
      paramContext = new Locale(new Scanner(new File(Environment.getExternalStorageDirectory(), "O7langFile.txt")).next());
      paramContext.getISO3Language();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      throw new Exception(paramContext);
    }
  }
  
  public static void removeData(Context paramContext, String paramString)
  {
    paramContext = new File(paramContext.getFilesDir(), paramString + ".data");
    synchronized (jsonCache)
    {
      jsonCache.remove(paramString);
      paramString = initLock(paramString);
      paramString.lock();
    }
  }
  
  public static void resizeWindowOnStart(Window paramWindow)
  {
    if (paramWindow == null) {
      return;
    }
    paramWindow.setLayout(-1, -1);
  }
  
  /* Error */
  public static String retrieveData(Context paramContext, String paramString)
    throws IOException
  {
    // Byte code:
    //   0: new 370	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 1362	android/content/Context:getFilesDir	()Ljava/io/File;
    //   8: new 255	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   15: aload_1
    //   16: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: ldc_w 1608
    //   22: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   31: astore 4
    //   33: aconst_null
    //   34: astore_2
    //   35: aload_1
    //   36: invokestatic 1612	com/outfit7/funnetworks/util/Util:initLock	(Ljava/lang/String;)Ljava/util/concurrent/locks/Lock;
    //   39: astore_3
    //   40: aload_3
    //   41: invokeinterface 1617 1 0
    //   46: aload_2
    //   47: astore_0
    //   48: new 990	java/io/BufferedReader
    //   51: dup
    //   52: new 992	java/io/FileReader
    //   55: dup
    //   56: aload 4
    //   58: invokespecial 993	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   61: invokespecial 996	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   64: astore 4
    //   66: aload 4
    //   68: invokevirtual 999	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   71: astore_1
    //   72: aload_1
    //   73: astore_0
    //   74: aload 4
    //   76: invokevirtual 1004	java/io/BufferedReader:close	()V
    //   79: aload_3
    //   80: invokeinterface 1620 1 0
    //   85: aload_1
    //   86: areturn
    //   87: astore_1
    //   88: aload_2
    //   89: astore_0
    //   90: aload 4
    //   92: invokevirtual 1004	java/io/BufferedReader:close	()V
    //   95: aload_2
    //   96: astore_0
    //   97: aload_1
    //   98: athrow
    //   99: astore_1
    //   100: aload_3
    //   101: invokeinterface 1620 1 0
    //   106: aload_0
    //   107: areturn
    //   108: astore_0
    //   109: aload_3
    //   110: invokeinterface 1620 1 0
    //   115: aload_0
    //   116: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	paramContext	Context
    //   0	117	1	paramString	String
    //   34	62	2	localObject1	Object
    //   39	71	3	localLock	Lock
    //   31	60	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   66	72	87	finally
    //   48	66	99	java/io/FileNotFoundException
    //   74	79	99	java/io/FileNotFoundException
    //   90	95	99	java/io/FileNotFoundException
    //   97	99	99	java/io/FileNotFoundException
    //   48	66	108	finally
    //   74	79	108	finally
    //   90	95	108	finally
    //   97	99	108	finally
  }
  
  public static void rotateRect(Rect paramRect, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    Matrix localMatrix = new Matrix();
    RectF localRectF = new RectF(paramRect);
    localMatrix.setRotate(paramFloat1, paramFloat2, paramFloat3);
    localMatrix.mapRect(localRectF);
    paramRect.set(Math.round(localRectF.left), Math.round(localRectF.top), Math.round(localRectF.right), Math.round(localRectF.bottom));
  }
  
  public static void rotateRectF(RectF paramRectF, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.setRotate(paramFloat1, paramFloat2, paramFloat3);
    localMatrix.mapRect(paramRectF);
  }
  
  public static String sanitizeZipEntryName(String paramString)
  {
    if (!paramString.contains("..")) {
      return paramString;
    }
    return null;
  }
  
  public static void saveCustomPostJSON(Context paramContext, String paramString)
  {
    paramContext.getSharedPreferences("prefs", 0).edit().putString("customPostJSON", paramString).commit();
  }
  
  /* Error */
  public static void saveUID(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 255	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   7: ldc_w 2055
    //   10: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: aload_1
    //   14: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   17: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   20: invokestatic 837	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;)V
    //   23: aload_0
    //   24: ifnull +342 -> 366
    //   27: aconst_null
    //   28: astore 6
    //   30: aconst_null
    //   31: astore 7
    //   33: aconst_null
    //   34: astore 8
    //   36: aconst_null
    //   37: astore_3
    //   38: aload_3
    //   39: astore 5
    //   41: aload 8
    //   43: astore_2
    //   44: aload 6
    //   46: astore 4
    //   48: new 370	java/io/File
    //   51: dup
    //   52: aload_0
    //   53: invokevirtual 1362	android/content/Context:getFilesDir	()Ljava/io/File;
    //   56: ldc 40
    //   58: invokespecial 377	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   61: astore 10
    //   63: aload_3
    //   64: astore 5
    //   66: aload 8
    //   68: astore_2
    //   69: aload 6
    //   71: astore 4
    //   73: new 255	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   80: ldc_w 2057
    //   83: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload 10
    //   88: invokevirtual 1848	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   91: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   97: invokestatic 837	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;)V
    //   100: aload_3
    //   101: astore 5
    //   103: aload 8
    //   105: astore_2
    //   106: aload 6
    //   108: astore 4
    //   110: new 637	java/io/FileOutputStream
    //   113: dup
    //   114: aload 10
    //   116: invokespecial 1123	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   119: astore 9
    //   121: aload_3
    //   122: astore 5
    //   124: aload 8
    //   126: astore_2
    //   127: aload 6
    //   129: astore 4
    //   131: aload 9
    //   133: invokevirtual 2058	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   136: invokevirtual 2061	java/nio/channels/FileChannel:lock	()Ljava/nio/channels/FileLock;
    //   139: astore_3
    //   140: aload_3
    //   141: astore 5
    //   143: aload_3
    //   144: astore_2
    //   145: aload 6
    //   147: astore 4
    //   149: new 2063	java/io/BufferedWriter
    //   152: dup
    //   153: new 2065	java/io/FileWriter
    //   156: dup
    //   157: aload 10
    //   159: invokespecial 2066	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   162: invokespecial 2069	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   165: astore 6
    //   167: aload 6
    //   169: aload_1
    //   170: invokevirtual 2072	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   173: new 255	java/lang/StringBuilder
    //   176: dup
    //   177: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   180: ldc_w 2074
    //   183: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   186: aload_0
    //   187: invokevirtual 262	android/content/Context:getPackageName	()Ljava/lang/String;
    //   190: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: invokestatic 837	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;)V
    //   199: aload 6
    //   201: invokevirtual 2077	java/io/BufferedWriter:flush	()V
    //   204: aload 9
    //   206: invokevirtual 2078	java/io/FileOutputStream:flush	()V
    //   209: aload 9
    //   211: invokevirtual 2081	java/io/FileOutputStream:getFD	()Ljava/io/FileDescriptor;
    //   214: invokevirtual 2086	java/io/FileDescriptor:sync	()V
    //   217: aload_0
    //   218: invokestatic 2091	com/outfit7/funnetworks/backup/BackupHelperFactory:getInstance	(Landroid/content/Context;)Lcom/outfit7/funnetworks/backup/O7BackupHelper;
    //   221: ldc 40
    //   223: ldc 32
    //   225: invokeinterface 2097 3 0
    //   230: pop
    //   231: aload_0
    //   232: invokestatic 2101	com/outfit7/funnetworks/backup/BackupHelperFactory:dataChanged	(Landroid/content/Context;)V
    //   235: aload_3
    //   236: ifnull +7 -> 243
    //   239: aload_3
    //   240: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   243: aload 6
    //   245: ifnull +148 -> 393
    //   248: aload 6
    //   250: invokevirtual 2102	java/io/BufferedWriter:close	()V
    //   253: aload_1
    //   254: putstatic 1378	com/outfit7/funnetworks/util/Util:cachedUID	Ljava/lang/String;
    //   257: return
    //   258: astore_0
    //   259: aload_0
    //   260: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   263: goto -20 -> 243
    //   266: astore_0
    //   267: aload_0
    //   268: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   271: goto -18 -> 253
    //   274: astore_2
    //   275: aload 7
    //   277: astore_0
    //   278: aload 5
    //   280: astore_3
    //   281: aload_2
    //   282: astore 5
    //   284: aload_3
    //   285: astore_2
    //   286: aload_0
    //   287: astore 4
    //   289: aload 5
    //   291: invokevirtual 472	java/lang/Exception:printStackTrace	()V
    //   294: aload_3
    //   295: ifnull +7 -> 302
    //   298: aload_3
    //   299: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   302: aload_0
    //   303: ifnull -50 -> 253
    //   306: aload_0
    //   307: invokevirtual 2102	java/io/BufferedWriter:close	()V
    //   310: goto -57 -> 253
    //   313: astore_0
    //   314: aload_0
    //   315: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   318: goto -65 -> 253
    //   321: astore_2
    //   322: aload_2
    //   323: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   326: goto -24 -> 302
    //   329: astore_0
    //   330: aload_2
    //   331: ifnull +7 -> 338
    //   334: aload_2
    //   335: invokevirtual 1864	java/nio/channels/FileLock:release	()V
    //   338: aload 4
    //   340: ifnull +8 -> 348
    //   343: aload 4
    //   345: invokevirtual 2102	java/io/BufferedWriter:close	()V
    //   348: aload_0
    //   349: athrow
    //   350: astore_1
    //   351: aload_1
    //   352: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   355: goto -17 -> 338
    //   358: astore_1
    //   359: aload_1
    //   360: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   363: goto -15 -> 348
    //   366: ldc_w 2104
    //   369: invokestatic 837	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;)V
    //   372: goto -119 -> 253
    //   375: astore_0
    //   376: aload 6
    //   378: astore 4
    //   380: aload_3
    //   381: astore_2
    //   382: goto -52 -> 330
    //   385: astore 5
    //   387: aload 6
    //   389: astore_0
    //   390: goto -106 -> 284
    //   393: goto -140 -> 253
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	396	0	paramContext	Context
    //   0	396	1	paramString	String
    //   43	102	2	localObject1	Object
    //   274	8	2	localException1	Exception
    //   285	1	2	localObject2	Object
    //   321	14	2	localIOException	IOException
    //   381	1	2	localObject3	Object
    //   37	344	3	localObject4	Object
    //   46	333	4	localObject5	Object
    //   39	251	5	localObject6	Object
    //   385	1	5	localException2	Exception
    //   28	360	6	localBufferedWriter	BufferedWriter
    //   31	245	7	localObject7	Object
    //   34	91	8	localObject8	Object
    //   119	91	9	localFileOutputStream	FileOutputStream
    //   61	97	10	localFile	File
    // Exception table:
    //   from	to	target	type
    //   239	243	258	java/io/IOException
    //   248	253	266	java/io/IOException
    //   48	63	274	java/lang/Exception
    //   73	100	274	java/lang/Exception
    //   110	121	274	java/lang/Exception
    //   131	140	274	java/lang/Exception
    //   149	167	274	java/lang/Exception
    //   306	310	313	java/io/IOException
    //   298	302	321	java/io/IOException
    //   48	63	329	finally
    //   73	100	329	finally
    //   110	121	329	finally
    //   131	140	329	finally
    //   149	167	329	finally
    //   289	294	329	finally
    //   334	338	350	java/io/IOException
    //   343	348	358	java/io/IOException
    //   167	235	375	finally
    //   167	235	385	java/lang/Exception
  }
  
  public static void setFullScreenModeEnabled(Activity paramActivity, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramActivity.getWindow().addFlags(1024);
      paramActivity.getWindow().clearFlags(2048);
      return;
    }
    paramActivity.getWindow().addFlags(2048);
    paramActivity.getWindow().clearFlags(1024);
  }
  
  public static void setWindowToFullScreen(Dialog paramDialog)
  {
    if (paramDialog.getWindow() == null) {
      return;
    }
    paramDialog.requestWindowFeature(1);
    paramDialog.getWindow().setFlags(1024, 1024);
    paramDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
  }
  
  public static String sha1(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.reset();
      localMessageDigest.update(paramString.getBytes("UTF-8"));
      paramString = convToHex(localMessageDigest.digest());
      return paramString;
    }
    catch (Exception paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public static void showDialog(Activity paramActivity, int paramInt1, int paramInt2)
  {
    showDialog(paramActivity, paramInt1, paramInt2, false);
  }
  
  public static void showDialog(Activity paramActivity, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    paramActivity.runOnUiThread(new Util.3(paramActivity, paramInt1, paramInt2, paramBoolean));
  }
  
  public static boolean showSoftKeyboard(Context paramContext, View paramView)
  {
    return ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 1);
  }
  
  public static void storeData(Context arg0, String paramString1, String paramString2)
    throws IOException
  {
    File localFile = new File(???.getFilesDir(), paramString1 + ".data");
    synchronized (jsonCache)
    {
      jsonCache.remove(paramString1);
      ??? = initLock(paramString1);
      ???.lock();
    }
    try
    {
      paramString1 = new BufferedWriter(new FileWriter(localFile));
      try
      {
        paramString1.write(paramString2, 0, paramString2.length());
        paramString1.close();
        return;
      }
      finally
      {
        paramString1.close();
      }
      paramString1 = finally;
      throw paramString1;
    }
    finally
    {
      ???.unlock();
    }
  }
  
  public static void storeReferrer(Context paramContext, String paramString)
    throws Exception
  {
    if (checkSdCard())
    {
      paramContext = getSdCardApplicationDir(paramContext);
      paramContext.mkdirs();
    }
    for (paramContext = new File(paramContext, ".referrer");; paramContext = new File(paramContext, ".referrer"))
    {
      paramContext = new DataOutputStream(new FileOutputStream(paramContext));
      paramContext.writeBytes(paramString);
      paramContext.close();
      return;
      paramContext = new File(paramContext.getFilesDir(), "data");
      paramContext.mkdirs();
    }
  }
  
  /* Error */
  public static String textStreamAsString(InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: new 255	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   7: astore_1
    //   8: new 990	java/io/BufferedReader
    //   11: dup
    //   12: new 2177	java/io/InputStreamReader
    //   15: dup
    //   16: aload_0
    //   17: ldc -27
    //   19: invokespecial 2180	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   22: invokespecial 996	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   25: astore_0
    //   26: aload_0
    //   27: invokevirtual 999	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   30: astore_2
    //   31: aload_2
    //   32: ifnull +34 -> 66
    //   35: aload_1
    //   36: aload_2
    //   37: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: bipush 10
    //   42: invokevirtual 2183	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: goto -20 -> 26
    //   49: astore_1
    //   50: aload_0
    //   51: invokevirtual 1004	java/io/BufferedReader:close	()V
    //   54: aload_1
    //   55: athrow
    //   56: astore_0
    //   57: new 147	java/io/IOException
    //   60: dup
    //   61: aload_0
    //   62: invokespecial 2184	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   65: athrow
    //   66: aload_0
    //   67: invokevirtual 1004	java/io/BufferedReader:close	()V
    //   70: aload_1
    //   71: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   74: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramInputStream	InputStream
    //   7	29	1	localStringBuilder	StringBuilder
    //   49	22	1	localObject	Object
    //   30	7	2	str	String
    // Exception table:
    //   from	to	target	type
    //   26	31	49	finally
    //   35	46	49	finally
    //   8	26	56	java/io/UnsupportedEncodingException
    //   50	56	56	java/io/UnsupportedEncodingException
    //   66	70	56	java/io/UnsupportedEncodingException
  }
  
  public static boolean userHasFBApp(PackageManager paramPackageManager)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage("com.facebook.katana");
    List localList = paramPackageManager.queryIntentActivities(localIntent, 0);
    if ((localList != null) && (!localList.isEmpty())) {}
    do
    {
      return true;
      localIntent.setPackage("com.htc.socialnetwork.facebook");
      paramPackageManager = paramPackageManager.queryIntentActivities(localIntent, 0);
    } while ((paramPackageManager != null) && (!paramPackageManager.isEmpty()));
    return false;
  }
  
  /* Error */
  public static boolean zipFiles(String[] paramArrayOfString, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: sipush 8192
    //   9: newarray byte
    //   11: astore 7
    //   13: new 2197	java/util/zip/ZipOutputStream
    //   16: dup
    //   17: new 2199	java/io/BufferedOutputStream
    //   20: dup
    //   21: new 637	java/io/FileOutputStream
    //   24: dup
    //   25: aload_1
    //   26: invokespecial 2200	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   29: sipush 8192
    //   32: invokespecial 2203	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
    //   35: invokespecial 2204	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   38: astore_1
    //   39: aload_0
    //   40: arraylength
    //   41: istore_3
    //   42: iconst_0
    //   43: istore_2
    //   44: iload_2
    //   45: iload_3
    //   46: if_icmpge +180 -> 226
    //   49: aload_0
    //   50: iload_2
    //   51: aaload
    //   52: astore 5
    //   54: ldc_w 2206
    //   57: new 255	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   64: ldc_w 2208
    //   67: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload 5
    //   72: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: invokestatic 1886	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;Ljava/lang/String;)V
    //   81: new 2210	java/io/BufferedInputStream
    //   84: dup
    //   85: new 1117	java/io/FileInputStream
    //   88: dup
    //   89: aload 5
    //   91: invokespecial 2211	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   94: sipush 8192
    //   97: invokespecial 2214	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;I)V
    //   100: astore 6
    //   102: aload_1
    //   103: new 2216	java/util/zip/ZipEntry
    //   106: dup
    //   107: aload 5
    //   109: aload 5
    //   111: ldc_w 1476
    //   114: invokevirtual 2219	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   117: iconst_1
    //   118: iadd
    //   119: invokevirtual 2221	java/lang/String:substring	(I)Ljava/lang/String;
    //   122: invokespecial 2222	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   125: invokevirtual 2226	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   128: aload 6
    //   130: aload 7
    //   132: invokevirtual 1726	java/io/InputStream:read	([B)I
    //   135: istore 4
    //   137: iload 4
    //   139: ifle +71 -> 210
    //   142: aload_1
    //   143: aload 7
    //   145: iconst_0
    //   146: iload 4
    //   148: invokevirtual 2228	java/util/zip/ZipOutputStream:write	([BII)V
    //   151: goto -23 -> 128
    //   154: astore 5
    //   156: aload_1
    //   157: astore_0
    //   158: aload 5
    //   160: astore_1
    //   161: aload_0
    //   162: astore 5
    //   164: aload_1
    //   165: invokevirtual 472	java/lang/Exception:printStackTrace	()V
    //   168: aload_0
    //   169: astore 5
    //   171: ldc_w 2206
    //   174: new 255	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   181: ldc_w 2230
    //   184: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload_1
    //   188: invokevirtual 2233	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   191: invokevirtual 266	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: invokevirtual 271	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokestatic 2236	com/outfit7/funnetworks/util/Logger:warning	(Ljava/lang/String;Ljava/lang/String;)V
    //   200: aload_0
    //   201: ifnull +7 -> 208
    //   204: aload_0
    //   205: invokevirtual 2237	java/util/zip/ZipOutputStream:close	()V
    //   208: iconst_0
    //   209: ireturn
    //   210: aload_1
    //   211: invokevirtual 2240	java/util/zip/ZipOutputStream:closeEntry	()V
    //   214: aload 6
    //   216: invokevirtual 643	java/io/InputStream:close	()V
    //   219: iload_2
    //   220: iconst_1
    //   221: iadd
    //   222: istore_2
    //   223: goto -179 -> 44
    //   226: aload_1
    //   227: invokevirtual 2241	java/util/zip/ZipOutputStream:flush	()V
    //   230: aload_1
    //   231: ifnull +7 -> 238
    //   234: aload_1
    //   235: invokevirtual 2237	java/util/zip/ZipOutputStream:close	()V
    //   238: iconst_1
    //   239: ireturn
    //   240: astore_0
    //   241: aload_0
    //   242: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   245: goto -7 -> 238
    //   248: astore_0
    //   249: aload_0
    //   250: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   253: iconst_0
    //   254: ireturn
    //   255: astore_0
    //   256: aload 5
    //   258: ifnull +8 -> 266
    //   261: aload 5
    //   263: invokevirtual 2237	java/util/zip/ZipOutputStream:close	()V
    //   266: aload_0
    //   267: athrow
    //   268: astore_1
    //   269: aload_1
    //   270: invokevirtual 1576	java/io/IOException:printStackTrace	()V
    //   273: goto -7 -> 266
    //   276: astore_0
    //   277: aload_1
    //   278: astore 5
    //   280: goto -24 -> 256
    //   283: astore_1
    //   284: aload 6
    //   286: astore_0
    //   287: goto -126 -> 161
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	290	0	paramArrayOfString	String[]
    //   0	290	1	paramString	String
    //   43	180	2	i	int
    //   41	6	3	j	int
    //   135	12	4	k	int
    //   1	109	5	str	String
    //   154	5	5	localException	Exception
    //   162	117	5	localObject	Object
    //   4	281	6	localBufferedInputStream	java.io.BufferedInputStream
    //   11	133	7	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   39	42	154	java/lang/Exception
    //   54	128	154	java/lang/Exception
    //   128	137	154	java/lang/Exception
    //   142	151	154	java/lang/Exception
    //   210	219	154	java/lang/Exception
    //   226	230	154	java/lang/Exception
    //   234	238	240	java/io/IOException
    //   204	208	248	java/io/IOException
    //   13	39	255	finally
    //   164	168	255	finally
    //   171	200	255	finally
    //   261	266	268	java/io/IOException
    //   39	42	276	finally
    //   54	128	276	finally
    //   128	137	276	finally
    //   142	151	276	finally
    //   210	219	276	finally
    //   226	230	276	finally
    //   13	39	283	java/lang/Exception
  }
}
