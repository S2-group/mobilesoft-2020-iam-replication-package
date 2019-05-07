package com.qisi.utils;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.app.y.d;
import android.support.v7.app.m.a;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import com.android.inputmethod.latin.LatinIME;
import com.android.inputmethod.latin.LatinIME.c;
import com.android.inputmethod.latin.r;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class d
{
  private static String a;
  
  public static String a()
  {
    try
    {
      String str = Locale.getDefault().getLanguage();
      return str;
    }
    catch (Exception localException) {}
    return "en";
  }
  
  public static String a(String paramString, Map<String, String> paramMap)
  {
    if (TextUtils.isEmpty(paramString)) {
      localObject = null;
    }
    do
    {
      return localObject;
      localObject = paramString;
    } while (paramMap.size() == 0);
    Object localObject = new StringBuilder(paramString);
    if (paramString.contains("?")) {
      ((StringBuilder)localObject).append("&");
    }
    for (;;)
    {
      paramString = paramMap.entrySet().iterator();
      int i = 0;
      label61:
      if (paramString.hasNext())
      {
        paramMap = (Map.Entry)paramString.next();
        if (i > 0) {
          ((StringBuilder)localObject).append("&");
        }
        try
        {
          ((StringBuilder)localObject).append(URLEncoder.encode((String)paramMap.getKey(), "UTF-8"));
          ((StringBuilder)localObject).append("=");
          ((StringBuilder)localObject).append(URLEncoder.encode((String)paramMap.getValue(), "UTF-8"));
          i += 1;
          break label61;
          ((StringBuilder)localObject).append("?");
        }
        catch (UnsupportedEncodingException paramMap)
        {
          for (;;)
          {
            paramMap.printStackTrace();
          }
        }
      }
    }
    return ((StringBuilder)localObject).toString();
  }
  
  /* Error */
  public static java.util.List<android.content.pm.PackageInfo> a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 111	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_2
    //   5: aload_2
    //   6: iconst_0
    //   7: invokevirtual 117	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: aload_0
    //   15: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   18: new 120	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 122	java/util/ArrayList:<init>	()V
    //   25: astore_3
    //   26: aconst_null
    //   27: astore_0
    //   28: invokestatic 128	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   31: ldc -126
    //   33: invokevirtual 134	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   36: astore 4
    //   38: new 136	java/io/BufferedReader
    //   41: dup
    //   42: new 138	java/io/InputStreamReader
    //   45: dup
    //   46: aload 4
    //   48: invokevirtual 144	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   51: invokespecial 147	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: invokespecial 150	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   57: astore_1
    //   58: aload_1
    //   59: astore_0
    //   60: aload_1
    //   61: invokevirtual 153	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 5
    //   66: aload 5
    //   68: ifnull +68 -> 136
    //   71: aload_1
    //   72: astore_0
    //   73: aload_3
    //   74: aload_2
    //   75: aload 5
    //   77: aload 5
    //   79: bipush 58
    //   81: invokevirtual 157	java/lang/String:indexOf	(I)I
    //   84: iconst_1
    //   85: iadd
    //   86: invokevirtual 161	java/lang/String:substring	(I)Ljava/lang/String;
    //   89: iconst_1
    //   90: invokevirtual 165	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   93: invokeinterface 171 2 0
    //   98: pop
    //   99: goto -41 -> 58
    //   102: astore_2
    //   103: aload_1
    //   104: astore_0
    //   105: aload_2
    //   106: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   109: aload_3
    //   110: astore_0
    //   111: aload_1
    //   112: ifnull -101 -> 11
    //   115: aload_1
    //   116: invokevirtual 174	java/io/BufferedReader:close	()V
    //   119: aload_3
    //   120: areturn
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   126: aload_3
    //   127: areturn
    //   128: astore_0
    //   129: aload_0
    //   130: invokevirtual 175	java/lang/NoSuchMethodError:printStackTrace	()V
    //   133: goto -115 -> 18
    //   136: aload_1
    //   137: astore_0
    //   138: aload 4
    //   140: invokevirtual 178	java/lang/Process:waitFor	()I
    //   143: pop
    //   144: aload_3
    //   145: astore_0
    //   146: aload_1
    //   147: ifnull -136 -> 11
    //   150: aload_1
    //   151: invokevirtual 174	java/io/BufferedReader:close	()V
    //   154: aload_3
    //   155: areturn
    //   156: astore_0
    //   157: aload_0
    //   158: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   161: aload_3
    //   162: areturn
    //   163: astore_2
    //   164: aload_0
    //   165: astore_1
    //   166: aload_2
    //   167: astore_0
    //   168: aload_1
    //   169: ifnull +7 -> 176
    //   172: aload_1
    //   173: invokevirtual 174	java/io/BufferedReader:close	()V
    //   176: aload_0
    //   177: athrow
    //   178: astore_1
    //   179: aload_1
    //   180: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   183: goto -7 -> 176
    //   186: astore_2
    //   187: aload_0
    //   188: astore_1
    //   189: aload_2
    //   190: astore_0
    //   191: goto -23 -> 168
    //   194: astore_2
    //   195: aconst_null
    //   196: astore_1
    //   197: goto -94 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	paramContext	Context
    //   57	116	1	localObject1	Object
    //   178	2	1	localException1	Exception
    //   188	9	1	localContext	Context
    //   4	71	2	localPackageManager	PackageManager
    //   102	4	2	localException2	Exception
    //   163	4	2	localObject2	Object
    //   186	4	2	localObject3	Object
    //   194	1	2	localException3	Exception
    //   25	137	3	localArrayList	java.util.ArrayList
    //   36	103	4	localProcess	Process
    //   64	14	5	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Exception
    //   60	66	102	java/lang/Exception
    //   73	99	102	java/lang/Exception
    //   138	144	102	java/lang/Exception
    //   115	119	121	java/lang/Exception
    //   5	11	128	java/lang/NoSuchMethodError
    //   150	154	156	java/lang/Exception
    //   28	58	163	finally
    //   172	176	178	java/lang/Exception
    //   60	66	186	finally
    //   73	99	186	finally
    //   105	109	186	finally
    //   138	144	186	finally
    //   28	58	194	java/lang/Exception
  }
  
  public static void a(Activity paramActivity)
  {
    if (paramActivity == null) {
      return;
    }
    View localView = paramActivity.getCurrentFocus();
    InputMethodManager localInputMethodManager = (InputMethodManager)paramActivity.getSystemService("input_method");
    if (localView == null) {}
    for (paramActivity = null;; paramActivity = localView.getApplicationWindowToken())
    {
      localInputMethodManager.hideSoftInputFromWindow(paramActivity, 0);
      return;
    }
  }
  
  public static void a(Context paramContext, PendingIntent paramPendingIntent, String paramString)
  {
    if ((paramContext == null) || (paramPendingIntent == null) || (paramString == null) || (paramString.isEmpty())) {
      return;
    }
    String str = paramContext.getString(paramContext.getApplicationInfo().labelRes);
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    m.a localA = new m.a(paramContext);
    localA.a(2130838997).a(true).b(paramString).a(str).a(paramPendingIntent).a(BitmapFactory.decodeResource(paramContext.getResources(), 2130903042));
    localNotificationManager.notify(0, localA.a());
  }
  
  public static void a(String paramString)
  {
    if (LatinIME.c.f() != null)
    {
      InputConnection localInputConnection = LatinIME.c.f().a();
      if (localInputConnection != null) {
        localInputConnection.commitText(paramString + '\n', 1);
      }
    }
  }
  
  public static Drawable b(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0).loadIcon(localPackageManager);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String b()
  {
    try
    {
      String str = Locale.getDefault().getCountry();
      return str;
    }
    catch (Exception localException) {}
    return "US";
  }
  
  public static boolean b(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (LatinIME.d != null) && (LatinIME.d.f != null) && (LatinIME.d.f.equals(paramString));
  }
  
  public static String c(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("country", b());
    localHashMap.put("language", Locale.getDefault().getLanguage());
    localHashMap.put("product", "emojiKeyboard");
    return a(paramString, localHashMap);
  }
  
  public static boolean c(Context paramContext)
  {
    try
    {
      paramContext.getApplicationContext().getPackageManager().getApplicationInfo("com.facebook.katana", 0);
      return true;
    }
    catch (Exception paramContext)
    {
      Log.e("xinmei", "facebook application does not installed in this device");
    }
    return false;
  }
  
  public static String d(Context paramContext)
  {
    if ((paramContext != null) && (TextUtils.isEmpty(a))) {
      if (i.e(paramContext) < 720) {
        break label37;
      }
    }
    label37:
    for (a = paramContext.getString(2131296745);; a = paramContext.getString(2131296746)) {
      return a;
    }
  }
}
