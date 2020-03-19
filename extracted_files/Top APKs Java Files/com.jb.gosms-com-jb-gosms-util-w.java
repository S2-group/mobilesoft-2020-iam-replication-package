package com.jb.gosms.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.Toast;
import com.jb.gosms.MmsApp;
import com.jb.gosms.contact.ContactDataItem;
import com.jb.gosms.data.r;
import com.jb.gosms.g;
import com.jb.gosms.goim.ui.ContactCard;
import com.jb.gosms.goim.ui.ContactCardListActivity;
import com.jb.gosms.purchase.d;
import com.jb.gosms.transaction.y;
import com.jb.gosms.ui.skin.h;
import com.jb.gosms.ui.skin.n;
import com.jb.gosms.ui.v;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class w
{
  private static String B = "random_user_id";
  private static String C = "1111222233334444";
  public static double Code;
  private static final Map I = new ConcurrentHashMap();
  public static double V;
  private static String Z = null;
  
  public static String B(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static boolean B()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean B(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      localObject = Uri.parse(paramString);
    } while (localObject == null);
    Object localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
    ((Intent)localObject).setFlags(268435456);
    try
    {
      paramContext.startActivity((Intent)localObject);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      Log.i("Mms/GOSmsUtil", "gotoBrowser error, uri = " + paramString);
      return false;
    }
    catch (Exception paramContext)
    {
      Log.i("Mms/GOSmsUtil", "gotoBrowser error, uri = " + paramString);
    }
    return false;
  }
  
  public static boolean B(String paramString)
  {
    if ((d.Code(MmsApp.getMmsApp(), "com.jb.gosms.goteamswitch")) || (com.jb.gosms.modules.g.a.V())) {}
    Context localContext;
    do
    {
      do
      {
        return false;
      } while (TextUtils.isEmpty(paramString));
      localContext = MmsApp.getApplication();
    } while ((com.jb.gosms.ui.composemessage.c.a.Z(paramString)) || (!Linkify.addLinks(new SpannableString(paramString), 1)) || (PreferenceManager.getDefaultSharedPreferences(localContext).getString("pref_key_recommend_mx_browser", null) != null) || (t.S("pref_key_recommend_mx_browser") != null) || (Code(localContext, "com.mx.browser")));
    return true;
  }
  
  private static String C()
  {
    long l = System.currentTimeMillis();
    int i = new Random().nextInt();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(l);
    localStringBuffer.append(i);
    return I(localStringBuffer.toString());
  }
  
  public static void C(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addCategory("android.intent.category.HOME");
      localIntent.setFlags(270532608);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void C(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    localIntent.putExtra("com.android.browser.application_id", paramContext.getPackageName());
    localIntent.setFlags(524288);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      int i = 2131297853;
      if (paramString.indexOf("mailto:") >= 0) {
        i = 2131297559;
      }
      Toast.makeText(paramContext, i, 1).show();
    }
  }
  
  public static boolean C(String paramString)
  {
    return (paramString != null) && ("https://play.google.com/store/apps/details?id=com.mx.browser&referrer=utm_source%3D3GGo%26utm_medium%3D3GGoDestop%26utm_term%3Dmaxthon_browser%26utm_content%3DScreen0%26utm_campaign%3D6200889000".equals(paramString));
  }
  
  /* Error */
  public static int Code()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: aconst_null
    //   6: astore 7
    //   8: aconst_null
    //   9: astore 6
    //   11: iconst_0
    //   12: istore_1
    //   13: iconst_0
    //   14: istore_2
    //   15: invokestatic 251	com/jb/gosms/data/u:Code	()Lcom/jb/gosms/data/u;
    //   18: astore 5
    //   20: aload 4
    //   22: astore_3
    //   23: aload 5
    //   25: astore 4
    //   27: aload 7
    //   29: astore 6
    //   31: aload 5
    //   33: invokevirtual 257	android/database/sqlite/SQLiteOpenHelper:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   36: ldc_w 259
    //   39: aconst_null
    //   40: invokevirtual 265	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   43: astore 7
    //   45: iload_2
    //   46: istore_0
    //   47: aload 7
    //   49: ifnull +55 -> 104
    //   52: iload_2
    //   53: istore_0
    //   54: aload 7
    //   56: astore_3
    //   57: aload 5
    //   59: astore 4
    //   61: aload 7
    //   63: astore 6
    //   65: aload 7
    //   67: invokeinterface 270 1 0
    //   72: ifeq +32 -> 104
    //   75: aload 7
    //   77: astore_3
    //   78: aload 5
    //   80: astore 4
    //   82: aload 7
    //   84: astore 6
    //   86: aload 7
    //   88: aload 7
    //   90: ldc_w 272
    //   93: invokeinterface 275 2 0
    //   98: invokeinterface 279 2 0
    //   103: istore_0
    //   104: aload 7
    //   106: ifnull +10 -> 116
    //   109: aload 7
    //   111: invokeinterface 282 1 0
    //   116: iload_0
    //   117: istore_1
    //   118: aload 5
    //   120: ifnull +10 -> 130
    //   123: aload 5
    //   125: invokevirtual 283	android/database/sqlite/SQLiteOpenHelper:close	()V
    //   128: iload_0
    //   129: istore_1
    //   130: iload_1
    //   131: ireturn
    //   132: astore 7
    //   134: aconst_null
    //   135: astore 5
    //   137: aload 6
    //   139: astore_3
    //   140: aload 5
    //   142: astore 4
    //   144: aload 7
    //   146: invokevirtual 54	java/lang/Exception:printStackTrace	()V
    //   149: aload 6
    //   151: ifnull +10 -> 161
    //   154: aload 6
    //   156: invokeinterface 282 1 0
    //   161: aload 5
    //   163: ifnull -33 -> 130
    //   166: aload 5
    //   168: invokevirtual 283	android/database/sqlite/SQLiteOpenHelper:close	()V
    //   171: iconst_0
    //   172: ireturn
    //   173: astore 5
    //   175: aconst_null
    //   176: astore 4
    //   178: aload_3
    //   179: ifnull +9 -> 188
    //   182: aload_3
    //   183: invokeinterface 282 1 0
    //   188: aload 4
    //   190: ifnull +8 -> 198
    //   193: aload 4
    //   195: invokevirtual 283	android/database/sqlite/SQLiteOpenHelper:close	()V
    //   198: aload 5
    //   200: athrow
    //   201: astore 5
    //   203: goto -25 -> 178
    //   206: astore 7
    //   208: goto -71 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   46	83	0	i	int
    //   12	119	1	j	int
    //   14	39	2	k	int
    //   1	182	3	localObject1	Object
    //   3	191	4	localObject2	Object
    //   18	149	5	localU	com.jb.gosms.data.u
    //   173	26	5	localObject3	Object
    //   201	1	5	localObject4	Object
    //   9	146	6	localCursor1	android.database.Cursor
    //   6	104	7	localCursor2	android.database.Cursor
    //   132	13	7	localException1	Exception
    //   206	1	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   15	20	132	java/lang/Exception
    //   15	20	173	finally
    //   31	45	201	finally
    //   65	75	201	finally
    //   86	104	201	finally
    //   144	149	201	finally
    //   31	45	206	java/lang/Exception
    //   65	75	206	java/lang/Exception
    //   86	104	206	java/lang/Exception
  }
  
  public static int Code(Context paramContext)
  {
    return com.jb.gosms.ad.b.Code();
  }
  
  public static String Code(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (paramString2.contains("@"))) {
      return "(" + paramString1 + " = \"" + paramString2 + "\")";
    }
    return "PHONE_NUMBERS_EQUAL(" + paramString1 + ",\"" + paramString2 + "\")";
  }
  
  public static String Code(List paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i < paramList.size())
    {
      if (i == 0) {
        localStringBuilder.append("[");
      }
      localStringBuilder.append(paramList.get(i).toString());
      if (i == paramList.size() - 1) {
        localStringBuilder.append("]");
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append(", ");
      }
    }
    return localStringBuilder.toString();
  }
  
  public static void Code(Activity paramActivity, ContactDataItem paramContactDataItem, Uri paramUri, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, ContactCard.class);
    localIntent.putExtra("isvcard", true);
    localIntent.putExtra("vcard_data", paramContactDataItem);
    localIntent.putExtra("vcard_uri", paramUri);
    localIntent.putExtra("vcard_path", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  public static void Code(Activity paramActivity, List paramList, Uri paramUri, String paramString)
  {
    paramActivity.startActivity(new Intent().setClass(paramActivity, ContactCardListActivity.class).putExtra("isvcard", true).putParcelableArrayListExtra("vcard_data_multi", (ArrayList)paramList).putExtra("vcard_uri", paramUri).putExtra("vcard_path", paramString));
  }
  
  public static void Code(Context paramContext, long paramLong, String paramString, int paramInt)
  {
    y.Code(paramContext, "", paramLong, paramContext.getString(2131296592, new Object[] { "#lks#", "#lke#" }) + "#tps#" + 6 + "#tpe#", -1, paramInt);
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putString("pref_key_recommend_mx_browser", "true").commit();
    paramContext = t.I();
    paramContext.put("pref_key_recommend_mx_browser", "true");
    t.Code(paramContext);
  }
  
  public static void Code(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, Handler paramHandler)
  {
    int k = 0;
    String str2 = Locale.getDefault().getLanguage();
    String str3 = Locale.getDefault().getCountry();
    Object localObject = com.jb.gosms.modules.lang.b.c.Code(paramContext).Code();
    String str1 = "com.jb.gosms." + ((String)localObject).replace("_", "");
    localObject = ((String)localObject).replace("_", "");
    localObject = "GoSmsLanguage" + ((String)localObject).substring(0, 1).toUpperCase() + ((String)localObject).substring(1, ((String)localObject).length());
    boolean bool = V(com.jb.gosms.f.a.c.Code("language") + "/" + (String)localObject + ".zip");
    int i;
    if (("tw".equalsIgnoreCase(str3)) || ((str2 != null) && (!"zh".equals(str2)) && (!"en".equals(str2)) && (!com.jb.gosms.modules.d.a.Code(str1)) && (!bool)))
    {
      localObject = paramContext.getResources().getStringArray(2131624007);
      i = 0;
    }
    for (;;)
    {
      int j = k;
      str1 = str2;
      if (i < localObject.length)
      {
        if ((localObject[i].contains(str2)) && (i >= 2))
        {
          localObject = paramContext.getResources().getString(2131296305);
          String str4 = paramContext.getResources().getString(2131296304);
          str1 = str2;
          if ("tw".equalsIgnoreCase(str3)) {
            str1 = str2 + "_" + str3;
          }
          Code(paramContext, paramOnClickListener, (String)localObject, str4, str1, paramHandler);
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          com.jb.gosms.background.pro.c.Code("lan_unsupport_" + str1, null);
        }
        return;
      }
      i += 1;
    }
  }
  
  private static void Code(Context paramContext, DialogInterface.OnClickListener paramOnClickListener, String paramString1, String paramString2, final String paramString3, final Handler paramHandler)
  {
    g.B(paramContext, false);
    com.jb.gosms.ui.d.a.Code(paramContext, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        com.jb.gosms.background.pro.c.Code("lang_sure", null);
        paramAnonymousDialogInterface = this.Code.getPackageName() + "." + paramString3.replace("_", "");
        g.Code(this.Code, paramAnonymousDialogInterface);
        paramAnonymousDialogInterface = paramString3.replace("_", "");
        paramAnonymousDialogInterface = "GoSmsLanguage" + paramAnonymousDialogInterface.substring(0, 1).toUpperCase() + paramAnonymousDialogInterface.substring(1, paramAnonymousDialogInterface.length()) + ".zip";
        String str = PreferenceManager.getDefaultSharedPreferences(this.Code).getString("url_pre", null);
        ProgressDialog localProgressDialog = new ProgressDialog(this.Code);
        if (str != null)
        {
          com.jb.gosms.modules.lang.b.c.Code(this.Code).Code("pref_key_setting_gosmslanguage", paramString3);
          com.jb.gosms.language.c.Code().Code(str + paramAnonymousDialogInterface, new w.1.1(this, localProgressDialog));
          localProgressDialog.setMessage(this.Code.getString(2131298447));
          localProgressDialog.setCancelable(true);
          localProgressDialog.show();
        }
      }
    }, paramOnClickListener, paramString1, paramString2, paramContext.getResources().getString(2131296293), paramContext.getResources().getString(2131296288));
  }
  
  public static void Code(Context paramContext, Uri paramUri, int paramInt)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("seen", Integer.valueOf(1));
    r.Code(paramContext, paramUri, localContentValues, null, null, paramInt);
  }
  
  public static void Code(Context paramContext, CharSequence paramCharSequence)
  {
    try
    {
      ((ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramCharSequence);
      Toast.makeText(paramContext, 2131296743, 0).show();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void Code(String paramString)
  {
    if (paramString != null) {
      synchronized (I)
      {
        I.remove(paramString);
        return;
      }
    }
  }
  
  private static void Code(String paramString, Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
    paramContext.putString(B, paramString);
    paramContext.commit();
  }
  
  public static boolean Code(Activity paramActivity)
  {
    try
    {
      ActivityManager localActivityManager = (ActivityManager)paramActivity.getSystemService("activity");
      if (localActivityManager == null) {
        return false;
      }
    }
    catch (Throwable localThrowable1)
    {
      List localList;
      for (;;)
      {
        localList = null;
      }
      try
      {
        localList = localList.getRunningTasks(1);
        if (localList == null) {
          return false;
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          localObject = null;
        }
        Object localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {
          return false;
        }
        localObject = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
        if (localObject == null) {
          return false;
        }
        localObject = ((ActivityManager.RunningTaskInfo)localObject).baseActivity.getPackageName();
        if (paramActivity.getPackageName().equals(localObject)) {
          return true;
        }
        if (((String)localObject).equals("com.jb.gosms.plugin.gochat")) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean Code(Activity paramActivity, int paramInt)
  {
    return false;
  }
  
  public static final boolean Code(Context arg0, String paramString)
  {
    boolean bool = false;
    if (paramString == null) {
      return false;
    }
    if (n.Code(paramString)) {
      return h.S(paramString);
    }
    synchronized (I)
    {
      if (I.containsKey(paramString))
      {
        Integer localInteger = (Integer)I.get(paramString);
        if (localInteger != null)
        {
          if (-1 != localInteger.intValue()) {
            bool = true;
          }
          return bool;
        }
      }
    }
    try
    {
      int i = ???.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      bool = true;
      return false;
    }
    catch (PackageManager.NameNotFoundException ???)
    {
      synchronized (I)
      {
        I.put(paramString, Integer.valueOf(i));
        if (Loger.isD()) {
          Loger.v("isExistPackage", paramString + ": real reached.");
        }
        return bool;
        ??? = ???;
        i = -1;
        bool = false;
      }
    }
    catch (Exception ???) {}
  }
  
  public static boolean D(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext != null)
      {
        paramContext = paramContext.getActiveNetworkInfo();
        if ((paramContext != null) && (paramContext.isConnected())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static long F(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return 0L;
    }
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      long l = localSimpleDateFormat.parse(paramString).getTime();
      return l;
    }
    catch (Throwable paramString) {}
    return 0L;
  }
  
  public static boolean F(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1;
  }
  
  public static Context I(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramContext = paramContext.createPackageContext(paramString, 2);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String I()
  {
    Locale localLocale = Locale.getDefault();
    return String.format(Locale.US, "%s-%s", new Object[] { localLocale.getLanguage().toLowerCase(), localLocale.getCountry().toLowerCase() });
  }
  
  public static String I(Context paramContext)
  {
    String str2 = com.jb.gosms.x.a.Code(paramContext).getValue(B, C);
    String str1 = str2;
    if (C.equals(str2))
    {
      str2 = PreferenceManager.getDefaultSharedPreferences(paramContext).getString(B, C);
      str1 = str2;
      if (C.equals(str2))
      {
        str2 = c(paramContext);
        str1 = str2;
        if (str2 == null) {
          str1 = C();
        }
        if (("D5788ED704F076E9".equals(str1)) || ("9BCE6A8D9AF04812".equals(str1))) {
          com.jb.gosms.background.a.Code("EBestV8CommonUserId", "userId:" + str1 + ";" + "imsi:" + com.jb.gosms.modules.e.a.V(paramContext) + ";" + "randomId:" + C() + ";");
        }
        Code(str1, paramContext);
        Z(paramContext);
      }
      V(str1, paramContext);
    }
    return str1;
  }
  
  public static String I(String paramString)
  {
    try
    {
      localObject = MessageDigest.getInstance("MD5");
      int i;
      return ((StringBuffer)localObject).substring(8, 24).toString().toUpperCase();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException1)
    {
      for (;;)
      {
        try
        {
          ((MessageDigest)localObject).reset();
          ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
          paramString = ((MessageDigest)localObject).digest();
          localObject = new StringBuffer();
          i = 0;
          if (i >= paramString.length) {
            continue;
          }
          if (Integer.toHexString(paramString[i] & 0xFF).length() != 1) {
            continue;
          }
          ((StringBuffer)localObject).append("0").append(Integer.toHexString(paramString[i] & 0xFF));
          i += 1;
          continue;
          localNoSuchAlgorithmException1 = localNoSuchAlgorithmException1;
          paramString = null;
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException2)
        {
          paramString = (String)localObject;
          continue;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException2)
        {
          Object localObject;
          paramString = (String)localObject;
          continue;
        }
        localObject = paramString;
        if (Loger.isD())
        {
          Loger.w("Mms/GOSmsUtil", localNoSuchAlgorithmException1.toString());
          localObject = paramString;
        }
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException1)
    {
      for (;;)
      {
        paramString = null;
        localUnsupportedEncodingException1.printStackTrace();
        localObject = paramString;
        if (Loger.isD())
        {
          Loger.w("Mms/GOSmsUtil", localUnsupportedEncodingException1.toString());
          localObject = paramString;
          continue;
          ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
        }
      }
    }
  }
  
  public static String L(Context paramContext)
  {
    if ((Z != null) && (!Z.equals("")))
    {
      localObject = Z;
      return localObject;
    }
    for (;;)
    {
      try
      {
        paramContext = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getDeclaredMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { paramContext });
        paramContext = (String)paramContext.getClass().getDeclaredMethod("getId", new Class[0]).invoke(paramContext, new Object[0]);
      }
      catch (Exception localException1)
      {
        try
        {
          Z = paramContext;
          localObject = paramContext;
          if (!TextUtils.isEmpty(paramContext)) {
            break;
          }
          return "UNABLE-TO-RETRIEVE";
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            localObject = paramContext;
          }
        }
        localException1 = localException1;
        localObject = "UNABLE-TO-RETRIEVE";
      }
      paramContext = (Context)localObject;
      if (Loger.isD())
      {
        localException1.printStackTrace();
        paramContext = (Context)localObject;
      }
    }
  }
  
  public static boolean S(Context paramContext)
  {
    return Code(paramContext, "com.android.vending");
  }
  
  public static boolean S(String paramString)
  {
    if (paramString == null) {}
    while (!"10000@weibo.gosms".equals(paramString)) {
      return false;
    }
    return true;
  }
  
  public static final int V(Context paramContext, String paramString)
  {
    synchronized (I)
    {
      int i;
      if (I.containsKey(paramString))
      {
        i = ((Integer)I.get(paramString)).intValue();
        return i;
      }
      Code(paramContext, paramString);
      if (I.containsKey(paramString))
      {
        i = ((Integer)I.get(paramString)).intValue();
        return i;
      }
    }
    return -1;
  }
  
  public static String V(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = null;
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext == null) {
        return "";
      }
      localIterator = paramContext.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo != null) && (localPackageInfo.packageName != null))
        {
          int i;
          if ((localPackageInfo.applicationInfo.flags & 0x1) > 0)
          {
            i = 1;
            label82:
            localStringBuilder.append(localPackageInfo.packageName);
            localStringBuilder.append(",");
            localStringBuilder.append(i);
            localStringBuilder.append(",");
            if (localPackageInfo.versionName == null) {
              break label168;
            }
          }
          label168:
          for (paramContext = localPackageInfo.versionName;; paramContext = "")
          {
            localStringBuilder.append(paramContext);
            localStringBuilder.append(",");
            localStringBuilder.append(localPackageInfo.versionCode);
            localStringBuilder.append("#");
            break;
            i = 0;
            break label82;
          }
        }
      }
      return localStringBuilder.toString();
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext = localIterator;
      }
    }
  }
  
  public static void V()
  {
    synchronized (I)
    {
      I.clear();
      return;
    }
  }
  
  private static void V(String paramString, Context paramContext)
  {
    com.jb.gosms.x.a localA = com.jb.gosms.x.a.Code(paramContext);
    localA.putValue(B, paramString);
    localA.commint(paramContext);
  }
  
  public static boolean V(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    return new File(paramString).exists();
  }
  
  public static String Z()
  {
    Locale localLocale = Locale.getDefault();
    return String.format(Locale.US, "%s-%s", new Object[] { localLocale.getLanguage().toLowerCase(), localLocale.getCountry().toLowerCase() });
  }
  
  public static void Z(Context paramContext)
  {
    try
    {
      paramContext = v.V(paramContext);
      int i = paramContext.getInt("pref_key_never_open", 0);
      if ((i & 0x8) == 0) {
        paramContext.edit().putInt("pref_key_never_open", i | 0x8).commit();
      }
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void Z(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
  }
  
  public static boolean Z(String paramString)
  {
    return Pattern.compile("^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,4}$").matcher(paramString).matches();
  }
  
  public static String a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if (paramContext.activityInfo == null) {}
    while (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static String b(Context paramContext)
  {
    if (paramContext == null) {
      return "com.jb.gosms";
    }
    return paramContext.getPackageName();
  }
  
  private static String c(Context paramContext)
  {
    paramContext = com.jb.gosms.goim.im.a.b.I(paramContext);
    if ((paramContext == null) || (paramContext.length() != 32)) {
      return null;
    }
    return paramContext.substring(16);
  }
}
