package com.lbe.parallel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.PowerManager;
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
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.TypedValue;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.appsflyer.h;
import com.google.android.gms.internal.ck;
import com.google.android.gms.internal.cl;
import com.lbe.mdremote.common.DAUser;
import com.lbe.parallel.ads.s;
import com.lbe.parallel.model.AdClientInfo;
import com.lbe.parallel.model.AdDeviceInfo;
import com.lbe.parallel.model.AdRecord;
import com.lbe.parallel.model.AdRecord.Builder;
import com.lbe.parallel.model.ClientInfo;
import com.lbe.parallel.model.DeviceInfo;
import com.lbe.parallel.model.InstalledPsThemes;
import com.lbe.parallel.model.PackageData;
import com.lbe.parallel.model.PsDevInfo;
import com.lbe.parallel.model.RecommendAdRequest;
import com.lbe.parallel.skin.SkinPackage;
import com.lbe.parallel.utility.SPConstant;
import com.lbe.parallel.utility.ab;
import com.lbe.parallel.utility.ae;
import com.lbe.parallel.utility.ae.a;
import com.lbe.parallel.utility.ah;
import com.lbe.parallel.utility.c.1;
import com.lbe.parallel.utility.i;
import com.lbe.parallel.utility.m;
import com.lbe.parallel.utility.u;
import com.lbe.parallel.utility.w;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.HttpHost;
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
    private static Boolean g;
    private static Boolean h;
    private static RequestQueue i;
    private static ImageLoader j;
    private static hl k;
    private static Map<String, String> l;
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
    
    public static IBinder A(Parcel paramParcel, int paramInt)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      IBinder localIBinder = paramParcel.readStrongBinder();
      paramParcel.setDataPosition(paramInt + m);
      return localIBinder;
    }
    
    public static String[] B(Parcel paramParcel, int paramInt)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      String[] arrayOfString = paramParcel.createStringArray();
      paramParcel.setDataPosition(paramInt + m);
      return arrayOfString;
    }
    
    public static byte[] C(Parcel paramParcel, int paramInt)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(paramInt + m);
      return arrayOfByte;
    }
    
    public static void D(Parcel paramParcel, int paramInt)
    {
      paramParcel.setDataPosition(H(paramParcel, paramInt) + paramParcel.dataPosition());
    }
    
    public static void E(Parcel paramParcel, int paramInt)
    {
      int m = paramParcel.dataPosition();
      paramParcel.setDataPosition(paramInt - 4);
      paramParcel.writeInt(m - paramInt);
      paramParcel.setDataPosition(m);
    }
    
    private static int F(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(0xFFFF0000 | paramInt);
      paramParcel.writeInt(0);
      return paramParcel.dataPosition();
    }
    
    private static void G(Parcel paramParcel, int paramInt)
    {
      int m = paramParcel.dataPosition();
      paramParcel.setDataPosition(paramInt - 4);
      paramParcel.writeInt(m - paramInt);
      paramParcel.setDataPosition(m);
    }
    
    private static int H(Parcel paramParcel, int paramInt)
    {
      if ((paramInt & 0xFFFF0000) != -65536) {
        return paramInt >> 16 & 0xFFFF;
      }
      return paramParcel.readInt();
    }
    
    private static int I(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(0xFFFF0000 | paramInt);
      paramParcel.writeInt(0);
      return paramParcel.dataPosition();
    }
    
    public static double a(double paramDouble1, double paramDouble2)
    {
      return Math.min(Math.max(paramDouble1, paramDouble2), 1.0D);
    }
    
    public static double a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5)
    {
      return (paramDouble1 - paramDouble2) / (paramDouble3 - paramDouble2) * (paramDouble5 - paramDouble4) + paramDouble4;
    }
    
    public static int a(Context paramContext, int paramInt, SkinPackage paramSkinPackage)
    {
      Object localObject = paramContext.getResources();
      paramContext = ((Resources)localObject).getResourceTypeName(paramInt);
      localObject = ((Resources)localObject).getResourceEntryName(paramInt);
      String str = paramSkinPackage.b;
      int m = paramSkinPackage.c().getIdentifier((String)localObject, paramContext, str);
      if (m == 0) {
        return paramInt;
      }
      return m;
    }
    
    public static int a(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      Context localContext = null;
      try
      {
        paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { paramInt });
        localContext = paramContext;
        paramInt = paramContext.getResourceId(0, -1);
        return paramInt;
      }
      finally
      {
        localContext.recycle();
      }
    }
    
    public static int a(Context paramContext, String paramString)
    {
      int m = Process.myPid();
      int n = Process.myUid();
      String str1 = paramContext.getPackageName();
      if (paramContext.checkPermission(paramString, m, n) == -1) {
        return -1;
      }
      String str2 = android.support.v4.app.c.a(paramString);
      if (str2 != null)
      {
        paramString = str1;
        if (str1 == null)
        {
          paramString = paramContext.getPackageManager().getPackagesForUid(n);
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
      int n = paramParcel.readInt();
      int i1 = a(paramParcel, n);
      int m = paramParcel.dataPosition();
      if ((0xFFFF & n) != 20293)
      {
        String str = String.valueOf(Integer.toHexString(n));
        if (str.length() != 0) {}
        for (str = "Expected object header. Got 0x".concat(str);; str = new String("Expected object header. Got 0x")) {
          throw new Fragment.a(str, paramParcel);
        }
      }
      n = m + i1;
      if ((n < m) || (n > paramParcel.dataSize())) {
        throw new Fragment.a(54 + "Size read is invalid start=" + m + " end=" + n, paramParcel);
      }
      return n;
    }
    
    public static int a(Parcel paramParcel, int paramInt)
    {
      if ((paramInt & 0xFFFF0000) != -65536) {
        return paramInt >> 16 & 0xFFFF;
      }
      return paramParcel.readInt();
    }
    
    public static int a(AttributeSet paramAttributeSet, String paramString)
    {
      return paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res/android", paramString, -1);
    }
    
    private static long a(InputStream paramInputStream, OutputStream paramOutputStream, byte[] paramArrayOfByte)
      throws IOException
    {
      int m;
      for (long l1 = 0L;; l1 += m)
      {
        m = paramInputStream.read(paramArrayOfByte);
        if (-1 == m) {
          break;
        }
        paramOutputStream.write(paramArrayOfByte, 0, m);
      }
      return l1;
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
    
    public static Drawable a(Context paramContext, int paramInt, Resources.Theme paramTheme)
    {
      int m = d(paramContext, paramInt);
      if ((m == paramInt) && (Build.VERSION.SDK_INT >= 21)) {
        return z().getDrawable(paramInt, paramTheme);
      }
      return z().getDrawable(m);
    }
    
    public static <T extends Parcelable> T a(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = (Parcelable)paramCreator.createFromParcel(paramParcel);
      paramParcel.setDataPosition(paramInt + m);
      return paramCreator;
    }
    
    private static if a(PackageInfo paramPackageInfo)
    {
      if localIf = new if();
      localIf.b = paramPackageInfo.packageName;
      localIf.f = "";
      localIf.e = "";
      localIf.c = paramPackageInfo.versionCode;
      String str = paramPackageInfo.versionName;
      if (str != null)
      {
        localIf.d = str;
        if ((paramPackageInfo.applicationInfo.flags & 0x1) == 0) {
          break label80;
        }
      }
      label80:
      for (boolean bool = true;; bool = false)
      {
        localIf.g = bool;
        return localIf;
        str = "";
        break;
      }
    }
    
    public static ik a(Context paramContext, ij paramIj)
    {
      try
      {
        String str = hw.a(paramContext, "events_control");
        paramContext = str;
        if (TextUtils.isEmpty(str)) {
          paramContext = "http://psi.lbesecapi.com:83/parallel/events_control";
        }
        paramContext = a(paramContext, ij.a(paramIj));
        if (paramContext != null)
        {
          paramContext = (ik)fb.a(new ik(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static il a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte)
    {
      il localIl = new il();
      localIl.b = o();
      localIl.c = p();
      localIl.d = paramString3;
      localIl.e = paramString4;
      localIl.f = paramString1;
      if (!TextUtils.isEmpty(paramString2))
      {
        paramString1 = c.1.a(paramContext, paramString2);
        if (paramString1 != null)
        {
          localIl.h = a(paramString1);
          paramString1 = c.1.b(paramString1);
          if (paramString1 == null) {
            break label174;
          }
        }
        label174:
        for (paramString1 = paramString1.toString();; paramString1 = null)
        {
          localIl.g = paramString1;
          paramString1 = new fq(paramContext);
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
        localIl.i = ((if[])paramString2.toArray(new if[paramString2.size()]));
        paramString2 = fn.a(paramContext).e(DAApp.a().c());
        if ((paramString2 != null) && (paramString2.length > 0))
        {
          paramString3 = new ArrayList(paramString2.length);
          int n = paramString2.length;
          int m = 0;
          for (;;)
          {
            if (m < n)
            {
              paramContext = paramString2[m];
              try
              {
                paramContext = paramString1.getPackageInfo(paramContext, 0);
                if (paramContext != null) {
                  paramString3.add(a(paramContext));
                }
                m += 1;
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
          localIl.k = ((if[])paramString3.toArray(new if[paramString3.size()]));
        }
        if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {
          localIl.j = paramArrayOfByte;
        }
      }
      return localIl;
    }
    
    public static im a(Context paramContext, il paramIl)
    {
      try
      {
        paramContext = a(hw.a(paramContext, "feedback"), il.a(paramIl));
        if (paramContext != null)
        {
          paramContext = (im)fb.a(new im(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static it a(Context paramContext, is paramIs)
    {
      try
      {
        paramContext = a(hw.a(paramContext, "messages"), is.a(paramIs));
        if (paramContext != null)
        {
          paramContext = (it)fb.a(new it(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static iv a(Context paramContext, iu paramIu)
    {
      try
      {
        paramContext = a(hw.a(paramContext, "raiders"), in.a(paramIu));
        if (paramContext != null)
        {
          paramContext = (iv)fb.a(new iv(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static iy a(Context paramContext, ix paramIx)
    {
      try
      {
        paramContext = a(hw.a(paramContext, "specialThanks"), in.a(paramIx));
        if (paramContext != null)
        {
          paramContext = (iy)fb.a(new iy(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static ja a(Context paramContext, iz paramIz)
    {
      try
      {
        paramContext = a(hw.a(paramContext, "update"), iz.a(paramIz));
        if (paramContext != null)
        {
          paramContext = (ja)fb.a(new ja(), paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static jd a(Context paramContext, jc paramJc)
    {
      try
      {
        paramContext = a(hw.a(paramContext, "web_navi"), jc.a(paramJc));
        if (paramContext != null)
        {
          paramContext = jd.a(paramContext);
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return null;
    }
    
    public static RecommendAdRequest a(Context paramContext, int paramInt)
    {
      RecommendAdRequest localRecommendAdRequest = new RecommendAdRequest();
      localRecommendAdRequest.setClientInfo(c(paramContext));
      localRecommendAdRequest.setDeviceInfo(q());
      localRecommendAdRequest.setPsDevInfo(e(paramContext));
      localRecommendAdRequest.setPageId(paramInt);
      return localRecommendAdRequest;
    }
    
    /* Error */
    public static com.lbe.parallel.model.UpdateInfo.DownloadInfo a(Context paramContext, long paramLong)
    {
      // Byte code:
      //   0: aload_0
      //   1: ldc_w 616
      //   4: invokevirtual 620	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
      //   7: checkcast 622	android/app/DownloadManager
      //   10: astore_0
      //   11: new 624	android/app/DownloadManager$Query
      //   14: dup
      //   15: invokespecial 625	android/app/DownloadManager$Query:<init>	()V
      //   18: astore 4
      //   20: aload 4
      //   22: iconst_1
      //   23: newarray long
      //   25: dup
      //   26: iconst_0
      //   27: lload_1
      //   28: lastore
      //   29: invokevirtual 629	android/app/DownloadManager$Query:setFilterById	([J)Landroid/app/DownloadManager$Query;
      //   32: pop
      //   33: aload_0
      //   34: aload 4
      //   36: invokevirtual 633	android/app/DownloadManager:query	(Landroid/app/DownloadManager$Query;)Landroid/database/Cursor;
      //   39: astore 6
      //   41: aload 6
      //   43: ifnull +215 -> 258
      //   46: aload 6
      //   48: invokeinterface 638 1 0
      //   53: ifle +205 -> 258
      //   56: aload 6
      //   58: invokeinterface 641 1 0
      //   63: ifeq +190 -> 253
      //   66: aload 6
      //   68: aload 6
      //   70: ldc_w 643
      //   73: invokeinterface 647 2 0
      //   78: invokeinterface 651 2 0
      //   83: lstore_1
      //   84: aload 6
      //   86: aload 6
      //   88: ldc_w 653
      //   91: invokeinterface 647 2 0
      //   96: invokeinterface 656 2 0
      //   101: astore 5
      //   103: aload 6
      //   105: aload 6
      //   107: ldc_w 658
      //   110: invokeinterface 647 2 0
      //   115: invokeinterface 662 2 0
      //   120: istore_3
      //   121: new 664	com/lbe/parallel/model/UpdateInfo$DownloadInfo
      //   124: dup
      //   125: invokespecial 665	com/lbe/parallel/model/UpdateInfo$DownloadInfo:<init>	()V
      //   128: astore_0
      //   129: aload 5
      //   131: astore 4
      //   133: aload 5
      //   135: ifnull +31 -> 166
      //   138: aload 5
      //   140: astore 4
      //   142: aload 5
      //   144: ldc_w 667
      //   147: invokevirtual 671	java/lang/String:startsWith	(Ljava/lang/String;)Z
      //   150: ifeq +16 -> 166
      //   153: aload 5
      //   155: ldc_w 667
      //   158: ldc_w 359
      //   161: invokevirtual 675	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   164: astore 4
      //   166: aload_0
      //   167: aload 4
      //   169: invokevirtual 678	com/lbe/parallel/model/UpdateInfo$DownloadInfo:setFilePath	(Ljava/lang/String;)V
      //   172: aload_0
      //   173: lload_1
      //   174: invokevirtual 682	com/lbe/parallel/model/UpdateInfo$DownloadInfo:setFileSize	(J)V
      //   177: aload_0
      //   178: iload_3
      //   179: invokevirtual 685	com/lbe/parallel/model/UpdateInfo$DownloadInfo:setStatus	(I)V
      //   182: aload_0
      //   183: astore 4
      //   185: aload 6
      //   187: ifnull +13 -> 200
      //   190: aload 6
      //   192: invokeinterface 688 1 0
      //   197: aload_0
      //   198: astore 4
      //   200: aload 4
      //   202: areturn
      //   203: astore 4
      //   205: aconst_null
      //   206: astore_0
      //   207: aload 4
      //   209: invokevirtual 422	java/lang/Exception:printStackTrace	()V
      //   212: aload_0
      //   213: astore 4
      //   215: aload 6
      //   217: ifnull -17 -> 200
      //   220: aload 6
      //   222: invokeinterface 688 1 0
      //   227: aload_0
      //   228: astore 4
      //   230: goto -30 -> 200
      //   233: astore_0
      //   234: aload 6
      //   236: ifnull +10 -> 246
      //   239: aload 6
      //   241: invokeinterface 688 1 0
      //   246: aload_0
      //   247: athrow
      //   248: astore 4
      //   250: goto -43 -> 207
      //   253: aconst_null
      //   254: astore_0
      //   255: goto -73 -> 182
      //   258: aconst_null
      //   259: astore 4
      //   261: goto -61 -> 200
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	264	0	paramContext	Context
      //   0	264	1	paramLong	long
      //   120	59	3	m	int
      //   18	183	4	localObject1	Object
      //   203	5	4	localException1	Exception
      //   213	16	4	localContext	Context
      //   248	1	4	localException2	Exception
      //   259	1	4	localObject2	Object
      //   101	53	5	str	String
      //   39	201	6	localCursor	android.database.Cursor
      // Exception table:
      //   from	to	target	type
      //   56	129	203	java/lang/Exception
      //   56	129	233	finally
      //   142	166	233	finally
      //   166	182	233	finally
      //   207	212	233	finally
      //   142	166	248	java/lang/Exception
      //   166	182	248	java/lang/Exception
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
    
    public static String a(int paramInt)
    {
      switch (paramInt)
      {
      case 1: 
      case 9: 
      case 11: 
      case 12: 
      default: 
        return 32 + "unknown status code: " + paramInt;
      case -1: 
        return "SUCCESS_CACHE";
      case 0: 
        return "SUCCESS";
      case 2: 
        return "SERVICE_VERSION_UPDATE_REQUIRED";
      case 3: 
        return "SERVICE_DISABLED";
      case 4: 
        return "SIGN_IN_REQUIRED";
      case 5: 
        return "INVALID_ACCOUNT";
      case 6: 
        return "RESOLUTION_REQUIRED";
      case 7: 
        return "NETWORK_ERROR";
      case 8: 
        return "INTERNAL_ERROR";
      case 10: 
        return "DEVELOPER_ERROR";
      case 13: 
        return "ERROR";
      case 14: 
        return "INTERRUPTED";
      case 15: 
        return "TIMEOUT";
      case 16: 
        return "CANCELED";
      case 17: 
        return "API_NOT_CONNECTED";
      }
      return "DEAD_CLIENT";
    }
    
    /* Error */
    public static String a(Context paramContext, String paramString1, String paramString2)
    {
      // Byte code:
      //   0: new 209	java/lang/StringBuilder
      //   3: dup
      //   4: invokespecial 758	java/lang/StringBuilder:<init>	()V
      //   7: astore 4
      //   9: sipush 512
      //   12: newarray char
      //   14: astore 5
      //   16: aload_0
      //   17: invokevirtual 106	android/content/Context:getResources	()Landroid/content/res/Resources;
      //   20: invokevirtual 762	android/content/res/Resources:getAssets	()Landroid/content/res/AssetManager;
      //   23: aload_1
      //   24: invokevirtual 768	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
      //   27: astore_0
      //   28: new 770	java/io/BufferedReader
      //   31: dup
      //   32: new 772	java/io/InputStreamReader
      //   35: dup
      //   36: aload_0
      //   37: aload_2
      //   38: invokespecial 775	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
      //   41: invokespecial 778	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   44: astore_1
      //   45: aload_1
      //   46: aload 5
      //   48: invokevirtual 781	java/io/BufferedReader:read	([C)I
      //   51: istore_3
      //   52: iload_3
      //   53: ifle +35 -> 88
      //   56: aload 4
      //   58: aload 5
      //   60: iconst_0
      //   61: iload_3
      //   62: invokevirtual 784	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
      //   65: pop
      //   66: goto -21 -> 45
      //   69: astore_2
      //   70: aload_0
      //   71: ifnull +7 -> 78
      //   74: aload_0
      //   75: invokevirtual 785	java/io/InputStream:close	()V
      //   78: aload_1
      //   79: ifnull +7 -> 86
      //   82: aload_1
      //   83: invokevirtual 786	java/io/BufferedReader:close	()V
      //   86: aconst_null
      //   87: areturn
      //   88: aload 4
      //   90: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   93: astore_2
      //   94: aload_0
      //   95: ifnull +7 -> 102
      //   98: aload_0
      //   99: invokevirtual 785	java/io/InputStream:close	()V
      //   102: aload_1
      //   103: invokevirtual 786	java/io/BufferedReader:close	()V
      //   106: aload_2
      //   107: areturn
      //   108: astore_0
      //   109: new 209	java/lang/StringBuilder
      //   112: dup
      //   113: ldc_w 788
      //   116: invokespecial 789	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   119: aload_0
      //   120: invokevirtual 792	java/io/IOException:getMessage	()Ljava/lang/String;
      //   123: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   126: pop
      //   127: aload_2
      //   128: areturn
      //   129: astore_0
      //   130: new 209	java/lang/StringBuilder
      //   133: dup
      //   134: ldc_w 788
      //   137: invokespecial 789	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   140: aload_0
      //   141: invokevirtual 792	java/io/IOException:getMessage	()Ljava/lang/String;
      //   144: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
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
      //   160: invokevirtual 785	java/io/InputStream:close	()V
      //   163: aload_1
      //   164: ifnull -78 -> 86
      //   167: aload_1
      //   168: invokevirtual 786	java/io/BufferedReader:close	()V
      //   171: aconst_null
      //   172: areturn
      //   173: astore_0
      //   174: new 209	java/lang/StringBuilder
      //   177: dup
      //   178: ldc_w 788
      //   181: invokespecial 789	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   184: aload_0
      //   185: invokevirtual 792	java/io/IOException:getMessage	()Ljava/lang/String;
      //   188: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
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
      //   204: invokevirtual 785	java/io/InputStream:close	()V
      //   207: aload_1
      //   208: ifnull +7 -> 215
      //   211: aload_1
      //   212: invokevirtual 786	java/io/BufferedReader:close	()V
      //   215: aload_2
      //   216: athrow
      //   217: astore_0
      //   218: new 209	java/lang/StringBuilder
      //   221: dup
      //   222: ldc_w 788
      //   225: invokespecial 789	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   228: aload_0
      //   229: invokevirtual 792	java/io/IOException:getMessage	()Ljava/lang/String;
      //   232: invokevirtual 217	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
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
      //   51	11	3	m	int
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
    
    public static <T extends fb> String a(T paramT)
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
      int m = 0;
      try
      {
        Object localObject = MessageDigest.getInstance("SHA-1");
        ((MessageDigest)localObject).reset();
        ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
        localObject = ((MessageDigest)localObject).digest();
        paramString = new Formatter();
        int n = localObject.length;
        while (m < n)
        {
          paramString.format("%02x", new Object[] { Byte.valueOf(localObject[m]) });
          m += 1;
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
    
    public static String a(String paramString, Object paramObject)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException(String.valueOf(paramObject));
      }
      return paramString;
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
    
    public static Field a(Object paramObject, String paramString)
      throws NoSuchFieldException
    {
      Class localClass = paramObject.getClass();
      while (localClass != null) {
        try
        {
          Field localField = localClass.getDeclaredField(paramString);
          if (!localField.isAccessible()) {
            localField.setAccessible(true);
          }
          return localField;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          localClass = localClass.getSuperclass();
        }
      }
      throw new NoSuchFieldException("Field " + paramString + " not found in " + paramObject.getClass());
    }
    
    public static ArrayList a(Parcel paramParcel, int paramInt, ClassLoader paramClassLoader)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramClassLoader = paramParcel.readArrayList(paramClassLoader);
      paramParcel.setDataPosition(paramInt + m);
      return paramClassLoader;
    }
    
    private static Map<String, u<io, Integer>> a(Context paramContext, iq paramIq)
    {
      paramContext = paramContext.getResources();
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      if ((paramIq != null) && (paramIq.b == 1))
      {
        paramIq = paramIq.c;
        NumberFormat localNumberFormat = NumberFormat.getInstance();
        if ((paramIq != null) && (paramIq.length > 0))
        {
          int n = paramIq.length;
          int m = 0;
          while (m < n)
          {
            Object localObject = paramIq[m];
            localObject.c = paramContext.getString(2131165439, new Object[] { localNumberFormat.format(localObject.d) });
            localLinkedHashMap.put(localObject.b, new u(localObject, Integer.valueOf(m)));
            m += 1;
          }
        }
      }
      return localLinkedHashMap;
    }
    
    public static void a(Context paramContext, List<gn> paramList)
    {
      if ((paramContext == null) || (paramList == null)) {
        return;
      }
      a(paramList);
    }
    
    public static void a(Bitmap paramBitmap, ae.a paramA)
    {
      if (paramBitmap != null) {
        new ae(paramBitmap, paramA).a();
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
        int n = (int)Math.ceil(Math.abs(4.0D * d1 / 3.141592653589793D));
        double d17 = Math.cos(d6);
        double d18 = Math.sin(d6);
        d6 = Math.cos(d5);
        d7 = Math.sin(d5);
        d2 = -d15 * d17 * d7 - d16 * d18 * d6;
        d6 = d6 * (d16 * d17) + d7 * (-d15 * d18);
        double d19 = d1 / n;
        int m = 0;
        d1 = d6;
        while (m < n)
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
          m += 1;
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
    
    public static void a(Drawable paramDrawable, int paramInt)
    {
      paramInt = c(DAApp.a(), paramInt);
      paramDrawable.mutate().setColorFilter(paramInt, PorterDuff.Mode.SRC_ATOP);
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
      paramInt = F(paramParcel, paramInt);
      paramParcel.writeBundle(paramBundle);
      G(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, Bundle paramBundle, boolean paramBoolean)
    {
      if (paramBundle == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt, 0);
        }
        return;
      }
      paramInt = I(paramParcel, paramInt);
      paramParcel.writeBundle(paramBundle);
      E(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return;
      }
      paramInt = F(paramParcel, paramInt);
      paramParcel.writeStrongBinder(paramIBinder);
      G(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, IBinder paramIBinder, boolean paramBoolean)
    {
      if (paramIBinder == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt, 0);
        }
        return;
      }
      paramInt = I(paramParcel, paramInt);
      paramParcel.writeStrongBinder(paramIBinder);
      E(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt1, Parcelable paramParcelable, int paramInt2)
    {
      if (paramParcelable == null) {
        return;
      }
      paramInt1 = F(paramParcel, paramInt1);
      paramParcelable.writeToParcel(paramParcel, paramInt2);
      G(paramParcel, paramInt1);
    }
    
    public static void a(Parcel paramParcel, int paramInt1, Parcelable paramParcelable, int paramInt2, boolean paramBoolean)
    {
      if (paramParcelable == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt1, 0);
        }
        return;
      }
      paramInt1 = I(paramParcel, paramInt1);
      paramParcelable.writeToParcel(paramParcel, paramInt2);
      E(paramParcel, paramInt1);
    }
    
    public static void a(Parcel paramParcel, int paramInt, Boolean paramBoolean)
    {
      if (paramBoolean == null) {
        return;
      }
      e(paramParcel, paramInt, 4);
      if (paramBoolean.booleanValue()) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
    
    public static void a(Parcel paramParcel, int paramInt, Double paramDouble)
    {
      if (paramDouble == null) {
        return;
      }
      e(paramParcel, paramInt, 8);
      paramParcel.writeDouble(paramDouble.doubleValue());
    }
    
    public static void a(Parcel paramParcel, int paramInt, Float paramFloat)
    {
      if (paramFloat == null) {
        return;
      }
      e(paramParcel, paramInt, 4);
      paramParcel.writeFloat(paramFloat.floatValue());
    }
    
    public static void a(Parcel paramParcel, int paramInt, Integer paramInteger)
    {
      if (paramInteger == null) {
        return;
      }
      c(paramParcel, paramInt, 4);
      paramParcel.writeInt(paramInteger.intValue());
    }
    
    public static void a(Parcel paramParcel, int paramInt, Long paramLong)
    {
      if (paramLong == null) {
        return;
      }
      e(paramParcel, paramInt, 8);
      paramParcel.writeLong(paramLong.longValue());
    }
    
    public static void a(Parcel paramParcel, int paramInt, String paramString)
    {
      if (paramString == null) {
        return;
      }
      paramInt = F(paramParcel, paramInt);
      paramParcel.writeString(paramString);
      G(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, String paramString, boolean paramBoolean)
    {
      if (paramString == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt, 0);
        }
        return;
      }
      paramInt = I(paramParcel, paramInt);
      paramParcel.writeString(paramString);
      E(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, List<String> paramList)
    {
      if (paramList == null) {
        return;
      }
      paramInt = F(paramParcel, paramInt);
      paramParcel.writeStringList(paramList);
      G(paramParcel, paramInt);
    }
    
    public static <T extends Parcelable> void a(Parcel paramParcel, int paramInt1, List<T> paramList, int paramInt2, boolean paramBoolean)
    {
      if (paramList == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt1, 0);
        }
        return;
      }
      paramInt1 = I(paramParcel, paramInt1);
      paramParcel.writeInt(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Parcelable localParcelable = (Parcelable)paramList.next();
        if (localParcelable == null) {
          paramParcel.writeInt(0);
        } else {
          b(paramParcel, localParcelable, paramInt2);
        }
      }
      E(paramParcel, paramInt1);
    }
    
    public static void a(Parcel paramParcel, int paramInt, List paramList, boolean paramBoolean)
    {
      if (paramList == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt, 0);
        }
        return;
      }
      paramInt = I(paramParcel, paramInt);
      paramParcel.writeList(paramList);
      E(paramParcel, paramInt);
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
    
    public static void a(Parcel paramParcel, int paramInt, byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null) {
        return;
      }
      paramInt = F(paramParcel, paramInt);
      paramParcel.writeByteArray(paramArrayOfByte);
      G(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, byte[] paramArrayOfByte, boolean paramBoolean)
    {
      if (paramArrayOfByte == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt, 0);
        }
        return;
      }
      paramInt = I(paramParcel, paramInt);
      paramParcel.writeByteArray(paramArrayOfByte);
      E(paramParcel, paramInt);
    }
    
    public static <T extends Parcelable> void a(Parcel paramParcel, int paramInt1, T[] paramArrayOfT, int paramInt2)
    {
      if (paramArrayOfT == null) {
        return;
      }
      int m = F(paramParcel, paramInt1);
      int n = paramArrayOfT.length;
      paramParcel.writeInt(n);
      paramInt1 = 0;
      if (paramInt1 < n)
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
      G(paramParcel, m);
    }
    
    public static <T extends Parcelable> void a(Parcel paramParcel, int paramInt1, T[] paramArrayOfT, int paramInt2, boolean paramBoolean)
    {
      if (paramArrayOfT == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt1, 0);
        }
        return;
      }
      int m = I(paramParcel, paramInt1);
      paramParcel.writeInt(paramArrayOfT.length);
      int n = paramArrayOfT.length;
      paramInt1 = 0;
      if (paramInt1 < n)
      {
        T ? = paramArrayOfT[paramInt1];
        if (? == null) {
          paramParcel.writeInt(0);
        }
        for (;;)
        {
          paramInt1 += 1;
          break;
          b(paramParcel, ?, paramInt2);
        }
      }
      E(paramParcel, m);
    }
    
    public static void a(Parcel paramParcel, int paramInt, String[] paramArrayOfString)
    {
      if (paramArrayOfString == null) {
        return;
      }
      paramInt = F(paramParcel, paramInt);
      paramParcel.writeStringArray(paramArrayOfString);
      G(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel, int paramInt, String[] paramArrayOfString, boolean paramBoolean)
    {
      if (paramArrayOfString == null)
      {
        if (paramBoolean) {
          e(paramParcel, paramInt, 0);
        }
        return;
      }
      paramInt = I(paramParcel, paramInt);
      paramParcel.writeStringArray(paramArrayOfString);
      E(paramParcel, paramInt);
    }
    
    public static void a(Parcel paramParcel1, Parcel paramParcel2)
    {
      if (paramParcel2 == null) {
        return;
      }
      int m = F(paramParcel1, 2);
      paramParcel1.appendFrom(paramParcel2, 0, paramParcel2.dataSize());
      G(paramParcel1, m);
    }
    
    private static <T extends Parcelable> void a(Parcel paramParcel, T paramT, int paramInt)
    {
      int m = paramParcel.dataPosition();
      paramParcel.writeInt(1);
      int n = paramParcel.dataPosition();
      paramT.writeToParcel(paramParcel, paramInt);
      paramInt = paramParcel.dataPosition();
      paramParcel.setDataPosition(m);
      paramParcel.writeInt(paramInt - n);
      paramParcel.setDataPosition(paramInt);
    }
    
    public static void a(Toolbar paramToolbar)
    {
      ViewGroup localViewGroup = (ViewGroup)paramToolbar.getParent();
      if (localViewGroup != null)
      {
        paramToolbar.setBackgroundColor(0);
        localViewGroup.setPadding(0, c.1.i(DAApp.a()), 0, 0);
      }
    }
    
    public static void a(gn paramGn)
    {
      if (paramGn == null) {}
      while ((!(paramGn instanceof gu)) || (TextUtils.isEmpty(paramGn.p()))) {
        return;
      }
      hb localHb = new hb();
      localHb.a(paramGn.m());
      localHb.a(new ArrayList(Arrays.asList(new String[] { paramGn.p() })));
      localHb.a(Math.max(15000L, paramGn.q()));
      localHb.a();
      Bundle localBundle2 = localHb.g();
      if (paramGn == null) {}
      Bundle localBundle1;
      for (paramGn = null;; paramGn = localBundle1)
      {
        localBundle2.putAll(paramGn);
        localHb.a(new s(null));
        gx.a(DAApp.a()).a(localHb);
        return;
        localBundle1 = new Bundle();
        localBundle1.putString("appId", paramGn.h());
        localBundle1.putString("pkgName", paramGn.m());
        localBundle1.putString("adSource", String.valueOf(paramGn.n()));
        localBundle1.putString("pageId", String.valueOf(paramGn.t().getInt("pageId")));
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
          int m = paramFile.read(arrayOfByte);
          if (m == -1) {
            break;
          }
          paramZipOutputStream.write(arrayOfByte, 0, m);
        }
        paramFile.close();
        paramZipOutputStream.flush();
        paramZipOutputStream.closeEntry();
      }
    }
    
    private static void a(Object paramObject1, String paramString, Object paramObject2)
      throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
      paramString = paramObject1.getClass().getDeclaredField(paramString);
      paramString.setAccessible(true);
      paramString.set(paramObject1, paramObject2);
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
        int m = str2.lastIndexOf('.');
        str1 = str2;
        if (m > 0) {
          str1 = str2.substring(m + 1);
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
        if (!(paramObject instanceof fb)) {
          break label417;
        }
        int i2 = paramStringBuffer1.length();
        if (paramString != null)
        {
          paramStringBuffer2.append(paramStringBuffer1).append(l(paramString)).append(" <\n");
          paramStringBuffer1.append("  ");
        }
        Class localClass = paramObject.getClass();
        Object localObject1 = localClass.getFields();
        int i3 = localObject1.length;
        int m = 0;
        String str;
        Object localObject2;
        while (m < i3)
        {
          Object localObject3 = localObject1[m];
          n = localObject3.getModifiers();
          str = localObject3.getName();
          if (((n & 0x1) == 1) && ((n & 0x8) != 8) && (!str.startsWith("_")) && (!str.endsWith("_")))
          {
            localObject2 = localObject3.getType();
            localObject3 = localObject3.get(paramObject);
            if ((((Class)localObject2).isArray()) && (((Class)localObject2).getComponentType() != Byte.TYPE))
            {
              if (localObject3 == null) {}
              for (n = 0;; n = Array.getLength(localObject3))
              {
                int i1 = 0;
                while (i1 < n)
                {
                  a(str, Array.get(localObject3, i1), paramStringBuffer1, paramStringBuffer2);
                  i1 += 1;
                }
              }
            }
            a(str, localObject3, paramStringBuffer1, paramStringBuffer2);
          }
          m += 1;
        }
        localObject1 = localClass.getMethods();
        int n = localObject1.length;
        m = 0;
        while (m < n)
        {
          str = localObject1[m].getName();
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
          m += 1;
        }
        if (paramString != null)
        {
          paramStringBuffer1.setLength(i2);
          paramStringBuffer2.append(paramStringBuffer1).append(">\n");
        }
      }
      return;
      label417:
      paramString = l(paramString);
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
        paramString = m(paramString);
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
      int n = paramArrayOfDouble.length;
      int m = 0;
      while (m < n)
      {
        if (m != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Double.toString(paramArrayOfDouble[m]));
        m += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, float[] paramArrayOfFloat)
    {
      int n = paramArrayOfFloat.length;
      int m = 0;
      while (m < n)
      {
        if (m != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Float.toString(paramArrayOfFloat[m]));
        m += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, long[] paramArrayOfLong)
    {
      int n = paramArrayOfLong.length;
      int m = 0;
      while (m < n)
      {
        if (m != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Long.toString(paramArrayOfLong[m]));
        m += 1;
      }
    }
    
    public static <T> void a(StringBuilder paramStringBuilder, T[] paramArrayOfT)
    {
      int n = paramArrayOfT.length;
      int m = 0;
      while (m < n)
      {
        if (m != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(paramArrayOfT[m].toString());
        m += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, String[] paramArrayOfString)
    {
      int n = paramArrayOfString.length;
      int m = 0;
      while (m < n)
      {
        if (m != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append("\"").append(paramArrayOfString[m]).append("\"");
        m += 1;
      }
    }
    
    public static void a(StringBuilder paramStringBuilder, boolean[] paramArrayOfBoolean)
    {
      int n = paramArrayOfBoolean.length;
      int m = 0;
      while (m < n)
      {
        if (m != 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append(Boolean.toString(paramArrayOfBoolean[m]));
        m += 1;
      }
    }
    
    public static void a(Throwable paramThrowable)
    {
      paramThrowable.printStackTrace(new PrintWriter(new StringWriter()));
    }
    
    public static void a(List<gn> paramList)
    {
      if (paramList == null) {}
      for (;;)
      {
        return;
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          gn localGn = (gn)paramList.next();
          if (((localGn instanceof gu)) && (!TextUtils.isEmpty(localGn.p()))) {
            a(localGn);
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
    
    public static void a(boolean paramBoolean)
    {
      if (!paramBoolean) {
        throw new IllegalStateException();
      }
    }
    
    public static void a(boolean paramBoolean, Object paramObject)
    {
      if (!paramBoolean) {
        throw new IllegalStateException(String.valueOf(paramObject));
      }
    }
    
    public static void a(boolean paramBoolean, String paramString, Object... paramVarArgs)
    {
      if (!paramBoolean) {
        throw new IllegalArgumentException(String.format(paramString, paramVarArgs));
      }
    }
    
    private static void a(byte[] paramArrayOfByte, StringBuffer paramStringBuffer)
    {
      if (paramArrayOfByte == null)
      {
        paramStringBuffer.append("\"\"");
        return;
      }
      paramStringBuffer.append('"');
      int m = 0;
      if (m < paramArrayOfByte.length)
      {
        int n = paramArrayOfByte[m] & 0xFF;
        if ((n == 92) || (n == 34)) {
          paramStringBuffer.append('\\').append((char)n);
        }
        for (;;)
        {
          m += 1;
          break;
          if ((n >= 32) && (n < 127)) {
            paramStringBuffer.append((char)n);
          } else {
            paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(n) }));
          }
        }
      }
      paramStringBuffer.append('"');
    }
    
    public static void a(a[] paramArrayOfA, Path paramPath)
    {
      float[] arrayOfFloat1 = new float[6];
      int i1 = 0;
      int n = 109;
      float[] arrayOfFloat2;
      float f4;
      float f3;
      float f6;
      float f1;
      float f2;
      int i2;
      float f7;
      float f8;
      label270:
      label462:
      float f9;
      if (i1 < paramArrayOfA.length)
      {
        int i3 = paramArrayOfA[i1].a;
        arrayOfFloat2 = paramArrayOfA[i1].b;
        f4 = arrayOfFloat1[0];
        f3 = arrayOfFloat1[1];
        f6 = arrayOfFloat1[2];
        f5 = arrayOfFloat1[3];
        f1 = arrayOfFloat1[4];
        f2 = arrayOfFloat1[5];
        int m;
        switch (i3)
        {
        default: 
          m = 2;
          i2 = 0;
          f7 = f3;
          f3 = f4;
          f4 = f7;
          f7 = f6;
          f8 = f5;
          if (i2 < arrayOfFloat2.length) {
            switch (i3)
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
            i2 += m;
            f9 = f2;
            float f10 = f4;
            n = i3;
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
            m = 2;
            break;
            m = 2;
            break;
            m = 1;
            break;
            m = 6;
            break;
            m = 4;
            break;
            m = 7;
            break;
            f3 += arrayOfFloat2[i2];
            f4 += arrayOfFloat2[(i2 + 1)];
            if (i2 > 0)
            {
              paramPath.rLineTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
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
              paramPath.rMoveTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
              f10 = f4;
              f9 = f3;
              f5 = f8;
              f1 = f4;
              f6 = f7;
              f2 = f3;
              f3 = f10;
              f4 = f9;
              continue;
              f3 = arrayOfFloat2[i2];
              f4 = arrayOfFloat2[(i2 + 1)];
              if (i2 > 0)
              {
                paramPath.lineTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
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
                paramPath.moveTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
                f10 = f4;
                f9 = f3;
                f5 = f8;
                f1 = f4;
                f6 = f7;
                f2 = f3;
                f3 = f10;
                f4 = f9;
                continue;
                paramPath.rLineTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
                f5 = arrayOfFloat2[i2];
                f4 += arrayOfFloat2[(i2 + 1)];
                f9 = f3 + f5;
                f3 = f2;
                f2 = f1;
                f5 = f8;
                f1 = f3;
                f6 = f7;
                f3 = f4;
                f4 = f9;
                continue;
                paramPath.lineTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
                f4 = arrayOfFloat2[i2];
                f9 = arrayOfFloat2[(i2 + 1)];
                f3 = f2;
                f2 = f1;
                f5 = f8;
                f1 = f3;
                f6 = f7;
                f3 = f9;
                continue;
                paramPath.rLineTo(arrayOfFloat2[i2], 0.0F);
                f5 = arrayOfFloat2[i2];
                f9 = f3 + f5;
                f3 = f1;
                f5 = f8;
                f1 = f2;
                f6 = f7;
                f2 = f3;
                f3 = f4;
                f4 = f9;
                continue;
                paramPath.lineTo(arrayOfFloat2[i2], f4);
                f5 = arrayOfFloat2[i2];
                f3 = f4;
                f4 = f5;
                f9 = f1;
                f5 = f8;
                f1 = f2;
                f6 = f7;
                f2 = f9;
                continue;
                paramPath.rLineTo(0.0F, arrayOfFloat2[i2]);
                f5 = arrayOfFloat2[i2];
                f9 = f1;
                f10 = f4 + f5;
                f4 = f3;
                f1 = f2;
                f5 = f8;
                f6 = f7;
                f2 = f9;
                f3 = f10;
                continue;
                paramPath.lineTo(f3, arrayOfFloat2[i2]);
                f9 = arrayOfFloat2[i2];
                f4 = f1;
                f10 = f3;
                f1 = f2;
                f5 = f8;
                f6 = f7;
                f2 = f4;
                f3 = f9;
                f4 = f10;
                continue;
                paramPath.rCubicTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)], arrayOfFloat2[(i2 + 2)], arrayOfFloat2[(i2 + 3)], arrayOfFloat2[(i2 + 4)], arrayOfFloat2[(i2 + 5)]);
                f6 = f3 + arrayOfFloat2[(i2 + 2)];
                f5 = f4 + arrayOfFloat2[(i2 + 3)];
                f7 = arrayOfFloat2[(i2 + 4)];
                f4 += arrayOfFloat2[(i2 + 5)];
                f7 = f3 + f7;
                f3 = f2;
                f2 = f1;
                f1 = f3;
                f3 = f4;
                f4 = f7;
                continue;
                paramPath.cubicTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)], arrayOfFloat2[(i2 + 2)], arrayOfFloat2[(i2 + 3)], arrayOfFloat2[(i2 + 4)], arrayOfFloat2[(i2 + 5)]);
                f4 = arrayOfFloat2[(i2 + 4)];
                f3 = arrayOfFloat2[(i2 + 5)];
                f7 = arrayOfFloat2[(i2 + 2)];
                f5 = arrayOfFloat2[(i2 + 3)];
                f6 = f2;
                f2 = f1;
                f1 = f6;
                f6 = f7;
              }
            }
          }
          f6 = 0.0F;
          if ((n == 99) || (n == 115) || (n == 67) || (n == 83)) {
            f6 = f4 - f8;
          }
          break;
        }
      }
      for (float f5 = f3 - f7;; f5 = 0.0F)
      {
        paramPath.rCubicTo(f5, f6, arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)], arrayOfFloat2[(i2 + 2)], arrayOfFloat2[(i2 + 3)]);
        f6 = f3 + arrayOfFloat2[i2];
        f5 = f4 + arrayOfFloat2[(i2 + 1)];
        f7 = arrayOfFloat2[(i2 + 2)];
        f4 += arrayOfFloat2[(i2 + 3)];
        f7 = f3 + f7;
        f3 = f2;
        f2 = f1;
        f1 = f3;
        f3 = f4;
        f4 = f7;
        break label462;
        if ((n == 99) || (n == 115) || (n == 67) || (n == 83))
        {
          f4 = 2.0F * f4 - f8;
          f3 = 2.0F * f3 - f7;
        }
        for (;;)
        {
          paramPath.cubicTo(f3, f4, arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)], arrayOfFloat2[(i2 + 2)], arrayOfFloat2[(i2 + 3)]);
          f6 = arrayOfFloat2[i2];
          f5 = arrayOfFloat2[(i2 + 1)];
          f4 = arrayOfFloat2[(i2 + 2)];
          f7 = arrayOfFloat2[(i2 + 3)];
          f3 = f2;
          f2 = f1;
          f1 = f3;
          f3 = f7;
          break label462;
          paramPath.rQuadTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)], arrayOfFloat2[(i2 + 2)], arrayOfFloat2[(i2 + 3)]);
          f6 = f3 + arrayOfFloat2[i2];
          f5 = f4 + arrayOfFloat2[(i2 + 1)];
          f7 = arrayOfFloat2[(i2 + 2)];
          f4 += arrayOfFloat2[(i2 + 3)];
          f7 = f3 + f7;
          f3 = f2;
          f2 = f1;
          f1 = f3;
          f3 = f4;
          f4 = f7;
          break label462;
          paramPath.quadTo(arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)], arrayOfFloat2[(i2 + 2)], arrayOfFloat2[(i2 + 3)]);
          f6 = arrayOfFloat2[i2];
          f5 = arrayOfFloat2[(i2 + 1)];
          f4 = arrayOfFloat2[(i2 + 2)];
          f7 = arrayOfFloat2[(i2 + 3)];
          f3 = f2;
          f2 = f1;
          f1 = f3;
          f3 = f7;
          break label462;
          if ((n == 113) || (n == 116) || (n == 81) || (n == 84)) {
            f5 = f4 - f8;
          }
          for (f6 = f3 - f7;; f6 = 0.0F)
          {
            paramPath.rQuadTo(f6, f5, arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
            f6 = f3 + f6;
            f5 = f4 + f5;
            f7 = arrayOfFloat2[i2];
            f4 += arrayOfFloat2[(i2 + 1)];
            f7 = f3 + f7;
            f3 = f2;
            f2 = f1;
            f1 = f3;
            f3 = f4;
            f4 = f7;
            break label462;
            if ((n != 113) && (n != 116) && (n != 81))
            {
              f6 = f4;
              f5 = f3;
              if (n != 84) {}
            }
            else
            {
              f5 = 2.0F * f3 - f7;
              f6 = 2.0F * f4 - f8;
            }
            paramPath.quadTo(f5, f6, arrayOfFloat2[i2], arrayOfFloat2[(i2 + 1)]);
            f4 = arrayOfFloat2[i2];
            f3 = arrayOfFloat2[(i2 + 1)];
            f7 = f6;
            f6 = f5;
            f8 = f2;
            f2 = f1;
            f5 = f7;
            f1 = f8;
            break label462;
            f5 = arrayOfFloat2[(i2 + 5)];
            f6 = arrayOfFloat2[(i2 + 6)];
            f7 = arrayOfFloat2[i2];
            f8 = arrayOfFloat2[(i2 + 1)];
            f9 = arrayOfFloat2[(i2 + 2)];
            boolean bool1;
            if (arrayOfFloat2[(i2 + 3)] != 0.0F)
            {
              bool1 = true;
              label2082:
              if (arrayOfFloat2[(i2 + 4)] == 0.0F) {
                break label2175;
              }
            }
            label2175:
            for (boolean bool2 = true;; bool2 = false)
            {
              a(paramPath, f3, f4, f5 + f3, f6 + f4, f7, f8, f9, bool1, bool2);
              f6 = f3 + arrayOfFloat2[(i2 + 5)];
              f5 = f4 + arrayOfFloat2[(i2 + 6)];
              f7 = f2;
              f2 = f1;
              f3 = f5;
              f4 = f6;
              f1 = f7;
              break;
              bool1 = false;
              break label2082;
            }
            f5 = arrayOfFloat2[(i2 + 5)];
            f6 = arrayOfFloat2[(i2 + 6)];
            f7 = arrayOfFloat2[i2];
            f8 = arrayOfFloat2[(i2 + 1)];
            f9 = arrayOfFloat2[(i2 + 2)];
            if (arrayOfFloat2[(i2 + 3)] != 0.0F)
            {
              bool1 = true;
              label2240:
              if (arrayOfFloat2[(i2 + 4)] == 0.0F) {
                break label2321;
              }
            }
            label2321:
            for (bool2 = true;; bool2 = false)
            {
              a(paramPath, f3, f4, f5, f6, f7, f8, f9, bool1, bool2);
              f6 = arrayOfFloat2[(i2 + 5)];
              f5 = arrayOfFloat2[(i2 + 6)];
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
            n = paramArrayOfA[i1].a;
            i1 += 1;
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
    
    @TargetApi(20)
    public static boolean a(Context paramContext)
    {
      if (h == null) {
        if ((!c(20)) || (!paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch"))) {
          break label43;
        }
      }
      label43:
      for (boolean bool = true;; bool = false)
      {
        h = Boolean.valueOf(bool);
        return h.booleanValue();
      }
    }
    
    public static boolean a(Context paramContext, String paramString, int paramInt)
    {
      label159:
      do
      {
        try
        {
          if (Build.VERSION.SDK_INT > 18) {
            return b(paramContext, paramString, paramInt);
          }
          if (Build.VERSION.SDK_INT <= 13) {
            break label159;
          }
          Object localObject = Class.forName("android.webkit.WebViewCore");
          paramContext = Class.forName("android.net.ProxyProperties");
          if ((localObject != null) && (paramContext != null))
          {
            localObject = ((Class)localObject).getDeclaredMethod("sendStaticMessage", new Class[] { Integer.TYPE, Object.class });
            paramContext = paramContext.getConstructor(new Class[] { String.class, Integer.TYPE, String.class });
            ((Method)localObject).setAccessible(true);
            paramContext.setAccessible(true);
            ((Method)localObject).invoke(null, new Object[] { Integer.valueOf(193), paramContext.newInstance(new Object[] { paramString, Integer.valueOf(paramInt), null }) });
            return true;
          }
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
          return false;
        }
        return false;
        paramContext = n(paramContext);
      } while (paramContext == null);
      a(paramContext, "mProxyHost", new HttpHost(paramString, paramInt, "http"));
      return true;
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
    
    public static boolean a(Resources paramResources)
    {
      boolean bool2 = false;
      if (paramResources == null) {
        return false;
      }
      int m;
      if (f == null)
      {
        if ((paramResources.getConfiguration().screenLayout & 0xF) <= 3) {
          break label118;
        }
        m = 1;
        if ((!c(11)) || (m == 0)) {
          if (g == null)
          {
            paramResources = paramResources.getConfiguration();
            if ((!c(13)) || ((paramResources.screenLayout & 0xF) > 3) || (paramResources.smallestScreenWidthDp < 600)) {
              break label123;
            }
          }
        }
      }
      label118:
      label123:
      for (boolean bool1 = true;; bool1 = false)
      {
        g = Boolean.valueOf(bool1);
        bool1 = bool2;
        if (g.booleanValue()) {
          bool1 = true;
        }
        f = Boolean.valueOf(bool1);
        return f.booleanValue();
        m = 0;
        break;
      }
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
      int m = 0;
      for (;;)
      {
        if (m >= 3) {
          break label291;
        }
        try
        {
          Object localObject1 = new URL(paramString.trim());
          localObject2 = (HttpURLConnection)((URL)localObject1).openConnection();
          if (TextUtils.equals("https", ((URL)localObject1).getProtocol())) {
            ((HttpsURLConnection)localObject2).setSSLSocketFactory(com.lbe.parallel.utility.d.a().b());
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
            int n = ((InputStream)localObject2).read(arrayOfByte);
            if (n == -1) {
              break;
            }
            ((ByteArrayOutputStream)localObject1).write(arrayOfByte, 0, n);
            ((ByteArrayOutputStream)localObject1).flush();
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          SystemClock.sleep(3000L);
          m += 1;
        }
      }
      Object localObject2 = android.support.v4.app.b.b(localException.toByteArray(), 4.37213640136E7D);
      localException.close();
      return localObject2;
      label291:
      return null;
    }
    
    public static int b(Parcel paramParcel)
    {
      return F(paramParcel, 20293);
    }
    
    public static int b(AttributeSet paramAttributeSet, String paramString)
    {
      return paramAttributeSet.getAttributeResourceValue("http://schemas.android.com/apk/res-auto", paramString, -1);
    }
    
    public static ColorStateList b(Context paramContext, int paramInt, SkinPackage paramSkinPackage)
    {
      paramInt = a(paramContext, paramInt, paramSkinPackage);
      return paramSkinPackage.c().getColorStateList(paramInt);
    }
    
    public static Drawable b(Context paramContext, int paramInt)
    {
      paramInt = d(paramContext, paramInt);
      return z().getDrawable(paramInt);
    }
    
    public static Bundle b(Parcel paramParcel, int paramInt, ClassLoader paramClassLoader)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramClassLoader = paramParcel.readBundle(paramClassLoader);
      paramParcel.setDataPosition(paramInt + m);
      return paramClassLoader;
    }
    
    public static AdRecord b(gn paramGn)
    {
      if (paramGn == null) {
        return new AdRecord();
      }
      Object localObject1 = paramGn.t();
      int m = ((Bundle)localObject1).getInt("policyId");
      int n = ((Bundle)localObject1).getInt("pageId");
      long l1 = ((Bundle)localObject1).getLong("createTime");
      int i1 = ((Bundle)localObject1).getInt("fromPageId");
      String str1 = ((Bundle)localObject1).getString("rowId");
      String str2 = ((Bundle)localObject1).getString("colId");
      Object localObject2 = ((Bundle)localObject1).getString("eventType");
      String str3 = ((Bundle)localObject1).getString("adMobUnitId");
      String str4 = ((Bundle)localObject1).getString("fbPlacementId");
      String str5 = ((Bundle)localObject1).getString("clickPos");
      localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = "2";
      }
      localObject2 = new AdRecord.Builder();
      ((AdRecord.Builder)localObject2).setPolicyId(String.valueOf(m)).setPageId(String.valueOf(String.valueOf(n))).setRowId(str1).setColId(str2).setAdSource(String.valueOf(paramGn.n())).setEventType((String)localObject1).setAppId(paramGn.h()).setPkgName(paramGn.m()).setTimeStamp(l1).setAdMobUnitId(str3).setFbPlacementId(str4).setClickPos(str5).setEventTime(String.valueOf(System.currentTimeMillis())).setAdType(String.valueOf(paramGn.r().a()));
      if (i1 > 0) {
        ((AdRecord.Builder)localObject2).setFromPageId(String.valueOf(i1));
      }
      return ((AdRecord.Builder)localObject2).build();
    }
    
    public static <T> T b(T paramT)
    {
      if (paramT == null) {
        throw new NullPointerException();
      }
      return paramT;
    }
    
    public static <T> T b(T paramT, Object paramObject)
    {
      if (paramT == null) {
        throw new NullPointerException(String.valueOf(paramObject));
      }
      return paramT;
    }
    
    public static String b()
    {
      return e;
    }
    
    public static String b(byte[] paramArrayOfByte)
    {
      if (paramArrayOfByte == null) {
        return null;
      }
      return Base64.encodeToString(paramArrayOfByte, 10);
    }
    
    public static Field b(String paramString1, String paramString2)
      throws NoSuchFieldException, ClassNotFoundException
    {
      Class localClass = Class.forName(paramString1);
      while (localClass != null) {
        try
        {
          Field localField = localClass.getDeclaredField(paramString2);
          if (!localField.isAccessible()) {
            localField.setAccessible(true);
          }
          return localField;
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          localClass = localClass.getSuperclass();
        }
      }
      throw new NoSuchFieldException("Field " + paramString2 + " not found in " + paramString1);
    }
    
    public static void b(Context paramContext)
    {
      try
      {
        if (i == null) {
          i = Volley.newRequestQueue(paramContext);
        }
        if (k == null) {
          k = new hl(paramContext, i.getCache());
        }
        if (j == null)
        {
          if (com.lbe.parallel.utility.af.p(paramContext)) {}
          for (paramContext = Bitmap.Config.ARGB_8888;; paramContext = Bitmap.Config.RGB_565)
          {
            j = new ImageLoader(i, k, paramContext);
            return;
          }
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
    
    public static void b(Parcel paramParcel, int paramInt, Integer paramInteger)
    {
      if (paramInteger == null) {
        return;
      }
      e(paramParcel, paramInt, 4);
      paramParcel.writeInt(paramInteger.intValue());
    }
    
    public static <T extends Parcelable> void b(Parcel paramParcel, int paramInt, List<T> paramList)
    {
      if (paramList == null) {
        return;
      }
      int m = F(paramParcel, paramInt);
      int n = paramList.size();
      paramParcel.writeInt(n);
      paramInt = 0;
      if (paramInt < n)
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
      G(paramParcel, m);
    }
    
    private static <T extends Parcelable> void b(Parcel paramParcel, T paramT, int paramInt)
    {
      int m = paramParcel.dataPosition();
      paramParcel.writeInt(1);
      int n = paramParcel.dataPosition();
      paramT.writeToParcel(paramParcel, paramInt);
      paramInt = paramParcel.dataPosition();
      paramParcel.setDataPosition(m);
      paramParcel.writeInt(paramInt - n);
      paramParcel.setDataPosition(paramInt);
    }
    
    public static void b(String paramString)
    {
      c = paramString;
      StringBuilder localStringBuilder = new StringBuilder();
      int m = 0;
      if (m < paramString.length())
      {
        if ((m == 0) || (m == 1) || (m > paramString.length() - 5)) {
          localStringBuilder.append(paramString.charAt(m));
        }
        for (;;)
        {
          m += 1;
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
        int m;
        try
        {
          StringBuffer localStringBuffer = new StringBuffer();
          int n = paramList.size();
          m = 0;
          if (m < n)
          {
            String str = ((PackageData)paramList.get(m)).getPackageName();
            if (!TextUtils.isEmpty(str))
            {
              localStringBuffer.append(str);
              if (m != n - 1) {
                localStringBuffer.append(";");
              }
            }
          }
          else
          {
            paramList = localStringBuffer.toString();
            ab.a().b("home_apps_ordered_list", paramList);
            return;
          }
        }
        finally {}
        m += 1;
      }
    }
    
    public static void b(boolean paramBoolean, Object paramObject)
    {
      if (!paramBoolean) {
        throw new IllegalArgumentException(String.valueOf(paramObject));
      }
    }
    
    public static boolean b(int paramInt)
    {
      if (!cl.a(null).a(paramInt, "com.google.android.gms")) {
        return false;
      }
      throw new NullPointerException();
    }
    
    @TargetApi(12)
    public static boolean b(Context paramContext, String paramString)
    {
      if (!c(12)) {}
      for (;;)
      {
        return false;
        try
        {
          int m = cl.a(paramContext).a(paramString, 0).flags;
          if ((m & 0x200000) != 0) {
            return true;
          }
        }
        catch (PackageManager.NameNotFoundException paramContext) {}
      }
      return false;
    }
    
    @TargetApi(19)
    private static boolean b(Context paramContext, String paramString, int paramInt)
    {
      if (Build.VERSION.SDK_INT == 19)
      {
        System.setProperty("http.proxyHost", paramString);
        System.setProperty("http.proxyPort", String.valueOf(paramInt));
        System.setProperty("https.proxyHost", paramString);
        System.setProperty("https.proxyPort", String.valueOf(paramInt));
      }
      paramContext = paramContext.getApplicationContext();
      try
      {
        Object localObject1 = paramContext.getClass().getField("mLoadedApk");
        ((Field)localObject1).setAccessible(true);
        localObject1 = ((Field)localObject1).get(paramContext);
        Object localObject2 = Class.forName("android.app.LoadedApk").getDeclaredField("mReceivers");
        ((Field)localObject2).setAccessible(true);
        localObject1 = ((ArrayMap)((Field)localObject2).get(localObject1)).values().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = ((ArrayMap)((Iterator)localObject1).next()).keySet().iterator();
          while (((Iterator)localObject2).hasNext())
          {
            Object localObject3 = ((Iterator)localObject2).next();
            Object localObject4 = localObject3.getClass();
            if (((Class)localObject4).getName().contains("ProxyChangeListener"))
            {
              localObject4 = ((Class)localObject4).getDeclaredMethod("onReceive", new Class[] { Context.class, Intent.class });
              Intent localIntent = new Intent("android.intent.action.PROXY_CHANGE");
              if ((Build.VERSION.SDK_INT >= 15) && (Build.VERSION.SDK_INT <= 20))
              {
                Constructor localConstructor = Class.forName("android.net.ProxyProperties").getConstructor(new Class[] { String.class, Integer.TYPE, String.class });
                localConstructor.setAccessible(true);
                localIntent.putExtra("android.intent.extra.PROXY_INFO", (Parcelable)localConstructor.newInstance(new Object[] { paramString, Integer.valueOf(paramInt), null }));
                ((Method)localObject4).invoke(localObject3, new Object[] { paramContext, localIntent });
              }
              if ((Build.VERSION.SDK_INT >= 21) && (Build.VERSION.SDK_INT <= 24))
              {
                localIntent.putExtra("android.intent.extra.PROXY_INFO", (Parcelable)Class.forName("android.net.ProxyInfo").getConstructor(new Class[] { String.class, Integer.TYPE, String.class }).newInstance(new Object[] { paramString, Integer.valueOf(paramInt), null }));
                ((Method)localObject4).invoke(localObject3, new Object[] { paramContext, localIntent });
              }
            }
          }
        }
        return true;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
    }
    
    public static <T> T[] b(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = paramParcel.createTypedArray(paramCreator);
      paramParcel.setDataPosition(paramInt + m);
      return paramCreator;
    }
    
    public static int c(Context paramContext, int paramInt)
    {
      paramInt = d(paramContext, paramInt);
      return z().getColor(paramInt);
    }
    
    public static int c(Parcel paramParcel)
    {
      int n = paramParcel.readInt();
      int i1 = H(paramParcel, n);
      int m = paramParcel.dataPosition();
      if ((0xFFFF & n) != 20293) {
        throw new Fragment.a("Expected object header. Got 0x" + Integer.toHexString(n));
      }
      n = m + i1;
      if ((n < m) || (n > paramParcel.dataSize())) {
        throw new Fragment.a("Size read is invalid start=" + m + " end=" + n);
      }
      return n;
    }
    
    public static AdClientInfo c(Context paramContext)
    {
      ClientInfo localClientInfo = ClientInfo.get();
      AdClientInfo localAdClientInfo = new AdClientInfo();
      localAdClientInfo.setGpCountry(localClientInfo.getGpCountry());
      localAdClientInfo.setPsGpCountry(localClientInfo.getPsGpCountry());
      localAdClientInfo.setUserGroupId(localClientInfo.getUserGroupId());
      localAdClientInfo.setChannel(localClientInfo.getChannel());
      localAdClientInfo.setChannelNetwork(localClientInfo.getChannelNetwork());
      localAdClientInfo.setChannelCampaign(localClientInfo.getChannelCampaign());
      localAdClientInfo.setChannelAdGroup(localClientInfo.getChannelAdGroup());
      localAdClientInfo.setPkgName(localClientInfo.getPkgName());
      localAdClientInfo.setVersionCode(localClientInfo.getVersionCode());
      localAdClientInfo.setVersionName(localClientInfo.getVersionName());
      localAdClientInfo.setFileMD5(localClientInfo.getFileMD5());
      localAdClientInfo.setSignatureMD5(localClientInfo.getSignatureMD5());
      localAdClientInfo.setInstallerPackageName(paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName()));
      localAdClientInfo.setLoginGP(localClientInfo.isLoginGP());
      localAdClientInfo.setLoginFB(localClientInfo.isLoginFB());
      localAdClientInfo.setLoginGPInPS(localClientInfo.isLoginGPInPS());
      localAdClientInfo.setLoginFBInPS(localClientInfo.isLoginFBInPS());
      localAdClientInfo.setDebug(localClientInfo.isDebug());
      localAdClientInfo.setSmartLockOpen(localClientInfo.isSmartLockOpen());
      return localAdClientInfo;
    }
    
    public static RecommendAdRequest c(Context paramContext, String paramString)
    {
      RecommendAdRequest localRecommendAdRequest = new RecommendAdRequest();
      String[] arrayOfString = fn.a(paramContext).e(DAApp.a().c());
      if (TextUtils.isEmpty(paramString))
      {
        localRecommendAdRequest.setOsNewPkg(ab.a().d("system_package_added_set"));
        if (Math.abs(System.currentTimeMillis() - ab.a().e(SPConstant.LAST_OS_PKG_UPLOAD_TIME)) >= 129600000L) {
          localRecommendAdRequest.setOsPkgs(c.1.d(paramContext));
        }
        if (arrayOfString != null) {
          break label131;
        }
      }
      label131:
      for (paramString = new ArrayList();; paramString = Arrays.asList(arrayOfString))
      {
        localRecommendAdRequest.setPsApps(paramString);
        localRecommendAdRequest.setOsRunning(c.1.e(paramContext));
        localRecommendAdRequest.setClientInfo(c(paramContext));
        localRecommendAdRequest.setDeviceInfo(q());
        localRecommendAdRequest.setPsDevInfo(e(paramContext));
        return localRecommendAdRequest;
        localRecommendAdRequest.setPsAppQuit(paramString);
        break;
      }
    }
    
    public static <T> ArrayList<T> c(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = paramParcel.createTypedArrayList(paramCreator);
      paramParcel.setDataPosition(paramInt + m);
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
        b(h.a().a("AppsFlyerKey"));
      }
      while ((c == null) || (!paramString.contains(c))) {
        return;
      }
      paramString.replace(c, d);
      com.appsflyer.b.a();
    }
    
    public static boolean c()
    {
      return c(11);
    }
    
    private static boolean c(int paramInt)
    {
      return Build.VERSION.SDK_INT >= paramInt;
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
    
    private static int d(Context paramContext, int paramInt)
    {
      Object localObject = paramContext.getResources();
      paramContext = ((Resources)localObject).getResourceTypeName(paramInt);
      localObject = ((Resources)localObject).getResourceEntryName(paramInt);
      String str = com.lbe.parallel.skin.d.a().b().b;
      int m = z().getIdentifier((String)localObject, paramContext, str);
      if (m == 0) {
        return paramInt;
      }
      return m;
    }
    
    public static int d(Parcel paramParcel)
    {
      return I(paramParcel, 20293);
    }
    
    public static int d(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 4);
      return paramParcel.readInt();
    }
    
    public static <T extends Parcelable> T d(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = (Parcelable)paramCreator.createFromParcel(paramParcel);
      paramParcel.setDataPosition(paramInt + m);
      return paramCreator;
    }
    
    public static String d(Context paramContext)
    {
      HashMap localHashMap = new HashMap();
      HashSet localHashSet = new HashSet();
      Object localObject = new fq(paramContext).getInstalledPackages(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!a(localPackageInfo.applicationInfo)) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
      localObject = fn.a(paramContext).f(DAApp.a().c());
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          localHashSet.add((String)((Iterator)localObject).next());
        }
      }
      localObject = q();
      localHashMap.put("clientInfo", c(paramContext));
      localHashMap.put("deviceInfo", localObject);
      localHashMap.put("existPkgs", localHashSet);
      return JSON.toJSONString(localHashMap);
    }
    
    public static String d(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("Given String is empty or null");
      }
      return paramString;
    }
    
    private static void d(Parcel paramParcel, int paramInt1, int paramInt2)
    {
      paramInt1 = H(paramParcel, paramInt1);
      if (paramInt1 != paramInt2) {
        throw new Fragment.a("Expected size " + paramInt2 + " got " + paramInt1 + " (0x" + Integer.toHexString(paramInt1) + ")");
      }
    }
    
    public static boolean d()
    {
      return c(14);
    }
    
    /* Error */
    public static byte[] d(Context paramContext, String paramString)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_0
      //   3: aload_1
      //   4: invokevirtual 2213	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
      //   7: astore_0
      //   8: aload_0
      //   9: astore_1
      //   10: aload_0
      //   11: invokestatic 2215	com/lbe/parallel/f$a:a	(Ljava/io/InputStream;)[B
      //   14: astore_2
      //   15: aload_2
      //   16: astore_1
      //   17: aload_0
      //   18: ifnull +9 -> 27
      //   21: aload_0
      //   22: invokestatic 1576	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
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
      //   35: invokevirtual 422	java/lang/Exception:printStackTrace	()V
      //   38: aload_3
      //   39: astore_1
      //   40: aload_0
      //   41: ifnull -14 -> 27
      //   44: aload_0
      //   45: invokestatic 1576	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   48: aconst_null
      //   49: areturn
      //   50: astore_0
      //   51: aconst_null
      //   52: astore_1
      //   53: aload_1
      //   54: ifnull +7 -> 61
      //   57: aload_1
      //   58: invokestatic 1576	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
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
    
    public static PsDevInfo e(Context paramContext)
    {
      PsDevInfo localPsDevInfo = new PsDevInfo();
      localPsDevInfo.setVersionName("3.5.7542");
      DAUser localDAUser = fn.a(paramContext).a(DAApp.a().c());
      if (localDAUser != null)
      {
        localPsDevInfo.setAndroidId(localDAUser.b());
        localPsDevInfo.setAndroidAdId(localDAUser.c());
        localPsDevInfo.setImei(localDAUser.a());
        localPsDevInfo.setMac(localDAUser.d());
        localPsDevInfo.setBtMac(localDAUser.e());
        localPsDevInfo.setFbAid(com.lbe.parallel.utility.c.a(paramContext).b());
      }
      return localPsDevInfo;
    }
    
    public static Integer e(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      if (paramInt == 0) {
        return null;
      }
      if (paramInt != 4)
      {
        String str = String.valueOf(Integer.toHexString(paramInt));
        throw new Fragment.a(String.valueOf(str).length() + 46 + "Expected size 4 got " + paramInt + " (0x" + str + ")", paramParcel);
      }
      return Integer.valueOf(paramParcel.readInt());
    }
    
    public static <T extends Parcelable> ArrayList<T> e(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = paramParcel.createTypedArrayList(paramCreator);
      paramParcel.setDataPosition(paramInt + m);
      return paramCreator;
    }
    
    private static void e(Parcel paramParcel, int paramInt1, int paramInt2)
    {
      if (paramInt2 >= 65535)
      {
        paramParcel.writeInt(0xFFFF0000 | paramInt1);
        paramParcel.writeInt(paramInt2);
        return;
      }
      paramParcel.writeInt(paramInt2 << 16 | paramInt1);
    }
    
    public static void e(String paramString)
    {
      if (Looper.myLooper() != Looper.getMainLooper()) {
        throw new IllegalStateException(paramString);
      }
    }
    
    public static boolean e()
    {
      return c(16);
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
    
    public static long f(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 8);
      return paramParcel.readLong();
    }
    
    public static void f(Context paramContext)
      throws Exception
    {
      a(paramContext, "", 0);
      paramContext = n(paramContext);
      if (paramContext != null) {
        a(paramContext, "mProxyHost", null);
      }
    }
    
    public static void f(String paramString)
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new IllegalStateException(paramString);
      }
    }
    
    public static boolean f()
    {
      return c(17);
    }
    
    public static <T extends Parcelable> T[] f(Parcel paramParcel, int paramInt, Parcelable.Creator<T> paramCreator)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      paramCreator = (Parcelable[])paramParcel.createTypedArray(paramCreator);
      paramParcel.setDataPosition(paramInt + m);
      return paramCreator;
    }
    
    public static BigInteger g(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(paramInt + m);
      return new BigInteger(arrayOfByte);
    }
    
    public static void g(String paramString)
    {
      try
      {
        if (TextUtils.isEmpty(paramString)) {
          throw new NullPointerException("packageName can not be null");
        }
      }
      finally {}
      Object localObject = ab.a().c("home_apps_ordered_list");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = new StringBuffer((String)localObject);
        if (((StringBuffer)localObject).length() <= 0) {
          break label101;
        }
        ((StringBuffer)localObject).append(";").append(paramString);
      }
      for (;;)
      {
        ab.a().a("home_apps_ordered_list", ((StringBuffer)localObject).toString());
        return;
        localObject = new StringBuffer();
        break;
        label101:
        ((StringBuffer)localObject).append(paramString);
      }
    }
    
    public static boolean g()
    {
      return c(18);
    }
    
    public static boolean g(Context paramContext)
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
    
    public static float h(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 4);
      return paramParcel.readFloat();
    }
    
    public static Map<String, u<io, Integer>> h(Context paramContext)
    {
      long l1 = f(paramContext, "hot_apps_response.info");
      Object localObject1;
      Object localObject3;
      Object localObject2;
      Object localObject4;
      boolean bool;
      if ((l1 != 0L) && (System.currentTimeMillis() - l1 < 86400000L))
      {
        localObject1 = new ip();
        ((ip)localObject1).b = o();
        ((ip)localObject1).c = p();
        localObject3 = new fq(paramContext).getInstalledPackages(0);
        localObject2 = new ArrayList(((List)localObject3).size());
        localObject3 = ((List)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (PackageInfo)((Iterator)localObject3).next();
          if (!a(((PackageInfo)localObject4).applicationInfo)) {
            ((List)localObject2).add(a((PackageInfo)localObject4));
          }
        }
        ((ip)localObject1).d = ((if[])((List)localObject2).toArray(new if[((List)localObject2).size()]));
        localObject2 = p(paramContext);
        if (localObject2 != null) {
          if (localObject1 == localObject2)
          {
            bool = true;
            if (!bool) {
              break label418;
            }
          }
        }
      }
      label418:
      for (int m = 1;; m = 0)
      {
        if (m != 0)
        {
          localObject1 = a(paramContext, o(paramContext));
          return localObject1;
          if (localObject2 == null)
          {
            bool = false;
            break;
          }
          if (localObject1.getClass() != localObject2.getClass())
          {
            bool = false;
            break;
          }
          m = ((fb)localObject1).b();
          if (((fb)localObject2).b() != m)
          {
            bool = false;
            break;
          }
          localObject3 = new byte[m];
          localObject4 = new byte[m];
          fb.a((fb)localObject1, (byte[])localObject3, m);
          fb.a((fb)localObject2, (byte[])localObject4, m);
          bool = Arrays.equals((byte[])localObject3, (byte[])localObject4);
          break;
        }
        com.lbe.parallel.utility.af.e(paramContext);
        localObject3 = paramContext.getResources().getStringArray(2131230722);
        localObject2 = new LinkedHashMap(localObject3.length);
        int n = localObject3.length;
        m = 0;
        for (;;)
        {
          localObject1 = localObject2;
          if (m >= n) {
            break;
          }
          localObject1 = new io();
          ((io)localObject1).b = localObject3[m];
          ((io)localObject1).c = paramContext.getString(2131165439, new Object[] { n(((io)localObject1).b) });
          ((LinkedHashMap)localObject2).put(((io)localObject1).b, new u(localObject1, Integer.valueOf(m)));
          m += 1;
        }
      }
    }
    
    public static boolean h()
    {
      return c(19);
    }
    
    public static boolean h(String paramString)
    {
      if ((TextUtils.equals("com.facebook.katana", paramString)) || (TextUtils.equals("com.whatsapp", paramString)))
      {
        m.a();
        if (!m.j(paramString)) {
          break label33;
        }
      }
      label33:
      while ((ab.a().b(o(paramString)) < 2) || (j(DAApp.a()))) {
        return false;
      }
      return true;
    }
    
    public static double i(Parcel paramParcel, int paramInt)
    {
      b(paramParcel, paramInt, 8);
      return paramParcel.readDouble();
    }
    
    public static List<PackageInfo> i(Context paramContext)
    {
      fq localFq = new fq(paramContext);
      Object localObject1 = localFq.getInstalledPackages(0);
      ArrayList localArrayList = new ArrayList(((List)localObject1).size());
      System.currentTimeMillis();
      Iterator localIterator = ((List)localObject1).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo != null) && (localPackageInfo.applicationInfo != null) && (localPackageInfo.packageName != null))
        {
          int m;
          label100:
          Object localObject2;
          if (!a(localPackageInfo.applicationInfo))
          {
            m = 1;
            if ((m == 0) || (i.b.contains(localPackageInfo.packageName))) {
              break label277;
            }
            localObject2 = w.a().b(localPackageInfo.packageName);
            localObject1 = localObject2;
            if (localObject2 == null)
            {
              if (localFq.getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
                break label279;
              }
              localObject1 = Boolean.TRUE;
              label158:
              w.a().a(localPackageInfo.packageName, (Boolean)localObject1);
            }
            if (!((Boolean)localObject1).booleanValue()) {
              continue;
            }
            localObject2 = ah.a().a(localPackageInfo.packageName);
            localObject1 = localObject2;
            if (localObject2 != null) {}
          }
          for (;;)
          {
            try
            {
              localObject1 = localFq.getApplicationInfo(localPackageInfo.packageName, 128);
              fp.a(paramContext);
              boolean bool = fp.a((ApplicationInfo)localObject1);
              localObject1 = Boolean.valueOf(bool);
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException2)
            {
              label277:
              label279:
              localObject1 = localObject2;
              localObject2 = localNameNotFoundException2;
            }
            try
            {
              ah.a().a(localPackageInfo.packageName, (Boolean)localObject1);
              localObject2 = localObject1;
              localObject1 = localObject2;
              if (localObject2 == null) {
                localObject1 = Boolean.FALSE;
              }
              if (((Boolean)localObject1).booleanValue()) {
                break;
              }
              localArrayList.add(localPackageInfo);
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException1)
            {
              for (;;) {}
            }
            m = 0;
            break label100;
            break;
            localObject1 = Boolean.FALSE;
            break label158;
            ((PackageManager.NameNotFoundException)localObject2).printStackTrace();
            localObject2 = localObject1;
          }
        }
      }
      return localArrayList;
    }
    
    public static void i(String paramString)
    {
      String str = o(paramString);
      if (!TextUtils.isEmpty(str))
      {
        ab.a().a(str, 0);
        m.a();
        m.i(paramString);
      }
    }
    
    public static boolean i()
    {
      return c(20);
    }
    
    public static BigDecimal j(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      int n = paramParcel.readInt();
      paramParcel.setDataPosition(paramInt + m);
      return new BigDecimal(new BigInteger(arrayOfByte), n);
    }
    
    public static boolean j()
    {
      return c(21);
    }
    
    public static boolean j(Context paramContext)
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        PowerManager localPowerManager = (PowerManager)paramContext.getSystemService("power");
        if (localPowerManager == null) {
          return false;
        }
        return localPowerManager.isIgnoringBatteryOptimizations(paramContext.getPackageName());
      }
      return false;
    }
    
    /* Error */
    public static byte[] j(String paramString)
    {
      // Byte code:
      //   0: invokestatic 2455	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
      //   3: ldc_w 2457
      //   6: invokevirtual 2459	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   9: ifeq +90 -> 99
      //   12: new 1252	java/io/File
      //   15: dup
      //   16: invokestatic 2462	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
      //   19: aload_0
      //   20: invokespecial 2280	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   23: astore_0
      //   24: aload_0
      //   25: invokevirtual 1255	java/io/File:exists	()Z
      //   28: ifne +5 -> 33
      //   31: aconst_null
      //   32: areturn
      //   33: new 1262	java/io/FileInputStream
      //   36: dup
      //   37: aload_0
      //   38: invokespecial 1265	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   41: astore_1
      //   42: aload_1
      //   43: astore_0
      //   44: aload_1
      //   45: invokestatic 2215	com/lbe/parallel/f$a:a	(Ljava/io/InputStream;)[B
      //   48: astore_2
      //   49: aload_1
      //   50: invokestatic 1576	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
      //   53: aload_2
      //   54: areturn
      //   55: astore_2
      //   56: aconst_null
      //   57: astore_1
      //   58: aload_1
      //   59: astore_0
      //   60: aload_2
      //   61: invokevirtual 422	java/lang/Exception:printStackTrace	()V
      //   64: aload_1
      //   65: ifnull +34 -> 99
      //   68: aload_1
      //   69: invokestatic 1576	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
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
      //   84: invokestatic 1576	com/lbe/parallel/f$a:a	(Ljava/io/Closeable;)V
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
    
    public static RequestQueue k()
    {
      if (i != null) {
        return i;
      }
      throw new IllegalStateException("RequestQueue not initialized");
    }
    
    public static String k(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      String str = paramParcel.readString();
      paramParcel.setDataPosition(paramInt + m);
      return str;
    }
    
    public static String k(String paramString)
    {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).update(paramString.getBytes());
        localObject = ((MessageDigest)localObject).digest();
        StringBuilder localStringBuilder = new StringBuilder();
        int m = 0;
        while (m < localObject.length)
        {
          String str = Integer.toHexString(localObject[m] & 0xFF);
          if (str.length() == 1) {
            localStringBuilder.append('0');
          }
          localStringBuilder.append(str);
          m += 1;
        }
        localObject = localStringBuilder.toString();
        return localObject;
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {}
      return String.valueOf(paramString.hashCode());
    }
    
    public static boolean k(Context paramContext)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      try
      {
        if (Build.VERSION.SDK_INT >= 23)
        {
          Intent localIntent = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS").setData(Uri.parse("package:" + paramContext.getPackageName()));
          paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
          bool1 = bool2;
          if (paramContext != null) {
            bool1 = true;
          }
        }
        return bool1;
      }
      catch (Exception paramContext) {}
      return false;
    }
    
    public static IBinder l(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      IBinder localIBinder = paramParcel.readStrongBinder();
      paramParcel.setDataPosition(paramInt + m);
      return localIBinder;
    }
    
    public static ImageLoader l()
    {
      if (j != null) {
        return j;
      }
      throw new IllegalStateException("ImageLoader not initialized");
    }
    
    private static String l(String paramString)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      int m = 0;
      if (m < paramString.length())
      {
        char c1 = paramString.charAt(m);
        if (m == 0) {
          localStringBuffer.append(Character.toLowerCase(c1));
        }
        for (;;)
        {
          m += 1;
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
    
    public static boolean l(Context paramContext)
    {
      boolean bool2 = false;
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        int m = paramContext.getIntExtra("plugged", 0);
        if ((m != 1) && (m != 2))
        {
          bool1 = bool2;
          if (m != 4) {}
        }
        else
        {
          bool1 = true;
        }
      }
      return bool1;
    }
    
    public static long m(Context paramContext)
    {
      long l1;
      try
      {
        l1 = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).firstInstallTime;
        if (l1 > 0L) {
          return l1;
        }
      }
      catch (Exception paramContext)
      {
        long l2;
        do
        {
          for (;;)
          {
            l1 = 0L;
          }
          l2 = ab.a().getLong("new_user_start_time", 0L);
          l1 = l2;
        } while (l2 > 0L);
        l1 = System.currentTimeMillis();
        ab.a().a("new_user_start_time", l1);
      }
      return l1;
    }
    
    public static Bundle m(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      Bundle localBundle = paramParcel.readBundle();
      paramParcel.setDataPosition(paramInt + m);
      return localBundle;
    }
    
    public static hl m()
    {
      if (k != null) {
        return k;
      }
      throw new IllegalStateException("ImageLoader not initialized");
    }
    
    private static String m(String paramString)
    {
      int n = paramString.length();
      StringBuilder localStringBuilder = new StringBuilder(n);
      int m = 0;
      if (m < n)
      {
        char c1 = paramString.charAt(m);
        if ((c1 >= ' ') && (c1 <= '~') && (c1 != '"') && (c1 != '\'')) {
          localStringBuilder.append(c1);
        }
        for (;;)
        {
          m += 1;
          break;
          localStringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c1) }));
        }
      }
      return localStringBuilder.toString();
    }
    
    private static Object n(Context paramContext)
      throws Exception
    {
      Class localClass = Class.forName("android.webkit.Network");
      if (localClass != null)
      {
        if ((localClass instanceof Class)) {}
        for (Object localObject = (Class)localClass;; localObject = localClass.getClass())
        {
          paramContext = ((Class)localObject).getMethod("getInstance", new Class[] { Context.class }).invoke(localClass, new Object[] { paramContext });
          if (paramContext == null) {
            break;
          }
          localObject = paramContext.getClass().getDeclaredField("mRequestQueue");
          ((Field)localObject).setAccessible(true);
          return ((Field)localObject).get(paramContext);
        }
      }
      return null;
    }
    
    private static String n(String paramString)
    {
      if (l == null)
      {
        Object localObject2 = DAApp.a().getResources();
        localObject1 = Arrays.asList(((Resources)localObject2).getStringArray(2131230734));
        localObject2 = Arrays.asList(((Resources)localObject2).getStringArray(2131230733));
        int n = ((List)localObject1).size();
        l = new ao(n);
        int m = 0;
        while (m < n)
        {
          l.put(((List)localObject1).get(m), ((List)localObject2).get(m));
          m += 1;
        }
      }
      Object localObject1 = (String)l.get(paramString);
      paramString = (String)localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1)) {
        paramString = "2,000,000";
      }
      return paramString;
    }
    
    public static void n()
    {
      if (k != null) {
        k.a();
      }
    }
    
    public static byte[] n(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      byte[] arrayOfByte = paramParcel.createByteArray();
      paramParcel.setDataPosition(paramInt + m);
      return arrayOfByte;
    }
    
    public static ig o()
    {
      ClientInfo localClientInfo = ClientInfo.get();
      ig localIg = new ig();
      localIg.e = localClientInfo.getChannel();
      localIg.b = localClientInfo.getPkgName();
      localIg.f = localClientInfo.getSignatureMD5();
      localIg.d = localClientInfo.getVersionCode();
      localIg.c = localClientInfo.getVersionName();
      localIg.g = localClientInfo.getInstallerPackageName();
      localIg.h = localClientInfo.getUserGroupId();
      return localIg;
    }
    
    private static iq o(Context paramContext)
    {
      paramContext = d(paramContext, "hot_apps_response.info");
      if (!c(paramContext)) {
        try
        {
          paramContext = (iq)fb.a(new iq(), paramContext);
          return paramContext;
        }
        catch (fa paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      return null;
    }
    
    private static String o(String paramString)
    {
      int m = -1;
      switch (paramString.hashCode())
      {
      }
      for (;;)
      {
        switch (m)
        {
        default: 
          return null;
          if (paramString.equals("com.facebook.katana"))
          {
            m = 0;
            continue;
            if (paramString.equals("com.whatsapp")) {
              m = 1;
            }
          }
          break;
        }
      }
      return "facebook_launch_count";
      return "whatapp_launch_count";
    }
    
    public static BigDecimal[] o(Parcel paramParcel, int paramInt)
    {
      int m = a(paramParcel, paramInt);
      int n = paramParcel.dataPosition();
      if (m == 0) {
        return null;
      }
      int i1 = paramParcel.readInt();
      BigDecimal[] arrayOfBigDecimal = new BigDecimal[i1];
      paramInt = 0;
      while (paramInt < i1)
      {
        byte[] arrayOfByte = paramParcel.createByteArray();
        int i2 = paramParcel.readInt();
        arrayOfBigDecimal[paramInt] = new BigDecimal(new BigInteger(arrayOfByte), i2);
        paramInt += 1;
      }
      paramParcel.setDataPosition(n + m);
      return arrayOfBigDecimal;
    }
    
    public static ih p()
    {
      DeviceInfo localDeviceInfo = DeviceInfo.get();
      ih localIh = new ih();
      localIh.b = localDeviceInfo.getAndroidId();
      localIh.c = localDeviceInfo.getDeviceCountry();
      localIh.e = localDeviceInfo.getNetworkCountry();
      localIh.f = localDeviceInfo.getConfigLanguage();
      localIh.h = localDeviceInfo.getModel();
      localIh.g = localDeviceInfo.getVendor();
      localIh.i = localDeviceInfo.getFingerprint();
      localIh.d = localDeviceInfo.getLocalLanguage();
      localIh.j = localDeviceInfo.getSdkInt();
      localIh.k = localDeviceInfo.getImei();
      localIh.l = kp.h();
      return localIh;
    }
    
    private static ip p(Context paramContext)
    {
      paramContext = d(paramContext, "hot_apps_request.info");
      if (!c(paramContext)) {
        try
        {
          paramContext = (ip)fb.a(new ip(), paramContext);
          return paramContext;
        }
        catch (fa paramContext)
        {
          paramContext.printStackTrace();
        }
      }
      return null;
    }
    
    public static String[] p(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      String[] arrayOfString = paramParcel.createStringArray();
      paramParcel.setDataPosition(paramInt + m);
      return arrayOfString;
    }
    
    public static AdDeviceInfo q()
    {
      DeviceInfo localDeviceInfo = DeviceInfo.get();
      AdDeviceInfo localAdDeviceInfo = new AdDeviceInfo();
      localAdDeviceInfo.setAndroidAdId(localDeviceInfo.getAndroidAdId());
      localAdDeviceInfo.setAndroidId(localDeviceInfo.getAndroidId());
      localAdDeviceInfo.setClientIP(localDeviceInfo.getClientIP());
      localAdDeviceInfo.setConfigLanguage(localDeviceInfo.getConfigLanguage());
      localAdDeviceInfo.setNetCarrier(localDeviceInfo.getNetCarrier());
      localAdDeviceInfo.setNetworkCountry(localDeviceInfo.getNetworkCountry());
      localAdDeviceInfo.setNetworkType(localDeviceInfo.getNetworkType());
      localAdDeviceInfo.setDeviceCountry(localDeviceInfo.getDeviceCountry());
      localAdDeviceInfo.setFingerprint(localDeviceInfo.getFingerprint());
      localAdDeviceInfo.setImei(localDeviceInfo.getImei());
      localAdDeviceInfo.setLatitude(localDeviceInfo.getLatitude());
      localAdDeviceInfo.setLongitude(localDeviceInfo.getLongitude());
      localAdDeviceInfo.setUserAgent(localDeviceInfo.getUserAgent());
      localAdDeviceInfo.setLocalLanguage(localDeviceInfo.getLocalLanguage());
      localAdDeviceInfo.setMac(localDeviceInfo.getMac());
      localAdDeviceInfo.setModel(localDeviceInfo.getModel());
      localAdDeviceInfo.setOsVersion(localDeviceInfo.getOsVersion());
      localAdDeviceInfo.setProduct(localDeviceInfo.getProduct());
      localAdDeviceInfo.setResolutionHeight(localDeviceInfo.getResolutionHeight());
      localAdDeviceInfo.setResolutionWidth(localDeviceInfo.getResolutionWidth());
      localAdDeviceInfo.setSdkInt(localDeviceInfo.getSdkInt());
      localAdDeviceInfo.setTimezoneId(localDeviceInfo.getTimezoneId());
      localAdDeviceInfo.setTimezoneOffset(localDeviceInfo.getTimezoneOffset());
      localAdDeviceInfo.setUserAgent(localDeviceInfo.getUserAgent());
      localAdDeviceInfo.setVendor(localDeviceInfo.getVendor());
      localAdDeviceInfo.setIsRTL(localDeviceInfo.getIsRTL());
      localAdDeviceInfo.setBtMac(localDeviceInfo.getBtMac());
      localAdDeviceInfo.setFbAid(localDeviceInfo.getFbAid());
      return localAdDeviceInfo;
    }
    
    public static ArrayList<String> q(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      ArrayList localArrayList = paramParcel.createStringArrayList();
      paramParcel.setDataPosition(paramInt + m);
      return localArrayList;
    }
    
    public static Parcel r(Parcel paramParcel, int paramInt)
    {
      paramInt = a(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      Parcel localParcel = Parcel.obtain();
      localParcel.appendFrom(paramParcel, m, paramInt);
      paramParcel.setDataPosition(paramInt + m);
      return localParcel;
    }
    
    public static Map<String, Integer> r()
    {
      int n = 0;
      try
      {
        Object localObject1 = ab.a().c("home_apps_ordered_list");
        if (TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject1 = null;
          if (localObject1 == null) {
            break label93;
          }
        }
        ao localAo;
        label93:
        for (int m = localObject1.length;; m = 0)
        {
          localAo = new ao(m);
          if ((localObject1 == null) || (localObject1.length <= 0)) {
            break label98;
          }
          int i1 = localObject1.length;
          m = n;
          while (m < i1)
          {
            localAo.put(localObject1[m], Integer.valueOf(m));
            m += 1;
          }
          localObject1 = ((String)localObject1).split(";");
          break;
        }
        label98:
        return localAo;
      }
      finally {}
    }
    
    public static List<InstalledPsThemes> s()
    {
      ArrayList localArrayList = new ArrayList();
      fq localFq = new fq(DAApp.a());
      try
      {
        List localList = localFq.getInstalledPackages(128);
        if ((localList == null) || (localList.size() <= 0)) {
          return localArrayList;
        }
      }
      catch (Exception localException1)
      {
        InstalledPsThemes localInstalledPsThemes;
        for (;;)
        {
          localException1.printStackTrace();
          localInstalledPsThemes = null;
        }
        Iterator localIterator = localInstalledPsThemes.iterator();
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          for (;;)
          {
            try
            {
              if (localPackageInfo.applicationInfo != null) {
                break label183;
              }
              localInstalledPsThemes = null;
              if ((localInstalledPsThemes == null) || (localInstalledPsThemes.getInt("ps_theme") != 1)) {
                break;
              }
              localInstalledPsThemes = new InstalledPsThemes();
              localInstalledPsThemes.setLabel(String.valueOf(localFq.getApplicationLabel(localPackageInfo.applicationInfo)));
              localInstalledPsThemes.setPackageName(localPackageInfo.packageName);
              localInstalledPsThemes.setVersionCode(localPackageInfo.versionCode);
              localInstalledPsThemes.setVersionName(localPackageInfo.versionName);
              localInstalledPsThemes.setFileMd5(com.lbe.parallel.utility.af.b(localPackageInfo));
              localArrayList.add(localInstalledPsThemes);
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
            break;
            label183:
            Bundle localBundle = localPackageInfo.applicationInfo.metaData;
          }
        }
      }
      return localArrayList;
    }
    
    public static Parcel[] s(Parcel paramParcel, int paramInt)
    {
      int m = a(paramParcel, paramInt);
      int n = paramParcel.dataPosition();
      if (m == 0) {
        return null;
      }
      int i1 = paramParcel.readInt();
      Parcel[] arrayOfParcel = new Parcel[i1];
      paramInt = 0;
      if (paramInt < i1)
      {
        int i2 = paramParcel.readInt();
        if (i2 != 0)
        {
          int i3 = paramParcel.dataPosition();
          Parcel localParcel = Parcel.obtain();
          localParcel.appendFrom(paramParcel, i3, i2);
          arrayOfParcel[paramInt] = localParcel;
          paramParcel.setDataPosition(i2 + i3);
        }
        for (;;)
        {
          paramInt += 1;
          break;
          arrayOfParcel[paramInt] = null;
        }
      }
      paramParcel.setDataPosition(n + m);
      return arrayOfParcel;
    }
    
    public static ArrayList<String> t()
    {
      localObject1 = null;
      try
      {
        localObject2 = DAApp.a().getPackageManager().getInstalledApplications(128);
        localObject1 = localObject2;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          Object localObject2;
          localException1.printStackTrace();
        }
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject1).next();
          try
          {
            Bundle localBundle = localApplicationInfo.metaData;
            if ((localBundle != null) && (localBundle.getInt("ps_theme") == 1)) {
              localException1.add(localApplicationInfo.packageName);
            }
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
          }
        }
        return localException1;
      }
      localObject2 = new ArrayList();
      if ((localObject1 == null) || (((List)localObject1).size() == 0)) {
        return localObject2;
      }
    }
    
    public static void t(Parcel paramParcel, int paramInt)
    {
      G(paramParcel, paramInt);
    }
    
    public static int u(Parcel paramParcel, int paramInt)
    {
      d(paramParcel, paramInt, 4);
      return paramParcel.readInt();
    }
    
    public static List<String> u()
    {
      ArrayList localArrayList = new ArrayList();
      List localList = com.lbe.parallel.skin.d.a().c();
      if ((localList == null) || (localList.size() <= 0)) {
        return localArrayList;
      }
      int m = 0;
      while (m < localList.size())
      {
        PackageInfo localPackageInfo = ((SkinPackage)localList.get(m)).a;
        if (localPackageInfo != null) {
          localArrayList.add(localPackageInfo.packageName);
        }
        m += 1;
      }
      return localArrayList;
    }
    
    public static boolean v(Parcel paramParcel, int paramInt)
    {
      d(paramParcel, paramInt, 4);
      return paramParcel.readInt() != 0;
    }
    
    public static long[] v()
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
    
    public static long w(Parcel paramParcel, int paramInt)
    {
      d(paramParcel, paramInt, 8);
      return paramParcel.readLong();
    }
    
    public static boolean w()
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
    
    public static float x(Parcel paramParcel, int paramInt)
    {
      d(paramParcel, paramInt, 4);
      return paramParcel.readFloat();
    }
    
    public static Method[] x()
    {
      ArrayList localArrayList = new ArrayList();
      Method[] arrayOfMethod = AssetManager.class.getDeclaredMethods();
      int n = arrayOfMethod.length;
      int m = 0;
      while (m < n)
      {
        Method localMethod = arrayOfMethod[m];
        if (Modifier.isNative(localMethod.getModifiers())) {
          localArrayList.add(localMethod);
        }
        m += 1;
      }
      return (Method[])localArrayList.toArray(new Method[localArrayList.size()]);
    }
    
    public static double y(Parcel paramParcel, int paramInt)
    {
      d(paramParcel, paramInt, 8);
      return paramParcel.readDouble();
    }
    
    public static long y()
    {
      if (com.lbe.parallel.utility.af.f()) {
        return TimeUnit.MINUTES.toMillis(5L);
      }
      return TimeUnit.HOURS.toMillis(4L);
    }
    
    private static Resources z()
    {
      return com.lbe.parallel.skin.d.a().b().c();
    }
    
    public static String z(Parcel paramParcel, int paramInt)
    {
      paramInt = H(paramParcel, paramInt);
      int m = paramParcel.dataPosition();
      if (paramInt == 0) {
        return null;
      }
      String str = paramParcel.readString();
      paramParcel.setDataPosition(paramInt + m);
      return str;
    }
  }
}
