package com.popularapp.periodcalendar.googledrive;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Builder;
import java.util.Iterator;
import java.util.List;

public final class a
{
  public static Drive a(GoogleAccountCredential paramGoogleAccountCredential, String paramString)
  {
    paramGoogleAccountCredential.setSelectedAccountName(paramString);
    return new Drive.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), paramGoogleAccountCredential).build();
  }
  
  /* Error */
  public static String a(java.io.InputStream paramInputStream, Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 39	java/lang/StringBuilder
    //   3: dup
    //   4: aload_1
    //   5: invokestatic 45	com/popularapp/periodcalendar/e/s:c	(Landroid/content/Context;)Ljava/lang/String;
    //   8: invokestatic 51	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   11: invokespecial 54	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   14: ldc 56
    //   16: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_2
    //   20: invokevirtual 60	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: invokevirtual 64	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   26: astore 5
    //   28: aconst_null
    //   29: astore 4
    //   31: aconst_null
    //   32: astore 6
    //   34: aload 4
    //   36: astore_2
    //   37: new 66	java/io/File
    //   40: dup
    //   41: aload 5
    //   43: invokespecial 67	java/io/File:<init>	(Ljava/lang/String;)V
    //   46: astore 7
    //   48: aload 4
    //   50: astore_2
    //   51: aload 7
    //   53: invokevirtual 71	java/io/File:exists	()Z
    //   56: ifeq +12 -> 68
    //   59: aload 4
    //   61: astore_2
    //   62: aload 7
    //   64: invokevirtual 74	java/io/File:delete	()Z
    //   67: pop
    //   68: aload 4
    //   70: astore_2
    //   71: aload 7
    //   73: invokevirtual 77	java/io/File:createNewFile	()Z
    //   76: pop
    //   77: aload 4
    //   79: astore_2
    //   80: new 79	java/io/FileOutputStream
    //   83: dup
    //   84: aload 5
    //   86: invokespecial 80	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   89: astore 4
    //   91: sipush 1204
    //   94: newarray byte
    //   96: astore_2
    //   97: aload_0
    //   98: aload_2
    //   99: invokevirtual 86	java/io/InputStream:read	([B)I
    //   102: istore_3
    //   103: iload_3
    //   104: iconst_m1
    //   105: if_icmpne +24 -> 129
    //   108: aload 4
    //   110: invokevirtual 89	java/io/FileOutputStream:close	()V
    //   113: aload 5
    //   115: astore_2
    //   116: aload_0
    //   117: ifnull +10 -> 127
    //   120: aload_0
    //   121: invokevirtual 90	java/io/InputStream:close	()V
    //   124: aload 5
    //   126: astore_2
    //   127: aload_2
    //   128: areturn
    //   129: aload 4
    //   131: aload_2
    //   132: iconst_0
    //   133: iload_3
    //   134: invokevirtual 94	java/io/FileOutputStream:write	([BII)V
    //   137: goto -40 -> 97
    //   140: astore 5
    //   142: aload 4
    //   144: astore_2
    //   145: aload_1
    //   146: ldc 96
    //   148: aload 5
    //   150: iconst_1
    //   151: invokestatic 101	com/popularapp/periodcalendar/e/u:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   154: aload 4
    //   156: astore_2
    //   157: aload 5
    //   159: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   162: ldc 106
    //   164: astore_2
    //   165: aload 4
    //   167: ifnull +8 -> 175
    //   170: aload 4
    //   172: invokevirtual 89	java/io/FileOutputStream:close	()V
    //   175: aload_0
    //   176: ifnull -49 -> 127
    //   179: aload_0
    //   180: invokevirtual 90	java/io/InputStream:close	()V
    //   183: ldc 106
    //   185: areturn
    //   186: astore_0
    //   187: aload_1
    //   188: ldc 108
    //   190: aload_0
    //   191: iconst_1
    //   192: invokestatic 101	com/popularapp/periodcalendar/e/u:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   195: aload_0
    //   196: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   199: ldc 106
    //   201: areturn
    //   202: astore 5
    //   204: aload_2
    //   205: astore 4
    //   207: aload 5
    //   209: astore_2
    //   210: aload 4
    //   212: ifnull +8 -> 220
    //   215: aload 4
    //   217: invokevirtual 89	java/io/FileOutputStream:close	()V
    //   220: aload_0
    //   221: ifnull +7 -> 228
    //   224: aload_0
    //   225: invokevirtual 90	java/io/InputStream:close	()V
    //   228: aload_2
    //   229: athrow
    //   230: astore_0
    //   231: aload_1
    //   232: ldc 108
    //   234: aload_0
    //   235: iconst_1
    //   236: invokestatic 101	com/popularapp/periodcalendar/e/u:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   239: aload_0
    //   240: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   243: goto -15 -> 228
    //   246: astore_0
    //   247: aload_1
    //   248: ldc 108
    //   250: aload_0
    //   251: iconst_1
    //   252: invokestatic 101	com/popularapp/periodcalendar/e/u:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   255: aload_0
    //   256: invokevirtual 104	java/io/IOException:printStackTrace	()V
    //   259: aload 5
    //   261: areturn
    //   262: astore_2
    //   263: goto -53 -> 210
    //   266: astore 5
    //   268: aload 6
    //   270: astore 4
    //   272: goto -130 -> 142
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	275	0	paramInputStream	java.io.InputStream
    //   0	275	1	paramContext	Context
    //   0	275	2	paramString	String
    //   102	32	3	i	int
    //   29	242	4	localObject1	Object
    //   26	99	5	str1	String
    //   140	18	5	localIOException1	java.io.IOException
    //   202	58	5	str2	String
    //   266	1	5	localIOException2	java.io.IOException
    //   32	237	6	localObject2	Object
    //   46	26	7	localFile	java.io.File
    // Exception table:
    //   from	to	target	type
    //   91	97	140	java/io/IOException
    //   97	103	140	java/io/IOException
    //   129	137	140	java/io/IOException
    //   170	175	186	java/io/IOException
    //   179	183	186	java/io/IOException
    //   37	48	202	finally
    //   51	59	202	finally
    //   62	68	202	finally
    //   71	77	202	finally
    //   80	91	202	finally
    //   145	154	202	finally
    //   157	162	202	finally
    //   215	220	230	java/io/IOException
    //   224	228	230	java/io/IOException
    //   108	113	246	java/io/IOException
    //   120	124	246	java/io/IOException
    //   91	97	262	finally
    //   97	103	262	finally
    //   129	137	262	finally
    //   37	48	266	java/io/IOException
    //   51	59	266	java/io/IOException
    //   62	68	266	java/io/IOException
    //   71	77	266	java/io/IOException
    //   80	91	266	java/io/IOException
  }
  
  public static boolean a(Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        bool1 = false;
      }
      PackageInfo localPackageInfo;
      boolean bool2;
      return false;
    }
    catch (Exception paramContext)
    {
      try
      {
        for (;;)
        {
          if (!paramContext.hasNext()) {
            return bool1;
          }
          localPackageInfo = (PackageInfo)paramContext.next();
          if (!TextUtils.isEmpty(localPackageInfo.packageName))
          {
            bool2 = localPackageInfo.packageName.equals("com.google.android.gms");
            if (bool2) {
              bool1 = true;
            }
          }
        }
        paramContext = paramContext;
      }
      catch (Exception paramContext)
      {
        for (;;) {}
      }
      paramContext.printStackTrace();
      return bool1;
    }
  }
}
