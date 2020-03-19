package com.thinkyeah.common.i;

import android.content.Context;
import com.thinkyeah.common.p;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public final class e
{
  private static final p a = p.j("DebugInfoPrinter");
  
  public static final class a
    extends e.b
  {
    public a(Context paramContext, File paramFile)
    {
      super(paramFile);
    }
    
    /* Error */
    public final void a()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	com/thinkyeah/common/i/e$b:b	Ljava/io/File;
      //   4: astore_1
      //   5: aload_1
      //   6: invokestatic 20	com/thinkyeah/common/i/e$a:a	(Ljava/io/File;)Z
      //   9: ifne +35 -> 44
      //   12: invokestatic 23	com/thinkyeah/common/i/e:a	()Lcom/thinkyeah/common/p;
      //   15: astore_2
      //   16: new 25	java/lang/StringBuilder
      //   19: dup
      //   20: ldc 27
      //   22: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   25: astore_3
      //   26: aload_3
      //   27: aload_1
      //   28: invokevirtual 36	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   31: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   34: pop
      //   35: aload_2
      //   36: aload_3
      //   37: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokevirtual 48	com/thinkyeah/common/p:d	(Ljava/lang/String;)V
      //   43: return
      //   44: new 50	java/io/FileOutputStream
      //   47: dup
      //   48: aload_1
      //   49: invokespecial 53	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   52: astore_2
      //   53: new 25	java/lang/StringBuilder
      //   56: dup
      //   57: ldc 55
      //   59: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   62: astore_1
      //   63: aload_1
      //   64: getstatic 61	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   67: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   70: pop
      //   71: aload_1
      //   72: ldc 63
      //   74: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   77: pop
      //   78: aload_1
      //   79: getstatic 67	android/os/Build$VERSION:SDK_INT	I
      //   82: invokevirtual 70	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   85: pop
      //   86: aload_1
      //   87: ldc 72
      //   89: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   92: pop
      //   93: aload_2
      //   94: aload_1
      //   95: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   98: invokestatic 75	com/thinkyeah/common/i/e$a:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   101: new 25	java/lang/StringBuilder
      //   104: dup
      //   105: ldc 77
      //   107: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   110: astore_1
      //   111: aload_1
      //   112: invokestatic 83	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   115: invokevirtual 86	java/util/Locale:getLanguage	()Ljava/lang/String;
      //   118: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   121: pop
      //   122: aload_1
      //   123: ldc 88
      //   125: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   128: pop
      //   129: aload_1
      //   130: invokestatic 83	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   133: invokevirtual 91	java/util/Locale:getCountry	()Ljava/lang/String;
      //   136: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   139: pop
      //   140: aload_2
      //   141: aload_1
      //   142: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   145: invokestatic 75	com/thinkyeah/common/i/e$a:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   148: new 25	java/lang/StringBuilder
      //   151: dup
      //   152: ldc 93
      //   154: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   157: astore_1
      //   158: aload_1
      //   159: getstatic 98	android/os/Build:MODEL	Ljava/lang/String;
      //   162: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   165: pop
      //   166: aload_2
      //   167: aload_1
      //   168: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   171: invokestatic 75	com/thinkyeah/common/i/e$a:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   174: new 25	java/lang/StringBuilder
      //   177: dup
      //   178: ldc 100
      //   180: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   183: astore_1
      //   184: aload_1
      //   185: getstatic 103	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   188: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   191: pop
      //   192: aload_2
      //   193: aload_1
      //   194: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   197: invokestatic 75	com/thinkyeah/common/i/e$a:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   200: aload_2
      //   201: invokestatic 108	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
      //   204: return
      //   205: astore_1
      //   206: goto +6 -> 212
      //   209: astore_1
      //   210: aconst_null
      //   211: astore_2
      //   212: aload_2
      //   213: invokestatic 108	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
      //   216: aload_1
      //   217: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	218	0	this	a
      //   4	190	1	localObject1	Object
      //   205	1	1	localObject2	Object
      //   209	8	1	localObject3	Object
      //   15	198	2	localObject4	Object
      //   25	12	3	localStringBuilder	StringBuilder
      // Exception table:
      //   from	to	target	type
      //   53	200	205	finally
      //   44	53	209	finally
    }
  }
  
  public static abstract class b
  {
    protected Context a;
    protected File b;
    
    public b(Context paramContext, File paramFile)
    {
      this.a = paramContext;
      this.b = paramFile;
    }
    
    protected static void a(OutputStream paramOutputStream, String paramString)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString);
      localStringBuilder.append("\n");
      paramOutputStream.write(localStringBuilder.toString().getBytes());
    }
    
    protected static boolean a(File paramFile)
    {
      File localFile = paramFile.getParentFile();
      if ((!localFile.exists()) && (!localFile.mkdirs()))
      {
        paramFile = e.a();
        StringBuilder localStringBuilder = new StringBuilder("Fail to create dir, path: ");
        localStringBuilder.append(localFile.getAbsolutePath());
        paramFile.d(localStringBuilder.toString());
        return false;
      }
      try
      {
        if (!paramFile.exists())
        {
          boolean bool = paramFile.createNewFile();
          if (!bool) {
            return false;
          }
        }
        return true;
      }
      catch (IOException paramFile) {}
      return false;
    }
    
    public abstract void a();
  }
  
  public static final class c
    extends e.b
  {
    public c(Context paramContext, File paramFile)
    {
      super(paramFile);
    }
    
    /* Error */
    public final void a()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 18	com/thinkyeah/common/i/e$b:b	Ljava/io/File;
      //   4: astore_1
      //   5: aload_1
      //   6: invokestatic 21	com/thinkyeah/common/i/e$c:a	(Ljava/io/File;)Z
      //   9: ifne +35 -> 44
      //   12: invokestatic 24	com/thinkyeah/common/i/e:a	()Lcom/thinkyeah/common/p;
      //   15: astore_2
      //   16: new 26	java/lang/StringBuilder
      //   19: dup
      //   20: ldc 28
      //   22: invokespecial 31	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   25: astore_3
      //   26: aload_3
      //   27: aload_1
      //   28: invokevirtual 37	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   31: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   34: pop
      //   35: aload_2
      //   36: aload_3
      //   37: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokevirtual 49	com/thinkyeah/common/p:d	(Ljava/lang/String;)V
      //   43: return
      //   44: aload_0
      //   45: getfield 52	com/thinkyeah/common/i/e$b:a	Landroid/content/Context;
      //   48: invokevirtual 58	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   51: astore_3
      //   52: new 60	java/io/FileOutputStream
      //   55: dup
      //   56: aload_1
      //   57: invokespecial 63	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   60: astore_2
      //   61: aload_3
      //   62: iconst_0
      //   63: invokevirtual 69	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   66: invokeinterface 75 1 0
      //   71: astore_1
      //   72: aload_1
      //   73: invokeinterface 81 1 0
      //   78: ifeq +33 -> 111
      //   81: aload_1
      //   82: invokeinterface 85 1 0
      //   87: checkcast 87	android/content/pm/ApplicationInfo
      //   90: astore_3
      //   91: aload_3
      //   92: getfield 91	android/content/pm/ApplicationInfo:flags	I
      //   95: iconst_1
      //   96: iand
      //   97: ifne -25 -> 72
      //   100: aload_2
      //   101: aload_3
      //   102: getfield 95	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   105: invokestatic 98	com/thinkyeah/common/i/e$c:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   108: goto -36 -> 72
      //   111: aload_2
      //   112: invokestatic 103	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
      //   115: return
      //   116: astore_1
      //   117: goto +6 -> 123
      //   120: astore_1
      //   121: aconst_null
      //   122: astore_2
      //   123: aload_2
      //   124: invokestatic 103	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
      //   127: aload_1
      //   128: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	129	0	this	c
      //   4	78	1	localObject1	Object
      //   116	1	1	localObject2	Object
      //   120	8	1	localObject3	Object
      //   15	109	2	localObject4	Object
      //   25	77	3	localObject5	Object
      // Exception table:
      //   from	to	target	type
      //   61	72	116	finally
      //   72	108	116	finally
      //   52	61	120	finally
    }
  }
}
