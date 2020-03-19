package com.trendmicro.tmmssuite.sso;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.trendmicro.tmmssuite.core.sys.c;
import com.trendmicro.tmmssuite.encrypt.TmEncrypt;
import com.trendmicro.tmmssuite.util.i;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static final String a = i.a(a.class);
  
  public a() {}
  
  /* Error */
  public static void a(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 24	android/content/ContentValues
    //   3: dup
    //   4: invokespecial 25	android/content/ContentValues:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: ldc 27
    //   13: aload_1
    //   14: aload_1
    //   15: invokevirtual 33	java/lang/String:length	()I
    //   18: invokestatic 39	com/trendmicro/tmmssuite/encrypt/TmEncrypt:encryptStr	(Ljava/lang/String;I)Ljava/lang/String;
    //   21: invokevirtual 43	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   24: aload_0
    //   25: invokevirtual 49	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   28: bipush 8
    //   30: invokevirtual 55	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   33: invokeinterface 61 1 0
    //   38: astore 7
    //   40: aload 7
    //   42: invokeinterface 67 1 0
    //   47: ifeq +340 -> 387
    //   50: aload 7
    //   52: invokeinterface 71 1 0
    //   57: checkcast 73	android/content/pm/PackageInfo
    //   60: getfield 77	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   63: astore 8
    //   65: aload 8
    //   67: ifnull -27 -> 40
    //   70: aload 8
    //   72: arraylength
    //   73: istore_3
    //   74: iconst_0
    //   75: istore_2
    //   76: iload_2
    //   77: iload_3
    //   78: if_icmpge -38 -> 40
    //   81: aload 8
    //   83: iload_2
    //   84: aaload
    //   85: astore_1
    //   86: aload_1
    //   87: getfield 82	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   90: ifnull +195 -> 285
    //   93: aload_1
    //   94: getfield 82	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   97: ldc 84
    //   99: invokevirtual 88	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   102: ifeq +183 -> 285
    //   105: aload_1
    //   106: getfield 82	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   109: ldc 90
    //   111: invokevirtual 94	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   114: ifeq +171 -> 285
    //   117: new 96	java/lang/StringBuilder
    //   120: dup
    //   121: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   124: ldc 99
    //   126: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_1
    //   130: getfield 82	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   133: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: ldc 105
    //   138: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: ldc 27
    //   143: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokestatic 115	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   152: astore 5
    //   154: getstatic 15	com/trendmicro/tmmssuite/sso/a:a	Ljava/lang/String;
    //   157: new 96	java/lang/StringBuilder
    //   160: dup
    //   161: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   164: ldc 117
    //   166: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: aload 5
    //   171: invokevirtual 118	android/net/Uri:toString	()Ljava/lang/String;
    //   174: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 123	com/trendmicro/tmmssuite/core/sys/c:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   183: aload_0
    //   184: invokevirtual 127	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   187: aload 5
    //   189: iconst_1
    //   190: anewarray 29	java/lang/String
    //   193: dup
    //   194: iconst_0
    //   195: ldc 27
    //   197: aastore
    //   198: aconst_null
    //   199: aconst_null
    //   200: aconst_null
    //   201: invokevirtual 133	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   204: astore_1
    //   205: aload_1
    //   206: ifnull +69 -> 275
    //   209: aload_1
    //   210: invokeinterface 138 1 0
    //   215: ifeq +77 -> 292
    //   218: aload_0
    //   219: invokevirtual 127	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   222: aload 5
    //   224: aload 6
    //   226: aconst_null
    //   227: aconst_null
    //   228: invokevirtual 142	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   231: istore 4
    //   233: getstatic 15	com/trendmicro/tmmssuite/sso/a:a	Ljava/lang/String;
    //   236: new 96	java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   243: ldc -112
    //   245: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   248: aload 5
    //   250: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   253: ldc -107
    //   255: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: iload 4
    //   260: invokevirtual 152	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   263: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: invokestatic 155	com/trendmicro/tmmssuite/core/sys/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   269: aload_1
    //   270: invokeinterface 158 1 0
    //   275: aload_1
    //   276: ifnull +9 -> 285
    //   279: aload_1
    //   280: invokeinterface 158 1 0
    //   285: iload_2
    //   286: iconst_1
    //   287: iadd
    //   288: istore_2
    //   289: goto -213 -> 76
    //   292: aload_0
    //   293: invokevirtual 127	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   296: aload 5
    //   298: aload 6
    //   300: invokevirtual 162	android/content/ContentResolver:insert	(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;
    //   303: astore 9
    //   305: getstatic 15	com/trendmicro/tmmssuite/sso/a:a	Ljava/lang/String;
    //   308: new 96	java/lang/StringBuilder
    //   311: dup
    //   312: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   315: ldc -92
    //   317: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: aload 5
    //   322: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   325: ldc -90
    //   327: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: aload 9
    //   332: invokevirtual 147	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   335: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   338: invokestatic 155	com/trendmicro/tmmssuite/core/sys/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   341: goto -72 -> 269
    //   344: astore 5
    //   346: getstatic 15	com/trendmicro/tmmssuite/sso/a:a	Ljava/lang/String;
    //   349: ldc -88
    //   351: invokestatic 155	com/trendmicro/tmmssuite/core/sys/c:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   354: aload 5
    //   356: invokevirtual 171	java/lang/Exception:printStackTrace	()V
    //   359: aload_1
    //   360: ifnull -75 -> 285
    //   363: aload_1
    //   364: invokeinterface 158 1 0
    //   369: goto -84 -> 285
    //   372: astore_0
    //   373: aconst_null
    //   374: astore_1
    //   375: aload_1
    //   376: ifnull +9 -> 385
    //   379: aload_1
    //   380: invokeinterface 158 1 0
    //   385: aload_0
    //   386: athrow
    //   387: return
    //   388: astore_0
    //   389: goto -14 -> 375
    //   392: astore_0
    //   393: goto -18 -> 375
    //   396: astore 5
    //   398: aconst_null
    //   399: astore_1
    //   400: goto -54 -> 346
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	403	0	paramContext	Context
    //   0	403	1	paramString	String
    //   75	214	2	i	int
    //   73	6	3	j	int
    //   231	28	4	k	int
    //   152	169	5	localUri1	Uri
    //   344	11	5	localException1	Exception
    //   396	1	5	localException2	Exception
    //   7	292	6	localContentValues	ContentValues
    //   38	13	7	localIterator	Iterator
    //   63	19	8	arrayOfProviderInfo	ProviderInfo[]
    //   303	28	9	localUri2	Uri
    // Exception table:
    //   from	to	target	type
    //   209	269	344	java/lang/Exception
    //   269	275	344	java/lang/Exception
    //   292	341	344	java/lang/Exception
    //   183	205	372	finally
    //   209	269	388	finally
    //   269	275	388	finally
    //   292	341	388	finally
    //   346	359	392	finally
    //   183	205	396	java/lang/Exception
  }
  
  public static boolean a(Context paramContext)
  {
    Object localObject1 = paramContext.getContentResolver().query(SsoTokenProvider.a, new String[] { "token" }, null, null, null);
    Object localObject2;
    if (((Cursor)localObject1).moveToNext())
    {
      localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("token"));
      if ((localObject2 != null) && (!((String)localObject2).equals("")))
      {
        ((Cursor)localObject1).close();
        return true;
      }
    }
    try
    {
      localObject1 = paramContext.getPackageManager().getInstalledPackages(8).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((PackageInfo)((Iterator)localObject1).next()).providers;
        if (localObject2 != null)
        {
          int j = localObject2.length;
          int i = 0;
          while (i < j)
          {
            Uri localUri = localObject2[i];
            if ((localUri.authority != null) && (localUri.authority.contains("trend")) && (localUri.authority.endsWith(".provider.sso")) && (!localUri.authority.contains("tmmssuite")))
            {
              localUri = Uri.parse("content://" + localUri.authority + "/" + "token");
              Cursor localCursor = paramContext.getContentResolver().query(localUri, new String[] { "token" }, null, null, null);
              if (localCursor != null)
              {
                if (localCursor.moveToNext())
                {
                  String str = localCursor.getString(localCursor.getColumnIndex("token"));
                  c.c(a, "check SSO from " + localUri.toString() + "selfToken:" + str);
                  if (!TextUtils.isEmpty(str))
                  {
                    b(paramContext, str);
                    return true;
                  }
                }
                localCursor.close();
              }
            }
            i += 1;
          }
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      c.b(a, "SSO status error");
      paramContext.printStackTrace();
      return false;
    }
  }
  
  public static String b(Context paramContext)
  {
    Object localObject1 = paramContext.getContentResolver().query(SsoTokenProvider.a, new String[] { "token" }, null, null, null);
    Object localObject2;
    if (((Cursor)localObject1).moveToNext())
    {
      localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("token"));
      if ((localObject2 != null) && (!((String)localObject2).equals("")))
      {
        ((Cursor)localObject1).close();
        paramContext = TmEncrypt.decryptStr((String)localObject2);
        c.c(a, "get client token return:" + paramContext);
        return paramContext;
      }
    }
    for (;;)
    {
      try
      {
        localObject1 = paramContext.getPackageManager().getInstalledPackages(8).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          continue;
        }
        localObject2 = ((PackageInfo)((Iterator)localObject1).next()).providers;
        if (localObject2 == null) {
          continue;
        }
        int j = localObject2.length;
        i = 0;
        if (i >= j) {
          continue;
        }
        Uri localUri = localObject2[i];
        if ((!localUri.authority.contains("trend")) || (!localUri.authority.endsWith(".provider.sso")) || (localUri.authority.contains("tmmssuite"))) {
          continue;
        }
        localUri = Uri.parse("content://" + localUri.authority + "/" + "token");
        localCursor = paramContext.getContentResolver().query(localUri, new String[] { "token" }, null, null, null);
        if (localCursor == null) {
          continue;
        }
        if (!localCursor.moveToNext()) {
          continue;
        }
        String str = localCursor.getString(localCursor.getColumnIndex("token"));
        c.c(a, "check SSO from " + localUri.toString() + "selfToken:" + str);
        if ((str == null) || (TextUtils.isEmpty(str))) {
          continue;
        }
        b(paramContext, str);
        paramContext = TmEncrypt.decryptStr(str);
        try
        {
          c.c(a, "get client token return:" + paramContext);
          return paramContext;
        }
        catch (Exception localException1) {}
      }
      catch (Exception localException2)
      {
        int i;
        Cursor localCursor;
        paramContext = "";
        continue;
      }
      c.b(a, "SSO get error");
      localException1.printStackTrace();
      return paramContext;
      localCursor.close();
      i += 1;
    }
    c.c(a, "get client token return:" + "");
    return "";
  }
  
  public static void b(Context paramContext, String paramString)
  {
    c.b(a, "inputTokenContentLocalPorvider");
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("token", paramString);
    paramString = paramContext.getContentResolver().query(SsoTokenProvider.a, new String[] { "token" }, null, null, null);
    c.b(a, String.valueOf(paramString.getCount()));
    if (paramString.moveToNext())
    {
      paramContext.getContentResolver().update(SsoTokenProvider.a, localContentValues, null, null);
      return;
    }
    paramContext.getContentResolver().insert(SsoTokenProvider.a, localContentValues);
  }
}
