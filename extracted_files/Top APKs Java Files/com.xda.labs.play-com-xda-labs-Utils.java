package com.xda.labs;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.processphoenix.ProcessPhoenix;
import com.xda.labs.entities.AppBucket;
import com.xda.labs.entities.AppCategory;
import com.xda.labs.entities.AppListResponse;
import com.xda.labs.entities.AppPermission;
import com.xda.labs.entities.NewAvatar;
import com.xda.labs.entities.XposedCategory;
import com.xda.labs.entities.XposedPermission;
import com.xda.labs.entities.XposedResults;
import com.xda.labs.messages.VolleyRequestFailed;
import com.xda.labs.one.ui.UserProfileActivity;
import com.xda.labs.realm.AppCache;
import com.xda.labs.realm.XposedCache;
import com.xda.labs.services.PushService;
import io.realm.Realm;
import io.realm.RealmQuery;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import trikita.log.Log;

public class Utils
{
  public static ArrayList<String> mInstalledApps;
  
  public Utils() {}
  
  /* Error */
  public static void addRippleToView(Context paramContext, View paramView, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iload_2
    //   1: ifeq +24 -> 25
    //   4: aload_0
    //   5: iload_2
    //   6: invokestatic 33	android/support/v4/content/ContextCompat:c	(Landroid/content/Context;I)I
    //   9: istore 4
    //   11: iload 4
    //   13: istore_2
    //   14: aload_1
    //   15: invokevirtual 39	android/view/View:getBackground	()Landroid/graphics/drawable/Drawable;
    //   18: invokestatic 45	android/support/v4/graphics/drawable/DrawableCompat:g	(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    //   21: iload_2
    //   22: invokestatic 49	android/support/v4/graphics/drawable/DrawableCompat:a	(Landroid/graphics/drawable/Drawable;I)V
    //   25: aload_0
    //   26: iload_3
    //   27: invokestatic 33	android/support/v4/content/ContextCompat:c	(Landroid/content/Context;I)I
    //   30: istore_2
    //   31: aload_1
    //   32: invokestatic 54	com/balysv/materialripple/MaterialRippleLayout:a	(Landroid/view/View;)Lcom/balysv/materialripple/MaterialRippleLayout$RippleBuilder;
    //   35: iload_2
    //   36: invokevirtual 59	com/balysv/materialripple/MaterialRippleLayout$RippleBuilder:a	(I)Lcom/balysv/materialripple/MaterialRippleLayout$RippleBuilder;
    //   39: ldc 60
    //   41: invokevirtual 63	com/balysv/materialripple/MaterialRippleLayout$RippleBuilder:a	(F)Lcom/balysv/materialripple/MaterialRippleLayout$RippleBuilder;
    //   44: iconst_1
    //   45: invokevirtual 67	com/balysv/materialripple/MaterialRippleLayout$RippleBuilder:b	(Z)Lcom/balysv/materialripple/MaterialRippleLayout$RippleBuilder;
    //   48: iconst_1
    //   49: invokevirtual 69	com/balysv/materialripple/MaterialRippleLayout$RippleBuilder:a	(Z)Lcom/balysv/materialripple/MaterialRippleLayout$RippleBuilder;
    //   52: invokevirtual 72	com/balysv/materialripple/MaterialRippleLayout$RippleBuilder:a	()Lcom/balysv/materialripple/MaterialRippleLayout;
    //   55: pop
    //   56: return
    //   57: astore 5
    //   59: goto -45 -> 14
    //   62: astore_0
    //   63: iload_3
    //   64: istore_2
    //   65: goto -34 -> 31
    //   68: astore_0
    //   69: return
    //   70: astore 5
    //   72: goto -47 -> 25
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	75	0	paramContext	Context
    //   0	75	1	paramView	View
    //   0	75	2	paramInt1	int
    //   0	75	3	paramInt2	int
    //   9	3	4	i	int
    //   57	1	5	localException1	Exception
    //   70	1	5	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   4	11	57	java/lang/Exception
    //   25	31	62	java/lang/Exception
    //   31	56	68	java/lang/Exception
    //   14	25	70	java/lang/Exception
  }
  
  public static void addRippleToView(Context paramContext, Button paramButton)
  {
    addRippleToView(paramContext, paramButton, 2131558405);
  }
  
  public static void addRippleToView(Context paramContext, Button paramButton, int paramInt)
  {
    addRippleToView(paramContext, paramButton, paramInt, 2131558583);
  }
  
  public static boolean canSideload(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 17) {
      if (Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) != 1) {}
    }
    while (Settings.Global.getInt(paramContext.getContentResolver(), "install_non_market_apps", 0) == 1)
    {
      return true;
      return false;
    }
    return false;
  }
  
  public static void closeKeyboard(Context paramContext, View paramView)
  {
    paramView.clearFocus();
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void colorizeIndeterminateDrawable(ProgressBar paramProgressBar, int paramInt)
  {
    Drawable localDrawable = paramProgressBar.getIndeterminateDrawable().mutate();
    DrawableCompat.a(DrawableCompat.g(localDrawable), paramInt);
    paramProgressBar.setIndeterminateDrawable(localDrawable);
  }
  
  public static void copyToClipboard(Context paramContext, String paramString1, String paramString2)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("labs", paramString1));
    Toast.makeText(paramContext, paramString2, 0).show();
    EventHelper.UrlCopied();
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources();
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getDisplayMetrics());
  }
  
  public static void forceEnglish(Context paramContext)
  {
    if (Hub.getSharedPrefsHelper().getBoolean("settings_force_english").booleanValue())
    {
      Configuration localConfiguration = new Configuration(paramContext.getResources().getConfiguration());
      localConfiguration.locale = Locale.ENGLISH;
      paramContext.getResources().updateConfiguration(localConfiguration, paramContext.getResources().getDisplayMetrics());
    }
  }
  
  public static String generateUserID()
  {
    String str = UUID.randomUUID().toString();
    Hub.getSharedPrefsHelper().setPref("uuid", str);
    return str;
  }
  
  public static boolean getAppEnabled(Context paramContext, String paramString)
  {
    if (!Hub.getSharedPrefsHelper().getBoolean("settings_hide_disabled_apps").booleanValue()) {
      return true;
    }
    paramContext = paramContext.getPackageManager();
    try
    {
      boolean bool = paramContext.getApplicationInfo(paramString, 0).enabled;
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static String getAppName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = (String)paramContext.getApplicationLabel(paramContext.getApplicationInfo(paramString, 0));
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "(unknown)";
  }
  
  public static String getAppSignature(Context paramContext, String paramString)
  {
    int i = 0;
    paramContext = paramContext.getPackageManager();
    try
    {
      paramString = paramContext.getPackageInfo(paramString, 64).signatures[0];
      if (paramString == null) {
        throw new Exception();
      }
    }
    catch (Exception paramContext)
    {
      return null;
    }
    paramContext = "";
    try
    {
      Object localObject = MessageDigest.getInstance("SHA256");
      ((MessageDigest)localObject).update(paramString.toByteArray());
      localObject = ((MessageDigest)localObject).digest();
      int j = localObject.length;
      while (i < j)
      {
        String str = Integer.toString(localObject[i] & 0xFF, 16);
        paramString = paramContext;
        if (str.length() == 1) {
          paramString = paramContext + "0";
        }
        paramContext = paramString + str;
        i += 1;
      }
      paramContext = paramContext.toUpperCase();
      return paramContext;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getAppUid(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getApplicationInfo(paramString, 0).uid;
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static int getAppVersionCode(Context paramContext, String paramString)
  {
    if (Hub.mAppVersionCache == null) {
      Hub.mAppVersionCache = new HashMap();
    }
    int i;
    if (Hub.mAppVersionCache.containsKey(paramString)) {
      i = ((Integer)Hub.mAppVersionCache.get(paramString)).intValue();
    }
    int j;
    do
    {
      return i;
      j = getAppVersionLookup(paramContext, paramString);
      i = j;
    } while (j <= -1);
    Hub.mAppVersionCache.put(paramString, Integer.valueOf(j));
    return j;
  }
  
  public static int getAppVersionLookup(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo(paramString, 128).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      Log.a("getAppVersionCode bailed! packageName [%s]", new Object[] { paramString });
    }
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 1).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "";
  }
  
  public static int getAttrColor(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true);
    return localTypedValue.data;
  }
  
  public static String getAverageRating(float paramFloat)
  {
    DecimalFormat localDecimalFormat = new DecimalFormat("#.##");
    if (paramFloat == 0.0F) {}
    for (double d = 5.0D;; d = paramFloat) {
      return localDecimalFormat.format(d);
    }
  }
  
  public static String getCallingActivityName(Activity paramActivity)
  {
    Object localObject = null;
    ComponentName localComponentName = paramActivity.getCallingActivity();
    if (localComponentName != null) {
      paramActivity = localComponentName.getClassName();
    }
    do
    {
      return paramActivity;
      localComponentName = paramActivity.getComponentName();
      paramActivity = localObject;
    } while (localComponentName == null);
    return localComponentName.getClassName();
  }
  
  public static int getColor(Context paramContext, int paramInt)
  {
    return ContextCompat.c(paramContext, paramInt);
  }
  
  public static int getDiscoverSetTheme()
  {
    return getSetTheme("discover_theme", false);
  }
  
  public static int getFontPref()
  {
    return Hub.getSharedPrefsHelper().getInt("settings_font_size", 0);
  }
  
  public static String getFontPrefString()
  {
    return Constants.PREF_SETTINGS_FONT_SIZE_STRINGS[getFontPref()];
  }
  
  public static ArrayList<String> getInstalledAppsList(Context paramContext)
  {
    try
    {
      if (mInstalledApps == null)
      {
        mInstalledApps = getInstalledPackages(paramContext, 128);
        Log.a("Fetching installed apps list [%s]", new Object[] { Integer.valueOf(mInstalledApps.size()) });
      }
      paramContext = mInstalledApps;
      return paramContext;
    }
    finally {}
  }
  
  /* Error */
  public static ArrayList<String> getInstalledPackages(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 260	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_0
    //   5: new 477	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 485	java/util/ArrayList:<init>	()V
    //   12: astore 4
    //   14: getstatic 88	android/os/Build$VERSION:SDK_INT	I
    //   17: bipush 22
    //   19: if_icmplt +47 -> 66
    //   22: aload_0
    //   23: iload_1
    //   24: invokevirtual 489	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   27: invokeinterface 495 1 0
    //   32: astore_0
    //   33: aload_0
    //   34: invokeinterface 500 1 0
    //   39: ifeq +24 -> 63
    //   42: aload 4
    //   44: aload_0
    //   45: invokeinterface 504 1 0
    //   50: checkcast 268	android/content/pm/ApplicationInfo
    //   53: getfield 507	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   56: invokevirtual 510	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   59: pop
    //   60: goto -27 -> 33
    //   63: aload 4
    //   65: areturn
    //   66: aload_0
    //   67: iload_1
    //   68: invokevirtual 489	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   71: invokeinterface 495 1 0
    //   76: astore_0
    //   77: aload_0
    //   78: invokeinterface 500 1 0
    //   83: ifeq +123 -> 206
    //   86: aload 4
    //   88: aload_0
    //   89: invokeinterface 504 1 0
    //   94: checkcast 268	android/content/pm/ApplicationInfo
    //   97: getfield 507	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   100: invokevirtual 510	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   103: pop
    //   104: goto -27 -> 77
    //   107: astore_0
    //   108: ldc_w 512
    //   111: iconst_0
    //   112: anewarray 4	java/lang/Object
    //   115: invokestatic 389	trikita/log/Log:a	(Ljava/lang/Object;[Ljava/lang/Object;)Ltrikita/log/Log;
    //   118: pop
    //   119: aconst_null
    //   120: astore_0
    //   121: invokestatic 518	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   124: ldc_w 520
    //   127: invokevirtual 524	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   130: astore_3
    //   131: new 526	java/io/BufferedReader
    //   134: dup
    //   135: new 528	java/io/InputStreamReader
    //   138: dup
    //   139: aload_3
    //   140: invokevirtual 534	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   143: invokespecial 537	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   146: invokespecial 540	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   149: astore_2
    //   150: aload_2
    //   151: astore_0
    //   152: aload_2
    //   153: invokevirtual 543	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   156: astore 5
    //   158: aload 5
    //   160: ifnull +49 -> 209
    //   163: aload_2
    //   164: astore_0
    //   165: aload 4
    //   167: aload 5
    //   169: aload 5
    //   171: bipush 58
    //   173: invokevirtual 547	java/lang/String:indexOf	(I)I
    //   176: iconst_1
    //   177: iadd
    //   178: invokevirtual 551	java/lang/String:substring	(I)Ljava/lang/String;
    //   181: invokevirtual 510	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   184: pop
    //   185: goto -35 -> 150
    //   188: astore_3
    //   189: aload_2
    //   190: astore_0
    //   191: aload_3
    //   192: invokevirtual 552	java/lang/Exception:printStackTrace	()V
    //   195: aload_2
    //   196: ifnull +7 -> 203
    //   199: aload_2
    //   200: invokevirtual 555	java/io/BufferedReader:close	()V
    //   203: aload 4
    //   205: areturn
    //   206: aload 4
    //   208: areturn
    //   209: aload_2
    //   210: astore_0
    //   211: aload_3
    //   212: invokevirtual 558	java/lang/Process:waitFor	()I
    //   215: pop
    //   216: aload_2
    //   217: ifnull -14 -> 203
    //   220: aload_2
    //   221: invokevirtual 555	java/io/BufferedReader:close	()V
    //   224: goto -21 -> 203
    //   227: astore_0
    //   228: aload_0
    //   229: invokevirtual 559	java/io/IOException:printStackTrace	()V
    //   232: goto -29 -> 203
    //   235: astore_0
    //   236: aload_0
    //   237: invokevirtual 559	java/io/IOException:printStackTrace	()V
    //   240: goto -37 -> 203
    //   243: astore_3
    //   244: aload_0
    //   245: astore_2
    //   246: aload_3
    //   247: astore_0
    //   248: aload_2
    //   249: ifnull +7 -> 256
    //   252: aload_2
    //   253: invokevirtual 555	java/io/BufferedReader:close	()V
    //   256: aload_0
    //   257: athrow
    //   258: astore_2
    //   259: aload_2
    //   260: invokevirtual 559	java/io/IOException:printStackTrace	()V
    //   263: goto -7 -> 256
    //   266: astore_3
    //   267: aload_0
    //   268: astore_2
    //   269: aload_3
    //   270: astore_0
    //   271: goto -23 -> 248
    //   274: astore_3
    //   275: aconst_null
    //   276: astore_2
    //   277: goto -88 -> 189
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	280	0	paramContext	Context
    //   0	280	1	paramInt	int
    //   149	104	2	localObject1	Object
    //   258	2	2	localIOException	java.io.IOException
    //   268	9	2	localContext	Context
    //   130	10	3	localProcess	Process
    //   188	24	3	localException1	Exception
    //   243	4	3	localObject2	Object
    //   266	4	3	localObject3	Object
    //   274	1	3	localException2	Exception
    //   12	195	4	localArrayList	ArrayList
    //   156	14	5	str	String
    // Exception table:
    //   from	to	target	type
    //   66	77	107	java/lang/Exception
    //   77	104	107	java/lang/Exception
    //   152	158	188	java/lang/Exception
    //   165	185	188	java/lang/Exception
    //   211	216	188	java/lang/Exception
    //   220	224	227	java/io/IOException
    //   199	203	235	java/io/IOException
    //   121	150	243	finally
    //   252	256	258	java/io/IOException
    //   152	158	266	finally
    //   165	185	266	finally
    //   191	195	266	finally
    //   211	216	266	finally
    //   121	150	274	java/lang/Exception
  }
  
  public static int getRandomNumber(int paramInt)
  {
    return new Random().nextInt(paramInt) + 1;
  }
  
  public static Hub.DetailsItem getRealmCacheData(Context paramContext, String paramString)
  {
    return getRealmCacheData(paramContext, paramString, null);
  }
  
  public static Hub.DetailsItem getRealmCacheData(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject = null;
    Hub.DetailsItem localDetailsItem = new Hub.DetailsItem();
    Realm localRealm = Hub.getRealmInstance(paramContext);
    paramContext = localRealm.b(AppCache.class);
    RealmQuery localRealmQuery = localRealm.b(XposedCache.class);
    if (paramString1 != null)
    {
      paramContext = (AppCache)paramContext.a("uuid", paramString1).d();
      paramString2 = (XposedCache)localRealmQuery.a("uuid", paramString1).d();
      paramString1 = paramContext;
      paramContext = paramString2;
    }
    for (;;)
    {
      if (paramString1 != null) {
        paramContext = new Hub.DetailsItem(paramString1);
      }
      for (;;)
      {
        localRealm.close();
        return paramContext;
        if (paramString2 == null) {
          break label152;
        }
        paramString1 = (AppCache)paramContext.a("packageName", paramString2).d();
        paramContext = (XposedCache)localRealmQuery.a("packageName", paramString2).d();
        break;
        if (paramContext != null) {
          paramContext = new Hub.DetailsItem(paramContext);
        } else {
          paramContext = localDetailsItem;
        }
      }
      label152:
      paramString1 = null;
      paramContext = localObject;
    }
  }
  
  public static String getSavedSignature()
  {
    return Hub.getSharedPrefsHelper().getPref("settings_signature", XDALabsApp.getAppContext().getString(2131165643));
  }
  
  public static int getSetTheme()
  {
    return getSetTheme("theme", false);
  }
  
  public static int getSetTheme(String paramString, boolean paramBoolean)
  {
    int j = 1;
    int k = Hub.getSharedPrefsHelper().getInt(paramString, 0);
    int i = k;
    if (k != 0)
    {
      i = k;
      if (k != 1)
      {
        Log.a("Theme resource changed!  Switching to default", new Object[0]);
        Hub.getSharedPrefsHelper().setPref(paramString, 0);
        i = 0;
      }
    }
    if (i == 0) {
      i = j;
    }
    while (i != 0) {
      if (paramBoolean)
      {
        switch (getFontPref())
        {
        case 0: 
        default: 
          return 2131361987;
          i = 0;
          break;
        case 1: 
          return 2131361988;
        case 2: 
          return 2131361989;
        case 3: 
          return 2131361990;
        }
      }
      else
      {
        switch (getFontPref())
        {
        default: 
          return 2131361987;
        case 0: 
          return 2131361858;
        case 1: 
          return 2131361979;
        case 2: 
          return 2131361980;
        }
        return 2131361981;
      }
    }
    if (paramBoolean)
    {
      switch (getFontPref())
      {
      default: 
        return 2131361987;
      case 0: 
        return 2131361983;
      case 1: 
        return 2131361984;
      case 2: 
        return 2131361985;
      }
      return 2131361986;
    }
    switch (getFontPref())
    {
    default: 
      return 2131361987;
    case 0: 
      return 2131361857;
    case 1: 
      return 2131361976;
    case 2: 
      return 2131361977;
    }
    return 2131361978;
  }
  
  public static int getSetTheme(boolean paramBoolean)
  {
    return getSetTheme("theme", paramBoolean);
  }
  
  public static ArrayList<String> getSourceApkFromPackages(Context paramContext, List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = (String)paramList.next();
      try
      {
        localObject = paramContext.getPackageInfo((String)localObject, 1).applicationInfo;
        Log.a("packageName==%s | enabled==%s | metaData==%s | sourceDir==%s", new Object[] { ((ApplicationInfo)localObject).packageName, Boolean.valueOf(((ApplicationInfo)localObject).enabled), ((ApplicationInfo)localObject).metaData, ((ApplicationInfo)localObject).sourceDir });
        if (((ApplicationInfo)localObject).enabled) {
          localArrayList.add(((ApplicationInfo)localObject).sourceDir);
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
    return localArrayList;
  }
  
  public static int getStatusBarHeight(Context paramContext)
  {
    int i = 0;
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static <T> int getTabType(Class<T> paramClass)
  {
    if (AppListResponse.class.getSimpleName().equals(paramClass.getSimpleName())) {
      return 0;
    }
    if ([Lcom.xda.labs.entities.XposedResults.class.getSimpleName().equals(paramClass.getSimpleName())) {
      return 1;
    }
    return -1;
  }
  
  public static String getThemeFontPref(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(2130771973, localTypedValue, true);
    return localTypedValue.string.toString();
  }
  
  public static String getThreadRating(float paramFloat)
  {
    return new DecimalFormat("#.#").format(paramFloat);
  }
  
  public static Drawable getTintedDrawable(Drawable paramDrawable, int paramInt)
  {
    paramDrawable = DrawableCompat.g(paramDrawable);
    DrawableCompat.a(paramDrawable, paramInt);
    DrawableCompat.a(paramDrawable, PorterDuff.Mode.SRC_IN);
    return paramDrawable;
  }
  
  public static String getTotalRatings(int paramInt)
  {
    return new DecimalFormat("###,###,###,###,###,###,###").format(paramInt);
  }
  
  public static String getUserAgent()
  {
    Context localContext = XDALabsApp.getAppContext();
    return String.format("%s %s", new Object[] { localContext.getString(2131165773), getAppVersionName(localContext, localContext.getPackageName()) });
  }
  
  public static boolean hasThemeChanged(Context paramContext)
  {
    return (isDarkTheme(paramContext) != isDarkThemePref()) || (!getFontPrefString().equals(getThemeFontPref(paramContext)));
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString1, String paramString2)
  {
    return isAppInstalled(paramContext, paramString1, paramString2, -1);
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    boolean bool2 = false;
    Object localObject = paramContext.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString1, 1);
      int i = ((PackageInfo)localObject).versionCode;
      if (paramInt == ((PackageInfo)localObject).versionCode) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        Log.a("package==%s installed version==%s matches package version==%s true==%s", new Object[] { paramString1, Integer.valueOf(i), Integer.valueOf(paramInt), Boolean.valueOf(bool1) });
        if (paramInt != ((PackageInfo)localObject).versionCode)
        {
          bool1 = bool2;
          if (paramInt != -1) {}
        }
        else
        {
          boolean bool3 = matchFingerprint(paramContext, paramString1, paramString2);
          bool1 = bool2;
          if (bool3) {
            bool1 = true;
          }
        }
        return bool1;
      }
      return false;
    }
    catch (Exception paramContext) {}
  }
  
  public static boolean isDarkTheme(Context paramContext)
  {
    TypedValue localTypedValue = new TypedValue();
    paramContext.getTheme().resolveAttribute(2130772038, localTypedValue, true);
    return localTypedValue.string.equals("dark");
  }
  
  public static boolean isDarkThemePref()
  {
    boolean bool = false;
    if (Hub.getSharedPrefsHelper().getInt("theme", 0) == 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isPlay()
  {
    return "play".equals("play");
  }
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getApplicationInfo(paramString, 0).flags;
      if ((0x81 & i) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void launchEmail(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", paramString1, null));
    paramString1 = getAppVersionName(paramContext, paramString3);
    if (!paramString1.isEmpty()) {}
    for (paramString1 = "v" + paramString1;; paramString1 = "")
    {
      localIntent.putExtra("android.intent.extra.SUBJECT", String.format("%s %s", new Object[] { paramString2, paramString1 }));
      paramContext.startActivity(Intent.createChooser(localIntent, paramContext.getString(2131165261)));
      return;
    }
  }
  
  public static void launchInternalUrl(Context paramContext, String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent(paramContext, com.xda.labs.one.ui.MainActivity.class);
    localIntent.addFlags(4194304);
    localIntent.putExtra("parsed_url", paramString);
    localIntent.putExtra("internal_url", true);
    localIntent.putExtra("from_external", paramBoolean);
    if (paramBoolean)
    {
      ((Activity)paramContext).startActivityForResult(localIntent, 3);
      return;
    }
    paramContext.startActivity(localIntent);
  }
  
  public static void launchInternalUrlWithTransition(Activity paramActivity, String paramString)
  {
    launchInternalUrl(paramActivity, paramString, true);
    paramActivity.overridePendingTransition(2130968598, 2130968599);
  }
  
  public static void launchUrl(Context paramContext, String paramString)
  {
    launchUrl(paramContext, paramString, false);
  }
  
  public static void launchUrl(Context paramContext, String paramString, boolean paramBoolean)
  {
    if ((!paramString.startsWith("http://")) && (!paramString.startsWith("https://"))) {
      return;
    }
    if (paramBoolean) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
        return;
      }
      catch (Exception paramString)
      {
        Toast.makeText(paramContext, paramContext.getString(2131165395), 1).show();
        return;
      }
    }
    Intent localIntent = new Intent(paramContext, ChromeCustomTabsActivity.class);
    localIntent.putExtra("url", paramString);
    paramContext.startActivity(localIntent);
  }
  
  public static void launchUrlInBrowser(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      Intent localIntent = Intent.createChooser(paramString, paramContext.getString(2131165465));
      localIntent.setFlags(268435456);
      if (paramString.resolveActivity(paramContext.getPackageManager()) != null) {
        paramContext.startActivity(localIntent);
      }
      return;
    }
    catch (Exception paramContext)
    {
      Log.a("bad URL 2 [%s]", new Object[] { paramContext.toString() });
    }
  }
  
  public static void launchUserProfile(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return;
    }
    Intent localIntent = new Intent(paramContext, UserProfileActivity.class);
    localIntent.putExtra("user_id", paramString);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean matchFingerprint(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return true;
    }
    String str = getAppSignature(paramContext, paramString1);
    if (str == null) {
      return true;
    }
    Log.a("package [%s] fingerprint [%s] appSignature [%s]", new Object[] { paramString1, paramString2, str });
    paramContext = str;
    if (paramString2.length() == 4) {
      paramContext = StringUtils.a(str, 0, 4);
    }
    return paramString2.equals(paramContext);
  }
  
  public static void openKeyboard(Context paramContext, View paramView)
  {
    paramView.requestFocus();
    ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramView, 1);
  }
  
  public static float parsePrice(float paramFloat)
  {
    String str = String.valueOf(paramFloat).replace(",", ".");
    return Float.parseFloat(String.format(Locale.US, "%1.8f", new Object[] { Float.valueOf(str) }));
  }
  
  public static String parseSupplementIds(Integer[] paramArrayOfInteger, HashMap<Integer, AppBucket> paramHashMap)
  {
    Object localObject;
    if (paramHashMap == null)
    {
      localObject = "";
      return localObject;
    }
    int j = paramArrayOfInteger.length;
    String str = "";
    int i = 0;
    for (;;)
    {
      localObject = str;
      if (i >= j) {
        break;
      }
      localObject = paramArrayOfInteger[i];
      str = str + ((AppBucket)paramHashMap.get(localObject)).name + ", ";
      i += 1;
    }
  }
  
  public static long parseTimestamp(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return l * 1000L;
    }
    catch (Exception paramString) {}
    return System.currentTimeMillis();
  }
  
  public static int parseVolleyError(VolleyRequestFailed paramVolleyRequestFailed)
  {
    i = 408;
    try
    {
      if ((paramVolleyRequestFailed.error instanceof NoConnectionError)) {
        return 408;
      }
      boolean bool = paramVolleyRequestFailed.error instanceof TimeoutError;
      if (!bool) {
        try
        {
          i = paramVolleyRequestFailed.error.a.a;
          return i;
        }
        catch (Exception paramVolleyRequestFailed)
        {
          return 0;
        }
      }
      return i;
    }
    catch (Exception paramVolleyRequestFailed)
    {
      Log.a(paramVolleyRequestFailed, new Object[0]);
      i = 0;
    }
  }
  
  public static boolean populateCategories(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1;
    switch (paramInt)
    {
    default: 
      bool1 = false;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!Hub.getSharedPrefsHelper().exists("categories_json"));
            bool1 = bool2;
          } while (Hub.mAppCategories != null);
          localType = new TypeToken() {}.getType();
          Hub.mAppCategories = (HashMap)new Gson().a(Hub.getSharedPrefsHelper().getPref("categories_json"), localType);
          if (Hub.mAppCategories.size() == 0) {}
          for (bool1 = true;; bool1 = false) {
            return bool1;
          }
          bool1 = bool2;
        } while (!Hub.getSharedPrefsHelper().exists("xposed_categories_json"));
        bool1 = bool2;
      } while (Hub.mXposedCategories != null);
      Type localType = new TypeToken() {}.getType();
      Hub.mXposedCategories = (HashMap)new Gson().a(Hub.getSharedPrefsHelper().getPref("xposed_categories_json"), localType);
      bool1 = bool2;
    } while (Hub.mXposedCategories.size() == 0);
    return false;
  }
  
  public static boolean populatePermissions(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1;
    switch (paramInt)
    {
    default: 
      bool1 = false;
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (!Hub.getSharedPrefsHelper().exists("permissions_json"));
            bool1 = bool2;
          } while (Hub.mAppPermissions != null);
          localType = new TypeToken() {}.getType();
          Hub.mAppPermissions = (HashMap)new Gson().a(Hub.getSharedPrefsHelper().getPref("permissions_json"), localType);
          if (Hub.mAppPermissions.size() == 0) {}
          for (bool1 = true;; bool1 = false) {
            return bool1;
          }
          bool1 = bool2;
        } while (!Hub.getSharedPrefsHelper().exists("xposed_permissions_json"));
        bool1 = bool2;
      } while (Hub.mXposedPermissions != null);
      Type localType = new TypeToken() {}.getType();
      Hub.mXposedPermissions = (HashMap)new Gson().a(Hub.getSharedPrefsHelper().getPref("xposed_permissions_json"), localType);
      bool1 = bool2;
    } while (Hub.mXposedPermissions.size() == 0);
    return false;
  }
  
  public static float pxToDp(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    return paramInt / (paramContext.densityDpi / 160.0F);
  }
  
  public static int responseCount(Response paramResponse)
  {
    int i = 1;
    for (;;)
    {
      paramResponse = paramResponse.l();
      if (paramResponse == null) {
        break;
      }
      i += 1;
    }
    return i;
  }
  
  @TargetApi(21)
  public static void setFabColor(Context paramContext, View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      FabColorHelperL.setFabColor(paramContext, paramView, paramInt);
      return;
    }
    paramContext = (StateListDrawable)ContextCompat.a(paramContext, 2130837661).mutate();
    paramContext.setColorFilter(paramInt, PorterDuff.Mode.MULTIPLY);
    paramContext.invalidateSelf();
    paramView.setBackground(paramContext);
  }
  
  public static void setOverscrollColor(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return;
    }
    ContextCompat.a(paramContext, paramContext.getResources().getIdentifier("overscroll_edge", "drawable", "android")).setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
    ContextCompat.a(paramContext, paramContext.getResources().getIdentifier("overscroll_glow", "drawable", "android")).setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
  }
  
  public static void startDownloadIntent(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt)
  {
    Intent localIntent = new Intent(paramContext.getApplicationContext(), PushService.class);
    localIntent.setAction("xda_download_start");
    localIntent.putExtra("downloadUrl", paramString1);
    localIntent.putExtra("uuid", paramString2);
    localIntent.putExtra("appName", paramString3);
    localIntent.putExtra("versionCode", paramInt);
    paramContext.startService(localIntent);
  }
  
  public static String stripUnicodeObj(String paramString)
  {
    if ((paramString == null) || (paramString.isEmpty())) {
      return paramString;
    }
    return paramString.replace(65532, '\000');
  }
  
  public static void styleSnackbar(Context paramContext, Snackbar paramSnackbar)
  {
    paramSnackbar.b().setBackgroundColor(getColor(paramContext, 2131558405));
    ((TextView)paramSnackbar.b().findViewById(2131689828)).setTextColor(getColor(paramContext, 2131558536));
    paramSnackbar.e(getColor(paramContext, 2131558536));
  }
  
  public static void toggleForumsLauncherIcon(Context paramContext, boolean paramBoolean)
  {
    if (isPlay()) {
      return;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = new ComponentName(paramContext, ForumLauncher.class);
    if (paramBoolean) {}
    for (int i = 1;; i = 2)
    {
      localPackageManager.setComponentEnabledSetting(paramContext, i, 1);
      return;
    }
  }
  
  public static void triggerRebirth(Context paramContext)
  {
    String str = getCallingActivityName((Activity)paramContext);
    Intent localIntent = new Intent(paramContext, MainActivity.class);
    if (str.equals(com.xda.labs.one.ui.MainActivity.class.getCanonicalName())) {
      localIntent = new Intent(paramContext, com.xda.labs.one.ui.MainActivity.class);
    }
    localIntent.addFlags(335577088);
    ProcessPhoenix.a(paramContext, localIntent);
  }
  
  public static void uninstallApp(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.UNINSTALL_PACKAGE");
    localIntent.setData(Uri.parse("package:" + paramString));
    localIntent.putExtra("android.intent.extra.RETURN_RESULT", true);
    paramActivity.startActivityForResult(localIntent, 4224);
  }
  
  public static void updateAvatarFromCache()
  {
    Object localObject = Hub.getSharedPrefsHelper().getPref("userid") + ".png";
    try
    {
      localObject = new BufferedInputStream(new FileInputStream(new File(XDALabsApp.getAppContext().getCacheDir(), (String)localObject)));
      Hub.getEventBus().post(new NewAvatar(BitmapFactory.decodeStream((InputStream)localObject)));
      return;
    }
    catch (Exception localException) {}
  }
  
  public static void updateTeslaUnreadCount(String paramString, int paramInt)
  {
    if (!Hub.getSharedPrefsHelper().getBoolean("settings_teslaunread_enabled", true).booleanValue()) {
      return;
    }
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("tag", XDALabsApp.getAppContext().getPackageName() + paramString);
      localContentValues.put("count", Integer.valueOf(paramInt));
      XDALabsApp.getAppContext().getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"), localContentValues);
      return;
    }
    catch (Exception paramString) {}
  }
  
  public static boolean usingValidSignature()
  {
    return !Hub.getSharedPrefsHelper().getBoolean("settings_no_signature", true).booleanValue();
  }
  
  public static class GridSize
  {
    final int LANDSCAPE = 1;
    final int MAX_GRIDSIZE = 150;
    final int MIN_GRIDSIZE = 120;
    final int PORTRAIT = 0;
    float mDpHeight;
    float mDpWidth;
    int mHeight = 0;
    public int mLandscape;
    public int mMaxLandscape;
    public int mMaxPortrait;
    public int mPortrait;
    int mWidth = 0;
    
    public GridSize() {}
    
    private void getDimensions(Context paramContext)
    {
      int i = 0;
      DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      Display localDisplay = ((Activity)paramContext).getWindowManager().getDefaultDisplay();
      Object localObject;
      float f;
      int j;
      if (Build.VERSION.SDK_INT >= 17)
      {
        localObject = new DisplayMetrics();
        localDisplay.getRealMetrics((DisplayMetrics)localObject);
        this.mWidth = ((DisplayMetrics)localObject).widthPixels;
        this.mHeight = ((DisplayMetrics)localObject).heightPixels;
        f = localDisplayMetrics.density;
        if (paramContext.getResources().getConfiguration().orientation == 2) {
          i = 1;
        }
        if (i == 0) {
          break label206;
        }
        j = this.mWidth;
        label99:
        this.mDpHeight = (j / f);
        if (i == 0) {
          break label215;
        }
      }
      label206:
      label215:
      for (i = this.mHeight;; i = this.mWidth)
      {
        this.mDpWidth = (i / f);
        return;
        if (Build.VERSION.SDK_INT < 14) {
          break;
        }
        try
        {
          localObject = Display.class.getMethod("getRawHeight", new Class[0]);
          this.mWidth = ((Integer)Display.class.getMethod("getRawWidth", new Class[0]).invoke(localDisplay, new Object[0])).intValue();
          this.mHeight = ((Integer)((Method)localObject).invoke(localDisplay, new Object[0])).intValue();
        }
        catch (Exception localException) {}
        break;
        j = this.mHeight;
        break label99;
      }
    }
    
    private void getGridSize(float paramFloat, int paramInt)
    {
      int i = (int)Math.floor(paramFloat / 120.0F);
      int j = (int)Math.floor(paramFloat / 150.0F);
      switch (paramInt)
      {
      default: 
        return;
      case 0: 
        this.mMaxPortrait = i;
        this.mPortrait = j;
        return;
      }
      this.mMaxLandscape = i;
      this.mLandscape = j;
    }
    
    public GridSize calculateSize(Context paramContext)
    {
      getDimensions(paramContext);
      getGridSize(this.mDpWidth, 0);
      getGridSize(this.mDpHeight, 1);
      return this;
    }
    
    public int getHeight(Context paramContext)
    {
      if (this.mHeight == 0) {
        calculateSize(paramContext);
      }
      return this.mHeight;
    }
    
    public int getWidth(Context paramContext)
    {
      if (this.mWidth == 0) {
        calculateSize(paramContext);
      }
      return this.mWidth;
    }
  }
}
