package com.riffsy.android.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.support.a.y;
import android.support.a.z;
import android.support.v4.l.a;
import android.support.v4.view.a.h;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import com.riffsy.android.sdk.R.string;
import com.riffsy.android.sdk.contants.Messengers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PackageManagerUtils
{
  public static final String CLASS_NAME_TEXTVIEW = TextView.class.getName();
  public static final String CLASS_SIMPLE_NAME_STRING = String.class.getSimpleName();
  public static final String CLASS_SIMPLE_NAME_STRING_ARRAY = [Ljava.lang.String.class.getSimpleName();
  
  public PackageManagerUtils() {}
  
  public static boolean bypassActionResolver(Context paramContext, h paramH)
  {
    Object localObject = AbstractNodeUtils.getRootNode(paramH);
    paramH = AbstractNodeUtils.getAllNodes((h)localObject, TextView.class);
    localObject = AbstractNodeUtils.getAllNodes((h)localObject, Button.class);
    if ((paramH.size() > 1) && (((List)localObject).size() == 2))
    {
      paramH = AbstractNodeUtils.getFirstClickableParent((h)paramH.get(1));
      paramContext = findJustOnceButton(paramContext, (List)localObject);
      if (Build.VERSION.SDK_INT < 18)
      {
        if (paramH.e(16)) {
          return paramContext.e(16);
        }
      }
      else {
        return paramContext.e(16);
      }
    }
    return false;
  }
  
  @y
  private static h findJustOnceButton(@y Context paramContext, @y List<h> paramList)
  {
    paramContext = AbstractStringUtils.getString(paramContext, "android", "activity_resolver_use_once");
    Iterator localIterator = paramList.iterator();
    label121:
    label124:
    while (localIterator.hasNext())
    {
      h localH = (h)localIterator.next();
      int i;
      if ((!TextUtils.isEmpty(localH.v())) && (!TextUtils.isEmpty(paramContext)) && (paramContext.equals(localH.v().toString())))
      {
        i = 1;
        if ((TextUtils.isEmpty(localH.y())) || (!localH.y().toLowerCase().contains("once"))) {
          break label121;
        }
      }
      for (int j = 1;; j = 0)
      {
        if ((i == 0) && (j == 0)) {
          break label124;
        }
        return localH;
        i = 0;
        break;
      }
    }
    return (h)paramList.get(1);
  }
  
  @y
  public static String getApplicationLabel(@y Context paramContext, @y String paramString)
  {
    a localA = Messengers.ALL_KNOWN_MESSENGERS;
    if ((paramContext == null) || (TextUtils.isEmpty(paramString)) || ("".equals(paramString))) {
      return paramContext.getString(((Integer)localA.get("")).intValue());
    }
    Iterator localIterator = localA.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (paramString.equals(str)) {
        return paramContext.getString(((Integer)localA.get(str)).intValue());
      }
    }
    return getInstalledApplicationLabel(paramContext, paramString);
  }
  
  public static List<String> getGoogleInputMethodsInstalled(@y Context paramContext)
  {
    return getInstalledPackageContains(paramContext, new String[] { "com.google.android.inputmethod", "com.android.inputmethod" });
  }
  
  @y
  public static String getInstalledApplicationLabel(@y Context paramContext, @y String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return "";
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = null;
    try
    {
      paramString = localPackageManager.getApplicationInfo(paramString, 128);
      if (paramString != null) {
        return localPackageManager.getApplicationLabel(paramString).toString();
      }
      return paramContext.getString(R.string.sdk_readable_app_name_none);
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;)
      {
        paramString = localObject;
      }
    }
  }
  
  public static List<String> getInstalledPackageContains(@y Context paramContext, @y String... paramVarArgs)
  {
    if ((paramContext == null) || (paramVarArgs.length == 0)) {
      return Collections.emptyList();
    }
    paramContext = new ArrayList();
    Iterator localIterator = getInstalledPackages().iterator();
    label89:
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      int j = paramVarArgs.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label89;
        }
        if (str.contains(paramVarArgs[i]))
        {
          paramContext.add(str);
          break;
        }
        i += 1;
      }
    }
    return paramContext;
  }
  
  /* Error */
  private static List<String> getInstalledPackages()
  {
    // Byte code:
    //   0: new 211	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 212	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: aconst_null
    //   9: astore_1
    //   10: invokestatic 227	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: ldc -27
    //   15: invokevirtual 233	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   18: astore_3
    //   19: new 235	java/io/BufferedReader
    //   22: dup
    //   23: new 237	java/io/InputStreamReader
    //   26: dup
    //   27: aload_3
    //   28: invokevirtual 243	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   31: invokespecial 246	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   34: invokespecial 249	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   37: astore_0
    //   38: aload_0
    //   39: invokevirtual 252	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore_1
    //   43: aload_1
    //   44: ifnull +36 -> 80
    //   47: aload_2
    //   48: aload_1
    //   49: aload_1
    //   50: bipush 58
    //   52: invokevirtual 256	java/lang/String:indexOf	(I)I
    //   55: iconst_1
    //   56: iadd
    //   57: invokevirtual 259	java/lang/String:substring	(I)Ljava/lang/String;
    //   60: invokeinterface 260 2 0
    //   65: pop
    //   66: goto -28 -> 38
    //   69: astore_1
    //   70: aload_0
    //   71: ifnull +7 -> 78
    //   74: aload_0
    //   75: invokevirtual 263	java/io/BufferedReader:close	()V
    //   78: aload_2
    //   79: areturn
    //   80: aload_3
    //   81: invokevirtual 266	java/lang/Process:waitFor	()I
    //   84: pop
    //   85: aload_0
    //   86: ifnull -8 -> 78
    //   89: aload_0
    //   90: invokevirtual 263	java/io/BufferedReader:close	()V
    //   93: aload_2
    //   94: areturn
    //   95: astore_0
    //   96: aload_2
    //   97: areturn
    //   98: astore_0
    //   99: aload_1
    //   100: ifnull +7 -> 107
    //   103: aload_1
    //   104: invokevirtual 263	java/io/BufferedReader:close	()V
    //   107: aload_0
    //   108: athrow
    //   109: astore_0
    //   110: aload_2
    //   111: areturn
    //   112: astore_1
    //   113: goto -6 -> 107
    //   116: astore_2
    //   117: aload_0
    //   118: astore_1
    //   119: aload_2
    //   120: astore_0
    //   121: goto -22 -> 99
    //   124: astore_0
    //   125: aconst_null
    //   126: astore_0
    //   127: goto -57 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   37	53	0	localBufferedReader	java.io.BufferedReader
    //   95	1	0	localException1	Exception
    //   98	10	0	localObject1	Object
    //   109	9	0	localException2	Exception
    //   120	1	0	localObject2	Object
    //   124	1	0	localException3	Exception
    //   126	1	0	localObject3	Object
    //   9	41	1	str	String
    //   69	35	1	localException4	Exception
    //   112	1	1	localException5	Exception
    //   118	1	1	localObject4	Object
    //   7	104	2	localArrayList	ArrayList
    //   116	4	2	localObject5	Object
    //   18	63	3	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   38	43	69	java/lang/Exception
    //   47	66	69	java/lang/Exception
    //   80	85	69	java/lang/Exception
    //   89	93	95	java/lang/Exception
    //   10	38	98	finally
    //   74	78	109	java/lang/Exception
    //   103	107	112	java/lang/Exception
    //   38	43	116	finally
    //   47	66	116	finally
    //   80	85	116	finally
    //   10	38	124	java/lang/Exception
  }
  
  public static int getVersionCode(@z Context paramContext, @z String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {}
    for (;;)
    {
      return -1;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 128);
        if (paramContext != null) {
          return paramContext.versionCode;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
    }
    return -1;
  }
  
  public static boolean hasActionResolver(@y Context paramContext, @y Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    if (paramContext.hasNext())
    {
      paramIntent = ((ResolveInfo)paramContext.next()).activityInfo.taskAffinity;
      int i = -1;
      switch (paramIntent.hashCode())
      {
      }
      for (;;)
      {
        switch (i)
        {
        default: 
          break;
        case 0: 
          return true;
          if (paramIntent.equals("com.tencent.mm"))
          {
            i = 0;
            continue;
            if (paramIntent.equals("jp.naver.line.android")) {
              i = 1;
            }
          }
          break;
        }
      }
      return true;
    }
    return false;
  }
  
  public static boolean isAllPackageInstalled(@y Context paramContext, @y String... paramVarArgs)
  {
    boolean bool1 = false;
    boolean bool2 = bool1;
    if (paramContext != null)
    {
      if (paramVarArgs.length == 0) {
        bool2 = bool1;
      }
    }
    else {
      return bool2;
    }
    paramContext = getInstalledPackages();
    int j = paramVarArgs.length;
    bool1 = true;
    int i = 0;
    for (;;)
    {
      bool2 = bool1;
      if (i >= j) {
        break;
      }
      bool2 = paramContext.contains(paramVarArgs[i]);
      i += 1;
      bool1 = bool2 & bool1;
    }
  }
  
  public static boolean isAnyPackageInstalled(@y Context paramContext, @y String... paramVarArgs)
  {
    if ((paramContext == null) || (paramVarArgs.length == 0)) {}
    for (;;)
    {
      return false;
      paramContext = getInstalledPackages();
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        if (paramContext.contains(paramVarArgs[i])) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isClickThroughSupported(@y String paramString, @y String[] paramArrayOfString)
  {
    if ((!TextUtils.isEmpty(paramString)) || (paramArrayOfString == null)) {}
    for (;;)
    {
      return false;
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfString[i].equals(paramString)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isGoogleInputMethodInstalled(@y Context paramContext)
  {
    return !getInstalledPackageContains(paramContext, new String[] { "com.google.android.inputmethod", "com.android.inputmethod" }).isEmpty();
  }
  
  public static boolean isKeyboardEnabled(@y Context paramContext, @y String paramString)
  {
    return isKeyboardEnabled((InputMethodManager)paramContext.getSystemService("input_method"), paramString);
  }
  
  public static boolean isKeyboardEnabled(@y InputMethodManager paramInputMethodManager, @y String paramString)
  {
    paramInputMethodManager = paramInputMethodManager.getEnabledInputMethodList().iterator();
    while (paramInputMethodManager.hasNext()) {
      if (paramString.equals(((InputMethodInfo)paramInputMethodManager.next()).getPackageName())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isPackageEquals(@y String paramString1, @y String paramString2)
  {
    return (!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2)) && (paramString1.equals(paramString2));
  }
  
  public static boolean isPackageInstalled(@y Context paramContext, @y String paramString)
  {
    return isAllPackageInstalled(paramContext, new String[] { paramString });
  }
}
