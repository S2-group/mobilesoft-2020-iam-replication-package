package mahmed.net.synctuneswireless;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProblemReportActivity
  extends Activity
{
  private ProgressDialog pd = null;
  
  public ProblemReportActivity() {}
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  private String generateLog(String... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: invokestatic 53	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   5: ldc 55
    //   7: invokevirtual 59	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   10: astore 4
    //   12: aload 4
    //   14: astore_3
    //   15: new 61	java/io/BufferedReader
    //   18: dup
    //   19: new 63	java/io/InputStreamReader
    //   22: dup
    //   23: aload 4
    //   25: invokevirtual 69	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   28: invokespecial 72	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   31: invokespecial 75	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   34: astore 5
    //   36: aload 4
    //   38: astore_3
    //   39: ldc 77
    //   41: iconst_1
    //   42: anewarray 79	java/lang/Object
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getstatic 85	mahmed/net/synctuneswireless/R$string:app_prefix	I
    //   51: invokevirtual 89	mahmed/net/synctuneswireless/ProblemReportActivity:getString	(I)Ljava/lang/String;
    //   54: aastore
    //   55: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   58: iconst_0
    //   59: invokestatic 101	java/util/regex/Pattern:compile	(Ljava/lang/String;I)Ljava/util/regex/Pattern;
    //   62: astore 6
    //   64: aload 4
    //   66: astore_3
    //   67: aload_0
    //   68: invokevirtual 105	mahmed/net/synctuneswireless/ProblemReportActivity:getFilesDir	()Ljava/io/File;
    //   71: invokevirtual 111	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   74: astore 7
    //   76: aload 4
    //   78: astore_3
    //   79: new 113	java/io/OutputStreamWriter
    //   82: dup
    //   83: aload_0
    //   84: ldc 115
    //   86: iconst_1
    //   87: invokevirtual 119	mahmed/net/synctuneswireless/ProblemReportActivity:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   90: invokespecial 122	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   93: astore 8
    //   95: aload 8
    //   97: ldc 124
    //   99: iconst_1
    //   100: anewarray 79	java/lang/Object
    //   103: dup
    //   104: iconst_0
    //   105: aload_1
    //   106: iconst_0
    //   107: aaload
    //   108: aastore
    //   109: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   112: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   115: aload 8
    //   117: ldc -126
    //   119: iconst_1
    //   120: anewarray 79	java/lang/Object
    //   123: dup
    //   124: iconst_0
    //   125: aload_1
    //   126: iconst_1
    //   127: aaload
    //   128: aastore
    //   129: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   132: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   135: aload 8
    //   137: ldc -124
    //   139: iconst_1
    //   140: anewarray 79	java/lang/Object
    //   143: dup
    //   144: iconst_0
    //   145: aload_1
    //   146: iconst_2
    //   147: aaload
    //   148: aastore
    //   149: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   152: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   155: aload 8
    //   157: ldc -122
    //   159: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   162: aload_0
    //   163: invokevirtual 138	mahmed/net/synctuneswireless/ProblemReportActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   166: aload_0
    //   167: invokevirtual 141	mahmed/net/synctuneswireless/ProblemReportActivity:getPackageName	()Ljava/lang/String;
    //   170: iconst_0
    //   171: invokevirtual 147	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   174: astore_1
    //   175: aload 8
    //   177: ldc -107
    //   179: iconst_1
    //   180: anewarray 79	java/lang/Object
    //   183: dup
    //   184: iconst_0
    //   185: aload_1
    //   186: getfield 155	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   189: aastore
    //   190: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   193: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   196: aload 8
    //   198: ldc -99
    //   200: iconst_1
    //   201: anewarray 79	java/lang/Object
    //   204: dup
    //   205: iconst_0
    //   206: aload_1
    //   207: getfield 160	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   210: aastore
    //   211: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   214: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   217: aload 8
    //   219: ldc -94
    //   221: iconst_1
    //   222: anewarray 79	java/lang/Object
    //   225: dup
    //   226: iconst_0
    //   227: aload_1
    //   228: getfield 165	android/content/pm/PackageInfo:versionCode	I
    //   231: invokestatic 171	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   234: aastore
    //   235: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   238: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   241: new 173	java/lang/StringBuffer
    //   244: dup
    //   245: invokespecial 174	java/lang/StringBuffer:<init>	()V
    //   248: astore_1
    //   249: aload_1
    //   250: ldc -80
    //   252: iconst_1
    //   253: anewarray 79	java/lang/Object
    //   256: dup
    //   257: iconst_0
    //   258: getstatic 181	android/os/Build:PRODUCT	Ljava/lang/String;
    //   261: aastore
    //   262: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   265: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   268: pop
    //   269: aload_1
    //   270: ldc -69
    //   272: iconst_1
    //   273: anewarray 79	java/lang/Object
    //   276: dup
    //   277: iconst_0
    //   278: getstatic 190	android/os/Build:BRAND	Ljava/lang/String;
    //   281: aastore
    //   282: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   285: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   288: pop
    //   289: aload_1
    //   290: ldc -64
    //   292: iconst_1
    //   293: anewarray 79	java/lang/Object
    //   296: dup
    //   297: iconst_0
    //   298: getstatic 195	android/os/Build:MODEL	Ljava/lang/String;
    //   301: aastore
    //   302: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   305: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   308: pop
    //   309: aload_1
    //   310: ldc -59
    //   312: iconst_1
    //   313: anewarray 79	java/lang/Object
    //   316: dup
    //   317: iconst_0
    //   318: getstatic 200	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   321: aastore
    //   322: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   325: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   328: pop
    //   329: aload_1
    //   330: ldc -54
    //   332: iconst_1
    //   333: anewarray 79	java/lang/Object
    //   336: dup
    //   337: iconst_0
    //   338: getstatic 205	android/os/Build:DEVICE	Ljava/lang/String;
    //   341: aastore
    //   342: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   345: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   348: pop
    //   349: aload_1
    //   350: ldc -49
    //   352: iconst_1
    //   353: anewarray 79	java/lang/Object
    //   356: dup
    //   357: iconst_0
    //   358: getstatic 212	android/os/Build$VERSION:SDK_INT	I
    //   361: invokestatic 171	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   364: aastore
    //   365: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   368: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   371: pop
    //   372: aload_1
    //   373: ldc -42
    //   375: iconst_1
    //   376: anewarray 79	java/lang/Object
    //   379: dup
    //   380: iconst_0
    //   381: aload_0
    //   382: invokestatic 220	mahmed/net/synctuneswireless/Utils:isServiceRunning	(Landroid/content/Context;)Z
    //   385: invokestatic 225	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   388: aastore
    //   389: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   392: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   395: pop
    //   396: getstatic 212	android/os/Build$VERSION:SDK_INT	I
    //   399: bipush 17
    //   401: if_icmplt +307 -> 708
    //   404: aload_0
    //   405: invokevirtual 229	mahmed/net/synctuneswireless/ProblemReportActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   408: ldc -25
    //   410: invokestatic 237	android/provider/Settings$Global:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   413: istore_2
    //   414: aload_1
    //   415: ldc -17
    //   417: iconst_1
    //   418: anewarray 79	java/lang/Object
    //   421: dup
    //   422: iconst_0
    //   423: iload_2
    //   424: invokestatic 171	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   427: aastore
    //   428: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   431: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   434: pop
    //   435: aload_1
    //   436: ldc -15
    //   438: iconst_1
    //   439: anewarray 79	java/lang/Object
    //   442: dup
    //   443: iconst_0
    //   444: new 243	mahmed/net/synctuneswireless/Settings
    //   447: dup
    //   448: aload_0
    //   449: invokevirtual 247	mahmed/net/synctuneswireless/ProblemReportActivity:getApplicationContext	()Landroid/content/Context;
    //   452: invokespecial 250	mahmed/net/synctuneswireless/Settings:<init>	(Landroid/content/Context;)V
    //   455: invokevirtual 253	mahmed/net/synctuneswireless/Settings:toString	()Ljava/lang/String;
    //   458: aastore
    //   459: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   462: invokevirtual 185	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   465: pop
    //   466: aload 8
    //   468: aload_1
    //   469: invokevirtual 254	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   472: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   475: aload 8
    //   477: ldc_w 256
    //   480: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   483: aload 8
    //   485: ldc_w 258
    //   488: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   491: invokestatic 264	de/jockels/open/Environment2:getSecondaryExternalStorage	()Lde/jockels/open/Device;
    //   494: astore_1
    //   495: aload 8
    //   497: new 266	java/lang/StringBuilder
    //   500: dup
    //   501: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   504: aload_1
    //   505: invokevirtual 272	de/jockels/open/Device:getMountPoint	()Ljava/lang/String;
    //   508: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: ldc_w 277
    //   514: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   520: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   523: aload 8
    //   525: new 266	java/lang/StringBuilder
    //   528: dup
    //   529: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   532: aload_1
    //   533: invokevirtual 281	de/jockels/open/Device:getName	()Ljava/lang/String;
    //   536: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: ldc_w 277
    //   542: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   548: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   551: aload 8
    //   553: ldc_w 283
    //   556: iconst_1
    //   557: anewarray 79	java/lang/Object
    //   560: dup
    //   561: iconst_0
    //   562: aload_1
    //   563: invokevirtual 272	de/jockels/open/Device:getMountPoint	()Ljava/lang/String;
    //   566: invokestatic 287	mahmed/net/synctuneswireless/Utils:makeTestFile	(Ljava/lang/String;)Z
    //   569: invokestatic 225	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   572: aastore
    //   573: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   576: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   579: aload 8
    //   581: new 266	java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   588: aload_1
    //   589: invokevirtual 290	de/jockels/open/Device:getState	()Ljava/lang/String;
    //   592: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   595: ldc_w 277
    //   598: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   604: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   607: aload 8
    //   609: ldc_w 292
    //   612: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   615: invokestatic 296	mahmed/net/synctuneswireless/Utils:getExternalMounts	()Ljava/util/HashSet;
    //   618: invokevirtual 302	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   621: astore_1
    //   622: aload_1
    //   623: invokeinterface 308 1 0
    //   628: ifeq +125 -> 753
    //   631: aload_1
    //   632: invokeinterface 312 1 0
    //   637: checkcast 91	java/lang/String
    //   640: astore_3
    //   641: aload 8
    //   643: new 266	java/lang/StringBuilder
    //   646: dup
    //   647: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   650: aload_3
    //   651: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   654: ldc_w 277
    //   657: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   660: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   663: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   666: aload 8
    //   668: ldc_w 283
    //   671: iconst_1
    //   672: anewarray 79	java/lang/Object
    //   675: dup
    //   676: iconst_0
    //   677: aload_3
    //   678: invokestatic 287	mahmed/net/synctuneswireless/Utils:makeTestFile	(Ljava/lang/String;)Z
    //   681: invokestatic 225	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   684: aastore
    //   685: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   688: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   691: goto -69 -> 622
    //   694: astore_1
    //   695: aload 4
    //   697: astore_3
    //   698: aload_3
    //   699: ifnull +7 -> 706
    //   702: aload_3
    //   703: invokevirtual 315	java/lang/Process:destroy	()V
    //   706: aconst_null
    //   707: areturn
    //   708: aload_0
    //   709: invokevirtual 229	mahmed/net/synctuneswireless/ProblemReportActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   712: ldc -25
    //   714: invokestatic 318	android/provider/Settings$System:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   717: istore_2
    //   718: goto -304 -> 414
    //   721: astore_1
    //   722: aload 8
    //   724: new 266	java/lang/StringBuilder
    //   727: dup
    //   728: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   731: aload_1
    //   732: invokevirtual 319	de/jockels/open/NoSecondaryStorageException:toString	()Ljava/lang/String;
    //   735: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   738: ldc_w 277
    //   741: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   747: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   750: goto -143 -> 607
    //   753: aload 8
    //   755: ldc_w 321
    //   758: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   761: getstatic 212	android/os/Build$VERSION:SDK_INT	I
    //   764: bipush 19
    //   766: if_icmplt +86 -> 852
    //   769: aload_0
    //   770: iconst_0
    //   771: invokestatic 325	mahmed/net/synctuneswireless/Utils:getExternalDirsKitKat	(Landroid/content/Context;Z)Ljava/util/List;
    //   774: invokeinterface 328 1 0
    //   779: astore_1
    //   780: aload_1
    //   781: invokeinterface 308 1 0
    //   786: ifeq +66 -> 852
    //   789: aload_1
    //   790: invokeinterface 312 1 0
    //   795: checkcast 91	java/lang/String
    //   798: astore_3
    //   799: aload 8
    //   801: new 266	java/lang/StringBuilder
    //   804: dup
    //   805: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   808: aload_3
    //   809: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: ldc_w 277
    //   815: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   818: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   821: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   824: aload 8
    //   826: ldc_w 283
    //   829: iconst_1
    //   830: anewarray 79	java/lang/Object
    //   833: dup
    //   834: iconst_0
    //   835: aload_3
    //   836: invokestatic 287	mahmed/net/synctuneswireless/Utils:makeTestFile	(Ljava/lang/String;)Z
    //   839: invokestatic 225	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   842: aastore
    //   843: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   846: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   849: goto -69 -> 780
    //   852: getstatic 212	android/os/Build$VERSION:SDK_INT	I
    //   855: bipush 9
    //   857: if_icmplt +27 -> 884
    //   860: aload 8
    //   862: ldc_w 330
    //   865: iconst_1
    //   866: anewarray 79	java/lang/Object
    //   869: dup
    //   870: iconst_0
    //   871: invokestatic 335	android/os/Environment:isExternalStorageRemovable	()Z
    //   874: invokestatic 225	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   877: aastore
    //   878: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   881: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   884: aload 8
    //   886: ldc_w 337
    //   889: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   892: aload_0
    //   893: invokevirtual 138	mahmed/net/synctuneswireless/ProblemReportActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   896: sipush 128
    //   899: invokevirtual 341	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   902: invokeinterface 328 1 0
    //   907: astore_1
    //   908: aload_1
    //   909: invokeinterface 308 1 0
    //   914: ifeq +44 -> 958
    //   917: aload_1
    //   918: invokeinterface 312 1 0
    //   923: checkcast 343	android/content/pm/ApplicationInfo
    //   926: astore_3
    //   927: aload 8
    //   929: new 266	java/lang/StringBuilder
    //   932: dup
    //   933: invokespecial 267	java/lang/StringBuilder:<init>	()V
    //   936: aload_3
    //   937: getfield 344	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   940: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   943: ldc_w 277
    //   946: invokevirtual 275	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   949: invokevirtual 278	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   952: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   955: goto -47 -> 908
    //   958: aload_0
    //   959: getstatic 349	mahmed/net/synctuneswireless/R$id:checkBox_full_log	I
    //   962: invokevirtual 353	mahmed/net/synctuneswireless/ProblemReportActivity:findViewById	(I)Landroid/view/View;
    //   965: checkcast 355	android/widget/CheckBox
    //   968: astore_1
    //   969: aload 5
    //   971: invokevirtual 358	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   974: astore_3
    //   975: aload_3
    //   976: ifnull +44 -> 1020
    //   979: aload_1
    //   980: invokevirtual 361	android/widget/CheckBox:isChecked	()Z
    //   983: ifne +20 -> 1003
    //   986: aload 6
    //   988: ifnull +15 -> 1003
    //   991: aload 6
    //   993: aload_3
    //   994: invokevirtual 365	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   997: invokevirtual 370	java/util/regex/Matcher:find	()Z
    //   1000: ifeq -31 -> 969
    //   1003: aload 8
    //   1005: aload_3
    //   1006: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   1009: aload 8
    //   1011: ldc_w 277
    //   1014: invokevirtual 128	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   1017: goto -48 -> 969
    //   1020: aload 8
    //   1022: invokevirtual 373	java/io/OutputStreamWriter:flush	()V
    //   1025: aload 8
    //   1027: invokevirtual 376	java/io/OutputStreamWriter:close	()V
    //   1030: aload 4
    //   1032: invokevirtual 315	java/lang/Process:destroy	()V
    //   1035: ldc_w 378
    //   1038: iconst_3
    //   1039: anewarray 79	java/lang/Object
    //   1042: dup
    //   1043: iconst_0
    //   1044: aload 7
    //   1046: aastore
    //   1047: dup
    //   1048: iconst_1
    //   1049: ldc_w 380
    //   1052: aastore
    //   1053: dup
    //   1054: iconst_2
    //   1055: ldc 115
    //   1057: aastore
    //   1058: invokestatic 95	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1061: astore_1
    //   1062: aload_1
    //   1063: areturn
    //   1064: astore_1
    //   1065: goto -367 -> 698
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1068	0	this	ProblemReportActivity
    //   0	1068	1	paramVarArgs	String[]
    //   413	305	2	i	int
    //   1	1005	3	localObject	Object
    //   10	1021	4	localProcess	Process
    //   34	936	5	localBufferedReader	java.io.BufferedReader
    //   62	930	6	localPattern	java.util.regex.Pattern
    //   74	971	7	str	String
    //   93	933	8	localOutputStreamWriter	java.io.OutputStreamWriter
    // Exception table:
    //   from	to	target	type
    //   95	414	694	java/lang/Exception
    //   414	491	694	java/lang/Exception
    //   491	607	694	java/lang/Exception
    //   607	622	694	java/lang/Exception
    //   622	691	694	java/lang/Exception
    //   708	718	694	java/lang/Exception
    //   722	750	694	java/lang/Exception
    //   753	780	694	java/lang/Exception
    //   780	849	694	java/lang/Exception
    //   852	884	694	java/lang/Exception
    //   884	908	694	java/lang/Exception
    //   908	955	694	java/lang/Exception
    //   958	969	694	java/lang/Exception
    //   969	975	694	java/lang/Exception
    //   979	986	694	java/lang/Exception
    //   991	1003	694	java/lang/Exception
    //   1003	1017	694	java/lang/Exception
    //   1020	1062	694	java/lang/Exception
    //   491	607	721	de/jockels/open/NoSecondaryStorageException
    //   2	12	1064	java/lang/Exception
    //   15	36	1064	java/lang/Exception
    //   39	64	1064	java/lang/Exception
    //   67	76	1064	java/lang/Exception
    //   79	95	1064	java/lang/Exception
  }
  
  private String uploadtoServer(String paramString)
  {
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(new File(paramString));
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(getString(R.string.error_report_url)).openConnection();
      localHttpURLConnection.setDoInput(true);
      localHttpURLConnection.setDoOutput(true);
      localHttpURLConnection.setUseCaches(false);
      localHttpURLConnection.setRequestMethod("POST");
      localHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
      localHttpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "*****");
      DataOutputStream localDataOutputStream = new DataOutputStream(localHttpURLConnection.getOutputStream());
      int i;
      int j;
      return null;
    }
    catch (Exception paramString)
    {
      try
      {
        localDataOutputStream.writeBytes("--" + "*****" + "\r\n");
        localDataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + paramString + "\"" + "\r\n");
        localDataOutputStream.writeBytes("\r\n");
        i = Math.min(localFileInputStream.available(), 1048576);
        paramString = new byte[i];
        for (j = localFileInputStream.read(paramString, 0, i); j > 0; j = localFileInputStream.read(paramString, 0, i))
        {
          localDataOutputStream.write(paramString, 0, i);
          i = Math.min(localFileInputStream.available(), 1048576);
        }
        localDataOutputStream.writeBytes("\r\n");
        localDataOutputStream.writeBytes("--" + "*****" + "--" + "\r\n");
        paramString = localHttpURLConnection.getResponseMessage();
        localFileInputStream.close();
        localDataOutputStream.flush();
        localDataOutputStream.close();
        return paramString;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      paramString = paramString;
    }
  }
  
  private List<String> validateFormValues()
  {
    ArrayList localArrayList = new ArrayList();
    String str1 = ((EditText)findViewById(R.id.editText_Name)).getText().toString();
    if (str1.trim().length() == 0)
    {
      Utils.messageT(getApplicationContext(), "Provide your name please..");
      return localArrayList;
    }
    String str2 = ((EditText)findViewById(R.id.editText_Email)).getText().toString();
    if ((str2.trim().length() == 0) || (!str2.contains("@")))
    {
      Utils.messageT(getApplicationContext(), "Email is required to contact you");
      return localArrayList;
    }
    String str3 = ((EditText)findViewById(R.id.editText_Problem)).getText().toString();
    if (str3.trim().length() == 0)
    {
      Utils.messageT(getApplicationContext(), "Please describe the problem you are facing, this will help me fix the issue");
      return localArrayList;
    }
    localArrayList.add(str1);
    localArrayList.add(str2);
    localArrayList.add(str3);
    return localArrayList;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_problem_report);
    ((Button)findViewById(R.id.btn_sendlog)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = ProblemReportActivity.this.validateFormValues();
        if (paramAnonymousView.isEmpty()) {
          return;
        }
        ProblemReportActivity.access$102(ProblemReportActivity.this, ProgressDialog.show(ProblemReportActivity.this, "Sending log", "Working please wait", true, false, null));
        new ProblemReportActivity.UploadLogTask(ProblemReportActivity.this, null).execute(new String[] { (String)paramAnonymousView.get(0), (String)paramAnonymousView.get(1), (String)paramAnonymousView.get(2) });
      }
    });
  }
  
  private class UploadLogTask
    extends AsyncTask<String, Integer, String>
  {
    private UploadLogTask() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      Object localObject = null;
      String str = ProblemReportActivity.this.generateLog(paramVarArgs);
      paramVarArgs = localObject;
      if (str != null)
      {
        paramVarArgs = ProblemReportActivity.this.uploadtoServer(str);
        publishProgress(new Integer[] { Integer.valueOf(10) });
      }
      return paramVarArgs;
    }
    
    protected void onPostExecute(String paramString)
    {
      ProblemReportActivity.this.pd.dismiss();
      if (paramString == null)
      {
        Utils.messageT(ProblemReportActivity.this.getApplicationContext(), "Error: Can not send..");
        return;
      }
      if (paramString.equalsIgnoreCase("OK"))
      {
        Utils.messageT(ProblemReportActivity.this.getApplicationContext(), "Message Sent Thank you..");
        ProblemReportActivity.this.finish();
        return;
      }
      Utils.messageT(ProblemReportActivity.this.getApplicationContext(), paramString);
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs) {}
  }
}
