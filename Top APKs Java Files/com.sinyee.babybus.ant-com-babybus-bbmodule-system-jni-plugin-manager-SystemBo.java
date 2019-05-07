package com.babybus.bbmodule.system.jni.plugin.manager;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.babybus.bbmodule.plugin.base.Plugin;
import com.babybus.bbmodule.system.jni.CallNative;
import com.babybus.bbmodule.system.jni.PlatformSystem;
import com.babybus.bbmodule.utils.BBDateUtil;
import com.babybus.bbmodule.utils.BBResources;
import com.babybus.bbmodule.utils.ReflectUtil;
import com.babybus.bbmodule.utils.SDCardUtil;
import com.babybus.bbmodule.utils.common.BBApplication;
import com.babybus.bbmodule.utils.constant.BBUtilsConst;
import com.babybus.bbmodule.utils.frameworkutils.ReflectFrameworkConstUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemBo
  extends BaseBo
{
  public static String shutdownData = "0";
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage) {}
  };
  
  public SystemBo() {}
  
  public static boolean assetsFileExist(String paramString)
  {
    return true;
  }
  
  /* Error */
  public static void copyAssets(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 101	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 104	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 7
    //   10: aload 7
    //   12: invokevirtual 108	java/io/File:exists	()Z
    //   15: ifne +19 -> 34
    //   18: aload 7
    //   20: invokevirtual 111	java/io/File:mkdirs	()Z
    //   23: ifne +11 -> 34
    //   26: ldc 113
    //   28: ldc 115
    //   30: invokestatic 121	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: aconst_null
    //   35: astore_1
    //   36: aconst_null
    //   37: astore 4
    //   39: aconst_null
    //   40: astore 6
    //   42: aconst_null
    //   43: astore 5
    //   45: aload 6
    //   47: astore_3
    //   48: getstatic 125	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:activity	Landroid/support/v4/app/FragmentActivity;
    //   51: invokevirtual 131	android/support/v4/app/FragmentActivity:getResources	()Landroid/content/res/Resources;
    //   54: invokevirtual 137	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   57: aload_0
    //   58: invokevirtual 143	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   61: astore_0
    //   62: aload_0
    //   63: astore 4
    //   65: aload_0
    //   66: astore_1
    //   67: aload 6
    //   69: astore_3
    //   70: new 145	java/io/FileOutputStream
    //   73: dup
    //   74: new 101	java/io/File
    //   77: dup
    //   78: aload 7
    //   80: aload_2
    //   81: invokespecial 148	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   84: invokespecial 151	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   87: astore_2
    //   88: aload_0
    //   89: aload_2
    //   90: invokestatic 157	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   93: pop
    //   94: aload_0
    //   95: invokevirtual 162	java/io/InputStream:close	()V
    //   98: aload_2
    //   99: invokevirtual 165	java/io/OutputStream:close	()V
    //   102: aload_0
    //   103: ifnull +7 -> 110
    //   106: aload_0
    //   107: invokevirtual 162	java/io/InputStream:close	()V
    //   110: aload_2
    //   111: ifnull +102 -> 213
    //   114: aload_2
    //   115: invokevirtual 165	java/io/OutputStream:close	()V
    //   118: return
    //   119: astore_2
    //   120: aload 5
    //   122: astore_0
    //   123: aload 4
    //   125: astore_1
    //   126: aload_0
    //   127: astore_3
    //   128: aload_2
    //   129: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   132: aload 4
    //   134: ifnull +8 -> 142
    //   137: aload 4
    //   139: invokevirtual 162	java/io/InputStream:close	()V
    //   142: aload_0
    //   143: ifnull -25 -> 118
    //   146: aload_0
    //   147: invokevirtual 165	java/io/OutputStream:close	()V
    //   150: return
    //   151: astore_0
    //   152: aload_0
    //   153: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   156: return
    //   157: astore_1
    //   158: aload_1
    //   159: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   162: goto -20 -> 142
    //   165: astore_0
    //   166: aload_1
    //   167: ifnull +7 -> 174
    //   170: aload_1
    //   171: invokevirtual 162	java/io/InputStream:close	()V
    //   174: aload_3
    //   175: ifnull +7 -> 182
    //   178: aload_3
    //   179: invokevirtual 165	java/io/OutputStream:close	()V
    //   182: aload_0
    //   183: athrow
    //   184: astore_1
    //   185: aload_1
    //   186: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   189: goto -15 -> 174
    //   192: astore_1
    //   193: aload_1
    //   194: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   197: goto -15 -> 182
    //   200: astore_0
    //   201: aload_0
    //   202: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   205: goto -95 -> 110
    //   208: astore_0
    //   209: aload_0
    //   210: invokevirtual 168	java/io/IOException:printStackTrace	()V
    //   213: return
    //   214: astore 4
    //   216: aload_0
    //   217: astore_1
    //   218: aload_2
    //   219: astore_3
    //   220: aload 4
    //   222: astore_0
    //   223: goto -57 -> 166
    //   226: astore_3
    //   227: aload_2
    //   228: astore_1
    //   229: aload_3
    //   230: astore_2
    //   231: aload_0
    //   232: astore 4
    //   234: aload_1
    //   235: astore_0
    //   236: goto -113 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	239	0	paramString1	String
    //   0	239	1	paramString2	String
    //   0	239	2	paramString3	String
    //   47	173	3	localObject1	Object
    //   226	4	3	localIOException	IOException
    //   37	101	4	str1	String
    //   214	7	4	localObject2	Object
    //   232	1	4	str2	String
    //   43	78	5	localObject3	Object
    //   40	28	6	localObject4	Object
    //   8	71	7	localFile	File
    // Exception table:
    //   from	to	target	type
    //   48	62	119	java/io/IOException
    //   70	88	119	java/io/IOException
    //   146	150	151	java/io/IOException
    //   137	142	157	java/io/IOException
    //   48	62	165	finally
    //   70	88	165	finally
    //   128	132	165	finally
    //   170	174	184	java/io/IOException
    //   178	182	192	java/io/IOException
    //   106	110	200	java/io/IOException
    //   114	118	208	java/io/IOException
    //   88	102	214	finally
    //   88	102	226	java/io/IOException
  }
  
  public static boolean existsAssets(String paramString)
  {
    Object localObject1 = paramString.split("/");
    localObject1 = localObject1[(localObject1.length - 1)];
    paramString = StringUtils.remove(paramString, "/" + (String)localObject1);
    boolean bool1 = false;
    if (dirMap != null) {
      bool1 = dirMap.containsKey(paramString);
    }
    Object localObject2;
    if (!bool1) {
      localObject2 = activity.getAssets();
    }
    for (;;)
    {
      try
      {
        localObject2 = ((AssetManager)localObject2).list(paramString);
        i = 0;
        if (i < localObject2.length) {
          continue;
        }
        dirMap.put(paramString, paramString);
      }
      catch (IOException paramString)
      {
        int i;
        boolean bool2;
        Object localObject3;
        paramString.printStackTrace();
        continue;
      }
      bool2 = false;
      bool1 = bool2;
      if (boxPicsMap != null)
      {
        bool1 = bool2;
        if (boxPicsMap.size() > 0) {
          bool1 = boxPicsMap.containsKey(localObject1);
        }
      }
      return bool1;
      localObject3 = localObject2[i];
      boxPicsMap.put(localObject3, localObject3);
      i += 1;
    }
  }
  
  private static void exitGame()
  {
    CallNative.gameExit();
    BBApplication.getInstance().exit();
  }
  
  public static String filepathes(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[");
    Object localObject = activity.getAssets();
    try
    {
      localObject = ((AssetManager)localObject).list(paramString);
      i = 0;
      if (i < localObject.length) {
        break label48;
      }
      localStringBuffer.append("]");
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        int i;
        label48:
        paramString.printStackTrace();
      }
    }
    return localStringBuffer.toString();
    if (i < localObject.length - 1) {}
    for (paramString = "\"" + localObject[i] + "\",";; paramString = "\"" + localObject[i] + "\"")
    {
      localStringBuffer.append(paramString);
      i += 1;
      break;
    }
  }
  
  public static String getAppID()
  {
    return activity.getPackageName();
  }
  
  public static String getCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  @SuppressLint({"NewApi"})
  public static String getDeviceInfo()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getLocalMacAddress()).append("|").append(getVersionName()).append("|").append(Build.MODEL).append("|").append("android").append("|").append(Build.VERSION.RELEASE).append("|").append(Build.BRAND).append("|");
    localStringBuilder.append("PRODUCT ").append(Build.PRODUCT).append("|");
    localStringBuilder.append("BOARD ").append(Build.BOARD).append("|");
    localStringBuilder.append("BOOTLOADER ").append(Build.BOOTLOADER).append("|");
    localStringBuilder.append("BRAND ").append(Build.BRAND).append("|");
    localStringBuilder.append("CPU_ABI ").append(Build.CPU_ABI).append("|");
    localStringBuilder.append("CPU_ABI2 ").append(Build.CPU_ABI2).append("|");
    localStringBuilder.append("DEVICE ").append(Build.DEVICE).append("|");
    localStringBuilder.append("DISPLAY ").append(Build.DISPLAY).append("|");
    localStringBuilder.append("FINGERPRINT ").append(Build.FINGERPRINT).append("|");
    localStringBuilder.append("HARDWARE ").append(Build.HARDWARE).append("|");
    localStringBuilder.append("HOST ").append(Build.HOST).append("|");
    localStringBuilder.append("ID ").append(Build.ID).append("|");
    localStringBuilder.append("MANUFACTURER ").append(Build.MANUFACTURER).append("|");
    localStringBuilder.append("MODEL ").append(Build.MODEL).append("|");
    localStringBuilder.append("PRODUCT ").append(Build.PRODUCT).append("|");
    localStringBuilder.append("RADIO ").append(Build.RADIO).append("|");
    localStringBuilder.append("SERIAL ").append(Build.SERIAL).append("|");
    localStringBuilder.append("TAGS ").append(Build.TAGS).append("|");
    localStringBuilder.append("TIME ").append(Build.TIME).append("|");
    localStringBuilder.append("TYPE ").append(Build.TYPE).append("|");
    localStringBuilder.append("USER ").append(Build.USER).append("|");
    return localStringBuilder.toString();
  }
  
  public static String getDeviceInfoJson()
  {
    String str1 = getLocalMacAddress();
    String str2 = getVersionName();
    String str3 = Build.DEVICE;
    String str4 = Build.VERSION.RELEASE;
    Object localObject = Build.BRAND;
    String str5 = activity.getWindowManager().getDefaultDisplay().getWidth() + "," + activity.getWindowManager().getDefaultDisplay().getHeight();
    String str6 = getDeviceInfo();
    localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("macAddr", str1);
      ((JSONObject)localObject).put("model", "");
      ((JSONObject)localObject).put("version", str2);
      ((JSONObject)localObject).put("system", str3);
      ((JSONObject)localObject).put("systemVersion", str4);
      ((JSONObject)localObject).put("resolution", str5);
      ((JSONObject)localObject).put("all", str6);
      return ((JSONObject)localObject).toString();
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  public static final String getGUID()
  {
    return UUID.randomUUID().toString();
  }
  
  public static String getIP()
  {
    return "";
  }
  
  public static String getLanguage()
  {
    int j = 0;
    Object localObject2 = ReflectFrameworkConstUtil.getStaticPropertyS_S("LANGUAGE");
    String str = ReflectFrameworkConstUtil.getStaticPropertyS_S("COUNTRY");
    int i;
    Object localObject1;
    if (ReflectFrameworkConstUtil.getStaticPropertyB_S("PUB_LANGUAGE_ASSIGN"))
    {
      i = 0;
      localObject1 = localObject2;
      if (((String)localObject2).equals("zh"))
      {
        if (!"CN".equalsIgnoreCase(str)) {
          break label83;
        }
        localObject1 = "zh";
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        if (((String)localObject1).equals("zh"))
        {
          localObject1 = "zh";
          return localObject1;
          i = 1;
          break;
          label83:
          localObject1 = "zht";
          continue;
        }
        if (((String)localObject1).equals("zht")) {
          return "zht";
        }
        if (((String)localObject1).equals("ja")) {
          return "ja";
        }
        if (((String)localObject1).equals("de")) {
          return "de";
        }
        if (((String)localObject1).equals("fr")) {
          return "fr";
        }
        if (((String)localObject1).equals("ru")) {
          return "ru";
        }
        if (((String)localObject1).equals("ko")) {
          return "ko";
        }
        if (((String)localObject1).equals("ar")) {
          return "ar";
        }
        if (((String)localObject1).equals("pt")) {
          return "pt";
        }
        if (((String)localObject1).equals("es")) {
          return "es";
        }
        return "en";
      }
    }
    String[] arrayOfString = ReflectFrameworkConstUtil.getStaticPropertySs_S("PUB_LANGUAGES");
    str = null;
    localObject2 = str;
    int k;
    if (arrayOfString != null)
    {
      localObject2 = str;
      if (arrayOfString.length > 0)
      {
        k = arrayOfString.length;
        i = j;
      }
    }
    for (;;)
    {
      if (i >= k) {}
      for (localObject2 = str;; localObject2 = localObject1)
      {
        localObject1 = localObject2;
        if (localObject2 != null) {
          break;
        }
        return ReflectFrameworkConstUtil.getStaticPropertyS_S("PUB_LANGUAGE_DEFAULT");
        if (!arrayOfString[i].equalsIgnoreCase((String)localObject1)) {
          break label297;
        }
      }
      label297:
      i += 1;
    }
  }
  
  public static String getLocalMacAddress()
  {
    WifiInfo localWifiInfo = ((WifiManager)activity.getSystemService("wifi")).getConnectionInfo();
    if (localWifiInfo.getMacAddress() == null) {
      return "";
    }
    return localWifiInfo.getMacAddress();
  }
  
  public static String getMIMEType(File paramFile)
  {
    paramFile = paramFile.getName();
    paramFile = paramFile.substring(paramFile.lastIndexOf(".") + 1).toLowerCase();
    if ((paramFile.equals("m4a")) || (paramFile.equals("mp3")) || (paramFile.equals("mid")) || (paramFile.equals("xmf")) || (paramFile.equals("ogg")) || (paramFile.equals("wav"))) {
      paramFile = "audio";
    }
    for (;;)
    {
      return paramFile + "/*";
      if ((paramFile.equals("3gp")) || (paramFile.equals("mp4")))
      {
        paramFile = "video";
      }
      else if ((paramFile.equals("jpg")) || (paramFile.equals("gif")) || (paramFile.equals("png")) || (paramFile.equals("jpeg")) || (paramFile.equals("bmp")))
      {
        paramFile = "image";
      }
      else
      {
        if (paramFile.equals("apk")) {
          return "application/vnd.android.package-archive";
        }
        paramFile = "*";
      }
    }
  }
  
  public static String getPackageName()
  {
    return activity.getPackageName();
  }
  
  public static String getSDCardPath()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return Environment.getExternalStorageDirectory() + "/";
    }
    return null;
  }
  
  public static String getVersionName()
  {
    try
    {
      String str = activity.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    return BBApplication.getInstance().hasAppInstalled(activity, paramString);
  }
  
  public static boolean isCompletePng(String paramString)
  {
    str1 = "";
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      arrayOfByte = new byte[2];
      String str2 = "";
      paramString = str1;
      if (localFileInputStream.read(arrayOfByte) != -1)
      {
        i = 0;
        paramString = str2;
        if (i < arrayOfByte.length) {
          break label158;
        }
        localFileInputStream.close();
        switch (Integer.parseInt(paramString))
        {
        }
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        byte[] arrayOfByte;
        int i;
        label158:
        paramString.printStackTrace();
        paramString = str1;
        continue;
        continue;
        paramString = "exe";
        continue;
        paramString = "midi";
        continue;
        paramString = "rar";
        continue;
        paramString = "zip";
        continue;
        paramString = "jpg";
        continue;
        paramString = "gif";
        continue;
        paramString = "bmp";
      }
    }
    for (paramString = "unknown type: " + paramString;; paramString = "png")
    {
      return "png".equals(paramString);
      paramString = paramString + Integer.toString(arrayOfByte[i] & 0xFF);
      i += 1;
      break;
    }
  }
  
  public static boolean isMarketInstalledAmazon()
  {
    Object localObject = (Cocos2dxActivity)activity;
    localObject = Cocos2dxActivity.getContext().getPackageManager().getInstalledPackages(0);
    int i;
    if (localObject != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= ((List)localObject).size()) {
        return false;
      }
      if ("com.amazon.venezia".equals(((PackageInfo)((List)localObject).get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
  }
  
  public static void launchApp(String paramString)
  {
    launchApp(paramString, true);
  }
  
  public static void launchApp(String paramString, boolean paramBoolean)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(500L);
          BBApplication.getInstance().launchApp(SystemBo.activity, SystemBo.this, false);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  /* Error */
  public static void launchMarket(String paramString)
  {
    // Byte code:
    //   0: ldc_w 692
    //   3: getstatic 294	android/os/Build:BRAND	Ljava/lang/String;
    //   6: invokevirtual 479	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   9: ifeq +8 -> 17
    //   12: aload_0
    //   13: invokestatic 695	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:launchMarketLenovo	(Ljava/lang/String;)V
    //   16: return
    //   17: invokestatic 697	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:isMarketInstalledAmazon	()Z
    //   20: ifeq +45 -> 65
    //   23: new 699	android/content/Intent
    //   26: dup
    //   27: ldc_w 701
    //   30: new 179	java/lang/StringBuilder
    //   33: dup
    //   34: ldc_w 703
    //   37: invokespecial 180	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: aload_0
    //   41: invokevirtual 706	java/lang/String:trim	()Ljava/lang/String;
    //   44: invokevirtual 184	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokestatic 712	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   53: invokespecial 715	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   56: astore_1
    //   57: getstatic 125	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:activity	Landroid/support/v4/app/FragmentActivity;
    //   60: aload_1
    //   61: invokevirtual 719	android/support/v4/app/FragmentActivity:startActivity	(Landroid/content/Intent;)V
    //   64: return
    //   65: aload_0
    //   66: invokestatic 722	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:launchMarketDefault	(Ljava/lang/String;)V
    //   69: return
    //   70: astore_1
    //   71: aload_0
    //   72: invokestatic 722	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:launchMarketDefault	(Ljava/lang/String;)V
    //   75: return
    //   76: astore_1
    //   77: goto -6 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	paramString	String
    //   56	5	1	localIntent	Intent
    //   70	1	1	localException1	Exception
    //   76	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	16	70	java/lang/Exception
    //   17	57	70	java/lang/Exception
    //   65	69	70	java/lang/Exception
    //   57	64	76	java/lang/Exception
  }
  
  public static void launchMarketDefault(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString.trim()));
    activity.startActivity(paramString);
  }
  
  public static void launchMarketLenovo(String paramString)
  {
    Iterator localIterator = ((ActivityManager)activity.getSystemService("activity")).getRunningTasks(500).iterator();
    for (;;)
    {
      int i = 0;
      if (!localIterator.hasNext()) {}
      for (;;)
      {
        if (i == 0) {
          break label125;
        }
        paramString = new Intent("android.intent.action.VIEW", Uri.parse("leapp://ptn/appinfo.do?service=ptn&packagename=" + paramString.trim()));
        activity.startActivity(paramString);
        return;
        String str = ((ActivityManager.RunningTaskInfo)localIterator.next()).baseActivity.getPackageName();
        if ((!"com.lenovo.leos.appstore.pad".equals(str)) && (!"com.lenovo.leos.appstore".equals(str))) {
          break;
        }
        i = 1;
      }
    }
    label125:
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        String str1 = "Please open Lenovo Market.";
        String str2 = ReflectFrameworkConstUtil.getStaticPropertyS_S("LANGUAGE");
        if (str2.equals("zh")) {
          str1 = "请先打开乐商店。";
        }
        for (;;)
        {
          Toast.makeText(SystemBo.activity, str1, 1).show();
          return;
          if (str2.equals("ja")) {
            str1 = "レノボショップを開いてください。";
          }
        }
      }
    });
  }
  
  public static void onekeyDownloadAll(String paramString)
  {
    Object localObject1 = activity.getPackageName();
    try
    {
      if ("LENOVO".equalsIgnoreCase(Build.BRAND))
      {
        launchMarketLenovo((String)localObject1);
        return;
      }
      if (!isMarketInstalledAmazon()) {
        break label114;
      }
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + ((String)localObject1).trim()));
      localObject1 = localIntent;
      try
      {
        activity.startActivity(localIntent);
        return;
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      Intent localIntent;
      Object localObject2;
      for (;;) {}
    }
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/search?q=" + paramString));
    activity.startActivity(paramString);
    return;
    label114:
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=" + paramString));
    localObject2 = localIntent;
    activity.startActivity(localIntent);
  }
  
  public static void open(String paramString)
  {
    paramString = new File(paramString);
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    String str = getMIMEType(paramString);
    localIntent.setDataAndType(Uri.fromFile(paramString), str);
    activity.startActivity(localIntent);
  }
  
  public static void openTestActivity()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.test.PluginTest");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "openTestActivity", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[PluginTest]openTestActivity() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[openTestActivity] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  private static void pauseGame() {}
  
  private static void pausePlugins()
  {
    Set localSet;
    Object[] arrayOfObject;
    int i;
    if (!plugins.isEmpty())
    {
      localSet = plugins.keySet();
      arrayOfObject = localSet.toArray();
      localObject = "";
      i = 0;
    }
    for (;;)
    {
      if (i >= localSet.size()) {
        return;
      }
      try
      {
        String str = (String)arrayOfObject[i];
        localObject = str;
        Plugin localPlugin = (Plugin)plugins.get(str);
        localObject = str;
        if (localPlugin != null)
        {
          localObject = str;
          localPlugin.pauseLogic();
          localObject = str;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          System.err.println("[Static]Plugin pause fail! Plugin Name is [" + (String)localObject + "]!");
        }
      }
      i += 1;
    }
  }
  
  public static boolean removeDirectory(String paramString)
  {
    Object localObject = paramString;
    if (!paramString.endsWith(File.separator)) {
      localObject = paramString + File.separator;
    }
    paramString = new File((String)localObject);
    if ((!paramString.exists()) || (!paramString.isDirectory())) {}
    label147:
    for (;;)
    {
      return false;
      int j = 1;
      localObject = paramString.listFiles();
      int i = 0;
      if (i >= localObject.length) {}
      for (;;)
      {
        if ((j == 0) || (!paramString.delete())) {
          break label147;
        }
        return true;
        boolean bool;
        if (localObject[i].isFile())
        {
          bool = removeFile(localObject[i].getAbsolutePath());
          j = bool;
          if (bool) {
            j = bool;
          }
        }
        else
        {
          do
          {
            i += 1;
            break;
            bool = removeDirectory(localObject[i].getAbsolutePath());
            j = bool;
          } while (bool);
          j = bool;
        }
      }
    }
  }
  
  public static boolean removeFile(String paramString)
  {
    paramString = new File(paramString);
    if ((paramString.isFile()) && (paramString.exists()))
    {
      paramString.delete();
      return true;
    }
    return false;
  }
  
  private static void resumeGame() {}
  
  private static void resumePlugins()
  {
    Set localSet;
    Object[] arrayOfObject;
    int i;
    if (!plugins.isEmpty())
    {
      localSet = plugins.keySet();
      arrayOfObject = localSet.toArray();
      localObject = "";
      i = 0;
    }
    for (;;)
    {
      if (i >= localSet.size()) {
        return;
      }
      try
      {
        String str = (String)arrayOfObject[i];
        localObject = str;
        Plugin localPlugin = (Plugin)plugins.get(str);
        localObject = str;
        if (localPlugin != null)
        {
          localObject = str;
          localPlugin.resumeLogic();
          localObject = str;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          System.err.println("[Static]Plugin resume fail! Plugin Name is [" + (String)localObject + "]!");
        }
      }
      i += 1;
    }
  }
  
  @SuppressLint({"NewApi"})
  public static void showCustomDialogConfirm(String paramString1, final String paramString2, int paramInt1, int paramInt2, int paramInt3, final String paramString3, final String paramString4)
  {
    Button localButton;
    if (!BBUtilsConst.isShowingExitDialog)
    {
      pauseGame();
      BBUtilsConst.isShowingExitDialog = true;
      paramString1 = (LinearLayout)LayoutInflater.from(activity).inflate(BBResources.getIdentifier(activity, "bb_custom_dialog_exit", "layout"), null);
      localButton = (Button)paramString1.findViewById(BBResources.getIdentifier(activity, "bb_custom_dialog_exit_iamge_button", "id"));
    }
    try
    {
      localButton.setBackground(Drawable.createFromPath(SDCardUtil.getSDPATH() + SDCardUtil.BABYBUS_PATH + "dialogIamge.png"));
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          try
          {
            ReflectUtil.invokeMethod(ReflectUtil.newInstance("com.babybus.bbmodule.system.jni.CallNative"), "postNotification", new Object[] { "DIALOG_CONFIRM_CLICK" });
            return;
          }
          catch (Exception paramAnonymousView)
          {
            paramAnonymousView.printStackTrace();
          }
        }
      });
      activity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(SystemBo.activity).setView(SystemBo.this).setMessage(paramString2).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              SystemBo.access$0();
            }
          }).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              try
              {
                ReflectUtil.invokeMethod(ReflectUtil.newInstance("com.babybus.bbmodule.system.jni.CallNative"), "postNotification", new Object[] { "DIALOG_CONFIRM_CBK" });
                BBUtilsConst.isShowingExitDialog = false;
                SystemBo.access$0();
                return;
              }
              catch (Exception paramAnonymous2DialogInterface)
              {
                for (;;)
                {
                  paramAnonymous2DialogInterface.printStackTrace();
                }
              }
            }
          }).setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              SystemBo.access$0();
            }
          }).show();
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public static void showCustomDialogInvokeQQGroupDialog()
  {
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (!BBUtilsConst.isShowingInvokeDialog)
        {
          SystemBo.access$2();
          BBUtilsConst.isShowingInvokeDialog = true;
          Object localObject = (LinearLayout)LayoutInflater.from(SystemBo.activity).inflate(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_invoke", "layout"), null);
          final AlertDialog localAlertDialog = new AlertDialog.Builder(SystemBo.activity).create();
          localAlertDialog.show();
          localAlertDialog.setContentView((View)localObject);
          localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              BBUtilsConst.isShowingInvokeDialog = false;
              BBUtilsConst.isBackFromInvokeDialog = true;
              SystemBo.access$0();
            }
          });
          ((TextView)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_invoke_textview_title", "id"))).setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "bb_invoke_title", "string")));
          ((TextView)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_invoke_textview_content", "id"))).setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "bb_invoke_content_first_part", "string")) + "486036920" + "\n" + SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "bb_invoke_content_last_part", "string")));
          Button localButton = (Button)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_invoke_btn_invoke", "id"));
          localButton.setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "yes", "string")));
          localButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localAlertDialog.dismiss();
              BBUtilsConst.isShowingInvokeDialog = false;
              paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://jq.qq.com/?_wv=1027&k=2G28PNf"));
              SystemBo.activity.startActivity(paramAnonymous2View);
            }
          });
          localObject = (Button)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_invoke_btn_cancel", "id"));
          ((Button)localObject).setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "no", "string")));
          ((Button)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localAlertDialog.dismiss();
              BBUtilsConst.isShowingInvokeDialog = false;
              BBUtilsConst.isBackFromInvokeDialog = true;
              SystemBo.access$0();
            }
          });
        }
      }
    });
  }
  
  @SuppressLint({"NewApi"})
  public static void showCustomDialogQuitConfirm(String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final int paramInt)
  {
    shutdownData = "0";
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject1;
        final AlertDialog localAlertDialog;
        Object localObject2;
        if (!BBUtilsConst.isShowingExitDialog)
        {
          SystemBo.access$2();
          BBUtilsConst.isShowingExitDialog = true;
          localObject1 = (LinearLayout)LayoutInflater.from(SystemBo.activity).inflate(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_exit", "layout"), null);
          localAlertDialog = new AlertDialog.Builder(SystemBo.activity).create();
          localAlertDialog.show();
          localAlertDialog.setContentView((View)localObject1);
          localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              SystemBo.access$0();
            }
          });
          ((TextView)localAlertDialog.findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_exit_textview_title", "id"))).setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "bb_try_product", "string")));
          localObject1 = (Button)localAlertDialog.findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_exit_image_button", "id"));
          if ((SystemBo.this != null) && (!"".equals(SystemBo.this)))
          {
            if ((paramString2 == null) || ("".equals(paramString2))) {
              break label384;
            }
            localObject2 = BBDateUtil.getCurDate();
            PlatformSystem.sendEventUMeng("6e3276c197f5ec992db1f8b7c1686c29", paramString2 + "_" + (String)localObject2);
          }
          localObject2 = Build.VERSION.RELEASE;
          if (Build.VERSION.SDK_INT >= 16) {
            break label422;
          }
          ((Button)localObject1).setBackgroundDrawable(Drawable.createFromPath(SystemBo.this));
        }
        for (;;)
        {
          ((Button)localObject1).setWidth(BBApplication.dip2px(SystemBo.activity, 320.0F));
          ((Button)localObject1).setHeight(BBApplication.dip2px(SystemBo.activity, 90.0F));
          localObject2 = (Button)localAlertDialog.findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_exit_btn_exit", "id"));
          ((Button)localObject2).setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "bb_exit", "string")));
          Button localButton = (Button)localAlertDialog.findViewById(BBResources.getIdentifier(SystemBo.activity, "bb_custom_dialog_exit_btn_cancel", "id"));
          localButton.setText(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "bb_resume", "string")));
          ((Button)localObject1).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              SystemBo.shutdownData = "1";
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, System.currentTimeMillis(), 2);
              SystemBo.shutdownData = "0";
              if ((this.val$url != null) && (!"".equals(this.val$url)))
              {
                paramAnonymous2View = BBDateUtil.getCurDate();
                PlatformSystem.sendEventUMeng("e7ab289f66577f39dacaefa5190e703f", this.val$url + "_" + paramAnonymous2View);
              }
              for (;;)
              {
                WebViewBo.openLink(this.val$url, false, this.val$key, this.val$name, this.val$umkey, this.val$opentype);
                return;
                paramAnonymous2View = BBDateUtil.getCurDate();
                PlatformSystem.sendEventUMeng("e7ab289f66577f39dacaefa5190e703f", this.val$key + "_" + paramAnonymous2View);
              }
            }
          });
          ((Button)localObject2).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
              localAlertDialog.dismiss();
              BBUtilsConst.isShowingExitDialog = false;
              SystemBo.access$1();
            }
          });
          localButton.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
              localAlertDialog.dismiss();
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              SystemBo.access$0();
            }
          });
          return;
          label384:
          localObject2 = BBDateUtil.getCurDate();
          PlatformSystem.sendEventUMeng("6e3276c197f5ec992db1f8b7c1686c29", paramString3 + "_" + (String)localObject2);
          break;
          label422:
          ((Button)localObject1).setBackground(Drawable.createFromPath(SystemBo.this));
        }
      }
    });
  }
  
  public static void showDialogConfirm(String paramString1, String paramString2, int paramInt1, int paramInt2, final String paramString3, final String paramString4)
  {
    if (!BBUtilsConst.isShowingExitDialog)
    {
      CallNative.gamePause();
      BBUtilsConst.isShowingExitDialog = true;
      activity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(SystemBo.activity).setMessage(SystemBo.this).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              CallNative.gameResume();
            }
          }).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              CallNative.postNotification("DIALOG_CONFIRM_CBK");
              BBUtilsConst.isShowingExitDialog = false;
              CallNative.gameResume();
            }
          }).setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              CallNative.gameResume();
            }
          }).show();
        }
      });
    }
  }
  
  public static void showDialogQuitConfirm()
  {
    if (!BBUtilsConst.isShowingExitDialog)
    {
      pauseGame();
      BBUtilsConst.isShowingExitDialog = true;
      activity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(SystemBo.activity).setMessage(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "quitOrReturn", "string"))).setNegativeButton(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "no", "string")), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              SystemBo.access$0();
            }
          }).setPositiveButton(SystemBo.activity.getString(BBResources.getIdentifier(SystemBo.activity, "yes", "string")), new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              BBUtilsConst.isShowingExitDialog = false;
              SystemBo.access$1();
            }
          }).setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface)
            {
              BBUtilsConst.isShowingExitDialog = false;
              BBUtilsConst.isBackFromExitDialog = true;
              SystemBo.access$0();
            }
          }).show();
        }
      });
    }
  }
  
  public static void showDialogSimple(String paramString1, String paramString2, final String paramString3)
  {
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(SystemBo.activity).setMessage(SystemBo.this).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        }).show();
      }
    });
  }
  
  public static void showToast(String paramString)
  {
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(SystemBo.activity, SystemBo.this, 0).show();
      }
    });
  }
  
  public static void vibrate()
  {
    ((Vibrator)activity.getSystemService("vibrator")).vibrate(40L);
  }
}
