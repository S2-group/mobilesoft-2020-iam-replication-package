package com.xodee.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.xodee.client.XLog;
import com.xodee.client.XLog.LoggerMode;
import com.xodee.client.XodeePreferences;
import com.xodee.client.XodeeUncaughtExceptionHandler;
import com.xodee.client.module.app.FileStore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Debug
{
  private static final String CHECK_LOG_CMD = "kill -0 %s";
  private static final String GZIP_POSTFIX = ".gz";
  private static final String GZIP_TEMP_FOLDER = "/gzip_log";
  private static final String LOCATION_LABEL = "[%s] Location: ";
  private static final String LOG_CMD = "logcat -v threadtime -f %s *:V";
  private static final String LOG_DIRECTORY = "log";
  private static final String LOG_FILE_NAME = "%s%s%s-%s.log";
  private static final int MAX_ERROR_SIZE = 10240;
  private static final String PREFERENCE_LOGGING_PID = "logging_pid";
  private static final int STACK_FRAME_OFFSET = 3;
  private static final String STOP_LOG_CMD = "kill -9 %s";
  public static final String TAG = "Util";
  private static final int TWO_DAYS_IN_MS = 172800000;
  public static String logFileName = null;
  private static Process loggingProcess = null;
  
  public Debug() {}
  
  public static void DUMP_STRING_TO_FILE(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new FileOutputStream(new File(paramString1));
      paramString2 = paramString2.toString().getBytes();
      paramString1.write(paramString2, 0, paramString2.length);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static String IntentToString(Intent paramIntent)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramIntent.toString());
    paramIntent = paramIntent.getExtras();
    if (!paramIntent.isEmpty())
    {
      localStringBuffer.append("\n[");
      Iterator localIterator = paramIntent.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localStringBuffer.append("(");
        localStringBuffer.append(str);
        localStringBuffer.append(" : ");
        localStringBuffer.append(paramIntent.get(str));
        localStringBuffer.append("),");
      }
      localStringBuffer.setLength(localStringBuffer.length() - 1);
      localStringBuffer.append("]");
    }
    return localStringBuffer.toString();
  }
  
  /* Error */
  private static boolean _writeAbbreviatedLogFile(Context paramContext, File paramFile, long paramLong)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 161	com/xodee/util/Debug:listRawLogFiles	(Landroid/content/Context;)[Ljava/io/File;
    //   7: astore 15
    //   9: aload 15
    //   11: arraylength
    //   12: anewarray 6	com/xodee/util/Debug$LogContentLimits
    //   15: astore 16
    //   17: iconst_0
    //   18: istore 4
    //   20: iload 4
    //   22: aload 15
    //   24: arraylength
    //   25: if_icmpge +83 -> 108
    //   28: aload 16
    //   30: iload 4
    //   32: new 6	com/xodee/util/Debug$LogContentLimits
    //   35: dup
    //   36: aconst_null
    //   37: invokespecial 164	com/xodee/util/Debug$LogContentLimits:<init>	(Lcom/xodee/util/Debug$1;)V
    //   40: aastore
    //   41: aload 15
    //   43: iload 4
    //   45: aaload
    //   46: invokevirtual 167	java/io/File:length	()J
    //   49: lstore 5
    //   51: aload 16
    //   53: iload 4
    //   55: aaload
    //   56: lload 5
    //   58: lload_2
    //   59: invokestatic 173	java/lang/Math:min	(JJ)J
    //   62: putfield 177	com/xodee/util/Debug$LogContentLimits:count	J
    //   65: aload 16
    //   67: iload 4
    //   69: aaload
    //   70: lload 5
    //   72: aload 16
    //   74: iload 4
    //   76: aaload
    //   77: getfield 177	com/xodee/util/Debug$LogContentLimits:count	J
    //   80: lsub
    //   81: putfield 180	com/xodee/util/Debug$LogContentLimits:begin	J
    //   84: aload 16
    //   86: iload 4
    //   88: aaload
    //   89: getfield 177	com/xodee/util/Debug$LogContentLimits:count	J
    //   92: lstore 5
    //   94: lload_2
    //   95: lload 5
    //   97: lsub
    //   98: lstore_2
    //   99: iload 4
    //   101: iconst_1
    //   102: iadd
    //   103: istore 4
    //   105: goto -85 -> 20
    //   108: aconst_null
    //   109: astore_0
    //   110: aconst_null
    //   111: astore 9
    //   113: aconst_null
    //   114: astore 11
    //   116: aconst_null
    //   117: astore 10
    //   119: new 63	java/io/FileOutputStream
    //   122: dup
    //   123: aload_1
    //   124: invokespecial 71	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   127: astore 13
    //   129: aload 13
    //   131: invokevirtual 184	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   134: astore 14
    //   136: aconst_null
    //   137: astore_0
    //   138: aload 14
    //   140: astore 10
    //   142: aload 15
    //   144: arraylength
    //   145: iconst_1
    //   146: isub
    //   147: istore 4
    //   149: aconst_null
    //   150: astore 9
    //   152: iload 4
    //   154: iflt +695 -> 849
    //   157: aload 14
    //   159: astore 10
    //   161: aload 16
    //   163: iload 4
    //   165: aaload
    //   166: getfield 177	com/xodee/util/Debug$LogContentLimits:count	J
    //   169: lstore_2
    //   170: lload_2
    //   171: lconst_0
    //   172: lcmp
    //   173: ifne +139 -> 312
    //   176: aload_0
    //   177: astore 10
    //   179: iload 4
    //   181: iconst_1
    //   182: isub
    //   183: istore 4
    //   185: aload 10
    //   187: astore_0
    //   188: goto -36 -> 152
    //   191: astore 10
    //   193: aload 9
    //   195: astore_0
    //   196: ldc 42
    //   198: ldc -70
    //   200: aload 10
    //   202: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   205: iconst_0
    //   206: istore 8
    //   208: iconst_0
    //   209: ifeq +11 -> 220
    //   212: new 194	java/lang/NullPointerException
    //   215: dup
    //   216: invokespecial 195	java/lang/NullPointerException:<init>	()V
    //   219: athrow
    //   220: iload 8
    //   222: istore 7
    //   224: aload 9
    //   226: ifnull +12 -> 238
    //   229: aload 9
    //   231: invokevirtual 198	java/io/FileOutputStream:close	()V
    //   234: iload 8
    //   236: istore 7
    //   238: ldc 2
    //   240: monitorexit
    //   241: iload 7
    //   243: ireturn
    //   244: astore_0
    //   245: ldc 42
    //   247: new 200	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   254: ldc -53
    //   256: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   259: aload_1
    //   260: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   263: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: aload_0
    //   267: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   270: goto -50 -> 220
    //   273: astore_0
    //   274: ldc 2
    //   276: monitorexit
    //   277: aload_0
    //   278: athrow
    //   279: astore_0
    //   280: ldc 42
    //   282: new 200	java/lang/StringBuilder
    //   285: dup
    //   286: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   289: ldc -44
    //   291: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   294: aload_1
    //   295: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   298: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   301: aload_0
    //   302: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   305: iload 8
    //   307: istore 7
    //   309: goto -71 -> 238
    //   312: new 214	java/io/FileInputStream
    //   315: dup
    //   316: aload 15
    //   318: iload 4
    //   320: aaload
    //   321: invokespecial 215	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   324: astore 11
    //   326: aload_0
    //   327: astore 9
    //   329: aload 11
    //   331: astore 10
    //   333: aload 11
    //   335: invokevirtual 216	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   338: astore 12
    //   340: aload 12
    //   342: astore 9
    //   344: aload 11
    //   346: astore 10
    //   348: aload 12
    //   350: astore_0
    //   351: aload 12
    //   353: aload 16
    //   355: iload 4
    //   357: aaload
    //   358: getfield 180	com/xodee/util/Debug$LogContentLimits:begin	J
    //   361: aload 16
    //   363: iload 4
    //   365: aaload
    //   366: getfield 177	com/xodee/util/Debug$LogContentLimits:count	J
    //   369: aload 14
    //   371: invokevirtual 222	java/nio/channels/FileChannel:transferTo	(JJLjava/nio/channels/WritableByteChannel;)J
    //   374: pop2
    //   375: aload 12
    //   377: astore_0
    //   378: aload 12
    //   380: ifnull +14 -> 394
    //   383: aload 14
    //   385: astore 10
    //   387: aload 12
    //   389: invokevirtual 223	java/nio/channels/FileChannel:close	()V
    //   392: aconst_null
    //   393: astore_0
    //   394: aload_0
    //   395: astore 10
    //   397: aload 11
    //   399: astore 9
    //   401: aload 11
    //   403: ifnull -224 -> 179
    //   406: aload 14
    //   408: astore 10
    //   410: aload 11
    //   412: invokevirtual 224	java/io/FileInputStream:close	()V
    //   415: aconst_null
    //   416: astore 9
    //   418: aload_0
    //   419: astore 10
    //   421: goto -242 -> 179
    //   424: astore_0
    //   425: aload 14
    //   427: astore 10
    //   429: ldc 42
    //   431: new 200	java/lang/StringBuilder
    //   434: dup
    //   435: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   438: ldc -30
    //   440: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: aload 15
    //   445: iload 4
    //   447: aaload
    //   448: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   451: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   454: aload_0
    //   455: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   458: goto -66 -> 392
    //   461: astore 9
    //   463: aload 13
    //   465: astore_0
    //   466: aload 10
    //   468: ifnull +8 -> 476
    //   471: aload 10
    //   473: invokevirtual 223	java/nio/channels/FileChannel:close	()V
    //   476: aload_0
    //   477: ifnull +7 -> 484
    //   480: aload_0
    //   481: invokevirtual 198	java/io/FileOutputStream:close	()V
    //   484: aload 9
    //   486: athrow
    //   487: astore 9
    //   489: aload 14
    //   491: astore 10
    //   493: ldc 42
    //   495: new 200	java/lang/StringBuilder
    //   498: dup
    //   499: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   502: ldc -28
    //   504: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: aload 15
    //   509: iload 4
    //   511: aaload
    //   512: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   515: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   518: aload 9
    //   520: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   523: goto -108 -> 415
    //   526: astore 12
    //   528: aload 9
    //   530: astore 11
    //   532: aload_0
    //   533: astore 9
    //   535: aload 11
    //   537: astore 10
    //   539: ldc 42
    //   541: ldc -70
    //   543: aload 12
    //   545: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   548: iconst_0
    //   549: istore 7
    //   551: aload_0
    //   552: ifnull +11 -> 563
    //   555: aload 14
    //   557: astore 10
    //   559: aload_0
    //   560: invokevirtual 223	java/nio/channels/FileChannel:close	()V
    //   563: aload 11
    //   565: ifnull +12 -> 577
    //   568: aload 14
    //   570: astore 10
    //   572: aload 11
    //   574: invokevirtual 224	java/io/FileInputStream:close	()V
    //   577: aload 14
    //   579: ifnull +8 -> 587
    //   582: aload 14
    //   584: invokevirtual 223	java/nio/channels/FileChannel:close	()V
    //   587: aload 13
    //   589: ifnull +8 -> 597
    //   592: aload 13
    //   594: invokevirtual 198	java/io/FileOutputStream:close	()V
    //   597: goto -359 -> 238
    //   600: astore_0
    //   601: aload 14
    //   603: astore 10
    //   605: ldc 42
    //   607: new 200	java/lang/StringBuilder
    //   610: dup
    //   611: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   614: ldc -30
    //   616: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   619: aload 15
    //   621: iload 4
    //   623: aaload
    //   624: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   627: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   630: aload_0
    //   631: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   634: goto -71 -> 563
    //   637: astore_0
    //   638: aload 14
    //   640: astore 10
    //   642: ldc 42
    //   644: new 200	java/lang/StringBuilder
    //   647: dup
    //   648: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   651: ldc -28
    //   653: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   656: aload 15
    //   658: iload 4
    //   660: aaload
    //   661: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   664: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   667: aload_0
    //   668: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   671: goto -94 -> 577
    //   674: astore_0
    //   675: ldc 42
    //   677: new 200	java/lang/StringBuilder
    //   680: dup
    //   681: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   684: ldc -53
    //   686: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   689: aload_1
    //   690: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   693: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   696: aload_0
    //   697: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   700: goto -113 -> 587
    //   703: astore_0
    //   704: ldc 42
    //   706: new 200	java/lang/StringBuilder
    //   709: dup
    //   710: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   713: ldc -44
    //   715: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   718: aload_1
    //   719: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   722: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   725: aload_0
    //   726: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   729: goto -132 -> 597
    //   732: astore 10
    //   734: aload 9
    //   736: astore 11
    //   738: aload 10
    //   740: astore 9
    //   742: aload_0
    //   743: ifnull +11 -> 754
    //   746: aload 14
    //   748: astore 10
    //   750: aload_0
    //   751: invokevirtual 223	java/nio/channels/FileChannel:close	()V
    //   754: aload 11
    //   756: ifnull +12 -> 768
    //   759: aload 14
    //   761: astore 10
    //   763: aload 11
    //   765: invokevirtual 224	java/io/FileInputStream:close	()V
    //   768: aload 14
    //   770: astore 10
    //   772: aload 9
    //   774: athrow
    //   775: astore_0
    //   776: aload 14
    //   778: astore 10
    //   780: ldc 42
    //   782: new 200	java/lang/StringBuilder
    //   785: dup
    //   786: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   789: ldc -30
    //   791: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   794: aload 15
    //   796: iload 4
    //   798: aaload
    //   799: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   802: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   805: aload_0
    //   806: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   809: goto -55 -> 754
    //   812: astore_0
    //   813: aload 14
    //   815: astore 10
    //   817: ldc 42
    //   819: new 200	java/lang/StringBuilder
    //   822: dup
    //   823: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   826: ldc -28
    //   828: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   831: aload 15
    //   833: iload 4
    //   835: aaload
    //   836: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   839: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   842: aload_0
    //   843: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   846: goto -78 -> 768
    //   849: iconst_1
    //   850: istore 7
    //   852: aload 14
    //   854: ifnull +8 -> 862
    //   857: aload 14
    //   859: invokevirtual 223	java/nio/channels/FileChannel:close	()V
    //   862: aload 13
    //   864: ifnull +8 -> 872
    //   867: aload 13
    //   869: invokevirtual 198	java/io/FileOutputStream:close	()V
    //   872: goto -634 -> 238
    //   875: astore_0
    //   876: ldc 42
    //   878: new 200	java/lang/StringBuilder
    //   881: dup
    //   882: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   885: ldc -53
    //   887: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   890: aload_1
    //   891: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   894: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   897: aload_0
    //   898: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   901: goto -39 -> 862
    //   904: astore_0
    //   905: ldc 42
    //   907: new 200	java/lang/StringBuilder
    //   910: dup
    //   911: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   914: ldc -44
    //   916: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: aload_1
    //   920: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   923: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   926: aload_0
    //   927: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   930: goto -58 -> 872
    //   933: astore 10
    //   935: ldc 42
    //   937: new 200	java/lang/StringBuilder
    //   940: dup
    //   941: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   944: ldc -53
    //   946: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   949: aload_1
    //   950: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   953: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   956: aload 10
    //   958: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   961: goto -485 -> 476
    //   964: astore_0
    //   965: ldc 42
    //   967: new 200	java/lang/StringBuilder
    //   970: dup
    //   971: invokespecial 201	java/lang/StringBuilder:<init>	()V
    //   974: ldc -44
    //   976: invokevirtual 206	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: aload_1
    //   980: invokevirtual 209	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   983: invokevirtual 210	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   986: aload_0
    //   987: invokestatic 192	com/xodee/client/XLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   990: goto -506 -> 484
    //   993: astore 9
    //   995: aload 11
    //   997: astore 10
    //   999: goto -533 -> 466
    //   1002: astore 11
    //   1004: aload 9
    //   1006: astore_0
    //   1007: aload 11
    //   1009: astore 9
    //   1011: aload 10
    //   1013: astore 11
    //   1015: goto -273 -> 742
    //   1018: astore 12
    //   1020: goto -488 -> 532
    //   1023: astore 10
    //   1025: aload 13
    //   1027: astore 9
    //   1029: goto -836 -> 193
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1032	0	paramContext	Context
    //   0	1032	1	paramFile	File
    //   0	1032	2	paramLong	long
    //   18	816	4	i	int
    //   49	47	5	l	long
    //   222	629	7	bool1	boolean
    //   206	100	8	bool2	boolean
    //   111	306	9	localObject1	Object
    //   461	24	9	localObject2	Object
    //   487	42	9	localException1	Exception
    //   533	240	9	localObject3	Object
    //   993	12	9	localObject4	Object
    //   1009	19	9	localObject5	Object
    //   117	69	10	localObject6	Object
    //   191	10	10	localException2	Exception
    //   331	310	10	localObject7	Object
    //   732	7	10	localObject8	Object
    //   748	68	10	localObject9	Object
    //   933	24	10	localException3	Exception
    //   997	15	10	localObject10	Object
    //   1023	1	10	localException4	Exception
    //   114	882	11	localObject11	Object
    //   1002	6	11	localObject12	Object
    //   1013	1	11	localObject13	Object
    //   338	50	12	localFileChannel1	java.nio.channels.FileChannel
    //   526	18	12	localException5	Exception
    //   1018	1	12	localException6	Exception
    //   127	899	13	localFileOutputStream	FileOutputStream
    //   134	724	14	localFileChannel2	java.nio.channels.FileChannel
    //   7	825	15	arrayOfFile	File[]
    //   15	347	16	arrayOfLogContentLimits	Debug.LogContentLimits[]
    // Exception table:
    //   from	to	target	type
    //   119	129	191	java/lang/Exception
    //   212	220	244	java/lang/Exception
    //   3	17	273	finally
    //   20	94	273	finally
    //   212	220	273	finally
    //   229	234	273	finally
    //   245	270	273	finally
    //   280	305	273	finally
    //   471	476	273	finally
    //   480	484	273	finally
    //   484	487	273	finally
    //   582	587	273	finally
    //   592	597	273	finally
    //   675	700	273	finally
    //   704	729	273	finally
    //   857	862	273	finally
    //   867	872	273	finally
    //   876	901	273	finally
    //   905	930	273	finally
    //   935	961	273	finally
    //   965	990	273	finally
    //   229	234	279	java/lang/Exception
    //   387	392	424	java/lang/Exception
    //   129	136	461	finally
    //   142	149	461	finally
    //   161	170	461	finally
    //   387	392	461	finally
    //   410	415	461	finally
    //   429	458	461	finally
    //   493	523	461	finally
    //   559	563	461	finally
    //   572	577	461	finally
    //   605	634	461	finally
    //   642	671	461	finally
    //   750	754	461	finally
    //   763	768	461	finally
    //   772	775	461	finally
    //   780	809	461	finally
    //   817	846	461	finally
    //   410	415	487	java/lang/Exception
    //   312	326	526	java/lang/Exception
    //   559	563	600	java/lang/Exception
    //   572	577	637	java/lang/Exception
    //   582	587	674	java/lang/Exception
    //   592	597	703	java/lang/Exception
    //   312	326	732	finally
    //   750	754	775	java/lang/Exception
    //   763	768	812	java/lang/Exception
    //   857	862	875	java/lang/Exception
    //   867	872	904	java/lang/Exception
    //   471	476	933	java/lang/Exception
    //   480	484	964	java/lang/Exception
    //   119	129	993	finally
    //   196	205	993	finally
    //   333	340	1002	finally
    //   351	375	1002	finally
    //   539	548	1002	finally
    //   333	340	1018	java/lang/Exception
    //   351	375	1018	java/lang/Exception
    //   129	136	1023	java/lang/Exception
  }
  
  private static boolean checkLogExecuting(Context paramContext)
  {
    if (loggingProcess != null) {}
    String str;
    do
    {
      try
      {
        loggingProcess.exitValue();
        return false;
      }
      catch (IllegalThreadStateException paramContext)
      {
        return true;
      }
      catch (Exception localException1) {}
      str = XodeePreferences.getInstance().getPreference(paramContext, "logging_pid");
    } while (TextUtils.isEmpty(str));
    Object localObject = String.format("kill -0 %s", new Object[] { str });
    Runtime localRuntime = Runtime.getRuntime();
    int j = 1;
    int i = j;
    try
    {
      localObject = localRuntime.exec((String)localObject);
      i = j;
      ((Process)localObject).waitFor();
      i = j;
      j = ((Process)localObject).exitValue();
      i = j;
      ((Process)localObject).destroy();
      i = j;
    }
    catch (Exception localException3)
    {
      for (;;)
      {
        XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException3, "Unable to check on running pid");
        try
        {
          android.os.Process.killProcess(Integer.parseInt(str));
        }
        catch (Exception localException2)
        {
          XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException2, "Unable to stop logging on pid");
        }
      }
    }
    if (i != 0)
    {
      XodeePreferences.getInstance().setPreferences(paramContext, new String[] { "logging_pid", null });
      return false;
    }
    return true;
  }
  
  private static void cleanupLogFiles(Context paramContext)
  {
    File localFile = getLogLocation(paramContext);
    if ((localFile == null) || (!localFile.exists())) {}
    do
    {
      return;
      paramContext = getLogGZipTempLocation(paramContext);
      if ((paramContext != null) && (paramContext.exists()))
      {
        File[] arrayOfFile = paramContext.listFiles();
        j = arrayOfFile.length;
        i = 0;
        while (i < j)
        {
          arrayOfFile[i].delete();
          i += 1;
        }
        paramContext.delete();
      }
      paramContext = localFile.listFiles();
    } while (paramContext == null);
    int j = paramContext.length;
    int i = 0;
    label84:
    if (i < j)
    {
      localFile = paramContext[i];
      if (!localFile.isDirectory()) {
        break label129;
      }
      XLog.e("Util", String.format("Unexpected directory found while cleaning logs: %s", new Object[] { localFile.getAbsolutePath() }));
    }
    for (;;)
    {
      i += 1;
      break label84;
      break;
      label129:
      if (System.currentTimeMillis() - localFile.lastModified() > 172800000L) {
        localFile.delete();
      }
    }
  }
  
  public static void crashIn(long paramLong)
  {
    new Debug.2(paramLong).start();
  }
  
  public static void dumpCaller(String paramString, int paramInt)
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    int i = Math.min(paramInt + 3, arrayOfStackTraceElement.length);
    paramInt = 3;
    while (paramInt < i)
    {
      XLog.e(paramString, paramString + " " + arrayOfStackTraceElement[paramInt].getClassName() + "." + arrayOfStackTraceElement[paramInt].getMethodName() + " " + arrayOfStackTraceElement[paramInt].getLineNumber());
      paramInt += 1;
    }
    XLog.e(paramString, "-- end --");
  }
  
  public static String dumpCallerAsString(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    int i = Math.min(paramInt + 3, arrayOfStackTraceElement.length);
    paramInt = 3;
    while (paramInt < i)
    {
      localStringBuilder.append(arrayOfStackTraceElement[paramInt].getClassName()).append(".").append(arrayOfStackTraceElement[paramInt].getMethodName()).append(" ").append(arrayOfStackTraceElement[paramInt].getLineNumber()).append("\n");
      paramInt += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static void dumpWAV(Context paramContext, String paramString, int paramInt, short[] paramArrayOfShort)
  {
    if (paramArrayOfShort == null) {
      return;
    }
    new Debug.1(paramArrayOfShort, paramContext, paramString, paramInt).start();
  }
  
  public static void ensureLogging(Context paramContext)
  {
    try
    {
      stopLogging(paramContext);
      cleanupLogFiles(paramContext);
      Context localContext = paramContext.getApplicationContext();
      if (XLog.getInstance().getMode() != XLog.LoggerMode.NONE)
      {
        File localFile = getLogLocation(paramContext);
        FileStore.getInstance(paramContext).ensureDirectoryPath(localFile);
        logFileName = String.format("%s%s%s-%s.log", new Object[] { localFile.getAbsolutePath(), File.separator, paramContext.getPackageName(), Calendar.getInstance().get(1) + "-" + (Calendar.getInstance().get(2) + 1) + "-" + Calendar.getInstance().get(5) });
        if (!checkLogExecuting(localContext)) {
          startLogging(localContext);
        }
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static String getLocation(String paramString)
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    paramString = new StringBuffer(String.format("[%s] Location: ", new Object[] { paramString }));
    int i = 3;
    while (i < arrayOfStackTraceElement.length)
    {
      paramString.append('\n');
      paramString.append("  ");
      paramString.append(arrayOfStackTraceElement[i].toString());
      i += 1;
    }
    return paramString.toString();
  }
  
  public static File getLogFile(Context paramContext)
  {
    File localFile1 = null;
    int j = 0;
    for (;;)
    {
      File localFile2;
      Object localObject;
      int i;
      File localFile3;
      try
      {
        File[] arrayOfFile = listRawLogFiles(paramContext);
        localFile2 = getLogGZipTempLocation(paramContext);
        if (localFile2 == null) {
          return localFile1;
        }
        int k;
        if (!localFile2.exists())
        {
          FileStore.getInstance(paramContext).ensureDirectoryPath(localFile2);
          localObject = new ArrayList();
          k = arrayOfFile.length;
          i = j;
          if (i >= k) {
            break label173;
          }
          localFile3 = arrayOfFile[i];
          if (localFile3.equals(localFile2)) {
            break label249;
          }
        }
        else
        {
          localObject = localFile2.listFiles();
          k = localObject.length;
          i = 0;
          if (i < k)
          {
            localObject[i].delete();
            i += 1;
            continue;
          }
          continue;
        }
        if (localFile3.isDirectory()) {
          XLog.e("Util", String.format("Unexpected directory found while compressing logs: %s", new Object[] { localFile3.getAbsolutePath() }));
        }
      }
      finally {}
      ((List)localObject).add(localFile3);
      label173:
      if (!((List)localObject).isEmpty())
      {
        localFile1 = new File(localFile2 + File.separator + ((File)((List)localObject).get(0)).getName() + ".gz");
        FileStore.getInstance(paramContext).appendAndGZipTextFiles((List)localObject, localFile1);
        continue;
        label249:
        i += 1;
      }
    }
  }
  
  public static File[] getLogFiles(Context paramContext)
  {
    int j = 0;
    for (;;)
    {
      File localFile1;
      int i;
      Object localObject;
      try
      {
        File[] arrayOfFile = listRawLogFiles(paramContext);
        localFile1 = getLogGZipTempLocation(paramContext);
        if (localFile1 == null)
        {
          paramContext = new File[0];
          return paramContext;
        }
        int k;
        if (!localFile1.exists())
        {
          FileStore.getInstance(paramContext).ensureDirectoryPath(localFile1);
          k = arrayOfFile.length;
          i = j;
          if (i >= k) {
            break label209;
          }
          localObject = arrayOfFile[i];
          if (((File)localObject).equals(localFile1)) {
            break label218;
          }
        }
        else
        {
          localObject = localFile1.listFiles();
          k = localObject.length;
          i = 0;
          if (i < k)
          {
            localObject[i].delete();
            i += 1;
            continue;
          }
          continue;
        }
        if (((File)localObject).isDirectory()) {
          XLog.e("Util", String.format("Unexpected directory found while compressing logs: %s", new Object[] { ((File)localObject).getAbsolutePath() }));
        }
      }
      finally {}
      File localFile2 = new File(localFile1 + File.separator + ((File)localObject).getName() + ".gz");
      FileStore.getInstance(paramContext).gzipFile((File)localObject, localFile2);
      break label218;
      label209:
      paramContext = localFile1.listFiles();
      continue;
      label218:
      i += 1;
    }
  }
  
  public static File getLogGZipTempLocation(Context paramContext)
  {
    paramContext = getLogLocation(paramContext);
    if (paramContext == null) {
      return null;
    }
    return new File(paramContext.getAbsolutePath() + "/gzip_log");
  }
  
  public static File getLogLocation(Context paramContext)
  {
    return new File(paramContext.getFilesDir(), "log");
  }
  
  public static void listInstalledPackages(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0);
    int i = 0;
    while (i < paramContext.size())
    {
      XLog.d("Util", "Installed Apk: " + ((ApplicationInfo)paramContext.get(i)).publicSourceDir);
      i += 1;
    }
  }
  
  /* Error */
  private static File[] listRawLogFiles(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 306	com/xodee/util/Debug:getLogLocation	(Landroid/content/Context;)Ljava/io/File;
    //   7: astore_0
    //   8: aload_0
    //   9: ifnonnull +13 -> 22
    //   12: iconst_0
    //   13: anewarray 65	java/io/File
    //   16: astore_0
    //   17: ldc 2
    //   19: monitorexit
    //   20: aload_0
    //   21: areturn
    //   22: aload_0
    //   23: invokevirtual 316	java/io/File:listFiles	()[Ljava/io/File;
    //   26: astore_0
    //   27: aload_0
    //   28: new 520	com/xodee/util/Debug$4
    //   31: dup
    //   32: invokespecial 521	com/xodee/util/Debug$4:<init>	()V
    //   35: invokestatic 527	java/util/Arrays:sort	([Ljava/lang/Object;Ljava/util/Comparator;)V
    //   38: goto -21 -> 17
    //   41: astore_0
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload_0
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   3	8	41	finally
    //   12	17	41	finally
    //   22	38	41	finally
  }
  
  private static void startLogging(Context paramContext)
  {
    Object localObject = String.format("logcat -v threadtime -f %s *:V", new Object[] { logFileName });
    try
    {
      loggingProcess = Runtime.getRuntime().exec((String)localObject);
      try
      {
        localObject = loggingProcess.getClass().getDeclaredField("pid");
        ((Field)localObject).setAccessible(true);
        int i = ((Field)localObject).getInt(loggingProcess);
        XodeePreferences.getInstance().setPreferences(paramContext, new String[] { "logging_pid", String.valueOf(i) });
        new Debug.3(loggingProcess, paramContext).start();
        return;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        for (;;)
        {
          Field localField = loggingProcess.getClass().getDeclaredField("id");
        }
      }
      catch (Exception localException1)
      {
        if (loggingProcess != null)
        {
          loggingProcess.destroy();
          loggingProcess = null;
        }
        XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException1, "Unable to get pid while starting logging process");
        return;
      }
      File localFile;
      return;
    }
    catch (Exception localException2)
    {
      XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException2, "Unable to start logging");
      localFile = new File(logFileName);
      try
      {
        new FileWriter(localFile).write("Error writing log to file!\n" + localException2.getMessage());
        return;
      }
      catch (Exception localException3)
      {
        XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException3, "Unable to write dummy data to log");
      }
    }
  }
  
  public static void startLoggingIfStopped(Context paramContext)
  {
    try
    {
      Context localContext = paramContext.getApplicationContext();
      if ((XLog.getInstance().getMode() != XLog.LoggerMode.NONE) && (!checkLogExecuting(localContext)))
      {
        File localFile = getLogLocation(paramContext);
        FileStore.getInstance(paramContext).ensureDirectoryPath(localFile);
        logFileName = String.format("%s%s%s-%s.log", new Object[] { localFile.getAbsolutePath(), File.separator, paramContext.getPackageName(), Calendar.getInstance().get(1) + "-" + (Calendar.getInstance().get(2) + 1) + "-" + Calendar.getInstance().get(5) });
        startLogging(localContext);
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private static void stopLogging(Context paramContext)
  {
    if (XLog.getInstance().getMode() == XLog.LoggerMode.NONE) {}
    while (!checkLogExecuting(paramContext)) {
      return;
    }
    for (;;)
    {
      try
      {
        if (loggingProcess != null)
        {
          loggingProcess.destroy();
          loggingProcess = null;
          XodeePreferences.getInstance().setPreferences(paramContext, new String[] { "logging_pid", null });
          return;
        }
      }
      catch (Exception localException1)
      {
        XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException1, "Unable to stop logging");
        return;
      }
      String str = XodeePreferences.getInstance().getPreference(paramContext, "logging_pid");
      boolean bool = TextUtils.isEmpty(str);
      if (!bool) {
        try
        {
          android.os.Process.killProcess(Integer.parseInt(str));
        }
        catch (Exception localException2)
        {
          XodeeUncaughtExceptionHandler.getInstance(paramContext).notify(localException2, "Unable to stop logging on pid");
        }
      }
    }
  }
  
  public static String summarizeUri(Uri paramUri)
  {
    if (paramUri == null) {
      paramUri = "";
    }
    String str;
    do
    {
      return paramUri;
      str = paramUri.toString();
      paramUri = str;
    } while (str.length() < 5);
    int i = Math.min(10, (str.length() - 3) / 2);
    return String.format("%s...%s", new Object[] { str.substring(0, i), str.substring(str.length() - i) });
  }
  
  public static boolean writeAbbreviatedLogFile(Context paramContext, File paramFile, long paramLong)
  {
    try
    {
      bool = _writeAbbreviatedLogFile(paramContext, paramFile, paramLong);
      return bool;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        XLog.e("Util", "Unable to abbreviate crash log.", paramContext);
        boolean bool = false;
      }
    }
    finally {}
  }
}
