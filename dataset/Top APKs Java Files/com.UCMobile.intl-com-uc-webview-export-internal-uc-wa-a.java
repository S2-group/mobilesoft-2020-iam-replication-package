package com.uc.webview.export.internal.uc.wa;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Pair;
import com.uc.webview.export.Build.Version;
import com.uc.webview.export.cyclone.UCLogger;
import com.uc.webview.export.extension.UCCore;
import com.uc.webview.export.internal.SDKFactory;
import com.uc.webview.export.internal.utility.Log;
import com.uc.webview.export.internal.utility.ReflectionUtil;
import com.uc.webview.export.internal.utility.j;
import com.uc.webview.export.internal.utility.j.a;
import java.io.BufferedWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a
{
  public static boolean a = true;
  public static int d = 20480;
  public static int e = 5242880;
  public static int f = d + 1024;
  private static a i;
  public Handler b;
  public List<b> c;
  public SimpleDateFormat g = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public Object h = new Object();
  private Context j;
  private HandlerThread k = new HandlerThread("SDKWaStatThread", 0);
  private Map<String, a> l;
  private SimpleDateFormat m = new SimpleDateFormat("yyyyMMdd");
  
  private a()
  {
    this.k.start();
    this.b = new Handler(this.k.getLooper());
  }
  
  private static <T> int a(BufferedWriter paramBufferedWriter, Map<String, T> paramMap, int paramInt)
  {
    if (!a(paramMap))
    {
      paramMap = paramMap.entrySet().iterator();
      Map.Entry localEntry;
      int i2;
      for (int n = 0;; n = (localEntry.getValue() + "`").length() + (i1 + 1 + i2) + n)
      {
        i1 = n;
        if (!paramMap.hasNext()) {
          break;
        }
        localEntry = (Map.Entry)paramMap.next();
        paramBufferedWriter.write((String)localEntry.getKey());
        paramBufferedWriter.write("=");
        paramBufferedWriter.write("#" + paramInt);
        paramBufferedWriter.write(localEntry.getValue() + "`");
        i1 = ((String)localEntry.getKey()).length();
        i2 = ("#" + paramInt).length();
      }
    }
    int i1 = 0;
    return i1;
  }
  
  public static a a()
  {
    if ((i == null) && (SDKFactory.e != null)) {
      a(SDKFactory.e);
    }
    return i;
  }
  
  private String a(SharedPreferences paramSharedPreferences)
  {
    Object localObject = paramSharedPreferences.getString("2", "");
    paramSharedPreferences = (SharedPreferences)localObject;
    if (com.uc.webview.export.internal.utility.b.a((String)localObject))
    {
      paramSharedPreferences = UUID.randomUUID().toString();
      localObject = this.j.getSharedPreferences("UC_WA_STAT", 4).edit();
      ((SharedPreferences.Editor)localObject).putString("2", paramSharedPreferences);
      ((SharedPreferences.Editor)localObject).commit();
    }
    return paramSharedPreferences;
  }
  
  private String a(String[] paramArrayOfString)
  {
    Object localObject1 = i();
    if (localObject1 == null) {
      return null;
    }
    Object localObject3 = (Map)localObject1[0];
    Object localObject2 = (List)localObject1[1];
    Object localObject4;
    Object localObject5;
    Object localObject6;
    try
    {
      localObject1 = new JSONObject();
      localObject4 = b(this.j);
      localObject5 = b((List)localObject4).iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject6 = (String[])((Iterator)localObject5).next();
        ((JSONObject)localObject1).put(localObject6[0], localObject6[1]);
      }
      if (j.d()) {
        break label173;
      }
    }
    catch (Exception paramArrayOfString)
    {
      Log.e("SDKWaStat", "getJsonUploadData", paramArrayOfString);
      return null;
    }
    paramArrayOfString[0] = b((Map)localObject3, (List)localObject2);
    paramArrayOfString = a((List)localObject4, paramArrayOfString[0]).iterator();
    while (paramArrayOfString.hasNext())
    {
      localObject4 = (String[])paramArrayOfString.next();
      ((JSONObject)localObject1).put(localObject4[0], localObject4[1]);
    }
    label173:
    paramArrayOfString = SDKFactory.A.entrySet().iterator();
    while (paramArrayOfString.hasNext())
    {
      localObject4 = (Map.Entry)paramArrayOfString.next();
      ((JSONObject)localObject1).put((String)((Map.Entry)localObject4).getKey(), ((Integer)((Map.Entry)localObject4).getValue()).intValue());
    }
    paramArrayOfString = new JSONArray();
    localObject3 = ((Map)localObject3).entrySet().iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject5 = (Map.Entry)((Iterator)localObject3).next();
      localObject4 = new JSONObject();
      localObject6 = ((a)((Map.Entry)localObject5).getValue()).a.entrySet().iterator();
      while (((Iterator)localObject6).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject6).next();
        ((JSONObject)localObject4).put((String)localEntry.getKey(), String.valueOf(localEntry.getValue()));
      }
      localObject5 = ((a)((Map.Entry)localObject5).getValue()).b.entrySet().iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject6 = (Map.Entry)((Iterator)localObject5).next();
        ((JSONObject)localObject4).put((String)((Map.Entry)localObject6).getKey(), ((Map.Entry)localObject6).getValue());
      }
      paramArrayOfString.put(localObject4);
    }
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject4 = (b)((Iterator)localObject2).next();
      localObject3 = new JSONObject();
      localObject4 = ((b)localObject4).b.entrySet().iterator();
      while (((Iterator)localObject4).hasNext())
      {
        localObject5 = (Map.Entry)((Iterator)localObject4).next();
        ((JSONObject)localObject3).put((String)((Map.Entry)localObject5).getKey(), ((Map.Entry)localObject5).getValue());
      }
      paramArrayOfString.put(localObject3);
    }
    ((JSONObject)localObject1).put("items", paramArrayOfString);
    ((JSONObject)localObject1).put("stat_size", String.valueOf(((JSONObject)localObject1).toString().length()));
    paramArrayOfString = ((JSONObject)localObject1).toString();
    return paramArrayOfString;
  }
  
  private List<String[]> a(List<PackageInfo> paramList, String paramString)
  {
    Object localObject;
    if (paramString == null) {
      localObject = new ArrayList(0);
    }
    ArrayList localArrayList;
    String str;
    do
    {
      return localObject;
      localArrayList = new ArrayList();
      str = this.j.getSharedPreferences("UC_WA_STAT", 4).getString("4", null);
      if (str == null) {
        break;
      }
      localObject = localArrayList;
    } while (str.equals(paramString));
    localArrayList.add(new String[] { "sdk_3rdappf", c(paramList) });
    return localArrayList;
  }
  
  /* Error */
  public static void a(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 360	com/uc/webview/export/internal/uc/wa/a:b	()Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifne +7 -> 15
    //   11: ldc 2
    //   13: monitorexit
    //   14: return
    //   15: getstatic 160	com/uc/webview/export/internal/uc/wa/a:i	Lcom/uc/webview/export/internal/uc/wa/a;
    //   18: ifnonnull +13 -> 31
    //   21: new 2	com/uc/webview/export/internal/uc/wa/a
    //   24: dup
    //   25: invokespecial 361	com/uc/webview/export/internal/uc/wa/a:<init>	()V
    //   28: putstatic 160	com/uc/webview/export/internal/uc/wa/a:i	Lcom/uc/webview/export/internal/uc/wa/a;
    //   31: getstatic 160	com/uc/webview/export/internal/uc/wa/a:i	Lcom/uc/webview/export/internal/uc/wa/a;
    //   34: aload_0
    //   35: invokevirtual 365	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   38: putfield 192	com/uc/webview/export/internal/uc/wa/a:j	Landroid/content/Context;
    //   41: goto -30 -> 11
    //   44: astore_0
    //   45: ldc 2
    //   47: monitorexit
    //   48: aload_0
    //   49: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	50	0	paramContext	Context
    //   6	2	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	7	44	finally
    //   15	31	44	finally
    //   31	41	44	finally
  }
  
  public static void a(Pair<String, HashMap<String, String>> paramPair)
  {
    UCLogger localUCLogger = UCLogger.create("d", "SDKWaStat");
    if (localUCLogger != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      String str = (String)paramPair.first;
      localStringBuilder.append("ev_ac=").append(str);
      paramPair = ((HashMap)paramPair.second).entrySet().iterator();
      while (paramPair.hasNext())
      {
        Object localObject = (Map.Entry)paramPair.next();
        str = (String)((Map.Entry)localObject).getKey();
        localObject = (String)((Map.Entry)localObject).getValue();
        localStringBuilder.append("`").append(str).append("=").append((String)localObject);
      }
      localUCLogger.print(localStringBuilder.toString(), new Throwable[0]);
    }
  }
  
  private static void a(StringBuilder paramStringBuilder, String paramString1, String paramString2)
  {
    paramStringBuilder.append(paramString1).append("=").append(paramString2).append("`");
  }
  
  /* Error */
  private void a(Map<String, a> paramMap, List<b> paramList)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 7
    //   3: aconst_null
    //   4: astore 10
    //   6: aconst_null
    //   7: astore 9
    //   9: aload_1
    //   10: invokestatic 90	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/util/Map;)Z
    //   13: ifeq +11 -> 24
    //   16: aload_2
    //   17: invokestatic 408	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/util/List;)Z
    //   20: ifeq +4 -> 24
    //   23: return
    //   24: new 410	java/io/File
    //   27: dup
    //   28: aload_0
    //   29: invokespecial 412	com/uc/webview/export/internal/uc/wa/a:f	()Ljava/lang/String;
    //   32: invokestatic 414	com/uc/webview/export/internal/uc/wa/a:g	()Ljava/lang/String;
    //   35: invokespecial 417	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: astore 11
    //   40: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   43: ifeq +27 -> 70
    //   46: ldc_w 287
    //   49: new 128	java/lang/StringBuilder
    //   52: dup
    //   53: ldc_w 424
    //   56: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: aload 11
    //   61: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   64: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   67: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   70: aload 11
    //   72: invokevirtual 429	java/io/File:exists	()Z
    //   75: ifeq +754 -> 829
    //   78: new 431	java/io/FileInputStream
    //   81: dup
    //   82: aload 11
    //   84: invokespecial 434	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   87: astore 8
    //   89: aload 8
    //   91: invokevirtual 437	java/io/FileInputStream:available	()I
    //   94: istore 4
    //   96: aload 8
    //   98: invokevirtual 440	java/io/FileInputStream:close	()V
    //   101: iload 4
    //   103: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   106: if_icmplt +62 -> 168
    //   109: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   112: ifeq +39 -> 151
    //   115: ldc_w 287
    //   118: new 128	java/lang/StringBuilder
    //   121: dup
    //   122: ldc_w 442
    //   125: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   128: iload 4
    //   130: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   133: ldc_w 444
    //   136: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   142: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   145: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: aconst_null
    //   152: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   155: aconst_null
    //   156: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   159: aconst_null
    //   160: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   163: aconst_null
    //   164: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   167: return
    //   168: new 451	java/io/FileOutputStream
    //   171: dup
    //   172: aload 11
    //   174: iconst_1
    //   175: invokespecial 454	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   178: astore 8
    //   180: new 121	java/io/BufferedWriter
    //   183: dup
    //   184: new 456	java/io/OutputStreamWriter
    //   187: dup
    //   188: aload 8
    //   190: invokespecial 459	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   193: sipush 1024
    //   196: invokespecial 462	java/io/BufferedWriter:<init>	(Ljava/io/Writer;I)V
    //   199: astore 9
    //   201: aload_1
    //   202: invokeinterface 96 1 0
    //   207: invokeinterface 102 1 0
    //   212: astore 10
    //   214: iconst_0
    //   215: istore_3
    //   216: iload_3
    //   217: istore 5
    //   219: iload 7
    //   221: istore 6
    //   223: aload 10
    //   225: invokeinterface 108 1 0
    //   230: ifeq +83 -> 313
    //   233: aload 10
    //   235: invokeinterface 112 1 0
    //   240: checkcast 114	java/util/Map$Entry
    //   243: astore 11
    //   245: iload_3
    //   246: iload 4
    //   248: iadd
    //   249: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   252: if_icmplt +149 -> 401
    //   255: iload_3
    //   256: istore 5
    //   258: iload 7
    //   260: istore 6
    //   262: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   265: ifeq +48 -> 313
    //   268: ldc_w 287
    //   271: new 128	java/lang/StringBuilder
    //   274: dup
    //   275: ldc_w 464
    //   278: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   281: iload_3
    //   282: iload 4
    //   284: iadd
    //   285: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   288: ldc_w 444
    //   291: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   297: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   300: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   306: iload 7
    //   308: istore 6
    //   310: iload_3
    //   311: istore 5
    //   313: iload 6
    //   315: bipush 10
    //   317: if_icmpge +64 -> 381
    //   320: iload 6
    //   322: aload_2
    //   323: invokeinterface 467 1 0
    //   328: if_icmpge +53 -> 381
    //   331: iload 5
    //   333: iload 4
    //   335: iadd
    //   336: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   339: if_icmplt +287 -> 626
    //   342: ldc_w 287
    //   345: new 128	java/lang/StringBuilder
    //   348: dup
    //   349: ldc_w 469
    //   352: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   355: iload 5
    //   357: iload 4
    //   359: iadd
    //   360: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   363: ldc_w 444
    //   366: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   369: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   372: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   375: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   378: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   381: aload 9
    //   383: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   386: aload 8
    //   388: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   391: aload 8
    //   393: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   396: aconst_null
    //   397: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   400: return
    //   401: aload 9
    //   403: ldc_w 471
    //   406: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   409: aload 9
    //   411: ldc_w 473
    //   414: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   417: aload 11
    //   419: invokeinterface 117 1 0
    //   424: checkcast 119	java/lang/String
    //   427: astore_1
    //   428: aload_1
    //   429: ldc_w 475
    //   432: invokevirtual 479	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   435: astore 12
    //   437: aload 12
    //   439: iconst_1
    //   440: aaload
    //   441: ldc_w 481
    //   444: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   447: ifeq +71 -> 518
    //   450: sipush 10020
    //   453: iconst_0
    //   454: anewarray 4	java/lang/Object
    //   457: invokestatic 485	com/uc/webview/export/internal/SDKFactory:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   460: checkcast 309	java/lang/Integer
    //   463: invokevirtual 312	java/lang/Integer:intValue	()I
    //   466: istore 6
    //   468: iload 6
    //   470: istore 5
    //   472: iload 6
    //   474: iconst_2
    //   475: if_icmpeq +14 -> 489
    //   478: iload 6
    //   480: bipush 10
    //   482: imul
    //   483: getstatic 488	com/uc/webview/export/internal/SDKFactory:o	I
    //   486: iadd
    //   487: istore 5
    //   489: new 128	java/lang/StringBuilder
    //   492: dup
    //   493: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   496: aload 12
    //   498: iconst_0
    //   499: aaload
    //   500: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: ldc_w 475
    //   506: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: iload 5
    //   511: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   514: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   517: astore_1
    //   518: aload 9
    //   520: aload_1
    //   521: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   524: aload_1
    //   525: invokevirtual 155	java/lang/String:length	()I
    //   528: istore 5
    //   530: aload 9
    //   532: ldc_w 490
    //   535: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   538: aload 9
    //   540: aload 11
    //   542: invokeinterface 143 1 0
    //   547: checkcast 6	com/uc/webview/export/internal/uc/wa/a$a
    //   550: getfield 320	com/uc/webview/export/internal/uc/wa/a$a:a	Ljava/util/Map;
    //   553: iconst_0
    //   554: invokestatic 492	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/io/BufferedWriter;Ljava/util/Map;I)I
    //   557: iload 5
    //   559: iload_3
    //   560: iconst_2
    //   561: iadd
    //   562: iconst_3
    //   563: iadd
    //   564: iadd
    //   565: iconst_3
    //   566: iadd
    //   567: iadd
    //   568: aload 9
    //   570: aload 11
    //   572: invokeinterface 143 1 0
    //   577: checkcast 6	com/uc/webview/export/internal/uc/wa/a$a
    //   580: getfield 325	com/uc/webview/export/internal/uc/wa/a$a:b	Ljava/util/Map;
    //   583: iconst_1
    //   584: invokestatic 492	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/io/BufferedWriter;Ljava/util/Map;I)I
    //   587: iadd
    //   588: istore_3
    //   589: aload 9
    //   591: invokevirtual 495	java/io/BufferedWriter:newLine	()V
    //   594: goto -378 -> 216
    //   597: astore_1
    //   598: aload 9
    //   600: astore_1
    //   601: aconst_null
    //   602: astore_2
    //   603: aload 8
    //   605: astore 9
    //   607: aload_1
    //   608: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   611: aload 9
    //   613: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   616: aload 8
    //   618: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   621: aload_2
    //   622: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   625: return
    //   626: aload_2
    //   627: iload 6
    //   629: invokeinterface 499 2 0
    //   634: checkcast 9	com/uc/webview/export/internal/uc/wa/a$b
    //   637: astore_1
    //   638: aload 9
    //   640: ldc_w 501
    //   643: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   646: aload 9
    //   648: ldc_w 473
    //   651: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   654: aload 9
    //   656: aload_1
    //   657: getfield 504	com/uc/webview/export/internal/uc/wa/a$b:a	Ljava/lang/String;
    //   660: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   663: aload_1
    //   664: getfield 504	com/uc/webview/export/internal/uc/wa/a$b:a	Ljava/lang/String;
    //   667: invokevirtual 155	java/lang/String:length	()I
    //   670: istore_3
    //   671: aload 9
    //   673: ldc_w 490
    //   676: invokevirtual 124	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   679: aload 9
    //   681: aload_1
    //   682: getfield 329	com/uc/webview/export/internal/uc/wa/a$b:b	Ljava/util/Map;
    //   685: iconst_1
    //   686: invokestatic 492	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/io/BufferedWriter;Ljava/util/Map;I)I
    //   689: istore 7
    //   691: aload 9
    //   693: invokevirtual 495	java/io/BufferedWriter:newLine	()V
    //   696: iload 6
    //   698: iconst_1
    //   699: iadd
    //   700: istore 6
    //   702: iload 7
    //   704: iload 5
    //   706: iconst_2
    //   707: iadd
    //   708: iconst_3
    //   709: iadd
    //   710: iload_3
    //   711: iadd
    //   712: iconst_3
    //   713: iadd
    //   714: iadd
    //   715: istore 5
    //   717: goto -404 -> 313
    //   720: astore_1
    //   721: aconst_null
    //   722: astore 9
    //   724: aconst_null
    //   725: astore 8
    //   727: aconst_null
    //   728: astore_2
    //   729: aload 9
    //   731: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   734: aload 8
    //   736: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   739: aload 10
    //   741: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   744: aload_2
    //   745: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   748: aload_1
    //   749: athrow
    //   750: astore_1
    //   751: aconst_null
    //   752: astore 9
    //   754: aconst_null
    //   755: astore 11
    //   757: aload 8
    //   759: astore_2
    //   760: aload 11
    //   762: astore 8
    //   764: goto -35 -> 729
    //   767: astore_1
    //   768: aconst_null
    //   769: astore 9
    //   771: aconst_null
    //   772: astore_2
    //   773: aload 8
    //   775: astore 10
    //   777: goto -48 -> 729
    //   780: astore_1
    //   781: aconst_null
    //   782: astore_2
    //   783: aload 8
    //   785: astore 10
    //   787: goto -58 -> 729
    //   790: astore_1
    //   791: aconst_null
    //   792: astore_1
    //   793: aconst_null
    //   794: astore 8
    //   796: aconst_null
    //   797: astore_2
    //   798: goto -191 -> 607
    //   801: astore_1
    //   802: aconst_null
    //   803: astore 10
    //   805: aload 8
    //   807: astore_2
    //   808: aconst_null
    //   809: astore_1
    //   810: aload 10
    //   812: astore 8
    //   814: goto -207 -> 607
    //   817: astore_1
    //   818: aconst_null
    //   819: astore_1
    //   820: aconst_null
    //   821: astore_2
    //   822: aload 8
    //   824: astore 9
    //   826: goto -219 -> 607
    //   829: iconst_0
    //   830: istore 4
    //   832: goto -731 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	835	0	this	a
    //   0	835	1	paramMap	Map<String, a>
    //   0	835	2	paramList	List<b>
    //   215	497	3	n	int
    //   94	737	4	i1	int
    //   217	499	5	i2	int
    //   221	480	6	i3	int
    //   1	714	7	i4	int
    //   87	736	8	localObject1	Object
    //   7	818	9	localObject2	Object
    //   4	807	10	localObject3	Object
    //   38	723	11	localObject4	Object
    //   435	62	12	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   201	214	597	java/lang/Exception
    //   223	255	597	java/lang/Exception
    //   262	306	597	java/lang/Exception
    //   320	381	597	java/lang/Exception
    //   401	437	597	java/lang/Exception
    //   437	468	597	java/lang/Exception
    //   478	489	597	java/lang/Exception
    //   489	518	597	java/lang/Exception
    //   518	594	597	java/lang/Exception
    //   626	696	597	java/lang/Exception
    //   70	89	720	finally
    //   101	151	720	finally
    //   168	180	720	finally
    //   89	101	750	finally
    //   180	201	767	finally
    //   201	214	780	finally
    //   223	255	780	finally
    //   262	306	780	finally
    //   320	381	780	finally
    //   401	437	780	finally
    //   437	468	780	finally
    //   478	489	780	finally
    //   489	518	780	finally
    //   518	594	780	finally
    //   626	696	780	finally
    //   70	89	790	java/lang/Exception
    //   101	151	790	java/lang/Exception
    //   168	180	790	java/lang/Exception
    //   89	101	801	java/lang/Exception
    //   180	201	817	java/lang/Exception
  }
  
  private static boolean a(List paramList)
  {
    return (paramList == null) || (paramList.size() == 0);
  }
  
  private static boolean a(Map paramMap)
  {
    return (paramMap == null) || (paramMap.size() == 0);
  }
  
  private static String b(Map<String, String> paramMap)
  {
    paramMap = (String)paramMap.get("tm");
    if ((paramMap != null) && (paramMap.length() > 10)) {
      return paramMap.substring(0, 10);
    }
    return null;
  }
  
  private static String b(Map<String, a> paramMap, List<b> paramList)
  {
    Object localObject1 = null;
    int n = 0;
    Object localObject2 = localObject1;
    String str;
    label51:
    Object localObject3;
    if (n < 3)
    {
      str = new String[] { "01", "10", "20" }[n];
      Iterator localIterator = paramMap.entrySet().iterator();
      if (localIterator.hasNext())
      {
        localObject3 = b(((a)((Map.Entry)localIterator.next()).getValue()).b);
        if ((localObject3 == null) || (!((String)localObject3).endsWith(str))) {
          break label216;
        }
        localObject2 = localObject3;
        if (localObject1 != null) {
          if (((String)localObject3).compareTo((String)localObject1) <= 0) {
            break label216;
          }
        }
      }
    }
    label216:
    for (localObject2 = localObject3;; localObject2 = localObject1)
    {
      localObject1 = localObject2;
      break label51;
      localObject3 = paramList.iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject2 = b(((b)((Iterator)localObject3).next()).b);
        if ((localObject2 != null) && (((String)localObject2).endsWith(str)) && ((localObject1 == null) || (((String)localObject2).compareTo((String)localObject1) > 0))) {
          localObject1 = localObject2;
        }
      }
      localObject2 = localObject1;
      if (localObject1 == null)
      {
        n += 1;
        break;
      }
      return localObject2;
    }
  }
  
  private static List<PackageInfo> b(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  private List<String[]> b(List<PackageInfo> paramList)
  {
    paramList = paramList.iterator();
    Object localObject;
    int n;
    if (paramList.hasNext())
    {
      localObject = (PackageInfo)paramList.next();
      if (((PackageInfo)localObject).packageName.equals("com.UCMobile"))
      {
        n = 1;
        label41:
        localObject = new ArrayList();
        ((List)localObject).add(new String[] { "lt", "ev" });
        ((List)localObject).add(new String[] { "ct", "corepv" });
        ((List)localObject).add(new String[] { "ver", Build.Version.NAME });
        ((List)localObject).add(new String[] { "pkg", this.j.getPackageName() });
        ((List)localObject).add(new String[] { "sdk_sn", com.uc.webview.export.Build.TIME });
        if (!com.uc.webview.export.internal.utility.b.a(android.os.Build.MODEL)) {
          break label1679;
        }
        paramList = "unknown";
        label181:
        ((List)localObject).add(new String[] { "sdk_pm", paramList });
        StringBuilder localStringBuilder1 = new StringBuilder();
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(524288L) })).booleanValue()) {
          if (SDKFactory.invoke(10036, new Object[] { this.j }) != null) {
            break label1697;
          }
        }
        paramList = "0";
        label263:
        StringBuilder localStringBuilder2 = localStringBuilder1.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(1L) })).booleanValue()) {
          break label1704;
        }
        paramList = "1";
        label301:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!SDKFactory.l) {
          break label1711;
        }
        paramList = "1";
        label319:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(2L) })).booleanValue()) {
          break label1718;
        }
        paramList = "1";
        label359:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(4L) })).booleanValue()) {
          break label1725;
        }
        paramList = "1";
        label399:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(8L) })).booleanValue()) {
          break label1732;
        }
        paramList = "1";
        label439:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(16L) })).booleanValue()) {
          break label1739;
        }
        paramList = "1";
        label479:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(32L) })).booleanValue()) {
          break label1746;
        }
        paramList = "1";
        label519:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(64L) })).booleanValue()) {
          break label1753;
        }
        paramList = "1";
        label559:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(128L) })).booleanValue()) {
          break label1760;
        }
        paramList = "1";
        label599:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(256L) })).booleanValue()) {
          break label1767;
        }
        paramList = "1";
        label639:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(512L) })).booleanValue()) {
          break label1774;
        }
        paramList = "1";
        label679:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(1024L) })).booleanValue()) {
          break label1781;
        }
        paramList = "1";
        label719:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(2048L) })).booleanValue()) {
          break label1788;
        }
        paramList = "1";
        label759:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(4096L) })).booleanValue()) {
          break label1795;
        }
        paramList = "1";
        label799:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(8192L) })).booleanValue()) {
          break label1802;
        }
        paramList = "1";
        label839:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(16384L) })).booleanValue()) {
          break label1809;
        }
        paramList = "1";
        label879:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(32768L) })).booleanValue()) {
          break label1816;
        }
        paramList = "1";
        label919:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(65536L) })).booleanValue()) {
          break label1823;
        }
        paramList = "1";
        label959:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(131072L) })).booleanValue()) {
          break label1830;
        }
        paramList = "1";
        label999:
        localStringBuilder2 = localStringBuilder2.append(paramList);
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(262144L) })).booleanValue()) {
          break label1837;
        }
        paramList = "1";
        label1039:
        localStringBuilder2.append(paramList);
        ((List)localObject).add(new String[] { "sdk_f", localStringBuilder1.toString() });
        ((List)localObject).add(new String[] { "sdk_uf", String.valueOf(n) });
        if (!com.uc.webview.export.internal.utility.b.a(android.os.Build.BRAND)) {
          break label1844;
        }
        paramList = "unknown";
        label1108:
        ((List)localObject).add(new String[] { "sdk_bd", paramList });
        ((List)localObject).add(new String[] { "sdk_osv", Build.VERSION.RELEASE });
        ((List)localObject).add(new String[] { "sdk_prd", com.uc.webview.export.Build.SDK_PRD });
        ((List)localObject).add(new String[] { "sdk_pfid", com.uc.webview.export.Build.SDK_PROFILE_ID });
        ((List)localObject).add(new String[] { "sdk_cos", j.c() });
        ((List)localObject).add(new String[] { "pro_sf", (String)UCCore.getGlobalOption("process_private_data_dir_suffix") });
        ((List)localObject).add(new String[] { "uuid", a(this.j.getSharedPreferences("UC_WA_STAT", 4)) });
        paramList = (String)UCCore.getGlobalOption("adapter_build_timing");
        if (!com.uc.webview.export.internal.utility.b.a(paramList)) {
          ((List)localObject).add(new String[] { "ab_sn", paramList });
        }
        paramList = (String)UCCore.getGlobalOption("adapter_build_version");
        if (!com.uc.webview.export.internal.utility.b.a(paramList)) {
          ((List)localObject).add(new String[] { "ab_ve", paramList });
        }
        if (!com.uc.webview.export.internal.utility.b.a(com.uc.webview.export.Build.CORE_VERSION)) {
          ((List)localObject).add(new String[] { "sdk_sdk_cv", com.uc.webview.export.Build.CORE_VERSION.trim() });
        }
        if (!com.uc.webview.export.internal.utility.b.a(com.uc.webview.export.Build.UCM_VERSION)) {
          ((List)localObject).add(new String[] { "sdk_ucm_cv", com.uc.webview.export.Build.UCM_VERSION.trim() });
        }
        if (n == 0)
        {
          if (!new File("/sdcard/Backucup/com.UCMobile").exists()) {
            break label1862;
          }
          paramList = "1";
          label1454:
          ((List)localObject).add(new String[] { "sdk_ucbackup", paramList });
        }
        if (!((Boolean)SDKFactory.invoke(10003, new Object[] { Long.valueOf(1048576L) })).booleanValue()) {
          break label1869;
        }
        paramList = "1";
        label1507:
        ((List)localObject).add(new String[] { "sdk_vac", paramList });
        paramList = j.a.a(this.j);
        if (com.uc.webview.export.internal.utility.b.a(paramList)) {
          break label1876;
        }
        ((List)localObject).add(new String[] { "ut_k", paramList });
      }
    }
    for (;;)
    {
      ((List)localObject).add(new String[] { "data_dir", this.j.getApplicationInfo().dataDir });
      paramList = (File)ReflectionUtil.invokeNoThrow(this.j, "getSharedPrefsFile", new Class[] { String.class }, new Object[] { "UC_WA_STAT" });
      if (paramList != null) {
        ((List)localObject).add(new String[] { "sp_dir", paramList.getAbsolutePath() });
      }
      return localObject;
      if (!((PackageInfo)localObject).packageName.equals("com.UCMobile.intl")) {
        break;
      }
      n = 2;
      break label41;
      n = 0;
      break label41;
      label1679:
      paramList = android.os.Build.MODEL.trim().replaceAll("[`|=]", "");
      break label181;
      label1697:
      paramList = "1";
      break label263;
      label1704:
      paramList = "0";
      break label301;
      label1711:
      paramList = "0";
      break label319;
      label1718:
      paramList = "0";
      break label359;
      label1725:
      paramList = "0";
      break label399;
      label1732:
      paramList = "0";
      break label439;
      label1739:
      paramList = "0";
      break label479;
      label1746:
      paramList = "0";
      break label519;
      label1753:
      paramList = "0";
      break label559;
      label1760:
      paramList = "0";
      break label599;
      label1767:
      paramList = "0";
      break label639;
      label1774:
      paramList = "0";
      break label679;
      label1781:
      paramList = "0";
      break label719;
      label1788:
      paramList = "0";
      break label759;
      label1795:
      paramList = "0";
      break label799;
      label1802:
      paramList = "0";
      break label839;
      label1809:
      paramList = "0";
      break label879;
      label1816:
      paramList = "0";
      break label919;
      label1823:
      paramList = "0";
      break label959;
      label1830:
      paramList = "0";
      break label999;
      label1837:
      paramList = "0";
      break label1039;
      label1844:
      paramList = android.os.Build.BRAND.trim().replaceAll("[`|=]", "");
      break label1108;
      label1862:
      paramList = "0";
      break label1454;
      label1869:
      paramList = "0";
      break label1507;
      label1876:
      ((List)localObject).add(new String[] { "ut_k", "null" });
    }
  }
  
  public static boolean b()
  {
    return ((Boolean)SDKFactory.invoke(10006, new Object[] { "stat", Boolean.valueOf(true) })).booleanValue();
  }
  
  /* Error */
  private static boolean b(String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 6
    //   6: getstatic 789	com/uc/webview/export/internal/SDKFactory:g	Z
    //   9: ifne +29 -> 38
    //   12: ldc_w 791
    //   15: invokestatic 794	com/uc/webview/export/extension/UCCore:getParam	(Ljava/lang/String;)Ljava/lang/String;
    //   18: invokestatic 797	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   21: ifeq +17 -> 38
    //   24: getstatic 800	android/os/Build$VERSION:SDK_INT	I
    //   27: bipush 14
    //   29: if_icmplt +9 -> 38
    //   32: ldc_w 801
    //   35: invokestatic 806	android/net/TrafficStats:setThreadStatsTag	(I)V
    //   38: new 808	java/net/URL
    //   41: dup
    //   42: aload_0
    //   43: invokespecial 809	java/net/URL:<init>	(Ljava/lang/String;)V
    //   46: invokevirtual 813	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   49: checkcast 815	java/net/HttpURLConnection
    //   52: astore 5
    //   54: aload 5
    //   56: getstatic 817	com/uc/webview/export/internal/utility/j:a	I
    //   59: invokevirtual 820	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   62: aload 5
    //   64: getstatic 822	com/uc/webview/export/internal/utility/j:b	I
    //   67: invokevirtual 825	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   70: aload 5
    //   72: iconst_1
    //   73: invokevirtual 829	java/net/HttpURLConnection:setDoInput	(Z)V
    //   76: aload 5
    //   78: iconst_1
    //   79: invokevirtual 832	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   82: aload 5
    //   84: ldc_w 834
    //   87: invokevirtual 837	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   90: aload 5
    //   92: iconst_0
    //   93: invokevirtual 840	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   96: aload 5
    //   98: ldc_w 842
    //   101: ldc_w 844
    //   104: invokevirtual 847	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   107: aload 5
    //   109: ldc_w 849
    //   112: aload_1
    //   113: arraylength
    //   114: invokestatic 337	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   117: invokevirtual 847	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload 5
    //   122: invokevirtual 853	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   125: astore_0
    //   126: aload_0
    //   127: aload_1
    //   128: invokevirtual 858	java/io/OutputStream:write	([B)V
    //   131: aload 5
    //   133: invokevirtual 861	java/net/HttpURLConnection:getResponseCode	()I
    //   136: sipush 200
    //   139: if_icmpeq +39 -> 178
    //   142: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   145: ifeq +19 -> 164
    //   148: ldc_w 287
    //   151: ldc_w 863
    //   154: new 388	java/lang/Throwable
    //   157: dup
    //   158: invokespecial 864	java/lang/Throwable:<init>	()V
    //   161: invokestatic 294	com/uc/webview/export/internal/utility/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   164: aload_0
    //   165: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   168: aconst_null
    //   169: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   172: aconst_null
    //   173: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   176: iconst_0
    //   177: ireturn
    //   178: aload 5
    //   180: invokevirtual 868	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   183: astore_1
    //   184: sipush 1024
    //   187: newarray byte
    //   189: astore 4
    //   191: new 870	java/io/ByteArrayOutputStream
    //   194: dup
    //   195: aload_1
    //   196: invokevirtual 873	java/io/InputStream:available	()I
    //   199: invokespecial 874	java/io/ByteArrayOutputStream:<init>	(I)V
    //   202: astore 6
    //   204: aload_1
    //   205: aload 4
    //   207: invokevirtual 878	java/io/InputStream:read	([B)I
    //   210: istore_2
    //   211: iload_2
    //   212: iconst_m1
    //   213: if_icmpeq +64 -> 277
    //   216: aload 6
    //   218: aload 4
    //   220: iconst_0
    //   221: iload_2
    //   222: invokevirtual 881	java/io/ByteArrayOutputStream:write	([BII)V
    //   225: goto -21 -> 204
    //   228: astore 7
    //   230: aload_1
    //   231: astore 5
    //   233: aload 6
    //   235: astore 4
    //   237: aload_0
    //   238: astore_1
    //   239: aload 5
    //   241: astore_0
    //   242: aload 7
    //   244: astore 5
    //   246: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   249: ifeq +13 -> 262
    //   252: ldc_w 287
    //   255: ldc -84
    //   257: aload 5
    //   259: invokestatic 294	com/uc/webview/export/internal/utility/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   262: aload_1
    //   263: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   266: aload_0
    //   267: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   270: aload 4
    //   272: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   275: iconst_0
    //   276: ireturn
    //   277: new 119	java/lang/String
    //   280: dup
    //   281: aload 6
    //   283: invokevirtual 884	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   286: invokespecial 886	java/lang/String:<init>	([B)V
    //   289: astore 4
    //   291: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   294: ifeq +27 -> 321
    //   297: ldc_w 287
    //   300: new 128	java/lang/StringBuilder
    //   303: dup
    //   304: ldc_w 888
    //   307: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   310: aload 4
    //   312: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokestatic 521	com/uc/webview/export/internal/utility/Log:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   321: aload 4
    //   323: ldc_w 890
    //   326: invokevirtual 894	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   329: istore_3
    //   330: iload_3
    //   331: ifeq +18 -> 349
    //   334: aload_0
    //   335: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   338: aload_1
    //   339: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   342: aload 6
    //   344: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   347: iconst_1
    //   348: ireturn
    //   349: aload_0
    //   350: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   353: aload_1
    //   354: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   357: aload 6
    //   359: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   362: goto -87 -> 275
    //   365: astore_1
    //   366: aconst_null
    //   367: astore 5
    //   369: aconst_null
    //   370: astore_0
    //   371: aload 5
    //   373: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   376: aload 4
    //   378: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   381: aload_0
    //   382: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   385: aload_1
    //   386: athrow
    //   387: astore_1
    //   388: aconst_null
    //   389: astore 6
    //   391: aload_0
    //   392: astore 5
    //   394: aload 6
    //   396: astore_0
    //   397: goto -26 -> 371
    //   400: astore 5
    //   402: aconst_null
    //   403: astore 6
    //   405: aload_1
    //   406: astore 4
    //   408: aload 5
    //   410: astore_1
    //   411: aload_0
    //   412: astore 5
    //   414: aload 6
    //   416: astore_0
    //   417: goto -46 -> 371
    //   420: astore 5
    //   422: aload_1
    //   423: astore 4
    //   425: aload 5
    //   427: astore_1
    //   428: aload_0
    //   429: astore 5
    //   431: aload 6
    //   433: astore_0
    //   434: goto -63 -> 371
    //   437: astore 7
    //   439: aload_1
    //   440: astore 5
    //   442: aload 4
    //   444: astore 6
    //   446: aload 7
    //   448: astore_1
    //   449: aload_0
    //   450: astore 4
    //   452: aload 6
    //   454: astore_0
    //   455: goto -84 -> 371
    //   458: astore 5
    //   460: aconst_null
    //   461: astore_0
    //   462: aconst_null
    //   463: astore 4
    //   465: aload 6
    //   467: astore_1
    //   468: goto -222 -> 246
    //   471: astore 5
    //   473: aconst_null
    //   474: astore 6
    //   476: aconst_null
    //   477: astore 4
    //   479: aload_0
    //   480: astore_1
    //   481: aload 6
    //   483: astore_0
    //   484: goto -238 -> 246
    //   487: astore 5
    //   489: aconst_null
    //   490: astore 6
    //   492: aload_0
    //   493: astore 4
    //   495: aload_1
    //   496: astore_0
    //   497: aload 4
    //   499: astore_1
    //   500: aload 6
    //   502: astore 4
    //   504: goto -258 -> 246
    //   507: astore 5
    //   509: goto -471 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	512	0	paramString	String
    //   0	512	1	paramArrayOfByte	byte[]
    //   210	12	2	n	int
    //   329	2	3	bool	boolean
    //   1	502	4	localObject1	Object
    //   52	341	5	localObject2	Object
    //   400	9	5	localObject3	Object
    //   412	1	5	str	String
    //   420	6	5	localObject4	Object
    //   429	12	5	localObject5	Object
    //   458	1	5	localThrowable1	Throwable
    //   471	1	5	localThrowable2	Throwable
    //   487	1	5	localThrowable3	Throwable
    //   507	1	5	localThrowable4	Throwable
    //   4	497	6	localObject6	Object
    //   228	15	7	localThrowable5	Throwable
    //   437	10	7	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   204	211	228	java/lang/Throwable
    //   216	225	228	java/lang/Throwable
    //   277	321	228	java/lang/Throwable
    //   321	330	228	java/lang/Throwable
    //   38	126	365	finally
    //   126	164	387	finally
    //   178	184	387	finally
    //   184	204	400	finally
    //   204	211	420	finally
    //   216	225	420	finally
    //   277	321	420	finally
    //   321	330	420	finally
    //   246	262	437	finally
    //   38	126	458	java/lang/Throwable
    //   126	164	471	java/lang/Throwable
    //   178	184	471	java/lang/Throwable
    //   184	204	487	java/lang/Throwable
    //   6	38	507	java/lang/Throwable
  }
  
  private static String c(List<PackageInfo> paramList)
  {
    long l1 = System.currentTimeMillis();
    HashSet localHashSet = new HashSet(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localHashSet.add(Integer.valueOf(((PackageInfo)paramList.next()).packageName.hashCode()));
    }
    paramList = new StringBuilder();
    int n = 0;
    if (n < 70)
    {
      if (localHashSet.contains(Integer.valueOf(new int[] { 744792033, -796004189, 1536737232, -1864872766, -245593387, 559984781, 1254578009, 460049591, -103524201, -191341086, 2075805265, -860300598, 195266379, 851655498, -172581751, -1692253156, -1709882794, 978047406, -1447376190, 1085732649, 400412247, 1007750384, 321803898, 1319538838, -1459422248, -173313837, 1488133239, 551552610, 1310504938, 633261597, -548160304, 1971200218, 757982267, 996952171, 1855462465, 2049668591, -189253699, -761937585, -1102972298, 195210534, -1433071276, -118960061, 810513273, 1659293491, 1552103645, 361910168, -973170826, -1805061386, -1635328017, -1131240456, 1429484426, -918490570, 1791072826, -894368837, -1394248969, -1476255283, 1994036591, 1219220171, 201325446, -1215205363, -257645900, 1197124177, 1765779203, 313184810, 308524937, -1652150487, 1174097286, -69877540, 2123438483, -1769871671 }[n]))) {
        paramList.append("1");
      }
      for (;;)
      {
        n += 1;
        break;
        paramList.append("0");
      }
    }
    Log.i("SDKWaStat", "getOtherAppInstallFlag用时:" + (System.currentTimeMillis() - l1) + " " + paramList);
    return paramList.toString();
  }
  
  /* Error */
  private void e()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 360	com/uc/webview/export/internal/uc/wa/a:b	()Z
    //   5: istore_1
    //   6: iload_1
    //   7: ifne +6 -> 13
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   16: ifeq +12 -> 28
    //   19: ldc_w 287
    //   22: ldc_w 992
    //   25: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   28: new 385	java/util/HashMap
    //   31: dup
    //   32: invokespecial 993	java/util/HashMap:<init>	()V
    //   35: astore_3
    //   36: new 339	java/util/ArrayList
    //   39: dup
    //   40: invokespecial 343	java/util/ArrayList:<init>	()V
    //   43: astore 4
    //   45: aload_0
    //   46: getfield 63	com/uc/webview/export/internal/uc/wa/a:h	Ljava/lang/Object;
    //   49: astore_2
    //   50: aload_2
    //   51: monitorenter
    //   52: sipush 10010
    //   55: iconst_0
    //   56: anewarray 4	java/lang/Object
    //   59: invokestatic 485	com/uc/webview/export/internal/SDKFactory:invoke	(I[Ljava/lang/Object;)Ljava/lang/Object;
    //   62: checkcast 619	java/lang/Boolean
    //   65: invokevirtual 622	java/lang/Boolean:booleanValue	()Z
    //   68: ifeq +32 -> 100
    //   71: aload_0
    //   72: getfield 995	com/uc/webview/export/internal/uc/wa/a:l	Ljava/util/Map;
    //   75: invokestatic 90	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/util/Map;)Z
    //   78: ifne +22 -> 100
    //   81: aload_3
    //   82: aload_0
    //   83: getfield 995	com/uc/webview/export/internal/uc/wa/a:l	Ljava/util/Map;
    //   86: invokeinterface 999 2 0
    //   91: aload_0
    //   92: getfield 995	com/uc/webview/export/internal/uc/wa/a:l	Ljava/util/Map;
    //   95: invokeinterface 1002 1 0
    //   100: aload_0
    //   101: getfield 1004	com/uc/webview/export/internal/uc/wa/a:c	Ljava/util/List;
    //   104: invokestatic 408	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/util/List;)Z
    //   107: ifne +24 -> 131
    //   110: aload 4
    //   112: aload_0
    //   113: getfield 1004	com/uc/webview/export/internal/uc/wa/a:c	Ljava/util/List;
    //   116: invokeinterface 1008 2 0
    //   121: pop
    //   122: aload_0
    //   123: getfield 1004	com/uc/webview/export/internal/uc/wa/a:c	Ljava/util/List;
    //   126: invokeinterface 1009 1 0
    //   131: aload_2
    //   132: monitorexit
    //   133: aload_0
    //   134: aload_3
    //   135: aload 4
    //   137: invokespecial 1011	com/uc/webview/export/internal/uc/wa/a:a	(Ljava/util/Map;Ljava/util/List;)V
    //   140: goto -130 -> 10
    //   143: astore_2
    //   144: goto -134 -> 10
    //   147: astore_3
    //   148: aload_2
    //   149: monitorexit
    //   150: aload_3
    //   151: athrow
    //   152: astore_2
    //   153: aload_0
    //   154: monitorexit
    //   155: aload_2
    //   156: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	this	a
    //   5	2	1	bool	boolean
    //   143	6	2	localException	Exception
    //   152	4	2	localObject2	Object
    //   35	100	3	localHashMap	HashMap
    //   147	4	3	localObject3	Object
    //   43	93	4	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   13	28	143	java/lang/Exception
    //   28	52	143	java/lang/Exception
    //   133	140	143	java/lang/Exception
    //   150	152	143	java/lang/Exception
    //   52	100	147	finally
    //   100	131	147	finally
    //   131	133	147	finally
    //   148	150	147	finally
    //   2	6	152	finally
    //   13	28	152	finally
    //   28	52	152	finally
    //   133	140	152	finally
    //   150	152	152	finally
  }
  
  private String f()
  {
    String str = this.j.getApplicationContext().getApplicationInfo().dataDir + "/ucwa";
    File localFile = new File(str);
    if (!localFile.exists()) {
      localFile.mkdir();
    }
    return str;
  }
  
  private static String g()
  {
    String str = (String)UCCore.getGlobalOption("process_private_data_dir_suffix");
    if ((com.uc.webview.export.internal.utility.b.a(str)) || (str.equals("0"))) {
      return "wa_upload_new.wa";
    }
    return "wa_upload_new.wa_" + str;
  }
  
  private static String h()
  {
    String str = (String)UCCore.getGlobalOption("process_private_data_dir_suffix");
    if ((com.uc.webview.export.internal.utility.b.a(str)) || (str.equals("0"))) {
      return "1";
    }
    return "1_" + str;
  }
  
  /* Error */
  private Object[] i()
  {
    // Byte code:
    //   0: new 410	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 412	com/uc/webview/export/internal/uc/wa/a:f	()Ljava/lang/String;
    //   8: invokestatic 414	com/uc/webview/export/internal/uc/wa/a:g	()Ljava/lang/String;
    //   11: invokespecial 417	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   14: astore 5
    //   16: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   19: ifeq +41 -> 60
    //   22: ldc_w 287
    //   25: new 128	java/lang/StringBuilder
    //   28: dup
    //   29: ldc_w 1024
    //   32: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   35: aload 5
    //   37: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   40: ldc_w 1026
    //   43: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: aload 5
    //   48: invokevirtual 429	java/io/File:exists	()Z
    //   51: invokevirtual 1029	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   54: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   60: aload 5
    //   62: invokevirtual 429	java/io/File:exists	()Z
    //   65: ifne +5 -> 70
    //   68: aconst_null
    //   69: areturn
    //   70: aconst_null
    //   71: astore 8
    //   73: aconst_null
    //   74: astore 6
    //   76: aconst_null
    //   77: astore 9
    //   79: aload 5
    //   81: invokevirtual 1031	java/io/File:length	()J
    //   84: pop2
    //   85: new 385	java/util/HashMap
    //   88: dup
    //   89: invokespecial 993	java/util/HashMap:<init>	()V
    //   92: astore 10
    //   94: new 339	java/util/ArrayList
    //   97: dup
    //   98: invokespecial 343	java/util/ArrayList:<init>	()V
    //   101: astore 11
    //   103: new 431	java/io/FileInputStream
    //   106: dup
    //   107: aload 5
    //   109: invokespecial 434	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   112: astore 5
    //   114: new 1033	java/io/BufferedReader
    //   117: dup
    //   118: new 1035	java/io/InputStreamReader
    //   121: dup
    //   122: aload 5
    //   124: invokespecial 1038	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   127: sipush 1024
    //   130: invokespecial 1041	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   133: astore 7
    //   135: iconst_0
    //   136: istore_1
    //   137: aload 7
    //   139: invokevirtual 1044	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   142: astore 6
    //   144: aload 6
    //   146: ifnull +85 -> 231
    //   149: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   152: ifeq +11 -> 163
    //   155: ldc_w 287
    //   158: aload 6
    //   160: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   163: aload 6
    //   165: invokestatic 183	com/uc/webview/export/internal/utility/b:a	(Ljava/lang/String;)Z
    //   168: ifne -31 -> 137
    //   171: aload 6
    //   173: invokevirtual 155	java/lang/String:length	()I
    //   176: iload_1
    //   177: iadd
    //   178: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   181: if_icmple +100 -> 281
    //   184: getstatic 422	com/uc/webview/export/utility/Utils:sWAPrintLog	Z
    //   187: ifeq +44 -> 231
    //   190: ldc_w 287
    //   193: new 128	java/lang/StringBuilder
    //   196: dup
    //   197: ldc_w 1046
    //   200: invokespecial 131	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   203: iload_1
    //   204: aload 6
    //   206: invokevirtual 155	java/lang/String:length	()I
    //   209: iadd
    //   210: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   213: ldc_w 444
    //   216: invokevirtual 151	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: getstatic 39	com/uc/webview/export/internal/uc/wa/a:d	I
    //   222: invokevirtual 135	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   225: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokestatic 426	com/uc/webview/export/internal/utility/Log:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   231: aload 10
    //   233: invokeinterface 509 1 0
    //   238: ifgt +13 -> 251
    //   241: aload 11
    //   243: invokeinterface 467 1 0
    //   248: ifle +616 -> 864
    //   251: aload 7
    //   253: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   256: aload 5
    //   258: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   261: aload 5
    //   263: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   266: iconst_2
    //   267: anewarray 4	java/lang/Object
    //   270: dup
    //   271: iconst_0
    //   272: aload 10
    //   274: aastore
    //   275: dup
    //   276: iconst_1
    //   277: aload 11
    //   279: aastore
    //   280: areturn
    //   281: iload_1
    //   282: aload 6
    //   284: invokevirtual 155	java/lang/String:length	()I
    //   287: iadd
    //   288: istore_1
    //   289: aload 6
    //   291: invokevirtual 718	java/lang/String:trim	()Ljava/lang/String;
    //   294: astore 6
    //   296: aload 6
    //   298: ldc_w 471
    //   301: invokevirtual 1049	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   304: ifne +14 -> 318
    //   307: aload 6
    //   309: ldc_w 501
    //   312: invokevirtual 1049	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   315: ifeq +618 -> 933
    //   318: aload 6
    //   320: ldc_w 473
    //   323: invokevirtual 1052	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   326: istore_2
    //   327: aload 6
    //   329: ldc_w 490
    //   332: invokevirtual 1052	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   335: istore_3
    //   336: iload_2
    //   337: iflt +596 -> 933
    //   340: iload_3
    //   341: iflt +592 -> 933
    //   344: aload 6
    //   346: iload_2
    //   347: iconst_3
    //   348: iadd
    //   349: iload_3
    //   350: invokevirtual 249	java/lang/String:substring	(II)Ljava/lang/String;
    //   353: astore 12
    //   355: aload 6
    //   357: iload_3
    //   358: iconst_3
    //   359: iadd
    //   360: invokevirtual 1054	java/lang/String:substring	(I)Ljava/lang/String;
    //   363: ldc -108
    //   365: invokevirtual 479	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   368: astore 8
    //   370: aload 6
    //   372: ldc_w 471
    //   375: invokevirtual 1049	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   378: ifeq +370 -> 748
    //   381: aload 12
    //   383: ldc_w 475
    //   386: invokevirtual 479	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   389: astore 9
    //   391: aload 9
    //   393: arraylength
    //   394: iconst_2
    //   395: if_icmpne +538 -> 933
    //   398: aload 9
    //   400: iconst_0
    //   401: aaload
    //   402: invokevirtual 155	java/lang/String:length	()I
    //   405: bipush 8
    //   407: if_icmpne +526 -> 933
    //   410: aload 9
    //   412: iconst_1
    //   413: aaload
    //   414: invokevirtual 155	java/lang/String:length	()I
    //   417: iconst_2
    //   418: if_icmpgt +515 -> 933
    //   421: aload 10
    //   423: aload 12
    //   425: invokeinterface 541 2 0
    //   430: checkcast 6	com/uc/webview/export/internal/uc/wa/a$a
    //   433: astore 6
    //   435: aload 6
    //   437: ifnonnull +493 -> 930
    //   440: aload 10
    //   442: invokeinterface 509 1 0
    //   447: bipush 8
    //   449: if_icmpne +18 -> 467
    //   452: aload 11
    //   454: invokeinterface 467 1 0
    //   459: bipush 10
    //   461: if_icmpeq -230 -> 231
    //   464: goto +469 -> 933
    //   467: new 6	com/uc/webview/export/internal/uc/wa/a$a
    //   470: dup
    //   471: aload_0
    //   472: iconst_0
    //   473: invokespecial 1057	com/uc/webview/export/internal/uc/wa/a$a:<init>	(Lcom/uc/webview/export/internal/uc/wa/a;B)V
    //   476: astore 6
    //   478: aload 10
    //   480: aload 12
    //   482: aload 6
    //   484: invokeinterface 1060 3 0
    //   489: pop
    //   490: aload 8
    //   492: arraylength
    //   493: istore_3
    //   494: iconst_0
    //   495: istore_2
    //   496: iload_2
    //   497: iload_3
    //   498: if_icmpge +229 -> 727
    //   501: aload 8
    //   503: iload_2
    //   504: aaload
    //   505: ldc 126
    //   507: invokevirtual 479	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   510: astore 15
    //   512: aload 15
    //   514: arraylength
    //   515: iconst_2
    //   516: if_icmpne +420 -> 936
    //   519: aload 15
    //   521: iconst_1
    //   522: aaload
    //   523: invokevirtual 155	java/lang/String:length	()I
    //   526: iconst_2
    //   527: if_icmple +409 -> 936
    //   530: aload 15
    //   532: iconst_1
    //   533: aaload
    //   534: iconst_2
    //   535: invokevirtual 1054	java/lang/String:substring	(I)Ljava/lang/String;
    //   538: astore 12
    //   540: aload 15
    //   542: iconst_1
    //   543: aaload
    //   544: ldc_w 1062
    //   547: invokevirtual 1049	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   550: ifeq +120 -> 670
    //   553: aload 6
    //   555: getfield 320	com/uc/webview/export/internal/uc/wa/a$a:a	Ljava/util/Map;
    //   558: aload 15
    //   560: iconst_0
    //   561: aaload
    //   562: invokeinterface 541 2 0
    //   567: checkcast 309	java/lang/Integer
    //   570: astore 13
    //   572: aload 13
    //   574: ifnonnull +29 -> 603
    //   577: aload 6
    //   579: getfield 320	com/uc/webview/export/internal/uc/wa/a$a:a	Ljava/util/Map;
    //   582: aload 15
    //   584: iconst_0
    //   585: aaload
    //   586: aload 12
    //   588: invokestatic 1065	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   591: invokestatic 903	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   594: invokeinterface 1060 3 0
    //   599: pop
    //   600: goto +336 -> 936
    //   603: aload 6
    //   605: getfield 320	com/uc/webview/export/internal/uc/wa/a$a:a	Ljava/util/Map;
    //   608: astore 14
    //   610: aload 15
    //   612: iconst_0
    //   613: aaload
    //   614: astore 15
    //   616: aload 12
    //   618: invokestatic 1065	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   621: istore 4
    //   623: aload 14
    //   625: aload 15
    //   627: aload 13
    //   629: invokevirtual 312	java/lang/Integer:intValue	()I
    //   632: iload 4
    //   634: iadd
    //   635: invokestatic 903	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   638: invokeinterface 1060 3 0
    //   643: pop
    //   644: goto +292 -> 936
    //   647: astore 6
    //   649: aload 5
    //   651: astore 6
    //   653: aload 7
    //   655: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   658: aload 6
    //   660: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   663: aload 5
    //   665: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   668: aconst_null
    //   669: areturn
    //   670: aload 15
    //   672: iconst_1
    //   673: aaload
    //   674: ldc_w 1067
    //   677: invokevirtual 1049	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   680: ifeq +256 -> 936
    //   683: aload 6
    //   685: getfield 325	com/uc/webview/export/internal/uc/wa/a$a:b	Ljava/util/Map;
    //   688: aload 15
    //   690: iconst_0
    //   691: aaload
    //   692: aload 12
    //   694: invokeinterface 1060 3 0
    //   699: pop
    //   700: goto +236 -> 936
    //   703: astore 6
    //   705: aload 5
    //   707: astore 8
    //   709: aload 7
    //   711: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   714: aload 5
    //   716: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   719: aload 8
    //   721: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   724: aload 6
    //   726: athrow
    //   727: aload 6
    //   729: getfield 325	com/uc/webview/export/internal/uc/wa/a$a:b	Ljava/util/Map;
    //   732: ldc_w 1069
    //   735: aload 9
    //   737: iconst_1
    //   738: aaload
    //   739: invokeinterface 1060 3 0
    //   744: pop
    //   745: goto -608 -> 137
    //   748: aload 11
    //   750: invokeinterface 467 1 0
    //   755: bipush 10
    //   757: if_icmpeq +176 -> 933
    //   760: new 385	java/util/HashMap
    //   763: dup
    //   764: aload 8
    //   766: arraylength
    //   767: invokespecial 1070	java/util/HashMap:<init>	(I)V
    //   770: astore 6
    //   772: aload 8
    //   774: arraylength
    //   775: istore_3
    //   776: iconst_0
    //   777: istore_2
    //   778: iload_2
    //   779: iload_3
    //   780: if_icmpge +48 -> 828
    //   783: aload 8
    //   785: iload_2
    //   786: aaload
    //   787: ldc 126
    //   789: invokevirtual 479	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   792: astore 9
    //   794: aload 9
    //   796: arraylength
    //   797: iconst_2
    //   798: if_icmpne +145 -> 943
    //   801: aload 9
    //   803: iconst_1
    //   804: aaload
    //   805: iconst_2
    //   806: invokevirtual 1054	java/lang/String:substring	(I)Ljava/lang/String;
    //   809: astore 13
    //   811: aload 6
    //   813: aload 9
    //   815: iconst_0
    //   816: aaload
    //   817: aload 13
    //   819: invokeinterface 1060 3 0
    //   824: pop
    //   825: goto +118 -> 943
    //   828: aload 6
    //   830: ldc_w 1072
    //   833: aload 12
    //   835: invokeinterface 1060 3 0
    //   840: pop
    //   841: aload 11
    //   843: new 9	com/uc/webview/export/internal/uc/wa/a$b
    //   846: dup
    //   847: aload_0
    //   848: aload 12
    //   850: aload 6
    //   852: invokespecial 1075	com/uc/webview/export/internal/uc/wa/a$b:<init>	(Lcom/uc/webview/export/internal/uc/wa/a;Ljava/lang/String;Ljava/util/Map;)V
    //   855: invokeinterface 357 2 0
    //   860: pop
    //   861: goto -724 -> 137
    //   864: aload 7
    //   866: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   869: aload 5
    //   871: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   874: aload 5
    //   876: invokestatic 449	com/uc/webview/export/cyclone/UCCyclone:close	(Ljava/io/Closeable;)V
    //   879: goto -211 -> 668
    //   882: astore 6
    //   884: aconst_null
    //   885: astore 5
    //   887: aconst_null
    //   888: astore 7
    //   890: goto -181 -> 709
    //   893: astore 6
    //   895: aload 5
    //   897: astore 8
    //   899: aconst_null
    //   900: astore 7
    //   902: goto -193 -> 709
    //   905: astore 5
    //   907: aconst_null
    //   908: astore 5
    //   910: aload 9
    //   912: astore 7
    //   914: goto -261 -> 653
    //   917: astore 6
    //   919: aload 5
    //   921: astore 6
    //   923: aload 9
    //   925: astore 7
    //   927: goto -274 -> 653
    //   930: goto -440 -> 490
    //   933: goto -796 -> 137
    //   936: iload_2
    //   937: iconst_1
    //   938: iadd
    //   939: istore_2
    //   940: goto -444 -> 496
    //   943: iload_2
    //   944: iconst_1
    //   945: iadd
    //   946: istore_2
    //   947: goto -169 -> 778
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	950	0	this	a
    //   136	153	1	n	int
    //   326	621	2	i1	int
    //   335	446	3	i2	int
    //   621	14	4	i3	int
    //   14	882	5	localObject1	Object
    //   905	1	5	localException1	Exception
    //   908	12	5	localObject2	Object
    //   74	530	6	localObject3	Object
    //   647	1	6	localException2	Exception
    //   651	33	6	localObject4	Object
    //   703	25	6	localObject5	Object
    //   770	81	6	localHashMap1	HashMap
    //   882	1	6	localObject6	Object
    //   893	1	6	localObject7	Object
    //   917	1	6	localException3	Exception
    //   921	1	6	localObject8	Object
    //   133	793	7	localObject9	Object
    //   71	827	8	localObject10	Object
    //   77	847	9	arrayOfString	String[]
    //   92	387	10	localHashMap2	HashMap
    //   101	741	11	localArrayList	ArrayList
    //   353	496	12	str	String
    //   570	248	13	localObject11	Object
    //   608	16	14	localMap	Map
    //   510	179	15	localObject12	Object
    // Exception table:
    //   from	to	target	type
    //   137	144	647	java/lang/Exception
    //   149	163	647	java/lang/Exception
    //   163	231	647	java/lang/Exception
    //   231	251	647	java/lang/Exception
    //   281	318	647	java/lang/Exception
    //   318	336	647	java/lang/Exception
    //   344	435	647	java/lang/Exception
    //   440	464	647	java/lang/Exception
    //   467	490	647	java/lang/Exception
    //   490	494	647	java/lang/Exception
    //   501	572	647	java/lang/Exception
    //   577	600	647	java/lang/Exception
    //   603	610	647	java/lang/Exception
    //   616	644	647	java/lang/Exception
    //   670	700	647	java/lang/Exception
    //   727	745	647	java/lang/Exception
    //   748	776	647	java/lang/Exception
    //   783	825	647	java/lang/Exception
    //   828	861	647	java/lang/Exception
    //   137	144	703	finally
    //   149	163	703	finally
    //   163	231	703	finally
    //   231	251	703	finally
    //   281	318	703	finally
    //   318	336	703	finally
    //   344	435	703	finally
    //   440	464	703	finally
    //   467	490	703	finally
    //   490	494	703	finally
    //   501	572	703	finally
    //   577	600	703	finally
    //   603	610	703	finally
    //   616	644	703	finally
    //   670	700	703	finally
    //   727	745	703	finally
    //   748	776	703	finally
    //   783	825	703	finally
    //   828	861	703	finally
    //   103	114	882	finally
    //   114	135	893	finally
    //   103	114	905	java/lang/Exception
    //   114	135	917	java/lang/Exception
  }
  
  public final void a(String paramString)
  {
    if (!b()) {
      return;
    }
    a(paramString, 0, 0, 1, null);
  }
  
  public final void a(String paramString1, int paramInt1, int paramInt2, int paramInt3, String paramString2)
  {
    Object localObject2 = new Date(System.currentTimeMillis());
    if (((Boolean)SDKFactory.invoke(10010, new Object[0])).booleanValue()) {}
    for (int n = ((Integer)SDKFactory.invoke(10020, new Object[0])).intValue();; n = 0)
    {
      int i1 = n;
      if (n != 2)
      {
        i1 = n;
        if (n != 0) {
          i1 = n * 10 + SDKFactory.o;
        }
      }
      String str = this.m.format((Date)localObject2) + "~" + i1;
      for (;;)
      {
        a localA;
        synchronized (this.h)
        {
          if (this.l == null) {
            this.l = new HashMap();
          }
          localA = (a)this.l.get(str);
          if (localA != null) {
            break label589;
          }
          localA = new a((byte)0);
          this.l.put(str, localA);
          localA.b.put("tm", this.g.format((Date)localObject2));
          switch (paramInt1)
          {
          case 0: 
            return;
            paramString2 = (Integer)localA.a.get(paramString1);
            if (paramString2 == null) {
              localA.a.put(paramString1, Integer.valueOf(paramInt3));
            }
            break;
          }
        }
        localA.a.put(paramString1, Integer.valueOf(paramString2.intValue() + paramInt3));
        continue;
        paramString2 = (Integer)localA.a.get(paramString1);
        label589:
        if ((paramString2 == null) || (Integer.MAX_VALUE - paramString2.intValue() >= paramInt3)) {
          if (paramString2 == null)
          {
            localA.a.put(paramString1, Integer.valueOf(paramInt3));
            localA.a.put(paramString1 + "_sc", Integer.valueOf(1));
          }
          else
          {
            localA.a.put(paramString1, Integer.valueOf(paramString2.intValue() + paramInt3));
            paramString2 = (Integer)localA.a.get(paramString1 + "_sc");
            localA.a.put(paramString1 + "_sc", Integer.valueOf(paramString2.intValue() + 1));
            continue;
            if (paramInt2 == 1)
            {
              localA.b.put(paramString1, paramString2);
            }
            else
            {
              localObject2 = (String)localA.b.get(paramString1);
              localA.b.put(paramString1, (String)localObject2 + "|" + paramString2);
              continue;
            }
          }
        }
      }
    }
  }
  
  public final void a(boolean paramBoolean)
  {
    if ((!b()) || (com.uc.webview.export.internal.utility.b.a((String)UCCore.getGlobalOption("process_private_data_dir_suffix")))) {}
    for (;;)
    {
      return;
      try
      {
        new b(this).start();
        if (paramBoolean)
        {
          Thread.sleep(20L);
          return;
        }
      }
      catch (Exception localException)
      {
        Log.e("SDKWaStat", "saveData", localException);
      }
    }
  }
  
  private final class a
  {
    Map<String, Integer> a = new HashMap();
    Map<String, String> b = new HashMap();
    
    private a() {}
    
    public final String toString()
    {
      return "Int Data: " + this.a.toString() + " String Data: " + this.b.toString();
    }
  }
  
  private final class b
  {
    String a;
    Map<String, String> b;
    
    public b(Map<String, String> paramMap)
    {
      this.a = paramMap;
      Object localObject;
      this.b = localObject;
    }
    
    public final String toString()
    {
      return "Key: " + this.a + " Data: " + this.b.toString();
    }
  }
}
