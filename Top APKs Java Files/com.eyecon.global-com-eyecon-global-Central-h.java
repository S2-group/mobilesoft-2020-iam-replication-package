package com.eyecon.global.Central;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import com.eyecon.global.Activities.NewContactActivity;
import com.eyecon.global.Objects.ah;
import com.eyecon.global.Objects.an;
import com.eyecon.global.Objects.ao;
import com.eyecon.global.Objects.ax;
import com.eyecon.global.Objects.bd;
import com.eyecon.global.Objects.q;
import com.eyecon.global.Objects.v;
import com.eyecon.global.Objects.z;
import com.eyecon.global.a.d;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import org.apache.http.NameValuePair;

public final class h
  extends i
{
  public static float a = 0.0F;
  static h b = new h();
  private static String c;
  private static String d;
  private static String e;
  private static String f;
  private static String g;
  private static int h = -1;
  private static int i = -1;
  private static Set<String> j;
  private static String k;
  private static String[] l;
  
  public h() {}
  
  private static String N()
  {
    if (g == null) {
      g = MyApplication.b().getString("userCountryISO", null);
    }
    return g;
  }
  
  private static void O()
  {
    if (h != -1) {
      return;
    }
    Context localContext = MyApplication.a();
    if ((i <= 0) || (h <= 0))
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)localContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      h = localDisplayMetrics.heightPixels;
      i = localDisplayMetrics.widthPixels;
      if (h < i)
      {
        int m = h;
        h = i;
        i = m;
      }
    }
  }
  
  public static int a(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = paramString.equals("sms");
      if (bool) {
        return 1;
      }
      try
      {
        if ((j == null) || (paramBoolean))
        {
          Object localObject = MyApplication.a().getPackageManager().getInstalledApplications(128);
          HashSet localHashSet = new HashSet(((List)localObject).size());
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
            if (localApplicationInfo.enabled) {
              localHashSet.add(localApplicationInfo.packageName);
            }
          }
          if (f(MyApplication.a()) == 1) {
            localHashSet.add("email");
          }
          j = localHashSet;
        }
      }
      catch (Exception localException)
      {
        com.google.a.a.a.a.a.a.a(localException);
      }
      if ((j != null) && (!j.isEmpty()))
      {
        paramBoolean = j.contains(paramString);
        if (paramBoolean) {
          return 1;
        }
        return 0;
      }
      return -1;
    }
    finally {}
  }
  
  public static AlertDialog a(Context paramContext, String paramString1, String paramString2)
  {
    return a(paramContext, paramString1, paramString2, null);
  }
  
  public static AlertDialog a(Context paramContext, String paramString1, String paramString2, Runnable paramRunnable)
  {
    if (paramContext != null)
    {
      boolean bool = paramContext instanceof Activity;
      if ((bool) && (((Activity)paramContext).isFinishing())) {
        return null;
      }
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
      localBuilder.setTitle(paramString1);
      localBuilder.setMessage(paramString2);
      localBuilder.setCancelable(false);
      localBuilder.setIcon(2131231046);
      localBuilder.setPositiveButton(MyApplication.d().getString(2131624513), new DialogInterface.OnClickListener()
      {
        public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (this.a != null) {
            this.a.run();
          }
          paramAnonymousDialogInterface.cancel();
        }
      });
      paramString1 = localBuilder.create();
      if (bool)
      {
        an.a(paramString1, (Activity)paramContext);
        return paramString1;
      }
      an.a(paramString1, null);
      return paramString1;
    }
    return null;
  }
  
  public static Intent a(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    return a(paramContext, paramString1, paramString2, paramBoolean, null);
  }
  
  public static Intent a(Context paramContext, String paramString1, @NonNull String paramString2, boolean paramBoolean, Runnable paramRunnable)
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setType("vnd.android-dir/mms-sms");
    StringBuilder localStringBuilder = new StringBuilder("sms:");
    localStringBuilder.append(paramString1);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    localIntent.putExtra("sms_body", paramString2);
    if ((!paramBoolean) || (paramRunnable != null)) {}
    try
    {
      if ((paramContext instanceof com.eyecon.global.Activities.c))
      {
        ((com.eyecon.global.Activities.c)paramContext).a(localIntent, null, paramRunnable);
        return localIntent;
      }
      paramContext.startActivity(localIntent);
      return localIntent;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    i.a(paramContext, "");
    com.eyecon.global.Activities.c.i();
    return localIntent;
  }
  
  public static Bitmap a(int paramInt1, int paramInt2)
  {
    try
    {
      localBitmap1 = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      return localBitmap1;
    }
    catch (OutOfMemoryError localOutOfMemoryError2)
    {
      Bitmap localBitmap1;
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException2)
    {
      for (;;) {}
    }
    MyApplication.h();
    try
    {
      localBitmap1 = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      return localBitmap1;
    }
    catch (OutOfMemoryError localOutOfMemoryError1)
    {
      com.google.a.a.a.a.a.a.a(localOutOfMemoryError1);
    }
    catch (IllegalArgumentException localIllegalArgumentException1)
    {
      com.google.a.a.a.a.a.a.a(localIllegalArgumentException1);
    }
    MyApplication.h();
    try
    {
      Bitmap localBitmap2 = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      return localBitmap2;
    }
    catch (OutOfMemoryError localOutOfMemoryError3)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Bitmap a(Bitmap paramBitmap, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject = null;
    if (paramBitmap != null) {}
    try
    {
      if (paramBitmap.getConfig() != null) {
        localObject = paramBitmap.getConfig();
      } else {
        localObject = Bitmap.Config.ARGB_8888;
      }
      localObject = paramBitmap.copy((Bitmap.Config)localObject, paramBoolean1);
      paramBitmap = (Bitmap)localObject;
    }
    catch (OutOfMemoryError localOutOfMemoryError1)
    {
      for (;;)
      {
        try
        {
          localObject = paramBitmap.copy(Bitmap.Config.ARGB_4444, paramBoolean1);
          return localObject;
        }
        catch (OutOfMemoryError localOutOfMemoryError3)
        {
          continue;
        }
        try
        {
          localObject = paramBitmap.copy(Bitmap.Config.RGB_565, paramBoolean1);
          return localObject;
        }
        catch (OutOfMemoryError paramBitmap) {}
        localOutOfMemoryError1 = localOutOfMemoryError1;
      }
    }
    MyApplication.h();
    try
    {
      localObject = paramBitmap.copy(paramBitmap.getConfig(), paramBoolean1);
      paramBitmap = (Bitmap)localObject;
      return paramBitmap;
    }
    catch (OutOfMemoryError localOutOfMemoryError2)
    {
      for (;;) {}
    }
    if (!paramBoolean2) {
      return null;
    }
    return null;
  }
  
  public static Bitmap a(File paramFile)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inScaled = false;
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeFile(paramFile.getAbsolutePath(), localOptions);
      return localBitmap;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
    MyApplication.h();
    try
    {
      paramFile = BitmapFactory.decodeFile(paramFile.getAbsolutePath(), localOptions);
      return paramFile;
    }
    catch (OutOfMemoryError paramFile)
    {
      com.google.a.a.a.a.a.a.a(paramFile);
    }
    catch (IllegalArgumentException paramFile)
    {
      com.google.a.a.a.a.a.a.a(paramFile);
    }
    MyApplication.h();
    try
    {
      paramFile = BitmapFactory.decodeFile(paramFile.getAbsolutePath(), localOptions);
      return paramFile;
    }
    catch (OutOfMemoryError paramFile)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Animation a(View paramView, Animation paramAnimation, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    Object localObject = new StringBuilder("resizeAnim fromWidth fromHeight toWidth toHeight: ");
    ((StringBuilder)localObject).append(paramInt1);
    ((StringBuilder)localObject).append(", ");
    ((StringBuilder)localObject).append(paramInt2);
    ((StringBuilder)localObject).append(", ");
    ((StringBuilder)localObject).append(paramInt3);
    ((StringBuilder)localObject).append(", ");
    ((StringBuilder)localObject).append(paramInt4);
    localObject = paramAnimation;
    if (paramAnimation == null) {
      localObject = new d(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
    }
    if ((!((Animation)localObject).hasEnded()) && (((Animation)localObject).hasStarted())) {
      paramInt1 = 0;
    } else {
      paramInt1 = 1;
    }
    if (paramInt1 != 0)
    {
      ((Animation)localObject).setDuration(paramInt5);
      paramView.startAnimation((Animation)localObject);
    }
    return localObject;
  }
  
  public static String a(int paramInt)
  {
    Object localObject;
    switch (6.a[(paramInt - 1)])
    {
    default: 
      localObject = null;
      break;
    case 5: 
      localObject = new byte[3];
      Object tmp50_49 = localObject;
      tmp50_49[0] = -30;
      Object tmp56_50 = tmp50_49;
      tmp56_50[1] = -104;
      Object tmp62_56 = tmp56_50;
      tmp62_56[2] = -122;
      tmp62_56;
      break;
    case 4: 
      localObject = new byte[4];
      Object tmp77_76 = localObject;
      tmp77_76[0] = -16;
      Object tmp83_77 = tmp77_76;
      tmp83_77[1] = -97;
      Object tmp89_83 = tmp83_77;
      tmp89_83[2] = -111;
      Object tmp95_89 = tmp89_83;
      tmp95_89[3] = -115;
      tmp95_89;
      break;
    case 3: 
      localObject = new byte[3];
      Object tmp110_109 = localObject;
      tmp110_109[0] = -30;
      Object tmp116_110 = tmp110_109;
      tmp116_110[1] = -99;
      Object tmp122_116 = tmp116_110;
      tmp122_116[2] = -92;
      tmp122_116;
      break;
    case 2: 
      localObject = new byte[3];
      Object tmp137_136 = localObject;
      tmp137_136[0] = -30;
      Object tmp143_137 = tmp137_136;
      tmp143_137[1] = -83;
      Object tmp149_143 = tmp143_137;
      tmp149_143[2] = -112;
      tmp149_143;
      break;
    case 1: 
      localObject = new byte[4];
      Object tmp164_163 = localObject;
      tmp164_163[0] = -16;
      Object tmp170_164 = tmp164_163;
      tmp170_164[1] = -97;
      Object tmp176_170 = tmp170_164;
      tmp176_170[2] = -109;
      Object tmp182_176 = tmp176_170;
      tmp182_176[3] = -78;
      tmp182_176;
    }
    try
    {
      localObject = new String((byte[])localObject, "UTF-8");
      return localObject;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      com.google.a.a.a.a.a.a.a(localUnsupportedEncodingException);
    }
    return "";
  }
  
  public static String a(long paramLong)
  {
    StringBuilder localStringBuilder;
    if (DateUtils.isToday(paramLong))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(MyApplication.a().getString(2131624863));
      localStringBuilder.append(", ");
      localStringBuilder.append(f(paramLong));
      return localStringBuilder.toString();
    }
    if (b(paramLong))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(MyApplication.a().getString(2131624910));
      localStringBuilder.append(", ");
      localStringBuilder.append(f(paramLong));
      return localStringBuilder.toString();
    }
    if (d == null)
    {
      d = MyApplication.a().getString(2131624233);
      e = MyApplication.a().getString(2131624860);
    }
    return a(paramLong, d);
  }
  
  public static String a(long paramLong, Context paramContext)
  {
    paramLong = System.currentTimeMillis() - paramLong;
    int m;
    if (paramLong < 60000L)
    {
      m = (int)Math.ceil(paramLong / 1000L);
      if (m == 1) {
        return paramContext.getString(2131624815);
      }
      paramContext = paramContext.getString(2131624817);
    }
    else if (paramLong < 3600000L)
    {
      m = (int)Math.ceil(paramLong / 60000L);
      if (m == 1) {
        return paramContext.getString(2131624469);
      }
      paramContext = paramContext.getString(2131624470);
    }
    else if (paramLong < 86400000L)
    {
      m = (int)Math.ceil(paramLong / 3600000L);
      if (m == 1) {
        return paramContext.getString(2131624419);
      }
      paramContext = paramContext.getString(2131624420);
    }
    else
    {
      if (paramLong < 172800000L) {
        return paramContext.getString(2131624234);
      }
      if (paramLong < 604800000L)
      {
        m = (int)Math.ceil(paramLong / 86400000L);
        paramContext = paramContext.getString(2131624235);
      }
      else
      {
        m = 0;
        paramContext = null;
      }
    }
    if (paramContext == null) {
      return null;
    }
    if (paramContext.contains("quelques")) {
      return paramContext.replaceFirst("quelques", String.valueOf(m));
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(m);
    localStringBuilder.append(" ");
    localStringBuilder.append(paramContext);
    return localStringBuilder.toString();
  }
  
  public static String a(long paramLong, String paramString)
  {
    return new SimpleDateFormat(paramString, Locale.getDefault()).format(new Date(paramLong));
  }
  
  /* Error */
  public static String a(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 513	java/io/BufferedReader
    //   3: dup
    //   4: new 515	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 518	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 521	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_1
    //   16: aload_1
    //   17: astore_0
    //   18: new 270	java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial 427	java/lang/StringBuilder:<init>	()V
    //   25: astore_2
    //   26: aload_1
    //   27: astore_0
    //   28: aload_1
    //   29: invokevirtual 524	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore_3
    //   33: aload_3
    //   34: ifnull +14 -> 48
    //   37: aload_1
    //   38: astore_0
    //   39: aload_2
    //   40: aload_3
    //   41: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: pop
    //   45: goto -19 -> 26
    //   48: aload_1
    //   49: astore_0
    //   50: aload_2
    //   51: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: astore_2
    //   55: aload_1
    //   56: invokevirtual 527	java/io/BufferedReader:close	()V
    //   59: aload_2
    //   60: areturn
    //   61: astore_2
    //   62: goto +12 -> 74
    //   65: astore_1
    //   66: aconst_null
    //   67: astore_0
    //   68: goto +62 -> 130
    //   71: astore_2
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_1
    //   75: astore_0
    //   76: new 270	java/lang/StringBuilder
    //   79: dup
    //   80: ldc_w 529
    //   83: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   86: astore_3
    //   87: aload_1
    //   88: astore_0
    //   89: aload_3
    //   90: aload_2
    //   91: invokevirtual 532	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   94: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload_1
    //   99: astore_0
    //   100: aload_3
    //   101: ldc_w 534
    //   104: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload_1
    //   109: astore_0
    //   110: aload_3
    //   111: aload_2
    //   112: invokevirtual 535	java/lang/Exception:toString	()Ljava/lang/String;
    //   115: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload_1
    //   120: ifnull +7 -> 127
    //   123: aload_1
    //   124: invokevirtual 527	java/io/BufferedReader:close	()V
    //   127: aconst_null
    //   128: areturn
    //   129: astore_1
    //   130: aload_0
    //   131: ifnull +7 -> 138
    //   134: aload_0
    //   135: invokevirtual 527	java/io/BufferedReader:close	()V
    //   138: aload_1
    //   139: athrow
    //   140: astore_0
    //   141: aload_2
    //   142: areturn
    //   143: astore_0
    //   144: goto -17 -> 127
    //   147: astore_0
    //   148: goto -10 -> 138
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	paramInputStream	InputStream
    //   15	41	1	localBufferedReader	java.io.BufferedReader
    //   65	1	1	localObject1	Object
    //   73	51	1	localObject2	Object
    //   129	10	1	localObject3	Object
    //   25	35	2	localObject4	Object
    //   61	1	2	localException1	Exception
    //   71	71	2	localException2	Exception
    //   32	79	3	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   18	26	61	java/lang/Exception
    //   28	33	61	java/lang/Exception
    //   39	45	61	java/lang/Exception
    //   50	55	61	java/lang/Exception
    //   0	16	65	finally
    //   0	16	71	java/lang/Exception
    //   18	26	129	finally
    //   28	33	129	finally
    //   39	45	129	finally
    //   50	55	129	finally
    //   76	87	129	finally
    //   89	98	129	finally
    //   100	108	129	finally
    //   110	119	129	finally
    //   55	59	140	java/io/IOException
    //   123	127	143	java/io/IOException
    //   134	138	147	java/io/IOException
  }
  
  public static String a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString3);
    localStringBuilder.append(paramString4);
    localStringBuilder.append(paramString5);
    localStringBuilder.append("Content-Disposition: form-data; name=\"");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("\"");
    localStringBuilder.append(paramString5);
    localStringBuilder.append(paramString5);
    localStringBuilder.append(paramString2);
    localStringBuilder.append(paramString5);
    return localStringBuilder.toString();
  }
  
  public static String a(String paramString, char[][] paramArrayOfChar)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramString = paramString.toCharArray();
    int n = paramString.length;
    int m = 0;
    while (m < n)
    {
      char c1 = paramString[m];
      if (an.a(paramArrayOfChar[0], c1)) {
        localStringBuilder.append("2");
      } else if (an.a(paramArrayOfChar[1], c1)) {
        localStringBuilder.append("3");
      } else if (an.a(paramArrayOfChar[2], c1)) {
        localStringBuilder.append("4");
      } else if (an.a(paramArrayOfChar[3], c1)) {
        localStringBuilder.append("5");
      } else if (an.a(paramArrayOfChar[4], c1)) {
        localStringBuilder.append("6");
      } else if (an.a(paramArrayOfChar[5], c1)) {
        localStringBuilder.append("7");
      } else if (an.a(paramArrayOfChar[6], c1)) {
        localStringBuilder.append("8");
      } else if (an.a(paramArrayOfChar[7], c1)) {
        localStringBuilder.append("9");
      } else if (c1 == ' ') {
        localStringBuilder.append("0");
      }
      m += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static String a(ArrayList<ao> paramArrayList)
  {
    HashMap localHashMap = new HashMap();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      ao localAo = (ao)paramArrayList.next();
      String str = localAo.b(c.m);
      if (!an.b(str)) {
        localHashMap.put(str, localAo.b(c.n));
      }
    }
    return a(localHashMap);
  }
  
  public static String a(List<NameValuePair> paramList, String paramString1, String paramString2)
  {
    return o.a(paramList, paramString1, "GET", null, paramString2);
  }
  
  private static String a(Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localStringBuilder.append(localEntry.getKey());
      localStringBuilder.append("@a@");
      localStringBuilder.append(localEntry.getValue());
      localStringBuilder.append("@z@");
      paramMap.remove();
    }
    new StringBuilder("contactMap2List result =").append(localStringBuilder);
    return localStringBuilder.toString();
  }
  
  private static String a(boolean paramBoolean)
  {
    for (;;)
    {
      Object localObject1;
      int m;
      Object localObject3;
      try
      {
        if ((k != null) && (!paramBoolean))
        {
          localObject1 = k;
          return localObject1;
        }
        localObject1 = null;
      }
      finally {}
      try
      {
        m = Settings.System.getInt(MyApplication.a().getContentResolver(), "time_12_24");
        if (m == 24)
        {
          localObject3 = Boolean.valueOf(true);
          localObject1 = localObject3;
        }
        else
        {
          localObject3 = localObject1;
          if (m == 12)
          {
            localObject3 = Boolean.valueOf(false);
            localObject1 = localObject3;
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject4 = localObject2;
        continue;
        localObject4 = localObject2;
        continue;
        String str = "hh:mm aaa";
      }
    }
    localObject1 = localObject3;
    if (localObject3 == null)
    {
      localObject1 = DateFormat.getTimeInstance(1, Locale.getDefault());
      if ((localObject1 instanceof SimpleDateFormat))
      {
        if (((SimpleDateFormat)localObject1).toPattern().indexOf('H') >= 0) {
          localObject1 = Boolean.valueOf(true);
        } else {
          localObject1 = Boolean.valueOf(false);
        }
      }
      else {
        localObject1 = Boolean.valueOf(false);
      }
    }
    if (((Boolean)localObject1).booleanValue())
    {
      localObject1 = "HH:mm";
      k = (String)localObject1;
      return localObject1;
    }
  }
  
  public static void a()
  {
    c = null;
  }
  
  public static void a(final int paramInt1, final int paramInt2, Bitmap paramBitmap1, final Bitmap paramBitmap2)
  {
    paramBitmap1 = new Runnable()
    {
      public final void run()
      {
        Rect localRect = new Rect();
        int i = this.a.getWidth();
        int j = this.a.getHeight();
        int k = paramInt1;
        int m = paramInt2;
        Object localObject = h.a(new int[] { i, j }, new int[] { k, m });
        localRect.set(localObject[2], 0, localObject[0], localObject[1]);
        localObject = new Canvas(paramBitmap2);
        Paint localPaint = new Paint(2);
        ((Canvas)localObject).drawBitmap(this.a, null, localRect, localPaint);
      }
    };
    try
    {
      paramBitmap1.run();
      return;
    }
    catch (Exception paramBitmap1)
    {
      i.a(paramBitmap1, "");
      return;
    }
    catch (OutOfMemoryError paramBitmap2)
    {
      for (;;) {}
    }
    MyApplication.h();
    try
    {
      paramBitmap1.run();
      return;
    }
    catch (Exception paramBitmap1)
    {
      i.a(paramBitmap1, "");
      return;
    }
    catch (OutOfMemoryError paramBitmap1)
    {
      i.a(paramBitmap1, "");
      return;
    }
  }
  
  public static void a(Activity paramActivity, q paramQ, String paramString)
  {
    if (!paramQ.a()) {
      return;
    }
    Intent localIntent = new Intent(paramActivity, NewContactActivity.class);
    localIntent.putExtra("phoneNumber", paramQ.b);
    localIntent.putExtra(c.k.a, paramQ.n);
    localIntent.putExtra("state", 1);
    localIntent.putExtra("EXTRA_SOURCE", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    new StringBuilder("start sms clicked. CLI =").append(an.e(paramString));
    if (paramActivity != null)
    {
      if (an.b(paramString)) {
        return;
      }
      Intent localIntent = new Intent("android.intent.action.SENDTO");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.setType("vnd.android-dir/mms-sms");
      StringBuilder localStringBuilder = new StringBuilder("sms:");
      localStringBuilder.append(paramString);
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
      paramActivity.startActivityForResult(localIntent, 76);
      return;
    }
  }
  
  public static void a(Activity paramActivity, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(paramActivity, NewContactActivity.class);
    localIntent.putExtra("phoneNumber", paramString1);
    localIntent.putExtra(c.k.a, "");
    localIntent.putExtra("state", 1);
    localIntent.putExtra("EXTRA_SOURCE", paramString2);
    paramActivity.startActivityForResult(localIntent, 7);
  }
  
  public static void a(Context paramContext, String paramString)
  {
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Number copied by Eyecon", paramString));
  }
  
  /* Error */
  public static void a(Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: invokestatic 750	com/eyecon/global/Objects/x:a	()V
    //   3: aload_0
    //   4: astore_3
    //   5: aload_0
    //   6: ifnonnull +7 -> 13
    //   9: invokestatic 79	com/eyecon/global/Central/MyApplication:a	()Landroid/content/Context;
    //   12: astore_3
    //   13: new 252	android/content/Intent
    //   16: dup
    //   17: ldc_w 752
    //   20: invokespecial 257	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   23: astore 4
    //   25: aload_1
    //   26: invokestatic 754	com/eyecon/global/Central/h:c	(Ljava/lang/String;)Ljava/lang/String;
    //   29: astore_0
    //   30: new 270	java/lang/StringBuilder
    //   33: dup
    //   34: ldc_w 756
    //   37: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: astore_1
    //   41: aload_1
    //   42: aload_0
    //   43: invokestatic 759	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   46: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload 4
    //   52: aload_1
    //   53: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 286	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   59: invokevirtual 290	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   62: pop
    //   63: iload_2
    //   64: iflt +13 -> 77
    //   67: invokestatic 764	com/eyecon/global/Objects/bd:a	()Lcom/eyecon/global/Objects/bd;
    //   70: pop
    //   71: aload 4
    //   73: iload_2
    //   74: invokestatic 766	com/eyecon/global/Objects/bd:a	(Landroid/content/Intent;I)V
    //   77: ldc_w 768
    //   80: invokestatic 771	com/eyecon/global/Central/h:p	(Ljava/lang/String;)Z
    //   83: ifeq +18 -> 101
    //   86: aload 4
    //   88: ldc_w 773
    //   91: ldc_w 768
    //   94: invokevirtual 776	android/content/Intent:setClassName	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   97: pop
    //   98: goto +23 -> 121
    //   101: iconst_1
    //   102: ldc_w 778
    //   105: iconst_0
    //   106: invokestatic 780	com/eyecon/global/Central/h:a	(Ljava/lang/String;Z)I
    //   109: if_icmpne +12 -> 121
    //   112: aload 4
    //   114: ldc_w 778
    //   117: invokevirtual 783	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   120: pop
    //   121: aload_3
    //   122: instanceof 194
    //   125: ifeq +15 -> 140
    //   128: aload_3
    //   129: checkcast 194	android/app/Activity
    //   132: aload 4
    //   134: bipush 79
    //   136: invokevirtual 727	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   139: return
    //   140: aload 4
    //   142: ldc_w 784
    //   145: invokevirtual 788	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   148: pop
    //   149: invokestatic 79	com/eyecon/global/Central/MyApplication:a	()Landroid/content/Context;
    //   152: aload 4
    //   154: invokevirtual 305	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   157: return
    //   158: aload_3
    //   159: invokevirtual 127	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   162: astore_1
    //   163: new 790	android/content/ComponentName
    //   166: dup
    //   167: aload_3
    //   168: ldc_w 792
    //   171: invokespecial 793	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   174: astore 4
    //   176: aload_1
    //   177: aload 4
    //   179: iconst_2
    //   180: iconst_1
    //   181: invokevirtual 797	android/content/pm/PackageManager:setComponentEnabledSetting	(Landroid/content/ComponentName;II)V
    //   184: new 252	android/content/Intent
    //   187: dup
    //   188: ldc_w 752
    //   191: invokespecial 257	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   194: astore 5
    //   196: new 270	java/lang/StringBuilder
    //   199: dup
    //   200: ldc_w 756
    //   203: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   206: astore 6
    //   208: aload 6
    //   210: aload_0
    //   211: invokestatic 759	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   214: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: pop
    //   218: aload 5
    //   220: aload 6
    //   222: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   225: invokestatic 286	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   228: invokevirtual 290	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   231: pop
    //   232: iload_2
    //   233: iflt +13 -> 246
    //   236: invokestatic 764	com/eyecon/global/Objects/bd:a	()Lcom/eyecon/global/Objects/bd;
    //   239: pop
    //   240: aload 5
    //   242: iload_2
    //   243: invokestatic 766	com/eyecon/global/Objects/bd:a	(Landroid/content/Intent;I)V
    //   246: aload_3
    //   247: instanceof 194
    //   250: ifeq +31 -> 281
    //   253: aload_3
    //   254: checkcast 194	android/app/Activity
    //   257: aload 5
    //   259: bipush 79
    //   261: invokevirtual 727	android/app/Activity:startActivityForResult	(Landroid/content/Intent;I)V
    //   264: new 6	com/eyecon/global/Central/h$1
    //   267: dup
    //   268: aload_1
    //   269: aload 4
    //   271: invokespecial 800	com/eyecon/global/Central/h$1:<init>	(Landroid/content/pm/PackageManager;Landroid/content/ComponentName;)V
    //   274: ldc2_w 801
    //   277: invokestatic 807	com/eyecon/global/f:a	(Ljava/lang/Runnable;J)V
    //   280: return
    //   281: aload 5
    //   283: ldc_w 784
    //   286: invokevirtual 788	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   289: pop
    //   290: invokestatic 79	com/eyecon/global/Central/MyApplication:a	()Landroid/content/Context;
    //   293: aload 5
    //   295: invokevirtual 305	android/content/Context:startActivity	(Landroid/content/Intent;)V
    //   298: new 6	com/eyecon/global/Central/h$1
    //   301: dup
    //   302: aload_1
    //   303: aload 4
    //   305: invokespecial 800	com/eyecon/global/Central/h$1:<init>	(Landroid/content/pm/PackageManager;Landroid/content/ComponentName;)V
    //   308: astore_0
    //   309: aload_0
    //   310: ldc2_w 801
    //   313: invokestatic 807	com/eyecon/global/f:a	(Ljava/lang/Runnable;J)V
    //   316: return
    //   317: astore_0
    //   318: goto +32 -> 350
    //   321: astore_0
    //   322: aload_0
    //   323: ldc_w 307
    //   326: invokestatic 310	com/eyecon/global/Central/i:a	(Ljava/lang/Throwable;Ljava/lang/String;)V
    //   329: ldc_w 808
    //   332: iconst_0
    //   333: invokestatic 811	com/eyecon/global/Central/i:b	(II)V
    //   336: new 6	com/eyecon/global/Central/h$1
    //   339: dup
    //   340: aload_1
    //   341: aload 4
    //   343: invokespecial 800	com/eyecon/global/Central/h$1:<init>	(Landroid/content/pm/PackageManager;Landroid/content/ComponentName;)V
    //   346: astore_0
    //   347: goto -38 -> 309
    //   350: new 6	com/eyecon/global/Central/h$1
    //   353: dup
    //   354: aload_1
    //   355: aload 4
    //   357: invokespecial 800	com/eyecon/global/Central/h$1:<init>	(Landroid/content/pm/PackageManager;Landroid/content/ComponentName;)V
    //   360: ldc2_w 801
    //   363: invokestatic 807	com/eyecon/global/f:a	(Ljava/lang/Runnable;J)V
    //   366: aload_0
    //   367: athrow
    //   368: astore_1
    //   369: goto -211 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	372	0	paramContext	Context
    //   0	372	1	paramString	String
    //   0	372	2	paramInt	int
    //   4	250	3	localContext	Context
    //   23	333	4	localObject	Object
    //   194	100	5	localIntent	Intent
    //   206	15	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   176	232	317	finally
    //   236	246	317	finally
    //   246	264	317	finally
    //   281	298	317	finally
    //   322	336	317	finally
    //   176	232	321	java/lang/Exception
    //   236	246	321	java/lang/Exception
    //   246	264	321	java/lang/Exception
    //   281	298	321	java/lang/Exception
    //   121	139	368	java/lang/Exception
    //   140	157	368	java/lang/Exception
  }
  
  public static <T> void a(AsyncTask<T, ?, ?> paramAsyncTask, T... paramVarArgs)
  {
    if (Build.VERSION.SDK_INT >= 11) {
      try
      {
        paramAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
        return;
      }
      catch (RejectedExecutionException paramAsyncTask)
      {
        new StringBuilder("RejectedExecutionException:").append(paramAsyncTask.getMessage());
        try
        {
          Thread.sleep(500L);
          new com.eyecon.global.a().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramVarArgs);
          return;
        }
        catch (InterruptedException paramAsyncTask)
        {
          com.google.a.a.a.a.a.a.a(paramAsyncTask);
          return;
        }
      }
    }
    try
    {
      paramAsyncTask.execute(paramVarArgs);
      return;
    }
    catch (RejectedExecutionException paramAsyncTask)
    {
      new StringBuilder("RejectedExecutionException:").append(paramAsyncTask.getMessage());
      try
      {
        Thread.sleep(1000L);
        new com.eyecon.global.a().execute(paramVarArgs);
        return;
      }
      catch (InterruptedException paramAsyncTask)
      {
        com.google.a.a.a.a.a.a.a(paramAsyncTask);
      }
    }
  }
  
  public static void a(View paramView, final Bitmap paramBitmap)
  {
    paramView.post(new Runnable()
    {
      private void b(final ImageView paramAnonymousImageView)
      {
        new Thread(new Runnable()
        {
          public final void run()
          {
            final Bitmap localBitmap2 = h.a(this.a, this.b);
            Bitmap localBitmap1;
            if ((h.5.this.b != null) && (!h.5.this.b.isEmpty()))
            {
              localBitmap1 = ax.a(h.5.this.b);
            }
            else if (h.5.this.c != null)
            {
              localBitmap1 = h.5.this.c;
            }
            else
            {
              if (h.5.this.d == -1) {
                return;
              }
              localBitmap1 = ax.a(h.5.this.d);
            }
            if ((localBitmap1 != null) && (localBitmap2 != null))
            {
              h.a(this.a, this.b, localBitmap1, localBitmap2);
              paramAnonymousImageView.post(new Runnable()
              {
                public final void run()
                {
                  h.5.2.this.c.setImageBitmap(localBitmap2);
                  if ((h.5.this.a instanceof ImageSwitcher)) {
                    ((ImageSwitcher)h.5.this.a).showNext();
                  }
                }
              });
              return;
            }
            paramAnonymousImageView.post(new Runnable()
            {
              public final void run()
              {
                h.5.this.a(h.5.2.this.c);
              }
            });
            return;
          }
        }).start();
      }
      
      final void a(ImageView paramAnonymousImageView)
      {
        if (paramBitmap != null) {
          paramAnonymousImageView.setImageBitmap(paramBitmap);
        } else if (this.d != -1) {
          paramAnonymousImageView.setImageResource(this.d);
        } else {
          paramAnonymousImageView.setImageBitmap(null);
        }
        if ((this.a instanceof ImageSwitcher)) {
          ((ImageSwitcher)this.a).showNext();
        }
      }
      
      public final void run()
      {
        if ((this.a instanceof ImageView)) {
          localObject = (ImageView)this.a;
        } else {
          localObject = (ImageView)((ImageSwitcher)this.a).getNextView();
        }
        StringBuilder localStringBuilder = new StringBuilder("view.getWidth() > 0 && view.getHeight()");
        localStringBuilder.append(this.a.getWidth());
        localStringBuilder.append(" , ");
        localStringBuilder.append(this.a.getHeight());
        if ((this.a.getWidth() > 0) && (this.a.getHeight() > 0))
        {
          b((ImageView)localObject);
          return;
        }
        a((ImageView)localObject);
        Object localObject = new ViewTreeObserver.OnPreDrawListener()
        {
          public final boolean onPreDraw()
          {
            h.5.this.a.getViewTreeObserver().removeOnPreDrawListener(this);
            h.5.a(h.5.this, this.a);
            return true;
          }
        };
        this.a.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)localObject);
      }
    });
  }
  
  public static void a(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    byte[] arrayOfByte = new byte['Ѐ'];
    for (;;)
    {
      int m = paramInputStream.read(arrayOfByte);
      if (m == -1) {
        break;
      }
      paramOutputStream.write(arrayOfByte, 0, m);
    }
  }
  
  public static void a(String paramString)
  {
    g = paramString;
  }
  
  public static void a(String paramString1, String paramString2, Bitmap paramBitmap, Context paramContext)
  {
    if (E()) {
      localFile = MyApplication.a().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    } else {
      localFile = MyApplication.a().getFilesDir();
    }
    File localFile = new File(localFile, "generated.vcf");
    FileWriter localFileWriter = new FileWriter(localFile);
    localFileWriter.write("BEGIN:VCARD\r\n");
    localFileWriter.write("VERSION:3.0\r\n");
    if (paramBitmap != null)
    {
      Object localObject = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject);
      paramBitmap = Base64.encodeToString(((ByteArrayOutputStream)localObject).toByteArray(), 0).replaceAll("\n", "");
      localObject = new StringBuilder("PHOTO;ENCODING=B;TYPE=JPEG: ");
      ((StringBuilder)localObject).append(paramBitmap);
      ((StringBuilder)localObject).append("\r\n");
      localFileWriter.write(((StringBuilder)localObject).toString());
    }
    paramBitmap = new StringBuilder("FN:");
    paramBitmap.append(paramString1);
    paramBitmap.append("\r\n");
    localFileWriter.write(paramBitmap.toString());
    paramString1 = new StringBuilder("TEL:");
    paramString1.append(paramString2);
    paramString1.append("\r\n");
    localFileWriter.write(paramString1.toString());
    localFileWriter.write("END:VCARD\r\n");
    localFileWriter.close();
    paramString1 = FileProvider.getUriForFile(MyApplication.a(), "com.eyecon.global.fileprovider", localFile);
    paramString2 = new Intent("android.intent.action.SEND");
    paramString2.putExtra("android.intent.extra.STREAM", paramString1);
    paramString2.setType("text/x-vcard");
    paramContext.startActivity(paramString2);
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (Build.VERSION.SDK_INT >= 20) {
      return paramContext.isInteractive();
    }
    return paramContext.isScreenOn();
  }
  
  public static boolean a(Context paramContext, EditText paramEditText)
  {
    if (paramEditText == null) {
      return false;
    }
    return ((InputMethodManager)paramContext.getSystemService("input_method")).hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
  }
  
  public static boolean a(View paramView, ValueAnimator paramValueAnimator, Animator.AnimatorListener paramAnimatorListener, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    ValueAnimator localValueAnimator = paramValueAnimator;
    if (paramValueAnimator == null)
    {
      paramValueAnimator = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt("height", new int[] { paramInt2, paramInt4 }), PropertyValuesHolder.ofInt("width", new int[] { paramInt1, paramInt3 }) });
      paramValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        int a;
        int b;
        
        public final void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          this.a = ((Integer)paramAnonymousValueAnimator.getAnimatedValue("height")).intValue();
          this.b = ((Integer)paramAnonymousValueAnimator.getAnimatedValue("width")).intValue();
          paramAnonymousValueAnimator = (ViewGroup.MarginLayoutParams)this.c.getLayoutParams();
          if (this.a != -1) {
            paramAnonymousValueAnimator.height = this.a;
          }
          if (this.b != -1) {
            paramAnonymousValueAnimator.width = this.b;
          }
          this.c.requestLayout();
        }
      });
      localValueAnimator = paramValueAnimator;
      if (paramAnimatorListener != null)
      {
        paramValueAnimator.addListener(paramAnimatorListener);
        localValueAnimator = paramValueAnimator;
      }
    }
    if (!localValueAnimator.isRunning())
    {
      localValueAnimator.setDuration(paramInt5);
      localValueAnimator.start();
      return true;
    }
    return false;
  }
  
  public static boolean a(com.eyecon.global.Activities.c paramC, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    ArrayList localArrayList = bd.a().b();
    com.eyecon.global.Activities.c localC = paramC;
    if (paramC == null) {
      localC = com.eyecon.global.Activities.c.e();
    }
    if ((localArrayList.size() >= 2) && (localC != null))
    {
      int m = bd.a().b(paramString3);
      if (m == Integer.MAX_VALUE)
      {
        bd.a();
        bd.a(localC, paramString1, paramString2, localArrayList, paramBoolean);
        return false;
      }
      a(localC, paramString1, m);
      v.b("Used already chosen SIM");
      return true;
    }
    a(localC, paramString1, -1);
    v.b("Only has 1 SIM");
    return true;
  }
  
  public static int[] a(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    float f4 = paramArrayOfInt1[0];
    float f3 = paramArrayOfInt1[1];
    float f6 = paramArrayOfInt2[0];
    float f7 = paramArrayOfInt2[1];
    float f8 = Math.abs(f4 - f6) / f4;
    float f5 = 0.0F;
    if (f4 > f6)
    {
      f1 = f4;
      f2 = f3;
      if (f8 != 0.0F) {
        if (f8 < 1.0F)
        {
          f2 = 1.0F - f8;
          f1 = f4 * f2;
          f2 = f3 * f2;
        }
        else
        {
          f1 = f4 / f8;
          f2 = f3 / f8;
        }
      }
    }
    else
    {
      f1 = f4;
      f2 = f3;
      if (f4 < f6)
      {
        f8 += 1.0F;
        f1 = f4;
        f2 = f3;
        if (f8 != 0.0F)
        {
          f1 = f4 * f8;
          f2 = f3 * f8;
        }
      }
    }
    f3 = f1;
    f4 = f2;
    if (f2 < f7)
    {
      f7 = Math.abs(f2 - f7) / f2 + 1.0F;
      f3 = f1;
      f4 = f2;
      if (f7 != 0.0F)
      {
        f3 = f1 * f7;
        f4 = f2 * f7;
      }
    }
    float f1 = f3;
    float f2 = f5;
    if (f3 > f6)
    {
      f2 = -Math.abs(f3 - f6) / 2.0F;
      f1 = f3 + f2;
    }
    return new int[] { (int)Math.ceil(f1), (int)f4, (int)f2 };
  }
  
  public static int b(int paramInt)
  {
    if (a == 0.0F) {
      a = MyApplication.b().getFloat("dpPixelRatio", 0.0F);
    }
    if (a > 0.0F) {
      return (int)(paramInt * a);
    }
    DisplayMetrics localDisplayMetrics = MyApplication.d().getDisplayMetrics();
    return Math.round(paramInt * localDisplayMetrics.density);
  }
  
  public static h b()
  {
    return b;
  }
  
  public static String b(Context paramContext)
  {
    String str = MyApplication.b().getString("userCountryName", "");
    if (!str.isEmpty()) {
      return str;
    }
    str = MyApplication.b().getString("userCountryZipCode", "");
    Object localObject = ((TelephonyManager)MyApplication.a().getSystemService("phone")).getSimCountryIso();
    if (localObject != null) {
      return e(((String)localObject).toUpperCase());
    }
    if (!str.isEmpty())
    {
      localObject = MyApplication.d().getStringArray(2130903040);
      int n = localObject.length;
      int m = 0;
      while (m < n)
      {
        String[] arrayOfString = localObject[m].split(",");
        if (arrayOfString[0].trim().equals(str)) {
          return new Locale("", arrayOfString[1].trim()).getDisplayCountry();
        }
        m += 1;
      }
    }
    return paramContext.getResources().getConfiguration().locale.getDisplayCountry();
  }
  
  /* Error */
  public static String b(List<NameValuePair> paramList, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 270	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 427	java/lang/StringBuilder:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore 8
    //   12: aconst_null
    //   13: astore 9
    //   15: aconst_null
    //   16: astore 5
    //   18: aconst_null
    //   19: astore 6
    //   21: aload_0
    //   22: ldc_w 1123
    //   25: invokestatic 1128	org/apache/http/client/utils/URLEncodedUtils:format	(Ljava/util/List;Ljava/lang/String;)Ljava/lang/String;
    //   28: astore 7
    //   30: new 1130	java/net/URL
    //   33: dup
    //   34: aload_1
    //   35: invokespecial 1131	java/net/URL:<init>	(Ljava/lang/String;)V
    //   38: invokevirtual 1135	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   41: checkcast 1137	java/net/HttpURLConnection
    //   44: astore_0
    //   45: aload_0
    //   46: invokestatic 1140	com/eyecon/global/Central/o:a	(Ljava/net/URLConnection;)V
    //   49: aload_0
    //   50: ldc_w 1141
    //   53: invokevirtual 1144	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   56: aload_0
    //   57: ldc_w 1141
    //   60: invokevirtual 1147	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   63: aload_0
    //   64: ldc_w 1149
    //   67: ldc_w 416
    //   70: invokevirtual 1152	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   73: aload_0
    //   74: ldc_w 1154
    //   77: ldc_w 1156
    //   80: invokevirtual 1152	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   83: aload_0
    //   84: ldc_w 1158
    //   87: invokevirtual 1161	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   90: aload_0
    //   91: iconst_1
    //   92: invokevirtual 1165	java/net/HttpURLConnection:setDoInput	(Z)V
    //   95: aload_0
    //   96: iconst_1
    //   97: invokevirtual 1168	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   100: aload_0
    //   101: invokevirtual 1172	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   104: astore 6
    //   106: new 1174	java/io/BufferedWriter
    //   109: dup
    //   110: new 1176	java/io/OutputStreamWriter
    //   113: dup
    //   114: aload 6
    //   116: ldc_w 416
    //   119: invokespecial 1179	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   122: invokespecial 1182	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   125: astore 8
    //   127: aload 8
    //   129: aload 7
    //   131: invokevirtual 1183	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   134: new 270	java/lang/StringBuilder
    //   137: dup
    //   138: ldc_w 1185
    //   141: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   144: aload 8
    //   146: invokevirtual 1188	java/lang/Object:toString	()Ljava/lang/String;
    //   149: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: pop
    //   153: aload 8
    //   155: invokevirtual 1191	java/io/BufferedWriter:flush	()V
    //   158: aload 8
    //   160: invokevirtual 1192	java/io/BufferedWriter:close	()V
    //   163: aload 6
    //   165: invokevirtual 1193	java/io/OutputStream:close	()V
    //   168: aload_0
    //   169: aload_1
    //   170: invokestatic 1196	com/eyecon/global/Central/o:a	(Ljava/net/HttpURLConnection;Ljava/lang/String;)I
    //   173: istore_3
    //   174: new 270	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 427	java/lang/StringBuilder:<init>	()V
    //   181: astore_1
    //   182: aload_1
    //   183: aload_2
    //   184: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: aload_1
    //   189: ldc_w 1198
    //   192: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: pop
    //   196: aload_1
    //   197: iload_3
    //   198: invokevirtual 371	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   201: pop
    //   202: iload_3
    //   203: sipush 401
    //   206: if_icmpeq +160 -> 366
    //   209: aload_0
    //   210: invokevirtual 1201	java/net/HttpURLConnection:connect	()V
    //   213: new 513	java/io/BufferedReader
    //   216: dup
    //   217: new 515	java/io/InputStreamReader
    //   220: dup
    //   221: aload_0
    //   222: invokevirtual 1205	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   225: invokespecial 518	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   228: invokespecial 521	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   231: astore_1
    //   232: aload_1
    //   233: invokevirtual 524	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   236: astore 5
    //   238: aload 5
    //   240: ifnull +14 -> 254
    //   243: aload 4
    //   245: aload 5
    //   247: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: goto -19 -> 232
    //   254: new 270	java/lang/StringBuilder
    //   257: dup
    //   258: invokespecial 427	java/lang/StringBuilder:<init>	()V
    //   261: astore 5
    //   263: aload 5
    //   265: aload_2
    //   266: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: pop
    //   270: aload 5
    //   272: ldc_w 1207
    //   275: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: pop
    //   279: aload 5
    //   281: aload 4
    //   283: invokevirtual 623	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload 4
    //   289: astore_2
    //   290: aload_1
    //   291: astore 5
    //   293: aload_0
    //   294: ifnull +13 -> 307
    //   297: aload_0
    //   298: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   301: aload_1
    //   302: astore 5
    //   304: aload 4
    //   306: astore_2
    //   307: aload 5
    //   309: invokevirtual 527	java/io/BufferedReader:close	()V
    //   312: aload_2
    //   313: astore_0
    //   314: goto +416 -> 730
    //   317: astore 4
    //   319: aload_1
    //   320: astore_2
    //   321: aload 4
    //   323: astore_1
    //   324: goto +437 -> 761
    //   327: astore 6
    //   329: aload 4
    //   331: astore_2
    //   332: aload_1
    //   333: astore 5
    //   335: aload 6
    //   337: astore_1
    //   338: goto +103 -> 441
    //   341: astore 6
    //   343: aload 4
    //   345: astore_2
    //   346: aload_1
    //   347: astore 5
    //   349: aload 6
    //   351: astore_1
    //   352: goto +105 -> 457
    //   355: astore 6
    //   357: aload_1
    //   358: astore 5
    //   360: aload 6
    //   362: astore_1
    //   363: goto +107 -> 470
    //   366: new 270	java/lang/StringBuilder
    //   369: dup
    //   370: ldc_w 1212
    //   373: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   376: astore_1
    //   377: aload_0
    //   378: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   381: aload_1
    //   382: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   385: astore 4
    //   387: aload_0
    //   388: ifnull +7 -> 395
    //   391: aload_0
    //   392: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   395: aload 4
    //   397: areturn
    //   398: astore_2
    //   399: aload_1
    //   400: astore 4
    //   402: aload_2
    //   403: astore_1
    //   404: goto +31 -> 435
    //   407: astore_2
    //   408: aload_1
    //   409: astore 4
    //   411: aload_2
    //   412: astore_1
    //   413: goto +38 -> 451
    //   416: astore 5
    //   418: aload_1
    //   419: astore 4
    //   421: aload 5
    //   423: astore_1
    //   424: goto +43 -> 467
    //   427: astore_1
    //   428: aload 5
    //   430: astore_2
    //   431: goto +330 -> 761
    //   434: astore_1
    //   435: aconst_null
    //   436: astore 5
    //   438: aload 4
    //   440: astore_2
    //   441: aload_0
    //   442: astore 6
    //   444: aload_1
    //   445: astore 7
    //   447: goto +49 -> 496
    //   450: astore_1
    //   451: aconst_null
    //   452: astore 5
    //   454: aload 4
    //   456: astore_2
    //   457: aload_0
    //   458: astore 6
    //   460: aload_1
    //   461: astore 7
    //   463: goto +76 -> 539
    //   466: astore_1
    //   467: aconst_null
    //   468: astore 5
    //   470: aload_0
    //   471: astore 6
    //   473: aload_1
    //   474: astore 7
    //   476: goto +112 -> 588
    //   479: astore_1
    //   480: aconst_null
    //   481: astore_0
    //   482: aload 5
    //   484: astore_2
    //   485: goto +276 -> 761
    //   488: astore 7
    //   490: aconst_null
    //   491: astore 5
    //   493: aload 4
    //   495: astore_2
    //   496: aload 6
    //   498: astore_1
    //   499: aload 5
    //   501: astore_0
    //   502: aload 7
    //   504: invokestatic 180	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   507: aload 6
    //   509: ifnull +8 -> 517
    //   512: aload 6
    //   514: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   517: aload_2
    //   518: astore_0
    //   519: aload 5
    //   521: ifnull +209 -> 730
    //   524: goto -217 -> 307
    //   527: astore 7
    //   529: aconst_null
    //   530: astore 5
    //   532: aload 8
    //   534: astore 6
    //   536: aload 4
    //   538: astore_2
    //   539: aload 6
    //   541: astore_1
    //   542: aload 5
    //   544: astore_0
    //   545: aload 7
    //   547: invokestatic 180	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   550: aload 6
    //   552: astore_1
    //   553: aload 5
    //   555: astore_0
    //   556: invokestatic 332	com/eyecon/global/Central/MyApplication:h	()V
    //   559: aload 6
    //   561: ifnull +8 -> 569
    //   564: aload 6
    //   566: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   569: aload_2
    //   570: astore_0
    //   571: aload 5
    //   573: ifnull +157 -> 730
    //   576: goto -269 -> 307
    //   579: astore 7
    //   581: aconst_null
    //   582: astore 5
    //   584: aload 9
    //   586: astore 6
    //   588: aload 6
    //   590: astore_1
    //   591: aload 5
    //   593: astore_0
    //   594: new 270	java/lang/StringBuilder
    //   597: dup
    //   598: invokespecial 427	java/lang/StringBuilder:<init>	()V
    //   601: astore 8
    //   603: aload 6
    //   605: astore_1
    //   606: aload 5
    //   608: astore_0
    //   609: aload 8
    //   611: aload_2
    //   612: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: pop
    //   616: aload 6
    //   618: astore_1
    //   619: aload 5
    //   621: astore_0
    //   622: aload 8
    //   624: ldc_w 1214
    //   627: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: pop
    //   631: aload 6
    //   633: astore_1
    //   634: aload 5
    //   636: astore_0
    //   637: aload 8
    //   639: aload 7
    //   641: invokevirtual 1215	java/io/IOException:getMessage	()Ljava/lang/String;
    //   644: invokevirtual 277	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   647: pop
    //   648: aload 6
    //   650: astore_1
    //   651: aload 5
    //   653: astore_0
    //   654: aload 7
    //   656: invokevirtual 1215	java/io/IOException:getMessage	()Ljava/lang/String;
    //   659: ifnull +47 -> 706
    //   662: aload 6
    //   664: astore_1
    //   665: aload 5
    //   667: astore_0
    //   668: aload 7
    //   670: invokevirtual 1215	java/io/IOException:getMessage	()Ljava/lang/String;
    //   673: ldc_w 1217
    //   676: invokevirtual 121	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   679: ifeq +27 -> 706
    //   682: aload 6
    //   684: ifnull +8 -> 692
    //   687: aload 6
    //   689: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   692: aload 5
    //   694: ifnull +8 -> 702
    //   697: aload 5
    //   699: invokevirtual 527	java/io/BufferedReader:close	()V
    //   702: ldc_w 1212
    //   705: areturn
    //   706: aload 6
    //   708: ifnull +8 -> 716
    //   711: aload 6
    //   713: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   716: aload 4
    //   718: astore_0
    //   719: aload 5
    //   721: ifnull +9 -> 730
    //   724: aload 4
    //   726: astore_2
    //   727: goto -420 -> 307
    //   730: new 270	java/lang/StringBuilder
    //   733: dup
    //   734: ldc_w 1219
    //   737: invokespecial 273	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   740: aload_0
    //   741: invokevirtual 623	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   744: pop
    //   745: aload_0
    //   746: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   749: areturn
    //   750: astore_2
    //   751: aload_1
    //   752: astore 4
    //   754: aload_2
    //   755: astore_1
    //   756: aload_0
    //   757: astore_2
    //   758: aload 4
    //   760: astore_0
    //   761: aload_0
    //   762: ifnull +7 -> 769
    //   765: aload_0
    //   766: invokevirtual 1210	java/net/HttpURLConnection:disconnect	()V
    //   769: aload_2
    //   770: ifnull +7 -> 777
    //   773: aload_2
    //   774: invokevirtual 527	java/io/BufferedReader:close	()V
    //   777: aload_1
    //   778: athrow
    //   779: astore_0
    //   780: aload_2
    //   781: astore_0
    //   782: goto -52 -> 730
    //   785: astore_0
    //   786: ldc_w 1212
    //   789: areturn
    //   790: astore_0
    //   791: goto -14 -> 777
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	794	0	paramList	List<NameValuePair>
    //   0	794	1	paramString1	String
    //   0	794	2	paramString2	String
    //   173	34	3	m	int
    //   7	298	4	localStringBuilder	StringBuilder
    //   317	27	4	localObject1	Object
    //   385	374	4	str1	String
    //   16	343	5	localObject2	Object
    //   416	13	5	localIOException1	java.io.IOException
    //   436	284	5	localObject3	Object
    //   19	145	6	localOutputStream	OutputStream
    //   327	9	6	localException1	Exception
    //   341	9	6	localOutOfMemoryError1	OutOfMemoryError
    //   355	6	6	localIOException2	java.io.IOException
    //   442	270	6	localObject4	Object
    //   28	447	7	str2	String
    //   488	15	7	localException2	Exception
    //   527	19	7	localOutOfMemoryError2	OutOfMemoryError
    //   579	90	7	localIOException3	java.io.IOException
    //   10	628	8	localObject5	Object
    //   13	572	9	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   232	238	317	finally
    //   243	251	317	finally
    //   254	287	317	finally
    //   232	238	327	java/lang/Exception
    //   243	251	327	java/lang/Exception
    //   254	287	327	java/lang/Exception
    //   232	238	341	java/lang/OutOfMemoryError
    //   243	251	341	java/lang/OutOfMemoryError
    //   254	287	341	java/lang/OutOfMemoryError
    //   232	238	355	java/io/IOException
    //   243	251	355	java/io/IOException
    //   254	287	355	java/io/IOException
    //   377	387	398	java/lang/Exception
    //   377	387	407	java/lang/OutOfMemoryError
    //   377	387	416	java/io/IOException
    //   45	202	427	finally
    //   209	232	427	finally
    //   366	377	427	finally
    //   377	387	427	finally
    //   45	202	434	java/lang/Exception
    //   209	232	434	java/lang/Exception
    //   366	377	434	java/lang/Exception
    //   45	202	450	java/lang/OutOfMemoryError
    //   209	232	450	java/lang/OutOfMemoryError
    //   366	377	450	java/lang/OutOfMemoryError
    //   45	202	466	java/io/IOException
    //   209	232	466	java/io/IOException
    //   366	377	466	java/io/IOException
    //   21	45	479	finally
    //   21	45	488	java/lang/Exception
    //   21	45	527	java/lang/OutOfMemoryError
    //   21	45	579	java/io/IOException
    //   502	507	750	finally
    //   545	550	750	finally
    //   556	559	750	finally
    //   594	603	750	finally
    //   609	616	750	finally
    //   622	631	750	finally
    //   637	648	750	finally
    //   654	662	750	finally
    //   668	682	750	finally
    //   307	312	779	java/io/IOException
    //   697	702	785	java/io/IOException
    //   773	777	790	java/io/IOException
  }
  
  public static void b(String paramString)
  {
    f = paramString;
  }
  
  public static boolean b(long paramLong)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(paramLong);
    localCalendar1.add(5, -1);
    return (localCalendar1.get(1) == localCalendar2.get(1)) && (localCalendar1.get(2) == localCalendar2.get(2)) && (localCalendar1.get(5) == localCalendar2.get(5));
  }
  
  public static boolean b(Activity paramActivity, String paramString)
  {
    paramString = q(paramString);
    if (paramString == null) {
      return false;
    }
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.STREAM", paramString);
    localIntent.setType("text/x-vcard");
    paramActivity.startActivity(localIntent);
    return true;
  }
  
  public static boolean b(Context paramContext, EditText paramEditText)
  {
    return ((InputMethodManager)paramContext.getSystemService("input_method")).showSoftInput(paramEditText, 1);
  }
  
  public static String c()
  {
    if (N() == null) {
      g = f(e());
    }
    return N();
  }
  
  public static String c(long paramLong)
  {
    if (c == null)
    {
      c = ((SimpleDateFormat)DateFormat.getDateInstance(2, Locale.getDefault())).toPattern().replaceAll("\\W?[Yy]+\\W?", "");
      StringBuilder localStringBuilder;
      if ((!Locale.getDefault().getCountry().equals("iw")) && (!Locale.getDefault().getCountry().equals("he")))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(c);
        localStringBuilder.append(" HH:mm");
        c = localStringBuilder.toString();
      }
      else
      {
        localStringBuilder = new StringBuilder("HH:mm ");
        localStringBuilder.append(c);
        c = localStringBuilder.toString();
      }
    }
    return a(paramLong, c);
  }
  
  public static String c(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    String str;
    if (Build.VERSION.SDK_INT >= 21) {
      str = PhoneNumberUtils.formatNumber(paramString, c());
    } else {
      str = PhoneNumberUtils.formatNumber(paramString);
    }
    if (str == null) {
      return paramString;
    }
    return str;
  }
  
  public static String d()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return o(str2);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(o(str1));
    localStringBuilder.append(" ");
    localStringBuilder.append(str2);
    return localStringBuilder.toString();
  }
  
  public static boolean d(String paramString)
  {
    return a(paramString, false) == 1;
  }
  
  public static String e()
  {
    if (!an.b(f)) {
      return f;
    }
    String str1 = MyApplication.b().getString("userCountryZipCode", "");
    if (!str1.isEmpty())
    {
      f = str1;
      return str1;
    }
    String str2 = ((TelephonyManager)MyApplication.a().getSystemService("phone")).getSimCountryIso().toUpperCase();
    String[] arrayOfString = MyApplication.d().getStringArray(2130903040);
    int n = arrayOfString.length;
    int m = 0;
    Object localObject;
    for (;;)
    {
      localObject = str1;
      if (m >= n) {
        break;
      }
      localObject = arrayOfString[m].split(",");
      if (localObject[1].trim().equals(str2.trim()))
      {
        localObject = localObject[0];
        break;
      }
      m += 1;
    }
    f = (String)localObject;
    return localObject;
  }
  
  public static String e(String paramString)
  {
    return new Locale("", paramString).getDisplayCountry();
  }
  
  private static int f(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("message/rfc822");
      int m = paramContext.getPackageManager().queryIntentActivities(localIntent, 0).size();
      if (m == 0) {
        return 0;
      }
      return 1;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public static String f()
  {
    String str2 = Locale.getDefault().getLanguage();
    if ((str2 != null) && (str2.length() != 0))
    {
      if (str2.equals("iw")) {
        return "he";
      }
      if (str2.equals("in")) {
        return "id";
      }
      String str1 = str2;
      if (str2.equals("ji")) {
        str1 = "yi";
      }
      return str1;
    }
    return "en";
  }
  
  private static String f(long paramLong)
  {
    return new SimpleDateFormat(a(false), Locale.getDefault()).format(new Date(paramLong));
  }
  
  public static String f(String paramString)
  {
    if (N() != null) {
      return N();
    }
    String str = MyApplication.b().getString("userCountryISO", "");
    if (!str.isEmpty())
    {
      g = str;
      return str;
    }
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      paramString = g(paramString);
      g = paramString;
      return paramString;
    }
    return "";
  }
  
  public static int g()
  {
    O();
    return h;
  }
  
  public static String g(String paramString)
  {
    String[] arrayOfString1 = MyApplication.d().getStringArray(2130903040);
    int n = arrayOfString1.length;
    int m = 0;
    while (m < n)
    {
      String[] arrayOfString2 = arrayOfString1[m].split(",");
      if (arrayOfString2[0].trim().equals(paramString.trim())) {
        return arrayOfString2[1].trim();
      }
      m += 1;
    }
    return "";
  }
  
  public static int h()
  {
    O();
    return i;
  }
  
  public static int i()
  {
    Resources localResources = MyApplication.d();
    int m = localResources.getIdentifier("config_showNavigationBar", "bool", "android");
    if (m > 0)
    {
      if (!localResources.getBoolean(m)) {
        return 0;
      }
      m = localResources.getIdentifier("navigation_bar_height", "dimen", "android");
      if (m > 0) {
        return localResources.getDimensionPixelSize(m);
      }
      return 0;
    }
    return 0;
  }
  
  public static boolean j()
  {
    int m = Character.getDirectionality(Locale.getDefault().getDisplayName().charAt(0));
    if (m != 1) {
      return m == 2;
    }
    return true;
  }
  
  public static void k()
  {
    a(true);
  }
  
  public static String l()
  {
    return a(false);
  }
  
  private static String o(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      char c1 = paramString.charAt(0);
      if (Character.isUpperCase(c1)) {
        return paramString;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Character.toUpperCase(c1));
      localStringBuilder.append(paramString.substring(1));
      return localStringBuilder.toString();
    }
    return "";
  }
  
  private static boolean p(String paramString)
  {
    try
    {
      Class.forName(paramString);
      return true;
    }
    catch (ClassNotFoundException paramString)
    {
      for (;;) {}
    }
    return false;
  }
  
  /* Error */
  private static Uri q(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: invokestatic 79	com/eyecon/global/Central/MyApplication:a	()Landroid/content/Context;
    //   5: invokevirtual 643	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   8: getstatic 1377	android/provider/ContactsContract$CommonDataKinds$Phone:CONTENT_URI	Landroid/net/Uri;
    //   11: iconst_1
    //   12: anewarray 117	java/lang/String
    //   15: dup
    //   16: iconst_0
    //   17: ldc_w 1379
    //   20: aastore
    //   21: ldc_w 1381
    //   24: iconst_1
    //   25: anewarray 117	java/lang/String
    //   28: dup
    //   29: iconst_0
    //   30: aload_0
    //   31: aastore
    //   32: aconst_null
    //   33: invokevirtual 1387	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   36: astore_1
    //   37: aload_1
    //   38: ifnull +61 -> 99
    //   41: aload_1
    //   42: astore_0
    //   43: aload_1
    //   44: invokeinterface 1392 1 0
    //   49: ifne +6 -> 55
    //   52: goto +47 -> 99
    //   55: aload_1
    //   56: astore_0
    //   57: aload_1
    //   58: aload_1
    //   59: ldc_w 1379
    //   62: invokeinterface 1395 2 0
    //   67: invokeinterface 1396 2 0
    //   72: astore_2
    //   73: aload_1
    //   74: astore_0
    //   75: getstatic 1401	android/provider/ContactsContract$Contacts:CONTENT_VCARD_URI	Landroid/net/Uri;
    //   78: aload_2
    //   79: invokestatic 1405	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   82: astore_2
    //   83: aload_1
    //   84: ifnull +9 -> 93
    //   87: aload_1
    //   88: invokeinterface 1406 1 0
    //   93: aload_2
    //   94: areturn
    //   95: astore_2
    //   96: goto +24 -> 120
    //   99: aload_1
    //   100: ifnull +9 -> 109
    //   103: aload_1
    //   104: invokeinterface 1406 1 0
    //   109: aconst_null
    //   110: areturn
    //   111: astore_1
    //   112: aload_2
    //   113: astore_0
    //   114: goto +25 -> 139
    //   117: astore_2
    //   118: aconst_null
    //   119: astore_1
    //   120: aload_1
    //   121: astore_0
    //   122: aload_2
    //   123: invokestatic 180	com/google/a/a/a/a/a/a:a	(Ljava/lang/Throwable;)V
    //   126: aload_1
    //   127: ifnull +9 -> 136
    //   130: aload_1
    //   131: invokeinterface 1406 1 0
    //   136: aconst_null
    //   137: areturn
    //   138: astore_1
    //   139: aload_0
    //   140: ifnull +9 -> 149
    //   143: aload_0
    //   144: invokeinterface 1406 1 0
    //   149: aload_1
    //   150: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	151	0	paramString	String
    //   36	68	1	localCursor	android.database.Cursor
    //   111	1	1	localObject1	Object
    //   119	12	1	localObject2	Object
    //   138	12	1	localObject3	Object
    //   1	93	2	localObject4	Object
    //   95	18	2	localException1	Exception
    //   117	6	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   43	52	95	java/lang/Exception
    //   57	73	95	java/lang/Exception
    //   75	83	95	java/lang/Exception
    //   2	37	111	finally
    //   2	37	117	java/lang/Exception
    //   43	52	138	finally
    //   57	73	138	finally
    //   75	83	138	finally
    //   122	126	138	finally
  }
  
  public static enum a
  {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final int e = 5;
    
    public static int[] a()
    {
      return (int[])f.clone();
    }
  }
}
