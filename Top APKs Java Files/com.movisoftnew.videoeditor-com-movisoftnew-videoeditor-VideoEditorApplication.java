package com.movisoftnew.videoeditor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.movisoftnew.videoeditor.e.i.a;
import com.movisoftnew.videoeditor.slideshow.activity.MainActivity;
import com.movisoftnew.videoeditor.slideshow.activity.MarketUrlRedirectActivity;
import com.movisoftnew.videoeditor.tool.j;
import com.movisoftnew.videoeditor.tool.t;
import com.movisoftnew.videoeditor.util.ah;
import com.movisoftnew.videoeditor.util.aj;
import com.movisoftnew.videoeditor.util.ak;
import com.movisoftnew.videoeditor.util.ap;
import com.movisoftnew.videoeditor.util.f;
import com.movisoftnew.videoeditor.util.q;
import com.movisoftnew.videoeditor.util.s;
import com.movisoftnew.videoeditor.view.StoryBoardView.a;
import com.xinmei365.fontsdk.FontCenter;
import com.xinmei365.fontsdk.bean.FailureInfo;
import com.xinmei365.fontsdk.bean.Font;
import com.xinmei365.fontsdk.callback.FontScanCallBack;
import com.xinmei365.fontsdk.callback.FontTypefaceCallBack;
import com.xvideostudio.videoeditor.activity.Tools;
import hl.productor.fxlib.HLRenderThread;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import org.xvideo.videoeditor.database.PaintDraftHandler;

public class VideoEditorApplication
  extends MultiDexApplication
{
  public static HashMap<String, Integer> A;
  public static int[] B;
  public static com.movisoftnew.videoeditor.n.b C;
  public static ArrayList<MediaClipTrim> D;
  public static Map<String, Context> F;
  public static int G;
  public static int H;
  public static String Q = "zh-CN";
  public static String R = "ar";
  public static boolean S = false;
  public static boolean T = false;
  public static boolean U = false;
  public static boolean V = false;
  public static boolean W = false;
  public static boolean X = false;
  public static String Y = "REMOVE_WATER";
  public static boolean Z = true;
  public static Context a;
  public static String aa = "170";
  public static String ab = "4835";
  private static String ac;
  private static String ad;
  private static String ae;
  private static String af;
  private static Map<String, Typeface> ag;
  private static List<com.movisoftnew.videoeditor.h.v> am;
  private static Map<String, Map<String, String>> ar = null;
  private static Boolean as = null;
  private static boolean au = false;
  private static boolean av = false;
  private static long aw = 0L;
  public static VideoEditorApplication b = null;
  public static int c = 0;
  public static int d = 0;
  public static float e = 0.0F;
  public static int f = 1496;
  public static String g = "7.0.0";
  public static boolean h;
  public static boolean i = false;
  public static boolean j = false;
  public static boolean k = false;
  public static boolean l = true;
  public static boolean m = false;
  public static String n;
  public static String o = "https://play.google";
  public static String p;
  public static String q;
  public static String r;
  public static String s;
  public static int t = 2;
  public static Map<String, Typeface> u;
  public static String v = "en-US";
  public static String w = "com.xvideostudio.videoeditor";
  public static boolean x = false;
  public static boolean y = false;
  public static Boolean z = Boolean.valueOf(false);
  public com.movisoftnew.videoeditor.materialdownload.c E = null;
  public Bundle I;
  public boolean J = true;
  public int K = 1;
  public Hashtable<String, com.movisoftnew.videoeditor.k.l> L = null;
  public List<String> M = null;
  Map<String, Integer> N = null;
  public com.movisoftnew.videoeditor.materialdownload.a O = null;
  public com.movisoftnew.videoeditor.materialdownload.a P = null;
  private DraftBoxHandler ah = null;
  private org.xvideo.videoeditor.a.b ai = null;
  private org.xvideo.videoeditor.b.b aj = null;
  private PaintDraftHandler ak = null;
  private com.b.a.b.d al = null;
  private boolean an = false;
  private boolean ao = false;
  private boolean ap = false;
  private Handler aq = new Handler()
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
            VideoEditorApplication.this.g();
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
                  com.movisoftnew.videoeditor.util.l.a(localFile);
                  com.movisoftnew.videoeditor.util.l.a(VideoEditorApplication.a, str, i);
                  if (bool1)
                  {
                    ap.a(str, localFile.getParent(), true);
                    com.movisoftnew.videoeditor.util.l.a(localFile);
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
                  com.movisoftnew.videoeditor.util.l.a(localFile);
                }
              }
            }
          }
        }).start();
        return;
      }
      j.a(2131624570);
    }
  };
  private StoryBoardView.a at = null;
  private boolean ax = false;
  
  static
  {
    A = new HashMap(100);
    B = null;
    C = null;
    D = null;
    F = new HashMap();
    G = 1;
    H = 1;
    am = null;
  }
  
  public VideoEditorApplication() {}
  
  public static boolean A()
  {
    boolean bool = false;
    if (as != null) {
      bool = as.booleanValue();
    }
    while (a == null) {
      return bool;
    }
    Object localObject = a.getPackageManager().getInstalledPackages(0);
    as = Boolean.FALSE;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equalsIgnoreCase("com.android.vending")) {
        as = Boolean.TRUE;
      }
    }
    return as.booleanValue();
  }
  
  public static void G()
  {
    if (au) {
      return;
    }
    au = true;
    n = o + ".com/store/";
    p = n + "apps/details?id=";
    s = p + "com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshow%2520setting%26utm_medium%3Dbanner%26utm_term%3Dvideoshow%2520pro%26utm_content%3Dvideoshow%2520pro%2520for%2520setting%26utm_campaign%3Dvideoshow%2520pro%2520for%2520setting";
    r = p + "com.xvideostudio.videoeditorpro";
    q = p + "com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshowlabs%26utm_medium%3Dbanner%26utm_term%3Dlabs%26utm_content%3Dvideoshow%2520for%2520labs%26utm_campaign%3Dvideoshow%2520for%2520labs";
    if ((w != null) && (w.length() > 0))
    {
      p += w;
      return;
    }
    p += "com.xvideostudio.videoeditor";
  }
  
  public static void H()
  {
    if (av) {}
    do
    {
      return;
      av = true;
    } while ((!o()) || (t.u(a) != 1));
    x = true;
  }
  
  /* Error */
  public static boolean I()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 398	java/lang/System:currentTimeMillis	()J
    //   6: lstore_0
    //   7: getstatic 255	com/movisoftnew/videoeditor/VideoEditorApplication:aw	J
    //   10: lstore_2
    //   11: lload_0
    //   12: lload_2
    //   13: lsub
    //   14: ldc2_w 399
    //   17: lcmp
    //   18: ifge +12 -> 30
    //   21: iconst_1
    //   22: istore 4
    //   24: ldc 2
    //   26: monitorexit
    //   27: iload 4
    //   29: ireturn
    //   30: lload_0
    //   31: putstatic 255	com/movisoftnew/videoeditor/VideoEditorApplication:aw	J
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
  
  private void N()
  {
    String str;
    if (!j())
    {
      if (!o()) {
        break label59;
      }
      aa = "170";
      ab = "4835";
      str = a(this, Process.myPid());
      if (str != null) {
        if (!str.equalsIgnoreCase(a.getPackageName())) {
          break label100;
        }
      }
    }
    for (;;)
    {
      str.equals(a.getPackageName());
      return;
      label59:
      if (n())
      {
        aa = "166";
        ab = "4835";
        break;
      }
      if (!m()) {
        break;
      }
      aa = "168";
      ab = "4833";
      break;
      label100:
      if ((str.equalsIgnoreCase(a.getPackageName() + ":channel")) || (str.equalsIgnoreCase(a.getPackageName() + ":servicerewards")) || (!str.equalsIgnoreCase(a.getPackageName() + ":servicerewardsprot"))) {}
    }
  }
  
  private void O() {}
  
  private void P() {}
  
  private static void Q() {}
  
  private void R()
  {
    Object localObject = new File(s());
    if (!((File)localObject).exists()) {
      ((File)localObject).mkdirs();
    }
    localObject = com.movisoftnew.videoeditor.n.c.o();
    try
    {
      d((String)localObject);
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
  
  private void S()
  {
    int i1 = Integer.valueOf(f.g()).intValue();
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "the cpu frequency is " + i1 + "khz");
    int i2 = f.o();
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "cpuCoreNums is " + i2);
    if (i2 < 2) {
      hl.productor.fxlib.b.D = false;
    }
    for (;;)
    {
      if (!hl.productor.fxlib.b.D) {
        t.k(a, 1);
      }
      if (true == hl.productor.fxlib.b.D) {
        if (HLRenderThread.detectGraphicsPerformance() == -1)
        {
          hl.productor.fxlib.b.D = false;
          t.k(a, 1);
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
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "detectGraphicsPerformance RGB565");
    t.k(a, 2);
    return;
    hl.productor.fxlib.e.c = 2;
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "detectGraphicsPerformance RGBA8888");
    t.k(a, 3);
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (c > 0) {
        return c;
      }
    }
    else if (d > 0) {
      return d;
    }
    paramContext = paramContext.getResources().getDisplayMetrics();
    c = paramContext.widthPixels;
    d = paramContext.heightPixels;
    e = paramContext.density;
    com.movisoftnew.videoeditor.tool.i.b("cxs", "width" + paramContext.widthPixels);
    com.movisoftnew.videoeditor.tool.i.b("cxs", "height" + paramContext.heightPixels);
    if (c > d)
    {
      int i1 = d;
      d = c;
      c = i1;
    }
    if (paramBoolean) {
      return c;
    }
    return d;
  }
  
  public static String a(int paramInt)
  {
    if (A == null)
    {
      A = new HashMap(100);
      Q();
    }
    Iterator localIterator = A.entrySet().iterator();
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
    u = new LinkedHashMap();
    if (paramFontScanCallBack != null)
    {
      FontCenter.getInstance().getLocalFontList(paramFontScanCallBack);
      return;
    }
    FontCenter.getInstance().getLocalFontList(new FontScanCallBack()
    {
      public void onFailure(String paramAnonymousString)
      {
        com.movisoftnew.videoeditor.tool.i.a("", paramAnonymousString);
      }
      
      public void onSuccess(List<Font> paramAnonymousList)
      {
        int i = 0;
        for (;;)
        {
          if (i < paramAnonymousList.size())
          {
            final String str = ((Font)paramAnonymousList.get(i)).getFontLocalPath();
            try
            {
              ((Font)paramAnonymousList.get(i)).getTypeface(new FontTypefaceCallBack()
              {
                public void onFailure(FailureInfo paramAnonymous2FailureInfo)
                {
                  VideoEditorApplication.u.put(s.a(str), Typeface.DEFAULT);
                }
                
                public void onSuccess(String paramAnonymous2String, Typeface paramAnonymous2Typeface)
                {
                  VideoEditorApplication.u.put(s.a(str), paramAnonymous2Typeface);
                }
              });
              i += 1;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                VideoEditorApplication.u.put(s.a(str), Typeface.DEFAULT);
                localException.printStackTrace();
              }
            }
          }
        }
      }
    });
  }
  
  public static void a(boolean paramBoolean)
  {
    int i1 = 0;
    if (paramBoolean) {
      i1 = 1;
    }
    t.o(a, i1);
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
      if ((Tools.b(paramContext)) || (n()) || (m()))
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
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "checkApkExist packageName:" + paramString);
    boolean bool1 = bool2;
    if (paramString != null)
    {
      if (!"".equals(paramString)) {
        break label74;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "checkApkExist ret:" + bool1);
      return bool1;
      try
      {
        label74:
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
    if (w == null) {
      w = f.t(i());
    }
    if (w.equalsIgnoreCase(paramString)) {}
    do
    {
      return true;
      w = f.t(i());
    } while (w.equalsIgnoreCase(paramString));
    return false;
  }
  
  public static Typeface b(String paramString)
  {
    if (com.movisoftnew.videoeditor.util.v.a(paramString))
    {
      if (ag == null) {
        y();
      }
      if (ag.containsKey(paramString)) {
        return (Typeface)ag.get(paramString);
      }
    }
    else
    {
      if (u == null) {
        a(null);
      }
      if (u.containsKey(paramString)) {
        return (Typeface)u.get(paramString);
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
      if (A())
      {
        i1 = 1;
        i2 = 1;
        localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
      }
      i1 = i2;
      localIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.xvideostudio.videoeditorpro&referrer=" + paramString));
      i1 = i2;
      paramContext.startActivity(localIntent);
      if (i2 != 0) {}
      return;
    }
    catch (Exception paramContext)
    {
      if (i1 != 0) {}
      paramContext.printStackTrace();
    }
  }
  
  public static int c(String paramString)
  {
    if (A == null)
    {
      A = new HashMap(100);
      Q();
    }
    if ((A != null) && (A.containsKey(paramString))) {
      return ((Integer)A.get(paramString)).intValue();
    }
    return 0;
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  private void d(String paramString)
    throws IOException
  {
    paramString = new BufferedOutputStream(new FileOutputStream(paramString));
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(getResources().openRawResource(2131558420));
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
  
  private void e(int paramInt)
  {
    am = new ArrayList();
    int i1 = 0;
    while (i1 < 20)
    {
      com.movisoftnew.videoeditor.h.v localV = new com.movisoftnew.videoeditor.h.v();
      localV.a = b.d[i1];
      localV.b = b.e[i1];
      localV.c = false;
      localV.d = (i1 + 4);
      localV.e = b.f[i1];
      localV.f = b.g[i1];
      localV.g = b.h[i1];
      if (paramInt == localV.d)
      {
        localV.c = true;
        hl.productor.fxlib.b.Y = false;
        hl.productor.fxlib.b.N = paramInt;
      }
      am.add(localV);
      i1 += 1;
    }
  }
  
  public static VideoEditorApplication i()
  {
    if (b == null) {
      b = new VideoEditorApplication();
    }
    return b;
  }
  
  public static boolean j()
  {
    i = true;
    return true;
  }
  
  public static boolean k()
  {
    return (x) || (hl.productor.fxlib.b.ax) || (j());
  }
  
  public static boolean l()
  {
    return f.q().equals("zh-CN");
  }
  
  public static boolean m()
  {
    return a("com.xvideostudio.videoeditorlite");
  }
  
  public static boolean n()
  {
    return a("com.xvideostudio.videoeditorprofree");
  }
  
  public static boolean o()
  {
    return a("com.xvideostudio.videoeditor");
  }
  
  public static boolean r()
  {
    if (!h) {}
    while (t.p(a, 0) == 0) {
      return false;
    }
    return true;
  }
  
  public static String s()
  {
    return ad;
  }
  
  public static boolean t()
  {
    return true;
  }
  
  public static SharedPreferences u()
  {
    return PreferenceManager.getDefaultSharedPreferences(b);
  }
  
  public static Map<String, Typeface> y()
  {
    if ((ag == null) || (ag.size() == 0))
    {
      ag = new LinkedHashMap();
      ah.a("VideoEditorApplication onCreate before new typeFace:");
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
          ag.put(i1 + "", Typeface.SANS_SERIF);
        }
        for (;;)
        {
          i1 -= 1;
          break;
          try
          {
            ag.put(i1 + "", Typeface.createFromAsset(a.getAssets(), "font/" + arrayOfString[i1]));
          }
          catch (Exception localException)
          {
            ag.put(i1 + "", Typeface.SANS_SERIF);
            localException.printStackTrace();
          }
        }
      }
      ah.a("VideoEditorApplication onCreate after typeFace:");
    }
    return ag;
  }
  
  public static Map<String, Map<String, String>> z()
  {
    int i1 = 0;
    if ((ar == null) || (ar.size() == 0))
    {
      ar = new LinkedHashMap();
      new HashMap();
      Object localObject = new String[0];
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
        ((Map)localObject).put("rawId", new int[0][i1] + "");
        ((Map)localObject).put("duration", new String[] { "64608" }[i1]);
        ((Map)localObject).put("musicName", arrayOfString[i1]);
        ar.put(new String[] { "music_new_york_love.aac" }[i1], localObject);
        i1 += 1;
      }
    }
    return ar;
  }
  
  public void B()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    this.aq.sendMessage(localMessage);
  }
  
  public void C()
  {
    int i2 = -1;
    int i4 = 1;
    for (;;)
    {
      try
      {
        Object localObject = t.w(a);
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
    str1 = com.movisoftnew.videoeditor.n.c.L();
    str2 = str1 + "export_720p_avc_test.mp4";
    com.movisoftnew.videoeditor.util.l.a(a, str2, 2131558406);
    if (!f.a(str2))
    {
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "this device can not surport 720P AVC HighProfile export");
      hl.productor.fxlib.b.X = false;
      str1 = str1 + "export_720p_mpeg4_test.mp4";
      com.movisoftnew.videoeditor.util.l.a(a, str1, 2131558407);
      if (!f.a(str1))
      {
        com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "this device can not surport 720P export");
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
      t.d(a, i2 + "," + i1);
      return;
      i2 = 0;
      break label325;
      i2 = -1;
      i1 = 0;
      break;
    }
  }
  
  public boolean D()
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
          com.movisoftnew.videoeditor.tool.i.d("JNIMsg", "Codec: " + localMediaCodecInfo.getName());
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
              com.movisoftnew.videoeditor.tool.i.d("JNIMsg", "mimes: " + str);
              i5 = i2;
              i6 = i1;
              if (str.equalsIgnoreCase("video/avc"))
              {
                str = localMediaCodecInfo.getName();
                com.movisoftnew.videoeditor.tool.i.b("JNIMsg", "AVC encoder is " + str);
                i2 += 1;
                i5 = i2;
                i6 = i1;
                if (str.equalsIgnoreCase("OMX.sprd.h264.encoder"))
                {
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
    return (i2 > 0) && (i1 == 0);
  }
  
  public void E()
  {
    String str = f.m();
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "cpu Architecture is " + str);
    f.d();
    if ((str.equals("armeabi-v7a")) || (str.equals("arm64-v8a")))
    {
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "H264 encoding is enabled");
      hl.productor.fxlib.b.X = true;
      hl.productor.fxlib.b.H = 1;
      int i1 = Math.min(f.k(a), f.l(a));
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "screenWidth = " + i1);
      if (i1 > 1446)
      {
        hl.productor.fxlib.b.g = i1;
        hl.productor.fxlib.b.h = i1;
      }
      B = f.u();
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "cameraMaxSize[0] = " + B[0]);
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "cameraMaxSize[1] = " + B[1]);
      if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > f.k(a) * f.l(a))
      {
        hl.productor.fxlib.b.e = Math.min(f.k(a), f.l(a));
        hl.productor.fxlib.b.f = Math.max(f.k(a), f.l(a));
        com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "Max output size restricted to " + f.k(a) + " x " + f.l(a));
      }
      if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > B[0] * B[1])
      {
        hl.productor.fxlib.b.e = Math.min(B[0], B[1]);
        hl.productor.fxlib.b.f = Math.max(B[0], B[1]);
      }
      if (((f.k(a) * f.l(a) <= 384000) && (f.k(a) * f.l(a) == B[0] * B[1])) || (B[0] * B[1] < 384000))
      {
        com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "special machine , we need disable video_hw_encode_enable  ");
        hl.productor.fxlib.b.w = false;
        hl.productor.fxlib.b.z = false;
      }
      if (f.o() < 4) {
        break label547;
      }
      com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "4-core   render_target_scale_mode_enabled can be false");
      hl.productor.fxlib.b.J = false;
      label452:
      if (B[2] != 1) {
        break label554;
      }
      if ((c * d <= 2073600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > B[0] * B[1]))
      {
        hl.productor.fxlib.b.ae = Math.min(B[0], B[1]);
        hl.productor.fxlib.b.af = Math.max(B[0], B[1]);
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
      label547:
      hl.productor.fxlib.b.J = true;
      break label452;
      label554:
      if ((c * d <= 921600) && (hl.productor.fxlib.b.ae * hl.productor.fxlib.b.af > B[0] * B[1]))
      {
        hl.productor.fxlib.b.ae = Math.min(B[0], B[1]);
        hl.productor.fxlib.b.af = Math.max(B[0], B[1]);
      }
    }
  }
  
  void F()
  {
    switch (t.l(a, 0))
    {
    default: 
      return;
    case 0: 
      S();
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
  
  public void J()
  {
    if ((f <= 0) || (g == null) || (g.equals(""))) {}
    try
    {
      PackageInfo localPackageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
      f = localPackageInfo.versionCode;
      g = localPackageInfo.versionName;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
  }
  
  public void K()
  {
    if (this.ax) {
      return;
    }
    this.ax = true;
    w = f.t(i());
  }
  
  public List<com.movisoftnew.videoeditor.h.v> L()
  {
    if (am == null) {
      e(t.r(i(), 3));
    }
    return am;
  }
  
  public boolean M()
  {
    boolean bool = this.J;
    if ((k()) || (l()) || (m())) {
      bool = false;
    }
    return bool;
  }
  
  public com.movisoftnew.videoeditor.materialdownload.c a()
  {
    if (this.E == null) {
      this.E = new com.movisoftnew.videoeditor.materialdownload.c(a);
    }
    return this.E;
  }
  
  public void a(final int paramInt, final MediaClip paramMediaClip)
  {
    this.aq.post(new Runnable()
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
          j.a(VideoEditorApplication.this.getResources().getString(2131624874), -1, 1);
        }
        while (VideoEditorApplication.c(VideoEditorApplication.this) == null) {
          return;
        }
        VideoEditorApplication.c(VideoEditorApplication.this).a(paramMediaClip);
      }
    });
  }
  
  public void a(StoryBoardView.a paramA)
  {
    this.at = paramA;
  }
  
  public void a(String paramString, ImageView paramImageView, com.b.a.b.c paramC)
  {
    com.b.a.b.c localC = paramC;
    if (paramC == null) {
      localC = q.a(0, true, true, true);
    }
    if (this.al == null)
    {
      this.al = com.b.a.b.d.a();
      q.a(getApplicationContext(), com.movisoftnew.videoeditor.n.c.u());
    }
    this.al.a(paramString, paramImageView, localC);
  }
  
  public void a(final String paramString, final boolean paramBoolean, final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        try
        {
          String str1 = SystemUtility.getTimeMinSecFormt(Tools.getVideoRealWidthHeight(paramString)[3]);
          String str2 = com.movisoftnew.videoeditor.util.l.a(com.movisoftnew.videoeditor.util.l.e(paramString), 1073741824L);
          org.xvideo.videoeditor.b.a localA = new org.xvideo.videoeditor.b.a();
          localA.filePath = paramString;
          localA.fileSize = str2;
          localA.videoName = paramString.substring(paramString.lastIndexOf("/") + 1);
          localA.showTime = System.currentTimeMillis();
          localA.videoDuration = str1;
          if (paramBoolean) {
            i = 1;
          }
          localA.isShowName = i;
          if (paramInt == 0) {}
          for (localA.newName = com.movisoftnew.videoeditor.util.l.j(localA.videoName);; localA.newName = localA.videoName.substring(0, localA.videoName.lastIndexOf("(")))
          {
            localA.ordinal = paramInt;
            VideoEditorApplication.this.x().a(localA);
            return;
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
    try
    {
      if (!t.d(a, f.d(a)))
      {
        if ((paramBoolean1) && (paramBoolean2)) {
          R();
        }
        if (paramBoolean1)
        {
          String str = com.movisoftnew.videoeditor.n.c.A();
          com.movisoftnew.videoeditor.util.l.b(str);
          Iterator localIterator = z().keySet().iterator();
          while (localIterator.hasNext())
          {
            Object localObject = (String)localIterator.next();
            localObject = (Map)z().get(localObject);
            if ((localObject != null) && (((Map)localObject).size() != 0))
            {
              Message localMessage = new Message();
              localMessage.what = 1;
              Bundle localBundle = new Bundle();
              localBundle.putInt("rawId", Integer.valueOf((String)((Map)localObject).get("rawId")).intValue());
              localBundle.putString("rawFilePath", str + (String)((Map)localObject).get("fileName"));
              localMessage.setData(localBundle);
              this.aq.sendMessage(localMessage);
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
    t.a(a, true, f.d(a));
  }
  
  public Hashtable<String, com.movisoftnew.videoeditor.k.l> b()
  {
    if (this.L == null) {
      this.L = new Hashtable();
    }
    return this.L;
  }
  
  public void b(final int paramInt)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          com.movisoftnew.videoeditor.e.b.a(MainActivity.a(VideoEditorApplication.a), 2, new i.a()
          {
            public void onFailed(String paramAnonymous2String)
            {
              com.movisoftnew.videoeditor.tool.i.b("xxw", "initLiteUnlockerProHttp=" + paramAnonymous2String);
              if (VideoEditorApplication.6.this.a == 0)
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
              t.w(VideoEditorApplication.a, 2);
            }
            
            public void onSuccess(Object paramAnonymous2Object)
            {
              com.movisoftnew.videoeditor.tool.i.b("xxw", "initLiteUnlockerProHttp=" + paramAnonymous2Object);
              if (!Boolean.parseBoolean(paramAnonymous2Object.toString()))
              {
                t.w(VideoEditorApplication.a, 0);
                return;
              }
              VideoEditorApplication.x = true;
              t.w(VideoEditorApplication.a, 1);
              com.movisoftnew.videoeditor.util.l.a(com.movisoftnew.videoeditor.n.c.I() + ".lup.dat", 1);
              VideoEditorApplication.b(VideoEditorApplication.this).post(new Thread()
              {
                public void run()
                {
                  j.a(2131624414, 0);
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
  
  public void b(String paramString, ImageView paramImageView, com.b.a.b.c paramC)
  {
    if (this.al == null)
    {
      this.al = com.b.a.b.d.a();
      q.a(getApplicationContext(), com.movisoftnew.videoeditor.n.c.u());
    }
    this.al.a("file://" + paramString, paramImageView, paramC);
  }
  
  public List<String> c()
  {
    if (this.M == null) {
      this.M = new ArrayList();
    }
    return this.M;
  }
  
  public void c(int paramInt)
  {
    int i1 = 0;
    com.movisoftnew.videoeditor.h.v localV;
    if (i1 < 20)
    {
      localV = (com.movisoftnew.videoeditor.h.v)am.get(i1);
      if (paramInt < 4)
      {
        if (!localV.c) {
          break label63;
        }
        localV.c = false;
        am.set(i1, localV);
      }
    }
    else
    {
      return;
    }
    if (paramInt == localV.d) {}
    for (localV.c = true;; localV.c = false)
    {
      label63:
      am.set(i1, localV);
      i1 += 1;
      break;
    }
  }
  
  public com.movisoftnew.videoeditor.h.v d(int paramInt)
  {
    int i1 = 0;
    while (i1 < 20)
    {
      com.movisoftnew.videoeditor.h.v localV = (com.movisoftnew.videoeditor.h.v)am.get(i1);
      if ((paramInt >= 4) && (paramInt == localV.d)) {
        return localV;
      }
      i1 += 1;
    }
    return null;
  }
  
  public Map<String, Integer> d()
  {
    if (this.N == null) {
      this.N = new Hashtable();
    }
    return this.N;
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
  
  public void e()
  {
    if (this.ap) {
      return;
    }
    this.ap = true;
    ah.a("VideoEditorApplication onCreate before:");
    v = f.q(a);
    try
    {
      PackageInfo localPackageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(), 0);
      w = localPackageInfo.packageName;
      f = localPackageInfo.versionCode;
      g = localPackageInfo.versionName;
      if ((w == null) || (w.length() == 0)) {
        w = "com.xvideostudio.videoeditor";
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    if (m()) {}
    FontCenter.init(i());
    FontCenter.getInstance().setFolder_font(com.movisoftnew.videoeditor.n.c.K());
    this.al = com.b.a.b.d.a();
    q.a(getApplicationContext(), com.movisoftnew.videoeditor.n.c.u());
    Q = f.q(a);
    com.movisoftnew.videoeditor.tool.i.b("language", Q);
    com.movisoftnew.videoeditor.tool.i.b("language", Q.substring(0, 2));
    N();
  }
  
  public void f()
  {
    if (this.ao) {
      return;
    }
    this.ao = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        if ((VideoEditorApplication.a(VideoEditorApplication.this)) && (com.movisoftnew.videoeditor.n.c.b(VideoEditorApplication.i()))) {
          c.d(VideoEditorApplication.a, Boolean.valueOf(true));
        }
        if (!c.p(VideoEditorApplication.a).booleanValue()) {
          hl.productor.fxlib.b.au = 1;
        }
        ak.a().a(VideoEditorApplication.a);
        VideoEditorApplication.this.p();
        VideoEditorApplication.b(VideoEditorApplication.this).sendEmptyMessageDelayed(0, 10L);
        int i;
        if (t.G(VideoEditorApplication.i()))
        {
          com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_START_APP");
          if (!t.F(VideoEditorApplication.a))
          {
            com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "HW_ENCODER_ERR errTime:" + t.H(VideoEditorApplication.i()) + " errResetTime:" + t.I(VideoEditorApplication.i()));
            if (t.I(VideoEditorApplication.i()) < 3) {
              if (t.H(VideoEditorApplication.i()) % 5 == 0)
              {
                t.m(VideoEditorApplication.a, false);
                t.D(VideoEditorApplication.i(), 0);
                if ((f.d() >= 18) && (VideoEditorApplication.this.D()))
                {
                  hl.productor.fxlib.b.w = true;
                  hl.productor.fxlib.b.z = true;
                }
                t.C(VideoEditorApplication.i(), t.I(VideoEditorApplication.i()) + 1);
                com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                com.movisoftnew.videoeditor.tool.i.b("", "FxConfig.video_hw_encode_enable = " + hl.productor.fxlib.b.w);
                VideoEditorApplication.this.E();
                i = VideoEditorApplication.c;
                int j = VideoEditorApplication.d;
                com.movisoftnew.videoeditor.tool.i.b("", "screenWidth = " + i + "screenHeight = " + j);
                if (i * j < 921600) {
                  hl.productor.fxlib.v.n = 0;
                }
                if (Math.min(hl.productor.fxlib.b.f, hl.productor.fxlib.b.e) >= 720) {
                  VideoEditorApplication.this.C();
                }
                if (hl.productor.fxlib.b.D != true) {
                  break label503;
                }
                VideoEditorApplication.this.F();
                label351:
                if (hl.productor.fxlib.b.E)
                {
                  Context localContext = VideoEditorApplication.a;
                  if (!hl.productor.fxlib.b.D) {
                    break label510;
                  }
                  i = 0;
                  label369:
                  i = t.j(localContext, i);
                  if ((i != 0) || (hl.productor.fxlib.b.D)) {
                    break label515;
                  }
                  t.i(VideoEditorApplication.a, 1);
                }
              }
            }
          }
        }
        label503:
        label510:
        label515:
        while ((i != 1) || (!hl.productor.fxlib.b.D))
        {
          return;
          t.D(VideoEditorApplication.i(), t.H(VideoEditorApplication.i()) + 1);
          break;
          com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
          break;
          com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
          t.m(VideoEditorApplication.a, false);
          t.D(VideoEditorApplication.i(), 0);
          if ((f.d() < 18) || (!VideoEditorApplication.this.D())) {
            break;
          }
          hl.productor.fxlib.b.w = true;
          hl.productor.fxlib.b.z = true;
          break;
          if ((f.d() < 18) || (!VideoEditorApplication.this.D())) {
            break;
          }
          hl.productor.fxlib.b.w = true;
          hl.productor.fxlib.b.z = true;
          break;
          hl.productor.fxlib.b.E = false;
          break label351;
          i = 1;
          break label369;
        }
        t.i(VideoEditorApplication.a, 0);
      }
    }).start();
  }
  
  public void g()
  {
    G();
    ah.a("VideoEditorApplication onCreate after:");
    Q();
    h();
    if (!com.movisoftnew.videoeditor.util.l.a(com.movisoftnew.videoeditor.n.d.f(com.movisoftnew.videoeditor.n.d.f(1), 6))) {
      t.a(a, false, f.d(a));
    }
    for (;;)
    {
      a(true, true, true, true, true);
      try
      {
        P();
        i1 = t.r(i(), 3);
        if (i1 == 1)
        {
          hl.productor.fxlib.b.Y = false;
          hl.productor.fxlib.b.N = 1;
          e(i1);
        }
      }
      catch (Exception localException1)
      {
        try
        {
          label242:
          do
          {
            for (;;)
            {
              y();
              String str = com.movisoftnew.videoeditor.n.c.e() + "1.png";
              if (!com.movisoftnew.videoeditor.util.l.a(str)) {
                com.movisoftnew.videoeditor.util.l.a(a, 2131558421, str);
              }
              if (o())
              {
                i1 = t.u(a);
                if (i1 != 1) {
                  break label266;
                }
                x = true;
              }
              return;
              if (!new File(com.movisoftnew.videoeditor.n.c.F()).isDirectory())
              {
                t.a(a, false, f.d(a));
                break;
              }
              if (new File(com.movisoftnew.videoeditor.n.c.H()).isDirectory()) {
                break;
              }
              t.a(a, false, f.d(a));
              break;
              localException1 = localException1;
              localException1.printStackTrace();
              continue;
              if (i1 != 2) {
                break label242;
              }
              hl.productor.fxlib.b.Y = false;
              hl.productor.fxlib.b.N = 2;
            }
          } while (i1 != 3);
          hl.productor.fxlib.b.Y = true;
          hl.productor.fxlib.b.N = 3;
        }
        catch (Exception localException2)
        {
          int i1;
          label266:
          do
          {
            for (;;)
            {
              localException2.printStackTrace();
            }
          } while (i1 != 2);
          b(1);
        }
      }
    }
  }
  
  public void h()
  {
    label187:
    for (;;)
    {
      try
      {
        Object localObject = com.movisoftnew.videoeditor.n.c.I() + "VideoShowUserDB.db";
        int i1;
        if (!new File((String)localObject).exists()) {
          if (!com.movisoftnew.videoeditor.util.l.b(com.movisoftnew.videoeditor.n.c.a(a).getAbsolutePath(), (String)localObject))
          {
            localObject = new com.movisoftnew.videoeditor.f.l(a);
            ((com.movisoftnew.videoeditor.f.l)localObject).a(((com.movisoftnew.videoeditor.f.l)localObject).a());
            i1 = 15;
            break label187;
            localObject = new com.movisoftnew.videoeditor.f.l(a);
            if (i1 < 15) {}
          }
        }
        try
        {
          SQLiteDatabase localSQLiteDatabase = ((com.movisoftnew.videoeditor.f.l)localObject).a();
          if (!((com.movisoftnew.videoeditor.f.l)localObject).a(localSQLiteDatabase, "filedownlog", "material_giphy")) {
            ((com.movisoftnew.videoeditor.f.l)localObject).d(localSQLiteDatabase);
          }
          localSQLiteDatabase.close();
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          continue;
        }
        if (i1 >= 15)
        {
          return;
          com.movisoftnew.videoeditor.util.l.a(com.movisoftnew.videoeditor.f.l.a, 1);
          i1 = 1;
          break label187;
          i1 = com.movisoftnew.videoeditor.util.l.n(com.movisoftnew.videoeditor.f.l.a);
          continue;
        }
        localException1.a(localException1.a(), i1, 15);
      }
      catch (Exception localException1)
      {
        j.a(localException1.getMessage(), 1);
        localException1.printStackTrace();
        return;
      }
      return;
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    a = this;
    b = this;
    com.movisoftnew.videoeditor.tool.i.a(a);
    com.movisoftnew.a.a.i.a(a);
    a(this, true);
    new AsyncTask()
    {
      protected Integer a(Object... paramAnonymousVarArgs)
      {
        return null;
      }
    };
    com.movisoftnew.videoeditor.tool.i.b("VideoEditorApplication", "Application start");
    this.an = c.b(i()).booleanValue();
    e();
    try
    {
      O();
      K();
      if (c.t(a) == 0L)
      {
        com.movisoftnew.videoeditor.util.c.a(a);
        c.g(this, Boolean.valueOf(false));
        c.a(a, System.currentTimeMillis());
        if ((Build.MANUFACTURER.equalsIgnoreCase("samsung")) && (w.equals("com.xvideostudio.videoeditorlite")) && (c.v(a) == 0L)) {
          new com.movisoftnew.videoeditor.n.a().b(a, null);
        }
      }
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
  
  public void p()
  {
    j();
    q();
  }
  
  public void q()
  {
    Object localObject = "";
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject = Environment.getExternalStorageDirectory().getAbsolutePath();
      com.movisoftnew.videoeditor.tool.i.b("cxs", "Sd1 path:" + (String)localObject);
    }
    String str = com.movisoftnew.videoeditor.n.c.a();
    if ((str != null) && (!((String)localObject).equalsIgnoreCase(str)) && (!str.startsWith("/storage/emulated/legacy")))
    {
      com.movisoftnew.videoeditor.tool.i.b("cxs", "Sd2 path:" + str);
      af = str + File.separator + com.movisoftnew.videoeditor.n.c.f;
      com.movisoftnew.videoeditor.util.l.b(af);
      h = true;
    }
    try
    {
      localObject = new File(af + aj.a() + ".test");
      ((File)localObject).createNewFile();
      ((File)localObject).delete();
      ac = com.movisoftnew.videoeditor.n.c.t();
      ad = com.movisoftnew.videoeditor.n.c.e();
      ae = com.movisoftnew.videoeditor.n.c.d();
      if ((!h) && (r() == true)) {
        a(false);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        h = false;
        localException.printStackTrace();
      }
    }
  }
  
  public DraftBoxHandler v()
  {
    if (this.ah == null) {
      this.ah = new DraftBoxHandler();
    }
    return this.ah;
  }
  
  public org.xvideo.videoeditor.a.b w()
  {
    if (this.ai == null) {
      this.ai = new org.xvideo.videoeditor.a.b(getApplicationContext());
    }
    return this.ai;
  }
  
  public org.xvideo.videoeditor.b.b x()
  {
    if (this.aj == null) {
      this.aj = new org.xvideo.videoeditor.b.b(getApplicationContext());
    }
    return this.aj;
  }
}
