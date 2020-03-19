package com.zenprise.samsungmdm;

import android.app.enterprise.ApplicationPolicy;
import android.app.enterprise.EnterpriseDeviceManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.citrix.work.log.Logger;
import com.zenprise.Util;
import com.zenprise.configuration.PackageInstallation;
import com.zenprise.configuration.TouchDownActivate;
import java.util.HashSet;
import java.util.Iterator;

public class Apps
{
  private static final String SAMSUNG_CONTAINER_PREFIX = "sec_container_";
  static Logger logger = Logger.getLogger("samsungmdm.Apps");
  
  public Apps() {}
  
  public static void allowUninstall(Context paramContext, String paramString, boolean paramBoolean)
  {
    EnterpriseDeviceManager localEnterpriseDeviceManager = (EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy");
    ApplicationPolicy localApplicationPolicy = localEnterpriseDeviceManager.getApplicationPolicy();
    if (paramBoolean) {
      localApplicationPolicy.setApplicationUninstallationEnabled(paramString);
    }
    for (;;)
    {
      if (paramString.equals(Util.getPackageName(paramContext))) {
        localEnterpriseDeviceManager.setAdminRemovable(paramBoolean);
      }
      return;
      localApplicationPolicy.setApplicationUninstallationDisabled(paramString);
    }
  }
  
  public static long getCPU(Context paramContext, String paramString)
    throws SecurityException
  {
    return ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy().getApplicationCpuUsage(paramString);
  }
  
  /* Error */
  public static void install(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    throws Exception
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 30
    //   3: invokevirtual 36	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   6: checkcast 38	android/app/enterprise/EnterpriseDeviceManager
    //   9: invokevirtual 42	android/app/enterprise/EnterpriseDeviceManager:getApplicationPolicy	()Landroid/app/enterprise/ApplicationPolicy;
    //   12: astore 8
    //   14: iload 5
    //   16: ifeq +166 -> 182
    //   19: aload_0
    //   20: invokestatic 86	com/zenprise/samsungmdm/Check:haveKnoxAPI	(Landroid/content/Context;)Z
    //   23: ifne +87 -> 110
    //   26: new 80	java/lang/Exception
    //   29: dup
    //   30: ldc 88
    //   32: invokespecial 90	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   35: athrow
    //   36: astore_0
    //   37: aload_0
    //   38: athrow
    //   39: astore 9
    //   41: iload 4
    //   43: ifeq +15 -> 58
    //   46: new 92	java/io/File
    //   49: dup
    //   50: aload_1
    //   51: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   54: invokevirtual 97	java/io/File:delete	()Z
    //   57: pop
    //   58: iload 5
    //   60: ifne +47 -> 107
    //   63: aload_2
    //   64: ifnull +43 -> 107
    //   67: aload_2
    //   68: bipush 59
    //   70: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   73: istore 6
    //   75: iload 6
    //   77: ifle +448 -> 525
    //   80: aload_2
    //   81: iconst_0
    //   82: iload 6
    //   84: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
    //   87: astore_0
    //   88: aload 8
    //   90: aload_0
    //   91: invokevirtual 109	android/app/enterprise/ApplicationPolicy:isApplicationInstalled	(Ljava/lang/String;)Z
    //   94: ifeq +13 -> 107
    //   97: iload_3
    //   98: ifeq +432 -> 530
    //   101: aload 8
    //   103: aload_0
    //   104: invokevirtual 48	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationEnabled	(Ljava/lang/String;)V
    //   107: aload 9
    //   109: athrow
    //   110: aload_0
    //   111: aload_1
    //   112: invokestatic 115	com/zenprise/samsungmdm/Container:appInstall	(Landroid/content/Context;Ljava/lang/String;)V
    //   115: iload 4
    //   117: ifeq +15 -> 132
    //   120: new 92	java/io/File
    //   123: dup
    //   124: aload_1
    //   125: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   128: invokevirtual 97	java/io/File:delete	()Z
    //   131: pop
    //   132: iload 5
    //   134: ifne +47 -> 181
    //   137: aload_2
    //   138: ifnull +43 -> 181
    //   141: aload_2
    //   142: bipush 59
    //   144: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   147: istore 6
    //   149: iload 6
    //   151: ifle +409 -> 560
    //   154: aload_2
    //   155: iconst_0
    //   156: iload 6
    //   158: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
    //   161: astore_0
    //   162: aload 8
    //   164: aload_0
    //   165: invokevirtual 109	android/app/enterprise/ApplicationPolicy:isApplicationInstalled	(Ljava/lang/String;)Z
    //   168: ifeq +13 -> 181
    //   171: iload_3
    //   172: ifeq +393 -> 565
    //   175: aload 8
    //   177: aload_0
    //   178: invokevirtual 48	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationEnabled	(Ljava/lang/String;)V
    //   181: return
    //   182: aload 8
    //   184: aload_1
    //   185: invokevirtual 118	android/app/enterprise/ApplicationPolicy:updateApplication	(Ljava/lang/String;)Z
    //   188: istore 7
    //   190: iload 7
    //   192: ifeq +83 -> 275
    //   195: iload 4
    //   197: ifeq +15 -> 212
    //   200: new 92	java/io/File
    //   203: dup
    //   204: aload_1
    //   205: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   208: invokevirtual 97	java/io/File:delete	()Z
    //   211: pop
    //   212: iload 5
    //   214: ifne -33 -> 181
    //   217: aload_2
    //   218: ifnull -37 -> 181
    //   221: aload_2
    //   222: bipush 59
    //   224: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   227: istore 6
    //   229: iload 6
    //   231: ifle +354 -> 585
    //   234: aload_2
    //   235: iconst_0
    //   236: iload 6
    //   238: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
    //   241: astore_0
    //   242: aload 8
    //   244: aload_0
    //   245: invokevirtual 109	android/app/enterprise/ApplicationPolicy:isApplicationInstalled	(Ljava/lang/String;)Z
    //   248: ifeq -67 -> 181
    //   251: iload_3
    //   252: ifeq +338 -> 590
    //   255: aload 8
    //   257: aload_0
    //   258: invokevirtual 48	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationEnabled	(Ljava/lang/String;)V
    //   261: return
    //   262: astore_0
    //   263: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   266: astore_1
    //   267: aload_1
    //   268: ldc 120
    //   270: aload_0
    //   271: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   274: return
    //   275: aload 8
    //   277: aload_1
    //   278: iconst_1
    //   279: invokevirtual 128	android/app/enterprise/ApplicationPolicy:installApplication	(Ljava/lang/String;Z)Z
    //   282: istore 7
    //   284: iload 7
    //   286: ifeq +78 -> 364
    //   289: iload 4
    //   291: ifeq +15 -> 306
    //   294: new 92	java/io/File
    //   297: dup
    //   298: aload_1
    //   299: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   302: invokevirtual 97	java/io/File:delete	()Z
    //   305: pop
    //   306: iload 5
    //   308: ifne -127 -> 181
    //   311: aload_2
    //   312: ifnull -131 -> 181
    //   315: aload_2
    //   316: bipush 59
    //   318: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   321: istore 6
    //   323: iload 6
    //   325: ifle +285 -> 610
    //   328: aload_2
    //   329: iconst_0
    //   330: iload 6
    //   332: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
    //   335: astore_0
    //   336: aload 8
    //   338: aload_0
    //   339: invokevirtual 109	android/app/enterprise/ApplicationPolicy:isApplicationInstalled	(Ljava/lang/String;)Z
    //   342: ifeq -161 -> 181
    //   345: iload_3
    //   346: ifeq +269 -> 615
    //   349: aload 8
    //   351: aload_0
    //   352: invokevirtual 48	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationEnabled	(Ljava/lang/String;)V
    //   355: return
    //   356: astore_0
    //   357: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   360: astore_1
    //   361: goto -94 -> 267
    //   364: aload 8
    //   366: aload_1
    //   367: iconst_0
    //   368: invokevirtual 128	android/app/enterprise/ApplicationPolicy:installApplication	(Ljava/lang/String;Z)Z
    //   371: istore 7
    //   373: iload 7
    //   375: ifeq +272 -> 647
    //   378: iload 4
    //   380: ifeq +15 -> 395
    //   383: new 92	java/io/File
    //   386: dup
    //   387: aload_1
    //   388: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   391: invokevirtual 97	java/io/File:delete	()Z
    //   394: pop
    //   395: iload 5
    //   397: ifne -216 -> 181
    //   400: aload_2
    //   401: ifnull -220 -> 181
    //   404: aload_2
    //   405: bipush 59
    //   407: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   410: istore 6
    //   412: iload 6
    //   414: ifle +221 -> 635
    //   417: aload_2
    //   418: iconst_0
    //   419: iload 6
    //   421: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
    //   424: astore_0
    //   425: aload 8
    //   427: aload_0
    //   428: invokevirtual 109	android/app/enterprise/ApplicationPolicy:isApplicationInstalled	(Ljava/lang/String;)Z
    //   431: ifeq -250 -> 181
    //   434: iload_3
    //   435: ifeq +205 -> 640
    //   438: aload 8
    //   440: aload_0
    //   441: invokevirtual 48	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationEnabled	(Ljava/lang/String;)V
    //   444: return
    //   445: astore_0
    //   446: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   449: astore_1
    //   450: goto -183 -> 267
    //   453: aload 8
    //   455: aload_2
    //   456: invokevirtual 109	android/app/enterprise/ApplicationPolicy:isApplicationInstalled	(Ljava/lang/String;)Z
    //   459: ifeq +13 -> 472
    //   462: iload_3
    //   463: ifeq +260 -> 723
    //   466: aload 8
    //   468: aload_2
    //   469: invokevirtual 48	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationEnabled	(Ljava/lang/String;)V
    //   472: new 80	java/lang/Exception
    //   475: dup
    //   476: new 130	java/lang/StringBuilder
    //   479: dup
    //   480: invokespecial 131	java/lang/StringBuilder:<init>	()V
    //   483: ldc -123
    //   485: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   488: aload_1
    //   489: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: invokevirtual 141	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   495: invokespecial 90	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   498: athrow
    //   499: astore_0
    //   500: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   503: ldc 120
    //   505: aload_0
    //   506: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   509: goto -451 -> 58
    //   512: astore_0
    //   513: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   516: ldc 120
    //   518: aload_0
    //   519: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   522: goto -415 -> 107
    //   525: aload_2
    //   526: astore_0
    //   527: goto -439 -> 88
    //   530: aload 8
    //   532: aload_0
    //   533: invokevirtual 67	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationDisabled	(Ljava/lang/String;)V
    //   536: goto -429 -> 107
    //   539: astore_0
    //   540: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   543: ldc 120
    //   545: aload_0
    //   546: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   549: goto -417 -> 132
    //   552: astore_0
    //   553: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   556: astore_1
    //   557: goto -290 -> 267
    //   560: aload_2
    //   561: astore_0
    //   562: goto -400 -> 162
    //   565: aload 8
    //   567: aload_0
    //   568: invokevirtual 67	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationDisabled	(Ljava/lang/String;)V
    //   571: return
    //   572: astore_0
    //   573: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   576: ldc 120
    //   578: aload_0
    //   579: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   582: goto -370 -> 212
    //   585: aload_2
    //   586: astore_0
    //   587: goto -345 -> 242
    //   590: aload 8
    //   592: aload_0
    //   593: invokevirtual 67	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationDisabled	(Ljava/lang/String;)V
    //   596: return
    //   597: astore_0
    //   598: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   601: ldc 120
    //   603: aload_0
    //   604: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   607: goto -301 -> 306
    //   610: aload_2
    //   611: astore_0
    //   612: goto -276 -> 336
    //   615: aload 8
    //   617: aload_0
    //   618: invokevirtual 67	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationDisabled	(Ljava/lang/String;)V
    //   621: return
    //   622: astore_0
    //   623: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   626: ldc 120
    //   628: aload_0
    //   629: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   632: goto -237 -> 395
    //   635: aload_2
    //   636: astore_0
    //   637: goto -212 -> 425
    //   640: aload 8
    //   642: aload_0
    //   643: invokevirtual 67	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationDisabled	(Ljava/lang/String;)V
    //   646: return
    //   647: iload 4
    //   649: ifeq +15 -> 664
    //   652: new 92	java/io/File
    //   655: dup
    //   656: aload_1
    //   657: invokespecial 93	java/io/File:<init>	(Ljava/lang/String;)V
    //   660: invokevirtual 97	java/io/File:delete	()Z
    //   663: pop
    //   664: iload 5
    //   666: ifne -194 -> 472
    //   669: aload_2
    //   670: ifnull -198 -> 472
    //   673: aload_2
    //   674: bipush 59
    //   676: invokevirtual 101	java/lang/String:indexOf	(I)I
    //   679: istore 6
    //   681: iload 6
    //   683: ifle -230 -> 453
    //   686: aload_2
    //   687: iconst_0
    //   688: iload 6
    //   690: invokevirtual 105	java/lang/String:substring	(II)Ljava/lang/String;
    //   693: astore_2
    //   694: goto -241 -> 453
    //   697: astore_0
    //   698: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   701: ldc 120
    //   703: aload_0
    //   704: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   707: goto -43 -> 664
    //   710: astore_0
    //   711: getstatic 22	com/zenprise/samsungmdm/Apps:logger	Lcom/citrix/work/log/Logger;
    //   714: ldc 120
    //   716: aload_0
    //   717: invokevirtual 124	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   720: goto -248 -> 472
    //   723: aload 8
    //   725: aload_2
    //   726: invokevirtual 67	android/app/enterprise/ApplicationPolicy:setApplicationUninstallationDisabled	(Ljava/lang/String;)V
    //   729: goto -257 -> 472
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	732	0	paramContext	Context
    //   0	732	1	paramString1	String
    //   0	732	2	paramString2	String
    //   0	732	3	paramBoolean1	boolean
    //   0	732	4	paramBoolean2	boolean
    //   0	732	5	paramBoolean3	boolean
    //   73	616	6	i	int
    //   188	186	7	bool	boolean
    //   12	712	8	localApplicationPolicy	ApplicationPolicy
    //   39	69	9	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   19	36	36	java/lang/Exception
    //   110	115	36	java/lang/Exception
    //   182	190	36	java/lang/Exception
    //   275	284	36	java/lang/Exception
    //   364	373	36	java/lang/Exception
    //   19	36	39	finally
    //   37	39	39	finally
    //   110	115	39	finally
    //   182	190	39	finally
    //   275	284	39	finally
    //   364	373	39	finally
    //   221	229	262	java/lang/Exception
    //   234	242	262	java/lang/Exception
    //   242	251	262	java/lang/Exception
    //   255	261	262	java/lang/Exception
    //   590	596	262	java/lang/Exception
    //   315	323	356	java/lang/Exception
    //   328	336	356	java/lang/Exception
    //   336	345	356	java/lang/Exception
    //   349	355	356	java/lang/Exception
    //   615	621	356	java/lang/Exception
    //   404	412	445	java/lang/Exception
    //   417	425	445	java/lang/Exception
    //   425	434	445	java/lang/Exception
    //   438	444	445	java/lang/Exception
    //   640	646	445	java/lang/Exception
    //   46	58	499	java/lang/Exception
    //   67	75	512	java/lang/Exception
    //   80	88	512	java/lang/Exception
    //   88	97	512	java/lang/Exception
    //   101	107	512	java/lang/Exception
    //   530	536	512	java/lang/Exception
    //   120	132	539	java/lang/Exception
    //   141	149	552	java/lang/Exception
    //   154	162	552	java/lang/Exception
    //   162	171	552	java/lang/Exception
    //   175	181	552	java/lang/Exception
    //   565	571	552	java/lang/Exception
    //   200	212	572	java/lang/Exception
    //   294	306	597	java/lang/Exception
    //   383	395	622	java/lang/Exception
    //   652	664	697	java/lang/Exception
    //   453	462	710	java/lang/Exception
    //   466	472	710	java/lang/Exception
    //   673	681	710	java/lang/Exception
    //   686	694	710	java/lang/Exception
    //   723	729	710	java/lang/Exception
  }
  
  public static boolean isManaged(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return false;
    }
    paramContext = ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy().getInstalledManagedApplicationsList();
    if ((paramContext == null) || (paramContext.length == 0)) {
      return false;
    }
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      if (paramString.equals(paramContext[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static boolean isRunning(Context paramContext, String paramString)
    throws SecurityException
  {
    return ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy().isApplicationRunning(paramString);
  }
  
  public static void selectiveWipe(Context paramContext)
    throws SecurityException
  {
    Object localObject3 = (EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy");
    ApplicationPolicy localApplicationPolicy = ((EnterpriseDeviceManager)localObject3).getApplicationPolicy();
    Object localObject2 = paramContext.getPackageManager();
    Object localObject1 = new HashSet();
    ((EnterpriseDeviceManager)localObject3).setAdminRemovable(true);
    localObject3 = localApplicationPolicy.getInstalledApplicationsIDList();
    int j;
    int i;
    if (localObject3 != null)
    {
      j = localObject3.length;
      i = 0;
      if (i < j)
      {
        String str = localObject3[i];
        if (str.startsWith("sec_container_")) {}
        for (;;)
        {
          i += 1;
          break;
          try
          {
            boolean bool = ((PackageManager)localObject2).getApplicationInfo(str, 0).sourceDir.startsWith("/system");
            if (!bool)
            {
              if (!localApplicationPolicy.getApplicationUninstallationEnabled(str)) {
                localApplicationPolicy.setApplicationUninstallationEnabled(str);
              }
              if (PackageInstallation.isManaged(paramContext, str)) {
                ((HashSet)localObject1).add(str);
              }
            }
          }
          catch (Exception localException)
          {
            logger.w(str, localException);
          }
        }
      }
    }
    localObject2 = localApplicationPolicy.getInstalledManagedApplicationsList();
    if ((localObject2 != null) && (localObject2.length > 0))
    {
      j = localObject2.length;
      i = 0;
      while (i < j)
      {
        ((HashSet)localObject1).add(localObject2[i]);
        i += 1;
      }
    }
    if (((HashSet)localObject1).size() == 0) {
      logger.i("no managed app to wipe");
    }
    for (;;)
    {
      return;
      paramContext = TouchDownActivate.getTDPackageName(paramContext);
      localObject1 = ((HashSet)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        if ((paramContext == null) || (!((String)localObject2).equals(paramContext)))
        {
          logger.i("wiping " + (String)localObject2);
          if (!localApplicationPolicy.uninstallApplication((String)localObject2, false)) {
            localApplicationPolicy.wipeApplicationData((String)localObject2);
          }
        }
      }
    }
  }
  
  public static void setEnabled(Context paramContext, String[] paramArrayOfString, boolean paramBoolean)
  {
    Object localObject = ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy();
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      localObject = ((ApplicationPolicy)localObject).getApplicationStateList(bool);
      if (localObject != null) {
        break;
      }
      return;
    }
    int n = localObject.length;
    int i = 0;
    label49:
    String str;
    int m;
    int i1;
    int j;
    if (i < n)
    {
      str = localObject[i];
      m = 1;
      i1 = paramArrayOfString.length;
      j = 0;
    }
    for (;;)
    {
      int k = m;
      if (j < i1)
      {
        if (!str.contains(paramArrayOfString[j])) {
          k = 0;
        }
      }
      else
      {
        if (k != 0) {
          setEnabled(paramContext, str, paramBoolean);
        }
        i += 1;
        break label49;
        break;
      }
      j += 1;
    }
  }
  
  public static boolean setEnabled(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy();
    if (paramBoolean) {
      return paramContext.setEnableApplication(paramString);
    }
    return paramContext.setDisableApplication(paramString);
  }
  
  public static void setManaged(Context paramContext, String paramString)
  {
    ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy().setAsManagedApp(paramString);
  }
  
  public static void setUnmanaged(Context paramContext, String paramString)
  {
    ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy().deleteManagedAppInfo(paramString);
  }
  
  public static void uninstall(Context paramContext, String paramString, boolean paramBoolean)
    throws Exception
  {
    if (paramBoolean)
    {
      if (!Check.haveKnoxAPI(paramContext)) {
        throw new Exception("Knox API not available");
      }
      Container.appRemove(paramContext, paramString);
    }
    do
    {
      do
      {
        return;
        paramContext = ((EnterpriseDeviceManager)paramContext.getSystemService("enterprise_policy")).getApplicationPolicy();
      } while (!paramContext.isApplicationInstalled(paramString));
      paramContext.setApplicationUninstallationEnabled(paramString);
    } while (paramContext.uninstallApplication(paramString, false));
    throw new SecurityException("could not uninstall " + paramString);
  }
}
