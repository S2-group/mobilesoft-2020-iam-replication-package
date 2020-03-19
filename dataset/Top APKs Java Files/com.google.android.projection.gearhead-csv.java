import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import com.google.android.gms.car.Car;
import com.google.android.gms.car.Car.CarApi;
import com.google.android.gms.car.Car.CarFirstPartyApi;
import com.google.android.gms.car.CarInfo;
import com.google.android.gms.car.CarNotConnectedException;
import com.google.android.gms.car.CarNotSupportedException;
import com.google.android.gms.car.ScreenshotResult;
import com.google.android.gms.car.diagnostics.InputStreamResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.googlehelp.GoogleHelp;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class csv
{
  private static final String[] bBV = { "com.google.android.gms", "com.google.android.gms.policy_car" };
  
  public csv() {}
  
  public csv(byte paramByte)
  {
    this();
  }
  
  @NonNull
  public static Intent Hd()
  {
    return new Intent("android.intent.action.MAIN").setComponent(new ComponentName("com.google.android.projection.gearhead", "com.google.android.projection.gearhead.companion.feedback.FeedbackListActivity"));
  }
  
  @Nullable
  private static Bitmap a(Context paramContext, GoogleApiClient paramGoogleApiClient, csn paramCsn)
  {
    if ((paramContext instanceof Activity)) {
      return GoogleHelp.o((Activity)paramContext);
    }
    try
    {
      paramContext = (ScreenshotResult)bqk.aWV.aXR.z(paramGoogleApiClient).Sb();
      paramCsn.g(new String[] { "fetch screenshot status: ", paramContext.cpN.toString() });
      if (paramContext.cpN.RC())
      {
        if (Status.cwW.equals(paramContext.cpN)) {
          break label111;
        }
        throw new IllegalStateException();
      }
    }
    catch (CarNotConnectedException paramContext)
    {
      bjc.j("GH.FeedbackClient", "Car not connected fetching screenshot");
      paramCsn.g(new String[] { "Car not connected fetching screenshot" });
    }
    return null;
    label111:
    paramContext = paramContext.aAo;
    return paramContext;
  }
  
  private static String a(PackageManager paramPackageManager)
  {
    Object localObject = b(paramPackageManager);
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((HashSet)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      try
      {
        PackageInfo localPackageInfo = paramPackageManager.getPackageInfo(str, 0);
        localStringBuilder.append(str).append(": ").append(localPackageInfo.versionName).append(" / ").append(localPackageInfo.versionCode).append("\n");
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localStringBuilder.append(str).append(": <not found>\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  private static void a(Context paramContext, Bitmap paramBitmap, csn paramCsn)
  {
    if (paramBitmap != null) {
      paramCsn.g(new String[] { "fetch screenshot size: ", Long.toString(paramBitmap.getByteCount()) });
    }
    try
    {
      paramCsn.a(paramContext, paramBitmap);
      return;
    }
    catch (IOException paramContext)
    {
      bjc.d("GH.FeedbackClient", paramContext, "Couldn't write screenshot, aborting");
      paramCsn.g(new String[] { "IO error writing screenshot" });
      return;
    }
    finally
    {
      paramBitmap.recycle();
    }
  }
  
  static void a(GoogleApiClient paramGoogleApiClient, csn paramCsn)
  {
    try
    {
      Object localObject = Car.ckR.n(paramGoogleApiClient);
      paramCsn.bBo = ((CarInfo)localObject).clT;
      paramCsn.bBp = ((CarInfo)localObject).model;
      paramCsn.bBq = ((CarInfo)localObject).clU;
      paramCsn.bBr = ((CarInfo)localObject).bBr;
      paramCsn.bBs = ((CarInfo)localObject).bBs;
      int i = ((CarInfo)localObject).clW;
      int j = ((CarInfo)localObject).clX;
      paramGoogleApiClient = ((CarInfo)localObject).cma;
      localObject = ((CarInfo)localObject).cmb;
      paramCsn.bBt = (String.valueOf(paramGoogleApiClient).length() + 25 + String.valueOf(localObject).length() + i + "." + j + ":" + paramGoogleApiClient + ":" + (String)localObject);
      return;
    }
    catch (CarNotConnectedException paramGoogleApiClient)
    {
      bjc.j("GH.FeedbackClient", "Car not connected fetching CarInfo");
      paramCsn.g(new String[] { "GH.FeedbackClient", "Car not connected fetching CarInfo" });
    }
  }
  
  private static void a(String paramString, csn paramCsn, InputStreamResult paramInputStreamResult, OutputStream paramOutputStream)
  {
    paramCsn.g(new String[] { "fetch ", paramString, " status: ", paramInputStreamResult.cpN.toString() });
    Object localObject4;
    Object localObject3;
    Object localObject2;
    Object localObject1;
    if (paramInputStreamResult.cpN.RC())
    {
      localObject4 = null;
      localObject3 = null;
      localObject2 = localObject3;
      localObject1 = localObject4;
    }
    try
    {
      if (paramInputStreamResult.cpN.GH != 0)
      {
        localObject2 = localObject3;
        localObject1 = localObject4;
        throw new IllegalStateException();
      }
    }
    catch (IOException paramInputStreamResult)
    {
      localObject1 = localObject2;
      bjc.b("GH.FeedbackClient", paramInputStreamResult, "IO error writing %s", new Object[] { paramString });
      localObject1 = localObject2;
      paramString = String.valueOf(paramString);
      localObject1 = localObject2;
      if (paramString.length() != 0) {
        localObject1 = localObject2;
      }
      for (paramString = "IO error writing ".concat(paramString);; paramString = new String("IO error writing "))
      {
        localObject1 = localObject2;
        paramCsn.g(new String[] { paramString });
        return;
        localObject2 = localObject3;
        localObject1 = localObject4;
        paramInputStreamResult = paramInputStreamResult.avl;
        localObject2 = paramInputStreamResult;
        localObject1 = paramInputStreamResult;
        paramCsn.g(new String[] { "fetch ", paramString, " size: ", Long.toString(IOUtils.a(paramInputStreamResult, paramOutputStream, false, 1024)) });
        IOUtils.a(paramInputStreamResult);
        return;
        localObject1 = localObject2;
      }
    }
    finally
    {
      IOUtils.a((Closeable)localObject1);
    }
  }
  
  private static void a(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(String.format(Locale.US, "---------- %s ----------\n", new Object[] { paramString }).getBytes(StandardCharsets.UTF_8));
  }
  
  static boolean a(Context paramContext, String paramString, GoogleApiClient paramGoogleApiClient, File paramFile)
  {
    if (ayt.aDW.name().equals(paramString)) {}
    try
    {
      paramContext = new csl(paramContext, paramGoogleApiClient);
      if ((paramContext != null) && (paramContext.m(paramFile))) {
        return true;
      }
    }
    catch (CarNotSupportedException paramContext)
    {
      for (;;)
      {
        paramContext = null;
        continue;
        if (!ayt.aDX.name().equals(paramString)) {
          break;
        }
        paramContext = new csa(paramContext);
      }
      return false;
    }
    catch (CarNotConnectedException paramContext)
    {
      for (;;) {}
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        continue;
        paramContext = null;
      }
    }
  }
  
  public static HashSet<String> b(PackageManager paramPackageManager)
  {
    Object localObject = paramPackageManager.getInstalledApplications(128);
    paramPackageManager = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.metaData != null) && (localApplicationInfo.metaData.get("com.google.android.gms.car.application") != null)) {
        paramPackageManager.add(localApplicationInfo.packageName);
      }
    }
    Collections.addAll(paramPackageManager, bBV);
    return paramPackageManager;
  }
  
  @VisibleForTesting
  protected static csb br(Context paramContext)
  {
    return new csb(paramContext);
  }
  
  public final void b(@NonNull Context paramContext, @NonNull String paramString1, @NonNull String paramString2, @Nullable Bundle paramBundle)
  {
    new Thread(new csx(this, paramContext)).start();
    new csy(this, paramContext, paramString1, paramString2, paramBundle).execute(new Void[0]);
  }
  
  /* Error */
  protected final android.net.Uri c(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
  {
    // Byte code:
    //   0: ldc 117
    //   2: ldc_w 469
    //   5: invokestatic 472	bjc:h	(Ljava/lang/String;Ljava/lang/String;)V
    //   8: aload_1
    //   9: invokestatic 478	fxo:q	(Ljava/lang/Object;)Ljava/lang/Object;
    //   12: pop
    //   13: getstatic 65	bqk:aWV	Lbqk;
    //   16: getfield 482	bqk:aWZ	Lbgu;
    //   19: aload_1
    //   20: getstatic 488	csw:bBW	Lcom/google/android/gms/common/api/GoogleApiClient$OnConnectionFailedListener;
    //   23: aconst_null
    //   24: invokevirtual 493	bgu:a	(Landroid/content/Context;Lcom/google/android/gms/common/api/GoogleApiClient$OnConnectionFailedListener;Lcom/google/android/gms/car/Car$CarConnectionListener;)Lcom/google/android/gms/common/api/GoogleApiClient;
    //   27: astore 11
    //   29: new 97	csn
    //   32: dup
    //   33: invokestatic 499	bbe:pP	()Lgbh;
    //   36: new 501	java/util/Date
    //   39: dup
    //   40: invokespecial 502	java/util/Date:<init>	()V
    //   43: invokespecial 505	csn:<init>	(Lgbh;Ljava/util/Date;)V
    //   46: astore 12
    //   48: getstatic 65	bqk:aWV	Lbqk;
    //   51: getfield 508	bqk:aXQ	Lcom/google/android/gms/car/Car$CarApi;
    //   54: aload 11
    //   56: new 510	csz
    //   59: dup
    //   60: aload_0
    //   61: aload 11
    //   63: aload 12
    //   65: invokespecial 513	csz:<init>	(Lcsv;Lcom/google/android/gms/common/api/GoogleApiClient;Lcsn;)V
    //   68: invokeinterface 516 3 0
    //   73: aload 4
    //   75: ifnull +40 -> 115
    //   78: aload 4
    //   80: ldc_w 518
    //   83: invokevirtual 522	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   86: ifnull +29 -> 115
    //   89: aload 4
    //   91: ldc_w 518
    //   94: invokevirtual 522	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   97: astore 6
    //   99: aload 6
    //   101: instanceof 524
    //   104: ifne +441 -> 545
    //   107: ldc 117
    //   109: ldc_w 526
    //   112: invokestatic 124	bjc:j	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: aload 12
    //   117: aload_1
    //   118: invokevirtual 532	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   121: invokestatic 534	csv:a	(Landroid/content/pm/PackageManager;)Ljava/lang/String;
    //   124: putfield 537	csn:bBC	Ljava/lang/String;
    //   127: aload 12
    //   129: aload_3
    //   130: putfield 540	csn:bBu	Ljava/lang/String;
    //   133: aload 12
    //   135: aload_2
    //   136: putfield 543	csn:bBv	Ljava/lang/String;
    //   139: aload 11
    //   141: invokevirtual 549	com/google/android/gms/common/api/GoogleApiClient:RY	()Lcom/google/android/gms/common/ConnectionResult;
    //   144: invokevirtual 552	com/google/android/gms/common/ConnectionResult:RC	()Z
    //   147: ifeq +753 -> 900
    //   150: getstatic 65	bqk:aWV	Lbqk;
    //   153: getfield 556	bqk:aXB	Lbgx;
    //   156: astore_2
    //   157: aload_2
    //   158: ifnull +662 -> 820
    //   161: aload_2
    //   162: aload_1
    //   163: invokevirtual 562	bgx:O	(Landroid/content/Context;)Z
    //   166: ifeq +654 -> 820
    //   169: iconst_1
    //   170: istore 5
    //   172: iload 5
    //   174: ifeq +780 -> 954
    //   177: new 440	java/lang/Thread
    //   180: dup
    //   181: new 564	cta
    //   184: dup
    //   185: aload 12
    //   187: aload_1
    //   188: aload_3
    //   189: aload 11
    //   191: invokespecial 567	cta:<init>	(Lcsn;Landroid/content/Context;Ljava/lang/String;Lcom/google/android/gms/common/api/GoogleApiClient;)V
    //   194: invokespecial 448	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   197: astore 6
    //   199: aload 6
    //   201: invokevirtual 451	java/lang/Thread:start	()V
    //   204: aload 4
    //   206: ifnull +620 -> 826
    //   209: aload 4
    //   211: ldc_w 569
    //   214: invokevirtual 573	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   217: ifeq +609 -> 826
    //   220: new 575	java/io/ByteArrayInputStream
    //   223: dup
    //   224: aload 4
    //   226: ldc_w 569
    //   229: invokevirtual 579	android/os/Bundle:getByteArray	(Ljava/lang/String;)[B
    //   232: invokespecial 581	java/io/ByteArrayInputStream:<init>	([B)V
    //   235: invokestatic 587	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   238: astore_2
    //   239: aload_1
    //   240: aload_2
    //   241: aload 12
    //   243: invokestatic 589	csv:a	(Landroid/content/Context;Landroid/graphics/Bitmap;Lcsn;)V
    //   246: getstatic 65	bqk:aWV	Lbqk;
    //   249: getfield 69	bqk:aXR	Lcom/google/android/gms/car/Car$CarFirstPartyApi;
    //   252: aload 11
    //   254: invokeinterface 593 2 0
    //   259: astore_2
    //   260: aload 12
    //   262: iconst_1
    //   263: anewarray 10	java/lang/String
    //   266: dup
    //   267: iconst_0
    //   268: ldc_w 595
    //   271: aastore
    //   272: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   275: aload_2
    //   276: invokeinterface 601 1 0
    //   281: ldc2_w 602
    //   284: getstatic 609	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   287: invokevirtual 612	com/google/android/gms/common/api/PendingResult:b	(JLjava/util/concurrent/TimeUnit;)Lcom/google/android/gms/common/api/Result;
    //   290: checkcast 294	com/google/android/gms/car/diagnostics/InputStreamResult
    //   293: astore 4
    //   295: aload 12
    //   297: iconst_1
    //   298: anewarray 10	java/lang/String
    //   301: dup
    //   302: iconst_0
    //   303: ldc_w 614
    //   306: aastore
    //   307: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   310: aload 12
    //   312: iconst_1
    //   313: anewarray 10	java/lang/String
    //   316: dup
    //   317: iconst_0
    //   318: ldc_w 616
    //   321: aastore
    //   322: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   325: aload_2
    //   326: invokeinterface 619 1 0
    //   331: ldc2_w 602
    //   334: getstatic 609	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   337: invokevirtual 612	com/google/android/gms/common/api/PendingResult:b	(JLjava/util/concurrent/TimeUnit;)Lcom/google/android/gms/common/api/Result;
    //   340: checkcast 294	com/google/android/gms/car/diagnostics/InputStreamResult
    //   343: astore 7
    //   345: aload 12
    //   347: iconst_1
    //   348: anewarray 10	java/lang/String
    //   351: dup
    //   352: iconst_0
    //   353: ldc_w 621
    //   356: aastore
    //   357: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   360: aconst_null
    //   361: astore_2
    //   362: aload 12
    //   364: aload_1
    //   365: invokevirtual 625	csn:bq	(Landroid/content/Context;)Ljava/io/FileOutputStream;
    //   368: astore_3
    //   369: aload_3
    //   370: astore_2
    //   371: ldc_w 627
    //   374: aload_3
    //   375: invokestatic 629	csv:a	(Ljava/lang/String;Ljava/io/OutputStream;)V
    //   378: aload_3
    //   379: astore_2
    //   380: aload_3
    //   381: ldc_w 631
    //   384: getstatic 343	java/nio/charset/StandardCharsets:UTF_8	Ljava/nio/charset/Charset;
    //   387: invokevirtual 347	java/lang/String:getBytes	(Ljava/nio/charset/Charset;)[B
    //   390: invokevirtual 353	java/io/OutputStream:write	([B)V
    //   393: aload_3
    //   394: astore_2
    //   395: new 633	java/io/PrintWriter
    //   398: dup
    //   399: aload_3
    //   400: invokespecial 636	java/io/PrintWriter:<init>	(Ljava/io/OutputStream;)V
    //   403: astore 8
    //   405: aload_3
    //   406: astore_2
    //   407: aload 8
    //   409: invokestatic 640	bjc:dump	(Ljava/io/PrintWriter;)V
    //   412: aload_3
    //   413: astore_2
    //   414: aload 8
    //   416: invokevirtual 643	java/io/PrintWriter:flush	()V
    //   419: aload_3
    //   420: astore_2
    //   421: ldc_w 645
    //   424: aload_3
    //   425: invokestatic 629	csv:a	(Ljava/lang/String;Ljava/io/OutputStream;)V
    //   428: aload_3
    //   429: astore_2
    //   430: ldc_w 645
    //   433: aload 12
    //   435: aload 4
    //   437: aload_3
    //   438: invokestatic 647	csv:a	(Ljava/lang/String;Lcsn;Lcom/google/android/gms/car/diagnostics/InputStreamResult;Ljava/io/OutputStream;)V
    //   441: aload_3
    //   442: astore_2
    //   443: ldc_w 649
    //   446: aload 12
    //   448: aload 7
    //   450: aload_3
    //   451: invokestatic 647	csv:a	(Ljava/lang/String;Lcsn;Lcom/google/android/gms/car/diagnostics/InputStreamResult;Ljava/io/OutputStream;)V
    //   454: aload_3
    //   455: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   458: iload 5
    //   460: ifeq +8 -> 468
    //   463: aload 6
    //   465: invokevirtual 652	java/lang/Thread:join	()V
    //   468: aload 11
    //   470: invokevirtual 655	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   473: aload 12
    //   475: iconst_1
    //   476: anewarray 10	java/lang/String
    //   479: dup
    //   480: iconst_0
    //   481: ldc_w 657
    //   484: aastore
    //   485: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   488: aload 12
    //   490: aload_1
    //   491: invokevirtual 660	csn:bo	(Landroid/content/Context;)V
    //   494: aload 12
    //   496: getfield 664	csn:bBB	Ljava/io/File;
    //   499: invokestatic 668	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   502: astore_1
    //   503: aload_1
    //   504: invokestatic 272	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   507: astore_2
    //   508: ldc 117
    //   510: new 137	java/lang/StringBuilder
    //   513: dup
    //   514: aload_2
    //   515: invokestatic 272	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   518: invokevirtual 275	java/lang/String:length	()I
    //   521: bipush 25
    //   523: iadd
    //   524: invokespecial 278	java/lang/StringBuilder:<init>	(I)V
    //   527: ldc_w 670
    //   530: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: aload_2
    //   534: invokevirtual 163	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   540: invokestatic 472	bjc:h	(Ljava/lang/String;Ljava/lang/String;)V
    //   543: aload_1
    //   544: areturn
    //   545: aload 6
    //   547: checkcast 524	android/net/Uri
    //   550: astore 6
    //   552: new 672	java/io/File
    //   555: dup
    //   556: aload 6
    //   558: invokevirtual 675	android/net/Uri:getPath	()Ljava/lang/String;
    //   561: invokespecial 676	java/io/File:<init>	(Ljava/lang/String;)V
    //   564: astore 13
    //   566: aload 13
    //   568: invokevirtual 679	java/io/File:exists	()Z
    //   571: ifne +56 -> 627
    //   574: aload 6
    //   576: invokevirtual 675	android/net/Uri:getPath	()Ljava/lang/String;
    //   579: invokestatic 272	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   582: astore 6
    //   584: aload 6
    //   586: invokevirtual 275	java/lang/String:length	()I
    //   589: ifeq +23 -> 612
    //   592: ldc_w 681
    //   595: aload 6
    //   597: invokevirtual 309	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   600: astore 6
    //   602: ldc 117
    //   604: aload 6
    //   606: invokestatic 124	bjc:j	(Ljava/lang/String;Ljava/lang/String;)V
    //   609: goto -494 -> 115
    //   612: new 10	java/lang/String
    //   615: dup
    //   616: ldc_w 681
    //   619: invokespecial 324	java/lang/String:<init>	(Ljava/lang/String;)V
    //   622: astore 6
    //   624: goto -22 -> 602
    //   627: aconst_null
    //   628: astore 6
    //   630: aconst_null
    //   631: astore 7
    //   633: aconst_null
    //   634: astore 10
    //   636: aconst_null
    //   637: astore 8
    //   639: new 683	java/io/FileInputStream
    //   642: dup
    //   643: aload 13
    //   645: invokespecial 686	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   648: astore 9
    //   650: aload 7
    //   652: astore 6
    //   654: aload 9
    //   656: astore 7
    //   658: aload 10
    //   660: astore 8
    //   662: aload 12
    //   664: aload_1
    //   665: invokevirtual 625	csn:bq	(Landroid/content/Context;)Ljava/io/FileOutputStream;
    //   668: astore 10
    //   670: aload 10
    //   672: astore 6
    //   674: aload 9
    //   676: astore 7
    //   678: aload 10
    //   680: astore 8
    //   682: ldc_w 688
    //   685: aload 10
    //   687: invokestatic 629	csv:a	(Ljava/lang/String;Ljava/io/OutputStream;)V
    //   690: aload 10
    //   692: astore 6
    //   694: aload 9
    //   696: astore 7
    //   698: aload 10
    //   700: astore 8
    //   702: aload 9
    //   704: aload 10
    //   706: iconst_0
    //   707: sipush 1024
    //   710: invokestatic 323	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/InputStream;Ljava/io/OutputStream;ZI)J
    //   713: pop2
    //   714: aload 10
    //   716: astore 6
    //   718: aload 9
    //   720: astore 7
    //   722: aload 10
    //   724: astore 8
    //   726: aload 13
    //   728: invokevirtual 691	java/io/File:delete	()Z
    //   731: pop
    //   732: aload 9
    //   734: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   737: aload 10
    //   739: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   742: goto -627 -> 115
    //   745: astore 10
    //   747: aconst_null
    //   748: astore 9
    //   750: aload 8
    //   752: astore 6
    //   754: aload 9
    //   756: astore 7
    //   758: ldc 117
    //   760: aload 10
    //   762: ldc_w 693
    //   765: invokestatic 213	bjc:d	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V
    //   768: aload 8
    //   770: astore 6
    //   772: aload 9
    //   774: astore 7
    //   776: aload 12
    //   778: iconst_1
    //   779: anewarray 10	java/lang/String
    //   782: dup
    //   783: iconst_0
    //   784: ldc_w 693
    //   787: aastore
    //   788: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   791: aload 9
    //   793: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   796: aload 8
    //   798: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   801: goto -686 -> 115
    //   804: astore_1
    //   805: aconst_null
    //   806: astore 7
    //   808: aload 7
    //   810: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   813: aload 6
    //   815: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   818: aload_1
    //   819: athrow
    //   820: iconst_0
    //   821: istore 5
    //   823: goto -651 -> 172
    //   826: aload_1
    //   827: aload 11
    //   829: aload 12
    //   831: invokestatic 695	csv:a	(Landroid/content/Context;Lcom/google/android/gms/common/api/GoogleApiClient;Lcsn;)Landroid/graphics/Bitmap;
    //   834: astore_2
    //   835: goto -596 -> 239
    //   838: astore_3
    //   839: aconst_null
    //   840: astore_2
    //   841: ldc 117
    //   843: aload_3
    //   844: ldc_w 693
    //   847: invokestatic 213	bjc:d	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V
    //   850: aload 12
    //   852: iconst_1
    //   853: anewarray 10	java/lang/String
    //   856: dup
    //   857: iconst_0
    //   858: ldc_w 693
    //   861: aastore
    //   862: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   865: aload_2
    //   866: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   869: goto -411 -> 458
    //   872: astore_1
    //   873: aload 11
    //   875: invokevirtual 655	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   878: aload_1
    //   879: athrow
    //   880: astore_1
    //   881: aload_2
    //   882: invokestatic 314	com/google/android/gms/common/util/IOUtils:a	(Ljava/io/Closeable;)V
    //   885: aload_1
    //   886: athrow
    //   887: astore_2
    //   888: ldc 117
    //   890: aload_2
    //   891: ldc_w 697
    //   894: invokestatic 213	bjc:d	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V
    //   897: goto -429 -> 468
    //   900: aload 12
    //   902: iconst_1
    //   903: anewarray 10	java/lang/String
    //   906: dup
    //   907: iconst_0
    //   908: ldc_w 699
    //   911: aastore
    //   912: invokevirtual 101	csn:g	([Ljava/lang/String;)V
    //   915: goto -442 -> 473
    //   918: astore_1
    //   919: ldc 117
    //   921: aload_1
    //   922: ldc_w 701
    //   925: invokestatic 213	bjc:d	(Ljava/lang/String;Ljava/lang/Throwable;Ljava/lang/String;)V
    //   928: goto -434 -> 494
    //   931: astore_1
    //   932: goto -51 -> 881
    //   935: astore 4
    //   937: aload_3
    //   938: astore_2
    //   939: aload 4
    //   941: astore_3
    //   942: goto -101 -> 841
    //   945: astore_1
    //   946: goto -138 -> 808
    //   949: astore 10
    //   951: goto -201 -> 750
    //   954: aconst_null
    //   955: astore 6
    //   957: goto -753 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	960	0	this	csv
    //   0	960	1	paramContext	Context
    //   0	960	2	paramString1	String
    //   0	960	3	paramString2	String
    //   0	960	4	paramBundle	Bundle
    //   170	652	5	i	int
    //   97	859	6	localObject1	Object
    //   343	466	7	localObject2	Object
    //   403	394	8	localObject3	Object
    //   648	144	9	localFileInputStream	java.io.FileInputStream
    //   634	104	10	localFileOutputStream	java.io.FileOutputStream
    //   745	16	10	localIOException1	IOException
    //   949	1	10	localIOException2	IOException
    //   27	847	11	localGoogleApiClient	GoogleApiClient
    //   46	855	12	localCsn	csn
    //   564	163	13	localFile	File
    // Exception table:
    //   from	to	target	type
    //   639	650	745	java/io/IOException
    //   639	650	804	finally
    //   362	369	838	java/io/IOException
    //   150	157	872	finally
    //   161	169	872	finally
    //   177	204	872	finally
    //   209	239	872	finally
    //   239	360	872	finally
    //   454	458	872	finally
    //   463	468	872	finally
    //   826	835	872	finally
    //   865	869	872	finally
    //   881	887	872	finally
    //   888	897	872	finally
    //   362	369	880	finally
    //   371	378	880	finally
    //   380	393	880	finally
    //   395	405	880	finally
    //   407	412	880	finally
    //   414	419	880	finally
    //   421	428	880	finally
    //   430	441	880	finally
    //   443	454	880	finally
    //   463	468	887	java/lang/InterruptedException
    //   488	494	918	java/io/IOException
    //   841	865	931	finally
    //   371	378	935	java/io/IOException
    //   380	393	935	java/io/IOException
    //   395	405	935	java/io/IOException
    //   407	412	935	java/io/IOException
    //   414	419	935	java/io/IOException
    //   421	428	935	java/io/IOException
    //   430	441	935	java/io/IOException
    //   443	454	935	java/io/IOException
    //   662	670	945	finally
    //   682	690	945	finally
    //   702	714	945	finally
    //   726	732	945	finally
    //   758	768	945	finally
    //   776	791	945	finally
    //   662	670	949	java/io/IOException
    //   682	690	949	java/io/IOException
    //   702	714	949	java/io/IOException
    //   726	732	949	java/io/IOException
  }
  
  @WorkerThread
  void d(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
  {
    paramString1 = c(paramContext, paramString1, paramString2, paramBundle);
    clc.e(paramContext, new Intent("android.intent.action.MAIN").setComponent(new ComponentName("com.google.android.projection.gearhead", "com.google.android.gearhead.feedback.FeedbackForwarderActivity")).putExtras(paramBundle).setData(paramString1));
  }
}
