package com.microsoft.appmanager.DataProvider;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.util.Log;
import com.google.gson.Gson;
import com.microsoft.appmanager.model.AppMeta;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class AppMetadataProvider
{
  private static AppMetadataProvider b = new AppMetadataProvider();
  private List<OnAppMetaDataChangeListener> a = new ArrayList();
  private Context c;
  private ArrayList<AppMeta> d;
  private long e;
  
  public AppMetadataProvider() {}
  
  private AppMetadataList a(InputStreamReader paramInputStreamReader)
  {
    try
    {
      paramInputStreamReader = new BufferedReader(paramInputStreamReader);
      paramInputStreamReader = (AppMetadataList)new Gson().fromJson(paramInputStreamReader, AppMetadataList.class);
      return paramInputStreamReader;
    }
    catch (Exception paramInputStreamReader)
    {
      Log.e("AppMetadataProvider", "Failed to load app metas.", paramInputStreamReader);
    }
    return null;
  }
  
  public static AppMetadataProvider a()
  {
    return b;
  }
  
  private void b(Context paramContext)
  {
    try
    {
      Object localObject1 = h();
      Object localObject2 = i();
      if (localObject1 == null)
      {
        paramContext = (Context)localObject2;
      }
      else
      {
        paramContext = (Context)localObject2;
        if (((AppMetadataList)localObject1).version > ((AppMetadataList)localObject2).version) {
          paramContext = (Context)localObject1;
        }
      }
      localObject1 = b.a(paramContext.appList);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("App list of version ");
      ((StringBuilder)localObject2).append(paramContext.version);
      ((StringBuilder)localObject2).append(" was loaded, with ");
      ((StringBuilder)localObject2).append(((ArrayList)localObject1).size());
      ((StringBuilder)localObject2).append(" apps in it.");
      Log.d("AppMetadataProvider", ((StringBuilder)localObject2).toString());
      if (localObject1 != null)
      {
        this.d = ((ArrayList)localObject1);
        this.e = paramContext.version;
        return;
      }
    }
    catch (Exception paramContext)
    {
      Log.e("AppMetadataProvider", "Failed to load app list.", paramContext);
    }
  }
  
  private boolean c(String paramString)
  {
    return this.c.getFileStreamPath(paramString).exists();
  }
  
  private int d(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("banner_");
    localStringBuilder.append(paramString.replace('.', '_').toLowerCase());
    paramString = localStringBuilder.toString();
    return this.c.getResources().getIdentifier(paramString, "drawable", this.c.getPackageName());
  }
  
  private void g()
  {
    if (this.d == null) {
      b(this.c);
    }
  }
  
  /* Error */
  private AppMetadataList h()
  {
    // Byte code:
    //   0: ldc 52
    //   2: ldc -92
    //   4: invokestatic 109	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   7: pop
    //   8: aload_0
    //   9: ldc -90
    //   11: invokespecial 168	com/microsoft/appmanager/DataProvider/AppMetadataProvider:c	(Ljava/lang/String;)Z
    //   14: istore_1
    //   15: aconst_null
    //   16: astore 4
    //   18: iload_1
    //   19: ifne +13 -> 32
    //   22: ldc 52
    //   24: ldc -86
    //   26: invokestatic 109	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   29: pop
    //   30: aconst_null
    //   31: areturn
    //   32: new 172	java/io/InputStreamReader
    //   35: dup
    //   36: aload_0
    //   37: getfield 118	com/microsoft/appmanager/DataProvider/AppMetadataProvider:c	Landroid/content/Context;
    //   40: ldc -90
    //   42: invokevirtual 176	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   45: invokespecial 179	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   48: astore_3
    //   49: aload_3
    //   50: astore_2
    //   51: aload_0
    //   52: aload_3
    //   53: invokespecial 181	com/microsoft/appmanager/DataProvider/AppMetadataProvider:a	(Ljava/io/InputStreamReader;)Lcom/microsoft/appmanager/DataProvider/AppMetadataList;
    //   56: astore 4
    //   58: aload_3
    //   59: ifnull +15 -> 74
    //   62: aload_3
    //   63: invokevirtual 184	java/io/InputStreamReader:close	()V
    //   66: aload 4
    //   68: areturn
    //   69: astore_2
    //   70: aload_2
    //   71: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   74: aload 4
    //   76: areturn
    //   77: astore 4
    //   79: goto +11 -> 90
    //   82: astore_2
    //   83: goto +37 -> 120
    //   86: astore 4
    //   88: aconst_null
    //   89: astore_3
    //   90: aload_3
    //   91: astore_2
    //   92: aload 4
    //   94: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   97: aload_3
    //   98: ifnull +14 -> 112
    //   101: aload_3
    //   102: invokevirtual 184	java/io/InputStreamReader:close	()V
    //   105: aconst_null
    //   106: areturn
    //   107: astore_2
    //   108: aload_2
    //   109: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   112: aconst_null
    //   113: areturn
    //   114: astore_3
    //   115: aload_2
    //   116: astore 4
    //   118: aload_3
    //   119: astore_2
    //   120: aload 4
    //   122: ifnull +16 -> 138
    //   125: aload 4
    //   127: invokevirtual 184	java/io/InputStreamReader:close	()V
    //   130: goto +8 -> 138
    //   133: astore_3
    //   134: aload_3
    //   135: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   138: aload_2
    //   139: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	this	AppMetadataProvider
    //   14	5	1	bool	boolean
    //   50	1	2	localInputStreamReader1	InputStreamReader
    //   69	2	2	localIOException1	java.io.IOException
    //   82	1	2	localObject1	Object
    //   91	1	2	localInputStreamReader2	InputStreamReader
    //   107	9	2	localIOException2	java.io.IOException
    //   119	20	2	localObject2	Object
    //   48	54	3	localInputStreamReader3	InputStreamReader
    //   114	5	3	localObject3	Object
    //   133	2	3	localIOException3	java.io.IOException
    //   16	59	4	localAppMetadataList	AppMetadataList
    //   77	1	4	localIOException4	java.io.IOException
    //   86	7	4	localIOException5	java.io.IOException
    //   116	10	4	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   62	66	69	java/io/IOException
    //   51	58	77	java/io/IOException
    //   32	49	82	finally
    //   32	49	86	java/io/IOException
    //   101	105	107	java/io/IOException
    //   51	58	114	finally
    //   92	97	114	finally
    //   125	130	133	java/io/IOException
  }
  
  /* Error */
  private AppMetadataList i()
  {
    // Byte code:
    //   0: ldc 52
    //   2: ldc -67
    //   4: invokestatic 109	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   7: pop
    //   8: aconst_null
    //   9: astore_3
    //   10: new 172	java/io/InputStreamReader
    //   13: dup
    //   14: aload_0
    //   15: getfield 118	com/microsoft/appmanager/DataProvider/AppMetadataProvider:c	Landroid/content/Context;
    //   18: invokevirtual 193	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   21: ldc -90
    //   23: invokevirtual 199	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   26: invokespecial 179	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   29: astore_2
    //   30: aload_2
    //   31: astore_1
    //   32: aload_0
    //   33: aload_2
    //   34: invokespecial 181	com/microsoft/appmanager/DataProvider/AppMetadataProvider:a	(Ljava/io/InputStreamReader;)Lcom/microsoft/appmanager/DataProvider/AppMetadataList;
    //   37: astore_3
    //   38: aload_2
    //   39: ifnull +14 -> 53
    //   42: aload_2
    //   43: invokevirtual 184	java/io/InputStreamReader:close	()V
    //   46: aload_3
    //   47: areturn
    //   48: astore_1
    //   49: aload_1
    //   50: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   53: aload_3
    //   54: areturn
    //   55: astore_3
    //   56: goto +10 -> 66
    //   59: astore_1
    //   60: goto +34 -> 94
    //   63: astore_3
    //   64: aconst_null
    //   65: astore_2
    //   66: aload_2
    //   67: astore_1
    //   68: aload_3
    //   69: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   72: aload_2
    //   73: ifnull +14 -> 87
    //   76: aload_2
    //   77: invokevirtual 184	java/io/InputStreamReader:close	()V
    //   80: aconst_null
    //   81: areturn
    //   82: astore_1
    //   83: aload_1
    //   84: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   87: aconst_null
    //   88: areturn
    //   89: astore_2
    //   90: aload_1
    //   91: astore_3
    //   92: aload_2
    //   93: astore_1
    //   94: aload_3
    //   95: ifnull +15 -> 110
    //   98: aload_3
    //   99: invokevirtual 184	java/io/InputStreamReader:close	()V
    //   102: goto +8 -> 110
    //   105: astore_2
    //   106: aload_2
    //   107: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   110: aload_1
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	this	AppMetadataProvider
    //   31	1	1	localInputStreamReader1	InputStreamReader
    //   48	2	1	localIOException1	java.io.IOException
    //   59	1	1	localObject1	Object
    //   67	1	1	localInputStreamReader2	InputStreamReader
    //   82	9	1	localIOException2	java.io.IOException
    //   93	18	1	localObject2	Object
    //   29	48	2	localInputStreamReader3	InputStreamReader
    //   89	4	2	localObject3	Object
    //   105	2	2	localIOException3	java.io.IOException
    //   9	45	3	localAppMetadataList	AppMetadataList
    //   55	1	3	localIOException4	java.io.IOException
    //   63	6	3	localIOException5	java.io.IOException
    //   91	8	3	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   42	46	48	java/io/IOException
    //   32	38	55	java/io/IOException
    //   10	30	59	finally
    //   10	30	63	java/io/IOException
    //   76	80	82	java/io/IOException
    //   32	38	89	finally
    //   68	72	89	finally
    //   98	102	105	java/io/IOException
  }
  
  public AppMeta a(String paramString)
  {
    g();
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext())
      {
        AppMeta localAppMeta = (AppMeta)localIterator.next();
        if (localAppMeta.Id.equalsIgnoreCase(paramString)) {
          return localAppMeta;
        }
      }
    }
    return null;
  }
  
  public void a(Context paramContext)
  {
    this.c = paramContext;
  }
  
  /* Error */
  public void a(AppMetadataList paramAppMetadataList)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: aconst_null
    //   6: astore 4
    //   8: aconst_null
    //   9: astore_3
    //   10: aload_3
    //   11: astore_2
    //   12: new 43	com/google/gson/Gson
    //   15: dup
    //   16: invokespecial 44	com/google/gson/Gson:<init>	()V
    //   19: aload_1
    //   20: invokevirtual 229	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   23: astore 5
    //   25: aload_3
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 118	com/microsoft/appmanager/DataProvider/AppMetadataProvider:c	Landroid/content/Context;
    //   31: ldc -90
    //   33: iconst_0
    //   34: invokevirtual 233	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   37: astore_3
    //   38: aload_3
    //   39: aload 5
    //   41: ldc -21
    //   43: invokevirtual 239	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   46: invokevirtual 245	java/io/FileOutputStream:write	([B)V
    //   49: aload_1
    //   50: getfield 74	com/microsoft/appmanager/DataProvider/AppMetadataList:appList	Ljava/util/ArrayList;
    //   53: invokestatic 79	com/microsoft/appmanager/DataProvider/b:a	(Ljava/util/List;)Ljava/util/ArrayList;
    //   56: astore_2
    //   57: aload_2
    //   58: invokevirtual 97	java/util/ArrayList:size	()I
    //   61: ifle +16 -> 77
    //   64: aload_0
    //   65: aload_2
    //   66: putfield 111	com/microsoft/appmanager/DataProvider/AppMetadataProvider:d	Ljava/util/ArrayList;
    //   69: aload_0
    //   70: aload_1
    //   71: getfield 71	com/microsoft/appmanager/DataProvider/AppMetadataList:version	J
    //   74: putfield 113	com/microsoft/appmanager/DataProvider/AppMetadataProvider:e	J
    //   77: aload_3
    //   78: ifnull +54 -> 132
    //   81: aload_3
    //   82: invokevirtual 246	java/io/FileOutputStream:close	()V
    //   85: goto +47 -> 132
    //   88: astore_1
    //   89: goto +80 -> 169
    //   92: astore_2
    //   93: aload_3
    //   94: astore_1
    //   95: aload_2
    //   96: astore_3
    //   97: goto +13 -> 110
    //   100: astore_1
    //   101: aload_2
    //   102: astore_3
    //   103: goto +66 -> 169
    //   106: astore_3
    //   107: aload 4
    //   109: astore_1
    //   110: aload_1
    //   111: astore_2
    //   112: aload_3
    //   113: invokevirtual 247	java/lang/Exception:printStackTrace	()V
    //   116: aload_1
    //   117: ifnull +15 -> 132
    //   120: aload_1
    //   121: invokevirtual 246	java/io/FileOutputStream:close	()V
    //   124: goto +8 -> 132
    //   127: astore_1
    //   128: aload_1
    //   129: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   132: aload_0
    //   133: getfield 33	com/microsoft/appmanager/DataProvider/AppMetadataProvider:a	Ljava/util/List;
    //   136: invokeinterface 250 1 0
    //   141: astore_1
    //   142: aload_1
    //   143: invokeinterface 211 1 0
    //   148: ifeq +20 -> 168
    //   151: aload_1
    //   152: invokeinterface 215 1 0
    //   157: checkcast 6	com/microsoft/appmanager/DataProvider/AppMetadataProvider$OnAppMetaDataChangeListener
    //   160: invokeinterface 253 1 0
    //   165: goto -23 -> 142
    //   168: return
    //   169: aload_3
    //   170: ifnull +15 -> 185
    //   173: aload_3
    //   174: invokevirtual 246	java/io/FileOutputStream:close	()V
    //   177: goto +8 -> 185
    //   180: astore_2
    //   181: aload_2
    //   182: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   185: aload_1
    //   186: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	this	AppMetadataProvider
    //   0	187	1	paramAppMetadataList	AppMetadataList
    //   11	55	2	localObject1	Object
    //   92	10	2	localException1	Exception
    //   111	1	2	localAppMetadataList	AppMetadataList
    //   180	2	2	localIOException	java.io.IOException
    //   9	94	3	localObject2	Object
    //   106	68	3	localException2	Exception
    //   6	102	4	localObject3	Object
    //   23	17	5	str	String
    // Exception table:
    //   from	to	target	type
    //   38	77	88	finally
    //   38	77	92	java/lang/Exception
    //   12	25	100	finally
    //   27	38	100	finally
    //   112	116	100	finally
    //   12	25	106	java/lang/Exception
    //   27	38	106	java/lang/Exception
    //   81	85	127	java/io/IOException
    //   120	124	127	java/io/IOException
    //   173	177	180	java/io/IOException
  }
  
  public void a(OnAppMetaDataChangeListener paramOnAppMetaDataChangeListener)
  {
    if (!this.a.contains(paramOnAppMetaDataChangeListener)) {
      this.a.add(paramOnAppMetaDataChangeListener);
    }
  }
  
  public ArrayList<AppMeta> b()
  {
    g();
    if (this.d == null) {
      return new ArrayList();
    }
    return new ArrayList(this.d);
  }
  
  public void b(OnAppMetaDataChangeListener paramOnAppMetaDataChangeListener)
  {
    if (this.a.contains(paramOnAppMetaDataChangeListener)) {
      this.a.remove(paramOnAppMetaDataChangeListener);
    }
  }
  
  public boolean b(String paramString)
  {
    Iterator localIterator = this.c.getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public long c()
  {
    return this.e;
  }
  
  public ArrayList<AppMeta> d()
  {
    Object localObject = e();
    HashSet localHashSet = new HashSet();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localHashSet.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    localObject = new ArrayList();
    Iterator localIterator = b().iterator();
    while (localIterator.hasNext())
    {
      AppMeta localAppMeta = (AppMeta)localIterator.next();
      if (localHashSet.contains(localAppMeta.Id)) {
        ((ArrayList)localObject).add(localAppMeta);
      }
    }
    return localObject;
  }
  
  /* Error */
  public List<PackageInfo> e()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 118	com/microsoft/appmanager/DataProvider/AppMetadataProvider:c	Landroid/content/Context;
    //   4: invokevirtual 274	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: astore 5
    //   9: aload 5
    //   11: iconst_0
    //   12: invokevirtual 303	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   15: astore_1
    //   16: aload_1
    //   17: areturn
    //   18: new 30	java/util/ArrayList
    //   21: dup
    //   22: invokespecial 31	java/util/ArrayList:<init>	()V
    //   25: astore 4
    //   27: aconst_null
    //   28: astore_3
    //   29: aconst_null
    //   30: astore_2
    //   31: aload_2
    //   32: astore_1
    //   33: invokestatic 309	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   36: ldc_w 311
    //   39: invokevirtual 315	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   42: astore 6
    //   44: aload_2
    //   45: astore_1
    //   46: new 38	java/io/BufferedReader
    //   49: dup
    //   50: new 172	java/io/InputStreamReader
    //   53: dup
    //   54: aload 6
    //   56: invokevirtual 321	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   59: invokespecial 179	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   62: invokespecial 41	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   65: astore_2
    //   66: aload_2
    //   67: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   70: astore_1
    //   71: aload_1
    //   72: ifnull +32 -> 104
    //   75: aload 4
    //   77: aload 5
    //   79: aload_1
    //   80: aload_1
    //   81: bipush 58
    //   83: invokevirtual 328	java/lang/String:indexOf	(I)I
    //   86: iconst_1
    //   87: iadd
    //   88: invokevirtual 332	java/lang/String:substring	(I)Ljava/lang/String;
    //   91: iconst_0
    //   92: invokevirtual 336	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   95: invokeinterface 261 2 0
    //   100: pop
    //   101: goto -35 -> 66
    //   104: aload 6
    //   106: invokevirtual 339	java/lang/Process:waitFor	()I
    //   109: pop
    //   110: aload_2
    //   111: ifnull +53 -> 164
    //   114: aload_2
    //   115: invokevirtual 340	java/io/BufferedReader:close	()V
    //   118: aload 4
    //   120: areturn
    //   121: astore_3
    //   122: aload_2
    //   123: astore_1
    //   124: aload_3
    //   125: astore_2
    //   126: goto +41 -> 167
    //   129: astore_3
    //   130: goto +12 -> 142
    //   133: astore_2
    //   134: goto +33 -> 167
    //   137: astore_1
    //   138: aload_3
    //   139: astore_2
    //   140: aload_1
    //   141: astore_3
    //   142: aload_2
    //   143: astore_1
    //   144: aload_3
    //   145: invokevirtual 247	java/lang/Exception:printStackTrace	()V
    //   148: aload_2
    //   149: ifnull +15 -> 164
    //   152: aload_2
    //   153: invokevirtual 340	java/io/BufferedReader:close	()V
    //   156: aload 4
    //   158: areturn
    //   159: astore_1
    //   160: aload_1
    //   161: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   164: aload 4
    //   166: areturn
    //   167: aload_1
    //   168: ifnull +15 -> 183
    //   171: aload_1
    //   172: invokevirtual 340	java/io/BufferedReader:close	()V
    //   175: goto +8 -> 183
    //   178: astore_1
    //   179: aload_1
    //   180: invokevirtual 187	java/io/IOException:printStackTrace	()V
    //   183: aload_2
    //   184: athrow
    //   185: astore_1
    //   186: goto -168 -> 18
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	this	AppMetadataProvider
    //   15	109	1	localObject1	Object
    //   137	4	1	localException1	Exception
    //   143	1	1	localObject2	Object
    //   159	13	1	localIOException1	java.io.IOException
    //   178	2	1	localIOException2	java.io.IOException
    //   185	1	1	localException2	Exception
    //   30	96	2	localObject3	Object
    //   133	1	2	localObject4	Object
    //   139	45	2	localException3	Exception
    //   28	1	3	localObject5	Object
    //   121	4	3	localObject6	Object
    //   129	10	3	localException4	Exception
    //   141	4	3	localException5	Exception
    //   25	140	4	localArrayList	ArrayList
    //   7	71	5	localPackageManager	PackageManager
    //   42	63	6	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   66	71	121	finally
    //   75	101	121	finally
    //   104	110	121	finally
    //   66	71	129	java/lang/Exception
    //   75	101	129	java/lang/Exception
    //   104	110	129	java/lang/Exception
    //   33	44	133	finally
    //   46	66	133	finally
    //   144	148	133	finally
    //   33	44	137	java/lang/Exception
    //   46	66	137	java/lang/Exception
    //   114	118	159	java/io/IOException
    //   152	156	159	java/io/IOException
    //   171	175	178	java/io/IOException
    //   9	16	185	java/lang/Exception
  }
  
  /* Error */
  public List<com.microsoft.appmanager.model.BannerInfo> f()
  {
    // Byte code:
    //   0: new 30	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 31	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_1
    //   13: new 38	java/io/BufferedReader
    //   16: dup
    //   17: new 172	java/io/InputStreamReader
    //   20: dup
    //   21: aload_0
    //   22: getfield 118	com/microsoft/appmanager/DataProvider/AppMetadataProvider:c	Landroid/content/Context;
    //   25: invokevirtual 193	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   28: ldc_w 344
    //   31: invokevirtual 199	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   34: invokespecial 179	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   37: invokespecial 41	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   40: astore_2
    //   41: aload_2
    //   42: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   45: astore_1
    //   46: aload_1
    //   47: ifnull +45 -> 92
    //   50: aload_1
    //   51: invokevirtual 347	java/lang/String:isEmpty	()Z
    //   54: ifeq +11 -> 65
    //   57: aload_2
    //   58: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   61: astore_1
    //   62: goto -16 -> 46
    //   65: aload 4
    //   67: new 349	com/microsoft/appmanager/model/BannerInfo
    //   70: dup
    //   71: aload_1
    //   72: aload_0
    //   73: aload_1
    //   74: invokespecial 351	com/microsoft/appmanager/DataProvider/AppMetadataProvider:d	(Ljava/lang/String;)I
    //   77: invokespecial 354	com/microsoft/appmanager/model/BannerInfo:<init>	(Ljava/lang/String;I)V
    //   80: invokevirtual 300	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   83: pop
    //   84: aload_2
    //   85: invokevirtual 324	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   88: astore_1
    //   89: goto -43 -> 46
    //   92: aload_2
    //   93: ifnull +40 -> 133
    //   96: aload_2
    //   97: invokevirtual 340	java/io/BufferedReader:close	()V
    //   100: aload 4
    //   102: areturn
    //   103: astore_1
    //   104: goto +11 -> 115
    //   107: goto +18 -> 125
    //   110: astore_3
    //   111: aload_1
    //   112: astore_2
    //   113: aload_3
    //   114: astore_1
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 340	java/io/BufferedReader:close	()V
    //   123: aload_1
    //   124: athrow
    //   125: aload_2
    //   126: ifnull +7 -> 133
    //   129: aload_2
    //   130: invokevirtual 340	java/io/BufferedReader:close	()V
    //   133: aload 4
    //   135: areturn
    //   136: astore_1
    //   137: aload_3
    //   138: astore_2
    //   139: goto -14 -> 125
    //   142: astore_1
    //   143: goto -36 -> 107
    //   146: astore_1
    //   147: aload 4
    //   149: areturn
    //   150: astore_2
    //   151: goto -28 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	this	AppMetadataProvider
    //   12	77	1	str	String
    //   103	9	1	localObject1	Object
    //   114	10	1	localObject2	Object
    //   136	1	1	localIOException1	java.io.IOException
    //   142	1	1	localIOException2	java.io.IOException
    //   146	1	1	localIOException3	java.io.IOException
    //   40	99	2	localObject3	Object
    //   150	1	2	localIOException4	java.io.IOException
    //   10	1	3	localObject4	Object
    //   110	28	3	localObject5	Object
    //   7	141	4	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   41	46	103	finally
    //   50	62	103	finally
    //   65	89	103	finally
    //   13	41	110	finally
    //   13	41	136	java/io/IOException
    //   41	46	142	java/io/IOException
    //   50	62	142	java/io/IOException
    //   65	89	142	java/io/IOException
    //   96	100	146	java/io/IOException
    //   129	133	146	java/io/IOException
    //   119	123	150	java/io/IOException
  }
  
  public static abstract interface OnAppMetaDataChangeListener
  {
    public abstract void onDataChange();
  }
}
