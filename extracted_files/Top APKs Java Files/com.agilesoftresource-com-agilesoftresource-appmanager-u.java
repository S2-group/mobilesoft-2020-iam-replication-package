package com.agilesoftresource.appmanager;

import a.a.a.b.a.a;
import a.a.a.b.a.b;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

public class u
{
  public u() {}
  
  private String a(File paramFile, NumberFormat paramNumberFormat)
  {
    String str2 = "";
    double d = paramFile.length();
    String str1;
    if ((!paramFile.isDirectory()) && (d >= 1024.0D) && (d <= 1048576.0D)) {
      str1 = paramNumberFormat.format(d / 1024.0D) + " KB";
    }
    do
    {
      do
      {
        return str1;
        if ((!paramFile.isDirectory()) && (d > 1048576.0D)) {
          return paramNumberFormat.format(d / 1048576.0D) + " MB";
        }
        str1 = str2;
      } while (paramFile.isDirectory());
      str1 = str2;
    } while (d < 0.0D);
    return paramNumberFormat.format(d) + " bytes";
  }
  
  /* Error */
  public ArrayList a(boolean paramBoolean1, android.app.Activity paramActivity, boolean paramBoolean2, String paramString, NumberFormat paramNumberFormat)
  {
    // Byte code:
    //   0: new 63	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 64	java/util/ArrayList:<init>	()V
    //   7: astore 8
    //   9: aload_2
    //   10: invokevirtual 70	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   13: iconst_0
    //   14: invokevirtual 76	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   17: astore 9
    //   19: aload 9
    //   21: invokeinterface 82 1 0
    //   26: istore 7
    //   28: iconst_0
    //   29: istore 6
    //   31: iload 6
    //   33: iload 7
    //   35: if_icmplt +6 -> 41
    //   38: aload 8
    //   40: areturn
    //   41: aload 9
    //   43: iload 6
    //   45: invokeinterface 86 2 0
    //   50: checkcast 88	android/content/pm/PackageInfo
    //   53: astore 10
    //   55: iload_1
    //   56: ifne +22 -> 78
    //   59: aload 10
    //   61: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   64: getfield 98	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   67: ldc 100
    //   69: invokevirtual 104	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   72: ifeq +6 -> 78
    //   75: goto +536 -> 611
    //   78: new 106	com/agilesoftresource/appmanager/a
    //   81: dup
    //   82: invokespecial 107	com/agilesoftresource/appmanager/a:<init>	()V
    //   85: astore 11
    //   87: aload 11
    //   89: aload 10
    //   91: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   94: aload_2
    //   95: invokevirtual 70	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   98: invokevirtual 111	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   101: invokeinterface 114 1 0
    //   106: putfield 116	com/agilesoftresource/appmanager/a:a	Ljava/lang/String;
    //   109: aload 11
    //   111: aload 10
    //   113: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   116: getfield 98	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   119: putfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   122: aload 11
    //   124: aload 10
    //   126: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   129: getfield 122	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   132: putfield 125	com/agilesoftresource/appmanager/a:h	Ljava/lang/String;
    //   135: aload 11
    //   137: aload 11
    //   139: getfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   142: aload 11
    //   144: getfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   147: ldc 127
    //   149: invokevirtual 131	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   152: iconst_1
    //   153: iadd
    //   154: invokevirtual 135	java/lang/String:substring	(I)Ljava/lang/String;
    //   157: putfield 138	com/agilesoftresource/appmanager/a:i	Ljava/lang/String;
    //   160: aload 11
    //   162: new 29	java/lang/StringBuilder
    //   165: dup
    //   166: ldc -116
    //   168: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: aload_0
    //   172: new 15	java/io/File
    //   175: dup
    //   176: aload 11
    //   178: getfield 125	com/agilesoftresource/appmanager/a:h	Ljava/lang/String;
    //   181: invokespecial 141	java/io/File:<init>	(Ljava/lang/String;)V
    //   184: aload 5
    //   186: invokespecial 143	com/agilesoftresource/appmanager/u:a	(Ljava/io/File;Ljava/text/NumberFormat;)Ljava/lang/String;
    //   189: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   192: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: putfield 146	com/agilesoftresource/appmanager/a:e	Ljava/lang/String;
    //   198: aload 11
    //   200: new 15	java/io/File
    //   203: dup
    //   204: aload 11
    //   206: getfield 125	com/agilesoftresource/appmanager/a:h	Ljava/lang/String;
    //   209: invokespecial 141	java/io/File:<init>	(Ljava/lang/String;)V
    //   212: invokevirtual 19	java/io/File:length	()J
    //   215: putfield 150	com/agilesoftresource/appmanager/a:f	J
    //   218: aload 11
    //   220: new 29	java/lang/StringBuilder
    //   223: dup
    //   224: ldc -104
    //   226: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   229: aload 10
    //   231: getfield 155	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   234: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   240: putfield 158	com/agilesoftresource/appmanager/a:c	Ljava/lang/String;
    //   243: aload 11
    //   245: aload 10
    //   247: getfield 162	android/content/pm/PackageInfo:versionCode	I
    //   250: putfield 165	com/agilesoftresource/appmanager/a:d	I
    //   253: aload 11
    //   255: aload 10
    //   257: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   260: aload_2
    //   261: invokevirtual 70	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   264: invokevirtual 169	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   267: putfield 173	com/agilesoftresource/appmanager/a:g	Landroid/graphics/drawable/Drawable;
    //   270: aload 11
    //   272: getfield 146	com/agilesoftresource/appmanager/a:e	Ljava/lang/String;
    //   275: ldc -81
    //   277: invokevirtual 104	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   280: ifne +11 -> 291
    //   283: aload 8
    //   285: aload 11
    //   287: invokevirtual 179	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   290: pop
    //   291: aload_0
    //   292: aload 8
    //   294: iload_3
    //   295: aload 4
    //   297: invokevirtual 182	com/agilesoftresource/appmanager/u:a	(Ljava/util/ArrayList;ZLjava/lang/String;)V
    //   300: goto +311 -> 611
    //   303: astore 10
    //   305: iconst_0
    //   306: istore 6
    //   308: iload 6
    //   310: iload 7
    //   312: if_icmpge -274 -> 38
    //   315: aload 9
    //   317: iload 6
    //   319: invokeinterface 86 2 0
    //   324: checkcast 88	android/content/pm/PackageInfo
    //   327: astore 10
    //   329: iload_1
    //   330: ifne +22 -> 352
    //   333: aload 10
    //   335: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   338: getfield 98	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   341: ldc 100
    //   343: invokevirtual 104	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   346: ifeq +6 -> 352
    //   349: goto +271 -> 620
    //   352: new 106	com/agilesoftresource/appmanager/a
    //   355: dup
    //   356: invokespecial 107	com/agilesoftresource/appmanager/a:<init>	()V
    //   359: astore 11
    //   361: aload 11
    //   363: aload 10
    //   365: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   368: aload_2
    //   369: invokevirtual 70	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   372: invokevirtual 111	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   375: invokeinterface 114 1 0
    //   380: putfield 116	com/agilesoftresource/appmanager/a:a	Ljava/lang/String;
    //   383: aload 11
    //   385: aload 10
    //   387: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   390: getfield 98	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   393: putfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   396: aload 11
    //   398: new 29	java/lang/StringBuilder
    //   401: dup
    //   402: ldc -72
    //   404: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   407: aload 11
    //   409: getfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   412: aload 11
    //   414: getfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   417: ldc 127
    //   419: invokevirtual 131	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   422: invokevirtual 135	java/lang/String:substring	(I)Ljava/lang/String;
    //   425: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: ldc -70
    //   430: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: putfield 125	com/agilesoftresource/appmanager/a:h	Ljava/lang/String;
    //   439: aload 11
    //   441: aload 11
    //   443: getfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   446: aload 11
    //   448: getfield 119	com/agilesoftresource/appmanager/a:b	Ljava/lang/String;
    //   451: ldc 127
    //   453: invokevirtual 131	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   456: iconst_1
    //   457: iadd
    //   458: invokevirtual 135	java/lang/String:substring	(I)Ljava/lang/String;
    //   461: putfield 138	com/agilesoftresource/appmanager/a:i	Ljava/lang/String;
    //   464: aload 11
    //   466: new 29	java/lang/StringBuilder
    //   469: dup
    //   470: ldc -116
    //   472: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   475: aload_0
    //   476: new 15	java/io/File
    //   479: dup
    //   480: aload 11
    //   482: getfield 125	com/agilesoftresource/appmanager/a:h	Ljava/lang/String;
    //   485: invokespecial 141	java/io/File:<init>	(Ljava/lang/String;)V
    //   488: aload 5
    //   490: invokespecial 143	com/agilesoftresource/appmanager/u:a	(Ljava/io/File;Ljava/text/NumberFormat;)Ljava/lang/String;
    //   493: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   499: putfield 146	com/agilesoftresource/appmanager/a:e	Ljava/lang/String;
    //   502: aload 11
    //   504: new 15	java/io/File
    //   507: dup
    //   508: aload 11
    //   510: getfield 125	com/agilesoftresource/appmanager/a:h	Ljava/lang/String;
    //   513: invokespecial 141	java/io/File:<init>	(Ljava/lang/String;)V
    //   516: invokevirtual 19	java/io/File:length	()J
    //   519: putfield 150	com/agilesoftresource/appmanager/a:f	J
    //   522: aload 11
    //   524: new 29	java/lang/StringBuilder
    //   527: dup
    //   528: ldc -104
    //   530: invokespecial 44	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   533: aload 10
    //   535: getfield 155	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   538: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   544: putfield 158	com/agilesoftresource/appmanager/a:c	Ljava/lang/String;
    //   547: aload 11
    //   549: aload 10
    //   551: getfield 162	android/content/pm/PackageInfo:versionCode	I
    //   554: putfield 165	com/agilesoftresource/appmanager/a:d	I
    //   557: aload 11
    //   559: aload 10
    //   561: getfield 92	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   564: aload_2
    //   565: invokevirtual 70	android/app/Activity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   568: invokevirtual 169	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   571: putfield 173	com/agilesoftresource/appmanager/a:g	Landroid/graphics/drawable/Drawable;
    //   574: aload 11
    //   576: getfield 146	com/agilesoftresource/appmanager/a:e	Ljava/lang/String;
    //   579: ldc -81
    //   581: invokevirtual 104	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   584: ifne +11 -> 595
    //   587: aload 8
    //   589: aload 11
    //   591: invokevirtual 179	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   594: pop
    //   595: aload_0
    //   596: aload 8
    //   598: iload_3
    //   599: aload 4
    //   601: invokevirtual 182	com/agilesoftresource/appmanager/u:a	(Ljava/util/ArrayList;ZLjava/lang/String;)V
    //   604: goto +16 -> 620
    //   607: astore_2
    //   608: aload 8
    //   610: areturn
    //   611: iload 6
    //   613: iconst_1
    //   614: iadd
    //   615: istore 6
    //   617: goto -586 -> 31
    //   620: iload 6
    //   622: iconst_1
    //   623: iadd
    //   624: istore 6
    //   626: goto -318 -> 308
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	629	0	this	u
    //   0	629	1	paramBoolean1	boolean
    //   0	629	2	paramActivity	android.app.Activity
    //   0	629	3	paramBoolean2	boolean
    //   0	629	4	paramString	String
    //   0	629	5	paramNumberFormat	NumberFormat
    //   29	596	6	i	int
    //   26	287	7	j	int
    //   7	602	8	localArrayList	ArrayList
    //   17	299	9	localList	java.util.List
    //   53	203	10	localPackageInfo1	android.content.pm.PackageInfo
    //   303	1	10	localException	Exception
    //   327	233	10	localPackageInfo2	android.content.pm.PackageInfo
    //   85	505	11	localA	a
    // Exception table:
    //   from	to	target	type
    //   41	55	303	java/lang/Exception
    //   59	75	303	java/lang/Exception
    //   78	291	303	java/lang/Exception
    //   291	300	303	java/lang/Exception
    //   41	55	607	finally
    //   59	75	607	finally
    //   78	291	607	finally
    //   291	300	607	finally
    //   315	329	607	finally
    //   333	349	607	finally
    //   352	595	607	finally
    //   595	604	607	finally
  }
  
  public void a(ArrayList paramArrayList, boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      if (paramString.equalsIgnoreCase("name")) {
        Collections.sort(paramArrayList, a.c);
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
        Collections.sort(paramArrayList, a.d);
        return;
      }
    } while (!paramString.equalsIgnoreCase("size"));
    Collections.sort(paramArrayList, b.b);
  }
}
