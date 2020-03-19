package com.google.android.c2dm;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import com.uc.browser.ActivityBrowser;
import com.uc.browser.fm;
import com.uc.browser.homepage.view.bb;
import com.uc.browser.homepage.view.bc;
import com.uc.browser.homepage.view.bd;
import com.uc.browser.homepage.view.bh;
import com.uc.browser.homepage.view.bi;
import com.uc.browser.homepage.view.bj;
import com.uc.browser.homepage.view.bk;
import com.uc.browser.homepage.view.bo;
import com.uc.browser.homepage.view.c;
import com.uc.browser.homepage.view.l;
import com.uc.browser.mediaplayer.UcMediaPlayerActivity;
import com.uc.browser.mediaplayer.ab;
import com.uc.browser.mediaplayer.ac;
import com.uc.browser.o;
import com.uc.platform.g;
import com.uc.platform.h;
import com.uc.util.b;
import dw;
import hi;
import hz;
import it;
import iu;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterOutputStream;
import kf;
import kg;
import kr;
import ks;
import kx;
import lb;
import nv;
import oh;
import oi;
import ok;
import ol;
import om;
import org.json.JSONArray;
import org.json.JSONException;
import pf;
import pr;
import ql;
import qm;
import rm;
import si;
import ss;
import ts;
import wr;
import ws;
import wx;
import xf;

public final class a
{
  public static Context d;
  private static Bitmap e;
  private static Bitmap f;
  public int a;
  public byte[] b;
  public int c;
  
  public a() {}
  
  public static int a(int paramInt)
  {
    int i = 0;
    if (paramInt == -1) {
      i = 999;
    }
    do
    {
      return i;
      if (paramInt == 2) {
        return 901;
      }
      if (paramInt == 3) {
        return 905;
      }
    } while (paramInt != 1);
    return 815;
  }
  
  private static int a(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readByte();
    if (i >= 0) {}
    int k;
    do
    {
      return i;
      i &= 0x7F;
      j = paramDataInputStream.readByte();
      if (j >= 0) {
        return i | j << 7;
      }
      i |= (j & 0x7F) << 7;
      j = paramDataInputStream.readByte();
      if (j >= 0) {
        return i | j << 14;
      }
      i |= (j & 0x7F) << 14;
      k = paramDataInputStream.readByte();
      if (k >= 0) {
        return i | k << 21;
      }
      j = paramDataInputStream.readByte();
      k = i | (k & 0x7F) << 21 | j << 28;
      i = k;
    } while (j >= 0);
    int j = 0;
    for (;;)
    {
      i = k;
      if (j >= 5) {
        break;
      }
      i = k;
      if (paramDataInputStream.readByte() >= 0) {
        break;
      }
      j += 1;
    }
  }
  
  public static int a(rm paramRm)
  {
    int i = paramRm.q();
    long l = paramRm.n();
    switch (i)
    {
    default: 
    case 3: 
    case 2: 
      do
      {
        do
        {
          return 307200;
          if (paramRm.n() >= 921600L) {
            return (int)(l / i);
          }
        } while (l <= 614400L);
        return 307200;
      } while (l <= 614400L);
      return (int)(l / i);
    }
    return (int)l;
  }
  
  public static int a(short paramShort)
  {
    switch (paramShort)
    {
    default: 
      return 0;
    case -11: 
    case -10: 
    case -1: 
      return 605;
    case -2: 
      return 902;
    case -4: 
      return 903;
    case -5: 
      return 606;
    case -127: 
      return 904;
    case -7: 
      return 907;
    case -8: 
      return 908;
    case -12: 
      return 909;
    case -61: 
      return 9011;
    case -62: 
      return 9012;
    case -63: 
      return 9013;
    case -64: 
      return 9014;
    case -65: 
      return 9015;
    case -66: 
      return 9016;
    }
    return 9017;
  }
  
  public static Drawable a(Context paramContext, int paramInt)
  {
    if (paramContext == null) {
      return null;
    }
    return ts.b().d(paramInt);
  }
  
  public static Drawable a(Drawable paramDrawable)
  {
    if (paramDrawable == null) {
      return null;
    }
    return new LayerDrawable(new Drawable[] { paramDrawable, c() });
  }
  
  public static Drawable a(Drawable paramDrawable, int paramInt)
  {
    if (paramDrawable == null) {
      return null;
    }
    return new LayerDrawable(new Drawable[] { paramDrawable, new ColorDrawable(paramInt) });
  }
  
  public static c a(Context paramContext, hi paramHi)
  {
    if ((paramContext == null) || (paramHi == null)) {
      return null;
    }
    switch (paramHi.a())
    {
    case 9: 
    default: 
      return null;
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 7: 
    case 11: 
      return new l(paramContext, paramHi);
    case 12: 
      return new bd(paramContext, paramHi);
    case 5: 
    case 6: 
      return new bk(paramContext, paramHi);
    case 8: 
      return new bb(paramContext, paramHi);
    case 10: 
      return new bo(paramContext, paramHi);
    case 13: 
      return new bc(paramContext, paramHi);
    case 14: 
      return new bh(paramContext, paramHi);
    case 15: 
      return new bi(paramContext, paramHi);
    }
    return new bj(paramContext, paramHi);
  }
  
  public static dw a(String paramString1, String paramString2)
  {
    int i = 0;
    if ((pf.a(paramString1)) || (pf.a(paramString2))) {
      return null;
    }
    paramString2 = new dw(paramString2);
    for (;;)
    {
      try
      {
        paramString1 = new JSONArray(paramString1);
        Object localObject1 = paramString1.opt(1);
        if ((localObject1 != null) && ((localObject1 instanceof JSONArray)))
        {
          localObject1 = (JSONArray)localObject1;
          int j = ((JSONArray)localObject1).length();
          if (i < j)
          {
            Object localObject2 = ((JSONArray)localObject1).get(i);
            if (localObject2 == null) {
              break label139;
            }
            if ((localObject2 instanceof JSONArray))
            {
              localObject2 = paramString1.opt(0);
              if (localObject2 != null) {
                paramString2.a(localObject2.toString());
              }
            }
            else
            {
              paramString2.a(localObject2.toString());
            }
          }
        }
      }
      catch (JSONException paramString1)
      {
        return paramString2;
      }
      finally
      {
        return paramString2;
      }
      label139:
      i += 1;
    }
  }
  
  public static String a()
  {
    String str = g.h().a();
    if (!TextUtils.isEmpty(str))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      if (!str.endsWith(File.separator)) {
        localStringBuilder.append(File.separator).append("UCDownloads").append(File.separator).append("Compress").append(File.separator).append("tmp");
      }
      for (;;)
      {
        return localStringBuilder.toString();
        localStringBuilder.append("UCDownloads").append(File.separator).append("Compress").append(File.separator).append("tmp");
      }
    }
    return null;
  }
  
  public static String a(Context paramContext)
  {
    return paramContext.getSharedPreferences("com.google.android.c2dm", 0).getString("dm_registration", "");
  }
  
  public static String a(Context paramContext, rm paramRm)
  {
    if ((paramContext == null) || (paramRm == null)) {
      return null;
    }
    long l;
    switch (paramRm.l())
    {
    default: 
      return null;
    case -1: 
      return paramContext.getString(2131361797);
    case 0: 
      l = paramRm.r();
      if (l > 0L) {
        l = (paramRm.n() - paramRm.m()) / l;
      }
      break;
    }
    try
    {
      paramContext = String.format(paramContext.getString(2131361800), new Object[] { pf.b(l) });
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
    if (paramRm.ae() == 1) {
      return paramContext.getString(2131361812);
    }
    if (paramRm.ae() == 2) {
      return paramContext.getString(2131361813);
    }
    if (paramRm.ae() == 3) {
      return paramContext.getString(2131361816);
    }
    return paramContext.getString(2131361799);
    if (paramRm.I() == 701) {
      return paramContext.getString(2131361805);
    }
    if (paramRm.ag()) {
      return paramContext.getString(2131361806);
    }
    if (paramRm.ah()) {
      return paramContext.getString(2131361807);
    }
    return paramContext.getString(2131361802);
    return paramContext.getString(2131361798);
    return paramContext.getString(2131361810);
    return String.format(paramContext.getString(2131361811), new Object[] { Integer.valueOf(paramRm.L() + 1) });
    return paramContext.getString(2131361801);
    return null;
  }
  
  public static String a(String paramString)
  {
    int k = 0;
    if (paramString == null) {
      return paramString;
    }
    byte[] arrayOfByte = paramString.getBytes();
    int m = arrayOfByte.length;
    int i = 0;
    for (;;)
    {
      int j = k;
      if (i < m)
      {
        Byte localByte = Byte.valueOf(arrayOfByte[i]);
        if (((localByte.byteValue() >= 0) && (localByte.byteValue() <= 31)) || (localByte.byteValue() == 96) || (localByte.byteValue() == Byte.MAX_VALUE))
        {
          new StringBuilder().append(paramString).append(" cotains invalid char");
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          break;
        }
        return Base64.encodeToString(arrayOfByte, 2);
      }
      i += 1;
    }
  }
  
  public static String a(String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer(40);
    localStringBuffer.append(paramString1).append('?');
    localStringBuffer.append("sn=").append(paramString2).append('&');
    localStringBuffer.append("session=").append(paramString3).append('&');
    localStringBuffer.append("boundary=-----Ajzlizbchdz&");
    localStringBuffer.append("seq=").append(paramInt).append('&');
    if (paramBoolean) {
      localStringBuffer.append("end=1");
    }
    for (;;)
    {
      return localStringBuffer.toString();
      localStringBuffer.append("end=0");
    }
  }
  
  /* Error */
  private static String a(String paramString, ArrayList paramArrayList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnonnull +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: new 305	java/net/URL
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 306	java/net/URL:<init>	(Ljava/lang/String;)V
    //   17: invokevirtual 310	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   20: checkcast 312	java/net/HttpURLConnection
    //   23: astore_0
    //   24: aload_0
    //   25: sipush 30000
    //   28: invokevirtual 315	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   31: aload_0
    //   32: sipush 30000
    //   35: invokevirtual 318	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   38: aload_0
    //   39: ldc_w 320
    //   42: invokevirtual 323	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   45: aload_0
    //   46: iconst_1
    //   47: invokevirtual 327	java/net/HttpURLConnection:setDoInput	(Z)V
    //   50: aload_0
    //   51: iconst_1
    //   52: invokevirtual 330	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   55: aload_0
    //   56: ldc_w 332
    //   59: ldc_w 334
    //   62: invokevirtual 338	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   65: aload_0
    //   66: ldc_w 340
    //   69: ldc_w 342
    //   72: invokevirtual 338	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   75: aload_0
    //   76: ldc_w 344
    //   79: ldc_w 346
    //   82: invokevirtual 338	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   85: aload_0
    //   86: invokevirtual 349	java/net/HttpURLConnection:connect	()V
    //   89: aload_0
    //   90: invokevirtual 353	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   93: astore_2
    //   94: new 355	java/io/ByteArrayOutputStream
    //   97: dup
    //   98: invokespecial 356	java/io/ByteArrayOutputStream:<init>	()V
    //   101: astore_3
    //   102: aload_1
    //   103: iconst_0
    //   104: invokevirtual 359	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   107: checkcast 165	java/lang/String
    //   110: astore 4
    //   112: aload_1
    //   113: iconst_1
    //   114: invokevirtual 359	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   117: checkcast 165	java/lang/String
    //   120: astore 5
    //   122: aload_1
    //   123: iconst_2
    //   124: invokevirtual 359	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   127: checkcast 165	java/lang/String
    //   130: astore 6
    //   132: aload_1
    //   133: iconst_3
    //   134: invokevirtual 359	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   137: checkcast 165	java/lang/String
    //   140: astore_1
    //   141: aload 4
    //   143: ifnull +42 -> 185
    //   146: aload_3
    //   147: ldc_w 361
    //   150: invokevirtual 256	java/lang/String:getBytes	()[B
    //   153: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   156: aload_3
    //   157: ldc_w 367
    //   160: invokevirtual 256	java/lang/String:getBytes	()[B
    //   163: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   166: aload_3
    //   167: aload 4
    //   169: invokevirtual 256	java/lang/String:getBytes	()[B
    //   172: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   175: aload_3
    //   176: ldc_w 369
    //   179: invokevirtual 256	java/lang/String:getBytes	()[B
    //   182: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   185: aload 5
    //   187: ifnull +42 -> 229
    //   190: aload_3
    //   191: ldc_w 371
    //   194: invokevirtual 256	java/lang/String:getBytes	()[B
    //   197: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   200: aload_3
    //   201: ldc_w 367
    //   204: invokevirtual 256	java/lang/String:getBytes	()[B
    //   207: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   210: aload_3
    //   211: aload 5
    //   213: invokevirtual 256	java/lang/String:getBytes	()[B
    //   216: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   219: aload_3
    //   220: ldc_w 369
    //   223: invokevirtual 256	java/lang/String:getBytes	()[B
    //   226: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   229: aload 6
    //   231: ifnull +42 -> 273
    //   234: aload_3
    //   235: ldc_w 373
    //   238: invokevirtual 256	java/lang/String:getBytes	()[B
    //   241: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   244: aload_3
    //   245: ldc_w 367
    //   248: invokevirtual 256	java/lang/String:getBytes	()[B
    //   251: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   254: aload_3
    //   255: aload 6
    //   257: invokevirtual 256	java/lang/String:getBytes	()[B
    //   260: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   263: aload_3
    //   264: ldc_w 369
    //   267: invokevirtual 256	java/lang/String:getBytes	()[B
    //   270: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   273: aload_1
    //   274: ifnull +31 -> 305
    //   277: aload_3
    //   278: ldc_w 375
    //   281: invokevirtual 256	java/lang/String:getBytes	()[B
    //   284: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   287: aload_3
    //   288: ldc_w 367
    //   291: invokevirtual 256	java/lang/String:getBytes	()[B
    //   294: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   297: aload_3
    //   298: aload_1
    //   299: invokevirtual 256	java/lang/String:getBytes	()[B
    //   302: invokevirtual 365	java/io/ByteArrayOutputStream:write	([B)V
    //   305: aload_2
    //   306: aload_3
    //   307: invokevirtual 378	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   310: invokevirtual 381	java/io/OutputStream:write	([B)V
    //   313: aload_0
    //   314: invokestatic 384	com/google/android/c2dm/a:a	(Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   317: astore_1
    //   318: aload_2
    //   319: ifnull +7 -> 326
    //   322: aload_2
    //   323: invokevirtual 387	java/io/OutputStream:close	()V
    //   326: aload_3
    //   327: invokevirtual 388	java/io/ByteArrayOutputStream:close	()V
    //   330: aload_0
    //   331: ifnull +7 -> 338
    //   334: aload_0
    //   335: invokevirtual 391	java/net/HttpURLConnection:disconnect	()V
    //   338: aload_1
    //   339: areturn
    //   340: astore_0
    //   341: aconst_null
    //   342: astore_2
    //   343: aconst_null
    //   344: astore_0
    //   345: aconst_null
    //   346: astore_1
    //   347: aload_1
    //   348: ifnull +7 -> 355
    //   351: aload_1
    //   352: invokevirtual 387	java/io/OutputStream:close	()V
    //   355: aload_0
    //   356: ifnull +7 -> 363
    //   359: aload_0
    //   360: invokevirtual 388	java/io/ByteArrayOutputStream:close	()V
    //   363: aload_2
    //   364: ifnull +7 -> 371
    //   367: aload_2
    //   368: invokevirtual 391	java/net/HttpURLConnection:disconnect	()V
    //   371: aconst_null
    //   372: areturn
    //   373: astore_0
    //   374: aconst_null
    //   375: astore_3
    //   376: aconst_null
    //   377: astore_2
    //   378: aload 4
    //   380: astore_1
    //   381: aload_2
    //   382: ifnull +7 -> 389
    //   385: aload_2
    //   386: invokevirtual 387	java/io/OutputStream:close	()V
    //   389: aload_3
    //   390: ifnull +7 -> 397
    //   393: aload_3
    //   394: invokevirtual 388	java/io/ByteArrayOutputStream:close	()V
    //   397: aload_1
    //   398: ifnull +7 -> 405
    //   401: aload_1
    //   402: invokevirtual 391	java/net/HttpURLConnection:disconnect	()V
    //   405: aload_0
    //   406: athrow
    //   407: astore_2
    //   408: goto -82 -> 326
    //   411: astore_2
    //   412: goto -82 -> 330
    //   415: astore_1
    //   416: goto -61 -> 355
    //   419: astore_0
    //   420: goto -57 -> 363
    //   423: astore_2
    //   424: goto -35 -> 389
    //   427: astore_2
    //   428: goto -31 -> 397
    //   431: astore 4
    //   433: aconst_null
    //   434: astore_3
    //   435: aconst_null
    //   436: astore_2
    //   437: aload_0
    //   438: astore_1
    //   439: aload 4
    //   441: astore_0
    //   442: goto -61 -> 381
    //   445: astore 4
    //   447: aconst_null
    //   448: astore_3
    //   449: aload_0
    //   450: astore_1
    //   451: aload 4
    //   453: astore_0
    //   454: goto -73 -> 381
    //   457: astore 4
    //   459: aload_0
    //   460: astore_1
    //   461: aload 4
    //   463: astore_0
    //   464: goto -83 -> 381
    //   467: astore_1
    //   468: aconst_null
    //   469: astore_3
    //   470: aconst_null
    //   471: astore_1
    //   472: aload_0
    //   473: astore_2
    //   474: aload_3
    //   475: astore_0
    //   476: goto -129 -> 347
    //   479: astore_1
    //   480: aconst_null
    //   481: astore_3
    //   482: aload_2
    //   483: astore_1
    //   484: aload_0
    //   485: astore_2
    //   486: aload_3
    //   487: astore_0
    //   488: goto -141 -> 347
    //   491: astore_1
    //   492: aload_2
    //   493: astore_1
    //   494: aload_0
    //   495: astore_2
    //   496: aload_3
    //   497: astore_0
    //   498: goto -151 -> 347
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	501	0	paramString	String
    //   0	501	1	paramArrayList	ArrayList
    //   93	293	2	localOutputStream	OutputStream
    //   407	1	2	localIOException1	IOException
    //   411	1	2	localIOException2	IOException
    //   423	1	2	localIOException3	IOException
    //   427	1	2	localIOException4	IOException
    //   436	60	2	str1	String
    //   101	396	3	localByteArrayOutputStream	ByteArrayOutputStream
    //   1	378	4	str2	String
    //   431	9	4	localObject1	Object
    //   445	7	4	localObject2	Object
    //   457	5	4	localObject3	Object
    //   120	92	5	str3	String
    //   130	126	6	str4	String
    // Exception table:
    //   from	to	target	type
    //   9	85	340	java/lang/Throwable
    //   9	85	373	finally
    //   322	326	407	java/io/IOException
    //   326	330	411	java/io/IOException
    //   351	355	415	java/io/IOException
    //   359	363	419	java/io/IOException
    //   385	389	423	java/io/IOException
    //   393	397	427	java/io/IOException
    //   85	94	431	finally
    //   94	102	445	finally
    //   102	141	457	finally
    //   146	185	457	finally
    //   190	229	457	finally
    //   234	273	457	finally
    //   277	305	457	finally
    //   305	318	457	finally
    //   85	94	467	java/lang/Throwable
    //   94	102	479	java/lang/Throwable
    //   102	141	491	java/lang/Throwable
    //   146	185	491	java/lang/Throwable
    //   190	229	491	java/lang/Throwable
    //   234	273	491	java/lang/Throwable
    //   277	305	491	java/lang/Throwable
    //   305	318	491	java/lang/Throwable
  }
  
  private static String a(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      if (paramHttpURLConnection.getResponseCode() >= 400) {}
      StringBuilder localStringBuilder;
      for (Object localObject = paramHttpURLConnection.getErrorStream();; localObject = paramHttpURLConnection.getInputStream())
      {
        localObject = new BufferedReader(new InputStreamReader((InputStream)localObject));
        localStringBuilder = new StringBuilder();
        for (;;)
        {
          String str = ((BufferedReader)localObject).readLine();
          if (str == null) {
            break;
          }
          localStringBuilder.append(str);
        }
      }
      localObject = localStringBuilder.toString();
      paramHttpURLConnection.disconnect();
      return localObject;
    }
    catch (Exception paramHttpURLConnection) {}
    return null;
  }
  
  public static ArrayList a(String paramString, ArrayList paramArrayList1, ArrayList paramArrayList2, fm paramFm)
  {
    if ((paramArrayList1 == null) || (paramArrayList1.size() == 0)) {
      return paramArrayList2;
    }
    ArrayList localArrayList = paramArrayList2;
    if (paramArrayList2 == null) {
      localArrayList = new ArrayList();
    }
    if ((pf.a(paramString)) || (paramFm == null))
    {
      localArrayList.addAll(paramArrayList1);
      return localArrayList;
    }
    paramArrayList2 = new ArrayList();
    paramArrayList1 = paramArrayList1.iterator();
    do
    {
      kr localKr;
      do
      {
        if (!paramArrayList1.hasNext()) {
          break;
        }
        localKr = (kr)paramArrayList1.next();
      } while (!paramFm.a(paramString, localKr));
      paramArrayList2.add(localKr);
    } while (paramArrayList2.size() < 5);
    if (localArrayList.size() > 0)
    {
      int i = paramArrayList2.size() - 1;
      if (i >= 0)
      {
        int j = localArrayList.size() - 1;
        for (;;)
        {
          if (j >= 0)
          {
            if (paramFm.a((kr)paramArrayList2.get(i), (kr)localArrayList.get(j))) {
              localArrayList.remove(j);
            }
          }
          else
          {
            i -= 1;
            break;
          }
          j -= 1;
        }
      }
    }
    localArrayList.addAll(paramArrayList2);
    return localArrayList;
  }
  
  public static oh a(oi paramOi)
  {
    try
    {
      switch (paramOi.a())
      {
      case 1: 
        oh localOh = new oh();
        try
        {
          localOh.a(paramOi);
          return localOh;
        }
        catch (Exception paramOi)
        {
          return localOh;
        }
      }
      return null;
    }
    catch (Exception paramOi)
    {
      return null;
    }
  }
  
  public static void a(Context paramContext, byte paramByte)
  {
    paramContext = g(paramContext);
    try
    {
      com.uc.util.a.a(new byte[] { paramByte }, paramContext);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static void a(Context paramContext, AbsListView paramAbsListView)
  {
    if (Build.VERSION.SDK_INT > 8) {
      try
      {
        if (e == null) {
          e = ((BitmapDrawable)paramContext.getResources().getDrawable(2130837512)).getBitmap();
        }
        if (f == null) {
          f = ((BitmapDrawable)paramContext.getResources().getDrawable(2130837513)).getBitmap();
        }
        paramContext = AbsListView.class.getDeclaredField("mEdgeGlowTop");
        paramContext.setAccessible(true);
        paramContext = paramContext.get(paramAbsListView);
        if (paramContext == null) {
          return;
        }
        a(paramContext);
        paramContext = AbsListView.class.getDeclaredField("mEdgeGlowBottom");
        paramContext.setAccessible(true);
        paramContext = paramContext.get(paramAbsListView);
        if (paramContext != null)
        {
          a(paramContext);
          return;
        }
      }
      catch (Throwable paramContext) {}
    }
  }
  
  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent.setPackage("com.google.android.gsf");
    localIntent.putExtra("app", PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
    localIntent.putExtra("sender", paramString);
    paramContext.startService(localIntent);
  }
  
  public static void a(Context paramContext, String paramString, Parcelable paramParcelable, Intent paramIntent)
  {
    if ((paramContext == null) || (pf.a(paramString)) || (paramParcelable == null)) {
      return;
    }
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent.putExtra("duplicate", false);
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramString);
    if ((paramParcelable instanceof Bitmap)) {
      localIntent.putExtra("android.intent.extra.shortcut.ICON", paramParcelable);
    }
    for (;;)
    {
      localIntent.putExtra("android.intent.extra.shortcut.INTENT", paramIntent);
      paramContext.sendBroadcast(localIntent);
      return;
      localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", paramParcelable);
    }
  }
  
  public static void a(View paramView)
  {
    if (Build.VERSION.SDK_INT > 8) {}
    try
    {
      Method localMethod = View.class.getDeclaredMethod("setOverScrollMode", new Class[] { Integer.TYPE });
      localMethod.setAccessible(true);
      Field localField = View.class.getDeclaredField("OVER_SCROLL_NEVER");
      localField.setAccessible(true);
      localMethod.invoke(paramView, new Object[] { Integer.valueOf(((Integer)localField.get(null)).intValue()) });
      return;
    }
    catch (Exception paramView) {}
  }
  
  public static void a(View paramView, int paramInt)
  {
    if (paramView == null) {
      return;
    }
    a(paramView, ts.b().d(paramInt));
  }
  
  public static void a(View paramView, Drawable paramDrawable)
  {
    if (paramView == null) {
      return;
    }
    int i = paramView.getPaddingLeft();
    int j = paramView.getPaddingRight();
    int k = paramView.getPaddingTop();
    int m = paramView.getPaddingBottom();
    paramView.setBackgroundDrawable(paramDrawable);
    paramView.setPadding(i, k, j, m);
  }
  
  private static void a(DataInputStream paramDataInputStream, om paramOm)
  {
    if (paramOm.f()) {
      b(paramDataInputStream, paramOm);
    }
    pr localPr;
    do
    {
      return;
      localPr = new pr(paramDataInputStream, a(paramDataInputStream));
      b(new DataInputStream(localPr), paramOm);
    } while (localPr.a <= 0);
    paramDataInputStream.skip(localPr.a);
  }
  
  public static void a(InputStream paramInputStream, om paramOm)
  {
    try
    {
      b(new DataInputStream(paramInputStream), paramOm);
      return;
    }
    catch (Exception paramInputStream) {}
  }
  
  public static void a(OutputStream paramOutputStream)
  {
    if (paramOutputStream != null) {}
    try
    {
      paramOutputStream.close();
      return;
    }
    catch (Exception paramOutputStream) {}
  }
  
  private static void a(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return;
      try
      {
        if (("android.widget.EdgeEffect".equals(paramObject.getClass().getName())) || ("android.widget.EdgeGlow".equals(paramObject.getClass().getName())))
        {
          Method localMethod = BitmapDrawable.class.getDeclaredMethod("setBitmap", new Class[] { Bitmap.class });
          if (localMethod != null)
          {
            localMethod.setAccessible(true);
            Object localObject = paramObject.getClass().getDeclaredField("mEdge");
            ((Field)localObject).setAccessible(true);
            localObject = ((Field)localObject).get(paramObject);
            if (localObject != null)
            {
              if ((localObject != null) && ((localObject instanceof BitmapDrawable))) {
                localMethod.invoke(localObject, new Object[] { e });
              }
              Field localField = paramObject.getClass().getDeclaredField("mGlow");
              if (localField != null)
              {
                localField.setAccessible(true);
                paramObject = localField.get(paramObject);
                if ((paramObject != null) && (paramObject != null) && ((localObject instanceof BitmapDrawable)))
                {
                  localMethod.invoke(paramObject, new Object[] { f });
                  return;
                }
              }
            }
          }
        }
      }
      catch (Exception paramObject) {}
    }
  }
  
  public static void a(String paramString1, String paramString2, String paramString3)
  {
    if (pf.a(paramString1)) {
      return;
    }
    int i = 1;
    if (paramString1.startsWith("rtsp://")) {
      i = 3;
    }
    a(paramString1, paramString2, paramString3, null, i, 2, 2, false);
  }
  
  public static void a(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    a(paramString1, paramString2, paramString3, null, 3, paramInt1, paramInt2, false);
  }
  
  private static void a(String paramString1, String paramString2, String paramString3, int paramInt1, String paramString4, int paramInt2, int paramInt3, String paramString5, String paramString6, int paramInt4, boolean paramBoolean)
  {
    if (b.a(paramString2)) {
      return;
    }
    if (paramInt2 == 3) {}
    try
    {
      String str = hz.a().b(paramString1);
      int i;
      if ((b.b(str)) && (!str.equals(paramString2)))
      {
        lb.b("vps_1");
        paramInt2 = 5;
        paramInt1 = ac.a().c(str);
        i = 0;
        paramBoolean = false;
      }
      for (;;)
      {
        o.f();
        o.a(nv.ds, Boolean.valueOf(false));
        Activity localActivity = o.f().a();
        Intent localIntent = new Intent(localActivity, UcMediaPlayerActivity.class);
        localIntent.setFlags(131072);
        boolean bool = paramBoolean;
        if (pf.b(str))
        {
          bool = paramBoolean;
          if (str.toLowerCase().contains(".m3u8")) {
            bool = false;
          }
        }
        ac.a();
        ab localAb = new ab();
        localAb.g(paramString3);
        localAb.f(paramInt1);
        localAb.e(paramString4);
        localAb.d(paramInt2);
        localAb.e(paramInt3);
        localAb.d(paramString5);
        localAb.c(paramString6);
        localAb.d(iu.a().f());
        localAb.b(paramString1);
        localAb.b(i);
        localAb.a(paramInt4);
        localAb.a(bool);
        localAb.a(paramString2);
        localAb.b(kx.a().g(paramString1));
        localAb.h(kg.e().a());
        localAb.i(ss.d());
        localAb.j(qm.l);
        localAb.k(hz.b());
        localAb.c(iu.a().d().o());
        ac.a(localIntent, str, localAb);
        ks.a(localActivity, localIntent, 12);
        return;
        lb.b("v17");
        if (kx.a().f(paramString1))
        {
          if (hz.a().c() == 1)
          {
            lb.b("vps_3");
            i = -1;
            str = paramString2;
            paramBoolean = false;
          }
          else
          {
            lb.b("vps_2");
          }
        }
        else
        {
          i = -1;
          str = paramString2;
          paramBoolean = false;
          continue;
          i = -1;
          str = paramString2;
        }
      }
      return;
    }
    catch (Exception paramString1) {}
  }
  
  public static void a(String paramString1, String paramString2, String paramString3, int paramInt, String[] paramArrayOfString)
  {
    int k = 2;
    if (pf.a(paramString1)) {
      return;
    }
    if (paramString1.startsWith("rtsp://")) {}
    for (int j = 3;; j = 2)
    {
      int i;
      if (paramInt == 1) {
        i = 1;
      }
      for (;;)
      {
        a(paramString1, paramString2, paramString3, paramArrayOfString, j, paramInt, i, true);
        return;
        i = k;
        if (paramInt != 2)
        {
          i = k;
          if (paramInt != 3) {
            i = 1;
          }
        }
      }
    }
  }
  
  private static void a(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = ac.a().c(paramString2);
    Object localObject4 = null;
    Object localObject5 = null;
    Object localObject6 = null;
    Object localObject3 = localObject4;
    Object localObject2 = localObject5;
    Object localObject1 = localObject6;
    if (paramArrayOfString != null)
    {
      localObject3 = localObject4;
      localObject2 = localObject5;
      localObject1 = localObject6;
      if (paramArrayOfString.length >= 7)
      {
        localObject3 = paramArrayOfString[7];
        localObject2 = paramArrayOfString[1];
        localObject1 = paramArrayOfString[2];
      }
    }
    a(paramString1, paramString2, paramString3, i, (String)localObject3, paramInt1, paramInt2, (String)localObject2, (String)localObject1, paramInt3, paramBoolean);
  }
  
  public static void a(xf paramXf)
  {
    if (paramXf != null) {}
    try
    {
      paramXf.a();
      return;
    }
    catch (Exception paramXf) {}
  }
  
  public static void a(si[] paramArrayOfSi, ContextMenu paramContextMenu)
  {
    if ((paramArrayOfSi == null) || (paramArrayOfSi.length == 0)) {}
    for (;;)
    {
      return;
      ts.b();
      int j = paramArrayOfSi.length;
      int i = 0;
      while (i < j)
      {
        si localSi = paramArrayOfSi[i];
        if (localSi.a != null) {
          paramContextMenu.add(localSi.c, localSi.b, 0, localSi.a).setIcon(null);
        }
        i += 1;
      }
    }
  }
  
  public static boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt2 != 0) && (paramInt1 == (paramInt1 | paramInt2));
  }
  
  public static boolean a(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (pf.a(paramString)) {
      return false;
    }
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    if (paramBoolean)
    {
      paramString.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
      paramString.setFlags(268435456);
    }
    try
    {
      paramContext.startActivity(paramString);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean a(View paramView1, View paramView2)
  {
    if ((paramView1 == null) || (paramView2 == null)) {
      return false;
    }
    int[] arrayOfInt1 = new int[2];
    paramView1.getLocationOnScreen(arrayOfInt1);
    int[] arrayOfInt2 = new int[2];
    paramView2.getLocationOnScreen(arrayOfInt2);
    return new Rect(arrayOfInt1[0], arrayOfInt1[1], arrayOfInt1[0] + paramView1.getWidth(), arrayOfInt1[1] + paramView1.getHeight()).intersect(new Rect(arrayOfInt2[0], arrayOfInt2[1], arrayOfInt2[0] + paramView2.getWidth(), arrayOfInt2[1] + paramView2.getHeight()));
  }
  
  /* Error */
  public static byte[] a(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 355	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: sipush 2048
    //   7: invokespecial 921	java/io/ByteArrayOutputStream:<init>	(I)V
    //   10: astore_2
    //   11: sipush 2048
    //   14: newarray byte
    //   16: astore_3
    //   17: aload_0
    //   18: aload_3
    //   19: iconst_0
    //   20: sipush 2048
    //   23: invokevirtual 927	java/io/InputStream:read	([BII)I
    //   26: istore_1
    //   27: iload_1
    //   28: iflt +46 -> 74
    //   31: aload_2
    //   32: aload_3
    //   33: iconst_0
    //   34: iload_1
    //   35: invokevirtual 930	java/io/ByteArrayOutputStream:write	([BII)V
    //   38: goto -21 -> 17
    //   41: astore_0
    //   42: getstatic 935	lf:b	Llg;
    //   45: new 152	java/lang/StringBuilder
    //   48: dup
    //   49: ldc_w 937
    //   52: invokespecial 938	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   55: aload_0
    //   56: invokevirtual 941	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   59: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: putfield 945	lg:h	Ljava/lang/String;
    //   68: aload_2
    //   69: invokestatic 947	com/google/android/c2dm/a:a	(Ljava/io/OutputStream;)V
    //   72: aconst_null
    //   73: areturn
    //   74: aload_2
    //   75: invokevirtual 378	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   78: astore_0
    //   79: aload_2
    //   80: invokestatic 947	com/google/android/c2dm/a:a	(Ljava/io/OutputStream;)V
    //   83: aload_0
    //   84: areturn
    //   85: astore_0
    //   86: aload_2
    //   87: invokestatic 947	com/google/android/c2dm/a:a	(Ljava/io/OutputStream;)V
    //   90: aload_0
    //   91: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	92	0	paramInputStream	InputStream
    //   26	9	1	i	int
    //   10	77	2	localByteArrayOutputStream	ByteArrayOutputStream
    //   16	17	3	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   11	17	41	java/lang/Exception
    //   17	27	41	java/lang/Exception
    //   31	38	41	java/lang/Exception
    //   74	79	41	java/lang/Exception
    //   11	17	85	finally
    //   17	27	85	finally
    //   31	38	85	finally
    //   42	68	85	finally
    //   74	79	85	finally
  }
  
  public static byte[] a(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
      return null;
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte, 0, paramArrayOfByte.length);
      paramArrayOfByte = localMessageDigest.digest();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte) {}
    return null;
  }
  
  public static byte[] a(byte[] paramArrayOfByte, InputStream paramInputStream, int paramInt)
  {
    int j = 0;
    localByteArrayOutputStream = new ByteArrayOutputStream();
    if (paramArrayOfByte != null) {
      localByteArrayOutputStream.write(paramArrayOfByte);
    }
    paramArrayOfByte = new byte['Ѐ'];
    int i = j;
    if (1024 > paramInt)
    {
      paramArrayOfByte = new byte[paramInt];
      i = j;
    }
    try
    {
      for (;;)
      {
        int k = paramInputStream.read(paramArrayOfByte);
        if ((-1 == k) || (i >= paramInt)) {
          break;
        }
        j = i + k;
        localByteArrayOutputStream.write(paramArrayOfByte, 0, k);
        i = j;
        if (paramArrayOfByte.length + j > paramInt)
        {
          paramArrayOfByte = new byte[paramInt - j];
          i = j;
        }
      }
      return localByteArrayOutputStream.toByteArray();
    }
    catch (Exception paramArrayOfByte) {}
  }
  
  private static long b(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readByte();
    int j = paramDataInputStream.readByte();
    int k = paramDataInputStream.readByte();
    int m = paramDataInputStream.readByte();
    int n = paramDataInputStream.readByte();
    int i1 = paramDataInputStream.readByte();
    int i2 = paramDataInputStream.readByte();
    int i3 = paramDataInputStream.readByte();
    long l = i;
    return (j & 0xFF) << 8 | l & 0xFF | (k & 0xFF) << 16 | (m & 0xFF) << 24 | (n & 0xFF) << 32 | (i1 & 0xFF) << 40 | (i2 & 0xFF) << 48 | (i3 & 0xFF) << 56;
  }
  
  static void b(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("com.google.android.c2dm", 0).edit();
    paramContext.putString("dm_registration", "");
    paramContext.putLong("last_registration_change", System.currentTimeMillis());
    paramContext.commit();
  }
  
  public static void b(View paramView)
  {
    if (paramView == null) {}
    Object localObject1;
    Drawable localDrawable;
    do
    {
      return;
      localObject1 = ts.b().d(10283);
      localDrawable = ts.b().d(10284);
    } while (paramView == null);
    try
    {
      Object localObject2 = View.class.getDeclaredField("mScrollCache");
      ((Field)localObject2).setAccessible(true);
      paramView = ((Field)localObject2).get(paramView);
      localObject2 = paramView.getClass().getDeclaredField("scrollBar");
      ((Field)localObject2).setAccessible(true);
      paramView = ((Field)localObject2).get(paramView);
      localObject2 = paramView.getClass().getDeclaredMethod("setHorizontalThumbDrawable", new Class[] { Drawable.class });
      ((Method)localObject2).setAccessible(true);
      ((Method)localObject2).invoke(paramView, new Object[] { localObject1 });
      localObject1 = paramView.getClass().getDeclaredMethod("setVerticalThumbDrawable", new Class[] { Drawable.class });
      ((Method)localObject1).setAccessible(true);
      ((Method)localObject1).invoke(paramView, new Object[] { localDrawable });
      return;
    }
    catch (Exception paramView) {}
  }
  
  private static void b(DataInputStream paramDataInputStream, om paramOm)
  {
    for (;;)
    {
      int j = c(paramDataInputStream);
      if (j == 0) {
        break;
      }
      Object localObject3 = paramOm.e(j >> 3);
      Object localObject1 = localObject3;
      if (localObject3 != null) {
        localObject1 = localObject3;
      }
      int i;
      Object localObject2;
      try
      {
        if (((ok)localObject3).f()) {
          localObject1 = ((ol)localObject3).p();
        }
        if (localObject1 != null) {}
        switch (((ok)localObject1).e())
        {
        default: 
          i = -1;
          if ((j & 0x7) == i) {
            break label254;
          }
          switch (j & 0x7)
          {
          case 3: 
          case 4: 
          default: 
            throw new IOException();
          }
          break;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localObject2 = localObject3;
          continue;
          i = 0;
          continue;
          i = 2;
          continue;
          i = 5;
          continue;
          i = 1;
        }
        a(paramDataInputStream);
      }
      continue;
      b(paramDataInputStream);
      continue;
      paramDataInputStream.skip(a(paramDataInputStream));
      continue;
      d(paramDataInputStream);
      continue;
      label254:
      switch (localObject2.e())
      {
      case 6: 
      case 7: 
      case 8: 
      default: 
        break;
      case 1: 
      case 2: 
        localObject2.a(Integer.valueOf(a(paramDataInputStream)));
        break;
      case 14: 
        a(paramDataInputStream, (om)localObject2);
        break;
      case 12: 
      case 13: 
        localObject3 = new byte[a(paramDataInputStream)];
        paramDataInputStream.readFully((byte[])localObject3, 0, localObject3.length);
        localObject2.a(localObject3);
        break;
      case 3: 
        i = a(paramDataInputStream);
        localObject2.a(Integer.valueOf(-(i & 0x1) ^ i >>> 1));
        break;
      case 4: 
      case 5: 
        localObject2.a(Integer.valueOf(d(paramDataInputStream)));
        break;
      case 11: 
        if (a(paramDataInputStream) != 0) {}
        for (boolean bool = true;; bool = false)
        {
          localObject2.a(Boolean.valueOf(bool));
          break;
        }
      case 9: 
      case 10: 
        localObject2.a(Long.valueOf(b(paramDataInputStream)));
      }
    }
  }
  
  public static void b(InputStream paramInputStream)
  {
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
      return;
    }
    catch (Exception paramInputStream) {}
  }
  
  public static void b(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    ab localAb;
    do
    {
      return;
      localAb = ac.a().e(paramString);
    } while (localAb == null);
    a(paramString, localAb.q(), localAb.r(), localAb.p(), localAb.o(), localAb.t(), localAb.u(), localAb.n(), localAb.m(), localAb.f(), localAb.c());
    lb.b("v08");
  }
  
  public static void b(String paramString1, String paramString2, String paramString3)
  {
    a(paramString1, paramString2, paramString3, null, 4, 0, 0, false);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    for (;;)
    {
      try
      {
        Object localObject = paramContext.getContentResolver();
        Uri localUri = f(paramContext);
        paramString = "intent like '%" + paramString + "%'";
        paramString = ((ContentResolver)localObject).query(localUri, new String[] { "intent" }, paramString, null, null);
        if (paramString == null) {
          break;
        }
        paramString.moveToFirst();
        int j = paramString.getCount();
        int i = 0;
        boolean bool;
        if (i < j)
        {
          localObject = paramString.getString(paramString.getColumnIndex("intent"));
          if (!TextUtils.isEmpty((CharSequence)localObject))
          {
            if (!((String)localObject).contains(paramContext.getPackageName()))
            {
              bool = ((String)localObject).contains("com.uc.browser.intent.action.LOADURL");
              if (!bool) {}
            }
            else
            {
              bool = true;
              try
              {
                paramString.close();
                return bool;
              }
              catch (Exception paramContext)
              {
                return bool;
              }
            }
            paramString.moveToNext();
          }
          i += 1;
        }
        else
        {
          bool = false;
        }
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
    return false;
  }
  
  public static boolean b(String paramString1, String paramString2)
  {
    paramString1 = k(paramString1);
    paramString2 = k(paramString2);
    if ((pf.b(paramString1)) && (pf.b(paramString2))) {
      return paramString1.equals(paramString2);
    }
    return false;
  }
  
  public static byte[] b()
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    localByteArrayOutputStream.write(13);
    localByteArrayOutputStream.write(10);
    try
    {
      localByteArrayOutputStream.write("-----Ajzlizbchdz".getBytes());
      localByteArrayOutputStream.write(13);
      localByteArrayOutputStream.write(10);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      try
      {
        localByteArrayOutputStream.close();
        return arrayOfByte;
      }
      catch (IOException localIOException1)
      {
        return arrayOfByte;
      }
    }
    catch (IOException localIOException2)
    {
      for (;;) {}
    }
  }
  
  public static byte[] b(byte[] paramArrayOfByte)
  {
    Object localObject2 = null;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
      return null;
    }
    Object localObject1 = localObject2;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject1 = localObject2;
      DeflaterOutputStream localDeflaterOutputStream = new DeflaterOutputStream(localByteArrayOutputStream);
      localObject1 = localObject2;
      localDeflaterOutputStream.write(paramArrayOfByte);
      localObject1 = localObject2;
      localDeflaterOutputStream.close();
      localObject1 = localObject2;
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      localObject1 = paramArrayOfByte;
      localByteArrayOutputStream.close();
      return paramArrayOfByte;
    }
    catch (Exception paramArrayOfByte) {}
    return localObject1;
  }
  
  private static int c(DataInputStream paramDataInputStream)
  {
    try
    {
      int i = a(paramDataInputStream);
      return i;
    }
    catch (IOException paramDataInputStream) {}
    return 0;
  }
  
  public static int c(String paramString)
  {
    try
    {
      paramString = new ExifInterface(paramString);
      int i;
      if (paramString != null)
      {
        i = paramString.getAttributeInt("Orientation", 0);
        if (i == 0) {}
      }
      switch (i)
      {
      case 4: 
      case 5: 
      case 7: 
      default: 
        return 0;
      }
    }
    catch (IOException paramString)
    {
      for (;;)
      {
        paramString = null;
      }
      return 90;
    }
    return 180;
    return 270;
  }
  
  public static Drawable c()
  {
    return new ColorDrawable(-2013265920);
  }
  
  private static String c(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (!paramString1.equals(""))
    {
      localObject1 = localObject2;
      if (paramString2 != null)
      {
        if (!paramString2.equals("")) {
          break label36;
        }
        localObject1 = localObject2;
      }
    }
    label36:
    int i;
    do
    {
      do
      {
        return localObject1;
        paramString1 = paramString1 + "=";
        i = paramString2.indexOf(paramString1);
        localObject1 = localObject2;
      } while (i < 0);
      paramString1 = paramString2.substring(paramString1.length() + i);
      i = u(paramString1);
      localObject1 = paramString1;
    } while (i < 0);
    return paramString1.substring(0, i);
  }
  
  /* Error */
  public static boolean c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +196 -> 197
    //   4: aload_0
    //   5: invokevirtual 1067	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   8: astore 5
    //   10: aload_0
    //   11: invokevirtual 1097	android/content/Context:getPackageName	()Ljava/lang/String;
    //   14: astore 6
    //   16: new 1141	android/content/ComponentName
    //   19: dup
    //   20: aload 6
    //   22: ldc_w 1143
    //   25: invokevirtual 659	java/lang/Class:getName	()Ljava/lang/String;
    //   28: invokespecial 1145	android/content/ComponentName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: astore 4
    //   33: aload_0
    //   34: invokestatic 1070	com/google/android/c2dm/a:f	(Landroid/content/Context;)Landroid/net/Uri;
    //   37: astore_0
    //   38: new 152	java/lang/StringBuilder
    //   41: dup
    //   42: ldc_w 1072
    //   45: invokespecial 938	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: aload 6
    //   50: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: ldc_w 1074
    //   56: invokevirtual 157	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: astore 6
    //   64: aload 5
    //   66: aload_0
    //   67: iconst_1
    //   68: anewarray 165	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: ldc_w 1076
    //   76: aastore
    //   77: aload 6
    //   79: aconst_null
    //   80: aconst_null
    //   81: invokevirtual 1082	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   84: astore_0
    //   85: aload_0
    //   86: ifnull +111 -> 197
    //   89: aload_0
    //   90: invokeinterface 1090 1 0
    //   95: istore_2
    //   96: aload_0
    //   97: invokeinterface 1087 1 0
    //   102: pop
    //   103: iconst_0
    //   104: istore_1
    //   105: iload_1
    //   106: iload_2
    //   107: if_icmpge +85 -> 192
    //   110: aload_0
    //   111: aload_0
    //   112: ldc_w 1076
    //   115: invokeinterface 1093 2 0
    //   120: invokeinterface 1094 2 0
    //   125: astore 5
    //   127: aload 5
    //   129: invokestatic 150	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   132: ifne +47 -> 179
    //   135: aload 5
    //   137: iconst_0
    //   138: invokestatic 1149	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   141: astore 5
    //   143: aload 5
    //   145: ifnull +34 -> 179
    //   148: aload 4
    //   150: invokevirtual 1150	android/content/ComponentName:toString	()Ljava/lang/String;
    //   153: aload 5
    //   155: invokevirtual 1154	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   158: invokevirtual 1150	android/content/ComponentName:toString	()Ljava/lang/String;
    //   161: invokevirtual 662	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   164: istore_3
    //   165: iload_3
    //   166: ifeq +13 -> 179
    //   169: iconst_1
    //   170: istore_3
    //   171: aload_0
    //   172: invokeinterface 1100 1 0
    //   177: iload_3
    //   178: ireturn
    //   179: iload_1
    //   180: iconst_1
    //   181: iadd
    //   182: istore_1
    //   183: goto -78 -> 105
    //   186: astore_0
    //   187: iconst_0
    //   188: ireturn
    //   189: astore_0
    //   190: iload_3
    //   191: ireturn
    //   192: iconst_0
    //   193: istore_3
    //   194: goto -23 -> 171
    //   197: iconst_0
    //   198: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	paramContext	Context
    //   104	79	1	i	int
    //   95	13	2	j	int
    //   164	30	3	bool	boolean
    //   31	118	4	localComponentName	android.content.ComponentName
    //   8	146	5	localObject	Object
    //   14	64	6	str	String
    // Exception table:
    //   from	to	target	type
    //   4	85	186	java/lang/Exception
    //   89	103	186	java/lang/Exception
    //   110	143	186	java/lang/Exception
    //   148	165	186	java/lang/Exception
    //   171	177	189	java/lang/Exception
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    int i = 0;
    File localFile1 = new File(paramString);
    boolean bool1 = true;
    boolean bool2 = true;
    if (localFile1.isDirectory())
    {
      String[] arrayOfString1 = localFile1.list();
      if (arrayOfString1 != null)
      {
        String[] arrayOfString2 = new String[arrayOfString1.length];
        bool1 = bool2;
        if (i < arrayOfString1.length)
        {
          File localFile2 = new File(localFile1, arrayOfString1[i]);
          if (localFile2.isDirectory()) {
            bool1 &= c(paramContext, paramString);
          }
          for (;;)
          {
            arrayOfString2[i] = (localFile1.getPath() + File.separator + arrayOfString1[i]);
            i += 1;
            break;
            bool1 &= e(paramContext, localFile2.getPath());
          }
        }
        MediaScannerConnection.scanFile(paramContext, arrayOfString2, null, null);
      }
      return bool1;
    }
    return false;
  }
  
  private static int d(DataInputStream paramDataInputStream)
  {
    return paramDataInputStream.readByte() & 0xFF | (paramDataInputStream.readByte() & 0xFF) << 8 | (paramDataInputStream.readByte() & 0xFF) << 16 | (paramDataInputStream.readByte() & 0xFF) << 24;
  }
  
  public static Drawable d()
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    Drawable localDrawable1 = ts.b().d(10303);
    Drawable localDrawable2 = ts.b().d(10302);
    localStateListDrawable.addState(new int[] { 16842912, 16842910 }, localDrawable1);
    localStateListDrawable.addState(new int[0], localDrawable2);
    return localStateListDrawable;
  }
  
  public static Drawable d(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    paramContext = new StateListDrawable();
    Drawable localDrawable1 = ts.b().d(10073);
    Drawable localDrawable2 = ts.b().d(10074);
    paramContext.addState(new int[] { 16842912, 16842910 }, localDrawable1);
    paramContext.addState(new int[0], localDrawable2);
    return paramContext;
  }
  
  public static void d(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return;
    }
    new HashMap();
    try
    {
      str1 = URLDecoder.decode(paramString, "UTF-8");
      paramString = str1;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      String str1;
      String str2;
      for (;;) {}
    }
    str1 = c("utm_source", paramString);
    str2 = c("utm_content", paramString);
    if ((str1 != null) && (!str1.equals(""))) {
      h.a(paramContext, str1);
    }
    for (;;)
    {
      if ((str2 != null) && (!str2.equals(""))) {
        h.b(paramContext, str2);
      }
      h.c(paramContext, 2);
      return;
      h.a(paramContext, paramString);
    }
  }
  
  public static byte[] d(String paramString)
  {
    paramString = paramString.substring(paramString.lastIndexOf('/') + 1, paramString.length());
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
    localDataOutputStream.writeByte(1);
    localDataOutputStream.writeUTF("filename");
    localDataOutputStream.writeUTF(paramString);
    paramString = localByteArrayOutputStream.toByteArray();
    localDataOutputStream.close();
    localByteArrayOutputStream.close();
    localByteArrayOutputStream = new ByteArrayOutputStream();
    localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
    localDataOutputStream.writeShort(paramString.length + 2);
    localDataOutputStream.write(paramString);
    paramString = localByteArrayOutputStream.toByteArray();
    localDataOutputStream.close();
    localByteArrayOutputStream.close();
    return paramString;
  }
  
  public static byte e(Context paramContext)
  {
    String str = g(paramContext);
    File localFile = new File(str);
    try
    {
      if (!localFile.exists())
      {
        a(paramContext, (byte)2);
        return 0;
      }
      byte b1 = com.uc.util.a.f(str)[0];
      return b1;
    }
    catch (Throwable paramContext) {}
    return 0;
  }
  
  public static DisplayMetrics e()
  {
    WindowManager localWindowManager = (WindowManager)d.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    if (localWindowManager != null) {
      localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    }
    return localDisplayMetrics;
  }
  
  private static boolean e(Context paramContext, String paramString)
  {
    boolean bool2 = true;
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    for (;;)
    {
      try
      {
        int i = paramContext.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data=?", new String[] { paramString });
        if (i == 0) {
          continue;
        }
        bool1 = true;
      }
      catch (IllegalArgumentException paramContext)
      {
        bool1 = false;
        continue;
      }
      catch (SQLiteException paramContext)
      {
        boolean bool1 = false;
        continue;
        bool1 = false;
        continue;
        continue;
      }
      if (bool1) {
        continue;
      }
      if (com.uc.util.a.b(new File(paramString)) != 0) {
        continue;
      }
      bool1 = bool2;
      return bool1;
      bool1 = false;
    }
  }
  
  /* Error */
  public static String[] e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 112	pf:a	(Ljava/lang/String;)Z
    //   4: ifne +33 -> 37
    //   7: aload_0
    //   8: ldc_w 1287
    //   11: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   14: ifne +25 -> 39
    //   17: aload_0
    //   18: ldc_w 1289
    //   21: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   24: ifne +15 -> 39
    //   27: aload_0
    //   28: ldc_w 1291
    //   31: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   34: ifne +5 -> 39
    //   37: aconst_null
    //   38: areturn
    //   39: aload_0
    //   40: invokestatic 739	pf:b	(Ljava/lang/String;)Z
    //   43: ifeq +209 -> 252
    //   46: aload_0
    //   47: ldc_w 1287
    //   50: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   53: ifne +13 -> 66
    //   56: aload_0
    //   57: ldc_w 1289
    //   60: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   63: ifeq +189 -> 252
    //   66: aload_0
    //   67: bipush 58
    //   69: invokevirtual 1293	java/lang/String:indexOf	(I)I
    //   72: istore 4
    //   74: aload_0
    //   75: bipush 44
    //   77: invokevirtual 1293	java/lang/String:indexOf	(I)I
    //   80: istore_2
    //   81: iload_2
    //   82: istore_1
    //   83: iload_2
    //   84: iconst_m1
    //   85: if_icmpne +11 -> 96
    //   88: aload_0
    //   89: ldc_w 1294
    //   92: invokevirtual 1293	java/lang/String:indexOf	(I)I
    //   95: istore_1
    //   96: aload_0
    //   97: bipush 63
    //   99: invokevirtual 1293	java/lang/String:indexOf	(I)I
    //   102: istore_3
    //   103: iload_3
    //   104: istore_2
    //   105: iload_3
    //   106: iconst_m1
    //   107: if_icmpne +11 -> 118
    //   110: aload_0
    //   111: ldc_w 1295
    //   114: invokevirtual 1293	java/lang/String:indexOf	(I)I
    //   117: istore_2
    //   118: iload 4
    //   120: iconst_m1
    //   121: if_icmpeq +261 -> 382
    //   124: iload_1
    //   125: iconst_m1
    //   126: if_icmpeq +77 -> 203
    //   129: iload_1
    //   130: iconst_1
    //   131: isub
    //   132: iload 4
    //   134: if_icmple +248 -> 382
    //   137: aload_0
    //   138: iload 4
    //   140: iconst_1
    //   141: iadd
    //   142: iload_1
    //   143: invokevirtual 1138	java/lang/String:substring	(II)Ljava/lang/String;
    //   146: astore 5
    //   148: aload_0
    //   149: ldc_w 1297
    //   152: invokevirtual 1129	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   155: istore_1
    //   156: iload_1
    //   157: iconst_m1
    //   158: if_icmpeq +218 -> 376
    //   161: aload_0
    //   162: iload_1
    //   163: iconst_5
    //   164: iadd
    //   165: invokevirtual 1133	java/lang/String:substring	(I)Ljava/lang/String;
    //   168: astore_0
    //   169: aload_0
    //   170: astore 6
    //   172: aload_0
    //   173: invokestatic 739	pf:b	(Ljava/lang/String;)Z
    //   176: ifeq +12 -> 188
    //   179: aload_0
    //   180: ldc_w 334
    //   183: invokestatic 1301	ql:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   186: astore 6
    //   188: iconst_2
    //   189: anewarray 165	java/lang/String
    //   192: dup
    //   193: iconst_0
    //   194: aload 5
    //   196: aastore
    //   197: dup
    //   198: iconst_1
    //   199: aload 6
    //   201: aastore
    //   202: areturn
    //   203: iload_2
    //   204: iconst_m1
    //   205: if_icmpeq +23 -> 228
    //   208: iload 4
    //   210: iload_2
    //   211: if_icmpge +17 -> 228
    //   214: aload_0
    //   215: iload 4
    //   217: iconst_1
    //   218: iadd
    //   219: iload_2
    //   220: invokevirtual 1138	java/lang/String:substring	(II)Ljava/lang/String;
    //   223: astore 5
    //   225: goto -77 -> 148
    //   228: aload_0
    //   229: ldc_w 1297
    //   232: invokevirtual 1129	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   235: iconst_m1
    //   236: if_icmpne +146 -> 382
    //   239: aload_0
    //   240: iload 4
    //   242: iconst_1
    //   243: iadd
    //   244: invokevirtual 1133	java/lang/String:substring	(I)Ljava/lang/String;
    //   247: astore 5
    //   249: goto -101 -> 148
    //   252: aload_0
    //   253: ldc_w 1291
    //   256: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   259: ifeq +131 -> 390
    //   262: aload_0
    //   263: ldc_w 1303
    //   266: invokevirtual 676	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   269: ifeq +73 -> 342
    //   272: aload_0
    //   273: bipush 8
    //   275: invokevirtual 1133	java/lang/String:substring	(I)Ljava/lang/String;
    //   278: astore_0
    //   279: aload_0
    //   280: ldc_w 1305
    //   283: invokevirtual 1129	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   286: istore_1
    //   287: iload_1
    //   288: iconst_m1
    //   289: if_icmpeq +63 -> 352
    //   292: aload_0
    //   293: iconst_0
    //   294: iload_1
    //   295: invokevirtual 1138	java/lang/String:substring	(II)Ljava/lang/String;
    //   298: astore 5
    //   300: aload_0
    //   301: iload_1
    //   302: iconst_1
    //   303: iadd
    //   304: invokevirtual 1133	java/lang/String:substring	(I)Ljava/lang/String;
    //   307: astore_0
    //   308: aload_0
    //   309: ldc_w 1305
    //   312: invokevirtual 1129	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   315: istore_1
    //   316: iload_1
    //   317: iconst_m1
    //   318: if_icmpeq +47 -> 365
    //   321: aload_0
    //   322: iconst_0
    //   323: iload_1
    //   324: invokevirtual 1138	java/lang/String:substring	(II)Ljava/lang/String;
    //   327: astore_0
    //   328: iconst_2
    //   329: anewarray 165	java/lang/String
    //   332: dup
    //   333: iconst_0
    //   334: aload_0
    //   335: aastore
    //   336: dup
    //   337: iconst_1
    //   338: aload 5
    //   340: aastore
    //   341: areturn
    //   342: aload_0
    //   343: bipush 7
    //   345: invokevirtual 1133	java/lang/String:substring	(I)Ljava/lang/String;
    //   348: astore_0
    //   349: goto -70 -> 279
    //   352: ldc -68
    //   354: astore 6
    //   356: aload_0
    //   357: astore 5
    //   359: aload 6
    //   361: astore_0
    //   362: goto -54 -> 308
    //   365: goto -37 -> 328
    //   368: astore 6
    //   370: aload_0
    //   371: astore 6
    //   373: goto -185 -> 188
    //   376: ldc -68
    //   378: astore_0
    //   379: goto -210 -> 169
    //   382: ldc -68
    //   384: astore 5
    //   386: goto -238 -> 148
    //   389: astore_0
    //   390: aconst_null
    //   391: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	392	0	paramString	String
    //   82	242	1	i	int
    //   80	140	2	j	int
    //   102	6	3	k	int
    //   72	172	4	m	int
    //   146	239	5	str1	String
    //   170	190	6	str2	String
    //   368	1	6	localException	Exception
    //   371	1	6	str3	String
    // Exception table:
    //   from	to	target	type
    //   179	188	368	java/lang/Exception
    //   262	279	389	java/lang/Exception
    //   279	287	389	java/lang/Exception
    //   292	308	389	java/lang/Exception
    //   308	316	389	java/lang/Exception
    //   321	328	389	java/lang/Exception
    //   328	342	389	java/lang/Exception
    //   342	349	389	java/lang/Exception
  }
  
  private static Uri f(Context paramContext)
  {
    int i;
    label61:
    ProviderInfo localProviderInfo;
    if (!TextUtils.isEmpty("com.android.launcher.permission.READ_SETTINGS"))
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8);
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        for (;;)
        {
          if (paramContext.hasNext())
          {
            ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
            if (arrayOfProviderInfo != null)
            {
              int j = arrayOfProviderInfo.length;
              i = 0;
              if (i < j)
              {
                localProviderInfo = arrayOfProviderInfo[i];
                if ((!"com.android.launcher.permission.READ_SETTINGS".equals(localProviderInfo.readPermission)) && (!"com.android.launcher.permission.READ_SETTINGS".equals(localProviderInfo.writePermission))) {
                  break;
                }
              }
            }
          }
        }
      }
    }
    for (paramContext = localProviderInfo.authority;; paramContext = null)
    {
      return Uri.parse("content://" + paramContext + "/favorites?notify=true");
      i += 1;
      break label61;
      break;
    }
  }
  
  public static String f()
  {
    try
    {
      String str3 = kg.e().a();
      String str1 = qm.q;
      String str2 = new String(pf.j(pf.h(pf.j(str3))));
      str3 = pf.q(qm.s + qm.t + str3);
      ArrayList localArrayList = new ArrayList(4);
      localArrayList.add(qm.s);
      localArrayList.add(null);
      localArrayList.add(str2);
      localArrayList.add(str3);
      str1 = a(str1, localArrayList);
      return str1;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String f(String paramString)
  {
    if (pf.a(paramString)) {
      return paramString;
    }
    int j = paramString.indexOf("://");
    int i;
    if (j == -1)
    {
      i = 1;
      j += i;
      i = paramString.indexOf('/', j);
      if (i == -1) {
        break label53;
      }
    }
    for (;;)
    {
      return paramString.substring(j, i);
      i = 3;
      break;
      label53:
      i = paramString.length();
    }
  }
  
  public static String g()
  {
    return d.getApplicationInfo().dataDir;
  }
  
  private static String g(Context paramContext)
  {
    return paramContext.getApplicationInfo().dataDir + "/files/uc/downloadStartFile";
  }
  
  public static String g(String paramString)
  {
    if (pf.a(paramString)) {
      return paramString;
    }
    paramString = f(paramString);
    int i = paramString.indexOf(':');
    if (i != -1) {}
    for (;;)
    {
      return paramString.substring(0, i);
      i = paramString.length();
    }
  }
  
  public static int h()
  {
    return ((WindowManager)d.getSystemService("window")).getDefaultDisplay().getWidth();
  }
  
  public static int h(String paramString)
  {
    if (pf.a(paramString)) {}
    int i;
    do
    {
      return -1;
      paramString = f(paramString);
      i = paramString.indexOf(':');
    } while (i == -1);
    try
    {
      i = Integer.parseInt(paramString.substring(i + 1, paramString.length()));
      return i;
    }
    catch (Exception paramString) {}
    return -1;
  }
  
  public static int i()
  {
    return ((WindowManager)d.getSystemService("window")).getDefaultDisplay().getHeight();
  }
  
  public static String i(String paramString)
  {
    paramString = g(paramString);
    if (pf.a(paramString)) {
      paramString = null;
    }
    String str;
    String[] arrayOfString;
    do
    {
      int i;
      do
      {
        return paramString;
        str = paramString.toLowerCase();
        arrayOfString = pf.d(str, ".");
        i = arrayOfString.length;
        paramString = str;
      } while (arrayOfString.length < 2);
      i -= 1;
      while (i > 0)
      {
        paramString = arrayOfString[i];
        int j;
        if (!pf.a(paramString))
        {
          paramString = paramString.toLowerCase();
          j = 0;
          if (j < 19) {
            if (new String[] { "com", "org", "net", "edu", "gov", "int", "mil", "biz", "info", "pro", "xxx", "name", "museum", "coop", "aero", "idv", "mobi", "cc", "me" }[j].equals(paramString)) {
              j = 1;
            }
          }
        }
        for (;;)
        {
          if (j != 0)
          {
            if (i >= 2)
            {
              if (v(arrayOfString[(i - 2)]))
              {
                return arrayOfString[(i - 1)];
                j += 1;
                break;
                j = 0;
                continue;
              }
              return arrayOfString[(i - 2)] + "." + arrayOfString[(i - 1)];
            }
            return arrayOfString[(i - 1)];
          }
        }
        i -= 1;
      }
      paramString = str;
    } while (!v(arrayOfString[0]));
    return str.substring(arrayOfString[0].length() + 1, str.length());
  }
  
  public static String j()
  {
    if (d != null) {
      return d.getPackageName();
    }
    return null;
  }
  
  public static String j(String paramString)
  {
    int i = 0;
    String str = paramString;
    String[] arrayOfString;
    if (pf.b(paramString))
    {
      arrayOfString = new String[3];
      arrayOfString[0] = "www.";
      arrayOfString[1] = "wap.";
      arrayOfString[2] = "m.";
    }
    for (;;)
    {
      str = paramString;
      if (i < 3)
      {
        if (paramString.startsWith(arrayOfString[i])) {
          str = paramString.substring(arrayOfString[i].length(), paramString.length());
        }
      }
      else {
        return str;
      }
      i += 1;
    }
  }
  
  public static Resources k()
  {
    if (d != null) {
      return d.getResources();
    }
    return null;
  }
  
  public static String k(String paramString)
  {
    if (pf.a(paramString))
    {
      str = null;
      return str;
    }
    String str = paramString.trim().toLowerCase();
    paramString = str;
    if (str.endsWith("/")) {
      paramString = str.substring(0, str.length() - 1);
    }
    int i = paramString.indexOf(":80");
    str = paramString;
    if (i > 0) {
      str = paramString.substring(0, i) + paramString.substring(i + 3);
    }
    i = str.indexOf("http://");
    if (i >= 0) {
      paramString = str.substring(i + 7);
    }
    for (;;)
    {
      str = paramString;
      if (paramString.indexOf("/") != -1) {
        break;
      }
      i = paramString.indexOf("?");
      str = paramString;
      if (i == -1) {
        break;
      }
      return paramString.substring(0, i);
      i = str.indexOf("https://");
      paramString = str;
      if (i >= 0) {
        paramString = str.substring(i + 8);
      }
    }
  }
  
  public static String l(String paramString)
  {
    if (pf.a(paramString)) {
      return null;
    }
    return j(g(paramString)).replace(".", "_");
  }
  
  public static String m(String paramString)
  {
    Object localObject = f(paramString);
    if (pf.a((String)localObject)) {
      return null;
    }
    int i = ((String)localObject).indexOf(':');
    paramString = (String)localObject;
    if (i != -1) {
      paramString = ((String)localObject).substring(0, i);
    }
    localObject = new StringBuffer();
    try
    {
      paramString = paramString.toLowerCase();
      arrayOfString = pf.d(paramString, ".");
      n = arrayOfString.length;
      j = n - 1;
      if (j >= 0)
      {
        if (((!arrayOfString[j].equals("com")) && (!arrayOfString[j].equals("net")) && (!arrayOfString[j].equals("org"))) || (j <= 0)) {
          break label161;
        }
        ((StringBuffer)localObject).insert(0, arrayOfString[j]).insert(0, ".").insert(0, arrayOfString[(j - 1)]).insert(0, ".");
      }
    }
    catch (Exception paramString)
    {
      String[] arrayOfString;
      int n;
      int j;
      label155:
      int m;
      char[] arrayOfChar;
      int k;
      for (;;) {}
    }
    return ((StringBuffer)localObject).toString();
    label161:
    if (j == n - 1)
    {
      m = 1;
      arrayOfChar = arrayOfString[j].toCharArray();
      k = 0;
    }
    for (;;)
    {
      i = m;
      if (k < arrayOfChar.length)
      {
        if (!Character.isDigit(arrayOfChar[k])) {}
      }
      else {
        for (i = 0;; i = 0)
        {
          if (i != 0)
          {
            ((StringBuffer)localObject).insert(0, arrayOfString[j]).insert(0, ".");
            j -= 1;
            break;
          }
          if (j == n - 2)
          {
            ((StringBuffer)localObject).insert(0, arrayOfString[j]).insert(0, ".");
            break label155;
          }
          ((StringBuffer)localObject).insert(0, paramString);
          break label155;
        }
      }
      k += 1;
    }
  }
  
  public static boolean n(String paramString)
  {
    return (pf.b(paramString)) && (paramString.toLowerCase().startsWith("file://"));
  }
  
  public static boolean o(String paramString)
  {
    return (pf.b(paramString)) && (paramString.toLowerCase().startsWith("market://"));
  }
  
  public static boolean p(String paramString)
  {
    paramString = ql.d(paramString);
    if (b.a(paramString)) {
      return false;
    }
    paramString = paramString.toLowerCase();
    try
    {
      boolean bool = Pattern.compile("^((https|http|ftp|rtsp|mms)?://)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.[a-z]{2,6})(:[0-9]{1,4})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$", 2).matcher(paramString).matches();
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static InputStream q(String paramString)
  {
    try
    {
      if (ActivityBrowser.a().getFileStreamPath(paramString).exists())
      {
        paramString = ActivityBrowser.a().openFileInput(paramString);
        return paramString;
      }
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static OutputStream r(String paramString)
  {
    try
    {
      paramString = ActivityBrowser.a().openFileOutput(paramString, 0);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static boolean s(String paramString)
  {
    try
    {
      boolean bool = ActivityBrowser.a().deleteFile(paramString);
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static wr t(String paramString)
  {
    int i = 0;
    int j;
    try
    {
      j = paramString.indexOf(':');
      if (j <= 0) {
        throw new IllegalArgumentException("no ':' in URL");
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new ws("The requested protocol does not exist " + paramString);
    }
    Object localObject = paramString.substring(0, j).toCharArray();
    for (;;)
    {
      if (i < localObject.length)
      {
        j = localObject[i];
        if (65 > j) {
          break label103;
        }
        if (j <= 90) {
          break label152;
        }
      }
      label103:
      while (((97 > j) || (j > 122)) && ((i <= 0) || (((48 > j) || (j > 57)) && (j != 43) && (j != 45) && (j != 46))))
      {
        throw new IllegalArgumentException("Invalid protocol name");
        localObject = wx.a(paramString);
        return localObject;
      }
      label152:
      i += 1;
    }
  }
  
  private static int u(String paramString)
  {
    int i = 0;
    int k;
    if ((paramString == null) || (paramString.equals("")))
    {
      k = -1;
      return k;
    }
    for (int j = 0;; j = k)
    {
      if (i >= paramString.length()) {
        break label73;
      }
      int m = paramString.charAt(i);
      if (m == 38)
      {
        k = i;
        if (j % 2 == 0) {
          break;
        }
      }
      k = j;
      if (34 == m) {
        k = j + 1;
      }
      i += 1;
    }
    label73:
    return -1;
  }
  
  private static boolean v(String paramString)
  {
    if (pf.b(paramString))
    {
      int i = 0;
      while (i < 3)
      {
        if (paramString.equals(new String[] { "www", "wap", "m" }[i])) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
}
