package com.v1.v1golf2.library;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.support.v4.content.CursorLoader;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.a.a;
import com.flurry.android.FlurryAgent;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AnalyzeActivity
  extends BaseActivity_Plain
  implements SensorEventListener, View.OnClickListener, View.OnLongClickListener, SeekBar.OnSeekBarChangeListener
{
  public static int A;
  public static Float B;
  public static Float C;
  public static int F;
  public static int G;
  public static jq H;
  public static TableRow I;
  public static TableRow J;
  public static TableRow K;
  public static ImageButton L;
  public static ImageButton M;
  public static ImageButton N;
  public static ImageButton O;
  public static ImageButton P;
  public static ImageButton Q;
  public static ImageButton R;
  public static LinearLayout S;
  public static ImageButton T;
  static View U;
  static Boolean V;
  static Boolean W;
  static boolean X;
  static Float Z;
  static Semaphore a = new Semaphore(1);
  private static boolean aA;
  private static View aB;
  private static View aC;
  private static View aD;
  private static View aE;
  private static View aF;
  private static View aG;
  private static SeekBar aI;
  private static double[] aJ;
  private static double aK;
  static Float ai;
  static Boolean p = Boolean.valueOf(false);
  public static bu s;
  public static ni[] t;
  public static av u = av.a;
  public static int v = 0;
  public static int w = 0;
  public static int x = 0;
  public static int y = 0;
  public static int z = 0;
  bf D;
  bi E;
  aw Y;
  private TextView aH;
  private ImageButton aL;
  private String aM;
  private String aN;
  private String aO;
  private String aP;
  private String aQ;
  private String aR;
  private String aS;
  private int aT;
  private int aU;
  private int aV = 0;
  private int aW = 0;
  private float aX;
  private float aY;
  private String aZ;
  bj aa;
  bk ab;
  GestureDetector ac;
  public int ad = 4;
  final ArrayBlockingQueue ae = new ArrayBlockingQueue(5);
  NumberFormat af = new DecimalFormat("00000");
  Boolean ag = Boolean.valueOf(false);
  OrientationEventListener ah;
  Boolean aj = Boolean.valueOf(false);
  Boolean ak = Boolean.valueOf(false);
  boolean al;
  private com.facebook.a.f am;
  private a an;
  private V1GolfLib ao;
  private long ap;
  private int aq = 0;
  private ProgressDialog ar;
  private int as;
  private int at;
  private int au = 300000;
  private String av;
  private String aw;
  private br ax;
  private boolean ay = false;
  private boolean az = false;
  SensorManager b = null;
  private int bA = -1;
  private int bB = -1;
  private boolean bC = false;
  private int bD = -1;
  private final Handler bE = new bd(this);
  private Boolean ba;
  private String bb = "";
  private int bc;
  private Display bd;
  private int be = 0;
  private Boolean bf = Boolean.valueOf(false);
  private MenuItem bg;
  private MenuItem bh;
  private MenuItem bi;
  private MenuItem bj;
  private MenuItem bk;
  private MenuItem bl;
  private oz bm;
  private boolean bn = true;
  private Float bo;
  private boolean bp = false;
  private boolean bq = true;
  private boolean br = false;
  private boolean bs = false;
  private View bt;
  private boolean bu = false;
  private boolean bv = false;
  private boolean bw = false;
  private int bx;
  private ThreadPoolExecutor by;
  private int bz = -1;
  SharedPreferences c = null;
  SharedPreferences.Editor d;
  ProgressDialog e;
  Bitmap f;
  int g = 0;
  int h = 0;
  int i = 0;
  int j = 100;
  int k = 0;
  Menu l;
  MenuItem m;
  Boolean n;
  Boolean o = Boolean.valueOf(false);
  int q = 0;
  Intent r = null;
  
  static
  {
    A = 0;
    aA = false;
    F = 0;
    G = 3;
    H = jq.e;
    X = false;
    try
    {
      System.loadLibrary("jnigraphics");
      System.loadLibrary("V1Analysis");
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {}catch (ExceptionInInitializerError localExceptionInInitializerError) {}
  }
  
  public AnalyzeActivity() {}
  
  private void A()
  {
    if (this.b != null)
    {
      this.b.unregisterListener(this);
      this.b = null;
    }
    if (t != null)
    {
      if (t[1] != null)
      {
        t[1].b();
        t[1] = null;
      }
      if (t[0] != null)
      {
        t[0].b();
        t[0] = null;
      }
      t = null;
    }
    if (this.f != null) {
      this.f = null;
    }
    try
    {
      if (s != null)
      {
        s.removeAllViews();
        c();
        s = null;
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      try
      {
        for (;;)
        {
          a(findViewById(pj.aS));
          try
          {
            if (this.bm != null) {
              this.bm.b();
            }
            Object localObject;
            if (this.by != null)
            {
              localObject = this.by;
              this.by = new ThreadPoolExecutor(this.ad, this.ad, 10L, TimeUnit.SECONDS, this.ae);
              ((ThreadPoolExecutor)localObject).shutdownNow();
            }
            System.gc();
            finish();
            Log.d("V1", "is open=" + Dashboard.j());
            Log.d("V1", "getFromDash=" + p());
            Log.d("V1", "getFromSonyApp=" + o());
            Log.d("V1", "compareFlag=" + this.aj);
            if ((!Dashboard.j()) && (p().booleanValue()) && ((!o().booleanValue()) || (this.aj.booleanValue())))
            {
              localObject = new Intent(this, Dashboard.class);
              ((Intent)localObject).addFlags(603979776);
              startActivity((Intent)localObject);
              localObject = new Intent("kill");
              ((Intent)localObject).setType("text/plain");
              sendBroadcast((Intent)localObject);
              finish();
            }
            if (this.ak.booleanValue())
            {
              localObject = new Intent(this, CaptureVideo3.class);
              ((Intent)localObject).addFlags(603979776);
              ((Intent)localObject).putExtra("fromSonyCamApp", true);
              startActivity((Intent)localObject);
            }
            return;
            localNullPointerException = localNullPointerException;
            localNullPointerException.printStackTrace();
          }
          catch (Exception localException1)
          {
            for (;;) {}
          }
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  private void B()
  {
    if (X)
    {
      if (u != av.a)
      {
        t[1].b();
        a(av.a);
        return;
      }
      localObject = new Intent(this, RecordLessonActivity_AddVideo.class);
      ((Intent)localObject).putExtra("StatusID", "3");
      ((Intent)localObject).putExtra("StatusIDName", "My Lessons & Drills");
      startActivityForResult((Intent)localObject, 3);
      overridePendingTransition(pd.b, pd.i);
      return;
    }
    if ((this.n.booleanValue()) || (!this.aP.equals("")))
    {
      if (!this.aP.equals(""))
      {
        if (this.h == 0)
        {
          a(av.c);
          if ((Build.VERSION.SDK_INT < 11) && (this.bg != null)) {
            this.bg.setTitle(getString(pm.cc));
          }
          this.h = 1;
          return;
        }
        a(av.b);
        if ((Build.VERSION.SDK_INT < 11) && (this.bg != null)) {
          this.bg.setTitle(getString(pm.eC));
        }
        this.h = 0;
        return;
      }
      a();
      this.c = PreferenceManager.getDefaultSharedPreferences(this);
      this.d = this.c.edit();
      this.d.putString("Compare_FirstSwing", this.aO);
      this.d.putInt("Compare_Orientation", x);
      this.d.commit();
      A();
      Toast.makeText(getApplicationContext(), getString(pm.cd), 1).show();
      return;
    }
    Object localObject = new HashMap();
    ((HashMap)localObject).put("Feature", "Compare");
    FlurryAgent.onEvent("Upgrade Dialog", (Map)localObject);
    localObject = new Intent(this, Compare_PopUp.class);
    this.aj = Boolean.valueOf(true);
    startActivityForResult((Intent)localObject, 1);
  }
  
  private void C()
  {
    if ((this.c.getString("LoggedInUser", "0").equals("0")) && (this.c.getString("LoggedInUser_ISA", "0").equals("0"))) {
      Toast.makeText(getApplicationContext(), getString(pm.ie), 1).show();
    }
    for (;;)
    {
      return;
      if (v >= 2) {
        break;
      }
      if ((t != null) && (t[v] != null) && (t[v].j != null))
      {
        if ((!new File(this.aO).exists()) && (this.aU != 9))
        {
          AlertDialog localAlertDialog = new AlertDialog.Builder(this).setTitle(getString(pm.gY)).setMessage(pm.gX).setCancelable(false).setPositiveButton(pm.iN, new v(this)).setNegativeButton(pm.ej, new w(this)).create();
          try
          {
            localAlertDialog.show();
            return;
          }
          catch (Exception localException)
          {
            return;
          }
        }
        String[] arrayOfString = t[v].j.split("/");
        Object localObject2 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_swing_path/" + arrayOfString[(arrayOfString.length - 1)].replace("mp4", "3gp")), null, null, null, null).loadInBackground();
        Object localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((Cursor)localObject2).getCount() == 0) {
            localObject1 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_swing_path/" + arrayOfString[(arrayOfString.length - 1)]), null, null, null, null).loadInBackground();
          }
        }
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (((Cursor)localObject1).getCount() == 0) {
            localObject2 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_swing_path/" + arrayOfString[(arrayOfString.length - 1)].replace("mp4", "avi")), null, null, null, null).loadInBackground();
          }
        }
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((Cursor)localObject2).getCount() == 0) {
            localObject1 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_swing_path/" + arrayOfString[(arrayOfString.length - 1)].replace("mp4", "mpeg")), null, null, null, null).loadInBackground();
          }
        }
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (((Cursor)localObject1).getCount() == 0) {
            localObject2 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_swing_path/" + arrayOfString[(arrayOfString.length - 1)].replace("mp4", "mov")), null, null, null, null).loadInBackground();
          }
        }
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          localObject1 = localObject2;
          if (((Cursor)localObject2).getCount() == 0) {
            localObject1 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_swing_path/" + arrayOfString[(arrayOfString.length - 1)].replace("mp4", "wmv")), null, null, null, null).loadInBackground();
          }
        }
        if ((localObject1 != null) && (((Cursor)localObject1).getCount() > 0))
        {
          if ((((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("SwingType")) != 7) && (((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("SwingType")) != 2) && (((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndexOrThrow("SwingType")) != 9)) {
            break label891;
          }
          if (!((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndexOrThrow("StatusID")).equals("0")) {
            break label870;
          }
          localObject2 = new Intent(this, Upload.class);
          ((Intent)localObject2).putExtra("SwingID", ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndexOrThrow("SwingID")));
          ((Intent)localObject2).putExtra("AcademyID", ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndexOrThrow("AcademyID")));
          ((Intent)localObject2).putExtra("FromAnalyzer", "true");
          if (getPackageName().equals("com.v1.v1pro")) {
            ((Intent)localObject2).putExtra("moveCommand", true);
          }
          startActivity((Intent)localObject2);
        }
        while (localObject1 != null)
        {
          ((Cursor)localObject1).close();
          return;
          label870:
          Toast.makeText(getApplicationContext(), getString(pm.if), 1).show();
          continue;
          label891:
          Toast.makeText(getApplicationContext(), getString(pm.ig), 1).show();
        }
      }
    }
    Toast.makeText(getApplicationContext(), getString(pm.ih), 1).show();
  }
  
  private void D()
  {
    a();
    boolean bool = this.c.getBoolean("wifionly", false);
    Object localObject = (WifiManager)getSystemService("wifi");
    if ((bool) && (!((WifiManager)localObject).isWifiEnabled()))
    {
      localObject = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject).setTitle(getString(pm.iM));
      ((AlertDialog.Builder)localObject).setMessage(getString(pm.iJ));
      ((AlertDialog.Builder)localObject).setCancelable(false);
      ((AlertDialog.Builder)localObject).setPositiveButton(pm.iK, new x(this));
      ((AlertDialog.Builder)localObject).setNegativeButton(pm.ey, new z(this));
      localObject = ((AlertDialog.Builder)localObject).create();
    }
    try
    {
      if (!isFinishing()) {
        ((AlertDialog)localObject).show();
      }
      return;
    }
    catch (Exception localException) {}
    FlurryAgent.onEvent("Sending Video");
    if (U != null) {
      U.setVisibility(4);
    }
    L.setVisibility(4);
    aI.setVisibility(4);
    aD.setVisibility(4);
    aB.setVisibility(4);
    aC.setVisibility(4);
    aF.setVisibility(4);
    aE.setVisibility(0);
    e(0);
    return;
  }
  
  private void E()
  {
    a();
    this.bt.setVisibility(0);
  }
  
  private void F()
  {
    a();
    if (getPackageName().equals("com.v1.v1pro"))
    {
      Object localObject;
      if ((this.c.getString("currentStudent_StudentID", "0").equals("0")) && (this.aZ.equals("0"))) {
        localObject = new AlertDialog.Builder(this).setTitle(getString(pm.en)).setMessage(pm.bp).setCancelable(false).setPositiveButton(pm.eo, new aa(this)).setNegativeButton(pm.ej, new ab(this)).create();
      }
      for (;;)
      {
        try
        {
          ((AlertDialog)localObject).setMessage(getString(pm.em));
          if (!isFinishing()) {
            ((AlertDialog)localObject).show();
          }
          return;
        }
        catch (Exception localException3) {}
        if (X)
        {
          setRequestedOrientation(-1);
          if (this.bh != null) {
            this.bh.setTitle(getString(pm.eT));
          }
          if (this.bi != null) {
            this.bi.setVisible(true);
          }
          if (this.bj != null) {
            this.bj.setVisible(true);
          }
          if (this.bk != null) {
            this.bk.setVisible(true);
          }
          if (this.bl != null) {
            this.bl.setVisible(true);
          }
          if (this.m != null) {
            this.m.setVisible(true);
          }
          if (this.aH != null) {
            this.aH.setVisibility(8);
          }
          Settings.System.putInt(getContentResolver(), "sound_effects_enabled", this.aq);
          X = false;
          this.ab.cancel(true);
          if (this.ah.canDetectOrientation()) {
            this.ah.disable();
          }
          if ((!this.bp) && (u != av.a))
          {
            a(av.a);
            if (t[1] != null) {
              t[1].b();
            }
          }
        }
        else
        {
          localObject = s;
          ((View)localObject).setDrawingCacheEnabled(true);
          Bitmap localBitmap = ((View)localObject).getDrawingCache();
          this.as = localBitmap.getWidth();
          this.at = localBitmap.getHeight();
          if (this.as > this.at)
          {
            this.as = 720;
            this.at = 480;
            setRequestedOrientation(0);
            ((View)localObject).setDrawingCacheEnabled(false);
            ((View)localObject).destroyDrawingCache();
            if (u != av.a) {
              this.bp = true;
            }
            if (beginTranscodeRec(this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/test.mp4", this.as, this.at) >= 0)
            {
              if (!this.c.getBoolean("display_tempo", true))
              {
                t[0].b.setVisibility(8);
                t[1].b.setVisibility(8);
              }
              t[0].k();
              t[1].k();
              this.aq = Settings.System.getInt(getContentResolver(), "sound_effects_enabled", 0);
              if (this.aq == 1) {
                Settings.System.putInt(getContentResolver(), "sound_effects_enabled", 0);
              }
              this.aa = new bj(this, (byte)0);
            }
          }
          else
          {
            try
            {
              this.aa.execute(new Integer[0]);
              this.ab = new bk(this, (byte)0);
            }
            catch (Exception localException1)
            {
              try
              {
                this.ab.execute(new Integer[0]);
                X = true;
                this.bq = true;
                if (this.bh != null) {
                  this.bh.setTitle(getString(pm.eU));
                }
                if (this.bi != null) {
                  this.bi.setVisible(false);
                }
                if (this.bj != null) {
                  this.bj.setVisible(false);
                }
                if (this.bk != null) {
                  this.bk.setVisible(false);
                }
                if (this.bl != null) {
                  this.bl.setVisible(false);
                }
                if (this.m != null) {
                  this.m.setVisible(false);
                }
                this.ap = SystemClock.uptimeMillis();
                if (this.aH != null)
                {
                  this.aH.setText("");
                  this.aH.setVisibility(0);
                  r();
                }
                this.bD = getResources().getConfiguration().orientation;
                if (this.ah.canDetectOrientation())
                {
                  this.ah.enable();
                  return;
                  this.as = 480;
                  this.at = 720;
                  setRequestedOrientation(1);
                  continue;
                  localException1 = localException1;
                  localException1.printStackTrace();
                }
              }
              catch (Exception localException2)
              {
                localException2.printStackTrace();
              }
            }
          }
        }
      }
    }
    this.bt.setVisibility(0);
    return;
  }
  
  private static void G()
  {
    int i1 = 0;
    for (;;)
    {
      if (i1 >= 2) {
        return;
      }
      if (((i1 == v) || (v == 2)) && (t != null) && (t[i1] != null)) {
        t[i1].e();
      }
      i1 += 1;
    }
  }
  
  private static void H()
  {
    if ((t != null) && (v < 2) && (t[v] != null)) {
      t[v].d();
    }
  }
  
  private void I()
  {
    a();
    if ((t != null) && (t[0] != null))
    {
      this.r = new Intent(this, AnalyzeActivity.class);
      this.r.putExtra("Encoded_Path", this.aM);
      if (t != null)
      {
        if (t[0] == null) {
          break label232;
        }
        this.r.putExtra("Trim_Start", B.floatValue() / t[0].n * 1000.0F);
        this.r.putExtra("Trim_Finish", C.floatValue() / t[0].n * 1000.0F);
      }
    }
    for (;;)
    {
      this.r.putExtra("TranscodeMe", 1);
      this.r.putExtra("TrimFlag", 2);
      this.r.putExtra("TrimOrientation", x);
      this.r.putExtra("swing_type", this.aU);
      this.r.putExtra("desc", this.aR);
      this.r.putExtra("fromDash", p());
      b(Boolean.valueOf(false));
      A();
      if (this.r != null) {
        startActivity(this.r);
      }
      return;
      label232:
      this.r.putExtra("Trim_Start", 0);
      this.r.putExtra("Trim_Finish", 1000.0F);
    }
  }
  
  private void J()
  {
    Object localObject = getString(pm.fc);
    String str = getString(pm.fd);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(getString(pm.fe));
    localBuilder.setNegativeButton(getString(pm.bB), new af(this));
    ag localAg = new ag(this);
    localBuilder.setItems(new CharSequence[] { localObject, str }, localAg);
    localObject = localBuilder.create();
    try
    {
      if (!isFinishing()) {
        ((AlertDialog)localObject).show();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void K()
  {
    a();
    AlertDialog localAlertDialog = new AlertDialog.Builder(this).setTitle(getString(pm.bh)).setMessage(pm.bo).setCancelable(false).setPositiveButton(pm.iN, new ah(this)).setNegativeButton(pm.ej, new ai(this)).create();
    try
    {
      if (!isFinishing()) {
        localAlertDialog.show();
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void L()
  {
    a();
    new File(this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/" + this.aM.substring(0, this.aM.length() - 3) + "jpg").delete();
    new File(this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/" + this.aM).delete();
    getContentResolver().delete(Uri.parse("content://" + getPackageName() + ".library.db/delete_swing_swingid/" + this.aM.substring(0, this.aM.length() - 4)), null, null);
    A();
    if ((Build.VERSION.SDK_INT >= 8) && (!this.c.getBoolean("override_native_capture", false))) {}
    for (Intent localIntent = new Intent(this, CaptureVideo3.class);; localIntent = new Intent(this, CaptureVideo2.class))
    {
      startActivity(localIntent);
      return;
    }
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3)
  {
    int i1;
    int i2;
    if ((!new File(t[paramInt1].j.substring(0, t[paramInt1].j.length() - 3) + "mp4").exists()) && (!new File(t[paramInt1].j.substring(0, t[paramInt1].j.length() - 3) + "wmv").exists()) && (this.bu))
    {
      i1 = (int)(500.0F / t[paramInt1].n);
      if (!this.ay)
      {
        i2 = c(paramInt1);
        if ((paramInt2 >= paramInt3) || (!this.bC)) {
          break label205;
        }
        if (Math.abs(paramInt2 - (int)(t[paramInt1].o / t[paramInt1].n * 1000.0F)) < i1) {}
      }
    }
    else
    {
      t[paramInt1].a(paramInt2, false);
    }
    label205:
    label363:
    do
    {
      do
      {
        do
        {
          do
          {
            return paramInt2;
            if ((paramInt2 >= paramInt3) || (this.bC)) {
              break;
            }
          } while (Math.abs(paramInt2 - (int)(t[paramInt1].o / t[paramInt1].n * 1000.0F)) < i1);
          break;
          this.bC = true;
        } while ((int)(t[paramInt1].o / t[paramInt1].n * 1000.0F) > aI.getProgress());
        if (paramInt2 <= i2 + 50) {
          break label363;
        }
        if (paramInt2 - (int)(t[paramInt1].o / t[paramInt1].n * 1000.0F) >= i1)
        {
          t[paramInt1].a(paramInt2, false);
          c(paramInt1);
          return paramInt2;
        }
      } while (paramInt2 > 1000);
      t[paramInt1].a(false, false);
      return paramInt2;
    } while (paramInt2 > 1000);
    t[paramInt1].a(false, false);
    return paramInt2;
  }
  
  public static void a(int paramInt)
  {
    try
    {
      if (v == 2)
      {
        float f1 = (float)((t[0].o - aJ[0]) / aK * 1000.0D);
        aI.setProgress(Float.valueOf(f1).intValue());
        return;
      }
      if ((t[v].d != 0) && (t[v].o >= 0.0F))
      {
        Float localFloat = Float.valueOf(t[v].o / t[v].n * 1000.0F);
        if ((paramInt <= 0) || (localFloat.floatValue() >= ai.floatValue())) {
          aI.setProgress(localFloat.intValue());
        }
        ai = localFloat;
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void a(View paramView)
  {
    if (paramView.getBackground() != null) {
      paramView.getBackground().setCallback(null);
    }
    int i1;
    if ((paramView instanceof ViewGroup)) {
      i1 = 0;
    }
    for (;;)
    {
      if (i1 >= ((ViewGroup)paramView).getChildCount())
      {
        ((ViewGroup)paramView).removeAllViews();
        return;
      }
      a(((ViewGroup)paramView).getChildAt(i1));
      i1 += 1;
    }
  }
  
  public static void a(av paramAv)
  {
    if (v != 2) {
      v = 0;
    }
    if (t != null)
    {
      int[] arrayOfInt = M();
      u = paramAv;
      switch (arrayOfInt[paramAv.ordinal()])
      {
      }
    }
    for (;;)
    {
      if (t[0] != null) {
        t[0].c();
      }
      if (t[1] != null) {
        t[1].c();
      }
      if (s != null) {
        s.requestLayout();
      }
      a(-1);
      return;
      t[1].setVisibility(8);
      t[0].b.setTypeface(Typeface.DEFAULT, 1);
      continue;
      t[1].setVisibility(0);
      t[0].b.setTypeface(Typeface.DEFAULT, 1);
      continue;
      t[1].setVisibility(0);
      t[0].b.setTypeface(Typeface.DEFAULT, 1);
    }
  }
  
  public static void a(jq paramJq)
  {
    H = paramJq;
    int i1 = 0;
    for (;;)
    {
      if (i1 >= 2) {
        return;
      }
      if (((i1 == v) || (v == 2)) && (t != null) && (t[i1] != null)) {
        t[i1].f();
      }
      i1 += 1;
    }
  }
  
  private void a(Boolean paramBoolean1, Boolean paramBoolean2)
  {
    if (aA) {}
    for (boolean bool = false;; bool = true)
    {
      aA = bool;
      if (bool) {
        break;
      }
      this.aL.setImageResource(pi.I);
      if ((this.ba.booleanValue()) && (!paramBoolean2.booleanValue())) {
        A = 0;
      }
      u();
      v();
      return;
    }
    this.aL.setImageResource(pi.H);
    if ((this.ba.booleanValue()) && (!paramBoolean2.booleanValue()))
    {
      y();
      A = 1;
    }
    t();
    c(paramBoolean1);
  }
  
  public static void a(boolean paramBoolean)
  {
    int i1;
    try
    {
      if (t[0].a(paramBoolean, false) == 1)
      {
        i1 = 1;
      }
      else
      {
        double d1;
        double d2;
        double d3;
        double d4;
        do
        {
          if ((t[1] != null) && (t[1].a(paramBoolean, false) == 1))
          {
            i1 = 1;
            break;
          }
          d1 = t[1].o;
          d2 = aJ[1];
          d3 = t[0].o;
          d4 = aJ[0];
        } while (d1 - d2 < d3 - d4);
        i1 = 0;
      }
    }
    catch (Exception localException)
    {
      i1 = 0;
    }
    do
    {
      float f1 = (float)(aJ[i1] / t[i1].n);
      t[i1].a(Float.valueOf(f1 * 1000.0F).intValue(), paramBoolean);
      for (;;)
      {
        if (t[i1].o >= aJ[i1]) {}
        while (t[i1].a(paramBoolean, false) == 1)
        {
          i1 += 1;
          break;
        }
      }
      if (i1 == 0) {
        break;
      }
      i1 = 0;
    } while (i1 < 2);
  }
  
  public static native ByteBuffer allocNativeBuffer(int paramInt);
  
  public static void b(int paramInt)
  {
    int i1 = 0;
    v = paramInt;
    if (t != null)
    {
      if ((u == av.c) && (paramInt != 2))
      {
        t[0].c();
        t[1].c();
        s.requestLayout();
      }
      if (paramInt != 2) {
        break label190;
      }
      paramInt = 0;
      if (paramInt < 2) {
        break label65;
      }
    }
    for (;;)
    {
      a(-1);
      return;
      label65:
      aJ[paramInt] = (Math.round(Math.max(0.0D, t[paramInt].o - t[(1 - paramInt)].o) * 1000.0D) / 1000.0D);
      aK = Math.round(Math.min(t[1].n - aJ[1], t[0].n - aJ[0]) * 1000.0D) / 1000.0D;
      paramInt += 1;
      break;
      label190:
      do
      {
        aJ[i1] = (Math.round(t[1].n * 1000.0F) / 1000.0D);
        i1 += 1;
      } while (i1 < 2);
    }
  }
  
  private void b(boolean paramBoolean)
  {
    int i1;
    int i2;
    label16:
    View localView1;
    View localView2;
    int i4;
    if (paramBoolean)
    {
      i1 = 300;
      if (!paramBoolean) {
        break label197;
      }
      i2 = 300;
      localObject2 = findViewById(pj.g);
      localView1 = findViewById(pj.o);
      localView2 = findViewById(pj.y);
      localObject1 = findViewById(pj.f);
      ((View)localObject2).bringToFront();
      i4 = localView2.getHeight();
      if (localView2.getVisibility() != 0) {
        break label202;
      }
    }
    label197:
    label202:
    for (int i3 = 1;; i3 = 0)
    {
      if (i3 == 0) {
        break label208;
      }
      localObject2 = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, 0.0F, 0, i4 * -1);
      ((TranslateAnimation)localObject2).setDuration(i1);
      ((TranslateAnimation)localObject2).setInterpolator(new DecelerateInterpolator());
      ((TranslateAnimation)localObject2).setAnimationListener(new ak(this, localView2, (View)localObject1));
      localObject1 = new AlphaAnimation(1.0F, 0.0F);
      ((AlphaAnimation)localObject1).setDuration(i2);
      ((AlphaAnimation)localObject1).setFillAfter(true);
      localView1.startAnimation((Animation)localObject2);
      localView2.startAnimation((Animation)localObject1);
      M.setImageDrawable(getResources().getDrawable(pi.Y));
      return;
      i1 = 0;
      break;
      i2 = 0;
      break label16;
    }
    label208:
    Object localObject2 = (RelativeLayout.LayoutParams)((View)localObject1).getLayoutParams();
    ((RelativeLayout.LayoutParams)localObject2).topMargin = ((int)getResources().getDimension(ph.a));
    ((View)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    Object localObject1 = new TranslateAnimation(0, 0.0F, 0, 0.0F, 0, i4 * -1, 0, 0.0F);
    ((TranslateAnimation)localObject1).setDuration(i1);
    ((TranslateAnimation)localObject1).setInterpolator(new DecelerateInterpolator());
    localObject2 = new AlphaAnimation(0.0F, 1.0F);
    ((AlphaAnimation)localObject2).setDuration(i2);
    ((AlphaAnimation)localObject2).setFillAfter(true);
    localView2.setVisibility(0);
    localView2.startAnimation((Animation)localObject2);
    localView1.startAnimation((Animation)localObject1);
    M.setImageDrawable(getResources().getDrawable(pi.X));
  }
  
  public static native int beginTranscode(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat);
  
  public static native int beginTranscodeRec(String paramString, int paramInt1, int paramInt2);
  
  private static int c(int paramInt)
  {
    for (double d1 = t[paramInt].p; t[paramInt].o > d1; d1 += 1.0D) {}
    return (int)(d1 / t[paramInt].n * 1000.0D);
  }
  
  private void c(Boolean paramBoolean)
  {
    try
    {
      if (this.D == null)
      {
        this.D = new bf(this, paramBoolean);
        this.D.start();
      }
      return;
    }
    finally
    {
      paramBoolean = finally;
      throw paramBoolean;
    }
  }
  
  public static native void closeMovie(int paramInt);
  
  /* Error */
  private void d(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 283	com/v1/v1golf2/library/AnalyzeActivity:ay	Z
    //   6: ifne +35 -> 41
    //   9: aload_0
    //   10: new 1620	com/v1/v1golf2/library/br
    //   13: dup
    //   14: aload_0
    //   15: iconst_0
    //   16: invokespecial 1621	com/v1/v1golf2/library/br:<init>	(Lcom/v1/v1golf2/library/AnalyzeActivity;B)V
    //   19: putfield 1623	com/v1/v1golf2/library/AnalyzeActivity:ax	Lcom/v1/v1golf2/library/br;
    //   22: aload_0
    //   23: getfield 1623	com/v1/v1golf2/library/AnalyzeActivity:ax	Lcom/v1/v1golf2/library/br;
    //   26: iconst_1
    //   27: anewarray 1012	java/lang/Integer
    //   30: dup
    //   31: iconst_0
    //   32: iload_1
    //   33: invokestatic 1626	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   36: aastore
    //   37: invokevirtual 1627	com/v1/v1golf2/library/br:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   40: pop
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_2
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_2
    //   48: athrow
    //   49: astore_2
    //   50: goto -9 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	this	AnalyzeActivity
    //   0	53	1	paramInt	int
    //   44	4	2	localObject	Object
    //   49	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   2	22	44	finally
    //   22	41	44	finally
    //   22	41	49	java/lang/Exception
  }
  
  public static native int detectKeyFrame(int paramInt1, int paramInt2);
  
  public static native int doTranscode();
  
  public static native int doTranscodeRec(Bitmap paramBitmap, float paramFloat);
  
  public static native int doTranscodeRecAudio(short[] paramArrayOfShort, int paramInt);
  
  /* Error */
  private void e(int paramInt)
  {
    // Byte code:
    //   0: iload_1
    //   1: ifne +259 -> 260
    //   4: getstatic 372	com/v1/v1golf2/library/AnalyzeActivity:s	Lcom/v1/v1golf2/library/bu;
    //   7: ifnull +88 -> 95
    //   10: getstatic 372	com/v1/v1golf2/library/AnalyzeActivity:s	Lcom/v1/v1golf2/library/bu;
    //   13: invokevirtual 1639	com/v1/v1golf2/library/bu:getRootView	()Landroid/view/View;
    //   16: astore_2
    //   17: aload_2
    //   18: iconst_1
    //   19: invokevirtual 964	android/view/View:setDrawingCacheEnabled	(Z)V
    //   22: aload_0
    //   23: aload_2
    //   24: invokevirtual 968	android/view/View:getDrawingCache	()Landroid/graphics/Bitmap;
    //   27: putfield 370	com/v1/v1golf2/library/AnalyzeActivity:f	Landroid/graphics/Bitmap;
    //   30: new 658	java/io/File
    //   33: dup
    //   34: new 424	java/lang/StringBuilder
    //   37: dup
    //   38: aload_0
    //   39: getfield 293	com/v1/v1golf2/library/AnalyzeActivity:bb	Ljava/lang/String;
    //   42: invokestatic 986	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   45: invokespecial 427	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: ldc_w 988
    //   51: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_0
    //   55: invokevirtual 725	com/v1/v1golf2/library/AnalyzeActivity:getPackageName	()Ljava/lang/String;
    //   58: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: ldc_w 1388
    //   64: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokespecial 659	java/io/File:<init>	(Ljava/lang/String;)V
    //   73: astore_2
    //   74: aload_0
    //   75: getfield 370	com/v1/v1golf2/library/AnalyzeActivity:f	Landroid/graphics/Bitmap;
    //   78: getstatic 1221	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   81: bipush 75
    //   83: new 1604	java/io/FileOutputStream
    //   86: dup
    //   87: aload_2
    //   88: invokespecial 1640	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   91: invokevirtual 1225	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   94: pop
    //   95: aload_0
    //   96: new 1642	com/facebook/a/f
    //   99: dup
    //   100: ldc_w 1644
    //   103: invokespecial 1645	com/facebook/a/f:<init>	(Ljava/lang/String;)V
    //   106: putfield 1200	com/v1/v1golf2/library/AnalyzeActivity:am	Lcom/facebook/a/f;
    //   109: aload_0
    //   110: new 1244	com/facebook/a/a
    //   113: dup
    //   114: aload_0
    //   115: getfield 1200	com/v1/v1golf2/library/AnalyzeActivity:am	Lcom/facebook/a/f;
    //   118: invokespecial 1648	com/facebook/a/a:<init>	(Lcom/facebook/a/f;)V
    //   121: putfield 1237	com/v1/v1golf2/library/AnalyzeActivity:an	Lcom/facebook/a/a;
    //   124: aload_0
    //   125: getfield 1200	com/v1/v1golf2/library/AnalyzeActivity:am	Lcom/facebook/a/f;
    //   128: aload_0
    //   129: invokestatic 1653	com/v1/v1golf2/library/rg:a	(Lcom/facebook/a/f;Landroid/content/Context;)Z
    //   132: pop
    //   133: iload_1
    //   134: iconst_1
    //   135: if_icmpne +184 -> 319
    //   138: iconst_3
    //   139: anewarray 534	java/lang/String
    //   142: astore_2
    //   143: aload_2
    //   144: iconst_0
    //   145: aload_0
    //   146: getstatic 1656	com/v1/v1golf2/library/pm:fx	I
    //   149: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   152: aastore
    //   153: aload_2
    //   154: iconst_1
    //   155: aload_0
    //   156: getstatic 1659	com/v1/v1golf2/library/pm:fD	I
    //   159: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   162: aastore
    //   163: aload_2
    //   164: iconst_2
    //   165: aload_0
    //   166: getstatic 1662	com/v1/v1golf2/library/pm:fz	I
    //   169: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   172: aastore
    //   173: new 666	android/app/AlertDialog$Builder
    //   176: dup
    //   177: aload_0
    //   178: invokespecial 669	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   181: astore_3
    //   182: aload_3
    //   183: aload_0
    //   184: getstatic 1289	com/v1/v1golf2/library/pm:fC	I
    //   187: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   190: invokevirtual 675	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   193: pop
    //   194: aload_3
    //   195: aload_0
    //   196: getstatic 1118	com/v1/v1golf2/library/pm:bB	I
    //   199: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   202: new 1664	com/v1/v1golf2/library/ac
    //   205: dup
    //   206: aload_0
    //   207: invokespecial 1665	com/v1/v1golf2/library/ac:<init>	(Lcom/v1/v1golf2/library/AnalyzeActivity;)V
    //   210: invokevirtual 1124	android/app/AlertDialog$Builder:setNegativeButton	(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    //   213: pop
    //   214: aload_3
    //   215: new 1667	com/v1/v1golf2/library/ad
    //   218: dup
    //   219: aload_0
    //   220: invokespecial 1668	com/v1/v1golf2/library/ad:<init>	(Lcom/v1/v1golf2/library/AnalyzeActivity;)V
    //   223: invokevirtual 1672	android/app/AlertDialog$Builder:setOnCancelListener	(Landroid/content/DialogInterface$OnCancelListener;)Landroid/app/AlertDialog$Builder;
    //   226: pop
    //   227: aload_3
    //   228: aload_2
    //   229: new 1674	com/v1/v1golf2/library/ae
    //   232: dup
    //   233: aload_0
    //   234: iload_1
    //   235: invokespecial 1676	com/v1/v1golf2/library/ae:<init>	(Lcom/v1/v1golf2/library/AnalyzeActivity;I)V
    //   238: invokevirtual 1133	android/app/AlertDialog$Builder:setItems	([Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
    //   241: pop
    //   242: aload_3
    //   243: invokevirtual 709	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   246: astore_2
    //   247: aload_2
    //   248: invokevirtual 845	android/app/AlertDialog:show	()V
    //   251: return
    //   252: astore_2
    //   253: aload_2
    //   254: invokevirtual 1053	java/lang/Exception:printStackTrace	()V
    //   257: goto -162 -> 95
    //   260: iload_1
    //   261: iconst_1
    //   262: if_icmpne -167 -> 95
    //   265: aload_0
    //   266: new 424	java/lang/StringBuilder
    //   269: dup
    //   270: aload_0
    //   271: getfield 293	com/v1/v1golf2/library/AnalyzeActivity:bb	Ljava/lang/String;
    //   274: invokestatic 986	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   277: invokespecial 427	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   280: ldc_w 988
    //   283: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: aload_0
    //   287: invokevirtual 725	com/v1/v1golf2/library/AnalyzeActivity:getPackageName	()Ljava/lang/String;
    //   290: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: ldc_w 1392
    //   296: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: invokevirtual 440	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   302: invokestatic 1590	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   305: putfield 370	com/v1/v1golf2/library/AnalyzeActivity:f	Landroid/graphics/Bitmap;
    //   308: goto -213 -> 95
    //   311: astore_2
    //   312: aload_2
    //   313: invokevirtual 1053	java/lang/Exception:printStackTrace	()V
    //   316: goto -221 -> 95
    //   319: aload_0
    //   320: getfield 664	com/v1/v1golf2/library/AnalyzeActivity:aU	I
    //   323: bipush 10
    //   325: if_icmpne +40 -> 365
    //   328: getstatic 217	com/v1/v1golf2/library/AnalyzeActivity:u	Lcom/v1/v1golf2/library/av;
    //   331: getstatic 215	com/v1/v1golf2/library/av:a	Lcom/v1/v1golf2/library/av;
    //   334: if_acmpne +31 -> 365
    //   337: iconst_2
    //   338: anewarray 534	java/lang/String
    //   341: astore_2
    //   342: aload_2
    //   343: iconst_0
    //   344: aload_0
    //   345: getstatic 1656	com/v1/v1golf2/library/pm:fx	I
    //   348: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   351: aastore
    //   352: aload_2
    //   353: iconst_1
    //   354: aload_0
    //   355: getstatic 1659	com/v1/v1golf2/library/pm:fD	I
    //   358: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   361: aastore
    //   362: goto -189 -> 173
    //   365: aload_0
    //   366: getfield 1200	com/v1/v1golf2/library/AnalyzeActivity:am	Lcom/facebook/a/f;
    //   369: invokevirtual 1678	com/facebook/a/f:b	()Z
    //   372: ifeq +108 -> 480
    //   375: getstatic 217	com/v1/v1golf2/library/AnalyzeActivity:u	Lcom/v1/v1golf2/library/av;
    //   378: getstatic 215	com/v1/v1golf2/library/av:a	Lcom/v1/v1golf2/library/av;
    //   381: if_acmpeq +41 -> 422
    //   384: iconst_3
    //   385: anewarray 534	java/lang/String
    //   388: astore_2
    //   389: aload_2
    //   390: iconst_0
    //   391: aload_0
    //   392: getstatic 1656	com/v1/v1golf2/library/pm:fx	I
    //   395: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   398: aastore
    //   399: aload_2
    //   400: iconst_1
    //   401: aload_0
    //   402: getstatic 1659	com/v1/v1golf2/library/pm:fD	I
    //   405: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   408: aastore
    //   409: aload_2
    //   410: iconst_2
    //   411: aload_0
    //   412: getstatic 1662	com/v1/v1golf2/library/pm:fz	I
    //   415: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   418: aastore
    //   419: goto -246 -> 173
    //   422: iconst_5
    //   423: anewarray 534	java/lang/String
    //   426: astore_2
    //   427: aload_2
    //   428: iconst_0
    //   429: aload_0
    //   430: getstatic 1656	com/v1/v1golf2/library/pm:fx	I
    //   433: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   436: aastore
    //   437: aload_2
    //   438: iconst_1
    //   439: aload_0
    //   440: getstatic 1659	com/v1/v1golf2/library/pm:fD	I
    //   443: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   446: aastore
    //   447: aload_2
    //   448: iconst_2
    //   449: aload_0
    //   450: getstatic 1662	com/v1/v1golf2/library/pm:fz	I
    //   453: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   456: aastore
    //   457: aload_2
    //   458: iconst_3
    //   459: aload_0
    //   460: getstatic 1681	com/v1/v1golf2/library/pm:fE	I
    //   463: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   466: aastore
    //   467: aload_2
    //   468: iconst_4
    //   469: aload_0
    //   470: getstatic 1684	com/v1/v1golf2/library/pm:fA	I
    //   473: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   476: aastore
    //   477: goto -304 -> 173
    //   480: getstatic 217	com/v1/v1golf2/library/AnalyzeActivity:u	Lcom/v1/v1golf2/library/av;
    //   483: getstatic 215	com/v1/v1golf2/library/av:a	Lcom/v1/v1golf2/library/av;
    //   486: if_acmpeq +31 -> 517
    //   489: iconst_2
    //   490: anewarray 534	java/lang/String
    //   493: astore_2
    //   494: aload_2
    //   495: iconst_0
    //   496: aload_0
    //   497: getstatic 1656	com/v1/v1golf2/library/pm:fx	I
    //   500: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   503: aastore
    //   504: aload_2
    //   505: iconst_1
    //   506: aload_0
    //   507: getstatic 1659	com/v1/v1golf2/library/pm:fD	I
    //   510: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   513: aastore
    //   514: goto -341 -> 173
    //   517: iconst_3
    //   518: anewarray 534	java/lang/String
    //   521: astore_2
    //   522: aload_2
    //   523: iconst_0
    //   524: aload_0
    //   525: getstatic 1656	com/v1/v1golf2/library/pm:fx	I
    //   528: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   531: aastore
    //   532: aload_2
    //   533: iconst_1
    //   534: aload_0
    //   535: getstatic 1659	com/v1/v1golf2/library/pm:fD	I
    //   538: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   541: aastore
    //   542: aload_2
    //   543: iconst_2
    //   544: aload_0
    //   545: getstatic 1681	com/v1/v1golf2/library/pm:fE	I
    //   548: invokevirtual 556	com/v1/v1golf2/library/AnalyzeActivity:getString	(I)Ljava/lang/String;
    //   551: aastore
    //   552: goto -379 -> 173
    //   555: astore_2
    //   556: aload_2
    //   557: invokevirtual 1053	java/lang/Exception:printStackTrace	()V
    //   560: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	561	0	this	AnalyzeActivity
    //   0	561	1	paramInt	int
    //   16	232	2	localObject	Object
    //   252	2	2	localException1	Exception
    //   311	2	2	localException2	Exception
    //   341	202	2	arrayOfString	String[]
    //   555	2	2	localException3	Exception
    //   181	62	3	localBuilder	AlertDialog.Builder
    // Exception table:
    //   from	to	target	type
    //   74	95	252	java/lang/Exception
    //   265	308	311	java/lang/Exception
    //   247	251	555	java/lang/Exception
  }
  
  public static native int endTranscode();
  
  public static native int endTranscodeRec();
  
  public static native void freeANativeWindow();
  
  public static native void freeNativeBuffer(ByteBuffer paramByteBuffer);
  
  public static native int[] getBitmap(int paramInt, byte paramByte);
  
  public static native int getBitmapBuffer(int paramInt, IntBuffer paramIntBuffer, byte paramByte);
  
  public static native float getDuration(int paramInt);
  
  public static native float[] getKeyFrameArray(int paramInt);
  
  public static native int getLastError();
  
  public static native int getMovieHeight(int paramInt);
  
  public static native int getMovieWidth(int paramInt);
  
  public static native int getNEON();
  
  public static native float getPosition(int paramInt);
  
  public static native int initJNI();
  
  public static native int openMovie(String paramString, int paramInt, float paramFloat);
  
  private void r()
  {
    if (!X) {
      return;
    }
    long l1 = SystemClock.uptimeMillis() - this.ap;
    long l2 = (Math.max(0L, this.au - l1) + 999L) / 1000L;
    long l3 = l2 / 60L;
    long l4 = l3 / 60L;
    Object localObject2 = Long.toString(l2 - l3 * 60L);
    Object localObject1 = localObject2;
    if (((String)localObject2).length() < 2) {
      localObject1 = "0" + (String)localObject2;
    }
    String str = Long.toString(l3 - 60L * l4);
    localObject2 = str;
    if (str.length() < 2) {
      localObject2 = "0" + str;
    }
    localObject2 = localObject2 + ":" + (String)localObject1;
    if (l4 > 0L)
    {
      str = Long.toString(l4);
      localObject1 = str;
      if (str.length() < 2) {
        localObject1 = "0" + str;
      }
    }
    for (localObject1 = localObject1 + ":" + (String)localObject2;; localObject1 = localObject2)
    {
      if (this.aS != null) {
        if (!this.ag.booleanValue())
        {
          localObject2 = new CursorLoader(this, Uri.parse("content://" + getPackageName() + ".library.db/fetch_student_swingid/" + this.aS), null, null, null, null).loadInBackground();
          if (localObject2 != null)
          {
            if (((Cursor)localObject2).getCount() > 0) {
              this.aH.setText(localObject1 + "  " + getString(pm.cj) + ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndexOrThrow("FirstName")) + " " + ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndexOrThrow("LastName")));
            }
            ((Cursor)localObject2).close();
          }
        }
      }
      for (;;)
      {
        this.bE.sendEmptyMessageDelayed(6, 1000L - l1 % 1000L);
        if (!((String)localObject1).equals("00:00")) {
          break;
        }
        F();
        return;
        if (!this.ag.booleanValue())
        {
          localObject2 = this.c.getString("currentStudent_Name", "");
          this.aH.setText(localObject1 + "  " + getString(pm.cj) + (String)localObject2);
        }
      }
    }
  }
  
  private void s()
  {
    Log.d("V1", "setting menu open true");
    this.ag = Boolean.valueOf(true);
    Object localObject2 = new ArrayList();
    if (X)
    {
      ((ArrayList)localObject2).add(getString(pm.eU));
      ((ArrayList)localObject2).add(getString(pm.cc));
      ((ArrayList)localObject2).add(getString(pm.cK));
      ((ArrayList)localObject2).add(getString(pm.hQ));
    }
    for (;;)
    {
      Object localObject1 = (CharSequence[])((ArrayList)localObject2).toArray(new CharSequence[0]);
      localObject2 = new AlertDialog.Builder(this);
      ((AlertDialog.Builder)localObject2).setCancelable(true);
      ((AlertDialog.Builder)localObject2).setNegativeButton(getString(pm.bB), new j(this));
      ((AlertDialog.Builder)localObject2).setItems((CharSequence[])localObject1, new k(this, (CharSequence[])localObject1));
      localObject1 = ((AlertDialog.Builder)localObject2).create();
      label191:
      label274:
      label448:
      label463:
      label478:
      label521:
      try
      {
        ((AlertDialog)localObject1).getWindow().getAttributes().windowAnimations = 17432578;
        ((AlertDialog)localObject1).setCanceledOnTouchOutside(true);
        if (!isFinishing()) {
          ((AlertDialog)localObject1).show();
        }
        return;
      }
      catch (Exception localException) {}
      if (w != 0)
      {
        localObject1 = getString(pm.cC);
        if ((this.aU != 2) || (w == 0)) {
          ((ArrayList)localObject2).add(localObject1);
        }
        if (w != 0) {
          break label521;
        }
        if (getPackageName().equals("com.v1.v1pro")) {
          ((ArrayList)localObject2).add(getString(pm.eT));
        }
        if (this.aP.equals("")) {
          break label463;
        }
        if (this.h != 0) {
          break label448;
        }
        ((ArrayList)localObject2).add(getString(pm.eC));
        if (((this.aU == 2) || (this.aU == 7)) && (u == av.a) && (!getPackageName().equals("com.v1.v1pro"))) {
          ((ArrayList)localObject2).add(getString(pm.cA));
        }
        if (((this.aU == 2) || (this.aU == 7)) && (u == av.a)) {
          ((ArrayList)localObject2).add(getString(pm.fb));
        }
        if ((this.aU != 9) || (getPackageName().equals("com.v1.v1pro"))) {
          break label478;
        }
        ((ArrayList)localObject2).add(getString(pm.bi));
      }
      for (;;)
      {
        if (!getPackageName().equals("com.v1.v1pro")) {
          ((ArrayList)localObject2).add(getString(pm.fB));
        }
        ((ArrayList)localObject2).add(getString(pm.dp));
        break;
        localObject1 = getString(pm.cB);
        break label191;
        ((ArrayList)localObject2).add(getString(pm.cc));
        break label274;
        ((ArrayList)localObject2).add(getString(pm.cc));
        break label274;
        if (!getPackageName().equals("com.v1.v1pro")) {
          ((ArrayList)localObject2).add(getString(pm.ic));
        } else {
          ((ArrayList)localObject2).add(getString(pm.ea));
        }
      }
      ((ArrayList)localObject2).add(getString(pm.hG));
      ((ArrayList)localObject2).add(getString(pm.fb));
      ((ArrayList)localObject2).add(getString(pm.cA));
      if (this.aU == 2) {
        ((ArrayList)localObject2).add(getString(pm.eZ));
      }
    }
  }
  
  public static native void seekMovie(int paramInt1, int paramInt2);
  
  public static native float setDuration(int paramInt, float paramFloat);
  
  public static native void setUpANativeWindow(Surface paramSurface);
  
  public static native int stepMovie(int paramInt);
  
  public static native void stepRMovie(int paramInt);
  
  public static native void stepRMovieTeach(int paramInt, float paramFloat);
  
  public static native String stringFromJNI(int paramInt);
  
  private void t()
  {
    try
    {
      if (this.E == null)
      {
        this.E = new bi(this);
        this.E.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void u()
  {
    try
    {
      if (this.E != null) {
        this.E.a();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static native int useANativeBitmapBuffer(int paramInt, byte paramByte);
  
  private void v()
  {
    try
    {
      if (this.D != null) {
        this.D.a();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void w()
  {
    int i1;
    if ((this.D == null) && (this.E == null) && (aI.getProgress() > 0)) {
      i1 = 0;
    }
    for (;;)
    {
      if (i1 >= 2) {
        return;
      }
      t[i1].c(false);
      i1 += 1;
    }
  }
  
  private void x()
  {
    List localList = getPackageManager().getInstalledPackages(0);
    int i1 = 0;
    for (;;)
    {
      if (i1 >= localList.size()) {
        return;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i1);
      if (localPackageInfo.packageName.equals("com.dynamixsoftware.printershare"))
      {
        this.be = 1;
        return;
      }
      if (localPackageInfo.packageName.equals("com.dynamixsoftware.printershare.amazon"))
      {
        this.be = 2;
        return;
      }
      i1 += 1;
    }
  }
  
  private void y()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setAnimationListener(new ba(this, (byte)0));
    localAlphaAnimation.setDuration(400L);
    TranslateAnimation localTranslateAnimation1 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    localTranslateAnimation1.setDuration(400L);
    localTranslateAnimation1.setInterpolator(new LinearInterpolator());
    localTranslateAnimation1.setAnimationListener(new az(this, (byte)0));
    TranslateAnimation localTranslateAnimation2 = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    localTranslateAnimation2.setDuration(400L);
    localTranslateAnimation2.setInterpolator(new LinearInterpolator());
    localTranslateAnimation2.setAnimationListener(new az(this, (byte)0));
    if (I != null) {
      I.startAnimation(localTranslateAnimation1);
    }
    if (J != null) {
      J.startAnimation(localTranslateAnimation1);
    }
    if (K != null) {
      K.startAnimation(localTranslateAnimation1);
    }
    L.startAnimation(localAlphaAnimation);
    if (U != null) {
      U.startAnimation(localAlphaAnimation);
    }
    T.startAnimation(localAlphaAnimation);
  }
  
  private void z()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setAnimationListener(new bc(this, (byte)0));
    localAlphaAnimation.setDuration(400L);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 1.0F, 1, 0.0F);
    localTranslateAnimation.setDuration(400L);
    localTranslateAnimation.setInterpolator(new LinearInterpolator());
    localTranslateAnimation.setAnimationListener(new bb(this, (byte)0));
    if (I != null) {
      I.startAnimation(localTranslateAnimation);
    }
    if (J != null) {
      J.startAnimation(localTranslateAnimation);
    }
    if (K != null) {
      K.startAnimation(localTranslateAnimation);
    }
    L.startAnimation(localAlphaAnimation);
    if (U != null) {
      U.startAnimation(localAlphaAnimation);
    }
    T.startAnimation(localAlphaAnimation);
    A = 0;
  }
  
  public final void a()
  {
    if (aA)
    {
      this.aL.setImageResource(pi.I);
      u();
      v();
      if (!aA) {
        break label37;
      }
    }
    label37:
    for (boolean bool = false;; bool = true)
    {
      aA = bool;
      return;
    }
  }
  
  final void b()
  {
    new bs(this, (byte)0).execute(new String[0]);
  }
  
  final void c()
  {
    FrameLayout localFrameLayout = (FrameLayout)findViewById(pj.aS);
    if (s != null) {
      localFrameLayout.removeView(s);
    }
    System.gc();
  }
  
  final void d()
  {
    s = new bu(this);
    ((FrameLayout)findViewById(pj.aS)).addView(s, 0);
    ni[] arrayOfNi = new ni[2];
    t = arrayOfNi;
    arrayOfNi[0] = new ni(this);
    t[1] = new ni(this);
    t[1].a = 1;
    s.addView(t[1]);
    s.addView(t[0]);
    this.bt = getLayoutInflater().inflate(pk.e, null);
    getWindow().addContentView(this.bt, new ViewGroup.LayoutParams(-1, -1));
    this.bt.setOnTouchListener(new q(this));
    if (!this.bs)
    {
      this.bt.setVisibility(0);
      this.d.putBoolean("helpshown", true);
      this.d.commit();
      return;
    }
    this.bt.setVisibility(4);
    b(false);
  }
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i1 = 0;
    this.aj = Boolean.valueOf(false);
    Log.d("V1", "analyze request = " + paramInt1);
    Log.d("V1", "analyze result = " + paramInt2);
    if (paramInt1 == 0)
    {
      paramInt1 = i1;
      switch (paramInt2)
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
            a(jq.b);
            L.setImageDrawable(getResources().getDrawable(pi.ae));
            G = paramInt2 - 1;
            return;
            a(jq.c);
            L.setImageDrawable(getResources().getDrawable(pi.ac));
            G = paramInt2 - 1;
            return;
            a(jq.d);
            L.setImageDrawable(getResources().getDrawable(pi.ab));
            G = paramInt2 - 1;
            return;
            a(jq.e);
            L.setImageDrawable(getResources().getDrawable(pi.ad));
            G = paramInt2 - 1;
            return;
            a(jq.f);
            L.setImageDrawable(getResources().getDrawable(pi.aa));
            G = paramInt2 - 1;
            return;
            do
            {
              if (((paramInt1 == v) || (v == 2)) && (t != null) && (t[paramInt1] != null)) {
                t[paramInt1].h();
              }
              paramInt1 += 1;
            } while (paramInt1 < 2);
            return;
            F = paramInt2 - 7;
            return;
            F = paramInt2 - 7;
            return;
            F = paramInt2 - 7;
            return;
            F = paramInt2 - 7;
            return;
            F = paramInt2 - 7;
            return;
            F = paramInt2 - 7;
            return;
            if (paramInt1 != 1) {
              break;
            }
            if (paramInt2 == 0)
            {
              paramIntent = new HashMap();
              paramIntent.put("Choice", "Cancel");
              paramIntent.put("package", getPackageName());
              FlurryAgent.onEvent("Upgrade Dialog - Compare With Banner", paramIntent);
              return;
            }
          } while (paramInt2 != 1);
          paramIntent = new HashMap();
          paramIntent.put("Choice", "BannerClick");
          paramIntent.put("package", getPackageName());
          FlurryAgent.onEvent("Upgrade Dialog - Compare With Banner", paramIntent);
          a();
          this.c = PreferenceManager.getDefaultSharedPreferences(this);
          this.d = this.c.edit();
          this.d.putString("Compare_FirstSwing", this.aO);
          this.d.commit();
          A();
          Toast.makeText(getApplicationContext(), getString(pm.ez), 1).show();
          return;
          if (paramInt1 != 2) {
            break;
          }
        } while ((paramInt2 == 0) || (paramInt2 != 1));
        e(1);
        return;
        if ((paramInt1 == 3) && (paramInt2 == -1))
        {
          paramIntent = paramIntent.getExtras().getString("compareFile");
          paramIntent = this.ao.a(paramIntent);
          if (Boolean.valueOf(t[1].a(paramIntent, z)).booleanValue()) {
            t[1].b(false);
          }
          a(av.b);
          return;
        }
        if (paramInt1 != 4) {
          break;
        }
      } while (paramInt2 != 0);
      if ((!Dashboard.j()) && (p().booleanValue()))
      {
        paramIntent = new Intent(this, Dashboard.class);
        paramIntent.addFlags(603979776);
        startActivity(paramIntent);
        paramIntent = new Intent("kill");
        paramIntent.setType("text/plain");
        sendBroadcast(paramIntent);
      }
      finish();
      return;
    } while ((paramInt1 != 5) || (paramInt2 != -1));
    s();
  }
  
  public void onBackPressed()
  {
    Log.d("V1", "on back pressed");
    if (X)
    {
      F();
      return;
    }
    if (aA)
    {
      a(Boolean.valueOf(true), Boolean.valueOf(false));
      return;
    }
    a();
    if ((this.c != null) && (this.d != null))
    {
      this.d = this.c.edit();
      this.d.putString("Compare_FirstSwing", "");
      this.d.commit();
    }
    A();
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    int i2 = 0;
    int i3 = 0;
    int i1 = 0;
    for (;;)
    {
      try
      {
        if (paramView == T)
        {
          Log.d("V1", " on key down2");
          a();
          s();
          return;
        }
        CharSequence[] arrayOfCharSequence;
        e localE1;
        e localE2;
        e localE3;
        e localE4;
        e localE5;
        if (paramView == L)
        {
          arrayOfCharSequence = new CharSequence[5];
          arrayOfCharSequence[0] = "Line";
          arrayOfCharSequence[1] = "Circle";
          arrayOfCharSequence[2] = "Box";
          arrayOfCharSequence[3] = "Pan Zoom";
          arrayOfCharSequence[4] = "Move";
          this.bm = new oz(this, 0);
          localE1 = new e(1, arrayOfCharSequence[0].toString(), getResources().getDrawable(pi.ae));
          localE2 = new e(2, arrayOfCharSequence[1].toString(), getResources().getDrawable(pi.ac));
          localE3 = new e(3, arrayOfCharSequence[2].toString(), getResources().getDrawable(pi.ab));
          localE4 = new e(4, arrayOfCharSequence[3].toString(), getResources().getDrawable(pi.ad));
          localE5 = new e(5, arrayOfCharSequence[4].toString(), getResources().getDrawable(pi.aa));
          this.bm.a(localE1);
          this.bm.a(localE2);
          this.bm.a(localE3);
          this.bm.a(localE4);
          this.bm.a(localE5);
          this.bm.a(new al(this, arrayOfCharSequence));
          this.bm.b(paramView);
          return;
        }
        if (paramView == N)
        {
          H();
          return;
        }
        if (paramView == O)
        {
          G();
          return;
        }
        if (paramView == P)
        {
          if (i1 < 2)
          {
            if (((i1 != v) && (v != 2)) || (t == null) || (t[i1] == null)) {
              break label1071;
            }
            t[i1].h();
            break label1071;
          }
        }
        else
        {
          if (paramView == Q)
          {
            arrayOfCharSequence = new CharSequence[6];
            arrayOfCharSequence[0] = "White";
            arrayOfCharSequence[1] = "Red";
            arrayOfCharSequence[2] = "Blue";
            arrayOfCharSequence[3] = "Yellow";
            arrayOfCharSequence[4] = "Green";
            arrayOfCharSequence[5] = "Black";
            this.bm = new oz(this, 0);
            localE1 = new e(1, arrayOfCharSequence[0].toString(), getResources().getDrawable(pi.p));
            localE2 = new e(2, arrayOfCharSequence[1].toString(), getResources().getDrawable(pi.o));
            localE3 = new e(3, arrayOfCharSequence[2].toString(), getResources().getDrawable(pi.m));
            localE4 = new e(4, arrayOfCharSequence[3].toString(), getResources().getDrawable(pi.q));
            localE5 = new e(5, arrayOfCharSequence[4].toString(), getResources().getDrawable(pi.n));
            e localE6 = new e(6, arrayOfCharSequence[5].toString(), getResources().getDrawable(pi.l));
            this.bm.a(localE1);
            this.bm.a(localE2);
            this.bm.a(localE3);
            this.bm.a(localE4);
            this.bm.a(localE5);
            this.bm.a(localE6);
            this.bm.a(new am(this, arrayOfCharSequence));
            this.bm.b(paramView);
            return;
          }
          if (paramView == R)
          {
            startActivity(new Intent(this, Tools_Help.class));
            return;
          }
          if (paramView == M)
          {
            S.setSelected(false);
            b(true);
            return;
          }
          if (paramView == aB)
          {
            a();
            if ((this.D == null) && (this.E == null) && (t != null))
            {
              i1 = i2;
              if (i1 < 2)
              {
                if ((i1 != v) && (v != 2)) {
                  break label1078;
                }
                if ((v == 2) && (t[0] != null) && (t[1] != null))
                {
                  w();
                  a(-1);
                  break label1078;
                }
                if ((!new File(this.aO).exists()) && (this.bu))
                {
                  if (this.ay) {
                    break label1078;
                  }
                  d(i1);
                  break label1078;
                }
                t[i1].c(false);
                a(-1);
                break label1078;
              }
            }
          }
          else if (paramView == aC)
          {
            if (!this.ay)
            {
              a();
              if ((this.D == null) && (this.E == null) && (t != null))
              {
                i1 = i3;
                if (i1 >= 2)
                {
                  a(1);
                  return;
                }
                if ((i1 != v) && (v != 2)) {
                  break label1085;
                }
                if ((v == 2) && (t[0] != null) && (t[1] != null))
                {
                  a(false);
                  break label1085;
                }
                t[i1].a(false, true);
                if (w != 1) {
                  break label1085;
                }
                if (t[i1].a())
                {
                  B = Float.valueOf(t[i1].o);
                  break label1085;
                }
                C = Float.valueOf(t[i1].o);
                break label1085;
              }
            }
          }
          else if (paramView == aD)
          {
            a(Boolean.valueOf(true), Boolean.valueOf(false));
          }
        }
        return;
      }
      catch (Exception paramView)
      {
        return;
      }
      label1071:
      i1 += 1;
      continue;
      label1078:
      i1 += 1;
      continue;
      label1085:
      i1 += 1;
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = new AlphaAnimation(0.0F, 1.0F);
    paramConfiguration.setDuration(750L);
    paramConfiguration.setFillAfter(true);
    paramConfiguration.setFillEnabled(true);
    try
    {
      if (u != null) {
        if (u == av.a)
        {
          if (t[0] != null) {
            t[0].startAnimation(paramConfiguration);
          }
        }
        else
        {
          if (t[0] != null) {
            t[0].startAnimation(paramConfiguration);
          }
          if (t[1] != null)
          {
            t[1].startAnimation(paramConfiguration);
            return;
          }
        }
      }
    }
    catch (Exception paramConfiguration) {}
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!Build.PRODUCT.contains("Kindle Fire")) {
      getWindow().setFlags(1024, 1024);
    }
    getWindow().addFlags(128);
    if (Build.VERSION.SDK_INT < 11)
    {
      requestWindowFeature(1);
      this.ah = new r(this, this);
      this.ac = new GestureDetector(new be(this));
      this.ao = ((V1GolfLib)getApplication());
      FlurryAgent.onPageView();
      System.gc();
    }
    Object localObject;
    for (;;)
    {
      try
      {
        initJNI();
      }
      catch (UnsatisfiedLinkError paramBundle)
      {
        try
        {
          x();
          if (Build.PRODUCT.contains("apollo"))
          {
            setContentView(pk.d);
            this.bb = this.ao.d();
            this.bd = ((WindowManager)getSystemService("window")).getDefaultDisplay();
            aJ = new double[2];
            paramBundle = (SeekBar)findViewById(pj.cB);
            aI = paramBundle;
            paramBundle.setOnSeekBarChangeListener(this);
            aI.setMax(1000);
            aG = findViewById(pj.n);
            paramBundle = (ImageButton)findViewById(pj.l);
            T = paramBundle;
            paramBundle.setOnClickListener(this);
            T.setOnLongClickListener(this);
            U = findViewById(pj.m);
            if (Build.PRODUCT.contains("NOOK")) {
              T.setImageResource(pi.aj);
            }
            paramBundle = (ImageButton)findViewById(pj.x);
            L = paramBundle;
            paramBundle.setOnClickListener(this);
            L.setOnLongClickListener(this);
            paramBundle = findViewById(pj.p);
            aD = paramBundle;
            paramBundle.setOnClickListener(this);
            aD.setOnLongClickListener(this);
            this.aL = ((ImageButton)findViewById(pj.p));
            paramBundle = findViewById(pj.u);
            aB = paramBundle;
            paramBundle.setOnClickListener(this);
            aB.setOnLongClickListener(this);
            paramBundle = findViewById(pj.v);
            aC = paramBundle;
            paramBundle.setOnClickListener(this);
            aC.setOnLongClickListener(this);
            aF = findViewById(pj.cC);
            aE = findViewById(pj.C);
            this.aH = ((TextView)findViewById(pj.G));
            paramBundle = (ImageButton)findViewById(pj.e);
            M = paramBundle;
            paramBundle.setOnClickListener(this);
            paramBundle = (ImageButton)findViewById(pj.D);
            N = paramBundle;
            paramBundle.setOnClickListener(this);
            N.setOnLongClickListener(this);
            paramBundle = (ImageButton)findViewById(pj.d);
            O = paramBundle;
            paramBundle.setOnClickListener(this);
            O.setOnLongClickListener(this);
            paramBundle = (ImageButton)findViewById(pj.h);
            P = paramBundle;
            paramBundle.setOnClickListener(this);
            P.setOnLongClickListener(this);
            paramBundle = (ImageButton)findViewById(pj.a);
            Q = paramBundle;
            paramBundle.setOnClickListener(this);
            Q.setOnLongClickListener(this);
            paramBundle = (ImageButton)findViewById(pj.z);
            R = paramBundle;
            paramBundle.setOnClickListener(this);
            R.setOnLongClickListener(this);
            S = (LinearLayout)findViewById(pj.f);
            M.setOnTouchListener(new y(this));
            paramBundle = (TableRow)findViewById(pj.cC);
            I = paramBundle;
            paramBundle.setOnTouchListener(new aj(this));
            if (Build.VERSION.SDK_INT >= 11)
            {
              I.setOnSystemUiVisibilityChangeListener(new an(this));
              paramBundle = new Handler();
              localObject = new ao(this);
              this.bn = false;
              paramBundle.postDelayed((Runnable)localObject, 1000L);
              T.setOnTouchListener(new ap(this));
              aD.setOnTouchListener(new aq(this));
              aB.setOnTouchListener(new ar(this));
              aC.setOnTouchListener(new h(this));
              aI.setOnTouchListener(new i(this));
            }
            paramBundle = getIntent().getExtras();
            this.c = PreferenceManager.getDefaultSharedPreferences(this);
            this.d = this.c.edit();
            this.aP = this.c.getString("Compare_FirstSwing", "");
            z = this.c.getInt("Compare_Orientation", 0);
            this.bu = this.c.getBoolean("teachmode", false);
            this.bv = this.c.getBoolean("hiqualitymode", false);
            this.d.putString("Compare_FirstSwing", "");
            this.d.putInt("Compare_Orientation", 0);
            this.d.commit();
            V = Boolean.valueOf(this.c.getBoolean("disabletouch", true));
            W = Boolean.valueOf(this.c.getBoolean("disableback", false));
            this.n = Boolean.valueOf(this.c.getBoolean("PaidFlag", true));
            this.ba = Boolean.valueOf(this.c.getBoolean("autohide", false));
            this.bs = this.c.getBoolean("helpshown", false);
            this.j = this.c.getInt("selectedSpeed_rate", 100);
            this.k = this.c.getInt("selectedSpeed_rate_wait", 0);
            this.aM = paramBundle.getString("Encoded_Path");
            this.aU = paramBundle.getInt("swing_type", 0);
            this.aW = paramBundle.getInt("TranscodeMe", 0);
            this.aR = paramBundle.getString("desc");
            this.aZ = paramBundle.getString("student_id");
            if (this.aZ == null) {
              this.aZ = "0";
            }
            this.bw = paramBundle.getBoolean("fromSonyCamApp", false);
            a(Boolean.valueOf(this.bw));
            this.aS = paramBundle.getString("swing_id");
            Log.d("V1", "lesson_swing_id=" + this.aS);
            if (new File(this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/" + this.aM.substring(0, this.aM.length() - 3) + "mp4").exists()) {
              break label1961;
            }
            if (this.aU != 2) {
              break label1911;
            }
            this.aN = this.ao.a(this.aM);
            if (this.aN.equals("bumblebee"))
            {
              if (!this.aM.substring(this.aM.length() - 3, this.aM.length()).equals("mp4")) {
                break label1878;
              }
              this.aN = this.ao.a(this.aM.substring(0, this.aM.length() - 3) + "3gp");
              if (!this.aN.equals("bumblebee")) {
                break label1860;
              }
              this.o = Boolean.valueOf(true);
              Toast.makeText(getApplicationContext(), getString(pm.hM), 1).show();
              A();
            }
            if (!this.o.booleanValue())
            {
              this.aO = (this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/" + this.aM.substring(0, this.aM.length() - 3) + "mp4");
              if ((!new File(this.aO).exists()) && (this.bu)) {
                this.by = new ThreadPoolExecutor(this.ad, this.ad, 10L, TimeUnit.SECONDS, this.ae);
              }
              this.aT = paramBundle.getInt("TrimFlag", 0);
              if (this.bf.booleanValue()) {
                this.aT = 1;
              }
              this.az = paramBundle.getBoolean("ForceTrim", false);
              this.aX = paramBundle.getFloat("Trim_Start", 0.0F);
              this.aY = paramBundle.getFloat("Trim_Finish", 0.0F);
              x = paramBundle.getInt("TrimOrientation", 0);
              this.bx = x;
              if (x < 0) {
                x = 0;
              }
              if (this.bd.getWidth() <= this.bd.getHeight()) {
                break label2030;
              }
              this.bc = 1;
              if ((!new File(this.aO).exists()) && (!this.bu) && (this.aU == 7) && (this.aT != 2)) {
                this.aT = 1;
              }
              if (this.aT != 1) {
                break label2038;
              }
              Log.d("V1", "going towards trim!");
              w = 1;
              this.bu = true;
              d();
              b();
            }
            label1700:
            Build.PRODUCT.contains("NOOK");
            if (!V.booleanValue())
            {
              this.b = ((SensorManager)getSystemService("sensor"));
              if (!Boolean.valueOf(this.b.registerListener(this, this.b.getDefaultSensor(1), 3)).booleanValue()) {
                this.b.unregisterListener(this);
              }
            }
            return;
            paramBundle = getWindow().getDecorView();
            if (getActionBar() != null) {
              getActionBar().hide();
            }
            paramBundle.setSystemUiVisibility(1);
            paramBundle.setOnSystemUiVisibilityChangeListener(new f(this));
            break;
            paramBundle = paramBundle;
            paramBundle.printStackTrace();
            Toast.makeText(getApplicationContext(), getString(pm.dD), 1).show();
            A();
            continue;
          }
          setContentView(pk.c);
          continue;
          if (this.bu) {
            continue;
          }
        }
        catch (Exception paramBundle)
        {
          paramBundle.printStackTrace();
          A();
          return;
        }
      }
      label1860:
      this.bf = Boolean.valueOf(true);
      continue;
      label1878:
      this.o = Boolean.valueOf(true);
      Toast.makeText(getApplicationContext(), getString(pm.hM), 1).show();
      A();
      continue;
      label1911:
      this.aN = (this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/" + this.aM);
      continue;
      label1961:
      this.aN = (this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/" + this.aM.substring(0, this.aM.length() - 3) + "mp4");
      continue;
      label2030:
      this.bc = 0;
    }
    label2038:
    Log.d("V1", "not going towards trim!");
    w = 0;
    float f1;
    if (this.aX > this.aY)
    {
      f1 = this.aX;
      this.aX = this.aY;
      this.aY = f1;
    }
    if (this.aV == 1) {
      x = 1;
    }
    paramBundle = Boolean.valueOf(true);
    int i1 = Build.VERSION.SDK_INT;
    if (i1 >= 10) {}
    for (;;)
    {
      String str;
      int i2;
      int i3;
      int i4;
      try
      {
        localObject = new MediaMetadataRetriever();
        ((MediaMetadataRetriever)localObject).setDataSource(this.aN);
        f1 = Float.parseFloat(((MediaMetadataRetriever)localObject).extractMetadata(9));
        Log.d("V1", "timeInmillisec=" + f1);
        float f2 = f1 / 1000.0F;
        f1 = f2;
        if (f2 == 0.0D) {
          f1 = 0.0F;
        }
        Log.d("V1", "java_duration=" + f1);
        if ((this.bu) && (!this.az))
        {
          if (x > 4) {
            x /= 4;
          }
          if (this.aT == 2)
          {
            if (this.aN.equals(this.aO)) {
              this.aO = (this.aO.substring(0, this.aO.length() - 4) + "_undead.mp4");
            }
            if (this.aO.contains("trim_")) {
              this.aO = this.aO.substring(5, this.aO.length());
            }
            if (this.aO.contains("Models/")) {
              this.aO = this.aO.replace("Models/", "");
            }
            localObject = this.aN;
            str = this.aO;
            i2 = x;
            i3 = (int)this.aX;
            i4 = (int)this.aY;
            if (!this.bv) {
              break label4392;
            }
            i1 = 1;
            if (beginTranscode((String)localObject, str, i2, i3, i4, i1, f1) < 0) {
              continue;
            }
            this.aT = 0;
          }
        }
      }
      catch (Exception localException2) {}
      try
      {
        showDialog(0);
        T.setVisibility(8);
        if (U != null) {
          U.setVisibility(8);
        }
        L.setVisibility(8);
        aB.setVisibility(8);
        aC.setVisibility(8);
        aD.setVisibility(8);
        aI.setVisibility(8);
        I.setVisibility(8);
        this.Y = new aw(this, (byte)0);
        this.Y.execute(new String[] { x });
      }
      catch (Exception localException3)
      {
        label4392:
        continue;
      }
      switch (F)
      {
      case 0: 
        switch (G)
        {
        case 0: 
          localObject = paramBundle;
          if (this.g == 1)
          {
            i1 = Build.VERSION.SDK_INT;
            localObject = new HashMap();
            ((HashMap)localObject).put("moviePath", this.aN);
            ((HashMap)localObject).put("moviePathEncoded", this.aO);
            FlurryAgent.onEvent("Analyzing Video", (Map)localObject);
            if (this.aP.equals(""))
            {
              a(av.a);
              v = 0;
              f1 = (float)(Math.round(Float.valueOf(t[0].o).floatValue() * 10000.0D) / 10000.0D);
              t[0].a(false, false);
              localObject = Float.valueOf(Float.valueOf((float)(Math.round(Float.valueOf(t[0].o).floatValue() * 10000.0D) / 10000.0D)).floatValue() - Float.valueOf(f1).floatValue());
              Z = (Float)localObject;
              Z = Float.valueOf((float)(Math.round(((Float)localObject).floatValue() * 10000.0D) / 10000.0D));
              t[0].c(false);
              localObject = paramBundle;
            }
          }
          else
          {
            paramBundle = new File(this.bb + "/v1golf/Android/data/" + getPackageName() + "/files/");
            if ((((Boolean)localObject).booleanValue()) || (!paramBundle.exists())) {
              break label1700;
            }
            if (p.booleanValue()) {
              continue;
            }
            Toast.makeText(getApplicationContext(), getString(pm.hL), 1).show();
            A();
            break label1700;
            d();
            if ((new File(this.aO).exists()) && (this.aW == 0) && (this.aT == 0))
            {
              localObject = Boolean.valueOf(t[0].a(this.aO, x));
              paramBundle = (Bundle)localObject;
              if (((Boolean)localObject).booleanValue())
              {
                t[0].b(false);
                paramBundle = (Bundle)localObject;
              }
              this.g = 1;
              continue;
            }
            if ((this.aN.substring(this.aN.length() - 3, this.aN.length()).equals("wmv")) && (this.aW == 0)) {
              this.aO = this.aN;
            }
            localObject = Boolean.valueOf(t[0].a(this.aN, x));
            paramBundle = (Bundle)localObject;
            if (!((Boolean)localObject).booleanValue()) {
              continue;
            }
            t[0].b(false);
            paramBundle = (Bundle)localObject;
            continue;
            if ((this.aN.equals(this.aO)) && (this.aT > 0)) {
              this.aO = (this.aO.substring(0, this.aO.length() - 4) + "_undead.mp4");
            }
            if (this.aO.contains("trim_")) {
              this.aO = this.aO.replaceAll("trim_", "");
            }
            if (this.aO.contains("Models/")) {
              this.aO = this.aO.replace("Models/", "");
            }
            if ((this.aN.substring(this.aN.length() - 3, this.aN.length()).equals("wmv")) && (this.aW == 0))
            {
              this.aO = this.aN;
              d();
              paramBundle = Boolean.valueOf(t[0].a(this.aN, x));
              if (paramBundle.booleanValue()) {
                t[0].b(false);
              }
              this.g = 1;
              continue;
            }
            if ((new File(this.aO).exists()) && (this.aW == 0) && (this.aT == 0))
            {
              d();
              paramBundle = Boolean.valueOf(t[0].a(this.aO, x));
              if (paramBundle.booleanValue()) {
                t[0].b(false);
              }
              this.g = 1;
              continue;
            }
            localObject = this.aN;
            str = this.aO;
            i2 = x;
            i3 = (int)this.aX;
            i4 = (int)this.aY;
            if (this.bv)
            {
              i1 = 1;
              if (beginTranscode((String)localObject, str, i2, i3, i4, i1, f1) < 0) {
                continue;
              }
              if (this.aT == 2) {
                this.aT = 0;
              }
            }
            try
            {
              showDialog(0);
              T.setVisibility(8);
              if (U != null) {
                U.setVisibility(8);
              }
              L.setVisibility(8);
              aB.setVisibility(8);
              aC.setVisibility(8);
              aD.setVisibility(8);
              aI.setVisibility(8);
              I.setVisibility(8);
              this.Y = new aw(this, (byte)0);
              this.Y.execute(new String[] { x });
            }
            catch (Exception localException1) {}
            i1 = 0;
            continue;
            continue;
            d();
            paramBundle = Boolean.valueOf(t[0].a(this.aN, x));
            if (paramBundle.booleanValue()) {
              t[0].b(false);
            }
            this.g = 1;
            continue;
            Q.setImageDrawable(getResources().getDrawable(pi.p));
          }
          break;
        }
        break;
      case 1: 
        Q.setImageDrawable(getResources().getDrawable(pi.o));
        break;
      case 2: 
        Q.setImageDrawable(getResources().getDrawable(pi.m));
        break;
      case 3: 
        Q.setImageDrawable(getResources().getDrawable(pi.q));
        break;
      case 4: 
        Q.setImageDrawable(getResources().getDrawable(pi.n));
        break;
      case 5: 
        Q.setImageDrawable(getResources().getDrawable(pi.l));
        continue;
        L.setImageDrawable(getResources().getDrawable(pi.ae));
        continue;
        L.setImageDrawable(getResources().getDrawable(pi.ac));
        continue;
        L.setImageDrawable(getResources().getDrawable(pi.ab));
        continue;
        L.setImageDrawable(getResources().getDrawable(pi.ad));
        continue;
        L.setImageDrawable(getResources().getDrawable(pi.aa));
        continue;
        this.aQ = (this.aP.substring(0, this.aP.length() - 3) + "mp4");
        Boolean localBoolean;
        if (this.aP.substring(this.aP.length() - 3, this.aP.length()).equals("wmv"))
        {
          localBoolean = Boolean.valueOf(t[1].a(this.aP, z));
          paramBundle = localBoolean;
          if (localBoolean.booleanValue())
          {
            t[1].b(false);
            paramBundle = localBoolean;
          }
          a(av.b);
        }
        else if (this.bu)
        {
          if (new File(this.aQ).exists())
          {
            localBoolean = Boolean.valueOf(t[1].a(this.aQ, z));
            paramBundle = localBoolean;
            if (localBoolean.booleanValue())
            {
              t[1].b(false);
              paramBundle = localBoolean;
            }
          }
          else
          {
            localBoolean = Boolean.valueOf(t[1].a(this.aP.substring(0, this.aP.length() - 3) + "3gp", z));
            paramBundle = localBoolean;
            if (localBoolean.booleanValue())
            {
              t[1].b(false);
              paramBundle = localBoolean;
            }
          }
        }
        else if (new File(this.aQ).exists())
        {
          localBoolean = Boolean.valueOf(t[1].a(this.aQ, z));
          paramBundle = localBoolean;
          if (localBoolean.booleanValue())
          {
            t[1].b(false);
            paramBundle = localBoolean;
            continue;
            if (!this.c.getString("newTrim", "0").equals("2"))
            {
              paramBundle = new Intent(this, TrimVideo.class);
              paramBundle.putExtra("swing_type", 7);
              paramBundle.putExtra("Encoded_Path", this.aM);
              paramBundle.putExtra("desc", this.aR);
              paramBundle.putExtra("fromCapture", false);
              paramBundle.putExtra("fromImport", true);
              startActivity(paramBundle);
            }
            else
            {
              paramBundle = new Intent(this, AnalyzeActivity.class);
              paramBundle.putExtra("swing_type", 7);
              paramBundle.putExtra("desc", this.aR);
              paramBundle.putExtra("TrimFlag", 1);
              paramBundle.putExtra("swing_id", this.aM.substring(0, this.aM.length() - 4));
              paramBundle.putExtra("Encoded_Path", this.aM);
              startActivity(paramBundle);
            }
          }
        }
        break;
      default: 
        continue;
        continue;
        f1 = 0.0F;
        continue;
        i1 = 0;
      }
    }
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      this.e = new ProgressDialog(this);
      this.e.setCancelable(false);
      this.e.setProgressStyle(1);
      this.e.setTitle(getString(pm.cH));
      this.e.setButton(-2, getString(pm.bB), new l(this));
      return this.e;
    case 1: 
      return new AlertDialog.Builder(this).setTitle(pm.et).setMessage(pm.es).setCancelable(false).setPositiveButton(pm.bz, new m(this)).setNegativeButton(pm.bB, new n(this)).create();
    }
    return new AlertDialog.Builder(this).setTitle(pm.el).setMessage(pm.ek).setCancelable(false).setPositiveButton(pm.bA, new o(this)).setNegativeButton(pm.bB, new p(this)).create();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    try
    {
      this.l = paramMenu;
      if ((Build.VERSION.SDK_INT >= 11) && (!this.aP.equals(""))) {
        paramMenu.add(3, 12, 0, getString(pm.fs)).setShortcut('5', 'o').setIcon(pi.L).setShowAsAction(5);
      }
      if (w == 0) {
        if (!this.aP.equals(""))
        {
          this.bg = paramMenu.add(1, 3, 0, getString(pm.eC)).setShortcut('1', 'o').setIcon(pi.L);
          if (((this.aU == 2) || (this.aU == 7)) && (u == av.a)) {
            this.m = paramMenu.add(0, 9, 0, getString(pm.fb)).setShortcut('3', 'i').setIcon(pi.T);
          }
          if (this.aU != 9) {
            break label535;
          }
          this.bi = paramMenu.add(2, 6, 0, getString(pm.bi)).setShortcut('2', 'l').setIcon(pi.G);
          label239:
          this.bj = paramMenu.add(2, 7, 0, getString(pm.fB)).setShortcut('3', 'n').setIcon(pi.D);
          paramMenu.add(0, 4, 0, getString(pm.cK)).setShortcut('4', 'e').setIcon(pi.N);
          paramMenu.add(0, 5, 0, getString(pm.hQ)).setShortcut('5', 'u').setIcon(pi.S);
          this.bk = paramMenu.add(0, 14, 0, getString(pm.dp)).setShortcut('6', 'h').setIcon(pi.O);
          if (getPackageName().equals("com.v1.v1pro")) {
            this.bh = paramMenu.add(0, 15, 0, getString(pm.eT)).setShortcut('7', 'z').setIcon(pi.W);
          }
          label434:
          if (w == 0) {
            break label730;
          }
        }
      }
      label535:
      label730:
      for (String str = getString(pm.cC);; str = getString(pm.cB))
      {
        if ((this.aU == 2) && (w != 0)) {
          break label741;
        }
        this.bl = paramMenu.add(0, 1, 0, str).setShortcut('3', 'x').setIcon(pi.J);
        return true;
        this.bg = paramMenu.add(1, 3, 0, getString(pm.cc)).setShortcut('1', 'o').setIcon(pi.L);
        break;
        this.bi = paramMenu.add(2, 6, 0, getString(pm.ic)).setShortcut('2', 'l').setIcon(pi.G);
        break label239;
        paramMenu.add(0, 8, 0, getString(pm.hG)).setShortcut('4', 's').setIcon(pi.U);
        this.m = paramMenu.add(0, 9, 0, getString(pm.fb)).setShortcut('3', 'i').setIcon(pi.T);
        paramMenu.add(0, 10, 0, getString(pm.cA)).setShortcut('2', 'd').setIcon(pi.N);
        if (this.aU != 2) {
          break label434;
        }
        paramMenu.add(0, 11, 0, getString(pm.eZ)).setShortcut('1', 'r').setIcon(pi.K);
        break label434;
      }
      label741:
      return true;
    }
    catch (Exception paramMenu) {}
    return true;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    Log.d("V1", " on key down");
    if (paramInt == 82)
    {
      s();
      a();
      if ((this.ba.booleanValue()) && (A == 1) && (aA)) {
        z();
      }
    }
    do
    {
      return true;
      if (paramInt != 4) {
        break;
      }
      if ((this.ba.booleanValue()) && (A == 1) && (aA)) {
        z();
      }
    } while (W.booleanValue());
    onBackPressed();
    return true;
    return false;
  }
  
  public boolean onLongClick(View paramView)
  {
    if (paramView == L)
    {
      Toast.makeText(getApplicationContext(), getString(pm.ba), 1).show();
      return true;
    }
    if (paramView == N)
    {
      Toast.makeText(getApplicationContext(), getString(pm.bb), 1).show();
      return true;
    }
    if (paramView == O)
    {
      Toast.makeText(getApplicationContext(), getString(pm.aW), 1).show();
      return true;
    }
    if (paramView == P)
    {
      Toast.makeText(getApplicationContext(), getString(pm.aX), 1).show();
      return true;
    }
    if (paramView == Q)
    {
      Toast.makeText(getApplicationContext(), getString(pm.aV), 1).show();
      return true;
    }
    if (paramView == R)
    {
      Toast.makeText(getApplicationContext(), getString(pm.aY), 1).show();
      return true;
    }
    if (paramView == T)
    {
      Toast.makeText(getApplicationContext(), getString(pm.aZ), 1).show();
      return true;
    }
    CharSequence[] arrayOfCharSequence;
    e localE1;
    e localE2;
    label330:
    e localE3;
    label376:
    e localE4;
    label422:
    e localE5;
    label468:
    e localE6;
    if (paramView == aD)
    {
      arrayOfCharSequence = new CharSequence[6];
      arrayOfCharSequence[0] = "100% (1/1)";
      arrayOfCharSequence[1] = "75% (3/4)";
      arrayOfCharSequence[2] = "67% (2/3)";
      arrayOfCharSequence[3] = "50% (1/2)";
      arrayOfCharSequence[4] = "33% (1/3)";
      arrayOfCharSequence[5] = "25% (1/4)";
      localE1 = new e();
      localE1.a(arrayOfCharSequence[0].toString());
      if (this.j != 100) {
        break label770;
      }
      localE1.a(getResources().getDrawable(pi.aN));
      localE2 = new e();
      localE2.a(arrayOfCharSequence[1].toString());
      if (this.j != 75) {
        break label788;
      }
      localE2.a(getResources().getDrawable(pi.aN));
      localE3 = new e();
      localE3.a(arrayOfCharSequence[2].toString());
      if (this.j != 67) {
        break label806;
      }
      localE3.a(getResources().getDrawable(pi.aN));
      localE4 = new e();
      localE4.a(arrayOfCharSequence[3].toString());
      if (this.j != 50) {
        break label824;
      }
      localE4.a(getResources().getDrawable(pi.aN));
      localE5 = new e();
      localE5.a(arrayOfCharSequence[4].toString());
      if (this.j != 33) {
        break label842;
      }
      localE5.a(getResources().getDrawable(pi.aN));
      localE6 = new e();
      localE6.a(arrayOfCharSequence[5].toString());
      if (this.j != 25) {
        break label860;
      }
      localE6.a(getResources().getDrawable(pi.aN));
    }
    int i1;
    for (;;)
    {
      this.bm = new oz(this, 1);
      this.bm.a(localE1);
      this.bm.a(localE2);
      this.bm.a(localE3);
      this.bm.a(localE4);
      this.bm.a(localE5);
      this.bm.a(localE6);
      this.bm.a(new s(this, arrayOfCharSequence));
      this.bm.b(paramView);
      if ((paramView == aC) && (!this.ay))
      {
        a();
        if ((this.D == null) && (this.E == null) && (t != null))
        {
          i1 = 0;
          if (i1 < 2) {
            break label878;
          }
          a(-1);
        }
        a(Boolean.valueOf(true), Boolean.valueOf(true));
        aC.setOnTouchListener(new t(this));
      }
      if (paramView == aB)
      {
        a();
        if ((this.D == null) && (this.E == null) && (t != null))
        {
          i1 = 0;
          if (i1 < 2) {
            break label940;
          }
        }
        if ((new File(this.aO).exists()) || (!this.bu))
        {
          a(Boolean.valueOf(false), Boolean.valueOf(true));
          aB.setOnTouchListener(new u(this));
        }
      }
      return false;
      label770:
      localE1.a(getResources().getDrawable(pi.aM));
      break;
      label788:
      localE2.a(getResources().getDrawable(pi.aM));
      break label330;
      label806:
      localE3.a(getResources().getDrawable(pi.aM));
      break label376;
      label824:
      localE4.a(getResources().getDrawable(pi.aM));
      break label422;
      label842:
      localE5.a(getResources().getDrawable(pi.aM));
      break label468;
      label860:
      localE6.a(getResources().getDrawable(pi.aM));
    }
    label878:
    if ((i1 == v) || (v == 2))
    {
      if ((v != 2) || (t[0] == null) || (t[1] == null)) {
        break label926;
      }
      a(false);
    }
    for (;;)
    {
      i1 += 1;
      break;
      label926:
      t[i1].a(false, true);
    }
    label940:
    if ((i1 == v) || (v == 2))
    {
      if ((v != 2) || (t[0] == null) || (t[1] == null)) {
        break label992;
      }
      w();
      a(-1);
    }
    for (;;)
    {
      i1 += 1;
      break;
      label992:
      if ((!new File(this.aO).exists()) && (this.bu))
      {
        if (!this.ay) {
          d(i1);
        }
      }
      else
      {
        t[i1].c(false);
        a(-1);
      }
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    this.ag = Boolean.valueOf(false);
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 12: 
      ni.l();
      if (t != null)
      {
        t[0].i();
        t[1].i();
      }
      b(2);
      return true;
    case 16908332: 
      a();
      this.d = this.c.edit();
      this.d.putString("Compare_FirstSwing", "");
      this.d.commit();
      A();
      return true;
    case 2: 
      paramMenuItem = new Intent(this, Tools_PopUp.class);
      paramMenuItem.putExtra("selected", G);
      paramMenuItem.putExtra("color_selected", F);
      startActivityForResult(paramMenuItem, 0);
      return true;
    case 3: 
      B();
      return true;
    case 4: 
      G();
      return true;
    case 7: 
      D();
      return true;
    case 13: 
      a();
      FlurryAgent.onEvent("Sequence Video");
      try
      {
        if (U != null) {
          U.setVisibility(4);
        }
        L.setVisibility(4);
        aI.setVisibility(4);
        aD.setVisibility(4);
        aB.setVisibility(4);
        aC.setVisibility(4);
        aF.setVisibility(4);
        aE.setVisibility(0);
        t[0].b.setVisibility(4);
        new ax(this, (byte)0).execute(new String[0]);
        return true;
      }
      catch (Exception paramMenuItem)
      {
        return true;
      }
    case 5: 
      H();
      return true;
    case 6: 
      C();
      return true;
    case 1: 
      onBackPressed();
      return true;
    case 8: 
      I();
      return true;
    case 9: 
      J();
      return true;
    case 10: 
      K();
      return true;
    case 11: 
      L();
      return true;
    case 14: 
      E();
      return true;
    }
    F();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.b != null) {
      this.b.unregisterListener(this);
    }
    a();
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      a();
      if (this.E != null) {
        break label278;
      }
      if (v >= 2) {
        break label124;
      }
    }
    for (;;)
    {
      try
      {
        this.bz = a(v, paramInt, this.bz);
        if (w == 1)
        {
          if (t[v].a()) {
            B = Float.valueOf(paramInt / 1000.0F * t[v].n);
          }
        }
        else
        {
          a(-1);
          return;
        }
        float f1 = paramInt / 1000.0F;
        C = Float.valueOf(f1 * t[v].n);
        continue;
      }
      catch (Exception paramSeekBar)
      {
        paramSeekBar.printStackTrace();
        continue;
      }
      label124:
      int i1 = 0;
      while (i1 < 2)
      {
        if (i1 == 0) {}
        try
        {
          this.bA = a(i1, (int)(aJ[i1] / t[i1].n * 1000.0D + paramInt / 1000.0F * aK / t[i1].n * 1000.0D), this.bA);
        }
        catch (Exception paramSeekBar)
        {
          paramSeekBar.printStackTrace();
        }
        this.bB = a(i1, (int)(aJ[i1] / t[i1].n * 1000.0D + paramInt / 1000.0F * aK / t[i1].n * 1000.0D), this.bB);
        break label330;
        try
        {
          label278:
          this.E.join(1000L);
          if (this.E != null) {
            this.E = null;
          }
          onProgressChanged(paramSeekBar, paramInt, paramBoolean);
          return;
        }
        catch (Exception localException)
        {
          if (this.E != null) {
            this.E = null;
          }
          onProgressChanged(paramSeekBar, paramInt, paramBoolean);
          return;
        }
        label330:
        i1 += 1;
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    this.c = PreferenceManager.getDefaultSharedPreferences(this);
    this.n = Boolean.valueOf(this.c.getBoolean("PaidFlag", true));
    if ((this.b != null) && (!Boolean.valueOf(this.b.registerListener(this, this.b.getDefaultSensor(1), 3)).booleanValue())) {
      this.b.unregisterListener(this);
    }
  }
  
  public boolean onSearchRequested()
  {
    return false;
  }
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    int i1 = 0;
    if (!V.booleanValue())
    {
      float f1 = paramSensorEvent.values[0];
      float f2 = paramSensorEvent.values[1];
      float f3 = paramSensorEvent.values[2];
      f1 = (float)(FloatMath.sqrt(f1 * f1 + f2 * f2 + f3 * f3) - 9.81D);
      if ((this.g != 1) || (Math.abs(f1) < 10.0F)) {
        break label151;
      }
      int i2 = this.q + 1;
      this.q = i2;
      if (i2 < 2) {}
    }
    for (;;)
    {
      if (i1 >= 2) {
        return;
      }
      if (((i1 == v) || (v == 2)) && (t != null) && (t[i1] != null)) {
        t[i1].e();
      }
      i1 += 1;
    }
    label151:
    this.q = 0;
  }
  
  public void onStart()
  {
    super.onStart();
    FlurryAgent.onStartSession(this, getString(pm.dg));
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar) {}
  
  protected void onStop()
  {
    super.onStop();
    if (this.b != null) {
      this.b.unregisterListener(this);
    }
    FlurryAgent.onEndSession(this);
    a();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar) {}
  
  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }
}
