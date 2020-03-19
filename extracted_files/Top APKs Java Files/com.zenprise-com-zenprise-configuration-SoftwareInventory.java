package com.zenprise.configuration;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import com.citrix.work.log.Logger;
import com.sparus.npcommon.services.InventoryCommand;
import com.sparus.npcommon.services.InventoryServiceHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SoftwareInventory
{
  private static final String DATA_ROOT = Environment.getDataDirectory().getAbsolutePath();
  private static final String SAMSUNG_CONTAINER_PREFIX = "sec_container_";
  private static Logger logger = Logger.getLogger("SoftwareInventory");
  private Context ctx;
  
  public SoftwareInventory(Context paramContext)
  {
    this.ctx = paramContext;
  }
  
  public static byte[] get(Context paramContext, String paramString)
  {
    InventoryCommand localInventoryCommand = new InventoryCommand(8);
    for (;;)
    {
      try
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        Object localObject1 = new SoftwareInventory(paramContext);
        paramContext = localPackageManager.getPackageInfo(paramString, 0);
        localObject2 = paramContext.applicationInfo;
        if ((localObject2 == null) || (((ApplicationInfo)localObject2).packageName.startsWith("sec_container_")) || (((ApplicationInfo)localObject2).sourceDir.startsWith("/system"))) {
          break;
        }
        localObject2 = paramContext.versionName;
        if (localObject2 == null)
        {
          localObject2 = InventoryCommand.companyNameFromPackage(paramContext.packageName);
          localObject1 = ((SoftwareInventory)localObject1).getAppExtInfo(paramString, paramContext.applicationInfo.dataDir);
          localInventoryCommand.setPackageName(paramString);
          localInventoryCommand.setAppName(localPackageManager.getApplicationLabel(paramContext.applicationInfo).toString());
          localInventoryCommand.setCompanyName((String)localObject2);
          localInventoryCommand.setDir(paramContext.applicationInfo.dataDir);
          localInventoryCommand.setTimeStamp(((AppExtInfo)localObject1).date);
          localInventoryCommand.setProductVersion(paramContext.versionName);
          localInventoryCommand.setNumVersion(paramContext.versionCode);
          localInventoryCommand.setSize(((AppExtInfo)localObject1).appSize);
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Object localObject2;
        localInventoryCommand.setPackageName(paramString);
        localInventoryCommand.setRemovedApp(true);
        localInventoryCommand.setAppName("");
        localInventoryCommand.setCompanyName("");
        localInventoryCommand.setDir("");
        localInventoryCommand.setTimeStamp(0L);
        localInventoryCommand.setProductVersion("");
        localInventoryCommand.setNumVersion(0L);
        localInventoryCommand.setSize(0L);
        continue;
      }
      catch (Exception paramContext)
      {
        logger.e("", paramContext);
        return null;
      }
      try
      {
        paramContext = new ArrayList();
        paramContext.add(localInventoryCommand);
        paramString = new InventoryServiceHandler(localInventoryCommand);
        paramString.setCmdList(paramContext);
        paramContext = paramString.toByteArray();
        return paramContext;
      }
      catch (Exception paramContext)
      {
        logger.e("", paramContext);
        return null;
      }
      if (((String)localObject2).length() > 30) {
        ((String)localObject2).substring(0, 30);
      }
    }
    return null;
  }
  
  private static long getSize(File paramFile)
  {
    long l1 = 0L;
    l2 = l1;
    try
    {
      if (paramFile.isFile())
      {
        l2 = l1;
        return paramFile.length();
      }
      l2 = l1;
      if (!paramFile.isDirectory()) {
        return 0L;
      }
      l2 = l1;
      String[] arrayOfString = paramFile.list();
      l2 = l1;
      if (arrayOfString != null)
      {
        l2 = l1;
        int j = arrayOfString.length;
        int i = 0;
        for (;;)
        {
          l2 = l1;
          if (i >= j) {
            break;
          }
          String str = arrayOfString[i];
          l2 = l1;
          long l3 = getSize(new File(paramFile.getAbsolutePath() + "/" + str));
          l1 += l3;
          i += 1;
        }
      }
      return l2;
    }
    catch (Exception paramFile)
    {
      logger.w("", paramFile);
    }
  }
  
  /* Error */
  public InventoryServiceHandler execute(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   4: invokevirtual 72	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   7: iconst_0
    //   8: invokevirtual 239	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   11: checkcast 166	java/util/ArrayList
    //   14: astore 8
    //   16: aload_0
    //   17: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   20: invokevirtual 72	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   23: astore 10
    //   25: aload 8
    //   27: invokevirtual 242	java/util/ArrayList:size	()I
    //   30: istore_3
    //   31: new 166	java/util/ArrayList
    //   34: dup
    //   35: invokespecial 167	java/util/ArrayList:<init>	()V
    //   38: astore 9
    //   40: aload_0
    //   41: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   44: invokestatic 248	com/citrix/work/database/AndroidDatabaseHelper:getHelper	(Landroid/content/Context;)Lcom/citrix/work/database/AndroidDatabaseHelper;
    //   47: astore 11
    //   49: iconst_0
    //   50: istore_2
    //   51: iload_2
    //   52: iload_3
    //   53: if_icmpge +596 -> 649
    //   56: aload 8
    //   58: iload_2
    //   59: invokevirtual 251	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   62: checkcast 82	android/content/pm/PackageInfo
    //   65: astore 12
    //   67: aload 12
    //   69: getfield 86	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   72: astore 13
    //   74: aload 13
    //   76: ifnull +566 -> 642
    //   79: aload 13
    //   81: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   84: ldc 12
    //   86: invokevirtual 97	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   89: ifne +553 -> 642
    //   92: aload 13
    //   94: getfield 100	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   97: ldc 102
    //   99: invokevirtual 97	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   102: ifne +540 -> 642
    //   105: iload_1
    //   106: iconst_5
    //   107: if_icmpeq +21 -> 128
    //   110: iload_1
    //   111: bipush 6
    //   113: if_icmpeq +15 -> 128
    //   116: iload_1
    //   117: bipush 8
    //   119: if_icmpeq +9 -> 128
    //   122: iload_1
    //   123: bipush 9
    //   125: if_icmpne +443 -> 568
    //   128: aload 12
    //   130: getfield 105	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   133: astore 6
    //   135: aload 6
    //   137: ifnonnull +395 -> 532
    //   140: ldc -56
    //   142: astore 5
    //   144: aload 11
    //   146: aload 13
    //   148: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   151: invokestatic 257	com/citrix/work/database/helpers/MobileAppManagementHelper:getPackageId	(Lcom/citrix/work/database/AndroidDatabaseHelper;Ljava/lang/String;)Ljava/lang/String;
    //   154: astore 7
    //   156: aload 7
    //   158: astore 6
    //   160: aload 7
    //   162: ifnonnull +7 -> 169
    //   165: ldc -56
    //   167: astore 6
    //   169: aload 13
    //   171: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   174: invokestatic 110	com/sparus/npcommon/services/InventoryCommand:companyNameFromPackage	(Ljava/lang/String;)Ljava/lang/String;
    //   177: astore 7
    //   179: new 6	com/zenprise/configuration/SoftwareInventory$AppExtInfo
    //   182: dup
    //   183: aload_0
    //   184: aload 13
    //   186: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   189: aload 13
    //   191: getfield 113	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   194: invokespecial 260	com/zenprise/configuration/SoftwareInventory$AppExtInfo:<init>	(Lcom/zenprise/configuration/SoftwareInventory;Ljava/lang/String;Ljava/lang/String;)V
    //   197: astore 14
    //   199: new 166	java/util/ArrayList
    //   202: dup
    //   203: invokespecial 167	java/util/ArrayList:<init>	()V
    //   206: astore 15
    //   208: iload_1
    //   209: bipush 8
    //   211: if_icmpeq +85 -> 296
    //   214: iload_1
    //   215: bipush 9
    //   217: if_icmpeq +79 -> 296
    //   220: new 63	com/sparus/npcommon/services/InventoryCommand
    //   223: dup
    //   224: iload_1
    //   225: invokespecial 66	com/sparus/npcommon/services/InventoryCommand:<init>	(I)V
    //   228: astore 16
    //   230: aload 16
    //   232: iconst_1
    //   233: invokevirtual 263	com/sparus/npcommon/services/InventoryCommand:setDepth	(I)V
    //   236: aload 16
    //   238: ldc -29
    //   240: invokevirtual 266	com/sparus/npcommon/services/InventoryCommand:setPath	(Ljava/lang/String;)V
    //   243: aload 16
    //   245: aload 14
    //   247: getfield 144	com/zenprise/configuration/SoftwareInventory$AppExtInfo:date	J
    //   250: invokevirtual 148	com/sparus/npcommon/services/InventoryCommand:setTimeStamp	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   253: pop
    //   254: aload 16
    //   256: aload 14
    //   258: getfield 161	com/zenprise/configuration/SoftwareInventory$AppExtInfo:appSize	J
    //   261: invokevirtual 164	com/sparus/npcommon/services/InventoryCommand:setSize	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   264: pop
    //   265: aload 16
    //   267: aload 5
    //   269: invokevirtual 151	com/sparus/npcommon/services/InventoryCommand:setProductVersion	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   272: pop
    //   273: aload 16
    //   275: aload 5
    //   277: invokevirtual 269	com/sparus/npcommon/services/InventoryCommand:setFileVersion	(Ljava/lang/String;)V
    //   280: aload 16
    //   282: aload 7
    //   284: invokevirtual 136	com/sparus/npcommon/services/InventoryCommand:setCompanyName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   287: pop
    //   288: aload 15
    //   290: aload 16
    //   292: invokevirtual 270	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   295: pop
    //   296: new 63	com/sparus/npcommon/services/InventoryCommand
    //   299: dup
    //   300: iload_1
    //   301: invokespecial 66	com/sparus/npcommon/services/InventoryCommand:<init>	(I)V
    //   304: astore 16
    //   306: iload_1
    //   307: bipush 8
    //   309: if_icmpeq +9 -> 318
    //   312: iload_1
    //   313: bipush 9
    //   315: if_icmpne +244 -> 559
    //   318: aload 16
    //   320: iconst_0
    //   321: invokevirtual 273	com/sparus/npcommon/services/InventoryCommand:setBelongsToContainer	(Z)Lcom/sparus/npcommon/services/InventoryCommand;
    //   324: pop
    //   325: aload 16
    //   327: aload 10
    //   329: aload 13
    //   331: invokevirtual 125	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   334: invokeinterface 130 1 0
    //   339: invokevirtual 133	com/sparus/npcommon/services/InventoryCommand:setAppName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   342: pop
    //   343: aload 16
    //   345: aload 7
    //   347: invokevirtual 136	com/sparus/npcommon/services/InventoryCommand:setCompanyName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   350: pop
    //   351: aload 16
    //   353: aload 13
    //   355: getfield 113	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   358: invokevirtual 140	com/sparus/npcommon/services/InventoryCommand:setDir	(Ljava/lang/String;)V
    //   361: aload 16
    //   363: aload 14
    //   365: getfield 144	com/zenprise/configuration/SoftwareInventory$AppExtInfo:date	J
    //   368: invokevirtual 148	com/sparus/npcommon/services/InventoryCommand:setTimeStamp	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   371: pop
    //   372: aload 16
    //   374: aload 13
    //   376: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   379: invokevirtual 121	com/sparus/npcommon/services/InventoryCommand:setPackageName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   382: pop
    //   383: aload 16
    //   385: aload 5
    //   387: invokevirtual 151	com/sparus/npcommon/services/InventoryCommand:setProductVersion	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   390: pop
    //   391: aload 16
    //   393: aload 12
    //   395: getfield 155	android/content/pm/PackageInfo:versionCode	I
    //   398: i2l
    //   399: invokevirtual 158	com/sparus/npcommon/services/InventoryCommand:setNumVersion	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   402: pop
    //   403: aload 16
    //   405: aload 14
    //   407: getfield 161	com/zenprise/configuration/SoftwareInventory$AppExtInfo:appSize	J
    //   410: invokevirtual 164	com/sparus/npcommon/services/InventoryCommand:setSize	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   413: pop
    //   414: aload 16
    //   416: aload 6
    //   418: invokevirtual 276	com/sparus/npcommon/services/InventoryCommand:setPackageId	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   421: pop
    //   422: aload 9
    //   424: aload 16
    //   426: invokeinterface 173 2 0
    //   431: pop
    //   432: getstatic 28	com/zenprise/configuration/SoftwareInventory:logger	Lcom/citrix/work/log/Logger;
    //   435: new 220	java/lang/StringBuilder
    //   438: dup
    //   439: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   442: ldc_w 278
    //   445: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: iload_1
    //   449: invokevirtual 281	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   452: ldc_w 283
    //   455: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   458: aload 13
    //   460: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   463: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: ldc_w 285
    //   469: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: aload 12
    //   474: getfield 155	android/content/pm/PackageInfo:versionCode	I
    //   477: invokevirtual 281	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   480: ldc_w 285
    //   483: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: aload 6
    //   488: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   494: invokevirtual 288	com/citrix/work/log/Logger:d	(Ljava/lang/String;)V
    //   497: aload 15
    //   499: invokevirtual 292	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   502: astore 5
    //   504: aload 5
    //   506: invokeinterface 297 1 0
    //   511: ifeq +131 -> 642
    //   514: aload 9
    //   516: aload 5
    //   518: invokeinterface 301 1 0
    //   523: invokeinterface 173 2 0
    //   528: pop
    //   529: goto -25 -> 504
    //   532: aload 6
    //   534: astore 5
    //   536: aload 6
    //   538: invokevirtual 190	java/lang/String:length	()I
    //   541: bipush 30
    //   543: if_icmple -399 -> 144
    //   546: aload 6
    //   548: iconst_0
    //   549: bipush 30
    //   551: invokevirtual 194	java/lang/String:substring	(II)Ljava/lang/String;
    //   554: astore 5
    //   556: goto -412 -> 144
    //   559: aload 16
    //   561: iconst_0
    //   562: invokevirtual 263	com/sparus/npcommon/services/InventoryCommand:setDepth	(I)V
    //   565: goto -240 -> 325
    //   568: new 63	com/sparus/npcommon/services/InventoryCommand
    //   571: dup
    //   572: iload_1
    //   573: invokespecial 66	com/sparus/npcommon/services/InventoryCommand:<init>	(I)V
    //   576: astore 5
    //   578: aload 5
    //   580: iconst_0
    //   581: invokevirtual 263	com/sparus/npcommon/services/InventoryCommand:setDepth	(I)V
    //   584: aload 5
    //   586: new 220	java/lang/StringBuilder
    //   589: dup
    //   590: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   593: aload 13
    //   595: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   598: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: ldc_w 303
    //   604: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: aload 12
    //   609: getfield 155	android/content/pm/PackageInfo:versionCode	I
    //   612: invokevirtual 281	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   615: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   618: invokevirtual 133	com/sparus/npcommon/services/InventoryCommand:setAppName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   621: pop
    //   622: aload 5
    //   624: aload 13
    //   626: getfield 113	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   629: invokevirtual 140	com/sparus/npcommon/services/InventoryCommand:setDir	(Ljava/lang/String;)V
    //   632: aload 9
    //   634: aload 5
    //   636: invokeinterface 173 2 0
    //   641: pop
    //   642: iload_2
    //   643: iconst_1
    //   644: iadd
    //   645: istore_2
    //   646: goto -595 -> 51
    //   649: aload 11
    //   651: ifnull +8 -> 659
    //   654: aload 11
    //   656: invokevirtual 306	com/citrix/work/database/AndroidDatabaseHelper:close	()V
    //   659: iload_1
    //   660: bipush 8
    //   662: if_icmpeq +9 -> 671
    //   665: iload_1
    //   666: bipush 9
    //   668: if_icmpne +295 -> 963
    //   671: aload_0
    //   672: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   675: invokestatic 312	com/zenprise/samsungmdm/Check:haveKnoxAPI	(Landroid/content/Context;)Z
    //   678: ifeq +285 -> 963
    //   681: aload_0
    //   682: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   685: invokestatic 317	com/zenprise/samsungmdm/Container:exists	(Landroid/content/Context;)Z
    //   688: ifeq +275 -> 963
    //   691: aload_0
    //   692: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   695: invokestatic 321	com/zenprise/samsungmdm/Container:getId	(Landroid/content/Context;)I
    //   698: istore_3
    //   699: aload_0
    //   700: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   703: invokestatic 325	com/zenprise/samsungmdm/Container:getSoftwareInventory	(Landroid/content/Context;)[Ljava/lang/String;
    //   706: astore 11
    //   708: aload 11
    //   710: ifnull +253 -> 963
    //   713: aload 11
    //   715: arraylength
    //   716: ifle +247 -> 963
    //   719: aload 11
    //   721: arraylength
    //   722: istore 4
    //   724: iconst_0
    //   725: istore_2
    //   726: iload_2
    //   727: iload 4
    //   729: if_icmpge +234 -> 963
    //   732: aload 11
    //   734: iload_2
    //   735: aaload
    //   736: astore 12
    //   738: aload_0
    //   739: getfield 49	com/zenprise/configuration/SoftwareInventory:ctx	Landroid/content/Context;
    //   742: aload 12
    //   744: iload_3
    //   745: invokestatic 329	com/zenprise/samsungmdm/Container:isAppEnabled	(Landroid/content/Context;Ljava/lang/String;I)Z
    //   748: ifne +6 -> 754
    //   751: goto +363 -> 1114
    //   754: new 63	com/sparus/npcommon/services/InventoryCommand
    //   757: dup
    //   758: iload_1
    //   759: invokespecial 66	com/sparus/npcommon/services/InventoryCommand:<init>	(I)V
    //   762: astore 13
    //   764: aload 13
    //   766: iconst_1
    //   767: invokevirtual 273	com/sparus/npcommon/services/InventoryCommand:setBelongsToContainer	(Z)Lcom/sparus/npcommon/services/InventoryCommand;
    //   770: pop
    //   771: aload 13
    //   773: aload 12
    //   775: invokevirtual 121	com/sparus/npcommon/services/InventoryCommand:setPackageName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   778: pop
    //   779: aload 13
    //   781: aload 12
    //   783: invokestatic 110	com/sparus/npcommon/services/InventoryCommand:companyNameFromPackage	(Ljava/lang/String;)Ljava/lang/String;
    //   786: invokevirtual 136	com/sparus/npcommon/services/InventoryCommand:setCompanyName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   789: pop
    //   790: new 220	java/lang/StringBuilder
    //   793: dup
    //   794: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   797: ldc 12
    //   799: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: iload_3
    //   803: invokevirtual 281	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   806: ldc_w 331
    //   809: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   812: aload 12
    //   814: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   820: astore 5
    //   822: aload 10
    //   824: aload 5
    //   826: iconst_0
    //   827: invokevirtual 335	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   830: astore 6
    //   832: aload 10
    //   834: aload 5
    //   836: iconst_0
    //   837: invokevirtual 80	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   840: astore 7
    //   842: aload 13
    //   844: aload 10
    //   846: aload 6
    //   848: invokevirtual 125	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   851: invokeinterface 130 1 0
    //   856: invokevirtual 133	com/sparus/npcommon/services/InventoryCommand:setAppName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   859: pop
    //   860: aload 13
    //   862: aload 7
    //   864: getfield 155	android/content/pm/PackageInfo:versionCode	I
    //   867: i2l
    //   868: invokevirtual 158	com/sparus/npcommon/services/InventoryCommand:setNumVersion	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   871: pop
    //   872: aload 7
    //   874: getfield 105	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   877: astore 8
    //   879: aload 8
    //   881: ifnonnull +134 -> 1015
    //   884: ldc -56
    //   886: astore 5
    //   888: aload 13
    //   890: aload 5
    //   892: invokevirtual 151	com/sparus/npcommon/services/InventoryCommand:setProductVersion	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   895: pop
    //   896: aload 13
    //   898: aload 7
    //   900: getfield 338	android/content/pm/PackageInfo:lastUpdateTime	J
    //   903: ldc2_w 339
    //   906: ldiv
    //   907: invokevirtual 148	com/sparus/npcommon/services/InventoryCommand:setTimeStamp	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   910: pop
    //   911: aload 13
    //   913: new 6	com/zenprise/configuration/SoftwareInventory$AppExtInfo
    //   916: dup
    //   917: aload_0
    //   918: aload 6
    //   920: getfield 91	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   923: aload 6
    //   925: getfield 113	android/content/pm/ApplicationInfo:dataDir	Ljava/lang/String;
    //   928: invokespecial 260	com/zenprise/configuration/SoftwareInventory$AppExtInfo:<init>	(Lcom/zenprise/configuration/SoftwareInventory;Ljava/lang/String;Ljava/lang/String;)V
    //   931: getfield 161	com/zenprise/configuration/SoftwareInventory$AppExtInfo:appSize	J
    //   934: invokevirtual 164	com/sparus/npcommon/services/InventoryCommand:setSize	(J)Lcom/sparus/npcommon/services/InventoryCommand;
    //   937: pop
    //   938: aload 9
    //   940: aload 13
    //   942: invokeinterface 173 2 0
    //   947: pop
    //   948: goto +166 -> 1114
    //   951: astore 5
    //   953: getstatic 28	com/zenprise/configuration/SoftwareInventory:logger	Lcom/citrix/work/log/Logger;
    //   956: ldc -56
    //   958: aload 5
    //   960: invokevirtual 233	com/citrix/work/log/Logger:w	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   963: new 175	com/sparus/npcommon/services/InventoryServiceHandler
    //   966: dup
    //   967: new 63	com/sparus/npcommon/services/InventoryCommand
    //   970: dup
    //   971: iload_1
    //   972: invokespecial 66	com/sparus/npcommon/services/InventoryCommand:<init>	(I)V
    //   975: invokespecial 178	com/sparus/npcommon/services/InventoryServiceHandler:<init>	(Lcom/sparus/npcommon/services/InventoryCommand;)V
    //   978: astore 5
    //   980: aload 5
    //   982: aload 9
    //   984: invokevirtual 182	com/sparus/npcommon/services/InventoryServiceHandler:setCmdList	(Ljava/util/List;)V
    //   987: aload 5
    //   989: areturn
    //   990: astore 5
    //   992: aload 10
    //   994: aload 12
    //   996: iconst_0
    //   997: invokevirtual 335	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1000: astore 6
    //   1002: aload 10
    //   1004: aload 12
    //   1006: iconst_0
    //   1007: invokevirtual 80	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1010: astore 7
    //   1012: goto -170 -> 842
    //   1015: aload 8
    //   1017: astore 5
    //   1019: aload 8
    //   1021: invokevirtual 190	java/lang/String:length	()I
    //   1024: bipush 30
    //   1026: if_icmple -138 -> 888
    //   1029: aload 8
    //   1031: iconst_0
    //   1032: bipush 30
    //   1034: invokevirtual 194	java/lang/String:substring	(II)Ljava/lang/String;
    //   1037: astore 5
    //   1039: goto -151 -> 888
    //   1042: astore 5
    //   1044: getstatic 28	com/zenprise/configuration/SoftwareInventory:logger	Lcom/citrix/work/log/Logger;
    //   1047: new 220	java/lang/StringBuilder
    //   1050: dup
    //   1051: invokespecial 221	java/lang/StringBuilder:<init>	()V
    //   1054: aload 12
    //   1056: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1059: ldc_w 342
    //   1062: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1065: aload 5
    //   1067: invokevirtual 345	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1070: invokevirtual 225	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1073: invokevirtual 228	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1076: invokevirtual 347	com/citrix/work/log/Logger:w	(Ljava/lang/String;)V
    //   1079: aload 13
    //   1081: invokevirtual 350	com/sparus/npcommon/services/InventoryCommand:getAppName	()Ljava/lang/String;
    //   1084: ifnonnull +11 -> 1095
    //   1087: aload 13
    //   1089: aload 12
    //   1091: invokevirtual 133	com/sparus/npcommon/services/InventoryCommand:setAppName	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   1094: pop
    //   1095: aload 13
    //   1097: invokevirtual 353	com/sparus/npcommon/services/InventoryCommand:getProductVersion	()Ljava/lang/String;
    //   1100: ifnonnull -162 -> 938
    //   1103: aload 13
    //   1105: ldc -56
    //   1107: invokevirtual 151	com/sparus/npcommon/services/InventoryCommand:setProductVersion	(Ljava/lang/String;)Lcom/sparus/npcommon/services/InventoryCommand;
    //   1110: pop
    //   1111: goto -173 -> 938
    //   1114: iload_2
    //   1115: iconst_1
    //   1116: iadd
    //   1117: istore_2
    //   1118: goto -392 -> 726
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1121	0	this	SoftwareInventory
    //   0	1121	1	paramInt	int
    //   50	1068	2	i	int
    //   30	773	3	j	int
    //   722	8	4	k	int
    //   142	749	5	localObject1	Object
    //   951	8	5	localException1	Exception
    //   978	10	5	localInventoryServiceHandler	InventoryServiceHandler
    //   990	1	5	localException2	Exception
    //   1017	21	5	localObject2	Object
    //   1042	24	5	localException3	Exception
    //   133	868	6	localObject3	Object
    //   154	857	7	localObject4	Object
    //   14	1016	8	localObject5	Object
    //   38	945	9	localArrayList1	ArrayList
    //   23	980	10	localPackageManager	PackageManager
    //   47	686	11	localObject6	Object
    //   65	1025	12	localPackageInfo	PackageInfo
    //   72	1032	13	localObject7	Object
    //   197	209	14	localAppExtInfo	AppExtInfo
    //   206	292	15	localArrayList2	ArrayList
    //   228	332	16	localInventoryCommand	InventoryCommand
    // Exception table:
    //   from	to	target	type
    //   699	708	951	java/lang/Exception
    //   713	724	951	java/lang/Exception
    //   738	751	951	java/lang/Exception
    //   754	822	951	java/lang/Exception
    //   938	948	951	java/lang/Exception
    //   1044	1095	951	java/lang/Exception
    //   1095	1111	951	java/lang/Exception
    //   822	842	990	java/lang/Exception
    //   842	879	1042	java/lang/Exception
    //   888	938	1042	java/lang/Exception
    //   992	1012	1042	java/lang/Exception
    //   1019	1039	1042	java/lang/Exception
  }
  
  protected AppExtInfo getAppExtInfo(String paramString1, String paramString2)
  {
    return new AppExtInfo(paramString1, paramString2);
  }
  
  protected class AppExtInfo
  {
    protected long appSize = 0L;
    protected long date = 0L;
    
    AppExtInfo(String paramString1, String paramString2)
    {
      this$1 = SoftwareInventory.DATA_ROOT + "/app/" + paramString1;
      String str1 = SoftwareInventory.DATA_ROOT + "/dalvik-cache/data@app@" + paramString1;
      String str2 = SoftwareInventory.DATA_ROOT + "/app-private/" + paramString1;
      paramString1 = SoftwareInventory.DATA_ROOT + "/dalvik-cache/data@app-private@" + paramString1;
      File[] arrayOfFile = new File[16];
      arrayOfFile[0] = new File(SoftwareInventory.this + ".apk");
      arrayOfFile[1] = new File(SoftwareInventory.this + "-1.apk");
      arrayOfFile[2] = new File(SoftwareInventory.this + "-2.apk");
      arrayOfFile[3] = new File(str1 + ".apk@classes.dex");
      arrayOfFile[4] = new File(str1 + "-1.apk@classes.dex");
      arrayOfFile[5] = new File(str1 + "-2.apk@classes.dex");
      arrayOfFile[6] = new File(str2 + ".apk");
      arrayOfFile[7] = new File(str2 + "-1.apk");
      arrayOfFile[8] = new File(str2 + "-2.apk");
      arrayOfFile[9] = new File(paramString1 + ".apk@classes.dex");
      arrayOfFile[10] = new File(paramString1 + "-1.apk@classes.dex");
      arrayOfFile[11] = new File(paramString1 + "-2.apk@classes.dex");
      arrayOfFile[12] = new File(SoftwareInventory.this + ".zip");
      arrayOfFile[13] = new File(SoftwareInventory.this + "-1.zip");
      arrayOfFile[14] = new File(SoftwareInventory.this + "-2.zip");
      arrayOfFile[15] = new File(paramString2);
      int i = 0;
      if (i < arrayOfFile.length)
      {
        if (!arrayOfFile[i].exists()) {}
        for (;;)
        {
          i += 1;
          break;
          if ((this.date == 0L) && (arrayOfFile[i].lastModified() / 1000L != 0L)) {
            this.date = (arrayOfFile[i].lastModified() / 1000L);
          }
          long l = SoftwareInventory.getSize(arrayOfFile[i]);
          this.appSize += l;
        }
      }
    }
  }
}
