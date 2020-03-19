package mobi.infolife.ezweather.widget.store;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CommonUtils
{
  public static final String UPDATE_WEATHER_ACTION = "mobi.infolife.ezweather.update.weather.action";
  public static final String WEATHER_DATA_ID_PARAM = "weather_data_id";
  public static String emailaddr = null;
  public static boolean isAutoLogin = false;
  
  CommonUtils() {}
  
  public static List<Integer> addArrayToList(List<Integer> paramList, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt != null) && (paramList != null))
    {
      int i = 0;
      while (i <= paramArrayOfInt.length - 1)
      {
        paramList.add(Integer.valueOf(paramArrayOfInt[i]));
        i += 1;
      }
    }
    return paramList;
  }
  
  public static void adddDiamondRingAnimation(ImageView paramImageView)
  {
    ((AnimationDrawable)paramImageView.getBackground()).start();
  }
  
  public static void changeLocaleSetting(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setClassName("com.android.settings", "com.android.settings.DateTimeSettings");
    paramContext.startActivity(localIntent);
  }
  
  public static int[] combineArray(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if ((paramArrayOfInt1 == null) && (paramArrayOfInt2 == null)) {
      arrayOfInt = null;
    }
    do
    {
      return arrayOfInt;
      if (paramArrayOfInt1 != null) {
        break;
      }
      arrayOfInt = paramArrayOfInt2;
    } while (paramArrayOfInt2 != null);
    if ((paramArrayOfInt2 == null) && (paramArrayOfInt1 != null)) {
      return paramArrayOfInt1;
    }
    int j = paramArrayOfInt1.length;
    int k = paramArrayOfInt2.length;
    int[] arrayOfInt = new int[j + k];
    int i = 0;
    while (i <= j - 1)
    {
      arrayOfInt[i] = paramArrayOfInt1[i];
      i += 1;
    }
    i = 0;
    while (i <= k - 1)
    {
      arrayOfInt[(j + i)] = paramArrayOfInt2[i];
      i += 1;
    }
    return arrayOfInt;
  }
  
  /* Error */
  public static void downloadPlugin(Context paramContext, PluginInfo paramPluginInfo)
  {
    // Byte code:
    //   0: new 57	android/content/Intent
    //   3: dup
    //   4: ldc 59
    //   6: new 84	java/lang/StringBuilder
    //   9: dup
    //   10: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   13: ldc 87
    //   15: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_1
    //   19: invokevirtual 97	mobi/infolife/ezweather/widget/store/PluginInfo:getPkgName	()Ljava/lang/String;
    //   22: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokestatic 106	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   31: invokespecial 109	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   34: astore_2
    //   35: aload_0
    //   36: aload_2
    //   37: invokevirtual 76	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   40: return
    //   41: astore_2
    //   42: aconst_null
    //   43: astore_2
    //   44: new 57	android/content/Intent
    //   47: dup
    //   48: ldc 59
    //   50: new 84	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 85	java/lang/StringBuilder:<init>	()V
    //   57: ldc 111
    //   59: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_1
    //   63: invokevirtual 97	mobi/infolife/ezweather/widget/store/PluginInfo:getPkgName	()Ljava/lang/String;
    //   66: invokevirtual 91	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 100	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokestatic 106	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   75: invokespecial 109	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   78: astore_1
    //   79: aload_0
    //   80: aload_1
    //   81: invokevirtual 76	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   84: return
    //   85: astore_0
    //   86: return
    //   87: astore_0
    //   88: return
    //   89: astore_3
    //   90: goto -46 -> 44
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramContext	Context
    //   0	93	1	paramPluginInfo	PluginInfo
    //   34	3	2	localIntent	Intent
    //   41	1	2	localException1	Exception
    //   43	1	2	localObject	Object
    //   89	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	35	41	java/lang/Exception
    //   79	84	85	java/lang/Exception
    //   44	79	87	java/lang/Exception
    //   35	40	89	java/lang/Exception
  }
  
  public static String exec(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    InputStream localInputStream;
    StringBuilder localStringBuilder;
    try
    {
      localInputStream = Runtime.getRuntime().exec(paramString).getInputStream();
      str1 = str2;
      paramString = new byte['Ѐ'];
      str1 = str2;
      localStringBuilder = new StringBuilder();
      for (;;)
      {
        str1 = str2;
        int i = localInputStream.read(paramString, 0, paramString.length);
        if (i == -1) {
          break;
        }
        str1 = str2;
        localStringBuilder.append(new String(paramString, 0, i));
      }
      str1 = str2;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
      return str1;
    }
    paramString = localStringBuilder.toString();
    str1 = paramString;
    localInputStream.close();
    return paramString;
  }
  
  public static Animation fadeOutAnimation()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setDuration(200L);
    return localAlphaAnimation;
  }
  
  public static String getClassName(Context paramContext, String paramString)
  {
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    localObject = "";
    Iterator localIterator = paramContext.iterator();
    do
    {
      paramContext = (Context)localObject;
      if (!localIterator.hasNext()) {
        break;
      }
      paramContext = (ResolveInfo)localIterator.next();
    } while (!paramContext.activityInfo.packageName.equals(paramString));
    paramContext = paramContext.activityInfo.name;
    return paramContext;
  }
  
  public static String getColoredHtmlString(String paramString, int paramInt)
  {
    if (paramString == null) {
      return null;
    }
    return "<font color=\"#" + Integer.toHexString(paramInt) + "\">" + paramString + "</font>";
  }
  
  public static int getCurrentVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    try
    {
      int i = localPackageManager.getPackageInfo(paramContext, 0).versionCode;
      return i;
    }
    catch (Exception paramContext) {}
    return 1;
  }
  
  public static String getDefaultBackupPath()
  {
    return getSDCardDirPath() + "/" + "EzWeather";
  }
  
  public static String getExternalStorageDirPath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static List<String> getHaveKillPermissionApps(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getInstalledAppList(paramContext, 4096);
    paramContext = paramContext.getPackageManager();
    localObject = ((List)localObject).iterator();
    label153:
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      String[] arrayOfString = localPackageInfo.requestedPermissions;
      if (arrayOfString != null)
      {
        int k = 0;
        int j = 0;
        int m = arrayOfString.length;
        int i = 0;
        for (;;)
        {
          if (i >= m) {
            break label153;
          }
          String str = arrayOfString[i];
          if ("android.permission.KILL_BACKGROUND_PROCESSES".equals(str)) {
            k = 1;
          }
          if ("android.permission.GET_TASKS".equals(str)) {
            j = 1;
          }
          if ((j != 0) && (k != 0))
          {
            localArrayList.add(localPackageInfo.applicationInfo.loadLabel(paramContext).toString());
            break;
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static List<PackageInfo> getInstalledAppList(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  public static List<PluginInfo> getInstalledPluginsForStore(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: new 264	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 265	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: invokevirtual 176	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: astore 5
    //   15: aload_0
    //   16: sipush 8192
    //   19: invokestatic 269	mobi/infolife/ezweather/widget/store/CommonUtils:getInstalledAppList	(Landroid/content/Context;I)Ljava/util/List;
    //   22: astore_2
    //   23: aconst_null
    //   24: astore_3
    //   25: aload_2
    //   26: invokeinterface 186 1 0
    //   31: astore 6
    //   33: aload 6
    //   35: invokeinterface 192 1 0
    //   40: ifeq +206 -> 246
    //   43: aload 6
    //   45: invokeinterface 196 1 0
    //   50: checkcast 236	android/content/pm/PackageInfo
    //   53: getfield 300	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   56: astore 7
    //   58: aload 5
    //   60: aload 7
    //   62: sipush 128
    //   65: invokevirtual 304	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   68: astore_2
    //   69: aload_2
    //   70: astore_3
    //   71: aload_2
    //   72: ifnull -39 -> 33
    //   75: aload_2
    //   76: astore_3
    //   77: aload_2
    //   78: getfield 308	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   81: ifnull -48 -> 33
    //   84: aload_2
    //   85: getfield 308	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   88: ldc_w 310
    //   91: invokevirtual 315	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   94: astore 8
    //   96: aload_2
    //   97: astore_3
    //   98: aload 8
    //   100: ifnull -67 -> 33
    //   103: aload_2
    //   104: astore_3
    //   105: ldc 117
    //   107: aload 8
    //   109: invokevirtual 210	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   112: ifne -79 -> 33
    //   115: iload_1
    //   116: tableswitch	default:+20->136, 0:+25->141
    //   136: aload_2
    //   137: astore_3
    //   138: goto -105 -> 33
    //   141: aload_2
    //   142: astore_3
    //   143: ldc_w 317
    //   146: aload 8
    //   148: invokevirtual 210	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   151: ifeq -118 -> 33
    //   154: aload_0
    //   155: aload 7
    //   157: invokestatic 321	mobi/infolife/ezweather/widget/store/CommonUtils:getOtherAppContext	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Context;
    //   160: astore_3
    //   161: aload_3
    //   162: aload 7
    //   164: invokestatic 325	mobi/infolife/ezweather/widget/store/CommonUtils:getRClass	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Class;
    //   167: astore 8
    //   169: new 93	mobi/infolife/ezweather/widget/store/PluginInfo
    //   172: dup
    //   173: aload 7
    //   175: invokespecial 326	mobi/infolife/ezweather/widget/store/PluginInfo:<init>	(Ljava/lang/String;)V
    //   178: astore 7
    //   180: aload 7
    //   182: aload 5
    //   184: aload_2
    //   185: invokevirtual 330	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   188: invokeinterface 290 1 0
    //   193: invokevirtual 333	mobi/infolife/ezweather/widget/store/PluginInfo:setTitle	(Ljava/lang/String;)V
    //   196: aload_3
    //   197: invokevirtual 337	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   200: aload 8
    //   202: ldc_w 339
    //   205: ldc_w 341
    //   208: invokestatic 345	mobi/infolife/ezweather/widget/store/CommonUtils:getResourseIdByName	(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/String;)I
    //   211: invokevirtual 351	android/content/res/Resources:getBoolean	(I)Z
    //   214: pop
    //   215: aload 7
    //   217: iconst_1
    //   218: invokevirtual 355	mobi/infolife/ezweather/widget/store/PluginInfo:setInstalled	(Z)V
    //   221: aload 4
    //   223: aload 7
    //   225: invokeinterface 38 2 0
    //   230: pop
    //   231: aload_2
    //   232: astore_3
    //   233: goto -200 -> 33
    //   236: astore_2
    //   237: aload_2
    //   238: invokevirtual 356	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   241: aload_3
    //   242: astore_2
    //   243: goto -174 -> 69
    //   246: aload 4
    //   248: areturn
    //   249: astore_3
    //   250: goto -35 -> 215
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	paramContext	Context
    //   0	253	1	paramInt	int
    //   22	210	2	localObject1	Object
    //   236	2	2	localNameNotFoundException	PackageManager.NameNotFoundException
    //   242	1	2	localObject2	Object
    //   24	218	3	localObject3	Object
    //   249	1	3	localException	Exception
    //   7	240	4	localArrayList	ArrayList
    //   13	170	5	localPackageManager	PackageManager
    //   31	13	6	localIterator	Iterator
    //   56	168	7	localObject4	Object
    //   94	107	8	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   58	69	236	android/content/pm/PackageManager$NameNotFoundException
    //   196	215	249	java/lang/Exception
  }
  
  public static Intent getIntentFromPackageName(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName(paramString, getClassName(paramContext, paramString));
    return localIntent;
  }
  
  public static int getNotifiIDFromPackgeName(String paramString)
  {
    if (paramString.equals(Constants.notifiThemeArray[0])) {}
    do
    {
      return 0;
      if (paramString.equals(Constants.notifiThemeArray[1])) {
        return 1;
      }
    } while (!paramString.equals(Constants.notifiThemeArray[2]));
    return 2;
  }
  
  public static Context getOtherAppContext(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Class<?> getRClass(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass(paramString + ".R");
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static int getResourseIdByName(Class paramClass, String paramString1, String paramString2)
  {
    int j = 0;
    try
    {
      Class[] arrayOfClass = paramClass.getClasses();
      Object localObject = null;
      int i = 0;
      for (;;)
      {
        paramClass = localObject;
        if (i < arrayOfClass.length)
        {
          if (arrayOfClass[i].getName().split("\\$")[1].equals(paramString1)) {
            paramClass = arrayOfClass[i];
          }
        }
        else
        {
          i = j;
          if (paramClass != null) {
            i = paramClass.getField(paramString2).getInt(paramClass);
          }
          return i;
        }
        i += 1;
      }
      return 0;
    }
    catch (IllegalArgumentException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (SecurityException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (IllegalAccessException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (NoSuchFieldException paramClass)
    {
      paramClass.printStackTrace();
      return 0;
    }
    catch (Exception paramClass)
    {
      paramClass.printStackTrace();
    }
  }
  
  public static String getSDCardDirPath()
  {
    if (isSamSungDevice())
    {
      if (isSamSungExternalSDMounted()) {
        return getExternalStorageDirPath() + "/" + "external_sd";
      }
      return getExternalStorageDirPath();
    }
    return getExternalStorageDirPath();
  }
  
  public static int getSDKVersionNumber()
  {
    try
    {
      int i = Integer.valueOf(Build.VERSION.SDK).intValue();
      return i;
    }
    catch (NumberFormatException localNumberFormatException) {}
    return 0;
  }
  
  public static int getSkinIDFromPackgeName(String paramString)
  {
    if (paramString.equals(Constants.appSkinArray[0])) {}
    do
    {
      return 0;
      if (paramString.equals(Constants.appSkinArray[1])) {
        return 1;
      }
      if (paramString.equals(Constants.appSkinArray[2])) {
        return 2;
      }
      if (paramString.equals(Constants.appSkinArray[3])) {
        return 3;
      }
    } while (!paramString.equals(Constants.appSkinArray[4]));
    return 4;
  }
  
  public static Drawable getUsingDataSourceIcon(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 0).loadIcon(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Class<?> getUtilsClass(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass(paramString + ".Utils");
      return paramContext;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static void gotoMarket(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramContext.getPackageName())));
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramContext.getPackageName())));
    }
  }
  
  public static void gotoMarket(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
      return;
    }
    catch (Exception localException)
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
    }
  }
  
  public static void hidePluginIcon(Context paramContext, String paramString)
  {
    localContext2 = null;
    localContext1 = null;
    localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      localContext1 = paramContext;
      localContext2 = paramContext;
      paramString = localPackageManager.getLaunchIntentForPackage(paramString);
      localContext1 = paramContext;
      localContext2 = paramContext;
      paramString = paramContext.getClassLoader().loadClass(paramString.resolveActivity(localPackageManager).getClassName());
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        int i;
        paramContext.printStackTrace();
        paramContext = localContext1;
        paramString = localObject;
      }
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = localContext2;
        paramString = localObject;
      }
    }
    paramContext = new ComponentName(paramContext, paramString);
    i = localPackageManager.getComponentEnabledSetting(paramContext);
    if ((i == 0) || (i == 1)) {
      localPackageManager.setComponentEnabledSetting(paramContext, 2, 1);
    }
  }
  
  public static Animation inFromLeftAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static Animation inFromRightAndFadeInAnimation()
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.5F, 1.0F);
    localAlphaAnimation.setDuration(600L);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, -1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localAnimationSet.addAnimation(localAlphaAnimation);
    localAnimationSet.addAnimation(localTranslateAnimation);
    return localAnimationSet;
  }
  
  public static Animation inFromRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 1.0F, 2, 0.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static boolean isAirplaneModeOn(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1;
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isGpsOn(Context paramContext)
  {
    return Settings.Secure.isLocationProviderEnabled(paramContext.getContentResolver(), "gps");
  }
  
  public static boolean isNetworkAvaliable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getAllNetworkInfo();
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if (paramContext[i].getState() == NetworkInfo.State.CONNECTED) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static boolean isSamSungDevice()
  {
    return new File(getExternalStorageDirPath() + "/" + "external_sd").exists();
  }
  
  public static boolean isSamSungExternalSDMounted()
  {
    return exec("mount").indexOf("external_sd") >= 0;
  }
  
  public static boolean isWifiAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getAllNetworkInfo();
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.length)
        {
          if ((paramContext[i].getState() == NetworkInfo.State.CONNECTED) && (paramContext[i].getTypeName().equals("WIFI"))) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static String[][] loadAlarmInfo(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.SET_ALARM");
    ((Intent)localObject).putExtra("android.intent.extra.alarm.MESSAGE", "New Alarm");
    ((Intent)localObject).putExtra("android.intent.extra.alarm.HOUR", 10);
    ((Intent)localObject).putExtra("android.intent.extra.alarm.MINUTES", 30);
    List localList = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    int j = localList.size();
    String[][] arrayOfString = (String[][])Array.newInstance(String.class, new int[] { j, 3 });
    localObject = "";
    int i = 0;
    while (i < j)
    {
      String str1 = ((ResolveInfo)localList.get(i)).loadLabel(paramContext.getPackageManager()).toString();
      String str2 = ((ResolveInfo)localList.get(i)).activityInfo.packageName;
      String str3 = ((ResolveInfo)localList.get(i)).activityInfo.name;
      arrayOfString[i][0] = str1;
      arrayOfString[i][1] = str2;
      arrayOfString[i][2] = str3;
      localObject = (String)localObject + str1 + "/";
      i += 1;
    }
    return arrayOfString;
  }
  
  public static String[][] loadCalendarInfo(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.EDIT");
    ((Intent)localObject).setType("vnd.android.cursor.item/event");
    ((Intent)localObject).putExtra("title", "Some title");
    ((Intent)localObject).putExtra("description", "Some description");
    String str1 = "";
    localObject = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0);
    int j = ((List)localObject).size();
    String[][] arrayOfString = (String[][])Array.newInstance(String.class, new int[] { j, 3 });
    int i = 0;
    while (i < j)
    {
      String str2 = ((ResolveInfo)((List)localObject).get(i)).activityInfo.packageName;
      String str3 = ((ResolveInfo)((List)localObject).get(i)).loadLabel(paramContext.getPackageManager()).toString();
      String str4 = ((ResolveInfo)((List)localObject).get(i)).activityInfo.name;
      arrayOfString[i][0] = str3;
      arrayOfString[i][1] = str2;
      arrayOfString[i][2] = str4;
      str1 = str1 + str3 + "/";
      i += 1;
    }
    return arrayOfString;
  }
  
  public static void log(String paramString) {}
  
  public static Animation outToLeftAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, -1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static Animation outToRightAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(2, 0.0F, 2, 1.0F, 2, 0.0F, 2, 0.0F);
    localTranslateAnimation.setDuration(200L);
    localTranslateAnimation.setInterpolator(new AccelerateInterpolator());
    return localTranslateAnimation;
  }
  
  public static byte[] readStream(InputStream paramInputStream)
    throws Exception
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1) {
        break;
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    localByteArrayOutputStream.close();
    paramInputStream.close();
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static void sendUpdateWeatherBroadcast(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent("mobi.infolife.ezweather.update.weather.action");
    localIntent.putExtra("weather_data_id", paramInt);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int j = 0;
    int i = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }
  
  public static void showDebugToast(Context paramContext, String paramString) {}
  
  public static void showLongToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }
  
  public static void showShortToast(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
  
  public static String stringArrayToString(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder("");
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(paramArrayOfString[i]);
      localStringBuilder.append("#WEATHERSPLITER#");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String[] stringToStringArray(String paramString)
  {
    if (paramString == null) {}
    while (paramString == "Unknown") {
      return null;
    }
    return paramString.split("#WEATHERSPLITER#");
  }
  
  public static PluginInfo updateDataSourcePluginInfo(Context paramContext, PluginInfo paramPluginInfo)
  {
    paramContext = getOtherAppContext(paramContext, paramPluginInfo.getPkgName());
    Class localClass = getRClass(paramContext, paramPluginInfo.getPkgName());
    if (localClass != null)
    {
      paramPluginInfo.setRestriction(paramContext.getString(getResourseIdByName(localClass, "string", "restriction")));
      paramPluginInfo.setFeature(paramContext.getString(getResourseIdByName(localClass, "string", "feature")));
    }
    return paramPluginInfo;
  }
}
