package com.joaomgcd.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Looper;
import android.os.PowerManager;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.BadTokenException;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.joaomgcd.common.a.f;
import com.joaomgcd.common.license.ServiceCheckLicense;
import com.joaomgcd.common.web.HttpRequest;
import com.joaomgcd.common.web.HttpResult;
import com.joaomgcd.common8.NotificationInfo;
import com.joaomgcd.common8.NotificationInfo.NotificationInfoActionType;
import com.joaomgcd.log.ActivityLogTabs;
import com.joaomgcd.systemicons.SystemIcon;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.InflaterInputStream;
import javax.crypto.Cipher;
import org.greenrobot.eventbus.EventBusException;

public class Util
{
  public static ArrayList<d> a = new ArrayList();
  public static HashMap<String, String> b = new HashMap();
  public static String c = "EXTRAssss";
  private static int d = 1;
  private static int e = 14441;
  private static String f = "retrycount";
  private static Pattern g = Pattern.compile("\"[^\"]+\"");
  private static Thread.UncaughtExceptionHandler h;
  private static q i;
  private static Boolean j;
  private static Cipher k;
  private static HashMap<Integer, String> l;
  
  static
  {
    a.add(new d("com.joaomgcd.intents", 100));
    a.add(new d("com.joaomgcd.autotalker", 50));
    a.add(new d("com.joaomgcd.barcode", 50));
  }
  
  public static Intent a(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setFlags(268435456);
    localIntent.setType("plain/text");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "support@joaoapps.com" });
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    b(paramContext.getString(w.g.achievement_buggy));
    paramContext = Intent.createChooser(localIntent, "Choose app to send bug report");
    paramContext.addFlags(268435456);
    return paramContext;
  }
  
  private static Intent a(Context paramContext, Throwable paramThrowable, String paramString)
  {
    StringBuilder localStringBuilder = b(paramContext, paramThrowable);
    return a(paramContext, a(paramThrowable, paramString), localStringBuilder.toString());
  }
  
  public static Intent a(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    return localIntent;
  }
  
  public static Intent a(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    Intent localIntent = a(paramString1, paramString2);
    if (paramString3 != null)
    {
      paramString2 = new File(paramString3);
      paramString1 = paramString2;
      if (!paramString2.exists()) {
        paramString1 = new File(paramString3.replace("file://", ""));
      }
      if (paramString1.exists())
      {
        paramString2 = af.j(paramString3);
        if (paramString2 != null)
        {
          paramString2 = paramString2.substring(1).toLowerCase();
          paramString2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(paramString2);
          if (b(paramString2)) {
            localIntent.setType(paramString2);
          }
        }
        c.h();
        if (paramBoolean) {
          paramString1 = a(paramString1);
        } else {
          paramString1 = Uri.parse(paramString3);
        }
        localIntent.putExtra("android.intent.extra.STREAM", paramString1);
      }
    }
    localIntent.setFlags(268435456);
    paramString1 = Intent.createChooser(localIntent, "Select app to share to");
    paramString1.setFlags(276824064);
    return paramString1;
  }
  
  public static Uri a(File paramFile)
  {
    c localC = c.h();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(localC.getPackageName());
    localStringBuilder.append(".fileprovider");
    return android.support.v4.content.c.a(localC, localStringBuilder.toString(), paramFile);
  }
  
  public static Bundle a(Object paramObject)
  {
    Bundle localBundle = new Bundle();
    a(localBundle, paramObject);
    return localBundle;
  }
  
  public static q a(Context paramContext, boolean paramBoolean)
  {
    try
    {
      paramContext = a(paramContext, paramBoolean, true);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static q a(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      if ((i == null) || (i.size() == 0) || (paramBoolean1))
      {
        q localQ = new q();
        paramContext = paramContext.getPackageManager();
        Iterator localIterator = paramContext.getInstalledApplications(0).iterator();
        while (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          p localP = new p();
          localP.b(localApplicationInfo.loadLabel(paramContext).toString());
          localP.a(localApplicationInfo.packageName);
          int n = 1;
          int m = n;
          if (!paramBoolean2)
          {
            m = n;
            if ((localApplicationInfo.flags & 0x1) != 0) {
              m = 0;
            }
          }
          if (m != 0) {
            localQ.add(localP);
          }
        }
        i = localQ;
        Collections.sort(i, new Comparator()
        {
          public int a(p paramAnonymousP1, p paramAnonymousP2)
          {
            return paramAnonymousP1.b().toLowerCase().compareTo(paramAnonymousP2.b().toLowerCase());
          }
        });
      }
      paramContext = i;
      return paramContext;
    }
    finally {}
  }
  
  public static Boolean a(String paramString, Boolean paramBoolean)
  {
    if (paramString != null)
    {
      if (paramString.equals("")) {
        return paramBoolean;
      }
      boolean bool;
      if ((!paramString.equals("true")) && (!paramString.equals("1"))) {
        bool = false;
      } else {
        bool = true;
      }
      return Boolean.valueOf(bool);
    }
    return paramBoolean;
  }
  
  public static CharSequence a(CharSequence paramCharSequence, String paramString)
  {
    try
    {
      paramString = b(paramCharSequence, paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      com.google.a.a.a.a.a.a.a(paramString);
    }
    return paramCharSequence;
  }
  
  public static <T extends Enum<T>> T a(Integer paramInteger, Class<T> paramClass)
  {
    return (Enum)a(paramInteger, (Enum[])paramClass.getEnumConstants());
  }
  
  public static <T extends Enum<T>> T a(String paramString, Class<T> paramClass)
  {
    return a(a(paramString, null), paramClass);
  }
  
  public static Float a(String paramString, Float paramFloat)
  {
    if (paramString != null) {
      if (paramString.equals("")) {
        return paramFloat;
      }
    }
    try
    {
      float f1 = Float.parseFloat(paramString);
      return Float.valueOf(f1);
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return paramFloat;
    return paramFloat;
  }
  
  public static <T extends Enum<T>> Integer a(T paramT, Class<T> paramClass)
  {
    return b(paramT, (Enum[])paramClass.getEnumConstants());
  }
  
  public static Integer a(String paramString, Integer paramInteger)
  {
    if (paramString != null) {
      if (paramString.equals("")) {
        return paramInteger;
      }
    }
    try
    {
      int m = Integer.parseInt(paramString);
      return Integer.valueOf(m);
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return paramInteger;
    return paramInteger;
  }
  
  public static Long a(String paramString, Long paramLong)
  {
    if (paramString != null) {
      if (paramString.equals("")) {
        return paramLong;
      }
    }
    try
    {
      long l1 = Long.parseLong(paramString);
      return Long.valueOf(l1);
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return paramLong;
    return paramLong;
  }
  
  public static <T> T a(Context paramContext, String paramString, Class<T> paramClass, f<String, T> paramF)
    throws Exception
  {
    paramContext = g(paramContext, paramString).getString("com.joaomgcd.common.EXTRA_JSON_PAYLOAD");
    if (paramContext == null) {
      return null;
    }
    if (paramF != null) {
      return paramF.call(paramContext);
    }
    return ag.a().a(paramContext, paramClass);
  }
  
  public static <T> T a(Intent paramIntent, Class<T> paramClass)
  {
    if (paramIntent == null) {
      return null;
    }
    paramIntent = paramIntent.getStringExtra("com.joaomgcd.common.EXTRA_JSON_PAYLOAD");
    if (paramIntent == null) {
      return null;
    }
    return ag.a().a(paramIntent, paramClass);
  }
  
  public static <TReturn> TReturn a(f<Integer, TReturn> paramF, int paramInt, long paramLong)
    throws Exception
  {
    int m = 0;
    Object localObject = null;
    while (m < paramInt)
    {
      int n = m + 1;
      try
      {
        localObject = paramF.call(Integer.valueOf(m));
        return localObject;
      }
      catch (Exception localException)
      {
        if (n < paramInt) {
          Thread.sleep(paramLong);
        }
        m = n;
      }
    }
    if (localException != null) {
      throw localException;
    }
    return null;
  }
  
  public static <T> T a(Integer paramInteger, T[] paramArrayOfT)
  {
    if ((paramArrayOfT != null) && (paramInteger != null) && (paramArrayOfT.length > paramInteger.intValue())) {
      return paramArrayOfT[paramInteger.intValue()];
    }
    return null;
  }
  
  public static <T> T a(T paramT, T... paramVarArgs)
  {
    int m = 0;
    int n = paramVarArgs.length;
    while (m < n)
    {
      T ? = paramVarArgs[m];
      if (? != null) {
        return ?;
      }
      m += 1;
    }
    return paramT;
  }
  
  public static String a()
  {
    c localC = c.h();
    return b(localC, localC.getPackageName(), null);
  }
  
  public static String a(c paramC)
    throws IOException
  {
    Object localObject2 = paramC.a();
    if (m((String)localObject2)) {
      return null;
    }
    Object localObject1 = localObject2;
    if (paramC.f())
    {
      localObject1 = localObject2;
      if (((String)localObject2).startsWith("/"))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("file://");
        ((StringBuilder)localObject1).append((String)localObject2);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    if (((String)localObject1).startsWith("file://")) {
      return af.b(c.h(), Uri.parse((String)localObject1));
    }
    int m = paramC.c();
    if (!paramC.e())
    {
      localObject2 = (HttpURLConnection)new URL((String)localObject1).openConnection();
      try
      {
        paramC.a((HttpURLConnection)localObject2);
        String str = a(((HttpURLConnection)localObject2).getInputStream(), true);
        return str;
      }
      catch (Exception localException)
      {
        if (localObject2 != null)
        {
          URL localURL = ((HttpURLConnection)localObject2).getURL();
          if ((a(new Object[] { localURL })) && (!localURL.toString().equals(localObject1))) {
            try
            {
              paramC.a(localURL.toURI().toString());
              paramC = a(paramC);
              return paramC;
            }
            catch (URISyntaxException paramC)
            {
              com.google.a.a.a.a.a.a.a(paramC);
            }
          }
        }
        if (localObject2 == null) {
          throw localException;
        }
        paramC = ((HttpURLConnection)localObject2).getErrorStream();
        if (paramC == null) {
          throw localException;
        }
      }
    }
    try
    {
      throw new a(((HttpURLConnection)localObject2).getResponseCode(), a(paramC));
      throw localException;
      return new HttpRequest().sendGetWebView(c.h(), (String)localObject1, m).getResult();
    }
    catch (IOException paramC)
    {
      for (;;) {}
    }
  }
  
  public static String a(InputStream paramInputStream)
    throws IOException
  {
    return a(paramInputStream, false);
  }
  
  public static String a(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    if (paramInputStream == null) {
      return null;
    }
    return b(paramInputStream, paramBoolean);
  }
  
  public static String a(Boolean paramBoolean)
  {
    if (paramBoolean == null) {
      return null;
    }
    if (paramBoolean.booleanValue()) {
      return "true";
    }
    return "false";
  }
  
  public static String a(Float paramFloat)
  {
    if (paramFloat == null) {
      return null;
    }
    return Float.toString(paramFloat.floatValue());
  }
  
  public static String a(Integer paramInteger)
  {
    if (paramInteger == null) {
      return null;
    }
    return Integer.toString(paramInteger.intValue());
  }
  
  public static String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Send the developer an email with this error: ");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }
  
  public static String a(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    int m = paramString1.length();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("%0");
    ((StringBuilder)localObject).append(paramInt - m);
    ((StringBuilder)localObject).append("d");
    localObject = String.format(((StringBuilder)localObject).toString(), new Object[] { Integer.valueOf(0) }).replace("0", paramString2);
    if (paramBoolean) {
      paramString2 = "";
    } else {
      paramString2 = (String)localObject;
    }
    if (!paramBoolean) {
      localObject = "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString2);
    localStringBuilder.append(paramString1);
    localStringBuilder.append((String)localObject);
    return localStringBuilder.toString();
  }
  
  public static String a(String paramString, boolean paramBoolean, int paramInt)
    throws IOException
  {
    return a((c)((c)((c)new c().a(paramString)).a(paramBoolean)).a(paramInt));
  }
  
  public static String a(String paramString, String[] paramArrayOfString)
  {
    int m = 0;
    while (m < paramArrayOfString.length)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("?<");
      localStringBuilder.append(paramArrayOfString[m]);
      localStringBuilder.append(">");
      paramString = paramString.replace(localStringBuilder.toString(), "");
      m += 1;
    }
    return paramString;
  }
  
  public static String a(Throwable paramThrowable, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(" - ");
    localStringBuilder.append(paramThrowable.toString());
    return localStringBuilder.toString();
  }
  
  public static String a(List<String> paramList)
  {
    return a(paramList, ",");
  }
  
  public static String a(List<String> paramList, String paramString)
  {
    return a(paramList, paramString, false);
  }
  
  public static <T> String a(List<T> paramList, String paramString, f<T, String> paramF)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramList != null) && (paramList.size() > 0))
    {
      int m = 0;
      while (m < paramList.size())
      {
        if (m > 0) {
          localStringBuilder.append(paramString);
        }
        Object localObject2 = paramList.get(m);
        Object localObject1 = null;
        if (paramF != null) {
          try
          {
            localObject2 = (String)paramF.call(localObject2);
            localObject1 = localObject2;
          }
          catch (Exception localException)
          {
            com.google.a.a.a.a.a.a.a(localException);
          }
        } else {
          localObject1 = localException.toString();
        }
        localStringBuilder.append((String)localObject1);
        m += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String a(List<String> paramList, String paramString, boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramList != null) && (paramList.size() > 0))
    {
      int m = 0;
      while (m < paramList.size())
      {
        String str2 = (String)paramList.get(m);
        String str1 = str2;
        if (paramBoolean)
        {
          str1 = str2;
          if (str2 == null) {
            str1 = "";
          }
        }
        if (str1 != null)
        {
          if (m > 0) {
            localStringBuilder.append(paramString);
          }
          localStringBuilder.append(str1);
        }
        m += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    return Base64.encodeToString(paramArrayOfByte, 2);
  }
  
  public static String a(String[] paramArrayOfString, String paramString)
  {
    if (paramArrayOfString == null) {
      return "";
    }
    return a(Arrays.asList(paramArrayOfString), paramString);
  }
  
  public static <T extends Annotation> T a(Class paramClass, Class<T> paramClass1)
  {
    paramClass = paramClass.getAnnotations();
    if ((paramClass != null) && (paramClass.length != 0)) {
      return a(paramClass, paramClass1);
    }
    return null;
  }
  
  public static <T extends Annotation> T a(Annotation[] paramArrayOfAnnotation, Class<T> paramClass)
  {
    int m = 0;
    int n = paramArrayOfAnnotation.length;
    while (m < n)
    {
      Annotation localAnnotation = paramArrayOfAnnotation[m];
      if (localAnnotation.annotationType().equals(paramClass)) {
        return localAnnotation;
      }
      m += 1;
    }
    return null;
  }
  
  public static ArrayList<String> a(String paramString1, String paramString2, boolean paramBoolean)
  {
    Object localObject = paramString2;
    if (paramString2 == null) {
      localObject = ",";
    }
    paramString2 = (String)localObject;
    if (((String)localObject).equals("|"))
    {
      paramString2 = new StringBuilder();
      paramString2.append("\\");
      paramString2.append((String)localObject);
      paramString2 = paramString2.toString();
    }
    if (!paramBoolean) {
      return k(paramString1);
    }
    localObject = new ArrayList();
    if (paramString1 != null) {
      ((ArrayList)localObject).addAll(Arrays.asList(paramString1.split(paramString2, -1)));
    }
    return localObject;
  }
  
  public static ArrayList<String> a(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject;
    if (paramString2 != null)
    {
      localObject = paramString2;
      if (!paramString2.equals("")) {}
    }
    else
    {
      localObject = ",";
    }
    ArrayList localArrayList = new ArrayList();
    if (b(paramString1))
    {
      localObject = paramString1.split(Pattern.quote((String)localObject));
      int n = localObject.length;
      int m = 0;
      while (m < n)
      {
        paramString2 = localObject[m];
        paramString1 = paramString2;
        if (paramBoolean1)
        {
          paramString1 = paramString2;
          if (paramString2 != null) {
            paramString1 = paramString2.trim();
          }
        }
        localArrayList.add(paramString1);
        m += 1;
      }
    }
    return localArrayList;
  }
  
  public static void a(int paramInt, Runnable paramRunnable, boolean paramBoolean)
  {
    b(paramInt * 1000, paramRunnable, paramBoolean);
  }
  
  public static void a(long paramLong)
  {
    try
    {
      Thread.sleep(paramLong);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      com.google.a.a.a.a.a.a.a(localInterruptedException);
    }
  }
  
  @TargetApi(26)
  public static void a(Activity paramActivity)
  {
    a(paramActivity.getWindow());
  }
  
  public static void a(Activity paramActivity, Object paramObject)
  {
    Intent localIntent = new Intent();
    a(localIntent, paramObject);
    paramActivity.setResult(-1, localIntent);
    paramActivity.finish();
  }
  
  public static void a(AlertDialog.Builder paramBuilder, final Runnable paramRunnable)
  {
    new ac().a(new Runnable()
    {
      public void run()
      {
        try
        {
          this.a.show();
          return;
        }
        catch (WindowManager.BadTokenException localBadTokenException)
        {
          com.google.a.a.a.a.a.a.a(localBadTokenException);
          ActivityLogTabs.a(localBadTokenException.toString(), "Dialogs");
          if (paramRunnable != null) {
            paramRunnable.run();
          }
        }
      }
    });
  }
  
  public static void a(Context paramContext, int paramInt)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancel(paramInt);
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, String paramString1, String paramString2, Intent paramIntent, Uri paramUri, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramContext = new NotificationInfo(paramContext).setId(Integer.toString(paramInt1)).setStatusBarIcon(new SystemIcon(paramInt2, "Exception Icon")).setTitle(paramString1).setText(paramString2).setAction(paramIntent).setSound(paramUri).setPersistent(paramBoolean2);
    if (!paramBoolean1) {
      paramContext.setActionIntentType(NotificationInfo.NotificationInfoActionType.Activity);
    }
    paramContext.notifyAutomaticType();
  }
  
  public static void a(Context paramContext, int paramInt1, int paramInt2, String paramString1, String paramString2, Intent paramIntent, boolean paramBoolean)
  {
    a(paramContext, paramInt1, paramInt2, paramString1, paramString2, paramIntent, null, paramBoolean, false);
  }
  
  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2)
  {
    a(paramContext, paramInt, paramString1, paramString2, new Intent(), false);
  }
  
  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2, Intent paramIntent, boolean paramBoolean)
  {
    a(paramContext, paramInt, paramString1, paramString2, paramIntent, paramBoolean, false);
  }
  
  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2, Intent paramIntent, boolean paramBoolean1, boolean paramBoolean2)
  {
    a(paramContext, paramInt, paramString1, paramString2, paramIntent, paramBoolean1, paramBoolean2, false);
  }
  
  public static void a(Context paramContext, int paramInt, String paramString1, String paramString2, Intent paramIntent, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    int m = 1;
    if (paramBoolean1)
    {
      m = 1 + d;
      d = m;
    }
    a(paramContext, m, paramInt, paramString1, paramString2, paramIntent, paramBoolean2);
  }
  
  public static void a(Context paramContext, BroadcastReceiver paramBroadcastReceiver)
  {
    if (paramBroadcastReceiver != null) {
      android.support.v4.content.e.a(paramContext).a(paramBroadcastReceiver);
    }
  }
  
  public static void a(Context paramContext, final com.joaomgcd.common.a.a<q> paramA, final boolean paramBoolean)
  {
    new Thread()
    {
      public void run()
      {
        Runnable local1 = new Runnable()
        {
          public void run()
          {
            Util.3.this.b.run(this.a);
          }
        };
        if (paramBoolean)
        {
          new ac().a(local1);
          return;
        }
        local1.run();
      }
    }.start();
  }
  
  public static void a(Context paramContext, Runnable paramRunnable)
  {
    String str1 = b(paramContext);
    if ((b(str1)) && (!str1.contains(".bf")))
    {
      String str2 = v.b(paramContext, "firstrundone");
      Object localObject = str2;
      if (str2 == null)
      {
        v.a(paramContext, "firstrundone", str1);
        localObject = str1;
      }
      if (!((String)localObject).equals(str1))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("showChangelogDialog::");
        ((StringBuilder)localObject).append(str1);
        b(paramContext, ((StringBuilder)localObject).toString(), paramRunnable);
      }
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return;
    }
    a(paramContext, paramString.hashCode());
  }
  
  public static void a(Context paramContext, String paramString, int paramInt)
  {
    a(paramContext, paramString, paramInt, 0);
  }
  
  private static void a(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    a(paramContext, paramString, paramInt1, paramInt2, 12.0F);
  }
  
  private static void a(Context paramContext, final String paramString, final int paramInt1, final int paramInt2, final float paramFloat)
  {
    new ac().a(new Runnable()
    {
      public void run()
      {
        View localView = LayoutInflater.from(this.a).inflate(w.e.toast_layout, null);
        ((ImageView)localView.findViewById(w.d.imageView_toast)).setImageResource(paramInt1);
        Object localObject = (TextView)localView.findViewById(w.d.textView_toast);
        ((TextView)localObject).setText(paramString);
        ((TextView)localObject).setTextSize(paramFloat);
        localObject = new Toast(this.a);
        ((Toast)localObject).setGravity(80, 0, 60);
        ((Toast)localObject).setDuration(paramInt2);
        ((Toast)localObject).setView(localView);
        ((Toast)localObject).show();
      }
    });
  }
  
  public static void a(Context paramContext, String paramString, Bundle paramBundle)
  {
    Intent localIntent = new Intent(paramString);
    if (paramBundle != null) {
      localIntent.putExtra("com.joaomgcd.common.EXTRA_ACTION_BUNDLE", paramBundle);
    }
    android.support.v4.content.e.a(paramContext).a(localIntent);
    paramContext = new StringBuilder();
    paramContext.append("Reported: ");
    paramContext.append(paramString);
    Log.v("SYNCACTIONS", paramContext.toString());
  }
  
  public static void a(Context paramContext, String paramString, com.joaomgcd.common.a.a<Bundle> paramA)
  {
    android.support.v4.content.e.a(paramContext).a(new BroadcastReceiver()new IntentFilter
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        android.support.v4.content.e.a(paramAnonymousContext).a(this);
        paramAnonymousContext = paramAnonymousIntent.getBundleExtra("com.joaomgcd.common.EXTRA_ACTION_BUNDLE");
        this.a.run(paramAnonymousContext);
      }
    }, new IntentFilter(paramString));
  }
  
  public static void a(Context paramContext, String paramString, com.joaomgcd.common.a.a<BroadcastReceiver> paramA, com.joaomgcd.common.a.a<Bundle> paramA1)
  {
    paramA1 = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        paramAnonymousContext = paramAnonymousIntent.getBundleExtra("com.joaomgcd.common.EXTRA_ACTION_BUNDLE");
        this.a.run(paramAnonymousContext);
      }
    };
    android.support.v4.content.e.a(paramContext).a(paramA1, new IntentFilter(paramString));
    paramA.run(paramA1);
  }
  
  public static void a(Context paramContext, String paramString, Object paramObject)
  {
    Bundle localBundle = new Bundle();
    if (paramObject != null) {
      paramObject = ag.a().a(paramObject);
    } else {
      paramObject = null;
    }
    localBundle.putString("com.joaomgcd.common.EXTRA_JSON_PAYLOAD", paramObject);
    a(paramContext, paramString, localBundle);
  }
  
  public static void a(Context paramContext, String paramString, Runnable paramRunnable)
  {
    android.support.v4.content.e.a(paramContext).a(new BroadcastReceiver()new IntentFilter
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        android.support.v4.content.e.a(paramAnonymousContext).a(this);
        this.a.run();
      }
    }, new IntentFilter(paramString));
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    a(paramContext, paramString1, paramString2, paramString3, null, false);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    paramContext.startActivity(Intent.createChooser(a(paramString2, paramString3, paramString4, paramBoolean), paramString1));
  }
  
  public static void a(Context paramContext, StringBuilder paramStringBuilder)
  {
    if (paramContext != null) {}
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(" version ");
      localStringBuilder.append(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName);
      localStringBuilder.append("\n\n");
      paramStringBuilder.append(localStringBuilder.toString());
      paramContext = new StringBuilder();
      paramContext.append(" OS Build ");
      paramContext.append(Build.DISPLAY);
      paramContext.append("\n");
      paramStringBuilder.append(paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append(" OS Code ");
      paramContext.append(Build.VERSION.SDK_INT);
      paramContext.append("\n");
      paramStringBuilder.append(paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append(" Device ");
      paramContext.append(Build.MODEL);
      paramContext.append("\n");
      paramStringBuilder.append(paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append(" Manufacturer ");
      paramContext.append(Build.MANUFACTURER);
      paramContext.append("\n");
      paramStringBuilder.append(paramContext.toString());
      paramContext = new StringBuilder();
      paramContext.append(" Product ");
      paramContext.append(Build.PRODUCT);
      paramContext.append("\n");
      paramStringBuilder.append(paramContext.toString());
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
  }
  
  public static void a(Context paramContext, Throwable paramThrowable)
  {
    a(paramContext, new HashMap(), paramThrowable, w.c.ic_launcher, null, "black", paramContext.getString(w.g.app_name));
  }
  
  public static void a(Context paramContext, ArrayList<String> paramArrayList, String paramString)
  {
    Intent localIntent = new Intent("com.joaomgcd.autovoice.ACTION_AUTOVOICE_RECOGNIZED");
    localIntent.setComponent(new ComponentName("com.joaomgcd.autovoice", "com.joaomgcd.autovoice.broadcastreceiver.BroadcastReceiverThirdParty"));
    localIntent.putExtra("android.speech.extra.RESULTS", paramArrayList);
    localIntent.putExtra("com.joaomgcd.autovoice.EXTRA_SOURCE", paramString);
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void a(Context paramContext, HashMap<Class<?>, String> paramHashMap, Throwable paramThrowable, int paramInt, Uri paramUri, String paramString1, String paramString2)
  {
    paramString1 = b(paramThrowable);
    ActivityLogTabs.a(paramContext, paramString1);
    com.google.a.a.a.a.a.a.a(paramThrowable);
    paramUri = paramThrowable;
    if ((paramThrowable instanceof EventBusException))
    {
      localObject = paramThrowable.getCause();
      paramUri = paramThrowable;
      if (localObject != null) {
        paramUri = (Uri)localObject;
      }
    }
    Object localObject = paramUri.getClass();
    paramThrowable = null;
    if (paramHashMap.containsKey(localObject))
    {
      paramUri = (String)paramHashMap.get(localObject);
      paramString1 = "Error";
      paramHashMap = paramThrowable;
      paramThrowable = paramString1;
    }
    else
    {
      paramThrowable = paramUri.toString();
      paramHashMap = a(paramContext, paramUri, paramString2);
      paramUri = paramString1;
    }
    new NotificationInfo(paramContext).setChannelId("Errors").setStatusBarIcon(paramInt).setTitle(paramThrowable).setText(paramUri).setAction(paramHashMap).setActionIntentType(NotificationInfo.NotificationInfoActionType.Activity).notifyAutomaticType();
  }
  
  public static void a(Context paramContext, HashMap<String, String> paramHashMap, Throwable paramThrowable, boolean paramBoolean, int paramInt, Uri paramUri, String paramString1, String paramString2)
  {
    com.google.a.a.a.a.a.a.a(paramThrowable);
    if (paramThrowable != null)
    {
      paramUri = paramThrowable.toString().trim();
      boolean bool = false;
      if (paramBoolean)
      {
        paramString1 = paramHashMap.keySet().iterator();
        do
        {
          paramBoolean = bool;
          if (!paramString1.hasNext()) {
            break;
          }
        } while (!paramUri.startsWith((String)paramString1.next()));
        paramBoolean = true;
      }
      else
      {
        paramBoolean = paramHashMap.containsKey(paramUri);
      }
      if (paramBoolean)
      {
        paramHashMap = (String)paramHashMap.get(paramUri);
        if (paramHashMap != null) {
          a(paramContext, paramInt, "Error", paramHashMap);
        }
      }
      else
      {
        a(paramContext, paramInt, paramThrowable.toString(), b(paramThrowable), a(paramContext, paramThrowable, paramString2), true);
      }
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean, Intent paramIntent, e paramE)
  {
    if (!Activity.class.isAssignableFrom(paramContext.getClass())) {
      paramIntent.addFlags(268435456);
    }
    if (!paramBoolean) {
      paramIntent.addFlags(1082130432);
    }
    if (paramE != null)
    {
      int m = paramE.b();
      if (m != 0) {
        paramIntent.addFlags(m);
      }
    }
    if (paramE != null)
    {
      paramE = paramE.a();
      if (b(paramE)) {
        paramIntent.setAction(paramE);
      }
    }
    paramContext.startActivity(paramIntent);
  }
  
  public static void a(DialogInterface paramDialogInterface)
  {
    if (paramDialogInterface != null) {
      new ac().a(new ad(paramDialogInterface));
    }
  }
  
  private static void a(Intent paramIntent, Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    paramIntent.putExtra("com.joaomgcd.common.EXTRA_JSON_PAYLOAD", ag.a().a(paramObject));
  }
  
  private static void a(Bundle paramBundle, Object paramObject)
  {
    paramBundle.putString("com.joaomgcd.common.EXTRA_JSON_PAYLOAD", ag.a().a(paramObject));
  }
  
  public static void a(Window paramWindow)
  {
    paramWindow.addFlags(6815872);
  }
  
  public static void a(com.joaomgcd.common.a.a<Throwable> paramA)
  {
    if (h == null)
    {
      h = Thread.getDefaultUncaughtExceptionHandler();
      Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
      {
        public void uncaughtException(Thread paramAnonymousThread, Throwable paramAnonymousThrowable)
        {
          this.a.run(paramAnonymousThrowable);
          Util.d().uncaughtException(paramAnonymousThread, paramAnonymousThrowable);
        }
      });
    }
  }
  
  public static void a(final String paramString, final int paramInt, com.joaomgcd.common.a.a<String> paramA, final com.joaomgcd.common.a.a<Throwable> paramA1)
  {
    new i()
    {
      protected void a()
      {
        try
        {
          this.a.run(Util.a(paramString, true, paramInt));
          return;
        }
        catch (IOException localIOException)
        {
          com.google.a.a.a.a.a.a.a(localIOException);
          paramA1.run(localIOException);
        }
      }
    };
  }
  
  public static void a(String paramString, HttpURLConnection paramHttpURLConnection)
  {
    paramHttpURLConnection.addRequestProperty("Cookie", CookieManager.getInstance().getCookie(paramString));
  }
  
  public static void a(StringBuilder paramStringBuilder, String paramString1, String paramString2, boolean paramBoolean)
  {
    if ((paramString2 != null) && (!paramString2.equals("")))
    {
      if (paramStringBuilder.length() > 0) {
        paramStringBuilder.append("\n");
      }
      Object localObject = paramString2;
      if (paramBoolean)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("\"");
        ((StringBuilder)localObject).append(paramString2);
        ((StringBuilder)localObject).append("\"");
        localObject = ((StringBuilder)localObject).toString();
      }
      paramString2 = new StringBuilder();
      paramString2.append(paramString1);
      paramString2.append(": ");
      paramString2.append((String)localObject);
      paramStringBuilder.append(paramString2.toString());
    }
  }
  
  public static void a(Throwable paramThrowable)
  {
    a(c.h(), paramThrowable);
  }
  
  public static void a(URL paramURL, URLConnection paramURLConnection)
  {
    paramURL = paramURL.getUserInfo();
    if (b(paramURL))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Basic ");
      localStringBuilder.append(i(paramURL));
      paramURLConnection.setRequestProperty("Authorization", localStringBuilder.toString());
    }
  }
  
  @TargetApi(23)
  public static boolean a(Activity paramActivity, int paramInt, String... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    boolean bool = false;
    int n = paramVarArgs.length;
    int m = 0;
    while (m < n)
    {
      String str = paramVarArgs[m];
      if (!a(paramActivity, new String[] { str })) {
        localArrayList.add(str);
      }
      m += 1;
    }
    if (localArrayList.size() == 0) {
      bool = true;
    }
    if (!bool) {
      paramActivity.requestPermissions((String[])localArrayList.toArray(new String[localArrayList.size()]), paramInt);
    }
    return bool;
  }
  
  @TargetApi(23)
  public static boolean a(Activity paramActivity, String... paramVarArgs)
  {
    return a(paramActivity, 241, paramVarArgs);
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static boolean a(Context paramContext, int paramInt1, int paramInt2, String[] paramArrayOfString, int[] paramArrayOfInt, boolean paramBoolean)
  {
    if (paramInt1 == paramInt2)
    {
      if (ak.a(paramContext, ae.a, paramArrayOfInt))
      {
        if (paramBoolean) {
          e(paramContext, "Permissions granted");
        }
        return true;
      }
      if (paramBoolean) {
        e(paramContext, "Permissions denied");
      }
    }
    return false;
  }
  
  @TargetApi(23)
  public static boolean a(Context paramContext, int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt, boolean paramBoolean)
  {
    return a(paramContext, paramInt, 241, paramArrayOfString, paramArrayOfInt, paramBoolean);
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    return a(paramContext, paramString1, paramString2, paramString3, paramBoolean1, paramBoolean2, paramBoolean3, null);
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, HashMap<String, String> paramHashMap)
  {
    return a(paramContext, paramString1, paramString2, paramString3, paramBoolean1, paramBoolean2, paramBoolean3, paramHashMap, false);
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, HashMap<String, String> paramHashMap, boolean paramBoolean4)
  {
    return a(paramContext, paramString1, paramString2, paramString3, paramBoolean1, paramBoolean2, paramBoolean3, paramHashMap, paramBoolean4, false);
  }
  
  public static boolean a(Context paramContext, String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, HashMap<String, String> paramHashMap, boolean paramBoolean4, boolean paramBoolean5)
  {
    boolean bool2 = true;
    boolean bool1 = true;
    if ((paramString1 == null) && (paramString2 == null)) {
      return true;
    }
    if ((paramString1 != null) && (!paramString1.equals(paramString3)))
    {
      if (paramString2 == null) {
        return true;
      }
      if (paramBoolean3)
      {
        StringBuilder localStringBuilder = null;
        String[] arrayOfString = b(paramString2);
        String str = a(paramString2, arrayOfString);
        try
        {
          paramString2 = Pattern.compile(str).matcher(paramString1);
          try
          {
            paramBoolean1 = paramString2.find();
            paramContext = paramString2;
          }
          catch (PatternSyntaxException paramString3) {}
          paramContext = new NotificationInfo(paramContext).setTitle("Bad Regex syntax");
        }
        catch (PatternSyntaxException paramString3)
        {
          paramString2 = localStringBuilder;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(" is not a valid regex: ");
        localStringBuilder.append(paramString3.getMessage());
        paramContext.setText(localStringBuilder.toString()).setId(str).notifyAutomaticType();
        paramContext = paramString2;
        paramBoolean1 = false;
        int m;
        if (paramHashMap != null) {
          m = 1;
        } else {
          m = 0;
        }
        if ((paramBoolean1) && (m != 0)) {
          if (!paramBoolean5)
          {
            if (paramContext != null)
            {
              paramHashMap.put("regexmatch", paramContext.group());
              m = 1;
              while (m < paramContext.groupCount() + 1)
              {
                paramString2 = paramContext.group(m);
                paramString3 = new StringBuilder();
                paramString3.append("regexgroups");
                paramString3.append(m);
                paramHashMap.put(paramString3.toString(), paramString2);
                m += 1;
              }
            }
            paramContext = d(paramString1, str);
            if (paramContext.length > 0)
            {
              m = 0;
              while (m < arrayOfString.length)
              {
                paramString1 = arrayOfString[m];
                paramString2 = paramContext[0];
                m += 1;
                paramHashMap.put(paramString1, paramString2[m]);
              }
            }
          }
          else
          {
            paramString1 = d(paramString1, str);
            int i1;
            for (m = 0;; m = i1)
            {
              i1 = m + 1;
              paramString2 = new StringBuilder();
              paramString2.append("regexmatch");
              paramString2.append(i1);
              paramHashMap.put(paramString2.toString(), paramContext.group());
              int n = 1;
              while (n < paramContext.groupCount() + 1)
              {
                paramString2 = paramContext.group(n);
                paramString3 = new StringBuilder();
                paramString3.append("regexgroups");
                paramString3.append(n);
                paramHashMap.put(paramString3.toString(), paramString2);
                n += 1;
              }
              n = 0;
              while (n < arrayOfString.length)
              {
                paramString2 = arrayOfString[n];
                paramString3 = new StringBuilder();
                paramString3.append(paramString2);
                paramString3.append(i1);
                paramString2 = paramString3.toString();
                paramString3 = paramString1[m];
                n += 1;
                paramHashMap.put(paramString2, paramString3[n]);
              }
              if (!paramContext.find()) {
                break;
              }
            }
          }
        }
        return paramBoolean1;
      }
      paramString3 = paramString1;
      paramContext = paramString2;
      if (paramBoolean2)
      {
        paramString3 = paramString1.toLowerCase();
        paramContext = paramString2.toLowerCase();
      }
      if (paramBoolean1)
      {
        if (paramString3.equals(paramContext)) {
          return true;
        }
      }
      else
      {
        if (paramBoolean4)
        {
          paramBoolean1 = bool2;
          paramString1 = paramContext;
          if (paramContext.contains("\""))
          {
            paramString2 = g.matcher(paramContext);
            paramBoolean2 = bool1;
            for (;;)
            {
              paramBoolean1 = paramBoolean2;
              paramString1 = paramContext;
              if (!paramString2.find()) {
                break;
              }
              paramString1 = paramString2.group();
              if (!paramString3.contains(paramString1.replace("\"", ""))) {
                paramBoolean2 = false;
              }
              paramContext = paramContext.replace(paramString1, "");
            }
          }
          paramContext = new StringTokenizer(paramString1, " ");
          for (;;)
          {
            paramBoolean2 = paramBoolean1;
            if (!paramContext.hasMoreElements()) {
              break;
            }
            paramString1 = paramContext.nextToken();
            if ((!paramString1.equals("")) && (!paramString3.contains(paramString1))) {
              paramBoolean1 = false;
            }
          }
        }
        if (paramString3.contains(paramContext)) {
          return true;
        }
      }
    }
    paramBoolean2 = false;
    return paramBoolean2;
  }
  
  public static boolean a(Context paramContext, String paramString1, boolean paramBoolean, String paramString2)
  {
    ServiceCheckLicense.a(paramContext, paramString1, paramString2);
    return ServiceCheckLicense.a(paramContext, paramString1, paramBoolean);
  }
  
  @TargetApi(23)
  public static boolean a(Context paramContext, String... paramVarArgs)
  {
    if (paramVarArgs != null)
    {
      if (paramVarArgs.length == 0) {
        return true;
      }
      if (com.joaomgcd.common8.a.c(23)) {
        return true;
      }
      ak.a(paramContext, new f()
      {
        public Boolean a(String paramAnonymousString)
          throws Exception
        {
          boolean bool;
          if (this.a.checkSelfPermission(paramAnonymousString) == 0) {
            bool = true;
          } else {
            bool = false;
          }
          return Boolean.valueOf(bool);
        }
      }, Arrays.asList(paramVarArgs));
    }
    return true;
  }
  
  public static boolean a(CharSequence paramCharSequence)
  {
    return (paramCharSequence == null) || (paramCharSequence.toString().equals(""));
  }
  
  public static boolean a(Object... paramVarArgs)
  {
    return c(Arrays.asList(paramVarArgs));
  }
  
  public static int b(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public static AlertDialog b(AlertDialog.Builder paramBuilder, Runnable paramRunnable)
  {
    try
    {
      paramBuilder = paramBuilder.show();
      return paramBuilder;
    }
    catch (WindowManager.BadTokenException paramBuilder)
    {
      com.google.a.a.a.a.a.a.a(paramBuilder);
      ActivityLogTabs.a(paramBuilder.toString(), "Dialogs");
      if (paramRunnable != null) {
        paramRunnable.run();
      }
    }
    return null;
  }
  
  public static Intent b(Object paramObject)
  {
    Intent localIntent = new Intent();
    a(localIntent, paramObject);
    return localIntent;
  }
  
  public static h b(Context paramContext, String paramString, int paramInt)
  {
    paramContext = new h.a(paramContext, paramString);
    if (paramInt != -1) {
      paramContext.setTimeOutMillis(Integer.valueOf(paramInt));
    }
    return new h(new h.b(paramContext));
  }
  
  public static CharSequence b(CharSequence paramCharSequence, String paramString)
    throws Exception
  {
    if (paramString != null)
    {
      if (a(paramCharSequence)) {
        return paramCharSequence;
      }
      paramString = new File(paramString);
      if (!paramString.exists()) {
        throw new Exception("Font file doesn't exist");
      }
      try
      {
        paramString = Typeface.createFromFile(paramString);
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
        localSpannableStringBuilder.setSpan(new CustomTypeFaceSpan(paramString), 0, paramCharSequence.length(), 18);
        return localSpannableStringBuilder;
      }
      catch (Exception paramCharSequence)
      {
        paramString = new StringBuilder();
        paramString.append("Font file is not valid: ");
        paramString.append(paramCharSequence.toString());
        throw new Exception(paramString.toString());
      }
    }
    return paramCharSequence;
  }
  
  public static <T> Integer b(T paramT, T[] paramArrayOfT)
  {
    int m = 0;
    while (m < paramArrayOfT.length)
    {
      if (paramArrayOfT[m] == paramT) {
        return Integer.valueOf(m);
      }
      m += 1;
    }
    return null;
  }
  
  public static Integer b(String paramString, Integer paramInteger)
  {
    if ((paramString != null) && (!paramString.equals(""))) {
      return Integer.valueOf(Color.parseColor(paramString));
    }
    return paramInteger;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      String str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    return "?";
  }
  
  public static String b(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString1 != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      paramString1 = paramContext.getApplicationInfo(paramString1, 0);
      if (paramString1 != null)
      {
        paramContext = paramContext.getApplicationLabel(paramString1);
        if (paramContext != null)
        {
          paramContext = paramContext.toString();
          return paramContext;
        }
      }
      return paramString2;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return paramString2;
  }
  
  public static String b(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      String str = localBufferedReader.readLine();
      if (str == null) {
        break;
      }
      localStringBuffer.append(str);
      if (paramBoolean) {
        localStringBuffer.append("\n");
      }
    }
    paramInputStream.close();
    return localStringBuffer.toString();
  }
  
  public static <T extends Enum<T>> String b(T paramT, Class<T> paramClass)
  {
    paramT = a(paramT, paramClass);
    if (paramT != null) {
      return Integer.toString(paramT.intValue());
    }
    return null;
  }
  
  public static String b(Integer paramInteger)
  {
    if (paramInteger == null) {
      return null;
    }
    if (l == null)
    {
      l = new HashMap();
      l.put(Integer.valueOf(26), "Oreo (8.0)");
      l.put(Integer.valueOf(25), "Nougat (7.1)");
      l.put(Integer.valueOf(24), "Nougat (7.0)");
      l.put(Integer.valueOf(23), "Marshmallow (6.0)");
      l.put(Integer.valueOf(22), "Lollipop (5.1)");
      l.put(Integer.valueOf(21), "Lollipop (5.0)");
      l.put(Integer.valueOf(19), "KitKat (4.4 - 4.4.4)");
      l.put(Integer.valueOf(18), "Jelly Bean (4.3.x)");
      l.put(Integer.valueOf(17), "Jelly Bean (4.2.x)");
      l.put(Integer.valueOf(16), "Jelly Bean (4.1.x)");
      l.put(Integer.valueOf(15), "Ice Cream Sandwich (4.0.3 - 4.0.4)");
      l.put(Integer.valueOf(14), "Ice Cream Sandwich (4.0.1 - 4.0.2)");
      l.put(Integer.valueOf(13), "Honeycomb (3.2.x)");
      l.put(Integer.valueOf(12), "Honeycomb (3.1)");
      l.put(Integer.valueOf(11), "Honeycomb (3.0)");
      l.put(Integer.valueOf(10), "Gingerbread (2.3.3 - 2.3.7)");
      l.put(Integer.valueOf(9), "Gingerbread (2.3 - 2.3.2)");
      l.put(Integer.valueOf(8), "Froyo (2.2.x)");
      l.put(Integer.valueOf(7), "Eclair (2.1)");
      l.put(Integer.valueOf(6), "Eclair (2.0.1)");
      l.put(Integer.valueOf(5), "Eclair (2.0)");
      l.put(Integer.valueOf(4), "Donut (1.6)");
      l.put(Integer.valueOf(3), "Cupcake (1.5)");
    }
    return (String)l.get(paramInteger);
  }
  
  public static String b(Throwable paramThrowable)
  {
    return a(paramThrowable.getMessage());
  }
  
  public static String b(String[] paramArrayOfString, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      int m = 0;
      while (m < paramArrayOfString.length)
      {
        if (m > 0) {
          localStringBuilder.append(paramString);
        }
        localStringBuilder.append(paramArrayOfString[m]);
        m += 1;
      }
    }
    return localStringBuilder.toString();
  }
  
  public static StringBuilder b(Context paramContext, Throwable paramThrowable)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getPackageName());
    a(paramContext, localStringBuilder);
    localStringBuilder.append("\n");
    paramContext = new StringBuilder();
    paramContext.append(c(paramThrowable));
    paramContext.append("\n");
    localStringBuilder.append(paramContext.toString());
    return localStringBuilder;
  }
  
  public static void b(int paramInt, final Runnable paramRunnable, final boolean paramBoolean)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          Thread.sleep(this.a);
          if (paramBoolean)
          {
            new ac().a(paramRunnable);
            return;
          }
          paramRunnable.run();
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;) {}
        }
      }
    }.start();
  }
  
  public static void b(Context paramContext, String paramString)
  {
    paramString = e(paramString);
    try
    {
      paramContext.startActivity(paramString);
      return;
    }
    catch (ActivityNotFoundException paramString)
    {
      for (;;) {}
    }
    c(paramContext, "Couldn't open web page.\n\nDo you have a web browser installed?");
  }
  
  public static void b(Context paramContext, String paramString, Runnable paramRunnable)
  {
    if (!i(paramContext, paramString))
    {
      v.a(paramContext, paramString, true);
      paramRunnable.run();
    }
  }
  
  public static boolean b()
  {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }
  
  public static boolean b(CharSequence paramCharSequence)
  {
    return a(paramCharSequence) ^ true;
  }
  
  public static boolean b(List paramList)
  {
    int m;
    if ((paramList != null) && (paramList.size() != 0)) {
      m = 0;
    } else {
      m = 1;
    }
    if (m != 0) {
      return true;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Object localObject = paramList.next();
      if ((localObject != null) && (b(localObject.toString()))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean b(Object... paramVarArgs)
  {
    return b(Arrays.asList(paramVarArgs));
  }
  
  public static byte[] b(byte[] paramArrayOfByte)
  {
    paramArrayOfByte = new InflaterInputStream(new ByteArrayInputStream(paramArrayOfByte));
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      byte[] arrayOfByte = new byte[' '];
      for (;;)
      {
        int m = paramArrayOfByte.read(arrayOfByte);
        if (m <= 0) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, m);
      }
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new AssertionError(paramArrayOfByte);
    }
  }
  
  public static String[] b(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null)
    {
      paramString = Pattern.compile("\\(\\?<([^>]+)>").matcher(paramString);
      paramString.groupCount();
      while (paramString.find()) {
        localArrayList.add(paramString.group(1));
      }
    }
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }
  
  public static String[] b(String paramString1, String paramString2)
  {
    paramString1 = c(paramString1, paramString2);
    return (String[])paramString1.toArray(new String[paramString1.size()]);
  }
  
  public static q c(Context paramContext)
  {
    try
    {
      paramContext = a(paramContext, false);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static Integer c(String paramString, Integer paramInteger)
  {
    if (m(paramString)) {
      return paramInteger;
    }
    try
    {
      int m = Color.parseColor(paramString.trim());
      return Integer.valueOf(m);
    }
    catch (Exception paramString) {}
    return paramInteger;
  }
  
  public static String c(String paramString)
  {
    Uri localUri = d(paramString);
    if (localUri == null) {
      return paramString;
    }
    return localUri.toString();
  }
  
  public static String c(Throwable paramThrowable)
  {
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    com.google.a.a.a.a.a.a.a(paramThrowable, localPrintWriter);
    localPrintWriter.flush();
    return localStringWriter.toString();
  }
  
  public static ArrayList<String> c(String paramString1, String paramString2)
  {
    return a(paramString1, paramString2, false, false);
  }
  
  public static void c(Context paramContext, String paramString)
  {
    a(paramContext, paramString, w.c.ic_launcher, 1);
  }
  
  private static void c(Context paramContext, final String paramString, final int paramInt)
  {
    new ac().a(new Runnable()
    {
      public void run()
      {
        Toast.makeText(this.a, paramString, paramInt).show();
      }
    });
  }
  
  public static boolean c()
  {
    return Debug.isDebuggerConnected();
  }
  
  public static boolean c(List paramList)
  {
    return b(paramList) ^ true;
  }
  
  public static Uri d(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    paramString = new File(paramString.replace("file://", ""));
    if (paramString.exists()) {
      return a(paramString);
    }
    return null;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    c(paramContext, paramString, 0);
  }
  
  public static void d(Throwable paramThrowable)
  {
    c localC = c.h();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Error: ");
    localStringBuilder.append(paramThrowable.toString());
    c(localC, localStringBuilder.toString());
  }
  
  public static boolean d(Context paramContext)
  {
    if (j == null) {
      j = Boolean.valueOf(false);
    }
    return j.booleanValue();
  }
  
  private static String[][] d(String paramString1, String paramString2)
  {
    paramString1 = Pattern.compile(paramString2).matcher(paramString1);
    int n = paramString1.groupCount() + 1;
    paramString2 = new ArrayList();
    while (paramString1.find())
    {
      String[] arrayOfString = new String[n];
      int m = 0;
      while (m < n)
      {
        arrayOfString[m] = paramString1.group(m);
        m += 1;
      }
      paramString2.add(arrayOfString);
    }
    return (String[][])paramString2.toArray(new String[paramString2.size()][]);
  }
  
  public static Intent e(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.addCategory("android.intent.category.BROWSABLE");
    paramString.setFlags(268435456);
    return paramString;
  }
  
  public static void e(Context paramContext, String paramString)
  {
    a(paramContext, paramString, w.c.ic_launcher, 0);
  }
  
  @TargetApi(21)
  public static boolean e(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (com.joaomgcd.common8.a.a(21)) {
      return paramContext.isInteractive();
    }
    return paramContext.isScreenOn();
  }
  
  public static String f(String paramString)
  {
    String str = paramString;
    if ("".equals(paramString)) {
      str = null;
    }
    return str;
  }
  
  @TargetApi(16)
  public static boolean f(Context paramContext)
  {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardLocked() ^ true;
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException|Throwable paramContext) {}
    return false;
  }
  
  public static Bundle g(Context paramContext, String paramString)
  {
    return (Bundle)new g(new g.b(new g.a(paramContext, paramString))).getNoExceptions();
  }
  
  public static Integer g(String paramString)
  {
    return c(paramString, null);
  }
  
  @TargetApi(16)
  public static boolean g(Context paramContext)
  {
    return ((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardSecure();
  }
  
  public static int h(String paramString)
  {
    return a(paramString, Integer.valueOf(1000)).intValue();
  }
  
  @TargetApi(22)
  public static Boolean h(Context paramContext)
  {
    if (com.joaomgcd.common8.a.c(22)) {
      return null;
    }
    return Boolean.valueOf(((KeyguardManager)paramContext.getSystemService("keyguard")).isDeviceLocked());
  }
  
  public static void h(Context paramContext, String paramString)
  {
    a(paramContext, paramString, null);
  }
  
  public static String i(String paramString)
  {
    return a(paramString.getBytes());
  }
  
  public static boolean i(Context paramContext, String paramString)
  {
    return v.c(paramContext, paramString);
  }
  
  public static String j(Context paramContext, String paramString)
  {
    return b(paramContext, paramString, null);
  }
  
  public static byte[] j(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Base64.decode(paramString, 0);
  }
  
  public static ArrayList<String> k(String paramString)
  {
    return c(paramString, null);
  }
  
  public static Map<String, String> l(String paramString)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    paramString = paramString.split("&");
    int n = paramString.length;
    int m = 0;
    while (m < n)
    {
      Object localObject = paramString[m];
      int i1 = localObject.indexOf("=");
      try
      {
        localLinkedHashMap.put(URLDecoder.decode(localObject.substring(0, i1), "UTF-8"), URLDecoder.decode(localObject.substring(i1 + 1), "UTF-8"));
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        com.google.a.a.a.a.a.a.a(localUnsupportedEncodingException);
      }
      m += 1;
    }
    return localLinkedHashMap;
  }
  
  public static boolean m(String paramString)
  {
    return (paramString == null) || (paramString.equals(""));
  }
  
  public static String n(String paramString)
  {
    Object localObject = c.h();
    localObject = j((Context)localObject, ((c)localObject).getPackageName()).replace(" ", "").toLowerCase();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://joaoapps.com/");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append("/");
    localStringBuilder.append(paramString);
    localStringBuilder.append("/");
    return localStringBuilder.toString();
  }
  
  public static enum PowerType
  {
    charging,  chargingAc,  chargingUsb,  chargingWireless,  notCharging;
    
    private PowerType() {}
    
    public static PowerType fromBatteryManager(int paramInt)
    {
      if (paramInt != 4)
      {
        switch (paramInt)
        {
        default: 
          return notCharging;
        case 2: 
          return chargingUsb;
        }
        return chargingAc;
      }
      return chargingWireless;
    }
    
    public boolean isCharging()
    {
      return (this == chargingWireless) || (this == chargingAc) || (this == chargingUsb);
    }
  }
  
  public static class a
    extends RuntimeException
  {
    private int a;
    
    public a(int paramInt, String paramString)
    {
      super();
      this.a = paramInt;
    }
  }
  
  public static class b<TThis extends b>
  {
    private String a;
    private boolean b = true;
    private int c;
    private boolean d;
    
    public b() {}
    
    public TThis a(int paramInt)
    {
      this.c = paramInt;
      return this;
    }
    
    public TThis a(String paramString)
    {
      this.a = paramString;
      return this;
    }
    
    public TThis a(boolean paramBoolean)
    {
      this.b = paramBoolean;
      return this;
    }
    
    public String a()
    {
      return this.a;
    }
    
    public void a(HttpURLConnection paramHttpURLConnection)
    {
      if (b()) {
        Util.a(this.a, paramHttpURLConnection);
      }
      if (d()) {
        paramHttpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.45 Safari/535.19");
      }
      paramHttpURLConnection.setConnectTimeout(c());
    }
    
    public boolean b()
    {
      return this.b;
    }
    
    public int c()
    {
      if (this.c == 0) {
        this.c = 60000;
      }
      return this.c;
    }
    
    public boolean d()
    {
      return this.d;
    }
  }
  
  public static class c
    extends Util.b<c>
  {
    private boolean a;
    private boolean b;
    
    public c() {}
    
    public boolean e()
    {
      return this.a;
    }
    
    public boolean f()
    {
      return this.b;
    }
  }
  
  private static class d
  {
    private String a;
    private int b;
    
    public d(String paramString, int paramInt)
    {
      this.a = paramString;
      this.b = paramInt;
    }
    
    public String a()
    {
      return this.a;
    }
    
    public boolean equals(Object paramObject)
    {
      return ((d)paramObject).a().equals(a());
    }
  }
  
  public static class e
  {
    private int a;
    private String b;
    
    public e() {}
    
    public String a()
    {
      return this.b;
    }
    
    public int b()
    {
      return this.a;
    }
  }
}
