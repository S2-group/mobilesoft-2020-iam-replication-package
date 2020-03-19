package com.sysdevsolutions.kclientlibv40;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.RingtoneManager;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.y.c;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.StarMicronics.jasura.b.a;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.d.a;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import xappdevelopment.xappdevelopment.BarcodeReader.KClientDlg;

public class CMyFormDlg
  extends Activity
{
  static final String b = CDadosCarregados.c;
  static final String c = CDadosCarregados.d;
  static final String d = CDadosCarregados.e;
  boolean A;
  boolean B;
  boolean C;
  boolean D;
  boolean E;
  boolean F;
  boolean G;
  boolean H;
  int I;
  boolean J;
  boolean K;
  boolean L;
  ImageView M;
  Bitmap N;
  Bitmap O;
  int[] P;
  int[] Q;
  int[] R;
  int[] S;
  double[] T;
  double[] U;
  boolean V;
  boolean W;
  boolean X;
  boolean Y;
  boolean Z;
  y a = new y(this);
  boolean aA;
  boolean aB;
  boolean aC;
  boolean aD;
  boolean aE;
  boolean aF;
  boolean aG;
  boolean aH;
  boolean aI;
  boolean aJ;
  b[] aK;
  int[] aL;
  int[] aM;
  int aN;
  KeyEvent aO;
  Runnable[] aP;
  public final cl aQ;
  ProgressDialog aR;
  ReentrantLock aS;
  volatile int aT;
  Runnable aU;
  boolean aV;
  String[] aW;
  boolean[] aX;
  boolean aY;
  boolean aZ;
  boolean aa;
  boolean ab;
  boolean ac;
  public boolean ad;
  boolean ae;
  boolean af;
  boolean ag;
  boolean ah;
  boolean ai;
  boolean aj;
  boolean ak;
  boolean al;
  boolean am;
  boolean an;
  boolean ao;
  boolean ap;
  boolean aq;
  boolean ar;
  boolean as;
  boolean at;
  boolean au;
  boolean av;
  boolean aw;
  boolean ax;
  boolean ay;
  boolean az;
  int ba;
  dz[] bb;
  volatile int bc;
  volatile Intent bd;
  boolean be;
  Object bf;
  volatile boolean bg;
  final Handler bh;
  boolean bi;
  int[] bj;
  int bk;
  int bl;
  boolean bm;
  boolean bn;
  BroadcastReceiver bo;
  private final BroadcastReceiver bp;
  private BroadcastReceiver bq;
  CMyFormDlg e = null;
  as f = null;
  String g = "";
  volatile boolean h;
  Runnable i;
  public int j;
  String k;
  int l;
  int m;
  int n;
  double o;
  double p;
  int q;
  String r;
  int s;
  int t;
  int u;
  boolean v;
  volatile boolean w;
  volatile Object x;
  volatile TempVars y;
  volatile ec z;
  
  public CMyFormDlg()
  {
    int i3 = 0;
    this.h = false;
    this.i = null;
    this.o = 1.0D;
    this.p = 1.0D;
    this.x = new Object();
    this.y = null;
    this.z = null;
    this.C = false;
    this.H = false;
    this.I = 0;
    this.J = true;
    this.K = false;
    this.L = false;
    this.M = null;
    this.N = null;
    this.O = null;
    this.P = new int[2];
    this.Q = new int[2];
    this.R = new int[2];
    this.S = new int[2];
    this.T = new double[2];
    this.U = new double[2];
    this.aN = 0;
    this.aO = null;
    this.aP = new Runnable[10];
    this.aQ = new cl();
    this.aR = null;
    this.aS = new ReentrantLock();
    this.aU = null;
    this.aV = false;
    this.aW = new String[100];
    this.aX = new boolean[100];
    this.aY = false;
    this.aZ = false;
    this.ba = 0;
    this.bb = null;
    this.bd = null;
    this.be = false;
    this.bf = new Object();
    this.bh = new Handler();
    this.bi = false;
    this.bj = new int[2];
    this.bn = false;
    this.bo = null;
    this.bp = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        paramAnonymousContext = paramAnonymousIntent.getAction();
        if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(paramAnonymousContext))
        {
          Object localObject1 = null;
          int i = 0;
          while (i < 10)
          {
            localObject2 = localObject1;
            if (CDadosCarregados.bg[i] == 3)
            {
              localObject2 = localObject1;
              if (CDadosCarregados.bf[i] != null)
              {
                paramAnonymousContext = localObject1;
                if (localObject1 == null) {
                  paramAnonymousContext = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                }
                localObject2 = paramAnonymousContext;
              }
            }
            try
            {
              if (CDadosCarregados.bf[i].getRemoteDevice().getAddress().equalsIgnoreCase(paramAnonymousContext.getAddress()))
              {
                CDadosCarregados.bf[i].close();
                localObject2 = paramAnonymousContext;
              }
            }
            catch (Exception localException)
            {
              for (;;)
              {
                localObject2 = paramAnonymousContext;
              }
            }
            i += 1;
            localObject1 = localObject2;
          }
        }
        if ("android.bluetooth.device.action.FOUND".equals(paramAnonymousContext))
        {
          if (CDadosCarregados.bN != null)
          {
            paramAnonymousContext = (BluetoothDevice)paramAnonymousIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (paramAnonymousContext == null) {
              return;
            }
            CDadosCarregados.bN.a(paramAnonymousContext);
          }
        }
        else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(paramAnonymousContext))
        {
          if (CDadosCarregados.bN != null) {
            CDadosCarregados.bN.a();
          }
        }
        else if (("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(paramAnonymousContext)) && (CDadosCarregados.bO != null) && (CDadosCarregados.bO.e != null))
        {
          if (paramAnonymousIntent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10) == 12)
          {
            CDadosCarregados.bO.e.set(1);
            return;
          }
          if (paramAnonymousIntent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10) == 11) {
            return;
          }
          CDadosCarregados.bO.e.set(-1);
        }
      }
    };
    this.bq = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        CMyFormDlg localCMyFormDlg = CDadosCarregados.y;
        paramAnonymousIntent = localCMyFormDlg;
        if (!localCMyFormDlg.aq) {
          paramAnonymousIntent = CMyFormDlg.this;
        }
        if (paramAnonymousIntent.aq)
        {
          paramAnonymousContext = new w(paramAnonymousIntent.bh, paramAnonymousIntent);
          paramAnonymousContext.d = false;
          paramAnonymousContext.a(paramAnonymousIntent.j, -2, "S_(13)", new de()
          {
            public void a()
            {
              if (CDadosCarregados.b) {
                MyFcmListenerService.b(CMyFormDlg.this, CDadosCarregados.a);
              }
              CDadosCarregados.a = null;
            }
          });
          return;
        }
        if (CUtil.StringToInt(CUtil.a(new r(CDadosCarregados.ay), "PUSH_NOTIFICATION_SAVE_LOST_MESSAGES", null)) == 1) {
          MyFcmListenerService.a(paramAnonymousContext, CDadosCarregados.a);
        }
        if (CDadosCarregados.b) {
          MyFcmListenerService.b(CMyFormDlg.this, CDadosCarregados.a);
        }
        CDadosCarregados.a = null;
      }
    };
    this.l = 0;
    this.n = 0;
    this.v = false;
    this.K = false;
    this.L = false;
    this.T[0] = 1.0D;
    this.T[1] = 1.0D;
    this.U[0] = 1.0D;
    this.U[1] = 1.0D;
    this.V = true;
    this.W = true;
    this.X = true;
    this.Y = true;
    this.Z = true;
    this.aa = true;
    this.ab = true;
    this.ac = true;
    this.ad = true;
    this.ae = true;
    this.af = true;
    this.ag = true;
    this.ah = true;
    this.ai = true;
    this.aj = true;
    this.ak = true;
    this.al = true;
    this.am = true;
    this.an = true;
    this.ao = true;
    this.ap = true;
    this.aq = true;
    this.ar = true;
    this.as = true;
    this.at = true;
    this.au = true;
    this.av = true;
    this.aw = true;
    this.ax = true;
    this.ay = true;
    this.az = true;
    this.aA = true;
    this.aB = true;
    this.aC = true;
    this.aD = true;
    this.aE = true;
    this.aF = true;
    this.aG = true;
    this.aH = true;
    this.aI = true;
    this.aJ = true;
    int i1 = 0;
    int i2;
    for (;;)
    {
      i2 = i3;
      if (i1 >= 100) {
        break;
      }
      this.aW[i1] = "";
      this.aX[i1] = true;
      i1 += 1;
    }
    while (i2 < 10)
    {
      this.aP[i2] = null;
      i2 += 1;
    }
  }
  
  static String a(b paramB)
  {
    return paramB.b;
  }
  
  static void aK(b paramB)
  {
    if (CDadosCarregados.bI == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2000", paramB.a);
      return;
    }
    int i1 = CDadosCarregados.bI.a();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bI.a, "1.2001", paramB.a);
      CDadosCarregados.bI = null;
      return;
    }
    CDadosCarregados.bI = null;
    CDadosCarregados.a(e(paramB));
  }
  
  static void aR(b paramB)
  {
    int i1 = CDadosCarregados.bA.d();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bA.a(), "1.1478", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  static void aY(b paramB)
  {
    int i1 = CDadosCarregados.bB.a();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bB.c, "1.2190", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  static void aa(b paramB)
  {
    if (CDadosCarregados.bx == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1928", paramB.a);
      return;
    }
    try
    {
      CDadosCarregados.bx.a();
      CDadosCarregados.bx = null;
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      CDadosCarregados.bx = null;
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error disconnecting from scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64625, localStringBuilder.toString(), "1.1929", paramB.a);
    }
  }
  
  static void ah(b paramB)
  {
    if (CDadosCarregados.by == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1895", paramB.a);
      return;
    }
    try
    {
      CDadosCarregados.by.b();
      CDadosCarregados.by = null;
      CDadosCarregados.bz = null;
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      CDadosCarregados.by = null;
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error disconnecting from scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64625, localStringBuilder.toString(), "1.1896", paramB.a);
    }
  }
  
  static void am(b paramB)
  {
    if (CDadosCarregados.bu == 1)
    {
      bB(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 2)
    {
      bI(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 4)
    {
      az(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 6)
    {
      aR(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 7)
    {
      bt(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 8)
    {
      ah(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 10)
    {
      bf(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 11)
    {
      aa(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 12)
    {
      aK(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 14)
    {
      ar(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 15)
    {
      aY(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    if (CDadosCarregados.bu == 16)
    {
      bm(paramB);
      CDadosCarregados.bu = 0;
      return;
    }
    CDadosCarregados.bu = 0;
    CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1453", paramB.a);
  }
  
  static void ar(b paramB)
  {
    if (CDadosCarregados.bM != null)
    {
      int i1 = CDadosCarregados.bM.a();
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bM.a, "1.2173", paramB.a);
        return;
      }
      CDadosCarregados.bM = null;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  static void az(b paramB)
  {
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1833", paramB.a);
      return;
    }
    int i1 = CDadosCarregados.bH.a();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bH.a, "1.1552", paramB.a);
      CDadosCarregados.bH = null;
      return;
    }
    CDadosCarregados.bH = null;
    CDadosCarregados.a(e(paramB));
  }
  
  static int b(b paramB)
  {
    return paramB.a;
  }
  
  public static CMyFormDlg b(String paramString)
  {
    CMyFormDlg localCMyFormDlg = CDadosCarregados.y;
    if (paramString.equals("X1"))
    {
      if (localCMyFormDlg.V) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X4"))
    {
      if (localCMyFormDlg.W) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X5"))
    {
      if (localCMyFormDlg.X) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X13"))
    {
      if (localCMyFormDlg.Y) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X14"))
    {
      if (localCMyFormDlg.Z) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X15"))
    {
      if (localCMyFormDlg.aa) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X18"))
    {
      if (localCMyFormDlg.ab) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X19"))
    {
      if (localCMyFormDlg.ac) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("X24"))
    {
      if (localCMyFormDlg.ad) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(1)"))
    {
      if (localCMyFormDlg.ae) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(2)"))
    {
      if (localCMyFormDlg.af) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(3)"))
    {
      if (localCMyFormDlg.ag) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(4)"))
    {
      if (localCMyFormDlg.ah) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(5)"))
    {
      if (localCMyFormDlg.ai) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(6)"))
    {
      if (localCMyFormDlg.aj) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(7)"))
    {
      if (localCMyFormDlg.ak) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(8)"))
    {
      if (localCMyFormDlg.al) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(9)"))
    {
      if (localCMyFormDlg.am) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(10)"))
    {
      if (localCMyFormDlg.an) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(11)"))
    {
      if (localCMyFormDlg.ao) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(12)"))
    {
      if (localCMyFormDlg.ap) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(13)"))
    {
      if (localCMyFormDlg.aq) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(14)"))
    {
      if (localCMyFormDlg.ar) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(15)"))
    {
      if (localCMyFormDlg.as) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("S_(16)"))
    {
      if (localCMyFormDlg.at) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("A_(1)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.au) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(2)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.av) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(3)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.aw) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(4)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.ax) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(5)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.ay) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(6)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.az) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(7)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.aA) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(8)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.aB) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(9)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.aC) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(10)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.aD) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("A_(11)")) {
      while (localCMyFormDlg != null)
      {
        if (localCMyFormDlg.aE) {
          return localCMyFormDlg;
        }
        localCMyFormDlg = localCMyFormDlg.e;
      }
    }
    if (paramString.equals("N_(1)"))
    {
      if (localCMyFormDlg.aF) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("N_(2)"))
    {
      if (localCMyFormDlg.aG) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("N_(3)"))
    {
      if (localCMyFormDlg.aH) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("N_(4)"))
    {
      if (localCMyFormDlg.aI) {
        return localCMyFormDlg;
      }
      return null;
    }
    if (paramString.equals("V_(2)"))
    {
      if (localCMyFormDlg.aJ) {
        return localCMyFormDlg;
      }
      return null;
    }
    return null;
  }
  
  static void bB(b paramB)
  {
    if (CDadosCarregados.bG != null)
    {
      int i1 = CDadosCarregados.bG.a();
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bG.b, "1.1405", paramB.a);
        CDadosCarregados.bG = null;
        return;
      }
      CDadosCarregados.bG = null;
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1406", paramB.a);
  }
  
  static void bI(b paramB)
  {
    int i1 = CDadosCarregados.bJ.a();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bJ.a, "1.1424", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  static void bf(b paramB)
  {
    int i1 = CDadosCarregados.bC.a();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bC.c, "1.1910", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  static void bm(b paramB)
  {
    int i1 = CDadosCarregados.bE.a();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bE.c, "1.2225", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  static void bt(b paramB)
  {
    if (CDadosCarregados.bF != null)
    {
      int i1 = CDadosCarregados.bF.a();
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bF.i, "1.1405", paramB.a);
        return;
      }
      CDadosCarregados.bF = null;
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1406", paramB.a);
  }
  
  static TempVars c(b paramB)
  {
    return paramB.c;
  }
  
  static ec e(b paramB)
  {
    if (paramB.c == null) {
      return null;
    }
    return paramB.c.errorState;
  }
  
  private void p()
  {
    this.aV = true;
    if ((this.F) && (CDadosCarregados.Z < 0))
    {
      int i2 = getWindow().findViewById(16908290).getTop();
      int i1;
      if ((this.G) && (!this.H)) {
        i1 = CDadosCarregados.aa;
      } else {
        i1 = 0;
      }
      CDadosCarregados.Z = i2 - i1;
    }
    if ((this.F) && (!this.D)) {
      this.I = getWindow().getDecorView().getHeight();
    }
    this.e = CDadosCarregados.y;
    CDadosCarregados.y = this;
    f();
    d(getResources().getConfiguration().orientation);
    setTitle(this.k);
    if (!l()) {
      return;
    }
    m();
    if (this.D)
    {
      setContentView(this.f.J);
    }
    else
    {
      if (CDadosCarregados.bt)
      {
        setTheme(2131558418);
        TextView localTextView = (TextView)getWindow().findViewById(16908310);
        localTextView.setPadding(10, 9, 10, 9);
        localTextView.setMinimumHeight(50);
        localTextView.setTextSize(16.0F);
      }
      setContentView(this.f.J, new AbsoluteLayout.LayoutParams(this.R[this.n], this.S[this.n], 0, 0));
    }
    k();
    setVisible(this.J);
    h();
    if (this.aZ) {
      getWindow().setSoftInputMode(35);
    } else {
      getWindow().setSoftInputMode(32);
    }
    if (CDadosCarregados.V) {
      a(null, 1, 0, this.f);
    }
    if ((CDadosCarregados.aj != 1) && (this.n != 0)) {
      a(getResources().getConfiguration().orientation);
    }
    this.v = true;
    if ((this.be) && ((CDadosCarregados.at) || (CDadosCarregados.as)))
    {
      CDadosCarregados.au = -1L;
      CUtil.a("You are running a demonstration project! This project will be closed in 10 minutes!", this);
      this.i = new Runnable()
      {
        public void run()
        {
          if (CDadosCarregados.y != null)
          {
            CUtil.a("The demo time has expired and the project was closed!", CDadosCarregados.m_androidMainActivity);
            Intent localIntent = new Intent();
            localIntent.putExtra("RESULT_COUNT", 0);
            CDadosCarregados.y.setResult(5, localIntent);
            CDadosCarregados.y.L = true;
            CDadosCarregados.y.c();
            CDadosCarregados.y.finish();
          }
        }
      };
      this.bh.postDelayed(this.i, 600000L);
      CDadosCarregados.au = Calendar.getInstance().getTimeInMillis();
    }
    this.aU = null;
    this.aT = 1;
    b();
    if ((this.W) || (this.Y) || (this.ar) || (this.be)) {
      new w(new Handler(), this).a();
    }
    if (this.H) {
      getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          if (CMyFormDlg.this.H) {
            CMyFormDlg.this.getWindow().getDecorView().setSystemUiVisibility(5894);
          }
        }
      });
    }
  }
  
  void A(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    Object localObject3 = new cj(0);
    r localR = new r(CDadosCarregados.ay);
    Object localObject2 = new ah();
    dw localDw1 = new dw("");
    dw localDw2 = new dw("");
    dw localDw3 = new dw("");
    Object localObject1 = new cj(0);
    if (!((ah)localObject2).a(d(paramB), "", str, true))
    {
      CDadosCarregados.a(e(paramB), ((ah)localObject2).y, ((ah)localObject2).z, "1.10", paramB.a);
      localR.a();
      return;
    }
    if (!a(((ah)localObject2).d, str, ((ah)localObject2).w, ((ah)localObject2).n, ((ah)localObject2).a, localDw2, localDw1, null, localDw3, (cj)localObject3, (cj)localObject1, paramB))
    {
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    if (((cj)localObject3).a == 0)
    {
      localObject1 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("There are no fields to insert on the table ");
      ((StringBuilder)localObject3).append(str);
      CDadosCarregados.a((ec)localObject1, 65233, ((StringBuilder)localObject3).toString(), "1.28", paramB.a);
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    localObject3 = ah.a(str);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Select Count(*) From ");
    localStringBuilder.append((String)localObject3);
    localStringBuilder.append("MFTBC, ");
    localStringBuilder.append((String)localObject3);
    localStringBuilder.append("MFTAB Where TABTAB = '");
    localStringBuilder.append(str);
    localStringBuilder.append("' And TBCIDO = TABIDO And TBCKEY = 1");
    if (!localR.a(localStringBuilder.toString()))
    {
      localObject1 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Error reading the fields from the table ");
      ((StringBuilder)localObject3).append(str);
      CDadosCarregados.a((ec)localObject1, 65128, ((StringBuilder)localObject3).toString(), "1.523", paramB.a);
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    localR.d();
    if (!localR.f())
    {
      localObject3 = localObject1;
      if (((cj)localObject3).a < CUtil.StringToInt(localR.b(0))) {
        ((cj)localObject3).a = 0;
      }
    }
    int i1 = 0;
    if (((cj)localObject1).a != 0)
    {
      localObject3 = localObject2;
      if (((ah)localObject3).a)
      {
        if (((ah)localObject3).b)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Select * From ");
          ((StringBuilder)localObject1).append(((ah)localObject3).d);
          ((StringBuilder)localObject1).append("\"");
          ((StringBuilder)localObject1).append(str);
          ((StringBuilder)localObject1).append("\"(nolock) Where ");
          ((StringBuilder)localObject1).append(localDw3.a);
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Select * From ");
          ((StringBuilder)localObject1).append(((ah)localObject3).d);
          ((StringBuilder)localObject1).append("\"");
          ((StringBuilder)localObject1).append(str);
          ((StringBuilder)localObject1).append("\" Where ");
          ((StringBuilder)localObject1).append(localDw3.a);
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
      else if (((ah)localObject3).b)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Select * From ");
        ((StringBuilder)localObject1).append(((ah)localObject3).d);
        ((StringBuilder)localObject1).append(str);
        ((StringBuilder)localObject1).append("(nolock) Where ");
        ((StringBuilder)localObject1).append(localDw3.a);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Select * From ");
        ((StringBuilder)localObject1).append(((ah)localObject3).d);
        ((StringBuilder)localObject1).append(str);
        ((StringBuilder)localObject1).append(" Where ");
        ((StringBuilder)localObject1).append(localDw3.a);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      if (!((ah)localObject3).b((String)localObject1))
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Error reading the records from the table ");
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject2).append(((ah)localObject3).z);
        CDadosCarregados.a((ec)localObject1, 65232, ((StringBuilder)localObject2).toString(), "1.29", paramB.a);
        localR.a();
        ((ah)localObject3).b();
        return;
      }
      ((ah)localObject3).f();
      if (((ah)localObject3).h())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw2.a);
        ((StringBuilder)localObject1).append(")");
        localDw2.a = ((StringBuilder)localObject1).toString();
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw1.a);
        ((StringBuilder)localObject1).append(")");
        localDw1.a = ((StringBuilder)localObject1).toString();
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw2.a);
        ((StringBuilder)localObject1).append(localDw1.a);
        if (!((ah)localObject3).b(((StringBuilder)localObject1).toString()))
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Error inserting a record on the table ");
          ((StringBuilder)localObject2).append(str);
          ((StringBuilder)localObject2).append("\n");
          ((StringBuilder)localObject2).append(((ah)localObject3).z);
          CDadosCarregados.a((ec)localObject1, 65231, ((StringBuilder)localObject2).toString(), "1.30", paramB.a);
          localR.a();
          ((ah)localObject3).b();
        }
      }
      else
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("It's not possible to insert a record in the table \"");
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append("\". It would create duplicate records on primary key!");
        CDadosCarregados.a((ec)localObject1, 65230, ((StringBuilder)localObject2).toString(), "1.31", paramB.a);
        localR.a();
        ((ah)localObject3).b();
      }
    }
    else
    {
      localObject1 = localObject2;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(localDw2.a);
      ((StringBuilder)localObject3).append(")");
      localDw2.a = ((StringBuilder)localObject3).toString();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(localDw1.a);
      ((StringBuilder)localObject3).append(")");
      localDw1.a = ((StringBuilder)localObject3).toString();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(localDw2.a);
      ((StringBuilder)localObject3).append(localDw1.a);
      if (!((ah)localObject1).b(((StringBuilder)localObject3).toString()))
      {
        localObject2 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error inserting a record on the table ");
        ((StringBuilder)localObject3).append(str);
        ((StringBuilder)localObject3).append("\n");
        ((StringBuilder)localObject3).append(((ah)localObject1).z);
        CDadosCarregados.a((ec)localObject2, 65231, ((StringBuilder)localObject3).toString(), "1.32", paramB.a);
        localR.a();
        ((ah)localObject1).b();
        return;
      }
    }
    ((ah)localObject2).b();
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        int i2 = this.bb[i1].F.c;
        if (!a(i1, paramB))
        {
          localR.a();
          return;
        }
        if (CUtil.a(this.bb[i1].F, i2)) {
          break;
        }
        CUtil.a(this.bb[i1].F);
        break;
      }
      i1 += 1;
    }
    CDadosCarregados.a(e(paramB));
    localR.a();
  }
  
  void B(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    Object localObject4 = new cj(0);
    r localR = new r(CDadosCarregados.ay);
    ah localAh = new ah();
    Object localObject1 = new dw("");
    Object localObject2 = new dw("");
    Object localObject3 = new cj(0);
    if (!localAh.a(d(paramB), "", str, true))
    {
      CDadosCarregados.a(e(paramB), localAh.y, localAh.z, "1.59", paramB.a);
      localR.a();
      return;
    }
    if (!a(localAh.d, str, localAh.w, localAh.n, localAh.a, null, null, (dw)localObject1, (dw)localObject2, (cj)localObject4, (cj)localObject3, paramB))
    {
      localR.a();
      localAh.b();
      return;
    }
    if (((cj)localObject4).a == 0)
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("There are no fields to update on the table ");
      ((StringBuilder)localObject2).append(str);
      CDadosCarregados.a((ec)localObject1, 65233, ((StringBuilder)localObject2).toString(), "1.77", paramB.a);
      localR.a();
      localAh.b();
      return;
    }
    localObject4 = ah.a(str);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Select Count(*) From ");
    localStringBuilder.append((String)localObject4);
    localStringBuilder.append("MFTBC, ");
    localStringBuilder.append((String)localObject4);
    localStringBuilder.append("MFTAB Where TABTAB = '");
    localStringBuilder.append(str);
    localStringBuilder.append("' And TBCIDO = TABIDO And TBCKEY = 1");
    if (!localR.a(localStringBuilder.toString()))
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error reading the fields from the table ");
      ((StringBuilder)localObject2).append(str);
      CDadosCarregados.a((ec)localObject1, 65128, ((StringBuilder)localObject2).toString(), "1.519", paramB.a);
      localR.a();
      localAh.b();
      return;
    }
    localR.d();
    if ((!localR.f()) && (((cj)localObject3).a < CUtil.StringToInt(localR.b(0))))
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Primary key for table \"");
      ((StringBuilder)localObject2).append(str);
      ((StringBuilder)localObject2).append("\" not specified!");
      CDadosCarregados.a((ec)localObject1, -7, ((StringBuilder)localObject2).toString(), "1.520", paramB.a);
      localR.a();
      localAh.b();
      return;
    }
    int i1 = 0;
    if (((cj)localObject3).a != 0)
    {
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(((dw)localObject1).a);
      ((StringBuilder)localObject3).append(" Where ");
      ((StringBuilder)localObject3).append(((dw)localObject2).a);
      if (!localAh.b(((StringBuilder)localObject3).toString()))
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Error updating a record from the table ");
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject2).append(localAh.z);
        CDadosCarregados.a((ec)localObject1, 65227, ((StringBuilder)localObject2).toString(), "1.78", paramB.a);
        localR.a();
        localAh.b();
        return;
      }
      localAh.b();
      while (i1 < this.ba)
      {
        if (this.bb[i1].a.equalsIgnoreCase(str))
        {
          int i2 = this.bb[i1].F.c;
          if (!a(i1, paramB))
          {
            localR.a();
            return;
          }
          if (CUtil.a(this.bb[i1].F, i2)) {
            break;
          }
          CUtil.a(this.bb[i1].F);
          break;
        }
        i1 += 1;
      }
      CDadosCarregados.a(e(paramB));
      localR.a();
      return;
    }
    localObject1 = e(paramB);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Not possible to update a record from the table \"");
    ((StringBuilder)localObject2).append(str);
    ((StringBuilder)localObject2).append("\". No key found or incomplete key!");
    CDadosCarregados.a((ec)localObject1, 65228, ((StringBuilder)localObject2).toString(), "1.79", paramB.a);
    localR.a();
    localAh.b();
  }
  
  void C(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    Object localObject3 = new cj(0);
    r localR = new r(CDadosCarregados.ay);
    ah localAh = new ah();
    Object localObject1 = new dw("");
    Object localObject2 = new cj(0);
    if (!localAh.a(d(paramB), "", str, true))
    {
      CDadosCarregados.a(e(paramB), localAh.y, localAh.z, "1.39", paramB.a);
      localR.a();
      return;
    }
    Object localObject4 = localAh.d;
    boolean bool1 = localAh.w;
    CDadosCarregados.a localA = localAh.n;
    boolean bool2 = localAh.a;
    int i1 = 0;
    if (!a((String)localObject4, str, bool1, localA, bool2, null, null, null, (dw)localObject1, (cj)localObject3, (cj)localObject2, paramB))
    {
      localR.a();
      localAh.b();
      return;
    }
    localObject3 = ah.a(str);
    localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append("Select Count(*) From ");
    ((StringBuilder)localObject4).append((String)localObject3);
    ((StringBuilder)localObject4).append("MFTBC, ");
    ((StringBuilder)localObject4).append((String)localObject3);
    ((StringBuilder)localObject4).append("MFTAB Where TABTAB = '");
    ((StringBuilder)localObject4).append(str);
    ((StringBuilder)localObject4).append("' And TBCIDO = TABIDO And TBCKEY = 1");
    if (!localR.a(((StringBuilder)localObject4).toString()))
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error reading the fields from the table ");
      ((StringBuilder)localObject2).append(str);
      CDadosCarregados.a((ec)localObject1, 65128, ((StringBuilder)localObject2).toString(), "1.521", paramB.a);
      localR.a();
      localAh.b();
      return;
    }
    localR.d();
    if ((!localR.f()) && (((cj)localObject2).a < CUtil.StringToInt(localR.b(0))))
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Primary key for table \"");
      ((StringBuilder)localObject2).append(str);
      ((StringBuilder)localObject2).append("\" not specified!");
      CDadosCarregados.a((ec)localObject1, -7, ((StringBuilder)localObject2).toString(), "1.522", paramB.a);
      localR.a();
      localAh.b();
      return;
    }
    if (((cj)localObject2).a != 0)
    {
      if (localAh.a)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Delete From ");
        ((StringBuilder)localObject2).append(localAh.d);
        ((StringBuilder)localObject2).append("\"");
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append("\" Where ");
        ((StringBuilder)localObject2).append(((dw)localObject1).a);
        localObject1 = ((StringBuilder)localObject2).toString();
      }
      else
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Delete From ");
        ((StringBuilder)localObject2).append(localAh.d);
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append(" Where ");
        ((StringBuilder)localObject2).append(((dw)localObject1).a);
        localObject1 = ((StringBuilder)localObject2).toString();
      }
      if (!localAh.b((String)localObject1))
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Error deleting a record from the table ");
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject2).append(localAh.z);
        CDadosCarregados.a((ec)localObject1, 65229, ((StringBuilder)localObject2).toString(), "1.53", paramB.a);
        localR.a();
        localAh.b();
        return;
      }
      localAh.b();
      while (i1 < this.ba)
      {
        if (this.bb[i1].a.equalsIgnoreCase(str))
        {
          int i2 = this.bb[i1].F.c;
          if (!a(i1, paramB))
          {
            localR.a();
            return;
          }
          if (CUtil.a(this.bb[i1].F, i2)) {
            break;
          }
          CUtil.a(this.bb[i1].F);
          break;
        }
        i1 += 1;
      }
      CDadosCarregados.a(e(paramB));
      localR.a();
      return;
    }
    localObject1 = e(paramB);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Not possible to delete a record from table \"");
    ((StringBuilder)localObject2).append(str);
    ((StringBuilder)localObject2).append("\". Key not found or incomplete!");
    CDadosCarregados.a((ec)localObject1, 65228, ((StringBuilder)localObject2).toString(), "1.54", paramB.a);
    localR.a();
    localAh.b();
  }
  
  String CalculaExpressaoNum(String paramString, TempVars paramTempVars)
  {
    return this.a.a(paramString, paramTempVars, 1, 2, null, 0, null, 0);
  }
  
  String CalculaExpressaoStr(String paramString, TempVars paramTempVars)
  {
    return this.a.a(paramString, paramTempVars, 1, 1, null, true, false, "", null, 0, null, 0);
  }
  
  void D(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        i2 = i1;
        break label56;
      }
      i1 += 1;
    }
    int i2 = -1;
    label56:
    if (i2 == -1) {
      return;
    }
    if ((!this.bb[i1].E) && (!a(i1, paramB))) {
      return;
    }
    if (!CUtil.b(this.bb[i1].F))
    {
      CUtil.d(this.bb[i1].F);
      CDadosCarregados.a(e(paramB), 65235, "Record not found or reached end of records!", "1.3", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void E(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        i2 = i1;
        break label56;
      }
      i1 += 1;
    }
    int i2 = -1;
    label56:
    if (i2 == -1)
    {
      CDadosCarregados.a(e(paramB));
      return;
    }
    if ((!this.bb[i1].E) && (!a(i1, paramB))) {
      return;
    }
    if (!CUtil.c(this.bb[i1].F))
    {
      CUtil.a(this.bb[i1].F);
      CDadosCarregados.a(e(paramB), 65235, "Record not found or was already on first record!", "1.5", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void F(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        i2 = i1;
        break label56;
      }
      i1 += 1;
    }
    int i2 = -1;
    label56:
    if (i2 == -1)
    {
      CDadosCarregados.a(e(paramB));
      return;
    }
    if ((!this.bb[i1].E) && (!a(i1, paramB))) {
      return;
    }
    if (!CUtil.a(this.bb[i1].F))
    {
      CDadosCarregados.a(e(paramB), 65235, "Record not found!", "1.7", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void G(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        i2 = i1;
        break label56;
      }
      i1 += 1;
    }
    int i2 = -1;
    label56:
    if (i2 == -1)
    {
      CDadosCarregados.a(e(paramB));
      return;
    }
    if ((!this.bb[i1].E) && (!a(i1, paramB))) {
      return;
    }
    if (!CUtil.d(this.bb[i1].F))
    {
      CDadosCarregados.a(e(paramB), 65235, "Record not found!", "1.9", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  ViewGroup GetContainerViewGroup()
  {
    if (this.f == null) {
      return null;
    }
    return this.f.J;
  }
  
  View GetHCTRLView(String paramString)
  {
    if (this.f == null) {
      return null;
    }
    paramString = CUtil.a("HCTRL(", paramString, ")");
    paramString = this.f.b(CUtil.StringToInt(paramString));
    if (paramString == null) {
      return null;
    }
    return paramString.b();
  }
  
  void H(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = CUtil.a("TBL(", ((CMyToken)localObject1).GetNextToken(), ")");
    Object localObject2 = ((CMyToken)localObject1).GetNextToken();
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str1))
      {
        i2 = i1;
        break label80;
      }
      i1 += 1;
    }
    int i2 = -1;
    label80:
    if (i2 == -1)
    {
      CDadosCarregados.a(e(paramB));
      return;
    }
    if ((!this.bb[i2].E) && (!a(i1, paramB))) {
      return;
    }
    boolean bool = ((String)localObject2).equals("");
    int i4 = 1;
    int i3;
    if (bool)
    {
      i1 = 0;
      i3 = i4;
    }
    else
    {
      int i5 = CUtil.a((String)localObject2, '\004') + 1;
      localObject1 = new int[i5];
      String[] arrayOfString = new String[i5];
      boolean[] arrayOfBoolean = new boolean[i5];
      localObject2 = new CMyToken((String)localObject2, c);
      i1 = 0;
      while (((CMyToken)localObject2).HasTokens())
      {
        Object localObject3 = ((CMyToken)localObject2).GetNextToken();
        if (((String)localObject3).equals("")) {
          break;
        }
        localObject3 = new CMyToken((String)localObject3, d);
        String str2 = ((CMyToken)localObject3).GetNextToken();
        arrayOfBoolean[i1] = ((CMyToken)localObject3).GetNextToken().equals("1");
        if (arrayOfBoolean[i1] != 0) {
          arrayOfString[i1] = CalculaExpressaoStr(((CMyToken)localObject3).GetNextToken(), c(paramB));
        } else {
          arrayOfString[i1] = CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB));
        }
        localObject1[i1] = a(str1, str2);
        i1 += 1;
      }
      int i6 = CUtil.e(this.bb[i2].F);
      i1 = 0;
      while (i1 < i6)
      {
        i3 = 0;
        while (i3 < i5)
        {
          if (arrayOfBoolean[i3] != 0)
          {
            if (arrayOfString[i3].equals(CUtil.a(this.bb[i2].F, localObject1[i3], i1))) {}
          }
          else {
            while (CUtil.StringToDouble(arrayOfString[i3]) != CUtil.StringToDouble(CUtil.a(this.bb[i2].F, localObject1[i3], i1)))
            {
              i3 = 0;
              break;
            }
          }
          i3 += 1;
        }
        i3 = 1;
        if (i3 != 0)
        {
          i3 = i4;
          break label460;
        }
        i1 += 1;
      }
      i3 = 0;
    }
    label460:
    if (i3 == 0)
    {
      CDadosCarregados.a(e(paramB), 65235, "Record not found!", "1.1493", paramB.a);
      return;
    }
    if (!CUtil.a(this.bb[i2].F, i1))
    {
      CDadosCarregados.a(e(paramB), 65235, "Record not found!", "1.1494", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void I(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        int i2 = this.bb[i1].F.c;
        if (!a(i1, paramB)) {
          return;
        }
        if (CUtil.a(this.bb[i1].F, i2)) {
          break;
        }
        CUtil.a(this.bb[i1].F);
        break;
      }
      i1 += 1;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void J(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    String str1 = localCMyToken.GetNextToken();
    String str2 = CUtil.a("TBL(", localCMyToken.GetNextToken(), ")");
    CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    String str3 = localCMyToken.GetNextToken();
    CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    paramB = localCMyToken.GetNextToken();
    int i1 = 0;
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str2))
      {
        this.bb[i1].c = str1;
        if (str1.equals("2.4"))
        {
          this.bb[i1].d = str3;
          this.bb[i1].e = paramB;
          this.bb[i1].f = "";
          this.bb[i1].g = "";
          return;
        }
        this.bb[i1].d = str3;
        this.bb[i1].e = paramB;
        this.bb[i1].f = "";
        this.bb[i1].g = "";
        return;
      }
      i1 += 1;
    }
  }
  
  boolean K(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str = CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")");
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i1 = i2;
    if (i2 != 1)
    {
      i1 = i2;
      if (i2 != 2)
      {
        i1 = i2;
        if (i2 != 3)
        {
          i1 = i2;
          if (i2 != 4)
          {
            i1 = i2;
            if (i2 != 5) {
              if ((!CUtil.d((String)localObject2, 3).equalsIgnoreCase("JPG")) && (!CUtil.d((String)localObject2, 4).equalsIgnoreCase("JPEG")))
              {
                if (CUtil.d((String)localObject2, 3).equalsIgnoreCase("GIF")) {
                  i1 = 3;
                } else if (CUtil.d((String)localObject2, 3).equalsIgnoreCase("PCX")) {
                  i1 = 4;
                } else if (CUtil.d((String)localObject2, 3).equalsIgnoreCase("PNG")) {
                  i1 = 5;
                } else {
                  i1 = 1;
                }
              }
              else {
                i1 = 2;
              }
            }
          }
        }
      }
    }
    int i3 = CUtil.StringToInt(str);
    i2 = 0;
    while (i2 < this.f.z)
    {
      if (this.f.y[i2].f == i3)
      {
        localObject1 = new ec();
        if (!a((String)localObject2, i1, this.f.y[i2], (ec)localObject1))
        {
          localObject2 = e(paramB);
          i1 = ((ec)localObject1).a;
          str = ((ec)localObject1).b;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("1.2024;");
          localStringBuilder.append(((ec)localObject1).c);
          CDadosCarregados.a((ec)localObject2, i1, str, localStringBuilder.toString(), paramB.a);
          return false;
        }
        CDadosCarregados.a(e(paramB));
        return true;
      }
      i2 += 1;
    }
    CDadosCarregados.a(e(paramB), 65226, "Error saving draw!\r\nControl not found", "1.2029", paramB.a);
    return false;
  }
  
  boolean L(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    Object localObject3 = CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")");
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i1 = i2;
    if (i2 != 1)
    {
      i1 = i2;
      if (i2 != 2)
      {
        i1 = i2;
        if (i2 != 3)
        {
          i1 = i2;
          if (i2 != 4)
          {
            i1 = i2;
            if (i2 != 5) {
              if ((!CUtil.d((String)localObject2, 3).equalsIgnoreCase("JPG")) && (!CUtil.d((String)localObject2, 4).equalsIgnoreCase("JPEG")))
              {
                if (CUtil.d((String)localObject2, 3).equalsIgnoreCase("GIF")) {
                  i1 = 3;
                } else if (CUtil.d((String)localObject2, 3).equalsIgnoreCase("PCX")) {
                  i1 = 4;
                } else if (CUtil.d((String)localObject2, 3).equalsIgnoreCase("PNG")) {
                  i1 = 5;
                } else {
                  i1 = 1;
                }
              }
              else {
                i1 = 2;
              }
            }
          }
        }
      }
    }
    i2 = CUtil.StringToInt((String)localObject3);
    localObject3 = this.f.a(i2);
    if (localObject3 != null)
    {
      localObject1 = new ec();
      if (!a((String)localObject2, i1, (bi)localObject3, (ec)localObject1))
      {
        localObject2 = e(paramB);
        i1 = ((ec)localObject1).a;
        localObject3 = ((ec)localObject1).b;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("1.136;");
        localStringBuilder.append(((ec)localObject1).c);
        CDadosCarregados.a((ec)localObject2, i1, (String)localObject3, localStringBuilder.toString(), paramB.a);
        return false;
      }
      CDadosCarregados.a(e(paramB));
      return true;
    }
    CDadosCarregados.a(e(paramB), 65226, "Error saving draw!\r\nControl not found", "1.137", paramB.a);
    return false;
  }
  
  boolean M(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")"));
    final int i2 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i3 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final int i4 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i5 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    paramB = this.f.a(i1);
    if (paramB != null)
    {
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          paramB.a(i2, i3, i4, i5);
        }
      });
      return true;
    }
    return false;
  }
  
  void N(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    Object localObject = CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")");
    final int i1 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i2 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final int i3 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i4 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final String str1 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    final String str2 = CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB));
    final boolean bool;
    if (CUtil.StringToLong(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))) == 1L) {
      bool = true;
    } else {
      bool = false;
    }
    paramB = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    int i5 = CUtil.StringToInt((String)localObject);
    localObject = this.f.a(i5);
    if (localObject != null) {
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          this.a.a(i1, i2, i3, i4, CUtil.k(str1), CUtil.StringToInt(str2), bool, CUtil.k(paramB));
        }
      });
    }
  }
  
  void O(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    Object localObject = CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")");
    final int i1 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i2 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final int i3 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i4 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final String str1 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    final String str2 = CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB));
    final boolean bool;
    if (CUtil.StringToLong(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))) == 1L) {
      bool = true;
    } else {
      bool = false;
    }
    paramB = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    int i5 = CUtil.StringToInt((String)localObject);
    localObject = this.f.a(i5);
    if (localObject != null) {
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          this.a.b(i1, i2, i3, i4, CUtil.k(str1), CUtil.StringToInt(str2), bool, CUtil.k(paramB));
        }
      });
    }
  }
  
  void P(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    String str2 = CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")");
    final int i1 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i2 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final int i3 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i4 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final String str1 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    final int i5 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    final int i6 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    int i7 = CUtil.StringToInt(str2);
    paramB = this.f.a(i7);
    if (paramB != null) {
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          paramB.a(i1, i2, i3, i4, CUtil.k(str1), i5, i6);
        }
      });
    }
  }
  
  void Q(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    Object localObject = CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")");
    final int i1 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i2 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    final int i3 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 1);
    final int i4 = a(CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), 2);
    paramB = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    int i5 = CUtil.StringToInt((String)localObject);
    localObject = this.f.a(i5);
    if (localObject != null) {
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          this.a.b(paramB, i1, i2, i3, i4);
        }
      });
    }
  }
  
  void R(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    String str2 = CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")");
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    Object localObject1 = "";
    Object localObject2 = "";
    String str1 = "";
    int i1 = 1;
    int i2 = 1;
    int i3;
    if (i4 == 2)
    {
      localObject2 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
      localObject3 = CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB));
      i3 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
      i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
      str1 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
      paramB = (b)localObject1;
      localObject1 = localObject3;
    }
    else
    {
      if (i4 == 3)
      {
        i1 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
        paramB = (b)localObject1;
      }
      for (;;)
      {
        localObject3 = "";
        i3 = 0;
        i2 = 0;
        localObject1 = localObject2;
        localObject2 = localObject3;
        break;
        paramB = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
        i1 = i2;
      }
    }
    int i5 = CUtil.StringToInt(str2);
    Object localObject3 = this.f.a(i5);
    if (localObject3 != null)
    {
      if (i4 == 2)
      {
        ((bi)localObject3).a((String)localObject2, CUtil.StringToInt((String)localObject1), i3, i2, CUtil.k(str1));
        return;
      }
      if (i4 == 3)
      {
        ((bi)localObject3).i(i1);
        return;
      }
      ((bi)localObject3).h(CUtil.k(paramB));
    }
  }
  
  void S(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    Object localObject2 = CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")");
    final int i1 = a(CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB))), 1);
    final int i2 = a(CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB))), 2);
    final int i3 = a(CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB))), 1);
    final int i4 = a(CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB))), 2);
    final String str1 = CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    final int i5 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    final String str2 = CUtil.Q(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    final int i6 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    localObject1 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i7 = CUtil.StringToInt((String)localObject2);
    localObject2 = this.f.a(i7);
    if (localObject2 != null)
    {
      final dw localDw = new dw("");
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          localDw.a = this.b.a(i1, i2, i3, i4, CUtil.StringToInt(str1), str2, i5, i6, CUtil.k(this.k));
        }
      });
      if (!localDw.a.equals(""))
      {
        CDadosCarregados.a(e(paramB), -14, localDw.a, "1.601", paramB.a);
        return;
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  boolean SetTarget(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, TempVars paramTempVars)
  {
    if (paramString1.equals("")) {
      return false;
    }
    String str = paramString2;
    if (paramString2 == null) {
      str = "";
    }
    paramString2 = str;
    if (!paramBoolean2) {
      if (str.equalsIgnoreCase("true"))
      {
        paramString2 = "1";
      }
      else if (str.equalsIgnoreCase("false"))
      {
        paramString2 = "0";
      }
      else if (str.equalsIgnoreCase("yes"))
      {
        paramString2 = "1";
      }
      else
      {
        paramString2 = str;
        if (str.equalsIgnoreCase("no")) {
          paramString2 = "0";
        }
      }
    }
    int i1;
    if (CUtil.c(paramString1, "TVAR"))
    {
      if (paramTempVars != null)
      {
        i1 = CUtil.StringToInt(CUtil.a(paramString1, 5, paramString1.length() - 6));
        if ((i1 >= 0) && (i1 < 100))
        {
          if (paramTempVars != null) {}
          for (paramString1 = paramTempVars.subProjectPrefix;; paramString1 = this.g) {
            break;
          }
          CDadosCarregados.a(null, paramTempVars, i1, paramString2, paramBoolean1, paramBoolean2, paramString1);
          return true;
        }
      }
    }
    else if (CUtil.c(paramString1, "CTRL"))
    {
      i1 = CUtil.StringToInt(CUtil.a(paramString1, 5, paramString1.length() - 6));
      paramString1 = this.f.b(i1);
      if (paramString1 != null)
      {
        paramString1.d(paramString2);
        return true;
      }
    }
    else if (CUtil.c(paramString1, "VAR"))
    {
      i1 = CUtil.StringToInt(CUtil.a(paramString1, 4, paramString1.length() - 5));
      if ((i1 >= 0) && (i1 < 1000))
      {
        if (paramTempVars != null) {}
        for (paramString1 = paramTempVars.subProjectPrefix;; paramString1 = this.g) {
          break;
        }
        CDadosCarregados.a(null, null, i1, paramString2, paramBoolean1, paramBoolean2, paramString1);
        return true;
      }
    }
    else if (CUtil.c(paramString1, "LVAR"))
    {
      i1 = CUtil.StringToInt(CUtil.a(paramString1, 5, paramString1.length() - 6));
      if ((i1 >= 0) && (i1 < 100))
      {
        if (paramTempVars != null) {}
        for (paramString1 = paramTempVars.subProjectPrefix;; paramString1 = this.g) {
          break;
        }
        CDadosCarregados.a(this, null, i1, paramString2, paramBoolean1, paramBoolean2, paramString1);
        return true;
      }
    }
    else if (CUtil.c(paramString1, "DYN_CTRL"))
    {
      paramString1 = CalculaExpressaoStr(CUtil.a(paramString1, 9, paramString1.length() - 10), paramTempVars);
      i1 = this.f.d(paramString1);
      if (i1 < 0) {
        return false;
      }
      paramString1 = this.f.b(i1);
      if (paramString1 != null)
      {
        paramString1.d(paramString2);
        return true;
      }
    }
    else if (CUtil.c(paramString1, "DYN_VAR"))
    {
      str = CalculaExpressaoStr(CUtil.a(paramString1, 8, paramString1.length() - 9), paramTempVars);
      if (paramTempVars != null) {
        paramString1 = paramTempVars.subProjectPrefix;
      } else {
        paramString1 = this.g;
      }
      i1 = a(str, false, paramString1);
      if ((i1 >= 0) && (i1 < 1000))
      {
        if (paramTempVars != null) {}
        for (paramString1 = paramTempVars.subProjectPrefix;; paramString1 = this.g) {
          break;
        }
        CDadosCarregados.a(null, null, i1, paramString2, paramBoolean1, paramBoolean2, paramString1);
        return true;
      }
    }
    else if (CUtil.c(paramString1, "DYN_LVAR"))
    {
      str = CalculaExpressaoStr(CUtil.a(paramString1, 9, paramString1.length() - 10), paramTempVars);
      if (paramTempVars != null) {
        paramString1 = paramTempVars.subProjectPrefix;
      } else {
        paramString1 = this.g;
      }
      i1 = a(str, true, paramString1);
      if ((i1 >= 0) && (i1 < 100))
      {
        if (paramTempVars != null) {}
        for (paramString1 = paramTempVars.subProjectPrefix;; paramString1 = this.g) {
          break;
        }
        CDadosCarregados.a(this, null, i1, paramString2, paramBoolean1, paramBoolean2, paramString1);
        return true;
      }
    }
    return false;
  }
  
  void T(b paramB)
  {
    Object localObject2 = new CMyToken(a(paramB), b);
    Object localObject1 = CUtil.a("CTRL(", ((CMyToken)localObject2).GetNextToken(), ")");
    localObject2 = ((CMyToken)localObject2).GetNextToken();
    int i1 = CUtil.StringToInt((String)localObject1);
    localObject1 = this.f.a(i1);
    if (localObject1 != null)
    {
      if (((bi)localObject1).aa.g) {}
      for (localObject1 = "1";; localObject1 = "0") {
        break;
      }
      SetTarget((String)localObject2, (String)localObject1, false, false, c(paramB));
    }
  }
  
  void U(b arg1)
  {
    Object localObject1 = new CMyToken(a(???), b);
    int i5 = CUtil.StringToInt(((CMyToken)localObject1).GetNextToken());
    Object localObject4 = ((CMyToken)localObject1).GetNextToken();
    String str3 = ((CMyToken)localObject1).GetNextToken();
    String str4 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    int i6 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    int i7 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    String str1 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(???));
    String str5 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(???));
    int i8 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(???)));
    Object localObject3;
    if (CUtil.b(str2, 4).equalsIgnoreCase("CTRL"))
    {
      localObject1 = j(CUtil.StringToInt(CUtil.a(str2, 5, str2.length() - 6)));
      if (localObject1 != null)
      {
        if (((ag)localObject1).v != 2) {
          break label471;
        }
        localObject3 = (au)localObject1;
        localObject1 = ((au)localObject3).d();
        if (!((au)localObject3).aj) {}
      }
      else
      {
        break label471;
      }
    }
    for (;;)
    {
      break;
      int i9;
      if (CUtil.b(str2, 3).equalsIgnoreCase("VAR"))
      {
        i9 = CUtil.StringToInt(CUtil.a(str2, 4, str2.length() - 5));
        if ((i9 >= 0) && (i9 < 1000)) {
          localObject1 = c(???).variaveisProject[i9];
        }
      }
      else if (CUtil.b(str2, 4).equalsIgnoreCase("LVAR"))
      {
        i9 = CUtil.StringToInt(CUtil.a(str2, 5, str2.length() - 6));
        if ((i9 >= 0) && (i9 < 100)) {
          localObject1 = this.aW[i9];
        }
      }
      else if (CUtil.b(str2, 4).equalsIgnoreCase("TVAR"))
      {
        i9 = CUtil.StringToInt(CUtil.a(str2, 5, str2.length() - 6));
        if ((i9 >= 0) && (i9 < 100))
        {
          localObject1 = ???.c.m_variaveis[i9];
          continue;
        }
      }
      label471:
      localObject1 = "";
    }
    if (i5 == 2) {
      localObject3 = new Intent(this, CTeclNum.class);
    } else if ((i5 != 3) && (i5 != 4) && (i5 != 5)) {
      localObject3 = new Intent(this, CTeclAlNum.class);
    } else {
      localObject3 = new Intent(this, CTeclDateTime.class);
    }
    ((Intent)localObject3).putExtra("m_kbTranslationOk", CalculaExpressaoStr(CDadosCarregados.cG, c(???)));
    ((Intent)localObject3).putExtra("m_kbTranslationCancel", CalculaExpressaoStr(CDadosCarregados.cH, c(???)));
    ((Intent)localObject3).putExtra("m_kbTranslationSpace", CalculaExpressaoStr(CDadosCarregados.cI, c(???)));
    ((Intent)localObject3).putExtra("m_kbTranslationCapsLock", CalculaExpressaoStr(CDadosCarregados.cJ, c(???)));
    ((Intent)localObject3).putExtra("m_kbTranslationShift", CalculaExpressaoStr(CDadosCarregados.cK, c(???)));
    ((Intent)localObject3).putExtra("m_kbTranslationClear", CalculaExpressaoStr(CDadosCarregados.cL, c(???)));
    ((Intent)localObject3).putExtra("m_kbTranslationEnter", CalculaExpressaoStr(CDadosCarregados.cM, c(???)));
    ((Intent)localObject3).putExtra("m_title", str5);
    ((Intent)localObject3).putExtra("m_type", i5);
    ((Intent)localObject3).putExtra("subProjectPrefix", d(???));
    this.w = false;
    this.y = c(???);
    this.z = e(???);
    boolean bool2 = true;
    boolean bool4 = true;
    boolean bool3 = true;
    boolean bool1;
    double d1;
    if (i5 == 2)
    {
      if (i6 == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ((Intent)localObject3).putExtra("m_password", bool1);
      if (str4.equals(""))
      {
        ((Intent)localObject3).putExtra("m_inteiro", false);
        ((Intent)localObject3).putExtra("m_maxDecimais", 6);
      }
      else
      {
        i5 = CUtil.StringToInt(CalculaExpressaoNum(str4, c(???)));
        if (i5 == 0) {
          bool1 = true;
        } else {
          bool1 = false;
        }
        ((Intent)localObject3).putExtra("m_inteiro", bool1);
        ((Intent)localObject3).putExtra("m_maxDecimais", i5);
      }
      ((Intent)localObject3).putExtra("m_valor", (String)localObject1);
      d1 = i2;
      Double.isNaN(d1);
      ((Intent)localObject3).putExtra("m_xRatio", d1 / 100.0D);
      d1 = i3;
      Double.isNaN(d1);
      ((Intent)localObject3).putExtra("m_yRatio", d1 / 100.0D);
      if (i4 == 1) {
        bool1 = bool3;
      } else {
        bool1 = false;
      }
      ((Intent)localObject3).putExtra("m_allowNegative", bool1);
      if (!((String)localObject4).equals("")) {
        ((Intent)localObject3).putExtra("m_minVal", CalculaExpressaoNum((String)localObject4, c(???)));
      } else {
        ((Intent)localObject3).putExtra("m_minVal", "");
      }
      if (!str3.equals("")) {
        ((Intent)localObject3).putExtra("m_maxVal", CalculaExpressaoNum(str3, c(???)));
      } else {
        ((Intent)localObject3).putExtra("m_maxVal", "");
      }
      ((Intent)localObject3).putExtra("destination", str2);
      ((Intent)localObject3).putExtra("setFocus", i1);
      ((Intent)localObject3).putExtra("actionID", ???.a);
      startActivityForResult((Intent)localObject3, 6);
    }
    else if ((i5 != 3) && (i5 != 4) && (i5 != 5))
    {
      if (i6 == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ((Intent)localObject3).putExtra("m_password", bool1);
      if (i8 == 1) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      ((Intent)localObject3).putExtra("m_multiLine", bool1);
      if (str3.equals("")) {
        ((Intent)localObject3).putExtra("m_maxCar", 10000);
      } else {
        ((Intent)localObject3).putExtra("m_maxCar", CUtil.StringToInt(CalculaExpressaoNum(str3, c(???))));
      }
      ((Intent)localObject3).putExtra("m_texto", (String)localObject1);
      if ((i7 != 1) && (i7 != 4)) {
        bool1 = false;
      } else {
        bool1 = true;
      }
      ((Intent)localObject3).putExtra("m_checkCaps", bool1);
      bool1 = bool2;
      if (i7 != 3) {
        if (i7 == 4) {
          bool1 = bool2;
        } else {
          bool1 = false;
        }
      }
      ((Intent)localObject3).putExtra("m_checkShift", bool1);
      d1 = i2;
      Double.isNaN(d1);
      ((Intent)localObject3).putExtra("m_xRatio", d1 / 100.0D);
      d1 = i3;
      Double.isNaN(d1);
      ((Intent)localObject3).putExtra("m_yRatio", d1 / 100.0D);
      ((Intent)localObject3).putExtra("destination", str2);
      ((Intent)localObject3).putExtra("setFocus", i1);
      ((Intent)localObject3).putExtra("actionID", ???.a);
      startActivityForResult((Intent)localObject3, 5);
    }
    else
    {
      d1 = i2;
      Double.isNaN(d1);
      ((Intent)localObject3).putExtra("m_xRatio", d1 / 100.0D);
      d1 = i3;
      Double.isNaN(d1);
      ((Intent)localObject3).putExtra("m_yRatio", d1 / 100.0D);
      if ((i5 != 3) && (i5 != 5)) {
        bool1 = false;
      } else {
        bool1 = true;
      }
      ((Intent)localObject3).putExtra("m_useDate", bool1);
      bool1 = bool4;
      if (i5 != 4) {
        if (i5 == 5) {
          bool1 = bool4;
        } else {
          bool1 = false;
        }
      }
      ((Intent)localObject3).putExtra("m_useTime", bool1);
      if (i5 == 3)
      {
        ((Intent)localObject3).putExtra("m_dateVal", CUtil.k((String)localObject1, str1));
      }
      else
      {
        localObject4 = str1;
        if (i5 == 4)
        {
          ((Intent)localObject3).putExtra("m_timeVal", CUtil.m((String)localObject1, (String)localObject4));
        }
        else
        {
          ((Intent)localObject3).putExtra("m_dateVal", CUtil.b(CUtil.o((String)localObject1, (String)localObject4), 8));
          ((Intent)localObject3).putExtra("m_timeVal", CUtil.a(CUtil.o((String)localObject1, (String)localObject4), 8, 6));
        }
      }
      ((Intent)localObject3).putExtra("m_mask", str1);
      ((Intent)localObject3).putExtra("destination", str2);
      ((Intent)localObject3).putExtra("setFocus", i1);
      ((Intent)localObject3).putExtra("actionID", ???.a);
      startActivityForResult((Intent)localObject3, 7);
    }
    while (!this.w)
    {
      try
      {
        synchronized (this.x)
        {
          this.x.wait(1000L);
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
      continue;
      throw localObject2;
    }
    this.y = null;
    this.z = null;
  }
  
  void V(b paramB)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  void W(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    Object localObject6 = CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    Object localObject3 = CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    Object localObject4 = CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    Object localObject2 = CUtil.Q(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject5 = CUtil.Q(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    localObject1 = ((CMyToken)localObject1).GetNextToken();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(CUtil.a((String)localObject2, false));
    localStringBuilder.append((String)localObject5);
    localObject2 = localStringBuilder.toString();
    localObject5 = new File((String)localObject2);
    if (((File)localObject5).exists())
    {
      CDadosCarregados.a(e(paramB), -1, "Target file already exists!", "1.1753", paramB.a);
      return;
    }
    if (!c("android.permission.CAMERA"))
    {
      CDadosCarregados.a(e(paramB), -20, "User did not give permission to access the camera!", "1.2255", paramB.a);
      return;
    }
    CDadosCarregados.p.set(0);
    int i1;
    if ((CUtil.StringToInt((String)localObject6) != 2) && (CUtil.StringToInt((String)localObject6) != 3))
    {
      if (!CUtil.a(this, "android.media.action.IMAGE_CAPTURE"))
      {
        CDadosCarregados.a(e(paramB), -1, "No application found for taking photos!", "1.1583", paramB.a);
        return;
      }
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(getCacheDir().getAbsolutePath());
      ((StringBuilder)localObject3).append("/Ktmp_imageK");
      localObject3 = new File(((StringBuilder)localObject3).toString());
      ((File)localObject3).delete();
      bool = CDadosCarregados.bA.c();
      if (bool) {
        CDadosCarregados.bA.d();
      }
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
          Object localObject1 = CMyFormDlg.this;
          Object localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(CMyFormDlg.this.getPackageName());
          ((StringBuilder)localObject2).append(".fileprovider");
          localObject1 = FileProvider.a((Context)localObject1, ((StringBuilder)localObject2).toString(), this.a);
          localIntent.putExtra("output", (Parcelable)localObject1);
          localIntent.addFlags(1);
          localIntent.addFlags(2);
          try
          {
            localObject2 = CMyFormDlg.this.getPackageManager().queryIntentActivities(localIntent, 65536).iterator();
            while (((Iterator)localObject2).hasNext())
            {
              String str = ((ResolveInfo)((Iterator)localObject2).next()).activityInfo.packageName;
              CMyFormDlg.this.grantUriPermission(str, (Uri)localObject1, 3);
            }
          }
          catch (Exception localException)
          {
            for (;;) {}
          }
          CMyFormDlg.this.startActivityForResult(localIntent, 8);
        }
      });
      while (CDadosCarregados.p.get() == 0) {
        CUtil.e(250);
      }
      if (bool) {
        CDadosCarregados.bA.a(this);
      }
      if (CDadosCarregados.p.get() == -1)
      {
        CDadosCarregados.a(e(paramB), -1, "User canceled photo!", "1.728", paramB.a);
        return;
      }
      try
      {
        localObject4 = new FileInputStream((File)localObject3);
        localObject5 = new FileOutputStream((File)localObject5);
        localObject6 = new byte['Ѐ'];
        for (;;)
        {
          i1 = ((FileInputStream)localObject4).read((byte[])localObject6);
          if (i1 <= 0) {
            break;
          }
          ((FileOutputStream)localObject5).write((byte[])localObject6, 0, i1);
        }
        ((FileInputStream)localObject4).close();
        ((FileOutputStream)localObject5).close();
        ((File)localObject3).delete();
        SetTarget((String)localObject1, (String)localObject2, true, true, c(paramB));
        CDadosCarregados.a(e(paramB));
        return;
      }
      catch (Exception localException1)
      {
        localObject2 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error copying image to final destination!\r\n");
        ((StringBuilder)localObject3).append(localException1.getMessage());
        CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.1750", paramB.a);
        return;
      }
    }
    if (!CUtil.a(this, "android.media.action.VIDEO_CAPTURE"))
    {
      CDadosCarregados.a(e(paramB), -1, "No application found for taking videos!", "1.1751", paramB.a);
      return;
    }
    localObject6 = new StringBuilder();
    ((StringBuilder)localObject6).append(getCacheDir().getAbsolutePath());
    ((StringBuilder)localObject6).append("/Ktmp_videoK");
    localObject6 = new File(((StringBuilder)localObject6).toString());
    ((File)localObject6).delete();
    boolean bool = CDadosCarregados.bA.c();
    if (bool) {
      CDadosCarregados.bA.d();
    }
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (CUtil.StringToInt(this.a) == 3) {
          localIntent.putExtra("android.intent.extra.videoQuality", 0);
        } else {
          localIntent.putExtra("android.intent.extra.videoQuality", 1);
        }
        localIntent.putExtra("android.intent.extra.durationLimit", CUtil.StringToInt(this.b));
        Object localObject1 = CMyFormDlg.this;
        Object localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(CMyFormDlg.this.getPackageName());
        ((StringBuilder)localObject2).append(".fileprovider");
        localObject1 = FileProvider.a((Context)localObject1, ((StringBuilder)localObject2).toString(), this.c);
        localIntent.putExtra("output", (Parcelable)localObject1);
        localIntent.addFlags(1);
        localIntent.addFlags(2);
        try
        {
          localObject2 = CMyFormDlg.this.getPackageManager().queryIntentActivities(localIntent, 65536).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            String str = ((ResolveInfo)((Iterator)localObject2).next()).activityInfo.packageName;
            CMyFormDlg.this.grantUriPermission(str, (Uri)localObject1, 3);
          }
        }
        catch (Exception localException)
        {
          for (;;) {}
        }
        CMyFormDlg.this.startActivityForResult(localIntent, 8);
      }
    });
    while (CDadosCarregados.p.get() == 0) {
      CUtil.e(250);
    }
    if (bool) {
      CDadosCarregados.bA.a(this);
    }
    if (CDadosCarregados.p.get() == -1)
    {
      CDadosCarregados.a(e(paramB), -1, "User canceled video!", "1.729", paramB.a);
      return;
    }
    try
    {
      localObject3 = new FileInputStream((File)localObject6);
      localObject4 = new FileOutputStream((File)localObject5);
      localObject5 = new byte['Ѐ'];
      for (;;)
      {
        i1 = ((FileInputStream)localObject3).read((byte[])localObject5);
        if (i1 <= 0) {
          break;
        }
        ((FileOutputStream)localObject4).write((byte[])localObject5, 0, i1);
      }
      ((FileInputStream)localObject3).close();
      ((FileOutputStream)localObject4).close();
      SetTarget(localException1, (String)localObject2, true, true, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException2)
    {
      localObject2 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Error copying video to final destination!\r\n");
      ((StringBuilder)localObject3).append(localException2.getMessage());
      CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.1752", paramB.a);
    }
  }
  
  void X(b paramB)
  {
    Object localObject3 = new CMyToken(a(paramB), b);
    Object localObject4 = CalculaExpressaoStr(((CMyToken)localObject3).GetNextToken(), c(paramB));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    double d2 = CUtil.StringToDouble(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    double d3 = CUtil.StringToDouble(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    int i6 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    int i7 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    double d4 = CUtil.StringToDouble(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    Object localObject2 = ((CMyToken)localObject3).GetNextToken();
    Object localObject1 = CUtil.Q(CalculaExpressaoStr(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject3).GetNextToken(), c(paramB)));
    String str = ((CMyToken)localObject3).GetNextToken();
    double d1 = d2;
    if (d2 == 0.0D) {
      d1 = 1.0D;
    }
    d2 = d3;
    if (d3 == 0.0D) {
      d2 = 3.0D;
    }
    c localC = new c(null);
    if (!((String)localObject2).equals(""))
    {
      i4 = CUtil.StringToInt(CUtil.a("CTRL(", (String)localObject2, ")"));
      localObject3 = this.f.a(i4);
      if (localObject3 == null)
      {
        CDadosCarregados.a(e(paramB), 65120, "Draw not found!", "1.1099", paramB.a);
        return;
      }
    }
    else
    {
      if ((i4 != 1) && (i4 != 2) && (i4 != 3) && (i4 != 4) && (!CUtil.d((String)localObject1, 3).equalsIgnoreCase("JPG")) && (!CUtil.d((String)localObject1, 4).equalsIgnoreCase("JPEG")) && (!CUtil.d((String)localObject1, 3).equalsIgnoreCase("GIF")) && (!CUtil.d((String)localObject1, 3).equalsIgnoreCase("PCX"))) {
        CUtil.d((String)localObject1, 3).equalsIgnoreCase("BMP");
      }
      localObject3 = null;
    }
    Object localObject5;
    int i8;
    int i9;
    int i5;
    if (i3 == 2)
    {
      localObject2 = new n();
      ((n)localObject2).a((String)localObject4, d1, d4, (bi)localObject3, localC, 0, i6, i7, i2, i1, -1, d2);
      i1 = ((n)localObject2).a();
      if ((localObject3 != null) || (!((String)localObject1).equals(""))) {
        ((n)localObject2).b();
      }
    }
    else
    {
      if (i3 == 3)
      {
        localObject2 = new o();
        ((o)localObject2).a((String)localObject4, d1, d4, (bi)localObject3, localC, 0, i6, i7, i2, i1, -1, 3.0D);
        i2 = ((o)localObject2).a();
        if (localObject3 == null)
        {
          i1 = i2;
          if (((String)localObject1).equals("")) {}
        }
        else
        {
          ((o)localObject2).b();
          i1 = i2;
        }
      }
      for (;;)
      {
        break;
        if (i3 == 4)
        {
          localObject2 = new ac();
          ((ac)localObject2).a((String)localObject4, d1, d4, (bi)localObject3, localC, 0, i6, i7, i2, i1, -1, d2);
          i2 = ((ac)localObject2).a();
          if (localObject3 == null)
          {
            i1 = i2;
            if (((String)localObject1).equals("")) {
              break;
            }
          }
          else
          {
            ((ac)localObject2).b();
            i1 = i2;
          }
        }
        else if (i3 == 5)
        {
          localObject2 = new bt();
          ((bt)localObject2).a((String)localObject4, d1, d4, (bi)localObject3, localC, 0, i6, i7, i2, i1, -1, d2);
          i2 = ((bt)localObject2).a();
          if (localObject3 == null)
          {
            i1 = i2;
            if (((String)localObject1).equals("")) {
              break;
            }
          }
          else
          {
            ((bt)localObject2).b();
            i1 = i2;
          }
        }
        else if (i3 == 6)
        {
          localObject2 = com.google.zxing.a.k;
          localObject5 = new com.google.zxing.f.d();
        }
        try
        {
          localObject6 = ((com.google.zxing.f.d)localObject5).a((String)localObject4, (com.google.zxing.a)localObject2, 0, 0, null);
          i4 = ((com.google.zxing.b.b)localObject6).f();
          i3 = ((com.google.zxing.b.b)localObject6).g();
          d2 = i4;
          Double.isNaN(d2);
          i4 = (int)(d2 * d1);
          d2 = i3;
          Double.isNaN(d2);
          i3 = (int)(d2 * d1);
          localObject4 = ((com.google.zxing.f.d)localObject5).a((String)localObject4, (com.google.zxing.a)localObject2, i4, i3, null);
          i8 = ((com.google.zxing.b.b)localObject4).f();
          i9 = ((com.google.zxing.b.b)localObject4).g();
          localObject2 = new int[i8 * i9];
          i3 = 0;
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            Object localObject6;
            continue;
            for (;;)
            {
              if (i3 >= i9) {
                break label921;
              }
              i4 = 0;
              break;
              i5 = -1;
              localException4[(i3 * i8 + i4)] = i5;
              i4 += 1;
              break;
              i3 += 1;
            }
            i4 = i8 + i6 * 2;
            i3 = i4;
            if (i2 > i4) {
              i3 = i2;
            }
            i4 = i9 + i7 * 2;
            i2 = i4;
            if (i1 > i4)
            {
              i2 = i1;
              continue;
              for (;;)
              {
                if (i3 >= i9) {
                  break label1262;
                }
                i4 = 0;
                break;
                i5 = -1;
                localException4[(i3 * i8 + i4)] = i5;
                i4 += 1;
                break;
                i3 += 1;
              }
              i3 = i8 + i6 * 2;
              if (i2 <= i3) {
                i2 = i3;
              }
              i4 = i9 + i7 * 2;
              i3 = i4;
              if (i1 > i4) {
                i3 = i1;
              }
            }
          }
        }
        if (i4 >= i8) {
          break label2324;
        }
        if (!((com.google.zxing.b.b)localObject4).a(i4, i3)) {
          break label2299;
        }
        i5 = -16777216;
        break label2302;
        label921:
        localObject4 = Bitmap.createBitmap(i8, i9, Bitmap.Config.ARGB_8888);
        ((Bitmap)localObject4).setPixels((int[])localObject2, 0, i8, 0, 0, i8, i9);
        if (localObject3 == null) {
          break label2333;
        }
        ((bi)localObject3).aa.b.drawBitmap((Bitmap)localObject4, new Rect(0, 0, i8, i9), new Rect(i6, i7, i8 + i6, i7 + i9), null);
        break label1081;
        localC.a = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_8888);
        localC.a.eraseColor(-1);
        new Canvas(localC.a).drawBitmap((Bitmap)localObject4, new Rect(0, 0, i8, i9), new Rect(i6, i7, i8 + i6, i7 + i9), null);
        for (;;)
        {
          label1081:
          break label2068;
          localObject2 = localObject1;
          if ((i3 != 7) && (i3 != 10))
          {
            if (i3 == 8)
            {
              localObject2 = com.google.zxing.a.l;
              localObject5 = new com.google.zxing.g.b();
              localObject6 = ((com.google.zxing.g.b)localObject5).a((String)localObject4, (com.google.zxing.a)localObject2, 0, 0, null);
              i4 = ((com.google.zxing.b.b)localObject6).f();
              i3 = ((com.google.zxing.b.b)localObject6).g();
              d2 = i4;
              Double.isNaN(d2);
              i4 = (int)(d2 * d1);
              d2 = i3;
              Double.isNaN(d2);
              i3 = (int)(d2 * d1);
              localObject4 = ((com.google.zxing.g.b)localObject5).a((String)localObject4, (com.google.zxing.a)localObject2, i4, i3, null);
              i8 = ((com.google.zxing.b.b)localObject4).f();
              i9 = ((com.google.zxing.b.b)localObject4).g();
              localObject2 = new int[i8 * i9];
              i3 = 0;
              break label2384;
              if (i4 >= i8) {
                break label2422;
              }
              if (!((com.google.zxing.b.b)localObject4).a(i4, i3)) {
                break label2397;
              }
              i5 = -16777216;
              break label2400;
              label1262:
              localObject4 = Bitmap.createBitmap(i8, i9, Bitmap.Config.ARGB_8888);
              ((Bitmap)localObject4).setPixels((int[])localObject2, 0, i8, 0, 0, i8, i9);
              if (localObject3 == null) {
                break label2431;
              }
              ((bi)localObject3).aa.b.drawBitmap((Bitmap)localObject4, new Rect(0, 0, i8, i9), new Rect(i6, i7, i8 + i6, i7 + i9), null);
              continue;
              localC.a = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
              localC.a.eraseColor(-1);
              new Canvas(localC.a).drawBitmap((Bitmap)localObject4, new Rect(0, 0, i8, i9), new Rect(i6, i7, i8 + i6, i7 + i9), null);
            }
            else
            {
              if (i3 == 9)
              {
                localObject4 = CUtil.b((String)localObject4, 12);
                localObject5 = new StringBuilder();
                ((StringBuilder)localObject5).append("000000000000");
                ((StringBuilder)localObject5).append((String)localObject4);
                localObject4 = CUtil.d(((StringBuilder)localObject5).toString(), 12);
                localObject5 = new v();
                ((v)localObject5).a((String)localObject4, d1, d4, 0, (bi)localObject3, localC, 0, i6, i7, i2, i1, -1, d2);
                i1 = ((v)localObject5).a();
                if (localObject3 == null) {
                  if (((String)localObject2).equals("")) {
                    break label1546;
                  }
                }
                ((v)localObject5).d();
                label1546:
                break label2071;
              }
              localObject1 = new m();
              boolean bool;
              if (i3 == 11) {
                bool = true;
              } else {
                bool = false;
              }
              ((m)localObject1).a((String)localObject4, d1, d4, (bi)localObject3, localC, 0, i6, i7, i2, i1, bool, '\035');
              i1 = ((m)localObject1).a();
              if ((localObject3 != null) || (!((String)localObject2).equals(""))) {
                ((m)localObject1).b();
              }
              localObject1 = localObject2;
              break label2071;
            }
          }
          else
          {
            localObject5 = com.google.zxing.a.f;
            localObject6 = new com.google.zxing.c.b();
          }
          try
          {
            localEnumMap = new EnumMap(g.class);
            if (i3 == 10) {
              localEnumMap.put(g.c, com.google.zxing.c.c.l.c);
            } else {
              localEnumMap.put(g.c, com.google.zxing.c.c.l.b);
            }
            localObject1 = localObject2;
          }
          catch (Exception localException2)
          {
            EnumMap localEnumMap;
            for (;;) {}
          }
          try
          {
            localObject2 = ((com.google.zxing.c.b)localObject6).a((String)localObject4, (com.google.zxing.a)localObject5, 0, 0, localEnumMap);
            i9 = ((com.google.zxing.b.b)localObject2).f();
            i8 = ((com.google.zxing.b.b)localObject2).g();
            localObject4 = new int[i9 * i8];
            i3 = 0;
          }
          catch (Exception localException4)
          {
            break label2068;
          }
          if (i4 >= i9) {
            break label2519;
          }
          if (!((com.google.zxing.b.b)localObject2).a(i4, i3)) {
            break label2494;
          }
          i5 = -16777216;
          break label2497;
        }
      }
    }
    label2068:
    label2071:
    label2299:
    label2302:
    label2324:
    label2333:
    label2384:
    label2397:
    label2400:
    label2422:
    label2431:
    label2494:
    label2497:
    label2519:
    label2526:
    for (;;)
    {
      localObject2 = Bitmap.createBitmap(i9, i8, Bitmap.Config.ARGB_8888);
      ((Bitmap)localObject2).setPixels((int[])localObject4, 0, i9, 0, 0, i9, i8);
      if (localObject3 != null)
      {
        localObject4 = ((bi)localObject3).aa.b;
        localObject5 = new Rect(0, 0, i9, i8);
        d2 = i9;
        Double.isNaN(d2);
        i1 = (int)(d2 * d1);
        d2 = i8;
        Double.isNaN(d2);
        i2 = (int)(d2 * d1);
        ((Canvas)localObject4).drawBitmap((Bitmap)localObject2, (Rect)localObject5, new Rect(i6, i7, i1 + i6, i7 + i2), null);
      }
      else
      {
        d2 = i9;
        Double.isNaN(d2);
        i4 = (int)(d2 * d1);
        i3 = i6 * 2 + i4;
        if (i2 <= i3) {
          i2 = i3;
        }
        d2 = i8;
        Double.isNaN(d2);
        i5 = (int)(d2 * d1);
        i3 = i5 + i7 * 2;
        if (i1 <= i3) {
          i1 = i3;
        }
        localC.a = Bitmap.createBitmap(i2, i1, Bitmap.Config.ARGB_8888);
        localC.a.eraseColor(-1);
        new Canvas(localC.a).drawBitmap((Bitmap)localObject2, new Rect(0, 0, i9, i8), new Rect(i6, i7, i6 + i4, i7 + i5), null);
        break label2068;
        localObject1 = localObject2;
      }
      i1 = 0;
      SetTarget(str, CUtil.a(i1), true, false, c(paramB));
      if ((!((String)localObject1).equals("")) && (localC.a != null)) {
        try
        {
          CUtil.a(localC.a, (String)localObject1, true, false);
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          localObject2 = e(paramB);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Error saving image to file!\r\n");
          ((StringBuilder)localObject3).append(localOutOfMemoryError.getMessage());
          CDadosCarregados.a((ec)localObject2, -15, ((StringBuilder)localObject3).toString(), "1.1826", paramB.a);
          return;
        }
        catch (Exception localException1)
        {
          localObject2 = e(paramB);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Error saving image to file!\r\n");
          ((StringBuilder)localObject3).append(localException1.getMessage());
          CDadosCarregados.a((ec)localObject2, -15, ((StringBuilder)localObject3).toString(), "1.1100", paramB.a);
          return;
        }
      }
      if (localObject3 != null) {
        CUtil.a(true, this.bh, new de()
        {
          public void a()
          {
            this.a.aa.invalidate();
          }
        });
      }
      CDadosCarregados.a(e(paramB));
      return;
      for (;;)
      {
        if (i3 >= i8) {
          break label2526;
        }
        i4 = 0;
        break;
        i5 = -1;
        localObject4[(i3 * i9 + i4)] = i5;
        i4 += 1;
        break;
        i3 += 1;
      }
    }
  }
  
  void Y(final b paramB)
  {
    if (CDadosCarregados.bu == 1)
    {
      bG(paramB);
      return;
    }
    if (CDadosCarregados.bu == 2)
    {
      bN(paramB);
      return;
    }
    if (CDadosCarregados.bu == 4)
    {
      aC(paramB);
      return;
    }
    if (CDadosCarregados.bu == 6)
    {
      aW(paramB);
      return;
    }
    if (CDadosCarregados.bu == 7)
    {
      by(paramB);
      return;
    }
    if (CDadosCarregados.bu == 8)
    {
      ak(paramB);
      return;
    }
    if (CDadosCarregados.bu == 10)
    {
      bk(paramB);
      return;
    }
    if (CDadosCarregados.bu == 11)
    {
      ad(paramB);
      return;
    }
    if (CDadosCarregados.bu == 12)
    {
      aP(paramB);
      return;
    }
    if (CDadosCarregados.bu == 14)
    {
      au(paramB);
      return;
    }
    if (CDadosCarregados.bu == 15)
    {
      bd(paramB);
      return;
    }
    if (CDadosCarregados.bu == 16)
    {
      br(paramB);
      return;
    }
    Object localObject = new CMyToken(a(paramB), b);
    String str2 = ((CMyToken)localObject).GetNextToken();
    String str3 = ((CMyToken)localObject).GetNextToken();
    CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    CDadosCarregados.F = 0;
    localObject = new e(false);
    if ((CDadosCarregados.bK != 2) && (!c("android.permission.CAMERA")))
    {
      CDadosCarregados.a(e(paramB), -20, "User did not give permission to access the camera!", "1.2256", paramB.a);
      return;
    }
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        for (;;)
        {
          try
          {
            if (CDadosCarregados.bK == 2)
            {
              localIntent = new Intent("com.google.zxing.client.android.SCAN");
            }
            else
            {
              int i = CDadosCarregados.bK;
              if (i != 3) {}
            }
          }
          catch (Exception localException1)
          {
            Intent localIntent;
            label101:
            ec localEc = CMyFormDlg.e(paramB);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Error starting intent!\r\n");
            localStringBuilder.append(localException1.toString());
            CDadosCarregados.a(localEc, 64621, localStringBuilder.toString(), "1.1828", paramB.a);
            this.b.a = true;
            CDadosCarregados.F = -1;
            return;
          }
          try
          {
            CMyFormDlg.this.getPackageManager().getApplicationInfo("com.google.zxing.client.android", 0);
            localIntent = new Intent("com.google.zxing.client.android.SCAN");
          }
          catch (Exception localException2) {}
        }
        localIntent = new Intent("com.google.zxing.client.android.KSCAN");
        localIntent.setClass(CMyFormDlg.this, CaptureActivity.class);
        break label101;
        localIntent = new Intent("com.google.zxing.client.android.KSCAN");
        localIntent.setClass(CMyFormDlg.this, CaptureActivity.class);
        localIntent.putExtra("SCAN_FORMATS", CDadosCarregados.E);
        localIntent.putExtra("RESULT_DISPLAY_DURATION_MS", 0L);
        localIntent.putExtra("ASSUME_GS1", true);
        CMyFormDlg.this.startActivityForResult(localIntent, 11);
      }
    });
    while (CDadosCarregados.F == 0) {
      CUtil.e(250);
    }
    if (((e)localObject).a) {
      return;
    }
    if (CDadosCarregados.F == -1)
    {
      CDadosCarregados.a(e(paramB), -1, "User canceled!", "1.1829", paramB.a);
      return;
    }
    localObject = "";
    if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.a.toString()))
    {
      localObject = "74";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.b.toString()))
    {
      localObject = "19";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.e.toString()))
    {
      String str1 = "23";
      localObject = str1;
      if (CDadosCarregados.H.startsWith("]C1"))
      {
        CDadosCarregados.H = CUtil.a(CDadosCarregados.H, 3, CDadosCarregados.H.length() - 3);
        localObject = str1;
      }
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.c.toString()))
    {
      localObject = "13";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.d.toString()))
    {
      localObject = "25";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.f.toString()))
    {
      localObject = "40";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.h.toString()))
    {
      localObject = "1";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.g.toString()))
    {
      localObject = "2";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.i.toString()))
    {
      localObject = "15";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.j.toString()))
    {
      localObject = "42";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.k.toString()))
    {
      localObject = "33";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.l.toString()))
    {
      localObject = "41";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.m.toString()))
    {
      localObject = "37";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.n.toString()))
    {
      localObject = "39";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.o.toString()))
    {
      localObject = "3";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.p.toString()))
    {
      localObject = "4";
    }
    else if (CDadosCarregados.G.equalsIgnoreCase(com.google.zxing.a.q.toString()))
    {
      localObject = "11";
    }
    SetTarget(str2, CDadosCarregados.H, true, true, c(paramB));
    SetTarget(str3, (String)localObject, true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void Z(b paramB)
  {
    for (;;)
    {
      int i1;
      try
      {
        Object localObject = getPackageManager();
        int i3 = 0;
        localObject = ((PackageManager)localObject).getInstalledPackages(0);
        i1 = 0;
        int i2 = i3;
        if (i1 < ((List)localObject).size())
        {
          if (!((PackageInfo)((List)localObject).get(i1)).packageName.equalsIgnoreCase("com.adlink.IMXUtil")) {
            break label169;
          }
          i2 = 1;
        }
        if (i2 == 0)
        {
          CDadosCarregados.a(e(paramB), 64626, "Error connecting to scanner!\r\ncom.adlink.IMXUtil not found.", "1.1926", paramB.a);
          return;
        }
        CDadosCarregados.bx = new a();
        CDadosCarregados.bx.a(this);
        CDadosCarregados.a(e(paramB));
        return;
      }
      catch (Exception localException)
      {
        ec localEc = e(paramB);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error connecting to scanner!\r\n");
        localStringBuilder.append(localException.getMessage());
        CDadosCarregados.a(localEc, 64626, localStringBuilder.toString(), "1.1927", paramB.a);
        return;
      }
      label169:
      i1 += 1;
    }
  }
  
  double a(String paramString, int paramInt)
  {
    int i3 = CUtil.StringToInt(paramString);
    double d1 = 0.0D;
    int i1 = 0;
    while (i1 < this.f.t)
    {
      double d2 = d1;
      if (this.f.s[i1].f == i3)
      {
        int i2 = 0;
        while (i2 < this.f.s[i1].s())
        {
          d1 += CUtil.StringToDouble(this.f.s[i1].a(i2, paramInt - 1));
          i2 += 1;
        }
        d2 = d1;
      }
      i1 += 1;
      d1 = d2;
    }
    return d1;
  }
  
  public int a(int paramInt1, int paramInt2)
  {
    return a(paramInt1, paramInt2, this.n);
  }
  
  public int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (CDadosCarregados.br == 3)
    {
      int i1 = paramInt2;
      if (paramInt2 == 3) {
        if (this.U[paramInt3] <= this.T[paramInt3]) {
          i1 = 2;
        } else {
          i1 = 1;
        }
      }
      if (i1 == 2)
      {
        d1 = paramInt1;
        d2 = this.U[paramInt3];
        Double.isNaN(d1);
        return (int)Math.round(d1 * d2);
      }
      double d1 = paramInt1;
      double d2 = this.T[paramInt3];
      Double.isNaN(d1);
      return (int)Math.round(d1 * d2);
    }
    if (CDadosCarregados.br == 2) {
      return paramInt1;
    }
    return Math.round(paramInt1 * CDadosCarregados.bs);
  }
  
  int a(int paramInt, final as paramAs)
  {
    final cj localCj = new cj(-1);
    int i2 = 0;
    final int i1 = 0;
    while (i1 < paramAs.l)
    {
      if (paramAs.k[i1].f == paramInt)
      {
        CUtil.a(true, this.bh, new de()
        {
          public void a()
          {
            localCj.a = paramAs.k[i1].o();
          }
        });
        if (localCj.a < 0) {
          localCj.a = 0;
        } else {
          localCj.a += 1;
        }
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramAs.j)
    {
      if (paramAs.i[i1].f == paramInt)
      {
        localCj.a = paramAs.i[i1].o();
        if (localCj.a < 0) {
          localCj.a = 0;
        } else {
          localCj.a += 1;
        }
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramAs.n)
    {
      if (paramAs.m[i1].f == paramInt)
      {
        localCj.a = paramAs.m[i1].af;
        if (localCj.a < 0) {
          localCj.a = 0;
        } else {
          localCj.a += 1;
        }
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramAs.t)
    {
      if (paramAs.s[i1].f == paramInt)
      {
        localCj.a = paramAs.s[i1].m();
        if (localCj.a < 0) {
          localCj.a = 0;
        } else {
          localCj.a += 1;
        }
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramAs.B)
    {
      if (paramAs.A[i1].f == paramInt)
      {
        localCj.a = paramAs.A[i1].n();
        if (localCj.a < 0) {
          localCj.a = 0;
        } else {
          localCj.a += 1;
        }
      }
      i1 += 1;
    }
    if (localCj.a < 0)
    {
      i1 = i2;
      while (i1 < paramAs.B)
      {
        as localAs = paramAs.A[i1].o();
        if (localAs != null) {
          localCj.a = a(paramInt, localAs);
        }
        if (localCj.a >= 0) {
          break;
        }
        i1 += 1;
      }
    }
    return localCj.a;
  }
  
  int a(int paramInt, as paramAs, boolean paramBoolean, cj paramCj)
  {
    int i4 = 0;
    int i2 = 0;
    int i1 = -1;
    while (i2 < paramAs.l)
    {
      if (paramAs.k[i2].f == paramInt) {
        i1 = paramAs.k[i2].j();
      }
      i2 += 1;
    }
    i2 = 0;
    while (i2 < paramAs.j)
    {
      if (paramAs.i[i2].f == paramInt) {
        i1 = paramAs.i[i2].j();
      }
      i2 += 1;
    }
    i2 = 0;
    while (i2 < paramAs.n)
    {
      if (paramAs.m[i2].f == paramInt) {
        i1 = paramAs.m[i2].j();
      }
      i2 += 1;
    }
    i2 = 0;
    while (i2 < paramAs.t)
    {
      if (paramAs.s[i2].f == paramInt)
      {
        if ((paramBoolean) && (paramAs.s[i2].an))
        {
          paramCj.a = 1;
          return -1;
        }
        i1 = paramAs.s[i2].s();
      }
      i2 += 1;
    }
    i2 = 0;
    while (i2 < paramAs.B)
    {
      if (paramAs.A[i2].f == paramInt)
      {
        if ((paramBoolean) && (paramAs.A[i2].ar))
        {
          paramCj.a = 1;
          return -1;
        }
        i1 = paramAs.A[i2].l();
      }
      i2 += 1;
    }
    int i3 = i1;
    if (i1 < 0)
    {
      i2 = i4;
      for (;;)
      {
        i3 = i1;
        if (i2 >= paramAs.B) {
          break;
        }
        as localAs = paramAs.A[i2].o();
        if (localAs != null) {
          i1 = a(paramInt, localAs, paramBoolean, paramCj);
        }
        if (i1 >= 0) {
          return i1;
        }
        i2 += 1;
      }
    }
    return i3;
  }
  
  int a(b paramB, boolean paramBoolean)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject).GetNextToken(), ")"));
    localObject = ((CMyToken)localObject).GetNextToken();
    cj localCj = new cj(0);
    i1 = a(i1, this.f, paramBoolean, localCj);
    if (localCj.a != 0) {
      return localCj.a;
    }
    SetTarget((String)localObject, CUtil.a(i1), true, false, c(paramB));
    return 0;
  }
  
  int a(String paramString1, String paramString2)
  {
    int i3 = this.f.b;
    ag[] arrayOfAg = this.f.a;
    int i1 = 0;
    int i2 = 0;
    while (i1 < i3)
    {
      if ((!arrayOfAg[i1].Q.equals("")) && (arrayOfAg[i1].Q.equalsIgnoreCase(paramString1)) && (arrayOfAg[i1].P.equals("")))
      {
        if (arrayOfAg[i1].O.equalsIgnoreCase(paramString2)) {
          return i2;
        }
        i2 += 1;
      }
      i1 += 1;
    }
    return -1;
  }
  
  int a(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Select TBCTYP From ");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("MFTBC, ");
    localStringBuilder.append(paramString3);
    localStringBuilder.append("MFTAB Where TABTAB = '");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("' And TABIDO = TBCIDO And TBCCOL = '");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("'");
    paramString2 = localStringBuilder.toString();
    paramString1 = new r(CDadosCarregados.ay);
    if (!paramString1.a(paramString2))
    {
      paramString2 = new StringBuilder();
      paramString2.append("Error reading the tables from the Data Base!\r\n");
      paramString2.append(paramString1.c);
      CUtil.a(paramString2.toString(), this);
      paramString1.a();
      return -1;
    }
    paramString1.d();
    if (!paramString1.f())
    {
      int i1 = CUtil.StringToInt(paramString1.b(0));
      paramString1.a();
      return i1;
    }
    CUtil.a("The table doesn’t exist in the Data Base!", this);
    paramString1.a();
    return -1;
  }
  
  int a(String paramString1, boolean paramBoolean, String paramString2)
  {
    r localR;
    StringBuilder localStringBuilder;
    int i1;
    if (paramBoolean)
    {
      localR = new r(CDadosCarregados.ay);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Select LVRIDV From ");
      localStringBuilder.append(paramString2);
      localStringBuilder.append("MFLVR Where LVRIDJ=");
      localStringBuilder.append(this.j);
      localStringBuilder.append(" And LVRNOM='");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("'");
      if (localR.a(localStringBuilder.toString()))
      {
        localR.d();
        if (!localR.f())
        {
          i1 = CUtil.StringToInt(localR.b(0));
          localR.a();
          return i1;
        }
      }
      localR.a();
    }
    else
    {
      localR = new r(CDadosCarregados.ay);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Select VARIDV From ");
      localStringBuilder.append(paramString2);
      localStringBuilder.append("MFVAR Where VARNOM='");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("'");
      if (localR.a(localStringBuilder.toString()))
      {
        localR.d();
        if (!localR.f())
        {
          i1 = CUtil.StringToInt(localR.b(0));
          localR.a();
          return i1;
        }
      }
      localR.a();
    }
    return -1;
  }
  
  String a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    ah localAh = new ah();
    String str = ah.a(paramString1);
    if (!localAh.a(str, "", paramString1, true))
    {
      CUtil.a(localAh.z, this);
      return "";
    }
    if (localAh.a)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Select \"");
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append("\" From ");
      ((StringBuilder)localObject).append(localAh.d);
      ((StringBuilder)localObject).append("\"");
      ((StringBuilder)localObject).append(paramString1);
      ((StringBuilder)localObject).append("\"");
      paramString2 = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Select ");
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append(" From ");
      ((StringBuilder)localObject).append(localAh.d);
      ((StringBuilder)localObject).append(paramString1);
      paramString2 = ((StringBuilder)localObject).toString();
    }
    Object localObject = paramString2;
    if (localAh.b)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append("(nolock)");
      localObject = ((StringBuilder)localObject).toString();
    }
    if (localAh.a)
    {
      paramString2 = new StringBuilder();
      paramString2.append((String)localObject);
      paramString2.append(" Where \"");
      paramString2.append(paramString3);
      paramString2.append("\" = ");
      paramString2 = paramString2.toString();
    }
    else
    {
      paramString2 = new StringBuilder();
      paramString2.append((String)localObject);
      paramString2.append(" Where ");
      paramString2.append(paramString3);
      paramString2.append(" = ");
      paramString2 = paramString2.toString();
    }
    int i1 = a(paramString1, paramString3, str);
    if ((i1 != 2) && (i1 != 3))
    {
      paramString1 = new StringBuilder();
      paramString1.append(paramString2);
      paramString1.append("'");
      paramString1.append(CalculaExpressaoStr(paramString4, null));
      paramString1.append("'");
      paramString1 = paramString1.toString();
    }
    else
    {
      paramString1 = new StringBuilder();
      paramString1.append(paramString2);
      paramString1.append(CalculaExpressaoNum(paramString4, null));
      paramString1 = paramString1.toString();
    }
    if (!localAh.b(paramString1))
    {
      paramString1 = new StringBuilder();
      paramString1.append("Error filling an input box!\r\n");
      paramString1.append(localAh.z);
      CUtil.a(paramString1.toString(), this);
      localAh.b();
      return "";
    }
    localAh.f();
    if (!localAh.h())
    {
      paramString1 = localAh.a(0);
      localAh.b();
      return paramString1;
    }
    localAh.b();
    return "";
  }
  
  String a(String paramString, boolean paramBoolean1, boolean paramBoolean2, TempVars paramTempVars)
  {
    int i1 = CUtil.StringToInt(paramString);
    if (paramBoolean2)
    {
      if (paramTempVars == null) {
        return "";
      }
      if (paramTempVars.m_variaveisString[i1] != 0)
      {
        paramString = new StringBuilder();
        paramString.append("'");
        paramString.append(paramTempVars.m_variaveis[i1]);
        paramString.append("'");
        return paramString.toString();
      }
      return paramTempVars.m_variaveis[i1];
    }
    if (paramBoolean1)
    {
      if (this.aX[i1] != 0)
      {
        paramString = new StringBuilder();
        paramString.append("'");
        paramString.append(this.aW[i1]);
        paramString.append("'");
        return paramString.toString();
      }
      return this.aW[i1];
    }
    if (paramTempVars == null) {
      paramString = CDadosCarregados.aZ;
    } else {
      paramString = paramTempVars.variaveisProject;
    }
    if (paramTempVars == null) {
      paramTempVars = CDadosCarregados.ba;
    } else {
      paramTempVars = paramTempVars.variaveisStringProject;
    }
    if (paramTempVars[i1] != 0)
    {
      paramTempVars = new StringBuilder();
      paramTempVars.append("'");
      paramTempVars.append(paramString[i1]);
      paramTempVars.append("'");
      return paramTempVars.toString();
    }
    return paramString[i1];
  }
  
  public void a()
  {
    this.aS.lock();
    this.aT -= 1;
    this.aS.unlock();
  }
  
  void a(int paramInt)
  {
    d(paramInt);
    this.f.b();
    k();
  }
  
  void a(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, b paramB)
  {
    ag localAg = j(paramInt1);
    if (localAg != null) {
      localAg.a(paramBoolean, paramInt2, paramInt4, paramInt5);
    }
    a(e(paramB), paramInt3, b(paramB), this.f);
  }
  
  void a(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, as paramAs)
  {
    int i2 = paramAs.b;
    Object localObject = paramAs.a;
    int i1 = 0;
    while (i1 < i2)
    {
      if ((localObject[i1].a != paramInt1) && (localObject[i1].a != 0)) {
        break label67;
      }
      localObject[i1].a(paramBoolean, paramInt2, paramInt3, paramInt4);
      label67:
      i1 += 1;
    }
    i2 = paramAs.B;
    paramAs = paramAs.A;
    i1 = 0;
    while (i1 < i2)
    {
      if ((paramAs[i1].a == paramInt1) || (paramAs[i1].a == 0))
      {
        localObject = paramAs[i1].o();
        if (localObject != null) {
          a(paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, (as)localObject);
        }
      }
      i1 += 1;
    }
  }
  
  void a(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if (paramView == null) {
      return;
    }
    if (paramBoolean)
    {
      paramView = getWindow().getDecorView();
      localObject = (WindowManager.LayoutParams)paramView.getLayoutParams();
      if ((!this.D) && (this.E)) {
        paramView.setPadding(0, 0, 0, 0);
      }
      int[] arrayOfInt1;
      int[] arrayOfInt2;
      if (paramInt1 == 7)
      {
        ((WindowManager.LayoutParams)localObject).x = a(paramInt2, 1);
        arrayOfInt1 = this.P;
        arrayOfInt2 = this.P;
        paramInt1 = ((WindowManager.LayoutParams)localObject).x;
        arrayOfInt2[1] = paramInt1;
        arrayOfInt1[0] = paramInt1;
        getWindowManager().updateViewLayout(paramView, (ViewGroup.LayoutParams)localObject);
        return;
      }
      if (paramInt1 == 8)
      {
        ((WindowManager.LayoutParams)localObject).y = a(paramInt2, 2);
        arrayOfInt1 = this.Q;
        arrayOfInt2 = this.Q;
        paramInt1 = ((WindowManager.LayoutParams)localObject).y;
        arrayOfInt2[1] = paramInt1;
        arrayOfInt1[0] = paramInt1;
        getWindowManager().updateViewLayout(paramView, (ViewGroup.LayoutParams)localObject);
        return;
      }
      double d1;
      double d2;
      if (paramInt1 == 9)
      {
        ((WindowManager.LayoutParams)localObject).width = a(paramInt2, 1);
        this.R[this.n] = ((WindowManager.LayoutParams)localObject).width;
        if (this.n == 0) {
          paramInt1 = 1;
        } else {
          paramInt1 = 0;
        }
        if (this.D) {
          this.S[paramInt1] = this.R[this.n];
        } else {
          this.f.J.getLayoutParams().width = this.R[this.n];
        }
        d1 = this.R[1];
        d2 = this.R[0];
        Double.isNaN(d1);
        Double.isNaN(d2);
        this.o = (d1 / d2);
        d1 = this.S[1];
        d2 = this.S[0];
        Double.isNaN(d1);
        Double.isNaN(d2);
        this.p = (d1 / d2);
        getWindowManager().updateViewLayout(paramView, (ViewGroup.LayoutParams)localObject);
        k();
        return;
      }
      if (paramInt1 == 10)
      {
        ((WindowManager.LayoutParams)localObject).height = a(paramInt2, 2);
        this.S[this.n] = ((WindowManager.LayoutParams)localObject).height;
        if (this.n == 0) {
          paramInt1 = 1;
        } else {
          paramInt1 = 0;
        }
        if (this.D) {
          this.R[paramInt1] = this.S[this.n];
        } else {
          this.f.J.getLayoutParams().height = this.S[this.n];
        }
        d1 = this.R[1];
        d2 = this.R[0];
        Double.isNaN(d1);
        Double.isNaN(d2);
        this.o = (d1 / d2);
        d1 = this.S[1];
        d2 = this.S[0];
        Double.isNaN(d1);
        Double.isNaN(d2);
        this.p = (d1 / d2);
        getWindowManager().updateViewLayout(paramView, (ViewGroup.LayoutParams)localObject);
        k();
      }
      return;
    }
    Object localObject = (AbsoluteLayout.LayoutParams)paramView.getLayoutParams();
    if (paramInt1 == 7) {
      ((AbsoluteLayout.LayoutParams)localObject).x = a(paramInt2, 1);
    } else if (paramInt1 == 8) {
      ((AbsoluteLayout.LayoutParams)localObject).y = a(paramInt2, 2);
    } else if (paramInt1 == 9) {
      ((AbsoluteLayout.LayoutParams)localObject).width = a(paramInt2, 1);
    } else if (paramInt1 == 10) {
      ((AbsoluteLayout.LayoutParams)localObject).height = a(paramInt2, 2);
    }
    paramView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }
  
  void a(com.google.firebase.messaging.d paramD, String paramString, b paramB, TempVars paramTempVars)
  {
    CMyToken localCMyToken = new CMyToken(paramString, c);
    db localDb = new db(paramD.b());
    while (localCMyToken.HasTokens())
    {
      paramString = new CMyToken(localCMyToken.GetNextToken(), d);
      String str2 = CalculaExpressaoStr(paramString.GetNextToken(), paramTempVars);
      CUtil.StringToInt(CalculaExpressaoNum(paramString.GetNextToken(), paramTempVars));
      String str1 = paramString.GetNextToken();
      paramB = localDb.a(str2);
      paramString = paramB;
      if (paramB == null) {
        if (paramD.d() == null) {
          paramString = "";
        } else if (str2.equalsIgnoreCase("title")) {
          paramString = paramD.d().a();
        } else if (str2.equalsIgnoreCase("body")) {
          paramString = paramD.d().b();
        } else if (str2.equalsIgnoreCase("tag")) {
          paramString = paramD.d().e();
        } else {
          paramString = "";
        }
      }
      SetTarget(str1, paramString, true, true, paramTempVars);
    }
  }
  
  void a(b paramB, int paramInt)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    Object localObject2 = ((CMyToken)localObject1).GetNextToken();
    Object localObject3 = ((CMyToken)localObject1).GetNextToken();
    if (((String)localObject2).equalsIgnoreCase("CURRENTCTRL"))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("CTRL(");
      ((StringBuilder)localObject1).append(CUtil.LongToString(paramInt));
      ((StringBuilder)localObject1).append(")");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = localObject2;
      if (CUtil.b((String)localObject2, 8).equalsIgnoreCase("DYN_CTRL"))
      {
        localObject1 = CalculaExpressaoStr(CUtil.a((String)localObject2, 9, ((String)localObject2).length() - 10), c(paramB));
        paramInt = this.f.d((String)localObject1);
        if (paramInt < 0)
        {
          localObject2 = e(paramB);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Control \"");
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append("\"not found!");
          CDadosCarregados.a((ec)localObject2, -17, ((StringBuilder)localObject3).toString(), "1.1885", paramB.a);
          return;
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("CTRL(");
        ((StringBuilder)localObject1).append(CUtil.a(paramInt));
        ((StringBuilder)localObject1).append(")");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    int i1 = CUtil.StringToInt(CUtil.a("CTRL(", (String)localObject1, ")"));
    int i2 = this.f.b;
    localObject2 = this.f.a;
    paramInt = 0;
    while (paramInt < i2)
    {
      if (localObject2[paramInt].f == i1)
      {
        SetTarget((String)localObject3, localObject2[paramInt].h, true, true, c(paramB));
        CDadosCarregados.a(e(paramB));
        return;
      }
      paramInt += 1;
    }
    localObject2 = e(paramB);
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("Control \"");
    ((StringBuilder)localObject3).append((String)localObject1);
    ((StringBuilder)localObject3).append("\"not found!");
    CDadosCarregados.a((ec)localObject2, -17, ((StringBuilder)localObject3).toString(), "1.1446", paramB.a);
  }
  
  void a(ec paramEc, int paramInt1, int paramInt2, as paramAs)
  {
    r localR1 = new r(CDadosCarregados.ay);
    r localR2 = new r(CDadosCarregados.ay);
    r localR3 = new r(CDadosCarregados.az);
    int i1 = paramAs.b;
    ag[] arrayOfAg = paramAs.a;
    int i3 = paramAs.j;
    Object localObject2 = paramAs.i;
    int i9 = paramAs.l;
    Object localObject6 = paramAs.k;
    int i8 = paramAs.n;
    Object localObject5 = paramAs.m;
    int i7 = paramAs.t;
    Object localObject4 = paramAs.s;
    int i6 = paramAs.z;
    Object localObject3 = paramAs.y;
    int i5 = paramAs.B;
    Object localObject1 = paramAs.A;
    int i2 = paramAs.f;
    au[] arrayOfAu = paramAs.e;
    paramAs = "";
    int i4 = 0;
    Object localObject7;
    while (i4 < i3)
    {
      if (!localObject2[i4].x)
      {
        localObject7 = localObject2[i4].n();
        if (!((String)localObject7).equals(""))
        {
          localObject7 = ((String)localObject7).replace("\r\n", "\r\n\t");
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramAs);
          localStringBuilder.append("\r\nError refreshing ComboBox(");
          localStringBuilder.append(localObject2[i4].e);
          localStringBuilder.append(")!\r\n\t");
          localStringBuilder.append((String)localObject7);
          paramAs = localStringBuilder.toString();
        }
        localObject2[i4].x = true;
      }
      i4 += 1;
    }
    localObject2 = localObject1;
    i3 = 0;
    while (i3 < i9)
    {
      if (!localObject6[i3].x)
      {
        localObject7 = localObject6[i3].n();
        localObject1 = paramAs;
        if (!((String)localObject7).equals(""))
        {
          localObject1 = ((String)localObject7).replace("\r\n", "\r\n\t");
          localObject7 = new StringBuilder();
          ((StringBuilder)localObject7).append(paramAs);
          ((StringBuilder)localObject7).append("\r\nError refreshing ListBox(");
          ((StringBuilder)localObject7).append(localObject6[i3].e);
          ((StringBuilder)localObject7).append(")!\r\n\t");
          ((StringBuilder)localObject7).append((String)localObject1);
          localObject1 = ((StringBuilder)localObject7).toString();
        }
        localObject6[i3].x = true;
        paramAs = (as)localObject1;
      }
      i3 += 1;
    }
    i3 = 0;
    while (i3 < i8)
    {
      if (!localObject5[i3].x)
      {
        localObject6 = localObject5[i3].m();
        localObject1 = paramAs;
        if (!((String)localObject6).equals(""))
        {
          localObject1 = ((String)localObject6).replace("\r\n", "\r\n\t");
          localObject6 = new StringBuilder();
          ((StringBuilder)localObject6).append(paramAs);
          ((StringBuilder)localObject6).append("\r\nError refreshing RadioGroup(");
          ((StringBuilder)localObject6).append(localObject5[i3].e);
          ((StringBuilder)localObject6).append(")!\r\n\t");
          ((StringBuilder)localObject6).append((String)localObject1);
          localObject1 = ((StringBuilder)localObject6).toString();
        }
        localObject5[i3].x = true;
        paramAs = (as)localObject1;
      }
      i3 += 1;
    }
    i3 = 0;
    while (i3 < i7)
    {
      if (!localObject4[i3].x)
      {
        localObject5 = localObject4[i3].i(paramInt1);
        localObject1 = paramAs;
        if (!((String)localObject5).equals(""))
        {
          localObject1 = ((String)localObject5).replace("\r\n", "\r\n\t");
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append(paramAs);
          ((StringBuilder)localObject5).append("\r\nError refreshing Table(");
          ((StringBuilder)localObject5).append(localObject4[i3].e);
          ((StringBuilder)localObject5).append(")!\r\n\t");
          ((StringBuilder)localObject5).append((String)localObject1);
          localObject1 = ((StringBuilder)localObject5).toString();
        }
        localObject4[i3].x = true;
        paramAs = (as)localObject1;
      }
      i3 += 1;
    }
    i3 = 0;
    while (i3 < i6)
    {
      if (!localObject3[i3].x)
      {
        localObject4 = localObject3[i3].m();
        localObject1 = paramAs;
        if (!((String)localObject4).equals(""))
        {
          localObject1 = ((String)localObject4).replace("\r\n", "\r\n\t");
          localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append(paramAs);
          ((StringBuilder)localObject4).append("\r\nError refreshing Chart(");
          ((StringBuilder)localObject4).append(localObject3[i3].e);
          ((StringBuilder)localObject4).append(")!\r\n\t");
          ((StringBuilder)localObject4).append((String)localObject1);
          localObject1 = ((StringBuilder)localObject4).toString();
        }
        localObject3[i3].x = true;
        paramAs = (as)localObject1;
      }
      i3 += 1;
    }
    i3 = 0;
    localObject1 = localObject2;
    for (localObject2 = paramAs; i3 < i5; localObject2 = paramAs)
    {
      paramAs = localObject1[i3].o();
      if (paramAs != null) {
        a(paramEc, paramInt1, paramInt2, paramAs);
      }
      if (localObject1[i3].x)
      {
        paramAs = (as)localObject2;
      }
      else
      {
        localObject1[i3].x = true;
        localObject3 = localObject1[i3].k(paramInt1);
        paramAs = (as)localObject2;
        if (((String)localObject3).length() != 0)
        {
          paramAs = ((String)localObject3).replace("\r\n", "\r\n\t");
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject2);
          ((StringBuilder)localObject3).append("\r\nError refreshing Looper(");
          ((StringBuilder)localObject3).append(localObject1[i3].e);
          ((StringBuilder)localObject3).append(")!\r\n\t");
          ((StringBuilder)localObject3).append(paramAs);
          paramAs = ((StringBuilder)localObject3).toString();
        }
      }
      i3 += 1;
    }
    paramInt1 = i1;
    if (this.ba > 0)
    {
      i3 = 0;
      for (;;)
      {
        paramInt1 = i1;
        if (i3 >= this.ba) {
          break;
        }
        i4 = 0;
        paramInt1 = 0;
        while (i4 < i1)
        {
          if ((!arrayOfAg[i4].Q.equals("")) && (arrayOfAg[i4].Q.equalsIgnoreCase(this.bb[i3].a)) && (arrayOfAg[i4].P.equals(""))) {
            if (arrayOfAg[i4].w)
            {
              paramInt1 += 1;
            }
            else
            {
              if (this.bb[i3].E)
              {
                localObject1 = CUtil.b(this.bb[i3].F, paramInt1);
                if (arrayOfAg[i4].N == 4)
                {
                  paramAs = CUtil.k((String)localObject1, arrayOfAg[i4].M);
                }
                else if (arrayOfAg[i4].N == 5)
                {
                  paramAs = CUtil.m((String)localObject1, arrayOfAg[i4].M);
                }
                else
                {
                  paramAs = (as)localObject1;
                  if (arrayOfAg[i4].N == 6) {
                    paramAs = CUtil.o((String)localObject1, arrayOfAg[i4].M);
                  }
                }
                arrayOfAg[i4].d(paramAs);
              }
              else
              {
                arrayOfAg[i4].d("");
              }
              paramInt1 += 1;
              arrayOfAg[i4].w = true;
            }
          }
          i4 += 1;
        }
        i3 += 1;
      }
    }
    i1 = 0;
    while (i1 < paramInt1)
    {
      if ((!arrayOfAg[i1].w) && (!arrayOfAg[i1].Q.equals("")) && (!arrayOfAg[i1].P.equals("")))
      {
        localObject1 = a(arrayOfAg[i1].Q, arrayOfAg[i1].O, arrayOfAg[i1].P, arrayOfAg[i1].H);
        if (arrayOfAg[i1].N == 4)
        {
          paramAs = CUtil.k((String)localObject1, arrayOfAg[i1].M);
        }
        else if (arrayOfAg[i1].N == 5)
        {
          paramAs = CUtil.m((String)localObject1, arrayOfAg[i1].M);
        }
        else
        {
          paramAs = (as)localObject1;
          if (arrayOfAg[i1].N == 6) {
            paramAs = CUtil.o((String)localObject1, arrayOfAg[i1].M);
          }
        }
        arrayOfAg[i1].d(paramAs);
        arrayOfAg[i1].w = true;
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < i2)
    {
      if (!arrayOfAu[i1].w) {
        if ((arrayOfAu[i1].ao) && ((arrayOfAu[i1].t == 3) || (arrayOfAu[i1].t == 4) || (arrayOfAu[i1].t == 8)))
        {
          arrayOfAu[i1].r();
          arrayOfAu[i1].w = true;
        }
        else if (arrayOfAu[i1].t == 5)
        {
          arrayOfAu[i1].d();
          arrayOfAu[i1].w = true;
        }
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramInt1)
    {
      if ((!arrayOfAg[i1].w) && (!arrayOfAg[i1].H.equals("")) && (arrayOfAg[i1].Q.equals("")))
      {
        if (arrayOfAg[i1].t == 2) {
          arrayOfAg[i1].d(CalculaExpressaoNum(arrayOfAg[i1].H, null));
        } else {
          arrayOfAg[i1].d(CalculaExpressaoStr(arrayOfAg[i1].H, null));
        }
        arrayOfAg[i1].w = true;
      }
      i1 += 1;
    }
    if (!((String)localObject2).equals(""))
    {
      paramAs = new StringBuilder();
      paramAs.append("Error refreshing controls!");
      paramAs.append((String)localObject2);
      CDadosCarregados.a(paramEc, -2, paramAs.toString(), "1.1", paramInt2);
    }
    else
    {
      CDadosCarregados.a(paramEc);
    }
    localR1.a();
    localR2.a();
    localR3.a();
  }
  
  void a(String paramString)
  {
    this.V = false;
    this.W = false;
    this.X = false;
    this.Y = false;
    this.Z = false;
    this.aa = false;
    this.ab = false;
    this.ac = false;
    this.ad = false;
    this.ae = false;
    this.af = false;
    this.ag = false;
    this.ah = false;
    this.ai = false;
    this.aj = false;
    this.ak = false;
    this.al = false;
    this.am = false;
    this.an = false;
    this.ao = false;
    this.ap = false;
    this.aq = false;
    this.ar = false;
    this.as = false;
    this.at = false;
    this.au = false;
    this.av = false;
    this.aw = false;
    this.ax = false;
    this.ay = false;
    this.az = false;
    this.aA = false;
    this.aB = false;
    this.aC = false;
    this.aD = false;
    this.aE = false;
    this.aF = false;
    this.aG = false;
    this.aH = false;
    this.aI = false;
    this.aJ = false;
    if (paramString.equals("")) {
      return;
    }
    paramString = new CMyToken(paramString, CDadosCarregados.c);
    while (paramString.HasTokens())
    {
      String str = paramString.GetNextToken();
      if (str.compareToIgnoreCase("X1") == 0) {
        this.V = true;
      } else if (str.compareToIgnoreCase("X4") == 0) {
        this.W = true;
      } else if (str.compareToIgnoreCase("X5") == 0) {
        this.X = true;
      } else if (str.compareToIgnoreCase("X13") == 0) {
        this.Y = true;
      } else if (str.compareToIgnoreCase("X14") == 0) {
        this.Z = true;
      } else if (str.compareToIgnoreCase("X15") == 0) {
        this.aa = true;
      } else if (str.compareToIgnoreCase("X18") == 0) {
        this.ab = true;
      } else if (str.compareToIgnoreCase("X19") == 0) {
        this.ac = true;
      } else if (str.compareToIgnoreCase("X24") == 0) {
        this.ad = true;
      } else if (str.compareToIgnoreCase("S_(1)") == 0) {
        this.ae = true;
      } else if (str.compareToIgnoreCase("S_(2)") == 0) {
        this.af = true;
      } else if (str.compareToIgnoreCase("S_(3)") == 0) {
        this.ag = true;
      } else if (str.compareToIgnoreCase("S_(4)") == 0) {
        this.ah = true;
      } else if (str.compareToIgnoreCase("S_(5)") == 0) {
        this.ai = true;
      } else if (str.compareToIgnoreCase("S_(6)") == 0) {
        this.aj = true;
      } else if (str.compareToIgnoreCase("S_(7)") == 0) {
        this.ak = true;
      } else if (str.compareToIgnoreCase("S_(8)") == 0) {
        this.al = true;
      } else if (str.compareToIgnoreCase("S_(9)") == 0) {
        this.am = true;
      } else if (str.compareToIgnoreCase("S_(10)") == 0) {
        this.an = true;
      } else if (str.compareToIgnoreCase("S_(11)") == 0) {
        this.ao = true;
      } else if (str.compareToIgnoreCase("S_(12)") == 0) {
        this.ap = true;
      } else if (str.compareToIgnoreCase("S_(13)") == 0) {
        this.aq = true;
      } else if (str.compareToIgnoreCase("S_(14)") == 0) {
        this.ar = true;
      } else if (str.compareToIgnoreCase("S_(15)") == 0) {
        this.as = true;
      } else if (str.compareToIgnoreCase("S_(16)") == 0) {
        this.at = true;
      } else if (str.compareToIgnoreCase("A_(1)") == 0) {
        this.au = true;
      } else if (str.compareToIgnoreCase("A_(2)") == 0) {
        this.av = true;
      } else if (str.compareToIgnoreCase("A_(3)") == 0) {
        this.aw = true;
      } else if (str.compareToIgnoreCase("A_(4)") == 0) {
        this.ax = true;
      } else if (str.compareToIgnoreCase("A_(5)") == 0) {
        this.ay = true;
      } else if (str.compareToIgnoreCase("A_(6)") == 0) {
        this.az = true;
      } else if (str.compareToIgnoreCase("A_(7)") == 0) {
        this.aA = true;
      } else if (str.compareToIgnoreCase("A_(8)") == 0) {
        this.aB = true;
      } else if (str.compareToIgnoreCase("A_(9)") == 0) {
        this.aC = true;
      } else if (str.compareToIgnoreCase("A_(10)") == 0) {
        this.aD = true;
      } else if (str.compareToIgnoreCase("A_(11)") == 0) {
        this.aE = true;
      } else if (str.compareToIgnoreCase("N_(1)") == 0) {
        this.aF = true;
      } else if (str.compareToIgnoreCase("N_(2)") == 0) {
        this.aG = true;
      } else if (str.compareToIgnoreCase("N_(3)") == 0) {
        this.aH = true;
      } else if (str.compareToIgnoreCase("N_(4)") == 0) {
        this.aI = true;
      } else if (str.compareToIgnoreCase("V_(2)") == 0) {
        this.aJ = true;
      }
    }
  }
  
  void a(String paramString1, String paramString2, cj paramCj, dw paramDw)
  {
    if ((!paramString1.equals("")) && (!paramString2.equals("")))
    {
      String str = ah.a(paramString1);
      r localR = new r(CDadosCarregados.ay);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Select TBCTYP, TBCMSK From ");
      localStringBuilder.append(str);
      localStringBuilder.append("MFTBC, ");
      localStringBuilder.append(str);
      localStringBuilder.append("MFTAB Where TABTAB = '");
      localStringBuilder.append(paramString1);
      localStringBuilder.append("' And TBCIDO = TABIDO And TBCCOL = '");
      localStringBuilder.append(paramString2);
      localStringBuilder.append("'");
      if (!localR.a(localStringBuilder.toString()))
      {
        paramCj.a = 1;
        paramDw.a = "";
      }
      else
      {
        localR.d();
        if (localR.f())
        {
          paramCj.a = 1;
          paramDw.a = "";
        }
        else
        {
          paramCj.a = CUtil.StringToInt(localR.b(0));
          paramDw.a = localR.b(1);
        }
      }
      localR.a();
      return;
    }
    paramCj.a = 1;
    paramDw.a = "";
  }
  
  void a(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, as paramAs)
  {
    int i3 = paramAs.b;
    Object localObject = paramAs.a;
    int i2 = 0;
    int i1 = 0;
    while (i1 < i3)
    {
      localObject[i1].a(paramBoolean, paramInt1, paramInt2, paramInt3);
      i1 += 1;
    }
    i3 = paramAs.B;
    paramAs = paramAs.A;
    i1 = i2;
    while (i1 < i3)
    {
      localObject = paramAs[i1].o();
      if (localObject != null) {
        a(paramBoolean, paramInt1, paramInt2, paramInt3, (as)localObject);
      }
      i1 += 1;
    }
  }
  
  boolean a(int paramInt, b paramB)
  {
    Object localObject1 = this.bb[paramInt].b;
    if (this.bb[paramInt].c.equals("2.4"))
    {
      localObject1 = new dw((String)localObject1);
      this.bb[paramInt].f = this.a.a(this.bb[paramInt].d, (dw)localObject1, this.bb[paramInt].A, this.bb[paramInt].B, this.bb[paramInt].C, c(paramB));
      this.bb[paramInt].g = this.a.a(this.bb[paramInt].e, (dw)localObject1, this.bb[paramInt].A, this.bb[paramInt].B, this.bb[paramInt].C, c(paramB));
      localObject2 = ((dw)localObject1).a;
      localObject3 = CUtil.n(this.bb[paramInt].f);
      if (CUtil.b((String)localObject3, 8).equalsIgnoreCase("ORDER BY"))
      {
        localObject1 = this.bb[paramInt];
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(" 1=1 ");
        ((StringBuilder)localObject3).append(this.bb[paramInt].f);
        ((dz)localObject1).f = ((StringBuilder)localObject3).toString();
        localObject1 = localObject2;
      }
      else if (CUtil.b((String)localObject3, 8).equalsIgnoreCase("GROUP BY"))
      {
        localObject1 = this.bb[paramInt];
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(" 1=1 ");
        ((StringBuilder)localObject3).append(this.bb[paramInt].f);
        ((dz)localObject1).f = ((StringBuilder)localObject3).toString();
        localObject1 = localObject2;
      }
      else if (CUtil.b((String)localObject3, 9).equalsIgnoreCase("ORDER  BY"))
      {
        localObject1 = this.bb[paramInt];
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append(" 1=1 ");
        ((StringBuilder)localObject3).append(this.bb[paramInt].f);
        ((dz)localObject1).f = ((StringBuilder)localObject3).toString();
        localObject1 = localObject2;
      }
      else
      {
        localObject1 = localObject2;
        if (CUtil.b((String)localObject3, 9).equalsIgnoreCase("GROUP  BY"))
        {
          localObject1 = this.bb[paramInt];
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(" 1=1 ");
          ((StringBuilder)localObject3).append(this.bb[paramInt].f);
          ((dz)localObject1).f = ((StringBuilder)localObject3).toString();
          localObject1 = localObject2;
        }
      }
    }
    else
    {
      localObject1 = new dw((String)localObject1);
      this.bb[paramInt].f = this.a.a(this.bb[paramInt].d, null, 1, 1, (dw)localObject1, this.bb[paramInt].A, this.bb[paramInt].B, "", null, 0, null, 0);
      this.bb[paramInt].g = CalculaExpressaoStr(this.bb[paramInt].e, null);
      localObject1 = ((dw)localObject1).a;
    }
    Object localObject2 = localObject1;
    if (!this.bb[paramInt].f.equals(""))
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(" Where ");
      ((StringBuilder)localObject2).append(this.bb[paramInt].f);
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    localObject1 = localObject2;
    if (!this.bb[paramInt].g.equals(""))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append(" Order By ");
      ((StringBuilder)localObject1).append(this.bb[paramInt].g);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    if (!this.bb[paramInt].h)
    {
      if ((this.bb[paramInt].p == CDadosCarregados.a.d) && (!CDadosCarregados.a(this.bb[paramInt].r)))
      {
        localObject2 = CDadosCarregados.a(this.bb[paramInt].r, this.bb[paramInt].D, this.bb[paramInt].t);
        if (!((String)localObject2).equals(""))
        {
          localObject1 = e(paramB);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Error executing query on table! Unable to connect to SQLite database!\r\n");
          ((StringBuilder)localObject3).append((String)localObject2);
          CDadosCarregados.a((ec)localObject1, -101, ((StringBuilder)localObject3).toString(), "1.1177", b(paramB));
          return false;
        }
      }
      if (this.bb[paramInt].F.d != null)
      {
        ((r)this.bb[paramInt].F.d).a();
        this.bb[paramInt].F.d = null;
      }
      this.bb[paramInt].E = false;
      localObject2 = new r(CDadosCarregados.az);
      if (!((r)localObject2).a((String)localObject1))
      {
        localObject3 = e(paramB);
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("Error executing query on table!\r\n");
        ((StringBuilder)localObject4).append(((r)localObject2).c);
        ((StringBuilder)localObject4).append("\r\n");
        ((StringBuilder)localObject4).append((String)localObject1);
        CDadosCarregados.a((ec)localObject3, -103, ((StringBuilder)localObject4).toString(), "1.1178", b(paramB));
        ((r)localObject2).a();
        return false;
      }
      c(paramB).recordCount.a = ((r)localObject2).b();
      this.bb[paramInt].F.e = this.bb[paramInt].h;
      if (((r)localObject2).b() > 0) {
        this.bb[paramInt].F.d = localObject2;
      }
      this.bb[paramInt].F.a = ((r)localObject2).b();
      this.bb[paramInt].F.b = ((r)localObject2).c();
      this.bb[paramInt].F.c = -1;
      this.bb[paramInt].E = true;
      return true;
    }
    if (this.bb[paramInt].F.d != null) {
      this.bb[paramInt].F.d = null;
    }
    this.bb[paramInt].E = false;
    localObject2 = new dw("");
    Object localObject4 = new cj(0);
    Object localObject3 = new dw("");
    int i2 = CDadosCarregados.aK.a((cj)localObject4, CUtil.StringToInt(this.bb[paramInt].j), this.bb[paramInt].i, CUtil.StringToInt(this.bb[paramInt].k), CUtil.StringToInt(this.bb[paramInt].l), CUtil.StringToInt(this.bb[paramInt].m), this.bb[paramInt].n, this.bb[paramInt].o, "", CUtil.StringToInt(CDadosCarregados.aH), CDadosCarregados.aJ, this.bb[paramInt].p, this.bb[paramInt].q, this.bb[paramInt].r, this.bb[paramInt].s, this.bb[paramInt].t, this.bb[paramInt].u, this.bb[paramInt].v, this.bb[paramInt].w, this.bb[paramInt].x, this.bb[paramInt].y, this.bb[paramInt].z, (dw)localObject3, CDadosCarregados.aB);
    int i1 = ((cj)localObject4).a;
    if (i2 != 0)
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error executing query on table! Unable to connect to remote database!\r\n");
      ((StringBuilder)localObject2).append(((dw)localObject3).a);
      CDadosCarregados.a((ec)localObject1, 65335, ((StringBuilder)localObject2).toString(), "1.1179", b(paramB));
      return false;
    }
    i2 = CDadosCarregados.aK.a(i1, (String)localObject1);
    if (i2 < 0)
    {
      CDadosCarregados.aK.a(i1, (dw)localObject2);
      CDadosCarregados.aK.g(i1);
      localObject3 = e(paramB);
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("Error connecting to remote database!\r\n");
      ((StringBuilder)localObject4).append(((dw)localObject2).a);
      ((StringBuilder)localObject4).append("\r\n");
      ((StringBuilder)localObject4).append((String)localObject1);
      CDadosCarregados.a((ec)localObject3, 65335, ((StringBuilder)localObject4).toString(), "1.1587", b(paramB));
      return false;
    }
    if (i2 > 0)
    {
      CDadosCarregados.aK.a(i1, (dw)localObject2);
      CDadosCarregados.aK.g(i1);
      localObject3 = e(paramB);
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("Error executing query on table! Error executing an SQL query in a remote database!\r\n");
      ((StringBuilder)localObject4).append(((dw)localObject2).a);
      ((StringBuilder)localObject4).append("\r\n");
      ((StringBuilder)localObject4).append((String)localObject1);
      CDadosCarregados.a((ec)localObject3, 65334, ((StringBuilder)localObject4).toString(), "1.1180", b(paramB));
      return false;
    }
    c(paramB).recordCount.a = CDadosCarregados.aK.c(i1);
    this.bb[paramInt].F.e = this.bb[paramInt].h;
    if (CDadosCarregados.aK.c(i1) > 0) {
      this.bb[paramInt].F.d = CDadosCarregados.aK.h(i1);
    }
    this.bb[paramInt].F.a = CDadosCarregados.aK.c(i1);
    this.bb[paramInt].F.b = CDadosCarregados.aK.b(i1);
    this.bb[paramInt].F.c = -1;
    this.bb[paramInt].E = true;
    CDadosCarregados.aK.g(i1);
    return true;
  }
  
  boolean a(b paramB, String paramString1, bw.a paramA, String paramString2, int paramInt1, int paramInt2, String paramString3, int paramInt3, String paramString4, boolean paramBoolean)
  {
    if (CDadosCarregados.bM != null)
    {
      CDadosCarregados.bM.a();
      CDadosCarregados.bM = null;
    }
    bw localBw = new bw();
    if (paramA == bw.a.c)
    {
      paramInt1 = localBw.a(paramString2, paramString1, paramBoolean, 3);
    }
    else if (paramA == bw.a.d)
    {
      paramInt1 = localBw.a(paramString2, paramInt1, paramString1, paramBoolean);
    }
    else
    {
      int i1;
      boolean bool;
      if (paramInt1 < 0)
      {
        i1 = paramInt1 * -1;
        bool = true;
      }
      else
      {
        bool = false;
        i1 = paramInt1;
      }
      if (!bool)
      {
        int i2 = 7;
        if (paramInt2 == 1)
        {
          paramInt1 = 110;
        }
        else if (paramInt2 == 2)
        {
          paramInt1 = 300;
        }
        else if (paramInt2 == 3)
        {
          paramInt1 = 600;
        }
        else if (paramInt2 == 4)
        {
          paramInt1 = 1200;
        }
        else if (paramInt2 == 5)
        {
          paramInt1 = 2400;
        }
        else if (paramInt2 == 6)
        {
          paramInt1 = 4800;
        }
        else if (paramInt2 == 7)
        {
          paramInt1 = 9600;
        }
        else if (paramInt2 == 8)
        {
          paramInt1 = 14400;
        }
        else if (paramInt2 == 9)
        {
          paramInt1 = 19200;
        }
        else if (paramInt2 == 10)
        {
          paramInt1 = 38400;
        }
        else if (paramInt2 == 11)
        {
          paramInt1 = 56000;
        }
        else if (paramInt2 == 12)
        {
          paramInt1 = 57600;
        }
        else if (paramInt2 == 13)
        {
          paramInt1 = 115200;
        }
        else if (paramInt2 == 14)
        {
          paramInt1 = 128000;
        }
        else
        {
          if (paramInt2 != 15) {
            break label453;
          }
          paramInt1 = 256000;
        }
        if (paramString3.equals("1"))
        {
          paramInt2 = i2;
        }
        else
        {
          if (!paramString3.equals("2")) {
            break label431;
          }
          paramInt2 = 8;
        }
        if (paramString4.equals("1")) {
          paramA = "NONE";
        }
        for (;;)
        {
          break label485;
          if (paramString4.equals("2"))
          {
            paramA = "EVEN";
          }
          else
          {
            if (!paramString4.equals("3")) {
              break;
            }
            paramA = "ODD";
          }
        }
        CDadosCarregados.a(e(paramB), 64935, "Invalid parity specified!", "1.2171", paramB.a);
        return false;
        label431:
        CDadosCarregados.a(e(paramB), 64935, "Invalid data bits specified!", "1.2170", paramB.a);
        return false;
        label453:
        CDadosCarregados.a(e(paramB), 64935, "Invalid baud rate specified!", "1.2169", paramB.a);
        return false;
      }
      paramA = "";
      paramInt1 = 0;
      paramInt2 = 0;
      label485:
      paramString3 = paramString2;
      if (paramString2.equals("")) {
        paramString3 = CUtil.g(i1);
      }
      paramInt1 = localBw.a(paramString3, paramInt1, paramInt2, paramInt3, paramA, bool, paramString1, paramBoolean, 3);
    }
    if (paramInt1 != 0)
    {
      paramString1 = e(paramB);
      paramA = new StringBuilder();
      paramA.append("Error connecting to scanner!\r\n");
      paramA.append(localBw.a);
      CDadosCarregados.a(paramString1, paramInt1, paramA.toString(), "1.2172", paramB.a);
      return false;
    }
    CDadosCarregados.bM = localBw;
    CDadosCarregados.a(e(paramB));
    return true;
  }
  
  boolean a(final String paramString, final int paramInt, final ao paramAo, final ec paramEc)
  {
    final e localE = new e(true);
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        try
        {
          paramAo.a(CUtil.Q(paramString), paramInt);
          return;
        }
        catch (Exception localException)
        {
          paramEc.a = -1;
          ec localEc = paramEc;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Error saving file!\r\n");
          localStringBuilder.append(localException.getMessage());
          localEc.b = localStringBuilder.toString();
          paramEc.c = "1.2025";
          localE.a = false;
        }
      }
    });
    return localE.a;
  }
  
  boolean a(final String paramString, final int paramInt, final bi paramBi, final ec paramEc)
  {
    final e localE = new e(true);
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        try
        {
          paramBi.a(CUtil.Q(paramString), paramInt);
          return;
        }
        catch (Exception localException)
        {
          paramEc.a = -1;
          ec localEc = paramEc;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Error saving file!\r\n");
          localStringBuilder.append(localException.getMessage());
          localEc.b = localStringBuilder.toString();
          paramEc.c = "1.138";
          localE.a = false;
        }
      }
    });
    return localE.a;
  }
  
  boolean a(String paramString1, int paramInt, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean[] paramArrayOfBoolean, String[] paramArrayOfString3, String paramString2)
  {
    ah localAh = new ah();
    if (!localAh.a(paramString2, "", paramString1, true))
    {
      CUtil.a("", localAh.z, this, this.bh);
      return false;
    }
    paramString2 = " Where ";
    int i1 = 0;
    int i2 = 1;
    while (i1 < paramInt)
    {
      Object localObject2 = paramArrayOfString3[i1];
      Object localObject1;
      if (paramArrayOfBoolean[i1] == 0) {
        if (i2 != 0) {
          if (localAh.a)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(paramString2);
            ((StringBuilder)localObject1).append(" \"");
            ((StringBuilder)localObject1).append(paramArrayOfString1[i1]);
            ((StringBuilder)localObject1).append("\" ");
            ((StringBuilder)localObject1).append(paramArrayOfString2[i1]);
            ((StringBuilder)localObject1).append(" '");
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append("' ");
            paramString2 = ((StringBuilder)localObject1).toString();
          }
          else
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append(paramString2);
            ((StringBuilder)localObject1).append(" ");
            ((StringBuilder)localObject1).append(paramArrayOfString1[i1]);
            ((StringBuilder)localObject1).append(" ");
            ((StringBuilder)localObject1).append(paramArrayOfString2[i1]);
            ((StringBuilder)localObject1).append(" '");
            ((StringBuilder)localObject1).append((String)localObject2);
            ((StringBuilder)localObject1).append("' ");
            paramString2 = ((StringBuilder)localObject1).toString();
          }
        }
      }
      for (;;)
      {
        i2 = 0;
        break label872;
        if (localAh.a)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(paramString2);
          ((StringBuilder)localObject1).append(" And \"");
          ((StringBuilder)localObject1).append(paramArrayOfString1[i1]);
          ((StringBuilder)localObject1).append("\" ");
          ((StringBuilder)localObject1).append(paramArrayOfString2[i1]);
          ((StringBuilder)localObject1).append(" '");
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append("' ");
          paramString2 = ((StringBuilder)localObject1).toString();
          break label872;
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append(" And ");
        ((StringBuilder)localObject1).append(paramArrayOfString1[i1]);
        ((StringBuilder)localObject1).append(" ");
        ((StringBuilder)localObject1).append(paramArrayOfString2[i1]);
        ((StringBuilder)localObject1).append(" '");
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append("' ");
        paramString2 = ((StringBuilder)localObject1).toString();
        break label872;
        localObject1 = localObject2;
        if (((String)localObject2).equals("")) {
          localObject1 = "0";
        }
        if (i2 == 0) {
          break;
        }
        if (localAh.a)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(paramString2);
          ((StringBuilder)localObject2).append(" \"");
          ((StringBuilder)localObject2).append(paramArrayOfString1[i1]);
          ((StringBuilder)localObject2).append("\" ");
          ((StringBuilder)localObject2).append(paramArrayOfString2[i1]);
          ((StringBuilder)localObject2).append(" ");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" ");
          paramString2 = ((StringBuilder)localObject2).toString();
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(paramString2);
          ((StringBuilder)localObject2).append(" ");
          ((StringBuilder)localObject2).append(paramArrayOfString1[i1]);
          ((StringBuilder)localObject2).append(" ");
          ((StringBuilder)localObject2).append(paramArrayOfString2[i1]);
          ((StringBuilder)localObject2).append(" ");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" ");
          paramString2 = ((StringBuilder)localObject2).toString();
        }
      }
      if (localAh.a)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramString2);
        ((StringBuilder)localObject2).append(" And \"");
        ((StringBuilder)localObject2).append(paramArrayOfString1[i1]);
        ((StringBuilder)localObject2).append("\" ");
        ((StringBuilder)localObject2).append(paramArrayOfString2[i1]);
        ((StringBuilder)localObject2).append(" ");
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(" ");
        paramString2 = ((StringBuilder)localObject2).toString();
      }
      else
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramString2);
        ((StringBuilder)localObject2).append(" And ");
        ((StringBuilder)localObject2).append(paramArrayOfString1[i1]);
        ((StringBuilder)localObject2).append(" ");
        ((StringBuilder)localObject2).append(paramArrayOfString2[i1]);
        ((StringBuilder)localObject2).append(" ");
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(" ");
        paramString2 = ((StringBuilder)localObject2).toString();
      }
      label872:
      i1 += 1;
    }
    if (localAh.a)
    {
      if (localAh.b)
      {
        paramArrayOfString2 = new StringBuilder();
        paramArrayOfString2.append("Select \"");
        paramArrayOfString2.append(paramArrayOfString1[0]);
        paramArrayOfString2.append("\" From ");
        paramArrayOfString2.append(localAh.d);
        paramArrayOfString2.append("\"");
        paramArrayOfString2.append(paramString1);
        paramArrayOfString2.append("\"(nolock) ");
        paramArrayOfString2.append(paramString2);
        paramString1 = paramArrayOfString2.toString();
      }
      else
      {
        paramArrayOfString2 = new StringBuilder();
        paramArrayOfString2.append("Select \"");
        paramArrayOfString2.append(paramArrayOfString1[0]);
        paramArrayOfString2.append("\" From ");
        paramArrayOfString2.append(localAh.d);
        paramArrayOfString2.append("\"");
        paramArrayOfString2.append(paramString1);
        paramArrayOfString2.append("\" ");
        paramArrayOfString2.append(paramString2);
        paramString1 = paramArrayOfString2.toString();
      }
    }
    else if (localAh.b)
    {
      paramArrayOfString2 = new StringBuilder();
      paramArrayOfString2.append("Select ");
      paramArrayOfString2.append(paramArrayOfString1[0]);
      paramArrayOfString2.append(" From ");
      paramArrayOfString2.append(localAh.d);
      paramArrayOfString2.append(paramString1);
      paramArrayOfString2.append("(nolock) ");
      paramArrayOfString2.append(paramString2);
      paramString1 = paramArrayOfString2.toString();
    }
    else
    {
      paramArrayOfString2 = new StringBuilder();
      paramArrayOfString2.append("Select ");
      paramArrayOfString2.append(paramArrayOfString1[0]);
      paramArrayOfString2.append(" From ");
      paramArrayOfString2.append(localAh.d);
      paramArrayOfString2.append(paramString1);
      paramArrayOfString2.append(" ");
      paramArrayOfString2.append(paramString2);
      paramString1 = paramArrayOfString2.toString();
    }
    if (!localAh.b(paramString1))
    {
      paramString1 = new StringBuilder();
      paramString1.append("Error verifying if the value exists on a table!\r\n");
      paramString1.append(localAh.z);
      CUtil.a("", paramString1.toString(), this, this.bh);
      return false;
    }
    localAh.f();
    if (!localAh.h())
    {
      localAh.b();
      return true;
    }
    localAh.b();
    return false;
  }
  
  boolean a(String paramString1, final String paramString2, final String paramString3, final as paramAs, final TempVars paramTempVars)
  {
    final int i2 = CUtil.StringToInt(paramString1);
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        int i = 0;
        String str;
        int j;
        while (i < paramAs.l)
        {
          if (paramAs.k[i].f == i2)
          {
            if (paramString2.equals("1"))
            {
              str = CMyFormDlg.this.CalculaExpressaoStr(paramString3, paramTempVars);
              if (str.equals(""))
              {
                paramAs.k[i].h(0);
                return;
              }
              paramAs.k[i].d(str);
              return;
            }
            if (paramString2.equals("2"))
            {
              j = CUtil.StringToInt(CMyFormDlg.this.CalculaExpressaoNum(paramString3, paramTempVars));
              if (j >= 0) {
                paramAs.k[i].h(j);
              }
            }
            return;
          }
          i += 1;
        }
        i = 0;
        while (i < paramAs.j)
        {
          if (paramAs.i[i].f == i2)
          {
            if (paramString2.equals("1"))
            {
              str = CMyFormDlg.this.CalculaExpressaoStr(paramString3, paramTempVars);
              if (str.equals(""))
              {
                paramAs.i[i].h(0);
                return;
              }
              paramAs.i[i].d(str);
              return;
            }
            if (paramString2.equals("2"))
            {
              j = CUtil.StringToInt(CMyFormDlg.this.CalculaExpressaoNum(paramString3, paramTempVars));
              if (j >= 0) {
                paramAs.i[i].h(j);
              }
            }
            return;
          }
          i += 1;
        }
        i = 0;
        while (i < paramAs.p)
        {
          if (paramAs.o[i].f == i2)
          {
            if (paramString2.equals("1"))
            {
              str = CMyFormDlg.this.CalculaExpressaoStr(paramString3, paramTempVars);
              if (str.equals(""))
              {
                paramAs.o[i].h(0);
                return;
              }
              paramAs.o[i].d(str);
              return;
            }
            if (paramString2.equals("2"))
            {
              j = CUtil.StringToInt(CMyFormDlg.this.CalculaExpressaoNum(paramString3, paramTempVars));
              if (j >= 0) {
                paramAs.o[i].h(j);
              }
            }
            return;
          }
          i += 1;
        }
        i = 0;
        while (i < paramAs.n)
        {
          if (paramAs.m[i].f == i2)
          {
            if (paramString2.equals("1"))
            {
              str = CMyFormDlg.this.CalculaExpressaoStr(paramString3, paramTempVars);
              if (str.equals(""))
              {
                paramAs.m[i].h(0);
                return;
              }
              paramAs.m[i].d(str);
              return;
            }
            if (paramString2.equals("2"))
            {
              j = CUtil.StringToInt(CMyFormDlg.this.CalculaExpressaoNum(paramString3, paramTempVars));
              if (j >= 0) {
                paramAs.m[i].h(j);
              }
            }
            return;
          }
          i += 1;
        }
      }
    });
    final int i1 = 0;
    while (i1 < paramAs.t)
    {
      if (paramAs.s[i1].f == i2)
      {
        if (paramString2.equals("1")) {
          return false;
        }
        if (paramString2.equals("2"))
        {
          i2 = CUtil.StringToInt(CalculaExpressaoNum(paramString3, paramTempVars)) - 1;
          if (i2 >= -1)
          {
            paramAs.s[i1].m(i2);
            CUtil.a(true, this.bh, new de()
            {
              public void a()
              {
                paramAs.s[i1].a(i2, true, true);
              }
            });
          }
        }
        return true;
      }
      i1 += 1;
    }
    i1 = 0;
    while (i1 < paramAs.B)
    {
      if (paramAs.A[i1].f == i2)
      {
        if (paramString2.equals("1")) {
          return false;
        }
        if (paramString2.equals("2"))
        {
          i2 = CUtil.StringToInt(CalculaExpressaoNum(paramString3, paramTempVars)) - 1;
          if (i2 >= -1)
          {
            paramAs.A[i1].h(i2);
            CUtil.a(true, this.bh, new de()
            {
              public void a()
              {
                paramAs.A[i1].a(i2, true);
              }
            });
          }
        }
        return true;
      }
      as localAs = paramAs.A[i1].o();
      if ((localAs != null) && (!a(paramString1, paramString2, paramString3, localAs, paramTempVars))) {
        return false;
      }
      i1 += 1;
    }
    return true;
  }
  
  boolean a(String paramString1, String paramString2, boolean paramBoolean1, CDadosCarregados.a paramA, boolean paramBoolean2, dw paramDw1, dw paramDw2, dw paramDw3, dw paramDw4, cj paramCj1, cj paramCj2, b paramB)
  {
    Object localObject1 = paramDw1;
    r localR = new r(CDadosCarregados.ay);
    paramCj1.a = 0;
    paramCj2.a = 0;
    if (localObject1 != null) {
      ((dw)localObject1).a = "";
    }
    if (paramDw2 != null) {
      paramDw2.a = "";
    }
    if (paramDw3 != null) {
      paramDw3.a = "";
    }
    if (paramDw4 != null) {
      paramDw4.a = "";
    }
    Object localObject2;
    if ((paramBoolean1) && (paramA != CDadosCarregados.a.d))
    {
      if (localObject1 != null)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Insert Into ");
        ((StringBuilder)localObject2).append(paramString1);
        ((StringBuilder)localObject2).append("\"");
        ((StringBuilder)localObject2).append(paramString2);
        ((StringBuilder)localObject2).append("\" (M_SYN");
        ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
      }
      if (paramDw2 != null) {
        paramDw2.a = " Values('N'";
      }
      if (paramDw3 != null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Update ");
        ((StringBuilder)localObject1).append(paramString1);
        ((StringBuilder)localObject1).append("\"");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\" Set M_SYN = 'N'");
        paramDw3.a = ((StringBuilder)localObject1).toString();
      }
    }
    else
    {
      if (localObject1 != null) {
        if (paramBoolean2)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Insert Into ");
          ((StringBuilder)localObject2).append(paramString1);
          ((StringBuilder)localObject2).append("\"");
          ((StringBuilder)localObject2).append(paramString2);
          ((StringBuilder)localObject2).append("\" (");
          ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Insert Into ");
          ((StringBuilder)localObject2).append(paramString1);
          ((StringBuilder)localObject2).append(paramString2);
          ((StringBuilder)localObject2).append(" (");
          ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
        }
      }
      if (paramDw2 != null) {
        paramDw2.a = " Values(";
      }
      if (paramDw3 != null) {
        if (paramBoolean2)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Update ");
          ((StringBuilder)localObject1).append(paramString1);
          ((StringBuilder)localObject1).append("\"");
          ((StringBuilder)localObject1).append(paramString2);
          ((StringBuilder)localObject1).append("\" Set ");
          paramDw3.a = ((StringBuilder)localObject1).toString();
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Update ");
          ((StringBuilder)localObject1).append(paramString1);
          ((StringBuilder)localObject1).append(paramString2);
          ((StringBuilder)localObject1).append(" Set ");
          paramDw3.a = ((StringBuilder)localObject1).toString();
        }
      }
    }
    String str1 = ah.a(paramString2);
    int i1 = this.f.b;
    ag[] arrayOfAg = this.f.a;
    paramString1 = "";
    int i2 = 0;
    for (;;)
    {
      localObject1 = paramDw1;
      if (i2 >= i1) {
        break;
      }
      if (arrayOfAg[i2].L.equalsIgnoreCase(paramString2)) {
        if (!arrayOfAg[i2].K.equals(""))
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Select TBCTYP, TBCKEY, TBCIMK From ");
          ((StringBuilder)localObject2).append(str1);
          ((StringBuilder)localObject2).append("MFTBC, ");
          ((StringBuilder)localObject2).append(str1);
          ((StringBuilder)localObject2).append("MFTAB Where TABTAB = '");
          ((StringBuilder)localObject2).append(paramString2);
          ((StringBuilder)localObject2).append("' And TBCIDO = TABIDO And TBCCOL = '");
          ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
          ((StringBuilder)localObject2).append("'");
          if (!localR.a(((StringBuilder)localObject2).toString()))
          {
            paramString1 = e(paramB);
            paramA = new StringBuilder();
            paramA.append("Error reading the fields from the table ");
            paramA.append(paramString2);
            CDadosCarregados.a(paramString1, 65128, paramA.toString(), "1.14", b(paramB));
            localR.a();
            return false;
          }
          localR.d();
          if (!localR.f())
          {
            if ((paramBoolean1) && (paramA != CDadosCarregados.a.d))
            {
              if (localObject1 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(((dw)localObject1).a);
                ((StringBuilder)localObject2).append(", ");
                ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw2 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw2.a);
                ((StringBuilder)localObject2).append(", ");
                paramDw2.a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw3 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw3.a);
                ((StringBuilder)localObject2).append(", ");
                paramDw3.a = ((StringBuilder)localObject2).toString();
              }
            }
            else if (paramCj1.a != 0)
            {
              if (localObject1 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(((dw)localObject1).a);
                ((StringBuilder)localObject2).append(", ");
                ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw2 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw2.a);
                ((StringBuilder)localObject2).append(", ");
                paramDw2.a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw3 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw3.a);
                ((StringBuilder)localObject2).append(", ");
                paramDw3.a = ((StringBuilder)localObject2).toString();
              }
            }
            if (paramBoolean2)
            {
              if (localObject1 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(((dw)localObject1).a);
                ((StringBuilder)localObject2).append("\"");
                ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                ((StringBuilder)localObject2).append("\"");
                ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw3 != null)
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append(paramDw3.a);
                ((StringBuilder)localObject1).append("\"");
                ((StringBuilder)localObject1).append(arrayOfAg[i2].K);
                ((StringBuilder)localObject1).append("\" = ");
                paramDw3.a = ((StringBuilder)localObject1).toString();
              }
            }
            for (;;)
            {
              break;
              if (localObject1 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(((dw)localObject1).a);
                ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                ((dw)localObject1).a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw3 != null)
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append(paramDw3.a);
                ((StringBuilder)localObject1).append(arrayOfAg[i2].K);
                ((StringBuilder)localObject1).append(" = ");
                paramDw3.a = ((StringBuilder)localObject1).toString();
              }
            }
            int i3 = CUtil.StringToInt(localR.b(0));
            if ((i3 != 1) && (i3 != 4) && (i3 != 5) && (i3 != 6))
            {
              localObject2 = arrayOfAg[i2].d();
              if (((String)localObject2).equals(""))
              {
                localObject1 = "0";
              }
              else
              {
                localObject1 = localObject2;
                if (((String)localObject2).startsWith("."))
                {
                  localObject1 = new StringBuilder();
                  ((StringBuilder)localObject1).append("0");
                  ((StringBuilder)localObject1).append((String)localObject2);
                  localObject1 = ((StringBuilder)localObject1).toString();
                }
              }
              if (paramDw2 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw2.a);
                ((StringBuilder)localObject2).append((String)localObject1);
                paramDw2.a = ((StringBuilder)localObject2).toString();
              }
              if (paramDw3 != null)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw3.a);
                ((StringBuilder)localObject2).append((String)localObject1);
                paramDw3.a = ((StringBuilder)localObject2).toString();
              }
              if (localR.b(1).equals("1"))
              {
                if (paramDw4 != null)
                {
                  if (paramCj2.a != 0)
                  {
                    localObject2 = new StringBuilder();
                    ((StringBuilder)localObject2).append(paramDw4.a);
                    ((StringBuilder)localObject2).append(" And ");
                    paramDw4.a = ((StringBuilder)localObject2).toString();
                  }
                  if (paramBoolean2)
                  {
                    localObject2 = new StringBuilder();
                    ((StringBuilder)localObject2).append(paramDw4.a);
                    ((StringBuilder)localObject2).append(" \"");
                    ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                    ((StringBuilder)localObject2).append("\" = ");
                    ((StringBuilder)localObject2).append((String)localObject1);
                    paramDw4.a = ((StringBuilder)localObject2).toString();
                  }
                  else
                  {
                    localObject2 = new StringBuilder();
                    ((StringBuilder)localObject2).append(paramDw4.a);
                    ((StringBuilder)localObject2).append(" ");
                    ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                    ((StringBuilder)localObject2).append(" = ");
                    ((StringBuilder)localObject2).append((String)localObject1);
                    paramDw4.a = ((StringBuilder)localObject2).toString();
                  }
                }
                paramCj2.a += 1;
              }
              localObject2 = paramString1;
            }
            do
            {
              localObject1 = localObject2;
              break;
              String str2 = arrayOfAg[i2].a(false);
              if ((str2.equals("")) && (arrayOfAg[i2].u))
              {
                paramString1 = e(paramB);
                paramString2 = new StringBuilder();
                paramString2.append("It is necessary to fill the control linked to the field ");
                paramString2.append(arrayOfAg[i2].K);
                paramString2.append(" of the table ");
                paramString2.append(arrayOfAg[i2].L);
                CDadosCarregados.a(paramString1, 65234, paramString2.toString(), "1.15", b(paramB));
                localR.a();
                return false;
              }
              if (!paramBoolean1)
              {
                localObject2 = localR.b(2);
                if (i3 == 4)
                {
                  paramString1 = CUtil.j(str2, (String)localObject2);
                  localObject1 = localObject2;
                }
                else if (i3 == 5)
                {
                  paramString1 = CUtil.l(str2, (String)localObject2);
                  localObject1 = localObject2;
                }
                else
                {
                  paramString1 = str2;
                  localObject1 = localObject2;
                  if (i3 == 6)
                  {
                    paramString1 = CUtil.n(str2, (String)localObject2);
                    localObject1 = localObject2;
                  }
                }
              }
              else
              {
                localObject1 = paramString1;
                paramString1 = str2;
              }
              if ((i3 != 1) && (((i3 != 4) && (i3 != 5) && (i3 != 6)) || ((!paramBoolean1) && (((String)localObject1).indexOf('(') >= 0) && (((String)localObject1).indexOf(')') >= 0))))
              {
                if (paramDw2 != null)
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append(paramDw2.a);
                  ((StringBuilder)localObject2).append(paramString1);
                  paramDw2.a = ((StringBuilder)localObject2).toString();
                }
                if (paramDw3 != null)
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append(paramDw3.a);
                  ((StringBuilder)localObject2).append(paramString1);
                  paramDw3.a = ((StringBuilder)localObject2).toString();
                }
              }
              else
              {
                if (paramDw2 != null)
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append(paramDw2.a);
                  ((StringBuilder)localObject2).append("'");
                  ((StringBuilder)localObject2).append(paramString1);
                  ((StringBuilder)localObject2).append("'");
                  paramDw2.a = ((StringBuilder)localObject2).toString();
                }
                if (paramDw3 != null)
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append(paramDw3.a);
                  ((StringBuilder)localObject2).append("'");
                  ((StringBuilder)localObject2).append(paramString1);
                  ((StringBuilder)localObject2).append("'");
                  paramDw3.a = ((StringBuilder)localObject2).toString();
                }
              }
              localObject2 = localObject1;
            } while (!localR.b(1).equals("1"));
            if (paramDw4 != null)
            {
              if (paramCj2.a != 0)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw4.a);
                ((StringBuilder)localObject2).append(" And ");
                paramDw4.a = ((StringBuilder)localObject2).toString();
              }
              if ((i3 != 1) && (((i3 != 4) && (i3 != 5) && (i3 != 6)) || ((!paramBoolean1) && (((String)localObject1).indexOf('(') >= 0) && (((String)localObject1).indexOf(')') >= 0))))
              {
                if (paramBoolean2)
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append(paramDw4.a);
                  ((StringBuilder)localObject2).append(" \"");
                  ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                  ((StringBuilder)localObject2).append("\" = ");
                  ((StringBuilder)localObject2).append(paramString1);
                  paramDw4.a = ((StringBuilder)localObject2).toString();
                }
                else
                {
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append(paramDw4.a);
                  ((StringBuilder)localObject2).append(" ");
                  ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                  ((StringBuilder)localObject2).append(" = ");
                  ((StringBuilder)localObject2).append(paramString1);
                  paramDw4.a = ((StringBuilder)localObject2).toString();
                }
              }
              else if (paramBoolean2)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw4.a);
                ((StringBuilder)localObject2).append(" \"");
                ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                ((StringBuilder)localObject2).append("\" = '");
                ((StringBuilder)localObject2).append(paramString1);
                ((StringBuilder)localObject2).append("'");
                paramDw4.a = ((StringBuilder)localObject2).toString();
              }
              else
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append(paramDw4.a);
                ((StringBuilder)localObject2).append(" ");
                ((StringBuilder)localObject2).append(arrayOfAg[i2].K);
                ((StringBuilder)localObject2).append(" = '");
                ((StringBuilder)localObject2).append(paramString1);
                ((StringBuilder)localObject2).append("'");
                paramDw4.a = ((StringBuilder)localObject2).toString();
              }
            }
            paramCj2.a += 1;
            paramCj1.a += 1;
            paramString1 = (String)localObject1;
          }
        }
      }
      for (;;)
      {
        break;
        paramString1 = e(paramB);
        paramA = new StringBuilder();
        paramA.append("There is no field ");
        paramA.append(arrayOfAg[i2].K);
        paramA.append(" on the table ");
        paramA.append(paramString2);
        CDadosCarregados.a(paramString1, 65127, paramA.toString(), "1.16", b(paramB));
        return false;
      }
      i2 += 1;
    }
    localR.a();
    return true;
  }
  
  void aA(b paramB)
  {
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1727", paramB.a);
      return;
    }
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bH.c = localCMyToken.GetNextToken();
    CDadosCarregados.bH.d = localCMyToken.GetNextToken();
    int i1 = CDadosCarregados.bH.a(true);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bH.a, "1.1728", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aB(b paramB)
  {
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1734", paramB.a);
      return;
    }
    int i1 = CDadosCarregados.bH.a(false);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bH.a, "1.1735", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aC(b paramB)
  {
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1297", paramB.a);
      return;
    }
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    if (i1 == 0) {
      i1 = 5000;
    }
    localObject = ((CMyToken)localObject).GetNextToken();
    int i2 = ((String)localObject).length();
    boolean bool = true;
    if (i2 > 0)
    {
      if (CUtil.StringToInt(CalculaExpressaoNum((String)localObject, c(paramB))) != 1) {
        bool = false;
      }
    }
    else {
      bool = true;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bH.a(i1, bool, (dw)localObject, localCj, this.bh);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bH.a, "1.1300", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aD(b paramB)
  {
    String str = new CMyToken(a(paramB), b).GetNextToken();
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected to scanner!", "1.1290", paramB.a);
      return;
    }
    SetTarget(str, CUtil.a(CDadosCarregados.bH.b()), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aE(b paramB)
  {
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected to scanner!", "1.1292", paramB.a);
      return;
    }
    CDadosCarregados.bH.c();
    CDadosCarregados.a(e(paramB));
  }
  
  void aF(b paramB)
  {
    Object localObject3 = new CMyToken(a(paramB), b);
    Object localObject1 = ((CMyToken)localObject3).GetNextToken();
    Object localObject2 = ((CMyToken)localObject3).GetNextToken();
    String str1 = ((CMyToken)localObject3).GetNextToken();
    String str2 = ((CMyToken)localObject3).GetNextToken();
    String str3 = ((CMyToken)localObject3).GetNextToken();
    String str4 = ((CMyToken)localObject3).GetNextToken();
    localObject3 = ((CMyToken)localObject3).GetNextToken();
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected to scanner!", "1.1301", paramB.a);
      return;
    }
    int i1;
    if (((String)localObject1).length() > 0) {
      i1 = CUtil.StringToInt(CalculaExpressaoNum((String)localObject1, c(paramB)));
    } else {
      i1 = -1;
    }
    int i2;
    if (((String)localObject2).length() > 0) {
      i2 = CUtil.StringToInt(CalculaExpressaoNum((String)localObject2, c(paramB)));
    } else {
      i2 = -1;
    }
    int i3;
    if (str1.length() > 0) {
      i3 = CUtil.StringToInt(CalculaExpressaoNum(str1, c(paramB)));
    } else {
      i3 = -1;
    }
    int i4;
    if (str2.length() > 0) {
      i4 = CUtil.StringToInt(CalculaExpressaoNum(str2, c(paramB)));
    } else {
      i4 = -1;
    }
    int i5;
    if (str3.length() > 0) {
      i5 = CUtil.StringToInt(CalculaExpressaoNum(str3, c(paramB)));
    } else {
      i5 = -1;
    }
    int i6;
    if (str4.length() > 0) {
      i6 = CUtil.StringToInt(CalculaExpressaoNum(str4, c(paramB)));
    } else {
      i6 = -1;
    }
    int i7;
    if (((String)localObject3).length() > 0) {
      i7 = CUtil.StringToInt(CalculaExpressaoNum((String)localObject3, c(paramB)));
    } else {
      i7 = -1;
    }
    if (CDadosCarregados.bH.a(i1, i2, i3, i4, i5, i6, i7) != 0)
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error configuring scanner!\r\n");
      ((StringBuilder)localObject2).append(CDadosCarregados.bH.a);
      CDadosCarregados.a((ec)localObject1, 64631, ((StringBuilder)localObject2).toString(), "1.1302", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aG(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    if (CDadosCarregados.bH == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected to scanner!", "1.1323", paramB.a);
      return;
    }
    if (CDadosCarregados.bH.a(i1, (CMyToken)localObject, c(paramB), this) != 0)
    {
      localObject = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error configuring scanner!\r\n");
      localStringBuilder.append(CDadosCarregados.bH.a);
      CDadosCarregados.a((ec)localObject, 64631, localStringBuilder.toString(), "1.1325", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aH(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bH.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bH.a, "1.2226", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aI(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bH.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bH.a, "1.2227", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aJ(b paramB)
  {
    if (CDadosCarregados.bI == null) {
      CDadosCarregados.bI = new t();
    }
    int i1 = CDadosCarregados.bI.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bI.a, "1.1998", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aL(b paramB)
  {
    if (CDadosCarregados.bI == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2002", paramB.a);
      return;
    }
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bI.b = localCMyToken.GetNextToken();
    CDadosCarregados.bI.c = localCMyToken.GetNextToken();
    int i1 = CDadosCarregados.bI.a(true);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bI.a, "1.2223", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aM(b paramB)
  {
    if (CDadosCarregados.bI == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2003", paramB.a);
      return;
    }
    int i1 = CDadosCarregados.bI.a(false);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bI.a, "1.2224", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aN(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bI.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bI.a, "1.2005", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aO(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bI.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bI.a, "1.2006", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aP(b paramB)
  {
    if (CDadosCarregados.bI == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2004", paramB.a);
      return;
    }
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    if (i1 == 0) {
      i1 = 5000;
    }
    localObject = ((CMyToken)localObject).GetNextToken();
    int i2 = ((String)localObject).length();
    boolean bool = true;
    if (i2 > 0)
    {
      if (CUtil.StringToInt(CalculaExpressaoNum((String)localObject, c(paramB))) != 1) {
        bool = false;
      }
    }
    else {
      bool = true;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bI.a(i1, bool, (dw)localObject, localCj, this.bh);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bI.a, "1.2225", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aQ(b paramB)
  {
    if (CDadosCarregados.bA == null) {
      CDadosCarregados.bA = new aa();
    }
    int i1 = CDadosCarregados.bA.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bA.a(), "1.1475", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aS(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str = ((CMyToken)localObject).GetNextToken();
    localObject = ((CMyToken)localObject).GetNextToken();
    int i1 = CDadosCarregados.bA.a(true, str, (String)localObject);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bA.a(), "1.1479", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aT(b paramB)
  {
    int i1 = CDadosCarregados.bA.a(false, "", "");
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bA.a(), "1.1480", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aU(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bA.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bA.a(), "1.1482", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aV(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bA.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bA.a(), "1.1483", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aW(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool = true;
    if (i2 != 1) {
      bool = false;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bA.a(i1, bool, (dw)localObject, localCj);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bA.a(), "1.1481", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void aX(b paramB)
  {
    CDadosCarregados.bB.a();
    CDadosCarregados.bB = new bs();
    int i1 = CDadosCarregados.bB.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bB.c, "1.2188", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void aZ(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bB.a = localCMyToken.GetNextToken();
    CDadosCarregados.bB.b = localCMyToken.GetNextToken();
    int i1 = CDadosCarregados.bB.a(true);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bB.c, "1.2192", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void ab(b paramB)
  {
    if (CDadosCarregados.bx == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1930", paramB.a);
      return;
    }
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bv = localCMyToken.GetNextToken();
    CDadosCarregados.bw = localCMyToken.GetNextToken();
    try
    {
      CDadosCarregados.bx.b(this);
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error enabling scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64631, localStringBuilder.toString(), "1.1931", paramB.a);
    }
  }
  
  void ac(b paramB)
  {
    if (CDadosCarregados.bx == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1932", paramB.a);
      return;
    }
    try
    {
      CDadosCarregados.bx.c(this);
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error disabling scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64631, localStringBuilder.toString(), "1.1933", paramB.a);
    }
  }
  
  void ad(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool;
    if (CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB))) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    if (CDadosCarregados.bx == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1934", paramB.a);
      return;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bx.a(i1, bool, (dw)localObject, localCj, this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bx.c, "1.1935", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void ae(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    if (CDadosCarregados.bx == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1936", paramB.a);
      return;
    }
    int i1 = 0;
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bx.a((int[])localObject1, i1, this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bx.c, "1.1937", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void af(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    if (CDadosCarregados.bx == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1938", paramB.a);
      return;
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bx.a(localCi, localCj, this);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bx.c, "1.1939", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void ag(b paramB)
  {
    try
    {
      CDadosCarregados.by = new com.StarMicronics.jasura.b();
      CDadosCarregados.by.a();
      if (CDadosCarregados.bz == null) {
        CDadosCarregados.bz = new du();
      }
      CDadosCarregados.by.a(CDadosCarregados.bz);
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Error localError)
    {
      CDadosCarregados.by = null;
      localEc = e(paramB);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error connecting to scanner!\r\n");
      localStringBuilder.append(localError.getMessage());
      CDadosCarregados.a(localEc, 64626, localStringBuilder.toString(), "1.1894", paramB.a);
      return;
    }
    catch (Exception localException)
    {
      CDadosCarregados.by = null;
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error connecting to scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64626, localStringBuilder.toString(), "1.1893", paramB.a);
    }
  }
  
  void ai(b paramB)
  {
    if (CDadosCarregados.by == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1897", paramB.a);
      return;
    }
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bv = localCMyToken.GetNextToken();
    CDadosCarregados.bw = localCMyToken.GetNextToken();
    try
    {
      CDadosCarregados.by.a(b.a.c);
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error enabling scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64631, localStringBuilder.toString(), "1.1898", paramB.a);
    }
  }
  
  void aj(b paramB)
  {
    if (CDadosCarregados.by == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1899", paramB.a);
      return;
    }
    try
    {
      CDadosCarregados.by.a(b.a.a);
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error disabling scanner!\r\n");
      localStringBuilder.append(localException.getMessage());
      CDadosCarregados.a(localEc, 64631, localStringBuilder.toString(), "1.1900", paramB.a);
    }
  }
  
  /* Error */
  void ak(b paramB)
  {
    // Byte code:
    //   0: new 1216	com/sysdevsolutions/kclientlibv40/CMyToken
    //   3: dup
    //   4: aload_1
    //   5: invokestatic 985	com/sysdevsolutions/kclientlibv40/CMyFormDlg:a	(Lcom/sysdevsolutions/kclientlibv40/b;)Ljava/lang/String;
    //   8: getstatic 281	com/sysdevsolutions/kclientlibv40/CMyFormDlg:b	Ljava/lang/String;
    //   11: invokespecial 1219	com/sysdevsolutions/kclientlibv40/CMyToken:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   14: astore_3
    //   15: aload_3
    //   16: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   19: astore 5
    //   21: aload_3
    //   22: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   25: astore 6
    //   27: aload_0
    //   28: aload_3
    //   29: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   32: aload_1
    //   33: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   36: invokevirtual 1236	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoNum	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   39: invokestatic 1068	com/sysdevsolutions/kclientlibv40/CUtil:StringToInt	(Ljava/lang/String;)I
    //   42: istore_2
    //   43: aload_0
    //   44: aload_3
    //   45: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   48: aload_1
    //   49: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   52: invokevirtual 1236	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoNum	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   55: invokestatic 1068	com/sysdevsolutions/kclientlibv40/CUtil:StringToInt	(Ljava/lang/String;)I
    //   58: pop
    //   59: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   62: ifnonnull +24 -> 86
    //   65: aload_1
    //   66: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   69: sipush 64624
    //   72: ldc_w 570
    //   75: ldc_w 2826
    //   78: aload_1
    //   79: getfield 524	com/sysdevsolutions/kclientlibv40/b:a	I
    //   82: invokestatic 527	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;ILjava/lang/String;Ljava/lang/String;I)V
    //   85: return
    //   86: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   89: iload_2
    //   90: invokevirtual 2827	com/StarMicronics/jasura/b:a	(I)V
    //   93: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   96: iconst_0
    //   97: putfield 2828	com/sysdevsolutions/kclientlibv40/du:b	Z
    //   100: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   103: iconst_1
    //   104: putfield 2830	com/sysdevsolutions/kclientlibv40/du:c	Z
    //   107: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   110: invokevirtual 2833	com/StarMicronics/jasura/b:d	()Lcom/StarMicronics/jasura/b$a;
    //   113: astore 7
    //   115: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   118: getstatic 2835	com/StarMicronics/jasura/b$a:b	Lcom/StarMicronics/jasura/b$a;
    //   121: invokevirtual 2816	com/StarMicronics/jasura/b:a	(Lcom/StarMicronics/jasura/b$a;)V
    //   124: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   127: getfield 2837	com/sysdevsolutions/kclientlibv40/du:a	Ljava/lang/Object;
    //   130: astore_3
    //   131: aload_3
    //   132: monitorenter
    //   133: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   136: getfield 2837	com/sysdevsolutions/kclientlibv40/du:a	Ljava/lang/Object;
    //   139: iload_2
    //   140: bipush 100
    //   142: iadd
    //   143: i2l
    //   144: invokevirtual 1562	java/lang/Object:wait	(J)V
    //   147: aload_3
    //   148: monitorexit
    //   149: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   152: getfield 2828	com/sysdevsolutions/kclientlibv40/du:b	Z
    //   155: ifne +46 -> 201
    //   158: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   161: aload 7
    //   163: invokevirtual 2816	com/StarMicronics/jasura/b:a	(Lcom/StarMicronics/jasura/b$a;)V
    //   166: aload_1
    //   167: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   170: sipush 64620
    //   173: ldc_w 2839
    //   176: ldc_w 2841
    //   179: aload_1
    //   180: getfield 524	com/sysdevsolutions/kclientlibv40/b:a	I
    //   183: invokestatic 527	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;ILjava/lang/String;Ljava/lang/String;I)V
    //   186: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   189: iconst_0
    //   190: putfield 2828	com/sysdevsolutions/kclientlibv40/du:b	Z
    //   193: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   196: iconst_0
    //   197: putfield 2830	com/sysdevsolutions/kclientlibv40/du:c	Z
    //   200: return
    //   201: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   204: invokevirtual 2843	com/StarMicronics/jasura/b:c	()Ljava/lang/String;
    //   207: astore 4
    //   209: aload 4
    //   211: astore_3
    //   212: aload 4
    //   214: ifnonnull +7 -> 221
    //   217: ldc_w 304
    //   220: astore_3
    //   221: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   224: iconst_0
    //   225: putfield 2828	com/sysdevsolutions/kclientlibv40/du:b	Z
    //   228: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   231: iconst_0
    //   232: putfield 2830	com/sysdevsolutions/kclientlibv40/du:c	Z
    //   235: getstatic 597	com/sysdevsolutions/kclientlibv40/CDadosCarregados:by	Lcom/StarMicronics/jasura/b;
    //   238: aload 7
    //   240: invokevirtual 2816	com/StarMicronics/jasura/b:a	(Lcom/StarMicronics/jasura/b$a;)V
    //   243: aload_0
    //   244: aload 5
    //   246: aload_3
    //   247: iconst_1
    //   248: iconst_1
    //   249: aload_1
    //   250: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   253: invokevirtual 1409	com/sysdevsolutions/kclientlibv40/CMyFormDlg:SetTarget	(Ljava/lang/String;Ljava/lang/String;ZZLcom/sysdevsolutions/kclientlibv40/TempVars;)Z
    //   256: pop
    //   257: aload_0
    //   258: aload 6
    //   260: ldc_w 304
    //   263: iconst_1
    //   264: iconst_0
    //   265: aload_1
    //   266: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   269: invokevirtual 1409	com/sysdevsolutions/kclientlibv40/CMyFormDlg:SetTarget	(Ljava/lang/String;Ljava/lang/String;ZZLcom/sysdevsolutions/kclientlibv40/TempVars;)Z
    //   272: pop
    //   273: aload_1
    //   274: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   277: invokestatic 539	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;)V
    //   280: return
    //   281: astore 4
    //   283: aload_3
    //   284: monitorexit
    //   285: aload 4
    //   287: athrow
    //   288: astore_3
    //   289: aload_1
    //   290: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   293: astore 4
    //   295: new 578	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 579	java/lang/StringBuilder:<init>	()V
    //   302: astore 5
    //   304: aload 5
    //   306: ldc_w 2845
    //   309: invokevirtual 585	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: pop
    //   313: aload 5
    //   315: aload_3
    //   316: invokevirtual 588	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   319: invokevirtual 585	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: pop
    //   323: aload 4
    //   325: sipush 64633
    //   328: aload 5
    //   330: invokevirtual 591	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   333: ldc_w 2847
    //   336: aload_1
    //   337: getfield 524	com/sysdevsolutions/kclientlibv40/b:a	I
    //   340: invokestatic 527	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;ILjava/lang/String;Ljava/lang/String;I)V
    //   343: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   346: iconst_0
    //   347: putfield 2828	com/sysdevsolutions/kclientlibv40/du:b	Z
    //   350: getstatic 607	com/sysdevsolutions/kclientlibv40/CDadosCarregados:bz	Lcom/sysdevsolutions/kclientlibv40/du;
    //   353: iconst_0
    //   354: putfield 2830	com/sysdevsolutions/kclientlibv40/du:c	Z
    //   357: return
    //   358: astore_3
    //   359: aload_1
    //   360: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   363: astore 4
    //   365: new 578	java/lang/StringBuilder
    //   368: dup
    //   369: invokespecial 579	java/lang/StringBuilder:<init>	()V
    //   372: astore 5
    //   374: aload 5
    //   376: ldc_w 2849
    //   379: invokevirtual 585	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: pop
    //   383: aload 5
    //   385: aload_3
    //   386: invokevirtual 588	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   389: invokevirtual 585	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload 4
    //   395: sipush 64624
    //   398: aload 5
    //   400: invokevirtual 591	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   403: ldc_w 2851
    //   406: aload_1
    //   407: getfield 524	com/sysdevsolutions/kclientlibv40/b:a	I
    //   410: invokestatic 527	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;ILjava/lang/String;Ljava/lang/String;I)V
    //   413: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	414	0	this	CMyFormDlg
    //   0	414	1	paramB	b
    //   42	101	2	i1	int
    //   288	28	3	localException1	Exception
    //   358	28	3	localException2	Exception
    //   207	6	4	str1	String
    //   281	5	4	localObject2	Object
    //   293	101	4	localEc	ec
    //   19	380	5	localObject3	Object
    //   25	234	6	str2	String
    //   113	126	7	localA	b.a
    // Exception table:
    //   from	to	target	type
    //   133	149	281	finally
    //   283	285	281	finally
    //   107	133	288	java/lang/Exception
    //   149	200	288	java/lang/Exception
    //   201	209	288	java/lang/Exception
    //   221	280	288	java/lang/Exception
    //   285	288	288	java/lang/Exception
    //   86	93	358	java/lang/Exception
  }
  
  void al(b paramB)
  {
    if (CDadosCarregados.bu > 0) {
      am(paramB);
    }
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    int i1 = i2;
    if (i2 > 3) {
      i1 = 0;
    }
    if (i1 > 0)
    {
      localObject = bw.a.a;
      if (i1 == 1) {
        localObject = bw.a.b;
      }
      for (;;)
      {
        break;
        if (i1 == 2)
        {
          localObject = bw.a.c;
        }
        else
        {
          if (i1 != 3) {
            break;
          }
          localObject = bw.a.d;
        }
      }
      String str = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
      boolean bool;
      if (CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))) == 1) {
        bool = true;
      } else {
        bool = false;
      }
      if (a(paramB, str, (bw.a)localObject, CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB)), CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)), CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB))), CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)), bool)) {
        CDadosCarregados.bu = 14;
      }
      return;
    }
    Object localObject = paramB.a();
    ((b)localObject).b = "";
    bA((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 1;
      return;
    }
    bH((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 2;
      return;
    }
    ay((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 4;
      return;
    }
    aQ((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 6;
      return;
    }
    bs((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 7;
      return;
    }
    ag((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 8;
      return;
    }
    be((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 10;
      return;
    }
    Z((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 11;
      return;
    }
    aJ((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 12;
      return;
    }
    aX((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 15;
      return;
    }
    bl((b)localObject);
    if (e((b)localObject).a == 0)
    {
      CDadosCarregados.bu = 16;
      return;
    }
    CDadosCarregados.a(e(paramB), 64626, "No supported scanner found or error connecting to scanner!", "1.1452", paramB.a);
  }
  
  void an(b paramB)
  {
    if (CDadosCarregados.bu == 1)
    {
      bC(paramB);
      return;
    }
    if (CDadosCarregados.bu == 2)
    {
      bJ(paramB);
      return;
    }
    if (CDadosCarregados.bu == 4)
    {
      aA(paramB);
      return;
    }
    if (CDadosCarregados.bu == 6)
    {
      aS(paramB);
      return;
    }
    if (CDadosCarregados.bu == 7)
    {
      bu(paramB);
      return;
    }
    if (CDadosCarregados.bu == 8)
    {
      ai(paramB);
      return;
    }
    if (CDadosCarregados.bu == 10)
    {
      bg(paramB);
      return;
    }
    if (CDadosCarregados.bu == 11)
    {
      ab(paramB);
      return;
    }
    if (CDadosCarregados.bu == 12)
    {
      aL(paramB);
      return;
    }
    if (CDadosCarregados.bu == 14)
    {
      as(paramB);
      return;
    }
    if (CDadosCarregados.bu == 15)
    {
      aZ(paramB);
      return;
    }
    if (CDadosCarregados.bu == 16)
    {
      bn(paramB);
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1454", paramB.a);
  }
  
  void ao(b paramB)
  {
    if (CDadosCarregados.bu == 1)
    {
      bD(paramB);
      return;
    }
    if (CDadosCarregados.bu == 2)
    {
      bK(paramB);
      return;
    }
    if (CDadosCarregados.bu == 4)
    {
      aB(paramB);
      return;
    }
    if (CDadosCarregados.bu == 6)
    {
      aT(paramB);
      return;
    }
    if (CDadosCarregados.bu == 7)
    {
      bv(paramB);
      return;
    }
    if (CDadosCarregados.bu == 8)
    {
      aj(paramB);
      return;
    }
    if (CDadosCarregados.bu == 10)
    {
      bh(paramB);
      return;
    }
    if (CDadosCarregados.bu == 11)
    {
      ac(paramB);
      return;
    }
    if (CDadosCarregados.bu == 12)
    {
      aM(paramB);
      return;
    }
    if (CDadosCarregados.bu == 14)
    {
      at(paramB);
      return;
    }
    if (CDadosCarregados.bu == 15)
    {
      ba(paramB);
      return;
    }
    if (CDadosCarregados.bu == 16)
    {
      bo(paramB);
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.1460", paramB.a);
  }
  
  void ap(b paramB)
  {
    if (CDadosCarregados.bu == 1)
    {
      bE(paramB);
      return;
    }
    if (CDadosCarregados.bu == 2)
    {
      bL(paramB);
      return;
    }
    if (CDadosCarregados.bu == 4)
    {
      aH(paramB);
      return;
    }
    if (CDadosCarregados.bu == 6)
    {
      aU(paramB);
      return;
    }
    if (CDadosCarregados.bu == 7)
    {
      bw(paramB);
      return;
    }
    if (CDadosCarregados.bu == 10)
    {
      bi(paramB);
      return;
    }
    if (CDadosCarregados.bu == 11)
    {
      ae(paramB);
      return;
    }
    if (CDadosCarregados.bu == 12)
    {
      aN(paramB);
      return;
    }
    if (CDadosCarregados.bu == 14)
    {
      av(paramB);
      return;
    }
    if (CDadosCarregados.bu == 15)
    {
      bb(paramB);
      return;
    }
    if (CDadosCarregados.bu == 16)
    {
      bp(paramB);
      return;
    }
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    localObject1 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int[] arrayOfInt = new int[CUtil.a((String)localObject1, '\r') + 1];
    localObject1 = new CMyToken((String)localObject1, "\r");
    int i1 = 0;
    Object localObject2;
    while (((CMyToken)localObject1).HasTokens())
    {
      localObject2 = ((CMyToken)localObject1).GetNextToken().trim();
      if (!((String)localObject2).equals(""))
      {
        arrayOfInt[i1] = CUtil.StringToInt((String)localObject2);
        i1 += 1;
      }
    }
    Object localObject3 = "";
    int i2 = 0;
    while (i2 < i1)
    {
      localObject2 = "";
      if (arrayOfInt[i2] == 1) {
        localObject1 = com.google.zxing.a.h.toString();
      }
      Object localObject4;
      for (;;)
      {
        localObject4 = localObject2;
        localObject2 = localObject1;
        break label903;
        if (arrayOfInt[i2] == 2)
        {
          localObject1 = com.google.zxing.a.g.toString();
        }
        else if (arrayOfInt[i2] == 3)
        {
          localObject1 = com.google.zxing.a.o.toString();
        }
        else if (arrayOfInt[i2] == 4)
        {
          localObject1 = com.google.zxing.a.p.toString();
        }
        else if (arrayOfInt[i2] == 5)
        {
          localObject1 = com.google.zxing.a.h.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 6)
        {
          localObject1 = com.google.zxing.a.g.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 7)
        {
          localObject1 = com.google.zxing.a.o.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 8)
        {
          localObject1 = com.google.zxing.a.p.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 9)
        {
          localObject1 = com.google.zxing.a.h.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 10)
        {
          localObject1 = com.google.zxing.a.g.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 11)
        {
          localObject1 = com.google.zxing.a.o.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else if (arrayOfInt[i2] == 12)
        {
          localObject1 = com.google.zxing.a.p.toString();
          localObject2 = com.google.zxing.a.q.toString();
        }
        else
        {
          if (arrayOfInt[i2] != 13) {
            break;
          }
          localObject1 = com.google.zxing.a.c.toString();
        }
      }
      if (arrayOfInt[i2] == 15) {
        localObject1 = com.google.zxing.a.i.toString();
      }
      for (;;)
      {
        localObject4 = "";
        localObject2 = localObject1;
        break;
        if (arrayOfInt[i2] == 19) {
          localObject1 = com.google.zxing.a.b.toString();
        } else if (arrayOfInt[i2] == 23) {
          localObject1 = com.google.zxing.a.e.toString();
        } else if (arrayOfInt[i2] == 25) {
          localObject1 = com.google.zxing.a.d.toString();
        } else if (arrayOfInt[i2] == 29) {
          localObject1 = com.google.zxing.a.c.toString();
        } else if (arrayOfInt[i2] == 33) {
          localObject1 = com.google.zxing.a.k.toString();
        } else if (arrayOfInt[i2] == 34) {
          localObject1 = com.google.zxing.a.e.toString();
        } else if (arrayOfInt[i2] == 35) {
          localObject1 = com.google.zxing.a.e.toString();
        } else if (arrayOfInt[i2] == 37) {
          localObject1 = com.google.zxing.a.m.toString();
        } else if (arrayOfInt[i2] == 39) {
          localObject1 = com.google.zxing.a.n.toString();
        } else if (arrayOfInt[i2] == 40) {
          localObject1 = com.google.zxing.a.f.toString();
        } else if (arrayOfInt[i2] == 41) {
          localObject1 = com.google.zxing.a.l.toString();
        } else if (arrayOfInt[i2] == 42) {
          localObject1 = com.google.zxing.a.j.toString();
        } else if (arrayOfInt[i2] == 74) {
          localObject1 = com.google.zxing.a.a.toString();
        } else {
          localObject1 = "";
        }
      }
      label903:
      localObject1 = localObject3;
      if (!((String)localObject2).equals("")) {
        if (((String)localObject3).equals(""))
        {
          localObject1 = localObject2;
        }
        else
        {
          localObject1 = localObject3;
          if (((String)localObject3).indexOf((String)localObject2) < 0)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append((String)localObject3);
            ((StringBuilder)localObject1).append(",");
            ((StringBuilder)localObject1).append((String)localObject2);
            localObject1 = ((StringBuilder)localObject1).toString();
          }
        }
      }
      localObject2 = localObject1;
      if (!((String)localObject4).equals("")) {
        if (((String)localObject1).equals(""))
        {
          localObject2 = localObject4;
        }
        else
        {
          localObject2 = localObject1;
          if (((String)localObject1).indexOf((String)localObject4) < 0)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append(",");
            ((StringBuilder)localObject2).append((String)localObject4);
            localObject2 = ((StringBuilder)localObject2).toString();
          }
        }
      }
      i2 += 1;
      localObject3 = localObject2;
    }
    CDadosCarregados.E = (String)localObject3;
    CDadosCarregados.a(e(paramB));
  }
  
  void aq(b paramB)
  {
    if (CDadosCarregados.bu == 1)
    {
      bF(paramB);
      return;
    }
    if (CDadosCarregados.bu == 2)
    {
      bM(paramB);
      return;
    }
    if (CDadosCarregados.bu == 4)
    {
      aI(paramB);
      return;
    }
    if (CDadosCarregados.bu == 6)
    {
      aV(paramB);
      return;
    }
    if (CDadosCarregados.bu == 7)
    {
      bx(paramB);
      return;
    }
    if (CDadosCarregados.bu == 10)
    {
      bj(paramB);
      return;
    }
    if (CDadosCarregados.bu == 11)
    {
      af(paramB);
      return;
    }
    if (CDadosCarregados.bu == 12)
    {
      aO(paramB);
      return;
    }
    if (CDadosCarregados.bu == 14)
    {
      aw(paramB);
      return;
    }
    if (CDadosCarregados.bu == 15)
    {
      bc(paramB);
      return;
    }
    if (CDadosCarregados.bu == 16)
    {
      bq(paramB);
      return;
    }
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject3 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject3 = "\r";
    }
    Object localObject2 = "";
    int i2 = 0;
    String str3 = CDadosCarregados.E;
    if (str3.indexOf(com.google.zxing.a.h.toString()) >= 0)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append("1");
      localObject2 = ((StringBuilder)localObject1).toString();
      if (str3.indexOf(com.google.zxing.a.q.toString()) >= 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("5");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("9");
        localObject2 = ((StringBuilder)localObject2).toString();
        i2 = 3;
      }
      else
      {
        i2 = 1;
      }
    }
    localObject1 = localObject2;
    int i1 = i2;
    if (str3.indexOf(com.google.zxing.a.g.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("2");
      localObject2 = ((StringBuilder)localObject2).toString();
      i2 += 1;
      localObject1 = localObject2;
      i1 = i2;
      if (str3.indexOf(com.google.zxing.a.q.toString()) >= 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("6");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("10");
        localObject1 = ((StringBuilder)localObject2).toString();
        i1 = i2 + 1 + 1;
      }
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.o.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("3");
      localObject1 = ((StringBuilder)localObject1).toString();
      i1 += 1;
      localObject2 = localObject1;
      i2 = i1;
      if (str3.indexOf(com.google.zxing.a.q.toString()) >= 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("7");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("11");
        localObject2 = ((StringBuilder)localObject2).toString();
        i2 = i1 + 1 + 1;
      }
    }
    localObject1 = localObject2;
    i1 = i2;
    if (str3.indexOf(com.google.zxing.a.p.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("4");
      localObject2 = ((StringBuilder)localObject2).toString();
      i2 += 1;
      localObject1 = localObject2;
      i1 = i2;
      if (str3.indexOf(com.google.zxing.a.q.toString()) >= 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("8");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("12");
        localObject1 = ((StringBuilder)localObject2).toString();
        i1 = i2 + 1 + 1;
      }
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.c.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("13");
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append((String)localObject3);
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("29");
      localObject2 = ((StringBuilder)localObject2).toString();
      i2 = i1 + 1 + 1;
    }
    localObject1 = localObject2;
    i1 = i2;
    if (str3.indexOf(com.google.zxing.a.i.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("15");
      localObject1 = ((StringBuilder)localObject2).toString();
      i1 = i2 + 1;
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.b.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("19");
      localObject2 = ((StringBuilder)localObject1).toString();
      i2 = i1 + 1;
    }
    localObject1 = localObject2;
    i1 = i2;
    if (str3.indexOf(com.google.zxing.a.e.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("23");
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append((String)localObject3);
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("34");
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append((String)localObject3);
      localObject1 = ((StringBuilder)localObject2).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("35");
      localObject1 = ((StringBuilder)localObject2).toString();
      i1 = i2 + 1 + 1 + 1;
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.d.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("25");
      localObject2 = ((StringBuilder)localObject1).toString();
      i2 = i1 + 1;
    }
    localObject1 = localObject2;
    i1 = i2;
    if (str3.indexOf(com.google.zxing.a.k.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("33");
      localObject1 = ((StringBuilder)localObject2).toString();
      i1 = i2 + 1;
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.m.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("37");
      localObject2 = ((StringBuilder)localObject1).toString();
      i2 = i1 + 1;
    }
    localObject1 = localObject2;
    i1 = i2;
    if (str3.indexOf(com.google.zxing.a.n.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("39");
      localObject1 = ((StringBuilder)localObject2).toString();
      i1 = i2 + 1;
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.f.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("40");
      localObject2 = ((StringBuilder)localObject1).toString();
      i2 = i1 + 1;
    }
    localObject1 = localObject2;
    i1 = i2;
    if (str3.indexOf(com.google.zxing.a.l.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("41");
      localObject1 = ((StringBuilder)localObject2).toString();
      i1 = i2 + 1;
    }
    localObject2 = localObject1;
    i2 = i1;
    if (str3.indexOf(com.google.zxing.a.j.toString()) >= 0)
    {
      localObject2 = localObject1;
      if (i1 > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append((String)localObject3);
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append((String)localObject2);
      ((StringBuilder)localObject1).append("42");
      localObject2 = ((StringBuilder)localObject1).toString();
      i2 = i1 + 1;
    }
    if (str3.indexOf(com.google.zxing.a.a.toString()) >= 0)
    {
      localObject1 = localObject2;
      if (i2 > 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject2);
        ((StringBuilder)localObject1).append((String)localObject3);
        localObject1 = ((StringBuilder)localObject1).toString();
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("74");
      localObject1 = ((StringBuilder)localObject2).toString();
      i2 += 1;
    }
    else
    {
      localObject1 = localObject2;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void as(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    String str = localCMyToken.GetNextToken();
    localCMyToken.GetNextToken();
    if (CDadosCarregados.bM == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.2174", paramB.a);
      return;
    }
    int i1 = CDadosCarregados.bM.a(str);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bM.a, "1.2175", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void at(b paramB)
  {
    if (CDadosCarregados.bM == null)
    {
      CDadosCarregados.a(e(paramB), 64624, "Not connected!", "1.2176", paramB.a);
      return;
    }
    int i1 = CDadosCarregados.bM.b();
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bM.a, "1.2177", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void au(b paramB)
  {
    CDadosCarregados.a(e(paramB), 64619, "This function is not supported in Serial Barcode!", "1.2178", paramB.a);
  }
  
  void av(b paramB)
  {
    CDadosCarregados.a(e(paramB), 64619, "This function is not supported in Serial Barcode!", "1.2179", paramB.a);
  }
  
  void aw(b paramB)
  {
    CDadosCarregados.a(e(paramB), 64619, "This function is not supported in Serial Barcode!", "1.2180", paramB.a);
  }
  
  void ax(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    String str3 = localCMyToken.GetNextToken();
    boolean bool = CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)).equals("1");
    String str1 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    String str2 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    this.bi = true;
    if (CUtil.b(str3, 4).equalsIgnoreCase("FORM"))
    {
      if (bool)
      {
        this.bj[0] = CUtil.l(str1);
        this.bj[1] = CUtil.l(str2);
        this.bk = i1;
        this.bl = i2;
        if (this.bl <= 0) {
          this.bl = -1;
        }
        this.bm = false;
        new Thread(new Runnable()
        {
          public void run()
          {
            CMyFormDlg.this.n();
          }
        }).start();
        return;
      }
      this.bm = true;
      return;
    }
    int i3 = CUtil.StringToInt(CUtil.a("CTRL(", str3, ")"));
    paramB = this.f.b(i3);
    if (paramB != null)
    {
      paramB.a(bool, str1, str2, i1, i2);
      return;
    }
  }
  
  void ay(b paramB)
  {
    if (CDadosCarregados.bH == null) {
      CDadosCarregados.bH = new l();
    }
    int i1 = CDadosCarregados.bH.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bH.a, "1.1277", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public int b(int paramInt)
  {
    int i1 = this.n;
    if (CDadosCarregados.br == 3)
    {
      if (this.U[i1] < this.T[i1])
      {
        d1 = paramInt;
        d2 = this.U[i1];
        Double.isNaN(d1);
        return (int)Math.round(d1 * d2);
      }
      double d1 = paramInt;
      double d2 = this.T[i1];
      Double.isNaN(d1);
      return (int)Math.round(d1 * d2);
    }
    if (CDadosCarregados.br == 2) {
      return paramInt;
    }
    return Math.round(paramInt * CDadosCarregados.bs);
  }
  
  public int b(int paramInt1, int paramInt2)
  {
    return b(paramInt1, paramInt2, this.n);
  }
  
  public int b(int paramInt1, int paramInt2, int paramInt3)
  {
    if (CDadosCarregados.br == 3)
    {
      if (paramInt2 == 2)
      {
        d1 = paramInt1;
        d2 = this.U[paramInt3];
        Double.isNaN(d1);
        return (int)Math.round(d1 / d2);
      }
      double d1 = paramInt1;
      double d2 = this.T[paramInt3];
      Double.isNaN(d1);
      return (int)Math.round(d1 / d2);
    }
    if (CDadosCarregados.br == 2) {
      return paramInt1;
    }
    return Math.round(paramInt1 / CDadosCarregados.bs);
  }
  
  String b(String paramString1, boolean paramBoolean, String paramString2)
  {
    int i2 = this.f.b;
    ag[] arrayOfAg = this.f.a;
    Object localObject = "";
    int i1 = 0;
    while (i1 < i2)
    {
      if ((!arrayOfAg[i1].Q.equals("")) && (arrayOfAg[i1].Q.equalsIgnoreCase(paramString1)) && (arrayOfAg[i1].P.equals("")))
      {
        StringBuilder localStringBuilder;
        if (paramBoolean)
        {
          if (((String)localObject).equals(""))
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(paramString2);
            ((StringBuilder)localObject).append("\"");
            ((StringBuilder)localObject).append(paramString1);
            ((StringBuilder)localObject).append("\".\"");
            ((StringBuilder)localObject).append(arrayOfAg[i1].O);
            ((StringBuilder)localObject).append("\"");
            localObject = ((StringBuilder)localObject).toString();
          }
          else
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append((String)localObject);
            localStringBuilder.append(", ");
            localStringBuilder.append(paramString2);
            localStringBuilder.append("\"");
            localStringBuilder.append(paramString1);
            localStringBuilder.append("\".\"");
            localStringBuilder.append(arrayOfAg[i1].O);
            localStringBuilder.append("\"");
            localObject = localStringBuilder.toString();
          }
        }
        else if (((String)localObject).equals(""))
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(paramString2);
          ((StringBuilder)localObject).append(paramString1);
          ((StringBuilder)localObject).append(".");
          ((StringBuilder)localObject).append(arrayOfAg[i1].O);
          localObject = ((StringBuilder)localObject).toString();
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append((String)localObject);
          localStringBuilder.append(", ");
          localStringBuilder.append(paramString2);
          localStringBuilder.append(paramString1);
          localStringBuilder.append(".");
          localStringBuilder.append(arrayOfAg[i1].O);
          localObject = localStringBuilder.toString();
        }
      }
      i1 += 1;
    }
    return localObject;
  }
  
  public void b()
  {
    if (!this.al) {
      return;
    }
    if (CDadosCarregados.bn <= 0) {
      return;
    }
    this.aS.lock();
    this.aT -= 1;
    if (this.aT == 0)
    {
      this.aU = new Runnable()
      {
        public void run()
        {
          CMyFormDlg.this.aS.lock();
          CMyFormDlg.this.aU = null;
          CMyFormDlg.this.aS.unlock();
          CMyFormDlg.this.a();
          w localW = new w(CMyFormDlg.this.bh, CMyFormDlg.this);
          localW.d = false;
          localW.a(CMyFormDlg.this.j, -2, "S_(8)");
        }
      };
      this.bh.postDelayed(this.aU, CDadosCarregados.bn);
    }
    else if (this.aT < 0)
    {
      this.aT = 0;
    }
    this.aS.unlock();
  }
  
  void b(b paramB, int paramInt)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    Object localObject2 = ((CMyToken)localObject1).GetNextToken();
    Object localObject3 = ((CMyToken)localObject1).GetNextToken();
    if (((String)localObject2).equalsIgnoreCase("CURRENTCTRL"))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("CTRL(");
      ((StringBuilder)localObject1).append(CUtil.a(paramInt));
      ((StringBuilder)localObject1).append(")");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = localObject2;
      if (CUtil.b((String)localObject2, 8).equalsIgnoreCase("DYN_CTRL"))
      {
        localObject1 = CalculaExpressaoStr(CUtil.a((String)localObject2, 9, ((String)localObject2).length() - 10), c(paramB));
        paramInt = this.f.d((String)localObject1);
        if (paramInt < 0)
        {
          localObject2 = e(paramB);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Control \"");
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append("\"not found!");
          CDadosCarregados.a((ec)localObject2, -17, ((StringBuilder)localObject3).toString(), "1.1884", paramB.a);
          return;
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("CTRL(");
        ((StringBuilder)localObject1).append(CUtil.a(paramInt));
        ((StringBuilder)localObject1).append(")");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    int i1 = CUtil.StringToInt(CUtil.a("CTRL(", (String)localObject1, ")"));
    localObject2 = CalculaExpressaoStr((String)localObject3, c(paramB));
    int i2 = this.f.b;
    localObject3 = this.f.a;
    paramInt = 0;
    while (paramInt < i2)
    {
      if (localObject3[paramInt].f == i1)
      {
        localObject3[paramInt].h = ((String)localObject2);
        CDadosCarregados.a(e(paramB));
        return;
      }
      paramInt += 1;
    }
    localObject2 = e(paramB);
    localObject3 = new StringBuilder();
    ((StringBuilder)localObject3).append("Control \"");
    ((StringBuilder)localObject3).append((String)localObject1);
    ((StringBuilder)localObject3).append("\"not found!");
    CDadosCarregados.a((ec)localObject2, -17, ((StringBuilder)localObject3).toString(), "1.1446", paramB.a);
  }
  
  void b(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        String[] arrayOfString1 = new String[2];
        String[] arrayOfString2 = new String[2];
        boolean[] arrayOfBoolean = new boolean[2];
        boolean[] tmp18_16 = arrayOfBoolean;
        tmp18_16[0] = 1;
        boolean[] tmp23_18 = tmp18_16;
        tmp23_18[1] = 0;
        tmp23_18;
        arrayOfString1[0] = paramString1;
        arrayOfString2[0] = paramString2;
        arrayOfString1[1] = paramString3;
        arrayOfString2[1] = paramString4;
        int j = CMyFormDlg.this.f.f;
        Object localObject = CMyFormDlg.this.f.e;
        int i = 0;
        while (i < j)
        {
          if (localObject[i].aD.hasFocus())
          {
            if (!localObject[i].aB) {
              break;
            }
            w localW = new w(CMyFormDlg.this.bh, CMyFormDlg.this);
            localW.d = false;
            localW.a(CMyFormDlg.this.j, localObject[i].f, "S_(6)", arrayOfString1, arrayOfString2, arrayOfBoolean, null);
            return;
          }
          i += 1;
        }
        if (CMyFormDlg.this.aj)
        {
          localObject = new w(CMyFormDlg.this.bh, CMyFormDlg.this);
          ((w)localObject).d = false;
          ((w)localObject).a(CMyFormDlg.this.j, -2, "S_(6)", arrayOfString1, arrayOfString2, arrayOfBoolean, null);
          return;
        }
        CMyFormDlg.this.SetTarget(CDadosCarregados.bv, paramString2, true, true, null);
        CMyFormDlg.this.SetTarget(CDadosCarregados.bw, paramString4, true, false, null);
      }
    });
  }
  
  void bA(b paramB)
  {
    String str = CalculaExpressaoStr(new CMyToken(a(paramB), b).GetNextToken(), c(paramB));
    if (CDadosCarregados.bG != null) {
      CDadosCarregados.bG.a();
    }
    try
    {
      CDadosCarregados.bG = new al();
      int i1 = CDadosCarregados.bG.a(this, str);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bG.b, "1.1404", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Error localError)
    {
      localEc = e(paramB);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error initializing EMDK API!\r\n");
      localStringBuilder.append(CUtil.a(localError));
      CDadosCarregados.a(localEc, 64628, localStringBuilder.toString(), "1.1403", paramB.a);
      return;
    }
    catch (Exception localException)
    {
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error initializing EMDK API!\r\n");
      localStringBuilder.append(CUtil.a(localException));
      CDadosCarregados.a(localEc, 64628, localStringBuilder.toString(), "1.1958", paramB.a);
    }
  }
  
  void bC(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bG.e = localCMyToken.GetNextToken();
    CDadosCarregados.bG.f = localCMyToken.GetNextToken();
    if (CDadosCarregados.bG != null)
    {
      int i1 = CDadosCarregados.bG.a(true);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bG.b, "1.1407", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1959", paramB.a);
  }
  
  void bD(b paramB)
  {
    if (CDadosCarregados.bG != null)
    {
      int i1 = CDadosCarregados.bG.a(false);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bG.b, "1.1408", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1960", paramB.a);
  }
  
  void bE(b paramB)
  {
    if (CDadosCarregados.bG != null)
    {
      Object localObject1 = new CMyToken(a(paramB), b);
      ((CMyToken)localObject1).GetNextToken();
      Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
      int i1 = 0;
      int i2 = CUtil.a((String)localObject2, '\r') + 1;
      localObject1 = new int[i2];
      localObject2 = new CMyToken((String)localObject2, "\r");
      while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
      {
        String str = ((CMyToken)localObject2).GetNextToken();
        if (!str.equals(""))
        {
          localObject1[i1] = CUtil.StringToInt(str);
          i1 += 1;
        }
      }
      i1 = CDadosCarregados.bG.a((int[])localObject1, i1);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bG.b, "1.1410", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1961", paramB.a);
  }
  
  void bF(b paramB)
  {
    if (CDadosCarregados.bG != null)
    {
      Object localObject1 = new CMyToken(a(paramB), b);
      String str1 = ((CMyToken)localObject1).GetNextToken();
      String str2 = ((CMyToken)localObject1).GetNextToken();
      localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
      Object localObject2 = localObject1;
      if (((String)localObject1).equals("")) {
        localObject2 = "\r";
      }
      ci localCi = new ci(null);
      int i1 = 0;
      cj localCj = new cj(0);
      int i2 = CDadosCarregados.bG.a(localCi, localCj);
      if (i2 != 0)
      {
        CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bG.b, "1.1411", paramB.a);
        return;
      }
      localObject1 = "";
      int i3;
      for (i2 = 0; i1 < localCj.a; i2 = i3)
      {
        Object localObject3 = localObject1;
        i3 = i2;
        if (localCi.a[i1] != 0)
        {
          localObject3 = localObject1;
          if (i2 > 0)
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append((String)localObject1);
            ((StringBuilder)localObject3).append((String)localObject2);
            localObject3 = ((StringBuilder)localObject3).toString();
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject3);
          ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
          localObject3 = ((StringBuilder)localObject1).toString();
          i3 = i2 + 1;
        }
        i1 += 1;
        localObject1 = localObject3;
      }
      SetTarget(str1, (String)localObject1, true, true, c(paramB));
      SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1962", paramB.a);
  }
  
  void bG(b paramB)
  {
    if (CDadosCarregados.bG != null)
    {
      Object localObject = new CMyToken(a(paramB), b);
      String str1 = ((CMyToken)localObject).GetNextToken();
      String str2 = ((CMyToken)localObject).GetNextToken();
      int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
      int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
      boolean bool = true;
      if (i2 != 1) {
        bool = false;
      }
      localObject = new dw("");
      cj localCj = new cj(0);
      i1 = CDadosCarregados.bG.a(i1, bool, (dw)localObject, localCj);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bG.b, "1.1409", paramB.a);
        return;
      }
      SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
      SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.1963", paramB.a);
  }
  
  void bH(b paramB)
  {
    CalculaExpressaoStr(new CMyToken(a(paramB), b).GetNextToken(), c(paramB));
    int i1 = CDadosCarregados.bJ.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bJ.a, "1.1421", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bJ(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bJ.b = localCMyToken.GetNextToken();
    CDadosCarregados.bJ.c = localCMyToken.GetNextToken();
    int i1 = CDadosCarregados.bJ.a(true);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bJ.a, "1.1425", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bK(b paramB)
  {
    int i1 = CDadosCarregados.bJ.a(false);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bJ.a, "1.1426", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bL(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bJ.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bJ.a, "1.1428", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bM(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bJ.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bJ.a, "1.1429", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bN(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool = true;
    if (i2 != 1) {
      bool = false;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bJ.a(i1, bool, (dw)localObject, localCj);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bJ.a, "1.1427", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bO(b paramB)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        CMyFormDlg.this.k();
      }
    });
    this.aY = false;
  }
  
  void bP(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("DLL(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    localObject1 = CUtil.Q(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    if ((i1 >= 1) && (i1 <= 10))
    {
      i1 -= 1;
      CDadosCarregados.bh[i1] = NativeDll.LoadLibrary((String)localObject1);
      if (CDadosCarregados.bh[i1] == 0L)
      {
        localObject1 = NativeDll.GetLastError();
        localObject2 = e(paramB);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error Loading DLL!\r\n");
        localStringBuilder.append((String)localObject1);
        CDadosCarregados.a((ec)localObject2, 64735, localStringBuilder.toString(), "1.799", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    localObject1 = e(paramB);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Invalid DLL ID!\r\nID: ");
    ((StringBuilder)localObject2).append(CUtil.a(i1));
    CDadosCarregados.a((ec)localObject1, -6, ((StringBuilder)localObject2).toString(), "1.798", paramB.a);
  }
  
  void bQ(b paramB)
  {
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("DLL(", a(paramB), ")"), c(paramB)));
    if ((i1 >= 1) && (i1 <= 10))
    {
      i1 -= 1;
      if (!NativeDll.FreeLibrary(CDadosCarregados.bh[i1]))
      {
        localObject1 = NativeDll.GetLastError();
        localObject2 = e(paramB);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error Unloading DLL!\r\n");
        localStringBuilder.append((String)localObject1);
        CDadosCarregados.a((ec)localObject2, 64734, localStringBuilder.toString(), "1.801", paramB.a);
      }
      else
      {
        CDadosCarregados.a(e(paramB));
      }
      CDadosCarregados.bh[i1] = 0L;
      return;
    }
    Object localObject1 = e(paramB);
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Invalid DLL ID!\r\nID: ");
    ((StringBuilder)localObject2).append(CUtil.a(i1));
    CDadosCarregados.a((ec)localObject1, -6, ((StringBuilder)localObject2).toString(), "1.800", paramB.a);
  }
  
  void bR(final b paramB)
  {
    Object localObject5 = new CMyToken(a(paramB), b);
    Object localObject1 = ((CMyToken)localObject5).GetNextToken();
    String str = CalculaExpressaoStr(((CMyToken)localObject5).GetNextToken(), c(paramB));
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject5).GetNextToken(), c(paramB));
    CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject5).GetNextToken(), c(paramB)));
    final int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject5).GetNextToken(), c(paramB)));
    final int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject5).GetNextToken(), c(paramB)));
    Object localObject3 = ((CMyToken)localObject5).GetNextToken();
    Object localObject4 = ((CMyToken)localObject5).GetNextToken();
    CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject5).GetNextToken(), c(paramB)));
    final int i4 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject5).GetNextToken(), c(paramB)));
    localObject5 = CUtil.Q(str);
    int i1;
    if (!((String)localObject1).equals(""))
    {
      i1 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("DLL(", (String)localObject1, ")"), c(paramB)));
      if ((i1 >= 1) && (i1 <= 10))
      {
        i1 = -1 + i1;
        if (CDadosCarregados.bh[i1] == 0L)
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("DLL not loaded!\r\nID: ");
          ((StringBuilder)localObject2).append(CUtil.a(i1 + 1));
          CDadosCarregados.a((ec)localObject1, 64733, ((StringBuilder)localObject2).toString(), "1.803", paramB.a);
          return;
        }
      }
      else
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Invalid DLL ID!\r\nID: ");
        ((StringBuilder)localObject2).append(CUtil.a(i1));
        CDadosCarregados.a((ec)localObject1, -6, ((StringBuilder)localObject2).toString(), "1.802", paramB.a);
      }
    }
    else
    {
      i1 = -1;
    }
    final long l1;
    if (i1 < 0)
    {
      l1 = NativeDll.LoadLibrary((String)localObject5);
      if (l1 == 0L)
      {
        localObject1 = NativeDll.GetLastError();
        localObject2 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error Loading DLL!\r\n");
        ((StringBuilder)localObject3).append((String)localObject1);
        CDadosCarregados.a((ec)localObject2, 64735, ((StringBuilder)localObject3).toString(), "1.804", paramB.a);
        return;
      }
    }
    else
    {
      l1 = CDadosCarregados.bh[i1];
    }
    localObject1 = new dw("");
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        this.a.a = NativeDll.DllRun(CMyFormDlg.this, CMyFormDlg.c(paramB), l1, this.d, i4, i2, i3, this.h, this.i);
      }
    });
    if ((i1 < 0) && (!NativeDll.FreeLibrary(l1)))
    {
      localObject2 = NativeDll.GetLastError();
      localObject3 = e(paramB);
      localObject4 = new StringBuilder();
      ((StringBuilder)localObject4).append("Error Unlading DLL!\r\n");
      ((StringBuilder)localObject4).append((String)localObject2);
      CDadosCarregados.a((ec)localObject3, 64734, ((StringBuilder)localObject4).toString(), "1.801", paramB.a);
      i1 = 1;
    }
    else
    {
      i1 = 0;
    }
    if (((dw)localObject1).a.startsWith("0:"))
    {
      localObject2 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Error loading function from dll!\r\n");
      ((StringBuilder)localObject3).append(CUtil.c(((dw)localObject1).a, 2));
      CDadosCarregados.a((ec)localObject2, 64732, ((StringBuilder)localObject3).toString(), "1.805", paramB.a);
      return;
    }
    if (((dw)localObject1).a.startsWith("0"))
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error loading function from dll!\r\n");
      ((StringBuilder)localObject2).append(NativeDll.GetLastError());
      CDadosCarregados.a((ec)localObject1, 64732, ((StringBuilder)localObject2).toString(), "1.806", paramB.a);
      return;
    }
    if (i1 == 0) {
      CDadosCarregados.a(e(paramB));
    }
  }
  
  void bS(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    String str = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    Object localObject3 = ((CMyToken)localObject1).GetNextToken();
    int i1 = 0;
    while (i1 < this.f.D)
    {
      if (this.f.C[i1].f == i2)
      {
        localObject1 = this.f.C[i1];
        break label109;
      }
      i1 += 1;
    }
    localObject1 = null;
    label109:
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = (bl)this.f.b(i2);
    }
    if (localObject2 == null) {
      return;
    }
    if (((String)localObject3).equals(""))
    {
      localObject1 = ((bl)localObject2).a(str, null);
    }
    else
    {
      dw localDw = new dw("");
      localObject2 = ((bl)localObject2).a(str, localDw);
      localObject1 = localObject2;
      if (((String)localObject2).equals(""))
      {
        SetTarget((String)localObject3, localDw.a, true, true, c(paramB));
        localObject1 = localObject2;
      }
    }
    if (!((String)localObject1).equals(""))
    {
      localObject2 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Script execution error!\r\n");
      ((StringBuilder)localObject3).append((String)localObject1);
      CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2119", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bT(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    String str = ((CMyToken)localObject1).GetNextToken();
    int i1 = 0;
    while (i1 < this.f.D)
    {
      if (this.f.C[i1].f == i2)
      {
        localObject1 = this.f.C[i1];
        break label94;
      }
      i1 += 1;
    }
    localObject1 = null;
    label94:
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = (bl)this.f.b(i2);
    }
    if (localObject2 == null) {
      return;
    }
    localObject1 = new dw("");
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        this.a.a = this.b.r();
      }
    });
    SetTarget(str, ((dw)localObject1).a, true, true, c(paramB));
  }
  
  void bU(b paramB)
  {
    // Byte code:
    //   0: new 1216	com/sysdevsolutions/kclientlibv40/CMyToken
    //   3: dup
    //   4: aload_1
    //   5: invokestatic 985	com/sysdevsolutions/kclientlibv40/CMyFormDlg:a	(Lcom/sysdevsolutions/kclientlibv40/b;)Ljava/lang/String;
    //   8: getstatic 281	com/sysdevsolutions/kclientlibv40/CMyFormDlg:b	Ljava/lang/String;
    //   11: invokespecial 1219	com/sysdevsolutions/kclientlibv40/CMyToken:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   14: astore 9
    //   16: ldc_w 1264
    //   19: aload 9
    //   21: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   24: ldc_w 987
    //   27: invokestatic 990	com/sysdevsolutions/kclientlibv40/CUtil:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   30: invokestatic 1068	com/sysdevsolutions/kclientlibv40/CUtil:StringToInt	(Ljava/lang/String;)I
    //   33: istore_3
    //   34: aload_0
    //   35: aload 9
    //   37: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   40: aload_1
    //   41: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   44: invokevirtual 1236	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoNum	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   47: invokestatic 1068	com/sysdevsolutions/kclientlibv40/CUtil:StringToInt	(Ljava/lang/String;)I
    //   50: istore 4
    //   52: iconst_0
    //   53: istore_2
    //   54: iload_2
    //   55: aload_0
    //   56: getfield 302	com/sysdevsolutions/kclientlibv40/CMyFormDlg:f	Lcom/sysdevsolutions/kclientlibv40/as;
    //   59: getfield 3199	com/sysdevsolutions/kclientlibv40/as:D	I
    //   62: if_icmpge +40 -> 102
    //   65: aload_0
    //   66: getfield 302	com/sysdevsolutions/kclientlibv40/CMyFormDlg:f	Lcom/sysdevsolutions/kclientlibv40/as;
    //   69: getfield 3202	com/sysdevsolutions/kclientlibv40/as:C	[Lcom/sysdevsolutions/kclientlibv40/bl;
    //   72: iload_2
    //   73: aaload
    //   74: getfield 3205	com/sysdevsolutions/kclientlibv40/bl:f	I
    //   77: iload_3
    //   78: if_icmpne +17 -> 95
    //   81: aload_0
    //   82: getfield 302	com/sysdevsolutions/kclientlibv40/CMyFormDlg:f	Lcom/sysdevsolutions/kclientlibv40/as;
    //   85: getfield 3202	com/sysdevsolutions/kclientlibv40/as:C	[Lcom/sysdevsolutions/kclientlibv40/bl;
    //   88: iload_2
    //   89: aaload
    //   90: astore 5
    //   92: goto +13 -> 105
    //   95: iload_2
    //   96: iconst_1
    //   97: iadd
    //   98: istore_2
    //   99: goto -45 -> 54
    //   102: aconst_null
    //   103: astore 5
    //   105: aload 5
    //   107: astore 6
    //   109: aload 5
    //   111: ifnonnull +16 -> 127
    //   114: aload_0
    //   115: getfield 302	com/sysdevsolutions/kclientlibv40/CMyFormDlg:f	Lcom/sysdevsolutions/kclientlibv40/as;
    //   118: iload_3
    //   119: invokevirtual 1210	com/sysdevsolutions/kclientlibv40/as:b	(I)Lcom/sysdevsolutions/kclientlibv40/ag;
    //   122: checkcast 3204	com/sysdevsolutions/kclientlibv40/bl
    //   125: astore 6
    //   127: aload 6
    //   129: ifnonnull +4 -> 133
    //   132: return
    //   133: ldc_w 304
    //   136: astore 5
    //   138: iload 4
    //   140: iconst_1
    //   141: if_icmpne +219 -> 360
    //   144: aload_0
    //   145: aload 9
    //   147: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   150: aload_1
    //   151: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   154: invokevirtual 1236	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoNum	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   157: invokestatic 1068	com/sysdevsolutions/kclientlibv40/CUtil:StringToInt	(Ljava/lang/String;)I
    //   160: istore_2
    //   161: aload_0
    //   162: aload 9
    //   164: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   167: aload_1
    //   168: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   171: invokevirtual 1234	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoStr	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   174: astore 7
    //   176: aload_0
    //   177: aload 9
    //   179: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   182: aload_1
    //   183: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   186: invokevirtual 1234	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoStr	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   189: astore 8
    //   191: aload_0
    //   192: aload 9
    //   194: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   197: aload_1
    //   198: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   201: invokevirtual 1236	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoNum	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   204: invokestatic 1068	com/sysdevsolutions/kclientlibv40/CUtil:StringToInt	(Ljava/lang/String;)I
    //   207: istore_3
    //   208: aload_0
    //   209: aload 9
    //   211: invokevirtual 1222	com/sysdevsolutions/kclientlibv40/CMyToken:GetNextToken	()Ljava/lang/String;
    //   214: aload_1
    //   215: invokestatic 1232	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/TempVars;
    //   218: invokevirtual 1234	com/sysdevsolutions/kclientlibv40/CMyFormDlg:CalculaExpressaoStr	(Ljava/lang/String;Lcom/sysdevsolutions/kclientlibv40/TempVars;)Ljava/lang/String;
    //   221: astore 5
    //   223: iload_2
    //   224: iconst_2
    //   225: if_icmpne +119 -> 344
    //   228: iload_3
    //   229: iconst_2
    //   230: if_icmpne +20 -> 250
    //   233: new 3221	org/apache/http/entity/ByteArrayEntity
    //   236: dup
    //   237: aload 5
    //   239: invokestatic 3224	com/sysdevsolutions/kclientlibv40/CUtil:L	(Ljava/lang/String;)[B
    //   242: invokespecial 3227	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   245: astore 5
    //   247: goto +80 -> 327
    //   250: iload_3
    //   251: iconst_3
    //   252: if_icmpne +20 -> 272
    //   255: new 3221	org/apache/http/entity/ByteArrayEntity
    //   258: dup
    //   259: aload 5
    //   261: invokestatic 3229	com/sysdevsolutions/kclientlibv40/CUtil:O	(Ljava/lang/String;)[B
    //   264: invokespecial 3227	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   267: astore 5
    //   269: goto +58 -> 327
    //   272: iload_3
    //   273: iconst_4
    //   274: if_icmpne +17 -> 291
    //   277: new 3231	org/apache/http/entity/StringEntity
    //   280: dup
    //   281: aload 5
    //   283: invokespecial 3232	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;)V
    //   286: astore 5
    //   288: goto +39 -> 327
    //   291: iload_3
    //   292: iconst_5
    //   293: if_icmpne +20 -> 313
    //   296: new 3221	org/apache/http/entity/ByteArrayEntity
    //   299: dup
    //   300: aload 5
    //   302: invokestatic 3234	com/sysdevsolutions/kclientlibv40/CUtil:D	(Ljava/lang/String;)[B
    //   305: invokespecial 3227	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   308: astore 5
    //   310: goto +17 -> 327
    //   313: new 3221	org/apache/http/entity/ByteArrayEntity
    //   316: dup
    //   317: aload 5
    //   319: invokestatic 3236	com/sysdevsolutions/kclientlibv40/CUtil:I	(Ljava/lang/String;)[B
    //   322: invokespecial 3227	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
    //   325: astore 5
    //   327: aload 6
    //   329: iconst_2
    //   330: aload 7
    //   332: aload 8
    //   334: aload 5
    //   336: invokevirtual 3239	com/sysdevsolutions/kclientlibv40/bl:a	(ILjava/lang/String;Ljava/lang/String;Lorg/apache/http/HttpEntity;)Ljava/lang/String;
    //   339: astore 5
    //   341: goto +16 -> 357
    //   344: aload 6
    //   346: iconst_1
    //   347: aload 7
    //   349: aload 8
    //   351: aconst_null
    //   352: invokevirtual 3239	com/sysdevsolutions/kclientlibv40/bl:a	(ILjava/lang/String;Ljava/lang/String;Lorg/apache/http/HttpEntity;)Ljava/lang/String;
    //   355: astore 5
    //   357: goto +64 -> 421
    //   360: iload 4
    //   362: iconst_2
    //   363: if_icmpne +13 -> 376
    //   366: aload 6
    //   368: invokevirtual 3240	com/sysdevsolutions/kclientlibv40/bl:n	()Ljava/lang/String;
    //   371: astore 5
    //   373: goto +48 -> 421
    //   376: iload 4
    //   378: iconst_3
    //   379: if_icmpne +13 -> 392
    //   382: aload 6
    //   384: invokevirtual 3242	com/sysdevsolutions/kclientlibv40/bl:o	()Ljava/lang/String;
    //   387: astore 5
    //   389: goto +32 -> 421
    //   392: iload 4
    //   394: iconst_4
    //   395: if_icmpne +13 -> 408
    //   398: aload 6
    //   400: invokevirtual 3244	com/sysdevsolutions/kclientlibv40/bl:p	()Ljava/lang/String;
    //   403: astore 5
    //   405: goto +16 -> 421
    //   408: iload 4
    //   410: iconst_5
    //   411: if_icmpne +10 -> 421
    //   414: aload 6
    //   416: invokevirtual 3246	com/sysdevsolutions/kclientlibv40/bl:q	()Ljava/lang/String;
    //   419: astore 5
    //   421: aload 5
    //   423: ldc_w 304
    //   426: invokevirtual 671	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   429: ifne +54 -> 483
    //   432: aload_1
    //   433: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   436: astore 6
    //   438: new 578	java/lang/StringBuilder
    //   441: dup
    //   442: invokespecial 579	java/lang/StringBuilder:<init>	()V
    //   445: astore 7
    //   447: aload 7
    //   449: ldc_w 3248
    //   452: invokevirtual 585	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: pop
    //   456: aload 7
    //   458: aload 5
    //   460: invokevirtual 585	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: pop
    //   464: aload 6
    //   466: iconst_m1
    //   467: aload 7
    //   469: invokevirtual 591	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   472: ldc_w 3250
    //   475: aload_1
    //   476: getfield 524	com/sysdevsolutions/kclientlibv40/b:a	I
    //   479: invokestatic 527	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;ILjava/lang/String;Ljava/lang/String;I)V
    //   482: return
    //   483: aload_1
    //   484: invokestatic 518	com/sysdevsolutions/kclientlibv40/CMyFormDlg:e	(Lcom/sysdevsolutions/kclientlibv40/b;)Lcom/sysdevsolutions/kclientlibv40/ec;
    //   487: invokestatic 539	com/sysdevsolutions/kclientlibv40/CDadosCarregados:a	(Lcom/sysdevsolutions/kclientlibv40/ec;)V
    //   490: return
    //   491: astore_1
    //   492: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	493	0	this	CMyFormDlg
    //   0	493	1	paramB	b
    //   53	173	2	i1	int
    //   33	261	3	i2	int
    //   50	362	4	i3	int
    //   90	369	5	localObject1	Object
    //   107	358	6	localObject2	Object
    //   174	294	7	localObject3	Object
    //   189	161	8	str	String
    //   14	196	9	localCMyToken	CMyToken
    // Exception table:
    //   from	to	target	type
    //   277	288	491	java/io/UnsupportedEncodingException
  }
  
  void bV(b paramB)
  {
    Object localObject3 = new CMyToken(a(paramB), b).GetNextToken();
    Object localObject1 = new r(CDadosCarregados.ay);
    if (CUtil.StringToInt(CUtil.a((r)localObject1, "PUSH_NOTIFICATION_ENABLED", null)) != 1)
    {
      CDadosCarregados.a(e(paramB), -1, "Push Notifications are not active!", "1.2050", paramB.a);
      return;
    }
    CDadosCarregados.z.d();
    localObject1 = CUtil.a((r)localObject1, "PUSH_NOTIFICATION_ANDROID_SENDER_ID", null);
    if (getPackageName().equalsIgnoreCase("com.sysdevsolutions.kclientv40")) {
      localObject1 = "755094859940";
    }
    try
    {
      localObject2 = FirebaseInstanceId.a().a((String)localObject1, "FCM");
      int i1 = 0;
      while (localObject2 == null)
      {
        i1 += 1;
        if (i1 < 10)
        {
          CUtil.e(1000);
          localObject2 = FirebaseInstanceId.a().a((String)localObject1, "FCM");
        }
        else
        {
          throw new Exception("Timeout waiting for token.\r\nToken not yet generated!");
        }
      }
      SetTarget((String)localObject3, (String)localObject2, true, true, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException)
    {
      Object localObject2 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Error retrieving token ID!\r\n");
      ((StringBuilder)localObject3).append(CUtil.a(localException));
      CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2051", paramB.a);
    }
  }
  
  void bW(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    ((CMyToken)localObject).GetNextToken();
    ((CMyToken)localObject).GetNextToken();
    boolean bool;
    if (CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB))) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    String str3 = ((CMyToken)localObject).GetNextToken();
    localObject = ((CMyToken)localObject).GetNextToken();
    if (CDadosCarregados.a == null)
    {
      CDadosCarregados.a(e(paramB), -1, "Error retrieving notification message!\r\nNo message found!", "1.2052", paramB.a);
      return;
    }
    SetTarget(str1, CDadosCarregados.a.a(), true, true, c(paramB));
    if (CDadosCarregados.a.d() == null)
    {
      SetTarget(str3, "", true, true, c(paramB));
      SetTarget((String)localObject, "", true, true, c(paramB));
    }
    else
    {
      SetTarget(str3, CDadosCarregados.a.d().a(), true, true, c(paramB));
      SetTarget((String)localObject, CDadosCarregados.a.d().b(), true, true, c(paramB));
    }
    if (!CDadosCarregados.a.b().isEmpty()) {
      a(CDadosCarregados.a, str2, paramB, c(paramB));
    }
    CDadosCarregados.b = bool;
    CDadosCarregados.a(e(paramB));
  }
  
  void bX(b paramB)
  {
    Object localObject2 = new CMyToken(a(paramB), b);
    Object localObject1 = CalculaExpressaoStr(((CMyToken)localObject2).GetNextToken(), c(paramB));
    String str1 = ((CMyToken)localObject2).GetNextToken();
    String str2 = ((CMyToken)localObject2).GetNextToken();
    ((CMyToken)localObject2).GetNextToken();
    ((CMyToken)localObject2).GetNextToken();
    String str3 = ((CMyToken)localObject2).GetNextToken();
    String str4 = ((CMyToken)localObject2).GetNextToken();
    String str5 = ((CMyToken)localObject2).GetNextToken();
    localObject2 = ((CMyToken)localObject2).GetNextToken();
    dw localDw1 = new dw("");
    dw localDw2 = new dw("");
    dw localDw3 = new dw("");
    localObject1 = MyFcmListenerService.a((String)localObject1, localDw1, localDw2, localDw3);
    if (localObject1 == null)
    {
      CDadosCarregados.a(e(paramB), -1, "Error retrieving notification message!\r\nNo message found!", "1.2053", paramB.a);
      return;
    }
    SetTarget(str1, localDw1.a, true, true, c(paramB));
    SetTarget(str3, localDw2.a, true, true, c(paramB));
    SetTarget(str4, localDw3.a, true, true, c(paramB));
    if (((com.google.firebase.messaging.d)localObject1).d() == null)
    {
      SetTarget(str5, "", true, true, c(paramB));
      SetTarget((String)localObject2, "", true, true, c(paramB));
    }
    else
    {
      SetTarget(str5, ((com.google.firebase.messaging.d)localObject1).d().a(), true, true, c(paramB));
      SetTarget((String)localObject2, ((com.google.firebase.messaging.d)localObject1).d().b(), true, true, c(paramB));
    }
    a((com.google.firebase.messaging.d)localObject1, str2, paramB, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bY(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    localObject = CalculaExpressaoStr(((CMyToken)localObject).GetNextToken(), c(paramB));
    if (i1 == 2) {
      MyFcmListenerService.c((String)localObject);
    } else if (i1 == 3) {
      MyFcmListenerService.b();
    } else {
      MyFcmListenerService.c("");
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bZ(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    localObject1 = localObject2;
    if (((String)localObject2).length() == 0) {
      localObject1 = "\r";
    }
    localObject2 = new dw("");
    int i1 = MyFcmListenerService.a((dw)localObject2, (String)localObject1);
    SetTarget(str1, ((dw)localObject2).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i1), true, false, c(paramB));
  }
  
  void ba(b paramB)
  {
    int i1 = CDadosCarregados.bB.a(false);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bB.c, "1.2193", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bb(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bB.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bB.c, "1.2195", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bc(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bB.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bB.c, "1.2196", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bd(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool = true;
    if (i2 != 1) {
      bool = false;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bB.a(i1, bool, (dw)localObject, localCj);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bB.c, "1.2194", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void be(b paramB)
  {
    CDadosCarregados.bC.a();
    CDadosCarregados.bC = new ai();
    int i1 = CDadosCarregados.bC.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bC.c, "1.1909", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bg(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bC.a = localCMyToken.GetNextToken();
    CDadosCarregados.bC.b = localCMyToken.GetNextToken();
    int i1 = CDadosCarregados.bC.a(true);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bC.c, "1.1912", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bh(b paramB)
  {
    int i1 = CDadosCarregados.bC.a(false);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bC.c, "1.1913", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bi(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bC.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bC.c, "1.1915", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bj(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bC.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bC.c, "1.1916", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bk(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool = true;
    if (i2 != 1) {
      bool = false;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bC.a(i1, bool, (dw)localObject, localCj);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bC.c, "1.1914", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bl(b paramB)
  {
    CDadosCarregados.bE.a();
    CDadosCarregados.bE = new bo();
    int i1 = CDadosCarregados.bE.a(this);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bE.c, "1.2223", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bn(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bE.a = localCMyToken.GetNextToken();
    CDadosCarregados.bE.b = localCMyToken.GetNextToken();
    int i1 = CDadosCarregados.bE.a(true);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bE.c, "1.2227", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bo(b paramB)
  {
    int i1 = CDadosCarregados.bE.a(false);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bE.c, "1.2228", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bp(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i2 = CUtil.a((String)localObject2, '\r') + 1;
    localObject1 = new int[i2];
    localObject2 = new CMyToken((String)localObject2, "\r");
    int i1 = 0;
    while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
    {
      String str = ((CMyToken)localObject2).GetNextToken();
      if (!str.equals(""))
      {
        localObject1[i1] = CUtil.StringToInt(str);
        i1 += 1;
      }
    }
    i1 = CDadosCarregados.bE.a((int[])localObject1, i1);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bE.c, "1.2230", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void bq(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    ci localCi = new ci(null);
    int i1 = 0;
    cj localCj = new cj(0);
    int i2 = CDadosCarregados.bE.a(localCi, localCj);
    if (i2 != 0)
    {
      CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bE.c, "1.2231", paramB.a);
      return;
    }
    localObject1 = "";
    int i3;
    for (i2 = 0; i1 < localCj.a; i2 = i3)
    {
      Object localObject3 = localObject1;
      i3 = i2;
      if (localCi.a[i1] != 0)
      {
        localObject3 = localObject1;
        if (i2 > 0)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append((String)localObject1);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append((String)localObject3);
        ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
        localObject3 = ((StringBuilder)localObject1).toString();
        i3 = i2 + 1;
      }
      i1 += 1;
      localObject1 = localObject3;
    }
    SetTarget(str1, (String)localObject1, true, true, c(paramB));
    SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void br(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool = true;
    if (i2 != 1) {
      bool = false;
    }
    localObject = new dw("");
    cj localCj = new cj(0);
    i1 = CDadosCarregados.bE.a(i1, bool, (dw)localObject, localCj);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bE.c, "1.2229", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
    SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  void bs(b paramB)
  {
    if (CDadosCarregados.bF != null) {
      CDadosCarregados.bF.a();
    }
    try
    {
      CDadosCarregados.bF = new s();
      int i1 = CDadosCarregados.bF.a(this, this.bh);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bF.i, "1.1403", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Error localError)
    {
      localEc = e(paramB);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error initializing Datalogic API!\r\n");
      localStringBuilder.append(CUtil.a(localError));
      CDadosCarregados.a(localEc, 64628, localStringBuilder.toString(), "1.2229", paramB.a);
      return;
    }
    catch (Exception localException)
    {
      ec localEc = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error initializing Datalogic API!\r\n");
      localStringBuilder.append(CUtil.a(localException));
      CDadosCarregados.a(localEc, 64628, localStringBuilder.toString(), "1.1404", paramB.a);
    }
  }
  
  void bu(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CDadosCarregados.bF.g = localCMyToken.GetNextToken();
    CDadosCarregados.bF.h = localCMyToken.GetNextToken();
    if (CDadosCarregados.bF != null)
    {
      int i1 = CDadosCarregados.bF.a(true);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bF.i, "1.1407", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2230", paramB.a);
  }
  
  void bv(b paramB)
  {
    if (CDadosCarregados.bF != null)
    {
      int i1 = CDadosCarregados.bF.a(false);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bF.i, "1.1408", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2231", paramB.a);
  }
  
  void bw(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    if (CDadosCarregados.bF != null)
    {
      int i1 = 0;
      int i2 = CUtil.a((String)localObject2, '\r') + 1;
      localObject1 = new int[i2];
      localObject2 = new CMyToken((String)localObject2, "\r");
      while ((((CMyToken)localObject2).HasTokens()) && (i1 < i2))
      {
        String str = ((CMyToken)localObject2).GetNextToken();
        if (!str.equals(""))
        {
          localObject1[i1] = CUtil.StringToInt(str);
          i1 += 1;
        }
      }
      i1 = CDadosCarregados.bF.a((int[])localObject1, i1);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bF.i, "1.1410", paramB.a);
        return;
      }
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2232", paramB.a);
  }
  
  void bx(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    localObject1 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = localObject1;
    if (((String)localObject1).equals("")) {
      localObject2 = "\r";
    }
    if (CDadosCarregados.bF != null)
    {
      ci localCi = new ci(null);
      int i1 = 0;
      cj localCj = new cj(0);
      int i2 = CDadosCarregados.bF.a(localCi, localCj);
      if (i2 != 0)
      {
        CDadosCarregados.a(e(paramB), i2, CDadosCarregados.bF.i, "1.1411", paramB.a);
        return;
      }
      localObject1 = "";
      int i3;
      for (i2 = 0; i1 < localCj.a; i2 = i3)
      {
        Object localObject3 = localObject1;
        i3 = i2;
        if (localCi.a[i1] != 0)
        {
          localObject3 = localObject1;
          if (i2 > 0)
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append((String)localObject1);
            ((StringBuilder)localObject3).append((String)localObject2);
            localObject3 = ((StringBuilder)localObject3).toString();
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject3);
          ((StringBuilder)localObject1).append(CUtil.a(localCi.a[i1]));
          localObject3 = ((StringBuilder)localObject1).toString();
          i3 = i2 + 1;
        }
        i1 += 1;
        localObject1 = localObject3;
      }
      SetTarget(str1, (String)localObject1, true, true, c(paramB));
      SetTarget(str2, CUtil.a(i2), true, false, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2233", paramB.a);
  }
  
  void by(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject).GetNextToken();
    String str2 = ((CMyToken)localObject).GetNextToken();
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool = true;
    if (i2 != 1) {
      bool = false;
    }
    if (CDadosCarregados.bF != null)
    {
      localObject = new dw("");
      cj localCj = new cj(0);
      i1 = CDadosCarregados.bF.a(i1, bool, (dw)localObject, localCj);
      if (i1 != 0)
      {
        CDadosCarregados.a(e(paramB), i1, CDadosCarregados.bF.i, "1.1409", paramB.a);
        return;
      }
      SetTarget(str1, ((dw)localObject).a, true, true, c(paramB));
      SetTarget(str2, CUtil.a(localCj.a), true, false, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), 64624, "Scanner not connected!", "1.2234", paramB.a);
  }
  
  void bz(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    String str3 = ((CMyToken)localObject1).GetNextToken();
    Object localObject2 = CUtil.C(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    localObject1 = localObject2;
    if (((String)localObject2).length() == 0) {
      localObject1 = "\r";
    }
    localObject2 = new dw("");
    dw localDw1 = new dw("");
    cj localCj = new cj(0);
    dw localDw2 = new dw("");
    int i1 = al.a(this, (dw)localObject2, localDw1, localCj, (String)localObject1, localDw2);
    if (i1 != 0)
    {
      CDadosCarregados.a(e(paramB), i1, localDw2.a, "1.1402", paramB.a);
      return;
    }
    SetTarget(str1, ((dw)localObject2).a, true, true, c(paramB));
    SetTarget(str2, localDw1.a, true, true, c(paramB));
    SetTarget(str3, CUtil.a(localCj.a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  public int c(int paramInt)
  {
    if (CDadosCarregados.am == -1) {
      return a(CUtil.a(paramInt, 96), CDadosCarregados.an);
    }
    if (CDadosCarregados.am == -2) {
      return a(paramInt, CDadosCarregados.an);
    }
    if (CDadosCarregados.am == -36)
    {
      double d1 = paramInt;
      double d2 = CDadosCarregados.ao;
      Double.isNaN(d1);
      return (int)(d1 * d2);
    }
    return a(paramInt, CDadosCarregados.an);
  }
  
  public void c()
  {
    if (!this.al) {
      return;
    }
    if (CDadosCarregados.bn <= 0) {
      return;
    }
    this.aS.lock();
    if (this.aU != null)
    {
      this.bh.removeCallbacks(this.aU);
      this.aU = null;
    }
    this.aT += 1;
    this.aS.unlock();
  }
  
  void c(final int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 1) && (paramInt1 != 2))
    {
      int i2 = 0;
      paramInt1 = 0;
      while (paramInt1 < this.f.f)
      {
        if (this.f.e[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.e[paramInt1].aD.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < this.f.h)
      {
        if (this.f.g[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.g[paramInt1].T.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < this.f.p)
      {
        if (this.f.o[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.o[paramInt1].W.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < this.f.j)
      {
        if (this.f.i[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.i[paramInt1].am.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < this.f.l)
      {
        if (this.f.k[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.k[paramInt1].ax.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < this.f.t)
      {
        if (this.f.s[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.s[paramInt1].cb.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < this.f.n)
      {
        if (this.f.m[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.m[paramInt1].ao.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      final int i1;
      for (;;)
      {
        i1 = i2;
        if (paramInt1 >= this.f.z) {
          break;
        }
        if (this.f.y[paramInt1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.y[paramInt1].R.requestFocus();
            }
          });
          return;
        }
        paramInt1 += 1;
      }
      while (i1 < this.f.D)
      {
        if (this.f.C[i1].f == paramInt2)
        {
          CUtil.a(true, this.bh, new de()
          {
            public void a()
            {
              CMyFormDlg.this.f.C[i1].V.requestFocus();
            }
          });
          return;
        }
        i1 += 1;
      }
      return;
    }
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        View localView = jdField_this.getCurrentFocus();
        if (localView == null) {
          return;
        }
        int i = 0;
        while (i < CMyFormDlg.this.f.E)
        {
          if (localView == CMyFormDlg.this.f.F[i]) {
            if (paramInt1 == 1)
            {
              if (i == CMyFormDlg.this.f.E - 1) {
                CMyFormDlg.this.f.F[0].requestFocus();
              } else {
                CMyFormDlg.this.f.F[(i + 1)].requestFocus();
              }
            }
            else if (i == 0) {
              CMyFormDlg.this.f.F[(CMyFormDlg.this.f.E - 1)].requestFocus();
            } else {
              CMyFormDlg.this.f.F[(i - 1)].requestFocus();
            }
          }
          i += 1;
        }
      }
    });
  }
  
  void c(final b paramB, final int paramInt)
  {
    Object localObject2 = new CMyToken(a(paramB), b);
    Object localObject1 = ((CMyToken)localObject2).GetNextToken();
    final String str2 = ((CMyToken)localObject2).GetNextToken();
    final String str1 = ((CMyToken)localObject2).GetNextToken();
    String str3 = ((CMyToken)localObject2).GetNextToken();
    if (CUtil.b(str3, 6).equalsIgnoreCase("PLANE(")) {}
    for (final int i1 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("PLANE(", str3, ")"), c(paramB)));; i1 = CUtil.StringToInt(CalculaExpressaoNum(str3, c(paramB)))) {
      break;
    }
    final int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject2).GetNextToken(), c(paramB)));
    int i3 = CUtil.StringToInt(str2);
    switch (i3)
    {
    default: 
      switch (i3)
      {
      default: 
        break;
      case 52: 
        if (!str1.equals("")) {
          str1 = CalculaExpressaoNum(str1, c(paramB));
        }
        break;
      case 51: 
        if (!str1.equals("")) {
          str1 = CalculaExpressaoNum(str1, c(paramB));
        }
        break;
      case 50: 
        if (!str1.equals("")) {
          str1 = CalculaExpressaoNum(str1, c(paramB));
        }
        break;
      case 49: 
        if (!str1.equals("")) {
          str1 = CalculaExpressaoNum(str1, c(paramB));
        }
        break;
      }
      break;
    case 20: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 19: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 18: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 17: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 16: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 15: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 14: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 13: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 12: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 11: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 10: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 9: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 8: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 7: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 6: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 5: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 4: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
      break;
    case 3: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 2: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 1: 
      str1 = CalculaExpressaoNum(str1, c(paramB));
      break;
    case 0: 
      str1 = CalculaExpressaoStr(str1, c(paramB));
    }
    localObject1 = new CMyToken((String)localObject1, c);
    localObject2 = new e(false);
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        while (this.a.HasTokens())
        {
          this.b.a = false;
          Object localObject2 = this.a.GetNextToken();
          Object localObject1;
          if (((String)localObject2).equalsIgnoreCase("CURRENTCTRL"))
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("CTRL(");
            ((StringBuilder)localObject1).append(CUtil.LongToString(paramInt));
            ((StringBuilder)localObject1).append(")");
            localObject1 = ((StringBuilder)localObject1).toString();
          }
          else
          {
            localObject1 = localObject2;
            if (CUtil.b((String)localObject2, 8).equalsIgnoreCase("DYN_CTRL"))
            {
              localObject1 = CMyFormDlg.this.CalculaExpressaoStr(CUtil.a((String)localObject2, 9, ((String)localObject2).length() - 10), CMyFormDlg.c(paramB));
              j = CMyFormDlg.this.f.d((String)localObject1);
              if (j < 0) {
                continue;
              }
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("CTRL(");
              ((StringBuilder)localObject1).append(CUtil.a(j));
              ((StringBuilder)localObject1).append(")");
              localObject1 = ((StringBuilder)localObject1).toString();
            }
          }
          int j = i1;
          if (!CUtil.b((String)localObject1, 8).equalsIgnoreCase("CTRL_TYP")) {
            j = -2;
          }
          int m = j;
          if (j < 0) {
            m = -2;
          }
          if (CUtil.b((String)localObject1, 4).equalsIgnoreCase("FORM"))
          {
            if (str2.equals("1"))
            {
              if (str1.equals("1"))
              {
                CMyFormDlg.this.J = true;
                CMyFormDlg.this.setVisible(true);
              }
              else
              {
                CMyFormDlg.this.J = false;
                CMyFormDlg.this.setVisible(false);
              }
            }
            else if (str2.equals("4"))
            {
              CMyFormDlg.this.k = str1;
              CMyFormDlg.this.setTitle(CMyFormDlg.this.k);
            }
            else if (str2.equals("6"))
            {
              CMyFormDlg.this.q = CUtil.k(str1);
              CMyFormDlg.this.k();
            }
            else if (str2.equals("7"))
            {
              CMyFormDlg.this.a(CMyFormDlg.this.f.J, 7, CUtil.StringToInt(str1), true, 0);
            }
            else if (str2.equals("8"))
            {
              CMyFormDlg.this.a(CMyFormDlg.this.f.J, 8, CUtil.StringToInt(str1), true, 0);
            }
            else if (str2.equals("9"))
            {
              CMyFormDlg.this.a(CMyFormDlg.this.f.J, 9, CUtil.StringToInt(str1), true, 0);
            }
            else if (str2.equals("10"))
            {
              CMyFormDlg.this.a(CMyFormDlg.this.f.J, 10, CUtil.StringToInt(str1), true, 0);
            }
            else
            {
              str2.equals("12");
            }
          }
          else
          {
            int i3;
            int i2;
            if (CUtil.b((String)localObject1, 8).equalsIgnoreCase("CTRL_TYP"))
            {
              i3 = CUtil.StringToInt(CUtil.a("CTRL_TYP(", (String)localObject1, ")"));
              i2 = -1;
            }
            else
            {
              i2 = CUtil.StringToInt(CUtil.a("CTRL(", (String)localObject1, ")"));
              i3 = -1;
            }
            localObject1 = CMyFormDlg.this.f;
            int n = -1;
            j = 1;
            int k = 0;
            while (localObject1 != null)
            {
              int i4 = ((as)localObject1).b;
              localObject2 = ((as)localObject1).a;
              int i1 = 0;
              while (i1 < i4)
              {
                if ((localObject2[i1].v != 10) || (!((bh)localObject2[i1]).ab))
                {
                  while ((localObject2[i1].f != i2) && ((localObject2[i1].v != i3) || ((m >= 0) && (m != localObject2[i1].a)))) {}
                  if (str2.equals("0")) {
                    localObject2[i1].d(str1);
                  }
                  for (;;)
                  {
                    break;
                    if (str2.equals("1"))
                    {
                      localObject2[i1].b(str1.equals("1"));
                      if ((localObject2[i1].k != null) && (localObject2[i1].j == ag.a.c)) {
                        localObject2[i1].k.b(str1.equals("1"));
                      }
                    }
                    else if (str2.equals("2"))
                    {
                      if ((!str1.equals("1")) && (!str1.equals("3"))) {
                        localObject2[i1].d(false);
                      } else {
                        localObject2[i1].d(true);
                      }
                      if (localObject2[i1].v == 2) {
                        ((au)localObject2[i1]).e(str1.equals("3"));
                      }
                    }
                    else if (str2.equals("3"))
                    {
                      if (str1.equals("1")) {
                        localObject2[i1].u = true;
                      } else {
                        localObject2[i1].u = false;
                      }
                    }
                    else if (str2.equals("4"))
                    {
                      localObject2[i1].e(str1);
                    }
                    else if (str2.equals("5"))
                    {
                      localObject2[i1].a(str1);
                    }
                    else if (str2.equals("6"))
                    {
                      if (localObject2[i1].k != null)
                      {
                        if (localObject2[i1].j == ag.a.a) {
                          localObject2[i1].b(str1);
                        }
                        localObject2[i1].k.b(str1);
                      }
                      else
                      {
                        localObject2[i1].b(str1);
                      }
                    }
                    else if (str2.equals("7"))
                    {
                      localObject2[i1].d(CUtil.StringToInt(str1));
                      if (localObject2[i1].k != null) {
                        localObject2[i1].k.d(CUtil.StringToInt(str1));
                      }
                    }
                    else if (str2.equals("8"))
                    {
                      localObject2[i1].e(CUtil.StringToInt(str1));
                      if (localObject2[i1].k != null) {
                        localObject2[i1].k.e(CUtil.StringToInt(str1));
                      }
                    }
                    else if (str2.equals("9"))
                    {
                      localObject2[i1].f(CUtil.StringToInt(str1));
                      if (localObject2[i1].k != null) {
                        localObject2[i1].k.f(CUtil.StringToInt(str1));
                      }
                    }
                    else if (str2.equals("10"))
                    {
                      localObject2[i1].g(CUtil.StringToInt(str1));
                      if (localObject2[i1].k != null) {
                        localObject2[i1].k.g(CUtil.StringToInt(str1));
                      }
                    }
                    else if (str2.equals("11"))
                    {
                      if (str1.equals("1"))
                      {
                        if (localObject2[i1].k != null)
                        {
                          if (localObject2[i1].g) {
                            localObject2[i1].k.b(true);
                          }
                          localObject2[i1].a(ag.a.c);
                          localObject2[i1].b("-1");
                        }
                        else
                        {
                          localObject2[i1].a(ag.a.b);
                        }
                      }
                      else
                      {
                        localObject2[i1].a(ag.a.a);
                        if (localObject2[i1].k != null)
                        {
                          localObject2[i1].k.b(false);
                          localObject2[i1].b(localObject2[i1].k.l);
                        }
                      }
                    }
                    else if (str2.equals("13"))
                    {
                      localObject2[i1].a = CUtil.StringToInt(str1);
                      if (localObject2[i1].a < 0) {
                        localObject2[i1].a = 0;
                      } else if (localObject2[i1].a > 99) {
                        localObject2[i1].a = 99;
                      }
                      if (localObject2[i1].k != null) {
                        localObject2[i1].k.a = localObject2[i1].a;
                      }
                      if (((localObject2[i1].a == CMyFormDlg.this.l) || (localObject2[i1].a == 0)) && (localObject2[i1].g))
                      {
                        localObject2[i1].c(true);
                        if ((localObject2[i1].k != null) && (localObject2[i1].j == ag.a.c)) {
                          localObject2[i1].k.c(true);
                        }
                      }
                      else
                      {
                        localObject2[i1].c(false);
                        if ((localObject2[i1].k != null) && (localObject2[i1].j == ag.a.c)) {
                          localObject2[i1].k.c(false);
                        }
                      }
                    }
                    else if (str2.equals("14"))
                    {
                      if (localObject2[i1].v == 2) {
                        ((au)localObject2[i1]).f(str1);
                      }
                    }
                    else if (str2.equals("15"))
                    {
                      localObject2[i1].a(str1, localObject2[i1].E[0], localObject2[i1].E[1], localObject2[i1].F);
                    }
                    else
                    {
                      Object localObject3;
                      if (str2.equals("16"))
                      {
                        localObject3 = new int[2];
                        localObject3[0] = localObject2[i1].E[0];
                        localObject3[1] = localObject2[i1].E[1];
                        localObject3[CMyFormDlg.this.n] = CUtil.StringToInt(str1);
                        localObject2[i1].a(localObject2[i1].D, localObject3[0], localObject3[1], localObject2[i1].F);
                      }
                      else if (str2.equals("17"))
                      {
                        localObject2[i1].a(localObject2[i1].D, localObject2[i1].E[0], localObject2[i1].E[1], CUtil.StringToInt(str1));
                      }
                      else if (str2.equals("18"))
                      {
                        if (localObject2[i1].v == 11) {
                          ((av)localObject2[i1]).f(str1);
                        }
                      }
                      else if (str2.equals("19"))
                      {
                        localObject2[i1].h = str1;
                      }
                      else if (str2.equals("20"))
                      {
                        localObject2[i1].c(CUtil.StringToInt(str1));
                      }
                      else if (str2.equals("49"))
                      {
                        if (localObject2[i1].v == 2)
                        {
                          localObject3 = (au)localObject2[i1];
                          if ((!str1.equals("")) && (((au)localObject3).t == 1))
                          {
                            ((au)localObject3).ad = true;
                            ((au)localObject3).ae = CUtil.StringToInt(str1);
                            if (((au)localObject3).aD != null) {
                              ((au)localObject3).aD.setFilters(new InputFilter[] { new InputFilter.LengthFilter(((au)localObject3).ae) });
                            }
                          }
                          else
                          {
                            ((au)localObject3).ad = false;
                            if (((au)localObject3).aD != null) {
                              ((au)localObject3).aD.setFilters(new InputFilter[0]);
                            }
                          }
                          ((au)localObject3).m();
                        }
                      }
                      else if (str2.equals("50"))
                      {
                        if (localObject2[i1].v == 2)
                        {
                          localObject3 = (au)localObject2[i1];
                          if ((!str1.equals("")) && (((au)localObject3).t == 2))
                          {
                            ((au)localObject3).Z = true;
                            ((au)localObject3).aa = CUtil.StringToDouble(str1);
                          }
                          else
                          {
                            ((au)localObject3).Z = false;
                          }
                          ((au)localObject3).m();
                        }
                      }
                      else if (str2.equals("51"))
                      {
                        if (localObject2[i1].v == 2)
                        {
                          localObject3 = (au)localObject2[i1];
                          if ((!str1.equals("")) && (((au)localObject3).t == 2))
                          {
                            ((au)localObject3).ab = true;
                            ((au)localObject3).ac = CUtil.StringToDouble(str1);
                          }
                          else
                          {
                            ((au)localObject3).ab = false;
                          }
                          ((au)localObject3).m();
                        }
                      }
                      else if ((str2.equals("52")) && (localObject2[i1].v == 2))
                      {
                        localObject3 = (au)localObject2[i1];
                        if ((!str1.equals("")) && (((au)localObject3).t == 2))
                        {
                          ((au)localObject3).X = true;
                          ((au)localObject3).Y = CUtil.StringToInt(str1);
                        }
                        else
                        {
                          ((au)localObject3).X = false;
                        }
                        ((au)localObject3).n();
                        ((au)localObject3).m();
                      }
                    }
                  }
                  if (localObject2[i1].f == i2)
                  {
                    this.b.a = true;
                    break;
                  }
                }
                i1 += 1;
              }
              i1 = i2;
              localObject2 = null;
              if (i1 < 0)
              {
                for (;;)
                {
                  i1 = k;
                  if (n >= CMyFormDlg.this.f.B) {
                    break;
                  }
                  if (j != 0)
                  {
                    n += 1;
                    localObject1 = null;
                    j = 0;
                    k = -1;
                  }
                  else
                  {
                    k += 1;
                    localObject1 = CMyFormDlg.this.f.A[n].i(k);
                    if (localObject1 != null)
                    {
                      i1 = k;
                      break;
                    }
                    j = 1;
                  }
                }
                k = i1;
              }
              else
              {
                n += 1;
                localObject1 = localObject2;
                while (n < CMyFormDlg.this.f.B)
                {
                  if (i2 > 0) {}
                  for (localObject1 = CMyFormDlg.this.f.A[n].i(i2 - 1);; localObject1 = CMyFormDlg.this.f.A[n].o()) {
                    break;
                  }
                  if (localObject1 != null) {
                    break;
                  }
                  n += 1;
                }
              }
            }
          }
        }
      }
    });
  }
  
  boolean c(final String paramString)
  {
    if (android.support.v4.content.a.b(this, paramString) != 0)
    {
      CDadosCarregados.cx = false;
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          android.support.v4.app.a.a(CMyFormDlg.this, new String[] { paramString }, 0);
        }
      });
      while (!CDadosCarregados.cx) {
        CUtil.e(250);
      }
      return CDadosCarregados.cy;
    }
    return true;
  }
  
  void ca(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    String str1 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    String str2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i1;
    if (CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB))) == 1) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    paramB = RingtoneManager.getDefaultUri(2);
    localObject1 = new y.c(this).a(2131099669);
    ((y.c)localObject1).a((CharSequence)localObject2);
    ((y.c)localObject1).b(str2);
    if (i1 != 0) {
      ((y.c)localObject1).a(paramB);
    }
    ((y.c)localObject1).a(true);
    ((y.c)localObject1).a(PendingIntent.getActivity(this, 0, new Intent(this, KClientDlg.class), 0));
    localObject2 = (NotificationManager)getSystemService("notification");
    paramB = str1;
    if (str1.equals("")) {
      paramB = CUtil.LongToString(CUtil.b());
    }
    ((NotificationManager)localObject2).notify(paramB, 0, ((y.c)localObject1).a());
  }
  
  public void cb(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    bg.a localA = bg.a(CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB))));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    boolean bool;
    if (CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB))) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    if (CDadosCarregados.cB == null) {
      CDadosCarregados.cB = new bg();
    }
    if (localA == bg.a.a)
    {
      CDadosCarregados.a(e(paramB), -1, "Unrecognized Sensor Type parameter!", "1.2057", paramB.a);
      return;
    }
    if (localA == bg.a.b)
    {
      localObject = CDadosCarregados.cB.a(this, i1, bool, CUtil.StringToDouble(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB))), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken());
      if (!((String)localObject).equals("")) {
        CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2058", paramB.a);
      }
    }
    else if (localA == bg.a.c)
    {
      localObject = CDadosCarregados.cB.a(this, i1, bool, ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken());
      if (!((String)localObject).equals("")) {
        CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2059", paramB.a);
      }
    }
    else
    {
      if (localA == bg.a.d)
      {
        localObject = CDadosCarregados.cB.b(this, i1, bool, ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2060", paramB.a);
        }
        return;
      }
      if (localA == bg.a.e)
      {
        localObject = CDadosCarregados.cB.c(this, i1, bool, ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2061", paramB.a);
        }
      }
      else if (localA == bg.a.f)
      {
        localObject = CDadosCarregados.cB.a(this, i1, ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2062", paramB.a);
        }
      }
      else if (localA == bg.a.g)
      {
        localObject = CDadosCarregados.cB.b(this, i1, ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2063", paramB.a);
        }
      }
      else if (localA == bg.a.h)
      {
        localObject = CDadosCarregados.cB.c(this, i1, ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2064", paramB.a);
        }
      }
      else if (localA == bg.a.i)
      {
        localObject = CDadosCarregados.cB.d(this, i1, ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2065", paramB.a);
        }
      }
      else if (localA == bg.a.j)
      {
        localObject = CDadosCarregados.cB.e(this, i1, ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2066", paramB.a);
        }
      }
      else if (localA == bg.a.k)
      {
        localObject = CDadosCarregados.cB.a(this, i1, ((CMyToken)localObject).GetNextToken(), ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2067", paramB.a);
        }
      }
      else if (localA == bg.a.l)
      {
        localObject = CDadosCarregados.cB.f(this, i1, ((CMyToken)localObject).GetNextToken());
        if (!((String)localObject).equals(""))
        {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2068", paramB.a);
          return;
        }
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void cc(b paramB)
  {
    Object localObject = bg.a(CUtil.StringToInt(CalculaExpressaoNum(new CMyToken(a(paramB), b).GetNextToken(), c(paramB))));
    if (CDadosCarregados.cB != null)
    {
      if (localObject == bg.a.a)
      {
        CDadosCarregados.a(e(paramB), -1, "Unrecognized Sensor Type parameter!", "1.2069", paramB.a);
        return;
      }
      if (localObject == bg.a.b)
      {
        localObject = CDadosCarregados.cB.a();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2070", paramB.a);
        }
      }
      else if (localObject == bg.a.c)
      {
        localObject = CDadosCarregados.cB.b();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2071", paramB.a);
        }
      }
      else if (localObject == bg.a.d)
      {
        localObject = CDadosCarregados.cB.c();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2072", paramB.a);
        }
      }
      else if (localObject == bg.a.e)
      {
        localObject = CDadosCarregados.cB.d();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2073", paramB.a);
        }
      }
      else if (localObject == bg.a.f)
      {
        localObject = CDadosCarregados.cB.e();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2074", paramB.a);
        }
      }
      else if (localObject == bg.a.g)
      {
        localObject = CDadosCarregados.cB.f();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2075", paramB.a);
        }
      }
      else if (localObject == bg.a.h)
      {
        localObject = CDadosCarregados.cB.g();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2076", paramB.a);
        }
      }
      else if (localObject == bg.a.i)
      {
        localObject = CDadosCarregados.cB.h();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2077", paramB.a);
        }
      }
      else if (localObject == bg.a.j)
      {
        localObject = CDadosCarregados.cB.i();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2078", paramB.a);
        }
      }
      else if (localObject == bg.a.k)
      {
        localObject = CDadosCarregados.cB.j();
        if (!((String)localObject).equals("")) {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2079", paramB.a);
        }
      }
      else if (localObject == bg.a.l)
      {
        localObject = CDadosCarregados.cB.k();
        if (!((String)localObject).equals(""))
        {
          CDadosCarregados.a(e(paramB), -1, (String)localObject, "1.2080", paramB.a);
          return;
        }
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void cd(final b paramB)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        String str = new CMyToken(CMyFormDlg.a(paramB), CMyFormDlg.b).GetNextToken();
        if (CDadosCarregados.cC != null) {
          CDadosCarregados.cC.a();
        }
        CDadosCarregados.cC = new bd();
        str = CDadosCarregados.cC.a(CMyFormDlg.this, str);
        if (!str.equals(""))
        {
          ec localEc = CMyFormDlg.e(paramB);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Error connecting to NFC device!\r\n");
          localStringBuilder.append(str);
          CDadosCarregados.a(localEc, -1, localStringBuilder.toString(), "1.2080", paramB.a);
          return;
        }
        CDadosCarregados.a(CMyFormDlg.e(paramB));
      }
    });
  }
  
  public void ce(final b paramB)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        if (CDadosCarregados.cC != null)
        {
          String str = CDadosCarregados.cC.a();
          if (!str.equals(""))
          {
            ec localEc = CMyFormDlg.e(paramB);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Error disconnecting from NFC device!\r\n");
            localStringBuilder.append(str);
            CDadosCarregados.a(localEc, -1, localStringBuilder.toString(), "1.2081", paramB.a);
            return;
          }
          CDadosCarregados.cC = null;
        }
        CDadosCarregados.a(CMyFormDlg.e(paramB));
      }
    });
  }
  
  public void cf(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    localObject = CalculaExpressaoStr(((CMyToken)localObject).GetNextToken(), c(paramB));
    if (CDadosCarregados.cC == null)
    {
      CDadosCarregados.a(e(paramB), -1, "Not connected to NFC device!", "1.2083", paramB.a);
      return;
    }
    ec localEc;
    StringBuilder localStringBuilder;
    if (i1 == 2)
    {
      localObject = CDadosCarregados.cC.b();
      if (!((String)localObject).equals(""))
      {
        localEc = e(paramB);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error stoping message publishing!\r\n");
        localStringBuilder.append((String)localObject);
        CDadosCarregados.a(localEc, -1, localStringBuilder.toString(), "1.2084", paramB.a);
      }
    }
    else
    {
      if (i2 == 2) {
        localObject = CDadosCarregados.cC.a((String)localObject, false);
      } else if (i2 == 3) {
        localObject = CDadosCarregados.cC.a((String)localObject, true);
      } else if (i2 == 4) {
        localObject = CDadosCarregados.cC.a(null, true);
      } else {
        localObject = CDadosCarregados.cC.a((String)localObject);
      }
      if (!((String)localObject).equals(""))
      {
        localEc = e(paramB);
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error publishing message!\r\n");
        localStringBuilder.append((String)localObject);
        CDadosCarregados.a(localEc, -1, localStringBuilder.toString(), "1.2085", paramB.a);
        return;
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void cg(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    Object localObject2 = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    String str = localCMyToken.GetNextToken();
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    Object localObject3;
    if (i1 == 2) {
      try
      {
        localObject2 = new NdefMessage(CUtil.D((String)localObject2));
      }
      catch (Exception localException1)
      {
        localObject2 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error loading source message!\r\n");
        ((StringBuilder)localObject3).append(CUtil.a(localException1));
        CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2086", paramB.a);
        return;
      }
    } else {
      localObject2 = null;
    }
    if (i2 == 1) {}
    try
    {
      i1 = CUtil.StringToInt(CalculaExpressaoNum(localException1.GetNextToken(), c(paramB)));
      if ((i1 >= 0) && (i1 <= 7))
      {
        localObject3 = CUtil.D(CalculaExpressaoStr(localException1.GetNextToken(), c(paramB)));
        localObject4 = CUtil.D(CalculaExpressaoStr(localException1.GetNextToken(), c(paramB)));
        localObject1 = CUtil.D(CalculaExpressaoStr(localException1.GetNextToken(), c(paramB)));
        localObject1 = new NdefRecord((short)i1, (byte[])localObject3, (byte[])localObject1, (byte[])localObject4);
        break label798;
      }
      CDadosCarregados.a(e(paramB), -1, "Invalid TNF type specifyed!", "1.2087", paramB.a);
      return;
    }
    catch (Exception localException2)
    {
      for (;;)
      {
        Object localObject4;
        Object localObject1;
        Object localObject5;
        byte[] arrayOfByte;
        label798:
        label871:
        continue;
      }
    }
    if (i2 == 2)
    {
      localObject3 = NdefRecord.createUri(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
      localObject4 = CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
      localObject1 = localObject3;
      if (localObject4 == null) {
        break label1035;
      }
      localObject1 = new NdefRecord(((NdefRecord)localObject3).getTnf(), ((NdefRecord)localObject3).getType(), (byte[])localObject4, ((NdefRecord)localObject3).getPayload());
    }
    else
    {
      if (i2 == 3)
      {
        localObject5 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
        localObject4 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
        localObject3 = CUtil.D("T");
        localObject1 = CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
        localObject5 = CUtil.I((String)localObject5);
        localObject4 = CUtil.I((String)localObject4);
        arrayOfByte = new byte[localObject5.length + 1 + localObject4.length];
        arrayOfByte[0] = 0;
        arrayOfByte[0] = ((byte)(arrayOfByte[0] & 0x7F));
        arrayOfByte[0] = ((byte)(arrayOfByte[0] | (byte)((byte)localObject5.length & 0x3F)));
        System.arraycopy(localObject5, 0, arrayOfByte, 1, localObject5.length);
        System.arraycopy(localObject4, 0, arrayOfByte, localObject5.length + 1, localObject4.length);
        localObject1 = new NdefRecord((short)1, (byte[])localObject3, (byte[])localObject1, arrayOfByte);
        break label1035;
      }
      if (i2 == 4)
      {
        localObject3 = NdefRecord.createMime(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)), CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB))));
        localObject4 = CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
        localObject1 = localObject3;
        if (localObject4 == null) {
          break label1035;
        }
        localObject1 = new NdefRecord(((NdefRecord)localObject3).getTnf(), ((NdefRecord)localObject3).getType(), (byte[])localObject4, ((NdefRecord)localObject3).getPayload());
      }
      else
      {
        if (i2 == 5)
        {
          localObject3 = CUtil.D("Sp");
          localObject4 = CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
          localObject1 = new NdefRecord((short)1, (byte[])localObject3, CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB))), (byte[])localObject4);
        }
        else
        {
          if (i2 != 6) {
            break label954;
          }
          localObject3 = NdefRecord.createExternal(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)), CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)), CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB))));
          localObject4 = CUtil.D(CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB)));
          localObject1 = localObject3;
          if (localObject4 == null) {
            break label1035;
          }
          localObject1 = new NdefRecord(((NdefRecord)localObject3).getTnf(), ((NdefRecord)localObject3).getType(), (byte[])localObject4, ((NdefRecord)localObject3).getPayload());
          break label1032;
        }
        if (localObject2 != null) {}
      }
    }
    try
    {
      localObject1 = new NdefMessage((NdefRecord)localObject1, new NdefRecord[0]);
      break label871;
      localObject2 = ((NdefMessage)localObject2).getRecords();
      localObject3 = new NdefRecord[localObject2.length + 1];
      System.arraycopy(localObject2, 0, localObject3, 0, localObject2.length);
      localObject3[(localObject2.length - 1)] = localObject1;
      localObject1 = new NdefMessage((NdefRecord[])localObject3);
      SetTarget(str, CUtil.a(((NdefMessage)localObject1).toByteArray()), true, true, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    catch (Exception localException3)
    {
      for (;;) {}
    }
    localObject1 = e(paramB);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Error adding Ndef record to Ndef message!\r\n");
    ((StringBuilder)localObject2).append(CUtil.a((Exception)localObject3));
    CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject2).toString(), "1.2092", paramB.a);
    return;
    label954:
    CDadosCarregados.a(e(paramB), -1, "Invalid message type parameter!", "1.2088", paramB.a);
    return;
    localObject1 = e(paramB);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Error creating Ndef record!\r\n");
    ((StringBuilder)localObject2).append(CUtil.a((Exception)localObject3));
    CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject2).toString(), "1.2089", paramB.a);
  }
  
  public void ch(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    for (;;)
    {
      try
      {
        localObject2 = new NdefMessage(CUtil.D((String)localObject2));
        Object localObject4;
        String str6;
        Object localObject5;
        String str4;
        if (i1 == 0)
        {
          SetTarget(((CMyToken)localObject1).GetNextToken(), CUtil.a(((NdefMessage)localObject2).getRecords().length), true, false, c(paramB));
        }
        else
        {
          int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB))) - 1;
          localObject2 = ((NdefMessage)localObject2).getRecords();
          if ((i2 < 0) || (i2 >= localObject2.length)) {
            continue;
          }
          localObject2 = localObject2[i2];
          if (i1 == 1)
          {
            localObject3 = ((CMyToken)localObject1).GetNextToken();
            localObject4 = ((CMyToken)localObject1).GetNextToken();
            str6 = ((CMyToken)localObject1).GetNextToken();
            localObject1 = ((CMyToken)localObject1).GetNextToken();
            SetTarget((String)localObject3, CUtil.a(((NdefRecord)localObject2).getTnf()), true, false, c(paramB));
            SetTarget((String)localObject4, CUtil.a(((NdefRecord)localObject2).getType()), true, true, c(paramB));
            SetTarget(str6, CUtil.a(((NdefRecord)localObject2).getPayload()), true, true, c(paramB));
            SetTarget((String)localObject1, CUtil.a(((NdefRecord)localObject2).getId()), true, true, c(paramB));
          }
          else
          {
            if (i1 == 2)
            {
              localObject3 = ((CMyToken)localObject1).GetNextToken();
              localObject1 = ((CMyToken)localObject1).GetNextToken();
              try
              {
                localObject4 = ((NdefRecord)localObject2).toUri();
                if (localObject4 != null)
                {
                  SetTarget((String)localObject3, ((Uri)localObject4).toString(), true, true, c(paramB));
                  SetTarget((String)localObject1, CUtil.a(((NdefRecord)localObject2).getId()), true, true, c(paramB));
                  continue;
                }
                throw new Exception("Message does not contain recognized Uri record type!");
              }
              catch (Exception localException1)
              {
                localObject2 = e(paramB);
                localObject3 = new StringBuilder();
                ((StringBuilder)localObject3).append("Error parsing record for Uri or it is not an URI record!\r\n");
                ((StringBuilder)localObject3).append(CUtil.a(localException1));
                CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2095", paramB.a);
                return;
              }
            }
            if (i1 == 3)
            {
              localObject3 = localException1.GetNextToken();
              localObject4 = localException1.GetNextToken();
              str6 = localException1.GetNextToken();
              try
              {
                if (((NdefRecord)localObject2).getTnf() == 1)
                {
                  if (CUtil.a(((NdefRecord)localObject2).getType()).equalsIgnoreCase("T"))
                  {
                    localObject5 = ((NdefRecord)localObject2).getPayload();
                    if ((localObject5[0] & 0x80) != 0) {
                      break label1384;
                    }
                    String str1 = "UTF-8";
                    i1 = localObject5[0] & 0x33;
                    String str7 = new String((byte[])localObject5, 1, i1, "UTF-8");
                    str1 = new String((byte[])localObject5, i1 + 1, localObject5.length - i1 - 1, str1);
                    SetTarget((String)localObject3, str7, true, true, c(paramB));
                    SetTarget((String)localObject4, str1, true, true, c(paramB));
                    SetTarget(str6, CUtil.a(((NdefRecord)localObject2).getId()), true, true, c(paramB));
                    continue;
                  }
                  throw new Exception("Message RTD type is not \"T\"!");
                }
                throw new Exception("Message is not TNF Well known type");
              }
              catch (Exception localException2)
              {
                localObject2 = e(paramB);
                localObject3 = new StringBuilder();
                ((StringBuilder)localObject3).append("Error parsing record for Text or it is not a Text record!\r\n");
                ((StringBuilder)localObject3).append(CUtil.a(localException2));
                CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2096", paramB.a);
                return;
              }
            }
            if (i1 == 4)
            {
              localObject3 = localException2.GetNextToken();
              localObject4 = localException2.GetNextToken();
              String str2 = localException2.GetNextToken();
              try
              {
                str6 = ((NdefRecord)localObject2).toMimeType();
                if (str6 != null)
                {
                  SetTarget((String)localObject3, str6, true, true, c(paramB));
                  SetTarget((String)localObject4, CUtil.a(((NdefRecord)localObject2).getPayload()), true, true, c(paramB));
                  SetTarget(str2, CUtil.a(((NdefRecord)localObject2).getId()), true, true, c(paramB));
                  continue;
                }
                throw new Exception("Message cannot be parsed to a mime type!");
              }
              catch (Exception localException3)
              {
                localObject2 = e(paramB);
                localObject3 = new StringBuilder();
                ((StringBuilder)localObject3).append("Error parsing record for Mime!\r\n");
                ((StringBuilder)localObject3).append(CUtil.a(localException3));
                CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2097", paramB.a);
                return;
              }
            }
            if (i1 == 5)
            {
              localObject3 = localException3.GetNextToken();
              String str3 = localException3.GetNextToken();
              try
              {
                if (((NdefRecord)localObject2).getTnf() == 1)
                {
                  if (CUtil.a(((NdefRecord)localObject2).getType()).equalsIgnoreCase("Sp"))
                  {
                    SetTarget((String)localObject3, CUtil.a(((NdefRecord)localObject2).getPayload()), true, true, c(paramB));
                    SetTarget(str3, CUtil.a(((NdefRecord)localObject2).getId()), true, true, c(paramB));
                    continue;
                  }
                  throw new Exception("Message RTD type is not \"Sp\"!");
                }
                throw new Exception("Message is not TNF Well known type");
              }
              catch (Exception localException4)
              {
                localObject2 = e(paramB);
                localObject3 = new StringBuilder();
                ((StringBuilder)localObject3).append("Error parsing record for Smart Poster or it is not a Smart Poster record!\r\n");
                ((StringBuilder)localObject3).append(CUtil.a(localException4));
                CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2098", paramB.a);
                return;
              }
            }
            if (i1 != 6) {
              continue;
            }
            localObject3 = localException4.GetNextToken();
            localObject4 = localException4.GetNextToken();
            str6 = localException4.GetNextToken();
            str4 = localException4.GetNextToken();
          }
        }
        try
        {
          if (((NdefRecord)localObject2).getTnf() == 4)
          {
            localObject5 = CUtil.a(((NdefRecord)localObject2).getType());
            if (((String)localObject5).indexOf(':') >= 0)
            {
              SetTarget((String)localObject3, CUtil.d((String)localObject5, ":"), true, true, c(paramB));
              SetTarget((String)localObject4, CUtil.e((String)localObject5, ":"), true, true, c(paramB));
            }
            else if (((String)localObject5).indexOf('.') >= 0)
            {
              SetTarget((String)localObject3, (String)localObject5, true, true, c(paramB));
              SetTarget((String)localObject4, "", true, true, c(paramB));
            }
            else
            {
              SetTarget((String)localObject3, "", true, true, c(paramB));
              SetTarget((String)localObject4, (String)localObject5, true, true, c(paramB));
            }
            SetTarget(str6, CUtil.a(((NdefRecord)localObject2).getPayload()), true, true, c(paramB));
            SetTarget(str4, CUtil.a(((NdefRecord)localObject2).getId()), true, true, c(paramB));
            CDadosCarregados.a(e(paramB));
            return;
          }
          throw new Exception("Message is not TNF External type");
        }
        catch (Exception localException5)
        {
          localObject2 = e(paramB);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Error parsing record for External Type!\r\n");
          ((StringBuilder)localObject3).append(CUtil.a(localException5));
          CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2099", paramB.a);
          return;
        }
        CDadosCarregados.a(e(paramB), -1, "Invalid option parameter!", "1.2100", paramB.a);
        return;
        CDadosCarregados.a(e(paramB), -1, "Invalid record index!", "1.2094", paramB.a);
        return;
      }
      catch (Exception localException6)
      {
        localObject2 = e(paramB);
        Object localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error loading message!\r\n");
        ((StringBuilder)localObject3).append(CUtil.a(localException6));
        CDadosCarregados.a((ec)localObject2, -1, ((StringBuilder)localObject3).toString(), "1.2093", paramB.a);
        return;
      }
      label1384:
      String str5 = "UTF-16";
    }
  }
  
  public void ci(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b).GetNextToken();
    if (CDadosCarregados.cC == null)
    {
      CDadosCarregados.a(e(paramB), -1, "Not connected to NFC device!", "1.2101", paramB.a);
      return;
    }
    Object localObject2 = new cj(0);
    String str = CDadosCarregados.cC.a((cj)localObject2);
    if (!str.equals(""))
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error retrieving tag type!\r\n");
      ((StringBuilder)localObject2).append(str);
      CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject2).toString(), "1.2102", paramB.a);
      return;
    }
    SetTarget((String)localObject1, CUtil.a(((cj)localObject2).a), true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  public void cj(b paramB)
  {
    Object localObject2 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject2).GetNextToken(), c(paramB)));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject2).GetNextToken(), c(paramB)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject2).GetNextToken(), c(paramB)));
    int i5 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject2).GetNextToken(), c(paramB)));
    Object localObject1 = ((CMyToken)localObject2).GetNextToken();
    int i1;
    if (CUtil.StringToLong(CalculaExpressaoNum(((CMyToken)localObject2).GetNextToken(), c(paramB))) == 1L) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    if (CDadosCarregados.cC == null)
    {
      CDadosCarregados.a(e(paramB), -1, "Not connected to NFC device!", "1.2103", paramB.a);
      return;
    }
    Object localObject3 = new dw("");
    if (i3 == 2)
    {
      localObject2 = CDadosCarregados.cC.a(i2, i4, i5, (dw)localObject3);
      if (!((String)localObject2).equals(""))
      {
        localObject1 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error reading tag data!\r\n");
        ((StringBuilder)localObject3).append((String)localObject2);
        CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject3).toString(), "1.2104", paramB.a);
        return;
      }
      if (i1 != 0) {
        ((dw)localObject3).a = CUtil.c(((dw)localObject3).a, false);
      }
    }
    else
    {
      localObject2 = CDadosCarregados.cC.a(i2, (dw)localObject3);
      if (!((String)localObject2).equals(""))
      {
        localObject1 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error reading tag ID!\r\n");
        ((StringBuilder)localObject3).append((String)localObject2);
        CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject3).toString(), "1.2105", paramB.a);
        return;
      }
    }
    SetTarget((String)localObject1, ((dw)localObject3).a, true, true, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  public void ck(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i1 = 1;
    if (i4 != 1) {
      i1 = 0;
    }
    if (CDadosCarregados.cC == null)
    {
      CDadosCarregados.a(e(paramB), -1, "Not connected to NFC device!", "1.2106", paramB.a);
      return;
    }
    localObject1 = localObject2;
    if (i1 != 0) {
      localObject1 = CUtil.C((String)localObject2);
    }
    localObject1 = CDadosCarregados.cC.a(i2, i3, (String)localObject1);
    if (!((String)localObject1).equals(""))
    {
      localObject2 = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error writing tag data!\r\n");
      localStringBuilder.append((String)localObject1);
      CDadosCarregados.a((ec)localObject2, -1, localStringBuilder.toString(), "1.2107", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void cl(b paramB)
  {
    Object localObject3 = new CMyToken(a(paramB), b);
    Object localObject1 = ((CMyToken)localObject3).GetNextToken();
    Object localObject2 = ((CMyToken)localObject3).GetNextToken();
    localObject3 = ((CMyToken)localObject3).GetNextToken();
    if (!c("android.permission.ACCESS_COARSE_LOCATION"))
    {
      CDadosCarregados.a(e(paramB), -20, "User did not give permission to access coarse location!", "1.2332", paramB.a);
      return;
    }
    if (CDadosCarregados.bN != null)
    {
      CDadosCarregados.bN.b();
      CDadosCarregados.bN = null;
    }
    CDadosCarregados.bN = new j();
    if (CDadosCarregados.bN.a((String)localObject1, (String)localObject2, (String)localObject3) != 0)
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error starting bluetooth device disconvery!\r\n");
      ((StringBuilder)localObject2).append(CDadosCarregados.bN.a);
      CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject2).toString(), "1.2181", paramB.a);
      CDadosCarregados.bN = null;
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void cm(b paramB)
  {
    if (CDadosCarregados.bN != null)
    {
      if (CDadosCarregados.bN.b() != 0)
      {
        ec localEc = e(paramB);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error stoping bluetooth device disconvery!\r\n");
        localStringBuilder.append(CDadosCarregados.bN.a);
        CDadosCarregados.a(localEc, -1, localStringBuilder.toString(), "1.2182", paramB.a);
        CDadosCarregados.bN = null;
        return;
      }
      CDadosCarregados.bN = null;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void cn(b paramB)
  {
    Object localObject = CalculaExpressaoStr(new CMyToken(a(paramB), b).GetNextToken(), c(paramB));
    CDadosCarregados.bO = new j();
    if (CDadosCarregados.bO.a((String)localObject) != 0)
    {
      localObject = e(paramB);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Error pairing bluetooth device!\r\n");
      localStringBuilder.append(CDadosCarregados.bO.a);
      CDadosCarregados.a((ec)localObject, -1, localStringBuilder.toString(), "1.2183", paramB.a);
      return;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void co(b paramB)
  {
    Object localObject2 = new CMyToken(a(paramB), b);
    Object localObject1 = CalculaExpressaoStr(((CMyToken)localObject2).GetNextToken(), c(paramB));
    localObject2 = ((CMyToken)localObject2).GetNextToken();
    j localJ = new j();
    e localE = new e(false);
    if (localJ.a((String)localObject1, localE) != 0)
    {
      localObject1 = e(paramB);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error determining pairing state!\r\n");
      ((StringBuilder)localObject2).append(CDadosCarregados.bN.a);
      CDadosCarregados.a((ec)localObject1, -1, ((StringBuilder)localObject2).toString(), "1.2187", paramB.a);
      CDadosCarregados.bN = null;
      return;
    }
    if (localE.a) {}
    for (localObject1 = "1";; localObject1 = "0") {
      break;
    }
    SetTarget((String)localObject2, (String)localObject1, true, false, c(paramB));
    CDadosCarregados.a(e(paramB));
  }
  
  public void cp(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    final int i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    if (i1 == 1)
    {
      if (!this.A)
      {
        CDadosCarregados.a(e(paramB), 65225, "Vertical scroll not active on this form!", "1.1120", paramB.a);
        return;
      }
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          try
          {
            Object localObject = (ViewGroup)CMyFormDlg.this.findViewById(16908290);
            int i = 0;
            localObject = (ScrollView)((ViewGroup)localObject).getChildAt(0);
            if (localObject == null) {
              return;
            }
            int j = CMyFormDlg.this.S[CMyFormDlg.this.n] - ((ScrollView)localObject).getHeight();
            int k = i2;
            k = CMyFormDlg.this.a(k, 2);
            if (k >= 0) {
              if (k > j) {
                i = j;
              } else {
                i = k;
              }
            }
            ((ScrollView)localObject).setScrollY(i);
            return;
          }
          catch (Exception localException) {}
        }
      });
    }
    else
    {
      if (!this.B)
      {
        CDadosCarregados.a(e(paramB), 65224, "Horizontal scroll not active on this form!", "1.1121", paramB.a);
        return;
      }
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          try
          {
            HorizontalScrollView localHorizontalScrollView;
            if (CMyFormDlg.this.A) {
              localHorizontalScrollView = (HorizontalScrollView)((ScrollView)((ViewGroup)CMyFormDlg.this.findViewById(16908290)).getChildAt(0)).getChildAt(0);
            } else {
              localHorizontalScrollView = (HorizontalScrollView)((ViewGroup)CMyFormDlg.this.findViewById(16908290)).getChildAt(0);
            }
            if (localHorizontalScrollView == null) {
              return;
            }
            int i = CMyFormDlg.this.R[CMyFormDlg.this.n] - localHorizontalScrollView.getWidth();
            int j = i2;
            j = CMyFormDlg.this.a(j, 1);
            if (j < 0) {
              i = 0;
            } else if (j <= i) {
              i = j;
            }
            localHorizontalScrollView.setScrollX(i);
            return;
          }
          catch (Exception localException) {}
        }
      });
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void cq(final b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    final int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    final String str = CalculaExpressaoStr(((CMyToken)localObject).GetNextToken(), c(paramB));
    localObject = ((CMyToken)localObject).GetNextToken();
    CDadosCarregados.u = 0;
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        try
        {
          Intent localIntent;
          if (i1 == 1)
          {
            localIntent = new Intent("android.intent.action.OPEN_DOCUMENT");
            localIntent.addCategory("android.intent.category.OPENABLE");
            localIntent.setType("*/*");
          }
          else if (i1 == 2)
          {
            localIntent = new Intent("android.intent.action.CREATE_DOCUMENT");
            localIntent.putExtra("android.intent.extra.TITLE", str);
            localIntent.addCategory("android.intent.category.OPENABLE");
            localIntent.setType("*/*");
          }
          else if (i1 == 3)
          {
            localIntent = new Intent("android.intent.action.PICK");
            localIntent.setType("image/*");
          }
          else if (i1 == 4)
          {
            localIntent = new Intent("android.intent.action.PICK");
            localIntent.setType("video/*");
          }
          else if (i1 == 10)
          {
            localIntent = new Intent("android.intent.action.OPEN_DOCUMENT_TREE");
            localIntent.setType("*/*");
          }
          else
          {
            localIntent = new Intent("android.intent.action.OPEN_DOCUMENT");
            localIntent.addCategory("android.intent.category.OPENABLE");
            localIntent.setType("*/*");
          }
          CMyFormDlg.this.startActivityForResult(localIntent, 15);
          return;
        }
        catch (Exception localException)
        {
          ec localEc = CMyFormDlg.e(paramB);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Error starting activity for intent!\r\n");
          localStringBuilder.append(localException.getMessage());
          CDadosCarregados.a(localEc, -1, localStringBuilder.toString(), "1.2276", i1);
          CDadosCarregados.u = -1;
        }
      }
    });
    while (CDadosCarregados.u == 0) {
      CUtil.e(250);
    }
    if (CDadosCarregados.u == -1) {
      return;
    }
    if (CDadosCarregados.v != null)
    {
      SetTarget((String)localObject, CDadosCarregados.v.toString(), true, true, c(paramB));
      CDadosCarregados.a(e(paramB));
      return;
    }
    CDadosCarregados.a(e(paramB), -1, "No file picked!", "1.2275", paramB.a);
  }
  
  Uri d(String paramString)
  {
    File localFile = new File(paramString);
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(CUtil.a(getCacheDir().getAbsolutePath(), false));
    ((StringBuilder)localObject1).append("KSHAREK");
    localObject1 = new File(((StringBuilder)localObject1).toString());
    ((File)localObject1).mkdir();
    Object localObject2 = new dw("");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(CUtil.a(((File)localObject1).getAbsolutePath(), false));
    localStringBuilder.append(localFile.getName());
    if (CUtil.a(paramString, localStringBuilder.toString(), false, (dw)localObject2, this))
    {
      paramString = new StringBuilder();
      paramString.append(getPackageName());
      paramString.append(".fileprovider");
      paramString = paramString.toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(CUtil.a(((File)localObject1).getAbsolutePath(), false));
      ((StringBuilder)localObject2).append(localFile.getName());
      return FileProvider.a(this, paramString, new File(((StringBuilder)localObject2).toString()));
    }
    paramString = new StringBuilder();
    paramString.append("Error copying file to internal share folder!\r\n");
    paramString.append(((dw)localObject2).a);
    throw new Exception(paramString.toString());
  }
  
  String d(b paramB)
  {
    if (paramB.c == null) {
      return this.g;
    }
    return paramB.c.subProjectPrefix;
  }
  
  public void d()
  {
    try
    {
      android.support.v4.content.c.a(this).a(this.bq);
      localR = new r(CDadosCarregados.ay);
      if (CUtil.StringToInt(CUtil.a(localR, "PUSH_NOTIFICATION_ENABLED", null)) != 1) {}
    }
    catch (Exception localException1)
    {
      try
      {
        r localR;
        android.support.v4.content.c.a(this).a(this.bq, new IntentFilter("GCM_Message_Received"));
        if (CUtil.StringToInt(CUtil.a(localR, "PUSH_NOTIFICATION_SAVE_LOST_MESSAGES", null)) != 1) {
          MyFcmListenerService.b();
        }
        return;
        localException1 = localException1;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
  }
  
  void d(int paramInt)
  {
    if (CDadosCarregados.af > CDadosCarregados.ag)
    {
      if (paramInt == 2)
      {
        this.n = 0;
        return;
      }
      this.n = 1;
      return;
    }
    if (CDadosCarregados.af <= CDadosCarregados.ag)
    {
      if (paramInt == 1)
      {
        this.n = 0;
        return;
      }
      this.n = 1;
    }
  }
  
  void d(final b paramB, final int paramInt)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    String str1 = localCMyToken.GetNextToken();
    final String str2 = localCMyToken.GetNextToken();
    final String str3 = localCMyToken.GetNextToken();
    Object localObject;
    if (str1.equalsIgnoreCase("CURRENTCTRL"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("CTRL(");
      ((StringBuilder)localObject).append(CUtil.LongToString(paramInt));
      ((StringBuilder)localObject).append(")");
      localObject = ((StringBuilder)localObject).toString();
    }
    for (;;)
    {
      break;
      localObject = str1;
      if (CUtil.b(str1, 8).equalsIgnoreCase("DYN_CTRL"))
      {
        localObject = CalculaExpressaoStr(CUtil.a(str1, 9, str1.length() - 10), c(paramB));
        paramInt = this.f.d((String)localObject);
        if (paramInt < 0) {
          return;
        }
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("CTRL(");
        ((StringBuilder)localObject).append(CUtil.a(paramInt));
        ((StringBuilder)localObject).append(")");
        localObject = ((StringBuilder)localObject).toString();
      }
    }
    paramInt = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    int i1 = CUtil.StringToInt(str2);
    final boolean bool;
    switch (i1)
    {
    default: 
      switch (i1)
      {
      default: 
        switch (i1)
        {
        }
        break;
      }
    case 0: 
    case 4: 
    case 5: 
    case 6: 
      bool = true;
      break;
    case 1: 
    case 2: 
    case 3: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    case 11: 
      bool = false;
    }
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        Object localObject = "";
        if (CUtil.b(this.a, 4).equalsIgnoreCase("FORM"))
        {
          if (str2.equals("1")) {
            if (CMyFormDlg.this.J) {
              localObject = "1";
            }
          }
          for (;;)
          {
            break;
            localObject = "2";
            continue;
            if (str2.equals("6"))
            {
              localObject = CUtil.b(CMyFormDlg.this.q);
            }
            else if (str2.equals("7"))
            {
              localObject = (WindowManager.LayoutParams)CMyFormDlg.this.getWindow().getDecorView().getLayoutParams();
              localObject = CUtil.a(CMyFormDlg.this.b(((WindowManager.LayoutParams)localObject).x, 1));
            }
            else if (str2.equals("8"))
            {
              localObject = (WindowManager.LayoutParams)CMyFormDlg.this.getWindow().getDecorView().getLayoutParams();
              localObject = CUtil.a(CMyFormDlg.this.b(((WindowManager.LayoutParams)localObject).y, 2));
            }
            else if (str2.equals("9"))
            {
              localObject = (WindowManager.LayoutParams)CMyFormDlg.this.getWindow().getDecorView().getLayoutParams();
              localObject = CUtil.a(CMyFormDlg.this.b(((WindowManager.LayoutParams)localObject).width, 1));
            }
            else if (str2.equals("10"))
            {
              localObject = (WindowManager.LayoutParams)CMyFormDlg.this.getWindow().getDecorView().getLayoutParams();
              localObject = CUtil.a(CMyFormDlg.this.b(((WindowManager.LayoutParams)localObject).height, 2));
            }
          }
          CMyFormDlg.this.SetTarget(str3, (String)localObject, true, bool, CMyFormDlg.c(paramB));
          return;
        }
        int i = CUtil.StringToInt(CUtil.a("CTRL(", this.a, ")"));
        ag localAg = CMyFormDlg.this.f.b(i, paramInt - 1);
        if (localAg != null)
        {
          if (str2.equals("0")) {
            localObject = localAg.d();
          }
          for (;;)
          {
            break;
            if (str2.equals("1"))
            {
              if (localAg.g) {
                localObject = "1";
              } else {
                localObject = "2";
              }
            }
            else if (str2.equals("2"))
            {
              if (localAg.i)
              {
                String str = "1";
                localObject = str;
                if (localAg.v == 2)
                {
                  localObject = str;
                  if (((au)localAg).aj) {
                    localObject = "3";
                  }
                }
              }
              else
              {
                localObject = "2";
              }
            }
            else if (str2.equals("3"))
            {
              if (localAg.u) {
                localObject = "1";
              } else {
                localObject = "2";
              }
            }
            else if (str2.equals("4")) {
              localObject = localAg.e();
            } else if (str2.equals("5")) {
              localObject = localAg.m;
            } else if (str2.equals("6"))
            {
              if (localAg.k != null) {
                localObject = localAg.k.l;
              } else {
                localObject = localAg.l;
              }
            }
            else if (str2.equals("7")) {
              localObject = CUtil.a(localAg.f());
            } else if (str2.equals("8")) {
              localObject = CUtil.a(localAg.g());
            } else if (str2.equals("9")) {
              localObject = CUtil.a(localAg.h());
            } else if (str2.equals("10")) {
              localObject = CUtil.a(localAg.i());
            } else if (str2.equals("11"))
            {
              if ((localAg.j != ag.a.b) && (localAg.j != ag.a.c)) {
                localObject = "2";
              } else {
                localObject = "1";
              }
            }
            else if (str2.equals("13")) {
              localObject = CUtil.a(localAg.a);
            } else if (str2.equals("14"))
            {
              if (localAg.v == 2) {
                localObject = ((au)localAg).q();
              } else {
                localObject = "";
              }
            }
            else if (str2.equals("15")) {
              localObject = localAg.D;
            } else if (str2.equals("16")) {
              localObject = CUtil.a(localAg.E[CMyFormDlg.this.n]);
            } else if (str2.equals("17")) {
              localObject = CUtil.a(localAg.F);
            } else if (str2.equals("18"))
            {
              if (localAg.v == 11) {
                localObject = ((av)localAg).V;
              } else {
                localObject = "";
              }
            }
            else if (str2.equals("19")) {
              localObject = localAg.h;
            } else if (str2.equals("20")) {
              localObject = CUtil.a(localAg.G);
            } else if (str2.equals("49"))
            {
              if (localAg.v == 2)
              {
                localObject = (au)localAg;
                if (((au)localObject).ad) {
                  localObject = CUtil.a(((au)localObject).ae);
                } else {
                  localObject = "";
                }
              }
              else
              {
                localObject = "";
              }
            }
            else if (str2.equals("50"))
            {
              if (localAg.v == 2)
              {
                localObject = (au)localAg;
                if (((au)localObject).Z) {
                  localObject = CUtil.DoubleToString(((au)localObject).aa, 30, 10);
                } else {
                  localObject = "";
                }
              }
              else
              {
                localObject = "";
              }
            }
            else if (str2.equals("51"))
            {
              if (localAg.v == 2)
              {
                localObject = (au)localAg;
                if (((au)localObject).ab) {
                  localObject = CUtil.DoubleToString(((au)localObject).ac, 30, 10);
                } else {
                  localObject = "";
                }
              }
              else
              {
                localObject = "";
              }
            }
            else if (str2.equals("52")) {
              if (localAg.v == 2)
              {
                localObject = (au)localAg;
                if (((au)localObject).X) {
                  localObject = CUtil.a(((au)localObject).Y);
                } else {
                  localObject = "";
                }
              }
              else
              {
                localObject = "";
              }
            }
          }
          CMyFormDlg.this.SetTarget(str3, (String)localObject, true, bool, CMyFormDlg.c(paramB));
        }
      }
    });
  }
  
  int e(int paramInt)
  {
    double d1 = paramInt;
    double d2 = this.o;
    Double.isNaN(d1);
    return (int)(d1 * d2);
  }
  
  void e()
  {
    for (;;)
    {
      int i1;
      try
      {
        String[] arrayOfString = getPackageManager().getPackageInfo(getPackageName(), 4096).requestedPermissions;
        int i4 = arrayOfString.length;
        i1 = 0;
        int i3 = 0;
        int i2 = 0;
        if (i1 < i4)
        {
          String str = arrayOfString[i1];
          if (str.toUpperCase().indexOf("WRITE_EXTERNAL_STORAGE") > 0) {
            i3 = 1;
          }
          if (str.toUpperCase().indexOf("READ_EXTERNAL_STORAGE") > 0) {
            i2 = 1;
          }
        }
        else
        {
          if (i3 != 0)
          {
            c("android.permission.WRITE_EXTERNAL_STORAGE");
            return;
          }
          if (i2 != 0) {
            c("android.permission.READ_EXTERNAL_STORAGE");
          }
          return;
        }
      }
      catch (Exception localException)
      {
        return;
      }
      i1 += 1;
    }
  }
  
  int f(int paramInt)
  {
    double d1 = paramInt;
    double d2 = this.p;
    Double.isNaN(d1);
    return (int)(d1 * d2);
  }
  
  void f()
  {
    CMyFormDlg localCMyFormDlg2 = i();
    CMyFormDlg localCMyFormDlg1 = localCMyFormDlg2;
    if (localCMyFormDlg2 == null) {
      localCMyFormDlg1 = this;
    }
    int i2 = CDadosCarregados.Z;
    int i1 = i2;
    if (this.F)
    {
      i1 = i2;
      if (!this.D) {
        i1 = this.I;
      }
    }
    i2 = 0;
    while (i2 < 2)
    {
      bool = this.D;
      if (i2 == 0)
      {
        d1 = CDadosCarregados.X;
        d2 = CDadosCarregados.Y;
        d3 = d1;
        d4 = d2;
        if (this.H) {
          if (d1 > d2)
          {
            d3 = CDadosCarregados.ac;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d4 = d2 + d3;
            d2 = CDadosCarregados.ae;
            Double.isNaN(d1);
            Double.isNaN(d2);
            d3 = d1 + d2;
          }
          else
          {
            d3 = CDadosCarregados.ab;
            Double.isNaN(d2);
            Double.isNaN(d3);
            d4 = d2 + d3;
            d2 = CDadosCarregados.ad;
            Double.isNaN(d1);
            Double.isNaN(d2);
            d3 = d1 + d2;
          }
        }
        if ((d3 > d4) && (CDadosCarregados.af <= CDadosCarregados.ag))
        {
          d2 = CDadosCarregados.X + CDadosCarregados.ae;
          d1 = d2;
          if (!this.H)
          {
            d1 = CDadosCarregados.ab;
            Double.isNaN(d2);
            Double.isNaN(d1);
            d1 = d2 - d1;
          }
          d3 = d1;
          d4 = CDadosCarregados.Y + CDadosCarregados.ac;
          d1 = d4;
          d2 = d3;
          if (!this.H)
          {
            d1 = CDadosCarregados.ad;
            Double.isNaN(d4);
            Double.isNaN(d1);
            d1 = d4 - d1;
            d2 = d3;
          }
        }
        else
        {
          d1 = d3;
          d2 = d4;
          if (d3 <= d4)
          {
            d1 = d3;
            d2 = d4;
            if (CDadosCarregados.af > CDadosCarregados.ag)
            {
              d2 = CDadosCarregados.X + CDadosCarregados.ad;
              d1 = d2;
              if (!this.H)
              {
                d1 = CDadosCarregados.ac;
                Double.isNaN(d2);
                Double.isNaN(d1);
                d1 = d2 - d1;
              }
              d3 = d1;
              d4 = CDadosCarregados.Y + CDadosCarregados.ab;
              d1 = d4;
              d2 = d3;
              if (!this.H)
              {
                d1 = CDadosCarregados.ae;
                Double.isNaN(d4);
                Double.isNaN(d1);
                d1 = d4 - d1;
                d2 = d3;
              }
            }
          }
        }
      }
      else
      {
        d1 = CDadosCarregados.X;
        d3 = CDadosCarregados.Y;
        if (CDadosCarregados.X > CDadosCarregados.Y)
        {
          d2 = CDadosCarregados.ae;
          Double.isNaN(d1);
          Double.isNaN(d2);
          d1 += d2;
          d2 = d1;
          if (!this.H)
          {
            d2 = CDadosCarregados.ab;
            Double.isNaN(d2);
            d2 = d1 - d2;
          }
          d1 = CDadosCarregados.ac;
          Double.isNaN(d3);
          Double.isNaN(d1);
          d4 = d3 + d1;
          d3 = d2;
          d1 = d4;
          if (!this.H)
          {
            d1 = CDadosCarregados.ad;
            Double.isNaN(d1);
            d1 = d4 - d1;
            d3 = d2;
          }
        }
        for (;;)
        {
          d4 = d1;
          break;
          d2 = CDadosCarregados.ad;
          Double.isNaN(d1);
          Double.isNaN(d2);
          d1 += d2;
          d2 = d1;
          if (!this.H)
          {
            d2 = CDadosCarregados.ac;
            Double.isNaN(d2);
            d2 = d1 - d2;
          }
          d1 = CDadosCarregados.ab;
          Double.isNaN(d3);
          Double.isNaN(d1);
          d4 = d3 + d1;
          d3 = d2;
          d1 = d4;
          if (!this.H)
          {
            d1 = CDadosCarregados.ae;
            Double.isNaN(d1);
            d1 = d4 - d1;
            d3 = d2;
          }
        }
        if ((d4 > d3) && (CDadosCarregados.af > CDadosCarregados.ag))
        {
          d4 = CDadosCarregados.Y;
          d3 = CDadosCarregados.X;
          d1 = d3;
          d2 = d4;
          if (this.H)
          {
            d1 = CDadosCarregados.ab;
            Double.isNaN(d4);
            Double.isNaN(d1);
            d2 = d4 + d1;
            d1 = CDadosCarregados.ad;
            Double.isNaN(d3);
            Double.isNaN(d1);
            d1 = d3 + d1;
          }
        }
        else
        {
          d1 = d4;
          d2 = d3;
          if (d4 <= d3)
          {
            d1 = d4;
            d2 = d3;
            if (CDadosCarregados.af <= CDadosCarregados.ag)
            {
              d4 = CDadosCarregados.Y;
              d3 = CDadosCarregados.X;
              d1 = d3;
              d2 = d4;
              if (this.H)
              {
                d1 = CDadosCarregados.ac;
                Double.isNaN(d4);
                Double.isNaN(d1);
                d2 = d4 + d1;
                d1 = CDadosCarregados.ae;
                Double.isNaN(d3);
                Double.isNaN(d1);
                d1 = d3 + d1;
              }
            }
          }
        }
      }
      if (d1 > d2)
      {
        if (CDadosCarregados.af > CDadosCarregados.ag)
        {
          d4 = CDadosCarregados.af;
          d3 = CDadosCarregados.ag;
        }
        else
        {
          d4 = CDadosCarregados.ag;
          d3 = CDadosCarregados.af;
        }
      }
      else if (CDadosCarregados.af > CDadosCarregados.ag)
      {
        d4 = CDadosCarregados.ag;
        d3 = CDadosCarregados.af;
      }
      else
      {
        d4 = CDadosCarregados.af;
        d3 = CDadosCarregados.ag;
      }
      d5 = d2;
      if (this.F)
      {
        d5 = i1;
        Double.isNaN(d5);
        d5 = d2 - d5;
      }
      d6 = d5;
      if (localCMyFormDlg1.G)
      {
        d6 = d5;
        if (!this.H)
        {
          d2 = CDadosCarregados.aa;
          Double.isNaN(d2);
          d6 = d5 - d2;
        }
      }
      d2 = d3;
      if (this.F)
      {
        d2 = CDadosCarregados.ai;
        Double.isNaN(d2);
        d2 = d3 - d2;
      }
      d3 = d2;
      if (localCMyFormDlg1.G)
      {
        d3 = CDadosCarregados.ah;
        Double.isNaN(d3);
        d3 = d2 - d3;
      }
      this.T[i2] = (d1 / d4);
      this.U[i2] = (d6 / d3);
      this.P[i2] = a(this.P[i2], 1, i2);
      this.Q[i2] = a(this.Q[i2], 2, i2);
      this.R[i2] = a(this.R[i2], 1, i2);
      this.S[i2] = a(this.S[i2], 2, i2);
      i2 += 1;
    }
    boolean bool = this.D;
    double d5 = CDadosCarregados.af;
    double d6 = CDadosCarregados.ag;
    double d4 = CDadosCarregados.ag;
    double d3 = CDadosCarregados.af;
    double d2 = d4;
    double d1 = d3;
    if (this.F)
    {
      d1 = CDadosCarregados.ai;
      Double.isNaN(d4);
      Double.isNaN(d1);
      d2 = d4 - d1;
      d1 = CDadosCarregados.ai;
      Double.isNaN(d3);
      Double.isNaN(d1);
      d1 = d3 - d1;
    }
    d4 = d2;
    d3 = d1;
    if (this.G)
    {
      d3 = CDadosCarregados.ah;
      Double.isNaN(d3);
      d4 = d2 - d3;
      d2 = CDadosCarregados.ah;
      Double.isNaN(d2);
      d3 = d1 - d2;
    }
    Double.isNaN(d6);
    Double.isNaN(d5);
    this.o = (d6 / d5);
    this.p = (d3 / d4);
  }
  
  void f(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    final String str = CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    CalculaExpressaoStr(localCMyToken.GetNextToken(), c(paramB));
    final int i1 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    final int i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    if (!c("android.permission.CALL_PHONE"))
    {
      CDadosCarregados.a(e(paramB), -20, "User did not give permission to make phone call!", "1.2258", paramB.a);
      return;
    }
    CDadosCarregados.t = false;
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        try
        {
          Intent localIntent;
          if (i1 == 1) {
            localIntent = new Intent("android.intent.action.DIAL");
          } else {
            localIntent = new Intent("android.intent.action.CALL");
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("tel:");
          ((StringBuilder)localObject).append(str);
          localIntent.setData(Uri.parse(((StringBuilder)localObject).toString()));
          if (i2 == 1) {
            CMyFormDlg.this.startActivityForResult(localIntent, 12);
          } else {
            CMyFormDlg.this.startActivity(localIntent);
          }
          CDadosCarregados.a(CMyFormDlg.e(paramB));
          return;
        }
        catch (Exception localException)
        {
          Object localObject = CMyFormDlg.e(paramB);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Error starting activity for intent!\r\n");
          localStringBuilder.append(localException.getMessage());
          CDadosCarregados.a((ec)localObject, -1, localStringBuilder.toString(), "1.547", i1);
          CDadosCarregados.t = true;
        }
      }
    });
    if (i2 == 1) {
      while (!CDadosCarregados.t) {
        CUtil.e(250);
      }
    }
  }
  
  double g()
  {
    return this.p;
  }
  
  void g(b paramB)
  {
    int i3 = CUtil.StringToInt(CUtil.a("CTRL(", new CMyToken(a(paramB), b).GetNextToken(), ")"));
    int i4 = this.f.t;
    paramB = this.f.s;
    int i2 = 0;
    int i1 = 0;
    while (i1 < i4)
    {
      if (paramB[i1].f == i3)
      {
        paramB[i1].ao = true;
        return;
      }
      i1 += 1;
    }
    i4 = this.f.B;
    paramB = this.f.A;
    i1 = i2;
    while (i1 < i4)
    {
      if (paramB[i1].f == i3)
      {
        paramB[i1].as = true;
        return;
      }
      i1 += 1;
    }
  }
  
  boolean g(int paramInt)
  {
    CDadosCarregados.Q = paramInt;
    if (this.aa) {
      new w(new Handler(), this).a(this.j, -2, "X15");
    }
    if (this.aN > 0)
    {
      int i3 = 0;
      int i1 = 0;
      int i2;
      for (;;)
      {
        i2 = i3;
        if (i1 >= this.aN) {
          break;
        }
        if (this.aM[i1] == paramInt)
        {
          i2 = 1;
          break;
        }
        i1 += 1;
      }
      if (i2 != 0) {
        new w(new Handler(), this).a(this.aN, this.aK, this.aL, -2, paramInt, this.aM, null);
      }
    }
    return true;
  }
  
  void h()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Select ACTEVN, ACTACT, ACTPAR, ACTORD, ACTIDA From ");
    ((StringBuilder)localObject).append(this.g);
    ((StringBuilder)localObject).append("MFACT Where ACTIDJ = ");
    ((StringBuilder)localObject).append(this.j);
    ((StringBuilder)localObject).append(" And ACTIDC = -2 And ACTEVN Not Like 'X%' COLLATE NOCASE And ACTEVN Not Like 'V%' COLLATE NOCASE And ACTEVN Not Like 'S%' COLLATE NOCASE Order By ACTORD Asc");
    localObject = ((StringBuilder)localObject).toString();
    r localR = new r(CDadosCarregados.ay);
    if (!localR.a((String)localObject))
    {
      CUtil.a("Error", "Error reading the actions of the page!", this, this.bh);
      localR.a();
      return;
    }
    this.aN = localR.b();
    if (this.aN == 0)
    {
      localR.a();
      return;
    }
    this.aK = new b[this.aN];
    this.aL = new int[this.aN];
    this.aM = new int[this.aN];
    localR.d();
    int i1 = 0;
    while (!localR.f())
    {
      this.aK[i1] = new b();
      this.aK[i1].b = localR.b(2);
      this.aK[i1].a = CUtil.StringToInt(localR.b(4));
      this.aK[i1].c = null;
      this.aL[i1] = CUtil.StringToInt(localR.b(1));
      this.aM[i1] = CUtil.StringToInt(localR.b(0));
      localR.e();
      i1 += 1;
    }
    localR.a();
  }
  
  void h(final int paramInt)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        int j = CMyFormDlg.this.f.b;
        ag[] arrayOfAg = CMyFormDlg.this.f.a;
        int i = 0;
        while (i < j)
        {
          if ((paramInt != 0) && (paramInt == paramInt)) {
            arrayOfAg[i].c(false);
          }
          i += 1;
        }
        CMyFormDlg.this.f.a();
      }
    });
  }
  
  void h(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject).GetNextToken(), ")"));
    final int i3 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject).GetNextToken(), ")"), c(paramB)));
    final String str = CalculaExpressaoStr(((CMyToken)localObject).GetNextToken(), c(paramB));
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        paramB = this.f.s[i1];
        break label127;
      }
      i1 += 1;
    }
    paramB = null;
    label127:
    localObject = paramB;
    if (paramB == null)
    {
      ag localAg = this.f.b(i2);
      localObject = paramB;
      if (localAg != null)
      {
        localObject = paramB;
        if ((localAg instanceof az)) {
          localObject = (az)localAg;
        }
      }
    }
    if ((localObject != null) && (i3 > 0) && (i3 < ((az)localObject).aC))
    {
      ((az)localObject).bd[(i3 - 1)] = str;
      if (((az)localObject).aB) {
        CUtil.a(true, this.bh, new de()
        {
          public void a()
          {
            this.a.be[(i3 - 1)].setText(str);
          }
        });
      }
    }
  }
  
  public CMyFormDlg i()
  {
    for (CMyFormDlg localCMyFormDlg = this; localCMyFormDlg != null; localCMyFormDlg = localCMyFormDlg.e) {
      if (localCMyFormDlg.D) {
        return localCMyFormDlg;
      }
    }
    return localCMyFormDlg;
  }
  
  void i(final int paramInt)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        int j = CMyFormDlg.this.f.b;
        ag[] arrayOfAg = CMyFormDlg.this.f.a;
        int i = 0;
        while (i < j)
        {
          if ((paramInt != 0) && (paramInt == paramInt) && (arrayOfAg[i].g)) {
            arrayOfAg[i].c(true);
          }
          i += 1;
        }
        CMyFormDlg.this.f.a();
      }
    });
  }
  
  void i(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label101;
      }
      i1 += 1;
    }
    localObject1 = null;
    label101:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i2);
      localObject2 = localObject1;
      if (localAg != null)
      {
        localObject2 = localObject1;
        if ((localAg instanceof az)) {
          localObject2 = (az)localAg;
        }
      }
    }
    if (localObject2 != null)
    {
      if (((az)localObject2).as != az.Z)
      {
        CDadosCarregados.a(e(paramB), 63832, "This table control does not have cell selection mode activated!", "1.784", paramB.a);
        return;
      }
      SetTarget(str1, CUtil.a(((az)localObject2).m() + 1), true, false, c(paramB));
      SetTarget(str2, CUtil.a(((az)localObject2).aN + 1), true, false, c(paramB));
    }
    CDadosCarregados.a(e(paramB));
  }
  
  ag j(int paramInt)
  {
    int i2 = this.f.b;
    Object localObject1 = this.f.a;
    int i1 = 0;
    while (i1 < i2)
    {
      if (localObject1[i1].f == paramInt) {
        return localObject1[i1];
      }
      i1 += 1;
    }
    int i3 = this.f.B;
    localObject1 = this.f.A;
    i1 = 0;
    while (i1 < i3)
    {
      Object localObject2 = localObject1[i1].o();
      if (localObject2 != null)
      {
        int i4 = ((as)localObject2).b;
        localObject2 = ((as)localObject2).a;
        i2 = 0;
        while (i2 < i4)
        {
          if (localObject2[i2].f == paramInt) {
            return localObject2[i2];
          }
          i2 += 1;
        }
      }
      i1 += 1;
    }
    return null;
  }
  
  void j(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i4 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i5 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    int i6 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    localObject1 = ((CMyToken)localObject1).GetNextToken();
    int i3 = 0;
    boolean bool = false;
    int i1;
    if ((i6 != 1) && (i6 != 2))
    {
      CalculaExpressaoStr((String)localObject1, c(paramB));
      i1 = 0;
    }
    else
    {
      i1 = CUtil.StringToInt(CalculaExpressaoNum((String)localObject1, c(paramB)));
    }
    localObject1 = null;
    int i2 = 0;
    for (;;)
    {
      paramB = (b)localObject1;
      if (i2 >= this.f.t) {
        break;
      }
      if (this.f.s[i2].f == i4)
      {
        paramB = this.f.s[i2];
        break;
      }
      i2 += 1;
    }
    localObject1 = paramB;
    Object localObject2;
    if (paramB == null)
    {
      localObject2 = this.f.b(i4);
      localObject1 = paramB;
      if (localObject2 != null)
      {
        localObject1 = paramB;
        if ((localObject2 instanceof az)) {
          localObject1 = (az)localObject2;
        }
      }
    }
    if (localObject1 != null) {
      if (i5 > 0)
      {
        if (i5 > ((az)localObject1).aC) {
          return;
        }
        i4 = -1;
        i5 -= 1;
        if (i6 == 1)
        {
          if (i1 == 1) {
            bool = true;
          }
          ((az)localObject1).a(i5, bool);
          return;
        }
        if (i6 == 2)
        {
          i6 = i1 - 1;
          if (i6 >= 0)
          {
            i2 = i3;
            if (i6 >= ((az)localObject1).aC) {
              return;
            }
            for (;;)
            {
              i1 = i4;
              if (i2 >= ((az)localObject1).aC) {
                break;
              }
              if (localObject1.aE[i2] == i5)
              {
                i1 = i2;
                break;
              }
              i2 += 1;
            }
            if (i1 < 0) {
              return;
            }
            if (i1 < i6) {
              for (i2 = i1; i2 < i6; i2 = i3)
              {
                paramB = ((az)localObject1).aE;
                localObject2 = ((az)localObject1).aE;
                i3 = i2 + 1;
                paramB[i2] = localObject2[i3];
              }
            }
            if (i1 > i6)
            {
              i2 = i1;
              while (i2 > i6)
              {
                ((az)localObject1).aE[i2] = localObject1.aE[(i2 - 1)];
                i2 -= 1;
              }
            }
            if (i1 == i6) {
              return;
            }
            ((az)localObject1).aE[i6] = i5;
            ((az)localObject1).u();
          }
        }
      }
      else {}
    }
  }
  
  boolean j()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Select PAGIDJ,     '', PAGFSC, PAGWDT, PAGHGT, PAGDSC, PAGFPG, PAGTIT, PAGPOS, PAGXPS, PAGYPS, PAGBCR, PAGSQL, PAGIMG, PAGPTH, PAGBIX, PAGBIY, PAGBIW, PAGBIH, PAGHDD, PAGBIM, PAGEVN, PAGXP2, PAGYP2, PAGWD2, PAGHG2, PAGVSC, PAGHSC, PAGTBR, PAGSBR, PAGACL From ");
    ((StringBuilder)localObject1).append(this.g);
    ((StringBuilder)localObject1).append("MFPAG Where PAGIDJ = ");
    ((StringBuilder)localObject1).append(String.valueOf(this.j));
    Object localObject2 = ((StringBuilder)localObject1).toString();
    localObject1 = new r(CDadosCarregados.ay);
    if (!((r)localObject1).a((String)localObject2))
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Error reading the form parameters from database!\r\n");
      ((StringBuilder)localObject2).append(((r)localObject1).c);
      CUtil.a(((StringBuilder)localObject2).toString(), this);
      ((r)localObject1).a();
      return false;
    }
    ((r)localObject1).d();
    if (((r)localObject1).f())
    {
      CUtil.a("Error reading the form parameters from database!\r\nRecord not found!", this);
      CUtil.a((String)localObject2, this);
      ((r)localObject1).a();
      return false;
    }
    this.k = ((r)localObject1).b(7);
    this.q = CUtil.k(((r)localObject1).b(11));
    this.r = "";
    this.s = CUtil.StringToInt(((r)localObject1).b(20));
    this.t = CUtil.StringToInt(((r)localObject1).b(15));
    this.u = CUtil.StringToInt(((r)localObject1).b(16));
    if (!((r)localObject1).b(13).equals("")) {
      if (!((r)localObject1).b(14).equals(""))
      {
        this.r = CUtil.Q(((r)localObject1).b(14));
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(CUtil.a(this.r, false));
        ((StringBuilder)localObject2).append(((r)localObject1).b(13));
        this.r = ((StringBuilder)localObject2).toString();
      }
      else
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(CDadosCarregados.aE);
        ((StringBuilder)localObject2).append("\\");
        ((StringBuilder)localObject2).append(((r)localObject1).b(13));
        this.r = CUtil.Q(((StringBuilder)localObject2).toString());
      }
    }
    boolean bool;
    if (CUtil.StringToInt(((r)localObject1).b(8)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.E = bool;
    if (CUtil.StringToInt(((r)localObject1).b(2)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.D = bool;
    if (CUtil.StringToInt(((r)localObject1).b(28)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.F = bool;
    if (CUtil.StringToInt(((r)localObject1).b(29)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.G = bool;
    if (CUtil.StringToInt(((r)localObject1).b(19)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.J = bool;
    int i1 = CUtil.StringToInt(CUtil.a(((r)localObject1).b(30), 0, '\002'));
    if (i1 == 2)
    {
      this.aZ = CDadosCarregados.M;
    }
    else
    {
      if (i1 == 1) {
        bool = true;
      } else {
        bool = false;
      }
      this.aZ = bool;
    }
    i1 = CUtil.StringToInt(CUtil.a(((r)localObject1).b(30), 1, '\002'));
    if (i1 == 2)
    {
      this.H = CDadosCarregados.L;
    }
    else
    {
      if (i1 == 1) {
        bool = true;
      } else {
        bool = false;
      }
      this.H = bool;
    }
    if (Build.VERSION.SDK_INT < 19) {
      this.H = false;
    }
    if (CUtil.StringToInt(((r)localObject1).b(26)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.A = bool;
    if (CUtil.StringToInt(((r)localObject1).b(27)) == 1) {
      bool = true;
    } else {
      bool = false;
    }
    this.B = bool;
    this.P[0] = CUtil.StringToInt(((r)localObject1).b(9));
    this.Q[0] = CUtil.StringToInt(((r)localObject1).b(10));
    this.R[0] = CUtil.StringToInt(((r)localObject1).b(3));
    this.S[0] = CUtil.StringToInt(((r)localObject1).b(4));
    if (CDadosCarregados.aj == 2)
    {
      localObject2 = this.P;
      d1 = CUtil.StringToInt(((r)localObject1).b(9));
      d2 = CDadosCarregados.af;
      double d3 = CDadosCarregados.ag;
      Double.isNaN(d2);
      Double.isNaN(d3);
      d2 /= d3;
      Double.isNaN(d1);
      localObject2[1] = ((int)(d1 * d2));
      localObject2 = this.Q;
      d1 = CUtil.StringToInt(((r)localObject1).b(10));
      d2 = CDadosCarregados.ag;
      d3 = CDadosCarregados.af;
      Double.isNaN(d2);
      Double.isNaN(d3);
      d2 /= d3;
      Double.isNaN(d1);
      localObject2[1] = ((int)(d1 * d2));
      if (this.D)
      {
        this.S[1] = CUtil.StringToInt(((r)localObject1).b(3));
        this.R[1] = CUtil.StringToInt(((r)localObject1).b(4));
        if (this.F)
        {
          this.S[1] -= CDadosCarregados.ai;
          this.R[1] += CDadosCarregados.ai;
        }
        if (this.G)
        {
          this.S[1] -= CDadosCarregados.ah;
          this.R[1] += CDadosCarregados.ah;
        }
      }
      else
      {
        localObject2 = this.S;
        d1 = CUtil.StringToInt(((r)localObject1).b(4));
        d2 = CDadosCarregados.af;
        d3 = CDadosCarregados.ag;
        Double.isNaN(d2);
        Double.isNaN(d3);
        d2 /= d3;
        Double.isNaN(d1);
        localObject2[1] = ((int)(d1 * d2));
        localObject2 = this.R;
        d1 = CUtil.StringToInt(((r)localObject1).b(3));
        d2 = CDadosCarregados.ag;
        d3 = CDadosCarregados.af;
        Double.isNaN(d2);
        Double.isNaN(d3);
        d2 /= d3;
        Double.isNaN(d1);
        localObject2[1] = ((int)(d1 * d2));
      }
    }
    else if (CDadosCarregados.aj == 3)
    {
      this.P[1] = CUtil.StringToInt(((r)localObject1).b(22));
      this.Q[1] = CUtil.StringToInt(((r)localObject1).b(23));
      this.R[1] = CUtil.StringToInt(((r)localObject1).b(24));
      this.S[1] = CUtil.StringToInt(((r)localObject1).b(25));
    }
    double d1 = this.R[1];
    double d2 = this.R[0];
    Double.isNaN(d1);
    Double.isNaN(d2);
    this.o = (d1 / d2);
    d1 = this.S[1];
    d2 = this.S[0];
    Double.isNaN(d1);
    Double.isNaN(d2);
    this.p = (d1 / d2);
    if (this.D) {
      setTheme(16973829);
    } else if (!this.E) {
      setTheme(2131558413);
    }
    if (!this.J) {
      setVisible(false);
    }
    a(((r)localObject1).b(21));
    ((r)localObject1).a();
    return true;
  }
  
  void k()
  {
    Object localObject1;
    Object localObject3;
    if ((!this.A) && (!this.B))
    {
      if (!this.D) {
        setContentView(this.f.J, new AbsoluteLayout.LayoutParams(this.R[this.n], this.S[this.n], 0, 0));
      }
    }
    else if (!this.C)
    {
      if ((this.A) && (this.B))
      {
        this.C = true;
        localObject1 = new ScrollView(this);
        setContentView((View)localObject1);
        localObject3 = new HorizontalScrollView(this);
        ((ScrollView)localObject1).addView((View)localObject3);
        ((HorizontalScrollView)localObject3).addView(this.f.J, 0, new AbsoluteLayout.LayoutParams(this.R[this.n], this.S[this.n], 0, 0));
      }
      else if (this.A)
      {
        this.C = true;
        localObject1 = new ScrollView(this);
        setContentView((View)localObject1);
        ((ScrollView)localObject1).addView(this.f.J, 0, new AbsoluteLayout.LayoutParams(this.R[this.n], this.S[this.n], 0, 0));
      }
      else if (this.B)
      {
        this.C = true;
        localObject1 = new HorizontalScrollView(this);
        setContentView((View)localObject1);
        ((HorizontalScrollView)localObject1).addView(this.f.J, 0, new AbsoluteLayout.LayoutParams(this.R[this.n], this.S[this.n], 0, 0));
      }
    }
    else
    {
      localObject1 = this.f.J.getLayoutParams();
      ((ViewGroup.LayoutParams)localObject1).width = this.R[this.n];
      ((ViewGroup.LayoutParams)localObject1).height = this.S[this.n];
      this.f.J.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    }
    int i1 = this.R[this.n];
    int i2 = this.S[this.n];
    if (i1 != 0)
    {
      if (i2 == 0) {
        return;
      }
      Object localObject2;
      if (!this.r.equals(""))
      {
        try
        {
          if (this.N == null) {
            this.N = Bitmap.createBitmap(i1, i2, Bitmap.Config.ARGB_8888);
          } else if ((i1 != this.N.getWidth()) || (i2 != this.N.getHeight())) {
            this.N = Bitmap.createBitmap(i1, i2, Bitmap.Config.ARGB_8888);
          }
          this.N.eraseColor(this.q);
          localObject1 = new Canvas(this.N);
          localObject3 = CUtil.t(this.r, this.g);
          if (this.s == 2)
          {
            if (this.O == null) {
              this.O = BitmapFactory.decodeFile((String)localObject3);
            }
            if (this.O != null) {
              ((Canvas)localObject1).drawBitmap(this.O, null, new Rect(0, 0, i1, i2), CDadosCarregados.aq);
            } else {
              throw new Exception("Error loading background image file!");
            }
          }
          else
          {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            if (this.s == 3)
            {
              if (this.O == null) {
                this.O = BitmapFactory.decodeFile((String)localObject3);
              }
              if (this.O != null)
              {
                double d1 = i1;
                i3 = this.O.getWidth();
                double d2 = i3;
                Double.isNaN(d1);
                Double.isNaN(d2);
                d1 /= d2;
                d2 = i2;
                i3 = this.O.getHeight();
                double d3 = i3;
                Double.isNaN(d2);
                Double.isNaN(d3);
                d2 /= d3;
                d1 = Math.min(d1, d2);
                i3 = this.O.getWidth();
                d2 = i3;
                Double.isNaN(d2);
                i3 = (int)(d2 * d1);
                i4 = this.O.getHeight();
                d2 = i4;
                Double.isNaN(d2);
                i4 = (int)(d2 * d1);
                i5 = i1 - i3;
                i6 = i5 / 2;
                i7 = i2 - i4;
                localObject3 = new Rect(i6, i7 / 2, i5 / 2 + i3, i7 / 2 + i4);
                ((Canvas)localObject1).drawBitmap(this.O, null, (Rect)localObject3, CDadosCarregados.aq);
              }
              else
              {
                throw new Exception("Error loading background image file!");
              }
            }
            else if (this.s == 4)
            {
              if (this.O == null) {
                this.O = BitmapFactory.decodeFile((String)localObject3);
              }
              if (this.O != null)
              {
                i3 = this.O.getWidth();
                i4 = this.O.getHeight();
                i5 = i1 - i3;
                i6 = i5 / 2;
                i7 = i2 - i4;
                localObject3 = new Rect(i6, i7 / 2, i5 / 2 + i3, i7 / 2 + i4);
                ((Canvas)localObject1).drawBitmap(this.O, null, (Rect)localObject3, CDadosCarregados.aq);
              }
              else
              {
                throw new Exception("Error loading background image file!");
              }
            }
            else
            {
              if (this.O == null) {
                this.O = BitmapFactory.decodeFile((String)localObject3);
              }
              if (this.O != null)
              {
                i3 = this.O.getWidth();
                i4 = this.O.getHeight();
                localObject3 = new Rect(a(this.t, 1), a(this.u, 2), a(this.t, 1) + i3, a(this.u, 2) + i4);
                ((Canvas)localObject1).drawBitmap(this.O, null, (Rect)localObject3, CDadosCarregados.aq);
              }
              else
              {
                throw new Exception("Error loading background image file!");
              }
            }
          }
        }
        catch (Error localError)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Error applying background!\r\n");
          ((StringBuilder)localObject3).append(localError.toString());
          CUtil.a(((StringBuilder)localObject3).toString(), this);
          this.N = null;
        }
        catch (Exception localException)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Error applying background!\r\n");
          ((StringBuilder)localObject3).append(localException.getMessage());
          CUtil.a(((StringBuilder)localObject3).toString(), this);
          this.N = null;
        }
        if (this.N == null)
        {
          if (this.M == null)
          {
            this.M = new ImageView(this);
            this.M.setBackgroundColor(this.q);
            this.f.J.addView(this.M, 0, new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
            localObject2 = this.f;
            ((as)localObject2).I += 1;
          }
          else
          {
            this.M.setBackgroundColor(this.q);
            this.M.setLayoutParams(new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
          }
        }
        else if (this.M == null)
        {
          this.M = new ImageView(this);
          this.M.setImageBitmap(this.N);
          this.f.J.addView(this.M, 0, new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
          localObject2 = this.f;
          ((as)localObject2).I += 1;
        }
        else
        {
          this.M.setImageBitmap(this.N);
          this.M.setLayoutParams(new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
        }
        this.N = null;
        this.O = null;
      }
      else if (this.M == null)
      {
        this.M = new ImageView(this);
        this.M.setBackgroundColor(this.q);
        this.f.J.addView(this.M, 0, new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
        localObject2 = this.f;
        ((as)localObject2).I += 1;
      }
      else
      {
        this.M.setBackgroundColor(this.q);
        this.M.setLayoutParams(new AbsoluteLayout.LayoutParams(i1, i2, 0, 0));
      }
      if (this.V) {
        this.M.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            new w(new Handler(), CMyFormDlg.this).a(CMyFormDlg.this.j, -2, "X1");
          }
        });
      }
      if (this.ab) {
        this.M.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            new w(new Handler(), CMyFormDlg.this).a(CMyFormDlg.this.j, -2, "X18");
            return true;
          }
        });
      }
      if ((!this.D) && (!this.E))
      {
        localObject2 = getWindow().getDecorView();
        localObject3 = (WindowManager.LayoutParams)((View)localObject2).getLayoutParams();
        ((WindowManager.LayoutParams)localObject3).gravity = 51;
        ((WindowManager.LayoutParams)localObject3).horizontalMargin = 0.0F;
        ((WindowManager.LayoutParams)localObject3).verticalMargin = 0.0F;
        ((WindowManager.LayoutParams)localObject3).x = this.P[this.n];
        ((WindowManager.LayoutParams)localObject3).y = this.Q[this.n];
        getWindowManager().updateViewLayout((View)localObject2, (ViewGroup.LayoutParams)localObject3);
      }
      return;
    }
  }
  
  void k(int paramInt)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        int i = 0;
        while (i < CMyFormDlg.this.f.f)
        {
          if (CMyFormDlg.this.f.e[i].ao) {
            CMyFormDlg.this.f.e[i].r();
          } else if (CMyFormDlg.this.f.e[i].t != 5) {
            if (CMyFormDlg.this.f.e[i].t == 6) {
              CMyFormDlg.this.f.e[i].d(CDadosCarregados.aH);
            } else {
              CMyFormDlg.this.f.e[i].d(CMyFormDlg.this.f.e[i].S);
            }
          }
          i += 1;
        }
        i = 0;
        while (i < CMyFormDlg.this.f.j)
        {
          CMyFormDlg.this.f.i[i].h(0);
          i += 1;
        }
        i = 0;
        while (i < CMyFormDlg.this.f.l)
        {
          CMyFormDlg.this.f.k[i].h(0);
          i += 1;
        }
        i = 0;
        while (i < CMyFormDlg.this.f.t)
        {
          CMyFormDlg.this.f.s[i].n(-1);
          CMyFormDlg.this.f.s[i].o();
          i += 1;
        }
        i = 0;
        while (i < CMyFormDlg.this.f.B)
        {
          CMyFormDlg.this.f.A[i].o(-1);
          i += 1;
        }
        i = 0;
        while (i < CMyFormDlg.this.f.p)
        {
          CMyFormDlg.this.f.o[i].h(0);
          i += 1;
        }
        i = 0;
        while (i < CMyFormDlg.this.f.n)
        {
          CMyFormDlg.this.f.m[i].h(0);
          i += 1;
        }
      }
    });
  }
  
  void k(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i4 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject).GetNextToken(), ")"));
    int i5 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject).GetNextToken(), ")"), c(paramB)));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    String str = ((CMyToken)localObject).GetNextToken();
    int i6 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    if (str.equals(""))
    {
      if (i1 == 1) {
        i1 = -1;
      } else {
        i1 = -2;
      }
    }
    else {
      i1 = CUtil.StringToInt(CalculaExpressaoNum(str, c(paramB)));
    }
    localObject = null;
    int i3 = 0;
    int i2 = 0;
    for (;;)
    {
      paramB = (b)localObject;
      if (i2 >= this.f.t) {
        break;
      }
      if (this.f.s[i2].f == i4)
      {
        paramB = this.f.s[i2];
        break;
      }
      i2 += 1;
    }
    localObject = paramB;
    if (paramB == null)
    {
      ag localAg = this.f.b(i4);
      localObject = paramB;
      if (localAg != null)
      {
        localObject = paramB;
        if ((localAg instanceof az)) {
          localObject = (az)localAg;
        }
      }
    }
    if (localObject != null)
    {
      if (str.equals(""))
      {
        if (i6 == -1) {
          while (((az)localObject).an) {
            CUtil.e(250);
          }
        }
        if (i6 > 0) {
          while ((((az)localObject).an) && (((az)localObject).aG.get() < i6)) {
            CUtil.e(250);
          }
        }
      }
      if (i5 != 0)
      {
        if ((i5 > 0) && (i5 <= ((az)localObject).aC)) {
          ((az)localObject).b(i5 - 1, i1);
        }
        i2 = 1;
      }
      else
      {
        for (i2 = 0; i3 < ((az)localObject).aC; i2 = 1)
        {
          ((az)localObject).b(i3, i1);
          i3 += 1;
        }
      }
      if (i2 != 0) {
        ((az)localObject).t();
      }
    }
  }
  
  void l(final int paramInt)
  {
    CUtil.a(true, this.bh, new de()
    {
      public void a()
      {
        int j = CMyFormDlg.this.f.f;
        Object localObject = CMyFormDlg.this.f.e;
        int i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0)) {
            if (localObject[i].ao) {
              localObject[i].r();
            } else if (localObject[i].t != 5) {
              if (localObject[i].t == 6) {
                localObject[i].d(CDadosCarregados.aH);
              } else {
                localObject[i].d(localObject[i].S);
              }
            }
          }
          i += 1;
        }
        j = CMyFormDlg.this.f.j;
        localObject = CMyFormDlg.this.f.i;
        i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0)) {
            localObject[i].h(0);
          }
          i += 1;
        }
        j = CMyFormDlg.this.f.l;
        localObject = CMyFormDlg.this.f.k;
        i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0)) {
            localObject[i].h(0);
          }
          i += 1;
        }
        j = CMyFormDlg.this.f.t;
        localObject = CMyFormDlg.this.f.s;
        i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0))
          {
            localObject[i].n(-1);
            localObject[i].o();
          }
          i += 1;
        }
        j = CMyFormDlg.this.f.B;
        localObject = CMyFormDlg.this.f.A;
        i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0)) {
            localObject[i].o(-1);
          }
          i += 1;
        }
        j = CMyFormDlg.this.f.p;
        localObject = CMyFormDlg.this.f.o;
        i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0)) {
            localObject[i].h(0);
          }
          i += 1;
        }
        j = CMyFormDlg.this.f.n;
        localObject = CMyFormDlg.this.f.m;
        i = 0;
        while (i < j)
        {
          if ((paramInt == paramInt) || (paramInt == 0)) {
            localObject[i].h(0);
          }
          i += 1;
        }
      }
    });
  }
  
  void l(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    String str2 = ((CMyToken)localObject1).GetNextToken();
    String str1 = "0";
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label126;
      }
      i1 += 1;
    }
    localObject1 = null;
    label126:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i2);
      localObject2 = localObject1;
      if (localAg != null)
      {
        localObject2 = localObject1;
        if ((localAg instanceof az)) {
          localObject2 = (az)localAg;
        }
      }
    }
    localObject1 = str1;
    if (localObject2 != null)
    {
      localObject1 = str1;
      if (i3 > 0)
      {
        localObject1 = str1;
        if (i3 <= ((az)localObject2).aC) {
          localObject1 = CUtil.a(b(localObject2.aV[(i3 - 1)], 1));
        }
      }
    }
    SetTarget(str2, (String)localObject1, true, false, c(paramB));
  }
  
  boolean l()
  {
    this.f = new as();
    String str = this.f.a(this, 0, true, null, null);
    if (!str.equals(""))
    {
      CUtil.a(str, this);
      return false;
    }
    return true;
  }
  
  void m()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Select PGDSID, Count(*) From ");
    ((StringBuilder)localObject1).append(this.g);
    ((StringBuilder)localObject1).append("MFPGD, ");
    ((StringBuilder)localObject1).append(this.g);
    ((StringBuilder)localObject1).append("MFTAB Where PGDIDJ = ");
    ((StringBuilder)localObject1).append(CUtil.a(this.j));
    ((StringBuilder)localObject1).append(" And (PGDTIP = 2 OR PGDTIP = 3 OR PGDTIP = 12) And PGDSFD = '' And PGDSID <> '' And TABTAB = PGDSID Group By PGDSID");
    localObject1 = ((StringBuilder)localObject1).toString();
    r localR = new r(CDadosCarregados.ay);
    if (!localR.a((String)localObject1))
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Error reading the controls of the page!\r\n");
      ((StringBuilder)localObject1).append(localR.c);
      CUtil.a(((StringBuilder)localObject1).toString(), this);
      localR.a();
      return;
    }
    int i1 = localR.b();
    if (i1 <= 0)
    {
      localR.a();
      return;
    }
    this.ba = i1;
    this.bb = new dz[i1];
    localR.d();
    ah localAh = new ah();
    i1 = 0;
    while (!localR.f())
    {
      if (!localAh.a(this.g, "", localR.b(0), false))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Error reading ODBC configuration from the database!\r\n");
        ((StringBuilder)localObject1).append(localAh.z);
        CUtil.a(((StringBuilder)localObject1).toString(), this);
        return;
      }
      this.bb[i1] = new dz();
      this.bb[i1].p = CDadosCarregados.a.b;
      this.bb[i1].a = localR.b(0);
      this.bb[i1].h = false;
      this.bb[i1].E = false;
      this.bb[i1].A = true;
      this.bb[i1].B = false;
      this.bb[i1].C = "";
      this.bb[i1].D = "";
      this.bb[i1].F = new ea();
      this.bb[i1].F.c = -1;
      this.bb[i1].F.d = null;
      this.bb[i1].F.b = 0;
      this.bb[i1].F.a = 0;
      this.bb[i1].F.e = false;
      Object localObject2;
      if (!localAh.w)
      {
        this.bb[i1].h = true;
        this.bb[i1].F.e = true;
        this.bb[i1].p = localAh.n;
        this.bb[i1].q = localAh.o;
        this.bb[i1].r = localAh.p;
        this.bb[i1].s = localAh.q;
        this.bb[i1].t = localAh.r;
        this.bb[i1].u = localAh.s;
        this.bb[i1].v = localAh.t;
        this.bb[i1].w = localAh.u;
        this.bb[i1].x = localAh.v;
        this.bb[i1].y = localAh.l;
        this.bb[i1].z = localAh.m;
        this.bb[i1].A = localAh.a;
        this.bb[i1].B = localAh.b;
        this.bb[i1].C = localAh.d;
        this.bb[i1].i = localAh.k;
        this.bb[i1].j = localAh.j;
        this.bb[i1].k = localAh.i;
        this.bb[i1].l = localAh.h;
        this.bb[i1].m = localAh.g;
        this.bb[i1].n = localAh.e;
        this.bb[i1].o = localAh.f;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Select ");
        ((StringBuilder)localObject1).append(b(this.bb[i1].a, this.bb[i1].A, this.bb[i1].C));
        localObject1 = ((StringBuilder)localObject1).toString();
        if (this.bb[i1].A)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" From ");
          ((StringBuilder)localObject2).append(this.bb[i1].C);
          ((StringBuilder)localObject2).append("\"");
          ((StringBuilder)localObject2).append(localR.b(0));
          ((StringBuilder)localObject2).append("\"");
          localObject1 = ((StringBuilder)localObject2).toString();
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" From ");
          ((StringBuilder)localObject2).append(this.bb[i1].C);
          ((StringBuilder)localObject2).append(localR.b(0));
          localObject1 = ((StringBuilder)localObject2).toString();
        }
        localObject2 = localObject1;
        if (this.bb[i1].B)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" (nolock)");
          localObject2 = ((StringBuilder)localObject2).toString();
        }
      }
      else
      {
        this.bb[i1].t = localAh.r;
        this.bb[i1].p = localAh.n;
        if (this.bb[i1].p == CDadosCarregados.a.d)
        {
          this.bb[i1].r = localAh.p;
          this.bb[i1].D = localAh.c;
          localObject1 = this.bb[i1];
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(this.bb[i1].r);
          ((StringBuilder)localObject2).append(".");
          ((dz)localObject1).C = ((StringBuilder)localObject2).toString();
        }
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Select ");
        ((StringBuilder)localObject1).append(b(this.bb[i1].a, true, this.bb[i1].C));
        localObject1 = ((StringBuilder)localObject1).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(" From ");
        ((StringBuilder)localObject2).append(this.bb[i1].C);
        ((StringBuilder)localObject2).append("\"");
        ((StringBuilder)localObject2).append(localR.b(0));
        ((StringBuilder)localObject2).append("\"");
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      this.bb[i1].b = ((String)localObject2);
      this.bb[i1].c = "3.0";
      this.bb[i1].d = "";
      this.bb[i1].e = "";
      this.bb[i1].f = "";
      this.bb[i1].g = "";
      i1 += 1;
      localR.e();
      localAh.b();
    }
    localR.a();
  }
  
  void m(b paramB)
  {
    paramB = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", paramB.GetNextToken(), ")"));
    String str = paramB.GetNextToken();
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        paramB = this.f.s[i1];
        break label89;
      }
      i1 += 1;
    }
    paramB = null;
    label89:
    Object localObject = paramB;
    if (paramB == null)
    {
      ag localAg = this.f.b(i2);
      localObject = paramB;
      if (localAg != null)
      {
        localObject = paramB;
        if ((localAg instanceof az)) {
          localObject = (az)localAg;
        }
      }
    }
    if (localObject != null)
    {
      if (((az)localObject).aC <= 0) {
        return;
      }
      paramB = new CMyToken(str, c);
      i1 = 0;
      while (i1 < ((az)localObject).aC)
      {
        if (paramB.HasTokens()) {
          ((az)localObject).aE[i1] = (CUtil.StringToInt(CUtil.a("COL_IND(", paramB.GetNextToken(), ")")) - 1);
        } else {
          ((az)localObject).aE[i1] = 0;
        }
        i1 += 1;
      }
      ((az)localObject).u();
    }
  }
  
  void n()
  {
    this.bn = true;
    int i5 = this.q;
    int i1 = 0;
    int i2 = 0;
    while (!this.bm)
    {
      this.q = this.bj[i1];
      CUtil.a(false, this.bh, new de()
      {
        public void a()
        {
          CMyFormDlg.this.k();
        }
      });
      int i3 = i2 + 1;
      CUtil.e(this.bk);
      if ((this.bl > 0) && (i3 >= this.bl)) {
        break;
      }
      int i4 = i1 + 1;
      i1 = i4;
      i2 = i3;
      if (i4 > 1)
      {
        i1 = 0;
        i2 = i3;
      }
    }
    this.q = i5;
    CUtil.a(false, this.bh, new de()
    {
      public void a()
      {
        CMyFormDlg.this.k();
      }
    });
    this.bn = false;
  }
  
  void n(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i3 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    Object localObject3 = ((CMyToken)localObject1).GetNextToken();
    int i1 = i2;
    if (i2 < 0) {
      i1 = 0;
    }
    Object localObject2 = null;
    i2 = 0;
    for (;;)
    {
      localObject1 = localObject2;
      if (i2 >= this.f.t) {
        break;
      }
      if (this.f.s[i2].f == i3)
      {
        localObject1 = this.f.s[i2];
        break;
      }
      i2 += 1;
    }
    localObject2 = localObject1;
    Object localObject4;
    if (localObject1 == null)
    {
      localObject4 = this.f.b(i3);
      localObject2 = localObject1;
      if (localObject4 != null)
      {
        localObject2 = localObject1;
        if ((localObject4 instanceof az)) {
          localObject2 = (az)localObject4;
        }
      }
    }
    if (localObject2 != null)
    {
      if (((az)localObject2).aC <= 0) {
        return;
      }
      if (i1 == 0) {
        i1 = ((az)localObject2).s();
      } else {
        i1 -= 1;
      }
      localObject1 = new CMyToken((String)localObject3, c);
      localObject3 = new cj(i1);
      localObject4 = ((az)localObject2).a(i1, false, (cj)localObject3);
      if (!((String)localObject4).equals(""))
      {
        CUtil.a((String)localObject4, this);
        return;
      }
      while (((CMyToken)localObject1).HasTokens())
      {
        localObject4 = new CMyToken(((CMyToken)localObject1).GetNextToken(), d);
        i1 = CUtil.StringToInt(CUtil.a("COL_IND(", ((CMyToken)localObject4).GetNextToken(), ")"));
        localObject4 = CalculaExpressaoStr(((CMyToken)localObject4).GetNextToken(), c(paramB));
        ((az)localObject2).a(((cj)localObject3).a, i1 - 1, (String)localObject4, false, false);
      }
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          if (this.a.bT != null)
          {
            ba localBa = (ba)this.a.bT.getAdapter();
            if (localBa != null) {
              localBa.notifyDataSetChanged();
            }
          }
          this.a.a(this.a.m(), true, false);
        }
      });
    }
  }
  
  void o()
  {
    if (!this.bi) {
      return;
    }
    this.bm = true;
    this.f.d();
    while (this.bn) {
      CUtil.e(100);
    }
  }
  
  void o(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label105;
      }
      i1 += 1;
    }
    localObject1 = null;
    label105:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i2);
      localObject2 = localObject1;
      if (localAg != null)
      {
        localObject2 = localObject1;
        if ((localAg instanceof az)) {
          localObject2 = (az)localAg;
        }
      }
    }
    if (localObject2 != null) {
      if (i3 == -1)
      {
        ((az)localObject2).n(-1);
      }
      else
      {
        i2 = i3;
        if (i3 == 0)
        {
          i1 = ((az)localObject2).m();
          if (i1 < 0) {
            i1 = 0;
          } else {
            i1 += 1;
          }
          i2 = i1;
          if (i1 == 0)
          {
            CDadosCarregados.a(e(paramB), 63835, "Trying to delete selected line, but there is no selected line!", "1.778", paramB.a);
            return;
          }
        }
        i1 = i2 - 1;
        if (i1 >= ((az)localObject2).s())
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Trying to delete line ");
          ((StringBuilder)localObject2).append(CUtil.LongToString(i1 + 1));
          ((StringBuilder)localObject2).append(", but this line does not exist!");
          CDadosCarregados.a((ec)localObject1, 63834, ((StringBuilder)localObject2).toString(), "1.779", paramB.a);
          return;
        }
        ((az)localObject2).n(i1);
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent arg3)
  {
    Intent localIntent = getIntent();
    if (CDadosCarregados.A != localIntent.getLongExtra("m_lauchTimeStamp", 0L))
    {
      this.L = true;
      c();
      finish();
      return;
    }
    if (paramInt1 == 2)
    {
      this.bc = paramInt2;
      this.bd = ???;
      this.bg = true;
      synchronized (this.bf)
      {
        this.bf.notify();
        CDadosCarregados.y = this;
        return;
      }
    }
    ag localAg = null;
    Object localObject5 = null;
    if ((paramInt1 != 5) && (paramInt1 != 6) && (paramInt1 != 7))
    {
      if (paramInt1 == 8)
      {
        if (paramInt2 == -1)
        {
          CDadosCarregados.p.set(1);
          return;
        }
        CDadosCarregados.p.set(-1);
        return;
      }
      if (paramInt1 == 10)
      {
        CDadosCarregados.q.set(paramInt2);
        CDadosCarregados.r = ???;
        CDadosCarregados.s = true;
        return;
      }
      if (paramInt1 == 11)
      {
        if (paramInt2 == -1)
        {
          CDadosCarregados.H = ???.getStringExtra("SCAN_RESULT");
          CDadosCarregados.G = ???.getStringExtra("SCAN_RESULT_FORMAT");
          CDadosCarregados.F = 1;
          return;
        }
        if (paramInt2 == 0) {
          CDadosCarregados.F = -1;
        }
      }
      else
      {
        if (paramInt1 == 12)
        {
          CDadosCarregados.t = true;
          return;
        }
        if (paramInt1 == 14)
        {
          if (CDadosCarregados.cF != null)
          {
            if (paramInt2 == -1)
            {
              CDadosCarregados.cF.a(???.getStringArrayListExtra("android.speech.extra.RESULTS"), ???.getFloatArrayExtra("android.speech.extra.CONFIDENCE_SCORES"));
              return;
            }
            CDadosCarregados.cF.a(null, null);
          }
        }
        else
        {
          if (paramInt1 == 15)
          {
            CDadosCarregados.v = null;
            if ((paramInt2 == -1) && (??? != null)) {
              CDadosCarregados.v = ???.getData();
            }
            CDadosCarregados.u = 1;
            return;
          }
          if (paramInt1 == 16) {
            CDadosCarregados.w = 1;
          }
        }
      }
    }
    else
    {
      int i1 = 0;
      if (paramInt2 == -1)
      {
        Object localObject2;
        Object localObject4;
        if (paramInt1 == 6)
        {
          localObject2 = ???.getStringExtra("m_valor");
          localObject4 = localObject2;
          if (!???.getStringExtra("m_minVal").equals(""))
          {
            localObject4 = localObject2;
            if (CUtil.StringToDouble((String)localObject2) < CUtil.StringToDouble(???.getStringExtra("m_minVal"))) {
              localObject4 = ???.getStringExtra("m_minVal");
            }
          }
          localObject2 = localObject4;
          if (!???.getStringExtra("m_maxVal").equals(""))
          {
            localObject2 = localObject4;
            if (CUtil.StringToDouble((String)localObject4) > CUtil.StringToDouble(???.getStringExtra("m_maxVal"))) {
              localObject2 = ???.getStringExtra("m_maxVal");
            }
          }
        }
        else if (paramInt1 == 7)
        {
          paramInt1 = ???.getIntExtra("m_type", 0);
          str = ???.getStringExtra("m_mask");
          if (paramInt1 == 3)
          {
            localObject4 = ???.getStringExtra("m_dateVal");
            localObject2 = localObject4;
            if (!str.equals(""))
            {
              localObject2 = localObject4;
              if (!((String)localObject4).equals("")) {
                localObject2 = CUtil.j((String)localObject4, str);
              }
            }
          }
          else if (paramInt1 == 4)
          {
            localObject4 = ???.getStringExtra("m_timeVal");
            localObject2 = localObject4;
            if (!str.equals(""))
            {
              localObject2 = localObject4;
              if (!((String)localObject4).equals("")) {
                localObject2 = CUtil.l((String)localObject4, str);
              }
            }
          }
          else
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append(???.getStringExtra("m_dateVal"));
            ((StringBuilder)localObject2).append(???.getStringExtra("m_timeVal"));
            ((StringBuilder)localObject2).append("000");
            localObject4 = ((StringBuilder)localObject2).toString();
            localObject2 = localObject4;
            if (!str.equals(""))
            {
              localObject2 = localObject4;
              if (!((String)localObject4).equals("")) {
                localObject2 = CUtil.n((String)localObject4, str);
              }
            }
          }
        }
        else
        {
          localObject2 = ???.getStringExtra("m_texto");
        }
        String str = ???.getStringExtra("destination");
        paramInt2 = ???.getIntExtra("setFocus", 0);
        if (CUtil.b(str, 4).equalsIgnoreCase("CTRL"))
        {
          localAg = j(CUtil.StringToInt(CUtil.a(str, 5, str.length() - 6)));
          localObject4 = localObject5;
          if (localAg != null)
          {
            localObject4 = localObject5;
            if (localAg.v == 2) {
              localObject4 = (au)localAg;
            }
          }
        }
        label813:
        do
        {
          do
          {
            do
            {
              do
              {
                paramInt1 = -1;
                break label932;
                if (!CUtil.b(str, 3).equalsIgnoreCase("VAR")) {
                  break;
                }
                paramInt1 = CUtil.StringToInt(CUtil.a(str, 4, str.length() - 5));
                localObject4 = localObject5;
              } while (paramInt1 < 0);
              localObject4 = localObject5;
            } while (paramInt1 >= 1000);
            localObject4 = localAg;
            break label932;
            if (!CUtil.b(str, 4).equalsIgnoreCase("LVAR")) {
              break;
            }
            paramInt1 = CUtil.StringToInt(CUtil.a(str, 5, str.length() - 6));
            localObject4 = localObject5;
          } while (paramInt1 < 0);
          localObject4 = localObject5;
        } while (paramInt1 >= 100);
        for (;;)
        {
          break label813;
          localObject4 = localObject5;
          if (!CUtil.b(str, 4).equalsIgnoreCase("TVAR")) {
            break;
          }
          paramInt1 = CUtil.StringToInt(CUtil.a(str, 5, str.length() - 6));
          localObject4 = localObject5;
          if (paramInt1 < 0) {
            break;
          }
          localObject4 = localObject5;
          if (paramInt1 >= 100) {
            break;
          }
        }
        label932:
        CDadosCarregados.a(this.z);
        if (localObject4 != null)
        {
          ((au)localObject4).d((String)localObject2);
          if (paramInt2 == 1)
          {
            ((au)localObject4).aD.requestFocus();
            ((au)localObject4).aD.setSelection(((au)localObject4).aD.getText().length());
          }
        }
        if (paramInt1 >= 0) {
          if (CUtil.b(str, 3).equalsIgnoreCase("VAR")) {
            CDadosCarregados.a(null, null, paramInt1, (String)localObject2, false, true, ???.getStringExtra("subProjectPrefix"));
          } else if (CUtil.b(str, 4).equalsIgnoreCase("LVAR")) {
            CDadosCarregados.a(this, null, paramInt1, (String)localObject2, false, true, ???.getStringExtra("subProjectPrefix"));
          } else if (CUtil.b(str, 4).equalsIgnoreCase("TVAR")) {
            CDadosCarregados.a(null, this.y, paramInt1, (String)localObject2, false, true, ???.getStringExtra("subProjectPrefix"));
          }
        }
      }
      else
      {
        paramInt1 = i1;
        if (??? != null) {
          paramInt1 = ???.getIntExtra("actionID", 0);
        }
        CDadosCarregados.a(this.z, -8, "User canceled!", "1.534", paramInt1);
      }
      this.w = true;
      synchronized (this.x)
      {
        this.x.notify();
        return;
      }
    }
  }
  
  public void onBackPressed()
  {
    if (this.h) {
      return;
    }
    if (this.ac) {
      new w(new Handler(), this).a(this.j, -2, "X19");
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if ((CDadosCarregados.aj == 1) && (this.n == 0)) {
      return;
    }
    a(paramConfiguration.orientation);
    if (this.Z) {
      new w(new Handler(), this).a(this.j, -2, "X14");
    }
  }
  
  public void onCreate(final Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      super.onCreate(paramBundle);
      this.L = true;
      c();
      finish();
      System.runFinalizersOnExit(true);
      System.exit(0);
      return;
    }
    Object localObject1 = getIntent();
    if (CDadosCarregados.A != ((Intent)localObject1).getLongExtra("m_lauchTimeStamp", 0L))
    {
      super.onCreate(paramBundle);
      this.L = true;
      c();
      finish();
      return;
    }
    if (CDadosCarregados.ay == null)
    {
      super.onCreate(paramBundle);
      this.L = true;
      c();
      finish();
      return;
    }
    this.g = ((Intent)localObject1).getStringExtra("SUB_PROJECT");
    if (this.g == null) {
      this.g = "";
    }
    this.j = ((Intent)localObject1).getIntExtra("PAGIDJ", -1);
    if (this.j == -1)
    {
      CUtil.a("Error loading form! No form identifier received in intent!", this);
      super.onCreate(paramBundle);
      return;
    }
    this.be = ((Intent)localObject1).getBooleanExtra("FIRST_FORM", false);
    if (this.be) {}
    try
    {
      com.google.android.gms.e.a.a(this);
      CDadosCarregados.z = this;
      Object localObject2 = new IntentFilter();
      ((IntentFilter)localObject2).addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
      ((IntentFilter)localObject2).addAction("android.bluetooth.device.action.FOUND");
      ((IntentFilter)localObject2).addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
      ((IntentFilter)localObject2).addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
      registerReceiver(this.bp, (IntentFilter)localObject2);
      d();
      this.l = ((Intent)localObject1).getIntExtra("START_PLANE", 0);
      localObject1 = ((Intent)localObject1).getStringArrayExtra("VARS_VALUES");
      if (localObject1 != null)
      {
        localObject2 = getIntent().getBooleanArrayExtra("VARS_IS_STRING");
        i1 = 0;
        while (i1 < 100)
        {
          if (localObject1[i1] != null) {
            CDadosCarregados.a(this, null, i1, localObject1[i1], true, localObject2[i1], "");
          }
          i1 += 1;
        }
      }
      if (!j())
      {
        super.onCreate(paramBundle);
        return;
      }
      if ((this.R[0] == 0) || (this.S[0] == 0)) {
        setTheme(16973909);
      }
      super.onCreate(paramBundle);
      setFinishOnTouchOutside(false);
      if (this.D) {
        if (CDadosCarregados.aj == 1)
        {
          if (CDadosCarregados.af > CDadosCarregados.ag) {
            if (getResources().getConfiguration().orientation != 2) {
              setRequestedOrientation(6);
            }
          }
          for (;;)
          {
            i1 = 1;
            break label474;
            setRequestedOrientation(6);
            break label472;
            if (CDadosCarregados.af >= CDadosCarregados.ag) {
              break label472;
            }
            if (getResources().getConfiguration().orientation == 1) {
              break;
            }
            setRequestedOrientation(7);
          }
          setRequestedOrientation(7);
        }
        else
        {
          setRequestedOrientation(2);
        }
      }
      label472:
      int i1 = 0;
      label474:
      if (!this.F) {
        requestWindowFeature(1);
      }
      if ((!this.G) || (this.H)) {
        getWindow().setFlags(1024, 1024);
      }
      if (this.H)
      {
        paramBundle = getWindow().getDecorView();
        paramBundle.setSystemUiVisibility(5894);
        paramBundle.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
        {
          public void onSystemUiVisibilityChange(int paramAnonymousInt)
          {
            paramBundle.setSystemUiVisibility(this.b);
          }
        });
      }
      if (CDadosCarregados.aj == 1) {
        this.n = 0;
      } else if (CDadosCarregados.af > CDadosCarregados.ag)
      {
        if (getResources().getConfiguration().orientation == 2) {
          this.n = 0;
        } else {
          this.n = 1;
        }
      }
      else if (CDadosCarregados.af < CDadosCarregados.ag) {
        if (getResources().getConfiguration().orientation == 1) {
          this.n = 0;
        } else {
          this.n = 1;
        }
      }
      int i2 = i1;
      if (this.F)
      {
        i2 = i1;
        if (CDadosCarregados.Z < 0) {
          i2 = 1;
        }
      }
      if ((i2 == 0) && ((this.D) || (this.E)) && ((this.D) || (!this.F)))
      {
        p();
        return;
      }
      getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          CMyFormDlg.this.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
          if (!CMyFormDlg.this.aV) {
            CMyFormDlg.a(CMyFormDlg.this);
          }
        }
      });
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  protected void onDestroy()
  {
    this.L = true;
    if (this.be) {}
    try
    {
      unregisterReceiver(this.bp);
    }
    catch (Exception localException2)
    {
      try
      {
        android.support.v4.content.c.a(this).a(this.bq);
        if (this.bo == null) {}
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            unregisterReceiver(this.bo);
            this.bo = null;
            super.onDestroy();
            return;
            localException1 = localException1;
          }
          localException2 = localException2;
        }
        catch (Exception localException3)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == this.aO) {
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    if ((CDadosCarregados.R == 1) || (CDadosCarregados.R == 3)) {
      g(cm.a(paramInt));
    }
    if ((paramInt == 0) && (CDadosCarregados.bA.b()) && ((paramKeyEvent.getScanCode() == 148) || (paramKeyEvent.getScanCode() == 87) || (paramKeyEvent.getScanCode() == 88))) {
      CDadosCarregados.bA.e();
    }
    if (CDadosCarregados.bx != null)
    {
      a localA = CDadosCarregados.bx;
      if (paramInt == a.b)
      {
        localA = CDadosCarregados.bx;
        if (a.a) {
          CDadosCarregados.bx.d(this);
        }
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == this.aO) {
      return super.onKeyUp(paramInt, paramKeyEvent);
    }
    if ((CDadosCarregados.R == 2) || (CDadosCarregados.R == 3)) {
      g(cm.a(paramInt));
    }
    if ((paramInt == 0) && (CDadosCarregados.bA.b()) && ((paramKeyEvent.getScanCode() == 148) || (paramKeyEvent.getScanCode() == 87) || (paramKeyEvent.getScanCode() == 88))) {
      CDadosCarregados.bA.f();
    }
    if (CDadosCarregados.bx != null)
    {
      a localA = CDadosCarregados.bx;
      if (paramInt == a.b)
      {
        localA = CDadosCarregados.bx;
        if (a.a) {
          CDadosCarregados.bx.e(this);
        }
      }
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void onNewIntent(Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.nfc.action.NDEF_DISCOVERED"))
    {
      if (CDadosCarregados.cC != null) {
        CDadosCarregados.cC.a(paramIntent);
      }
    }
    else if (paramIntent.getAction().equals("android.nfc.action.TAG_DISCOVERED"))
    {
      if (CDadosCarregados.cC != null) {
        CDadosCarregados.cC.a(paramIntent);
      }
    }
    else if ((paramIntent.getAction().equals("android.nfc.action.TECH_DISCOVERED")) && (CDadosCarregados.cC != null)) {
      CDadosCarregados.cC.a(paramIntent);
    }
  }
  
  public void onPause()
  {
    super.onPause();
    if (CDadosCarregados.cC != null) {
      CDadosCarregados.cC.a(this);
    }
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    paramInt = paramArrayOfInt.length;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramInt > 0)
    {
      bool1 = bool2;
      if (paramArrayOfInt[0] == 0) {
        bool1 = true;
      }
    }
    CDadosCarregados.cy = bool1;
    CDadosCarregados.cx = true;
  }
  
  public void onRestart()
  {
    super.onRestart();
    if ((CDadosCarregados.y == this) && (!this.K)) {
      CDadosCarregados.e();
    }
  }
  
  public void onResume()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 4497	android/app/Activity:onResume	()V
    //   4: aload_0
    //   5: getfield 327	com/sysdevsolutions/kclientlibv40/CMyFormDlg:H	Z
    //   8: ifeq +16 -> 24
    //   11: aload_0
    //   12: invokevirtual 818	com/sysdevsolutions/kclientlibv40/CMyFormDlg:getWindow	()Landroid/view/Window;
    //   15: invokevirtual 840	android/view/Window:getDecorView	()Landroid/view/View;
    //   18: sipush 5894
    //   21: invokevirtual 4425	android/view/View:setSystemUiVisibility	(I)V
    //   24: getstatic 3646	com/sysdevsolutions/kclientlibv40/CDadosCarregados:cC	Lcom/sysdevsolutions/kclientlibv40/bd;
    //   27: ifnull +10 -> 37
    //   30: getstatic 3646	com/sysdevsolutions/kclientlibv40/CDadosCarregados:cC	Lcom/sysdevsolutions/kclientlibv40/bd;
    //   33: aload_0
    //   34: invokevirtual 4499	com/sysdevsolutions/kclientlibv40/bd:b	(Lcom/sysdevsolutions/kclientlibv40/CMyFormDlg;)V
    //   37: getstatic 931	com/sysdevsolutions/kclientlibv40/CDadosCarregados:at	Z
    //   40: ifne +9 -> 49
    //   43: getstatic 932	com/sysdevsolutions/kclientlibv40/CDadosCarregados:as	Z
    //   46: ifeq +139 -> 185
    //   49: getstatic 936	com/sysdevsolutions/kclientlibv40/CDadosCarregados:au	J
    //   52: lconst_0
    //   53: lcmp
    //   54: ifle +131 -> 185
    //   57: invokestatic 956	java/util/Calendar:getInstance	()Ljava/util/Calendar;
    //   60: invokevirtual 960	java/util/Calendar:getTimeInMillis	()J
    //   63: lstore_1
    //   64: aload_0
    //   65: getfield 310	com/sysdevsolutions/kclientlibv40/CMyFormDlg:i	Ljava/lang/Runnable;
    //   68: ifnull +17 -> 85
    //   71: aload_0
    //   72: getfield 402	com/sysdevsolutions/kclientlibv40/CMyFormDlg:bh	Landroid/os/Handler;
    //   75: aload_0
    //   76: getfield 310	com/sysdevsolutions/kclientlibv40/CMyFormDlg:i	Ljava/lang/Runnable;
    //   79: invokevirtual 3399	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
    //   82: goto +3 -> 85
    //   85: lload_1
    //   86: getstatic 936	com/sysdevsolutions/kclientlibv40/CDadosCarregados:au	J
    //   89: lsub
    //   90: ldc2_w 945
    //   93: lcmp
    //   94: ifle +63 -> 157
    //   97: getstatic 665	com/sysdevsolutions/kclientlibv40/CDadosCarregados:y	Lcom/sysdevsolutions/kclientlibv40/CMyFormDlg;
    //   100: ifnull +85 -> 185
    //   103: ldc_w 4501
    //   106: getstatic 4505	com/sysdevsolutions/kclientlibv40/CDadosCarregados:m_androidMainActivity	Landroid/app/Activity;
    //   109: invokestatic 943	com/sysdevsolutions/kclientlibv40/CUtil:a	(Ljava/lang/String;Landroid/content/Context;)V
    //   112: new 1430	android/content/Intent
    //   115: dup
    //   116: invokespecial 4506	android/content/Intent:<init>	()V
    //   119: astore_3
    //   120: aload_3
    //   121: ldc_w 4508
    //   124: iconst_0
    //   125: invokevirtual 1485	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   128: pop
    //   129: getstatic 665	com/sysdevsolutions/kclientlibv40/CDadosCarregados:y	Lcom/sysdevsolutions/kclientlibv40/CMyFormDlg;
    //   132: iconst_5
    //   133: aload_3
    //   134: invokevirtual 4512	com/sysdevsolutions/kclientlibv40/CMyFormDlg:setResult	(ILandroid/content/Intent;)V
    //   137: getstatic 665	com/sysdevsolutions/kclientlibv40/CDadosCarregados:y	Lcom/sysdevsolutions/kclientlibv40/CMyFormDlg;
    //   140: iconst_1
    //   141: putfield 335	com/sysdevsolutions/kclientlibv40/CMyFormDlg:L	Z
    //   144: getstatic 665	com/sysdevsolutions/kclientlibv40/CDadosCarregados:y	Lcom/sysdevsolutions/kclientlibv40/CMyFormDlg;
    //   147: invokevirtual 4273	com/sysdevsolutions/kclientlibv40/CMyFormDlg:c	()V
    //   150: getstatic 665	com/sysdevsolutions/kclientlibv40/CDadosCarregados:y	Lcom/sysdevsolutions/kclientlibv40/CMyFormDlg;
    //   153: invokevirtual 4276	com/sysdevsolutions/kclientlibv40/CMyFormDlg:finish	()V
    //   156: return
    //   157: aload_0
    //   158: getfield 310	com/sysdevsolutions/kclientlibv40/CMyFormDlg:i	Ljava/lang/Runnable;
    //   161: ifnull +24 -> 185
    //   164: aload_0
    //   165: getfield 402	com/sysdevsolutions/kclientlibv40/CMyFormDlg:bh	Landroid/os/Handler;
    //   168: aload_0
    //   169: getfield 310	com/sysdevsolutions/kclientlibv40/CMyFormDlg:i	Ljava/lang/Runnable;
    //   172: ldc2_w 945
    //   175: lload_1
    //   176: getstatic 936	com/sysdevsolutions/kclientlibv40/CDadosCarregados:au	J
    //   179: lsub
    //   180: lsub
    //   181: invokevirtual 950	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
    //   184: pop
    //   185: return
    //   186: astore_3
    //   187: goto -102 -> 85
    //   190: astore_3
    //   191: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	this	CMyFormDlg
    //   63	113	1	l1	long
    //   119	15	3	localIntent	Intent
    //   186	1	3	localException1	Exception
    //   190	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   71	82	186	java/lang/Exception
    //   164	185	190	java/lang/Exception
  }
  
  public void onStop()
  {
    super.onStop();
    if ((CDadosCarregados.y == this) && (!this.K)) {
      CDadosCarregados.d();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.h) {
      return true;
    }
    if ((!this.D) && (this.ac) && (CUtil.c(CDadosCarregados.aF, "MSSSmartPhone")))
    {
      Rect localRect = new Rect();
      getWindow().getDecorView().getHitRect(localRect);
      if ((!localRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY())) && (paramMotionEvent.getAction() == 0))
      {
        new w(new Handler(), this).a(this.j, -2, "X19");
        return true;
      }
    }
    return super.onTouchEvent(paramMotionEvent);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if ((this.H) && (paramBoolean)) {
      getWindow().getDecorView().setSystemUiVisibility(5894);
    }
  }
  
  void p(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    String str = ((CMyToken)localObject1).GetNextToken();
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label139;
      }
      i1 += 1;
    }
    localObject1 = null;
    label139:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i2);
      localObject2 = localObject1;
      if (localAg != null)
      {
        localObject2 = localObject1;
        if ((localAg instanceof az)) {
          localObject2 = (az)localAg;
        }
      }
    }
    if (localObject2 != null)
    {
      i2 = i3;
      if (i3 == 0)
      {
        i1 = ((az)localObject2).m();
        if (i1 < 0) {
          i1 = 0;
        } else {
          i1 += 1;
        }
        i2 = i1;
        if (i1 == 0)
        {
          CDadosCarregados.a(e(paramB), 63835, "Trying to get value from selected line, but there is no selected line!", "1.786", paramB.a);
          return;
        }
      }
      i1 = i2 - 1;
      if (((i1 < ((az)localObject2).aG.get()) && (i1 >= 0)) || ((i1 < ((az)localObject2).s()) && (i1 >= 0)))
      {
        i2 = i4 - 1;
        if (i2 >= ((az)localObject2).aC)
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Trying to get value from column ");
          ((StringBuilder)localObject2).append(CUtil.LongToString(i2 + 1));
          ((StringBuilder)localObject2).append(", but this column does not exist!");
          CDadosCarregados.a((ec)localObject1, 63833, ((StringBuilder)localObject2).toString(), "1.788", paramB.a);
          return;
        }
        localObject1 = ((az)localObject2).a(i1, i2);
        boolean bool;
        if (localObject2.bf[i2] != 2) {
          bool = true;
        } else {
          bool = false;
        }
        SetTarget(str, (String)localObject1, true, bool, c(paramB));
      }
      else
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to get value from line ");
        ((StringBuilder)localObject2).append(CUtil.LongToString(i1 + 1));
        ((StringBuilder)localObject2).append(", but this line does not exist!");
        CDadosCarregados.a((ec)localObject1, 63834, ((StringBuilder)localObject2).toString(), "1.787", paramB.a);
        return;
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void q(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    Object localObject2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label147;
      }
      i1 += 1;
    }
    localObject1 = null;
    label147:
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i2);
      if ((localAg != null) && ((localAg instanceof az))) {
        localObject1 = (az)localAg;
      }
    }
    if (localObject1 != null)
    {
      i2 = i3;
      if (i3 == 0)
      {
        i1 = ((az)localObject1).m();
        if (i1 < 0) {
          i1 = 0;
        } else {
          i1 += 1;
        }
        i2 = i1;
        if (i1 == 0)
        {
          CDadosCarregados.a(e(paramB), 63835, "Trying to set value in selected line, but there is no selected line!", "1.789", paramB.a);
          return;
        }
      }
      i1 = i2 - 1;
      if (((i1 < ((az)localObject1).aG.get()) && (i1 >= 0)) || ((i1 < ((az)localObject1).s()) && (i1 >= 0)))
      {
        i2 = i4 - 1;
        if (i2 >= ((az)localObject1).aC)
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Trying to set value in column ");
          ((StringBuilder)localObject2).append(CUtil.LongToString(i2 + 1));
          ((StringBuilder)localObject2).append(", but this column does not exist!");
          CDadosCarregados.a((ec)localObject1, 63833, ((StringBuilder)localObject2).toString(), "1.791", paramB.a);
          return;
        }
        ((az)localObject1).a(i1, i2, (String)localObject2, true, true);
      }
      else
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to set value in line ");
        ((StringBuilder)localObject2).append(CUtil.LongToString(i1 + 1));
        ((StringBuilder)localObject2).append(", but this line does not exist!");
        CDadosCarregados.a((ec)localObject1, 63834, ((StringBuilder)localObject2).toString(), "1.790", paramB.a);
        return;
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void r(b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    CUtil.StringToInt(CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")"));
    CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", localCMyToken.GetNextToken(), ")"), c(paramB)));
    CDadosCarregados.a(e(paramB));
  }
  
  void s(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i6 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    int i5 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    String str1;
    if (i5 != 3) {
      str1 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    } else {
      str1 = CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB));
    }
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    String str2 = ((CMyToken)localObject1).GetNextToken();
    boolean bool = true;
    int i2 = i1;
    if (i1 < 1) {
      i2 = 1;
    }
    int i3 = -1;
    Object localObject2 = null;
    i1 = 0;
    for (;;)
    {
      localObject1 = localObject2;
      if (i1 >= this.f.t) {
        break;
      }
      if (this.f.s[i1].f == i6)
      {
        localObject1 = this.f.s[i1];
        break;
      }
      i1 += 1;
    }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i6);
      localObject2 = localObject1;
      if (localAg != null)
      {
        localObject2 = localObject1;
        if ((localAg instanceof az)) {
          localObject2 = (az)localAg;
        }
      }
    }
    i1 = i3;
    if (localObject2 != null) {
      if (i5 != 3)
      {
        if (i5 != 2) {
          bool = false;
        }
        i1 = ((az)localObject2).a(i2 - 1, i4 - 1, bool, str1);
      }
      else
      {
        i1 = ((az)localObject2).a(i2 - 1, i4 - 1, CUtil.StringToDouble(str1));
      }
    }
    i2 = i1;
    if (i1 >= 0) {
      i2 = i1 + 1;
    }
    SetTarget(str2, CUtil.LongToString(i2), true, false, c(paramB));
  }
  
  void t(final b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject).GetNextToken(), ")"));
    final int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    final int i4 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject).GetNextToken(), ")"), c(paramB)));
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject = this.f.s[i1];
        break label132;
      }
      i1 += 1;
    }
    localObject = null;
    label132:
    if (localObject == null)
    {
      ag localAg = this.f.b(i2);
      if ((localAg != null) && ((localAg instanceof az))) {
        localObject = (az)localAg;
      }
    }
    if (localObject != null) {
      CUtil.a(true, this.bh, new de()
      {
        public void a()
        {
          if (this.a.as != az.Z)
          {
            CDadosCarregados.a(CMyFormDlg.e(paramB), 63832, "This table control does not have cell selection mode activated!", "1.785", paramB.a);
            return;
          }
          if (i3 != 0) {
            this.a.a(i3 - 1, true, true);
          }
          int i;
          if (i4 == 0) {
            i = this.a.aN;
          } else {
            i = i4 - 1;
          }
          this.a.p(i);
        }
      });
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void u(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    String str1 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    String str2 = CalculaExpressaoStr(((CMyToken)localObject1).GetNextToken(), c(paramB));
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label162;
      }
      i1 += 1;
    }
    localObject1 = null;
    label162:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      ag localAg = this.f.b(i2);
      localObject2 = localObject1;
      if (localAg != null)
      {
        localObject2 = localObject1;
        if ((localAg instanceof az)) {
          localObject2 = (az)localAg;
        }
      }
    }
    if (localObject2 != null)
    {
      if (!((az)localObject2).aq)
      {
        CDadosCarregados.a(e(paramB), 63830, "Trying to set color in table control, but the advanced style is not active!", "1.1365", paramB.a);
        return;
      }
      i2 = i3;
      if (i3 == 0)
      {
        i1 = ((az)localObject2).m();
        if (i1 < 0) {
          i1 = 0;
        } else {
          i1 += 1;
        }
        i2 = i1;
        if (i1 == 0)
        {
          CDadosCarregados.a(e(paramB), 63835, "Trying to set color in selected line, but there is no selected line!", "1.1366", paramB.a);
          return;
        }
      }
      i3 = i2 - 1;
      if ((i3 >= ((az)localObject2).aG.get()) && (i3 >= ((az)localObject2).s()))
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to set color in line ");
        ((StringBuilder)localObject2).append(CUtil.LongToString(i3 + 1));
        ((StringBuilder)localObject2).append(", but this line does not exist!");
        CDadosCarregados.a((ec)localObject1, 63834, ((StringBuilder)localObject2).toString(), "1.1367", paramB.a);
        return;
      }
      i4 -= 1;
      if (i4 >= ((az)localObject2).aC)
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to set color in column ");
        ((StringBuilder)localObject2).append(CUtil.LongToString(i3 + 1));
        ((StringBuilder)localObject2).append(", but this column does not exist!");
        CDadosCarregados.a((ec)localObject1, 63833, ((StringBuilder)localObject2).toString(), "1.1369", paramB.a);
        return;
      }
      boolean bool1 = str1.equals("");
      if (!str1.equals("")) {}
      for (i1 = CUtil.k(str1);; i1 = Color.rgb(0, 0, 0)) {
        break;
      }
      boolean bool2 = str2.equals("");
      if (!str2.equals("")) {}
      for (i2 = CUtil.k(str2);; i2 = Color.rgb(0, 0, 0)) {
        break;
      }
      ((az)localObject2).a(i3, i4, bool1 ^ true, i1, bool2 ^ true, i2, true);
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void v(b paramB)
  {
    Object localObject1 = new CMyToken(a(paramB), b);
    int i2 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject1).GetNextToken(), ")"));
    int i3 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject1).GetNextToken(), c(paramB)));
    int i4 = CUtil.StringToInt(CalculaExpressaoNum(CUtil.a("COL_IND(", ((CMyToken)localObject1).GetNextToken(), ")"), c(paramB)));
    String str1 = ((CMyToken)localObject1).GetNextToken();
    String str2 = ((CMyToken)localObject1).GetNextToken();
    int i1 = 0;
    while (i1 < this.f.t)
    {
      if (this.f.s[i1].f == i2)
      {
        localObject1 = this.f.s[i1];
        break label146;
      }
      i1 += 1;
    }
    localObject1 = null;
    label146:
    Object localObject2 = localObject1;
    Object localObject3;
    if (localObject1 == null)
    {
      localObject3 = this.f.b(i2);
      localObject2 = localObject1;
      if (localObject3 != null)
      {
        localObject2 = localObject1;
        if ((localObject3 instanceof az)) {
          localObject2 = (az)localObject3;
        }
      }
    }
    if (localObject2 != null)
    {
      if (!((az)localObject2).aq)
      {
        CDadosCarregados.a(e(paramB), 63830, "Trying to get color in table control, but the advanced style is not active!", "1.1370", paramB.a);
        return;
      }
      i2 = i3;
      if (i3 == 0)
      {
        i1 = ((az)localObject2).m();
        if (i1 < 0) {
          i1 = 0;
        } else {
          i1 += 1;
        }
        i2 = i1;
        if (i1 == 0)
        {
          CDadosCarregados.a(e(paramB), 63835, "Trying to get color in selected line, but there is no selected line!", "1.1371", paramB.a);
          return;
        }
      }
      i1 = i2 - 1;
      if ((i1 >= ((az)localObject2).aG.get()) && (i1 >= ((az)localObject2).s()))
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to get color in line ");
        ((StringBuilder)localObject2).append(CUtil.LongToString(i1 + 1));
        ((StringBuilder)localObject2).append(", but this line does not exist!");
        CDadosCarregados.a((ec)localObject1, 63834, ((StringBuilder)localObject2).toString(), "1.1373", paramB.a);
        return;
      }
      i2 = i4 - 1;
      if (i2 >= ((az)localObject2).aC)
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Trying to get color in column ");
        ((StringBuilder)localObject2).append(CUtil.LongToString(i1 + 1));
        ((StringBuilder)localObject2).append(", but this column does not exist!");
        CDadosCarregados.a((ec)localObject1, 63833, ((StringBuilder)localObject2).toString(), "1.1376", paramB.a);
        return;
      }
      localObject1 = new cj(0);
      localObject3 = new cj(0);
      if (!((az)localObject2).a(i1, i2, (cj)localObject1, (cj)localObject3))
      {
        CDadosCarregados.a(e(paramB), -1, "Error retrieving color!", "1.1377", paramB.a);
        return;
      }
      if (!str1.equals("")) {
        SetTarget(str1, CUtil.b(((cj)localObject1).a), true, true, c(paramB));
      }
      if (!str2.equals("")) {
        SetTarget(str2, CUtil.b(((cj)localObject3).a), true, true, c(paramB));
      }
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void w(final b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i3 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject).GetNextToken(), ")"));
    final int i2 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    localObject = ((CMyToken)localObject).GetNextToken();
    int i1 = i2;
    if (i2 < 0) {
      i1 = 0;
    }
    i2 = 0;
    while (i2 < this.f.B)
    {
      if (this.f.A[i2].f == i3)
      {
        if (i1 == 0) {
          i1 = this.f.A[i2].l();
        } else {
          i1 -= 1;
        }
        localObject = new CMyToken((String)localObject, c);
        final cj localCj = new cj(i1);
        CUtil.a(true, this.bh, new de()
        {
          public void a()
          {
            Object localObject = CMyFormDlg.this.f.A[i2].a(i2, localCj);
            if (!((String)localObject).equals(""))
            {
              CUtil.a((String)localObject, CMyFormDlg.this);
              return;
            }
            while (this.c.HasTokens())
            {
              localObject = new CMyToken(this.c.GetNextToken(), CMyFormDlg.d);
              int i = CUtil.StringToInt(CUtil.a("COL_IND(", ((CMyToken)localObject).GetNextToken(), ")"));
              localObject = CMyFormDlg.this.CalculaExpressaoStr(((CMyToken)localObject).GetNextToken(), CMyFormDlg.c(paramB));
              CMyFormDlg.this.f.A[i2].a(i2, i - 1, (String)localObject);
            }
            if (CMyFormDlg.this.f.A[i2].aE != null)
            {
              localObject = (bc)CMyFormDlg.this.f.A[i2].aE.getAdapter();
              if (localObject != null) {
                ((bc)localObject).c();
              }
            }
          }
        });
        return;
      }
      i2 += 1;
    }
  }
  
  void x(b paramB)
  {
    Object localObject = new CMyToken(a(paramB), b);
    int i3 = CUtil.StringToInt(CUtil.a("CTRL(", ((CMyToken)localObject).GetNextToken(), ")"));
    int i1 = CUtil.StringToInt(CalculaExpressaoNum(((CMyToken)localObject).GetNextToken(), c(paramB)));
    int i2 = 0;
    while (i2 < this.f.B)
    {
      if (this.f.A[i2].f == i3)
      {
        if (i1 == -1)
        {
          this.f.A[i2].o(-1);
          break;
        }
        i3 = i1;
        if (i1 == 0)
        {
          i1 = this.f.A[i2].n();
          if (i1 < 0) {
            i1 = 0;
          } else {
            i1 += 1;
          }
          i3 = i1;
          if (i1 == 0)
          {
            CDadosCarregados.a(e(paramB), 63835, "Trying to delete selected line, but there is no selected line!", "1.778", paramB.a);
            return;
          }
        }
        i1 = i3 - 1;
        if (i1 >= this.f.A[i2].l())
        {
          localObject = e(paramB);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Trying to delete line ");
          localStringBuilder.append(CUtil.LongToString(i1 + 1));
          localStringBuilder.append(", but this line does not exist!");
          CDadosCarregados.a((ec)localObject, 63834, localStringBuilder.toString(), "1.779", paramB.a);
          return;
        }
        this.f.A[i2].o(i1);
        break;
      }
      i2 += 1;
    }
    CDadosCarregados.a(e(paramB));
  }
  
  void y(final b paramB)
  {
    CMyToken localCMyToken = new CMyToken(a(paramB), b);
    int i1 = CUtil.StringToInt(CUtil.a("CTRL(", localCMyToken.GetNextToken(), ")"));
    final int i2 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    final int i3 = CUtil.StringToInt(CalculaExpressaoNum(localCMyToken.GetNextToken(), c(paramB)));
    paramB = this.f.a(i1, 2);
    if (paramB != null)
    {
      paramB = (au)paramB;
      if (paramB != null) {
        CUtil.a(true, this.bh, new de()
        {
          public void a()
          {
            boolean bool = paramB.aD.hasFocus();
            int j = 0;
            if (bool)
            {
              localObject = paramB.aD.getText();
              if (((Editable)localObject).length() > 0) {
                ((Editable)localObject).replace(0, 1, ((Editable)localObject).subSequence(0, 1), 0, 1);
              }
            }
            else
            {
              paramB.aD.requestFocus();
            }
            if ((i2 == 0) && (i3 == -1))
            {
              paramB.aD.selectAll();
              return;
            }
            if (i2 == -1)
            {
              paramB.aD.setSelection(0);
              return;
            }
            Object localObject = paramB.e();
            int m = i2;
            int k = i3;
            int i;
            if (m < 0)
            {
              i = 0;
            }
            else
            {
              i = m;
              if (m > ((String)localObject).length()) {
                i = ((String)localObject).length();
              }
            }
            if (k >= 0) {
              if (k > ((String)localObject).length()) {
                j = ((String)localObject).length();
              } else {
                j = k;
              }
            }
            k = j;
            if (j < i) {
              k = i;
            }
            paramB.aD.setSelection(i, k);
          }
        });
      }
    }
  }
  
  void z(b paramB)
  {
    String str = CUtil.a("TBL(", a(paramB), ")");
    Object localObject3 = new cj(0);
    r localR = new r(CDadosCarregados.ay);
    Object localObject2 = new ah();
    dw localDw2 = new dw("");
    dw localDw3 = new dw("");
    dw localDw1 = new dw("");
    dw localDw4 = new dw("");
    Object localObject1 = new cj(0);
    if (!((ah)localObject2).a(d(paramB), "", str, true))
    {
      CDadosCarregados.a(e(paramB), ((ah)localObject2).y, ((ah)localObject2).z, "1.84", paramB.a);
      localR.a();
      return;
    }
    if (!a(((ah)localObject2).d, str, ((ah)localObject2).w, ((ah)localObject2).n, ((ah)localObject2).a, localDw3, localDw2, localDw4, localDw1, (cj)localObject3, (cj)localObject1, paramB))
    {
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    if (((cj)localObject3).a == 0)
    {
      localObject1 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("There are no fields to insert on the table ");
      ((StringBuilder)localObject3).append(str);
      CDadosCarregados.a((ec)localObject1, 65233, ((StringBuilder)localObject3).toString(), "1.102", paramB.a);
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    localObject3 = ah.a(str);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Select Count(*) From ");
    localStringBuilder.append((String)localObject3);
    localStringBuilder.append("MFTBC, ");
    localStringBuilder.append((String)localObject3);
    localStringBuilder.append("MFTAB Where TABTAB = '");
    localStringBuilder.append(str);
    localStringBuilder.append("' And TBCIDO = TABIDO And TBCKEY = 1");
    if (!localR.a(localStringBuilder.toString()))
    {
      localObject1 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Error reading the fields from the table ");
      ((StringBuilder)localObject3).append(str);
      CDadosCarregados.a((ec)localObject1, 65128, ((StringBuilder)localObject3).toString(), "1.517", paramB.a);
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    localR.d();
    if ((!localR.f()) && (((cj)localObject1).a < CUtil.StringToInt(localR.b(0))))
    {
      localObject1 = e(paramB);
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Primary key for table \"");
      ((StringBuilder)localObject3).append(str);
      ((StringBuilder)localObject3).append("\" not specified!");
      CDadosCarregados.a((ec)localObject1, -7, ((StringBuilder)localObject3).toString(), "1.518", paramB.a);
      localR.a();
      ((ah)localObject2).b();
      return;
    }
    int i1 = 0;
    if (((cj)localObject1).a != 0)
    {
      localObject3 = localObject2;
      if (((ah)localObject3).a)
      {
        if (((ah)localObject3).b)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Select * From ");
          ((StringBuilder)localObject1).append(((ah)localObject3).d);
          ((StringBuilder)localObject1).append("\"");
          ((StringBuilder)localObject1).append(str);
          ((StringBuilder)localObject1).append("\"(nolock) Where ");
          ((StringBuilder)localObject1).append(localDw1.a);
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Select * From ");
          ((StringBuilder)localObject1).append(((ah)localObject3).d);
          ((StringBuilder)localObject1).append("\"");
          ((StringBuilder)localObject1).append(str);
          ((StringBuilder)localObject1).append("\" Where ");
          ((StringBuilder)localObject1).append(localDw1.a);
          localObject1 = ((StringBuilder)localObject1).toString();
        }
      }
      else
      {
        localObject1 = localDw1;
        if (((ah)localObject3).b)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Select * From ");
          localStringBuilder.append(((ah)localObject3).d);
          localStringBuilder.append(str);
          localStringBuilder.append("(nolock) Where ");
          localStringBuilder.append(((dw)localObject1).a);
          localObject1 = localStringBuilder.toString();
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Select * From ");
          localStringBuilder.append(((ah)localObject3).d);
          localStringBuilder.append(str);
          localStringBuilder.append(" Where ");
          localStringBuilder.append(((dw)localObject1).a);
          localObject1 = localStringBuilder.toString();
        }
      }
      if (!((ah)localObject3).b((String)localObject1))
      {
        localObject1 = e(paramB);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Error reading the records from the table ");
        ((StringBuilder)localObject2).append(str);
        ((StringBuilder)localObject2).append("\n");
        ((StringBuilder)localObject2).append(((ah)localObject3).z);
        CDadosCarregados.a((ec)localObject1, 65232, ((StringBuilder)localObject2).toString(), "1.103", paramB.a);
        localR.a();
        ((ah)localObject3).b();
        return;
      }
      ((ah)localObject3).f();
      if (((ah)localObject3).h())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw3.a);
        ((StringBuilder)localObject1).append(")");
        localDw3.a = ((StringBuilder)localObject1).toString();
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw2.a);
        ((StringBuilder)localObject1).append(")");
        localDw2.a = ((StringBuilder)localObject1).toString();
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw3.a);
        ((StringBuilder)localObject1).append(localDw2.a);
        if (!((ah)localObject3).b(((StringBuilder)localObject1).toString()))
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Error inserting a record on the table ");
          ((StringBuilder)localObject2).append(str);
          ((StringBuilder)localObject2).append("\n");
          ((StringBuilder)localObject2).append(((ah)localObject3).z);
          CDadosCarregados.a((ec)localObject1, 65231, ((StringBuilder)localObject2).toString(), "1.104", paramB.a);
          localR.a();
          ((ah)localObject3).b();
        }
      }
      else
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append(localDw4.a);
        ((StringBuilder)localObject1).append(" Where ");
        ((StringBuilder)localObject1).append(localDw1.a);
        if (!((ah)localObject3).b(((StringBuilder)localObject1).toString()))
        {
          localObject1 = e(paramB);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Error updating a record from the table ");
          ((StringBuilder)localObject2).append(str);
          ((StringBuilder)localObject2).append("\n");
          ((StringBuilder)localObject2).append(((ah)localObject3).z);
          CDadosCarregados.a((ec)localObject1, 65227, ((StringBuilder)localObject2).toString(), "1.105", paramB.a);
          localR.a();
          ((ah)localObject3).b();
        }
      }
    }
    else
    {
      localObject1 = localObject2;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(localDw3.a);
      ((StringBuilder)localObject3).append(")");
      localDw3.a = ((StringBuilder)localObject3).toString();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(localDw2.a);
      ((StringBuilder)localObject3).append(")");
      localDw2.a = ((StringBuilder)localObject3).toString();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append(localDw3.a);
      ((StringBuilder)localObject3).append(localDw2.a);
      if (!((ah)localObject1).b(((StringBuilder)localObject3).toString()))
      {
        localObject2 = e(paramB);
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Error inserting a record on the table ");
        ((StringBuilder)localObject3).append(str);
        ((StringBuilder)localObject3).append("\n");
        ((StringBuilder)localObject3).append(((ah)localObject1).z);
        CDadosCarregados.a((ec)localObject2, 65231, ((StringBuilder)localObject3).toString(), "1.106", paramB.a);
        localR.a();
        ((ah)localObject1).b();
        return;
      }
    }
    ((ah)localObject2).b();
    while (i1 < this.ba)
    {
      if (this.bb[i1].a.equalsIgnoreCase(str))
      {
        int i2 = this.bb[i1].F.c;
        if (!a(i1, paramB))
        {
          localR.a();
          return;
        }
        if (CUtil.a(this.bb[i1].F, i2)) {
          break;
        }
        CUtil.a(this.bb[i1].F);
        break;
      }
      i1 += 1;
    }
    CDadosCarregados.a(e(paramB));
    localR.a();
  }
}
