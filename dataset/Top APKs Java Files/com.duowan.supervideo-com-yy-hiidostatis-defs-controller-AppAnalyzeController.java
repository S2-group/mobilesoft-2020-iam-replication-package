package com.yy.hiidostatis.defs.controller;

import android.content.Context;
import android.os.Environment;
import com.yy.hiidostatis.defs.interf.IConfigAPI;
import com.yy.hiidostatis.defs.interf.IStatisAPI;
import com.yy.hiidostatis.defs.obj.RecentAppInfo;
import com.yy.hiidostatis.inner.util.ThreadPool;
import com.yy.hiidostatis.inner.util.Util;
import com.yy.hiidostatis.inner.util.log.L;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class AppAnalyzeController
{
  private static final String AES_KEY = "*&Hjkfa{{07";
  private static final String PREF_KEY_APP_ANALYZE_REPORT_DATE = "PREF_KEY_APP_ANALYZE_REPORT_DATE";
  private static final int SPLIT_SIZE = 50;
  private String appKey;
  private IConfigAPI mConfigAPI;
  private IStatisAPI statisAPI;
  
  public AppAnalyzeController(IStatisAPI paramIStatisAPI, IConfigAPI paramIConfigAPI, String paramString)
  {
    this.statisAPI = paramIStatisAPI;
    this.mConfigAPI = paramIConfigAPI;
    this.appKey = paramString;
  }
  
  private String appInfoToString(List<RecentAppInfo> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject = null;
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        localObject = (RecentAppInfo)paramList.next();
        localStringBuffer.append(((RecentAppInfo)localObject).getName()).append(";").append(((RecentAppInfo)localObject).getPkg()).append(";").append(((RecentAppInfo)localObject).getLastModified()).append("|");
      }
      if (localStringBuffer.length() > 0) {
        localStringBuffer.setLength(localStringBuffer.length() - 1);
      }
      localObject = localStringBuffer.toString();
    }
    return localObject;
  }
  
  private String getAppList(List<Map<String, Object>> paramList, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Map localMap = (Map)paramList.next();
        if (paramString.equals(localMap.get("type"))) {
          localStringBuffer.append(localMap.get("appname")).append("|");
        }
      }
      if (localStringBuffer.length() > 0) {
        localStringBuffer.setLength(localStringBuffer.length() - 1);
      }
      L.debug(AppAnalyzeController.class, "type=%s,applist length=%d,applist bypes length=%d", new Object[] { paramString, Integer.valueOf(localStringBuffer.toString().length()), Integer.valueOf(localStringBuffer.toString().getBytes().length) });
      L.debug(AppAnalyzeController.class, "applist=%s", new Object[] { localStringBuffer.toString() });
    }
    return localStringBuffer.toString();
  }
  
  private String getAppList2(List<Map<String, Object>> paramList, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Map localMap = (Map)paramList.next();
        if (paramString.equals(localMap.get("type"))) {
          localStringBuffer.append(localMap.get("appname")).append(";").append(localMap.get("appid")).append(";").append(localMap.get("firstInstallTime")).append(";").append(localMap.get("lastUpdateTime")).append("|");
        }
      }
      if (localStringBuffer.length() > 0) {
        localStringBuffer.setLength(localStringBuffer.length() - 1);
      }
      L.debug(AppAnalyzeController.class, "type=%s,applist2 length=%d,applist2 bypes length=%d", new Object[] { paramString, Integer.valueOf(localStringBuffer.toString().length()), Integer.valueOf(localStringBuffer.toString().getBytes().length) });
      L.debug(AppAnalyzeController.class, "applist2=%s", new Object[] { localStringBuffer.toString() });
    }
    return localStringBuffer.toString();
  }
  
  private String getCurrDate()
  {
    return Util.formatDate("yyyyMMdd", System.currentTimeMillis());
  }
  
  private List<Map<String, Object>> getSpecial(List<Map<String, Object>> paramList, String paramString, JSONObject paramJSONObject)
  {
    Iterator localIterator = null;
    if (paramJSONObject != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!paramJSONObject.has("appListConfig")) {
          return null;
        }
        JSONArray localJSONArray = paramJSONObject.getJSONArray("appListConfig");
        if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
          break label304;
        }
        paramJSONObject = new ArrayList();
        try
        {
          localIterator = paramList.iterator();
          if (!localIterator.hasNext()) {
            continue;
          }
          Map localMap = (Map)localIterator.next();
          if (!paramString.equals(localMap.get("type"))) {
            continue;
          }
          String str1 = localMap.get("appid") + "";
          i = 0;
          if (i >= localJSONArray.length()) {
            continue;
          }
          paramList = localJSONArray.getJSONObject(i);
          String str2 = paramList.getString("scheme");
          if (!paramList.has("isExact")) {
            break label315;
          }
          paramList = paramList.get("isExact") + "";
          if ("0".equals(paramList))
          {
            if (str1.contains(str2))
            {
              paramJSONObject.add(localMap);
              L.brief("getSpecial isExact(0).pkgName:%s,tmpPkgName:%s", new Object[] { str1, str2 });
            }
          }
          else if (str1.equals(str2))
          {
            paramJSONObject.add(localMap);
            L.brief("getSpecial isExact(1).pkgName:%s,tmpPkgName:%s", new Object[] { str1, str2 });
          }
        }
        catch (Throwable paramString)
        {
          paramList = paramJSONObject;
        }
      }
      catch (Throwable paramString)
      {
        paramList = localIterator;
        continue;
      }
      L.warn(this, "getSpecial exception = %s", new Object[] { paramString });
      return paramList;
      return paramJSONObject;
      label304:
      return null;
      i += 1;
      continue;
      label315:
      paramList = "1";
    }
  }
  
  private long lastModify(File paramFile, long paramLong)
  {
    int i;
    long l;
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      i = 0;
      l = paramLong;
      if (i >= j) {
        break label74;
      }
      l = lastModify(paramFile[i], paramLong);
      if (l <= paramLong) {
        break label79;
      }
      paramLong = l;
    }
    label74:
    label79:
    for (;;)
    {
      i += 1;
      break;
      if (paramFile.lastModified() > paramLong)
      {
        l = paramFile.lastModified();
        return l;
      }
      return paramLong;
    }
  }
  
  /* Error */
  private Map<String, RecentAppInfo> loadRecord()
  {
    // Byte code:
    //   0: invokestatic 279	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   3: astore_2
    //   4: new 257	java/io/File
    //   7: dup
    //   8: new 203	java/lang/StringBuilder
    //   11: dup
    //   12: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   15: aload_2
    //   16: invokevirtual 282	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   19: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: ldc_w 284
    //   25: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   31: invokespecial 287	java/io/File:<init>	(Ljava/lang/String;)V
    //   34: astore_2
    //   35: new 289	java/util/HashMap
    //   38: dup
    //   39: invokespecial 290	java/util/HashMap:<init>	()V
    //   42: astore_3
    //   43: aload_2
    //   44: invokevirtual 293	java/io/File:exists	()Z
    //   47: ifeq +137 -> 184
    //   50: new 295	java/io/FileInputStream
    //   53: dup
    //   54: aload_2
    //   55: invokespecial 298	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   58: astore_2
    //   59: aload_2
    //   60: invokestatic 302	com/yy/hiidostatis/inner/util/Util:readInputStream	(Ljava/io/InputStream;)[B
    //   63: astore 4
    //   65: new 187	org/json/JSONObject
    //   68: dup
    //   69: new 126	java/lang/String
    //   72: dup
    //   73: new 304	com/yy/hiidostatis/inner/util/cipher/AesCipher
    //   76: dup
    //   77: ldc 8
    //   79: invokevirtual 148	java/lang/String:getBytes	()[B
    //   82: invokespecial 307	com/yy/hiidostatis/inner/util/cipher/AesCipher:<init>	([B)V
    //   85: aload 4
    //   87: invokevirtual 311	com/yy/hiidostatis/inner/util/cipher/AesCipher:decrypt	([B)[B
    //   90: ldc_w 313
    //   93: invokespecial 316	java/lang/String:<init>	([BLjava/lang/String;)V
    //   96: invokevirtual 319	java/lang/String:trim	()Ljava/lang/String;
    //   99: invokespecial 320	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   102: astore 4
    //   104: aload 4
    //   106: ldc_w 322
    //   109: invokevirtual 326	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   112: pop
    //   113: aload 4
    //   115: ldc_w 328
    //   118: invokevirtual 195	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   121: astore 4
    //   123: aload 4
    //   125: ifnull +51 -> 176
    //   128: iconst_0
    //   129: istore_1
    //   130: iload_1
    //   131: aload 4
    //   133: invokevirtual 198	org/json/JSONArray:length	()I
    //   136: if_icmpge +40 -> 176
    //   139: aload 4
    //   141: iload_1
    //   142: invokevirtual 217	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   145: invokestatic 332	com/yy/hiidostatis/defs/obj/RecentAppInfo:fromJson	(Lorg/json/JSONObject;)Lcom/yy/hiidostatis/defs/obj/RecentAppInfo;
    //   148: astore 5
    //   150: aload 5
    //   152: ifnull +17 -> 169
    //   155: aload_3
    //   156: aload 5
    //   158: invokevirtual 92	com/yy/hiidostatis/defs/obj/RecentAppInfo:getPkg	()Ljava/lang/String;
    //   161: aload 5
    //   163: invokeinterface 336 3 0
    //   168: pop
    //   169: iload_1
    //   170: iconst_1
    //   171: iadd
    //   172: istore_1
    //   173: goto -43 -> 130
    //   176: aload_2
    //   177: ifnull +7 -> 184
    //   180: aload_2
    //   181: invokevirtual 339	java/io/FileInputStream:close	()V
    //   184: aload_3
    //   185: areturn
    //   186: astore_2
    //   187: aload_2
    //   188: invokevirtual 342	java/io/IOException:printStackTrace	()V
    //   191: aload_3
    //   192: areturn
    //   193: astore_2
    //   194: aconst_null
    //   195: astore_2
    //   196: aload_2
    //   197: ifnull -13 -> 184
    //   200: aload_2
    //   201: invokevirtual 339	java/io/FileInputStream:close	()V
    //   204: aload_3
    //   205: areturn
    //   206: astore_2
    //   207: aload_2
    //   208: invokevirtual 342	java/io/IOException:printStackTrace	()V
    //   211: aload_3
    //   212: areturn
    //   213: astore_3
    //   214: aconst_null
    //   215: astore_2
    //   216: aload_2
    //   217: ifnull +7 -> 224
    //   220: aload_2
    //   221: invokevirtual 339	java/io/FileInputStream:close	()V
    //   224: aload_3
    //   225: athrow
    //   226: astore_2
    //   227: aload_2
    //   228: invokevirtual 342	java/io/IOException:printStackTrace	()V
    //   231: goto -7 -> 224
    //   234: astore_3
    //   235: goto -19 -> 216
    //   238: astore 4
    //   240: goto -44 -> 196
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	this	AppAnalyzeController
    //   129	44	1	i	int
    //   3	178	2	localObject1	Object
    //   186	2	2	localIOException1	java.io.IOException
    //   193	1	2	localThrowable1	Throwable
    //   195	6	2	localObject2	Object
    //   206	2	2	localIOException2	java.io.IOException
    //   215	6	2	localObject3	Object
    //   226	2	2	localIOException3	java.io.IOException
    //   42	170	3	localHashMap	java.util.HashMap
    //   213	12	3	localObject4	Object
    //   234	1	3	localObject5	Object
    //   63	77	4	localObject6	Object
    //   238	1	4	localThrowable2	Throwable
    //   148	14	5	localRecentAppInfo	RecentAppInfo
    // Exception table:
    //   from	to	target	type
    //   180	184	186	java/io/IOException
    //   50	59	193	java/lang/Throwable
    //   200	204	206	java/io/IOException
    //   50	59	213	finally
    //   220	224	226	java/io/IOException
    //   59	123	234	finally
    //   130	150	234	finally
    //   155	169	234	finally
    //   59	123	238	java/lang/Throwable
    //   130	150	238	java/lang/Throwable
    //   155	169	238	java/lang/Throwable
  }
  
  private void reportAppList(List<Map<String, Object>> paramList, long paramLong, String paramString)
  {
    String str = getAppList(paramList, paramString);
    paramList = getAppList2(paramList, paramString);
    this.statisAPI.reportAppList(paramLong, paramString, str, paramList);
  }
  
  private void reportAppListBySplit(List<Map<String, Object>> paramList, long paramLong, String paramString)
  {
    if (paramList == null) {}
    ArrayList localArrayList;
    do
    {
      return;
      localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Map localMap = (Map)paramList.next();
        if (paramString.equals(localMap.get("type")))
        {
          localArrayList.add(localMap);
          if (localArrayList.size() == 50)
          {
            reportAppList(localArrayList, paramLong, paramString);
            localArrayList.clear();
          }
        }
      }
    } while (localArrayList.size() <= 0);
    reportAppList(localArrayList, paramLong, paramString);
  }
  
  private void reportRecentUsedApp(Context paramContext, long paramLong)
  {
    List localList1 = getAllApps(paramContext);
    Map localMap1 = loadRecord();
    List localList2 = scanSdFileForLastModifiedTime(paramContext);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator = localList2.iterator();
    while (localIterator.hasNext())
    {
      RecentAppInfo localRecentAppInfo = (RecentAppInfo)localIterator.next();
      Object localObject1 = (RecentAppInfo)localMap1.get(localRecentAppInfo.getPkg());
      label119:
      Object localObject2;
      if (localObject1 != null)
      {
        if ((((RecentAppInfo)localObject1).getName() != null) && (!((RecentAppInfo)localObject1).getName().isEmpty())) {
          localRecentAppInfo.setName(((RecentAppInfo)localObject1).getName());
        }
        for (;;)
        {
          localRecentAppInfo.setType(((RecentAppInfo)localObject1).getType());
          if (((RecentAppInfo)localObject1).getLastModified() == localRecentAppInfo.getLastModified()) {
            break;
          }
          if (localRecentAppInfo.getType() != 1) {
            break label237;
          }
          localArrayList2.add(localRecentAppInfo);
          break;
          localObject2 = localList1.iterator();
          if (((Iterator)localObject2).hasNext())
          {
            Map localMap2 = (Map)((Iterator)localObject2).next();
            if (!localMap2.get("appid").equals(localRecentAppInfo.getPkg())) {
              break label119;
            }
            localRecentAppInfo.setName((String)localMap2.get("appname"));
          }
        }
        label237:
        localArrayList1.add(localRecentAppInfo);
      }
      else
      {
        localObject1 = localList1.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (Map)((Iterator)localObject1).next();
          if (((Map)localObject2).get("appid").equals(localRecentAppInfo.getPkg()))
          {
            localRecentAppInfo.setName((String)((Map)localObject2).get("appname"));
            localObject1 = (String)((Map)localObject2).get("type");
            if ((localObject1 == null) || (!((String)localObject1).equals("1"))) {
              break label375;
            }
            localRecentAppInfo.setType(1);
          }
        }
        for (;;)
        {
          if (localRecentAppInfo.getType() != 1) {
            break label384;
          }
          localArrayList2.add(localRecentAppInfo);
          break;
          label375:
          localRecentAppInfo.setType(2);
        }
        label384:
        if (localRecentAppInfo.getType() == 2) {
          localArrayList1.add(localRecentAppInfo);
        }
      }
    }
    sendRecentApp(localArrayList1, localArrayList2, paramLong);
    saveRecentApp(paramContext, localList2);
  }
  
  /* Error */
  private void saveRecentApp(Context paramContext, List<RecentAppInfo> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 203	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   9: aload_1
    //   10: invokevirtual 397	android/content/Context:getExternalCacheDir	()Ljava/io/File;
    //   13: invokevirtual 282	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   16: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: ldc_w 399
    //   22: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: astore 4
    //   30: invokestatic 279	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   33: astore_1
    //   34: new 257	java/io/File
    //   37: dup
    //   38: new 203	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   45: aload_1
    //   46: invokevirtual 282	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   49: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc_w 401
    //   55: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: invokespecial 287	java/io/File:<init>	(Ljava/lang/String;)V
    //   64: astore_1
    //   65: aload_1
    //   66: invokevirtual 293	java/io/File:exists	()Z
    //   69: ifeq +10 -> 79
    //   72: aload_1
    //   73: invokevirtual 260	java/io/File:isDirectory	()Z
    //   76: ifne +8 -> 84
    //   79: aload_1
    //   80: invokevirtual 404	java/io/File:mkdirs	()Z
    //   83: pop
    //   84: new 257	java/io/File
    //   87: dup
    //   88: new 203	java/lang/StringBuilder
    //   91: dup
    //   92: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   95: aload_1
    //   96: invokevirtual 282	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   99: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: ldc_w 406
    //   105: invokevirtual 212	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: invokespecial 287	java/io/File:<init>	(Ljava/lang/String;)V
    //   114: astore 5
    //   116: new 187	org/json/JSONObject
    //   119: dup
    //   120: invokespecial 407	org/json/JSONObject:<init>	()V
    //   123: astore_1
    //   124: aload_1
    //   125: ldc_w 322
    //   128: iconst_1
    //   129: invokevirtual 410	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   132: pop
    //   133: new 197	org/json/JSONArray
    //   136: dup
    //   137: invokespecial 411	org/json/JSONArray:<init>	()V
    //   140: astore 6
    //   142: aload_2
    //   143: invokeinterface 68 1 0
    //   148: astore_2
    //   149: aload_2
    //   150: invokeinterface 74 1 0
    //   155: ifeq +45 -> 200
    //   158: aload_2
    //   159: invokeinterface 78 1 0
    //   164: checkcast 80	com/yy/hiidostatis/defs/obj/RecentAppInfo
    //   167: invokevirtual 415	com/yy/hiidostatis/defs/obj/RecentAppInfo:toJson	()Lorg/json/JSONObject;
    //   170: astore 7
    //   172: aload 7
    //   174: ifnull -25 -> 149
    //   177: aload 6
    //   179: aload 7
    //   181: invokevirtual 418	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   184: pop
    //   185: goto -36 -> 149
    //   188: astore_1
    //   189: aconst_null
    //   190: astore_1
    //   191: aload_1
    //   192: ifnull +7 -> 199
    //   195: aload_1
    //   196: invokevirtual 421	java/io/FileOutputStream:close	()V
    //   199: return
    //   200: aload_1
    //   201: ldc_w 328
    //   204: aload 6
    //   206: invokevirtual 424	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   209: pop
    //   210: aload_1
    //   211: invokevirtual 425	org/json/JSONObject:toString	()Ljava/lang/String;
    //   214: astore_1
    //   215: new 304	com/yy/hiidostatis/inner/util/cipher/AesCipher
    //   218: dup
    //   219: ldc 8
    //   221: invokevirtual 148	java/lang/String:getBytes	()[B
    //   224: invokespecial 307	com/yy/hiidostatis/inner/util/cipher/AesCipher:<init>	([B)V
    //   227: aload_1
    //   228: ldc_w 427
    //   231: invokevirtual 430	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   234: invokevirtual 433	com/yy/hiidostatis/inner/util/cipher/AesCipher:encrypt	([B)[B
    //   237: astore_2
    //   238: new 420	java/io/FileOutputStream
    //   241: dup
    //   242: aload 4
    //   244: invokespecial 434	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   247: astore_1
    //   248: aload_1
    //   249: aload_2
    //   250: invokevirtual 437	java/io/FileOutputStream:write	([B)V
    //   253: aload_1
    //   254: invokevirtual 440	java/io/FileOutputStream:flush	()V
    //   257: aload_1
    //   258: invokevirtual 421	java/io/FileOutputStream:close	()V
    //   261: aload 5
    //   263: invokevirtual 443	java/io/File:delete	()Z
    //   266: pop
    //   267: new 257	java/io/File
    //   270: dup
    //   271: aload 4
    //   273: invokespecial 287	java/io/File:<init>	(Ljava/lang/String;)V
    //   276: aload 5
    //   278: invokevirtual 447	java/io/File:renameTo	(Ljava/io/File;)Z
    //   281: pop
    //   282: iconst_0
    //   283: ifeq -84 -> 199
    //   286: new 449	java/lang/NullPointerException
    //   289: dup
    //   290: invokespecial 450	java/lang/NullPointerException:<init>	()V
    //   293: athrow
    //   294: astore_1
    //   295: aload_1
    //   296: invokevirtual 342	java/io/IOException:printStackTrace	()V
    //   299: return
    //   300: astore_1
    //   301: aload_1
    //   302: invokevirtual 342	java/io/IOException:printStackTrace	()V
    //   305: return
    //   306: astore_2
    //   307: aload_3
    //   308: astore_1
    //   309: aload_1
    //   310: ifnull +7 -> 317
    //   313: aload_1
    //   314: invokevirtual 421	java/io/FileOutputStream:close	()V
    //   317: aload_2
    //   318: athrow
    //   319: astore_1
    //   320: aload_1
    //   321: invokevirtual 342	java/io/IOException:printStackTrace	()V
    //   324: goto -7 -> 317
    //   327: astore_2
    //   328: goto -19 -> 309
    //   331: astore_2
    //   332: goto -141 -> 191
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	335	0	this	AppAnalyzeController
    //   0	335	1	paramContext	Context
    //   0	335	2	paramList	List<RecentAppInfo>
    //   1	307	3	localObject	Object
    //   28	244	4	str	String
    //   114	163	5	localFile	File
    //   140	65	6	localJSONArray	JSONArray
    //   170	10	7	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   124	149	188	java/lang/Throwable
    //   149	172	188	java/lang/Throwable
    //   177	185	188	java/lang/Throwable
    //   200	248	188	java/lang/Throwable
    //   261	282	188	java/lang/Throwable
    //   286	294	294	java/io/IOException
    //   195	199	300	java/io/IOException
    //   124	149	306	finally
    //   149	172	306	finally
    //   177	185	306	finally
    //   200	248	306	finally
    //   261	282	306	finally
    //   313	317	319	java/io/IOException
    //   248	261	327	finally
    //   248	261	331	java/lang/Throwable
  }
  
  private List<RecentAppInfo> scanSdFileForLastModifiedTime(Context paramContext)
  {
    paramContext = Environment.getExternalStorageDirectory();
    paramContext = new File(paramContext.getAbsolutePath() + "/Android/data");
    if ((paramContext.exists()) && (paramContext.isDirectory()))
    {
      paramContext = paramContext.listFiles();
      ArrayList localArrayList = new ArrayList(paramContext.length);
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        File localFile = paramContext[i];
        long l2 = lastModify(localFile, 0L);
        long l1 = l2;
        if (l2 == 0L) {
          l1 = localFile.lastModified();
        }
        RecentAppInfo localRecentAppInfo = new RecentAppInfo();
        localRecentAppInfo.setPkg(localFile.getName());
        localRecentAppInfo.setLastModified(l1);
        localArrayList.add(localRecentAppInfo);
        i += 1;
      }
      return localArrayList;
    }
    return null;
  }
  
  private void sendRecentApp(List<RecentAppInfo> paramList1, List<RecentAppInfo> paramList2, long paramLong)
  {
    this.statisAPI.reportRecentAppList(paramLong, appInfoToString(paramList1), appInfoToString(paramList2));
  }
  
  private void startAppAnalyzeReport(Context paramContext, long paramLong)
  {
    paramContext = new AppAnalyzeController.2(this, paramContext, paramLong);
    ThreadPool.getPool().execute(paramContext, 10000L);
  }
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  public List<Map<String, Object>> getAllApps(Context paramContext)
  {
    // Byte code:
    //   0: new 200	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 201	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aload_1
    //   9: invokevirtual 496	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: astore_1
    //   13: aload_1
    //   14: iconst_0
    //   15: invokevirtual 502	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   18: astore 4
    //   20: iconst_0
    //   21: istore_2
    //   22: iload_2
    //   23: aload 4
    //   25: invokeinterface 357 1 0
    //   30: if_icmpge +246 -> 276
    //   33: new 289	java/util/HashMap
    //   36: dup
    //   37: invokespecial 290	java/util/HashMap:<init>	()V
    //   40: astore 5
    //   42: aload 4
    //   44: iload_2
    //   45: invokeinterface 505 2 0
    //   50: checkcast 507	android/content/pm/PackageInfo
    //   53: astore 6
    //   55: aload 6
    //   57: getfield 511	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   60: getfield 516	android/content/pm/ApplicationInfo:flags	I
    //   63: iconst_1
    //   64: iand
    //   65: ifle +178 -> 243
    //   68: aload 5
    //   70: ldc 120
    //   72: ldc -4
    //   74: invokeinterface 336 3 0
    //   79: pop
    //   80: aload 5
    //   82: ldc -124
    //   84: aload_1
    //   85: aload 6
    //   87: getfield 511	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   90: invokevirtual 520	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   93: invokeinterface 523 1 0
    //   98: invokeinterface 336 3 0
    //   103: pop
    //   104: aload 5
    //   106: ldc -96
    //   108: aload 6
    //   110: getfield 511	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   113: getfield 526	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   116: invokeinterface 336 3 0
    //   121: pop
    //   122: getstatic 531	android/os/Build$VERSION:SDK_INT	I
    //   125: bipush 9
    //   127: if_icmplt +151 -> 278
    //   130: aload 5
    //   132: ldc -94
    //   134: aload 6
    //   136: getfield 534	android/content/pm/PackageInfo:firstInstallTime	J
    //   139: ldc2_w 535
    //   142: ldiv
    //   143: invokestatic 541	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   146: invokeinterface 336 3 0
    //   151: pop
    //   152: aload 5
    //   154: ldc_w 543
    //   157: aload 6
    //   159: getfield 545	android/content/pm/PackageInfo:lastUpdateTime	J
    //   162: ldc2_w 535
    //   165: ldiv
    //   166: invokestatic 541	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   169: invokeinterface 336 3 0
    //   174: pop
    //   175: aload 6
    //   177: getfield 511	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   180: getfield 548	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   183: astore 6
    //   185: aload 5
    //   187: ldc_w 550
    //   190: aload 6
    //   192: invokeinterface 336 3 0
    //   197: pop
    //   198: aload 5
    //   200: ldc -92
    //   202: new 257	java/io/File
    //   205: dup
    //   206: aload 6
    //   208: invokespecial 287	java/io/File:<init>	(Ljava/lang/String;)V
    //   211: invokevirtual 269	java/io/File:lastModified	()J
    //   214: ldc2_w 535
    //   217: ldiv
    //   218: invokestatic 541	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   221: invokeinterface 336 3 0
    //   226: pop
    //   227: aload_3
    //   228: aload 5
    //   230: invokeinterface 237 2 0
    //   235: pop
    //   236: iload_2
    //   237: iconst_1
    //   238: iadd
    //   239: istore_2
    //   240: goto -218 -> 22
    //   243: aload 5
    //   245: ldc 120
    //   247: ldc_w 552
    //   250: invokeinterface 336 3 0
    //   255: pop
    //   256: goto -176 -> 80
    //   259: astore_1
    //   260: ldc 2
    //   262: ldc_w 554
    //   265: iconst_1
    //   266: anewarray 4	java/lang/Object
    //   269: dup
    //   270: iconst_0
    //   271: aload_1
    //   272: aastore
    //   273: invokestatic 250	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   276: aload_3
    //   277: areturn
    //   278: aload 5
    //   280: ldc -94
    //   282: iconst_0
    //   283: invokestatic 144	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   286: invokeinterface 336 3 0
    //   291: pop
    //   292: aload 5
    //   294: ldc_w 543
    //   297: iconst_0
    //   298: invokestatic 144	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   301: invokeinterface 336 3 0
    //   306: pop
    //   307: goto -132 -> 175
    //   310: astore 6
    //   312: aload 5
    //   314: ldc -92
    //   316: iconst_0
    //   317: invokestatic 144	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   320: invokeinterface 336 3 0
    //   325: pop
    //   326: ldc 2
    //   328: ldc_w 556
    //   331: iconst_1
    //   332: anewarray 4	java/lang/Object
    //   335: dup
    //   336: iconst_0
    //   337: aload 6
    //   339: aastore
    //   340: invokestatic 250	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   343: goto -116 -> 227
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	346	0	this	AppAnalyzeController
    //   0	346	1	paramContext	Context
    //   21	219	2	i	int
    //   7	270	3	localArrayList	ArrayList
    //   18	25	4	localList	List
    //   40	273	5	localHashMap	java.util.HashMap
    //   53	154	6	localObject	Object
    //   310	28	6	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   8	20	259	java/lang/Throwable
    //   22	80	259	java/lang/Throwable
    //   80	175	259	java/lang/Throwable
    //   227	236	259	java/lang/Throwable
    //   243	256	259	java/lang/Throwable
    //   278	307	259	java/lang/Throwable
    //   312	343	259	java/lang/Throwable
    //   175	227	310	java/lang/Throwable
  }
  
  public void reportAppAnalyze(Context paramContext, long paramLong)
  {
    startAppAnalyzeReport(paramContext, paramLong);
  }
  
  public void reportRecentAppAnalyze(Context paramContext, long paramLong)
  {
    ThreadPool.getPool().execute(new AppAnalyzeController.1(this, paramContext, paramLong), 18000L);
  }
}
