package com.examobile.applib.e;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.b.a;
import android.view.ContextThemeWrapper;
import com.examobile.applib.a.f;
import com.examobile.applib.a.g;
import com.examobile.applib.activity.AlertActivity;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class e
{
  public static byte a = Byte.MAX_VALUE;
  private static SharedPreferences b = null;
  private static boolean c = false;
  private static boolean d = false;
  private static MediaPlayer e = null;
  private static Vibrator f = null;
  private static int g = 0;
  
  public static String a(Context paramContext)
  {
    String str2 = b(paramContext).getString("locale", "none");
    String str1 = str2;
    if (str2.equals("none")) {
      str1 = paramContext.getString(a.f.applib_default_locale);
    }
    return str1;
  }
  
  /* Error */
  public static java.util.List<android.content.pm.ApplicationInfo> a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 79	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: iload_1
    //   7: invokevirtual 85	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   10: astore_0
    //   11: aload_0
    //   12: areturn
    //   13: astore_0
    //   14: new 87	java/util/ArrayList
    //   17: dup
    //   18: invokespecial 90	java/util/ArrayList:<init>	()V
    //   21: astore 4
    //   23: aconst_null
    //   24: astore_0
    //   25: invokestatic 96	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   28: ldc 98
    //   30: invokevirtual 102	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   33: astore 5
    //   35: new 104	java/io/BufferedReader
    //   38: dup
    //   39: new 106	java/io/InputStreamReader
    //   42: dup
    //   43: aload 5
    //   45: invokevirtual 112	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   48: invokespecial 115	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   51: invokespecial 118	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   54: astore_2
    //   55: aload_2
    //   56: astore_0
    //   57: aload_2
    //   58: invokevirtual 122	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   61: astore 6
    //   63: aload 6
    //   65: ifnull +64 -> 129
    //   68: aload_2
    //   69: astore_0
    //   70: aload 4
    //   72: aload_3
    //   73: aload 6
    //   75: aload 6
    //   77: bipush 58
    //   79: invokevirtual 126	java/lang/String:indexOf	(I)I
    //   82: iconst_1
    //   83: iadd
    //   84: invokevirtual 129	java/lang/String:substring	(I)Ljava/lang/String;
    //   87: iload_1
    //   88: invokevirtual 133	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   91: invokeinterface 138 2 0
    //   96: pop
    //   97: goto -42 -> 55
    //   100: astore_3
    //   101: aload_2
    //   102: astore_0
    //   103: aload_3
    //   104: invokevirtual 141	java/lang/Exception:printStackTrace	()V
    //   107: aload 4
    //   109: astore_0
    //   110: aload_2
    //   111: ifnull -100 -> 11
    //   114: aload_2
    //   115: invokevirtual 144	java/io/BufferedReader:close	()V
    //   118: aload 4
    //   120: areturn
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 145	java/io/IOException:printStackTrace	()V
    //   126: aload 4
    //   128: areturn
    //   129: aload_2
    //   130: astore_0
    //   131: aload 5
    //   133: invokevirtual 149	java/lang/Process:waitFor	()I
    //   136: pop
    //   137: aload 4
    //   139: astore_0
    //   140: aload_2
    //   141: ifnull -130 -> 11
    //   144: aload_2
    //   145: invokevirtual 144	java/io/BufferedReader:close	()V
    //   148: aload 4
    //   150: areturn
    //   151: astore_0
    //   152: aload_0
    //   153: invokevirtual 145	java/io/IOException:printStackTrace	()V
    //   156: aload 4
    //   158: areturn
    //   159: astore_3
    //   160: aload_0
    //   161: astore_2
    //   162: aload_3
    //   163: astore_0
    //   164: aload_2
    //   165: ifnull +7 -> 172
    //   168: aload_2
    //   169: invokevirtual 144	java/io/BufferedReader:close	()V
    //   172: aload_0
    //   173: athrow
    //   174: astore_2
    //   175: aload_2
    //   176: invokevirtual 145	java/io/IOException:printStackTrace	()V
    //   179: goto -7 -> 172
    //   182: astore_3
    //   183: aload_0
    //   184: astore_2
    //   185: aload_3
    //   186: astore_0
    //   187: goto -23 -> 164
    //   190: astore_3
    //   191: aconst_null
    //   192: astore_2
    //   193: goto -92 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	196	0	paramContext	Context
    //   0	196	1	paramInt	int
    //   54	115	2	localObject1	Object
    //   174	2	2	localIOException	java.io.IOException
    //   184	9	2	localContext	Context
    //   4	69	3	localPackageManager	android.content.pm.PackageManager
    //   100	4	3	localException1	Exception
    //   159	4	3	localObject2	Object
    //   182	4	3	localObject3	Object
    //   190	1	3	localException2	Exception
    //   21	136	4	localArrayList	java.util.ArrayList
    //   33	99	5	localProcess	Process
    //   61	15	6	str	String
    // Exception table:
    //   from	to	target	type
    //   5	11	13	java/lang/Exception
    //   57	63	100	java/lang/Exception
    //   70	97	100	java/lang/Exception
    //   131	137	100	java/lang/Exception
    //   114	118	121	java/io/IOException
    //   144	148	151	java/io/IOException
    //   25	55	159	finally
    //   168	172	174	java/io/IOException
    //   57	63	182	finally
    //   70	97	182	finally
    //   103	107	182	finally
    //   131	137	182	finally
    //   25	55	190	java/lang/Exception
  }
  
  public static void a()
  {
    if (c)
    {
      e.stop();
      e.release();
      e = null;
      c = false;
    }
  }
  
  public static void a(byte paramByte)
  {
    a = paramByte;
  }
  
  public static void a(int paramInt)
  {
    g = paramInt;
  }
  
  public static void a(Activity paramActivity)
  {
    new b.a(new ContextThemeWrapper(paramActivity, a.g.Theme_AppCompat), a.g.RateDialogTheme).a(paramActivity.getString(a.f.applib_alert_offline_title)).b(paramActivity.getString(a.f.applib_alert_offline_message)).a(paramActivity.getString(a.f.applib_alert_offline_positive), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          this.a.startActivityForResult(new Intent("android.settings.WIRELESS_SETTINGS"), 0);
          return;
        }
        catch (ActivityNotFoundException paramAnonymousDialogInterface) {}
      }
    }).b(paramActivity.getString(a.f.applib_alert_offline_negative), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).b().show();
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if (a == Byte.MAX_VALUE)
    {
      Intent localIntent = new Intent(paramActivity, AlertActivity.class);
      a = 6;
      paramActivity.startActivity(localIntent.putExtra("TYPE", (byte)6).putExtra("share_app_name", paramString1).putExtra("share_google_app_link", paramString2).putExtra("share_samsung_app_link", paramString3).putExtra("share_windowsphone_link", paramString5).putExtra("share_appstore_app_link", paramString4));
    }
  }
  
  public static void a(Activity paramActivity, boolean paramBoolean)
  {
    byte b1;
    if (a == Byte.MAX_VALUE)
    {
      if (!paramBoolean) {
        break label35;
      }
      b1 = 5;
    }
    for (;;)
    {
      paramActivity.startActivity(new Intent(paramActivity, AlertActivity.class).putExtra("TYPE", b1));
      return;
      label35:
      b1 = 4;
      a = 4;
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    b(paramContext).edit().putString("locale", paramString).commit();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    ContextThemeWrapper localContextThemeWrapper = new ContextThemeWrapper(paramContext, a.g.Theme_AppCompat);
    paramContext = new a(paramContext, paramString1, paramString2, paramString3, paramString4, null);
    new b.a(localContextThemeWrapper, a.g.RateDialogTheme).a(a.f.rate_us_dialog_title).b(a.f.rate_us_dialog_content).a(true).a(a.f.rate_us_dialog_positive, paramContext).b(a.f.rate_us_dialog_negative, paramContext).c(a.f.rate_us_dialog_neutral, paramContext).b().show();
  }
  
  public static void a(Context paramContext, String paramString, boolean paramBoolean)
  {
    int i = b(paramContext).getInt(paramString, 0);
    paramContext = b(paramContext).edit();
    if ((paramBoolean) && (i < 0))
    {
      paramContext.putInt(paramString, 0);
      paramContext.commit();
    }
    while ((paramBoolean) || (i < 0)) {
      return;
    }
    paramContext.putInt(paramString, -1);
    paramContext.commit();
  }
  
  public static void a(Context paramContext, boolean paramBoolean)
  {
    paramContext = b(paramContext).edit();
    paramContext.putBoolean("purchases_restored", paramBoolean);
    paramContext.commit();
  }
  
  public static boolean a(Context paramContext, String paramString, int paramInt)
  {
    int i = b(paramContext).getInt(paramString, 0);
    if (i < 0) {
      return false;
    }
    paramContext = b(paramContext).edit();
    if (i < paramInt)
    {
      paramContext.putInt(paramString, i + 1);
      paramContext.commit();
      return false;
    }
    paramContext.putInt(paramString, 0);
    paramContext.commit();
    return true;
  }
  
  public static SharedPreferences b(Context paramContext)
  {
    if (b == null)
    {
      paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
      b = paramContext;
      return paramContext;
    }
    return b;
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    if ((paramContext != null) && (f(paramContext)) && (!e(paramContext)) && (!c) && (paramInt != 0))
    {
      if (e == null) {
        e = MediaPlayer.create(paramContext, paramInt);
      }
      e.setLooping(true);
      e.setVolume(1.0F, 1.0F);
      e.start();
      c = true;
    }
  }
  
  public static boolean b(Context paramContext, boolean paramBoolean)
  {
    return b(paramContext).getBoolean("googleanalytics", paramBoolean);
  }
  
  public static void c(Context paramContext, int paramInt)
  {
    paramContext = b(paramContext).edit();
    paramContext.putInt("RATESTATUS", paramInt);
    paramContext.commit();
  }
  
  public static void c(Context paramContext, boolean paramBoolean)
  {
    paramContext = b(paramContext).edit();
    paramContext.putBoolean("ad_block_purchased", paramBoolean);
    paramContext.commit();
  }
  
  public static boolean c(Context paramContext)
  {
    return GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext) == 0;
  }
  
  public static void d(Context paramContext, boolean paramBoolean)
  {
    paramContext = b(paramContext).edit();
    paramContext.putBoolean("premium_version_purchased", paramBoolean);
    paramContext.putBoolean("full_version", paramBoolean);
    paramContext.commit();
  }
  
  public static boolean d(Context paramContext)
  {
    return b(paramContext).getBoolean("purchases_restored", false);
  }
  
  public static void e(Context paramContext, boolean paramBoolean)
  {
    paramContext = b(paramContext).edit();
    paramContext.putBoolean("premium_promo", paramBoolean);
    paramContext.commit();
  }
  
  public static boolean e(Context paramContext)
  {
    return b(paramContext).getBoolean("mute_pref", false);
  }
  
  public static boolean f(Context paramContext)
  {
    return b(paramContext).getBoolean("SOUND_BG", true);
  }
  
  public static void g(Context paramContext)
  {
    if (g != 0) {
      b(paramContext, g);
    }
  }
  
  public static boolean h(Context paramContext)
  {
    boolean bool = false;
    if ((b(paramContext).getBoolean("ad_block_purchased", false)) || (i(paramContext))) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean i(Context paramContext)
  {
    return (k(paramContext)) || (l(paramContext)) || (j(paramContext));
  }
  
  public static boolean j(Context paramContext)
  {
    return b(paramContext).getBoolean("forced_premium_version_purchased", false);
  }
  
  public static boolean k(Context paramContext)
  {
    boolean bool = false;
    if ((b(paramContext).getBoolean("premium_version_purchased", false)) || (b(paramContext).getBoolean("full_version", false)) || (m(paramContext)) || (j(paramContext))) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean l(Context paramContext)
  {
    return b(paramContext).getBoolean("premium_free_version_unlocked", false);
  }
  
  public static boolean m(Context paramContext)
  {
    return b(paramContext).getBoolean("premium_promo", false);
  }
  
  public static boolean n(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  private static class a
    implements DialogInterface.OnClickListener
  {
    private Context a;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    
    private a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
    {
      this.a = paramContext;
      this.b = paramString1;
      this.c = paramString2;
      this.d = paramString3;
      this.e = paramString4;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      switch (paramInt)
      {
      }
      for (;;)
      {
        paramDialogInterface.dismiss();
        return;
        Intent localIntent;
        if (e.n(this.a))
        {
          e.c(this.a, 1);
          this.a.sendBroadcast(new Intent("RATE_US_CLICKED"));
          this.a.sendBroadcast(new Intent("broadcast_rate_us_low_rate_clicked"));
          localIntent = new Intent("android.intent.action.SEND");
          localIntent.setType("text/html");
          localIntent.putExtra("android.intent.extra.EMAIL", new String[] { this.e });
          localIntent.putExtra("android.intent.extra.SUBJECT", this.a.getString(a.f.rate_us_dislike_mail_title, new Object[] { this.b }));
          localIntent.putExtra("android.intent.extra.TEXT", "");
          this.a.startActivity(Intent.createChooser(localIntent, "Send Email"));
          b.a(this.a).a("RATE_US", "DISLIKE IT", "CLICK", 0L);
        }
        else
        {
          e.a((Activity)this.a);
          continue;
          e.c(this.a, 0);
          this.a.sendBroadcast(new Intent("broadcast_rate_us_cancel_clicked"));
          b.a(this.a).a("RATE_US", "CANCEL", "CLICK", 0L);
          continue;
          if (e.n(this.a))
          {
            e.c(this.a, 1);
            this.a.sendBroadcast(new Intent("RATE_US_CLICKED"));
            localIntent = new Intent("android.intent.action.VIEW");
            localIntent.setData(Uri.parse(this.c));
            this.a.sendBroadcast(new Intent("broadcast_rate_us_rated_clicked"));
            if (localIntent.resolveActivity(this.a.getPackageManager()) != null) {
              this.a.startActivity(localIntent);
            }
            for (;;)
            {
              b.a(this.a).a("RATE_US", "5 STARS", "CLICK", 1L);
              break;
              localIntent.setData(Uri.parse(this.d));
              this.a.startActivity(localIntent);
            }
          }
          e.a((Activity)this.a);
        }
      }
    }
  }
}
