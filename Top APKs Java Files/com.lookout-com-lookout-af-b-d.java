package com.lookout.af.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import com.lookout.LookoutApplication;
import com.lookout.aa.bi;
import com.lookout.af.g;
import com.lookout.af.i;
import com.lookout.android.c.n;
import com.lookout.android.c.o;
import com.lookout.androidsecurity.i.e;
import com.lookout.security.w;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d
{
  private static d a;
  private static final org.a.b c = org.a.c.a(d.class);
  private final o b = new o();
  
  protected d() {}
  
  private b a(i paramI)
  {
    return new b(paramI.a, paramI.b);
  }
  
  private b a(String paramString1, String paramString2)
  {
    return new b(paramString1, paramString2);
  }
  
  public static d a()
  {
    try
    {
      if (a == null) {
        a = new d();
      }
      d localD = a;
      return localD;
    }
    finally {}
  }
  
  private String a(HashSet paramHashSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramHashSet = paramHashSet.iterator();
    while (paramHashSet.hasNext())
    {
      String str = (String)paramHashSet.next();
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }
  
  private String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "null";
    }
    return com.lookout.r.a.a.b(paramArrayOfByte);
  }
  
  private JSONObject a(String paramString1, int paramInt, long paramLong, String paramString2, byte[][] paramArrayOfByte, HashSet paramHashSet)
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("os", "android");
    localJSONObject.put("package_name", paramString1);
    localJSONObject.put("version_code", paramInt);
    localJSONObject.put("size", paramLong);
    localJSONObject.put("file_sha1", paramString2);
    localJSONObject.put("flags", a(paramHashSet));
    paramString1 = new JSONArray();
    if (paramArrayOfByte == null) {
      paramString1.put(a(null));
    }
    for (;;)
    {
      localJSONObject.put("sigs_sha1", paramString1);
      return localJSONObject;
      int i = paramArrayOfByte.length;
      paramInt = 0;
      while (paramInt < i)
      {
        paramString1.put(a(paramArrayOfByte[paramInt]));
        paramInt += 1;
      }
    }
  }
  
  private static com.lookout.core.b.c b(com.lookout.ae.b paramB, com.lookout.core.b.b paramB1)
  {
    long l = paramB.a(paramB1.a(), -1L);
    if (l != -1L)
    {
      paramB = new com.lookout.core.b.d();
      paramB.a(paramB1);
      paramB.a(l);
      return paramB;
    }
    return null;
  }
  
  private com.lookout.core.b.c b(com.lookout.core.b.b paramB)
  {
    Object localObject = (b)paramB;
    localObject = g.a().a(((b)localObject).b(), ((b)localObject).c());
    if (localObject != null)
    {
      com.lookout.core.b.d localD = new com.lookout.core.b.d();
      localD.a(paramB);
      localD.a(((i)localObject).g);
      return localD;
    }
    return null;
  }
  
  private HashSet b(i paramI)
  {
    paramI = new HashSet();
    paramI.add("quarantined");
    return paramI;
  }
  
  private static Map b(com.lookout.ae.b paramB)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramB.b().iterator();
    while (localIterator.hasNext())
    {
      b localB = b.a((String)localIterator.next());
      if (c(localB.c())) {
        localHashMap.put(localB, b(paramB, localB));
      }
    }
    return Collections.unmodifiableMap(localHashMap);
  }
  
  private JSONObject b(String paramString1, String paramString2)
  {
    paramString2 = e().a(paramString1, paramString2);
    if (paramString2 == null) {
      return null;
    }
    int i = paramString2.d;
    long l = b(paramString2.c);
    byte[][] arrayOfByte = paramString2.a();
    HashSet localHashSet = b(paramString2);
    return a(paramString1, i, l, paramString2.b, arrayOfByte, localHashSet);
  }
  
  private static boolean c(String paramString)
  {
    return paramString.length() % 2 == 0;
  }
  
  /* Error */
  private boolean d(String paramString)
  {
    // Byte code:
    //   0: new 228	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 231	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 5
    //   10: aload 5
    //   12: invokevirtual 234	java/io/File:exists	()Z
    //   15: ifeq +55 -> 70
    //   18: aload 5
    //   20: invokevirtual 237	java/io/File:canRead	()Z
    //   23: ifeq +47 -> 70
    //   26: new 239	com/lookout/aa/a/b/e
    //   29: dup
    //   30: aload 5
    //   32: invokespecial 242	com/lookout/aa/a/b/e:<init>	(Ljava/io/File;)V
    //   35: astore_3
    //   36: aload_3
    //   37: astore_1
    //   38: aload_3
    //   39: invokevirtual 245	com/lookout/aa/a/b/e:b	()Lcom/lookout/aa/a/b/g;
    //   42: astore 4
    //   44: aload 4
    //   46: ifnull +26 -> 72
    //   49: aload_3
    //   50: astore_1
    //   51: aload 4
    //   53: invokevirtual 248	com/lookout/aa/a/b/g:b	()Ljava/lang/String;
    //   56: ldc -6
    //   58: invokevirtual 253	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   61: istore_2
    //   62: iload_2
    //   63: ifeq -27 -> 36
    //   66: aload_3
    //   67: invokestatic 258	com/lookout/utils/bf:a	(Lcom/lookout/aa/a/b/e;)V
    //   70: iconst_0
    //   71: ireturn
    //   72: aload_3
    //   73: invokestatic 258	com/lookout/utils/bf:a	(Lcom/lookout/aa/a/b/e;)V
    //   76: iconst_1
    //   77: ireturn
    //   78: astore 4
    //   80: aconst_null
    //   81: astore_3
    //   82: aload_3
    //   83: astore_1
    //   84: getstatic 19	com/lookout/af/b/d:c	Lorg/a/b;
    //   87: new 49	java/lang/StringBuilder
    //   90: dup
    //   91: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   94: ldc_w 260
    //   97: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: aload 5
    //   102: invokevirtual 263	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: aload 4
    //   110: invokeinterface 268 3 0
    //   115: aload_3
    //   116: invokestatic 258	com/lookout/utils/bf:a	(Lcom/lookout/aa/a/b/e;)V
    //   119: iconst_0
    //   120: ireturn
    //   121: astore_3
    //   122: aconst_null
    //   123: astore_1
    //   124: aload_1
    //   125: invokestatic 258	com/lookout/utils/bf:a	(Lcom/lookout/aa/a/b/e;)V
    //   128: aload_3
    //   129: athrow
    //   130: astore_3
    //   131: goto -7 -> 124
    //   134: astore 4
    //   136: goto -54 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	d
    //   0	139	1	paramString	String
    //   61	2	2	bool	boolean
    //   35	81	3	localE	com.lookout.aa.a.b.e
    //   121	8	3	localObject1	Object
    //   130	1	3	localObject2	Object
    //   42	10	4	localG	com.lookout.aa.a.b.g
    //   78	31	4	localException1	Exception
    //   134	1	4	localException2	Exception
    //   8	93	5	localFile	File
    // Exception table:
    //   from	to	target	type
    //   26	36	78	java/lang/Exception
    //   26	36	121	finally
    //   38	44	130	finally
    //   51	62	130	finally
    //   84	115	130	finally
    //   38	44	134	java/lang/Exception
    //   51	62	134	java/lang/Exception
  }
  
  public b a(String paramString)
  {
    try
    {
      Object localObject = d().getPackageInfo(paramString, 4096);
      localObject = this.b.a((PackageInfo)localObject).a(paramString);
      if ((localObject != null) && (((w)localObject).g() != null))
      {
        localObject = new b(paramString, ((w)localObject).g());
        return localObject;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      return null;
    }
    catch (bi localBi)
    {
      c.d("Exception trying to fetch packageInfo for " + paramString, localBi);
      return null;
    }
    return null;
  }
  
  public com.lookout.core.b.c a(com.lookout.ae.b paramB, com.lookout.core.b.b paramB1)
  {
    return b(paramB, paramB1);
  }
  
  public com.lookout.core.b.c a(b paramB)
  {
    try
    {
      PackageInfo localPackageInfo = d().getPackageInfo(paramB.b(), 0);
      com.lookout.core.b.d localD = new com.lookout.core.b.d();
      localD.a(paramB);
      paramB = new File(localPackageInfo.applicationInfo.sourceDir);
      if (paramB.exists())
      {
        localD.a(paramB.lastModified());
        return localD;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      return b(paramB);
    }
    return null;
  }
  
  protected HashSet a(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    HashSet localHashSet = new HashSet();
    Object localObject = paramPackageInfo.applicationInfo;
    if (localObject != null)
    {
      if ((((ApplicationInfo)localObject).flags & 0x1) != 0) {
        localHashSet.add("system");
      }
      if ((((ApplicationInfo)localObject).flags & 0x80) != 0) {
        localHashSet.add("system");
      }
      if ((((ApplicationInfo)localObject).flags & 0x20000000) != 0) {
        localHashSet.add("forward_lock");
      }
      if ((((ApplicationInfo)localObject).flags & 0x4) != 0) {
        localHashSet.add("has_code");
      }
      localObject = ((ApplicationInfo)localObject).sourceDir;
      if (d((String)localObject)) {
        localHashSet.add("no_classes_dex");
      }
      File localFile = new File((String)localObject);
      if (localFile.exists())
      {
        String str = localFile.getName();
        if (new File(localFile.getParent(), str.substring(0, str.lastIndexOf('.')) + ".odex").exists()) {
          localHashSet.add("odex");
        }
      }
      if (((String)localObject).startsWith(Environment.getRootDirectory().getAbsolutePath())) {
        localHashSet.add("system_dir");
      }
    }
    paramPackageManager = paramPackageManager.getInstallerPackageName(paramPackageInfo.packageName);
    if (org.apache.a.e.d.c(paramPackageManager)) {
      localHashSet.add(e.a().e(paramPackageManager));
    }
    return localHashSet;
  }
  
  public Map a(com.lookout.ae.b paramB)
  {
    return b(paramB);
  }
  
  public JSONObject a(com.lookout.core.b.b paramB)
  {
    Object localObject1 = null;
    b localB = (b)paramB;
    Object localObject2 = d();
    try
    {
      PackageInfo localPackageInfo = ((PackageManager)localObject2).getPackageInfo(localB.b(), 64);
      localObject2 = a(localB.b(), (PackageManager)localObject2, localPackageInfo);
      localObject1 = localObject2;
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        localObject1 = b(localB.b(), localB.c());
        if (localObject1 != null) {
          break label205;
        }
        throw new com.lookout.c.d("Unable to find app data in any store for key: " + paramB.a());
      }
      catch (JSONException localJSONException2)
      {
        throw new com.lookout.c.d("Unable to parse JSON from quarantine stored for key: " + paramB.a());
      }
      localJSONException1 = localJSONException1;
      throw new com.lookout.c.d("Unable to parse JSON stored for key from package manager: " + paramB.a());
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        c.c("Unable to find record in package manager for: " + paramB.a());
      }
    }
    if (localObject1 == null) {
      c.c("Attempting to get Json from quarantine.");
    }
    for (;;)
    {
      label205:
      return localJSONException2;
    }
  }
  
  protected JSONObject a(String paramString, PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    return a(paramString, paramPackageManager, paramPackageInfo, null);
  }
  
  protected JSONObject a(String paramString1, PackageManager paramPackageManager, PackageInfo paramPackageInfo, String paramString2)
  {
    com.lookout.androidsecurity.a.a.a localA = new com.lookout.androidsecurity.a.a.a(paramPackageInfo, paramPackageManager);
    paramPackageManager = a(paramPackageManager, paramPackageInfo);
    c.b(paramString1 + ": " + paramPackageManager);
    if (paramString2 == null) {}
    for (paramString1 = a(b(localA));; paramString1 = paramString2) {
      return a(localA.p(), localA.m(), c(localA), paramString1, a(localA), paramPackageManager);
    }
  }
  
  protected byte[][] a(com.lookout.androidsecurity.a.a.a paramA)
  {
    return paramA.f();
  }
  
  protected long b(String paramString)
  {
    return new File(paramString).length();
  }
  
  public List b()
  {
    Object localObject1 = d().getInstalledPackages(4096);
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      PackageInfo localPackageInfo;
      try
      {
        n localN = this.b.a((List)localObject1);
        localObject1 = ((List)localObject1).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localPackageInfo = (PackageInfo)((Iterator)localObject1).next();
        Object localObject2 = localN.a(localPackageInfo.packageName);
        if (localObject2 == null) {
          break label149;
        }
        localObject2 = a(localPackageInfo.packageName, ((w)localObject2).g());
        if (localObject2 != null)
        {
          localArrayList.add(localObject2);
          continue;
        }
        c.d("Could not get data ID for " + localPackageInfo);
      }
      catch (bi localBi)
      {
        c.d("ScannerException while enumeratingDataId", localBi);
        return localArrayList;
      }
      continue;
      label149:
      c.d("Could not get data ID for " + localPackageInfo);
    }
    com.lookout.af.d.a().b();
    Iterator localIterator = e().b().iterator();
    while (localIterator.hasNext())
    {
      localObject1 = a((i)localIterator.next());
      if (!localArrayList.contains(localObject1)) {
        localArrayList.add(localObject1);
      }
    }
    return localArrayList;
  }
  
  protected byte[] b(com.lookout.androidsecurity.a.a.a paramA)
  {
    return paramA.e();
  }
  
  protected long c(com.lookout.androidsecurity.a.a.a paramA)
  {
    return paramA.g();
  }
  
  public JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    Object localObject1 = d();
    HashSet localHashSet = new HashSet();
    Object localObject2;
    try
    {
      Object localObject3 = ((PackageManager)localObject1).getInstalledPackages(4160);
      localObject2 = this.b.a((List)localObject3);
      localObject3 = ((List)localObject3).iterator();
      for (;;)
      {
        if (!((Iterator)localObject3).hasNext()) {
          break label184;
        }
        localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
        localHashSet.add(localPackageInfo.packageName);
        localW = ((n)localObject2).a(localPackageInfo.packageName);
        if (localW != null) {
          break;
        }
        localJSONArray.put(a(localPackageInfo.packageName, (PackageManager)localObject1, localPackageInfo));
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        PackageInfo localPackageInfo;
        w localW;
        throw new com.lookout.c.d("Unable to generate JSON for installed packages", localJSONException);
        localJSONArray.put(a(localPackageInfo.packageName, (PackageManager)localObject1, localPackageInfo, localW.g()));
      }
    }
    catch (bi localBi)
    {
      throw new com.lookout.c.d("Unable to load package list", localBi);
    }
    label184:
    com.lookout.af.d.a().b();
    localObject1 = e().b().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (i)((Iterator)localObject1).next();
      if (!localHashSet.contains(((i)localObject2).a)) {
        localJSONArray.put(b(((i)localObject2).a, ((i)localObject2).b));
      }
    }
    localBi.put("device_apps", localJSONArray);
    return localBi;
  }
  
  protected PackageManager d()
  {
    return LookoutApplication.getContext().getPackageManager();
  }
  
  protected g e()
  {
    return g.a();
  }
}
