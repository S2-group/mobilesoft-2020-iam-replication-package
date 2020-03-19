package com.babybus.bbmodule.system.jni.plugin.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.babybus.app.App;
import com.babybus.bbmodule.system.jni.CallNative;
import com.babybus.bbmodule.system.jni.PlatformSystem;
import com.babybus.managers.BBPluginManager;
import com.babybus.plugins.Plugin;
import com.babybus.umeng.BBUmengAnalytics;
import com.babybus.utils.ApkUtil;
import com.babybus.utils.BBResources;
import com.babybus.utils.NetUtil;
import com.babybus.utils.ReflectUtil;
import com.babybus.utils.SDCardUtil;
import com.babybus.utils.UIUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class SystemBo
  extends BaseBo
{
  private static StringBuffer assetFilePathContent = new StringBuffer();
  public static String shutdownData = "0";
  
  public SystemBo() {}
  
  public static boolean assetsFileExist(String paramString)
  {
    return true;
  }
  
  /* Error */
  public static void copyAssets(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 98	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 101	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 7
    //   10: aload 7
    //   12: invokevirtual 105	java/io/File:exists	()Z
    //   15: ifne +9 -> 24
    //   18: aload 7
    //   20: invokevirtual 108	java/io/File:mkdirs	()Z
    //   23: pop
    //   24: aconst_null
    //   25: astore_1
    //   26: aconst_null
    //   27: astore 4
    //   29: aconst_null
    //   30: astore 6
    //   32: aconst_null
    //   33: astore 5
    //   35: aload 6
    //   37: astore_3
    //   38: invokestatic 114	com/babybus/app/App:get	()Lcom/babybus/app/App;
    //   41: getfield 118	com/babybus/app/App:mainActivity	Landroid/app/Activity;
    //   44: invokevirtual 124	android/app/Activity:getResources	()Landroid/content/res/Resources;
    //   47: invokevirtual 130	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   50: aload_0
    //   51: invokevirtual 136	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   54: astore_0
    //   55: aload_0
    //   56: astore 4
    //   58: aload_0
    //   59: astore_1
    //   60: aload 6
    //   62: astore_3
    //   63: new 138	java/io/FileOutputStream
    //   66: dup
    //   67: new 98	java/io/File
    //   70: dup
    //   71: aload 7
    //   73: aload_2
    //   74: invokespecial 141	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   77: invokespecial 144	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   80: astore_2
    //   81: aload_0
    //   82: aload_2
    //   83: invokestatic 150	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   86: pop
    //   87: aload_0
    //   88: invokevirtual 155	java/io/InputStream:close	()V
    //   91: aload_2
    //   92: invokevirtual 158	java/io/OutputStream:close	()V
    //   95: aload_0
    //   96: ifnull +7 -> 103
    //   99: aload_0
    //   100: invokevirtual 155	java/io/InputStream:close	()V
    //   103: aload_2
    //   104: ifnull +102 -> 206
    //   107: aload_2
    //   108: invokevirtual 158	java/io/OutputStream:close	()V
    //   111: return
    //   112: astore_2
    //   113: aload 5
    //   115: astore_0
    //   116: aload 4
    //   118: astore_1
    //   119: aload_0
    //   120: astore_3
    //   121: aload_2
    //   122: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   125: aload 4
    //   127: ifnull +8 -> 135
    //   130: aload 4
    //   132: invokevirtual 155	java/io/InputStream:close	()V
    //   135: aload_0
    //   136: ifnull -25 -> 111
    //   139: aload_0
    //   140: invokevirtual 158	java/io/OutputStream:close	()V
    //   143: return
    //   144: astore_0
    //   145: aload_0
    //   146: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   149: return
    //   150: astore_1
    //   151: aload_1
    //   152: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   155: goto -20 -> 135
    //   158: astore_0
    //   159: aload_1
    //   160: ifnull +7 -> 167
    //   163: aload_1
    //   164: invokevirtual 155	java/io/InputStream:close	()V
    //   167: aload_3
    //   168: ifnull +7 -> 175
    //   171: aload_3
    //   172: invokevirtual 158	java/io/OutputStream:close	()V
    //   175: aload_0
    //   176: athrow
    //   177: astore_1
    //   178: aload_1
    //   179: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   182: goto -15 -> 167
    //   185: astore_1
    //   186: aload_1
    //   187: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   190: goto -15 -> 175
    //   193: astore_0
    //   194: aload_0
    //   195: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   198: goto -95 -> 103
    //   201: astore_0
    //   202: aload_0
    //   203: invokevirtual 161	java/io/IOException:printStackTrace	()V
    //   206: return
    //   207: astore 4
    //   209: aload_0
    //   210: astore_1
    //   211: aload_2
    //   212: astore_3
    //   213: aload 4
    //   215: astore_0
    //   216: goto -57 -> 159
    //   219: astore_3
    //   220: aload_2
    //   221: astore_1
    //   222: aload_3
    //   223: astore_2
    //   224: aload_0
    //   225: astore 4
    //   227: aload_1
    //   228: astore_0
    //   229: goto -113 -> 116
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	232	0	paramString1	String
    //   0	232	1	paramString2	String
    //   0	232	2	paramString3	String
    //   37	176	3	localObject1	Object
    //   219	4	3	localIOException	IOException
    //   27	104	4	str1	String
    //   207	7	4	localObject2	Object
    //   225	1	4	str2	String
    //   33	81	5	localObject3	Object
    //   30	31	6	localObject4	Object
    //   8	64	7	localFile	File
    // Exception table:
    //   from	to	target	type
    //   38	55	112	java/io/IOException
    //   63	81	112	java/io/IOException
    //   139	143	144	java/io/IOException
    //   130	135	150	java/io/IOException
    //   38	55	158	finally
    //   63	81	158	finally
    //   121	125	158	finally
    //   163	167	177	java/io/IOException
    //   171	175	185	java/io/IOException
    //   99	103	193	java/io/IOException
    //   107	111	201	java/io/IOException
    //   81	95	207	finally
    //   81	95	219	java/io/IOException
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
      localObject2 = App.get().mainActivity.getAssets();
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
    App.get().exit();
  }
  
  public static String filepathes(String paramString, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[");
    AssetManager localAssetManager = App.get().mainActivity.getAssets();
    if (!paramBoolean1) {}
    for (;;)
    {
      int i;
      try
      {
        paramString = localAssetManager.list(paramString);
        i = 0;
        if (i < paramString.length) {
          continue;
        }
        localStringBuffer.append(assetFilePathContent);
        localStringBuffer.append("]");
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        continue;
      }
      return localStringBuffer.toString();
      if (i < paramString.length - 1)
      {
        assetFilePathContent.append("\"" + paramString[i] + "\",");
      }
      else
      {
        assetFilePathContent.append("\"" + paramString[i] + "\"");
        break label193;
        listAssets(localAssetManager, paramString, paramBoolean2, paramBoolean3);
        assetFilePathContent = assetFilePathContent.replace(assetFilePathContent.length() - 1, assetFilePathContent.length(), "");
        continue;
      }
      label193:
      i += 1;
    }
  }
  
  public static String getAppID()
  {
    return App.get().mainActivity.getPackageName();
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
    String str5 = App.get().mainActivity.getWindowManager().getDefaultDisplay().getWidth() + "," + App.get().mainActivity.getWindowManager().getDefaultDisplay().getHeight();
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
    return UIUtil.getLanguage();
  }
  
  public static String getLocalMacAddress()
  {
    WifiInfo localWifiInfo = ((WifiManager)App.get().mainActivity.getSystemService("wifi")).getConnectionInfo();
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
    return App.get().mainActivity.getPackageName();
  }
  
  public static String getSDCardPath()
  {
    return SDCardUtil.getSDPATH();
  }
  
  public static float getScreenSizeOfDevice()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    App.get().mainActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    int k = localDisplayMetrics.densityDpi;
    double d1 = i / k;
    double d2 = j / k;
    return (float)Math.sqrt(Math.pow(d1, 2.0D) + Math.pow(d2, 2.0D));
  }
  
  public static String getVersionName()
  {
    try
    {
      String str = App.get().mainActivity.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
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
    return ApkUtil.isAppInstalled(paramString);
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
          break label154;
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
        label154:
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
    Object localObject = (Cocos2dxActivity)App.get().mainActivity;
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
    ApkUtil.launchApp(paramString, false);
  }
  
  /* Error */
  public static void launchMarket(String paramString)
  {
    // Byte code:
    //   0: ldc_w 656
    //   3: getstatic 291	android/os/Build:BRAND	Ljava/lang/String;
    //   6: invokevirtual 659	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   9: ifeq +8 -> 17
    //   12: aload_0
    //   13: invokestatic 662	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:launchMarketLenovo	(Ljava/lang/String;)V
    //   16: return
    //   17: invokestatic 664	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:isMarketInstalledAmazon	()Z
    //   20: ifeq +48 -> 68
    //   23: new 666	android/content/Intent
    //   26: dup
    //   27: ldc_w 668
    //   30: new 172	java/lang/StringBuilder
    //   33: dup
    //   34: ldc_w 670
    //   37: invokespecial 173	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: aload_0
    //   41: invokevirtual 673	java/lang/String:trim	()Ljava/lang/String;
    //   44: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokestatic 679	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   53: invokespecial 682	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   56: astore_1
    //   57: invokestatic 114	com/babybus/app/App:get	()Lcom/babybus/app/App;
    //   60: getfield 118	com/babybus/app/App:mainActivity	Landroid/app/Activity;
    //   63: aload_1
    //   64: invokevirtual 686	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   67: return
    //   68: aload_0
    //   69: invokestatic 689	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:launchMarketDefault	(Ljava/lang/String;)V
    //   72: return
    //   73: astore_1
    //   74: aload_0
    //   75: invokestatic 689	com/babybus/bbmodule/system/jni/plugin/manager/SystemBo:launchMarketDefault	(Ljava/lang/String;)V
    //   78: return
    //   79: astore_1
    //   80: goto -6 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramString	String
    //   56	8	1	localIntent	Intent
    //   73	1	1	localException1	Exception
    //   79	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	16	73	java/lang/Exception
    //   17	57	73	java/lang/Exception
    //   68	72	73	java/lang/Exception
    //   57	67	79	java/lang/Exception
  }
  
  public static void launchMarketDefault(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString.trim()));
    App.get().mainActivity.startActivity(paramString);
  }
  
  public static void launchMarketLenovo(String paramString)
  {
    Iterator localIterator = ((ActivityManager)App.get().mainActivity.getSystemService("activity")).getRunningTasks(500).iterator();
    for (;;)
    {
      int i = 0;
      if (!localIterator.hasNext()) {}
      for (;;)
      {
        if (i == 0) {
          break label131;
        }
        paramString = new Intent("android.intent.action.VIEW", Uri.parse("leapp://ptn/appinfo.do?service=ptn&packagename=" + paramString.trim()));
        App.get().mainActivity.startActivity(paramString);
        return;
        String str = ((ActivityManager.RunningTaskInfo)localIterator.next()).baseActivity.getPackageName();
        if ((!"com.lenovo.leos.appstore.pad".equals(str)) && (!"com.lenovo.leos.appstore".equals(str))) {
          break;
        }
        i = 1;
      }
    }
    label131:
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        String str1 = "Please open Lenovo Market.";
        String str2 = UIUtil.getLanguage();
        if (str2.equals("zh")) {
          str1 = "请先打开乐商店。";
        }
        for (;;)
        {
          Toast.makeText(App.get().mainActivity, str1, 1).show();
          return;
          if (str2.equals("ja")) {
            str1 = "レノボショップを開いてください。";
          }
        }
      }
    });
  }
  
  private static void listAssets(AssetManager paramAssetManager, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    for (;;)
    {
      int i;
      try
      {
        String[] arrayOfString = paramAssetManager.list(paramString);
        i = 0;
        if (i >= arrayOfString.length) {
          return;
        }
        String str = "\"" + arrayOfString[i] + "\",";
        if (paramBoolean2) {
          str = "\"" + paramString + "/" + arrayOfString[i] + "\",";
        }
        if (!paramBoolean1)
        {
          if (str.contains(".")) {
            assetFilePathContent.append(str);
          }
          if (paramString != "")
          {
            str = paramString + "/" + arrayOfString[i];
            if (paramAssetManager.list(str).length <= 0) {
              break label194;
            }
            listAssets(paramAssetManager, str, paramBoolean1, paramBoolean2);
            break label194;
          }
        }
        else
        {
          assetFilePathContent.append(str);
          continue;
        }
        str = arrayOfString[i];
      }
      catch (IOException paramAssetManager)
      {
        paramAssetManager.printStackTrace();
        return;
      }
      continue;
      label194:
      i += 1;
    }
  }
  
  public static void onekeyDownloadAll(String paramString)
  {
    Object localObject1 = App.get().mainActivity.getPackageName();
    try
    {
      if ("LENOVO".equalsIgnoreCase(Build.BRAND))
      {
        launchMarketLenovo((String)localObject1);
        return;
      }
      if (!isMarketInstalledAmazon()) {
        break label123;
      }
      localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + ((String)localObject1).trim()));
      localObject1 = localIntent;
      try
      {
        App.get().mainActivity.startActivity(localIntent);
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
    App.get().mainActivity.startActivity(paramString);
    return;
    label123:
    localIntent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=" + paramString));
    localObject2 = localIntent;
    App.get().mainActivity.startActivity(localIntent);
  }
  
  public static void open(String paramString)
  {
    paramString = new File(paramString);
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    String str = getMIMEType(paramString);
    localIntent.setDataAndType(Uri.fromFile(paramString), str);
    App.get().mainActivity.startActivity(localIntent);
  }
  
  public static void openTestActivity()
  {
    try
    {
      Plugin localPlugin = BBPluginManager.get().getPlugin("com.babybus.bbmodule.plugin.test.PluginTest");
      if (localPlugin != null) {}
      try
      {
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
  
  @SuppressLint({"NewApi"})
  public static void showCustomDialogConfirm(String paramString1, final String paramString2, int paramInt1, int paramInt2, int paramInt3, final String paramString3, final String paramString4)
  {
    paramString1 = (LinearLayout)LayoutInflater.from(App.get().mainActivity).inflate(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit", "layout"), null);
    Button localButton = (Button)paramString1.findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit_iamge_button", "id"));
    try
    {
      localButton.setBackground(Drawable.createFromPath(SDCardUtil.getSDPATH() + SDCardUtil.BABYBUS_PATH + "dialogIamge.png"));
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CallNative.postNotification("DIALOG_CONFIRM_CLICK");
        }
      });
      App.get().mainActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(App.get().mainActivity).setView(SystemBo.this).setMessage(paramString2).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              CallNative.postNotification("DIALOG_CONFIRM_CBK");
            }
          }).setOnCancelListener(new DialogInterface.OnCancelListener()
          {
            public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
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
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = (LinearLayout)LayoutInflater.from(App.get().mainActivity).inflate(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_invoke", "layout"), null);
        final AlertDialog localAlertDialog = new AlertDialog.Builder(App.get().mainActivity).create();
        localAlertDialog.show();
        localAlertDialog.setContentView((View)localObject);
        localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        });
        ((TextView)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_invoke_textview_title", "id"))).setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "bb_invoke_title", "string")));
        ((TextView)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_invoke_textview_content", "id"))).setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "bb_invoke_content_first_part", "string")) + "486036920" + "\n" + App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "bb_invoke_content_last_part", "string")));
        Button localButton = (Button)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_invoke_btn_invoke", "id"));
        localButton.setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "yes", "string")));
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localAlertDialog.dismiss();
            paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://jq.qq.com/?_wv=1027&k=2G28PNf"));
            App.get().mainActivity.startActivity(paramAnonymous2View);
          }
        });
        localObject = (Button)((LinearLayout)localObject).findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_invoke_btn_cancel", "id"));
        ((Button)localObject).setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "no", "string")));
        ((Button)localObject).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localAlertDialog.dismiss();
          }
        });
      }
    });
  }
  
  @SuppressLint({"NewApi"})
  public static void showCustomDialogQuitConfirm(String paramString1, final String paramString2, final String paramString3, final String paramString4, final String paramString5, final int paramInt1, final int paramInt2)
  {
    shutdownData = "0";
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = (LinearLayout)LayoutInflater.from(App.get().mainActivity).inflate(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit", "layout"), null);
        final AlertDialog localAlertDialog = new AlertDialog.Builder(App.get().mainActivity).create();
        localAlertDialog.show();
        localAlertDialog.setContentView((View)localObject);
        localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface)
          {
            BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
          }
        });
        ((TextView)localAlertDialog.findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit_textview_title", "id"))).setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "bb_try_product", "string")));
        localObject = (Button)localAlertDialog.findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit_image_button", "id"));
        if ((SystemBo.this != null) && (!"".equals(SystemBo.this)))
        {
          if (NetUtil.get().isNetActive()) {
            BBUmengAnalytics.get().sendEvent(App.get().mainActivity, "6e3276c197f5ec992db1f8b7c1686c29", paramInt2);
          }
        }
        else
        {
          if (Build.VERSION.SDK_INT >= 16) {
            break label430;
          }
          ((Button)localObject).setBackgroundDrawable(Drawable.createFromPath(SystemBo.this));
        }
        for (;;)
        {
          ((Button)localObject).setWidth(UIUtil.dip2Px(320));
          ((Button)localObject).setHeight(UIUtil.dip2Px(90));
          Button localButton1 = (Button)localAlertDialog.findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit_btn_exit", "id"));
          localButton1.setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "bb_exit", "string")));
          Button localButton2 = (Button)localAlertDialog.findViewById(BBResources.getIdentifier(App.get().mainActivity, "bb_custom_dialog_exit_btn_cancel", "id"));
          localButton2.setText(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "bb_resume", "string")));
          ((Button)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              SystemBo.shutdownData = "1";
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, System.currentTimeMillis(), 2);
              SystemBo.shutdownData = "0";
              if (NetUtil.get().isNetActive()) {
                BBUmengAnalytics.get().sendEvent(App.get().mainActivity, "e7ab289f66577f39dacaefa5190e703f", this.val$adid);
              }
              for (;;)
              {
                WebViewBo.openLink(this.val$url, this.val$key, this.val$name, this.val$umkey, this.val$opentype);
                return;
                BBUmengAnalytics.get().sendEvent(App.get().mainActivity, "e7ab289f66577f39dacaefa5190e703f", this.val$adid + "(offline)");
              }
            }
          });
          localButton1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              if (BBPluginManager.get().checkPluginIsExist("com.babybus.bbmodule.plugin.babybusdata.PluginBabybusData")) {
                BBPluginManager.get().getPlugin("com.babybus.bbmodule.plugin.babybusdata.PluginBabybusData").onDestory();
              }
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
              localAlertDialog.dismiss();
              SystemBo.access$0();
            }
          });
          localButton2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
              localAlertDialog.dismiss();
            }
          });
          return;
          BBUmengAnalytics.get().sendEvent(App.get().mainActivity, "6e3276c197f5ec992db1f8b7c1686c29", paramInt2 + "(offline)");
          break;
          label430:
          ((Button)localObject).setBackground(Drawable.createFromPath(SystemBo.this));
        }
      }
    });
  }
  
  public static void showDialog4giveMePraise(String paramString1, final String paramString2, final String paramString3)
  {
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(SystemBo.this).setPositiveButton(paramString2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            PlatformSystem.giveMePraise(App.get().getPackageName());
          }
        }).setNeutralButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).show();
      }
    });
  }
  
  public static void showDialogConfirm(String paramString1, String paramString2, int paramInt1, int paramInt2, final String paramString3, final String paramString4)
  {
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(SystemBo.this).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            CallNative.postNotification("DIALOG_CONFIRM_CBK");
          }
        }).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        }).show();
      }
    });
  }
  
  public static void showDialogQuitConfirm()
  {
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "quitOrReturn", "string"))).setNegativeButton(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "no", "string")), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setPositiveButton(App.get().mainActivity.getString(BBResources.getIdentifier(App.get().mainActivity, "yes", "string")), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if (BBPluginManager.get().checkPluginIsExist("com.babybus.bbmodule.plugin.babybusdata.PluginBabybusData")) {
              BBPluginManager.get().getPlugin("com.babybus.bbmodule.plugin.babybusdata.PluginBabybusData").onDestory();
            }
            SystemBo.access$0();
          }
        }).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        }).show();
      }
    });
  }
  
  public static void showDialogSimple(String paramString1, String paramString2, final String paramString3)
  {
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(SystemBo.this).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
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
    App.get().mainActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(App.get().mainActivity, SystemBo.this, 0).show();
      }
    });
  }
  
  public static void vibrate()
  {
    ((Vibrator)App.get().mainActivity.getSystemService("vibrator")).vibrate(40L);
  }
}
