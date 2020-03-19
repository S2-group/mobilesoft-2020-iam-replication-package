package com.agilesoftresource.appmanager;

import a.a.a.b.a.b;
import android.content.SharedPreferences;
import android.os.Environment;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class p
{
  private String a;
  private SharedPreferences b;
  private String c = Environment.getExternalStorageDirectory().getAbsolutePath();
  private ArrayList d = new ArrayList();
  
  public p() {}
  
  private String a(File paramFile, NumberFormat paramNumberFormat)
  {
    String str2 = "";
    double d1 = paramFile.length();
    String str1;
    if ((!paramFile.isDirectory()) && (d1 >= 1024.0D) && (d1 <= 1048576.0D)) {
      str1 = paramNumberFormat.format(d1 / 1024.0D) + " KB";
    }
    do
    {
      do
      {
        return str1;
        if ((!paramFile.isDirectory()) && (d1 > 1048576.0D)) {
          return paramNumberFormat.format(d1 / 1048576.0D) + " MB";
        }
        str1 = str2;
      } while (paramFile.isDirectory());
      str1 = str2;
    } while (d1 < 0.0D);
    return paramNumberFormat.format(d1) + " bytes";
  }
  
  private ArrayList a()
  {
    Object localObject = (String[])null;
    localObject = new ArrayList();
    if (this.a == null) {
      this.a = "app_backup";
    }
    if (!this.a.startsWith("/")) {
      this.a = ("/" + this.a);
    }
    String[] arrayOfString = new File(this.c + "/" + this.a).list(new a());
    if (arrayOfString != null) {
      localObject = new ArrayList(Arrays.asList(arrayOfString));
    }
    return localObject;
  }
  
  private void a(SharedPreferences paramSharedPreferences)
  {
    this.a = paramSharedPreferences.getString("app_backup_folder", "app_backup");
  }
  
  /* Error */
  public ArrayList a(boolean paramBoolean1, android.app.Activity paramActivity, boolean paramBoolean2, String paramString, NumberFormat paramNumberFormat)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 124	com/agilesoftresource/appmanager/p:b	Landroid/content/SharedPreferences;
    //   4: ifnonnull +11 -> 15
    //   7: aload_0
    //   8: aload_2
    //   9: invokestatic 130	android/preference/PreferenceManager:getDefaultSharedPreferences	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   12: putfield 124	com/agilesoftresource/appmanager/p:b	Landroid/content/SharedPreferences;
    //   15: aload_0
    //   16: aload_0
    //   17: getfield 124	com/agilesoftresource/appmanager/p:b	Landroid/content/SharedPreferences;
    //   20: invokespecial 132	com/agilesoftresource/appmanager/p:a	(Landroid/content/SharedPreferences;)V
    //   23: aload_0
    //   24: aload_0
    //   25: invokespecial 134	com/agilesoftresource/appmanager/p:a	()Ljava/util/ArrayList;
    //   28: putfield 20	com/agilesoftresource/appmanager/p:d	Ljava/util/ArrayList;
    //   31: new 17	java/util/ArrayList
    //   34: dup
    //   35: invokespecial 18	java/util/ArrayList:<init>	()V
    //   38: astore 8
    //   40: aload_2
    //   41: invokevirtual 140	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: iconst_0
    //   45: invokevirtual 146	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   48: astore 9
    //   50: aload 9
    //   52: invokeinterface 152 1 0
    //   57: istore 7
    //   59: iconst_0
    //   60: istore 6
    //   62: iload 6
    //   64: iload 7
    //   66: if_icmplt +6 -> 72
    //   69: aload 8
    //   71: areturn
    //   72: aload 9
    //   74: iload 6
    //   76: invokeinterface 156 2 0
    //   81: checkcast 158	android/content/pm/PackageInfo
    //   84: astore 10
    //   86: iload_1
    //   87: ifne +22 -> 109
    //   90: aload 10
    //   92: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   95: getfield 167	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   98: ldc -87
    //   100: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   103: ifeq +6 -> 109
    //   106: goto +648 -> 754
    //   109: new 171	com/agilesoftresource/appmanager/b
    //   112: dup
    //   113: invokespecial 172	com/agilesoftresource/appmanager/b:<init>	()V
    //   116: astore 11
    //   118: aload 11
    //   120: aload 10
    //   122: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   125: aload_2
    //   126: invokevirtual 140	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   129: invokevirtual 176	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   132: invokeinterface 179 1 0
    //   137: putfield 180	com/agilesoftresource/appmanager/b:a	Ljava/lang/String;
    //   140: aload 11
    //   142: aload 10
    //   144: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   147: getfield 167	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   150: putfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   153: aload 11
    //   155: aload 10
    //   157: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   160: getfield 185	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   163: putfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   166: aload 11
    //   168: getfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   171: ldc -66
    //   173: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   176: ifeq +9 -> 185
    //   179: aload 11
    //   181: iconst_1
    //   182: putfield 194	com/agilesoftresource/appmanager/b:j	Z
    //   185: aload 11
    //   187: getfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   190: ldc -60
    //   192: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   195: ifne +9 -> 204
    //   198: aload 11
    //   200: iconst_1
    //   201: putfield 199	com/agilesoftresource/appmanager/b:k	Z
    //   204: aload 11
    //   206: aload 11
    //   208: getfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   211: aload 11
    //   213: getfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   216: ldc 89
    //   218: invokevirtual 203	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   221: iconst_1
    //   222: iadd
    //   223: invokevirtual 207	java/lang/String:substring	(I)Ljava/lang/String;
    //   226: putfield 210	com/agilesoftresource/appmanager/b:i	Ljava/lang/String;
    //   229: aload 11
    //   231: new 52	java/lang/StringBuilder
    //   234: dup
    //   235: ldc -44
    //   237: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   240: aload_0
    //   241: new 28	java/io/File
    //   244: dup
    //   245: aload 11
    //   247: getfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   250: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   253: aload 5
    //   255: invokespecial 214	com/agilesoftresource/appmanager/p:a	(Ljava/io/File;Ljava/text/NumberFormat;)Ljava/lang/String;
    //   258: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   264: putfield 217	com/agilesoftresource/appmanager/b:e	Ljava/lang/String;
    //   267: aload 11
    //   269: new 28	java/io/File
    //   272: dup
    //   273: aload 11
    //   275: getfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   278: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   281: invokevirtual 42	java/io/File:length	()J
    //   284: putfield 221	com/agilesoftresource/appmanager/b:f	J
    //   287: aload 11
    //   289: new 52	java/lang/StringBuilder
    //   292: dup
    //   293: ldc -33
    //   295: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   298: aload 10
    //   300: getfield 226	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   303: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   309: putfield 227	com/agilesoftresource/appmanager/b:c	Ljava/lang/String;
    //   312: aload 11
    //   314: aload 10
    //   316: getfield 231	android/content/pm/PackageInfo:versionCode	I
    //   319: putfield 233	com/agilesoftresource/appmanager/b:d	I
    //   322: aload 11
    //   324: aload 10
    //   326: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   329: aload_2
    //   330: invokevirtual 140	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   333: invokevirtual 237	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   336: putfield 241	com/agilesoftresource/appmanager/b:g	Landroid/graphics/drawable/Drawable;
    //   339: aload_0
    //   340: getfield 20	com/agilesoftresource/appmanager/p:d	Ljava/util/ArrayList;
    //   343: new 52	java/lang/StringBuilder
    //   346: dup
    //   347: aload 11
    //   349: getfield 180	com/agilesoftresource/appmanager/b:a	Ljava/lang/String;
    //   352: invokestatic 64	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   355: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   358: ldc -13
    //   360: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: aload 11
    //   365: getfield 227	com/agilesoftresource/appmanager/b:c	Ljava/lang/String;
    //   368: ldc -11
    //   370: ldc 38
    //   372: invokevirtual 249	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   375: ldc -5
    //   377: ldc 38
    //   379: invokevirtual 249	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   382: invokevirtual 254	java/lang/String:trim	()Ljava/lang/String;
    //   385: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: ldc_w 256
    //   391: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   394: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   397: invokevirtual 260	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   400: ifeq +9 -> 409
    //   403: aload 11
    //   405: iconst_1
    //   406: putfield 263	com/agilesoftresource/appmanager/b:l	Z
    //   409: aload 11
    //   411: getfield 217	com/agilesoftresource/appmanager/b:e	Ljava/lang/String;
    //   414: ldc_w 265
    //   417: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   420: ifne +11 -> 431
    //   423: aload 8
    //   425: aload 11
    //   427: invokevirtual 268	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   430: pop
    //   431: aload_0
    //   432: aload 8
    //   434: iload_3
    //   435: aload 4
    //   437: invokevirtual 271	com/agilesoftresource/appmanager/p:a	(Ljava/util/ArrayList;ZLjava/lang/String;)V
    //   440: goto +314 -> 754
    //   443: astore 10
    //   445: iconst_0
    //   446: istore 6
    //   448: iload 6
    //   450: iload 7
    //   452: if_icmpge -383 -> 69
    //   455: aload 9
    //   457: iload 6
    //   459: invokeinterface 156 2 0
    //   464: checkcast 158	android/content/pm/PackageInfo
    //   467: astore 10
    //   469: iload_1
    //   470: ifne +22 -> 492
    //   473: aload 10
    //   475: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   478: getfield 167	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   481: ldc -87
    //   483: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   486: ifeq +6 -> 492
    //   489: goto +274 -> 763
    //   492: new 171	com/agilesoftresource/appmanager/b
    //   495: dup
    //   496: invokespecial 172	com/agilesoftresource/appmanager/b:<init>	()V
    //   499: astore 11
    //   501: aload 11
    //   503: aload 10
    //   505: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   508: aload_2
    //   509: invokevirtual 140	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   512: invokevirtual 176	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   515: invokeinterface 179 1 0
    //   520: putfield 180	com/agilesoftresource/appmanager/b:a	Ljava/lang/String;
    //   523: aload 11
    //   525: aload 10
    //   527: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   530: getfield 167	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   533: putfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   536: aload 11
    //   538: new 52	java/lang/StringBuilder
    //   541: dup
    //   542: ldc_w 273
    //   545: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   548: aload 11
    //   550: getfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   553: aload 11
    //   555: getfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   558: ldc 89
    //   560: invokevirtual 203	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   563: invokevirtual 207	java/lang/String:substring	(I)Ljava/lang/String;
    //   566: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: ldc_w 256
    //   572: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   578: putfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   581: aload 11
    //   583: aload 11
    //   585: getfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   588: aload 11
    //   590: getfield 182	com/agilesoftresource/appmanager/b:b	Ljava/lang/String;
    //   593: ldc 89
    //   595: invokevirtual 203	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   598: iconst_1
    //   599: iadd
    //   600: invokevirtual 207	java/lang/String:substring	(I)Ljava/lang/String;
    //   603: putfield 210	com/agilesoftresource/appmanager/b:i	Ljava/lang/String;
    //   606: aload 11
    //   608: new 52	java/lang/StringBuilder
    //   611: dup
    //   612: ldc -44
    //   614: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   617: aload_0
    //   618: new 28	java/io/File
    //   621: dup
    //   622: aload 11
    //   624: getfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   627: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   630: aload 5
    //   632: invokespecial 214	com/agilesoftresource/appmanager/p:a	(Ljava/io/File;Ljava/text/NumberFormat;)Ljava/lang/String;
    //   635: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   641: putfield 217	com/agilesoftresource/appmanager/b:e	Ljava/lang/String;
    //   644: aload 11
    //   646: new 28	java/io/File
    //   649: dup
    //   650: aload 11
    //   652: getfield 188	com/agilesoftresource/appmanager/b:h	Ljava/lang/String;
    //   655: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   658: invokevirtual 42	java/io/File:length	()J
    //   661: putfield 221	com/agilesoftresource/appmanager/b:f	J
    //   664: aload 11
    //   666: new 52	java/lang/StringBuilder
    //   669: dup
    //   670: ldc -33
    //   672: invokespecial 67	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   675: aload 10
    //   677: getfield 226	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   680: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   683: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   686: putfield 227	com/agilesoftresource/appmanager/b:c	Ljava/lang/String;
    //   689: aload 11
    //   691: aload 10
    //   693: getfield 231	android/content/pm/PackageInfo:versionCode	I
    //   696: putfield 233	com/agilesoftresource/appmanager/b:d	I
    //   699: aload 11
    //   701: aload 10
    //   703: getfield 162	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   706: aload_2
    //   707: invokevirtual 140	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   710: invokevirtual 237	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   713: putfield 241	com/agilesoftresource/appmanager/b:g	Landroid/graphics/drawable/Drawable;
    //   716: aload 11
    //   718: getfield 217	com/agilesoftresource/appmanager/b:e	Ljava/lang/String;
    //   721: ldc_w 265
    //   724: invokevirtual 93	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   727: ifne +11 -> 738
    //   730: aload 8
    //   732: aload 11
    //   734: invokevirtual 268	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   737: pop
    //   738: aload_0
    //   739: aload 8
    //   741: iload_3
    //   742: aload 4
    //   744: invokevirtual 271	com/agilesoftresource/appmanager/p:a	(Ljava/util/ArrayList;ZLjava/lang/String;)V
    //   747: goto +16 -> 763
    //   750: astore_2
    //   751: aload 8
    //   753: areturn
    //   754: iload 6
    //   756: iconst_1
    //   757: iadd
    //   758: istore 6
    //   760: goto -698 -> 62
    //   763: iload 6
    //   765: iconst_1
    //   766: iadd
    //   767: istore 6
    //   769: goto -321 -> 448
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	772	0	this	p
    //   0	772	1	paramBoolean1	boolean
    //   0	772	2	paramActivity	android.app.Activity
    //   0	772	3	paramBoolean2	boolean
    //   0	772	4	paramString	String
    //   0	772	5	paramNumberFormat	NumberFormat
    //   60	708	6	i	int
    //   57	396	7	j	int
    //   38	714	8	localArrayList	ArrayList
    //   48	408	9	localList	java.util.List
    //   84	241	10	localPackageInfo1	android.content.pm.PackageInfo
    //   443	1	10	localException	Exception
    //   467	235	10	localPackageInfo2	android.content.pm.PackageInfo
    //   116	617	11	localB	b
    // Exception table:
    //   from	to	target	type
    //   72	86	443	java/lang/Exception
    //   90	106	443	java/lang/Exception
    //   109	185	443	java/lang/Exception
    //   185	204	443	java/lang/Exception
    //   204	409	443	java/lang/Exception
    //   409	431	443	java/lang/Exception
    //   431	440	443	java/lang/Exception
    //   72	86	750	finally
    //   90	106	750	finally
    //   109	185	750	finally
    //   185	204	750	finally
    //   204	409	750	finally
    //   409	431	750	finally
    //   431	440	750	finally
    //   455	469	750	finally
    //   473	489	750	finally
    //   492	738	750	finally
    //   738	747	750	finally
  }
  
  public void a(ArrayList paramArrayList, boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      if (paramString.equalsIgnoreCase("name")) {
        Collections.sort(paramArrayList, a.a.a.b.a.a.c);
      }
    }
    do
    {
      do
      {
        return;
      } while (!paramString.equalsIgnoreCase("size"));
      Collections.sort(paramArrayList, b.a);
      return;
      if (paramString.equalsIgnoreCase("name"))
      {
        Collections.sort(paramArrayList, a.a.a.b.a.a.d);
        return;
      }
    } while (!paramString.equalsIgnoreCase("size"));
    Collections.sort(paramArrayList, b.b);
  }
}
