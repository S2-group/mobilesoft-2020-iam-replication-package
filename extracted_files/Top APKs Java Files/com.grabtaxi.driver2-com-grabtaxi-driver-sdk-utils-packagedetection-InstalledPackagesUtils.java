package com.grabtaxi.driver.sdk.utils.packagedetection;

import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.grabtaxi.driver.sdk.utils.g;
import java.util.ArrayList;
import java.util.List;

public class InstalledPackagesUtils
{
  private static final int ACTIVITY_MATCH_THRESHOLD = 5;
  private static final String TAG = InstalledPackagesUtils.class.getSimpleName();
  
  public InstalledPackagesUtils() {}
  
  private static void addPackageToAPackageInfoList(List<APackageInfo> paramList, PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    if ((paramPackageManager == null) || (paramPackageInfo == null)) {
      return;
    }
    Object localObject = paramList;
    if (paramList == null) {
      localObject = new ArrayList();
    }
    try
    {
      paramList = new APackageInfo();
      paramList.setAppName(paramPackageInfo.packageName);
      if (paramPackageInfo.applicationInfo != null)
      {
        String str = paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString();
        if (!TextUtils.isEmpty(str)) {
          paramList.setAppName(str);
        }
        paramList.setIcon(paramPackageInfo.applicationInfo.loadIcon(paramPackageManager));
        paramList.setPackageName(paramPackageInfo.applicationInfo.packageName);
      }
      paramPackageManager = paramPackageInfo.activities;
      if ((paramPackageManager != null) && (paramPackageManager.length > 0))
      {
        int j = paramPackageManager.length;
        int i = 0;
        while (i < j)
        {
          paramPackageInfo = paramPackageManager[i];
          paramList.getActivities().add(paramPackageInfo.name);
          i += 1;
        }
      }
      ((List)localObject).add(paramList);
      return;
    }
    catch (Exception paramList)
    {
      g.c(TAG, "The specific package cannot be found");
    }
  }
  
  /* Error */
  public static boolean findPackageByRegex(List<APackageInfo> paramList, List<String> paramList1)
  {
    // Byte code:
    //   0: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   3: new 112	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   10: ldc 115
    //   12: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   18: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   21: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   24: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   30: aload_0
    //   31: ifnull +25 -> 56
    //   34: aload_0
    //   35: invokeinterface 139 1 0
    //   40: ifne +16 -> 56
    //   43: aload_1
    //   44: ifnull +12 -> 56
    //   47: aload_1
    //   48: invokeinterface 139 1 0
    //   53: ifeq +5 -> 58
    //   56: iconst_0
    //   57: ireturn
    //   58: aload_1
    //   59: invokeinterface 143 1 0
    //   64: astore_3
    //   65: aload_3
    //   66: invokeinterface 148 1 0
    //   71: ifeq +358 -> 429
    //   74: aload_3
    //   75: invokeinterface 152 1 0
    //   80: checkcast 154	java/lang/String
    //   83: astore 4
    //   85: aload 4
    //   87: invokestatic 160	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   90: astore 5
    //   92: aload_0
    //   93: invokeinterface 143 1 0
    //   98: astore 6
    //   100: aload 6
    //   102: invokeinterface 148 1 0
    //   107: ifeq -42 -> 65
    //   110: aload 6
    //   112: invokeinterface 152 1 0
    //   117: checkcast 32	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo
    //   120: astore 7
    //   122: aload 5
    //   124: aload 7
    //   126: invokevirtual 163	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo:getAppName	()Ljava/lang/String;
    //   129: invokevirtual 167	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   132: astore 8
    //   134: aload 5
    //   136: aload 7
    //   138: invokevirtual 170	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo:getPackageName	()Ljava/lang/String;
    //   141: invokevirtual 167	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   144: astore 9
    //   146: aload 8
    //   148: invokevirtual 175	java/util/regex/Matcher:matches	()Z
    //   151: ifne +13 -> 164
    //   154: aload 9
    //   156: invokevirtual 175	java/util/regex/Matcher:matches	()Z
    //   159: istore_2
    //   160: iload_2
    //   161: ifeq +47 -> 208
    //   164: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   167: new 112	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   174: ldc -79
    //   176: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   179: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   182: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   185: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   188: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   191: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   194: aload_0
    //   195: invokeinterface 180 1 0
    //   200: aload_1
    //   201: invokeinterface 180 1 0
    //   206: iconst_1
    //   207: ireturn
    //   208: aload 7
    //   210: invokevirtual 83	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo:getActivities	()Ljava/util/List;
    //   213: astore 7
    //   215: aload 7
    //   217: ifnull -117 -> 100
    //   220: aload 7
    //   222: invokeinterface 139 1 0
    //   227: ifne -127 -> 100
    //   230: aload 7
    //   232: invokeinterface 143 1 0
    //   237: astore 7
    //   239: aload 7
    //   241: invokeinterface 148 1 0
    //   246: ifeq -146 -> 100
    //   249: aload 5
    //   251: aload 7
    //   253: invokeinterface 152 1 0
    //   258: checkcast 154	java/lang/String
    //   261: invokevirtual 167	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   264: invokevirtual 175	java/util/regex/Matcher:matches	()Z
    //   267: istore_2
    //   268: iload_2
    //   269: ifeq -30 -> 239
    //   272: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   275: new 112	java/lang/StringBuilder
    //   278: dup
    //   279: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   282: ldc -79
    //   284: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   290: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   293: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   296: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   299: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   302: aload_0
    //   303: invokeinterface 180 1 0
    //   308: aload_1
    //   309: invokeinterface 180 1 0
    //   314: iconst_1
    //   315: ireturn
    //   316: astore 5
    //   318: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   321: new 112	java/lang/StringBuilder
    //   324: dup
    //   325: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   328: ldc -74
    //   330: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: aload 4
    //   335: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   341: aload 5
    //   343: iconst_1
    //   344: invokestatic 186	com/grabtaxi/driver/sdk/utils/g:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   347: goto -282 -> 65
    //   350: astore_3
    //   351: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   354: new 112	java/lang/StringBuilder
    //   357: dup
    //   358: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   361: ldc -79
    //   363: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   369: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   372: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   375: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   378: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   381: aload_0
    //   382: invokeinterface 180 1 0
    //   387: aload_1
    //   388: invokeinterface 180 1 0
    //   393: aload_3
    //   394: athrow
    //   395: astore 5
    //   397: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   400: new 112	java/lang/StringBuilder
    //   403: dup
    //   404: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   407: ldc -68
    //   409: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: aload 4
    //   414: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   420: aload 5
    //   422: iconst_1
    //   423: invokestatic 186	com/grabtaxi/driver/sdk/utils/g:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   426: goto -361 -> 65
    //   429: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   432: new 112	java/lang/StringBuilder
    //   435: dup
    //   436: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   439: ldc -79
    //   441: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   447: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   450: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   453: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: aload_0
    //   460: invokeinterface 180 1 0
    //   465: aload_1
    //   466: invokeinterface 180 1 0
    //   471: iconst_0
    //   472: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	473	0	paramList	List<APackageInfo>
    //   0	473	1	paramList1	List<String>
    //   159	110	2	bool	boolean
    //   64	11	3	localIterator1	java.util.Iterator
    //   350	44	3	localObject1	Object
    //   83	330	4	str	String
    //   90	160	5	localPattern	java.util.regex.Pattern
    //   316	26	5	localPatternSyntaxException	java.util.regex.PatternSyntaxException
    //   395	26	5	localIllegalArgumentException	IllegalArgumentException
    //   98	13	6	localIterator2	java.util.Iterator
    //   120	132	7	localObject2	Object
    //   132	15	8	localMatcher1	java.util.regex.Matcher
    //   144	11	9	localMatcher2	java.util.regex.Matcher
    // Exception table:
    //   from	to	target	type
    //   85	100	316	java/util/regex/PatternSyntaxException
    //   100	160	316	java/util/regex/PatternSyntaxException
    //   208	215	316	java/util/regex/PatternSyntaxException
    //   220	239	316	java/util/regex/PatternSyntaxException
    //   239	268	316	java/util/regex/PatternSyntaxException
    //   58	65	350	finally
    //   65	85	350	finally
    //   85	100	350	finally
    //   100	160	350	finally
    //   208	215	350	finally
    //   220	239	350	finally
    //   239	268	350	finally
    //   318	347	350	finally
    //   397	426	350	finally
    //   85	100	395	java/lang/IllegalArgumentException
    //   100	160	395	java/lang/IllegalArgumentException
    //   208	215	395	java/lang/IllegalArgumentException
    //   220	239	395	java/lang/IllegalArgumentException
    //   239	268	395	java/lang/IllegalArgumentException
  }
  
  /* Error */
  public static List<APackageInfo> findPackageInfoByRegex(List<APackageInfo> paramList, List<String> paramList1)
  {
    // Byte code:
    //   0: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   3: new 112	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   10: ldc 115
    //   12: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   18: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   21: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   24: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   30: new 29	java/util/ArrayList
    //   33: dup
    //   34: invokespecial 30	java/util/ArrayList:<init>	()V
    //   37: astore 4
    //   39: aload_0
    //   40: ifnull +25 -> 65
    //   43: aload_0
    //   44: invokeinterface 139 1 0
    //   49: ifne +16 -> 65
    //   52: aload_1
    //   53: ifnull +12 -> 65
    //   56: aload_1
    //   57: invokeinterface 139 1 0
    //   62: ifeq +6 -> 68
    //   65: aload 4
    //   67: areturn
    //   68: aload_1
    //   69: invokeinterface 143 1 0
    //   74: astore 5
    //   76: aload 5
    //   78: invokeinterface 148 1 0
    //   83: ifeq +289 -> 372
    //   86: aload 5
    //   88: invokeinterface 152 1 0
    //   93: checkcast 154	java/lang/String
    //   96: astore 6
    //   98: aload 6
    //   100: invokestatic 160	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   103: astore 7
    //   105: aload_0
    //   106: invokeinterface 143 1 0
    //   111: astore 8
    //   113: aload 8
    //   115: invokeinterface 148 1 0
    //   120: ifeq -44 -> 76
    //   123: aload 8
    //   125: invokeinterface 152 1 0
    //   130: checkcast 32	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo
    //   133: astore 9
    //   135: iconst_0
    //   136: istore_3
    //   137: aload 7
    //   139: aload 9
    //   141: invokevirtual 163	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo:getAppName	()Ljava/lang/String;
    //   144: invokevirtual 167	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   147: astore 10
    //   149: aload 7
    //   151: aload 9
    //   153: invokevirtual 170	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo:getPackageName	()Ljava/lang/String;
    //   156: invokevirtual 167	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   159: astore 11
    //   161: aload 10
    //   163: invokevirtual 175	java/util/regex/Matcher:matches	()Z
    //   166: ifne +11 -> 177
    //   169: aload 11
    //   171: invokevirtual 175	java/util/regex/Matcher:matches	()Z
    //   174: ifeq +97 -> 271
    //   177: aload 4
    //   179: aload 9
    //   181: invokeinterface 94 2 0
    //   186: pop
    //   187: goto -111 -> 76
    //   190: astore 7
    //   192: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   195: new 112	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   202: ldc -74
    //   204: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: aload 6
    //   209: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   215: aload 7
    //   217: iconst_1
    //   218: invokestatic 186	com/grabtaxi/driver/sdk/utils/g:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   221: goto -145 -> 76
    //   224: astore 4
    //   226: aload_0
    //   227: invokeinterface 180 1 0
    //   232: aload_1
    //   233: invokeinterface 180 1 0
    //   238: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   241: new 112	java/lang/StringBuilder
    //   244: dup
    //   245: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   248: ldc -79
    //   250: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   256: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   259: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   262: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   265: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   268: aload 4
    //   270: athrow
    //   271: aload 9
    //   273: invokevirtual 83	com/grabtaxi/driver/sdk/utils/packagedetection/APackageInfo:getActivities	()Ljava/util/List;
    //   276: invokeinterface 143 1 0
    //   281: astore 10
    //   283: iload_3
    //   284: istore_2
    //   285: aload 10
    //   287: invokeinterface 148 1 0
    //   292: ifeq +28 -> 320
    //   295: aload 7
    //   297: aload 10
    //   299: invokeinterface 152 1 0
    //   304: checkcast 154	java/lang/String
    //   307: invokevirtual 167	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   310: invokevirtual 175	java/util/regex/Matcher:matches	()Z
    //   313: ifeq -30 -> 283
    //   316: iconst_0
    //   317: iconst_1
    //   318: iadd
    //   319: istore_2
    //   320: iload_2
    //   321: iconst_5
    //   322: if_icmplt -209 -> 113
    //   325: aload 4
    //   327: aload 9
    //   329: invokeinterface 94 2 0
    //   334: pop
    //   335: goto -259 -> 76
    //   338: astore 7
    //   340: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   343: new 112	java/lang/StringBuilder
    //   346: dup
    //   347: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   350: ldc -68
    //   352: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   355: aload 6
    //   357: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   360: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   363: aload 7
    //   365: iconst_1
    //   366: invokestatic 186	com/grabtaxi/driver/sdk/utils/g:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;Z)V
    //   369: goto -293 -> 76
    //   372: aload_0
    //   373: invokeinterface 180 1 0
    //   378: aload_1
    //   379: invokeinterface 180 1 0
    //   384: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   387: new 112	java/lang/StringBuilder
    //   390: dup
    //   391: invokespecial 113	java/lang/StringBuilder:<init>	()V
    //   394: ldc -79
    //   396: invokevirtual 119	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: invokestatic 125	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   402: invokevirtual 129	java/util/Calendar:getTimeInMillis	()J
    //   405: invokevirtual 132	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   408: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   411: invokestatic 136	com/grabtaxi/driver/sdk/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   414: aload 4
    //   416: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	417	0	paramList	List<APackageInfo>
    //   0	417	1	paramList1	List<String>
    //   284	39	2	i	int
    //   136	148	3	j	int
    //   37	141	4	localArrayList	ArrayList
    //   224	191	4	localList	List<APackageInfo>
    //   74	13	5	localIterator1	java.util.Iterator
    //   96	260	6	str	String
    //   103	47	7	localPattern	java.util.regex.Pattern
    //   190	106	7	localPatternSyntaxException	java.util.regex.PatternSyntaxException
    //   338	26	7	localIllegalArgumentException	IllegalArgumentException
    //   111	13	8	localIterator2	java.util.Iterator
    //   133	195	9	localAPackageInfo	APackageInfo
    //   147	151	10	localObject	Object
    //   159	11	11	localMatcher	java.util.regex.Matcher
    // Exception table:
    //   from	to	target	type
    //   98	113	190	java/util/regex/PatternSyntaxException
    //   113	135	190	java/util/regex/PatternSyntaxException
    //   137	177	190	java/util/regex/PatternSyntaxException
    //   177	187	190	java/util/regex/PatternSyntaxException
    //   271	283	190	java/util/regex/PatternSyntaxException
    //   285	316	190	java/util/regex/PatternSyntaxException
    //   325	335	190	java/util/regex/PatternSyntaxException
    //   68	76	224	finally
    //   76	98	224	finally
    //   98	113	224	finally
    //   113	135	224	finally
    //   137	177	224	finally
    //   177	187	224	finally
    //   192	221	224	finally
    //   271	283	224	finally
    //   285	316	224	finally
    //   325	335	224	finally
    //   340	369	224	finally
    //   98	113	338	java/lang/IllegalArgumentException
    //   113	135	338	java/lang/IllegalArgumentException
    //   137	177	338	java/lang/IllegalArgumentException
    //   177	187	338	java/lang/IllegalArgumentException
    //   271	283	338	java/lang/IllegalArgumentException
    //   285	316	338	java/lang/IllegalArgumentException
    //   325	335	338	java/lang/IllegalArgumentException
  }
  
  /* Error */
  public static List<APackageInfo> getAllInstalledPackagesWithFallback(PackageManager paramPackageManager)
  {
    // Byte code:
    //   0: new 29	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 30	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: iconst_0
    //   10: invokevirtual 204	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   13: astore_3
    //   14: aload_3
    //   15: ifnull +279 -> 294
    //   18: aload_3
    //   19: invokeinterface 139 1 0
    //   24: ifeq +6 -> 30
    //   27: goto +267 -> 294
    //   30: new 29	java/util/ArrayList
    //   33: dup
    //   34: aload_3
    //   35: invokeinterface 208 1 0
    //   40: invokespecial 211	java/util/ArrayList:<init>	(I)V
    //   43: astore_2
    //   44: aload_3
    //   45: invokeinterface 143 1 0
    //   50: astore_1
    //   51: aload_1
    //   52: invokeinterface 148 1 0
    //   57: ifeq +161 -> 218
    //   60: aload_1
    //   61: invokeinterface 152 1 0
    //   66: checkcast 35	android/content/pm/PackageInfo
    //   69: astore_3
    //   70: aload_0
    //   71: aload_3
    //   72: getfield 46	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   75: getfield 72	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   78: iconst_1
    //   79: invokevirtual 215	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   82: astore 4
    //   84: aload 4
    //   86: ifnull +123 -> 209
    //   89: aload_2
    //   90: aload_0
    //   91: aload 4
    //   93: invokestatic 217	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:addPackageToAPackageInfoList	(Ljava/util/List;Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)V
    //   96: goto -45 -> 51
    //   99: astore_3
    //   100: getstatic 19	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:TAG	Ljava/lang/String;
    //   103: ldc 96
    //   105: invokestatic 102	com/grabtaxi/driver/sdk/utils/g:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   108: goto -57 -> 51
    //   111: astore_1
    //   112: aconst_null
    //   113: astore_3
    //   114: aconst_null
    //   115: astore 4
    //   117: aload_3
    //   118: astore_1
    //   119: invokestatic 223	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   122: ldc -31
    //   124: invokevirtual 229	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   127: astore 5
    //   129: aload_3
    //   130: astore_1
    //   131: new 231	java/io/BufferedReader
    //   134: dup
    //   135: new 233	java/io/InputStreamReader
    //   138: dup
    //   139: aload 5
    //   141: invokevirtual 239	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   144: getstatic 245	com/grabtaxi/driver/sdk/c:b	Ljava/nio/charset/Charset;
    //   147: invokespecial 248	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   150: invokespecial 251	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   153: astore_3
    //   154: aload_3
    //   155: invokevirtual 254	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   158: astore_1
    //   159: aload_1
    //   160: ifnull +60 -> 220
    //   163: aload_2
    //   164: aload_0
    //   165: aload_0
    //   166: aload_1
    //   167: aload_1
    //   168: bipush 58
    //   170: invokevirtual 258	java/lang/String:indexOf	(I)I
    //   173: iconst_1
    //   174: iadd
    //   175: invokevirtual 262	java/lang/String:substring	(I)Ljava/lang/String;
    //   178: iconst_0
    //   179: invokevirtual 215	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   182: invokestatic 217	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:addPackageToAPackageInfoList	(Ljava/util/List;Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)V
    //   185: goto -31 -> 154
    //   188: astore_1
    //   189: aload_3
    //   190: astore_0
    //   191: aload_1
    //   192: astore_3
    //   193: aload_0
    //   194: astore_1
    //   195: aload_3
    //   196: invokevirtual 265	java/lang/Exception:printStackTrace	()V
    //   199: aload_0
    //   200: ifnull +7 -> 207
    //   203: aload_0
    //   204: invokevirtual 268	java/io/BufferedReader:close	()V
    //   207: aload_2
    //   208: areturn
    //   209: aload_2
    //   210: aload_0
    //   211: aload_3
    //   212: invokestatic 217	com/grabtaxi/driver/sdk/utils/packagedetection/InstalledPackagesUtils:addPackageToAPackageInfoList	(Ljava/util/List;Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)V
    //   215: goto -164 -> 51
    //   218: aload_2
    //   219: areturn
    //   220: aload 5
    //   222: invokevirtual 271	java/lang/Process:waitFor	()I
    //   225: pop
    //   226: aload_3
    //   227: ifnull +64 -> 291
    //   230: aload_3
    //   231: invokevirtual 268	java/io/BufferedReader:close	()V
    //   234: goto -27 -> 207
    //   237: astore_0
    //   238: aload_0
    //   239: invokevirtual 272	java/io/IOException:printStackTrace	()V
    //   242: goto -35 -> 207
    //   245: astore_0
    //   246: aload_0
    //   247: invokevirtual 272	java/io/IOException:printStackTrace	()V
    //   250: goto -43 -> 207
    //   253: astore_0
    //   254: aload_1
    //   255: ifnull +7 -> 262
    //   258: aload_1
    //   259: invokevirtual 268	java/io/BufferedReader:close	()V
    //   262: aload_0
    //   263: athrow
    //   264: astore_1
    //   265: aload_1
    //   266: invokevirtual 272	java/io/IOException:printStackTrace	()V
    //   269: goto -7 -> 262
    //   272: astore_0
    //   273: aload_3
    //   274: astore_1
    //   275: goto -21 -> 254
    //   278: astore_3
    //   279: aload 4
    //   281: astore_0
    //   282: goto -89 -> 193
    //   285: astore_2
    //   286: aload_1
    //   287: astore_2
    //   288: goto -176 -> 112
    //   291: goto -84 -> 207
    //   294: aload_1
    //   295: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	296	0	paramPackageManager	PackageManager
    //   7	54	1	localObject1	Object
    //   111	1	1	localException1	Exception
    //   118	50	1	localObject2	Object
    //   188	4	1	localException2	Exception
    //   194	65	1	localPackageManager	PackageManager
    //   264	2	1	localIOException	java.io.IOException
    //   274	21	1	localObject3	Object
    //   43	176	2	localArrayList	ArrayList
    //   285	1	2	localException3	Exception
    //   287	1	2	localObject4	Object
    //   13	59	3	localObject5	Object
    //   99	1	3	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   113	161	3	localObject6	Object
    //   278	1	3	localException4	Exception
    //   82	198	4	localPackageInfo	PackageInfo
    //   127	94	5	localProcess	Process
    // Exception table:
    //   from	to	target	type
    //   70	84	99	android/content/pm/PackageManager$NameNotFoundException
    //   89	96	99	android/content/pm/PackageManager$NameNotFoundException
    //   209	215	99	android/content/pm/PackageManager$NameNotFoundException
    //   44	51	111	java/lang/Exception
    //   51	70	111	java/lang/Exception
    //   70	84	111	java/lang/Exception
    //   89	96	111	java/lang/Exception
    //   100	108	111	java/lang/Exception
    //   209	215	111	java/lang/Exception
    //   154	159	188	java/lang/Exception
    //   163	185	188	java/lang/Exception
    //   220	226	188	java/lang/Exception
    //   230	234	237	java/io/IOException
    //   203	207	245	java/io/IOException
    //   119	129	253	finally
    //   131	154	253	finally
    //   195	199	253	finally
    //   258	262	264	java/io/IOException
    //   154	159	272	finally
    //   163	185	272	finally
    //   220	226	272	finally
    //   119	129	278	java/lang/Exception
    //   131	154	278	java/lang/Exception
    //   8	14	285	java/lang/Exception
    //   18	27	285	java/lang/Exception
    //   30	44	285	java/lang/Exception
  }
}
