package mbinc12.mb32b;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.provider.MediaStore.Audio.Playlists;
import android.provider.Settings.System;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.PhoneStateListener;
import android.text.Editable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import apb;
import apd;
import apg;
import apj;
import apl;
import apm;
import apn;
import apq;
import apu;
import apv;
import aqc;
import aqd;
import aqe;
import aqf;
import aqs;
import aqt;
import aqt.10;
import aqt.4;
import aqt.8;
import aqt.9;
import aqu;
import aqy;
import aqz;
import arc;
import ard;
import are;
import arf;
import arg;
import arh;
import ari;
import ari.8;
import arj;
import ark;
import arl;
import arm;
import arn;
import aro;
import arp;
import art;
import aru;
import arv;
import arw;
import arx;
import arz;
import asa;
import asb;
import asc;
import ask;
import asn;
import ass;
import ast;
import atf;
import ath;
import ati;
import atk;
import atl.a;
import atm;
import att;
import cd;
import cd.e;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.NativeAd;
import com.facebook.model.GraphUser;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageApi.MessageListener;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;
import com.mopub.nativeads.StaticNativeAd;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TimeZone;
import kt;
import mbinc12.mb32b.cast.MyMediaRouteButton;
import mbinc12.mb32b.classes.MyAutoCompleteTextView;
import mbinc12.mb32b.fragments.SongFragment;
import mbinc12.mb32b.services.ConnectionChangeReceiver;
import mbinc12.mb32b.services.EarphoneReceiver;
import mbinc12.mb32b.services.MyReceiver;
import mbinc12.mb32b.services.MyService;
import mbinc12.mb32b.services.PlayerReceiver;
import mbinc12.mb32b.services.RemoteControlReceiver;
import mbinc12.mb32b.services.ScreenReceiver;
import mbinc12.mb32b.services.SleepReceiver;
import mbinc12.mb32b.services.SystemDialogReceiver;
import mbinc12.mb32b.services.WindowPlayerService;
import mbinc12.mb32b.services.WindowPlayerService.b;
import mbinc12.mb32b.services.WindowPlayerService.r;
import mbinc12.mb32b.utils.MixerBoxUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tl;
import tl.a;
import vf;
import vf.c;
import xh;

public class MainPage
  extends ActionBarActivity
  implements MessageApi.MessageListener, WindowPlayerService.r
{
  public static Stack<Integer> K;
  private static String aT = "";
  public static boolean aq;
  static Bundle as;
  static boolean au;
  public static int e;
  public static boolean f;
  static int j = 0;
  public List<aqc> A;
  public List<aqc> B;
  public List<aqc> C;
  public List<aqc> D;
  public ArrayList<apn> E;
  public ArrayList<apn> F;
  public int G;
  public int H;
  public apn I;
  public apm J;
  public ArrayList<apm> L;
  public ArrayList<apm> M;
  public ArrayList<apn> N;
  int O;
  int P;
  int Q;
  int R;
  MoPubView S;
  public RewardedVideoAd T;
  public apl U;
  public NotificationManager V;
  final String W = "PlayerBroadcastReceiver";
  PlayerReceiver X;
  SleepReceiver Y;
  ScreenReceiver Z;
  public String a = asb.a[0];
  public int aA = 1;
  public String aB = "";
  public String aC = "";
  public boolean aD = false;
  boolean aE = false;
  public boolean aF = false;
  public boolean aG = false;
  List<String> aH = Arrays.asList(new String[] { "publish_actions" });
  public boolean aI = false;
  public WindowPlayerService.b aJ = null;
  public ServiceConnection aK = new ServiceConnection()
  {
    public final void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      MainPage.this.aJ = ((WindowPlayerService.b)paramAnonymousIBinder);
      if (MainPage.g(MainPage.this) != null)
      {
        paramAnonymousComponentName = MainPage.this.aJ;
        paramAnonymousIBinder = MainPage.g(MainPage.this);
        paramAnonymousComponentName.b.ay = paramAnonymousIBinder;
        paramAnonymousComponentName = paramAnonymousComponentName.b;
        if (paramAnonymousComponentName.ay != null)
        {
          paramAnonymousIBinder = paramAnonymousComponentName.ay;
          paramAnonymousIBinder.b();
          paramAnonymousIBinder.e.addSessionManagerListener(paramAnonymousIBinder, CastSession.class);
          if (paramAnonymousIBinder.b == null) {
            paramAnonymousIBinder.b = paramAnonymousIBinder.e.getCurrentCastSession();
          }
          paramAnonymousIBinder = paramAnonymousComponentName.ay;
          paramAnonymousIBinder.c = paramAnonymousComponentName.am;
          CastButtonFactory.setUpMediaRouteButton(paramAnonymousIBinder.a, paramAnonymousIBinder.c);
          paramAnonymousIBinder.c.setApplicationContext(paramAnonymousIBinder.a.getApplicationContext());
          paramAnonymousIBinder.c.setRouteSelector(paramAnonymousIBinder.d);
          paramAnonymousIBinder.a();
          paramAnonymousIBinder.c();
          paramAnonymousComponentName.ay.f = paramAnonymousComponentName.b;
        }
      }
      MainPage.h(MainPage.this).removeMessages(2);
      MainPage.h(MainPage.this).sendEmptyMessageDelayed(2, 30000L);
    }
    
    public final void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      if (MainPage.g(MainPage.this) != null) {
        MainPage.g(MainPage.this).b();
      }
      MainPage.this.aJ = null;
    }
  };
  Messenger aL = new Messenger(this.br);
  PhoneStateListener aM = new PhoneStateListener()
  {
    protected boolean a = false;
    
    public final void onCallStateChanged(int paramAnonymousInt, String paramAnonymousString)
    {
      if ((MainPage.this.aJ == null) || (!MainPage.this.aJ.b.a())) {
        return;
      }
      if (paramAnonymousInt == 1) {
        if ((MainPage.this.I != null) && ((MainPage.this.I.f == 3) || (MainPage.this.I.f == 2)))
        {
          if ((MyService.a != null) && (MyService.a.isPlaying()))
          {
            MyService.a.pause();
            if (MainPage.this.aJ != null) {
              MainPage.this.aJ.b(true);
            }
            this.a = true;
          }
          MainPage.this.d();
        }
      }
      for (;;)
      {
        super.onCallStateChanged(paramAnonymousInt, paramAnonymousString);
        return;
        if ((MainPage.this.b == 1) || (MainPage.this.b == 3))
        {
          MainPage.a(MainPage.this, false, MainPage.this.H);
          if (MainPage.this.aJ != null) {
            MainPage.this.aJ.f("javascript:pauseVideo()");
          }
          if (MainPage.this.aJ != null) {
            MainPage.this.aJ.b(true);
          }
          this.a = true;
          continue;
          if ((paramAnonymousInt == 0) && (this.a))
          {
            this.a = false;
            Object localObject;
            try
            {
              if ((MainPage.this.I == null) || ((MainPage.this.I.f != 3) && (MainPage.this.I.f != 2))) {
                break label345;
              }
              if (MyService.a != null)
              {
                MyService.a.start();
                if (MainPage.this.aJ != null) {
                  MainPage.this.aJ.b(false);
                }
              }
              MainPage.this.d();
            }
            catch (Exception localException)
            {
              localObject = new HashMap();
              ((Map)localObject).put("log", "v321_MainPage.java_line1769_error_when_replay_after_phone_call");
              MixerBoxUtils.a("log:error", (Map)localObject);
            }
            continue;
            label345:
            if (MainPage.this.aJ != null) {
              localObject = MainPage.this.aJ;
            }
            if (MainPage.this.aJ != null) {
              MainPage.this.aJ.f("javascript:playVideo()");
            }
            MainPage.a(MainPage.this, true, MainPage.this.H);
            if (MainPage.this.aJ != null) {
              MainPage.this.aJ.b(false);
            }
          }
        }
      }
    }
  };
  public boolean aN = true;
  int aO = 0;
  boolean aP = false;
  String aQ = "";
  Runnable aR = new Runnable()
  {
    public final void run()
    {
      MixerBoxUtils.a("action:mb3_first_launch", null);
      Iterator localIterator = MainPage.this.getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (ApplicationInfo)localIterator.next();
        if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox"))
        {
          localObject = new HashMap();
          ((Map)localObject).put("version", "com.mixerbox.mixerbox");
          MixerBoxUtils.a("status:installed_old_mb", (Map)localObject);
        }
        else if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox2"))
        {
          localObject = new HashMap();
          ((Map)localObject).put("version", "com.mixerbox.mixerbox2");
          MixerBoxUtils.a("status:installed_old_mb", (Map)localObject);
        }
        else if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox2b"))
        {
          localObject = new HashMap();
          ((Map)localObject).put("version", "com.mixerbox.mixerbox2b");
          MixerBoxUtils.a("status:installed_old_mb", (Map)localObject);
        }
        else if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox3"))
        {
          localObject = new HashMap();
          ((Map)localObject).put("version", "com.mixerbox.mixerbox3");
          MixerBoxUtils.a("status:installed_old_mb", (Map)localObject);
        }
        else if (((ApplicationInfo)localObject).packageName.equals("com.mixerbox.mixerbox3b"))
        {
          localObject = new HashMap();
          ((Map)localObject).put("version", "com.mixerbox.mixerbox3b");
          MixerBoxUtils.a("status:installed_old_mb", (Map)localObject);
        }
      }
      if (!MainPage.this.aB.equals("")) {
        MixerBoxUtils.a("First Launch " + MainPage.this.aB, null);
      }
    }
  };
  Runnable aS = new Runnable()
  {
    public final void run()
    {
      if (!MainPage.this.aB.equals(""))
      {
        String str = asa.aj(MainPage.this);
        if (!MainPage.this.aB.equals(str))
        {
          MixerBoxUtils.a("Group Launch " + MainPage.this.aB, null);
          asa.v(MainPage.this, MainPage.this.aB);
        }
      }
    }
  };
  private int aU;
  private int aV;
  private boolean aW;
  private boolean aX = false;
  private apm aY;
  private MoPubInterstitial aZ;
  ConnectionChangeReceiver aa;
  SystemDialogReceiver ab;
  public boolean ac;
  public boolean ad;
  public boolean ae;
  int af;
  EarphoneReceiver ag;
  BroadcastReceiver ah;
  MyReceiver ai;
  AudioManager aj;
  int ak = 0;
  public Menu al;
  public boolean am = false;
  public arz an = null;
  public int ao;
  public boolean ap;
  public boolean ar;
  boolean at = false;
  public boolean av;
  public boolean aw;
  public boolean ax;
  public boolean ay;
  int az;
  public int b;
  private d ba;
  private BroadcastReceiver bb;
  private BroadcastReceiver bc;
  private Boolean bd;
  private boolean be;
  private int bf;
  private RelativeLayout bg;
  private int bh;
  private int bi;
  private boolean bj = true;
  private asn bk = null;
  private apg bl = null;
  private ArrayList<String> bm = null;
  private MyAutoCompleteTextView bn = null;
  private Thread bo = null;
  private UiLifecycleHelper bp;
  private Session.StatusCallback bq = new Session.StatusCallback()
  {
    public final void call(Session paramAnonymousSession, SessionState paramAnonymousSessionState, Exception paramAnonymousException)
    {
      MainPage.a(paramAnonymousSession, paramAnonymousSessionState);
    }
  };
  private final b br = new b(this);
  private Runnable bs = new Runnable()
  {
    public final void run()
    {
      try
      {
        arz localArz = MainPage.this.an;
        Object localObject = MainPage.this.I.d;
        if (localArz.a.isOpen())
        {
          localObject = localArz.a.rawQuery("SELECT * FROM tableSong WHERE PID=\"PLAYLISTHISTORYID\" AND YT_ID=\"" + (String)localObject + "\"", null);
          if (((Cursor)localObject).getCount() > 0)
          {
            ((Cursor)localObject).moveToFirst();
            localArz.a.delete("tableSong", "_ID=" + ((Cursor)localObject).getString(0), null);
          }
          ((Cursor)localObject).close();
        }
        localArz = MainPage.this.an;
        localObject = MainPage.this.I.b;
        String str1 = MainPage.this.I.c;
        String str2 = MainPage.this.I.d;
        apn localApn = MainPage.this.I;
        localArz.a("PLAYLISTHISTORYID", (String)localObject, str1, str2, MainPage.this.I.f, MainPage.this.I.g);
        if (MainPage.this.an.b("PLAYLISTHISTORYID") > 100)
        {
          localArz = MainPage.this.an;
          if (localArz.a.isOpen())
          {
            localObject = localArz.a.rawQuery("SELECT * FROM tableSong WHERE PID=\"PLAYLISTHISTORYID\"", null);
            ((Cursor)localObject).moveToFirst();
            localArz.a.delete("tableSong", "_ID=" + ((Cursor)localObject).getString(0), null);
            ((Cursor)localObject).close();
          }
        }
        return;
      }
      catch (Exception localException) {}
    }
  };
  public TextView c;
  public e d;
  int g;
  int h;
  int[] i;
  public ImageView k;
  FragmentManager l;
  public Stack<Fragment> m;
  RelativeLayout n;
  RelativeLayout o;
  RelativeLayout p;
  RelativeLayout q;
  RelativeLayout r;
  LinearLayout s;
  public ari t;
  arh u;
  ard v;
  public arc w;
  public arf x;
  public arj y;
  boolean z = true;
  
  public MainPage() {}
  
  private void K()
  {
    Object localObject2 = this.U.a(aru.f());
    final Dialog localDialog;
    Object localObject1;
    Object localObject4;
    TextView localTextView1;
    if (localObject2 != null)
    {
      localDialog = new Dialog(this);
      localDialog.setContentView(2130903098);
      localDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
      Object localObject3 = (RelativeLayout)localDialog.findViewById(2131624145);
      localObject1 = (RelativeLayout)localDialog.findViewById(2131624146);
      localObject4 = (ImageView)localDialog.findViewById(2131624063);
      ImageView localImageView2 = (ImageView)localDialog.findViewById(2131624147);
      TextView localTextView2 = (TextView)localDialog.findViewById(2131624149);
      TextView localTextView3 = (TextView)localDialog.findViewById(2131624150);
      localTextView1 = (TextView)localDialog.findViewById(2131624151);
      ImageView localImageView1 = (ImageView)localDialog.findViewById(2131624152);
      localTextView2.setText(((StaticNativeAd)localObject2).getTitle());
      localTextView3.setText(((StaticNativeAd)localObject2).getText());
      localTextView1.setText(((StaticNativeAd)localObject2).getCallToAction());
      ((ImageView)localObject4).setBackgroundResource(17170445);
      MixerBoxUtils.a(this, ((StaticNativeAd)localObject2).getMainImageUrl(), localImageView2, 8);
      MixerBoxUtils.a(this, ((StaticNativeAd)localObject2).getIconImageUrl(), (ImageView)localObject4, 8);
      localObject4 = (RelativeLayout)localDialog.findViewById(2131624154);
      ((RelativeLayout)localObject4).setVisibility(0);
      localTextView1.setVisibility(0);
      localImageView1.setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
        }
      });
      ((RelativeLayout)localObject3).setOnClickListener(new View.OnClickListener()
      {
        public final void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
        }
      });
      if (((StaticNativeAd)localObject2).getClickDestinationUrl() != null)
      {
        ((RelativeLayout)localObject4).setVisibility(8);
        localObject3 = new View.OnClickListener()
        {
          public final void onClick(View paramAnonymousView)
          {
            this.a.handleClick(null);
          }
        };
        ((RelativeLayout)localObject1).setOnClickListener((View.OnClickListener)localObject3);
        localTextView1.setVisibility(0);
        localTextView1.setOnClickListener((View.OnClickListener)localObject3);
        ((StaticNativeAd)localObject2).recordImpression(null);
      }
    }
    for (;;)
    {
      if (!isFinishing()) {
        localObject1 = new JSONObject();
      }
      try
      {
        ((JSONObject)localObject1).put("iaaURL", "NativeAd");
        MixerBoxUtils.a(this, "PopFullPage", (JSONObject)localObject1);
        localDialog.show();
        return;
        if (((StaticNativeAd)localObject2).getExtra("NativeAd") == null) {
          continue;
        }
        localObject2 = (NativeAd)((StaticNativeAd)localObject2).getExtra("NativeAd");
        localTextView1.setVisibility(0);
        ((NativeAd)localObject2).registerViewForInteraction((View)localObject1);
        ((RelativeLayout)localObject4).removeAllViews();
        localObject1 = new AdChoicesView(this, (NativeAd)localObject2, true);
        ((RelativeLayout)localObject4).setVisibility(0);
        ((RelativeLayout)localObject4).addView((View)localObject1);
        continue;
        MixerBoxUtils.a(this, getResources().getString(2131231011), 0, new boolean[0]);
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
      }
    }
  }
  
  private void L()
  {
    if (asa.D(this))
    {
      if (W())
      {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        Object localObject = LayoutInflater.from(this).inflate(2130903096, null);
        localBuilder.setView((View)localObject);
        localObject = (CheckBox)((View)localObject).findViewById(2131624144);
        localBuilder.setTitle(getResources().getString(2131231054));
        localBuilder.setPositiveButton(getResources().getString(2131230851), new DialogInterface.OnClickListener()
        {
          public final void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            if (this.a.isChecked()) {
              asa.e(MainPage.this, false);
            }
            paramAnonymousDialogInterface.dismiss();
          }
        });
        localBuilder.create().show();
      }
    }
    else {
      return;
    }
    asa.e(this, false);
  }
  
  /* Error */
  private void M()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   5: ifnull +603 -> 608
    //   8: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   11: ldc_w 720
    //   14: iconst_0
    //   15: invokevirtual 726	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   18: ifeq +590 -> 608
    //   21: ldc_w 728
    //   24: aconst_null
    //   25: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   28: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   31: ldc_w 733
    //   34: ldc_w 374
    //   37: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   40: astore_2
    //   41: aload_2
    //   42: ifnull +22 -> 64
    //   45: aload_2
    //   46: invokevirtual 740	java/lang/String:length	()I
    //   49: ifle +15 -> 64
    //   52: aload_0
    //   53: new 742	org/json/JSONArray
    //   56: dup
    //   57: aload_2
    //   58: invokespecial 745	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   61: invokestatic 748	mbinc12/mb32b/utils/MixerBoxUtils:b	(Landroid/content/Context;Lorg/json/JSONArray;)V
    //   64: aload_0
    //   65: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   68: ldc_w 720
    //   71: invokevirtual 751	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   74: invokespecial 753	mbinc12/mb32b/MainPage:g	(I)V
    //   77: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   80: ldc_w 755
    //   83: ldc_w 374
    //   86: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   89: invokevirtual 740	java/lang/String:length	()I
    //   92: ifle +341 -> 433
    //   95: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   98: ldc_w 757
    //   101: ldc_w 374
    //   104: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   107: invokevirtual 740	java/lang/String:length	()I
    //   110: ifne +323 -> 433
    //   113: new 759	apm
    //   116: dup
    //   117: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   120: ldc_w 755
    //   123: ldc_w 374
    //   126: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   129: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   132: ldc_w 761
    //   135: ldc_w 374
    //   138: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   141: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   144: ldc_w 763
    //   147: ldc_w 374
    //   150: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   153: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   156: ldc_w 765
    //   159: ldc_w 374
    //   162: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   165: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   168: ldc_w 767
    //   171: ldc_w 374
    //   174: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   177: ldc_w 374
    //   180: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   183: ldc_w 769
    //   186: ldc_w 374
    //   189: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   192: iconst_0
    //   193: invokespecial 772	apm:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   196: astore_2
    //   197: aload_0
    //   198: getfield 774	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   201: invokevirtual 779	java/util/ArrayList:clear	()V
    //   204: aload_0
    //   205: aload_2
    //   206: getfield 780	apm:a	Ljava/lang/String;
    //   209: iconst_0
    //   210: invokevirtual 783	mbinc12/mb32b/MainPage:b	(Ljava/lang/String;Z)Lmbinc12/mb32b/fragments/SongFragment;
    //   213: astore_3
    //   214: aload_3
    //   215: aload_2
    //   216: putfield 787	mbinc12/mb32b/fragments/SongFragment:F	Lapm;
    //   219: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   222: ldc_w 789
    //   225: iconst_0
    //   226: invokevirtual 726	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   229: iconst_1
    //   230: if_icmpeq +189 -> 419
    //   233: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   236: ldc_w 789
    //   239: iconst_0
    //   240: invokevirtual 726	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   243: iconst_m1
    //   244: if_icmpne +136 -> 380
    //   247: aload_0
    //   248: aload_2
    //   249: getfield 780	apm:a	Ljava/lang/String;
    //   252: iconst_m1
    //   253: aload_3
    //   254: iconst_0
    //   255: invokestatic 792	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;ILmbinc12/mb32b/fragments/SongFragment;I)V
    //   258: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   261: ldc_w 794
    //   264: iconst_0
    //   265: invokevirtual 798	android/os/Bundle:getBoolean	(Ljava/lang/String;Z)Z
    //   268: ifeq +8 -> 276
    //   271: aload_0
    //   272: aload_2
    //   273: invokevirtual 801	mbinc12/mb32b/MainPage:c	(Lapm;)V
    //   276: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   279: ldc_w 803
    //   282: ldc_w 374
    //   285: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   288: invokevirtual 740	java/lang/String:length	()I
    //   291: ifle +76 -> 367
    //   294: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   297: ldc_w 805
    //   300: ldc_w 374
    //   303: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   306: invokevirtual 740	java/lang/String:length	()I
    //   309: istore_1
    //   310: iload_1
    //   311: ifle +56 -> 367
    //   314: aload_0
    //   315: invokestatic 808	asa:l	(Landroid/content/Context;)I
    //   318: istore_1
    //   319: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   322: ldc_w 805
    //   325: ldc_w 374
    //   328: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   331: astore_2
    //   332: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   335: ldc_w 803
    //   338: ldc_w 374
    //   341: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   344: astore_3
    //   345: getstatic 812	mbinc12/mb32b/MyApplication:b	Z
    //   348: ifeq +217 -> 565
    //   351: aload_0
    //   352: aload_2
    //   353: invokestatic 817	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   356: new 14	mbinc12/mb32b/MainPage$11
    //   359: dup
    //   360: aload_0
    //   361: invokespecial 818	mbinc12/mb32b/MainPage$11:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   364: invokestatic 823	tl:a	(Landroid/app/Activity;ILtl$a;)V
    //   367: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   370: ldc_w 720
    //   373: iconst_0
    //   374: invokevirtual 827	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   377: aload_0
    //   378: monitorexit
    //   379: return
    //   380: aload_0
    //   381: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   384: ifnull +35 -> 419
    //   387: aload_0
    //   388: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   391: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   394: invokevirtual 836	mbinc12/mb32b/services/WindowPlayerService:a	()Z
    //   397: ifeq +22 -> 419
    //   400: aload_0
    //   401: aload_2
    //   402: getfield 780	apm:a	Ljava/lang/String;
    //   405: iconst_m1
    //   406: aload_3
    //   407: iconst_0
    //   408: invokestatic 792	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;ILmbinc12/mb32b/fragments/SongFragment;I)V
    //   411: goto -153 -> 258
    //   414: astore_2
    //   415: aload_0
    //   416: monitorexit
    //   417: aload_2
    //   418: athrow
    //   419: aload_0
    //   420: aload_2
    //   421: getfield 780	apm:a	Ljava/lang/String;
    //   424: iconst_0
    //   425: aload_3
    //   426: iconst_1
    //   427: invokestatic 792	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;ILmbinc12/mb32b/fragments/SongFragment;I)V
    //   430: goto -172 -> 258
    //   433: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   436: ldc_w 755
    //   439: ldc_w 374
    //   442: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   445: invokevirtual 740	java/lang/String:length	()I
    //   448: ifle +62 -> 510
    //   451: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   454: ldc_w 757
    //   457: ldc_w 374
    //   460: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   463: invokevirtual 740	java/lang/String:length	()I
    //   466: ifle +44 -> 510
    //   469: aload_0
    //   470: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   473: ldc_w 755
    //   476: ldc_w 374
    //   479: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   482: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   485: ldc_w 757
    //   488: ldc_w 374
    //   491: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   494: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   497: ldc_w 794
    //   500: iconst_0
    //   501: invokevirtual 798	android/os/Bundle:getBoolean	(Ljava/lang/String;Z)Z
    //   504: invokespecial 839	mbinc12/mb32b/MainPage:a	(Ljava/lang/String;Ljava/lang/String;Z)V
    //   507: goto -231 -> 276
    //   510: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   513: ldc_w 755
    //   516: ldc_w 374
    //   519: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   522: invokevirtual 740	java/lang/String:length	()I
    //   525: ifne -249 -> 276
    //   528: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   531: ldc_w 757
    //   534: ldc_w 374
    //   537: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   540: invokevirtual 740	java/lang/String:length	()I
    //   543: ifle -267 -> 276
    //   546: aload_0
    //   547: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   550: ldc_w 757
    //   553: ldc_w 374
    //   556: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   559: invokespecial 841	mbinc12/mb32b/MainPage:p	(Ljava/lang/String;)V
    //   562: goto -286 -> 276
    //   565: new 609	org/json/JSONObject
    //   568: dup
    //   569: invokespecial 610	org/json/JSONObject:<init>	()V
    //   572: astore 4
    //   574: aload 4
    //   576: ldc_w 612
    //   579: aload_3
    //   580: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   583: pop
    //   584: aload_0
    //   585: ldc_w 620
    //   588: aload 4
    //   590: invokestatic 623	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   593: aload_0
    //   594: aload_3
    //   595: iconst_1
    //   596: aload_2
    //   597: iload_1
    //   598: invokestatic 844	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/lang/String;I)V
    //   601: goto -234 -> 367
    //   604: astore_2
    //   605: goto -238 -> 367
    //   608: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   611: ifnull -234 -> 377
    //   614: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   617: ldc_w 846
    //   620: ldc_w 374
    //   623: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   626: invokevirtual 740	java/lang/String:length	()I
    //   629: ifle -252 -> 377
    //   632: aload_0
    //   633: invokevirtual 848	mbinc12/mb32b/MainPage:w	()V
    //   636: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   639: ldc_w 846
    //   642: ldc_w 374
    //   645: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   648: astore_3
    //   649: aload_3
    //   650: astore_2
    //   651: aload_3
    //   652: ldc_w 850
    //   655: invokevirtual 854	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   658: ifeq +18 -> 676
    //   661: aload_3
    //   662: iconst_0
    //   663: aload_3
    //   664: invokevirtual 740	java/lang/String:length	()I
    //   667: iconst_1
    //   668: isub
    //   669: invokevirtual 858	java/lang/String:subSequence	(II)Ljava/lang/CharSequence;
    //   672: checkcast 433	java/lang/String
    //   675: astore_2
    //   676: aload_2
    //   677: ldc_w 850
    //   680: invokevirtual 862	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   683: astore_3
    //   684: aload_2
    //   685: ldc_w 864
    //   688: invokevirtual 868	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   691: ifeq +62 -> 753
    //   694: aload_0
    //   695: invokestatic 872	asc:a	(Landroid/content/Context;)Z
    //   698: ifeq +34 -> 732
    //   701: aload_3
    //   702: iconst_4
    //   703: aaload
    //   704: astore_2
    //   705: aload_2
    //   706: invokevirtual 740	java/lang/String:length	()I
    //   709: ifle +8 -> 717
    //   712: aload_0
    //   713: aload_2
    //   714: invokestatic 875	asc:b	(Landroid/content/Context;Ljava/lang/String;)V
    //   717: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   720: ldc_w 846
    //   723: ldc_w 374
    //   726: invokevirtual 879	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   729: goto -352 -> 377
    //   732: aload_0
    //   733: aload_0
    //   734: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   737: ldc_w 880
    //   740: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   743: iconst_0
    //   744: iconst_0
    //   745: newarray boolean
    //   747: invokestatic 660	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;I[Z)V
    //   750: goto -33 -> 717
    //   753: aload_3
    //   754: arraylength
    //   755: iconst_5
    //   756: if_icmpne +41 -> 797
    //   759: aload_3
    //   760: iconst_3
    //   761: aaload
    //   762: ldc_w 882
    //   765: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   768: ifeq +14 -> 782
    //   771: aload_0
    //   772: aload_3
    //   773: iconst_4
    //   774: aaload
    //   775: iconst_1
    //   776: invokevirtual 889	mbinc12/mb32b/MainPage:a	(Ljava/lang/String;Z)V
    //   779: goto -62 -> 717
    //   782: aload_3
    //   783: iconst_3
    //   784: aaload
    //   785: ldc_w 891
    //   788: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   791: ifeq -74 -> 717
    //   794: goto -77 -> 717
    //   797: aload_3
    //   798: arraylength
    //   799: bipush 6
    //   801: if_icmpne -84 -> 717
    //   804: aload_3
    //   805: iconst_3
    //   806: aaload
    //   807: ldc_w 893
    //   810: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   813: ifeq -96 -> 717
    //   816: aload_3
    //   817: iconst_4
    //   818: aaload
    //   819: ldc_w 895
    //   822: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   825: ifeq +13 -> 838
    //   828: aload_0
    //   829: aload_3
    //   830: iconst_5
    //   831: aaload
    //   832: invokespecial 841	mbinc12/mb32b/MainPage:p	(Ljava/lang/String;)V
    //   835: goto -118 -> 717
    //   838: aload_0
    //   839: aload_3
    //   840: iconst_4
    //   841: aaload
    //   842: aload_3
    //   843: iconst_5
    //   844: aaload
    //   845: iconst_0
    //   846: invokespecial 839	mbinc12/mb32b/MainPage:a	(Ljava/lang/String;Ljava/lang/String;Z)V
    //   849: goto -132 -> 717
    //   852: astore 5
    //   854: goto -270 -> 584
    //   857: astore_2
    //   858: goto -794 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	861	0	this	MainPage
    //   309	289	1	i1	int
    //   40	362	2	localObject1	Object
    //   414	183	2	str	String
    //   604	1	2	localException1	Exception
    //   650	64	2	localObject2	Object
    //   857	1	2	localException2	Exception
    //   213	630	3	localObject3	Object
    //   572	17	4	localJSONObject	JSONObject
    //   852	1	5	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   2	28	414	finally
    //   28	41	414	finally
    //   45	64	414	finally
    //   64	258	414	finally
    //   258	276	414	finally
    //   276	310	414	finally
    //   314	367	414	finally
    //   367	377	414	finally
    //   380	411	414	finally
    //   419	430	414	finally
    //   433	507	414	finally
    //   510	562	414	finally
    //   565	574	414	finally
    //   574	584	414	finally
    //   584	601	414	finally
    //   608	649	414	finally
    //   651	676	414	finally
    //   676	701	414	finally
    //   705	717	414	finally
    //   717	729	414	finally
    //   732	750	414	finally
    //   753	779	414	finally
    //   782	794	414	finally
    //   797	835	414	finally
    //   838	849	414	finally
    //   314	367	604	java/lang/Exception
    //   565	574	604	java/lang/Exception
    //   574	584	604	java/lang/Exception
    //   584	601	604	java/lang/Exception
    //   574	584	852	org/json/JSONException
    //   28	41	857	java/lang/Exception
    //   45	64	857	java/lang/Exception
  }
  
  private void N()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(2131230878).setTitle(2131230877).setPositiveButton(2131230851, null);
    localBuilder.create().show();
  }
  
  private void O()
  {
    if (this.v == null) {
      return;
    }
    JSONObject localJSONObject = k();
    arx.a(this, arx.e(this), localJSONObject, new apj(this)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        MainPage.this.v.a();
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if (paramAnonymousArrayOfByte == null) {
          return;
        }
        paramAnonymousArrayOfXh = new ArrayList();
        try
        {
          paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("searchPage").getJSONArray("items");
          paramAnonymousInt = 0;
          while (paramAnonymousInt < paramAnonymousArrayOfByte.length())
          {
            MixerBoxUtils.a(paramAnonymousArrayOfXh, paramAnonymousArrayOfByte.getJSONObject(paramAnonymousInt), MainPage.this, paramAnonymousInt, paramAnonymousArrayOfByte.length() - paramAnonymousInt - 1);
            paramAnonymousInt += 1;
          }
          return;
        }
        catch (JSONException paramAnonymousArrayOfByte)
        {
          paramAnonymousArrayOfByte.printStackTrace();
          MainPage.this.B = paramAnonymousArrayOfXh;
          MainPage.this.v.a();
        }
      }
    });
  }
  
  private void P()
  {
    if (!arx.a(this))
    {
      r();
      return;
    }
    a(false, 0);
  }
  
  private void Q()
  {
    int i2 = 0;
    if ((this.F == null) || (this.F.size() == 0)) {}
    for (;;)
    {
      return;
      this.i = new int[this.F.size()];
      int i1 = 0;
      while (i1 < this.F.size())
      {
        this.i[i1] = i1;
        i1 += 1;
      }
      Random localRandom = new Random();
      i1 = i2;
      while (i1 < this.F.size())
      {
        i2 = localRandom.nextInt(i1 + 1);
        int i3 = this.i[i2];
        this.i[i2] = this.i[i1];
        this.i[i1] = i3;
        i1 += 1;
      }
    }
  }
  
  private void R()
  {
    TextView localTextView = (TextView)this.q.findViewById(2131624260);
    int i1 = asb.e(this);
    int i2 = asb.c(this);
    MixerBoxUtils.a(this, asb.o(this), (ImageView)this.q.findViewById(2131624259), 8);
    this.q.setBackgroundColor(i2);
    localTextView.setTextColor(i1);
  }
  
  private void S()
  {
    int i1 = asb.b(this);
    this.n.setBackgroundColor(i1);
    this.r.setBackgroundColor(i1);
    this.o.setBackgroundColor(i1);
    this.p.setBackgroundColor(i1);
    this.q.setBackgroundColor(i1);
    i1 = asb.d(this);
    TextView localTextView = (TextView)this.n.findViewById(2131624246);
    ImageView localImageView = (ImageView)this.n.findViewById(2131624245);
    localTextView.setTextColor(i1);
    MixerBoxUtils.a(this, asb.f(this), localImageView, 8);
    localTextView = (TextView)this.r.findViewById(2131624250);
    localImageView = (ImageView)this.r.findViewById(2131624249);
    localTextView.setTextColor(i1);
    MixerBoxUtils.a(this, asb.g(this), localImageView, 8);
    localTextView = (TextView)this.o.findViewById(2131624253);
    localImageView = (ImageView)this.o.findViewById(2131624252);
    localTextView.setTextColor(i1);
    MixerBoxUtils.a(this, asb.h(this), localImageView, 8);
    localTextView = (TextView)this.p.findViewById(2131624257);
    localImageView = (ImageView)this.p.findViewById(2131624256);
    localTextView.setTextColor(i1);
    MixerBoxUtils.a(this, asb.i(this), localImageView, 8);
    localTextView = (TextView)this.q.findViewById(2131624260);
    localImageView = (ImageView)this.q.findViewById(2131624259);
    localTextView.setTextColor(i1);
    MixerBoxUtils.a(this, asb.j(this), localImageView, 8);
    getSupportActionBar().a(asb.a(this));
  }
  
  private void T()
  {
    if ((this.aJ != null) && (aq) && (this.ap) && (arx.a(this)))
    {
      if (this.I == null) {
        break label66;
      }
      MixerBoxUtils.f(this, "AndroidShowMultitaskingWhenFirstLeaveApp");
      this.aJ.g();
      this.aJ.a(false, 0, this.ae);
      asa.aK(this);
    }
    label66:
    do
    {
      return;
      MixerBoxUtils.f(this, "PlayZeroSongAtFirstSession");
    } while ((this.N == null) || (this.aY == null) || (this.N.size() <= 0));
    this.aJ.g();
    this.F = this.N;
    this.I = ((apn)this.N.get(0));
    this.J = this.aY;
    this.G = 0;
    a(false, 0, true);
    MixerBoxUtils.f(this, "AndroidShowMultitaskingWhenFirstLeaveApp");
    asa.aK(this);
  }
  
  /* Error */
  private void U()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 1041	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   4: ifnull +112 -> 116
    //   7: aload_0
    //   8: getfield 1041	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   11: invokeinterface 1044 1 0
    //   16: aload_0
    //   17: new 776	java/util/ArrayList
    //   20: dup
    //   21: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   24: putfield 1047	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
    //   27: aload_0
    //   28: new 776	java/util/ArrayList
    //   31: dup
    //   32: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   35: putfield 1049	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
    //   38: new 609	org/json/JSONObject
    //   41: dup
    //   42: aload_0
    //   43: invokestatic 1051	asa:ax	(Landroid/content/Context;)Ljava/lang/String;
    //   46: invokespecial 1052	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   49: ldc_w 1054
    //   52: invokevirtual 1058	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   55: astore 4
    //   57: iconst_0
    //   58: istore_1
    //   59: aload 4
    //   61: invokevirtual 1059	org/json/JSONArray:length	()I
    //   64: istore_2
    //   65: iload_1
    //   66: iload_2
    //   67: if_icmpge +156 -> 223
    //   70: aload 4
    //   72: iload_1
    //   73: invokevirtual 1063	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   76: astore 5
    //   78: aload 5
    //   80: ldc_w 1065
    //   83: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   86: astore_3
    //   87: aload_3
    //   88: ldc_w 1070
    //   91: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   94: ifeq +36 -> 130
    //   97: aload_0
    //   98: aload 5
    //   100: ldc_w 1072
    //   103: invokevirtual 1058	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   106: invokespecial 1075	mbinc12/mb32b/MainPage:b	(Lorg/json/JSONArray;)V
    //   109: iload_1
    //   110: iconst_1
    //   111: iadd
    //   112: istore_1
    //   113: goto -54 -> 59
    //   116: aload_0
    //   117: new 776	java/util/ArrayList
    //   120: dup
    //   121: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   124: putfield 1041	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   127: goto -111 -> 16
    //   130: aload_3
    //   131: ldc_w 1077
    //   134: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifeq +39 -> 176
    //   140: new 742	org/json/JSONArray
    //   143: dup
    //   144: invokespecial 1078	org/json/JSONArray:<init>	()V
    //   147: astore_3
    //   148: aload 5
    //   150: ldc_w 1080
    //   153: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   156: ifne +12 -> 168
    //   159: aload 5
    //   161: ldc_w 1080
    //   164: invokevirtual 1058	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   167: astore_3
    //   168: aload_0
    //   169: aload_3
    //   170: invokespecial 1085	mbinc12/mb32b/MainPage:c	(Lorg/json/JSONArray;)V
    //   173: goto -64 -> 109
    //   176: aload_3
    //   177: ldc_w 1087
    //   180: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   183: ifeq -74 -> 109
    //   186: new 742	org/json/JSONArray
    //   189: dup
    //   190: invokespecial 1078	org/json/JSONArray:<init>	()V
    //   193: astore_3
    //   194: aload 5
    //   196: ldc_w 1080
    //   199: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   202: ifne +12 -> 214
    //   205: aload 5
    //   207: ldc_w 1080
    //   210: invokevirtual 1058	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   213: astore_3
    //   214: aload_0
    //   215: aload_3
    //   216: invokespecial 1089	mbinc12/mb32b/MainPage:d	(Lorg/json/JSONArray;)V
    //   219: goto -110 -> 109
    //   222: astore_3
    //   223: aload_0
    //   224: invokevirtual 1091	mbinc12/mb32b/MainPage:t	()V
    //   227: return
    //   228: astore_3
    //   229: goto -120 -> 109
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	232	0	this	MainPage
    //   58	55	1	i1	int
    //   64	4	2	i2	int
    //   86	130	3	localObject	Object
    //   222	1	3	localException1	Exception
    //   228	1	3	localException2	Exception
    //   55	16	4	localJSONArray	JSONArray
    //   76	130	5	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   38	57	222	java/lang/Exception
    //   59	65	222	java/lang/Exception
    //   70	109	228	java/lang/Exception
    //   130	148	228	java/lang/Exception
    //   148	168	228	java/lang/Exception
    //   168	173	228	java/lang/Exception
    //   176	194	228	java/lang/Exception
    //   194	214	228	java/lang/Exception
    //   214	219	228	java/lang/Exception
  }
  
  private void V()
  {
    int i2 = 0;
    for (;;)
    {
      try
      {
        MoPubView localMoPubView = this.S;
        if (localMoPubView == null) {
          return;
        }
        i1 = i2;
        if (asa.w(this))
        {
          i1 = i2;
          if (!asc.f(this))
          {
            if ((this.be) || (this.aJ == null)) {
              break label176;
            }
            i1 = i2;
            if (!this.aJ.b.a()) {
              break label176;
            }
          }
        }
        if (i1 != 0)
        {
          if ((this.bd == null) || (!this.bd.booleanValue()))
          {
            this.S.setBannerAdListener(new MoPubView.BannerAdListener()
            {
              public final void onBannerClicked(MoPubView paramAnonymousMoPubView) {}
              
              public final void onBannerCollapsed(MoPubView paramAnonymousMoPubView) {}
              
              public final void onBannerExpanded(MoPubView paramAnonymousMoPubView) {}
              
              public final void onBannerFailed(MoPubView paramAnonymousMoPubView, MoPubErrorCode paramAnonymousMoPubErrorCode)
              {
                MainPage.z(MainPage.this);
              }
              
              public final void onBannerLoaded(MoPubView paramAnonymousMoPubView)
              {
                MainPage.y(MainPage.this);
              }
            });
            this.S.forceRefresh();
            this.S.setAutorefreshEnabled(true);
            c(true);
          }
          this.bd = Boolean.valueOf(true);
          continue;
        }
        if (this.bd == null) {
          break label155;
        }
      }
      finally {}
      if (this.bd.booleanValue())
      {
        label155:
        d(false);
        c(false);
      }
      this.bd = Boolean.valueOf(false);
      continue;
      label176:
      int i1 = 1;
    }
  }
  
  private boolean W()
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.equals("com.mixerbox.mixerbox")) {
        return true;
      }
      if (localApplicationInfo.packageName.equals("com.mixerbox.mixerbox2")) {
        return true;
      }
      if (localApplicationInfo.packageName.equals("com.mixerbox.mixerbox2b")) {
        return true;
      }
      if (localApplicationInfo.packageName.equals("com.mixerbox.mixerbox3")) {
        return true;
      }
      if (localApplicationInfo.packageName.equals("com.mixerbox.mixerbox3b")) {
        return true;
      }
    }
    return false;
  }
  
  private void X()
  {
    asa.a(this);
    asa.g(this, 2);
  }
  
  public static Animation a(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, paramFloat1, 1, 0.0F, 1, paramFloat2, 1, paramFloat3);
    localTranslateAnimation.setDuration(paramInt);
    return localTranslateAnimation;
  }
  
  private static String a(Bundle paramBundle, String paramString1, String paramString2)
  {
    try
    {
      if (Build.VERSION.SDK_INT < 12)
      {
        paramBundle = paramBundle.getString(paramString1);
        if (paramBundle == null) {
          return paramString2;
        }
      }
      else
      {
        paramBundle = paramBundle.getString(paramString1, paramString2);
        return paramBundle;
      }
    }
    catch (Exception paramBundle)
    {
      return paramString2;
    }
    return paramBundle;
  }
  
  /* Error */
  public static ArrayList<apm> a(JSONArray paramJSONArray)
  {
    // Byte code:
    //   0: new 776	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   7: astore 10
    //   9: iconst_0
    //   10: istore_1
    //   11: aload_0
    //   12: invokevirtual 1059	org/json/JSONArray:length	()I
    //   15: istore_2
    //   16: iload_1
    //   17: iload_2
    //   18: if_icmpge +223 -> 241
    //   21: aload_0
    //   22: iload_1
    //   23: invokevirtual 1063	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   26: astore 11
    //   28: ldc_w 374
    //   31: astore 4
    //   33: ldc_w 374
    //   36: astore 5
    //   38: ldc_w 374
    //   41: astore 6
    //   43: ldc_w 374
    //   46: astore 7
    //   48: ldc_w 374
    //   51: astore 8
    //   53: ldc_w 374
    //   56: astore 9
    //   58: aload 11
    //   60: ldc_w 1196
    //   63: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   66: ifne +13 -> 79
    //   69: aload 11
    //   71: ldc_w 1196
    //   74: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   77: astore 4
    //   79: aload 11
    //   81: ldc_w 1198
    //   84: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   87: ifne +13 -> 100
    //   90: aload 11
    //   92: ldc_w 1198
    //   95: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   98: astore 5
    //   100: aload 11
    //   102: ldc_w 1200
    //   105: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   108: ifne +13 -> 121
    //   111: aload 11
    //   113: ldc_w 1200
    //   116: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   119: astore 6
    //   121: aload 11
    //   123: ldc_w 1202
    //   126: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   129: ifne +13 -> 142
    //   132: aload 11
    //   134: ldc_w 1202
    //   137: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   140: astore 7
    //   142: aload 11
    //   144: ldc_w 1203
    //   147: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   150: ifne +13 -> 163
    //   153: aload 11
    //   155: ldc_w 1203
    //   158: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   161: astore 8
    //   163: aload 11
    //   165: ldc_w 1205
    //   168: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   171: ifne +13 -> 184
    //   174: aload 11
    //   176: ldc_w 1205
    //   179: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   182: astore 9
    //   184: aload 11
    //   186: ldc_w 1207
    //   189: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   192: ifne +57 -> 249
    //   195: aload 11
    //   197: ldc_w 1207
    //   200: invokevirtual 1209	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   203: istore_3
    //   204: aload 10
    //   206: new 759	apm
    //   209: dup
    //   210: aload 4
    //   212: aload 5
    //   214: aload 6
    //   216: aload 7
    //   218: aload 8
    //   220: ldc_w 374
    //   223: aload 9
    //   225: iload_3
    //   226: invokespecial 772	apm:<init>	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   229: invokevirtual 1212	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   232: pop
    //   233: iload_1
    //   234: iconst_1
    //   235: iadd
    //   236: istore_1
    //   237: goto -226 -> 11
    //   240: astore_0
    //   241: aload 10
    //   243: areturn
    //   244: astore 4
    //   246: goto -13 -> 233
    //   249: iconst_0
    //   250: istore_3
    //   251: goto -47 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	254	0	paramJSONArray	JSONArray
    //   10	227	1	i1	int
    //   15	4	2	i2	int
    //   203	48	3	bool	boolean
    //   31	180	4	str1	String
    //   244	1	4	localException	Exception
    //   36	177	5	str2	String
    //   41	174	6	str3	String
    //   46	171	7	str4	String
    //   51	168	8	str5	String
    //   56	168	9	str6	String
    //   7	235	10	localArrayList	ArrayList
    //   26	170	11	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   11	16	240	java/lang/Exception
    //   21	28	244	java/lang/Exception
    //   58	79	244	java/lang/Exception
    //   79	100	244	java/lang/Exception
    //   100	121	244	java/lang/Exception
    //   121	142	244	java/lang/Exception
    //   142	163	244	java/lang/Exception
    //   163	184	244	java/lang/Exception
    //   184	204	244	java/lang/Exception
    //   204	233	244	java/lang/Exception
  }
  
  private void a(int paramInt, JSONArray paramJSONArray)
  {
    int i4 = 0;
    if (i4 < paramJSONArray.length()) {}
    for (;;)
    {
      Object localObject2;
      int i1;
      int i3;
      int i2;
      boolean bool;
      int i5;
      try
      {
        localObject2 = paramJSONArray.getJSONObject(i4);
        int i6 = ((JSONObject)localObject2).getJSONObject("indices").getInt("first");
        if (((JSONObject)localObject2).isNull("indices")) {
          break label622;
        }
        if (((JSONObject)localObject2).getJSONObject("indices").isNull("step")) {
          break label617;
        }
        i1 = ((JSONObject)localObject2).getJSONObject("indices").getInt("step");
        if (((JSONObject)localObject2).getJSONObject("indices").isNull("count")) {
          break label605;
        }
        i3 = ((JSONObject)localObject2).getJSONObject("indices").getInt("count");
        i2 = i1;
        i1 = i3;
        if (((JSONObject)localObject2).isNull("view")) {
          break label589;
        }
        if (((JSONObject)localObject2).getJSONObject("view").isNull("style")) {
          break label581;
        }
        Object localObject1 = ((JSONObject)localObject2).getJSONObject("view").getString("style");
        if (((JSONObject)localObject2).getJSONObject("view").isNull("margin")) {
          break label575;
        }
        bool = ((JSONObject)localObject2).getJSONObject("view").getBoolean("margin");
        if (((JSONObject)localObject2).getJSONObject("view").isNull("unitId")) {
          break label563;
        }
        Object localObject3 = ((JSONObject)localObject2).getJSONObject("view").getString("unitId");
        localObject2 = localObject1;
        localObject1 = localObject3;
        i3 = paramInt + i6;
        i5 = i1;
        if (i6 < 0)
        {
          i3 = this.C.size() + i6 + 1;
          i5 = i1;
        }
        if ((i5 > 0) && (i3 >= 0) && (i3 <= this.C.size()))
        {
          localObject3 = new JSONObject();
          ((JSONObject)localObject3).put("type", "nativeAd");
          ((JSONObject)localObject3).put("style", localObject2);
          ((JSONObject)localObject3).put("margin", bool);
          if (!((String)localObject1).equals("")) {
            ((JSONObject)localObject3).put("unitId", localObject1);
          }
          try
          {
            if (!((JSONObject)localObject3).getString("type").equals("nativeAd")) {
              break label630;
            }
            if (asc.f(this)) {
              this.C.add(i3, new apu());
            } else if ((!((JSONObject)localObject3).isNull("style")) && (((JSONObject)localObject3).getString("style").equals("large"))) {
              this.C.add(i3, new aqd(this, (JSONObject)localObject3));
            }
          }
          catch (Exception localException2)
          {
            this.C.add(i3, new apu());
          }
        }
      }
      catch (Exception localException1)
      {
        i4 += 1;
      }
      if ((!localException2.isNull("style")) && (localException2.getString("style").equals("small")))
      {
        this.C.add(i3, new aqe(this, localException2));
      }
      else
      {
        this.C.add(i3, new apu());
        break label630;
        return;
        label563:
        localObject2 = localException1;
        String str = "";
        continue;
        label575:
        bool = false;
        continue;
        label581:
        str = "small";
        continue;
        label589:
        str = "";
        localObject2 = "small";
        bool = false;
        continue;
        label605:
        i3 = 1;
        i2 = i1;
        i1 = i3;
        continue;
        label617:
        i1 = 1;
        continue;
        label622:
        i1 = 1;
        i2 = 1;
        continue;
      }
      label630:
      i3 += i2;
      i5 -= 1;
    }
  }
  
  private void a(View paramView)
  {
    if (paramView != null) {}
    try
    {
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
      return;
    }
    catch (Exception paramView) {}
  }
  
  private void a(final String paramString1, final String paramString2, final boolean paramBoolean)
  {
    arx.a(arx.a(paramString1, "playlist"), new apj(this)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if (paramAnonymousArrayOfByte != null) {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
        }
        try
        {
          JSONObject localJSONObject = new JSONObject(paramAnonymousArrayOfXh).getJSONObject("getVector");
          paramAnonymousArrayOfXh = "";
          if (!localJSONObject.isNull("name")) {
            paramAnonymousArrayOfXh = localJSONObject.getString("name");
          }
          paramAnonymousArrayOfByte = "";
          if (!localJSONObject.isNull("ownerName")) {
            paramAnonymousArrayOfByte = localJSONObject.getString("ownerName");
          }
          String str1 = "";
          if (!localJSONObject.isNull("owner")) {
            str1 = localJSONObject.getString("owner");
          }
          String str2 = "";
          if (!localJSONObject.isNull("subsCnt")) {
            str2 = localJSONObject.getString("subsCnt");
          }
          paramAnonymousArrayOfXh = new apm(paramString1, paramAnonymousArrayOfXh, str1, paramAnonymousArrayOfByte, "", str2, MainPage.as.getString("url"), false);
          paramAnonymousArrayOfByte = localJSONObject.getJSONArray("items");
          paramAnonymousInt = 0;
          for (;;)
          {
            if (paramAnonymousInt < paramAnonymousArrayOfByte.length())
            {
              if (!paramAnonymousArrayOfByte.getJSONObject(paramAnonymousInt).getString("_id").equals(paramString2)) {}
            }
            else
            {
              for (;;)
              {
                int i = paramAnonymousArrayOfByte.length();
                MainPage.this.E.clear();
                paramAnonymousArrayOfByte = MainPage.this.b(paramString1, false);
                paramAnonymousArrayOfByte.F = paramAnonymousArrayOfXh;
                if (this.c != 1) {
                  if (this.c == -1) {
                    MixerBoxUtils.a(MainPage.this, paramString1, -1, paramAnonymousArrayOfByte, 0);
                  }
                }
                while (paramBoolean)
                {
                  MainPage.this.c(paramAnonymousArrayOfXh);
                  return;
                  if ((MainPage.this.aJ != null) && (MainPage.this.aJ.b.a())) {
                    MixerBoxUtils.a(MainPage.this, paramString1, -1, paramAnonymousArrayOfByte, 0);
                  } else {
                    MixerBoxUtils.a(MainPage.this, paramString1, i - paramAnonymousInt - 1, paramAnonymousArrayOfByte, 1);
                  }
                }
                paramAnonymousInt = 0;
              }
              return;
            }
            paramAnonymousInt += 1;
          }
          return;
        }
        catch (JSONException paramAnonymousArrayOfXh) {}
      }
    });
  }
  
  private void a(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    try
    {
      Bundle localBundle = new Bundle();
      Object localObject;
      long l1;
      if (this.I.b.contains("-"))
      {
        localObject = this.I.b.split("-");
        localBundle.putString("track", localObject[1]);
        localBundle.putString("artist", localObject[0]);
        l1 = Long.parseLong(this.I.c + "000", 10);
        localObject = new Intent();
        if (paramInt2 != 0) {
          break label315;
        }
        ((Intent)localObject).setAction("com.android.music.metachanged");
      }
      for (;;)
      {
        localBundle.putLong("duration", Long.valueOf(l1).longValue());
        if (paramInt1 != -1) {
          localBundle.putLong("position", Long.valueOf(Long.parseLong(paramInt1 + "000", 10)).longValue());
        }
        localBundle.putBoolean("playing", paramBoolean);
        localBundle.putString("scrobbling_source", "mbinc12.mb32b");
        ((Intent)localObject).putExtras(localBundle);
        sendBroadcast((Intent)localObject);
        return;
        if (this.I.b.contains(" "))
        {
          localObject = this.I.b.split(" ");
          if (localObject.length == 2)
          {
            localBundle.putString("track", localObject[1]);
            localBundle.putString("artist", localObject[0]);
            break;
          }
          localBundle.putString("track", this.I.b);
          break;
        }
        localBundle.putString("track", this.I.b);
        break;
        label315:
        ((Intent)localObject).setAction("com.android.music.playstatechanged");
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void b(JSONArray paramJSONArray)
  {
    int i1 = 0;
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {}
      try
      {
        MixerBoxUtils.a(this.C, paramJSONArray.getJSONObject(i1), this, 0, 0);
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
  
  private void c(JSONArray paramJSONArray)
  {
    int i2 = this.C.size();
    if ((this.an != null) && (this.an.a()))
    {
      Cursor localCursor = this.an.b();
      int i3 = localCursor.getCount();
      localCursor.moveToLast();
      int i1 = 0;
      if (i1 < i3)
      {
        int i4 = this.an.b(localCursor.getString(0));
        Object localObject1 = this.an;
        Object localObject2 = localCursor.getString(0);
        if (((arz)localObject1).a.isOpen())
        {
          localObject2 = ((arz)localObject1).a.rawQuery("SELECT * FROM tableSong WHERE PID=\"" + (String)localObject2 + "\"", null);
          if (((Cursor)localObject2).getCount() <= 0) {
            ((Cursor)localObject2).close();
          }
        }
        else
        {
          localObject1 = "";
        }
        for (;;)
        {
          localObject1 = new apm(localCursor.getString(0), localCursor.getString(2), localCursor.getString(3), localCursor.getString(4), String.valueOf(i4), localCursor.getString(6), (String)localObject1, false);
          localObject2 = b((apm)localObject1);
          this.C.add(localObject2);
          this.M.add(localObject1);
          localCursor.moveToPrevious();
          i1 += 1;
          break;
          ((Cursor)localObject2).moveToLast();
          localObject1 = "http://i.ytimg.com/vi/" + ((Cursor)localObject2).getString(4) + "/mqdefault.jpg";
          ((Cursor)localObject2).close();
        }
      }
      localCursor.close();
    }
    a(i2, paramJSONArray);
  }
  
  private void c(boolean paramBoolean)
  {
    try
    {
      boolean bool = asa.aB(this);
      if (this.U != null) {
        if (!bool) {
          break label33;
        }
      }
      label33:
      for (this.U.d = true; bool; this.U.d = paramBoolean) {
        return;
      }
      if (this.m == null) {
        break label112;
      }
    }
    finally {}
    int i1;
    if (!this.m.isEmpty()) {
      i1 = 0;
    }
    for (;;)
    {
      if (i1 < this.m.size())
      {
        if ((this.m.get(i1) instanceof arg)) {
          ((arg)this.m.get(i1)).b();
        }
      }
      else
      {
        label112:
        if (this.w != null) {
          arc.a(this.w.a);
        }
        if (this.v != null) {
          ard.a(this.v.b);
        }
        if (this.u != null) {
          arh.a(this.u.c);
        }
        if (this.t == null) {
          break;
        }
        ari.a(this.t.b);
        break;
      }
      i1 += 1;
    }
  }
  
  private void d(JSONArray paramJSONArray)
  {
    int i2 = this.C.size();
    Cursor localCursor = this.an.c();
    int i3 = localCursor.getCount();
    localCursor.moveToLast();
    int i1 = 0;
    while (i1 < i3)
    {
      boolean bool = false;
      if (localCursor.getInt(9) == 1) {
        bool = true;
      }
      apm localApm = new apm(localCursor.getString(1), localCursor.getString(2), localCursor.getString(3), localCursor.getString(4), localCursor.getString(5), localCursor.getString(6), localCursor.getString(7), bool);
      aqc localAqc = b(localApm);
      this.C.add(localAqc);
      this.L.add(localApm);
      localCursor.moveToPrevious();
      i1 += 1;
    }
    localCursor.close();
    a(i2, paramJSONArray);
  }
  
  private void d(boolean paramBoolean)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, 0);
    localLayoutParams.addRule(12);
    this.S.setLayoutParams(localLayoutParams);
    localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.setMargins(0, 0, 0, 0);
    localLayoutParams.addRule(12);
    this.s.setLayoutParams(localLayoutParams);
    if (paramBoolean) {
      this.S.setBannerAdListener(new MoPubView.BannerAdListener()
      {
        public final void onBannerClicked(MoPubView paramAnonymousMoPubView) {}
        
        public final void onBannerCollapsed(MoPubView paramAnonymousMoPubView) {}
        
        public final void onBannerExpanded(MoPubView paramAnonymousMoPubView) {}
        
        public final void onBannerFailed(MoPubView paramAnonymousMoPubView, MoPubErrorCode paramAnonymousMoPubErrorCode)
        {
          MainPage.z(MainPage.this);
        }
        
        public final void onBannerLoaded(MoPubView paramAnonymousMoPubView)
        {
          MainPage.y(MainPage.this);
        }
      });
    }
    for (;;)
    {
      this.S.setAutorefreshEnabled(paramBoolean);
      return;
      this.S.setBannerAdListener(null);
    }
  }
  
  private void g(int paramInt)
  {
    if (paramInt == 1)
    {
      u();
      o("playlist");
      return;
    }
    if (paramInt == 2)
    {
      v();
      o("newsfeed");
      return;
    }
    if (paramInt != 3)
    {
      if (paramInt == 4)
      {
        x();
        o("radio");
        return;
      }
      if (paramInt == 5)
      {
        y();
        o("setting");
        return;
      }
    }
    w();
    o("search");
  }
  
  private int h(int paramInt)
  {
    if ((this.F == null) || (this.F.size() == 0)) {
      return -1;
    }
    if ((this.i == null) || (paramInt >= this.F.size()) || (this.h >= this.F.size()) || (paramInt >= this.i.length))
    {
      Q();
      this.h = 0;
    }
    if (this.i.length != this.F.size())
    {
      Q();
      this.h = 0;
    }
    if (this.i[paramInt] >= this.F.size())
    {
      Q();
      this.h = 0;
    }
    this.h += 1;
    return this.i[paramInt];
  }
  
  private void o(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("tab", paramString);
      MixerBoxUtils.a(this, "FirstTab", localJSONObject);
      return;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
  }
  
  private void p(final String paramString)
  {
    arx.a("https://www.googleapis.com/youtube/v3/videos?part=contentDetails%2Csnippet&key=AIzaSyBCZ7B2rXSROSCMk-dZpXlTKCN8TXlPl24&id=" + paramString, new apj(this)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        paramAnonymousArrayOfXh = "";
        if (paramAnonymousArrayOfByte != null) {}
        try
        {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
          paramAnonymousArrayOfXh = new JSONObject(paramAnonymousArrayOfXh).getJSONArray("items");
          String str1 = paramAnonymousArrayOfXh.getJSONObject(0).getJSONObject("snippet").getString("title");
          String str2 = arx.q(paramString);
          String str3 = paramAnonymousArrayOfXh.getJSONObject(0).getJSONObject("contentDetails").getString("duration");
          paramAnonymousArrayOfByte = "'PT'";
          if (str3.contains("H")) {
            paramAnonymousArrayOfByte = "'PT'" + "H'H'";
          }
          paramAnonymousArrayOfXh = paramAnonymousArrayOfByte;
          if (str3.contains("M")) {
            paramAnonymousArrayOfXh = paramAnonymousArrayOfByte + "m'M'";
          }
          paramAnonymousArrayOfByte = paramAnonymousArrayOfXh;
          if (str3.contains("S")) {
            paramAnonymousArrayOfByte = paramAnonymousArrayOfXh + "s'S'";
          }
          paramAnonymousArrayOfXh = new SimpleDateFormat(paramAnonymousArrayOfByte).parse(str3);
          paramAnonymousArrayOfByte = Calendar.getInstance();
          paramAnonymousArrayOfByte.setTimeInMillis(paramAnonymousArrayOfXh.getTime());
          paramAnonymousArrayOfXh = new apn("", str1, String.valueOf(paramAnonymousArrayOfByte.get(13) + paramAnonymousArrayOfByte.get(12) * 60 + paramAnonymousArrayOfByte.get(10) * 3600), paramString, 0, 1, str2);
          paramAnonymousArrayOfByte = new ArrayList();
          paramAnonymousArrayOfByte.add(paramAnonymousArrayOfXh);
          MainPage.this.F = paramAnonymousArrayOfByte;
          MainPage.this.I = paramAnonymousArrayOfXh;
          MainPage.this.J = null;
          MainPage.this.G = 0;
          MainPage.this.a(true, 1, false);
          return;
        }
        catch (Exception paramAnonymousArrayOfXh) {}
      }
    });
  }
  
  private String q(String paramString)
  {
    String str = "";
    Uri localUri = ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), Long.valueOf(paramString).longValue());
    ContentResolver localContentResolver = getContentResolver();
    paramString = str;
    try
    {
      if (localContentResolver.openInputStream(localUri) != null) {
        paramString = localUri.toString();
      }
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  /* Error */
  private static String r(String paramString)
  {
    // Byte code:
    //   0: ldc_w 374
    //   3: astore_2
    //   4: aload_0
    //   5: ldc_w 1732
    //   8: invokestatic 1737	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   11: astore_0
    //   12: getstatic 376	mbinc12/mb32b/MainPage:aT	Ljava/lang/String;
    //   15: invokevirtual 740	java/lang/String:length	()I
    //   18: ifle +142 -> 160
    //   21: getstatic 376	mbinc12/mb32b/MainPage:aT	Ljava/lang/String;
    //   24: ldc_w 1739
    //   27: aload_0
    //   28: invokevirtual 1743	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   31: astore_0
    //   32: aconst_null
    //   33: astore_2
    //   34: new 1330	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 1331	java/lang/StringBuilder:<init>	()V
    //   41: astore_3
    //   42: new 1745	java/net/URL
    //   45: dup
    //   46: aload_0
    //   47: invokespecial 1746	java/net/URL:<init>	(Ljava/lang/String;)V
    //   50: invokevirtual 1750	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   53: checkcast 1752	java/net/HttpURLConnection
    //   56: astore_0
    //   57: new 1754	java/io/InputStreamReader
    //   60: dup
    //   61: aload_0
    //   62: invokevirtual 1758	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   65: invokespecial 1761	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   68: astore_2
    //   69: sipush 1024
    //   72: newarray char
    //   74: astore 4
    //   76: aload_2
    //   77: aload 4
    //   79: invokevirtual 1765	java/io/InputStreamReader:read	([C)I
    //   82: istore_1
    //   83: iload_1
    //   84: iconst_m1
    //   85: if_icmpeq +33 -> 118
    //   88: aload_3
    //   89: aload 4
    //   91: iconst_0
    //   92: iload_1
    //   93: invokevirtual 1768	java/lang/StringBuilder:append	([CII)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: goto -21 -> 76
    //   100: astore_2
    //   101: aload_0
    //   102: ifnull +7 -> 109
    //   105: aload_0
    //   106: invokevirtual 1771	java/net/HttpURLConnection:disconnect	()V
    //   109: new 433	java/lang/String
    //   112: dup
    //   113: aload_3
    //   114: invokespecial 1774	java/lang/String:<init>	(Ljava/lang/StringBuilder;)V
    //   117: areturn
    //   118: aload_0
    //   119: ifnull -10 -> 109
    //   122: aload_0
    //   123: invokevirtual 1771	java/net/HttpURLConnection:disconnect	()V
    //   126: goto -17 -> 109
    //   129: astore_3
    //   130: aload_2
    //   131: astore_0
    //   132: aload_3
    //   133: astore_2
    //   134: aload_0
    //   135: ifnull +7 -> 142
    //   138: aload_0
    //   139: invokevirtual 1771	java/net/HttpURLConnection:disconnect	()V
    //   142: aload_2
    //   143: athrow
    //   144: astore_2
    //   145: goto -11 -> 134
    //   148: astore_0
    //   149: aconst_null
    //   150: astore_0
    //   151: goto -50 -> 101
    //   154: astore_0
    //   155: aload_2
    //   156: astore_0
    //   157: goto -145 -> 12
    //   160: ldc_w 374
    //   163: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	164	0	paramString	String
    //   82	11	1	i1	int
    //   3	74	2	localObject1	Object
    //   100	31	2	localException	Exception
    //   133	10	2	localObject2	Object
    //   144	12	2	localObject3	Object
    //   41	73	3	localStringBuilder	StringBuilder
    //   129	4	3	localObject4	Object
    //   74	16	4	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   57	76	100	java/lang/Exception
    //   76	83	100	java/lang/Exception
    //   88	97	100	java/lang/Exception
    //   42	57	129	finally
    //   57	76	144	finally
    //   76	83	144	finally
    //   88	97	144	finally
    //   42	57	148	java/lang/Exception
    //   4	12	154	java/io/UnsupportedEncodingException
  }
  
  public final void A()
  {
    if (!C()) {}
    are localAre;
    do
    {
      return;
      this.ao = 14;
      localAre = new are();
      a(localAre, true);
      localAre.c = new ArrayList();
      Object localObject = localAre.getActivity().getContentResolver().query(MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI, new String[] { "_id", "name", "_data" }, null, null, null);
      int i2 = ((Cursor)localObject).getCount();
      if (i2 > 0)
      {
        ((Cursor)localObject).moveToFirst();
        int i1 = 0;
        while (i1 < i2)
        {
          String str = localAre.a(((Cursor)localObject).getString(0));
          localAre.c.add(new apm("PLAYLISTMP3PLAYLISTID", ((Cursor)localObject).getString(1), ((Cursor)localObject).getString(0), "", "", "0", str, false));
          ((Cursor)localObject).moveToNext();
          i1 += 1;
        }
      }
      ((Cursor)localObject).close();
      localObject = new apb(localAre.getActivity(), localAre.c);
      localAre.g.setAdapter((ListAdapter)localObject);
      localAre.a();
      localAre.b();
      localAre.c();
    } while (localAre.c.size() != 0);
    if (localAre.b.size() == 0)
    {
      if (localAre.d.size() == 0)
      {
        localAre.e.setCurrentTab(3);
        return;
      }
      localAre.e.setCurrentTab(2);
      return;
    }
    localAre.e.setCurrentTab(1);
  }
  
  public final void B()
  {
    if (this.aD) {
      MixerBoxUtils.f(this, "OnScreenOn");
    }
    if ((this.aJ != null) && (asa.H(this) == 1) && (this.I != null) && (this.I.f == 1))
    {
      if (!ScreenReceiver.b) {
        break label88;
      }
      ((AudioManager)getSystemService("audio")).setStreamVolume(3, this.aA, 0);
      f();
    }
    for (;;)
    {
      this.aJ.d();
      ScreenReceiver.b = false;
      return;
      label88:
      d();
    }
  }
  
  public final boolean C()
  {
    boolean bool = true;
    if (!this.z)
    {
      this.z = true;
      bool = false;
    }
    return bool;
  }
  
  public final void D()
  {
    int i2 = 0;
    Object localObject1 = this.l.beginTransaction();
    Object localObject2;
    if (!this.m.isEmpty())
    {
      if ((this.m.lastElement() instanceof aro))
      {
        localObject2 = (aro)this.m.lastElement();
        if (((aro)localObject2).b != null) {
          ((aro)localObject2).b.loadUrl("about:blank");
        }
      }
      if (((this.m.lastElement() instanceof arn)) || ((this.m.lastElement() instanceof ark)) || ((this.m.lastElement() instanceof SongFragment)) || ((this.m.lastElement() instanceof are)) || ((this.m.lastElement() instanceof arm)) || ((this.m.lastElement() instanceof aro)))
      {
        ((FragmentTransaction)localObject1).remove((Fragment)this.m.pop());
        K.pop();
        this.ao = ((Integer)K.lastElement()).intValue();
      }
    }
    else
    {
      if (!this.m.isEmpty()) {
        ((FragmentTransaction)localObject1).show((Fragment)this.m.lastElement());
      }
      if (this.m.size() == 1) {
        art.a(this);
      }
      ((FragmentTransaction)localObject1).commit();
      if (!K.isEmpty()) {
        art.a(this.al, ((Integer)K.lastElement()).intValue());
      }
      if (((this.m.lastElement() instanceof SongFragment)) && ((((SongFragment)this.m.lastElement()).b.getAdapter() == null) || (((SongFragment)this.m.lastElement()).a == null))) {
        art.b(this.al, 11);
      }
      localObject1 = (FrameLayout)findViewById(2131624234);
      localObject2 = new RelativeLayout.LayoutParams(-1, -1);
      if (!(this.m.lastElement() instanceof aro)) {
        break label529;
      }
      this.s.setVisibility(8);
      ((FrameLayout)findViewById(2131624261)).setVisibility(8);
      ((RelativeLayout.LayoutParams)localObject2).addRule(2, 2131624243);
      ((FrameLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      label390:
      if ((this.m.size() == 1) && (this.aZ != null) && (this.aZ.isReady())) {
        if ((this.aJ != null) && ((this.aJ == null) || (this.aJ.b.a()))) {
          break label573;
        }
      }
    }
    label529:
    label573:
    for (int i1 = 1;; i1 = 0)
    {
      if (asc.f(this)) {
        i1 = i2;
      }
      for (;;)
      {
        if (i1 != 0)
        {
          long l1 = asa.N(this);
          if (l1 > 0L)
          {
            long l2 = asa.O(this);
            long l3 = Calendar.getInstance().getTimeInMillis();
            if (l3 - l2 > l1)
            {
              this.aZ.show();
              asa.d(this, l3);
            }
          }
        }
        return;
        ((FragmentTransaction)localObject1).hide((Fragment)this.m.pop());
        break;
        this.s.setVisibility(0);
        ((FrameLayout)findViewById(2131624261)).setVisibility(0);
        ((RelativeLayout.LayoutParams)localObject2).addRule(2, 2131624235);
        ((FrameLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
        break label390;
      }
    }
  }
  
  public final void E()
  {
    if (this.aJ != null)
    {
      if (asa.H(this) != 2) {
        break label23;
      }
      this.aJ.c();
    }
    label23:
    do
    {
      WindowPlayerService.b localB;
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
                if (asa.H(this) != 0) {
                  break;
                }
              } while ((this.I == null) || (this.I.f != 1));
              this.aJ.f("javascript:pauseVideo()");
              this.aJ.b(true);
              this.aJ.c();
              return;
            } while (asa.H(this) != 1);
            if (!this.aJ.b.a()) {
              break;
            }
          } while ((this.I == null) || (this.I.f != 1));
          if (this.aD)
          {
            MixerBoxUtils.f(this, "MultiTasking");
            asa.m(this, false);
          }
          localB = this.aJ;
        } while (((localB.b.j != null) && (localB.b.j.getVisibility() == 0)) || (localB.b.T.getVisibility() == 0));
        if ((localB.b.c != 0) && (localB.b.c != -1) && (localB.b.W.getVisibility() != 0)) {
          localB.b.a(false);
        }
      } while (localB.b.c == 1);
      WindowPlayerService.a(localB.b, new boolean[0]);
      return;
    } while ((!this.aD) || (asa.aJ(this)) || (!asa.aL(this)));
    T();
  }
  
  public final boolean F()
  {
    return (this.aJ != null) && (this.aJ.b.a());
  }
  
  public final void G()
  {
    for (;;)
    {
      int i1;
      try
      {
        if ((this.m != null) && (!this.m.isEmpty()))
        {
          i1 = 0;
          if (i1 < this.m.size())
          {
            new StringBuilder("update favorite: ").append(((Fragment)this.m.get(i1)).getClass().getName());
            if (!(this.m.get(i1) instanceof arg)) {
              break label119;
            }
            ((arg)this.m.get(i1)).c();
            break label119;
          }
        }
        if (this.v != null) {
          MixerBoxUtils.a(this.v.b);
        }
        return;
      }
      finally {}
      label119:
      i1 += 1;
    }
  }
  
  public final long a(ask paramAsk, String paramString1, String paramString2, String paramString3)
  {
    return asc.a(this.an, paramAsk, paramString1, paramString2, paramString3);
  }
  
  public final Cursor a(long paramLong)
  {
    try
    {
      Cursor localCursor = this.an.a.rawQuery("SELECT * FROM tableIapReceipt WHERE _ID=" + paramLong, null);
      return localCursor;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public final arn a(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    arn localArn = new arn();
    localArn.d = paramString1;
    localArn.e = paramString2;
    localArn.f = paramInt;
    localArn.h = paramString3;
    if (!C()) {
      return localArn;
    }
    this.ao = 10;
    a(localArn, true);
    paramString2 = new HashMap();
    paramString2.put("vectorId", paramString1);
    MixerBoxUtils.a("page:vector", paramString2);
    return localArn;
  }
  
  /* Error */
  public final void a()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: iconst_1
    //   4: istore 4
    //   6: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   9: ifnull +40 -> 49
    //   12: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   15: ldc_w 803
    //   18: ldc_w 374
    //   21: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   24: invokevirtual 740	java/lang/String:length	()I
    //   27: ifle +22 -> 49
    //   30: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   33: ldc_w 805
    //   36: ldc_w 374
    //   39: invokestatic 736	mbinc12/mb32b/MainPage:a	(Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   42: invokevirtual 740	java/lang/String:length	()I
    //   45: ifle +4 -> 49
    //   48: return
    //   49: getstatic 812	mbinc12/mb32b/MyApplication:b	Z
    //   52: ifeq +142 -> 194
    //   55: aload_0
    //   56: invokestatic 2077	asa:av	(Landroid/content/Context;)Z
    //   59: istore 6
    //   61: iload 5
    //   63: istore_3
    //   64: aload_0
    //   65: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   68: ifnull +29 -> 97
    //   71: iload 5
    //   73: istore_3
    //   74: aload_0
    //   75: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   78: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   81: invokevirtual 836	mbinc12/mb32b/services/WindowPlayerService:a	()Z
    //   84: ifeq +13 -> 97
    //   87: iload 5
    //   89: istore_3
    //   90: iload 6
    //   92: ifne +5 -> 97
    //   95: iconst_1
    //   96: istore_3
    //   97: aload_0
    //   98: invokestatic 1097	asc:f	(Landroid/content/Context;)Z
    //   101: ifeq +281 -> 382
    //   104: iload 4
    //   106: istore_3
    //   107: aload_0
    //   108: ldc_w 2079
    //   111: invokestatic 1013	mbinc12/mb32b/utils/MixerBoxUtils:f	(Landroid/content/Context;Ljava/lang/String;)V
    //   114: aload_0
    //   115: invokestatic 2081	asa:B	(Landroid/content/Context;)Ljava/lang/String;
    //   118: astore 7
    //   120: new 609	org/json/JSONObject
    //   123: dup
    //   124: invokespecial 610	org/json/JSONObject:<init>	()V
    //   127: astore 8
    //   129: aload 7
    //   131: ldc_w 2083
    //   134: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: ifeq +31 -> 168
    //   140: aload 8
    //   142: ldc_w 2085
    //   145: ldc_w 2086
    //   148: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   151: pop
    //   152: aload_0
    //   153: iload_3
    //   154: aload 8
    //   156: new 54	mbinc12/mb32b/MainPage$3
    //   159: dup
    //   160: aload_0
    //   161: invokespecial 2087	mbinc12/mb32b/MainPage$3:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   164: invokestatic 2090	tl:a	(Landroid/app/Activity;ZLorg/json/JSONObject;Ltl$a;)V
    //   167: return
    //   168: aload 7
    //   170: ldc_w 2092
    //   173: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   176: ifeq -24 -> 152
    //   179: aload 8
    //   181: ldc_w 2085
    //   184: ldc_w 2093
    //   187: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   190: pop
    //   191: goto -39 -> 152
    //   194: aload_0
    //   195: invokestatic 2095	asa:au	(Landroid/content/Context;)Z
    //   198: istore_3
    //   199: aload_0
    //   200: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   203: ifnull +26 -> 229
    //   206: aload_0
    //   207: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   210: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   213: invokevirtual 836	mbinc12/mb32b/services/WindowPlayerService:a	()Z
    //   216: ifeq +13 -> 229
    //   219: iload_3
    //   220: ifne +9 -> 229
    //   223: aload_0
    //   224: iconst_1
    //   225: putfield 391	mbinc12/mb32b/MainPage:aX	Z
    //   228: return
    //   229: aload_0
    //   230: invokestatic 2097	asa:at	(Landroid/content/Context;)I
    //   233: istore_1
    //   234: aload_0
    //   235: invokestatic 808	asa:l	(Landroid/content/Context;)I
    //   238: istore_2
    //   239: iload_2
    //   240: iload_1
    //   241: if_icmplt -193 -> 48
    //   244: aload_0
    //   245: invokestatic 2099	asa:j	(Landroid/content/Context;)Ljava/lang/String;
    //   248: ldc_w 374
    //   251: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   254: istore_3
    //   255: iload_3
    //   256: ifne -208 -> 48
    //   259: new 609	org/json/JSONObject
    //   262: dup
    //   263: aload_0
    //   264: invokestatic 2099	asa:j	(Landroid/content/Context;)Ljava/lang/String;
    //   267: invokespecial 1052	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   270: astore 7
    //   272: aload 7
    //   274: ldc_w 2101
    //   277: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   280: ldc_w 374
    //   283: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   286: ifne -238 -> 48
    //   289: aload 7
    //   291: ldc_w 2101
    //   294: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   297: aload_0
    //   298: invokestatic 2103	asa:k	(Landroid/content/Context;)Ljava/lang/String;
    //   301: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   304: ifne -256 -> 48
    //   307: aload 7
    //   309: ldc_w 2105
    //   312: invokevirtual 1209	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   315: ifeq -267 -> 48
    //   318: aload 7
    //   320: ldc_w 2107
    //   323: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   326: astore 8
    //   328: new 609	org/json/JSONObject
    //   331: dup
    //   332: invokespecial 610	org/json/JSONObject:<init>	()V
    //   335: astore 9
    //   337: aload 9
    //   339: ldc_w 612
    //   342: aload 8
    //   344: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   347: pop
    //   348: aload_0
    //   349: ldc_w 620
    //   352: aload 9
    //   354: invokestatic 623	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   357: aload_0
    //   358: aload 8
    //   360: iconst_0
    //   361: aload 7
    //   363: ldc_w 2101
    //   366: invokevirtual 1068	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   369: iload_2
    //   370: invokestatic 844	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;ZLjava/lang/String;I)V
    //   373: return
    //   374: astore 7
    //   376: return
    //   377: astore 10
    //   379: goto -31 -> 348
    //   382: goto -275 -> 107
    //   385: astore 7
    //   387: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	388	0	this	MainPage
    //   233	9	1	i1	int
    //   238	132	2	i2	int
    //   63	193	3	bool1	boolean
    //   4	101	4	bool2	boolean
    //   1	87	5	bool3	boolean
    //   59	32	6	bool4	boolean
    //   118	244	7	localObject1	Object
    //   374	1	7	localJSONException1	JSONException
    //   385	1	7	localException	Exception
    //   127	232	8	localObject2	Object
    //   335	18	9	localJSONObject	JSONObject
    //   377	1	10	localJSONException2	JSONException
    // Exception table:
    //   from	to	target	type
    //   259	337	374	org/json/JSONException
    //   348	373	374	org/json/JSONException
    //   337	348	377	org/json/JSONException
    //   49	61	385	java/lang/Exception
    //   64	71	385	java/lang/Exception
    //   74	87	385	java/lang/Exception
    //   97	104	385	java/lang/Exception
    //   107	152	385	java/lang/Exception
    //   152	167	385	java/lang/Exception
    //   168	191	385	java/lang/Exception
    //   194	219	385	java/lang/Exception
    //   223	228	385	java/lang/Exception
    //   229	239	385	java/lang/Exception
    //   244	255	385	java/lang/Exception
    //   259	337	385	java/lang/Exception
    //   337	348	385	java/lang/Exception
    //   348	373	385	java/lang/Exception
  }
  
  public final void a(int paramInt)
  {
    if (this.aJ != null) {
      this.aJ.c(MixerBoxUtils.a(String.valueOf(paramInt)));
    }
    try
    {
      this.aJ.c(paramInt);
      return;
    }
    catch (Exception localException)
    {
      this.aJ.c(600);
    }
  }
  
  public final void a(int paramInt1, int paramInt2)
  {
    JSONObject localJSONObject;
    if (asa.aR(this)) {
      localJSONObject = new JSONObject();
    }
    try
    {
      localJSONObject.put("from", paramInt1);
      localJSONObject.put("state", paramInt2);
      MixerBoxUtils.a(this, "PlayerStateChanged", localJSONObject);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final void a(final int paramInt, final Runnable paramRunnable)
  {
    Object localObject1 = arv.a(this);
    Object localObject2 = new int[2];
    Object tmp13_11 = localObject2;
    tmp13_11[0] = 0;
    Object tmp17_13 = tmp13_11;
    tmp17_13[1] = 0;
    tmp17_13;
    localObject2[0] = ((arv)localObject1).a;
    localObject2[1] = ((arv)localObject1).b;
    final int i1 = localObject2[0];
    final int i2 = localObject2[1];
    final boolean bool;
    if (i2 > i1) {
      bool = true;
    }
    int i3;
    switch (paramInt)
    {
    case 4: 
    default: 
    case 1: 
    case 2: 
    case 3: 
      do
      {
        int i4;
        do
        {
          return;
          bool = false;
          break;
          if (MixerBoxUtils.e(this) > 0)
          {
            localObject1 = getResources().getString(2131230841);
            localObject2 = arv.a(this);
            i3 = (int)(i1 * 0.17D);
            i4 = (int)(i2 * 0.3D);
            i1 = (int)(Math.min(i1, i2) * 0.3D);
            if (!bool) {
              break label205;
            }
          }
          for (paramInt = 2;; paramInt = 3)
          {
            ((arv)localObject2).a(i3, i4, i1, (String)localObject1, paramInt, paramRunnable);
            return;
            localObject1 = getResources().getString(2131230840);
            break;
          }
        } while (this.t == null);
        if (this.t != null)
        {
          localObject1 = this.t;
          if (((ari)localObject1).b != null)
          {
            paramInt = ((ari)localObject1).b.getFirstVisiblePosition();
            if (paramInt <= ((ari)localObject1).b.getLastVisiblePosition()) {
              if ((((ari)localObject1).b.getItemAtPosition(paramInt) instanceof apq)) {
                if (paramInt + 1 <= ((ari)localObject1).b.getLastVisiblePosition())
                {
                  localObject1 = ((ari)localObject1).b.getChildAt(paramInt + 1);
                  if (localObject1 == null) {
                    break label414;
                  }
                  localObject2 = new int[2];
                  Object tmp313_311 = localObject2;
                  tmp313_311[0] = 0;
                  Object tmp317_313 = tmp313_311;
                  tmp317_313[1] = 0;
                  tmp317_313;
                  ((View)localObject1).getLocationInWindow((int[])localObject2);
                  i3 = localObject2[1];
                  localObject1 = arv.a(this);
                  i4 = (int)(i1 * 0.17D);
                  i1 = (int)(Math.min(i1, i2) * 0.3D);
                  localObject2 = getResources().getString(2131230843);
                  if (!bool) {
                    break label416;
                  }
                }
              }
            }
          }
        }
        for (paramInt = 2;; paramInt = 3)
        {
          ((arv)localObject1).a(i4, i3, i1, (String)localObject2, paramInt, paramRunnable);
          return;
          localObject1 = null;
          break label301;
          paramInt += 1;
          break label247;
          localObject1 = null;
          break label301;
          break;
        }
        if (this.t != null)
        {
          localObject1 = this.t;
          if (((ari)localObject1).b != null)
          {
            paramInt = 0;
            if (paramInt < ((ari)localObject1).b.getCount()) {
              if (!(((ari)localObject1).b.getItemAtPosition(paramInt) instanceof apv)) {}
            }
          }
        }
        for (;;)
        {
          if (paramInt >= 0) {
            break label492;
          }
          asa.bo(this);
          return;
          paramInt += 1;
          break;
          paramInt = -1;
        }
        localObject1 = this.t.b;
      } while (localObject1 == null);
      ((ListView)localObject1).smoothScrollToPositionFromTop(((ListView)localObject1).getCount() - 1, 0, 300);
      ((ListView)localObject1).postDelayed(new Runnable()
      {
        public final void run()
        {
          int j = (int)(i2 * 0.7D);
          int i = j;
          if (this.b != null)
          {
            this.b.smoothScrollBy(0, 0);
            localObject1 = this.b.getChildAt(paramInt - this.b.getFirstVisiblePosition());
            i = j;
            if (localObject1 != null)
            {
              localObject2 = new int[2];
              Object tmp64_62 = localObject2;
              tmp64_62[0] = 0;
              Object tmp68_64 = tmp64_62;
              tmp68_64[1] = 0;
              tmp68_64;
              ((View)localObject1).getLocationInWindow((int[])localObject2);
              i = localObject2[1];
            }
          }
          Object localObject1 = arv.a(MainPage.this);
          int k = (int)(i1 * 0.17D);
          int m = (int)(Math.min(i1, i2) * 0.3D);
          Object localObject2 = MainPage.this.getResources().getString(2131230844);
          if (bool) {}
          for (j = 5;; j = 3)
          {
            ((arv)localObject1).a(k, i, m, (String)localObject2, j, paramRunnable);
            return;
          }
        }
      }, 350L);
      return;
    case 5: 
      label205:
      label247:
      label301:
      label414:
      label416:
      label492:
      if (this.al != null)
      {
        localObject1 = this.al.findItem(999);
        if ((localObject1 != null) && (cd.e((MenuItem)localObject1))) {
          cd.d((MenuItem)localObject1);
        }
      }
      localObject1 = new TypedValue();
      if (!getTheme().resolveAttribute(16843499, (TypedValue)localObject1, true)) {
        break;
      }
    }
    for (paramInt = TypedValue.complexToDimensionPixelSize(((TypedValue)localObject1).data, getResources().getDisplayMetrics());; paramInt = 0)
    {
      localObject1 = arv.a(this);
      i1 = (int)(i1 - paramInt * 2.5D);
      i2 = (int)(paramInt * 0.5D);
      i3 = (int)(paramInt * 1.2D);
      localObject2 = getResources().getString(2131230839);
      if (bool) {}
      for (paramInt = 1;; paramInt = 4)
      {
        ((arv)localObject1).a(i1, i2, i3, (String)localObject2, paramInt, paramRunnable);
        return;
      }
      if ((this.t == null) || (!this.t.a(6))) {
        break;
      }
      this.t.b.postDelayed(new Runnable()
      {
        public final void run()
        {
          int j = MainPage.this.t.b(6);
          if (j < 0)
          {
            MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131230845), 1, new boolean[0]);
            paramRunnable.run();
            return;
          }
          arv localArv = arv.a(MainPage.this);
          int k = (int)(i1 * 0.1D);
          int m = (int)(Math.min(i1, i2) * 0.25D);
          String str = MainPage.this.getResources().getString(2131230845);
          if (bool) {}
          for (int i = 2;; i = 3)
          {
            localArv.a(k, j, m, str, i, paramRunnable);
            return;
          }
        }
      }, 100L);
      return;
      if ((this.t == null) || (!this.t.a(7))) {
        break;
      }
      this.t.b.postDelayed(new Runnable()
      {
        public final void run()
        {
          int j = MainPage.this.t.b(7);
          if (j < 0)
          {
            MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131230847), 1, new boolean[0]);
            paramRunnable.run();
            return;
          }
          arv localArv = arv.a(MainPage.this);
          int k = (int)(i1 * 0.1D);
          int m = (int)(Math.min(i1, i2) * 0.25D);
          String str = MainPage.this.getResources().getString(2131230847);
          if (bool) {}
          for (int i = 2;; i = 3)
          {
            localArv.a(k, j, m, str, i, paramRunnable);
            return;
          }
        }
      }, 100L);
      return;
    }
  }
  
  public final void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      arz localArz = this.an;
      Cursor localCursor = localArz.a.rawQuery("SELECT * FROM tableIapReceipt WHERE _ID=" + paramLong, null);
      ContentValues localContentValues;
      if (localCursor.getCount() > 0)
      {
        localCursor.moveToFirst();
        localContentValues = new ContentValues();
        if (!paramBoolean) {
          break label117;
        }
        localContentValues.put("PROCESS_SUCCESS", Integer.valueOf(1));
      }
      for (;;)
      {
        localArz.a.update("tableIapReceipt", localContentValues, "_ID=" + paramLong, null);
        localCursor.close();
        return;
        label117:
        localContentValues.put("PROCESS_SUCCESS", Integer.valueOf(0));
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void a(Fragment paramFragment, boolean paramBoolean)
  {
    
    if ((art.b != null) && (art.b != null))
    {
      art.b.finish();
      art.b = null;
      art.c = new ArrayList();
      ((MainPage)art.a).s();
    }
    Object localObject1 = (FrameLayout)findViewById(2131624234);
    Object localObject2 = new RelativeLayout.LayoutParams(-1, -1);
    if ((paramFragment instanceof aro))
    {
      this.s.setVisibility(8);
      ((FrameLayout)findViewById(2131624261)).setVisibility(8);
      ((FrameLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = this.l.beginTransaction();
      if (!this.m.empty()) {
        ((FragmentTransaction)localObject1).hide((Fragment)this.m.lastElement());
      }
      if (paramBoolean) {
        break label312;
      }
      this.m.clear();
      K.clear();
      art.a(this);
      S();
      label161:
      this.l.executePendingTransactions();
      if (!paramFragment.isAdded()) {
        break label349;
      }
      ((FragmentTransaction)localObject1).show(paramFragment);
    }
    for (;;)
    {
      ((FragmentTransaction)localObject1).commit();
      this.l.executePendingTransactions();
      this.m.add(paramFragment);
      K.add(Integer.valueOf(this.ao));
      if (this.al != null) {
        art.a(this.al, ((Integer)K.lastElement()).intValue());
      }
      if ((this.aJ != null) && (this.aJ.b.a())) {
        this.aJ.j();
      }
      return;
      ((FrameLayout)findViewById(2131624261)).setVisibility(0);
      this.s.setVisibility(0);
      ((RelativeLayout.LayoutParams)localObject2).addRule(2, 2131624235);
      ((FrameLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      break;
      label312:
      art.a = this;
      localObject2 = ((ActionBarActivity)this).getSupportActionBar();
      ((ActionBar)localObject2).f();
      ((ActionBar)localObject2).d();
      ((ActionBar)localObject2).e();
      ((ActionBar)localObject2).a(true);
      break label161;
      label349:
      ((FragmentTransaction)localObject1).add(2131624234, paramFragment);
    }
  }
  
  public final void a(apm paramApm)
  {
    int i1 = 0;
    Object localObject = paramApm.a;
    String str = paramApm.b;
    MixerBoxUtils.a(this, getResources().getString(2131230818), 0, new boolean[0]);
    if (((this.m.lastElement() instanceof SongFragment)) && (((SongFragment)this.m.lastElement()).F.a.equals(localObject))) {}
    label171:
    label283:
    label430:
    label433:
    for (;;)
    {
      return;
      if (((String)localObject).equals("PLAYLISTHISTORYID"))
      {
        a((String)localObject);
        return;
      }
      if ((((String)localObject).equals("PLAYLISTMP3ALBUMID")) || (((String)localObject).equals("PLAYLISTMP3ARTISTID")) || (((String)localObject).equals("PLAYLISTMP3PLAYLISTID")))
      {
        A();
        paramApm = (are)this.m.lastElement();
        if (((String)localObject).equals("PLAYLISTMP3ALBUMID"))
        {
          paramApm.e.setCurrentTabByTag(getString(2131230814));
          paramApm = paramApm.i;
          int i2 = paramApm.getCount();
          if (i1 >= i2) {
            break label430;
          }
          if (!((apm)paramApm.getItemAtPosition(i1)).b.equals(str)) {
            break label283;
          }
        }
      }
      for (;;)
      {
        if (i1 < 0) {
          break label433;
        }
        paramApm.performItemClick(paramApm.getAdapter().getView(i1, null, null), i1, paramApm.getAdapter().getItemId(i1));
        return;
        if (((String)localObject).equals("PLAYLISTMP3PLAYLISTID"))
        {
          paramApm.e.setCurrentTabByTag(getString(2131230947));
          paramApm = paramApm.g;
          break;
        }
        paramApm.e.setCurrentTabByTag(getString(2131230816));
        paramApm = paramApm.h;
        break;
        i1 += 1;
        break label171;
        if (((String)localObject).equals("PLAYLISTMP3ID"))
        {
          A();
          ((are)this.m.lastElement()).e.setCurrentTabByTag(getString(2131230996));
          return;
        }
        if ((!this.am) && (i((String)localObject)))
        {
          str = paramApm.a;
          this.E.clear();
          localObject = b(str, true);
          ((SongFragment)localObject).F = paramApm;
          MixerBoxUtils.a(this, str, false, (SongFragment)localObject);
          return;
        }
        str = paramApm.i;
        if ((str != null) && (!str.isEmpty()))
        {
          a((String)localObject, false, paramApm.g, "relatedMusic");
          return;
        }
        a((String)localObject, false, paramApm.g, null);
        return;
        i1 = -1;
      }
    }
  }
  
  public final void a(String paramString)
  {
    this.E.clear();
    SongFragment localSongFragment = b(paramString, true);
    localSongFragment.F = h(paramString);
    MixerBoxUtils.a(this, "PLAYLISTHISTORYID", false, localSongFragment);
  }
  
  public final void a(String paramString1, String paramString2)
  {
    this.bo = new Thread(new f(paramString1, paramString2));
    this.bo.start();
  }
  
  public final void a(String paramString, boolean paramBoolean)
  {
    a(paramString, paramBoolean, null, null);
  }
  
  public final void a(final String paramString1, final boolean paramBoolean, final String paramString2, final String paramString3)
  {
    arx.a(arx.a(paramString1, 0, "playlist"), new apj(this)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        if (paramAnonymousArrayOfByte != null) {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
        }
        try
        {
          JSONObject localJSONObject = new JSONObject(paramAnonymousArrayOfXh).getJSONObject("getVector");
          label61:
          String str1;
          label75:
          String str2;
          label89:
          String str3;
          if (localJSONObject.isNull("name"))
          {
            paramAnonymousArrayOfXh = "";
            if (!localJSONObject.isNull("owner")) {
              break label292;
            }
            paramAnonymousArrayOfByte = "";
            if (!localJSONObject.isNull("ownerName")) {
              break label303;
            }
            str1 = "";
            if (!localJSONObject.isNull("subsCnt")) {
              break label315;
            }
            str2 = "0";
            if (!localJSONObject.isNull("total")) {
              break label327;
            }
            str3 = "";
            label103:
            if (MainPage.as == null) {
              break label339;
            }
          }
          label292:
          label303:
          label315:
          label327:
          label339:
          for (paramAnonymousArrayOfXh = new apm(paramString1, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, str1, str3, str2, MainPage.as.getString("url"), false);; paramAnonymousArrayOfXh = new apm(paramString1, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, str1, str3, str2, paramString2, false))
          {
            if ((paramString3 != null) && (!localJSONObject.isNull("names")))
            {
              paramAnonymousArrayOfByte = localJSONObject.getJSONObject("names");
              if (!paramAnonymousArrayOfByte.isNull(paramString3)) {
                paramAnonymousArrayOfXh.i = paramAnonymousArrayOfByte.getString(paramString3);
              }
            }
            MainPage.this.E.clear();
            paramAnonymousArrayOfByte = MainPage.this.b(paramString1, false);
            paramAnonymousArrayOfByte.F = paramAnonymousArrayOfXh;
            if (((MainPage.this.aJ == null) || (!MainPage.this.aJ.b.a()) || ((paramString3 != null) && (paramString3.equals("relatedMusic")))) && (paramBoolean)) {
              break label367;
            }
            MixerBoxUtils.a(MainPage.this, paramString1, -1, paramAnonymousArrayOfByte, 0);
            return;
            paramAnonymousArrayOfXh = localJSONObject.getString("name");
            break;
            paramAnonymousArrayOfByte = localJSONObject.getString("owner");
            break label61;
            str1 = localJSONObject.getString("ownerName");
            break label75;
            str2 = localJSONObject.getString("subsCnt");
            break label89;
            str3 = localJSONObject.getString("total");
            break label103;
          }
          label367:
          MixerBoxUtils.a(MainPage.this, paramString1, 0, paramAnonymousArrayOfByte, 1);
          return;
        }
        catch (JSONException paramAnonymousArrayOfXh) {}
      }
    });
  }
  
  public final void a(boolean paramBoolean)
  {
    if ((asa.H(this) != 2) && (((KeyguardManager)getSystemService("keyguard")).inKeyguardRestrictedInputMode())) {}
    label95:
    do
    {
      do
      {
        return;
        if ((this.I != null) && ((this.I.f == 3) || (this.I.f == 2)))
        {
          if (MyService.a != null)
          {
            if (!MyService.a.isPlaying()) {
              break label95;
            }
            MyService.a.pause();
            if (this.aJ != null) {
              this.aJ.b(true);
            }
          }
          for (;;)
          {
            d();
            return;
            MyService.a.start();
            if (this.aJ != null) {
              this.aJ.b(false);
            }
          }
        }
        if (this.b != 1) {
          break;
        }
        a(false, this.H, 1);
        if (this.aJ != null) {
          this.aJ.f("javascript:pauseVideo()");
        }
      } while (this.aJ == null);
      this.aJ.b(true);
      return;
    } while (((this.b != 2) && (this.b != 5)) || (!arx.a(this)));
    if (this.aJ != null)
    {
      if ((asa.H(this) != 1) || (this.aJ.b.a()) || (!paramBoolean)) {
        break label271;
      }
      MixerBoxUtils.f(this, "Log071301");
      this.aJ.a(true, 2, this.ae);
    }
    for (;;)
    {
      a(true, this.H, 1);
      if (this.aJ == null) {
        break;
      }
      this.aJ.b(false);
      return;
      label271:
      MixerBoxUtils.f(this, "Log071302");
      this.aJ.f("javascript:playVideo()");
    }
  }
  
  public final void a(boolean paramBoolean, int paramInt)
  {
    if ((asa.H(this) != 2) && (((KeyguardManager)getSystemService("keyguard")).inKeyguardRestrictedInputMode())) {}
    label24:
    do
    {
      int i1;
      do
      {
        do
        {
          break label24;
          do
          {
            return;
          } while (this.F == null);
          if (e != 1) {
            break;
          }
        } while (this.F.size() == 0);
        this.G = ((this.G + 1) % this.F.size());
        if (!f) {
          break;
        }
        i1 = h(this.G);
      } while (i1 == -1);
      int i2 = this.F.size();
      this.I = ((apn)this.F.get(i1 % i2));
      a(paramBoolean, paramInt, false);
      return;
      this.I = ((apn)this.F.get(this.G));
      a(paramBoolean, paramInt, false);
      return;
    } while (this.F.size() == 0);
    if ((this.G >= this.F.size()) || (this.G < 0)) {
      this.G = 0;
    }
    this.I = ((apn)this.F.get(this.G));
    a(paramBoolean, paramInt, false);
  }
  
  /* Error */
  public final void a(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 2402	mbinc12/mb32b/MainPage:ax	Z
    //   4: ifne +4 -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   12: ifnull +76 -> 88
    //   15: iload_1
    //   16: ifne +72 -> 88
    //   19: aload_0
    //   20: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   23: getfield 780	apm:a	Ljava/lang/String;
    //   26: astore 5
    //   28: iconst_m1
    //   29: istore 4
    //   31: aload 5
    //   33: invokevirtual 2405	java/lang/String:hashCode	()I
    //   36: lookupswitch	default:+28->64, -1015893492:+560->596, 106559616:+577->613
    //   64: iload 4
    //   66: tableswitch	default:+22->88, 0:+564->630, 1:+581->647
    //   88: aload_0
    //   89: getfield 1550	mbinc12/mb32b/MainPage:bg	Landroid/widget/RelativeLayout;
    //   92: bipush 8
    //   94: invokevirtual 582	android/widget/RelativeLayout:setVisibility	(I)V
    //   97: aload_0
    //   98: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   101: ifnull +61 -> 162
    //   104: aload_0
    //   105: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   108: astore 5
    //   110: aload_0
    //   111: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   114: astore 6
    //   116: aload 5
    //   118: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   121: aload 6
    //   123: putfield 2407	mbinc12/mb32b/services/WindowPlayerService:k	Lapm;
    //   126: aload 5
    //   128: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   131: getfield 2407	mbinc12/mb32b/services/WindowPlayerService:k	Lapm;
    //   134: ifnonnull +530 -> 664
    //   137: aload 5
    //   139: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   142: getfield 2409	mbinc12/mb32b/services/WindowPlayerService:ab	Landroid/widget/ImageView;
    //   145: bipush 8
    //   147: invokevirtual 2410	android/widget/ImageView:setVisibility	(I)V
    //   150: aload_0
    //   151: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   154: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   157: aload_0
    //   158: invokestatic 2413	mbinc12/mb32b/services/WindowPlayerService:a	(Lmbinc12/mb32b/services/WindowPlayerService;Lmbinc12/mb32b/services/WindowPlayerService$r;)Lmbinc12/mb32b/services/WindowPlayerService$r;
    //   161: pop
    //   162: aload_0
    //   163: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   166: ifnull +780 -> 946
    //   169: aload_0
    //   170: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   173: getfield 1892	apn:f	I
    //   176: iconst_3
    //   177: if_icmpeq +14 -> 191
    //   180: aload_0
    //   181: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   184: getfield 1892	apn:f	I
    //   187: iconst_2
    //   188: if_icmpne +758 -> 946
    //   191: aload_0
    //   192: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   195: ifnull +13 -> 208
    //   198: aload_0
    //   199: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   202: ldc_w 1687
    //   205: invokevirtual 1689	mbinc12/mb32b/services/WindowPlayerService$b:f	(Ljava/lang/String;)V
    //   208: aload_0
    //   209: bipush 10
    //   211: putfield 1593	mbinc12/mb32b/MainPage:az	I
    //   214: iconst_0
    //   215: putstatic 1895	mbinc12/mb32b/services/ScreenReceiver:b	Z
    //   218: aload_0
    //   219: iconst_0
    //   220: putfield 2415	mbinc12/mb32b/MainPage:aU	I
    //   223: aload_0
    //   224: iconst_0
    //   225: putfield 2417	mbinc12/mb32b/MainPage:aW	Z
    //   228: aload_0
    //   229: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   232: ifnull +32 -> 264
    //   235: aload_0
    //   236: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   239: iload_1
    //   240: iload_2
    //   241: iconst_1
    //   242: invokevirtual 1020	mbinc12/mb32b/services/WindowPlayerService$b:a	(ZIZ)V
    //   245: aload_0
    //   246: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   249: aload_0
    //   250: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   253: invokevirtual 2420	mbinc12/mb32b/services/WindowPlayerService$b:a	(Lapn;)V
    //   256: aload_0
    //   257: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   260: iconst_0
    //   261: invokevirtual 2422	mbinc12/mb32b/services/WindowPlayerService$b:i	(Z)V
    //   264: getstatic 378	mbinc12/mb32b/MainPage:j	I
    //   267: ifne +18 -> 285
    //   270: aload_0
    //   271: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   274: ifnull +11 -> 285
    //   277: aload_0
    //   278: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   281: iconst_0
    //   282: invokevirtual 2424	mbinc12/mb32b/services/WindowPlayerService$b:e	(Z)V
    //   285: aload_0
    //   286: iconst_1
    //   287: putfield 470	mbinc12/mb32b/MainPage:aN	Z
    //   290: aload_0
    //   291: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   294: ifnull +25 -> 319
    //   297: aload_0
    //   298: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   301: iconst_0
    //   302: invokevirtual 1415	mbinc12/mb32b/services/WindowPlayerService$b:b	(Z)V
    //   305: aload_0
    //   306: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   309: aload_0
    //   310: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   313: getfield 1324	apn:b	Ljava/lang/String;
    //   316: invokevirtual 2425	mbinc12/mb32b/services/WindowPlayerService$b:d	(Ljava/lang/String;)V
    //   319: aload_0
    //   320: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   323: ifnull +19 -> 342
    //   326: aload_0
    //   327: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   330: iconst_0
    //   331: invokevirtual 2426	mbinc12/mb32b/services/WindowPlayerService$b:c	(Z)V
    //   334: aload_0
    //   335: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   338: iconst_0
    //   339: invokevirtual 2427	mbinc12/mb32b/services/WindowPlayerService$b:d	(Z)V
    //   342: aload_0
    //   343: iconst_1
    //   344: putfield 2429	mbinc12/mb32b/MainPage:ad	Z
    //   347: aload_0
    //   348: invokevirtual 1417	mbinc12/mb32b/MainPage:d	()V
    //   351: aload_0
    //   352: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   355: getfield 1598	apn:a	Ljava/lang/String;
    //   358: ifnull +338 -> 696
    //   361: aload_0
    //   362: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   365: ifnull +331 -> 696
    //   368: aload_0
    //   369: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   372: getfield 1892	apn:f	I
    //   375: iconst_2
    //   376: if_icmpne +320 -> 696
    //   379: aload_0
    //   380: aload_0
    //   381: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   384: getfield 1598	apn:a	Ljava/lang/String;
    //   387: invokespecial 1519	mbinc12/mb32b/MainPage:q	(Ljava/lang/String;)Ljava/lang/String;
    //   390: astore 5
    //   392: aload 5
    //   394: invokevirtual 740	java/lang/String:length	()I
    //   397: ifle +282 -> 679
    //   400: aload_0
    //   401: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   404: ifnull +12 -> 416
    //   407: aload_0
    //   408: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   411: aload 5
    //   413: invokevirtual 2431	mbinc12/mb32b/services/WindowPlayerService$b:e	(Ljava/lang/String;)V
    //   416: aload_0
    //   417: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   420: ifnull +11 -> 431
    //   423: aload_0
    //   424: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   427: iconst_0
    //   428: invokevirtual 2433	mbinc12/mb32b/services/WindowPlayerService$b:g	(Z)V
    //   431: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   434: ifnull +9 -> 443
    //   437: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   440: invokevirtual 2386	android/media/MediaPlayer:pause	()V
    //   443: aload_0
    //   444: aload_0
    //   445: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   448: getfield 1333	apn:c	Ljava/lang/String;
    //   451: invokestatic 2436	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   454: invokevirtual 1951	java/lang/Integer:intValue	()I
    //   457: invokevirtual 2438	mbinc12/mb32b/MainPage:a	(I)V
    //   460: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   463: ifnull +9 -> 472
    //   466: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   469: invokevirtual 2441	android/media/MediaPlayer:reset	()V
    //   472: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   475: ifnull +9 -> 484
    //   478: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   481: invokevirtual 2444	android/media/MediaPlayer:release	()V
    //   484: aconst_null
    //   485: putstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   488: new 2381	android/media/MediaPlayer
    //   491: dup
    //   492: invokespecial 2445	android/media/MediaPlayer:<init>	()V
    //   495: putstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   498: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   501: aload_0
    //   502: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   505: getfield 1595	apn:d	Ljava/lang/String;
    //   508: invokevirtual 2448	android/media/MediaPlayer:setDataSource	(Ljava/lang/String;)V
    //   511: aload_0
    //   512: new 1350	android/content/Intent
    //   515: dup
    //   516: aload_0
    //   517: ldc_w 2376
    //   520: invokespecial 2451	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   523: invokevirtual 2455	mbinc12/mb32b/MainPage:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   526: pop
    //   527: new 1552	java/util/HashMap
    //   530: dup
    //   531: invokespecial 1553	java/util/HashMap:<init>	()V
    //   534: astore 5
    //   536: aload 5
    //   538: ldc_w 2457
    //   541: ldc_w 2459
    //   544: invokeinterface 1560 3 0
    //   549: pop
    //   550: ldc_w 2461
    //   553: aload 5
    //   555: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   558: aload_0
    //   559: getfield 413	mbinc12/mb32b/MainPage:aD	Z
    //   562: ifeq -555 -> 7
    //   565: new 609	org/json/JSONObject
    //   568: dup
    //   569: invokespecial 610	org/json/JSONObject:<init>	()V
    //   572: astore 5
    //   574: aload 5
    //   576: ldc_w 2463
    //   579: ldc_w 2465
    //   582: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   585: pop
    //   586: aload_0
    //   587: ldc_w 2467
    //   590: aload 5
    //   592: invokestatic 623	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   595: return
    //   596: aload 5
    //   598: ldc_w 2300
    //   601: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   604: ifeq -540 -> 64
    //   607: iconst_0
    //   608: istore 4
    //   610: goto -546 -> 64
    //   613: aload 5
    //   615: ldc_w 2302
    //   618: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   621: ifeq -557 -> 64
    //   624: iconst_1
    //   625: istore 4
    //   627: goto -563 -> 64
    //   630: aload_0
    //   631: aload_0
    //   632: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   635: getfield 2468	apm:c	Ljava/lang/String;
    //   638: ldc_w 2470
    //   641: invokevirtual 1609	mbinc12/mb32b/MainPage:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   644: goto -556 -> 88
    //   647: aload_0
    //   648: aload_0
    //   649: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   652: getfield 2295	apm:b	Ljava/lang/String;
    //   655: ldc_w 2470
    //   658: invokevirtual 1609	mbinc12/mb32b/MainPage:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   661: goto -573 -> 88
    //   664: aload 5
    //   666: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   669: getfield 2409	mbinc12/mb32b/services/WindowPlayerService:ab	Landroid/widget/ImageView;
    //   672: iconst_0
    //   673: invokevirtual 2410	android/widget/ImageView:setVisibility	(I)V
    //   676: goto -526 -> 150
    //   679: aload_0
    //   680: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   683: ifnull -267 -> 416
    //   686: aload_0
    //   687: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   690: invokevirtual 2471	mbinc12/mb32b/services/WindowPlayerService$b:f	()V
    //   693: goto -277 -> 416
    //   696: aload_0
    //   697: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   700: getfield 1892	apn:f	I
    //   703: iconst_3
    //   704: if_icmpne -146 -> 558
    //   707: aload_0
    //   708: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   711: ifnull +10 -> 721
    //   714: aload_0
    //   715: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   718: invokevirtual 2471	mbinc12/mb32b/services/WindowPlayerService$b:f	()V
    //   721: aload_0
    //   722: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   725: getfield 2472	apn:g	Ljava/lang/String;
    //   728: invokevirtual 740	java/lang/String:length	()I
    //   731: ifle +24 -> 755
    //   734: aload_0
    //   735: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   738: ifnull +17 -> 755
    //   741: aload_0
    //   742: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   745: aload_0
    //   746: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   749: getfield 2472	apn:g	Ljava/lang/String;
    //   752: invokevirtual 2431	mbinc12/mb32b/services/WindowPlayerService$b:e	(Ljava/lang/String;)V
    //   755: aload_0
    //   756: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   759: ifnull +11 -> 770
    //   762: aload_0
    //   763: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   766: iconst_1
    //   767: invokevirtual 2433	mbinc12/mb32b/services/WindowPlayerService$b:g	(Z)V
    //   770: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   773: ifnull +9 -> 782
    //   776: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   779: invokevirtual 2386	android/media/MediaPlayer:pause	()V
    //   782: aload_0
    //   783: aload_0
    //   784: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   787: getfield 1333	apn:c	Ljava/lang/String;
    //   790: invokestatic 2436	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   793: invokevirtual 1951	java/lang/Integer:intValue	()I
    //   796: invokevirtual 2438	mbinc12/mb32b/MainPage:a	(I)V
    //   799: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   802: ifnull +9 -> 811
    //   805: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   808: invokevirtual 2441	android/media/MediaPlayer:reset	()V
    //   811: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   814: ifnull +9 -> 823
    //   817: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   820: invokevirtual 2444	android/media/MediaPlayer:release	()V
    //   823: aconst_null
    //   824: putstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   827: new 2381	android/media/MediaPlayer
    //   830: dup
    //   831: invokespecial 2445	android/media/MediaPlayer:<init>	()V
    //   834: putstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   837: new 1330	java/lang/StringBuilder
    //   840: dup
    //   841: ldc_w 2474
    //   844: invokespecial 1447	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   847: aload_0
    //   848: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   851: getfield 1595	apn:d	Ljava/lang/String;
    //   854: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   857: ldc_w 2476
    //   860: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   863: getstatic 2479	ary:b	Ljava/lang/String;
    //   866: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   869: invokevirtual 1342	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   872: astore 5
    //   874: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   877: aload 5
    //   879: invokevirtual 2448	android/media/MediaPlayer:setDataSource	(Ljava/lang/String;)V
    //   882: aload_0
    //   883: new 1350	android/content/Intent
    //   886: dup
    //   887: aload_0
    //   888: ldc_w 2376
    //   891: invokespecial 2451	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   894: invokevirtual 2455	mbinc12/mb32b/MainPage:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   897: pop
    //   898: new 1552	java/util/HashMap
    //   901: dup
    //   902: invokespecial 1553	java/util/HashMap:<init>	()V
    //   905: astore 5
    //   907: aload 5
    //   909: ldc_w 2457
    //   912: ldc_w 2481
    //   915: invokeinterface 1560 3 0
    //   920: pop
    //   921: ldc_w 2461
    //   924: aload 5
    //   926: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   929: new 2352	java/lang/Thread
    //   932: dup
    //   933: aload_0
    //   934: getfield 473	mbinc12/mb32b/MainPage:bs	Ljava/lang/Runnable;
    //   937: invokespecial 2358	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   940: invokevirtual 2361	java/lang/Thread:start	()V
    //   943: goto -385 -> 558
    //   946: aload_0
    //   947: iconst_0
    //   948: putfield 405	mbinc12/mb32b/MainPage:at	Z
    //   951: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   954: ifnull +9 -> 963
    //   957: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   960: invokevirtual 2386	android/media/MediaPlayer:pause	()V
    //   963: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   966: ifnull +9 -> 975
    //   969: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   972: invokevirtual 2441	android/media/MediaPlayer:reset	()V
    //   975: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   978: ifnull +9 -> 987
    //   981: getstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   984: invokevirtual 2444	android/media/MediaPlayer:release	()V
    //   987: aconst_null
    //   988: putstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   991: new 2381	android/media/MediaPlayer
    //   994: dup
    //   995: invokespecial 2445	android/media/MediaPlayer:<init>	()V
    //   998: putstatic 2379	mbinc12/mb32b/services/MyService:a	Landroid/media/MediaPlayer;
    //   1001: aload_0
    //   1002: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1005: ifnull +11 -> 1016
    //   1008: aload_0
    //   1009: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1012: iconst_0
    //   1013: invokevirtual 2433	mbinc12/mb32b/services/WindowPlayerService$b:g	(Z)V
    //   1016: aload_0
    //   1017: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1020: ifnull +13 -> 1033
    //   1023: aload_0
    //   1024: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1027: getfield 1595	apn:d	Ljava/lang/String;
    //   1030: ifnonnull +22 -> 1052
    //   1033: aload_0
    //   1034: aload_0
    //   1035: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   1038: ldc_w 651
    //   1041: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1044: iconst_1
    //   1045: iconst_0
    //   1046: newarray boolean
    //   1048: invokestatic 660	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;I[Z)V
    //   1051: return
    //   1052: aload_0
    //   1053: invokestatic 923	arx:a	(Landroid/content/Context;)Z
    //   1056: ifne +20 -> 1076
    //   1059: iload_3
    //   1060: ifne +16 -> 1076
    //   1063: aload_0
    //   1064: iconst_1
    //   1065: putfield 1670	mbinc12/mb32b/MainPage:ar	Z
    //   1068: aload_0
    //   1069: invokestatic 2486	aqt:a	(Landroid/app/Activity;)Landroid/app/AlertDialog;
    //   1072: invokevirtual 626	android/app/Dialog:show	()V
    //   1075: return
    //   1076: getstatic 1005	mbinc12/mb32b/MainPage:aq	Z
    //   1079: ifne +1025 -> 2104
    //   1082: iload_3
    //   1083: ifne +8 -> 1091
    //   1086: aload_0
    //   1087: iconst_1
    //   1088: putfield 1670	mbinc12/mb32b/MainPage:ar	Z
    //   1091: aload_0
    //   1092: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1095: ifnull +11 -> 1106
    //   1098: aload_0
    //   1099: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1102: iconst_1
    //   1103: invokevirtual 2422	mbinc12/mb32b/services/WindowPlayerService$b:i	(Z)V
    //   1106: aload_0
    //   1107: getfield 2488	mbinc12/mb32b/MainPage:c	Landroid/widget/TextView;
    //   1110: invokevirtual 2489	android/widget/TextView:getVisibility	()I
    //   1113: ifne +12 -> 1125
    //   1116: aload_0
    //   1117: getfield 2488	mbinc12/mb32b/MainPage:c	Landroid/widget/TextView;
    //   1120: bipush 8
    //   1122: invokevirtual 583	android/widget/TextView:setVisibility	(I)V
    //   1125: getstatic 1005	mbinc12/mb32b/MainPage:aq	Z
    //   1128: ifeq +30 -> 1158
    //   1131: aload_0
    //   1132: getfield 1007	mbinc12/mb32b/MainPage:ap	Z
    //   1135: ifeq +23 -> 1158
    //   1138: aload_0
    //   1139: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1142: ifnull +16 -> 1158
    //   1145: aload_0
    //   1146: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1149: iconst_0
    //   1150: invokevirtual 2422	mbinc12/mb32b/services/WindowPlayerService$b:i	(Z)V
    //   1153: aload_0
    //   1154: iconst_0
    //   1155: putfield 1670	mbinc12/mb32b/MainPage:ar	Z
    //   1158: aload_0
    //   1159: getfield 399	mbinc12/mb32b/MainPage:ak	I
    //   1162: aload_0
    //   1163: getfield 930	mbinc12/mb32b/MainPage:F	Ljava/util/ArrayList;
    //   1166: invokevirtual 933	java/util/ArrayList:size	()I
    //   1169: iconst_2
    //   1170: iadd
    //   1171: if_icmple +7 -> 1178
    //   1174: iload_1
    //   1175: ifeq -1168 -> 7
    //   1178: aload_0
    //   1179: invokestatic 1890	asa:H	(Landroid/content/Context;)I
    //   1182: istore 4
    //   1184: iload 4
    //   1186: iconst_2
    //   1187: if_icmpeq +19 -> 1206
    //   1190: aload_0
    //   1191: ldc_w 2369
    //   1194: invokevirtual 1264	mbinc12/mb32b/MainPage:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1197: checkcast 2371	android/app/KeyguardManager
    //   1200: invokevirtual 2374	android/app/KeyguardManager:inKeyguardRestrictedInputMode	()Z
    //   1203: ifne -1196 -> 7
    //   1206: iload 4
    //   1208: iconst_1
    //   1209: if_icmpne +188 -> 1397
    //   1212: iload_1
    //   1213: ifeq +184 -> 1397
    //   1216: aload_0
    //   1217: invokestatic 2491	asa:al	(Landroid/content/Context;)Z
    //   1220: ifeq +918 -> 2138
    //   1223: new 508	android/app/Dialog
    //   1226: dup
    //   1227: aload_0
    //   1228: invokespecial 511	android/app/Dialog:<init>	(Landroid/content/Context;)V
    //   1231: astore 5
    //   1233: aload 5
    //   1235: ldc_w 2492
    //   1238: invokevirtual 516	android/app/Dialog:setContentView	(I)V
    //   1241: aload_0
    //   1242: ldc_w 2493
    //   1245: aload 5
    //   1247: ldc_w 539
    //   1250: invokevirtual 535	android/app/Dialog:findViewById	(I)Landroid/view/View;
    //   1253: checkcast 541	android/widget/ImageView
    //   1256: bipush 8
    //   1258: invokestatic 956	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;ILandroid/widget/ImageView;I)V
    //   1261: aload 5
    //   1263: ldc_w 2494
    //   1266: invokevirtual 535	android/app/Dialog:findViewById	(I)Landroid/view/View;
    //   1269: checkcast 545	android/widget/TextView
    //   1272: astore 6
    //   1274: aload 6
    //   1276: ldc_w 374
    //   1279: invokevirtual 557	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   1282: aload 6
    //   1284: aload_0
    //   1285: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   1288: ldc_w 2495
    //   1291: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1294: invokevirtual 2497	android/widget/TextView:append	(Ljava/lang/CharSequence;)V
    //   1297: aload 6
    //   1299: aload_0
    //   1300: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   1303: ldc_w 2498
    //   1306: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1309: invokevirtual 2497	android/widget/TextView:append	(Ljava/lang/CharSequence;)V
    //   1312: aload 6
    //   1314: aload_0
    //   1315: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   1318: ldc_w 2499
    //   1321: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1324: invokevirtual 2497	android/widget/TextView:append	(Ljava/lang/CharSequence;)V
    //   1327: aload 5
    //   1329: ldc_w 2500
    //   1332: invokevirtual 535	android/app/Dialog:findViewById	(I)Landroid/view/View;
    //   1335: checkcast 2502	android/widget/Button
    //   1338: new 34	mbinc12/mb32b/MainPage$20
    //   1341: dup
    //   1342: aload_0
    //   1343: aload 5
    //   1345: invokespecial 2503	mbinc12/mb32b/MainPage$20:<init>	(Lmbinc12/mb32b/MainPage;Landroid/app/Dialog;)V
    //   1348: invokevirtual 2504	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1351: aload_0
    //   1352: invokevirtual 607	mbinc12/mb32b/MainPage:isFinishing	()Z
    //   1355: ifne +38 -> 1393
    //   1358: new 609	org/json/JSONObject
    //   1361: dup
    //   1362: invokespecial 610	org/json/JSONObject:<init>	()V
    //   1365: astore 6
    //   1367: aload 6
    //   1369: ldc_w 2506
    //   1372: ldc_w 2508
    //   1375: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1378: pop
    //   1379: aload_0
    //   1380: ldc_w 2510
    //   1383: aload 6
    //   1385: invokestatic 623	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   1388: aload 5
    //   1390: invokevirtual 626	android/app/Dialog:show	()V
    //   1393: aload_0
    //   1394: invokestatic 2512	asa:ak	(Landroid/content/Context;)V
    //   1397: aload_0
    //   1398: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1401: ifnull +27 -> 1428
    //   1404: aload_0
    //   1405: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1408: iload_1
    //   1409: iload_2
    //   1410: aload_0
    //   1411: getfield 1017	mbinc12/mb32b/MainPage:ae	Z
    //   1414: invokevirtual 1020	mbinc12/mb32b/services/WindowPlayerService$b:a	(ZIZ)V
    //   1417: aload_0
    //   1418: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1421: aload_0
    //   1422: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1425: invokevirtual 2420	mbinc12/mb32b/services/WindowPlayerService$b:a	(Lapn;)V
    //   1428: aload_0
    //   1429: iconst_1
    //   1430: iconst_0
    //   1431: iconst_0
    //   1432: invokespecial 1321	mbinc12/mb32b/MainPage:a	(ZII)V
    //   1435: aload_0
    //   1436: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1439: ifnull +57 -> 1496
    //   1442: aload_0
    //   1443: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1446: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   1449: getfield 2514	mbinc12/mb32b/services/WindowPlayerService:S	Landroid/widget/ImageView;
    //   1452: bipush 8
    //   1454: invokevirtual 2410	android/widget/ImageView:setVisibility	(I)V
    //   1457: aload_0
    //   1458: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1461: iconst_1
    //   1462: invokevirtual 2426	mbinc12/mb32b/services/WindowPlayerService$b:c	(Z)V
    //   1465: aload_0
    //   1466: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   1469: ifnull +715 -> 2184
    //   1472: aload_0
    //   1473: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   1476: getfield 780	apm:a	Ljava/lang/String;
    //   1479: ldc_w 1597
    //   1482: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1485: ifeq +699 -> 2184
    //   1488: aload_0
    //   1489: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1492: iconst_0
    //   1493: invokevirtual 2427	mbinc12/mb32b/services/WindowPlayerService$b:d	(Z)V
    //   1496: aload_0
    //   1497: iconst_1
    //   1498: putfield 2429	mbinc12/mb32b/MainPage:ad	Z
    //   1501: aload_0
    //   1502: iconst_0
    //   1503: putfield 2415	mbinc12/mb32b/MainPage:aU	I
    //   1506: aload_0
    //   1507: iconst_0
    //   1508: putfield 2417	mbinc12/mb32b/MainPage:aW	Z
    //   1511: aload_0
    //   1512: invokevirtual 1417	mbinc12/mb32b/MainPage:d	()V
    //   1515: new 1552	java/util/HashMap
    //   1518: dup
    //   1519: invokespecial 1553	java/util/HashMap:<init>	()V
    //   1522: astore 7
    //   1524: aload 7
    //   1526: ldc_w 2515
    //   1529: aload_0
    //   1530: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1533: getfield 1595	apn:d	Ljava/lang/String;
    //   1536: invokeinterface 1560 3 0
    //   1541: pop
    //   1542: aload 7
    //   1544: ldc_w 2457
    //   1547: ldc_w 2517
    //   1550: invokeinterface 1560 3 0
    //   1555: pop
    //   1556: invokestatic 2523	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1559: invokevirtual 2526	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   1562: astore 6
    //   1564: aload 6
    //   1566: ldc_w 2528
    //   1569: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1572: ifne +25 -> 1597
    //   1575: aload 6
    //   1577: ldc_w 2530
    //   1580: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1583: ifne +14 -> 1597
    //   1586: aload 6
    //   1588: ldc_w 2532
    //   1591: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1594: ifeq +59 -> 1653
    //   1597: aload 6
    //   1599: astore 5
    //   1601: aload 6
    //   1603: ldc_w 2532
    //   1606: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1609: ifeq +21 -> 1630
    //   1612: aload_0
    //   1613: invokestatic 2533	mbinc12/mb32b/utils/MixerBoxUtils:f	(Landroid/content/Context;)Ljava/lang/String;
    //   1616: ldc_w 2535
    //   1619: invokevirtual 886	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1622: ifeq +573 -> 2195
    //   1625: ldc_w 2537
    //   1628: astore 5
    //   1630: new 1330	java/lang/StringBuilder
    //   1633: dup
    //   1634: ldc_w 2539
    //   1637: invokespecial 1447	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1640: aload 5
    //   1642: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1645: invokevirtual 1342	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1648: aload 7
    //   1650: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   1653: ldc_w 2461
    //   1656: aload 7
    //   1658: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   1661: aload_0
    //   1662: iconst_0
    //   1663: putfield 1413	mbinc12/mb32b/MainPage:H	I
    //   1666: getstatic 378	mbinc12/mb32b/MainPage:j	I
    //   1669: iconst_2
    //   1670: if_icmpne +533 -> 2203
    //   1673: aload_0
    //   1674: getfield 1017	mbinc12/mb32b/MainPage:ae	Z
    //   1677: ifne +526 -> 2203
    //   1680: aload_0
    //   1681: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1684: ifnull +11 -> 1695
    //   1687: aload_0
    //   1688: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1691: iconst_1
    //   1692: invokevirtual 1672	mbinc12/mb32b/services/WindowPlayerService$b:h	(Z)V
    //   1695: aload_0
    //   1696: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1699: ifnull +42 -> 1741
    //   1702: aload_0
    //   1703: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1706: iconst_0
    //   1707: invokevirtual 1415	mbinc12/mb32b/services/WindowPlayerService$b:b	(Z)V
    //   1710: aload_0
    //   1711: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1714: aload_0
    //   1715: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1718: getfield 1333	apn:c	Ljava/lang/String;
    //   1721: invokestatic 2108	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;)Ljava/lang/String;
    //   1724: invokevirtual 2110	mbinc12/mb32b/services/WindowPlayerService$b:c	(Ljava/lang/String;)V
    //   1727: aload_0
    //   1728: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1731: aload_0
    //   1732: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1735: getfield 1324	apn:b	Ljava/lang/String;
    //   1738: invokevirtual 2425	mbinc12/mb32b/services/WindowPlayerService$b:d	(Ljava/lang/String;)V
    //   1741: aload_0
    //   1742: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1745: ifnull +192 -> 1937
    //   1748: aload_0
    //   1749: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1752: aload_0
    //   1753: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1756: getfield 1333	apn:c	Ljava/lang/String;
    //   1759: invokestatic 2436	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   1762: invokevirtual 1951	java/lang/Integer:intValue	()I
    //   1765: invokevirtual 2112	mbinc12/mb32b/services/WindowPlayerService$b:c	(I)V
    //   1768: aload_0
    //   1769: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1772: getfield 832	mbinc12/mb32b/services/WindowPlayerService$b:b	Lmbinc12/mb32b/services/WindowPlayerService;
    //   1775: getfield 2541	mbinc12/mb32b/services/WindowPlayerService:av	Z
    //   1778: ifeq +464 -> 2242
    //   1781: aload_0
    //   1782: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1785: getfield 1333	apn:c	Ljava/lang/String;
    //   1788: invokestatic 817	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1791: istore_2
    //   1792: aload_0
    //   1793: iload_2
    //   1794: i2f
    //   1795: ldc_w 2542
    //   1798: fmul
    //   1799: f2i
    //   1800: putfield 2544	mbinc12/mb32b/MainPage:bh	I
    //   1803: aload_0
    //   1804: aload_0
    //   1805: getfield 2544	mbinc12/mb32b/MainPage:bh	I
    //   1808: bipush 20
    //   1810: iadd
    //   1811: putfield 2546	mbinc12/mb32b/MainPage:bi	I
    //   1814: aload_0
    //   1815: getfield 2546	mbinc12/mb32b/MainPage:bi	I
    //   1818: iload_2
    //   1819: if_icmple +8 -> 1827
    //   1822: aload_0
    //   1823: iload_2
    //   1824: putfield 2546	mbinc12/mb32b/MainPage:bi	I
    //   1827: aload_0
    //   1828: iconst_1
    //   1829: putfield 417	mbinc12/mb32b/MainPage:bj	Z
    //   1832: aload_0
    //   1833: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   1836: new 1330	java/lang/StringBuilder
    //   1839: dup
    //   1840: ldc_w 2548
    //   1843: invokespecial 1447	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1846: aload_0
    //   1847: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1850: getfield 1595	apn:d	Ljava/lang/String;
    //   1853: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1856: ldc_w 2550
    //   1859: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1862: aload_0
    //   1863: getfield 2544	mbinc12/mb32b/MainPage:bh	I
    //   1866: invokevirtual 1375	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1869: ldc_w 2552
    //   1872: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1875: iload_2
    //   1876: invokevirtual 1375	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1879: ldc_w 2554
    //   1882: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1885: invokevirtual 1342	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1888: invokevirtual 1689	mbinc12/mb32b/services/WindowPlayerService$b:f	(Ljava/lang/String;)V
    //   1891: aload_0
    //   1892: new 1330	java/lang/StringBuilder
    //   1895: dup
    //   1896: ldc_w 2556
    //   1899: invokespecial 1447	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1902: aload_0
    //   1903: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   1906: ldc_w 901
    //   1909: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   1912: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1915: ldc_w 2558
    //   1918: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1921: aload_0
    //   1922: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1925: getfield 1324	apn:b	Ljava/lang/String;
    //   1928: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1931: invokevirtual 1342	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1934: invokestatic 2560	mbinc12/mb32b/utils/MixerBoxUtils:h	(Landroid/content/Context;Ljava/lang/String;)V
    //   1937: aload_0
    //   1938: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   1941: invokestatic 2563	mbinc12/mb32b/utils/MixerBoxUtils:a	(Lapm;)Z
    //   1944: ifne +53 -> 1997
    //   1947: aload_0
    //   1948: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   1951: ifnonnull +14 -> 1965
    //   1954: aload_0
    //   1955: getfield 930	mbinc12/mb32b/MainPage:F	Ljava/util/ArrayList;
    //   1958: invokevirtual 933	java/util/ArrayList:size	()I
    //   1961: iconst_1
    //   1962: if_icmpgt +35 -> 1997
    //   1965: aload_0
    //   1966: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   1969: invokestatic 2564	mbinc12/mb32b/utils/MixerBoxUtils:a	(Lapn;)V
    //   1972: aload_0
    //   1973: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   1976: ifnonnull +356 -> 2332
    //   1979: aload_0
    //   1980: aconst_null
    //   1981: invokestatic 2566	asa:F	(Landroid/content/Context;Ljava/lang/String;)V
    //   1984: aload_0
    //   1985: aconst_null
    //   1986: invokestatic 2568	asa:G	(Landroid/content/Context;Ljava/lang/String;)V
    //   1989: aconst_null
    //   1990: aconst_null
    //   1991: invokestatic 2569	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   1994: invokestatic 2570	mbinc12/mb32b/utils/MixerBoxUtils:a	()V
    //   1997: aload_0
    //   1998: getfield 419	mbinc12/mb32b/MainPage:bk	Lasn;
    //   2001: ifnull +17 -> 2018
    //   2004: aload_0
    //   2005: invokevirtual 2572	mbinc12/mb32b/MainPage:F	()Z
    //   2008: ifeq +10 -> 2018
    //   2011: aload_0
    //   2012: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   2015: ifnonnull +359 -> 2374
    //   2018: new 2352	java/lang/Thread
    //   2021: dup
    //   2022: aload_0
    //   2023: getfield 473	mbinc12/mb32b/MainPage:bs	Ljava/lang/Runnable;
    //   2026: invokespecial 2358	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   2029: invokevirtual 2361	java/lang/Thread:start	()V
    //   2032: aload_0
    //   2033: getfield 413	mbinc12/mb32b/MainPage:aD	Z
    //   2036: ifeq -2029 -> 7
    //   2039: new 609	org/json/JSONObject
    //   2042: dup
    //   2043: invokespecial 610	org/json/JSONObject:<init>	()V
    //   2046: astore 5
    //   2048: aload 5
    //   2050: ldc_w 2574
    //   2053: aload_0
    //   2054: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   2057: getfield 1595	apn:d	Ljava/lang/String;
    //   2060: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2063: pop
    //   2064: aload_0
    //   2065: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   2068: getfield 780	apm:a	Ljava/lang/String;
    //   2071: astore 6
    //   2073: aload_0
    //   2074: aload 6
    //   2076: invokevirtual 2334	mbinc12/mb32b/MainPage:i	(Ljava/lang/String;)Z
    //   2079: ifeq +309 -> 2388
    //   2082: aload 5
    //   2084: ldc_w 2463
    //   2087: ldc_w 2576
    //   2090: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2093: pop
    //   2094: aload_0
    //   2095: ldc_w 2467
    //   2098: aload 5
    //   2100: invokestatic 623	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   2103: return
    //   2104: aload_0
    //   2105: getfield 1007	mbinc12/mb32b/MainPage:ap	Z
    //   2108: ifne -1002 -> 1106
    //   2111: iload_3
    //   2112: ifne +8 -> 2120
    //   2115: aload_0
    //   2116: iconst_1
    //   2117: putfield 1670	mbinc12/mb32b/MainPage:ar	Z
    //   2120: aload_0
    //   2121: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2124: ifnull -1018 -> 1106
    //   2127: aload_0
    //   2128: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2131: iconst_1
    //   2132: invokevirtual 2422	mbinc12/mb32b/services/WindowPlayerService$b:i	(Z)V
    //   2135: goto -1029 -> 1106
    //   2138: aload_0
    //   2139: getfield 423	mbinc12/mb32b/MainPage:aF	Z
    //   2142: ifeq +19 -> 2161
    //   2145: aload_0
    //   2146: invokespecial 1802	mbinc12/mb32b/MainPage:N	()V
    //   2149: aload_0
    //   2150: invokestatic 2578	asa:ao	(Landroid/content/Context;)V
    //   2153: aload_0
    //   2154: iconst_0
    //   2155: putfield 423	mbinc12/mb32b/MainPage:aF	Z
    //   2158: goto -761 -> 1397
    //   2161: aload_0
    //   2162: getfield 425	mbinc12/mb32b/MainPage:aG	Z
    //   2165: ifeq -768 -> 1397
    //   2168: aload_0
    //   2169: invokespecial 1802	mbinc12/mb32b/MainPage:N	()V
    //   2172: aload_0
    //   2173: invokestatic 2580	asa:aq	(Landroid/content/Context;)V
    //   2176: aload_0
    //   2177: iconst_0
    //   2178: putfield 425	mbinc12/mb32b/MainPage:aG	Z
    //   2181: goto -784 -> 1397
    //   2184: aload_0
    //   2185: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2188: iconst_1
    //   2189: invokevirtual 2427	mbinc12/mb32b/services/WindowPlayerService$b:d	(Z)V
    //   2192: goto -696 -> 1496
    //   2195: ldc_w 2582
    //   2198: astore 5
    //   2200: goto -570 -> 1630
    //   2203: getstatic 378	mbinc12/mb32b/MainPage:j	I
    //   2206: ifne -511 -> 1695
    //   2209: aload_0
    //   2210: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2213: ifnull -518 -> 1695
    //   2216: aload_0
    //   2217: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2220: iconst_1
    //   2221: invokevirtual 2424	mbinc12/mb32b/services/WindowPlayerService$b:e	(Z)V
    //   2224: goto -529 -> 1695
    //   2227: astore 5
    //   2229: aload_0
    //   2230: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2233: sipush 600
    //   2236: invokevirtual 2112	mbinc12/mb32b/services/WindowPlayerService$b:c	(I)V
    //   2239: goto -471 -> 1768
    //   2242: iload_3
    //   2243: ifeq +50 -> 2293
    //   2246: aload_0
    //   2247: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2250: new 1330	java/lang/StringBuilder
    //   2253: dup
    //   2254: ldc_w 2584
    //   2257: invokespecial 1447	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2260: aload_0
    //   2261: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   2264: getfield 1595	apn:d	Ljava/lang/String;
    //   2267: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2270: ldc_w 2586
    //   2273: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2276: invokevirtual 1342	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2279: invokevirtual 1689	mbinc12/mb32b/services/WindowPlayerService$b:f	(Ljava/lang/String;)V
    //   2282: aload_0
    //   2283: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2286: iconst_1
    //   2287: invokevirtual 1415	mbinc12/mb32b/services/WindowPlayerService$b:b	(Z)V
    //   2290: goto -353 -> 1937
    //   2293: aload_0
    //   2294: getfield 452	mbinc12/mb32b/MainPage:aJ	Lmbinc12/mb32b/services/WindowPlayerService$b;
    //   2297: new 1330	java/lang/StringBuilder
    //   2300: dup
    //   2301: ldc_w 2548
    //   2304: invokespecial 1447	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2307: aload_0
    //   2308: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   2311: getfield 1595	apn:d	Ljava/lang/String;
    //   2314: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2317: ldc_w 2586
    //   2320: invokevirtual 1337	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2323: invokevirtual 1342	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2326: invokevirtual 1689	mbinc12/mb32b/services/WindowPlayerService$b:f	(Ljava/lang/String;)V
    //   2329: goto -392 -> 1937
    //   2332: aload_0
    //   2333: aload_0
    //   2334: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   2337: getfield 780	apm:a	Ljava/lang/String;
    //   2340: invokestatic 2566	asa:F	(Landroid/content/Context;Ljava/lang/String;)V
    //   2343: aload_0
    //   2344: aload_0
    //   2345: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   2348: getfield 2295	apm:b	Ljava/lang/String;
    //   2351: invokestatic 2568	asa:G	(Landroid/content/Context;Ljava/lang/String;)V
    //   2354: aload_0
    //   2355: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   2358: getfield 780	apm:a	Ljava/lang/String;
    //   2361: aload_0
    //   2362: getfield 1036	mbinc12/mb32b/MainPage:J	Lapm;
    //   2365: getfield 2295	apm:b	Ljava/lang/String;
    //   2368: invokestatic 2569	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   2371: goto -377 -> 1994
    //   2374: aload_0
    //   2375: getfield 419	mbinc12/mb32b/MainPage:bk	Lasn;
    //   2378: aload_0
    //   2379: getfield 1009	mbinc12/mb32b/MainPage:I	Lapn;
    //   2382: invokevirtual 2589	asn:a	(Lapn;)V
    //   2385: goto -367 -> 2018
    //   2388: aload_0
    //   2389: aload 6
    //   2391: invokevirtual 2591	mbinc12/mb32b/MainPage:j	(Ljava/lang/String;)Z
    //   2394: ifeq +18 -> 2412
    //   2397: aload 5
    //   2399: ldc_w 2463
    //   2402: ldc_w 794
    //   2405: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2408: pop
    //   2409: goto -315 -> 2094
    //   2412: aload 5
    //   2414: ldc_w 2463
    //   2417: ldc_w 2465
    //   2420: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2423: pop
    //   2424: goto -330 -> 2094
    //   2427: astore 7
    //   2429: goto -1050 -> 1379
    //   2432: astore 6
    //   2434: goto -1848 -> 586
    //   2437: astore 5
    //   2439: goto -1541 -> 898
    //   2442: astore 5
    //   2444: goto -1662 -> 782
    //   2447: astore 5
    //   2449: goto -1922 -> 527
    //   2452: astore 5
    //   2454: goto -2011 -> 443
    //   2457: astore 6
    //   2459: goto -365 -> 2094
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2462	0	this	MainPage
    //   0	2462	1	paramBoolean1	boolean
    //   0	2462	2	paramInt	int
    //   0	2462	3	paramBoolean2	boolean
    //   29	1181	4	i1	int
    //   26	2173	5	localObject1	Object
    //   2227	186	5	localException1	Exception
    //   2437	1	5	localException2	Exception
    //   2442	1	5	localException3	Exception
    //   2447	1	5	localException4	Exception
    //   2452	1	5	localException5	Exception
    //   114	2276	6	localObject2	Object
    //   2432	1	6	localException6	Exception
    //   2457	1	6	localException7	Exception
    //   1522	135	7	localHashMap	HashMap
    //   2427	1	7	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   1748	1768	2227	java/lang/Exception
    //   1367	1379	2427	org/json/JSONException
    //   574	586	2432	java/lang/Exception
    //   837	898	2437	java/lang/Exception
    //   776	782	2442	java/lang/Exception
    //   498	527	2447	java/lang/Exception
    //   437	443	2452	java/lang/Exception
    //   2048	2094	2457	java/lang/Exception
    //   2388	2409	2457	java/lang/Exception
    //   2412	2424	2457	java/lang/Exception
  }
  
  public final aqc b(apm paramApm)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", "playlist");
      localJSONObject.put("ref", paramApm.a);
      localJSONObject.put("title", paramApm.b);
      localJSONObject.put("ownerId", paramApm.d);
      localJSONObject.put("owner", paramApm.c);
      localJSONObject.put("size", paramApm.e);
      localJSONObject.put("thumbnail", paramApm.g);
      localJSONObject.put("thumbnailHQ", paramApm.g);
      localJSONObject.put("isAlbum", paramApm.h.booleanValue());
      return new aqf(this, localJSONObject);
    }
    catch (Exception paramApm)
    {
      for (;;) {}
    }
  }
  
  public final ark b(boolean paramBoolean)
  {
    ark localArk = ark.a(paramBoolean);
    if (!C()) {
      return localArk;
    }
    this.ao = 17;
    a(localArk, true);
    return localArk;
  }
  
  public final SongFragment b(String paramString, boolean paramBoolean)
  {
    SongFragment localSongFragment = SongFragment.a(paramBoolean);
    if (!C()) {
      return localSongFragment;
    }
    MixerBoxUtils.a("page:playlist", null);
    if (i(paramString)) {
      this.ao = 4;
    }
    for (;;)
    {
      a(localSongFragment, true);
      return localSongFragment;
      if (j(paramString)) {
        this.ao = 5;
      } else {
        this.ao = 7;
      }
    }
  }
  
  public final void b()
  {
    this.T.loadAd(asa.U(this), new AdRequest.Builder().build());
  }
  
  public final void b(int paramInt)
  {
    try
    {
      if ((this.aV != paramInt) && (this.I != null))
      {
        this.aU += 1;
        this.aV = paramInt;
        paramInt = Integer.valueOf(this.I.c).intValue();
        if (paramInt <= 85) {
          break label86;
        }
        paramInt = (int)(paramInt * 0.94D);
      }
      for (;;)
      {
        if ((this.aU >= paramInt) && (!this.aW))
        {
          asc.a(this, "videoPlayed");
          this.aW = true;
        }
        return;
        label86:
        paramInt -= 5;
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void b(String paramString)
  {
    MixerBoxUtils.a(this, getResources().getString(2131230850), 0, new boolean[0]);
    arx.a(arx.o(paramString), new apj(this)
    {
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramAnonymousArrayOfXh = new ArrayList();
        try
        {
          paramAnonymousArrayOfByte = new JSONObject(new String(paramAnonymousArrayOfByte)).getJSONObject("getRadioPlaylist").getJSONArray("items");
          paramAnonymousInt = 0;
          while (paramAnonymousInt < paramAnonymousArrayOfByte.length())
          {
            paramAnonymousArrayOfXh.add(MixerBoxUtils.a(paramAnonymousArrayOfByte.getJSONObject(paramAnonymousInt), paramAnonymousInt));
            paramAnonymousInt += 1;
          }
          return;
        }
        catch (Exception paramAnonymousArrayOfByte)
        {
          if (paramAnonymousArrayOfXh.size() == 0) {
            return;
          }
          MainPage.this.F = new ArrayList(paramAnonymousArrayOfXh);
          MainPage.this.I = ((apn)MainPage.this.F.get(0));
          MainPage.this.J = null;
          MainPage.this.G = 0;
          if ((MainPage.this.aJ != null) && (MainPage.this.aJ.b.av)) {
            MainPage.this.j();
          }
          MainPage.this.a(true, 2, false);
        }
      }
    });
  }
  
  public final void b(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("playlistId", paramString1);
      localJSONObject.put("ytId", paramString2);
      MixerBoxUtils.a(this, "PerformAddSong", localJSONObject);
      return;
    }
    catch (Exception paramString1)
    {
      for (;;) {}
    }
  }
  
  public final long c(String paramString)
  {
    for (;;)
    {
      try
      {
        paramString = this.an.a.rawQuery("SELECT * FROM tableIapReceipt WHERE ORDER_ID=\"" + paramString + "\"", null);
        if (paramString.getCount() > 0)
        {
          paramString.moveToFirst();
          l1 = paramString.getLong(0);
          paramString.close();
          return l1;
        }
      }
      catch (Exception paramString)
      {
        return -1L;
      }
      long l1 = -1L;
    }
  }
  
  @SuppressLint({"SetJavaScriptEnabled"})
  public final void c()
  {
    int i2;
    int i1;
    if (this.aJ == null)
    {
      Intent localIntent = new Intent(this, WindowPlayerService.class);
      localIntent.putExtra("messenger", this.aL);
      bindService(localIntent, this.aK, 1);
      if (this.Q <= this.R) {
        break label137;
      }
      i2 = this.Q;
      i1 = this.R;
      if (i2 <= 1500) {
        break label150;
      }
    }
    label137:
    label150:
    for (this.g = (i2 / 5);; this.g = 220)
    {
      this.g = 200;
      this.O = this.g;
      this.P = ((int)(this.g * 16.0D / 9.0D));
      if (i1 < this.P) {
        this.P = i1;
      }
      this.b = -1;
      e = 1;
      f = false;
      return;
      i2 = this.R;
      i1 = this.Q;
      break;
    }
  }
  
  public final void c(int paramInt)
  {
    if (this.ac) {}
    do
    {
      return;
      a(1, paramInt);
      this.b = paramInt;
      WindowPlayerService.b localB;
      if (this.b == 1)
      {
        this.ak = 0;
        if (this.aJ != null)
        {
          this.aJ.i(false);
          localB = this.aJ;
          if ((!localB.b.a()) && (asa.H(localB.b) != 2)) {
            localB.b.d();
          }
        }
      }
      if ((this.b == 2) && (ScreenReceiver.b)) {
        ((AudioManager)getSystemService("audio")).setStreamVolume(3, this.aA, 0);
      }
      if ((j == 2) && (!this.ae) && (this.b == 1))
      {
        this.ae = true;
        if (this.aJ != null) {
          this.aJ.h(false);
        }
      }
      if ((this.aJ != null) && (this.aJ.b.C.getVisibility() == 0) && (this.b == 1))
      {
        this.aJ.e(false);
        asa.b(this, 1);
        localB = this.aJ;
        if (localB.b.ad != null) {
          localB.b.ad.setVisibility(0);
        }
        if (localB.b.ac != null) {
          localB.b.ac.setVisibility(0);
        }
        j = 1;
      }
    } while ((this.b != 1) || (!this.aP));
    this.aP = false;
    if (this.aJ != null) {
      this.aJ.f("javascript:seekTo(" + this.aO + ")");
    }
    this.aO = 0;
  }
  
  public final void c(apm paramApm)
  {
    Object localObject1 = new JSONObject();
    try
    {
      ((JSONObject)localObject1).put("playlistId", paramApm.a);
      MixerBoxUtils.a(this, "PerformSubscribe", (JSONObject)localObject1);
      if (this.am)
      {
        MixerBoxUtils.b(this, paramApm.a);
        return;
      }
      localObject1 = this.an;
      String str1 = paramApm.a;
      String str2 = paramApm.b;
      String str3 = paramApm.d;
      String str4 = paramApm.c;
      String str5 = paramApm.e;
      String str6 = paramApm.f;
      String str7 = paramApm.g;
      boolean bool = paramApm.h.booleanValue();
      if ((((arz)localObject1).a.isOpen()) && (((arz)localObject1).a.rawQuery("SELECT * FROM tablePlaylist WHERE MB_ID=\"" + str1 + "\"", null).getCount() == 0))
      {
        if (bool)
        {
          i1 = 1;
          Object localObject2 = ((arz)localObject1).a.rawQuery("SELECT * FROM tablePlaylist", null);
          int i2 = ((Cursor)localObject2).getCount();
          ((Cursor)localObject2).close();
          localObject2 = new ContentValues();
          ((ContentValues)localObject2).put("MB_ID", str1);
          ((ContentValues)localObject2).put("NAME", str2);
          ((ContentValues)localObject2).put("OWNER_ID", str3);
          ((ContentValues)localObject2).put("OWNER_NAME", str4);
          ((ContentValues)localObject2).put("ITEM_COUNT", str5);
          ((ContentValues)localObject2).put("SUBS_COUNT", str6);
          ((ContentValues)localObject2).put("COVER", str7);
          ((ContentValues)localObject2).put("TYPE", Integer.valueOf(2));
          ((ContentValues)localObject2).put("ISALBUM", Integer.valueOf(i1));
          ((ContentValues)localObject2).put("ORDERS", Integer.valueOf(i2));
          ((arz)localObject1).a.insert("tablePlaylist", null, (ContentValues)localObject2);
        }
      }
      else
      {
        localObject1 = b(new apm(paramApm.a, paramApm.b, paramApm.d, paramApm.c, paramApm.e, paramApm.f, paramApm.g, paramApm.h.booleanValue()));
        this.C.add(localObject1);
        this.L.add(paramApm);
        s();
        G();
        if (this.k.getVisibility() != 0) {
          paramApm = new JSONObject();
        }
        try
        {
          paramApm.put("Tab", "Playlist");
          MixerBoxUtils.a(this, "ShowBadgeIcon", paramApm);
          this.k.setVisibility(0);
          MixerBoxUtils.a(this, getResources().getString(2131230881), 0, new boolean[0]);
          asc.a(this, "subPlaylist");
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        continue;
        int i1 = 0;
      }
    }
  }
  
  public final void d()
  {
    boolean bool2 = true;
    if ((this.aJ == null) || (!this.ad) || (this.ac)) {
      return;
    }
    final int i1 = asa.H(this);
    final boolean bool1;
    if ((this.I != null) && ((this.I.f == 3) || (this.I.f == 2)))
    {
      bool1 = bool2;
      if (MyService.a != null)
      {
        if (!MyService.a.isPlaying()) {
          break label98;
        }
        bool1 = bool2;
      }
    }
    for (;;)
    {
      new Thread(new Runnable()
      {
        public final void run()
        {
          try
          {
            Object localObject1;
            if (MainPage.this.I == null)
            {
              localObject1 = new HashMap();
              ((Map)localObject1).put("log", "v307_MainPage.java_line1793_playingSong_is_null");
              MixerBoxUtils.a("log:error", (Map)localObject1);
              return;
            }
            if (MainPage.this.I.f == 2) {
              localObject1 = MainPage.d(MainPage.this, MainPage.this.I.a);
            }
            for (;;)
            {
              Intent localIntent = new Intent(MainPage.this, WindowPlayerService.class);
              Object localObject2 = "";
              if (MainPage.this.I.b != null) {
                localObject2 = MainPage.this.I.b;
              }
              localIntent.putExtra("song_name", (String)localObject2);
              if (i1 == 2) {
                localIntent.putExtra("should_show_btn", true);
              }
              for (;;)
              {
                localIntent.putExtra("song_source", MainPage.this.I.f);
                localIntent.putExtra("url", (String)localObject1);
                localIntent.putExtra("is_playing", bool1);
                MainPage.this.startService(localIntent);
                return;
                if (MainPage.this.I.f == 1)
                {
                  localObject1 = "http://i.ytimg.com/vi/" + MainPage.this.I.d + "/mqdefault.jpg";
                  break;
                }
                if (MainPage.this.I.f != 3) {
                  break label357;
                }
                localObject1 = MainPage.this.I.g;
                break;
                if ((MainPage.this.Z != null) && (!ScreenReceiver.a)) {
                  localIntent.putExtra("should_show_btn", false);
                } else if (i1 == 0)
                {
                  if (MainPage.this.aJ != null)
                  {
                    localObject2 = MainPage.this.aJ;
                    if (WindowPlayerService.b.b()) {
                      localIntent.putExtra("should_show_btn", false);
                    } else {
                      localIntent.putExtra("should_show_btn", false);
                    }
                  }
                  else
                  {
                    localIntent.putExtra("should_show_btn", false);
                  }
                }
                else {
                  localIntent.putExtra("should_show_btn", true);
                }
              }
              label357:
              localObject1 = "";
            }
            return;
          }
          catch (Exception localException) {}
        }
      }).start();
      return;
      label98:
      bool1 = false;
      continue;
      bool1 = bool2;
      if (this.af != 1) {
        bool1 = false;
      }
    }
  }
  
  public final void d(int paramInt)
  {
    this.af = paramInt;
    if (this.af == 1)
    {
      if (this.aJ == null) {
        break label69;
      }
      if ((this.I == null) || (this.I.f != 1)) {
        break label58;
      }
      if (arx.a(this)) {
        this.aJ.b(false);
      }
    }
    for (;;)
    {
      d();
      return;
      label58:
      this.aJ.b(false);
      continue;
      label69:
      if ((this.aJ != null) && ((this.I == null) || (this.I.f != 1) || (arx.a(this)))) {
        this.aJ.b(true);
      }
    }
  }
  
  public final void d(String paramString)
  {
    if ((this.I != null) && ((this.I.f == 3) || (this.I.f == 2))) {}
    label29:
    do
    {
      do
      {
        do
        {
          do
          {
            break label29;
            break label29;
            do
            {
              return;
            } while ((paramString == null) || (paramString.equals("undefined")));
            this.H = ((int)Math.round(Double.valueOf(paramString).doubleValue()));
          } while ((!this.av) || (this.aJ == null));
          this.aJ.d(this.H);
          b(this.H);
          this.aJ.b(MixerBoxUtils.a(String.valueOf(this.H)));
        } while (!this.aJ.b.av);
        if (this.H < this.bh - 5)
        {
          this.aJ.f("javascript:seekTo(" + this.bh + ")");
          return;
        }
        if ((this.H < this.bh - 5) || (this.H >= this.bh + 5)) {
          break;
        }
      } while (!this.bj);
      this.bj = false;
      WindowPlayerService.a(this.aJ.b, true);
      return;
      if (this.H == this.bi - 5)
      {
        WindowPlayerService.a(this.aJ.b, false);
        return;
      }
    } while (this.H < this.bi);
    P();
  }
  
  public final void e()
  {
    this.ad = false;
    if ((j == 2) && (!this.ae) && (this.aJ != null)) {
      this.aJ.h(false);
    }
    if (this.aJ != null)
    {
      this.aJ.f("javascript:pauseVideo()");
      this.aJ.h();
    }
    if (MyService.a != null) {
      MyService.a.pause();
    }
    if (MyService.a != null) {
      MyService.a.reset();
    }
    if (MyService.a != null) {
      MyService.a.release();
    }
    MyService.a = null;
    MyService.a = new MediaPlayer();
    stopService(new Intent(this, MyService.class));
    if (this.aJ != null)
    {
      this.aJ.f("about:blank");
      this.aJ.i();
    }
    if (this.aJ != null) {
      unbindService(this.aK);
    }
    this.aJ = null;
  }
  
  public final void e(int paramInt)
  {
    if ((asa.H(this) != 2) && (((KeyguardManager)getSystemService("keyguard")).inKeyguardRestrictedInputMode())) {}
    label24:
    do
    {
      int i1;
      do
      {
        do
        {
          break label24;
          do
          {
            return;
          } while (this.F == null);
          if (e != 1) {
            break;
          }
          this.G -= 1;
        } while (this.F.size() == 0);
        if (this.G < 0) {
          this.G = (this.F.size() - 1);
        }
        if (this.G >= this.F.size()) {
          this.G = 0;
        }
        if (!f) {
          break;
        }
        i1 = h(this.G);
      } while (i1 == -1);
      int i2 = this.F.size();
      this.I = ((apn)this.F.get(i1 % i2));
      a(true, paramInt, false);
      return;
      this.I = ((apn)this.F.get(this.G));
      a(true, paramInt, false);
      return;
    } while (this.F.size() == 0);
    if ((this.G >= this.F.size()) || (this.G < 0)) {
      this.G = 0;
    }
    this.I = ((apn)this.F.get(this.G));
    a(true, paramInt, false);
  }
  
  public final void e(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("playlistId", paramString);
      MixerBoxUtils.a(this, "CreatePlaylist", localJSONObject);
      return;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
  }
  
  public final void f()
  {
    if ((this.I != null) && ((this.I.f == 3) || (this.I.f == 2)))
    {
      if ((MyService.a != null) && (!MyService.a.isPlaying()))
      {
        MyService.a.start();
        if (this.aJ != null) {
          this.aJ.b(false);
        }
      }
      d();
      return;
    }
    if (this.b == 2)
    {
      if (this.aJ != null) {
        this.aJ.f("javascript:playVideo()");
      }
      a(true, this.H, 1);
      if (this.aJ != null) {
        this.aJ.b(false);
      }
    }
    d();
  }
  
  public final void f(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("artistId", paramString);
      MixerBoxUtils.a(this, "FollowArtist", localJSONObject);
      return;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
  }
  
  public final void g()
  {
    if ((this.I != null) && ((this.I.f == 3) || (this.I.f == 2)))
    {
      if ((MyService.a != null) && (MyService.a.isPlaying()))
      {
        MyService.a.pause();
        if (this.aJ != null) {
          this.aJ.b(true);
        }
      }
      d();
      return;
    }
    this.ar = false;
    if ((this.b == 1) || (this.b == 3))
    {
      if (this.aJ != null) {
        this.aJ.f("javascript:pauseVideo()");
      }
      a(false, this.H, 1);
      if (this.aJ != null) {
        this.aJ.b(true);
      }
    }
    d();
  }
  
  public final void g(String paramString)
  {
    if (paramString.length() == 0) {
      return;
    }
    this.a = paramString;
    asa.r(this, paramString);
    S();
    R();
    MixerBoxUtils.h(this);
  }
  
  public final apm h(String paramString)
  {
    apm localApm = new apm(paramString, "", "", "", "0", "", "", false);
    int i1 = 0;
    while (i1 < this.C.size()) {
      try
      {
        if (((aqc)this.C.get(i1)).b().getString("ref").equals(paramString))
        {
          JSONObject localJSONObject = ((aqc)this.C.get(i1)).b();
          Object localObject = "";
          String str1 = "";
          String str2 = "";
          String str3 = "";
          String str4 = "";
          boolean bool = false;
          if (!localJSONObject.isNull("title")) {
            localObject = localJSONObject.getString("title");
          }
          if (!localJSONObject.isNull("ownerId")) {
            str1 = localJSONObject.getString("ownerId");
          }
          if (!localJSONObject.isNull("owner")) {
            str2 = localJSONObject.getString("owner");
          }
          if (!localJSONObject.isNull("size")) {
            str3 = localJSONObject.getString("size");
          }
          if (!localJSONObject.isNull("thumbnailHQ")) {
            str4 = localJSONObject.getString("thumbnailHQ");
          }
          if (!localJSONObject.isNull("isAlbum")) {
            bool = localJSONObject.getBoolean("isAlbum");
          }
          localObject = new apm(paramString, (String)localObject, str1, str2, str3, "", str4, bool);
          return localObject;
        }
      }
      catch (Exception localException)
      {
        i1 += 1;
      }
    }
    return localApm;
  }
  
  public final void h()
  {
    MenuItem localMenuItem = this.al.findItem(999);
    if (localMenuItem != null)
    {
      if (!cd.e(localMenuItem)) {
        cd.c(localMenuItem);
      }
      this.bn.setText("");
    }
    try
    {
      this.bn.setText(this.bn.getText());
      if (this.bn.requestFocus()) {
        ((InputMethodManager)getSystemService("input_method")).showSoftInput(this.bn, 1);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public final void i()
  {
    if (this.aJ != null)
    {
      WindowPlayerService localWindowPlayerService = this.aJ.b;
      localWindowPlayerService.av = true;
      localWindowPlayerService.b(false);
      localWindowPlayerService.c(false);
      localWindowPlayerService.aw = e;
      localWindowPlayerService.ax = f;
      e = 1;
      f = false;
      MixerBoxUtils.f(localWindowPlayerService, "startFastBrowse");
    }
  }
  
  public final boolean i(String paramString)
  {
    if ((this.M == null) || (this.M.size() == 0)) {}
    for (;;)
    {
      return false;
      int i1 = 0;
      while (i1 < this.M.size())
      {
        if ((this.M.get(i1) != null) && (((apm)this.M.get(i1)).a != null) && (((apm)this.M.get(i1)).a.equals(paramString))) {
          return true;
        }
        i1 += 1;
      }
    }
  }
  
  public final void j()
  {
    if (this.aJ != null) {
      this.aJ.b.b();
    }
  }
  
  public final boolean j(String paramString)
  {
    if ((this.L == null) || (this.L.size() == 0)) {}
    for (;;)
    {
      return false;
      int i1 = 0;
      while (i1 < this.L.size())
      {
        if ((this.L.get(i1) != null) && (((apm)this.L.get(i1)).a != null) && (((apm)this.L.get(i1)).a.equals(paramString))) {
          return true;
        }
        i1 += 1;
      }
    }
  }
  
  public final JSONObject k()
  {
    JSONArray localJSONArray = l();
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("artists", localJSONArray);
      localJSONObject.toString();
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public final void k(String paramString)
  {
    if ((this.L == null) || (this.L.size() == 0)) {
      return;
    }
    int i1 = 0;
    while (i1 < this.L.size())
    {
      if ((this.L.get(i1) != null) && (((apm)this.L.get(i1)).a != null) && (((apm)this.L.get(i1)).a.equals(paramString))) {
        this.L.remove(i1);
      }
      i1 += 1;
    }
    G();
  }
  
  public final JSONArray l()
  {
    JSONArray localJSONArray = asa.ah(this);
    String[] arrayOfString = asa.ag(this).split(",");
    int i1;
    if (arrayOfString.length > 1) {
      i1 = 0;
    }
    for (;;)
    {
      JSONObject localJSONObject;
      if (i1 < arrayOfString.length) {
        localJSONObject = new JSONObject();
      }
      try
      {
        localJSONObject.put("source", "unknown");
        localJSONObject.put("name", URLDecoder.decode(arrayOfString[i1], "utf-8"));
        localJSONObject.put("count", 1);
        localJSONArray.put(localJSONObject);
        i1 += 1;
        continue;
        asa.a(this, localJSONArray);
        asa.t(this, "");
        return localJSONArray;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
  }
  
  public final boolean l(String paramString)
  {
    if (this.am)
    {
      localObject = this.M.iterator();
      while (((Iterator)localObject).hasNext())
      {
        apm localApm = (apm)((Iterator)localObject).next();
        if (localApm.a.equals(paramString)) {
          return Integer.parseInt(localApm.e) < 500;
        }
      }
      return false;
    }
    Object localObject = this.an;
    if (((arz)localObject).a.isOpen()) {
      if (((arz)localObject).a.query("tableSong", null, "PID=?", new String[] { paramString }, null, null, null).getCount() < 500) {
        return true;
      }
    }
    return false;
  }
  
  public final Cursor m()
  {
    try
    {
      Cursor localCursor = this.an.e();
      return localCursor;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public final Cursor n()
  {
    try
    {
      Cursor localCursor = this.an.a.rawQuery("SELECT * FROM tableIapReceipt WHERE PROCESS_SUCCESS=0", null);
      return localCursor;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public final JSONArray o()
  {
    JSONArray localJSONArray = new JSONArray();
    Cursor localCursor = this.an.e();
    int i2 = localCursor.getCount();
    localCursor.moveToLast();
    int i1 = 0;
    while (i1 < i2)
    {
      try
      {
        localJSONObject = new JSONObject();
        localJSONObject.put("type", "receipt");
        localJSONObject.put("name", localCursor.getString(1));
        localJSONObject.put("product_id", localCursor.getString(2));
        localJSONObject.put("timestamp", localCursor.getLong(3));
        localJSONObject.put("price", localCursor.getString(4));
        localJSONObject.put("coin", localCursor.getString(6));
        localJSONObject.put("order_id", localCursor.getString(7));
        if (localCursor.getInt(10) != 1) {
          break label229;
        }
        bool = true;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          JSONObject localJSONObject;
          continue;
          boolean bool = false;
        }
      }
      localJSONObject.put("success", bool);
      localJSONArray.put(localJSONObject);
      localCursor.moveToPrevious();
      i1 += 1;
    }
    localCursor.close();
    return localJSONArray;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (!asc.a(paramInt1, paramInt2, paramIntent))
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      this.bp.onActivityResult(paramInt1, paramInt2, paramIntent);
      Session.getActiveSession().onActivityResult(this, paramInt1, paramInt2, paramIntent);
    }
    switch (paramInt1)
    {
    default: 
    case 129742: 
      do
      {
        return;
      } while (paramInt2 != 0);
      L();
      return;
    case 1000: 
      if (paramInt2 == -1)
      {
        paramIntent = paramIntent.getStringExtra("authAccount");
        if (paramIntent == null)
        {
          q();
          return;
        }
        if (arx.a(this))
        {
          new a(this, paramIntent, "oauth2:https://www.googleapis.com/auth/youtube.readonly").execute(new Object[0]);
          return;
        }
        arw.a(this, false, "NoInternet", new String[0]);
        return;
      }
      arw.a(this, false, "GetAuthFailed", new String[0]);
      return;
    }
    new AsyncHttpClient().get("https://graph.facebook.com/me/permissions?access_token=" + asa.g(this), null, new apj(this)
    {
      public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
      {
        super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
        if (paramAnonymousArrayOfByte != null) {
          new String(paramAnonymousArrayOfByte);
        }
      }
      
      public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
      {
        super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
        paramAnonymousArrayOfXh = "";
        if (paramAnonymousArrayOfByte != null) {
          paramAnonymousArrayOfXh = new String(paramAnonymousArrayOfByte);
        }
        try
        {
          paramAnonymousArrayOfXh = new JSONObject(paramAnonymousArrayOfXh);
          if (paramAnonymousArrayOfXh.isNull("data")) {
            return;
          }
          paramAnonymousArrayOfXh = paramAnonymousArrayOfXh.getJSONArray("data");
          if (paramAnonymousArrayOfXh.isNull(0)) {
            return;
          }
          paramAnonymousArrayOfXh = paramAnonymousArrayOfXh.getJSONObject(0);
          if (paramAnonymousArrayOfXh.isNull("publish_actions"))
          {
            asa.a(MainPage.this, false);
            if (!MainPage.this.x.isAdded()) {
              return;
            }
            MainPage.this.x.a.setChecked(false);
            return;
          }
        }
        catch (JSONException paramAnonymousArrayOfXh)
        {
          paramAnonymousArrayOfXh.printStackTrace();
          return;
        }
        if (paramAnonymousArrayOfXh.getInt("publish_actions") == 1)
        {
          asa.a(MainPage.this, true);
          if (MainPage.this.x.isAdded())
          {
            MainPage.this.x.b = false;
            MainPage.this.x.a.setChecked(true);
          }
          paramAnonymousArrayOfXh = new HashMap();
          paramAnonymousArrayOfXh.put("on", "1");
          MixerBoxUtils.a("action:set_publish_activity", paramAnonymousArrayOfXh);
          return;
        }
        asa.a(MainPage.this, false);
        if (MainPage.this.x.isAdded()) {
          MainPage.this.x.a.setChecked(false);
        }
      }
    });
  }
  
  public void onBackPressed()
  {
    if (arv.a(this).a())
    {
      arv.a(this).b();
      return;
    }
    if (MixerBoxUtils.c())
    {
      MixerBoxUtils.d();
      return;
    }
    if (this.al != null)
    {
      MenuItem localMenuItem = this.al.findItem(999);
      if ((localMenuItem != null) && (cd.e(localMenuItem))) {
        cd.d(localMenuItem);
      }
    }
    if ((this.aJ != null) && (this.aJ.b.a()) && (this.aJ.b.c == 2))
    {
      this.aJ.j();
      return;
    }
    if (this.m.size() > 1)
    {
      D();
      return;
    }
    if ((this.aJ != null) && (this.aJ.b.a()))
    {
      E();
      moveTaskToBack(true);
    }
    for (;;)
    {
      FlurryAgent.onEndSession(this);
      return;
      if ((this.aD) && (!asa.aJ(this)) && (asa.aL(this)))
      {
        T();
        moveTaskToBack(true);
      }
      else
      {
        if (this.aJ != null) {
          this.aJ.f("about:blank");
        }
        finish();
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = arv.a(this);
    paramConfiguration.c();
    if (paramConfiguration.a()) {
      paramConfiguration.b();
    }
  }
  
  /* Error */
  @SuppressLint({"SetJavaScriptEnabled"})
  public void onCreate(final Bundle paramBundle)
  {
    // Byte code:
    //   0: aload_0
    //   1: aconst_null
    //   2: invokespecial 2957	android/support/v7/app/ActionBarActivity:onCreate	(Landroid/os/Bundle;)V
    //   5: aload_0
    //   6: putstatic 2270	art:a	Landroid/content/Context;
    //   9: aload_0
    //   10: checkcast 4	android/support/v7/app/ActionBarActivity
    //   13: invokevirtual 2286	android/support/v7/app/ActionBarActivity:getSupportActionBar	()Landroid/support/v7/app/ActionBar;
    //   16: astore 6
    //   18: aload 6
    //   20: invokevirtual 2958	android/support/v7/app/ActionBar:c	()V
    //   23: aload 6
    //   25: iconst_0
    //   26: invokevirtual 2291	android/support/v7/app/ActionBar:a	(Z)V
    //   29: aload 6
    //   31: invokevirtual 2287	android/support/v7/app/ActionBar:f	()V
    //   34: aload 6
    //   36: invokevirtual 2288	android/support/v7/app/ActionBar:d	()V
    //   39: aload 6
    //   41: invokevirtual 2290	android/support/v7/app/ActionBar:e	()V
    //   44: aload 6
    //   46: invokevirtual 2959	android/support/v7/app/ActionBar:b	()V
    //   49: aload_0
    //   50: ldc_w 2960
    //   53: invokevirtual 2961	mbinc12/mb32b/MainPage:setContentView	(I)V
    //   56: aload_0
    //   57: iconst_0
    //   58: putfield 415	mbinc12/mb32b/MainPage:aE	Z
    //   61: aload_0
    //   62: invokevirtual 2965	mbinc12/mb32b/MainPage:getIntent	()Landroid/content/Intent;
    //   65: invokevirtual 2969	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   68: putstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   71: getstatic 2971	ary:a	Z
    //   74: ifeq +21 -> 95
    //   77: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   80: ifnull +15 -> 95
    //   83: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   86: ldc_w 2973
    //   89: invokevirtual 2976	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   92: ifne +47 -> 139
    //   95: new 1350	android/content/Intent
    //   98: dup
    //   99: aload_0
    //   100: ldc_w 2978
    //   103: invokespecial 2451	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   106: astore_1
    //   107: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   110: ifnull +11 -> 121
    //   113: aload_1
    //   114: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   117: invokevirtual 1388	android/content/Intent:putExtras	(Landroid/os/Bundle;)Landroid/content/Intent;
    //   120: pop
    //   121: aload_1
    //   122: ldc_w 2979
    //   125: invokevirtual 2983	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   128: pop
    //   129: aload_0
    //   130: aload_1
    //   131: invokevirtual 2986	mbinc12/mb32b/MainPage:startActivity	(Landroid/content/Intent;)V
    //   134: aload_0
    //   135: invokevirtual 2946	mbinc12/mb32b/MainPage:finish	()V
    //   138: return
    //   139: aload_0
    //   140: aload_0
    //   141: invokestatic 2988	asa:W	(Landroid/content/Context;)Ljava/lang/String;
    //   144: putfield 389	mbinc12/mb32b/MainPage:a	Ljava/lang/String;
    //   147: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   150: ifnonnull +13 -> 163
    //   153: new 722	android/os/Bundle
    //   156: dup
    //   157: invokespecial 1322	android/os/Bundle:<init>	()V
    //   160: putstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   163: iconst_0
    //   164: putstatic 2971	ary:a	Z
    //   167: aload_0
    //   168: iconst_1
    //   169: putfield 1600	mbinc12/mb32b/MainPage:ay	Z
    //   172: aload_0
    //   173: new 2887	com/facebook/UiLifecycleHelper
    //   176: dup
    //   177: aload_0
    //   178: aload_0
    //   179: getfield 448	mbinc12/mb32b/MainPage:bq	Lcom/facebook/Session$StatusCallback;
    //   182: invokespecial 2991	com/facebook/UiLifecycleHelper:<init>	(Landroid/app/Activity;Lcom/facebook/Session$StatusCallback;)V
    //   185: putfield 2885	mbinc12/mb32b/MainPage:bp	Lcom/facebook/UiLifecycleHelper;
    //   188: aload_0
    //   189: getfield 2885	mbinc12/mb32b/MainPage:bp	Lcom/facebook/UiLifecycleHelper;
    //   192: aload_1
    //   193: invokevirtual 2992	com/facebook/UiLifecycleHelper:onCreate	(Landroid/os/Bundle;)V
    //   196: aload_0
    //   197: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   200: ldc_w 2973
    //   203: invokevirtual 2993	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   206: putfield 401	mbinc12/mb32b/MainPage:am	Z
    //   209: aload_0
    //   210: iconst_1
    //   211: anewarray 2995	ali
    //   214: dup
    //   215: iconst_0
    //   216: new 2997	ku
    //   219: dup
    //   220: invokespecial 2998	ku:<init>	()V
    //   223: aastore
    //   224: invokestatic 3003	alc:a	(Landroid/content/Context;[Lali;)Lalc;
    //   227: pop
    //   228: aload_0
    //   229: iconst_1
    //   230: putfield 393	mbinc12/mb32b/MainPage:z	Z
    //   233: aload_0
    //   234: iconst_1
    //   235: putfield 415	mbinc12/mb32b/MainPage:aE	Z
    //   238: aload_0
    //   239: invokevirtual 3007	mbinc12/mb32b/MainPage:getApplicationContext	()Landroid/content/Context;
    //   242: invokestatic 3009	aru:a	()Ljava/lang/String;
    //   245: invokestatic 3014	com/google/android/gms/ads/MobileAds:initialize	(Landroid/content/Context;Ljava/lang/String;)V
    //   248: aload_0
    //   249: invokestatic 3019	com/applovin/sdk/AppLovinSdk:initializeSdk	(Landroid/content/Context;)V
    //   252: aload_0
    //   253: new 503	apl
    //   256: dup
    //   257: aload_0
    //   258: invokespecial 3020	apl:<init>	(Landroid/content/Context;)V
    //   261: putfield 496	mbinc12/mb32b/MainPage:U	Lapl;
    //   264: aload_0
    //   265: aload_0
    //   266: invokestatic 3024	com/google/android/gms/ads/MobileAds:getRewardedVideoAdInstance	(Landroid/content/Context;)Lcom/google/android/gms/ads/reward/RewardedVideoAd;
    //   269: putfield 2610	mbinc12/mb32b/MainPage:T	Lcom/google/android/gms/ads/reward/RewardedVideoAd;
    //   272: aload_0
    //   273: getfield 2610	mbinc12/mb32b/MainPage:T	Lcom/google/android/gms/ads/reward/RewardedVideoAd;
    //   276: new 124	mbinc12/mb32b/MainPage$8
    //   279: dup
    //   280: aload_0
    //   281: invokespecial 3025	mbinc12/mb32b/MainPage$8:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   284: invokeinterface 3029 2 0
    //   289: aload_0
    //   290: invokevirtual 1563	mbinc12/mb32b/MainPage:b	()V
    //   293: aload_0
    //   294: iconst_0
    //   295: putfield 1785	mbinc12/mb32b/MainPage:bf	I
    //   298: aload_0
    //   299: aload_0
    //   300: ldc_w 3030
    //   303: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   306: checkcast 537	android/widget/RelativeLayout
    //   309: putfield 1550	mbinc12/mb32b/MainPage:bg	Landroid/widget/RelativeLayout;
    //   312: aload_0
    //   313: ldc_w 3031
    //   316: aload_0
    //   317: ldc_w 3032
    //   320: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   323: checkcast 541	android/widget/ImageView
    //   326: bipush 8
    //   328: invokestatic 956	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;ILandroid/widget/ImageView;I)V
    //   331: aload_0
    //   332: invokestatic 1783	asa:ba	(Landroid/content/Context;)Z
    //   335: ifeq +25 -> 360
    //   338: aload_0
    //   339: getfield 496	mbinc12/mb32b/MainPage:U	Lapl;
    //   342: invokestatic 501	aru:f	()Ljava/lang/String;
    //   345: invokevirtual 506	apl:a	(Ljava/lang/String;)Lcom/mopub/nativeads/StaticNativeAd;
    //   348: pop
    //   349: aload_0
    //   350: getfield 496	mbinc12/mb32b/MainPage:U	Lapl;
    //   353: invokestatic 1791	aru:g	()Ljava/lang/String;
    //   356: invokevirtual 506	apl:a	(Ljava/lang/String;)Lcom/mopub/nativeads/StaticNativeAd;
    //   359: pop
    //   360: aload_0
    //   361: aload_0
    //   362: invokestatic 3034	asa:d	(Landroid/content/Context;)Ljava/lang/String;
    //   365: putfield 411	mbinc12/mb32b/MainPage:aC	Ljava/lang/String;
    //   368: aload_0
    //   369: getfield 411	mbinc12/mb32b/MainPage:aC	Ljava/lang/String;
    //   372: invokestatic 3035	ku:b	(Ljava/lang/String;)V
    //   375: aload_0
    //   376: invokestatic 3037	asa:A	(Landroid/content/Context;)Z
    //   379: ifeq +1966 -> 2345
    //   382: aload_0
    //   383: iconst_0
    //   384: putfield 413	mbinc12/mb32b/MainPage:aD	Z
    //   387: aload_0
    //   388: getfield 413	mbinc12/mb32b/MainPage:aD	Z
    //   391: ifeq +10 -> 401
    //   394: aload_0
    //   395: ldc_w 3039
    //   398: invokestatic 1013	mbinc12/mb32b/utils/MixerBoxUtils:f	(Landroid/content/Context;Ljava/lang/String;)V
    //   401: ldc_w 3041
    //   404: invokestatic 3042	mbinc12/mb32b/utils/MixerBoxUtils:b	(Ljava/lang/String;)V
    //   407: aload_0
    //   408: getfield 419	mbinc12/mb32b/MainPage:bk	Lasn;
    //   411: ifnonnull +15 -> 426
    //   414: aload_0
    //   415: new 2588	asn
    //   418: dup
    //   419: aload_0
    //   420: invokespecial 3043	asn:<init>	(Landroid/content/Context;)V
    //   423: putfield 419	mbinc12/mb32b/MainPage:bk	Lasn;
    //   426: invokestatic 3044	arx:g	()Ljava/lang/String;
    //   429: new 56	mbinc12/mb32b/MainPage$30
    //   432: dup
    //   433: aload_0
    //   434: aload_0
    //   435: invokespecial 3045	mbinc12/mb32b/MainPage$30:<init>	(Lmbinc12/mb32b/MainPage;Landroid/content/Context;)V
    //   438: invokestatic 1295	arx:a	(Ljava/lang/String;Lcom/loopj/android/http/AsyncHttpResponseHandler;)V
    //   441: aload_0
    //   442: aload_0
    //   443: ldc_w 2012
    //   446: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   449: checkcast 1545	android/widget/LinearLayout
    //   452: putfield 1543	mbinc12/mb32b/MainPage:s	Landroid/widget/LinearLayout;
    //   455: aload_0
    //   456: aconst_null
    //   457: putfield 1101	mbinc12/mb32b/MainPage:bd	Ljava/lang/Boolean;
    //   460: aload_0
    //   461: aload_0
    //   462: ldc_w 1981
    //   465: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   468: checkcast 1109	com/mopub/mobileads/MoPubView
    //   471: putfield 1093	mbinc12/mb32b/MainPage:S	Lcom/mopub/mobileads/MoPubView;
    //   474: aload_0
    //   475: getfield 1093	mbinc12/mb32b/MainPage:S	Lcom/mopub/mobileads/MoPubView;
    //   478: aload_0
    //   479: invokestatic 3047	asa:P	(Landroid/content/Context;)Ljava/lang/String;
    //   482: invokevirtual 3050	com/mopub/mobileads/MoPubView:setAdUnitId	(Ljava/lang/String;)V
    //   485: aload_0
    //   486: invokestatic 3052	asa:R	(Landroid/content/Context;)Ljava/lang/String;
    //   489: putstatic 3053	aru:a	Ljava/lang/String;
    //   492: aload_0
    //   493: invokestatic 3055	asa:S	(Landroid/content/Context;)Ljava/lang/String;
    //   496: putstatic 3056	aru:b	Ljava/lang/String;
    //   499: aload_0
    //   500: invokespecial 1399	mbinc12/mb32b/MainPage:V	()V
    //   503: aload_0
    //   504: invokestatic 1992	asa:N	(Landroid/content/Context;)J
    //   507: lconst_0
    //   508: lcmp
    //   509: ifle +43 -> 552
    //   512: aload_0
    //   513: new 1986	com/mopub/mobileads/MoPubInterstitial
    //   516: dup
    //   517: aload_0
    //   518: aload_0
    //   519: invokestatic 3058	asa:Q	(Landroid/content/Context;)Ljava/lang/String;
    //   522: invokespecial 3061	com/mopub/mobileads/MoPubInterstitial:<init>	(Landroid/app/Activity;Ljava/lang/String;)V
    //   525: putfield 488	mbinc12/mb32b/MainPage:aZ	Lcom/mopub/mobileads/MoPubInterstitial;
    //   528: new 104	mbinc12/mb32b/MainPage$47
    //   531: dup
    //   532: aload_0
    //   533: invokespecial 3062	mbinc12/mb32b/MainPage$47:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   536: astore_1
    //   537: aload_0
    //   538: getfield 488	mbinc12/mb32b/MainPage:aZ	Lcom/mopub/mobileads/MoPubInterstitial;
    //   541: aload_1
    //   542: invokevirtual 3066	com/mopub/mobileads/MoPubInterstitial:setInterstitialAdListener	(Lcom/mopub/mobileads/MoPubInterstitial$InterstitialAdListener;)V
    //   545: aload_0
    //   546: getfield 488	mbinc12/mb32b/MainPage:aZ	Lcom/mopub/mobileads/MoPubInterstitial;
    //   549: invokevirtual 3069	com/mopub/mobileads/MoPubInterstitial:load	()V
    //   552: aload_0
    //   553: invokevirtual 1132	mbinc12/mb32b/MainPage:getPackageManager	()Landroid/content/pm/PackageManager;
    //   556: ldc_w 3071
    //   559: invokevirtual 3074	android/content/pm/PackageManager:hasSystemFeature	(Ljava/lang/String;)Z
    //   562: ifeq +22 -> 584
    //   565: aload_0
    //   566: ldc_w 3076
    //   569: invokevirtual 1264	mbinc12/mb32b/MainPage:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   572: checkcast 3078	android/telephony/TelephonyManager
    //   575: aload_0
    //   576: getfield 468	mbinc12/mb32b/MainPage:aM	Landroid/telephony/PhoneStateListener;
    //   579: bipush 32
    //   581: invokevirtual 3082	android/telephony/TelephonyManager:listen	(Landroid/telephony/PhoneStateListener;I)V
    //   584: iconst_1
    //   585: putstatic 3084	mbinc12/mb32b/MainPage:au	Z
    //   588: aload_0
    //   589: ldc_w 3086
    //   592: invokevirtual 1264	mbinc12/mb32b/MainPage:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   595: checkcast 3088	android/view/WindowManager
    //   598: invokeinterface 3092 1 0
    //   603: invokevirtual 3097	android/view/Display:getRotation	()I
    //   606: istore_2
    //   607: aload_0
    //   608: invokevirtual 3101	mbinc12/mb32b/MainPage:getWindowManager	()Landroid/view/WindowManager;
    //   611: astore_1
    //   612: getstatic 1188	android/os/Build$VERSION:SDK_INT	I
    //   615: bipush 13
    //   617: if_icmplt +1736 -> 2353
    //   620: new 3103	android/util/DisplayMetrics
    //   623: dup
    //   624: invokespecial 3104	android/util/DisplayMetrics:<init>	()V
    //   627: astore 6
    //   629: aload_1
    //   630: invokeinterface 3092 1 0
    //   635: aload 6
    //   637: invokevirtual 3108	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   640: aload_0
    //   641: aload 6
    //   643: getfield 3111	android/util/DisplayMetrics:heightPixels	I
    //   646: putfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   649: aload_0
    //   650: aload 6
    //   652: getfield 3114	android/util/DisplayMetrics:widthPixels	I
    //   655: putfield 2664	mbinc12/mb32b/MainPage:R	I
    //   658: aload_0
    //   659: getfield 2664	mbinc12/mb32b/MainPage:R	I
    //   662: aload_0
    //   663: getfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   666: if_icmple +1713 -> 2379
    //   669: iload_2
    //   670: ifeq +1709 -> 2379
    //   673: iconst_1
    //   674: putstatic 3084	mbinc12/mb32b/MainPage:au	Z
    //   677: aload_0
    //   678: getfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   681: aload_0
    //   682: getfield 2664	mbinc12/mb32b/MainPage:R	I
    //   685: if_icmple +21 -> 706
    //   688: aload_0
    //   689: getfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   692: istore_2
    //   693: aload_0
    //   694: aload_0
    //   695: getfield 2664	mbinc12/mb32b/MainPage:R	I
    //   698: putfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   701: aload_0
    //   702: iload_2
    //   703: putfield 2664	mbinc12/mb32b/MainPage:R	I
    //   706: aload_0
    //   707: aload_0
    //   708: ldc_w 3116
    //   711: invokevirtual 1264	mbinc12/mb32b/MainPage:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   714: checkcast 3118	android/app/NotificationManager
    //   717: putfield 3120	mbinc12/mb32b/MainPage:V	Landroid/app/NotificationManager;
    //   720: aload_0
    //   721: getfield 3120	mbinc12/mb32b/MainPage:V	Landroid/app/NotificationManager;
    //   724: sipush 12161
    //   727: invokevirtual 3123	android/app/NotificationManager:cancel	(I)V
    //   730: invokestatic 3124	vf:g	()V
    //   733: aload_0
    //   734: iconst_0
    //   735: putfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   738: aload_0
    //   739: aload_0
    //   740: invokestatic 3128	asa:ai	(Landroid/content/Context;)Ljava/lang/String;
    //   743: putfield 409	mbinc12/mb32b/MainPage:aB	Ljava/lang/String;
    //   746: aload_0
    //   747: ldc_w 3130
    //   750: invokevirtual 3134	mbinc12/mb32b/MainPage:getDatabasePath	(Ljava/lang/String;)Ljava/io/File;
    //   753: astore 6
    //   755: aload 6
    //   757: invokevirtual 3139	java/io/File:exists	()Z
    //   760: ifne +2117 -> 2877
    //   763: iconst_0
    //   764: istore_2
    //   765: aload_0
    //   766: new 1421	arz
    //   769: dup
    //   770: aload_0
    //   771: invokespecial 3140	arz:<init>	(Landroid/content/Context;)V
    //   774: putfield 403	mbinc12/mb32b/MainPage:an	Larz;
    //   777: iload_2
    //   778: ifne +137 -> 915
    //   781: aload_0
    //   782: invokespecial 667	mbinc12/mb32b/MainPage:W	()Z
    //   785: ifeq +12 -> 797
    //   788: aload_0
    //   789: invokespecial 3142	mbinc12/mb32b/MainPage:X	()V
    //   792: aload_0
    //   793: iconst_1
    //   794: putfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   797: invokestatic 3148	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   800: astore 7
    //   802: aload 7
    //   804: invokevirtual 3151	java/io/File:canWrite	()Z
    //   807: ifeq +108 -> 915
    //   810: new 3136	java/io/File
    //   813: dup
    //   814: aload 7
    //   816: ldc_w 3153
    //   819: invokespecial 3156	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   822: astore_1
    //   823: aload_1
    //   824: invokevirtual 3139	java/io/File:exists	()Z
    //   827: ifeq +1618 -> 2445
    //   830: iconst_1
    //   831: istore_2
    //   832: iload_2
    //   833: ifeq +82 -> 915
    //   836: aload_0
    //   837: invokespecial 3142	mbinc12/mb32b/MainPage:X	()V
    //   840: aload_0
    //   841: getfield 403	mbinc12/mb32b/MainPage:an	Larz;
    //   844: invokevirtual 3157	arz:close	()V
    //   847: new 3159	java/io/FileInputStream
    //   850: dup
    //   851: aload_1
    //   852: invokespecial 3162	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   855: invokevirtual 3166	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   858: astore 7
    //   860: new 3168	java/io/FileOutputStream
    //   863: dup
    //   864: aload 6
    //   866: invokespecial 3169	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   869: invokevirtual 3170	java/io/FileOutputStream:getChannel	()Ljava/nio/channels/FileChannel;
    //   872: astore 6
    //   874: aload 6
    //   876: aload 7
    //   878: lconst_0
    //   879: aload 7
    //   881: invokevirtual 3174	java/nio/channels/FileChannel:size	()J
    //   884: invokevirtual 3178	java/nio/channels/FileChannel:transferFrom	(Ljava/nio/channels/ReadableByteChannel;JJ)J
    //   887: pop2
    //   888: aload 7
    //   890: invokevirtual 3179	java/nio/channels/FileChannel:close	()V
    //   893: aload 6
    //   895: invokevirtual 3179	java/nio/channels/FileChannel:close	()V
    //   898: aload_0
    //   899: new 1421	arz
    //   902: dup
    //   903: aload_0
    //   904: invokespecial 3140	arz:<init>	(Landroid/content/Context;)V
    //   907: putfield 403	mbinc12/mb32b/MainPage:an	Larz;
    //   910: aload_1
    //   911: invokevirtual 3182	java/io/File:delete	()Z
    //   914: pop
    //   915: aload_0
    //   916: getfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   919: ifeq +1660 -> 2579
    //   922: aload_0
    //   923: iconst_0
    //   924: putfield 2402	mbinc12/mb32b/MainPage:ax	Z
    //   927: aload_0
    //   928: getfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   931: ifne +7 -> 938
    //   934: aload_0
    //   935: invokevirtual 3183	mbinc12/mb32b/MainPage:c	()V
    //   938: aload_0
    //   939: new 192	mbinc12/mb32b/MainPage$e
    //   942: dup
    //   943: aload_0
    //   944: invokespecial 3184	mbinc12/mb32b/MainPage$e:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   947: putfield 1589	mbinc12/mb32b/MainPage:d	Lmbinc12/mb32b/MainPage$e;
    //   950: aload_0
    //   951: iconst_0
    //   952: putfield 2415	mbinc12/mb32b/MainPage:aU	I
    //   955: aload_0
    //   956: iconst_0
    //   957: putfield 2417	mbinc12/mb32b/MainPage:aW	Z
    //   960: aload_0
    //   961: iconst_m1
    //   962: putfield 2627	mbinc12/mb32b/MainPage:aV	I
    //   965: ldc_w 3186
    //   968: aconst_null
    //   969: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   972: aload_0
    //   973: ldc_w 3188
    //   976: invokevirtual 1264	mbinc12/mb32b/MainPage:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   979: checkcast 3190	android/net/ConnectivityManager
    //   982: invokevirtual 3194	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   985: astore_1
    //   986: new 1552	java/util/HashMap
    //   989: dup
    //   990: invokespecial 1553	java/util/HashMap:<init>	()V
    //   993: astore 6
    //   995: aload_1
    //   996: ifnull +1632 -> 2628
    //   999: aload_1
    //   1000: invokevirtual 3199	android/net/NetworkInfo:isConnected	()Z
    //   1003: ifeq +1625 -> 2628
    //   1006: aload_1
    //   1007: invokevirtual 3202	android/net/NetworkInfo:getType	()I
    //   1010: iconst_1
    //   1011: if_icmpne +1576 -> 2587
    //   1014: aload 6
    //   1016: ldc_w 3204
    //   1019: ldc_w 3206
    //   1022: invokeinterface 1560 3 0
    //   1027: pop
    //   1028: ldc_w 3208
    //   1031: aload 6
    //   1033: invokestatic 731	mbinc12/mb32b/utils/MixerBoxUtils:a	(Ljava/lang/String;Ljava/util/Map;)V
    //   1036: aload_0
    //   1037: ldc_w 3209
    //   1040: putfield 1584	mbinc12/mb32b/MainPage:h	I
    //   1043: aload_0
    //   1044: iconst_0
    //   1045: putfield 2677	mbinc12/mb32b/MainPage:ac	Z
    //   1048: aload_0
    //   1049: iconst_0
    //   1050: putfield 2429	mbinc12/mb32b/MainPage:ad	Z
    //   1053: aload_0
    //   1054: iconst_0
    //   1055: putfield 1017	mbinc12/mb32b/MainPage:ae	Z
    //   1058: aload_0
    //   1059: iconst_0
    //   1060: putfield 2743	mbinc12/mb32b/MainPage:af	I
    //   1063: aload_0
    //   1064: iconst_0
    //   1065: putfield 1007	mbinc12/mb32b/MainPage:ap	Z
    //   1068: iconst_0
    //   1069: putstatic 1005	mbinc12/mb32b/MainPage:aq	Z
    //   1072: aload_0
    //   1073: iconst_0
    //   1074: putfield 1670	mbinc12/mb32b/MainPage:ar	Z
    //   1077: aload_0
    //   1078: iconst_0
    //   1079: putfield 399	mbinc12/mb32b/MainPage:ak	I
    //   1082: aload_0
    //   1083: iconst_1
    //   1084: putfield 2759	mbinc12/mb32b/MainPage:av	Z
    //   1087: aload_0
    //   1088: iconst_0
    //   1089: putfield 1593	mbinc12/mb32b/MainPage:az	I
    //   1092: aload_0
    //   1093: new 776	java/util/ArrayList
    //   1096: dup
    //   1097: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1100: putfield 3211	mbinc12/mb32b/MainPage:B	Ljava/util/List;
    //   1103: aload_0
    //   1104: new 776	java/util/ArrayList
    //   1107: dup
    //   1108: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1111: putfield 3213	mbinc12/mb32b/MainPage:D	Ljava/util/List;
    //   1114: aload_0
    //   1115: new 776	java/util/ArrayList
    //   1118: dup
    //   1119: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1122: putfield 1041	mbinc12/mb32b/MainPage:C	Ljava/util/List;
    //   1125: aload_0
    //   1126: new 776	java/util/ArrayList
    //   1129: dup
    //   1130: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1133: putfield 774	mbinc12/mb32b/MainPage:E	Ljava/util/ArrayList;
    //   1136: aload_0
    //   1137: new 776	java/util/ArrayList
    //   1140: dup
    //   1141: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1144: putfield 3215	mbinc12/mb32b/MainPage:A	Ljava/util/List;
    //   1147: aload_0
    //   1148: new 776	java/util/ArrayList
    //   1151: dup
    //   1152: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1155: putfield 1049	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
    //   1158: aload_0
    //   1159: new 776	java/util/ArrayList
    //   1162: dup
    //   1163: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   1166: putfield 1047	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
    //   1169: aload_0
    //   1170: getfield 401	mbinc12/mb32b/MainPage:am	Z
    //   1173: ifeq +65 -> 1238
    //   1176: aload_0
    //   1177: invokestatic 3217	asa:aw	(Landroid/content/Context;)Ljava/lang/String;
    //   1180: astore_1
    //   1181: new 609	org/json/JSONObject
    //   1184: dup
    //   1185: aload_1
    //   1186: invokespecial 1052	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1189: astore_1
    //   1190: aload_1
    //   1191: ldc_w 1077
    //   1194: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1197: ifne +1448 -> 2645
    //   1200: aload_0
    //   1201: aload_1
    //   1202: ldc_w 1077
    //   1205: invokevirtual 1058	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1208: invokestatic 3219	mbinc12/mb32b/MainPage:a	(Lorg/json/JSONArray;)Ljava/util/ArrayList;
    //   1211: putfield 1047	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
    //   1214: aload_1
    //   1215: ldc_w 3221
    //   1218: invokevirtual 1083	org/json/JSONObject:isNull	(Ljava/lang/String;)Z
    //   1221: ifne +1464 -> 2685
    //   1224: aload_0
    //   1225: aload_1
    //   1226: ldc_w 3221
    //   1229: invokevirtual 1058	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1232: invokestatic 3219	mbinc12/mb32b/MainPage:a	(Lorg/json/JSONArray;)Ljava/util/ArrayList;
    //   1235: putfield 1049	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
    //   1238: aload_0
    //   1239: aload_0
    //   1240: ldc_w 3222
    //   1243: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1246: checkcast 537	android/widget/RelativeLayout
    //   1249: putfield 966	mbinc12/mb32b/MainPage:n	Landroid/widget/RelativeLayout;
    //   1252: aload_0
    //   1253: aload_0
    //   1254: ldc_w 3223
    //   1257: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1260: checkcast 537	android/widget/RelativeLayout
    //   1263: putfield 968	mbinc12/mb32b/MainPage:r	Landroid/widget/RelativeLayout;
    //   1266: aload_0
    //   1267: aload_0
    //   1268: ldc_w 3224
    //   1271: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1274: checkcast 537	android/widget/RelativeLayout
    //   1277: putfield 970	mbinc12/mb32b/MainPage:o	Landroid/widget/RelativeLayout;
    //   1280: aload_0
    //   1281: aload_0
    //   1282: ldc_w 3225
    //   1285: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1288: checkcast 537	android/widget/RelativeLayout
    //   1291: putfield 972	mbinc12/mb32b/MainPage:p	Landroid/widget/RelativeLayout;
    //   1294: aload_0
    //   1295: aload_0
    //   1296: ldc_w 3226
    //   1299: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1302: checkcast 537	android/widget/RelativeLayout
    //   1305: putfield 944	mbinc12/mb32b/MainPage:q	Landroid/widget/RelativeLayout;
    //   1308: aload_0
    //   1309: aload_0
    //   1310: ldc_w 3227
    //   1313: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1316: checkcast 545	android/widget/TextView
    //   1319: putfield 2488	mbinc12/mb32b/MainPage:c	Landroid/widget/TextView;
    //   1322: aload_0
    //   1323: aload_0
    //   1324: ldc_w 3228
    //   1327: invokevirtual 1975	mbinc12/mb32b/MainPage:findViewById	(I)Landroid/view/View;
    //   1330: checkcast 541	android/widget/ImageView
    //   1333: putfield 2729	mbinc12/mb32b/MainPage:k	Landroid/widget/ImageView;
    //   1336: aload_0
    //   1337: new 1479	java/util/Stack
    //   1340: dup
    //   1341: invokespecial 3229	java/util/Stack:<init>	()V
    //   1344: putfield 1477	mbinc12/mb32b/MainPage:m	Ljava/util/Stack;
    //   1347: new 1479	java/util/Stack
    //   1350: dup
    //   1351: invokespecial 3229	java/util/Stack:<init>	()V
    //   1354: putstatic 1948	mbinc12/mb32b/MainPage:K	Ljava/util/Stack;
    //   1357: aload_0
    //   1358: aload_0
    //   1359: invokevirtual 3233	mbinc12/mb32b/MainPage:getSupportFragmentManager	()Landroid/support/v4/app/FragmentManager;
    //   1362: putfield 1908	mbinc12/mb32b/MainPage:l	Landroid/support/v4/app/FragmentManager;
    //   1365: aload_0
    //   1366: new 1514	ari
    //   1369: dup
    //   1370: invokespecial 3234	ari:<init>	()V
    //   1373: putfield 1512	mbinc12/mb32b/MainPage:t	Lari;
    //   1376: aload_0
    //   1377: new 1507	arh
    //   1380: dup
    //   1381: invokespecial 3235	arh:<init>	()V
    //   1384: putfield 1505	mbinc12/mb32b/MainPage:u	Larh;
    //   1387: aload_0
    //   1388: new 1500	ard
    //   1391: dup
    //   1392: invokespecial 3236	ard:<init>	()V
    //   1395: putfield 908	mbinc12/mb32b/MainPage:v	Lard;
    //   1398: aload_0
    //   1399: new 1492	arc
    //   1402: dup
    //   1403: invokespecial 3237	arc:<init>	()V
    //   1406: putfield 1490	mbinc12/mb32b/MainPage:w	Larc;
    //   1409: aload_0
    //   1410: new 3239	arf
    //   1413: dup
    //   1414: invokespecial 3240	arf:<init>	()V
    //   1417: putfield 3242	mbinc12/mb32b/MainPage:x	Larf;
    //   1420: aload_0
    //   1421: new 3244	arj
    //   1424: dup
    //   1425: invokespecial 3245	arj:<init>	()V
    //   1428: putfield 3247	mbinc12/mb32b/MainPage:y	Larj;
    //   1431: aload_0
    //   1432: invokevirtual 1815	mbinc12/mb32b/MainPage:C	()Z
    //   1435: ifeq +12 -> 1447
    //   1438: aload_0
    //   1439: aload_0
    //   1440: getfield 3247	mbinc12/mb32b/MainPage:y	Larj;
    //   1443: iconst_0
    //   1444: invokevirtual 1823	mbinc12/mb32b/MainPage:a	(Landroid/support/v4/app/Fragment;Z)V
    //   1447: aload_0
    //   1448: getfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   1451: ifne +7 -> 1458
    //   1454: aload_0
    //   1455: invokespecial 2897	mbinc12/mb32b/MainPage:L	()V
    //   1458: aload_0
    //   1459: getfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   1462: ifne +1237 -> 2699
    //   1465: aload_0
    //   1466: invokestatic 923	arx:a	(Landroid/content/Context;)Z
    //   1469: ifeq +1230 -> 2699
    //   1472: aload_0
    //   1473: invokestatic 3249	asa:x	(Landroid/content/Context;)Z
    //   1476: ifne +1223 -> 2699
    //   1479: aload_0
    //   1480: invokevirtual 2965	mbinc12/mb32b/MainPage:getIntent	()Landroid/content/Intent;
    //   1483: invokevirtual 2969	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   1486: ifnull +77 -> 1563
    //   1489: aload_0
    //   1490: invokevirtual 2965	mbinc12/mb32b/MainPage:getIntent	()Landroid/content/Intent;
    //   1493: invokevirtual 2969	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   1496: ldc_w 3251
    //   1499: invokevirtual 3255	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   1502: ifnull +61 -> 1563
    //   1505: aload_0
    //   1506: invokevirtual 2965	mbinc12/mb32b/MainPage:getIntent	()Landroid/content/Intent;
    //   1509: invokevirtual 2969	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   1512: ldc_w 3251
    //   1515: invokevirtual 3255	android/os/Bundle:getSerializable	(Ljava/lang/String;)Ljava/io/Serializable;
    //   1518: checkcast 1552	java/util/HashMap
    //   1521: astore_1
    //   1522: aload_1
    //   1523: ifnull +40 -> 1563
    //   1526: aload_1
    //   1527: invokevirtual 3256	java/util/HashMap:size	()I
    //   1530: ifle +33 -> 1563
    //   1533: aload_0
    //   1534: new 2352	java/lang/Thread
    //   1537: dup
    //   1538: new 195	mbinc12/mb32b/MainPage$f
    //   1541: dup
    //   1542: aload_0
    //   1543: aload_1
    //   1544: ldc_w 3258
    //   1547: invokespecial 3261	mbinc12/mb32b/MainPage$f:<init>	(Lmbinc12/mb32b/MainPage;Ljava/util/HashMap;Ljava/lang/String;)V
    //   1550: invokespecial 2358	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   1553: putfield 431	mbinc12/mb32b/MainPage:bo	Ljava/lang/Thread;
    //   1556: aload_0
    //   1557: getfield 431	mbinc12/mb32b/MainPage:bo	Ljava/lang/Thread;
    //   1560: invokevirtual 2361	java/lang/Thread:start	()V
    //   1563: aload_0
    //   1564: invokevirtual 848	mbinc12/mb32b/MainPage:w	()V
    //   1567: aload_0
    //   1568: invokestatic 3263	asa:I	(Landroid/content/Context;)V
    //   1571: aload_0
    //   1572: ldc_w 1582
    //   1575: invokespecial 1568	mbinc12/mb32b/MainPage:o	(Ljava/lang/String;)V
    //   1578: aload_0
    //   1579: getfield 401	mbinc12/mb32b/MainPage:am	Z
    //   1582: ifne +7 -> 1589
    //   1585: aload_0
    //   1586: invokespecial 3265	mbinc12/mb32b/MainPage:U	()V
    //   1589: aload_0
    //   1590: getfield 966	mbinc12/mb32b/MainPage:n	Landroid/widget/RelativeLayout;
    //   1593: new 16	mbinc12/mb32b/MainPage$12
    //   1596: dup
    //   1597: aload_0
    //   1598: invokespecial 3266	mbinc12/mb32b/MainPage$12:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   1601: invokevirtual 592	android/widget/RelativeLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1604: aload_0
    //   1605: getfield 968	mbinc12/mb32b/MainPage:r	Landroid/widget/RelativeLayout;
    //   1608: new 38	mbinc12/mb32b/MainPage$22
    //   1611: dup
    //   1612: aload_0
    //   1613: invokespecial 3267	mbinc12/mb32b/MainPage$22:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   1616: invokevirtual 592	android/widget/RelativeLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1619: aload_0
    //   1620: getfield 970	mbinc12/mb32b/MainPage:o	Landroid/widget/RelativeLayout;
    //   1623: new 62	mbinc12/mb32b/MainPage$33
    //   1626: dup
    //   1627: aload_0
    //   1628: invokespecial 3268	mbinc12/mb32b/MainPage$33:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   1631: invokevirtual 592	android/widget/RelativeLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1634: aload_0
    //   1635: getfield 972	mbinc12/mb32b/MainPage:p	Landroid/widget/RelativeLayout;
    //   1638: new 98	mbinc12/mb32b/MainPage$44
    //   1641: dup
    //   1642: aload_0
    //   1643: invokespecial 3269	mbinc12/mb32b/MainPage$44:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   1646: invokevirtual 592	android/widget/RelativeLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1649: aload_0
    //   1650: getfield 944	mbinc12/mb32b/MainPage:q	Landroid/widget/RelativeLayout;
    //   1653: new 112	mbinc12/mb32b/MainPage$50
    //   1656: dup
    //   1657: aload_0
    //   1658: invokespecial 3270	mbinc12/mb32b/MainPage$50:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   1661: invokevirtual 592	android/widget/RelativeLayout:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1664: aload_0
    //   1665: invokestatic 3272	asa:V	(Landroid/content/Context;)Ljava/lang/String;
    //   1668: putstatic 376	mbinc12/mb32b/MainPage:aT	Ljava/lang/String;
    //   1671: aload_0
    //   1672: aload_0
    //   1673: invokestatic 3274	asa:T	(Landroid/content/Context;)Z
    //   1676: putfield 1099	mbinc12/mb32b/MainPage:be	Z
    //   1679: new 3276	android/content/IntentFilter
    //   1682: dup
    //   1683: ldc_w 395
    //   1686: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1689: astore_1
    //   1690: aload_0
    //   1691: new 3279	mbinc12/mb32b/services/PlayerReceiver
    //   1694: dup
    //   1695: invokespecial 3280	mbinc12/mb32b/services/PlayerReceiver:<init>	()V
    //   1698: putfield 3282	mbinc12/mb32b/MainPage:X	Lmbinc12/mb32b/services/PlayerReceiver;
    //   1701: aload_0
    //   1702: aload_0
    //   1703: getfield 3282	mbinc12/mb32b/MainPage:X	Lmbinc12/mb32b/services/PlayerReceiver;
    //   1706: aload_1
    //   1707: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1710: pop
    //   1711: new 3276	android/content/IntentFilter
    //   1714: dup
    //   1715: ldc_w 3288
    //   1718: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1721: astore_1
    //   1722: aload_0
    //   1723: new 3290	mbinc12/mb32b/services/SystemDialogReceiver
    //   1726: dup
    //   1727: invokespecial 3291	mbinc12/mb32b/services/SystemDialogReceiver:<init>	()V
    //   1730: putfield 3293	mbinc12/mb32b/MainPage:ab	Lmbinc12/mb32b/services/SystemDialogReceiver;
    //   1733: aload_0
    //   1734: aload_0
    //   1735: getfield 3293	mbinc12/mb32b/MainPage:ab	Lmbinc12/mb32b/services/SystemDialogReceiver;
    //   1738: aload_1
    //   1739: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1742: pop
    //   1743: new 3276	android/content/IntentFilter
    //   1746: dup
    //   1747: ldc_w 3295
    //   1750: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1753: astore_1
    //   1754: aload_0
    //   1755: new 3297	mbinc12/mb32b/services/SleepReceiver
    //   1758: dup
    //   1759: invokespecial 3298	mbinc12/mb32b/services/SleepReceiver:<init>	()V
    //   1762: putfield 3300	mbinc12/mb32b/MainPage:Y	Lmbinc12/mb32b/services/SleepReceiver;
    //   1765: aload_0
    //   1766: aload_0
    //   1767: getfield 3300	mbinc12/mb32b/MainPage:Y	Lmbinc12/mb32b/services/SleepReceiver;
    //   1770: aload_1
    //   1771: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1774: pop
    //   1775: aload_0
    //   1776: new 189	mbinc12/mb32b/MainPage$d
    //   1779: dup
    //   1780: aload_0
    //   1781: iconst_0
    //   1782: invokespecial 3303	mbinc12/mb32b/MainPage$d:<init>	(Lmbinc12/mb32b/MainPage;B)V
    //   1785: putfield 3305	mbinc12/mb32b/MainPage:ba	Lmbinc12/mb32b/MainPage$d;
    //   1788: new 3276	android/content/IntentFilter
    //   1791: dup
    //   1792: ldc_w 3307
    //   1795: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1798: astore_1
    //   1799: aload_0
    //   1800: aload_0
    //   1801: getfield 3305	mbinc12/mb32b/MainPage:ba	Lmbinc12/mb32b/MainPage$d;
    //   1804: aload_1
    //   1805: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1808: pop
    //   1809: aload_0
    //   1810: new 3309	mbinc12/mb32b/services/EarphoneReceiver
    //   1813: dup
    //   1814: invokespecial 3310	mbinc12/mb32b/services/EarphoneReceiver:<init>	()V
    //   1817: putfield 3312	mbinc12/mb32b/MainPage:ag	Lmbinc12/mb32b/services/EarphoneReceiver;
    //   1820: aload_0
    //   1821: aload_0
    //   1822: getfield 3312	mbinc12/mb32b/MainPage:ag	Lmbinc12/mb32b/services/EarphoneReceiver;
    //   1825: new 3276	android/content/IntentFilter
    //   1828: dup
    //   1829: ldc_w 3314
    //   1832: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1835: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1838: pop
    //   1839: new 3276	android/content/IntentFilter
    //   1842: dup
    //   1843: ldc_w 3316
    //   1846: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1849: astore_1
    //   1850: aload_0
    //   1851: new 114	mbinc12/mb32b/MainPage$51
    //   1854: dup
    //   1855: aload_0
    //   1856: invokespecial 3317	mbinc12/mb32b/MainPage$51:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   1859: putfield 3319	mbinc12/mb32b/MainPage:ah	Landroid/content/BroadcastReceiver;
    //   1862: aload_0
    //   1863: aload_0
    //   1864: getfield 3319	mbinc12/mb32b/MainPage:ah	Landroid/content/BroadcastReceiver;
    //   1867: aload_1
    //   1868: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1871: pop
    //   1872: aload_0
    //   1873: aload_0
    //   1874: ldc_w 1897
    //   1877: invokevirtual 1264	mbinc12/mb32b/MainPage:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1880: checkcast 1899	android/media/AudioManager
    //   1883: putfield 3321	mbinc12/mb32b/MainPage:aj	Landroid/media/AudioManager;
    //   1886: aload_0
    //   1887: getfield 3321	mbinc12/mb32b/MainPage:aj	Landroid/media/AudioManager;
    //   1890: new 3323	android/content/ComponentName
    //   1893: dup
    //   1894: aload_0
    //   1895: ldc_w 3325
    //   1898: invokespecial 3326	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1901: invokevirtual 3330	android/media/AudioManager:registerMediaButtonEventReceiver	(Landroid/content/ComponentName;)V
    //   1904: new 3276	android/content/IntentFilter
    //   1907: dup
    //   1908: ldc_w 3332
    //   1911: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1914: astore_1
    //   1915: aload_0
    //   1916: new 3334	mbinc12/mb32b/services/MyReceiver
    //   1919: dup
    //   1920: invokespecial 3335	mbinc12/mb32b/services/MyReceiver:<init>	()V
    //   1923: putfield 3337	mbinc12/mb32b/MainPage:ai	Lmbinc12/mb32b/services/MyReceiver;
    //   1926: aload_0
    //   1927: aload_0
    //   1928: getfield 3337	mbinc12/mb32b/MainPage:ai	Lmbinc12/mb32b/services/MyReceiver;
    //   1931: aload_1
    //   1932: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1935: pop
    //   1936: aload_0
    //   1937: invokestatic 1890	asa:H	(Landroid/content/Context;)I
    //   1940: iconst_3
    //   1941: if_icmpeq +49 -> 1990
    //   1944: new 3276	android/content/IntentFilter
    //   1947: dup
    //   1948: ldc_w 3339
    //   1951: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   1954: astore_1
    //   1955: aload_1
    //   1956: ldc_w 3341
    //   1959: invokevirtual 3344	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1962: aload_1
    //   1963: ldc_w 3346
    //   1966: invokevirtual 3344	android/content/IntentFilter:addAction	(Ljava/lang/String;)V
    //   1969: aload_0
    //   1970: new 1894	mbinc12/mb32b/services/ScreenReceiver
    //   1973: dup
    //   1974: invokespecial 3347	mbinc12/mb32b/services/ScreenReceiver:<init>	()V
    //   1977: putfield 3349	mbinc12/mb32b/MainPage:Z	Lmbinc12/mb32b/services/ScreenReceiver;
    //   1980: aload_0
    //   1981: aload_0
    //   1982: getfield 3349	mbinc12/mb32b/MainPage:Z	Lmbinc12/mb32b/services/ScreenReceiver;
    //   1985: aload_1
    //   1986: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   1989: pop
    //   1990: new 3276	android/content/IntentFilter
    //   1993: dup
    //   1994: ldc_w 3351
    //   1997: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   2000: astore_1
    //   2001: aload_0
    //   2002: new 3353	mbinc12/mb32b/services/ConnectionChangeReceiver
    //   2005: dup
    //   2006: invokespecial 3354	mbinc12/mb32b/services/ConnectionChangeReceiver:<init>	()V
    //   2009: putfield 3356	mbinc12/mb32b/MainPage:aa	Lmbinc12/mb32b/services/ConnectionChangeReceiver;
    //   2012: aload_0
    //   2013: aload_0
    //   2014: getfield 3356	mbinc12/mb32b/MainPage:aa	Lmbinc12/mb32b/services/ConnectionChangeReceiver;
    //   2017: aload_1
    //   2018: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   2021: pop
    //   2022: aload_0
    //   2023: invokestatic 3358	asa:n	(Landroid/content/Context;)I
    //   2026: putstatic 378	mbinc12/mb32b/MainPage:j	I
    //   2029: aload_0
    //   2030: invokestatic 3360	asa:aC	(Landroid/content/Context;)I
    //   2033: istore_2
    //   2034: aload_0
    //   2035: invokestatic 3362	asa:aD	(Landroid/content/Context;)I
    //   2038: istore_3
    //   2039: aload_0
    //   2040: invokestatic 3364	asa:bd	(Landroid/content/Context;)I
    //   2043: istore 4
    //   2045: iload 4
    //   2047: sipush 591
    //   2050: if_icmpge +46 -> 2096
    //   2053: aload_0
    //   2054: invokestatic 808	asa:l	(Landroid/content/Context;)I
    //   2057: istore 5
    //   2059: iload 5
    //   2061: iload_2
    //   2062: if_icmpeq +18 -> 2080
    //   2065: iload 5
    //   2067: iload_2
    //   2068: if_icmple +28 -> 2096
    //   2071: iload 5
    //   2073: iload_2
    //   2074: isub
    //   2075: iload_3
    //   2076: irem
    //   2077: ifne +19 -> 2096
    //   2080: aload_0
    //   2081: ldc_w 3366
    //   2084: invokestatic 1013	mbinc12/mb32b/utils/MixerBoxUtils:f	(Landroid/content/Context;Ljava/lang/String;)V
    //   2087: aload_0
    //   2088: iload 4
    //   2090: invokestatic 3369	aqt:a	(Landroid/app/Activity;I)Landroid/app/AlertDialog;
    //   2093: invokevirtual 626	android/app/Dialog:show	()V
    //   2096: getstatic 812	mbinc12/mb32b/MyApplication:b	Z
    //   2099: ifeq +7 -> 2106
    //   2102: aload_0
    //   2103: invokestatic 3371	tl:a	(Landroid/app/Activity;)V
    //   2106: new 2352	java/lang/Thread
    //   2109: dup
    //   2110: aload_0
    //   2111: getfield 485	mbinc12/mb32b/MainPage:aS	Ljava/lang/Runnable;
    //   2114: invokespecial 2358	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   2117: invokevirtual 2361	java/lang/Thread:start	()V
    //   2120: aload_0
    //   2121: aload_0
    //   2122: getfield 409	mbinc12/mb32b/MainPage:aB	Ljava/lang/String;
    //   2125: invokestatic 3373	mbinc12/mb32b/utils/MixerBoxUtils:g	(Landroid/content/Context;Ljava/lang/String;)V
    //   2128: new 3276	android/content/IntentFilter
    //   2131: dup
    //   2132: ldc_w 3375
    //   2135: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   2138: astore_1
    //   2139: aload_0
    //   2140: new 32	mbinc12/mb32b/MainPage$2
    //   2143: dup
    //   2144: aload_0
    //   2145: invokespecial 3376	mbinc12/mb32b/MainPage$2:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   2148: putfield 3378	mbinc12/mb32b/MainPage:bc	Landroid/content/BroadcastReceiver;
    //   2151: aload_0
    //   2152: aload_0
    //   2153: getfield 3378	mbinc12/mb32b/MainPage:bc	Landroid/content/BroadcastReceiver;
    //   2156: aload_1
    //   2157: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   2160: pop
    //   2161: new 1350	android/content/Intent
    //   2164: dup
    //   2165: aload_0
    //   2166: ldc_w 3380
    //   2169: invokespecial 2451	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   2172: astore_1
    //   2173: aload_1
    //   2174: ldc_w 3382
    //   2177: aload_0
    //   2178: getfield 401	mbinc12/mb32b/MainPage:am	Z
    //   2181: invokevirtual 3385	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   2184: pop
    //   2185: aload_0
    //   2186: aload_1
    //   2187: invokevirtual 2455	mbinc12/mb32b/MainPage:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   2190: pop
    //   2191: new 3276	android/content/IntentFilter
    //   2194: dup
    //   2195: ldc_w 3316
    //   2198: invokespecial 3277	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   2201: astore_1
    //   2202: aload_0
    //   2203: new 118	mbinc12/mb32b/MainPage$53
    //   2206: dup
    //   2207: aload_0
    //   2208: invokespecial 3386	mbinc12/mb32b/MainPage$53:<init>	(Lmbinc12/mb32b/MainPage;)V
    //   2211: putfield 3388	mbinc12/mb32b/MainPage:bb	Landroid/content/BroadcastReceiver;
    //   2214: aload_0
    //   2215: aload_0
    //   2216: getfield 3388	mbinc12/mb32b/MainPage:bb	Landroid/content/BroadcastReceiver;
    //   2219: aload_1
    //   2220: invokevirtual 3286	mbinc12/mb32b/MainPage:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   2223: pop
    //   2224: aload_0
    //   2225: invokestatic 3389	asc:h	(Landroid/content/Context;)V
    //   2228: aload_0
    //   2229: getfield 413	mbinc12/mb32b/MainPage:aD	Z
    //   2232: ifeq +38 -> 2270
    //   2235: aload_0
    //   2236: invokestatic 2035	asa:aJ	(Landroid/content/Context;)Z
    //   2239: ifne +31 -> 2270
    //   2242: aload_0
    //   2243: invokevirtual 650	mbinc12/mb32b/MainPage:getResources	()Landroid/content/res/Resources;
    //   2246: ldc_w 3390
    //   2249: invokevirtual 657	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   2252: astore_1
    //   2253: aload_1
    //   2254: invokestatic 3392	arx:g	(Ljava/lang/String;)Ljava/lang/String;
    //   2257: new 12	mbinc12/mb32b/MainPage$10
    //   2260: dup
    //   2261: aload_0
    //   2262: aload_0
    //   2263: aload_1
    //   2264: invokespecial 3393	mbinc12/mb32b/MainPage$10:<init>	(Lmbinc12/mb32b/MainPage;Landroid/content/Context;Ljava/lang/String;)V
    //   2267: invokestatic 1295	arx:a	(Ljava/lang/String;Lcom/loopj/android/http/AsyncHttpResponseHandler;)V
    //   2270: aload_0
    //   2271: getfield 421	mbinc12/mb32b/MainPage:bl	Lapg;
    //   2274: ifnonnull +15 -> 2289
    //   2277: aload_0
    //   2278: invokestatic 3395	asa:aM	(Landroid/content/Context;)Z
    //   2281: ifne +474 -> 2755
    //   2284: aload_0
    //   2285: aconst_null
    //   2286: putfield 421	mbinc12/mb32b/MainPage:bl	Lapg;
    //   2289: invokestatic 3396	arw:a	()Z
    //   2292: ifeq +15 -> 2307
    //   2295: aload_0
    //   2296: iconst_0
    //   2297: ldc_w 3398
    //   2300: iconst_0
    //   2301: anewarray 433	java/lang/String
    //   2304: invokestatic 2920	arw:a	(Landroid/content/Context;ZLjava/lang/String;[Ljava/lang/String;)V
    //   2307: ldc_w 3400
    //   2310: invokestatic 3042	mbinc12/mb32b/utils/MixerBoxUtils:b	(Ljava/lang/String;)V
    //   2313: return
    //   2314: astore_1
    //   2315: new 1350	android/content/Intent
    //   2318: dup
    //   2319: aload_0
    //   2320: ldc_w 2978
    //   2323: invokespecial 2451	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   2326: astore_1
    //   2327: aload_1
    //   2328: ldc_w 2979
    //   2331: invokevirtual 2983	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   2334: pop
    //   2335: aload_0
    //   2336: aload_1
    //   2337: invokevirtual 2986	mbinc12/mb32b/MainPage:startActivity	(Landroid/content/Intent;)V
    //   2340: aload_0
    //   2341: invokevirtual 2946	mbinc12/mb32b/MainPage:finish	()V
    //   2344: return
    //   2345: aload_0
    //   2346: iconst_1
    //   2347: putfield 413	mbinc12/mb32b/MainPage:aD	Z
    //   2350: goto -1963 -> 387
    //   2353: aload_1
    //   2354: invokeinterface 3092 1 0
    //   2359: astore_1
    //   2360: aload_0
    //   2361: aload_1
    //   2362: invokevirtual 3403	android/view/Display:getHeight	()I
    //   2365: putfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   2368: aload_0
    //   2369: aload_1
    //   2370: invokevirtual 3406	android/view/Display:getWidth	()I
    //   2373: putfield 2664	mbinc12/mb32b/MainPage:R	I
    //   2376: goto -1718 -> 658
    //   2379: aload_0
    //   2380: getfield 2664	mbinc12/mb32b/MainPage:R	I
    //   2383: aload_0
    //   2384: getfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   2387: if_icmple +14 -> 2401
    //   2390: iload_2
    //   2391: ifne +10 -> 2401
    //   2394: iconst_0
    //   2395: putstatic 3084	mbinc12/mb32b/MainPage:au	Z
    //   2398: goto -1721 -> 677
    //   2401: aload_0
    //   2402: getfield 2664	mbinc12/mb32b/MainPage:R	I
    //   2405: aload_0
    //   2406: getfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   2409: if_icmpge +14 -> 2423
    //   2412: iload_2
    //   2413: ifne +10 -> 2423
    //   2416: iconst_1
    //   2417: putstatic 3084	mbinc12/mb32b/MainPage:au	Z
    //   2420: goto -1743 -> 677
    //   2423: aload_0
    //   2424: getfield 2664	mbinc12/mb32b/MainPage:R	I
    //   2427: aload_0
    //   2428: getfield 2662	mbinc12/mb32b/MainPage:Q	I
    //   2431: if_icmpge -1754 -> 677
    //   2434: iload_2
    //   2435: ifeq -1758 -> 677
    //   2438: iconst_0
    //   2439: putstatic 3084	mbinc12/mb32b/MainPage:au	Z
    //   2442: goto -1765 -> 677
    //   2445: new 3136	java/io/File
    //   2448: dup
    //   2449: aload 7
    //   2451: ldc_w 3408
    //   2454: invokespecial 3156	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   2457: astore_1
    //   2458: aload_1
    //   2459: invokevirtual 3139	java/io/File:exists	()Z
    //   2462: ifeq +13 -> 2475
    //   2465: aload_0
    //   2466: iconst_0
    //   2467: putfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   2470: iconst_1
    //   2471: istore_2
    //   2472: goto -1640 -> 832
    //   2475: new 3136	java/io/File
    //   2478: dup
    //   2479: aload 7
    //   2481: ldc_w 3410
    //   2484: invokespecial 3156	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   2487: astore_1
    //   2488: aload_1
    //   2489: invokevirtual 3139	java/io/File:exists	()Z
    //   2492: ifeq +13 -> 2505
    //   2495: aload_0
    //   2496: iconst_1
    //   2497: putfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   2500: iconst_1
    //   2501: istore_2
    //   2502: goto -1670 -> 832
    //   2505: new 3136	java/io/File
    //   2508: dup
    //   2509: aload 7
    //   2511: ldc_w 3412
    //   2514: invokespecial 3156	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   2517: astore_1
    //   2518: aload_1
    //   2519: invokevirtual 3139	java/io/File:exists	()Z
    //   2522: ifeq +13 -> 2535
    //   2525: aload_0
    //   2526: iconst_0
    //   2527: putfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   2530: iconst_1
    //   2531: istore_2
    //   2532: goto -1700 -> 832
    //   2535: new 3136	java/io/File
    //   2538: dup
    //   2539: aload 7
    //   2541: ldc_w 3414
    //   2544: invokespecial 3156	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   2547: astore_1
    //   2548: aload_1
    //   2549: invokevirtual 3139	java/io/File:exists	()Z
    //   2552: ifeq +13 -> 2565
    //   2555: aload_0
    //   2556: iconst_1
    //   2557: putfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   2560: iconst_1
    //   2561: istore_2
    //   2562: goto -1730 -> 832
    //   2565: iconst_0
    //   2566: istore_2
    //   2567: goto -1735 -> 832
    //   2570: astore_1
    //   2571: aload_1
    //   2572: invokevirtual 3415	java/lang/Exception:toString	()Ljava/lang/String;
    //   2575: pop
    //   2576: goto -1661 -> 915
    //   2579: aload_0
    //   2580: iconst_1
    //   2581: putfield 2402	mbinc12/mb32b/MainPage:ax	Z
    //   2584: goto -1657 -> 927
    //   2587: aload_1
    //   2588: invokevirtual 3202	android/net/NetworkInfo:getType	()I
    //   2591: ifne +20 -> 2611
    //   2594: aload 6
    //   2596: ldc_w 3204
    //   2599: ldc_w 3417
    //   2602: invokeinterface 1560 3 0
    //   2607: pop
    //   2608: goto -1580 -> 1028
    //   2611: aload 6
    //   2613: ldc_w 3204
    //   2616: ldc_w 2833
    //   2619: invokeinterface 1560 3 0
    //   2624: pop
    //   2625: goto -1597 -> 1028
    //   2628: aload 6
    //   2630: ldc_w 3204
    //   2633: ldc_w 3419
    //   2636: invokeinterface 1560 3 0
    //   2641: pop
    //   2642: goto -1614 -> 1028
    //   2645: aload_0
    //   2646: new 776	java/util/ArrayList
    //   2649: dup
    //   2650: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   2653: putfield 1047	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
    //   2656: goto -1442 -> 1214
    //   2659: astore_1
    //   2660: aload_0
    //   2661: new 776	java/util/ArrayList
    //   2664: dup
    //   2665: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   2668: putfield 1047	mbinc12/mb32b/MainPage:M	Ljava/util/ArrayList;
    //   2671: aload_0
    //   2672: new 776	java/util/ArrayList
    //   2675: dup
    //   2676: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   2679: putfield 1049	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
    //   2682: goto -1444 -> 1238
    //   2685: aload_0
    //   2686: new 776	java/util/ArrayList
    //   2689: dup
    //   2690: invokespecial 1045	java/util/ArrayList:<init>	()V
    //   2693: putfield 1049	mbinc12/mb32b/MainPage:L	Ljava/util/ArrayList;
    //   2696: goto -1458 -> 1238
    //   2699: aload_0
    //   2700: getfield 3126	mbinc12/mb32b/MainPage:aw	Z
    //   2703: ifne -1125 -> 1578
    //   2706: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   2709: ifnull +35 -> 2744
    //   2712: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   2715: ldc_w 720
    //   2718: iconst_0
    //   2719: invokevirtual 726	android/os/Bundle:getInt	(Ljava/lang/String;I)I
    //   2722: ifne +15 -> 2737
    //   2725: getstatic 718	mbinc12/mb32b/MainPage:as	Landroid/os/Bundle;
    //   2728: ldc_w 846
    //   2731: invokevirtual 2976	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   2734: ifeq +10 -> 2744
    //   2737: aload_0
    //   2738: invokespecial 3421	mbinc12/mb32b/MainPage:M	()V
    //   2741: goto -1163 -> 1578
    //   2744: aload_0
    //   2745: aload_0
    //   2746: invokestatic 3423	asa:F	(Landroid/content/Context;)I
    //   2749: invokespecial 753	mbinc12/mb32b/MainPage:g	(I)V
    //   2752: goto -1174 -> 1578
    //   2755: invokestatic 3428	com/google/android/gms/common/GoogleApiAvailability:getInstance	()Lcom/google/android/gms/common/GoogleApiAvailability;
    //   2758: aload_0
    //   2759: invokevirtual 3431	com/google/android/gms/common/GoogleApiAvailability:isGooglePlayServicesAvailable	(Landroid/content/Context;)I
    //   2762: ifeq +11 -> 2773
    //   2765: aload_0
    //   2766: aconst_null
    //   2767: putfield 421	mbinc12/mb32b/MainPage:bl	Lapg;
    //   2770: goto -481 -> 2289
    //   2773: aload_0
    //   2774: invokevirtual 1132	mbinc12/mb32b/MainPage:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2777: ldc_w 3433
    //   2780: iconst_0
    //   2781: invokevirtual 3437	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2784: getfield 3442	android/content/pm/PackageInfo:versionCode	I
    //   2787: istore_2
    //   2788: aload_0
    //   2789: new 1778	apg
    //   2792: dup
    //   2793: aload_0
    //   2794: invokespecial 3443	apg:<init>	(Landroid/content/Context;)V
    //   2797: putfield 421	mbinc12/mb32b/MainPage:bl	Lapg;
    //   2800: goto -511 -> 2289
    //   2803: astore 6
    //   2805: new 609	org/json/JSONObject
    //   2808: dup
    //   2809: invokespecial 610	org/json/JSONObject:<init>	()V
    //   2812: astore_1
    //   2813: aload_1
    //   2814: ldc_w 3445
    //   2817: aload_0
    //   2818: invokevirtual 1132	mbinc12/mb32b/MainPage:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2821: ldc_w 3433
    //   2824: iconst_0
    //   2825: invokevirtual 3437	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2828: getfield 3442	android/content/pm/PackageInfo:versionCode	I
    //   2831: invokevirtual 2118	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   2834: pop
    //   2835: aload_1
    //   2836: ldc_w 3447
    //   2839: aload 6
    //   2841: invokevirtual 3450	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   2844: invokevirtual 618	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2847: pop
    //   2848: aload_0
    //   2849: ldc_w 3452
    //   2852: aload_1
    //   2853: invokestatic 623	mbinc12/mb32b/utils/MixerBoxUtils:a	(Landroid/content/Context;Ljava/lang/String;Lorg/json/JSONObject;)V
    //   2856: aload_0
    //   2857: aconst_null
    //   2858: putfield 421	mbinc12/mb32b/MainPage:bl	Lapg;
    //   2861: goto -572 -> 2289
    //   2864: astore 6
    //   2866: goto -18 -> 2848
    //   2869: astore_1
    //   2870: goto -82 -> 2788
    //   2873: astore_1
    //   2874: goto -2144 -> 730
    //   2877: iconst_1
    //   2878: istore_2
    //   2879: goto -2114 -> 765
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2882	0	this	MainPage
    //   0	2882	1	paramBundle	Bundle
    //   606	2273	2	i1	int
    //   2038	39	3	i2	int
    //   2043	46	4	i3	int
    //   2057	18	5	i4	int
    //   16	2613	6	localObject1	Object
    //   2803	37	6	localException1	Exception
    //   2864	1	6	localException2	Exception
    //   800	1740	7	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   196	209	2314	java/lang/Exception
    //   797	830	2570	java/lang/Exception
    //   836	915	2570	java/lang/Exception
    //   2445	2470	2570	java/lang/Exception
    //   2475	2500	2570	java/lang/Exception
    //   2505	2530	2570	java/lang/Exception
    //   2535	2560	2570	java/lang/Exception
    //   1181	1214	2659	java/lang/Exception
    //   1214	1238	2659	java/lang/Exception
    //   2645	2656	2659	java/lang/Exception
    //   2685	2696	2659	java/lang/Exception
    //   2788	2800	2803	java/lang/Exception
    //   2813	2848	2864	java/lang/Exception
    //   2773	2788	2869	android/content/pm/PackageManager$NameNotFoundException
    //   720	730	2873	java/lang/Exception
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    this.al = paramMenu;
    art.a = this;
    Object localObject1 = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903067, null);
    ((View)localObject1).setFocusable(true);
    this.bn = ((MyAutoCompleteTextView)((View)localObject1).findViewById(2131624062));
    this.bn.setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (MainPage.w(MainPage.this).getShouldDismissClick()) {
          MainPage.w(MainPage.this).setShouldDismissClick(false);
        }
        do
        {
          do
          {
            return;
          } while (MainPage.w(MainPage.this).isPopupShowing());
          MainPage.w(MainPage.this).showDropDown();
        } while (MainPage.w(MainPage.this).getText().length() != 0);
        MainPage.w(MainPage.this).setShouldDismissClick(true);
      }
    });
    this.bn.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public final void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (!paramAnonymousBoolean) {
          MainPage.a(MainPage.this, paramAnonymousView);
        }
      }
    });
    Object localObject2 = (ImageView)((View)localObject1).findViewById(2131624063);
    this.bn.setThreshold(1);
    this.bn.setMaxLines(1);
    this.bn.setAdapter(new c(this, this.bn));
    this.bn.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public final void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousView = (MainPage.c)MainPage.w(MainPage.this).getAdapter();
        MainPage.c.a localA = paramAnonymousView.a(paramAnonymousInt);
        JSONObject localJSONObject = new JSONObject();
        for (;;)
        {
          try
          {
            if ((paramAnonymousView.getItemViewType(paramAnonymousInt) != 1) || (localA == null)) {
              continue;
            }
            switch (localA.c)
            {
            }
          }
          catch (Exception paramAnonymousAdapterView)
          {
            continue;
            paramAnonymousAdapterView = "InvalidType";
            continue;
            paramAnonymousAdapterView = "SearchHistory";
            continue;
            paramAnonymousAdapterView = "TopSearch";
            continue;
            paramAnonymousAdapterView = "AutoComplete";
            continue;
          }
          localJSONObject.put("type", paramAnonymousAdapterView);
          MixerBoxUtils.a(jdField_this, "SearchDropDownClick", localJSONObject);
          if ((localA == null) || (localA.c != 3)) {
            continue;
          }
          paramAnonymousAdapterView = MainPage.this.an;
          if (paramAnonymousAdapterView.a.isOpen()) {
            paramAnonymousAdapterView.a.delete("tableSearchHistory", null, null);
          }
          paramAnonymousView.a();
          paramAnonymousView.notifyDataSetChanged();
          MainPage.w(MainPage.this).setText("");
          return;
          paramAnonymousAdapterView = "Header";
          continue;
          paramAnonymousAdapterView = "ClearSearchHistory";
        }
        MainPage.w(MainPage.this).onEditorAction(3);
        MainPage.w(MainPage.this).setShouldDismissClick(true);
      }
    });
    String str = getResources().getText(2131230931).toString();
    SpannableString localSpannableString = new SpannableString(str);
    localSpannableString.setSpan(new RelativeSizeSpan(0.7F), 0, str.length(), 33);
    this.bn.setHint(localSpannableString);
    this.bn.setHintTextColor(getResources().getColor(2131558511));
    ((ImageView)localObject2).setOnClickListener(new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        MainPage.w(MainPage.this).setText("");
        MainPage.w(MainPage.this).clearFocus();
        MainPage.w(MainPage.this).dismissDropDown();
      }
    });
    this.bn.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public final boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        String str;
        if (paramAnonymousInt == 3)
        {
          MainPage.x(MainPage.this);
          MainPage.w(MainPage.this).dismissDropDown();
          str = paramAnonymousTextView.getText().toString().replace("\n", " ");
          if (str.trim().length() != 0) {}
        }
        else
        {
          return false;
        }
        if (!arx.a(MainPage.this))
        {
          aqt.a(MainPage.this).show();
          return false;
        }
        HashMap localHashMap = new HashMap();
        localHashMap.put("query", str);
        paramAnonymousKeyEvent = Locale.getDefault().getLanguage();
        if ((paramAnonymousKeyEvent.equals("zh")) || (paramAnonymousKeyEvent.equals("ja")) || (paramAnonymousKeyEvent.equals("en")))
        {
          paramAnonymousTextView = paramAnonymousKeyEvent;
          if (paramAnonymousKeyEvent.equals("en"))
          {
            if (MixerBoxUtils.f(MainPage.this).equals("us")) {
              paramAnonymousTextView = "en_us";
            }
          }
          else {
            MixerBoxUtils.a("action:search_" + paramAnonymousTextView, localHashMap);
          }
        }
        else
        {
          MixerBoxUtils.b("Search " + str);
          MixerBoxUtils.a("action:search", localHashMap);
          paramAnonymousTextView = MainPage.this.an;
          if (paramAnonymousTextView.a.isOpen())
          {
            paramAnonymousKeyEvent = paramAnonymousTextView.a.query("tableSearchHistory", null, "NAME=?", new String[] { str }, null, null, null);
            paramAnonymousInt = paramAnonymousKeyEvent.getCount();
            paramAnonymousKeyEvent.close();
            if (paramAnonymousInt <= 0) {
              break label411;
            }
            paramAnonymousTextView.a.delete("tableSearchHistory", "NAME=?", new String[] { str });
          }
        }
        for (;;)
        {
          paramAnonymousKeyEvent = new ContentValues();
          paramAnonymousKeyEvent.put("NAME", str);
          paramAnonymousTextView.a.insert("tableSearchHistory", null, paramAnonymousKeyEvent);
          MainPage.this.w();
          paramAnonymousTextView = MainPage.this.a("VECTORIDSEARCH", "vector", 2, "");
          MixerBoxUtils.a(MainPage.this, str, paramAnonymousTextView);
          asc.a(MainPage.this, "doSearch");
          ((MainPage.c)MainPage.w(MainPage.this).getAdapter()).a();
          MainPage.w(MainPage.this).dismissDropDown();
          MainPage.w(MainPage.this).setShouldDismissClick(true);
          MixerBoxUtils.f(MainPage.this, "PerformSearch");
          return false;
          paramAnonymousTextView = "en_not_us";
          break;
          label411:
          paramAnonymousKeyEvent = paramAnonymousTextView.a.rawQuery("SELECT * FROM tableSearchHistory", null);
          if (paramAnonymousKeyEvent.getCount() >= 5)
          {
            paramAnonymousKeyEvent.moveToFirst();
            paramAnonymousTextView.a.delete("tableSearchHistory", "_ID=" + paramAnonymousKeyEvent.getString(0), null);
          }
          paramAnonymousKeyEvent.close();
        }
      }
    });
    localObject2 = paramMenu.addSubMenu(0, 2, 2, getResources().getString(2131230914)).setIcon(2130837857);
    ((SubMenu)localObject2).add(0, 6, 6, getResources().getString(2131230966));
    ((SubMenu)localObject2).add(0, 7, 7, getResources().getString(2131230915));
    ((SubMenu)localObject2).add(0, 8, 8, getResources().getString(2131230916));
    ((SubMenu)localObject2).add(0, 10, 10, getResources().getString(2131230984));
    ((SubMenu)localObject2).add(0, 12, 12, getResources().getString(2131230856));
    ((SubMenu)localObject2).add(0, 11, 11, getResources().getString(2131230967));
    ((SubMenu)localObject2).getItem().setShowAsAction(1);
    localObject2 = paramMenu.addSubMenu(0, 4, 4, getResources().getString(2131230914)).setIcon(2130837857);
    ((SubMenu)localObject2).add(0, 14, 14, getResources().getString(2131230967) + " (" + getResources().getString(2131230950) + ")");
    ((SubMenu)localObject2).add(0, 16, 16, getResources().getString(2131230967) + " (" + getResources().getString(2131230951) + ")");
    ((SubMenu)localObject2).getItem().setShowAsAction(1);
    paramMenu.add(0, 999, 999, getResources().getString(2131230970)).setIcon(2130837979).setActionView((View)localObject1).setShowAsAction(9);
    paramMenu.add(0, 1, 1, getResources().getString(2131230838)).setIcon(2130837765).setShowAsAction(1);
    paramMenu.add(0, 13, 13, getResources().getString(2131230869)).setShowAsAction(6);
    paramMenu.add(0, 15, 15, getResources().getString(2131230869)).setShowAsAction(6);
    paramMenu.add(0, 17, 17, getResources().getString(2131230869)).setShowAsAction(6);
    art.b(paramMenu, 4);
    art.b(paramMenu, 2);
    art.b(paramMenu, 7);
    art.b(paramMenu, 8);
    art.b(paramMenu, 13);
    art.b(paramMenu, 15);
    art.b(paramMenu, 17);
    localObject1 = paramMenu.findItem(999);
    if (Build.VERSION.SDK_INT >= 14) {
      cd.a((MenuItem)localObject1, new cd.e()
      {
        public final boolean a(MenuItem paramAnonymousMenuItem)
        {
          return true;
        }
        
        public final boolean b(MenuItem paramAnonymousMenuItem)
        {
          if (MainPage.this.m.size() > 1)
          {
            if ((MainPage.this.aJ == null) || (MainPage.this.aJ.b.c != 2)) {
              break label60;
            }
            MainPage.this.aJ.j();
          }
          for (;;)
          {
            MainPage.x(MainPage.this);
            return true;
            label60:
            MainPage.this.D();
          }
        }
      });
    }
    if ((paramMenu != null) && (K != null) && (K.size() > 0)) {
      art.a(paramMenu, ((Integer)K.lastElement()).intValue());
    }
    return true;
  }
  
  public void onDestroy()
  {
    this.z = false;
    MixerBoxUtils.a("Session End", null);
    if (this.aD) {
      MixerBoxUtils.f(this, "EndSession");
    }
    if (this.bp != null) {
      this.bp.onDestroy();
    }
    if (this.aJ != null) {
      this.aJ.f("about:blank");
    }
    this.ay = false;
    if (arv.a(this).a()) {
      arv.a(this).b();
    }
    Object localObject;
    if (this.bk != null)
    {
      localObject = this.bk;
      if ((((asn)localObject).c != null) && (((asn)localObject).b != null) && ((((asn)localObject).b.isConnected()) || (((asn)localObject).b.isConnecting())))
      {
        Wearable.MessageApi.removeListener(((asn)localObject).b, (MainPage)((asn)localObject).a);
        ((asn)localObject).b.disconnect();
      }
    }
    if (this.bl != null)
    {
      localObject = this.bl;
      ((apg)localObject).b();
      ((apg)localObject).d();
    }
    ((AlarmManager)getSystemService("alarm")).cancel(PendingIntent.getBroadcast(this, 0, new Intent("SleepService"), 0));
    if (this.X != null) {
      unregisterReceiver(this.X);
    }
    if (this.ab != null) {
      unregisterReceiver(this.ab);
    }
    if (this.Y != null) {
      unregisterReceiver(this.Y);
    }
    if (this.ba != null) {
      unregisterReceiver(this.ba);
    }
    if (this.ag != null) {
      unregisterReceiver(this.ag);
    }
    if (this.ai != null) {
      unregisterReceiver(this.ai);
    }
    if (this.Z != null) {
      unregisterReceiver(this.Z);
    }
    if (this.aa != null) {
      unregisterReceiver(this.aa);
    }
    if (this.aj != null) {
      this.aj.unregisterMediaButtonEventReceiver(new ComponentName(this, RemoteControlReceiver.class));
    }
    if (this.bb != null) {}
    try
    {
      unregisterReceiver(this.bb);
      if (this.bc != null) {}
      try
      {
        unregisterReceiver(this.bc);
        asc.d();
        try
        {
          if (this.ah != null) {
            unregisterReceiver(this.ah);
          }
          e();
          if (this.an != null) {
            this.an.close();
          }
          if (this.aJ != null) {
            unbindService(this.aK);
          }
          this.aJ = null;
          if (this.S != null) {
            this.S.destroy();
          }
          if (this.aZ != null) {
            this.aZ.destroy();
          }
          if ((this.aD) && (this.aE))
          {
            MixerBoxUtils.f(this, "onDestroy");
            if (((!aq) || (!this.ap)) && (!asa.aN(this)))
            {
              MixerBoxUtils.f(this, "PlayerNotReadyWhenFirstSessionEndedV2");
              asa.aO(this);
            }
            if (!asa.aP(this)) {
              localObject = new JSONObject();
            }
          }
          try
          {
            ((JSONObject)localObject).put("type", asa.n(this));
            MixerBoxUtils.a(this, "AndroidPlayerType", (JSONObject)localObject);
            asa.aQ(this);
            if (this.T != null) {
              this.T.destroy(this);
            }
            super.onDestroy();
            return;
          }
          catch (Exception localException4)
          {
            for (;;) {}
          }
        }
        catch (Exception localException1)
        {
          for (;;) {}
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
    catch (Exception localException3)
    {
      for (;;) {}
    }
  }
  
  public void onMessageReceived(MessageEvent paramMessageEvent)
  {
    new StringBuilder("Receive Msg: ").append(paramMessageEvent.getPath());
    try
    {
      paramMessageEvent = paramMessageEvent.getPath();
      if (paramMessageEvent.equals("PlayPause"))
      {
        a(true);
        return;
      }
      if (paramMessageEvent.equals("Next"))
      {
        a(true, 0);
        return;
      }
    }
    catch (Exception paramMessageEvent)
    {
      paramMessageEvent.printStackTrace();
      return;
    }
    if (paramMessageEvent.equals("Prev"))
    {
      e(0);
      return;
    }
    if ((paramMessageEvent.equals("Resend")) && (this.bk != null) && (this.I != null)) {
      this.bk.a(this.I);
    }
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
    try
    {
      Bundle localBundle = new Bundle();
      if (as != null) {
        localBundle = new Bundle(as);
      }
      paramIntent = paramIntent.getExtras();
      as = paramIntent;
      if ((paramIntent != null) && (!as.containsKey("isLogIn")) && (localBundle.containsKey("isLogIn"))) {
        as.putBoolean("isLogIn", localBundle.getBoolean("isLogIn"));
      }
      M();
      return;
    }
    catch (Exception paramIntent)
    {
      paramIntent = new Intent(this, MixerBox.class);
      paramIntent.addFlags(67108864);
      startActivity(paramIntent);
      finish();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int i2 = 0;
    int i1 = 0;
    boolean bool2 = true;
    boolean bool1 = bool2;
    Object localObject1;
    switch (paramMenuItem.getItemId())
    {
    default: 
      bool1 = super.onOptionsItemSelected(paramMenuItem);
    case 7: 
    case 16908332: 
    case 999: 
    case 1: 
    case 2: 
    case 6: 
    case 8: 
    case 12: 
    case 10: 
    case 11: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
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
                return bool1;
                bool1 = bool2;
              } while (this.m.size() <= 1);
              D();
              return true;
              h();
              return true;
              str = getResources().getString(2131230925);
              localObject3 = Calendar.getInstance();
              localObject1 = String.valueOf(((Calendar)localObject3).get(2) + 1);
              paramMenuItem = (MenuItem)localObject1;
              if (((String)localObject1).length() == 1) {
                paramMenuItem = "0" + (String)localObject1;
              }
              localObject2 = String.valueOf(((Calendar)localObject3).get(5));
              localObject1 = localObject2;
              if (((String)localObject2).length() == 1) {
                localObject1 = "0" + (String)localObject2;
              }
              new aqu(this, 1, str + " (" + ((Calendar)localObject3).get(1) + paramMenuItem + (String)localObject1 + ")").show();
              return true;
              paramMenuItem = new HashMap();
              paramMenuItem.put("id", ((SongFragment)this.m.lastElement()).F.a);
              MixerBoxUtils.a("action:playlist_edit", paramMenuItem);
              return true;
              new aqz(this, ((SongFragment)this.m.lastElement()).F).show();
              return true;
              if (this.am)
              {
                MixerBoxUtils.c(this, ((SongFragment)this.m.lastElement()).F.a);
                return true;
              }
              this.an.c(((SongFragment)this.m.lastElement()).F.a);
              MixerBoxUtils.a(this, getResources().getString(2131230869), 0, new boolean[0]);
              s();
              return true;
              bool1 = bool2;
            } while (!(this.m.lastElement() instanceof SongFragment));
            new aqy(this, 1, ((SongFragment)this.m.lastElement()).F, null, 0).show();
            return true;
            paramMenuItem = ((SongFragment)this.m.lastElement()).F.b;
            localObject1 = ((SongFragment)this.m.lastElement()).F.a;
            Object localObject2 = new HashMap();
            ((Map)localObject2).put("id", localObject1);
            MixerBoxUtils.a("action:share_playlist", (Map)localObject2);
            localObject1 = "http://www.mixerbox.com/list/" + (String)localObject1;
            localObject2 = getResources().getString(2131230985) + getResources().getString(2131230986);
            String str = getResources().getString(2131230987);
            Object localObject3 = new Intent("android.intent.action.SEND");
            ((Intent)localObject3).setType("text/plain");
            ((Intent)localObject3).putExtra("android.intent.extra.SUBJECT", (String)localObject2 + paramMenuItem + str);
            ((Intent)localObject3).putExtra("android.intent.extra.TEXT", (String)localObject2 + paramMenuItem + str + " " + (String)localObject1);
            startActivity(Intent.createChooser((Intent)localObject3, null));
            return true;
            if (C()) {
              paramMenuItem = new arl();
            }
            try
            {
              paramMenuItem.e = this;
              paramMenuItem.f = ((SongFragment)this.m.lastElement()).F.a;
              paramMenuItem.c = ((SongFragment)this.m.lastElement()).a;
              paramMenuItem.g = false;
              paramMenuItem.a = new apd(paramMenuItem.e, paramMenuItem.c);
              paramMenuItem.setListAdapter(paramMenuItem.a);
              this.ao = 11;
              a(paramMenuItem, true);
              MixerBoxUtils.a("action:rearrange", null);
            }
            catch (Exception paramMenuItem)
            {
              for (;;)
              {
                MixerBoxUtils.a(this, getResources().getString(2131231010), 0, new boolean[0]);
              }
            }
            bool1 = bool2;
          } while (asa.m(this));
          paramMenuItem = new AlertDialog.Builder(this);
          paramMenuItem.setTitle(getResources().getString(2131231006));
          paramMenuItem.setMessage(getResources().getString(2131231007)).setCancelable(true).setPositiveButton(2131230851, new aqt.4());
          paramMenuItem.create().show();
          asa.G(this);
          return true;
          paramMenuItem = ((arl)this.m.lastElement()).c;
          if (!this.am)
          {
            this.an.a(((arl)this.m.lastElement()).f, ((arl)this.m.lastElement()).c);
            D();
            ((SongFragment)this.m.lastElement()).a();
            MixerBoxUtils.a(this, getResources().getString(2131230869), 0, new boolean[0]);
            return true;
          }
          localObject1 = new RequestParams();
          i1 = paramMenuItem.size() - 1;
          while (i1 >= 0)
          {
            ((RequestParams)localObject1).put("music[" + (paramMenuItem.size() - i1 - 1) + "]", ((apn)paramMenuItem.get(i1)).a);
            i1 -= 1;
          }
          arx.a(arx.n(((arl)this.m.lastElement()).f), (RequestParams)localObject1, new apj(this)
          {
            public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
            {
              super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
              MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131231011), 0, new boolean[0]);
            }
            
            public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
            {
              super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
              if ((paramAnonymousArrayOfByte != null) && (new String(paramAnonymousArrayOfByte).length() > 0)) {
                try
                {
                  paramAnonymousArrayOfXh = new JSONObject(new String(paramAnonymousArrayOfByte));
                  if ((!paramAnonymousArrayOfXh.isNull("changePlaylist")) && (!paramAnonymousArrayOfXh.getJSONObject("changePlaylist").isNull("status"))) {
                    if (paramAnonymousArrayOfXh.getJSONObject("changePlaylist").getBoolean("status"))
                    {
                      MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131230869), 0, new boolean[0]);
                      if ((MainPage.this.m.lastElement() instanceof SongFragment)) {
                        ((SongFragment)MainPage.this.m.lastElement()).a();
                      }
                    }
                    else
                    {
                      MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131231011), 0, new boolean[0]);
                      return;
                    }
                  }
                }
                catch (JSONException paramAnonymousArrayOfXh)
                {
                  paramAnonymousArrayOfXh.printStackTrace();
                }
              }
            }
          });
          MixerBoxUtils.a(this, getResources().getString(2131230850), 0, new boolean[0]);
          D();
          return true;
          bool1 = bool2;
        } while (!C());
        paramMenuItem = new arl();
        try
        {
          localObject1 = this.M;
          if (((apm)((ArrayList)localObject1).get(((ArrayList)localObject1).size() - 1)).a.equals("PLAYLISTHISTORYID")) {
            ((ArrayList)localObject1).remove(((ArrayList)localObject1).size() - 1);
          }
          if (((apm)((ArrayList)localObject1).get(0)).a.equals("-999")) {
            ((ArrayList)localObject1).remove(0);
          }
          paramMenuItem.e = this;
          paramMenuItem.d = ((ArrayList)localObject1);
          paramMenuItem.a();
          this.ao = 15;
          a(paramMenuItem, true);
          MixerBoxUtils.a("action:rearrange_playlist", null);
          return true;
        }
        catch (Exception paramMenuItem)
        {
          MixerBoxUtils.a(this, getResources().getString(2131231010), 0, new boolean[0]);
          return true;
        }
        paramMenuItem = ((arl)this.m.lastElement()).d;
        if (this.am)
        {
          localObject1 = new RequestParams();
          while (i1 <= paramMenuItem.size() - 1)
          {
            ((RequestParams)localObject1).put("playlistIds[" + (paramMenuItem.size() - i1 - 1) + "]", ((apm)paramMenuItem.get(i1)).a);
            i1 += 1;
          }
          arx.a(arx.b(), (RequestParams)localObject1, new apj(this)
          {
            public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
            {
              super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
              MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131231011), 0, new boolean[0]);
            }
            
            public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
            {
              super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
              MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131230869), 0, new boolean[0]);
              MixerBoxUtils.a(MainPage.this, false);
            }
          });
          D();
          return true;
        }
        this.an.a(paramMenuItem);
        MixerBoxUtils.a(this, getResources().getString(2131230869), 0, new boolean[0]);
        s();
        D();
        return true;
        bool1 = bool2;
      } while (!C());
      paramMenuItem = new arl();
      try
      {
        localObject1 = new ArrayList(this.L);
        paramMenuItem.e = this;
        paramMenuItem.d = ((ArrayList)localObject1);
        paramMenuItem.a();
        this.ao = 16;
        a(paramMenuItem, true);
        MixerBoxUtils.a("action:rearrange_subscription", null);
        return true;
      }
      catch (Exception paramMenuItem)
      {
        MixerBoxUtils.a(this, getResources().getString(2131231010), 0, new boolean[0]);
        return true;
      }
    }
    paramMenuItem = ((arl)this.m.lastElement()).d;
    if (this.am)
    {
      localObject1 = new RequestParams();
      i1 = i2;
      while (i1 <= paramMenuItem.size() - 1)
      {
        ((RequestParams)localObject1).put("subsPlaylistIds[" + (paramMenuItem.size() - i1 - 1) + "]", ((apm)paramMenuItem.get(i1)).a);
        i1 += 1;
      }
      arx.a(arx.b(), (RequestParams)localObject1, new apj(this)
      {
        public final void onFailure(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte, Throwable paramAnonymousThrowable)
        {
          super.onFailure(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte, paramAnonymousThrowable);
          MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131231011), 0, new boolean[0]);
        }
        
        public final void onSuccess(int paramAnonymousInt, xh[] paramAnonymousArrayOfXh, byte[] paramAnonymousArrayOfByte)
        {
          super.onSuccess(paramAnonymousInt, paramAnonymousArrayOfXh, paramAnonymousArrayOfByte);
          MixerBoxUtils.a(MainPage.this, MainPage.this.getResources().getString(2131230869), 0, new boolean[0]);
          MixerBoxUtils.a(MainPage.this, false);
        }
      });
      D();
      return true;
    }
    this.an.b(paramMenuItem);
    MixerBoxUtils.a(this, getResources().getString(2131230869), 0, new boolean[0]);
    s();
    D();
    return true;
  }
  
  public void onPause()
  {
    this.z = false;
    MixerBoxUtils.b("Start onPause()");
    this.bp.onPause();
    this.ay = false;
    if (arv.a(this).a()) {
      arv.a(this).b();
    }
    if (asa.H(this) == 0) {
      E();
    }
    if (this.T != null) {
      this.T.pause(this);
    }
    super.onPause();
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    MixerBoxUtils.b("Start onResume()");
    if (this.aD) {
      MixerBoxUtils.f(this, "onResume");
    }
    super.onResume();
    if (Settings.System.getInt(getContentResolver(), "accelerometer_rotation", 0) != 1) {
      setRequestedOrientation(7);
    }
    for (;;)
    {
      if (this.aJ != null) {
        this.aJ.d();
      }
      WindowPlayerService.b localB;
      if (this.aJ != null)
      {
        localB = this.aJ;
        localB.b.W.setVisibility(8);
        if (localB.b.ay != null)
        {
          localB.b.ay.g = true;
          localB.b.ay.a();
        }
      }
      this.ay = true;
      this.bp.onResume();
      this.V.cancel(12321);
      asc.g(this);
      MixerBoxUtils.b("End onResume()");
      this.z = true;
      if (this.aJ != null)
      {
        localB = this.aJ;
        if (localB.b.au)
        {
          WindowPlayerService.c(localB.b).a(localB.b.k);
          localB.b.au = false;
        }
      }
      if (this.T != null) {
        this.T.resume(this);
      }
      return;
      setRequestedOrientation(10);
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    this.z = false;
    super.onSaveInstanceState(paramBundle);
    this.bp.onSaveInstanceState(paramBundle);
  }
  
  protected void onStart()
  {
    MixerBoxUtils.b("Start onStart()");
    super.onStart();
    this.ay = true;
    this.z = true;
    FlurryAgent.onStartSession(this);
  }
  
  protected void onStop()
  {
    this.z = false;
    MixerBoxUtils.b("Start onStop()");
    if (this.aJ != null)
    {
      WindowPlayerService.b localB = this.aJ;
      localB.b.W.setVisibility(0);
      if (localB.b.ay != null) {
        localB.b.ay.g = false;
      }
      localB.b.am.setVisibility(8);
    }
    this.ay = false;
    if (this.aD) {
      MixerBoxUtils.f(this, "onStop");
    }
    FlurryAgent.onEndSession(this);
    super.onStop();
  }
  
  protected void onUserLeaveHint()
  {
    super.onUserLeaveHint();
  }
  
  public final boolean p()
  {
    return !this.aD;
  }
  
  public final void q()
  {
    try
    {
      startActivityForResult(AccountPicker.newChooseAccountIntent(null, null, new String[] { "com.google" }, false, null, null, null, null), 1000);
      return;
    }
    catch (Exception localException)
    {
      MixerBoxUtils.a(this, getResources().getString(2131230927), 0, new boolean[0]);
      arw.a(this, false, "PickUserAccountException", new String[] { localException.getMessage() });
    }
  }
  
  public final void r()
  {
    this.ad = false;
    if (this.aJ != null)
    {
      this.aJ.b.d();
      this.aJ.i();
    }
  }
  
  public final void s()
  {
    boolean bool = false;
    if (!this.am) {
      U();
    }
    for (;;)
    {
      if (this.L.size() == 0) {
        bool = true;
      }
      MixerBoxUtils.a(bool);
      if ((this.t != null) && (!this.am)) {
        this.t.a();
      }
      return;
      MixerBoxUtils.a(this, false);
    }
  }
  
  public final void t()
  {
    if (this.C == null) {}
    for (;;)
    {
      return;
      Iterator localIterator = this.C.iterator();
      while (localIterator.hasNext())
      {
        aqc localAqc = (aqc)localIterator.next();
        if ((localAqc instanceof aqf)) {
          ((aqf)localAqc).h = true;
        }
      }
    }
  }
  
  public final void u()
  {
    if (!C()) {}
    for (;;)
    {
      return;
      if (this.k.getVisibility() == 0) {
        this.k.setVisibility(8);
      }
      Object localObject = new HashMap();
      ((Map)localObject).put("tab", "navPlaylists");
      MixerBoxUtils.a("action:main_tab_nav", (Map)localObject);
      this.ao = 0;
      a(this.t, false);
      if ((this.C == null) || (this.C.size() <= 1)) {
        s();
      }
      localObject = (TextView)this.n.findViewById(2131624246);
      int i1 = asb.e(this);
      int i2 = asb.c(this);
      MixerBoxUtils.a(this, asb.k(this), (ImageView)this.n.findViewById(2131624245), 8);
      this.n.setBackgroundColor(i2);
      ((TextView)localObject).setTextColor(i1);
      if (this.t != null)
      {
        localObject = this.t;
        if (asa.c(((ari)localObject).getActivity()) == 593) {}
        for (i1 = 1; (((ari)localObject).b != null) && (i1 != 0); i1 = 0)
        {
          ((ari)localObject).b.post(new ari.8((ari)localObject));
          return;
        }
      }
    }
  }
  
  public final void v()
  {
    if (!C()) {}
    do
    {
      return;
      Object localObject = new HashMap();
      ((Map)localObject).put("tab", "navNewsFeed");
      MixerBoxUtils.a("action:main_tab_nav", (Map)localObject);
      this.ao = 12;
      a(this.u, false);
      localObject = (TextView)this.r.findViewById(2131624250);
      int i1 = asb.e(this);
      int i2 = asb.c(this);
      MixerBoxUtils.a(this, asb.l(this), (ImageView)this.r.findViewById(2131624249), 8);
      this.r.setBackgroundColor(i2);
      ((TextView)localObject).setTextColor(i1);
    } while (this.D.size() > 1);
    this.u.a();
  }
  
  public final void w()
  {
    if (!C()) {
      return;
    }
    if ((this.B != null) && (this.B.size() == 0)) {
      this.B.add(new apu());
    }
    Object localObject = (ImageView)findViewById(2131624254);
    if (((ImageView)localObject).getVisibility() == 0) {
      ((ImageView)localObject).setVisibility(8);
    }
    localObject = new HashMap();
    ((Map)localObject).put("tab", "navSearch");
    MixerBoxUtils.a("action:main_tab_nav", (Map)localObject);
    this.ao = 1;
    if ((this.bo != null) && (this.bo.isAlive())) {}
    for (int i1 = 1;; i1 = 0)
    {
      if ((i1 == 0) && ((this.B == null) || (this.B.size() <= 1))) {
        O();
      }
      a(this.v, false);
      localObject = (TextView)this.o.findViewById(2131624253);
      i1 = asb.e(this);
      int i2 = asb.c(this);
      MixerBoxUtils.a(this, asb.m(this), (ImageView)this.o.findViewById(2131624252), 8);
      this.o.setBackgroundColor(i2);
      ((TextView)localObject).setTextColor(i1);
      return;
    }
  }
  
  public final void x()
  {
    if (!C()) {
      return;
    }
    Object localObject = new HashMap();
    ((Map)localObject).put("tab", "navFriends");
    MixerBoxUtils.a("action:main_tab_nav", (Map)localObject);
    this.ao = 2;
    a(this.w, false);
    if ((this.A == null) || (this.A.isEmpty()) || ((this.w.a != null) && (this.w.a.getChildCount() == 0))) {}
    try
    {
      MixerBoxUtils.d(this);
      localObject = (TextView)this.p.findViewById(2131624257);
      int i1 = asb.e(this);
      int i2 = asb.c(this);
      MixerBoxUtils.a(this, asb.n(this), (ImageView)this.p.findViewById(2131624256), 8);
      this.p.setBackgroundColor(i2);
      ((TextView)localObject).setTextColor(i1);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public final void y()
  {
    if (!C()) {}
    for (;;)
    {
      return;
      HashMap localHashMap = new HashMap();
      localHashMap.put("tab", "navSettings");
      MixerBoxUtils.a("action:main_tab_nav", localHashMap);
      this.ao = 3;
      a(this.x, false);
      try
      {
        this.x.a();
        R();
        if (!this.am) {
          continue;
        }
        try
        {
          if (asa.aU(this)) {
            continue;
          }
          Request.executeMeRequestAsync(Session.getActiveSession(), new Request.GraphUserCallback()
          {
            public final void onCompleted(GraphUser paramAnonymousGraphUser, Response paramAnonymousResponse)
            {
              int j = 0;
              if (paramAnonymousGraphUser != null) {
                try
                {
                  Object localObject1 = paramAnonymousGraphUser.asMap().get("gender").toString();
                  paramAnonymousResponse = paramAnonymousGraphUser.asMap().get("locale").toString();
                  if (paramAnonymousResponse != null) {
                    if (paramAnonymousResponse.length() == 0) {
                      break label510;
                    }
                  }
                  for (;;)
                  {
                    int i;
                    if (((String)localObject1).equals("male")) {
                      i = 1;
                    }
                    int k;
                    label109:
                    label212:
                    label365:
                    label407:
                    label451:
                    label495:
                    label502:
                    for (;;)
                    {
                      k = Calendar.getInstance().get(1);
                      try
                      {
                        paramAnonymousGraphUser = paramAnonymousGraphUser.getBirthday();
                        if ((paramAnonymousGraphUser == null) || (paramAnonymousGraphUser.length() != 4)) {
                          break label365;
                        }
                        m = Integer.valueOf(paramAnonymousGraphUser).intValue();
                        j = m;
                      }
                      catch (Exception paramAnonymousGraphUser)
                      {
                        for (;;)
                        {
                          int m;
                          ati localAti;
                          Object localObject2;
                          String str;
                          continue;
                          j = k;
                        }
                        i = 0;
                      }
                      j = k - j;
                      localObject1 = asa.i(MainPage.this).replace("-", "_");
                      paramAnonymousGraphUser = new JSONObject();
                      paramAnonymousGraphUser.put("data", arp.a(MainPage.this, (String)localObject1, paramAnonymousResponse, j, i));
                      localAti = new ati();
                      localObject2 = ath.a("application/json; charset=utf-8");
                      str = paramAnonymousGraphUser.toString();
                      paramAnonymousResponse = att.e;
                      localObject1 = localObject2;
                      if (localObject2 != null)
                      {
                        if (((ath)localObject2).a == null) {
                          break label407;
                        }
                        paramAnonymousGraphUser = Charset.forName(((ath)localObject2).a);
                        paramAnonymousResponse = paramAnonymousGraphUser;
                        localObject1 = localObject2;
                        if (paramAnonymousGraphUser == null)
                        {
                          paramAnonymousResponse = att.e;
                          localObject1 = ath.a(localObject2 + "; charset=utf-8");
                        }
                      }
                      paramAnonymousResponse = atm.a((ath)localObject1, str.getBytes(paramAnonymousResponse));
                      localObject1 = new atl.a();
                      paramAnonymousGraphUser = "https://lj24vizfk6.execute-api.us-east-1.amazonaws.com/prod/sendgender";
                      if ("https://lj24vizfk6.execute-api.us-east-1.amazonaws.com/prod/sendgender".regionMatches(true, 0, "ws:", 0, 3)) {
                        paramAnonymousGraphUser = "http:" + "https://lj24vizfk6.execute-api.us-east-1.amazonaws.com/prod/sendgender".substring(3);
                      }
                      for (;;)
                      {
                        localObject2 = atf.d(paramAnonymousGraphUser);
                        if (localObject2 != null) {
                          break label451;
                        }
                        throw new IllegalArgumentException("unexpected url: " + paramAnonymousGraphUser);
                        boolean bool = ((String)localObject1).equals("female");
                        if (!bool) {
                          break label502;
                        }
                        i = 2;
                        break;
                        if ((paramAnonymousGraphUser == null) || (paramAnonymousGraphUser.length() != 10)) {
                          break label495;
                        }
                        m = Integer.valueOf(paramAnonymousGraphUser.substring(paramAnonymousGraphUser.length() - 4, paramAnonymousGraphUser.length())).intValue();
                        j = m;
                        break label109;
                        paramAnonymousGraphUser = null;
                        break label212;
                        if ("https://lj24vizfk6.execute-api.us-east-1.amazonaws.com/prod/sendgender".regionMatches(true, 0, "wss:", 0, 4)) {
                          paramAnonymousGraphUser = "https:" + "https://lj24vizfk6.execute-api.us-east-1.amazonaws.com/prod/sendgender".substring(4);
                        }
                      }
                      new atk(localAti, ((atl.a)localObject1).a((atf)localObject2).a("POST", paramAnonymousResponse).a(), false).a(new ast() {});
                      return;
                    }
                    continue;
                    label510:
                    paramAnonymousResponse = "0";
                  }
                  return;
                }
                catch (Exception paramAnonymousGraphUser) {}
              }
            }
          });
          asa.aV(this);
          return;
        }
        catch (Exception localException1) {}
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  public final void z()
  {
    if (!C()) {
      return;
    }
    this.ao = 17;
    a(arm.a(), true);
  }
  
  public final class a
    extends AsyncTask
  {
    Activity a;
    String b;
    String c;
    
    a(Activity paramActivity, String paramString1, String paramString2)
    {
      this.a = paramActivity;
      this.b = paramString2;
      this.c = paramString1;
    }
    
    private String a()
    {
      try
      {
        String str = GoogleAuthUtil.getToken(this.a, this.c, this.b);
        if (str == null) {
          arw.a(MainPage.this, false, "TokenNull", new String[0]);
        }
        return str;
      }
      catch (UserRecoverableAuthException localUserRecoverableAuthException)
      {
        MainPage.this.startActivityForResult(localUserRecoverableAuthException.getIntent(), 1000);
        return null;
      }
      catch (GoogleAuthException localGoogleAuthException)
      {
        for (;;)
        {
          arw.a(MainPage.this, false, "TokenAuthException", new String[] { localGoogleAuthException.getMessage() });
        }
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          arw.a(MainPage.this, false, "TokenIOException", new String[] { localIOException.getMessage() });
        }
      }
    }
    
    protected final Object doInBackground(Object... paramVarArgs)
    {
      try
      {
        paramVarArgs = a();
        if (paramVarArgs != null)
        {
          ArrayList localArrayList = new ArrayList();
          arw.a(MainPage.this, paramVarArgs, localArrayList, "");
        }
      }
      catch (IOException paramVarArgs)
      {
        for (;;)
        {
          arw.a(MainPage.this, false, "TokenException", new String[] { paramVarArgs.getMessage() });
        }
      }
      return null;
    }
  }
  
  static final class b
    extends Handler
  {
    private final WeakReference<MainPage> a;
    
    public b(MainPage paramMainPage)
    {
      this.a = new WeakReference(paramMainPage);
    }
    
    public final void handleMessage(final Message paramMessage)
    {
      boolean bool1 = true;
      boolean bool2 = true;
      int i = 1;
      final MainPage localMainPage = (MainPage)this.a.get();
      if (localMainPage != null) {}
      Object localObject;
      switch (paramMessage.what)
      {
      case 30213: 
      case 30222: 
      case 30226: 
      default: 
      case 30201: 
      case 30202: 
      case 30203: 
      case 30204: 
      case 30205: 
      case 30206: 
      case 30207: 
      case 30208: 
      case 30209: 
      case 30210: 
      case 30211: 
      case 30212: 
      case 30214: 
      case 30227: 
      case 30215: 
      case 30216: 
      case 30217: 
      case 30218: 
      case 30219: 
      case 30220: 
      case 30221: 
      case 30223: 
      case 30224: 
      case 30225: 
      case 30232: 
      case 30228: 
      case 30229: 
      case 30230: 
      case 30231: 
      case 30233: 
      case 30234: 
      case 30235: 
      case 30236: 
      case 30237: 
      case 30238: 
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
                            do
                            {
                              do
                              {
                                do
                                {
                                  return;
                                } while ((localMainPage.aJ == null) || (localMainPage.aJ.a()));
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.b(localMainPage, (String)paramMessage.obj);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.i(localMainPage);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.j(localMainPage);
                                    MainPage.k(localMainPage);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.l(localMainPage);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.m(localMainPage);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.n(localMainPage);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.o(localMainPage);
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    MainPage.f(Integer.valueOf((String)paramMessage.obj).intValue());
                                  }
                                });
                                return;
                                localMainPage.runOnUiThread(new Runnable()
                                {
                                  public final void run()
                                  {
                                    Object localObject = paramMessage.obj;
                                    MainPage.H();
                                  }
                                });
                                return;
                              } while ((localMainPage.aJ == null) || (localMainPage.aJ.a()));
                              localMainPage.runOnUiThread(new Runnable()
                              {
                                public final void run()
                                {
                                  localMainPage.c(Integer.valueOf((String)paramMessage.obj).intValue());
                                }
                              });
                              return;
                            } while ((localMainPage.aJ == null) || (localMainPage.aJ.a()));
                            localMainPage.runOnUiThread(new Runnable()
                            {
                              public final void run()
                              {
                                localMainPage.d(Integer.valueOf((String)paramMessage.obj).intValue());
                              }
                            });
                            return;
                            localMainPage.runOnUiThread(new Runnable()
                            {
                              public final void run()
                              {
                                Object localObject = paramMessage.obj;
                                MainPage.I();
                              }
                            });
                            return;
                            localMainPage.runOnUiThread(new Runnable()
                            {
                              public final void run()
                              {
                                localMainPage.a(false);
                              }
                            });
                            return;
                            localMainPage.runOnUiThread(new Runnable()
                            {
                              public final void run()
                              {
                                localMainPage.g();
                              }
                            });
                            return;
                          } while (localMainPage.F == null);
                          if ((MainPage.e == 2) && (localMainPage.F != null) && (localMainPage.F.size() > 0)) {
                            localMainPage.G = ((localMainPage.G + 1) % localMainPage.F.size());
                          }
                          localMainPage.a(true, 2);
                          return;
                          if (localMainPage.H < 5) {
                            break;
                          }
                        } while (localMainPage.aJ == null);
                        localMainPage.aJ.f("javascript:seekTo(0)");
                        return;
                        if ((MainPage.e == 2) && (localMainPage.F.size() > 0))
                        {
                          localMainPage.G -= 1;
                          if (localMainPage.G == -1) {
                            localMainPage.G = (localMainPage.F.size() - 1);
                          }
                        }
                        localMainPage.e(2);
                        return;
                        localMainPage.runOnUiThread(new Runnable()
                        {
                          public final void run()
                          {
                            try
                            {
                              new aqs(localMainPage, localMainPage.I).show().getWindow().setSoftInputMode(16);
                              return;
                            }
                            catch (Exception localException)
                            {
                              MixerBoxUtils.a(localMainPage, localMainPage.getResources().getString(2131231011), 0, new boolean[0]);
                            }
                          }
                        });
                        return;
                      } while (localMainPage.I == null);
                      if ((localMainPage.J == null) || ((!localMainPage.am) && (localMainPage.i(localMainPage.J.a)))) {}
                      for (paramMessage = "http://www.mixerbox.com/music/0/" + localMainPage.I.d;; paramMessage = "http://www.mixerbox.com/music/" + localMainPage.J.a + "/" + localMainPage.I.a)
                      {
                        localObject = localMainPage.getResources().getString(2131230985) + localMainPage.getResources().getString(2131230986);
                        String str = localMainPage.getResources().getString(2131230987);
                        Intent localIntent = new Intent("android.intent.action.SEND");
                        localIntent.setType("text/plain");
                        localIntent.putExtra("android.intent.extra.SUBJECT", (String)localObject + localMainPage.I.b + str);
                        localIntent.putExtra("android.intent.extra.TEXT", (String)localObject + localMainPage.I.b + str + " " + paramMessage);
                        localMainPage.startActivity(Intent.createChooser(localIntent, null));
                        return;
                      }
                      localMainPage.ap = false;
                      MainPage.aq = false;
                    } while (localMainPage.aJ == null);
                    localMainPage.aJ.f("http://static.mixerbox.com/android/yt_android.min.b.v11.html");
                    return;
                    localMainPage.av = false;
                  } while ((localMainPage.I == null) || ((localMainPage.I.f != 3) && (localMainPage.I.f != 2)));
                  MyService.a.pause();
                  return;
                  localMainPage.av = true;
                  if (((localMainPage.I == null) || (localMainPage.I.f != 3)) && ((localMainPage.J == null) || (localMainPage.I.f != 2))) {
                    break;
                  }
                } while (MyService.a == null);
                MyService.a.seekTo(paramMessage.arg1 * 1000);
                MyService.a.start();
                return;
              } while (localMainPage.aJ == null);
              MainPage.a(localMainPage, true, paramMessage.arg1);
              localMainPage.aJ.f("javascript:seekTo(" + paramMessage.arg1 + ")");
              return;
              localMainPage.w();
              paramMessage = (apm)paramMessage.obj;
              localObject = localMainPage.a(paramMessage.a, "vector", 0, paramMessage.b);
              MixerBoxUtils.a(localMainPage, paramMessage.a, (arn)localObject, "vector", paramMessage.b);
              return;
              localMainPage.w();
              paramMessage = (apm)paramMessage.obj;
              localObject = localMainPage.a(paramMessage.d, "dj", 0, paramMessage.c);
              MixerBoxUtils.a(localMainPage, paramMessage.d, (arn)localObject, "dj", paramMessage.c);
              return;
              MainPage.c(localMainPage, (String)paramMessage.obj);
              return;
              localMainPage.runOnUiThread(new Runnable()
              {
                public final void run()
                {
                  MainPage.p(localMainPage);
                }
              });
              return;
              if (MainPage.f)
              {
                MainPage.f = false;
                if (localMainPage.aJ != null) {
                  localMainPage.aJ.a(2130837985);
                }
                MixerBoxUtils.a(localMainPage, localMainPage.getResources().getString(2131230988), 0, new boolean[] { false });
                return;
              }
              localMainPage.h = 0;
              MainPage.q(localMainPage);
              MainPage.f = true;
              if (localMainPage.aJ != null) {
                localMainPage.aJ.a(2130837986);
              }
              MixerBoxUtils.a(localMainPage, localMainPage.getResources().getString(2131230989), 0, new boolean[] { false });
              return;
              if (MainPage.e == 1)
              {
                MainPage.e = 2;
                if (localMainPage.aJ != null) {
                  localMainPage.aJ.b(2130837978);
                }
                MixerBoxUtils.a(localMainPage, localMainPage.getResources().getString(2131230969), 0, new boolean[] { false });
                return;
              }
              MainPage.e = 1;
              if (localMainPage.aJ != null) {
                localMainPage.aJ.b(2130837977);
              }
              MixerBoxUtils.a(localMainPage, localMainPage.getResources().getString(2131230968), 0, new boolean[] { false });
              return;
              paramMessage = new Intent(localMainPage, MainPage.class);
              paramMessage.addFlags(131072);
              localMainPage.startActivity(paramMessage);
              return;
              MainPage.b(localMainPage);
              return;
              MainPage.r(localMainPage);
              return;
              localMainPage.runOnUiThread(new Runnable()
              {
                public final void run()
                {
                  localMainPage.a(false);
                  MixerBoxUtils.f(localMainPage, "ClickPlayButtonOfMultitaskingDialog");
                }
              });
              return;
              localMainPage.a(2, ((Integer)paramMessage.obj).intValue());
              return;
            } while ((localMainPage.I == null) || (localMainPage.aJ == null));
            if (localMainPage.b == 1) {}
            while (i != 0)
            {
              localMainPage.aJ.a("javascript:loadVideoById('" + localMainPage.I.d + "'," + localMainPage.H + ")");
              return;
              i = 0;
            }
            localMainPage.aJ.a("javascript:cueVideoById('" + localMainPage.I.d + "'," + localMainPage.H + ")");
            return;
          } while ((localMainPage.I == null) || (localMainPage.aJ == null));
          localMainPage.aJ.f("javascript:cueVideoById('" + localMainPage.I.d + "'," + localMainPage.H + ")");
          return;
        } while ((localMainPage.aJ == null) || (!localMainPage.aJ.a()));
        localMainPage.runOnUiThread(new Runnable()
        {
          public final void run()
          {
            localMainPage.d((String)paramMessage.obj);
          }
        });
        return;
      case 30240: 
        localObject = new StringBuilder("onStateChanged (cast version): ");
        if (localMainPage.aJ != null) {}
        for (;;)
        {
          ((StringBuilder)localObject).append(bool1);
          if ((localMainPage.aJ == null) || (!localMainPage.aJ.a())) {
            break;
          }
          localMainPage.runOnUiThread(new Runnable()
          {
            public final void run()
            {
              localMainPage.c(Integer.valueOf((String)paramMessage.obj).intValue());
            }
          });
          return;
          bool1 = false;
        }
      case 30239: 
        localObject = new StringBuilder("onShouldPlayChanged (cast version): ");
        if (localMainPage.aJ != null) {}
        for (bool1 = bool2;; bool1 = false)
        {
          ((StringBuilder)localObject).append(bool1);
          if ((localMainPage.aJ == null) || (!localMainPage.aJ.a())) {
            break;
          }
          localMainPage.runOnUiThread(new Runnable()
          {
            public final void run()
            {
              localMainPage.d(Integer.valueOf((String)paramMessage.obj).intValue());
            }
          });
          return;
        }
      }
      localMainPage.runOnUiThread(new Runnable()
      {
        public final void run()
        {
          MainPage.s(localMainPage);
        }
      });
    }
  }
  
  final class c
    extends ArrayAdapter<a>
    implements Filterable
  {
    MyAutoCompleteTextView a;
    private ArrayList<a> c = null;
    
    public c(Context paramContext, MyAutoCompleteTextView paramMyAutoCompleteTextView)
    {
      super(17367050);
      this.a = paramMyAutoCompleteTextView;
    }
    
    public final a a(int paramInt)
    {
      if ((this.c == null) || (paramInt >= this.c.size()) || (paramInt < 0)) {
        return null;
      }
      return (a)this.c.get(paramInt);
    }
    
    public final void a()
    {
      if (this.c != null) {
        this.c.clear();
      }
    }
    
    public final int getCount()
    {
      if (this.c != null) {
        return this.c.size();
      }
      return 0;
    }
    
    public final Filter getFilter()
    {
      new Filter()
      {
        protected final Filter.FilterResults performFiltering(CharSequence paramAnonymousCharSequence)
        {
          localFilterResults = new Filter.FilterResults();
          ArrayList localArrayList = new ArrayList();
          Object localObject1 = new ArrayList();
          int i;
          Object localObject2;
          if ((paramAnonymousCharSequence == null) || (paramAnonymousCharSequence.toString().length() == 0))
          {
            paramAnonymousCharSequence = MainPage.this.an.a.rawQuery("SELECT * FROM tableSearchHistory", null);
            int k = paramAnonymousCharSequence.getCount();
            int j = 10 - k;
            if (k > 0)
            {
              localArrayList.add(new MainPage.c.a(MainPage.c.this, 0, MainPage.this.getResources().getString(2131230971), null));
              paramAnonymousCharSequence.moveToLast();
              i = 0;
              while (i < k)
              {
                localObject2 = paramAnonymousCharSequence.getString(1);
                localArrayList.add(new MainPage.c.a(MainPage.c.this, 2, (String)localObject2, null));
                ((ArrayList)localObject1).add(localObject2);
                paramAnonymousCharSequence.moveToPrevious();
                i += 1;
              }
              localArrayList.add(new MainPage.c.a(MainPage.c.this, 3, MainPage.this.getResources().getString(2131230825), null));
            }
            paramAnonymousCharSequence.close();
            if (j > 0)
            {
              localArrayList.add(new MainPage.c.a(MainPage.c.this, 0, MainPage.this.getResources().getString(2131231009), null));
              paramAnonymousCharSequence = MainPage.v(MainPage.this).iterator();
              i = 0;
              while (paramAnonymousCharSequence.hasNext())
              {
                localObject2 = (String)paramAnonymousCharSequence.next();
                if (i >= j) {
                  break;
                }
                if (!((ArrayList)localObject1).contains(localObject2))
                {
                  localArrayList.add(new MainPage.c.a(MainPage.c.this, 1, (String)localObject2, null));
                  i += 1;
                }
              }
            }
            localFilterResults.values = localArrayList;
            localFilterResults.count = localArrayList.size();
            return localFilterResults;
          }
          paramAnonymousCharSequence = MainPage.n(paramAnonymousCharSequence.toString());
          try
          {
            paramAnonymousCharSequence = new JSONObject(new String(paramAnonymousCharSequence));
            if (!paramAnonymousCharSequence.isNull("suggest"))
            {
              paramAnonymousCharSequence = paramAnonymousCharSequence.getJSONObject("suggest");
              if (!paramAnonymousCharSequence.isNull("suggestions"))
              {
                localObject2 = paramAnonymousCharSequence.getJSONArray("suggestions");
                i = 0;
                while (i < ((JSONArray)localObject2).length())
                {
                  if (!((JSONArray)localObject2).getJSONObject(i).isNull("suggestion"))
                  {
                    localObject1 = "";
                    paramAnonymousCharSequence = (CharSequence)localObject1;
                    if (!((JSONArray)localObject2).getJSONObject(i).isNull("id"))
                    {
                      paramAnonymousCharSequence = (CharSequence)localObject1;
                      if (((JSONArray)localObject2).getJSONObject(i).getString("id").length() == 11) {
                        paramAnonymousCharSequence = arx.q(((JSONArray)localObject2).getJSONObject(i).getString("id"));
                      }
                    }
                    localArrayList.add(new MainPage.c.a(MainPage.c.this, 4, ((JSONArray)localObject2).getJSONObject(i).getString("suggestion"), paramAnonymousCharSequence));
                  }
                  i += 1;
                }
              }
            }
            return localFilterResults;
          }
          catch (Exception paramAnonymousCharSequence)
          {
            localFilterResults.values = localArrayList;
            localFilterResults.count = localArrayList.size();
          }
        }
        
        protected final void publishResults(CharSequence paramAnonymousCharSequence, final Filter.FilterResults paramAnonymousFilterResults)
        {
          MainPage.this.runOnUiThread(new Runnable()
          {
            public final void run()
            {
              MainPage.c.a(MainPage.c.this, (ArrayList)paramAnonymousFilterResults.values);
              if ((MainPage.c.a(MainPage.c.this) != null) && (MainPage.c.a(MainPage.c.this).size() > 0))
              {
                MainPage.c.this.notifyDataSetChanged();
                return;
              }
              MainPage.c.this.notifyDataSetInvalidated();
            }
          });
        }
      };
    }
    
    public final int getItemViewType(int paramInt)
    {
      if ((this.c != null) && (this.c.size() > paramInt) && (((a)this.c.get(paramInt)).c == 0)) {
        return 0;
      }
      return 1;
    }
    
    public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int i = getItemViewType(paramInt);
      Object localObject;
      if (i == 0)
      {
        if ((paramView == null) || (paramView.getTag() == null) || (((b)paramView.getTag()).a != i))
        {
          paramView = ((LayoutInflater)MainPage.this.getSystemService("layout_inflater")).inflate(2130903161, null);
          paramViewGroup = new b((byte)0);
          paramViewGroup.b = ((TextView)paramView.findViewById(2131624143));
          paramViewGroup.a = i;
          paramView.setTag(paramViewGroup);
        }
        for (;;)
        {
          localObject = a(paramInt);
          if (localObject != null) {
            paramViewGroup.b.setText(((a)localObject).a);
          }
          paramView.setEnabled(false);
          paramView.setOnClickListener(null);
          localObject = paramView;
          return localObject;
          paramViewGroup = (b)paramView.getTag();
        }
      }
      if ((paramView == null) || (paramView.getTag() == null) || (((b)paramView.getTag()).a != i))
      {
        paramView = ((LayoutInflater)MainPage.this.getSystemService("layout_inflater")).inflate(2130903160, null);
        paramViewGroup = new b((byte)0);
        paramViewGroup.b = ((TextView)paramView.findViewById(2131624371));
        paramViewGroup.c = ((ImageView)paramView.findViewById(2131624369));
        paramViewGroup.d = ((ImageView)paramView.findViewById(2131624419));
        paramViewGroup.d.setTag(Integer.valueOf(paramInt));
        paramViewGroup.a = i;
        paramView.setTag(paramViewGroup);
      }
      a localA;
      int j;
      for (;;)
      {
        paramViewGroup.d.setOnClickListener(new View.OnClickListener()
        {
          public final void onClick(View paramAnonymousView)
          {
            new StringBuilder("ivArrow clicked: ").append(paramAnonymousView.getTag());
            int i = ((Integer)paramAnonymousView.getTag()).intValue();
            paramAnonymousView = MainPage.c.this.a(i);
            if (paramAnonymousView != null)
            {
              MainPage.c.this.a.setText(paramAnonymousView.a);
              MainPage.c.this.a.setSelection(paramAnonymousView.a.length());
            }
          }
        });
        paramViewGroup.b.setMaxLines(1);
        localA = a(paramInt);
        paramInt = MixerBoxUtils.a(MainPage.this, 8);
        i = MixerBoxUtils.a(MainPage.this, 3);
        j = MixerBoxUtils.a(MainPage.this, 13);
        localObject = paramView;
        if (localA == null) {
          break;
        }
        switch (localA.c)
        {
        default: 
          return paramView;
        case 1: 
          paramViewGroup.c.setPadding(paramInt, paramInt, i, paramInt);
          paramViewGroup.c.setVisibility(8);
          paramViewGroup.d.setVisibility(0);
          paramViewGroup.b.setTextSize(15.0F);
          paramViewGroup.b.setText(localA.a);
          paramViewGroup.b.setTextColor(MainPage.this.getResources().getColor(2131558410));
          return paramView;
          paramViewGroup = (b)paramView.getTag();
          paramViewGroup.d.setTag(Integer.valueOf(paramInt));
        }
      }
      paramViewGroup.c.setPadding(paramInt, paramInt, i, paramInt);
      paramViewGroup.c.setVisibility(0);
      MixerBoxUtils.a(getContext(), 2130838001, paramViewGroup.c, 8);
      paramViewGroup.d.setVisibility(0);
      paramViewGroup.b.setTextSize(15.0F);
      paramViewGroup.b.setText(localA.a);
      paramViewGroup.b.setTextColor(MainPage.this.getResources().getColor(2131558410));
      return paramView;
      paramViewGroup.c.setPadding(j, paramInt, paramInt, paramInt);
      paramViewGroup.c.setVisibility(0);
      MixerBoxUtils.a(getContext(), 2130837784, paramViewGroup.c, 8);
      paramViewGroup.b.setTextSize(13.0F);
      paramViewGroup.b.setText(localA.a);
      paramViewGroup.b.setTextColor(MainPage.this.getResources().getColor(2131558412));
      paramViewGroup.d.setVisibility(8);
      return paramView;
      paramViewGroup.c.setPadding(paramInt, paramInt, i, paramInt);
      try
      {
        MixerBoxUtils.a(MainPage.this, localA.b, paramViewGroup.c, 22, MainPage.this.getResources().getColor(2131558412), 0, 3);
        paramViewGroup.c.setVisibility(0);
        paramViewGroup.b.setTextSize(15.0F);
        paramViewGroup.b.setText(localA.a);
        paramViewGroup.b.setTextColor(MainPage.this.getResources().getColor(2131558410));
        paramViewGroup.d.setVisibility(0);
        return paramView;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    public final int getViewTypeCount()
    {
      return 2;
    }
    
    public final class a
    {
      public String a;
      public String b;
      public int c;
      
      public a(int paramInt, String paramString1, String paramString2)
      {
        this.c = paramInt;
        this.a = paramString1;
        this.b = paramString2;
      }
      
      public final String toString()
      {
        return this.a;
      }
    }
    
    final class b
    {
      public int a;
      public TextView b;
      public ImageView c;
      public ImageView d;
      
      private b() {}
    }
  }
  
  final class d
    extends BroadcastReceiver
  {
    private d() {}
    
    public final void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
        switch (paramIntent.getIntExtra("state", -1))
        {
        }
      }
      do
      {
        do
        {
          do
          {
            return;
            if (MainPage.this.b != 1) {
              break;
            }
            if (MainPage.this.aJ != null) {
              MainPage.this.aJ.f("javascript:pauseVideo()");
            }
          } while (MainPage.this.aJ == null);
          MainPage.this.aJ.b(true);
          return;
        } while ((MyService.a == null) || (!MyService.a.isPlaying()));
        MyService.a.pause();
      } while (MainPage.this.aJ == null);
      MainPage.this.aJ.b(true);
    }
  }
  
  public static final class e
    extends Handler
  {
    WeakReference<MainPage> a;
    
    e(MainPage paramMainPage)
    {
      this.a = new WeakReference(paramMainPage);
    }
    
    public final void handleMessage(Message paramMessage)
    {
      MainPage localMainPage = (MainPage)this.a.get();
      super.handleMessage(paramMessage);
      if (localMainPage != null) {
        switch (paramMessage.what)
        {
        }
      }
      do
      {
        return;
      } while (MainPage.aq);
      if (localMainPage.aJ != null)
      {
        WindowPlayerService.j(localMainPage.aJ.b);
        MainPage.h(localMainPage).removeMessages(2);
        MainPage.h(localMainPage).sendEmptyMessageDelayed(2, 30000L);
        return;
      }
      localMainPage.c();
    }
  }
  
  public final class f
    implements Runnable
  {
    private HashMap<String, Integer> b = null;
    private ArrayList<String> c = null;
    private String d = null;
    
    public f(String paramString1, String paramString2)
    {
      this.c = new ArrayList();
      this.c.add(paramString1);
      this.d = paramString2;
    }
    
    public f(String paramString)
    {
      this.b = paramString;
      Object localObject;
      this.d = localObject;
    }
    
    public final void run()
    {
      if (((this.c == null) || (this.c.size() == 0)) && ((this.b == null) || (this.b.size() == 0))) {
        return;
      }
      JSONArray localJSONArray1 = MainPage.this.l();
      try
      {
        JSONArray localJSONArray2 = new JSONArray();
        Object localObject;
        if (this.c != null)
        {
          i = 0;
          while (i < this.c.size())
          {
            localObject = new JSONObject();
            ((JSONObject)localObject).put("source", this.d);
            ((JSONObject)localObject).put("name", this.c.get(i));
            ((JSONObject)localObject).put("count", 1);
            localJSONArray2.put(i, localObject);
            i += 1;
          }
        }
        if (this.b != null)
        {
          localObject = this.b.entrySet().iterator();
          i = 0;
          while (((Iterator)localObject).hasNext())
          {
            Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.put("source", this.d);
            localJSONObject.put("name", localEntry.getKey());
            localJSONObject.put("count", localEntry.getValue());
            localJSONArray2.put(i, localJSONObject);
            i += 1;
          }
        }
        int j = localJSONArray2.length();
        int i = 0;
        while ((j < 100) && (i < localJSONArray1.length()))
        {
          localJSONArray2.put(j, localJSONArray1.get(i));
          j += 1;
          i += 1;
        }
        asa.a(MainPage.this, localJSONArray2);
        MainPage.u(MainPage.this);
        return;
      }
      catch (JSONException localJSONException) {}
    }
  }
}
