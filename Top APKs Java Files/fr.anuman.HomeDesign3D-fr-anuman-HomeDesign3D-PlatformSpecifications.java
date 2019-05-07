package fr.anuman.HomeDesign3D;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Debug;
import android.os.Environment;
import android.os.Parcelable;
import android.os.storage.StorageManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.HitBuilders.TimingBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.libraries.cloudtesting.screenshots.ScreenShotter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PlatformSpecifications
{
  static Map<String, Long> gaEventTimers;
  static InAppInterface inAppInterface = new InAppInterface(SingletonQtApplication.getInstance().getCurrentActivity());
  static AssetManager mAssetManager;
  static final Map<Integer, String> mountErrors = new HashMap() {};
  
  static
  {
    gaEventTimers = new HashMap();
    mAssetManager = SingletonQtApplication.getInstance().getAssets();
  }
  
  PlatformSpecifications() {}
  
  static void analyticsEndTimedEvent(String paramString, String[] paramArrayOfString)
  {
    Object localObject;
    if (paramArrayOfString.length > 0)
    {
      localObject = new HashMap();
      int i = 0;
      while (i < paramArrayOfString.length / 2)
      {
        ((Map)localObject).put(paramArrayOfString[(i * 2)], paramArrayOfString[(i * 2 + 1)]);
        i += 1;
      }
    }
    paramArrayOfString = SingletonQtApplication.getInstance().getCurrentActivity().getGATracker();
    if ((paramArrayOfString != null) && (gaEventTimers.containsKey(paramString)))
    {
      long l1 = System.currentTimeMillis();
      long l2 = ((Long)gaEventTimers.get(paramString)).longValue();
      localObject = new HitBuilders.TimingBuilder();
      ((HitBuilders.TimingBuilder)localObject).setCategory(paramString);
      ((HitBuilders.TimingBuilder)localObject).setValue(l1 - l2);
      paramArrayOfString.send(((HitBuilders.TimingBuilder)localObject).build());
    }
  }
  
  static void analyticsLogEvent(String paramString, String[] paramArrayOfString, int paramInt)
  {
    Object localObject1 = new HashMap();
    int i = 0;
    while (i < paramArrayOfString.length / 2)
    {
      ((Map)localObject1).put(paramArrayOfString[(i * 2)], paramArrayOfString[(i * 2 + 1)]);
      i += 1;
    }
    paramArrayOfString = SingletonQtApplication.getInstance().getCurrentActivity().getGATracker();
    if (paramArrayOfString != null)
    {
      localObject2 = new HitBuilders.EventBuilder();
      ((HitBuilders.EventBuilder)localObject2).setCategory(paramString);
      paramArrayOfString.send(((HitBuilders.EventBuilder)localObject2).build());
      if (paramInt != 0) {
        gaEventTimers.put(paramString, new Long(System.currentTimeMillis()));
      }
    }
    paramArrayOfString = paramString;
    Object localObject2 = ((Map)localObject1).entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject2).next();
      paramArrayOfString = paramArrayOfString + ", " + (String)localEntry.getKey() + ":" + (String)localEntry.getValue();
    }
    Crashlytics.log(paramArrayOfString);
    paramString = new CustomEvent(paramString);
    paramArrayOfString = ((Map)localObject1).entrySet().iterator();
    while (paramArrayOfString.hasNext())
    {
      localObject1 = (Map.Entry)paramArrayOfString.next();
      if (isNumeric((String)((Map.Entry)localObject1).getValue()))
      {
        paramString.putCustomAttribute((String)((Map.Entry)localObject1).getKey(), Double.valueOf(Double.parseDouble((String)((Map.Entry)localObject1).getValue())));
        System.out.println("Item : " + (String)((Map.Entry)localObject1).getKey() + " Numeric Value : " + Double.parseDouble((String)((Map.Entry)localObject1).getValue()));
      }
      else
      {
        paramString.putCustomAttribute((String)((Map.Entry)localObject1).getKey(), (String)((Map.Entry)localObject1).getValue());
        System.out.println("Item : " + (String)((Map.Entry)localObject1).getKey() + " String Value : " + (String)((Map.Entry)localObject1).getValue());
      }
    }
    Answers.getInstance().logCustom(paramString);
  }
  
  static String backupPath()
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      waitForExternalStorageAvailable();
      str1 = str2;
      str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + SingletonQtApplication.getInstance().getPackageName();
      str1 = str2;
      new File(str2).mkdirs();
      return str2;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      localException.printStackTrace();
    }
    return str1;
  }
  
  private int copyFile(String paramString1, String paramString2)
  {
    try
    {
      waitForExternalStorageAvailable();
      paramString1 = new File(paramString1);
      paramString2 = new File(paramString2);
      paramString1 = new FileInputStream(paramString1);
      paramString2 = new FileOutputStream(paramString2);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramString1.read(arrayOfByte);
        if (i <= 0) {
          break;
        }
        paramString2.write(arrayOfByte, 0, i);
      }
      paramString1.close();
    }
    catch (Exception paramString1)
    {
      Crashlytics.logException(paramString1);
      paramString1.printStackTrace();
      return 0;
    }
    paramString2.close();
    return 1;
  }
  
  static void criticalPopup(String paramString1, final String paramString2)
  {
    try
    {
      SingletonQtApplication.getInstance().getCurrentActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(SingletonQtApplication.getInstance().getCurrentActivity());
          localBuilder.setTitle(this.val$title);
          localBuilder.setMessage(paramString2);
          localBuilder.setCancelable(false);
          localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.cancel();
              SingletonQtApplication.getInstance().getCurrentActivity().finish();
              System.exit(0);
            }
          });
          localBuilder.create().show();
        }
      });
      for (;;)
      {
        Thread.currentThread();
        Thread.sleep(50L);
      }
      return;
    }
    catch (Exception paramString1)
    {
      Crashlytics.logException(paramString1);
      paramString1.printStackTrace();
    }
  }
  
  private void finishActivity()
  {
    try
    {
      System.out.println("finishActivity");
      SingletonQtApplication.getInstance().getCurrentActivity().finish();
      return;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      localException.printStackTrace();
    }
  }
  
  private void firebaseTestLabTakeScreenshot(String paramString)
  {
    try
    {
      System.out.println("firebaseTestLabTakeScreenshot : " + paramString);
      ScreenShotter.takeScreenshot(paramString, SingletonQtApplication.getInstance().getCurrentActivity());
      return;
    }
    catch (Exception paramString)
    {
      Crashlytics.logException(paramString);
      paramString.printStackTrace();
    }
  }
  
  static ArrayList getFilesRecursively(File paramFile)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles();
      int j = paramFile.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramFile[i];
        localArrayList.add(localObject);
        localArrayList.addAll(getFilesRecursively(localObject));
        i += 1;
      }
    }
    return localArrayList;
  }
  
  private static boolean isNumeric(String paramString)
  {
    try
    {
      Double.parseDouble(paramString);
      return true;
    }
    catch (NumberFormatException paramString) {}
    return false;
  }
  
  private int mkpath(String paramString)
  {
    int i = 0;
    try
    {
      boolean bool = new File(paramString).mkdirs();
      if (bool) {
        i = 1;
      }
      return i;
    }
    catch (Exception paramString)
    {
      Crashlytics.logException(paramString);
      paramString.printStackTrace();
    }
    return 0;
  }
  
  private String mountObb(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    ObbStateChangeListener localObbStateChangeListener;
    StorageManager localStorageManager;
    try
    {
      localObbStateChangeListener = new ObbStateChangeListener();
      str1 = str2;
      localStorageManager = (StorageManager)SingletonQtApplication.getInstance().getSystemService("storage");
      str1 = str2;
      System.out.println("[mountObbPackage] obb file location : " + paramString);
      str1 = str2;
      if (!localStorageManager.isObbMounted(paramString)) {
        break label144;
      }
      str1 = str2;
      if (localStorageManager.unmountObb(paramString, true, localObbStateChangeListener)) {
        for (;;)
        {
          str1 = str2;
          if (localObbStateChangeListener.getState(paramString) != -1) {
            break;
          }
          str1 = str2;
          Thread.currentThread();
          str1 = str2;
          Thread.sleep(50L);
        }
      }
      str1 = str2;
    }
    catch (Exception paramString)
    {
      Crashlytics.logException(paramString);
      paramString.printStackTrace();
      return str1;
    }
    System.out.println("[mountObbPackage] error while adding obb file to unmounting queue");
    label144:
    str1 = str2;
    int i;
    if (localStorageManager.mountObb(paramString, null, localObbStateChangeListener)) {
      for (;;)
      {
        str1 = str2;
        i = localObbStateChangeListener.getState(paramString);
        if (i != -1) {
          break;
        }
        str1 = str2;
        Thread.currentThread();
        str1 = str2;
        Thread.sleep(50L);
      }
    }
    for (;;)
    {
      str1 = str2;
      paramString = localStorageManager.getMountedObbPath(paramString);
      str1 = paramString;
      System.out.println("[mountObbPackage] mounted path : " + paramString);
      return paramString;
      do
      {
        str1 = str2;
        System.out.println("[mountObbPackage] mounting error : " + (String)mountErrors.get(Integer.valueOf(i)));
        return "";
        str1 = str2;
        System.out.println("[mountObbPackage] error while adding obb file to mounting queue");
        return "";
        if (i == 1) {
          break;
        }
      } while (i != 24);
    }
  }
  
  public static boolean waitForExternalStorageAvailable()
    throws InterruptedException
  {
    int i = 0;
    while (i < 40)
    {
      if ("mounted".equals(Environment.getExternalStorageState())) {
        return true;
      }
      Thread.currentThread();
      Thread.sleep(50L);
      i += 1;
    }
    criticalPopup("Fatal Error", "Unable to access to the external storage. Please ensure your sd card is correctly plugged or your device unplugged from any computer.");
    return false;
  }
  
  private boolean writeFile(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      waitForExternalStorageAvailable();
      paramString = new FileOutputStream(new File(paramString));
      paramString.write(paramArrayOfByte);
      paramString.close();
      return true;
    }
    catch (Exception paramArrayOfByte)
    {
      Crashlytics.logException(paramArrayOfByte);
      paramArrayOfByte.printStackTrace();
    }
    return false;
  }
  
  AssetManager assetManager()
  {
    return mAssetManager;
  }
  
  int buyInApp(String paramString)
  {
    return InAppInterface.getInstance().buyInApp(paramString);
  }
  
  public void checkRuntimePermission(int paramInt)
  {
    SingletonQtApplication.getInstance().getCurrentActivity().checkRuntimePermission(paramInt);
  }
  
  void checkStoreAvailable()
  {
    InAppInterface.getInstance().checkStoreAvailable();
  }
  
  void crashLyticsError(String paramString1, int paramInt, String paramString2)
  {
    Crashlytics.logException(new Exception(paramString1 + ":" + paramInt + ": " + paramString2));
  }
  
  void crashLyticsLog(String paramString)
  {
    Crashlytics.log(paramString);
  }
  
  void displayAdBanner(int paramInt)
  {
    if (paramInt != 0) {}
    for (boolean bool = true;; bool = false)
    {
      SingletonQtApplication.getInstance().getCurrentActivity().getAdManager().setBannerVisible(bool);
      return;
    }
  }
  
  void displayAdInterstitial(String paramString)
  {
    SingletonQtApplication.getInstance().getCurrentActivity().getAdManager().displayAdInterstitial(paramString);
  }
  
  float dpi()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)SingletonQtApplication.getInstance().getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.xdpi;
  }
  
  void errorPopup(final String paramString1, final String paramString2)
  {
    try
    {
      SingletonQtApplication.getInstance().getCurrentActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(SingletonQtApplication.getInstance().getCurrentActivity());
          localBuilder.setTitle(paramString1);
          localBuilder.setMessage(paramString2);
          localBuilder.setCancelable(false);
          localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.cancel();
            }
          });
          localBuilder.create().show();
        }
      });
      return;
    }
    catch (Exception paramString1)
    {
      Crashlytics.logException(paramString1);
      paramString1.printStackTrace();
    }
  }
  
  void exportFiles(String[] paramArrayOfString, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    Object localObject1;
    Object localObject2;
    Object localObject3;
    for (;;)
    {
      try
      {
        paramString3 = new Intent();
        if (paramArrayOfString.length <= 1) {
          break label243;
        }
        paramString3.setAction("android.intent.action.SEND_MULTIPLE");
        paramString3.addFlags(1);
        localObject1 = new ArrayList();
        int i = 0;
        if (i >= paramArrayOfString.length) {
          break;
        }
        localObject2 = Uri.parse("content://" + FileProvider.getAuthority() + paramArrayOfString[i]);
        ((ArrayList)localObject1).add(localObject2);
        localObject3 = SingletonQtApplication.getInstance().getPackageManager().getInstalledApplications(128).iterator();
        if (((Iterator)localObject3).hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject3).next();
          SingletonQtApplication.getInstance().grantUriPermission(localApplicationInfo.packageName, (Uri)localObject2, 3);
        }
        else
        {
          i += 1;
        }
      }
      catch (Exception paramArrayOfString)
      {
        System.out.println("Error during files export : " + paramArrayOfString);
        Crashlytics.logException(paramArrayOfString);
        return;
      }
    }
    paramString3.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject1);
    for (;;)
    {
      paramString3.setType(paramString1);
      if (paramBoolean == true) {
        SingletonQtApplication.getInstance().getCurrentActivity().mFilesToDelete = paramArrayOfString;
      }
      SingletonQtApplication.getInstance().getCurrentActivity().startActivityForResult(Intent.createChooser(paramString3, paramString2), 10);
      return;
      label243:
      paramString3.setAction("android.intent.action.SEND");
      paramString3.addFlags(1);
      localObject1 = Uri.parse("content://" + FileProvider.getAuthority() + paramArrayOfString[0]);
      localObject2 = SingletonQtApplication.getInstance().getPackageManager().getInstalledApplications(128).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
        SingletonQtApplication.getInstance().grantUriPermission(((ApplicationInfo)localObject3).packageName, (Uri)localObject1, 3);
      }
      paramString3.putExtra("android.intent.extra.STREAM", (Parcelable)localObject1);
    }
  }
  
  void getFileDataFromUri(Uri paramUri, String paramString)
  {
    System.out.println("PlatformSpecifications.java - getFileDataFromUri");
    SingletonQtApplication.getInstance().getCurrentActivity().getFileDataFromUri(paramUri, paramString);
  }
  
  String getFilePathFromUri(Uri paramUri)
  {
    System.out.println("PlatformSpecifications.java - getFilePathFromUri");
    return SingletonQtApplication.getInstance().getCurrentActivity().getFilePathFromUri(paramUri);
  }
  
  String getInAppInfo(String paramString1, String paramString2, String paramString3)
  {
    return InAppInterface.getInstance().getInAppInfo(paramString1, paramString2, paramString3);
  }
  
  String getInternalStoragePath()
  {
    return SingletonQtApplication.getInstance().getFilesDir().getAbsolutePath();
  }
  
  String getVersionCode()
  {
    try
    {
      Object localObject = SingletonQtApplication.getInstance().getPackageName();
      localObject = SingletonQtApplication.getInstance().getPackageManager().getPackageInfo((String)localObject, 0);
      localObject = "" + ((PackageInfo)localObject).versionCode;
      return localObject;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      localException.printStackTrace();
    }
    return "not-found";
  }
  
  String getVersionName()
  {
    try
    {
      Object localObject = SingletonQtApplication.getInstance().getPackageName();
      localObject = SingletonQtApplication.getInstance().getPackageManager().getPackageInfo((String)localObject, 0);
      localObject = "" + ((PackageInfo)localObject).versionName;
      return localObject;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      localException.printStackTrace();
    }
    return "not-found";
  }
  
  void initPromoCodes() {}
  
  void initializeInAppBillingService(String paramString)
  {
    InAppInterface.getInstance().initializeInAppBillingService(paramString);
  }
  
  void loadAdInterstitial(String paramString)
  {
    SingletonQtApplication.getInstance().getCurrentActivity().getAdManager().loadAdInterstitial(paramString);
  }
  
  /* Error */
  byte[] loadProtectedData()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: aconst_null
    //   7: astore 6
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore 7
    //   14: aconst_null
    //   15: astore_2
    //   16: getstatic 613	fr/anuman/HomeDesign3D/HD3DActivity:sDataLock	[Ljava/lang/Object;
    //   19: astore 8
    //   21: aload 8
    //   23: monitorenter
    //   24: aload 7
    //   26: astore_1
    //   27: new 245	java/io/File
    //   30: dup
    //   31: aload_0
    //   32: invokevirtual 615	fr/anuman/HomeDesign3D/PlatformSpecifications:getInternalStoragePath	()Ljava/lang/String;
    //   35: ldc_w 617
    //   38: invokespecial 618	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: astore 9
    //   43: aload 7
    //   45: astore_1
    //   46: aload 9
    //   48: invokevirtual 621	java/io/File:exists	()Z
    //   51: ifeq +60 -> 111
    //   54: aload 7
    //   56: astore_1
    //   57: new 623	java/io/RandomAccessFile
    //   60: dup
    //   61: aload 9
    //   63: ldc_w 625
    //   66: invokespecial 628	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   69: astore_2
    //   70: aload 4
    //   72: astore_1
    //   73: aload_2
    //   74: invokevirtual 631	java/io/RandomAccessFile:length	()J
    //   77: l2i
    //   78: newarray byte
    //   80: astore_3
    //   81: aload_3
    //   82: astore_1
    //   83: aload_2
    //   84: aload_3
    //   85: invokevirtual 632	java/io/RandomAccessFile:read	([B)I
    //   88: pop
    //   89: aload_3
    //   90: astore_1
    //   91: aload_1
    //   92: astore 4
    //   94: aload_2
    //   95: ifnull +10 -> 105
    //   98: aload_2
    //   99: invokevirtual 633	java/io/RandomAccessFile:close	()V
    //   102: aload_1
    //   103: astore 4
    //   105: aload 8
    //   107: monitorexit
    //   108: aload 4
    //   110: areturn
    //   111: aload 7
    //   113: astore_1
    //   114: getstatic 203	java/lang/System:out	Ljava/io/PrintStream;
    //   117: ldc_w 635
    //   120: invokevirtual 215	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   123: aload 6
    //   125: astore_1
    //   126: goto -35 -> 91
    //   129: astore 4
    //   131: aload 5
    //   133: astore_2
    //   134: aload_3
    //   135: astore_1
    //   136: aload 4
    //   138: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   141: aload_2
    //   142: astore 4
    //   144: aload_3
    //   145: ifnull -40 -> 105
    //   148: aload_3
    //   149: invokevirtual 633	java/io/RandomAccessFile:close	()V
    //   152: aload_2
    //   153: astore 4
    //   155: goto -50 -> 105
    //   158: astore_1
    //   159: aload_1
    //   160: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   163: aload_1
    //   164: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   167: aload_2
    //   168: astore 4
    //   170: goto -65 -> 105
    //   173: astore_1
    //   174: aload 8
    //   176: monitorexit
    //   177: aload_1
    //   178: athrow
    //   179: astore_2
    //   180: aload_2
    //   181: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   184: aload_2
    //   185: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   188: aload_1
    //   189: astore 4
    //   191: goto -86 -> 105
    //   194: astore_2
    //   195: aload_1
    //   196: ifnull +7 -> 203
    //   199: aload_1
    //   200: invokevirtual 633	java/io/RandomAccessFile:close	()V
    //   203: aload_2
    //   204: athrow
    //   205: astore_1
    //   206: aload_1
    //   207: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   210: aload_1
    //   211: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   214: goto -11 -> 203
    //   217: astore_3
    //   218: aload_2
    //   219: astore_1
    //   220: aload_3
    //   221: astore_2
    //   222: goto -27 -> 195
    //   225: astore 4
    //   227: aload_2
    //   228: astore_3
    //   229: aload_1
    //   230: astore_2
    //   231: goto -97 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	234	0	this	PlatformSpecifications
    //   26	110	1	localObject1	Object
    //   158	6	1	localIOException1	java.io.IOException
    //   173	27	1	localObject2	Object
    //   205	6	1	localIOException2	java.io.IOException
    //   219	11	1	localObject3	Object
    //   15	153	2	localObject4	Object
    //   179	6	2	localIOException3	java.io.IOException
    //   194	25	2	localObject5	Object
    //   221	10	2	localObject6	Object
    //   10	139	3	arrayOfByte	byte[]
    //   217	4	3	localObject7	Object
    //   228	1	3	localObject8	Object
    //   4	105	4	localObject9	Object
    //   129	8	4	localIOException4	java.io.IOException
    //   142	48	4	localObject10	Object
    //   225	1	4	localIOException5	java.io.IOException
    //   1	131	5	localObject11	Object
    //   7	117	6	localObject12	Object
    //   12	100	7	localObject13	Object
    //   19	156	8	arrayOfObject	Object[]
    //   41	21	9	localFile	File
    // Exception table:
    //   from	to	target	type
    //   27	43	129	java/io/IOException
    //   46	54	129	java/io/IOException
    //   57	70	129	java/io/IOException
    //   114	123	129	java/io/IOException
    //   148	152	158	java/io/IOException
    //   98	102	173	finally
    //   105	108	173	finally
    //   148	152	173	finally
    //   159	167	173	finally
    //   174	177	173	finally
    //   180	188	173	finally
    //   199	203	173	finally
    //   203	205	173	finally
    //   206	214	173	finally
    //   98	102	179	java/io/IOException
    //   27	43	194	finally
    //   46	54	194	finally
    //   57	70	194	finally
    //   114	123	194	finally
    //   136	141	194	finally
    //   199	203	205	java/io/IOException
    //   73	81	217	finally
    //   83	89	217	finally
    //   73	81	225	java/io/IOException
    //   83	89	225	java/io/IOException
  }
  
  String mountObbPackage()
  {
    String str = SingletonQtApplication.getInstance().getPackageName();
    try
    {
      waitForExternalStorageAvailable();
      Object localObject = SingletonQtApplication.getInstance().getPackageManager().getPackageInfo(str, 0);
      str = "main." + ((PackageInfo)localObject).versionCode + "." + str + ".obb";
      localObject = mountObb(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/obb/" + SingletonQtApplication.getInstance().getPackageName() + "/" + str);
      if (localObject != "") {
        return localObject;
      }
      str = mountObb(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str);
      if (str != "") {
        return ???;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
        localException.printStackTrace();
      }
      return localException;
    }
    return "";
  }
  
  String packageName()
  {
    return SingletonQtApplication.getInstance().getPackageName();
  }
  
  void pickImage()
  {
    System.out.println("HD3DActivity - performPickImage");
    Intent localIntent = new Intent("android.intent.action.OPEN_DOCUMENT");
    localIntent.addCategory("android.intent.category.OPENABLE");
    localIntent.setType("image/*");
    SingletonQtApplication.getInstance().getCurrentActivity().startActivityForResult(localIntent, 42);
  }
  
  void requestBackup()
  {
    SingletonQtApplication.getInstance().getCurrentActivity().backupApplicationData(new File(backupPath()), null);
  }
  
  int resetInApps()
  {
    return InAppInterface.getInstance().resetInApps();
  }
  
  String[] restoreInApps()
  {
    return InAppInterface.getInstance().restoreInApps();
  }
  
  String retrieveExternalStoragePath()
  {
    try
    {
      waitForExternalStorageAvailable();
      String str = Environment.getExternalStorageDirectory().getAbsolutePath();
      return str;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      localException.printStackTrace();
    }
    return "";
  }
  
  String retrieveObbPackagePath()
  {
    String str = SingletonQtApplication.getInstance().getPackageName();
    try
    {
      waitForExternalStorageAvailable();
      Object localObject = SingletonQtApplication.getInstance().getPackageManager().getPackageInfo(str, 0);
      str = "main." + ((PackageInfo)localObject).versionCode + "." + str + ".obb";
      System.out.println(" *** Version Name : " + ((PackageInfo)localObject).versionName + " - Version code : " + ((PackageInfo)localObject).versionCode);
      System.out.println("[retrieveObbPackagePath] obbFileName : \"" + str + "\"");
      localObject = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/obb/" + SingletonQtApplication.getInstance().getPackageName() + "/" + str;
      if (new File((String)localObject).exists()) {
        return localObject;
      }
      localObject = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + str;
      boolean bool = new File((String)localObject).exists();
      if (bool) {
        return ???;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Crashlytics.logException(localException);
        localException.printStackTrace();
      }
      return localException;
    }
    return "";
  }
  
  public void runJavaTests() {}
  
  /* Error */
  void saveProtectedData(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 4
    //   9: aload 5
    //   11: astore_2
    //   12: aload 6
    //   14: astore_3
    //   15: getstatic 613	fr/anuman/HomeDesign3D/HD3DActivity:sDataLock	[Ljava/lang/Object;
    //   18: astore 7
    //   20: aload 5
    //   22: astore_2
    //   23: aload 6
    //   25: astore_3
    //   26: aload 7
    //   28: monitorenter
    //   29: aload 4
    //   31: astore_2
    //   32: new 623	java/io/RandomAccessFile
    //   35: dup
    //   36: new 245	java/io/File
    //   39: dup
    //   40: aload_0
    //   41: invokevirtual 615	fr/anuman/HomeDesign3D/PlatformSpecifications:getInternalStoragePath	()Ljava/lang/String;
    //   44: ldc_w 617
    //   47: invokespecial 618	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   50: ldc_w 695
    //   53: invokespecial 628	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   56: astore_3
    //   57: aload_3
    //   58: lconst_0
    //   59: invokevirtual 698	java/io/RandomAccessFile:setLength	(J)V
    //   62: aload_3
    //   63: aload_1
    //   64: invokevirtual 699	java/io/RandomAccessFile:write	([B)V
    //   67: aload 7
    //   69: monitorexit
    //   70: aload_3
    //   71: ifnull +7 -> 78
    //   74: aload_3
    //   75: invokevirtual 633	java/io/RandomAccessFile:close	()V
    //   78: invokestatic 41	fr/anuman/HomeDesign3D/SingletonQtApplication:getInstance	()Lfr/anuman/HomeDesign3D/SingletonQtApplication;
    //   81: invokevirtual 53	fr/anuman/HomeDesign3D/SingletonQtApplication:getCurrentActivity	()Lfr/anuman/HomeDesign3D/HD3DActivity;
    //   84: invokevirtual 703	fr/anuman/HomeDesign3D/HD3DActivity:getBackupManager	()Landroid/app/backup/BackupManager;
    //   87: invokevirtual 708	android/app/backup/BackupManager:dataChanged	()V
    //   90: return
    //   91: astore 4
    //   93: aload_2
    //   94: astore_1
    //   95: aload_1
    //   96: astore_2
    //   97: aload 7
    //   99: monitorexit
    //   100: aload_1
    //   101: astore_2
    //   102: aload_1
    //   103: astore_3
    //   104: aload 4
    //   106: athrow
    //   107: astore_1
    //   108: aload_2
    //   109: astore_3
    //   110: aload_1
    //   111: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   114: aload_2
    //   115: ifnull -37 -> 78
    //   118: aload_2
    //   119: invokevirtual 633	java/io/RandomAccessFile:close	()V
    //   122: goto -44 -> 78
    //   125: astore_1
    //   126: aload_1
    //   127: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   130: aload_1
    //   131: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   134: goto -56 -> 78
    //   137: astore_1
    //   138: aload_1
    //   139: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   142: aload_1
    //   143: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   146: goto -68 -> 78
    //   149: astore_1
    //   150: aload_3
    //   151: ifnull +7 -> 158
    //   154: aload_3
    //   155: invokevirtual 633	java/io/RandomAccessFile:close	()V
    //   158: aload_1
    //   159: athrow
    //   160: astore_2
    //   161: aload_2
    //   162: invokestatic 261	com/crashlytics/android/Crashlytics:logException	(Ljava/lang/Throwable;)V
    //   165: aload_2
    //   166: invokevirtual 636	java/io/IOException:printStackTrace	()V
    //   169: goto -11 -> 158
    //   172: astore 4
    //   174: aload_3
    //   175: astore_1
    //   176: goto -81 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	PlatformSpecifications
    //   0	179	1	paramArrayOfByte	byte[]
    //   11	108	2	localObject1	Object
    //   160	6	2	localIOException	java.io.IOException
    //   14	161	3	localObject2	Object
    //   7	23	4	localObject3	Object
    //   91	14	4	localObject4	Object
    //   172	1	4	localObject5	Object
    //   1	20	5	localObject6	Object
    //   4	20	6	localObject7	Object
    //   18	80	7	arrayOfObject	Object[]
    // Exception table:
    //   from	to	target	type
    //   32	57	91	finally
    //   97	100	91	finally
    //   15	20	107	java/io/IOException
    //   26	29	107	java/io/IOException
    //   104	107	107	java/io/IOException
    //   118	122	125	java/io/IOException
    //   74	78	137	java/io/IOException
    //   15	20	149	finally
    //   26	29	149	finally
    //   104	107	149	finally
    //   110	114	149	finally
    //   154	158	160	java/io/IOException
    //   57	70	172	finally
  }
  
  void sendMail(String paramString1, String[] paramArrayOfString1, String paramString2, String[] paramArrayOfString2, boolean paramBoolean)
  {
    Object localObject3 = null;
    Object localObject1 = null;
    try
    {
      Object localObject2 = new Intent("android.intent.action.SENDTO");
      ((Intent)localObject2).setData(Uri.parse("mailto:some@emaildomain.com"));
      Object localObject4 = SingletonQtApplication.getInstance().getPackageManager().queryIntentActivities((Intent)localObject2, 0);
      if (localObject4 != null)
      {
        localObject2 = localObject4;
        if (((List)localObject4).size() != 0) {}
      }
      else
      {
        localObject2 = new Intent("android.intent.action.SEND_MULTIPLE");
        ((Intent)localObject2).setType("message/rfc822");
        localObject2 = SingletonQtApplication.getInstance().getPackageManager().queryIntentActivities((Intent)localObject2, 0);
      }
      if (localObject2 != null)
      {
        if (((List)localObject2).size() == 1) {
          localObject1 = (ResolveInfo)((List)localObject2).get(0);
        }
        localObject4 = ((List)localObject2).iterator();
        for (;;)
        {
          localObject3 = localObject1;
          if (!((Iterator)localObject4).hasNext()) {
            break;
          }
          localObject2 = (ResolveInfo)((Iterator)localObject4).next();
          if (true == ((ResolveInfo)localObject2).isDefault) {
            localObject1 = localObject2;
          }
        }
      }
      localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
      if (localObject3 != null) {
        ((Intent)localObject1).setComponent(new ComponentName(localObject3.activityInfo.packageName, localObject3.activityInfo.name));
      }
      ((Intent)localObject1).setType("plain/text");
      ((Intent)localObject1).putExtra("android.intent.extra.SUBJECT", paramString1);
      ((Intent)localObject1).putExtra("android.intent.extra.TEXT", paramString2);
      ((Intent)localObject1).putExtra("android.intent.extra.EMAIL", paramArrayOfString1);
      if (paramArrayOfString2.length > 0)
      {
        paramString1 = new ArrayList();
        int i = 0;
        while (i < paramArrayOfString2.length)
        {
          paramString1.add(Uri.parse("content://" + FileProvider.getAuthority() + paramArrayOfString2[i]));
          i += 1;
        }
        ((Intent)localObject1).putParcelableArrayListExtra("android.intent.extra.STREAM", paramString1);
      }
      if (paramBoolean == true) {
        SingletonQtApplication.getInstance().getCurrentActivity().mFilesToDelete = paramArrayOfString2;
      }
      SingletonQtApplication.getInstance().getCurrentActivity().startActivityForResult((Intent)localObject1, 10);
      return;
    }
    catch (Exception paramString1)
    {
      System.out.println("Error during mail sending : " + paramString1);
      Crashlytics.logException(paramString1);
    }
  }
  
  void setAdBannerId(String paramString)
  {
    SingletonQtApplication.getInstance().getCurrentActivity().getAdManager().setAdBannerId(paramString);
  }
  
  void startMethodTracing()
  {
    String str = SingletonQtApplication.getInstance().getPackageName() + ".trace";
    Debug.startMethodTracing(str, 65536000);
    System.out.println("[startMethodTracing] output : \"" + str + "\"");
  }
  
  void stopMethodTracing()
  {
    System.out.println("[stopMethodTracing]");
    Debug.stopMethodTracing();
  }
  
  String userLanguage()
  {
    try
    {
      String str = SingletonQtApplication.getInstance().getResources().getConfiguration().locale.getLanguage();
      return str;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
      localException.printStackTrace();
    }
    return "";
  }
}
