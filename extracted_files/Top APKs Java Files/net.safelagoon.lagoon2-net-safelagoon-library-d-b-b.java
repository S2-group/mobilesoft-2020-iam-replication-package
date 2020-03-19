package net.safelagoon.library.d.b;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources.Theme;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.a.a.f;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.safelagoon.library.BuildConfig;
import net.safelagoon.library.a.f;

public final class b
{
  private static final double a = Math.pow(1024.0D, 2.0D);
  
  public static int a(Context paramContext, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    if (paramContext.getTheme().resolveAttribute(paramInt, localTypedValue, true)) {
      return localTypedValue.data;
    }
    return -1;
  }
  
  public static NotificationCompat.Builder a(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, int paramInt3, String paramString3)
  {
    paramString3 = new NotificationCompat.Builder(paramContext, paramString3).setSmallIcon(paramInt1).setLargeIcon(BitmapFactory.decodeResource(paramContext.getResources(), paramInt2)).setTicker(paramString2).setContentTitle(paramString1).setContentText(paramString2);
    if (paramInt3 > -1) {
      paramString3.setAutoCancel(true);
    }
    if (paramIntent != null) {
      paramString3.setContentIntent(PendingIntent.getActivity(paramContext, 0, paramIntent, 134217728));
    }
    paramContext = new NotificationCompat.BigTextStyle();
    paramContext.setBigContentTitle(paramString1);
    paramContext.bigText(paramString2);
    paramString3.setStyle(paramContext);
    return paramString3;
  }
  
  public static File a(List<String> paramList, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new File(paramString1);
      if ((!paramString1.exists()) && (!paramString1.mkdirs())) {
        return null;
      }
      paramString1 = new File(paramString1, paramString2);
      a(paramList, new BufferedWriter(new FileWriter(paramString1, true), (int)a * 4));
      return paramString1;
    }
    catch (Exception paramList)
    {
      if (BuildConfig.DEBUG_MODE.booleanValue()) {
        Log.e("LibraryHelper", "Can't write to file", paramList);
      }
    }
    return null;
  }
  
  public static String a()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return e.b(str2);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(e.b(str1));
    localStringBuilder.append(" ");
    localStringBuilder.append(str2);
    return localStringBuilder.toString();
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      if (BuildConfig.DEBUG_MODE.booleanValue()) {
        Log.e("LibraryHelper", "Settings provider error", paramContext);
      }
    }
    return null;
  }
  
  public static String a(InputStream paramInputStream, String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream, paramString));
    for (;;)
    {
      paramString = paramInputStream.readLine();
      if (paramString == null) {
        break;
      }
      localStringBuilder.append(paramString);
    }
    return localStringBuilder.toString();
  }
  
  public static List<String> a(Context paramContext, String paramString, Set<String> paramSet, boolean paramBoolean)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(4096);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    label245:
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.requestedPermissions != null) && (!TextUtils.equals(localPackageInfo.packageName, paramContext.getPackageName())))
      {
        int k = 0;
        int j = 0;
        for (;;)
        {
          if (j >= localPackageInfo.requestedPermissions.length) {
            break label245;
          }
          if (TextUtils.equals(localPackageInfo.requestedPermissions[j], paramString))
          {
            int i = k;
            if (!a(paramSet))
            {
              Iterator localIterator = paramSet.iterator();
              String str;
              do
              {
                i = k;
                if (!localIterator.hasNext()) {
                  break;
                }
                str = (String)localIterator.next();
              } while (!localPackageInfo.packageName.startsWith(str));
              i = 1;
            }
            k = i;
            if (paramBoolean)
            {
              k = i;
              if (i == 0)
              {
                k = i;
                if (localPackageInfo.requestedPermissionsFlags != null)
                {
                  k = i;
                  if ((localPackageInfo.requestedPermissionsFlags[j] & 0x2) == 0) {
                    k = 1;
                  }
                }
              }
            }
            if (k != 0) {
              break;
            }
            localArrayList.add(localPackageInfo.packageName);
            break;
          }
          j += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static <K, V extends Comparable<? super V>> Map<K, V> a(Map<K, V> paramMap, boolean paramBoolean)
  {
    Object localObject = new LinkedList(paramMap.entrySet());
    if (paramBoolean) {
      Collections.sort((List)localObject, Collections.reverseOrder(-..Lambda.b.GjsIaAuMN4wPCAGRzuz8_YegSDc.INSTANCE));
    } else {
      Collections.sort((List)localObject, -..Lambda.b.BQKVaH5e2cisT4iRQYZLucs2gnA.INSTANCE);
    }
    paramMap = new LinkedHashMap();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      paramMap.put(localEntry.getKey(), localEntry.getValue());
    }
    return paramMap;
  }
  
  public static void a(Context paramContext, NotificationCompat.Builder paramBuilder, int paramInt, boolean paramBoolean)
  {
    paramBuilder = paramBuilder.build();
    if (paramBoolean)
    {
      paramBuilder.defaults |= 0x2;
      paramBuilder.sound = e(paramContext, "notification");
    }
    NotificationManagerCompat.from(paramContext).notify(paramInt, paramBuilder);
  }
  
  public static void a(Context paramContext, View paramView)
  {
    ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void a(Context paramContext, View paramView1, View paramView2, boolean paramBoolean)
  {
    int j = 0;
    int i;
    if (paramView1 != null)
    {
      if (paramBoolean) {
        i = 8;
      } else {
        i = 0;
      }
      paramView1.setVisibility(i);
    }
    if (paramView2 != null)
    {
      if (paramBoolean) {
        i = j;
      } else {
        i = 8;
      }
      paramView2.setVisibility(i);
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        if (paramString.contains("://"))
        {
          paramString = Uri.parse(paramString);
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("http://");
          ((StringBuilder)localObject).append(paramString);
          paramString = Uri.parse(((StringBuilder)localObject).toString());
        }
        Object localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setData(paramString);
        paramContext.startActivity((Intent)localObject);
        return;
      }
      catch (ActivityNotFoundException paramString)
      {
        Toast.makeText(paramContext, paramString.getLocalizedMessage(), 0).show();
      }
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2, Intent paramIntent, int paramInt3, String paramString3, int paramInt4, boolean paramBoolean)
  {
    a(paramContext, a(paramContext, paramString1, paramString2, paramInt1, paramInt2, paramIntent, paramInt3, paramString3), paramInt4, paramBoolean);
  }
  
  public static void a(Context paramContext, List<NotificationChannel> paramList)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      ((NotificationManager)paramContext.getSystemService(NotificationManager.class)).createNotificationChannels(paramList);
    }
  }
  
  public static void a(android.support.v7.app.a paramA, String paramString)
  {
    if (paramA != null)
    {
      paramA.c(true);
      paramA.a(paramString);
    }
  }
  
  /* Error */
  private static void a(List<String> paramList, java.io.Writer paramWriter)
    throws IOException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +103 -> 104
    //   4: aload_0
    //   5: invokeinterface 263 1 0
    //   10: astore_0
    //   11: aload_0
    //   12: invokeinterface 268 1 0
    //   17: ifeq +46 -> 63
    //   20: aload_0
    //   21: invokeinterface 271 1 0
    //   26: checkcast 189	java/lang/String
    //   29: astore_2
    //   30: new 201	java/lang/StringBuilder
    //   33: dup
    //   34: invokespecial 202	java/lang/StringBuilder:<init>	()V
    //   37: astore_3
    //   38: aload_3
    //   39: aload_2
    //   40: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_3
    //   45: ldc_w 478
    //   48: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: aload_1
    //   53: aload_3
    //   54: invokevirtual 211	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokevirtual 483	java/io/Writer:write	(Ljava/lang/String;)V
    //   60: goto -49 -> 11
    //   63: aload_1
    //   64: invokevirtual 486	java/io/Writer:flush	()V
    //   67: goto +26 -> 93
    //   70: astore_0
    //   71: goto +27 -> 98
    //   74: astore_0
    //   75: getstatic 160	net/safelagoon/library/BuildConfig:DEBUG_MODE	Ljava/lang/Boolean;
    //   78: invokevirtual 165	java/lang/Boolean:booleanValue	()Z
    //   81: ifeq +12 -> 93
    //   84: ldc -89
    //   86: ldc -87
    //   88: aload_0
    //   89: invokestatic 175	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   92: pop
    //   93: aload_1
    //   94: invokevirtual 489	java/io/Writer:close	()V
    //   97: return
    //   98: aload_1
    //   99: invokevirtual 489	java/io/Writer:close	()V
    //   102: aload_0
    //   103: athrow
    //   104: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	paramList	List<String>
    //   0	105	1	paramWriter	java.io.Writer
    //   29	11	2	str	String
    //   37	17	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   4	11	70	finally
    //   11	60	70	finally
    //   63	67	70	finally
    //   75	93	70	finally
    //   4	11	74	java/io/IOException
    //   11	60	74	java/io/IOException
    //   63	67	74	java/io/IOException
  }
  
  public static boolean a(CharSequence paramCharSequence)
  {
    return (!TextUtils.isEmpty(paramCharSequence)) && (paramCharSequence.length() >= 6);
  }
  
  public static boolean a(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    return (!TextUtils.isEmpty(paramCharSequence2)) && (TextUtils.equals(paramCharSequence1, paramCharSequence2));
  }
  
  public static boolean a(Collection paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  public static String b(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    String str = localTelephonyManager.getSimOperatorName();
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = localTelephonyManager.getNetworkOperatorName();
    }
    return paramContext;
  }
  
  public static void b(Context paramContext, int paramInt)
  {
    NotificationManagerCompat.from(paramContext).cancel(paramInt);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        if (paramString.contains("://"))
        {
          paramString = Uri.parse(paramString);
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("http://");
          ((StringBuilder)localObject).append(paramString);
          paramString = Uri.parse(((StringBuilder)localObject).toString());
        }
        Object localObject = new Intent("android.intent.action.VIEW", paramString);
        ((Intent)localObject).setDataAndType(paramString, "video/*");
        paramContext.startActivity((Intent)localObject);
        return;
      }
      catch (ActivityNotFoundException paramString)
      {
        Toast.makeText(paramContext, paramString.getLocalizedMessage(), 0).show();
      }
    }
  }
  
  public static boolean b()
  {
    return "mounted".equals(Environment.getExternalStorageState());
  }
  
  public static boolean b(CharSequence paramCharSequence)
  {
    return (!TextUtils.isEmpty(paramCharSequence)) && (TextUtils.indexOf(paramCharSequence, "@") > 0);
  }
  
  public static void c(Context paramContext, int paramInt)
  {
    final AudioManager localAudioManager = (AudioManager)paramContext.getSystemService("audio");
    Uri localUri = e(paramContext, "notification");
    MediaPlayer localMediaPlayer = new MediaPlayer();
    final int i = localAudioManager.getStreamVolume(4);
    try
    {
      localMediaPlayer.setDataSource(paramContext, localUri);
      localMediaPlayer.setAudioStreamType(4);
      localMediaPlayer.setLooping(false);
      localMediaPlayer.prepare();
      localMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
      {
        public void onPrepared(MediaPlayer paramAnonymousMediaPlayer)
        {
          this.a.setStreamVolume(4, this.a.getStreamMaxVolume(4), 4);
          paramAnonymousMediaPlayer.seekTo(0);
          paramAnonymousMediaPlayer.start();
        }
      });
      localMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
      {
        private int d = 0;
        
        public void onCompletion(MediaPlayer paramAnonymousMediaPlayer)
        {
          if (this.d < this.a)
          {
            this.d += 1;
            paramAnonymousMediaPlayer.seekTo(0);
            paramAnonymousMediaPlayer.start();
            return;
          }
          paramAnonymousMediaPlayer.stop();
          paramAnonymousMediaPlayer.reset();
          paramAnonymousMediaPlayer.release();
          localAudioManager.setStreamVolume(4, i, 4);
        }
      });
      return;
    }
    catch (IOException paramContext)
    {
      if (BuildConfig.DEBUG_MODE.booleanValue()) {
        Log.e("LibraryHelper", "Can't play a sound", paramContext);
      }
    }
  }
  
  public static void c(Context paramContext, String paramString)
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("vnd.youtube:");
      localStringBuilder.append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://www.youtube.com/watch?v=");
    localStringBuilder.append(paramString);
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, paramString.getLocalizedMessage(), 0).show();
      return;
    }
  }
  
  public static boolean c(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("com.google");
    localHashSet.add("com.android");
    return a(a(paramContext, "android.permission.SYSTEM_ALERT_WINDOW", localHashSet, true)) ^ true;
  }
  
  public static boolean c(CharSequence paramCharSequence)
  {
    return (!TextUtils.isEmpty(paramCharSequence)) && (Patterns.PHONE.matcher(paramCharSequence).matches());
  }
  
  public static String d(Context paramContext)
  {
    paramContext = a(paramContext, "net.safelagoon.lagoon2.permission.SET_CONNECTION", null, false);
    if (!a(paramContext)) {
      return (String)paramContext.get(0);
    }
    return null;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      Toast.makeText(paramContext, paramString.getLocalizedMessage(), 0).show();
      return;
    }
  }
  
  public static Uri e(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("android.resource");
    localStringBuilder.append(File.pathSeparator);
    localStringBuilder.append(File.separator);
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramContext.getPackageName());
    localStringBuilder.append("/raw/");
    localStringBuilder.append(paramString);
    return Uri.parse(localStringBuilder.toString());
  }
  
  public static boolean e(Context paramContext)
  {
    return d(paramContext) != null;
  }
  
  public static void f(Context paramContext)
  {
    d(paramContext, paramContext.getPackageName());
  }
  
  public static void g(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("market://details?id=");
    localStringBuilder.append(paramContext.getPackageName());
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    paramContext.startActivity(localIntent);
  }
  
  public static Activity h(Context paramContext)
  {
    Context localContext = paramContext;
    if ((paramContext instanceof Activity)) {
      return (Activity)paramContext;
    }
    while ((localContext instanceof ContextWrapper))
    {
      if ((localContext instanceof Activity)) {
        return (Activity)localContext;
      }
      localContext = ((ContextWrapper)localContext).getBaseContext();
    }
    return null;
  }
  
  public static void i(Context paramContext)
  {
    NotificationManagerCompat.from(paramContext).cancelAll();
  }
  
  public static boolean j(Context paramContext)
  {
    paramContext = k(paramContext);
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static NetworkInfo k(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext == null) {
      return null;
    }
    return paramContext.getActiveNetworkInfo();
  }
  
  public static boolean l(Context paramContext)
  {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardLocked();
  }
  
  public static void m(Context paramContext)
  {
    if (b())
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append(com.a.a.a.a.a());
      ((StringBuilder)localObject1).append(".txt");
      localObject1 = ((StringBuilder)localObject1).toString();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("SafeLagoon_");
      ((StringBuilder)localObject2).append(((String)localObject1).replaceAll("[^a-zA-Z0-9_\\\\-\\\\.]", "_"));
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
      if (f.b()) {
        while (f.b())
        {
          File localFile = a(f.a(), (String)localObject2, (String)localObject1);
          if ((localFile != null) && (localFile.exists())) {
            Toast.makeText(paramContext, String.format(paramContext.getString(a.f.user_logs_message), new Object[] { localFile.getAbsolutePath() }), 1).show();
          } else {
            Toast.makeText(paramContext, a.f.storage_exception, 0).show();
          }
        }
      }
      Toast.makeText(paramContext, a.f.user_logs_exception, 0).show();
      return;
    }
    Toast.makeText(paramContext, a.f.storage_exception, 0).show();
  }
}
