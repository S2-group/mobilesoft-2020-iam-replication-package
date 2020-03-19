package com.damiapp.sdc.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.Vibrator;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.ViewFlipper;
import com.andexert.library.RippleView;
import com.damiapp.zxing.activity.CaptureActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.lyy.anyness.A2AanynessProfileView;
import com.lyy.apdatacable.A2AServerService;
import com.lyy.apdatacable.A2AinviteFriend;
import com.lyy.apdatacable.A2AjoinNetwork;
import com.lyy.apdatacable.A2AnearbyView;
import com.lyy.apdatacable.be;
import com.lyy.apdatacable.bh;
import com.lyy.apdatacable.i;
import com.lyy.filemanager.filedialog.ap;
import com.lyy.ftpservice.FTPServerService;
import com.lyy.ftpservice.NormalDataSocketFactory;
import com.lyy.ftpservice.SessionThread;
import com.lyy.ftpservice.SessionThread.STReceiver;
import com.lyy.ftpservice.SessionThread.Source;
import com.lyy.ftpservice.Util;
import com.lyy.softdatacable.HelpActivity;
import com.lyy.softdatacable.SettingsPreference;
import com.lyy.softdatacable.Softdatacable;
import com.lyy.softsync.AutoSyncView;
import com.lyy.widgets.CircularImageView;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi", "RtlHardcoded", "InflateParams"})
public class sdc_main_activity
  extends ActionBarActivity
  implements View.OnClickListener, SessionThread.STReceiver
{
  private static Timer bq;
  private static long br = 0L;
  public Handler A = new n(this);
  public BroadcastReceiver B = new y(this);
  com.lyy.filemanager.filedialog.aj C;
  com.lyy.filemanager.filedialog.as D = new an(this);
  private RippleView E;
  private RippleView F;
  private RippleView G;
  private RippleView H;
  private RippleView I;
  private RippleView J;
  private RippleView K;
  private ViewAnimator L;
  private RippleView M;
  private RippleView N;
  private RippleView O;
  private RippleView P;
  private RippleView Q;
  private int R = 0;
  private boolean S = false;
  private AdView T = null;
  private DrawerLayout U;
  private ao V;
  private ActionBarDrawerToggle W;
  private final int X = 10001;
  private String Y;
  private String Z = null;
  public com.lyy.softsync.a a;
  private com.lyy.filemanager.filedialog.k aA = new com.lyy.filemanager.filedialog.k();
  private boolean aB = false;
  private int aC = 0;
  private com.lyy.filemanager.filedialog.e aD;
  private ImageView aE;
  private TextView aF;
  private RelativeLayout aG;
  private HorizontalScrollView aH;
  private LinearLayout aI;
  private RelativeLayout aJ;
  private RelativeLayout aK;
  private RelativeLayout aL;
  private String aM;
  private String aN;
  private String aO;
  private String aP;
  private List aQ = new ArrayList();
  private Socket aR = null;
  private SessionThread aS = null;
  private boolean aT = false;
  private boolean aU = false;
  private boolean aV = false;
  private boolean aW = false;
  private boolean aX = false;
  private boolean aY = false;
  private com.lyy.apdatacable.k aZ;
  private boolean aa = false;
  private boolean ab = true;
  private boolean ac = false;
  private boolean ad = false;
  private boolean ae = false;
  private boolean af = false;
  private int ag;
  private int ah;
  private RelativeLayout ai;
  private RelativeLayout aj;
  private RelativeLayout ak;
  private RelativeLayout al;
  private RelativeLayout am;
  private RelativeLayout an;
  private RelativeLayout ao;
  private RelativeLayout ap;
  private RelativeLayout aq;
  private RelativeLayout ar;
  private RelativeLayout as;
  private RelativeLayout at;
  private RelativeLayout au;
  private ProgressDialog av;
  private com.lyy.filemanager.filedialog.a aw = null;
  private SharedPreferences ax;
  private MediaPlayer ay;
  private com.lyy.filemanager.filedialog.j az = new com.lyy.filemanager.filedialog.j();
  public FrameLayout b;
  private AdapterView.OnItemClickListener bA = new aj(this);
  private View.OnLongClickListener bB = new ak(this);
  private View.OnClickListener bC = new al(this);
  private SharedPreferences.OnSharedPreferenceChangeListener bD = new am(this);
  private String ba;
  private Messenger bb = new Messenger(new au(this));
  private Messenger bc = null;
  private RelativeLayout bd = null;
  private ArrayList be;
  private FrameLayout bf;
  private ImageView bg;
  private com.b.a.b.d bh;
  private Dialog bi = null;
  private ProgressBar bj = null;
  private TextView bk = null;
  private TextView bl = null;
  private ListAdapter bm;
  private com.lyy.filemanager.filedialog.d[] bn;
  private long bo = 0L;
  private boolean bp = false;
  private long bs = -1L;
  private Bundle bt = null;
  private com.lyy.widgets.a bu = null;
  private ArrayList bv;
  private CharSequence bw = "";
  private boolean bx = false;
  private ServiceConnection by = new b(this);
  private AdapterView.OnItemLongClickListener bz = new ah(this);
  public FrameLayout c;
  public RelativeLayout d;
  public final int e = 2;
  public ListView f;
  public GridView g;
  public AbsListView h;
  public com.lyy.filemanager.filedialog.ag i;
  public com.lyy.filemanager.filedialog.g j;
  public com.lyy.filemanager.filedialog.ag k;
  int l = -1;
  public com.lyy.filemanager.filedialog.a.b m;
  public Handler n;
  public String o;
  public int p;
  public com.lyy.filemanager.filedialog.f q = null;
  public String r;
  public String s;
  public com.lyy.filemanager.filedialog.l t;
  public LinearLayout u;
  public LinearLayout v;
  public LinearLayout w;
  public ArrayList x;
  boolean y = true;
  boolean z = true;
  
  public sdc_main_activity() {}
  
  private void A()
  {
    this.g.setGravity(119);
    this.g.setAdapter(this.j);
    this.j.a(this.g);
    this.g.setOnItemClickListener(this.bA);
    this.g.setOnItemLongClickListener(this.bz);
  }
  
  private final void B()
  {
    this.ai.setOnClickListener(this.bC);
    this.aj.setOnClickListener(this.bC);
    this.al.setOnClickListener(this.bC);
    this.ak.setOnClickListener(this.bC);
    this.am.setOnClickListener(this.bC);
    this.an.setOnClickListener(this.bC);
    this.ao.setOnClickListener(this.bC);
    this.ap.setOnClickListener(this.bC);
    this.aq.setOnClickListener(this.bC);
    this.ar.setOnClickListener(this.bC);
    this.at.setOnClickListener(this.bC);
    this.as.setOnClickListener(this.bC);
    this.au.setOnClickListener(this.bC);
    this.ai.setOnLongClickListener(this.bB);
    this.aj.setOnLongClickListener(this.bB);
    this.al.setOnLongClickListener(this.bB);
    this.ak.setOnLongClickListener(this.bB);
    this.am.setOnLongClickListener(this.bB);
    this.an.setOnLongClickListener(this.bB);
    this.ao.setOnLongClickListener(this.bB);
    this.ap.setOnLongClickListener(this.bB);
    this.aq.setOnLongClickListener(this.bB);
    this.ar.setOnLongClickListener(this.bB);
    this.at.setOnLongClickListener(this.bB);
    this.as.setOnLongClickListener(this.bB);
    this.au.setOnLongClickListener(this.bB);
    this.aJ = ((RelativeLayout)findViewById(2131362116));
    this.aK = ((RelativeLayout)findViewById(2131362119));
    this.aL = ((RelativeLayout)findViewById(2131362122));
    this.aJ.setOnClickListener(this);
    this.aK.setOnClickListener(this);
    this.aL.setOnClickListener(this);
    this.bd.setOnClickListener(this);
  }
  
  private void C()
  {
    if ((!A2AServerService.b()) && (this.aZ == null)) {
      return;
    }
    try
    {
      Object localObject = com.damiapp.a.b.g(this);
      ((AlertDialog.Builder)localObject).setTitle(2131427429);
      ((AlertDialog.Builder)localObject).setMessage(2131427430);
      ((AlertDialog.Builder)localObject).setPositiveButton(2131427433, new s(this));
      ((AlertDialog.Builder)localObject).setNegativeButton(2131427415, new t(this));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
      ((AlertDialog)localObject).show();
      return;
    }
    catch (Exception localException) {}
  }
  
  private void D()
  {
    int i2 = 0;
    String str = j();
    int i1;
    if (d() == 1)
    {
      if (str.equals("/"))
      {
        C();
        return;
      }
      e(1);
      i1 = 0;
      label37:
      if (i1 < this.x.size()) {
        break label107;
      }
      i1 = i2;
      label50:
      if (i1 == 0) {
        break label134;
      }
    }
    label107:
    label134:
    for (str = "/";; str = new File(str).getParent())
    {
      a(str);
      return;
      if (d() == 0)
      {
        if (str.equals(com.lyy.filemanager.filedialog.a.a.a(this)))
        {
          C();
          return;
        }
        if (!str.equals("/")) {
          break;
        }
        C();
        return;
      }
      C();
      return;
      if (str.equals(this.x.get(i1)))
      {
        i1 = 1;
        break label50;
      }
      i1 += 1;
      break label37;
    }
  }
  
  private void E()
  {
    AlertDialog.Builder localBuilder = com.damiapp.a.b.g(this);
    localBuilder.setTitle(getResources().getString(2131427526));
    localBuilder.setItems(getResources().getStringArray(2131558401), new u(this));
    localBuilder.show();
  }
  
  private void F()
  {
    Object localObject = com.damiapp.a.b.g(this);
    ((AlertDialog.Builder)localObject).setTitle(getResources().getString(2131427497));
    ((AlertDialog.Builder)localObject).setItems(getResources().getStringArray(2131558402), new v(this));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
    ((AlertDialog)localObject).show();
  }
  
  private List G()
  {
    for (;;)
    {
      ArrayList localArrayList;
      PackageManager localPackageManager;
      String str1;
      String str2;
      long l2;
      long l1;
      File localFile;
      long l3;
      try
      {
        localArrayList = new ArrayList();
        localArrayList.clear();
        localPackageManager = getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
        if (!localIterator.hasNext()) {}
        switch (this.aC)
        {
        case 0: 
          Collections.sort(localArrayList, this.az);
          return localArrayList;
          localObject3 = (PackageInfo)localIterator.next();
          if ((((PackageInfo)localObject3).applicationInfo.flags & 0x1) != 0)
          {
            if (!this.aa) {
              continue;
            }
            str1 = ((PackageInfo)localObject3).packageName;
            str2 = ((PackageInfo)localObject3).applicationInfo.loadLabel(localPackageManager).toString();
            l2 = 0L;
            l1 = l2;
          }
          break;
        }
      }
      finally {}
      try
      {
        localFile = new File(localPackageManager.getApplicationInfo(str1, 0).sourceDir);
        l1 = l2;
        l2 = localFile.lastModified();
        l1 = l2;
        l3 = localFile.length();
        l1 = l3;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        Object localObject2 = null;
        l2 = l1;
        l1 = 0L;
        continue;
        this.az.a(true);
        Collections.sort(localArrayList, this.az);
      }
      Object localObject3 = ((PackageInfo)localObject3).applicationInfo.loadIcon(localPackageManager);
      localArrayList.add(new com.lyy.filemanager.filedialog.aj(str2, localFile.getAbsolutePath(), 15, l1, l2, false, (Drawable)localObject3, str1));
      continue;
      if (this.aa)
      {
        continue;
        continue;
        this.az.a(false);
        Collections.sort(localArrayList, this.az);
        continue;
        this.aA.a(true);
        Collections.sort(localArrayList, this.aA);
        continue;
        this.aA.a(false);
        Collections.sort(localArrayList, this.aA);
        continue;
        Collections.sort(localArrayList, Collections.reverseOrder());
        continue;
        Collections.sort(localArrayList);
      }
    }
  }
  
  private void H()
  {
    this.f = ((ListView)findViewById(2131362127));
    this.g = ((GridView)findViewById(2131362128));
    this.ai = ((RelativeLayout)findViewById(2131362130));
    this.aj = ((RelativeLayout)findViewById(2131362150));
    this.al = ((RelativeLayout)findViewById(2131362134));
    this.ak = ((RelativeLayout)findViewById(2131362152));
    this.am = ((RelativeLayout)findViewById(2131362132));
    this.an = ((RelativeLayout)findViewById(2131362136));
    this.ao = ((RelativeLayout)findViewById(2131362139));
    this.ap = ((RelativeLayout)findViewById(2131362141));
    this.aq = ((RelativeLayout)findViewById(2131362154));
    this.ar = ((RelativeLayout)findViewById(2131362143));
    this.at = ((RelativeLayout)findViewById(2131362147));
    this.as = ((RelativeLayout)findViewById(2131362145));
    this.au = ((RelativeLayout)findViewById(2131362156));
    this.u = ((LinearLayout)findViewById(2131362129));
    this.v = ((LinearLayout)findViewById(2131362138));
    this.w = ((LinearLayout)findViewById(2131362149));
    this.aE = ((ImageView)findViewById(2131362110));
    this.aF = ((TextView)findViewById(2131362111));
    this.aH = ((HorizontalScrollView)findViewById(2131362113));
    this.aI = ((LinearLayout)findViewById(2131362114));
    this.aG = ((RelativeLayout)findViewById(2131362109));
    this.bd = ((RelativeLayout)findViewById(2131362105));
  }
  
  private void I()
  {
    if (Build.VERSION.SDK_INT < 14)
    {
      J();
      return;
    }
    try
    {
      Object localObject = com.damiapp.a.b.g(this);
      String str = getResources().getString(2131427581);
      ((AlertDialog.Builder)localObject).setTitle(2131427902);
      ((AlertDialog.Builder)localObject).setMessage(str);
      ((AlertDialog.Builder)localObject).setPositiveButton(2131427433, new x(this));
      ((AlertDialog.Builder)localObject).setNegativeButton(2131427415, new z(this));
      localObject = ((AlertDialog.Builder)localObject).create();
      ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
      ((AlertDialog)localObject).show();
      return;
    }
    catch (Exception localException) {}
  }
  
  private void J()
  {
    try
    {
      com.lyy.widgets.b localB = new com.lyy.widgets.b(this);
      String str = getResources().getString(2131427581);
      localB.b(2131427902);
      localB.a(str);
      localB.a(2131427433, new aa(this));
      localB.b(2131427415, new ab(this));
      localB.a().show();
      return;
    }
    catch (Exception localException) {}
  }
  
  private void K()
  {
    int i1 = ((WifiManager)getSystemService("wifi")).getConnectionInfo().getIpAddress();
    if (i1 == 0) {}
    do
    {
      return;
      String str = Util.intToInet(i1).getHostAddress();
      Context localContext = getApplicationContext();
      n();
      if (FTPServerService.isRunning()) {
        localContext.stopService(new Intent(localContext, FTPServerService.class));
      }
      A2AServerService.c(str);
      A2AServerService.f(com.damiapp.a.b.a(8, 1));
      A2AServerService.g(com.damiapp.a.b.a(8, 1));
      localContext.startService(new Intent(localContext, A2AServerService.class));
      this.aW = true;
      this.aX = true;
    } while (this.aY);
    N();
  }
  
  private void L()
  {
    a(2, 1, getResources().getString(2131427874));
    Intent localIntent = new Intent();
    localIntent.setClass(this, A2AjoinNetwork.class);
    startActivityForResult(localIntent, 4);
  }
  
  private void M()
  {
    a(2, 1, getResources().getString(2131427540));
    WifiManager localWifiManager = (WifiManager)getSystemService("wifi");
    b(localWifiManager);
    a(2, 1, getResources().getString(2131427546));
    this.aV = true;
    if (!a(localWifiManager))
    {
      this.aV = false;
      a(4, 0, getResources().getString(2131427583));
      return;
    }
    bq = new Timer();
    bq.schedule(new ba(this), 30000L);
  }
  
  private void N()
  {
    this.aY = getApplicationContext().bindService(new Intent(getApplicationContext(), A2AServerService.class), this.by, 1);
  }
  
  private void O()
  {
    if ((!this.aY) || (this.bc != null)) {}
    try
    {
      Message localMessage = Message.obtain(null, 2);
      localMessage.replyTo = this.bb;
      this.bc.send(localMessage);
      getApplicationContext().unbindService(this.by);
      this.aY = false;
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  private boolean P()
  {
    Iterator localIterator;
    if (this.aQ.size() > 0) {
      localIterator = this.aQ.iterator();
    }
    i localI;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localI = (i)localIterator.next();
    } while ((localI.b() != 1) && (localI.b() != 2));
    return true;
  }
  
  public static final int a(String paramString, File paramFile)
  {
    int i2 = 9;
    int i1;
    if (paramFile.isDirectory()) {
      i1 = 1;
    }
    do
    {
      do
      {
        return i1;
        i1 = i2;
      } while (paramString == null);
      paramString = paramString.toLowerCase(Locale.getDefault());
      if (paramString.equals("txt")) {
        return 2;
      }
      if ((paramString.equals("html")) || (paramString.equals("htm")) || (paramString.equals("chm")) || (paramString.equals("xml"))) {
        return 3;
      }
      if ((paramString.equals("jpeg")) || (paramString.equals("jpg")) || (paramString.equals("bmp")) || (paramString.equals("gif")) || (paramString.equals("png"))) {
        return 6;
      }
      if ((paramString.equals("rmvb")) || (paramString.equals("rmb")) || (paramString.equals("mkv")) || (paramString.equals("avi")) || (paramString.equals("wmv")) || (paramString.equals("mp4")) || (paramString.equals("3gp")) || (paramString.equals("flv"))) {
        return 4;
      }
      if ((paramString.equals("mp3")) || (paramString.equals("wav")) || (paramString.equals("wma")) || (paramString.equals("ogg"))) {
        return 5;
      }
      if (paramString.equals("apk")) {
        return 7;
      }
      if ((paramString.equals("zip")) || (paramString.equals("tar")) || (paramString.equals("bar")) || (paramString.equals("bz2")) || (paramString.equals("bz")) || (paramString.equals("gz")) || (paramString.equals("rar"))) {
        return 8;
      }
      if (paramString.equals("pdf")) {
        return 11;
      }
      if ((paramString.equals("ppt")) || (paramString.equals("pptx"))) {
        return 12;
      }
      if ((paramString.equals("doc")) || (paramString.equals("docx")) || (paramString.equals("dotx")) || (paramString.equals("dotm")) || (paramString.equals("dot"))) {
        return 13;
      }
      if (paramString.equals("xls")) {
        break;
      }
      i1 = i2;
    } while (!paramString.equals("csv"));
    return 14;
  }
  
  private int a(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, String paramString5)
  {
    try
    {
      int i1 = Integer.parseInt(paramString2);
      this.aN = paramString3;
      this.aR = new Socket(InetAddress.getByName(paramString1), i1);
      this.aR.setReuseAddress(true);
      this.aR.setSoTimeout(0);
      this.aS = new SessionThread(this.aR, new NormalDataSocketFactory(), SessionThread.Source.LOCAL, paramString4, this.aP, paramBoolean, this, paramString5);
      this.aS.start();
      return 0;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      if (this.aR == null) {}
    }
    try
    {
      this.aR.close();
      return -1;
    }
    catch (IOException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
      }
    }
  }
  
  private String a(Uri paramUri)
  {
    if (paramUri == null) {}
    do
    {
      do
      {
        String str;
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
                  return null;
                  str = paramUri.getScheme();
                } while (str == null);
                if (!str.equals("file")) {
                  break;
                }
              } while (paramUri.getPath() == null);
              paramUri = new File(paramUri.getPath());
            } while (!paramUri.exists());
            return paramUri.getAbsolutePath();
          } while (!str.equals("content"));
          str = getIntent().getType();
        } while ((str == null) || ((!str.startsWith("image/")) && (!str.startsWith("video/")) && (!str.startsWith("audio/"))));
        paramUri = b(paramUri);
      } while (paramUri == null);
      paramUri = new File(paramUri);
    } while (!paramUri.exists());
    return paramUri.getAbsolutePath();
  }
  
  private ArrayList a(ArrayList paramArrayList)
  {
    if (paramArrayList == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    for (;;)
    {
      if (!paramArrayList.hasNext()) {
        return localArrayList;
      }
      Object localObject = (Uri)paramArrayList.next();
      if (localObject != null)
      {
        String str = ((Uri)localObject).getScheme();
        if (str.equals("file"))
        {
          if (((Uri)localObject).getPath() != null)
          {
            localObject = new File(((Uri)localObject).getPath());
            if (((File)localObject).exists()) {
              localArrayList.add(((File)localObject).getAbsolutePath());
            }
          }
        }
        else if (str.equals("content"))
        {
          localObject = b((Uri)localObject);
          if (localObject != null)
          {
            localObject = new File((String)localObject);
            if (((File)localObject).exists()) {
              localArrayList.add(((File)localObject).getAbsolutePath());
            }
          }
        }
      }
    }
  }
  
  private void a(int paramInt, boolean paramBoolean)
  {
    try
    {
      if (!this.af)
      {
        if (this.ay != null)
        {
          this.ay.release();
          this.ay = null;
        }
        this.ay = MediaPlayer.create(getApplicationContext(), paramInt);
        if (this.ay != null) {
          this.ay.start();
        }
      }
      if (paramBoolean) {
        ((Vibrator)getSystemService("vibrator")).vibrate(500L);
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void a(AdView paramAdView)
  {
    if (this.c != null) {
      return;
    }
    try
    {
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -2);
      localLayoutParams.gravity = 80;
      View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903074, null, false);
      this.c = ((FrameLayout)localView.findViewById(2131361960));
      this.d = ((RelativeLayout)localView.findViewById(2131361961));
      this.d.addView(paramAdView);
      paramAdView = new ImageView(this);
      paramAdView.setImageResource(2130837654);
      paramAdView.setBackgroundResource(2130837791);
      paramAdView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams1.addRule(10);
      localLayoutParams1.addRule(11);
      paramAdView.setOnClickListener(new g(this));
      this.d.addView(paramAdView, localLayoutParams1);
      addContentView(localView, localLayoutParams);
      paramAdView = AnimationUtils.loadAnimation(this, 2130968585);
      this.c.startAnimation(paramAdView);
      this.c.setVisibility(0);
      return;
    }
    catch (Exception paramAdView) {}
  }
  
  private void a(String paramString1, String paramString2)
  {
    Dialog localDialog = new Dialog(this, 2131493130);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903043);
    ListView localListView = (ListView)localDialog.findViewById(2131361880);
    localListView.setAdapter(new be(this, 2130903044, this.aQ));
    localListView.setOnItemClickListener(new ag(this, localDialog, paramString1, paramString2));
    localDialog.show();
  }
  
  private void a(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    if ((this.bu != null) && (this.bu.isShowing()))
    {
      this.bu.dismiss();
      this.bu = null;
    }
    Object localObject = Message.obtain();
    ((Message)localObject).what = 20001;
    Bundle localBundle = new Bundle();
    localBundle.putString("meid", paramString3);
    ((Message)localObject).obj = localBundle;
    this.A.sendMessageDelayed((Message)localObject, 30000L);
    localObject = new com.lyy.widgets.b(this, paramString3);
    ((com.lyy.widgets.b)localObject).b(2131427901);
    ((com.lyy.widgets.b)localObject).a(getString(2131427550, new Object[] { paramString1 }) + " ? (" + paramString2 + ")");
    ((com.lyy.widgets.b)localObject).a(2131427551, new m(this, paramString1, paramString3, paramInt));
    ((com.lyy.widgets.b)localObject).b(2131427552, new o(this, paramString3, paramInt));
    this.bu = ((com.lyy.widgets.b)localObject).a();
    this.bu.show();
  }
  
  private void a(ArrayList paramArrayList, File paramFile, String paramString, boolean paramBoolean)
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null) {}
    for (;;)
    {
      return;
      int i2 = arrayOfFile.length;
      String str = paramString;
      if (!paramBoolean) {
        str = paramString.toLowerCase(Locale.getDefault());
      }
      this.bw = paramFile.getAbsolutePath();
      this.n.sendEmptyMessage(20);
      int i1 = 0;
      while (i1 < i2)
      {
        if ((this.ad) || (!arrayOfFile[i1].isHidden())) {
          break label89;
        }
        label80:
        i1 += 1;
      }
      continue;
      try
      {
        label89:
        Thread.sleep(20L);
        if (!this.bx) {
          continue;
        }
        paramString = arrayOfFile[i1].getName();
        paramFile = paramString;
        if (!paramBoolean) {
          paramFile = paramString.toLowerCase(Locale.getDefault());
        }
        if (paramFile.matches(str))
        {
          paramFile = new Bundle();
          paramFile.putString("SEARCHFILEPATH", arrayOfFile[i1].getAbsolutePath());
          paramString = this.n.obtainMessage(22);
          paramString.setData(paramFile);
          this.n.sendMessage(paramString);
        }
        if (!arrayOfFile[i1].isDirectory()) {
          break label80;
        }
        a(paramArrayList, arrayOfFile[i1], str, paramBoolean);
      }
      catch (InterruptedException paramFile)
      {
        for (;;) {}
      }
    }
  }
  
  private void a(boolean paramBoolean, String paramString, int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("nearby_broadcast_ack");
    localIntent.putExtra("nearby_ack_value", paramBoolean);
    localIntent.putExtra("nearby_ack_fromIp", paramString);
    localIntent.putExtra("nearby_ack_fromPort", paramInt);
    sendBroadcast(localIntent);
  }
  
  private boolean a(WifiManager paramWifiManager)
  {
    WifiConfiguration localWifiConfiguration = new WifiConfiguration();
    localWifiConfiguration.allowedAuthAlgorithms.set(0);
    localWifiConfiguration.allowedProtocols.set(1);
    localWifiConfiguration.allowedProtocols.set(0);
    localWifiConfiguration.allowedKeyManagement.set(0);
    this.aM = this.ax.getString("dp_ssid", null);
    if (this.aM == null)
    {
      this.aM = com.damiapp.a.b.a(11, 100);
      localObject = this.ax.edit();
      ((SharedPreferences.Editor)localObject).putString("dp_ssid", this.aM);
      ((SharedPreferences.Editor)localObject).commit();
    }
    localWifiConfiguration.SSID = this.aM;
    Object localObject = getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f());
    this.ae = ((SharedPreferences)localObject).getBoolean("pref_keepMobileDataConnection", false);
    com.damiapp.a.b.a(localWifiConfiguration, ((SharedPreferences)localObject).getBoolean("pref_forceDhcp", false));
    return bh.a(paramWifiManager, localWifiConfiguration, true);
  }
  
  private String b(Uri paramUri)
  {
    for (;;)
    {
      try
      {
        localCursor = getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
        if (localCursor == null) {
          return null;
        }
        if (!localCursor.moveToFirst()) {
          continue;
        }
        paramUri = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
      }
      catch (Exception localException2)
      {
        Cursor localCursor;
        paramUri = null;
        continue;
        paramUri = null;
        continue;
      }
      try
      {
        localCursor.close();
        return paramUri;
      }
      catch (Exception localException1) {}
    }
    localException1.printStackTrace();
    return paramUri;
  }
  
  private void b(int paramInt, String paramString)
  {
    if (paramInt == -1) {}
    View localView;
    Button localButton;
    do
    {
      return;
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -2);
      localLayoutParams.gravity = 80;
      localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903079, null, false);
      this.b = ((FrameLayout)localView.findViewById(2131361967));
      ((ImageView)localView.findViewById(2131361969)).setOnClickListener(new d(this, paramInt));
      localButton = (Button)localView.findViewById(2131361970);
      localButton.setOnClickListener(new e(this, paramInt));
      addContentView(localView, localLayoutParams);
      if (paramInt == 1)
      {
        paramString = AnimationUtils.loadAnimation(this, 2130968585);
        this.b.startAnimation(paramString);
        this.b.setVisibility(0);
        return;
      }
    } while ((paramInt != 2) && (paramInt != 3));
    ((TextView)localView.findViewById(2131361968)).setText(paramString);
    if (paramInt == 2) {
      localButton.setText(2131427868);
    }
    paramString = AnimationUtils.loadAnimation(this, 2130968585);
    this.b.startAnimation(paramString);
    this.b.setVisibility(0);
  }
  
  private void b(int paramInt, boolean paramBoolean)
  {
    e(this.ag);
    if (d() == paramInt) {}
    do
    {
      return;
      if (d() == 1) {
        this.Z = j();
      }
      f(paramInt);
    } while (!paramBoolean);
    a(this.o);
  }
  
  private void b(WifiManager paramWifiManager)
  {
    bh.a(paramWifiManager, false);
    if (!this.ae)
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
      if ((localTelephonyManager.getDataState() == 2) || (localTelephonyManager.getDataState() == 1))
      {
        this.aT = true;
        bh.a(this, 2, Build.VERSION.SDK_INT);
      }
    }
    if ((paramWifiManager.getWifiState() == 3) || (paramWifiManager.getWifiState() == 2))
    {
      this.aU = true;
      paramWifiManager.setWifiEnabled(false);
    }
    if (FTPServerService.isRunning())
    {
      paramWifiManager = getApplicationContext();
      paramWifiManager.stopService(new Intent(paramWifiManager, FTPServerService.class));
    }
  }
  
  private void b(com.lyy.filemanager.filedialog.aj paramAj)
  {
    br = System.currentTimeMillis();
    TextView localTextView1;
    ImageView localImageView;
    if (paramAj.j())
    {
      this.bo = -1L;
      this.bp = true;
      this.bi = new Dialog(this, 2131493130);
      this.bi.requestWindowFeature(1);
      this.bi.setContentView(2130903117);
      this.bj = ((ProgressBar)this.bi.findViewById(2131362209));
      this.bj.setMax(100);
      localTextView1 = (TextView)this.bi.findViewById(2131362207);
      TextView localTextView2 = (TextView)this.bi.findViewById(2131362208);
      localImageView = (ImageView)this.bi.findViewById(2131362206);
      if (paramAj.i() != null) {
        break label356;
      }
      com.b.a.b.d localD = new com.b.a.b.e().a(2130837577).b(2130837788).c(2130837788).d(true).a(Bitmap.Config.RGB_565).a();
      String str = "file://" + paramAj.b();
      com.b.a.b.f.a().a(str, localImageView, localD);
      label209:
      localTextView2.setText(com.lyy.filemanager.filedialog.a.a.c(paramAj.b()));
      if (this.bp) {
        break label368;
      }
      localTextView1.setText(getResources().getString(2131427575) + " " + "(" + com.lyy.filemanager.filedialog.a.a.a(this.bo) + ")");
    }
    for (;;)
    {
      this.bk = ((TextView)this.bi.findViewById(2131362212));
      this.bl = ((TextView)this.bi.findViewById(2131362213));
      this.bk.setOnClickListener(this);
      this.bl.setOnClickListener(this);
      this.bi.show();
      return;
      this.bo = paramAj.d;
      this.bp = false;
      break;
      label356:
      localImageView.setImageDrawable(paramAj.i());
      break label209;
      label368:
      localTextView1.setText(getResources().getString(2131427577));
    }
  }
  
  private void b(String paramString)
  {
    Toast.makeText(getBaseContext(), paramString, 0).show();
  }
  
  private List c(String paramString)
  {
    int i3 = 1;
    int i2 = 0;
    ArrayList localArrayList;
    int i1;
    label49:
    label92:
    label101:
    Object localObject1;
    try
    {
      localArrayList = new ArrayList();
      localArrayList.clear();
      if (!paramString.equals("/")) {
        break label918;
      }
      i1 = 1;
    }
    finally {}
    if (i1 >= this.x.size()) {}
    Object localObject2;
    for (;;)
    {
      switch (this.aC)
      {
      case 0: 
        Collections.sort(localArrayList, this.az);
        return localArrayList;
        localObject1 = new File((String)this.x.get(i1));
        localObject2 = ((File)localObject1).getAbsolutePath();
        i2 = ((String)localObject2).lastIndexOf('.');
        if (i2 == -1) {}
        for (paramString = null;; paramString = ((String)localObject2).substring(i2 + 1).toLowerCase(Locale.getDefault()))
        {
          localArrayList.add(new com.lyy.filemanager.filedialog.aj((String)localObject2, ((File)localObject1).getAbsolutePath(), a(paramString, (File)localObject1), null, ((File)localObject1).isDirectory()));
          i1 += 1;
          break;
        }
        label207:
        localObject2 = new File(paramString).listFiles();
        if ((paramString.equals(this.Y)) && (d() == 0) && ((localObject2 == null) || (localObject2.length == 0)))
        {
          localArrayList.add(0, new com.lyy.filemanager.filedialog.aj(getString(2131427892), getString(2131427892), 16, null, false));
          localArrayList.add(1, new com.lyy.filemanager.filedialog.aj(getString(2131427893), getString(2131427892), 17, null, false));
          localArrayList.add(2, new com.lyy.filemanager.filedialog.aj(getString(2131427894), getString(2131427892), 18, null, false));
          localArrayList.add(3, new com.lyy.filemanager.filedialog.aj(getString(2131427895), getString(2131427895), 19, null, false));
          return localArrayList;
        }
        if ((localObject2 == null) || (localObject2.length == 0))
        {
          if (d() != 1)
          {
            if ((d() == 0) && (!paramString.equals(this.Y)))
            {
              break label932;
              label426:
              if (i1 < this.x.size()) {
                break label477;
              }
              i2 = 0;
              label439:
              if (i2 == 0) {
                break label502;
              }
              localArrayList.add(0, new com.lyy.filemanager.filedialog.aj(getString(2131427488), "/", 10, null, true));
            }
            for (;;)
            {
              return localArrayList;
              label477:
              i2 = i3;
              if (paramString.equals(this.x.get(i1))) {
                break label439;
              }
              i1 += 1;
              break;
              label502:
              localArrayList.add(0, new com.lyy.filemanager.filedialog.aj(getString(2131427488), com.lyy.filemanager.filedialog.a.a.d(paramString), 10, null, true));
            }
          }
        }
        else
        {
          i2 = localObject2.length;
          i1 = 0;
          label546:
          if (i1 >= i2)
          {
            if (d() == 1) {
              break label937;
            }
            if ((d() == 0) && (!paramString.equals(this.Y))) {
              break label937;
            }
          }
        }
        break;
      }
    }
    for (;;)
    {
      if (i1 >= this.x.size()) {}
      for (i1 = 0;; i1 = 1)
      {
        if (i1 == 0) {
          break label773;
        }
        localArrayList.add(0, new com.lyy.filemanager.filedialog.aj(getString(2131427488), "/", 10, i2, true));
        break;
        String str = localObject2[i1].getName();
        if ((!this.ad) && (localObject2[i1].isHidden())) {
          break label942;
        }
        i3 = str.lastIndexOf('.');
        if (i3 == -1) {}
        for (localObject1 = null;; localObject1 = str.substring(i3 + 1).toLowerCase(Locale.getDefault()))
        {
          localArrayList.add(new com.lyy.filemanager.filedialog.aj(str, localObject2[i1].getAbsolutePath(), a((String)localObject1, localObject2[i1]), null, localObject2[i1].isDirectory()));
          break;
        }
        if (!paramString.equals(this.x.get(i1))) {
          break label949;
        }
      }
      label773:
      localArrayList.add(0, new com.lyy.filemanager.filedialog.aj(getString(2131427488), com.lyy.filemanager.filedialog.a.a.d(paramString), 10, i2, true));
      break label49;
      this.az.a(true);
      Collections.sort(localArrayList, this.az);
      break label101;
      this.az.a(false);
      Collections.sort(localArrayList, this.az);
      break label101;
      this.aA.a(true);
      Collections.sort(localArrayList, this.aA);
      break label101;
      this.aA.a(false);
      Collections.sort(localArrayList, this.aA);
      break label101;
      Collections.sort(localArrayList, Collections.reverseOrder());
      break label101;
      Collections.sort(localArrayList);
      break label101;
      label918:
      i1 = 0;
      if (i1 == 0) {
        break label207;
      }
      i1 = i2;
      break;
      break label92;
      label932:
      i1 = 0;
      break label426;
      label937:
      i1 = 0;
      continue;
      label942:
      i1 += 1;
      break label546;
      label949:
      i1 += 1;
    }
  }
  
  private void c(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 3)) {
      try
      {
        if ((this.b != null) && (this.b.getVisibility() == 0)) {
          return;
        }
        this.T = new AdView(this);
        this.T.setAdUnitId("ca-app-pub-4229463104817235/2576741906");
        this.T.setAdSize(AdSize.SMART_BANNER);
        this.T.setAdListener(new f(this));
        Object localObject = new Bundle();
        ((Bundle)localObject).putString("color_bg", "FFFFFF");
        ((Bundle)localObject).putString("color_text", "222222");
        localObject = new AdMobExtras((Bundle)localObject);
        localObject = new AdRequest.Builder().addKeyword("game").addKeyword("shopping").addNetworkExtras((NetworkExtras)localObject).build();
        this.T.loadAd((AdRequest)localObject);
        return;
      }
      catch (Exception localException) {}
    }
  }
  
  private void d(int paramInt)
  {
    try
    {
      Object localObject1 = this.C.b();
      if (this.C.i)
      {
        a((String)localObject1);
        return;
      }
      Object localObject2;
      if ((d() == 2) || (this.aQ.size() > 0))
      {
        this.t.a((String)localObject1);
        this.t.a(paramInt);
        this.t.b(((com.lyy.filemanager.filedialog.aj)h().get(paramInt)).a());
        if (d() == 2) {
          this.t.c(((com.lyy.filemanager.filedialog.aj)h().get(paramInt)).c());
        }
        if (this.ag == 2)
        {
          localObject2 = new com.lyy.filemanager.filedialog.am(this, 2131493127);
          ((com.lyy.filemanager.filedialog.am)localObject2).a((String)localObject1);
          ((com.lyy.filemanager.filedialog.am)localObject2).show();
          return;
        }
        localObject2 = com.damiapp.a.b.g(this);
        localObject1 = ((com.lyy.filemanager.filedialog.aj)h().get(paramInt)).a();
        if (((com.lyy.filemanager.filedialog.aj)h().get(paramInt)).j()) {
          localObject1 = localObject1 + " (" + getResources().getString(2131427797) + ")";
        }
      }
      for (;;)
      {
        ((AlertDialog.Builder)localObject2).setTitle((CharSequence)localObject1);
        ((AlertDialog.Builder)localObject2).setAdapter(this.bm, new q(this));
        localObject1 = ((AlertDialog.Builder)localObject2).create();
        ((AlertDialog)localObject1).setCanceledOnTouchOutside(true);
        ((AlertDialog)localObject1).show();
        return;
        if (d() == 2)
        {
          localObject1 = localObject1 + " (app)";
          continue;
          this.t.f((String)localObject1);
          return;
        }
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void d(String paramString)
  {
    this.av = new ProgressDialog(this, 2131493125);
    this.av.setProgressStyle(0);
    this.av.setCancelable(false);
    this.av.setTitle(getString(2131427521) + " \"" + paramString + "\"");
    this.av.setMessage(getString(2131427474) + "\n");
    this.av.setIcon(2130837649);
    paramString = new w(this);
    this.av.setButton(-3, getString(2131427519), paramString);
  }
  
  private void e(int paramInt)
  {
    if (this.ah == paramInt) {
      return;
    }
    this.ah = paramInt;
    if (paramInt == 1)
    {
      this.k = this.i;
      this.f.setVisibility(0);
      this.g.setVisibility(8);
      this.h = this.f;
    }
    for (;;)
    {
      this.k.a(this.q);
      this.k.notifyDataSetChanged();
      return;
      this.k = this.j;
      this.g.setVisibility(0);
      this.f.setVisibility(8);
      this.h = this.g;
      this.g.getViewTreeObserver().addOnGlobalLayoutListener(new r(this));
    }
  }
  
  private void e(String paramString)
  {
    try
    {
      AlertDialog.Builder localBuilder = com.damiapp.a.b.g(this);
      localBuilder.setTitle(2131427903);
      localBuilder.setMessage(paramString);
      localBuilder.setPositiveButton(2131427890, new ac(this));
      localBuilder.show();
      return;
    }
    catch (Exception paramString) {}
  }
  
  private Drawable f(String paramString)
  {
    PackageManager localPackageManager = getPackageManager();
    PackageInfo localPackageInfo = localPackageManager.getPackageArchiveInfo(paramString, 0);
    localPackageInfo.applicationInfo.sourceDir = paramString;
    localPackageInfo.applicationInfo.publicSourceDir = paramString;
    return localPackageInfo.applicationInfo.loadIcon(localPackageManager);
  }
  
  private void f(int paramInt)
  {
    a(paramInt);
    TextView localTextView1 = (TextView)findViewById(2131362117);
    ImageView localImageView1 = (ImageView)findViewById(2131362118);
    TextView localTextView2 = (TextView)findViewById(2131362120);
    ImageView localImageView2 = (ImageView)findViewById(2131362121);
    TextView localTextView3 = (TextView)findViewById(2131362123);
    ImageView localImageView3 = (ImageView)findViewById(2131362124);
    localTextView1.setTextColor(getResources().getColor(2131165280));
    localTextView2.setTextColor(getResources().getColor(2131165280));
    localTextView3.setTextColor(getResources().getColor(2131165280));
    localImageView1.setVisibility(8);
    localImageView2.setVisibility(8);
    localImageView3.setVisibility(8);
    localTextView1.setTypeface(null, 0);
    localTextView2.setTypeface(null, 0);
    localTextView3.setTypeface(null, 0);
    this.aJ.setBackgroundResource(2130837586);
    this.aK.setBackgroundResource(2130837586);
    this.aL.setBackgroundResource(2130837586);
    ImageView localImageView4 = (ImageView)findViewById(2131362131);
    if (d() == 0)
    {
      this.o = com.lyy.filemanager.filedialog.a.a.a(this);
      this.aJ.setBackgroundResource(2130837587);
      localTextView1.setTextColor(getResources().getColor(2131165288));
      localTextView1.setTypeface(null, 1);
      localImageView1.setVisibility(0);
      localImageView4.setImageResource(2130837567);
    }
    do
    {
      return;
      if (d() == 1)
      {
        if (TextUtils.isEmpty(this.Z)) {}
        for (this.o = "/";; this.o = this.Z)
        {
          this.aK.setBackgroundResource(2130837587);
          localTextView2.setTextColor(getResources().getColor(2131165288));
          localTextView2.setTypeface(null, 1);
          localImageView2.setVisibility(0);
          localImageView4.setImageResource(2130837567);
          return;
        }
      }
    } while (d() != 2);
    this.o = "/data/app";
    this.aa = false;
    this.aL.setBackgroundResource(2130837587);
    localTextView3.setTextColor(getResources().getColor(2131165288));
    localTextView3.setTypeface(null, 1);
    localImageView3.setVisibility(0);
    localImageView4.setImageResource(2130837681);
  }
  
  private void p()
  {
    setSupportActionBar((Toolbar)findViewById(2131362227));
    this.U = ((DrawerLayout)findViewById(2131362226));
    this.U.setStatusBarBackgroundColor(getResources().getColor(2131165239));
    this.U.setDrawerListener(new ax(this, null));
    this.U.setDrawerTitle(8388611, getString(2131427361));
    this.V = t();
    this.V.a();
    this.W = new ActionBarDrawerToggle(this, this.U, 2131427825, 2131427826);
    this.L = ((ViewAnimator)findViewById(2131362092));
    this.M = ((RippleView)findViewById(2131362095));
    this.N = ((RippleView)findViewById(2131362097));
    this.O = ((RippleView)findViewById(2131362101));
    this.P = ((RippleView)findViewById(2131362099));
    this.Q = ((RippleView)findViewById(2131362103));
    this.M.setOnClickListener(this);
    this.N.setOnClickListener(this);
    this.O.setOnClickListener(this);
    this.P.setOnClickListener(this);
    this.Q.setOnClickListener(this);
    this.bh = new com.b.a.b.e().a(2130837577).b(2130837623).c(2130837623).b(true).c(true).d(true).a(Bitmap.Config.RGB_565).a();
    Object localObject = getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f());
    this.ad = ((SharedPreferences)localObject).getBoolean("pref_dpShowHiddenFiles", false);
    this.ae = ((SharedPreferences)localObject).getBoolean("pref_keepMobileDataConnection", false);
    this.af = ((SharedPreferences)localObject).getBoolean("pref_dpSilentMode", false);
    com.lyy.softdatacable.a.a(((SharedPreferences)localObject).getBoolean("prefs_enableMediaScan", true));
    try
    {
      this.aC = Integer.parseInt(((SharedPreferences)localObject).getString("prefs_sort_mode", "0"));
      str1 = ((SharedPreferences)localObject).getString("pref_sdc_token", "InitialUser");
      com.damiapp.c.a.a().a(this, str1);
      int i1 = ((SharedPreferences)localObject).getInt("pref_sdc_https", -1);
      com.damiapp.c.a.a().a(i1);
      this.o = com.lyy.filemanager.filedialog.a.a.a(this);
      this.Y = this.o;
      a(0);
      com.lyy.softdatacable.a.a(((SharedPreferences)localObject).getString("directpush_useremail", com.damiapp.a.b.e(this)));
      com.lyy.softdatacable.a.a(((SharedPreferences)localObject).getBoolean("prefs_enableMediaScan", true));
      this.x = com.damiapp.a.b.i(this);
      com.lyy.softdatacable.a.a(this.x);
      com.damiapp.a.b.d(this);
      this.a = new com.lyy.softsync.a(this);
      this.a.a();
      r();
    }
    catch (Exception localException)
    {
      try
      {
        String str1 = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        if (!((SharedPreferences)localObject).getString("prefs_newversion", "").equals(str1))
        {
          this.A.sendEmptyMessageDelayed(10001, 2000L);
          localObject = ((SharedPreferences)localObject).edit();
          ((SharedPreferences.Editor)localObject).putString("prefs_newversion", str1);
          ((SharedPreferences.Editor)localObject).commit();
        }
        return;
        localException = localException;
        this.aC = 0;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        for (;;)
        {
          String str2 = "unknown";
        }
      }
    }
  }
  
  private void q()
  {
    if (this.aQ.size() <= 0) {
      this.aI.removeAllViews();
    }
    int i1;
    label35:
    do
    {
      return;
      i1 = 0;
      if (i1 < this.aI.getChildCount()) {
        break;
      }
      i1 = 0;
    } while (i1 >= this.aQ.size());
    int i2 = 0;
    label50:
    label63:
    Object localObject1;
    Object localObject2;
    ImageView localImageView1;
    ImageView localImageView2;
    if (i2 >= this.aI.getChildCount())
    {
      i2 = 0;
      if (i2 == 0)
      {
        localObject1 = (RelativeLayout)((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903048, null);
        ((RelativeLayout)localObject1).setTag(((i)this.aQ.get(i1)).d());
        localObject2 = (i)this.aQ.get(i1);
        localImageView1 = (ImageView)((RelativeLayout)localObject1).findViewById(2131361904);
        localImageView2 = (ImageView)((RelativeLayout)localObject1).findViewById(2131361905);
        if (localImageView1 != null)
        {
          localImageView1.setTag("userPicView");
          if (TextUtils.isEmpty(((i)localObject2).e())) {
            break label690;
          }
          com.b.a.b.f.a().a(com.lyy.anyness.j.a(((i)localObject2).e(), com.lyy.softdatacable.a.c()), localImageView1, this.bh);
          label194:
          localImageView1.setOnClickListener(new c(this, (i)localObject2));
        }
        if (localImageView2 != null)
        {
          localImageView2.setTag("userActionView");
          switch (((i)localObject2).b())
          {
          }
        }
      }
    }
    for (;;)
    {
      this.aI.addView((View)localObject1, this.aI.getChildCount());
      i1 += 1;
      break label35;
      if (this.aI.getChildAt(i1).getTag() != null)
      {
        localObject1 = this.aI.getChildAt(i1).getTag().toString();
        i2 = 0;
      }
      for (;;)
      {
        if (i2 >= this.aQ.size()) {}
        for (i2 = 0;; i2 = 1)
        {
          if (i2 == 0) {
            this.aI.removeViewAt(i1);
          }
          i1 += 1;
          break;
          if (!((i)this.aQ.get(i2)).d().equals(localObject1)) {
            break label367;
          }
        }
        label367:
        i2 += 1;
      }
      if (this.aI.getChildAt(i2).getTag() != null)
      {
        localObject1 = this.aI.getChildAt(i2).getTag().toString();
        if (((i)this.aQ.get(i1)).d().equals(localObject1))
        {
          localObject1 = (RelativeLayout)this.aI.getChildAt(i2);
          i2 = 0;
          if (i2 >= ((RelativeLayout)localObject1).getChildCount())
          {
            i2 = 1;
            break label63;
          }
          if (((RelativeLayout)localObject1).getChildAt(i2).getTag() != null)
          {
            localObject2 = ((RelativeLayout)localObject1).getChildAt(i2).getTag().toString();
            if (!((String)localObject2).equals("userPicView")) {
              break label572;
            }
            localObject2 = (ImageView)((RelativeLayout)localObject1).getChildAt(i2);
            if (TextUtils.isEmpty(((i)this.aQ.get(i1)).e())) {
              break label561;
            }
            com.b.a.b.f.a().a(com.lyy.anyness.j.a(((i)this.aQ.get(i1)).e(), com.lyy.softdatacable.a.c()), (ImageView)localObject2, this.bh);
          }
          for (;;)
          {
            i2 += 1;
            break;
            label561:
            ((ImageView)localObject2).setImageResource(com.damiapp.a.b.b());
            continue;
            label572:
            if (((String)localObject2).equals("userActionView"))
            {
              localObject2 = (ImageView)((RelativeLayout)localObject1).getChildAt(i2);
              switch (((i)this.aQ.get(i1)).b())
              {
              default: 
                break;
              case 0: 
                ((ImageView)localObject2).setVisibility(8);
                break;
              case 1: 
                ((ImageView)localObject2).setVisibility(0);
                ((ImageView)localObject2).setImageResource(2130837789);
                break;
              case 2: 
                ((ImageView)localObject2).setVisibility(0);
                ((ImageView)localObject2).setImageResource(2130837636);
              }
            }
          }
        }
      }
      i2 += 1;
      break label50;
      label690:
      localImageView1.setImageResource(com.damiapp.a.b.b());
      break label194;
      localImageView2.setVisibility(8);
      continue;
      localImageView2.setVisibility(0);
      localImageView2.setImageResource(2130837789);
      continue;
      localImageView2.setVisibility(0);
      localImageView2.setImageResource(2130837636);
    }
  }
  
  private void r()
  {
    this.E = ((RippleView)findViewById(2131362223));
    this.E.setOnClickListener(this);
    this.F = ((RippleView)findViewById(2131362219));
    this.F.setOnClickListener(this);
    this.G = ((RippleView)findViewById(2131362221));
    this.G.setOnClickListener(this);
    this.H = ((RippleView)findViewById(2131362220));
    this.H.setOnClickListener(this);
    this.I = ((RippleView)findViewById(2131362224));
    this.I.setOnClickListener(this);
    this.J = ((RippleView)findViewById(2131362225));
    this.J.setOnClickListener(this);
    this.K = ((RippleView)findViewById(2131362222));
    this.K.setOnClickListener(this);
    Object localObject1 = this.a.a(com.damiapp.a.b.c(this));
    SharedPreferences localSharedPreferences = getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f());
    Object localObject2;
    Object localObject3;
    if (localObject1 == null)
    {
      localObject2 = com.damiapp.a.b.e(this);
      localObject1 = new com.lyy.anyness.a(com.damiapp.a.b.c(this), (String)localObject2, (String)localObject2, Build.MODEL, Build.MANUFACTURER, "", System.currentTimeMillis(), Build.MANUFACTURER + " " + Build.MODEL, 0L);
      this.a.b((com.lyy.anyness.a)localObject1);
      localObject3 = localSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject3).putString("directpush_username", (String)localObject2);
      ((SharedPreferences.Editor)localObject3).putString("directpush_useremail", (String)localObject2);
      ((SharedPreferences.Editor)localObject3).commit();
    }
    for (;;)
    {
      localObject2 = (TextView)findViewById(2131362218);
      CircularImageView localCircularImageView;
      RippleView localRippleView;
      if (TextUtils.isEmpty(((com.lyy.anyness.a)localObject1).h()))
      {
        ((TextView)localObject2).setVisibility(8);
        ((TextView)findViewById(2131362217)).setText(((com.lyy.anyness.a)localObject1).g());
        localObject2 = localSharedPreferences.getString("directpush_username", null);
        if ((TextUtils.isEmpty((CharSequence)localObject2)) || (!((String)localObject2).equals(((com.lyy.anyness.a)localObject1).g())))
        {
          localObject2 = localSharedPreferences.edit();
          ((SharedPreferences.Editor)localObject2).putString("directpush_username", ((com.lyy.anyness.a)localObject1).g());
          ((SharedPreferences.Editor)localObject2).putString("directpush_useremail", ((com.lyy.anyness.a)localObject1).b());
          ((SharedPreferences.Editor)localObject2).commit();
        }
        localCircularImageView = (CircularImageView)findViewById(2131362216);
        localCircularImageView.setOnClickListener(this);
        localRippleView = (RippleView)findViewById(2131362214);
        localRippleView.setOnClickListener(this);
        localObject2 = (CircularImageView)findViewById(2131362228);
        ((CircularImageView)localObject2).setOnClickListener(this);
        localObject3 = ((com.lyy.anyness.a)localObject1).e();
        if (!TextUtils.isEmpty((CharSequence)localObject3)) {
          break label585;
        }
        localCircularImageView.setVisibility(8);
        localRippleView.setVisibility(0);
        ((TextView)findViewById(2131362215)).setText(Character.toUpperCase(((com.lyy.anyness.a)localObject1).g().charAt(0)));
        ((CircularImageView)localObject2).setImageResource(com.damiapp.a.b.b());
      }
      for (;;)
      {
        b(localSharedPreferences.getInt("pref_sdc_update_avlb", -1), localSharedPreferences.getString("pref_sdc_update_content", ""));
        c(localSharedPreferences.getInt("pref_sdc_ad_type", -1));
        return;
        ((TextView)localObject2).setVisibility(0);
        ((TextView)localObject2).setText(((com.lyy.anyness.a)localObject1).h());
        break;
        label585:
        localCircularImageView.setVisibility(0);
        localRippleView.setVisibility(8);
        com.b.a.b.f.a().a(com.lyy.anyness.j.a((String)localObject3, com.lyy.softdatacable.a.c()), localCircularImageView, this.bh);
        localObject1 = localSharedPreferences.getString("directpush_useravatar", null);
        if ((TextUtils.isEmpty((CharSequence)localObject1)) || (!((String)localObject1).equals(localObject3)))
        {
          localObject1 = localSharedPreferences.edit();
          ((SharedPreferences.Editor)localObject1).putString("directpush_useravatar", (String)localObject3);
          ((SharedPreferences.Editor)localObject1).commit();
        }
        com.b.a.b.f.a().a(com.lyy.anyness.j.a((String)localObject3, com.lyy.softdatacable.a.c()), (ImageView)localObject2, this.bh);
      }
    }
  }
  
  private com.a.a.v s()
  {
    return new h(this);
  }
  
  private ao t()
  {
    return new ao(this);
  }
  
  @SuppressLint({"InflateParams"})
  private void u()
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 85;
    localLayoutParams.bottomMargin = getResources().getDimensionPixelOffset(2131230770);
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903127, null, false);
    this.bf = ((FrameLayout)localView.findViewById(2131362260));
    this.bg = ((ImageView)localView.findViewById(2131362261));
    addContentView(localView, localLayoutParams);
  }
  
  private void v()
  {
    String[] arrayOfString = getResources().getStringArray(2131558404);
    this.bn = new com.lyy.filemanager.filedialog.d[arrayOfString.length];
    this.bn[0] = new com.lyy.filemanager.filedialog.d(arrayOfString[0], 2130837705);
    this.bn[1] = new com.lyy.filemanager.filedialog.d(arrayOfString[1], 2130837672);
    this.bn[2] = new com.lyy.filemanager.filedialog.d(arrayOfString[2], 2130837708);
    this.bn[3] = new com.lyy.filemanager.filedialog.d(arrayOfString[3], 2130837709);
    this.bn[4] = new com.lyy.filemanager.filedialog.d(arrayOfString[4], 2130837710);
    this.bn[5] = new com.lyy.filemanager.filedialog.d(arrayOfString[5], 2130837712);
    this.bn[6] = new com.lyy.filemanager.filedialog.d(arrayOfString[6], 2130837670);
    this.bm = new l(this, this, 2130903116, 2131362204, this.bn);
  }
  
  private void w()
  {
    this.ac = false;
    this.ag = this.ax.getInt("FMviewStyle", 1);
    e(this.ag);
  }
  
  private void x()
  {
    this.n = new p(this);
  }
  
  private void y()
  {
    this.i = new com.lyy.filemanager.filedialog.ag(this, this.q, 1);
    this.j = new com.lyy.filemanager.filedialog.g(this, this.q, 2);
    this.t = new com.lyy.filemanager.filedialog.l(this);
  }
  
  private void z()
  {
    this.f.setItemsCanFocus(true);
    this.f.setAdapter(this.i);
    this.i.a(this.f);
    this.f.setOnItemLongClickListener(this.bz);
    this.f.setOnItemClickListener(this.bA);
  }
  
  public void a()
  {
    ((LinearLayout)findViewById(2131362158)).setVisibility(8);
    this.t.a();
  }
  
  public void a(int paramInt)
  {
    try
    {
      this.p = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  protected void a(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt2 == 0) && (this.R != 0))
    {
      com.damiapp.sdc.a.a.a(this.L, com.damiapp.sdc.a.b.b);
      this.R = 0;
      this.aQ.clear();
      q();
    }
    ViewFlipper localViewFlipper;
    if (paramInt2 == 2)
    {
      this.aE.clearAnimation();
      this.aG.setVisibility(8);
      if (this.aH.getVisibility() != 0) {
        this.aH.setVisibility(0);
      }
      localViewFlipper = (ViewFlipper)findViewById(2131362112);
      if ((paramInt1 != 6) && (this.aF.getVisibility() == 8))
      {
        localViewFlipper.stopFlipping();
        localViewFlipper.setVisibility(8);
        this.aF.setVisibility(0);
      }
      switch (paramInt1)
      {
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            if (paramInt2 == 1)
            {
              this.aH.setVisibility(8);
              if (this.aG.getVisibility() == 0) {
                break;
              }
              this.aG.setVisibility(0);
              break;
            }
          } while (paramInt2 != 0);
          this.aE.clearAnimation();
          this.aG.setVisibility(8);
          this.aH.setVisibility(8);
          break;
          Toast.makeText(this, paramString, 0).show();
          return;
          Toast.makeText(this, paramString, 0).show();
        } while (this.aQ.size() > 0);
        this.aH.setVisibility(8);
      } while (!this.aW);
      this.aG.setVisibility(0);
      this.aE.startAnimation(AnimationUtils.loadAnimation(this, 2130968587));
      this.aF.setText(2131427542);
      return;
      this.aF.setVisibility(8);
      localViewFlipper.setVisibility(0);
      localViewFlipper.startFlipping();
      return;
      this.aE.startAnimation(AnimationUtils.loadAnimation(this, 2130968587));
      this.aF.setText(paramString);
      return;
      e(paramString);
      return;
      Toast.makeText(this, paramString, 0).show();
      return;
      Toast.makeText(this, paramString, 0).show();
      return;
      Toast.makeText(this, paramString, 0).show();
      return;
      this.aE.startAnimation(AnimationUtils.loadAnimation(this, 2130968587));
      this.aF.setText(paramString);
      return;
      e(getResources().getString(2131427583));
    } while (bq == null);
    bq.cancel();
    bq.purge();
    bq = null;
  }
  
  protected void a(int paramInt, long paramLong)
  {
    if (this.aQ.size() <= 0) {}
    do
    {
      return;
      ((i)this.aQ.get(0)).a(paramInt);
      q();
      if (paramInt == 2)
      {
        str = "5100:" + this.aP + "\r\n";
        this.aS.writeString(str);
        return;
      }
    } while (paramInt != 1);
    if ((paramInt == 1) && (paramLong > 0L)) {}
    for (String str = "5200:" + this.aP + ":" + paramLong + "\r\n";; str = "5200:" + this.aP + "\r\n")
    {
      this.aS.writeString(str);
      return;
    }
  }
  
  protected void a(int paramInt, String paramString)
  {
    if ((this.aY) && (this.bc != null)) {}
    try
    {
      Message localMessage = new Message();
      localMessage.what = paramInt;
      Bundle localBundle = new Bundle();
      localBundle.putString("meid", paramString);
      localMessage.obj = localBundle;
      this.bc.send(localMessage);
      return;
    }
    catch (RemoteException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  protected void a(int paramInt, String paramString1, String paramString2)
  {
    if ((this.aY) && (this.bc != null)) {}
    try
    {
      Message localMessage = new Message();
      localMessage.what = paramInt;
      Bundle localBundle = new Bundle();
      localBundle.putString("meid", paramString1);
      localBundle.putString("a2ad_path", paramString2);
      localMessage.obj = localBundle;
      this.bc.send(localMessage);
      return;
    }
    catch (RemoteException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  protected void a(long paramLong)
  {
    if ((this.bi != null) && (this.bi.isShowing()) && (this.bo >= 0L)) {
      if (this.bo <= 0L) {
        break label188;
      }
    }
    label188:
    for (int i1 = (int)(100L * paramLong / this.bo * 1.0D);; i1 = 100)
    {
      float f1 = (float)((System.currentTimeMillis() - br) / 1000L);
      if (f1 > 0.0F)
      {
        paramLong = ((float)paramLong / f1);
        ((TextView)this.bi.findViewById(2131362211)).setText(com.lyy.filemanager.filedialog.a.a.a(paramLong) + "/s");
      }
      this.bj.setProgress(i1);
      ((TextView)this.bi.findViewById(2131362210)).setText(i1 + "%");
      if ((i1 == 100) && (!this.bp))
      {
        this.bi.dismiss();
        this.bi = null;
      }
      return;
    }
  }
  
  public void a(Bundle paramBundle)
  {
    String str = paramBundle.getString("fileName");
    long l1 = paramBundle.getLong("fileProgress");
    boolean bool = paramBundle.getBoolean("complete");
    paramBundle = paramBundle.getString("errString");
    if (this.bs > 0L)
    {
      if (this.aD == null) {
        this.aD = new com.lyy.filemanager.filedialog.e(this);
      }
      this.aD.a(str, this.bs, l1, bool, paramBundle);
    }
  }
  
  protected void a(com.lyy.filemanager.filedialog.aj paramAj)
  {
    if (this.aQ.size() <= 0)
    {
      Toast.makeText(this, 2131427573, 0).show();
      return;
    }
    if (P())
    {
      Toast.makeText(this, 2131427726, 0).show();
      return;
    }
    if (this.aQ.size() == 1)
    {
      if (!new File(paramAj.b()).exists())
      {
        Toast.makeText(this, paramAj.b() + " : " + getResources().getString(2131427487), 0).show();
        a(j());
        return;
      }
      if (this.aS != null)
      {
        if (d() == 2) {
          this.aZ.b(paramAj.b(), paramAj.a() + ".apk");
        }
        for (;;)
        {
          a(1, paramAj.d);
          b(paramAj);
          return;
          this.aZ.a(paramAj.b());
        }
      }
      if (this.aW)
      {
        if (d() == 2)
        {
          new com.lyy.apdatacable.j(this.A, paramAj.b(), com.lyy.filemanager.filedialog.a.a.a(this) + "/apps/.tmp/", paramAj.a() + ".apk", ((i)this.aQ.get(0)).d()).start();
          if ((this.aw != null) && (this.aw.isShowing())) {
            this.aw.dismiss();
          }
          this.aw = null;
          this.aw = com.lyy.filemanager.filedialog.a.a(this, getString(2131427578), "", false, true);
          return;
        }
        a(8, ((i)this.aQ.get(0)).d(), paramAj.b());
        return;
      }
      Toast.makeText(this, 2131427554, 0).show();
      return;
    }
    if (this.aW)
    {
      a(paramAj.b(), paramAj.a());
      return;
    }
    Toast.makeText(this, 2131427554, 0).show();
  }
  
  public final void a(String paramString)
  {
    if (this.aw == null)
    {
      this.aw = com.lyy.filemanager.filedialog.a.a(this, getString(2131427578), "", true);
      k();
      if ((this.t.w.size() <= 0) && (this.u.getVisibility() != 0))
      {
        this.u.setVisibility(0);
        this.w.setVisibility(8);
        this.v.setVisibility(8);
      }
      this.q.c = paramString;
      this.k.a(paramString);
      h().clear();
      this.k.notifyDataSetChanged();
      if (paramString != null) {
        break label165;
      }
      b(getString(2131427487));
      if ((this.aw == null) || (!this.aw.isShowing())) {}
    }
    for (;;)
    {
      label165:
      try
      {
        this.aw.dismiss();
        this.aw = null;
        return;
      }
      catch (Exception paramString) {}
      if (!this.aw.isShowing()) {
        break;
      }
      return;
      if (!new File(paramString).exists())
      {
        b(getString(2131427487) + " (" + paramString + ")");
        if ((this.aw == null) || (!this.aw.isShowing())) {
          continue;
        }
        try
        {
          this.aw.dismiss();
          this.aw = null;
          return;
        }
        catch (Exception paramString)
        {
          return;
        }
      }
      new aw(this).execute(new String[] { paramString });
      return;
    }
  }
  
  protected void a(String paramString, long paramLong)
  {
    if ((this.bi != null) && (this.bi.isShowing()))
    {
      br = System.currentTimeMillis();
      this.bo = paramLong;
      TextView localTextView1 = (TextView)this.bi.findViewById(2131362207);
      TextView localTextView2 = (TextView)this.bi.findViewById(2131362208);
      localTextView1.setText(getResources().getString(2131427575) + " " + "(" + com.lyy.filemanager.filedialog.a.a.a(this.bo) + ")");
      localTextView2.setText(paramString);
      this.bj.setProgress(0);
    }
  }
  
  void a(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, boolean paramBoolean)
  {
    this.ba = paramString1;
    if ((this.ba == null) || (paramInt == 0) || (paramString3 == null) || (paramString4 == null))
    {
      a(15, 0, getResources().getString(2131427554));
      return;
    }
    a(13, 1, getResources().getString(2131427560, new Object[] { paramString3 }));
    new aq(this, paramBoolean, getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f()).getString("directpush_useravatar", null)).execute(new String[] { this.ba, paramInt, paramString3, paramString4 });
  }
  
  public void a(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString3 == null)) {
      Toast.makeText(this, 2131427549, 0).show();
    }
    Object localObject;
    label182:
    for (;;)
    {
      return;
      if ((!getSharedPreferences("remmeid.xml", 0).getBoolean(paramString2, false)) && (!paramBoolean)) {
        break;
      }
      a(4, paramString2);
      localObject = this.aQ.iterator();
      if (!((Iterator)localObject).hasNext()) {}
      for (int i1 = 0;; i1 = 1)
      {
        if (i1 != 0) {
          break label182;
        }
        paramString2 = new i(paramString1, paramString2, paramString3, paramString4);
        this.aQ.add(paramString2);
        a(0, 2, paramString1 + " " + getResources().getString(2131427572));
        q();
        a(2131034113, true);
        return;
        if (!((i)((Iterator)localObject).next()).d().equals(paramString2)) {
          break;
        }
      }
    }
    try
    {
      if ((this.bu != null) && (this.bu.isShowing()))
      {
        this.bu.dismiss();
        this.bu = null;
      }
      localObject = Message.obtain();
      ((Message)localObject).what = 20001;
      Bundle localBundle = new Bundle();
      localBundle.putString("meid", paramString2);
      ((Message)localObject).obj = localBundle;
      this.A.sendMessageDelayed((Message)localObject, 45000L);
      localObject = new com.lyy.widgets.b(this, paramString2);
      ((com.lyy.widgets.b)localObject).b(2131427901);
      ((com.lyy.widgets.b)localObject).a(getString(2131427550, new Object[] { paramString1 }) + " ? (" + paramString3 + ")");
      ((com.lyy.widgets.b)localObject).a(true, 2131427614);
      ((com.lyy.widgets.b)localObject).a(new ad(this, paramString2));
      ((com.lyy.widgets.b)localObject).a(2131427551, new ae(this, paramString2, paramString1, paramString3, paramString4));
      ((com.lyy.widgets.b)localObject).b(2131427552, new af(this, paramString2));
      this.bu = ((com.lyy.widgets.b)localObject).a();
      this.bu.show();
      a(2131034114, true);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  protected void a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.aQ.size() <= 0) {}
    for (;;)
    {
      return;
      ((i)this.aQ.get(0)).a(0);
      q();
      String str;
      if (paramBoolean2)
      {
        str = "5001:" + this.aP + "\r\n";
        this.aS.writeString(str);
        if (!paramBoolean1) {
          Toast.makeText(this, getResources().getString(2131427579) + " (" + com.lyy.filemanager.filedialog.a.a.c(paramString) + ")", 0).show();
        }
        if ((d() == 0) && (paramBoolean2))
        {
          a(this.o);
          com.damiapp.a.b.b(this, this.o + "/" + com.lyy.filemanager.filedialog.a.a.c(paramString));
        }
        if (paramBoolean2) {
          a(2131034116, false);
        }
        if ((this.bi == null) || (!this.bi.isShowing())) {}
      }
      try
      {
        this.bi.dismiss();
        this.bi = null;
        if ((this.be == null) || (this.be.size() <= 0)) {
          continue;
        }
        a((com.lyy.filemanager.filedialog.aj)this.be.get(0));
        this.be.remove(0);
        return;
        str = "5000:" + this.aP + "\r\n";
        this.aS.writeString(str);
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
    }
  }
  
  protected void b()
  {
    Object localObject = com.damiapp.a.b.g(this);
    ((AlertDialog.Builder)localObject).setTitle(2131427889);
    ((AlertDialog.Builder)localObject).setMessage(getString(2131427888, new Object[] { this.o }));
    ((AlertDialog.Builder)localObject).setPositiveButton(2131427890, new k(this));
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
    ((AlertDialog)localObject).show();
  }
  
  protected void b(int paramInt)
  {
    if (this.aS != null)
    {
      this.aS.closeDataSocket(true);
      this.aS.closeSocket(true);
      if (this.aS.isAlive()) {}
    }
    try
    {
      this.aS.join();
      this.aS = null;
      if (this.aZ != null) {
        this.aZ.c();
      }
      switch (paramInt)
      {
      default: 
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
      a(9, 0, getResources().getString(2131427555));
      return;
    }
    a(10, 0, getResources().getString(2131427556) + " " + this.aN);
    return;
    a(11, 0, getResources().getString(2131427558));
    return;
    a(16, 0, getResources().getString(2131427574));
    return;
    a(15, 0, getResources().getString(2131427574));
  }
  
  protected void b(Bundle paramBundle)
  {
    for (;;)
    {
      Object localObject1;
      long l1;
      long l2;
      boolean bool;
      Object localObject2;
      String str;
      try
      {
        localObject1 = paramBundle.getString("push_filename");
        l1 = paramBundle.getLong("push_progress");
        l2 = paramBundle.getLong("push_maxLength");
        bool = paramBundle.getBoolean("push_complete");
        localObject2 = paramBundle.getString("push_error");
        str = paramBundle.getString("push_speed");
        if ((this.bi == null) || (!this.bi.isShowing())) {
          break label255;
        }
        if (l2 <= 0L) {
          break label743;
        }
        i1 = (int)(100L * l1 / l2 * 1.0D);
        this.bj.setProgress(i1);
        ((TextView)this.bi.findViewById(2131362211)).setText(str);
        ((TextView)this.bi.findViewById(2131362210)).setText(i1 + "%");
        if (i1 == 100)
        {
          this.bi.dismiss();
          this.bi = null;
          return;
        }
        if (localObject2 != null)
        {
          this.bi.dismiss();
          this.bi = null;
          Toast.makeText(this, getResources().getString(2131427764) + ": " + (String)localObject2, 0).show();
          return;
        }
      }
      catch (Exception paramBundle)
      {
        paramBundle.printStackTrace();
        return;
      }
      if (bool)
      {
        this.bi.dismiss();
        this.bi = null;
        return;
        label255:
        if ((localObject1 != null) && (this.ab))
        {
          this.bo = l2;
          this.bp = false;
          if (localObject2 != null)
          {
            Toast.makeText(this, getResources().getString(2131427764) + ": " + (String)localObject2, 0).show();
            return;
          }
          if ((!bool) && (l1 != l2))
          {
            localObject2 = new File((String)localObject1);
            this.bi = new Dialog(this, 2131493130);
            this.bi.requestWindowFeature(1);
            this.bi.setContentView(2130903117);
            this.bj = ((ProgressBar)this.bi.findViewById(2131362209));
            this.bj.setMax(100);
            TextView localTextView1 = (TextView)this.bi.findViewById(2131362207);
            TextView localTextView2 = (TextView)this.bi.findViewById(2131362208);
            ImageView localImageView = (ImageView)this.bi.findViewById(2131362206);
            i1 = ((File)localObject2).getName().lastIndexOf('.');
            if (i1 == -1)
            {
              paramBundle = null;
              i1 = a(paramBundle, (File)localObject2);
              paramBundle = null;
              if (i1 == 7) {
                paramBundle = f(((File)localObject2).getAbsolutePath());
              }
              localObject1 = paramBundle;
              if (paramBundle == null) {
                localObject1 = com.lyy.filemanager.filedialog.ag.b(i1);
              }
              localImageView.setImageDrawable((Drawable)localObject1);
              ((TextView)this.bi.findViewById(2131362211)).setText(str);
              if (l2 <= 0L) {
                break label736;
              }
            }
            label736:
            for (i1 = (int)(100L * l1 / l2 * 1.0D);; i1 = 100)
            {
              ((TextView)this.bi.findViewById(2131362210)).setText(i1 + "%");
              localTextView2.setText(((File)localObject2).getName());
              localTextView1.setText(getResources().getString(2131427575) + " " + "(" + com.lyy.filemanager.filedialog.a.a.a(this.bo) + ")");
              this.bk = ((TextView)this.bi.findViewById(2131362212));
              this.bl = ((TextView)this.bi.findViewById(2131362213));
              this.bk.setOnClickListener(this);
              this.bl.setOnClickListener(this);
              this.bi.show();
              return;
              paramBundle = ((File)localObject2).getName().substring(i1 + 1).toLowerCase(Locale.getDefault());
              break;
            }
          }
        }
      }
      return;
      label743:
      int i1 = 100;
    }
  }
  
  protected void c()
  {
    int i1 = 192;
    for (;;)
    {
      try
      {
        JSONObject localJSONObject = new JSONObject();
        int i2;
        try
        {
          localJSONObject.put("ssid", A2AServerService.h());
          localJSONObject.put("ip", A2AServerService.g());
          localJSONObject.put("port", 18988);
          SharedPreferences localSharedPreferences = getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f());
          localJSONObject.put("username", localSharedPreferences.getString("directpush_username", null));
          localJSONObject.put("avatar", localSharedPreferences.getString("directpush_useravatar", null));
          i2 = getResources().getDimensionPixelOffset(2131230808);
          if (i2 < 192)
          {
            new as(this, null).execute(new String[] { localJSONObject.toString(), i1 });
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        i1 = i2;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
    }
  }
  
  public void connectServerRsp(String paramString1, String paramString2, String paramString3)
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    Bundle localBundle = new Bundle();
    localBundle.putString("host_ip", paramString1);
    localBundle.putString("host_username", paramString2);
    localBundle.putString("host_password", paramString3);
    localMessage.obj = localBundle;
    this.A.sendMessage(localMessage);
  }
  
  public int d()
  {
    try
    {
      int i1 = this.p;
      return i1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public boolean e()
  {
    return this.ac;
  }
  
  public final boolean f()
  {
    return this.y;
  }
  
  public void forceCancelTransfer()
  {
    this.A.sendEmptyMessage(14);
  }
  
  public void forceDownload(String paramString, boolean paramBoolean)
  {
    Message localMessage = new Message();
    localMessage.what = 8;
    Bundle localBundle = new Bundle();
    localBundle.putString("down_path", paramString);
    localBundle.putBoolean("down_dir", paramBoolean);
    localMessage.obj = localBundle;
    this.A.sendMessage(localMessage);
  }
  
  public void forceDownload(String paramString, boolean paramBoolean, long paramLong)
  {
    Message localMessage = new Message();
    localMessage.what = 15;
    Bundle localBundle = new Bundle();
    localBundle.putString("down_path", paramString);
    localBundle.putBoolean("down_dir", paramBoolean);
    localBundle.putLong("down_size", paramLong);
    localMessage.obj = localBundle;
    this.A.sendMessage(localMessage);
  }
  
  public boolean g()
  {
    return this.z;
  }
  
  public ArrayList h()
  {
    return this.q.a;
  }
  
  public ArrayList i()
  {
    return this.q.b;
  }
  
  public String j()
  {
    return this.q.c;
  }
  
  public void k()
  {
    this.q.b.clear();
  }
  
  public int l()
  {
    return this.q.b.size();
  }
  
  public void m()
  {
    this.u.setVisibility(8);
    this.v.setVisibility(0);
    this.w.setVisibility(8);
  }
  
  /* Error */
  protected void n()
  {
    // Byte code:
    //   0: getstatic 889	com/damiapp/sdc/ui/sdc_main_activity:bq	Ljava/util/Timer;
    //   3: ifnull +20 -> 23
    //   6: getstatic 889	com/damiapp/sdc/ui/sdc_main_activity:bq	Ljava/util/Timer;
    //   9: invokevirtual 2314	java/util/Timer:cancel	()V
    //   12: getstatic 889	com/damiapp/sdc/ui/sdc_main_activity:bq	Ljava/util/Timer;
    //   15: invokevirtual 2317	java/util/Timer:purge	()I
    //   18: pop
    //   19: aconst_null
    //   20: putstatic 889	com/damiapp/sdc/ui/sdc_main_activity:bq	Ljava/util/Timer;
    //   23: aload_0
    //   24: invokespecial 2604	com/damiapp/sdc/ui/sdc_main_activity:O	()V
    //   27: aload_0
    //   28: getfield 258	com/damiapp/sdc/ui/sdc_main_activity:aQ	Ljava/util/List;
    //   31: invokeinterface 590 1 0
    //   36: aload_0
    //   37: getfield 454	com/damiapp/sdc/ui/sdc_main_activity:aZ	Lcom/lyy/apdatacable/k;
    //   40: ifnull +10 -> 50
    //   43: aload_0
    //   44: getfield 454	com/damiapp/sdc/ui/sdc_main_activity:aZ	Lcom/lyy/apdatacable/k;
    //   47: invokevirtual 2516	com/lyy/apdatacable/k:c	()V
    //   50: aload_0
    //   51: getfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   54: ifnull +53 -> 107
    //   57: aload_0
    //   58: getfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   61: ldc_w 2323
    //   64: invokevirtual 2326	com/lyy/ftpservice/SessionThread:writeString	(Ljava/lang/String;)V
    //   67: aload_0
    //   68: getfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   71: iconst_1
    //   72: invokevirtual 2505	com/lyy/ftpservice/SessionThread:closeDataSocket	(Z)V
    //   75: aload_0
    //   76: getfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   79: iconst_1
    //   80: invokevirtual 2508	com/lyy/ftpservice/SessionThread:closeSocket	(Z)V
    //   83: aload_0
    //   84: getfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   87: invokevirtual 2511	com/lyy/ftpservice/SessionThread:isAlive	()Z
    //   90: istore_1
    //   91: iload_1
    //   92: ifne +10 -> 102
    //   95: aload_0
    //   96: getfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   99: invokevirtual 2514	com/lyy/ftpservice/SessionThread:join	()V
    //   102: aload_0
    //   103: aconst_null
    //   104: putfield 262	com/damiapp/sdc/ui/sdc_main_activity:aS	Lcom/lyy/ftpservice/SessionThread;
    //   107: aload_0
    //   108: ldc_w 793
    //   111: invokevirtual 797	com/damiapp/sdc/ui/sdc_main_activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   114: checkcast 799	android/net/wifi/WifiManager
    //   117: astore_2
    //   118: invokestatic 452	com/lyy/apdatacable/A2AServerService:b	()Z
    //   121: ifeq +9 -> 130
    //   124: aload_2
    //   125: iconst_0
    //   126: invokestatic 1556	com/lyy/apdatacable/bh:a	(Landroid/net/wifi/WifiManager;Z)Z
    //   129: pop
    //   130: aconst_null
    //   131: invokestatic 843	com/lyy/apdatacable/A2AServerService:c	(Ljava/lang/String;)V
    //   134: aload_0
    //   135: invokevirtual 823	com/damiapp/sdc/ui/sdc_main_activity:getApplicationContext	()Landroid/content/Context;
    //   138: astore_3
    //   139: aload_3
    //   140: new 832	android/content/Intent
    //   143: dup
    //   144: aload_3
    //   145: ldc_w 449
    //   148: invokespecial 835	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   151: invokevirtual 841	android/content/Context:stopService	(Landroid/content/Intent;)Z
    //   154: pop
    //   155: aload_0
    //   156: iconst_0
    //   157: putfield 268	com/damiapp/sdc/ui/sdc_main_activity:aV	Z
    //   160: aload_0
    //   161: iconst_0
    //   162: putfield 270	com/damiapp/sdc/ui/sdc_main_activity:aW	Z
    //   165: aload_0
    //   166: iconst_0
    //   167: putfield 272	com/damiapp/sdc/ui/sdc_main_activity:aX	Z
    //   170: aload_0
    //   171: getfield 264	com/damiapp/sdc/ui/sdc_main_activity:aT	Z
    //   174: ifeq +38 -> 212
    //   177: aload_0
    //   178: ldc_w 1558
    //   181: invokevirtual 797	com/damiapp/sdc/ui/sdc_main_activity:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   184: checkcast 1560	android/telephony/TelephonyManager
    //   187: astore_3
    //   188: aload_3
    //   189: invokevirtual 1563	android/telephony/TelephonyManager:getDataState	()I
    //   192: iconst_2
    //   193: if_icmpeq +19 -> 212
    //   196: aload_3
    //   197: invokevirtual 1563	android/telephony/TelephonyManager:getDataState	()I
    //   200: iconst_1
    //   201: if_icmpeq +11 -> 212
    //   204: aload_0
    //   205: iconst_1
    //   206: getstatic 747	android/os/Build$VERSION:SDK_INT	I
    //   209: invokestatic 1566	com/lyy/apdatacable/bh:a	(Landroid/content/Context;II)V
    //   212: aload_0
    //   213: getfield 266	com/damiapp/sdc/ui/sdc_main_activity:aU	Z
    //   216: ifeq +25 -> 241
    //   219: aload_2
    //   220: invokevirtual 1569	android/net/wifi/WifiManager:getWifiState	()I
    //   223: iconst_3
    //   224: if_icmpeq +17 -> 241
    //   227: aload_2
    //   228: invokevirtual 1569	android/net/wifi/WifiManager:getWifiState	()I
    //   231: iconst_2
    //   232: if_icmpeq +9 -> 241
    //   235: aload_2
    //   236: iconst_1
    //   237: invokevirtual 1573	android/net/wifi/WifiManager:setWifiEnabled	(Z)Z
    //   240: pop
    //   241: aload_0
    //   242: getfield 762	com/damiapp/sdc/ui/sdc_main_activity:bf	Landroid/widget/FrameLayout;
    //   245: bipush 8
    //   247: invokevirtual 1262	android/widget/FrameLayout:setVisibility	(I)V
    //   250: return
    //   251: astore_2
    //   252: aload_2
    //   253: invokevirtual 2517	java/lang/InterruptedException:printStackTrace	()V
    //   256: goto -154 -> 102
    //   259: astore_2
    //   260: aload_2
    //   261: invokevirtual 1085	java/lang/Exception:printStackTrace	()V
    //   264: return
    //   265: astore_2
    //   266: goto -239 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	269	0	this	sdc_main_activity
    //   90	2	1	bool	boolean
    //   117	119	2	localWifiManager	WifiManager
    //   251	2	2	localInterruptedException	InterruptedException
    //   259	2	2	localException1	Exception
    //   265	1	2	localException2	Exception
    //   138	59	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   95	102	251	java/lang/InterruptedException
    //   0	23	259	java/lang/Exception
    //   27	50	259	java/lang/Exception
    //   50	91	259	java/lang/Exception
    //   95	102	259	java/lang/Exception
    //   102	107	259	java/lang/Exception
    //   107	130	259	java/lang/Exception
    //   130	212	259	java/lang/Exception
    //   212	241	259	java/lang/Exception
    //   241	250	259	java/lang/Exception
    //   252	256	259	java/lang/Exception
    //   23	27	265	java/lang/Exception
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {
      if ((paramInt1 == 4) && (this.R != 0))
      {
        com.damiapp.sdc.a.a.a(this.L, com.damiapp.sdc.a.b.b);
        this.R = 0;
      }
    }
    Object localObject2;
    Object localObject3;
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
            } while (paramIntent == null);
            switch (paramInt1)
            {
            case 3: 
            default: 
              return;
            case 2: 
              paramInt1 = paramIntent.getIntExtra("profile.updated", 0);
              paramIntent = this.a.a(com.damiapp.a.b.c(this));
            }
          } while (paramIntent == null);
          if (paramInt1 == 100)
          {
            localObject2 = (CircularImageView)findViewById(2131362216);
            localObject3 = (RippleView)findViewById(2131362214);
            localObject1 = com.lyy.anyness.j.a(paramIntent.e(), com.lyy.softdatacable.a.c());
            if (TextUtils.isEmpty((CharSequence)localObject1))
            {
              ((CircularImageView)localObject2).setVisibility(8);
              ((RippleView)localObject3).setVisibility(0);
              ((TextView)findViewById(2131362215)).setText(Character.toUpperCase(paramIntent.g().charAt(0)));
              return;
            }
            ((CircularImageView)localObject2).setVisibility(0);
            ((RippleView)localObject3).setVisibility(8);
            com.b.a.b.f.a().a((String)localObject1, (ImageView)localObject2, this.bh);
            paramIntent = (CircularImageView)findViewById(2131362228);
            com.b.a.b.f.a().a((String)localObject1, paramIntent, this.bh);
            return;
          }
        } while (paramInt1 != 200);
        ((TextView)findViewById(2131362217)).setText(paramIntent.g());
        localObject1 = (TextView)findViewById(2131362218);
        if (TextUtils.isEmpty(paramIntent.h()))
        {
          ((TextView)localObject1).setVisibility(8);
          return;
        }
        ((TextView)localObject1).setVisibility(0);
        ((TextView)localObject1).setText(paramIntent.h());
        return;
      } while (paramIntent.getIntExtra("action_nearby", 0) != 4);
      if (this.R != 1)
      {
        com.damiapp.sdc.a.a.a(this.L, com.damiapp.sdc.a.b.a);
        this.R = 1;
      }
      localObject1 = paramIntent.getStringExtra("conn_hostip");
      paramInt1 = paramIntent.getIntExtra("conn_hostport", 0);
      localObject2 = paramIntent.getStringExtra("conn_localName");
      a(13, 1, getResources().getString(2131427560, new Object[] { paramIntent.getStringExtra("conn_remoteName") }));
      K();
    } while ((!this.aW) || (localObject1 == null) || (paramInt1 != 28003) || (localObject2 == null));
    new av(this, null).execute(new String[] { localObject1, localObject2 });
    return;
    this.aO = paramIntent.getStringExtra("conn_remoteAvatar");
    a(paramIntent.getStringExtra("conn_hostip"), paramIntent.getStringExtra("conn_ssid"), paramIntent.getIntExtra("conn_hostport", 0), paramIntent.getStringExtra("conn_remoteName"), paramIntent.getStringExtra("conn_localName"), paramIntent.getBooleanExtra("conn_autoAccept", false));
    return;
    if (this.R != 1)
    {
      com.damiapp.sdc.a.a.a(this.L, com.damiapp.sdc.a.b.a);
      this.R = 1;
    }
    a(13, 1, getResources().getString(2131427877));
    Object localObject1 = (WifiManager)getSystemService("wifi");
    if (!((WifiManager)localObject1).isWifiEnabled()) {}
    label845:
    label863:
    label910:
    label928:
    label988:
    for (;;)
    {
      try
      {
        ((WifiManager)localObject1).setWifiEnabled(true);
        localObject2 = ((WifiManager)localObject1).getConfiguredNetworks();
        if (localObject2 == null) {
          break label845;
        }
        localObject2 = ((List)localObject2).iterator();
        if (!((Iterator)localObject2).hasNext())
        {
          paramInt2 = 0;
          paramInt1 = -1;
          if (paramInt2 != 0) {
            break label988;
          }
          localObject2 = new WifiConfiguration();
          ((WifiConfiguration)localObject2).SSID = ("\"" + paramIntent.getStringExtra("conn_ssid") + "\"");
          ((WifiConfiguration)localObject2).allowedAuthAlgorithms.set(0);
          ((WifiConfiguration)localObject2).allowedProtocols.set(1);
          ((WifiConfiguration)localObject2).allowedProtocols.set(0);
          ((WifiConfiguration)localObject2).allowedKeyManagement.set(0);
          ((WifiManager)localObject1).addNetwork((WifiConfiguration)localObject2);
          localObject2 = ((WifiManager)localObject1).getConfiguredNetworks();
          if (localObject2 == null) {
            break label910;
          }
          localObject2 = ((List)localObject2).iterator();
          if (((Iterator)localObject2).hasNext()) {
            break label863;
          }
          if (paramInt1 != -1) {
            break label928;
          }
          a(15, 0, getResources().getString(2131427554));
          return;
        }
      }
      catch (Exception paramIntent)
      {
        a(15, 0, getResources().getString(2131427554));
        return;
      }
      localObject3 = (WifiConfiguration)((Iterator)localObject2).next();
      if ((((WifiConfiguration)localObject3).SSID != null) && (((WifiConfiguration)localObject3).SSID.contains(paramIntent.getStringExtra("conn_ssid"))))
      {
        paramInt1 = ((WifiConfiguration)localObject3).networkId;
        paramInt2 = 1;
        continue;
        a(15, 0, getResources().getString(2131427554));
        return;
        localObject3 = (WifiConfiguration)((Iterator)localObject2).next();
        if ((((WifiConfiguration)localObject3).SSID != null) && (((WifiConfiguration)localObject3).SSID.contains(paramIntent.getStringExtra("conn_ssid"))))
        {
          paramInt1 = ((WifiConfiguration)localObject3).networkId;
          continue;
          a(15, 0, getResources().getString(2131427554));
          return;
          ((WifiManager)localObject1).disconnect();
          ((WifiManager)localObject1).enableNetwork(paramInt1, true);
          ((WifiManager)localObject1).reconnect();
          this.bt = paramIntent.getExtras();
          a(13, 1, getResources().getString(2131427560, new Object[] { paramIntent.getStringExtra("conn_remoteName") }));
          return;
        }
      }
    }
  }
  
  public void onClick(View paramView)
  {
    int i1 = 0;
    if ((paramView == this.E) || (paramView == (RippleView)findViewById(2131362214)) || (paramView == (CircularImageView)findViewById(2131362216)) || (paramView == (CircularImageView)findViewById(2131362228)))
    {
      paramView = new Intent();
      paramView.setClass(this, A2AanynessProfileView.class);
      startActivityForResult(paramView, 2);
    }
    do
    {
      do
      {
        return;
        if ((paramView == this.F) || (paramView == this.P))
        {
          paramView = new Intent();
          paramView.setClass(this, Softdatacable.class);
          startActivity(paramView);
          return;
        }
        if (paramView == this.G)
        {
          paramView = new Intent();
          paramView.setClass(this, AutoSyncView.class);
          startActivity(paramView);
          return;
        }
        if ((paramView == this.H) || (paramView == this.Q))
        {
          paramView = new Intent();
          paramView.setClass(this, A2AnearbyView.class);
          startActivityForResult(paramView, 5);
          return;
        }
        if (paramView == this.I)
        {
          paramView = new Intent();
          paramView.setClass(this, SettingsPreference.class);
          startActivity(paramView);
          return;
        }
        if (paramView == this.J)
        {
          paramView = new Intent();
          paramView.setClass(this, HelpActivity.class);
          startActivity(paramView);
          return;
        }
        if (paramView == this.K)
        {
          paramView = new Intent();
          paramView.setClass(this, A2AinviteFriend.class);
          startActivity(paramView);
          return;
        }
        if (paramView == this.aJ)
        {
          b(0, true);
          return;
        }
        if (paramView == this.aK)
        {
          b(1, true);
          return;
        }
        if (paramView == this.aL)
        {
          b(2, true);
          return;
        }
        if (paramView != this.bk) {
          break;
        }
      } while ((this.bi == null) || (!this.bi.isShowing()));
      try
      {
        this.bi.dismiss();
        this.ab = false;
        return;
      }
      catch (Exception paramView)
      {
        return;
      }
      if (paramView != this.bl) {
        break;
      }
    } while ((this.bi == null) || (!this.bi.isShowing()));
    for (;;)
    {
      try
      {
        this.bi.dismiss();
        this.bi = null;
        this.ab = false;
        if (this.aW)
        {
          if (i1 >= this.aQ.size()) {
            break;
          }
          if (((i)this.aQ.get(i1)).b() != 1) {
            break label618;
          }
          a(13, ((i)this.aQ.get(i1)).d(), "");
          break label618;
        }
        if (this.aZ == null) {
          break;
        }
        this.aZ.b();
        return;
      }
      catch (Exception paramView)
      {
        return;
      }
      if (paramView == this.M)
      {
        if (this.R == 1) {
          break;
        }
        com.damiapp.sdc.a.a.a(this.L, com.damiapp.sdc.a.b.a);
        this.R = 1;
        M();
        return;
      }
      if (paramView == this.N)
      {
        if (this.R == 1) {
          break;
        }
        com.damiapp.sdc.a.a.a(this.L, com.damiapp.sdc.a.b.a);
        this.R = 1;
        L();
        return;
      }
      if (paramView == this.O)
      {
        paramView = new Intent();
        paramView.setClass(this, CaptureActivity.class);
        paramView.putExtra("capture_method", 2);
        startActivityForResult(paramView, 6);
        return;
      }
      if (paramView != this.bd) {
        break;
      }
      I();
      return;
      label618:
      i1 += 1;
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (this.g.getVisibility() == 0) {
      this.g.getViewTreeObserver().addOnGlobalLayoutListener(new j(this));
    }
    super.onConfigurationChanged(paramConfiguration);
    this.W.onConfigurationChanged(paramConfiguration);
  }
  
  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903119);
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().setStatusBarColor(getResources().getColor(2131165239));
    }
    com.damiapp.a.b.f(this);
    p();
    com.damiapp.c.a.a().c().a("", -1, s());
    this.r = getString(2131427453);
    this.s = getString(2131427415);
    this.ax = getSharedPreferences("DMfileManager", 0);
    this.m = new com.lyy.filemanager.filedialog.a.b(Runtime.getRuntime());
    paramBundle = getIntent().getAction();
    Object localObject;
    if (("android.intent.action.SEND".equals(paramBundle)) || ("android.intent.action.SEND_MULTIPLE".equals(paramBundle)))
    {
      this.q = new com.lyy.filemanager.filedialog.f(new ArrayList(), null, "/");
      H();
      B();
      y();
      z();
      A();
      x();
      v();
      w();
      localObject = new IntentFilter();
      ((IntentFilter)localObject).addAction("android.net.conn.TETHER_STATE_CHANGED");
      ((IntentFilter)localObject).addAction("android.net.conn.CONNECTIVITY_CHANGE");
      ((IntentFilter)localObject).addAction("nearby_broadcast_autoconn");
      registerReceiver(this.B, (IntentFilter)localObject);
      this.aP = com.damiapp.a.b.c(this);
      if ((this.aP == null) || ((this.aP != null) && (this.aP.length() <= 0))) {
        this.aP = com.damiapp.a.b.a(11, 1);
      }
      this.aP = this.aP.replaceAll(":", "-");
      if ((!"android.intent.action.SEND".equals(paramBundle)) && (!"android.intent.action.SEND_MULTIPLE".equals(paramBundle))) {
        break label767;
      }
      if (!"android.intent.action.SEND".equals(paramBundle)) {
        break label587;
      }
      paramBundle = a((Uri)getIntent().getParcelableExtra("android.intent.extra.STREAM"));
      if (paramBundle == null) {
        break label562;
      }
      b(1, false);
      this.bv = h();
      this.bv.clear();
      localObject = new com.lyy.filemanager.filedialog.aj(getString(2131427751), "/", 10, null, true);
      ((com.lyy.filemanager.filedialog.aj)localObject).a(true);
      this.bv.add(localObject);
      paramBundle = new com.lyy.filemanager.filedialog.aj(paramBundle);
      paramBundle.a(true);
      this.bv.add(paramBundle);
      this.k.notifyDataSetChanged();
      this.A.sendEmptyMessageDelayed(10019, 500L);
    }
    for (;;)
    {
      if (getIntent().getBooleanExtra("com.lyy.filemanager.nearby", false))
      {
        a(getIntent().getStringExtra("main_nearby_user"), getIntent().getStringExtra("main_nearby_devInfo"), getIntent().getStringExtra("main_nearby_fromIp"), getIntent().getIntExtra("main_nearby_fromPort", 28004));
        a(2131034114, true);
      }
      u();
      getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f()).registerOnSharedPreferenceChangeListener(this.bD);
      return;
      this.q = new com.lyy.filemanager.filedialog.f(new ArrayList(), null, this.o);
      break;
      label562:
      b(getResources().getString(2131427905));
      a(j());
      continue;
      label587:
      paramBundle = a(getIntent().getParcelableArrayListExtra("android.intent.extra.STREAM"));
      if ((paramBundle != null) && (paramBundle.size() > 0))
      {
        b(1, false);
        this.bv = h();
        this.bv.clear();
        localObject = new com.lyy.filemanager.filedialog.aj(getString(2131427751), "/", 10, null, true);
        ((com.lyy.filemanager.filedialog.aj)localObject).a(true);
        this.bv.add(localObject);
        paramBundle = paramBundle.iterator();
        for (;;)
        {
          if (!paramBundle.hasNext())
          {
            this.k.notifyDataSetChanged();
            this.A.sendEmptyMessageDelayed(10019, 500L);
            break;
          }
          localObject = new com.lyy.filemanager.filedialog.aj((String)paramBundle.next());
          ((com.lyy.filemanager.filedialog.aj)localObject).a(true);
          this.bv.add(localObject);
        }
      }
      b(getResources().getString(2131427905));
      a(j());
      continue;
      label767:
      a(j());
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    getSharedPreferences(com.lyy.softdatacable.a.e(), com.lyy.softdatacable.a.f()).unregisterOnSharedPreferenceChangeListener(this.bD);
    if (this.T != null)
    {
      this.T.removeAllViews();
      this.T.destroy();
    }
    if (this.a != null)
    {
      this.a.b();
      this.a = null;
    }
    try
    {
      File localFile = new File(com.lyy.filemanager.filedialog.a.a.a(this) + "/apps/.tmp/");
      String[] arrayOfString;
      int i1;
      if ((localFile.exists()) && (localFile.isDirectory()))
      {
        arrayOfString = localFile.list();
        i1 = 0;
      }
      for (;;)
      {
        if (i1 >= arrayOfString.length)
        {
          if (this.ay != null)
          {
            this.ay.release();
            this.ay = null;
          }
          unregisterReceiver(this.B);
          this.t.g();
          n();
          return;
        }
        new File(localFile, arrayOfString[i1]).delete();
        i1 += 1;
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyDown(paramInt, paramKeyEvent);
    case 4: 
      LinearLayout localLinearLayout;
      TextView localTextView;
      if (this.w.getVisibility() == 0)
      {
        localLinearLayout = (LinearLayout)findViewById(2131362158);
        localTextView = (TextView)findViewById(2131362159);
        localLinearLayout.setVisibility(8);
        localTextView.setText("");
        k();
        this.u.setVisibility(0);
        this.w.setVisibility(8);
        this.v.setVisibility(8);
      }
      while ((A2AServerService.b()) || (this.aZ != null))
      {
        return true;
        if (this.v.getVisibility() == 0)
        {
          localLinearLayout = (LinearLayout)findViewById(2131362158);
          localTextView = (TextView)findViewById(2131362159);
          localLinearLayout.setVisibility(8);
          localTextView.setText("");
          k();
          this.u.setVisibility(0);
          this.w.setVisibility(8);
          this.v.setVisibility(8);
        }
        else
        {
          D();
        }
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    if (this.bx) {
      Toast.makeText(this, getString(2131427474), 0).show();
    }
    for (;;)
    {
      return true;
      paramKeyEvent = new ap(this);
      paramKeyEvent.a(this.D);
      paramKeyEvent.show();
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (this.W.onOptionsItemSelected(paramMenuItem)) {
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.T != null) {
      this.T.pause();
    }
  }
  
  protected void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    this.W.syncState();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      this.q.c = paramBundle.getString("FMcurrentPath");
      if (this.q.c == null) {
        this.q.c = this.o;
      }
      a(j());
    }
    super.onRestoreInstanceState(paramBundle);
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.T != null) {
      this.T.resume();
    }
    if (this.S)
    {
      b();
      this.S = false;
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putString("FMcurrentPath", j());
    super.onSaveInstanceState(paramBundle);
  }
  
  public void serverDisconnected()
  {
    this.A.sendEmptyMessage(3);
  }
}
