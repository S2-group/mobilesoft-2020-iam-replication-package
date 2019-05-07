package com.hld.anzenbokusu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.hld.anzenbokusu.App;
import com.hld.anzenbokusu.db.entity.LockApp;
import com.hld.anzenbokusu.mvp.entity.AppInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import mv;
import ye;

public class O0000o00
{
  private static Boolean O000000o(PackageManager paramPackageManager, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setPackage(paramString);
    boolean bool2 = false;
    paramPackageManager = paramPackageManager.queryIntentActivities(localIntent, 0);
    boolean bool1 = bool2;
    if (paramPackageManager != null)
    {
      bool1 = bool2;
      if (paramPackageManager.size() > 0) {
        bool1 = true;
      }
    }
    return Boolean.valueOf(bool1);
  }
  
  public static String O000000o(String paramString)
  {
    try
    {
      paramString = O00oOooO.O00000Oo(paramString).substring(0, 1).toUpperCase(Locale.CHINESE);
      if (paramString.matches("[A-Z]")) {
        return paramString;
      }
      return "#";
    }
    catch (Exception paramString)
    {
      ye.O00000o(paramString.toString());
    }
    return "#";
  }
  
  /* Error */
  public static List<AppInfo> O000000o()
  {
    // Byte code:
    //   0: new 80	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 81	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 86	com/hld/anzenbokusu/App:O000000o	()Landroid/content/Context;
    //   11: invokevirtual 92	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: astore 4
    //   16: aload 4
    //   18: invokestatic 95	com/hld/anzenbokusu/utils/O0000o00:O000000o	(Landroid/content/pm/PackageManager;)Ljava/util/List;
    //   21: astore 6
    //   23: new 80	java/util/ArrayList
    //   26: dup
    //   27: invokespecial 81	java/util/ArrayList:<init>	()V
    //   30: astore 5
    //   32: invokestatic 86	com/hld/anzenbokusu/App:O000000o	()Landroid/content/Context;
    //   35: invokestatic 100	com/hld/anzenbokusu/utils/O00000o:O000000o	(Landroid/content/Context;)Z
    //   38: istore_1
    //   39: invokestatic 105	mv:O00000o	()Lmv;
    //   42: invokevirtual 108	mv:O0000ooO	()Ljava/util/List;
    //   45: invokeinterface 112 1 0
    //   50: astore_2
    //   51: iload_1
    //   52: istore_0
    //   53: aload_2
    //   54: invokeinterface 118 1 0
    //   59: ifeq +42 -> 101
    //   62: aload 5
    //   64: aload_2
    //   65: invokeinterface 122 1 0
    //   70: checkcast 124	com/hld/anzenbokusu/db/entity/HideApp
    //   73: invokevirtual 127	com/hld/anzenbokusu/db/entity/HideApp:getPackageName	()Ljava/lang/String;
    //   76: invokeinterface 131 2 0
    //   81: pop
    //   82: goto -31 -> 51
    //   85: astore_2
    //   86: iload_1
    //   87: istore_0
    //   88: goto +6 -> 94
    //   91: astore_2
    //   92: iconst_0
    //   93: istore_0
    //   94: aload_2
    //   95: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   98: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   101: aload 6
    //   103: invokeinterface 112 1 0
    //   108: astore_2
    //   109: aload_2
    //   110: invokeinterface 118 1 0
    //   115: ifeq +227 -> 342
    //   118: aload_2
    //   119: invokeinterface 122 1 0
    //   124: checkcast 133	android/content/pm/PackageInfo
    //   127: astore 6
    //   129: aload 6
    //   131: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   134: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   137: invokestatic 149	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   140: ifeq +6 -> 146
    //   143: goto -34 -> 109
    //   146: invokestatic 86	com/hld/anzenbokusu/App:O000000o	()Landroid/content/Context;
    //   149: invokevirtual 150	android/content/Context:getPackageName	()Ljava/lang/String;
    //   152: aload 6
    //   154: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   157: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   160: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   163: ifeq +6 -> 169
    //   166: goto -57 -> 109
    //   169: iload_0
    //   170: ifeq +38 -> 208
    //   173: aload 6
    //   175: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   178: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   181: invokestatic 155	com/hld/anzenbokusu/utils/O00000o:O000000o	(Ljava/lang/String;)Z
    //   184: ifeq -75 -> 109
    //   187: aload 5
    //   189: aload 6
    //   191: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   194: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   197: invokeinterface 158 2 0
    //   202: ifeq +27 -> 229
    //   205: goto -96 -> 109
    //   208: aload 5
    //   210: aload 6
    //   212: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   215: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   218: invokeinterface 158 2 0
    //   223: ifeq +6 -> 229
    //   226: goto -117 -> 109
    //   229: aload 4
    //   231: aload 6
    //   233: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   236: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   239: invokestatic 160	com/hld/anzenbokusu/utils/O0000o00:O000000o	(Landroid/content/pm/PackageManager;Ljava/lang/String;)Ljava/lang/Boolean;
    //   242: invokevirtual 163	java/lang/Boolean:booleanValue	()Z
    //   245: istore_1
    //   246: iload_1
    //   247: ifne -138 -> 109
    //   250: new 165	com/hld/anzenbokusu/mvp/entity/AppInfo
    //   253: dup
    //   254: invokespecial 166	com/hld/anzenbokusu/mvp/entity/AppInfo:<init>	()V
    //   257: astore 7
    //   259: aload 7
    //   261: aload 6
    //   263: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   266: aload 4
    //   268: invokevirtual 170	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   271: invokeinterface 173 1 0
    //   276: invokevirtual 177	com/hld/anzenbokusu/mvp/entity/AppInfo:setAppName	(Ljava/lang/String;)V
    //   279: aload 7
    //   281: aload 6
    //   283: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   286: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   289: invokevirtual 180	com/hld/anzenbokusu/mvp/entity/AppInfo:setPackageName	(Ljava/lang/String;)V
    //   292: aload 7
    //   294: aload 6
    //   296: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   299: invokevirtual 184	com/hld/anzenbokusu/mvp/entity/AppInfo:setApplicationInfo	(Landroid/content/pm/ApplicationInfo;)V
    //   302: aload 7
    //   304: invokestatic 187	com/hld/anzenbokusu/utils/O0000o00:O000000o	(Lcom/hld/anzenbokusu/mvp/entity/AppInfo;)V
    //   307: aload_3
    //   308: aload 7
    //   310: invokeinterface 131 2 0
    //   315: pop
    //   316: goto -207 -> 109
    //   319: astore 6
    //   321: aload 6
    //   323: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   326: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   329: goto -220 -> 109
    //   332: astore 6
    //   334: aload 6
    //   336: invokevirtual 190	java/lang/Exception:printStackTrace	()V
    //   339: goto -230 -> 109
    //   342: aload_3
    //   343: new 192	com/hld/anzenbokusu/utils/O0000Oo
    //   346: dup
    //   347: invokespecial 193	com/hld/anzenbokusu/utils/O0000Oo:<init>	()V
    //   350: invokestatic 199	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   353: aload_3
    //   354: areturn
    //   355: astore_2
    //   356: aload_2
    //   357: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   360: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   363: aload_3
    //   364: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   52	118	0	bool1	boolean
    //   38	209	1	bool2	boolean
    //   50	15	2	localIterator1	java.util.Iterator
    //   85	1	2	localException1	Exception
    //   91	4	2	localException2	Exception
    //   108	11	2	localIterator2	java.util.Iterator
    //   355	2	2	localException3	Exception
    //   7	357	3	localArrayList1	ArrayList
    //   14	253	4	localPackageManager	PackageManager
    //   30	179	5	localArrayList2	ArrayList
    //   21	274	6	localObject	Object
    //   319	3	6	localException4	Exception
    //   332	3	6	localException5	Exception
    //   257	52	7	localAppInfo	AppInfo
    // Exception table:
    //   from	to	target	type
    //   39	51	85	java/lang/Exception
    //   53	82	85	java/lang/Exception
    //   32	39	91	java/lang/Exception
    //   250	316	319	java/lang/Exception
    //   129	143	332	java/lang/Exception
    //   146	166	332	java/lang/Exception
    //   173	205	332	java/lang/Exception
    //   208	226	332	java/lang/Exception
    //   229	246	332	java/lang/Exception
    //   321	329	332	java/lang/Exception
    //   342	353	355	java/lang/Exception
  }
  
  @SuppressLint({"WrongConstant"})
  private static List<PackageInfo> O000000o(PackageManager paramPackageManager)
  {
    try
    {
      localList1 = paramPackageManager.getInstalledPackages(8192);
    }
    catch (Exception localException)
    {
      List localList1;
      List localList2;
      for (;;) {}
    }
    localList1 = paramPackageManager.getInstalledPackages(0);
    if (localList1 != null)
    {
      localList2 = localList1;
      if (localList1.size() != 0) {}
    }
    else
    {
      localList2 = paramPackageManager.getInstalledPackages(5);
    }
    return localList2;
  }
  
  /* Error */
  public static List<AppInfo> O000000o(boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: new 80	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 81	java/util/ArrayList:<init>	()V
    //   7: astore 7
    //   9: invokestatic 86	com/hld/anzenbokusu/App:O000000o	()Landroid/content/Context;
    //   12: invokevirtual 92	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   15: astore 8
    //   17: aload 8
    //   19: invokestatic 95	com/hld/anzenbokusu/utils/O0000o00:O000000o	(Landroid/content/pm/PackageManager;)Ljava/util/List;
    //   22: astore 10
    //   24: invokestatic 214	com/hld/anzenbokusu/utils/O0000o00:O00000oO	()Ljava/util/List;
    //   27: astore 5
    //   29: goto +16 -> 45
    //   32: astore 5
    //   34: aload 5
    //   36: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   39: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   42: aconst_null
    //   43: astore 5
    //   45: new 80	java/util/ArrayList
    //   48: dup
    //   49: invokespecial 81	java/util/ArrayList:<init>	()V
    //   52: astore 9
    //   54: iload_1
    //   55: ifeq +84 -> 139
    //   58: invokestatic 86	com/hld/anzenbokusu/App:O000000o	()Landroid/content/Context;
    //   61: invokestatic 100	com/hld/anzenbokusu/utils/O00000o:O000000o	(Landroid/content/Context;)Z
    //   64: istore 4
    //   66: invokestatic 105	mv:O00000o	()Lmv;
    //   69: invokevirtual 108	mv:O0000ooO	()Ljava/util/List;
    //   72: invokeinterface 112 1 0
    //   77: astore 6
    //   79: iload 4
    //   81: istore_3
    //   82: aload 6
    //   84: invokeinterface 118 1 0
    //   89: ifeq +52 -> 141
    //   92: aload 9
    //   94: aload 6
    //   96: invokeinterface 122 1 0
    //   101: checkcast 124	com/hld/anzenbokusu/db/entity/HideApp
    //   104: invokevirtual 127	com/hld/anzenbokusu/db/entity/HideApp:getPackageName	()Ljava/lang/String;
    //   107: invokeinterface 131 2 0
    //   112: pop
    //   113: goto -34 -> 79
    //   116: astore 6
    //   118: iload 4
    //   120: istore_3
    //   121: goto +7 -> 128
    //   124: astore 6
    //   126: iconst_0
    //   127: istore_3
    //   128: aload 6
    //   130: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   133: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   136: goto +5 -> 141
    //   139: iconst_0
    //   140: istore_3
    //   141: aload 10
    //   143: invokeinterface 112 1 0
    //   148: astore 6
    //   150: aload 6
    //   152: invokeinterface 118 1 0
    //   157: ifeq +254 -> 411
    //   160: aload 6
    //   162: invokeinterface 122 1 0
    //   167: checkcast 133	android/content/pm/PackageInfo
    //   170: astore 10
    //   172: aload 10
    //   174: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   177: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   180: invokestatic 149	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   183: ifeq +6 -> 189
    //   186: goto -36 -> 150
    //   189: invokestatic 86	com/hld/anzenbokusu/App:O000000o	()Landroid/content/Context;
    //   192: invokevirtual 150	android/content/Context:getPackageName	()Ljava/lang/String;
    //   195: aload 10
    //   197: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   200: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   203: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   206: ifeq +6 -> 212
    //   209: goto -59 -> 150
    //   212: iload_1
    //   213: ifeq +45 -> 258
    //   216: iload_3
    //   217: ifeq +20 -> 237
    //   220: aload 10
    //   222: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   225: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   228: invokestatic 155	com/hld/anzenbokusu/utils/O00000o:O000000o	(Ljava/lang/String;)Z
    //   231: ifeq +27 -> 258
    //   234: goto -84 -> 150
    //   237: aload 9
    //   239: aload 10
    //   241: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   244: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   247: invokeinterface 158 2 0
    //   252: ifeq +6 -> 258
    //   255: goto -105 -> 150
    //   258: iload_0
    //   259: ifeq +16 -> 275
    //   262: aload 10
    //   264: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   267: getfield 218	android/content/pm/ApplicationInfo:flags	I
    //   270: iconst_1
    //   271: iand
    //   272: ifne +22 -> 294
    //   275: iload_0
    //   276: ifne -126 -> 150
    //   279: aload 10
    //   281: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   284: getfield 218	android/content/pm/ApplicationInfo:flags	I
    //   287: istore_2
    //   288: iload_2
    //   289: iconst_1
    //   290: iand
    //   291: ifne -141 -> 150
    //   294: new 165	com/hld/anzenbokusu/mvp/entity/AppInfo
    //   297: dup
    //   298: invokespecial 166	com/hld/anzenbokusu/mvp/entity/AppInfo:<init>	()V
    //   301: astore 11
    //   303: aload 11
    //   305: aload 10
    //   307: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   310: aload 8
    //   312: invokevirtual 170	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   315: invokeinterface 173 1 0
    //   320: invokevirtual 177	com/hld/anzenbokusu/mvp/entity/AppInfo:setAppName	(Ljava/lang/String;)V
    //   323: aload 11
    //   325: aload 10
    //   327: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   330: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   333: invokevirtual 180	com/hld/anzenbokusu/mvp/entity/AppInfo:setPackageName	(Ljava/lang/String;)V
    //   336: aload 11
    //   338: aload 10
    //   340: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   343: invokevirtual 184	com/hld/anzenbokusu/mvp/entity/AppInfo:setApplicationInfo	(Landroid/content/pm/ApplicationInfo;)V
    //   346: iload_1
    //   347: ifne +23 -> 370
    //   350: aload 11
    //   352: aload 5
    //   354: aload 10
    //   356: getfield 137	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   359: getfield 143	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   362: invokeinterface 158 2 0
    //   367: invokevirtual 222	com/hld/anzenbokusu/mvp/entity/AppInfo:setChecked	(Z)V
    //   370: aload 11
    //   372: invokestatic 187	com/hld/anzenbokusu/utils/O0000o00:O000000o	(Lcom/hld/anzenbokusu/mvp/entity/AppInfo;)V
    //   375: aload 7
    //   377: aload 11
    //   379: invokeinterface 131 2 0
    //   384: pop
    //   385: goto -235 -> 150
    //   388: astore 10
    //   390: aload 10
    //   392: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   395: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   398: goto -248 -> 150
    //   401: astore 10
    //   403: aload 10
    //   405: invokevirtual 190	java/lang/Exception:printStackTrace	()V
    //   408: goto -258 -> 150
    //   411: aload 7
    //   413: new 192	com/hld/anzenbokusu/utils/O0000Oo
    //   416: dup
    //   417: invokespecial 193	com/hld/anzenbokusu/utils/O0000Oo:<init>	()V
    //   420: invokestatic 199	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   423: iload_1
    //   424: ifne +28 -> 452
    //   427: aload 7
    //   429: new 224	com/hld/anzenbokusu/utils/O0000Oo0
    //   432: dup
    //   433: invokespecial 225	com/hld/anzenbokusu/utils/O0000Oo0:<init>	()V
    //   436: invokestatic 199	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   439: aload 7
    //   441: areturn
    //   442: astore 5
    //   444: aload 5
    //   446: invokevirtual 71	java/lang/Exception:toString	()Ljava/lang/String;
    //   449: invokestatic 77	ye:O00000o	(Ljava/lang/Object;)V
    //   452: aload 7
    //   454: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	455	0	paramBoolean1	boolean
    //   0	455	1	paramBoolean2	boolean
    //   287	4	2	i	int
    //   81	136	3	bool1	boolean
    //   64	55	4	bool2	boolean
    //   27	1	5	localList	List
    //   32	3	5	localException1	Exception
    //   43	310	5	localObject1	Object
    //   442	3	5	localException2	Exception
    //   77	18	6	localIterator1	java.util.Iterator
    //   116	1	6	localException3	Exception
    //   124	5	6	localException4	Exception
    //   148	13	6	localIterator2	java.util.Iterator
    //   7	446	7	localArrayList1	ArrayList
    //   15	296	8	localPackageManager	PackageManager
    //   52	186	9	localArrayList2	ArrayList
    //   22	333	10	localObject2	Object
    //   388	3	10	localException5	Exception
    //   401	3	10	localException6	Exception
    //   301	77	11	localAppInfo	AppInfo
    // Exception table:
    //   from	to	target	type
    //   24	29	32	java/lang/Exception
    //   66	79	116	java/lang/Exception
    //   82	113	116	java/lang/Exception
    //   58	66	124	java/lang/Exception
    //   294	346	388	java/lang/Exception
    //   350	370	388	java/lang/Exception
    //   370	385	388	java/lang/Exception
    //   172	186	401	java/lang/Exception
    //   189	209	401	java/lang/Exception
    //   220	234	401	java/lang/Exception
    //   237	255	401	java/lang/Exception
    //   262	275	401	java/lang/Exception
    //   279	288	401	java/lang/Exception
    //   390	398	401	java/lang/Exception
    //   411	423	442	java/lang/Exception
    //   427	439	442	java/lang/Exception
  }
  
  private static void O000000o(AppInfo paramAppInfo)
  {
    try
    {
      String str2 = paramAppInfo.getAppName().trim();
      String str1 = str2;
      if (str2.startsWith(" ")) {
        str1 = str2.replace(" ", "");
      }
      str1 = O00oOooO.O00000Oo(str1).substring(0, 1).toUpperCase(Locale.CHINESE);
      if (str1.matches("[A-Z]"))
      {
        paramAppInfo.setRankLetter(str1);
        return;
      }
      paramAppInfo.setRankLetter("#");
      return;
    }
    catch (Exception localException)
    {
      ye.O00000o(localException.toString());
      paramAppInfo.setRankLetter("#");
    }
  }
  
  public static boolean O000000o(Intent paramIntent)
  {
    return (paramIntent != null) && (paramIntent.hasCategory("android.intent.category.HOME"));
  }
  
  public static String O00000Oo()
  {
    Object localObject = App.O000000o().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(App.O000000o().getPackageName(), 0).applicationInfo.loadLabel((PackageManager)localObject).toString();
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      ye.O00000o(localNameNotFoundException.toString());
    }
    return "";
  }
  
  public static boolean O00000Oo(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    try
    {
      App.O000000o().getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int O00000o()
  {
    PackageManager localPackageManager = App.O000000o().getPackageManager();
    try
    {
      int i = localPackageManager.getPackageInfo(App.O000000o().getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      ye.O00000o(localNameNotFoundException.toString());
    }
    return 0;
  }
  
  public static String O00000o0()
  {
    Object localObject = App.O000000o().getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(App.O000000o().getPackageName(), 0).versionName;
      return localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      ye.O00000o(localNameNotFoundException.toString());
    }
    return "";
  }
  
  @NonNull
  private static List<String> O00000oO()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = mv.O00000o().O0000o();
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      String str = ((LockApp)localList.get(i)).getPackageName();
      if (O00000Oo(str)) {
        localArrayList.add(i, str);
      } else {
        mv.O00000o().O0000O0o(str);
      }
      i += 1;
    }
    if ((localArrayList.size() <= 0) && (O00Oo.O00000o0("app_lock_using", false))) {
      O00Oo.O000000o("app_lock_using", false);
    }
    return localArrayList;
  }
}
