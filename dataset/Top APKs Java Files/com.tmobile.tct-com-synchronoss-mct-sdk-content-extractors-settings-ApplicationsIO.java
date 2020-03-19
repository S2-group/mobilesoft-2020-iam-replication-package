package com.synchronoss.mct.sdk.content.extractors.settings;

import android.content.Context;
import android.content.pm.PackageManager;
import com.synchronoss.p2p.containers.settings.Applications;

public class ApplicationsIO
  extends SettingsIO
{
  private final Applications c = new Applications();
  private final PackageManager d;
  
  public ApplicationsIO(Context paramContext)
  {
    super(paramContext);
    this.d = paramContext.getPackageManager();
  }
  
  public final String a()
  {
    return "applications";
  }
  
  /* Error */
  public final void a(com.synchronoss.mct.sdk.interfaces.ContentProgress arg1)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 27	com/synchronoss/mct/sdk/content/extractors/settings/ApplicationsIO:d	Landroid/content/pm/PackageManager;
    //   4: iconst_0
    //   5: invokevirtual 41	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   8: astore 7
    //   10: aload 7
    //   12: invokeinterface 47 1 0
    //   17: astore 8
    //   19: iconst_0
    //   20: istore_2
    //   21: aload 8
    //   23: invokeinterface 53 1 0
    //   28: ifeq +123 -> 151
    //   31: aload 8
    //   33: invokeinterface 57 1 0
    //   38: checkcast 59	android/content/pm/PackageInfo
    //   41: astore 6
    //   43: new 61	com/synchronoss/p2p/containers/settings/Application
    //   46: dup
    //   47: aload 6
    //   49: getfield 65	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   52: aload_0
    //   53: getfield 27	com/synchronoss/mct/sdk/content/extractors/settings/ApplicationsIO:d	Landroid/content/pm/PackageManager;
    //   56: invokevirtual 71	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   59: invokeinterface 76 1 0
    //   64: aload 6
    //   66: getfield 80	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   69: aload 6
    //   71: getfield 83	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   74: ldc 85
    //   76: ldc 85
    //   78: aconst_null
    //   79: invokespecial 88	com/synchronoss/p2p/containers/settings/Application:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/synchronoss/p2p/containers/settings/Icon;)V
    //   82: astore 6
    //   84: aload_0
    //   85: getfield 19	com/synchronoss/mct/sdk/content/extractors/settings/ApplicationsIO:c	Lcom/synchronoss/p2p/containers/settings/Applications;
    //   88: invokevirtual 91	com/synchronoss/p2p/containers/settings/Applications:c	()Ljava/util/List;
    //   91: aload 6
    //   93: invokeinterface 95 2 0
    //   98: pop
    //   99: iload_2
    //   100: iconst_1
    //   101: iadd
    //   102: istore_2
    //   103: iload_2
    //   104: i2l
    //   105: lstore 4
    //   107: aload_1
    //   108: ldc 32
    //   110: new 97	com/synchronoss/mct/sdk/transfer/ProgressInfo
    //   113: dup
    //   114: lload 4
    //   116: aload 7
    //   118: invokeinterface 101 1 0
    //   123: i2l
    //   124: invokespecial 104	com/synchronoss/mct/sdk/transfer/ProgressInfo:<init>	(JJ)V
    //   127: invokeinterface 109 3 0
    //   132: goto -111 -> 21
    //   135: astore_1
    //   136: iload_2
    //   137: istore_3
    //   138: aload_1
    //   139: ldc 32
    //   141: aload 6
    //   143: invokeinterface 112 3 0
    //   148: goto -127 -> 21
    //   151: aload_1
    //   152: ldc 32
    //   154: aload_0
    //   155: getfield 19	com/synchronoss/mct/sdk/content/extractors/settings/ApplicationsIO:c	Lcom/synchronoss/p2p/containers/settings/Applications;
    //   158: invokevirtual 91	com/synchronoss/p2p/containers/settings/Applications:c	()Ljava/util/List;
    //   161: invokeinterface 101 1 0
    //   166: i2l
    //   167: invokeinterface 115 4 0
    //   172: return
    //   173: astore 6
    //   175: goto -37 -> 138
    //   178: astore 6
    //   180: goto -81 -> 99
    // Exception table:
    //   from	to	target	type
    //   107	132	173	java/lang/Exception
    //   43	99	178	java/lang/Exception
  }
  
  public final Applications b()
  {
    return this.c;
  }
}
