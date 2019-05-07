package com.babybus.bbmodule.system.jni.manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
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
import com.babybus.app.App;
import com.babybus.bbmodule.system.jni.CallNative;
import com.babybus.bbmodule.system.jni.PlatformSystem;
import com.babybus.managers.BBPluginManager;
import com.babybus.platformsystem.R.id;
import com.babybus.platformsystem.R.layout;
import com.babybus.plugins.BBPlugin;
import com.babybus.utils.NetUtil;
import com.babybus.utils.ReflectUtil;
import com.babybus.utils.SDCardUtil;
import com.babybus.utils.UIUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SystemBo
  extends BaseBo
{
  public static String shutdownData = "0";
  
  public SystemBo() {}
  
  /* Error */
  public static void copyAssets(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: new 82	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 85	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 7
    //   10: aload 7
    //   12: invokevirtual 89	java/io/File:exists	()Z
    //   15: ifne +11 -> 26
    //   18: aload 7
    //   20: invokevirtual 92	java/io/File:mkdirs	()Z
    //   23: ifne +3 -> 26
    //   26: aconst_null
    //   27: astore_1
    //   28: aconst_null
    //   29: astore 4
    //   31: aconst_null
    //   32: astore 6
    //   34: aconst_null
    //   35: astore 5
    //   37: aload 6
    //   39: astore_3
    //   40: invokestatic 98	com/babybus/utils/UIUtil:getResources	()Landroid/content/res/Resources;
    //   43: invokevirtual 104	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   46: aload_0
    //   47: invokevirtual 110	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   50: astore_0
    //   51: aload_0
    //   52: astore 4
    //   54: aload_0
    //   55: astore_1
    //   56: aload 6
    //   58: astore_3
    //   59: new 112	java/io/FileOutputStream
    //   62: dup
    //   63: new 82	java/io/File
    //   66: dup
    //   67: aload 7
    //   69: aload_2
    //   70: invokespecial 115	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   73: invokespecial 118	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   76: astore_2
    //   77: aload_0
    //   78: aload_2
    //   79: invokestatic 124	org/apache/commons/io/IOUtils:copy	(Ljava/io/InputStream;Ljava/io/OutputStream;)I
    //   82: pop
    //   83: aload_0
    //   84: invokevirtual 129	java/io/InputStream:close	()V
    //   87: aload_2
    //   88: invokevirtual 132	java/io/OutputStream:close	()V
    //   91: aload_0
    //   92: ifnull +7 -> 99
    //   95: aload_0
    //   96: invokevirtual 129	java/io/InputStream:close	()V
    //   99: aload_2
    //   100: ifnull +128 -> 228
    //   103: aload_2
    //   104: invokevirtual 132	java/io/OutputStream:close	()V
    //   107: return
    //   108: astore_0
    //   109: aload_0
    //   110: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   113: goto -14 -> 99
    //   116: astore_0
    //   117: aload_0
    //   118: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   121: return
    //   122: astore_2
    //   123: aload 5
    //   125: astore_0
    //   126: aload 4
    //   128: astore_1
    //   129: aload_0
    //   130: astore_3
    //   131: aload_2
    //   132: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   135: aload 4
    //   137: ifnull +8 -> 145
    //   140: aload 4
    //   142: invokevirtual 129	java/io/InputStream:close	()V
    //   145: aload_0
    //   146: ifnull -39 -> 107
    //   149: aload_0
    //   150: invokevirtual 132	java/io/OutputStream:close	()V
    //   153: return
    //   154: astore_0
    //   155: aload_0
    //   156: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   159: return
    //   160: astore_1
    //   161: aload_1
    //   162: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   165: goto -20 -> 145
    //   168: astore_0
    //   169: aload_1
    //   170: ifnull +7 -> 177
    //   173: aload_1
    //   174: invokevirtual 129	java/io/InputStream:close	()V
    //   177: aload_3
    //   178: ifnull +7 -> 185
    //   181: aload_3
    //   182: invokevirtual 132	java/io/OutputStream:close	()V
    //   185: aload_0
    //   186: athrow
    //   187: astore_1
    //   188: aload_1
    //   189: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   192: goto -15 -> 177
    //   195: astore_1
    //   196: aload_1
    //   197: invokevirtual 135	java/io/IOException:printStackTrace	()V
    //   200: goto -15 -> 185
    //   203: astore 4
    //   205: aload_0
    //   206: astore_1
    //   207: aload_2
    //   208: astore_3
    //   209: aload 4
    //   211: astore_0
    //   212: goto -43 -> 169
    //   215: astore_3
    //   216: aload_2
    //   217: astore_1
    //   218: aload_3
    //   219: astore_2
    //   220: aload_0
    //   221: astore 4
    //   223: aload_1
    //   224: astore_0
    //   225: goto -99 -> 126
    //   228: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	229	0	paramString1	String
    //   0	229	1	paramString2	String
    //   0	229	2	paramString3	String
    //   39	170	3	localObject1	Object
    //   215	4	3	localIOException	IOException
    //   29	112	4	str1	String
    //   203	7	4	localObject2	Object
    //   221	1	4	str2	String
    //   35	89	5	localObject3	Object
    //   32	25	6	localObject4	Object
    //   8	60	7	localFile	File
    // Exception table:
    //   from	to	target	type
    //   95	99	108	java/io/IOException
    //   103	107	116	java/io/IOException
    //   40	51	122	java/io/IOException
    //   59	77	122	java/io/IOException
    //   149	153	154	java/io/IOException
    //   140	145	160	java/io/IOException
    //   40	51	168	finally
    //   59	77	168	finally
    //   131	135	168	finally
    //   173	177	187	java/io/IOException
    //   181	185	195	java/io/IOException
    //   77	91	203	finally
    //   77	91	215	java/io/IOException
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
      localObject2 = App.get().getAssets();
    }
    try
    {
      localObject2 = ((AssetManager)localObject2).list(paramString);
      int i = 0;
      while (i < localObject2.length)
      {
        Object localObject3 = localObject2[i];
        boxPicsMap.put(localObject3, localObject3);
        i += 1;
      }
      dirMap.put(paramString, paramString);
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        boolean bool2;
        paramString.printStackTrace();
      }
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
  }
  
  private static void exitGame()
  {
    BBPlugin localBBPlugin;
    if (BBPluginManager.get().checkPluginIsExist("com.babybus.plugin.babybusdata.PluginBabybusData")) {
      localBBPlugin = BBPluginManager.get().getPlugin("com.babybus.plugin.babybusdata.PluginBabybusData");
    }
    try
    {
      App.get().isExitGame4Data = true;
      ReflectUtil.invokeMethod(localBBPlugin, "onPause");
      if (!App.get().u3d) {
        CallNative.gameExit();
      }
      App.get().exit();
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
  
  public static String filepathes(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("[");
    Object localObject = App.get().getAssets();
    try
    {
      localObject = ((AssetManager)localObject).list(paramString);
      int i = 0;
      if (i < localObject.length)
      {
        if (i < localObject.length - 1) {}
        for (paramString = "\"" + localObject[i] + "\",";; paramString = "\"" + localObject[i] + "\"")
        {
          localStringBuffer.append(paramString);
          i += 1;
          break;
        }
      }
      localStringBuffer.append("]");
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    return localStringBuffer.toString();
  }
  
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
    String str5 = "" + App.get().screenWidth + "," + App.get().screenHight + "";
    String str6 = getDeviceInfo();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("macAddr", str1);
      localJSONObject.put("model", "");
      localJSONObject.put("version", str2);
      localJSONObject.put("system", str3);
      localJSONObject.put("systemVersion", str4);
      localJSONObject.put("resolution", str5);
      localJSONObject.put("all", str6);
      return localJSONObject.toString();
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
      }
    }
  }
  
  public static String getLanguage()
  {
    return UIUtil.getLanguage();
  }
  
  public static String getLocalMacAddress()
  {
    WifiInfo localWifiInfo = ((WifiManager)App.get().getSystemService("wifi")).getConnectionInfo();
    if (localWifiInfo.getMacAddress() == null) {
      return "";
    }
    return localWifiInfo.getMacAddress();
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
      String str = App.get().getPackageManager().getPackageInfo(App.get().getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }
  
  public static boolean isMarketInstalledAmazon()
  {
    List localList = App.get().getPackageManager().getInstalledPackages(0);
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        if ("com.amazon.venezia".equals(((PackageInfo)localList.get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  /* Error */
  public static void launchMarket(String paramString)
  {
    // Byte code:
    //   0: ldc_w 524
    //   3: getstatic 274	android/os/Build:BRAND	Ljava/lang/String;
    //   6: invokevirtual 527	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   9: ifeq +4 -> 13
    //   12: return
    //   13: invokestatic 529	com/babybus/bbmodule/system/jni/manager/SystemBo:isMarketInstalledAmazon	()Z
    //   16: ifeq +56 -> 72
    //   19: new 531	android/content/Intent
    //   22: dup
    //   23: ldc_w 533
    //   26: new 147	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   33: ldc_w 535
    //   36: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_0
    //   40: invokevirtual 538	java/lang/String:trim	()Ljava/lang/String;
    //   43: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 156	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokestatic 544	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   52: invokespecial 547	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   55: astore_1
    //   56: aload_1
    //   57: ldc_w 548
    //   60: invokevirtual 552	android/content/Intent:setFlags	(I)Landroid/content/Intent;
    //   63: pop
    //   64: invokestatic 178	com/babybus/app/App:get	()Lcom/babybus/app/App;
    //   67: aload_1
    //   68: invokevirtual 556	com/babybus/app/App:startActivity	(Landroid/content/Intent;)V
    //   71: return
    //   72: aload_0
    //   73: invokestatic 559	com/babybus/bbmodule/system/jni/manager/SystemBo:launchMarketDefault	(Ljava/lang/String;)V
    //   76: return
    //   77: astore_1
    //   78: aload_0
    //   79: invokestatic 559	com/babybus/bbmodule/system/jni/manager/SystemBo:launchMarketDefault	(Ljava/lang/String;)V
    //   82: return
    //   83: astore_1
    //   84: goto -6 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	87	0	paramString	String
    //   55	13	1	localIntent	Intent
    //   77	1	1	localException1	Exception
    //   83	1	1	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	12	77	java/lang/Exception
    //   13	56	77	java/lang/Exception
    //   72	76	77	java/lang/Exception
    //   56	71	83	java/lang/Exception
  }
  
  public static void launchMarketDefault(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString.trim()));
    paramString.setFlags(268435456);
    App.get().startActivity(paramString);
  }
  
  public static void openTestActivity()
  {
    try
    {
      BBPlugin localBBPlugin = (BBPlugin)BBPluginManager.get().plugins.get("com.babybus.plugin.test.PluginTest");
      if (localBBPlugin != null) {}
      try
      {
        ReflectUtil.invokeMethod(localBBPlugin, "openTestActivity", new Object[0]);
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
  public static void showCustomDialogConfirm(final String paramString1, final String paramString2, final String paramString3)
  {
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(App.get()).inflate(App.get().getResources().getIdentifier("bb_custom_dialog_exit", "layout", App.get().getPackageName()), null);
    Button localButton = (Button)localLinearLayout.findViewById(App.get().getResources().getIdentifier("bb_custom_dialog_exit_iamge_button", "id", App.get().getPackageName()));
    try
    {
      localButton.setBackground(Drawable.createFromPath(SDCardUtil.getSDPATH() + SDCardUtil.BABYBUS_PATH + "dialogIamge.png"));
      localButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (!App.get().u3d) {
            CallNative.postNotification("DIALOG_CONFIRM_CLICK");
          }
        }
      });
      UIUtil.runOnUiThread(new Runnable()
      {
        public void run()
        {
          new AlertDialog.Builder(App.get().mainActivity).setView(this.val$dialogView).setMessage(paramString1).setNegativeButton(paramString3, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
          }).setPositiveButton(paramString2, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              if (!App.get().u3d) {
                CallNative.postNotification("DIALOG_CONFIRM_CBK");
              }
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
    App.get();
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = (LinearLayout)LayoutInflater.from(App.get()).inflate(R.layout.bb_custom_dialog_invoke, null);
        final AlertDialog localAlertDialog = new AlertDialog.Builder(App.get().mainActivity).create();
        localAlertDialog.show();
        localAlertDialog.setContentView((View)localObject);
        localAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        });
        ((TextView)((LinearLayout)localObject).findViewById(R.id.bb_custom_dialog_invoke_textview_title)).setText(UIUtil.getString("bb_invoke_title"));
        ((TextView)((LinearLayout)localObject).findViewById(R.id.bb_custom_dialog_invoke_textview_content)).setText(UIUtil.getString("bb_invoke_content_first_part") + "486036920" + "\n" + UIUtil.getString("bb_invoke_content_last_part"));
        Button localButton = (Button)((LinearLayout)localObject).findViewById(R.id.bb_custom_dialog_invoke_btn_invoke);
        localButton.setText(UIUtil.getString("yes"));
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            localAlertDialog.dismiss();
            paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://jq.qq.com/?_wv=1027&k=2G28PNf"));
            paramAnonymous2View.setFlags(268435456);
            App.get().startActivity(paramAnonymous2View);
          }
        });
        localObject = (Button)((LinearLayout)localObject).findViewById(R.id.bb_custom_dialog_invoke_btn_cancel);
        ((Button)localObject).setText(UIUtil.getString("no"));
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
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = (LinearLayout)LayoutInflater.from(App.get()).inflate(R.layout.bb_custom_dialog_exit, null);
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
        ((TextView)localAlertDialog.findViewById(App.get().getResources().getIdentifier("bb_custom_dialog_exit_textview_title", "id", App.get().getPackageName()))).setText(UIUtil.getString("bb_try_product"));
        localObject = (Button)localAlertDialog.findViewById(App.get().getResources().getIdentifier("bb_custom_dialog_exit_image_button", "id", App.get().getPackageName()));
        if ((this.val$imagePath != null) && (!"".equals(this.val$imagePath)))
        {
          if (NetUtil.get().isNetActive()) {
            PlatformSystem.sendEventUMeng("6e3276c197f5ec992db1f8b7c1686c29", paramInt2 + "");
          }
        }
        else
        {
          if (Build.VERSION.SDK_INT >= 16) {
            break label321;
          }
          ((Button)localObject).setBackgroundDrawable(Drawable.createFromPath(this.val$imagePath));
        }
        for (;;)
        {
          ((Button)localObject).setWidth(UIUtil.dip2Px(320));
          ((Button)localObject).setHeight(UIUtil.dip2Px(90));
          Button localButton1 = (Button)localAlertDialog.findViewById(R.id.bb_custom_dialog_exit_btn_exit);
          localButton1.setText(UIUtil.getString("bb_exit"));
          Button localButton2 = (Button)localAlertDialog.findViewById(R.id.bb_custom_dialog_exit_btn_cancel);
          localButton2.setText(UIUtil.getString("bb_resume"));
          ((Button)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              SystemBo.shutdownData = "1";
              BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, System.currentTimeMillis(), 2);
              SystemBo.shutdownData = "0";
              if (NetUtil.get().isNetActive()) {
                PlatformSystem.sendEventUMeng("e7ab289f66577f39dacaefa5190e703f", SystemBo.6.this.val$adid + "");
              }
              for (;;)
              {
                WebViewBo.openLink(SystemBo.6.this.val$url, SystemBo.6.this.val$key, SystemBo.6.this.val$name, SystemBo.6.this.val$umkey, SystemBo.6.this.val$opentype);
                return;
                PlatformSystem.sendEventUMeng("e7ab289f66577f39dacaefa5190e703f", SystemBo.6.this.val$adid + "(offline)");
              }
            }
          });
          localButton1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              if (App.get().u3d) {}
              try
              {
                ReflectUtil.invokeStaticMethod("com.unity3d.player.UnityPlayer", "UnitySendMessage", new Object[] { "NativePluginEventHandler", "OnExitGame", "close" });
                BBAdSystemBo.adStatic4Shutdown(SystemBo.shutdownData, 0L, 2);
                localAlertDialog.dismiss();
                SystemBo.access$000();
                return;
              }
              catch (Exception paramAnonymous2View)
              {
                for (;;)
                {
                  paramAnonymous2View.printStackTrace();
                }
              }
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
          PlatformSystem.sendEventUMeng("6e3276c197f5ec992db1f8b7c1686c29", paramInt2 + "(offline)");
          break;
          label321:
          ((Button)localObject).setBackground(Drawable.createFromPath(this.val$imagePath));
        }
      }
    });
  }
  
  public static void showDialog4giveMePraise(String paramString1, final String paramString2, final String paramString3)
  {
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(this.val$contentString).setPositiveButton(paramString2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            PlatformSystem.giveMePraise(SystemBo.8.this.val$appKey);
          }
        }).setNeutralButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).show();
      }
    });
  }
  
  public static void showDialogConfirm(String paramString1, final String paramString2, final String paramString3)
  {
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(this.val$contentString).setNegativeButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setPositiveButton(paramString2, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            if (!App.get().u3d) {
              CallNative.postNotification("DIALOG_CONFIRM_CBK");
            }
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
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(UIUtil.getString("quitOrReturn")).setNegativeButton(UIUtil.getString("no"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setPositiveButton(UIUtil.getString("yes"), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        }).show();
      }
    });
  }
  
  public static void showDialogSimple(String paramString1, String paramString2, final String paramString3)
  {
    UIUtil.runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(App.get().mainActivity).setMessage(this.val$contentString).setPositiveButton(paramString3, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int) {}
        }).setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymous2DialogInterface) {}
        }).show();
      }
    });
  }
  
  public static void vibrate()
  {
    ((Vibrator)App.get().mainActivity.getSystemService("vibrator")).vibrate(40L);
  }
}
