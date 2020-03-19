package com.yy.mobile.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.yy.mobile.framework.R.string;
import com.yy.mobile.util.log.MLog;
import com.yy.mobile.util.pref.CommonPref;
import java.util.Iterator;
import java.util.List;

public class AppHelperUtils
{
  public static final String ADD_SHORTCUT = "ADD_SHORTCUT";
  public static final String FROM_SHORTCUT = "FROM_SHORTCUT";
  private static final String KEY_APK_SIGN = "apk_sign_md5";
  private static String sProcessName;
  
  public AppHelperUtils() {}
  
  public static void addShortcut(Context paramContext)
  {
    addShortcut(paramContext, getTitle(paramContext));
  }
  
  public static void addShortcut(Context paramContext, String paramString)
  {
    Object localObject = new Intent(paramContext.getApplicationContext(), paramContext.getClass());
    ((Intent)localObject).setAction("android.intent.action.MAIN");
    ((Intent)localObject).putExtra("FROM_SHORTCUT", true);
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    Intent localIntent = new Intent();
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", (Parcelable)localObject);
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext.getApplicationContext(), localApplicationInfo.icon));
    localObject = paramString;
    if (FP.empty(paramString)) {
      localObject = "YY";
    }
    localIntent.putExtra("android.intent.extra.shortcut.NAME", (String)localObject);
    localIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
    paramContext.getApplicationContext().sendBroadcast(localIntent);
  }
  
  public static void deleteShortcut(Context paramContext)
  {
    Intent localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    Object localObject = paramContext.getApplicationInfo();
    localIntent.putExtra("android.intent.extra.shortcut.NAME", ((ApplicationInfo)localObject).name);
    localObject = new ComponentName(((ApplicationInfo)localObject).packageName, ((ApplicationInfo)localObject).className);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", new Intent("android.intent.action.MAIN").setComponent((ComponentName)localObject));
    paramContext.sendBroadcast(localIntent);
  }
  
  /* Error */
  public static String getProcessName(int paramInt)
  {
    // Byte code:
    //   0: ldc 127
    //   2: new 129	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   9: ldc -124
    //   11: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: iload_0
    //   15: invokevirtual 139	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   18: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: invokestatic 149	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: pop
    //   25: new 151	java/io/File
    //   28: dup
    //   29: new 129	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 130	java/lang/StringBuilder:<init>	()V
    //   36: ldc -103
    //   38: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: iload_0
    //   42: invokevirtual 139	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   45: ldc -101
    //   47: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokespecial 156	java/io/File:<init>	(Ljava/lang/String;)V
    //   56: astore_2
    //   57: aload_2
    //   58: invokevirtual 160	java/io/File:exists	()Z
    //   61: ifeq +83 -> 144
    //   64: aload_2
    //   65: invokevirtual 163	java/io/File:isDirectory	()Z
    //   68: ifne +76 -> 144
    //   71: aconst_null
    //   72: astore_1
    //   73: new 165	java/io/BufferedReader
    //   76: dup
    //   77: new 167	java/io/InputStreamReader
    //   80: dup
    //   81: new 169	java/io/FileInputStream
    //   84: dup
    //   85: aload_2
    //   86: invokespecial 172	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   89: invokespecial 175	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   92: invokespecial 178	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   95: astore_2
    //   96: aload_2
    //   97: astore_1
    //   98: aload_2
    //   99: invokevirtual 181	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   102: astore_3
    //   103: aload_2
    //   104: astore_1
    //   105: aload_3
    //   106: invokestatic 186	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   109: ifne +27 -> 136
    //   112: aload_2
    //   113: astore_1
    //   114: aload_3
    //   115: invokevirtual 191	java/lang/String:trim	()Ljava/lang/String;
    //   118: astore_3
    //   119: aload_2
    //   120: ifnull +7 -> 127
    //   123: aload_2
    //   124: invokevirtual 194	java/io/BufferedReader:close	()V
    //   127: aload_3
    //   128: areturn
    //   129: astore_1
    //   130: aload_1
    //   131: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   134: aload_3
    //   135: areturn
    //   136: aload_2
    //   137: ifnull +7 -> 144
    //   140: aload_2
    //   141: invokevirtual 194	java/io/BufferedReader:close	()V
    //   144: ldc -57
    //   146: areturn
    //   147: astore_1
    //   148: aload_1
    //   149: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   152: goto -8 -> 144
    //   155: astore_3
    //   156: aconst_null
    //   157: astore_2
    //   158: aload_2
    //   159: astore_1
    //   160: aload_3
    //   161: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   164: aload_2
    //   165: ifnull -21 -> 144
    //   168: aload_2
    //   169: invokevirtual 194	java/io/BufferedReader:close	()V
    //   172: goto -28 -> 144
    //   175: astore_1
    //   176: aload_1
    //   177: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   180: goto -36 -> 144
    //   183: astore_3
    //   184: aload_1
    //   185: astore_2
    //   186: aload_3
    //   187: astore_1
    //   188: aload_2
    //   189: ifnull +7 -> 196
    //   192: aload_2
    //   193: invokevirtual 194	java/io/BufferedReader:close	()V
    //   196: aload_1
    //   197: athrow
    //   198: astore_2
    //   199: aload_2
    //   200: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   203: goto -7 -> 196
    //   206: astore_3
    //   207: aload_1
    //   208: astore_2
    //   209: aload_3
    //   210: astore_1
    //   211: goto -23 -> 188
    //   214: astore_3
    //   215: goto -57 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	paramInt	int
    //   72	42	1	localObject1	Object
    //   129	2	1	localException1	Exception
    //   147	2	1	localException2	Exception
    //   159	1	1	localObject2	Object
    //   175	10	1	localException3	Exception
    //   187	24	1	localObject3	Object
    //   56	137	2	localObject4	Object
    //   198	2	2	localException4	Exception
    //   208	1	2	localObject5	Object
    //   102	33	3	str	String
    //   155	6	3	localException5	Exception
    //   183	4	3	localObject6	Object
    //   206	4	3	localObject7	Object
    //   214	1	3	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   123	127	129	java/lang/Exception
    //   140	144	147	java/lang/Exception
    //   73	96	155	java/lang/Exception
    //   168	172	175	java/lang/Exception
    //   73	96	183	finally
    //   192	196	198	java/lang/Exception
    //   98	103	206	finally
    //   105	112	206	finally
    //   114	119	206	finally
    //   160	164	206	finally
    //   98	103	214	java/lang/Exception
    //   105	112	214	java/lang/Exception
    //   114	119	214	java/lang/Exception
  }
  
  public static String getProcessName(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!TextUtils.isEmpty(sProcessName))
        {
          Log.i("AppHelperUtils", "getProcessName processName = " + sProcessName);
          paramContext = sProcessName;
          return paramContext;
        }
        int i = Process.myPid();
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
        if ((paramContext == null) || (paramContext.isEmpty()))
        {
          paramContext = getProcessName(i);
          MLog.info("AppHelperUtils", "getProcessName processName = " + sProcessName, new Object[0]);
          sProcessName = paramContext;
          paramContext = sProcessName;
        }
        else
        {
          paramContext = paramContext.iterator();
          if (paramContext.hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
            Log.i("AppHelperUtils", String.format("processName = %s", new Object[] { localRunningAppProcessInfo.processName }));
            if (localRunningAppProcessInfo.pid == i) {
              paramContext = localRunningAppProcessInfo.processName;
            }
          }
          else
          {
            paramContext = "";
          }
        }
      }
      finally {}
    }
  }
  
  public static String getSignMd5Str(Context paramContext)
  {
    try
    {
      String str = CommonPref.instance().get("apk_sign_md5");
      if (!TextUtils.isEmpty(str)) {
        return str;
      }
      paramContext = MD5Utils.getMD5String(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64).signatures[0].toByteArray());
      MLog.info("AppHelper", "APK Sign %s", new Object[] { paramContext });
      CommonPref.instance().putString("apk_sign_md5", str);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  private static String getTitle(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getString(R.string.short_cut_app_name);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      Log.e("AppHelperUtils", "Empty Catch on getTitle", paramContext);
    }
    return "Noizz";
  }
  
  public static boolean hasShortCut(Context paramContext)
  {
    String str;
    ContentResolver localContentResolver;
    if (Build.VERSION.SDK_INT < 8)
    {
      str = "content://com.android.launcher.settings/favorites?notify=true";
      localContentResolver = paramContext.getContentResolver();
      paramContext = getTitle(paramContext);
      if (!FP.empty(paramContext)) {
        break label99;
      }
      paramContext = "YY";
    }
    label99:
    for (;;)
    {
      paramContext = localContentResolver.query(Uri.parse(str), new String[] { "title", "iconResource" }, "title=?", new String[] { paramContext }, null);
      if ((paramContext != null) && (paramContext.moveToFirst()))
      {
        paramContext.close();
        return true;
        str = "content://com.android.launcher2.settings/favorites?notify=true";
        break;
      }
      return false;
    }
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    String str = getProcessName(paramContext);
    if (FP.empty(str)) {
      return false;
    }
    paramContext = paramContext.getPackageName();
    Log.i("AppHelperUtils", "package name = " + paramContext);
    return str.equals(paramContext);
  }
  
  public static boolean isWeixinAvilible(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.size())
        {
          boolean bool = ((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mm");
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      Log.e("AppHelperUtils", "Empty Catch on isWeixinAvilible", paramContext);
    }
  }
  
  /* Error */
  private static String readProcessName()
  {
    // Byte code:
    //   0: ldc 127
    //   2: ldc_w 402
    //   5: iconst_0
    //   6: anewarray 4	java/lang/Object
    //   9: invokestatic 233	com/yy/mobile/util/log/MLog:info	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: new 151	java/io/File
    //   15: dup
    //   16: ldc_w 404
    //   19: invokespecial 156	java/io/File:<init>	(Ljava/lang/String;)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 160	java/io/File:exists	()Z
    //   27: ifeq +83 -> 110
    //   30: aload_1
    //   31: invokevirtual 163	java/io/File:isDirectory	()Z
    //   34: ifne +76 -> 110
    //   37: aconst_null
    //   38: astore_0
    //   39: new 165	java/io/BufferedReader
    //   42: dup
    //   43: new 167	java/io/InputStreamReader
    //   46: dup
    //   47: new 169	java/io/FileInputStream
    //   50: dup
    //   51: aload_1
    //   52: invokespecial 172	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   55: invokespecial 175	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   58: invokespecial 178	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   61: astore_1
    //   62: aload_1
    //   63: astore_0
    //   64: aload_1
    //   65: invokevirtual 181	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   68: astore_2
    //   69: aload_1
    //   70: astore_0
    //   71: aload_2
    //   72: invokestatic 186	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   75: ifne +27 -> 102
    //   78: aload_1
    //   79: astore_0
    //   80: aload_2
    //   81: invokevirtual 191	java/lang/String:trim	()Ljava/lang/String;
    //   84: astore_2
    //   85: aload_1
    //   86: ifnull +7 -> 93
    //   89: aload_1
    //   90: invokevirtual 194	java/io/BufferedReader:close	()V
    //   93: aload_2
    //   94: areturn
    //   95: astore_0
    //   96: aload_0
    //   97: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   100: aload_2
    //   101: areturn
    //   102: aload_1
    //   103: ifnull +7 -> 110
    //   106: aload_1
    //   107: invokevirtual 194	java/io/BufferedReader:close	()V
    //   110: ldc -57
    //   112: areturn
    //   113: astore_0
    //   114: aload_0
    //   115: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   118: goto -8 -> 110
    //   121: astore_2
    //   122: aconst_null
    //   123: astore_1
    //   124: aload_1
    //   125: astore_0
    //   126: aload_2
    //   127: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   130: aload_1
    //   131: ifnull -21 -> 110
    //   134: aload_1
    //   135: invokevirtual 194	java/io/BufferedReader:close	()V
    //   138: goto -28 -> 110
    //   141: astore_0
    //   142: aload_0
    //   143: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   146: goto -36 -> 110
    //   149: astore_2
    //   150: aload_0
    //   151: astore_1
    //   152: aload_2
    //   153: astore_0
    //   154: aload_1
    //   155: ifnull +7 -> 162
    //   158: aload_1
    //   159: invokevirtual 194	java/io/BufferedReader:close	()V
    //   162: aload_0
    //   163: athrow
    //   164: astore_1
    //   165: aload_1
    //   166: invokevirtual 197	java/lang/Exception:printStackTrace	()V
    //   169: goto -7 -> 162
    //   172: astore_2
    //   173: aload_0
    //   174: astore_1
    //   175: aload_2
    //   176: astore_0
    //   177: goto -23 -> 154
    //   180: astore_2
    //   181: goto -57 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   38	42	0	localObject1	Object
    //   95	2	0	localException1	Exception
    //   113	2	0	localException2	Exception
    //   125	1	0	localObject2	Object
    //   141	10	0	localException3	Exception
    //   153	24	0	localObject3	Object
    //   22	137	1	localObject4	Object
    //   164	2	1	localException4	Exception
    //   174	1	1	localObject5	Object
    //   68	33	2	str	String
    //   121	6	2	localException5	Exception
    //   149	4	2	localObject6	Object
    //   172	4	2	localObject7	Object
    //   180	1	2	localException6	Exception
    // Exception table:
    //   from	to	target	type
    //   89	93	95	java/lang/Exception
    //   106	110	113	java/lang/Exception
    //   39	62	121	java/lang/Exception
    //   134	138	141	java/lang/Exception
    //   39	62	149	finally
    //   158	162	164	java/lang/Exception
    //   64	69	172	finally
    //   71	78	172	finally
    //   80	85	172	finally
    //   126	130	172	finally
    //   64	69	180	java/lang/Exception
    //   71	78	180	java/lang/Exception
    //   80	85	180	java/lang/Exception
  }
}
