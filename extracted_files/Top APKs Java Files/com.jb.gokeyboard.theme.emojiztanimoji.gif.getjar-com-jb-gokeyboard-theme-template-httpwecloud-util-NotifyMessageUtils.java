package com.jb.gokeyboard.theme.template.httpwecloud.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.text.TextUtils;
import com.jb.gokeyboard.theme.template.ThemeApplication;
import com.jb.gokeyboard.theme.template.httpwecloud.bean.message.NotifyMessageBean;
import com.jb.gokeyboard.theme.template.util.LogPrint;
import java.io.File;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NotifyMessageUtils
{
  private static final String TAG = "NotifyMessageUtils";
  
  public NotifyMessageUtils() {}
  
  /* Error */
  public static void addLocalMessageId(int paramInt)
  {
    // Byte code:
    //   0: invokestatic 23	com/jb/gokeyboard/theme/template/httpwecloud/util/NotifyMessageUtils:getLocalMessageIds	()Ljava/util/List;
    //   3: invokeinterface 29 1 0
    //   8: astore_2
    //   9: aload_2
    //   10: invokeinterface 35 1 0
    //   15: ifeq +20 -> 35
    //   18: aload_2
    //   19: invokeinterface 39 1 0
    //   24: checkcast 41	java/lang/Integer
    //   27: invokevirtual 45	java/lang/Integer:intValue	()I
    //   30: iload_0
    //   31: if_icmpne -22 -> 9
    //   34: return
    //   35: ldc 47
    //   37: invokestatic 53	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   40: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   43: ifeq -9 -> 34
    //   46: new 61	java/io/File
    //   49: dup
    //   50: invokestatic 65	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   53: ldc 67
    //   55: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   58: astore_2
    //   59: aload_2
    //   60: invokevirtual 73	java/io/File:mkdirs	()Z
    //   63: pop
    //   64: aload_2
    //   65: invokevirtual 76	java/io/File:isDirectory	()Z
    //   68: ifeq -34 -> 34
    //   71: new 61	java/io/File
    //   74: dup
    //   75: aload_2
    //   76: ldc 78
    //   78: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   81: astore_2
    //   82: iconst_1
    //   83: istore_1
    //   84: aload_2
    //   85: invokevirtual 81	java/io/File:exists	()Z
    //   88: ifne +10 -> 98
    //   91: iconst_0
    //   92: istore_1
    //   93: aload_2
    //   94: invokevirtual 84	java/io/File:createNewFile	()Z
    //   97: pop
    //   98: aconst_null
    //   99: astore 5
    //   101: aconst_null
    //   102: astore_3
    //   103: aconst_null
    //   104: astore 4
    //   106: new 86	java/io/FileOutputStream
    //   109: dup
    //   110: aload_2
    //   111: iconst_1
    //   112: invokespecial 89	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   115: astore_2
    //   116: iload_1
    //   117: ifeq +46 -> 163
    //   120: aload_2
    //   121: new 91	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   128: ldc 94
    //   130: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: iload_0
    //   134: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   137: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   140: invokevirtual 108	java/lang/String:getBytes	()[B
    //   143: invokevirtual 112	java/io/FileOutputStream:write	([B)V
    //   146: aload_2
    //   147: invokevirtual 115	java/io/FileOutputStream:flush	()V
    //   150: aload_2
    //   151: ifnull -117 -> 34
    //   154: aload_2
    //   155: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   158: return
    //   159: astore_2
    //   160: return
    //   161: astore_2
    //   162: return
    //   163: aload_2
    //   164: new 91	java/lang/StringBuilder
    //   167: dup
    //   168: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   171: ldc 120
    //   173: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: iload_0
    //   177: invokevirtual 101	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   180: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   183: invokevirtual 108	java/lang/String:getBytes	()[B
    //   186: invokevirtual 112	java/io/FileOutputStream:write	([B)V
    //   189: goto -43 -> 146
    //   192: astore_3
    //   193: aload_2
    //   194: ifnull -160 -> 34
    //   197: aload_2
    //   198: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   201: return
    //   202: astore_2
    //   203: return
    //   204: astore_2
    //   205: aload 5
    //   207: astore_2
    //   208: aload_2
    //   209: ifnull -175 -> 34
    //   212: aload_2
    //   213: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   216: return
    //   217: astore_2
    //   218: return
    //   219: astore_2
    //   220: aload_3
    //   221: ifnull +7 -> 228
    //   224: aload_3
    //   225: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   228: aload_2
    //   229: athrow
    //   230: astore_3
    //   231: goto -3 -> 228
    //   234: astore 4
    //   236: aload_2
    //   237: astore_3
    //   238: aload 4
    //   240: astore_2
    //   241: goto -21 -> 220
    //   244: astore_3
    //   245: goto -37 -> 208
    //   248: astore_2
    //   249: aload 4
    //   251: astore_2
    //   252: goto -59 -> 193
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	255	0	paramInt	int
    //   83	34	1	i	int
    //   8	147	2	localObject1	Object
    //   159	1	2	localIOException1	java.io.IOException
    //   161	37	2	localIOException2	java.io.IOException
    //   202	1	2	localIOException3	java.io.IOException
    //   204	1	2	localIOException4	java.io.IOException
    //   207	6	2	localObject2	Object
    //   217	1	2	localIOException5	java.io.IOException
    //   219	18	2	localObject3	Object
    //   240	1	2	localObject4	Object
    //   248	1	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   251	1	2	localObject5	Object
    //   102	1	3	localObject6	Object
    //   192	33	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   230	1	3	localIOException6	java.io.IOException
    //   237	1	3	localObject7	Object
    //   244	1	3	localIOException7	java.io.IOException
    //   104	1	4	localObject8	Object
    //   234	16	4	localObject9	Object
    //   99	107	5	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   154	158	159	java/io/IOException
    //   93	98	161	java/io/IOException
    //   120	146	192	java/io/FileNotFoundException
    //   146	150	192	java/io/FileNotFoundException
    //   163	189	192	java/io/FileNotFoundException
    //   197	201	202	java/io/IOException
    //   106	116	204	java/io/IOException
    //   212	216	217	java/io/IOException
    //   106	116	219	finally
    //   224	228	230	java/io/IOException
    //   120	146	234	finally
    //   146	150	234	finally
    //   163	189	234	finally
    //   120	146	244	java/io/IOException
    //   146	150	244	java/io/IOException
    //   163	189	244	java/io/IOException
    //   106	116	248	java/io/FileNotFoundException
  }
  
  public static boolean checkBlackAndWhiteList(Context paramContext, NotifyMessageBean paramNotifyMessageBean)
  {
    Object localObject1 = paramNotifyMessageBean.getBlackList();
    Object localObject2 = paramNotifyMessageBean.getWhiteList();
    if ((TextUtils.isEmpty((CharSequence)localObject1)) && (TextUtils.isEmpty((CharSequence)localObject2))) {
      return true;
    }
    localObject2 = new ArrayList();
    localObject1 = new ArrayList();
    Object localObject3 = paramNotifyMessageBean.getBlackList().split("\\,");
    paramNotifyMessageBean = paramNotifyMessageBean.getWhiteList().split("\\,");
    int i = 0;
    while (i < localObject3.length)
    {
      if (!TextUtils.isEmpty(localObject3[i])) {
        ((List)localObject2).add(localObject3[i]);
      }
      i += 1;
    }
    i = 0;
    while (i < paramNotifyMessageBean.length)
    {
      if (!TextUtils.isEmpty(paramNotifyMessageBean[i])) {
        ((List)localObject1).add(paramNotifyMessageBean[i]);
      }
      i += 1;
    }
    paramContext = paramContext.getPackageManager();
    new ArrayList();
    paramContext = paramContext.getInstalledPackages(0);
    if ((((List)localObject2).size() > 0) && (((List)localObject1).size() > 0))
    {
      Iterator localIterator;
      do
      {
        paramNotifyMessageBean = paramContext.iterator();
        while (!localIterator.hasNext())
        {
          if (!paramNotifyMessageBean.hasNext()) {
            break;
          }
          localObject3 = ((PackageInfo)paramNotifyMessageBean.next()).packageName;
          localIterator = ((List)localObject2).iterator();
        }
      } while (!((String)localObject3).equals((String)localIterator.next()));
      return false;
      i = 0;
      paramContext = paramContext.iterator();
      for (;;)
      {
        if (!paramContext.hasNext()) {
          break label318;
        }
        paramNotifyMessageBean = ((PackageInfo)paramContext.next()).packageName;
        localObject2 = ((List)localObject1).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          if (!paramNotifyMessageBean.equals((String)((Iterator)localObject2).next())) {
            break;
          }
          i += 1;
        }
      }
      label318:
      return i == ((List)localObject1).size();
    }
    if (((List)localObject2).size() > 0)
    {
      do
      {
        paramContext = paramContext.iterator();
        while (!((Iterator)localObject1).hasNext())
        {
          if (!paramContext.hasNext()) {
            break;
          }
          paramNotifyMessageBean = ((PackageInfo)paramContext.next()).packageName;
          localObject1 = ((List)localObject2).iterator();
        }
      } while (!paramNotifyMessageBean.equals((String)((Iterator)localObject1).next()));
      return false;
      return true;
    }
    if (((List)localObject1).size() > 0)
    {
      i = 0;
      paramContext = paramContext.iterator();
      for (;;)
      {
        if (!paramContext.hasNext()) {
          break label490;
        }
        paramNotifyMessageBean = ((PackageInfo)paramContext.next()).packageName;
        localObject2 = ((List)localObject1).iterator();
        if (((Iterator)localObject2).hasNext())
        {
          if (!paramNotifyMessageBean.equals((String)((Iterator)localObject2).next())) {
            break;
          }
          i += 1;
        }
      }
      label490:
      return i == ((List)localObject1).size();
    }
    return false;
  }
  
  public static boolean checkDate(NotifyMessageBean paramNotifyMessageBean)
  {
    try
    {
      paramNotifyMessageBean = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramNotifyMessageBean.getEffectiveTo());
      if (paramNotifyMessageBean.before(new Date())) {
        return false;
      }
    }
    catch (ParseException paramNotifyMessageBean)
    {
      return false;
    }
    return true;
  }
  
  public static boolean checkMessageUri(Context paramContext, NotifyMessageBean paramNotifyMessageBean)
  {
    String str1 = paramNotifyMessageBean.getActionParam().trim();
    String str2 = str1.toLowerCase();
    if (TextUtils.equals(paramNotifyMessageBean.getActionType(), "2")) {
      if (str2.startsWith("market://details?id=")) {
        break label70;
      }
    }
    label70:
    do
    {
      do
      {
        return false;
        if (!TextUtils.equals(paramNotifyMessageBean.getActionType(), "1")) {
          break;
        }
      } while ((!str2.startsWith("http://")) && (!str2.startsWith("https://")));
      for (;;)
      {
        return true;
        if (TextUtils.equals(paramNotifyMessageBean.getActionType(), "3")) {
          if ((str1.startsWith("intent:#Intent;component=")) && (str1.endsWith(";end"))) {
            try
            {
              paramNotifyMessageBean = Intent.parseUri(str1, 1);
              int i = paramContext.getPackageManager().queryIntentActivities(paramNotifyMessageBean, 131072).size();
              if (i == 0) {
                return false;
              }
            }
            catch (URISyntaxException paramContext)
            {
              return false;
            }
          }
        }
      }
    } while (!str1.startsWith("#self#"));
    paramContext = str1.replace("#self#", "");
    try
    {
      Integer.parseInt(paramContext);
      return true;
    }
    catch (NumberFormatException paramContext) {}
    return false;
  }
  
  public static int generateIntentRequestCode()
  {
    String str = ThemeApplication.getThemePackageName();
    int j = 0;
    int i = 0;
    while (i < str.length())
    {
      j += str.charAt(i);
      i += 1;
    }
    return -j;
  }
  
  /* Error */
  private static boolean getFileLock()
    throws java.io.FileNotFoundException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: ldc 47
    //   4: invokestatic 53	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   7: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   10: ifeq +400 -> 410
    //   13: new 61	java/io/File
    //   16: dup
    //   17: invokestatic 65	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   20: ldc 67
    //   22: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   25: astore_2
    //   26: aload_2
    //   27: invokevirtual 73	java/io/File:mkdirs	()Z
    //   30: pop
    //   31: iload_1
    //   32: istore_0
    //   33: aload_2
    //   34: invokevirtual 76	java/io/File:isDirectory	()Z
    //   37: ifeq +133 -> 170
    //   40: new 61	java/io/File
    //   43: dup
    //   44: aload_2
    //   45: ldc_w 273
    //   48: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   51: astore_2
    //   52: aload_2
    //   53: invokevirtual 81	java/io/File:exists	()Z
    //   56: ifeq +238 -> 294
    //   59: new 275	java/io/FileInputStream
    //   62: dup
    //   63: aload_2
    //   64: invokespecial 278	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   67: astore_3
    //   68: sipush 1024
    //   71: newarray byte
    //   73: astore 4
    //   75: aload_3
    //   76: aload 4
    //   78: invokevirtual 282	java/io/FileInputStream:read	([B)I
    //   81: pop
    //   82: new 55	java/lang/String
    //   85: dup
    //   86: aload 4
    //   88: invokespecial 284	java/lang/String:<init>	([B)V
    //   91: astore_3
    //   92: new 174	java/text/SimpleDateFormat
    //   95: dup
    //   96: ldc -80
    //   98: invokespecial 179	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   101: aload_3
    //   102: invokevirtual 186	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   105: astore 4
    //   107: new 188	java/util/Date
    //   110: dup
    //   111: invokespecial 189	java/util/Date:<init>	()V
    //   114: astore_3
    //   115: aload_3
    //   116: invokevirtual 288	java/util/Date:getTime	()J
    //   119: aload 4
    //   121: invokevirtual 288	java/util/Date:getTime	()J
    //   124: lsub
    //   125: ldc2_w 289
    //   128: lcmp
    //   129: ifle +43 -> 172
    //   132: new 86	java/io/FileOutputStream
    //   135: dup
    //   136: aload_2
    //   137: invokespecial 291	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   140: astore 4
    //   142: aload 4
    //   144: new 174	java/text/SimpleDateFormat
    //   147: dup
    //   148: ldc -80
    //   150: invokespecial 179	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   153: aload_3
    //   154: invokevirtual 295	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   157: invokevirtual 108	java/lang/String:getBytes	()[B
    //   160: invokevirtual 112	java/io/FileOutputStream:write	([B)V
    //   163: aload 4
    //   165: invokevirtual 115	java/io/FileOutputStream:flush	()V
    //   168: iconst_1
    //   169: istore_0
    //   170: iload_0
    //   171: ireturn
    //   172: iconst_0
    //   173: istore_0
    //   174: goto -4 -> 170
    //   177: astore_3
    //   178: new 174	java/text/SimpleDateFormat
    //   181: dup
    //   182: ldc -80
    //   184: invokespecial 179	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   187: new 188	java/util/Date
    //   190: dup
    //   191: invokespecial 189	java/util/Date:<init>	()V
    //   194: invokevirtual 295	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   197: astore 6
    //   199: aconst_null
    //   200: astore 5
    //   202: aconst_null
    //   203: astore_3
    //   204: aconst_null
    //   205: astore 4
    //   207: new 86	java/io/FileOutputStream
    //   210: dup
    //   211: aload_2
    //   212: invokespecial 291	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   215: astore_2
    //   216: aload_2
    //   217: aload 6
    //   219: invokevirtual 108	java/lang/String:getBytes	()[B
    //   222: invokevirtual 112	java/io/FileOutputStream:write	([B)V
    //   225: aload_2
    //   226: invokevirtual 115	java/io/FileOutputStream:flush	()V
    //   229: aload_2
    //   230: ifnull +7 -> 237
    //   233: aload_2
    //   234: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   237: iconst_1
    //   238: ireturn
    //   239: astore_2
    //   240: iconst_1
    //   241: ireturn
    //   242: astore_2
    //   243: aload 4
    //   245: astore_2
    //   246: iload_1
    //   247: istore_0
    //   248: aload_2
    //   249: ifnull -79 -> 170
    //   252: aload_2
    //   253: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   256: iconst_0
    //   257: ireturn
    //   258: astore_2
    //   259: iconst_0
    //   260: ireturn
    //   261: astore_2
    //   262: aload 5
    //   264: astore_2
    //   265: iload_1
    //   266: istore_0
    //   267: aload_2
    //   268: ifnull -98 -> 170
    //   271: aload_2
    //   272: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   275: iconst_0
    //   276: ireturn
    //   277: astore_2
    //   278: iconst_0
    //   279: ireturn
    //   280: astore_2
    //   281: aload_3
    //   282: ifnull +7 -> 289
    //   285: aload_3
    //   286: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   289: aload_2
    //   290: athrow
    //   291: astore_2
    //   292: aload_2
    //   293: athrow
    //   294: aconst_null
    //   295: astore 5
    //   297: aconst_null
    //   298: astore_3
    //   299: aconst_null
    //   300: astore 4
    //   302: aload_2
    //   303: invokevirtual 84	java/io/File:createNewFile	()Z
    //   306: pop
    //   307: new 86	java/io/FileOutputStream
    //   310: dup
    //   311: aload_2
    //   312: invokespecial 291	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   315: astore_2
    //   316: aload_2
    //   317: new 174	java/text/SimpleDateFormat
    //   320: dup
    //   321: ldc -80
    //   323: invokespecial 179	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   326: new 188	java/util/Date
    //   329: dup
    //   330: invokespecial 189	java/util/Date:<init>	()V
    //   333: invokevirtual 295	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   336: invokevirtual 108	java/lang/String:getBytes	()[B
    //   339: invokevirtual 112	java/io/FileOutputStream:write	([B)V
    //   342: aload_2
    //   343: invokevirtual 115	java/io/FileOutputStream:flush	()V
    //   346: iconst_1
    //   347: istore_0
    //   348: aload_2
    //   349: ifnull -179 -> 170
    //   352: aload_2
    //   353: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   356: iconst_1
    //   357: ireturn
    //   358: astore_2
    //   359: iconst_1
    //   360: ireturn
    //   361: astore_2
    //   362: aload 4
    //   364: astore_2
    //   365: iload_1
    //   366: istore_0
    //   367: aload_2
    //   368: ifnull -198 -> 170
    //   371: aload_2
    //   372: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   375: iconst_0
    //   376: ireturn
    //   377: astore_2
    //   378: iconst_0
    //   379: ireturn
    //   380: astore_2
    //   381: aload 5
    //   383: astore_2
    //   384: iload_1
    //   385: istore_0
    //   386: aload_2
    //   387: ifnull -217 -> 170
    //   390: aload_2
    //   391: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   394: iconst_0
    //   395: ireturn
    //   396: astore_2
    //   397: iconst_0
    //   398: ireturn
    //   399: astore_2
    //   400: aload_3
    //   401: ifnull +7 -> 408
    //   404: aload_3
    //   405: invokevirtual 118	java/io/FileOutputStream:close	()V
    //   408: aload_2
    //   409: athrow
    //   410: new 19	java/io/FileNotFoundException
    //   413: dup
    //   414: ldc_w 297
    //   417: invokespecial 298	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   420: athrow
    //   421: astore_3
    //   422: goto -133 -> 289
    //   425: astore_3
    //   426: goto -18 -> 408
    //   429: astore 4
    //   431: aload_2
    //   432: astore_3
    //   433: aload 4
    //   435: astore_2
    //   436: goto -36 -> 400
    //   439: astore_3
    //   440: goto -56 -> 384
    //   443: astore_3
    //   444: goto -79 -> 365
    //   447: astore_2
    //   448: goto -156 -> 292
    //   451: astore_2
    //   452: goto -160 -> 292
    //   455: astore 4
    //   457: aload_2
    //   458: astore_3
    //   459: aload 4
    //   461: astore_2
    //   462: goto -181 -> 281
    //   465: astore_3
    //   466: goto -201 -> 265
    //   469: astore_3
    //   470: goto -224 -> 246
    //   473: astore_3
    //   474: goto -296 -> 178
    //   477: astore_3
    //   478: goto -300 -> 178
    //   481: astore_2
    //   482: iconst_0
    //   483: ireturn
    //   484: astore_2
    //   485: iconst_0
    //   486: ireturn
    //   487: astore_2
    //   488: iconst_0
    //   489: ireturn
    //   490: astore_2
    //   491: iconst_0
    //   492: ireturn
    //   493: astore_2
    //   494: iconst_0
    //   495: ireturn
    //   496: astore_2
    //   497: iconst_0
    //   498: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   32	354	0	bool1	boolean
    //   1	384	1	bool2	boolean
    //   25	209	2	localObject1	Object
    //   239	1	2	localIOException1	java.io.IOException
    //   242	1	2	localFileNotFoundException1	java.io.FileNotFoundException
    //   245	8	2	localObject2	Object
    //   258	1	2	localIOException2	java.io.IOException
    //   261	1	2	localIOException3	java.io.IOException
    //   264	8	2	localObject3	Object
    //   277	1	2	localIOException4	java.io.IOException
    //   280	10	2	localObject4	Object
    //   291	21	2	localFile	File
    //   315	38	2	localFileOutputStream	java.io.FileOutputStream
    //   358	1	2	localIOException5	java.io.IOException
    //   361	1	2	localFileNotFoundException2	java.io.FileNotFoundException
    //   364	8	2	localObject5	Object
    //   377	1	2	localIOException6	java.io.IOException
    //   380	1	2	localIOException7	java.io.IOException
    //   383	8	2	localObject6	Object
    //   396	1	2	localIOException8	java.io.IOException
    //   399	33	2	localObject7	Object
    //   435	1	2	localObject8	Object
    //   447	1	2	localObject9	Object
    //   451	7	2	localObject10	Object
    //   461	1	2	localObject11	Object
    //   481	1	2	localIOException9	java.io.IOException
    //   484	1	2	localIOException10	java.io.IOException
    //   487	1	2	localIOException11	java.io.IOException
    //   490	1	2	localFileNotFoundException3	java.io.FileNotFoundException
    //   493	1	2	localFileNotFoundException4	java.io.FileNotFoundException
    //   496	1	2	localFileNotFoundException5	java.io.FileNotFoundException
    //   67	87	3	localObject12	Object
    //   177	1	3	localParseException1	ParseException
    //   203	202	3	localObject13	Object
    //   421	1	3	localIOException12	java.io.IOException
    //   425	1	3	localIOException13	java.io.IOException
    //   432	1	3	localObject14	Object
    //   439	1	3	localIOException14	java.io.IOException
    //   443	1	3	localFileNotFoundException6	java.io.FileNotFoundException
    //   458	1	3	localObject15	Object
    //   465	1	3	localIOException15	java.io.IOException
    //   469	1	3	localFileNotFoundException7	java.io.FileNotFoundException
    //   473	1	3	localParseException2	ParseException
    //   477	1	3	localParseException3	ParseException
    //   73	290	4	localObject16	Object
    //   429	5	4	localObject17	Object
    //   455	5	4	localObject18	Object
    //   200	182	5	localObject19	Object
    //   197	21	6	str	String
    // Exception table:
    //   from	to	target	type
    //   59	68	177	java/text/ParseException
    //   233	237	239	java/io/IOException
    //   207	216	242	java/io/FileNotFoundException
    //   252	256	258	java/io/IOException
    //   207	216	261	java/io/IOException
    //   271	275	277	java/io/IOException
    //   207	216	280	finally
    //   59	68	291	finally
    //   178	199	291	finally
    //   233	237	291	finally
    //   252	256	291	finally
    //   271	275	291	finally
    //   285	289	291	finally
    //   289	291	291	finally
    //   352	356	358	java/io/IOException
    //   302	316	361	java/io/FileNotFoundException
    //   371	375	377	java/io/IOException
    //   302	316	380	java/io/IOException
    //   390	394	396	java/io/IOException
    //   302	316	399	finally
    //   285	289	421	java/io/IOException
    //   404	408	425	java/io/IOException
    //   316	346	429	finally
    //   316	346	439	java/io/IOException
    //   316	346	443	java/io/FileNotFoundException
    //   68	142	447	finally
    //   142	168	451	finally
    //   216	229	455	finally
    //   216	229	465	java/io/IOException
    //   216	229	469	java/io/FileNotFoundException
    //   68	142	473	java/text/ParseException
    //   142	168	477	java/text/ParseException
    //   59	68	481	java/io/IOException
    //   68	142	484	java/io/IOException
    //   142	168	487	java/io/IOException
    //   59	68	490	java/io/FileNotFoundException
    //   68	142	493	java/io/FileNotFoundException
    //   142	168	496	java/io/FileNotFoundException
  }
  
  /* Error */
  public static List<Integer> getLocalMessageIds()
  {
    // Byte code:
    //   0: new 138	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 139	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: ldc 47
    //   10: invokestatic 53	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   13: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   16: ifeq +193 -> 209
    //   19: new 61	java/io/File
    //   22: dup
    //   23: invokestatic 65	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   26: ldc 67
    //   28: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   31: astore_3
    //   32: aload_3
    //   33: invokevirtual 73	java/io/File:mkdirs	()Z
    //   36: pop
    //   37: aload_3
    //   38: invokevirtual 76	java/io/File:isDirectory	()Z
    //   41: ifeq +168 -> 209
    //   44: new 61	java/io/File
    //   47: dup
    //   48: aload_3
    //   49: ldc 78
    //   51: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   54: astore_3
    //   55: aload_3
    //   56: invokevirtual 81	java/io/File:exists	()Z
    //   59: ifeq +150 -> 209
    //   62: new 275	java/io/FileInputStream
    //   65: dup
    //   66: aload_3
    //   67: invokespecial 278	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   70: astore_3
    //   71: sipush 1024
    //   74: newarray byte
    //   76: astore 4
    //   78: new 91	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   85: astore 5
    //   87: aload_3
    //   88: aload 4
    //   90: iconst_0
    //   91: sipush 1024
    //   94: invokevirtual 302	java/io/FileInputStream:read	([BII)I
    //   97: istore_0
    //   98: iload_0
    //   99: iconst_m1
    //   100: if_icmpeq +23 -> 123
    //   103: aload 5
    //   105: new 55	java/lang/String
    //   108: dup
    //   109: aload 4
    //   111: iconst_0
    //   112: iload_0
    //   113: invokespecial 305	java/lang/String:<init>	([BII)V
    //   116: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: goto -33 -> 87
    //   123: aload 5
    //   125: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   128: astore_3
    //   129: ldc 8
    //   131: new 91	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   138: ldc_w 307
    //   141: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_3
    //   145: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: invokestatic 313	com/jb/gokeyboard/theme/template/util/LogPrint:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   154: aload_3
    //   155: ldc -115
    //   157: invokevirtual 145	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   160: astore_3
    //   161: iconst_0
    //   162: istore_0
    //   163: aload_3
    //   164: arraylength
    //   165: istore_1
    //   166: iload_0
    //   167: iload_1
    //   168: if_icmpge +41 -> 209
    //   171: aload_2
    //   172: aload_3
    //   173: iload_0
    //   174: aaload
    //   175: invokestatic 257	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   178: invokestatic 317	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   181: invokeinterface 148 2 0
    //   186: pop
    //   187: iload_0
    //   188: iconst_1
    //   189: iadd
    //   190: istore_0
    //   191: goto -28 -> 163
    //   194: astore 4
    //   196: goto -9 -> 187
    //   199: astore_3
    //   200: aload_2
    //   201: areturn
    //   202: astore_3
    //   203: aload_2
    //   204: areturn
    //   205: astore_3
    //   206: aload_2
    //   207: areturn
    //   208: astore_3
    //   209: aload_2
    //   210: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   97	94	0	i	int
    //   165	4	1	j	int
    //   7	203	2	localArrayList	ArrayList
    //   31	142	3	localObject	Object
    //   199	1	3	localIOException1	java.io.IOException
    //   202	1	3	localIOException2	java.io.IOException
    //   205	1	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   208	1	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   76	34	4	arrayOfByte	byte[]
    //   194	1	4	localNumberFormatException	NumberFormatException
    //   85	39	5	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   171	187	194	java/lang/NumberFormatException
    //   62	71	199	java/io/IOException
    //   71	87	202	java/io/IOException
    //   87	98	202	java/io/IOException
    //   103	120	202	java/io/IOException
    //   123	161	202	java/io/IOException
    //   163	166	202	java/io/IOException
    //   171	187	202	java/io/IOException
    //   62	71	205	java/io/FileNotFoundException
    //   71	87	208	java/io/FileNotFoundException
    //   87	98	208	java/io/FileNotFoundException
    //   103	120	208	java/io/FileNotFoundException
    //   123	161	208	java/io/FileNotFoundException
    //   163	166	208	java/io/FileNotFoundException
    //   171	187	208	java/io/FileNotFoundException
  }
  
  /* Error */
  public static long getTimeStampSynch()
  {
    // Byte code:
    //   0: new 188	java/util/Date
    //   3: dup
    //   4: invokespecial 189	java/util/Date:<init>	()V
    //   7: invokevirtual 288	java/util/Date:getTime	()J
    //   10: ldc_w 323
    //   13: i2l
    //   14: lsub
    //   15: lstore_3
    //   16: iconst_0
    //   17: istore 7
    //   19: iload 7
    //   21: ifne +26 -> 47
    //   24: invokestatic 325	com/jb/gokeyboard/theme/template/httpwecloud/util/NotifyMessageUtils:getFileLock	()Z
    //   27: istore 8
    //   29: iload 8
    //   31: istore 7
    //   33: ldc2_w 326
    //   36: invokestatic 333	java/lang/Thread:sleep	(J)V
    //   39: goto -20 -> 19
    //   42: astore 9
    //   44: goto -25 -> 19
    //   47: lload_3
    //   48: lstore 5
    //   50: ldc 47
    //   52: invokestatic 53	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   55: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +131 -> 189
    //   61: new 61	java/io/File
    //   64: dup
    //   65: invokestatic 65	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   68: ldc 67
    //   70: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   73: astore 9
    //   75: lload_3
    //   76: lstore 5
    //   78: aload 9
    //   80: invokevirtual 76	java/io/File:isDirectory	()Z
    //   83: ifeq +106 -> 189
    //   86: new 61	java/io/File
    //   89: dup
    //   90: aload 9
    //   92: ldc_w 335
    //   95: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   98: astore 9
    //   100: lload_3
    //   101: lstore 5
    //   103: aload 9
    //   105: invokevirtual 81	java/io/File:exists	()Z
    //   108: ifeq +81 -> 189
    //   111: aconst_null
    //   112: astore 12
    //   114: aconst_null
    //   115: astore 13
    //   117: aconst_null
    //   118: astore 10
    //   120: aconst_null
    //   121: astore 11
    //   123: new 275	java/io/FileInputStream
    //   126: dup
    //   127: aload 9
    //   129: invokespecial 278	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   132: astore 9
    //   134: sipush 1024
    //   137: newarray byte
    //   139: astore 10
    //   141: aload 9
    //   143: aload 10
    //   145: invokevirtual 282	java/io/FileInputStream:read	([B)I
    //   148: istore_0
    //   149: lload_3
    //   150: lstore_1
    //   151: iload_0
    //   152: ifle +21 -> 173
    //   155: new 55	java/lang/String
    //   158: dup
    //   159: aload 10
    //   161: iconst_0
    //   162: iload_0
    //   163: invokespecial 305	java/lang/String:<init>	([BII)V
    //   166: invokevirtual 204	java/lang/String:trim	()Ljava/lang/String;
    //   169: invokestatic 341	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   172: lstore_1
    //   173: lload_1
    //   174: lstore 5
    //   176: aload 9
    //   178: ifnull +11 -> 189
    //   181: aload 9
    //   183: invokevirtual 342	java/io/FileInputStream:close	()V
    //   186: lload_1
    //   187: lstore 5
    //   189: lload 5
    //   191: lreturn
    //   192: astore 9
    //   194: aload 11
    //   196: astore 9
    //   198: lload_3
    //   199: lstore 5
    //   201: aload 9
    //   203: ifnull -14 -> 189
    //   206: aload 9
    //   208: invokevirtual 342	java/io/FileInputStream:close	()V
    //   211: lload_3
    //   212: lreturn
    //   213: astore 9
    //   215: lload_3
    //   216: lreturn
    //   217: astore 9
    //   219: aload 12
    //   221: astore 9
    //   223: lload_3
    //   224: lstore 5
    //   226: aload 9
    //   228: ifnull -39 -> 189
    //   231: aload 9
    //   233: invokevirtual 342	java/io/FileInputStream:close	()V
    //   236: lload_3
    //   237: lreturn
    //   238: astore 9
    //   240: lload_3
    //   241: lreturn
    //   242: astore 9
    //   244: aload 13
    //   246: astore 9
    //   248: lload_3
    //   249: lstore 5
    //   251: aload 9
    //   253: ifnull -64 -> 189
    //   256: aload 9
    //   258: invokevirtual 342	java/io/FileInputStream:close	()V
    //   261: lload_3
    //   262: lreturn
    //   263: astore 9
    //   265: lload_3
    //   266: lreturn
    //   267: astore 9
    //   269: aload 10
    //   271: ifnull +8 -> 279
    //   274: aload 10
    //   276: invokevirtual 342	java/io/FileInputStream:close	()V
    //   279: aload 9
    //   281: athrow
    //   282: astore 9
    //   284: goto -251 -> 33
    //   287: astore 9
    //   289: lload_1
    //   290: lreturn
    //   291: astore 10
    //   293: goto -14 -> 279
    //   296: astore 11
    //   298: aload 9
    //   300: astore 10
    //   302: aload 11
    //   304: astore 9
    //   306: goto -37 -> 269
    //   309: astore 10
    //   311: goto -63 -> 248
    //   314: astore 10
    //   316: goto -93 -> 223
    //   319: astore 10
    //   321: goto -123 -> 198
    // Local variable table:
    //   start	length	slot	name	signature
    //   148	15	0	i	int
    //   150	140	1	l1	long
    //   15	251	3	l2	long
    //   48	202	5	l3	long
    //   17	15	7	j	int
    //   27	3	8	bool	boolean
    //   42	1	9	localInterruptedException	InterruptedException
    //   73	109	9	localObject1	Object
    //   192	1	9	localFileNotFoundException1	java.io.FileNotFoundException
    //   196	11	9	localObject2	Object
    //   213	1	9	localIOException1	java.io.IOException
    //   217	1	9	localNumberFormatException1	NumberFormatException
    //   221	11	9	localObject3	Object
    //   238	1	9	localIOException2	java.io.IOException
    //   242	1	9	localIOException3	java.io.IOException
    //   246	11	9	localObject4	Object
    //   263	1	9	localIOException4	java.io.IOException
    //   267	13	9	localObject5	Object
    //   282	1	9	localFileNotFoundException2	java.io.FileNotFoundException
    //   287	12	9	localIOException5	java.io.IOException
    //   304	1	9	localObject6	Object
    //   118	157	10	arrayOfByte	byte[]
    //   291	1	10	localIOException6	java.io.IOException
    //   300	1	10	localIOException7	java.io.IOException
    //   309	1	10	localIOException8	java.io.IOException
    //   314	1	10	localNumberFormatException2	NumberFormatException
    //   319	1	10	localFileNotFoundException3	java.io.FileNotFoundException
    //   121	74	11	localObject7	Object
    //   296	7	11	localObject8	Object
    //   112	108	12	localObject9	Object
    //   115	130	13	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   33	39	42	java/lang/InterruptedException
    //   123	134	192	java/io/FileNotFoundException
    //   206	211	213	java/io/IOException
    //   123	134	217	java/lang/NumberFormatException
    //   231	236	238	java/io/IOException
    //   123	134	242	java/io/IOException
    //   256	261	263	java/io/IOException
    //   123	134	267	finally
    //   24	29	282	java/io/FileNotFoundException
    //   181	186	287	java/io/IOException
    //   274	279	291	java/io/IOException
    //   134	149	296	finally
    //   155	173	296	finally
    //   134	149	309	java/io/IOException
    //   155	173	309	java/io/IOException
    //   134	149	314	java/lang/NumberFormatException
    //   155	173	314	java/lang/NumberFormatException
    //   134	149	319	java/io/FileNotFoundException
    //   155	173	319	java/io/FileNotFoundException
  }
  
  public static boolean isValidMessage(Context paramContext, NotifyMessageBean paramNotifyMessageBean)
  {
    if ((paramNotifyMessageBean.getMessageId() == null) || (TextUtils.isEmpty(paramNotifyMessageBean.getType())) || (TextUtils.isEmpty(paramNotifyMessageBean.getActionType())) || (TextUtils.isEmpty(paramNotifyMessageBean.getActionParam())) || (TextUtils.isEmpty(paramNotifyMessageBean.getWarmType())) || (TextUtils.isEmpty(paramNotifyMessageBean.getPosition())) || (TextUtils.isEmpty(paramNotifyMessageBean.getEffectiveFrom())) || (TextUtils.isEmpty(paramNotifyMessageBean.getEffectiveTo()))) {
      return false;
    }
    if (!"1".equals(paramNotifyMessageBean.getType()))
    {
      LogPrint.d("NotifyMessageUtils", "消息类型检查不通过");
      return false;
    }
    if (!checkMessageUri(paramContext, paramNotifyMessageBean))
    {
      LogPrint.d("NotifyMessageUtils", "检查跳转地址不通过");
      return false;
    }
    if (!checkDate(paramNotifyMessageBean))
    {
      LogPrint.d("NotifyMessageUtils", "检查消息展示时间不通过");
      return false;
    }
    if (!checkBlackAndWhiteList(paramContext, paramNotifyMessageBean))
    {
      LogPrint.d("NotifyMessageUtils", "检查消息黑白名单不通过");
      return false;
    }
    return true;
  }
  
  private static void releaseLock()
  {
    if ("mounted".equals(Environment.getExternalStorageState()))
    {
      File localFile = new File(Environment.getExternalStorageDirectory(), "gokeyboard/singlePage");
      if (localFile.isDirectory())
      {
        localFile = new File(localFile, "lock");
        if (localFile.exists()) {
          localFile.delete();
        }
      }
    }
  }
  
  /* Error */
  public static void updateTimeStamp(long paramLong, boolean paramBoolean)
  {
    // Byte code:
    //   0: iload_2
    //   1: ifeq +97 -> 98
    //   4: ldc 47
    //   6: invokestatic 53	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   9: invokevirtual 59	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   12: ifeq +86 -> 98
    //   15: new 61	java/io/File
    //   18: dup
    //   19: invokestatic 65	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   22: ldc 67
    //   24: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   27: astore_3
    //   28: aload_3
    //   29: invokevirtual 76	java/io/File:isDirectory	()Z
    //   32: ifeq +66 -> 98
    //   35: new 61	java/io/File
    //   38: dup
    //   39: aload_3
    //   40: ldc_w 335
    //   43: invokespecial 70	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   46: astore_3
    //   47: aload_3
    //   48: invokevirtual 81	java/io/File:exists	()Z
    //   51: ifne +8 -> 59
    //   54: aload_3
    //   55: invokevirtual 84	java/io/File:createNewFile	()Z
    //   58: pop
    //   59: new 86	java/io/FileOutputStream
    //   62: dup
    //   63: aload_3
    //   64: invokespecial 291	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   67: astore_3
    //   68: aload_3
    //   69: new 91	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 92	java/lang/StringBuilder:<init>	()V
    //   76: lload_0
    //   77: ldc2_w 380
    //   80: lsub
    //   81: invokevirtual 384	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   84: ldc 120
    //   86: invokevirtual 98	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokevirtual 108	java/lang/String:getBytes	()[B
    //   95: invokevirtual 112	java/io/FileOutputStream:write	([B)V
    //   98: invokestatic 386	com/jb/gokeyboard/theme/template/httpwecloud/util/NotifyMessageUtils:releaseLock	()V
    //   101: return
    //   102: astore_3
    //   103: return
    //   104: astore_3
    //   105: goto -7 -> 98
    //   108: astore_3
    //   109: goto -11 -> 98
    //   112: astore_3
    //   113: goto -15 -> 98
    //   116: astore_3
    //   117: goto -19 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	paramLong	long
    //   0	120	2	paramBoolean	boolean
    //   27	42	3	localObject	Object
    //   102	1	3	localIOException1	java.io.IOException
    //   104	1	3	localIOException2	java.io.IOException
    //   108	1	3	localIOException3	java.io.IOException
    //   112	1	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   116	1	3	localFileNotFoundException2	java.io.FileNotFoundException
    // Exception table:
    //   from	to	target	type
    //   54	59	102	java/io/IOException
    //   59	68	104	java/io/IOException
    //   68	98	108	java/io/IOException
    //   59	68	112	java/io/FileNotFoundException
    //   68	98	116	java/io/FileNotFoundException
  }
}
