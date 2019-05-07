package com.bandwidthx.library;

import android.os.Build;
import android.os.Build.VERSION;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class z
  implements bu.f
{
  public z() {}
  
  /* Error */
  private JSONArray a(ai paramAi)
  {
    // Byte code:
    //   0: new 17	org/json/JSONArray
    //   3: dup
    //   4: invokespecial 18	org/json/JSONArray:<init>	()V
    //   7: astore 4
    //   9: invokestatic 24	com/bandwidthx/library/ai:d	()Landroid/content/Context;
    //   12: invokevirtual 30	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   15: astore 5
    //   17: aload 5
    //   19: iconst_0
    //   20: invokevirtual 36	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   23: invokeinterface 42 1 0
    //   28: astore 6
    //   30: aload 6
    //   32: invokeinterface 48 1 0
    //   37: ifeq +136 -> 173
    //   40: aload 6
    //   42: invokeinterface 52 1 0
    //   47: checkcast 54	android/content/pm/ApplicationInfo
    //   50: astore 7
    //   52: aload_1
    //   53: invokevirtual 58	com/bandwidthx/library/ai:g	()Lcom/bandwidthx/library/c;
    //   56: pop
    //   57: aload 5
    //   59: aload 7
    //   61: getfield 62	android/content/pm/ApplicationInfo:uid	I
    //   64: invokestatic 68	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   67: invokestatic 74	com/bandwidthx/library/c:b	(Ljava/lang/Integer;)Ljava/lang/String;
    //   70: iconst_2
    //   71: invokevirtual 78	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   74: astore 7
    //   76: aload 7
    //   78: ifnull -48 -> 30
    //   81: aload 7
    //   83: getfield 84	android/content/pm/PackageInfo:receivers	[Landroid/content/pm/ActivityInfo;
    //   86: astore 8
    //   88: aload 8
    //   90: ifnull -60 -> 30
    //   93: aload 8
    //   95: arraylength
    //   96: istore_3
    //   97: iconst_0
    //   98: istore_2
    //   99: iload_2
    //   100: iload_3
    //   101: if_icmpge -71 -> 30
    //   104: aload 8
    //   106: iload_2
    //   107: aaload
    //   108: getfield 90	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   111: ldc 92
    //   113: invokevirtual 98	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   116: ifeq +50 -> 166
    //   119: aload 4
    //   121: new 100	java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   128: aload 7
    //   130: getfield 105	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   133: aload 5
    //   135: invokevirtual 109	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   138: invokeinterface 115 1 0
    //   143: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: ldc 121
    //   148: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: getfield 124	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   156: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokevirtual 129	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   165: pop
    //   166: iload_2
    //   167: iconst_1
    //   168: iadd
    //   169: istore_2
    //   170: goto -71 -> 99
    //   173: aload 4
    //   175: areturn
    //   176: astore_1
    //   177: aconst_null
    //   178: areturn
    //   179: astore_1
    //   180: aload 4
    //   182: areturn
    //   183: astore 7
    //   185: goto -155 -> 30
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	this	z
    //   0	188	1	paramAi	ai
    //   98	72	2	i	int
    //   96	6	3	j	int
    //   7	174	4	localJSONArray	JSONArray
    //   15	119	5	localPackageManager	android.content.pm.PackageManager
    //   28	13	6	localIterator	java.util.Iterator
    //   50	102	7	localObject	Object
    //   183	1	7	localException	Exception
    //   86	19	8	arrayOfActivityInfo	android.content.pm.ActivityInfo[]
    // Exception table:
    //   from	to	target	type
    //   0	9	176	java/lang/Exception
    //   9	30	179	java/lang/Exception
    //   30	52	179	java/lang/Exception
    //   52	76	183	java/lang/Exception
    //   81	88	183	java/lang/Exception
    //   93	97	183	java/lang/Exception
    //   104	166	183	java/lang/Exception
  }
  
  public void a(ai paramAi, JSONObject paramJSONObject)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("man", Build.MANUFACTURER);
    localJSONObject.put("mod", Build.MODEL);
    localJSONObject.put("os", "Android");
    localJSONObject.put("ver", Build.VERSION.RELEASE);
    localJSONObject.put("app", (c.e() + " " + c.g() + "." + c.f() + " " + c.h()).trim());
    JSONArray localJSONArray = a(paramAi);
    if ((localJSONArray != null) && (localJSONArray.length() > 1)) {
      localJSONObject.put("apps", localJSONArray);
    }
    paramAi.g();
    localJSONObject.put("bld", c.i());
    paramAi.g();
    localJSONObject.put("cod", c.f());
    paramAi.g();
    localJSONObject.put("lib", c.d());
    paramAi.g();
    localJSONObject.put("nam", c.c());
    paramAi.g();
    localJSONObject.put("lcl", c.n());
    try
    {
      localJSONObject.put("c2d", paramAi.u().a());
      paramJSONObject.put("dev", localJSONObject);
      return;
    }
    catch (Exception paramAi)
    {
      for (;;) {}
    }
  }
}
