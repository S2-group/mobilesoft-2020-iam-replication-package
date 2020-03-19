package com.epi.data.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Base64;
import android.util.Log;
import com.epi.network.NetworkApi;
import com.facebook.Settings;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils
{
  public static char[] REPLACEMENTS = { 97, 97, 97, 97, 101, 101, 101, 105, 105, 111, 111, 111, 111, 117, 117, 121, 97, 100, 105, 117, 111, 117, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 101, 101, 101, 101, 101, 101, 101, 101, 105, 105, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 111, 79, 111, 117, 117, 117, 117, 117, 117, 117 };
  public static char[] SPECIAL_CHARACTERS = { 224, 225, 226, 227, 232, 233, 234, 236, 237, 242, 243, 244, 245, 249, 250, 253, 259, 273, 297, 361, 417, 432, 7841, 7843, 7845, 7847, 7849, 7851, 7853, 7855, 7857, 7859, 7861, 7863, 7865, 7867, 7869, 7871, 7873, 7875, 7877, 7879, 7881, 7883, 7885, 7887, 7889, 7891, 7893, 7895, 7897, 7898, 7899, 7901, 7903, 7905, 7907, 7909, 7911, 7913, 7915, 7917, 7919, 7921 };
  private static AsyncHttpClient client = new AsyncHttpClient();
  public static String urlServer = "http://comment.baomoi.com";
  public int ADS_INDEX_MAX = 20;
  public int ADS_INDEX_MIN = 3;
  public int ANPHA_IMAGEVIEW_THEME_DARK = 179;
  public Bitmap.Config BitmapConfigImageLoader = Bitmap.Config.RGB_565;
  public int DATA_DELETE_CACHE_DISC = 1;
  public int[] DEFAUSE_ID_ZONESOURCE = { 119, 58, 52, 55, 45, 54, 121, 53, 82, 59, 145, 147, 84, 71, 79, 72, 132, 137 };
  public boolean[] DEFAUSE_ISADD_ZONESOURCE = new boolean[this.DEFAUSE_ID_ZONESOURCE.length];
  public String[] DEFAUSE_KEY_ZONESOURCE = { "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone", "zone" };
  public String[] DEFAUSE_NAME_ZONESOURCE = { "Thế giới", "Pháp luật", "Giải trí", "Thể thao", "Kinh tế", "Văn hóa", "Xã hội", "Công nghệ", "Sức khỏe", "Giáo dục", "Ôtô-Xe máy", "Nhà đất", "Ẩm thực", "Âm nhạc", "Khoa học", "Thời trang", "Phim", "Du lịch" };
  public int DensityDpi;
  public String FORDER_IMAGE_CACHE = "BaoMoi2013/Cache";
  public int FULL_SCREEN_NO = 16973839;
  public int FULL_SCREEN_YES = 16973841;
  public int HEIGHT_IMAGE_CELL_INDEXVIEW_2_0 = 240;
  public int HEIGHT_IMAGE_CELL_INDEXVIEW_2_1 = this.WIDTH_IMAGE_CELL_INDEXVIEW_2_1 * 9 / 16;
  public int HEIGHT_IMAGE_ZONE_FULL_ZONEVIEW = this.WIDTH_IMAGE_ZONE_FULL_ZONEVIEW * 16 / 9;
  public int HEIGHT_IMAGE_ZONE_ZONE_1_ZONEVIEW = this.WIDTH_IMAGE_ZONE_FULL_ZONEVIEW * 9 / 16;
  public int IMAGE_QUALITY_HIEGHT = 80;
  public int IMAGE_QUALITY_SLOW = 50;
  public boolean IS_SDK_INT_HEIGHT = true;
  public String KEY_DATABASE = "ZONE_INFO_DATA_1_10_2013";
  public int KEY_HOTNEWS = 2;
  public String KEY_MD5_SETTING_ADS = "9f98a6757048736caf26674e95793fb2";
  public String KEY_VALUE_SETTING_ADS = "_ads_android";
  public String KEY_ZONESOURCE_DEFAULT = "";
  public String LINK_SERVICE = "http://dataprovider.touch.baomoi.com";
  public final int NOTIFY_OUTOFMEMORY = 0;
  public final int SL_HEIGHT = 1500;
  public int SWIPE_MIN_DISTANCE = 30;
  public int TOTAL_NEWS_HIEGHT = 100;
  public int TOTAL_NEWS_SLOW = 50;
  public String URL_PRIVACY = "http://m.baomoi.com/privacy.aspx";
  public int WIDTH_IMAGE_CELL_INDEXVIEW_2_0 = 240;
  public int WIDTH_IMAGE_CELL_INDEXVIEW_2_1 = 360;
  public int WIDTH_IMAGE_ZONE_FULL_ZONEVIEW = 360;
  public int WIDTH_IMAGE_ZONE_ZONE_1_ZONEVIEW = 360;
  public int WIDTH_MIN_IMAGE = 300;
  public boolean[] boolean_help_show = new boolean[this.id_help_linearlayout.length];
  public String dataLog = "";
  public int dataLogCount = 0;
  public int[] id_help_imageview = { 2131099782, 2131099785, 2131099787, 2131099789, 2131099791, 2131099793, 2131099796, 2131099798, 2131099800 };
  public int[] id_help_linearlayout = { 2131099781, 2131099784, 2131099786, 2131099788, 2131099790, 2131099792, 2131099794, 2131099797, 2131099799 };
  public final int[] id_img_zone_type_zonesource_home = { 2130837818, 2130837817, 2130837816 };
  public final int index_help_addsource = 7;
  public final int index_help_menu = 0;
  public final int index_help_myzone = 4;
  public final int index_help_setting = 3;
  public final int index_help_showImage = 5;
  public final int index_help_source = 1;
  public final int index_help_source_zone = 2;
  public final int index_help_topback = 8;
  public final int index_help_updatehome = 6;
  public final boolean isShowHelp = false;
  int maxLogCount = 20;
  public int sizeImgDisc = 50;
  
  public Utils() {}
  
  public static void getHashKey(Context paramContext)
  {
    int i = 0;
    int j;
    do
    {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo("com.example.commentfacebook", 64).signatures;
        j = paramContext.length;
      }
      catch (Exception paramContext)
      {
        Object localObject;
        MessageDigest localMessageDigest;
        return;
      }
      catch (NoSuchAlgorithmException paramContext)
      {
        return;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return;
      }
      localObject = paramContext[i];
      localMessageDigest = MessageDigest.getInstance("SHA");
      localMessageDigest.update(localObject.toByteArray());
      new String(Base64.encode(localMessageDigest.digest(), 0));
      i += 1;
    } while (i < j);
  }
  
  public static void getListComment(String paramString, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    Log.e("", "getListComment : " + paramString);
    paramString = urlServer + "/comment?postid=" + paramString;
    client.get(paramString, null, paramAsyncHttpResponseHandler);
  }
  
  public static void getTotalComment(String paramString, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    Log.e("", "getListComment : " + paramString);
    paramString = urlServer + "/cc?postid=" + paramString;
    client.get(paramString, null, paramAsyncHttpResponseHandler);
  }
  
  /* Error */
  private static void post(String paramString)
  {
    // Byte code:
    //   0: new 459	java/net/URL
    //   3: dup
    //   4: ldc_w 461
    //   7: invokespecial 462	java/net/URL:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: aload_0
    //   12: invokevirtual 465	java/lang/String:getBytes	()[B
    //   15: astore_3
    //   16: aconst_null
    //   17: astore_1
    //   18: aconst_null
    //   19: astore_0
    //   20: new 467	org/apache/http/client/methods/HttpGet
    //   23: dup
    //   24: ldc_w 461
    //   27: invokespecial 468	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   30: astore 5
    //   32: ldc_w 470
    //   35: invokevirtual 465	java/lang/String:getBytes	()[B
    //   38: iconst_2
    //   39: invokestatic 474	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   42: astore 4
    //   44: aload 5
    //   46: ldc_w 476
    //   49: new 422	java/lang/StringBuilder
    //   52: dup
    //   53: ldc_w 478
    //   56: invokespecial 426	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: aload 4
    //   61: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 434	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: invokeinterface 484 3 0
    //   72: aload_2
    //   73: invokevirtual 488	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   76: checkcast 490	java/net/HttpURLConnection
    //   79: astore_2
    //   80: aload_2
    //   81: astore_0
    //   82: aload_2
    //   83: astore_1
    //   84: aload_2
    //   85: iconst_1
    //   86: invokevirtual 494	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   89: aload_2
    //   90: astore_0
    //   91: aload_2
    //   92: astore_1
    //   93: aload_2
    //   94: iconst_0
    //   95: invokevirtual 497	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   98: aload_2
    //   99: astore_0
    //   100: aload_2
    //   101: astore_1
    //   102: aload_2
    //   103: aload_3
    //   104: arraylength
    //   105: invokevirtual 501	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   108: aload_2
    //   109: astore_0
    //   110: aload_2
    //   111: astore_1
    //   112: aload_2
    //   113: ldc_w 503
    //   116: invokevirtual 506	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   119: aload_2
    //   120: astore_0
    //   121: aload_2
    //   122: astore_1
    //   123: aload_2
    //   124: ldc_w 508
    //   127: ldc_w 510
    //   130: invokevirtual 513	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   133: aload_2
    //   134: astore_0
    //   135: aload_2
    //   136: astore_1
    //   137: aload_2
    //   138: ldc_w 476
    //   141: new 422	java/lang/StringBuilder
    //   144: dup
    //   145: ldc_w 478
    //   148: invokespecial 426	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   151: aload 4
    //   153: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 434	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokevirtual 513	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: aload_2
    //   163: astore_0
    //   164: aload_2
    //   165: astore_1
    //   166: aload_2
    //   167: invokevirtual 517	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   170: astore 4
    //   172: aload_2
    //   173: astore_0
    //   174: aload_2
    //   175: astore_1
    //   176: aload 4
    //   178: aload_3
    //   179: invokevirtual 522	java/io/OutputStream:write	([B)V
    //   182: aload_2
    //   183: astore_0
    //   184: aload_2
    //   185: astore_1
    //   186: aload 4
    //   188: invokevirtual 525	java/io/OutputStream:close	()V
    //   191: aload_2
    //   192: astore_0
    //   193: aload_2
    //   194: astore_1
    //   195: aload_2
    //   196: invokevirtual 529	java/net/HttpURLConnection:getResponseCode	()I
    //   199: pop
    //   200: aload_2
    //   201: ifnull +7 -> 208
    //   204: aload_2
    //   205: invokevirtual 532	java/net/HttpURLConnection:disconnect	()V
    //   208: return
    //   209: astore_0
    //   210: new 534	java/lang/IllegalArgumentException
    //   213: dup
    //   214: new 422	java/lang/StringBuilder
    //   217: dup
    //   218: ldc_w 536
    //   221: invokespecial 426	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   224: ldc_w 461
    //   227: invokevirtual 430	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 434	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: invokespecial 537	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   236: athrow
    //   237: astore_2
    //   238: aload_0
    //   239: astore_1
    //   240: aload_2
    //   241: invokevirtual 540	java/io/IOException:printStackTrace	()V
    //   244: aload_0
    //   245: ifnull -37 -> 208
    //   248: aload_0
    //   249: invokevirtual 532	java/net/HttpURLConnection:disconnect	()V
    //   252: return
    //   253: astore_0
    //   254: aload_1
    //   255: ifnull +7 -> 262
    //   258: aload_1
    //   259: invokevirtual 532	java/net/HttpURLConnection:disconnect	()V
    //   262: aload_0
    //   263: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	264	0	paramString	String
    //   17	242	1	localObject1	Object
    //   10	195	2	localObject2	Object
    //   237	4	2	localIOException	IOException
    //   15	164	3	arrayOfByte	byte[]
    //   42	145	4	localObject3	Object
    //   30	15	5	localHttpGet	org.apache.http.client.methods.HttpGet
    // Exception table:
    //   from	to	target	type
    //   0	11	209	java/net/MalformedURLException
    //   72	80	237	java/io/IOException
    //   84	89	237	java/io/IOException
    //   93	98	237	java/io/IOException
    //   102	108	237	java/io/IOException
    //   112	119	237	java/io/IOException
    //   123	133	237	java/io/IOException
    //   137	162	237	java/io/IOException
    //   166	172	237	java/io/IOException
    //   176	182	237	java/io/IOException
    //   186	191	237	java/io/IOException
    //   195	200	237	java/io/IOException
    //   72	80	253	finally
    //   84	89	253	finally
    //   93	98	253	finally
    //   102	108	253	finally
    //   112	119	253	finally
    //   123	133	253	finally
    //   137	162	253	finally
    //   166	172	253	finally
    //   176	182	253	finally
    //   186	191	253	finally
    //   195	200	253	finally
    //   240	244	253	finally
  }
  
  public static void postMessage(String paramString1, String paramString2, String paramString3, String paramString4, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    RequestParams localRequestParams = new RequestParams();
    localRequestParams.put("uid", paramString2);
    localRequestParams.put("message", paramString3);
    localRequestParams.put("postid", paramString4);
    Log.e("", "params = " + paramString1 + "?" + localRequestParams.toString());
    client.post(paramString1, localRequestParams, paramAsyncHttpResponseHandler);
  }
  
  public static void postUserInfo(String paramString1, String paramString2, String paramString3, String paramString4, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    RequestParams localRequestParams = new RequestParams();
    localRequestParams.put("uid", paramString2);
    localRequestParams.put("name", paramString3);
    localRequestParams.put("birthday", paramString4);
    client.post(paramString1, localRequestParams, paramAsyncHttpResponseHandler);
  }
  
  /* Error */
  public static String readFileAsString(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 572	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   4: aload_0
    //   5: invokevirtual 578	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   8: astore_0
    //   9: new 580	java/io/StringWriter
    //   12: dup
    //   13: invokespecial 581	java/io/StringWriter:<init>	()V
    //   16: astore_1
    //   17: sipush 2048
    //   20: newarray char
    //   22: astore_3
    //   23: new 583	java/io/BufferedReader
    //   26: dup
    //   27: new 585	java/io/InputStreamReader
    //   30: dup
    //   31: aload_0
    //   32: ldc_w 587
    //   35: invokespecial 590	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   38: invokespecial 593	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   41: astore 4
    //   43: aload 4
    //   45: aload_3
    //   46: invokevirtual 599	java/io/Reader:read	([C)I
    //   49: istore_2
    //   50: iload_2
    //   51: iconst_m1
    //   52: if_icmpne +14 -> 66
    //   55: aload_0
    //   56: invokevirtual 602	java/io/InputStream:close	()V
    //   59: aload_1
    //   60: invokevirtual 603	java/lang/Object:toString	()Ljava/lang/String;
    //   63: astore_0
    //   64: aload_0
    //   65: areturn
    //   66: aload_1
    //   67: aload_3
    //   68: iconst_0
    //   69: iload_2
    //   70: invokevirtual 608	java/io/Writer:write	([CII)V
    //   73: goto -30 -> 43
    //   76: astore_1
    //   77: aload_0
    //   78: invokevirtual 602	java/io/InputStream:close	()V
    //   81: aload_1
    //   82: athrow
    //   83: astore_0
    //   84: aload_0
    //   85: invokevirtual 540	java/io/IOException:printStackTrace	()V
    //   88: aconst_null
    //   89: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	paramString	String
    //   0	90	1	paramContext	Context
    //   49	21	2	i	int
    //   22	46	3	arrayOfChar	char[]
    //   41	3	4	localBufferedReader	java.io.BufferedReader
    // Exception table:
    //   from	to	target	type
    //   23	43	76	finally
    //   43	50	76	finally
    //   66	73	76	finally
    //   0	23	83	java/io/IOException
    //   55	64	83	java/io/IOException
    //   77	83	83	java/io/IOException
  }
  
  public static char removeAccent(char paramChar)
  {
    int i = Arrays.binarySearch(SPECIAL_CHARACTERS, paramChar);
    if (i >= 0) {
      paramChar = REPLACEMENTS[i];
    }
    return paramChar;
  }
  
  public void CopyStream(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    try
    {
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramInputStream.read(arrayOfByte, 0, 1024);
        if (i == -1) {
          return;
        }
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      return;
    }
    catch (Exception paramInputStream) {}
  }
  
  public void callFacebook(Context paramContext)
  {
    try
    {
      Settings.publishInstallAsync(paramContext, paramContext.getString(2131296302));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public Bitmap.Config getAnimationBitmapFormat()
  {
    if (NetworkApi.getInstance().data.scrHeight > 1500) {
      return Bitmap.Config.ARGB_8888;
    }
    return Bitmap.Config.RGB_565;
  }
  
  public Bitmap getBitmapFromAsset(String paramString, Context paramContext)
  {
    AssetManager localAssetManager = paramContext.getAssets();
    paramContext = null;
    try
    {
      paramString = localAssetManager.open(paramString);
      return BitmapFactory.decodeStream(paramString);
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = paramContext;
      }
    }
  }
  
  public long getFolderSize(File paramFile)
  {
    long l1 = 0L;
    long l2 = l1;
    int i;
    if (paramFile.listFiles() != null)
    {
      l2 = l1;
      if (paramFile.listFiles().length > 0)
      {
        paramFile = paramFile.listFiles();
        int j = paramFile.length;
        i = 0;
        if (i < j) {
          break label52;
        }
        l2 = l1;
      }
    }
    return l2 / 1024L;
    label52:
    File localFile = paramFile[i];
    if (localFile.isFile()) {}
    for (l1 += localFile.length();; l1 += getFolderSize(localFile))
    {
      i += 1;
      break;
    }
  }
  
  public ArrayList<String> getInstalledApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i >= paramContext.size()) {
      return localArrayList;
    }
    PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
    if ((0 == 0) && (localPackageInfo.versionName == null)) {}
    for (;;)
    {
      i += 1;
      break;
      localArrayList.add(localPackageInfo.packageName);
    }
  }
  
  public String getUsedMemorySize()
  {
    long l2 = 0L;
    long l3 = 0L;
    long l4 = -1L;
    long l1 = l2;
    try
    {
      Runtime localRuntime = Runtime.getRuntime();
      l1 = l2;
      l2 = localRuntime.freeMemory();
      l1 = l2;
      long l5 = localRuntime.totalMemory();
      l3 = l5;
      l4 = l3 - l2;
      l1 = l2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    return "Total : " + l3 / 1024L / 1024L + "M - Free : " + l1 / 1024L / 1024L + "M - userd : " + l4 / 1024L / 1024L + "M";
  }
  
  public boolean isInstalledApps(String paramString)
  {
    int i = 0;
    try
    {
      while (i < NetworkApi.getInstance().data.listAppInstalled.size())
      {
        int j = ((String)NetworkApi.getInstance().data.listAppInstalled.get(i)).trim().hashCode();
        int k = paramString.trim().hashCode();
        if (j == k) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void postLogSerVer(Context paramContext, String paramString)
  {
    this.dataLog += paramString;
    this.dataLogCount += 1;
    if (this.dataLogCount == this.maxLogCount)
    {
      new downloadFile().execute(new String[] { this.dataLog });
      this.dataLogCount = 0;
      this.dataLog = "";
    }
  }
  
  public String readFileAsString(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte[paramContext.available()];
      paramContext.read(paramString);
      paramContext.close();
      paramContext = new String(paramString);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public String removeAccent(String paramString)
  {
    paramString = new StringBuilder(paramString);
    int i = 0;
    for (;;)
    {
      if (i >= paramString.length()) {
        return paramString.toString();
      }
      paramString.setCharAt(i, removeAccent(paramString.charAt(i)));
      i += 1;
    }
  }
  
  public void vibrate(Context paramContext, int paramInt)
  {
    try
    {
      ((Vibrator)paramContext.getSystemService("vibrator")).vibrate(paramInt);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  class downloadFile
    extends AsyncTask<String, String, String>
  {
    downloadFile() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      Utils.post(paramVarArgs[0]);
      return null;
    }
    
    protected void onPostExecute(String paramString) {}
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
    
    protected void onProgressUpdate(String... paramVarArgs) {}
  }
}
