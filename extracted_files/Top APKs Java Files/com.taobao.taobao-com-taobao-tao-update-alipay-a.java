package com.taobao.tao.update.alipay;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.TextUtils;
import com.taobao.statistic.CT;
import com.taobao.statistic.TBS.Adv;
import com.taobao.tao.Globals;
import com.taobao.tao.TaoApplication;
import com.taobao.tao.homepage.preference.AppPreference;
import com.taobao.tao.update.c;
import com.taobao.tao.util.NetWorkUtils;
import com.taobao.tao.util.NetWorkUtils.MobileNetworkType;
import com.taobao.update.OnUpdateListener;
import com.taobao.update.d;
import com.taobao.update.f;
import com.taobao.update.f.a;
import com.taobao.update.g;
import com.taobao.update.i;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class a
  implements OnUpdateListener
{
  public static final String FILENAME = "AliPay_Extension.alipay";
  public static boolean NOT_UPDATE = false;
  private static final String a = Environment.getExternalStorageDirectory().getAbsolutePath() + "/taobao/alipay_support/";
  private static a b;
  private static long f = 0L;
  public final String[] OUI_MANUFACTOR_ARRAY = { "00-EE-BD", "04-18-0F" };
  public final String TAG = "AlipaySilentDownloader";
  private Context c;
  private f d;
  private NetWorkStateChangeReceiver e;
  
  protected a(Context paramContext)
  {
    this.c = paramContext;
    this.d = new f(new d(paramContext), new c("1.0", "alipay4android"), this);
    paramContext = new File(a);
    if (!paramContext.exists()) {
      paramContext.mkdirs();
    }
    this.d.setApkStorePath(a);
  }
  
  private boolean a(String paramString1, String paramString2)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramString2 != null)
    {
      paramString2 = paramString2.toLowerCase();
      if ((paramString2.contains("android")) || (paramString2.contains("ap")) || (paramString2.contains("lenovo")) || (paramString2.contains("coolpad")) || (paramString2.contains("samsung")) || (paramString2.contains("iphone"))) {
        bool1 = true;
      }
    }
    do
    {
      return bool1;
      bool1 = bool2;
    } while (paramString1 == null);
    int i = 0;
    for (;;)
    {
      bool1 = bool2;
      if (i >= this.OUI_MANUFACTOR_ARRAY.length) {
        break;
      }
      if (paramString1.toUpperCase().startsWith(this.OUI_MANUFACTOR_ARRAY[i])) {
        return true;
      }
      i += 1;
    }
  }
  
  private boolean b()
  {
    Iterator localIterator = this.c.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.eg.android.AlipayGphone")) {
        return true;
      }
    }
    return false;
  }
  
  public static a getInstance(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new a(paramContext);
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  public boolean canDownload()
  {
    if ((!NetWorkUtils.isLowNetworkMode(this.c)) && (NetWorkUtils.getMobileNetworkType(Globals.getApplication()) != NetWorkUtils.MobileNetworkType.MOBILE_NETWORK_TYPE_4G))
    {
      WifiInfo localWifiInfo = ((WifiManager)this.c.getSystemService("wifi")).getConnectionInfo();
      if (!a(localWifiInfo.getBSSID(), localWifiInfo.getSSID())) {
        return true;
      }
    }
    return false;
  }
  
  public void checkDownload()
  {
    if (!b())
    {
      if (new File(a, "AliPay_Extension.alipay").exists()) {
        break label68;
      }
      if ((canDownload()) && (System.currentTimeMillis() - f > 3600000L)) {
        f = System.currentTimeMillis();
      }
    }
    label68:
    do
    {
      this.d.request(a, "5894", "0.0.0");
      do
      {
        return;
      } while (NOT_UPDATE);
      localObject = AppPreference.getString("last_request_alipay_time", "0");
    } while ((System.currentTimeMillis() - Long.parseLong((String)localObject) < 86400000L) || (!canDownload()));
    Object localObject = this.c.getPackageManager().getPackageArchiveInfo(new File(a, "AliPay_Extension.alipay").getAbsolutePath(), 1);
    String str = ((PackageInfo)localObject).applicationInfo.packageName;
    localObject = ((PackageInfo)localObject).versionName;
    this.d.request(a, TaoApplication.getTTIDNum(), (String)localObject);
  }
  
  public boolean checkInstallAliPay()
  {
    if (b()) {}
    File localFile;
    do
    {
      return false;
      localFile = new File(a, "AliPay_Extension.alipay");
      localObject = getAPkMd5();
    } while ((localFile == null) || (localObject == null) || (!((String)localObject).equals(i.getMD5(localFile.getAbsolutePath()))));
    Object localObject = new Intent();
    ((Intent)localObject).setAction("android.intent.action.VIEW");
    ((Intent)localObject).setDataAndType(Uri.parse("file://" + localFile.getAbsolutePath()), "application/vnd.android.package-archive");
    ((Intent)localObject).setFlags(268435456);
    this.c.startActivity((Intent)localObject);
    TBS.Adv.ctrlClicked(CT.Button, "alipaysilentinstall", new String[0]);
    return true;
  }
  
  /* Error */
  public String getAPkMd5()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 41	java/io/File
    //   6: dup
    //   7: new 30	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 33	java/lang/StringBuilder:<init>	()V
    //   14: getstatic 56	com/taobao/tao/update/alipay/a:a	Ljava/lang/String;
    //   17: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   20: ldc_w 345
    //   23: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokespecial 100	java/io/File:<init>	(Ljava/lang/String;)V
    //   32: astore_2
    //   33: aload 4
    //   35: astore_1
    //   36: aload_2
    //   37: invokevirtual 104	java/io/File:exists	()Z
    //   40: ifeq +38 -> 78
    //   43: new 347	java/io/BufferedReader
    //   46: dup
    //   47: new 349	java/io/FileReader
    //   50: dup
    //   51: aload_2
    //   52: invokespecial 352	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   55: invokespecial 355	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   58: astore_2
    //   59: aload_2
    //   60: astore_1
    //   61: aload_2
    //   62: invokevirtual 358	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   65: astore_3
    //   66: aload_3
    //   67: astore_1
    //   68: aload_2
    //   69: ifnull +9 -> 78
    //   72: aload_2
    //   73: invokevirtual 361	java/io/BufferedReader:close	()V
    //   76: aload_3
    //   77: astore_1
    //   78: aload_1
    //   79: areturn
    //   80: astore_1
    //   81: aload_1
    //   82: invokevirtual 364	java/io/IOException:printStackTrace	()V
    //   85: aload_3
    //   86: areturn
    //   87: astore_3
    //   88: aconst_null
    //   89: astore_2
    //   90: aload_2
    //   91: astore_1
    //   92: aload_3
    //   93: invokevirtual 365	java/io/FileNotFoundException:printStackTrace	()V
    //   96: aload 4
    //   98: astore_1
    //   99: aload_2
    //   100: ifnull -22 -> 78
    //   103: aload_2
    //   104: invokevirtual 361	java/io/BufferedReader:close	()V
    //   107: aconst_null
    //   108: areturn
    //   109: astore_1
    //   110: aload_1
    //   111: invokevirtual 364	java/io/IOException:printStackTrace	()V
    //   114: aconst_null
    //   115: areturn
    //   116: astore_3
    //   117: aconst_null
    //   118: astore_2
    //   119: aload_2
    //   120: astore_1
    //   121: aload_3
    //   122: invokevirtual 364	java/io/IOException:printStackTrace	()V
    //   125: aload 4
    //   127: astore_1
    //   128: aload_2
    //   129: ifnull -51 -> 78
    //   132: aload_2
    //   133: invokevirtual 361	java/io/BufferedReader:close	()V
    //   136: aconst_null
    //   137: areturn
    //   138: astore_1
    //   139: aload_1
    //   140: invokevirtual 364	java/io/IOException:printStackTrace	()V
    //   143: aconst_null
    //   144: areturn
    //   145: astore_2
    //   146: aconst_null
    //   147: astore_1
    //   148: aload_1
    //   149: ifnull +7 -> 156
    //   152: aload_1
    //   153: invokevirtual 361	java/io/BufferedReader:close	()V
    //   156: aload_2
    //   157: athrow
    //   158: astore_1
    //   159: aload_1
    //   160: invokevirtual 364	java/io/IOException:printStackTrace	()V
    //   163: goto -7 -> 156
    //   166: astore_2
    //   167: goto -19 -> 148
    //   170: astore_3
    //   171: goto -52 -> 119
    //   174: astore_3
    //   175: goto -85 -> 90
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	178	0	this	a
    //   35	44	1	localObject1	Object
    //   80	2	1	localIOException1	java.io.IOException
    //   91	8	1	localObject2	Object
    //   109	2	1	localIOException2	java.io.IOException
    //   120	8	1	localObject3	Object
    //   138	2	1	localIOException3	java.io.IOException
    //   147	6	1	localObject4	Object
    //   158	2	1	localIOException4	java.io.IOException
    //   32	101	2	localObject5	Object
    //   145	12	2	localObject6	Object
    //   166	1	2	localObject7	Object
    //   65	21	3	str	String
    //   87	6	3	localFileNotFoundException1	java.io.FileNotFoundException
    //   116	6	3	localIOException5	java.io.IOException
    //   170	1	3	localIOException6	java.io.IOException
    //   174	1	3	localFileNotFoundException2	java.io.FileNotFoundException
    //   1	125	4	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   72	76	80	java/io/IOException
    //   43	59	87	java/io/FileNotFoundException
    //   103	107	109	java/io/IOException
    //   43	59	116	java/io/IOException
    //   132	136	138	java/io/IOException
    //   43	59	145	finally
    //   152	156	158	java/io/IOException
    //   61	66	166	finally
    //   92	96	166	finally
    //   121	125	166	finally
    //   61	66	170	java/io/IOException
    //   61	66	174	java/io/FileNotFoundException
  }
  
  public void onDownloadError(int paramInt, String paramString)
  {
    try
    {
      if (this.c != null) {
        this.c.unregisterReceiver(this.e);
      }
      return;
    }
    catch (Throwable paramString) {}
  }
  
  public void onDownloadFinsh(String paramString)
  {
    new StringBuilder().append("download finish ").append(paramString).toString();
    try
    {
      if (this.c != null) {
        this.c.unregisterReceiver(this.e);
      }
      File localFile = new File(a, "AliPay_Extension.alipay");
      if (localFile.exists()) {
        localFile.delete();
      }
      new File(paramString).renameTo(localFile);
      NOT_UPDATE = true;
      AppPreference.putString("last_request_alipay_time", System.currentTimeMillis() + "");
      try
      {
        paramString = i.getMD5(localFile.getAbsolutePath());
        String str = getAPkMd5();
        if ((paramString != null) && (str != null) && (!paramString.equals(str)))
        {
          localFile.delete();
          int i = AppPreference.getInt("alipay_download_wrong_md5_count", 0);
          if (i < 2)
          {
            checkDownload();
            AppPreference.putInt("alipay_download_wrong_md5_count", i + 1);
          }
        }
        else
        {
          TBS.Adv.ctrlClicked(CT.Button, "alipaysilentfinish", new String[0]);
          return;
        }
      }
      catch (Throwable paramString)
      {
        paramString.printStackTrace();
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public void onDownloadProgress(int paramInt)
  {
    new StringBuilder().append("download progress = ").append(paramInt).toString();
  }
  
  public void onRequestResult(g paramG, f.a paramA)
  {
    if ((paramG != null) && (paramG.mNewApkMD5 != null) && (paramG.mApkSize > 0L) && (!TextUtils.isEmpty(paramG.mApkDLUrl)))
    {
      paramA.download();
      storeApkInfo(paramG.mNewApkMD5);
      paramG = new IntentFilter();
      paramG.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      this.e = new NetWorkStateChangeReceiver();
      this.c.registerReceiver(this.e, paramG);
      TBS.Adv.ctrlClicked(CT.Button, "alipaysilentdownload", new String[0]);
    }
  }
  
  public void stopDownload()
  {
    if (this.d != null) {
      this.d.cancelDownload();
    }
  }
  
  public void storeApkInfo(String paramString)
  {
    new b(this, paramString).execute(new String[0]);
  }
}
