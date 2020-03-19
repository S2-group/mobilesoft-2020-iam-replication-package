package e.w;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.Toast;
import com.ew.sdk.task.util.processutil.AndroidAppProcess;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public class wj
{
  private static String a = "Task_Tools";
  private static si b;
  
  public static long a(wi.a paramA)
  {
    long l2 = 0L;
    String str = "";
    if (paramA == wi.a.DATE) {
      str = "yyyyMMdd";
    }
    for (;;)
    {
      long l1 = l2;
      try
      {
        if (!TextUtils.isEmpty(str))
        {
          paramA = new Date();
          paramA = new SimpleDateFormat(str).format(paramA);
          l1 = l2;
          if (!TextUtils.isEmpty(paramA)) {
            l1 = i(paramA);
          }
        }
        return l1;
      }
      catch (Exception paramA)
      {
        sb.c(a + " getNowTime is error:" + paramA.getMessage());
      }
      if (paramA == wi.a.HOURS) {
        str = "yyyyMMddHH";
      } else if (paramA == wi.a.MINUTES) {
        str = "yyyyMMddHHmm";
      } else if (paramA == wi.a.SECONDS) {
        str = "yyyyMMddHHmmss";
      }
    }
    return 0L;
  }
  
  public static si a()
  {
    if (b == null) {
      b = new si();
    }
    return b;
  }
  
  public static String a(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
    }
    try
    {
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf('.');
        str = paramString;
        if (i > -1)
        {
          str = paramString;
          if (i < paramString.length()) {
            str = paramString.substring(0, i);
          }
        }
      }
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    return b(paramString1, paramString2);
  }
  
  public static String a(String paramString1, String paramString2, int paramInt)
  {
    str = "";
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return paramString1;
      }
      StringBuffer localStringBuffer = new StringBuffer(paramString1);
      paramString1 = str;
      if (localStringBuffer != null) {
        paramString1 = localStringBuffer.insert(paramInt, paramString2).toString();
      }
    }
    catch (NullPointerException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
        paramString1 = str;
      }
    }
    return paramString1;
  }
  
  /* Error */
  public static String a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 131	e/w/wj:e	(Ljava/lang/String;)Ljava/lang/String;
    //   4: astore_2
    //   5: aload_2
    //   6: astore_3
    //   7: aload_0
    //   8: invokestatic 34	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   11: ifeq +149 -> 160
    //   14: getstatic 136	e/w/rm:b	Le/w/sv;
    //   17: ldc -118
    //   19: invokevirtual 142	e/w/sv:c	(Ljava/lang/String;)Ljava/lang/String;
    //   22: astore_0
    //   23: new 69	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   30: getstatic 14	e/w/wj:a	Ljava/lang/String;
    //   33: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: ldc -112
    //   38: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_0
    //   42: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokestatic 146	e/w/sb:b	(Ljava/lang/String;)V
    //   51: iload_1
    //   52: ifeq +69 -> 121
    //   55: new 69	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   62: aload_0
    //   63: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: getstatic 151	java/io/File:separator	Ljava/lang/String;
    //   69: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: getstatic 156	e/w/wi:m	Ljava/lang/String;
    //   75: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: astore_0
    //   82: new 69	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   89: ldc -98
    //   91: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: getstatic 161	e/w/wi:o	Ljava/lang/String;
    //   97: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: getstatic 151	java/io/File:separator	Ljava/lang/String;
    //   103: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload_0
    //   107: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: ldc -93
    //   112: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   118: astore_2
    //   119: aload_2
    //   120: areturn
    //   121: new 69	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   128: aload_0
    //   129: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: getstatic 151	java/io/File:separator	Ljava/lang/String;
    //   135: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: getstatic 166	e/w/wi:l	Ljava/lang/String;
    //   141: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: astore_0
    //   148: goto -66 -> 82
    //   151: astore_3
    //   152: aload_2
    //   153: astore_0
    //   154: aload_3
    //   155: invokevirtual 127	java/lang/NullPointerException:printStackTrace	()V
    //   158: aload_0
    //   159: astore_3
    //   160: new 69	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   167: ldc -98
    //   169: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: getstatic 161	e/w/wi:o	Ljava/lang/String;
    //   175: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: getstatic 151	java/io/File:separator	Ljava/lang/String;
    //   181: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: aload_3
    //   185: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: getstatic 151	java/io/File:separator	Ljava/lang/String;
    //   191: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: aload_3
    //   195: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: ldc -93
    //   200: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: areturn
    //   207: astore_3
    //   208: goto -54 -> 154
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	211	0	paramString	String
    //   0	211	1	paramBoolean	boolean
    //   4	149	2	str1	String
    //   6	1	3	str2	String
    //   151	4	3	localNullPointerException1	NullPointerException
    //   159	36	3	str3	String
    //   207	1	3	localNullPointerException2	NullPointerException
    // Exception table:
    //   from	to	target	type
    //   7	51	151	java/lang/NullPointerException
    //   55	82	151	java/lang/NullPointerException
    //   121	148	151	java/lang/NullPointerException
    //   82	119	207	java/lang/NullPointerException
  }
  
  public static List<String> a(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    paramJSONObject = paramJSONObject.keys();
    if (paramJSONObject == null) {
      return localArrayList;
    }
    while (paramJSONObject.hasNext())
    {
      String str = (String)paramJSONObject.next();
      if (!TextUtils.isEmpty(str)) {
        localArrayList.add(str);
      }
    }
    return localArrayList;
  }
  
  public static Map<String, Integer> a(Context paramContext)
  {
    Object localObject1 = new HashMap();
    if (paramContext == null) {
      return localObject1;
    }
    for (;;)
    {
      int i;
      int j;
      try
      {
        localPackageManager = paramContext.getPackageManager();
        if (localPackageManager == null) {
          break;
        }
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        if ((paramContext == null) || (paramContext.size() <= 0)) {
          break;
        }
        localIterator = paramContext.iterator();
        paramContext = (Context)localObject1;
      }
      catch (Exception paramContext)
      {
        try
        {
          PackageManager localPackageManager;
          Iterator localIterator;
          if (localIterator.hasNext())
          {
            Object localObject2 = (PackageInfo)localIterator.next();
            paramContext = (Context)localObject1;
            if (localObject2 == null) {
              continue;
            }
            localObject2 = ((PackageInfo)localObject2).applicationInfo;
            paramContext = (Context)localObject1;
            if (localObject2 == null) {
              continue;
            }
            Object localObject3 = ((ApplicationInfo)localObject2).loadLabel(localPackageManager);
            paramContext = (Context)localObject1;
            if (TextUtils.isEmpty((CharSequence)localObject3)) {
              continue;
            }
            localObject3 = ((CharSequence)localObject3).toString().toLowerCase();
            if ((((ApplicationInfo)localObject2).flags & 0x1) != 0)
            {
              paramContext = wi.d.d;
              int k = 1;
              i = 0;
              j = k;
              if (i >= paramContext.length) {
                break label264;
              }
              if (!((String)localObject3).contains(paramContext[i])) {
                break label274;
              }
              j = 0;
              break label264;
            }
            if (((String)localObject3).indexOf(" ") > -1)
            {
              paramContext = a((Map)localObject1, (String)localObject3, " ");
              continue;
            }
            if (((String)localObject3).contains("&"))
            {
              paramContext = a((Map)localObject1, (String)localObject3, "&");
              continue;
            }
            paramContext = b((Map)localObject1, (String)localObject3);
            continue;
          }
          return localObject1;
        }
        catch (Exception paramContext)
        {
          continue;
          paramContext = (Context)localObject1;
        }
        paramContext = paramContext;
        sb.b(paramContext.getMessage());
        paramContext.printStackTrace();
        return localObject1;
      }
      localObject1 = paramContext;
      label264:
      if (j == 0)
      {
        continue;
        label274:
        i += 1;
      }
    }
  }
  
  public static Map<String, Integer> a(Map<String, Integer> paramMap, String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return paramMap;
      }
      paramString = paramString.split(":");
      if ((paramString != null) && (paramString.length == 2))
      {
        paramMap.put(paramString[0], Integer.valueOf(Integer.parseInt(paramString[1])));
        return paramMap;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return paramMap;
  }
  
  /* Error */
  private static Map<String, Integer> a(Map<String, Integer> paramMap, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_1
    //   1: aload_2
    //   2: invokevirtual 268	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   5: astore_2
    //   6: aload_2
    //   7: ifnull +109 -> 116
    //   10: aload_2
    //   11: arraylength
    //   12: ifgt +5 -> 17
    //   15: aload_0
    //   16: areturn
    //   17: aload_2
    //   18: arraylength
    //   19: istore 4
    //   21: iconst_0
    //   22: istore_3
    //   23: aload_0
    //   24: astore_1
    //   25: iload_3
    //   26: iload 4
    //   28: if_icmpge +82 -> 110
    //   31: aload_2
    //   32: iload_3
    //   33: aaload
    //   34: astore 5
    //   36: aload 5
    //   38: invokestatic 34	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   41: ifeq +8 -> 49
    //   44: aload_0
    //   45: astore_1
    //   46: goto +72 -> 118
    //   49: aload_0
    //   50: astore_1
    //   51: aload 5
    //   53: ldc_w 258
    //   56: invokevirtual 287	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   59: ifne +59 -> 118
    //   62: aload 5
    //   64: ldc_w 258
    //   67: invokevirtual 247	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   70: ifeq +16 -> 86
    //   73: aload_0
    //   74: aload 5
    //   76: ldc_w 258
    //   79: invokestatic 256	e/w/wj:a	(Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Map;
    //   82: astore_1
    //   83: goto +35 -> 118
    //   86: aload_0
    //   87: aload 5
    //   89: invokestatic 261	e/w/wj:b	(Ljava/util/Map;Ljava/lang/String;)Ljava/util/Map;
    //   92: astore_1
    //   93: goto +25 -> 118
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual 80	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   101: invokestatic 146	e/w/sb:b	(Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokevirtual 112	java/lang/Exception:printStackTrace	()V
    //   108: aload_0
    //   109: astore_1
    //   110: aload_1
    //   111: areturn
    //   112: astore_1
    //   113: goto -16 -> 97
    //   116: aload_0
    //   117: areturn
    //   118: iload_3
    //   119: iconst_1
    //   120: iadd
    //   121: istore_3
    //   122: aload_1
    //   123: astore_0
    //   124: goto -101 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	127	0	paramMap	Map<String, Integer>
    //   0	127	1	paramString1	String
    //   0	127	2	paramString2	String
    //   22	100	3	i	int
    //   19	10	4	j	int
    //   34	54	5	localCharSequence	CharSequence
    // Exception table:
    //   from	to	target	type
    //   0	6	96	java/lang/Exception
    //   10	15	96	java/lang/Exception
    //   17	21	96	java/lang/Exception
    //   36	44	112	java/lang/Exception
    //   51	83	112	java/lang/Exception
    //   86	93	112	java/lang/Exception
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    try
    {
      sb.b(a + " Fantasy copy:" + paramString);
      ((ClipboardManager)paramActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("copy", paramString));
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
  }
  
  public static void a(Context paramContext, Bitmap paramBitmap)
  {
    try
    {
      b(paramContext, paramBitmap);
      paramBitmap = "保存成功！";
      return;
    }
    catch (Exception paramBitmap)
    {
      for (;;)
      {
        try
        {
          paramContext = Toast.makeText(paramContext, paramBitmap, 1);
          paramContext.setGravity(17, 0, 0);
          paramContext.show();
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
        paramBitmap = paramBitmap;
        paramBitmap.printStackTrace();
        paramBitmap = "保存失败！";
      }
    }
  }
  
  public static void a(File paramFile)
  {
    if (paramFile != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!paramFile.exists()) {
          break label83;
        }
        if (!paramFile.isDirectory()) {
          return;
        }
        File[] arrayOfFile = paramFile.listFiles();
        int j = arrayOfFile.length;
        i = 0;
        if (i < j)
        {
          File localFile = arrayOfFile[i];
          if (localFile.isFile()) {
            localFile.delete();
          } else if (localFile.isDirectory()) {
            a(localFile);
          }
        }
      }
      catch (Exception paramFile)
      {
        paramFile.printStackTrace();
        return;
      }
      paramFile.delete();
      label83:
      return;
      i += 1;
    }
  }
  
  /* Error */
  public static void a(Object paramObject, String paramString)
  {
    // Byte code:
    //   0: new 357	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 358	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 4
    //   9: new 360	java/io/ObjectOutputStream
    //   12: dup
    //   13: aload 4
    //   15: invokespecial 363	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   18: astore_3
    //   19: aload_3
    //   20: astore_2
    //   21: aload_3
    //   22: aload_0
    //   23: invokevirtual 367	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   26: aload_3
    //   27: astore_2
    //   28: new 97	java/lang/String
    //   31: dup
    //   32: aload 4
    //   34: invokevirtual 371	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   37: iconst_0
    //   38: invokestatic 377	android/util/Base64:encode	([BI)[B
    //   41: invokespecial 380	java/lang/String:<init>	([B)V
    //   44: astore_0
    //   45: aload_3
    //   46: astore_2
    //   47: getstatic 136	e/w/rm:b	Le/w/sv;
    //   50: aload_1
    //   51: aload_0
    //   52: invokevirtual 383	e/w/sv:a	(Ljava/lang/String;Ljava/lang/String;)Le/w/sv;
    //   55: pop
    //   56: aload_3
    //   57: ifnull +7 -> 64
    //   60: aload_3
    //   61: invokevirtual 386	java/io/ObjectOutputStream:close	()V
    //   64: aload 4
    //   66: ifnull +8 -> 74
    //   69: aload 4
    //   71: invokevirtual 387	java/io/ByteArrayOutputStream:close	()V
    //   74: return
    //   75: astore_0
    //   76: aload_0
    //   77: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   80: return
    //   81: astore_1
    //   82: aconst_null
    //   83: astore_0
    //   84: aload_0
    //   85: astore_2
    //   86: aload_1
    //   87: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   90: aload_0
    //   91: astore_2
    //   92: new 69	java/lang/StringBuilder
    //   95: dup
    //   96: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   99: getstatic 14	e/w/wj:a	Ljava/lang/String;
    //   102: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: ldc_w 390
    //   108: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: aload_1
    //   112: invokevirtual 391	java/io/IOException:getMessage	()Ljava/lang/String;
    //   115: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   121: invokestatic 88	e/w/sb:c	(Ljava/lang/String;)V
    //   124: aload_0
    //   125: ifnull +7 -> 132
    //   128: aload_0
    //   129: invokevirtual 386	java/io/ObjectOutputStream:close	()V
    //   132: aload 4
    //   134: ifnull -60 -> 74
    //   137: aload 4
    //   139: invokevirtual 387	java/io/ByteArrayOutputStream:close	()V
    //   142: return
    //   143: astore_0
    //   144: aload_0
    //   145: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   148: return
    //   149: astore_0
    //   150: aconst_null
    //   151: astore_2
    //   152: aload_2
    //   153: ifnull +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 386	java/io/ObjectOutputStream:close	()V
    //   160: aload 4
    //   162: ifnull +8 -> 170
    //   165: aload 4
    //   167: invokevirtual 387	java/io/ByteArrayOutputStream:close	()V
    //   170: aload_0
    //   171: athrow
    //   172: astore_1
    //   173: aload_1
    //   174: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   177: goto -7 -> 170
    //   180: astore_0
    //   181: goto -29 -> 152
    //   184: astore_1
    //   185: aload_3
    //   186: astore_0
    //   187: goto -103 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	190	0	paramObject	Object
    //   0	190	1	paramString	String
    //   20	137	2	localObject	Object
    //   18	168	3	localObjectOutputStream	java.io.ObjectOutputStream
    //   7	159	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   60	64	75	java/io/IOException
    //   69	74	75	java/io/IOException
    //   9	19	81	java/io/IOException
    //   128	132	143	java/io/IOException
    //   137	142	143	java/io/IOException
    //   9	19	149	finally
    //   156	160	172	java/io/IOException
    //   165	170	172	java/io/IOException
    //   21	26	180	finally
    //   28	45	180	finally
    //   47	56	180	finally
    //   86	90	180	finally
    //   92	124	180	finally
    //   21	26	184	java/io/IOException
    //   28	45	184	java/io/IOException
    //   47	56	184	java/io/IOException
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return sw.c(paramString);
  }
  
  public static boolean a(uo paramUo)
  {
    boolean bool = false;
    try
    {
      long l2 = System.currentTimeMillis() / 1000L;
      long l1 = paramUo.getCurTaskBranch().getExprienceTime();
      l2 -= paramUo.getTaskStartTime();
      sb.b(a + " 已经体验" + String.valueOf(l2) + "秒");
      if (l2 >= l1) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramUo)
    {
      paramUo.printStackTrace();
    }
    return false;
  }
  
  public static String b()
  {
    Object localObject2 = "";
    try
    {
      localObject1 = Locale.getDefault().getLanguage().toLowerCase().trim();
      localObject2 = localObject1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject1;
        String str;
        localException.printStackTrace();
      }
    }
    localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = "en";
    }
    localObject2 = localObject1;
    if (((String)localObject1).equals("zh"))
    {
      str = sc.a().toLowerCase();
      if (!str.equals("tw"))
      {
        localObject2 = localObject1;
        if (!str.equals("hk")) {}
      }
      else
      {
        localObject2 = "tw";
      }
    }
    return localObject2;
  }
  
  public static String b(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.addCategory("android.intent.category.BROWSABLE");
      localIntent.setDataAndType(Uri.parse("http://"), null);
      paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 32);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = ((ResolveInfo)paramContext.get(0)).activityInfo.packageName;
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String b(String paramString)
  {
    String str = paramString;
    if (paramString != null) {
      str = paramString;
    }
    try
    {
      if (paramString.length() > 0)
      {
        int i = paramString.lastIndexOf('.');
        str = paramString;
        if (i > -1)
        {
          str = paramString;
          if (i < paramString.length() - 1) {
            str = paramString.substring(i + 1);
          }
        }
      }
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return paramString;
  }
  
  public static String b(String paramString1, String paramString2)
  {
    return "http://" + paramString1 + "/res/1/" + paramString2;
  }
  
  private static Map<String, Integer> b(Map<String, Integer> paramMap, String paramString)
  {
    try
    {
      paramString = paramString.toLowerCase();
      if (paramMap.containsKey(paramString))
      {
        paramMap.put(paramString, Integer.valueOf(((Integer)paramMap.get(paramString)).intValue() + 1));
        return paramMap;
      }
      paramMap.put(paramString, Integer.valueOf(1));
      return paramMap;
    }
    catch (Exception paramString)
    {
      sb.b(paramString.getMessage());
      paramString.printStackTrace();
    }
    return paramMap;
  }
  
  public static void b(Context paramContext, Bitmap paramBitmap)
  {
    try
    {
      File localFile = new File(Environment.getExternalStorageDirectory(), "webCacheImg");
      if (!localFile.exists()) {
        localFile.mkdir();
      }
      String str = System.currentTimeMillis() + ".jpg";
      localFile = new File(localFile, str);
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      MediaStore.Images.Media.insertImage(paramContext.getContentResolver(), localFile.getAbsolutePath(), str, null);
      paramBitmap = c(paramContext) + File.separator + "webCacheImg" + File.separator + str;
      paramContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + paramBitmap)));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void b(uo paramUo)
  {
    try
    {
      paramUo.setTaskStartTime(System.currentTimeMillis() / 1000L);
      ut.a(paramUo);
      rm.b.a("taskIdKey", paramUo.getId());
      return;
    }
    catch (Exception paramUo)
    {
      paramUo.printStackTrace();
    }
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    int k;
    int i;
    try
    {
      k = paramString.equals("");
      if (paramString != null) {
        break label96;
      }
      i = 1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    for (;;)
    {
      if (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.processName.equals(paramString))
        {
          i = localRunningAppProcessInfo.importance;
          if (i == 100) {
            return true;
          }
        }
      }
      else
      {
        return false;
      }
    }
    while ((i | k) != 0)
    {
      return false;
      label96:
      int j = 0;
    }
  }
  
  public static boolean b(String paramString, boolean paramBoolean)
  {
    try
    {
      String str = e(paramString);
      if (TextUtils.isEmpty(paramString))
      {
        paramString = rm.b.c("oldTemplateName");
        sb.b(a + " default template name:" + paramString);
        if (paramBoolean) {}
        for (paramString = paramString + File.separator + wi.m;; paramString = paramString + File.separator + wi.l)
        {
          paramString = wi.o + File.separator + paramString + ".html";
          sb.b(a + " template path1:" + paramString);
          return new File(paramString).exists();
        }
      }
      paramString = wi.o + File.separator + str + File.separator + str + ".html";
      sb.b(a + " template path2:" + paramString);
      paramBoolean = new File(paramString).exists();
      if (paramBoolean) {
        return true;
      }
    }
    catch (NullPointerException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static String c()
  {
    String str1 = null;
    String str2 = b();
    String[] arrayOfString = new String[10];
    arrayOfString[0] = "Start_en";
    arrayOfString[1] = "Anfang_de";
    arrayOfString[2] = "comienzo_es";
    arrayOfString[3] = "Démarrer_fr";
    arrayOfString[4] = "Mulai_in";
    arrayOfString[5] = "開始_ja";
    arrayOfString[6] = "스타트_ko";
    arrayOfString[7] = "Começar_pt";
    arrayOfString[8] = "Начало_ru";
    arrayOfString[9] = "开始_zh";
    int i = 0;
    while (i < arrayOfString.length)
    {
      Object localObject = arrayOfString[i];
      if (((String)localObject).contains(str2))
      {
        localObject = ((String)localObject).split("_");
        int j = 0;
        while (j < localObject.length)
        {
          str1 = localObject[j];
          if (!str1.equals(str2)) {
            return str1;
          }
          j += 1;
        }
      }
      i += 1;
    }
    return str1;
  }
  
  public static String c(Context paramContext)
  {
    for (;;)
    {
      try
      {
        boolean bool = Environment.isExternalStorageRemovable();
        if (("mounted".equals(Environment.getExternalStorageState())) && (!bool)) {
          paramContext = Environment.getExternalStorageDirectory().getAbsolutePath();
        }
      }
      catch (Exception localException1)
      {
        paramContext = "";
      }
      try
      {
        sb.c(a + " public dir path:" + paramContext);
        return paramContext;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      paramContext = Environment.getExternalStorageDirectory().getPath();
    }
    localException1.printStackTrace();
    return paramContext;
  }
  
  public static String c(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.indexOf("http://") != -1) || (paramString.indexOf("https://") != -1)) {
      return paramString;
    }
    return a("gop1.co", paramString);
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    if (Build.VERSION.SDK_INT >= 21) {
      return d(paramContext, paramString);
    }
    return b(paramContext, paramString);
  }
  
  /* Error */
  public static Object d(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: getstatic 136	e/w/rm:b	Le/w/sv;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +39 -> 47
    //   11: iconst_0
    //   12: ifeq +11 -> 23
    //   15: new 118	java/lang/NullPointerException
    //   18: dup
    //   19: invokespecial 667	java/lang/NullPointerException:<init>	()V
    //   22: athrow
    //   23: aload 6
    //   25: astore_0
    //   26: iconst_0
    //   27: ifeq +11 -> 38
    //   30: new 118	java/lang/NullPointerException
    //   33: dup
    //   34: invokespecial 667	java/lang/NullPointerException:<init>	()V
    //   37: athrow
    //   38: aload_0
    //   39: areturn
    //   40: astore_0
    //   41: aload_0
    //   42: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   45: aconst_null
    //   46: areturn
    //   47: getstatic 136	e/w/rm:b	Le/w/sv;
    //   50: aload_0
    //   51: invokevirtual 142	e/w/sv:c	(Ljava/lang/String;)Ljava/lang/String;
    //   54: astore_0
    //   55: aload_0
    //   56: invokestatic 34	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   59: istore_1
    //   60: iload_1
    //   61: ifeq +37 -> 98
    //   64: iconst_0
    //   65: ifeq +11 -> 76
    //   68: new 118	java/lang/NullPointerException
    //   71: dup
    //   72: invokespecial 667	java/lang/NullPointerException:<init>	()V
    //   75: athrow
    //   76: aload 6
    //   78: astore_0
    //   79: iconst_0
    //   80: ifeq -42 -> 38
    //   83: new 118	java/lang/NullPointerException
    //   86: dup
    //   87: invokespecial 667	java/lang/NullPointerException:<init>	()V
    //   90: athrow
    //   91: astore_0
    //   92: aload_0
    //   93: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   96: aconst_null
    //   97: areturn
    //   98: new 669	java/io/ByteArrayInputStream
    //   101: dup
    //   102: aload_0
    //   103: invokevirtual 672	java/lang/String:getBytes	()[B
    //   106: iconst_0
    //   107: invokestatic 675	android/util/Base64:decode	([BI)[B
    //   110: invokespecial 676	java/io/ByteArrayInputStream:<init>	([B)V
    //   113: astore_0
    //   114: new 678	java/io/ObjectInputStream
    //   117: dup
    //   118: aload_0
    //   119: invokespecial 681	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   122: astore 4
    //   124: aload 4
    //   126: astore_3
    //   127: aload_0
    //   128: astore_2
    //   129: aload 4
    //   131: invokevirtual 684	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   134: astore 5
    //   136: aload 5
    //   138: astore_2
    //   139: aload_0
    //   140: ifnull +7 -> 147
    //   143: aload_0
    //   144: invokevirtual 685	java/io/ByteArrayInputStream:close	()V
    //   147: aload_2
    //   148: astore_0
    //   149: aload 4
    //   151: ifnull -113 -> 38
    //   154: aload 4
    //   156: invokevirtual 686	java/io/ObjectInputStream:close	()V
    //   159: aload_2
    //   160: areturn
    //   161: astore_0
    //   162: aload_0
    //   163: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   166: aload_2
    //   167: areturn
    //   168: astore 5
    //   170: aconst_null
    //   171: astore 4
    //   173: aconst_null
    //   174: astore_0
    //   175: aload 4
    //   177: astore_3
    //   178: aload_0
    //   179: astore_2
    //   180: aload 5
    //   182: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   185: aload 4
    //   187: astore_3
    //   188: aload_0
    //   189: astore_2
    //   190: new 69	java/lang/StringBuilder
    //   193: dup
    //   194: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   197: getstatic 14	e/w/wj:a	Ljava/lang/String;
    //   200: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: ldc_w 688
    //   206: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   209: aload 5
    //   211: invokevirtual 391	java/io/IOException:getMessage	()Ljava/lang/String;
    //   214: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokestatic 88	e/w/sb:c	(Ljava/lang/String;)V
    //   223: aload_0
    //   224: ifnull +7 -> 231
    //   227: aload_0
    //   228: invokevirtual 685	java/io/ByteArrayInputStream:close	()V
    //   231: aload 6
    //   233: astore_0
    //   234: aload 4
    //   236: ifnull -198 -> 38
    //   239: aload 4
    //   241: invokevirtual 686	java/io/ObjectInputStream:close	()V
    //   244: aconst_null
    //   245: areturn
    //   246: astore_0
    //   247: aload_0
    //   248: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   251: aconst_null
    //   252: areturn
    //   253: astore 5
    //   255: aconst_null
    //   256: astore 4
    //   258: aconst_null
    //   259: astore_0
    //   260: aload 4
    //   262: astore_3
    //   263: aload_0
    //   264: astore_2
    //   265: aload 5
    //   267: invokevirtual 689	java/lang/ClassNotFoundException:printStackTrace	()V
    //   270: aload_0
    //   271: ifnull +7 -> 278
    //   274: aload_0
    //   275: invokevirtual 685	java/io/ByteArrayInputStream:close	()V
    //   278: aload 6
    //   280: astore_0
    //   281: aload 4
    //   283: ifnull -245 -> 38
    //   286: aload 4
    //   288: invokevirtual 686	java/io/ObjectInputStream:close	()V
    //   291: aconst_null
    //   292: areturn
    //   293: astore_0
    //   294: aload_0
    //   295: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   298: aconst_null
    //   299: areturn
    //   300: astore 4
    //   302: aconst_null
    //   303: astore_3
    //   304: aconst_null
    //   305: astore_0
    //   306: aload_0
    //   307: ifnull +7 -> 314
    //   310: aload_0
    //   311: invokevirtual 685	java/io/ByteArrayInputStream:close	()V
    //   314: aload_3
    //   315: ifnull +7 -> 322
    //   318: aload_3
    //   319: invokevirtual 686	java/io/ObjectInputStream:close	()V
    //   322: aload 4
    //   324: athrow
    //   325: astore_0
    //   326: aload_0
    //   327: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   330: goto -8 -> 322
    //   333: astore 4
    //   335: aconst_null
    //   336: astore_3
    //   337: goto -31 -> 306
    //   340: astore 4
    //   342: aload_2
    //   343: astore_0
    //   344: goto -38 -> 306
    //   347: astore 5
    //   349: aconst_null
    //   350: astore 4
    //   352: goto -92 -> 260
    //   355: astore 5
    //   357: goto -97 -> 260
    //   360: astore 5
    //   362: aconst_null
    //   363: astore 4
    //   365: goto -190 -> 175
    //   368: astore 5
    //   370: goto -195 -> 175
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	373	0	paramString	String
    //   59	2	1	bool	boolean
    //   6	337	2	localObject1	Object
    //   126	211	3	localObjectInputStream1	java.io.ObjectInputStream
    //   122	165	4	localObjectInputStream2	java.io.ObjectInputStream
    //   300	23	4	localObject2	Object
    //   333	1	4	localObject3	Object
    //   340	1	4	localObject4	Object
    //   350	14	4	localObject5	Object
    //   134	3	5	localObject6	Object
    //   168	42	5	localIOException1	java.io.IOException
    //   253	13	5	localClassNotFoundException1	ClassNotFoundException
    //   347	1	5	localClassNotFoundException2	ClassNotFoundException
    //   355	1	5	localClassNotFoundException3	ClassNotFoundException
    //   360	1	5	localIOException2	java.io.IOException
    //   368	1	5	localIOException3	java.io.IOException
    //   1	278	6	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   15	23	40	java/io/IOException
    //   30	38	40	java/io/IOException
    //   68	76	91	java/io/IOException
    //   83	91	91	java/io/IOException
    //   143	147	161	java/io/IOException
    //   154	159	161	java/io/IOException
    //   3	7	168	java/io/IOException
    //   47	60	168	java/io/IOException
    //   98	114	168	java/io/IOException
    //   227	231	246	java/io/IOException
    //   239	244	246	java/io/IOException
    //   3	7	253	java/lang/ClassNotFoundException
    //   47	60	253	java/lang/ClassNotFoundException
    //   98	114	253	java/lang/ClassNotFoundException
    //   274	278	293	java/io/IOException
    //   286	291	293	java/io/IOException
    //   3	7	300	finally
    //   47	60	300	finally
    //   98	114	300	finally
    //   310	314	325	java/io/IOException
    //   318	322	325	java/io/IOException
    //   114	124	333	finally
    //   129	136	340	finally
    //   180	185	340	finally
    //   190	223	340	finally
    //   265	270	340	finally
    //   114	124	347	java/lang/ClassNotFoundException
    //   129	136	355	java/lang/ClassNotFoundException
    //   114	124	360	java/io/IOException
    //   129	136	368	java/io/IOException
  }
  
  public static boolean d(Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return true;
      try
      {
        int i = paramContext.getResources().getConfiguration().orientation;
        if (i == 2) {
          return false;
        }
      }
      catch (NullPointerException paramContext)
      {
        sb.c(a + " isScreenOriatationPortrait NullPointerException:" + paramContext.getMessage());
      }
    }
    return true;
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    try
    {
      paramContext = wp.a(paramContext);
      if (paramContext == null) {
        break label77;
      }
      if (paramContext.size() <= 0) {
        return false;
      }
      paramContext = paramContext.iterator();
      do
      {
        AndroidAppProcess localAndroidAppProcess;
        do
        {
          if (!paramContext.hasNext()) {
            break;
          }
          localAndroidAppProcess = (AndroidAppProcess)paramContext.next();
        } while (localAndroidAppProcess == null);
        bool = localAndroidAppProcess.a().equals(paramString);
      } while (!bool);
      bool = true;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        boolean bool = false;
      }
    }
    return bool;
    label77:
    return false;
  }
  
  public static String e(String paramString)
  {
    int i = 0;
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return "";
      }
      paramString = paramString.split("\\/");
      if ((paramString != null) && (paramString.length > 0)) {
        while (i < paramString.length)
        {
          Object localObject = paramString[i];
          if ((!TextUtils.isEmpty((CharSequence)localObject)) && (((String)localObject).contains(".zip")))
          {
            localObject = ((String)localObject).split(".zip");
            if ((localObject != null) && (localObject.length > 0))
            {
              paramString = localObject[0];
              return paramString;
            }
          }
          i += 1;
        }
      }
      return "";
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static List<String> f(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return localArrayList;
      paramString = paramString.toLowerCase();
      if (paramString.contains("chrome"))
      {
        localArrayList.add("com.android.chrome");
        localArrayList.add("com.chrome.beta");
        localArrayList.add("com.chrome.dev");
        localArrayList.add("com.chrome.canary");
        localArrayList.add("com.chrome.dev");
        return localArrayList;
      }
      if (paramString.contains("firefox"))
      {
        localArrayList.add("org.mozilla.firefox");
        localArrayList.add("org.mozilla.firefox_beta");
        return localArrayList;
      }
      if (paramString.contains("opera"))
      {
        localArrayList.add("com.opera.browser");
        localArrayList.add("com.opera.mini.native");
        return localArrayList;
      }
      if (paramString.contains("uc"))
      {
        localArrayList.add("com.UCMobile.intl");
        localArrayList.add("com.uc.browser.en");
        localArrayList.add("com.uc.browser");
        localArrayList.add("com.UCMobile");
        localArrayList.add("com.opera.browser");
        return localArrayList;
      }
      if (paramString.contains("dolphin"))
      {
        localArrayList.add("mobi.mgeek.TunnyBrowser");
        localArrayList.add("com.dolphin.browser.zero");
        localArrayList.add("com.dolphin.browser.engine");
        return localArrayList;
      }
      if (paramString.contains("webbrowser"))
      {
        localArrayList.add("com.explore.web.browser");
        return localArrayList;
      }
      if (paramString.contains("cmbrowser"))
      {
        localArrayList.add("com.ksmobile.cb");
        localArrayList.add("com.cmcm.armorfly");
        return localArrayList;
      }
    } while (!paramString.contains("qqbrowser"));
    localArrayList.add("com.tencent.mtt");
    localArrayList.add("com.tencent.mtt.intl");
    return localArrayList;
  }
  
  public static boolean g(String paramString)
  {
    return sw.c(paramString);
  }
  
  public static List<String> h(String paramString)
  {
    int j = 0;
    localArrayList = new ArrayList();
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return localArrayList;
      }
      String str = paramString;
      String[] arrayOfString;
      int k;
      int i;
      if (paramString.contains("null"))
      {
        arrayOfString = paramString.split("null");
        k = arrayOfString.length;
        i = 0;
      }
      for (;;)
      {
        str = paramString;
        if (i < k)
        {
          str = arrayOfString[i];
          if ((str == "null") || (TextUtils.isEmpty(str))) {}
        }
        else
        {
          paramString = str.split(",");
          if ((paramString == null) || (paramString.length <= 0)) {
            break;
          }
          k = paramString.length;
          i = j;
          while (i < k)
          {
            str = paramString[i];
            if (!TextUtils.isEmpty(str)) {
              localArrayList.add(str);
            }
            i += 1;
          }
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static long i(String paramString)
  {
    long l = 0L;
    try
    {
      if (!TextUtils.isEmpty(paramString)) {
        l = Long.parseLong(paramString);
      }
      return l;
    }
    catch (NumberFormatException paramString)
    {
      sb.c(a + " parseLong is error:" + paramString.getMessage());
    }
    return 0L;
  }
  
  public static Bitmap j(String paramString)
  {
    try
    {
      paramString = Base64.decode(paramString.split(",")[1], 0);
      paramString = BitmapFactory.decodeByteArray(paramString, 0, paramString.length);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static void k(String paramString)
  {
    try
    {
      a(new File(paramString));
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static String l(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (a().b(paramString)))
    {
      int i = paramString.lastIndexOf("/");
      if (i == -1) {
        i = 0;
      }
      for (;;)
      {
        paramString = paramString.substring(i);
        return "file://" + wi.n + sn.a(paramString);
        i += 1;
      }
    }
    return null;
  }
}
