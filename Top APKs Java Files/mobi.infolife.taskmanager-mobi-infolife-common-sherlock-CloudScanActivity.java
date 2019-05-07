package mobi.infolife.common.sherlock;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.aduwant.ads.expr.Utils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import com.trustlook.antivirus.utils.AppInfo;
import com.trustlook.cloudscan.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mobi.infolife.common.utils.AnalysisAgent;

public class CloudScanActivity
  extends ActionBarActivity
{
  public static final String DELETE_APK_FILE_ACTION = "delete_apk";
  public static final String KEY_ENABLE_PLAY_LINK = "enable_play_link";
  private static final int SCAN = 0;
  private static final int SCANNING = 1;
  public static final int SCAN_ALLAPKS = 2;
  public static final int SCAN_EXTRALISTS_APK = 1;
  public static final String SCAN_EXTRA_LIST_NAME = "extra_list";
  private static final int SCAN_FINISH = 2;
  public static final int SCAN_INSTALLED_APPS = 0;
  public static final String SCAN_MODEL_EXTRA_NAME = "scan_model";
  private static final String SUFFIX = ".apk";
  public static final String TAG = "CloudScanActivity";
  private Map<AppInfo, String> apkInfoMap = null;
  private List<String> extraList = new ArrayList();
  float interval = 0.0F;
  private Context mContext;
  private Drawable mDefaultAppIcon;
  private boolean mEnablePlayLink = true;
  private ListView mListView;
  private LinearLayout mScan;
  private TextView mScanAppName;
  private RelativeLayout mScanLayout;
  private LinearLayout mTrustLookLayout;
  private Button mUninstall;
  private LinearLayout mUninstallLayout;
  private MyAdapter myAdapter = null;
  private List<AppInfo> myAppInfoList = null;
  private float progress = 0.0F;
  private BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      paramAnonymousIntent = paramAnonymousIntent.getDataString();
      paramAnonymousIntent = paramAnonymousIntent.substring(paramAnonymousIntent.indexOf(":") + 1);
      if (("android.intent.action.PACKAGE_REMOVED".equals(paramAnonymousContext)) && (CloudScanActivity.this.myAppInfoList != null))
      {
        paramAnonymousContext = CloudScanActivity.this.selectedList.iterator();
        while (paramAnonymousContext.hasNext())
        {
          AppInfo localAppInfo = (AppInfo)paramAnonymousContext.next();
          if (localAppInfo.getPackageName().equals(paramAnonymousIntent)) {
            CloudScanActivity.this.deleteOneAppFromVirusList(localAppInfo);
          }
        }
      }
    }
  };
  private TextView resultText;
  private ScanApps scanApps;
  private int scanState = 0;
  private int scan_model = 0;
  private List<AppInfo> selectedList = new ArrayList();
  private TextView state;
  private TextView virusResult;
  private ProgressWheel wheel = null;
  
  public CloudScanActivity() {}
  
  private boolean appInstalled(String paramString)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  private void deleteOneAppFromVirusList(AppInfo paramAppInfo)
  {
    this.myAppInfoList.remove(paramAppInfo);
    this.selectedList.remove(paramAppInfo);
    this.myAdapter.notifyDataSetChanged();
    if (this.myAppInfoList.size() == 0)
    {
      this.mScanLayout.setVisibility(0);
      this.resultText.setText(getString(R.string.great));
      this.state.setVisibility(0);
      this.mUninstallLayout.setVisibility(8);
      this.mScanAppName.setVisibility(8);
      this.mTrustLookLayout.setVisibility(0);
    }
  }
  
  private void deleteVirusApk(List<AppInfo> paramList)
  {
    Object localObject = new ArrayList();
    ((List)localObject).addAll(paramList);
    paramList = ((List)localObject).iterator();
    while (paramList.hasNext())
    {
      localObject = (AppInfo)paramList.next();
      new File((String)this.apkInfoMap.get(localObject)).delete();
      deleteOneAppFromVirusList((AppInfo)localObject);
      Toast.makeText(this.mContext, ((AppInfo)localObject).getDisplayName() + " " + this.mContext.getString(R.string.deleted), 0).show();
      sendBroadcast(new Intent("delete_apk"));
    }
  }
  
  /* Error */
  private String generateDigest(File paramFile, String paramString)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokestatic 398	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   4: astore_2
    //   5: new 400	java/io/FileInputStream
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 403	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   13: astore_1
    //   14: sipush 8192
    //   17: newarray byte
    //   19: astore 4
    //   21: aload_1
    //   22: aload 4
    //   24: invokevirtual 407	java/io/FileInputStream:read	([B)I
    //   27: istore_3
    //   28: iload_3
    //   29: ifle +62 -> 91
    //   32: aload_2
    //   33: aload 4
    //   35: iconst_0
    //   36: iload_3
    //   37: invokevirtual 411	java/security/MessageDigest:update	([BII)V
    //   40: goto -19 -> 21
    //   43: astore_2
    //   44: new 413	java/lang/RuntimeException
    //   47: dup
    //   48: ldc_w 415
    //   51: aload_2
    //   52: invokespecial 418	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   55: athrow
    //   56: astore_2
    //   57: aload_1
    //   58: invokevirtual 421	java/io/FileInputStream:close	()V
    //   61: aload_2
    //   62: athrow
    //   63: astore_1
    //   64: ldc_w 423
    //   67: ldc_w 425
    //   70: aload_1
    //   71: invokestatic 431	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   74: pop
    //   75: aconst_null
    //   76: areturn
    //   77: astore_1
    //   78: ldc_w 423
    //   81: ldc_w 433
    //   84: aload_1
    //   85: invokestatic 431	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   88: pop
    //   89: aconst_null
    //   90: areturn
    //   91: ldc_w 435
    //   94: iconst_1
    //   95: anewarray 437	java/lang/Object
    //   98: dup
    //   99: iconst_0
    //   100: new 439	java/math/BigInteger
    //   103: dup
    //   104: iconst_1
    //   105: aload_2
    //   106: invokevirtual 443	java/security/MessageDigest:digest	()[B
    //   109: invokespecial 446	java/math/BigInteger:<init>	(I[B)V
    //   112: bipush 16
    //   114: invokevirtual 448	java/math/BigInteger:toString	(I)Ljava/lang/String;
    //   117: aastore
    //   118: invokestatic 452	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   121: bipush 32
    //   123: bipush 48
    //   125: invokevirtual 456	java/lang/String:replace	(CC)Ljava/lang/String;
    //   128: getstatic 462	java/util/Locale:US	Ljava/util/Locale;
    //   131: invokevirtual 466	java/lang/String:toUpperCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   134: astore_2
    //   135: aload_1
    //   136: invokevirtual 421	java/io/FileInputStream:close	()V
    //   139: aload_2
    //   140: areturn
    //   141: astore_1
    //   142: ldc_w 423
    //   145: new 349	java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial 350	java/lang/StringBuilder:<init>	()V
    //   152: ldc_w 468
    //   155: invokevirtual 357	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_1
    //   159: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   162: invokevirtual 366	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   165: invokestatic 474	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   168: pop
    //   169: aload_2
    //   170: areturn
    //   171: astore_1
    //   172: ldc_w 423
    //   175: new 349	java/lang/StringBuilder
    //   178: dup
    //   179: invokespecial 350	java/lang/StringBuilder:<init>	()V
    //   182: ldc_w 468
    //   185: invokevirtual 357	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: aload_1
    //   189: invokevirtual 471	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   192: invokevirtual 366	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   195: invokestatic 474	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   198: pop
    //   199: goto -138 -> 61
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	this	CloudScanActivity
    //   0	202	1	paramFile	File
    //   0	202	2	paramString	String
    //   27	10	3	i	int
    //   19	15	4	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   21	28	43	java/io/IOException
    //   32	40	43	java/io/IOException
    //   91	135	43	java/io/IOException
    //   21	28	56	finally
    //   32	40	56	finally
    //   44	56	56	finally
    //   91	135	56	finally
    //   0	5	63	java/security/NoSuchAlgorithmException
    //   5	14	77	java/io/FileNotFoundException
    //   135	139	141	java/io/IOException
    //   57	61	171	java/io/IOException
  }
  
  private List<AppInfo> getAllLocalAppInfos(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getLocalAppsPkgInfo(paramContext);
    this.interval = (360.0F / ((List)localObject).size());
    this.wheel.setSpinSpeed(this.interval);
    Iterator localIterator = ((List)localObject).iterator();
    localObject = localArrayList;
    if (localIterator.hasNext())
    {
      localObject = (PackageInfo)localIterator.next();
      if (this.scanApps.isCancelled()) {
        localObject = null;
      }
    }
    else
    {
      return localObject;
    }
    if ((((PackageInfo)localObject).packageName != null) && (((PackageInfo)localObject).packageName.equals(paramContext.getPackageName()))) {}
    for (;;)
    {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          CloudScanActivity.this.wheel.incrementProgress();
          CloudScanActivity.access$1802(CloudScanActivity.this, CloudScanActivity.this.progress + CloudScanActivity.this.interval);
        }
      });
      break;
      localArrayList.add(populateAppInfo(paramContext, (PackageInfo)localObject));
    }
  }
  
  private void getAllSDCardApks(List<String> paramList, File paramFile, String paramString)
  {
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        getAllSDCardApks(paramList, paramFile[i], paramString);
        i += 1;
      }
    }
    paramFile = paramFile.getAbsolutePath();
    if (paramFile.endsWith(paramString)) {
      paramList.add(paramFile);
    }
  }
  
  /* Error */
  @SuppressLint({"NewApi"})
  private AppInfo getAppinfoByApk(final Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 268	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: astore_3
    //   5: aload_3
    //   6: aload_2
    //   7: iconst_1
    //   8: invokevirtual 537	android/content/pm/PackageManager:getPackageArchiveInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   11: astore 4
    //   13: aload 4
    //   15: getfield 541	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   18: astore 5
    //   20: aload 5
    //   22: aload_2
    //   23: putfield 546	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   26: aload 4
    //   28: ifnull +8 -> 36
    //   31: aload 5
    //   33: ifnonnull +138 -> 171
    //   36: ldc_w 548
    //   39: astore_1
    //   40: aload_1
    //   41: checkcast 341	java/lang/String
    //   44: astore 6
    //   46: aload 4
    //   48: ifnonnull +133 -> 181
    //   51: ldc_w 548
    //   54: astore_1
    //   55: new 331	com/trustlook/antivirus/utils/AppInfo
    //   58: dup
    //   59: aload 6
    //   61: aload_1
    //   62: invokespecial 551	com/trustlook/antivirus/utils/AppInfo:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   65: astore_1
    //   66: aload_2
    //   67: ifnull +8 -> 75
    //   70: aload_1
    //   71: aload_2
    //   72: invokevirtual 554	com/trustlook/antivirus/utils/AppInfo:setApkPath	(Ljava/lang/String;)V
    //   75: aload_1
    //   76: invokevirtual 557	com/trustlook/antivirus/utils/AppInfo:getApkPath	()Ljava/lang/String;
    //   79: ifnull +32 -> 111
    //   82: new 333	java/io/File
    //   85: dup
    //   86: aload_1
    //   87: invokevirtual 557	com/trustlook/antivirus/utils/AppInfo:getApkPath	()Ljava/lang/String;
    //   90: invokespecial 344	java/io/File:<init>	(Ljava/lang/String;)V
    //   93: astore_2
    //   94: aload_1
    //   95: aload_0
    //   96: aload_2
    //   97: invokevirtual 561	mobi/infolife/common/sherlock/CloudScanActivity:MD5	(Ljava/io/File;)Ljava/lang/String;
    //   100: invokevirtual 564	com/trustlook/antivirus/utils/AppInfo:setMd5	(Ljava/lang/String;)V
    //   103: aload_1
    //   104: aload_2
    //   105: invokevirtual 568	java/io/File:length	()J
    //   108: invokevirtual 572	com/trustlook/antivirus/utils/AppInfo:setSizeInBytes	(J)V
    //   111: aload_1
    //   112: aload 4
    //   114: getfield 575	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   117: invokevirtual 578	com/trustlook/antivirus/utils/AppInfo:setVersion	(Ljava/lang/String;)V
    //   120: getstatic 583	android/os/Build$VERSION:SDK_INT	I
    //   123: bipush 9
    //   125: if_icmplt +12 -> 137
    //   128: aload_1
    //   129: aload 4
    //   131: getfield 587	android/content/pm/PackageInfo:lastUpdateTime	J
    //   134: invokevirtual 590	com/trustlook/antivirus/utils/AppInfo:setLastUpdate	(J)V
    //   137: aload_1
    //   138: aload 4
    //   140: getfield 594	android/content/pm/PackageInfo:requestedPermissions	[Ljava/lang/String;
    //   143: invokevirtual 598	com/trustlook/antivirus/utils/AppInfo:setPermissions	([Ljava/lang/String;)V
    //   146: aload_1
    //   147: aload_3
    //   148: aload 5
    //   150: invokevirtual 602	android/content/pm/PackageManager:getApplicationIcon	(Landroid/content/pm/ApplicationInfo;)Landroid/graphics/drawable/Drawable;
    //   153: invokevirtual 606	com/trustlook/antivirus/utils/AppInfo:setIcon	(Landroid/graphics/drawable/Drawable;)V
    //   156: aload_0
    //   157: new 20	mobi/infolife/common/sherlock/CloudScanActivity$8
    //   160: dup
    //   161: aload_0
    //   162: aload_1
    //   163: invokespecial 608	mobi/infolife/common/sherlock/CloudScanActivity$8:<init>	(Lmobi/infolife/common/sherlock/CloudScanActivity;Lcom/trustlook/antivirus/utils/AppInfo;)V
    //   166: invokevirtual 505	mobi/infolife/common/sherlock/CloudScanActivity:runOnUiThread	(Ljava/lang/Runnable;)V
    //   169: aload_1
    //   170: areturn
    //   171: aload_3
    //   172: aload 5
    //   174: invokevirtual 612	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   177: astore_1
    //   178: goto -138 -> 40
    //   181: aload 4
    //   183: getfield 494	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   186: astore_1
    //   187: goto -132 -> 55
    //   190: astore_1
    //   191: iconst_1
    //   192: ldc_w 614
    //   195: invokestatic 620	com/aduwant/ads/expr/Utils:log	(ZLjava/lang/String;)V
    //   198: aconst_null
    //   199: areturn
    //   200: astore_2
    //   201: aload_1
    //   202: aload_0
    //   203: getfield 622	mobi/infolife/common/sherlock/CloudScanActivity:mDefaultAppIcon	Landroid/graphics/drawable/Drawable;
    //   206: invokevirtual 606	com/trustlook/antivirus/utils/AppInfo:setIcon	(Landroid/graphics/drawable/Drawable;)V
    //   209: goto -53 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	212	0	this	CloudScanActivity
    //   0	212	1	paramContext	Context
    //   0	212	2	paramString	String
    //   4	168	3	localPackageManager	PackageManager
    //   11	171	4	localPackageInfo	PackageInfo
    //   18	155	5	localApplicationInfo	ApplicationInfo
    //   44	16	6	str	String
    // Exception table:
    //   from	to	target	type
    //   82	111	190	java/lang/Exception
    //   146	156	200	java/lang/OutOfMemoryError
  }
  
  private List<PackageInfo> getLocalAppsPkgInfo(Context paramContext)
  {
    int i = 0;
    for (;;)
    {
      if (i < 3) {
        try
        {
          List localList = paramContext.getPackageManager().getInstalledPackages(8192);
          return localList;
        }
        catch (RuntimeException localRuntimeException) {}
      }
      try
      {
        Thread.sleep(100L);
        i += 1;
        continue;
        return new ArrayList();
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private static String getSdCardPath()
  {
    return Environment.getExternalStorageDirectory().getPath();
  }
  
  private void initScanModel(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.hasExtra("scan_model")))
    {
      this.scan_model = paramIntent.getIntExtra("scan_model", 0);
      if (this.scan_model == 1)
      {
        this.apkInfoMap = new HashMap();
        this.extraList.addAll(paramIntent.getStringArrayListExtra("extra_list"));
        Utils.log(true, "extra_list size=" + this.extraList.size());
        startScan();
      }
    }
  }
  
  private void initView()
  {
    this.resultText = ((TextView)findViewById(R.id.result_text));
    this.state = ((TextView)findViewById(R.id.state));
    this.wheel = ((ProgressWheel)findViewById(R.id.progress_wheel));
    this.mScanAppName = ((TextView)findViewById(R.id.scan_app_name));
    this.mListView = ((ListView)findViewById(R.id.listview));
    this.mScan = ((LinearLayout)findViewById(R.id.scan));
    this.virusResult = ((TextView)findViewById(R.id.virusResult));
    this.mTrustLookLayout = ((LinearLayout)findViewById(R.id.trust_look_layout));
    if ((appInstalled("com.trustlook.antivirus")) || (!this.mEnablePlayLink)) {
      this.mTrustLookLayout.setVisibility(8);
    }
    this.mTrustLookLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW", Uri.parse("http://ad.apps.fm/3a_qFmC2LULcZD9LferPOq5px440Px0vtrw1ww5B54y_JdjpZC30p0EIOoNsM-R1DQgrw5N9mQi2TOrTnn-8UetZFRSgN6npJe_tYrTXJ_U"));
        CloudScanActivity.this.startActivity(paramAnonymousView);
        AnalysisAgent.getTracker().send(new HitBuilders.EventBuilder("promotion", "trustlook").setLabel("t").setValue(0L).build());
      }
    });
    if (this.progress == 0.0F) {
      this.wheel.setProgress(360);
    }
    for (;;)
    {
      this.mScan.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (CloudScanActivity.this.scanState == 0) {
            CloudScanActivity.this.startScan();
          }
          while (CloudScanActivity.this.scanState != 2) {
            return;
          }
          CloudScanActivity.access$302(CloudScanActivity.this, 0);
          CloudScanActivity.this.resultText.setText(R.string.scan);
          CloudScanActivity.this.state.setVisibility(8);
        }
      });
      this.mScanLayout = ((RelativeLayout)findViewById(R.id.scan_layout));
      this.mUninstallLayout = ((LinearLayout)findViewById(R.id.uninstall_layout));
      this.mUninstall = ((Button)findViewById(R.id.uninstall));
      this.mUninstall.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (CloudScanActivity.this.scan_model == 1)
          {
            CloudScanActivity.this.deleteVirusApk(CloudScanActivity.this.selectedList);
            return;
          }
          CloudScanActivity.this.uninstallVirusApp(CloudScanActivity.this.selectedList);
        }
      });
      this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (AppInfo)CloudScanActivity.this.myAppInfoList.get(paramAnonymousInt);
          if (!CloudScanActivity.this.selectedList.contains(paramAnonymousAdapterView)) {
            CloudScanActivity.this.selectedList.add(paramAnonymousAdapterView);
          }
          for (;;)
          {
            CloudScanActivity.this.myAdapter.notifyDataSetChanged();
            return;
            CloudScanActivity.this.selectedList.remove(paramAnonymousAdapterView);
          }
        }
      });
      return;
      this.wheel.setProgress((int)this.progress);
    }
  }
  
  private static boolean isAvailable()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) || ("mounted_ro".equals(str));
  }
  
  private String safeGetDeviceId(Context paramContext)
  {
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    paramContext = str;
    if (str == null) {
      paramContext = "DEVICE_ID";
    }
    return paramContext;
  }
  
  private void setActionBar()
  {
    getSupportActionBar().setDisplayShowHomeEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(R.string.scan_title);
  }
  
  private void startScan()
  {
    this.scanState = 1;
    this.progress = 0.0F;
    this.wheel.resetCount();
    this.scanApps = new ScanApps(getApplicationContext());
    this.scanApps.execute(new Void[0]);
  }
  
  private void uninstallVirusApp(List<AppInfo> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      AppInfo localAppInfo = (AppInfo)paramList.next();
      uninstallPackage(this.mContext, localAppInfo.getPackageName());
    }
  }
  
  public String MD5(File paramFile)
  {
    return generateDigest(paramFile, "MD5");
  }
  
  public String SHA1(File paramFile)
  {
    return generateDigest(paramFile, "SHA1");
  }
  
  public void finish()
  {
    super.finish();
    if (this.scanApps != null) {
      this.scanApps.cancel(true);
    }
  }
  
  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    getWindow().setFormat(1);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(2);
    super.onCreate(paramBundle);
    paramBundle = getIntent();
    if (paramBundle != null) {
      this.mEnablePlayLink = paramBundle.getBooleanExtra("enable_play_link", true);
    }
    this.mDefaultAppIcon = getResources().getDrawable(R.drawable.ic_launcher);
    setActionBar();
    setContentView(R.layout.cloud_scan);
    this.mContext = this;
    initView();
    startReceiver();
    initScanModel(paramBundle);
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.mContext.unregisterReceiver(this.receiver);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      finish();
    }
  }
  
  public void onStart()
  {
    super.onStart();
    GoogleAnalytics.getInstance(this).reportActivityStart(this);
  }
  
  public void onStop()
  {
    super.onStop();
    GoogleAnalytics.getInstance(this).reportActivityStop(this);
  }
  
  @SuppressLint({"NewApi"})
  public AppInfo populateAppInfo(final Context paramContext, PackageInfo paramPackageInfo)
  {
    if ((paramPackageInfo == null) || (paramPackageInfo.applicationInfo == null)) {
      paramContext = "unknown";
    }
    for (;;)
    {
      Object localObject = (String)paramContext;
      if (paramPackageInfo == null)
      {
        paramContext = "unknown";
        paramContext = new AppInfo((String)localObject, paramContext);
        if ((paramPackageInfo != null) && (paramPackageInfo.applicationInfo != null)) {
          paramContext.setApkPath(paramPackageInfo.applicationInfo.publicSourceDir);
        }
        if (paramContext.getApkPath() != null)
        {
          localObject = new File(paramContext.getApkPath());
          paramContext.setMd5(MD5((File)localObject));
          paramContext.setSizeInBytes(((File)localObject).length());
        }
        paramContext.setVersion(paramPackageInfo.versionName);
        if (Build.VERSION.SDK_INT >= 9) {
          paramContext.setLastUpdate(paramPackageInfo.lastUpdateTime);
        }
        paramContext.setPermissions(paramPackageInfo.requestedPermissions);
      }
      try
      {
        paramContext.setIcon(paramPackageInfo.applicationInfo.loadIcon(getPackageManager()));
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            CloudScanActivity.this.mScanAppName.setText(paramContext.getDisplayName());
          }
        });
        return paramContext;
        paramContext = paramPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager());
        continue;
        paramContext = paramPackageInfo.packageName;
      }
      catch (OutOfMemoryError paramPackageInfo)
      {
        for (;;)
        {
          paramContext.setIcon(this.mDefaultAppIcon);
        }
      }
    }
  }
  
  public void startReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addDataScheme("package");
    this.mContext.registerReceiver(this.receiver, localIntentFilter);
  }
  
  public void stopReceiver()
  {
    this.mContext.unregisterReceiver(this.receiver);
  }
  
  public void uninstallPackage(Context paramContext, String paramString)
  {
    paramContext.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString)));
  }
  
  class ApkInfo
  {
    private AppInfo appInfo;
    private String path;
    
    public ApkInfo(AppInfo paramAppInfo, String paramString)
    {
      this.appInfo = paramAppInfo;
      this.path = paramString;
    }
  }
  
  class MyAdapter
    extends BaseAdapter
  {
    LayoutInflater inflater = null;
    
    public MyAdapter() {}
    
    public int getCount()
    {
      return CloudScanActivity.this.myAppInfoList.size();
    }
    
    public Object getItem(int paramInt)
    {
      return CloudScanActivity.this.myAppInfoList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      AppInfo localAppInfo;
      if (paramView == null)
      {
        paramViewGroup = new Holder();
        paramView = this.inflater.inflate(R.layout.scan_result_list_item, null);
        paramViewGroup.appIcon = ((ImageView)paramView.findViewById(R.id.task_icon));
        paramViewGroup.appTitle = ((TextView)paramView.findViewById(R.id.task_title));
        paramViewGroup.flag = ((ImageView)paramView.findViewById(R.id.flag));
        paramViewGroup.checkBox = ((CheckBox)paramView.findViewById(R.id.app_select));
        paramView.setTag(paramViewGroup);
        localAppInfo = (AppInfo)CloudScanActivity.this.myAppInfoList.get(paramInt);
        paramViewGroup.appIcon.setImageDrawable(localAppInfo.getIcon());
        paramViewGroup.appTitle.setText(localAppInfo.getDisplayName());
        if (localAppInfo.getScore() != 10) {
          break label186;
        }
        paramViewGroup.flag.setImageResource(R.drawable.ic_virus);
      }
      for (;;)
      {
        if (!CloudScanActivity.this.selectedList.contains(localAppInfo)) {
          break label199;
        }
        paramViewGroup.checkBox.setChecked(true);
        return paramView;
        paramViewGroup = (Holder)paramView.getTag();
        break;
        label186:
        paramViewGroup.flag.setImageResource(R.drawable.ic_risk);
      }
      label199:
      paramViewGroup.checkBox.setChecked(false);
      return paramView;
    }
    
    class Holder
    {
      ImageView appIcon;
      TextView appTitle;
      CheckBox checkBox;
      ImageView flag;
      
      Holder() {}
    }
  }
  
  class MyAppInfo
    extends AppInfo
  {
    private boolean isChecked;
    
    public MyAppInfo(String paramString1, String paramString2)
    {
      super(paramString2);
    }
    
    public boolean isChecked()
    {
      return this.isChecked;
    }
    
    public void setChecked(boolean paramBoolean)
    {
      this.isChecked = paramBoolean;
    }
  }
  
  private class ScanApps
    extends AsyncTask<Void, String, String>
  {
    private List<AppInfo> _appInfoList = null;
    private Context context = null;
    Scanner scanner = new Scanner("jzr3xj2c3qx9a4v0oemms6n96ldz1zz1");
    
    public ScanApps(Context paramContext)
    {
      this.context = paramContext;
    }
    
    protected String doInBackground(Void... paramVarArgs)
    {
      if (isCancelled()) {
        return "";
      }
      Log.i("CloudScanActivity", "scan_model:" + CloudScanActivity.this.scan_model);
      switch (CloudScanActivity.this.scan_model)
      {
      }
      for (;;)
      {
        CloudScanActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            CloudScanActivity.this.resultText.setText(CloudScanActivity.this.mContext.getString(R.string.checking));
          }
        });
        paramVarArgs = CloudScanActivity.this.safeGetDeviceId(this.context);
        return this.scanner.queryTrustLook(paramVarArgs, this._appInfoList);
        this._appInfoList = CloudScanActivity.this.getAllLocalAppInfos(this.context);
        continue;
        this._appInfoList = new ArrayList();
        if (CloudScanActivity.this.extraList.size() > 0)
        {
          CloudScanActivity.this.interval = (360.0F / CloudScanActivity.this.extraList.size());
          CloudScanActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              CloudScanActivity.this.wheel.setSpinSpeed(CloudScanActivity.this.interval);
            }
          });
        }
        paramVarArgs = CloudScanActivity.this.extraList.iterator();
        if (paramVarArgs.hasNext())
        {
          Object localObject = (String)paramVarArgs.next();
          AppInfo localAppInfo = CloudScanActivity.this.getAppinfoByApk(CloudScanActivity.this, (String)localObject);
          StringBuilder localStringBuilder = new StringBuilder().append((String)localObject).append(",");
          if (localAppInfo == null) {}
          for (boolean bool = true;; bool = false)
          {
            Utils.log(true, bool);
            if (localAppInfo == null) {
              break;
            }
            this._appInfoList.add(localAppInfo);
            CloudScanActivity.this.apkInfoMap.put(localAppInfo, localObject);
            CloudScanActivity.this.runOnUiThread(new Runnable()
            {
              public void run()
              {
                CloudScanActivity.this.wheel.incrementProgress();
                CloudScanActivity.access$1802(CloudScanActivity.this, CloudScanActivity.this.progress + CloudScanActivity.this.interval);
              }
            });
            break;
          }
          if (CloudScanActivity.access$1900())
          {
            paramVarArgs = new File(CloudScanActivity.access$2000());
            localObject = new ArrayList();
            CloudScanActivity.this.getAllSDCardApks((List)localObject, paramVarArgs, ".apk");
            this._appInfoList = CloudScanActivity.this.getAllLocalAppInfos(this.context);
            paramVarArgs = ((List)localObject).iterator();
            while (paramVarArgs.hasNext())
            {
              localObject = (String)paramVarArgs.next();
              this._appInfoList.add(CloudScanActivity.this.getAppinfoByApk(this.context, (String)localObject));
            }
          }
        }
      }
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
    }
    
    protected void onPostExecute(String paramString)
    {
      try
      {
        CloudScanActivity.access$002(CloudScanActivity.this, new ArrayList());
        this.scanner.parseQueryResult((ArrayList)this._appInfoList, paramString);
        paramString = this._appInfoList.iterator();
        while (paramString.hasNext())
        {
          AppInfo localAppInfo = (AppInfo)paramString.next();
          if (localAppInfo.getScore() > 7)
          {
            CloudScanActivity.this.myAppInfoList.add(localAppInfo);
            CloudScanActivity.this.selectedList.add(localAppInfo);
          }
        }
        CloudScanActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            if (CloudScanActivity.this.myAppInfoList.size() > 0)
            {
              CloudScanActivity.this.mScanLayout.setVisibility(8);
              CloudScanActivity.this.mUninstallLayout.setVisibility(0);
              StringBuffer localStringBuffer = new StringBuffer();
              int j = 0;
              int i = 0;
              Iterator localIterator = CloudScanActivity.this.myAppInfoList.iterator();
              while (localIterator.hasNext()) {
                if (((AppInfo)localIterator.next()).getScore() == 10) {
                  i += 1;
                } else {
                  j += 1;
                }
              }
              localStringBuffer.append(String.format(CloudScanActivity.this.getString(R.string.result_text), new Object[] { Integer.valueOf(i), Integer.valueOf(j) }));
              CloudScanActivity.this.virusResult.setText(localStringBuffer.toString());
              CloudScanActivity.access$1002(CloudScanActivity.this, new CloudScanActivity.MyAdapter(CloudScanActivity.this));
              CloudScanActivity.this.mListView.setAdapter(CloudScanActivity.this.myAdapter);
              CloudScanActivity.this.mTrustLookLayout.setVisibility(8);
            }
            for (;;)
            {
              CloudScanActivity.access$302(CloudScanActivity.this, 2);
              return;
              CloudScanActivity.this.mScanAppName.setVisibility(8);
              CloudScanActivity.this.resultText.setText(CloudScanActivity.this.getString(R.string.great));
              CloudScanActivity.this.state.setVisibility(0);
            }
          }
        });
      }
      catch (Exception paramString)
      {
        Log.e("scan", paramString.getLocalizedMessage() + "++");
        return;
      }
    }
    
    protected void onPreExecute()
    {
      CloudScanActivity.this.resultText.setText(CloudScanActivity.this.mContext.getString(R.string.scanning));
      CloudScanActivity.this.state.setVisibility(8);
      CloudScanActivity.this.mScanAppName.setVisibility(0);
    }
    
    protected void onProgressUpdate(String... paramVarArgs) {}
  }
}
