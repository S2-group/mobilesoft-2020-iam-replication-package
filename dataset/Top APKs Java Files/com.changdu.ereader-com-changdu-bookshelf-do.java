package com.changdu.bookshelf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import com.changdu.ApplicationInit;
import com.changdu.BaseActivity;
import com.changdu.OpenFileActivity;
import com.changdu.bq;
import com.changdu.browser.filebrowser.ad;
import com.changdu.e.d;
import com.changdu.e.e;
import com.changdu.e.g;
import com.changdu.e.j;
import com.changdu.e.k;
import com.changdu.home.Changdu;
import com.changdu.i;
import com.changdu.realvoice.RealVoiceActivity;
import com.changdu.setting.bn;
import com.changdu.util.z;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class do
{
  public static final String a = ".bpt";
  public static final String b = "com.android.launcher.action.INSTALL_SHORTCUT";
  public static final String c = "com.android.launcher.action.UNINSTALL_SHORTCUT";
  public static final String d = "duplicate";
  private static final float e = 1.777F;
  private static com.changdu.util.h f = new com.changdu.util.h();
  private static final String g = "  x.ndb  x.ndl  x.epub  x.umd  x.pdf";
  
  public do() {}
  
  public static int a(List<dc.a> paramList, dc.a paramA)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (((dc.a)paramList.get(i)).equals(paramA)) {
        return i;
      }
      i += 1;
    }
    return 0;
  }
  
  public static Intent a(Context paramContext, dc.a paramA, boolean paramBoolean)
  {
    if (paramA != null)
    {
      if (paramA.o == RealVoiceActivity.i)
      {
        Intent localIntent = a(paramContext, paramBoolean);
        localIntent.putExtra("android.intent.extra.shortcut.NAME", l(paramA.m));
        paramContext = changdu.android.support.v4.a.a.b(new ComponentName(paramContext.getPackageName(), RealVoiceActivity.class.getName()));
        paramContext.setAction("android.intent.action.MAIN");
        paramContext.addCategory("android.intent.category.LAUNCHER");
        paramContext.putExtra(RealVoiceActivity.b, paramA.e);
        long l2 = z.O(paramA.a);
        long l1 = l2;
        if (l2 == 0L)
        {
          l1 = System.currentTimeMillis();
          z.b(paramA.a, l1);
        }
        paramContext.putExtra("time", l1);
        localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramContext);
        return localIntent;
      }
      return a(paramContext, paramA.f(), paramBoolean);
    }
    return null;
  }
  
  @SuppressLint({"InlinedApi"})
  public static Intent a(Context paramContext, File paramFile, boolean paramBoolean)
  {
    Intent localIntent = a(paramContext, paramBoolean);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", l(paramFile.getName()));
    paramContext = changdu.android.support.v4.a.a.b(new ComponentName(paramContext.getPackageName(), OpenFileActivity.class.getName()));
    paramContext.putExtra("uri", paramFile.getAbsolutePath());
    paramContext.setAction("android.intent.action.MAIN");
    paramContext.addCategory("android.intent.category.LAUNCHER");
    long l2 = z.O(paramFile.getAbsolutePath());
    long l1 = l2;
    if (l2 == 0L)
    {
      l1 = System.currentTimeMillis();
      z.b(paramFile.getAbsolutePath(), l1);
    }
    paramContext.putExtra("time", l1);
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramContext);
    return localIntent;
  }
  
  public static Intent a(Context paramContext, boolean paramBoolean)
  {
    Intent localIntent;
    if (paramBoolean) {
      localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    } else {
      localIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    }
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramContext, 2131166145));
    return localIntent;
  }
  
  public static Bitmap a(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBitmap == null) {
      return null;
    }
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1 = paramInt1 / i;
    float f2 = paramInt2 / j;
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("bitmap width is :");
    ((StringBuilder)localObject).append(i);
    com.changdu.changdulib.e.h.c(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("bitmap height is :");
    ((StringBuilder)localObject).append(j);
    com.changdu.changdulib.e.h.c(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("new width is :");
    ((StringBuilder)localObject).append(paramInt1);
    com.changdu.changdulib.e.h.c(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("new height is :");
    ((StringBuilder)localObject).append(paramInt2);
    com.changdu.changdulib.e.h.c(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("scale width is  :");
    ((StringBuilder)localObject).append(f1);
    com.changdu.changdulib.e.h.c(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("scale height is  :");
    ((StringBuilder)localObject).append(f2);
    com.changdu.changdulib.e.h.c(((StringBuilder)localObject).toString());
    localObject = new Matrix();
    ((Matrix)localObject).postScale(f1, f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, (Matrix)localObject, true);
  }
  
  public static Bitmap a(String paramString, Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean)
  {
    paramBitmap2 = Bitmap.createBitmap(116, 162, Bitmap.Config.ARGB_4444);
    Object localObject = new Canvas(paramBitmap2);
    Paint localPaint = new Paint(1);
    ((Canvas)localObject).drawBitmap(paramBitmap1, new Rect(0, 0, paramBitmap1.getWidth(), paramBitmap1.getHeight()), new Rect(0, 0, 116, 162), localPaint);
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        paramString = new File(paramString);
        paramBitmap1 = paramString.getName();
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString.getParent());
        ((StringBuilder)localObject).append("/_");
        ((StringBuilder)localObject).append(paramBitmap1);
        a(new File(((StringBuilder)localObject).toString()), paramBitmap2);
        return paramBitmap2;
      }
      catch (IOException paramString)
      {
        com.changdu.changdulib.e.h.b(paramString);
      }
    }
    return paramBitmap2;
  }
  
  public static Bitmap a(String paramString, Drawable paramDrawable1, Drawable paramDrawable2, boolean paramBoolean)
  {
    if (paramDrawable1 != null)
    {
      paramDrawable1 = (BitmapDrawable)paramDrawable1;
      if (paramDrawable1.getBitmap() == null) {
        return null;
      }
      paramDrawable1 = paramDrawable1.getBitmap();
      if (paramDrawable1 == null) {
        return null;
      }
      return b(paramString, paramDrawable1, ((BitmapDrawable)paramDrawable2).getBitmap(), true);
    }
    return null;
  }
  
  public static File a()
  {
    File localFile = new File(com.changdu.changdulib.e.c.b.b("/covers", 20971520L));
    if (!localFile.exists()) {
      localFile.mkdirs();
    }
    return localFile;
  }
  
  public static String a(long paramLong)
  {
    if (paramLong < 1024L) {
      return String.format("%d B", new Object[] { Integer.valueOf((int)paramLong) });
    }
    if (paramLong < 1048576L) {
      return String.format("%.2f KB", new Object[] { Double.valueOf(paramLong * 1.0D / 1024L) });
    }
    if (paramLong < 1073741824L) {
      return String.format("%.2f MB", new Object[] { Double.valueOf(paramLong * 1.0D / 1048576L) });
    }
    return String.format("%.2f GB", new Object[] { Double.valueOf(paramLong * 1.0D / 1073741824L) });
  }
  
  /* Error */
  public static String a(com.changdu.bookread.pdf.PdfParser paramPdfParser, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 7
    //   3: aconst_null
    //   4: astore 8
    //   6: aconst_null
    //   7: astore 9
    //   9: aload 8
    //   11: astore 5
    //   13: aload_0
    //   14: ifnull +279 -> 293
    //   17: aload 8
    //   19: astore 5
    //   21: aload_1
    //   22: invokestatic 271	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   25: ifne +268 -> 293
    //   28: sipush 149
    //   31: istore_3
    //   32: sipush 165
    //   35: istore 4
    //   37: getstatic 348	com/changdu/ApplicationInit:h	Landroid/content/Context;
    //   40: invokevirtual 352	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   43: ldc_w 353
    //   46: invokevirtual 359	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   49: astore 10
    //   51: aload 10
    //   53: ifnull +16 -> 69
    //   56: aload 10
    //   58: invokevirtual 364	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
    //   61: istore_3
    //   62: aload 10
    //   64: invokevirtual 367	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
    //   67: istore 4
    //   69: aload_0
    //   70: invokevirtual 372	com/changdu/bookread/pdf/PdfParser:needsPassword	()Z
    //   73: ifne +120 -> 193
    //   76: aload_0
    //   77: iload_3
    //   78: iload 4
    //   80: invokevirtual 376	com/changdu/bookread/pdf/PdfParser:getCoverBitmap	(II)Landroid/graphics/Bitmap;
    //   83: astore 5
    //   85: aload 9
    //   87: astore_0
    //   88: aload 5
    //   90: astore 6
    //   92: aload 5
    //   94: ifnull +105 -> 199
    //   97: aload 10
    //   99: ifnull +72 -> 171
    //   102: aload 5
    //   104: astore 6
    //   106: new 248	android/graphics/Canvas
    //   109: dup
    //   110: aload 5
    //   112: invokespecial 251	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   115: astore_0
    //   116: aload 5
    //   118: astore 6
    //   120: aload_0
    //   121: aload 10
    //   123: invokestatic 381	com/changdu/common/k:a	(Landroid/graphics/drawable/Drawable;)Landroid/graphics/Bitmap;
    //   126: fconst_0
    //   127: fconst_0
    //   128: new 253	android/graphics/Paint
    //   131: dup
    //   132: iconst_1
    //   133: invokespecial 256	android/graphics/Paint:<init>	(I)V
    //   136: invokevirtual 384	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V
    //   139: aload 5
    //   141: astore 6
    //   143: aload 10
    //   145: aload_0
    //   146: invokevirtual 388	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   149: aload 5
    //   151: astore 6
    //   153: aload_0
    //   154: bipush 31
    //   156: invokevirtual 392	android/graphics/Canvas:save	(I)I
    //   159: pop
    //   160: aload 5
    //   162: astore 6
    //   164: aload_0
    //   165: invokevirtual 395	android/graphics/Canvas:restore	()V
    //   168: goto +3 -> 171
    //   171: aload 5
    //   173: astore 6
    //   175: aload 5
    //   177: aload_1
    //   178: invokestatic 397	com/changdu/bookshelf/do:b	(Ljava/lang/String;)Ljava/lang/String;
    //   181: invokestatic 400	com/changdu/bookshelf/do:a	(Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   184: aload_2
    //   185: astore_0
    //   186: aload 5
    //   188: astore 6
    //   190: goto +9 -> 199
    //   193: aconst_null
    //   194: astore 6
    //   196: aload 9
    //   198: astore_0
    //   199: aload_0
    //   200: astore 5
    //   202: aload 6
    //   204: ifnull +89 -> 293
    //   207: aload_0
    //   208: astore 5
    //   210: aload 6
    //   212: invokevirtual 403	android/graphics/Bitmap:isRecycled	()Z
    //   215: ifne +78 -> 293
    //   218: aload_0
    //   219: astore_1
    //   220: aload 6
    //   222: invokevirtual 406	android/graphics/Bitmap:recycle	()V
    //   225: aload_1
    //   226: areturn
    //   227: astore_0
    //   228: aconst_null
    //   229: astore 6
    //   231: goto +42 -> 273
    //   234: astore_1
    //   235: aconst_null
    //   236: astore_0
    //   237: aload_0
    //   238: astore 6
    //   240: aload_1
    //   241: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   244: aload 8
    //   246: astore 5
    //   248: aload_0
    //   249: ifnull +44 -> 293
    //   252: aload 8
    //   254: astore 5
    //   256: aload_0
    //   257: invokevirtual 403	android/graphics/Bitmap:isRecycled	()Z
    //   260: ifne +33 -> 293
    //   263: aload 7
    //   265: astore_1
    //   266: aload_0
    //   267: astore 6
    //   269: goto -49 -> 220
    //   272: astore_0
    //   273: aload 6
    //   275: ifnull +16 -> 291
    //   278: aload 6
    //   280: invokevirtual 403	android/graphics/Bitmap:isRecycled	()Z
    //   283: ifne +8 -> 291
    //   286: aload 6
    //   288: invokevirtual 406	android/graphics/Bitmap:recycle	()V
    //   291: aload_0
    //   292: athrow
    //   293: aload 5
    //   295: areturn
    //   296: astore_1
    //   297: aload 5
    //   299: astore_0
    //   300: goto -63 -> 237
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	303	0	paramPdfParser	com.changdu.bookread.pdf.PdfParser
    //   0	303	1	paramString1	String
    //   0	303	2	paramString2	String
    //   31	47	3	i	int
    //   35	44	4	j	int
    //   11	287	5	localObject1	Object
    //   90	197	6	localObject2	Object
    //   1	263	7	localObject3	Object
    //   4	249	8	localObject4	Object
    //   7	190	9	localObject5	Object
    //   49	95	10	localDrawable	Drawable
    // Exception table:
    //   from	to	target	type
    //   69	85	227	finally
    //   69	85	234	java/lang/Exception
    //   106	116	272	finally
    //   120	139	272	finally
    //   143	149	272	finally
    //   153	160	272	finally
    //   164	168	272	finally
    //   175	184	272	finally
    //   240	244	272	finally
    //   106	116	296	java/lang/Exception
    //   120	139	296	java/lang/Exception
    //   143	149	296	java/lang/Exception
    //   153	160	296	java/lang/Exception
    //   164	168	296	java/lang/Exception
    //   175	184	296	java/lang/Exception
  }
  
  public static String a(File paramFile, Context paramContext)
  {
    String str = paramContext.getString(2131558804);
    if (paramFile != null)
    {
      if (paramFile.isDirectory()) {
        return paramContext.getString(2131559072);
      }
      if (z.a(paramFile)) {
        return paramContext.getString(2131559068);
      }
      str = paramContext.getString(2131559070);
    }
    return str;
  }
  
  public static final String a(String paramString)
  {
    return z.d(paramString);
  }
  
  /* Error */
  public static String a(String paramString, k paramK)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +215 -> 216
    //   4: iconst_0
    //   5: istore_3
    //   6: iload_3
    //   7: istore_2
    //   8: aload_0
    //   9: astore 5
    //   11: aload_0
    //   12: invokestatic 431	com/changdu/changdulib/e/k:a	(Ljava/lang/String;)Z
    //   15: ifne +46 -> 61
    //   18: iload_3
    //   19: istore_2
    //   20: aload_0
    //   21: astore 5
    //   23: aload_0
    //   24: invokevirtual 434	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   27: ldc_w 436
    //   30: invokevirtual 439	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   33: ifeq +28 -> 61
    //   36: aload_0
    //   37: invokestatic 444	com/changdu/bookread/a/a:f	(Ljava/lang/String;)Lcom/changdu/bookread/a/c;
    //   40: astore 4
    //   42: iload_3
    //   43: istore_2
    //   44: aload_0
    //   45: astore 5
    //   47: aload 4
    //   49: ifnull +12 -> 61
    //   52: aload 4
    //   54: invokevirtual 448	com/changdu/bookread/a/c:c	()Ljava/lang/String;
    //   57: astore 5
    //   59: iconst_1
    //   60: istore_2
    //   61: aconst_null
    //   62: astore 4
    //   64: aconst_null
    //   65: astore_0
    //   66: iload_2
    //   67: ifeq +13 -> 80
    //   70: aload_1
    //   71: aload 5
    //   73: invokevirtual 454	com/changdu/e/k:k	(Ljava/lang/String;)Landroid/database/Cursor;
    //   76: astore_1
    //   77: goto +10 -> 87
    //   80: aload_1
    //   81: aload 5
    //   83: invokevirtual 457	com/changdu/e/k:j	(Ljava/lang/String;)Landroid/database/Cursor;
    //   86: astore_1
    //   87: aload_1
    //   88: ifnull +65 -> 153
    //   91: aload_1
    //   92: astore_0
    //   93: aload_1
    //   94: astore 4
    //   96: aload_1
    //   97: invokeinterface 462 1 0
    //   102: ifle +51 -> 153
    //   105: aload_1
    //   106: astore_0
    //   107: aload_1
    //   108: astore 4
    //   110: aload_1
    //   111: invokeinterface 465 1 0
    //   116: pop
    //   117: aload_1
    //   118: astore_0
    //   119: aload_1
    //   120: astore 4
    //   122: aload_1
    //   123: bipush 13
    //   125: invokeinterface 466 2 0
    //   130: astore 5
    //   132: aload_1
    //   133: ifnull +17 -> 150
    //   136: aload_1
    //   137: invokeinterface 469 1 0
    //   142: aload 5
    //   144: areturn
    //   145: astore_0
    //   146: aload_0
    //   147: invokestatic 471	com/changdu/changdulib/e/h:a	(Ljava/lang/Object;)V
    //   150: aload 5
    //   152: areturn
    //   153: aload_1
    //   154: ifnull +62 -> 216
    //   157: aload_1
    //   158: invokeinterface 469 1 0
    //   163: goto +53 -> 216
    //   166: astore_0
    //   167: aload_0
    //   168: invokestatic 471	com/changdu/changdulib/e/h:a	(Ljava/lang/Object;)V
    //   171: goto +45 -> 216
    //   174: aload 4
    //   176: astore_0
    //   177: aload_1
    //   178: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   181: aload 4
    //   183: ifnull +33 -> 216
    //   186: aload 4
    //   188: invokeinterface 469 1 0
    //   193: goto +23 -> 216
    //   196: aload_0
    //   197: ifnull +17 -> 214
    //   200: aload_0
    //   201: invokeinterface 469 1 0
    //   206: goto +8 -> 214
    //   209: astore_0
    //   210: aload_0
    //   211: invokestatic 471	com/changdu/changdulib/e/h:a	(Ljava/lang/Object;)V
    //   214: aload_1
    //   215: athrow
    //   216: ldc_w 473
    //   219: areturn
    //   220: astore_1
    //   221: goto -25 -> 196
    //   224: astore_1
    //   225: goto -51 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	paramString	String
    //   0	228	1	paramK	k
    //   7	60	2	i	int
    //   5	38	3	j	int
    //   40	147	4	localObject	Object
    //   9	142	5	str	String
    // Exception table:
    //   from	to	target	type
    //   136	142	145	java/lang/Exception
    //   157	163	166	java/lang/Exception
    //   186	193	166	java/lang/Exception
    //   200	206	209	java/lang/Exception
    //   70	77	220	finally
    //   80	87	220	finally
    //   96	105	220	finally
    //   110	117	220	finally
    //   122	132	220	finally
    //   177	181	220	finally
    //   70	77	224	java/lang/Exception
    //   80	87	224	java/lang/Exception
    //   96	105	224	java/lang/Exception
    //   110	117	224	java/lang/Exception
    //   122	132	224	java/lang/Exception
  }
  
  public static String a(String paramString, File paramFile)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(paramString.hashCode());
    localStringBuilder.append(paramFile.length());
    paramString = localStringBuilder.toString();
    paramFile = new StringBuilder();
    paramFile.append(a().getAbsolutePath());
    paramFile.append("/_");
    paramFile.append(paramString);
    paramFile.append(".bpt");
    return paramFile.toString();
  }
  
  /* Error */
  public static String a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: iconst_1
    //   1: getstatic 493	com/changdu/netprotocol/NdPlugInData$PlugInInfo:PLUGIN_PDF	Ljava/lang/String;
    //   4: invokestatic 498	com/changdu/plugin/i:a	(ILjava/lang/String;)I
    //   7: istore_2
    //   8: iload_2
    //   9: iconst_2
    //   10: if_icmpeq +8 -> 18
    //   13: iload_2
    //   14: iconst_3
    //   15: if_icmpne +66 -> 81
    //   18: aload_0
    //   19: invokestatic 502	com/changdu/bookread/pdf/PdfParser:createPdfParser	(Ljava/lang/String;)Lcom/changdu/bookread/pdf/PdfParser;
    //   22: astore 4
    //   24: aload 4
    //   26: astore_3
    //   27: aload 4
    //   29: aload_0
    //   30: aload_1
    //   31: invokestatic 504	com/changdu/bookshelf/do:a	(Lcom/changdu/bookread/pdf/PdfParser;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   34: astore_0
    //   35: aload 4
    //   37: ifnull +8 -> 45
    //   40: aload 4
    //   42: invokevirtual 507	com/changdu/bookread/pdf/PdfParser:onDestroy	()V
    //   45: aload_0
    //   46: areturn
    //   47: astore_0
    //   48: goto +35 -> 83
    //   51: astore_1
    //   52: aload 4
    //   54: astore_0
    //   55: goto +12 -> 67
    //   58: astore_0
    //   59: aconst_null
    //   60: astore_3
    //   61: goto +22 -> 83
    //   64: astore_1
    //   65: aconst_null
    //   66: astore_0
    //   67: aload_0
    //   68: astore_3
    //   69: aload_1
    //   70: invokestatic 282	com/changdu/changdulib/e/h:b	(Ljava/lang/Object;)V
    //   73: aload_0
    //   74: ifnull +7 -> 81
    //   77: aload_0
    //   78: invokevirtual 507	com/changdu/bookread/pdf/PdfParser:onDestroy	()V
    //   81: aconst_null
    //   82: areturn
    //   83: aload_3
    //   84: ifnull +7 -> 91
    //   87: aload_3
    //   88: invokevirtual 507	com/changdu/bookread/pdf/PdfParser:onDestroy	()V
    //   91: aload_0
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	paramString1	String
    //   0	93	1	paramString2	String
    //   7	9	2	i	int
    //   26	62	3	localObject	Object
    //   22	31	4	localPdfParser	com.changdu.bookread.pdf.PdfParser
    // Exception table:
    //   from	to	target	type
    //   27	35	47	finally
    //   69	73	47	finally
    //   27	35	51	java/lang/Throwable
    //   18	24	58	finally
    //   18	24	64	java/lang/Throwable
  }
  
  public static String a(String paramString, String[] paramArrayOfString)
  {
    String str = com.changdu.changdulib.e.c.b.e("/covers");
    if (str == null) {
      return "";
    }
    File localFile = new File(str);
    if ((!localFile.isFile()) && (localFile.exists()))
    {
      String[] arrayOfString = paramArrayOfString;
      if (paramArrayOfString == null) {
        arrayOfString = localFile.list();
      }
      int i = 0;
      while (i < arrayOfString.length)
      {
        if ((arrayOfString[i].endsWith("bpt")) && (arrayOfString[i].indexOf(paramString) != -1))
        {
          paramString = new StringBuilder();
          paramString.append(str);
          paramString.append("/");
          paramString.append(arrayOfString[i]);
          return paramString.toString();
        }
        i += 1;
      }
      return "";
    }
    return "";
  }
  
  public static ArrayList<dc.a> a(dc.a paramA)
  {
    return dc.m(paramA.g);
  }
  
  public static ArrayList<dc.a> a(ArrayList<dc.a> paramArrayList, String paramString)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    while (i < paramArrayList.size())
    {
      localObject = (dc.a)paramArrayList.get(i);
      if (((dc.a)localObject).d()) {
        localArrayList2.add(localObject);
      } else {
        localArrayList1.add(localObject);
      }
      i += 1;
    }
    Object localObject = new ArrayList();
    if (i.a().f()) {
      localObject = c(localArrayList1);
    }
    a(paramString.replace(BookShelfActivity.b, com.changdu.changdulib.e.c.b.f()), localArrayList1, localArrayList2);
    paramString = localArrayList1;
    if (i.a().f()) {
      paramString = b(localArrayList1, (ArrayList)localObject);
    }
    if (paramArrayList != null) {
      paramArrayList.clear();
    }
    return a(localArrayList2, paramString);
  }
  
  public static ArrayList<dc.a> a(ArrayList<dc.a> paramArrayList1, ArrayList<dc.a> paramArrayList2)
  {
    if ((paramArrayList2 != null) && (!paramArrayList2.isEmpty()))
    {
      if ((paramArrayList1 != null) && (!paramArrayList1.isEmpty()))
      {
        if (bn.V().u() == 1)
        {
          int k = paramArrayList1.size();
          int m = paramArrayList2.size();
          ArrayList localArrayList = new ArrayList(k + m);
          int j = 0;
          int i = 0;
          for (;;)
          {
            if ((j >= k) && (i >= m)) {
              return localArrayList;
            }
            dc.a localA2 = null;
            dc.a localA1;
            if (j < k) {
              localA1 = (dc.a)paramArrayList1.get(j);
            } else {
              localA1 = null;
            }
            if (i < m) {
              localA2 = (dc.a)paramArrayList2.get(i);
            }
            if ((localA1 != null) && ((localA2 == null) || (localA1.i > localA2.i)))
            {
              localArrayList.add(localA1);
              j += 1;
            }
            else if (localA2 != null)
            {
              localArrayList.add(localA2);
              i += 1;
            }
          }
        }
        paramArrayList1.addAll(paramArrayList2);
        return paramArrayList1;
      }
      return paramArrayList2;
    }
    return paramArrayList1;
  }
  
  public static ArrayList<dc.a> a(List<dc.a> paramList, String paramString)
  {
    paramString = dc.n(paramString);
    if ((paramString != null) && (paramString.size() != 0))
    {
      paramList = paramList.iterator();
      label90:
      while (paramList.hasNext())
      {
        dc.a localA = (dc.a)paramList.next();
        int i = 0;
        for (;;)
        {
          if (i >= paramString.size()) {
            break label90;
          }
          if (localA.a.equals(((dc.a)paramString.get(i)).a))
          {
            paramString.remove(i);
            break;
          }
          i += 1;
        }
      }
      return paramString;
    }
    return new ArrayList(0);
  }
  
  public static void a(Activity paramActivity, File paramFile)
  {
    String str = paramFile.getAbsolutePath();
    paramFile = com.changdu.bookread.a.a.f(paramFile.getAbsolutePath());
    int i;
    if ((!TextUtils.isEmpty(paramFile.d())) && (TextUtils.isDigitsOnly(paramFile.d()))) {
      i = Integer.parseInt(paramFile.d());
    } else {
      i = 5;
    }
    paramFile = z.a(i, paramFile.c());
    if (!TextUtils.isEmpty(paramFile)) {
      if (!TextUtils.isEmpty(str))
      {
        paramActivity = paramActivity.getParent();
        if ((paramActivity != null) && ((paramActivity instanceof Changdu))) {
          ((Changdu)paramActivity).a(paramFile, true);
        }
      }
      else
      {
        z.d(paramActivity, paramFile);
        new du().sendEmptyMessageDelayed(0, 300L);
      }
    }
  }
  
  public static void a(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    Intent localIntent1 = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
    localIntent1.putExtra("duplicate", false);
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramContext.getString(2131558480));
    Intent localIntent2 = new Intent(paramContext, Changdu.class);
    localIntent2.setAction("android.intent.action.MAIN");
    localIntent2.addCategory("android.intent.category.LAUNCHER");
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    paramContext.sendBroadcast(localIntent1);
  }
  
  public static void a(Context paramContext, dc.a paramA)
  {
    com.changdu.util.f.a.a(paramContext, paramA);
    ApplicationInit.r.postDelayed(new ds(paramContext, paramA), 500L);
  }
  
  public static void a(Context paramContext, File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()))
    {
      com.changdu.util.f.a.a(paramContext, paramFile);
      ApplicationInit.r.postDelayed(new dr(paramContext, paramFile), 500L);
      return;
    }
    Toast.makeText(paramContext, 2131558956, 0).show();
  }
  
  public static void a(Bitmap paramBitmap, String paramString)
    throws IOException
  {
    Object localObject = new File(e(paramString));
    if (((File)localObject).exists()) {
      ((File)localObject).delete();
    }
    ((File)localObject).createNewFile();
    paramString = null;
    try
    {
      localObject = new FileOutputStream((File)localObject);
      paramString = (String)localObject;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramString);
    try
    {
      paramString.flush();
    }
    catch (IOException paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
    if (paramString != null) {
      try
      {
        paramString.close();
        return;
      }
      catch (IOException paramBitmap)
      {
        paramBitmap.printStackTrace();
      }
    }
  }
  
  public static void a(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
  {
    if ((paramOptions.outWidth <= paramInt1) && (paramOptions.outHeight <= paramInt2)) {
      return;
    }
    paramOptions.inSampleSize = Math.max(paramOptions.outWidth / paramInt1, paramOptions.outHeight / paramInt2);
    float f1 = (float)Math.floor(paramOptions.outWidth * 1.0F / paramOptions.inSampleSize);
    float f2 = (float)Math.floor(paramOptions.outHeight * 1.0F / paramOptions.inSampleSize);
    paramOptions.outWidth = ((int)f1);
    paramOptions.outHeight = ((int)f2);
    paramOptions.inJustDecodeBounds = false;
  }
  
  public static void a(dc.a paramA, TextView paramTextView, int paramInt)
  {
    a(paramA, paramTextView, paramInt, false);
  }
  
  public static void a(dc.a paramA, TextView paramTextView, int paramInt, boolean paramBoolean)
  {
    b(paramA, paramTextView, paramInt);
  }
  
  public static void a(dc.a paramA, String paramString, e paramE)
  {
    if (paramA.c())
    {
      paramA.g = paramString;
      paramE.d(paramA.a, paramString);
      return;
    }
    String str1 = paramA.j();
    String str2 = paramA.a;
    paramA.m = b(paramString, paramA.m, paramE);
    paramA.g = paramString;
    if (!paramA.h())
    {
      paramString = new StringBuilder();
      paramString.append("/");
      paramString.append(paramA.g);
      paramString.append("/");
      paramString.append(paramA.m);
      paramA.a = paramString.toString();
    }
    a(paramA, str1, str2);
  }
  
  public static void a(dc.a paramA, String paramString1, String paramString2)
  {
    dc.a(paramA, paramString1, paramString2);
  }
  
  public static void a(dc.a paramA, String paramString1, String paramString2, List<Object> paramList)
  {
    if (paramList != null) {
      new dq(paramString1, paramString2, paramA, paramList).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[0]);
    }
  }
  
  public static void a(File paramFile, Bitmap paramBitmap)
    throws IOException
  {
    if ((paramFile != null) && (!paramFile.exists()) && (paramBitmap != null))
    {
      paramFile.createNewFile();
      Object localObject = null;
      try
      {
        paramFile = new FileOutputStream(paramFile);
      }
      catch (FileNotFoundException paramFile)
      {
        paramFile.printStackTrace();
        paramFile = localObject;
      }
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, paramFile);
      if (paramFile != null) {
        try
        {
          paramFile.flush();
          paramFile.close();
          return;
        }
        catch (IOException paramFile)
        {
          com.changdu.changdulib.e.h.e(paramFile);
        }
      }
    }
  }
  
  public static void a(File paramFile, da paramDa, ArrayList<File> paramArrayList)
  {
    if (paramFile == null) {
      return;
    }
    e localE = com.changdu.e.h.b();
    paramFile = paramFile.listFiles();
    if ((paramFile != null) && (paramFile.length > 0))
    {
      Object localObject = da.a.a;
      int i = 0;
      while (i < paramFile.length)
      {
        if ((paramDa.a(paramFile[i]) == da.a.b) && (!localE.f(paramFile[i].getAbsolutePath()))) {
          if (paramFile[i].isFile())
          {
            paramArrayList.add(paramFile[i]);
          }
          else if (paramFile[i].isDirectory())
          {
            localObject = paramFile[i].listFiles();
            if ((localObject != null) && (localObject.length != 0))
            {
              paramArrayList.add(paramFile[i]);
              a(paramFile[i], paramDa, paramArrayList);
            }
          }
        }
        i += 1;
      }
    }
  }
  
  public static void a(String paramString, int paramInt, e paramE)
  {
    if (paramE != null) {
      paramE.b(paramString, paramInt);
    }
  }
  
  public static void a(String paramString1, String paramString2, e paramE)
  {
    if (paramE != null) {
      paramE.a(paramString1, paramString2);
    }
  }
  
  /* Error */
  public static void a(String paramString, ArrayList<dc.a> paramArrayList1, ArrayList<dc.a> paramArrayList2)
  {
    // Byte code:
    //   0: invokestatic 584	com/changdu/setting/bn:V	()Lcom/changdu/setting/bn;
    //   3: invokevirtual 839	com/changdu/setting/bn:t	()I
    //   6: istore_3
    //   7: aconst_null
    //   8: astore 5
    //   10: aconst_null
    //   11: astore 4
    //   13: aload_0
    //   14: iload_3
    //   15: invokestatic 844	com/changdu/browser/filebrowser/ad:a	(Ljava/lang/String;I)Lcom/changdu/browser/a/e;
    //   18: astore_0
    //   19: aload_1
    //   20: ifnull +18 -> 38
    //   23: aload_1
    //   24: invokevirtual 578	java/util/ArrayList:isEmpty	()Z
    //   27: ifne +11 -> 38
    //   30: aload_1
    //   31: aload_0
    //   32: invokestatic 850	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   35: goto +3 -> 38
    //   38: aload_2
    //   39: ifnull +15 -> 54
    //   42: aload_2
    //   43: invokevirtual 578	java/util/ArrayList:isEmpty	()Z
    //   46: ifne +8 -> 54
    //   49: aload_2
    //   50: aload_0
    //   51: invokestatic 850	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   54: aload_0
    //   55: ifnull +38 -> 93
    //   58: aload_0
    //   59: invokeinterface 854 1 0
    //   64: return
    //   65: astore_1
    //   66: aload 4
    //   68: astore_0
    //   69: goto +25 -> 94
    //   72: astore_1
    //   73: aload 5
    //   75: astore_0
    //   76: aload_0
    //   77: astore 4
    //   79: aload_1
    //   80: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   83: aload_0
    //   84: ifnull +9 -> 93
    //   87: aload_0
    //   88: invokeinterface 854 1 0
    //   93: return
    //   94: aload_0
    //   95: ifnull +9 -> 104
    //   98: aload_0
    //   99: invokeinterface 854 1 0
    //   104: aload_1
    //   105: athrow
    //   106: astore_1
    //   107: goto -13 -> 94
    //   110: astore_1
    //   111: goto -35 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	paramString	String
    //   0	114	1	paramArrayList1	ArrayList<dc.a>
    //   0	114	2	paramArrayList2	ArrayList<dc.a>
    //   6	9	3	i	int
    //   11	67	4	str	String
    //   8	66	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   13	19	65	finally
    //   79	83	65	finally
    //   13	19	72	java/lang/Exception
    //   23	35	106	finally
    //   42	54	106	finally
    //   23	35	110	java/lang/Exception
    //   42	54	110	java/lang/Exception
  }
  
  public static void a(ArrayList<dc.a> paramArrayList)
  {
    e localE = com.changdu.e.h.b();
    int i = 0;
    while (i < paramArrayList.size())
    {
      dc.a localA = (dc.a)paramArrayList.get(i);
      if (localA != null)
      {
        String str = localA.m;
        if ((localA.d()) && (BookShelfActivity.b.equals(localA.g)))
        {
          Object localObject = b(BookShelfActivity.b, localA.m, localE);
          if (!str.equals(localObject))
          {
            localA.m = ((String)localObject);
            paramArrayList.set(i, localA);
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(BookShelfActivity.b);
            ((StringBuilder)localObject).append("/");
            ((StringBuilder)localObject).append(str);
            a(paramArrayList, ((StringBuilder)localObject).toString(), localA.j());
          }
        }
      }
      i += 1;
    }
  }
  
  public static void a(ArrayList<dc.a> paramArrayList, String paramString1, String paramString2)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      dc.a localA = (dc.a)paramArrayList.get(i);
      if (paramString1.equals(localA.g)) {
        if (localA.c())
        {
          localA.g = paramString2;
          paramArrayList.set(i, localA);
        }
        else
        {
          String str = localA.j();
          localA.g = paramString2;
          paramArrayList.set(i, localA);
          a(paramArrayList, str, localA.j());
        }
      }
      i += 1;
    }
  }
  
  public static void a(List<File> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      Collections.sort(paramList, new dp());
    }
  }
  
  public static void a(List<dc.a> paramList, int paramInt)
  {
    if (paramList.size() > paramInt)
    {
      File localFile = ((dc.a)paramList.get(paramInt)).f();
      if ((localFile != null) && (localFile.exists())) {
        a(localFile);
      }
      paramList.remove(paramInt);
    }
  }
  
  public static void a(List<dc.a> paramList, int paramInt, String paramString, e paramE)
  {
    if (paramList.size() > paramInt)
    {
      paramList = (dc.a)paramList.get(paramInt);
      if (paramList.c())
      {
        paramE.d(paramList.a, paramString);
        return;
      }
      String str1 = paramList.j();
      String str2 = paramList.a;
      paramList.m = b(paramString, paramList.m, paramE);
      paramList.g = paramString;
      if (!paramList.f().exists())
      {
        paramString = new StringBuilder();
        paramString.append("/");
        paramString.append(paramList.g);
        paramString.append("/");
        paramString.append(paramList.m);
        paramList.a = paramString.toString();
      }
      a(paramList, str1, str2);
    }
  }
  
  public static void a(List<File> paramList, ad paramAd, Context paramContext)
  {
    File[] arrayOfFile = new File(com.changdu.changdulib.e.c.b.f()).listFiles();
    if ((arrayOfFile != null) && (arrayOfFile.length > 0))
    {
      paramContext = new da(paramContext.getResources().getStringArray(2130837506), paramContext.getResources().getStringArray(2130837507), paramContext.getResources().getStringArray(2130837545));
      Object localObject = da.a.a;
      int i = 0;
      while (i < arrayOfFile.length)
      {
        localObject = paramContext.a(arrayOfFile[i]);
        if (localObject == da.a.b)
        {
          paramList.add(arrayOfFile[i]);
        }
        else if (localObject == da.a.c)
        {
          localObject = paramAd.a(arrayOfFile[i], ".", true);
          int j = 0;
          while (j < localObject.length)
          {
            paramList.add(localObject[j]);
            j += 1;
          }
        }
        i += 1;
      }
    }
  }
  
  @Deprecated
  public static void a(List<File> paramList1, List<File> paramList2, List<File> paramList3, Comparator<Object> paramComparator)
  {
    int j = 0;
    int i;
    if ((paramList2 != null) && (!paramList2.isEmpty()))
    {
      File[] arrayOfFile = new File[paramList2.size()];
      paramList2.toArray(arrayOfFile);
      Arrays.sort(arrayOfFile, paramComparator);
      i = 0;
      while (i < arrayOfFile.length)
      {
        paramList3.add(i, arrayOfFile[i]);
        i += 1;
      }
    }
    if ((paramList1 != null) && (!paramList1.isEmpty()))
    {
      paramList2 = new File[paramList1.size()];
      paramList1.toArray(paramList2);
      Arrays.sort(paramList2, paramComparator);
      i = j;
      while (i < paramList2.length)
      {
        paramList3.add(i, paramList2[i]);
        i += 1;
      }
    }
  }
  
  public static boolean a(int paramInt1, int paramInt2, float paramFloat)
  {
    return ((paramInt1 >= 720) && (paramInt2 >= 1184) && (paramFloat <= 2.0F)) || ((paramInt1 == 600) && (paramInt2 >= 964) && (paramFloat == 1.0F));
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    if (Build.VERSION.SDK_INT < 8) {
      localObject = "content://com.android.launcher.settings/favorites?notify=true";
    } else if (Build.VERSION.SDK_INT < 19) {
      localObject = "content://com.android.launcher2.settings/favorites?notify=true";
    } else {
      localObject = "content://com.android.launcher3.settings/favorites?notify=true";
    }
    Object localObject = Uri.parse((String)localObject);
    boolean bool2 = true;
    localObject = localContentResolver.query((Uri)localObject, null, "title=?", new String[] { paramString }, null);
    boolean bool1;
    if ((localObject != null) && (((Cursor)localObject).getCount() > 0))
    {
      if (!((Cursor)localObject).isClosed()) {
        ((Cursor)localObject).close();
      }
      bool1 = true;
    }
    else
    {
      bool1 = false;
    }
    if (localObject == null)
    {
      localObject = paramContext.getPackageManager().getInstalledPackages(8);
      if (localObject == null)
      {
        paramString = new Intent("android.intent.action.MAIN");
        paramString.addCategory("android.intent.category.HOME");
        paramContext = paramContext.getPackageManager().resolveActivity(paramString, 0);
        if (paramContext != null) {
          paramString = paramContext.activityInfo;
        }
        paramContext.activityInfo.packageName.equals("android");
      }
      else
      {
        paramContext = null;
        Iterator localIterator = ((List)localObject).iterator();
        do
        {
          ProviderInfo[] arrayOfProviderInfo;
          do
          {
            localObject = paramContext;
            if (!localIterator.hasNext()) {
              break;
            }
            arrayOfProviderInfo = ((PackageInfo)localIterator.next()).providers;
          } while (arrayOfProviderInfo == null);
          int j = arrayOfProviderInfo.length;
          int i = 0;
          for (;;)
          {
            localObject = paramContext;
            if (i >= j) {
              break;
            }
            localObject = arrayOfProviderInfo[i];
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("=======provider.readPermission=");
            localStringBuilder.append(((ProviderInfo)localObject).readPermission);
            localStringBuilder.append("=========provider.authority=");
            localStringBuilder.append(((ProviderInfo)localObject).authority);
            com.changdu.changdulib.e.h.e(localStringBuilder.toString());
            if ("com.android.launcher.permission.READ_SETTINGS".equals(((ProviderInfo)localObject).readPermission))
            {
              localObject = ((ProviderInfo)localObject).authority;
              break;
            }
            i += 1;
          }
          paramContext = (Context)localObject;
        } while (TextUtils.isEmpty((CharSequence)localObject));
        if (!TextUtils.isEmpty((CharSequence)localObject))
        {
          paramContext = new StringBuilder();
          paramContext.append("content://");
          paramContext.append((String)localObject);
          paramContext.append("/favorites?notify=true");
          paramContext = localContentResolver.query(Uri.parse(paramContext.toString()), null, "title=?", new String[] { paramString }, null);
          if ((paramContext != null) && (paramContext.getCount() > 0))
          {
            bool1 = bool2;
            if (!paramContext.isClosed())
            {
              paramContext.close();
              return true;
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public static boolean a(dc.a paramA, com.changdu.e.c paramC, k paramK, boolean paramBoolean)
  {
    File localFile = paramA.f();
    if (localFile == null) {
      return false;
    }
    if (paramA.o == RealVoiceActivity.i) {
      paramK.F(paramA.e);
    }
    if (!localFile.exists()) {
      dc.b(localFile.getAbsolutePath(), true);
    } else if (paramA.h()) {
      dc.b(localFile.getAbsolutePath(), false);
    } else {
      dc.b(localFile.getAbsolutePath(), paramBoolean);
    }
    Object localObject3;
    if (localFile.isFile())
    {
      paramA = localFile.getAbsolutePath();
      c(paramA);
      if (paramA.toLowerCase().endsWith(".ndb"))
      {
        Object localObject1 = d(localFile.getName());
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("/temp/");
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append(".txt");
        localObject1 = com.changdu.changdulib.e.c.b.d(((StringBuilder)localObject3).toString());
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject1 = new File((String)localObject1);
          if ((((File)localObject1).isFile()) && (((File)localObject1).exists())) {
            ((File)localObject1).delete();
          }
        }
      }
      b(ApplicationInit.h, localFile);
      if (!localFile.getName().toLowerCase().endsWith(".ndl")) {
        try
        {
          paramC.g(localFile.getAbsolutePath());
          paramK.a(localFile.getAbsolutePath());
          com.changdu.e.h.d().c(localFile.getAbsolutePath());
          paramK.e(localFile.getAbsolutePath());
        }
        catch (Exception localException1)
        {
          com.changdu.changdulib.e.h.e(localException1);
        }
      }
      com.changdu.bookread.text.c.b.b(paramA);
    }
    label904:
    label910:
    label913:
    label945:
    for (;;)
    {
      try
      {
        if (localFile.getName().toLowerCase().endsWith(".ndl"))
        {
          localObject3 = com.changdu.bookread.a.a.f(localFile.getAbsolutePath());
          if (localObject3 != null)
          {
            String str1 = ((com.changdu.bookread.a.c)localObject3).c();
            i = -1;
            String str2 = ((com.changdu.bookread.a.c)localObject3).f();
            if ((!TextUtils.isEmpty(str1)) && (!"0".equals(str1))) {
              com.changdu.bookread.text.c.b.b(str1);
            }
            boolean bool = str2.startsWith("readwebbook");
            Object localObject2 = null;
            if (bool)
            {
              i = str2.indexOf('(');
              paramA = localObject2;
              if (i > 0)
              {
                int j = str2.lastIndexOf(')');
                paramA = localObject2;
                if (j > i) {
                  paramA = str2.substring(i + 1, j).split(",");
                }
              }
              if (paramA != null)
              {
                i = paramA.length;
                if (i == 2)
                {
                  try
                  {
                    paramK.a(paramA[0], paramA[1]);
                    paramK.b(paramA[0], paramA[1]);
                    com.changdu.e.h.g().a(paramA[0], paramA[1]);
                  }
                  catch (Exception paramK)
                  {
                    com.changdu.changdulib.e.h.e(paramK);
                  }
                  try
                  {
                    paramC.a(localFile.getAbsolutePath());
                    paramC.e(paramA[0], paramA[1]);
                  }
                  catch (Exception paramA)
                  {
                    com.changdu.changdulib.e.h.e(paramA);
                  }
                }
              }
            }
            else
            {
              if ((str2.contains("ndaction:readonline")) || (str2.contains("ndaction:listenonline"))) {
                break label910;
              }
              if (str2.contains("ndaction:readcomic"))
              {
                i = 1;
                break label913;
              }
              if (str2.contains("restype=8")) {
                break label904;
              }
              if (!str2.contains("ndaction:listenbook")) {
                break label913;
              }
              break label904;
              paramA = new StringBuilder();
              paramA.append("download/eBookOnLine/");
              paramA.append(str1);
              paramA = com.changdu.changdulib.e.c.b.a(paramA.toString(), 0L);
              break label945;
              paramA = new StringBuilder();
              paramA.append("download/cartoonOnLine/");
              paramA.append(str1);
              paramA = com.changdu.changdulib.e.c.b.a(paramA.toString(), 0L);
              break label945;
              paramA = new StringBuilder();
              paramA.append("download/");
              paramA.append(((com.changdu.bookread.a.c)localObject3).a());
              paramA = com.changdu.changdulib.e.c.b.a(paramA.toString(), 0L);
              break label945;
              try
              {
                paramK.a(i, str1);
                paramK.a(null, ((com.changdu.bookread.a.c)localObject3).c(), ((com.changdu.bookread.a.c)localObject3).f(), 0, null);
              }
              catch (Exception localException2)
              {
                com.changdu.changdulib.e.h.e(localException2);
              }
              try
              {
                paramC.a(null, ((com.changdu.bookread.a.c)localObject3).c());
              }
              catch (Exception localException3)
              {
                com.changdu.changdulib.e.h.e(localException3);
              }
              if (paramA != null)
              {
                if (paramA.d()) {
                  a(new dc.a(paramA.d), paramC, paramK, paramBoolean);
                }
                if (paramA.e()) {
                  a(new dc.a(paramA.e), paramC, paramK, paramBoolean);
                }
              }
            }
          }
        }
        com.changdu.e.h.e().a(localFile.getAbsolutePath());
        com.changdu.e.h.e().b(localFile.getAbsolutePath());
      }
      catch (Exception paramA)
      {
        com.changdu.changdulib.e.h.b(paramA);
      }
      if (paramBoolean)
      {
        return localFile.delete();
        if (paramA.d())
        {
          paramA = paramA.l();
          i = 0;
          while (i < paramA.size())
          {
            a((dc.a)paramA.get(i), paramC, paramK, paramBoolean);
            i += 1;
          }
        }
      }
      return false;
      int i = 2;
      break label913;
      i = 0;
      switch (i)
      {
      }
      paramA = null;
    }
  }
  
  public static boolean a(dc.a paramA, String paramString)
  {
    return paramA.a(paramString);
  }
  
  public static boolean a(File paramFile)
  {
    int i = 0;
    if (paramFile == null) {
      return false;
    }
    if (paramFile.isFile())
    {
      b(ApplicationInit.h, paramFile);
      return paramFile.delete();
    }
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      while (i < arrayOfFile.length)
      {
        a(arrayOfFile[i]);
        i += 1;
      }
      return paramFile.delete();
    }
    return false;
  }
  
  public static boolean a(File paramFile, da paramDa)
  {
    Object localObject = new ArrayList();
    a(paramFile, paramDa, (ArrayList)localObject);
    if (d()) {
      b((ArrayList)localObject);
    }
    paramFile = new ArrayList();
    int i = 0;
    while (i < ((ArrayList)localObject).size())
    {
      paramFile.add(dc.b(((File)((ArrayList)localObject).get(i)).getAbsolutePath(), dc.b.a));
      i += 1;
    }
    if (e())
    {
      paramDa = paramFile.iterator();
      while (paramDa.hasNext())
      {
        localObject = (dc.a)paramDa.next();
        com.changdu.e.h.c().a((dc.a)localObject);
      }
    }
    a(paramFile);
    return dc.d(paramFile);
  }
  
  public static boolean a(File paramFile, com.changdu.e.c paramC, k paramK)
  {
    boolean bool = paramFile.isFile();
    int i = 0;
    int j = 0;
    Object localObject2;
    if (bool)
    {
      Object localObject1 = paramFile.getAbsolutePath();
      c((String)localObject1);
      Object localObject3;
      if (((String)localObject1).toLowerCase().endsWith(".ndb"))
      {
        localObject1 = d(paramFile.getName());
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("/temp/");
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append(".txt");
        localObject1 = com.changdu.changdulib.e.c.b.d(((StringBuilder)localObject3).toString());
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject1 = new File((String)localObject1);
          if ((((File)localObject1).isFile()) && (((File)localObject1).exists())) {
            ((File)localObject1).delete();
          }
        }
      }
      b(ApplicationInit.h, paramFile);
      if ((paramC != null) && (!paramFile.getName().toLowerCase().endsWith(".ndl"))) {
        try
        {
          paramC.g(paramFile.getAbsolutePath());
          paramK.a(paramFile.getAbsolutePath());
          com.changdu.e.h.d().c(paramFile.getAbsolutePath());
          paramK.e(paramFile.getAbsolutePath());
        }
        catch (Exception localException)
        {
          com.changdu.changdulib.e.h.e(localException);
        }
      }
      try
      {
        if (paramFile.getName().toLowerCase().endsWith(".ndl"))
        {
          localObject2 = com.changdu.bookread.a.a.f(paramFile.getAbsolutePath());
          if (localObject2 != null)
          {
            localObject3 = ((com.changdu.bookread.a.c)localObject2).c();
            String str = ((com.changdu.bookread.a.c)localObject2).f();
            i = j;
            if (!str.contains("ndaction:readonline")) {
              if (str.contains("ndaction:listenonline"))
              {
                i = j;
              }
              else if (str.contains("ndaction:readcomic"))
              {
                i = 1;
              }
              else
              {
                if (!str.contains("restype=8"))
                {
                  bool = str.contains("ndaction:listenbook");
                  if (!bool)
                  {
                    i = -1;
                    break label338;
                  }
                }
                i = 2;
              }
            }
            try
            {
              label338:
              paramK.a(i, (String)localObject3);
              paramK.a(null, ((com.changdu.bookread.a.c)localObject2).c(), ((com.changdu.bookread.a.c)localObject2).f(), 0, null);
            }
            catch (Exception paramK)
            {
              com.changdu.changdulib.e.h.e(paramK);
            }
            try
            {
              paramC.a(null, ((com.changdu.bookread.a.c)localObject2).c());
            }
            catch (Exception paramC)
            {
              com.changdu.changdulib.e.h.e(paramC);
            }
          }
        }
        return paramFile.delete();
      }
      catch (Exception paramC)
      {
        com.changdu.changdulib.e.h.b(paramC);
      }
    }
    if (paramFile.isDirectory())
    {
      localObject2 = paramFile.listFiles();
      while (i < localObject2.length)
      {
        a(localObject2[i], paramC, paramK);
        i += 1;
      }
      return paramFile.delete();
    }
    return false;
  }
  
  public static int b()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static Bitmap b(String paramString, Bitmap paramBitmap1, Bitmap paramBitmap2, boolean paramBoolean)
  {
    int j = z.a(paramBitmap2.getWidth());
    int i = z.a(paramBitmap2.getHeight());
    Bitmap localBitmap = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_4444);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint(1);
    Rect localRect;
    double d1;
    if (paramBoolean)
    {
      localRect = new Rect(0, 0, paramBitmap1.getWidth(), paramBitmap1.getHeight());
      d1 = j;
      j = (int)(0.073D * d1);
      double d2 = i;
      localCanvas.drawBitmap(paramBitmap1, localRect, new Rect(j, (int)(d2 * 0.05D), (int)(d1 * 0.92D), (int)(d2 * 0.95D)), localPaint);
    }
    else
    {
      localRect = new Rect(0, 0, paramBitmap1.getWidth(), paramBitmap1.getHeight());
      d1 = i;
      localCanvas.drawBitmap(paramBitmap1, localRect, new Rect(0, (int)(d1 * 0.01D), j, (int)(d1 * 0.99D)), localPaint);
    }
    localCanvas.drawBitmap(paramBitmap2, new Rect(0, 0, paramBitmap2.getWidth(), paramBitmap2.getHeight()), new Rect(0, (int)(i * 0.01D), localBitmap.getWidth(), localBitmap.getHeight()), localPaint);
    localCanvas.save(31);
    localCanvas.restore();
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        paramString = new File(paramString);
        paramBitmap1 = paramString.getName();
        paramBitmap2 = new StringBuilder();
        paramBitmap2.append(paramString.getParent());
        paramBitmap2.append("/");
        paramBitmap2.append(bn.V().W());
        paramBitmap2.append("_");
        paramBitmap2.append(paramBitmap1);
        a(new File(paramBitmap2.toString()), localBitmap);
        return localBitmap;
      }
      catch (IOException paramString)
      {
        com.changdu.changdulib.e.h.b(paramString);
      }
    }
    return localBitmap;
  }
  
  public static String b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    return a(paramString.toLowerCase());
  }
  
  public static String b(String paramString1, String paramString2, e paramE)
  {
    Object localObject = paramString2;
    int i = 0;
    while (paramE.e(paramString1, (String)localObject))
    {
      int j = ((String)localObject).lastIndexOf('.');
      if (j > 0)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(((String)localObject).substring(0, j));
        localStringBuilder.append(i);
        localStringBuilder.append(((String)localObject).substring(j));
        localObject = localStringBuilder.toString();
        i += 1;
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString2);
        ((StringBuilder)localObject).append(i);
        localObject = ((StringBuilder)localObject).toString();
        i += 1;
      }
    }
    return localObject;
  }
  
  private static ArrayList<dc.a> b(ArrayList<dc.a> paramArrayList1, ArrayList<dc.a> paramArrayList2)
  {
    if ((paramArrayList2 != null) && (paramArrayList2.size() > 0)) {
      paramArrayList1.addAll(0, paramArrayList2);
    }
    return paramArrayList1;
  }
  
  public static void b(Context paramContext) {}
  
  public static void b(Context paramContext, File paramFile)
  {
    if (paramContext != null)
    {
      if (paramFile == null) {
        return;
      }
      paramContext.sendBroadcast(a(paramContext, paramFile, false));
      return;
    }
  }
  
  public static void b(dc.a paramA, TextView paramTextView, int paramInt)
  {
    if (paramA != null)
    {
      if (bq.bc) {
        localObject = ApplicationInit.g.a(paramA.a);
      } else {
        localObject = paramA.a;
      }
      paramInt = ((String)localObject).lastIndexOf('/');
      int i = ((String)localObject).lastIndexOf('.');
      if ((paramA.c()) && (!paramA.m.contains("&")))
      {
        if ((i == -1) && (paramInt == -1)) {
          return;
        }
        if (i < paramInt) {
          paramA = ((String)localObject).substring(paramInt + 1);
        } else {
          paramA = ((String)localObject).substring(paramInt + 1, i);
        }
      }
      else if (bq.bc)
      {
        paramA = ApplicationInit.g.a(paramA.m);
      }
      else
      {
        paramA = paramA.m;
      }
      Object localObject = paramA;
      if (paramA.contains(RealVoiceActivity.a)) {
        localObject = paramA.replace(RealVoiceActivity.a, "");
      }
      paramTextView.setText((CharSequence)localObject);
    }
  }
  
  private static void b(File paramFile1, File paramFile2)
  {
    String str1 = f(paramFile2.getAbsolutePath());
    String str2 = f(paramFile1.getAbsolutePath());
    if ((str1 != null) && (!new File(str1).exists()))
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append(".ndf");
      localObject = ((StringBuilder)localObject).toString();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str2);
      localStringBuilder.append(".ndf");
      b((String)localObject, localStringBuilder.toString());
      b(a(paramFile2.getAbsolutePath(), paramFile1), h(paramFile1.getAbsolutePath()));
      b(str1, str2);
    }
  }
  
  private static void b(String paramString1, String paramString2)
  {
    paramString1 = new File(paramString1);
    paramString2 = new File(paramString2);
    if (paramString2.exists()) {
      paramString2.delete();
    }
    if (paramString1.exists()) {
      paramString1.renameTo(paramString2);
    }
  }
  
  /* Error */
  public static void b(ArrayList<File> paramArrayList)
  {
    // Byte code:
    //   0: invokestatic 1221	com/changdu/e/h:a	()Lcom/changdu/e/k;
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: aconst_null
    //   9: astore_2
    //   10: aload 5
    //   12: invokevirtual 1225	com/changdu/e/k:q	()Landroid/database/Cursor;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull +130 -> 147
    //   20: aload_3
    //   21: invokeinterface 462 1 0
    //   26: ifle +121 -> 147
    //   29: aload_3
    //   30: invokeinterface 465 1 0
    //   35: pop
    //   36: iconst_0
    //   37: istore_1
    //   38: iload_1
    //   39: aload_3
    //   40: invokeinterface 462 1 0
    //   45: if_icmpge +102 -> 147
    //   48: aload_3
    //   49: bipush 14
    //   51: invokeinterface 466 2 0
    //   56: astore_2
    //   57: aload_2
    //   58: invokestatic 271	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   61: ifne +23 -> 84
    //   64: aload_2
    //   65: ldc_w 1227
    //   68: invokevirtual 1043	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   71: ifeq +13 -> 84
    //   74: aload_3
    //   75: invokeinterface 1230 1 0
    //   80: pop
    //   81: goto +47 -> 128
    //   84: aload_3
    //   85: iconst_0
    //   86: invokeinterface 466 2 0
    //   91: astore_2
    //   92: aload_2
    //   93: invokestatic 271	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   96: ifne +25 -> 121
    //   99: new 157	java/io/File
    //   102: dup
    //   103: aload_2
    //   104: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   107: astore_2
    //   108: aload_2
    //   109: invokevirtual 304	java/io/File:exists	()Z
    //   112: ifeq +9 -> 121
    //   115: aload_0
    //   116: aload_2
    //   117: invokevirtual 545	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   120: pop
    //   121: aload_3
    //   122: invokeinterface 1230 1 0
    //   127: pop
    //   128: iload_1
    //   129: iconst_1
    //   130: iadd
    //   131: istore_1
    //   132: goto -94 -> 38
    //   135: astore_0
    //   136: goto +41 -> 177
    //   139: astore_2
    //   140: aload_3
    //   141: astore_0
    //   142: aload_2
    //   143: astore_3
    //   144: goto +20 -> 164
    //   147: aload 5
    //   149: aload_3
    //   150: invokevirtual 1233	com/changdu/e/k:a	(Landroid/database/Cursor;)V
    //   153: return
    //   154: astore_0
    //   155: aload_2
    //   156: astore_3
    //   157: goto +20 -> 177
    //   160: astore_3
    //   161: aload 4
    //   163: astore_0
    //   164: aload_0
    //   165: astore_2
    //   166: aload_3
    //   167: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   170: aload 5
    //   172: aload_0
    //   173: invokevirtual 1233	com/changdu/e/k:a	(Landroid/database/Cursor;)V
    //   176: return
    //   177: aload 5
    //   179: aload_3
    //   180: invokevirtual 1233	com/changdu/e/k:a	(Landroid/database/Cursor;)V
    //   183: aload_0
    //   184: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	185	0	paramArrayList	ArrayList<File>
    //   37	95	1	i	int
    //   9	108	2	localObject1	Object
    //   139	17	2	localException1	Exception
    //   165	1	2	localArrayList	ArrayList<File>
    //   15	142	3	localObject2	Object
    //   160	20	3	localException2	Exception
    //   6	156	4	localObject3	Object
    //   3	175	5	localK	k
    // Exception table:
    //   from	to	target	type
    //   20	36	135	finally
    //   38	81	135	finally
    //   84	121	135	finally
    //   121	128	135	finally
    //   20	36	139	java/lang/Exception
    //   38	81	139	java/lang/Exception
    //   84	121	139	java/lang/Exception
    //   121	128	139	java/lang/Exception
    //   10	16	154	finally
    //   166	170	154	finally
    //   10	16	160	java/lang/Exception
  }
  
  private static ArrayList<dc.a> c(ArrayList<dc.a> paramArrayList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    if (paramArrayList != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (paramArrayList.size() > 0)
        {
          i = 0;
          if (i < paramArrayList.size())
          {
            dc.a localA = (dc.a)paramArrayList.get(i);
            if (!localA.g()) {
              break label128;
            }
            if (localA.f().getName().split("\\.")[0].equals(ApplicationInit.h.getString(2131558480)))
            {
              localArrayList1.add(localA);
              break label128;
            }
            localArrayList2.add(localA);
            break label128;
          }
          paramArrayList.clear();
          paramArrayList.addAll(localArrayList2);
          return localArrayList1;
        }
      }
      catch (Exception paramArrayList)
      {
        com.changdu.changdulib.e.h.e(paramArrayList);
      }
      return localArrayList1;
      label128:
      i += 1;
    }
  }
  
  public static void c()
  {
    BaseActivity localBaseActivity = com.changdu.common.a.a().b(new dt());
    if (localBaseActivity != null) {
      ((BookShelfActivity)localBaseActivity).f();
    }
  }
  
  private static void c(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()))
    {
      Object localObject1 = f(paramFile.getAbsolutePath());
      Object localObject2;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        localObject1 = new File((String)localObject1);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((File)localObject1).getParent());
        ((StringBuilder)localObject2).append("/");
        ((StringBuilder)localObject2).append("default");
        ((StringBuilder)localObject2).append("_");
        ((StringBuilder)localObject2).append(((File)localObject1).getName());
        localObject2 = ((StringBuilder)localObject2).toString();
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject2 = new File((String)localObject2);
          if ((((File)localObject2).exists()) && (((File)localObject2).isFile())) {
            ((File)localObject2).delete();
          }
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((File)localObject1).getParent());
        ((StringBuilder)localObject2).append("/");
        ((StringBuilder)localObject2).append("orange");
        ((StringBuilder)localObject2).append("_");
        ((StringBuilder)localObject2).append(((File)localObject1).getName());
        localObject2 = ((StringBuilder)localObject2).toString();
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject2 = new File((String)localObject2);
          if ((((File)localObject2).exists()) && (((File)localObject2).isFile())) {
            ((File)localObject2).delete();
          }
        }
        if ((((File)localObject1).exists()) && (((File)localObject1).isFile())) {
          ((File)localObject1).delete();
        }
      }
      if (paramFile.getAbsolutePath().toLowerCase().endsWith(".ndb"))
      {
        localObject1 = d(paramFile.getName());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("/temp/");
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(".txt");
        localObject1 = com.changdu.changdulib.e.c.b.d(((StringBuilder)localObject2).toString());
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject1 = new File((String)localObject1);
          if ((((File)localObject1).isFile()) && (((File)localObject1).exists())) {
            ((File)localObject1).delete();
          }
        }
      }
      b(ApplicationInit.h, paramFile);
    }
  }
  
  public static void c(String paramString)
  {
    paramString = f(paramString);
    if (!TextUtils.isEmpty(paramString))
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append(".ndf");
      localObject = new File(((StringBuilder)localObject).toString());
      if (((File)localObject).exists()) {
        ((File)localObject).delete();
      }
      paramString = new File(paramString);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString.getParent());
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append("default");
      ((StringBuilder)localObject).append("_");
      ((StringBuilder)localObject).append(paramString.getName());
      localObject = new File(((StringBuilder)localObject).toString());
      if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
        ((File)localObject).delete();
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString.getParent());
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append("orange");
      ((StringBuilder)localObject).append("_");
      ((StringBuilder)localObject).append(paramString.getName());
      localObject = new File(((StringBuilder)localObject).toString());
      if ((((File)localObject).exists()) && (((File)localObject).isFile())) {
        ((File)localObject).delete();
      }
      if ((paramString.exists()) && (paramString.isFile())) {
        paramString.delete();
      }
    }
  }
  
  public static boolean c(Context paramContext)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("content://");
    ((StringBuilder)localObject).append("com.android.launcher.settings");
    ((StringBuilder)localObject).append("/favorites?notify=true");
    localObject = Uri.parse(((StringBuilder)localObject).toString());
    boolean bool2 = false;
    paramContext = paramContext.getString(2131558480);
    paramContext = localContentResolver.query((Uri)localObject, new String[] { "title", "iconResource" }, "title=?", new String[] { paramContext }, null);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.getCount() > 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean c(Context paramContext, File paramFile)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    if (b() < 8) {
      paramContext = "com.android.launcher.settings";
    } else {
      paramContext = "com.android.launcher2.settings";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("content://");
    localStringBuilder.append(paramContext);
    localStringBuilder.append("/favorites?notify=true");
    paramContext = Uri.parse(localStringBuilder.toString());
    boolean bool2 = false;
    paramFile = paramFile.getName();
    paramContext = localContentResolver.query(paramContext, new String[] { "title", "iconResource" }, "title=?", new String[] { paramFile }, null);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramContext.getCount() > 0)
      {
        if (!paramContext.isClosed()) {
          paramContext.close();
        }
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static boolean c(String paramString1, String paramString2)
  {
    return paramString1.toLowerCase().endsWith(paramString2.toLowerCase());
  }
  
  public static String d(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      String[] arrayOfString = ApplicationInit.h.getResources().getStringArray(2130837545);
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        int j = arrayOfString.length;
        int i = 1;
        while (i < j)
        {
          if (paramString.toLowerCase().endsWith(arrayOfString[i]))
          {
            i = paramString.lastIndexOf(".");
            if (i <= -1) {
              break;
            }
            return paramString.substring(0, i);
          }
          i += 1;
        }
      }
    }
    return paramString;
  }
  
  public static boolean d()
  {
    SharedPreferences localSharedPreferences = ApplicationInit.h.getSharedPreferences("setting", 0);
    boolean bool = localSharedPreferences.getBoolean("has_add_history", false);
    if (!bool) {
      localSharedPreferences.edit().putBoolean("has_add_history", true).commit();
    }
    return bool ^ true;
  }
  
  public static String e(String paramString)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("/covers/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(".jpg");
    localObject = com.changdu.changdulib.e.c.b.a(((StringBuilder)localObject).toString(), 0L);
    if (((com.changdu.changdulib.e.c.a)localObject).d()) {
      return ((com.changdu.changdulib.e.c.a)localObject).b();
    }
    if (((com.changdu.changdulib.e.c.a)localObject).e()) {
      return ((com.changdu.changdulib.e.c.a)localObject).c();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(a().getAbsolutePath());
    ((StringBuilder)localObject).append("/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(".jpg");
    return ((StringBuilder)localObject).toString();
  }
  
  public static boolean e()
  {
    SharedPreferences localSharedPreferences = ApplicationInit.h.getSharedPreferences("setting", 0);
    boolean bool = localSharedPreferences.getBoolean("has_add_cover", false);
    if (!bool) {
      localSharedPreferences.edit().putBoolean("has_add_cover", true).commit();
    }
    return bool ^ true;
  }
  
  public static final String f(String paramString)
  {
    return e(a(paramString.toLowerCase()));
  }
  
  public static boolean g(String paramString)
  {
    return new File(f(paramString)).exists();
  }
  
  public static String h(String paramString)
  {
    Object localObject = paramString;
    if (paramString == null) {
      localObject = "";
    }
    paramString = new File((String)localObject);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(((String)localObject).hashCode());
    localStringBuilder.append(paramString.length());
    paramString = localStringBuilder.toString();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(a().getAbsolutePath());
    ((StringBuilder)localObject).append("/_");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(".bpt");
    return ((StringBuilder)localObject).toString();
  }
  
  public static boolean i(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    int i = paramString.lastIndexOf('.');
    if (i == -1) {
      return false;
    }
    if ("  x.ndb  x.ndl  x.epub  x.umd  x.pdf".indexOf(paramString.substring(i).toLowerCase()) == -1) {
      return false;
    }
    if (new File(h(paramString)).exists()) {
      return false;
    }
    paramString = f(paramString);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(".ndf");
    return !new File(localStringBuilder.toString()).exists();
  }
  
  /* Error */
  public static String j(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 1308	com/changdu/bookshelf/do:i	(Ljava/lang/String;)Z
    //   4: istore_1
    //   5: aconst_null
    //   6: astore_3
    //   7: iload_1
    //   8: ifne +5 -> 13
    //   11: aconst_null
    //   12: areturn
    //   13: aload_0
    //   14: ldc_w 1009
    //   17: invokestatic 1310	com/changdu/bookshelf/do:c	(Ljava/lang/String;Ljava/lang/String;)Z
    //   20: ifeq +299 -> 319
    //   23: aload_0
    //   24: invokestatic 1207	com/changdu/bookshelf/do:f	(Ljava/lang/String;)Ljava/lang/String;
    //   27: astore 4
    //   29: new 192	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   36: astore_2
    //   37: aload_2
    //   38: aload 4
    //   40: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_2
    //   45: ldc_w 1209
    //   48: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: aload_2
    //   53: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: astore_2
    //   57: new 157	java/io/File
    //   60: dup
    //   61: aload 4
    //   63: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   66: astore 5
    //   68: new 157	java/io/File
    //   71: dup
    //   72: aload_2
    //   73: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   76: astore 6
    //   78: aload 4
    //   80: astore_2
    //   81: aload 5
    //   83: invokevirtual 304	java/io/File:exists	()Z
    //   86: ifne +715 -> 801
    //   89: aload 4
    //   91: astore_2
    //   92: aload 6
    //   94: invokevirtual 304	java/io/File:exists	()Z
    //   97: ifne +704 -> 801
    //   100: invokestatic 1314	com/changdu/changdulib/parser/ndb/l:b	()V
    //   103: aload_0
    //   104: invokestatic 1317	com/changdu/changdulib/parser/ndb/l:a	(Ljava/lang/String;)Lcom/changdu/changdulib/parser/ndb/l;
    //   107: astore_2
    //   108: aload_2
    //   109: ifnull +208 -> 317
    //   112: aload_2
    //   113: invokevirtual 1320	com/changdu/changdulib/parser/ndb/l:v	()Z
    //   116: ifne +5 -> 121
    //   119: aconst_null
    //   120: areturn
    //   121: aload_2
    //   122: invokevirtual 1321	com/changdu/changdulib/parser/ndb/l:u	()I
    //   125: iconst_1
    //   126: if_icmpne +58 -> 184
    //   129: aload_2
    //   130: invokevirtual 1324	com/changdu/changdulib/parser/ndb/l:a	()Lcom/changdu/changdulib/parser/ndb/a/n;
    //   133: astore 5
    //   135: aload_3
    //   136: astore_2
    //   137: aload 5
    //   139: ifnull +662 -> 801
    //   142: aload 5
    //   144: invokevirtual 1329	com/changdu/changdulib/parser/ndb/a/n:p	()Ljava/lang/String;
    //   147: astore_2
    //   148: new 157	java/io/File
    //   151: dup
    //   152: aload_2
    //   153: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   156: astore_3
    //   157: aload_3
    //   158: invokevirtual 304	java/io/File:exists	()Z
    //   161: ifeq +640 -> 801
    //   164: aload_3
    //   165: new 157	java/io/File
    //   168: dup
    //   169: aload 4
    //   171: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   174: invokevirtual 1218	java/io/File:renameTo	(Ljava/io/File;)Z
    //   177: pop
    //   178: aload 4
    //   180: astore_2
    //   181: goto +620 -> 801
    //   184: aload_2
    //   185: bipush 109
    //   187: sipush 150
    //   190: invokevirtual 1332	com/changdu/changdulib/parser/ndb/l:a	(II)Landroid/graphics/drawable/Drawable;
    //   193: astore 5
    //   195: aload_3
    //   196: astore_2
    //   197: aload 5
    //   199: ifnull +602 -> 801
    //   202: aload_3
    //   203: astore_2
    //   204: aload 5
    //   206: instanceof 285
    //   209: ifeq +592 -> 801
    //   212: aload 5
    //   214: checkcast 285	android/graphics/drawable/BitmapDrawable
    //   217: invokevirtual 289	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   220: astore 5
    //   222: aload 5
    //   224: aload_0
    //   225: invokestatic 397	com/changdu/bookshelf/do:b	(Ljava/lang/String;)Ljava/lang/String;
    //   228: invokestatic 400	com/changdu/bookshelf/do:a	(Landroid/graphics/Bitmap;Ljava/lang/String;)V
    //   231: aload 4
    //   233: astore_2
    //   234: aload 5
    //   236: ifnull +565 -> 801
    //   239: aload 4
    //   241: astore_2
    //   242: aload 5
    //   244: invokevirtual 403	android/graphics/Bitmap:isRecycled	()Z
    //   247: ifne +554 -> 801
    //   250: aload 5
    //   252: invokevirtual 406	android/graphics/Bitmap:recycle	()V
    //   255: aload 4
    //   257: astore_2
    //   258: goto +543 -> 801
    //   261: astore_0
    //   262: goto +35 -> 297
    //   265: astore_2
    //   266: aload_2
    //   267: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   270: aload_3
    //   271: astore_2
    //   272: aload 5
    //   274: ifnull +527 -> 801
    //   277: aload_3
    //   278: astore_2
    //   279: aload 5
    //   281: invokevirtual 403	android/graphics/Bitmap:isRecycled	()Z
    //   284: ifne +517 -> 801
    //   287: aload 5
    //   289: invokevirtual 406	android/graphics/Bitmap:recycle	()V
    //   292: aload_3
    //   293: astore_2
    //   294: goto +507 -> 801
    //   297: aload 5
    //   299: ifnull +16 -> 315
    //   302: aload 5
    //   304: invokevirtual 403	android/graphics/Bitmap:isRecycled	()Z
    //   307: ifne +8 -> 315
    //   310: aload 5
    //   312: invokevirtual 406	android/graphics/Bitmap:recycle	()V
    //   315: aload_0
    //   316: athrow
    //   317: aconst_null
    //   318: areturn
    //   319: aload_0
    //   320: ldc_w 1334
    //   323: invokestatic 1310	com/changdu/bookshelf/do:c	(Ljava/lang/String;Ljava/lang/String;)Z
    //   326: ifeq +130 -> 456
    //   329: aload_0
    //   330: invokestatic 1207	com/changdu/bookshelf/do:f	(Ljava/lang/String;)Ljava/lang/String;
    //   333: astore 4
    //   335: new 157	java/io/File
    //   338: dup
    //   339: aload 4
    //   341: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   344: astore 5
    //   346: new 192	java/lang/StringBuilder
    //   349: dup
    //   350: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   353: astore_2
    //   354: aload_2
    //   355: aload 5
    //   357: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   360: pop
    //   361: aload_2
    //   362: ldc_w 1209
    //   365: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: pop
    //   369: new 157	java/io/File
    //   372: dup
    //   373: aload_2
    //   374: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   377: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   380: astore 6
    //   382: aload 4
    //   384: astore_2
    //   385: aload 5
    //   387: invokevirtual 304	java/io/File:exists	()Z
    //   390: ifne +411 -> 801
    //   393: aload 4
    //   395: astore_2
    //   396: aload 6
    //   398: invokevirtual 304	java/io/File:exists	()Z
    //   401: ifne +400 -> 801
    //   404: new 1339	com/changdu/changdulib/parser/b/g
    //   407: dup
    //   408: invokespecial 1340	com/changdu/changdulib/parser/b/g:<init>	()V
    //   411: astore_2
    //   412: aload_2
    //   413: aload_0
    //   414: invokevirtual 1341	com/changdu/changdulib/parser/b/g:a	(Ljava/lang/String;)Z
    //   417: pop
    //   418: aload_2
    //   419: aload 4
    //   421: ldc_w 1301
    //   424: ldc_w 473
    //   427: invokevirtual 564	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   430: invokevirtual 1342	com/changdu/changdulib/parser/b/g:b	(Ljava/lang/String;)Z
    //   433: istore_1
    //   434: aload 4
    //   436: astore_2
    //   437: iload_1
    //   438: ifne +363 -> 801
    //   441: aconst_null
    //   442: astore_2
    //   443: goto +358 -> 801
    //   446: astore_2
    //   447: aload_2
    //   448: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   451: aload_3
    //   452: astore_2
    //   453: goto +348 -> 801
    //   456: aload_0
    //   457: ldc_w 1344
    //   460: invokestatic 1310	com/changdu/bookshelf/do:c	(Ljava/lang/String;Ljava/lang/String;)Z
    //   463: ifeq +128 -> 591
    //   466: aload_0
    //   467: invokestatic 1207	com/changdu/bookshelf/do:f	(Ljava/lang/String;)Ljava/lang/String;
    //   470: astore_3
    //   471: new 192	java/lang/StringBuilder
    //   474: dup
    //   475: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   478: astore_2
    //   479: aload_2
    //   480: aload_3
    //   481: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   484: pop
    //   485: aload_2
    //   486: ldc_w 1209
    //   489: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: pop
    //   493: aload_2
    //   494: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   497: astore_2
    //   498: new 157	java/io/File
    //   501: dup
    //   502: aload_3
    //   503: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   506: astore 4
    //   508: new 157	java/io/File
    //   511: dup
    //   512: aload_2
    //   513: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   516: astore 5
    //   518: aload_3
    //   519: astore_2
    //   520: aload 4
    //   522: invokevirtual 304	java/io/File:exists	()Z
    //   525: ifne +276 -> 801
    //   528: aload_3
    //   529: astore_2
    //   530: aload 5
    //   532: invokevirtual 304	java/io/File:exists	()Z
    //   535: ifne +266 -> 801
    //   538: aload_0
    //   539: ldc_w 1012
    //   542: invokestatic 510	com/changdu/changdulib/e/c/b:e	(Ljava/lang/String;)Ljava/lang/String;
    //   545: iconst_0
    //   546: invokestatic 1349	com/changdu/bookread/epub/h:a	(Ljava/lang/String;Ljava/lang/String;Z)Ljava/lang/String;
    //   549: astore_2
    //   550: aload_2
    //   551: ifnull +37 -> 588
    //   554: new 157	java/io/File
    //   557: dup
    //   558: aload_2
    //   559: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   562: astore_2
    //   563: aload_2
    //   564: invokevirtual 304	java/io/File:exists	()Z
    //   567: ifeq -126 -> 441
    //   570: aload_2
    //   571: new 157	java/io/File
    //   574: dup
    //   575: aload_3
    //   576: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   579: invokevirtual 1218	java/io/File:renameTo	(Ljava/io/File;)Z
    //   582: pop
    //   583: aload_3
    //   584: astore_2
    //   585: goto +216 -> 801
    //   588: goto +213 -> 801
    //   591: aload_0
    //   592: ldc_w 436
    //   595: invokestatic 1310	com/changdu/bookshelf/do:c	(Ljava/lang/String;Ljava/lang/String;)Z
    //   598: ifeq +97 -> 695
    //   601: aload_0
    //   602: invokestatic 1207	com/changdu/bookshelf/do:f	(Ljava/lang/String;)Ljava/lang/String;
    //   605: astore 4
    //   607: new 192	java/lang/StringBuilder
    //   610: dup
    //   611: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   614: astore_2
    //   615: aload_2
    //   616: aload 4
    //   618: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: pop
    //   622: aload_2
    //   623: ldc_w 1209
    //   626: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   629: pop
    //   630: aload_2
    //   631: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   634: astore_2
    //   635: new 157	java/io/File
    //   638: dup
    //   639: aload 4
    //   641: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   644: astore 5
    //   646: new 157	java/io/File
    //   649: dup
    //   650: aload_2
    //   651: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   654: astore 6
    //   656: aload 4
    //   658: astore_2
    //   659: aload 5
    //   661: invokevirtual 304	java/io/File:exists	()Z
    //   664: ifne +137 -> 801
    //   667: aload 4
    //   669: astore_2
    //   670: aload 6
    //   672: invokevirtual 304	java/io/File:exists	()Z
    //   675: ifne +126 -> 801
    //   678: aload_3
    //   679: astore_2
    //   680: aload_0
    //   681: aload 4
    //   683: invokestatic 1350	com/changdu/bookread/a/a:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   686: ifeq +115 -> 801
    //   689: aload 4
    //   691: astore_2
    //   692: goto +109 -> 801
    //   695: aload_3
    //   696: astore_2
    //   697: aload_0
    //   698: ldc_w 1352
    //   701: invokestatic 1310	com/changdu/bookshelf/do:c	(Ljava/lang/String;Ljava/lang/String;)Z
    //   704: ifeq +97 -> 801
    //   707: aload_0
    //   708: invokestatic 1207	com/changdu/bookshelf/do:f	(Ljava/lang/String;)Ljava/lang/String;
    //   711: astore_3
    //   712: new 192	java/lang/StringBuilder
    //   715: dup
    //   716: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   719: astore_2
    //   720: aload_2
    //   721: aload_3
    //   722: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: pop
    //   726: aload_2
    //   727: ldc_w 1209
    //   730: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   733: pop
    //   734: aload_2
    //   735: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   738: astore_2
    //   739: new 157	java/io/File
    //   742: dup
    //   743: aload_3
    //   744: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   747: astore 4
    //   749: new 157	java/io/File
    //   752: dup
    //   753: aload_2
    //   754: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   757: astore 5
    //   759: aload_3
    //   760: astore_2
    //   761: aload 4
    //   763: invokevirtual 304	java/io/File:exists	()Z
    //   766: ifne +35 -> 801
    //   769: aload_3
    //   770: astore_2
    //   771: aload 5
    //   773: invokevirtual 304	java/io/File:exists	()Z
    //   776: ifne +25 -> 801
    //   779: iconst_1
    //   780: getstatic 493	com/changdu/netprotocol/NdPlugInData$PlugInInfo:PLUGIN_PDF	Ljava/lang/String;
    //   783: invokestatic 498	com/changdu/plugin/i:a	(ILjava/lang/String;)I
    //   786: iconst_2
    //   787: if_icmpne +12 -> 799
    //   790: aload_0
    //   791: aload_3
    //   792: invokestatic 1354	com/changdu/bookshelf/do:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   795: astore_2
    //   796: goto +5 -> 801
    //   799: aconst_null
    //   800: areturn
    //   801: aload_2
    //   802: ifnull +17 -> 819
    //   805: new 157	java/io/File
    //   808: dup
    //   809: aload_2
    //   810: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   813: invokevirtual 304	java/io/File:exists	()Z
    //   816: ifne +65 -> 881
    //   819: new 192	java/lang/StringBuilder
    //   822: dup
    //   823: invokespecial 193	java/lang/StringBuilder:<init>	()V
    //   826: astore_3
    //   827: aload_3
    //   828: aload_0
    //   829: invokestatic 1207	com/changdu/bookshelf/do:f	(Ljava/lang/String;)Ljava/lang/String;
    //   832: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   835: pop
    //   836: aload_3
    //   837: ldc_w 1209
    //   840: invokevirtual 199	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: pop
    //   844: new 157	java/io/File
    //   847: dup
    //   848: aload_3
    //   849: invokevirtual 205	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   852: invokespecial 272	java/io/File:<init>	(Ljava/lang/String;)V
    //   855: astore_0
    //   856: aload_0
    //   857: invokevirtual 304	java/io/File:exists	()Z
    //   860: ifne +21 -> 881
    //   863: new 710	java/io/FileOutputStream
    //   866: dup
    //   867: aload_0
    //   868: invokespecial 713	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   871: invokevirtual 731	java/io/FileOutputStream:close	()V
    //   874: aload_2
    //   875: areturn
    //   876: astore_0
    //   877: aload_0
    //   878: invokestatic 408	com/changdu/changdulib/e/h:e	(Ljava/lang/Object;)V
    //   881: aload_2
    //   882: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	883	0	paramString	String
    //   4	434	1	bool	boolean
    //   36	222	2	localObject1	Object
    //   265	2	2	localException1	Exception
    //   271	172	2	localObject2	Object
    //   446	2	2	localException2	Exception
    //   452	430	2	localObject3	Object
    //   6	843	3	localObject4	Object
    //   27	735	4	localObject5	Object
    //   66	706	5	localObject6	Object
    //   76	595	6	localFile	File
    // Exception table:
    //   from	to	target	type
    //   222	231	261	finally
    //   266	270	261	finally
    //   222	231	265	java/lang/Exception
    //   412	434	446	java/lang/Exception
    //   863	874	876	java/lang/Exception
  }
  
  public static boolean k(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    a(localOptions, 10, 20);
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    localOptions.inJustDecodeBounds = false;
    if (!TextUtils.isEmpty(paramString)) {
      try
      {
        paramString = new File(paramString);
        String str = paramString.getName();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString.getParent());
        localStringBuilder.append("/_");
        localStringBuilder.append(str);
        paramString = localStringBuilder.toString();
        a(new File(paramString), BitmapFactory.decodeFile(paramString, localOptions));
        return true;
      }
      catch (IOException paramString)
      {
        com.changdu.changdulib.e.h.b(paramString);
        return false;
      }
    }
    return true;
  }
  
  public static String l(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    String str = paramString;
    if (i > 0)
    {
      str = paramString;
      if (i < paramString.length()) {
        str = paramString.substring(0, i);
      }
    }
    paramString = str;
    if (bq.bc) {
      paramString = f.a(str);
    }
    return paramString;
  }
  
  public static Drawable m(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    localOptions.inDensity = 240;
    localOptions.inTargetDensity = 320;
    localOptions.inJustDecodeBounds = false;
    paramString = BitmapFactory.decodeFile(paramString, localOptions);
    return new BitmapDrawable(ApplicationInit.h.getResources(), paramString);
  }
  
  public static Drawable n(String paramString)
  {
    Canvas localCanvas = null;
    Object localObject1 = null;
    try
    {
      Object localObject2 = m(paramString);
      paramString = localCanvas;
      if (localObject2 != null)
      {
        localObject2 = ((BitmapDrawable)localObject2).getBitmap();
        paramString = localCanvas;
        if (localObject2 != null)
        {
          Object localObject3 = com.changdu.util.g.c.a("r_drawable_shelf_cover", 0);
          paramString = localCanvas;
          if (localObject3 != null)
          {
            localObject3 = ((BitmapDrawable)localObject3).getBitmap();
            paramString = localCanvas;
            if (localObject3 != null)
            {
              int j = ((Bitmap)localObject3).getWidth();
              int i = ((Bitmap)localObject3).getHeight();
              paramString = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_4444);
              localCanvas = new Canvas(paramString);
              localObject3 = new Paint(1);
              Rect localRect = new Rect(0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight());
              double d1 = j;
              j = (int)(0.105D * d1);
              double d2 = i;
              localCanvas.drawBitmap((Bitmap)localObject2, localRect, new Rect(j, (int)(0.005D * d2), (int)(d1 * 0.895D), (int)(d2 * 0.995D)), (Paint)localObject3);
              localCanvas.save(31);
              localCanvas.restore();
              paramString = new BitmapDrawable(paramString);
              try
              {
                paramString.setTargetDensity(ApplicationInit.h.getResources().getDisplayMetrics());
                return paramString;
              }
              catch (Exception localException1) {}
              com.changdu.changdulib.e.h.e(localException2);
            }
          }
        }
      }
    }
    catch (Exception localException2)
    {
      paramString = localObject1;
    }
    return paramString;
  }
  
  public static Drawable o(String paramString)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    Bitmap localBitmap = ((BitmapDrawable)com.changdu.util.g.c.a("r_drawable_shelf_cover", 0)).getBitmap();
    int i = localBitmap.getWidth();
    int j = localBitmap.getHeight();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;
    a(localOptions, i, j);
    localOptions.inJustDecodeBounds = false;
    paramString = new BitmapDrawable(BitmapFactory.decodeFile(paramString, localOptions));
    paramString.setTargetDensity(ApplicationInit.h.getResources().getDisplayMetrics());
    return paramString;
  }
}
