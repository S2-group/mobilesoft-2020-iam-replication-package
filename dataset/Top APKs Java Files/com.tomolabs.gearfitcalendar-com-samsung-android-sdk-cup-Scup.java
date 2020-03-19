package com.samsung.android.sdk.cup;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Parcelable;
import android.util.Log;
import com.samsung.android.sdk.SsdkInterface;

public class Scup
  implements SsdkInterface
{
  private static final String TAG = "Scup";
  private static final String VERSION = "1.2.1";
  private static final int VERSION_LEVEL = 5;
  private static final String XML_NAME_SPACE = "http://www.samsung.com/scup";
  private static final int mHashCode = -1283921572;
  private static boolean mIsInitialized = false;
  
  public Scup() {}
  
  static String getXmlNameSpace()
  {
    return "http://www.samsung.com/scup";
  }
  
  private void insertLog(Context paramContext)
  {
    try
    {
      i = paramContext.getPackageManager().getPackageInfo("com.samsung.android.providers.context", 128).versionCode;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      int i;
      Object localObject1;
      Object localObject2;
      Object localObject3;
      for (;;) {}
    }
    Log.d("SM_SDK", "Could not find ContextProvider");
    i = -1;
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("versionCode: ");
    ((StringBuilder)localObject1).append(i);
    Log.d("SM_SDK", ((StringBuilder)localObject1).toString());
    if (i > 1)
    {
      if (paramContext.checkCallingOrSelfPermission("com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY") != 0) {
        throw new SecurityException();
      }
      localObject1 = new ContentValues();
      localObject2 = getClass().getPackage().getName();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(paramContext.getPackageName());
      ((StringBuilder)localObject3).append("#");
      ((StringBuilder)localObject3).append(getVersionCode());
      localObject3 = ((StringBuilder)localObject3).toString();
      ((ContentValues)localObject1).put("app_id", (String)localObject2);
      ((ContentValues)localObject1).put("feature", (String)localObject3);
      localObject2 = new Intent();
      ((Intent)localObject2).setAction("com.samsung.android.providers.context.log.action.USE_APP_FEATURE_SURVEY");
      ((Intent)localObject2).putExtra("data", (Parcelable)localObject1);
      ((Intent)localObject2).setPackage("com.samsung.android.providers.context");
      paramContext.sendBroadcast((Intent)localObject2);
      return;
    }
    Log.d("SM_SDK", "Add com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY permission");
  }
  
  static boolean isInitialized()
  {
    return mIsInitialized;
  }
  
  int SCUPSDKVERSION1_2_0(int paramInt)
  {
    return paramInt * paramInt + paramInt;
  }
  
  public int getVersionCode()
  {
    return 5;
  }
  
  public String getVersionName()
  {
    return "1.2.1";
  }
  
  /* Error */
  public void initialize(Context paramContext)
    throws com.samsung.android.sdk.SsdkUnsupportedException
  {
    // Byte code:
    //   0: new 67	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: ldc -94
    //   11: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   14: pop
    //   15: aload_3
    //   16: aload_0
    //   17: invokevirtual 164	com/samsung/android/sdk/cup/Scup:getVersionName	()Ljava/lang/String;
    //   20: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: pop
    //   24: ldc 10
    //   26: aload_3
    //   27: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokestatic 65	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   33: pop
    //   34: aload_1
    //   35: ifnonnull +13 -> 48
    //   38: new 166	java/lang/IllegalArgumentException
    //   41: dup
    //   42: ldc -88
    //   44: invokespecial 171	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: aload_1
    //   49: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   52: astore_3
    //   53: aload_3
    //   54: sipush 128
    //   57: invokevirtual 175	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   60: invokeinterface 181 1 0
    //   65: astore 4
    //   67: iconst_0
    //   68: istore_2
    //   69: aload 4
    //   71: invokeinterface 186 1 0
    //   76: ifeq +249 -> 325
    //   79: aload 4
    //   81: invokeinterface 190 1 0
    //   86: checkcast 192	android/content/pm/ApplicationInfo
    //   89: astore 5
    //   91: aload 5
    //   93: getfield 196	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   96: astore 6
    //   98: aload 6
    //   100: ifnull -31 -> 69
    //   103: aload 6
    //   105: ldc -58
    //   107: iconst_0
    //   108: invokevirtual 204	android/os/Bundle:getBoolean	(Ljava/lang/String;Z)Z
    //   111: ifeq -42 -> 69
    //   114: aload_1
    //   115: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   118: aload 5
    //   120: getfield 207	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   123: bipush 64
    //   125: invokevirtual 50	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   128: astore 6
    //   130: aload 6
    //   132: ifnull +153 -> 285
    //   135: aload 6
    //   137: getfield 211	android/content/pm/PackageInfo:signatures	[Landroid/content/pm/Signature;
    //   140: astore 6
    //   142: aload 6
    //   144: ifnonnull +6 -> 150
    //   147: goto +138 -> 285
    //   150: aload_3
    //   151: aload 5
    //   153: getfield 207	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   156: iconst_0
    //   157: invokevirtual 50	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   160: getfield 55	android/content/pm/PackageInfo:versionCode	I
    //   163: istore_2
    //   164: goto +5 -> 169
    //   167: iconst_0
    //   168: istore_2
    //   169: iload_2
    //   170: sipush 1045
    //   173: if_icmpgt +23 -> 196
    //   176: getstatic 213	com/samsung/android/sdk/cup/Scup:TAG	Ljava/lang/String;
    //   179: ldc -41
    //   181: invokestatic 218	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   184: pop
    //   185: new 158	com/samsung/android/sdk/SsdkUnsupportedException
    //   188: dup
    //   189: ldc -36
    //   191: iconst_3
    //   192: invokespecial 223	com/samsung/android/sdk/SsdkUnsupportedException:<init>	(Ljava/lang/String;I)V
    //   195: athrow
    //   196: new 67	java/lang/StringBuilder
    //   199: dup
    //   200: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   203: astore 6
    //   205: aload 6
    //   207: ldc -31
    //   209: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload 6
    //   215: aload 5
    //   217: getfield 207	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   220: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: pop
    //   224: aload 6
    //   226: ldc -29
    //   228: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload 6
    //   234: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   237: invokestatic 232	java/lang/System:load	(Ljava/lang/String;)V
    //   240: goto +15 -> 255
    //   243: astore 5
    //   245: aload 5
    //   247: invokevirtual 235	java/lang/Throwable:printStackTrace	()V
    //   250: ldc -19
    //   252: invokestatic 240	java/lang/System:loadLibrary	(Ljava/lang/String;)V
    //   255: iconst_1
    //   256: istore_2
    //   257: goto -188 -> 69
    //   260: astore_1
    //   261: aload_1
    //   262: invokevirtual 235	java/lang/Throwable:printStackTrace	()V
    //   265: getstatic 213	com/samsung/android/sdk/cup/Scup:TAG	Ljava/lang/String;
    //   268: ldc -14
    //   270: invokestatic 218	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   273: pop
    //   274: new 158	com/samsung/android/sdk/SsdkUnsupportedException
    //   277: dup
    //   278: ldc -14
    //   280: iconst_2
    //   281: invokespecial 223	com/samsung/android/sdk/SsdkUnsupportedException:<init>	(Ljava/lang/String;I)V
    //   284: athrow
    //   285: getstatic 213	com/samsung/android/sdk/cup/Scup:TAG	Ljava/lang/String;
    //   288: ldc -12
    //   290: invokestatic 218	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   293: pop
    //   294: new 158	com/samsung/android/sdk/SsdkUnsupportedException
    //   297: dup
    //   298: ldc -10
    //   300: iconst_2
    //   301: invokespecial 223	com/samsung/android/sdk/SsdkUnsupportedException:<init>	(Ljava/lang/String;I)V
    //   304: athrow
    //   305: getstatic 213	com/samsung/android/sdk/cup/Scup:TAG	Ljava/lang/String;
    //   308: ldc -8
    //   310: invokestatic 218	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   313: pop
    //   314: new 158	com/samsung/android/sdk/SsdkUnsupportedException
    //   317: dup
    //   318: ldc -6
    //   320: iconst_2
    //   321: invokespecial 223	com/samsung/android/sdk/SsdkUnsupportedException:<init>	(Ljava/lang/String;I)V
    //   324: athrow
    //   325: iload_2
    //   326: ifne +52 -> 378
    //   329: aload_3
    //   330: ldc -4
    //   332: iconst_0
    //   333: invokevirtual 50	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   336: pop
    //   337: getstatic 213	com/samsung/android/sdk/cup/Scup:TAG	Ljava/lang/String;
    //   340: ldc -41
    //   342: invokestatic 218	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   345: pop
    //   346: new 158	com/samsung/android/sdk/SsdkUnsupportedException
    //   349: dup
    //   350: ldc -36
    //   352: iconst_3
    //   353: invokespecial 223	com/samsung/android/sdk/SsdkUnsupportedException:<init>	(Ljava/lang/String;I)V
    //   356: athrow
    //   357: getstatic 213	com/samsung/android/sdk/cup/Scup:TAG	Ljava/lang/String;
    //   360: ldc -2
    //   362: invokestatic 218	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   365: pop
    //   366: new 158	com/samsung/android/sdk/SsdkUnsupportedException
    //   369: dup
    //   370: ldc_w 256
    //   373: iconst_2
    //   374: invokespecial 223	com/samsung/android/sdk/SsdkUnsupportedException:<init>	(Ljava/lang/String;I)V
    //   377: athrow
    //   378: aload_0
    //   379: aload_1
    //   380: invokespecial 258	com/samsung/android/sdk/cup/Scup:insertLog	(Landroid/content/Context;)V
    //   383: iconst_1
    //   384: putstatic 152	com/samsung/android/sdk/cup/Scup:mIsInitialized	Z
    //   387: return
    //   388: new 88	java/lang/SecurityException
    //   391: dup
    //   392: ldc_w 260
    //   395: invokespecial 261	java/lang/SecurityException:<init>	(Ljava/lang/String;)V
    //   398: athrow
    //   399: astore_1
    //   400: goto -95 -> 305
    //   403: astore 6
    //   405: goto -238 -> 167
    //   408: astore_1
    //   409: goto -52 -> 357
    //   412: astore_1
    //   413: goto -25 -> 388
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	416	0	this	Scup
    //   0	416	1	paramContext	Context
    //   68	258	2	i	int
    //   7	323	3	localObject1	Object
    //   65	15	4	localIterator	java.util.Iterator
    //   89	127	5	localApplicationInfo	android.content.pm.ApplicationInfo
    //   243	3	5	localThrowable	Throwable
    //   96	137	6	localObject2	Object
    //   403	1	6	localNameNotFoundException	PackageManager.NameNotFoundException
    // Exception table:
    //   from	to	target	type
    //   196	240	243	java/lang/Throwable
    //   250	255	260	java/lang/Throwable
    //   114	130	399	android/content/pm/PackageManager$NameNotFoundException
    //   135	142	399	android/content/pm/PackageManager$NameNotFoundException
    //   285	305	399	android/content/pm/PackageManager$NameNotFoundException
    //   150	164	403	android/content/pm/PackageManager$NameNotFoundException
    //   329	357	408	android/content/pm/PackageManager$NameNotFoundException
    //   378	383	412	java/lang/SecurityException
  }
  
  public boolean isFeatureEnabled(int paramInt)
  {
    return true;
  }
}
