package com.xvideostudio.videoeditor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.AsyncTask;
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
import com.c.a.b.d;
import com.duapps.ad.base.DuAdNetwork;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GoogleApiAvailability;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.xinmei365.fontsdk.FontCenter;
import com.xinmei365.fontsdk.bean.Font;
import com.xinmei365.fontsdk.callback.FontScanCallBack;
import com.xvideostudio.VsCommunity.Api.VscUserinfoSession;
import com.xvideostudio.videoeditor.activity.MainActivity;
import com.xvideostudio.videoeditor.activity.MarketUrlRedirectActivity;
import com.xvideostudio.videoeditor.activity.SplashActivity;
import com.xvideostudio.videoeditor.activity.Tools;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.HomeAppListBean;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.ShareAppListBean;
import com.xvideostudio.videoeditor.gsonentity.SiteInfoBean;
import com.xvideostudio.videoeditor.tool.i;
import com.xvideostudio.videoeditor.tool.j;
import com.xvideostudio.videoeditor.util.af;
import com.xvideostudio.videoeditor.util.aj;
import com.xvideostudio.videoeditor.util.al;
import com.xvideostudio.videoeditor.util.am;
import com.xvideostudio.videoeditor.util.an;
import com.xvideostudio.videoeditor.util.at;
import com.xvideostudio.videoeditor.util.l;
import com.xvideostudio.videoeditor.util.r;
import com.xvideostudio.videoeditor.util.t;
import com.xvideostudio.videoeditor.util.w;
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
  public static Boolean A = Boolean.valueOf(false);
  public static HashMap<String, Integer> B = new HashMap(100);
  public static int[] C;
  public static com.xvideostudio.videoeditor.o.a D;
  public static ArrayList<MediaClipTrim> E;
  public static Map<String, Context> F = new HashMap();
  public static int G = 0;
  public static int H = 1;
  public static List<MySelfAdResponse.HomeAppListBean> I = new ArrayList();
  public static List<MySelfAdResponse.ShareAppListBean> J = new ArrayList();
  public static boolean K = false;
  public static String L = "zh-CN";
  public static String M = "ar";
  public static boolean N = false;
  public static boolean O = false;
  public static boolean P = false;
  public static boolean Q = false;
  public static boolean R = false;
  public static boolean S = true;
  public static boolean T = true;
  public static boolean U = true;
  public static boolean V = true;
  public static String W = ar;
  public static String X = "4835";
  public static int a = 0;
  private static Map<String, Map<String, String>> aA;
  private static Boolean aB;
  private static boolean aC = false;
  private static boolean aD = false;
  private static long aE = 0L;
  private static Tracker aF;
  public static boolean ak = false;
  public static boolean al = false;
  public static boolean am = false;
  public static boolean ap = true;
  private static String aq;
  private static String ar;
  private static VideoEditorApplication as;
  private static Context at;
  private static String au;
  private static String av;
  private static String aw;
  private static String ax;
  private static Map<String, Typeface> ay;
  private static List<com.xvideostudio.videoeditor.g.u> az;
  public static int b = 0;
  public static float c = 0.0F;
  public static Boolean d = Boolean.valueOf(false);
  public static Boolean e = Boolean.valueOf(false);
  public static String f = "";
  public static String g = "";
  public static int h = 1496;
  public static String i = "7.0.0";
  public static boolean j = false;
  public static boolean k = false;
  public static boolean l = true;
  public static boolean m = false;
  public static boolean n = true;
  public static String o;
  public static String p = "https://play.google";
  public static String q;
  public static String r;
  public static String s;
  public static String t;
  public static int u = 2;
  public static Map<String, MyFontEntity> v;
  public static long w = 0L;
  public static String x = "en-US";
  public static String y;
  public static boolean z = false;
  public com.xvideostudio.videoeditor.materialdownload.c Y = null;
  public Bundle Z;
  private DraftBoxHandler aG = null;
  private org.xvideo.videoeditor.a.b aH = null;
  private org.xvideo.videoeditor.b.b aI = null;
  private PaintDraftHandler aJ = null;
  private d aK = null;
  private boolean aL = true;
  private String aM = "user_id";
  private boolean aN = false;
  private boolean aO = false;
  private Handler aP = new Handler()
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
        j.a(2131690162);
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
              l.a(VideoEditorApplication.R(), str, i);
              if (!bool1) {
                break label134;
              }
              at.a(str, localFile.getParent(), true);
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
          VideoEditorApplication.this.z();
        }
      }).start();
    }
  };
  private StoryBoardView.a aQ = null;
  private boolean aR = false;
  public boolean aa = true;
  public int ab = 1;
  public Hashtable<String, SiteInfoBean> ac = null;
  public List<String> ad = null;
  public com.xvideostudio.videoeditor.materialdownload.a ae = null;
  public boolean af = false;
  public String ag;
  public String ah;
  public Handler ai = new Handler(Looper.getMainLooper()) {};
  Map<String, Integer> aj = null;
  public boolean an = false;
  public boolean ao = false;
  
  public VideoEditorApplication() {}
  
  private static void S()
  {
    B.put("1f001", Integer.valueOf(2131231205));
    B.put("1f002", Integer.valueOf(2131231206));
    B.put("1f003", Integer.valueOf(2131231207));
    B.put("1f004", Integer.valueOf(2131231208));
    B.put("1f005", Integer.valueOf(2131231209));
    B.put("1f006", Integer.valueOf(2131231210));
    B.put("1f007", Integer.valueOf(2131231211));
    B.put("1f008", Integer.valueOf(2131231212));
    B.put("1f009", Integer.valueOf(2131231213));
    B.put("1f010", Integer.valueOf(2131231214));
    B.put("1f011", Integer.valueOf(2131231215));
    B.put("1f012", Integer.valueOf(2131231216));
    B.put("1f013", Integer.valueOf(2131231217));
    B.put("1f014", Integer.valueOf(2131231218));
    B.put("1f015", Integer.valueOf(2131231219));
    B.put("1f016", Integer.valueOf(2131231220));
    B.put("1f017", Integer.valueOf(2131231221));
    B.put("1f018", Integer.valueOf(2131231222));
    B.put("1f019", Integer.valueOf(2131231223));
    B.put("1f020", Integer.valueOf(2131231224));
    B.put("1f021", Integer.valueOf(2131231225));
    B.put("1f022", Integer.valueOf(2131231226));
    B.put("1f023", Integer.valueOf(2131231227));
    B.put("1f024", Integer.valueOf(2131231228));
    B.put("1f025", Integer.valueOf(2131231229));
    B.put("1f026", Integer.valueOf(2131231230));
    B.put("1f027", Integer.valueOf(2131231231));
    B.put("1f028", Integer.valueOf(2131231232));
    B.put("1f029", Integer.valueOf(2131231233));
    B.put("1f030", Integer.valueOf(2131231234));
    B.put("1f031", Integer.valueOf(2131231235));
    B.put("1f032", Integer.valueOf(2131231236));
    B.put("3f001", Integer.valueOf(2131231237));
    B.put("3f002", Integer.valueOf(2131231238));
    B.put("3f003", Integer.valueOf(2131231239));
    B.put("3f004", Integer.valueOf(2131231240));
    B.put("3f005", Integer.valueOf(2131231241));
    B.put("3f006", Integer.valueOf(2131231242));
    B.put("3f007", Integer.valueOf(2131231243));
    B.put("3f008", Integer.valueOf(2131231244));
    B.put("3f009", Integer.valueOf(2131231245));
    B.put("3f010", Integer.valueOf(2131231246));
    B.put("3f011", Integer.valueOf(2131231247));
    B.put("3f012", Integer.valueOf(2131231248));
    B.put("3f013", Integer.valueOf(2131231249));
    B.put("3f014", Integer.valueOf(2131231250));
    B.put("3f015", Integer.valueOf(2131231251));
    B.put("3f016", Integer.valueOf(2131231252));
    B.put("3f017", Integer.valueOf(2131231253));
    B.put("3f018", Integer.valueOf(2131231254));
    B.put("3f019", Integer.valueOf(2131231255));
    B.put("3f020", Integer.valueOf(2131231256));
    B.put("3f021", Integer.valueOf(2131231257));
    B.put("3f022", Integer.valueOf(2131231258));
    B.put("3f023", Integer.valueOf(2131231259));
    B.put("3f024", Integer.valueOf(2131231260));
  }
  
  private void T()
  {
    if (!b())
    {
      String str1 = aq;
      W = ar;
      X = "4835";
      String str2 = a(this, Process.myPid());
      if (str2 != null)
      {
        if (str2.equalsIgnoreCase(at.getPackageName()))
        {
          try
          {
            MobclickAgent.onEvent(this, "PROCESS_MAIN_APPLICATION");
          }
          catch (NoClassDefFoundError localNoClassDefFoundError)
          {
            localNoClassDefFoundError.printStackTrace();
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(at.getPackageName());
          localStringBuilder.append(":channel");
          if (str2.equalsIgnoreCase(localStringBuilder.toString()))
          {
            MobclickAgent.onEvent(this, "PROCESS_UMPUSH_APPLICATION");
          }
          else
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(at.getPackageName());
            localStringBuilder.append(":servicerewards");
            if (str2.equalsIgnoreCase(localStringBuilder.toString()))
            {
              MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDS_APPLICATION");
            }
            else
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append(at.getPackageName());
              localStringBuilder.append(":servicerewardsprot");
              if (str2.equalsIgnoreCase(localStringBuilder.toString())) {
                MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDSPROT_APPLICATION");
              }
            }
          }
        }
        if (str2.equals(at.getPackageName()))
        {
          MobclickAgent.onEvent(this, "INIT_ADS_IN_APPLICATION");
          a(str1, "e708c4bd96e9ca808eeb2e53b41d3fef");
          U();
          V();
        }
      }
    }
  }
  
  private void U()
  {
    DuAdNetwork.init(this, w());
  }
  
  private void V()
  {
    try
    {
      MobileAds.initialize(this, com.xvideostudio.videoeditor.a.a.a().b());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void W()
  {
    if (!com.xvideostudio.videoeditor.o.b.V()) {
      ap = com.xvideostudio.videoeditor.o.b.c(this);
    }
    if (af.b(this, "android.permission.WRITE_EXTERNAL_STORAGE"))
    {
      SplashActivity.a(this);
      SplashActivity.b(this);
    }
  }
  
  private void X()
  {
    Object localObject = new File(i());
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = com.xvideostudio.videoeditor.o.b.o();
    try
    {
      i((String)localObject);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private void Y()
  {
    int i1 = Integer.valueOf(com.xvideostudio.videoeditor.util.f.g()).intValue();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("the cpu frequency is ");
    localStringBuilder.append(i1);
    localStringBuilder.append("khz");
    i.b("VideoEditorApplication", localStringBuilder.toString());
    int i2 = com.xvideostudio.videoeditor.util.f.o();
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
      com.xvideostudio.videoeditor.tool.u.n(at, 1);
    }
    if (true == hl.productor.fxlib.b.D)
    {
      if (HLRenderThread.detectGraphicsPerformance() == -1)
      {
        hl.productor.fxlib.b.D = false;
        com.xvideostudio.videoeditor.tool.u.n(at, 1);
      }
      i1 = HLRenderThread.queryValue(3) / 8;
      if (i1 != 2)
      {
        if (i1 != 4) {
          return;
        }
        hl.productor.fxlib.e.c = 2;
        i.b("VideoEditorApplication", "detectGraphicsPerformance RGBA8888");
        com.xvideostudio.videoeditor.tool.u.n(at, 3);
        return;
      }
      hl.productor.fxlib.e.c = 1;
      i.b("VideoEditorApplication", "detectGraphicsPerformance RGB565");
      com.xvideostudio.videoeditor.tool.u.n(at, 2);
    }
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (a > 0) {
        return a;
      }
    }
    else if (b > 0) {
      return b;
    }
    paramContext = paramContext.getResources().getDisplayMetrics();
    a = paramContext.widthPixels;
    b = paramContext.heightPixels;
    c = paramContext.density;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("width");
    localStringBuilder.append(paramContext.widthPixels);
    i.b("cxs", localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("height");
    localStringBuilder.append(paramContext.heightPixels);
    i.b("cxs", localStringBuilder.toString());
    if (a > b)
    {
      int i1 = b;
      b = a;
      a = i1;
    }
    if (paramBoolean) {
      return a;
    }
    return b;
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
    if (B == null)
    {
      B = new HashMap(100);
      S();
    }
    Iterator localIterator = B.entrySet().iterator();
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
    if (v == null) {
      v = new LinkedHashMap();
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
          if (!VideoEditorApplication.v.containsKey(str1))
          {
            String str2 = ((Font)paramAnonymousList.get(i)).getFontName();
            try
            {
              ((Font)paramAnonymousList.get(i)).getTypeface(new VideoEditorApplication.3.1(this, str1, str2));
            }
            catch (Exception localException)
            {
              VideoEditorApplication.v.put(str1, new MyFontEntity(Typeface.DEFAULT, str2));
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
  
  private void a(String paramString1, String paramString2)
  {
    com.mintegral.msdk.f.a localA = com.mintegral.msdk.out.f.a();
    paramString1 = localA.a(paramString1, paramString2);
    paramString1.put("applicationID", "com.funcamerastudio.videomaker");
    com.mintegral.msdk.a.l = true;
    com.mintegral.msdk.a.i = false;
    localA.a(paramString1, at);
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
    if ((i1 != 0) && (com.xvideostudio.videoeditor.util.f.n(paramContext))) {
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
    if ((y == null) && (a() != null) && (a().getBaseContext() != null)) {
      y = a().getBaseContext().getPackageName();
    }
    return (!TextUtils.isEmpty(y)) && (y.equalsIgnoreCase(paramString));
  }
  
  public static Typeface b(String paramString)
  {
    if (w.a(paramString))
    {
      if (ay == null) {
        k();
      }
      if (ay.containsKey(paramString)) {
        return (Typeface)ay.get(paramString);
      }
    }
    else
    {
      if (v == null) {
        a(null);
      }
      if (v.containsKey(paramString)) {
        return ((MyFontEntity)v.get(paramString)).typeface;
      }
    }
    return Typeface.SANS_SERIF;
  }
  
  public static void b(Activity paramActivity)
  {
    if (!F.containsKey("MainActivity"))
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
      if (m())
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
      k = true;
      return true;
    }
    return false;
  }
  
  public static String c(String paramString)
  {
    if (v == null) {
      a(null);
    }
    if (v.containsKey(paramString)) {
      return ((MyFontEntity)v.get(paramString)).fontName;
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
    if (B == null)
    {
      B = new HashMap(100);
      S();
    }
    if ((B != null) && (B.containsKey(paramString))) {
      return ((Integer)B.get(paramString)).intValue();
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
      if ((g()) && (com.xvideostudio.videoeditor.tool.u.A(a()))) {
        return false;
      }
      return (f()) || (g()) || (e());
    }
    return false;
  }
  
  private void e(int paramInt)
  {
    az = new ArrayList();
    int i1 = 0;
    while (i1 < 20)
    {
      com.xvideostudio.videoeditor.g.u localU = new com.xvideostudio.videoeditor.g.u();
      localU.a = b.d[i1];
      localU.b = b.e[i1];
      localU.c = false;
      localU.d = (i1 + 4);
      localU.e = b.f[i1];
      localU.f = b.g[i1];
      localU.g = b.h[i1];
      if (paramInt == localU.d)
      {
        localU.c = true;
        hl.productor.fxlib.b.Y = false;
        hl.productor.fxlib.b.N = paramInt;
      }
      az.add(localU);
      i1 += 1;
    }
  }
  
  public static boolean e()
  {
    return false;
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
    //   0: new 1019	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 1020	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore_3
    //   14: new 1022	java/io/BufferedInputStream
    //   17: dup
    //   18: getstatic 276	com/xvideostudio/videoeditor/VideoEditorApplication:at	Landroid/content/Context;
    //   21: invokevirtual 1026	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   24: aload_1
    //   25: invokevirtual 1032	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   28: invokespecial 1035	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore_1
    //   32: sipush 4096
    //   35: newarray byte
    //   37: astore_3
    //   38: aload_1
    //   39: aload_3
    //   40: invokevirtual 1039	java/io/BufferedInputStream:read	([B)I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +14 -> 59
    //   48: aload 6
    //   50: aload_3
    //   51: iconst_0
    //   52: iload_2
    //   53: invokevirtual 1043	java/io/ByteArrayOutputStream:write	([BII)V
    //   56: goto -18 -> 38
    //   59: aload_0
    //   60: aload_1
    //   61: invokespecial 1045	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
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
    //   93: new 499	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 500	java/lang/StringBuilder:<init>	()V
    //   100: astore 5
    //   102: aload_1
    //   103: astore_3
    //   104: aload 5
    //   106: ldc_w 1047
    //   109: invokevirtual 504	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload_1
    //   114: astore_3
    //   115: aload 5
    //   117: aload 4
    //   119: invokevirtual 1050	java/io/IOException:getMessage	()Ljava/lang/String;
    //   122: invokevirtual 504	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload_1
    //   127: astore_3
    //   128: ldc_w 615
    //   131: aload 5
    //   133: invokevirtual 509	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokestatic 1052	com/xvideostudio/videoeditor/tool/i:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: aload_0
    //   140: aload_1
    //   141: invokespecial 1045	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   144: aload 6
    //   146: invokevirtual 1053	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   149: areturn
    //   150: aload_0
    //   151: aload_3
    //   152: invokespecial 1045	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
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
    if (!j) {
      return false;
    }
    return com.xvideostudio.videoeditor.tool.u.s(at, 0) != 0;
  }
  
  public static String i()
  {
    return av;
  }
  
  private void i(String paramString)
    throws IOException
  {
    paramString = new BufferedOutputStream(new FileOutputStream(paramString));
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(getResources().openRawResource(2131623956));
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
  
  public static SharedPreferences j()
  {
    return PreferenceManager.getDefaultSharedPreferences(as);
  }
  
  public static Map<String, Typeface> k()
  {
    if ((ay == null) || (ay.size() == 0))
    {
      ay = new LinkedHashMap();
      aj.a("VideoEditorApplication onCreate before new typeFace:");
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
          localMap = ay;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(i1);
          ((StringBuilder)localObject1).append("");
          localMap.put(((StringBuilder)localObject1).toString(), Typeface.SANS_SERIF);
        }
        else
        {
          try
          {
            localMap = ay;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(i1);
            ((StringBuilder)localObject1).append("");
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject2 = at.getAssets();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("font/");
            localStringBuilder.append(arrayOfString[i1]);
            localMap.put(localObject1, Typeface.createFromAsset((AssetManager)localObject2, localStringBuilder.toString()));
          }
          catch (Exception localException)
          {
            localObject1 = ay;
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(i1);
            ((StringBuilder)localObject2).append("");
            ((Map)localObject1).put(((StringBuilder)localObject2).toString(), Typeface.SANS_SERIF);
            localException.printStackTrace();
          }
        }
        i1 -= 1;
      }
      ay.put("9", Typeface.createFromAsset(at.getAssets(), "font/helvetica_inserat_lt.ttf"));
      aj.a("VideoEditorApplication onCreate after typeFace:");
    }
    return ay;
  }
  
  public static Map<String, Map<String, String>> l()
  {
    if ((aA == null) || (aA.size() == 0))
    {
      aA = new LinkedHashMap();
      new HashMap();
      Object localObject2 = new String[1];
      Object localObject1 = at.getResources().getString(2131690046);
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
        localStringBuilder.append(new int[] { 2131623946 }[i1]);
        localStringBuilder.append("");
        ((Map)localObject2).put("rawId", localStringBuilder.toString());
        ((Map)localObject2).put("duration", new String[] { "64608" }[i1]);
        ((Map)localObject2).put("musicName", localObject1[i1]);
        aA.put(new String[] { "music_new_york_love.aac" }[i1], localObject2);
        i1 += 1;
      }
    }
    return aA;
  }
  
  public static boolean m()
  {
    if (aB != null) {
      return aB.booleanValue();
    }
    if (at == null) {
      return false;
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
  
  public static String n()
  {
    if (q == null) {
      o();
    }
    return q;
  }
  
  public static void o()
  {
    if (aC) {
      return;
    }
    aC = true;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(p);
    localStringBuilder.append(".com/store/");
    o = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(o);
    localStringBuilder.append("apps/details?id=");
    q = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(q);
    localStringBuilder.append("com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshow%2520setting%26utm_medium%3Dbanner%26utm_term%3Dvideoshow%2520pro%26utm_content%3Dvideoshow%2520pro%2520for%2520setting%26utm_campaign%3Dvideoshow%2520pro%2520for%2520setting");
    t = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(q);
    localStringBuilder.append("com.xvideostudio.videoeditorpro");
    s = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(q);
    localStringBuilder.append("com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshowlabs%26utm_medium%3Dbanner%26utm_term%3Dlabs%26utm_content%3Dvideoshow%2520for%2520labs%26utm_campaign%3Dvideoshow%2520for%2520labs");
    r = localStringBuilder.toString();
    if ((y != null) && (y.length() > 0))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(q);
      localStringBuilder.append(y);
      q = localStringBuilder.toString();
      return;
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(q);
    localStringBuilder.append("com.funcamerastudio.videomaker");
    q = localStringBuilder.toString();
  }
  
  public static void p()
  {
    if (aD) {
      return;
    }
    aD = true;
    if ((g()) && (com.xvideostudio.videoeditor.tool.u.B(at) == 1)) {
      z = true;
    }
  }
  
  public static boolean q()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = aE;
      if (l1 - l2 < 1000L) {
        return true;
      }
      aE = l1;
      return false;
    }
    finally {}
  }
  
  public static boolean r()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = aE;
      if (l1 - l2 < 400L) {
        return true;
      }
      aE = l1;
      return false;
    }
    finally {}
  }
  
  public void A()
  {
    b();
    B();
  }
  
  public void B()
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
    Object localObject2 = com.xvideostudio.videoeditor.o.b.a();
    if ((localObject2 != null) && (!((String)localObject1).equalsIgnoreCase((String)localObject2)) && (!((String)localObject2).startsWith("/storage/emulated/legacy")))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Sd2 path:");
      ((StringBuilder)localObject1).append((String)localObject2);
      i.b("cxs", ((StringBuilder)localObject1).toString());
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append(File.separator);
      ((StringBuilder)localObject1).append(com.xvideostudio.videoeditor.o.b.f);
      ax = ((StringBuilder)localObject1).toString();
      l.b(ax);
      j = true;
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(ax);
        ((StringBuilder)localObject1).append(al.a());
        ((StringBuilder)localObject1).append(".test");
        localObject1 = new File(((StringBuilder)localObject1).toString());
        ((File)localObject1).createNewFile();
        ((File)localObject1).delete();
      }
      catch (Exception localException)
      {
        j = false;
        localException.printStackTrace();
      }
    }
    au = com.xvideostudio.videoeditor.o.b.t();
    av = com.xvideostudio.videoeditor.o.b.e();
    aw = com.xvideostudio.videoeditor.o.b.d();
    if ((!j) && (h() == true)) {
      a(false);
    }
  }
  
  public DraftBoxHandler C()
  {
    if (this.aG == null) {
      this.aG = new DraftBoxHandler();
    }
    return this.aG;
  }
  
  public org.xvideo.videoeditor.a.b D()
  {
    if (this.aH == null) {
      this.aH = new org.xvideo.videoeditor.a.b(getApplicationContext());
    }
    return this.aH;
  }
  
  public org.xvideo.videoeditor.b.b E()
  {
    if (this.aI == null) {
      this.aI = new org.xvideo.videoeditor.b.b(getApplicationContext());
    }
    return this.aI;
  }
  
  public void F()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    this.aP.sendMessage(localMessage);
  }
  
  public void G()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public boolean H()
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
                  MobclickAgent.onEvent(at, "AVC_ENCODER_CHECK_OMX_SPRD_H264");
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
      MobclickAgent.onEvent(at, "AVC_ENCODER_CHECK_H264_HW_ENCODE_SUPPORT");
      return true;
    }
    MobclickAgent.onEvent(at, "AVC_ENCODER_CHECK_H264_HW_ENCODE_CANNOT_SUPPORT");
    return false;
  }
  
  public void I()
  {
    Object localObject = com.xvideostudio.videoeditor.util.f.m();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("cpu Architecture is ");
    localStringBuilder.append((String)localObject);
    i.b("VideoEditorApplication", localStringBuilder.toString());
    com.xvideostudio.videoeditor.util.f.d();
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
    int i1 = Math.min(com.xvideostudio.videoeditor.util.f.k(at), com.xvideostudio.videoeditor.util.f.l(at));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("screenWidth = ");
    ((StringBuilder)localObject).append(i1);
    i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    if (i1 > 1446)
    {
      hl.productor.fxlib.b.g = i1;
      hl.productor.fxlib.b.h = i1;
    }
    C = com.xvideostudio.videoeditor.util.f.v();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cameraMaxSize[0] = ");
    ((StringBuilder)localObject).append(C[0]);
    i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cameraMaxSize[1] = ");
    ((StringBuilder)localObject).append(C[1]);
    i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > com.xvideostudio.videoeditor.util.f.k(at) * com.xvideostudio.videoeditor.util.f.l(at))
    {
      hl.productor.fxlib.b.e = Math.min(com.xvideostudio.videoeditor.util.f.k(at), com.xvideostudio.videoeditor.util.f.l(at));
      hl.productor.fxlib.b.f = Math.max(com.xvideostudio.videoeditor.util.f.k(at), com.xvideostudio.videoeditor.util.f.l(at));
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Max output size restricted to ");
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.util.f.k(at));
      ((StringBuilder)localObject).append(" x ");
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.util.f.l(at));
      i.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    }
    if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > C[0] * C[1])
    {
      hl.productor.fxlib.b.e = Math.min(C[0], C[1]);
      hl.productor.fxlib.b.f = Math.max(C[0], C[1]);
    }
    if (((com.xvideostudio.videoeditor.util.f.k(at) * com.xvideostudio.videoeditor.util.f.l(at) <= 384000) && (com.xvideostudio.videoeditor.util.f.k(at) * com.xvideostudio.videoeditor.util.f.l(at) == C[0] * C[1])) || (C[0] * C[1] < 384000))
    {
      i.b("VideoEditorApplication", "special machine , we need disable video_hw_encode_enable  ");
      hl.productor.fxlib.b.w = false;
      hl.productor.fxlib.b.z = false;
      MobclickAgent.onEvent(this, "EXPORT_FORCE_SET_HW_ENCODE_DISABLE");
    }
    if (com.xvideostudio.videoeditor.util.f.o() >= 4)
    {
      i.b("VideoEditorApplication", "4-core   render_target_scale_mode_enabled can be false");
      hl.productor.fxlib.b.J = false;
    }
    else
    {
      hl.productor.fxlib.b.J = true;
    }
    if (C[2] == 1)
    {
      if ((a * b <= 2073600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > C[0] * C[1]))
      {
        hl.productor.fxlib.b.ae = Math.min(C[0], C[1]);
        hl.productor.fxlib.b.af = Math.max(C[0], C[1]);
      }
      hl.productor.fxlib.b.ag = true;
    }
    else if ((a * b <= 921600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > C[0] * C[1]))
    {
      hl.productor.fxlib.b.ae = Math.min(C[0], C[1]);
      hl.productor.fxlib.b.af = Math.max(C[0], C[1]);
    }
    hl.productor.fxlib.b.ad = 1;
  }
  
  void J()
  {
    switch (com.xvideostudio.videoeditor.tool.u.o(at, 0))
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
    Y();
  }
  
  public void K()
  {
    if (this.aR) {
      return;
    }
    this.aR = true;
  }
  
  public List<com.xvideostudio.videoeditor.g.u> L()
  {
    if (az == null) {
      e(com.xvideostudio.videoeditor.tool.u.u(a(), 3));
    }
    return az;
  }
  
  public boolean M()
  {
    return g();
  }
  
  public boolean N()
  {
    return a("com.funcamerastudio.videoeditor");
  }
  
  public boolean O()
  {
    return a("com.bestvideostudio.movieeditor");
  }
  
  public boolean P()
  {
    return a("com.videoeditor.videomaker.photos.music.pictures");
  }
  
  public boolean Q()
  {
    return a("com.videomaker.editor.slideshow.songs.record.album");
  }
  
  public void a(final int paramInt, final MediaClip paramMediaClip)
  {
    this.aP.post(new Runnable()
    {
      public void run()
      {
        if (paramInt == 0)
        {
          if (VideoEditorApplication.b(VideoEditorApplication.this) != null)
          {
            paramMediaClip.rotation = 64537;
            VideoEditorApplication.b(VideoEditorApplication.this).b(paramMediaClip);
          }
          j.a(VideoEditorApplication.this.getResources().getString(2131690383), -1, 1);
          return;
        }
        if (VideoEditorApplication.b(VideoEditorApplication.this) != null) {
          VideoEditorApplication.b(VideoEditorApplication.this).a(paramMediaClip);
        }
      }
    });
  }
  
  public void a(StoryBoardView.a paramA)
  {
    this.aQ = paramA;
  }
  
  public void a(String paramString, int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    Bundle localBundle = new Bundle();
    localBundle.putInt("duration", paramInt);
    localBundle.putString("text", paramString);
    localMessage.setData(localBundle);
    this.aP.sendMessage(localMessage);
  }
  
  public void a(String paramString, ImageView paramImageView, com.c.a.b.c paramC)
  {
    com.c.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aK == null)
    {
      this.aK = d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.o.b.w());
    }
    this.aK.a(paramString, paramImageView, localC);
  }
  
  public void a(String paramString, ImageView paramImageView, com.c.a.b.c paramC, com.c.a.b.f.c paramC1)
  {
    this.aK.a(paramString, paramImageView, paramC, paramC1);
  }
  
  public void a(String paramString, com.c.a.b.c paramC, com.c.a.b.f.a paramA)
  {
    com.c.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aK == null)
    {
      this.aK = d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.o.b.w());
    }
    this.aK.a(paramString, localC, paramA);
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
      if (!com.xvideostudio.videoeditor.tool.u.d(at, com.xvideostudio.videoeditor.util.f.d(at)))
      {
        if ((paramBoolean1) && (paramBoolean2)) {
          X();
        }
        String str;
        Object localObject1;
        Object localObject2;
        Object localObject3;
        if (paramBoolean1)
        {
          str = com.xvideostudio.videoeditor.o.b.C();
          l.b(str);
          localObject1 = l().keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localObject2 = (Map)l().get(localObject2);
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
              this.aP.sendMessage((Message)localObject3);
            }
          }
        }
        if (paramBoolean2)
        {
          str = com.xvideostudio.videoeditor.o.b.E();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623957);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("theme_new_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aP.sendMessage((Message)localObject1);
        }
        if (paramBoolean3)
        {
          str = com.xvideostudio.videoeditor.o.b.G();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623958);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("trans_new_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aP.sendMessage((Message)localObject1);
        }
        if (paramBoolean4)
        {
          str = com.xvideostudio.videoeditor.o.b.H();
          l.b(str);
          localObject1 = new Message();
          ((Message)localObject1).what = 1;
          localObject2 = new Bundle();
          ((Bundle)localObject2).putInt("rawId", 2131623955);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append("subtitle_style_temp.zip");
          ((Bundle)localObject2).putString("rawFilePath", ((StringBuilder)localObject3).toString());
          ((Bundle)localObject2).putBoolean("isZip", true);
          ((Message)localObject1).setData((Bundle)localObject2);
          this.aP.sendMessage((Message)localObject1);
        }
        if (paramBoolean5)
        {
          str = com.xvideostudio.videoeditor.o.b.J();
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
          this.aP.sendMessage((Message)localObject1);
        }
        com.xvideostudio.videoeditor.tool.u.a(at, true, com.xvideostudio.videoeditor.util.f.d(at));
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    com.xvideostudio.videoeditor.util.a.a.f(paramContext);
    super.attachBaseContext(com.xvideostudio.videoeditor.util.a.a.d(paramContext));
  }
  
  public void b(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          com.xvideostudio.videoeditor.control.b.a(MainActivity.a(VideoEditorApplication.R()), 2, new VideoEditorApplication.7.1(this));
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public void b(String paramString, ImageView paramImageView, com.c.a.b.c paramC)
  {
    if (this.aK == null)
    {
      this.aK = d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.o.b.w());
    }
    d localD = this.aK;
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
      com.xvideostudio.videoeditor.g.u localU = (com.xvideostudio.videoeditor.g.u)L().get(i1);
      if (paramInt < 4)
      {
        if (localU.c)
        {
          localU.c = false;
          az.set(i1, localU);
        }
      }
      else if (paramInt == localU.d) {
        localU.c = true;
      } else {
        localU.c = false;
      }
      az.set(i1, localU);
      i1 += 1;
    }
  }
  
  public com.xvideostudio.videoeditor.g.u d(int paramInt)
  {
    int i1 = 0;
    while (i1 < 20)
    {
      com.xvideostudio.videoeditor.g.u localU = (com.xvideostudio.videoeditor.g.u)az.get(i1);
      if ((paramInt >= 4) && (paramInt == localU.d)) {
        return localU;
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
    this.ag = paramString;
  }
  
  public void g(String paramString)
  {
    this.ah = paramString;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    com.xvideostudio.videoeditor.util.a.a.g(getApplicationContext());
  }
  
  public void onCreate()
  {
    super.onCreate();
    W();
    at = this;
    as = this;
    y = getPackageName();
    try
    {
      try
      {
        i.a(at);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      for (;;) {}
    }
    aq = com.xvideostudio.videoeditor.a.a.a().c();
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
    x();
    if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(a()) == 0)
    {
      this.aL = false;
      if (Tools.b(a())) {
        j.a("FireBase推送可用，屏蔽友盟推送");
      }
    }
    else
    {
      if (Tools.b(a())) {
        j.a("FireBase推送不可用，使用友盟推送");
      }
      this.aL = true;
    }
    if (c.K(at) == 0L)
    {
      com.xvideostudio.videoeditor.util.c.a(at);
      c.k(this, Boolean.valueOf(false));
      c.a(at, System.currentTimeMillis());
    }
  }
  
  public com.xvideostudio.videoeditor.materialdownload.c s()
  {
    if (this.Y == null) {
      this.Y = new com.xvideostudio.videoeditor.materialdownload.c(at);
    }
    return this.Y;
  }
  
  public Hashtable<String, SiteInfoBean> t()
  {
    if (this.ac == null) {
      this.ac = new Hashtable();
    }
    return this.ac;
  }
  
  public List<String> u()
  {
    if (this.ad == null) {
      this.ad = new ArrayList();
    }
    return this.ad;
  }
  
  public Map<String, Integer> v()
  {
    if (this.aj == null) {
      this.aj = new Hashtable();
    }
    return this.aj;
  }
  
  public String w()
  {
    return h("baiduId/normal_id.json");
  }
  
  public void x()
  {
    if (this.aO) {
      return;
    }
    this.aO = true;
    aj.a("VideoEditorApplication onCreate before:");
    x = com.xvideostudio.videoeditor.util.f.q(at);
    try
    {
      PackageInfo localPackageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
      y = localPackageInfo.packageName;
      h = localPackageInfo.versionCode;
      i = localPackageInfo.versionName;
      if ((y == null) || (y.length() == 0)) {
        y = "com.funcamerastudio.videomaker";
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
    this.aK = d.a();
    r.a(getApplicationContext(), com.xvideostudio.videoeditor.o.b.w());
    L = com.xvideostudio.videoeditor.util.f.q(at);
    i.b("language", L);
    i.b("language", L.substring(0, 2));
    T();
    this.aM = am.a(this);
  }
  
  public void y()
  {
    if (this.aN) {
      return;
    }
    this.aN = true;
    try
    {
      FontCenter.getInstance().setFolder_font(com.xvideostudio.videoeditor.o.b.M());
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        an.a().a(VideoEditorApplication.R());
        VideoEditorApplication.this.A();
        VideoEditorApplication.a(VideoEditorApplication.this).sendEmptyMessageDelayed(0, 10L);
        if (com.xvideostudio.videoeditor.tool.u.I(VideoEditorApplication.a()))
        {
          MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_START_APP");
          i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_START_APP");
          if (!com.xvideostudio.videoeditor.tool.u.H(VideoEditorApplication.R()))
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("HW_ENCODER_ERR errTime:");
            localStringBuilder.append(com.xvideostudio.videoeditor.tool.u.J(VideoEditorApplication.a()));
            localStringBuilder.append(" errResetTime:");
            localStringBuilder.append(com.xvideostudio.videoeditor.tool.u.K(VideoEditorApplication.a()));
            i.b("VideoEditorApplication", localStringBuilder.toString());
            if (com.xvideostudio.videoeditor.tool.u.K(VideoEditorApplication.a()) < 3)
            {
              if (com.xvideostudio.videoeditor.tool.u.J(VideoEditorApplication.a()) % 5 == 0)
              {
                com.xvideostudio.videoeditor.tool.u.l(VideoEditorApplication.R(), false);
                com.xvideostudio.videoeditor.tool.u.F(VideoEditorApplication.a(), 0);
                if ((com.xvideostudio.videoeditor.util.f.d() >= 18) && (VideoEditorApplication.this.H()))
                {
                  hl.productor.fxlib.b.w = true;
                  hl.productor.fxlib.b.z = true;
                }
                com.xvideostudio.videoeditor.tool.u.E(VideoEditorApplication.a(), com.xvideostudio.videoeditor.tool.u.K(VideoEditorApplication.a()) + 1);
                i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
              }
              else
              {
                com.xvideostudio.videoeditor.tool.u.F(VideoEditorApplication.a(), com.xvideostudio.videoeditor.tool.u.J(VideoEditorApplication.a()) + 1);
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
            com.xvideostudio.videoeditor.tool.u.l(VideoEditorApplication.R(), false);
            com.xvideostudio.videoeditor.tool.u.F(VideoEditorApplication.a(), 0);
            if ((com.xvideostudio.videoeditor.util.f.d() >= 18) && (VideoEditorApplication.this.H()))
            {
              hl.productor.fxlib.b.w = true;
              hl.productor.fxlib.b.z = true;
            }
          }
        }
        else if (com.xvideostudio.videoeditor.util.f.d() >= 18)
        {
          if (VideoEditorApplication.this.H())
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
        VideoEditorApplication.this.I();
        int i = VideoEditorApplication.a;
        int j = VideoEditorApplication.b;
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
          VideoEditorApplication.this.G();
        }
        if (hl.productor.fxlib.b.D == true) {
          VideoEditorApplication.this.J();
        } else {
          hl.productor.fxlib.b.E = false;
        }
        if (hl.productor.fxlib.b.E)
        {
          i = com.xvideostudio.videoeditor.tool.u.m(VideoEditorApplication.R(), hl.productor.fxlib.b.D ^ true);
          if ((i == 0) && (!hl.productor.fxlib.b.D))
          {
            com.xvideostudio.videoeditor.tool.u.l(VideoEditorApplication.R(), 1);
            return;
          }
          if ((i == 1) && (hl.productor.fxlib.b.D)) {
            com.xvideostudio.videoeditor.tool.u.l(VideoEditorApplication.R(), 0);
          }
        }
      }
    }).start();
  }
  
  public void z()
  {
    o();
    aj.a("VideoEditorApplication onCreate after:");
    S();
    if (!l.a(com.xvideostudio.videoeditor.o.c.f(com.xvideostudio.videoeditor.o.c.f(1), 6))) {
      com.xvideostudio.videoeditor.tool.u.a(at, false, com.xvideostudio.videoeditor.util.f.d(at));
    } else if (!new File(com.xvideostudio.videoeditor.o.b.H()).isDirectory()) {
      com.xvideostudio.videoeditor.tool.u.a(at, false, com.xvideostudio.videoeditor.util.f.d(at));
    } else if (!new File(com.xvideostudio.videoeditor.o.b.J()).isDirectory()) {
      com.xvideostudio.videoeditor.tool.u.a(at, false, com.xvideostudio.videoeditor.util.f.d(at));
    }
    a(true, true, true, true, true);
    int i1 = com.xvideostudio.videoeditor.tool.u.u(a(), 3);
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
      k();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.o.b.f());
      ((StringBuilder)localObject).append("1.png");
      localObject = ((StringBuilder)localObject).toString();
      if (!l.a((String)localObject)) {
        l.a(at, 2131623959, (String)localObject);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    if (g())
    {
      i1 = com.xvideostudio.videoeditor.tool.u.B(at);
      if (i1 == 1)
      {
        z = true;
        return;
      }
      if (i1 == 2) {
        b(1);
      }
    }
  }
}
