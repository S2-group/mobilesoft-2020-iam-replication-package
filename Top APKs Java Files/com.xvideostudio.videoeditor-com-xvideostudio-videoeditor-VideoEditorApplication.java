package com.xvideostudio.videoeditor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Notification;
import android.content.ComponentName;
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
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.a.a.b.d;
import com.facebook.FacebookSdk;
import com.mobvista.msdk.MobVistaSDK;
import com.mobvista.msdk.out.MobVistaSDKFactory;
import com.umeng.message.PushAgent;
import com.umeng.message.UHandler;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.UmengRegistrar;
import com.xinmei365.fontsdk.FontCenter;
import com.xinmei365.fontsdk.bean.FailureInfo;
import com.xinmei365.fontsdk.bean.Font;
import com.xinmei365.fontsdk.callback.FontTypefaceCallBack;
import com.xvideostudio.VsCommunity.Api.VscUserinfoSession;
import com.xvideostudio.VsCommunity.entity.HotVideoDataparam.ContestItem;
import com.xvideostudio.videoeditor.activity.MainActivity;
import com.xvideostudio.videoeditor.activity.MaterialActivity;
import com.xvideostudio.videoeditor.activity.Tools;
import com.xvideostudio.videoeditor.f.j;
import com.xvideostudio.videoeditor.gsonentity.SiteInfoBean;
import com.xvideostudio.videoeditor.tool.l;
import com.xvideostudio.videoeditor.tool.y;
import com.xvideostudio.videoeditor.util.ah;
import com.xvideostudio.videoeditor.util.aj;
import com.xvideostudio.videoeditor.util.ak;
import com.xvideostudio.videoeditor.util.ar;
import com.xvideostudio.videoeditor.util.e;
import com.xvideostudio.videoeditor.util.q;
import com.xvideostudio.videoeditor.util.s;
import com.xvideostudio.videoeditor.util.v;
import com.xvideostudio.videoeditor.view.StoryBoardView.a;
import hl.productor.fxlib.HLRenderThread;
import java.io.File;
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
import org.xvideo.videoeditor.database.PaintDraftHandler;

public class VideoEditorApplication
  extends MultiDexApplication
{
  public static String A = "com.xvideostudio.videoeditor";
  public static boolean B = false;
  public static boolean C = false;
  public static boolean D = false;
  public static Boolean E = Boolean.valueOf(false);
  public static HashMap<String, Integer> F = new HashMap(100);
  public static int[] G = null;
  public static com.xvideostudio.videoeditor.m.a H = null;
  public static ArrayList<MediaClipTrim> I = null;
  public static Map<String, Context> K = new HashMap();
  public static String Q = "zh-CN";
  public static String R = "ar";
  public static boolean S = false;
  public static boolean T = false;
  public static boolean U = false;
  public static boolean V = false;
  public static boolean W = false;
  public static boolean X = false;
  public static String Y = "170";
  public static int a;
  private static String ab;
  private static String ac;
  private static String ad;
  private static String ae;
  private static Map<String, Typeface> af;
  private static Map<String, Map<String, String>> ao = null;
  private static Boolean ap = null;
  private static boolean ar = false;
  private static boolean as = false;
  private static long at = 0L;
  public static String b;
  public static int c = -1;
  public static boolean d = false;
  public static Context e;
  public static VideoEditorApplication f = null;
  public static int g = 0;
  public static int h = 0;
  public static float i = 0.0F;
  public static int j;
  public static String k;
  public static boolean l;
  public static boolean m = false;
  public static boolean n = false;
  public static boolean o = false;
  public static boolean p = true;
  public static boolean q = false;
  public static String r;
  public static String s = "https://play.google";
  public static String t;
  public static String u;
  public static String v;
  public static String w;
  public static int x = 2;
  public static Map<String, Typeface> y;
  public static String z = "en-US";
  public com.xvideostudio.videoeditor.materialdownload.c J = null;
  public Hashtable<Integer, SiteInfoBean> L = null;
  public List<Integer> M = null;
  Map<Integer, Integer> N = null;
  public com.xvideostudio.videoeditor.materialdownload.a O = null;
  public com.xvideostudio.videoeditor.materialdownload.a P = null;
  List<HotVideoDataparam.ContestItem> Z = null;
  private final String aa = "VideoEditorApplication";
  private DraftBoxHandler ag = null;
  private org.xvideo.videoeditor.a.b ah = null;
  private PaintDraftHandler ai = null;
  private d aj = null;
  private PushAgent ak;
  private boolean al = false;
  private boolean am = false;
  private Handler an = new Handler()
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
            VideoEditorApplication.this.j();
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
                  com.xvideostudio.videoeditor.util.k.a(localFile);
                  com.xvideostudio.videoeditor.util.k.a(VideoEditorApplication.e, str, i);
                  if (bool1)
                  {
                    ar.a(str, localFile.getParent(), true);
                    com.xvideostudio.videoeditor.util.k.a(localFile);
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
                  com.xvideostudio.videoeditor.util.k.a(localFile);
                }
              }
            }
          }
        }).start();
        return;
      }
      l.a(2131296949);
    }
  };
  private StoryBoardView.a aq = null;
  
  public VideoEditorApplication() {}
  
  public static String A()
  {
    return ad;
  }
  
  public static String B()
  {
    return y() + "/";
  }
  
  public static boolean C()
  {
    String str = com.xvideostudio.videoeditor.util.k.a(e, "UMENG_CHANNEL", "GOOGLEPLAY");
    return ((str.equalsIgnoreCase("GOOGLEPLAY")) || (str.equalsIgnoreCase("VIDEOSHOWLABS")) || (str.equalsIgnoreCase("VIDEOSHOWLITE"))) && (!e.p(e).equals("zh-CN"));
  }
  
  public static boolean D()
  {
    String str = com.xvideostudio.videoeditor.util.k.a(e, "UMENG_CHANNEL", "GOOGLEPLAY");
    if ((str.equalsIgnoreCase("GOOGLEPLAY")) || (str.equalsIgnoreCase("VIDEOSHOWLABS")) || (str.equalsIgnoreCase("VIDEOSHOWLITE")))
    {
      if ((e.p(e).equals("zh-CN")) || (m()))
      {
        S = false;
        return false;
      }
      S = true;
      return true;
    }
    S = false;
    return false;
  }
  
  public static boolean E()
  {
    String str = com.xvideostudio.videoeditor.util.k.a(e, "UMENG_CHANNEL", "GOOGLEPLAY");
    if ((str.equalsIgnoreCase("GOOGLEPLAY")) || (str.equalsIgnoreCase("VIDEOSHOWLABS")) || (str.equalsIgnoreCase("VIDEOSHOWLITE")))
    {
      if (m())
      {
        S = false;
        return false;
      }
      S = true;
      return true;
    }
    S = false;
    return false;
  }
  
  public static boolean F()
  {
    String str = com.xvideostudio.videoeditor.util.k.a(e, "UMENG_CHANNEL", "GOOGLEPLAY");
    return (!str.equalsIgnoreCase("GOOGLEPLAY")) && (!str.equalsIgnoreCase("VIDEOSHOWLABS")) && (!str.equalsIgnoreCase("VIDEOSHOWLITE"));
  }
  
  public static boolean G()
  {
    return !com.xvideostudio.videoeditor.util.k.a(e, "UMENG_CHANNEL", "GOOGLEPLAY").equalsIgnoreCase("SAMSUNG");
  }
  
  public static SharedPreferences H()
  {
    return PreferenceManager.getDefaultSharedPreferences(f);
  }
  
  public static Map<String, Typeface> L()
  {
    if ((af == null) || (af.size() == 0))
    {
      af = new LinkedHashMap();
      ah.a("VideoEditorApplication onCreate before new typeFace:");
      String[] arrayOfString = new String[10];
      arrayOfString[0] = "";
      arrayOfString[1] = "cyrillic_font_lobster.ttf";
      arrayOfString[2] = "impact.ttf";
      arrayOfString[3] = "pointy.ttf";
      arrayOfString[4] = "helvetica_neue_lt_pro_bd.otf";
      arrayOfString[5] = "un-finished.ttf";
      arrayOfString[6] = "futura_medium_bt.ttf";
      arrayOfString[7] = "didot.ttf";
      arrayOfString[8] = "birth_of_a_hero.ttf";
      arrayOfString[9] = "sketchetik.otf";
      int i1 = arrayOfString.length - 1;
      if (i1 >= 0)
      {
        if (i1 == 0) {
          af.put(i1 + "", Typeface.SANS_SERIF);
        }
        for (;;)
        {
          i1 -= 1;
          break;
          try
          {
            af.put(i1 + "", Typeface.createFromAsset(e.getAssets(), "font/" + arrayOfString[i1]));
          }
          catch (Exception localException)
          {
            af.put(i1 + "", Typeface.SANS_SERIF);
            localException.printStackTrace();
          }
        }
      }
      ah.a("VideoEditorApplication onCreate after typeFace:");
    }
    return af;
  }
  
  public static Map<String, Typeface> M()
  {
    y = new LinkedHashMap();
    List localList = FontCenter.getInstance().getLocalFontList();
    int i1 = 0;
    for (;;)
    {
      if (i1 < localList.size())
      {
        String str = ((Font)localList.get(i1)).getFontLocalPath();
        try
        {
          ((Font)localList.get(i1)).getTypeface(new FontTypefaceCallBack()
          {
            public void onFailure(FailureInfo paramAnonymousFailureInfo)
            {
              VideoEditorApplication.y.put(s.a(this.a), Typeface.DEFAULT);
            }
            
            public void onSuccess(String paramAnonymousString, Typeface paramAnonymousTypeface)
            {
              VideoEditorApplication.y.put(s.a(this.a), paramAnonymousTypeface);
            }
          });
          i1 += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            y.put(s.a(str), Typeface.DEFAULT);
            localException.printStackTrace();
          }
        }
      }
    }
    return y;
  }
  
  public static Map<String, Map<String, String>> N()
  {
    int i1 = 0;
    if ((ao == null) || (ao.size() == 0))
    {
      ao = new LinkedHashMap();
      new HashMap();
      Object localObject = new String[1];
      localObject[0] = e.getResources().getString(2131296847);
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
        ((Map)localObject).put("rawId", new int[] { 2131165197 }[i1] + "");
        ((Map)localObject).put("duration", new String[] { "64608" }[i1]);
        ((Map)localObject).put("musicName", arrayOfString[i1]);
        ao.put(new String[] { "music_new_york_love.aac" }[i1], localObject);
        i1 += 1;
      }
    }
    return ao;
  }
  
  public static boolean O()
  {
    boolean bool = false;
    if (ap != null) {
      bool = ap.booleanValue();
    }
    while (e == null) {
      return bool;
    }
    Object localObject = e.getPackageManager().getInstalledPackages(0);
    ap = Boolean.FALSE;
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((PackageInfo)((Iterator)localObject).next()).packageName.equalsIgnoreCase("com.android.vending")) {
        ap = Boolean.TRUE;
      }
    }
    return ap.booleanValue();
  }
  
  public static boolean R()
  {
    try
    {
      String str = com.xvideostudio.videoeditor.m.b.I() + "export_240p_os_test.mp4";
      if (!new File(str).exists()) {
        com.xvideostudio.videoeditor.util.k.a(e, str, 2131165189);
      }
      boolean bool = e.a(str);
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static void V()
  {
    if (ar) {
      return;
    }
    ar = true;
    r = s + ".com/store/";
    t = r + "apps/details?id=";
    w = t + "com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshow%2520setting%26utm_medium%3Dbanner%26utm_term%3Dvideoshow%2520pro%26utm_content%3Dvideoshow%2520pro%2520for%2520setting%26utm_campaign%3Dvideoshow%2520pro%2520for%2520setting";
    v = t + "com.xvideostudio.videoeditorpro";
    u = t + "com.xvideostudio.videoeditor&referrer=utm_source%3Dvideoshowlabs%26utm_medium%3Dbanner%26utm_term%3Dlabs%26utm_content%3Dvideoshow%2520for%2520labs%26utm_campaign%3Dvideoshow%2520for%2520labs";
    if ((A != null) && (A.length() > 0))
    {
      t += A;
      return;
    }
    t += "com.xvideostudio.videoeditor";
  }
  
  public static void W()
  {
    if (as) {}
    do
    {
      return;
      as = true;
    } while ((!u()) || (y.L(e) != 1));
    B = true;
  }
  
  /* Error */
  public static boolean X()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 572	java/lang/System:currentTimeMillis	()J
    //   6: lstore_0
    //   7: getstatic 228	com/xvideostudio/videoeditor/VideoEditorApplication:at	J
    //   10: lstore_2
    //   11: lload_0
    //   12: lload_2
    //   13: lsub
    //   14: ldc2_w 573
    //   17: lcmp
    //   18: ifge +12 -> 30
    //   21: iconst_1
    //   22: istore 4
    //   24: ldc 2
    //   26: monitorexit
    //   27: iload 4
    //   29: ireturn
    //   30: lload_0
    //   31: putstatic 228	com/xvideostudio/videoeditor/VideoEditorApplication:at	J
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
  
  private void Y()
  {
    String str = "";
    Object localObject1 = "";
    Object localObject2;
    if (!m())
    {
      if (!u()) {
        break label124;
      }
      str = "22711";
      localObject1 = "e708c4bd96e9ca808eeb2e53b41d3fef";
      Y = "170";
      localObject2 = a(this, Process.myPid());
      if (localObject2 != null)
      {
        if (!((String)localObject2).equalsIgnoreCase(e.getPackageName())) {
          break label170;
        }
        com.umeng.a.b.a(this, "PROCESS_MAIN_APPLICATION");
      }
    }
    for (;;)
    {
      if (((String)localObject2).equals(e.getPackageName()))
      {
        com.umeng.a.b.a(this, "INIT_ADS_IN_APPLICATION");
        localObject2 = MobVistaSDKFactory.getMobVistaSDK();
        localObject1 = ((MobVistaSDK)localObject2).getMVConfigurationMap(str, (String)localObject1);
        ((Map)localObject1).put("applicationID", A);
        ((MobVistaSDK)localObject2).init((Map)localObject1, this);
        Z();
      }
      return;
      label124:
      if (t())
      {
        str = "22695";
        localObject1 = "e708c4bd96e9ca808eeb2e53b41d3fef";
        Y = "166";
        break;
      }
      if (!s()) {
        break;
      }
      str = "22710";
      localObject1 = "e708c4bd96e9ca808eeb2e53b41d3fef";
      Y = "168";
      break;
      label170:
      if (((String)localObject2).equalsIgnoreCase(e.getPackageName() + ":push")) {
        com.umeng.a.b.a(this, "PROCESS_UMPUSH_APPLICATION");
      } else if (((String)localObject2).equalsIgnoreCase(e.getPackageName() + ":servicerewards")) {
        com.umeng.a.b.a(this, "PROCESS_SERVICEREWARDS_APPLICATION");
      } else if (((String)localObject2).equalsIgnoreCase(e.getPackageName() + ":servicerewardsprot")) {
        com.umeng.a.b.a(this, "PROCESS_SERVICEREWARDSPROT_APPLICATION");
      }
    }
  }
  
  private void Z()
  {
    com.duapps.ad.base.a.a(this, "[{\"pid\":\"placeholder\",\"fbids\":[\"xxxxxxxxxxxxxxxx\"]}]".replace("placeholder", f()));
  }
  
  public static int a(Context paramContext, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (g > 0) {
        return g;
      }
    }
    else if (h > 0) {
      return h;
    }
    paramContext = paramContext.getResources().getDisplayMetrics();
    g = paramContext.widthPixels;
    h = paramContext.heightPixels;
    i = paramContext.density;
    com.xvideostudio.videoeditor.tool.k.b("cxs", "width" + paramContext.widthPixels);
    com.xvideostudio.videoeditor.tool.k.b("cxs", "height" + paramContext.heightPixels);
    if (g > h)
    {
      int i1 = h;
      h = g;
      g = i1;
    }
    if (paramBoolean) {
      return g;
    }
    return h;
  }
  
  public static String a(int paramInt)
  {
    if (F == null)
    {
      F = new HashMap(100);
      ac();
    }
    Iterator localIterator = F.entrySet().iterator();
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
  
  public static void a(Context paramContext, String paramString)
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
      if (O())
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
        com.umeng.a.b.a(paramContext, "BUY_PRO_GO_TO_GP_SUCCESSFUL");
        return;
      }
      i1 = i2;
      com.umeng.a.b.a(paramContext, "BUY_PRO_GO_TO_BROWSER_SUCCESSFUL");
      return;
    }
    catch (Exception paramString)
    {
      if (i1 == 0) {
        break label134;
      }
    }
    com.umeng.a.b.a(paramContext, "BUY_PRO_GO_TO_GP_FAILED");
    for (;;)
    {
      paramString.printStackTrace();
      return;
      label134:
      com.umeng.a.b.a(paramContext, "BUY_PRO_GO_TO_BROWSER_FAILED");
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    int i1 = 0;
    if (paramBoolean) {
      i1 = 1;
    }
    y.o(e, i1);
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
      if ((Tools.d(paramContext)) || (t()) || (s()))
      {
        i1 = 1;
        if ((i1 == 0) || (!e.m(paramContext))) {
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
  
  public static boolean a(String paramString)
  {
    if (A == null) {
      A = e.s(l());
    }
    if (A.equalsIgnoreCase(paramString)) {}
    do
    {
      return true;
      A = e.s(l());
    } while (A.equalsIgnoreCase(paramString));
    return false;
  }
  
  private void aa()
  {
    this.ak = PushAgent.getInstance(e);
    this.ak.setDebugMode(false);
    com.xvideostudio.videoeditor.tool.k.b("initUmPush", "getRegistrationId: " + UmengRegistrar.getRegistrationId(e));
    new com.xvideostudio.videoeditor.umengpush.a(null, this.ak, e.p(e), true).execute(new Void[0]);
  }
  
  private void ab()
  {
    Object localObject = new UmengMessageHandler()
    {
      public void dealWithCustomMessage(Context paramAnonymousContext, com.umeng.message.a.a paramAnonymousA)
      {
        new Handler(VideoEditorApplication.this.getMainLooper()).post(new VideoEditorApplication.4.1(this, paramAnonymousA));
      }
      
      public Notification getNotification(Context paramAnonymousContext, com.umeng.message.a.a paramAnonymousA)
      {
        switch (paramAnonymousA.r)
        {
        default: 
          return super.getNotification(paramAnonymousContext, paramAnonymousA);
        }
        NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(paramAnonymousContext);
        RemoteViews localRemoteViews = new RemoteViews(paramAnonymousContext.getPackageName(), 2130968925);
        localRemoteViews.setTextViewText(2131690702, paramAnonymousA.e);
        localRemoteViews.setTextViewText(2131690717, paramAnonymousA.f);
        localRemoteViews.setImageViewBitmap(2131690700, getLargeIcon(paramAnonymousContext, paramAnonymousA));
        localRemoteViews.setImageViewResource(2131690718, getSmallIconId(paramAnonymousContext, paramAnonymousA));
        localBuilder.setContent(localRemoteViews);
        paramAnonymousContext = localBuilder.build();
        paramAnonymousContext.flags = 16;
        paramAnonymousContext.contentView = localRemoteViews;
        return paramAnonymousContext;
      }
    };
    this.ak.setMessageHandler((UHandler)localObject);
    localObject = new UmengNotificationClickHandler()
    {
      public void dealWithCustomAction(Context paramAnonymousContext, com.umeng.message.a.a paramAnonymousA)
      {
        Object localObject = paramAnonymousA.s;
        if (((Map)localObject).containsKey("customType")) {}
        switch (Integer.valueOf((String)((Map)localObject).get("customType")).intValue())
        {
        default: 
          localObject = new Intent(paramAnonymousContext, MainActivity.class);
          ((Intent)localObject).addFlags(268435456);
          VideoEditorApplication.this.startActivity((Intent)localObject);
          Toast.makeText(paramAnonymousContext, paramAnonymousA.l, 1).show();
          return;
        }
        paramAnonymousContext = new Intent(paramAnonymousContext, MaterialActivity.class);
        paramAnonymousContext.addFlags(268435456);
        paramAnonymousContext.putExtra("custom", paramAnonymousA.l);
        paramAnonymousContext.putExtra("extraMsg", com.xvideostudio.videoeditor.umengpush.b.a((Map)localObject));
        VideoEditorApplication.this.startActivity(paramAnonymousContext);
      }
    };
    this.ak.setNotificationClickHandler((UHandler)localObject);
  }
  
  private static void ac()
  {
    F.put("1f001", Integer.valueOf(2130838091));
    F.put("1f002", Integer.valueOf(2130838092));
    F.put("1f003", Integer.valueOf(2130838093));
    F.put("1f004", Integer.valueOf(2130838094));
    F.put("1f005", Integer.valueOf(2130838095));
    F.put("1f006", Integer.valueOf(2130838096));
    F.put("1f007", Integer.valueOf(2130838097));
    F.put("1f008", Integer.valueOf(2130838098));
    F.put("1f009", Integer.valueOf(2130838099));
    F.put("1f010", Integer.valueOf(2130838100));
    F.put("1f011", Integer.valueOf(2130838101));
    F.put("1f012", Integer.valueOf(2130838102));
    F.put("1f013", Integer.valueOf(2130838103));
    F.put("1f014", Integer.valueOf(2130838104));
    F.put("1f015", Integer.valueOf(2130838105));
    F.put("1f016", Integer.valueOf(2130838106));
    F.put("1f017", Integer.valueOf(2130838107));
    F.put("1f018", Integer.valueOf(2130838108));
    F.put("1f019", Integer.valueOf(2130838109));
    F.put("1f020", Integer.valueOf(2130838110));
    F.put("1f021", Integer.valueOf(2130838111));
    F.put("1f022", Integer.valueOf(2130838112));
    F.put("1f023", Integer.valueOf(2130838113));
    F.put("1f024", Integer.valueOf(2130838114));
    F.put("1f025", Integer.valueOf(2130838115));
    F.put("1f026", Integer.valueOf(2130838116));
    F.put("1f027", Integer.valueOf(2130838117));
    F.put("1f028", Integer.valueOf(2130838118));
    F.put("1f029", Integer.valueOf(2130838119));
    F.put("1f030", Integer.valueOf(2130838120));
    F.put("1f031", Integer.valueOf(2130838121));
    F.put("1f032", Integer.valueOf(2130838122));
    F.put("3f001", Integer.valueOf(2130838123));
    F.put("3f002", Integer.valueOf(2130838124));
    F.put("3f003", Integer.valueOf(2130838125));
    F.put("3f004", Integer.valueOf(2130838126));
    F.put("3f005", Integer.valueOf(2130838127));
    F.put("3f006", Integer.valueOf(2130838128));
    F.put("3f007", Integer.valueOf(2130838129));
    F.put("3f008", Integer.valueOf(2130838130));
    F.put("3f009", Integer.valueOf(2130838131));
    F.put("3f010", Integer.valueOf(2130838132));
    F.put("3f011", Integer.valueOf(2130838133));
    F.put("3f012", Integer.valueOf(2130838134));
    F.put("3f013", Integer.valueOf(2130838135));
    F.put("3f014", Integer.valueOf(2130838136));
    F.put("3f015", Integer.valueOf(2130838137));
    F.put("3f016", Integer.valueOf(2130838138));
    F.put("3f017", Integer.valueOf(2130838139));
    F.put("3f018", Integer.valueOf(2130838140));
    F.put("3f019", Integer.valueOf(2130838141));
    F.put("3f020", Integer.valueOf(2130838142));
    F.put("3f021", Integer.valueOf(2130838143));
    F.put("3f022", Integer.valueOf(2130838144));
    F.put("3f023", Integer.valueOf(2130838145));
    F.put("3f024", Integer.valueOf(2130838146));
  }
  
  private void ad()
  {
    int i1 = Integer.valueOf(e.g()).intValue();
    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "the cpu frequency is " + i1 + "khz");
    int i2 = e.o();
    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "cpuCoreNums is " + i2);
    if (i2 < 2) {
      hl.productor.fxlib.b.A = false;
    }
    for (;;)
    {
      if (!hl.productor.fxlib.b.A) {
        y.k(e, 1);
      }
      if (true == hl.productor.fxlib.b.A) {
        if (HLRenderThread.detectGraphicsPerformance() == -1)
        {
          hl.productor.fxlib.b.A = false;
          y.k(e, 1);
        }
      }
      switch (HLRenderThread.queryValue(3) / 8)
      {
      case 3: 
      default: 
        return;
        if ((i2 == 2) && (i1 <= 1000000)) {
          hl.productor.fxlib.b.A = false;
        } else {
          hl.productor.fxlib.b.A = true;
        }
        break;
      }
    }
    hl.productor.fxlib.e.c = 1;
    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "detectGraphicsPerformance RGB565");
    y.k(e, 2);
    return;
    hl.productor.fxlib.e.c = 2;
    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "detectGraphicsPerformance RGBA8888");
    y.k(e, 3);
  }
  
  public static Typeface b(String paramString)
  {
    if (v.a(paramString))
    {
      if (af == null) {
        L();
      }
      if (af.containsKey(paramString)) {
        return (Typeface)af.get(paramString);
      }
    }
    else
    {
      if (y == null) {
        M();
      }
      if (y.containsKey(paramString)) {
        return (Typeface)y.get(paramString);
      }
    }
    return Typeface.SANS_SERIF;
  }
  
  public static void b(Activity paramActivity)
  {
    if (!K.containsKey("MainActivity"))
    {
      Intent localIntent = new Intent();
      localIntent.setClass(paramActivity, MainActivity.class);
      paramActivity.startActivity(localIntent);
    }
    paramActivity.finish();
  }
  
  public static int c(String paramString)
  {
    if (F == null)
    {
      F = new HashMap(100);
      ac();
    }
    if ((F != null) && (F.containsKey(paramString))) {
      return ((Integer)F.get(paramString)).intValue();
    }
    return 0;
  }
  
  public static boolean d(String paramString)
  {
    if (e == null) {
      return false;
    }
    Iterator localIterator = e.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equalsIgnoreCase(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean e(String paramString)
  {
    Iterator localIterator = ((ActivityManager)e.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (localIterator.hasNext()) {
      if (paramString.equals(((ActivityManager.RunningServiceInfo)localIterator.next()).service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  public static VideoEditorApplication l()
  {
    if (f == null) {
      f = new VideoEditorApplication();
    }
    return f;
  }
  
  public static boolean m()
  {
    if (a("com.xvideostudio.videoeditorpro"))
    {
      m = true;
      return true;
    }
    return false;
  }
  
  public static boolean n()
  {
    return (B) || (hl.productor.fxlib.b.ak) || (m());
  }
  
  public static boolean o()
  {
    return e.u().equals("zh-CN");
  }
  
  public static boolean p()
  {
    return !Tools.d(l());
  }
  
  public static boolean q()
  {
    if ((!p()) || (Build.VERSION.SDK_INT < 9)) {}
    while (((u()) && (y.K(l()))) || ((!t()) && (!u()) && (!s()))) {
      return false;
    }
    return true;
  }
  
  public static boolean r()
  {
    return false;
  }
  
  public static boolean s()
  {
    return a("com.xvideostudio.videoeditorlite");
  }
  
  public static boolean t()
  {
    return a("com.xvideostudio.videoeditorprofree");
  }
  
  public static boolean u()
  {
    return a("com.xvideostudio.videoeditor");
  }
  
  public static boolean x()
  {
    if (!l) {}
    while (y.p(e, 0) == 0) {
      return false;
    }
    return true;
  }
  
  public static String y()
  {
    if (!l) {
      return ab;
    }
    if (H().getInt("output_path_type", 0) == 0) {
      return ab;
    }
    return ae;
  }
  
  public static String z()
  {
    return ac;
  }
  
  public DraftBoxHandler I()
  {
    if (this.ag == null) {
      this.ag = new DraftBoxHandler();
    }
    return this.ag;
  }
  
  public org.xvideo.videoeditor.a.b J()
  {
    if (this.ah == null) {
      this.ah = new org.xvideo.videoeditor.a.b(getApplicationContext());
    }
    return this.ah;
  }
  
  public PaintDraftHandler K()
  {
    if (this.ai == null) {
      this.ai = new PaintDraftHandler();
    }
    return this.ai;
  }
  
  public void P()
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    this.an.sendMessage(localMessage);
  }
  
  public void Q()
  {
    int i2 = -1;
    int i4 = 1;
    for (;;)
    {
      try
      {
        Object localObject = y.N(e);
        if (localObject == null) {
          break label316;
        }
        i1 = ((String)localObject).indexOf(",");
        if (i1 <= -1) {
          break label316;
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
              hl.productor.fxlib.b.O = bool;
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
    str1 = com.xvideostudio.videoeditor.m.b.I();
    str2 = str1 + "export_720p_avc_test.mp4";
    com.xvideostudio.videoeditor.util.k.a(e, str2, 2131165190);
    if (!e.a(str2))
    {
      com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "this device can not surport 720P AVC HighProfile export");
      hl.productor.fxlib.b.O = false;
      str1 = str1 + "export_720p_mpeg4_test.mp4";
      com.xvideostudio.videoeditor.util.k.a(e, str1, 2131165191);
      if (!e.a(str1))
      {
        com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "this device can not surport 720P export");
        hl.productor.fxlib.b.f = 720;
        hl.productor.fxlib.b.f = 720;
        i1 = hl.productor.fxlib.b.f;
        if (hl.productor.fxlib.b.O)
        {
          i2 = i4;
          break label323;
        }
      }
    }
    label316:
    label323:
    for (;;)
    {
      y.d(e, i2 + "," + i1);
      return;
      i2 = 0;
      break label323;
      i2 = -1;
      i1 = 0;
      break;
    }
  }
  
  public boolean S()
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
          com.xvideostudio.videoeditor.tool.k.d("JNIMsg", "Codec: " + localMediaCodecInfo.getName());
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
              com.xvideostudio.videoeditor.tool.k.d("JNIMsg", "mimes: " + str);
              i5 = i2;
              i6 = i1;
              if (str.equalsIgnoreCase("video/avc"))
              {
                str = localMediaCodecInfo.getName();
                com.xvideostudio.videoeditor.tool.k.b("JNIMsg", "AVC encoder is " + str);
                i2 += 1;
                i5 = i2;
                i6 = i1;
                if (str.equalsIgnoreCase("OMX.sprd.h264.encoder"))
                {
                  com.umeng.a.b.a(e, "AVC_ENCODER_CHECK_OMX_SPRD_H264");
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
      com.umeng.a.b.a(e, "AVC_ENCODER_CHECK_H264_HW_ENCODE_SUPPORT");
      return true;
    }
    com.umeng.a.b.a(e, "AVC_ENCODER_CHECK_H264_HW_ENCODE_CANNOT_SUPPORT");
    return false;
  }
  
  public void T()
  {
    String str = e.m();
    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "cpu Architecture is " + str);
    int i1 = e.d();
    if (((str.equals("armeabi-v7a")) || (str.equals("arm64-v8a"))) && (i1 < 23))
    {
      com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "H264 encoding is enabled");
      hl.productor.fxlib.b.O = true;
      hl.productor.fxlib.b.D = 1;
      i1 = Math.min(e.j(e), e.k(e));
      com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "screenWidth = " + i1);
      if (i1 > 1446)
      {
        hl.productor.fxlib.b.g = i1;
        hl.productor.fxlib.b.h = i1;
      }
      G = e.y();
      com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "cameraMaxSize[0] = " + G[0]);
      com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "cameraMaxSize[1] = " + G[1]);
      if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > e.j(e) * e.k(e))
      {
        hl.productor.fxlib.b.e = Math.min(e.j(e), e.k(e));
        hl.productor.fxlib.b.f = Math.max(e.j(e), e.k(e));
        com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "Max output size restricted to " + e.j(e) + " x " + e.k(e));
      }
      if (hl.productor.fxlib.b.f * hl.productor.fxlib.b.e > G[0] * G[1])
      {
        hl.productor.fxlib.b.e = Math.min(G[0], G[1]);
        hl.productor.fxlib.b.f = Math.max(G[0], G[1]);
      }
      if (((e.j(e) * e.k(e) <= 384000) && (e.j(e) * e.k(e) == G[0] * G[1])) || (G[0] * G[1] < 384000))
      {
        com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "special machine , we need disable video_hw_encode_enable  ");
        hl.productor.fxlib.b.w = false;
        com.umeng.a.b.a(this, "EXPORT_FORCE_SET_HW_ENCODE_DISABLE");
      }
      if (G[2] != 1) {
        break label529;
      }
      if ((g * h <= 2073600) && (hl.productor.fxlib.b.V * hl.productor.fxlib.b.W > G[0] * G[1]))
      {
        hl.productor.fxlib.b.V = Math.min(G[0], G[1]);
        hl.productor.fxlib.b.W = Math.max(G[0], G[1]);
      }
      hl.productor.fxlib.b.X = true;
    }
    for (;;)
    {
      hl.productor.fxlib.b.U = 1;
      return;
      hl.productor.fxlib.b.O = false;
      hl.productor.fxlib.b.D = 0;
      break;
      label529:
      if ((g * h <= 921600) && (hl.productor.fxlib.b.V * hl.productor.fxlib.b.W > G[0] * G[1]))
      {
        hl.productor.fxlib.b.V = Math.min(G[0], G[1]);
        hl.productor.fxlib.b.W = Math.max(G[0], G[1]);
      }
    }
  }
  
  void U()
  {
    switch (y.l(e, 0))
    {
    default: 
      return;
    case 0: 
      ad();
      return;
    case 1: 
      hl.productor.fxlib.b.A = false;
      return;
    case 2: 
      hl.productor.fxlib.b.A = true;
      hl.productor.fxlib.e.c = 1;
      return;
    }
    hl.productor.fxlib.b.A = true;
    hl.productor.fxlib.e.c = 2;
  }
  
  public com.xvideostudio.videoeditor.materialdownload.c a()
  {
    if (this.J == null) {
      this.J = new com.xvideostudio.videoeditor.materialdownload.c(e);
    }
    return this.J;
  }
  
  public void a(StoryBoardView.a paramA)
  {
    this.aq = paramA;
  }
  
  public void a(String paramString, ImageView paramImageView, com.a.a.b.c paramC)
  {
    com.a.a.b.c localC = paramC;
    if (paramC == null) {
      localC = q.a(0, true, true, true);
    }
    if (this.aj == null)
    {
      this.aj = d.a();
      q.a(getApplicationContext(), com.xvideostudio.videoeditor.m.b.s());
    }
    this.aj.a(paramString, paramImageView, localC);
  }
  
  public void a(List<HotVideoDataparam.ContestItem> paramList)
  {
    this.Z = paramList;
  }
  
  public void a(final MediaClip paramMediaClip)
  {
    this.an.post(new Runnable()
    {
      public void run()
      {
        if (VideoEditorApplication.b(VideoEditorApplication.this) != null)
        {
          paramMediaClip.rotation = 64537;
          VideoEditorApplication.b(VideoEditorApplication.this).a(paramMediaClip);
        }
        l.a(VideoEditorApplication.this.getResources().getString(2131297170), -1, 1);
      }
    });
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    Object localObject1;
    Object localObject2;
    try
    {
      if (!y.d(e, e.d(e)))
      {
        if (paramBoolean1)
        {
          String str1 = com.xvideostudio.videoeditor.m.b.z();
          com.xvideostudio.videoeditor.util.k.b(str1);
          localObject1 = N().keySet().iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            localObject2 = (Map)N().get(localObject2);
            if ((localObject2 != null) && (((Map)localObject2).size() != 0))
            {
              Message localMessage = new Message();
              localMessage.what = 1;
              Bundle localBundle = new Bundle();
              localBundle.putInt("rawId", Integer.valueOf((String)((Map)localObject2).get("rawId")).intValue());
              localBundle.putString("rawFilePath", str1 + (String)((Map)localObject2).get("fileName"));
              localMessage.setData(localBundle);
              this.an.sendMessage(localMessage);
            }
          }
        }
      }
      else {
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
      str2 = com.xvideostudio.videoeditor.m.b.B();
      com.xvideostudio.videoeditor.util.k.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131165208);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "theme_new_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.an.sendMessage((Message)localObject1);
    }
    if (paramBoolean3)
    {
      str2 = com.xvideostudio.videoeditor.m.b.C();
      com.xvideostudio.videoeditor.util.k.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131165209);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "trans_new_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.an.sendMessage((Message)localObject1);
    }
    if (paramBoolean4)
    {
      str2 = com.xvideostudio.videoeditor.m.b.D();
      com.xvideostudio.videoeditor.util.k.b(str2);
      localObject1 = new Message();
      ((Message)localObject1).what = 1;
      localObject2 = new Bundle();
      ((Bundle)localObject2).putInt("rawId", 2131165206);
      ((Bundle)localObject2).putString("rawFilePath", str2 + "subtitle_style_temp.zip");
      ((Bundle)localObject2).putBoolean("isZip", true);
      ((Message)localObject1).setData((Bundle)localObject2);
      this.an.sendMessage((Message)localObject1);
    }
    y.a(e, true, e.d(e));
  }
  
  public Hashtable<Integer, SiteInfoBean> b()
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
          com.xvideostudio.videoeditor.e.b.a(MainActivity.a(VideoEditorApplication.e), 2, new VideoEditorApplication.8.1(this));
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  public List<Integer> c()
  {
    if (this.M == null) {
      this.M = new ArrayList();
    }
    return this.M;
  }
  
  public Map<Integer, Integer> d()
  {
    if (this.N == null) {
      this.N = new Hashtable();
    }
    return this.N;
  }
  
  public List<HotVideoDataparam.ContestItem> e()
  {
    if (this.Z == null) {
      this.Z = new ArrayList();
    }
    return this.Z;
  }
  
  public String f()
  {
    String str = "";
    if (s()) {
      str = "10859";
    }
    do
    {
      return str;
      if (t()) {
        return "10858";
      }
    } while (!u());
    return "10839";
  }
  
  public String g()
  {
    if (s()) {
      return "695461479160";
    }
    if (t()) {
      return "791583400776";
    }
    return "720153878853";
  }
  
  public void h()
  {
    if (this.am) {
      return;
    }
    this.am = true;
    ah.a("VideoEditorApplication onCreate before:");
    z = e.p(e);
    A = e.s(e);
    FontCenter.initFontCenter("TXCzRZwWI22l4Rcj6jAA", l());
    FontCenter.getInstance().setFolder_font(com.xvideostudio.videoeditor.m.b.H());
    FacebookSdk.sdkInitialize(l());
    this.aj = d.a();
    q.a(getApplicationContext(), com.xvideostudio.videoeditor.m.b.s());
    Q = e.p(e);
    com.xvideostudio.videoeditor.tool.k.b("language", Q);
    com.xvideostudio.videoeditor.tool.k.b("language", Q.substring(0, 2));
    Y();
  }
  
  public void i()
  {
    if (this.al) {
      return;
    }
    this.al = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          VideoEditorApplication.C = new File(com.xvideostudio.videoeditor.m.b.z()).exists();
          ak.a().a(VideoEditorApplication.e);
          VideoEditorApplication.this.v();
        }
        catch (Exception localException)
        {
          try
          {
            Object localObject = VideoEditorApplication.this.getApplicationContext().getPackageManager().getPackageInfo(VideoEditorApplication.this.getPackageName(), 0);
            VideoEditorApplication.j = ((PackageInfo)localObject).versionCode;
            VideoEditorApplication.k = ((PackageInfo)localObject).versionName;
            VideoEditorApplication.a(VideoEditorApplication.this).sendEmptyMessageDelayed(0, 10L);
            if (y.Y(VideoEditorApplication.l()))
            {
              com.umeng.a.b.a(VideoEditorApplication.l(), "HW_ENCODER_ERR_START_APP");
              com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_START_APP");
              if (!y.X(VideoEditorApplication.e))
              {
                com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "HW_ENCODER_ERR errTime:" + y.Z(VideoEditorApplication.l()) + " errResetTime:" + y.aa(VideoEditorApplication.l()));
                if (y.aa(VideoEditorApplication.l()) < 3) {
                  if (y.Z(VideoEditorApplication.l()) % 5 == 0)
                  {
                    y.t(VideoEditorApplication.e, false);
                    y.F(VideoEditorApplication.l(), 0);
                    if ((e.d() >= 18) && (VideoEditorApplication.this.S())) {
                      hl.productor.fxlib.b.w = true;
                    }
                    y.E(VideoEditorApplication.l(), y.aa(VideoEditorApplication.l()) + 1);
                    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                    com.umeng.a.b.a(VideoEditorApplication.l(), "HW_ENCODER_ERR_RESET_NEVER_SUCCESSFUL");
                    com.xvideostudio.videoeditor.tool.k.b("", "FxConfig.video_hw_encode_enable = " + hl.productor.fxlib.b.w);
                    VideoEditorApplication.this.T();
                    i = VideoEditorApplication.g;
                    int j = VideoEditorApplication.h;
                    com.xvideostudio.videoeditor.tool.k.b("", "screenWidth = " + i + "screenHeight = " + j);
                    if (i * j < 921600) {
                      hl.productor.fxlib.v.n = 0;
                    }
                    if (Math.min(hl.productor.fxlib.b.f, hl.productor.fxlib.b.e) >= 720) {
                      VideoEditorApplication.this.Q();
                    }
                    if (hl.productor.fxlib.b.A != true) {
                      break label566;
                    }
                    VideoEditorApplication.this.U();
                    if (hl.productor.fxlib.b.B)
                    {
                      localObject = VideoEditorApplication.e;
                      if (!hl.productor.fxlib.b.A) {
                        break label573;
                      }
                      i = 0;
                      i = y.j((Context)localObject, i);
                      if ((i != 0) || (hl.productor.fxlib.b.A)) {
                        break label578;
                      }
                      y.i(VideoEditorApplication.e, 1);
                    }
                    return;
                    localException = localException;
                    localException.printStackTrace();
                  }
                }
              }
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            int i;
            label566:
            label573:
            label578:
            do
            {
              for (;;)
              {
                localNameNotFoundException.printStackTrace();
                continue;
                y.F(VideoEditorApplication.l(), y.Z(VideoEditorApplication.l()) + 1);
                continue;
                com.umeng.a.b.a(VideoEditorApplication.l(), "HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
                com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_CONTINUAL_THREE_TIME");
                continue;
                com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "HW_ENCODER_ERR HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
                com.umeng.a.b.a(VideoEditorApplication.l(), "HW_ENCODER_ERR_RESET_EVER_SUCCESSFUL");
                y.t(VideoEditorApplication.e, false);
                y.F(VideoEditorApplication.l(), 0);
                if ((e.d() >= 18) && (VideoEditorApplication.this.S()))
                {
                  hl.productor.fxlib.b.w = true;
                  continue;
                  if (e.d() >= 18)
                  {
                    if (VideoEditorApplication.this.S()) {
                      hl.productor.fxlib.b.w = true;
                    }
                    com.umeng.a.b.a(VideoEditorApplication.l(), "HW_ENCODER_OS_UPTO_18");
                  }
                  else
                  {
                    com.umeng.a.b.a(VideoEditorApplication.l(), "HW_ENCODER_OS_BELOW_18");
                    continue;
                    hl.productor.fxlib.b.B = false;
                    continue;
                    i = 1;
                  }
                }
              }
            } while ((i != 1) || (!hl.productor.fxlib.b.A));
            y.i(VideoEditorApplication.e, 0);
          }
        }
      }
    }).start();
  }
  
  public void j()
  {
    V();
    ah.a("VideoEditorApplication onCreate after:");
    ac();
    k();
    if (!new File(com.xvideostudio.videoeditor.m.c.f(com.xvideostudio.videoeditor.m.c.f(1), 6)).exists()) {
      y.a(e, false, e.d(e));
    }
    for (;;)
    {
      a(true, true, true, true);
      try
      {
        aa();
        i1 = y.r(l(), 3);
        if (i1 == 1)
        {
          hl.productor.fxlib.b.P = false;
          hl.productor.fxlib.b.F = 1;
        }
      }
      catch (Exception localException1)
      {
        try
        {
          for (;;)
          {
            L();
            String str = com.xvideostudio.videoeditor.m.b.e() + "1.png";
            if (!com.xvideostudio.videoeditor.util.k.a(str)) {
              com.xvideostudio.videoeditor.util.k.a(e, 2131165210, str);
            }
            if (u())
            {
              i1 = y.L(e);
              if (i1 != 1) {
                break label234;
              }
              B = true;
            }
            return;
            if (new File(com.xvideostudio.videoeditor.m.b.D()).isDirectory()) {
              break;
            }
            y.a(e, false, e.d(e));
            break;
            localException1 = localException1;
            localException1.printStackTrace();
            continue;
            if (i1 == 2)
            {
              hl.productor.fxlib.b.P = false;
              hl.productor.fxlib.b.F = 2;
            }
            else if (i1 == 3)
            {
              hl.productor.fxlib.b.P = true;
              hl.productor.fxlib.b.F = 3;
            }
          }
        }
        catch (Exception localException2)
        {
          int i1;
          label234:
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
  
  public void k()
  {
    int i1;
    do
    {
      try
      {
        localObject = com.xvideostudio.videoeditor.m.b.F() + "VideoShowUserDB.db";
        if (!new File((String)localObject).exists())
        {
          if (!com.xvideostudio.videoeditor.util.k.b(com.xvideostudio.videoeditor.m.b.a(e).getAbsolutePath(), (String)localObject))
          {
            localObject = new j(e);
            ((j)localObject).a(((j)localObject).a());
            i1 = 9;
          }
          else
          {
            com.xvideostudio.videoeditor.util.k.a(j.a, 1);
            i1 = 1;
          }
        }
        else {
          i1 = com.xvideostudio.videoeditor.util.k.l(j.a);
        }
      }
      catch (Exception localException)
      {
        Object localObject;
        l.a(localException.getMessage(), 1);
        localException.printStackTrace();
        return;
      }
      localObject = new j(e);
      ((j)localObject).a(((j)localObject).a(), i1, 9);
      return;
    } while (i1 < 9);
  }
  
  public void onCreate()
  {
    super.onCreate();
    e = this;
    f = this;
    com.xvideostudio.videoeditor.tool.k.a(e);
    if ((c.a(this).booleanValue()) && (!com.xvideostudio.videoeditor.util.k.a(b.d))) {
      c.g(e, Boolean.valueOf(true));
    }
    if (!c.B(e).booleanValue()) {
      hl.productor.fxlib.b.ah = 1;
    }
    VscUserinfoSession.setmApplicationContext(e);
    a(this, true);
    new AsyncTask()
    {
      protected Integer a(Object... paramAnonymousVarArgs)
      {
        return null;
      }
    };
    com.xvideostudio.videoeditor.tool.k.b("VideoEditorApplication", "Application start");
    h();
  }
  
  public void v()
  {
    m();
    w();
  }
  
  public void w()
  {
    Object localObject = "";
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      localObject = Environment.getExternalStorageDirectory().getAbsolutePath();
      com.xvideostudio.videoeditor.tool.k.b("cxs", "Sd1 path:" + (String)localObject);
    }
    String str = com.xvideostudio.videoeditor.m.b.a();
    if ((str != null) && (!((String)localObject).equalsIgnoreCase(str)) && (!str.startsWith("/storage/emulated/legacy")))
    {
      com.xvideostudio.videoeditor.tool.k.b("cxs", "Sd2 path:" + str);
      ae = str + File.separator + com.xvideostudio.videoeditor.m.b.a;
      com.xvideostudio.videoeditor.util.k.b(ae);
      l = true;
    }
    try
    {
      localObject = new File(ae + aj.a() + ".test");
      ((File)localObject).createNewFile();
      ((File)localObject).delete();
      ab = com.xvideostudio.videoeditor.m.b.m();
      ac = com.xvideostudio.videoeditor.m.b.e();
      ad = com.xvideostudio.videoeditor.m.b.d();
      if ((!l) && (x() == true)) {
        a(false);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        l = false;
        localException.printStackTrace();
      }
    }
  }
}
