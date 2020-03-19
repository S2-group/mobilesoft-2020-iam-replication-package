package emoji.keyboard.emoticonkeyboard.extras;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.android.inputmethod.latin.d.x;
import com.android.inputmethod.latin.kkuirearch.PremiumActivity;
import com.android.inputmethod.latin.kkuirearch.views.materialdialogs.c;
import com.android.inputmethod.latin.kkuirearch.views.materialdialogs.c.a;
import com.android.inputmethod.latin.kkuirearch.views.materialdialogs.c.b;
import com.android.inputmethod.latin.kkuirearch.views.materialdialogs.c.f;
import com.android.inputmethod.latin.setup.SetupActivity;
import com.google.android.gms.analytics.e.a;
import com.google.android.gms.analytics.g;
import com.myandroid.umeng.GoogleAnalyticsApp;
import com.myandroid.umeng.GoogleAnalyticsApp.a;
import com.umeng.analytics.MobclickAgent;
import emoji.keyboard.emoticonkeyboard.kbd.KeyboardSetupDialogActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class d
{
  public static final String[] a = { "zeroflte", "zeroflteacg", "zeroflteaio", "zeroflteatt", "zerofltebmc", "zerofltechn", "zerofltectc", "zerofltektt", "zerofltelgt", "zerofltelra", "zerofltemtr", "zeroflteskt", "zerofltespr", "zerofltetfnvzw", "zerofltetmo", "zeroflteusc", "zerofltevzw", "marinelteatt", "404sc", "scv31", "zerolte", "zerolteacg", "zerolteatt", "zeroltebmc", "zeroltechn", "zeroltektt", "zeroltelgt", "zeroltelra", "zerolteskt", "zeroltespr", "zeroltetmo", "zerolteusc", "zeroltevzw", "zenlte", "zenlteatt", "zenltebmc", "zenltechn", "zenltektt", "zenltekx", "zenltelgt", "zenlteskt", "zenltespr", "zenltetmo", "zenltevzw", "tre3caltelgt", "tre3calteskt", "tre3caltektt", "tre3g", "trelte", "treltektt", "treltelgt", "trelteskt", "trhplte", "trlte", "trlteatt", "trltecan", "trltechn", "trltechnzh", "trltespr", "trltetmo", "trlteusc", "trltevzw", "noblelte", "nobleltezc", "noblelteatt", "nobleltelra", "noblelteacg", "nobleltebmc", "nobleltechn", "nobleltecmcc", "nobleltehk", "nobleltektt", "nobleltelgt", "noblelteskt", "nobleltespr", "nobleltetmo", "noblelteusc", "nobleltevzw" };
  private static final List<String> b = new ArrayList();
  
  static
  {
    List localList = Arrays.asList(a);
    b.addAll(localList);
  }
  
  public static Boolean a(Activity paramActivity, String paramString)
  {
    paramActivity = MobclickAgent.getConfigParams(paramActivity, paramString);
    Log.d("Utility", "needShowADS" + paramActivity);
    if (paramActivity.equals("1")) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static void a(Activity paramActivity, final Boolean paramBoolean)
  {
    if (!SetupActivity.b(paramActivity, (InputMethodManager)paramActivity.getSystemService("input_method")))
    {
      paramBoolean = new Intent(paramActivity, KeyboardSetupDialogActivity.class);
      paramBoolean.putExtra("startPreviewComponent", "Preview");
      paramActivity.startActivity(paramBoolean);
      return;
    }
    final InputMethodManager localInputMethodManager = (InputMethodManager)paramActivity.getSystemService("input_method");
    paramActivity = new c.a(paramActivity).a(2131296784).e(2130968824).c(2131296751).a().a(new c.b()
    {
      public final void onNegative(c paramAnonymousC)
      {
        if (paramBoolean.booleanValue()) {
          this.a.finish();
        }
      }
      
      public final void onPositive(c paramAnonymousC)
      {
        paramAnonymousC = this.a.getCurrentFocus();
        this.a.getWindow().setSoftInputMode(3);
        localInputMethodManager.hideSoftInputFromWindow(paramAnonymousC.getWindowToken(), 0);
        if (paramBoolean.booleanValue()) {
          this.a.finish();
        }
      }
    }).d();
    paramActivity.getWindow().getAttributes().gravity = 49;
    paramActivity.show();
    localInputMethodManager.toggleSoftInput(0, 2);
    paramBoolean = (EditText)paramActivity.a;
    paramBoolean.postDelayed(new Runnable()
    {
      public final void run()
      {
        this.a.getWindow().setSoftInputMode(4);
        localInputMethodManager.showSoftInput(paramBoolean, 2);
      }
    }, 100L);
  }
  
  public static void a(Application paramApplication, String paramString)
  {
    paramApplication = ((GoogleAnalyticsApp)paramApplication).a(GoogleAnalyticsApp.a.a);
    paramApplication.a("&cd", paramString);
    paramApplication.a(new e.a().a());
  }
  
  public static void a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0).edit();
    paramContext.putLong(paramString, System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static boolean a(Context paramContext)
  {
    boolean bool2 = false;
    String str1 = Build.MANUFACTURER;
    String str2 = Build.PRODUCT;
    boolean bool1 = bool2;
    if (!TextUtils.isEmpty(str1))
    {
      bool1 = bool2;
      if (!TextUtils.isEmpty(str2))
      {
        bool1 = bool2;
        if (str1.toLowerCase().equals("samsung"))
        {
          bool1 = bool2;
          if (Build.VERSION.SDK_INT == 22)
          {
            bool1 = bool2;
            if (x.a(paramContext.getResources()) == 1080)
            {
              bool1 = bool2;
              if (b.contains(str2.toLowerCase())) {
                bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public static long b(Context paramContext, String paramString)
  {
    return System.currentTimeMillis() - paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getLong(paramString, 0L);
  }
  
  public static void b(Application paramApplication, String paramString)
  {
    paramApplication = ((GoogleAnalyticsApp)paramApplication).a(GoogleAnalyticsApp.a.b);
    paramApplication.a("&cd", paramString);
    paramApplication.a(new e.a().a());
  }
  
  public static void b(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramContext.getPackageName(), 4);
    if (localSharedPreferences.getBoolean("need_to_show_special_update_hint", true))
    {
      String str = paramContext.getResources().getString(2131297185);
      new c.a(paramContext).a(paramContext.getString(2131297186)).b(str).f(2131689504).b().c().c(2131296751).a(new c.f()
      {
        public final void onPositive(c paramAnonymousC) {}
      }).d().show();
      localSharedPreferences.edit().putBoolean("need_to_show_special_update_hint", false).apply();
    }
  }
  
  public static int c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo(paramString, 1).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  /* Error */
  public static void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokevirtual 381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   5: iconst_4
    //   6: invokevirtual 385	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   9: astore 4
    //   11: aload 4
    //   13: ldc_w 513
    //   16: iconst_1
    //   17: invokeinterface 458 3 0
    //   22: ifeq +217 -> 239
    //   25: aload_0
    //   26: invokestatic 517	com/myandroid/promotion/b/b:b	(Landroid/content/Context;)Z
    //   29: ifne +210 -> 239
    //   32: aconst_null
    //   33: astore_2
    //   34: aload_0
    //   35: invokevirtual 495	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   38: aload_0
    //   39: invokevirtual 381	android/content/Context:getPackageName	()Ljava/lang/String;
    //   42: sipush 16384
    //   45: invokevirtual 501	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   48: astore_1
    //   49: aload_1
    //   50: astore_2
    //   51: new 519	java/io/BufferedReader
    //   54: dup
    //   55: new 521	java/io/InputStreamReader
    //   58: dup
    //   59: aload_0
    //   60: invokevirtual 437	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   63: invokevirtual 525	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   66: ldc_w 527
    //   69: invokevirtual 533	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   72: invokespecial 536	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   75: invokespecial 539	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   78: astore 5
    //   80: ldc_w 541
    //   83: astore_1
    //   84: aload 5
    //   86: invokevirtual 544	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   89: astore 6
    //   91: aload_1
    //   92: astore_3
    //   93: aload 6
    //   95: ifnull +45 -> 140
    //   98: new 214	java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial 545	java/lang/StringBuilder:<init>	()V
    //   105: aload_1
    //   106: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload 6
    //   111: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: ldc_w 547
    //   117: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   123: astore_3
    //   124: aload_3
    //   125: astore_1
    //   126: goto -42 -> 84
    //   129: astore_3
    //   130: ldc_w 541
    //   133: astore_1
    //   134: aload_3
    //   135: invokevirtual 548	java/lang/Exception:printStackTrace	()V
    //   138: aload_1
    //   139: astore_3
    //   140: new 282	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a
    //   143: dup
    //   144: aload_0
    //   145: invokespecial 285	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:<init>	(Landroid/content/Context;)V
    //   148: aload_0
    //   149: ldc_w 549
    //   152: iconst_1
    //   153: anewarray 4	java/lang/Object
    //   156: dup
    //   157: iconst_0
    //   158: aload_2
    //   159: getfield 552	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   162: aastore
    //   163: invokevirtual 555	android/content/Context:getString	(I[Ljava/lang/Object;)Ljava/lang/String;
    //   166: invokevirtual 470	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:a	(Ljava/lang/CharSequence;)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   169: aload_3
    //   170: invokevirtual 472	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:b	(Ljava/lang/CharSequence;)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   173: ldc_w 473
    //   176: invokevirtual 476	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:f	(I)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   179: invokevirtual 478	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:b	()Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   182: invokevirtual 480	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:c	()Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   185: astore_1
    //   186: aload_0
    //   187: invokestatic 559	com/myandroid/billing/a:a	(Landroid/content/Context;)Z
    //   190: ifeq +50 -> 240
    //   193: aload_1
    //   194: ldc_w 294
    //   197: invokevirtual 297	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:c	(I)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   200: new 8	emoji/keyboard/emoticonkeyboard/extras/d$2
    //   203: dup
    //   204: invokespecial 560	emoji/keyboard/emoticonkeyboard/extras/d$2:<init>	()V
    //   207: invokevirtual 306	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:a	(Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$f;)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   210: pop
    //   211: aload_1
    //   212: invokevirtual 309	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:d	()Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c;
    //   215: invokevirtual 330	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c:show	()V
    //   218: aload 4
    //   220: invokeinterface 391 1 0
    //   225: ldc_w 513
    //   228: iconst_0
    //   229: invokeinterface 485 3 0
    //   234: invokeinterface 488 1 0
    //   239: return
    //   240: aload_1
    //   241: ldc_w 294
    //   244: invokevirtual 297	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:c	(I)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   247: ldc_w 561
    //   250: invokevirtual 563	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:d	(I)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   253: new 10	emoji/keyboard/emoticonkeyboard/extras/d$3
    //   256: dup
    //   257: aload_0
    //   258: invokespecial 564	emoji/keyboard/emoticonkeyboard/extras/d$3:<init>	(Landroid/content/Context;)V
    //   261: invokevirtual 306	com/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a:a	(Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$f;)Lcom/android/inputmethod/latin/kkuirearch/views/materialdialogs/c$a;
    //   264: pop
    //   265: goto -54 -> 211
    //   268: astore_3
    //   269: goto -135 -> 134
    //   272: astore_1
    //   273: goto -222 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	paramContext	Context
    //   48	193	1	localObject1	Object
    //   272	1	1	localNameNotFoundException	PackageManager.NameNotFoundException
    //   33	126	2	localObject2	Object
    //   92	33	3	localObject3	Object
    //   129	6	3	localException1	Exception
    //   139	31	3	localObject4	Object
    //   268	1	3	localException2	Exception
    //   9	210	4	localSharedPreferences	SharedPreferences
    //   78	7	5	localBufferedReader	java.io.BufferedReader
    //   89	21	6	str	String
    // Exception table:
    //   from	to	target	type
    //   51	80	129	java/lang/Exception
    //   84	91	268	java/lang/Exception
    //   98	124	268	java/lang/Exception
    //   34	49	272	android/content/pm/PackageManager$NameNotFoundException
  }
  
  public static void d(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(paramContext.getPackageName(), 4);
    String str = localSharedPreferences.getString("SHARED_PREFERENCES_KEY_OLD_VERSION_CODE", "no_install");
    Object localObject = null;
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 16384);
      localObject = localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      label106:
      int i;
      for (;;) {}
    }
    if (localObject == null)
    {
      localObject = "";
      if (!str.equals("no_install")) {
        break label196;
      }
      localSharedPreferences.edit().putBoolean("this_is_new_user", true).apply();
      localSharedPreferences.edit().putBoolean("need_to_show_update_summary", false).apply();
      paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
      if (paramContext.getInt("pref_cute_initial_version", 0) == 0) {
        if (!"no_install".equals(str)) {
          break label271;
        }
      }
    }
    label196:
    label271:
    for (i = Integer.decode((String)localObject).intValue();; i = Integer.decode(str).intValue())
    {
      paramContext.edit().putInt("pref_cute_initial_version", i).apply();
      localSharedPreferences.edit().putString("SHARED_PREFERENCES_KEY_OLD_VERSION_CODE", (String)localObject).apply();
      return;
      localObject = String.valueOf(((PackageInfo)localObject).versionCode);
      break;
      if (str.equals(localObject)) {
        break label106;
      }
      localSharedPreferences.edit().putBoolean("this_is_new_user", false).apply();
      localSharedPreferences.edit().putBoolean("need_to_show_update_summary", true).apply();
      localSharedPreferences.edit().putBoolean("first_launch_after_upgrading", true).apply();
      break label106;
    }
  }
  
  public static boolean e(Context paramContext)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 4).getBoolean("this_is_new_user", true);
  }
  
  public static boolean f(Context paramContext)
  {
    return paramContext.getSharedPreferences(paramContext.getPackageName(), 4).getBoolean("first_launch_after_upgrading", false);
  }
  
  public static void g(Context paramContext)
  {
    paramContext.getSharedPreferences(paramContext.getPackageName(), 4).edit().putBoolean("first_launch_after_upgrading", false).apply();
  }
  
  public static void h(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.a);
        Iterator localIterator = this.a.getPackageManager().getInstalledPackages(0).iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          int i;
          if (localPackageInfo.packageName.contains("com.emojifamily.emoji.keyboard.font.twitteremoji"))
          {
            i = d.c(this.a, "com.emojifamily.emoji.keyboard.font.twitteremoji");
            localSharedPreferences.edit().putInt("kbd_emoji_twitter_style_version", i).apply();
          }
          if (localPackageInfo.packageName.contains("com.emojifamily.emoji.keyboard.style.coloremoji"))
          {
            i = d.c(this.a, "com.emojifamily.emoji.keyboard.style.coloremoji");
            localSharedPreferences.edit().putInt("kbd_emoji_one_style_version", i).apply();
          }
        }
      }
    }).start();
  }
}
