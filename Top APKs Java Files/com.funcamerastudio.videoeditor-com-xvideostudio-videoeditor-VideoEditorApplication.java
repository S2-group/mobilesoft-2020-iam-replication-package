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
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.Toast;
import com.c.a.b.d;
import com.duapps.ad.base.DuAdNetwork;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GoogleApiAvailability;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.xinmei365.fontsdk.FontCenter;
import com.xinmei365.fontsdk.bean.FailureInfo;
import com.xinmei365.fontsdk.bean.Font;
import com.xinmei365.fontsdk.callback.FontScanCallBack;
import com.xinmei365.fontsdk.callback.FontTypefaceCallBack;
import com.xvideostudio.VsCommunity.Api.VscUserinfoSession;
import com.xvideostudio.videoeditor.activity.MainActivity;
import com.xvideostudio.videoeditor.activity.MarketUrlRedirectActivity;
import com.xvideostudio.videoeditor.activity.Tools;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.HomeAppListBean;
import com.xvideostudio.videoeditor.bean.MySelfAdResponse.ShareAppListBean;
import com.xvideostudio.videoeditor.control.f.a;
import com.xvideostudio.videoeditor.d.m;
import com.xvideostudio.videoeditor.e.u;
import com.xvideostudio.videoeditor.gsonentity.SiteInfoBean;
import com.xvideostudio.videoeditor.tool.j;
import com.xvideostudio.videoeditor.tool.k;
import com.xvideostudio.videoeditor.tool.y;
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
  public static String A;
  public static boolean B = false;
  public static boolean C = false;
  public static Boolean D = Boolean.valueOf(false);
  public static HashMap<String, Integer> E = new HashMap(100);
  public static int[] F;
  public static com.xvideostudio.videoeditor.j.a G;
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
  private static Map<String, Typeface> aA;
  private static List<u> aB;
  private static Map<String, Map<String, String>> aC;
  private static Boolean aD;
  private static boolean aE = false;
  private static boolean aF = false;
  private static long aG = 0L;
  private static Tracker aH;
  public static String aa = at;
  public static String ab = "4835";
  public static boolean ap = false;
  public static boolean aq = false;
  public static boolean ar = false;
  private static String as;
  private static String at;
  private static VideoEditorApplication au;
  private static Context av;
  private static String aw;
  private static String ax;
  private static String ay;
  private static String az;
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
  private DraftBoxHandler aI = null;
  private org.xvideo.videoeditor.a.b aJ = null;
  private org.xvideo.videoeditor.b.b aK = null;
  private PaintDraftHandler aL = null;
  private d aM = null;
  private boolean aN = true;
  private String aO = "user_id";
  private boolean aP = false;
  private boolean aQ = false;
  private boolean aR = false;
  private Handler aS = new Handler()
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
        k.a(paramAnonymousMessage.getString("text"), 1, i);
        return;
      case 2: 
        k.a(2131690327);
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
              l.a(VideoEditorApplication.U(), str, i);
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
  private StoryBoardView.a aT = null;
  private boolean aU = false;
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
  
  private static void V()
  {
    E.put("1f001", Integer.valueOf(2131231192));
    E.put("1f002", Integer.valueOf(2131231193));
    E.put("1f003", Integer.valueOf(2131231194));
    E.put("1f004", Integer.valueOf(2131231195));
    E.put("1f005", Integer.valueOf(2131231196));
    E.put("1f006", Integer.valueOf(2131231197));
    E.put("1f007", Integer.valueOf(2131231198));
    E.put("1f008", Integer.valueOf(2131231199));
    E.put("1f009", Integer.valueOf(2131231200));
    E.put("1f010", Integer.valueOf(2131231201));
    E.put("1f011", Integer.valueOf(2131231202));
    E.put("1f012", Integer.valueOf(2131231203));
    E.put("1f013", Integer.valueOf(2131231204));
    E.put("1f014", Integer.valueOf(2131231205));
    E.put("1f015", Integer.valueOf(2131231206));
    E.put("1f016", Integer.valueOf(2131231207));
    E.put("1f017", Integer.valueOf(2131231208));
    E.put("1f018", Integer.valueOf(2131231209));
    E.put("1f019", Integer.valueOf(2131231210));
    E.put("1f020", Integer.valueOf(2131231211));
    E.put("1f021", Integer.valueOf(2131231212));
    E.put("1f022", Integer.valueOf(2131231213));
    E.put("1f023", Integer.valueOf(2131231214));
    E.put("1f024", Integer.valueOf(2131231215));
    E.put("1f025", Integer.valueOf(2131231216));
    E.put("1f026", Integer.valueOf(2131231217));
    E.put("1f027", Integer.valueOf(2131231218));
    E.put("1f028", Integer.valueOf(2131231219));
    E.put("1f029", Integer.valueOf(2131231220));
    E.put("1f030", Integer.valueOf(2131231221));
    E.put("1f031", Integer.valueOf(2131231222));
    E.put("1f032", Integer.valueOf(2131231223));
    E.put("3f001", Integer.valueOf(2131231224));
    E.put("3f002", Integer.valueOf(2131231225));
    E.put("3f003", Integer.valueOf(2131231226));
    E.put("3f004", Integer.valueOf(2131231227));
    E.put("3f005", Integer.valueOf(2131231228));
    E.put("3f006", Integer.valueOf(2131231229));
    E.put("3f007", Integer.valueOf(2131231230));
    E.put("3f008", Integer.valueOf(2131231231));
    E.put("3f009", Integer.valueOf(2131231232));
    E.put("3f010", Integer.valueOf(2131231233));
    E.put("3f011", Integer.valueOf(2131231234));
    E.put("3f012", Integer.valueOf(2131231235));
    E.put("3f013", Integer.valueOf(2131231236));
    E.put("3f014", Integer.valueOf(2131231237));
    E.put("3f015", Integer.valueOf(2131231238));
    E.put("3f016", Integer.valueOf(2131231239));
    E.put("3f017", Integer.valueOf(2131231240));
    E.put("3f018", Integer.valueOf(2131231241));
    E.put("3f019", Integer.valueOf(2131231242));
    E.put("3f020", Integer.valueOf(2131231243));
    E.put("3f021", Integer.valueOf(2131231244));
    E.put("3f022", Integer.valueOf(2131231245));
    E.put("3f023", Integer.valueOf(2131231246));
    E.put("3f024", Integer.valueOf(2131231247));
  }
  
  private void W()
  {
    if (!b())
    {
      Object localObject1 = as;
      aa = at;
      ab = "4835";
      Object localObject2 = a(this, Process.myPid());
      if (localObject2 != null)
      {
        if (((String)localObject2).equalsIgnoreCase(av.getPackageName()))
        {
          MobclickAgent.onEvent(this, "PROCESS_MAIN_APPLICATION");
        }
        else
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(av.getPackageName());
          localStringBuilder.append(":channel");
          if (((String)localObject2).equalsIgnoreCase(localStringBuilder.toString()))
          {
            MobclickAgent.onEvent(this, "PROCESS_UMPUSH_APPLICATION");
          }
          else
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append(av.getPackageName());
            localStringBuilder.append(":servicerewards");
            if (((String)localObject2).equalsIgnoreCase(localStringBuilder.toString()))
            {
              MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDS_APPLICATION");
            }
            else
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append(av.getPackageName());
              localStringBuilder.append(":servicerewardsprot");
              if (((String)localObject2).equalsIgnoreCase(localStringBuilder.toString())) {
                MobclickAgent.onEvent(this, "PROCESS_SERVICEREWARDSPROT_APPLICATION");
              }
            }
          }
        }
        if (((String)localObject2).equals(av.getPackageName()))
        {
          MobclickAgent.onEvent(this, "INIT_ADS_IN_APPLICATION");
          localObject2 = com.mintegral.msdk.out.f.a();
          localObject1 = ((com.mintegral.msdk.b)localObject2).a((String)localObject1, "e708c4bd96e9ca808eeb2e53b41d3fef");
          ((Map)localObject1).put("applicationID", "com.funcamerastudio.videomaker");
          com.mintegral.msdk.a.l = true;
          com.mintegral.msdk.a.i = false;
          ((com.mintegral.msdk.b)localObject2).a((Map)localObject1, av);
          X();
          Y();
        }
      }
    }
  }
  
  private void X()
  {
    DuAdNetwork.init(this, w());
  }
  
  private void Y()
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
  
  private void Z()
  {
    Object localObject = new File(i());
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = com.xvideostudio.videoeditor.j.b.o();
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
    j.b("cxs", localStringBuilder.toString());
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("height");
    localStringBuilder.append(paramContext.heightPixels);
    j.b("cxs", localStringBuilder.toString());
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
    if (au == null) {
      au = new VideoEditorApplication();
    }
    return au;
  }
  
  public static String a(int paramInt)
  {
    if (E == null)
    {
      E = new HashMap(100);
      V();
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
        j.a("", paramAnonymousString);
      }
      
      public void onSuccess(List<Font> paramAnonymousList)
      {
        int i = 0;
        while (i < paramAnonymousList.size())
        {
          final String str1 = t.a(((Font)paramAnonymousList.get(i)).getFontLocalPath());
          if (!VideoEditorApplication.x.containsKey(str1))
          {
            final String str2 = ((Font)paramAnonymousList.get(i)).getFontName();
            try
            {
              ((Font)paramAnonymousList.get(i)).getTypeface(new FontTypefaceCallBack()
              {
                public void onFailure(FailureInfo paramAnonymous2FailureInfo)
                {
                  VideoEditorApplication.x.put(str1, new MyFontEntity(Typeface.DEFAULT, str2));
                }
                
                public void onSuccess(String paramAnonymous2String, Typeface paramAnonymous2Typeface)
                {
                  VideoEditorApplication.x.put(str1, new MyFontEntity(paramAnonymous2Typeface, str2));
                }
              });
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
    j.b("VideoEditorApplication", localStringBuilder.toString());
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
    j.b("VideoEditorApplication", paramContext.toString());
    return bool1;
  }
  
  public static boolean a(String paramString)
  {
    if ((A == null) && (a() != null) && (a().getBaseContext() != null)) {
      A = a().getBaseContext().getPackageName();
    }
    return (!TextUtils.isEmpty(A)) && (A.equalsIgnoreCase(paramString));
  }
  
  private void aa()
  {
    int i1 = Integer.valueOf(com.xvideostudio.videoeditor.util.f.g()).intValue();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("the cpu frequency is ");
    localStringBuilder.append(i1);
    localStringBuilder.append("khz");
    j.b("VideoEditorApplication", localStringBuilder.toString());
    int i2 = com.xvideostudio.videoeditor.util.f.o();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("cpuCoreNums is ");
    localStringBuilder.append(i2);
    j.b("VideoEditorApplication", localStringBuilder.toString());
    if (i2 < 2) {
      hl.productor.fxlib.b.D = false;
    } else if ((i2 == 2) && (i1 <= 1000000)) {
      hl.productor.fxlib.b.D = false;
    } else {
      hl.productor.fxlib.b.D = true;
    }
    if (!hl.productor.fxlib.b.D) {
      y.n(av, 1);
    }
    if (true == hl.productor.fxlib.b.D)
    {
      if (HLRenderThread.detectGraphicsPerformance() == -1)
      {
        hl.productor.fxlib.b.D = false;
        y.n(av, 1);
      }
      i1 = HLRenderThread.queryValue(3) / 8;
      if (i1 != 2)
      {
        if (i1 != 4) {
          return;
        }
        hl.productor.fxlib.e.c = 2;
        j.b("VideoEditorApplication", "detectGraphicsPerformance RGBA8888");
        y.n(av, 3);
        return;
      }
      hl.productor.fxlib.e.c = 1;
      j.b("VideoEditorApplication", "detectGraphicsPerformance RGB565");
      y.n(av, 2);
    }
  }
  
  public static Typeface b(String paramString)
  {
    if (w.a(paramString))
    {
      if (aA == null) {
        k();
      }
      if (aA.containsKey(paramString)) {
        return (Typeface)aA.get(paramString);
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
      V();
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
      if ((g()) && (y.z(a()))) {
        return false;
      }
      return (f()) || (g()) || (e());
    }
    return false;
  }
  
  private void e(int paramInt)
  {
    aB = new ArrayList();
    int i1 = 0;
    while (i1 < 20)
    {
      u localU = new u();
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
      aB.add(localU);
      i1 += 1;
    }
  }
  
  public static boolean e()
  {
    return false;
  }
  
  public static boolean e(String paramString)
  {
    if (av == null) {
      return false;
    }
    Iterator localIterator = av.getPackageManager().getInstalledPackages(0).iterator();
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
    //   0: new 1002	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 1003	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore_3
    //   14: new 1005	java/io/BufferedInputStream
    //   17: dup
    //   18: getstatic 288	com/xvideostudio/videoeditor/VideoEditorApplication:av	Landroid/content/Context;
    //   21: invokevirtual 1009	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   24: aload_1
    //   25: invokevirtual 1015	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   28: invokespecial 1018	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   31: astore_1
    //   32: sipush 4096
    //   35: newarray byte
    //   37: astore_3
    //   38: aload_1
    //   39: aload_3
    //   40: invokevirtual 1022	java/io/BufferedInputStream:read	([B)I
    //   43: istore_2
    //   44: iload_2
    //   45: ifle +14 -> 59
    //   48: aload 6
    //   50: aload_3
    //   51: iconst_0
    //   52: iload_2
    //   53: invokevirtual 1026	java/io/ByteArrayOutputStream:write	([BII)V
    //   56: goto -18 -> 38
    //   59: aload_0
    //   60: aload_1
    //   61: invokespecial 1028	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
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
    //   93: new 503	java/lang/StringBuilder
    //   96: dup
    //   97: invokespecial 504	java/lang/StringBuilder:<init>	()V
    //   100: astore 5
    //   102: aload_1
    //   103: astore_3
    //   104: aload 5
    //   106: ldc_w 1030
    //   109: invokevirtual 508	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload_1
    //   114: astore_3
    //   115: aload 5
    //   117: aload 4
    //   119: invokevirtual 1033	java/io/IOException:getMessage	()Ljava/lang/String;
    //   122: invokevirtual 508	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: pop
    //   126: aload_1
    //   127: astore_3
    //   128: ldc_w 778
    //   131: aload 5
    //   133: invokevirtual 513	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokestatic 1035	com/xvideostudio/videoeditor/tool/j:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: aload_0
    //   140: aload_1
    //   141: invokespecial 1028	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
    //   144: aload 6
    //   146: invokevirtual 1036	java/io/ByteArrayOutputStream:toString	()Ljava/lang/String;
    //   149: areturn
    //   150: aload_0
    //   151: aload_3
    //   152: invokespecial 1028	com/xvideostudio/videoeditor/VideoEditorApplication:a	(Ljava/io/Closeable;)V
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
    return y.s(av, 0) != 0;
  }
  
  public static String i()
  {
    return ax;
  }
  
  private void i(String paramString)
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
  
  public static SharedPreferences j()
  {
    return PreferenceManager.getDefaultSharedPreferences(au);
  }
  
  public static Map<String, Typeface> k()
  {
    if ((aA == null) || (aA.size() == 0))
    {
      aA = new LinkedHashMap();
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
          localMap = aA;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(i1);
          ((StringBuilder)localObject1).append("");
          localMap.put(((StringBuilder)localObject1).toString(), Typeface.SANS_SERIF);
        }
        else
        {
          try
          {
            localMap = aA;
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(i1);
            ((StringBuilder)localObject1).append("");
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject2 = av.getAssets();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("font/");
            localStringBuilder.append(arrayOfString[i1]);
            localMap.put(localObject1, Typeface.createFromAsset((AssetManager)localObject2, localStringBuilder.toString()));
          }
          catch (Exception localException)
          {
            localObject1 = aA;
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(i1);
            ((StringBuilder)localObject2).append("");
            ((Map)localObject1).put(((StringBuilder)localObject2).toString(), Typeface.SANS_SERIF);
            localException.printStackTrace();
          }
        }
        i1 -= 1;
      }
      aA.put("9", Typeface.createFromAsset(av.getAssets(), "font/helvetica_inserat_lt.ttf"));
      aj.a("VideoEditorApplication onCreate after typeFace:");
    }
    return aA;
  }
  
  public static Map<String, Map<String, String>> l()
  {
    if ((aC == null) || (aC.size() == 0))
    {
      aC = new LinkedHashMap();
      new HashMap();
      Object localObject2 = new String[1];
      Object localObject1 = av.getResources().getString(2131690185);
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
        aC.put(new String[] { "music_new_york_love.aac" }[i1], localObject2);
        i1 += 1;
      }
    }
    return aC;
  }
  
  public static boolean m()
  {
    if (aD != null) {
      return aD.booleanValue();
    }
    if (av == null) {
      return false;
    }
    Object localObject = av.getPackageManager().getInstalledPackages(0);
    aD = Boolean.FALSE;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equalsIgnoreCase("com.android.vending")) {
        aD = Boolean.TRUE;
      }
    }
    return aD.booleanValue();
  }
  
  public static String n()
  {
    if (s == null) {
      o();
    }
    return s;
  }
  
  public static void o()
  {
    if (aE) {
      return;
    }
    aE = true;
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
  
  public static void p()
  {
    if (aF) {
      return;
    }
    aF = true;
    if ((g()) && (y.A(av) == 1)) {
      B = true;
    }
  }
  
  public static boolean q()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = aG;
      if (l1 - l2 < 1000L) {
        return true;
      }
      aG = l1;
      return false;
    }
    finally {}
  }
  
  public static boolean r()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = aG;
      if (l1 - l2 < 400L) {
        return true;
      }
      aG = l1;
      return false;
    }
    finally {}
  }
  
  public void A()
  {
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.j.b.K());
      ((StringBuilder)localObject).append("VideoShowUserDB.db");
      localObject = ((StringBuilder)localObject).toString();
      if (!new File((String)localObject).exists())
      {
        boolean bool = l.b(com.xvideostudio.videoeditor.j.b.a(av).getAbsolutePath(), (String)localObject);
        i1 = 1;
        if (!bool)
        {
          localObject = new m(av);
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
      localObject = new m(av);
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
              try
              {
                Toast.makeText(VideoEditorApplication.U(), localException1.getMessage(), 1).show();
                return;
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
              }
            }
          });
        }
        catch (Exception localException3)
        {
          for (;;) {}
        }
        j.a("VideoEditorApplication", localException1.toString());
        j.a("VideoEditorApplication", localException1.toString());
        return;
      } while (i1 < 18);
    }
    ((m)localObject).a(((m)localObject).a(), i1, 18);
    return;
  }
  
  public void B()
  {
    b();
    C();
  }
  
  public void C()
  {
    Object localObject1 = "";
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject1 = Environment.getExternalStorageDirectory().getAbsolutePath();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Sd1 path:");
      ((StringBuilder)localObject2).append((String)localObject1);
      j.b("cxs", ((StringBuilder)localObject2).toString());
    }
    Object localObject2 = com.xvideostudio.videoeditor.j.b.a();
    if ((localObject2 != null) && (!((String)localObject1).equalsIgnoreCase((String)localObject2)) && (!((String)localObject2).startsWith("/storage/emulated/legacy")))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Sd2 path:");
      ((StringBuilder)localObject1).append((String)localObject2);
      j.b("cxs", ((StringBuilder)localObject1).toString());
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append(File.separator);
      ((StringBuilder)localObject1).append(com.xvideostudio.videoeditor.j.b.f);
      az = ((StringBuilder)localObject1).toString();
      l.b(az);
      k = true;
      try
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(az);
        ((StringBuilder)localObject1).append(al.a());
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
    aw = com.xvideostudio.videoeditor.j.b.t();
    ax = com.xvideostudio.videoeditor.j.b.e();
    ay = com.xvideostudio.videoeditor.j.b.d();
    if ((!k) && (h() == true)) {
      a(false);
    }
  }
  
  public DraftBoxHandler D()
  {
    if (this.aI == null) {
      this.aI = new DraftBoxHandler();
    }
    return this.aI;
  }
  
  public org.xvideo.videoeditor.a.b E()
  {
    if (this.aJ == null) {
      this.aJ = new org.xvideo.videoeditor.a.b(getApplicationContext());
    }
    return this.aJ;
  }
  
  public org.xvideo.videoeditor.b.b F()
  {
    if (this.aK == null) {
      this.aK = new org.xvideo.videoeditor.b.b(getApplicationContext());
    }
    return this.aK;
  }
  
  public void G()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    this.aS.sendMessage(localMessage);
  }
  
  public void H()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public boolean I()
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
          j.d("JNIMsg", ((StringBuilder)localObject).toString());
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
              j.d("JNIMsg", localStringBuilder.toString());
              int i6 = i1;
              i4 = i2;
              if (str.equalsIgnoreCase("video/avc"))
              {
                str = localMediaCodecInfo.getName();
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("AVC encoder is ");
                localStringBuilder.append(str);
                j.b("JNIMsg", localStringBuilder.toString());
                i2 += 1;
                i6 = i1;
                i4 = i2;
                if (str.equalsIgnoreCase("OMX.sprd.h264.encoder"))
                {
                  MobclickAgent.onEvent(av, "AVC_ENCODER_CHECK_OMX_SPRD_H264");
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
      MobclickAgent.onEvent(av, "AVC_ENCODER_CHECK_H264_HW_ENCODE_SUPPORT");
      return true;
    }
    MobclickAgent.onEvent(av, "AVC_ENCODER_CHECK_H264_HW_ENCODE_CANNOT_SUPPORT");
    return false;
  }
  
  public void J()
  {
    Object localObject = com.xvideostudio.videoeditor.util.f.m();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("cpu Architecture is ");
    localStringBuilder.append((String)localObject);
    j.b("VideoEditorApplication", localStringBuilder.toString());
    com.xvideostudio.videoeditor.util.f.d();
    if ((!((String)localObject).equals("armeabi-v7a")) && (!((String)localObject).equals("arm64-v8a")))
    {
      hl.productor.fxlib.b.X = false;
      hl.productor.fxlib.b.H = 0;
    }
    else
    {
      j.b("VideoEditorApplication", "H264 encoding is enabled");
      hl.productor.fxlib.b.X = true;
      hl.productor.fxlib.b.H = 1;
    }
    int i1 = Math.min(com.xvideostudio.videoeditor.util.f.k(av), com.xvideostudio.videoeditor.util.f.l(av));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("screenWidth = ");
    ((StringBuilder)localObject).append(i1);
    j.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    if (i1 > 1446)
    {
      hl.productor.fxlib.b.g = i1;
      hl.productor.fxlib.b.h = i1;
    }
    F = com.xvideostudio.videoeditor.util.f.v();
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cameraMaxSize[0] = ");
    ((StringBuilder)localObject).append(F[0]);
    j.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("cameraMaxSize[1] = ");
    ((StringBuilder)localObject).append(F[1]);
    j.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > com.xvideostudio.videoeditor.util.f.k(av) * com.xvideostudio.videoeditor.util.f.l(av))
    {
      hl.productor.fxlib.b.e = Math.min(com.xvideostudio.videoeditor.util.f.k(av), com.xvideostudio.videoeditor.util.f.l(av));
      hl.productor.fxlib.b.f = Math.max(com.xvideostudio.videoeditor.util.f.k(av), com.xvideostudio.videoeditor.util.f.l(av));
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Max output size restricted to ");
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.util.f.k(av));
      ((StringBuilder)localObject).append(" x ");
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.util.f.l(av));
      j.b("VideoEditorApplication", ((StringBuilder)localObject).toString());
    }
    if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > F[0] * F[1])
    {
      hl.productor.fxlib.b.e = Math.min(F[0], F[1]);
      hl.productor.fxlib.b.f = Math.max(F[0], F[1]);
    }
    if (((com.xvideostudio.videoeditor.util.f.k(av) * com.xvideostudio.videoeditor.util.f.l(av) <= 384000) && (com.xvideostudio.videoeditor.util.f.k(av) * com.xvideostudio.videoeditor.util.f.l(av) == F[0] * F[1])) || (F[0] * F[1] < 384000))
    {
      j.b("VideoEditorApplication", "special machine , we need disable video_hw_encode_enable  ");
      hl.productor.fxlib.b.w = false;
      hl.productor.fxlib.b.z = false;
      MobclickAgent.onEvent(this, "EXPORT_FORCE_SET_HW_ENCODE_DISABLE");
    }
    if (com.xvideostudio.videoeditor.util.f.o() >= 4)
    {
      j.b("VideoEditorApplication", "4-core   render_target_scale_mode_enabled can be false");
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
  
  void K()
  {
    switch (y.o(av, 0))
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
    aa();
  }
  
  public void L()
  {
    if (this.aU) {
      return;
    }
    this.aU = true;
  }
  
  public List<u> M()
  {
    if (aB == null) {
      e(y.u(a(), 3));
    }
    return aB;
  }
  
  public boolean N()
  {
    return g();
  }
  
  public boolean O()
  {
    return a("com.funcamerastudio.videoeditor");
  }
  
  public boolean P()
  {
    return a("com.bestvideostudio.movieeditor");
  }
  
  public boolean Q()
  {
    return a("com.videoeditor.videomaker.photos.music.pictures");
  }
  
  public boolean R()
  {
    return a("com.videomaker.editor.slideshow.songs.record.album");
  }
  
  public String S()
  {
    if (TextUtils.isEmpty(this.al)) {
      this.al = "-1000";
    }
    return this.al;
  }
  
  public String T()
  {
    if (TextUtils.isEmpty(this.al)) {
      this.al = "-2000";
    }
    return this.am;
  }
  
  public void a(final int paramInt, final MediaClip paramMediaClip)
  {
    this.aS.post(new Runnable()
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
          k.a(VideoEditorApplication.this.getResources().getString(2131690583), -1, 1);
          return;
        }
        if (VideoEditorApplication.c(VideoEditorApplication.this) != null) {
          VideoEditorApplication.c(VideoEditorApplication.this).a(paramMediaClip);
        }
      }
    });
  }
  
  public void a(StoryBoardView.a paramA)
  {
    this.aT = paramA;
  }
  
  public void a(String paramString, int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    Bundle localBundle = new Bundle();
    localBundle.putInt("duration", paramInt);
    localBundle.putString("text", paramString);
    localMessage.setData(localBundle);
    this.aS.sendMessage(localMessage);
  }
  
  public void a(String paramString, ImageView paramImageView, com.c.a.b.c paramC)
  {
    com.c.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aM == null)
    {
      this.aM = d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.j.b.w());
    }
    this.aM.a(paramString, paramImageView, localC);
  }
  
  public void a(String paramString, ImageView paramImageView, com.c.a.b.c paramC, com.c.a.b.f.c paramC1)
  {
    this.aM.a(paramString, paramImageView, paramC, paramC1);
  }
  
  public void a(String paramString, com.c.a.b.c paramC, com.c.a.b.f.a paramA)
  {
    com.c.a.b.c localC = paramC;
    if (paramC == null) {
      localC = r.a(0, true, true, true);
    }
    if (this.aM == null)
    {
      this.aM = d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.j.b.w());
    }
    this.aM.a(paramString, localC, paramA);
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
      if (!y.d(av, com.xvideostudio.videoeditor.util.f.d(av)))
      {
        if ((paramBoolean1) && (paramBoolean2)) {
          Z();
        }
        String str;
        Object localObject1;
        Object localObject2;
        Object localObject3;
        if (paramBoolean1)
        {
          str = com.xvideostudio.videoeditor.j.b.C();
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
              this.aS.sendMessage((Message)localObject3);
            }
          }
        }
        if (paramBoolean2)
        {
          str = com.xvideostudio.videoeditor.j.b.E();
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
          this.aS.sendMessage((Message)localObject1);
        }
        if (paramBoolean3)
        {
          str = com.xvideostudio.videoeditor.j.b.G();
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
          this.aS.sendMessage((Message)localObject1);
        }
        if (paramBoolean4)
        {
          str = com.xvideostudio.videoeditor.j.b.H();
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
          this.aS.sendMessage((Message)localObject1);
        }
        if (paramBoolean5)
        {
          str = com.xvideostudio.videoeditor.j.b.J();
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
          this.aS.sendMessage((Message)localObject1);
        }
        y.a(av, true, com.xvideostudio.videoeditor.util.f.d(av));
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
          com.xvideostudio.videoeditor.control.b.a(MainActivity.a(VideoEditorApplication.U()), 2, new f.a()
          {
            public void onFailed(String paramAnonymous2String)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("initLiteUnlockerProHttp=");
              localStringBuilder.append(paramAnonymous2String);
              j.b("xxw", localStringBuilder.toString());
              if (VideoEditorApplication.8.this.a == 0)
              {
                VideoEditorApplication.b(VideoEditorApplication.this).postDelayed(new Thread()
                {
                  public void run()
                  {
                    VideoEditorApplication.this.b(1);
                  }
                }, 60000L);
                return;
              }
              y.z(VideoEditorApplication.U(), 2);
              MobclickAgent.onEvent(VideoEditorApplication.U(), "LITE_UNLOCK_PRO_TIME_HTTP_FAILED");
            }
            
            public void onSuccess(Object paramAnonymous2Object)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("initLiteUnlockerProHttp=");
              localStringBuilder.append(paramAnonymous2Object);
              j.b("xxw", localStringBuilder.toString());
              if (!Boolean.parseBoolean(paramAnonymous2Object.toString()))
              {
                y.z(VideoEditorApplication.U(), 0);
                MobclickAgent.onEvent(VideoEditorApplication.U(), "LITE_UNLOCK_PRO_TIME_FAILED");
                return;
              }
              VideoEditorApplication.B = true;
              y.z(VideoEditorApplication.U(), 1);
              paramAnonymous2Object = new StringBuilder();
              paramAnonymous2Object.append(com.xvideostudio.videoeditor.j.b.K());
              paramAnonymous2Object.append(".lup.dat");
              l.a(paramAnonymous2Object.toString(), 1);
              VideoEditorApplication.b(VideoEditorApplication.this).post(new Thread()
              {
                public void run()
                {
                  k.a(2131690100, 0);
                  MobclickAgent.onEvent(VideoEditorApplication.U(), "LITE_UNLOCK_PRO_TIME_SUCCESSFUL");
                }
              });
            }
          });
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
    if (this.aM == null)
    {
      this.aM = d.a();
      r.a(getApplicationContext(), com.xvideostudio.videoeditor.j.b.w());
    }
    d localD = this.aM;
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
      u localU = (u)M().get(i1);
      if (paramInt < 4)
      {
        if (localU.c)
        {
          localU.c = false;
          aB.set(i1, localU);
        }
      }
      else if (paramInt == localU.d) {
        localU.c = true;
      } else {
        localU.c = false;
      }
      aB.set(i1, localU);
      i1 += 1;
    }
  }
  
  public u d(int paramInt)
  {
    int i1 = 0;
    while (i1 < 20)
    {
      u localU = (u)aB.get(i1);
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
    this.al = paramString;
  }
  
  public void g(String paramString)
  {
    this.am = paramString;
  }
  
  public void onCreate()
  {
    super.onCreate();
    av = this;
    au = this;
    A = getPackageName();
    try
    {
      j.a(av);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    as = com.xvideostudio.videoeditor.a.a.a().c();
    at = com.xvideostudio.videoeditor.a.b.a().b();
    VscUserinfoSession.setmApplicationContext(av);
    a(this, true);
    new AsyncTask()
    {
      protected Integer a(Object... paramAnonymousVarArgs)
      {
        return null;
      }
    };
    j.b("VideoEditorApplication", "Application start");
    this.aP = c.d(a()).booleanValue();
    x();
    if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(a()) == 0)
    {
      this.aN = false;
      if (Tools.b(a())) {
        k.a("FireBase推送可用，屏蔽友盟推送");
      }
    }
    else
    {
      if (Tools.b(a())) {
        k.a("FireBase推送不可用，使用友盟推送");
      }
      this.aN = true;
    }
    if (c.L(av) == 0L)
    {
      com.xvideostudio.videoeditor.util.c.a(av);
      c.k(this, Boolean.valueOf(false));
      c.a(av, System.currentTimeMillis());
    }
  }
  
  public com.xvideostudio.videoeditor.materialdownload.c s()
  {
    if (this.ac == null) {
      this.ac = new com.xvideostudio.videoeditor.materialdownload.c(av);
    }
    return this.ac;
  }
  
  public Hashtable<String, SiteInfoBean> t()
  {
    if (this.ag == null) {
      this.ag = new Hashtable();
    }
    return this.ag;
  }
  
  public List<String> u()
  {
    if (this.ah == null) {
      this.ah = new ArrayList();
    }
    return this.ah;
  }
  
  public Map<String, Integer> v()
  {
    if (this.ao == null) {
      this.ao = new Hashtable();
    }
    return this.ao;
  }
  
  public String w()
  {
    return h("baiduId/normal_id.json");
  }
  
  public void x()
  {
    if (this.aR) {
      return;
    }
    this.aR = true;
    aj.a("VideoEditorApplication onCreate before:");
    z = com.xvideostudio.videoeditor.util.f.q(av);
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
    this.aM = d.a();
    r.a(getApplicationContext(), com.xvideostudio.videoeditor.j.b.w());
    O = com.xvideostudio.videoeditor.util.f.q(av);
    j.b("language", O);
    j.b("language", O.substring(0, 2));
    W();
    this.aO = am.a(this);
  }
  
  public void y()
  {
    if (this.aQ) {
      return;
    }
    this.aQ = true;
    try
    {
      FontCenter.getInstance().setFolder_font(com.xvideostudio.videoeditor.j.b.M());
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        if ((VideoEditorApplication.a(VideoEditorApplication.this)) && (com.xvideostudio.videoeditor.j.b.b(VideoEditorApplication.a()))) {
          c.i(VideoEditorApplication.U(), Boolean.valueOf(true));
        }
        if (!c.I(VideoEditorApplication.U()).booleanValue()) {
          hl.productor.fxlib.b.au = 1;
        }
        an.a().a(VideoEditorApplication.U());
        VideoEditorApplication.this.B();
        VideoEditorApplication.b(VideoEditorApplication.this).sendEmptyMessageDelayed(0, 10L);
        if (y.H(VideoEditorApplication.a()))
        {
          MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_START_APP");
          j.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_START_APP");
          if (!y.G(VideoEditorApplication.U()))
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("HW_ENCODER_ERR errTime:");
            localStringBuilder.append(y.I(VideoEditorApplication.a()));
            localStringBuilder.append(" errResetTime:");
            localStringBuilder.append(y.J(VideoEditorApplication.a()));
            j.b("VideoEditorApplication", localStringBuilder.toString());
            if (y.J(VideoEditorApplication.a()) < 3)
            {
              if (y.I(VideoEditorApplication.a()) % 5 == 0)
              {
                y.l(VideoEditorApplication.U(), false);
                y.F(VideoEditorApplication.a(), 0);
                if ((com.xvideostudio.videoeditor.util.f.d() >= 18) && (VideoEditorApplication.this.I()))
                {
                  hl.productor.fxlib.b.w = true;
                  hl.productor.fxlib.b.z = true;
                }
                y.E(VideoEditorApplication.a(), y.J(VideoEditorApplication.a()) + 1);
                j.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
              }
              else
              {
                y.F(VideoEditorApplication.a(), y.I(VideoEditorApplication.a()) + 1);
              }
            }
            else
            {
              MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
              j.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
            }
          }
          else
          {
            j.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
            MobclickAgent.onEvent(VideoEditorApplication.a(), "HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
            y.l(VideoEditorApplication.U(), false);
            y.F(VideoEditorApplication.a(), 0);
            if ((com.xvideostudio.videoeditor.util.f.d() >= 18) && (VideoEditorApplication.this.I()))
            {
              hl.productor.fxlib.b.w = true;
              hl.productor.fxlib.b.z = true;
            }
          }
        }
        else if (com.xvideostudio.videoeditor.util.f.d() >= 18)
        {
          if (VideoEditorApplication.this.I())
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
        j.b("", localStringBuilder.toString());
        VideoEditorApplication.this.J();
        int i = VideoEditorApplication.b;
        int j = VideoEditorApplication.c;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("screenWidth = ");
        localStringBuilder.append(i);
        localStringBuilder.append("screenHeight = ");
        localStringBuilder.append(j);
        j.b("", localStringBuilder.toString());
        if (i * j < 921600) {
          hl.productor.fxlib.v.n = 0;
        }
        if (Math.min(hl.productor.fxlib.b.f, hl.productor.fxlib.b.e) >= 720) {
          VideoEditorApplication.this.H();
        }
        if (hl.productor.fxlib.b.D == true) {
          VideoEditorApplication.this.K();
        } else {
          hl.productor.fxlib.b.E = false;
        }
        if (hl.productor.fxlib.b.E)
        {
          i = y.m(VideoEditorApplication.U(), hl.productor.fxlib.b.D ^ true);
          if ((i == 0) && (!hl.productor.fxlib.b.D))
          {
            y.l(VideoEditorApplication.U(), 1);
            return;
          }
          if ((i == 1) && (hl.productor.fxlib.b.D)) {
            y.l(VideoEditorApplication.U(), 0);
          }
        }
      }
    }).start();
  }
  
  public void z()
  {
    o();
    aj.a("VideoEditorApplication onCreate after:");
    V();
    A();
    if (!l.a(com.xvideostudio.videoeditor.j.c.f(com.xvideostudio.videoeditor.j.c.f(1), 6))) {
      y.a(av, false, com.xvideostudio.videoeditor.util.f.d(av));
    } else if (!new File(com.xvideostudio.videoeditor.j.b.H()).isDirectory()) {
      y.a(av, false, com.xvideostudio.videoeditor.util.f.d(av));
    } else if (!new File(com.xvideostudio.videoeditor.j.b.J()).isDirectory()) {
      y.a(av, false, com.xvideostudio.videoeditor.util.f.d(av));
    }
    a(true, true, true, true, true);
    int i1 = y.u(a(), 3);
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
      ((StringBuilder)localObject).append(com.xvideostudio.videoeditor.j.b.f());
      ((StringBuilder)localObject).append("1.png");
      localObject = ((StringBuilder)localObject).toString();
      if (!l.a((String)localObject)) {
        l.a(av, 2131623960, (String)localObject);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    if (g())
    {
      i1 = y.A(av);
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
}
