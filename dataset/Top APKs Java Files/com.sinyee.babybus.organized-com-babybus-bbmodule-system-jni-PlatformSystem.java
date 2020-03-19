package com.babybus.bbmodule.system.jni;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.Vibrator;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;
import com.babybus.bbmodule.plugin.base.Plugin;
import com.babybus.bbmodule.utils.ReflectUtil;
import com.babybus.bbmodule.utils.frameworkutils.ReflectFrameworkConstUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.cocos2dx.lib.Cocos2dxActivity;

public class PlatformSystem
{
  public static Activity activity;
  public static Map<String, Plugin> plugins = new HashMap();
  
  public PlatformSystem() {}
  
  public static void addAd(int paramInt)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.adview.PluginADView");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "addAd", new Object[] { Integer.valueOf(paramInt) });
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[PluginAdview]addAd() fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static boolean canRecord()
  {
    boolean bool = false;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        bool = ((Boolean)ReflectUtil.invokeMethod(localPlugin, "canRecord", new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]canRecord() reflect fail!");
        return false;
      }
      return false;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static boolean canSwitchCamera()
  {
    return false;
  }
  
  public static void closeCamera() {}
  
  /* Error */
  public static void copyAssets(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 116	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 118	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 8
    //   10: aload 8
    //   12: invokevirtual 121	java/io/File:exists	()Z
    //   15: ifne +19 -> 34
    //   18: aload 8
    //   20: invokevirtual 124	java/io/File:mkdirs	()Z
    //   23: ifne +11 -> 34
    //   26: ldc 126
    //   28: ldc -128
    //   30: invokestatic 134	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: aconst_null
    //   35: astore 5
    //   37: aconst_null
    //   38: astore_1
    //   39: aconst_null
    //   40: astore 6
    //   42: aconst_null
    //   43: astore 7
    //   45: aload 6
    //   47: astore 4
    //   49: getstatic 60	com/babybus/bbmodule/system/jni/PlatformSystem:activity	Landroid/app/Activity;
    //   52: invokevirtual 140	android/app/Activity:getResources	()Landroid/content/res/Resources;
    //   55: invokevirtual 146	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   58: aload_0
    //   59: invokevirtual 152	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   62: astore_0
    //   63: aload_0
    //   64: astore_1
    //   65: aload 6
    //   67: astore 4
    //   69: aload_0
    //   70: astore 5
    //   72: new 154	java/io/FileOutputStream
    //   75: dup
    //   76: new 116	java/io/File
    //   79: dup
    //   80: aload 8
    //   82: aload_2
    //   83: invokespecial 157	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   86: invokespecial 160	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   89: astore_2
    //   90: sipush 1024
    //   93: newarray byte
    //   95: astore_1
    //   96: aload_0
    //   97: aload_1
    //   98: invokevirtual 166	java/io/InputStream:read	([B)I
    //   101: istore_3
    //   102: iload_3
    //   103: ifle +42 -> 145
    //   106: aload_2
    //   107: aload_1
    //   108: iconst_0
    //   109: iload_3
    //   110: invokevirtual 172	java/io/OutputStream:write	([BII)V
    //   113: goto -17 -> 96
    //   116: astore 6
    //   118: aload_0
    //   119: astore_1
    //   120: aload_2
    //   121: astore 4
    //   123: aload 6
    //   125: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   128: aload_0
    //   129: ifnull +7 -> 136
    //   132: aload_0
    //   133: invokevirtual 176	java/io/InputStream:close	()V
    //   136: aload_2
    //   137: ifnull +7 -> 144
    //   140: aload_2
    //   141: invokevirtual 177	java/io/OutputStream:close	()V
    //   144: return
    //   145: aload_0
    //   146: ifnull +7 -> 153
    //   149: aload_0
    //   150: invokevirtual 176	java/io/InputStream:close	()V
    //   153: aload_2
    //   154: ifnull +97 -> 251
    //   157: aload_2
    //   158: invokevirtual 177	java/io/OutputStream:close	()V
    //   161: return
    //   162: astore_0
    //   163: aload_0
    //   164: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   167: goto -14 -> 153
    //   170: astore_0
    //   171: aload_0
    //   172: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   175: return
    //   176: astore_0
    //   177: aload_0
    //   178: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   181: goto -45 -> 136
    //   184: astore_0
    //   185: aload_0
    //   186: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   189: return
    //   190: astore_0
    //   191: aload_1
    //   192: ifnull +7 -> 199
    //   195: aload_1
    //   196: invokevirtual 176	java/io/InputStream:close	()V
    //   199: aload 4
    //   201: ifnull +8 -> 209
    //   204: aload 4
    //   206: invokevirtual 177	java/io/OutputStream:close	()V
    //   209: aload_0
    //   210: athrow
    //   211: astore_1
    //   212: aload_1
    //   213: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   216: goto -17 -> 199
    //   219: astore_1
    //   220: aload_1
    //   221: invokevirtual 173	java/io/IOException:printStackTrace	()V
    //   224: goto -15 -> 209
    //   227: astore 5
    //   229: aload_0
    //   230: astore_1
    //   231: aload_2
    //   232: astore 4
    //   234: aload 5
    //   236: astore_0
    //   237: goto -46 -> 191
    //   240: astore 6
    //   242: aload 5
    //   244: astore_0
    //   245: aload 7
    //   247: astore_2
    //   248: goto -130 -> 118
    //   251: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	paramString1	String
    //   0	252	1	paramString2	String
    //   0	252	2	paramString3	String
    //   101	9	3	i	int
    //   47	186	4	localObject1	Object
    //   35	36	5	str	String
    //   227	16	5	localObject2	Object
    //   40	26	6	localObject3	Object
    //   116	8	6	localIOException1	java.io.IOException
    //   240	1	6	localIOException2	java.io.IOException
    //   43	203	7	localObject4	Object
    //   8	73	8	localFile	File
    // Exception table:
    //   from	to	target	type
    //   90	96	116	java/io/IOException
    //   96	102	116	java/io/IOException
    //   106	113	116	java/io/IOException
    //   149	153	162	java/io/IOException
    //   157	161	170	java/io/IOException
    //   132	136	176	java/io/IOException
    //   140	144	184	java/io/IOException
    //   49	63	190	finally
    //   72	90	190	finally
    //   123	128	190	finally
    //   195	199	211	java/io/IOException
    //   204	209	219	java/io/IOException
    //   90	96	227	finally
    //   96	102	227	finally
    //   106	113	227	finally
    //   49	63	240	java/io/IOException
    //   72	90	240	java/io/IOException
  }
  
  public static void exit()
  {
    Process.killProcess(Process.myPid());
  }
  
  public static void feedback()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.umeng.PluginUmeng");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "openFeedBack", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        System.err.println("[Third Login]openFeedBack() fail!");
        localException1.printStackTrace();
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static FrameLayout.LayoutParams getADLayoutParams(int paramInt)
  {
    FrameLayout.LayoutParams localLayoutParams = null;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.adview.PluginADView");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        localLayoutParams = (FrameLayout.LayoutParams)ReflectUtil.invokeMethod(localPlugin, "getADLayoutParams", new Object[] { Integer.valueOf(paramInt) });
        return localLayoutParams;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[PluginAdview]getADLayoutParams() fail!");
        return null;
      }
      return null;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
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
    localStringBuilder.append("PRODUCT ").append(Build.PRODUCT).append("\n");
    localStringBuilder.append("BOARD ").append(Build.BOARD).append("\n");
    localStringBuilder.append("BOOTLOADER ").append(Build.BOOTLOADER).append("\n");
    localStringBuilder.append("BRAND ").append(Build.BRAND).append("\n");
    localStringBuilder.append("CPU_ABI ").append(Build.CPU_ABI).append("\n");
    localStringBuilder.append("CPU_ABI2 ").append(Build.CPU_ABI2).append("\n");
    localStringBuilder.append("DEVICE ").append(Build.DEVICE).append("\n");
    localStringBuilder.append("DISPLAY ").append(Build.DISPLAY).append("\n");
    localStringBuilder.append("FINGERPRINT ").append(Build.FINGERPRINT).append("\n");
    localStringBuilder.append("HARDWARE ").append(Build.HARDWARE).append("\n");
    localStringBuilder.append("HOST ").append(Build.HOST).append("\n");
    localStringBuilder.append("ID ").append(Build.ID).append("\n");
    localStringBuilder.append("MANUFACTURER ").append(Build.MANUFACTURER).append("\n");
    localStringBuilder.append("MODEL ").append(Build.MODEL).append("\n");
    localStringBuilder.append("PRODUCT ").append(Build.PRODUCT).append("\n");
    localStringBuilder.append("RADIO ").append(Build.RADIO).append("\n");
    localStringBuilder.append("SERIAL ").append(Build.SERIAL).append("\n");
    localStringBuilder.append("TAGS ").append(Build.TAGS).append("\n");
    localStringBuilder.append("TIME ").append(Build.TIME).append("\n");
    localStringBuilder.append("TYPE ").append(Build.TYPE).append("\n");
    localStringBuilder.append("USER ").append(Build.USER).append("\n");
    return localStringBuilder.toString();
  }
  
  public static String getEasyRecordDir()
  {
    String str2 = "";
    try
    {
      Object localObject = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      String str1 = str2;
      if (localObject != null) {}
      try
      {
        ((Plugin)localObject).setActivity(activity);
        localObject = ReflectUtil.invokeMethod(localObject, "getEasyRecordDir", new Object[0]);
        str1 = str2;
        if ((String)localObject != null) {
          str1 = (String)localObject;
        }
        return str1;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]getEasyRecordDir() reflect fail!");
        return "";
      }
      return "";
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static float getEasyRecordSoundDuration(String paramString)
  {
    l2 = -1L;
    for (;;)
    {
      try
      {
        localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
        l1 = l2;
        if (localPlugin == null) {}
      }
      catch (Exception paramString)
      {
        Plugin localPlugin;
        System.err.println("[Third Login] instance class fail!");
        paramString.printStackTrace();
        long l1 = l2;
        continue;
      }
      try
      {
        localPlugin.setActivity(activity);
        paramString = ReflectUtil.invokeMethod(localPlugin, "getEasyRecordSoundDuration", new Object[] { paramString });
        l1 = l2;
        if ((Long)paramString != null) {
          l1 = ((Long)paramString).longValue();
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        System.err.println("[Recorder]getEasyRecordSoundDuration() reflect fail!");
        l1 = l2;
      }
    }
    return (float)l1;
  }
  
  public static String getIP()
  {
    try
    {
      Object localObject = new URL("http://iframe.ip138.com/ic.asp");
      try
      {
        localObject = (HttpURLConnection)((URL)localObject).openConnection();
        if (((HttpURLConnection)localObject).getResponseCode() != 200) {
          break label150;
        }
        localObject = ((HttpURLConnection)localObject).getInputStream();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader((InputStream)localObject, "utf-8"));
        localStringBuilder = new StringBuilder();
        for (;;)
        {
          String str2 = localBufferedReader.readLine();
          if (str2 == null) {
            break;
          }
          localStringBuilder.append(str2 + "\n");
        }
        localException1.printStackTrace();
      }
      catch (Exception localException1) {}
    }
    catch (Exception localException2)
    {
      StringBuilder localStringBuilder;
      label150:
      for (;;) {}
    }
    System.err.println("[IP]getIP(): only because of site[http://iframe.ip138.com/ic.asp] access fail!");
    for (;;)
    {
      return "";
      localException1.close();
      int i = localStringBuilder.indexOf("[");
      String str1 = localStringBuilder.substring(i + 1, localStringBuilder.indexOf("]", i + 1));
      return str1;
    }
  }
  
  public static String getLanguage()
  {
    Object localObject2 = ReflectFrameworkConstUtil.getStaticPropertyS_S("LANGUAGE");
    String str = ReflectFrameworkConstUtil.getStaticPropertyS_S("COUNTRY");
    int i;
    Object localObject1;
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("PUB_LANGUAGE_ASSIGN"))
    {
      i = 1;
      localObject1 = localObject2;
      if (((String)localObject2).equals("zh"))
      {
        if (!"CN".equalsIgnoreCase(str)) {
          break label78;
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
          i = 0;
          break;
          label78:
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
        if (((String)localObject1).equals("it")) {
          return "it";
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
        return "en";
      }
    }
    String[] arrayOfString = ReflectFrameworkConstUtil.getStaticPropertySs_S("PUB_LANGUAGES");
    str = null;
    localObject2 = str;
    int j;
    if (arrayOfString != null)
    {
      localObject2 = str;
      if (arrayOfString.length > 0)
      {
        j = arrayOfString.length;
        i = 0;
      }
    }
    for (;;)
    {
      localObject2 = str;
      if (i < j)
      {
        if (arrayOfString[i].equalsIgnoreCase((String)localObject1)) {
          localObject2 = localObject1;
        }
      }
      else
      {
        localObject1 = localObject2;
        if (localObject2 != null) {
          break;
        }
        return ReflectFrameworkConstUtil.getStaticPropertyS_S("PUB_LANGUAGE_DEFAULT");
      }
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
  
  private static String getMIMEType(File paramFile)
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
    boolean bool = false;
    Object localObject = null;
    try
    {
      paramString = activity.getPackageManager().getPackageInfo(paramString, 0);
      if (paramString != null) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
  }
  
  public static boolean isBlowing()
  {
    boolean bool = false;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        bool = ((Boolean)ReflectUtil.invokeMethod(localPlugin, "isBlowing", new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]isBlowing() reflect fail!");
        return false;
      }
      return false;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static boolean isCompletePng(String paramString)
  {
    str1 = "";
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      byte[] arrayOfByte = new byte[2];
      String str2 = "";
      paramString = str1;
      if (localFileInputStream.read(arrayOfByte) == -1) {
        break label189;
      }
      int i = 0;
      paramString = str2;
      while (i < arrayOfByte.length)
      {
        paramString = paramString + Integer.toString(arrayOfByte[i] & 0xFF);
        i += 1;
      }
      localFileInputStream.close();
      switch (Integer.parseInt(paramString))
      {
      }
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        label189:
        paramString.printStackTrace();
        paramString = str1;
        continue;
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
    paramString = "unknown type: " + paramString;
    for (;;)
    {
      return "png".equals(paramString);
      paramString = "exe";
      continue;
      paramString = "png";
    }
  }
  
  public static boolean isMarketInstalledAmazon()
  {
    Object localObject = (Cocos2dxActivity)activity;
    localObject = Cocos2dxActivity.getContext().getPackageManager().getInstalledPackages(0);
    if (localObject != null)
    {
      int i = 0;
      while (i < ((List)localObject).size())
      {
        if ("com.amazon.venezia".equals(((PackageInfo)((List)localObject).get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean isMuteListen()
  {
    return true;
  }
  
  public static boolean isRecordOk()
  {
    boolean bool = false;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        bool = ((Boolean)ReflectUtil.invokeMethod(localPlugin, "isRecordOk", new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]isRecordOk() reflect fail!");
        return false;
      }
      return false;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static boolean isRecording()
  {
    boolean bool = false;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        bool = ((Boolean)ReflectUtil.invokeMethod(localPlugin, "isRecording", new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]isRecording() reflect fail!");
        return false;
      }
      return false;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void launchApp(String paramString)
  {
    launchApp(paramString, true);
  }
  
  public static void launchApp(String paramString, boolean paramBoolean)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).setPackage(paramString);
    localObject = (ResolveInfo)activity.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
    if (localObject != null)
    {
      paramString = ((ResolveInfo)localObject).activityInfo.packageName;
      localObject = ((ResolveInfo)localObject).activityInfo.name;
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.LAUNCHER");
      localIntent.setComponent(new ComponentName(paramString, (String)localObject));
      localIntent.putExtra("launchFlag", true);
      activity.startActivity(localIntent);
      if (paramBoolean) {
        activity.finish();
      }
    }
  }
  
  /* Error */
  public static void launchMarket(String paramString)
  {
    // Byte code:
    //   0: ldc_w 710
    //   3: getstatic 251	android/os/Build:BRAND	Ljava/lang/String;
    //   6: invokevirtual 439	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   9: ifeq +8 -> 17
    //   12: aload_0
    //   13: invokestatic 713	com/babybus/bbmodule/system/jni/PlatformSystem:launchMarketLenovo	(Ljava/lang/String;)V
    //   16: return
    //   17: invokestatic 715	com/babybus/bbmodule/system/jni/PlatformSystem:isMarketInstalledAmazon	()Z
    //   20: ifeq +48 -> 68
    //   23: new 644	android/content/Intent
    //   26: dup
    //   27: ldc_w 717
    //   30: new 221	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 222	java/lang/StringBuilder:<init>	()V
    //   37: ldc_w 719
    //   40: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: aload_0
    //   44: invokevirtual 722	java/lang/String:trim	()Ljava/lang/String;
    //   47: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 728	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   56: invokespecial 649	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   59: astore_1
    //   60: getstatic 60	com/babybus/bbmodule/system/jni/PlatformSystem:activity	Landroid/app/Activity;
    //   63: aload_1
    //   64: invokevirtual 704	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   67: return
    //   68: aload_0
    //   69: invokestatic 731	com/babybus/bbmodule/system/jni/PlatformSystem:launchMarketDefault	(Ljava/lang/String;)V
    //   72: return
    //   73: astore_1
    //   74: aload_0
    //   75: invokestatic 731	com/babybus/bbmodule/system/jni/PlatformSystem:launchMarketDefault	(Ljava/lang/String;)V
    //   78: return
    //   79: astore_1
    //   80: goto -6 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	paramString	String
    //   59	5	1	localIntent	Intent
    //   73	1	1	localException1	Exception
    //   79	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	16	73	java/lang/Exception
    //   17	60	73	java/lang/Exception
    //   68	72	73	java/lang/Exception
    //   60	67	79	java/lang/Exception
  }
  
  private static void launchMarketDefault(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString.trim()));
    activity.startActivity(paramString);
  }
  
  private static void launchMarketLenovo(String paramString)
  {
    Iterator localIterator = ((ActivityManager)activity.getSystemService("activity")).getRunningTasks(500).iterator();
    for (;;)
    {
      int i = 0;
      if (localIterator.hasNext())
      {
        String str = ((ActivityManager.RunningTaskInfo)localIterator.next()).baseActivity.getPackageName();
        if (("com.lenovo.leos.appstore.pad".equals(str)) || ("com.lenovo.leos.appstore".equals(str))) {
          i = 1;
        }
      }
      else
      {
        if (i == 0) {
          break;
        }
        paramString = new Intent("android.intent.action.VIEW", Uri.parse("leapp://ptn/appinfo.do?service=ptn&packagename=" + paramString.trim()));
        activity.startActivity(paramString);
        return;
      }
    }
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
          Toast.makeText(PlatformSystem.activity, str1, 1).show();
          return;
          if (str2.equals("ja")) {
            str1 = "レノボショップを開いてください。";
          }
        }
      }
    });
  }
  
  public static boolean manIsTalking()
  {
    boolean bool = false;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        bool = ((Boolean)ReflectUtil.invokeMethod(localPlugin, "manIsTalking", new Object[0])).booleanValue();
        return bool;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]manIsTalking() reflect fail!");
        return false;
      }
      return false;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  /* Error */
  public static void onekeyDownloadAll(String paramString)
  {
    // Byte code:
    //   0: getstatic 60	com/babybus/bbmodule/system/jni/PlatformSystem:activity	Landroid/app/Activity;
    //   3: invokevirtual 206	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   6: astore_1
    //   7: ldc_w 710
    //   10: getstatic 251	android/os/Build:BRAND	Ljava/lang/String;
    //   13: invokevirtual 439	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   16: ifeq +8 -> 24
    //   19: aload_1
    //   20: invokestatic 713	com/babybus/bbmodule/system/jni/PlatformSystem:launchMarketLenovo	(Ljava/lang/String;)V
    //   23: return
    //   24: invokestatic 715	com/babybus/bbmodule/system/jni/PlatformSystem:isMarketInstalledAmazon	()Z
    //   27: ifeq +50 -> 77
    //   30: new 644	android/content/Intent
    //   33: dup
    //   34: ldc_w 717
    //   37: new 221	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 222	java/lang/StringBuilder:<init>	()V
    //   44: ldc_w 719
    //   47: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: aload_1
    //   51: invokevirtual 722	java/lang/String:trim	()Ljava/lang/String;
    //   54: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokestatic 728	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   63: invokespecial 649	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   66: astore_2
    //   67: aload_2
    //   68: astore_1
    //   69: getstatic 60	com/babybus/bbmodule/system/jni/PlatformSystem:activity	Landroid/app/Activity;
    //   72: aload_2
    //   73: invokevirtual 704	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   76: return
    //   77: new 644	android/content/Intent
    //   80: dup
    //   81: ldc_w 717
    //   84: new 221	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 222	java/lang/StringBuilder:<init>	()V
    //   91: ldc_w 767
    //   94: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload_0
    //   98: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   104: invokestatic 728	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   107: invokespecial 649	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   110: astore_2
    //   111: aload_2
    //   112: astore_1
    //   113: getstatic 60	com/babybus/bbmodule/system/jni/PlatformSystem:activity	Landroid/app/Activity;
    //   116: aload_2
    //   117: invokevirtual 704	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   120: return
    //   121: astore_1
    //   122: new 644	android/content/Intent
    //   125: dup
    //   126: ldc_w 717
    //   129: new 221	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 222	java/lang/StringBuilder:<init>	()V
    //   136: ldc_w 769
    //   139: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_0
    //   143: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 338	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokestatic 728	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   152: invokespecial 649	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   155: astore_0
    //   156: getstatic 60	com/babybus/bbmodule/system/jni/PlatformSystem:activity	Landroid/app/Activity;
    //   159: aload_0
    //   160: invokevirtual 704	android/app/Activity:startActivity	(Landroid/content/Intent;)V
    //   163: return
    //   164: astore_1
    //   165: goto -43 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	paramString	String
    //   6	107	1	localObject	Object
    //   121	1	1	localException1	Exception
    //   164	1	1	localException2	Exception
    //   66	51	2	localIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   7	23	121	java/lang/Exception
    //   24	67	121	java/lang/Exception
    //   77	111	121	java/lang/Exception
    //   69	76	164	java/lang/Exception
    //   113	120	164	java/lang/Exception
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
  
  public static void openAlbum(int paramInt1, int paramInt2)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.album.PluginAlbum");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "openAlbum", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[PluginAlbum]openAlbum() fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void openCamera(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.camera.PluginCamera");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "openCamera", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        System.err.println("[Third Login]openCamera() fail!");
        return;
      }
      return;
    }
    catch (Exception paramString)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString.printStackTrace();
    }
  }
  
  public static void openLink(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    activity.startActivity(paramString);
  }
  
  public static void pauseSound(int paramInt)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sound.PluginSound");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "pauseSound", new Object[] { Integer.valueOf(paramInt) });
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Sound]pauseSound() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void photograph(int paramInt1, int paramInt2) {}
  
  public static int playRecordSound(String paramString)
  {
    int j = -1;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      int i = j;
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        paramString = ReflectUtil.invokeMethod(localPlugin, "playRecordSound", new Object[] { paramString });
        i = j;
        if ((Integer)paramString != null) {
          i = ((Integer)paramString).intValue();
        }
        return i;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        System.err.println("[Recorder]playRecordSound() reflect fail!");
        return -1;
      }
      return -1;
    }
    catch (Exception paramString)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString.printStackTrace();
    }
  }
  
  public static int playSound(String paramString, boolean paramBoolean)
  {
    int j = -1;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sound.PluginSound");
      int i = j;
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        paramString = ReflectUtil.invokeMethod(localPlugin, "playSound", new Object[] { paramString, Boolean.valueOf(paramBoolean) });
        i = j;
        if ((Integer)paramString != null) {
          i = ((Integer)paramString).intValue();
        }
        return i;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        System.err.println("[Sound]playSound() reflect fail!");
        return -1;
      }
      return -1;
    }
    catch (Exception paramString)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString.printStackTrace();
    }
  }
  
  public static void readyRecord() {}
  
  public static void removeAd()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.adview.PluginADView");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "removeAd", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[PluginAdview]removeAd() fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static boolean removeDirectory(String paramString)
  {
    String str = paramString;
    if (!paramString.endsWith(File.separator)) {
      str = paramString + File.separator;
    }
    paramString = new File(str);
    if ((!paramString.exists()) || (!paramString.isDirectory()))
    {
      System.out.println("删除目录失败" + str + "目录不存在！");
      return false;
    }
    int j = 1;
    File[] arrayOfFile = paramString.listFiles();
    int i = 0;
    for (;;)
    {
      boolean bool;
      if (i < arrayOfFile.length)
      {
        if (!arrayOfFile[i].isFile()) {
          break label155;
        }
        bool = removeFile(arrayOfFile[i].getAbsolutePath());
        j = bool;
        if (bool) {
          break label174;
        }
        j = bool;
      }
      label155:
      do
      {
        if (j != 0) {
          break;
        }
        System.out.println("删除目录失败");
        return false;
        bool = removeDirectory(arrayOfFile[i].getAbsolutePath());
        j = bool;
      } while (!bool);
      j = bool;
      label174:
      i += 1;
    }
    if (paramString.delete())
    {
      System.out.println("删除目录" + str + "成功！");
      return true;
    }
    System.out.println("删除目录" + str + "失败！");
    return false;
  }
  
  public static boolean removeFile(String paramString)
  {
    File localFile = new File(paramString);
    if ((localFile.isFile()) && (localFile.exists()))
    {
      localFile.delete();
      System.out.println("删除单个文件" + paramString + "成功！");
      return true;
    }
    System.out.println("删除单个文件" + paramString + "失败！");
    return false;
  }
  
  public static void sendEventTD(String paramString1, String paramString2)
  {
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("ENABLE_TALKINGDATA")) {}
    for (;;)
    {
      return;
      try
      {
        Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.talkingdata.PluginTalkingData");
        if (localPlugin == null) {
          continue;
        }
        try
        {
          localPlugin.setActivity(activity);
          ReflectUtil.invokeMethod(localPlugin, "sendEventTD", new Object[] { paramString1, paramString2 });
          return;
        }
        catch (Exception paramString1)
        {
          System.err.println("[Third Login]sendEventTD() fail!");
          paramString1.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramString1)
      {
        System.err.println("[Third Login] instance class fail!");
        paramString1.printStackTrace();
      }
    }
  }
  
  public static void sendEventUMeng(String paramString1, String paramString2)
  {
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("KEY_UMENG")) {}
    for (;;)
    {
      return;
      try
      {
        Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.umeng.PluginUmeng");
        if (localPlugin == null) {
          continue;
        }
        try
        {
          localPlugin.setActivity(activity);
          ReflectUtil.invokeMethod(localPlugin, "sendEventUMeng", new Object[] { paramString1, paramString2 });
          return;
        }
        catch (Exception paramString1)
        {
          System.err.println("[Third Login]sendEventUMeng() fail!");
          paramString1.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramString1)
      {
        System.err.println("[Third Login] instance class fail!");
        paramString1.printStackTrace();
      }
    }
  }
  
  public static void shareAll(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sharesdk.PluginShareSDK");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "shareAll", new Object[] { paramString1, paramString2, paramString3, paramString4, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
        return;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        System.err.println("[ShareSDK]shareAll() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception paramString1)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString1.printStackTrace();
    }
  }
  
  public static void shareOne(int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt2, int paramInt3)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sharesdk.PluginShareSDK");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "shareOne", new Object[] { Integer.valueOf(paramInt1), paramString1, paramString2, paramString3, paramString4, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
        return;
      }
      catch (Exception paramString1)
      {
        paramString1.printStackTrace();
        System.err.println("[ShareSDK]shareOne() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception paramString1)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString1.printStackTrace();
    }
  }
  
  public static void showDialogConfirm(String paramString1, String paramString2, final int paramInt1, final int paramInt2, final String paramString3, final String paramString4)
  {
    CallNative.gamePause();
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(PlatformSystem.activity).setMessage(this.val$contentString).setNegativeButton(paramString4, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            LuaCaller.callLuaFunction(Integer.valueOf(PlatformSystem.2.this.val$fnCancelInt));
            CallNative.gameResume();
          }
        }).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            LuaCaller.callLuaFunction(Integer.valueOf(PlatformSystem.2.this.val$fnOkInt));
            CallNative.gameResume();
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
    CallNative.gamePause();
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(PlatformSystem.activity).setMessage(PlatformSystem.activity.getString(R.string.quitOrReturn)).setNegativeButton(PlatformSystem.activity.getString(R.string.no), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setPositiveButton(PlatformSystem.activity.getString(R.string.yes), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            CallNative.gameExit();
            PlatformSystem.activity.finish();
            PlatformSystem.exit();
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
    activity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(PlatformSystem.activity).setMessage(this.val$contentString).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        }).show();
      }
    });
  }
  
  public static long soundDuration(String paramString)
  {
    long l2 = 0L;
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sound.PluginSound");
      long l1 = l2;
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        paramString = ReflectUtil.invokeMethod(localPlugin, "getSoundDuration", new Object[] { paramString });
        l1 = l2;
        if ((Long)paramString != null) {
          l1 = ((Long)paramString).longValue();
        }
        return l1;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        System.err.println("[Sound]getSoundDuration() reflect fail!");
        return 0L;
      }
      return 0L;
    }
    catch (Exception paramString)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString.printStackTrace();
    }
  }
  
  public static void startBlowing()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "startBlowing", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]startBlowing() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void startEasyRecord(int paramInt1, int paramInt2)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "startEasyRecord", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]startEasyRecord() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void startMuteListen() {}
  
  public static void startRecord(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "startRecord", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        System.err.println("[Recorder]startRecord() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception paramString)
    {
      System.err.println("[Third Login] instance class fail!");
      paramString.printStackTrace();
    }
  }
  
  public static void stopBlowing()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "stopBlowing", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]stopBlowing() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void stopEasyRecord()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "stopEasyRecord", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]stopEasyRecord() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void stopMuteListen() {}
  
  public static void stopRecord()
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.record.PluginRecord");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "stopRecord", new Object[0]);
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Recorder]stopRecord() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void stopSound(int paramInt)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sound.PluginSound");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "stopSound", new Object[] { Integer.valueOf(paramInt) });
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[Sound]stopSound() reflect fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void switchCamera() {}
  
  public static void thirdLogin(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.sharesdk.PluginShareSDK");
      if (localPlugin != null) {}
      try
      {
        localPlugin.setActivity(activity);
        ReflectUtil.invokeMethod(localPlugin, "thirdLogin", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        System.err.println("[PluginShareSDK] thirdLogin() fail!");
        return;
      }
      return;
    }
    catch (Exception localException2)
    {
      System.err.println("[Third Login] instance class fail!");
      localException2.printStackTrace();
    }
  }
  
  public static void trackBeginTD(String paramString)
  {
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("ENABLE_TALKINGDATA")) {}
    for (;;)
    {
      return;
      try
      {
        Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.talkingdata.PluginTalkingData");
        if (localPlugin == null) {
          continue;
        }
        try
        {
          localPlugin.setActivity(activity);
          ReflectUtil.invokeMethod(localPlugin, "trackBeginTD", new Object[] { paramString });
          return;
        }
        catch (Exception paramString)
        {
          System.err.println("[Third Login]trackBeginTD() fail!");
          paramString.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramString)
      {
        System.err.println("[Third Login] instance class fail!");
        paramString.printStackTrace();
      }
    }
  }
  
  public static void trackBeginUMeng(String paramString)
  {
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("ENABLE_UMENG")) {}
    for (;;)
    {
      return;
      try
      {
        Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.umeng.PluginUmeng");
        if (localPlugin == null) {
          continue;
        }
        try
        {
          localPlugin.setActivity(activity);
          ReflectUtil.invokeMethod(localPlugin, "trackBeginUMeng", new Object[] { paramString });
          return;
        }
        catch (Exception paramString)
        {
          System.err.println("[Third Login]trackBeginUMeng() fail!");
          paramString.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramString)
      {
        System.err.println("[Third Login] instance class fail!");
        paramString.printStackTrace();
      }
    }
  }
  
  public static void trackEndTD(String paramString)
  {
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("ENABLE_TALKINGDATA")) {}
    for (;;)
    {
      return;
      try
      {
        Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.talkingdata.PluginTalkingData");
        if (localPlugin == null) {
          continue;
        }
        try
        {
          localPlugin.setActivity(activity);
          ReflectUtil.invokeMethod(localPlugin, "trackEndTD", new Object[] { paramString });
          return;
        }
        catch (Exception paramString)
        {
          System.err.println("[Third Login]trackEndTD() fail!");
          paramString.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramString)
      {
        System.err.println("[Third Login] instance class fail!");
        paramString.printStackTrace();
      }
    }
  }
  
  public static void trackEndUMeng(String paramString)
  {
    if (!ReflectFrameworkConstUtil.getStaticPropertyB_S("KEY_UMENG")) {}
    for (;;)
    {
      return;
      try
      {
        Plugin localPlugin = (Plugin)plugins.get("com.babybus.bbmodule.plugin.umeng.PluginUmeng");
        if (localPlugin == null) {
          continue;
        }
        try
        {
          localPlugin.setActivity(activity);
          ReflectUtil.invokeMethod(localPlugin, "trackEndUMeng", new Object[] { paramString });
          return;
        }
        catch (Exception paramString)
        {
          System.err.println("[Third Login]trackEndUMeng() fail!");
          paramString.printStackTrace();
          return;
        }
        return;
      }
      catch (Exception paramString)
      {
        System.err.println("[Third Login] instance class fail!");
        paramString.printStackTrace();
      }
    }
  }
  
  public static void vibrate()
  {
    ((Vibrator)activity.getSystemService("vibrator")).vibrate(40L);
  }
}
