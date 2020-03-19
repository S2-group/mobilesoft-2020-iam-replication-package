package com.alphainventor.filemanager.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.alphainventor.filemanager.e;
import com.alphainventor.filemanager.g;
import com.alphainventor.filemanager.i.aa;
import com.alphainventor.filemanager.i.bg;
import com.alphainventor.filemanager.r.h.c;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

@SuppressLint({"DefaultLocale"})
public class b
{
  private static final Logger h = g.a(b.class);
  private static b i;
  Context a;
  List<Intent> b = new ArrayList();
  com.alphainventor.filemanager.f.b c = com.alphainventor.filemanager.f.b.a();
  HashMap<String, b> d;
  final Object e = new Object();
  HashMap<String, Boolean> f = new HashMap();
  final File g;
  
  private b(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
    this.g = new File(com.alphainventor.filemanager.f.h.a().f() + "/backups/apps");
    a();
  }
  
  public static Bitmap a(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable))
    {
      localObject = (BitmapDrawable)paramDrawable;
      if (((BitmapDrawable)localObject).getBitmap() != null) {
        return ((BitmapDrawable)localObject).getBitmap();
      }
    }
    if ((paramDrawable.getIntrinsicWidth() <= 0) || (paramDrawable.getIntrinsicHeight() <= 0)) {}
    for (Object localObject = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);; localObject = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888))
    {
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
      paramDrawable.draw(localCanvas);
      return localObject;
    }
  }
  
  public static a a(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    PackageInfo localPackageInfo = localPackageManager.getPackageArchiveInfo(paramString, 0);
    if (localPackageInfo == null) {
      return null;
    }
    a localA = new a();
    paramContext = localPackageInfo.applicationInfo;
    paramContext.sourceDir = paramString;
    paramContext.publicSourceDir = paramString;
    if (localPackageInfo.versionName != null) {}
    for (paramContext = localPackageInfo.versionName;; paramContext = "")
    {
      localA.b = paramContext;
      localA.a = localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString();
      localA.c = localPackageInfo.packageName;
      return localA;
    }
  }
  
  public static b a(Context paramContext)
  {
    if (i == null) {
      i = new b(paramContext);
    }
    return i;
  }
  
  private c a(List<c> paramList, String paramString)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      c localC = (c)paramList.next();
      if (localC.c().equals(paramString)) {
        return localC;
      }
    }
    return null;
  }
  
  public static String a(String paramString)
  {
    return paramString.substring("/Android/data/".length(), paramString.lastIndexOf('/'));
  }
  
  public static List<c> a(List<c> paramList)
  {
    if (paramList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      c localC = (c)paramList.next();
      if (!localC.j()) {
        localArrayList.add(localC);
      }
    }
    return localArrayList;
  }
  
  private void a(List<c> paramList, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    paramIntent = paramIntent.getDataString().replace("package:", "");
    if ("android.intent.action.PACKAGE_ADDED".equals(str)) {
      c(paramList, paramIntent);
    }
    do
    {
      return;
      if ("android.intent.action.PACKAGE_REPLACED".equals(str))
      {
        b(paramList, paramIntent);
        c(paramList, paramIntent);
        return;
      }
    } while (!"android.intent.action.PACKAGE_REMOVED".equals(str));
    b(paramList, paramIntent);
  }
  
  private static byte[] a(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static InputStream b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getPackageArchiveInfo(paramString, 0);
    if (localObject == null) {
      return null;
    }
    localObject = ((PackageInfo)localObject).applicationInfo;
    ((ApplicationInfo)localObject).sourceDir = paramString;
    ((ApplicationInfo)localObject).publicSourceDir = paramString;
    paramContext = ((ApplicationInfo)localObject).loadIcon(paramContext);
    if (paramContext == null) {
      return null;
    }
    if ((paramContext instanceof BitmapDrawable)) {}
    for (paramContext = ((BitmapDrawable)paramContext).getBitmap();; paramContext = a(paramContext)) {
      return new ByteArrayInputStream(a(paramContext));
    }
  }
  
  public static String b(c paramC)
  {
    return aa.a(paramC);
  }
  
  private void b(List<c> paramList)
  {
    this.d = new HashMap();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      c localC = (c)paramList.next();
      this.d.put(localC.c(), new b(localC.b(), localC.e()));
    }
  }
  
  private void b(List<c> paramList, String paramString)
  {
    paramString = a(paramList, paramString);
    if (paramString != null) {
      paramList.remove(paramString);
    }
  }
  
  public static boolean b(String paramString)
  {
    return (paramString.endsWith("/cache")) && (paramString.startsWith("/Android/data/")) && (paramString.split("/").length == 5);
  }
  
  private void c(List<c> paramList)
  {
    paramList = new File(g().getAbsolutePath() + ".tmp");
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(new FileOutputStream(paramList));
      localObjectOutputStream.writeObject(this.d);
      localObjectOutputStream.close();
      paramList.renameTo(g());
      return;
    }
    catch (IOException paramList)
    {
      paramList.printStackTrace();
    }
  }
  
  private void c(List<c> paramList, String paramString)
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    try
    {
      paramList.add(new c(localPackageManager, localPackageManager.getPackageInfo(paramString, 0)));
      return;
    }
    catch (Exception paramList)
    {
      h.severe("Adding changed packaged to cache was failed");
    }
  }
  
  private File g()
  {
    return new File(e.a(this.a), "appcache_" + Locale.getDefault().toString());
  }
  
  /* Error */
  private HashMap<String, b> h()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 362	com/alphainventor/filemanager/b/b:g	()Ljava/io/File;
    //   4: astore 6
    //   6: aload 6
    //   8: invokevirtual 431	java/io/File:exists	()Z
    //   11: ifne +7 -> 18
    //   14: aconst_null
    //   15: astore_2
    //   16: aload_2
    //   17: areturn
    //   18: new 433	java/io/FileInputStream
    //   21: dup
    //   22: aload 6
    //   24: invokespecial 434	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   27: astore_1
    //   28: new 436	java/io/ObjectInputStream
    //   31: dup
    //   32: aload_1
    //   33: invokespecial 439	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   36: astore 4
    //   38: aload 4
    //   40: astore_3
    //   41: aload_1
    //   42: astore_2
    //   43: aload 4
    //   45: invokevirtual 442	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   48: checkcast 56	java/util/HashMap
    //   51: astore 5
    //   53: aload 4
    //   55: ifnull +15 -> 70
    //   58: aload 4
    //   60: invokevirtual 443	java/io/ObjectInputStream:close	()V
    //   63: aload 5
    //   65: areturn
    //   66: astore_1
    //   67: aload 5
    //   69: areturn
    //   70: aload 5
    //   72: astore_2
    //   73: aload_1
    //   74: ifnull -58 -> 16
    //   77: aload_1
    //   78: invokevirtual 444	java/io/FileInputStream:close	()V
    //   81: aload 5
    //   83: areturn
    //   84: astore_1
    //   85: aload 5
    //   87: areturn
    //   88: astore 5
    //   90: aconst_null
    //   91: astore 4
    //   93: aconst_null
    //   94: astore_1
    //   95: aload 4
    //   97: astore_3
    //   98: aload_1
    //   99: astore_2
    //   100: aload 6
    //   102: invokevirtual 447	java/io/File:delete	()Z
    //   105: pop
    //   106: aload 4
    //   108: astore_3
    //   109: aload_1
    //   110: astore_2
    //   111: aload 5
    //   113: invokevirtual 391	java/io/IOException:printStackTrace	()V
    //   116: aload 4
    //   118: ifnull +10 -> 128
    //   121: aload 4
    //   123: invokevirtual 443	java/io/ObjectInputStream:close	()V
    //   126: aconst_null
    //   127: areturn
    //   128: aload_1
    //   129: ifnull -3 -> 126
    //   132: aload_1
    //   133: invokevirtual 444	java/io/FileInputStream:close	()V
    //   136: goto -10 -> 126
    //   139: astore_1
    //   140: goto -14 -> 126
    //   143: astore 5
    //   145: aconst_null
    //   146: astore 4
    //   148: aconst_null
    //   149: astore_1
    //   150: aload 4
    //   152: astore_3
    //   153: aload_1
    //   154: astore_2
    //   155: aload 6
    //   157: invokevirtual 447	java/io/File:delete	()Z
    //   160: pop
    //   161: aload 4
    //   163: astore_3
    //   164: aload_1
    //   165: astore_2
    //   166: aload 5
    //   168: invokevirtual 448	java/lang/ClassNotFoundException:printStackTrace	()V
    //   171: aload 4
    //   173: ifnull +10 -> 183
    //   176: aload 4
    //   178: invokevirtual 443	java/io/ObjectInputStream:close	()V
    //   181: aconst_null
    //   182: areturn
    //   183: aload_1
    //   184: ifnull -3 -> 181
    //   187: aload_1
    //   188: invokevirtual 444	java/io/FileInputStream:close	()V
    //   191: goto -10 -> 181
    //   194: astore_1
    //   195: goto -14 -> 181
    //   198: astore 4
    //   200: aconst_null
    //   201: astore_3
    //   202: aconst_null
    //   203: astore_1
    //   204: aload_3
    //   205: ifnull +10 -> 215
    //   208: aload_3
    //   209: invokevirtual 443	java/io/ObjectInputStream:close	()V
    //   212: aload 4
    //   214: athrow
    //   215: aload_1
    //   216: ifnull -4 -> 212
    //   219: aload_1
    //   220: invokevirtual 444	java/io/FileInputStream:close	()V
    //   223: goto -11 -> 212
    //   226: astore_1
    //   227: goto -15 -> 212
    //   230: astore_1
    //   231: goto -105 -> 126
    //   234: astore_1
    //   235: goto -54 -> 181
    //   238: astore_1
    //   239: goto -27 -> 212
    //   242: astore 4
    //   244: aconst_null
    //   245: astore_3
    //   246: goto -42 -> 204
    //   249: astore 4
    //   251: aload_2
    //   252: astore_1
    //   253: goto -49 -> 204
    //   256: astore 5
    //   258: aconst_null
    //   259: astore 4
    //   261: goto -111 -> 150
    //   264: astore 5
    //   266: goto -116 -> 150
    //   269: astore 5
    //   271: aconst_null
    //   272: astore 4
    //   274: goto -179 -> 95
    //   277: astore 5
    //   279: goto -184 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	282	0	this	b
    //   27	15	1	localFileInputStream	java.io.FileInputStream
    //   66	12	1	localIOException1	IOException
    //   84	1	1	localIOException2	IOException
    //   94	39	1	localObject1	Object
    //   139	1	1	localIOException3	IOException
    //   149	39	1	localObject2	Object
    //   194	1	1	localIOException4	IOException
    //   203	17	1	localObject3	Object
    //   226	1	1	localIOException5	IOException
    //   230	1	1	localIOException6	IOException
    //   234	1	1	localIOException7	IOException
    //   238	1	1	localIOException8	IOException
    //   252	1	1	localObject4	Object
    //   15	237	2	localObject5	Object
    //   40	206	3	localObjectInputStream1	java.io.ObjectInputStream
    //   36	141	4	localObjectInputStream2	java.io.ObjectInputStream
    //   198	15	4	localObject6	Object
    //   242	1	4	localObject7	Object
    //   249	1	4	localObject8	Object
    //   259	14	4	localObject9	Object
    //   51	35	5	localHashMap	HashMap
    //   88	24	5	localIOException9	IOException
    //   143	24	5	localClassNotFoundException1	ClassNotFoundException
    //   256	1	5	localClassNotFoundException2	ClassNotFoundException
    //   264	1	5	localClassNotFoundException3	ClassNotFoundException
    //   269	1	5	localIOException10	IOException
    //   277	1	5	localIOException11	IOException
    //   4	152	6	localFile	File
    // Exception table:
    //   from	to	target	type
    //   58	63	66	java/io/IOException
    //   77	81	84	java/io/IOException
    //   18	28	88	java/io/IOException
    //   132	136	139	java/io/IOException
    //   18	28	143	java/lang/ClassNotFoundException
    //   187	191	194	java/io/IOException
    //   18	28	198	finally
    //   219	223	226	java/io/IOException
    //   121	126	230	java/io/IOException
    //   176	181	234	java/io/IOException
    //   208	212	238	java/io/IOException
    //   28	38	242	finally
    //   43	53	249	finally
    //   100	106	249	finally
    //   111	116	249	finally
    //   155	161	249	finally
    //   166	171	249	finally
    //   28	38	256	java/lang/ClassNotFoundException
    //   43	53	264	java/lang/ClassNotFoundException
    //   28	38	269	java/io/IOException
    //   43	53	277	java/io/IOException
  }
  
  /* Error */
  private List<c> i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 49	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 50	java/util/ArrayList:<init>	()V
    //   9: astore_1
    //   10: aload_0
    //   11: getfield 74	com/alphainventor/filemanager/b/b:a	Landroid/content/Context;
    //   14: invokevirtual 155	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   17: astore_2
    //   18: aload_2
    //   19: iconst_0
    //   20: invokevirtual 454	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   23: invokeinterface 212 1 0
    //   28: astore_3
    //   29: aload_3
    //   30: invokeinterface 218 1 0
    //   35: ifeq +49 -> 84
    //   38: aload_3
    //   39: invokeinterface 222 1 0
    //   44: checkcast 166	android/content/pm/PackageInfo
    //   47: astore 4
    //   49: aload_1
    //   50: new 224	com/alphainventor/filemanager/b/c
    //   53: dup
    //   54: aload_2
    //   55: aload 4
    //   57: invokespecial 399	com/alphainventor/filemanager/b/c:<init>	(Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)V
    //   60: invokeinterface 400 2 0
    //   65: pop
    //   66: goto -37 -> 29
    //   69: astore 4
    //   71: aload 4
    //   73: invokevirtual 455	java/lang/Exception:printStackTrace	()V
    //   76: goto -47 -> 29
    //   79: astore_2
    //   80: aload_2
    //   81: invokevirtual 455	java/lang/Exception:printStackTrace	()V
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: areturn
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	93	0	this	b
    //   9	78	1	localArrayList	ArrayList
    //   88	4	1	localObject	Object
    //   17	38	2	localPackageManager	PackageManager
    //   79	2	2	localException1	Exception
    //   28	11	3	localIterator	Iterator
    //   47	9	4	localPackageInfo	PackageInfo
    //   69	3	4	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   49	66	69	java/lang/Exception
    //   10	29	79	java/lang/Exception
    //   29	49	79	java/lang/Exception
    //   71	76	79	java/lang/Exception
    //   2	10	88	finally
    //   10	29	88	finally
    //   29	49	88	finally
    //   49	66	88	finally
    //   71	76	88	finally
    //   80	84	88	finally
  }
  
  public int a(boolean paramBoolean)
  {
    List localList = d();
    if (paramBoolean) {
      return localList.size();
    }
    return a(localList).size();
  }
  
  public File a(c paramC)
  {
    String str = bg.g(paramC.f().getName());
    paramC = bg.r(paramC.b() + " " + paramC.d());
    return new File(this.g, paramC + "." + str);
  }
  
  public InputStream a(Uri paramUri)
  {
    String str = paramUri.getPath();
    if ("apk".equals(paramUri.getAuthority())) {
      return b(this.a, str);
    }
    paramUri = e(str.substring(1));
    if (paramUri != null)
    {
      paramUri = paramUri.h();
      if ((paramUri instanceof BitmapDrawable)) {
        return new ByteArrayInputStream(a(((BitmapDrawable)paramUri).getBitmap()));
      }
      if (paramUri != null) {
        return new ByteArrayInputStream(a(a(paramUri)));
      }
    }
    return null;
  }
  
  public void a()
  {
    if (!this.g.exists()) {
      this.g.mkdirs();
    }
  }
  
  public void a(Intent paramIntent)
  {
    try
    {
      this.b.add((Intent)paramIntent.clone());
      paramIntent = this.c.c("APPS_BASE");
      this.c.b();
      if (paramIntent != null) {
        this.c.a("APPS_BASE", paramIntent);
      }
      return;
    }
    finally {}
  }
  
  public long b(boolean paramBoolean)
  {
    Object localObject2 = d();
    Object localObject1 = localObject2;
    if (!paramBoolean) {
      localObject1 = a((List)localObject2);
    }
    long l1 = 0L;
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (c)((Iterator)localObject1).next();
        try
        {
          long l2 = ((c)localObject2).f().length();
          l1 = l2 + l1;
        }
        catch (NullPointerException localNullPointerException)
        {
          for (;;)
          {
            com.socialnmobile.commons.reporter.c.c().d("AHFLNULL!!:").a(localNullPointerException).c();
          }
        }
      }
    }
    return l1;
  }
  
  public boolean b()
  {
    return !g().exists();
  }
  
  public List<c> c()
  {
    ArrayList localArrayList = new ArrayList();
    if ((!this.g.exists()) || (!this.g.isDirectory())) {}
    File[] arrayOfFile;
    do
    {
      return localArrayList;
      arrayOfFile = this.g.listFiles(new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return paramAnonymousString.endsWith(".apk");
        }
      });
    } while (arrayOfFile == null);
    PackageManager localPackageManager = this.a.getPackageManager();
    int k = arrayOfFile.length;
    int j = 0;
    label67:
    File localFile;
    PackageInfo localPackageInfo;
    if (j < k)
    {
      localFile = arrayOfFile[j];
      localPackageInfo = localPackageManager.getPackageArchiveInfo(localFile.getAbsolutePath(), 0);
      if (localPackageInfo != null) {
        break label103;
      }
    }
    for (;;)
    {
      j += 1;
      break label67;
      break;
      label103:
      localArrayList.add(new c(localPackageManager, localPackageInfo, localFile));
    }
  }
  
  public boolean c(String paramString)
  {
    paramString = (Boolean)this.f.get(paramString);
    if (paramString == null) {
      return false;
    }
    return paramString.booleanValue();
  }
  
  public List<c> d()
  {
    try
    {
      List localList = this.c.c("APPS_BASE");
      if ((localList != null) && (localList.size() != 0)) {
        if (this.b.size() > 0)
        {
          Iterator localIterator = this.b.iterator();
          while (localIterator.hasNext()) {
            a(localList, (Intent)localIterator.next());
          }
          this.b.clear();
        }
      }
    }
    finally {}
    for (Object localObject = new ArrayList(localCollection);; localObject = new ArrayList((Collection)localObject))
    {
      return localObject;
      this.b.clear();
      localObject = i();
      this.c.a("APPS_BASE", (List)localObject);
    }
  }
  
  public List<c> d(String paramString)
  {
    List localList = this.c.c(paramString);
    if ((localList != null) && (localList.size() != 0)) {
      return new ArrayList(localList);
    }
    localList = d();
    if ("APPS_DOWNLOADED".equals(paramString)) {
      localList = a(localList);
    }
    for (;;)
    {
      this.c.a(paramString, new ArrayList(localList));
      if (this.d == null) {}
      int j;
      c localC;
      synchronized (this.e)
      {
        this.d = h();
        Iterator localIterator = localList.iterator();
        j = 0;
        if (localIterator.hasNext()) {
          localC = (c)localIterator.next();
        }
      }
      label224:
      for (;;)
      {
        synchronized (this.e)
        {
          if (!localC.a(this.d)) {
            break label224;
          }
          j = 1;
          break;
          paramString = finally;
          throw paramString;
        }
        if (("APPS_ALL".equals(paramString)) && (j != 0)) {}
        synchronized (this.e)
        {
          b(localList);
          c(localList);
          this.f.put(paramString, Boolean.valueOf(true));
          return localList;
        }
      }
    }
  }
  
  public c e(String paramString)
  {
    Object localObject = this.c.c("APPS_BASE");
    if ((localObject != null) && (((List)localObject).size() != 0)) {
      localObject = ((List)localObject).iterator();
    }
    while (((Iterator)localObject).hasNext())
    {
      c localC = (c)((Iterator)localObject).next();
      if (paramString.equals(localC.c()))
      {
        return localC;
        localObject = this.a.getPackageManager();
        try
        {
          paramString = ((PackageManager)localObject).getPackageInfo(paramString, 0);
          if (paramString != null)
          {
            paramString = new c((PackageManager)localObject, paramString);
            return paramString;
          }
        }
        catch (PackageManager.NameNotFoundException paramString) {}
      }
    }
    return null;
  }
  
  public File e()
  {
    return this.g;
  }
  
  public static class a
    extends com.alphainventor.filemanager.r.h<Void, Void, Void>
  {
    Context a;
    
    public a(Context paramContext)
    {
      super();
      this.a = paramContext;
    }
    
    protected Void a(Void... paramVarArgs)
    {
      b.f().fine("start caching app name");
      b.a(this.a).d("APPS_ALL");
      return null;
    }
  }
  
  public static class b
    implements Serializable
  {
    String a;
    long b;
    
    b(String paramString, long paramLong)
    {
      this.a = paramString;
      this.b = paramLong;
    }
    
    public long a()
    {
      return this.b;
    }
    
    public String b()
    {
      return this.a;
    }
  }
}
