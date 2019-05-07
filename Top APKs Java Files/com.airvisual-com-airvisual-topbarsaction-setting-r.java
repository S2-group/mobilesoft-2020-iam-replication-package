package com.airvisual.topbarsaction.setting;

import android.preference.Preference.OnPreferenceClickListener;

class r
  implements Preference.OnPreferenceClickListener
{
  r(o paramO) {}
  
  /* Error */
  public boolean onPreferenceClick(android.preference.Preference paramPreference)
  {
    // Byte code:
    //   0: ldc 24
    //   2: invokestatic 30	com/airvisual/b/l:b	(Ljava/lang/String;)V
    //   5: invokestatic 36	com/airvisual/MainActivity:d	()Lcom/airvisual/MainActivity;
    //   8: invokevirtual 40	com/airvisual/MainActivity:getApplication	()Landroid/app/Application;
    //   11: invokevirtual 46	android/app/Application:getPackageManager	()Landroid/content/pm/PackageManager;
    //   14: sipush 8192
    //   17: invokevirtual 52	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   20: invokeinterface 58 1 0
    //   25: astore_1
    //   26: aload_1
    //   27: invokeinterface 64 1 0
    //   32: ifeq +163 -> 195
    //   35: aload_1
    //   36: invokeinterface 68 1 0
    //   41: checkcast 70	android/content/pm/PackageInfo
    //   44: astore 4
    //   46: aload 4
    //   48: getfield 74	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   51: ldc 76
    //   53: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifne +18 -> 74
    //   59: aload 4
    //   61: getfield 74	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   64: ldc 84
    //   66: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   69: istore_3
    //   70: iload_3
    //   71: ifeq -45 -> 26
    //   74: iconst_1
    //   75: istore_2
    //   76: iload_2
    //   77: ifeq +101 -> 178
    //   80: invokestatic 36	com/airvisual/MainActivity:d	()Lcom/airvisual/MainActivity;
    //   83: invokevirtual 88	com/airvisual/MainActivity:getPackageName	()Ljava/lang/String;
    //   86: astore_1
    //   87: aload_0
    //   88: getfield 12	com/airvisual/topbarsaction/setting/r:a	Lcom/airvisual/topbarsaction/setting/o;
    //   91: new 90	android/content/Intent
    //   94: dup
    //   95: ldc 92
    //   97: new 94	java/lang/StringBuilder
    //   100: dup
    //   101: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   104: ldc 97
    //   106: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_1
    //   110: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 110	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   119: invokespecial 113	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   122: invokevirtual 119	com/airvisual/topbarsaction/setting/o:startActivity	(Landroid/content/Intent;)V
    //   125: iconst_1
    //   126: ireturn
    //   127: astore_1
    //   128: iconst_0
    //   129: istore_2
    //   130: goto -54 -> 76
    //   133: astore 4
    //   135: aload_0
    //   136: getfield 12	com/airvisual/topbarsaction/setting/r:a	Lcom/airvisual/topbarsaction/setting/o;
    //   139: new 90	android/content/Intent
    //   142: dup
    //   143: ldc 92
    //   145: new 94	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   152: ldc 121
    //   154: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: aload_1
    //   158: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: invokevirtual 104	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   164: invokestatic 110	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   167: invokespecial 113	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   170: invokevirtual 119	com/airvisual/topbarsaction/setting/o:startActivity	(Landroid/content/Intent;)V
    //   173: iconst_1
    //   174: ireturn
    //   175: astore_1
    //   176: iconst_1
    //   177: ireturn
    //   178: new 123	com/airvisual/b/b/k
    //   181: dup
    //   182: invokespecial 124	com/airvisual/b/b/k:<init>	()V
    //   185: iconst_0
    //   186: anewarray 126	java/lang/Void
    //   189: invokevirtual 130	com/airvisual/b/b/k:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   192: pop
    //   193: iconst_1
    //   194: ireturn
    //   195: iconst_0
    //   196: istore_2
    //   197: goto -121 -> 76
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	this	r
    //   0	200	1	paramPreference	android.preference.Preference
    //   75	122	2	i	int
    //   69	2	3	bool	boolean
    //   44	16	4	localPackageInfo	android.content.pm.PackageInfo
    //   133	1	4	localActivityNotFoundException	android.content.ActivityNotFoundException
    // Exception table:
    //   from	to	target	type
    //   5	26	127	java/lang/Exception
    //   26	70	127	java/lang/Exception
    //   87	125	133	android/content/ActivityNotFoundException
    //   80	87	175	java/lang/Exception
    //   87	125	175	java/lang/Exception
    //   135	173	175	java/lang/Exception
  }
}
