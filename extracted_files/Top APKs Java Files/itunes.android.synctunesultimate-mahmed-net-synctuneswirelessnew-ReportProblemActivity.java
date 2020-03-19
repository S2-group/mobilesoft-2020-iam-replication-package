package mahmed.net.synctuneswirelessnew;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import mahmed.net.synctuneswirelessnew.common.Utils;

public class ReportProblemActivity
  extends AppCompatActivity
{
  private ProgressDialog pd = null;
  
  public ReportProblemActivity() {}
  
  /* Error */
  private String generateLog(String... paramVarArgs)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: invokestatic 50	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   5: ldc 52
    //   7: invokevirtual 56	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   10: astore 4
    //   12: aload 4
    //   14: astore_3
    //   15: new 58	java/io/BufferedReader
    //   18: dup
    //   19: new 60	java/io/InputStreamReader
    //   22: dup
    //   23: aload 4
    //   25: invokevirtual 66	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   28: invokespecial 69	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   31: invokespecial 72	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   34: astore 5
    //   36: aload 4
    //   38: astore_3
    //   39: ldc 74
    //   41: iconst_1
    //   42: anewarray 76	java/lang/Object
    //   45: dup
    //   46: iconst_0
    //   47: aload_0
    //   48: getstatic 82	mahmed/net/synctuneswirelessnew/R$string:app_prefix	I
    //   51: invokevirtual 86	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getString	(I)Ljava/lang/String;
    //   54: aastore
    //   55: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   58: iconst_0
    //   59: invokestatic 98	java/util/regex/Pattern:compile	(Ljava/lang/String;I)Ljava/util/regex/Pattern;
    //   62: astore 6
    //   64: aload 4
    //   66: astore_3
    //   67: aload_0
    //   68: invokevirtual 102	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getFilesDir	()Ljava/io/File;
    //   71: invokevirtual 108	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   74: astore 7
    //   76: aload 4
    //   78: astore_3
    //   79: new 110	java/io/OutputStreamWriter
    //   82: dup
    //   83: aload_0
    //   84: ldc 112
    //   86: iconst_1
    //   87: invokevirtual 116	mahmed/net/synctuneswirelessnew/ReportProblemActivity:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   90: invokespecial 119	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   93: astore 8
    //   95: aload 8
    //   97: ldc 121
    //   99: iconst_1
    //   100: anewarray 76	java/lang/Object
    //   103: dup
    //   104: iconst_0
    //   105: aload_1
    //   106: iconst_0
    //   107: aaload
    //   108: aastore
    //   109: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   112: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   115: aload 8
    //   117: ldc 127
    //   119: iconst_1
    //   120: anewarray 76	java/lang/Object
    //   123: dup
    //   124: iconst_0
    //   125: aload_1
    //   126: iconst_1
    //   127: aaload
    //   128: aastore
    //   129: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   132: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   135: aload 8
    //   137: ldc -127
    //   139: iconst_1
    //   140: anewarray 76	java/lang/Object
    //   143: dup
    //   144: iconst_0
    //   145: aload_1
    //   146: iconst_2
    //   147: aaload
    //   148: aastore
    //   149: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   152: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   155: aload 8
    //   157: ldc -125
    //   159: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   162: aload_0
    //   163: invokevirtual 135	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   166: aload_0
    //   167: invokevirtual 138	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getPackageName	()Ljava/lang/String;
    //   170: iconst_0
    //   171: invokevirtual 144	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   174: astore_1
    //   175: aload 8
    //   177: ldc -110
    //   179: iconst_1
    //   180: anewarray 76	java/lang/Object
    //   183: dup
    //   184: iconst_0
    //   185: aload_1
    //   186: getfield 152	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   189: aastore
    //   190: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   193: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   196: aload 8
    //   198: ldc -102
    //   200: iconst_1
    //   201: anewarray 76	java/lang/Object
    //   204: dup
    //   205: iconst_0
    //   206: aload_1
    //   207: getfield 157	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   210: aastore
    //   211: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   214: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   217: aload 8
    //   219: ldc -97
    //   221: iconst_1
    //   222: anewarray 76	java/lang/Object
    //   225: dup
    //   226: iconst_0
    //   227: aload_1
    //   228: getfield 162	android/content/pm/PackageInfo:versionCode	I
    //   231: invokestatic 168	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   234: aastore
    //   235: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   238: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   241: new 170	java/lang/StringBuffer
    //   244: dup
    //   245: invokespecial 171	java/lang/StringBuffer:<init>	()V
    //   248: astore_1
    //   249: aload_1
    //   250: ldc -83
    //   252: iconst_1
    //   253: anewarray 76	java/lang/Object
    //   256: dup
    //   257: iconst_0
    //   258: getstatic 178	android/os/Build:PRODUCT	Ljava/lang/String;
    //   261: aastore
    //   262: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   265: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   268: pop
    //   269: aload_1
    //   270: ldc -72
    //   272: iconst_1
    //   273: anewarray 76	java/lang/Object
    //   276: dup
    //   277: iconst_0
    //   278: getstatic 187	android/os/Build:BRAND	Ljava/lang/String;
    //   281: aastore
    //   282: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   285: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   288: pop
    //   289: aload_1
    //   290: ldc -67
    //   292: iconst_1
    //   293: anewarray 76	java/lang/Object
    //   296: dup
    //   297: iconst_0
    //   298: getstatic 192	android/os/Build:MODEL	Ljava/lang/String;
    //   301: aastore
    //   302: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   305: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   308: pop
    //   309: aload_1
    //   310: ldc -62
    //   312: iconst_1
    //   313: anewarray 76	java/lang/Object
    //   316: dup
    //   317: iconst_0
    //   318: getstatic 197	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   321: aastore
    //   322: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   325: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   328: pop
    //   329: aload_1
    //   330: ldc -57
    //   332: iconst_1
    //   333: anewarray 76	java/lang/Object
    //   336: dup
    //   337: iconst_0
    //   338: getstatic 202	android/os/Build:DEVICE	Ljava/lang/String;
    //   341: aastore
    //   342: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   345: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   348: pop
    //   349: aload_1
    //   350: ldc -52
    //   352: iconst_1
    //   353: anewarray 76	java/lang/Object
    //   356: dup
    //   357: iconst_0
    //   358: getstatic 209	android/os/Build$VERSION:SDK_INT	I
    //   361: invokestatic 168	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   364: aastore
    //   365: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   368: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   371: pop
    //   372: aload_1
    //   373: ldc -45
    //   375: iconst_1
    //   376: anewarray 76	java/lang/Object
    //   379: dup
    //   380: iconst_0
    //   381: aload_0
    //   382: invokestatic 217	mahmed/net/synctuneswirelessnew/common/Utils:isServiceRunning	(Landroid/content/Context;)Z
    //   385: invokestatic 222	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   388: aastore
    //   389: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   392: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   395: pop
    //   396: getstatic 209	android/os/Build$VERSION:SDK_INT	I
    //   399: bipush 17
    //   401: if_icmplt +305 -> 706
    //   404: aload_0
    //   405: invokevirtual 226	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   408: ldc -28
    //   410: invokestatic 234	android/provider/Settings$Global:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   413: istore_2
    //   414: aload_1
    //   415: ldc -20
    //   417: iconst_1
    //   418: anewarray 76	java/lang/Object
    //   421: dup
    //   422: iconst_0
    //   423: iload_2
    //   424: invokestatic 168	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   427: aastore
    //   428: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   431: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   434: pop
    //   435: aload_1
    //   436: ldc -18
    //   438: iconst_1
    //   439: anewarray 76	java/lang/Object
    //   442: dup
    //   443: iconst_0
    //   444: new 240	mahmed/net/synctuneswirelessnew/common/Settings
    //   447: dup
    //   448: aload_0
    //   449: invokevirtual 244	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getApplicationContext	()Landroid/content/Context;
    //   452: invokespecial 247	mahmed/net/synctuneswirelessnew/common/Settings:<init>	(Landroid/content/Context;)V
    //   455: invokevirtual 250	mahmed/net/synctuneswirelessnew/common/Settings:toString	()Ljava/lang/String;
    //   458: aastore
    //   459: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   462: invokevirtual 182	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   465: pop
    //   466: aload 8
    //   468: aload_1
    //   469: invokevirtual 251	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   472: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   475: aload 8
    //   477: ldc -3
    //   479: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   482: aload 8
    //   484: ldc -1
    //   486: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   489: invokestatic 261	de/jockels/open/Environment2:getSecondaryExternalStorage	()Lde/jockels/open/Device;
    //   492: astore_1
    //   493: aload 8
    //   495: new 263	java/lang/StringBuilder
    //   498: dup
    //   499: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   502: aload_1
    //   503: invokevirtual 269	de/jockels/open/Device:getMountPoint	()Ljava/lang/String;
    //   506: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: ldc_w 274
    //   512: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   518: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   521: aload 8
    //   523: new 263	java/lang/StringBuilder
    //   526: dup
    //   527: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   530: aload_1
    //   531: invokevirtual 278	de/jockels/open/Device:getName	()Ljava/lang/String;
    //   534: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: ldc_w 274
    //   540: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   546: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   549: aload 8
    //   551: ldc_w 280
    //   554: iconst_1
    //   555: anewarray 76	java/lang/Object
    //   558: dup
    //   559: iconst_0
    //   560: aload_1
    //   561: invokevirtual 269	de/jockels/open/Device:getMountPoint	()Ljava/lang/String;
    //   564: invokestatic 284	mahmed/net/synctuneswirelessnew/common/Utils:makeTestFile	(Ljava/lang/String;)Z
    //   567: invokestatic 222	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   570: aastore
    //   571: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   574: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   577: aload 8
    //   579: new 263	java/lang/StringBuilder
    //   582: dup
    //   583: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   586: aload_1
    //   587: invokevirtual 287	de/jockels/open/Device:getState	()Ljava/lang/String;
    //   590: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   593: ldc_w 274
    //   596: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   599: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   602: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   605: aload 8
    //   607: ldc_w 289
    //   610: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   613: invokestatic 293	mahmed/net/synctuneswirelessnew/common/Utils:getExternalMounts	()Ljava/util/HashSet;
    //   616: invokevirtual 299	java/util/HashSet:iterator	()Ljava/util/Iterator;
    //   619: astore_1
    //   620: aload_1
    //   621: invokeinterface 305 1 0
    //   626: ifeq +125 -> 751
    //   629: aload_1
    //   630: invokeinterface 309 1 0
    //   635: checkcast 88	java/lang/String
    //   638: astore_3
    //   639: aload 8
    //   641: new 263	java/lang/StringBuilder
    //   644: dup
    //   645: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   648: aload_3
    //   649: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   652: ldc_w 274
    //   655: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   658: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   661: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   664: aload 8
    //   666: ldc_w 280
    //   669: iconst_1
    //   670: anewarray 76	java/lang/Object
    //   673: dup
    //   674: iconst_0
    //   675: aload_3
    //   676: invokestatic 284	mahmed/net/synctuneswirelessnew/common/Utils:makeTestFile	(Ljava/lang/String;)Z
    //   679: invokestatic 222	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   682: aastore
    //   683: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   686: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   689: goto -69 -> 620
    //   692: astore_1
    //   693: aload 4
    //   695: astore_3
    //   696: aload_3
    //   697: ifnull +7 -> 704
    //   700: aload_3
    //   701: invokevirtual 312	java/lang/Process:destroy	()V
    //   704: aconst_null
    //   705: areturn
    //   706: aload_0
    //   707: invokevirtual 226	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   710: ldc -28
    //   712: invokestatic 315	android/provider/Settings$System:getInt	(Landroid/content/ContentResolver;Ljava/lang/String;)I
    //   715: istore_2
    //   716: goto -302 -> 414
    //   719: astore_1
    //   720: aload 8
    //   722: new 263	java/lang/StringBuilder
    //   725: dup
    //   726: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   729: aload_1
    //   730: invokevirtual 316	de/jockels/open/NoSecondaryStorageException:toString	()Ljava/lang/String;
    //   733: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: ldc_w 274
    //   739: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   745: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   748: goto -143 -> 605
    //   751: aload 8
    //   753: ldc_w 318
    //   756: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   759: getstatic 209	android/os/Build$VERSION:SDK_INT	I
    //   762: bipush 19
    //   764: if_icmplt +86 -> 850
    //   767: aload_0
    //   768: iconst_0
    //   769: invokestatic 322	mahmed/net/synctuneswirelessnew/common/Utils:getExternalDirsKitKat	(Landroid/content/Context;Z)Ljava/util/List;
    //   772: invokeinterface 325 1 0
    //   777: astore_1
    //   778: aload_1
    //   779: invokeinterface 305 1 0
    //   784: ifeq +66 -> 850
    //   787: aload_1
    //   788: invokeinterface 309 1 0
    //   793: checkcast 88	java/lang/String
    //   796: astore_3
    //   797: aload 8
    //   799: new 263	java/lang/StringBuilder
    //   802: dup
    //   803: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   806: aload_3
    //   807: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: ldc_w 274
    //   813: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   819: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   822: aload 8
    //   824: ldc_w 280
    //   827: iconst_1
    //   828: anewarray 76	java/lang/Object
    //   831: dup
    //   832: iconst_0
    //   833: aload_3
    //   834: invokestatic 284	mahmed/net/synctuneswirelessnew/common/Utils:makeTestFile	(Ljava/lang/String;)Z
    //   837: invokestatic 222	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   840: aastore
    //   841: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   844: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   847: goto -69 -> 778
    //   850: getstatic 209	android/os/Build$VERSION:SDK_INT	I
    //   853: bipush 9
    //   855: if_icmplt +27 -> 882
    //   858: aload 8
    //   860: ldc_w 327
    //   863: iconst_1
    //   864: anewarray 76	java/lang/Object
    //   867: dup
    //   868: iconst_0
    //   869: invokestatic 332	android/os/Environment:isExternalStorageRemovable	()Z
    //   872: invokestatic 222	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   875: aastore
    //   876: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   879: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   882: aload 8
    //   884: ldc_w 334
    //   887: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   890: aload_0
    //   891: invokevirtual 135	mahmed/net/synctuneswirelessnew/ReportProblemActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   894: sipush 128
    //   897: invokevirtual 338	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   900: invokeinterface 325 1 0
    //   905: astore_1
    //   906: aload_1
    //   907: invokeinterface 305 1 0
    //   912: ifeq +44 -> 956
    //   915: aload_1
    //   916: invokeinterface 309 1 0
    //   921: checkcast 340	android/content/pm/ApplicationInfo
    //   924: astore_3
    //   925: aload 8
    //   927: new 263	java/lang/StringBuilder
    //   930: dup
    //   931: invokespecial 264	java/lang/StringBuilder:<init>	()V
    //   934: aload_3
    //   935: getfield 341	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   938: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   941: ldc_w 274
    //   944: invokevirtual 272	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   947: invokevirtual 275	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   950: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   953: goto -47 -> 906
    //   956: aload_0
    //   957: getstatic 346	mahmed/net/synctuneswirelessnew/R$id:checkBox_full_log	I
    //   960: invokevirtual 350	mahmed/net/synctuneswirelessnew/ReportProblemActivity:findViewById	(I)Landroid/view/View;
    //   963: checkcast 352	android/widget/CheckBox
    //   966: astore_1
    //   967: aload 5
    //   969: invokevirtual 355	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   972: astore_3
    //   973: aload_3
    //   974: ifnull +44 -> 1018
    //   977: aload_1
    //   978: invokevirtual 358	android/widget/CheckBox:isChecked	()Z
    //   981: ifne +20 -> 1001
    //   984: aload 6
    //   986: ifnull +15 -> 1001
    //   989: aload 6
    //   991: aload_3
    //   992: invokevirtual 362	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   995: invokevirtual 367	java/util/regex/Matcher:find	()Z
    //   998: ifeq -31 -> 967
    //   1001: aload 8
    //   1003: aload_3
    //   1004: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   1007: aload 8
    //   1009: ldc_w 274
    //   1012: invokevirtual 125	java/io/OutputStreamWriter:write	(Ljava/lang/String;)V
    //   1015: goto -48 -> 967
    //   1018: aload 8
    //   1020: invokevirtual 370	java/io/OutputStreamWriter:flush	()V
    //   1023: aload 8
    //   1025: invokevirtual 373	java/io/OutputStreamWriter:close	()V
    //   1028: aload 4
    //   1030: invokevirtual 312	java/lang/Process:destroy	()V
    //   1033: ldc_w 375
    //   1036: iconst_3
    //   1037: anewarray 76	java/lang/Object
    //   1040: dup
    //   1041: iconst_0
    //   1042: aload 7
    //   1044: aastore
    //   1045: dup
    //   1046: iconst_1
    //   1047: ldc_w 377
    //   1050: aastore
    //   1051: dup
    //   1052: iconst_2
    //   1053: ldc 112
    //   1055: aastore
    //   1056: invokestatic 92	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1059: astore_1
    //   1060: aload_1
    //   1061: areturn
    //   1062: astore_1
    //   1063: goto -367 -> 696
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1066	0	this	ReportProblemActivity
    //   0	1066	1	paramVarArgs	String[]
    //   413	303	2	i	int
    //   1	1003	3	localObject	Object
    //   10	1019	4	localProcess	Process
    //   34	934	5	localBufferedReader	java.io.BufferedReader
    //   62	928	6	localPattern	java.util.regex.Pattern
    //   74	969	7	str	String
    //   93	931	8	localOutputStreamWriter	java.io.OutputStreamWriter
    // Exception table:
    //   from	to	target	type
    //   95	414	692	java/lang/Exception
    //   414	489	692	java/lang/Exception
    //   489	605	692	java/lang/Exception
    //   605	620	692	java/lang/Exception
    //   620	689	692	java/lang/Exception
    //   706	716	692	java/lang/Exception
    //   720	748	692	java/lang/Exception
    //   751	778	692	java/lang/Exception
    //   778	847	692	java/lang/Exception
    //   850	882	692	java/lang/Exception
    //   882	906	692	java/lang/Exception
    //   906	953	692	java/lang/Exception
    //   956	967	692	java/lang/Exception
    //   967	973	692	java/lang/Exception
    //   977	984	692	java/lang/Exception
    //   989	1001	692	java/lang/Exception
    //   1001	1015	692	java/lang/Exception
    //   1018	1060	692	java/lang/Exception
    //   489	605	719	de/jockels/open/NoSecondaryStorageException
    //   2	12	1062	java/lang/Exception
    //   15	36	1062	java/lang/Exception
    //   39	64	1062	java/lang/Exception
    //   67	76	1062	java/lang/Exception
    //   79	95	1062	java/lang/Exception
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
    setContentView(R.layout.activity_report_problem);
    setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ((Button)findViewById(R.id.btn_sendlog)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = ReportProblemActivity.this.validateFormValues();
        if (paramAnonymousView.isEmpty()) {
          return;
        }
        ReportProblemActivity.access$102(ReportProblemActivity.this, ProgressDialog.show(ReportProblemActivity.this, "Sending Message", "Working please wait", true, false, null));
        new ReportProblemActivity.UploadLogTask(ReportProblemActivity.this, null).execute(new String[] { (String)paramAnonymousView.get(0), (String)paramAnonymousView.get(1), (String)paramAnonymousView.get(2) });
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
      String str = ReportProblemActivity.this.generateLog(paramVarArgs);
      paramVarArgs = localObject;
      if (str != null)
      {
        paramVarArgs = ReportProblemActivity.this.uploadtoServer(str);
        publishProgress(new Integer[] { Integer.valueOf(10) });
      }
      return paramVarArgs;
    }
    
    protected void onPostExecute(String paramString)
    {
      ReportProblemActivity.this.pd.dismiss();
      if (paramString == null)
      {
        Utils.messageT(ReportProblemActivity.this.getApplicationContext(), "Error: Can not send..");
        return;
      }
      if (paramString.equalsIgnoreCase("OK"))
      {
        Utils.messageT(ReportProblemActivity.this.getApplicationContext(), "Message Sent, thank you");
        return;
      }
      Utils.messageT(ReportProblemActivity.this.getApplicationContext(), paramString);
    }
    
    protected void onProgressUpdate(Integer... paramVarArgs) {}
  }
}
