package com.dywx.larkplayer;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import com.crashlytics.android.Crashlytics;
import com.dywx.larkplayer.gui.ads.utils.AdsInstaller.HeadsetReceiver;
import com.facebook.appevents.aux;
import com.wandoujia.udid.UDIDUtil;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Fabric.Builder;
import io.fabric.sdk.android.Kit;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import o.K;
import o.cxz;
import o.cyc;
import o.dab;
import o.des;
import o.deu;
import o.dgf;
import o.dgm;
import o.dgt;
import o.dgx;
import o.dgy;
import o.dha;
import o.dhp;
import o.dhy;
import o.dig;
import o.djg;
import o.ӱ;
import o.ט;
import o.צ;
import o.ঢ;
import o.ঢ.ˊ;
import o.দ;
import o.ন;
import o.ব;
import o.ব.1;
import o.ব.if;
import o.ব.ˊ;
import o.ต;
import o.ე;
import o.ე.if;
import o.პ;
import o.ᐯ;
import o.ᐴ;
import o.ᒯ;
import o.ᒯ.if;
import o.ᒵ;
import o.ᒹ;
import o.ᓮ;
import o.ᖅ;
import o.ᖭ;
import o.ᖾ;
import o.ᗀ.if;
import o.ᗁ;
import o.ᴬ;
import o.ᵩ;
import o.ᵩ.if;
import o.ḻ;
import o.っ;
import o.ﯦ;
import o.ﺙ;
import o.ﺙ.if;
import rx.Observable;
import rx.schedulers.Schedulers;

public class LarkPlayerApplication
  extends Application
{
  private static LarkPlayerApplication ʻ;
  private static SharedPreferences ʼ;
  private static boolean ʽ;
  private static int ˈ = 0;
  public static final String ˊ = K.ˋ("SleepIntent");
  public static Calendar ˋ = null;
  public static final ThreadFactory ˏ;
  private static ᴬ<String, Object> ͺ;
  private static ArrayList<პ> ι;
  private final Handler ʾ = new Handler();
  private ThreadPoolExecutor ʿ = new ThreadPoolExecutor(0, 2, 2L, TimeUnit.SECONDS, new LinkedBlockingQueue(), ˏ);
  private ᐯ ˉ;
  public djg ˎ;
  private final boolean ᐝ = false;
  
  static
  {
    ʽ = false;
    ͺ = new ᴬ();
    ˏ = new ThreadFactory()
    {
      public final Thread newThread(Runnable paramAnonymousRunnable)
      {
        paramAnonymousRunnable = new Thread(paramAnonymousRunnable);
        paramAnonymousRunnable.setPriority(1);
        return paramAnonymousRunnable;
      }
    };
  }
  
  public LarkPlayerApplication() {}
  
  /* Error */
  private void ʻ()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 106	o/cyr:ˊ	(Landroid/content/Context;)Lo/cyr;
    //   4: pop
    //   5: invokestatic 111	o/dab:ˊ	()Lo/dab;
    //   8: astore 8
    //   10: aload 8
    //   12: getfield 114	o/dab:ᐝ	Ljava/util/concurrent/locks/ReadWriteLock;
    //   15: invokeinterface 120 1 0
    //   20: invokeinterface 125 1 0
    //   25: aload 8
    //   27: getfield 128	o/dab:ˎ	Lo/bwv;
    //   30: ifnull +134 -> 164
    //   33: aload 8
    //   35: getfield 128	o/dab:ˎ	Lo/bwv;
    //   38: getfield 133	o/bwv:ˎ	Ljava/util/Map;
    //   41: ifnull +123 -> 164
    //   44: aload 8
    //   46: getfield 128	o/dab:ˎ	Lo/bwv;
    //   49: getfield 133	o/bwv:ˎ	Ljava/util/Map;
    //   52: ldc -121
    //   54: invokeinterface 141 2 0
    //   59: ifnull +105 -> 164
    //   62: aload 8
    //   64: getfield 128	o/dab:ˎ	Lo/bwv;
    //   67: getfield 133	o/bwv:ˎ	Ljava/util/Map;
    //   70: ldc -121
    //   72: invokeinterface 141 2 0
    //   77: checkcast 143	o/bwq
    //   80: astore_2
    //   81: ldc -112
    //   83: aload_2
    //   84: getfield 146	o/bwq:ˊ	I
    //   87: if_icmpne +77 -> 164
    //   90: aload 8
    //   92: getfield 128	o/dab:ˎ	Lo/bwv;
    //   95: getfield 149	o/bwv:ˋ	J
    //   98: aload_2
    //   99: getfield 150	o/bwq:ˋ	J
    //   102: lcmp
    //   103: ifne +61 -> 164
    //   106: ldc -104
    //   108: iconst_3
    //   109: invokestatic 158	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   112: ifeq +11 -> 123
    //   115: ldc -104
    //   117: ldc -96
    //   119: invokestatic 164	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   122: pop
    //   123: aload 8
    //   125: getfield 114	o/dab:ᐝ	Ljava/util/concurrent/locks/ReadWriteLock;
    //   128: invokeinterface 120 1 0
    //   133: invokeinterface 167 1 0
    //   138: aload 8
    //   140: invokevirtual 170	o/dab:ˋ	()Z
    //   143: pop
    //   144: aload 8
    //   146: invokevirtual 173	o/dab:ˏ	()Lo/cyc;
    //   149: new 16	com/dywx/larkplayer/LarkPlayerApplication$6
    //   152: dup
    //   153: aload_0
    //   154: aload 8
    //   156: invokespecial 176	com/dywx/larkplayer/LarkPlayerApplication$6:<init>	(Lcom/dywx/larkplayer/LarkPlayerApplication;Lo/dab;)V
    //   159: invokevirtual 181	o/cyc:ˊ	(Lo/cxz;)Lo/cyc;
    //   162: pop
    //   163: return
    //   164: aload 8
    //   166: getfield 114	o/dab:ᐝ	Ljava/util/concurrent/locks/ReadWriteLock;
    //   169: invokeinterface 120 1 0
    //   174: invokeinterface 167 1 0
    //   179: new 183	java/util/HashMap
    //   182: dup
    //   183: invokespecial 184	java/util/HashMap:<init>	()V
    //   186: astore 9
    //   188: aload 8
    //   190: getfield 187	o/dab:ˏ	Landroid/content/Context;
    //   193: invokevirtual 193	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   196: ldc -112
    //   198: invokevirtual 199	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   201: astore 10
    //   203: aload 10
    //   205: invokeinterface 205 1 0
    //   210: istore_1
    //   211: aconst_null
    //   212: astore_2
    //   213: aconst_null
    //   214: astore 7
    //   216: aconst_null
    //   217: astore_3
    //   218: iload_1
    //   219: iconst_1
    //   220: if_icmpeq +220 -> 440
    //   223: iload_1
    //   224: iconst_2
    //   225: if_icmpne +67 -> 292
    //   228: aload 10
    //   230: invokeinterface 209 1 0
    //   235: astore 6
    //   237: aload_2
    //   238: astore 5
    //   240: aload_3
    //   241: astore 4
    //   243: aload 10
    //   245: invokeinterface 212 1 0
    //   250: istore_1
    //   251: aload 4
    //   253: astore_3
    //   254: aload 5
    //   256: astore_2
    //   257: aload 6
    //   259: astore 7
    //   261: goto -43 -> 218
    //   264: astore_2
    //   265: aload 8
    //   267: getfield 114	o/dab:ᐝ	Ljava/util/concurrent/locks/ReadWriteLock;
    //   270: invokeinterface 120 1 0
    //   275: invokeinterface 167 1 0
    //   280: aload_2
    //   281: athrow
    //   282: astore_2
    //   283: ldc -104
    //   285: ldc -42
    //   287: invokestatic 164	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   290: pop
    //   291: return
    //   292: iload_1
    //   293: iconst_3
    //   294: if_icmpne +63 -> 357
    //   297: aload_3
    //   298: astore 4
    //   300: aload_2
    //   301: astore 5
    //   303: ldc -40
    //   305: aload 10
    //   307: invokeinterface 209 1 0
    //   312: invokevirtual 222	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   315: ifeq +185 -> 500
    //   318: aload_3
    //   319: astore 4
    //   321: aload_2
    //   322: astore 5
    //   324: aload_2
    //   325: ifnull +175 -> 500
    //   328: aload_3
    //   329: astore 4
    //   331: aload_2
    //   332: astore 5
    //   334: aload_3
    //   335: ifnull +165 -> 500
    //   338: aload 9
    //   340: aload_2
    //   341: aload_3
    //   342: invokeinterface 226 3 0
    //   347: pop
    //   348: aconst_null
    //   349: astore 4
    //   351: aconst_null
    //   352: astore 5
    //   354: goto +146 -> 500
    //   357: aload_3
    //   358: astore 4
    //   360: aload_2
    //   361: astore 5
    //   363: aload 7
    //   365: astore 6
    //   367: iload_1
    //   368: iconst_4
    //   369: if_icmpne -126 -> 243
    //   372: ldc -28
    //   374: aload 7
    //   376: invokevirtual 222	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   379: ifeq +22 -> 401
    //   382: aload 10
    //   384: invokeinterface 231 1 0
    //   389: astore 5
    //   391: aload_3
    //   392: astore 4
    //   394: aload 7
    //   396: astore 6
    //   398: goto -155 -> 243
    //   401: aload_3
    //   402: astore 4
    //   404: aload_2
    //   405: astore 5
    //   407: aload 7
    //   409: astore 6
    //   411: ldc -23
    //   413: aload 7
    //   415: invokevirtual 222	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   418: ifeq -175 -> 243
    //   421: aload 10
    //   423: invokeinterface 231 1 0
    //   428: astore 4
    //   430: aload_2
    //   431: astore 5
    //   433: aload 7
    //   435: astore 6
    //   437: goto -194 -> 243
    //   440: new 143	o/bwq
    //   443: dup
    //   444: ldc -112
    //   446: aload 8
    //   448: getfield 128	o/dab:ˎ	Lo/bwv;
    //   451: getfield 149	o/bwv:ˋ	J
    //   454: invokespecial 236	o/bwq:<init>	(IJ)V
    //   457: astore_2
    //   458: aload 8
    //   460: getfield 128	o/dab:ˎ	Lo/bwv;
    //   463: getfield 133	o/bwv:ˎ	Ljava/util/Map;
    //   466: ldc -121
    //   468: aload_2
    //   469: invokeinterface 226 3 0
    //   474: pop
    //   475: aload 8
    //   477: aload 9
    //   479: ldc -121
    //   481: invokevirtual 239	o/dab:ˊ	(Ljava/util/Map;Ljava/lang/String;)V
    //   484: goto -346 -> 138
    //   487: astore_2
    //   488: ldc -104
    //   490: ldc -15
    //   492: aload_2
    //   493: invokestatic 245	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   496: pop
    //   497: goto -359 -> 138
    //   500: aconst_null
    //   501: astore 6
    //   503: goto -260 -> 243
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	506	0	this	LarkPlayerApplication
    //   210	160	1	i	int
    //   80	177	2	localObject1	Object
    //   264	17	2	localObject2	Object
    //   282	149	2	localException1	Exception
    //   457	12	2	localBwq	o.bwq
    //   487	6	2	localException2	Exception
    //   217	185	3	localObject3	Object
    //   241	188	4	localObject4	Object
    //   238	194	5	localObject5	Object
    //   235	267	6	localObject6	Object
    //   214	220	7	localObject7	Object
    //   8	468	8	localDab	dab
    //   186	292	9	localHashMap	java.util.HashMap
    //   201	221	10	localXmlResourceParser	android.content.res.XmlResourceParser
    // Exception table:
    //   from	to	target	type
    //   25	123	264	finally
    //   0	25	282	java/lang/Exception
    //   123	138	282	java/lang/Exception
    //   138	163	282	java/lang/Exception
    //   164	188	282	java/lang/Exception
    //   265	282	282	java/lang/Exception
    //   488	497	282	java/lang/Exception
    //   188	211	487	java/lang/Exception
    //   228	237	487	java/lang/Exception
    //   243	251	487	java/lang/Exception
    //   303	318	487	java/lang/Exception
    //   338	348	487	java/lang/Exception
    //   372	391	487	java/lang/Exception
    //   411	430	487	java/lang/Exception
    //   440	484	487	java/lang/Exception
  }
  
  public static Object ˊ(String paramString)
  {
    return ͺ.remove(paramString);
  }
  
  public static ArrayList<პ> ˊ()
  {
    return ι;
  }
  
  public static void ˊ(Window paramWindow, boolean paramBoolean)
  {
    if (paramWindow == null) {}
    do
    {
      return;
      if (Build.VERSION.SDK_INT >= 19) {
        paramWindow.clearFlags(67108864);
      }
    } while (Build.VERSION.SDK_INT < 21);
    paramWindow.addFlags(Integer.MIN_VALUE);
    if (paramBoolean)
    {
      paramWindow.setStatusBarColor(ე.if.ˊ.ˋ(2131099846));
      if (ე.if.ˊ.ʻ())
      {
        paramWindow.setNavigationBarColor(ე.if.ˊ.ˋ(2131099847));
        return;
      }
      paramWindow.setNavigationBarColor(ე.if.ˊ.ˋ(2131099846));
      return;
    }
    paramWindow.setStatusBarColor(ﯦ.ˎ(ʻ, 2131099846));
    paramWindow.setNavigationBarColor(ﯦ.ˎ(ʻ, 2131099846));
  }
  
  public static void ˊ(Runnable paramRunnable)
  {
    ʻ.ʿ.execute(paramRunnable);
  }
  
  public static void ˊ(String paramString, Object paramObject)
  {
    ͺ.put(paramString, paramObject);
  }
  
  public static boolean ˋ(Runnable paramRunnable)
  {
    return ʻ.ʿ.remove(paramRunnable);
  }
  
  public static Context ˎ()
  {
    return ʻ;
  }
  
  public static Resources ˏ()
  {
    return ʻ.getResources();
  }
  
  public static LarkPlayerApplication ᐝ()
  {
    return ʻ;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    ᖭ.ˊ(paramContext);
    Thread.setDefaultUncaughtExceptionHandler(new צ(this));
  }
  
  public Object getSystemService(String paramString)
  {
    if (dgt.ˊ.equals(paramString)) {
      return this.ˉ;
    }
    try
    {
      paramString = super.getSystemService(paramString);
      return paramString;
    }
    catch (Throwable paramString) {}
    return null;
  }
  
  public void onCreate()
  {
    boolean bool2 = true;
    super.onCreate();
    ʻ = this;
    if (ᖭ.ˋ(this)) {
      return;
    }
    if (dha.ˊ(this).endsWith(":alive")) {
      ʻ();
    }
    if (dha.ˊ(this).contains(":"))
    {
      ʻ();
      return;
    }
    dhp.ˊ(this);
    Fabric.with(new Fabric.Builder(this).kits(new Kit[] { new Crashlytics() }).build());
    Observable.fromCallable(new Callable()
    {
      private Void ˊ()
        throws Exception
      {
        Object localObject2 = LarkPlayerApplication.this;
        Crashlytics.setString("lang", ᖅ.ˊ());
        Crashlytics.setString("region", dhy.ˏ((Context)localObject2));
        Crashlytics.setString("locale", Locale.getDefault().toString());
        try
        {
          Crashlytics.setString("cpu_abis", K.ˊ(",", Arrays.asList(dhy.ˋ())));
          Object localObject1 = "";
          try
          {
            localObject2 = UDIDUtil.ˊ((Context)localObject2);
            localObject1 = localObject2;
          }
          catch (Throwable localThrowable2)
          {
            for (;;)
            {
              localThrowable2.printStackTrace();
            }
          }
          Crashlytics.setUserIdentifier((String)localObject1);
          return null;
        }
        catch (Throwable localThrowable1)
        {
          for (;;)
          {
            localThrowable1.printStackTrace();
          }
        }
      }
    }).subscribeOn(Schedulers.io()).subscribe(dgx.ˊ());
    ʻ();
    Object localObject1 = ᒯ.ˏ();
    ((ᒯ.if)localObject1).ˋ = ((deu)dig.ˊ(new deu(this)));
    ((ᒯ.if)localObject1).ˊ = ((ᐴ)dig.ˊ(new ᐴ(this)));
    if (((ᒯ.if)localObject1).ˊ == null) {
      throw new IllegalStateException(ᐴ.class.getCanonicalName() + " must be set");
    }
    if (((ᒯ.if)localObject1).ˋ == null) {
      throw new IllegalStateException(deu.class.getCanonicalName() + " must be set");
    }
    this.ˉ = new ᒯ((ᒯ.if)localObject1, (byte)0);
    ((ᐯ)dgt.ˊ(this)).ˊ(this);
    ᒵ.ˊ();
    ᒹ.ˊ().ˋ();
    ﺙ.ˊ(this, new ﺙ.if()
    {
      public final void ˊ()
      {
        aux.ˊ(LarkPlayerApplication.this);
      }
    });
    ʼ = PreferenceManager.getDefaultSharedPreferences(this);
    ე localე = ე.if.ˊ;
    localე.ˊ = getApplicationContext();
    localე.ˋ = new ต(localე.ˊ);
    Object localObject3 = localე.ˋ.ˊ.getString("key_plugin_path", "").split(ต.ˋ);
    if (localObject3.length > 0) {
      localObject1 = localObject3[0];
    }
    for (;;)
    {
      Object localObject2;
      if (localObject3.length > 1)
      {
        localObject2 = localObject3[1];
        label375:
        if (localObject3.length <= 2) {
          break label821;
        }
        localObject3 = localObject3[2];
        label388:
        localე.ˏ = ((String)localObject3);
        if ((TextUtils.isEmpty((CharSequence)localObject1)) || (!new File((String)localObject1).exists()) || (!ე.ˋ((String)localObject2))) {}
      }
      try
      {
        localე.ˊ((String)localObject1, (String)localObject2, localე.ˏ);
        localე.ᐝ = ((String)localObject1);
        localე.ʻ = ((String)localObject2);
        new Thread(new Runnable()
        {
          public final void run()
          {
            LarkPlayerApplication.this.ˋ();
          }
        }).run();
        ᖾ.ˊ();
        দ.ˊ();
        dgf.ˊ(this);
        if (!ᓮ.ᐝ()) {
          ᓮ.ˊ(false);
        }
        ᗁ.ʻ().ˏ();
        Log.d("LarkPlayerScanner", "Application.onCreate");
        ʽ = ʼ.getBoolean("key_first_run", true);
        Log.d("LarkPlayerScanner", "isFirstRun = " + ʽ);
        if (ʽ)
        {
          localObject1 = ʼ.edit();
          ((SharedPreferences.Editor)localObject1).putBoolean("key_first_run", false);
          っ.ˊ((SharedPreferences.Editor)localObject1);
          ᗁ.ʻ().ˋ();
          ט.ˊ(this);
          localObject1 = new IntentFilter();
          ((IntentFilter)localObject1).addAction("android.intent.action.HEADSET_PLUG");
          registerReceiver(new AdsInstaller.HeadsetReceiver((byte)0), (IntentFilter)localObject1);
          localObject1 = ঢ.ͺ();
          if (((ঢ.ˊ)localObject1).ˊ)
          {
            localObject2 = new StringBuilder("WTF: KeepAliveConfig is enabled! doubleService :");
            if ((!((ঢ.ˊ)localObject1).ˊ) || (!((ঢ.ˊ)localObject1).ˋ)) {
              break label903;
            }
            bool1 = true;
            localObject2 = ((StringBuilder)localObject2).append(bool1).append(" jobScheduler: ");
            if ((!((ঢ.ˊ)localObject1).ˊ) || (!((ঢ.ˊ)localObject1).ˏ)) {
              break label908;
            }
            bool1 = true;
            localObject2 = ((StringBuilder)localObject2).append(bool1).append(" useHidden1px:");
            if ((!((ঢ.ˊ)localObject1).ˊ) || (!((ঢ.ˊ)localObject1).ˎ)) {
              break label913;
            }
            bool1 = bool2;
            ӱ.ˋ(new RuntimeException(bool1));
          }
        }
      }
      catch (Exception localException)
      {
        try
        {
          for (;;)
          {
            des.ˊ(this);
            try
            {
              localObject1 = ব.ˊ(this);
              if (TextUtils.isEmpty(((ব)localObject1).ˊ(ব.ˊ.ˊ, ব.if.ˋ)))
              {
                localObject2 = new ᵩ.if(((ব)localObject1).ˋ, (byte)0);
                if (((ᵩ.if)localObject2).ˊ != null) {
                  break label926;
                }
                throw new IllegalArgumentException("Please provide a valid Context.");
              }
            }
            catch (Throwable localThrowable1)
            {
              Crashlytics.logException(localThrowable1);
            }
            ᗀ.if.ᐝ.ˊ(this);
            return;
            String str = "";
            break;
            localObject2 = "";
            break label375;
            label821:
            localObject3 = "";
            break label388;
            localException = localException;
            localე.ˋ.ˊ();
            localException.printStackTrace();
            continue;
            Log.d("LarkPlayerScanner", "auto_rescan = " + ʼ.getBoolean("auto_rescan", true));
            this.ʾ.postDelayed(new Runnable()
            {
              public final void run()
              {
                ᗁ.ʻ().ˋ();
              }
            }, 5000L);
            continue;
            label903:
            bool1 = false;
            continue;
            label908:
            bool1 = false;
          }
          label913:
          boolean bool1 = false;
        }
        catch (Throwable localThrowable2)
        {
          for (;;)
          {
            Crashlytics.logException(localThrowable2);
            continue;
            label926:
            localObject2 = new ḻ(((ᵩ.if)localObject2).ˊ);
            ((ᵩ)localObject2).ˊ(new ব.1(localThrowable2, (ᵩ)localObject2));
          }
        }
      }
    }
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    ন.ˊ().ˋ();
  }
  
  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    if (dha.ˊ(this).contains(":")) {
      return;
    }
    ন.ˊ().ˋ();
  }
  
  public final void ˋ()
  {
    Object localObject1 = getPackageManager();
    try
    {
      localObject1 = ((PackageManager)localObject1).getInstalledPackages(8192);
      if (ι != null) {
        ι.clear();
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        String str = ((PackageInfo)localObject2).packageName;
        if ((((PackageInfo)localObject2).sharedUserId != null) && (((PackageInfo)localObject2).sharedUserId.equals("com.dywx.larkplayer")) && (!str.equals(getPackageName())))
        {
          localObject2 = new პ(((PackageInfo)localObject2).applicationInfo.sourceDir, str, ((PackageInfo)localObject2).versionCode, ((PackageInfo)localObject2).versionName);
          if (ι == null) {
            ι = new ArrayList();
          }
          ι.add(localObject2);
        }
      }
      return;
    }
    catch (Exception localException)
    {
      dgy.ˊ(localException);
    }
  }
}
