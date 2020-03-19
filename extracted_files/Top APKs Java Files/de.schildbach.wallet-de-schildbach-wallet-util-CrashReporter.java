package de.schildbach.wallet.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrashReporter
{
  private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
  private static File backgroundTracesFile;
  private static File crashTraceFile;
  private static final Logger log = LoggerFactory.getLogger(CrashReporter.class);
  
  public CrashReporter() {}
  
  public static void appendInstalledPackages(Appendable paramAppendable, Context paramContext)
    throws IOException
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    Collections.sort(paramContext, new Comparator()
    {
      public int compare(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
      {
        return paramAnonymousPackageInfo1.packageName.compareTo(paramAnonymousPackageInfo2.packageName);
      }
    });
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      paramAppendable.append(String.format(Locale.US, "%s %s (%d) - %tF %tF\n", new Object[] { localPackageInfo.packageName, localPackageInfo.versionName, Integer.valueOf(localPackageInfo.versionCode), Long.valueOf(localPackageInfo.firstInstallTime), Long.valueOf(localPackageInfo.lastUpdateTime) }));
    }
  }
  
  /* Error */
  public static void appendSavedCrashTrace(Appendable paramAppendable)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 45	de/schildbach/wallet/util/CrashReporter:crashTraceFile	Ljava/io/File;
    //   3: invokevirtual 150	java/io/File:exists	()Z
    //   6: ifeq +54 -> 60
    //   9: new 152	java/io/BufferedReader
    //   12: dup
    //   13: new 154	java/io/InputStreamReader
    //   16: dup
    //   17: new 156	java/io/FileInputStream
    //   20: dup
    //   21: getstatic 45	de/schildbach/wallet/util/CrashReporter:crashTraceFile	Ljava/io/File;
    //   24: invokespecial 159	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   27: getstatic 165	java/nio/charset/StandardCharsets:UTF_8	Ljava/nio/charset/Charset;
    //   30: invokespecial 168	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   33: invokespecial 171	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   36: astore_2
    //   37: aconst_null
    //   38: astore_1
    //   39: aload_2
    //   40: aload_0
    //   41: invokestatic 175	de/schildbach/wallet/util/CrashReporter:copy	(Ljava/io/BufferedReader;Ljava/lang/Appendable;)V
    //   44: aload_2
    //   45: ifnull +11 -> 56
    //   48: iconst_0
    //   49: ifeq +28 -> 77
    //   52: aload_2
    //   53: invokevirtual 178	java/io/BufferedReader:close	()V
    //   56: invokestatic 181	de/schildbach/wallet/util/CrashReporter:deleteSaveCrashTrace	()Z
    //   59: pop
    //   60: return
    //   61: astore_0
    //   62: new 183	java/lang/NullPointerException
    //   65: dup
    //   66: invokespecial 184	java/lang/NullPointerException:<init>	()V
    //   69: athrow
    //   70: astore_0
    //   71: invokestatic 181	de/schildbach/wallet/util/CrashReporter:deleteSaveCrashTrace	()Z
    //   74: pop
    //   75: aload_0
    //   76: athrow
    //   77: aload_2
    //   78: invokevirtual 178	java/io/BufferedReader:close	()V
    //   81: goto -25 -> 56
    //   84: astore_0
    //   85: aload_0
    //   86: astore_1
    //   87: aload_0
    //   88: athrow
    //   89: astore_0
    //   90: aload_2
    //   91: ifnull +11 -> 102
    //   94: aload_1
    //   95: ifnull +18 -> 113
    //   98: aload_2
    //   99: invokevirtual 178	java/io/BufferedReader:close	()V
    //   102: aload_0
    //   103: athrow
    //   104: astore_2
    //   105: aload_1
    //   106: aload_2
    //   107: invokevirtual 188	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   110: goto -8 -> 102
    //   113: aload_2
    //   114: invokevirtual 178	java/io/BufferedReader:close	()V
    //   117: goto -15 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	120	0	paramAppendable	Appendable
    //   38	68	1	localAppendable	Appendable
    //   36	63	2	localBufferedReader	BufferedReader
    //   104	10	2	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   52	56	61	java/lang/Throwable
    //   9	37	70	finally
    //   52	56	70	finally
    //   62	70	70	finally
    //   77	81	70	finally
    //   98	102	70	finally
    //   102	104	70	finally
    //   105	110	70	finally
    //   113	117	70	finally
    //   39	44	84	java/lang/Throwable
    //   39	44	89	finally
    //   87	89	89	finally
    //   98	102	104	java/lang/Throwable
  }
  
  private static void appendTrace(PrintWriter paramPrintWriter, Throwable paramThrowable)
  {
    paramThrowable.printStackTrace(paramPrintWriter);
    for (paramThrowable = paramThrowable.getCause(); paramThrowable != null; paramThrowable = paramThrowable.getCause())
    {
      paramPrintWriter.println("\nCause:\n");
      paramThrowable.printStackTrace(paramPrintWriter);
    }
  }
  
  public static boolean collectSavedBackgroundTraces(File paramFile)
  {
    return backgroundTracesFile.renameTo(paramFile);
  }
  
  private static void copy(BufferedReader paramBufferedReader, Appendable paramAppendable)
    throws IOException
  {
    for (;;)
    {
      String str = paramBufferedReader.readLine();
      if (str == null) {
        return;
      }
      paramAppendable.append(str).append('\n');
    }
  }
  
  public static boolean deleteSaveCrashTrace()
  {
    return crashTraceFile.delete();
  }
  
  public static boolean hasSavedCrashTrace()
  {
    return crashTraceFile.exists();
  }
  
  public static void init(File paramFile)
  {
    backgroundTracesFile = new File(paramFile, "background.trace");
    crashTraceFile = new File(paramFile, "crash.trace");
    Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
  }
  
  /* Error */
  public static void saveBackgroundTrace(Throwable paramThrowable, PackageInfo paramPackageInfo)
  {
    // Byte code:
    //   0: getstatic 208	de/schildbach/wallet/util/CrashReporter:backgroundTracesFile	Ljava/io/File;
    //   3: astore_3
    //   4: aload_3
    //   5: monitorenter
    //   6: new 200	java/io/PrintWriter
    //   9: dup
    //   10: new 246	java/io/OutputStreamWriter
    //   13: dup
    //   14: new 248	java/io/FileOutputStream
    //   17: dup
    //   18: getstatic 208	de/schildbach/wallet/util/CrashReporter:backgroundTracesFile	Ljava/io/File;
    //   21: iconst_1
    //   22: invokespecial 251	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   25: getstatic 165	java/nio/charset/StandardCharsets:UTF_8	Ljava/nio/charset/Charset;
    //   28: invokespecial 254	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V
    //   31: invokespecial 257	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   34: astore 4
    //   36: aconst_null
    //   37: astore_2
    //   38: new 259	java/util/GregorianCalendar
    //   41: dup
    //   42: getstatic 27	de/schildbach/wallet/util/CrashReporter:UTC	Ljava/util/TimeZone;
    //   45: invokespecial 262	java/util/GregorianCalendar:<init>	(Ljava/util/TimeZone;)V
    //   48: astore 5
    //   50: aload 4
    //   52: getstatic 97	java/util/Locale:US	Ljava/util/Locale;
    //   55: ldc_w 264
    //   58: iconst_5
    //   59: anewarray 4	java/lang/Object
    //   62: dup
    //   63: iconst_0
    //   64: aload 5
    //   66: aastore
    //   67: dup
    //   68: iconst_1
    //   69: aload 5
    //   71: aastore
    //   72: dup
    //   73: iconst_2
    //   74: aload 5
    //   76: aastore
    //   77: dup
    //   78: iconst_3
    //   79: aload_1
    //   80: getfield 106	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   83: aastore
    //   84: dup
    //   85: iconst_4
    //   86: aload_1
    //   87: getfield 110	android/content/pm/PackageInfo:versionCode	I
    //   90: invokestatic 116	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   93: aastore
    //   94: invokestatic 134	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   97: invokevirtual 204	java/io/PrintWriter:println	(Ljava/lang/String;)V
    //   100: aload 4
    //   102: aload_0
    //   103: invokestatic 50	de/schildbach/wallet/util/CrashReporter:appendTrace	(Ljava/io/PrintWriter;Ljava/lang/Throwable;)V
    //   106: aload 4
    //   108: ifnull +12 -> 120
    //   111: iconst_0
    //   112: ifeq +41 -> 153
    //   115: aload 4
    //   117: invokevirtual 265	java/io/PrintWriter:close	()V
    //   120: aload_3
    //   121: monitorexit
    //   122: return
    //   123: astore_0
    //   124: new 183	java/lang/NullPointerException
    //   127: dup
    //   128: invokespecial 184	java/lang/NullPointerException:<init>	()V
    //   131: athrow
    //   132: astore_0
    //   133: getstatic 35	de/schildbach/wallet/util/CrashReporter:log	Lorg/slf4j/Logger;
    //   136: ldc_w 267
    //   139: aload_0
    //   140: invokeinterface 273 3 0
    //   145: goto -25 -> 120
    //   148: astore_0
    //   149: aload_3
    //   150: monitorexit
    //   151: aload_0
    //   152: athrow
    //   153: aload 4
    //   155: invokevirtual 265	java/io/PrintWriter:close	()V
    //   158: goto -38 -> 120
    //   161: astore_0
    //   162: aload_0
    //   163: athrow
    //   164: astore_1
    //   165: aload 4
    //   167: ifnull +12 -> 179
    //   170: aload_0
    //   171: ifnull +19 -> 190
    //   174: aload 4
    //   176: invokevirtual 265	java/io/PrintWriter:close	()V
    //   179: aload_1
    //   180: athrow
    //   181: astore_2
    //   182: aload_0
    //   183: aload_2
    //   184: invokevirtual 188	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   187: goto -8 -> 179
    //   190: aload 4
    //   192: invokevirtual 265	java/io/PrintWriter:close	()V
    //   195: goto -16 -> 179
    //   198: astore_1
    //   199: aload_2
    //   200: astore_0
    //   201: goto -36 -> 165
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	204	0	paramThrowable	Throwable
    //   0	204	1	paramPackageInfo	PackageInfo
    //   37	1	2	localObject	Object
    //   181	19	2	localThrowable	Throwable
    //   3	147	3	localFile	File
    //   34	157	4	localPrintWriter	PrintWriter
    //   48	27	5	localGregorianCalendar	java.util.GregorianCalendar
    // Exception table:
    //   from	to	target	type
    //   115	120	123	java/lang/Throwable
    //   6	36	132	java/io/IOException
    //   115	120	132	java/io/IOException
    //   124	132	132	java/io/IOException
    //   153	158	132	java/io/IOException
    //   174	179	132	java/io/IOException
    //   179	181	132	java/io/IOException
    //   182	187	132	java/io/IOException
    //   190	195	132	java/io/IOException
    //   6	36	148	finally
    //   115	120	148	finally
    //   120	122	148	finally
    //   124	132	148	finally
    //   133	145	148	finally
    //   149	151	148	finally
    //   153	158	148	finally
    //   174	179	148	finally
    //   179	181	148	finally
    //   182	187	148	finally
    //   190	195	148	finally
    //   38	106	161	java/lang/Throwable
    //   162	164	164	finally
    //   174	179	181	java/lang/Throwable
    //   38	106	198	finally
  }
  
  private static class ExceptionHandler
    implements Thread.UncaughtExceptionHandler
  {
    private final Thread.UncaughtExceptionHandler previousHandler;
    
    public ExceptionHandler(Thread.UncaughtExceptionHandler paramUncaughtExceptionHandler)
    {
      this.previousHandler = paramUncaughtExceptionHandler;
    }
    
    private void saveCrashTrace(Throwable paramThrowable)
      throws IOException
    {
      PrintWriter localPrintWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(CrashReporter.crashTraceFile), StandardCharsets.UTF_8));
      CrashReporter.appendTrace(localPrintWriter, paramThrowable);
      localPrintWriter.close();
    }
    
    /* Error */
    public void uncaughtException(Thread paramThread, Throwable paramThrowable)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: invokestatic 62	de/schildbach/wallet/util/CrashReporter:access$000	()Lorg/slf4j/Logger;
      //   5: ldc 64
      //   7: aload_2
      //   8: invokeinterface 70 3 0
      //   13: aload_0
      //   14: aload_2
      //   15: invokespecial 72	de/schildbach/wallet/util/CrashReporter$ExceptionHandler:saveCrashTrace	(Ljava/lang/Throwable;)V
      //   18: aload_0
      //   19: getfield 18	de/schildbach/wallet/util/CrashReporter$ExceptionHandler:previousHandler	Ljava/lang/Thread$UncaughtExceptionHandler;
      //   22: aload_1
      //   23: aload_2
      //   24: invokeinterface 74 3 0
      //   29: aload_0
      //   30: monitorexit
      //   31: return
      //   32: astore_3
      //   33: invokestatic 62	de/schildbach/wallet/util/CrashReporter:access$000	()Lorg/slf4j/Logger;
      //   36: ldc 76
      //   38: aload_3
      //   39: invokeinterface 79 3 0
      //   44: goto -26 -> 18
      //   47: astore_1
      //   48: aload_0
      //   49: monitorexit
      //   50: aload_1
      //   51: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	52	0	this	ExceptionHandler
      //   0	52	1	paramThread	Thread
      //   0	52	2	paramThrowable	Throwable
      //   32	7	3	localIOException	IOException
      // Exception table:
      //   from	to	target	type
      //   13	18	32	java/io/IOException
      //   2	13	47	finally
      //   13	18	47	finally
      //   18	29	47	finally
      //   33	44	47	finally
    }
  }
}
