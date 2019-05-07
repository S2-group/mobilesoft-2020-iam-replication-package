package com.xvideostudio.videoeditor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;
import com.duapps.ad.base.DuAdNetwork;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GoogleApiAvailability;
import com.intowow.sdk.I2WAPI;
import com.mnt.MntLib;
import com.mobi.sdk.e;
import com.mobvista.msdk.MobVistaSDK;
import com.mobvista.msdk.out.MobVistaSDKFactory;
import com.mobvista.msdk.system.MobVistaSDKImpl;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.xinmei365.fontsdk.FontCenter;
import com.xinmei365.fontsdk.bean.Font;
import com.xinmei365.fontsdk.callback.FontScanCallBack;
import com.xvideostudio.VsCommunity.Api.VscUserinfoSession;
import com.xvideostudio.videoeditor.activity.MainActivity;
import com.xvideostudio.videoeditor.activity.MarketUrlRedirectActivity;
import com.xvideostudio.videoeditor.activity.Tools;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.HomeAppListBean;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.ShareAppListBean;
import com.xvideostudio.videoeditor.gsonentity.SiteInfoBean;
import com.xvideostudio.videoeditor.tool.i;
import com.xvideostudio.videoeditor.tool.j;
import com.xvideostudio.videoeditor.tool.x;
import com.xvideostudio.videoeditor.util.ai;
import com.xvideostudio.videoeditor.util.ak;
import com.xvideostudio.videoeditor.util.al;
import com.xvideostudio.videoeditor.util.am;
import com.xvideostudio.videoeditor.util.ar;
import com.xvideostudio.videoeditor.util.as;
import com.xvideostudio.videoeditor.util.f;
import com.xvideostudio.videoeditor.util.l;
import com.xvideostudio.videoeditor.util.r;
import com.xvideostudio.videoeditor.util.t;
import com.xvideostudio.videoeditor.view.StoryBoardView.a;
import hl.productor.fxlib.HLRenderThread;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.stagex.danmaku.helper.SystemUtility;
import org.xvideo.videoeditor.database.DraftBoxHandler;
import org.xvideo.videoeditor.database.MediaClip;
import org.xvideo.videoeditor.database.MediaClipTrim;
import org.xvideo.videoeditor.database.MyFontEntity;
import org.xvideo.videoeditor.database.PaintDraftHandler;

public class VideoEditorApplication
  extends MultiDexApplication
{
  public static String A;
  public static boolean B;
  public static boolean C;
  public static Boolean D;
  public static HashMap<String, Integer> E;
  public static int[] F;
  public static com.xvideostudio.videoeditor.i.b G;
  public static ArrayList<MediaClipTrim> H;
  public static Map<String, Context> I;
  public static int J;
  public static int K;
  public static List<MySelfAdResponse.HomeAppListBean> L;
  public static List<MySelfAdResponse.ShareAppListBean> M;
  public static boolean N;
  public static String O;
  public static String P;
  public static boolean Q;
  public static boolean R;
  public static boolean S;
  public static boolean T;
  public static boolean U;
  public static boolean V;
  public static boolean W;
  public static boolean X;
  public static boolean Y;
  public static boolean Z;
  public static final boolean a;
  private static Map<String, Map<String, String>> aA;
  private static Boolean aB;
  private static boolean aC;
  private static boolean aD;
  private static long aE;
  private static Tracker aF;
  public static String aa;
  public static String ab;
  public static boolean ap;
  private static String aq;
  private static String ar;
  private static VideoEditorApplication as;
  private static Context at;
  private static String au;
  private static String av;
  private static String aw;
  private static String ax;
  private static Map<String, Typeface> ay;
  private static List<com.xvideostudio.videoeditor.d.w> az;
  public static int b;
  public static int c;
  public static float d;
  public static Boolean e;
  public static Boolean f;
  public static String g;
  public static String h;
  public static int i;
  public static String j;
  public static boolean k;
  public static boolean l;
  public static boolean m;
  public static boolean n;
  public static boolean o;
  public static boolean p;
  public static String q;
  public static String r;
  public static String s;
  public static String t;
  public static String u;
  public static String v;
  public static int w;
  public static Map<String, MyFontEntity> x;
  public static long y;
  public static String z;
  private DraftBoxHandler aG = null;
  private org.xvideo.videoeditor.a.b aH = null;
  private org.xvideo.videoeditor.b.b aI = null;
  private PaintDraftHandler aJ = null;
  private com.b.a.b.d aK = null;
  private boolean aL = true;
  private String aM = "user_id";
  private boolean aN = false;
  private boolean aO = false;
  private boolean aP = false;
  private Handler aQ = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 0: 
        new Thread(new Runnable()
        {
          public void run()
          {
            VideoEditorApplication.this.B();
          }
        }).start();
        return;
      case 1: 
        new Thread(new Runnable()
        {
          public void run()
          {
            int i = this.a.getInt("rawId");
            String str = this.a.getString("rawFilePath");
            boolean bool1 = this.a.getBoolean("isZip", false);
            File localFile = new File(str);
            if ((bool1) || (!localFile.exists()))
            {
              if (bool1) {}
              try
              {
                boolean bool2 = localFile.exists();
                if (bool2) {}
                try
                {
                  l.a(localFile);
                  l.a(VideoEditorApplication.S(), str, i);
                  if (bool1)
                  {
                    ar.a(str, localFile.getParent(), true);
                    l.a(localFile);
                    return;
                  }
                }
                catch (Exception localException2)
                {
                  for (;;)
                  {
                    localException2.printStackTrace();
                  }
                }
                return;
              }
              catch (Exception localException1)
              {
                localException1.printStackTrace();
                if (bool1) {
                  l.a(localFile);
                }
              }
            }
          }
        }).start();
        return;
      case 2: 
        j.a(2131690313);
        return;
      }
      paramAnonymousMessage = paramAnonymousMessage.getData();
      int i = paramAnonymousMessage.getInt("duration");
      j.a(paramAnonymousMessage.getString("text"), 1, i);
    }
  };
  private StoryBoardView.a aR = null;
  private boolean aS = false;
  public com.xvideostudio.videoeditor.materialdownload.c ac = null;
  public Bundle ad;
  public boolean ae = true;
  public int af = 1;
  public Hashtable<String, SiteInfoBean> ag = null;
  public List<String> ah = null;
  public com.xvideostudio.videoeditor.materialdownload.a ai = null;
  public com.xvideostudio.videoeditor.materialdownload.a aj = null;
  public boolean ak = false;
  public String al;
  public String am;
  public Handler an = new Handler() {};
  Map<String, Integer> ao = null;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      as = null;
      b = 0;
      c = 0;
      d = 0.0F;
      e = Boolean.valueOf(false);
      f = Boolean.valueOf(false);
      g = "";
      h = "";
      i = 1496;
      j = "7.0.0";
      l = false;
      m = true;
      n = false;
      o = true;
      p = false;
      r = "https://play.google";
      w = 2;
      y = 0L;
      z = "en-US";
      B = false;
      C = false;
      D = Boolean.valueOf(false);
      E = new HashMap(100);
      F = null;
      G = null;
      H = null;
      I = new HashMap();
      J = 1;
      K = 1;
      L = new ArrayList();
      M = new ArrayList();
      N = false;
      O = "zh-CN";
      P = "ar";
      Q = false;
      R = false;
      S = false;
      T = false;
      U = false;
      V = false;
      W = true;
      X = true;
      Y = true;
      Z = true;
      aa = ar;
      ab = "4835";
      az = null;
      aA = null;
      aB = null;
      aC = false;
      aD = false;
      aE = 0L;
      aF = null;
      ap = false;
      return;
    }
  }
  
  public VideoEditorApplication() {}
  
  private static void T()
  {
    E.put("1f001", Integer.valueOf(2131231221));
    E.put("1f002", Integer.valueOf(2131231222));
    E.put("1f003", Integer.valueOf(2131231223));
    E.put("1f004", Integer.valueOf(2131231224));
    E.put("1f005", Integer.valueOf(2131231225));
    E.put("1f006", Integer.valueOf(2131231226));
    E.put("1f007", Integer.valueOf(2131231227));
    E.put("1f008", Integer.valueOf(2131231228));
    E.put("1f009", Integer.valueOf(2131231229));
    E.put("1f010", Integer.valueOf(2131231230));
    E.put("1f011", Integer.valueOf(2131231231));
    E.put("1f012", Integer.valueOf(2131231232));
    E.put("1f013", Integer.valueOf(2131231233));
    E.put("1f014", Integer.valueOf(2131231234));
    E.put("1f015", Integer.valueOf(2131231235));
    E.put("1f016", Integer.valueOf(2131231236));
    E.put("1f017", Integer.valueOf(2131231237));
    E.put("1f018", Integer.valueOf(2131231238));
    E.put("1f019", Integer.valueOf(2131231239));
    E.put("1f020", Integer.valueOf(2131231240));
    E.put("1f021", Integer.valueOf(2131231241));
    E.put("1f022", Integer.valueOf(2131231242));
    E.put("1f023", Integer.valueOf(2131231243));
    E.put("1f024", Integer.valueOf(2131231244));
    E.put("1f025", Integer.valueOf(2131231245));
    E.put("1f026", Integer.valueOf(2131231246));
    E.put("1f027", Integer.valueOf(2131231247));
    E.put("1f028", Integer.valueOf(2131231248));
    E.put("1f029", Integer.valueOf(2131231249));
    E.put("1f030", Integer.valueOf(2131231250));
    E.put("1f031", Integer.valueOf(2131231251));
    E.put("1f032", Integer.valueOf(2131231252));
    E.put("3f001", Integer.valueOf(2131231253));
    E.put("3f002", Integer.valueOf(2131231254));
    E.put("3f003", Integer.valueOf(2131231255));
    E.put("3f004", Integer.valueOf(2131231256));
    E.put("3f005", Integer.valueOf(2131231257));
    E.put("3f006", Integer.valueOf(2131231258));
    E.put("3f007", Integer.valueOf(2131231259));
    E.put("3f008", Integer.valueOf(2131231260));
    E.put("3f009", Integer.valueOf(2131231261));
    E.put("3f010", Integer.valueOf(2131231262));
    E.put("3f011", Integer.valueOf(2131231263));
    E.put("3f012", Integer.valueOf(2131231264));
    E.put("3f013", Integer.valueOf(2131231265));
    E.put("3f014", Integer.valueOf(2131231266));
    E.put("3f015", Integer.valueOf(2131231267));
    E.put("3f016", Integer.valueOf(2131231268));
    E.put("3f017", Integer.valueOf(2131231269));
    E.put("3f018", Integer.valueOf(2131231270));
    E.put("3f019", Integer.valueOf(2131231271));
    E.put("3f020", Integer.valueOf(2131231272));
    E.put("3f021", Integer.valueOf(2131231273));
    E.put("3f022", Integer.valueOf(2131231274));
    E.put("3f023", Integer.valueOf(2131231275));
    E.put("3f024", Integer.valueOf(2131231276));
  }
  
  private void U()
  {
    final String str1;
    String str2;
    String str3;
    if (!b())
    {
      str1 = aq;
      aa = ar;
      ab = "4835";
      str2 = com.xvideostudio.videoeditor.a.a.a().b();
      str3 = a(this, Process.myPid());
      if (str3 != null)
      {
        if (!str3.equalsIgnoreCase(at.getPackageName())) {
          break label121;
        }
        MobclickAgent.onEvent(this, "PROCESS_MAIN_APPLICATION");
      }
    }
    for (;;)
    {
      if (str3.equals(at.getPackageName()))
      {
        MobclickAgent.onEvent(this, "INIT_ADS_IN_APPLICATION");
        new Thread(new Runnable()
        {
          public void run()
          {
            Looper.prepare();
            i.d("mobivsta", "init");
            MobVistaSDKImpl localMobVistaSDKImpl = MobVistaSDKFactory.getMobVistaSDK();
            Map localMap = localMobVistaSDKImpl.getMVConfigurationMap(str1, this.b);
            localMap.put("applicationID", "com.funcamerastudio.videomaker");
            com.mobvista.msdk.MobVistaConstans.PRELOAD_RESULT_IN_SUBTHREAD = true;
            com.mobvista.msdk.MobVistaConstans.INIT_UA_IN = false;
            localMobVistaSDKImpl.init(localMap, VideoEditorApplication.S());
            Looper.loop();
          }
        }).start();
        W();
        i(str2);
        X();
        V();
      }
      return;
      label121:
      if (str3.equalsIgnoreCase(at.getPackageName() + ":channel")) {
        MobclickAgent.onEvent(this, "PROCESS_UMPUSH_APPLICATION");
      } else if (str3.equalsIgnoreCase(at.getPackageName() + ":servicerewards")) {
        MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDS_APPLICATION");
      } else if (str3.equalsIgnoreCase(at.getPackageName() + ":servicerewardsprot")) {
        MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDSPROT_APPLICATION");
      }
    }
  }
  
  private void V()
  {
    e.a(at).a();
    MobclickAgent.onEvent(at, "ALTAMOB_IN_SUCCESS", "pkname:" + A);
    if (c.W(at).booleanValue()) {
      Toast.makeText(at, "=======Alt---Init=======", 1).show();
    }
  }
  
  private void W()
  {
    DuAdNetwork.init(this, y());
  }
  
  private void X()
  {
    MobileAds.initialize(this, com.xvideostudio.videoeditor.a.a.a().c());
  }
  
  private void Y()
  {
    Object localObject = new File(i());
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = com.xvideostudio.videoeditor.i.c.o();
    try
    {
      j((String)localObject);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private void Z()
  {
    int i1 = Integer.valueOf(f.g()).intValue();
    i.b("VideoEditorApplication", "the cpu frequency is " + i1 + "khz");
    int i2 = f.o();
    i.b("VideoEditorApplication", "cpuCoreNums is " + i2);
    if (i2 < 2) {
      hl.productor.fxlib.b.D = false;
    }
    for (;;)
    {
      if (!hl.productor.fxlib.b.D) {
        x.n(at, 1);
      }
      if (true == hl.productor.fxlib.b.D) {
        if (HLRenderThread.detectGraphicsPerformance() == -1)
        {
          hl.productor.fxlib.b.D = false;
          x.n(at, 1);
        }
      }
      switch (HLRenderThread.queryValue(3) / 8)
      {
      case 3: 
      default: 
        return;
        if ((i2 == 2) && (i1 <= 1000000)) {
          hl.productor.fxlib.b.D = false;
        } else {
          hl.productor.fxlib.b.D = true;
        }
        break;
      }
    }
    hl.productor.fxlib.e.c = 1;
    i.b("VideoEditorApplication", "detectGraphicsPerformance RGB565");
    x.n(at, 2);
    return;
    hl.productor.fxlib.e.c = 2;
    i.b("VideoEditorApplication", "detectGraphicsPerformance RGBA8888");
    x.n(at, 3);
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (b > 0) {
        return b;
      }
    }
    else if (c > 0) {
      return c;
    }
    paramContext = paramContext.getResources().getDisplayMetrics();
    b = paramContext.widthPixels;
    c = paramContext.heightPixels;
    d = paramContext.density;
    i.b("cxs", "width" + paramContext.widthPixels);
    i.b("cxs", "height" + paramContext.heightPixels);
    if (b > c)
    {
      int i1 = c;
      c = b;
      b = i1;
    }
    if (paramBoolean) {
      return b;
    }
    return c;
  }
  
  public static VideoEditorApplication a()
  {
    if (as == null) {
      as = new VideoEditorApplication();
    }
    return as;
  }
  
  public static String a(int paramInt)
  {
    if (E == null)
    {
      E = new HashMap(100);
      T();
    }
    Iterator localIterator = E.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (((Integer)localEntry.getValue()).intValue() == paramInt) {
        return localEntry.getKey().toString();
      }
    }
    return null;
  }
  
  public static String a(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.pid == paramInt) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static void a(FontScanCallBack paramFontScanCallBack)
  {
    if (x == null) {
      x = new LinkedHashMap();
    }
    if (paramFontScanCallBack != null)
    {
      FontCenter.getInstance().getLocalFontList(paramFontScanCallBack);
      return;
    }
    FontCenter.getInstance().getLocalFontList(new FontScanCallBack()
    {
      public void onFailure(String paramAnonymousString)
      {
        i.a("", paramAnonymousString);
      }
      
      public void onSuccess(List<Font> paramAnonymousList)
      {
        int i = 0;
        for (;;)
        {
          if (i < paramAnonymousList.size())
          {
            String str1 = t.a(((Font)paramAnonymousList.get(i)).getFontLocalPath());
            String str2;
            if (!VideoEditorApplication.x.containsKey(str1)) {
              str2 = ((Font)paramAnonymousList.get(i)).getFontName();
            }
            try
            {
              ((Font)paramAnonymousList.get(i)).getTypeface(new VideoEditorApplication.4.1(this, str1, str2));
              i += 1;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                VideoEditorApplication.x.put(str1, new MyFontEntity(Typeface.DEFAULT, str2));
                localException.printStackTrace();
              }
            }
          }
        }
      }
    });
  }
  
  private void a(Closeable paramCloseable)
  {
    if (paramCloseable == null) {
      return;
    }
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static void a(boolean paramBoolean)
  {
    int i1 = 0;
    if (paramBoolean) {
      i1 = 1;
    }
    x.r(at, i1);
  }
  
  @SuppressLint({"NewApi"})
  public static boolean a(Activity paramActivity)
  {
    if (paramActivity == null) {
      return true;
    }
    try
    {
      boolean bool = paramActivity.isDestroyed();
      return bool;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      localNoSuchMethodError.printStackTrace();
      return paramActivity.isFinishing();
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return true;
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext == null) {}
    label51:
    label54:
    for (;;)
    {
      return false;
      if ((Tools.b(paramContext)) || (f()) || (e()))
      {
        i1 = 1;
        if ((i1 == 0) || (!f.n(paramContext))) {
          break label51;
        }
      }
      for (int i1 = 1;; i1 = 0)
      {
        if (i1 == 0) {
          break label54;
        }
        return true;
        i1 = 0;
        break;
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    i.b("VideoEditorApplication", "checkApkExist packageName:" + paramString);
    boolean bool1 = bool2;
    if (paramString != null)
    {
      if (!"".equals(paramString)) {
        break label73;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      i.b("VideoEditorApplication", "checkApkExist ret:" + bool1);
      return bool1;
      try
      {
        label73:
        paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
        bool1 = true;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        bool1 = bool2;
      }
    }
  }
  
  public static boolean a(String paramString)
  {
    if ((A == null) && (a() != null) && (a().getBaseContext() != null)) {
      A = a().getBaseContext().getPackageName();
    }
    return (!TextUtils.isEmpty(A)) && (A.equalsIgnoreCase(paramString));
  }
  
  public static Typeface b(String paramString)
  {
    if (com.xvideostudio.videoeditor.util.w.a(paramString))
    {
      if (ay == null) {
        l();
      }
      if (ay.containsKey(paramString)) {
        return (Typeface)ay.get(paramString);
      }
    }
    else
    {
      if (x == null) {
        a(null);
      }
      if (x.containsKey(paramString)) {
        return ((MyFontEntity)x.get(paramString)).typeface;
      }
    }
    return Typeface.SANS_SERIF;
  }
  
  public static void b(Activity paramActivity)
  {
    if (!I.containsKey("MainActivity"))
    {
      Intent localIntent = new Intent();
      localIntent.setClass(paramActivity, MainActivity.class);
      paramActivity.startActivity(localIntent);
    }
    paramActivity.finish();
  }
  
  public static void b(Context paramContext, String paramString)
  {
    int i3 = 0;
    int i2 = 0;
    int i1 = i3;
    try
    {
      Intent localIntent = new Intent();
      i1 = i3;
      localIntent.setAction("android.intent.action.VIEW");
      i1 = i3;
      if (n())
      {
        i1 = 1;
        i2 = 1;
        localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
      }
      i1 = i2;
      localIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.xvideostudio.videoeditorpro&referrer=" + paramString));
      i1 = i2;
      paramContext.startActivity(localIntent);
      if (i2 != 0)
      {
        i1 = i2;
        MobclickAgent.onEvent(paramContext, "BUY_PRO_GO_TO_GP_SUCCESSFUL");
        return;
      }
      i1 = i2;
      MobclickAgent.onEvent(paramContext, "BUY_PRO_GO_TO_BROWSER_SUCCESSFUL");
      return;
    }
    catch (Exception paramString)
    {
      if (i1 == 0) {
        break label134;
      }
    }
    MobclickAgent.onEvent(paramContext, "BUY_PRO_GO_TO_GP_FAILED");
    for (;;)
    {
      paramString.printStackTrace();
      return;
      label134:
      MobclickAgent.onEvent(paramContext, "BUY_PRO_GO_TO_BROWSER_FAILED");
    }
  }
  
  public static boolean b()
  {
    if (a("com.xvideostudio.videoeditorpro"))
    {
      l = true;
      return true;
    }
    return false;
  }
  
  public static String c(String paramString)
  {
    String str = null;
    if (x == null) {
      a(null);
    }
    if (x.containsKey(paramString)) {
      str = ((MyFontEntity)x.get(paramString)).fontName;
    }
    return str;
  }
  
  public static void c(Context paramContext, String paramString)
  {
    if (paramContext == null) {}
  }
  
  public static boolean c()
  {
    return !Tools.b(a());
  }
  
  public static int d(String paramString)
  {
    if (E == null)
    {
      E = new HashMap(100);
      T();
    }
    if ((E != null) && (E.containsKey(paramString))) {
      return ((Integer)E.get(paramString)).intValue();
    }
    return 0;
  }
  
  public static boolean d()
  {
    if ((!c()) || (Build.VERSION.SDK_INT < 9)) {}
    while (((g()) && (x.z(a()))) || ((!f()) && (!g()) && (!e()))) {
      return false;
    }
    return true;
  }
  
  private void e(int paramInt)
  {
    az = new ArrayList();
    int i1 = 0;
    while (i1 < 20)
    {
      com.xvideostudio.videoeditor.d.w localW = new com.xvideostudio.videoeditor.d.w();
      localW.a = b.d[i1];
      localW.b = b.e[i1];
      localW.c = false;
      localW.d = (i1 + 4);
      localW.e = b.f[i1];
      localW.f = b.g[i1];
      localW.g = b.h[i1];
      if (paramInt == localW.d)
      {
        localW.c = true;
        hl.productor.fxlib.b.Y = false;
        hl.productor.fxlib.b.N = paramInt;
      }
      az.add(localW);
      i1 += 1;
    }
  }
  
  public static boolean e()
  {
    return a("com.xvideostudio.videoeditorlite");
  }
  
  public static boolean e(String paramString)
  {
    if (at == null) {
      return false;
    }
    Iterator localIterator = at.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean f()
  {
    return a("com.xvideostudio.videoeditorprofree");
  }
  
  public static boolean g()
  {
    return a("com.funcamerastudio.videomaker");
  }
  
  /* Error */
  private String h(String paramString)
  {
    // Byte code:
    //   0: new 1090	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 1091	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 5
    //   9: new 1093	java/io/BufferedInputStream
    //   12: dup
    //   13: getstatic 365	com/xvideostudio/videoeditor/VideoEditorApplication:at	Landroid/content/Context;
    //   16: invokevirtual 1097	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   19: aload_1
    //   20: invokevirtual 1103	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   23: invokespecial 1106	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   26: astore_3
    //   27: aload_3
    //   28: astore_1
    //   29: sipush 4096
    //   32: newarray byte
    //   34: astore 4
    //   36: aload_3
    //   37: astore_1
    //   38: aload_3
    //   39: aload 4
    //   41: invokevirtual 1110	java/io/BufferedInputStream:read	([B)I
    //   44: istore_2
    //   45: iload_2
    //   46: ifle +62 -> 108
    //   49: aload_3
    //   50: astore_1
    //   51: aload 5
    //   53: aload 4
    //   55: iconst_0
    //   56: iload_2
    //   57: invokevirtual 1114	java/io/ByteArrayOutputStream:write	([BII)V
    //   60: goto -24 -> 36
    //   63: astore 4
    //   65: aload_3
    //   66: astore_1
    //   67: ldc_w 717
    //   70: new 613	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 614	java/lang/StringBuilder:<init>	()V
    //   77: ldc_w 1116
    //   80: invokevirtual 618	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: aload 4
    //   85: invokevirtual 1119	java/io/IOException:getMessage	()Ljava/lang/String;
    //   88: invokevirtual 618	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: invokevirtual 623	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   94: invokestatic 1121	com/xvideostudio/videoeditor/tool/i:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   97: aload_0
    //   98: aload_3
    //   99: invokespecial 1123	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   102: aload 5
    //   104: invokevirtual 1124	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   107: areturn
    //   108: aload_0
    //   109: aload_3
    //   110: invokespecial 1123	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   113: goto -11 -> 102
    //   116: astore_3
    //   117: aconst_null
    //   118: astore_1
    //   119: aload_0
    //   120: aload_1
    //   121: invokespecial 1123	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   124: aload_3
    //   125: athrow
    //   126: astore_3
    //   127: goto -8 -> 119
    //   130: astore 4
    //   132: aconst_null
    //   133: astore_3
    //   134: goto -69 -> 65
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	137	0	this	VideoEditorApplication
    //   0	137	1	paramString	String
    //   44	13	2	i1	int
    //   26	84	3	localBufferedInputStream	BufferedInputStream
    //   116	9	3	localObject1	Object
    //   126	1	3	localObject2	Object
    //   133	1	3	localObject3	Object
    //   34	20	4	arrayOfByte	byte[]
    //   63	21	4	localIOException1	IOException
    //   130	1	4	localIOException2	IOException
    //   7	96	5	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   29	36	63	java/io/IOException
    //   38	45	63	java/io/IOException
    //   51	60	63	java/io/IOException
    //   9	27	116	finally
    //   29	36	126	finally
    //   38	45	126	finally
    //   51	60	126	finally
    //   67	97	126	finally
    //   9	27	130	java/io/IOException
  }
  
  public static boolean h()
  {
    if (!k) {}
    while (x.s(at, 0) == 0) {
      return false;
    }
    return true;
  }
  
  public static String i()
  {
    return av;
  }
  
  private void i(String paramString)
  {
    MntLib.init(getApplicationContext(), paramString);
  }
  
  private void j(String paramString)
    throws IOException
  {
    paramString = new BufferedOutputStream(new FileOutputStream(paramString));
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(getResources().openRawResource(2131623957));
    byte[] arrayOfByte = new byte[32768];
    for (;;)
    {
      int i1 = localBufferedInputStream.read(arrayOfByte);
      if (i1 <= 0) {
        break;
      }
      paramString.write(arrayOfByte, 0, i1);
    }
    paramString.flush();
    paramString.close();
  }
  
  public static boolean j()
  {
    return !l.a(at, "UMENG_CHANNEL", "GOOGLEPLAY").equalsIgnoreCase("SAMSUNG");
  }
  
  public static SharedPreferences k()
  {
    return PreferenceManager.getDefaultSharedPreferences(as);
  }
  
  public static Map<String, Typeface> l()
  {
    if ((ay == null) || (ay.size() == 0))
    {
      ay = new LinkedHashMap();
      ai.a("VideoEditorApplication onCreate before new typeFace:");
      String[] arrayOfString = new String[9];
      arrayOfString[0] = "";
      arrayOfString[1] = "cyrillic_font_lobster.ttf";
      arrayOfString[2] = "impact.ttf";
      arrayOfString[3] = "pointy.ttf";
      arrayOfString[4] = "helvetica_neue_lt_pro_bd.otf";
      arrayOfString[5] = "un-finished.ttf";
      arrayOfString[6] = "futura_medium_bt.ttf";
      arrayOfString[7] = "didot.ttf";
      arrayOfString[8] = "birth_of_a_hero.ttf";
      int i1 = arrayOfString.length - 1;
      if (i1 >= 0)
      {
        if (i1 == 0) {
          ay.put(i1 + "", Typeface.SANS_SERIF);
        }
        for (;;)
        {
          i1 -= 1;
          break;
          try
          {
            ay.put(i1 + "", Typeface.createFromAsset(at.getAssets(), "font/" + arrayOfString[i1]));
          }
          catch (Exception localException)
          {
            ay.put(i1 + "", Typeface.SANS_SERIF);
            localException.printStackTrace();
          }
        }
      }
      ay.put("9", Typeface.createFromAsset(at.getAssets(), "font/helvetica_inserat_lt.ttf"));
      ai.a("VideoEditorApplication onCreate after typeFace:");
    }
    return ay;
  }
  
  public static Map<String, Map<String, String>> m()
  {
    int i1 = 0;
    if ((aA == null) || (aA.size() == 0))
    {
      aA = new LinkedHashMap();
      new HashMap();
      Object localObject = new String[1];
      localObject[0] = at.getResources().getString(2131690171);
      String[] arrayOfString = new String[localObject.length];
      System.arraycopy(localObject, 0, arrayOfString, 0, localObject.length);
      while (i1 < arrayOfString.length)
      {
        localObject = new HashMap();
        ((Map)localObject).put("songId", 100000 + i1 + "");
        ((Map)localObject).put("isShow", "1");
        ((Map)localObject).put("fileName", new String[] { "music_new_york_love.aac" }[i1]);
        ((Map)localObject).put("lang", "en");
        ((Map)localObject).put("artist", "artist");
        ((Map)localObject).put("rawId", new int[] { 2131623947 }[i1] + "");
        ((Map)localObject).put("duration", new String[] { "64608" }[i1]);
        ((Map)localObject).put("musicName", arrayOfString[i1]);
        aA.put(new String[] { "music_new_york_love.aac" }[i1], localObject);
        i1 += 1;
      }
    }
    return aA;
  }
  
  public static boolean n()
  {
    boolean bool = false;
    if (aB != null) {
      bool = aB.booleanValue();
    }
    while (at == null) {
      return bool;
    }
    Object localObject = at.getPackageManager().getInstalledPackages(0);
    aB = Boolean.FALSE;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equalsIgnoreCase("com.android.vending")) {
        aB = Boolean.TRUE;
      }
    }
    return aB.booleanValue();
  }
  
  public static String o()
  {
    if (s == null) {
      p();
    }
    return s;
  }
  
  public static void p()
  {
    if (aC) {
      return;
    }
    aC = true;
    q = r + ".com/store/";
    s = q + "apps/details?id=";
    v = s + "com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshow%2520setting%26utm_medium%3Dbanner%26utm_term%3Dvideoshow%2520pro%26utm_content%3Dvideoshow%2520pro%2520for%2520setting%26utm_campaign%3Dvideoshow%2520pro%2520for%2520setting";
    u = s + "com.xvideostudio.videoeditorpro";
    t = s + "com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshowlabs%26utm_medium%3Dbanner%26utm_term%3Dlabs%26utm_content%3Dvideoshow%2520for%2520labs%26utm_campaign%3Dvideoshow%2520for%2520labs";
    if ((A != null) && (A.length() > 0))
    {
      s += A;
      return;
    }
    s += "com.funcamerastudio.videomaker";
  }
  
  public static void q()
  {
    if (aD) {}
    do
    {
      return;
      aD = true;
    } while ((!g()) || (x.A(at) != 1));
    B = true;
  }
  
  /* Error */
  public static boolean r()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 1289	java/lang/System:currentTimeMillis	()J
    //   6: lstore_0
    //   7: getstatic 304	com/xvideostudio/videoeditor/VideoEditorApplication:aE	J
    //   10: lstore_2
    //   11: lload_0
    //   12: lload_2
    //   13: lsub
    //   14: ldc2_w 1290
    //   17: lcmp
    //   18: ifge +12 -> 30
    //   21: iconst_1
    //   22: istore 4
    //   24: ldc 2
    //   26: monitorexit
    //   27: iload 4
    //   29: ireturn
    //   30: lload_0
    //   31: putstatic 304	com/xvideostudio/videoeditor/VideoEditorApplication:aE	J
    //   34: iconst_0
    //   35: istore 4
    //   37: goto -13 -> 24
    //   40: astore 5
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload 5
    //   47: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	25	0	l1	long
    //   10	3	2	l2	long
    //   22	14	4	bool	boolean
    //   40	6	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	11	40	finally
    //   30	34	40	finally
  }
  
  /* Error */
  public static boolean s()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 1289	java/lang/System:currentTimeMillis	()J
    //   6: lstore_0
    //   7: getstatic 304	com/xvideostudio/videoeditor/VideoEditorApplication:aE	J
    //   10: lstore_2
    //   11: lload_0
    //   12: lload_2
    //   13: lsub
    //   14: ldc2_w 1292
    //   17: lcmp
    //   18: ifge +12 -> 30
    //   21: iconst_1
    //   22: istore 4
    //   24: ldc 2
    //   26: monitorexit
    //   27: iload 4
    //   29: ireturn
    //   30: lload_0
    //   31: putstatic 304	com/xvideostudio/videoeditor/VideoEditorApplication:aE	J
    //   34: iconst_0
    //   35: istore 4
    //   37: goto -13 -> 24
    //   40: astore 5
    //   42: ldc 2
    //   44: monitorexit
    //   45: aload 5
    //   47: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	25	0	l1	long
    //   10	3	2	l2	long
    //   22	14	4	bool	boolean
    //   40	6	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	11	40	finally
    //   30	34	40	finally
  }
  
  public static void t()
  {
    if ((!a) || (c.aj(at).booleanValue())) {}
    while (I2WAPI.hasReadyAd(at, "WAITING_PAGE_NATIVE")) {
      return;
    }
    I2WAPI.preload(at, "WAITING_PAGE_NATIVE");
  }
  
  public void A()
  {
    if (this.aO) {
      return;
    }
    this.aO = true;
    try
    {
      FontCenter.getInstance().setFolder_font(com.xvideostudio.videoeditor.i.c.K());
      new Thread(new Runnable()
      {
        public void run()
        {
          if ((VideoEditorApplication.a(VideoEditorApplication.this)) && (com.xvideostudio.videoeditor.i.c.b(VideoEditorApplication.a()))) {
            c.i(VideoEditorApplication.S(), Boolean.valueOf(true));
          }
          if (!c.F(VideoEditorApplication.S()).booleanValue()) {
            hl.productor.fxlib.b.au = 1;
          }
          am.a().a(VideoEditorApplication.S());
          VideoEditorApplication.this.D();
          VideoEditorApplication.b(VideoEditorApplication.this).sendEmptyMessageDelayed(0, 10L);
          int i;
          if (x.M(VideoEditorApplication.a()))
          {
            MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_START_APP");
            i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_START_APP");
            if (!x.L(VideoEditorApplication.S()))
            {
              i.b("VideoEditorApplication", "HW_ENCODER_ERR errTime:" + x.N(VideoEditorApplication.a()) + " errResetTime:" + x.O(VideoEditorApplication.a()));
              if (x.O(VideoEditorApplication.a()) < 3) {
                if (x.N(VideoEditorApplication.a()) % 5 == 0)
                {
                  x.q(VideoEditorApplication.S(), false);
                  x.G(VideoEditorApplication.a(), 0);
                  if ((f.d() >= 18) && (VideoEditorApplication.this.K()))
                  {
                    hl.productor.fxlib.b.w = true;
                    hl.productor.fxlib.b.z = true;
                  }
                  x.F(VideoEditorApplication.a(), x.O(VideoEditorApplication.a()) + 1);
                  i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                  MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                  i.b("", "FxConfig.video_hw_encode_enable = " + hl.productor.fxlib.b.w);
                  VideoEditorApplication.this.L();
                  i = VideoEditorApplication.b;
                  int j = VideoEditorApplication.c;
                  i.b("", "screenWidth = " + i + "screenHeight = " + j);
                  if (i * j < 921600) {
                    hl.productor.fxlib.v.n = 0;
                  }
                  if (Math.min(hl.productor.fxlib.b.f, hl.productor.fxlib.b.e) >= 720) {
                    VideoEditorApplication.this.J();
                  }
                  if (hl.productor.fxlib.b.D != true) {
                    break label554;
                  }
                  VideoEditorApplication.this.M();
                  label367:
                  if (hl.productor.fxlib.b.E)
                  {
                    Context localContext = VideoEditorApplication.S();
                    if (!hl.productor.fxlib.b.D) {
                      break label561;
                    }
                    i = 0;
                    label385:
                    i = x.m(localContext, i);
                    if ((i != 0) || (hl.productor.fxlib.b.D)) {
                      break label566;
                    }
                    x.l(VideoEditorApplication.S(), 1);
                  }
                }
              }
            }
          }
          label554:
          label561:
          label566:
          while ((i != 1) || (!hl.productor.fxlib.b.D))
          {
            return;
            x.G(VideoEditorApplication.a(), x.N(VideoEditorApplication.a()) + 1);
            break;
            MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
            i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
            break;
            i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
            MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
            x.q(VideoEditorApplication.S(), false);
            x.G(VideoEditorApplication.a(), 0);
            if ((f.d() < 18) || (!VideoEditorApplication.this.K())) {
              break;
            }
            hl.productor.fxlib.b.w = true;
            hl.productor.fxlib.b.z = true;
            break;
            if (f.d() >= 18)
            {
              if (VideoEditorApplication.this.K())
              {
                hl.productor.fxlib.b.w = true;
                hl.productor.fxlib.b.z = true;
              }
              MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_OS_UPTO_18");
              break;
            }
            MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_OS_BELOW_18");
            break;
            hl.productor.fxlib.b.E = false;
            break label367;
            i = 1;
            break label385;
          }
          x.l(VideoEditorApplication.S(), 0);
        }
      }).start();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void B()
  {
    p();
    ai.a("VideoEditorApplication onCreate after:");
    T();
    C();
    if (!l.a(com.xvideostudio.videoeditor.i.d.f(com.xvideostudio.videoeditor.i.d.f(1), 6))) {
      x.a(at, false, f.d(at));
    }
    for (;;)
    {
      a(true, true, true, true, true);
      int i1 = x.u(a(), 3);
      if (i1 == 1)
      {
        hl.productor.fxlib.b.Y = false;
        hl.productor.fxlib.b.N = 1;
        label75:
        e(i1);
      }
      try
      {
        l();
        String str = com.xvideostudio.videoeditor.i.c.f() + "1.png";
        if (!l.a(str)) {
          l.a(at, 2131623960, str);
        }
        if (g())
        {
          i1 = x.A(at);
          if (i1 == 1) {
            B = true;
          }
        }
        else
        {
          return;
          if (!new File(com.xvideostudio.videoeditor.i.c.F()).isDirectory())
          {
            x.a(at, false, f.d(at));
            continue;
          }
          if (new File(com.xvideostudio.videoeditor.i.c.H()).isDirectory()) {
            continue;
          }
          x.a(at, false, f.d(at));
          continue;
          if (i1 == 2)
          {
            hl.productor.fxlib.b.Y = false;
            hl.productor.fxlib.b.N = 2;
            break label75;
          }
          if (i1 != 3) {
            break label75;
          }
          hl.productor.fxlib.b.Y = true;
          hl.productor.fxlib.b.N = 3;
        }
      }
      catch (Exception localException)
      {
        do
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        } while (i1 != 2);
        b(1);
      }
    }
  }
  
  /* Error */
  public void C()
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_1
    //   2: new 613	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 614	java/lang/StringBuilder:<init>	()V
    //   9: invokestatic 1354	com/xvideostudio/videoeditor/i/c:I	()Ljava/lang/String;
    //   12: invokevirtual 618	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   15: ldc_w 1356
    //   18: invokevirtual 618	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: invokevirtual 623	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   24: astore_2
    //   25: new 686	java/io/File
    //   28: dup
    //   29: aload_2
    //   30: invokespecial 690	java/io/File:<init>	(Ljava/lang/String;)V
    //   33: invokevirtual 693	java/io/File:exists	()Z
    //   36: ifne +157 -> 193
    //   39: getstatic 365	com/xvideostudio/videoeditor/VideoEditorApplication:at	Landroid/content/Context;
    //   42: invokestatic 1359	com/xvideostudio/videoeditor/i/c:a	(Landroid/content/Context;)Ljava/io/File;
    //   45: invokevirtual 1362	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   48: aload_2
    //   49: invokestatic 1365	com/xvideostudio/videoeditor/util/l:b	(Ljava/lang/String;Ljava/lang/String;)Z
    //   52: ifne +118 -> 170
    //   55: new 1367	com/xvideostudio/videoeditor/c/m
    //   58: dup
    //   59: getstatic 365	com/xvideostudio/videoeditor/VideoEditorApplication:at	Landroid/content/Context;
    //   62: invokespecial 1370	com/xvideostudio/videoeditor/c/m:<init>	(Landroid/content/Context;)V
    //   65: astore_2
    //   66: aload_2
    //   67: aload_2
    //   68: invokevirtual 1373	com/xvideostudio/videoeditor/c/m:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   71: invokevirtual 1376	com/xvideostudio/videoeditor/c/m:a	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   74: bipush 18
    //   76: istore_1
    //   77: goto +146 -> 223
    //   80: new 1367	com/xvideostudio/videoeditor/c/m
    //   83: dup
    //   84: getstatic 365	com/xvideostudio/videoeditor/VideoEditorApplication:at	Landroid/content/Context;
    //   87: invokespecial 1370	com/xvideostudio/videoeditor/c/m:<init>	(Landroid/content/Context;)V
    //   90: astore_2
    //   91: iload_1
    //   92: bipush 15
    //   94: if_icmplt +69 -> 163
    //   97: aload_2
    //   98: invokevirtual 1373	com/xvideostudio/videoeditor/c/m:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   101: astore_3
    //   102: aload_2
    //   103: aload_3
    //   104: ldc_w 1378
    //   107: ldc_w 1380
    //   110: invokevirtual 1383	com/xvideostudio/videoeditor/c/m:a	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Z
    //   113: ifne +8 -> 121
    //   116: aload_2
    //   117: aload_3
    //   118: invokevirtual 1385	com/xvideostudio/videoeditor/c/m:e	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   121: aload_2
    //   122: aload_3
    //   123: ldc_w 1378
    //   126: ldc_w 1387
    //   129: invokevirtual 1383	com/xvideostudio/videoeditor/c/m:a	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Z
    //   132: ifne +8 -> 140
    //   135: aload_2
    //   136: aload_3
    //   137: invokevirtual 1389	com/xvideostudio/videoeditor/c/m:p	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   140: aload_2
    //   141: aload_3
    //   142: ldc_w 1378
    //   145: ldc_w 1391
    //   148: invokevirtual 1383	com/xvideostudio/videoeditor/c/m:a	(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/lang/String;)Z
    //   151: ifne +8 -> 159
    //   154: aload_2
    //   155: aload_3
    //   156: invokevirtual 1393	com/xvideostudio/videoeditor/c/m:q	(Landroid/database/sqlite/SQLiteDatabase;)V
    //   159: aload_3
    //   160: invokevirtual 1396	android/database/sqlite/SQLiteDatabase:close	()V
    //   163: iload_1
    //   164: bipush 18
    //   166: if_icmplt +45 -> 211
    //   169: return
    //   170: getstatic 1398	com/xvideostudio/videoeditor/c/m:a	Ljava/lang/String;
    //   173: iconst_1
    //   174: invokestatic 1401	com/xvideostudio/videoeditor/util/l:a	(Ljava/lang/String;I)Z
    //   177: pop
    //   178: goto +45 -> 223
    //   181: astore_2
    //   182: ldc_w 717
    //   185: aload_2
    //   186: invokevirtual 1402	java/lang/Exception:toString	()Ljava/lang/String;
    //   189: invokestatic 1404	com/xvideostudio/videoeditor/tool/i:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   192: return
    //   193: getstatic 1398	com/xvideostudio/videoeditor/c/m:a	Ljava/lang/String;
    //   196: invokestatic 1406	com/xvideostudio/videoeditor/util/l:o	(Ljava/lang/String;)I
    //   199: istore_1
    //   200: goto -120 -> 80
    //   203: astore_3
    //   204: aload_3
    //   205: invokevirtual 888	java/lang/Exception:printStackTrace	()V
    //   208: goto -45 -> 163
    //   211: aload_2
    //   212: aload_2
    //   213: invokevirtual 1373	com/xvideostudio/videoeditor/c/m:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   216: iload_1
    //   217: bipush 18
    //   219: invokevirtual 1409	com/xvideostudio/videoeditor/c/m:a	(Landroid/database/sqlite/SQLiteDatabase;II)V
    //   222: return
    //   223: goto -143 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	226	0	this	VideoEditorApplication
    //   1	216	1	i1	int
    //   24	131	2	localObject	Object
    //   181	32	2	localException1	Exception
    //   101	59	3	localSQLiteDatabase	android.database.sqlite.SQLiteDatabase
    //   203	2	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   2	74	181	java/lang/Exception
    //   80	91	181	java/lang/Exception
    //   170	178	181	java/lang/Exception
    //   193	200	181	java/lang/Exception
    //   204	208	181	java/lang/Exception
    //   211	222	181	java/lang/Exception
    //   97	121	203	java/lang/Exception
    //   121	140	203	java/lang/Exception
    //   140	159	203	java/lang/Exception
    //   159	163	203	java/lang/Exception
  }
  
  public void D()
  {
    b();
    E();
  }
  
  public void E()
  {
    Object localObject = "";
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject = Environment.getExternalStorageDirectory().getAbsolutePath();
      i.b("cxs", "Sd1 path:" + (String)localObject);
    }
    String str = com.xvideostudio.videoeditor.i.c.a();
    if ((str != null) && (!((String)localObject).equalsIgnoreCase(str)) && (!str.startsWith("/storage/emulated/legacy")))
    {
      i.b("cxs", "Sd2 path:" + str);
      ax = str + File.separator + com.xvideostudio.videoeditor.i.c.f;
      l.b(ax);
      k = true;
    }
    try
    {
      localObject = new File(ax + ak.a() + ".test");
      ((File)localObject).createNewFile();
      ((File)localObject).delete();
      au = com.xvideostudio.videoeditor.i.c.t();
      av = com.xvideostudio.videoeditor.i.c.e();
      aw = com.xvideostudio.videoeditor.i.c.d();
      if ((!k) && (h() == true)) {
        a(false);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        k = false;
        localException.printStackTrace();
      }
    }
  }
  
  public DraftBoxHandler F()
  {
    if (this.aG == null) {
      this.aG = new DraftBoxHandler();
    }
    return this.aG;
  }
  
  public org.xvideo.videoeditor.a.b G()
  {
    if (this.aH == null) {
      this.aH = new org.xvideo.videoeditor.a.b(getApplicationContext());
    }
    return this.aH;
  }
  
  public org.xvideo.videoeditor.b.b H()
  {
    if (this.aI == null) {
      this.aI = new org.xvideo.videoeditor.b.b(getApplicationContext());
    }
    return this.aI;
  }
  
  public void I()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    this.aQ.sendMessage(localMessage);
  }
  
  public void J()
  {
    int i2 = -1;
    int i4 = 1;
    for (;;)
    {
      try
      {
        Object localObject = x.C(at);
        if (localObject == null) {
          break label318;
        }
        i1 = ((String)localObject).indexOf(",");
        if (i1 <= -1) {
          break label318;
        }
        i1 = i2;
        try
        {
          localObject = ((String)localObject).split(",");
          i1 = i2;
          i2 = Integer.valueOf(localObject[0]).intValue();
          if (i2 >= 0)
          {
            if (i2 == 1)
            {
              bool = true;
              i1 = i2;
              hl.productor.fxlib.b.X = bool;
            }
          }
          else
          {
            i1 = i2;
            i3 = Integer.valueOf(localObject[1]).intValue();
            if (i3 <= 0) {}
          }
        }
        catch (Exception localException1)
        {
          boolean bool;
          i2 = 0;
        }
      }
      catch (Exception localException2)
      {
        String str1;
        String str2;
        localException2.printStackTrace();
        return;
      }
      try
      {
        hl.productor.fxlib.b.f = i3;
        return;
      }
      catch (Exception localException3)
      {
        i1 = i2;
        i2 = i3;
        continue;
        continue;
        continue;
      }
      bool = false;
    }
    localException1.printStackTrace();
    int i3 = i1;
    int i1 = i2;
    i2 = i3;
    str1 = com.xvideostudio.videoeditor.i.c.L();
    str2 = str1 + "export_720p_avc_test.mp4";
    l.a(at, str2, 2131623939);
    if (!f.a(str2))
    {
      i.b("VideoEditorApplication", "this device can not surport 720P AVC HighProfile export");
      hl.productor.fxlib.b.X = false;
      str1 = str1 + "export_720p_mpeg4_test.mp4";
      l.a(at, str1, 2131623940);
      if (!f.a(str1))
      {
        i.b("VideoEditorApplication", "this device can not surport 720P export");
        hl.productor.fxlib.b.f = 720;
        hl.productor.fxlib.b.f = 720;
        i1 = hl.productor.fxlib.b.f;
        if (hl.productor.fxlib.b.X)
        {
          i2 = i4;
          break label325;
        }
      }
    }
    label318:
    label325:
    for (;;)
    {
      x.f(at, i2 + "," + i1);
      return;
      i2 = 0;
      break label325;
      i2 = -1;
      i1 = 0;
      break;
    }
  }
  
  public boolean K()
  {
    int i7 = MediaCodecList.getCodecCount();
    int i3 = 0;
    int i2 = 0;
    int i6;
    for (int i1 = 0; i3 < i7; i1 = i6)
    {
      MediaCodecInfo localMediaCodecInfo = MediaCodecList.getCodecInfoAt(i3);
      int i5 = i2;
      i6 = i1;
      if (localMediaCodecInfo != null)
      {
        i5 = i2;
        i6 = i1;
        if (localMediaCodecInfo.isEncoder())
        {
          i.d("JNIMsg", "Codec: " + localMediaCodecInfo.getName());
          String[] arrayOfString = localMediaCodecInfo.getSupportedTypes();
          i5 = i2;
          i6 = i1;
          if (arrayOfString != null)
          {
            int i8 = arrayOfString.length;
            int i4 = 0;
            for (;;)
            {
              i5 = i2;
              i6 = i1;
              if (i4 >= i8) {
                break;
              }
              String str = arrayOfString[i4];
              i.d("JNIMsg", "mimes: " + str);
              i5 = i2;
              i6 = i1;
              if (str.equalsIgnoreCase("video/avc"))
              {
                str = localMediaCodecInfo.getName();
                i.b("JNIMsg", "AVC encoder is " + str);
                i2 += 1;
                i5 = i2;
                i6 = i1;
                if (str.equalsIgnoreCase("OMX.sprd.h264.encoder"))
                {
                  MobclickAgent.onEvent(at, "AVC_ENCODER_CHECK_OMX_SPRD_H264");
                  i6 = 1;
                  i5 = i2;
                }
              }
              i4 += 1;
              i2 = i5;
              i1 = i6;
            }
          }
        }
      }
      i3 += 1;
      i2 = i5;
    }
    if ((i2 > 0) && (i1 == 0))
    {
      MobclickAgent.onEvent(at, "AVC_ENCODER_CHECK_H264_HW_ENCODE_SUPPORT");
      return true;
    }
    MobclickAgent.onEvent(at, "AVC_ENCODER_CHECK_H264_HW_ENCODE_CANNOT_SUPPORT");
    return false;
  }
  
  public void L()
  {
    String str = f.m();
    i.b("VideoEditorApplication", "cpu Architecture is " + str);
    f.d();
    if ((str.equals("armeabi-v7a")) || (str.equals("arm64-v8a")))
    {
      i.b("VideoEditorApplication", "H264 encoding is enabled");
      hl.productor.fxlib.b.X = true;
      hl.productor.fxlib.b.H = 1;
      int i1 = Math.min(f.k(at), f.l(at));
      i.b("VideoEditorApplication", "screenWidth = " + i1);
      if (i1 > 1446)
      {
        hl.productor.fxlib.b.g = i1;
        hl.productor.fxlib.b.h = i1;
      }
      F = f.v();
      i.b("VideoEditorApplication", "cameraMaxSize[0] = " + F[0]);
      i.b("VideoEditorApplication", "cameraMaxSize[1] = " + F[1]);
      if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > f.k(at) * f.l(at))
      {
        hl.productor.fxlib.b.e = Math.min(f.k(at), f.l(at));
        hl.productor.fxlib.b.f = Math.max(f.k(at), f.l(at));
        i.b("VideoEditorApplication", "Max output size restricted to " + f.k(at) + " x " + f.l(at));
      }
      if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > F[0] * F[1])
      {
        hl.productor.fxlib.b.e = Math.min(F[0], F[1]);
        hl.productor.fxlib.b.f = Math.max(F[0], F[1]);
      }
      if (((f.k(at) * f.l(at) <= 384000) && (f.k(at) * f.l(at) == F[0] * F[1])) || (F[0] * F[1] < 384000))
      {
        i.b("VideoEditorApplication", "special machine , we need disable video_hw_encode_enable  ");
        hl.productor.fxlib.b.w = false;
        hl.productor.fxlib.b.z = false;
        MobclickAgent.onEvent(this, "EXPORT_FORCE_SET_HW_ENCODE_DISABLE");
      }
      if (f.o() < 4) {
        break label554;
      }
      i.b("VideoEditorApplication", "4-core   render_target_scale_mode_enabled can be false");
      hl.productor.fxlib.b.J = false;
      label459:
      if (F[2] != 1) {
        break label561;
      }
      if ((b * c <= 2073600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > F[0] * F[1]))
      {
        hl.productor.fxlib.b.ae = Math.min(F[0], F[1]);
        hl.productor.fxlib.b.af = Math.max(F[0], F[1]);
      }
      hl.productor.fxlib.b.ag = true;
    }
    for (;;)
    {
      hl.productor.fxlib.b.ad = 1;
      return;
      hl.productor.fxlib.b.X = false;
      hl.productor.fxlib.b.H = 0;
      break;
      label554:
      hl.productor.fxlib.b.J = true;
      break label459;
      label561:
      if ((b * c <= 921600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > F[0] * F[1]))
      {
        hl.productor.fxlib.b.ae = Math.min(F[0], F[1]);
        hl.productor.fxlib.b.af = Math.max(F[0], F[1]);
      }
    }
  }
  
  void M()
  {
    switch (x.o(at, 0))
    {
    default: 
      return;
    case 0: 
      Z();
      return;
    case 1: 
      hl.productor.fxlib.b.D = false;
      return;
    case 2: 
      hl.productor.fxlib.b.D = true;
      hl.productor.fxlib.e.c = 1;
      return;
    }
    hl.productor.fxlib.b.D = true;
    hl.productor.fxlib.e.c = 2;
  }
  
  public void N()
  {
    if ((i <= 0) || (j == null) || (j.equals(""))) {}
    try
    {
      PackageInfo localPackageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
      i = localPackageInfo.versionCode;
      j = localPackageInfo.versionName;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
  }
  
  public void O()
  {
    if (this.aS) {}
    do
    {
      return;
      this.aS = true;
    } while (!A.equals("com.xvideostudio.videoeditorlite"));
    as.a(at, true);
  }
  
  public List<com.xvideostudio.videoeditor.d.w> P()
  {
    if (az == null) {
      e(x.u(a(), 3));
    }
    return az;
  }
  
  public String Q()
  {
    if (TextUtils.isEmpty(this.al)) {
      this.al = "-1000";
    }
    return this.al;
  }
  
  public String R()
  {
    if (TextUtils.isEmpty(this.al)) {
      this.al = "-2000";
    }
    return this.am;
  }
  
  public void a(final int paramInt, final MediaClip paramMediaClip)
  {
    this.aQ.post(new Runnable()
    {
      public void run()
      {
        if (paramInt == 0)
        {
          if (VideoEditorApplication.c(VideoEditorApplication.this) != null)
          {
            paramMediaClip.rotation = 64537;
            VideoEditorApplication.c(VideoEditorApplication.this).b(paramMediaClip);
          }
          j.a(VideoEditorApplication.this.getResources().getString(2131690645), -1, 1);
        }
        while (VideoEditorApplication.c(VideoEditorApplication.this) == null) {
          return;
        }
        VideoEditorApplication.c(VideoEditorApplication.this).a(paramMediaClip);
      }
    });
  }
  
  public void a(Application paramApplication, final Context paramContext)
  {
    if (a) {
      paramApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
      {
        public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
        {
          i.d("enjoyMobiAd", "onActivityCreated");
          I2WAPI.init(paramContext, false, false);
        }
        
        public void onActivityDestroyed(Activity paramAnonymousActivity)
        {
          i.d("enjoyMobiAd", "onActivityDestroyed");
        }
        
        public void onActivityPaused(Activity paramAnonymousActivity)
        {
          i.d("enjoyMobiAd", "onActivityPaused");
          I2WAPI.onActivityPause(paramAnonymousActivity);
        }
        
        public void onActivityResumed(Activity paramAnonymousActivity)
        {
          i.d("enjoyMobiAd", "onActivityResumed");
          I2WAPI.onActivityResume(paramAnonymousActivity);
        }
        
        public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
        {
          i.d("enjoyMobiAd", "onActivitySaveInstanceState");
        }
        
        public void onActivityStarted(Activity paramAnonymousActivity)
        {
          i.d("enjoyMobiAd", "onActivityStarted");
        }
        
        public void onActivityStopped(Activity paramAnonymousActivity)
        {
          i.d("enjoyMobiAd", "onActivityStopped");
        }
      });
    }
  }
  
  public void a(StoryBoardView.a paramA)
  {
    this.aR = paramA;
  }
  
  public void a(String paramString, int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    Bundle localBundle = new Bundle();
    localBundle.putInt("duration", paramInt);
    localBundle.putString("text", paramString);
    localMessage.setData(localBundle);
    this.aQ.sendMessage(localMessage);
  }
  
  public void a(String paramString, ImageView paramImageView, com.b.a.b.c paramC)
  {
    com.b.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aK == null)
    {
      this.aK = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    }
    this.aK.a(paramString, paramImageView, localC);
  }
  
  public void a(String paramString, ImageView paramImageView, com.b.a.b.c paramC, com.b.a.b.f.c paramC1)
  {
    this.aK.a(paramString, paramImageView, paramC, paramC1);
  }
  
  public void a(String paramString, com.b.a.b.c paramC, com.b.a.b.f.a paramA)
  {
    com.b.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aK == null)
    {
      this.aK = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    }
    this.aK.a(paramString, localC, paramA);
  }
  
  public void a(final String paramString1, final boolean paramBoolean, final int paramInt, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        try
        {
          String str = l.a(l.e(paramString1), 1073741824L);
          org.xvideo.videoeditor.b.a localA = new org.xvideo.videoeditor.b.a();
          localA.filePath = paramString1;
          localA.fileSize = str;
          localA.videoName = paramString1.substring(paramString1.lastIndexOf("/") + 1);
          localA.showTime = System.currentTimeMillis();
          if (Tools.f(localA.videoName))
          {
            str = paramString2;
            localA.videoDuration = str;
            if (paramBoolean) {
              i = 1;
            }
            localA.isShowName = i;
            if (paramInt != 0) {
              break label155;
            }
          }
          label155:
          for (localA.newName = l.j(localA.videoName);; localA.newName = localA.videoName.substring(0, localA.videoName.lastIndexOf("(")))
          {
            localA.ordinal = paramInt;
            VideoEditorApplication.this.H().a(localA);
            return;
            str = SystemUtility.getTimeMinSecFormt(Tools.getVideoRealWidthHeight(paramString1)[3]);
            break;
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
  {
    Object localObject1;
    Object localObject2;
    try
    {
      if (!x.d(at, f.d(at)))
      {
        if ((paramBoolean1) && (paramBoolean2)) {
          Y();
        }
        if (paramBoolean1)
        {
          String str1 = com.xvideostudio.videoeditor.i.c.A();
          l.b(str1);
          localObject1 = m().keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localObject2 = (Map)m().get(localObject2);
            if ((localObject2 != null) && (((Map)localObject2).size() != 0))
            {
              Message localMessage = new Message();
              localMessage.what = 1;
              Bundle localBundle = new Bundle();
              localBundle.putInt("rawId", Integer.valueOf((String)((Map)localObject2).get("rawId")).intValue());
              localBundle.putString("rawFilePath", str1 + (String)((Map)localObject2).get("fileName"));
              localMessage.setData(localBundle);
              this.aQ.sendMessage(localMessage);
            }
          }
        }
      }
      else
      {
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    String str2;
    if (paramBoolean2)
    {
      str2 = com.xvideostudio.videoeditor.i.c.C();
      l.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131623958);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "theme_new_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.aQ.sendMessage((Message)localObject1);
    }
    if (paramBoolean3)
    {
      str2 = com.xvideostudio.videoeditor.i.c.E();
      l.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131623959);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "trans_new_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.aQ.sendMessage((Message)localObject1);
    }
    if (paramBoolean4)
    {
      str2 = com.xvideostudio.videoeditor.i.c.F();
      l.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131623956);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "subtitle_style_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.aQ.sendMessage((Message)localObject1);
    }
    if (paramBoolean5)
    {
      str2 = com.xvideostudio.videoeditor.i.c.H();
      l.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131623942);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "fx_sound_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.aQ.sendMessage((Message)localObject1);
    }
    x.a(at, true, f.d(at));
  }
  
  public void b(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          com.xvideostudio.videoeditor.control.b.a(MainActivity.a(VideoEditorApplication.S()), 2, new VideoEditorApplication.9.1(this));
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public void b(String paramString, ImageView paramImageView, com.b.a.b.c paramC)
  {
    if (this.aK == null)
    {
      this.aK = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    }
    this.aK.a("file://" + paramString, paramImageView, paramC);
  }
  
  public void c(int paramInt)
  {
    int i1 = 0;
    com.xvideostudio.videoeditor.d.w localW;
    if (i1 < 20)
    {
      localW = (com.xvideostudio.videoeditor.d.w)P().get(i1);
      if (paramInt < 4)
      {
        if (!localW.c) {
          break label64;
        }
        localW.c = false;
        az.set(i1, localW);
      }
    }
    else
    {
      return;
    }
    if (paramInt == localW.d) {}
    for (localW.c = true;; localW.c = false)
    {
      label64:
      az.set(i1, localW);
      i1 += 1;
      break;
    }
  }
  
  public com.xvideostudio.videoeditor.d.w d(int paramInt)
  {
    int i1 = 0;
    while (i1 < 20)
    {
      com.xvideostudio.videoeditor.d.w localW = (com.xvideostudio.videoeditor.d.w)az.get(i1);
      if ((paramInt >= 4) && (paramInt == localW.d)) {
        return localW;
      }
      i1 += 1;
    }
    return null;
  }
  
  public void d(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, MarketUrlRedirectActivity.class);
    localIntent.putExtra("OriginalUrl", paramString);
    if (!(paramContext instanceof Activity)) {
      localIntent.addFlags(268435456);
    }
    paramContext.startActivity(localIntent);
  }
  
  public void f(String paramString)
  {
    this.al = paramString;
  }
  
  public void g(String paramString)
  {
    this.am = paramString;
  }
  
  public void onCreate()
  {
    super.onCreate();
    at = this;
    as = this;
    A = getPackageName();
    i.a(at);
    aq = com.xvideostudio.videoeditor.a.a.a().d();
    ar = com.xvideostudio.videoeditor.a.b.a().b();
    VscUserinfoSession.setmApplicationContext(at);
    a(this, true);
    new AsyncTask()
    {
      protected Integer a(Object... paramAnonymousVarArgs)
      {
        return null;
      }
    };
    i.b("VideoEditorApplication", "Application start");
    this.aN = c.d(a()).booleanValue();
    z();
    if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(a()) == 0)
    {
      this.aL = false;
      if (Tools.b(a())) {
        j.a("FireBase推送可用，屏蔽友盟推送");
      }
    }
    for (;;)
    {
      if (c.I(at) == 0L)
      {
        com.xvideostudio.videoeditor.util.c.a(at);
        c.k(this, Boolean.valueOf(false));
        c.a(at, System.currentTimeMillis());
        if ((Build.MANUFACTURER.equalsIgnoreCase("samsung")) && (A.equals("com.xvideostudio.videoeditorlite")) && (c.K(at) == 0L)) {
          new com.xvideostudio.videoeditor.i.a().a(at);
        }
      }
      a(a(), a());
      return;
      if (Tools.b(a())) {
        j.a("FireBase推送不可用，使用友盟推送");
      }
      this.aL = true;
    }
  }
  
  public com.xvideostudio.videoeditor.materialdownload.c u()
  {
    if (this.ac == null) {
      this.ac = new com.xvideostudio.videoeditor.materialdownload.c(at);
    }
    return this.ac;
  }
  
  public Hashtable<String, SiteInfoBean> v()
  {
    if (this.ag == null) {
      this.ag = new Hashtable();
    }
    return this.ag;
  }
  
  public List<String> w()
  {
    if (this.ah == null) {
      this.ah = new ArrayList();
    }
    return this.ah;
  }
  
  public Map<String, Integer> x()
  {
    if (this.ao == null) {
      this.ao = new Hashtable();
    }
    return this.ao;
  }
  
  public String y()
  {
    return h("baiduId/normal_id.json");
  }
  
  public void z()
  {
    if (this.aP) {
      return;
    }
    this.aP = true;
    ai.a("VideoEditorApplication onCreate before:");
    z = f.q(at);
    try
    {
      PackageInfo localPackageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
      A = localPackageInfo.packageName;
      i = localPackageInfo.versionCode;
      j = localPackageInfo.versionName;
      if ((A == null) || (A.length() == 0)) {
        A = "com.funcamerastudio.videomaker";
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        continue;
        PlatformConfig.setWeixin("wx7956b39d1d0e45c1", "0cf13077c52826fa3b149446e2ae5913");
      }
    }
    if (e())
    {
      PlatformConfig.setWeixin("wx0ea6dc7cd59a37d8", "f352c67099f614c8bc5540cf090af1ee");
      FontCenter.initFontCenter("TXCzRZwWI22l4Rcj6jAA", a());
      FontCenter.init(a());
      FacebookSdk.sdkInitialize(a());
      this.aK = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
      O = f.q(at);
      i.b("language", O);
      i.b("language", O.substring(0, 2));
      U();
      this.aM = al.a(this);
      return;
    }
  }
}
