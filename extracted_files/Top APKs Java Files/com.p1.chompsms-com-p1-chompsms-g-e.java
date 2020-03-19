package com.p1.chompsms.g;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.MeasureSpec;
import com.p1.chompsms.activities.themesettings.CustomizeFontInfo;
import com.p1.chompsms.f;
import com.p1.chompsms.p;
import com.p1.chompsms.sms.s;
import com.p1.chompsms.system.a.1;
import com.p1.chompsms.t.k;
import com.p1.chompsms.t.l;
import com.p1.chompsms.util.BitmapUtil;
import com.p1.chompsms.util.Util;
import com.p1.chompsms.util.aw;
import com.p1.chompsms.util.bl;
import com.p1.chompsms.util.dl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public final class e
  implements Comparable
{
  public static final String p = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chomp/themes";
  public static final String[] r = { "Default", "Blue Sky", "Dark Night", "Sunny Summer", "Winter Snow", "Blue Swirl", "SGS2 inspired", "Greeny", "Dark Mode" };
  public String a;
  public String b;
  public String c;
  public String d;
  public String e;
  public String f = null;
  public int g = 0;
  public String h;
  public String i;
  public int j;
  public int k;
  public String l;
  public a m;
  public b n;
  public d o;
  public boolean q;
  private Uri s;
  
  public e() {}
  
  private e(String paramString1, String paramString2, String paramString3)
  {
    this.b = paramString1;
    this.d = paramString2;
    this.e = paramString3;
  }
  
  private Bitmap a(ZipFile paramZipFile, String paramString, Context paramContext, aw paramAw)
  {
    try
    {
      ZipEntry localZipEntry = paramZipFile.getEntry(paramString);
      if (localZipEntry != null) {
        return BitmapUtil.readBitmapWithADimensionLimit(paramZipFile.getInputStream(localZipEntry), paramAw, paramContext, true);
      }
      paramZipFile = paramZipFile.entries();
      while (paramZipFile.hasMoreElements())
      {
        paramContext = (ZipEntry)paramZipFile.nextElement();
        com.p1.chompsms.system.b.e.a("ChompSms", "Entry: " + paramContext.getName(), new Object[0]);
      }
      throw new IOException("Zip entry " + paramString + " not found for theme " + this.b);
    }
    catch (IOException paramZipFile)
    {
      com.p1.chompsms.system.b.e.a("ChompSms", paramZipFile.getMessage(), new Object[] { paramZipFile });
      return null;
    }
  }
  
  public static Uri a(e paramE)
  {
    Uri.Builder localBuilder = Uri.parse("content://com.p1.chompsms.provider.ThemeProvider/packaged-themes/" + paramE.f + "/" + paramE.g).buildUpon();
    if (paramE.h != null) {}
    for (Object localObject = paramE.h;; localObject = "NULL")
    {
      localObject = localBuilder.appendPath((String)localObject).build();
      if (paramE.f == null) {
        break;
      }
      return localObject;
    }
    return a(paramE.a);
  }
  
  public static Uri a(String paramString)
  {
    return Uri.parse("content://com.p1.chompsms.provider.ThemeProvider/themes/" + new File(paramString).getName());
  }
  
  public static e a(Context paramContext, Uri paramUri)
    throws IOException, XmlPullParserException
  {
    if (paramUri.toString().contains("packaged-theme"))
    {
      Object localObject = paramUri.getPathSegments();
      if (((List)localObject).size() < 4) {
        throw new IllegalArgumentException(paramUri.toString());
      }
      String str = (String)((List)localObject).get(((List)localObject).size() - 3);
      int i1 = Integer.parseInt((String)((List)localObject).get(((List)localObject).size() - 2));
      localObject = (String)((List)localObject).get(((List)localObject).size() - 1);
      paramUri = (Uri)localObject;
      if ("NULL".equals(localObject)) {
        paramUri = null;
      }
      paramContext = paramContext.getPackageManager();
      try
      {
        paramContext = paramContext.getResourcesForApplication(str);
        if (paramUri == null) {}
        for (paramContext = b(paramContext.openRawResource(i1));; paramContext = b(paramContext.getAssets().open(paramUri)))
        {
          paramContext.f = str;
          paramContext.g = i1;
          paramContext.h = paramUri;
          return paramContext;
        }
        paramContext = b(paramContext.getContentResolver().openInputStream(paramUri));
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        throw new IOException(paramContext.getMessage());
      }
    }
    if (paramContext != null) {
      paramContext.s = paramUri;
    }
    return paramContext;
  }
  
  public static e a(File paramFile, Context paramContext)
    throws IOException, XmlPullParserException
  {
    ZipFile localZipFile = new ZipFile(paramFile);
    Object localObject1;
    try
    {
      localObject1 = localZipFile.getEntry("theme.xml");
      if (localObject1 == null) {
        throw new IOException("No theme.xml file found");
      }
    }
    finally {}
    try
    {
      localZipFile.close();
      throw paramFile;
      Object localObject2 = localZipFile.getInputStream((ZipEntry)localObject1);
      localObject1 = c((InputStream)localObject2);
      ((e)localObject1).a = paramFile.getAbsolutePath();
      ((InputStream)localObject2).close();
      paramFile = localZipFile.entries();
      localObject2 = new HashSet();
      while (paramFile.hasMoreElements()) {
        ((HashSet)localObject2).add(((ZipEntry)paramFile.nextElement()).getName());
      }
      ((e)localObject1).m.n = ((HashSet)localObject2).contains("conversation-list-portrait.png");
      ((e)localObject1).m.o = ((HashSet)localObject2).contains("conversation-list-landscape.png");
      ((e)localObject1).n.t = ((HashSet)localObject2).contains("conversation-portrait.png");
      ((e)localObject1).n.u = ((HashSet)localObject2).contains("conversation-landscape.png");
      ((e)localObject1).e(paramContext);
      try
      {
        localZipFile.close();
        return localObject1;
      }
      catch (IOException paramFile)
      {
        return localObject1;
      }
    }
    catch (IOException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static e a(String paramString, Context paramContext)
    throws IOException, XmlPullParserException
  {
    return a(new File(paramString), paramContext);
  }
  
  public static String a(Context paramContext, e paramE, String paramString)
  {
    try
    {
      String str = "/sdcard/chomp/preview-" + paramString;
      paramE.a(paramContext, paramString, str);
      return str;
    }
    catch (IOException paramContext)
    {
      Log.w("ChompSms", paramContext.getMessage(), paramContext);
    }
    return null;
  }
  
  private String a(Context paramContext, String paramString)
    throws IOException
  {
    String str = "/sdcard/chomp/" + paramString;
    a(paramContext, paramString, str);
    return str;
  }
  
  public static String a(InputStream paramInputStream)
    throws IOException, XmlPullParserException
  {
    paramInputStream = b(paramInputStream);
    if (paramInputStream != null) {
      return paramInputStream.b;
    }
    return null;
  }
  
  public static void a(Activity paramActivity)
    throws IOException
  {
    boolean bool2 = false;
    g(paramActivity);
    Object localObject1 = new File(p + "/current.zip");
    int i1;
    e localE;
    float f1;
    label158:
    boolean bool1;
    if ((f.dc(paramActivity)) && (f.dn(paramActivity)))
    {
      i1 = 1;
      if ((((File)localObject1).exists()) || (i1 != 0)) {
        break label825;
      }
      localObject1 = paramActivity.getString(t.l.my_theme);
      String str = c((String)localObject1);
      localE = new e((String)localObject1, null, null);
      localE.i = Build.MODEL;
      localE.j = paramActivity.getResources().getDisplayMetrics().widthPixels;
      localE.k = paramActivity.getResources().getDisplayMetrics().heightPixels;
      f1 = paramActivity.getResources().getDisplayMetrics().density;
      if (f1 >= 1.0F) {
        break label782;
      }
      localE.l = "LDPI";
      localE.m = new a();
      localE.m.d = f.cL(paramActivity);
      localE.m.e = f.cQ(paramActivity);
      localE.m.f = f.cR(paramActivity);
      localE.m.g = f.cS(paramActivity);
      localE.m.h = f.cT(paramActivity);
      Object localObject2 = localE.m;
      if (f.cU(paramActivity) == null) {
        break label810;
      }
      bool1 = true;
      label246:
      ((a)localObject2).n = bool1;
      localObject2 = localE.m;
      if (f.cV(paramActivity) == null) {
        break label815;
      }
      bool1 = true;
      label268:
      ((a)localObject2).o = bool1;
      localE.m.k = f.cM(paramActivity);
      localE.m.l = f.cN(paramActivity);
      localE.m.m = f.cO(paramActivity);
      localE.m.a = f.da(paramActivity);
      localE.n = new b();
      localE.n.e = f.w(paramActivity);
      localE.n.f = f.A(paramActivity);
      localE.n.g = f.C(paramActivity);
      localE.n.h = f.z(paramActivity);
      localE.n.i = f.B(paramActivity);
      localE.n.j = f.D(paramActivity);
      localE.n.k = f.dg(paramActivity);
      localE.n.m = f.dk(paramActivity);
      localE.n.l = f.df(paramActivity);
      localObject2 = localE.n;
      if (f.du(paramActivity) == null) {
        break label820;
      }
      bool1 = true;
      label458:
      ((b)localObject2).t = bool1;
      localObject2 = localE.n;
      bool1 = bool2;
      if (f.dt(paramActivity) != null) {
        bool1 = true;
      }
      ((b)localObject2).u = bool1;
      localE.n.o = f.di(paramActivity);
      localE.n.p = f.dj(paramActivity);
      localE.n.n = f.dh(paramActivity);
      localE.n.q = f.dl(paramActivity);
      localE.n.r = f.x(paramActivity);
      localE.n.s = f.y(paramActivity);
      localE.n.a = f.dq(paramActivity);
      localE.n.d = f.ds(paramActivity);
      localE.o = new d();
      localE.o.a = f.dz(paramActivity);
      localE.o.d = f.dC(paramActivity);
      localE.o.c = f.dB(paramActivity);
      localE.o.e = f.dD(paramActivity);
      localE.o.g = f.dK(paramActivity);
      localE.o.f = f.dE(paramActivity);
      localE.o.h = f.dF(paramActivity);
      localE.o.j = f.dL(paramActivity);
      localE.o.i = f.dG(paramActivity);
      localE.o.l = f.dM(paramActivity);
      localE.o.k = f.dH(paramActivity);
      localE.o.n = f.dJ(paramActivity);
      localE.o.m = f.dI(paramActivity);
      a(paramActivity, str, localE, null, null, null, null);
      f.L(paramActivity, (String)localObject1);
    }
    for (;;)
    {
      f.h(paramActivity, true);
      return;
      i1 = 0;
      break;
      label782:
      if (f1 == 1.0F)
      {
        localE.l = "MDPI";
        break label158;
      }
      localE.l = "HDPI";
      break label158;
      label810:
      bool1 = false;
      break label246;
      label815:
      bool1 = false;
      break label268;
      label820:
      bool1 = false;
      break label458;
      label825:
      if (i1 != 0) {
        f.L(paramActivity, "Default");
      } else {
        f.L(paramActivity, "Current");
      }
    }
  }
  
  /* Error */
  public static void a(Activity paramActivity, String paramString1, e paramE, String paramString2, String paramString3, String paramString4, String paramString5)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aload_2
    //   4: aconst_null
    //   5: putfield 89	com/p1/chompsms/g/e:f	Ljava/lang/String;
    //   8: aload_2
    //   9: aload_1
    //   10: putfield 190	com/p1/chompsms/g/e:a	Ljava/lang/String;
    //   13: invokestatic 639	com/p1/chompsms/g/e:b	()V
    //   16: new 641	java/util/zip/ZipOutputStream
    //   19: dup
    //   20: new 643	java/io/FileOutputStream
    //   23: dup
    //   24: aload_1
    //   25: iconst_0
    //   26: invokespecial 646	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   29: invokespecial 649	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   32: astore 9
    //   34: aload 9
    //   36: bipush 9
    //   38: invokevirtual 653	java/util/zip/ZipOutputStream:setLevel	(I)V
    //   41: aload 9
    //   43: new 133	java/util/zip/ZipEntry
    //   46: dup
    //   47: ldc_w 286
    //   50: invokespecial 654	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   53: invokevirtual 658	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   56: aload_2
    //   57: aload 9
    //   59: invokespecial 660	com/p1/chompsms/g/e:a	(Ljava/io/OutputStream;)V
    //   62: aload 9
    //   64: invokevirtual 663	java/util/zip/ZipOutputStream:closeEntry	()V
    //   67: aload_2
    //   68: getfield 304	com/p1/chompsms/g/e:m	Lcom/p1/chompsms/g/a;
    //   71: getfield 312	com/p1/chompsms/g/a:n	Z
    //   74: ifeq +12 -> 86
    //   77: aload 9
    //   79: ldc_w 306
    //   82: aload_3
    //   83: invokestatic 666	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   86: aload_2
    //   87: getfield 304	com/p1/chompsms/g/e:m	Lcom/p1/chompsms/g/a;
    //   90: getfield 316	com/p1/chompsms/g/a:o	Z
    //   93: ifeq +13 -> 106
    //   96: aload 9
    //   98: ldc_w 314
    //   101: aload 4
    //   103: invokestatic 666	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   106: aload_2
    //   107: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   110: getfield 325	com/p1/chompsms/g/b:t	Z
    //   113: ifeq +13 -> 126
    //   116: aload 9
    //   118: ldc_w 320
    //   121: aload 5
    //   123: invokestatic 666	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   126: aload_2
    //   127: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   130: getfield 330	com/p1/chompsms/g/b:u	Z
    //   133: ifeq +13 -> 146
    //   136: aload 9
    //   138: ldc_w 327
    //   141: aload 6
    //   143: invokestatic 666	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Ljava/lang/String;Ljava/lang/String;)V
    //   146: aload_2
    //   147: getfield 304	com/p1/chompsms/g/e:m	Lcom/p1/chompsms/g/a;
    //   150: getfield 312	com/p1/chompsms/g/a:n	Z
    //   153: ifeq +181 -> 334
    //   156: aload_3
    //   157: astore_1
    //   158: aload_2
    //   159: getfield 304	com/p1/chompsms/g/e:m	Lcom/p1/chompsms/g/a;
    //   162: getfield 316	com/p1/chompsms/g/a:o	Z
    //   165: ifeq +174 -> 339
    //   168: aload 4
    //   170: astore_3
    //   171: aload_2
    //   172: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   175: getfield 325	com/p1/chompsms/g/b:t	Z
    //   178: ifeq +166 -> 344
    //   181: aload 5
    //   183: astore 4
    //   185: aload_2
    //   186: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   189: getfield 330	com/p1/chompsms/g/b:u	Z
    //   192: ifeq +158 -> 350
    //   195: aload 6
    //   197: astore 5
    //   199: aload_0
    //   200: invokevirtual 396	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   203: invokevirtual 400	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   206: getfield 405	android/util/DisplayMetrics:widthPixels	I
    //   209: istore 7
    //   211: aload_0
    //   212: invokevirtual 396	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   215: invokevirtual 400	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   218: getfield 410	android/util/DisplayMetrics:heightPixels	I
    //   221: istore 8
    //   223: aload_2
    //   224: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   227: getfield 559	com/p1/chompsms/g/b:d	Z
    //   230: ifeq +126 -> 356
    //   233: aload_0
    //   234: checkcast 376	android/app/Activity
    //   237: invokestatic 670	com/p1/chompsms/util/c:b	(Landroid/app/Activity;)V
    //   240: new 672	android/widget/FrameLayout
    //   243: dup
    //   244: aload_0
    //   245: invokespecial 674	android/widget/FrameLayout:<init>	(Landroid/content/Context;)V
    //   248: astore 6
    //   250: aload 6
    //   252: new 676	android/view/ViewGroup$LayoutParams
    //   255: dup
    //   256: iconst_m1
    //   257: iconst_m1
    //   258: invokespecial 679	android/view/ViewGroup$LayoutParams:<init>	(II)V
    //   261: invokevirtual 683	android/widget/FrameLayout:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   264: aload_0
    //   265: ldc_w 685
    //   268: invokevirtual 689	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   271: checkcast 691	android/view/LayoutInflater
    //   274: getstatic 696	com/p1/chompsms/t$h:preview_theme	I
    //   277: aload 6
    //   279: iconst_0
    //   280: invokevirtual 700	android/view/LayoutInflater:inflate	(ILandroid/view/ViewGroup;Z)Landroid/view/View;
    //   283: astore 10
    //   285: aload 6
    //   287: aload 10
    //   289: invokevirtual 704	android/widget/FrameLayout:addView	(Landroid/view/View;)V
    //   292: iload 7
    //   294: iload 8
    //   296: getstatic 710	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   299: invokestatic 714	com/p1/chompsms/util/BitmapUtil:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   302: astore 11
    //   304: aload 11
    //   306: ifnonnull +64 -> 370
    //   309: new 101	java/io/IOException
    //   312: dup
    //   313: ldc_w 716
    //   316: invokespecial 156	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   319: athrow
    //   320: astore_0
    //   321: aload 9
    //   323: astore_1
    //   324: aload_1
    //   325: ifnull +7 -> 332
    //   328: aload_1
    //   329: invokevirtual 717	java/util/zip/ZipOutputStream:close	()V
    //   332: aload_0
    //   333: athrow
    //   334: aconst_null
    //   335: astore_1
    //   336: goto -178 -> 158
    //   339: aconst_null
    //   340: astore_3
    //   341: goto -170 -> 171
    //   344: aconst_null
    //   345: astore 4
    //   347: goto -162 -> 185
    //   350: aconst_null
    //   351: astore 5
    //   353: goto -154 -> 199
    //   356: aload_0
    //   357: invokevirtual 721	android/content/Context:getTheme	()Landroid/content/res/Resources$Theme;
    //   360: getstatic 726	com/p1/chompsms/t$m:DefaultTheme	I
    //   363: iconst_1
    //   364: invokevirtual 732	android/content/res/Resources$Theme:applyStyle	(IZ)V
    //   367: goto -127 -> 240
    //   370: new 734	android/graphics/Canvas
    //   373: dup
    //   374: aload 11
    //   376: invokespecial 737	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   379: astore 12
    //   381: aload 10
    //   383: getstatic 742	com/p1/chompsms/t$g:sliding_view_container	I
    //   386: invokevirtual 748	android/view/View:findViewById	(I)Landroid/view/View;
    //   389: checkcast 750	com/p1/chompsms/views/SlidingViewContainer
    //   392: astore 13
    //   394: new 752	com/p1/chompsms/activities/themesettings/i
    //   397: dup
    //   398: aload_0
    //   399: checkcast 376	android/app/Activity
    //   402: aload 10
    //   404: invokespecial 755	com/p1/chompsms/activities/themesettings/i:<init>	(Landroid/app/Activity;Landroid/view/View;)V
    //   407: aload_2
    //   408: aload_1
    //   409: aload_3
    //   410: aload 4
    //   412: aload 5
    //   414: invokevirtual 758	com/p1/chompsms/activities/themesettings/i:a	(Lcom/p1/chompsms/g/e;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   417: aload 6
    //   419: iload 7
    //   421: iload 8
    //   423: aload 12
    //   425: invokestatic 761	com/p1/chompsms/g/e:a	(Landroid/view/View;IILandroid/graphics/Canvas;)V
    //   428: aload 13
    //   430: iconst_0
    //   431: invokevirtual 764	com/p1/chompsms/views/SlidingViewContainer:setCurrentScreen	(I)V
    //   434: aload 6
    //   436: iload 7
    //   438: iload 8
    //   440: aload 12
    //   442: invokestatic 761	com/p1/chompsms/g/e:a	(Landroid/view/View;IILandroid/graphics/Canvas;)V
    //   445: aload 9
    //   447: aload 11
    //   449: ldc_w 766
    //   452: invokestatic 769	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   455: aload 13
    //   457: iconst_1
    //   458: invokevirtual 764	com/p1/chompsms/views/SlidingViewContainer:setCurrentScreen	(I)V
    //   461: aload 10
    //   463: getstatic 772	com/p1/chompsms/t$g:conversation_list_background_preview	I
    //   466: invokevirtual 748	android/view/View:findViewById	(I)Landroid/view/View;
    //   469: checkcast 774	com/p1/chompsms/views/ScreenPreview
    //   472: invokevirtual 775	com/p1/chompsms/views/ScreenPreview:b	()V
    //   475: aload 6
    //   477: iload 7
    //   479: iload 8
    //   481: aload 12
    //   483: invokestatic 761	com/p1/chompsms/g/e:a	(Landroid/view/View;IILandroid/graphics/Canvas;)V
    //   486: aload 9
    //   488: aload 11
    //   490: ldc_w 777
    //   493: invokestatic 769	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   496: aload 13
    //   498: iconst_2
    //   499: invokevirtual 764	com/p1/chompsms/views/SlidingViewContainer:setCurrentScreen	(I)V
    //   502: aload 10
    //   504: getstatic 780	com/p1/chompsms/t$g:conversation_background_preview	I
    //   507: invokevirtual 748	android/view/View:findViewById	(I)Landroid/view/View;
    //   510: checkcast 774	com/p1/chompsms/views/ScreenPreview
    //   513: invokevirtual 775	com/p1/chompsms/views/ScreenPreview:b	()V
    //   516: aload 6
    //   518: iload 7
    //   520: iload 8
    //   522: aload 12
    //   524: invokestatic 761	com/p1/chompsms/g/e:a	(Landroid/view/View;IILandroid/graphics/Canvas;)V
    //   527: aload 9
    //   529: aload 11
    //   531: ldc_w 782
    //   534: invokestatic 769	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipOutputStream;Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   537: aload 10
    //   539: getstatic 772	com/p1/chompsms/t$g:conversation_list_background_preview	I
    //   542: invokevirtual 748	android/view/View:findViewById	(I)Landroid/view/View;
    //   545: checkcast 774	com/p1/chompsms/views/ScreenPreview
    //   548: invokevirtual 775	com/p1/chompsms/views/ScreenPreview:b	()V
    //   551: aload 10
    //   553: getstatic 780	com/p1/chompsms/t$g:conversation_background_preview	I
    //   556: invokevirtual 748	android/view/View:findViewById	(I)Landroid/view/View;
    //   559: checkcast 774	com/p1/chompsms/views/ScreenPreview
    //   562: invokevirtual 775	com/p1/chompsms/views/ScreenPreview:b	()V
    //   565: aload 9
    //   567: invokevirtual 717	java/util/zip/ZipOutputStream:close	()V
    //   570: return
    //   571: astore_0
    //   572: return
    //   573: astore_1
    //   574: goto -242 -> 332
    //   577: astore_0
    //   578: aload 10
    //   580: astore_1
    //   581: goto -257 -> 324
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	584	0	paramActivity	Activity
    //   0	584	1	paramString1	String
    //   0	584	2	paramE	e
    //   0	584	3	paramString2	String
    //   0	584	4	paramString3	String
    //   0	584	5	paramString4	String
    //   0	584	6	paramString5	String
    //   209	310	7	i1	int
    //   221	300	8	i2	int
    //   32	534	9	localZipOutputStream	ZipOutputStream
    //   1	578	10	localView	View
    //   302	228	11	localBitmap	Bitmap
    //   379	144	12	localCanvas	Canvas
    //   392	105	13	localSlidingViewContainer	com.p1.chompsms.views.SlidingViewContainer
    // Exception table:
    //   from	to	target	type
    //   34	86	320	finally
    //   86	106	320	finally
    //   106	126	320	finally
    //   126	146	320	finally
    //   146	156	320	finally
    //   158	168	320	finally
    //   171	181	320	finally
    //   185	195	320	finally
    //   199	240	320	finally
    //   240	304	320	finally
    //   309	320	320	finally
    //   356	367	320	finally
    //   370	565	320	finally
    //   565	570	571	java/io/IOException
    //   328	332	573	java/io/IOException
    //   3	34	577	finally
  }
  
  public static void a(Context paramContext)
  {
    try
    {
      g(paramContext);
      Object localObject1 = f.cJ(paramContext);
      if (a(paramContext, (CharSequence)localObject1))
      {
        a(c((String)localObject1), paramContext).d(paramContext);
        return;
      }
      localObject1 = a(c((String)localObject1), paramContext);
      Object localObject2 = a(c("Default"), paramContext);
      ((e)localObject1).n.q = ((e)localObject2).n.q;
      ((e)localObject1).n.m = ((e)localObject2).n.m;
      b();
      localObject2 = c(((e)localObject1).b) + ".tmp";
      Object localObject3 = c(((e)localObject1).b);
      ((e)localObject1).b((String)localObject2, paramContext);
      localObject3 = new File((String)localObject3);
      ((File)localObject3).delete();
      new File((String)localObject2).renameTo((File)localObject3);
      ((e)localObject1).d(paramContext);
      return;
    }
    catch (IOException paramContext)
    {
      Log.w("ChompSms", paramContext.getMessage(), paramContext);
      return;
    }
    catch (XmlPullParserException paramContext)
    {
      Log.w("ChompSms", paramContext.getMessage(), paramContext);
    }
  }
  
  private static void a(Context paramContext, int paramInt, String paramString)
    throws IOException
  {
    b();
    paramContext = paramContext.getResources().openRawResourceFd(paramInt);
    paramString = new File(p + "/" + paramString);
    if (paramString.exists()) {
      paramString.delete();
    }
    Util.a(paramContext.createInputStream(), new FileOutputStream(paramString));
  }
  
  private static void a(View paramView, int paramInt1, int paramInt2, Canvas paramCanvas)
  {
    Util.a(paramView);
    paramView.measure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
    paramView.layout(0, 0, paramInt1, paramInt2);
    paramView.draw(paramCanvas);
  }
  
  private void a(OutputStream paramOutputStream)
    throws IOException
  {
    XmlSerializer localXmlSerializer = Xml.newSerializer();
    localXmlSerializer.setOutput(paramOutputStream, "UTF-8");
    localXmlSerializer.startDocument("UTF-8", Boolean.valueOf(true));
    localXmlSerializer.startTag(null, "theme");
    s.a("name", this.b, localXmlSerializer);
    s.a("author", this.d, localXmlSerializer);
    s.a("description", this.e, localXmlSerializer);
    s.a("model", this.i, localXmlSerializer);
    s.a("width", Integer.toString(this.j), localXmlSerializer);
    s.a("height", Integer.toString(this.k), localXmlSerializer);
    s.a("screen-denstity", this.l, localXmlSerializer);
    Object localObject = this.m;
    localXmlSerializer.startTag(null, "conversation-list");
    s.a("contact-font-color", h.a(((a)localObject).d), localXmlSerializer);
    s.a("message-text-font-color", h.a(((a)localObject).e), localXmlSerializer);
    s.a("date-font-color", h.a(((a)localObject).f), localXmlSerializer);
    s.a("list-divider-color", h.a(((a)localObject).g), localXmlSerializer);
    s.a("background-color", h.a(((a)localObject).h), localXmlSerializer);
    h.a("contact-font", ((a)localObject).k, localXmlSerializer);
    h.a("message-font", ((a)localObject).l, localXmlSerializer);
    h.a("date-font", ((a)localObject).m, localXmlSerializer);
    s.a("action-bar-color", h.a(((a)localObject).c), localXmlSerializer);
    s.a("action-bar-dark-mode", ((a)localObject).a, localXmlSerializer);
    s.a("unread-dot-color", h.a(((a)localObject).i), localXmlSerializer);
    localXmlSerializer.endTag(null, "conversation-list");
    localObject = this.n;
    localXmlSerializer.startTag(null, "conversation");
    s.a("incoming-bubble-color", h.a(((b)localObject).e), localXmlSerializer);
    s.a("incoming-font-color", h.a(((b)localObject).f), localXmlSerializer);
    s.a("incoming-hyperlink-color", h.a(((b)localObject).g), localXmlSerializer);
    s.a("outgoing-bubble-color", h.a(((b)localObject).h), localXmlSerializer);
    s.a("outgoing-font-color", h.a(((b)localObject).i), localXmlSerializer);
    s.a("outgoing-hyperlink-color", h.a(((b)localObject).j), localXmlSerializer);
    s.a("date-font-color", h.a(((b)localObject).k), localXmlSerializer);
    s.a("background-color", h.a(((b)localObject).l), localXmlSerializer);
    s.a("counters-font-color", h.a(((b)localObject).m), localXmlSerializer);
    h.a("date-font", ((b)localObject).n, localXmlSerializer);
    h.a("incoming-font", ((b)localObject).o, localXmlSerializer);
    h.a("outgoing-font", ((b)localObject).p, localXmlSerializer);
    h.a("counters-font", ((b)localObject).q, localXmlSerializer);
    s.a("incoming-bubble-style", Integer.toString(((b)localObject).r), localXmlSerializer);
    s.a("outgoing-bubble-style", Integer.toString(((b)localObject).s), localXmlSerializer);
    s.a("action-bar-dark-mode", ((b)localObject).a, localXmlSerializer);
    s.a("action-bar-color", h.a(((b)localObject).b), localXmlSerializer);
    s.a("send-area-dark-mode", ((b)localObject).d, localXmlSerializer);
    localXmlSerializer.endTag(null, "conversation");
    localObject = this.o;
    localXmlSerializer.startTag(null, "quick-reply");
    s.a("background-color", h.a(((d)localObject).a), localXmlSerializer);
    s.a("recents-handle-color", h.a(((d)localObject).b), localXmlSerializer);
    s.a("contact-font-color", h.a(((d)localObject).c), localXmlSerializer);
    h.a("contact-font", ((d)localObject).d, localXmlSerializer);
    s.a("separator-color", h.a(((d)localObject).e), localXmlSerializer);
    s.a("message-font-color", h.a(((d)localObject).f), localXmlSerializer);
    h.a("message-font", ((d)localObject).g, localXmlSerializer);
    s.a("message-hyperlink-color", h.a(((d)localObject).h), localXmlSerializer);
    s.a("date-font-color", h.a(((d)localObject).i), localXmlSerializer);
    h.a("date-font", ((d)localObject).j, localXmlSerializer);
    s.a("button-font-color", h.a(((d)localObject).k), localXmlSerializer);
    h.a("button-font", ((d)localObject).l, localXmlSerializer);
    s.a("character-counter-font-color", h.a(((d)localObject).m), localXmlSerializer);
    h.a("character-counter-font", ((d)localObject).n, localXmlSerializer);
    s.a("plus-panel-dark-mode", Boolean.toString(((d)localObject).o), localXmlSerializer);
    localXmlSerializer.endTag(null, "quick-reply");
    localXmlSerializer.endTag(null, "theme");
    localXmlSerializer.flush();
    paramOutputStream.flush();
  }
  
  private static void a(String paramString, g paramG, XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    for (;;)
    {
      int i1 = paramXmlPullParser.next();
      if (((i1 == 3) && (paramXmlPullParser.getName().equals(paramString))) || (i1 == 1)) {
        break;
      }
      if (i1 == 2)
      {
        String str = paramXmlPullParser.getName();
        int i2 = paramXmlPullParser.getAttributeCount();
        HashMap localHashMap = new HashMap();
        i1 = 0;
        while (i1 < i2)
        {
          localHashMap.put(paramXmlPullParser.getAttributeName(i1), paramXmlPullParser.getAttributeValue(i1));
          i1 += 1;
        }
        paramG.a(str, paramXmlPullParser.nextText(), localHashMap);
      }
    }
  }
  
  private static void a(ZipEntry paramZipEntry, InputStream paramInputStream, ZipOutputStream paramZipOutputStream)
    throws IOException
  {
    if (paramZipEntry != null)
    {
      paramZipEntry = new ZipEntry(paramZipEntry.getName());
      try
      {
        paramZipOutputStream.putNextEntry(paramZipEntry);
        paramZipEntry = new byte[' '];
        for (;;)
        {
          int i1 = paramInputStream.read(paramZipEntry);
          if (i1 == -1) {
            break;
          }
          paramZipOutputStream.write(paramZipEntry, 0, i1);
        }
        try
        {
          paramZipOutputStream.closeEntry();
          throw paramZipEntry;
          try
          {
            paramZipOutputStream.closeEntry();
            return;
          }
          catch (IOException paramZipEntry)
          {
            return;
          }
          Log.w("ChompSms", "Couldn't find zip entry conversation-list-portrait.png");
          return;
        }
        catch (IOException paramInputStream)
        {
          for (;;) {}
        }
      }
      finally {}
    }
  }
  
  private static void a(ZipOutputStream paramZipOutputStream, Bitmap paramBitmap, String paramString)
    throws IOException
  {
    paramZipOutputStream.putNextEntry(new ZipEntry(paramString));
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 85, paramZipOutputStream);
    paramZipOutputStream.closeEntry();
  }
  
  /* Error */
  private static void a(ZipOutputStream paramZipOutputStream, String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: new 133	java/util/zip/ZipEntry
    //   4: dup
    //   5: aload_1
    //   6: invokespecial 654	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   9: invokevirtual 658	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   12: new 1052	java/io/FileInputStream
    //   15: dup
    //   16: aload_2
    //   17: invokespecial 1053	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   20: astore_2
    //   21: aload_2
    //   22: astore_1
    //   23: sipush 8000
    //   26: newarray byte
    //   28: astore 4
    //   30: aload_2
    //   31: astore_1
    //   32: aload_2
    //   33: aload 4
    //   35: invokevirtual 1054	java/io/FileInputStream:read	([B)I
    //   38: istore_3
    //   39: iload_3
    //   40: iconst_m1
    //   41: if_icmpeq +46 -> 87
    //   44: aload_2
    //   45: astore_1
    //   46: aload_0
    //   47: aload 4
    //   49: iconst_0
    //   50: iload_3
    //   51: invokevirtual 1033	java/util/zip/ZipOutputStream:write	([BII)V
    //   54: goto -24 -> 30
    //   57: astore 4
    //   59: aload_2
    //   60: astore_1
    //   61: ldc -121
    //   63: aload 4
    //   65: invokevirtual 151	java/io/IOException:getMessage	()Ljava/lang/String;
    //   68: aload 4
    //   70: invokestatic 348	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   73: pop
    //   74: aload_2
    //   75: ifnull +7 -> 82
    //   78: aload_2
    //   79: invokevirtual 1055	java/io/FileInputStream:close	()V
    //   82: aload_0
    //   83: invokevirtual 663	java/util/zip/ZipOutputStream:closeEntry	()V
    //   86: return
    //   87: aload_2
    //   88: invokevirtual 1055	java/io/FileInputStream:close	()V
    //   91: aload_0
    //   92: invokevirtual 663	java/util/zip/ZipOutputStream:closeEntry	()V
    //   95: return
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_1
    //   99: aload_1
    //   100: ifnull +7 -> 107
    //   103: aload_1
    //   104: invokevirtual 1055	java/io/FileInputStream:close	()V
    //   107: aload_0
    //   108: invokevirtual 663	java/util/zip/ZipOutputStream:closeEntry	()V
    //   111: aload_2
    //   112: athrow
    //   113: astore_1
    //   114: goto -23 -> 91
    //   117: astore_1
    //   118: goto -36 -> 82
    //   121: astore_1
    //   122: goto -15 -> 107
    //   125: astore_2
    //   126: goto -27 -> 99
    //   129: astore 4
    //   131: aconst_null
    //   132: astore_2
    //   133: goto -74 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	paramZipOutputStream	ZipOutputStream
    //   0	136	1	paramString1	String
    //   0	136	2	paramString2	String
    //   38	13	3	i1	int
    //   28	20	4	arrayOfByte	byte[]
    //   57	12	4	localIOException1	IOException
    //   129	1	4	localIOException2	IOException
    // Exception table:
    //   from	to	target	type
    //   23	30	57	java/io/IOException
    //   32	39	57	java/io/IOException
    //   46	54	57	java/io/IOException
    //   12	21	96	finally
    //   87	91	113	java/io/IOException
    //   78	82	117	java/io/IOException
    //   103	107	121	java/io/IOException
    //   23	30	125	finally
    //   32	39	125	finally
    //   46	54	125	finally
    //   61	74	125	finally
    //   12	21	129	java/io/IOException
  }
  
  public static boolean a(Context paramContext, CharSequence paramCharSequence)
  {
    int i2 = r.length;
    int i1;
    if (paramCharSequence == null)
    {
      localObject = null;
      i1 = 0;
    }
    for (;;)
    {
      if (i1 >= i2) {
        break label52;
      }
      if (r[i1].equals(localObject))
      {
        return true;
        localObject = paramCharSequence.toString();
        break;
      }
      i1 += 1;
    }
    label52:
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getInstalledApplications(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.packageName.startsWith("com.p1.chompsms.themes.")) {
        try
        {
          boolean bool = Util.a(paramContext.getResourcesForApplication(localApplicationInfo).getAssets().list("themes"), b(paramCharSequence.toString().trim()));
          if (bool) {
            return true;
          }
        }
        catch (Exception localException)
        {
          Log.e("ChompSms", "Failed to load pacakage", localException);
        }
      }
    }
    return false;
  }
  
  public static e b(Bundle paramBundle)
  {
    e localE = new e();
    localE.b = paramBundle.getString("theme.name");
    localE.e = paramBundle.getString("theme.description");
    localE.d = paramBundle.getString("theme.author");
    localE.i = paramBundle.getString("theme.model");
    localE.j = paramBundle.getInt("theme.width");
    localE.k = paramBundle.getInt("theme.height");
    localE.l = paramBundle.getString("theme.density");
    localE.m = a.b(paramBundle);
    localE.n = b.b(paramBundle);
    localE.o = d.a(paramBundle);
    localE.a = paramBundle.getString("theme.path");
    localE.s = ((Uri)paramBundle.getParcelable("theme.uri"));
    localE.f = paramBundle.getString("theme.packageName");
    localE.g = paramBundle.getInt("theme.packageResourceId");
    localE.h = paramBundle.getString("theme.assetPath");
    return localE;
  }
  
  /* Error */
  public static e b(InputStream paramInputStream)
    throws IOException, XmlPullParserException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 1155	java/util/zip/ZipInputStream
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 1158	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   10: astore_1
    //   11: new 298	java/util/HashSet
    //   14: dup
    //   15: invokespecial 299	java/util/HashSet:<init>	()V
    //   18: astore_3
    //   19: aload_2
    //   20: astore_0
    //   21: aload_1
    //   22: invokevirtual 1162	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull +48 -> 75
    //   30: aload_3
    //   31: aload_2
    //   32: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   35: invokevirtual 302	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   38: pop
    //   39: aload_2
    //   40: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   43: ldc_w 286
    //   46: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   49: ifeq -28 -> 21
    //   52: aload_1
    //   53: invokestatic 293	com/p1/chompsms/g/e:c	(Ljava/io/InputStream;)Lcom/p1/chompsms/g/e;
    //   56: astore_0
    //   57: aload_1
    //   58: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   61: goto -40 -> 21
    //   64: astore_0
    //   65: aload_1
    //   66: ifnull +7 -> 73
    //   69: aload_1
    //   70: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   73: aload_0
    //   74: athrow
    //   75: aload_0
    //   76: ifnull +59 -> 135
    //   79: aload_0
    //   80: getfield 304	com/p1/chompsms/g/e:m	Lcom/p1/chompsms/g/a;
    //   83: aload_3
    //   84: ldc_w 306
    //   87: invokevirtual 308	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   90: putfield 312	com/p1/chompsms/g/a:n	Z
    //   93: aload_0
    //   94: getfield 304	com/p1/chompsms/g/e:m	Lcom/p1/chompsms/g/a;
    //   97: aload_3
    //   98: ldc_w 314
    //   101: invokevirtual 308	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   104: putfield 316	com/p1/chompsms/g/a:o	Z
    //   107: aload_0
    //   108: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   111: aload_3
    //   112: ldc_w 320
    //   115: invokevirtual 308	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   118: putfield 325	com/p1/chompsms/g/b:t	Z
    //   121: aload_0
    //   122: getfield 318	com/p1/chompsms/g/e:n	Lcom/p1/chompsms/g/b;
    //   125: aload_3
    //   126: ldc_w 327
    //   129: invokevirtual 308	java/util/HashSet:contains	(Ljava/lang/Object;)Z
    //   132: putfield 330	com/p1/chompsms/g/b:u	Z
    //   135: aload_1
    //   136: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   139: aload_0
    //   140: areturn
    //   141: astore_1
    //   142: aload_0
    //   143: areturn
    //   144: astore_1
    //   145: goto -72 -> 73
    //   148: astore_0
    //   149: aconst_null
    //   150: astore_1
    //   151: goto -86 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	paramInputStream	InputStream
    //   10	126	1	localZipInputStream	ZipInputStream
    //   141	1	1	localIOException1	IOException
    //   144	1	1	localIOException2	IOException
    //   150	1	1	localObject	Object
    //   1	39	2	localZipEntry	ZipEntry
    //   18	108	3	localHashSet	HashSet
    // Exception table:
    //   from	to	target	type
    //   11	19	64	finally
    //   21	26	64	finally
    //   30	61	64	finally
    //   79	135	64	finally
    //   135	139	141	java/io/IOException
    //   69	73	144	java/io/IOException
    //   2	11	148	finally
  }
  
  public static String b(String paramString)
  {
    return paramString.toLowerCase().replaceAll(" ", "_") + ".zip";
  }
  
  private static void b()
  {
    new File(p).mkdirs();
  }
  
  /* Error */
  private void b(String paramString, Context paramContext)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aload_0
    //   7: getfield 190	com/p1/chompsms/g/e:a	Ljava/lang/String;
    //   10: ifnull +173 -> 183
    //   13: new 1052	java/io/FileInputStream
    //   16: dup
    //   17: aload_0
    //   18: getfield 190	com/p1/chompsms/g/e:a	Ljava/lang/String;
    //   21: invokespecial 1053	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   24: astore_2
    //   25: new 1155	java/util/zip/ZipInputStream
    //   28: dup
    //   29: aload_2
    //   30: invokespecial 1158	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   33: astore_2
    //   34: new 641	java/util/zip/ZipOutputStream
    //   37: dup
    //   38: new 643	java/io/FileOutputStream
    //   41: dup
    //   42: aload_1
    //   43: iconst_0
    //   44: invokespecial 646	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   47: invokespecial 649	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   50: astore_1
    //   51: aload_1
    //   52: bipush 9
    //   54: invokevirtual 653	java/util/zip/ZipOutputStream:setLevel	(I)V
    //   57: aload_1
    //   58: new 133	java/util/zip/ZipEntry
    //   61: dup
    //   62: ldc_w 286
    //   65: invokespecial 654	java/util/zip/ZipEntry:<init>	(Ljava/lang/String;)V
    //   68: invokevirtual 658	java/util/zip/ZipOutputStream:putNextEntry	(Ljava/util/zip/ZipEntry;)V
    //   71: aload_0
    //   72: aload_1
    //   73: invokespecial 660	com/p1/chompsms/g/e:a	(Ljava/io/OutputStream;)V
    //   76: aload_1
    //   77: invokevirtual 663	java/util/zip/ZipOutputStream:closeEntry	()V
    //   80: aload_2
    //   81: invokevirtual 1162	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   84: astore 4
    //   86: aload 4
    //   88: ifnull +149 -> 237
    //   91: aload 4
    //   93: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   96: ldc_w 1182
    //   99: invokevirtual 1185	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   102: ifne +17 -> 119
    //   105: aload 4
    //   107: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   110: ldc_w 1187
    //   113: invokevirtual 1185	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   116: ifeq -36 -> 80
    //   119: aload 4
    //   121: aload_2
    //   122: aload_1
    //   123: invokestatic 1189	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipEntry;Ljava/io/InputStream;Ljava/util/zip/ZipOutputStream;)V
    //   126: goto -46 -> 80
    //   129: astore 5
    //   131: aload_2
    //   132: astore 4
    //   134: aload_1
    //   135: astore_2
    //   136: aload 4
    //   138: astore_1
    //   139: aload 5
    //   141: astore 4
    //   143: new 101	java/io/IOException
    //   146: dup
    //   147: aload 4
    //   149: invokespecial 1192	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   152: athrow
    //   153: astore 5
    //   155: aload_2
    //   156: astore 4
    //   158: aload_1
    //   159: astore_2
    //   160: aload 5
    //   162: astore_1
    //   163: aload_2
    //   164: ifnull +7 -> 171
    //   167: aload_2
    //   168: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   171: aload 4
    //   173: ifnull +8 -> 181
    //   176: aload 4
    //   178: invokevirtual 717	java/util/zip/ZipOutputStream:close	()V
    //   181: aload_1
    //   182: athrow
    //   183: aload_0
    //   184: getfield 89	com/p1/chompsms/g/e:f	Ljava/lang/String;
    //   187: astore 7
    //   189: aload_0
    //   190: getfield 91	com/p1/chompsms/g/e:g	I
    //   193: istore_3
    //   194: aload_0
    //   195: getfield 176	com/p1/chompsms/g/e:h	Ljava/lang/String;
    //   198: astore 6
    //   200: aload_2
    //   201: invokevirtual 241	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   204: aload 7
    //   206: invokevirtual 247	android/content/pm/PackageManager:getResourcesForApplication	(Ljava/lang/String;)Landroid/content/res/Resources;
    //   209: astore_2
    //   210: aload 6
    //   212: ifnull +16 -> 228
    //   215: aload_2
    //   216: invokevirtual 260	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   219: aload 6
    //   221: invokevirtual 266	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   224: astore_2
    //   225: goto -200 -> 25
    //   228: aload_2
    //   229: iload_3
    //   230: invokevirtual 253	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   233: astore_2
    //   234: goto -209 -> 25
    //   237: aload_2
    //   238: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   241: aload_1
    //   242: invokevirtual 717	java/util/zip/ZipOutputStream:close	()V
    //   245: return
    //   246: astore_2
    //   247: goto -6 -> 241
    //   250: astore_1
    //   251: return
    //   252: astore_2
    //   253: goto -82 -> 171
    //   256: astore_2
    //   257: goto -76 -> 181
    //   260: astore_1
    //   261: aconst_null
    //   262: astore_2
    //   263: goto -100 -> 163
    //   266: astore_1
    //   267: goto -104 -> 163
    //   270: astore 5
    //   272: aload_1
    //   273: astore 4
    //   275: aload 5
    //   277: astore_1
    //   278: goto -115 -> 163
    //   281: astore 4
    //   283: aconst_null
    //   284: astore_2
    //   285: aload 5
    //   287: astore_1
    //   288: goto -145 -> 143
    //   291: astore 4
    //   293: aconst_null
    //   294: astore 5
    //   296: aload_2
    //   297: astore_1
    //   298: aload 5
    //   300: astore_2
    //   301: goto -158 -> 143
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	304	0	this	e
    //   0	304	1	paramString	String
    //   0	304	2	paramContext	Context
    //   193	37	3	i1	int
    //   1	273	4	localObject1	Object
    //   281	1	4	localNameNotFoundException1	PackageManager.NameNotFoundException
    //   291	1	4	localNameNotFoundException2	PackageManager.NameNotFoundException
    //   4	1	5	localObject2	Object
    //   129	11	5	localNameNotFoundException3	PackageManager.NameNotFoundException
    //   153	8	5	localObject3	Object
    //   270	16	5	localObject4	Object
    //   294	5	5	localObject5	Object
    //   198	22	6	str1	String
    //   187	18	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   51	80	129	android/content/pm/PackageManager$NameNotFoundException
    //   80	86	129	android/content/pm/PackageManager$NameNotFoundException
    //   91	119	129	android/content/pm/PackageManager$NameNotFoundException
    //   119	126	129	android/content/pm/PackageManager$NameNotFoundException
    //   143	153	153	finally
    //   237	241	246	java/io/IOException
    //   241	245	250	java/io/IOException
    //   167	171	252	java/io/IOException
    //   176	181	256	java/io/IOException
    //   6	25	260	finally
    //   25	34	260	finally
    //   183	210	260	finally
    //   215	225	260	finally
    //   228	234	260	finally
    //   34	51	266	finally
    //   51	80	270	finally
    //   80	86	270	finally
    //   91	119	270	finally
    //   119	126	270	finally
    //   6	25	281	android/content/pm/PackageManager$NameNotFoundException
    //   25	34	281	android/content/pm/PackageManager$NameNotFoundException
    //   183	210	281	android/content/pm/PackageManager$NameNotFoundException
    //   215	225	281	android/content/pm/PackageManager$NameNotFoundException
    //   228	234	281	android/content/pm/PackageManager$NameNotFoundException
    //   34	51	291	android/content/pm/PackageManager$NameNotFoundException
  }
  
  /* Error */
  public static boolean b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 1197	com/p1/chompsms/t$k:default_theme	I
    //   4: ldc_w 1199
    //   7: invokestatic 1201	com/p1/chompsms/g/e:a	(Landroid/content/Context;ILjava/lang/String;)V
    //   10: aload_0
    //   11: getstatic 1204	com/p1/chompsms/t$k:dark_mode_theme	I
    //   14: ldc_w 1206
    //   17: invokestatic 1201	com/p1/chompsms/g/e:a	(Landroid/content/Context;ILjava/lang/String;)V
    //   20: aload_0
    //   21: invokestatic 785	com/p1/chompsms/f:cJ	(Landroid/content/Context;)Ljava/lang/String;
    //   24: ifnull +15 -> 39
    //   27: aload_0
    //   28: invokestatic 785	com/p1/chompsms/f:cJ	(Landroid/content/Context;)Ljava/lang/String;
    //   31: ldc 67
    //   33: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   36: ifeq +17 -> 53
    //   39: aload_0
    //   40: ldc_w 1199
    //   43: invokestatic 192	com/p1/chompsms/g/e:a	(Ljava/lang/String;)Landroid/net/Uri;
    //   46: invokestatic 1208	com/p1/chompsms/g/e:a	(Landroid/content/Context;Landroid/net/Uri;)Lcom/p1/chompsms/g/e;
    //   49: aload_0
    //   50: invokevirtual 792	com/p1/chompsms/g/e:d	(Landroid/content/Context;)V
    //   53: aload_0
    //   54: invokestatic 785	com/p1/chompsms/f:cJ	(Landroid/content/Context;)Ljava/lang/String;
    //   57: ifnull +15 -> 72
    //   60: aload_0
    //   61: invokestatic 785	com/p1/chompsms/f:cJ	(Landroid/content/Context;)Ljava/lang/String;
    //   64: ldc 83
    //   66: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   69: ifeq +17 -> 86
    //   72: aload_0
    //   73: ldc_w 1206
    //   76: invokestatic 192	com/p1/chompsms/g/e:a	(Ljava/lang/String;)Landroid/net/Uri;
    //   79: invokestatic 1208	com/p1/chompsms/g/e:a	(Landroid/content/Context;Landroid/net/Uri;)Lcom/p1/chompsms/g/e;
    //   82: aload_0
    //   83: invokevirtual 792	com/p1/chompsms/g/e:d	(Landroid/content/Context;)V
    //   86: aload_0
    //   87: iconst_1
    //   88: invokestatic 631	com/p1/chompsms/f:h	(Landroid/content/Context;Z)V
    //   91: iconst_1
    //   92: ireturn
    //   93: astore_0
    //   94: ldc -121
    //   96: aload_0
    //   97: invokevirtual 151	java/io/IOException:getMessage	()Ljava/lang/String;
    //   100: aload_0
    //   101: invokestatic 348	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   104: pop
    //   105: iconst_0
    //   106: ireturn
    //   107: astore_0
    //   108: iconst_0
    //   109: ireturn
    //   110: astore_0
    //   111: iconst_0
    //   112: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   0	20	93	java/io/IOException
    //   39	53	107	java/lang/Exception
    //   72	86	110	java/lang/Exception
  }
  
  public static e c(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    Object localObject1 = XmlPullParserFactory.newInstance();
    ((XmlPullParserFactory)localObject1).setNamespaceAware(false);
    localObject1 = ((XmlPullParserFactory)localObject1).newPullParser();
    ((XmlPullParser)localObject1).setInput(paramInputStream, "UTF-8");
    dl.a((XmlPullParser)localObject1, "theme");
    int i2 = ((XmlPullParser)localObject1).getDepth();
    paramInputStream = new e();
    for (;;)
    {
      dl.a((XmlPullParser)localObject1, i2 + 1);
      if (((XmlPullParser)localObject1).getEventType() == 1) {
        break;
      }
      Object localObject2 = ((XmlPullParser)localObject1).getName();
      if (((String)localObject2).equals("name"))
      {
        paramInputStream.b = ((XmlPullParser)localObject1).nextText();
        paramInputStream.c = paramInputStream.b;
      }
      else if (((String)localObject2).equals("author"))
      {
        paramInputStream.d = ((XmlPullParser)localObject1).nextText();
      }
      else if (((String)localObject2).equals("description"))
      {
        paramInputStream.e = ((XmlPullParser)localObject1).nextText();
      }
      else if (((String)localObject2).equals("model"))
      {
        paramInputStream.i = ((XmlPullParser)localObject1).nextText();
      }
      else if (((String)localObject2).equals("width"))
      {
        paramInputStream.j = h.b((String)localObject2, ((XmlPullParser)localObject1).nextText());
      }
      else if (((String)localObject2).equals("height"))
      {
        paramInputStream.k = h.b((String)localObject2, ((XmlPullParser)localObject1).nextText());
      }
      else if (((String)localObject2).equals("screen-density"))
      {
        paramInputStream.l = ((XmlPullParser)localObject1).nextText();
      }
      else
      {
        int i1;
        if (((String)localObject2).equals("conversation-list"))
        {
          if (paramInputStream.m != null) {
            throw new XmlPullParserException("conversation-list element can only appear once");
          }
          paramInputStream.m = new a();
          a("conversation-list", paramInputStream.m, (XmlPullParser)localObject1);
          if (!paramInputStream.m.b)
          {
            localObject2 = paramInputStream.m;
            if (!paramInputStream.m.a) {
              break label371;
            }
          }
          label371:
          for (i1 = -13421773;; i1 = -1710619)
          {
            ((a)localObject2).c = i1;
            if (paramInputStream.m.j) {
              break;
            }
            paramInputStream.m.i = paramInputStream.m.c;
            break;
          }
        }
        if (((String)localObject2).equals("conversation"))
        {
          if (paramInputStream.n != null) {
            throw new XmlPullParserException("conversation element can only appear once");
          }
          paramInputStream.n = new b();
          a("conversation", paramInputStream.n, (XmlPullParser)localObject1);
          if (!paramInputStream.n.c)
          {
            localObject2 = paramInputStream.n;
            if (paramInputStream.n.a) {}
            for (i1 = -13421773;; i1 = -1710619)
            {
              ((b)localObject2).b = i1;
              break;
            }
          }
        }
        else if (((String)localObject2).equals("quick-reply"))
        {
          if (paramInputStream.o != null) {
            throw new XmlPullParserException("quick-reply element can only appear once");
          }
          paramInputStream.o = new d();
          a("quick-reply", paramInputStream.o, (XmlPullParser)localObject1);
        }
      }
    }
    if (paramInputStream.o == null) {
      paramInputStream.o = new d();
    }
    return paramInputStream;
  }
  
  public static String c(String paramString)
  {
    return p + "/" + b(paramString);
  }
  
  public static void c(Context paramContext)
  {
    Object localObject = f.cJ(paramContext);
    if ((!"Default".equals(localObject)) && (!"Dark Mode".equals(localObject)) && (a(paramContext, (CharSequence)localObject)))
    {
      localObject = h.a(paramContext, "com.p1.chompsms.themes", ((String)localObject).toLowerCase().replace(" ", "_") + "_theme");
      if (localObject == null) {}
    }
    try
    {
      ((e)localObject).d(paramContext);
      return;
    }
    catch (IOException paramContext)
    {
      Log.w("ChompSms", "failed to re-apply theme", paramContext);
    }
  }
  
  private static void g(Context paramContext)
    throws IOException
  {
    File localFile = new File(p);
    if ((!localFile.exists()) && (!localFile.mkdirs())) {
      throw new IOException("Failed to create themes dir!");
    }
    a(paramContext, t.k.default_theme, "default.zip");
    a(paramContext, t.k.dark_mode_theme, "dark_mode.zip");
  }
  
  private ZipInputStream h(Context paramContext)
    throws IOException
  {
    if (this.a != null) {
      return new ZipInputStream(new FileInputStream(this.a));
    }
    if (this.s != null) {
      return new ZipInputStream(paramContext.getContentResolver().openInputStream(this.s));
    }
    if ((this.f != null) && (this.g != 0)) {
      try
      {
        paramContext = new ZipInputStream(paramContext.getPackageManager().getResourcesForApplication(this.f).openRawResource(this.g));
        return paramContext;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        throw new IllegalStateException("Package " + this.f + " missing");
      }
    }
    if ((this.f != null) && (this.h != null)) {
      try
      {
        paramContext = new ZipInputStream(paramContext.getPackageManager().getResourcesForApplication(this.f).getAssets().open(this.h));
        return paramContext;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        throw new IllegalStateException("Package " + this.f + " missing");
      }
    }
    throw new IllegalStateException("No Uri or path specified for theme");
  }
  
  public final Bundle a(Bundle paramBundle)
  {
    paramBundle.putString("theme.name", this.b);
    paramBundle.putString("theme.description", this.e);
    paramBundle.putString("theme.author", this.d);
    paramBundle.putString("theme.model", this.i);
    paramBundle.putInt("theme.width", this.j);
    paramBundle.putInt("theme.height", this.k);
    paramBundle.putString("theme.density", this.l);
    paramBundle.putString("theme.path", this.a);
    paramBundle.putParcelable("theme.uri", this.s);
    paramBundle.putString("theme.packageName", this.f);
    paramBundle.putInt("theme.packageResourceId", this.g);
    paramBundle.putString("theme.assetPath", this.h);
    this.m.a(paramBundle);
    this.n.a(paramBundle);
    this.o.b(paramBundle);
    return paramBundle;
  }
  
  /* Error */
  public final void a(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_0
    //   4: aload_1
    //   5: invokespecial 1308	com/p1/chompsms/g/e:h	(Landroid/content/Context;)Ljava/util/zip/ZipInputStream;
    //   8: astore_1
    //   9: aload_1
    //   10: invokevirtual 1162	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   13: astore 5
    //   15: aload 5
    //   17: ifnull +85 -> 102
    //   20: aload 5
    //   22: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   25: aload_2
    //   26: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   29: ifeq -20 -> 9
    //   32: new 643	java/io/FileOutputStream
    //   35: dup
    //   36: aload_3
    //   37: iconst_0
    //   38: invokespecial 646	java/io/FileOutputStream:<init>	(Ljava/lang/String;Z)V
    //   41: astore_2
    //   42: sipush 8192
    //   45: newarray byte
    //   47: astore 5
    //   49: aload_1
    //   50: aload 5
    //   52: invokevirtual 1309	java/util/zip/ZipInputStream:read	([B)I
    //   55: istore 4
    //   57: aload_2
    //   58: astore_3
    //   59: iload 4
    //   61: iconst_m1
    //   62: if_icmpeq +42 -> 104
    //   65: aload_2
    //   66: aload 5
    //   68: iconst_0
    //   69: iload 4
    //   71: invokevirtual 1310	java/io/FileOutputStream:write	([BII)V
    //   74: goto -25 -> 49
    //   77: astore 5
    //   79: aload_1
    //   80: astore_3
    //   81: aload 5
    //   83: astore_1
    //   84: aload_2
    //   85: ifnull +7 -> 92
    //   88: aload_2
    //   89: invokevirtual 1311	java/io/FileOutputStream:close	()V
    //   92: aload_3
    //   93: ifnull +7 -> 100
    //   96: aload_3
    //   97: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   100: aload_1
    //   101: athrow
    //   102: aconst_null
    //   103: astore_3
    //   104: aload_3
    //   105: ifnull +7 -> 112
    //   108: aload_3
    //   109: invokevirtual 1311	java/io/FileOutputStream:close	()V
    //   112: aload_1
    //   113: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   116: return
    //   117: astore_2
    //   118: goto -6 -> 112
    //   121: astore_1
    //   122: return
    //   123: astore_2
    //   124: goto -32 -> 92
    //   127: astore_2
    //   128: goto -28 -> 100
    //   131: astore_1
    //   132: aconst_null
    //   133: astore_2
    //   134: aload 5
    //   136: astore_3
    //   137: goto -53 -> 84
    //   140: astore 5
    //   142: aconst_null
    //   143: astore_2
    //   144: aload_1
    //   145: astore_3
    //   146: aload 5
    //   148: astore_1
    //   149: goto -65 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	152	0	this	e
    //   0	152	1	paramContext	Context
    //   0	152	2	paramString1	String
    //   0	152	3	paramString2	String
    //   55	15	4	i1	int
    //   1	66	5	localObject1	Object
    //   77	58	5	localObject2	Object
    //   140	7	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   42	49	77	finally
    //   49	57	77	finally
    //   65	74	77	finally
    //   108	112	117	java/io/IOException
    //   112	116	121	java/io/IOException
    //   88	92	123	java/io/IOException
    //   96	100	127	java/io/IOException
    //   3	9	131	finally
    //   9	15	140	finally
    //   20	42	140	finally
  }
  
  public final void a(CharSequence paramCharSequence, Context paramContext)
    throws IOException
  {
    a(paramCharSequence, paramContext, new File(p));
  }
  
  public final void a(CharSequence paramCharSequence, Context paramContext, File paramFile)
    throws IOException
  {
    if (new File(paramFile, paramCharSequence.toString()).exists()) {
      throw new IOException("Theme name " + paramCharSequence + " already exists!");
    }
    if (a())
    {
      this.d = null;
      this.e = null;
    }
    this.b = paramCharSequence.toString();
    paramCharSequence = c(this.b);
    b(paramCharSequence, paramContext);
    this.a = paramCharSequence;
  }
  
  public final boolean a()
  {
    return ((this.b != null) && ((this.b.equals("Default")) || (Util.a(r, this.b)))) || (this.f != null);
  }
  
  /* Error */
  public final Bitmap[] a(Context paramContext, aw paramAw)
    throws IOException
  {
    // Byte code:
    //   0: iconst_3
    //   1: anewarray 1046	android/graphics/Bitmap
    //   4: astore 6
    //   6: aload_0
    //   7: getfield 89	com/p1/chompsms/g/e:f	Ljava/lang/String;
    //   10: ifnull +316 -> 326
    //   13: aload_1
    //   14: invokevirtual 241	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: aload_0
    //   18: getfield 89	com/p1/chompsms/g/e:f	Ljava/lang/String;
    //   21: invokevirtual 247	android/content/pm/PackageManager:getResourcesForApplication	(Ljava/lang/String;)Landroid/content/res/Resources;
    //   24: astore 7
    //   26: aconst_null
    //   27: astore 4
    //   29: aload 4
    //   31: astore 5
    //   33: aload_0
    //   34: getfield 176	com/p1/chompsms/g/e:h	Ljava/lang/String;
    //   37: ifnull +116 -> 153
    //   40: aload 4
    //   42: astore 5
    //   44: new 1155	java/util/zip/ZipInputStream
    //   47: dup
    //   48: aload 7
    //   50: invokevirtual 260	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
    //   53: aload_0
    //   54: getfield 176	com/p1/chompsms/g/e:h	Ljava/lang/String;
    //   57: invokevirtual 266	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   60: invokespecial 1158	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   63: astore 4
    //   65: aload 4
    //   67: astore 5
    //   69: aload 4
    //   71: invokevirtual 1162	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   74: astore 7
    //   76: aload 7
    //   78: ifnull +236 -> 314
    //   81: aload 4
    //   83: astore 5
    //   85: aload 7
    //   87: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   90: ldc_w 766
    //   93: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   96: istore_3
    //   97: iload_3
    //   98: ifeq +96 -> 194
    //   101: aload 6
    //   103: iconst_0
    //   104: aload 4
    //   106: aload_2
    //   107: aload_1
    //   108: iconst_0
    //   109: invokestatic 117	com/p1/chompsms/util/BitmapUtil:readBitmapWithADimensionLimit	(Ljava/io/InputStream;Lcom/p1/chompsms/util/aw;Landroid/content/Context;Z)Landroid/graphics/Bitmap;
    //   112: aastore
    //   113: aload 4
    //   115: astore 5
    //   117: aload 4
    //   119: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   122: goto -57 -> 65
    //   125: astore_1
    //   126: aload 5
    //   128: ifnull +8 -> 136
    //   131: aload 5
    //   133: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   136: aload_1
    //   137: athrow
    //   138: astore_1
    //   139: ldc -121
    //   141: aload_1
    //   142: invokevirtual 267	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   145: aload_1
    //   146: invokestatic 348	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   149: pop
    //   150: aload 6
    //   152: areturn
    //   153: aload 4
    //   155: astore 5
    //   157: new 1155	java/util/zip/ZipInputStream
    //   160: dup
    //   161: aload 7
    //   163: aload_0
    //   164: getfield 91	com/p1/chompsms/g/e:g	I
    //   167: invokevirtual 253	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   170: invokespecial 1158	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   173: astore 4
    //   175: goto -110 -> 65
    //   178: astore_1
    //   179: aload 4
    //   181: astore 5
    //   183: aload 4
    //   185: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   188: aload 4
    //   190: astore 5
    //   192: aload_1
    //   193: athrow
    //   194: aload 4
    //   196: astore 5
    //   198: aload 7
    //   200: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   203: ldc_w 777
    //   206: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   209: istore_3
    //   210: iload_3
    //   211: ifeq +43 -> 254
    //   214: aload 6
    //   216: iconst_1
    //   217: aload 4
    //   219: aload_2
    //   220: aload_1
    //   221: iconst_0
    //   222: invokestatic 117	com/p1/chompsms/util/BitmapUtil:readBitmapWithADimensionLimit	(Ljava/io/InputStream;Lcom/p1/chompsms/util/aw;Landroid/content/Context;Z)Landroid/graphics/Bitmap;
    //   225: aastore
    //   226: aload 4
    //   228: astore 5
    //   230: aload 4
    //   232: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   235: goto -170 -> 65
    //   238: astore_1
    //   239: aload 4
    //   241: astore 5
    //   243: aload 4
    //   245: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   248: aload 4
    //   250: astore 5
    //   252: aload_1
    //   253: athrow
    //   254: aload 4
    //   256: astore 5
    //   258: aload 7
    //   260: invokevirtual 143	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   263: ldc_w 782
    //   266: invokevirtual 235	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   269: istore_3
    //   270: iload_3
    //   271: ifeq -206 -> 65
    //   274: aload 6
    //   276: iconst_2
    //   277: aload 4
    //   279: aload_2
    //   280: aload_1
    //   281: iconst_0
    //   282: invokestatic 117	com/p1/chompsms/util/BitmapUtil:readBitmapWithADimensionLimit	(Ljava/io/InputStream;Lcom/p1/chompsms/util/aw;Landroid/content/Context;Z)Landroid/graphics/Bitmap;
    //   285: aastore
    //   286: aload 4
    //   288: astore 5
    //   290: aload 4
    //   292: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   295: goto -230 -> 65
    //   298: astore_1
    //   299: aload 4
    //   301: astore 5
    //   303: aload 4
    //   305: invokevirtual 1163	java/util/zip/ZipInputStream:closeEntry	()V
    //   308: aload 4
    //   310: astore 5
    //   312: aload_1
    //   313: athrow
    //   314: aload 4
    //   316: invokevirtual 1164	java/util/zip/ZipInputStream:close	()V
    //   319: aload 6
    //   321: areturn
    //   322: astore_1
    //   323: aload 6
    //   325: areturn
    //   326: aload_0
    //   327: getfield 190	com/p1/chompsms/g/e:a	Ljava/lang/String;
    //   330: ifnull -180 -> 150
    //   333: new 103	java/util/zip/ZipFile
    //   336: dup
    //   337: aload_0
    //   338: getfield 190	com/p1/chompsms/g/e:a	Ljava/lang/String;
    //   341: invokespecial 1329	java/util/zip/ZipFile:<init>	(Ljava/lang/String;)V
    //   344: astore 4
    //   346: aload 6
    //   348: iconst_0
    //   349: aload_0
    //   350: aload 4
    //   352: ldc_w 766
    //   355: aload_1
    //   356: aload_2
    //   357: invokespecial 1331	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipFile;Ljava/lang/String;Landroid/content/Context;Lcom/p1/chompsms/util/aw;)Landroid/graphics/Bitmap;
    //   360: aastore
    //   361: aload 6
    //   363: iconst_1
    //   364: aload_0
    //   365: aload 4
    //   367: ldc_w 777
    //   370: aload_1
    //   371: aload_2
    //   372: invokespecial 1331	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipFile;Ljava/lang/String;Landroid/content/Context;Lcom/p1/chompsms/util/aw;)Landroid/graphics/Bitmap;
    //   375: aastore
    //   376: aload 6
    //   378: iconst_2
    //   379: aload_0
    //   380: aload 4
    //   382: ldc_w 782
    //   385: aload_1
    //   386: aload_2
    //   387: invokespecial 1331	com/p1/chompsms/g/e:a	(Ljava/util/zip/ZipFile;Ljava/lang/String;Landroid/content/Context;Lcom/p1/chompsms/util/aw;)Landroid/graphics/Bitmap;
    //   390: aastore
    //   391: aload 4
    //   393: invokevirtual 291	java/util/zip/ZipFile:close	()V
    //   396: aload 6
    //   398: areturn
    //   399: astore_1
    //   400: aload 6
    //   402: areturn
    //   403: astore_1
    //   404: aload 4
    //   406: invokevirtual 291	java/util/zip/ZipFile:close	()V
    //   409: aload_1
    //   410: athrow
    //   411: astore_2
    //   412: goto -276 -> 136
    //   415: astore_2
    //   416: goto -7 -> 409
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	419	0	this	e
    //   0	419	1	paramContext	Context
    //   0	419	2	paramAw	aw
    //   96	175	3	bool	boolean
    //   27	378	4	localObject1	Object
    //   31	280	5	localObject2	Object
    //   4	397	6	arrayOfBitmap	Bitmap[]
    //   24	235	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   33	40	125	finally
    //   44	65	125	finally
    //   69	76	125	finally
    //   85	97	125	finally
    //   117	122	125	finally
    //   157	175	125	finally
    //   183	188	125	finally
    //   192	194	125	finally
    //   198	210	125	finally
    //   230	235	125	finally
    //   243	248	125	finally
    //   252	254	125	finally
    //   258	270	125	finally
    //   290	295	125	finally
    //   303	308	125	finally
    //   312	314	125	finally
    //   13	26	138	android/content/pm/PackageManager$NameNotFoundException
    //   131	136	138	android/content/pm/PackageManager$NameNotFoundException
    //   136	138	138	android/content/pm/PackageManager$NameNotFoundException
    //   314	319	138	android/content/pm/PackageManager$NameNotFoundException
    //   101	113	178	finally
    //   214	226	238	finally
    //   274	286	298	finally
    //   314	319	322	java/io/IOException
    //   391	396	399	java/io/IOException
    //   346	391	403	finally
    //   131	136	411	java/io/IOException
    //   404	409	415	java/io/IOException
  }
  
  public final int compareTo(Object paramObject)
  {
    paramObject = (e)paramObject;
    if (this.b == null) {
      return -1;
    }
    return this.b.compareToIgnoreCase(paramObject.b);
  }
  
  public final void d(Context paramContext)
    throws IOException
  {
    int i1 = this.m.d;
    int i2 = this.m.e;
    int i3 = this.m.f;
    int i4 = this.m.g;
    int i5 = this.m.h;
    Object localObject;
    label83:
    int i6;
    if (this.m.n)
    {
      localObject = a(paramContext, "conversation-list-portrait.png");
      if (!this.m.o) {
        break label486;
      }
      str = a(paramContext, "conversation-list-landscape.png");
      f.a(paramContext, i1, i2, i3, i4, i5, (String)localObject, str, this.m.k, this.m.l, this.m.m, this.m.a, this.m.c, this.m.i);
      i1 = this.n.e;
      i2 = this.n.f;
      i3 = this.n.h;
      i4 = this.n.i;
      i5 = this.n.k;
      i6 = this.n.l;
      if (!this.n.u) {
        break label492;
      }
      localObject = a(paramContext, "conversation-landscape.png");
      label213:
      if (!this.n.t) {
        break label498;
      }
    }
    label486:
    label492:
    label498:
    for (String str = a(paramContext, "conversation-portrait.png");; str = null)
    {
      f.a(paramContext, i1, i2, i3, i4, i5, i6, (String)localObject, str, this.n.n, this.n.o, this.n.p, this.n.m, this.n.q, this.n.r, this.n.s, this.n.g, this.n.j, this.n.a, this.n.d, this.n.b);
      f.a(paramContext, this.o.a, this.o.b, this.o.c, this.o.d, this.o.e, this.o.f, this.o.g, this.o.h, this.o.i, this.o.j, this.o.k, this.o.l, this.o.m, this.o.n, this.o.o);
      f.L(paramContext, this.b);
      localObject = com.p1.chompsms.system.a.a;
      ((com.p1.chompsms.system.a)localObject).c.post(new a.1((com.p1.chompsms.system.a)localObject));
      bl.a(paramContext);
      return;
      localObject = null;
      break;
      str = null;
      break label83;
      localObject = null;
      break label213;
    }
  }
  
  public final boolean e(Context paramContext)
  {
    boolean bool = true;
    Object localObject = this.n;
    int i1;
    if ((((b)localObject).n.a(paramContext)) && (((b)localObject).o.a(paramContext)) && (((b)localObject).p.a(paramContext)) && (((b)localObject).q.a(paramContext)))
    {
      i1 = 1;
      if (i1 == 0) {
        break label207;
      }
      localObject = this.m;
      if ((!((a)localObject).k.a(paramContext)) || (!((a)localObject).l.a(paramContext)) || (!((a)localObject).m.a(paramContext))) {
        break label197;
      }
      i1 = 1;
      label106:
      if (i1 == 0) {
        break label207;
      }
      localObject = this.o;
      if ((!d.a(((d)localObject).d, paramContext)) || (!d.a(((d)localObject).g, paramContext)) || (!d.a(((d)localObject).j, paramContext)) || (!d.a(((d)localObject).l, paramContext)) || (!d.a(((d)localObject).n, paramContext))) {
        break label202;
      }
      i1 = 1;
      label178:
      if (i1 == 0) {
        break label207;
      }
    }
    for (;;)
    {
      this.q = bool;
      return this.q;
      i1 = 0;
      break;
      label197:
      i1 = 0;
      break label106;
      label202:
      i1 = 0;
      break label178;
      label207:
      bool = false;
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (e)paramObject;
      if (this.b == null) {
        break;
      }
    } while (this.b.equals(paramObject.b));
    while (paramObject.b != null) {
      return false;
    }
    return true;
  }
  
  public final ArrayList<c> f(Context paramContext)
  {
    Object localObject = new HashSet();
    ((HashSet)localObject).add(new c(this.m.k.a.a, this.m.k.a.b));
    ((HashSet)localObject).add(new c(this.m.m.a.a, this.m.m.a.b));
    ((HashSet)localObject).add(new c(this.m.l.a.a, this.m.l.a.b));
    ((HashSet)localObject).add(new c(this.n.o.a.a, this.n.o.a.b));
    ((HashSet)localObject).add(new c(this.n.p.a.a, this.n.p.a.b));
    ((HashSet)localObject).add(new c(this.n.n.a.a, this.n.n.a.b));
    ((HashSet)localObject).add(new c(this.n.q.a.a, this.n.q.a.b));
    ((HashSet)localObject).add(new c(this.o.d.a.a, this.o.d.a.b));
    paramContext = paramContext.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    localObject = ((HashSet)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      c localC = (c)((Iterator)localObject).next();
      if (!localC.a.equals("System")) {
        try
        {
          paramContext.getPackageInfo(localC.a, 0);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localArrayList.add(localC);
        }
      }
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  public final int hashCode()
  {
    if (this.b != null) {
      return this.b.hashCode();
    }
    return 0;
  }
  
  public final String toString()
  {
    return this.c;
  }
}
