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
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
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
import com.xvideostudio.videoeditor.ads.AdsInitUtil;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.HomeAppListBean;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.ShareAppListBean;
import com.xvideostudio.videoeditor.c.m;
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
import org.xvideo.videoeditor.database.DraftBoxHandler;
import org.xvideo.videoeditor.database.MediaClip;
import org.xvideo.videoeditor.database.MediaClipTrim;
import org.xvideo.videoeditor.database.MyFontEntity;
import org.xvideo.videoeditor.database.PaintDraftHandler;

public class VideoEditorApplication
  extends MultiDexApplication
{
  public static String A;
  public static boolean B = false;
  public static boolean C = false;
  public static Boolean D = Boolean.valueOf(false);
  public static HashMap<String, Integer> E = new HashMap(100);
  public static int[] F;
  public static com.xvideostudio.videoeditor.i.b G;
  public static ArrayList<MediaClipTrim> H;
  public static Map<String, Context> I = new HashMap();
  public static int J = 1;
  public static int K = 1;
  public static List<MySelfAdResponse.HomeAppListBean> L = new ArrayList();
  public static List<MySelfAdResponse.ShareAppListBean> M = new ArrayList();
  public static boolean N = false;
  public static String O = "zh-CN";
  public static String P = "ar";
  public static boolean Q = false;
  public static boolean R = false;
  public static boolean S = false;
  public static boolean T = false;
  public static boolean U = false;
  public static boolean V = false;
  public static boolean W = true;
  public static boolean X = true;
  public static boolean Y = true;
  public static boolean Z = true;
  public static final boolean a;
  private static List<com.xvideostudio.videoeditor.d.w> aA;
  private static Map<String, Map<String, String>> aB;
  private static Boolean aC;
  private static boolean aD = false;
  private static boolean aE = false;
  private static long aF = 0L;
  private static Tracker aG;
  public static String aa = as;
  public static String ab = "4835";
  public static boolean ap = false;
  public static boolean aq = false;
  private static String ar;
  private static String as;
  private static VideoEditorApplication at;
  private static Context au;
  private static String av;
  private static String aw;
  private static String ax;
  private static String ay;
  private static Map<String, Typeface> az;
  public static int b = 0;
  public static int c = 0;
  public static float d = 0.0F;
  public static Boolean e;
  public static Boolean f;
  public static String g = "";
  public static String h = "";
  public static int i = 1496;
  public static String j = "7.0.0";
  public static boolean k = false;
  public static boolean l = false;
  public static boolean m = true;
  public static boolean n = false;
  public static boolean o = true;
  public static boolean p = false;
  public static String q;
  public static String r = "https://play.google";
  public static String s;
  public static String t;
  public static String u;
  public static String v;
  public static int w = 2;
  public static Map<String, MyFontEntity> x;
  public static long y = 0L;
  public static String z = "en-US";
  private DraftBoxHandler aH = null;
  private org.xvideo.videoeditor.a.b aI = null;
  private org.xvideo.videoeditor.b.b aJ = null;
  private PaintDraftHandler aK = null;
  private com.b.a.b.d aL = null;
  private boolean aM = true;
  private String aN = "user_id";
  private boolean aO = false;
  private boolean aP = false;
  private boolean aQ = false;
  private Handler aR = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 3: 
        paramAnonymousMessage = paramAnonymousMessage.getData();
        int i = paramAnonymousMessage.getInt("duration");
        j.a(paramAnonymousMessage.getString("text"), 1, i);
        return;
      case 2: 
        j.a(2131690312);
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
            if (((!bool1) && (localFile.exists())) || (bool1)) {}
            try
            {
              boolean bool2 = localFile.exists();
              if (bool2) {
                try
                {
                  l.a(localFile);
                }
                catch (Exception localException2)
                {
                  localException2.printStackTrace();
                }
              }
              l.a(VideoEditorApplication.S(), str, i);
              if (!bool1) {
                break label134;
              }
              ar.a(str, localFile.getParent(), true);
              l.a(localFile);
              return;
            }
            catch (Exception localException1)
            {
              for (;;) {}
            }
            str.printStackTrace();
            if (bool1) {
              l.a(localFile);
            }
            label134:
          }
        }).start();
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          VideoEditorApplication.this.B();
        }
      }).start();
    }
  };
  private StoryBoardView.a aS = null;
  private boolean aT = false;
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
    boolean bool;
    if (Build.VERSION.SDK_INT >= 19) {
      bool = true;
    } else {
      bool = false;
    }
    a = bool;
    e = Boolean.valueOf(false);
    f = Boolean.valueOf(false);
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
    if (!b())
    {
      final String str1 = ar;
      aa = as;
      ab = "4835";
      String str2 = com.xvideostudio.videoeditor.a.a.a().b();
      String str3 = a(this, Process.myPid());
      if (str3 != null)
      {
        if (str3.equalsIgnoreCase(au.getPackageName()))
        {
          MobclickAgent.onEvent(this, "PROCESS_MAIN_APPLICATION");
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(au.getPackageName());
          localStringBuilder.append(":channel");
          if (str3.equalsIgnoreCase(localStringBuilder.toString()))
          {
            MobclickAgent.onEvent(this, "PROCESS_UMPUSH_APPLICATION");
          }
          else
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(au.getPackageName());
            localStringBuilder.append(":servicerewards");
            if (str3.equalsIgnoreCase(localStringBuilder.toString()))
            {
              MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDS_APPLICATION");
            }
            else
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append(au.getPackageName());
              localStringBuilder.append(":servicerewardsprot");
              if (str3.equalsIgnoreCase(localStringBuilder.toString())) {
                MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDSPROT_APPLICATION");
              }
            }
          }
        }
        if (str3.equals(au.getPackageName()))
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
      }
    }
  }
  
  private void V()
  {
    e.a(au).a();
    Context localContext = au;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("pkname:");
    localStringBuilder.append(A);
    MobclickAgent.onEvent(localContext, "ALTAMOB_IN_SUCCESS", localStringBuilder.toString());
    if (c.W(au).booleanValue()) {
      Toast.makeText(au, "=======Alt---Init=======", 1).show();
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("the cpu frequency is ");
    localStringBuilder.append(i1);
    localStringBuilder.append("khz");
    i.b("VideoEditorApplication", localStringBuilder.toString());
    int i2 = f.o();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("cpuCoreNums is ");
    localStringBuilder.append(i2);
    i.b("VideoEditorApplication", localStringBuilder.toString());
    if (i2 < 2) {
      hl.productor.fxlib.b.D = false;
    } else if ((i2 == 2) && (i1 <= 1000000)) {
      hl.productor.fxlib.b.D = false;
    } else {
      hl.productor.fxlib.b.D = true;
    }
    if (!hl.productor.fxlib.b.D) {
      x.n(au, 1);
    }
    if (true == hl.productor.fxlib.b.D)
    {
      if (HLRenderThread.detectGraphicsPerformance() == -1)
      {
        hl.productor.fxlib.b.D = false;
        x.n(au, 1);
      }
      i1 = HLRenderThread.queryValue(3) / 8;
      if (i1 != 2)
      {
        if (i1 != 4) {
          return;
        }
        hl.productor.fxlib.e.c = 2;
        i.b("VideoEditorApplication", "detectGraphicsPerformance RGBA8888");
        x.n(au, 3);
        return;
      }
      hl.productor.fxlib.e.c = 1;
      i.b("VideoEditorApplication", "detectGraphicsPerformance RGB565");
      x.n(au, 2);
    }
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("width");
    localStringBuilder.append(paramContext.widthPixels);
    i.b("cxs", localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("height");
    localStringBuilder.append(paramContext.heightPixels);
    i.b("cxs", localStringBuilder.toString());
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
    if (at == null) {
      at = new VideoEditorApplication();
    }
    return at;
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
        while (i < paramAnonymousList.size())
        {
          String str1 = t.a(((Font)paramAnonymousList.get(i)).getFontLocalPath());
          if (!VideoEditorApplication.x.containsKey(str1))
          {
            String str2 = ((Font)paramAnonymousList.get(i)).getFontName();
            try
            {
              ((Font)paramAnonymousList.get(i)).getTypeface(new VideoEditorApplication.5.1(this, str1, str2));
            }
            catch (Exception localException)
            {
              VideoEditorApplication.x.put(str1, new MyFontEntity(Typeface.DEFAULT, str2));
              localException.printStackTrace();
            }
          }
          i += 1;
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
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
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
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
      return true;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      localNoSuchMethodError.printStackTrace();
    }
    return paramActivity.isFinishing();
  }
  
  public static boolean a(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    int i1;
    if ((!Tools.b(paramContext)) && (!f()) && (!e())) {
      i1 = 0;
    } else {
      i1 = 1;
    }
    if ((i1 != 0) && (f.n(paramContext))) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    return i1 != 0;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("checkApkExist packageName:");
    localStringBuilder.append(paramString);
    i.b("VideoEditorApplication", localStringBuilder.toString());
    bool2 = false;
    bool1 = bool2;
    if (paramString != null) {
      if ("".equals(paramString)) {
        bool1 = bool2;
      }
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      bool1 = true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        bool1 = bool2;
      }
    }
    paramContext = new StringBuilder();
    paramContext.append("checkApkExist ret:");
    paramContext.append(bool1);
    i.b("VideoEditorApplication", paramContext.toString());
    return bool1;
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
      if (az == null) {
        l();
      }
      if (az.containsKey(paramString)) {
        return (Typeface)az.get(paramString);
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
      StringBuilder localStringBuilder = new StringBuilder();
      i1 = i2;
      localStringBuilder.append("https://play.google.com/store/apps/details?id=com.xvideostudio.videoeditorpro&referrer=");
      i1 = i2;
      localStringBuilder.append(paramString);
      i1 = i2;
      localIntent.setData(Uri.parse(localStringBuilder.toString()));
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
      if (i1 != 0) {
        MobclickAgent.onEvent(paramContext, "BUY_PRO_GO_TO_GP_FAILED");
      } else {
        MobclickAgent.onEvent(paramContext, "BUY_PRO_GO_TO_BROWSER_FAILED");
      }
      paramString.printStackTrace();
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
    if (x == null) {
      a(null);
    }
    if (x.containsKey(paramString)) {
      return ((MyFontEntity)x.get(paramString)).fontName;
    }
    return null;
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
    if (c())
    {
      if (Build.VERSION.SDK_INT < 9) {
        return false;
      }
      if ((g()) && (x.z(a()))) {
        return false;
      }
      return (f()) || (g()) || (e());
    }
    return false;
  }
  
  private void e(int paramInt)
  {
    aA = new ArrayList();
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
      aA.add(localW);
      i1 += 1;
    }
  }
  
  public static boolean e()
  {
    return a("com.xvideostudio.videoeditorlite");
  }
  
  public static boolean e(String paramString)
  {
    if (au == null) {
      return false;
    }
    Iterator localIterator = au.getPackageManager().getInstalledPackages(0).iterator();
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
    //   0: new 1028	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 1029	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore_3
    //   14: new 1031	java/io/BufferedInputStream
    //   17: dup
    //   18: getstatic 283	com/xvideostudio/videoeditor/VideoEditorApplication:au	Landroid/content/Context;
    //   21: invokevirtual 1035	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   24: aload_1
    //   25: invokevirtual 1041	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   28: invokespecial 1044	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore_1
    //   32: sipush 4096
    //   35: newarray byte
    //   37: astore_3
    //   38: aload_1
    //   39: aload_3
    //   40: invokevirtual 1048	java/io/BufferedInputStream:read	([B)I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +14 -> 59
    //   48: aload 6
    //   50: aload_3
    //   51: iconst_0
    //   52: iload_2
    //   53: invokevirtual 1052	java/io/ByteArrayOutputStream:write	([BII)V
    //   56: goto -18 -> 38
    //   59: aload_0
    //   60: aload_1
    //   61: invokespecial 1054	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   64: goto +80 -> 144
    //   67: astore 4
    //   69: aload_1
    //   70: astore_3
    //   71: aload 4
    //   73: astore_1
    //   74: goto +76 -> 150
    //   77: astore 4
    //   79: goto +12 -> 91
    //   82: astore_1
    //   83: goto +67 -> 150
    //   86: astore 4
    //   88: aload 5
    //   90: astore_1
    //   91: aload_1
    //   92: astore_3
    //   93: new 505	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 506	java/lang/StringBuilder:<init>	()V
    //   100: astore 5
    //   102: aload_1
    //   103: astore_3
    //   104: aload 5
    //   106: ldc_w 1056
    //   109: invokevirtual 510	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload_1
    //   114: astore_3
    //   115: aload 5
    //   117: aload 4
    //   119: invokevirtual 1059	java/io/IOException:getMessage	()Ljava/lang/String;
    //   122: invokevirtual 510	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload_1
    //   127: astore_3
    //   128: ldc_w 644
    //   131: aload 5
    //   133: invokevirtual 515	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokestatic 1061	com/xvideostudio/videoeditor/tool/i:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: aload_0
    //   140: aload_1
    //   141: invokespecial 1054	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   144: aload 6
    //   146: invokevirtual 1062	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   149: areturn
    //   150: aload_0
    //   151: aload_3
    //   152: invokespecial 1054	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   155: aload_1
    //   156: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	this	VideoEditorApplication
    //   0	157	1	paramString	String
    //   43	10	2	i1	int
    //   13	139	3	localObject1	Object
    //   67	5	4	localObject2	Object
    //   77	1	4	localIOException1	IOException
    //   86	32	4	localIOException2	IOException
    //   10	122	5	localStringBuilder	StringBuilder
    //   7	138	6	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    // Exception table:
    //   from	to	target	type
    //   32	38	67	finally
    //   38	44	67	finally
    //   48	56	67	finally
    //   32	38	77	java/io/IOException
    //   38	44	77	java/io/IOException
    //   48	56	77	java/io/IOException
    //   14	32	82	finally
    //   93	102	82	finally
    //   104	113	82	finally
    //   115	126	82	finally
    //   128	139	82	finally
    //   14	32	86	java/io/IOException
  }
  
  public static boolean h()
  {
    if (!k) {
      return false;
    }
    return x.s(au, 0) != 0;
  }
  
  public static String i()
  {
    return aw;
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
    return !l.a(au, "UMENG_CHANNEL", "GOOGLEPLAY").equalsIgnoreCase("SAMSUNG");
  }
  
  public static SharedPreferences k()
  {
    return PreferenceManager.getDefaultSharedPreferences(at);
  }
  
  public static Map<String, Typeface> l()
  {
    if ((az == null) || (az.size() == 0))
    {
      az = new LinkedHashMap();
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
      while (i1 >= 0)
      {
        Map localMap;
        Object localObject1;
        if (i1 == 0)
        {
          localMap = az;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(i1);
          ((StringBuilder)localObject1).append("");
          localMap.put(((StringBuilder)localObject1).toString(), Typeface.SANS_SERIF);
        }
        else
        {
          try
          {
            localMap = az;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(i1);
            ((StringBuilder)localObject1).append("");
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject2 = au.getAssets();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("font/");
            localStringBuilder.append(arrayOfString[i1]);
            localMap.put(localObject1, Typeface.createFromAsset((AssetManager)localObject2, localStringBuilder.toString()));
          }
          catch (Exception localException)
          {
            localObject1 = az;
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(i1);
            ((StringBuilder)localObject2).append("");
            ((Map)localObject1).put(((StringBuilder)localObject2).toString(), Typeface.SANS_SERIF);
            localException.printStackTrace();
          }
        }
        i1 -= 1;
      }
      az.put("9", Typeface.createFromAsset(au.getAssets(), "font/helvetica_inserat_lt.ttf"));
      ai.a("VideoEditorApplication onCreate after typeFace:");
    }
    return az;
  }
  
  public static Map<String, Map<String, String>> m()
  {
    if ((aB == null) || (aB.size() == 0))
    {
      aB = new LinkedHashMap();
      new HashMap();
      Object localObject2 = new String[1];
      Object localObject1 = au.getResources().getString(2131690170);
      int i1 = 0;
      localObject2[0] = localObject1;
      localObject1 = new String[localObject2.length];
      System.arraycopy(localObject2, 0, localObject1, 0, localObject2.length);
      while (i1 < localObject1.length)
      {
        localObject2 = new HashMap();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(100000 + i1);
        localStringBuilder.append("");
        ((Map)localObject2).put("songId", localStringBuilder.toString());
        ((Map)localObject2).put("isShow", "1");
        ((Map)localObject2).put("fileName", new String[] { "music_new_york_love.aac" }[i1]);
        ((Map)localObject2).put("lang", "en");
        ((Map)localObject2).put("artist", "artist");
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(new int[] { 2131623947 }[i1]);
        localStringBuilder.append("");
        ((Map)localObject2).put("rawId", localStringBuilder.toString());
        ((Map)localObject2).put("duration", new String[] { "64608" }[i1]);
        ((Map)localObject2).put("musicName", localObject1[i1]);
        aB.put(new String[] { "music_new_york_love.aac" }[i1], localObject2);
        i1 += 1;
      }
    }
    return aB;
  }
  
  public static boolean n()
  {
    if (aC != null) {
      return aC.booleanValue();
    }
    if (au == null) {
      return false;
    }
    Object localObject = au.getPackageManager().getInstalledPackages(0);
    aC = Boolean.FALSE;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equalsIgnoreCase("com.android.vending")) {
        aC = Boolean.TRUE;
      }
    }
    return aC.booleanValue();
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
    if (aD) {
      return;
    }
    aD = true;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(r);
    localStringBuilder.append(".com/store/");
    q = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(q);
    localStringBuilder.append("apps/details?id=");
    s = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(s);
    localStringBuilder.append("com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshow%2520setting%26utm_medium%3Dbanner%26utm_term%3Dvideoshow%2520pro%26utm_content%3Dvideoshow%2520pro%2520for%2520setting%26utm_campaign%3Dvideoshow%2520pro%2520for%2520setting");
    v = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(s);
    localStringBuilder.append("com.xvideostudio.videoeditorpro");
    u = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(s);
    localStringBuilder.append("com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshowlabs%26utm_medium%3Dbanner%26utm_term%3Dlabs%26utm_content%3Dvideoshow%2520for%2520labs%26utm_campaign%3Dvideoshow%2520for%2520labs");
    t = localStringBuilder.toString();
    if ((A != null) && (A.length() > 0))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(s);
      localStringBuilder.append(A);
      s = localStringBuilder.toString();
      return;
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(s);
    localStringBuilder.append("com.funcamerastudio.videomaker");
    s = localStringBuilder.toString();
  }
  
  public static void q()
  {
    if (aE) {
      return;
    }
    aE = true;
    if ((g()) && (x.A(au) == 1)) {
      B = true;
    }
  }
  
  public static boolean r()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = aF;
      if (l1 - l2 < 1000L) {
        return true;
      }
      aF = l1;
      return false;
    }
    finally {}
  }
  
  public static boolean s()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = aF;
      if (l1 - l2 < 400L) {
        return true;
      }
      aF = l1;
      return false;
    }
    finally {}
  }
  
  public static void t()
  {
    if (a)
    {
      if (c.aj(au).booleanValue()) {
        return;
      }
      if (!I2WAPI.hasReadyAd(au, "WAITING_PAGE_NATIVE")) {
        I2WAPI.preload(au, "WAITING_PAGE_NATIVE");
      }
      return;
    }
  }
  
  public void A()
  {
    if (this.aP) {
      return;
    }
    this.aP = true;
    try
    {
      FontCenter.getInstance().setFolder_font(com.xvideostudio.videoeditor.i.c.K());
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
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
        if (x.M(VideoEditorApplication.a()))
        {
          MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_START_APP");
          i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_START_APP");
          if (!x.L(VideoEditorApplication.S()))
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("HW_ENCODER_ERR errTime:");
            localStringBuilder.append(x.N(VideoEditorApplication.a()));
            localStringBuilder.append(" errResetTime:");
            localStringBuilder.append(x.O(VideoEditorApplication.a()));
            i.b("VideoEditorApplication", localStringBuilder.toString());
            if (x.O(VideoEditorApplication.a()) < 3)
            {
              if (x.N(VideoEditorApplication.a()) % 5 == 0)
              {
                x.p(VideoEditorApplication.S(), false);
                x.G(VideoEditorApplication.a(), 0);
                if ((f.d() >= 18) && (VideoEditorApplication.this.K()))
                {
                  hl.productor.fxlib.b.w = true;
                  hl.productor.fxlib.b.z = true;
                }
                x.F(VideoEditorApplication.a(), x.O(VideoEditorApplication.a()) + 1);
                i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
              }
              else
              {
                x.G(VideoEditorApplication.a(), x.N(VideoEditorApplication.a()) + 1);
              }
            }
            else
            {
              MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
              i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
            }
          }
          else
          {
            i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
            MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
            x.p(VideoEditorApplication.S(), false);
            x.G(VideoEditorApplication.a(), 0);
            if ((f.d() >= 18) && (VideoEditorApplication.this.K()))
            {
              hl.productor.fxlib.b.w = true;
              hl.productor.fxlib.b.z = true;
            }
          }
        }
        else if (f.d() >= 18)
        {
          if (VideoEditorApplication.this.K())
          {
            hl.productor.fxlib.b.w = true;
            hl.productor.fxlib.b.z = true;
          }
          MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_OS_UPTO_18");
        }
        else
        {
          MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_OS_BELOW_18");
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("FxConfig.video_hw_encode_enable = ");
        localStringBuilder.append(hl.productor.fxlib.b.w);
        i.b("", localStringBuilder.toString());
        VideoEditorApplication.this.L();
        int i = VideoEditorApplication.b;
        int j = VideoEditorApplication.c;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("screenWidth = ");
        localStringBuilder.append(i);
        localStringBuilder.append("screenHeight = ");
        localStringBuilder.append(j);
        i.b("", localStringBuilder.toString());
        if (i * j < 921600) {
          hl.productor.fxlib.v.n = 0;
        }
        if (Math.min(hl.productor.fxlib.b.f, hl.productor.fxlib.b.e) >= 720) {
          VideoEditorApplication.this.J();
        }
        if (hl.productor.fxlib.b.D == true) {
          VideoEditorApplication.this.M();
        } else {
          hl.productor.fxlib.b.E = false;
        }
        if (hl.productor.fxlib.b.E)
        {
          i = x.m(VideoEditorApplication.S(), hl.productor.fxlib.b.D ^ true);
          if ((i == 0) && (!hl.productor.fxlib.b.D))
          {
            x.l(VideoEditorApplication.S(), 1);
            return;
          }
          if ((i == 1) && (hl.productor.fxlib.b.D)) {
            x.l(VideoEditorApplication.S(), 0);
          }
        }
      }
    }).start();
  }
  
  public void B()
  {
    p();
    ai.a("VideoEditorApplication onCreate after:");
    T();
    C();
    if (!l.a(com.xvideostudio.videoeditor.i.d.f(com.xvideostudio.videoeditor.i.d.f(1), 6))) {
      x.a(au, false, f.d(au));
    } else if (!new File(com.xvideostudio.videoeditor.i.c.F()).isDirectory()) {
      x.a(au, false, f.d(au));
    } else if (!new File(com.xvideostudio.videoeditor.i.c.H()).isDirectory()) {
      x.a(au, false, f.d(au));
    }
    a(true, true, true, true, true);
    int i1 = x.u(a(), 3);
    if (i1 == 1)
    {
      hl.productor.fxlib.b.Y = false;
      hl.productor.fxlib.b.N = 1;
    }
    else if (i1 == 2)
    {
      hl.productor.fxlib.b.Y = false;
      hl.productor.fxlib.b.N = 2;
    }
    else if (i1 == 3)
    {
      hl.productor.fxlib.b.Y = true;
      hl.productor.fxlib.b.N = 3;
    }
    e(i1);
    try
    {
      l();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.i.c.f());
      ((StringBuilder)localObject).append("1.png");
      localObject = ((StringBuilder)localObject).toString();
      if (!l.a((String)localObject)) {
        l.a(au, 2131623960, (String)localObject);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    if (g())
    {
      i1 = x.A(au);
      if (i1 == 1)
      {
        B = true;
        return;
      }
      if (i1 == 2) {
        b(1);
      }
    }
  }
  
  public void C()
  {
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.i.c.I());
      ((StringBuilder)localObject).append("VideoShowUserDB.db");
      localObject = ((StringBuilder)localObject).toString();
      if (!new File((String)localObject).exists())
      {
        boolean bool = l.b(com.xvideostudio.videoeditor.i.c.a(au).getAbsolutePath(), (String)localObject);
        i1 = 1;
        if (!bool)
        {
          localObject = new m(au);
          ((m)localObject).a(((m)localObject).a());
          i1 = 18;
        }
        else
        {
          l.a(m.a, 1);
        }
      }
      else
      {
        i1 = l.o(m.a);
      }
      localObject = new m(au);
      if (i1 < 15) {
        break label269;
      }
      try
      {
        SQLiteDatabase localSQLiteDatabase = ((m)localObject).a();
        if (!((m)localObject).a(localSQLiteDatabase, "filedownlog", "material_giphy")) {
          ((m)localObject).e(localSQLiteDatabase);
        }
        if (!((m)localObject).a(localSQLiteDatabase, "filedownlog", "is_music")) {
          ((m)localObject).p(localSQLiteDatabase);
        }
        if (!((m)localObject).a(localSQLiteDatabase, "filedownlog", "is_pro")) {
          ((m)localObject).q(localSQLiteDatabase);
        }
        localSQLiteDatabase.close();
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    catch (Exception localException1)
    {
      int i1;
      label269:
      do
      {
        try
        {
          Object localObject;
          this.an.post(new Runnable()
          {
            public void run()
            {
              Toast.makeText(VideoEditorApplication.S(), localException1.getMessage(), 1).show();
            }
          });
        }
        catch (Exception localException3)
        {
          for (;;) {}
        }
        i.a("VideoEditorApplication", localException1.toString());
        i.a("VideoEditorApplication", localException1.toString());
        return;
      } while (i1 < 18);
    }
    ((m)localObject).a(((m)localObject).a(), i1, 18);
    return;
  }
  
  public void D()
  {
    b();
    E();
  }
  
  public void E()
  {
    Object localObject1 = "";
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Sd1 path:");
      ((StringBuilder)localObject2).append((String)localObject1);
      i.b("cxs", ((StringBuilder)localObject2).toString());
    }
    Object localObject2 = com.xvideostudio.videoeditor.i.c.a();
    if ((localObject2 != null) && (!((String)localObject1).equalsIgnoreCase((String)localObject2)) && (!((String)localObject2).startsWith("/storage/emulated/legacy")))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Sd2 path:");
      ((StringBuilder)localObject1).append((String)localObject2);
      i.b("cxs", ((StringBuilder)localObject1).toString());
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append(File.separator);
      ((StringBuilder)localObject1).append(com.xvideostudio.videoeditor.i.c.f);
      ay = ((StringBuilder)localObject1).toString();
      l.b(ay);
      k = true;
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(ay);
        ((StringBuilder)localObject1).append(ak.a());
        ((StringBuilder)localObject1).append(".test");
        localObject1 = new File(((StringBuilder)localObject1).toString());
        ((File)localObject1).createNewFile();
        ((File)localObject1).delete();
      }
      catch (Exception localException)
      {
        k = false;
        localException.printStackTrace();
      }
    }
    av = com.xvideostudio.videoeditor.i.c.t();
    aw = com.xvideostudio.videoeditor.i.c.e();
    ax = com.xvideostudio.videoeditor.i.c.d();
    if ((!k) && (h() == true)) {
      a(false);
    }
  }
  
  public DraftBoxHandler F()
  {
    if (this.aH == null) {
      this.aH = new DraftBoxHandler();
    }
    return this.aH;
  }
  
  public org.xvideo.videoeditor.a.b G()
  {
    if (this.aI == null) {
      this.aI = new org.xvideo.videoeditor.a.b(getApplicationContext());
    }
    return this.aI;
  }
  
  public org.xvideo.videoeditor.b.b H()
  {
    if (this.aJ == null) {
      this.aJ = new org.xvideo.videoeditor.b.b(getApplicationContext());
    }
    return this.aJ;
  }
  
  public void I()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    this.aR.sendMessage(localMessage);
  }
  
  public void J()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public boolean K()
  {
    int i7 = MediaCodecList.getCodecCount();
    int i3 = 0;
    int i2 = 0;
    int i4;
    for (int i1 = 0; i3 < i7; i1 = i4)
    {
      MediaCodecInfo localMediaCodecInfo = MediaCodecList.getCodecInfoAt(i3);
      int i5 = i2;
      i4 = i1;
      if (localMediaCodecInfo != null)
      {
        i5 = i2;
        i4 = i1;
        if (localMediaCodecInfo.isEncoder())
        {
          Object localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Codec: ");
          ((StringBuilder)localObject).append(localMediaCodecInfo.getName());
          i.d("JNIMsg", ((StringBuilder)localObject).toString());
          localObject = localMediaCodecInfo.getSupportedTypes();
          i5 = i2;
          i4 = i1;
          if (localObject != null)
          {
            int i8 = localObject.length;
            i5 = 0;
            while (i5 < i8)
            {
              String str = localObject[i5];
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("mimes: ");
              localStringBuilder.append(str);
              i.d("JNIMsg", localStringBuilder.toString());
              int i6 = i1;
              i4 = i2;
              if (str.equalsIgnoreCase("video/avc"))
              {
                str = localMediaCodecInfo.getName();
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("AVC encoder is ");
                localStringBuilder.append(str);
                i.b("JNIMsg", localStringBuilder.toString());
                i2 += 1;
                i6 = i1;
                i4 = i2;
                if (str.equalsIgnoreCase("OMX.sprd.h264.encoder"))
                {
                  MobclickAgent.onEvent(au, "AVC_ENCODER_CHECK_OMX_SPRD_H264");
                  i6 = 1;
                  i4 = i2;
                }
              }
              i5 += 1;
              i1 = i6;
              i2 = i4;
            }
            i4 = i1;
            i5 = i2;
          }
        }
      }
      i3 += 1;
      i2 = i5;
    }
    if ((i2 > 0) && (i1 == 0))
    {
      MobclickAgent.onEvent(au, "AVC_ENCODER_CHECK_H264_HW_ENCODE_SUPPORT");
      return true;
    }
    MobclickAgent.onEvent(au, "AVC_ENCODER_CHECK_H264_HW_ENCODE_CANNOT_SUPPORT");
    return false;
  }
  
  public void L()
  {
    Object localObject = f.m();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("cpu Architecture is ");
    localStringBuilder.append((String)localObject);
    i.b("VideoEditorApplication", localStringBuilder.toString());
    f.d();
    if ((!((String)localObject).equals("armeabi-v7a")) && (!((String)localObject).equals("arm64-v8a")))
    {
      hl.productor.fxlib.b.X = false;
      hl.productor.fxlib.b.H = 0;
    }
    else
    {
      i.b("VideoEditorApplication", "H264 encoding is enabled");
      hl.productor.fxlib.b.X = true;
      hl.productor.fxlib.b.H = 1;
    }
    int i1 = Math.min(f.k(au), f.l(au));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("screenWidth = ");
    ((StringBuilder)localObject).append(i1);
    i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    if (i1 > 1446)
    {
      hl.productor.fxlib.b.g = i1;
      hl.productor.fxlib.b.h = i1;
    }
    F = f.v();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cameraMaxSize[0] = ");
    ((StringBuilder)localObject).append(F[0]);
    i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cameraMaxSize[1] = ");
    ((StringBuilder)localObject).append(F[1]);
    i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > f.k(au) * f.l(au))
    {
      hl.productor.fxlib.b.e = Math.min(f.k(au), f.l(au));
      hl.productor.fxlib.b.f = Math.max(f.k(au), f.l(au));
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Max output size restricted to ");
      ((StringBuilder)localObject).append(f.k(au));
      ((StringBuilder)localObject).append(" x ");
      ((StringBuilder)localObject).append(f.l(au));
      i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    }
    if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > F[0] * F[1])
    {
      hl.productor.fxlib.b.e = Math.min(F[0], F[1]);
      hl.productor.fxlib.b.f = Math.max(F[0], F[1]);
    }
    if (((f.k(au) * f.l(au) <= 384000) && (f.k(au) * f.l(au) == F[0] * F[1])) || (F[0] * F[1] < 384000))
    {
      i.b("VideoEditorApplication", "special machine , we need disable video_hw_encode_enable  ");
      hl.productor.fxlib.b.w = false;
      hl.productor.fxlib.b.z = false;
      MobclickAgent.onEvent(this, "EXPORT_FORCE_SET_HW_ENCODE_DISABLE");
    }
    if (f.o() >= 4)
    {
      i.b("VideoEditorApplication", "4-core   render_target_scale_mode_enabled can be false");
      hl.productor.fxlib.b.J = false;
    }
    else
    {
      hl.productor.fxlib.b.J = true;
    }
    if (F[2] == 1)
    {
      if ((b * c <= 2073600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > F[0] * F[1]))
      {
        hl.productor.fxlib.b.ae = Math.min(F[0], F[1]);
        hl.productor.fxlib.b.af = Math.max(F[0], F[1]);
      }
      hl.productor.fxlib.b.ag = true;
    }
    else if ((b * c <= 921600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > F[0] * F[1]))
    {
      hl.productor.fxlib.b.ae = Math.min(F[0], F[1]);
      hl.productor.fxlib.b.af = Math.max(F[0], F[1]);
    }
    hl.productor.fxlib.b.ad = 1;
  }
  
  void M()
  {
    switch (x.o(au, 0))
    {
    default: 
      return;
    case 3: 
      hl.productor.fxlib.b.D = true;
      hl.productor.fxlib.e.c = 2;
      return;
    case 2: 
      hl.productor.fxlib.b.D = true;
      hl.productor.fxlib.e.c = 1;
      return;
    case 1: 
      hl.productor.fxlib.b.D = false;
      return;
    }
    Z();
  }
  
  public void N()
  {
    if ((i <= 0) || (j == null) || (j.equals(""))) {
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
  }
  
  public void O()
  {
    if (this.aT) {
      return;
    }
    this.aT = true;
    if (A.equals("com.xvideostudio.videoeditorlite")) {
      as.a(au, true);
    }
  }
  
  public List<com.xvideostudio.videoeditor.d.w> P()
  {
    if (aA == null) {
      e(x.u(a(), 3));
    }
    return aA;
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
    this.aR.post(new Runnable()
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
          j.a(VideoEditorApplication.this.getResources().getString(2131690644), -1, 1);
          return;
        }
        if (VideoEditorApplication.c(VideoEditorApplication.this) != null) {
          VideoEditorApplication.c(VideoEditorApplication.this).a(paramMediaClip);
        }
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
    this.aS = paramA;
  }
  
  public void a(String paramString, int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    Bundle localBundle = new Bundle();
    localBundle.putInt("duration", paramInt);
    localBundle.putString("text", paramString);
    localMessage.setData(localBundle);
    this.aR.sendMessage(localMessage);
  }
  
  public void a(String paramString, ImageView paramImageView, com.b.a.b.c paramC)
  {
    com.b.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aL == null)
    {
      this.aL = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    }
    this.aL.a(paramString, paramImageView, localC);
  }
  
  public void a(String paramString, ImageView paramImageView, com.b.a.b.c paramC, com.b.a.b.f.c paramC1)
  {
    this.aL.a(paramString, paramImageView, paramC, paramC1);
  }
  
  public void a(String paramString, com.b.a.b.c paramC, com.b.a.b.f.a paramA)
  {
    com.b.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aL == null)
    {
      this.aL = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    }
    this.aL.a(paramString, localC, paramA);
  }
  
  public void a(final String paramString1, final boolean paramBoolean, final int paramInt, final String paramString2)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
      }
    }).start();
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
  {
    try
    {
      if (!x.d(au, f.d(au)))
      {
        if ((paramBoolean1) && (paramBoolean2)) {
          Y();
        }
        String str;
        Object localObject1;
        Object localObject2;
        Object localObject3;
        if (paramBoolean1)
        {
          str = com.xvideostudio.videoeditor.i.c.A();
          l.b(str);
          localObject1 = m().keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localObject2 = (Map)m().get(localObject2);
            if ((localObject2 != null) && (((Map)localObject2).size() != 0))
            {
              localObject3 = new Message();
              ((Message)localObject3).what = 1;
              Bundle localBundle = new Bundle();
              localBundle.putInt("rawId", Integer.valueOf((String)((Map)localObject2).get("rawId")).intValue());
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(str);
              localStringBuilder.append((String)((Map)localObject2).get("fileName"));
              localBundle.putString("rawFilePath", localStringBuilder.toString());
              ((Message)localObject3).setData(localBundle);
              this.aR.sendMessage((Message)localObject3);
            }
          }
        }
        if (paramBoolean2)
        {
          str = com.xvideostudio.videoeditor.i.c.C();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623958);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("theme_new_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aR.sendMessage((Message)localObject1);
        }
        if (paramBoolean3)
        {
          str = com.xvideostudio.videoeditor.i.c.E();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623959);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("trans_new_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aR.sendMessage((Message)localObject1);
        }
        if (paramBoolean4)
        {
          str = com.xvideostudio.videoeditor.i.c.F();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623956);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("subtitle_style_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aR.sendMessage((Message)localObject1);
        }
        if (paramBoolean5)
        {
          str = com.xvideostudio.videoeditor.i.c.H();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623942);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("fx_sound_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aR.sendMessage((Message)localObject1);
        }
        x.a(au, true, f.d(au));
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void b(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          com.xvideostudio.videoeditor.control.b.a(MainActivity.a(VideoEditorApplication.S()), 2, new VideoEditorApplication.11.1(this));
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
    if (this.aL == null)
    {
      this.aL = com.b.a.b.d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    }
    com.b.a.b.d localD = this.aL;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("file://");
    localStringBuilder.append(paramString);
    localD.a(localStringBuilder.toString(), paramImageView, paramC);
  }
  
  public void c(int paramInt)
  {
    int i1 = 0;
    while (i1 < 20)
    {
      com.xvideostudio.videoeditor.d.w localW = (com.xvideostudio.videoeditor.d.w)P().get(i1);
      if (paramInt < 4)
      {
        if (localW.c)
        {
          localW.c = false;
          aA.set(i1, localW);
        }
      }
      else if (paramInt == localW.d) {
        localW.c = true;
      } else {
        localW.c = false;
      }
      aA.set(i1, localW);
      i1 += 1;
    }
  }
  
  public com.xvideostudio.videoeditor.d.w d(int paramInt)
  {
    int i1 = 0;
    while (i1 < 20)
    {
      com.xvideostudio.videoeditor.d.w localW = (com.xvideostudio.videoeditor.d.w)aA.get(i1);
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
    au = this;
    at = this;
    A = getPackageName();
    i.a(au);
    ar = com.xvideostudio.videoeditor.a.a.a().d();
    as = com.xvideostudio.videoeditor.a.b.a().b();
    VscUserinfoSession.setmApplicationContext(au);
    a(this, true);
    new AsyncTask()
    {
      protected Integer a(Object... paramAnonymousVarArgs)
      {
        return null;
      }
    };
    i.b("VideoEditorApplication", "Application start");
    this.aO = c.d(a()).booleanValue();
    z();
    if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(a()) == 0)
    {
      this.aM = false;
      if (Tools.b(a())) {
        j.a("FireBase推送可用，屏蔽友盟推送");
      }
    }
    else
    {
      if (Tools.b(a())) {
        j.a("FireBase推送不可用，使用友盟推送");
      }
      this.aM = true;
    }
    if (c.I(au) == 0L)
    {
      com.xvideostudio.videoeditor.util.c.a(au);
      c.k(this, Boolean.valueOf(false));
      c.a(au, System.currentTimeMillis());
      if ((Build.MANUFACTURER.equalsIgnoreCase("samsung")) && (A.equals("com.xvideostudio.videoeditorlite")) && (c.K(au) == 0L)) {
        new com.xvideostudio.videoeditor.i.a().a(au);
      }
    }
    a(a(), a());
    if (!AdsInitUtil.is_ads_init.booleanValue())
    {
      AdsInitUtil.is_ads_init = Boolean.valueOf(true);
      AdsInitUtil.initAllAds(au, this.aR);
    }
  }
  
  public com.xvideostudio.videoeditor.materialdownload.c u()
  {
    if (this.ac == null) {
      this.ac = new com.xvideostudio.videoeditor.materialdownload.c(au);
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
    if (this.aQ) {
      return;
    }
    this.aQ = true;
    ai.a("VideoEditorApplication onCreate before:");
    z = f.q(au);
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
      localException.printStackTrace();
    }
    if (e()) {
      PlatformConfig.setWeixin("wx0ea6dc7cd59a37d8", "f352c67099f614c8bc5540cf090af1ee");
    } else {
      PlatformConfig.setWeixin("wx7956b39d1d0e45c1", "0cf13077c52826fa3b149446e2ae5913");
    }
    FontCenter.initFontCenter("TXCzRZwWI22l4Rcj6jAA", a());
    FontCenter.init(a());
    FacebookSdk.sdkInitialize(a());
    this.aL = com.b.a.b.d.a();
    r.a(getApplicationContext(), com.xvideostudio.videoeditor.i.c.u());
    O = f.q(au);
    i.b("language", O);
    i.b("language", O.substring(0, 2));
    U();
    this.aN = al.a(this);
  }
}
