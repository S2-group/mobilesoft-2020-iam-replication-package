package com.lbe.parallel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Process;
import android.os.StatFs;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.Fragment.a;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.lbe.doubleagent.service.plugin.e;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import com.lbe.multidroid.service.k;
import com.lbe.parallel.ads.r;
import com.lbe.parallel.model.AdClientInfo;
import com.lbe.parallel.model.AdDeviceInfo;
import com.lbe.parallel.model.PackageData;
import com.lbe.parallel.model.PsDevInfo;
import com.lbe.parallel.model.RecommendAdRequest;
import com.lbe.parallel.utility.a;
import com.lbe.parallel.utility.ac;
import com.lbe.parallel.utility.ac.a;
import com.lbe.parallel.utility.ad;
import com.lbe.parallel.utility.d;
import com.lbe.parallel.utility.i;
import com.lbe.parallel.utility.s;
import com.lbe.parallel.utility.u;
import com.lbe.parallel.utility.y;
import com.lbe.parallel.utility.z;
import com.xinmei.adsdk.utils.m;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

final class f
{
  private int a;
  private boolean b;
  
  f() {}
  
  public static float a(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser, String paramString, int paramInt, float paramFloat)
  {
    if (!a(paramXmlPullParser, paramString)) {
      return paramFloat;
    }
    return paramTypedArray.getFloat(paramInt, paramFloat);
  }
  
  public static int a(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser, String paramString, int paramInt)
  {
    if (!a(paramXmlPullParser, paramString)) {
      return -1;
    }
    return paramTypedArray.getInt(paramInt, -1);
  }
  
  public static int a(TypedArray paramTypedArray, XmlPullParser paramXmlPullParser, String paramString, int paramInt1, int paramInt2)
  {
    if (!a(paramXmlPullParser, paramString)) {
      return paramInt2;
    }
    return paramTypedArray.getColor(paramInt1, paramInt2);
  }
  
  private static int a(String paramString, int paramInt)
  {
    for (;;)
    {
      if (paramInt < paramString.length())
      {
        int i = paramString.charAt(paramInt);
        if ((((i - 65) * (i - 90) > 0) && ((i - 97) * (i - 122) > 0)) || (i == 101) || (i == 69)) {}
      }
      else
      {
        return paramInt;
      }
      paramInt += 1;
    }
  }
  
  private static void a(ArrayList<a> paramArrayList, char paramChar, float[] paramArrayOfFloat)
  {
    paramArrayList.add(new a(paramChar, paramArrayOfFloat));
  }
  
  public static boolean a(XmlPullParser paramXmlPullParser, String paramString)
  {
    return paramXmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", paramString) != null;
  }
  
  static float[] a(float[] paramArrayOfFloat, int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException();
    }
    int i = paramArrayOfFloat.length;
    if (i < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }
    i = Math.min(paramInt, i);
    float[] arrayOfFloat = new float[paramInt];
    System.arraycopy(paramArrayOfFloat, 0, arrayOfFloat, 0, i);
    return arrayOfFloat;
  }
  
  public static a[] a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    int j = 1;
    int i = 0;
    while (j < paramString.length())
    {
      j = a(paramString, j);
      String str = paramString.substring(i, j).trim();
      if (str.length() > 0)
      {
        float[] arrayOfFloat = b(str);
        a(localArrayList, str.charAt(0), arrayOfFloat);
      }
      int k = j + 1;
      i = j;
      j = k;
    }
    if ((j - i == 1) && (i < paramString.length())) {
      a(localArrayList, paramString.charAt(i), new float[0]);
    }
    return (a[])localArrayList.toArray(new a[localArrayList.size()]);
  }
  
  public static a[] a(a[] paramArrayOfA)
  {
    if (paramArrayOfA == null) {
      return null;
    }
    a[] arrayOfA = new a[paramArrayOfA.length];
    int i = 0;
    while (i < paramArrayOfA.length)
    {
      arrayOfA[i] = new a(paramArrayOfA[i]);
      i += 1;
    }
    return arrayOfA;
  }
  
  private static float[] b(String paramString)
  {
    int i;
    if (paramString.charAt(0) == 'z')
    {
      i = 1;
      if (paramString.charAt(0) != 'Z') {
        break label39;
      }
    }
    label39:
    for (int j = 1;; j = 0)
    {
      if ((i | j) == 0) {
        break label44;
      }
      return new float[0];
      i = 0;
      break;
    }
    for (;;)
    {
      label44:
      int i1;
      try
      {
        float[] arrayOfFloat = new float[paramString.length()];
        f localF = new f();
        int i3 = paramString.length();
        n = 1;
        m = 0;
        if (n < i3)
        {
          localF.b = false;
          k = 0;
          i = 0;
          j = 0;
          i1 = n;
          if (i1 >= paramString.length()) {}
        }
        switch (paramString.charAt(i1))
        {
        case '-': 
          if ((i1 == n) || (k != 0)) {
            break label316;
          }
          localF.b = true;
          j = i;
          k = 1;
          i = 0;
          break label326;
          label194:
          localF.b = true;
          j = i;
          k = 1;
          i = 0;
          break label326;
          localF.a = i1;
          j = localF.a;
          if (n < j)
          {
            i = m + 1;
            arrayOfFloat[m] = Float.parseFloat(paramString.substring(n, j));
            if (!localF.b) {
              break label384;
            }
            n = j;
            m = i;
            continue;
            arrayOfFloat = a(arrayOfFloat, m);
            return arrayOfFloat;
          }
          break;
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new RuntimeException("error in parsing \"" + paramString + "\"", localNumberFormatException);
      }
      i = m;
      continue;
      label316:
      int i2 = 0;
      int k = j;
      j = i;
      i = i2;
      for (;;)
      {
        label326:
        if (k != 0) {
          break label382;
        }
        i2 = i1 + 1;
        i1 = i;
        i = j;
        j = k;
        k = i1;
        i1 = i2;
        break;
        j = i;
        k = 1;
        i = 0;
        continue;
        if (i != 0) {
          break label194;
        }
        i = 0;
        k = j;
        j = 1;
        continue;
        k = j;
        j = i;
        i = 1;
      }
      label382:
      continue;
      label384:
      int n = j + 1;
      int m = i;
    }
  }
  
  public static final class a<T>
  {
    private static String c;
    private static String d;
    private static volatile String e;
    private static Boolean f;
    private static RequestQueue g;
    private static ImageLoader h;
    private static ha i;
    private static Map<String, String> j;
    private char a;
    private float[] b;
    
    public a() {}
    
    a(char paramChar, float[] paramArrayOfFloat)
    {
      this.a = paramChar;
      this.b = paramArrayOfFloat;
    }
    
    a(a paramA)
    {
      this.a = paramA.a;
      this.b = f.a(paramA.b, paramA.b.length);
    }
    
    public static double a(double paramDouble1, double paramDouble2)
    {
      return Math.min(Math.max(paramDouble1, paramDouble2), 1.0D);
    }
    
    public static double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5)
    {
      return (paramDouble1 - paramDouble2) / (paramDouble3 - paramDouble2) * (paramDouble5 - paramDouble4) + paramDouble4;
    }
    
    public static int a(Context paramContext, String paramString)
    {
      int k = Process.myPid();
      int m = Process.myUid();
      String str1 = paramContext.getPackageName();
      if (paramContext.checkPermission(paramString, k, m) == -1) {
        return -1;
      }
      String str2 = android.support.v4.app.c.a(paramString);
      if (str2 != null)
      {
        paramString = str1;
        if (str1 == null)
        {
          paramString = paramContext.getPackageManager().getPackagesForUid(m);
          if ((paramString == null) || (paramString.length <= 0)) {
            return -1;
          }
          paramString = paramString[0];
        }
        if (android.support.v4.app.c.a(paramContext, str2, paramString) != 0) {
          return -2;
        }
      }
      return 0;
    }
    
    public static int a(Parcel paramParcel)
    {
      int m = paramParcel.readInt();
      int n = a(paramParcel, m);
      int k = paramParcel.dataPosition();
      if ((0xFFFF & m) != 20293)
      {
        String str = String.valueOf(Integer.toHexString(m));
        if (str.length() != 0) {}
        for (str = "Expected object header. Got 0x".concat(str);; str = new String("Expected object header. Got 0x")) {
          throw new Fragment.a(str, paramParcel);
        }
      }
      m = k + n;
      if ((m < k) || (m > paramParcel.dataSize())) {
        throw new Fragment.a(54 + "Size read is invalid start=" + k + " end=" + m, paramParcel);
      }
      return m;
    }
    
    public static int a(Parcel paramParcel, int paramInt)
    {
      if ((paramInt & 0xFFFF0000) != -65536) {
        return paramInt >> 16 & 0xFFFF;
      }
      return paramParcel.readInt();
    }
    
    /* Error */
    public static int a(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 6
      //   3: aconst_null
      //   4: astore 5
      //   6: aload_3
      //   7: ifnull +10 -> 17
      //   10: aload_0
      //   11: invokevirtual 161	java/lang/String:isEmpty	()Z
      //   14: ifeq +5 -> 19
      //   17: iconst_m1
      //   18: ireturn
      //   19: aload_3
      //   20: arraylength
      //   21: ldc -94
      //   23: if_icmpgt +10 -> 33
      //   26: aload_3
      //   27: arraylength
      //   28: ldc -93
      //   30: if_icmpge +5 -> 35
      //   33: iconst_m1
      //   34: ireturn
      //   35: aload_0
      //   36: invokestatic 167	com/xinmei/adsdk/utils/m:b	(Ljava/lang/String;)Ljava/lang/String;
      //   39: astore_0
      //   40: new 134	java/lang/StringBuilder
      //   43: dup
      //   44: invokespecial 168	java/lang/StringBuilder:<init>	()V
      //   47: aload_0
      //   48: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   51: ldc -86
      //   53: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   56: aload_2
      //   57: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   60: ldc -84
      //   62: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   65: aload_1
      //   66: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   69: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   72: astore_0
      //   73: new 174	java/net/URL
      //   76: dup
      //   77: aload_0
      //   78: invokespecial 175	java/net/URL:<init>	(Ljava/lang/String;)V
      //   81: invokevirtual 179	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   84: checkcast 181	java/net/HttpURLConnection
      //   87: astore 7
      //   89: aload 7
      //   91: sipush 30000
      //   94: invokevirtual 184	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   97: aload 7
      //   99: ldc -71
      //   101: invokevirtual 188	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   104: aload 7
      //   106: iconst_1
      //   107: invokevirtual 192	java/net/HttpURLConnection:setDoInput	(Z)V
      //   110: aload 7
      //   112: iconst_1
      //   113: invokevirtual 195	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   116: aload 7
      //   118: iconst_0
      //   119: invokevirtual 198	java/net/HttpURLConnection:setUseCaches	(Z)V
      //   122: aload 7
      //   124: ldc -56
      //   126: invokevirtual 203	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   129: aload 7
      //   131: ldc -51
      //   133: ldc -49
      //   135: invokevirtual 211	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   138: aload 7
      //   140: ldc -43
      //   142: ldc -41
      //   144: invokevirtual 211	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   147: aload 7
      //   149: ldc -39
      //   151: ldc -37
      //   153: invokevirtual 211	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   156: new 221	java/io/DataOutputStream
      //   159: dup
      //   160: aload 7
      //   162: invokevirtual 225	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   165: invokespecial 228	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   168: astore_0
      //   169: aload_0
      //   170: aload_3
      //   171: invokevirtual 232	java/io/DataOutputStream:write	([B)V
      //   174: aload_0
      //   175: invokevirtual 235	java/io/DataOutputStream:flush	()V
      //   178: aload 7
      //   180: invokevirtual 238	java/net/HttpURLConnection:getResponseCode	()I
      //   183: istore 4
      //   185: new 240	java/io/BufferedReader
      //   188: dup
      //   189: new 242	java/io/InputStreamReader
      //   192: dup
      //   193: aload 7
      //   195: invokevirtual 246	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   198: invokespecial 249	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   201: invokespecial 252	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   204: astore_2
      //   205: ldc -2
      //   207: astore_1
      //   208: aload_2
      //   209: invokevirtual 257	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   212: astore_3
      //   213: aload_3
      //   214: ifnull +25 -> 239
      //   217: new 134	java/lang/StringBuilder
      //   220: dup
      //   221: invokespecial 168	java/lang/StringBuilder:<init>	()V
      //   224: aload_1
      //   225: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   228: aload_3
      //   229: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   232: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   235: astore_1
      //   236: goto -28 -> 208
      //   239: aload_0
      //   240: invokevirtual 260	java/io/DataOutputStream:close	()V
      //   243: aload 7
      //   245: invokevirtual 263	java/net/HttpURLConnection:disconnect	()V
      //   248: aload_0
      //   249: invokevirtual 260	java/io/DataOutputStream:close	()V
      //   252: aload_2
      //   253: invokevirtual 264	java/io/BufferedReader:close	()V
      //   256: iload 4
      //   258: ireturn
      //   259: astore_0
      //   260: aconst_null
      //   261: astore_0
      //   262: aload 5
      //   264: astore_1
      //   265: invokestatic 266	com/xinmei/adsdk/utils/m:f	()V
      //   268: aload_0
      //   269: ifnull +7 -> 276
      //   272: aload_0
      //   273: invokevirtual 260	java/io/DataOutputStream:close	()V
      //   276: aload_1
      //   277: ifnull +7 -> 284
      //   280: aload_1
      //   281: invokevirtual 264	java/io/BufferedReader:close	()V
      //   284: iconst_m1
      //   285: ireturn
      //   286: astore_0
      //   287: aconst_null
      //   288: astore_2
      //   289: aload 6
      //   291: astore_1
      //   292: aload_1
      //   293: ifnull +7 -> 300
      //   296: aload_1
      //   297: invokevirtual 260	java/io/DataOutputStream:close	()V
      //   300: aload_2
      //   301: ifnull +7 -> 308
      //   304: aload_2
      //   305: invokevirtual 264	java/io/BufferedReader:close	()V
      //   308: aload_0
      //   309: athrow
      //   310: astore_1
      //   311: goto -3 -> 308
      //   314: astore_3
      //   315: aconst_null
      //   316: astore_2
      //   317: aload_0
      //   318: astore_1
      //   319: aload_3
      //   320: astore_0
      //   321: goto -29 -> 292
      //   324: astore_3
      //   325: aload_0
      //   326: astore_1
      //   327: aload_3
      //   328: astore_0
      //   329: goto -37 -> 292
      //   332: astore_3
      //   333: aload_1
      //   334: astore_2
      //   335: aload_0
      //   336: astore_1
      //   337: aload_3
      //   338: astore_0
      //   339: goto -47 -> 292
      //   342: astore_0
      //   343: goto -59 -> 284
      //   346: astore_1
      //   347: aload 5
      //   349: astore_1
      //   350: goto -85 -> 265
      //   353: astore_1
      //   354: aload_2
      //   355: astore_1
      //   356: goto -91 -> 265
      //   359: astore_0
      //   360: goto -104 -> 256
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	363	0	paramString1	String
      //   0	363	1	paramString2	String
      //   0	363	2	paramString3	String
      //   0	363	3	paramArrayOfByte	byte[]
      //   183	74	4	k	int
      //   4	344	5	localObject1	Object
      //   1	289	6	localObject2	Object
      //   87	157	7	localHttpURLConnection	HttpURLConnection
      // Exception table:
      //   from	to	target	type
      //   73	169	259	java/lang/Exception
      //   73	169	286	finally
      //   296	300	310	java/io/IOException
      //   304	308	310	java/io/IOException
      //   169	205	314	finally
      //   208	213	324	finally
      //   217	236	324	finally
      //   239	248	324	finally
      //   265	268	332	finally
      //   272	276	342	java/io/IOException
      //   280	284	342	java/io/IOException
      //   169	205	346	java/lang/Exception
      //   208	213	353	java/lang/Exception
      //   217	236	353	java/lang/Exception
      //   239	248	353	java/lang/Exception
      //   248	256	359	java/io/IOException
    }
    
    private static long a(InputStream paramInputStream, OutputStream paramOutputStream, byte[] paramArrayOfByte)
      throws IOException
    {
      int k;
      for (long l = 0L;; l += k)
      {
        k = paramInputStream.read(paramArrayOfByte);
        if (-1 == k) {
          break;
        }
        paramOutputStream.write(paramArrayOfByte, 0, k);
      }
      return l;
    }
    
    @TargetApi(17)
    public static Bitmap a(Context paramContext, Bitmap paramBitmap)
    {
      try
      {
        Object localObject1 = Bitmap.createScaledBitmap(paramBitmap, Math.round(paramBitmap.getWidth() * 0.5F), Math.round(paramBitmap.getHeight() * 0.5F), false);
        paramBitmap = Bitmap.createBitmap((Bitmap)localObject1);
        Object localObject2 = RenderScript.create(paramContext);
        paramContext = ScriptIntrinsicBlur.create((RenderScript)localObject2, Element.U8_4((RenderScript)localObject2));
        localObject1 = Allocation.createFromBitmap((RenderScript)localObject2, (Bitmap)localObject1);
        localObject2 = Allocation.createFromBitmap((RenderScript)localObject2, paramBitmap);
        paramContext.setRadius(25.0F);
        paramContext.setInput((Allocation)localObject1);
        paramContext.forEach((Allocation)localObject2);
        ((Allocation)localObject2).copyTo(paramBitmap);
        return paramBitmap;
      }
      catch (Throwable paramContext) {}
      return null;
    }
    
    public static <T extends Parcelable> T a(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = (Parcelable)paramCreator.createFromParcel(paramParcel);
      paramParcel.setDataPosition(paramInt + k);
      return paramCreator;
    }
    
    private static ht a(PackageInfo paramPackageInfo)
    {
      ht localHt = new ht();
      localHt.b = paramPackageInfo.packageName;
      localHt.f = "";
      localHt.e = "";
      localHt.c = paramPackageInfo.versionCode;
      String str = paramPackageInfo.versionName;
      if (str != null)
      {
        localHt.d = str;
        if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
          break label77;
        }
      }
      label77:
      for (boolean bool = true;; bool = false)
      {
        localHt.g = bool;
        return localHt;
        str = "";
        break;
      }
    }
    
    public static hx a(Context paramContext, hw paramHw)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "events_control"), hw.a(paramHw));
        if (paramContext != null)
        {
          paramContext = (hx)fj.a(new hx(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static hy a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte)
    {
      hy localHy = new hy();
      localHy.b = c(paramContext);
      localHy.c = d(paramContext);
      localHy.d = paramString3;
      localHy.e = paramString4;
      localHy.f = paramString1;
      if (!TextUtils.isEmpty(paramString2))
      {
        paramString1 = a.a(paramContext, paramString2);
        if (paramString1 != null)
        {
          localHy.h = a(paramString1);
          paramString1 = a.b(paramString1);
          if (paramString1 == null) {
            break label176;
          }
        }
        label176:
        for (paramString1 = paramString1.toString();; paramString1 = null)
        {
          localHy.g = paramString1;
          paramString1 = new PackageManagerWrapper(paramContext);
          paramString3 = paramString1.getInstalledPackages(0);
          paramString2 = new ArrayList(paramString3.size());
          paramString3 = paramString3.iterator();
          while (paramString3.hasNext())
          {
            paramString4 = (PackageInfo)paramString3.next();
            if (!a(paramString4.applicationInfo)) {
              paramString2.add(a(paramString4));
            }
          }
        }
        localHy.i = ((ht[])paramString2.toArray(new ht[paramString2.size()]));
        paramString2 = com.lbe.multidroid.service.b.a(paramContext).c().d(DAApp.o().q());
        if ((paramString2 != null) && (paramString2.length > 0))
        {
          paramString3 = new ArrayList(paramString2.length);
          int m = paramString2.length;
          int k = 0;
          for (;;)
          {
            if (k < m)
            {
              paramContext = paramString2[k];
              try
              {
                paramContext = paramString1.getPackageInfo(paramContext, 0);
                if (paramContext != null) {
                  paramString3.add(a(paramContext));
                }
                k += 1;
              }
              catch (PackageManager.NameNotFoundException paramContext)
              {
                for (;;)
                {
                  paramContext.printStackTrace();
                  paramContext = null;
                }
              }
            }
          }
          localHy.k = ((ht[])paramString3.toArray(new ht[paramString3.size()]));
        }
        if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {
          localHy.j = paramArrayOfByte;
        }
      }
      return localHy;
    }
    
    public static hz a(Context paramContext, hy paramHy)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "feedback"), hy.a(paramHy));
        if (paramContext != null)
        {
          paramContext = (hz)fj.a(new hz(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static ib a(Context paramContext, ia paramIa)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "helpPage"), ia.a(paramIa));
        if (paramContext != null)
        {
          paramContext = (ib)fj.a(new ib(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    private static ie a(Context paramContext, id paramId)
    {
      Object localObject = null;
      try
      {
        paramId = a(hk.a(paramContext, "getHotApp"), id.a(paramId));
        if (paramId != null) {}
        for (paramContext = Integer.valueOf(paramId.length);; paramContext = null)
        {
          paramContext = localObject;
          if (paramId != null) {
            paramContext = ie.a(paramId);
          }
          return paramContext;
        }
        return null;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        paramContext.getMessage();
      }
    }
    
    public static ih a(Context paramContext, ig paramIg)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "messages"), ig.a(paramIg));
        if (paramContext != null)
        {
          paramContext = (ih)fj.a(new ih(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static ij a(Context paramContext, ii paramIi)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "raiders"), ia.a(paramIi));
        if (paramContext != null)
        {
          paramContext = (ij)fj.a(new ij(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static im a(Context paramContext, il paramIl)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "specialThanks"), ia.a(paramIl));
        if (paramContext != null)
        {
          paramContext = (im)fj.a(new im(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static io a(Context paramContext, in paramIn)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "update"), in.a(paramIn));
        if (paramContext != null)
        {
          paramContext = (io)fj.a(new io(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static ir a(Context paramContext, iq paramIq)
    {
      try
      {
        paramContext = a(hk.a(paramContext, "web_navi"), iq.a(paramIq));
        if (paramContext != null)
        {
          paramContext = ir.a(paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    /* Error */
    public static com.lbe.parallel.model.UpdateInfo.DownloadInfo a(Context paramContext, long paramLong)
    {
      // Byte code:
      //   0: aload_0
      //   1: ldc_w 623
      //   4: invokevirtual 627	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   7: checkcast 629	android/app/DownloadManager
      //   10: astore_0
      //   11: new 631	android/app/DownloadManager$Query
      //   14: dup
      //   15: invokespecial 632	android/app/DownloadManager$Query:<init>	()V
      //   18: astore 4
      //   20: aload 4
      //   22: iconst_1
      //   23: newarray long
      //   25: dup
      //   26: iconst_0
      //   27: lload_1
      //   28: lastore
      //   29: invokevirtual 636	android/app/DownloadManager$Query:setFilterById	([J)Landroid/app/DownloadManager$Query;
      //   32: pop
      //   33: aload_0
      //   34: aload 4
      //   36: invokevirtual 640	android/app/DownloadManager:query	(Landroid/app/DownloadManager$Query;)Landroid/database/Cursor;
      //   39: astore 6
      //   41: aload 6
      //   43: ifnull +214 -> 257
      //   46: aload 6
      //   48: invokeinterface 645 1 0
      //   53: ifle +204 -> 257
      //   56: aload 6
      //   58: invokeinterface 648 1 0
      //   63: ifeq +189 -> 252
      //   66: aload 6
      //   68: aload 6
      //   70: ldc_w 650
      //   73: invokeinterface 654 2 0
      //   78: invokeinterface 658 2 0
      //   83: lstore_1
      //   84: aload 6
      //   86: aload 6
      //   88: ldc_w 660
      //   91: invokeinterface 654 2 0
      //   96: invokeinterface 663 2 0
      //   101: astore 5
      //   103: aload 6
      //   105: aload 6
      //   107: ldc_w 665
      //   110: invokeinterface 654 2 0
      //   115: invokeinterface 669 2 0
      //   120: istore_3
      //   121: new 671	com/lbe/parallel/model/UpdateInfo$DownloadInfo
      //   124: dup
      //   125: invokespecial 672	com/lbe/parallel/model/UpdateInfo$DownloadInfo:<init>	()V
      //   128: astore_0
      //   129: aload 5
      //   131: astore 4
      //   133: aload 5
      //   135: ifnull +30 -> 165
      //   138: aload 5
      //   140: astore 4
      //   142: aload 5
      //   144: ldc_w 674
      //   147: invokevirtual 678	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   150: ifeq +15 -> 165
      //   153: aload 5
      //   155: ldc_w 674
      //   158: ldc -2
      //   160: invokevirtual 682	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   163: astore 4
      //   165: aload_0
      //   166: aload 4
      //   168: invokevirtual 685	com/lbe/parallel/model/UpdateInfo$DownloadInfo:setFilePath	(Ljava/lang/String;)V
      //   171: aload_0
      //   172: lload_1
      //   173: invokevirtual 689	com/lbe/parallel/model/UpdateInfo$DownloadInfo:setFileSize	(J)V
      //   176: aload_0
      //   177: iload_3
      //   178: invokevirtual 692	com/lbe/parallel/model/UpdateInfo$DownloadInfo:setStatus	(I)V
      //   181: aload_0
      //   182: astore 4
      //   184: aload 6
      //   186: ifnull +13 -> 199
      //   189: aload 6
      //   191: invokeinterface 693 1 0
      //   196: aload_0
      //   197: astore 4
      //   199: aload 4
      //   201: areturn
      //   202: astore 4
      //   204: aconst_null
      //   205: astore_0
      //   206: aload 4
      //   208: invokevirtual 425	java/lang/Exception:printStackTrace	()V
      //   211: aload_0
      //   212: astore 4
      //   214: aload 6
      //   216: ifnull -17 -> 199
      //   219: aload 6
      //   221: invokeinterface 693 1 0
      //   226: aload_0
      //   227: astore 4
      //   229: goto -30 -> 199
      //   232: astore_0
      //   233: aload 6
      //   235: ifnull +10 -> 245
      //   238: aload 6
      //   240: invokeinterface 693 1 0
      //   245: aload_0
      //   246: athrow
      //   247: astore 4
      //   249: goto -43 -> 206
      //   252: aconst_null
      //   253: astore_0
      //   254: goto -73 -> 181
      //   257: aconst_null
      //   258: astore 4
      //   260: goto -61 -> 199
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	263	0	paramContext	Context
      //   0	263	1	paramLong	long
      //   120	58	3	k	int
      //   18	182	4	localObject1	Object
      //   202	5	4	localException1	Exception
      //   212	16	4	localContext	Context
      //   247	1	4	localException2	Exception
      //   258	1	4	localObject2	Object
      //   101	53	5	str	String
      //   39	200	6	localCursor	android.database.Cursor
      // Exception table:
      //   from	to	target	type
      //   56	129	202	java/lang/Exception
      //   56	129	232	finally
      //   142	165	232	finally
      //   165	181	232	finally
      //   206	211	232	finally
      //   142	165	247	java/lang/Exception
      //   165	181	247	java/lang/Exception
    }
    
    public static Object a(PackageManager paramPackageManager, String paramString, int paramInt)
    {
      return com.lbe.doubleagent.service.parser.c.a(paramPackageManager, paramString, null, paramInt, paramInt);
    }
    
    public static <T> T a(T paramT)
    {
      if (paramT == null) {
        throw new NullPointerException("null reference");
      }
      return paramT;
    }
    
    public static <T> T a(T paramT, Object paramObject)
    {
      if (paramT == null) {
        throw new NullPointerException(String.valueOf(paramObject));
      }
      return paramT;
    }
    
    public static <T> T a(Callable<T> paramCallable)
    {
      StrictMode.ThreadPolicy localThreadPolicy = StrictMode.getThreadPolicy();
      try
      {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.LAX);
        paramCallable = paramCallable.call();
        StrictMode.setThreadPolicy(localThreadPolicy);
        return paramCallable;
      }
      catch (Throwable paramCallable)
      {
        paramCallable = paramCallable;
        StrictMode.setThreadPolicy(localThreadPolicy);
        return null;
      }
      finally
      {
        paramCallable = finally;
        StrictMode.setThreadPolicy(localThreadPolicy);
        throw paramCallable;
      }
    }
    
    /* Error */
    public static String a(Context paramContext, String paramString1, String paramString2)
    {
      // Byte code:
      //   0: new 134	java/lang/StringBuilder
      //   3: dup
      //   4: invokespecial 168	java/lang/StringBuilder:<init>	()V
      //   7: astore 4
      //   9: sipush 512
      //   12: newarray char
      //   14: astore 5
      //   16: aload_0
      //   17: invokevirtual 738	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   20: invokevirtual 744	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
      //   23: aload_1
      //   24: invokevirtual 750	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
      //   27: astore_0
      //   28: new 240	java/io/BufferedReader
      //   31: dup
      //   32: new 242	java/io/InputStreamReader
      //   35: dup
      //   36: aload_0
      //   37: aload_2
      //   38: invokespecial 753	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
      //   41: invokespecial 252	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   44: astore_1
      //   45: aload_1
      //   46: aload 5
      //   48: invokevirtual 756	java/io/BufferedReader:read	([C)I
      //   51: istore_3
      //   52: iload_3
      //   53: ifle +35 -> 88
      //   56: aload 4
      //   58: aload 5
      //   60: iconst_0
      //   61: iload_3
      //   62: invokevirtual 759	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
      //   65: pop
      //   66: goto -21 -> 45
      //   69: astore_2
      //   70: aload_0
      //   71: ifnull +7 -> 78
      //   74: aload_0
      //   75: invokevirtual 760	java/io/InputStream:close	()V
      //   78: aload_1
      //   79: ifnull +7 -> 86
      //   82: aload_1
      //   83: invokevirtual 264	java/io/BufferedReader:close	()V
      //   86: aconst_null
      //   87: areturn
      //   88: aload 4
      //   90: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   93: astore_2
      //   94: aload_0
      //   95: ifnull +7 -> 102
      //   98: aload_0
      //   99: invokevirtual 760	java/io/InputStream:close	()V
      //   102: aload_1
      //   103: invokevirtual 264	java/io/BufferedReader:close	()V
      //   106: aload_2
      //   107: areturn
      //   108: astore_0
      //   109: new 134	java/lang/StringBuilder
      //   112: dup
      //   113: ldc_w 762
      //   116: invokespecial 763	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   119: aload_0
      //   120: invokevirtual 764	java/io/IOException:getMessage	()Ljava/lang/String;
      //   123: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   126: pop
      //   127: aload_2
      //   128: areturn
      //   129: astore_0
      //   130: new 134	java/lang/StringBuilder
      //   133: dup
      //   134: ldc_w 762
      //   137: invokespecial 763	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   140: aload_0
      //   141: invokevirtual 764	java/io/IOException:getMessage	()Ljava/lang/String;
      //   144: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   147: pop
      //   148: aconst_null
      //   149: areturn
      //   150: astore_0
      //   151: aconst_null
      //   152: astore_1
      //   153: aconst_null
      //   154: astore_0
      //   155: aload_0
      //   156: ifnull +7 -> 163
      //   159: aload_0
      //   160: invokevirtual 760	java/io/InputStream:close	()V
      //   163: aload_1
      //   164: ifnull -78 -> 86
      //   167: aload_1
      //   168: invokevirtual 264	java/io/BufferedReader:close	()V
      //   171: aconst_null
      //   172: areturn
      //   173: astore_0
      //   174: new 134	java/lang/StringBuilder
      //   177: dup
      //   178: ldc_w 762
      //   181: invokespecial 763	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   184: aload_0
      //   185: invokevirtual 764	java/io/IOException:getMessage	()Ljava/lang/String;
      //   188: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   191: pop
      //   192: aconst_null
      //   193: areturn
      //   194: astore_2
      //   195: aconst_null
      //   196: astore_0
      //   197: aconst_null
      //   198: astore_1
      //   199: aload_0
      //   200: ifnull +7 -> 207
      //   203: aload_0
      //   204: invokevirtual 760	java/io/InputStream:close	()V
      //   207: aload_1
      //   208: ifnull +7 -> 215
      //   211: aload_1
      //   212: invokevirtual 264	java/io/BufferedReader:close	()V
      //   215: aload_2
      //   216: athrow
      //   217: astore_0
      //   218: new 134	java/lang/StringBuilder
      //   221: dup
      //   222: ldc_w 762
      //   225: invokespecial 763	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   228: aload_0
      //   229: invokevirtual 764	java/io/IOException:getMessage	()Ljava/lang/String;
      //   232: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   235: pop
      //   236: goto -21 -> 215
      //   239: astore_2
      //   240: aconst_null
      //   241: astore_1
      //   242: goto -43 -> 199
      //   245: astore_2
      //   246: goto -47 -> 199
      //   249: astore_1
      //   250: aconst_null
      //   251: astore_1
      //   252: goto -97 -> 155
      //   255: astore_2
      //   256: goto -101 -> 155
      //   259: astore_0
      //   260: aconst_null
      //   261: astore_1
      //   262: aconst_null
      //   263: astore_0
      //   264: goto -194 -> 70
      //   267: astore_1
      //   268: aconst_null
      //   269: astore_1
      //   270: goto -200 -> 70
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	273	0	paramContext	Context
      //   0	273	1	paramString1	String
      //   0	273	2	paramString2	String
      //   51	11	3	k	int
      //   7	82	4	localStringBuilder	StringBuilder
      //   14	45	5	arrayOfChar	char[]
      // Exception table:
      //   from	to	target	type
      //   45	52	69	java/io/UnsupportedEncodingException
      //   56	66	69	java/io/UnsupportedEncodingException
      //   88	94	69	java/io/UnsupportedEncodingException
      //   98	102	108	java/io/IOException
      //   102	106	108	java/io/IOException
      //   74	78	129	java/io/IOException
      //   82	86	129	java/io/IOException
      //   0	28	150	java/io/IOException
      //   159	163	173	java/io/IOException
      //   167	171	173	java/io/IOException
      //   0	28	194	finally
      //   203	207	217	java/io/IOException
      //   211	215	217	java/io/IOException
      //   28	45	239	finally
      //   45	52	245	finally
      //   56	66	245	finally
      //   88	94	245	finally
      //   28	45	249	java/io/IOException
      //   45	52	255	java/io/IOException
      //   56	66	255	java/io/IOException
      //   88	94	255	java/io/IOException
      //   0	28	259	java/io/UnsupportedEncodingException
      //   28	45	267	java/io/UnsupportedEncodingException
    }
    
    public static String a(Context paramContext, String paramString1, String paramString2, String paramString3, Map<String, String> paramMap)
    {
      JSONObject localJSONObject;
      try
      {
        localJSONObject = new JSONObject();
        localJSONObject.put("oid", paramString1);
        localJSONObject.put("adid", paramString2);
        localJSONObject.put("ttp", paramString3);
        paramString1 = paramMap;
        if (paramMap == null) {
          paramString1 = new HashMap();
        }
        if (paramString1.get("sdk_version") == null) {
          paramString1.put("sdk_version", m.e());
        }
        if (paramString1.get("gaid") == null) {
          paramString1.put("gaid", m.a(paramContext));
        }
        if (paramString1.get("aid") == null) {
          paramString1.put("aid", m.b(paramContext));
        }
        paramContext = new JSONObject();
        paramString1 = paramString1.entrySet().iterator();
        while (paramString1.hasNext())
        {
          paramString2 = (Map.Entry)paramString1.next();
          paramContext.put((String)paramString2.getKey(), paramString2.getValue());
        }
        localJSONObject.put("extra", paramContext);
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        return null;
      }
      localJSONObject.put("ts", System.currentTimeMillis());
      paramContext = localJSONObject.toString();
      return paramContext;
    }
    
    public static <T extends fj> String a(T paramT)
    {
      if (paramT == null) {
        return "";
      }
      StringBuffer localStringBuffer = new StringBuffer();
      try
      {
        a(null, paramT, new StringBuffer(), localStringBuffer);
        return localStringBuffer.toString();
      }
      catch (IllegalAccessException paramT)
      {
        return "Error printing proto: " + paramT.getMessage();
      }
      catch (InvocationTargetException paramT) {}
      return "Error printing proto: " + paramT.getMessage();
    }
    
    public static String a(String paramString)
    {
      int k = 0;
      try
      {
        Object localObject = MessageDigest.getInstance("SHA-1");
        ((MessageDigest)localObject).reset();
        ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
        localObject = ((MessageDigest)localObject).digest();
        paramString = new Formatter();
        int m = localObject.length;
        while (k < m)
        {
          paramString.format("%02x", new Object[] { Byte.valueOf(localObject[k]) });
          k += 1;
        }
        localObject = paramString.toString();
        paramString.close();
        return localObject;
      }
      catch (Exception paramString)
      {
        com.appsflyer.b.a();
      }
      return null;
    }
    
    public static String a(String paramString1, String paramString2)
    {
      if (TextUtils.isEmpty(paramString1)) {
        return paramString2;
      }
      if (TextUtils.isEmpty(paramString2)) {
        return paramString1;
      }
      return "(" + paramString1 + ") AND (" + paramString2 + ")";
    }
    
    public static String a(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null) {
        return null;
      }
      return Base64.encodeToString(paramArrayOfByte, 0);
    }
    
    private static Map<String, s<ic, Integer>> a(Context paramContext, ie paramIe)
    {
      paramContext = paramContext.getResources();
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      if ((paramIe != null) && (paramIe.b == 1))
      {
        paramIe = paramIe.c;
        NumberFormat localNumberFormat = NumberFormat.getInstance();
        if ((paramIe != null) && (paramIe.length > 0))
        {
          int m = paramIe.length;
          int k = 0;
          while (k < m)
          {
            Object localObject = paramIe[k];
            localObject.c = paramContext.getString(2131099874, new Object[] { localNumberFormat.format(localObject.d) });
            localLinkedHashMap.put(localObject.b, new s(localObject, Integer.valueOf(k)));
            k += 1;
          }
        }
      }
      return localLinkedHashMap;
    }
    
    public static Map<String, s<ic, Integer>> a(Context paramContext, boolean paramBoolean)
    {
      long l = f(paramContext, "hot_apps_response.info");
      Object localObject2 = null;
      Object localObject1 = localObject2;
      Object localObject3;
      boolean bool;
      int k;
      if (l != 0L)
      {
        localObject1 = localObject2;
        if (System.currentTimeMillis() - l < 86400000L)
        {
          localObject2 = k(paramContext);
          localObject3 = m(paramContext);
          localObject1 = localObject2;
          if (localObject3 != null) {
            if (localObject2 == localObject3)
            {
              bool = true;
              localObject1 = localObject2;
              if (!bool) {
                break label439;
              }
              k = 1;
              localObject1 = localObject2;
            }
          }
        }
      }
      for (;;)
      {
        if (k != 0)
        {
          localObject1 = a(paramContext, l(paramContext));
          return localObject1;
          if (localObject3 == null)
          {
            bool = false;
            break;
          }
          if (localObject2.getClass() != localObject3.getClass())
          {
            bool = false;
            break;
          }
          k = ((fj)localObject2).b();
          if (((fj)localObject3).b() != k)
          {
            bool = false;
            break;
          }
          localObject1 = new byte[k];
          byte[] arrayOfByte = new byte[k];
          fj.a((fj)localObject2, (byte[])localObject1, k);
          fj.a((fj)localObject3, arrayOfByte, k);
          bool = Arrays.equals((byte[])localObject1, arrayOfByte);
          break;
        }
        bool = ad.d(paramContext);
        if ((paramBoolean) && (bool))
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = k(paramContext);
          }
          localObject1 = a(paramContext, (id)localObject2);
          if ((localObject1 != null) && (((ie)localObject1).b == 1))
          {
            if (localObject2 != null)
            {
              localObject2 = id.a((fj)localObject2);
              if (!c((byte[])localObject2)) {
                a(paramContext, "hot_apps_request.info", (byte[])localObject2);
              }
            }
            if (localObject1 != null)
            {
              localObject2 = ie.a((fj)localObject1);
              if (!c((byte[])localObject2)) {
                a(paramContext, "hot_apps_response.info", (byte[])localObject2);
              }
            }
            z.a().a("last_acquire_hot_apps_time", System.currentTimeMillis());
            return a(paramContext, (ie)localObject1);
          }
        }
        localObject3 = paramContext.getResources().getStringArray(2131165186);
        localObject2 = new LinkedHashMap(localObject3.length);
        int m = localObject3.length;
        k = 0;
        for (;;)
        {
          localObject1 = localObject2;
          if (k >= m) {
            break;
          }
          localObject1 = new ic();
          ((ic)localObject1).b = localObject3[k];
          ((ic)localObject1).c = paramContext.getString(2131099874, new Object[] { i(((ic)localObject1).b) });
          ((LinkedHashMap)localObject2).put(((ic)localObject1).b, new s(localObject1, Integer.valueOf(k)));
          k += 1;
        }
        label439:
        k = 0;
      }
    }
    
    public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, Map<String, String> paramMap)
    {
      int m = 0;
      int k = -1;
      switch (paramString1.hashCode())
      {
      default: 
        switch (k)
        {
        default: 
          label72:
          k = 1;
        }
        break;
      }
      for (;;)
      {
        if (k != 0) {
          lb.a(paramContext, paramString1, paramString2, paramString3, paramString4, paramMap);
        }
        return;
        if (!paramString1.equals("ad_getadresource")) {
          break;
        }
        k = 0;
        break;
        if (!paramString1.equals("ad_show")) {
          break;
        }
        k = 1;
        break;
        if (!paramString1.equals("ad_click")) {
          break;
        }
        k = 2;
        break;
        if (ky.h() != 0) {
          break label72;
        }
        k = m;
        continue;
        if (ky.f() != 0) {
          break label72;
        }
        k = m;
        continue;
        if (ky.g() != 0) {
          break label72;
        }
        k = m;
      }
    }
    
    public static void a(Context paramContext, List<gi> paramList)
    {
      if ((paramContext == null) || (paramList == null)) {
        return;
      }
      a(paramList);
    }
    
    public static void a(Bitmap paramBitmap, ImageView paramImageView, ac.a paramA)
    {
      if ((paramImageView != null) && (paramBitmap != null))
      {
        Canvas localCanvas = new Canvas(Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888));
        localCanvas.translate(-paramImageView.getLeft(), -paramImageView.getTop());
        localCanvas.scale(1.0F, 1.0F);
        Paint localPaint = new Paint();
        localPaint.setFlags(2);
        localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
        new ac(paramBitmap, paramImageView, paramA).a();
      }
    }
    
    private static void a(Path paramPath, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean1, boolean paramBoolean2)
    {
      double d6;
      double d11;
      double d12;
      double d5;
      double d7;
      double d4;
      double d9;
      double d8;
      double d3;
      double d2;
      double d13;
      for (;;)
      {
        d6 = Math.toRadians(paramFloat7);
        d11 = Math.cos(d6);
        d12 = Math.sin(d6);
        d5 = (paramFloat1 * d11 + paramFloat2 * d12) / paramFloat5;
        d7 = (-paramFloat1 * d12 + paramFloat2 * d11) / paramFloat6;
        d1 = (paramFloat3 * d11 + paramFloat4 * d12) / paramFloat5;
        d4 = (-paramFloat3 * d12 + paramFloat4 * d11) / paramFloat6;
        d9 = d5 - d1;
        d8 = d7 - d4;
        d3 = (d5 + d1) / 2.0D;
        d2 = (d7 + d4) / 2.0D;
        d10 = d9 * d9 + d8 * d8;
        if (d10 == 0.0D) {
          return;
        }
        d13 = 1.0D / d10 - 0.25D;
        if (d13 >= 0.0D) {
          break;
        }
        float f1 = (float)(Math.sqrt(d10) / 1.99999D);
        paramFloat5 *= f1;
        paramFloat6 *= f1;
      }
      double d10 = Math.sqrt(d13);
      d9 *= d10;
      d8 *= d10;
      if (paramBoolean1 == paramBoolean2)
      {
        d3 -= d8;
        d2 = d9 + d2;
        label239:
        d5 = Math.atan2(d7 - d2, d5 - d3);
        d4 = Math.atan2(d4 - d2, d1 - d3) - d5;
        if (d4 < 0.0D) {
          break label713;
        }
        paramBoolean1 = true;
        label282:
        d1 = d4;
        if (paramBoolean2 != paramBoolean1) {
          if (d4 <= 0.0D) {
            break label719;
          }
        }
      }
      label713:
      label719:
      for (double d1 = d4 - 6.283185307179586D;; d1 = d4 + 6.283185307179586D)
      {
        d13 = d3 * paramFloat5;
        double d14 = d2 * paramFloat6;
        double d15 = paramFloat5;
        double d16 = paramFloat6;
        d4 = paramFloat1;
        d3 = paramFloat2;
        int m = (int)Math.ceil(Math.abs(4.0D * d1 / 3.141592653589793D));
        double d17 = Math.cos(d6);
        double d18 = Math.sin(d6);
        d6 = Math.cos(d5);
        d7 = Math.sin(d5);
        d2 = -d15 * d17 * d7 - d16 * d18 * d6;
        d6 = d6 * (d16 * d17) + d7 * (-d15 * d18);
        double d19 = d1 / m;
        int k = 0;
        d1 = d6;
        while (k < m)
        {
          d8 = d5 + d19;
          d10 = Math.sin(d8);
          double d20 = Math.cos(d8);
          d6 = d15 * d17 * d20 + (d13 * d11 - d14 * d12) - d16 * d18 * d10;
          d7 = d16 * d17 * d10 + (d15 * d18 * d20 + (d13 * d12 + d14 * d11));
          d9 = -d15 * d17 * d10 - d16 * d18 * d20;
          d10 = d10 * (-d15 * d18) + d20 * (d16 * d17);
          d20 = Math.tan((d8 - d5) / 2.0D);
          d5 = Math.sin(d8 - d5) * (Math.sqrt(d20 * (3.0D * d20) + 4.0D) - 1.0D) / 3.0D;
          paramPath.cubicTo((float)(d2 * d5 + d4), (float)(d1 * d5 + d3), (float)(d6 - d5 * d9), (float)(d7 - d5 * d10), (float)d6, (float)d7);
          d1 = d10;
          d2 = d9;
          d5 = d8;
          k += 1;
          d3 = d7;
          d4 = d6;
        }
        break;
        d3 = d8 + d3;
        d2 -= d9;
        break label239;
        paramBoolean1 = false;
        break label282;
      }
    }
    
    public static void a(Parcel paramParcel, double paramDouble)
    {
      c(paramParcel, 5, 8);
      paramParcel.writeDouble(paramDouble);
    }
    
    public static void a(Parcel paramParcel, float paramFloat)
    {
      c(paramParcel, 15, 4);
      paramParcel.writeFloat(paramFloat);
    }
    
    public static void a(Parcel paramParcel, int paramInt1, int paramInt2)
    {
      c(paramParcel, paramInt1, 4);
      paramParcel.writeInt(paramInt2);
    }
    
    public static void a(Parcel paramParcel, int paramInt, long paramLong)
    {
      c(paramParcel, paramInt, 8);
      paramParcel.writeLong(paramLong);
    }
    
    public static void a(Parcel paramParcel, int paramInt, Bundle paramBundle)
    {
      if (paramBundle == null) {
        return;
      }
      paramInt = s(paramParcel, paramInt);
      paramParcel.writeBundle(paramBundle);
      t(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return;
      }
      paramInt = s(paramParcel, paramInt);
      paramParcel.writeStrongBinder(paramIBinder);
      t(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt1, Parcelable paramParcelable, int paramInt2)
    {
      if (paramParcelable == null) {
        return;
      }
      paramInt1 = s(paramParcel, paramInt1);
      paramParcelable.writeToParcel(paramParcel, paramInt2);
      t(paramParcel, paramInt1);
    }
    
    public static void a(Parcel paramParcel, int paramInt, String paramString)
    {
      if (paramString == null) {
        return;
      }
      paramInt = s(paramParcel, paramInt);
      paramParcel.writeString(paramString);
      t(paramParcel, paramInt);
    }
    
    public static <T extends Parcelable> void a(Parcel paramParcel, int paramInt, List<T> paramList)
    {
      if (paramList == null) {
        return;
      }
      int k = s(paramParcel, paramInt);
      int m = paramList.size();
      paramParcel.writeInt(m);
      paramInt = 0;
      if (paramInt < m)
      {
        Parcelable localParcelable = (Parcelable)paramList.get(paramInt);
        if (localParcelable == null) {
          paramParcel.writeInt(0);
        }
        for (;;)
        {
          paramInt += 1;
          break;
          a(paramParcel, localParcelable, 0);
        }
      }
      t(paramParcel, k);
    }
    
    public static void a(Parcel paramParcel, int paramInt, boolean paramBoolean)
    {
      c(paramParcel, paramInt, 4);
      if (paramBoolean) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
    
    public static <T extends Parcelable> void a(Parcel paramParcel, int paramInt1, T[] paramArrayOfT, int paramInt2)
    {
      if (paramArrayOfT == null) {
        return;
      }
      int k = s(paramParcel, paramInt1);
      int m = paramArrayOfT.length;
      paramParcel.writeInt(m);
      paramInt1 = 0;
      if (paramInt1 < m)
      {
        T ? = paramArrayOfT[paramInt1];
        if (? == null) {
          paramParcel.writeInt(0);
        }
        for (;;)
        {
          paramInt1 += 1;
          break;
          a(paramParcel, ?, paramInt2);
        }
      }
      t(paramParcel, k);
    }
    
    public static void a(Parcel paramParcel1, Parcel paramParcel2)
    {
      if (paramParcel2 == null) {
        return;
      }
      int k = s(paramParcel1, 2);
      paramParcel1.appendFrom(paramParcel2, 0, paramParcel2.dataSize());
      t(paramParcel1, k);
    }
    
    private static <T extends Parcelable> void a(Parcel paramParcel, T paramT, int paramInt)
    {
      int k = paramParcel.dataPosition();
      paramParcel.writeInt(1);
      int m = paramParcel.dataPosition();
      paramT.writeToParcel(paramParcel, paramInt);
      paramInt = paramParcel.dataPosition();
      paramParcel.setDataPosition(k);
      paramParcel.writeInt(paramInt - m);
      paramParcel.setDataPosition(paramInt);
    }
    
    public static void a(Parcel paramParcel, List<String> paramList)
    {
      if (paramList == null) {
        return;
      }
      int k = s(paramParcel, 6);
      paramParcel.writeStringList(paramList);
      t(paramParcel, k);
    }
    
    public static void a(Parcel paramParcel, byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null) {
        return;
      }
      int k = s(paramParcel, 7);
      paramParcel.writeByteArray(paramArrayOfByte);
      t(paramParcel, k);
    }
    
    public static void a(Parcel paramParcel, String[] paramArrayOfString)
    {
      if (paramArrayOfString == null) {
        return;
      }
      int k = s(paramParcel, 4);
      paramParcel.writeStringArray(paramArrayOfString);
      t(paramParcel, k);
    }
    
    public static void a(gi paramGi)
    {
      if (paramGi == null) {}
      while ((!(paramGi instanceof gl)) || (TextUtils.isEmpty(paramGi.o()))) {
        return;
      }
      gr localGr = new gr();
      localGr.a(paramGi.l());
      localGr.a(new ArrayList(Arrays.asList(new String[] { paramGi.o() })));
      localGr.a(Math.max(15000L, paramGi.p()));
      localGr.b(300000L);
      Bundle localBundle2 = localGr.f();
      if (paramGi == null) {}
      Bundle localBundle1;
      for (paramGi = null;; paramGi = localBundle1)
      {
        localBundle2.putAll(paramGi);
        localGr.a(new r(null));
        gn.a(DAApp.o()).a(localGr);
        return;
        localBundle1 = new Bundle();
        localBundle1.putString("appId", paramGi.h());
        localBundle1.putString("pkgName", paramGi.l());
        localBundle1.putString("adSource", String.valueOf(paramGi.m()));
        localBundle1.putString("pageId", String.valueOf(paramGi.r().getInt("pageId")));
      }
    }
    
    private static void a(Closeable paramCloseable)
    {
      if (paramCloseable != null) {}
      try
      {
        paramCloseable.close();
        return;
      }
      catch (IOException paramCloseable) {}
    }
    
    private static void a(File paramFile, ZipOutputStream paramZipOutputStream, String paramString)
      throws IOException
    {
      if ((paramFile.exists()) && (paramFile.isFile()))
      {
        byte[] arrayOfByte = new byte['䀀'];
        paramFile = new BufferedInputStream(new FileInputStream(paramFile), 16384);
        paramZipOutputStream.putNextEntry(new ZipEntry(paramString));
        for (;;)
        {
          int k = paramFile.read(arrayOfByte);
          if (k == -1) {
            break;
          }
          paramZipOutputStream.write(arrayOfByte, 0, k);
        }
        paramFile.close();
        paramZipOutputStream.flush();
        paramZipOutputStream.closeEntry();
      }
    }
    
    public static void a(Object paramObject, StringBuilder paramStringBuilder)
    {
      if (paramObject == null)
      {
        paramStringBuilder.append("null");
        return;
      }
      String str2 = paramObject.getClass().getSimpleName();
      String str1;
      if (str2 != null)
      {
        str1 = str2;
        if (str2.length() > 0) {}
      }
      else
      {
        str2 = paramObject.getClass().getName();
        int k = str2.lastIndexOf('.');
        str1 = str2;
        if (k > 0) {
          str1 = str2.substring(k + 1);
        }
      }
      paramStringBuilder.append(str1);
      paramStringBuilder.append('{');
      paramStringBuilder.append(Integer.toHexString(System.identityHashCode(paramObject)));
    }
    
    private static void a(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
      throws IllegalAccessException, InvocationTargetException
    {
      if (paramObject != null)
      {
        if (!(paramObject instanceof fj)) {
          break label417;
        }
        int i1 = paramStringBuffer1.length();
        if (paramString != null)
        {
          paramStringBuffer2.append(paramStringBuffer1).append(g(paramString)).append(" <\n");
          paramStringBuffer1.append("  ");
        }
        Class localClass = paramObject.getClass();
        Object localObject1 = localClass.getFields();
        int i2 = localObject1.length;
        int k = 0;
        String str;
        Object localObject2;
        while (k < i2)
        {
          Object localObject3 = localObject1[k];
          m = localObject3.getModifiers();
          str = localObject3.getName();
          if (((m & 0x1) == 1) && ((m & 0x8) != 8) && (!str.startsWith("_")) && (!str.endsWith("_")))
          {
            localObject2 = localObject3.getType();
            localObject3 = localObject3.get(paramObject);
            if ((((Class)localObject2).isArray()) && (((Class)localObject2).getComponentType() != Byte.TYPE))
            {
              if (localObject3 == null) {}
              for (m = 0;; m = Array.getLength(localObject3))
              {
                int n = 0;
                while (n < m)
                {
                  a(str, Array.get(localObject3, n), paramStringBuffer1, paramStringBuffer2);
                  n += 1;
                }
              }
            }
            a(str, localObject3, paramStringBuffer1, paramStringBuffer2);
          }
          k += 1;
        }
        localObject1 = localClass.getMethods();
        int m = localObject1.length;
        k = 0;
        while (k < m)
        {
          str = localObject1[k].getName();
          if (str.startsWith("set")) {
            str = str.substring(3);
          }
          for (;;)
          {
            try
            {
              localObject2 = localClass.getMethod("has" + str, new Class[0]);
              if (!((Boolean)((Method)localObject2).invoke(paramObject, new Object[0])).booleanValue()) {}
            }
            catch (NoSuchMethodException localNoSuchMethodException2)
            {
              continue;
            }
            try
            {
              localObject2 = localClass.getMethod("get" + str, new Class[0]);
              a(str, ((Method)localObject2).invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
            }
            catch (NoSuchMethodException localNoSuchMethodException1) {}
          }
          k += 1;
        }
        if (paramString != null)
        {
          paramStringBuffer1.setLength(i1);
          paramStringBuffer2.append(paramStringBuffer1).append(">\n");
        }
      }
      return;
      label417:
      paramString = g(paramString);
      paramStringBuffer2.append(paramStringBuffer1).append(paramString).append(": ");
      if ((paramObject instanceof String))
      {
        paramObject = (String)paramObject;
        paramString = paramObject;
        if (!paramObject.startsWith("http"))
        {
          paramString = paramObject;
          if (paramObject.length() > 200) {
            paramString = paramObject.substring(0, 200) + "[...]";
          }
        }
        paramString = h(paramString);
        paramStringBuffer2.append("\"").append(paramString).append("\"");
      }
      for (;;)
      {
        paramStringBuffer2.append("\n");
        return;
        if ((paramObject instanceof byte[])) {
          a((byte[])paramObject, paramStringBuffer2);
        } else {
          paramStringBuffer2.append(paramObject);
        }
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, double[] paramArrayOfDouble)
    {
      int m = paramArrayOfDouble.length;
      int k = 0;
      while (k < m)
      {
        if (k != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Double.toString(paramArrayOfDouble[k]));
        k += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, float[] paramArrayOfFloat)
    {
      int m = paramArrayOfFloat.length;
      int k = 0;
      while (k < m)
      {
        if (k != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Float.toString(paramArrayOfFloat[k]));
        k += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, long[] paramArrayOfLong)
    {
      int m = paramArrayOfLong.length;
      int k = 0;
      while (k < m)
      {
        if (k != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Long.toString(paramArrayOfLong[k]));
        k += 1;
      }
    }
    
    public static <T> void a(StringBuilder paramStringBuilder, T[] paramArrayOfT)
    {
      int m = paramArrayOfT.length;
      int k = 0;
      while (k < m)
      {
        if (k != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(paramArrayOfT[k].toString());
        k += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, String[] paramArrayOfString)
    {
      int m = paramArrayOfString.length;
      int k = 0;
      while (k < m)
      {
        if (k != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append("\"").append(paramArrayOfString[k]).append("\"");
        k += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, boolean[] paramArrayOfBoolean)
    {
      int m = paramArrayOfBoolean.length;
      int k = 0;
      while (k < m)
      {
        if (k != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Boolean.toString(paramArrayOfBoolean[k]));
        k += 1;
      }
    }
    
    public static void a(List<gi> paramList)
    {
      if (paramList == null) {}
      for (;;)
      {
        return;
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          gi localGi = (gi)paramList.next();
          if (((localGi instanceof gl)) && (!TextUtils.isEmpty(localGi.o()))) {
            a(localGi);
          }
        }
      }
    }
    
    public static void a(List<File> paramList, File paramFile)
      throws IOException
    {
      paramFile = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(paramFile), 16384));
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        File localFile = (File)paramList.next();
        a(localFile, paramFile, localFile.getName());
      }
      paramFile.close();
    }
    
    private static void a(byte[] paramArrayOfByte, StringBuffer paramStringBuffer)
    {
      if (paramArrayOfByte == null)
      {
        paramStringBuffer.append("\"\"");
        return;
      }
      paramStringBuffer.append('"');
      int k = 0;
      if (k < paramArrayOfByte.length)
      {
        int m = paramArrayOfByte[k] & 0xFF;
        if ((m == 92) || (m == 34)) {
          paramStringBuffer.append('\\').append((char)m);
        }
        for (;;)
        {
          k += 1;
          break;
          if ((m >= 32) && (m < 127)) {
            paramStringBuffer.append((char)m);
          } else {
            paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(m) }));
          }
        }
      }
      paramStringBuffer.append('"');
    }
    
    public static void a(a[] paramArrayOfA, Path paramPath)
    {
      float[] arrayOfFloat1 = new float[6];
      int n = 0;
      int m = 109;
      float[] arrayOfFloat2;
      float f4;
      float f3;
      float f6;
      float f1;
      float f2;
      int i1;
      float f7;
      float f8;
      label270:
      label462:
      float f9;
      if (n < paramArrayOfA.length)
      {
        int i2 = paramArrayOfA[n].a;
        arrayOfFloat2 = paramArrayOfA[n].b;
        f4 = arrayOfFloat1[0];
        f3 = arrayOfFloat1[1];
        f6 = arrayOfFloat1[2];
        f5 = arrayOfFloat1[3];
        f1 = arrayOfFloat1[4];
        f2 = arrayOfFloat1[5];
        int k;
        switch (i2)
        {
        default: 
          k = 2;
          i1 = 0;
          f7 = f3;
          f3 = f4;
          f4 = f7;
          f7 = f6;
          f8 = f5;
          if (i1 < arrayOfFloat2.length) {
            switch (i2)
            {
            default: 
              f5 = f2;
              f2 = f4;
              f4 = f3;
              f3 = f2;
              f2 = f1;
              f6 = f7;
              f1 = f5;
              f5 = f8;
            }
          }
          break;
        case 90: 
        case 122: 
        case 76: 
        case 77: 
        case 84: 
        case 108: 
        case 109: 
        case 116: 
        case 72: 
        case 86: 
        case 104: 
        case 118: 
        case 67: 
        case 99: 
        case 81: 
        case 83: 
        case 113: 
        case 115: 
        case 65: 
        case 97: 
          for (;;)
          {
            i1 += k;
            f9 = f2;
            float f10 = f4;
            m = i2;
            f8 = f5;
            f7 = f6;
            f2 = f1;
            f1 = f9;
            f4 = f3;
            f3 = f10;
            break label270;
            paramPath.close();
            paramPath.moveTo(f1, f2);
            f5 = f2;
            f6 = f1;
            f3 = f2;
            f4 = f1;
            k = 2;
            break;
            k = 2;
            break;
            k = 1;
            break;
            k = 6;
            break;
            k = 4;
            break;
            k = 7;
            break;
            f3 += arrayOfFloat2[i1];
            f4 += arrayOfFloat2[(i1 + 1)];
            if (i1 > 0)
            {
              paramPath.rLineTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
              f9 = f3;
              f3 = f2;
              f2 = f1;
              f5 = f8;
              f1 = f3;
              f6 = f7;
              f3 = f4;
              f4 = f9;
            }
            else
            {
              paramPath.rMoveTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
              f10 = f4;
              f9 = f3;
              f5 = f8;
              f1 = f4;
              f6 = f7;
              f2 = f3;
              f3 = f10;
              f4 = f9;
              continue;
              f3 = arrayOfFloat2[i1];
              f4 = arrayOfFloat2[(i1 + 1)];
              if (i1 > 0)
              {
                paramPath.lineTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
                f9 = f3;
                f3 = f2;
                f2 = f1;
                f5 = f8;
                f1 = f3;
                f6 = f7;
                f3 = f4;
                f4 = f9;
              }
              else
              {
                paramPath.moveTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
                f10 = f4;
                f9 = f3;
                f5 = f8;
                f1 = f4;
                f6 = f7;
                f2 = f3;
                f3 = f10;
                f4 = f9;
                continue;
                paramPath.rLineTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
                f5 = arrayOfFloat2[i1];
                f4 += arrayOfFloat2[(i1 + 1)];
                f9 = f3 + f5;
                f3 = f2;
                f2 = f1;
                f5 = f8;
                f1 = f3;
                f6 = f7;
                f3 = f4;
                f4 = f9;
                continue;
                paramPath.lineTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
                f4 = arrayOfFloat2[i1];
                f9 = arrayOfFloat2[(i1 + 1)];
                f3 = f2;
                f2 = f1;
                f5 = f8;
                f1 = f3;
                f6 = f7;
                f3 = f9;
                continue;
                paramPath.rLineTo(arrayOfFloat2[i1], 0.0F);
                f5 = arrayOfFloat2[i1];
                f9 = f3 + f5;
                f3 = f1;
                f5 = f8;
                f1 = f2;
                f6 = f7;
                f2 = f3;
                f3 = f4;
                f4 = f9;
                continue;
                paramPath.lineTo(arrayOfFloat2[i1], f4);
                f5 = arrayOfFloat2[i1];
                f3 = f4;
                f4 = f5;
                f9 = f1;
                f5 = f8;
                f1 = f2;
                f6 = f7;
                f2 = f9;
                continue;
                paramPath.rLineTo(0.0F, arrayOfFloat2[i1]);
                f5 = arrayOfFloat2[i1];
                f9 = f1;
                f10 = f4 + f5;
                f4 = f3;
                f1 = f2;
                f5 = f8;
                f6 = f7;
                f2 = f9;
                f3 = f10;
                continue;
                paramPath.lineTo(f3, arrayOfFloat2[i1]);
                f9 = arrayOfFloat2[i1];
                f4 = f1;
                f10 = f3;
                f1 = f2;
                f5 = f8;
                f6 = f7;
                f2 = f4;
                f3 = f9;
                f4 = f10;
                continue;
                paramPath.rCubicTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)], arrayOfFloat2[(i1 + 2)], arrayOfFloat2[(i1 + 3)], arrayOfFloat2[(i1 + 4)], arrayOfFloat2[(i1 + 5)]);
                f6 = f3 + arrayOfFloat2[(i1 + 2)];
                f5 = f4 + arrayOfFloat2[(i1 + 3)];
                f7 = arrayOfFloat2[(i1 + 4)];
                f4 += arrayOfFloat2[(i1 + 5)];
                f7 = f3 + f7;
                f3 = f2;
                f2 = f1;
                f1 = f3;
                f3 = f4;
                f4 = f7;
                continue;
                paramPath.cubicTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)], arrayOfFloat2[(i1 + 2)], arrayOfFloat2[(i1 + 3)], arrayOfFloat2[(i1 + 4)], arrayOfFloat2[(i1 + 5)]);
                f4 = arrayOfFloat2[(i1 + 4)];
                f3 = arrayOfFloat2[(i1 + 5)];
                f7 = arrayOfFloat2[(i1 + 2)];
                f5 = arrayOfFloat2[(i1 + 3)];
                f6 = f2;
                f2 = f1;
                f1 = f6;
                f6 = f7;
              }
            }
          }
          f6 = 0.0F;
          if ((m == 99) || (m == 115) || (m == 67) || (m == 83)) {
            f6 = f4 - f8;
          }
          break;
        }
      }
      for (float f5 = f3 - f7;; f5 = 0.0F)
      {
        paramPath.rCubicTo(f5, f6, arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)], arrayOfFloat2[(i1 + 2)], arrayOfFloat2[(i1 + 3)]);
        f6 = f3 + arrayOfFloat2[i1];
        f5 = f4 + arrayOfFloat2[(i1 + 1)];
        f7 = arrayOfFloat2[(i1 + 2)];
        f4 += arrayOfFloat2[(i1 + 3)];
        f7 = f3 + f7;
        f3 = f2;
        f2 = f1;
        f1 = f3;
        f3 = f4;
        f4 = f7;
        break label462;
        if ((m == 99) || (m == 115) || (m == 67) || (m == 83))
        {
          f4 = 2.0F * f4 - f8;
          f3 = 2.0F * f3 - f7;
        }
        for (;;)
        {
          paramPath.cubicTo(f3, f4, arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)], arrayOfFloat2[(i1 + 2)], arrayOfFloat2[(i1 + 3)]);
          f6 = arrayOfFloat2[i1];
          f5 = arrayOfFloat2[(i1 + 1)];
          f4 = arrayOfFloat2[(i1 + 2)];
          f7 = arrayOfFloat2[(i1 + 3)];
          f3 = f2;
          f2 = f1;
          f1 = f3;
          f3 = f7;
          break label462;
          paramPath.rQuadTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)], arrayOfFloat2[(i1 + 2)], arrayOfFloat2[(i1 + 3)]);
          f6 = f3 + arrayOfFloat2[i1];
          f5 = f4 + arrayOfFloat2[(i1 + 1)];
          f7 = arrayOfFloat2[(i1 + 2)];
          f4 += arrayOfFloat2[(i1 + 3)];
          f7 = f3 + f7;
          f3 = f2;
          f2 = f1;
          f1 = f3;
          f3 = f4;
          f4 = f7;
          break label462;
          paramPath.quadTo(arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)], arrayOfFloat2[(i1 + 2)], arrayOfFloat2[(i1 + 3)]);
          f6 = arrayOfFloat2[i1];
          f5 = arrayOfFloat2[(i1 + 1)];
          f4 = arrayOfFloat2[(i1 + 2)];
          f7 = arrayOfFloat2[(i1 + 3)];
          f3 = f2;
          f2 = f1;
          f1 = f3;
          f3 = f7;
          break label462;
          if ((m == 113) || (m == 116) || (m == 81) || (m == 84)) {
            f5 = f4 - f8;
          }
          for (f6 = f3 - f7;; f6 = 0.0F)
          {
            paramPath.rQuadTo(f6, f5, arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
            f6 = f3 + f6;
            f5 = f4 + f5;
            f7 = arrayOfFloat2[i1];
            f4 += arrayOfFloat2[(i1 + 1)];
            f7 = f3 + f7;
            f3 = f2;
            f2 = f1;
            f1 = f3;
            f3 = f4;
            f4 = f7;
            break label462;
            if ((m != 113) && (m != 116) && (m != 81))
            {
              f6 = f4;
              f5 = f3;
              if (m != 84) {}
            }
            else
            {
              f5 = 2.0F * f3 - f7;
              f6 = 2.0F * f4 - f8;
            }
            paramPath.quadTo(f5, f6, arrayOfFloat2[i1], arrayOfFloat2[(i1 + 1)]);
            f4 = arrayOfFloat2[i1];
            f3 = arrayOfFloat2[(i1 + 1)];
            f7 = f6;
            f6 = f5;
            f8 = f2;
            f2 = f1;
            f5 = f7;
            f1 = f8;
            break label462;
            f5 = arrayOfFloat2[(i1 + 5)];
            f6 = arrayOfFloat2[(i1 + 6)];
            f7 = arrayOfFloat2[i1];
            f8 = arrayOfFloat2[(i1 + 1)];
            f9 = arrayOfFloat2[(i1 + 2)];
            boolean bool1;
            if (arrayOfFloat2[(i1 + 3)] != 0.0F)
            {
              bool1 = true;
              label2082:
              if (arrayOfFloat2[(i1 + 4)] == 0.0F) {
                break label2175;
              }
            }
            label2175:
            for (boolean bool2 = true;; bool2 = false)
            {
              a(paramPath, f3, f4, f5 + f3, f6 + f4, f7, f8, f9, bool1, bool2);
              f6 = f3 + arrayOfFloat2[(i1 + 5)];
              f5 = f4 + arrayOfFloat2[(i1 + 6)];
              f7 = f2;
              f2 = f1;
              f3 = f5;
              f4 = f6;
              f1 = f7;
              break;
              bool1 = false;
              break label2082;
            }
            f5 = arrayOfFloat2[(i1 + 5)];
            f6 = arrayOfFloat2[(i1 + 6)];
            f7 = arrayOfFloat2[i1];
            f8 = arrayOfFloat2[(i1 + 1)];
            f9 = arrayOfFloat2[(i1 + 2)];
            if (arrayOfFloat2[(i1 + 3)] != 0.0F)
            {
              bool1 = true;
              label2240:
              if (arrayOfFloat2[(i1 + 4)] == 0.0F) {
                break label2321;
              }
            }
            label2321:
            for (bool2 = true;; bool2 = false)
            {
              a(paramPath, f3, f4, f5, f6, f7, f8, f9, bool1, bool2);
              f6 = arrayOfFloat2[(i1 + 5)];
              f5 = arrayOfFloat2[(i1 + 6)];
              f7 = f2;
              f2 = f1;
              f3 = f5;
              f4 = f6;
              f1 = f7;
              break;
              bool1 = false;
              break label2240;
            }
            arrayOfFloat1[0] = f3;
            arrayOfFloat1[1] = f4;
            arrayOfFloat1[2] = f7;
            arrayOfFloat1[3] = f8;
            arrayOfFloat1[4] = f1;
            arrayOfFloat1[5] = f2;
            m = paramArrayOfA[n].a;
            n += 1;
            break;
            return;
            f5 = 0.0F;
          }
        }
      }
    }
    
    public static boolean a()
    {
      return Build.VERSION.SDK_INT >= 24;
    }
    
    public static boolean a(int paramInt)
    {
      if (!ey.a(null).a(paramInt, "com.google.android.gms")) {
        return false;
      }
      throw new NullPointerException();
    }
    
    @TargetApi(20)
    public static boolean a(Context paramContext)
    {
      if (f == null) {
        if ((!b(20)) || (!paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch"))) {
          break label43;
        }
      }
      label43:
      for (boolean bool = true;; bool = false)
      {
        f = Boolean.valueOf(bool);
        return f.booleanValue();
      }
    }
    
    public static boolean a(Context paramContext, String paramString, byte[] paramArrayOfByte)
    {
      boolean bool1 = false;
      localObject = null;
      localContext = null;
      try
      {
        paramContext = paramContext.openFileOutput(paramString, 0);
        localContext = paramContext;
        localObject = paramContext;
        paramContext.write(paramArrayOfByte);
        localContext = paramContext;
        localObject = paramContext;
        paramContext.flush();
        boolean bool2 = true;
        bool1 = bool2;
        if (paramContext != null)
        {
          a(paramContext);
          bool1 = bool2;
        }
      }
      catch (Exception paramContext)
      {
        do
        {
          localObject = localContext;
          paramContext.printStackTrace();
        } while (localContext == null);
        a(localContext);
        return false;
      }
      finally
      {
        if (localObject == null) {
          break label87;
        }
        a((Closeable)localObject);
      }
      return bool1;
    }
    
    public static boolean a(ApplicationInfo paramApplicationInfo)
    {
      return paramApplicationInfo.uid < 10000;
    }
    
    public static boolean a(Exception paramException)
    {
      return (paramException != null) && (!paramException.toString().contains("NoConnectionError")) && (!paramException.toString().contains("TimeoutException"));
    }
    
    private static byte[] a(InputStream paramInputStream)
      throws IOException
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      a(paramInputStream, localByteArrayOutputStream, new byte['က']);
      return localByteArrayOutputStream.toByteArray();
    }
    
    private static byte[] a(String paramString, byte[] paramArrayOfByte)
    {
      if (TextUtils.isEmpty(paramString)) {
        return null;
      }
      int k = 0;
      for (;;)
      {
        if (k >= 3) {
          break label287;
        }
        try
        {
          Object localObject1 = new URL(paramString.trim());
          localObject2 = (HttpURLConnection)((URL)localObject1).openConnection();
          if (TextUtils.equals("https", ((URL)localObject1).getProtocol())) {
            ((HttpsURLConnection)localObject2).setSSLSocketFactory(com.lbe.parallel.utility.f.a().b());
          }
          ((HttpURLConnection)localObject2).setDoInput(true);
          ((HttpURLConnection)localObject2).setDoOutput(true);
          ((HttpURLConnection)localObject2).setUseCaches(false);
          ((HttpURLConnection)localObject2).setRequestMethod("POST");
          ((HttpURLConnection)localObject2).setRequestProperty("Content-Type", "UTF-8");
          ((HttpURLConnection)localObject2).setRequestProperty("Connection", "Keep-Alive");
          ((HttpURLConnection)localObject2).setRequestProperty("Charset", "UTF-8");
          ((HttpURLConnection)localObject2).addRequestProperty("LBE-ClienTime", System.currentTimeMillis());
          ((HttpURLConnection)localObject2).setConnectTimeout(10000);
          ((HttpURLConnection)localObject2).setReadTimeout(30000);
          ((HttpURLConnection)localObject2).getOutputStream().write(android.support.v4.app.b.a(paramArrayOfByte, 4.37213640136E7D));
          ((HttpURLConnection)localObject2).getOutputStream().flush();
          ((HttpURLConnection)localObject2).getOutputStream().close();
          byte[] arrayOfByte = new byte['Ѐ'];
          localObject1 = new ByteArrayOutputStream();
          localObject2 = ((HttpURLConnection)localObject2).getInputStream();
          for (;;)
          {
            int m = ((InputStream)localObject2).read(arrayOfByte);
            if (m == -1) {
              break;
            }
            ((ByteArrayOutputStream)localObject1).write(arrayOfByte, 0, m);
            ((ByteArrayOutputStream)localObject1).flush();
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          SystemClock.sleep(3000L);
          k += 1;
        }
      }
      Object localObject2 = android.support.v4.app.b.b(localException.toByteArray(), 4.37213640136E7D);
      localException.close();
      return localObject2;
      label287:
      return null;
    }
    
    public static int b(Parcel paramParcel)
    {
      return s(paramParcel, 20293);
    }
    
    public static <T> T b(T paramT)
    {
      if (paramT == null) {
        throw new NullPointerException();
      }
      return paramT;
    }
    
    public static String b()
    {
      return e;
    }
    
    /* Error */
    public static String b(String paramString1, String paramString2)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 6
      //   3: aconst_null
      //   4: astore 5
      //   6: aload_1
      //   7: astore 4
      //   9: aload 6
      //   11: astore_3
      //   12: aload_1
      //   13: invokestatic 451	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   16: ifne +24 -> 40
      //   19: aload 6
      //   21: astore_3
      //   22: new 134	java/lang/StringBuilder
      //   25: dup
      //   26: ldc -86
      //   28: invokespecial 763	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   31: aload_1
      //   32: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   35: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   38: astore 4
      //   40: aload 6
      //   42: astore_3
      //   43: new 174	java/net/URL
      //   46: dup
      //   47: new 134	java/lang/StringBuilder
      //   50: dup
      //   51: invokespecial 168	java/lang/StringBuilder:<init>	()V
      //   54: aload_0
      //   55: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   58: aload 4
      //   60: invokevirtual 143	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   63: invokevirtual 151	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   66: invokestatic 167	com/xinmei/adsdk/utils/m:b	(Ljava/lang/String;)Ljava/lang/String;
      //   69: invokespecial 175	java/net/URL:<init>	(Ljava/lang/String;)V
      //   72: invokevirtual 179	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   75: checkcast 181	java/net/HttpURLConnection
      //   78: astore_1
      //   79: aload 6
      //   81: astore_3
      //   82: aload_1
      //   83: sipush 30000
      //   86: invokevirtual 184	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   89: aload 6
      //   91: astore_3
      //   92: aload_1
      //   93: ldc -71
      //   95: invokevirtual 188	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   98: aload 6
      //   100: astore_3
      //   101: aload_1
      //   102: invokevirtual 1663	java/net/HttpURLConnection:connect	()V
      //   105: aload 6
      //   107: astore_3
      //   108: aload_1
      //   109: invokevirtual 238	java/net/HttpURLConnection:getResponseCode	()I
      //   112: istore_2
      //   113: iload_2
      //   114: iconst_m1
      //   115: if_icmpeq +33 -> 148
      //   118: iload_2
      //   119: sipush 200
      //   122: if_icmpeq +26 -> 148
      //   125: aload 6
      //   127: astore_3
      //   128: aload_0
      //   129: ldc_w 1665
      //   132: invokevirtual 1587	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   135: ifeq +10 -> 145
      //   138: iload_2
      //   139: invokestatic 1282	java/lang/String:valueOf	(I)Ljava/lang/String;
      //   142: astore_0
      //   143: aload_0
      //   144: areturn
      //   145: ldc -2
      //   147: areturn
      //   148: aload 6
      //   150: astore_3
      //   151: new 240	java/io/BufferedReader
      //   154: dup
      //   155: new 242	java/io/InputStreamReader
      //   158: dup
      //   159: aload_1
      //   160: invokevirtual 246	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   163: invokespecial 249	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
      //   166: invokespecial 252	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   169: astore_1
      //   170: new 841	java/lang/StringBuffer
      //   173: dup
      //   174: invokespecial 842	java/lang/StringBuffer:<init>	()V
      //   177: astore_3
      //   178: aload_1
      //   179: invokevirtual 257	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   182: astore 4
      //   184: aload 4
      //   186: ifnull +65 -> 251
      //   189: aload_3
      //   190: aload 4
      //   192: invokevirtual 1363	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   195: pop
      //   196: aload_3
      //   197: ldc_w 1449
      //   200: invokevirtual 1363	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
      //   203: pop
      //   204: goto -26 -> 178
      //   207: astore 4
      //   209: aload_1
      //   210: astore_3
      //   211: invokestatic 266	com/xinmei/adsdk/utils/m:f	()V
      //   214: aload_1
      //   215: astore_3
      //   216: aload_0
      //   217: ldc_w 1665
      //   220: invokevirtual 1587	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   223: ifeq +42 -> 265
      //   226: aload_1
      //   227: astore_3
      //   228: aload 4
      //   230: invokestatic 1670	com/lbe/parallel/lc:a	(Ljava/lang/Exception;)Ljava/lang/String;
      //   233: astore_0
      //   234: aload_0
      //   235: astore_3
      //   236: aload_3
      //   237: astore_0
      //   238: aload_1
      //   239: ifnull -96 -> 143
      //   242: aload_1
      //   243: invokevirtual 264	java/io/BufferedReader:close	()V
      //   246: aload_3
      //   247: areturn
      //   248: astore_0
      //   249: aload_3
      //   250: areturn
      //   251: aload_3
      //   252: invokevirtual 846	java/lang/StringBuffer:toString	()Ljava/lang/String;
      //   255: astore_3
      //   256: aload_1
      //   257: invokevirtual 264	java/io/BufferedReader:close	()V
      //   260: aload_3
      //   261: areturn
      //   262: astore_0
      //   263: aload_3
      //   264: areturn
      //   265: ldc -2
      //   267: astore_0
      //   268: aload_1
      //   269: ifnull -126 -> 143
      //   272: aload_1
      //   273: invokevirtual 264	java/io/BufferedReader:close	()V
      //   276: ldc -2
      //   278: areturn
      //   279: astore_0
      //   280: ldc -2
      //   282: areturn
      //   283: astore_0
      //   284: aload_3
      //   285: ifnull +7 -> 292
      //   288: aload_3
      //   289: invokevirtual 264	java/io/BufferedReader:close	()V
      //   292: aload_0
      //   293: athrow
      //   294: astore_1
      //   295: goto -3 -> 292
      //   298: astore_0
      //   299: aload_1
      //   300: astore_3
      //   301: goto -17 -> 284
      //   304: astore 4
      //   306: aload 5
      //   308: astore_1
      //   309: goto -100 -> 209
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	312	0	paramString1	String
      //   0	312	1	paramString2	String
      //   112	27	2	k	int
      //   11	290	3	localObject1	Object
      //   7	184	4	str	String
      //   207	22	4	localException1	Exception
      //   304	1	4	localException2	Exception
      //   4	303	5	localObject2	Object
      //   1	148	6	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   170	178	207	java/lang/Exception
      //   178	184	207	java/lang/Exception
      //   189	204	207	java/lang/Exception
      //   251	256	207	java/lang/Exception
      //   242	246	248	java/io/IOException
      //   256	260	262	java/io/IOException
      //   272	276	279	java/io/IOException
      //   12	19	283	finally
      //   22	40	283	finally
      //   43	79	283	finally
      //   82	89	283	finally
      //   92	98	283	finally
      //   101	105	283	finally
      //   108	113	283	finally
      //   128	138	283	finally
      //   151	170	283	finally
      //   211	214	283	finally
      //   216	226	283	finally
      //   228	234	283	finally
      //   288	292	294	java/io/IOException
      //   170	178	298	finally
      //   178	184	298	finally
      //   189	204	298	finally
      //   251	256	298	finally
      //   12	19	304	java/lang/Exception
      //   22	40	304	java/lang/Exception
      //   43	79	304	java/lang/Exception
      //   82	89	304	java/lang/Exception
      //   92	98	304	java/lang/Exception
      //   101	105	304	java/lang/Exception
      //   108	113	304	java/lang/Exception
      //   128	138	304	java/lang/Exception
      //   151	170	304	java/lang/Exception
    }
    
    public static String b(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null) {
        return null;
      }
      return Base64.encodeToString(paramArrayOfByte, 10);
    }
    
    public static void b(Context paramContext)
    {
      try
      {
        if (g == null) {
          g = Volley.newRequestQueue(paramContext);
        }
        if (i == null) {
          i = new ha(paramContext, g.getCache());
        }
        if (h == null) {
          h = new ImageLoader(g, i);
        }
        return;
      }
      catch (Exception paramContext) {}
    }
    
    public static void b(Parcel paramParcel, int paramInt)
    {
      paramParcel.setDataPosition(a(paramParcel, paramInt) + paramParcel.dataPosition());
    }
    
    private static void b(Parcel paramParcel, int paramInt1, int paramInt2)
    {
      paramInt1 = a(paramParcel, paramInt1);
      if (paramInt1 != paramInt2)
      {
        String str = String.valueOf(Integer.toHexString(paramInt1));
        throw new Fragment.a(String.valueOf(str).length() + 46 + "Expected size " + paramInt2 + " got " + paramInt1 + " (0x" + str + ")", paramParcel);
      }
    }
    
    public static void b(String paramString)
    {
      c = paramString;
      StringBuilder localStringBuilder = new StringBuilder();
      int k = 0;
      if (k < paramString.length())
      {
        if ((k == 0) || (k == 1) || (k > paramString.length() - 5)) {
          localStringBuilder.append(paramString.charAt(k));
        }
        for (;;)
        {
          k += 1;
          break;
          localStringBuilder.append("*");
        }
      }
      d = localStringBuilder.toString();
    }
    
    public static void b(List<PackageData> paramList)
    {
      for (;;)
      {
        int k;
        try
        {
          StringBuffer localStringBuffer = new StringBuffer();
          int m = paramList.size();
          k = 0;
          if (k < m)
          {
            String str = ((PackageData)paramList.get(k)).getPackageName();
            if (!TextUtils.isEmpty(str))
            {
              localStringBuffer.append(str);
              if (k != m - 1) {
                localStringBuffer.append(";");
              }
            }
          }
          else
          {
            paramList = localStringBuffer.toString();
            z.a().a("home_apps_ordered_list", paramList);
            return;
          }
        }
        finally {}
        k += 1;
      }
    }
    
    private static boolean b(int paramInt)
    {
      return Build.VERSION.SDK_INT >= paramInt;
    }
    
    @TargetApi(12)
    public static boolean b(Context paramContext, String paramString)
    {
      if (!b(12)) {}
      for (;;)
      {
        return false;
        try
        {
          int k = ey.a(paramContext).a(paramString, 0).flags;
          if ((k & 0x200000) != 0) {
            return true;
          }
        }
        catch (PackageManager.NameNotFoundException paramContext) {}
      }
      return false;
    }
    
    public static <T> T[] b(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = paramParcel.createTypedArray(paramCreator);
      paramParcel.setDataPosition(paramInt + k);
      return paramCreator;
    }
    
    public static hu c(Context paramContext)
    {
      hu localHu = new hu();
      localHu.e = "B1";
      Object localObject = a.a(paramContext, paramContext.getPackageName());
      localHu.b = ((PackageInfo)localObject).packageName;
      localHu.f = ad.g(paramContext);
      localHu.d = ((PackageInfo)localObject).versionCode;
      localHu.c = ((PackageInfo)localObject).versionName;
      localObject = paramContext.getPackageManager().getInstallerPackageName(((PackageInfo)localObject).packageName);
      paramContext = (Context)localObject;
      if (localObject == null) {
        paramContext = "";
      }
      localHu.g = paramContext;
      return localHu;
    }
    
    public static RecommendAdRequest c(Context paramContext, String paramString)
    {
      RecommendAdRequest localRecommendAdRequest = new RecommendAdRequest();
      String[] arrayOfString = com.lbe.multidroid.service.b.a(paramContext).c().d(DAApp.o().q());
      if (TextUtils.isEmpty(paramString))
      {
        localRecommendAdRequest.setOsNewPkg(z.a().d("system_package_added_set"));
        if (Math.abs(System.currentTimeMillis() - z.a().e(y.a)) >= 129600000L) {
          localRecommendAdRequest.setOsPkgs(a.c(paramContext));
        }
        if (arrayOfString != null) {
          break label135;
        }
      }
      label135:
      for (paramString = new ArrayList();; paramString = Arrays.asList(arrayOfString))
      {
        localRecommendAdRequest.setPsApps(paramString);
        localRecommendAdRequest.setOsRunning(a.d(paramContext));
        localRecommendAdRequest.setClientInfo(e(paramContext));
        localRecommendAdRequest.setDeviceInfo(f(paramContext));
        localRecommendAdRequest.setPsDevInfo(h(paramContext));
        return localRecommendAdRequest;
        localRecommendAdRequest.setPsAppQuit(paramString);
        break;
      }
    }
    
    public static <T> ArrayList<T> c(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = paramParcel.createTypedArrayList(paramCreator);
      paramParcel.setDataPosition(paramInt + k);
      return paramCreator;
    }
    
    private static void c(Parcel paramParcel, int paramInt1, int paramInt2)
    {
      if (paramInt2 >= 65535)
      {
        paramParcel.writeInt(0xFFFF0000 | paramInt1);
        paramParcel.writeInt(paramInt2);
        return;
      }
      paramParcel.writeInt(paramInt2 << 16 | paramInt1);
    }
    
    public static void c(String paramString)
    {
      if (c == null) {
        b(com.appsflyer.h.a().a("AppsFlyerKey"));
      }
      while ((c == null) || (!paramString.contains(c))) {
        return;
      }
      paramString.replace(c, d);
      com.appsflyer.b.a();
    }
    
    public static boolean c()
    {
      return b(18);
    }
    
    public static boolean c(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 4);
      return paramParcel.readInt() != 0;
    }
    
    public static boolean c(byte[] paramArrayOfByte)
    {
      return (paramArrayOfByte == null) || (paramArrayOfByte.length <= 0);
    }
    
    public static int d(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 4);
      return paramParcel.readInt();
    }
    
    public static hv d(Context paramContext)
    {
      hv localHv = new hv();
      localHv.b = ad.a(paramContext);
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      String str = localTelephonyManager.getSimCountryIso();
      if (TextUtils.isEmpty(str)) {}
      for (str = Locale.getDefault().getCountry();; str = str.toUpperCase())
      {
        localHv.c = str;
        localHv.e = localTelephonyManager.getNetworkCountryIso();
        localHv.f = paramContext.getResources().getConfiguration().locale.toString();
        localHv.h = Build.MODEL;
        localHv.g = Build.MANUFACTURER;
        localHv.i = Build.FINGERPRINT;
        localHv.d = Locale.getDefault().getLanguage();
        localHv.j = Build.VERSION.SDK_INT;
        localHv.k = jx.a(paramContext).e();
        return localHv;
      }
    }
    
    public static void d(String paramString)
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new IllegalStateException(paramString);
      }
    }
    
    public static boolean d()
    {
      return b(19);
    }
    
    /* Error */
    public static byte[] d(Context paramContext, String paramString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_0
      //   3: aload_1
      //   4: invokevirtual 1923	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   7: astore_0
      //   8: aload_0
      //   9: astore_1
      //   10: aload_0
      //   11: invokestatic 1925	com/lbe/parallel/f$a:a	(Ljava/io/InputStream;)[B
      //   14: astore_2
      //   15: aload_2
      //   16: astore_1
      //   17: aload_0
      //   18: ifnull +9 -> 27
      //   21: aload_0
      //   22: invokestatic 1577	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   25: aload_2
      //   26: astore_1
      //   27: aload_1
      //   28: areturn
      //   29: astore_2
      //   30: aconst_null
      //   31: astore_0
      //   32: aload_0
      //   33: astore_1
      //   34: aload_2
      //   35: invokevirtual 425	java/lang/Exception:printStackTrace	()V
      //   38: aload_3
      //   39: astore_1
      //   40: aload_0
      //   41: ifnull -14 -> 27
      //   44: aload_0
      //   45: invokestatic 1577	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   48: aconst_null
      //   49: areturn
      //   50: astore_0
      //   51: aconst_null
      //   52: astore_1
      //   53: aload_1
      //   54: ifnull +7 -> 61
      //   57: aload_1
      //   58: invokestatic 1577	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   61: aload_0
      //   62: athrow
      //   63: astore_0
      //   64: goto -11 -> 53
      //   67: astore_2
      //   68: goto -36 -> 32
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	71	0	paramContext	Context
      //   0	71	1	paramString	String
      //   14	12	2	arrayOfByte	byte[]
      //   29	6	2	localException1	Exception
      //   67	1	2	localException2	Exception
      //   1	38	3	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   2	8	29	java/lang/Exception
      //   2	8	50	finally
      //   10	15	63	finally
      //   34	38	63	finally
      //   10	15	67	java/lang/Exception
    }
    
    /* Error */
    public static byte[] d(byte[] paramArrayOfByte)
    {
      // Byte code:
      //   0: new 1592	java/io/ByteArrayOutputStream
      //   3: dup
      //   4: invokespecial 1593	java/io/ByteArrayOutputStream:<init>	()V
      //   7: astore_1
      //   8: new 1928	java/util/zip/DeflaterOutputStream
      //   11: dup
      //   12: aload_1
      //   13: invokespecial 1929	java/util/zip/DeflaterOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   16: astore_2
      //   17: aload_2
      //   18: aload_0
      //   19: invokevirtual 1930	java/util/zip/DeflaterOutputStream:write	([B)V
      //   22: aload_2
      //   23: invokevirtual 1931	java/util/zip/DeflaterOutputStream:close	()V
      //   26: aload_1
      //   27: invokevirtual 1598	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   30: astore_0
      //   31: aload_1
      //   32: invokevirtual 1659	java/io/ByteArrayOutputStream:close	()V
      //   35: aload_2
      //   36: invokevirtual 1931	java/util/zip/DeflaterOutputStream:close	()V
      //   39: aload_1
      //   40: invokevirtual 1659	java/io/ByteArrayOutputStream:close	()V
      //   43: aload_0
      //   44: areturn
      //   45: astore_0
      //   46: aload_2
      //   47: invokevirtual 1931	java/util/zip/DeflaterOutputStream:close	()V
      //   50: aload_1
      //   51: invokevirtual 1659	java/io/ByteArrayOutputStream:close	()V
      //   54: aconst_null
      //   55: areturn
      //   56: astore_0
      //   57: aload_2
      //   58: invokevirtual 1931	java/util/zip/DeflaterOutputStream:close	()V
      //   61: aload_1
      //   62: invokevirtual 1659	java/io/ByteArrayOutputStream:close	()V
      //   65: aload_0
      //   66: athrow
      //   67: astore_2
      //   68: goto -29 -> 39
      //   71: astore_1
      //   72: aload_0
      //   73: areturn
      //   74: astore_0
      //   75: goto -25 -> 50
      //   78: astore_0
      //   79: goto -25 -> 54
      //   82: astore_2
      //   83: goto -22 -> 61
      //   86: astore_1
      //   87: goto -22 -> 65
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	90	0	paramArrayOfByte	byte[]
      //   7	55	1	localByteArrayOutputStream	ByteArrayOutputStream
      //   71	1	1	localIOException1	IOException
      //   86	1	1	localIOException2	IOException
      //   16	42	2	localDeflaterOutputStream	java.util.zip.DeflaterOutputStream
      //   67	1	2	localIOException3	IOException
      //   82	1	2	localIOException4	IOException
      // Exception table:
      //   from	to	target	type
      //   17	35	45	java/io/IOException
      //   17	35	56	finally
      //   35	39	67	java/io/IOException
      //   39	43	71	java/io/IOException
      //   46	50	74	java/io/IOException
      //   50	54	78	java/io/IOException
      //   57	61	82	java/io/IOException
      //   61	65	86	java/io/IOException
    }
    
    public static long e(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 8);
      return paramParcel.readLong();
    }
    
    public static AdClientInfo e(Context paramContext)
    {
      AdClientInfo localAdClientInfo = new AdClientInfo();
      localAdClientInfo.setGpCountry("");
      localAdClientInfo.setPsGpCountry("");
      localAdClientInfo.setUserGroupId(jv.b(paramContext));
      localAdClientInfo.setChannel("B1");
      localAdClientInfo.setChannelNetwork(z.a().c("ad_channel_net_work"));
      localAdClientInfo.setChannelCampaign(z.a().c("ad_channel_campaign"));
      localAdClientInfo.setChannelAdGroup(z.a().c("ad_channel_ad_group"));
      localAdClientInfo.setPkgName(paramContext.getPackageName());
      localAdClientInfo.setVersionCode(185);
      localAdClientInfo.setVersionName("3.1.6210");
      try
      {
        localAdClientInfo.setFileMD5(ad.a(paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)));
        localAdClientInfo.setSignatureMD5(ad.g(paramContext));
        localAdClientInfo.setInstallerPackageName(paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName()));
        boolean[] arrayOfBoolean = ad.a(paramContext, new String[] { "com.google", "com.facebook.auth.login" });
        localAdClientInfo.setLoginGP(arrayOfBoolean[0]);
        localAdClientInfo.setLoginFB(arrayOfBoolean[1]);
        paramContext = ad.a(paramContext, DAApp.o().q(), new String[] { "com.google", "com.facebook.auth.login" });
        localAdClientInfo.setLoginGPInPS(paramContext[0]);
        localAdClientInfo.setLoginFBInPS(paramContext[1]);
        localAdClientInfo.setDebug(false);
        return localAdClientInfo;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    }
    
    public static void e(String paramString)
    {
      try
      {
        if (TextUtils.isEmpty(paramString)) {
          throw new NullPointerException("packageName can not be null");
        }
      }
      finally {}
      Object localObject = z.a().c("home_apps_ordered_list");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = new StringBuffer((String)localObject);
        if (((StringBuffer)localObject).length() <= 0) {
          break label106;
        }
        ((StringBuffer)localObject).append(";").append(paramString);
      }
      for (;;)
      {
        z.a().a("home_apps_ordered_list", ((StringBuffer)localObject).toString());
        ((StringBuffer)localObject).toString();
        return;
        localObject = new StringBuffer();
        break;
        label106:
        ((StringBuffer)localObject).append(paramString);
      }
    }
    
    public static boolean e()
    {
      return b(21);
    }
    
    public static boolean e(Context paramContext, String paramString)
    {
      paramContext = new File(paramContext.getFilesDir(), paramString);
      if (paramContext.exists()) {
        return paramContext.delete();
      }
      return true;
    }
    
    public static long f(Context paramContext, String paramString)
    {
      return new File(paramContext.getFilesDir(), paramString).lastModified();
    }
    
    public static RequestQueue f()
    {
      if (g != null) {
        return g;
      }
      throw new IllegalStateException("RequestQueue not initialized");
    }
    
    public static AdDeviceInfo f(Context paramContext)
    {
      AdDeviceInfo localAdDeviceInfo = new AdDeviceInfo();
      localAdDeviceInfo.setAndroidAdId(ad.h(paramContext));
      localAdDeviceInfo.setAndroidId(ad.a(paramContext));
      localAdDeviceInfo.setClientIP(ad.e());
      Object localObject = paramContext.getResources().getConfiguration().locale;
      if (localObject == null) {
        localObject = Locale.getDefault();
      }
      for (;;)
      {
        localAdDeviceInfo.setConfigLanguage(((Locale)localObject).toString());
        TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
        String str = localTelephonyManager.getSimCountryIso();
        if (TextUtils.isEmpty(str))
        {
          localObject = ((Locale)localObject).getCountry();
          localAdDeviceInfo.setNetCarrier(localTelephonyManager.getNetworkOperatorName());
          localAdDeviceInfo.setNetworkCountry(localTelephonyManager.getNetworkCountryIso());
          localAdDeviceInfo.setNetworkType(localTelephonyManager.getNetworkType());
          localAdDeviceInfo.setDeviceCountry((String)localObject);
          localAdDeviceInfo.setFingerprint(Build.FINGERPRINT);
          localAdDeviceInfo.setImei(ad.b(paramContext));
          localObject = ad.i(paramContext);
          if (localObject != null)
          {
            localAdDeviceInfo.setLatitude(((Double)((s)localObject).a).doubleValue());
            localAdDeviceInfo.setLongitude(((Double)((s)localObject).b).doubleValue());
          }
          localAdDeviceInfo.setUserAgent(ad.j(paramContext));
          localAdDeviceInfo.setLocalLanguage(Locale.getDefault().getLanguage());
          localAdDeviceInfo.setMac(ad.b("wlan0"));
          localAdDeviceInfo.setModel(Build.MODEL);
          localAdDeviceInfo.setOsVersion(Build.VERSION.RELEASE);
          localAdDeviceInfo.setProduct(Build.PRODUCT);
          localObject = paramContext.getResources().getDisplayMetrics();
          localAdDeviceInfo.setResolutionHeight(((DisplayMetrics)localObject).heightPixels);
          localAdDeviceInfo.setResolutionWidth(((DisplayMetrics)localObject).widthPixels);
          localAdDeviceInfo.setSdkInt(Build.VERSION.SDK_INT);
          localAdDeviceInfo.setTimezoneId(Calendar.getInstance().getTimeZone().getID());
          localAdDeviceInfo.setTimezoneOffset(Calendar.getInstance().getTimeZone().getRawOffset());
          localAdDeviceInfo.setUserAgent(ad.j(paramContext));
          localAdDeviceInfo.setVendor(Build.MANUFACTURER);
          if (!a.e(paramContext)) {
            break label345;
          }
        }
        label345:
        for (int k = 1;; k = 0)
        {
          localAdDeviceInfo.setIsRTL(k);
          localAdDeviceInfo.setBtMac(ad.m(paramContext));
          paramContext = d.a(paramContext);
          if (paramContext != null) {
            localAdDeviceInfo.setFbAid(paramContext.a());
          }
          return localAdDeviceInfo;
          localObject = str.toUpperCase();
          break;
        }
      }
    }
    
    public static BigInteger f(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(paramInt + k);
      return new BigInteger(arrayOfByte);
    }
    
    /* Error */
    public static byte[] f(String paramString)
    {
      // Byte code:
      //   0: invokestatic 2210	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
      //   3: ldc_w 2212
      //   6: invokevirtual 1025	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   9: ifeq +90 -> 99
      //   12: new 1296	java/io/File
      //   15: dup
      //   16: invokestatic 2215	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
      //   19: aload_0
      //   20: invokespecial 2031	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   23: astore_0
      //   24: aload_0
      //   25: invokevirtual 1299	java/io/File:exists	()Z
      //   28: ifne +5 -> 33
      //   31: aconst_null
      //   32: areturn
      //   33: new 1306	java/io/FileInputStream
      //   36: dup
      //   37: aload_0
      //   38: invokespecial 1309	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   41: astore_1
      //   42: aload_1
      //   43: astore_0
      //   44: aload_1
      //   45: invokestatic 1925	com/lbe/parallel/f$a:a	(Ljava/io/InputStream;)[B
      //   48: astore_2
      //   49: aload_1
      //   50: invokestatic 1577	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   53: aload_2
      //   54: areturn
      //   55: astore_2
      //   56: aconst_null
      //   57: astore_1
      //   58: aload_1
      //   59: astore_0
      //   60: aload_2
      //   61: invokevirtual 425	java/lang/Exception:printStackTrace	()V
      //   64: aload_1
      //   65: ifnull +34 -> 99
      //   68: aload_1
      //   69: invokestatic 1577	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   72: aconst_null
      //   73: areturn
      //   74: astore_0
      //   75: aconst_null
      //   76: astore_2
      //   77: aload_0
      //   78: astore_1
      //   79: aload_2
      //   80: ifnull +7 -> 87
      //   83: aload_2
      //   84: invokestatic 1577	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   87: aload_1
      //   88: athrow
      //   89: astore_1
      //   90: aload_0
      //   91: astore_2
      //   92: goto -13 -> 79
      //   95: astore_2
      //   96: goto -38 -> 58
      //   99: aconst_null
      //   100: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	101	0	paramString	String
      //   41	47	1	localObject1	Object
      //   89	1	1	localObject2	Object
      //   48	6	2	arrayOfByte	byte[]
      //   55	6	2	localException1	Exception
      //   76	16	2	localObject3	Object
      //   95	1	2	localException2	Exception
      // Exception table:
      //   from	to	target	type
      //   12	31	55	java/lang/Exception
      //   33	42	55	java/lang/Exception
      //   12	31	74	finally
      //   33	42	74	finally
      //   44	49	89	finally
      //   60	64	89	finally
      //   44	49	95	java/lang/Exception
    }
    
    public static float g(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 4);
      return paramParcel.readFloat();
    }
    
    public static ImageLoader g()
    {
      if (h != null) {
        return h;
      }
      throw new IllegalStateException("ImageLoader not initialized");
    }
    
    public static String g(Context paramContext)
    {
      HashMap localHashMap = new HashMap();
      HashSet localHashSet = new HashSet();
      Object localObject = new PackageManagerWrapper(paramContext).getInstalledPackages(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!a(localPackageInfo.applicationInfo)) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
      localObject = com.lbe.multidroid.service.b.a(paramContext).c().e(DAApp.o().q());
      if (localObject != null)
      {
        localObject = ((Set)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          localHashSet.add((String)((Iterator)localObject).next());
        }
      }
      localObject = f(paramContext);
      localHashMap.put("clientInfo", e(paramContext));
      localHashMap.put("deviceInfo", localObject);
      localHashMap.put("existPkgs", localHashSet);
      return JSON.toJSONString(localHashMap);
    }
    
    private static String g(String paramString)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      int k = 0;
      if (k < paramString.length())
      {
        char c1 = paramString.charAt(k);
        if (k == 0) {
          localStringBuffer.append(Character.toLowerCase(c1));
        }
        for (;;)
        {
          k += 1;
          break;
          if (Character.isUpperCase(c1)) {
            localStringBuffer.append('_').append(Character.toLowerCase(c1));
          } else {
            localStringBuffer.append(c1);
          }
        }
      }
      return localStringBuffer.toString();
    }
    
    public static double h(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 8);
      return paramParcel.readDouble();
    }
    
    public static ha h()
    {
      if (i != null) {
        return i;
      }
      throw new IllegalStateException("ImageLoader not initialized");
    }
    
    public static PsDevInfo h(Context paramContext)
    {
      PsDevInfo localPsDevInfo = new PsDevInfo();
      localPsDevInfo.setVersionName("3.1.6210");
      k localK = com.lbe.multidroid.service.b.a(paramContext).c().a(DAApp.o().q());
      if (localK != null)
      {
        localPsDevInfo.setAndroidId(localK.b());
        localPsDevInfo.setAndroidAdId(localK.c());
        localPsDevInfo.setImei(localK.a());
        localPsDevInfo.setMac(localK.d());
        localPsDevInfo.setBtMac(localK.e());
        localPsDevInfo.setFbAid(d.a(paramContext).b());
      }
      return localPsDevInfo;
    }
    
    private static String h(String paramString)
    {
      int m = paramString.length();
      StringBuilder localStringBuilder = new StringBuilder(m);
      int k = 0;
      if (k < m)
      {
        char c1 = paramString.charAt(k);
        if ((c1 >= ' ') && (c1 <= '~') && (c1 != '"') && (c1 != '\'')) {
          localStringBuilder.append(c1);
        }
        for (;;)
        {
          k += 1;
          break;
          localStringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c1) }));
        }
      }
      return localStringBuilder.toString();
    }
    
    private static String i(String paramString)
    {
      if (j == null)
      {
        Object localObject2 = DAApp.o().getResources();
        localObject1 = Arrays.asList(((Resources)localObject2).getStringArray(2131165198));
        localObject2 = Arrays.asList(((Resources)localObject2).getStringArray(2131165197));
        int m = ((List)localObject1).size();
        j = new ao(m);
        int k = 0;
        while (k < m)
        {
          j.put(((List)localObject1).get(k), ((List)localObject2).get(k));
          k += 1;
        }
      }
      Object localObject1 = (String)j.get(paramString);
      paramString = (String)localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        paramString = "2,000,000";
      }
      return paramString;
    }
    
    public static BigDecimal i(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      int m = paramParcel.readInt();
      paramParcel.setDataPosition(paramInt + k);
      return new BigDecimal(new BigInteger(arrayOfByte), m);
    }
    
    public static void i()
    {
      if (i != null) {
        i.a();
      }
    }
    
    public static boolean i(Context paramContext)
    {
      try
      {
        boolean bool = af.a(paramContext).b();
        return bool;
      }
      catch (SecurityException paramContext)
      {
        paramContext.printStackTrace();
      }
      return false;
    }
    
    public static String j(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      String str = paramParcel.readString();
      paramParcel.setDataPosition(paramInt + k);
      return str;
    }
    
    public static List<PackageInfo> j(Context paramContext)
    {
      PackageManagerWrapper localPackageManagerWrapper = new PackageManagerWrapper(paramContext);
      paramContext = localPackageManagerWrapper.getInstalledPackages(0);
      ArrayList localArrayList = new ArrayList(paramContext.size());
      System.currentTimeMillis();
      Iterator localIterator = paramContext.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo != null) && (localPackageInfo.applicationInfo != null) && (localPackageInfo.packageName != null))
        {
          int k;
          label100:
          Object localObject;
          if (!a(localPackageInfo.applicationInfo))
          {
            k = 1;
            if ((k == 0) || (i.b.contains(localPackageInfo.packageName))) {
              break label261;
            }
            localObject = u.a().b(localPackageInfo.packageName);
            paramContext = (Context)localObject;
            if (localObject == null)
            {
              if (localPackageManagerWrapper.getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
                break label263;
              }
              paramContext = Boolean.TRUE;
              label155:
              u.a().a(localPackageInfo.packageName, paramContext);
            }
            if (!paramContext.booleanValue()) {
              continue;
            }
            localObject = com.lbe.parallel.utility.af.a().a(localPackageInfo.packageName);
            paramContext = (Context)localObject;
            if (localObject != null) {}
          }
          for (;;)
          {
            try
            {
              boolean bool = e.a(localPackageManagerWrapper.getApplicationInfo(localPackageInfo.packageName, 128));
              paramContext = Boolean.valueOf(bool);
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException2)
            {
              label261:
              label263:
              paramContext = (Context)localObject;
              localObject = localNameNotFoundException2;
            }
            try
            {
              com.lbe.parallel.utility.af.a().a(localPackageInfo.packageName, paramContext);
              localObject = paramContext;
              paramContext = (Context)localObject;
              if (localObject == null) {
                paramContext = Boolean.FALSE;
              }
              if (paramContext.booleanValue()) {
                break;
              }
              localArrayList.add(localPackageInfo);
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException1)
            {
              for (;;) {}
            }
            k = 0;
            break label100;
            break;
            paramContext = Boolean.FALSE;
            break label155;
            ((PackageManager.NameNotFoundException)localObject).printStackTrace();
            localObject = paramContext;
          }
        }
      }
      return localArrayList;
    }
    
    public static Map<String, Integer> j()
    {
      int m = 0;
      try
      {
        Object localObject1 = z.a().c("home_apps_ordered_list");
        if (TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject1 = null;
          if (localObject1 == null) {
            break label98;
          }
        }
        ao localAo;
        label98:
        for (int k = localObject1.length;; k = 0)
        {
          localAo = new ao(k);
          if ((localObject1 == null) || (localObject1.length <= 0)) {
            break label103;
          }
          int n = localObject1.length;
          k = m;
          while (k < n)
          {
            localAo.put(localObject1[k], Integer.valueOf(k));
            k += 1;
          }
          localObject1 = ((String)localObject1).split(";");
          a.a((String[])localObject1);
          break;
        }
        label103:
        return localAo;
      }
      finally {}
    }
    
    public static IBinder k(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      IBinder localIBinder = paramParcel.readStrongBinder();
      paramParcel.setDataPosition(paramInt + k);
      return localIBinder;
    }
    
    private static id k(Context paramContext)
    {
      id localId = new id();
      localId.b = c(paramContext);
      localId.c = d(paramContext);
      Object localObject = new PackageManagerWrapper(paramContext).getInstalledPackages(0);
      paramContext = new ArrayList(((List)localObject).size());
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!a(localPackageInfo.applicationInfo)) {
          paramContext.add(a(localPackageInfo));
        }
      }
      localId.d = ((ht[])paramContext.toArray(new ht[paramContext.size()]));
      return localId;
    }
    
    public static long[] k()
    {
      long l1 = 0L;
      long[] arrayOfLong = new long[3];
      long[] tmp9_7 = arrayOfLong;
      tmp9_7[0] = 0L;
      long[] tmp13_9 = tmp9_7;
      tmp13_9[1] = 0L;
      long[] tmp17_13 = tmp13_9;
      tmp17_13[2] = 0L;
      tmp17_13;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        Object localObject = Environment.getExternalStorageDirectory();
        try
        {
          localObject = new StatFs(((File)localObject).getPath());
          long l2 = ((StatFs)localObject).getBlockSize();
          long l3 = ((StatFs)localObject).getBlockCount();
          long l4 = ((StatFs)localObject).getAvailableBlocks();
          tmp13_9[0] = (l3 * l2);
          tmp13_9[1] = (l2 * (l3 - l4));
          if (l3 != 0L)
          {
            l1 = l4 * 100L / l3;
            l1 = 100L - l1;
          }
        }
        catch (Exception localException)
        {
          return tmp13_9;
        }
        tmp13_9[2] = l1;
      }
      return tmp13_9;
    }
    
    public static Bundle l(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      Bundle localBundle = paramParcel.readBundle();
      paramParcel.setDataPosition(paramInt + k);
      return localBundle;
    }
    
    private static ie l(Context paramContext)
    {
      Object localObject = null;
      byte[] arrayOfByte = d(paramContext, "hot_apps_response.info");
      paramContext = localObject;
      if (!c(arrayOfByte)) {}
      try
      {
        paramContext = ie.a(arrayOfByte);
        return paramContext;
      }
      catch (fi paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static boolean l()
    {
      try
      {
        AssetManager.class.getDeclaredMethod("openNonAssetNative", new Class[] { Integer.TYPE, String.class, Integer.TYPE });
        AssetManager.class.getDeclaredMethod("openNonAssetFdNative", new Class[] { Integer.TYPE, String.class, [J.class });
        AssetManager.class.getDeclaredMethod("openXmlAssetNative", new Class[] { Integer.TYPE, String.class });
        AssetManager.class.getDeclaredMethod("loadResourceValue", new Class[] { Integer.TYPE, Short.TYPE, TypedValue.class, Boolean.TYPE });
        AssetManager.class.getDeclaredMethod("loadResourceBagValue", new Class[] { Integer.TYPE, Integer.TYPE, TypedValue.class, Boolean.TYPE });
        if (Build.VERSION.SDK_INT >= 21)
        {
          AssetManager.class.getDeclaredMethod("applyStyle", new Class[] { Long.TYPE, Integer.TYPE, Integer.TYPE, Long.TYPE, [I.class, [I.class, [I.class });
          AssetManager.class.getDeclaredMethod("retrieveAttributes", new Class[] { Long.TYPE, [I.class, [I.class, [I.class });
          AssetManager.class.getDeclaredMethod("resolveAttrs", new Class[] { Long.TYPE, Integer.TYPE, Integer.TYPE, [I.class, [I.class, [I.class, [I.class });
          AssetManager.class.getDeclaredMethod("loadThemeAttributeValue", new Class[] { Long.TYPE, Integer.TYPE, TypedValue.class, Boolean.TYPE });
        }
        for (;;)
        {
          AssetManager.class.getDeclaredMethod("retrieveArray", new Class[] { Integer.TYPE, [I.class });
          return true;
          AssetManager.class.getDeclaredMethod("applyStyle", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, [I.class, [I.class, [I.class });
          AssetManager.class.getDeclaredMethod("retrieveAttributes", new Class[] { Integer.TYPE, [I.class, [I.class, [I.class });
          AssetManager.class.getDeclaredMethod("loadThemeAttributeValue", new Class[] { Integer.TYPE, Integer.TYPE, TypedValue.class, Boolean.TYPE });
        }
        return false;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
    
    private static id m(Context paramContext)
    {
      paramContext = d(paramContext, "hot_apps_request.info");
      if (!c(paramContext)) {
        try
        {
          paramContext = (id)fj.a(new id(), paramContext);
          return paramContext;
        }
        catch (fi paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      return null;
    }
    
    public static byte[] m(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(paramInt + k);
      return arrayOfByte;
    }
    
    public static Method[] m()
    {
      ArrayList localArrayList = new ArrayList();
      Method[] arrayOfMethod = AssetManager.class.getDeclaredMethods();
      int m = arrayOfMethod.length;
      int k = 0;
      while (k < m)
      {
        Method localMethod = arrayOfMethod[k];
        if (Modifier.isNative(localMethod.getModifiers())) {
          localArrayList.add(localMethod);
        }
        k += 1;
      }
      return (Method[])localArrayList.toArray(new Method[localArrayList.size()]);
    }
    
    public static BigDecimal[] n(Parcel paramParcel, int paramInt)
    {
      int k = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (k == 0) {
        return null;
      }
      int n = paramParcel.readInt();
      BigDecimal[] arrayOfBigDecimal = new BigDecimal[n];
      paramInt = 0;
      while (paramInt < n)
      {
        byte[] arrayOfByte = paramParcel.createByteArray();
        int i1 = paramParcel.readInt();
        arrayOfBigDecimal[paramInt] = new BigDecimal(new BigInteger(arrayOfByte), i1);
        paramInt += 1;
      }
      paramParcel.setDataPosition(m + k);
      return arrayOfBigDecimal;
    }
    
    public static String[] o(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      String[] arrayOfString = paramParcel.createStringArray();
      paramParcel.setDataPosition(paramInt + k);
      return arrayOfString;
    }
    
    public static Parcel p(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int k = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      Parcel localParcel = Parcel.obtain();
      localParcel.appendFrom(paramParcel, k, paramInt);
      paramParcel.setDataPosition(paramInt + k);
      return localParcel;
    }
    
    public static Parcel[] q(Parcel paramParcel, int paramInt)
    {
      int k = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (k == 0) {
        return null;
      }
      int n = paramParcel.readInt();
      Parcel[] arrayOfParcel = new Parcel[n];
      paramInt = 0;
      if (paramInt < n)
      {
        int i1 = paramParcel.readInt();
        if (i1 != 0)
        {
          int i2 = paramParcel.dataPosition();
          Parcel localParcel = Parcel.obtain();
          localParcel.appendFrom(paramParcel, i2, i1);
          arrayOfParcel[paramInt] = localParcel;
          paramParcel.setDataPosition(i1 + i2);
        }
        for (;;)
        {
          paramInt += 1;
          break;
          arrayOfParcel[paramInt] = null;
        }
      }
      paramParcel.setDataPosition(m + k);
      return arrayOfParcel;
    }
    
    public static void r(Parcel paramParcel, int paramInt)
    {
      t(paramParcel, paramInt);
    }
    
    private static int s(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(0xFFFF0000 | paramInt);
      paramParcel.writeInt(0);
      return paramParcel.dataPosition();
    }
    
    private static void t(Parcel paramParcel, int paramInt)
    {
      int k = paramParcel.dataPosition();
      paramParcel.setDataPosition(paramInt - 4);
      paramParcel.writeInt(k - paramInt);
      paramParcel.setDataPosition(k);
    }
  }
}
