package com.qisi.utils.a;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import com.android.inputmethod.latin.LatinIME;
import com.qisi.inputmethod.keyboard.d.b;
import com.qisi.model.Sticker2.StickerGroup;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class d
{
  public static final String[] a = { "181", "40", "309", "306" };
  public static final String[] b = { "com.qisiemoji.inputmethod.sticker.dog", "com.qisiemoji.inputmethod.sticker.cool", "com.qisiemoji.inputmethod.sticker.taro", "com.qisiemoji.inputmethod.sticker.cat" };
  private static String c;
  
  public static Uri a(String paramString1, String paramString2)
  {
    return Uri.parse("content://" + paramString1 + "/" + paramString2);
  }
  
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
  
  public static String a(Context paramContext, int paramInt1, int paramInt2)
  {
    if ((paramContext != null) && (TextUtils.isEmpty(c))) {
      if (h.g(paramContext) < 720) {
        break label35;
      }
    }
    label35:
    for (c = paramContext.getString(paramInt1);; c = paramContext.getString(paramInt2)) {
      return c;
    }
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
    //   1: invokevirtual 161	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_2
    //   5: aload_2
    //   6: iconst_0
    //   7: invokevirtual 167	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: aload_0
    //   15: invokevirtual 168	java/lang/Exception:printStackTrace	()V
    //   18: new 170	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 171	java/util/ArrayList:<init>	()V
    //   25: astore_3
    //   26: aconst_null
    //   27: astore_0
    //   28: invokestatic 177	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   31: ldc -77
    //   33: invokevirtual 183	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   36: astore 4
    //   38: new 185	java/io/BufferedReader
    //   41: dup
    //   42: new 187	java/io/InputStreamReader
    //   45: dup
    //   46: aload 4
    //   48: invokevirtual 193	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   51: invokespecial 196	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   54: invokespecial 199	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   57: astore_1
    //   58: aload_1
    //   59: astore_0
    //   60: aload_1
    //   61: invokevirtual 202	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   64: astore 5
    //   66: aload 5
    //   68: ifnull +55 -> 123
    //   71: aload_1
    //   72: astore_0
    //   73: aload_3
    //   74: aload_2
    //   75: aload 5
    //   77: aload 5
    //   79: bipush 58
    //   81: invokevirtual 206	java/lang/String:indexOf	(I)I
    //   84: iconst_1
    //   85: iadd
    //   86: invokevirtual 209	java/lang/String:substring	(I)Ljava/lang/String;
    //   89: iconst_1
    //   90: invokevirtual 213	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   93: invokeinterface 219 2 0
    //   98: pop
    //   99: goto -41 -> 58
    //   102: astore_2
    //   103: aload_1
    //   104: astore_0
    //   105: aload_2
    //   106: invokevirtual 168	java/lang/Exception:printStackTrace	()V
    //   109: aload_1
    //   110: invokestatic 224	com/qisi/utils/a/k:a	(Ljava/io/Closeable;)V
    //   113: aload_3
    //   114: areturn
    //   115: astore_0
    //   116: aload_0
    //   117: invokevirtual 225	java/lang/NoSuchMethodError:printStackTrace	()V
    //   120: goto -102 -> 18
    //   123: aload_1
    //   124: astore_0
    //   125: aload 4
    //   127: invokevirtual 228	java/lang/Process:waitFor	()I
    //   130: pop
    //   131: aload_1
    //   132: invokestatic 224	com/qisi/utils/a/k:a	(Ljava/io/Closeable;)V
    //   135: aload_3
    //   136: areturn
    //   137: astore_2
    //   138: aload_0
    //   139: astore_1
    //   140: aload_2
    //   141: astore_0
    //   142: aload_1
    //   143: invokestatic 224	com/qisi/utils/a/k:a	(Ljava/io/Closeable;)V
    //   146: aload_0
    //   147: athrow
    //   148: astore_2
    //   149: aload_0
    //   150: astore_1
    //   151: aload_2
    //   152: astore_0
    //   153: goto -11 -> 142
    //   156: astore_2
    //   157: aconst_null
    //   158: astore_1
    //   159: goto -56 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	paramContext	Context
    //   57	102	1	localObject1	Object
    //   4	71	2	localPackageManager	PackageManager
    //   102	4	2	localException1	Exception
    //   137	4	2	localObject2	Object
    //   148	4	2	localObject3	Object
    //   156	1	2	localException2	Exception
    //   25	111	3	localArrayList	java.util.ArrayList
    //   36	90	4	localProcess	Process
    //   64	14	5	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Exception
    //   60	66	102	java/lang/Exception
    //   73	99	102	java/lang/Exception
    //   125	131	102	java/lang/Exception
    //   5	11	115	java/lang/NoSuchMethodError
    //   28	58	137	finally
    //   60	66	148	finally
    //   73	99	148	finally
    //   105	109	148	finally
    //   125	131	148	finally
    //   28	58	156	java/lang/Exception
  }
  
  public static Map<String, String> a(String paramString)
  {
    int i = 0;
    localLinkedHashMap = new LinkedHashMap();
    paramString = paramString.split("&");
    try
    {
      int j = paramString.length;
      while (i < j)
      {
        Object localObject = paramString[i];
        int k = localObject.indexOf('=');
        if ((k <= 0) && (k >= localObject.length() - 1)) {
          return null;
        }
        localLinkedHashMap.put(URLDecoder.decode(localObject.substring(0, k), "UTF-8"), URLDecoder.decode(localObject.substring(k + 1), "UTF-8"));
        i += 1;
      }
      return localLinkedHashMap;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return null;
    }
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
  
  public static boolean a(Sticker2.StickerGroup paramStickerGroup)
  {
    if (paramStickerGroup == null) {}
    for (;;)
    {
      return false;
      if ((!TextUtils.isEmpty(paramStickerGroup.key)) && ("1".equals(com.kikatech.b.a.a().b("apksticker_switch", "0"))))
      {
        String[] arrayOfString = a;
        int j = arrayOfString.length;
        int i = 0;
        while (i < j)
        {
          String str = arrayOfString[i];
          if (paramStickerGroup.key.equalsIgnoreCase(str)) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  public static boolean a(String paramString1, String paramString2, String paramString3)
  {
    return (!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (!TextUtils.isEmpty(paramString3)) && (paramString1.contains(paramString2 + "=" + paramString3));
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
  
  public static String b(Sticker2.StickerGroup paramStickerGroup)
  {
    if (paramStickerGroup == null) {
      return "";
    }
    paramStickerGroup = paramStickerGroup.key;
    if (TextUtils.isEmpty(paramStickerGroup)) {
      return "";
    }
    int i = 0;
    for (;;)
    {
      if ((i >= a.length) || (paramStickerGroup.equalsIgnoreCase(a[i])))
      {
        if ((i < a.length) && (i < b.length)) {
          break;
        }
        return "";
      }
      i += 1;
    }
    return b[i];
  }
  
  public static String b(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("country", b());
    localHashMap.put("language", Locale.getDefault().getLanguage());
    localHashMap.put("product", paramString2);
    return a(paramString1, localHashMap);
  }
  
  public static boolean b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      if ("1".equals(com.kikatech.b.a.a().b("apksticker_switch", "0")))
      {
        String[] arrayOfString = b;
        int j = arrayOfString.length;
        int i = 0;
        while (i < j)
        {
          if (paramString.equalsIgnoreCase(arrayOfString[i])) {
            return true;
          }
          i += 1;
        }
      }
    }
  }
  
  public static String c(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    int i = 0;
    for (;;)
    {
      if ((i >= b.length) || (paramString.equalsIgnoreCase(b[i])))
      {
        if ((i < a.length) && (i < b.length)) {
          break;
        }
        return "";
      }
      i += 1;
    }
    return a[i];
  }
  
  public static boolean c()
  {
    if ((!"com.facebook.katana".equals(b.a().e())) && (!"com.facebook.lite".equals(b.a().e()))) {}
    while (((b.a().d().imeOptions & 0x7) == 3) || (("com.facebook.katana".equals(b.a().e())) && (b.a().d().imeOptions == 1073741830)) || ("0".equals(com.kikatech.b.a.a().b("fb_sticker_switch", "0")))) {
      return false;
    }
    return true;
  }
  
  public static boolean d()
  {
    if ((!"com.facebook.katana".equals(b.a().e())) && (!"com.facebook.lite".equals(b.a().e()))) {}
    while (((b.a().d().imeOptions & 0x7) == 3) || ("0".equals(com.kikatech.b.a.a().b("fb_popup_switch", "0")))) {
      return false;
    }
    return true;
  }
  
  public static boolean e()
  {
    return com.qisi.application.a.a().getResources().getConfiguration().orientation == 1;
  }
  
  public static boolean f()
  {
    if (LatinIME.c() == null) {}
    EditorInfo localEditorInfo;
    do
    {
      return false;
      localEditorInfo = LatinIME.c().getCurrentInputEditorInfo();
    } while (localEditorInfo == null);
    return "com.whatsapp".equals(localEditorInfo.packageName);
  }
  
  public static boolean g()
  {
    if (!"com.tencent.mm".equals(b.a().e())) {}
    while ((b.a().d().imeOptions & 0x7) != 3) {
      return false;
    }
    return true;
  }
}
