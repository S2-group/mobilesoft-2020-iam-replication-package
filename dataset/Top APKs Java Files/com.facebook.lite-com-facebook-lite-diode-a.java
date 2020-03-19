package com.facebook.lite.diode;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static final String a = a.class.getSimpleName();
  
  private a() {}
  
  /* Error */
  private static byte a(android.content.ContentResolver paramContentResolver, String paramString)
  {
    // Byte code:
    //   0: new 25	java/lang/StringBuilder
    //   3: dup
    //   4: ldc 27
    //   6: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   9: aload_1
    //   10: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: ldc 36
    //   15: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: invokevirtual 39	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   21: astore_1
    //   22: aload_0
    //   23: ldc 41
    //   25: invokestatic 47	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   28: aconst_null
    //   29: aload_1
    //   30: aconst_null
    //   31: aconst_null
    //   32: invokevirtual 53	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   35: astore_0
    //   36: aload_0
    //   37: ifnonnull +5 -> 42
    //   40: iconst_1
    //   41: ireturn
    //   42: aload_0
    //   43: invokestatic 56	com/facebook/lite/diode/a:a	(Landroid/database/Cursor;)B
    //   46: istore_2
    //   47: aload_0
    //   48: invokeinterface 61 1 0
    //   53: iload_2
    //   54: ireturn
    //   55: astore_0
    //   56: getstatic 16	com/facebook/lite/diode/a:a	Ljava/lang/String;
    //   59: ldc 63
    //   61: aload_0
    //   62: invokestatic 69	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   65: pop
    //   66: iconst_0
    //   67: ireturn
    //   68: astore_1
    //   69: aload_0
    //   70: invokeinterface 61 1 0
    //   75: aload_1
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	paramContentResolver	android.content.ContentResolver
    //   0	77	1	paramString	String
    //   46	8	2	b	byte
    // Exception table:
    //   from	to	target	type
    //   0	36	55	java/lang/Exception
    //   47	53	55	java/lang/Exception
    //   69	77	55	java/lang/Exception
    //   42	47	68	finally
  }
  
  private static byte a(Cursor paramCursor)
  {
    int k = 0;
    if (paramCursor.moveToNext())
    {
      int m = paramCursor.getColumnCount();
      int i;
      if (m > 0) {
        if (paramCursor.getInt(0) > 0) {
          i = 1;
        }
      }
      int j;
      for (;;)
      {
        j = k;
        if (m >= 3)
        {
          j = k;
          if (paramCursor.getInt(2) > 0) {
            j = 1;
          }
        }
        if (i == 0) {
          break;
        }
        return 3;
        i = 0;
        continue;
        i = 0;
      }
      if (j != 0) {
        return 4;
      }
      return 2;
    }
    return 0;
  }
  
  private static long a()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getAvailableBlocks();
      int i = localStatFs.getBlockSize();
      return i * l;
    }
    catch (Exception localException)
    {
      Log.e(a, "Free space error", localException);
    }
    return 0L;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramString.startsWith("messenger.diode.status.")))
    {
      paramString = paramString.substring(23);
      return Byte.toString(a(paramContext.getContentResolver(), paramString));
    }
    if ("messenger.diode.store".equals(paramString)) {
      return Boolean.toString(a(paramContext));
    }
    if ("messenger.diode.freespace".equals(paramString)) {
      return Long.toString(a());
    }
    return null;
  }
  
  private static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if (!"com.google.market".equals(localPackageInfo.packageName))
        {
          boolean bool = "com.android.vending".equals(localPackageInfo.packageName);
          if (!bool) {
            break;
          }
        }
        else
        {
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.e(a, "Play store error", paramContext);
    }
    return false;
  }
}
