package com.allfootball.news.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore.Images.Media;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.alibaba.json.JSON;
import com.alibaba.json.TypeReference;
import com.alibaba.json.parser.Feature;
import com.allfootball.news.BaseApplication;
import com.allfootball.news.a.b;
import com.allfootball.news.businessbase.R.color;
import com.allfootball.news.businessbase.R.drawable;
import com.allfootball.news.businessbase.R.string;
import com.allfootball.news.common.activity.DownloadActivity;
import com.allfootball.news.entity.AttachmentEntity;
import com.allfootball.news.entity.CommentEntity;
import com.allfootball.news.entity.ErrorEntity;
import com.allfootball.news.entity.SwitchLanguageEntity;
import com.allfootball.news.entity.SwitchLanguageItemEntity;
import com.allfootball.news.entity.UserEntity;
import com.allfootball.news.model.DownloadModel;
import com.allfootball.news.model.ThumbModel;
import com.allfootball.news.model.UserNotificationModel;
import com.allfootball.news.model.db.TabsDbModel;
import com.allfootball.news.model.gson.GlobalGsonModel;
import com.allfootball.news.model.gson.MenusGsonModel;
import com.allfootball.news.model.gson.RegionGsonModel;
import com.allfootball.news.model.gson.TabsGsonModel;
import com.allfootball.news.service.AppJobService;
import com.allfootball.news.service.AppService;
import com.allfootball.news.view.CircularProgressDrawable;
import com.allfootball.news.view.CircularProgressDrawable.Builder;
import com.allfootball.news.view.RemindDialog;
import com.allfootballapp.news.core.scheme.t.a;
import com.android.volley2.NetworkResponse;
import com.android.volley2.error.VolleyError;
import com.google.android.gms.common.GoogleApiAvailability;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.umeng.analytics.MobclickAgent;
import com.urbanairship.UAirship;
import com.urbanairship.push.i;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.Request.Builder;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

@NBSInstrumented
public class e
{
  public static String[] a = { "jpg", "png" };
  
  public static void A(Context paramContext)
  {
    for (;;)
    {
      try
      {
        localObject1 = aa.a(paramContext);
        if (((String)localObject1).startsWith("es"))
        {
          localObject1 = "menu_es.cache";
        }
        else if (((String)localObject1).startsWith("fr"))
        {
          localObject1 = "menu_fr.cache";
        }
        else
        {
          if ((((String)localObject1).startsWith("in")) || (((String)localObject1).startsWith("id"))) {
            break label548;
          }
          if (((String)localObject1).startsWith("de"))
          {
            localObject1 = "menu_de.cache";
          }
          else if (((String)localObject1).startsWith("pt"))
          {
            localObject1 = "menu_pt.cache";
          }
          else if (((String)localObject1).startsWith("th"))
          {
            localObject1 = "menu_th.cache";
          }
          else if (((String)localObject1).startsWith("ja"))
          {
            localObject1 = "menu_ja.cache";
          }
          else if (((String)localObject1).startsWith("it"))
          {
            localObject1 = "menu_it.cache";
          }
          else if (((String)localObject1).startsWith("ru"))
          {
            localObject1 = "menu_ru.cache";
          }
          else if (((String)localObject1).startsWith("ar"))
          {
            localObject1 = "menu_ar.cache";
          }
          else
          {
            if (!((String)localObject1).startsWith("vi")) {
              break label541;
            }
            localObject1 = "menu_vi.cache";
          }
        }
        localObject1 = (GlobalGsonModel)JSON.parseObject(f(paramContext, (String)localObject1).toString(), GlobalGsonModel.class);
        if (localObject1 == null) {
          return;
        }
        MenusGsonModel localMenusGsonModel = ((GlobalGsonModel)localObject1).menus;
        if (localMenusGsonModel != null)
        {
          Object localObject2 = localMenusGsonModel.getNews();
          int j = 0;
          int k;
          int i;
          if ((localObject2 != null) && (!localMenusGsonModel.getNews().isEmpty()))
          {
            localObject2 = new ArrayList();
            k = localMenusGsonModel.getNews().size();
            i = 0;
            if (i < k)
            {
              ((List)localObject2).add(new TabsDbModel((TabsGsonModel)localMenusGsonModel.getNews().get(i), i));
              i += 1;
              continue;
            }
            b.i = (List)localObject2;
          }
          if ((localMenusGsonModel.getMatch_tab() != null) && (!localMenusGsonModel.getMatch_tab().isEmpty()))
          {
            localObject2 = new ArrayList();
            k = localMenusGsonModel.getMatch_tab().size();
            i = j;
            if (i < k)
            {
              ((List)localObject2).add(new TabsDbModel((TabsGsonModel)localMenusGsonModel.getMatch_tab().get(i), i));
              i += 1;
              continue;
            }
            b.l = (List)localObject2;
          }
          if ((localMenusGsonModel.getRanking_new() != null) && (!localMenusGsonModel.getRanking_new().isEmpty()))
          {
            com.allfootball.news.db.a.a(paramContext, localMenusGsonModel.ranking_new);
            b.k = com.allfootball.news.db.a.e(paramContext);
          }
          if ((localMenusGsonModel.getVideo() != null) && (!localMenusGsonModel.getVideo().isEmpty()))
          {
            com.allfootball.news.db.a.c(paramContext, localMenusGsonModel.video);
            b.j = com.allfootball.news.db.a.b(paramContext);
          }
        }
        if (((GlobalGsonModel)localObject1).modules != null) {
          d.v(paramContext, JSON.toJSONString(((GlobalGsonModel)localObject1).modules));
        }
        b.V = true;
        return;
      }
      catch (IllegalArgumentException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      catch (com.alibaba.json.JSONException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      catch (IOException paramContext)
      {
        paramContext.printStackTrace();
        return;
      }
      label541:
      Object localObject1 = "menu_en.cache";
      continue;
      label548:
      localObject1 = "menu_in.cache";
    }
  }
  
  public static List<RegionGsonModel> B(Context paramContext)
  {
    return com.allfootball.news.db.a.n(paramContext);
  }
  
  public static int C(Context paramContext)
  {
    paramContext = paramContext.getResources();
    int i = paramContext.getIdentifier("status_bar_height", "dimen", "android");
    if (i > 0) {
      return paramContext.getDimensionPixelSize(i);
    }
    return (int)Math.ceil(paramContext.getDisplayMetrics().density * 25.0F);
  }
  
  public static CircularProgressDrawable D(Context paramContext)
  {
    paramContext = new CircularProgressDrawable.Builder().setRingWidth(a(paramContext, 6.0F)).setOutlineColor(872415231).setRingColor(paramContext.getResources().getColor(17170432)).setCenterColor(paramContext.getResources().getColor(R.color.transparent)).create();
    paramContext.setIndeterminate(false);
    paramContext.setProgress(0.0F);
    return paramContext;
  }
  
  public static int E(Context paramContext)
  {
    return 186;
  }
  
  public static int F(Context paramContext)
  {
    try
    {
      Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
      paramContext = ((ConnectivityManager)localObject).getNetworkInfo(0);
      localObject = ((ConnectivityManager)localObject).getNetworkInfo(1);
      if (localObject != null) {
        if (((NetworkInfo)localObject).isConnected()) {
          break label57;
        }
      }
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return 1;
        }
      }
      return 2;
    }
    catch (Exception paramContext)
    {
      return 0;
    }
    label57:
    return 0;
  }
  
  public static int G(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }
  
  public static Intent H(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("fb://page/1195121897229652"));
    if (paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() == 0) {
      localIntent.setData(Uri.parse("https://www.facebook.com/allfootballapp"));
    }
    return localIntent;
  }
  
  public static Intent I(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("twitter://user?screen_name=allfootballapp"));
    if (paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() == 0) {
      localIntent.setData(Uri.parse("https://twitter.com/allfootballapp"));
    }
    return localIntent;
  }
  
  public static void J(Context paramContext)
  {
    String str = d.aP(paramContext);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(com.allfootball.news.a.d.f);
    ((StringBuilder)localObject1).append("/downloadCoin");
    Object localObject2 = ((StringBuilder)localObject1).toString();
    localObject1 = localObject2;
    if (!TextUtils.isEmpty(str))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("?invite_code=");
      ((StringBuilder)localObject1).append(str);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    localObject1 = w((String)localObject1);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(paramContext.getString(R.string.share_app_to_friend));
    ((StringBuilder)localObject2).append((String)localObject1);
    h(paramContext, ((StringBuilder)localObject2).toString());
  }
  
  public static boolean K(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.a();
    int i;
    if (localGoogleApiAvailability != null) {
      i = localGoogleApiAvailability.a(paramContext);
    } else {
      i = com.urbanairship.google.a.a(paramContext);
    }
    return i == 0;
  }
  
  public static String L(Context paramContext)
  {
    return Settings.System.getString(paramContext.getContentResolver(), "android_id");
  }
  
  public static boolean M(Context paramContext)
  {
    return DateFormat.is24HourFormat(paramContext);
  }
  
  public static void N(Context paramContext)
  {
    CookieSyncManager.createInstance(paramContext);
    CookieSyncManager.getInstance().startSync();
    CookieManager.getInstance().removeAllCookie();
  }
  
  public static void O(Context paramContext)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      Object localObject;
      if (!b.b.contains("-"))
      {
        localJSONObject.put("user_language", b.b);
      }
      else
      {
        int i = b.b.indexOf("-");
        localObject = b.b.substring(0, i);
        String str = b.b.substring(i + 1, b.b.length());
        localJSONObject.put("user_language", localObject);
        localJSONObject.put("user_country", str);
      }
      if (Locale.getDefault() != null)
      {
        localObject = BaseApplication.e();
        if (localObject != null)
        {
          localJSONObject.put("sys_language", ((Locale)localObject).getLanguage());
          localJSONObject.put("sys_country", ((Locale)localObject).getCountry());
        }
      }
      SensorsDataAPI.sharedInstance(paramContext).track("sys_user_locale", localJSONObject);
      return;
    }
    catch (org.json.JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static int P(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return 2;
    }
    try
    {
      AppOpsManager localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
      Object localObject = paramContext.getApplicationInfo();
      paramContext = paramContext.getApplicationContext().getPackageName();
      int i = ((ApplicationInfo)localObject).uid;
      localObject = Class.forName(AppOpsManager.class.getName());
      i = ((Integer)((Class)localObject).getMethod("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(((Integer)((Class)localObject).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), paramContext })).intValue();
      if (i == 0) {
        return 1;
      }
      return 0;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 2;
  }
  
  public static String Q(Context paramContext)
  {
    String str = d.aG(paramContext);
    Object localObject = str;
    if (TextUtils.isEmpty(str))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.BRAND);
      ((StringBuilder)localObject).append(" - ");
      ((StringBuilder)localObject).append(Build.MODEL);
      localObject = ((StringBuilder)localObject).toString();
      d.R(paramContext, (String)localObject);
    }
    return localObject;
  }
  
  public static boolean R(Context paramContext)
  {
    if (TextUtils.isEmpty(b.W)) {
      b.W = d.aX(paramContext);
    }
    return "true".equals(b.W);
  }
  
  public static boolean S(Context paramContext)
  {
    Object localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setData(Uri.parse("http://play.google.com/store/apps/details?id=com.allfootball.news"));
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 131072);
    if (paramContext != null)
    {
      if (paramContext.size() == 0) {
        return false;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        localObject = (ResolveInfo)paramContext.next();
        if ((localObject != null) && (((ResolveInfo)localObject).activityInfo != null) && (!TextUtils.isEmpty(((ResolveInfo)localObject).activityInfo.packageName)) && (((ResolveInfo)localObject).activityInfo.packageName.equals("com.android.vending"))) {
          return true;
        }
      }
      return false;
    }
    return false;
  }
  
  public static int T(Context paramContext)
  {
    if (b.X <= 0) {
      b.X = d.bc(paramContext);
    }
    return b.X;
  }
  
  /* Error */
  public static int U(Context arg0)
  {
    // Byte code:
    //   0: getstatic 539	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 21
    //   5: if_icmplt +298 -> 303
    //   8: aload_0
    //   9: invokestatic 674	com/allfootball/news/util/e:K	(Landroid/content/Context;)Z
    //   12: ifeq +291 -> 303
    //   15: aload_0
    //   16: ldc_w 676
    //   19: invokevirtual 311	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   22: checkcast 678	android/app/job/JobScheduler
    //   25: astore 5
    //   27: aload 5
    //   29: ifnonnull +5 -> 34
    //   32: iconst_0
    //   33: ireturn
    //   34: aload 5
    //   36: invokevirtual 681	android/app/job/JobScheduler:getAllPendingJobs	()Ljava/util/List;
    //   39: invokeinterface 642 1 0
    //   44: astore 6
    //   46: aload 6
    //   48: invokeinterface 647 1 0
    //   53: ifeq +78 -> 131
    //   56: aload 6
    //   58: invokeinterface 651 1 0
    //   63: checkcast 683	android/app/job/JobInfo
    //   66: astore 4
    //   68: aload 4
    //   70: ifnonnull +6 -> 76
    //   73: goto -27 -> 46
    //   76: ldc_w 685
    //   79: invokevirtual 564	java/lang/Class:getName	()Ljava/lang/String;
    //   82: aload 4
    //   84: invokevirtual 689	android/app/job/JobInfo:getService	()Landroid/content/ComponentName;
    //   87: invokevirtual 694	android/content/ComponentName:getClassName	()Ljava/lang/String;
    //   90: invokevirtual 634	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   93: ifeq -47 -> 46
    //   96: new 380	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   103: astore_0
    //   104: aload_0
    //   105: ldc_w 696
    //   108: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_0
    //   113: aload 4
    //   115: invokevirtual 699	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: ldc_w 701
    //   122: aload_0
    //   123: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: invokestatic 706	com/allfootball/news/util/an:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   129: iconst_0
    //   130: ireturn
    //   131: aload 5
    //   133: invokevirtual 681	android/app/job/JobScheduler:getAllPendingJobs	()Ljava/util/List;
    //   136: invokeinterface 122 1 0
    //   141: istore_3
    //   142: iload_3
    //   143: bipush 100
    //   145: if_icmplt +5 -> 150
    //   148: iconst_0
    //   149: ireturn
    //   150: aload_0
    //   151: invokestatic 709	com/allfootball/news/util/d:bj	(Landroid/content/Context;)I
    //   154: istore_2
    //   155: iload_2
    //   156: istore_1
    //   157: iload_2
    //   158: iload_3
    //   159: iadd
    //   160: bipush 99
    //   162: if_icmple +5 -> 167
    //   165: iconst_0
    //   166: istore_1
    //   167: iload_1
    //   168: iconst_1
    //   169: iadd
    //   170: istore_2
    //   171: aload 5
    //   173: new 711	android/app/job/JobInfo$Builder
    //   176: dup
    //   177: iload_1
    //   178: sipush 1000
    //   181: iadd
    //   182: new 691	android/content/ComponentName
    //   185: dup
    //   186: aload_0
    //   187: invokevirtual 554	android/content/Context:getPackageName	()Ljava/lang/String;
    //   190: ldc_w 685
    //   193: invokevirtual 564	java/lang/Class:getName	()Ljava/lang/String;
    //   196: invokespecial 713	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   199: invokespecial 716	android/app/job/JobInfo$Builder:<init>	(ILandroid/content/ComponentName;)V
    //   202: iconst_1
    //   203: invokevirtual 720	android/app/job/JobInfo$Builder:setRequiredNetworkType	(I)Landroid/app/job/JobInfo$Builder;
    //   206: ldc2_w 721
    //   209: invokevirtual 726	android/app/job/JobInfo$Builder:setPeriodic	(J)Landroid/app/job/JobInfo$Builder;
    //   212: invokevirtual 730	android/app/job/JobInfo$Builder:build	()Landroid/app/job/JobInfo;
    //   215: invokevirtual 734	android/app/job/JobScheduler:schedule	(Landroid/app/job/JobInfo;)I
    //   218: istore_1
    //   219: goto +16 -> 235
    //   222: astore 4
    //   224: goto +4 -> 228
    //   227: astore_0
    //   228: aload 4
    //   230: invokevirtual 202	java/lang/IllegalArgumentException:printStackTrace	()V
    //   233: iconst_0
    //   234: istore_1
    //   235: aload_0
    //   236: iload_2
    //   237: invokestatic 737	com/allfootball/news/util/d:E	(Landroid/content/Context;I)Z
    //   240: pop
    //   241: new 380	java/lang/StringBuilder
    //   244: dup
    //   245: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   248: astore_0
    //   249: aload_0
    //   250: ldc_w 739
    //   253: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: pop
    //   257: aload_0
    //   258: iload_1
    //   259: invokevirtual 742	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: aload_0
    //   264: ldc_w 744
    //   267: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   270: pop
    //   271: aload_0
    //   272: iload_3
    //   273: invokevirtual 742	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   276: pop
    //   277: aload_0
    //   278: ldc_w 746
    //   281: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: pop
    //   285: aload_0
    //   286: iload_2
    //   287: invokevirtual 742	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   290: pop
    //   291: ldc_w 701
    //   294: aload_0
    //   295: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   298: invokestatic 706	com/allfootball/news/util/an:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   301: iload_1
    //   302: ireturn
    //   303: iconst_0
    //   304: ireturn
    // Exception table:
    //   from	to	target	type
    //   171	219	222	java/lang/IllegalArgumentException
  }
  
  public static boolean V(Context paramContext)
  {
    return false;
  }
  
  public static boolean W(Context paramContext)
  {
    Object localObject1 = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (localObject1 == null) {
      return true;
    }
    paramContext = paramContext.getPackageName();
    int i = android.os.Process.myPid();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Main:");
    ((StringBuilder)localObject2).append(paramContext);
    an.a("AppUtils", ((StringBuilder)localObject2).toString());
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
      if ((localObject2 != null) && (((ActivityManager.RunningAppProcessInfo)localObject2).pid == i) && (paramContext.equals(((ActivityManager.RunningAppProcessInfo)localObject2).processName)))
      {
        an.a("AppUtils", ((ActivityManager.RunningAppProcessInfo)localObject2).processName);
        return true;
      }
    }
    return false;
  }
  
  public static float a(List<String> paramList)
  {
    if ((paramList != null) && (paramList.size() != 0))
    {
      long l1 = 0L;
      int i = 0;
      while (i < paramList.size())
      {
        File localFile = new File((String)paramList.get(i));
        long l2 = l1;
        try
        {
          if (localFile.exists())
          {
            l2 = localFile.length();
            l2 = l1 + l2;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          l2 = l1;
        }
        i += 1;
        l1 = l2;
      }
      return (float)l1 / 1024.0F / 1024.0F;
    }
    return 0.0F;
  }
  
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  /* Error */
  public static int a(Context arg0, String arg1, String arg2, String arg3, String arg4, long arg5, long arg7)
  {
    // Byte code:
    //   0: getstatic 539	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 21
    //   5: if_icmplt +263 -> 268
    //   8: aload_0
    //   9: ldc_w 676
    //   12: invokevirtual 311	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   15: checkcast 678	android/app/job/JobScheduler
    //   18: astore 12
    //   20: aload 12
    //   22: ifnonnull +5 -> 27
    //   25: iconst_0
    //   26: ireturn
    //   27: aload 12
    //   29: invokevirtual 681	android/app/job/JobScheduler:getAllPendingJobs	()Ljava/util/List;
    //   32: invokeinterface 122 1 0
    //   37: istore 11
    //   39: iload 11
    //   41: bipush 100
    //   43: if_icmplt +5 -> 48
    //   46: iconst_0
    //   47: ireturn
    //   48: aload_0
    //   49: invokestatic 709	com/allfootball/news/util/d:bj	(Landroid/content/Context;)I
    //   52: istore 10
    //   54: iload 10
    //   56: istore 9
    //   58: iload 10
    //   60: iload 11
    //   62: iadd
    //   63: bipush 99
    //   65: if_icmple +6 -> 71
    //   68: iconst_0
    //   69: istore 9
    //   71: iload 9
    //   73: iconst_1
    //   74: iadd
    //   75: istore 10
    //   77: aload 12
    //   79: new 711	android/app/job/JobInfo$Builder
    //   82: dup
    //   83: iload 9
    //   85: sipush 1000
    //   88: iadd
    //   89: new 691	android/content/ComponentName
    //   92: dup
    //   93: aload_0
    //   94: invokevirtual 554	android/content/Context:getPackageName	()Ljava/lang/String;
    //   97: ldc_w 784
    //   100: invokevirtual 564	java/lang/Class:getName	()Ljava/lang/String;
    //   103: invokespecial 713	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   106: invokespecial 716	android/app/job/JobInfo$Builder:<init>	(ILandroid/content/ComponentName;)V
    //   109: iconst_1
    //   110: invokevirtual 720	android/app/job/JobInfo$Builder:setRequiredNetworkType	(I)Landroid/app/job/JobInfo$Builder;
    //   113: new 786	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a
    //   116: dup
    //   117: invokespecial 787	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:<init>	()V
    //   120: aload_3
    //   121: invokevirtual 790	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:b	(Ljava/lang/String;)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   124: lload 7
    //   126: invokestatic 795	com/allfootball/news/util/n:c	(J)Ljava/lang/String;
    //   129: invokevirtual 797	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:c	(Ljava/lang/String;)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   132: lload 7
    //   134: invokevirtual 800	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:a	(J)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   137: lload 5
    //   139: invokevirtual 802	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:b	(J)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   142: lload 7
    //   144: lload 5
    //   146: lsub
    //   147: invokevirtual 804	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:c	(J)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   150: aload 4
    //   152: invokevirtual 806	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:e	(Ljava/lang/String;)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   155: aload_1
    //   156: invokevirtual 809	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:d	(Ljava/lang/String;)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   159: aload_2
    //   160: invokevirtual 811	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:a	(Ljava/lang/String;)Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a;
    //   163: invokevirtual 814	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel$a:a	()Lcom/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel;
    //   166: invokevirtual 819	com/allfootball/news/service/PushAnalyseJobService$PushAnalyseModel:a	()Landroid/os/PersistableBundle;
    //   169: invokevirtual 823	android/app/job/JobInfo$Builder:setExtras	(Landroid/os/PersistableBundle;)Landroid/app/job/JobInfo$Builder;
    //   172: invokevirtual 730	android/app/job/JobInfo$Builder:build	()Landroid/app/job/JobInfo;
    //   175: invokevirtual 734	android/app/job/JobScheduler:schedule	(Landroid/app/job/JobInfo;)I
    //   178: istore 9
    //   180: goto +15 -> 195
    //   183: astore_1
    //   184: goto +4 -> 188
    //   187: astore_0
    //   188: aload_1
    //   189: invokevirtual 202	java/lang/IllegalArgumentException:printStackTrace	()V
    //   192: iconst_0
    //   193: istore 9
    //   195: aload_0
    //   196: iload 10
    //   198: invokestatic 737	com/allfootball/news/util/d:E	(Landroid/content/Context;I)Z
    //   201: pop
    //   202: new 380	java/lang/StringBuilder
    //   205: dup
    //   206: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   209: astore_0
    //   210: aload_0
    //   211: ldc_w 825
    //   214: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload_0
    //   219: iload 9
    //   221: invokevirtual 742	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   224: pop
    //   225: aload_0
    //   226: ldc_w 744
    //   229: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   232: pop
    //   233: aload_0
    //   234: iload 11
    //   236: invokevirtual 742	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   239: pop
    //   240: aload_0
    //   241: ldc_w 746
    //   244: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   247: pop
    //   248: aload_0
    //   249: iload 10
    //   251: invokevirtual 742	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   254: pop
    //   255: ldc_w 701
    //   258: aload_0
    //   259: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   262: invokestatic 706	com/allfootball/news/util/an:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   265: iload 9
    //   267: ireturn
    //   268: iconst_0
    //   269: ireturn
    // Exception table:
    //   from	to	target	type
    //   77	180	183	java/lang/IllegalArgumentException
  }
  
  private static Bitmap a(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    float f1 = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f2 = 720.0F / f1;
    float f3 = j;
    Object localObject = new Matrix();
    f1 = 'ː' / i;
    f2 = f2 * f3 / f3;
    if (i <= 720) {
      ((Matrix)localObject).postScale(1.0F, 1.0F);
    } else {
      ((Matrix)localObject).postScale(f1, f2);
    }
    localObject = Bitmap.createBitmap(paramBitmap, 0, 0, i, j, (Matrix)localObject, true);
    paramBitmap.recycle();
    return localObject;
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt)
  {
    Object localObject1 = new Matrix();
    ((Matrix)localObject1).postRotate(paramInt);
    try
    {
      localObject1 = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), (Matrix)localObject1, true);
    }
    catch (RuntimeException localRuntimeException)
    {
      localRuntimeException.printStackTrace();
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      localOutOfMemoryError.printStackTrace();
    }
    Object localObject2 = null;
    Object localObject3 = localObject2;
    if (localObject2 == null) {
      localObject3 = paramBitmap;
    }
    if (paramBitmap != localObject3) {
      System.gc();
    }
    return localObject3;
  }
  
  /* Error */
  public static String a()
  {
    // Byte code:
    //   0: new 380	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: invokestatic 865	com/allfootball/news/util/e:k	()Ljava/lang/String;
    //   12: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: pop
    //   16: aload_1
    //   17: ldc_w 867
    //   20: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: new 771	java/io/File
    //   27: dup
    //   28: aload_1
    //   29: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   35: astore_1
    //   36: aload_1
    //   37: invokevirtual 775	java/io/File:exists	()Z
    //   40: istore_0
    //   41: aconst_null
    //   42: astore 5
    //   44: aconst_null
    //   45: astore 4
    //   47: iload_0
    //   48: ifne +5 -> 53
    //   51: aconst_null
    //   52: areturn
    //   53: new 869	java/io/FileInputStream
    //   56: dup
    //   57: aload_1
    //   58: invokespecial 872	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   61: astore_3
    //   62: new 874	java/io/InputStreamReader
    //   65: dup
    //   66: aload_3
    //   67: ldc_w 876
    //   70: invokespecial 879	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   73: astore_1
    //   74: new 881	java/io/BufferedReader
    //   77: dup
    //   78: aload_1
    //   79: invokespecial 884	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   82: astore_2
    //   83: aload_2
    //   84: invokevirtual 887	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   87: astore 4
    //   89: aload_2
    //   90: invokevirtual 890	java/io/BufferedReader:close	()V
    //   93: aload_1
    //   94: invokevirtual 891	java/io/InputStreamReader:close	()V
    //   97: aload_3
    //   98: invokevirtual 892	java/io/FileInputStream:close	()V
    //   101: goto +8 -> 109
    //   104: astore_1
    //   105: aload_1
    //   106: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   109: aload 4
    //   111: areturn
    //   112: astore 5
    //   114: aload_2
    //   115: astore 4
    //   117: aload_1
    //   118: astore_2
    //   119: aload 5
    //   121: astore_1
    //   122: goto +35 -> 157
    //   125: goto +77 -> 202
    //   128: astore 5
    //   130: aload_1
    //   131: astore_2
    //   132: aload 5
    //   134: astore_1
    //   135: goto +22 -> 157
    //   138: astore_1
    //   139: aconst_null
    //   140: astore_2
    //   141: goto +16 -> 157
    //   144: aconst_null
    //   145: astore_1
    //   146: aload 5
    //   148: astore_2
    //   149: goto +53 -> 202
    //   152: astore_1
    //   153: aconst_null
    //   154: astore_2
    //   155: aload_2
    //   156: astore_3
    //   157: aload 4
    //   159: ifnull +11 -> 170
    //   162: aload 4
    //   164: invokevirtual 890	java/io/BufferedReader:close	()V
    //   167: goto +3 -> 170
    //   170: aload_2
    //   171: ifnull +7 -> 178
    //   174: aload_2
    //   175: invokevirtual 891	java/io/InputStreamReader:close	()V
    //   178: aload_3
    //   179: ifnull +14 -> 193
    //   182: aload_3
    //   183: invokevirtual 892	java/io/FileInputStream:close	()V
    //   186: goto +7 -> 193
    //   189: aload_2
    //   190: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   193: aload_1
    //   194: athrow
    //   195: aconst_null
    //   196: astore_1
    //   197: aload_1
    //   198: astore_3
    //   199: aload 5
    //   201: astore_2
    //   202: aload_2
    //   203: ifnull +10 -> 213
    //   206: aload_2
    //   207: invokevirtual 890	java/io/BufferedReader:close	()V
    //   210: goto +3 -> 213
    //   213: aload_1
    //   214: ifnull +7 -> 221
    //   217: aload_1
    //   218: invokevirtual 891	java/io/InputStreamReader:close	()V
    //   221: aload_3
    //   222: ifnull +15 -> 237
    //   225: aload_3
    //   226: invokevirtual 892	java/io/FileInputStream:close	()V
    //   229: ldc_w 894
    //   232: areturn
    //   233: aload_1
    //   234: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   237: ldc_w 894
    //   240: areturn
    //   241: astore_1
    //   242: goto -47 -> 195
    //   245: astore_1
    //   246: goto -102 -> 144
    //   249: astore_2
    //   250: aload 5
    //   252: astore_2
    //   253: goto -51 -> 202
    //   256: astore 4
    //   258: goto -133 -> 125
    //   261: astore_2
    //   262: goto -73 -> 189
    //   265: astore_1
    //   266: goto -33 -> 233
    // Local variable table:
    //   start	length	slot	name	signature
    //   40	8	0	bool	boolean
    //   7	87	1	localObject1	Object
    //   104	14	1	localIOException1	IOException
    //   121	14	1	localObject2	Object
    //   138	1	1	localObject3	Object
    //   145	1	1	localObject4	Object
    //   152	42	1	localObject5	Object
    //   196	38	1	localObject6	Object
    //   241	1	1	localException1	Exception
    //   245	1	1	localException2	Exception
    //   265	1	1	localIOException2	IOException
    //   82	125	2	localObject7	Object
    //   249	1	2	localException3	Exception
    //   252	1	2	localObject8	Object
    //   261	1	2	localIOException3	IOException
    //   61	165	3	localObject9	Object
    //   45	118	4	localObject10	Object
    //   256	1	4	localException4	Exception
    //   42	1	5	localObject11	Object
    //   112	8	5	localObject12	Object
    //   128	123	5	localObject13	Object
    // Exception table:
    //   from	to	target	type
    //   89	101	104	java/io/IOException
    //   83	89	112	finally
    //   74	83	128	finally
    //   62	74	138	finally
    //   53	62	152	finally
    //   53	62	241	java/lang/Exception
    //   62	74	245	java/lang/Exception
    //   74	83	249	java/lang/Exception
    //   83	89	256	java/lang/Exception
    //   162	167	261	java/io/IOException
    //   174	178	261	java/io/IOException
    //   182	186	261	java/io/IOException
    //   206	210	265	java/io/IOException
    //   217	221	265	java/io/IOException
    //   225	229	265	java/io/IOException
  }
  
  public static final String a(int paramInt)
  {
    if (paramInt < 1) {
      return null;
    }
    Object localObject = new Random();
    char[] arrayOfChar2 = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    char[] arrayOfChar1 = new char[paramInt];
    paramInt = 0;
    while (paramInt < arrayOfChar1.length)
    {
      arrayOfChar1[paramInt] = arrayOfChar2[localObject.nextInt(71)];
      paramInt += 1;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Random:");
    ((StringBuilder)localObject).append(arrayOfChar1);
    return new String(((StringBuilder)localObject).toString());
  }
  
  /* Error */
  public static String a(Context paramContext, Bitmap paramBitmap, String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore 5
    //   3: iload 4
    //   5: ifeq +9 -> 14
    //   8: aload_1
    //   9: invokestatic 912	com/allfootball/news/util/e:a	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   12: astore 5
    //   14: new 380	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   21: astore_1
    //   22: aload_1
    //   23: aload_0
    //   24: invokestatic 914	com/allfootball/news/util/e:d	(Landroid/content/Context;)Ljava/lang/String;
    //   27: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_1
    //   32: ldc_w 916
    //   35: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_1
    //   40: aload_2
    //   41: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: pop
    //   45: aload_1
    //   46: ldc_w 916
    //   49: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: pop
    //   53: aload_1
    //   54: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore 6
    //   59: new 771	java/io/File
    //   62: dup
    //   63: aload 6
    //   65: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   68: astore_0
    //   69: aload_0
    //   70: invokevirtual 775	java/io/File:exists	()Z
    //   73: ifne +8 -> 81
    //   76: aload_0
    //   77: invokevirtual 919	java/io/File:mkdir	()Z
    //   80: pop
    //   81: new 380	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   88: astore_0
    //   89: aload_0
    //   90: aload 6
    //   92: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: pop
    //   96: aload_0
    //   97: aload_3
    //   98: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: new 771	java/io/File
    //   105: dup
    //   106: aload_0
    //   107: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   110: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   113: astore_1
    //   114: aconst_null
    //   115: astore_2
    //   116: aconst_null
    //   117: astore_0
    //   118: new 921	java/io/BufferedOutputStream
    //   121: dup
    //   122: new 923	java/io/FileOutputStream
    //   125: dup
    //   126: aload_1
    //   127: invokespecial 924	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   130: invokespecial 927	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   133: astore_1
    //   134: aload 5
    //   136: getstatic 933	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   139: bipush 70
    //   141: aload_1
    //   142: invokevirtual 937	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   145: pop
    //   146: aload_1
    //   147: invokevirtual 940	java/io/BufferedOutputStream:flush	()V
    //   150: aload_1
    //   151: invokevirtual 941	java/io/BufferedOutputStream:close	()V
    //   154: aload 5
    //   156: invokevirtual 847	android/graphics/Bitmap:recycle	()V
    //   159: goto +47 -> 206
    //   162: astore_2
    //   163: aload_1
    //   164: astore_0
    //   165: aload_2
    //   166: astore_1
    //   167: goto +65 -> 232
    //   170: astore_2
    //   171: goto +12 -> 183
    //   174: astore_1
    //   175: goto +57 -> 232
    //   178: astore_0
    //   179: aload_2
    //   180: astore_1
    //   181: aload_0
    //   182: astore_2
    //   183: aload_1
    //   184: astore_0
    //   185: aload_2
    //   186: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   189: aload_1
    //   190: invokevirtual 941	java/io/BufferedOutputStream:close	()V
    //   193: aload 5
    //   195: invokevirtual 847	android/graphics/Bitmap:recycle	()V
    //   198: goto +8 -> 206
    //   201: astore_0
    //   202: aload_0
    //   203: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   206: new 380	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   213: astore_0
    //   214: aload_0
    //   215: aload 6
    //   217: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: pop
    //   221: aload_0
    //   222: aload_3
    //   223: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_0
    //   228: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: areturn
    //   232: aload_0
    //   233: invokevirtual 941	java/io/BufferedOutputStream:close	()V
    //   236: aload 5
    //   238: invokevirtual 847	android/graphics/Bitmap:recycle	()V
    //   241: goto +8 -> 249
    //   244: astore_0
    //   245: aload_0
    //   246: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   249: aload_1
    //   250: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	251	0	paramContext	Context
    //   0	251	1	paramBitmap	Bitmap
    //   0	251	2	paramString1	String
    //   0	251	3	paramString2	String
    //   0	251	4	paramBoolean	boolean
    //   1	236	5	localBitmap	Bitmap
    //   57	159	6	str	String
    // Exception table:
    //   from	to	target	type
    //   134	150	162	finally
    //   134	150	170	java/lang/Exception
    //   118	134	174	finally
    //   185	189	174	finally
    //   118	134	178	java/lang/Exception
    //   150	159	201	java/io/IOException
    //   189	198	201	java/io/IOException
    //   232	241	244	java/io/IOException
  }
  
  public static String a(VolleyError paramVolleyError)
  {
    if (paramVolleyError != null) {
      try
      {
        if ((paramVolleyError.a != null) && (paramVolleyError.a.b != null))
        {
          paramVolleyError = new String(paramVolleyError.a.b);
          return paramVolleyError;
        }
      }
      catch (Exception paramVolleyError)
      {
        paramVolleyError.printStackTrace();
      }
    }
    return null;
  }
  
  public static String a(Exception paramException)
  {
    if (paramException == null) {
      return null;
    }
    StringWriter localStringWriter = new StringWriter();
    paramException.printStackTrace(new PrintWriter(localStringWriter));
    return localStringWriter.toString();
  }
  
  public static String a(String paramString)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("@");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("zQcN6aR4");
      ((StringBuilder)localObject).append(c(localStringBuilder.toString()));
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  /* Error */
  public static String a(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    // Byte code:
    //   0: new 771	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 14
    //   10: aload 14
    //   12: invokevirtual 775	java/io/File:exists	()Z
    //   15: ifne +9 -> 24
    //   18: aload 14
    //   20: invokevirtual 978	java/io/File:mkdirs	()Z
    //   23: pop
    //   24: new 771	java/io/File
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   32: astore 15
    //   34: aconst_null
    //   35: astore 5
    //   37: aconst_null
    //   38: astore 8
    //   40: aconst_null
    //   41: astore 13
    //   43: new 980	java/util/zip/ZipFile
    //   46: dup
    //   47: aload 15
    //   49: invokespecial 981	java/util/zip/ZipFile:<init>	(Ljava/io/File;)V
    //   52: astore 12
    //   54: aload 12
    //   56: invokevirtual 985	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   59: astore 16
    //   61: aconst_null
    //   62: astore 6
    //   64: aload 6
    //   66: astore 7
    //   68: aload 16
    //   70: invokeinterface 990 1 0
    //   75: istore 4
    //   77: iload 4
    //   79: ifeq +397 -> 476
    //   82: aload 16
    //   84: invokeinterface 993 1 0
    //   89: checkcast 995	java/util/zip/ZipEntry
    //   92: astore 5
    //   94: aload 5
    //   96: invokevirtual 996	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   99: astore 7
    //   101: aload 12
    //   103: aload 5
    //   105: invokevirtual 1000	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   108: astore 5
    //   110: new 380	java/lang/StringBuilder
    //   113: dup
    //   114: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   117: astore 8
    //   119: aload 8
    //   121: aload_1
    //   122: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload 8
    //   128: ldc_w 916
    //   131: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload 8
    //   137: aload 7
    //   139: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: pop
    //   143: aload 8
    //   145: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: ldc_w 1002
    //   151: ldc_w 916
    //   154: invokevirtual 1006	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   157: astore 7
    //   159: new 771	java/io/File
    //   162: dup
    //   163: aload 7
    //   165: iconst_0
    //   166: aload 7
    //   168: bipush 47
    //   170: invokevirtual 1009	java/lang/String:lastIndexOf	(I)I
    //   173: invokevirtual 495	java/lang/String:substring	(II)Ljava/lang/String;
    //   176: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   179: astore 8
    //   181: aload 8
    //   183: invokevirtual 775	java/io/File:exists	()Z
    //   186: ifne +9 -> 195
    //   189: aload 8
    //   191: invokevirtual 978	java/io/File:mkdirs	()Z
    //   194: pop
    //   195: new 771	java/io/File
    //   198: dup
    //   199: aload 7
    //   201: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   204: invokevirtual 1012	java/io/File:isDirectory	()Z
    //   207: ifeq +48 -> 255
    //   210: aload 6
    //   212: invokestatic 398	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   215: istore 4
    //   217: aload 6
    //   219: astore 8
    //   221: iload 4
    //   223: ifeq +7 -> 230
    //   226: aload 7
    //   228: astore 8
    //   230: aload 8
    //   232: astore 6
    //   234: aload 5
    //   236: ifnull -172 -> 64
    //   239: aload 8
    //   241: astore 7
    //   243: aload 5
    //   245: invokevirtual 1015	java/io/InputStream:close	()V
    //   248: aload 8
    //   250: astore 6
    //   252: goto -188 -> 64
    //   255: new 923	java/io/FileOutputStream
    //   258: dup
    //   259: aload 7
    //   261: invokespecial 1016	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   264: astore 8
    //   266: aload 5
    //   268: astore 7
    //   270: aload 8
    //   272: astore 10
    //   274: sipush 1024
    //   277: newarray byte
    //   279: astore 9
    //   281: aload 5
    //   283: astore 7
    //   285: aload 8
    //   287: astore 10
    //   289: aload 5
    //   291: aload 9
    //   293: invokevirtual 1020	java/io/InputStream:read	([B)I
    //   296: istore_3
    //   297: iload_3
    //   298: ifle +23 -> 321
    //   301: aload 5
    //   303: astore 7
    //   305: aload 8
    //   307: astore 10
    //   309: aload 8
    //   311: aload 9
    //   313: iconst_0
    //   314: iload_3
    //   315: invokevirtual 1026	java/io/OutputStream:write	([BII)V
    //   318: goto -37 -> 281
    //   321: aload 8
    //   323: astore 9
    //   325: aload 5
    //   327: ifnull +16 -> 343
    //   330: aload 6
    //   332: astore 7
    //   334: aload 5
    //   336: invokevirtual 1015	java/io/InputStream:close	()V
    //   339: aload 8
    //   341: astore 9
    //   343: aload 6
    //   345: astore 7
    //   347: aload 9
    //   349: invokevirtual 1027	java/io/OutputStream:close	()V
    //   352: goto -288 -> 64
    //   355: astore 11
    //   357: aload 5
    //   359: astore 9
    //   361: aload 8
    //   363: astore 5
    //   365: goto +47 -> 412
    //   368: astore_1
    //   369: aload 13
    //   371: astore 8
    //   373: goto +69 -> 442
    //   376: astore 11
    //   378: aconst_null
    //   379: astore 7
    //   381: aload 5
    //   383: astore 9
    //   385: aload 7
    //   387: astore 5
    //   389: goto +23 -> 412
    //   392: astore_1
    //   393: aconst_null
    //   394: astore 5
    //   396: aload 13
    //   398: astore 8
    //   400: goto +42 -> 442
    //   403: astore 11
    //   405: aconst_null
    //   406: astore 9
    //   408: aload 9
    //   410: astore 5
    //   412: aload 9
    //   414: astore 7
    //   416: aload 5
    //   418: astore 10
    //   420: aload 11
    //   422: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   425: aload 9
    //   427: ifnull +271 -> 698
    //   430: aload 6
    //   432: astore 7
    //   434: aload 9
    //   436: invokevirtual 1015	java/io/InputStream:close	()V
    //   439: goto +259 -> 698
    //   442: aload 5
    //   444: ifnull +12 -> 456
    //   447: aload 6
    //   449: astore 7
    //   451: aload 5
    //   453: invokevirtual 1015	java/io/InputStream:close	()V
    //   456: aload 8
    //   458: ifnull +12 -> 470
    //   461: aload 6
    //   463: astore 7
    //   465: aload 8
    //   467: invokevirtual 1027	java/io/OutputStream:close	()V
    //   470: aload 6
    //   472: astore 7
    //   474: aload_1
    //   475: athrow
    //   476: aload 12
    //   478: invokevirtual 1028	java/util/zip/ZipFile:close	()V
    //   481: aload 6
    //   483: astore_0
    //   484: goto +117 -> 601
    //   487: astore 5
    //   489: aload 7
    //   491: astore 6
    //   493: goto +16 -> 509
    //   496: astore_0
    //   497: aload 12
    //   499: astore 5
    //   501: goto +185 -> 686
    //   504: astore 5
    //   506: aconst_null
    //   507: astore 6
    //   509: aload 12
    //   511: astore_1
    //   512: aload 5
    //   514: astore 7
    //   516: goto +15 -> 531
    //   519: astore_0
    //   520: goto +166 -> 686
    //   523: astore 7
    //   525: aconst_null
    //   526: astore 6
    //   528: aload 8
    //   530: astore_1
    //   531: aload_1
    //   532: astore 5
    //   534: new 380	java/lang/StringBuilder
    //   537: dup
    //   538: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   541: astore 8
    //   543: aload_1
    //   544: astore 5
    //   546: aload 8
    //   548: ldc_w 1030
    //   551: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: pop
    //   555: aload_1
    //   556: astore 5
    //   558: aload 8
    //   560: aload_0
    //   561: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   564: pop
    //   565: aload_1
    //   566: astore 5
    //   568: ldc_w 701
    //   571: aload 8
    //   573: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   576: invokestatic 1033	com/allfootball/news/util/an:a	(Ljava/lang/String;Ljava/lang/Object;)V
    //   579: aload_1
    //   580: astore 5
    //   582: aload 7
    //   584: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   587: aload 6
    //   589: astore_0
    //   590: aload_1
    //   591: ifnull +10 -> 601
    //   594: aload_1
    //   595: invokevirtual 1028	java/util/zip/ZipFile:close	()V
    //   598: aload 6
    //   600: astore_0
    //   601: aload 15
    //   603: invokevirtual 1036	java/io/File:delete	()Z
    //   606: pop
    //   607: aload_2
    //   608: invokestatic 398	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   611: ifeq +9 -> 620
    //   614: aload 14
    //   616: invokevirtual 1039	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   619: areturn
    //   620: new 771	java/io/File
    //   623: dup
    //   624: aload_0
    //   625: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   628: astore_0
    //   629: new 380	java/lang/StringBuilder
    //   632: dup
    //   633: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   636: astore_1
    //   637: aload_1
    //   638: aload_0
    //   639: invokevirtual 1043	java/io/File:getParentFile	()Ljava/io/File;
    //   642: invokevirtual 1039	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   645: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   648: pop
    //   649: aload_1
    //   650: ldc_w 916
    //   653: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   656: pop
    //   657: aload_1
    //   658: aload_2
    //   659: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: pop
    //   663: new 771	java/io/File
    //   666: dup
    //   667: aload_1
    //   668: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   671: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   674: astore_1
    //   675: aload_0
    //   676: aload_1
    //   677: invokevirtual 1047	java/io/File:renameTo	(Ljava/io/File;)Z
    //   680: pop
    //   681: aload_1
    //   682: invokevirtual 1039	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   685: areturn
    //   686: aload 5
    //   688: ifnull +8 -> 696
    //   691: aload 5
    //   693: invokevirtual 1028	java/util/zip/ZipFile:close	()V
    //   696: aload_0
    //   697: athrow
    //   698: aload 5
    //   700: ifnull -636 -> 64
    //   703: aload 5
    //   705: astore 9
    //   707: goto -364 -> 343
    //   710: astore_1
    //   711: aload 10
    //   713: astore 8
    //   715: aload 7
    //   717: astore 5
    //   719: goto -277 -> 442
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	722	0	paramString1	String
    //   0	722	1	paramString2	String
    //   0	722	2	paramString3	String
    //   296	19	3	i	int
    //   75	147	4	bool	boolean
    //   35	417	5	localObject1	Object
    //   487	1	5	localException1	Exception
    //   499	1	5	localObject2	Object
    //   504	9	5	localException2	Exception
    //   532	186	5	localObject3	Object
    //   62	537	6	localObject4	Object
    //   66	449	7	localObject5	Object
    //   523	193	7	localException3	Exception
    //   38	676	8	localObject6	Object
    //   279	427	9	localObject7	Object
    //   272	440	10	localObject8	Object
    //   355	1	11	localException4	Exception
    //   376	1	11	localException5	Exception
    //   403	18	11	localException6	Exception
    //   52	458	12	localZipFile	java.util.zip.ZipFile
    //   41	356	13	localObject9	Object
    //   8	607	14	localFile1	File
    //   32	570	15	localFile2	File
    //   59	24	16	localEnumeration	java.util.Enumeration
    // Exception table:
    //   from	to	target	type
    //   274	281	355	java/lang/Exception
    //   289	297	355	java/lang/Exception
    //   309	318	355	java/lang/Exception
    //   110	195	368	finally
    //   195	217	368	finally
    //   255	266	368	finally
    //   110	195	376	java/lang/Exception
    //   195	217	376	java/lang/Exception
    //   255	266	376	java/lang/Exception
    //   82	110	392	finally
    //   82	110	403	java/lang/Exception
    //   68	77	487	java/lang/Exception
    //   243	248	487	java/lang/Exception
    //   334	339	487	java/lang/Exception
    //   347	352	487	java/lang/Exception
    //   434	439	487	java/lang/Exception
    //   451	456	487	java/lang/Exception
    //   465	470	487	java/lang/Exception
    //   474	476	487	java/lang/Exception
    //   54	61	496	finally
    //   68	77	496	finally
    //   243	248	496	finally
    //   334	339	496	finally
    //   347	352	496	finally
    //   434	439	496	finally
    //   451	456	496	finally
    //   465	470	496	finally
    //   474	476	496	finally
    //   54	61	504	java/lang/Exception
    //   43	54	519	finally
    //   534	543	519	finally
    //   546	555	519	finally
    //   558	565	519	finally
    //   568	579	519	finally
    //   582	587	519	finally
    //   43	54	523	java/lang/Exception
    //   274	281	710	finally
    //   289	297	710	finally
    //   309	318	710	finally
    //   420	425	710	finally
  }
  
  public static void a(Activity paramActivity)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 21)
    {
      localObject = paramActivity.getWindow();
      ((Window)localObject).clearFlags(201326592);
      ((Window)localObject).getDecorView().setSystemUiVisibility(1792);
      ((Window)localObject).addFlags(Integer.MIN_VALUE);
      ((Window)localObject).setStatusBarColor(0);
      paramActivity = new com.a.a.a(paramActivity);
      paramActivity.a(true);
      paramActivity.c(R.color.title);
      return;
    }
    if (Build.VERSION.SDK_INT == 19)
    {
      localObject = paramActivity.getWindow();
      WindowManager.LayoutParams localLayoutParams = ((Window)localObject).getAttributes();
      localLayoutParams.flags |= 0x4000000;
      ((Window)localObject).setAttributes(localLayoutParams);
      localObject = new com.a.a.a(paramActivity);
      ((com.a.a.a)localObject).a(true);
      ((com.a.a.a)localObject).b(true);
      ((com.a.a.a)localObject).a(paramActivity.getResources().getColor(R.color.transparent));
    }
  }
  
  public static void a(Activity paramActivity, View paramView, int paramInt)
  {
    Object localObject = (ViewGroup)paramView.getParent();
    if (localObject != null) {
      ((ViewGroup)localObject).removeView(paramView);
    }
    paramActivity = (ViewGroup)paramActivity.findViewById(16908290);
    localObject = new FrameLayout.LayoutParams(-1, -1);
    if (paramInt < 0)
    {
      paramActivity.addView(paramView, (ViewGroup.LayoutParams)localObject);
      return;
    }
    paramActivity.addView(paramView, paramInt, (ViewGroup.LayoutParams)localObject);
  }
  
  public static void a(Context paramContext, long paramLong, int paramInt, List<String> paramList)
  {
    if (paramContext != null)
    {
      if (paramList == null) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(com.allfootball.news.a.d.j);
      localStringBuilder.append("?ct=");
      localStringBuilder.append(paramLong);
      localStringBuilder.append("&archive_num=");
      localStringBuilder.append(paramInt);
      localStringBuilder.append("&pic_num=");
      localStringBuilder.append(paramList.size());
      localStringBuilder.append("&pic_size=");
      localStringBuilder.append(a(paramList));
      paramList = localStringBuilder.toString();
      an.a("AppUtils", paramList);
      AppJobService.a(paramContext, paramList);
      return;
    }
  }
  
  public static void a(Context paramContext, long paramLong1, long paramLong2, String paramString)
  {
    if (paramContext == null) {
      return;
    }
    float f = (float)(paramLong2 - paramLong1);
    try
    {
      paramContext = new JSONObject();
      paramContext.put("url", paramString);
      paramContext.put("consume_time", f);
      ai.a(BaseApplication.c(), "request_ct", paramContext);
      return;
    }
    catch (org.json.JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, long paramLong, String paramString)
  {
    a(paramContext, String.valueOf(paramLong), paramString);
  }
  
  public static void a(Context paramContext, View paramView)
  {
    paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    if ((paramContext != null) && (paramView != null)) {
      paramContext.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
  }
  
  public static void a(Context paramContext, ConsoleMessage paramConsoleMessage, String paramString)
  {
    if (paramConsoleMessage == null) {
      return;
    }
    String str1 = paramConsoleMessage.message();
    int i = paramConsoleMessage.lineNumber();
    String str2 = paramConsoleMessage.sourceId();
    if (paramConsoleMessage.messageLevel() != null) {
      paramConsoleMessage = paramConsoleMessage.messageLevel().name();
    } else {
      paramConsoleMessage = null;
    }
    if (!TextUtils.isEmpty(str1))
    {
      if (!"error".equalsIgnoreCase(paramConsoleMessage)) {
        return;
      }
      new ai.a().a("af_type", paramString).a("af_line_number", i).a("af_source_id", str2).a("af_message", str1).a("af_console_error").a(paramContext);
      return;
    }
  }
  
  public static void a(Context paramContext, TextView paramTextView, String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString))
    {
      paramTextView.setText("");
      return;
    }
    int j = paramTextView.getMeasuredWidth();
    String str = paramString;
    if (j > 0)
    {
      int i = paramString.length();
      float f = paramTextView.getPaint().measureText(paramString) / i;
      paramInt = (int)((j * 2 - a(paramContext, paramInt)) / f);
      str = paramString;
      if (paramInt < i)
      {
        str = paramString;
        if (paramInt > 3)
        {
          paramContext = paramString.substring(0, paramInt - 3);
          paramString = new StringBuilder();
          paramString.append(paramContext);
          paramString.append("...");
          str = paramString.toString();
        }
      }
    }
    paramTextView.setText(str);
  }
  
  public static void a(Context paramContext, TextView paramTextView, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = R.string.attented;; i = R.string.ready_attent)
    {
      paramContext = paramContext.getString(i);
      break;
    }
    paramTextView.setText(paramContext);
    paramTextView.setSelected(paramBoolean);
    if (paramTextView.getVisibility() != 0) {
      paramTextView.setVisibility(0);
    }
  }
  
  public static void a(Context paramContext, CommentEntity paramCommentEntity)
  {
    paramContext.startActivity(new t.a().a(paramCommentEntity.getUser().getId()).c(paramCommentEntity.getUser().getUsername()).a(paramCommentEntity.getUser().getAvatar()).d(paramCommentEntity.getUser().getLogined_at()).a().a(paramContext));
  }
  
  public static void a(Context paramContext, UserNotificationModel paramUserNotificationModel)
  {
    UAirship.a().o().c(true);
    UAirship.a().o().e(paramUserNotificationModel.isSilent() ^ true);
    UAirship.a().o().f(paramUserNotificationModel.isSilent() ^ true);
    paramUserNotificationModel.setNotice(true);
    d.a(paramContext, paramUserNotificationModel);
    AppService.e(paramContext, "notify");
  }
  
  public static void a(Context paramContext, VolleyError paramVolleyError, EmptyViewErrorManager paramEmptyViewErrorManager)
  {
    Object localObject = b(paramVolleyError);
    if ((localObject != null) && (!TextUtils.isEmpty(((ErrorEntity)localObject).getMessage())))
    {
      localObject = ((ErrorEntity)localObject).getMessage();
    }
    else
    {
      paramContext.getString(R.string.request_fail);
      localObject = paramContext.getString(R.string.system_error);
    }
    if (a(paramContext, paramVolleyError))
    {
      paramEmptyViewErrorManager.showErrorView(paramContext.getString(R.string.network_not_good), R.drawable.no_network);
      return;
    }
    paramEmptyViewErrorManager.showErrorView((String)localObject, R.drawable.no_network);
  }
  
  public static void a(Context paramContext, Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    a(paramContext, paramObject, true);
  }
  
  public static void a(Context paramContext, Object paramObject, boolean paramBoolean)
  {
    a(paramObject, paramBoolean);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (!"mounted".equals(Environment.getExternalStorageState()))
    {
      a(paramContext, paramContext.getString(R.string.no_sd_card_cant_download));
      return;
    }
    DownloadModel localDownloadModel = new DownloadModel();
    localDownloadModel.setTitle(paramContext.getString(R.string.if_download_apk));
    localDownloadModel.setDesc(paramString);
    localDownloadModel.setUrl(paramString);
    paramString = new Intent(paramContext, DownloadActivity.class);
    paramString.putExtra("download_model", localDownloadModel);
    paramContext.startActivity(paramString);
  }
  
  public static void a(Context paramContext, String paramString, int paramInt1, long paramLong1, long paramLong2, long paramLong3, long paramLong4, int paramInt2)
  {
    if (paramContext == null) {
      return;
    }
    if (d.R(paramContext).equals("true"))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(com.allfootball.news.a.d.j);
      localStringBuilder.append("room_trace?m=");
      localStringBuilder.append(paramString);
      localStringBuilder.append("&r=");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append("&t1=");
      localStringBuilder.append(paramLong1);
      localStringBuilder.append("&t2=");
      localStringBuilder.append(paramLong2);
      localStringBuilder.append("&t3=");
      localStringBuilder.append(paramLong3);
      localStringBuilder.append("&t=");
      localStringBuilder.append(paramLong4);
      localStringBuilder.append("&s=");
      localStringBuilder.append(paramInt2);
      AppJobService.a(paramContext, localStringBuilder.toString());
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    AppService.a(paramContext, paramString1, paramString2);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext.startActivity(new t.a().b(paramString2).c(paramString1).a(paramString3).a().a(paramContext));
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    int i = paramContext.getPackageManager().getApplicationEnabledSetting("com.android.providers.downloads");
    if ((i != 0) && (i != 1))
    {
      paramString2 = new Intent("android.intent.action.VIEW");
      paramString2.setData(Uri.parse(paramString1));
      paramContext.startActivity(paramString2);
      return;
    }
    DownloadManager localDownloadManager = (DownloadManager)paramContext.getSystemService("download");
    DownloadManager.Request localRequest = new DownloadManager.Request(Uri.parse(paramString1));
    localRequest.setAllowedNetworkTypes(3);
    localRequest.setVisibleInDownloadsUi(true);
    if (Build.VERSION.SDK_INT >= 11)
    {
      localRequest.allowScanningByMediaScanner();
      localRequest.setNotificationVisibility(1);
    }
    if (TextUtils.isEmpty(paramString4)) {
      paramString1 = paramString1.substring(paramString1.lastIndexOf("/") + 1);
    } else {
      paramString1 = paramString4;
    }
    paramString4 = paramString2;
    if (paramString2 == null) {
      paramString4 = paramContext.getString(R.string.download_apk);
    }
    localRequest.setTitle(paramString4);
    paramString2 = paramString3;
    if (paramString3 == null) {
      paramString2 = paramString1;
    }
    localRequest.setDescription(paramString2);
    if ("mounted".equals(Environment.getExternalStorageState())) {
      localRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, paramString1);
    }
    d.a(paramContext, localDownloadManager.enqueue(localRequest));
  }
  
  public static void a(Context paramContext, Map<String, String> paramMap) {}
  
  public static void a(TextView paramTextView, String paramString)
  {
    a(paramTextView, paramString, 0.4F);
  }
  
  public static void a(TextView paramTextView, String paramString, float paramFloat)
  {
    if (TextUtils.isEmpty(paramString))
    {
      paramTextView.setText("");
      return;
    }
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
    localSpannableStringBuilder.setSpan(new r(paramFloat), 0, paramString.length(), 34);
    paramTextView.setText(localSpannableStringBuilder);
  }
  
  public static void a(File paramFile)
    throws IOException
  {
    a(new FileOutputStream(paramFile, true), true);
  }
  
  public static void a(OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    byte[] arrayOfByte = new byte['ࠀ'];
    localObject3 = null;
    for (;;)
    {
      try
      {
        Object localObject1 = Runtime.getRuntime().exec("logcat -d -v time").getInputStream();
        try
        {
          int i = ((InputStream)localObject1).read(arrayOfByte);
          if (-1 != i)
          {
            paramOutputStream.write(arrayOfByte, 0, i);
            i = ((InputStream)localObject1).read(arrayOfByte);
            continue;
          }
          if (localObject1 == null) {}
        }
        finally
        {
          try
          {
            paramOutputStream.flush();
            if (paramBoolean) {
              paramOutputStream.close();
            }
            return;
          }
          catch (IOException paramOutputStream)
          {
            return;
          }
          localObject4 = finally;
          localObject3 = localObject1;
          localObject1 = localObject4;
        }
      }
      finally
      {
        if (localObject3 != null) {}
        try
        {
          localObject3.close();
        }
        catch (IOException localIOException2)
        {
          continue;
        }
        if (paramOutputStream != null) {}
        try
        {
          paramOutputStream.flush();
          if (paramBoolean) {
            paramOutputStream.close();
          }
        }
        catch (IOException paramOutputStream)
        {
          continue;
        }
      }
      try
      {
        ((InputStream)localObject1).close();
      }
      catch (IOException localIOException1) {}
    }
    if (paramOutputStream == null) {}
  }
  
  public static void a(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof Integer)) {
      try
      {
        localObject = BaseApplication.c().getString(((Integer)paramObject).intValue());
      }
      catch (Resources.NotFoundException paramObject)
      {
        paramObject.printStackTrace();
        return;
      }
    }
    a(localObject, true);
  }
  
  public static void a(Object paramObject, boolean paramBoolean)
  {
    BaseApplication.c().a(paramObject, paramBoolean ^ true);
  }
  
  public static void a(Request.Builder paramBuilder, String paramString1, String paramString2)
  {
    Object localObject;
    if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)))
    {
      localObject = paramString2;
      if (paramString2.length() < paramString2.getBytes().length)
      {
        localObject = new StringBuilder();
        int i = 0;
        int j = paramString2.length();
        while (i < j)
        {
          char c = paramString2.charAt(i);
          if ((c > '\037') && (c < '')) {
            ((StringBuilder)localObject).append(c);
          }
          i += 1;
        }
        localObject = ((StringBuilder)localObject).toString();
        if (TextUtils.isEmpty((CharSequence)localObject))
        {
          paramBuilder = new StringBuilder();
          paramBuilder.append("Unexpected value:");
          paramBuilder.append(paramString2);
          an.a("AppUtils", paramBuilder.toString());
          return;
        }
      }
    }
    try
    {
      paramBuilder.addHeader(paramString1, (String)localObject);
      return;
    }
    catch (Exception paramBuilder) {}
  }
  
  public static boolean a(Context paramContext)
  {
    Object localObject = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (localObject == null)
    {
      a(paramContext, paramContext.getResources().getString(R.string.please_connect_network));
      return false;
    }
    localObject = ((ConnectivityManager)localObject).getAllNetworkInfo();
    if (localObject != null)
    {
      int i = 0;
      while (i < localObject.length)
      {
        if (localObject[i].getState() == NetworkInfo.State.CONNECTED) {
          return true;
        }
        i += 1;
      }
    }
    a(paramContext, paramContext.getResources().getString(R.string.please_connect_network));
    return false;
  }
  
  public static boolean a(Context paramContext, int paramInt)
  {
    if (d.h(paramContext))
    {
      if (paramInt == 1) {
        return true;
      }
    }
    else if (paramInt == 0) {
      return true;
    }
    return false;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 1).size() > 0;
  }
  
  public static boolean a(Context paramContext, TextView paramTextView, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      paramTextView.setCompoundDrawables(null, null, null, null);
      return false;
    }
    Object localObject = paramContext.getResources();
    if (paramInt1 == 0) {
      paramInt2 = R.drawable.user_mark_big;
    } else {
      paramInt2 = R.drawable.user_mark_small;
    }
    localObject = ((Resources)localObject).getDrawable(paramInt2);
    float f2 = 11.0F;
    if (paramInt1 == 0) {
      f1 = 15.0F;
    } else {
      f1 = 11.0F;
    }
    paramInt2 = a(paramContext, f1);
    float f1 = f2;
    if (paramInt1 == 0) {
      f1 = 15.0F;
    }
    ((Drawable)localObject).setBounds(new Rect(0, 0, paramInt2, a(paramContext, f1)));
    paramTextView.setCompoundDrawables(null, null, (Drawable)localObject, null);
    return true;
  }
  
  public static boolean a(Context paramContext, VolleyError paramVolleyError)
  {
    paramVolleyError = com.android.volley2.error.a.a(paramVolleyError, paramContext);
    return (paramVolleyError.equals(paramContext.getString(R.string.request_timeout))) || (paramVolleyError.equals(Integer.valueOf(R.string.network_disable_exit)));
  }
  
  public static boolean a(Context paramContext, List<String> paramList)
  {
    if (paramList != null)
    {
      if (paramList.size() == 0) {
        return true;
      }
      long l1 = 0L;
      int i = 0;
      while (i < paramList.size())
      {
        File localFile = new File((String)paramList.get(i));
        long l2 = l1;
        try
        {
          if (!localFile.isDirectory())
          {
            l2 = t.a(localFile);
            l2 = l1 + l2;
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          l2 = l1;
        }
        i += 1;
        l1 = l2;
      }
      if ((float)l1 / 1024.0F > y(paramContext)) {
        i = 1;
      } else {
        i = 0;
      }
      return i == 0;
    }
    return true;
  }
  
  public static boolean a(View paramView, MotionEvent paramMotionEvent)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    int i = arrayOfInt[0];
    int j = arrayOfInt[1];
    if ((paramMotionEvent.getX() >= i) && (paramMotionEvent.getX() <= i + paramView.getWidth()) && (paramMotionEvent.getY() >= j)) {
      return paramMotionEvent.getY() <= j + paramView.getHeight();
    }
    return false;
  }
  
  public static boolean a(AttachmentEntity paramAttachmentEntity)
  {
    if (paramAttachmentEntity == null) {
      return false;
    }
    if ((!TextUtils.isEmpty(paramAttachmentEntity.getMime())) && (paramAttachmentEntity.getMime().equals("image/gif"))) {
      return true;
    }
    return e(paramAttachmentEntity.getUrl());
  }
  
  public static boolean a(UserEntity paramUserEntity)
  {
    return (paramUserEntity != null) && (!TextUtils.isEmpty(paramUserEntity.getUsername())) && (paramUserEntity.getId() != 0L);
  }
  
  public static long b()
  {
    if (!"mounted".equals(Environment.getExternalStorageState())) {
      return -1L;
    }
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return localStatFs.getAvailableBlocks() * l;
  }
  
  public static Uri b(Context paramContext, Intent paramIntent)
  {
    Uri localUri = paramIntent.getData();
    Object localObject = paramIntent.getType();
    paramIntent = localUri;
    int i;
    if (localUri != null)
    {
      paramIntent = localUri;
      if (localUri.getScheme() != null)
      {
        paramIntent = localUri;
        if (localUri.getScheme().equals("file"))
        {
          paramIntent = localUri;
          if (localObject != null)
          {
            paramIntent = localUri;
            if (((String)localObject).contains("image/"))
            {
              localObject = localUri.getEncodedPath();
              paramIntent = localUri;
              if (localObject != null)
              {
                paramIntent = Uri.decode((String)localObject);
                paramContext = paramContext.getContentResolver();
                localObject = new StringBuffer();
                ((StringBuffer)localObject).append("(");
                ((StringBuffer)localObject).append("_data");
                ((StringBuffer)localObject).append("=");
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("'");
                localStringBuilder.append(paramIntent);
                localStringBuilder.append("'");
                ((StringBuffer)localObject).append(localStringBuilder.toString());
                ((StringBuffer)localObject).append(")");
                paramIntent = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                i = 0;
                localObject = ((StringBuffer)localObject).toString();
                localObject = paramContext.query(paramIntent, new String[] { "_id" }, (String)localObject, null, null);
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      try
      {
        ((Cursor)localObject).moveToFirst();
        if (((Cursor)localObject).isAfterLast()) {
          break label327;
        }
        i = ((Cursor)localObject).getInt(((Cursor)localObject).getColumnIndex("_id"));
        ((Cursor)localObject).moveToNext();
        continue;
        paramContext = new StringBuilder();
        paramContext.append("content://media/external/images/media/");
        paramContext.append(i);
        paramIntent = Uri.parse(paramContext.toString());
        paramContext = localUri;
        if (paramIntent != null) {
          paramContext = paramIntent;
        }
        paramIntent = paramContext;
        return paramContext;
      }
      finally
      {
        if (localObject != null) {
          ((Cursor)localObject).close();
        }
      }
      return paramIntent;
      label327:
      if (i == 0) {
        paramContext = localUri;
      }
    }
  }
  
  public static ErrorEntity b(VolleyError paramVolleyError)
  {
    try
    {
      paramVolleyError = a(paramVolleyError);
      if (!TextUtils.isEmpty(paramVolleyError))
      {
        paramVolleyError = (ErrorEntity)JSON.parseObject(paramVolleyError, ErrorEntity.class);
        return paramVolleyError;
      }
    }
    catch (Exception paramVolleyError)
    {
      paramVolleyError.printStackTrace();
    }
    return null;
  }
  
  public static String b(Context paramContext)
  {
    if (!"mounted".equals(Environment.getExternalStorageState())) {
      return paramContext.getFilesDir().getAbsolutePath();
    }
    File localFile = paramContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    if (localFile != null) {
      return localFile.getAbsolutePath();
    }
    localFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    if (localFile != null) {
      return localFile.getAbsolutePath();
    }
    return paramContext.getFilesDir().getAbsolutePath();
  }
  
  public static String b(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramString.startsWith("@")))
    {
      try
      {
        Object localObject = o(paramString.substring(1));
        try
        {
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            paramString = ((String)localObject).replace("zQcN6aR4", "");
            return paramString;
          }
          return localObject;
        }
        catch (Exception localException2)
        {
          paramString = (String)localObject;
          localObject = localException2;
        }
        localException1.printStackTrace();
      }
      catch (Exception localException1) {}
      return paramString;
    }
    return paramString;
  }
  
  public static String b(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = "";
    if (!TextUtils.isEmpty(paramString2))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append(paramString2);
      localObject = ((StringBuilder)localObject).toString();
    }
    paramString2 = (String)localObject;
    if (!TextUtils.isEmpty(paramString3))
    {
      paramString2 = new StringBuilder();
      paramString2.append((String)localObject);
      paramString2.append(paramString3);
      paramString2 = paramString2.toString();
    }
    if (TextUtils.isEmpty(paramString1)) {
      return "";
    }
    if (!TextUtils.isEmpty(paramString2))
    {
      paramString3 = new StringBuilder();
      paramString3.append(paramString1);
      paramString3.append(" ");
      paramString3.append(paramString2);
      return paramString3.toString();
    }
    return paramString1;
  }
  
  public static Pattern b(List<String> paramList)
  {
    if (paramList != null)
    {
      if (paramList.isEmpty()) {
        return null;
      }
      StringBuilder localStringBuilder = new StringBuilder(paramList.size() * 3);
      localStringBuilder.append('(');
      int i = 0;
      while (i < paramList.size())
      {
        localStringBuilder.append(Pattern.quote((String)paramList.get(i)));
        localStringBuilder.append('|');
        i += 1;
      }
      localStringBuilder.replace(localStringBuilder.length() - 1, localStringBuilder.length(), ")");
      try
      {
        paramList = Pattern.compile(localStringBuilder.toString());
        return paramList;
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return null;
      }
    }
    return null;
  }
  
  public static void b(Activity paramActivity)
  {
    Object localObject = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = ((Window)localObject).getAttributes();
    localLayoutParams.flags |= 0x4000000;
    ((Window)localObject).setAttributes(localLayoutParams);
    localObject = new com.a.a.a(paramActivity);
    ((com.a.a.a)localObject).a(true);
    ((com.a.a.a)localObject).b(true);
    ((com.a.a.a)localObject).a(paramActivity.getResources().getColor(R.color.title));
  }
  
  public static void b(Context paramContext, long paramLong, String paramString)
  {
    b(paramContext, String.valueOf(paramLong), paramString);
  }
  
  public static void b(Context paramContext, View paramView)
  {
    paramContext = (InputMethodManager)paramContext.getSystemService("input_method");
    if ((paramContext != null) && (paramView != null)) {
      paramContext.showSoftInput(paramView, 2);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    a(paramContext, paramString, null, null, null);
  }
  
  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    AppService.b(paramContext, paramString1, paramString2);
  }
  
  public static boolean b(Context paramContext, List<ThumbModel> paramList)
  {
    Object localObject = new ArrayList();
    if (paramList == null) {
      return true;
    }
    paramList = paramList.iterator();
    int i;
    while (paramList.hasNext())
    {
      ThumbModel localThumbModel = (ThumbModel)paramList.next();
      if (!e(paramContext, localThumbModel.path))
      {
        paramList = paramContext.getResources();
        i = R.string.not_supportupload;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(x(paramContext));
        ((StringBuilder)localObject).append("");
        a(paramContext, paramList.getString(i, new Object[] { ((StringBuilder)localObject).toString() }));
        return false;
      }
      if ((localThumbModel != null) && (!TextUtils.isEmpty(localThumbModel.path))) {
        ((ArrayList)localObject).add(localThumbModel.path);
      }
    }
    if (!a(paramContext, (List)localObject))
    {
      paramList = paramContext.getResources();
      i = R.string.not_supportupload;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(d.E(paramContext));
      ((StringBuilder)localObject).append("");
      a(paramContext, paramList.getString(i, new Object[] { ((StringBuilder)localObject).toString() }));
      return false;
    }
    return true;
  }
  
  public static int c(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      paramContext = MediaStore.Images.Media.query(paramContext.getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_id", "_data" }, "(mime_type in (?, ?, ?)) AND _data = ? ", new String[] { "image/jpeg", "image/png", "image/gif", paramString }, " _id DESC");
      if (paramContext != null) {
        try
        {
          if (paramContext.moveToFirst())
          {
            int i = paramContext.getInt(0);
            if (paramContext != null) {
              paramContext.close();
            }
            return i;
          }
        }
        finally
        {
          break label113;
        }
      }
      if (paramContext != null) {
        paramContext.close();
      }
      return -1;
    }
    finally
    {
      paramContext = localObject;
      label113:
      if (paramContext != null) {
        paramContext.close();
      }
    }
  }
  
  public static String c(Context paramContext)
  {
    if (!"mounted".equals(Environment.getExternalStorageState()))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramContext.getCacheDir().getAbsolutePath());
      ((StringBuilder)localObject).append("/web_cache/");
      return ((StringBuilder)localObject).toString();
    }
    Object localObject = paramContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    if (localObject != null)
    {
      paramContext = new StringBuilder();
      paramContext.append(((File)localObject).getAbsolutePath());
      paramContext.append("/web_cache/");
      return paramContext.toString();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramContext.getCacheDir().getAbsolutePath());
    ((StringBuilder)localObject).append("/web_cache/");
    return ((StringBuilder)localObject).toString();
  }
  
  public static String c(String paramString)
    throws Exception
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    Object localObject2 = "T3qAL3Mh".getBytes();
    Object localObject1 = new IvParameterSpec("RCh2M8xE".getBytes());
    localObject2 = new SecretKeySpec((byte[])localObject2, "DES");
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    if (localCipher == null) {
      return paramString;
    }
    localCipher.init(1, (Key)localObject2, (AlgorithmParameterSpec)localObject1);
    paramString = localCipher.doFinal(paramString.getBytes());
    int i = 0;
    paramString = new String(Base64.encode(paramString, 0));
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    localObject1 = new StringBuffer();
    while (i < paramString.length())
    {
      int j = paramString.charAt(i);
      if ((j != 10) && (j != 13)) {
        ((StringBuffer)localObject1).append(paramString.subSequence(i, i + 1));
      }
      i += 1;
    }
    return ((StringBuffer)localObject1).toString();
  }
  
  public static void c(Activity paramActivity)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 21)
    {
      localObject = paramActivity.getWindow();
      ((Window)localObject).clearFlags(201326592);
      ((Window)localObject).getDecorView().setSystemUiVisibility(1792);
      ((Window)localObject).addFlags(Integer.MIN_VALUE);
      ((Window)localObject).setStatusBarColor(0);
      localObject = new com.a.a.a(paramActivity);
      ((com.a.a.a)localObject).a(true);
      ((com.a.a.a)localObject).c(R.color.transparent);
    }
    else if (Build.VERSION.SDK_INT == 19)
    {
      localObject = paramActivity.getWindow();
      WindowManager.LayoutParams localLayoutParams = ((Window)localObject).getAttributes();
      localLayoutParams.flags |= 0x4000000;
      ((Window)localObject).setAttributes(localLayoutParams);
      localObject = new com.a.a.a(paramActivity);
      ((com.a.a.a)localObject).a(true);
      ((com.a.a.a)localObject).b(true);
      ((com.a.a.a)localObject).a(paramActivity.getResources().getColor(R.color.half_transparent));
    }
    StatusBarTextColorHelper.a(paramActivity);
  }
  
  public static void c(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = new JSONObject();
      paramContext.put("old_device_id", paramString1);
      paramContext.put("new_device_id", paramString2);
      ai.a(BaseApplication.c(), "push_register_change", paramContext);
      return;
    }
    catch (org.json.JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean c()
  {
    long l = b();
    return (l < 0L) || (l >= 204800L);
  }
  
  public static boolean c(VolleyError paramVolleyError)
  {
    return (com.android.volley2.error.a.a(paramVolleyError)) || (com.android.volley2.error.a.b(paramVolleyError));
  }
  
  /* Error */
  public static int d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 398	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: iconst_1
    //   8: ireturn
    //   9: new 771	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   17: astore 4
    //   19: aload 4
    //   21: invokevirtual 775	java/io/File:exists	()Z
    //   24: ifne +5 -> 29
    //   27: iconst_2
    //   28: ireturn
    //   29: aload_0
    //   30: invokevirtual 1934	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   33: ldc_w 1936
    //   36: invokevirtual 1939	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   39: ifeq +186 -> 225
    //   42: new 869	java/io/FileInputStream
    //   45: dup
    //   46: aload 4
    //   48: invokespecial 872	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   51: astore_3
    //   52: aload_3
    //   53: astore_0
    //   54: aload_3
    //   55: invokevirtual 1942	java/io/FileInputStream:available	()I
    //   58: istore_1
    //   59: aload_3
    //   60: astore_0
    //   61: invokestatic 1171	com/allfootball/news/BaseApplication:c	()Lcom/allfootball/news/BaseApplication;
    //   64: invokestatic 1841	com/allfootball/news/util/e:x	(Landroid/content/Context;)I
    //   67: istore_2
    //   68: iload_1
    //   69: iload_2
    //   70: sipush 1024
    //   73: imul
    //   74: sipush 1024
    //   77: imul
    //   78: if_icmple +16 -> 94
    //   81: aload_3
    //   82: invokevirtual 892	java/io/FileInputStream:close	()V
    //   85: iconst_4
    //   86: ireturn
    //   87: astore_0
    //   88: aload_0
    //   89: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   92: iconst_4
    //   93: ireturn
    //   94: aload_3
    //   95: astore_0
    //   96: new 1944	android/graphics/BitmapFactory$Options
    //   99: dup
    //   100: invokespecial 1945	android/graphics/BitmapFactory$Options:<init>	()V
    //   103: astore 5
    //   105: aload_3
    //   106: astore_0
    //   107: aload 5
    //   109: iconst_1
    //   110: putfield 1948	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   113: aload_3
    //   114: astore_0
    //   115: aload 4
    //   117: invokevirtual 1039	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   120: aload 5
    //   122: invokestatic 1954	com/networkbench/agent/impl/instrumentation/NBSBitmapFactoryInstrumentation:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   125: pop
    //   126: aload_3
    //   127: astore_0
    //   128: aload 5
    //   130: getfield 1957	android/graphics/BitmapFactory$Options:outHeight	I
    //   133: istore_1
    //   134: iload_1
    //   135: sipush 1280
    //   138: if_icmple +16 -> 154
    //   141: aload_3
    //   142: invokevirtual 892	java/io/FileInputStream:close	()V
    //   145: iconst_5
    //   146: ireturn
    //   147: astore_0
    //   148: aload_0
    //   149: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   152: iconst_5
    //   153: ireturn
    //   154: aload_3
    //   155: invokevirtual 892	java/io/FileInputStream:close	()V
    //   158: iconst_0
    //   159: ireturn
    //   160: astore_0
    //   161: aload_0
    //   162: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   165: iconst_0
    //   166: ireturn
    //   167: astore 4
    //   169: goto +13 -> 182
    //   172: astore_3
    //   173: aconst_null
    //   174: astore_0
    //   175: goto +32 -> 207
    //   178: astore 4
    //   180: aconst_null
    //   181: astore_3
    //   182: aload_3
    //   183: astore_0
    //   184: aload 4
    //   186: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   189: aload_3
    //   190: ifnull +14 -> 204
    //   193: aload_3
    //   194: invokevirtual 892	java/io/FileInputStream:close	()V
    //   197: iconst_3
    //   198: ireturn
    //   199: astore_0
    //   200: aload_0
    //   201: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   204: iconst_3
    //   205: ireturn
    //   206: astore_3
    //   207: aload_0
    //   208: ifnull +15 -> 223
    //   211: aload_0
    //   212: invokevirtual 892	java/io/FileInputStream:close	()V
    //   215: goto +8 -> 223
    //   218: astore_0
    //   219: aload_0
    //   220: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   223: aload_3
    //   224: athrow
    //   225: iconst_0
    //   226: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	paramString	String
    //   58	81	1	i	int
    //   67	7	2	j	int
    //   51	104	3	localFileInputStream	java.io.FileInputStream
    //   172	1	3	localObject1	Object
    //   181	13	3	localObject2	Object
    //   206	18	3	localObject3	Object
    //   17	99	4	localFile	File
    //   167	1	4	localIOException1	IOException
    //   178	7	4	localIOException2	IOException
    //   103	26	5	localOptions	android.graphics.BitmapFactory.Options
    // Exception table:
    //   from	to	target	type
    //   81	85	87	java/io/IOException
    //   141	145	147	java/io/IOException
    //   154	158	160	java/io/IOException
    //   54	59	167	java/io/IOException
    //   61	68	167	java/io/IOException
    //   96	105	167	java/io/IOException
    //   107	113	167	java/io/IOException
    //   115	126	167	java/io/IOException
    //   128	134	167	java/io/IOException
    //   42	52	172	finally
    //   42	52	178	java/io/IOException
    //   193	197	199	java/io/IOException
    //   54	59	206	finally
    //   61	68	206	finally
    //   96	105	206	finally
    //   107	113	206	finally
    //   115	126	206	finally
    //   128	134	206	finally
    //   184	189	206	finally
    //   211	215	218	java/io/IOException
  }
  
  public static String d(Context paramContext)
  {
    if (!"mounted".equals(Environment.getExternalStorageState())) {
      return paramContext.getFilesDir().getAbsolutePath();
    }
    File localFile = paramContext.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    if (localFile != null) {
      return localFile.getAbsolutePath();
    }
    localFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    if (localFile != null) {
      return localFile.getAbsolutePath();
    }
    return paramContext.getFilesDir().getAbsolutePath();
  }
  
  public static void d(Context paramContext, String paramString)
  {
    if (!d.a("Notifi_Match", true)) {
      return;
    }
    new RemindDialog(paramContext, paramString, paramContext.getString(R.string.alt_attentsuceess), paramContext.getString(R.string.got_it)).show();
    d.b("Notifi_Match", false);
  }
  
  public static boolean d()
  {
    String str = Build.BRAND;
    return (!TextUtils.isEmpty(str)) && (str.toLowerCase().equals("xiaomi"));
  }
  
  public static int[] d(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return new int[] { localDisplayMetrics.widthPixels, localDisplayMetrics.heightPixels };
  }
  
  public static String e(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d(paramContext));
    localStringBuilder.append("/share_image/");
    paramContext = new File(localStringBuilder.toString());
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static boolean e()
  {
    String str = Build.BRAND;
    return (!TextUtils.isEmpty(str)) && (str.toLowerCase().equals("samsung"));
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return true;
    }
    long l2 = 0L;
    paramString = new File(paramString);
    long l1 = l2;
    try
    {
      if (!paramString.isDirectory())
      {
        l1 = t.a(paramString);
        l1 += 0L;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      l1 = l2;
    }
    int i;
    if ((float)l1 / 1024.0F > x(paramContext)) {
      i = 1;
    } else {
      i = 0;
    }
    return i == 0;
  }
  
  public static boolean e(String paramString)
  {
    boolean bool2 = TextUtils.isEmpty(paramString);
    boolean bool1 = false;
    if (bool2) {
      return false;
    }
    paramString = paramString.toLowerCase();
    if ((paramString.endsWith(".gif")) || (paramString.contains(".gif!")) || (paramString.contains(".gif-")) || (paramString.contains(".gif_"))) {
      bool1 = true;
    }
    return bool1;
  }
  
  public static String f()
  {
    if (TextUtils.isEmpty(b.c)) {
      b.c = d.ak(BaseApplication.c());
    }
    return b.c;
  }
  
  public static String f(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d(paramContext));
    localStringBuilder.append("/emoji/");
    paramContext = new File(localStringBuilder.toString());
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static String f(Context paramContext, String paramString)
    throws IOException
  {
    if ((paramContext != null) && (paramContext.getAssets() != null) && (!TextUtils.isEmpty(paramString)))
    {
      paramContext = paramContext.getAssets().open(paramString);
      paramString = new byte['Ѐ'];
      StringBuilder localStringBuilder = new StringBuilder();
      for (;;)
      {
        int i = paramContext.read(paramString);
        if (i == -1) {
          break;
        }
        localStringBuilder.append(new String(paramString, 0, i));
      }
      return localStringBuilder.toString();
    }
    return "";
  }
  
  public static String f(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    paramString = paramString.trim();
    if ((!paramString.contains("\n")) && (!paramString.contains("\r"))) {
      return paramString;
    }
    paramString = paramString.replaceAll("\r", "\n").split("\n");
    StringBuilder localStringBuilder1 = new StringBuilder();
    int i = 0;
    int j = paramString.length;
    while (i < j - 2)
    {
      if (!TextUtils.isEmpty(paramString[i]))
      {
        StringBuilder localStringBuilder2 = new StringBuilder();
        localStringBuilder2.append(paramString[i]);
        localStringBuilder2.append("\n");
        localStringBuilder1.append(localStringBuilder2.toString());
      }
      i += 1;
    }
    localStringBuilder1.append(paramString[(paramString.length - 1)]);
    return localStringBuilder1.toString();
  }
  
  public static String g(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d(paramContext));
    localStringBuilder.append("/template/");
    paramContext = new File(localStringBuilder.toString());
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static boolean g()
  {
    String str = Build.BRAND;
    return (!TextUtils.isEmpty(str)) && (str.equalsIgnoreCase("SMARTISAN"));
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    boolean bool2 = false;
    paramContext = paramContext.getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((paramString == null) || (!paramString.equals(localPackageInfo.packageName)));
    boolean bool1 = true;
    return bool1;
  }
  
  public static boolean g(String paramString)
  {
    return Pattern.compile(d.n(BaseApplication.c())).matcher(paramString).matches();
  }
  
  public static Uri h(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return Uri.parse("");
    }
    return Uri.parse(paramString);
  }
  
  public static String h()
  {
    return b.aa;
  }
  
  public static String h(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(d(paramContext));
    localStringBuilder.append("/music/");
    paramContext = new File(localStringBuilder.toString());
    if (!paramContext.exists()) {
      paramContext.mkdir();
    }
    return paramContext.getAbsolutePath();
  }
  
  public static void h(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.TEXT", paramString);
    localIntent.setType("text/plain");
    try
    {
      paramContext.startActivity(Intent.createChooser(localIntent, ""));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static long i(String paramString)
    throws ParseException
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return localSimpleDateFormat.parse(paramString).getTime() - System.currentTimeMillis();
  }
  
  public static SwitchLanguageItemEntity i(Context paramContext, String paramString)
  {
    paramContext = q(paramContext);
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getData().iterator();
    while (paramContext.hasNext())
    {
      SwitchLanguageItemEntity localSwitchLanguageItemEntity = (SwitchLanguageItemEntity)paramContext.next();
      if (paramString.equals(localSwitchLanguageItemEntity.getLocation_code())) {
        return localSwitchLanguageItemEntity;
      }
    }
    return null;
  }
  
  public static String i(Context paramContext)
  {
    if (TextUtils.isEmpty(b.u)) {
      b.u = j(paramContext);
    }
    return b.u;
  }
  
  public static void i()
  {
    if (!b.ag.isEmpty()) {
      return;
    }
    Object localObject = d.g();
    if ((localObject != null) && (!((Set)localObject).isEmpty()))
    {
      localObject = ((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str1 = (String)((Iterator)localObject).next();
        if (!TextUtils.isEmpty(str1))
        {
          String str2 = d.a(str1, null);
          if (!TextUtils.isEmpty(str2)) {
            b.ag.put(str1, str2);
          }
        }
      }
    }
  }
  
  public static Intent j(Context paramContext, String paramString)
  {
    return null;
  }
  
  private static String j()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public static String j(Context paramContext)
  {
    Object localObject2 = paramContext;
    if (paramContext == null) {
      localObject2 = BaseApplication.c();
    }
    paramContext = d.v((Context)localObject2);
    if (!TextUtils.isEmpty(paramContext))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("UUID: step 1:");
      ((StringBuilder)localObject1).append(paramContext);
      an.a("AppUtils", ((StringBuilder)localObject1).toString());
      return paramContext;
    }
    Object localObject3 = ((Context)localObject2).getSharedPreferences("allfootball", 0).getString("UUID", null);
    paramContext = (Context)localObject3;
    if (!TextUtils.isEmpty((CharSequence)localObject3))
    {
      localObject1 = localObject3;
      if (((String)localObject3).startsWith("@")) {
        localObject1 = b((String)localObject3);
      }
      paramContext = (Context)localObject1;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        paramContext = (Context)localObject1;
        if (((String)localObject1).equals("02:00:00:00:00:00")) {
          paramContext = null;
        }
      }
    }
    Object localObject1 = paramContext;
    if (TextUtils.isEmpty(paramContext)) {
      localObject1 = l((Context)localObject2);
    }
    paramContext = (Context)localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      paramContext = a();
    }
    localObject1 = paramContext;
    if (TextUtils.isEmpty(paramContext))
    {
      paramContext = UUID.randomUUID().toString().replaceAll("-", "");
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(paramContext);
      ((StringBuilder)localObject1).append(UUID.randomUUID().toString().replaceAll("-", ""));
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      paramContext = (Context)localObject1;
      if (((String)localObject1).length() > 7) {}
    }
    else
    {
      paramContext = a(20);
    }
    localObject1 = paramContext;
    Context localContext;
    try
    {
      if (!TextUtils.isEmpty(paramContext))
      {
        localObject1 = paramContext;
        if (!paramContext.startsWith("@"))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("@");
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramContext);
          ((StringBuilder)localObject3).append("zQcN6aR4");
          ((StringBuilder)localObject1).append(c(((StringBuilder)localObject3).toString()));
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      localContext = paramContext;
    }
    d.s((Context)localObject2, localContext);
    paramContext = new StringBuilder();
    paramContext.append("UUID: step 2:");
    paramContext.append(localContext);
    an.a("AppUtils", paramContext.toString());
    return localContext;
  }
  
  public static boolean j(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if (paramString.startsWith("p"))
    {
      paramString = paramString.substring(1);
      try
      {
        Integer.parseInt(paramString);
        return true;
      }
      catch (Exception paramString)
      {
        an.a("AppUtils", paramString.getMessage());
      }
    }
    return false;
  }
  
  private static String k()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(j());
    ((StringBuilder)localObject).append("/.allfootball");
    localObject = new File(((StringBuilder)localObject).toString());
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdir();
    }
    return ((File)localObject).getAbsolutePath();
  }
  
  public static String k(Context paramContext)
  {
    Object localObject2 = d.v(paramContext);
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = localObject2;
      if (!((String)localObject2).startsWith("@"))
      {
        localObject1 = a((String)localObject2);
        d.s(paramContext, (String)localObject1);
      }
      b.u = (String)localObject1;
      return localObject1;
    }
    String str = paramContext.getSharedPreferences("allfootball", 0).getString("UUID", null);
    Object localObject1 = str;
    if (!TextUtils.isEmpty(str))
    {
      localObject2 = str;
      if (str.startsWith("@")) {
        localObject2 = b(str);
      }
      localObject1 = localObject2;
      if (!TextUtils.isEmpty((CharSequence)localObject2))
      {
        localObject1 = localObject2;
        if (((String)localObject2).equals("02:00:00:00:00:00")) {
          localObject1 = null;
        }
      }
    }
    localObject2 = localObject1;
    Object localObject3;
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = l(paramContext);
      localObject2 = localObject1;
      try
      {
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject2 = localObject1;
          if (!((String)localObject1).startsWith("@")) {
            localObject2 = a((String)localObject1);
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localObject3 = localObject1;
      }
    }
    b.u = localObject3;
    d.s(paramContext, localObject3);
    return localObject3;
  }
  
  /* Error */
  public static String k(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_1
    //   3: ldc_w 2210
    //   6: invokevirtual 481	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   9: ifeq +19 -> 28
    //   12: aload_1
    //   13: aload_1
    //   14: ldc_w 2212
    //   17: invokevirtual 491	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   20: aload_1
    //   21: invokevirtual 498	java/lang/String:length	()I
    //   24: invokevirtual 495	java/lang/String:substring	(II)Ljava/lang/String;
    //   27: astore_3
    //   28: iconst_0
    //   29: istore_2
    //   30: aload_3
    //   31: iconst_0
    //   32: invokestatic 2215	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   35: astore 6
    //   37: iload_2
    //   38: aload 6
    //   40: arraylength
    //   41: if_icmpge +30 -> 71
    //   44: aload 6
    //   46: iload_2
    //   47: baload
    //   48: ifge +16 -> 64
    //   51: aload 6
    //   53: iload_2
    //   54: aload 6
    //   56: iload_2
    //   57: baload
    //   58: sipush 256
    //   61: iadd
    //   62: i2b
    //   63: bastore
    //   64: iload_2
    //   65: iconst_1
    //   66: iadd
    //   67: istore_2
    //   68: goto -31 -> 37
    //   71: aconst_null
    //   72: astore 4
    //   74: aconst_null
    //   75: astore 5
    //   77: aconst_null
    //   78: astore_3
    //   79: aload_3
    //   80: astore_1
    //   81: new 380	java/lang/StringBuilder
    //   84: dup
    //   85: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   88: astore 7
    //   90: aload_3
    //   91: astore_1
    //   92: aload 7
    //   94: aload_0
    //   95: invokestatic 2217	com/allfootball/news/util/e:e	(Landroid/content/Context;)Ljava/lang/String;
    //   98: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload_3
    //   103: astore_1
    //   104: aload 7
    //   106: ldc_w 916
    //   109: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload_3
    //   114: astore_1
    //   115: aload 7
    //   117: invokestatic 2117	java/lang/System:currentTimeMillis	()J
    //   120: invokevirtual 1144	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   123: pop
    //   124: aload_3
    //   125: astore_1
    //   126: aload 7
    //   128: ldc_w 2219
    //   131: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload_3
    //   136: astore_1
    //   137: aload 7
    //   139: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   142: astore 7
    //   144: aload_3
    //   145: astore_1
    //   146: new 923	java/io/FileOutputStream
    //   149: dup
    //   150: aload 7
    //   152: invokespecial 1016	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   155: astore_0
    //   156: aload_0
    //   157: aload 6
    //   159: invokevirtual 2221	java/io/OutputStream:write	([B)V
    //   162: aload_0
    //   163: invokevirtual 1562	java/io/OutputStream:flush	()V
    //   166: aload_0
    //   167: invokevirtual 1027	java/io/OutputStream:close	()V
    //   170: aload 7
    //   172: areturn
    //   173: astore_0
    //   174: aload_0
    //   175: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   178: aload 7
    //   180: areturn
    //   181: astore_3
    //   182: aload_0
    //   183: astore_1
    //   184: aload_3
    //   185: astore_0
    //   186: goto +77 -> 263
    //   189: astore_3
    //   190: goto +15 -> 205
    //   193: astore_3
    //   194: goto +42 -> 236
    //   197: astore_0
    //   198: goto +65 -> 263
    //   201: astore_3
    //   202: aload 4
    //   204: astore_0
    //   205: aload_0
    //   206: astore_1
    //   207: aload_3
    //   208: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   211: aload_0
    //   212: ifnull +16 -> 228
    //   215: aload_0
    //   216: invokevirtual 1027	java/io/OutputStream:close	()V
    //   219: ldc_w 894
    //   222: areturn
    //   223: astore_0
    //   224: aload_0
    //   225: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   228: ldc_w 894
    //   231: areturn
    //   232: astore_3
    //   233: aload 5
    //   235: astore_0
    //   236: aload_0
    //   237: astore_1
    //   238: aload_3
    //   239: invokevirtual 2222	java/io/FileNotFoundException:printStackTrace	()V
    //   242: aload_0
    //   243: ifnull +16 -> 259
    //   246: aload_0
    //   247: invokevirtual 1027	java/io/OutputStream:close	()V
    //   250: ldc_w 894
    //   253: areturn
    //   254: astore_0
    //   255: aload_0
    //   256: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   259: ldc_w 894
    //   262: areturn
    //   263: aload_1
    //   264: ifnull +15 -> 279
    //   267: aload_1
    //   268: invokevirtual 1027	java/io/OutputStream:close	()V
    //   271: goto +8 -> 279
    //   274: astore_1
    //   275: aload_1
    //   276: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   279: aload_0
    //   280: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	281	0	paramContext	Context
    //   0	281	1	paramString	String
    //   29	39	2	i	int
    //   1	144	3	str	String
    //   181	4	3	localObject1	Object
    //   189	1	3	localIOException1	IOException
    //   193	1	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   201	7	3	localIOException2	IOException
    //   232	7	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   72	131	4	localObject2	Object
    //   75	159	5	localObject3	Object
    //   35	123	6	arrayOfByte	byte[]
    //   88	91	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   166	170	173	java/io/IOException
    //   156	166	181	finally
    //   156	166	189	java/io/IOException
    //   156	166	193	java/io/FileNotFoundException
    //   81	90	197	finally
    //   92	102	197	finally
    //   104	113	197	finally
    //   115	124	197	finally
    //   126	135	197	finally
    //   137	144	197	finally
    //   146	156	197	finally
    //   207	211	197	finally
    //   238	242	197	finally
    //   81	90	201	java/io/IOException
    //   92	102	201	java/io/IOException
    //   104	113	201	java/io/IOException
    //   115	124	201	java/io/IOException
    //   126	135	201	java/io/IOException
    //   137	144	201	java/io/IOException
    //   146	156	201	java/io/IOException
    //   215	219	223	java/io/IOException
    //   81	90	232	java/io/FileNotFoundException
    //   92	102	232	java/io/FileNotFoundException
    //   104	113	232	java/io/FileNotFoundException
    //   115	124	232	java/io/FileNotFoundException
    //   126	135	232	java/io/FileNotFoundException
    //   137	144	232	java/io/FileNotFoundException
    //   146	156	232	java/io/FileNotFoundException
    //   246	250	254	java/io/IOException
    //   267	271	274	java/io/IOException
  }
  
  public static String k(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    int i = paramString.lastIndexOf(".");
    if (i == -1) {
      return "";
    }
    paramString = paramString.substring(i + 1);
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return paramString.toLowerCase();
  }
  
  public static String l(Context paramContext)
  {
    paramContext = Settings.System.getString(paramContext.getContentResolver(), "android_id");
    String str = Build.SERIAL;
    if ((!TextUtils.isEmpty(paramContext)) && (!TextUtils.isEmpty(str)) && (!paramContext.equals("9774d56d682e549c")))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext);
      localStringBuilder.append(str);
      return UUID.nameUUIDFromBytes(localStringBuilder.toString().getBytes()).toString();
    }
    if ((!TextUtils.isEmpty(paramContext)) && (!paramContext.equals("9774d56d682e549c"))) {
      return UUID.nameUUIDFromBytes(paramContext.getBytes()).toString();
    }
    if (!TextUtils.isEmpty(str)) {
      return UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }
    return null;
  }
  
  public static String[] l(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return new String[] { "", "" };
    }
    Object localObject2 = paramString.trim();
    if (((String)localObject2).startsWith("/")) {
      return l(((String)localObject2).substring(1));
    }
    if (((String)localObject2).endsWith("/")) {
      return l(((String)localObject2).substring(0, ((String)localObject2).length() - 1));
    }
    String str = "";
    int i = ((String)localObject2).indexOf("/");
    paramString = str;
    Object localObject1 = localObject2;
    if (i != -1) {
      if (i >= ((String)localObject2).length() - 1)
      {
        paramString = str;
        localObject1 = localObject2;
      }
      else
      {
        localObject1 = ((String)localObject2).substring(0, i);
        paramString = ((String)localObject2).substring(i + 1);
      }
    }
    localObject2 = localObject1;
    if (!TextUtils.isEmpty((CharSequence)localObject1))
    {
      for (localObject2 = ((String)localObject1).trim();; localObject2 = ((String)localObject2).substring(1))
      {
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2)) {
          break;
        }
        localObject1 = localObject2;
        if (!((String)localObject2).startsWith("/")) {
          break;
        }
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break;
        }
        localObject2 = localObject1;
        if (!((String)localObject1).endsWith("/")) {
          break;
        }
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
    }
    localObject1 = paramString;
    if (!TextUtils.isEmpty(paramString))
    {
      for (localObject1 = paramString.trim();; localObject1 = ((String)localObject1).substring(1))
      {
        paramString = (String)localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break;
        }
        paramString = (String)localObject1;
        if (!((String)localObject1).startsWith("/")) {
          break;
        }
      }
      for (;;)
      {
        localObject1 = paramString;
        if (TextUtils.isEmpty(paramString)) {
          break;
        }
        localObject1 = paramString;
        if (!paramString.endsWith("/")) {
          break;
        }
        paramString = paramString.substring(0, paramString.length() - 1);
      }
    }
    return new String[] { localObject2, localObject1 };
  }
  
  public static int m(String paramString)
  {
    try
    {
      int i = new ExifInterface(paramString).getAttributeInt("Orientation", 1);
      if (i != 3)
      {
        if (i != 6)
        {
          if (i != 8) {
            return 0;
          }
          return 270;
        }
        return 90;
      }
      return 180;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public static Header[] m(Context paramContext)
  {
    Object localObject1 = paramContext;
    if (paramContext == null) {
      localObject1 = BaseApplication.c();
    }
    paramContext = com.allfootball.news.db.a.j((Context)localObject1);
    if (paramContext != null) {
      paramContext = paramContext.getAccess_token();
    } else {
      paramContext = "";
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Authorization:");
    ((StringBuilder)localObject2).append(paramContext);
    an.a("AppUtils", ((StringBuilder)localObject2).toString());
    localObject2 = new BasicHeader("Pragma", "no-cache");
    BasicHeader localBasicHeader1 = new BasicHeader("Cache-Control", "no-cache");
    BasicHeader localBasicHeader2 = new BasicHeader("charset", "UTF-8");
    paramContext = new BasicHeader("Authorization", paramContext);
    BasicHeader localBasicHeader3 = new BasicHeader("UUID", i((Context)localObject1));
    BasicHeader localBasicHeader4 = new BasicHeader("User-Agent", n((Context)localObject1));
    BasicHeader localBasicHeader5 = new BasicHeader("lang", aa.b((Context)localObject1));
    BasicHeader localBasicHeader6 = new BasicHeader("Platform", "Android");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(E((Context)localObject1));
    localStringBuilder.append("");
    return new Header[] { localObject2, localBasicHeader1, localBasicHeader2, paramContext, localBasicHeader3, localBasicHeader4, localBasicHeader5, localBasicHeader6, new BasicHeader("Version", localStringBuilder.toString()), new BasicHeader("App", ""), new BasicHeader("Version-Name", "3.0.9"), new BasicHeader("Package", ((Context)localObject1).getPackageName()), new BasicHeader("fingerprint", h()) };
  }
  
  public static String n(Context paramContext)
  {
    if (TextUtils.isEmpty(b.w)) {
      b.w = o(paramContext);
    }
    return b.w;
  }
  
  public static void n(String paramString)
  {
    MobclickAgent.onEvent(BaseApplication.c(), paramString);
  }
  
  public static String o(Context paramContext)
  {
    Object localObject2 = d.t(paramContext);
    Object localObject1 = localObject2;
    String str;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      if (Build.VERSION.SDK_INT >= 17) {
        try
        {
          localObject1 = WebSettings.getDefaultUserAgent(paramContext);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          str = System.getProperty("http.agent");
        }
      } else {
        str = System.getProperty("http.agent");
      }
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(str);
    ((StringBuilder)localObject2).append(p(paramContext));
    return ((StringBuilder)localObject2).toString();
  }
  
  public static String o(String paramString)
    throws Exception
  {
    Object localObject = "T3qAL3Mh".getBytes();
    IvParameterSpec localIvParameterSpec = new IvParameterSpec("RCh2M8xE".getBytes());
    localObject = new SecretKeySpec((byte[])localObject, "DES");
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(2, (Key)localObject, localIvParameterSpec);
    return new String(localCipher.doFinal(Base64.decode(paramString.getBytes(), 0)));
  }
  
  public static String p(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" NewsApp/");
    localStringBuilder.append(b.m);
    localStringBuilder.append(" Android/");
    localStringBuilder.append(b.m);
    localStringBuilder.append(" AppName/");
    localStringBuilder.append(b.n);
    localStringBuilder.append(" SDK/");
    localStringBuilder.append(Build.VERSION.SDK_INT);
    localStringBuilder.append(" PackageName/");
    localStringBuilder.append(paramContext.getPackageName());
    return localStringBuilder.toString();
  }
  
  public static String p(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    String[] arrayOfString = paramString.split("\n");
    if (arrayOfString.length == 1) {
      return paramString;
    }
    if (arrayOfString.length > 1)
    {
      paramString = new StringBuilder();
      int i = 0;
      while (i < arrayOfString.length)
      {
        paramString.append(arrayOfString[i]);
        if ((i != arrayOfString.length - 1) && (!paramString.toString().endsWith("<br/>"))) {
          paramString.append("<br/>");
        }
        i += 1;
      }
      return paramString.toString();
    }
    return paramString;
  }
  
  public static SwitchLanguageEntity q(Context paramContext)
  {
    Object localObject3 = null;
    Object localObject2 = null;
    for (;;)
    {
      try
      {
        SwitchLanguageEntity localSwitchLanguageEntity;
        if (TextUtils.isEmpty(d.aA(paramContext)))
        {
          paramContext = (SwitchLanguageEntity)JSON.parseObject(f(paramContext, "location.json"), SwitchLanguageEntity.class);
        }
        else
        {
          localSwitchLanguageEntity = (SwitchLanguageEntity)JSON.parseObject(d.aA(paramContext), SwitchLanguageEntity.class);
          if (localSwitchLanguageEntity == null) {}
        }
        localJSONException1.printStackTrace();
      }
      catch (IOException localIOException1)
      {
        try
        {
          if (localSwitchLanguageEntity.getData() != null) {
            if (!localSwitchLanguageEntity.getData().isEmpty()) {
              break label111;
            }
          }
          paramContext = (SwitchLanguageEntity)JSON.parseObject(f(paramContext, "location.json"), SwitchLanguageEntity.class);
          return paramContext;
        }
        catch (IOException localIOException2)
        {
          paramContext = localJSONException1;
          localObject1 = localIOException2;
          continue;
        }
        catch (com.alibaba.json.JSONException localJSONException2)
        {
          for (;;)
          {
            paramContext = (Context)localObject1;
            Object localObject1 = localJSONException2;
          }
        }
        localIOException1 = localIOException1;
        paramContext = localObject2;
        localIOException1.printStackTrace();
        return paramContext;
      }
      catch (com.alibaba.json.JSONException localJSONException1)
      {
        paramContext = localObject3;
      }
      return paramContext;
      label111:
      paramContext = localJSONException1;
    }
  }
  
  public static boolean q(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramString = Uri.parse(paramString).getHost();
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return Pattern.compile(".*(dongqiudi.com|dongqiudi.net|allfootballapp.com|xiaohongdan.com)").matcher(paramString).matches();
  }
  
  public static Map<String, String> r(Context paramContext)
  {
    Object localObject1 = paramContext;
    if (paramContext == null) {
      localObject1 = BaseApplication.c();
    }
    paramContext = com.allfootball.news.db.a.j((Context)localObject1);
    if (paramContext != null) {
      paramContext = paramContext.getAccess_token();
    } else {
      paramContext = "";
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Authorization:");
    ((StringBuilder)localObject2).append(paramContext);
    an.a("AppUtils", ((StringBuilder)localObject2).toString());
    localObject2 = new HashMap();
    ((Map)localObject2).put("Pragma", "no-cache");
    ((Map)localObject2).put("Cache-Control", "no-cache");
    ((Map)localObject2).put("charset", "UTF-8");
    ((Map)localObject2).put("Authorization", paramContext);
    ((Map)localObject2).put("UUID", i((Context)localObject1));
    ((Map)localObject2).put("User-Agent", n((Context)localObject1));
    ((Map)localObject2).put("lang", aa.b((Context)localObject1));
    ((Map)localObject2).put("Platform", "Android");
    paramContext = new StringBuilder();
    paramContext.append(E((Context)localObject1));
    paramContext.append("");
    ((Map)localObject2).put("Version", paramContext.toString());
    ((Map)localObject2).put("App", "");
    ((Map)localObject2).put("Version-Name", "3.0.9");
    ((Map)localObject2).put("Package", ((Context)localObject1).getPackageName());
    ((Map)localObject2).put("language", aa.a(BaseApplication.c()));
    ((Map)localObject2).put("fingerprint", h());
    return localObject2;
  }
  
  public static boolean r(String paramString)
  {
    Object localObject = d.V(BaseApplication.c());
    if (localObject != null)
    {
      if (!((Map)localObject).containsKey(paramString)) {
        return false;
      }
      paramString = (String)((Map)localObject).get(paramString);
      try
      {
        paramString = (List)JSON.parseObject(paramString, new TypeReference() {}.getType(), new Feature[0]);
      }
      catch (com.alibaba.json.JSONException paramString)
      {
        paramString.printStackTrace();
        paramString = null;
      }
      if (paramString != null)
      {
        if (paramString.isEmpty()) {
          return false;
        }
        paramString = paramString.iterator();
        while (paramString.hasNext())
        {
          localObject = (String)paramString.next();
          if ((!TextUtils.isEmpty((CharSequence)localObject)) && (!new File((String)localObject).exists())) {
            return false;
          }
        }
        return true;
      }
      return false;
    }
    return false;
  }
  
  public static String s(String paramString)
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("@");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("zQcN6aR4");
      ((StringBuilder)localObject).append(c(localStringBuilder.toString()));
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  public static void s(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      paramContext = (InputMethodManager)paramContext.getApplicationContext().getSystemService("input_method");
      if (paramContext == null) {
        return;
      }
      Field localField1 = paramContext.getClass().getDeclaredField("mCurRootView");
      Field localField2 = paramContext.getClass().getDeclaredField("mServedView");
      Field localField3 = paramContext.getClass().getDeclaredField("mNextServedView");
      if (!localField1.isAccessible()) {
        localField1.setAccessible(true);
      }
      if (localField1.get(paramContext) != null) {
        localField1.set(paramContext, null);
      }
      if (!localField2.isAccessible()) {
        localField2.setAccessible(true);
      }
      if (localField2.get(paramContext) != null) {
        localField2.set(paramContext, null);
      }
      if (!localField3.isAccessible()) {
        localField3.setAccessible(true);
      }
      if (localField3.get(paramContext) != null)
      {
        localField3.set(paramContext, null);
        return;
      }
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static String t(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext == null) {
      return "No Tel Manager";
    }
    int i = paramContext.getNetworkType();
    switch (i)
    {
    case 7: 
    default: 
      paramContext = new StringBuilder();
      paramContext.append("unknow:");
      paramContext.append(i);
      return paramContext.toString();
    case 8: 
      return "联通3g:NETWORK_TYPE_HSDPA";
    case 6: 
      return "电信3g:NETWORK_TYPE_EVDO_A";
    case 5: 
      return "电信3g:NETWORK_TYPE_EVDO_0";
    case 4: 
      return "电信2g:NETWORK_TYPE_CDMA";
    case 3: 
      return "联通3g:NETWORK_TYPE_UMTS";
    case 2: 
      return "移动或者联通2g:NETWORK_TYPE_EDGE";
    }
    return "移动或者联通2g:NETWORK_TYPE_GPRS";
  }
  
  public static boolean t(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    return (paramString.startsWith("af")) && (paramString.length() == 8);
  }
  
  /* Error */
  public static String u(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 398	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: new 771	java/io/File
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   17: astore_0
    //   18: aload_0
    //   19: invokevirtual 775	java/io/File:exists	()Z
    //   22: ifne +5 -> 27
    //   25: aconst_null
    //   26: areturn
    //   27: new 869	java/io/FileInputStream
    //   30: dup
    //   31: aload_0
    //   32: invokespecial 872	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   35: astore_2
    //   36: aload_2
    //   37: astore_0
    //   38: sipush 1024
    //   41: newarray byte
    //   43: astore_3
    //   44: aload_2
    //   45: astore_0
    //   46: new 380	java/lang/StringBuilder
    //   49: dup
    //   50: ldc_w 894
    //   53: invokespecial 2424	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   56: astore 4
    //   58: aload_2
    //   59: astore_0
    //   60: aload_2
    //   61: aload_3
    //   62: invokevirtual 2425	java/io/FileInputStream:read	([B)I
    //   65: istore_1
    //   66: iload_1
    //   67: ifle +24 -> 91
    //   70: aload_2
    //   71: astore_0
    //   72: aload 4
    //   74: new 13	java/lang/String
    //   77: dup
    //   78: aload_3
    //   79: iconst_0
    //   80: iload_1
    //   81: invokespecial 2026	java/lang/String:<init>	([BII)V
    //   84: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: pop
    //   88: goto -30 -> 58
    //   91: aload_2
    //   92: astore_0
    //   93: aload 4
    //   95: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: astore_3
    //   99: aload_2
    //   100: invokevirtual 892	java/io/FileInputStream:close	()V
    //   103: aload_3
    //   104: areturn
    //   105: astore_0
    //   106: aload_0
    //   107: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   110: aload_3
    //   111: areturn
    //   112: astore_3
    //   113: goto +12 -> 125
    //   116: astore_0
    //   117: aconst_null
    //   118: astore_2
    //   119: goto +34 -> 153
    //   122: astore_3
    //   123: aconst_null
    //   124: astore_2
    //   125: aload_2
    //   126: astore_0
    //   127: aload_3
    //   128: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   131: aload_2
    //   132: ifnull +14 -> 146
    //   135: aload_2
    //   136: invokevirtual 892	java/io/FileInputStream:close	()V
    //   139: aconst_null
    //   140: areturn
    //   141: astore_0
    //   142: aload_0
    //   143: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   146: aconst_null
    //   147: areturn
    //   148: astore_3
    //   149: aload_0
    //   150: astore_2
    //   151: aload_3
    //   152: astore_0
    //   153: aload_2
    //   154: ifnull +15 -> 169
    //   157: aload_2
    //   158: invokevirtual 892	java/io/FileInputStream:close	()V
    //   161: goto +8 -> 169
    //   164: astore_2
    //   165: aload_2
    //   166: invokevirtual 605	java/lang/Exception:printStackTrace	()V
    //   169: aload_0
    //   170: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	171	0	paramString	String
    //   65	16	1	i	int
    //   35	123	2	localObject1	Object
    //   164	2	2	localException1	Exception
    //   43	68	3	localObject2	Object
    //   112	1	3	localException2	Exception
    //   122	6	3	localException3	Exception
    //   148	4	3	localObject3	Object
    //   56	38	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   99	103	105	java/lang/Exception
    //   38	44	112	java/lang/Exception
    //   46	58	112	java/lang/Exception
    //   60	66	112	java/lang/Exception
    //   72	88	112	java/lang/Exception
    //   93	99	112	java/lang/Exception
    //   27	36	116	finally
    //   27	36	122	java/lang/Exception
    //   135	139	141	java/lang/Exception
    //   38	44	148	finally
    //   46	58	148	finally
    //   60	66	148	finally
    //   72	88	148	finally
    //   93	99	148	finally
    //   127	131	148	finally
    //   157	161	164	java/lang/Exception
  }
  
  public static boolean u(Context paramContext)
  {
    return (d.c(paramContext)) && (F(paramContext) != 0);
  }
  
  public static String v(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return paramString;
  }
  
  public static boolean v(Context paramContext)
  {
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    paramContext = com.allfootball.news.db.a.j(paramContext);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (!TextUtils.isEmpty(paramContext.getUsername()))
      {
        bool1 = bool2;
        if (paramContext.getId() != 0L) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static UserEntity w(Context paramContext)
  {
    paramContext = com.allfootball.news.db.a.j(paramContext);
    if (a(paramContext)) {
      return paramContext;
    }
    return null;
  }
  
  public static String w(String paramString)
  {
    Object localObject1 = paramString;
    if (q(paramString))
    {
      localObject1 = Uri.parse(paramString);
      Object localObject2 = Uri.parse(paramString).buildUpon();
      if (localObject2 != null)
      {
        if (!((Uri)localObject1).getQueryParameterNames().contains("language")) {
          ((Uri.Builder)localObject2).appendQueryParameter("language", aa.a(BaseApplication.c()));
        }
        if (!TextUtils.isEmpty("")) {
          ((Uri.Builder)localObject2).appendQueryParameter("package", "");
        }
        return ((Uri.Builder)localObject2).build().toString();
      }
      if ((((Uri)localObject1).getQueryParameterNames() != null) && (!((Uri)localObject1).getQueryParameterNames().isEmpty()))
      {
        localObject2 = paramString;
        if (!((Uri)localObject1).getQueryParameterNames().contains("language"))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString);
          ((StringBuilder)localObject1).append("&language=");
          ((StringBuilder)localObject1).append(aa.a(BaseApplication.c()));
          localObject2 = ((StringBuilder)localObject1).toString();
        }
        localObject1 = localObject2;
        if (!TextUtils.isEmpty(""))
        {
          paramString = new StringBuilder();
          paramString.append((String)localObject2);
          paramString.append("&package=");
          paramString.append("");
          return paramString.toString();
        }
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramString);
        ((StringBuilder)localObject1).append("?language=");
        ((StringBuilder)localObject1).append(aa.a(BaseApplication.c()));
        paramString = ((StringBuilder)localObject1).toString();
        localObject1 = paramString;
        if (!TextUtils.isEmpty(""))
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString);
          ((StringBuilder)localObject1).append("&package=");
          ((StringBuilder)localObject1).append("");
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
    }
    return localObject1;
  }
  
  public static int x(Context paramContext)
  {
    if (b.g == 0) {
      b.g = d.F(paramContext);
    }
    return b.g;
  }
  
  /* Error */
  public static byte[] x(String paramString)
  {
    // Byte code:
    //   0: new 869	java/io/FileInputStream
    //   3: dup
    //   4: new 771	java/io/File
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 772	java/io/File:<init>	(Ljava/lang/String;)V
    //   12: invokespecial 872	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   15: astore_0
    //   16: new 2465	java/io/ByteArrayOutputStream
    //   19: dup
    //   20: invokespecial 2466	java/io/ByteArrayOutputStream:<init>	()V
    //   23: astore 4
    //   25: aload 4
    //   27: astore_2
    //   28: aload_0
    //   29: astore_3
    //   30: sipush 1024
    //   33: newarray byte
    //   35: astore 5
    //   37: aload 4
    //   39: astore_2
    //   40: aload_0
    //   41: astore_3
    //   42: aload_0
    //   43: aload 5
    //   45: invokevirtual 2425	java/io/FileInputStream:read	([B)I
    //   48: istore_1
    //   49: iload_1
    //   50: iconst_m1
    //   51: if_icmpeq +20 -> 71
    //   54: aload 4
    //   56: astore_2
    //   57: aload_0
    //   58: astore_3
    //   59: aload 4
    //   61: aload 5
    //   63: iconst_0
    //   64: iload_1
    //   65: invokevirtual 2467	java/io/ByteArrayOutputStream:write	([BII)V
    //   68: goto -31 -> 37
    //   71: aload 4
    //   73: astore_2
    //   74: aload_0
    //   75: astore_3
    //   76: aload 4
    //   78: invokevirtual 2470	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   81: astore 5
    //   83: aload_0
    //   84: invokevirtual 892	java/io/FileInputStream:close	()V
    //   87: aload 4
    //   89: invokevirtual 2471	java/io/ByteArrayOutputStream:close	()V
    //   92: goto +8 -> 100
    //   95: astore_0
    //   96: aload_0
    //   97: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   100: aload 5
    //   102: areturn
    //   103: astore 5
    //   105: goto +45 -> 150
    //   108: astore 5
    //   110: goto +77 -> 187
    //   113: astore_2
    //   114: aconst_null
    //   115: astore_3
    //   116: goto +119 -> 235
    //   119: astore 5
    //   121: aconst_null
    //   122: astore 4
    //   124: goto +26 -> 150
    //   127: astore 5
    //   129: aconst_null
    //   130: astore 4
    //   132: goto +55 -> 187
    //   135: astore_2
    //   136: aconst_null
    //   137: astore_3
    //   138: aload_3
    //   139: astore_0
    //   140: goto +95 -> 235
    //   143: astore 5
    //   145: aconst_null
    //   146: astore_0
    //   147: aload_0
    //   148: astore 4
    //   150: aload 4
    //   152: astore_2
    //   153: aload_0
    //   154: astore_3
    //   155: aload 5
    //   157: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   160: aload_0
    //   161: ifnull +7 -> 168
    //   164: aload_0
    //   165: invokevirtual 892	java/io/FileInputStream:close	()V
    //   168: aload 4
    //   170: ifnull +54 -> 224
    //   173: aload 4
    //   175: invokevirtual 2471	java/io/ByteArrayOutputStream:close	()V
    //   178: aconst_null
    //   179: areturn
    //   180: astore 5
    //   182: aconst_null
    //   183: astore_0
    //   184: aload_0
    //   185: astore 4
    //   187: aload 4
    //   189: astore_2
    //   190: aload_0
    //   191: astore_3
    //   192: aload 5
    //   194: invokevirtual 2222	java/io/FileNotFoundException:printStackTrace	()V
    //   197: aload_0
    //   198: ifnull +10 -> 208
    //   201: aload_0
    //   202: invokevirtual 892	java/io/FileInputStream:close	()V
    //   205: goto +3 -> 208
    //   208: aload 4
    //   210: ifnull +14 -> 224
    //   213: aload 4
    //   215: invokevirtual 2471	java/io/ByteArrayOutputStream:close	()V
    //   218: aconst_null
    //   219: areturn
    //   220: aload_0
    //   221: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   224: aconst_null
    //   225: areturn
    //   226: astore 4
    //   228: aload_3
    //   229: astore_0
    //   230: aload_2
    //   231: astore_3
    //   232: aload 4
    //   234: astore_2
    //   235: aload_0
    //   236: ifnull +10 -> 246
    //   239: aload_0
    //   240: invokevirtual 892	java/io/FileInputStream:close	()V
    //   243: goto +3 -> 246
    //   246: aload_3
    //   247: ifnull +14 -> 261
    //   250: aload_3
    //   251: invokevirtual 2471	java/io/ByteArrayOutputStream:close	()V
    //   254: goto +7 -> 261
    //   257: aload_0
    //   258: invokevirtual 204	java/io/IOException:printStackTrace	()V
    //   261: aload_2
    //   262: athrow
    //   263: astore_0
    //   264: goto -44 -> 220
    //   267: astore_0
    //   268: goto -11 -> 257
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	271	0	paramString	String
    //   48	17	1	i	int
    //   27	47	2	localObject1	Object
    //   113	1	2	localObject2	Object
    //   135	1	2	localObject3	Object
    //   152	110	2	localObject4	Object
    //   29	222	3	localObject5	Object
    //   23	191	4	localObject6	Object
    //   226	7	4	localObject7	Object
    //   35	66	5	arrayOfByte	byte[]
    //   103	1	5	localIOException1	IOException
    //   108	1	5	localFileNotFoundException1	java.io.FileNotFoundException
    //   119	1	5	localIOException2	IOException
    //   127	1	5	localFileNotFoundException2	java.io.FileNotFoundException
    //   143	13	5	localIOException3	IOException
    //   180	13	5	localFileNotFoundException3	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   83	92	95	java/io/IOException
    //   30	37	103	java/io/IOException
    //   42	49	103	java/io/IOException
    //   59	68	103	java/io/IOException
    //   76	83	103	java/io/IOException
    //   30	37	108	java/io/FileNotFoundException
    //   42	49	108	java/io/FileNotFoundException
    //   59	68	108	java/io/FileNotFoundException
    //   76	83	108	java/io/FileNotFoundException
    //   16	25	113	finally
    //   16	25	119	java/io/IOException
    //   16	25	127	java/io/FileNotFoundException
    //   0	16	135	finally
    //   0	16	143	java/io/IOException
    //   0	16	180	java/io/FileNotFoundException
    //   30	37	226	finally
    //   42	49	226	finally
    //   59	68	226	finally
    //   76	83	226	finally
    //   155	160	226	finally
    //   192	197	226	finally
    //   164	168	263	java/io/IOException
    //   173	178	263	java/io/IOException
    //   201	205	263	java/io/IOException
    //   213	218	263	java/io/IOException
    //   239	243	267	java/io/IOException
    //   250	254	267	java/io/IOException
  }
  
  public static int y(Context paramContext)
  {
    if (b.h == 0) {
      b.h = d.E(paramContext);
    }
    return b.h;
  }
  
  public static String z(Context paramContext)
  {
    paramContext = (ClipboardManager)paramContext.getSystemService("clipboard");
    if (!paramContext.hasPrimaryClip()) {
      return "";
    }
    paramContext = paramContext.getPrimaryClip();
    if (TextUtils.isEmpty(paramContext.getItemAt(0).getText())) {
      return "";
    }
    return paramContext.getItemAt(0).getText().toString();
  }
}
