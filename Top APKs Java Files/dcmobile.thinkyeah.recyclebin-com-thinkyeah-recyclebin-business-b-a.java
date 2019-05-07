package com.thinkyeah.recyclebin.business.b;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Files;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.widget.Toast;
import com.thinkyeah.common.p;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class a
{
  public static final p a = p.a(a.class);
  @SuppressLint({"StaticFieldLeak"})
  private static a u;
  public Context b;
  public e c;
  public volatile Map<String, b> d;
  public volatile Map<String, b> e;
  public volatile Map<String, b> f;
  public volatile Map<String, b> g;
  public volatile Map<String, b> h;
  public volatile int i = 1;
  public a j;
  private List<String> k;
  private List<String> l;
  private List<String> m;
  private volatile Map<String, String> n;
  private c o;
  private c p;
  private c q;
  private c r;
  private d s;
  private Handler t;
  private c v;
  private final c.a w = new c.a()
  {
    public final Set<String> a(int paramAnonymousInt)
    {
      Map localMap = a.a(a.this, paramAnonymousInt);
      if (localMap == null) {
        return null;
      }
      return localMap.keySet();
    }
    
    public final void a(int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString)
    {
      Object localObject1 = a.c();
      Object localObject2 = new StringBuilder("==> onEventHappened, ");
      ((StringBuilder)localObject2).append(a.a(paramAnonymousInt1));
      ((StringBuilder)localObject2).append(", ");
      ((StringBuilder)localObject2).append(a.b(paramAnonymousInt2));
      ((StringBuilder)localObject2).append(", path: ");
      ((StringBuilder)localObject2).append(paramAnonymousString);
      ((p)localObject1).g(((StringBuilder)localObject2).toString());
      localObject1 = a.a(a.this, paramAnonymousInt1);
      if (localObject1 == null) {
        return;
      }
      if (paramAnonymousInt2 == 1)
      {
        if (a.b(a.this).a(paramAnonymousString) == 1) {
          return;
        }
        localObject2 = paramAnonymousString.toLowerCase();
        if ((!a.a(a.this, (String)localObject2)) && (a.b(a.this, (String)localObject2)))
        {
          a.c().g("Ignore file in black list, path: ".concat(String.valueOf(paramAnonymousString)));
          return;
        }
        localObject2 = b.a(a.c(a.this).a(), UUID.randomUUID().toString());
        if (((b)localObject2).b.a(paramAnonymousString, ((b)localObject2).a))
        {
          a.c().g("Add new file, path: ".concat(String.valueOf(paramAnonymousString)));
          ((Map)localObject1).put(paramAnonymousString, localObject2);
          return;
        }
        a.c().d("Fail to open file, path: ".concat(String.valueOf(paramAnonymousString)));
        return;
      }
      if (paramAnonymousInt2 == 2)
      {
        localObject2 = (b)((Map)localObject1).get(paramAnonymousString);
        if (localObject2 != null)
        {
          a.c().g("On file delete, try to recover, path: ".concat(String.valueOf(paramAnonymousString)));
          a.a(a.this, (Map)localObject1, (b)localObject2, paramAnonymousString);
          return;
        }
        a.c().g("Item not found, path: ".concat(String.valueOf(paramAnonymousString)));
        return;
      }
      if (paramAnonymousInt2 == 3)
      {
        localObject1 = (b)((Map)localObject1).get(paramAnonymousString);
        if (localObject1 != null) {
          a.a((b)localObject1, paramAnonymousString);
        }
      }
    }
    
    public final boolean a(String paramAnonymousString, int paramAnonymousInt)
    {
      if ((paramAnonymousInt != 1) && (paramAnonymousInt != 2))
      {
        if (paramAnonymousInt == 3) {
          return true;
        }
        if (paramAnonymousInt == 4)
        {
          paramAnonymousInt = a.b(a.this).a(paramAnonymousString);
          if ((paramAnonymousInt != 16) && (paramAnonymousInt != 32)) {
            return paramAnonymousInt == 64;
          }
          return true;
        }
        return false;
      }
      return true;
    }
  };
  private final d.a x = new d.a()
  {
    private void a(String paramAnonymousString)
    {
      Object localObject1 = a.a(a.this).getPackageManager();
      try
      {
        paramAnonymousString = ((PackageManager)localObject1).getApplicationInfo(paramAnonymousString, 0);
        if ((paramAnonymousString.flags & 0x1) != 0) {
          return;
        }
        localObject1 = b.a(a.c(a.this).a(), UUID.randomUUID().toString());
        if (((b)localObject1).b.a(paramAnonymousString.sourceDir, ((b)localObject1).a))
        {
          localObject2 = a.c();
          StringBuilder localStringBuilder = new StringBuilder("Add new file, path: ");
          localStringBuilder.append(paramAnonymousString.sourceDir);
          ((p)localObject2).g(localStringBuilder.toString());
          a.i(a.this).put(paramAnonymousString.sourceDir, localObject1);
          a.h(a.this).put(paramAnonymousString.packageName, paramAnonymousString.sourceDir);
          return;
        }
        localObject1 = a.c();
        Object localObject2 = new StringBuilder("Fail to open file, path: ");
        ((StringBuilder)localObject2).append(paramAnonymousString.sourceDir);
        ((p)localObject1).d(((StringBuilder)localObject2).toString());
        return;
      }
      catch (PackageManager.NameNotFoundException paramAnonymousString)
      {
        a.c().a(paramAnonymousString);
      }
    }
    
    public final void a(int paramAnonymousInt, String paramAnonymousString)
    {
      if (paramAnonymousInt == 1)
      {
        a(paramAnonymousString);
        return;
      }
      String str;
      if (paramAnonymousInt == 2)
      {
        str = (String)a.h(a.this).get(paramAnonymousString);
        if (str == null) {
          return;
        }
        a.h(a.this).remove(paramAnonymousString);
        paramAnonymousString = (b)a.i(a.this).get(str);
        if (paramAnonymousString != null)
        {
          a.c().g("On file delete, try to recover, path: ".concat(String.valueOf(str)));
          a.a(a.this, a.i(a.this), paramAnonymousString, str);
          return;
        }
        a.c().g("Item not found, path: ".concat(String.valueOf(str)));
        return;
      }
      if (paramAnonymousInt == 3)
      {
        str = (String)a.h(a.this).get(paramAnonymousString);
        if (str == null) {
          return;
        }
        a.h(a.this).remove(paramAnonymousString);
        b localB = (b)a.i(a.this).get(str);
        if (localB != null)
        {
          if (!localB.b.a(localB.a))
          {
            p localP = a.c();
            StringBuilder localStringBuilder = new StringBuilder("Fail to close file, uuid: ");
            localStringBuilder.append(localB.a);
            localStringBuilder.append(", path: ");
            localStringBuilder.append(str);
            localP.d(localStringBuilder.toString());
          }
          a.i(a.this).remove(str);
        }
        else
        {
          a.c().g("Item not found, path: ".concat(String.valueOf(str)));
        }
        a(paramAnonymousString);
        return;
      }
      a.c().d("Unexpected event, ".concat(String.valueOf(paramAnonymousInt)));
    }
  };
  
  private a(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
    this.c = new e(paramContext);
    this.d = new HashMap();
    this.e = new HashMap();
    this.f = new HashMap();
    this.g = new HashMap();
    this.h = new HashMap();
    this.n = new HashMap();
    this.k = new ArrayList();
    this.l = new ArrayList();
    this.m = new ArrayList();
    this.t = new Handler();
  }
  
  /* Error */
  private b a(Uri paramUri, Map<String, b> paramMap, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: new 115	java/util/ArrayList
    //   3: dup
    //   4: sipush 500
    //   7: invokespecial 134	java/util/ArrayList:<init>	(I)V
    //   10: astore 11
    //   12: new 15	com/thinkyeah/recyclebin/business/b/a$b
    //   15: dup
    //   16: aload_0
    //   17: iconst_0
    //   18: invokespecial 137	com/thinkyeah/recyclebin/business/b/a$b:<init>	(Lcom/thinkyeah/recyclebin/business/b/a;B)V
    //   21: astore 10
    //   23: aconst_null
    //   24: astore 9
    //   26: aconst_null
    //   27: astore 7
    //   29: aload_0
    //   30: getfield 92	com/thinkyeah/recyclebin/business/b/a:b	Landroid/content/Context;
    //   33: invokevirtual 141	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   36: aload_1
    //   37: iconst_2
    //   38: anewarray 143	java/lang/String
    //   41: dup
    //   42: iconst_0
    //   43: ldc -111
    //   45: aastore
    //   46: dup
    //   47: iconst_1
    //   48: ldc -109
    //   50: aastore
    //   51: ldc -107
    //   53: iconst_1
    //   54: anewarray 143	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: iload 4
    //   61: invokestatic 153	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   64: aastore
    //   65: ldc -101
    //   67: invokevirtual 161	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +92 -> 164
    //   75: aload_1
    //   76: invokeinterface 167 1 0
    //   81: ifeq +83 -> 164
    //   84: aload 10
    //   86: iconst_1
    //   87: putfield 170	com/thinkyeah/recyclebin/business/b/a$b:a	Z
    //   90: aload_1
    //   91: ldc -111
    //   93: invokeinterface 174 2 0
    //   98: istore 4
    //   100: aload_1
    //   101: ldc -109
    //   103: invokeinterface 174 2 0
    //   108: istore 5
    //   110: aload 10
    //   112: aload_1
    //   113: iload 4
    //   115: invokeinterface 178 2 0
    //   120: putfield 180	com/thinkyeah/recyclebin/business/b/a$b:b	I
    //   123: aload 11
    //   125: aload_1
    //   126: iload 5
    //   128: invokeinterface 183 2 0
    //   133: invokeinterface 189 2 0
    //   138: pop
    //   139: aload_1
    //   140: invokeinterface 192 1 0
    //   145: istore 6
    //   147: iload 6
    //   149: ifne -39 -> 110
    //   152: goto +12 -> 164
    //   155: astore_2
    //   156: goto +252 -> 408
    //   159: astore 8
    //   161: goto +28 -> 189
    //   164: aload_1
    //   165: ifnull +45 -> 210
    //   168: aload_1
    //   169: invokeinterface 195 1 0
    //   174: goto +36 -> 210
    //   177: astore_2
    //   178: aload 7
    //   180: astore_1
    //   181: goto +227 -> 408
    //   184: astore 8
    //   186: aload 9
    //   188: astore_1
    //   189: aload_1
    //   190: astore 7
    //   192: getstatic 69	com/thinkyeah/recyclebin/business/b/a:a	Lcom/thinkyeah/common/p;
    //   195: aload 8
    //   197: invokevirtual 198	com/thinkyeah/common/p:a	(Ljava/lang/Throwable;)V
    //   200: aload_1
    //   201: ifnull +9 -> 210
    //   204: aload_1
    //   205: invokeinterface 195 1 0
    //   210: aload 11
    //   212: invokeinterface 202 1 0
    //   217: astore_1
    //   218: aload_1
    //   219: invokeinterface 207 1 0
    //   224: ifeq +181 -> 405
    //   227: aload_1
    //   228: invokeinterface 211 1 0
    //   233: checkcast 143	java/lang/String
    //   236: astore 7
    //   238: aload 7
    //   240: invokestatic 217	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   243: ifne -25 -> 218
    //   246: aload 7
    //   248: ldc -37
    //   250: invokevirtual 222	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   253: ifne -35 -> 218
    //   256: aload_0
    //   257: getfield 224	com/thinkyeah/recyclebin/business/b/a:j	Lcom/thinkyeah/recyclebin/business/b/a$a;
    //   260: aload 7
    //   262: invokeinterface 226 2 0
    //   267: iload_3
    //   268: iand
    //   269: ifeq -51 -> 218
    //   272: aload 7
    //   274: invokevirtual 230	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   277: astore 8
    //   279: aload_0
    //   280: aload 8
    //   282: invokespecial 233	com/thinkyeah/recyclebin/business/b/a:a	(Ljava/lang/String;)Z
    //   285: ifne +31 -> 316
    //   288: aload_0
    //   289: aload 8
    //   291: invokespecial 235	com/thinkyeah/recyclebin/business/b/a:b	(Ljava/lang/String;)Z
    //   294: ifeq +22 -> 316
    //   297: getstatic 69	com/thinkyeah/recyclebin/business/b/a:a	Lcom/thinkyeah/common/p;
    //   300: ldc -19
    //   302: aload 7
    //   304: invokestatic 240	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   307: invokevirtual 244	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   310: invokevirtual 247	com/thinkyeah/common/p:g	(Ljava/lang/String;)V
    //   313: goto -95 -> 218
    //   316: aload_0
    //   317: getfield 98	com/thinkyeah/recyclebin/business/b/a:c	Lcom/thinkyeah/recyclebin/business/b/e;
    //   320: invokevirtual 250	com/thinkyeah/recyclebin/business/b/e:a	()Lcom/thinkyeah/recyclebin/business/b/e$a;
    //   323: invokestatic 256	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   326: invokevirtual 259	java/util/UUID:toString	()Ljava/lang/String;
    //   329: invokestatic 264	com/thinkyeah/recyclebin/business/b/b:a	(Lcom/thinkyeah/recyclebin/business/b/e$a;Ljava/lang/String;)Lcom/thinkyeah/recyclebin/business/b/b;
    //   332: astore 8
    //   334: aload 8
    //   336: getfield 267	com/thinkyeah/recyclebin/business/b/b:b	Lcom/thinkyeah/recyclebin/business/b/e$a;
    //   339: aload 7
    //   341: aload 8
    //   343: getfield 270	com/thinkyeah/recyclebin/business/b/b:a	Ljava/lang/String;
    //   346: invokeinterface 275 3 0
    //   351: ifeq +34 -> 385
    //   354: getstatic 69	com/thinkyeah/recyclebin/business/b/a:a	Lcom/thinkyeah/common/p;
    //   357: ldc_w 277
    //   360: aload 7
    //   362: invokestatic 240	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   365: invokevirtual 244	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   368: invokevirtual 247	com/thinkyeah/common/p:g	(Ljava/lang/String;)V
    //   371: aload_2
    //   372: aload 7
    //   374: aload 8
    //   376: invokeinterface 283 3 0
    //   381: pop
    //   382: goto -164 -> 218
    //   385: getstatic 69	com/thinkyeah/recyclebin/business/b/a:a	Lcom/thinkyeah/common/p;
    //   388: ldc_w 285
    //   391: aload 7
    //   393: invokestatic 240	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   396: invokevirtual 244	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   399: invokevirtual 287	com/thinkyeah/common/p:d	(Ljava/lang/String;)V
    //   402: goto -184 -> 218
    //   405: aload 10
    //   407: areturn
    //   408: aload_1
    //   409: ifnull +9 -> 418
    //   412: aload_1
    //   413: invokeinterface 195 1 0
    //   418: aload_2
    //   419: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	420	0	this	a
    //   0	420	1	paramUri	Uri
    //   0	420	2	paramMap	Map<String, b>
    //   0	420	3	paramInt1	int
    //   0	420	4	paramInt2	int
    //   108	19	5	i1	int
    //   145	3	6	bool	boolean
    //   27	365	7	localObject1	Object
    //   159	1	8	localException1	Exception
    //   184	12	8	localException2	Exception
    //   277	98	8	localObject2	Object
    //   24	163	9	localObject3	Object
    //   21	385	10	localB	b
    //   10	201	11	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   75	110	155	finally
    //   110	147	155	finally
    //   75	110	159	java/lang/Exception
    //   110	147	159	java/lang/Exception
    //   29	71	177	finally
    //   192	200	177	finally
    //   29	71	184	java/lang/Exception
  }
  
  public static a a(Context paramContext)
  {
    if (u == null) {
      try
      {
        if (u == null) {
          u = new a(paramContext);
        }
      }
      finally {}
    }
    return u;
  }
  
  static String a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN_MEDIA_STORE_TYPE";
    case 4: 
      return "MISC";
    case 3: 
      return "AUDIO";
    case 2: 
      return "VIDEO";
    }
    return "IMAGE";
  }
  
  private void a(Uri paramUri, Map<String, b> paramMap, int[] paramArrayOfInt)
  {
    int i3 = paramArrayOfInt.length;
    int i1 = 0;
    int i2 = 0;
    while (i1 < i3)
    {
      i2 |= paramArrayOfInt[i1];
      i1 += 1;
    }
    for (paramArrayOfInt = a(paramUri, paramMap, i2, 0); paramArrayOfInt.a; paramArrayOfInt = a(paramUri, paramMap, i2, paramArrayOfInt.b)) {}
  }
  
  private void a(c paramC)
  {
    if (paramC != null)
    {
      this.b.getContentResolver().unregisterContentObserver(paramC);
      paramC.a = true;
    }
  }
  
  /* Error */
  public static void a(File paramFile, Map<String, b> paramMap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: new 380	java/io/BufferedOutputStream
    //   7: dup
    //   8: new 382	java/io/FileOutputStream
    //   11: dup
    //   12: aload_0
    //   13: invokespecial 385	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   16: invokespecial 388	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   19: astore_0
    //   20: aload_1
    //   21: invokeinterface 392 1 0
    //   26: invokeinterface 395 1 0
    //   31: astore_1
    //   32: aload_1
    //   33: invokeinterface 207 1 0
    //   38: ifeq +49 -> 87
    //   41: aload_1
    //   42: invokeinterface 211 1 0
    //   47: checkcast 143	java/lang/String
    //   50: astore_2
    //   51: new 317	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 396	java/lang/StringBuilder:<init>	()V
    //   58: astore_3
    //   59: aload_3
    //   60: aload_2
    //   61: invokevirtual 325	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload_3
    //   66: ldc_w 398
    //   69: invokevirtual 325	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload_0
    //   74: aload_3
    //   75: invokevirtual 328	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: invokevirtual 402	java/lang/String:getBytes	()[B
    //   81: invokevirtual 408	java/io/OutputStream:write	([B)V
    //   84: goto -52 -> 32
    //   87: aload_0
    //   88: invokevirtual 411	java/io/OutputStream:flush	()V
    //   91: aload_0
    //   92: invokestatic 416	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
    //   95: return
    //   96: astore_1
    //   97: goto +30 -> 127
    //   100: astore_1
    //   101: goto +12 -> 113
    //   104: astore_1
    //   105: aload_2
    //   106: astore_0
    //   107: goto +20 -> 127
    //   110: astore_1
    //   111: aload_3
    //   112: astore_0
    //   113: aload_0
    //   114: astore_2
    //   115: getstatic 69	com/thinkyeah/recyclebin/business/b/a:a	Lcom/thinkyeah/common/p;
    //   118: aload_1
    //   119: invokevirtual 198	com/thinkyeah/common/p:a	(Ljava/lang/Throwable;)V
    //   122: aload_0
    //   123: invokestatic 416	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
    //   126: return
    //   127: aload_0
    //   128: invokestatic 416	com/thinkyeah/common/i/h:a	(Ljava/io/Closeable;)V
    //   131: aload_1
    //   132: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	133	0	paramFile	File
    //   0	133	1	paramMap	Map<String, b>
    //   3	112	2	localObject	Object
    //   1	111	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   20	32	96	finally
    //   32	84	96	finally
    //   87	91	96	finally
    //   20	32	100	java/io/IOException
    //   32	84	100	java/io/IOException
    //   87	91	100	java/io/IOException
    //   4	20	104	finally
    //   115	122	104	finally
    //   4	20	110	java/io/IOException
  }
  
  private static void a(Map<String, b> paramMap)
  {
    if (paramMap.isEmpty()) {
      return;
    }
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      b localB = (b)localEntry.getValue();
      if (!localB.b.a(localB.a))
      {
        p localP = a;
        StringBuilder localStringBuilder = new StringBuilder("Fail to close file, uuid: ");
        localStringBuilder.append(localB.a);
        localStringBuilder.append(", path: ");
        localStringBuilder.append((String)localEntry.getKey());
        localP.d(localStringBuilder.toString());
      }
    }
  }
  
  private boolean a(String paramString)
  {
    Iterator localIterator = this.k.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!paramString.equals(str))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(File.separator);
        if (!paramString.startsWith(localStringBuilder.toString())) {
          break;
        }
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  private boolean b(String paramString)
  {
    Iterator localIterator = this.l.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!paramString.equals(str))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(File.separator);
        if (!paramString.startsWith(localStringBuilder.toString())) {
          break;
        }
      }
      else
      {
        return true;
      }
    }
    localIterator = this.m.iterator();
    while (localIterator.hasNext()) {
      if (paramString.contains((String)localIterator.next())) {
        return true;
      }
    }
    return false;
  }
  
  private void c(int paramInt)
  {
    if (this.i == paramInt) {
      return;
    }
    this.i = paramInt;
    Intent localIntent = new Intent();
    localIntent.setAction("file_monitor://status_changed");
    localIntent.putExtra("value", paramInt);
    localIntent.setPackage(this.b.getPackageName());
    this.b.sendBroadcast(localIntent);
  }
  
  public final void a()
  {
    try
    {
      if (this.i == 1)
      {
        this.i = 2;
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder("Should only prepare on Idle status, current: ");
      localStringBuilder.append(this.i);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    finally {}
  }
  
  public final boolean a(List<String> paramList1, List<String> paramList2, List<String> paramList3)
  {
    try
    {
      this.k.clear();
      paramList1 = paramList1.iterator();
      Object localObject;
      while (paramList1.hasNext())
      {
        localObject = (String)paramList1.next();
        this.k.add(((String)localObject).toLowerCase());
      }
      this.l.clear();
      if (paramList2 != null)
      {
        paramList1 = paramList2.iterator();
        while (paramList1.hasNext())
        {
          paramList2 = (String)paramList1.next();
          this.l.add(paramList2.toLowerCase());
        }
      }
      this.m.clear();
      if (paramList3 != null)
      {
        paramList1 = paramList3.iterator();
        while (paramList1.hasNext())
        {
          paramList2 = (String)paramList1.next();
          this.m.add(paramList2.toLowerCase());
        }
      }
      a.g("==> doStartMonitor");
      c(3);
      final long l1 = SystemClock.elapsedRealtime();
      this.d.clear();
      this.e.clear();
      this.f.clear();
      this.g.clear();
      this.h.clear();
      this.n.clear();
      paramList1 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
      a(paramList1, this.d, new int[] { 2 });
      this.o = new c(this.b, this.t, 1, paramList1, this.w);
      this.b.getContentResolver().registerContentObserver(paramList1, true, this.o);
      paramList1 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
      a(paramList1, this.e, new int[] { 4 });
      this.p = new c(this.b, this.t, 2, paramList1, this.w);
      this.b.getContentResolver().registerContentObserver(paramList1, true, this.p);
      paramList1 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
      a(paramList1, this.f, new int[] { 8 });
      this.q = new c(this.b, this.t, 3, paramList1, this.w);
      this.b.getContentResolver().registerContentObserver(paramList1, true, this.q);
      paramList1 = MediaStore.Files.getContentUri("external");
      a(paramList1, this.g, new int[] { 16, 32, 64 });
      this.r = new c(this.b, this.t, 4, paramList1, this.w);
      this.b.getContentResolver().registerContentObserver(paramList1, true, this.r);
      paramList1 = this.b.getPackageManager().getInstalledApplications(0).iterator();
      while (paramList1.hasNext())
      {
        paramList2 = (ApplicationInfo)paramList1.next();
        if ((paramList2.flags & 0x1) == 0)
        {
          paramList3 = b.a(this.c.a(), UUID.randomUUID().toString());
          if (paramList3.b.a(paramList2.sourceDir, paramList3.a))
          {
            localObject = a;
            StringBuilder localStringBuilder = new StringBuilder("Add installed app, path: ");
            localStringBuilder.append(paramList2.sourceDir);
            ((p)localObject).g(localStringBuilder.toString());
            this.h.put(paramList2.sourceDir, paramList3);
            this.n.put(paramList2.packageName, paramList2.sourceDir);
          }
          else
          {
            paramList3 = a;
            localObject = new StringBuilder("Fail to open file, path: ");
            ((StringBuilder)localObject).append(paramList2.sourceDir);
            paramList3.d(((StringBuilder)localObject).toString());
          }
        }
      }
      this.s = new d(this.b, this.x);
      paramList1 = this.s;
      paramList2 = new IntentFilter();
      paramList2.addAction("android.intent.action.PACKAGE_ADDED");
      paramList2.addAction("android.intent.action.PACKAGE_REMOVED");
      paramList2.addAction("android.intent.action.PACKAGE_REPLACED");
      paramList2.addDataScheme("package");
      paramList1.a.registerReceiver(paramList1.b, paramList2);
      c(4);
      com.thinkyeah.common.a.a(new Runnable()
      {
        public final void run()
        {
          if (p.a() <= 1)
          {
            Context localContext = a.a(a.this);
            StringBuilder localStringBuilder = new StringBuilder("Scan finished, used time: ");
            localStringBuilder.append(SystemClock.elapsedRealtime() - l1);
            Toast.makeText(localContext, localStringBuilder.toString(), 1).show();
          }
        }
      });
      com.thinkyeah.common.h.a.a().a("fs_scanned", new com.thinkyeah.common.h.a.a().a("used_time", com.thinkyeah.recyclebin.d.a.b(SystemClock.elapsedRealtime() - l1)).a);
      this.v = new c((byte)0);
      paramList1 = new IntentFilter("cross_process://action/main_ui_opened");
      this.b.registerReceiver(this.v, paramList1);
      return true;
    }
    finally {}
  }
  
  public final void b()
  {
    try
    {
      if (this.v != null)
      {
        this.b.unregisterReceiver(this.v);
        this.v = null;
      }
      a.g("==> doStopMonitor");
      a(this.d);
      a(this.e);
      a(this.f);
      a(this.g);
      a(this.h);
      this.d.clear();
      this.e.clear();
      this.f.clear();
      this.g.clear();
      this.h.clear();
      this.n.clear();
      a(this.o);
      a(this.p);
      a(this.q);
      a(this.r);
      d localD = this.s;
      localD.a.unregisterReceiver(localD.b);
      this.o = null;
      this.p = null;
      this.q = null;
      this.r = null;
      this.s = null;
      c(1);
      return;
    }
    finally {}
  }
  
  public static abstract interface a
  {
    public abstract int a(String paramString);
    
    public abstract void a(String paramString1, String paramString2, long paramLong);
    
    public abstract boolean b(String paramString);
    
    public abstract File c(String paramString);
  }
  
  final class b
  {
    boolean a = false;
    int b;
    
    private b() {}
  }
  
  final class c
    extends BroadcastReceiver
  {
    private long b = 0L;
    
    private c() {}
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent != null)
      {
        paramContext = paramIntent.getAction();
        if (paramContext == null) {
          return;
        }
        if (!"cross_process://action/main_ui_opened".equals(paramContext)) {
          return;
        }
        a.c().g("==> onReceive, broadcast event: cross_process://action/main_ui_opened");
        long l = SystemClock.elapsedRealtime();
        if (l - this.b < 60000L)
        {
          a.c().g("Within time period, skip scan");
          return;
        }
        this.b = l;
        if (a.d(a.this) != null) {
          a.d(a.this).onChange(false);
        }
        if (a.e(a.this) != null) {
          a.e(a.this).onChange(false);
        }
        if (a.f(a.this) != null) {
          a.f(a.this).onChange(false);
        }
        if (a.g(a.this) != null) {
          a.g(a.this).onChange(false);
        }
        return;
      }
    }
  }
}
