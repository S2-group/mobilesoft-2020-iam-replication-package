package com.kapp.ifont.d;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.text.TextUtils;
import com.arasthel.asyncjob.a.a;
import com.arasthel.asyncjob.a.b;
import com.arasthel.asyncjob.a.c;
import com.kapp.ifont.beans.TypefaceFile;
import com.kapp.ifont.beans.TypefaceFont;
import com.kapp.ifont.beansdao.TypefaceFileDb;
import com.kapp.ifont.beansdao.TypefaceFontDb;
import com.kapp.ifont.core.b;
import com.kapp.ifont.core.util.n;
import com.path.android.jobqueue.f;
import com.path.android.jobqueue.h;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.xmlpull.v1.XmlPullParserException;

public class c
  extends com.path.android.jobqueue.d
{
  private static final AtomicInteger a = new AtomicInteger(0);
  private static List<String> e = new ArrayList();
  private static Comparator<TypefaceFont> f = new Comparator()
  {
    private final Collator a = Collator.getInstance();
    
    public int a(TypefaceFont paramAnonymousTypefaceFont1, TypefaceFont paramAnonymousTypefaceFont2)
    {
      paramAnonymousTypefaceFont1 = paramAnonymousTypefaceFont1.getSortName();
      paramAnonymousTypefaceFont2 = paramAnonymousTypefaceFont2.getSortName();
      return this.a.compare(paramAnonymousTypefaceFont1, paramAnonymousTypefaceFont2);
    }
  };
  private final int b = a.incrementAndGet();
  private Context c;
  private String d;
  
  public c(Context paramContext, String paramString)
  {
    super(new h(g.c).a("fetch-local-font-fc"));
    this.c = paramContext;
    this.d = paramString;
  }
  
  public static TypefaceFont a(Context paramContext, TypefaceFontDb paramTypefaceFontDb)
  {
    paramContext = new TypefaceFont();
    paramContext.setName(paramTypefaceFontDb.getName());
    paramContext.setSortName(paramTypefaceFontDb.getSortName());
    paramContext.setFontPackageName(paramTypefaceFontDb.getFontPackageName());
    paramContext.setFontPath(paramTypefaceFontDb.getFontPath());
    paramContext.setTypefaceFilename(paramTypefaceFontDb.getTypefaceFilename());
    paramContext.setInstalled(paramTypefaceFontDb.getInstalled().booleanValue());
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    paramTypefaceFontDb = paramTypefaceFontDb.getTypefaceFiles().iterator();
    while (paramTypefaceFontDb.hasNext())
    {
      Object localObject = (TypefaceFileDb)paramTypefaceFontDb.next();
      TypefaceFile localTypefaceFile = new TypefaceFile();
      localTypefaceFile.setDroidName(((TypefaceFileDb)localObject).getDroidName());
      localTypefaceFile.setFileName(((TypefaceFileDb)localObject).getFileName());
      localObject = ((TypefaceFileDb)localObject).getTypeName();
      if (((String)localObject).equals("monospace")) {
        localArrayList1.add(localTypefaceFile);
      } else if (((String)localObject).equals("sans")) {
        localArrayList2.add(localTypefaceFile);
      } else if (((String)localObject).equals("serif")) {
        localArrayList3.add(localTypefaceFile);
      }
    }
    paramContext.setMonospaceFonts(localArrayList1);
    paramContext.setSansFonts(localArrayList2);
    paramContext.setSerifFonts(localArrayList3);
    return paramContext;
  }
  
  public static TypefaceFontDb a(Context paramContext, TypefaceFont paramTypefaceFont)
  {
    paramContext = com.kapp.ifont.a.a.a(paramContext);
    TypefaceFontDb localTypefaceFontDb = new TypefaceFontDb();
    localTypefaceFontDb.setName(paramTypefaceFont.getName());
    localTypefaceFontDb.setSortName(paramTypefaceFont.getSortName());
    localTypefaceFontDb.setFontPackageName(paramTypefaceFont.getFontPackageName());
    localTypefaceFontDb.setFontPath(paramTypefaceFont.getFontPath());
    localTypefaceFontDb.setTypefaceFilename(paramTypefaceFont.getTypefaceFilename());
    localTypefaceFontDb.setInstalled(Boolean.valueOf(paramTypefaceFont.isInstalled()));
    paramContext.a(localTypefaceFontDb);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = paramTypefaceFont.getMonospaceFonts().iterator();
    Object localObject2;
    TypefaceFileDb localTypefaceFileDb;
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (TypefaceFile)((Iterator)localObject1).next();
      localTypefaceFileDb = new TypefaceFileDb();
      localTypefaceFileDb.setDroidName(((TypefaceFile)localObject2).getDroidName());
      localTypefaceFileDb.setFileName(((TypefaceFile)localObject2).getFileName());
      localTypefaceFileDb.setTypeName("monospace");
      localArrayList.add(localTypefaceFileDb);
    }
    paramContext.a(localTypefaceFontDb, localArrayList);
    localArrayList = new ArrayList();
    localObject1 = paramTypefaceFont.getSansFonts().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (TypefaceFile)((Iterator)localObject1).next();
      localTypefaceFileDb = new TypefaceFileDb();
      localTypefaceFileDb.setDroidName(((TypefaceFile)localObject2).getDroidName());
      localTypefaceFileDb.setFileName(((TypefaceFile)localObject2).getFileName());
      localTypefaceFileDb.setTypeName("sans");
      localArrayList.add(localTypefaceFileDb);
    }
    paramContext.a(localTypefaceFontDb, localArrayList);
    localArrayList = new ArrayList();
    paramTypefaceFont = paramTypefaceFont.getSerifFonts().iterator();
    while (paramTypefaceFont.hasNext())
    {
      localObject1 = (TypefaceFile)paramTypefaceFont.next();
      localObject2 = new TypefaceFileDb();
      ((TypefaceFileDb)localObject2).setDroidName(((TypefaceFile)localObject1).getDroidName());
      ((TypefaceFileDb)localObject2).setFileName(((TypefaceFile)localObject1).getFileName());
      ((TypefaceFileDb)localObject2).setTypeName("serif");
      localArrayList.add(localObject2);
    }
    paramContext.a(localTypefaceFontDb, localArrayList);
    return localTypefaceFontDb;
  }
  
  public static List<TypefaceFont> a(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject1 = new File(paramString2);
    paramString2 = new ArrayList();
    if ((localObject1 != null) && (((File)localObject1).isDirectory()))
    {
      localObject1 = ((File)localObject1).listFiles();
      if ((localObject1 != null) && (localObject1.length > 0))
      {
        int j = localObject1.length;
        int i = 0;
        if (i < j)
        {
          Object localObject2 = localObject1[i];
          if (((File)localObject2).isFile())
          {
            localObject2 = n.b(paramContext, (File)localObject2);
            if (localObject2 != null) {
              paramString2.add(localObject2);
            }
          }
          for (;;)
          {
            a.a.b.c.a().d(new com.kapp.ifont.b.d(paramString1, 1, paramString2));
            i += 1;
            break;
            ((File)localObject2).getName();
            localObject2 = n.a(paramContext, (File)localObject2);
            if (localObject2 != null) {
              paramString2.add(localObject2);
            }
          }
        }
      }
    }
    return paramString2;
  }
  
  public static List<TypefaceFont> a(Context paramContext, List<TypefaceFontDb> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(a(paramContext, (TypefaceFontDb)paramList.next()));
    }
    return localArrayList;
  }
  
  private static List<TypefaceFont> a(List<TypefaceFont> paramList)
  {
    Collections.sort(paramList, f);
    return paramList;
  }
  
  public static void a(Context paramContext, final String paramString, final boolean paramBoolean)
  {
    a.a.b.c.a().d(new com.kapp.ifont.b.d(paramString, 0));
    new a.b().a(new a.a()
    {
      public Boolean b()
      {
        c.b(this.a, paramString, paramBoolean);
        return Boolean.valueOf(true);
      }
    }).a(new a.c()
    {
      public void a(Boolean paramAnonymousBoolean) {}
    }).a().a();
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    return !e.contains(paramString);
  }
  
  public static File[] a(String paramString)
  {
    File localFile = new File(paramString);
    Object localObject = null;
    paramString = localObject;
    if (localFile != null)
    {
      paramString = localObject;
      if (localFile.isDirectory()) {
        paramString = localFile.listFiles(new FilenameFilter()
        {
          public boolean accept(File paramAnonymousFile, String paramAnonymousString)
          {
            return paramAnonymousString.endsWith(".apk");
          }
        });
      }
    }
    return paramString;
  }
  
  public static List<TypefaceFont> b(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      e.add(paramString);
      paramContext = c(paramContext, paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return localArrayList;
    }
    finally
    {
      e.remove(paramString);
      a.a.b.c.a().d(new com.kapp.ifont.b.d(paramString, 2, localArrayList));
    }
  }
  
  public static List<TypefaceFontDb> b(Context paramContext, List<TypefaceFont> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      localArrayList.add(a(paramContext, (TypefaceFont)paramList.next()));
    }
    return localArrayList;
  }
  
  public static void b(Context paramContext, String paramString, boolean paramBoolean)
  {
    f localF = com.kapp.ifont.a.a().d();
    if ((TextUtils.isEmpty(paramString)) || (a(paramContext, paramString)) || (paramBoolean)) {
      localF.a(new c(paramContext, paramString));
    }
  }
  
  public static List<TypefaceFont> c(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    List localList;
    if (paramString.equals("download"))
    {
      localList = d(paramContext, paramString);
      if (localList != null) {
        localArrayList.addAll(localList);
      }
    }
    if (paramString.equals("install"))
    {
      localList = f(paramContext, paramString);
      if (localList != null) {
        localArrayList.addAll(localList);
      }
    }
    if (paramString.equals("custom"))
    {
      paramContext = a(paramContext, paramString, b.e);
      if (paramContext != null) {
        localArrayList.addAll(paramContext);
      }
    }
    if ((localArrayList != null) && (localArrayList.size() > 0)) {
      a(localArrayList);
    }
    return localArrayList;
  }
  
  public static List<TypefaceFont> d(Context paramContext, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(n.a(paramContext));
    a.a.b.c.a().d(new com.kapp.ifont.b.d(paramString, 1, localArrayList));
    File[] arrayOfFile;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      arrayOfFile = a(b.b);
      if (arrayOfFile != null) {}
    }
    else
    {
      return localArrayList;
    }
    new ArrayList();
    int j = arrayOfFile.length;
    int i = 0;
    label80:
    Object localObject;
    if (i < j) {
      localObject = arrayOfFile[i];
    }
    for (;;)
    {
      try
      {
        List localList = e(paramContext, ((File)localObject).getAbsolutePath());
        if ((localList == null) || (localList.size() <= 0)) {
          continue;
        }
        localArrayList.addAll(localList);
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        continue;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        continue;
      }
      catch (XmlPullParserException localXmlPullParserException)
      {
        localXmlPullParserException.printStackTrace();
        continue;
      }
      a.a.b.c.a().d(new com.kapp.ifont.b.d(paramString, 1, localArrayList));
      i += 1;
      break label80;
      break;
      localObject = n.a(paramContext, ((File)localObject).getAbsolutePath());
      if (localObject != null)
      {
        localArrayList.addAll((Collection)localObject);
        b(paramContext, (List)localObject);
      }
    }
  }
  
  public static List<TypefaceFont> e(Context paramContext, String paramString)
  {
    return a(paramContext, com.kapp.ifont.a.a.a(paramContext).a(paramString));
  }
  
  /* Error */
  public static List<TypefaceFont> f(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: new 38	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 40	java/util/ArrayList:<init>	()V
    //   7: astore 5
    //   9: new 38	java/util/ArrayList
    //   12: dup
    //   13: invokespecial 40	java/util/ArrayList:<init>	()V
    //   16: astore 6
    //   18: new 241	java/io/File
    //   21: dup
    //   22: getstatic 423	com/kapp/ifont/core/b:u	Ljava/lang/String;
    //   25: invokespecial 243	java/io/File:<init>	(Ljava/lang/String;)V
    //   28: astore 7
    //   30: aload 7
    //   32: invokevirtual 426	java/io/File:exists	()Z
    //   35: ifeq +84 -> 119
    //   38: aload 7
    //   40: invokevirtual 430	java/io/File:list	()[Ljava/lang/String;
    //   43: astore 7
    //   45: aload 7
    //   47: arraylength
    //   48: istore_3
    //   49: iconst_0
    //   50: istore_2
    //   51: iload_2
    //   52: iload_3
    //   53: if_icmpge +66 -> 119
    //   56: aload 7
    //   58: iload_2
    //   59: aaload
    //   60: astore 8
    //   62: aload 6
    //   64: aload 8
    //   66: invokeinterface 174 2 0
    //   71: pop
    //   72: aload_0
    //   73: aload 8
    //   75: invokestatic 432	com/kapp/ifont/core/util/n:b	(Landroid/content/Context;Ljava/lang/String;)Ljava/util/List;
    //   78: astore 8
    //   80: aload 8
    //   82: ifnull +13 -> 95
    //   85: aload 5
    //   87: aload 8
    //   89: invokeinterface 366 2 0
    //   94: pop
    //   95: invokestatic 263	a/a/b/c:a	()La/a/b/c;
    //   98: new 265	com/kapp/ifont/b/d
    //   101: dup
    //   102: aload_1
    //   103: iconst_1
    //   104: aload 5
    //   106: invokespecial 268	com/kapp/ifont/b/d:<init>	(Ljava/lang/String;ILjava/util/List;)V
    //   109: invokevirtual 271	a/a/b/c:d	(Ljava/lang/Object;)V
    //   112: iload_2
    //   113: iconst_1
    //   114: iadd
    //   115: istore_2
    //   116: goto -65 -> 51
    //   119: invokestatic 436	com/kapp/ifont/e/j:a	()Z
    //   122: ifeq +124 -> 246
    //   125: aload_0
    //   126: invokevirtual 442	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   129: astore 7
    //   131: aload 7
    //   133: sipush 8192
    //   136: invokevirtual 448	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   139: invokeinterface 134 1 0
    //   144: astore 7
    //   146: aload 7
    //   148: invokeinterface 139 1 0
    //   153: ifeq +93 -> 246
    //   156: aload 7
    //   158: invokeinterface 143 1 0
    //   163: checkcast 450	android/content/pm/PackageInfo
    //   166: astore 8
    //   168: aload 8
    //   170: getfield 453	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   173: invokestatic 456	com/kapp/ifont/core/util/n:a	(Ljava/lang/String;)Z
    //   176: ifeq -30 -> 146
    //   179: aload 6
    //   181: aload 8
    //   183: getfield 453	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   186: invokeinterface 315 2 0
    //   191: istore 4
    //   193: iload 4
    //   195: ifne -49 -> 146
    //   198: aload_0
    //   199: aload 8
    //   201: invokestatic 459	com/kapp/ifont/core/util/n:a	(Landroid/content/Context;Landroid/content/pm/PackageInfo;)Ljava/util/List;
    //   204: astore 8
    //   206: aload 8
    //   208: ifnull +13 -> 221
    //   211: aload 5
    //   213: aload 8
    //   215: invokeinterface 366 2 0
    //   220: pop
    //   221: invokestatic 263	a/a/b/c:a	()La/a/b/c;
    //   224: new 265	com/kapp/ifont/b/d
    //   227: dup
    //   228: aload_1
    //   229: iconst_1
    //   230: aload 5
    //   232: invokespecial 268	com/kapp/ifont/b/d:<init>	(Ljava/lang/String;ILjava/util/List;)V
    //   235: invokevirtual 271	a/a/b/c:d	(Ljava/lang/Object;)V
    //   238: goto -92 -> 146
    //   241: astore_0
    //   242: aload_0
    //   243: invokevirtual 331	java/lang/Exception:printStackTrace	()V
    //   246: aload 5
    //   248: areturn
    //   249: astore 8
    //   251: aload 8
    //   253: invokevirtual 414	java/io/IOException:printStackTrace	()V
    //   256: goto -35 -> 221
    //   259: astore 8
    //   261: aload 8
    //   263: invokevirtual 415	org/xmlpull/v1/XmlPullParserException:printStackTrace	()V
    //   266: goto -45 -> 221
    //   269: astore_0
    //   270: aload_0
    //   271: invokevirtual 331	java/lang/Exception:printStackTrace	()V
    //   274: aload 5
    //   276: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	277	0	paramContext	Context
    //   0	277	1	paramString	String
    //   50	66	2	i	int
    //   48	6	3	j	int
    //   191	3	4	bool	boolean
    //   7	268	5	localArrayList1	ArrayList
    //   16	164	6	localArrayList2	ArrayList
    //   28	129	7	localObject1	Object
    //   60	154	8	localObject2	Object
    //   249	3	8	localIOException	IOException
    //   259	3	8	localXmlPullParserException	XmlPullParserException
    // Exception table:
    //   from	to	target	type
    //   131	146	241	java/lang/Exception
    //   146	193	241	java/lang/Exception
    //   198	206	241	java/lang/Exception
    //   211	221	241	java/lang/Exception
    //   221	238	241	java/lang/Exception
    //   251	256	241	java/lang/Exception
    //   261	266	241	java/lang/Exception
    //   198	206	249	java/io/IOException
    //   211	221	249	java/io/IOException
    //   198	206	259	org/xmlpull/v1/XmlPullParserException
    //   211	221	259	org/xmlpull/v1/XmlPullParserException
    //   9	49	269	java/lang/Exception
    //   62	80	269	java/lang/Exception
    //   85	95	269	java/lang/Exception
    //   95	112	269	java/lang/Exception
    //   119	131	269	java/lang/Exception
    //   242	246	269	java/lang/Exception
  }
  
  public void a() {}
  
  protected boolean a(Throwable paramThrowable)
  {
    return true;
  }
  
  public void b()
  {
    b(this.c, this.d);
  }
  
  protected void c() {}
}
