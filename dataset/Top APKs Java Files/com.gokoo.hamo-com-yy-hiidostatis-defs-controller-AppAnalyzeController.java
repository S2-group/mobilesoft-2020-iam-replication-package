package com.yy.hiidostatis.defs.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Environment;
import com.yy.hiidostatis.defs.interf.IConfigAPI;
import com.yy.hiidostatis.defs.interf.IStatisAPI;
import com.yy.hiidostatis.defs.obj.RecentAppInfo;
import com.yy.hiidostatis.inner.util.DefaultPreference;
import com.yy.hiidostatis.inner.util.FindEmulator;
import com.yy.hiidostatis.inner.util.Preference;
import com.yy.hiidostatis.inner.util.ThreadPool;
import com.yy.hiidostatis.inner.util.Util;
import com.yy.hiidostatis.inner.util.log.L;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
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
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        RecentAppInfo localRecentAppInfo = (RecentAppInfo)paramList.next();
        localStringBuffer.append(localRecentAppInfo.getName());
        localStringBuffer.append(";");
        localStringBuffer.append(localRecentAppInfo.getPkg());
        localStringBuffer.append(";");
        localStringBuffer.append(localRecentAppInfo.getLastModified());
        localStringBuffer.append("|");
      }
      if (localStringBuffer.length() > 0) {
        localStringBuffer.setLength(localStringBuffer.length() - 1);
      }
      return localStringBuffer.toString();
    }
    return null;
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
        if (paramString.equals(localMap.get("type")))
        {
          localStringBuffer.append(localMap.get("appname"));
          localStringBuffer.append("|");
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
        if (paramString.equals(localMap.get("type")))
        {
          localStringBuffer.append(localMap.get("appname"));
          localStringBuffer.append(";");
          localStringBuffer.append(localMap.get("appid"));
          localStringBuffer.append(";");
          localStringBuffer.append(localMap.get("firstInstallTime"));
          localStringBuffer.append(";");
          localStringBuffer.append(localMap.get("lastUpdateTime"));
          localStringBuffer.append("|");
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
    if (paramJSONObject != null) {}
    for (;;)
    {
      try
      {
        if (!paramJSONObject.has("appListConfig")) {
          return null;
        }
        JSONArray localJSONArray = paramJSONObject.getJSONArray("appListConfig");
        if (localJSONArray != null)
        {
          if (localJSONArray.length() == 0) {
            return null;
          }
          paramJSONObject = new ArrayList();
          try
          {
            Iterator localIterator = paramList.iterator();
            paramList = paramJSONObject;
            if (localIterator.hasNext())
            {
              Map localMap = (Map)localIterator.next();
              if (!paramString.equals(localMap.get("type"))) {
                continue;
              }
              paramList = new StringBuilder();
              paramList.append(localMap.get("appid"));
              paramList.append("");
              String str1 = paramList.toString();
              int i = 0;
              if (i < localJSONArray.length())
              {
                paramList = localJSONArray.getJSONObject(i);
                String str2 = paramList.getString("scheme");
                if (!paramList.has("isExact")) {
                  break label331;
                }
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append(paramList.get("isExact"));
                localStringBuilder.append("");
                paramList = localStringBuilder.toString();
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
                i += 1;
                continue;
              }
              continue;
              return null;
            }
          }
          catch (Throwable paramString)
          {
            paramList = paramJSONObject;
          }
        }
        return paramList;
      }
      catch (Throwable paramString)
      {
        paramList = null;
        L.warn(this, "getSpecial exception = %s", new Object[] { paramString });
      }
      return null;
      label331:
      paramList = "1";
    }
  }
  
  private long lastModify(File paramFile, long paramLong)
  {
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        long l2 = lastModify(paramFile[i], paramLong);
        long l1 = paramLong;
        if (l2 > paramLong) {
          l1 = l2;
        }
        i += 1;
        paramLong = l1;
      }
      return paramLong;
    }
    if (paramFile.lastModified() > paramLong) {
      return paramFile.lastModified();
    }
    return paramLong;
  }
  
  /* Error */
  private Map<String, RecentAppInfo> loadRecord()
  {
    // Byte code:
    //   0: invokestatic 283	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   3: astore_2
    //   4: new 207	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   11: astore_3
    //   12: aload_3
    //   13: aload_2
    //   14: invokevirtual 286	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   17: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: pop
    //   21: aload_3
    //   22: ldc_w 288
    //   25: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: pop
    //   29: new 261	java/io/File
    //   32: dup
    //   33: aload_3
    //   34: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   40: astore_2
    //   41: new 293	java/util/HashMap
    //   44: dup
    //   45: invokespecial 294	java/util/HashMap:<init>	()V
    //   48: astore_3
    //   49: aload_2
    //   50: invokevirtual 297	java/io/File:exists	()Z
    //   53: ifeq +187 -> 240
    //   56: new 299	java/io/FileInputStream
    //   59: dup
    //   60: aload_2
    //   61: invokespecial 302	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   64: astore_2
    //   65: aload_2
    //   66: invokestatic 306	com/yy/hiidostatis/inner/util/Util:readInputStream	(Ljava/io/InputStream;)[B
    //   69: astore 4
    //   71: new 191	org/json/JSONObject
    //   74: dup
    //   75: new 130	java/lang/String
    //   78: dup
    //   79: new 308	com/yy/hiidostatis/inner/util/cipher/AesCipher
    //   82: dup
    //   83: ldc 12
    //   85: invokevirtual 152	java/lang/String:getBytes	()[B
    //   88: invokespecial 311	com/yy/hiidostatis/inner/util/cipher/AesCipher:<init>	([B)V
    //   91: aload 4
    //   93: invokevirtual 315	com/yy/hiidostatis/inner/util/cipher/AesCipher:decrypt	([B)[B
    //   96: ldc_w 317
    //   99: invokespecial 320	java/lang/String:<init>	([BLjava/lang/String;)V
    //   102: invokevirtual 323	java/lang/String:trim	()Ljava/lang/String;
    //   105: invokespecial 324	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   108: astore 4
    //   110: aload 4
    //   112: ldc_w 326
    //   115: invokevirtual 330	org/json/JSONObject:getInt	(Ljava/lang/String;)I
    //   118: pop
    //   119: aload 4
    //   121: ldc_w 332
    //   124: invokevirtual 199	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   127: astore 4
    //   129: aload 4
    //   131: ifnull +51 -> 182
    //   134: iconst_0
    //   135: istore_1
    //   136: iload_1
    //   137: aload 4
    //   139: invokevirtual 202	org/json/JSONArray:length	()I
    //   142: if_icmpge +40 -> 182
    //   145: aload 4
    //   147: iload_1
    //   148: invokevirtual 221	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   151: invokestatic 336	com/yy/hiidostatis/defs/obj/RecentAppInfo:fromJson	(Lorg/json/JSONObject;)Lcom/yy/hiidostatis/defs/obj/RecentAppInfo;
    //   154: astore 5
    //   156: aload 5
    //   158: ifnull +17 -> 175
    //   161: aload_3
    //   162: aload 5
    //   164: invokevirtual 96	com/yy/hiidostatis/defs/obj/RecentAppInfo:getPkg	()Ljava/lang/String;
    //   167: aload 5
    //   169: invokeinterface 340 3 0
    //   174: pop
    //   175: iload_1
    //   176: iconst_1
    //   177: iadd
    //   178: istore_1
    //   179: goto -43 -> 136
    //   182: aload_2
    //   183: invokevirtual 343	java/io/FileInputStream:close	()V
    //   186: aload_3
    //   187: areturn
    //   188: astore_2
    //   189: aload_2
    //   190: invokevirtual 346	java/io/IOException:printStackTrace	()V
    //   193: aload_3
    //   194: areturn
    //   195: astore_3
    //   196: goto +9 -> 205
    //   199: goto +26 -> 225
    //   202: astore_3
    //   203: aconst_null
    //   204: astore_2
    //   205: aload_2
    //   206: ifnull +15 -> 221
    //   209: aload_2
    //   210: invokevirtual 343	java/io/FileInputStream:close	()V
    //   213: goto +8 -> 221
    //   216: astore_2
    //   217: aload_2
    //   218: invokevirtual 346	java/io/IOException:printStackTrace	()V
    //   221: aload_3
    //   222: athrow
    //   223: aconst_null
    //   224: astore_2
    //   225: aload_2
    //   226: ifnull +14 -> 240
    //   229: aload_2
    //   230: invokevirtual 343	java/io/FileInputStream:close	()V
    //   233: aload_3
    //   234: areturn
    //   235: astore_2
    //   236: aload_2
    //   237: invokevirtual 346	java/io/IOException:printStackTrace	()V
    //   240: aload_3
    //   241: areturn
    //   242: astore_2
    //   243: goto -20 -> 223
    //   246: astore 4
    //   248: goto -49 -> 199
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	251	0	this	AppAnalyzeController
    //   135	44	1	i	int
    //   3	180	2	localObject1	Object
    //   188	2	2	localIOException1	java.io.IOException
    //   204	6	2	localObject2	Object
    //   216	2	2	localIOException2	java.io.IOException
    //   224	6	2	localObject3	Object
    //   235	2	2	localIOException3	java.io.IOException
    //   242	1	2	localThrowable1	Throwable
    //   11	183	3	localObject4	Object
    //   195	1	3	localObject5	Object
    //   202	39	3	localMap	Map<String, RecentAppInfo>
    //   69	77	4	localObject6	Object
    //   246	1	4	localThrowable2	Throwable
    //   154	14	5	localRecentAppInfo	RecentAppInfo
    // Exception table:
    //   from	to	target	type
    //   182	186	188	java/io/IOException
    //   65	129	195	finally
    //   136	156	195	finally
    //   161	175	195	finally
    //   56	65	202	finally
    //   209	213	216	java/io/IOException
    //   229	233	235	java/io/IOException
    //   56	65	242	java/lang/Throwable
    //   65	129	246	java/lang/Throwable
    //   136	156	246	java/lang/Throwable
    //   161	175	246	java/lang/Throwable
  }
  
  private void reportAppList(List<Map<String, Object>> paramList, long paramLong, String paramString)
  {
    String str = getAppList(paramList, paramString);
    paramList = getAppList2(paramList, paramString);
    this.statisAPI.reportAppList(paramLong, paramString, str, paramList);
  }
  
  private void reportAppListBySplit(List<Map<String, Object>> paramList, long paramLong, String paramString)
  {
    if (paramList == null) {
      return;
    }
    ArrayList localArrayList = new ArrayList();
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
    if (localArrayList.size() > 0) {
      reportAppList(localArrayList, paramLong, paramString);
    }
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
      Object localObject2;
      if (localObject1 != null)
      {
        if ((((RecentAppInfo)localObject1).getName() != null) && (!((RecentAppInfo)localObject1).getName().isEmpty()))
        {
          localRecentAppInfo.setName(((RecentAppInfo)localObject1).getName());
        }
        else
        {
          localObject2 = localList1.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            Map localMap2 = (Map)((Iterator)localObject2).next();
            if (localMap2.get("appid").equals(localRecentAppInfo.getPkg())) {
              localRecentAppInfo.setName((String)localMap2.get("appname"));
            }
          }
        }
        localRecentAppInfo.setType(((RecentAppInfo)localObject1).getType());
        if (((RecentAppInfo)localObject1).getLastModified() != localRecentAppInfo.getLastModified()) {
          if (localRecentAppInfo.getType() == 1) {
            localArrayList2.add(localRecentAppInfo);
          } else {
            localArrayList1.add(localRecentAppInfo);
          }
        }
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
            if ((localObject1 != null) && (((String)localObject1).equals("1"))) {
              localRecentAppInfo.setType(1);
            } else {
              localRecentAppInfo.setType(2);
            }
          }
        }
        if (localRecentAppInfo.getType() == 1) {
          localArrayList2.add(localRecentAppInfo);
        } else if (localRecentAppInfo.getType() == 2) {
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
    //   0: new 207	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: aload_1
    //   10: invokevirtual 401	android/content/Context:getExternalCacheDir	()Ljava/io/File;
    //   13: invokevirtual 286	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   16: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: pop
    //   20: aload_3
    //   21: ldc_w 403
    //   24: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: pop
    //   28: aload_3
    //   29: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   32: astore 5
    //   34: invokestatic 283	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   37: astore_1
    //   38: new 207	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   45: astore_3
    //   46: aload_3
    //   47: aload_1
    //   48: invokevirtual 286	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   51: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: pop
    //   55: aload_3
    //   56: ldc_w 405
    //   59: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: new 261	java/io/File
    //   66: dup
    //   67: aload_3
    //   68: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   74: astore_1
    //   75: aload_1
    //   76: invokevirtual 297	java/io/File:exists	()Z
    //   79: ifeq +10 -> 89
    //   82: aload_1
    //   83: invokevirtual 264	java/io/File:isDirectory	()Z
    //   86: ifne +8 -> 94
    //   89: aload_1
    //   90: invokevirtual 408	java/io/File:mkdirs	()Z
    //   93: pop
    //   94: new 207	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 208	java/lang/StringBuilder:<init>	()V
    //   101: astore_3
    //   102: aload_3
    //   103: aload_1
    //   104: invokevirtual 286	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   107: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   110: pop
    //   111: aload_3
    //   112: ldc_w 410
    //   115: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: new 261	java/io/File
    //   122: dup
    //   123: aload_3
    //   124: invokevirtual 217	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   130: astore 6
    //   132: new 191	org/json/JSONObject
    //   135: dup
    //   136: invokespecial 411	org/json/JSONObject:<init>	()V
    //   139: astore_1
    //   140: aconst_null
    //   141: astore 4
    //   143: aconst_null
    //   144: astore_3
    //   145: aload_1
    //   146: ldc_w 326
    //   149: iconst_1
    //   150: invokevirtual 414	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   153: pop
    //   154: new 201	org/json/JSONArray
    //   157: dup
    //   158: invokespecial 415	org/json/JSONArray:<init>	()V
    //   161: astore 7
    //   163: aload_2
    //   164: invokeinterface 72 1 0
    //   169: astore_2
    //   170: aload_2
    //   171: invokeinterface 78 1 0
    //   176: ifeq +33 -> 209
    //   179: aload_2
    //   180: invokeinterface 82 1 0
    //   185: checkcast 84	com/yy/hiidostatis/defs/obj/RecentAppInfo
    //   188: invokevirtual 419	com/yy/hiidostatis/defs/obj/RecentAppInfo:toJson	()Lorg/json/JSONObject;
    //   191: astore 8
    //   193: aload 8
    //   195: ifnull -25 -> 170
    //   198: aload 7
    //   200: aload 8
    //   202: invokevirtual 422	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   205: pop
    //   206: goto -36 -> 170
    //   209: aload_1
    //   210: ldc_w 332
    //   213: aload 7
    //   215: invokevirtual 425	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   218: pop
    //   219: aload_1
    //   220: invokevirtual 426	org/json/JSONObject:toString	()Ljava/lang/String;
    //   223: astore_1
    //   224: new 308	com/yy/hiidostatis/inner/util/cipher/AesCipher
    //   227: dup
    //   228: ldc 12
    //   230: invokevirtual 152	java/lang/String:getBytes	()[B
    //   233: invokespecial 311	com/yy/hiidostatis/inner/util/cipher/AesCipher:<init>	([B)V
    //   236: aload_1
    //   237: ldc_w 428
    //   240: invokevirtual 431	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   243: invokevirtual 434	com/yy/hiidostatis/inner/util/cipher/AesCipher:encrypt	([B)[B
    //   246: astore_2
    //   247: new 436	java/io/FileOutputStream
    //   250: dup
    //   251: aload 5
    //   253: invokespecial 437	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   256: astore_1
    //   257: aload_1
    //   258: aload_2
    //   259: invokevirtual 440	java/io/FileOutputStream:write	([B)V
    //   262: aload_1
    //   263: invokevirtual 443	java/io/FileOutputStream:flush	()V
    //   266: aload_1
    //   267: invokevirtual 444	java/io/FileOutputStream:close	()V
    //   270: aload 6
    //   272: invokevirtual 447	java/io/File:delete	()Z
    //   275: pop
    //   276: new 261	java/io/File
    //   279: dup
    //   280: aload 5
    //   282: invokespecial 291	java/io/File:<init>	(Ljava/lang/String;)V
    //   285: aload 6
    //   287: invokevirtual 451	java/io/File:renameTo	(Ljava/io/File;)Z
    //   290: pop
    //   291: return
    //   292: astore_3
    //   293: aload_1
    //   294: astore_2
    //   295: aload_3
    //   296: astore_1
    //   297: goto +9 -> 306
    //   300: goto +24 -> 324
    //   303: astore_1
    //   304: aload_3
    //   305: astore_2
    //   306: aload_2
    //   307: ifnull +15 -> 322
    //   310: aload_2
    //   311: invokevirtual 444	java/io/FileOutputStream:close	()V
    //   314: goto +8 -> 322
    //   317: astore_2
    //   318: aload_2
    //   319: invokevirtual 346	java/io/IOException:printStackTrace	()V
    //   322: aload_1
    //   323: athrow
    //   324: aload_1
    //   325: ifnull +13 -> 338
    //   328: aload_1
    //   329: invokevirtual 444	java/io/FileOutputStream:close	()V
    //   332: return
    //   333: astore_1
    //   334: aload_1
    //   335: invokevirtual 346	java/io/IOException:printStackTrace	()V
    //   338: return
    //   339: astore_1
    //   340: aload 4
    //   342: astore_1
    //   343: goto -19 -> 324
    //   346: astore_2
    //   347: goto -47 -> 300
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	350	0	this	AppAnalyzeController
    //   0	350	1	paramContext	Context
    //   0	350	2	paramList	List<RecentAppInfo>
    //   7	138	3	localStringBuilder	StringBuilder
    //   292	13	3	localObject1	Object
    //   141	200	4	localObject2	Object
    //   32	249	5	str	String
    //   130	156	6	localFile	File
    //   161	53	7	localJSONArray	JSONArray
    //   191	10	8	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   257	270	292	finally
    //   145	170	303	finally
    //   170	193	303	finally
    //   198	206	303	finally
    //   209	257	303	finally
    //   270	291	303	finally
    //   310	314	317	java/io/IOException
    //   328	332	333	java/io/IOException
    //   145	170	339	java/lang/Throwable
    //   170	193	339	java/lang/Throwable
    //   198	206	339	java/lang/Throwable
    //   209	257	339	java/lang/Throwable
    //   270	291	339	java/lang/Throwable
    //   257	270	346	java/lang/Throwable
  }
  
  private List<RecentAppInfo> scanSdFileForLastModifiedTime(Context paramContext)
  {
    paramContext = Environment.getExternalStorageDirectory();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramContext.getAbsolutePath());
    ((StringBuilder)localObject).append("/Android/data");
    paramContext = new File(((StringBuilder)localObject).toString());
    if ((paramContext.exists()) && (paramContext.isDirectory()))
    {
      paramContext = paramContext.listFiles();
      localObject = new ArrayList(paramContext.length);
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
        ((List)localObject).add(localRecentAppInfo);
        i += 1;
      }
      return localObject;
    }
    return null;
  }
  
  private void sendRecentApp(List<RecentAppInfo> paramList1, List<RecentAppInfo> paramList2, long paramLong)
  {
    this.statisAPI.reportRecentAppList(paramLong, appInfoToString(paramList1), appInfoToString(paramList2));
  }
  
  private void startAppAnalyzeReport(final Context paramContext, final long paramLong)
  {
    paramContext = new Runnable()
    {
      public void run()
      {
        String str = AppAnalyzeController.this.getCurrDate();
        if (DefaultPreference.getPreference().getPrefString(paramContext, "PREF_KEY_APP_ANALYZE_REPORT_DATE", "").equals(str))
        {
          L.debug(AppAnalyzeController.class, "AppAnalyze is reported today[%s]，so not report again!", new Object[] { str });
          return;
        }
        Object localObject1 = AppAnalyzeController.this.mConfigAPI.getOnlineConfigs(paramContext, AppAnalyzeController.this.appKey);
        boolean bool1;
        int i;
        try
        {
          if (!Util.empty((String)localObject1))
          {
            localJSONObject = new JSONObject((String)localObject1);
            Object localObject2 = localJSONObject.get("onlineParams");
            localObject1 = null;
            if ((localObject2 instanceof JSONObject)) {
              localObject1 = localJSONObject.getJSONObject("onlineParams");
            }
            if ((localObject1 != null) && (((JSONObject)localObject1).has("hiido_applist_enable")))
            {
              localObject1 = ((JSONObject)localObject1).getString("hiido_applist_enable");
              if (localObject1 != null)
              {
                bool1 = ((String)localObject1).equals("true");
                if (bool1) {
                  i = 1;
                }
              }
            }
          }
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
          i = 0;
        }
        if (i == 0) {
          return;
        }
        JSONObject localJSONObject = AppAnalyzeController.this.mConfigAPI.getAppListConfig(paramContext, true);
        if (localJSONObject != null) {
          try
          {
            if (localJSONObject.has("enable"))
            {
              StringBuilder localStringBuilder1 = new StringBuilder();
              localStringBuilder1.append(localJSONObject.get("enable"));
              localStringBuilder1.append("");
              bool1 = "0".equals(localStringBuilder1.toString());
              if (bool1) {
                bool1 = false;
              }
            }
          }
          catch (JSONException localJSONException2)
          {
            bool1 = true;
          }
        }
        for (;;)
        {
          bool2 = false;
          break label470;
          bool1 = true;
          if (localJSONObject == null) {
            break;
          }
          try
          {
            if (!localJSONObject.has("sysAppEnable")) {
              break;
            }
            StringBuilder localStringBuilder2 = new StringBuilder();
            localStringBuilder2.append(localJSONObject.get("sysAppEnable"));
            localStringBuilder2.append("");
            bool2 = "1".equals(localStringBuilder2.toString());
            if (!bool2) {
              break;
            }
            bool2 = true;
          }
          catch (JSONException localJSONException3) {}
        }
        boolean bool2 = false;
        boolean bool4 = bool1;
        boolean bool3 = bool2;
        if (!bool2)
        {
          bool4 = bool1;
          bool3 = bool2;
          try
          {
            if (!FindEmulator.isEmulator(paramContext)) {
              break label492;
            }
            bool4 = bool1;
            bool3 = bool2;
            if (localJSONObject == null) {
              break label492;
            }
            bool4 = bool1;
            bool3 = bool2;
            if (!localJSONObject.has("emuSysAppEnable")) {
              break label492;
            }
            StringBuilder localStringBuilder3 = new StringBuilder();
            localStringBuilder3.append(localJSONObject.get("emuSysAppEnable"));
            localStringBuilder3.append("");
            bool4 = bool1;
            bool3 = bool2;
            if (!"1".equals(localStringBuilder3.toString())) {
              break label492;
            }
            L.brief("emuSysAppEnable is true && isEmulator is true", new Object[0]);
            bool3 = true;
            bool4 = bool1;
          }
          catch (JSONException localJSONException4) {}
          label470:
          L.error(AppAnalyzeController.class, "get json.enable exception: %s", new Object[] { localJSONException4 });
          bool3 = bool2;
          bool4 = bool1;
        }
        label492:
        L.debug(AppAnalyzeController.class, "AppAnalyze enable is %b，sysAppEnable is %b", new Object[] { Boolean.valueOf(bool4), Boolean.valueOf(bool3) });
        if (bool4)
        {
          List localList = AppAnalyzeController.this.getAllApps(paramContext);
          AppAnalyzeController.this.reportAppListBySplit(localList, paramLong, "2");
          if (bool3)
          {
            AppAnalyzeController.this.reportAppListBySplit(localList, paramLong, "1");
          }
          else
          {
            localList = AppAnalyzeController.this.getSpecial(localList, "1", localJSONObject);
            if ((localList != null) && (localList.size() > 0)) {
              AppAnalyzeController.this.reportAppListBySplit(localList, paramLong, "1");
            }
          }
        }
        DefaultPreference.getPreference().setPrefString(paramContext, "PREF_KEY_APP_ANALYZE_REPORT_DATE", str);
      }
    };
    ThreadPool.getPool().execute(paramContext, 10000L);
  }
  
  @SuppressLint({"NewApi"})
  public List<Map<String, Object>> getAllApps(Context paramContext)
  {
    localArrayList = new ArrayList();
    try
    {
      paramContext = paramContext.getPackageManager();
      List localList = paramContext.getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        HashMap localHashMap = new HashMap();
        Object localObject = (PackageInfo)localList.get(i);
        if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) > 0) {
          localHashMap.put("type", "1");
        } else {
          localHashMap.put("type", "2");
        }
        localHashMap.put("appname", paramContext.getApplicationLabel(((PackageInfo)localObject).applicationInfo).toString());
        localHashMap.put("appid", ((PackageInfo)localObject).applicationInfo.packageName);
        if (Build.VERSION.SDK_INT >= 9)
        {
          localHashMap.put("firstInstallTime", Long.valueOf(((PackageInfo)localObject).firstInstallTime / 1000L));
          localHashMap.put("lastUpdateTime_", Long.valueOf(((PackageInfo)localObject).lastUpdateTime / 1000L));
        }
        else
        {
          localHashMap.put("firstInstallTime", Integer.valueOf(0));
          localHashMap.put("lastUpdateTime_", Integer.valueOf(0));
        }
        try
        {
          localObject = ((PackageInfo)localObject).applicationInfo.publicSourceDir;
          localHashMap.put("dir", localObject);
          localHashMap.put("lastUpdateTime", Long.valueOf(new File((String)localObject).lastModified() / 1000L));
        }
        catch (Throwable localThrowable)
        {
          localHashMap.put("lastUpdateTime", Integer.valueOf(0));
          L.warn(AppAnalyzeController.class, "exception on get updatetime info: %s", new Object[] { localThrowable });
        }
        localArrayList.add(localHashMap);
        i += 1;
      }
      return localArrayList;
    }
    catch (Throwable paramContext)
    {
      L.warn(AppAnalyzeController.class, "exception on get All Apps info: %s", new Object[] { paramContext });
    }
  }
  
  public void reportAppAnalyze(Context paramContext, long paramLong)
  {
    startAppAnalyzeReport(paramContext, paramLong);
  }
  
  public void reportRecentAppAnalyze(final Context paramContext, final long paramLong)
  {
    ThreadPool.getPool().execute(new Runnable()
    {
      public void run()
      {
        try
        {
          AppAnalyzeController.this.reportRecentUsedApp(paramContext, paramLong);
          return;
        }
        catch (Throwable localThrowable)
        {
          L.errorOn(AppAnalyzeController.class, "reportRecentUsedApp exception:%s", new Object[] { localThrowable.getMessage() });
        }
      }
    }, 18000L);
  }
}
