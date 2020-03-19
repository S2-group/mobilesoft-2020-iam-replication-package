package com.phototools.touchretouch.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.b.a;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.a.a.i;
import com.a.a.j;
import com.advasoft.touchretouch.TouchRetouchLib;
import com.facebook.ads.AdIconView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.e;
import com.facebook.ads.s;
import com.facebook.ads.u;
import com.google.android.gms.ads.h;
import com.phototools.touchretouch.a.b;
import com.phototools.touchretouch.a.d;
import com.phototools.touchretouch.b.c.b;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

public class TouchRetouchActivity
  extends Activity
  implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener, com.phototools.touchretouch.a.c
{
  private static String N = "MyTouchView";
  private static boolean O;
  private static int P;
  private static int Q;
  private static long R = 0L;
  private static long S;
  private static int T;
  public static final String b = com.phototools.touchretouch.a.a.a;
  static int c;
  static int d;
  public static IntBuffer e;
  public static float f;
  public static BitmapFactory.Options g;
  public static int h;
  public static String i;
  public static int j;
  public static int k;
  public static int l;
  public static int m;
  public static int n = 0;
  com.phototools.touchretouch.b.c A;
  com.phototools.touchretouch.b.c B;
  RelativeLayout C;
  RelativeLayout D;
  int E;
  Intent F;
  RelativeLayout G;
  ImageView H;
  String I;
  String J;
  RelativeLayout K;
  int L = 0;
  int M = 0;
  private Runnable U = new q(this);
  private TranslateAnimation V;
  private Queue<a> W;
  private Queue<Integer> X;
  private Queue<Integer> Y;
  private Queue<String> Z;
  String a = "0";
  private Animation aA;
  private Animation aB;
  private Animation aC;
  private int aD = 0;
  private GridView aE;
  private Button aF;
  private List<ResolveInfo> aG;
  private final int aH = 101;
  private Handler aI = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what != 101) {
        return;
      }
      TouchRetouchActivity.a(TouchRetouchActivity.this).setAdapter(new TouchRetouchActivity.n(TouchRetouchActivity.this));
    }
  };
  private final String aJ = TouchRetouchActivity.class.getSimpleName();
  private s aK;
  private NativeAdLayout aL;
  private LinearLayout aM;
  private ProgressDialog aN;
  private Queue<Bitmap> aa;
  private Queue<o> ab;
  private boolean ac = false;
  private Bitmap ad;
  private BitmapFactory.Options ae;
  private String af;
  private ExifInterface ag;
  private String ah;
  private boolean ai;
  private b aj;
  private boolean ak;
  private boolean al;
  private boolean am = false;
  private int an = 0;
  private int ao;
  private int ap;
  private int aq = -1;
  private boolean ar = false;
  private boolean as;
  private boolean at = false;
  private Boolean au = Boolean.valueOf(false);
  private FloatingActionButton av;
  private FloatingActionButton aw;
  private FloatingActionButton ax;
  private FloatingActionButton ay;
  private Animation az;
  RelativeLayout o;
  GLSurfaceView p;
  boolean q = false;
  protected boolean r = false;
  protected boolean s = false;
  public Timer t;
  TextView u;
  TextView v;
  TextView w;
  h x;
  Animation y;
  SharedPreferences.Editor z;
  
  static
  {
    O = false;
    Q = 0;
    f = 0.0F;
  }
  
  public TouchRetouchActivity() {}
  
  private int A()
  {
    return findViewById(2131361891).getHeight() + findViewById(2131361858).getHeight();
  }
  
  private int B()
  {
    Log.e("event ", "done");
    return ((SeekBar)findViewById(2131362117)).getProgress();
  }
  
  private CharSequence[] C()
  {
    int i5;
    if (d > c) {
      i5 = d;
    } else {
      i5 = c;
    }
    int i1 = a(h);
    int i3 = 2;
    i1 %= 2;
    int i4 = 1;
    int i6;
    if (i1 == 1) {
      i6 = c;
    } else {
      i6 = d;
    }
    int i7;
    if (i1 == 1) {
      i7 = d;
    } else {
      i7 = c;
    }
    Object localObject = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bigSide = ");
    localStringBuilder.append(i5);
    d.b((String)localObject, localStringBuilder.toString());
    localObject = b;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("m_cur_image_width = ");
    localStringBuilder.append(d);
    localStringBuilder.append(" m_cur_image_height = ");
    localStringBuilder.append(c);
    d.b((String)localObject, localStringBuilder.toString());
    if (i5 >= 640) {
      i2 = 1;
    } else {
      i2 = 0;
    }
    i1 = i2;
    if (i5 >= 1280) {
      i1 = i2 + 1;
    }
    int i2 = i1;
    if (i5 >= 2560) {
      i2 = i1 + 1;
    }
    i1 = i2;
    if (i5 >= 5120) {
      i1 = i2 + 1;
    }
    i2 = i1;
    if (i1 == 0) {
      i2 = i1 + 1;
    }
    localObject = b;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("number of proposals = ");
    localStringBuilder.append(i2);
    d.b((String)localObject, localStringBuilder.toString());
    localObject = new CharSequence[i2];
    if (this.q)
    {
      i3 = 1;
      i4 = 0;
    }
    else
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(getString(2131820664));
      localStringBuilder.append(" (");
      localStringBuilder.append(i6);
      localStringBuilder.append("x");
      localStringBuilder.append(i7);
      localStringBuilder.append(")");
      localObject[0] = localStringBuilder.toString();
    }
    i1 = i3;
    i2 = i4;
    if (a(i5, i3) >= 640)
    {
      if (this.q)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(getString(2131820661));
        localStringBuilder.append(" (");
        localStringBuilder.append(a(i6, i3));
        localStringBuilder.append("x");
        localStringBuilder.append(a(i7, i3));
        localStringBuilder.append(")");
        localObject[i4] = localStringBuilder.toString();
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(f(a(i5, i3)));
        localStringBuilder.append(" (");
        localStringBuilder.append(a(i6, i3));
        localStringBuilder.append("x");
        localStringBuilder.append(a(i7, i3));
        localStringBuilder.append(")");
        localObject[i4] = localStringBuilder.toString();
      }
      i2 = i4 + 1;
      i1 = i3 * 2;
    }
    i4 = i1;
    i3 = i2;
    if (a(i5, i1) >= 640)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(f(a(i5, i1)));
      localStringBuilder.append(" (");
      localStringBuilder.append(a(i6, i1));
      localStringBuilder.append("x");
      localStringBuilder.append(a(i7, i1));
      localStringBuilder.append(")");
      localObject[i2] = localStringBuilder.toString();
      i3 = i2 + 1;
      i4 = i1 * 2;
    }
    if (a(i5, i4) >= 640)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(f(a(i5, i4)));
      localStringBuilder.append(" (");
      localStringBuilder.append(a(i6, i4));
      localStringBuilder.append("x");
      localStringBuilder.append(a(i7, i4));
      localStringBuilder.append(")");
      localObject[i3] = localStringBuilder.toString();
    }
    return localObject;
  }
  
  private void D()
  {
    showDialog(606);
  }
  
  private void E()
  {
    findViewById(2131361796).setVisibility(8);
    findViewById(2131361805).setVisibility(8);
    findViewById(2131361799).setVisibility(8);
    findViewById(2131361798).setVisibility(8);
  }
  
  private Dialog F()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131820687);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099804));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2130903040, new DialogInterface.OnClickListener()
    {
      final TouchRetouchActivity a = null;
      
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt != 0) {
          return;
        }
        this.a.j();
      }
    }).create();
  }
  
  private Dialog G()
  {
    d.b(b, "InitChoseResolutionDialog");
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131820688));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099804));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(C(), new DialogInterface.OnClickListener()
    {
      final TouchRetouchActivity a = null;
      
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 3: 
          Log.e("start Long", "Ops99");
          TouchRetouchActivity.c(this.a);
          paramAnonymousDialogInterface = TouchRetouchActivity.g;
          paramAnonymousInt = paramAnonymousDialogInterface.inSampleSize * 8;
          paramAnonymousDialogInterface.inSampleSize = paramAnonymousInt;
          TouchRetouchActivity.j(this.a).recycle();
          TouchRetouchActivity.a(this.a, TouchRetouchActivity.a(this.a, TouchRetouchActivity.i, TouchRetouchActivity.g));
        }
        for (;;)
        {
          this.a.a(TouchRetouchActivity.j(this.a));
          return;
          Log.e("start Long", "Ops99");
          TouchRetouchActivity.c(this.a);
          paramAnonymousDialogInterface = TouchRetouchActivity.g;
          paramAnonymousInt = paramAnonymousDialogInterface.inSampleSize * 4;
          break;
          Log.e("start Long", "Ops99");
          TouchRetouchActivity.c(this.a);
          paramAnonymousDialogInterface = TouchRetouchActivity.g;
          paramAnonymousInt = paramAnonymousDialogInterface.inSampleSize * 2;
          break;
          Log.e("start Long", "Ops99");
          TouchRetouchActivity.c(this.a);
        }
      }
    }).create();
  }
  
  private void H()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
    TouchRetouchLib.SetRetouchAnimation(((SharedPreferences)localObject).getBoolean("pref_check_show_animation", true));
    localObject = ((SharedPreferences)localObject).getString("pref_list_show_hint", "1");
    String str;
    if (((String)localObject).equals("1"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(0);
      localObject = b;
      str = "Auto set";
    }
    for (;;)
    {
      d.b((String)localObject, str);
      return;
      if (((String)localObject).equals("2"))
      {
        TouchRetouchLib.SetFingerMoveHintMode(1);
        localObject = b;
        str = "Top Left";
      }
      else if (((String)localObject).equals("3"))
      {
        TouchRetouchLib.SetFingerMoveHintMode(2);
        localObject = b;
        str = "Top Right";
      }
      else if (((String)localObject).equals("4"))
      {
        TouchRetouchLib.SetFingerMoveHintMode(3);
        localObject = b;
        str = "Off is set";
      }
      else
      {
        localObject = b;
        str = "The value is not allowed!!!!!!!!!!";
      }
    }
  }
  
  private void I()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    g(302);
    g(301);
    g(300);
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
    localEditor.apply();
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i1);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    this.aq = 2131361795;
    TouchRetouchLib.SetEditTool(1, B());
    Y();
  }
  
  private void J()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i1);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    g(300);
    g(301);
    g(302);
    if ((h(300)) && (Q == 1))
    {
      g(300);
    }
    else
    {
      o(300);
      Q = 1;
    }
    g(301);
    g(302);
  }
  
  private void K()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    g(301);
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i1);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    g(300);
    g(302);
    if ((h(301)) && (Q == 1))
    {
      g(301);
      return;
    }
    o(301);
    Q = 1;
  }
  
  private void L()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    findViewById(2131361793).setVisibility(8);
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i1);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i1);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    g(300);
    g(301);
    findViewById(2131361796).setVisibility(8);
    if ((h(302)) && (Q == 3))
    {
      g(302);
      E();
    }
    else
    {
      Z();
      o(302);
      Q = 3;
    }
    this.aq = 2131361797;
    TouchRetouchLib.SetEditTool(5, B());
    Y();
  }
  
  private void M()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i1);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    g(300);
    g(301);
    TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131362117)).getProgress());
  }
  
  private void N()
  {
    Log.e("start Long", "Ops88");
    ah();
    a(new a(this, 4));
  }
  
  private void O()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_ERASER");
    localEditor.apply();
    g(302);
    g(301);
    g(300);
    findViewById(2131361793).setVisibility(8);
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i1);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    this.aq = 2131361806;
    TouchRetouchLib.SetEditTool(2, B());
    Y();
  }
  
  private void P()
  {
    a(new a(this, 3));
    ah();
    g(302);
    g(301);
    g(300);
  }
  
  private void Q()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_LASSO");
    localEditor.apply();
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i1);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    g(302);
    g(301);
    g(300);
    this.aq = 2131361812;
    TouchRetouchLib.SetEditTool(0, B());
  }
  
  private void R()
  {
    g(302);
    g(301);
    g(300);
    j();
  }
  
  private boolean S()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    findViewById(2131361811).setVisibility(8);
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131362007), i1);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i2);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362009), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    this.aq = 2131361815;
    TouchRetouchLib.SetEditTool(1, B());
    Y();
    return true;
  }
  
  private void T()
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    g(302);
    g(301);
    g(300);
    a(new a(this, 1));
    ah();
  }
  
  private void U()
  {
    g(302);
    g(301);
    g(300);
    a(new a(this, 2));
    ah();
  }
  
  private void V()
  {
    d = g.outWidth;
    c = g.outHeight;
    String str = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bm width = ");
    localStringBuilder.append(g.outWidth);
    localStringBuilder.append(", bm height = ");
    localStringBuilder.append(g.outHeight);
    localStringBuilder.append(" m_opt.outMimeType = ");
    localStringBuilder.append(g.outMimeType);
    d.b(str, localStringBuilder.toString());
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    str = b;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("alwaysMaxRes = ");
    localStringBuilder.append(bool);
    d.b(str, localStringBuilder.toString());
    if (bool)
    {
      j = 1;
      b(i);
      return;
    }
    this.as = false;
    c(0);
    O = true;
  }
  
  private void W()
  {
    TouchRetouchLib.setUndoPath(d.a().getAbsolutePath());
  }
  
  private void X()
  {
    findViewById(2131361796).setVisibility(0);
  }
  
  private void Y()
  {
    View localView = findViewById(2131361793);
    try
    {
      if (localView.getVisibility() == 4)
      {
        localView.setVisibility(0);
        new Handler().postDelayed(new j(this), 300L);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void Z()
  {
    findViewById(2131361805).setVisibility(0);
    findViewById(2131361799).setVisibility(0);
    findViewById(2131361798).setVisibility(0);
  }
  
  public static int a(int paramInt)
  {
    return paramInt / 90;
  }
  
  public static int a(int paramInt1, int paramInt2)
  {
    return (paramInt2 - 1 + paramInt1) / paramInt2;
  }
  
  private Bitmap a(String paramString, BitmapFactory.Options paramOptions)
  {
    Object localObject = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("trying to decode bitmap file ");
    localStringBuilder.append(paramOptions.inSampleSize);
    d.b((String)localObject, localStringBuilder.toString());
    try
    {
      localObject = BitmapFactory.decodeFile(paramString, paramOptions);
      return localObject;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;) {}
    }
    d.b(b, "Out of memory error :(");
    paramOptions.inSampleSize *= 2;
    this.q = true;
    localObject = b;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Set it twice less with opt.inSampleSize ");
    localStringBuilder.append(paramOptions.inSampleSize);
    d.b((String)localObject, localStringBuilder.toString());
    return a(paramString, paramOptions);
  }
  
  protected static File a()
  {
    File localFile = new File(d.b(), "share_t.jpg");
    String str = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Saving to folder ");
    localStringBuilder.append(localFile.getAbsolutePath());
    d.b(str, localStringBuilder.toString());
    int i1 = a(h);
    str = localFile.getAbsolutePath();
    int i2 = i1 % 2;
    if (i2 == 1) {
      i1 = c;
    } else {
      i1 = d;
    }
    if (i2 == 1) {
      i2 = d;
    } else {
      i2 = c;
    }
    TouchRetouchLib.SaveImage(str, i1, i2);
    return localFile;
  }
  
  private void a(int paramInt, boolean paramBoolean)
  {
    ImageView localImageView = (ImageView)findViewById(paramInt);
    localImageView.setOnClickListener(this);
    localImageView.setTag(new Boolean(false));
    if (!paramBoolean) {
      localImageView.setVisibility(8);
    }
  }
  
  private void a(ResolveInfo paramResolveInfo)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    Log.e("Intent Filepath =", this.I);
    localIntent.putExtra("android.intent.extra.STREAM", Uri.parse(this.I));
    if (paramResolveInfo != null) {
      localIntent.setComponent(new ComponentName(paramResolveInfo.activityInfo.packageName, paramResolveInfo.activityInfo.name));
    }
    localIntent.setType("image/*");
    this.K.setVisibility(4);
    startActivity(localIntent);
  }
  
  private void a(ImageView paramImageView, int paramInt)
  {
    paramImageView.setBackgroundResource(paramInt);
    b(paramImageView, getResources().getColor(2131099696));
  }
  
  private void a(s paramS)
  {
    paramS.z();
    this.aL = ((NativeAdLayout)findViewById(2131362055));
    Object localObject1 = LayoutInflater.from(this);
    Object localObject2 = this.aL;
    int i1 = 0;
    this.aM = ((LinearLayout)((LayoutInflater)localObject1).inflate(2131558459, (ViewGroup)localObject2, false));
    this.aL.addView(this.aM);
    localObject1 = (LinearLayout)findViewById(2131361855);
    localObject2 = new e(this, paramS, this.aL);
    ((LinearLayout)localObject1).removeAllViews();
    ((LinearLayout)localObject1).addView((View)localObject2, 0);
    localObject1 = (AdIconView)this.aM.findViewById(2131362056);
    localObject2 = (TextView)this.aM.findViewById(2131362060);
    MediaView localMediaView = (MediaView)this.aM.findViewById(2131362057);
    Object localObject3 = (TextView)this.aM.findViewById(2131362058);
    TextView localTextView1 = (TextView)this.aM.findViewById(2131362053);
    TextView localTextView2 = (TextView)this.aM.findViewById(2131362059);
    Button localButton = (Button)this.aM.findViewById(2131362054);
    ((TextView)localObject2).setText(paramS.o());
    localTextView1.setText(paramS.q());
    ((TextView)localObject3).setText(paramS.s());
    if (!paramS.k()) {
      i1 = 4;
    }
    localButton.setVisibility(i1);
    localButton.setText(paramS.r());
    localTextView2.setText(paramS.t());
    localObject3 = new ArrayList();
    ((List)localObject3).add(localObject2);
    ((List)localObject3).add(localButton);
    paramS.a(this.aM, localMediaView, (MediaView)localObject1, (List)localObject3);
  }
  
  private void a(String paramString1, String paramString2)
  {
    c(false);
    this.ag = c(paramString1);
    ah();
    d.b(b, "ProcessImagePath");
    i = paramString1;
    if (paramString2 != null)
    {
      d.b(b, "orient not null!!!");
      h = Integer.parseInt(paramString2);
    }
    else
    {
      d.b(b, "orient is null!!!");
      h = 0;
    }
    this.af = new File(paramString1).getName();
    ac();
    if (g == null) {
      g = new BitmapFactory.Options();
    }
    g.inPreferredConfig = Bitmap.Config.ARGB_8888;
    g.inSampleSize = 1;
    this.q = false;
    d.b(b, "init config values");
    g.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString1, g);
    paramString1 = b;
    paramString2 = new StringBuilder();
    paramString2.append("mime type = ");
    paramString2.append(g.outMimeType);
    d.b(paramString1, paramString2.toString());
    if (g.outMimeType == null)
    {
      Toast.makeText(this, getString(2131820700), 1).show();
      setResult(0);
      finish();
      return;
    }
    if (g.outMimeType.equalsIgnoreCase("image/jpeg"))
    {
      d.b(b, "Processing jpeg image!");
      k = 401;
      V();
      return;
    }
    k = 402;
    g.inJustDecodeBounds = false;
    this.ad = a(i, g);
    paramString1 = b;
    paramString2 = new StringBuilder();
    paramString2.append("opt.inSampleSize = ");
    paramString2.append(g.inSampleSize);
    d.b(paramString1, paramString2.toString());
    d = g.outWidth;
    c = g.outHeight;
    paramString1 = b;
    paramString2 = new StringBuilder();
    paramString2.append("m_cur_image_width = ");
    paramString2.append(d);
    paramString2.append(", m_cur_image_height = ");
    paramString2.append(c);
    d.b(paramString1, paramString2.toString());
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    paramString1 = b;
    paramString2 = new StringBuilder();
    paramString2.append("alwaysMaxRes = ");
    paramString2.append(bool);
    d.b(paramString1, paramString2.toString());
    if (bool)
    {
      a(this.ad);
    }
    else
    {
      this.as = false;
      c(0);
      O = true;
    }
    ac();
  }
  
  private void aa()
  {
    try
    {
      Object localObject1 = Environment.getExternalStorageDirectory();
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(((File)localObject1).getAbsolutePath());
      ((StringBuilder)localObject2).append("/");
      ((StringBuilder)localObject2).append(com.phototools.touchretouch.a.a.a);
      new File(((StringBuilder)localObject2).toString()).mkdirs();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(Environment.getExternalStorageDirectory());
      ((StringBuilder)localObject2).append(File.separator);
      ((StringBuilder)localObject2).append(b);
      Object localObject3 = ((StringBuilder)localObject2).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("WipeOut");
      ((StringBuilder)localObject2).append(new SimpleDateFormat("mm_dd_yyyy_hhmmss.SSSSSS").format(new Date()));
      ((StringBuilder)localObject2).append(".png");
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject3 = new File((String)localObject3, (String)localObject2);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("file://");
      localStringBuilder.append(((File)localObject1).getAbsolutePath());
      localStringBuilder.append("/");
      localStringBuilder.append(com.phototools.touchretouch.a.a.a);
      localStringBuilder.append("/");
      localStringBuilder.append((String)localObject2);
      this.I = localStringBuilder.toString();
      Log.e("Created Filepath =", this.I);
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(((File)localObject1).getAbsolutePath());
      localStringBuilder.append("/");
      localStringBuilder.append(com.phototools.touchretouch.a.a.a);
      localStringBuilder.append("/");
      localStringBuilder.append((String)localObject2);
      com.phototools.touchretouch.a.a.g = localStringBuilder.toString();
      try
      {
        localObject1 = new FileOutputStream((File)localObject3);
        ((FileOutputStream)localObject1).flush();
        ((FileOutputStream)localObject1).close();
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
      this.af = ((String)localObject2);
      if (((File)localObject3).exists()) {
        showDialog(505);
      }
      ah();
      a(new a(this, 5));
      return;
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
    }
  }
  
  private Dialog ab()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131820700));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099804));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131820701)).setPositiveButton(getString(2131820695), new g(this)).create();
  }
  
  private void ac()
  {
    Object localObject = (ActivityManager)getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)localObject).getMemoryInfo(localMemoryInfo);
    localObject = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" memoryInfo.availMem ");
    localStringBuilder.append(localMemoryInfo.availMem);
    localStringBuilder.append("\n");
    d.b((String)localObject, localStringBuilder.toString());
  }
  
  private Dialog ad()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131820706);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099804));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131820709)).setPositiveButton(getString(2131820696), new e(this)).setNegativeButton(getString(2131820694), new d(this)).create();
  }
  
  private Dialog ae()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131820706);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099804));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131820709)).setPositiveButton(getString(2131820696), new c(this)).setNegativeButton(getString(2131820694), new b(this)).create();
  }
  
  private Dialog af()
  {
    int i1 = a(h);
    TextView localTextView = new TextView(this);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getString(2131820689));
    localStringBuilder.append(" (");
    int i2 = i1 % 2;
    if (i2 == 1) {
      i1 = c;
    } else {
      i1 = d;
    }
    localStringBuilder.append(i1);
    localStringBuilder.append("x");
    if (i2 == 1) {
      i1 = d;
    } else {
      i1 = c;
    }
    localStringBuilder.append(i1);
    localStringBuilder.append(")");
    localTextView.setText(localStringBuilder.toString());
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099804));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2130903041, new f(this)).create();
  }
  
  private void ag()
  {
    aa();
    l();
  }
  
  private void ah()
  {
    try
    {
      Log.e("start Long", "Ops");
      System.gc();
      y();
      findViewById(2131362085).setVisibility(0);
      findViewById(2131362084).setVisibility(0);
      this.ak = true;
      this.al = true;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void ai()
  {
    runOnUiThread(this.U);
  }
  
  private void aj()
  {
    Object localObject1 = getPackageManager();
    List localList = ((PackageManager)localObject1).getInstalledPackages(1);
    int i1 = 0;
    localObject1 = ((PackageManager)localObject1).getInstalledApplications(0);
    while (i1 < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i1);
      localObject2 = ((ApplicationInfo)((List)localObject1).get(i1)).publicSourceDir;
      if (((String)localObject2).contains(getPackageName()))
      {
        this.ah = ((String)localObject2);
        D();
      }
      i1 += 1;
    }
  }
  
  private List<ResolveInfo> ak()
  {
    new ArrayList();
    Intent localIntent = new Intent("android.intent.action.SEND", null);
    localIntent.putExtra("android.intent.extra.TEXT", "This is my text to send.");
    localIntent.setType("image/*");
    return getPackageManager().queryIntentActivities(localIntent, 0);
  }
  
  private void al()
  {
    findViewById(2131361805).performClick();
    findViewById(2131361811).setVisibility(8);
    findViewById(2131362039).setVisibility(8);
    findViewById(2131362162).setVisibility(0);
    findViewById(2131361797).setVisibility(8);
    findViewById(2131361815).setVisibility(8);
    findViewById(2131361795).setVisibility(8);
    findViewById(2131361812).setVisibility(8);
    findViewById(2131361806).setVisibility(8);
    findViewById(2131361805).setVisibility(0);
    findViewById(2131361799).setVisibility(0);
    findViewById(2131361798).setVisibility(0);
    findViewById(2131361818).setVisibility(0);
    this.y = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    this.y.setDuration(300L);
    findViewById(2131362162).startAnimation(this.y);
    this.z = getSharedPreferences("WIPE_OUT", 0).edit();
    this.z.putString("PANEL_VISIBLE", "CLONE_STAMP");
    this.z.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    this.z.apply();
  }
  
  private void am()
  {
    findViewById(2131362039).setVisibility(0);
    this.o.setVisibility(8);
  }
  
  private void an()
  {
    findViewById(2131361811).setVisibility(0);
    findViewById(2131362039).setVisibility(8);
    findViewById(2131362162).setVisibility(0);
    findViewById(2131361797).setVisibility(8);
    findViewById(2131361795).setVisibility(0);
    findViewById(2131361815).setVisibility(8);
    findViewById(2131361812).setVisibility(0);
    findViewById(2131361806).setVisibility(0);
    findViewById(2131361818).setVisibility(0);
    findViewById(2131361805).setVisibility(8);
    findViewById(2131361799).setVisibility(8);
    findViewById(2131361798).setVisibility(8);
    this.y = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    this.y.setDuration(300L);
    findViewById(2131362162).startAnimation(this.y);
    this.z = getSharedPreferences("WIPE_OUT", 0).edit();
    this.z.putString("PANEL_VISIBLE", "OBJECT_REMOVAL");
    this.z.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
    this.z.apply();
  }
  
  private void ao()
  {
    this.aK = new s(this, "523935284752994_530400107439845");
    this.aK.a(new u()
    {
      public void a(com.facebook.ads.a paramAnonymousA)
      {
        Log.e("Facebook ad", String.valueOf(paramAnonymousA));
        if ((TouchRetouchActivity.z(TouchRetouchActivity.this) != null) && (TouchRetouchActivity.z(TouchRetouchActivity.this) == paramAnonymousA))
        {
          TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.z(TouchRetouchActivity.this));
          return;
        }
        Log.e("Facebook empty", String.valueOf(paramAnonymousA));
      }
      
      public void a(com.facebook.ads.a paramAnonymousA, com.facebook.ads.c paramAnonymousC)
      {
        Log.e("Facebook ad error", String.valueOf(paramAnonymousA));
        Log.e("Facebook error", String.valueOf(paramAnonymousC.a()));
      }
      
      public void b(com.facebook.ads.a paramAnonymousA) {}
      
      public void c(com.facebook.ads.a paramAnonymousA) {}
      
      public void d(com.facebook.ads.a paramAnonymousA) {}
    });
    this.aK.h();
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    a((ImageView)findViewById(paramInt1), d(paramInt2));
  }
  
  private void b(int paramInt, boolean paramBoolean)
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(paramInt);
    localRelativeLayout.setOnClickListener(this);
    localRelativeLayout.setTag(new Boolean(false));
    if (!paramBoolean) {
      localRelativeLayout.setVisibility(8);
    }
  }
  
  private void b(ImageView paramImageView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramImageView.getBackground().setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
      return;
    }
    Drawable localDrawable = android.support.v4.graphics.drawable.a.g(paramImageView.getBackground());
    android.support.v4.graphics.drawable.a.a(localDrawable, paramInt);
    paramImageView.setBackgroundDrawable(android.support.v4.graphics.drawable.a.h(localDrawable));
  }
  
  private static void b(String paramString, EGL10 paramEGL10)
  {
    while (paramEGL10.eglGetError() != 12288) {}
  }
  
  private ExifInterface c(String paramString)
  {
    try
    {
      paramString = new ExifInterface(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    d.b(b, "exif exception");
    return null;
  }
  
  private void c(boolean paramBoolean)
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "MOVE");
    localEditor.apply();
    int i1 = getResources().getColor(2131099696);
    int i2 = getResources().getColor(2131099804);
    b((ImageView)findViewById(2131362004), i2);
    b((ImageView)findViewById(2131361998), i2);
    b((ImageView)findViewById(2131361999), i2);
    b((ImageView)findViewById(2131362006), i1);
    b((ImageView)findViewById(2131362003), i2);
    b((ImageView)findViewById(2131362005), i2);
    b((ImageView)findViewById(2131362011), i2);
    b((ImageView)findViewById(2131362008), i2);
    b((ImageView)findViewById(2131362010), i2);
    b((ImageView)findViewById(2131362000), i2);
    b((ImageView)findViewById(2131362001), i2);
    b((ImageView)findViewById(2131362002), i2);
    ((TextView)findViewById(2131362184)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362187)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362186)).setTextColor(getResources().getColor(2131099804));
    b((ImageView)findViewById(2131362009), i2);
    ((TextView)findViewById(2131362196)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362191)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362183)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362194)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362185)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362193)).setTextColor(getResources().getColor(2131099696));
    ((TextView)findViewById(2131362192)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362198)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362195)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362189)).setTextColor(getResources().getColor(2131099804));
    ((TextView)findViewById(2131362197)).setTextColor(getResources().getColor(2131099804));
    g(301);
    g(300);
    g(302);
    this.aq = 2131361813;
    TouchRetouchLib.SetEditTool(3, B());
  }
  
  private int d(int paramInt)
  {
    switch (paramInt)
    {
    case 2131361796: 
    case 2131361798: 
    case 2131361799: 
    case 2131361805: 
    case 2131361811: 
    case 2131361814: 
    default: 
      return 0;
    case 2131361813: 
      return 2131230931;
    case 2131361812: 
      return 2131230927;
    case 2131361810: 
      return 2131230860;
    case 2131361809: 
      return 2131230859;
    case 2131361808: 
      return 2131230858;
    case 2131361807: 
      return 2131230857;
    case 2131361806: 
      return 2131230903;
    case 2131361804: 
      return 2131230856;
    case 2131361803: 
      return 2131230855;
    case 2131361802: 
      return 2131230854;
    case 2131361801: 
      return 2131230853;
    case 2131361800: 
      return 2131230851;
    case 2131361797: 
      return 2131230864;
    }
    return 2131230850;
  }
  
  private void d(boolean paramBoolean)
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    g(302);
    g(301);
    g(300);
    ag();
  }
  
  private View e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 302: 
      paramInt = 2131361858;
    }
    for (;;)
    {
      return findViewById(paramInt);
      paramInt = 2131361910;
      continue;
      paramInt = 2131361975;
    }
  }
  
  private void e(boolean paramBoolean)
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    TouchRetouchLib.ZoomPicture(paramBoolean);
    i();
    a(TouchRetouchLib.GetZoom());
    h();
  }
  
  private String f(int paramInt)
  {
    if (paramInt >= 5120) {
      paramInt = 2131820661;
    }
    for (;;)
    {
      return getString(paramInt);
      if (paramInt >= 2560)
      {
        paramInt = 2131820660;
      }
      else if (paramInt >= 1280)
      {
        paramInt = 2131820663;
      }
      else
      {
        if (paramInt < 640) {
          break;
        }
        paramInt = 2131820662;
      }
    }
    return null;
  }
  
  private void g(int paramInt)
  {
    View localView = e(paramInt);
    if (localView != null) {
      localView.setVisibility(8);
    }
  }
  
  private boolean h(int paramInt)
  {
    View localView = e(paramInt);
    return (localView != null) && (localView.getVisibility() == 0);
  }
  
  private void i(int paramInt)
  {
    String str = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Flip ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" selected");
    d.b(str, localStringBuilder.toString());
    int i1 = paramInt - 2131361807;
    g(300);
    this.ap = paramInt;
    b(2131362001, paramInt);
    TouchRetouchLib.SetCloneStampType(this.ao - 2131361800, i1);
    str = b;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("flip mode i = ");
    localStringBuilder.append(i1);
    localStringBuilder.append(" selected");
    d.b(str, localStringBuilder.toString());
  }
  
  private void j(int paramInt)
  {
    int i1 = paramInt - 2131361800;
    g(301);
    this.ao = paramInt;
    b(2131362002, paramInt);
    TouchRetouchLib.SetCloneStampType(i1, this.ap - 2131361807);
    String str = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("clone mode i = ");
    localStringBuilder.append(i1);
    localStringBuilder.append(" selected");
    d.b(str, localStringBuilder.toString());
  }
  
  private void k(int paramInt)
  {
    findViewById(paramInt).setEnabled(false);
  }
  
  private void l(int paramInt)
  {
    findViewById(paramInt).setEnabled(true);
  }
  
  private void m(int paramInt)
  {
    findViewById(paramInt).setEnabled(false);
  }
  
  private void n(int paramInt)
  {
    findViewById(paramInt).setEnabled(true);
  }
  
  private void o(int paramInt)
  {
    View localView = e(paramInt);
    if (localView != null) {
      localView.setVisibility(0);
    }
  }
  
  private void y()
  {
    m(2131361814);
    m(2131361819);
    m(2131361816);
    m(2131361812);
    m(2131361795);
    m(2131361815);
    m(2131361813);
    m(2131361806);
    m(2131361811);
    m(2131361797);
    m(2131361817);
    k(2131361796);
    m(2131361805);
    m(2131361799);
    m(2131361798);
    k(2131361800);
    k(2131361801);
    k(2131361802);
    k(2131361803);
    k(2131361804);
    k(2131361807);
    k(2131361808);
    k(2131361809);
    k(2131361810);
  }
  
  private void z()
  {
    n(2131361814);
    n(2131361819);
    n(2131361816);
    n(2131361812);
    n(2131361795);
    n(2131361815);
    n(2131361813);
    n(2131361806);
    n(2131361811);
    n(2131361797);
    n(2131361817);
    l(2131361796);
    n(2131361805);
    n(2131361799);
    n(2131361798);
    n(2131361911);
    n(2131361912);
    n(2131361913);
    n(2131361914);
    n(2131361915);
    n(2131361976);
    n(2131361977);
    n(2131361978);
    n(2131361979);
  }
  
  public void ObjectClone(View paramView)
  {
    findViewById(2131361805).performClick();
    findViewById(2131361811).setVisibility(8);
    findViewById(2131362039).setVisibility(8);
    findViewById(2131362162).setVisibility(0);
    findViewById(2131361797).setVisibility(8);
    findViewById(2131361815).setVisibility(8);
    findViewById(2131361795).setVisibility(8);
    findViewById(2131361812).setVisibility(8);
    findViewById(2131361806).setVisibility(8);
    findViewById(2131361805).setVisibility(0);
    findViewById(2131361799).setVisibility(0);
    findViewById(2131361798).setVisibility(0);
    findViewById(2131361818).setVisibility(0);
    paramView = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131362162).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "CLONE_STAMP");
    paramView.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    paramView.apply();
  }
  
  public void OnBack(View paramView)
  {
    ah();
    a(new a(this, 4));
    g(301);
    g(300);
    g(302);
    findViewById(2131362039).setVisibility(0);
    findViewById(2131362162).setVisibility(8);
    findViewById(2131361797).setVisibility(8);
    findViewById(2131361815).setVisibility(8);
    findViewById(2131361795).setVisibility(8);
    findViewById(2131361812).setVisibility(8);
    findViewById(2131361806).setVisibility(8);
    findViewById(2131361818).setVisibility(8);
    findViewById(2131361811).setVisibility(8);
    findViewById(2131361813).performClick();
    paramView = new TranslateAnimation(1, -1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131362039).startAnimation(paramView);
  }
  
  public void QuickRepair(View paramView)
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    findViewById(2131361815).performClick();
    findViewById(2131361811).setVisibility(8);
    findViewById(2131362039).setVisibility(8);
    findViewById(2131362162).setVisibility(0);
    findViewById(2131361797).setVisibility(8);
    findViewById(2131361815).setVisibility(0);
    findViewById(2131361795).setVisibility(8);
    findViewById(2131361812).setVisibility(8);
    findViewById(2131361806).setVisibility(8);
    findViewById(2131361818).setVisibility(0);
    findViewById(2131361805).setVisibility(8);
    findViewById(2131361799).setVisibility(8);
    findViewById(2131361798).setVisibility(8);
    paramView = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131362162).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "QUICK_REPAIR");
    paramView.putString("OBJECT_SELECT", "OBJECT_QUICK_BRUSH");
    paramView.apply();
  }
  
  public void Settings(View paramView)
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
    if (paramView.equals("OBJECT_REMOVAL_BRUSH"))
    {
      g(301);
      g(300);
      if ((h(302)) && (Q == 1))
      {
        g(302);
        return;
      }
      E();
    }
    do
    {
      o(302);
      Q = 1;
      return;
      if (paramView.equals("OBJECT_QUICK_BRUSH"))
      {
        g(301);
        g(300);
        if ((!h(302)) || (Q != 1)) {
          break;
        }
        g(302);
        return;
      }
      if (!paramView.equals("OBJECT_CLONE_BRUSH")) {
        break label331;
      }
      g(301);
      g(300);
    } while ((!h(302)) || (Q != 1));
    g(302);
    return;
    label331:
    if (paramView.equals("OBJECT_REMOVAL_ERASER"))
    {
      g(301);
      g(300);
      if ((h(302)) && (Q == 2))
      {
        g(302);
      }
      else
      {
        E();
        o(302);
        Q = 2;
      }
      X();
    }
  }
  
  protected void a(float paramFloat)
  {
    ImageView localImageView = (ImageView)findViewById(2131362215);
    Canvas localCanvas = new Canvas();
    d.b(b, "we really call drawZoomValue here!!!");
    d.b(b, "koef = 12.0");
    Bitmap localBitmap = Bitmap.createBitmap(1440, 960, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(-1895825408);
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawRoundRect(new RectF(120.0F, 120.0F, 1320.0F, 648.0F), 240.0F, 240.0F, localPaint);
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(-1);
    float f1 = 336.0F;
    localPaint.setTextSize(336.0F);
    localPaint.setAntiAlias(true);
    localPaint.setTypeface(Typeface.defaultFromStyle(0));
    int i1 = (int)(paramFloat * 100.0F);
    Object localObject;
    if (i1 >= 100)
    {
      Log.e("Current Pos:", String.valueOf(102));
      localObject = new StringBuilder(String.valueOf(i1));
      ((StringBuilder)localObject).append("%");
      localObject = ((StringBuilder)localObject).toString();
    }
    for (paramFloat = f1;; paramFloat = 420.0F)
    {
      localCanvas.drawText((String)localObject, paramFloat, 480.0F, localPaint);
      break;
      Log.e("Current Pos:", String.valueOf(100));
      localObject = new StringBuilder(String.valueOf(i1));
      ((StringBuilder)localObject).append("%");
      localObject = ((StringBuilder)localObject).toString();
    }
    localImageView.setImageBitmap(localBitmap);
  }
  
  public void a(Bitmap paramBitmap)
  {
    try
    {
      this.aa.add(paramBitmap);
      return;
    }
    finally
    {
      paramBitmap = finally;
      throw paramBitmap;
    }
  }
  
  public void a(Uri paramUri)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, TouchRetouchActivity.class);
    localIntent.putExtra("pic", String.valueOf(paramUri));
    localIntent.setData(paramUri);
    startActivity(localIntent);
    finish();
  }
  
  public void a(a paramA)
  {
    try
    {
      this.W.add(paramA);
      return;
    }
    finally
    {
      paramA = finally;
      throw paramA;
    }
  }
  
  public void a(o paramO)
  {
    try
    {
      this.ab.add(paramO);
      return;
    }
    finally
    {
      paramO = finally;
      throw paramO;
    }
  }
  
  public void a(Integer paramInteger)
  {
    try
    {
      this.X.add(paramInteger);
      return;
    }
    finally
    {
      paramInteger = finally;
      throw paramInteger;
    }
  }
  
  public void a(String paramString)
  {
    Toast.makeText(this, getString(2131820700), 1).show();
    m();
  }
  
  public void a(String paramString1, String paramString2, int paramInt)
  {
    a(paramString1, paramString2);
    m();
  }
  
  public void a(boolean paramBoolean)
  {
    View localView1 = findViewById(2131362214);
    View localView2 = findViewById(2131361858);
    Object localObject = findViewById(2131361975);
    View localView3 = findViewById(2131361910);
    ((View)localObject).setVisibility(4);
    localView3.setVisibility(4);
    localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    ((Animation)localObject).setDuration(300L);
    if (this.aq == 2131361797)
    {
      this.ac = true;
      localView2.startAnimation((Animation)localObject);
    }
    if (!this.ai)
    {
      this.V = new TranslateAnimation(1, 0.0F, 1, 1.0F, 1, 0.0F, 1, 0.0F);
      this.V.setDuration(300L);
      localView1.startAnimation(this.V);
      localView1.setVisibility(4);
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    findViewById(2131361819).setEnabled(paramBoolean1);
    findViewById(2131361816).setEnabled(paramBoolean2);
  }
  
  public a b()
  {
    try
    {
      a localA = (a)this.W.poll();
      return localA;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void b(final int paramInt)
  {
    try
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ImageView localImageView = (ImageView)TouchRetouchActivity.this.findViewById(2131361893);
          Canvas localCanvas = new Canvas();
          Bitmap localBitmap = Bitmap.createBitmap(2400, 2400, Bitmap.Config.ARGB_4444);
          localCanvas.setBitmap(localBitmap);
          Paint localPaint = new Paint();
          localPaint.setColor(TouchRetouchActivity.this.getResources().getColor(2131099803));
          localPaint.setStyle(Paint.Style.FILL);
          localCanvas.drawRoundRect(new RectF(2.14748365E9F, 2.14748365E9F, -2.14748122E9F, -2.14748122E9F), 50.0F, 50.0F, localPaint);
          localPaint.setColor(TouchRetouchActivity.this.getResources().getColor(2131099696));
          localPaint.setStyle(Paint.Style.FILL);
          double d = paramInt;
          Double.isNaN(d);
          localCanvas.drawCircle(1200.0F, 1200.0F, (float)(d * 5.6D), localPaint);
          localImageView.setImageBitmap(localBitmap);
        }
      }, 500L);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void b(Integer paramInteger)
  {
    try
    {
      this.Y.add(paramInteger);
      return;
    }
    finally
    {
      paramInteger = finally;
      throw paramInteger;
    }
  }
  
  public void b(String paramString)
  {
    try
    {
      this.Z.add(paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public Integer c()
  {
    try
    {
      Integer localInteger = (Integer)this.X.poll();
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void c(int paramInt)
  {
    BitmapFactory.Options localOptions;
    switch (paramInt)
    {
    default: 
      return;
    case 3: 
      ah();
      if (k == 402)
      {
        localOptions = g;
        localOptions.inSampleSize *= 8;
        this.ad.recycle();
        this.ad = a(i, g);
        a(this.ad);
        return;
      }
      if (k == 401)
      {
        j = 8;
        b(i);
      }
      return;
    case 2: 
      ah();
      if (k == 402)
      {
        localOptions = g;
        localOptions.inSampleSize *= 4;
        this.ad.recycle();
        this.ad = a(i, g);
        a(this.ad);
        return;
      }
      if (k == 401)
      {
        j = 4;
        b(i);
      }
      return;
    case 1: 
      ah();
      if (k == 402)
      {
        localOptions = g;
        localOptions.inSampleSize *= 2;
        this.ad.recycle();
        this.ad = a(i, g);
        a(this.ad);
        return;
      }
      if (k == 401)
      {
        j = 2;
        b(i);
      }
      return;
    }
    ah();
    if (k == 402)
    {
      a(this.ad);
      return;
    }
    if (k == 401)
    {
      j = 1;
      b(i);
    }
  }
  
  public Integer d()
  {
    try
    {
      Integer localInteger = (Integer)this.Y.poll();
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public String e()
  {
    try
    {
      String str = (String)this.Z.poll();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Bitmap f()
  {
    try
    {
      Bitmap localBitmap = (Bitmap)this.aa.poll();
      return localBitmap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public o g()
  {
    try
    {
      o localO = (o)this.ab.poll();
      return localO;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void h()
  {
    this.at = false;
    new Handler().postDelayed(new h(this), 300L);
  }
  
  public void i()
  {
    findViewById(2131361832).setVisibility(0);
    this.at = true;
  }
  
  public void j()
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      localIntent.setType("image/*");
      startActivityForResult(Intent.createChooser(localIntent, getString(2131820687)), 3);
      return;
    }
    catch (Exception localException) {}
  }
  
  public void k()
  {
    d.b(b, "SetLastEditedTool");
    String str = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("lastSelectedTool = ");
    localStringBuilder.append(this.aq);
    d.b(str, localStringBuilder.toString());
    switch (this.aq)
    {
    default: 
      return;
    case 2131361815: 
      try
      {
        TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131362117)).getProgress());
        return;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return;
      }
    case 2131361812: 
      try
      {
        TouchRetouchLib.SetEditTool(0, ((SeekBar)findViewById(2131362117)).getProgress());
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        return;
      }
    case 2131361806: 
      try
      {
        TouchRetouchLib.SetEditTool(2, ((SeekBar)findViewById(2131362117)).getProgress());
        return;
      }
      catch (Exception localException3)
      {
        localException3.printStackTrace();
        return;
      }
    case 2131361797: 
      try
      {
        TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131362117)).getProgress());
        TouchRetouchLib.SetCloneStampType(this.ao, this.ap);
        return;
      }
      catch (Exception localException4)
      {
        localException4.printStackTrace();
        return;
      }
    }
    try
    {
      TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131362117)).getProgress());
      return;
    }
    catch (Exception localException5)
    {
      localException5.printStackTrace();
    }
  }
  
  public void l()
  {
    System.gc();
    this.ak = false;
  }
  
  public void m()
  {
    if (!this.ar)
    {
      if (this.t == null) {
        this.t = new Timer();
      }
      this.t.schedule(new i(this), 0L, 200L);
      this.ar = true;
    }
  }
  
  public void n()
  {
    if ((this.ar) && (this.t != null))
    {
      this.t.cancel();
      this.t.purge();
      this.t = null;
      this.ar = false;
    }
  }
  
  public void o()
  {
    this.K.setVisibility(4);
    Toast.makeText(this, getString(2131820704), 1).show();
    Intent localIntent = new Intent(getApplicationContext(), PreviewActivity.class);
    localIntent.putExtra("pic", this.J);
    localIntent.putExtra("path", this.I);
    startActivity(localIntent);
    finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 != 3)
    {
      if (paramInt1 != 303) {
        return;
      }
      if (paramInt2 != -1) {
        break label84;
      }
      try
      {
        this.aj.a(paramIntent);
        return;
      }
      catch (Exception paramIntent) {}
    }
    try
    {
      paramIntent.printStackTrace();
      if (!this.x.a()) {
        break label83;
      }
      this.x.b();
      return;
    }
    catch (Exception paramIntent)
    {
      paramIntent.printStackTrace();
      return;
    }
    this.E = paramInt2;
    this.F = paramIntent;
    new m().execute(new String[0]);
    return;
    label83:
    return;
    label84:
    if (paramInt2 == 0) {}
  }
  
  public void onBackPressed()
  {
    if (this.C.getVisibility() == 0)
    {
      this.C.setVisibility(4);
      return;
    }
    LayoutInflater.from(this).inflate(2131558436, null);
    new b.a(this, 2131886082).b("Do you want to go back without saving image?").a("Image not Saved !").a(Html.fromHtml("<font color='#27c886'>SAVE</font>"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.r();
      }
    }).b(Html.fromHtml("<font color='#27c886'>BACK</font>"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (TouchRetouchActivity.d(TouchRetouchActivity.this))
        {
          System.gc();
          Log.e("Finishing", "first");
        }
        for (paramAnonymousDialogInterface = new Intent(TouchRetouchActivity.this.getApplicationContext(), ActivityStart.class);; paramAnonymousDialogInterface = new Intent(TouchRetouchActivity.this.getApplicationContext(), ActivityStart.class))
        {
          paramAnonymousDialogInterface.addFlags(67108864);
          TouchRetouchActivity.this.startActivity(paramAnonymousDialogInterface);
          TouchRetouchActivity.this.finish();
          return;
          Log.e("Finishing", "second");
        }
      }
    }).c();
  }
  
  public void onClick(View paramView)
  {
    if (paramView == findViewById(2131361814))
    {
      v();
      if (this.au.booleanValue())
      {
        this.ax.startAnimation(this.aC);
        this.av.startAnimation(this.aA);
        this.aw.startAnimation(this.aA);
        this.ay.startAnimation(this.aA);
        this.av.setVisibility(8);
        this.aw.setVisibility(8);
        this.ay.setVisibility(8);
        this.u.setVisibility(8);
        this.v.setVisibility(8);
        this.w.setVisibility(8);
        this.av.setClickable(false);
        this.aw.setClickable(false);
        this.ay.setClickable(false);
        this.au = Boolean.valueOf(false);
        Log.d("kk", "close");
      }
    }
    else
    {
      if (paramView == findViewById(2131361819))
      {
        if (this.au.booleanValue())
        {
          this.ax.startAnimation(this.aC);
          this.av.startAnimation(this.aA);
          this.aw.startAnimation(this.aA);
          this.ay.startAnimation(this.aA);
          this.av.setVisibility(8);
          this.aw.setVisibility(8);
          this.ay.setVisibility(8);
          this.u.setVisibility(8);
          this.v.setVisibility(8);
          this.w.setVisibility(8);
          this.av.setClickable(false);
          this.aw.setClickable(false);
          this.ay.setClickable(false);
          this.au = Boolean.valueOf(false);
          Log.d("kk", "close");
        }
        U();
        return;
      }
      if (paramView == findViewById(2131361816))
      {
        T();
        return;
      }
      if (paramView == findViewById(2131361812))
      {
        Q();
        return;
      }
      if (paramView == findViewById(2131361795))
      {
        I();
        return;
      }
      if (paramView == findViewById(2131361815))
      {
        S();
        return;
      }
      if (paramView == findViewById(2131361813))
      {
        c(true);
        return;
      }
      if (paramView == findViewById(2131361806))
      {
        O();
        return;
      }
      if (paramView == findViewById(2131361811))
      {
        P();
        if (!this.x.a()) {}
      }
      else
      {
        for (;;)
        {
          this.x.b();
          return;
          if (paramView == findViewById(2131361797))
          {
            L();
            return;
          }
          if (paramView != findViewById(2131361817)) {
            break;
          }
          try
          {
            r();
          }
          catch (Exception paramView)
          {
            paramView.printStackTrace();
          }
          if (!this.x.a()) {
            return;
          }
          this.L = 1;
        }
        if (paramView == findViewById(2131361796))
        {
          N();
          return;
        }
        if (paramView == findViewById(2131361805))
        {
          M();
          return;
        }
        if (paramView == findViewById(2131361799))
        {
          K();
          this.A.b(paramView);
        }
        for (paramView = this.A;; paramView = this.B)
        {
          paramView.c(4);
          return;
          if (paramView != findViewById(2131361798)) {
            break;
          }
          J();
          this.B.b(paramView);
        }
        int i1;
        if (paramView == findViewById(2131361911)) {
          i1 = 2131361800;
        }
        for (;;)
        {
          j(i1);
          return;
          if (paramView == findViewById(2131361912))
          {
            i1 = 2131361801;
          }
          else if (paramView == findViewById(2131361913))
          {
            i1 = 2131361802;
          }
          else if (paramView == findViewById(2131361914))
          {
            i1 = 2131361803;
          }
          else
          {
            if (paramView != findViewById(2131361915)) {
              break;
            }
            i1 = 2131361804;
          }
        }
        if (paramView == findViewById(2131361976)) {
          i1 = 2131361807;
        }
        for (;;)
        {
          i(i1);
          return;
          if (paramView == findViewById(2131361977))
          {
            i1 = 2131361808;
          }
          else if (paramView == findViewById(2131361978))
          {
            i1 = 2131361809;
          }
          else
          {
            if (paramView != findViewById(2131361979)) {
              break;
            }
            i1 = 2131361810;
          }
        }
        if (paramView == findViewById(2131361821))
        {
          e(true);
          return;
        }
        if (paramView == findViewById(2131361820)) {
          e(false);
        }
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    d.b(b, "OnConfig changed!!!");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131558433);
    this.aN = new ProgressDialog(this, 2131886082);
    this.aN.setMessage("Please wait, Image Loading...");
    this.aN.show();
    p();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        d.b(TouchRetouchActivity.b, "onCreate");
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        System.gc();
        TouchRetouchActivity.this.J = TouchRetouchActivity.this.getIntent().getDataString();
        TouchRetouchActivity.this.H = ((ImageView)TouchRetouchActivity.this.findViewById(2131361884));
        TouchRetouchActivity.this.G = ((RelativeLayout)TouchRetouchActivity.this.findViewById(2131361968));
        TouchRetouchActivity.a(TouchRetouchActivity.this, AnimationUtils.loadAnimation(TouchRetouchActivity.this.getApplicationContext(), 2130771986));
        TouchRetouchActivity.b(TouchRetouchActivity.this, AnimationUtils.loadAnimation(TouchRetouchActivity.this.getApplicationContext(), 2130771985));
        TouchRetouchActivity.c(TouchRetouchActivity.this, AnimationUtils.loadAnimation(TouchRetouchActivity.this.getApplicationContext(), 2130772009));
        TouchRetouchActivity.d(TouchRetouchActivity.this, AnimationUtils.loadAnimation(TouchRetouchActivity.this.getApplicationContext(), 2130772008));
        Object localObject1 = (RelativeLayout)TouchRetouchActivity.this.findViewById(2131361794);
        TouchRetouchActivity.this.C = ((RelativeLayout)TouchRetouchActivity.this.findViewById(2131362120));
        TouchRetouchActivity.this.D = ((RelativeLayout)TouchRetouchActivity.this.findViewById(2131362096));
        TouchRetouchActivity.this.K = ((RelativeLayout)TouchRetouchActivity.this.findViewById(2131362085));
        TouchRetouchActivity.a(TouchRetouchActivity.this, (FloatingActionButton)TouchRetouchActivity.this.findViewById(2131361966));
        TouchRetouchActivity.b(TouchRetouchActivity.this, (FloatingActionButton)TouchRetouchActivity.this.findViewById(2131361964));
        TouchRetouchActivity.c(TouchRetouchActivity.this, (FloatingActionButton)TouchRetouchActivity.this.findViewById(2131361967));
        TouchRetouchActivity.d(TouchRetouchActivity.this, (FloatingActionButton)TouchRetouchActivity.this.findViewById(2131361963));
        TouchRetouchActivity.this.u = ((TextView)TouchRetouchActivity.this.findViewById(2131362188));
        TouchRetouchActivity.this.v = ((TextView)TouchRetouchActivity.this.findViewById(2131362199));
        TouchRetouchActivity.this.w = ((TextView)TouchRetouchActivity.this.findViewById(2131362190));
        try
        {
          Log.e("Pic", TouchRetouchActivity.this.J);
          if (Uri.parse(TouchRetouchActivity.this.J) != null)
          {
            com.a.a.c.a(TouchRetouchActivity.this).a(TouchRetouchActivity.this.J).a(TouchRetouchActivity.this.H);
            Log.d(TouchRetouchActivity.k(TouchRetouchActivity.this), "Image loaded");
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
        }
        TouchRetouchActivity.this.findViewById(2131362085).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View) {}
        });
        ((RelativeLayout)localObject1).setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymous2View, MotionEvent paramAnonymous2MotionEvent)
          {
            if (paramAnonymous2MotionEvent.getAction() == 0)
            {
              this.a.setBackgroundColor(TouchRetouchActivity.this.getResources().getColor(2131099681));
              TouchRetouchActivity.this.H.setVisibility(0);
              return true;
            }
            if (paramAnonymous2MotionEvent.getAction() == 1)
            {
              this.a.setBackgroundColor(TouchRetouchActivity.this.getResources().getColor(2131099803));
              TouchRetouchActivity.this.H.setVisibility(4);
              return true;
            }
            return false;
          }
        });
        TouchRetouchActivity.l(TouchRetouchActivity.this).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.this.u();
          }
        });
        TouchRetouchActivity.p(TouchRetouchActivity.this).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.m(TouchRetouchActivity.this);
            TouchRetouchActivity.n(TouchRetouchActivity.this);
            if ((TouchRetouchActivity.o(TouchRetouchActivity.this) % 3 == 0) && (TouchRetouchActivity.this.x.a()))
            {
              TouchRetouchActivity.this.x.b();
              TouchRetouchActivity.a(TouchRetouchActivity.this, 0);
            }
          }
        });
        TouchRetouchActivity.r(TouchRetouchActivity.this).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.m(TouchRetouchActivity.this);
            TouchRetouchActivity.q(TouchRetouchActivity.this);
            if ((TouchRetouchActivity.o(TouchRetouchActivity.this) % 3 == 0) && (TouchRetouchActivity.this.x.a()))
            {
              TouchRetouchActivity.this.x.b();
              TouchRetouchActivity.a(TouchRetouchActivity.this, 0);
            }
          }
        });
        TouchRetouchActivity.t(TouchRetouchActivity.this).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.m(TouchRetouchActivity.this);
            TouchRetouchActivity.s(TouchRetouchActivity.this);
            if ((TouchRetouchActivity.o(TouchRetouchActivity.this) % 3 == 0) && (TouchRetouchActivity.this.x.a()))
            {
              TouchRetouchActivity.this.x.b();
              TouchRetouchActivity.a(TouchRetouchActivity.this, 0);
            }
          }
        });
        localObject1 = new com.phototools.touchretouch.b.a(1, "Maximum", TouchRetouchActivity.this.getResources().getDrawable(2131230851));
        Object localObject2 = new com.phototools.touchretouch.b.a(2, "Opacity", TouchRetouchActivity.this.getResources().getDrawable(2131230853));
        com.phototools.touchretouch.b.a localA1 = new com.phototools.touchretouch.b.a(3, "Full", TouchRetouchActivity.this.getResources().getDrawable(2131230854));
        com.phototools.touchretouch.b.a localA2 = new com.phototools.touchretouch.b.a(4, "Half", TouchRetouchActivity.this.getResources().getDrawable(2131230855));
        com.phototools.touchretouch.b.a localA3 = new com.phototools.touchretouch.b.a(5, "Least", TouchRetouchActivity.this.getResources().getDrawable(2131230856));
        com.phototools.touchretouch.b.a localA4 = new com.phototools.touchretouch.b.a(6, "Normal", TouchRetouchActivity.this.getResources().getDrawable(2131230857));
        com.phototools.touchretouch.b.a localA5 = new com.phototools.touchretouch.b.a(7, "Horizontal", TouchRetouchActivity.this.getResources().getDrawable(2131230858));
        com.phototools.touchretouch.b.a localA6 = new com.phototools.touchretouch.b.a(8, "Vertical", TouchRetouchActivity.this.getResources().getDrawable(2131230859));
        com.phototools.touchretouch.b.a localA7 = new com.phototools.touchretouch.b.a(9, "Diagonal", TouchRetouchActivity.this.getResources().getDrawable(2131230860));
        TouchRetouchActivity.this.A = new com.phototools.touchretouch.b.c(TouchRetouchActivity.this, 0);
        TouchRetouchActivity.this.B = new com.phototools.touchretouch.b.c(TouchRetouchActivity.this, 0);
        TouchRetouchActivity.this.A.a((com.phototools.touchretouch.b.a)localObject1);
        TouchRetouchActivity.this.A.a((com.phototools.touchretouch.b.a)localObject2);
        TouchRetouchActivity.this.A.a(localA1);
        TouchRetouchActivity.this.A.a(localA2);
        TouchRetouchActivity.this.A.a(localA3);
        TouchRetouchActivity.this.B.a(localA4);
        TouchRetouchActivity.this.B.a(localA5);
        TouchRetouchActivity.this.B.a(localA6);
        TouchRetouchActivity.this.B.a(localA7);
        TouchRetouchActivity.this.A.a(new com.phototools.touchretouch.b.c.a()
        {
          public void a(com.phototools.touchretouch.b.c paramAnonymous2C, int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            TouchRetouchActivity.this.A.a(paramAnonymous2Int1);
            if (paramAnonymous2Int2 == 1)
            {
              paramAnonymous2C = TouchRetouchActivity.this;
              paramAnonymous2Int1 = 2131361800;
            }
            for (;;)
            {
              TouchRetouchActivity.b(paramAnonymous2C, paramAnonymous2Int1);
              return;
              if (paramAnonymous2Int2 == 2)
              {
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361801;
              }
              else if (paramAnonymous2Int2 == 3)
              {
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361802;
              }
              else if (paramAnonymous2Int2 == 4)
              {
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361803;
              }
              else
              {
                if (paramAnonymous2Int2 != 5) {
                  break;
                }
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361804;
              }
            }
          }
        });
        TouchRetouchActivity.this.A.a(new c.b()
        {
          public void a() {}
        });
        TouchRetouchActivity.this.B.a(new com.phototools.touchretouch.b.c.a()
        {
          public void a(com.phototools.touchretouch.b.c paramAnonymous2C, int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            TouchRetouchActivity.this.B.a(paramAnonymous2Int1);
            if (paramAnonymous2Int2 == 6)
            {
              paramAnonymous2C = TouchRetouchActivity.this;
              paramAnonymous2Int1 = 2131361807;
            }
            for (;;)
            {
              TouchRetouchActivity.c(paramAnonymous2C, paramAnonymous2Int1);
              return;
              if (paramAnonymous2Int2 == 7)
              {
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361808;
              }
              else if (paramAnonymous2Int2 == 8)
              {
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361809;
              }
              else
              {
                if (paramAnonymous2Int2 != 9) {
                  break;
                }
                paramAnonymous2C = TouchRetouchActivity.this;
                paramAnonymous2Int1 = 2131361810;
              }
            }
          }
        });
        TouchRetouchActivity.this.B.a(new c.b()
        {
          public void a() {}
        });
        TouchRetouchActivity.this.o = ((RelativeLayout)TouchRetouchActivity.this.findViewById(2131361811));
        TouchRetouchActivity.e = IntBuffer.allocate(2621440);
        localObject1 = TouchRetouchActivity.b;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("extra uri = ");
        ((StringBuilder)localObject2).append(TouchRetouchActivity.this.getIntent().getDataString());
        d.b((String)localObject1, ((StringBuilder)localObject2).toString());
        try
        {
          TouchRetouchActivity.this.q();
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
        TouchRetouchActivity.this.s();
        TouchRetouchActivity.a(TouchRetouchActivity.this, (GridView)TouchRetouchActivity.this.findViewById(2131362034));
        TouchRetouchActivity.a(TouchRetouchActivity.this, (Button)TouchRetouchActivity.this.findViewById(2131361895));
        TouchRetouchActivity.this.D.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.a(TouchRetouchActivity.this, true);
          }
        });
        TouchRetouchActivity.u(TouchRetouchActivity.this).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.u(TouchRetouchActivity.this).setEnabled(false);
            TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.v(TouchRetouchActivity.this));
            if (TouchRetouchActivity.w(TouchRetouchActivity.this) != null)
            {
              TouchRetouchActivity.a(TouchRetouchActivity.this).setAdapter(new TouchRetouchActivity.n(TouchRetouchActivity.this));
              TouchRetouchActivity.a(TouchRetouchActivity.this).setOnItemClickListener(new AdapterView.OnItemClickListener()
              {
                public void onItemClick(AdapterView<?> paramAnonymous3AdapterView, View paramAnonymous3View, int paramAnonymous3Int, long paramAnonymous3Long)
                {
                  TouchRetouchActivity.a(TouchRetouchActivity.this, (ResolveInfo)TouchRetouchActivity.w(TouchRetouchActivity.this).get(paramAnonymous3Int));
                }
              });
            }
          }
        });
        TouchRetouchActivity.this.C.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View) {}
        });
        ((ImageView)TouchRetouchActivity.this.findViewById(2131361880)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            TouchRetouchActivity.this.C.setVisibility(4);
          }
        });
        TouchRetouchActivity.x(TouchRetouchActivity.this);
        if (TouchRetouchActivity.y(TouchRetouchActivity.this).isShowing()) {
          TouchRetouchActivity.y(TouchRetouchActivity.this).dismiss();
        }
      }
    }, 1000L);
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 603: 
      return ad();
    case 602: 
      return ae();
    case 506: 
      return af();
    case 504: 
      return ab();
    case 502: 
      return F();
    }
    return G();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131623937, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    d.b("touchetouch", "OnDestroy");
    d.b(b, "super.onDestroy()");
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 2131362045) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    if (TouchRetouchLib.NeedToSave())
    {
      this.s = true;
      showDialog(603);
      return true;
    }
    setResult(1);
    finish();
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.p != null) {
      this.p.onPause();
    }
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    paramInt += 2;
    double d1 = paramInt;
    Double.isNaN(d1);
    if (d1 * 1.2D > 250.0D) {
      paramInt = 208;
    }
    b(paramInt);
    TouchRetouchLib.SetBrushSize(paramInt);
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    super.onResume();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (TouchRetouchActivity.this.L == 1) {
          TouchRetouchActivity.this.L = 0;
        } else if (TouchRetouchActivity.this.C.getVisibility() == 0) {
          TouchRetouchActivity.this.C.setVisibility(4);
        }
        if (TouchRetouchActivity.this.p != null) {
          TouchRetouchActivity.this.p.onResume();
        }
      }
    }, 1200L);
  }
  
  protected void onStart()
  {
    super.onStart();
    d.b("touchretouch1", "Before check that is needed device");
    int i1 = 0;
    this.am = false;
    SeekBar localSeekBar = (SeekBar)findViewById(2131362117);
    if (this.am) {
      i1 = 8;
    }
    localSeekBar.setVisibility(i1);
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.aq != 2131361797) && (this.aq != 2131361813)) {
      findViewById(2131361793).setVisibility(0);
    }
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.aq != 2131361797) && (this.aq != 2131361813))
    {
      findViewById(2131361793).setVisibility(8);
      Y();
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
    }
    if (!this.ak)
    {
      System.gc();
      paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
      if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 2) || (paramMotionEvent.getAction() == 1) || (paramMotionEvent.getAction() == 3) || (paramMotionEvent.getAction() == 261) || (paramMotionEvent.getAction() == 262) || (paramMotionEvent.getAction() == 6) || (paramMotionEvent.getAction() == 5))
      {
        int i2 = paramMotionEvent.getPointerCount();
        if (this.an != i2)
        {
          if (i2 == 2) {
            i();
          }
          if (i2 == 1) {
            h();
          }
          this.an = i2;
        }
        int i1 = 0;
        while (i1 < i2)
        {
          Log.e("Value of J=", String.valueOf(paramMotionEvent.getAction()));
          this.M += 1;
          a(new o(this, paramMotionEvent.getPointerId(i1), paramMotionEvent.getAction(), (int)paramMotionEvent.getX(i1), (int)paramMotionEvent.getY(i1)));
          if ((paramMotionEvent.getAction() == 0) && (this.am))
          {
            TouchRetouchLib.SetBrushSize(140);
            Log.e("working", "on it");
          }
          if ((2 == paramMotionEvent.getAction()) && (h(302)) && ((int)paramMotionEvent.getY(i1) > P - A() - 15)) {
            g(302);
          }
          int i3 = paramMotionEvent.getAction();
          if (i3 != 1)
          {
            if (i3 != 6)
            {
              if (i3 == 262) {
                break label625;
              }
              break label665;
            }
          }
          else
          {
            i3 = findViewById(2131361832).getVisibility();
            if ((paramView != null) && (paramView.equals("OBJECT_QUICK_BRUSH")) && (i3 == 4))
            {
              if (paramMotionEvent.getAction() != 1) {
                break label660;
              }
              if ((d >= 450) && (c >= 450))
              {
                P();
                break label660;
              }
              Log.e("Test Height", String.valueOf(d));
              Log.e("Test Width", String.valueOf(c));
              if (Build.VERSION.SDK_INT > 23) {
                if (this.M > 6)
                {
                  Log.e("doing this", "Yes");
                  P();
                }
              }
              for (;;)
              {
                this.M = 0;
                break;
                Log.e("doing this", "No");
                if (this.M > 15) {
                  P();
                }
              }
            }
            this.M = 0;
          }
          label625:
          if (System.currentTimeMillis() - S < 500L)
          {
            i();
            a(TouchRetouchLib.GetZoom());
            h();
          }
          S = System.currentTimeMillis();
          label660:
          this.M = 0;
          label665:
          i1 += 1;
        }
      }
    }
    return true;
  }
  
  protected void p()
  {
    this.ae = new BitmapFactory.Options();
    this.ae.inPreferredConfig = Bitmap.Config.ARGB_4444;
    R = System.currentTimeMillis();
    b(2131361814, true);
    b(2131361819, true);
    b(2131361816, true);
    b(2131361812, true);
    b(2131361795, true);
    b(2131361815, true);
    b(2131361813, true);
    b(2131361806, true);
    b(2131361811, true);
    b(2131361797, true);
    b(2131361817, true);
    a(2131361796, false);
    b(2131361805, false);
    b(2131361799, false);
    b(2131361798, false);
    b(2131361911, true);
    b(2131361912, true);
    b(2131361913, true);
    b(2131361914, true);
    b(2131361915, true);
    b(2131361976, true);
    b(2131361977, true);
    b(2131361978, true);
    b(2131361979, true);
    a(2131361821, true);
    a(2131361820, true);
    g(302);
    g(300);
    g(301);
    this.ao = 2131361800;
    this.ap = 2131361807;
    this.aq = 2131361813;
    Object localObject = (SeekBar)findViewById(2131362117);
    ((SeekBar)localObject).setOnSeekBarChangeListener(this);
    b(((SeekBar)localObject).getProgress());
    this.p = ((GLSurfaceView)findViewById(2131361983));
    this.p.getHolder().setFormat(-3);
    this.p.setEGLContextFactory(new l(null));
    this.p.setEGLConfigChooser(new k(8, 8, 8, 8, 0, 0));
    this.p.setRenderer(new p(this));
    this.p.setRenderMode(1);
    this.p.setOnTouchListener(this);
    localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    int i2 = getWindowManager().getDefaultDisplay().getOrientation();
    String str = b;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("metrics.widthPixels = ");
    localStringBuilder.append(((DisplayMetrics)localObject).widthPixels);
    localStringBuilder.append("metrics.heightPixels = ");
    localStringBuilder.append(((DisplayMetrics)localObject).heightPixels);
    d.b(str, localStringBuilder.toString());
    switch (i2)
    {
    default: 
      break;
    case 1: 
    case 3: 
      T = ((DisplayMetrics)localObject).heightPixels;
      i1 = ((DisplayMetrics)localObject).widthPixels;
      break;
    case 0: 
    case 2: 
      T = ((DisplayMetrics)localObject).widthPixels;
      i1 = ((DisplayMetrics)localObject).heightPixels;
    }
    P = i1;
    int i1 = TouchRetouchLib.init(T, P, i2, Build.VERSION.SDK_INT);
    if (i1 != 0) {
      switch (i1)
      {
      default: 
        break;
      case 2: 
        n = 1;
        aj();
        break;
      case 1: 
        d.b(b, "app is cracked!");
        n = 1;
      }
    }
    W();
    this.ab = new LinkedList();
    this.aa = new LinkedList();
    this.Z = new LinkedList();
    this.W = new LinkedList();
    this.X = new LinkedList();
    this.Y = new LinkedList();
    d.b(b, "lined lists initialized");
    this.ai = getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    if (!this.ai) {
      findViewById(2131362214).setVisibility(0);
    }
    d.b(b, "checked multitouch");
    this.aj = new b();
    this.aj.a(this);
    d.b(b, "init controls finished");
  }
  
  protected void q()
  {
    try
    {
      if (getIntent().getIntExtra("picsource", 0) == 702)
      {
        str1 = getIntent().getStringExtra("picpath");
        String str2 = getIntent().getStringExtra("picorient");
        d.b(b, "processing image from camera");
        a(str1, str2);
        m();
        return;
      }
      str1 = getIntent().getDataString();
      Log.e("PIC SOURCE", str1);
      if (str1 != null) {}
    }
    catch (Exception localException1)
    {
      String str1;
      label135:
      localException1.printStackTrace();
      return;
    }
    try
    {
      Log.e("WHY", "IS THIS RUNNING?");
      this.aj.a((Uri)getIntent().getExtras().get("android.intent.extra.STREAM"));
      return;
    }
    catch (Exception localException2)
    {
      break label135;
    }
    Log.e("OH", "IS THIS RUNNING?");
    this.aj.a(Uri.parse(str1));
    return;
    str1.printStackTrace();
  }
  
  void r()
  {
    this.C.setVisibility(0);
    this.aG = ak();
    if (this.aG != null)
    {
      this.aE.setAdapter(new n());
      int i1 = this.aE.getCount();
      float f1 = i1 / 3 * 110.0F * getApplicationContext().getResources().getDisplayMetrics().density;
      Log.e("Items sizes", String.valueOf(f1));
      Log.e("Items in grid is", String.valueOf(i1));
      ViewGroup.LayoutParams localLayoutParams = this.aE.getLayoutParams();
      localLayoutParams.height = ((int)f1);
      this.aE.setLayoutParams(localLayoutParams);
      this.aE.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          if (paramAnonymousInt == 0)
          {
            TouchRetouchActivity.this.K.setVisibility(0);
            TouchRetouchActivity.this.a = "0";
            TouchRetouchActivity.a(TouchRetouchActivity.this, true);
            return;
          }
          TouchRetouchActivity.this.a = "1";
          TouchRetouchActivity.this.K.setVisibility(0);
          TouchRetouchActivity.b(TouchRetouchActivity.this);
          TouchRetouchActivity.this.K.setVisibility(0);
          TouchRetouchActivity.a(TouchRetouchActivity.this, (ResolveInfo)TouchRetouchActivity.w(TouchRetouchActivity.this).get(paramAnonymousInt));
        }
      });
    }
  }
  
  public void s()
  {
    this.x = new h(this);
    this.x.a(getString(2131820544));
    this.x.a(new com.google.android.gms.ads.a()
    {
      public void c()
      {
        TouchRetouchActivity.this.t();
      }
    });
    t();
  }
  
  protected void t()
  {
    com.google.android.gms.ads.c localC = new com.google.android.gms.ads.c.a().a();
    this.x.a(localC);
  }
  
  public void u()
  {
    g(302);
    if (this.au.booleanValue())
    {
      this.ax.startAnimation(this.aC);
      this.av.startAnimation(this.aA);
      this.aw.startAnimation(this.aA);
      this.ay.startAnimation(this.aA);
      this.av.setVisibility(8);
      this.aw.setVisibility(8);
      this.ay.setVisibility(8);
      this.u.setVisibility(8);
      this.v.setVisibility(8);
      this.w.setVisibility(8);
      this.av.setClickable(false);
      this.aw.setClickable(false);
      this.ay.setClickable(false);
      this.au = Boolean.valueOf(false);
      Log.d("kk", "close");
      return;
    }
    this.ax.startAnimation(this.aB);
    this.av.startAnimation(this.az);
    this.aw.startAnimation(this.az);
    this.ay.startAnimation(this.az);
    this.av.setVisibility(0);
    this.aw.setVisibility(0);
    this.ay.setVisibility(0);
    this.u.setVisibility(0);
    this.v.setVisibility(0);
    this.w.setVisibility(0);
    this.av.setClickable(true);
    this.aw.setClickable(true);
    this.ay.setClickable(true);
    this.au = Boolean.valueOf(true);
    Log.e("kk", "open");
  }
  
  protected void v()
  {
    LayoutInflater.from(this).inflate(2131558436, null);
    new b.a(this, 2131886082).b("Do you want to edit another picture?").a("Open Gallery").a(Html.fromHtml("<font color='#27c886'>Ok</font>"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.A(TouchRetouchActivity.this);
      }
    }).b(Html.fromHtml("<font color='#27c886'>Cancel</font>"), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).c();
  }
  
  class a
  {
    int a = 0;
    final TouchRetouchActivity b;
    
    a(TouchRetouchActivity paramTouchRetouchActivity, int paramInt)
    {
      this.b = paramTouchRetouchActivity;
      this.a = paramInt;
    }
  }
  
  class b
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    b(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.a.showDialog(502);
    }
  }
  
  class c
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    c(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      TouchRetouchActivity.a(this.a, false);
    }
  }
  
  class d
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    d(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.a.n();
      TouchRetouchActivity.b(false);
      if (this.a.s)
      {
        this.a.s = false;
        this.a.setResult(1);
        this.a.startActivity(new Intent(this.a, ActivityStart.class));
      }
      for (;;)
      {
        this.a.finish();
        return;
        this.a.setResult(0);
      }
    }
  }
  
  class e
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    e(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.a.s = false;
      TouchRetouchActivity.a(this.a, false);
    }
  }
  
  class f
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    f(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      switch (paramInt)
      {
      default: 
        Log.e("start Long", "Ops4");
        return;
      case 2: 
        Log.e("start Long", "Ops2");
        TouchRetouchActivity.c(this.a);
        this.a.a(new TouchRetouchActivity.a(TouchRetouchActivity.this, this.a, 6));
        return;
      case 1: 
        Log.e("start Long", "Ops1");
        TouchRetouchActivity.c(this.a);
        this.a.a(new TouchRetouchActivity.a(TouchRetouchActivity.this, this.a, 6));
        return;
      }
      Log.e("start Long", "Ops0");
      TouchRetouchActivity.b(this.a);
    }
  }
  
  class g
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    g(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt) {}
  }
  
  class h
    implements Runnable
  {
    final TouchRetouchActivity a;
    
    h(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      View localView = this.a.findViewById(2131361832);
      try
      {
        if (localView.getVisibility() == 0)
        {
          Animation localAnimation = AnimationUtils.loadAnimation(this.a, 2130771993);
          localAnimation.setDuration(300L);
          localView.startAnimation(localAnimation);
          localView.setVisibility(4);
        }
        return;
      }
      catch (Exception localException) {}
    }
  }
  
  class i
    extends TimerTask
  {
    final TouchRetouchActivity a;
    
    i(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      TouchRetouchActivity.g(this.a);
    }
  }
  
  class j
    implements Runnable
  {
    final TouchRetouchActivity a;
    
    j(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      View localView = this.a.findViewById(2131361793);
      if (localView.getVisibility() == 0)
      {
        Animation localAnimation = AnimationUtils.loadAnimation(this.a, 2130771993);
        localAnimation.setDuration(300L);
        localView.startAnimation(localAnimation);
        localView.setVisibility(4);
      }
    }
  }
  
  private static class k
    implements GLSurfaceView.EGLConfigChooser
  {
    private static int g = 4;
    private static int[] h = { 12324, 4, 12323, 4, 12322, 4, 12352, g, 12344 };
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    private int[] i = new int[1];
    
    k(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
      this.e = paramInt1;
      this.d = paramInt2;
      this.b = paramInt3;
      this.a = paramInt4;
      this.c = paramInt5;
      this.f = paramInt6;
    }
    
    private int a(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig, int paramInt1, int paramInt2)
    {
      if (paramEGL10.eglGetConfigAttrib(paramEGLDisplay, paramEGLConfig, paramInt1, this.i)) {
        paramInt2 = this.i[0];
      }
      return paramInt2;
    }
    
    EGLConfig a(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig[] paramArrayOfEGLConfig)
    {
      int k = paramArrayOfEGLConfig.length;
      int j = 0;
      while (j < k)
      {
        EGLConfig localEGLConfig = paramArrayOfEGLConfig[j];
        int m = a(paramEGL10, paramEGLDisplay, localEGLConfig, 12325, 0);
        int n = a(paramEGL10, paramEGLDisplay, localEGLConfig, 12326, 0);
        if ((m >= this.c) && (n >= this.f))
        {
          m = a(paramEGL10, paramEGLDisplay, localEGLConfig, 12324, 0);
          n = a(paramEGL10, paramEGLDisplay, localEGLConfig, 12323, 0);
          int i1 = a(paramEGL10, paramEGLDisplay, localEGLConfig, 12322, 0);
          int i2 = a(paramEGL10, paramEGLDisplay, localEGLConfig, 12321, 0);
          if ((m == this.e) && (n == this.d) && (i1 == this.b) && (i2 == this.a)) {
            return localEGLConfig;
          }
        }
        j += 1;
      }
      return null;
    }
    
    public EGLConfig chooseConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay)
    {
      int[] arrayOfInt = new int[1];
      paramEGL10.eglChooseConfig(paramEGLDisplay, h, null, 0, arrayOfInt);
      int j = arrayOfInt[0];
      if (j > 0)
      {
        EGLConfig[] arrayOfEGLConfig = new EGLConfig[j];
        paramEGL10.eglChooseConfig(paramEGLDisplay, h, arrayOfEGLConfig, j, arrayOfInt);
        return a(paramEGL10, paramEGLDisplay, arrayOfEGLConfig);
      }
      throw new IllegalArgumentException("No configs match configSpec");
    }
  }
  
  private static class l
    implements GLSurfaceView.EGLContextFactory
  {
    private static int a = 12440;
    
    private l() {}
    
    public EGLContext createContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      d.b(TouchRetouchActivity.w(), "creating OpenGL ES 2.0 context");
      TouchRetouchActivity.a("Before eglCreateContext", paramEGL10);
      paramEGLDisplay = paramEGL10.eglCreateContext(paramEGLDisplay, paramEGLConfig, EGL10.EGL_NO_CONTEXT, new int[] { a, 2, 12344 });
      TouchRetouchActivity.a("After eglCreateContext", paramEGL10);
      return paramEGLDisplay;
    }
    
    public void destroyContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLContext paramEGLContext)
    {
      paramEGL10.eglDestroyContext(paramEGLDisplay, paramEGLContext);
    }
  }
  
  class m
    extends AsyncTask<String, Void, Void>
  {
    ProgressDialog a;
    
    m() {}
    
    protected Void a(String... paramVarArgs)
    {
      try
      {
        if (TouchRetouchActivity.this.E == -1)
        {
          Log.e("Going to", TouchRetouchActivity.this.F.getData().toString());
          paramVarArgs = TouchRetouchActivity.this.F.getData();
          paramVarArgs = MediaStore.Images.Media.getBitmap(TouchRetouchActivity.this.getContentResolver(), paramVarArgs);
          Object localObject = TouchRetouchActivity.this.F.getData();
          localObject = TouchRetouchActivity.this.managedQuery((Uri)localObject, new String[] { "_data" }, null, null, null);
          int i = ((Cursor)localObject).getColumnIndexOrThrow("_data");
          ((Cursor)localObject).moveToFirst();
          localObject = new File(((Cursor)localObject).getString(i)).getAbsolutePath();
          try
          {
            i = new android.support.e.a((String)localObject).a("Orientation", 1);
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Exif: ");
            ((StringBuilder)localObject).append(i);
            Log.d("EXIF", ((StringBuilder)localObject).toString());
            localObject = new Matrix();
            if (i == 6) {
              ((Matrix)localObject).postRotate(90.0F);
            } else if (i == 3) {
              ((Matrix)localObject).postRotate(180.0F);
            } else if (i == 8) {
              ((Matrix)localObject).postRotate(270.0F);
            }
            paramVarArgs = Bitmap.createBitmap(paramVarArgs, 0, 0, paramVarArgs.getWidth(), paramVarArgs.getHeight(), (Matrix)localObject, true);
            i = new Random().nextInt(651) + 20;
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(Environment.getExternalStorageDirectory().toString());
            ((StringBuilder)localObject).append("/removertemp");
            localObject = ((StringBuilder)localObject).toString();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(String.valueOf(i));
            localStringBuilder.append(".jpg");
            localObject = new FileOutputStream(new File((String)localObject, localStringBuilder.toString()));
            paramVarArgs.compress(Bitmap.CompressFormat.JPEG, 100, (OutputStream)localObject);
            paramVarArgs = TouchRetouchActivity.this;
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(Environment.getExternalStorageDirectory().toString());
            ((StringBuilder)localObject).append("/removertemp/");
            ((StringBuilder)localObject).append(String.valueOf(i));
            ((StringBuilder)localObject).append(".jpg");
            paramVarArgs.a(Uri.fromFile(new File(((StringBuilder)localObject).toString())));
          }
          catch (Exception paramVarArgs)
          {
            paramVarArgs.printStackTrace();
          }
          if (TouchRetouchActivity.this.x.a()) {
            TouchRetouchActivity.this.x.b();
          }
        }
      }
      catch (Exception paramVarArgs)
      {
        paramVarArgs.printStackTrace();
      }
      return null;
    }
    
    protected void a(Void paramVoid)
    {
      if (TouchRetouchActivity.this.x.a()) {
        TouchRetouchActivity.this.x.b();
      }
    }
    
    protected void onPreExecute()
    {
      this.a = new ProgressDialog(TouchRetouchActivity.this, 2131886082);
      this.a.setMessage("Please wait, Image Loading...");
      this.a.show();
      super.onPreExecute();
    }
  }
  
  class n
    extends BaseAdapter
  {
    PackageManager a = TouchRetouchActivity.this.getPackageManager();
    
    public n() {}
    
    public int getCount()
    {
      return TouchRetouchActivity.w(TouchRetouchActivity.this).size();
    }
    
    public Object getItem(int paramInt)
    {
      return TouchRetouchActivity.w(TouchRetouchActivity.this).get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = new TouchRetouchActivity.r();
        paramViewGroup = LayoutInflater.from(TouchRetouchActivity.this).inflate(2131558461, paramViewGroup, false);
        paramView.a = ((ImageView)paramViewGroup.findViewById(2131362020));
        paramView.b = ((TextView)paramViewGroup.findViewById(2131362177));
        paramView.c = ((TextView)paramViewGroup.findViewById(2131362178));
        paramViewGroup.setTag(paramView);
      }
      else
      {
        localObject = (TouchRetouchActivity.r)paramView.getTag();
        paramViewGroup = paramView;
        paramView = (View)localObject;
      }
      if (paramInt == 0)
      {
        localObject = (ResolveInfo)TouchRetouchActivity.w(TouchRetouchActivity.this).get(paramInt);
        paramView.a.setImageDrawable(TouchRetouchActivity.this.getApplicationContext().getResources().getDrawable(2131230907));
        paramView.b.setText("Save to gallery");
        paramView.c.setText("Save");
        return paramViewGroup;
      }
      Object localObject = (ResolveInfo)TouchRetouchActivity.w(TouchRetouchActivity.this).get(paramInt);
      paramView.a.setImageDrawable(((ResolveInfo)localObject).loadIcon(this.a));
      paramView.b.setText(((ResolveInfo)localObject).loadLabel(this.a));
      paramView.c.setText(((ResolveInfo)localObject).activityInfo.packageName);
      return paramViewGroup;
    }
  }
  
  private class o
  {
    int a;
    public int b;
    int c;
    final TouchRetouchActivity d;
    public int e;
    
    o(TouchRetouchActivity paramTouchRetouchActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.d = paramTouchRetouchActivity;
      this.e = paramInt1;
      this.b = paramInt2;
      this.a = paramInt3;
      this.c = paramInt4;
    }
  }
  
  private class p
    implements GLSurfaceView.Renderer
  {
    private TouchRetouchActivity b;
    private int c;
    private int d;
    
    p(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.b = paramTouchRetouchActivity;
    }
    
    private void a(IntBuffer paramIntBuffer, int paramInt)
    {
      int i = 0;
      for (;;)
      {
        int k = paramInt / 2;
        if (i >= k) {
          break;
        }
        int j = 0;
        while (j < k)
        {
          int m = i * paramInt + j;
          int i2 = paramInt - 1;
          int i1 = i2 - i;
          int n = j * paramInt + i1;
          i2 -= j;
          i1 = i1 * paramInt + i2;
          i2 = i2 * paramInt + i;
          int i3 = paramIntBuffer.array()[m];
          paramIntBuffer.array()[m] = paramIntBuffer.array()[i2];
          paramIntBuffer.array()[i2] = paramIntBuffer.array()[i1];
          paramIntBuffer.array()[i1] = paramIntBuffer.array()[n];
          paramIntBuffer.array()[n] = i3;
          j += 1;
        }
        i += 1;
      }
    }
    
    private void a(GL10 paramGL10)
    {
      paramGL10 = this.b.e();
      if (paramGL10 != null)
      {
        String str = TouchRetouchActivity.b;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("orient = ");
        localStringBuilder.append(TouchRetouchActivity.h);
        d.b(str, localStringBuilder.toString());
        str = TouchRetouchActivity.b;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("scale = ");
        localStringBuilder.append(TouchRetouchActivity.j);
        d.b(str, localStringBuilder.toString());
        TouchRetouchActivity.d = TouchRetouchActivity.a(TouchRetouchActivity.d, TouchRetouchActivity.j);
        TouchRetouchActivity.c = TouchRetouchActivity.a(TouchRetouchActivity.c, TouchRetouchActivity.j);
        int i = TouchRetouchActivity.a(TouchRetouchActivity.h);
        d.b(TouchRetouchActivity.b, "GetOrientation");
        TouchRetouchLib.splitImage3(paramGL10, TouchRetouchActivity.j, i);
        this.b.l();
        this.b.r = true;
      }
    }
    
    private void b(GL10 paramGL10)
    {
      Bitmap localBitmap = this.b.f();
      if (localBitmap != null)
      {
        a(paramGL10, localBitmap, 512);
        localBitmap.recycle();
        this.b.l();
        this.b.r = true;
      }
    }
    
    private void c(GL10 paramGL10)
    {
      Object localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inPreferredConfig = Bitmap.Config.ARGB_8888;
      Bitmap localBitmap = BitmapFactory.decodeResource(this.b.getResources(), 2131230865, (BitmapFactory.Options)localObject);
      IntBuffer localIntBuffer = IntBuffer.allocate(localBitmap.getWidth() * localBitmap.getHeight());
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int i = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.b.getResources(), 2131230866, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int j = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.b.getResources(), 2131230867, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int k = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.b.getResources(), 2131230868, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int m = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.b.getResources(), 2131230869, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int n = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localObject = BitmapFactory.decodeResource(this.b.getResources(), 2131230870, (BitmapFactory.Options)localObject);
      ((Bitmap)localObject).getPixels(localIntBuffer.array(), 0, ((Bitmap)localObject).getWidth(), 0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      int i1 = a(paramGL10, localIntBuffer, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      ((Bitmap)localObject).recycle();
      TouchRetouchLib.setCloneTextures(new int[] { i, j, k, m, n }, i1);
    }
    
    private void d()
    {
      for (;;)
      {
        TouchRetouchActivity.a localA = this.b.b();
        if (localA == null) {
          break;
        }
        switch (localA.a)
        {
        default: 
          break;
        case 6: 
          a();
          break;
        case 5: 
          b();
          break;
        case 4: 
          TouchRetouchLib.OnClearAllSelected();
          break;
        case 3: 
          if (!TouchRetouchLib.OnGoSelected()) {
            this.b.b(Integer.valueOf(1));
          }
          break;
        case 2: 
          TouchRetouchLib.OnUndoSelected();
          break;
        case 1: 
          TouchRetouchLib.OnRedoSelected();
          this.b.l();
        }
      }
    }
    
    private void e()
    {
      Object localObject = this.b.g();
      int i = 0;
      while (localObject != null)
      {
        TouchRetouchActivity.o localO = this.b.g();
        i += 1;
        if ((i < 2) || (i % 15 == 0) || (((TouchRetouchActivity.o)localObject).b != 2) || (localO == null) || (localO.b != 2)) {
          TouchRetouchLib.nativeTouch(((TouchRetouchActivity.o)localObject).a, ((TouchRetouchActivity.o)localObject).c, ((TouchRetouchActivity.o)localObject).b, ((TouchRetouchActivity.o)localObject).e);
        }
        localObject = localO;
      }
    }
    
    private void f()
    {
      long l1 = System.currentTimeMillis();
      long l2 = TouchRetouchActivity.x();
      TouchRetouchActivity.a(l1);
      double d1 = l1 - l2;
      Double.isNaN(d1);
      TouchRetouchLib.step((float)(d1 / 1000.0D));
    }
    
    int a(GL10 paramGL10, IntBuffer paramIntBuffer, int paramInt1, int paramInt2)
    {
      int[] arrayOfInt = new int[1];
      int i = 0;
      paramGL10.glGenTextures(1, arrayOfInt, 0);
      int j = arrayOfInt[0];
      paramGL10.glBindTexture(3553, j);
      paramGL10.glTexParameterf(3553, 10241, 9985.0F);
      paramGL10.glTexParameterf(3553, 10241, 9987.0F);
      paramGL10.glTexParameterf(3553, 10242, 10497.0F);
      paramGL10.glTexParameterf(3553, 10243, 10497.0F);
      while (i < paramIntBuffer.array().length)
      {
        int k = paramIntBuffer.get(i);
        paramIntBuffer.put(i, (k >>> 8 & 0xFF) << 8 | (k >>> 24 & 0xFF) << 24 | (k & 0xFF) << 16 | k >>> 16 & 0xFF);
        i += 1;
      }
      paramGL10.glTexImage2D(3553, 0, 6408, paramInt1, paramInt2, 0, 6408, 5121, paramIntBuffer);
      return j;
    }
    
    void a()
    {
      Object localObject1 = TouchRetouchActivity.a();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("image/jpeg");
      localObject1 = Uri.fromFile((File)localObject1);
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(com.phototools.touchretouch.a.a.e);
      ((StringBuilder)localObject2).append(" Create By : ");
      ((StringBuilder)localObject2).append(com.phototools.touchretouch.a.a.d);
      localIntent.putExtra("android.intent.extra.TEXT", ((StringBuilder)localObject2).toString());
      localObject2 = TouchRetouchActivity.b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Uri = ");
      localStringBuilder.append(localObject1.toString());
      d.b((String)localObject2, localStringBuilder.toString());
      localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject1);
      this.b.startActivity(Intent.createChooser(localIntent, this.b.getResources().getString(2131820699)));
      TouchRetouchLib.ImageSaved();
      this.b.l();
    }
    
    void a(GL10 paramGL10, Bitmap paramBitmap, int paramInt)
    {
      int k = paramBitmap.getWidth();
      int m = paramBitmap.getHeight();
      TouchRetouchActivity.d = k;
      TouchRetouchActivity.c = m;
      int i2 = paramInt - 1;
      int i;
      if (k % i2 > 0) {
        i = k / (paramInt - 1) + 1;
      } else {
        i = k / (paramInt - 1);
      }
      TouchRetouchActivity.m = i;
      if (m % i2 > 0) {
        i = m / (paramInt - 1) + 1;
      } else {
        i = m / (paramInt - 1);
      }
      TouchRetouchActivity.l = i;
      int[] arrayOfInt = new int[TouchRetouchActivity.m * TouchRetouchActivity.l];
      IntBuffer localIntBuffer = TouchRetouchActivity.e;
      int i1;
      String str;
      StringBuilder localStringBuilder;
      int n;
      int i3;
      int i4;
      int j;
      if (TouchRetouchActivity.h == 90)
      {
        d.b(TouchRetouchActivity.b, "Orientation is vertical!!!");
        i = TouchRetouchActivity.l;
        i1 = paramInt - 1;
        i = paramInt - (i * i1 - m + 1);
        str = TouchRetouchActivity.b;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("offset_x = ");
        localStringBuilder.append(paramInt - (TouchRetouchActivity.m * i1 - k + 1));
        localStringBuilder.append(", offset_y = ");
        localStringBuilder.append(i);
        d.b(str, localStringBuilder.toString());
        n = 0;
        i3 = 0;
        while (n < TouchRetouchActivity.m)
        {
          i4 = TouchRetouchActivity.l - 1;
          while (i4 >= 0)
          {
            int i8 = n * i1;
            int i5;
            if (i4 == 0) {
              i5 = 0;
            } else {
              i5 = (i4 - 1) * i1 + i - 1;
            }
            int i6;
            if (i4 == 0) {
              i6 = (paramInt - i) * paramInt;
            } else {
              i6 = 0;
            }
            j = n + 1;
            int i7;
            if (j * i2 >= k) {
              i7 = paramInt - (j * i1 - k + 1);
            } else {
              i7 = paramInt;
            }
            if (i4 == 0) {
              j = i;
            } else {
              j = paramInt;
            }
            localIntBuffer.clear();
            str = TouchRetouchActivity.b;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("j = ");
            localStringBuilder.append(i4);
            localStringBuilder.append(", i = ");
            localStringBuilder.append(n);
            localStringBuilder.append("width = ");
            localStringBuilder.append(k);
            localStringBuilder.append("height = ");
            localStringBuilder.append(m);
            d.b(str, localStringBuilder.toString());
            str = TouchRetouchActivity.b;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(i8);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(i5);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(i7);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(j);
            d.b(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), 0, 512, i8, i5, i7, j);
            localIntBuffer.clear();
            str = TouchRetouchActivity.b;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(i8);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(i5);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(i7);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(j);
            d.b(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), i6, 512, i8, i5, i7, j);
            a(localIntBuffer, paramInt);
            arrayOfInt[i3] = a(paramGL10, localIntBuffer, paramInt, paramInt);
            i3 += 1;
            i4 -= 1;
          }
          n += 1;
        }
        Log.e("Current Pos:", String.valueOf("split2"));
        i = TouchRetouchActivity.l;
        j = TouchRetouchActivity.m;
      }
      for (;;)
      {
        TouchRetouchLib.splitImage2(arrayOfInt, m, k, i, j, paramInt);
        return;
        i = 0;
        while (i < TouchRetouchActivity.l)
        {
          for (j = 0; j < TouchRetouchActivity.m; j = i3)
          {
            i1 = paramInt - 1;
            this.c = (i1 * j);
            this.d = (i1 * i);
            i3 = j + 1;
            if (i3 * i2 >= k) {
              n = paramInt - (i3 * i1 - k + 1);
            } else {
              n = paramInt;
            }
            i4 = i + 1;
            if (i4 * i2 >= m) {
              i1 = paramInt - (i4 * i1 - m + 1);
            } else {
              i1 = paramInt;
            }
            localIntBuffer.clear();
            str = TouchRetouchActivity.b;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(this.c);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(this.d);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(n);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(i1);
            d.b(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), 0, 512, this.c, this.d, n, i1);
            arrayOfInt[(TouchRetouchActivity.m * i + j)] = a(paramGL10, localIntBuffer, paramInt, paramInt);
          }
          i += 1;
        }
        Log.e("Current Pos:", String.valueOf("split21"));
        n = TouchRetouchActivity.m;
        j = TouchRetouchActivity.l;
        i = m;
        m = k;
        k = i;
        i = n;
      }
    }
    
    void b()
    {
      File localFile = new File(d.c(), TouchRetouchActivity.h(this.b));
      String str = TouchRetouchActivity.b;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Saving to folder ");
      localStringBuilder.append(localFile.getAbsolutePath());
      d.b(str, localStringBuilder.toString());
      int j = TouchRetouchActivity.a(TouchRetouchActivity.h) % 2;
      int i;
      if (j == 1) {
        i = TouchRetouchActivity.c;
      } else {
        i = TouchRetouchActivity.d;
      }
      if (j == 1) {
        j = TouchRetouchActivity.d;
      } else {
        j = TouchRetouchActivity.c;
      }
      d.b(TouchRetouchActivity.b, "save image func");
      TouchRetouchLib.SaveImage(localFile.getAbsolutePath(), i, j);
      str = TouchRetouchActivity.w();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("SaveImage: ");
      localStringBuilder.append(TouchRetouchActivity.b);
      Log.e(str, localStringBuilder.toString());
      if (TouchRetouchActivity.this.a.equals("1")) {}
      for (;;)
      {
        this.b.l();
        return;
        d.b(TouchRetouchActivity.b, "before save func");
        new a(this, this.b, localFile);
        this.b.a(Integer.valueOf(1));
        TouchRetouchLib.ImageSaved();
      }
    }
    
    void c()
    {
      TouchRetouchLib.loadResourcesDoubleTexFrag(d.a(2131755008, this.b));
      TouchRetouchLib.loadResourcesDoubleTexVert(d.a(2131755009, this.b));
      TouchRetouchLib.loadResourcesNoTexFrag(d.a(2131755010, this.b));
      TouchRetouchLib.loadResourcesNoTexVert(d.a(2131755011, this.b));
      TouchRetouchLib.loadResourcesSingleTexFrag(d.a(2131755012, this.b));
      TouchRetouchLib.loadResourcesSingleTexVert(d.a(2131755013, this.b));
    }
    
    public void onDrawFrame(GL10 paramGL10)
    {
      paramGL10.glClearColor(1.0F, 1.0F, 1.0F, 0.0F);
      b(paramGL10);
      a(paramGL10);
      d();
      e();
      f();
    }
    
    public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
    {
      d.b(TouchRetouchActivity.b, "Surface changed");
      TouchRetouchLib.surfaceChanged(this.b.getWindowManager().getDefaultDisplay().getOrientation());
      paramGL10 = TouchRetouchActivity.b;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("orientation = ");
      ((StringBuilder)localObject).append(this.b.getWindowManager().getDefaultDisplay().getOrientation());
      d.b(paramGL10, ((StringBuilder)localObject).toString());
      paramGL10 = this.b.findViewById(2131362165);
      localObject = this.b.findViewById(2131361891);
      View localView = this.b.findViewById(2131362040);
      switch (this.b.getWindowManager().getDefaultDisplay().getOrientation())
      {
      default: 
        break;
      case 1: 
      case 3: 
        paramInt1 = localView.getHeight();
        paramInt2 = localView.getWidth();
        break;
      case 0: 
      case 2: 
        paramInt1 = localView.getWidth();
        paramInt2 = localView.getHeight();
      }
      TouchRetouchLib.SetScreenSize(paramInt1, paramInt2);
      TouchRetouchLib.SetMargins(paramGL10.getHeight(), ((View)localObject).getHeight());
    }
    
    public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
    {
      d.b(TouchRetouchActivity.b, "Surface created");
      c();
      TouchRetouchLib.SetMargins(this.b.findViewById(2131362165).getHeight(), this.b.findViewById(2131361891).getHeight());
      TouchRetouchLib.initResources(this.b.getWindowManager().getDefaultDisplay().getOrientation());
      c(paramGL10);
      TouchRetouchActivity.i(this.b);
      this.b.k();
    }
    
    class a
      implements MediaScannerConnection.MediaScannerConnectionClient
    {
      final TouchRetouchActivity.p a;
      private File c;
      private MediaScannerConnection d;
      
      a(TouchRetouchActivity.p paramP, Context paramContext, File paramFile)
      {
        this.a = paramP;
        this.c = paramFile;
        this.d = new MediaScannerConnection(paramContext, this);
        this.d.connect();
      }
      
      public void onMediaScannerConnected()
      {
        this.d.scanFile(this.c.getAbsolutePath(), null);
      }
      
      public void onScanCompleted(String paramString, Uri paramUri)
      {
        this.d.disconnect();
      }
    }
  }
  
  class q
    implements Runnable
  {
    final TouchRetouchActivity a;
    private int c;
    
    q(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      if (!TouchRetouchActivity.d(this.a))
      {
        if (this.a.r)
        {
          float f = TouchRetouchLib.GetZoom();
          if (TouchRetouchActivity.f != f)
          {
            TouchRetouchActivity.f = f;
            this.a.a(f);
          }
        }
        boolean bool1 = TouchRetouchActivity.d(this.a);
        boolean bool3 = false;
        if ((!bool1) && (TouchRetouchActivity.e(this.a)))
        {
          TouchRetouchActivity.f(this.a);
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              TouchRetouchActivity.this.K.setVisibility(4);
            }
          }, 1000L);
          TouchRetouchActivity.b(this.a, false);
        }
        TouchRetouchActivity localTouchRetouchActivity;
        if (this.a.r)
        {
          localTouchRetouchActivity = this.a;
          if (TouchRetouchLib.getUndoState() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          }
          boolean bool2;
          if (TouchRetouchLib.getRedoState() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          }
          localTouchRetouchActivity.a(bool1, bool2);
        }
        bool1 = PreferenceManager.getDefaultSharedPreferences(this.a).getBoolean("pref_check_hide_toolbars", true);
        if ((this.a.r) && (bool1))
        {
          int i = TouchRetouchLib.getPanelsVisible();
          if (i != this.c)
          {
            localTouchRetouchActivity = this.a;
            bool1 = bool3;
            if (i != 0) {
              bool1 = true;
            }
            localTouchRetouchActivity.a(bool1);
          }
          this.c = i;
        }
        while (this.a.c() != null) {
          this.a.o();
        }
        while (this.a.d() != null) {
          this.a.showDialog(605);
        }
      }
    }
  }
  
  static class r
  {
    ImageView a;
    TextView b;
    TextView c;
    
    r() {}
  }
}
