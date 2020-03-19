package com.gameloft.android.ANMP.GloftPEHM.installer;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RemoteViews;
import com.gameloft.android.ANMP.GloftPEHM.ApplicationSetUp;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.Device;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.HTTP;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.LowProfileListener;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.SUtils;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.Tracking;
import com.gameloft.android.ANMP.GloftPEHM.GLUtils.XPlayer;
import com.gameloft.android.ANMP.GloftPEHM.Game;
import com.gameloft.android.ANMP.GloftPEHM.installer.utils.CRC;
import com.gameloft.android.ANMP.GloftPEHM.installer.utils.DownloadComponent;
import com.gameloft.android.ANMP.GloftPEHM.installer.utils.Downloader;
import com.gameloft.android.ANMP.GloftPEHM.installer.utils.GPUInstallerGLSurfaceView;
import com.gameloft.android.ANMP.GloftPEHM.installer.utils.HttpClient;
import com.gameloft.android.ANMP.GloftPEHM.installer.utils.Tracker;
import com.gameloft.android.ANMP.GloftPEHM.utils.GoogleAnalyticsConstants.Label;
import com.gameloft.android.ANMP.GloftPEHM.utils.GoogleAnalyticsTracker;
import com.google.analytics.tracking.android.GoogleAnalytics;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class GameInstaller
  extends Activity
  implements com.gameloft.android.ANMP.GloftPEHM.installer.utils.c, Runnable
{
  public static String DATA_PATH;
  public static final int LAYOUT_BLACK = 24;
  public static final int LAYOUT_CHECKING_REQUIRED_FILES = 0;
  public static final int LAYOUT_CONFIRM_3G = 1;
  public static final int LAYOUT_CONFIRM_UPDATE = 2;
  public static final int LAYOUT_CONFIRM_WAITING_FOR_WIFI = 3;
  public static final int LAYOUT_DOWNLOAD_ANYTIME = 4;
  public static final int LAYOUT_DOWNLOAD_FILES = 5;
  public static final int LAYOUT_DOWNLOAD_FILES_CANCEL_QUESTION = 7;
  public static final int LAYOUT_DOWNLOAD_FILES_ERROR = 8;
  public static final int LAYOUT_DOWNLOAD_FILES_NO_WIFI_QUESTION = 9;
  public static final int LAYOUT_DOWNLOAD_FILES_QUESTION = 10;
  public static final int LAYOUT_LICENSE_INFO = 13;
  public static final int LAYOUT_LOGO = 14;
  public static final int LAYOUT_MKP_DEVICE_NOT_SUPPORTED = 15;
  public static final int LAYOUT_NO_DATA_CONNECTION_FOUND = 16;
  public static final int LAYOUT_NO_GP_ACCOUNT_DETECTED = 29;
  public static final int LAYOUT_RETRY_UPDATE_VERSION = 17;
  public static final int LAYOUT_SD_SPACE_INFO = 18;
  public static final int LAYOUT_SEARCHING_FOR_NEW_VERSION = 19;
  public static final int LAYOUT_SEARCHING_FOR_WIFI = 20;
  public static final int LAYOUT_SUCCESS_DOWNLOADED = 21;
  public static final int LAYOUT_UNZIP_FILES = 27;
  public static final int LAYOUT_UNZIP_FILES_CANCEL_QUESTION = 28;
  public static final int LAYOUT_VERIFYING_FILES = 22;
  public static final int LAYOUT_WAITING_FOR_WIFI = 23;
  public static String LIBS_PATH;
  public static String SaveFolder;
  private static int TAP_COUNT_MAX;
  public static boolean bIsPaused;
  public static Boolean isReached;
  private static int leftTapCount;
  public static TelephonyManager mDeviceInfo;
  public static String mPreferencesName;
  private static AlertDialog m_Dialog;
  private static int m_delayTime;
  private static String m_errorMessage;
  public static long m_iDownloadedSize;
  public static long m_iRealRequiredSize;
  private static Object m_objectToastLock;
  public static Downloader m_pDownloader;
  public static String m_portalCode;
  private static String m_prevErrorMessage;
  public static GameInstaller m_sInstance;
  public static String m_sVersion;
  private static int m_toastExtra;
  private static int m_toastSize;
  public static String marketPath;
  private static int pack_NoFiles = -1;
  private static long pack_biggestFile;
  private static int rightTapCount;
  private static ArrayList<String> sNativeLibs = new ArrayList();
  private static boolean sUpdateAPK;
  public static boolean s_files_changed;
  public static boolean s_isPauseGame;
  public static String s_lpsVersion;
  public static boolean sbStarted = false;
  public static String sd_folder;
  static long slLastIndex;
  private static long startTime;
  private static boolean statePressA;
  private static boolean statePressB;
  private static boolean statePressC;
  public final int A = 3;
  public final int B = 4;
  public final int C = 5;
  public final int D = 6;
  public final int E = 7;
  public final int F = 8;
  public final int G = 9;
  public final int H = 10;
  public final int I = 11;
  public final int J = 12;
  public final int K = 13;
  public final int L = 14;
  public final int M = 20;
  public final int N = 21;
  public final int O = 23;
  public final int P = 24;
  public final int Q = 25;
  public final int R = 26;
  public final int S = 27;
  public final int T = 28;
  public final int U = 29;
  public final int V = 30;
  public final int W = 31;
  public final int X = 32;
  public final int Y = 33;
  public final int Z = 41;
  public final int a = 7;
  public final int aA = 1;
  public final int aB = 2;
  public final int aC = 3;
  public final int aD = 4;
  public int[] aE = { 0, 0, 0, 0, 0 };
  WifiManager aF;
  ConnectivityManager aG;
  WifiManager.WifiLock aH;
  PowerManager.WakeLock aI;
  public boolean aJ = false;
  public boolean aK = false;
  public DataInputStream aL;
  FileOutputStream aM = null;
  int aN = 0;
  int aO = 0;
  com.gameloft.android.ANMP.GloftPEHM.installer.utils.g aP = null;
  int aQ = 0;
  public boolean aR = false;
  public boolean aS = false;
  public String aT;
  public boolean aU = true;
  public int aV = 0;
  public int aW = 0;
  public int aX = 0;
  public final int aY = 30;
  public boolean aZ = false;
  public final int aa = 42;
  public final int ab = -1;
  public final int ac = 0;
  public final int ad = 1;
  public final int ae = 2;
  public final int af = 3;
  public final int ag = 4;
  public final int ah = 5;
  public final int ai = 6;
  public final int aj = 7;
  public final int ak = 8;
  public final int al = 9;
  public final int am = 10;
  public int an;
  public int ao;
  public int ap = 0;
  public String aq;
  public int ar = 0;
  public int as;
  public int at;
  public int au;
  public int av = 0;
  public int aw = 1;
  public int ax = 2;
  public int ay = 3;
  public final int az = 0;
  NotificationManager b;
  private final boolean bA = false;
  private final boolean bB = false;
  private final boolean bC = true;
  private final int bD = 0;
  private final int bE = 1;
  private final int bF = 2;
  private int bG = 0;
  private boolean bH = false;
  private final int bI = 32768;
  private final int bJ = 7176;
  private final String bK = "com.gameloft.android.ANMP.GloftPEHM.Game";
  private String bL = "";
  private ArrayList<DownloadComponent> bM = new ArrayList();
  private int bN = 0;
  private boolean bO = false;
  private final int bP = 3000;
  private final int bQ = 30000;
  private long bR = 0L;
  private long bS = 0L;
  private int bT = -1;
  private int bU = -1;
  private Device bV;
  private XPlayer bW;
  private boolean bX = false;
  private com.gameloft.android.ANMP.GloftPEHM.installer.utils.h bY;
  private com.gameloft.android.ANMP.GloftPEHM.installer.utils.d bZ;
  public boolean ba = false;
  public boolean bb = true;
  public int bc = 0;
  NetworkInfo bd = null;
  Vector<Pair<String, Long>> be;
  Vector<Pair<String, Long>> bf;
  public boolean bg = false;
  BroadcastReceiver bh = null;
  BroadcastReceiver bi = null;
  public boolean bj = false;
  public boolean bk = false;
  public boolean bl = true;
  public View.OnClickListener bm = new c(this);
  Notification bn;
  PendingIntent bo;
  private int bu = -1;
  private boolean bv = true;
  private boolean bw = false;
  private String bx = "/pack.info";
  private boolean by = false;
  private int bz = 0;
  Vector<com.gameloft.android.ANMP.GloftPEHM.installer.utils.g> c = null;
  private boolean ca = false;
  private final int cb = 0;
  private final int cc = 1;
  private final int cd = 2;
  private final int ce = 3;
  private ArrayList<Button> cf;
  private int cg = -1;
  private int ch = 0;
  private int ci = -1;
  private int cj = 0;
  private int ck = 0;
  private long cl = 0L;
  private long cm = 1000L;
  private Handler cn;
  private int co = 0;
  Vector<com.gameloft.android.ANMP.GloftPEHM.installer.utils.g> d = new Vector();
  Vector<com.gameloft.android.ANMP.GloftPEHM.installer.utils.g> e = new Vector();
  long f = 0L;
  long g = 0L;
  long h = 0L;
  boolean i = false;
  long j = 0L;
  long k = 0L;
  long l = 0L;
  DecimalFormat m;
  final int n = 0;
  final int o = -1;
  final int p = -2;
  final int q = -3;
  final int r = -4;
  final int s = -5;
  AssetManager t;
  HttpClient u;
  boolean v = false;
  boolean w = false;
  public final int x = 0;
  public final int y = 1;
  public final int z = 2;
  
  static
  {
    s_isPauseGame = false;
    m_sInstance = null;
    mPreferencesName = "GamePrefs";
    sUpdateAPK = false;
    m_portalCode = "";
    SaveFolder = null;
    sd_folder = "/sdcard/gameloft/games/GloftPEHM";
    DATA_PATH = sd_folder + "/";
    LIBS_PATH = "/libs/";
    s_files_changed = false;
    s_lpsVersion = "";
    m_sVersion = "";
    marketPath = Environment.getExternalStorageDirectory() + "/Android/obb/com.gameloft.android.ANMP.GloftPEHM";
    slLastIndex = 0L;
    isReached = null;
    bIsPaused = false;
    startTime = 0L;
    leftTapCount = 0;
    rightTapCount = 0;
    TAP_COUNT_MAX = 3;
    m_toastSize = 0;
    m_delayTime = 1500;
    m_toastExtra = 20;
    m_objectToastLock = new Object();
    m_errorMessage = "";
    m_prevErrorMessage = "";
    statePressA = false;
    statePressB = false;
    statePressC = false;
    pack_biggestFile = -1L;
  }
  
  public GameInstaller()
  {
    SUtils.setContext(this);
  }
  
  private boolean A()
  {
    if (this.bG == 1) {}
    do
    {
      return false;
      this.bd = this.aG.getActiveNetworkInfo();
    } while ((this.bd == null) || (this.bd.getType() == 1) || (!this.bd.isConnected()));
    return true;
  }
  
  private void B()
  {
    String[] arrayOfString = new File(SaveFolder).list();
    int i1 = 0;
    for (;;)
    {
      if ((i1 >= arrayOfString.length) || ((arrayOfString[i1].startsWith("pack")) && (arrayOfString[i1].endsWith(".info")))) {}
      try
      {
        Vector localVector = new com.gameloft.android.ANMP.GloftPEHM.installer.utils.h(this).a(SaveFolder + "/" + arrayOfString[i1]);
        this.e.addAll(localVector);
        i1 += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  private Vector<String> C()
  {
    Vector localVector = new Vector();
    for (;;)
    {
      try
      {
        localObject = getExternalFilesDir(null);
        if (localObject != null)
        {
          localVector.add(((File)localObject).getAbsolutePath());
          if ((((File)localObject).exists()) || (((File)localObject).list().length == 0))
          {
            ((File)localObject).delete();
            new File(((File)localObject).getAbsolutePath().substring(0, ((File)localObject).getAbsolutePath().lastIndexOf("/files"))).delete();
          }
        }
      }
      catch (Exception localException2)
      {
        Object localObject;
        BufferedReader localBufferedReader;
        String str;
        continue;
      }
      try
      {
        localObject = new DataInputStream(new FileInputStream("/proc/mounts"));
        localBufferedReader = new BufferedReader(new InputStreamReader((InputStream)localObject));
        str = localBufferedReader.readLine();
        if (str != null)
        {
          if (((!str.contains("/mnt/sdcard")) && (!str.contains("/storage/sdcard"))) || (str.contains("android_secure"))) {
            continue;
          }
          str = str.substring(str.indexOf(' ') + 1);
          localVector.add(str.substring(0, str.indexOf(' ')) + "/Android/data/com.gameloft.android.ANMP.GloftPEHM" + "/files");
          continue;
        }
        localBufferedReader.close();
      }
      catch (Exception localException1)
      {
        localVector.add("/sdcard/gameloft/games/GloftPEHM");
        return localVector;
      }
      localException1.close();
    }
  }
  
  private String D()
  {
    Object localObject4 = "";
    Object localObject1;
    if (Build.VERSION.SDK_INT >= 8) {
      localObject1 = localObject4;
    }
    for (;;)
    {
      Object localObject3;
      try
      {
        new Vector().add("/sdcard/gameloft/games/GloftPEHM");
        localObject1 = localObject4;
        Object localObject5 = C();
        localObject1 = localObject4;
        Iterator localIterator = ((Vector)localObject5).iterator();
        Object localObject2 = localObject4;
        localObject1 = localObject4;
        if (localIterator.hasNext())
        {
          localObject1 = localObject4;
          localObject2 = (String)localIterator.next();
          localObject1 = localObject4;
          File localFile = new File((String)localObject2);
          localObject1 = localObject4;
          if (!localFile.exists()) {
            continue;
          }
          localObject1 = localObject4;
          if (localFile.list().length <= 0) {
            continue;
          }
        }
        localObject1 = localObject2;
        this.be = new Vector();
        localObject1 = localObject2;
        this.bf = new Vector();
        localObject1 = localObject2;
        localObject4 = ((Vector)localObject5).iterator();
        localObject1 = localObject2;
        if (((Iterator)localObject4).hasNext())
        {
          localObject1 = localObject2;
          localObject5 = (String)((Iterator)localObject4).next();
          localObject1 = localObject2;
          this.be.add(new Pair(localObject5, Long.valueOf(c((String)localObject5))));
          localObject1 = localObject2;
          localIterator = this.bf.iterator();
          localObject1 = localObject2;
          if (!localIterator.hasNext()) {
            break label338;
          }
          localObject1 = localObject2;
          if (!((Long)((Pair)localIterator.next()).second).equals(Long.valueOf(c((String)localObject5)))) {
            continue;
          }
          i1 = 0;
          if (i1 == 0) {
            continue;
          }
          localObject1 = localObject2;
          this.bf.add(new Pair(localObject5, Long.valueOf(c((String)localObject5))));
          continue;
        }
        localObject1 = localObject3;
      }
      catch (Exception localException)
      {
        localObject3 = localObject1;
        if (localObject1.equals("")) {
          localObject3 = "/sdcard/gameloft/games/GloftPEHM";
        }
        return localObject3;
      }
      if (((String)localObject3).equals("")) {
        return "";
      }
      return localObject3;
      return "/sdcard/gameloft/games/GloftPEHM";
      label338:
      int i1 = 1;
    }
  }
  
  private String E()
  {
    String str = SUtils.getPreferenceString("SDFolder", "", mPreferencesName);
    str = SUtils.getOverriddenSetting(str + "/qaTestingConfigs.txt", "DATA_LINK");
    if (str != null)
    {
      this.bw = true;
      return str;
    }
    this.bw = false;
    str = SUtils.ReadFile(2130968577);
    int i3 = str.indexOf("DYNAMIC:") + 8;
    int i2 = str.indexOf('\r', i3);
    int i1 = i2;
    if (i2 == -1) {
      i1 = str.length();
    }
    str = str.substring(i3, i1);
    str = str + "?model=" + Device.getPhoneModel() + "&device=" + Device.getPhoneDevice() + "&product=1367" + "&version=2.2.1";
    return (str + "&portal=" + m_portalCode).replaceAll("\\s+", "%20");
  }
  
  private int F()
  {
    this.h = 0L;
    try
    {
      Object localObject = SUtils.getPreferenceString("SDFolder", "", mPreferencesName);
      if (((String)localObject).contains("com.gameloft.android.ANMP.GloftPEHM")) {}
      for (localObject = new File(((String)localObject).substring(0, ((String)localObject).indexOf("com.gameloft.android.ANMP.GloftPEHM")));; localObject = new File("/sdcard/"))
      {
        if (!((File)localObject).exists()) {
          ((File)localObject).mkdirs();
        }
        localObject = new StatFs(((File)localObject).getAbsolutePath());
        this.g = ((int)(((StatFs)localObject).getBlockSize() * ((StatFs)localObject).getAvailableBlocks() / 1048576L));
        if ((this.g == 0L) && (!a(0)))
        {
          int i1 = hasSDCard();
          this.aE[0] = i1;
        }
        localObject = this.bM.iterator();
        while (((Iterator)localObject).hasNext())
        {
          DownloadComponent localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
          this.h += localDownloadComponent.f();
        }
        if (localObject != "/sdcard/gameloft/games/GloftPEHM") {
          break;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        this.g = 0L;
        continue;
        File localFile = new File(localException);
      }
      if (this.h > 0L) {
        this.h += 0L;
      }
      if (this.g <= this.h) {
        return 1;
      }
    }
    return 0;
  }
  
  private void G()
  {
    setResult(1);
    if (!isFinishing()) {
      finish();
    }
  }
  
  private static String GetCurrentVersion(String paramString)
  {
    try
    {
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "";
      if (!sUpdateAPK) {
        arrayOfString = paramString.split("SERVER_URL");
      }
      paramString = arrayOfString[0].split("VERSION_AVAILABLE");
      paramString = paramString[1].substring(2, paramString[1].length() - 2);
      return paramString;
    }
    catch (Exception paramString) {}
    return "3.1.4";
  }
  
  private static String GetLayoutName(int paramInt)
  {
    switch (paramInt)
    {
    case 6: 
    case 11: 
    case 12: 
    case 25: 
    case 26: 
    case 28: 
    default: 
      return "Unknown Layout(" + paramInt + ")";
    case 1: 
      return "LAYOUT_CONFIRM_3G";
    case 3: 
      return "LAYOUT_CONFIRM_WAITING_FOR_WIFI";
    case 4: 
      return "LAYOUT_DOWNLOAD_ANYTIME";
    case 29: 
      return "LAYOUT_NO_GP_ACCOUNT_DETECTED";
    case 5: 
      return "LAYOUT_DOWNLOAD_FILES";
    case 7: 
      return "LAYOUT_DOWNLOAD_FILES_CANCEL_QUESTION";
    case 8: 
      return "LAYOUT_DOWNLOAD_FILES_ERROR";
    case 9: 
      return "LAYOUT_DOWNLOAD_FILES_NO_WIFI_QUESTION";
    case 10: 
      return "LAYOUT_DOWNLOAD_FILES_QUESTION";
    case 13: 
      return "LAYOUT_LICENSE_INFO";
    case 14: 
      return "LAYOUT_LOGO";
    case 16: 
      return "LAYOUT_NO_DATA_CONNECTION_FOUND";
    case 18: 
      return "LAYOUT_SD_SPACE_INFO";
    case 22: 
      return "LAYOUT_VERIFYING_FILES";
    case 20: 
      return "LAYOUT_SEARCHING_FOR_WIFI";
    case 21: 
      return "LAYOUT_SUCCESS_DOWNLOADED";
    case 23: 
      return "LAYOUT_WAITING_FOR_WIFI";
    case 24: 
      return "LAYOUT_MAIN";
    case 19: 
      return "LAYOUT_SEARCHING_FOR_NEW_VERSION";
    case 2: 
      return "LAYOUT_CONFIRM_UPDATE";
    case 17: 
      return "LAYOUT_RETRY_UPDATE_VERSION";
    case 0: 
      return "LAYOUT_CHECKING_REQUIRED_FILES";
    case 15: 
      return "LAYOUT_MKP_DEVICE_NOT_SUPPORTED";
    }
    return "LAYOUT_UNZIP_FILES";
  }
  
  private static String GetUrl(String paramString)
  {
    return paramString.substring(paramString.indexOf("http"));
  }
  
  private void H()
  {
    if (isFinishing()) {
      return;
    }
    setResult(2);
    finish();
  }
  
  private String I()
  {
    String str = "" + "1";
    str = str + "1";
    str = str + "0";
    str = str + "0";
    switch (this.bG)
    {
    default: 
      str = str + "x";
    }
    for (;;)
    {
      str = str + "-google_market";
      return str + "-1367";
      str = str + "0";
      continue;
      str = str + "1";
      continue;
      str = str + "2";
    }
  }
  
  private void J()
  {
    this.bj = true;
    if (this.bi == null)
    {
      this.bl = true;
      this.bi = new n(this);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
      registerReceiver(this.bi, localIntentFilter);
    }
  }
  
  private void K()
  {
    if (this.bi != null)
    {
      unregisterReceiver(this.bi);
      this.bi = null;
    }
  }
  
  private void L()
  {
    switch (this.ci)
    {
    case 3: 
    case 4: 
    case 6: 
    case 11: 
    case 12: 
    case 14: 
    case 19: 
    case 22: 
    case 24: 
    case 25: 
    case 26: 
    case 28: 
    default: 
      return;
    case 2: 
      b(2131230760);
      return;
    case 29: 
      b(2131230758);
      return;
    case 27: 
      b(2131230762);
      return;
    case 5: 
      b(2131230762);
      return;
    case 7: 
      b(2131230760);
      return;
    case 8: 
      b(2131230760);
      return;
    case 9: 
      b(2131230762);
      return;
    case 10: 
      b(2131230760);
      return;
    case 13: 
      b(2131230758);
      return;
    case 15: 
      b(2131230758);
      return;
    case 16: 
      b(2131230760);
      return;
    case 17: 
      b(2131230760);
      return;
    case 18: 
      b(2131230758);
      return;
    case 20: 
      b(2131230762);
      return;
    case 21: 
      b(2131230758);
      return;
    }
    b(2131230760);
  }
  
  private void M()
  {
    if (this.b == null) {
      this.b = ((NotificationManager)getSystemService("notification"));
    }
    this.bo = PendingIntent.getActivity(this, 0, new Intent(this, GameInstaller.class), 0);
    this.bn = new Notification();
    this.bn.icon = 2130837615;
    this.bn.when = System.currentTimeMillis();
    this.bn.contentIntent = this.bo;
  }
  
  private boolean N()
  {
    if (!new File(DATA_PATH + "InsTime").exists()) {
      SaveDateLastUpdate(DATA_PATH);
    }
    long l1;
    long l2;
    do
    {
      return false;
      String[] arrayOfString = SUtils.ReadFile(DATA_PATH + "InsTime").split("/");
      l1 = Long.parseLong(arrayOfString[0]);
      l2 = System.currentTimeMillis() / 1000L;
      if (arrayOfString.length > 2)
      {
        SaveDateLastUpdate(DATA_PATH);
        return false;
      }
      if (l2 - l1 >= 604800L)
      {
        SaveDateLastUpdate(DATA_PATH);
        return true;
      }
    } while (l2 - l1 >= 0L);
    SaveDateLastUpdate(DATA_PATH);
    return false;
  }
  
  private void O()
  {
    this.aT = (Build.MANUFACTURER + Build.MODEL);
    pack_biggestFile = -1L;
    pack_NoFiles = 0;
    Iterator localIterator = this.bM.iterator();
    while (localIterator.hasNext())
    {
      DownloadComponent localDownloadComponent = (DownloadComponent)localIterator.next();
      localDownloadComponent.a(this);
      pack_NoFiles += localDownloadComponent.m();
      if (pack_biggestFile < localDownloadComponent.l()) {
        pack_biggestFile = localDownloadComponent.l();
      }
    }
  }
  
  private boolean P()
  {
    Iterator localIterator = this.bM.iterator();
    boolean bool = true;
    if (localIterator.hasNext())
    {
      if (((DownloadComponent)localIterator.next()).n()) {
        break label41;
      }
      bool = false;
    }
    label41:
    for (;;)
    {
      break;
      return bool;
    }
  }
  
  private static boolean SaveDateLastUpdate(String paramString)
  {
    String str = String.format("%d/", new Object[] { Long.valueOf(System.currentTimeMillis() / 1000L) });
    SUtils.WriteFile(paramString + "InsTime", str);
    return true;
  }
  
  private void a(int paramInt1, int paramInt2)
  {
    this.aE[paramInt1] = paramInt2;
  }
  
  private void a(int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    if (paramInt1 == 12) {}
    try
    {
      if (System.currentTimeMillis() - this.cl > this.cm)
      {
        if (this.bn == null)
        {
          if (this.b == null) {
            this.b = ((NotificationManager)getSystemService("notification"));
          }
          this.bo = PendingIntent.getActivity(this, 0, new Intent(this, GameInstaller.class), 0);
          this.bn = new Notification();
          this.bn.icon = 2130837615;
          this.bn.when = System.currentTimeMillis();
          this.bn.contentIntent = this.bo;
        }
        for (;;)
        {
          this.bn.contentView.setImageViewResource(2131230795, 2130837615);
          this.bn.contentView.setTextViewText(2131230796, getString(2131034935));
          this.cl = System.currentTimeMillis();
          this.b.notify(7176, this.bn);
          return;
          this.bn.contentView = new RemoteViews(getApplicationContext().getPackageName(), 2130903064);
          this.bn.contentView.setTextViewText(2131230797, getString(2131034257));
          this.bn.flags = 16;
          b(null, getString(2131034257));
          continue;
          this.bn.contentView = new RemoteViews(getApplicationContext().getPackageName(), 2130903064);
          this.bn.contentView.setTextViewText(2131230797, getString(2131034256));
          this.bn.flags = 16;
          b(null, getString(2131034256));
          continue;
          this.bn.contentView = new RemoteViews(getApplicationContext().getPackageName(), 2130903065);
          this.bn.contentView.setProgressBar(2131230798, paramInt2, paramInt3, false);
          this.bn.contentView.setTextViewText(2131230797, paramString);
          this.bn.flags = 18;
          continue;
          switch (paramInt1)
          {
          }
        }
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  private void a(int paramInt, boolean paramBoolean)
  {
    runOnUiThread(new j(this, paramInt, paramBoolean));
  }
  
  private void a(long paramLong)
  {
    if (sd_folder.equals(""))
    {
      Iterator localIterator = this.be.iterator();
      while (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        if (((Long)localPair.second).longValue() >= paramLong)
        {
          sd_folder = (String)localPair.first;
          SUtils.setPreference("SDFolder", sd_folder, mPreferencesName);
          DATA_PATH = sd_folder + "/";
        }
      }
    }
    if (sd_folder.equals(""))
    {
      sd_folder = "/sdcard/gameloft/games/GloftPEHM";
      DATA_PATH = sd_folder + "/";
      SUtils.setPreference("SDFolder", sd_folder, mPreferencesName);
    }
  }
  
  private void a(Context paramContext)
  {
    this.bi = new n(this);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
    paramContext.registerReceiver(this.bi, localIntentFilter);
  }
  
  private void a(Button paramButton)
  {
    runOnUiThread(new f(this, paramButton));
  }
  
  private void a(String paramString1, String paramString2)
  {
    try
    {
      Object localObject1 = paramString2 + "/pack.info";
      paramString1 = new DataInputStream(new FileInputStream(paramString1));
      Object localObject2 = new com.gameloft.android.ANMP.GloftPEHM.installer.utils.h(this);
      int i4 = paramString1.readInt();
      Object localObject3 = new FileOutputStream((String)localObject1);
      int i1 = 0;
      for (;;)
      {
        byte[] arrayOfByte = new byte[i2];
        paramString1.readFully(arrayOfByte);
        ((FileOutputStream)localObject3).write(arrayOfByte);
        ((FileOutputStream)localObject3).flush();
        i1 = i2 + i1;
        while (i1 >= i4)
        {
          ((FileOutputStream)localObject3).close();
          localObject2 = ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.h)localObject2).a((String)localObject1);
          i3 = ((Vector)localObject2).size();
          localObject3 = new com.gameloft.android.ANMP.GloftPEHM.installer.utils.b(paramString1);
          arrayOfByte = new byte[32768];
          i1 = 0;
          while ((i1 < i3) && (!this.v))
          {
            com.gameloft.android.ANMP.GloftPEHM.installer.utils.g localG = (com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)((Vector)localObject2).elementAt(i1);
            new File(paramString2 + "/" + localG.c());
            ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.b)localObject3).a(localG.f());
            com.gameloft.android.ANMP.GloftPEHM.installer.utils.l localL = new com.gameloft.android.ANMP.GloftPEHM.installer.utils.l((InputStream)localObject3);
            paramString1 = localL.getNextEntry();
            localObject1 = localG.a().replace(".\\\\", "").replace(".\\", "").replace("\\", "/");
            paramString1 = paramString2 + "/" + (String)localObject1 + "/" + paramString1.getName();
            if (paramString1.endsWith("/")) {}
            for (localObject1 = paramString1;; localObject1 = new File(paramString1).getParent())
            {
              if (localObject1 != null)
              {
                localObject1 = new File((String)localObject1);
                if (!((File)localObject1).exists()) {
                  ((File)localObject1).mkdirs();
                }
              }
              localObject1 = paramString1;
              if (paramString1.endsWith(".so"))
              {
                localObject1 = LIBS_PATH + localG.b();
                addNativeLib(localG.b());
              }
              paramString1 = new FileOutputStream((String)localObject1);
              if (((String)localObject1).endsWith(".so")) {
                makeLibExecutable((String)localObject1);
              }
              localObject1 = new BufferedOutputStream(paramString1, arrayOfByte.length);
              i2 = 0;
              for (;;)
              {
                i4 = localL.read(arrayOfByte, 0, 32768);
                if (i4 < 0) {
                  break;
                }
                i2 += i4;
                ((BufferedOutputStream)localObject1).write(arrayOfByte, 0, i4);
              }
            }
            i4 = this.bc;
            this.bc = (i2 / 1024 + i4);
            if (!bIsPaused) {
              runOnUiThread(new a(this));
            }
            ((BufferedOutputStream)localObject1).flush();
            ((BufferedOutputStream)localObject1).close();
            paramString1.close();
            localL.closeEntry();
            ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.b)localObject3).b();
            ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.b)localObject3).a();
            i1 += 1;
          }
        }
        int i3 = i4 - i1;
        int i2 = i3;
        if (i3 > 32768) {
          i2 = 32768;
        }
      }
      return;
    }
    catch (Exception paramString1) {}
  }
  
  private void a(ArrayList<Button> paramArrayList)
  {
    this.cf = paramArrayList;
  }
  
  private boolean a(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    boolean bool3 = false;
    boolean bool4 = false;
    paramBoolean = false;
    paramString3 = paramString4 + "/" + paramString3 + "/";
    paramString1 = paramString2 + "/" + paramString1;
    boolean bool1 = bool3;
    boolean bool2 = bool4;
    try
    {
      paramString2 = new ZipFile(paramString1);
      bool1 = bool3;
      bool2 = bool4;
      paramString4 = paramString2.entries();
      for (;;)
      {
        bool1 = paramBoolean;
        bool2 = paramBoolean;
        if (!paramString4.hasMoreElements()) {
          break;
        }
        bool1 = paramBoolean;
        bool2 = paramBoolean;
        if (this.v) {
          break;
        }
        bool1 = paramBoolean;
        bool2 = paramBoolean;
        Object localObject1 = (ZipEntry)paramString4.nextElement();
        bool1 = paramBoolean;
        bool2 = paramBoolean;
        if (((ZipEntry)localObject1).getName().endsWith(".so"))
        {
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          paramString1 = LIBS_PATH + ((ZipEntry)localObject1).getName();
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          addNativeLib(((ZipEntry)localObject1).getName());
        }
        Object localObject2;
        for (;;)
        {
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          localObject2 = new File(paramString1);
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          new File(((File)localObject2).getParent()).mkdirs();
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          if (!((File)localObject2).exists()) {
            break label372;
          }
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          if (((File)localObject2).isDirectory()) {
            break label372;
          }
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          this.bc = ((int)(this.bc + ((File)localObject2).length() / 1024L));
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          a();
          break;
          bool1 = paramBoolean;
          bool2 = paramBoolean;
          paramString1 = paramString3 + ((ZipEntry)localObject1).getName();
        }
        label372:
        bool1 = paramBoolean;
        bool2 = paramBoolean;
        if (!paramString1.endsWith("/"))
        {
          bool3 = true;
          bool4 = true;
          paramBoolean = true;
          bool1 = bool3;
          bool2 = bool4;
          localObject1 = new BufferedInputStream(paramString2.getInputStream((ZipEntry)localObject1));
          bool1 = bool3;
          bool2 = bool4;
          localObject2 = new byte['䀀'];
          bool1 = bool3;
          bool2 = bool4;
          FileOutputStream localFileOutputStream = new FileOutputStream(paramString1);
          bool1 = bool3;
          bool2 = bool4;
          if (paramString1.endsWith(".so"))
          {
            bool1 = bool3;
            bool2 = bool4;
            makeLibExecutable(paramString1);
          }
          bool1 = bool3;
          bool2 = bool4;
          paramString1 = new BufferedOutputStream(localFileOutputStream, localObject2.length);
          for (;;)
          {
            bool1 = bool3;
            bool2 = bool4;
            int i1 = ((BufferedInputStream)localObject1).read((byte[])localObject2, 0, localObject2.length);
            if (i1 == -1) {
              break;
            }
            bool1 = bool3;
            bool2 = bool4;
            if (this.v) {
              break;
            }
            bool1 = bool3;
            bool2 = bool4;
            paramString1.write((byte[])localObject2, 0, i1);
            bool1 = bool3;
            bool2 = bool4;
            int i2 = this.bc;
            bool1 = bool3;
            bool2 = bool4;
            this.bc = (i1 / 1024 + i2);
            bool1 = bool3;
            bool2 = bool4;
            a();
          }
          bool1 = bool3;
          bool2 = bool4;
          a();
          bool1 = bool3;
          bool2 = bool4;
          paramString1.flush();
          bool1 = bool3;
          bool2 = bool4;
          paramString1.close();
          bool1 = bool3;
          bool2 = bool4;
          localFileOutputStream.close();
          bool1 = bool3;
          bool2 = bool4;
          ((BufferedInputStream)localObject1).close();
        }
      }
      return paramBoolean;
    }
    catch (IOException paramString1)
    {
      return bool1;
    }
    catch (Exception paramString1) {}
    return bool2;
  }
  
  private boolean a(String[] paramArrayOfString, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    bool1 = true;
    paramString3 = paramString3 + "/" + paramString2 + "/";
    int i4 = 0;
    bool2 = bool1;
    if (i4 < 3) {
      paramString2 = paramString1 + "/" + paramArrayOfString[i4];
    }
    for (;;)
    {
      try
      {
        localEnumeration = new ZipFile(paramString2).entries();
        bool3 = bool1;
        bool4 = bool1;
      }
      catch (Exception paramArrayOfString)
      {
        try
        {
          Enumeration localEnumeration;
          ZipEntry localZipEntry1;
          File localFile;
          int i5;
          if (localEnumeration.hasMoreElements())
          {
            bool3 = bool1;
            bool4 = bool1;
            localZipEntry1 = (ZipEntry)localEnumeration.nextElement();
            bool3 = bool1;
            bool4 = bool1;
            if (localZipEntry1.getName().endsWith(".so"))
            {
              bool3 = bool1;
              bool4 = bool1;
              paramString2 = LIBS_PATH + localZipEntry1.getName();
              bool3 = bool1;
              bool4 = bool1;
              addNativeLib(localZipEntry1.getName());
              bool3 = bool1;
              bool4 = bool1;
              localFile = new File(paramString2);
              bool3 = bool1;
              bool4 = bool1;
              new File(localFile.getParent()).mkdirs();
              bool3 = bool1;
              bool4 = bool1;
              if (paramString2.endsWith("/")) {
                continue;
              }
              i1 = 0;
              bool3 = bool1;
              bool4 = bool1;
              this.bc += 1;
              if (paramBoolean)
              {
                bool3 = bool1;
                bool4 = bool1;
                if (CRC.isValidChecksum(localFile.getAbsolutePath(), localZipEntry1.getCrc())) {
                  break label652;
                }
                i1 = 1;
              }
              bool2 = bool1;
              bool3 = bool1;
              bool4 = bool1;
              if (localFile.isDirectory()) {
                continue;
              }
              bool3 = bool1;
              bool4 = bool1;
              if (!localFile.exists()) {
                continue;
              }
              bool3 = bool1;
              bool4 = bool1;
              long l1 = localFile.length();
              bool3 = bool1;
              bool4 = bool1;
              long l2 = localZipEntry1.getSize();
              if (l1 == l2)
              {
                bool2 = bool1;
                if (i1 == 0) {
                  continue;
                }
              }
              i2 = 0;
              i3 = 0;
              i1 = 0;
              i5 = i4 + 1;
              if (i5 >= 2) {
                continue;
              }
              bool3 = bool1;
            }
          }
          try
          {
            paramString2 = new ZipFile(paramString1 + "/" + paramArrayOfString[i5]).entries();
            i2 = i1;
            bool3 = bool1;
            i3 = i1;
            if (paramString2.hasMoreElements())
            {
              bool3 = bool1;
              i3 = i1;
              ZipEntry localZipEntry2 = (ZipEntry)paramString2.nextElement();
              bool3 = bool1;
              i3 = i1;
              bool2 = localZipEntry1.getName().equals(localZipEntry2.getName());
              if (!bool2) {
                break label649;
              }
              i1 = 1;
              continue;
              bool3 = bool1;
              bool4 = bool1;
              paramString2 = paramString3 + localZipEntry1.getName();
            }
          }
          catch (Exception paramString2)
          {
            i2 = i3;
            continue;
            bool2 = bool1;
            continue;
          }
          if (i2 == 0)
          {
            bool3 = bool1;
            bool4 = bool1;
            localFile.delete();
            bool1 = false;
            break label666;
            bool3 = bool2;
            bool4 = bool2;
            a();
            bool1 = bool2;
          }
        }
        catch (IOException paramArrayOfString)
        {
          boolean bool3;
          bool2 = bool3;
          return bool2;
          bool2 = false;
          continue;
          i4 += 1;
          break;
          paramArrayOfString = paramArrayOfString;
          return bool1;
        }
        catch (Exception paramArrayOfString)
        {
          boolean bool4;
          return bool4;
        }
      }
      catch (IOException paramArrayOfString)
      {
        return bool1;
      }
      break label666;
      label649:
      continue;
      label652:
      int i1 = 0;
    }
  }
  
  public static void addErrorNumber(int paramInt)
  {
    synchronized (m_objectToastLock)
    {
      if (!m_errorMessage.contains(paramInt)) {
        m_errorMessage = m_errorMessage + " " + paramInt;
      }
      return;
    }
  }
  
  public static void addNativeLib(String paramString)
  {
    if (!sNativeLibs.contains(paramString)) {
      sNativeLibs.add(paramString);
    }
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    this.cf = new ArrayList();
    this.cf.clear();
    runOnUiThread(new b(this, paramInt1, paramInt2, getApplicationContext()));
  }
  
  private void b(int paramInt, boolean paramBoolean)
  {
    runOnUiThread(new k(this, paramInt, paramBoolean));
  }
  
  private void b(Button paramButton)
  {
    runOnUiThread(new g(this, paramButton));
  }
  
  private void b(String paramString1, String paramString2)
  {
    this.cn.post(new e(this, paramString2, paramString1));
  }
  
  /* Error */
  private boolean b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 1438	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:u	Lcom/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient;
    //   4: ifnonnull +41 -> 45
    //   7: aload_0
    //   8: new 1440	com/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient
    //   11: dup
    //   12: invokespecial 1441	com/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient:<init>	()V
    //   15: putfield 1438	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:u	Lcom/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient;
    //   18: aload_0
    //   19: getfield 1438	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:u	Lcom/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient;
    //   22: aload_1
    //   23: invokevirtual 1444	com/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient:a	(Ljava/lang/String;)Ljava/io/InputStream;
    //   26: astore_1
    //   27: aload_1
    //   28: ifnonnull +48 -> 76
    //   31: aload_0
    //   32: bipush -2
    //   34: putfield 492	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:bN	I
    //   37: sipush 220
    //   40: invokestatic 1446	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:addErrorNumber	(I)V
    //   43: iconst_0
    //   44: ireturn
    //   45: aload_0
    //   46: getfield 1438	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:u	Lcom/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient;
    //   49: invokevirtual 1447	com/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient:b	()V
    //   52: goto -34 -> 18
    //   55: astore_1
    //   56: sipush 221
    //   59: invokestatic 1446	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:addErrorNumber	(I)V
    //   62: aload_0
    //   63: getfield 1438	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:u	Lcom/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient;
    //   66: astore_1
    //   67: invokestatic 1450	com/gameloft/android/ANMP/GloftPEHM/installer/utils/HttpClient:incrementConnectionTimeout	()V
    //   70: aload_0
    //   71: invokespecial 1452	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:k	()V
    //   74: iconst_0
    //   75: ireturn
    //   76: aload_0
    //   77: getfield 1454	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:aL	Ljava/io/DataInputStream;
    //   80: ifnull +15 -> 95
    //   83: aload_0
    //   84: getfield 1454	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:aL	Ljava/io/DataInputStream;
    //   87: invokevirtual 836	java/io/DataInputStream:close	()V
    //   90: aload_0
    //   91: aconst_null
    //   92: putfield 1454	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:aL	Ljava/io/DataInputStream;
    //   95: aload_0
    //   96: new 794	java/io/DataInputStream
    //   99: dup
    //   100: aload_1
    //   101: invokespecial 802	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   104: putfield 1454	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:aL	Ljava/io/DataInputStream;
    //   107: iconst_1
    //   108: ireturn
    //   109: astore_1
    //   110: sipush 222
    //   113: invokestatic 1446	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:addErrorNumber	(I)V
    //   116: aload_0
    //   117: bipush -2
    //   119: putfield 492	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:bN	I
    //   122: goto -52 -> 70
    //   125: astore_1
    //   126: sipush 223
    //   129: invokestatic 1446	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:addErrorNumber	(I)V
    //   132: aload_0
    //   133: iconst_m1
    //   134: putfield 492	com/gameloft/android/ANMP/GloftPEHM/installer/GameInstaller:bN	I
    //   137: goto -67 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	this	GameInstaller
    //   0	140	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   0	18	55	java/net/SocketTimeoutException
    //   18	27	55	java/net/SocketTimeoutException
    //   31	43	55	java/net/SocketTimeoutException
    //   45	52	55	java/net/SocketTimeoutException
    //   76	95	55	java/net/SocketTimeoutException
    //   95	107	55	java/net/SocketTimeoutException
    //   0	18	109	java/io/FileNotFoundException
    //   18	27	109	java/io/FileNotFoundException
    //   31	43	109	java/io/FileNotFoundException
    //   45	52	109	java/io/FileNotFoundException
    //   76	95	109	java/io/FileNotFoundException
    //   95	107	109	java/io/FileNotFoundException
    //   0	18	125	java/lang/Exception
    //   18	27	125	java/lang/Exception
    //   31	43	125	java/lang/Exception
    //   45	52	125	java/lang/Exception
    //   76	95	125	java/lang/Exception
    //   95	107	125	java/lang/Exception
  }
  
  private boolean b(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    boolean bool3 = true;
    boolean bool4 = true;
    boolean bool1 = true;
    paramString3 = paramString4 + "/" + paramString3 + "/";
    paramString1 = paramString2 + "/" + paramString1;
    for (;;)
    {
      try
      {
        paramString2 = new ZipFile(paramString1).entries();
        bool3 = bool1;
        bool4 = bool1;
        if (!paramString2.hasMoreElements()) {
          continue;
        }
        bool3 = bool1;
        bool4 = bool1;
        paramString4 = (ZipEntry)paramString2.nextElement();
        bool3 = bool1;
        bool4 = bool1;
        if (!paramString4.getName().endsWith(".so")) {
          continue;
        }
        bool3 = bool1;
        bool4 = bool1;
        paramString1 = LIBS_PATH + paramString4.getName();
        bool3 = bool1;
        bool4 = bool1;
        addNativeLib(paramString4.getName());
        bool3 = bool1;
        bool4 = bool1;
        File localFile = new File(paramString1);
        bool3 = bool1;
        bool4 = bool1;
        new File(localFile.getParent()).mkdirs();
        bool3 = bool1;
        bool4 = bool1;
        if (paramString1.endsWith("/")) {
          continue;
        }
        i1 = 0;
        bool3 = bool1;
        bool4 = bool1;
        this.bc = ((int)(this.bc + localFile.length()));
        if (paramBoolean)
        {
          bool3 = bool1;
          bool4 = bool1;
          if (CRC.isValidChecksum(localFile.getAbsolutePath(), paramString4.getCrc())) {
            continue;
          }
          i1 = 1;
        }
        bool2 = bool1;
        bool3 = bool1;
        bool4 = bool1;
        if (!localFile.isDirectory())
        {
          bool3 = bool1;
          bool4 = bool1;
          if (!localFile.exists()) {
            continue;
          }
          bool3 = bool1;
          bool4 = bool1;
          if (localFile.length() == paramString4.getSize())
          {
            bool2 = bool1;
            if (i1 == 0) {}
          }
          else
          {
            bool3 = bool1;
            bool4 = bool1;
            localFile.delete();
          }
        }
      }
      catch (Exception paramString1)
      {
        int i1;
        return bool3;
        boolean bool2 = false;
        continue;
      }
      catch (IOException paramString1) {}
      bool3 = bool2;
      bool4 = bool2;
      a();
      bool1 = bool2;
      continue;
      bool3 = bool1;
      bool4 = bool1;
      paramString1 = paramString3 + paramString4.getName();
      continue;
      i1 = 0;
    }
    return bool1;
    return bool4;
  }
  
  private long c(String paramString)
  {
    int i1 = 1;
    for (;;)
    {
      try
      {
        File localFile = new File(paramString + "/");
        if (!localFile.exists())
        {
          localFile.mkdirs();
          paramString = new StatFs(paramString);
          this.g = ((int)(paramString.getBlockSize() * paramString.getAvailableBlocks() / 1048576L));
          if ((i1 != 0) || (localFile.list().length == 0))
          {
            if ((localFile.getAbsolutePath().endsWith("/files")) || (localFile.getAbsolutePath().endsWith("/files/")))
            {
              localFile.delete();
              new File(localFile.getAbsolutePath().substring(0, localFile.getAbsolutePath().lastIndexOf("/files"))).delete();
            }
          }
          else
          {
            this.aE[0] = 1;
            return this.g;
          }
          localFile.delete();
          continue;
        }
        i1 = 0;
      }
      catch (Exception paramString)
      {
        return 0L;
      }
    }
  }
  
  private void c(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 14) || (paramInt == 5) || (paramInt == 31)) {
      clearErrorHistory();
    }
    switch (paramInt)
    {
    }
    for (;;)
    {
      this.at = this.as;
      this.as = paramInt;
      if (this.an != 5) {
        this.an = -1;
      }
      return;
      b(2130903056, 13);
      continue;
      this.bR = System.currentTimeMillis();
      b(2130903061, 14);
      if (this.bL.equals("")) {
        m();
      }
      Tracking.onLaunchGame(1);
      Game.Z = true;
      this.bG = x();
      Tracker.launchInstallerTracker(this.bG, y());
      if (this.bG == 1)
      {
        localObject = "Wifi Only";
        label319:
        if (!y()) {
          break label349;
        }
      }
      label349:
      for (String str = "Wifi On";; str = "Wifi Off")
      {
        GoogleAnalyticsTracker.trackEvent("Launch Installer", (String)localObject, str, null);
        break;
        localObject = "Wifi & 3G";
        break label319;
      }
      b(2130903056, 18);
      continue;
      b(2130903059, 9);
      continue;
      Object localObject = getApplicationContext().getPackageManager();
      if (((this.bG == 0) || (this.bG == 2)) && (((PackageManager)localObject).checkPermission("android.permission.CHANGE_WIFI_STATE", "com.gameloft.android.ANMP.GloftPEHM") == 0))
      {
        b(2130903059, 20);
      }
      else
      {
        label612:
        try
        {
          if (m_portalCode.equals("amazon")) {}
          for (localObject = new Intent("android.settings.WIRELESS_SETTINGS");; localObject = new Intent("android.settings.WIFI_SETTINGS"))
          {
            ((Intent)localObject).setFlags(268435456);
            startActivity((Intent)localObject);
            this.bH = true;
            break;
          }
          b(2130903056, 3);
        }
        catch (Exception localException3) {}
        this.bj = true;
        if (this.bi == null)
        {
          this.bl = true;
          this.bi = new n(this);
          localObject = new IntentFilter();
          ((IntentFilter)localObject).addAction("android.net.wifi.STATE_CHANGE");
          registerReceiver(this.bi, (IntentFilter)localObject);
        }
        this.bS = System.currentTimeMillis();
        b(2130903056, 23);
        continue;
        if (!this.bg)
        {
          if (this.bg) {
            break label612;
          }
          a(13, "", 0, 0);
        }
        while (a(3))
        {
          saveVersion("22120");
          break;
          localObject = getIntent();
          try
          {
            ((Intent)localObject).addFlags(4194304);
            ((Intent)localObject).addFlags(131072);
            ((Intent)localObject).addFlags(536870912);
            startActivity((Intent)localObject);
          }
          catch (Exception localException1) {}
        }
        if (this.ci != 4)
        {
          b(2130903056, 8);
          new o(this).start();
          if (!this.bg) {
            if (!this.bg)
            {
              a(14, "", 0, 0);
            }
            else
            {
              Intent localIntent = getIntent();
              try
              {
                localIntent.addFlags(4194304);
                localIntent.addFlags(131072);
                localIntent.addFlags(536870912);
                startActivity(localIntent);
              }
              catch (Exception localException2) {}
              continue;
              b(2130903056, 29);
              continue;
              if ((!this.aS) && (this.aU))
              {
                paramInt = 23;
                break;
                b(2130903059, 19);
                continue;
                b(2130903056, 2);
                continue;
                b(2130903056, 17);
                continue;
                b(2130903056, 15);
              }
            }
          }
        }
      }
    }
  }
  
  private static void cancelDialog()
  {
    if (m_Dialog != null) {
      m_Dialog.cancel();
    }
  }
  
  private static void clearErrorHistory()
  {
    m_prevErrorMessage = m_errorMessage;
    m_errorMessage = "";
  }
  
  private static void createNoMedia(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return;
      try
      {
        File localFile = new File(paramString);
        if (!localFile.exists()) {
          localFile.mkdirs();
        }
        paramString = new File(paramString + "/.nomedia");
        if (!paramString.exists())
        {
          paramString.createNewFile();
          return;
        }
      }
      catch (Exception paramString) {}
    }
  }
  
  private String d(String paramString)
  {
    return paramString.replace("$", this.f / 1048576L + 1L);
  }
  
  private void d(int paramInt)
  {
    try
    {
      int i1 = this.cf.size();
      if (i1 > 0)
      {
        if ((paramInt != 19) && (paramInt != 22)) {
          break label155;
        }
        paramInt = this.cg + 1;
        this.cg = paramInt;
        if (paramInt >= i1) {
          this.cg = (i1 - 1);
        }
        a((Button)this.cf.get(this.cg));
      }
      for (;;)
      {
        paramInt = this.cg - 1;
        this.cg = paramInt;
        if (paramInt < 0) {
          this.cg = 0;
        }
        a((Button)this.cf.get(this.cg));
        return;
        label155:
        do
        {
          if ((paramInt == 96) && (this.cg >= 0) && (this.cg < i1)) {
            runOnUiThread(new g(this, (Button)this.cf.get(this.cg)));
          }
          return;
          if (paramInt == 20) {
            break;
          }
        } while (paramInt != 21);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private static boolean deleteFile$4ad1f430()
  {
    return true;
  }
  
  private void e()
  {
    int i1 = 0;
    while (i1 < this.aE.length)
    {
      this.aE[i1] = 0;
      i1 += 1;
    }
  }
  
  private void e(String paramString)
  {
    b(null, paramString);
  }
  
  private int f()
  {
    Iterator localIterator = this.bM.iterator();
    for (int i1 = 0; localIterator.hasNext(); i1 = ((DownloadComponent)localIterator.next()).g() + i1) {}
    return i1;
  }
  
  private void f(String paramString)
  {
    paramString = new DownloadComponent(paramString, "");
    this.bM.add(paramString);
  }
  
  private int g()
  {
    Iterator localIterator = this.bM.iterator();
    for (int i1 = 0; localIterator.hasNext(); i1 = ((DownloadComponent)localIterator.next()).h() + i1) {}
    return i1;
  }
  
  private static String getSDFolder()
  {
    return sd_folder;
  }
  
  private static long getZipRealSpace(ZipFile paramZipFile)
  {
    paramZipFile = paramZipFile.entries();
    for (long l1 = 0L; paramZipFile.hasMoreElements(); l1 += ((ZipEntry)paramZipFile.nextElement()).getSize()) {}
    return l1;
  }
  
  private int h()
  {
    Object localObject2;
    int i1;
    if (this.as == 20)
    {
      localObject1 = (ProgressBar)findViewById(2131230764);
      localObject2 = this.bM.iterator();
      for (i1 = 0; ((Iterator)localObject2).hasNext(); i1 = ((DownloadComponent)((Iterator)localObject2).next()).h() + i1) {}
      i2 = this.e.size();
      if (localObject1 != null) {
        ((ProgressBar)localObject1).setMax(i2 + i1);
      }
    }
    this.j = 0L;
    this.k = 0L;
    this.h = 0L;
    m_iRealRequiredSize = 0L;
    this.bc = 0;
    long l1 = 0L;
    Object localObject1 = this.bM.iterator();
    int i2 = 0;
    if (((Iterator)localObject1).hasNext())
    {
      localObject2 = (DownloadComponent)((Iterator)localObject1).next();
      if (((DownloadComponent)localObject2).a(this.i, this) == 1)
      {
        m_iRealRequiredSize += ((DownloadComponent)localObject2).e();
        i2 = 1;
      }
      this.j += ((DownloadComponent)localObject2).i();
      this.k += ((DownloadComponent)localObject2).j();
      Object localObject3 = ((DownloadComponent)localObject2).o();
      if (localObject3 != null)
      {
        localObject3 = ((ArrayList)localObject3).iterator();
        while (((Iterator)localObject3).hasNext()) {
          addNativeLib((String)((Iterator)localObject3).next());
        }
      }
      if (((DownloadComponent)localObject2).k()) {
        break label886;
      }
      l1 = ((DownloadComponent)localObject2).f() + l1;
    }
    label346:
    label770:
    label881:
    label886:
    for (;;)
    {
      break;
      long l2;
      int i4;
      int i3;
      if (this.as == 20)
      {
        l2 = this.k;
        i4 = this.bc;
        localObject1 = new File(DATA_PATH).list();
        if (localObject1 != null)
        {
          this.bc = 0;
          localObject2 = (ProgressBar)findViewById(2131230764);
          if (localObject2 != null) {
            ((ProgressBar)localObject2).setMax(localObject1.length);
          }
          i3 = 0;
          if (i3 < localObject1.length)
          {
            i1 = 0;
            if (i1 >= this.e.size()) {
              break label881;
            }
            if (!((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.e.get(i1)).b().contains(localObject1[i3])) {}
          }
        }
      }
      for (i1 = 1;; i1 = 0)
      {
        if (i1 == 0)
        {
          localObject2 = this.bM.iterator();
          while (((Iterator)localObject2).hasNext()) {
            if (((DownloadComponent)((Iterator)localObject2).next()).a(localObject1[i3])) {
              i1 = 1;
            }
          }
        }
        for (;;)
        {
          if ((i1 == 0) && (!"InsTime,firstlaunch.dat".contains(localObject1[i3])) && (!localObject1[i3].contains("tracking")) && (!localObject1[i3].contains(".bin"))) {
            new File(DATA_PATH + localObject1[i3]).exists();
          }
          this.bc += 1;
          a();
          i3 += 1;
          break;
          i1 += 1;
          break label346;
          this.k = l2;
          this.bc = i4;
          i1 = 0;
          if (i1 < this.e.size())
          {
            localObject1 = ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.e.get(i1)).b();
            localObject2 = this.bM.iterator();
            do
            {
              if (!((Iterator)localObject2).hasNext()) {
                break;
              }
            } while (!((DownloadComponent)((Iterator)localObject2).next()).a((String)localObject1));
          }
          for (i3 = 1;; i3 = 0)
          {
            if (i3 == 0) {
              if ((!((String)localObject1).startsWith("main")) && (!((String)localObject1).startsWith("patch"))) {
                break label770;
              }
            }
            for (localObject1 = marketPath + ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.e.get(i1)).a().replace(".\\\\", "").replace(".\\", "").replace("\\", "/") + "/" + ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.e.get(i1)).b();; localObject1 = DATA_PATH + ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.e.get(i1)).a().replace(".\\\\", "").replace(".\\", "").replace("\\", "/") + "/" + ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.e.get(i1)).b())
            {
              localObject1 = new File((String)localObject1);
              if (((File)localObject1).exists()) {
                ((File)localObject1).delete();
              }
              this.bc += 1;
              a();
              i1 += 1;
              break;
            }
            if (l1 > 0L) {
              a(l1);
            }
            return i2;
          }
        }
      }
    }
  }
  
  private static int hasNativeError()
  {
    return 0;
  }
  
  private static int hasSDCard()
  {
    String str = Environment.getExternalStorageState();
    if (("mounted".equals(str)) || ("mounted_ro".equals(str))) {
      return 0;
    }
    return 1;
  }
  
  private boolean i()
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (!new File(DATA_PATH).exists()) {
      return bool2;
    }
    String str = SUtils.ReadFile(SaveFolder + "/prefs/gl_ver");
    if ((str != null) && (str.length() > 0) && (str.compareTo("22120") == 0)) {}
    for (;;)
    {
      bool2 = bool1;
      if (bool1 != true) {
        break;
      }
      SUtils.setPreference("ZipHasCRCtest", Boolean.valueOf(true), mPreferencesName);
      return bool1;
      bool1 = true;
    }
  }
  
  private static boolean isEnoughInternalSpace()
  {
    StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
    localStatFs.getBlockSize();
    localStatFs.getAvailableBlocks();
    return true;
  }
  
  private void j()
  {
    saveVersion("22120");
  }
  
  private void k()
  {
    try
    {
      if (this.aL != null)
      {
        this.aL.close();
        this.aL = null;
      }
      if (this.aM != null)
      {
        this.aM.close();
        this.aM = null;
      }
      if (this.u != null) {
        this.u.b();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private boolean l()
  {
    if (this.bT == -1) {}
    do
    {
      return false;
      if (this.bT == 0) {
        return true;
      }
    } while ((m_iRealRequiredSize <= 0L) || ((int)(m_iRealRequiredSize >> 20) < this.bT));
    return true;
  }
  
  private static void loadPreferences$552c4e01() {}
  
  private void m()
  {
    String str = SUtils.getPreferenceString("SDFolder", "", mPreferencesName);
    str = SUtils.getOverriddenSetting(str + "/qaTestingConfigs.txt", "DATA_LINK");
    if (str != null) {
      this.bw = true;
    }
    for (;;)
    {
      this.bL = str;
      return;
      this.bw = false;
      str = SUtils.ReadFile(2130968577);
      int i3 = str.indexOf("DYNAMIC:") + 8;
      int i2 = str.indexOf('\r', i3);
      int i1 = i2;
      if (i2 == -1) {
        i1 = str.length();
      }
      str = str.substring(i3, i1);
      str = str + "?model=" + Device.getPhoneModel() + "&device=" + Device.getPhoneDevice() + "&product=1367" + "&version=2.2.1";
      str = (str + "&portal=" + m_portalCode).replaceAll("\\s+", "%20");
    }
  }
  
  public static void makeLibExecutable(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      new File(paramString).setExecutable(true);
      return;
    }
    try
    {
      Runtime.getRuntime().exec("/system/bin/chmod u+x " + LIBS_PATH);
      Runtime.getRuntime().exec("/system/bin/chmod u+x " + paramString);
      return;
    }
    catch (Exception paramString) {}
  }
  
  private void n()
  {
    startTime = System.currentTimeMillis();
    while ((GPUInstallerGLSurfaceView.getGPU().equals("")) && (System.currentTimeMillis() - startTime < 3000L)) {
      try
      {
        Thread.sleep(10L);
      }
      catch (Exception localException) {}
    }
    GoogleAnalyticsTracker.trackEvent("Configuration", "Running Applications", "", Long.valueOf(((ActivityManager)getSystemService("activity")).getRunningAppProcesses().size()));
    GoogleAnalyticsTracker.trackEvent("Configuration", "Installed Applications", "", Long.valueOf(getPackageManager().getInstalledPackages(128).size()));
    GoogleAnalyticsTracker.trackEvent("Configuration", "Kernel Version", System.getProperty("os.version"), null);
    GoogleAnalyticsTracker.trackEvent("Configuration", "GL_RENDERER", GPUInstallerGLSurfaceView.getGPU(), null);
    GoogleAnalyticsTracker.trackEvent("Configuration", "GL_VENDOR", GPUInstallerGLSurfaceView.getGL_VENDOR(), null);
    GoogleAnalyticsTracker.trackEvent("Configuration", "GL_VERSION", GPUInstallerGLSurfaceView.getGL_VERSION(), null);
    Object localObject2 = new StringTokenizer(GPUInstallerGLSurfaceView.getGL_EXTENSIONS());
    Object localObject1 = new Vector();
    String str;
    while (((StringTokenizer)localObject2).hasMoreTokens())
    {
      str = ((StringTokenizer)localObject2).nextToken();
      ((Vector)localObject1).add(str);
      GoogleAnalyticsTracker.trackEvent("Configuration", "GL_EXTENSION", str, null);
    }
    Collections.sort((List)localObject1);
    localObject2 = new StringBuilder();
    localObject1 = ((Vector)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      str = (String)((Iterator)localObject1).next();
      ((StringBuilder)localObject2).append(str + " ");
    }
    GoogleAnalyticsTracker.trackEvent("Configuration", "GL_EXTENSION", ((StringBuilder)localObject2).toString(), null);
    D();
    localObject1 = this.bf.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (Pair)((Iterator)localObject1).next();
      GoogleAnalyticsTracker.trackEvent("Configuration", "SDcard" + 0, "Before Install", (Long)((Pair)localObject2).second);
    }
    switch (getResources().getConfiguration().screenLayout & 0xF)
    {
    default: 
      localObject1 = "undefined";
      GoogleAnalyticsTracker.trackEvent("Configuration", "Screen Type", (String)localObject1, null);
      localObject1 = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject1);
      switch (((DisplayMetrics)localObject1).densityDpi)
      {
      default: 
        localObject1 = "default";
      }
      break;
    }
    for (;;)
    {
      GoogleAnalyticsTracker.trackEvent("Configuration", "Screen Density", (String)localObject1, null);
      return;
      localObject1 = "xlarge";
      break;
      localObject1 = "large";
      break;
      localObject1 = "normal";
      break;
      localObject1 = "small";
      break;
      localObject1 = "xxhigh";
      continue;
      localObject1 = "xhigh";
      continue;
      localObject1 = "high";
      continue;
      localObject1 = "tv";
      continue;
      localObject1 = "medium";
      continue;
      localObject1 = "low";
    }
  }
  
  private Vector<Pair<String, Long>> o()
  {
    Vector localVector = new Vector();
    Iterator localIterator1;
    if (this.be != null) {
      localIterator1 = this.be.iterator();
    }
    label107:
    for (;;)
    {
      Pair localPair;
      if (localIterator1.hasNext())
      {
        localPair = (Pair)localIterator1.next();
        Iterator localIterator2 = localVector.iterator();
        do
        {
          if (!localIterator2.hasNext()) {
            break;
          }
        } while (!((Long)((Pair)localIterator2.next()).second).equals(localPair.second));
      }
      for (int i1 = 1;; i1 = 0)
      {
        if (i1 != 0) {
          break label107;
        }
        localVector.add(localPair);
        break;
        return localVector;
      }
    }
  }
  
  private static String ovWifiMode()
  {
    String str = SUtils.getOverriddenSetting(DATA_PATH + "qaTestingConfigs.txt", "WIFI_MODE");
    if (str != null) {
      return str;
    }
    return null;
  }
  
  private boolean p()
  {
    Iterator localIterator;
    long l1;
    if (SUtils.getPreferenceString("SDFolder", "", mPreferencesName).equals(""))
    {
      localIterator = this.bM.iterator();
      l1 = 0L;
    }
    for (;;)
    {
      Object localObject2;
      if (localIterator.hasNext()) {
        localObject2 = (DownloadComponent)localIterator.next();
      }
      long l2;
      try
      {
        if ((((DownloadComponent)localObject2).a.startsWith("main")) || (((DownloadComponent)localObject2).a.startsWith("patch"))) {}
        for (Object localObject1 = marketPath;; localObject1 = DATA_PATH)
        {
          l2 = l1 + getZipRealSpace(new ZipFile((String)localObject1 + "/" + ((DownloadComponent)localObject2).a));
          l1 = l2;
          break;
        }
        if (l1 > 0L) {
          a(l1 / 1048576L);
        }
        localObject1 = SUtils.getPreferenceString("SDFolder", "", mPreferencesName);
        long l5;
        if (((String)localObject1).contains("com.gameloft.android.ANMP.GloftPEHM"))
        {
          localObject1 = new File(((String)localObject1).substring(0, ((String)localObject1).indexOf("com.gameloft.android.ANMP.GloftPEHM")));
          if (!((File)localObject1).exists()) {
            ((File)localObject1).mkdirs();
          }
          localObject1 = new StatFs(((File)localObject1).getAbsolutePath());
          l5 = ((StatFs)localObject1).getBlockSize() * ((StatFs)localObject1).getAvailableBlocks();
          this.g = ((int)(l5 / 1048576L));
          localIterator = this.bM.iterator();
          l2 = 0L;
          l1 = 0L;
        }
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label615;
          }
          localObject2 = (DownloadComponent)localIterator.next();
          if (!((DownloadComponent)localObject2).a.equals("")) {
            try
            {
              if ((((DownloadComponent)localObject2).a.startsWith("main")) || (((DownloadComponent)localObject2).a.startsWith("patch")))
              {
                localObject1 = marketPath;
                label342:
                localObject1 = new ZipFile((String)localObject1 + "/" + ((DownloadComponent)localObject2).a).entries();
                label383:
                l4 = l2;
              }
            }
            catch (IOException localIOException1)
            {
              try
              {
                if (((Enumeration)localObject1).hasMoreElements())
                {
                  l4 = l2;
                  this.l += 1L;
                  l4 = l2;
                  localObject2 = (ZipEntry)((Enumeration)localObject1).nextElement();
                  l4 = l2;
                  File localFile = new File(DATA_PATH + "/" + ((ZipEntry)localObject2).getName());
                  long l3 = l2;
                  l4 = l2;
                  long l6;
                  if (!localFile.isDirectory())
                  {
                    l4 = l2;
                    if (!localFile.exists()) {
                      break label588;
                    }
                    l4 = l2;
                    l6 = ((ZipEntry)localObject2).getSize() - localFile.length();
                    l3 = l2;
                    if (l6 <= 0L) {}
                  }
                  for (l3 = l2 + l6;; l3 = l2 + l3)
                  {
                    l4 = l3;
                    l2 = ((ZipEntry)localObject2).getSize();
                    l1 += l2;
                    l2 = l3;
                    break label383;
                    if (localObject1 == "/sdcard/gameloft/games/GloftPEHM")
                    {
                      localObject1 = new File("/sdcard/");
                      break;
                    }
                    localObject1 = new File((String)localObject1);
                    break;
                    localObject1 = DATA_PATH;
                    break label342;
                    label588:
                    l4 = l2;
                    l3 = ((ZipEntry)localObject2).getSize();
                  }
                }
              }
              catch (IOException localIOException2)
              {
                for (;;)
                {
                  long l4;
                  l2 = l4;
                }
              }
              localIOException1 = localIOException1;
            }
          }
        }
        label615:
        if (l2 >= l5)
        {
          this.j = l1;
          this.h = ((int)(l2 / 1048576L));
          return false;
        }
        this.j = l1;
        return true;
      }
      catch (Exception localException) {}
    }
  }
  
  private static void pushHashMapResource()
  {
    try
    {
      if (p.b == null)
      {
        p.b = new HashMap();
        DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(sd_folder + "/d_o_w_n_l_o_a_d_e_d.txt"));
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localDataInputStream));
        for (;;)
        {
          String str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          p.b.put(str, str);
        }
        localDataInputStream.close();
        return;
      }
    }
    catch (Exception localException) {}
  }
  
  private void q()
  {
    if (this.an == -1) {
      if (this.as == 12) {
        if ((this.at == 9) || (this.at == 20) || (this.at == 8))
        {
          this.ap = this.d.size();
          this.an = 6;
        }
      }
    }
    label324:
    label342:
    label404:
    label409:
    label417:
    label1166:
    label1193:
    label1425:
    label1433:
    label1470:
    label1488:
    label1524:
    label1532:
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        return;
                      } while (this.an == 5);
                      this.an = 0;
                      return;
                      if (this.an != 5) {
                        this.an = 0;
                      }
                      try
                      {
                        Thread.sleep(50L);
                        return;
                      }
                      catch (Exception localException)
                      {
                        return;
                      }
                      switch (this.as)
                      {
                      case 1: 
                      case 4: 
                      case 10: 
                      case 8: 
                      case 11: 
                      case 14: 
                      case 15: 
                      case 16: 
                      case 17: 
                      case 18: 
                      case 19: 
                      case 21: 
                      case 22: 
                      case 26: 
                      case 31: 
                      case 32: 
                      case 33: 
                      case 34: 
                      case 35: 
                      case 36: 
                      case 37: 
                      case 38: 
                      case 39: 
                      case 40: 
                      default: 
                        return;
                      case 0: 
                        c(2);
                        return;
                      case 9: 
                        if (y())
                        {
                          this.bU = 1;
                          if (!this.aZ)
                          {
                            i1 = this.bG;
                            if (this.bU != 0) {
                              break label404;
                            }
                            bool = true;
                            Tracker.downloadStartTracker(i1, bool);
                            if (this.bG != 1) {
                              break label409;
                            }
                            localObject1 = "Wifi Only";
                            if (this.bU != 0) {
                              break label417;
                            }
                          }
                        }
                        for (localObject2 = "3G";; localObject2 = "Wifi")
                        {
                          GoogleAnalyticsTracker.trackEvent("Start Download", (String)localObject1, (String)localObject2, null);
                          this.aZ = true;
                          createNoMedia(DATA_PATH);
                          this.bN = 0;
                          c(12);
                          b(2130903057, 5);
                          return;
                          this.bU = 0;
                          break;
                          bool = false;
                          break label324;
                          localObject1 = "Wifi & 3G";
                          break label342;
                        }
                      }
                    } while (this.ba);
                    this.ba = true;
                    if (this.bL.equals("")) {
                      m();
                    }
                    localObject1 = new DownloadComponent(this.bL, "");
                    this.bM.add(localObject1);
                    i1 = 0;
                    while (i1 < this.aE.length)
                    {
                      this.aE[i1] = 0;
                      i1 += 1;
                    }
                    localObject1 = SUtils.getOverriddenSetting(DATA_PATH + "qaTestingConfigs.txt", "SKIP_VALIDATION");
                    if ((localObject1 != null) && (((String)localObject1).equals("1")))
                    {
                      c(21);
                      this.bN = 0;
                      this.aS = true;
                      this.aU = true;
                      return;
                    }
                    O();
                    pushHashMapResource();
                    localObject1 = mPreferencesName;
                    this.aE[4] = 0;
                    this.ar = 0;
                    if (g() <= 0)
                    {
                      c(3);
                      return;
                    }
                    i1 = hasSDCard();
                    this.aE[0] = i1;
                    if (i()) {}
                    for (i1 = 1;; i1 = 0)
                    {
                      this.aE[3] = i1;
                      i1 = h();
                      this.aE[2] = i1;
                      if (a(2))
                      {
                        i1 = F();
                        this.aE[1] = i1;
                      }
                      if ((!a(0)) && (!a(1)) && (!a(2)) && (!a(3))) {
                        break;
                      }
                      c(3);
                      return;
                    }
                    c(21);
                    return;
                  } while (System.currentTimeMillis() - this.bR <= 3000L);
                  s_files_changed = true;
                  if (g() <= 0)
                  {
                    if (!y())
                    {
                      addErrorNumber(241);
                      if (l())
                      {
                        c(5);
                        return;
                      }
                      c(12);
                      return;
                    }
                    if ((y()) && (!a(this.bL)))
                    {
                      addErrorNumber(261);
                      c(14);
                      return;
                    }
                    c(12);
                    return;
                  }
                  if ((a(0)) || (a(1)))
                  {
                    c(4);
                    return;
                  }
                  if (a(3))
                  {
                    c(20);
                    return;
                  }
                  if (!y())
                  {
                    addErrorNumber(240);
                    if (l())
                    {
                      c(5);
                      return;
                    }
                    c(12);
                    return;
                  }
                  if ((y()) && (!a(this.bL)))
                  {
                    addErrorNumber(260);
                    c(14);
                    return;
                  }
                  c(12);
                  return;
                  this.i = true;
                  if (P()) {
                    O();
                  }
                  i1 = h();
                  if (f() > 0)
                  {
                    this.i = false;
                    int i2 = hasSDCard();
                    this.aE[0] = i2;
                    this.aE[2] = i1;
                    i1 = F();
                    this.aE[1] = i1;
                    if ((a(0)) || (a(1)))
                    {
                      c(4);
                      return;
                    }
                    if ((this.bv) && (a(4)))
                    {
                      addErrorNumber(183);
                      c(31);
                      return;
                    }
                    if ((!y()) && (l()) && (!this.w))
                    {
                      c(5);
                      return;
                    }
                    c(9);
                    return;
                  }
                  saveVersion("22120");
                  c(21);
                  return;
                  if (((this.bG != 0) && (this.bG != 2)) || (getApplicationContext().getPackageManager().checkPermission("android.permission.CHANGE_WIFI_STATE", "com.gameloft.android.ANMP.GloftPEHM") != 0)) {
                    break label1193;
                  }
                  i1 = z();
                  if (!this.bj) {
                    break label1166;
                  }
                  if (i1 <= 0) {
                    break;
                  }
                } while (this.bi == null);
                unregisterReceiver(this.bi);
                this.bi = null;
                return;
              } while (i1 >= 0);
              c(7);
              return;
            } while (i1 >= 0);
            if (this.at == 8)
            {
              c(7);
              return;
            }
            c(8);
            return;
          } while ((!this.bH) || (!y()));
          c(12);
          this.bH = false;
          return;
        } while ((!this.bg) || (System.currentTimeMillis() - this.bS <= 30000L));
        moveTaskToBack(true);
        return;
      } while (this.an != 0);
      if ((this.bG == 0) || (this.bG == 2))
      {
        a(2131230760, true);
        b(2131230771, false);
      }
      this.an = 1;
      return;
      if (this.an == 0)
      {
        this.bc = 0;
        this.aO = 0;
        this.an = 2;
        return;
      }
      r();
      return;
      if (this.an == 0) {
        if (this.aZ)
        {
          i1 = this.bG;
          if (this.bU == 0)
          {
            bool = true;
            Tracker.downloadFinishTracker(i1, bool);
            if (this.bG != 1) {
              break label1425;
            }
            localObject1 = "Wifi Only";
            if (this.bU != 0) {
              break label1433;
            }
            localObject2 = "3G";
            GoogleAnalyticsTracker.trackEvent("Finish Download", (String)localObject1, (String)localObject2, null);
            this.aZ = false;
            t();
          }
        }
        else
        {
          this.an = 1;
        }
      }
      while ((this.an != 5) || (!this.aZ)) {
        for (;;)
        {
          this.aU = true;
          this.bN = 0;
          c(21);
          return;
          bool = false;
          continue;
          localObject1 = "Wifi & 3G";
          continue;
          localObject2 = "Wifi";
        }
      }
      int i1 = this.bG;
      if (this.bU == 0)
      {
        bool = true;
        Tracker.downloadFinishTracker(i1, bool);
        if (this.bG != 1) {
          break label1524;
        }
        localObject1 = "Wifi Only";
        if (this.bU != 0) {
          break label1532;
        }
      }
      for (Object localObject2 = "3G";; localObject2 = "Wifi")
      {
        GoogleAnalyticsTracker.trackEvent("Finish Download", (String)localObject1, (String)localObject2, null);
        this.aZ = false;
        break;
        bool = false;
        break label1470;
        localObject1 = "Wifi & 3G";
        break label1488;
      }
      this.bc = 0;
      SUtils.getPreferenceString("ExtraFile", "", mPreferencesName);
      SUtils.getPreferenceString("mainFileName", "", "ExpansionPrefs");
      SUtils.getPreferenceString("patchFileName", "", "ExpansionPrefs");
      boolean bool = SUtils.getPreferenceBoolean("ZipHasCRCtest", false, mPreferencesName);
      Object localObject1 = new String[3];
      localObject2 = this.bM.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        DownloadComponent localDownloadComponent = (DownloadComponent)((Iterator)localObject2).next();
        if (localDownloadComponent.a.startsWith("main")) {
          localObject1[0] = localDownloadComponent.a;
        } else if (localDownloadComponent.a.startsWith("patch")) {
          localObject1[1] = localDownloadComponent.a;
        } else {
          localObject1[2] = localDownloadComponent.a;
        }
      }
      if (bool) {
        b(2130903057, 22);
      }
      if (!(a((String[])localObject1, marketPath, "", DATA_PATH, bool) & true))
      {
        this.bc = 0;
        b(2130903057, 27);
        localObject1 = this.bM.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (DownloadComponent)((Iterator)localObject1).next();
          if ((!((DownloadComponent)localObject2).a.startsWith("main")) && (!((DownloadComponent)localObject2).a.startsWith("patch"))) {
            a(((DownloadComponent)localObject2).a, DATA_PATH, "", DATA_PATH, false);
          }
        }
        localObject1 = this.bM.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (DownloadComponent)((Iterator)localObject1).next();
          if (((DownloadComponent)localObject2).a.startsWith("patch")) {
            a(((DownloadComponent)localObject2).a, marketPath, "", DATA_PATH, false);
          }
        }
        localObject1 = this.bM.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (DownloadComponent)((Iterator)localObject1).next();
          if (((DownloadComponent)localObject2).a.startsWith("main")) {
            a(((DownloadComponent)localObject2).a, marketPath, "", DATA_PATH, false);
          }
        }
      }
    } while (this.v);
    SUtils.setPreference("ZipHasCRCtest", Boolean.valueOf(false), mPreferencesName);
    this.aU = true;
    this.bN = 0;
    c(21);
    return;
    v();
  }
  
  private void r()
  {
    int i2 = 0;
    label101:
    DownloadComponent localDownloadComponent;
    switch (this.an)
    {
    case 4: 
    default: 
    case 2: 
    case 10: 
    case 3: 
    case 8: 
    case 9: 
    case 6: 
      for (;;)
      {
        a();
        if ((this.as == 12) && ((this.as != 12) || (this.an == 7) || ((this.an == 5) && (bIsPaused)))) {
          break;
        }
        return;
        s();
        this.u = new HttpClient();
        this.bN = 0;
        this.an = 10;
        b(2130903059, 0);
        continue;
        if (this.bL.equals("")) {
          m();
        }
        if (this.bw)
        {
          this.aE[4] = 0;
          this.bu = 1;
          this.bv = false;
          localObject = this.bM.iterator();
          while (((Iterator)localObject).hasNext())
          {
            localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
            localDownloadComponent.c();
            SUtils.setPreference("CurrentVersion" + localDownloadComponent.a(), Integer.valueOf(this.bu), mPreferencesName);
            SUtils.setPreference("IsGenericBuild" + localDownloadComponent.a(), Boolean.valueOf(this.bv), mPreferencesName);
          }
          this.an = 3;
        }
        else
        {
          localObject = this.bM.iterator();
          while (((Iterator)localObject).hasNext())
          {
            localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
            if (localDownloadComponent.p())
            {
              localDownloadComponent.q();
              if (localDownloadComponent.d() != SUtils.getPreferenceInt("CurrentVersion" + localDownloadComponent.a(), 0, mPreferencesName))
              {
                localDownloadComponent.c();
                SUtils.setPreference("CurrentVersion" + localDownloadComponent.a(), Integer.valueOf(localDownloadComponent.d()), mPreferencesName);
                SUtils.setPreference("IsGenericBuild" + localDownloadComponent.a(), Boolean.valueOf(localDownloadComponent.b()), mPreferencesName);
              }
            }
          }
          this.aE[4] = 0;
          this.an = 3;
          continue;
          if (b(this.bL))
          {
            if (P())
            {
              O();
              this.an = 8;
            }
            else if ((this.bv) && (a(4)))
            {
              addErrorNumber(180);
              c(31);
            }
            else
            {
              addErrorNumber(203);
              c(14);
            }
          }
          else
          {
            addErrorNumber(204);
            c(14);
            if (this.u != null)
            {
              this.u.b();
              this.u = null;
              continue;
              this.ar = 0;
              i1 = hasSDCard();
              this.aE[0] = i1;
              if (i()) {}
              for (i1 = 1;; i1 = 0)
              {
                this.aE[3] = i1;
                i1 = h();
                this.aE[2] = i1;
                if (a(2))
                {
                  i1 = F();
                  this.aE[1] = i1;
                }
                if ((!a(0)) && (!a(1))) {
                  break label680;
                }
                c(4);
                break;
              }
              label680:
              if (a(3))
              {
                c(20);
              }
              else if (a(2))
              {
                if (f() <= 0)
                {
                  addErrorNumber(201);
                  c(14);
                }
                else if ((!y()) && (l()) && (!this.w))
                {
                  c(5);
                }
                else
                {
                  c(9);
                }
              }
              else
              {
                this.an = 9;
                continue;
                localObject = this.bM.iterator();
                while (((Iterator)localObject).hasNext())
                {
                  localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
                  if (localDownloadComponent.d() != SUtils.getPreferenceInt("CurrentVersion" + localDownloadComponent.a(), 0, mPreferencesName))
                  {
                    localDownloadComponent.c();
                    SUtils.setPreference("CurrentVersion" + localDownloadComponent.a(), Integer.valueOf(localDownloadComponent.d()), mPreferencesName);
                    SUtils.setPreference("IsGenericBuild" + localDownloadComponent.a(), Boolean.valueOf(localDownloadComponent.b()), mPreferencesName);
                  }
                }
                SaveDateLastUpdate(DATA_PATH);
                c(13);
                continue;
                s();
                if (m_iRealRequiredSize > 0L) {
                  GoogleAnalyticsTracker.startTimingTracking("Installer", System.currentTimeMillis(), "Total Download Time", GoogleAnalyticsConstants.Label.g);
                }
                localObject = this.bM.iterator();
                while (((Iterator)localObject).hasNext())
                {
                  localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
                  if ((localDownloadComponent.a().startsWith("patch")) || (localDownloadComponent.a().startsWith("main"))) {
                    localDownloadComponent.b(marketPath);
                  } else {
                    localDownloadComponent.b(sd_folder);
                  }
                }
                localObject = this.bM.iterator();
                while (((Iterator)localObject).hasNext())
                {
                  localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
                  if (localDownloadComponent.d() != SUtils.getPreferenceInt("CurrentVersion" + localDownloadComponent.a(), 0, mPreferencesName))
                  {
                    localDownloadComponent.c();
                    SUtils.setPreference("CurrentVersion" + localDownloadComponent.a(), Integer.valueOf(localDownloadComponent.d()), mPreferencesName);
                    SUtils.setPreference("IsGenericBuild" + localDownloadComponent.a(), Boolean.valueOf(localDownloadComponent.b()), mPreferencesName);
                  }
                }
                this.an = 7;
              }
            }
          }
        }
      }
    }
    if (((this.bU == 1) && (!y())) || ((this.bU == 0) && ((y()) || (!A()))))
    {
      localObject = this.bM.iterator();
      while (((Iterator)localObject).hasNext()) {
        ((DownloadComponent)((Iterator)localObject).next()).w();
      }
      if (m_iRealRequiredSize > 0L) {
        GoogleAnalyticsTracker.stopTimingTracking("Installer", System.currentTimeMillis(), "Total Download Time", GoogleAnalyticsConstants.Label.g);
      }
      addErrorNumber(246);
      c(14);
      return;
    }
    Object localObject = this.bM.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((DownloadComponent)((Iterator)localObject).next()).s();
    }
    m_iDownloadedSize = 0L;
    localObject = this.bM.iterator();
    while (((Iterator)localObject).hasNext())
    {
      localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
      m_iDownloadedSize += localDownloadComponent.u();
    }
    this.bc = ((int)(m_iDownloadedSize >> 10));
    localObject = this.bM.iterator();
    int i1 = 1;
    label1412:
    if (((Iterator)localObject).hasNext())
    {
      if (((DownloadComponent)((Iterator)localObject).next()).t()) {
        break label1745;
      }
      i1 = 0;
    }
    label1491:
    label1742:
    label1743:
    label1745:
    for (;;)
    {
      break label1412;
      if (i1 != 0)
      {
        if (m_iRealRequiredSize > 0L) {
          GoogleAnalyticsTracker.sendTimingTracking("Installer", System.currentTimeMillis(), "Total Download Time", GoogleAnalyticsConstants.Label.g);
        }
        c(13);
      }
      localObject = this.bM.iterator();
      i1 = i2;
      if (((Iterator)localObject).hasNext())
      {
        if (!((DownloadComponent)((Iterator)localObject).next()).v()) {
          break label1742;
        }
        if (m_iRealRequiredSize > 0L) {
          GoogleAnalyticsTracker.stopTimingTracking("Installer", System.currentTimeMillis(), "Total Download Time", GoogleAnalyticsConstants.Label.g);
        }
        i1 = 1;
      }
      for (;;)
      {
        break label1491;
        if (i1 == 0) {
          break label1743;
        }
        addErrorNumber(550);
        c(14);
        break;
        float f3 = (float)((this.k / 1024.0D + this.bc) / 1024.0D);
        float f2 = (float)(this.j / 1048576.0D);
        float f1 = f3;
        if (f3 > f2) {
          f1 = f2;
        }
        localObject = getString(2131034265).replace("{SIZE}", this.m.format(f1)).replace("{TOTAL_SIZE}", this.m.format(f2));
        if ((!bIsPaused) && (this.an != 5))
        {
          runOnUiThread(new i(this, (String)localObject));
          return;
        }
        if (!bIsPaused) {
          break label101;
        }
        a(12, (String)localObject, (int)(this.j / 1024L + 1L), (int)(this.k / 1024L) + this.bc);
        return;
      }
      break;
    }
  }
  
  private static String readVersion()
  {
    return SUtils.ReadFile(SaveFolder + "/prefs/gl_ver");
  }
  
  private void s()
  {
    if (this.bU != 0)
    {
      if (this.aH == null) {
        this.aH = this.aF.createWifiLock(1, "Installer");
      }
      if (!this.aH.isHeld()) {
        this.aH.acquire();
      }
      if (this.aI == null) {
        this.aI = ((PowerManager)getSystemService("power")).newWakeLock(1, "Installer_PowerLock");
      }
      if (!this.aI.isHeld()) {
        this.aI.acquire();
      }
    }
  }
  
  private static void saveVersion(String paramString)
  {
    try
    {
      String str = SaveFolder + "/prefs/gl_ver";
      File localFile = new File(str);
      if (!localFile.exists()) {
        new File(localFile.getParent()).mkdirs();
      }
      for (;;)
      {
        SUtils.WriteFile(str, paramString);
        return;
        localFile.delete();
      }
      return;
    }
    catch (Exception paramString) {}
  }
  
  private static void sendHDIDFVtracker()
  {
    if (!SUtils.getPreferenceBoolean("HDIDFV_sent", false, mPreferencesName))
    {
      String str = Device.getHDIDFV();
      SUtils.setPreference("HDIDFV_sent", Boolean.valueOf(true), mPreferencesName);
      GoogleAnalyticsTracker.trackEvent("Configuration", "HDIDFV", str, null);
    }
  }
  
  public static void startGame()
  {
    sbStarted = true;
    m_sInstance.G();
  }
  
  private void t()
  {
    if (this.bU != 0)
    {
      if (this.aH != null)
      {
        if (this.aH.isHeld()) {
          this.aH.release();
        }
        this.aH = null;
      }
      if (this.aI != null)
      {
        if (this.aI.isHeld()) {
          this.aI.release();
        }
        this.aI = null;
      }
    }
  }
  
  private void u()
  {
    if (this.as != 12) {}
    String str;
    do
    {
      do
      {
        return;
      } while ((this.as == 12) && (this.an != 7) && ((this.an != 5) || (!bIsPaused)));
      float f3 = (float)((this.k / 1024.0D + this.bc) / 1024.0D);
      float f2 = (float)(this.j / 1048576.0D);
      float f1 = f3;
      if (f3 > f2) {
        f1 = f2;
      }
      str = getString(2131034265).replace("{SIZE}", this.m.format(f1)).replace("{TOTAL_SIZE}", this.m.format(f2));
      if ((!bIsPaused) && (this.an != 5))
      {
        runOnUiThread(new i(this, str));
        return;
      }
    } while (!bIsPaused);
    a(12, str, (int)(this.j / 1024L + 1L), (int)(this.k / 1024L) + this.bc);
  }
  
  private void v()
  {
    label150:
    int i1;
    Object localObject;
    switch (this.as)
    {
    case 26: 
    case 28: 
    case 29: 
    default: 
    case 23: 
    case 24: 
      label274:
      label293:
      do
      {
        for (;;)
        {
          if (!this.bX)
          {
            getClass();
            c(21);
          }
          return;
          if (this.bL.equals("")) {
            m();
          }
          if (!sUpdateAPK)
          {
            if ((!this.aS) && (!this.bX))
            {
              if (!new File(DATA_PATH + "InsTime").exists()) {
                SaveDateLastUpdate(DATA_PATH);
              }
              for (;;)
              {
                long l1;
                long l2;
                for (i1 = 0;; i1 = 1)
                {
                  if (i1 == 0) {
                    break label293;
                  }
                  this.aS = true;
                  sbStarted = false;
                  this.bN = 0;
                  this.bX = true;
                  c(24);
                  break;
                  localObject = SUtils.ReadFile(DATA_PATH + "InsTime").split("/");
                  l1 = Long.parseLong(localObject[0]);
                  l2 = System.currentTimeMillis() / 1000L;
                  if (localObject.length > 2)
                  {
                    SaveDateLastUpdate(DATA_PATH);
                    break label150;
                  }
                  if (l2 - l1 < 604800L) {
                    break label274;
                  }
                  SaveDateLastUpdate(DATA_PATH);
                }
                if (l2 - l1 < 0L) {
                  SaveDateLastUpdate(DATA_PATH);
                }
              }
            }
            this.aS = true;
            this.aU = true;
            c(21);
          }
        }
      } while (sUpdateAPK);
      localObject = this.bM.iterator();
      i1 = 1;
      label329:
      if (((Iterator)localObject).hasNext())
      {
        if (((DownloadComponent)((Iterator)localObject).next()).p()) {
          break label627;
        }
        i1 = 0;
      }
      break;
    }
    label450:
    label624:
    label627:
    for (;;)
    {
      break label329;
      if (i1 != 0)
      {
        c(25);
        break;
      }
      if ((!b(this.bL + "&head=1")) && (SUtils.hasConnectivity() == 1))
      {
        this.bN = 0;
        c(21);
        break;
      }
      c(28);
      break;
      if (sUpdateAPK) {
        break;
      }
      localObject = this.bM.iterator();
      i1 = 0;
      while (((Iterator)localObject).hasNext())
      {
        DownloadComponent localDownloadComponent = (DownloadComponent)((Iterator)localObject).next();
        localDownloadComponent.q();
        if ((!localDownloadComponent.b()) && (SUtils.getPreferenceBoolean("IsGenericBuild" + localDownloadComponent.a(), false, mPreferencesName)))
        {
          i1 = 1;
        }
        else
        {
          if (localDownloadComponent.d() <= SUtils.getPreferenceInt("CurrentVersion" + localDownloadComponent.a(), 0, mPreferencesName)) {
            break label624;
          }
          i1 = 1;
        }
      }
      for (;;)
      {
        break label450;
        if (i1 != 0)
        {
          c(27);
          break;
        }
        SaveDateLastUpdate(DATA_PATH);
        this.aU = true;
        this.bN = 0;
        c(21);
        break;
        this.aU = true;
        break;
        if (sUpdateAPK) {
          break;
        }
        c(12);
        break;
      }
    }
  }
  
  private void w()
  {
    c(2);
  }
  
  private int x()
  {
    int i2 = 2;
    Object localObject = SUtils.getOverriddenSetting(DATA_PATH + "qaTestingConfigs.txt", "WIFI_MODE");
    int i1;
    if (localObject != null)
    {
      if (localObject == null) {
        break label102;
      }
      if ((!((String)localObject).equals("WIFI_ONLY")) && (!((String)localObject).equals("TRUE"))) {
        break label68;
      }
      i1 = 1;
    }
    label68:
    label102:
    do
    {
      do
      {
        return i1;
        localObject = null;
        break;
        if ((((String)localObject).equals("WIFI_3G")) || (((String)localObject).equals("FALSE"))) {
          return 0;
        }
        i1 = i2;
      } while (((String)localObject).equals("WIFI_3G_ORANGE_IL"));
      i1 = mDeviceInfo.getPhoneType();
      localObject = mDeviceInfo;
      if (i1 != 2)
      {
        i1 = mDeviceInfo.getSimState();
        localObject = mDeviceInfo;
        if (i1 != 1)
        {
          i1 = mDeviceInfo.getSimState();
          localObject = mDeviceInfo;
          if (i1 != 0) {}
        }
        else
        {
          return 1;
        }
      }
      this.bV = new Device();
      this.bW = new XPlayer(this.bV);
      this.bW.a();
      while (!this.bW.b()) {
        try
        {
          Thread.sleep(50L);
        }
        catch (Exception localException1) {}
      }
      XPlayer localXPlayer1 = this.bW;
      if (XPlayer.getWHTTP().t == null) {
        return 0;
      }
      if (XPlayer.getLastErrorCode() != 0) {
        break label350;
      }
      localXPlayer1 = this.bW;
      if (XPlayer.getWHTTP().t.contains("|")) {}
      try
      {
        localXPlayer1 = this.bW;
        this.bT = Integer.parseInt(XPlayer.getWHTTP().t.split("\\|")[1]);
        localXPlayer1 = this.bW;
        if (XPlayer.getWHTTP().t.contains("WIFI_ONLY")) {
          return 1;
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          this.bT = -1;
        }
        XPlayer localXPlayer2 = this.bW;
        if (XPlayer.getWHTTP().t.contains("WIFI_3G")) {
          return 0;
        }
        localXPlayer2 = this.bW;
        i1 = i2;
      }
    } while (XPlayer.getWHTTP().t.contains("WIFI_3G_ORANGE_IL"));
    label350:
    return 0;
  }
  
  private boolean y()
  {
    boolean bool3 = false;
    if ((this.aG != null) && (this.aG.getNetworkInfo(1) != null)) {}
    for (boolean bool1 = this.aG.getNetworkInfo(1).isConnected();; bool1 = false)
    {
      boolean bool2 = bool3;
      if (this.aF.isWifiEnabled())
      {
        bool2 = bool3;
        if (bool1) {
          bool2 = true;
        }
      }
      return bool2;
    }
  }
  
  private int z()
  {
    int i2 = 0;
    int i1;
    switch (this.aW)
    {
    default: 
      i1 = i2;
    }
    for (;;)
    {
      this.aW += 1;
      return i1;
      i1 = i2;
      if (!this.aF.isWifiEnabled())
      {
        this.aF.setWifiEnabled(true);
        this.aW -= 1;
        i1 = i2;
        continue;
        i1 = i2;
        if (this.aH == null)
        {
          this.aH = this.aF.createWifiLock(1, "Installer");
          this.aW -= 1;
          i1 = i2;
          continue;
          i1 = i2;
          if (!this.aH.isHeld())
          {
            this.aH.acquire();
            this.aW -= 1;
            i1 = i2;
            continue;
            if (this.aF.getConnectionInfo() == null)
            {
              this.aW -= 1;
              this.aX += 1;
            }
            try
            {
              Thread.sleep(1000L);
              i1 = i2;
              if (this.aX > 30) {
                if (this.u != null)
                {
                  this.u.b();
                  this.u = null;
                  i1 = -1;
                  continue;
                  this.aX = 0;
                  i1 = i2;
                  continue;
                  if (!y())
                  {
                    this.aW -= 1;
                    this.aX += 1;
                  }
                }
              }
            }
            catch (Exception localException1)
            {
              try
              {
                Thread.sleep(1000L);
                i1 = i2;
                if (this.aX <= 30) {
                  continue;
                }
                if ((this.bd == null) && (this.u != null))
                {
                  this.u.b();
                  this.u = null;
                }
                this.aW = -1;
                this.aX = 0;
                i1 = -1;
                continue;
                this.aX = -1;
                this.aW = 0;
                this.bU = 1;
                c(12);
                this.aJ = true;
                i1 = 1;
                continue;
                localException1 = localException1;
              }
              catch (Exception localException2)
              {
                for (;;) {}
              }
              i1 = -1;
            }
          }
        }
      }
    }
  }
  
  public final String a(int paramInt, String paramString1, String paramString2)
  {
    String str2 = getString(paramInt);
    String str1 = paramString1;
    if (paramString1 == null) {
      str1 = "{SIZE}";
    }
    return str2.replace(str1, paramString2);
  }
  
  public final void a()
  {
    if (((this.as != 12) && (this.as != 20) && (this.as != 41)) || (this.an == 5)) {}
    while (((this.as == 12) && (this.an != 7)) || (bIsPaused)) {
      return;
    }
    runOnUiThread(new h(this));
  }
  
  public final boolean a(int paramInt)
  {
    return this.aE[paramInt] == 1;
  }
  
  public final boolean a(String paramString)
  {
    long l1 = System.currentTimeMillis();
    new l(this, paramString).start();
    while ((isReached == null) && (System.currentTimeMillis() - l1 < 2000L)) {}
    if (isReached == null) {
      isReached = Boolean.valueOf(false);
    }
    return isReached.booleanValue();
  }
  
  public final ArrayList<Button> b()
  {
    return this.cf;
  }
  
  public final void b(int paramInt)
  {
    switch (this.ci)
    {
    }
    for (;;)
    {
      return;
      if (paramInt == 2131230758)
      {
        saveVersion("0.0.1");
        c(30);
        return;
      }
      if (paramInt != 2131230760) {
        continue;
      }
      if ((this.bW != null) && (this.bW.x))
      {
        this.aU = false;
        c(21);
        return;
      }
      c(21);
      return;
      if (paramInt != 2131230758) {
        continue;
      }
      this.aU = false;
      c(21);
      H();
      return;
      if ((paramInt != 2131230762) || (this.as != 12)) {
        continue;
      }
      this.ao = this.an;
      this.an = 5;
      b(2130903056, 7);
      return;
      if (paramInt != 2131230762) {
        continue;
      }
      b(2130903056, 28);
      return;
      if (paramInt == 2131230758)
      {
        this.v = true;
        c(21);
        H();
        return;
      }
      if (paramInt != 2131230760) {
        continue;
      }
      b(2130903057, 27);
      return;
      if (paramInt == 2131230758)
      {
        new d(this).start();
        this.aU = false;
        c(21);
        H();
        try
        {
          this.aU = false;
          k();
          Object localObject = (com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)this.d.get(this.ar);
          localObject = new File(DATA_PATH + ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)localObject).a().replace(".\\\\", "").replace(".\\", "").replace("\\", "/") + "/" + ((com.gameloft.android.ANMP.GloftPEHM.installer.utils.g)localObject).c());
          if (!((File)localObject).exists()) {
            continue;
          }
          ((File)localObject).delete();
          return;
        }
        catch (Exception localException1)
        {
          return;
        }
      }
      if ((paramInt != 2131230760) || (this.as != 12)) {
        continue;
      }
      this.an = this.ao;
      b(2130903057, 5);
      return;
      if (paramInt == 2131230758)
      {
        this.ar = 0;
        k();
        if (!y())
        {
          addErrorNumber(244);
          if (l())
          {
            c(5);
            return;
          }
          if (this.bG == 0)
          {
            c(12);
            return;
          }
          c(6);
          return;
        }
        c(12);
        return;
      }
      if (paramInt != 2131230760) {
        continue;
      }
      if (this.u != null)
      {
        this.u.b();
        this.u = null;
      }
      this.aU = false;
      c(21);
      return;
      if (paramInt == 2131230758)
      {
        c(6);
        return;
      }
      if (paramInt == 2131230760)
      {
        if (this.bG == 1)
        {
          c(21);
          return;
        }
        if ((this.aF.isWifiEnabled()) && (getApplicationContext().getPackageManager().checkPermission("android.permission.CHANGE_WIFI_STATE", "com.gameloft.android.ANMP.GloftPEHM") == 0)) {
          this.aF.setWifiEnabled(false);
        }
      }
      try
      {
        Thread.sleep(50L);
        this.aK = true;
        if (A())
        {
          this.bU = 0;
          this.w = true;
          if (l())
          {
            c(12);
            return;
          }
          c(9);
          return;
        }
        Intent localIntent = new Intent("android.settings.WIRELESS_SETTINGS");
        localIntent.setFlags(268435456);
        startActivity(localIntent);
        this.bH = true;
        return;
        if (paramInt != 2131230762) {
          continue;
        }
        this.aU = false;
        c(21);
        return;
        if (paramInt != 2131230758) {
          continue;
        }
        this.aU = false;
        c(21);
        H();
        return;
        if (paramInt != 2131230758) {
          continue;
        }
        this.aU = false;
        c(21);
        return;
        if (paramInt == 2131230758)
        {
          c(24);
          return;
        }
        if (paramInt != 2131230760) {
          continue;
        }
        this.bN = 0;
        c(21);
        return;
        if (paramInt != 2131230758) {
          continue;
        }
        this.aU = false;
        c(21);
        H();
        return;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  final void c()
  {
    this.b = ((NotificationManager)getSystemService("notification"));
    this.b.cancel(7176);
  }
  
  final void d()
  {
    this.cg = -1;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SUtils.checkNewVersionInstalled();
    SaveFolder = SUtils.getSaveFolder();
    LIBS_PATH = SaveFolder + LIBS_PATH;
    this.bx = (SaveFolder + this.bx);
    paramBundle = getIntent();
    if (SUtils.getPreferenceString("SDFolder", "", mPreferencesName).equals(""))
    {
      sd_folder = D();
      SUtils.setPreference("SDFolder", sd_folder, mPreferencesName);
      DATA_PATH = sd_folder + "/";
      if ((paramBundle == null) || (paramBundle.getExtras() == null) || (!paramBundle.getExtras().getBoolean("finishGame"))) {
        break label183;
      }
      H();
    }
    for (;;)
    {
      return;
      sd_folder = SUtils.getPreferenceString("SDFolder", "", mPreferencesName);
      break;
      label183:
      this.cn = new Handler(Looper.getMainLooper());
      paramBundle = new GPUInstallerGLSurfaceView(getApplication());
      Object localObject = getWindowManager().getDefaultDisplay();
      label235:
      int i1;
      if (((Display)localObject).getWidth() < ((Display)localObject).getHeight())
      {
        ((Display)localObject).getWidth();
        localObject = new RelativeLayout(this);
        RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams1.addRule(15);
        localLayoutParams1.addRule(14);
        RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        localLayoutParams1.addRule(15);
        localLayoutParams1.addRule(14);
        ((RelativeLayout)localObject).addView(paramBundle, localLayoutParams2);
        ((RelativeLayout)localObject).addView(new ProgressBar(this, null, 16842874), localLayoutParams1);
        setContentView((View)localObject);
        new m(this).start();
        m_portalCode = "google_market";
        this.aF = ((WifiManager)getSystemService("wifi"));
        mDeviceInfo = (TelephonyManager)getSystemService("phone");
        this.aG = ((ConnectivityManager)getSystemService("connectivity"));
        this.bZ = new com.gameloft.android.ANMP.GloftPEHM.installer.utils.d();
        this.bZ.a(this);
        B();
        if ((!i()) && (!this.bw)) {
          break label530;
        }
        paramBundle = new File(SaveFolder).list();
        i1 = 0;
        label437:
        if ((i1 < paramBundle.length) && ((!paramBundle[i1].startsWith("pack")) || (!paramBundle[i1].endsWith(".info")))) {}
      }
      try
      {
        localObject = new File(SaveFolder + "/" + paramBundle[i1]);
        if (((File)localObject).exists()) {
          ((File)localObject).delete();
        }
        i1 += 1;
        break label437;
        ((Display)localObject).getHeight();
        break label235;
        label530:
        this.bN = 0;
        this.aU = false;
        Tracking.init();
        m_sInstance = this;
        paramBundle = SUtils.getOverriddenSetting(DATA_PATH + "qaTestingConfigs.txt", "OUTPUT_ACP_REVISION");
        if ((paramBundle == null) || (!paramBundle.equals("1"))) {
          continue;
        }
        if ((!"9739".equals("")) && (!"9739".equals("0")))
        {
          b("ACP Revision", "9739");
          return;
        }
        b("ACP Revision", "Android Core Package not versioned");
        return;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.u != null)
    {
      this.u.b();
      this.u = null;
    }
    t();
    this.t = null;
    if (!sbStarted) {
      H();
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    LowProfileListener.onKeyDown(this, paramInt);
    if ((paramInt == 25) || (paramInt == 24) || (paramInt == 27)) {
      return false;
    }
    for (;;)
    {
      try
      {
        i1 = this.cf.size();
        if (i1 <= 0) {
          continue;
        }
        if ((paramInt != 19) && (paramInt != 22)) {
          continue;
        }
        paramInt = this.cg + 1;
        this.cg = paramInt;
        if (paramInt >= i1) {
          this.cg = (i1 - 1);
        }
        a((Button)this.cf.get(this.cg));
      }
      catch (Exception paramKeyEvent)
      {
        int i1;
        continue;
      }
      paramInt = this.cg - 1;
      this.cg = paramInt;
      if (paramInt < 0) {
        this.cg = 0;
      }
      a((Button)this.cf.get(this.cg));
      continue;
      if ((paramInt == 96) && (this.cg >= 0) && (this.cg < i1)) {
        runOnUiThread(new g(this, (Button)this.cf.get(this.cg)));
      }
      return true;
      if (paramInt != 20) {
        if (paramInt != 21) {}
      }
    }
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (((paramInt == 4) || (paramInt == 97)) && (paramKeyEvent.getRepeatCount() == 0)) {
      switch (this.ci)
      {
      }
    }
    while ((paramInt != 25) && (paramInt != 24) && (paramInt != 27))
    {
      return true;
      b(2131230760);
      return true;
      b(2131230758);
      return true;
      b(2131230762);
      return true;
      b(2131230762);
      return true;
      b(2131230760);
      return true;
      b(2131230760);
      return true;
      b(2131230762);
      return true;
      b(2131230760);
      return true;
      b(2131230758);
      return true;
      b(2131230758);
      return true;
      b(2131230760);
      return true;
      b(2131230760);
      return true;
      b(2131230758);
      return true;
      b(2131230762);
      return true;
      b(2131230758);
      return true;
      b(2131230760);
      return true;
    }
    return false;
  }
  
  protected void onPause()
  {
    super.onPause();
    bIsPaused = true;
    if (m_Dialog != null) {
      m_Dialog.cancel();
    }
    LowProfileListener.unRegisterListener(this);
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    if (this.ci != -1) {
      b(this.cj, this.ci);
    }
    super.onResume();
    bIsPaused = false;
    if ((this.bG == 1) && (this.bH) && (this.as != 1))
    {
      if (y()) {
        c(12);
      }
      this.bH = false;
    }
    c();
    LowProfileListener.makeActivityImmersive(this);
    LowProfileListener.registerListener(this);
  }
  
  protected void onStart()
  {
    super.onStart();
    GoogleAnalyticsTracker.activityStart(this);
    if (SUtils.getPreferenceString(ApplicationSetUp.b, ApplicationSetUp.d, ApplicationSetUp.a).equals(ApplicationSetUp.c)) {}
    for (boolean bool = true;; bool = false)
    {
      GoogleAnalytics.getInstance(this).a(bool);
      GoogleAnalyticsTracker.Init(getApplicationContext());
      if (!this.bO)
      {
        this.bO = true;
        this.t = getAssets();
        new Thread(this).start();
      }
      return;
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    GoogleAnalyticsTracker.activityStop(this);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((m_toastSize == 0) && (m_toastSize == 0)) {
      m_toastSize = Integer.parseInt(getString(2131296290).replaceAll("[\\D]+[^.]", "")) + 25 + m_toastExtra;
    }
    int i1 = paramMotionEvent.getAction();
    Object localObject = getWindowManager().getDefaultDisplay();
    int i2 = ((Display)localObject).getWidth();
    ((Display)localObject).getHeight();
    if ((paramMotionEvent.getX() < m_toastSize) && (paramMotionEvent.getY() < m_toastSize))
    {
      switch (i1)
      {
      }
      label464:
      for (;;)
      {
        return false;
        if ((leftTapCount != 0) && (System.currentTimeMillis() - startTime >= m_delayTime)) {
          break;
        }
        leftTapCount += 1;
        startTime = System.currentTimeMillis();
        if ((!statePressA) && (!statePressB) && (!statePressC)) {
          statePressA = true;
        }
        for (;;)
        {
          if (leftTapCount != TAP_COUNT_MAX) {
            break label464;
          }
          leftTapCount = 0;
          if ((m_prevErrorMessage == "") || ((this.as != 1) && (this.as != 14) && (this.as != 5) && (this.as != 31))) {
            break;
          }
          paramMotionEvent = new StringBuilder();
          m_Dialog = new AlertDialog.Builder(this).setNeutralButton("Close", null).create();
          paramMotionEvent.append("Configuration: " + I());
          paramMotionEvent.append("\nDevice: " + Build.MANUFACTURER + " " + Build.MODEL + " " + Build.VERSION.RELEASE);
          paramMotionEvent.append("\nGame: " + getString(2131034935) + " 2" + ".2" + ".1");
          paramMotionEvent.append("\nError:" + m_prevErrorMessage);
          m_Dialog.setMessage(paramMotionEvent.toString());
          m_Dialog.setTitle("Installer version 3.7.9739");
          m_Dialog.show();
          return true;
          if ((statePressA) && (statePressB) && (!statePressC))
          {
            statePressC = true;
          }
          else
          {
            statePressC = false;
            statePressB = false;
            statePressA = true;
          }
        }
      }
      leftTapCount = 0;
      return false;
    }
    if ((paramMotionEvent.getX() < i2) && (paramMotionEvent.getX() > i2 - m_toastSize) && (paramMotionEvent.getY() < m_toastSize))
    {
      leftTapCount = 0;
      switch (i1)
      {
      default: 
        return false;
      }
      if (System.currentTimeMillis() - startTime < m_delayTime)
      {
        if ((statePressA) && (!statePressB))
        {
          startTime = System.currentTimeMillis();
          statePressB = true;
          return false;
        }
        if ((statePressA) && (statePressB) && (statePressC))
        {
          statePressC = false;
          statePressB = false;
          statePressA = false;
          m_Dialog = new AlertDialog.Builder(this).setNeutralButton("Close", null).create();
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Configuration: " + I());
          ((StringBuilder)localObject).append("\nInstallation Path: " + DATA_PATH);
          StringBuilder localStringBuilder = new StringBuilder("\nBiggest file: ");
          if (pack_biggestFile != -1L)
          {
            paramMotionEvent = new DecimalFormat("#,##0.00").format((pack_biggestFile >> 10) / 1024.0D) + " MB";
            ((StringBuilder)localObject).append(paramMotionEvent);
            localStringBuilder = new StringBuilder("\nNumber of files: ");
            if (pack_NoFiles == -1) {
              break label841;
            }
          }
          label841:
          for (paramMotionEvent = Integer.valueOf(pack_NoFiles);; paramMotionEvent = "")
          {
            ((StringBuilder)localObject).append(paramMotionEvent);
            m_Dialog.setMessage(((StringBuilder)localObject).toString());
            m_Dialog.setTitle("Installer version 3.7.9739");
            m_Dialog.show();
            return true;
            paramMotionEvent = "";
            break;
          }
        }
        statePressC = false;
        statePressB = false;
        statePressA = false;
        return false;
      }
      statePressC = false;
      statePressB = false;
      statePressA = false;
      rightTapCount = 0;
      return false;
    }
    statePressC = false;
    statePressB = false;
    statePressA = false;
    rightTapCount = 0;
    return false;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    this.bg = paramBoolean;
    if (!paramBoolean) {}
    for (paramBoolean = true;; paramBoolean = false)
    {
      s_isPauseGame = paramBoolean;
      this.bS = System.currentTimeMillis();
      return;
    }
  }
  
  public void run()
  {
    Looper.prepare();
    this.as = 0;
    this.an = 0;
    this.bN = 0;
    this.aU = true;
    this.aZ = false;
    this.m = new DecimalFormat("#,##0.00");
    if (!SUtils.getPreferenceBoolean("HDIDFV_sent", false, mPreferencesName))
    {
      String str1 = Device.getHDIDFV();
      SUtils.setPreference("HDIDFV_sent", Boolean.valueOf(true), mPreferencesName);
      GoogleAnalyticsTracker.trackEvent("Configuration", "HDIDFV", str1, null);
    }
    int i1;
    if (i())
    {
      n();
      i1 = 1;
    }
    for (;;)
    {
      long l1;
      if ((this.as != 21) && (!sbStarted))
      {
        this.aR = false;
        l1 = System.currentTimeMillis();
        if (bIsPaused)
        {
          if (((this.as != 12) && (this.as != 20)) || ((this.as == 12) && (this.an != 7) && (this.an != 5)))
          {
            try
            {
              Thread.sleep(50L);
            }
            catch (Exception localException1) {}
            continue;
          }
          if ((this.as != 12) || (this.an != 7)) {}
        }
      }
      try
      {
        Thread.sleep(100L);
        q();
        if ((this.as == 12) && (this.an == 7)) {
          if (System.currentTimeMillis() - l1 != 0L) {}
        }
      }
      catch (Exception localException6)
      {
        try
        {
          Thread.sleep(50L);
          for (;;)
          {
            this.aR = true;
            break;
            try
            {
              Thread.sleep(50L / (System.currentTimeMillis() - l1));
            }
            catch (Exception localException2) {}
            continue;
            try
            {
              Thread.sleep(20L);
            }
            catch (Exception localException3) {}
          }
          Iterator localIterator;
          Object localObject;
          if ((this.bN == 0) && (this.aU))
          {
            if (i1 != 0)
            {
              D();
              localIterator = this.bf.iterator();
              while (localIterator.hasNext())
              {
                localObject = (Pair)localIterator.next();
                GoogleAnalyticsTracker.trackEvent("Configuration", "SDcard" + 0, "After Install", (Long)((Pair)localObject).second);
              }
            }
            new File(LIBS_PATH + "/libsampleSandBox.so");
          }
          try
          {
            if (sNativeLibs.size() <= 0) {
              break label843;
            }
            localIterator = sNativeLibs.iterator();
            i1 = 0;
            for (;;)
            {
              i3 = i1;
              i2 = i1;
              try
              {
                if (localIterator.hasNext())
                {
                  i3 = i1;
                  localObject = (String)localIterator.next();
                  i1 += 1;
                  i3 = i1;
                  if (((String)localObject).indexOf("LittlestPetShop") == -1) {
                    break label695;
                  }
                  i3 = i1;
                  if (!Game.isARMv7()) {
                    break label665;
                  }
                  i3 = i1;
                  System.load(LIBS_PATH + "libLittlestPetShopArm7.so");
                }
              }
              catch (Exception localException4)
              {
                i2 = i3;
              }
            }
          }
          catch (Exception localException8)
          {
            for (;;)
            {
              int i3;
              label557:
              label665:
              label695:
              label733:
              label742:
              String str2;
              int i2 = 0;
              continue;
              i2 = 0;
            }
          }
          if (i2 == 0)
          {
            if (new String("LittlestPetShop").indexOf("LittlestPetShop") == -1) {
              break label733;
            }
            if (Game.isARMv7()) {
              System.loadLibrary("LittlestPetShopArm7");
            }
          }
          else
          {
            new File(LIBS_PATH + "/libsampleSandBox.so");
            if (getApplicationContext().getPackageManager().checkPermission("android.permission.CHANGE_WIFI_STATE", "com.gameloft.android.ANMP.GloftPEHM") == 0)
            {
              if (!this.aJ) {
                break label742;
              }
              this.aF.setWifiEnabled(false);
            }
          }
          for (;;)
          {
            createNoMedia(DATA_PATH);
            Tracking.setFlag(17);
            Tracking.onLaunchGame(2);
            Game.Z = true;
            sbStarted = true;
            while (s_isPauseGame) {
              try
              {
                Thread.sleep(100L);
              }
              catch (Exception localException5) {}
            }
            i3 = i1;
            System.load(LIBS_PATH + "libLittlestPetShopArm.so");
            break;
            i3 = i1;
            System.load(LIBS_PATH + (String)localObject);
            break;
            System.loadLibrary("LittlestPetShopArm");
            break label557;
            System.loadLibrary("LittlestPetShop");
            break label557;
            if (this.aK) {
              this.aF.setWifiEnabled(true);
            }
          }
          if ((this.bN == 0) && (this.aU))
          {
            str2 = SUtils.getPreferenceString("SDFolder", "", mPreferencesName);
            if (str2.equals(""))
            {
              a(0L);
              createNoMedia(str2);
              SaveDateLastUpdate(str2);
            }
            G();
            return;
          }
          H();
          return;
          localException6 = localException6;
        }
        catch (Exception localException7)
        {
          for (;;) {}
        }
      }
      label843:
      i1 = 0;
    }
  }
}
