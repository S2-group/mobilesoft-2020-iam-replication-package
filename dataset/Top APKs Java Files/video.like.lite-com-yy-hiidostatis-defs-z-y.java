package com.yy.hiidostatis.defs.z;

import android.content.Context;
import com.yy.hiidostatis.defs.y.w;
import com.yy.hiidostatis.inner.util.e;
import com.yy.hiidostatis.inner.util.y.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class y
{
  private com.yy.hiidostatis.defs.y.y y;
  private w z;
  
  public y(w paramW, com.yy.hiidostatis.defs.y.y paramY)
  {
    this.z = paramW;
    this.y = paramY;
  }
  
  private static String z(List<Map<String, Object>> paramList, String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramList != null)
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Map localMap = (Map)paramList.next();
        if (paramString.equals(localMap.get("type"))) {
          localStringBuffer.append(localMap.get("appname")).append(";").append(localMap.get("appid")).append(";").append(localMap.get("firstInstallTime")).append(";").append(localMap.get("lastUpdateTime")).append("|");
        }
      }
      if (localStringBuffer.length() > 0) {
        localStringBuffer.setLength(localStringBuffer.length() - 1);
      }
      b.y(y.class, "type=%s,applist2 length=%d,applist2 bypes length=%d", new Object[] { paramString, Integer.valueOf(localStringBuffer.toString().length()), Integer.valueOf(localStringBuffer.toString().getBytes().length) });
      b.y(y.class, "applist2=%s", new Object[] { localStringBuffer.toString() });
    }
    return localStringBuffer.toString();
  }
  
  /* Error */
  @android.annotation.SuppressLint({"NewApi"})
  public static List<Map<String, Object>> z(Context paramContext)
  {
    // Byte code:
    //   0: new 127	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 128	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: invokevirtual 134	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   12: astore_0
    //   13: aload_0
    //   14: iconst_0
    //   15: invokevirtual 140	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   18: astore_3
    //   19: iconst_0
    //   20: istore_1
    //   21: iload_1
    //   22: aload_3
    //   23: invokeinterface 143 1 0
    //   28: if_icmpge +241 -> 269
    //   31: new 145	java/util/HashMap
    //   34: dup
    //   35: invokespecial 146	java/util/HashMap:<init>	()V
    //   38: astore 4
    //   40: aload_3
    //   41: iload_1
    //   42: invokeinterface 149 2 0
    //   47: checkcast 151	android/content/pm/PackageInfo
    //   50: astore 5
    //   52: aload 5
    //   54: getfield 155	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   57: getfield 161	android/content/pm/ApplicationInfo:flags	I
    //   60: iconst_1
    //   61: iand
    //   62: ifle +176 -> 238
    //   65: aload 4
    //   67: ldc 57
    //   69: ldc -93
    //   71: invokeinterface 167 3 0
    //   76: pop
    //   77: aload 4
    //   79: ldc 69
    //   81: aload_0
    //   82: aload 5
    //   84: getfield 155	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   87: invokevirtual 171	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   90: invokeinterface 174 1 0
    //   95: invokeinterface 167 3 0
    //   100: pop
    //   101: aload 4
    //   103: ldc 80
    //   105: aload 5
    //   107: getfield 155	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   110: getfield 178	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   113: invokeinterface 167 3 0
    //   118: pop
    //   119: getstatic 183	android/os/Build$VERSION:SDK_INT	I
    //   122: bipush 9
    //   124: if_icmplt +147 -> 271
    //   127: aload 4
    //   129: ldc 82
    //   131: aload 5
    //   133: getfield 186	android/content/pm/PackageInfo:firstInstallTime	J
    //   136: ldc2_w 187
    //   139: ldiv
    //   140: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   143: invokeinterface 167 3 0
    //   148: pop
    //   149: aload 4
    //   151: ldc -61
    //   153: aload 5
    //   155: getfield 197	android/content/pm/PackageInfo:lastUpdateTime	J
    //   158: ldc2_w 187
    //   161: ldiv
    //   162: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   165: invokeinterface 167 3 0
    //   170: pop
    //   171: aload 5
    //   173: getfield 155	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   176: getfield 200	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   179: astore 5
    //   181: aload 4
    //   183: ldc -54
    //   185: aload 5
    //   187: invokeinterface 167 3 0
    //   192: pop
    //   193: aload 4
    //   195: ldc 84
    //   197: new 204	java/io/File
    //   200: dup
    //   201: aload 5
    //   203: invokespecial 207	java/io/File:<init>	(Ljava/lang/String;)V
    //   206: invokevirtual 210	java/io/File:lastModified	()J
    //   209: ldc2_w 187
    //   212: ldiv
    //   213: invokestatic 193	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   216: invokeinterface 167 3 0
    //   221: pop
    //   222: aload_2
    //   223: aload 4
    //   225: invokeinterface 213 2 0
    //   230: pop
    //   231: iload_1
    //   232: iconst_1
    //   233: iadd
    //   234: istore_1
    //   235: goto -214 -> 21
    //   238: aload 4
    //   240: ldc 57
    //   242: ldc -41
    //   244: invokeinterface 167 3 0
    //   249: pop
    //   250: goto -173 -> 77
    //   253: astore_0
    //   254: ldc 2
    //   256: ldc -39
    //   258: iconst_1
    //   259: anewarray 4	java/lang/Object
    //   262: dup
    //   263: iconst_0
    //   264: aload_0
    //   265: aastore
    //   266: invokestatic 220	com/yy/hiidostatis/inner/util/y/b:v	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   269: aload_2
    //   270: areturn
    //   271: aload 4
    //   273: ldc 82
    //   275: iconst_0
    //   276: invokestatic 106	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   279: invokeinterface 167 3 0
    //   284: pop
    //   285: aload 4
    //   287: ldc -61
    //   289: iconst_0
    //   290: invokestatic 106	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   293: invokeinterface 167 3 0
    //   298: pop
    //   299: goto -128 -> 171
    //   302: astore 5
    //   304: aload 4
    //   306: ldc 84
    //   308: iconst_0
    //   309: invokestatic 106	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   312: invokeinterface 167 3 0
    //   317: pop
    //   318: ldc 2
    //   320: ldc -34
    //   322: iconst_1
    //   323: anewarray 4	java/lang/Object
    //   326: dup
    //   327: iconst_0
    //   328: aload 5
    //   330: aastore
    //   331: invokestatic 220	com/yy/hiidostatis/inner/util/y/b:v	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   334: goto -112 -> 222
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	337	0	paramContext	Context
    //   20	215	1	i	int
    //   7	263	2	localArrayList	ArrayList
    //   18	23	3	localList	List
    //   38	267	4	localHashMap	java.util.HashMap
    //   50	152	5	localObject	Object
    //   302	27	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   8	19	253	java/lang/Throwable
    //   21	77	253	java/lang/Throwable
    //   77	171	253	java/lang/Throwable
    //   222	231	253	java/lang/Throwable
    //   238	250	253	java/lang/Throwable
    //   271	299	253	java/lang/Throwable
    //   304	334	253	java/lang/Throwable
    //   171	222	302	java/lang/Throwable
  }
  
  private List<Map<String, Object>> z(List<Map<String, Object>> paramList, String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!paramJSONObject.has("appListConfig")) {
          break label301;
        }
        JSONArray localJSONArray = paramJSONObject.getJSONArray("appListConfig");
        if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
          break label303;
        }
        paramJSONObject = new ArrayList();
        try
        {
          Iterator localIterator = paramList.iterator();
          if (!localIterator.hasNext()) {
            continue;
          }
          Map localMap = (Map)localIterator.next();
          if (!paramString.equals(localMap.get("type"))) {
            continue;
          }
          String str1 = localMap.get("appid");
          i = 0;
          if (i >= localJSONArray.length()) {
            continue;
          }
          paramList = localJSONArray.getJSONObject(i);
          String str2 = paramList.getString("scheme");
          if (!paramList.has("isExact")) {
            break label314;
          }
          paramList = paramList.get("isExact");
          if ("0".equals(paramList))
          {
            if (str1.contains(str2))
            {
              paramJSONObject.add(localMap);
              b.z("getSpecial isExact(0).pkgName:%s,tmpPkgName:%s", new Object[] { str1, str2 });
            }
          }
          else if (str1.equals(str2))
          {
            paramJSONObject.add(localMap);
            b.z("getSpecial isExact(1).pkgName:%s,tmpPkgName:%s", new Object[] { str1, str2 });
          }
        }
        catch (Throwable paramString)
        {
          paramList = paramJSONObject;
        }
      }
      catch (Throwable paramString)
      {
        paramList = null;
        continue;
      }
      b.v(this, "getSpecial exception = %s", new Object[] { paramString });
      return paramList;
      return paramJSONObject;
      label301:
      return null;
      label303:
      return null;
      i += 1;
      continue;
      label314:
      paramList = "1";
    }
  }
  
  private void z(List<Map<String, Object>> paramList, long paramLong, String paramString)
  {
    Object localObject = new StringBuffer();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Map localMap = (Map)localIterator.next();
      if (paramString.equals(localMap.get("type"))) {
        ((StringBuffer)localObject).append(localMap.get("appname")).append("|");
      }
    }
    if (((StringBuffer)localObject).length() > 0) {
      ((StringBuffer)localObject).setLength(((StringBuffer)localObject).length() - 1);
    }
    b.y(y.class, "type=%s,applist length=%d,applist bypes length=%d", new Object[] { paramString, Integer.valueOf(((StringBuffer)localObject).toString().length()), Integer.valueOf(((StringBuffer)localObject).toString().getBytes().length) });
    b.y(y.class, "applist=%s", new Object[] { ((StringBuffer)localObject).toString() });
    localObject = ((StringBuffer)localObject).toString();
    paramList = z(paramList, paramString);
    this.z.z(paramLong, paramString, (String)localObject, paramList);
  }
  
  public final void z(Context paramContext, long paramLong)
  {
    paramContext = new x(this, paramContext, paramLong);
    e.z().z(paramContext, 10000L);
  }
}
