package com.ta.audid.upload;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ta.audid.Variables;
import com.ta.audid.utils.ByteUtils;
import com.ta.audid.utils.UtdidLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AppsResponse
{
  private static final String GET_APPLIST_URL = "https://audid-api.taobao.com/v2.0/a/audid/apps?version=";
  private static final String TAG_APPS = "apps";
  private static final String TAG_DATA = "data";
  private static final String TAG_VERSION = "version";
  private static final AppsResponse mInstance = new AppsResponse();
  private boolean bCollectFinished = false;
  public ArrayList<String> mAppList = new ArrayList();
  public int mVersion = 0;
  
  private AppsResponse() {}
  
  private List<String> getDeviceAppList()
  {
    Object localObject = Variables.getInstance().getContext().getPackageManager();
    localArrayList = new ArrayList();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      int i = 0;
      while (i < ((List)localObject).size())
      {
        PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localPackageInfo.packageName);
        }
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public static AppsResponse getInstance()
  {
    return mInstance;
  }
  
  /* Error */
  private String unGzip(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: new 107	java/io/ByteArrayInputStream
    //   6: dup
    //   7: aload_1
    //   8: invokespecial 110	java/io/ByteArrayInputStream:<init>	([B)V
    //   11: astore 5
    //   13: new 112	java/util/zip/GZIPInputStream
    //   16: dup
    //   17: aload 5
    //   19: invokespecial 115	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   22: astore 4
    //   24: sipush 1024
    //   27: newarray byte
    //   29: astore_3
    //   30: new 117	java/io/ByteArrayOutputStream
    //   33: dup
    //   34: invokespecial 118	java/io/ByteArrayOutputStream:<init>	()V
    //   37: astore_1
    //   38: aload_1
    //   39: astore 8
    //   41: aload 4
    //   43: astore 7
    //   45: aload 5
    //   47: astore 6
    //   49: aload 4
    //   51: aload_3
    //   52: iconst_0
    //   53: aload_3
    //   54: arraylength
    //   55: invokevirtual 122	java/util/zip/GZIPInputStream:read	([BII)I
    //   58: istore_2
    //   59: iload_2
    //   60: iconst_m1
    //   61: if_icmpeq +83 -> 144
    //   64: aload_1
    //   65: astore 8
    //   67: aload 4
    //   69: astore 7
    //   71: aload 5
    //   73: astore 6
    //   75: aload_1
    //   76: aload_3
    //   77: iconst_0
    //   78: iload_2
    //   79: invokevirtual 126	java/io/ByteArrayOutputStream:write	([BII)V
    //   82: goto -44 -> 38
    //   85: astore_3
    //   86: aload_1
    //   87: astore 8
    //   89: aload 4
    //   91: astore 7
    //   93: aload 5
    //   95: astore 6
    //   97: aload_3
    //   98: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   101: aload_1
    //   102: ifnull +7 -> 109
    //   105: aload_1
    //   106: invokevirtual 135	java/io/ByteArrayOutputStream:close	()V
    //   109: aload 4
    //   111: ifnull +8 -> 119
    //   114: aload 4
    //   116: invokevirtual 136	java/util/zip/GZIPInputStream:close	()V
    //   119: aload 9
    //   121: astore_1
    //   122: aload 5
    //   124: ifnull +11 -> 135
    //   127: aload 5
    //   129: invokevirtual 137	java/io/ByteArrayInputStream:close	()V
    //   132: aload 9
    //   134: astore_1
    //   135: new 139	java/lang/String
    //   138: dup
    //   139: aload_1
    //   140: invokespecial 140	java/lang/String:<init>	([B)V
    //   143: areturn
    //   144: aload_1
    //   145: astore 8
    //   147: aload 4
    //   149: astore 7
    //   151: aload 5
    //   153: astore 6
    //   155: aload_1
    //   156: invokevirtual 143	java/io/ByteArrayOutputStream:flush	()V
    //   159: aload_1
    //   160: astore 8
    //   162: aload 4
    //   164: astore 7
    //   166: aload 5
    //   168: astore 6
    //   170: aload_1
    //   171: invokevirtual 147	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   174: astore_3
    //   175: aload_1
    //   176: ifnull +7 -> 183
    //   179: aload_1
    //   180: invokevirtual 135	java/io/ByteArrayOutputStream:close	()V
    //   183: aload 4
    //   185: ifnull +8 -> 193
    //   188: aload 4
    //   190: invokevirtual 136	java/util/zip/GZIPInputStream:close	()V
    //   193: aload_3
    //   194: astore_1
    //   195: aload 5
    //   197: ifnull -62 -> 135
    //   200: aload 5
    //   202: invokevirtual 137	java/io/ByteArrayInputStream:close	()V
    //   205: aload_3
    //   206: astore_1
    //   207: goto -72 -> 135
    //   210: astore_1
    //   211: aload_1
    //   212: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   215: aload_3
    //   216: astore_1
    //   217: goto -82 -> 135
    //   220: astore_1
    //   221: aload_1
    //   222: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   225: goto -42 -> 183
    //   228: astore_1
    //   229: aload_1
    //   230: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   233: goto -40 -> 193
    //   236: astore_1
    //   237: aload_1
    //   238: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   241: goto -132 -> 109
    //   244: astore_1
    //   245: aload_1
    //   246: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   249: goto -130 -> 119
    //   252: astore_1
    //   253: aload_1
    //   254: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   257: aload 9
    //   259: astore_1
    //   260: goto -125 -> 135
    //   263: astore_1
    //   264: aconst_null
    //   265: astore_3
    //   266: aconst_null
    //   267: astore 4
    //   269: aconst_null
    //   270: astore 5
    //   272: aload_3
    //   273: ifnull +7 -> 280
    //   276: aload_3
    //   277: invokevirtual 135	java/io/ByteArrayOutputStream:close	()V
    //   280: aload 4
    //   282: ifnull +8 -> 290
    //   285: aload 4
    //   287: invokevirtual 136	java/util/zip/GZIPInputStream:close	()V
    //   290: aload 5
    //   292: ifnull +8 -> 300
    //   295: aload 5
    //   297: invokevirtual 137	java/io/ByteArrayInputStream:close	()V
    //   300: aload_1
    //   301: athrow
    //   302: astore_3
    //   303: aload_3
    //   304: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   307: goto -27 -> 280
    //   310: astore_3
    //   311: aload_3
    //   312: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   315: goto -25 -> 290
    //   318: astore_3
    //   319: aload_3
    //   320: invokestatic 132	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   323: goto -23 -> 300
    //   326: astore_1
    //   327: aconst_null
    //   328: astore_3
    //   329: aconst_null
    //   330: astore 4
    //   332: goto -60 -> 272
    //   335: astore_1
    //   336: aconst_null
    //   337: astore_3
    //   338: goto -66 -> 272
    //   341: astore_1
    //   342: aload 8
    //   344: astore_3
    //   345: aload 7
    //   347: astore 4
    //   349: aload 6
    //   351: astore 5
    //   353: goto -81 -> 272
    //   356: astore_3
    //   357: aconst_null
    //   358: astore_1
    //   359: aconst_null
    //   360: astore 4
    //   362: aconst_null
    //   363: astore 5
    //   365: goto -279 -> 86
    //   368: astore_3
    //   369: aconst_null
    //   370: astore_1
    //   371: aconst_null
    //   372: astore 4
    //   374: goto -288 -> 86
    //   377: astore_3
    //   378: aconst_null
    //   379: astore_1
    //   380: goto -294 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	383	0	this	AppsResponse
    //   0	383	1	paramArrayOfByte	byte[]
    //   58	21	2	i	int
    //   29	48	3	arrayOfByte1	byte[]
    //   85	13	3	localException1	Exception
    //   174	103	3	arrayOfByte2	byte[]
    //   302	2	3	localException2	Exception
    //   310	2	3	localIOException1	java.io.IOException
    //   318	2	3	localIOException2	java.io.IOException
    //   328	17	3	localObject1	Object
    //   356	1	3	localException3	Exception
    //   368	1	3	localException4	Exception
    //   377	1	3	localException5	Exception
    //   22	351	4	localObject2	Object
    //   11	353	5	localObject3	Object
    //   47	303	6	localObject4	Object
    //   43	303	7	localObject5	Object
    //   39	304	8	arrayOfByte3	byte[]
    //   1	257	9	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   49	59	85	java/lang/Exception
    //   75	82	85	java/lang/Exception
    //   155	159	85	java/lang/Exception
    //   170	175	85	java/lang/Exception
    //   200	205	210	java/io/IOException
    //   179	183	220	java/lang/Exception
    //   188	193	228	java/io/IOException
    //   105	109	236	java/lang/Exception
    //   114	119	244	java/io/IOException
    //   127	132	252	java/io/IOException
    //   3	13	263	finally
    //   276	280	302	java/lang/Exception
    //   285	290	310	java/io/IOException
    //   295	300	318	java/io/IOException
    //   13	24	326	finally
    //   24	38	335	finally
    //   49	59	341	finally
    //   75	82	341	finally
    //   97	101	341	finally
    //   155	159	341	finally
    //   170	175	341	finally
    //   3	13	356	java/lang/Exception
    //   13	24	368	java/lang/Exception
    //   24	38	377	java/lang/Exception
  }
  
  public String getAppsList()
  {
    int k = 0;
    for (;;)
    {
      int j;
      try
      {
        Object localObject1;
        if (this.mVersion <= 0)
        {
          localObject1 = "00000000";
          this.mAppList.clear();
          this.bCollectFinished = true;
          UtdidLogger.sd("", new Object[] { localObject1 });
          return localObject1;
        }
        j = 4;
        if ((this.mAppList != null) && (this.mAppList.size() > 0))
        {
          i = this.mAppList.size();
          j = 4 + ((i - 1) / 8 + 1);
          localObject1 = new byte[j];
          localObject1[0] = ((byte)(this.mVersion >>> 24));
          localObject1[1] = ((byte)(this.mVersion >> 16));
          localObject1[2] = ((byte)(this.mVersion >> 8));
          localObject1[3] = ((byte)this.mVersion);
          if (i > 0)
          {
            List localList = getDeviceAppList();
            UtdidLogger.sd("", new Object[] { localList });
            j = k;
            if (j < i)
            {
              if (!localList.contains(this.mAppList.get(j))) {
                break label240;
              }
              k = (byte)(1 << 7 - j % 8);
              int m = j / 8 + 4;
              localObject1[m] = ((byte)(k | localObject1[m]));
              break label240;
            }
          }
          localObject1 = ByteUtils.toHex((byte[])localObject1);
          continue;
        }
        int i = 0;
      }
      finally {}
      continue;
      label240:
      j += 1;
    }
  }
  
  /* Error */
  public void parseAppFile()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 44	com/ta/audid/upload/AppsResponse:bCollectFinished	Z
    //   8: istore_2
    //   9: iload_2
    //   10: ifeq +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 37	com/ta/audid/upload/AppsResponse:mVersion	I
    //   21: aload_0
    //   22: getfield 42	com/ta/audid/upload/AppsResponse:mAppList	Ljava/util/ArrayList;
    //   25: invokevirtual 154	java/util/ArrayList:clear	()V
    //   28: invokestatic 180	com/ta/audid/upload/UtdidKeyFile:readAppsFile	()Ljava/lang/String;
    //   31: astore_3
    //   32: aload_3
    //   33: invokestatic 186	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   36: istore_2
    //   37: iload_2
    //   38: ifne -25 -> 13
    //   41: new 188	org/json/JSONObject
    //   44: dup
    //   45: aload_3
    //   46: invokespecial 191	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   49: astore_3
    //   50: aload_3
    //   51: ldc 17
    //   53: invokevirtual 195	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   56: ifeq -43 -> 13
    //   59: aload_0
    //   60: aload_3
    //   61: ldc 17
    //   63: invokevirtual 199	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   66: putfield 37	com/ta/audid/upload/AppsResponse:mVersion	I
    //   69: ldc -100
    //   71: iconst_1
    //   72: anewarray 4	java/lang/Object
    //   75: dup
    //   76: iconst_0
    //   77: aload_0
    //   78: getfield 37	com/ta/audid/upload/AppsResponse:mVersion	I
    //   81: invokestatic 205	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   84: aastore
    //   85: invokestatic 162	com/ta/audid/utils/UtdidLogger:sd	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   88: aload_3
    //   89: ldc 11
    //   91: invokevirtual 195	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   94: ifeq -81 -> 13
    //   97: aload_3
    //   98: ldc 11
    //   100: invokevirtual 209	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   103: astore_3
    //   104: ldc -100
    //   106: iconst_1
    //   107: anewarray 4	java/lang/Object
    //   110: dup
    //   111: iconst_0
    //   112: aload_3
    //   113: aastore
    //   114: invokestatic 162	com/ta/audid/utils/UtdidLogger:sd	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   117: aload_3
    //   118: invokestatic 186	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   121: ifne -108 -> 13
    //   124: aload_0
    //   125: aload_3
    //   126: iconst_2
    //   127: invokestatic 215	com/ta/utdid2/android/utils/Base64:decode	(Ljava/lang/String;I)[B
    //   130: invokestatic 221	com/ta/audid/utils/RC4:rc4	([B)[B
    //   133: invokespecial 223	com/ta/audid/upload/AppsResponse:unGzip	([B)Ljava/lang/String;
    //   136: astore_3
    //   137: ldc -100
    //   139: iconst_1
    //   140: anewarray 4	java/lang/Object
    //   143: dup
    //   144: iconst_0
    //   145: aload_3
    //   146: aastore
    //   147: invokestatic 162	com/ta/audid/utils/UtdidLogger:sd	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   150: new 225	org/json/JSONArray
    //   153: dup
    //   154: aload_3
    //   155: invokespecial 226	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   158: astore_3
    //   159: iload_1
    //   160: aload_3
    //   161: invokevirtual 229	org/json/JSONArray:length	()I
    //   164: if_icmpge +23 -> 187
    //   167: aload_0
    //   168: getfield 42	com/ta/audid/upload/AppsResponse:mAppList	Ljava/util/ArrayList;
    //   171: aload_3
    //   172: iload_1
    //   173: invokevirtual 232	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   176: invokevirtual 233	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   179: pop
    //   180: iload_1
    //   181: iconst_1
    //   182: iadd
    //   183: istore_1
    //   184: goto -25 -> 159
    //   187: ldc -100
    //   189: iconst_4
    //   190: anewarray 4	java/lang/Object
    //   193: dup
    //   194: iconst_0
    //   195: ldc -22
    //   197: aastore
    //   198: dup
    //   199: iconst_1
    //   200: aload_0
    //   201: getfield 42	com/ta/audid/upload/AppsResponse:mAppList	Ljava/util/ArrayList;
    //   204: invokevirtual 163	java/util/ArrayList:size	()I
    //   207: invokestatic 205	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   210: aastore
    //   211: dup
    //   212: iconst_2
    //   213: ldc -20
    //   215: aastore
    //   216: dup
    //   217: iconst_3
    //   218: aload_0
    //   219: getfield 42	com/ta/audid/upload/AppsResponse:mAppList	Ljava/util/ArrayList;
    //   222: aastore
    //   223: invokestatic 162	com/ta/audid/utils/UtdidLogger:sd	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   226: goto -213 -> 13
    //   229: astore_3
    //   230: goto -217 -> 13
    //   233: astore_3
    //   234: aload_0
    //   235: monitorexit
    //   236: aload_3
    //   237: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	238	0	this	AppsResponse
    //   1	183	1	i	int
    //   8	30	2	bool	boolean
    //   31	141	3	localObject1	Object
    //   229	1	3	localException	Exception
    //   233	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   41	159	229	java/lang/Exception
    //   159	180	229	java/lang/Exception
    //   187	226	229	java/lang/Exception
    //   4	9	233	finally
    //   16	37	233	finally
    //   41	159	233	finally
    //   159	180	233	finally
    //   187	226	233	finally
  }
  
  void parseAppResult(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      if (!paramString.has("data")) {
        return;
      }
      paramString = paramString.getJSONObject("data");
      if ((paramString == null) || (!paramString.has("version"))) {
        return;
      }
      int i = paramString.getInt("version");
      UtdidLogger.sd("", new Object[] { Integer.valueOf(i) });
      if (i < 0)
      {
        UtdidKeyFile.writeAppsFile("");
        return;
      }
      if (!paramString.has("apps")) {
        return;
      }
      paramString = paramString.getString("apps");
      UtdidLogger.sd("", new Object[] { paramString });
      if (!TextUtils.isEmpty(paramString))
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("version", "" + i);
        localHashMap.put("apps", paramString);
        UtdidKeyFile.writeAppsFile(new JSONObject(localHashMap).toString());
        parseAppFile();
        return;
      }
    }
    catch (JSONException paramString)
    {
      UtdidLogger.d("", new Object[] { paramString });
      return;
    }
    UtdidKeyFile.writeAppsFile("");
  }
  
  public void requestAppList()
  {
    parseAppFile();
    String str1 = "https://audid-api.taobao.com/v2.0/a/audid/apps?version=" + this.mVersion;
    UtdidLogger.sd("", new Object[] { str1 });
    HttpResponse localHttpResponse = HttpUtils.sendRequest(str1, "", false);
    try
    {
      str1 = new String(localHttpResponse.data, "UTF-8");
      if (HttpResponse.checkSignature(str1, localHttpResponse.signature)) {
        parseAppResult(str1);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        UtdidLogger.d("", new Object[] { localException });
        String str2 = "";
      }
    }
  }
}
