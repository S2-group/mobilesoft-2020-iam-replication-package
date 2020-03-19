package eraser.touch.photo.vn.touch.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.advasoft.touchretouch.TouchRetouchLib;
import eraser.touch.photo.vn.touch.a.b;
import eraser.touch.photo.vn.touch.a.c;
import eraser.touch.photo.vn.touch.a.c.a;
import eraser.touch.photo.vn.touch.a.f;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

public class TouchRetouchActivity
  extends Activity
  implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener
{
  private static int A = 0;
  public static final String a = b.a;
  static int b = 0;
  static int c = 0;
  public static IntBuffer d;
  public static float e = 0.0F;
  public static BitmapFactory.Options f;
  public static int g = 0;
  public static String h;
  public static int i = 0;
  public static int j = 0;
  public static int k = 0;
  public static int l = 0;
  public static int m = 0;
  private static String u = "MyTouchView";
  private static boolean v = false;
  private static int w;
  private static int x = 0;
  private static long y;
  private static long z;
  private Runnable B = new j(this);
  private TranslateAnimation C;
  private Queue<a> D;
  private Queue<Integer> E;
  private Queue<Integer> F;
  private Queue<String> G;
  private Queue<Bitmap> H;
  private Queue<s> I;
  private boolean J = false;
  private Bitmap K;
  private BitmapFactory.Options L;
  private String M;
  private ExifInterface N;
  private String O;
  private boolean P;
  private boolean Q;
  private boolean R;
  private boolean S = false;
  private int T = 0;
  private int U;
  private int V;
  private int W = -1;
  private boolean X = false;
  private boolean Y;
  private boolean Z = false;
  RelativeLayout n;
  GLSurfaceView o;
  boolean p = false;
  protected boolean q = false;
  protected boolean r = false;
  ImageView s;
  public Timer t;
  
  static
  {
    e = 0.0F;
    y = 0L;
  }
  
  public TouchRetouchActivity() {}
  
  private Dialog A()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131689579);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099678));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2130903042, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt != 0) {
          return;
        }
        TouchRetouchActivity.this.i();
      }
    }).create();
  }
  
  private Dialog B()
  {
    f.a(a, "InitChoseResolutionDialog");
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131689580));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099773));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(x(), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 3: 
          TouchRetouchActivity.b(TouchRetouchActivity.this);
          paramAnonymousDialogInterface = TouchRetouchActivity.f;
          paramAnonymousDialogInterface.inSampleSize *= 8;
          TouchRetouchActivity.i(TouchRetouchActivity.this).recycle();
          TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.h, TouchRetouchActivity.f));
          TouchRetouchActivity.this.a(TouchRetouchActivity.i(TouchRetouchActivity.this));
          return;
        case 2: 
          TouchRetouchActivity.b(TouchRetouchActivity.this);
          paramAnonymousDialogInterface = TouchRetouchActivity.f;
          paramAnonymousDialogInterface.inSampleSize *= 4;
          TouchRetouchActivity.i(TouchRetouchActivity.this).recycle();
          TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.h, TouchRetouchActivity.f));
          TouchRetouchActivity.this.a(TouchRetouchActivity.i(TouchRetouchActivity.this));
          return;
        case 1: 
          TouchRetouchActivity.b(TouchRetouchActivity.this);
          paramAnonymousDialogInterface = TouchRetouchActivity.f;
          paramAnonymousDialogInterface.inSampleSize *= 2;
          TouchRetouchActivity.i(TouchRetouchActivity.this).recycle();
          TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.h, TouchRetouchActivity.f));
          TouchRetouchActivity.this.a(TouchRetouchActivity.i(TouchRetouchActivity.this));
          return;
        }
        TouchRetouchActivity.b(TouchRetouchActivity.this);
        TouchRetouchActivity.this.a(TouchRetouchActivity.i(TouchRetouchActivity.this));
      }
    }).create();
  }
  
  private void C()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
    TouchRetouchLib.SetRetouchAnimation(((SharedPreferences)localObject).getBoolean("pref_check_show_animation", true));
    localObject = ((SharedPreferences)localObject).getString("pref_list_show_hint", "1");
    if (((String)localObject).equals("1"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(0);
      f.a(a, "Auto set");
      return;
    }
    if (((String)localObject).equals("2"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(1);
      f.a(a, "Top Left");
      return;
    }
    if (((String)localObject).equals("3"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(2);
      f.a(a, "Top Right");
      return;
    }
    if (((String)localObject).equals("4"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(3);
      f.a(a, "Off is set");
      return;
    }
    f.a(a, "The value is not allowed!!!!!!!!!!");
  }
  
  private void D()
  {
    g(302);
    g(301);
    g(300);
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
    localEditor.apply();
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i1);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296397), i1);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    this.W = 2131296258;
    TouchRetouchLib.SetEditTool(1, w());
    S();
  }
  
  private void E()
  {
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i1);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_MIRRORING");
    localEditor.apply();
    g(300);
    g(301);
    g(302);
  }
  
  private void F()
  {
    g(300);
    g(301);
    g(302);
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i1);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_HARDNESS");
    localEditor.apply();
  }
  
  private void G()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    findViewById(2131296257).setVisibility(8);
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i1);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i1);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    g(300);
    g(301);
    findViewById(2131296259).setVisibility(8);
    if ((h(302)) && (x == 3))
    {
      g(302);
      z();
    }
    else
    {
      U();
      o(302);
      x = 3;
    }
    this.W = 2131296260;
    TouchRetouchLib.SetEditTool(5, w());
    S();
  }
  
  private void H()
  {
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i1);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    g(300);
    g(301);
    TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131296489)).getProgress());
  }
  
  private void I()
  {
    ad();
    a(new a(this, 4));
  }
  
  private void J()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_ERASER");
    localEditor.apply();
    g(302);
    g(301);
    g(300);
    findViewById(2131296257).setVisibility(8);
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i1);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    this.W = 2131296269;
    TouchRetouchLib.SetEditTool(2, w());
    S();
  }
  
  private void K()
  {
    a(new a(this, 3));
    ad();
    g(302);
    g(301);
    g(300);
  }
  
  private void L()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_LASSO");
    localEditor.apply();
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i1);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    g(302);
    g(301);
    g(300);
    this.W = 2131296275;
    TouchRetouchLib.SetEditTool(0, w());
  }
  
  private boolean M()
  {
    findViewById(2131296274).setVisibility(8);
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296407), i1);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i2);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296409), i2);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    this.W = 2131296278;
    TouchRetouchLib.SetEditTool(1, w());
    S();
    return true;
  }
  
  private void N()
  {
    g(302);
    g(301);
    g(300);
    a(new a(this, 1));
    ad();
  }
  
  private void O()
  {
    g(302);
    g(301);
    g(300);
    a(new a(this, 2));
    ad();
  }
  
  private void P()
  {
    c = f.outWidth;
    b = f.outHeight;
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bm width = ");
    localStringBuilder.append(f.outWidth);
    localStringBuilder.append(", bm height = ");
    localStringBuilder.append(f.outHeight);
    localStringBuilder.append(" m_opt.outMimeType = ");
    localStringBuilder.append(f.outMimeType);
    f.a(str, localStringBuilder.toString());
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    str = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("alwaysMaxRes = ");
    localStringBuilder.append(bool);
    f.a(str, localStringBuilder.toString());
    if (bool)
    {
      i = 1;
      a(h);
      return;
    }
    this.Y = false;
    c(0);
    v = true;
  }
  
  private void Q()
  {
    TouchRetouchLib.setUndoPath(f.a().getAbsolutePath());
  }
  
  private void R()
  {
    findViewById(2131296259).setVisibility(0);
  }
  
  private void S()
  {
    View localView = findViewById(2131296257);
    if (localView.getVisibility() == 4)
    {
      localView.setVisibility(0);
      new Handler().postDelayed(new n(this), 300L);
    }
  }
  
  private Dialog T()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131689599));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099773));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131689594)).setPositiveButton(getString(2131689592), new e(this)).setNegativeButton(getString(2131689590), new d(this)).create();
  }
  
  private void U()
  {
    findViewById(2131296268).setVisibility(0);
    findViewById(2131296262).setVisibility(0);
    findViewById(2131296261).setVisibility(0);
  }
  
  private void V()
  {
    Object localObject1 = Environment.getExternalStorageDirectory();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((File)localObject1).getAbsolutePath());
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(b.a);
    new File(((StringBuilder)localObject2).toString()).mkdirs();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(Environment.getExternalStorageDirectory());
    ((StringBuilder)localObject2).append(File.separator);
    ((StringBuilder)localObject2).append(a);
    Object localObject3 = ((StringBuilder)localObject2).toString();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("WipeOut");
    ((StringBuilder)localObject2).append(new SimpleDateFormat("mm_dd_yyyy_hhmmss.SSSSSS").format(new Date()));
    ((StringBuilder)localObject2).append(".jpg");
    localObject2 = ((StringBuilder)localObject2).toString();
    localObject3 = new File((String)localObject3, (String)localObject2);
    Object localObject4 = new StringBuilder();
    ((StringBuilder)localObject4).append("file://");
    ((StringBuilder)localObject4).append(((File)localObject1).getAbsolutePath());
    ((StringBuilder)localObject4).append("/");
    ((StringBuilder)localObject4).append(b.a);
    ((StringBuilder)localObject4).append("/");
    ((StringBuilder)localObject4).append((String)localObject2);
    localObject4 = ((StringBuilder)localObject4).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(((File)localObject1).getAbsolutePath());
    localStringBuilder.append("/");
    localStringBuilder.append(b.a);
    localStringBuilder.append("/");
    localStringBuilder.append((String)localObject2);
    b.b = localStringBuilder.toString();
    try
    {
      localObject1 = new FileOutputStream((File)localObject3);
      ((FileOutputStream)localObject1).flush();
      ((FileOutputStream)localObject1).close();
      sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File((String)localObject4))));
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    this.M = ((String)localObject2);
    if (((File)localObject3).exists()) {
      showDialog(505);
    }
    ad();
    a(new a(this, 5));
  }
  
  private Dialog W()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131689597));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099773));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131689598)).setPositiveButton(getString(2131689592), new i(this)).create();
  }
  
  private Dialog X()
  {
    return new AlertDialog.Builder(this).setTitle("Send email to developers").setMessage("We found a problem! Please press ok and send the mail we will compose").setPositiveButton(getString(2131689593), new p(this)).setNegativeButton(getString(2131689591), new o(this)).create();
  }
  
  private void Y()
  {
    Object localObject = (ActivityManager)getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)localObject).getMemoryInfo(localMemoryInfo);
    localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" memoryInfo.availMem ");
    localStringBuilder.append(localMemoryInfo.availMem);
    localStringBuilder.append("\n");
    f.a((String)localObject, localStringBuilder.toString());
  }
  
  private Dialog Z()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131689603);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099678));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131689606)).setPositiveButton(getString(2131689593), new g(this)).setNegativeButton(getString(2131689591), new f(this)).create();
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
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("trying to decode bitmap file ");
    localStringBuilder.append(paramOptions.inSampleSize);
    f.a((String)localObject, localStringBuilder.toString());
    try
    {
      localObject = BitmapFactory.decodeFile(paramString, paramOptions);
      return localObject;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;) {}
    }
    f.a(a, "Out of memory error :(");
    paramOptions.inSampleSize *= 2;
    this.p = true;
    localObject = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Set it twice less with opt.inSampleSize ");
    localStringBuilder.append(paramOptions.inSampleSize);
    f.a((String)localObject, localStringBuilder.toString());
    return a(paramString, paramOptions);
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
  
  private void a(ImageView paramImageView, int paramInt)
  {
    paramImageView.setBackgroundResource(paramInt);
    b(paramImageView, getResources().getColor(2131099691));
  }
  
  private void a(String paramString, int paramInt)
  {
    if (TextUtils.isEmpty(paramString))
    {
      Toast.makeText(this, getString(2131689557), 0).show();
      onBackPressed();
      return;
    }
    c(false);
    this.N = b(paramString);
    ad();
    f.a(a, "processImagePath");
    h = paramString;
    g = paramInt;
    this.M = new File(paramString).getName();
    Y();
    if (f == null) {
      f = new BitmapFactory.Options();
    }
    f.inPreferredConfig = Bitmap.Config.ARGB_8888;
    f.inSampleSize = 1;
    this.p = false;
    f.a(a, "init config values");
    f.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, f);
    paramString = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("mime type = ");
    localStringBuilder.append(f.outMimeType);
    f.a(paramString, localStringBuilder.toString());
    if (f.outMimeType == null)
    {
      Toast.makeText(this, getString(2131689597), 1).show();
      setResult(0);
      finish();
      return;
    }
    if (f.outMimeType.equalsIgnoreCase("image/jpeg"))
    {
      f.a(a, "Processing jpeg image!");
      j = 401;
      P();
      return;
    }
    j = 402;
    f.inJustDecodeBounds = false;
    this.K = a(h, f);
    paramString = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("opt.inSampleSize = ");
    localStringBuilder.append(f.inSampleSize);
    f.a(paramString, localStringBuilder.toString());
    c = f.outWidth;
    b = f.outHeight;
    paramString = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("m_cur_image_width = ");
    localStringBuilder.append(c);
    localStringBuilder.append(", m_cur_image_height = ");
    localStringBuilder.append(b);
    f.a(paramString, localStringBuilder.toString());
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    paramString = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("alwaysMaxRes = ");
    localStringBuilder.append(bool);
    f.a(paramString, localStringBuilder.toString());
    if (bool)
    {
      a(this.K);
    }
    else
    {
      this.Y = false;
      c(0);
      v = true;
    }
    Y();
  }
  
  private Dialog aa()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131689603);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099678));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131689606)).setPositiveButton(getString(2131689593), new c(this)).setNegativeButton(getString(2131689591), new b(this)).create();
  }
  
  private Dialog ab()
  {
    int i1 = a(g);
    TextView localTextView = new TextView(this);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getString(2131689583));
    localStringBuilder.append(" (");
    int i2 = i1 % 2;
    if (i2 == 1) {
      i1 = b;
    } else {
      i1 = c;
    }
    localStringBuilder.append(i1);
    localStringBuilder.append("x");
    if (i2 == 1) {
      i1 = c;
    } else {
      i1 = b;
    }
    localStringBuilder.append(i1);
    localStringBuilder.append(")");
    localTextView.setText(localStringBuilder.toString());
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099773));
    localTextView.setTextSize(20.0F);
    return new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2130903043, new h(this)).create();
  }
  
  private void ac()
  {
    V();
    k();
  }
  
  private void ad()
  {
    findViewById(2131296461).setVisibility(0);
    findViewById(2131296460).setVisibility(0);
    this.Q = true;
    this.R = true;
    t();
  }
  
  private void ae()
  {
    runOnUiThread(this.B);
  }
  
  private void af()
  {
    Object localObject1 = getPackageManager();
    List localList = ((PackageManager)localObject1).getInstalledPackages(1);
    int i1 = 0;
    localObject1 = ((PackageManager)localObject1).getInstalledApplications(0);
    while (i1 < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i1);
      localObject2 = ((ApplicationInfo)((List)localObject1).get(i1)).publicSourceDir;
      if (((String)localObject2).contains("com.advasoft.touchretouch"))
      {
        this.O = ((String)localObject2);
        y();
      }
      i1 += 1;
    }
  }
  
  private void ag()
  {
    Intent localIntent = new Intent();
    localIntent.setType("image/*");
    localIntent.addCategory("android.intent.category.OPENABLE");
    if (Build.VERSION.SDK_INT >= 19)
    {
      localIntent.setAction("android.intent.action.OPEN_DOCUMENT");
      if (eraser.touch.photo.vn.touch.a.a.a().d(this)) {
        eraser.touch.photo.vn.touch.a.a.a().a(this, localIntent);
      }
    }
    else
    {
      localIntent.setAction("android.intent.action.GET_CONTENT");
      startActivityForResult(Intent.createChooser(localIntent, getString(2131689578)), 101);
    }
  }
  
  private ExifInterface b(String paramString)
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
    f.a(a, "exif exception");
    return null;
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
    if (Build.VERSION.SDK_INT >= 21) {
      return;
    }
    android.support.v4.a.a.a.f(paramImageView.getBackground());
  }
  
  private static void b(String paramString, EGL10 paramEGL10)
  {
    while (paramEGL10.eglGetError() != 12288) {}
  }
  
  private void c(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "MOVE");
    localEditor.apply();
    int i1 = getResources().getColor(2131099691);
    int i2 = getResources().getColor(2131099773);
    b((ImageView)findViewById(2131296403), i2);
    b((ImageView)findViewById(2131296397), i2);
    b((ImageView)findViewById(2131296398), i2);
    b((ImageView)findViewById(2131296406), i1);
    b((ImageView)findViewById(2131296402), i2);
    b((ImageView)findViewById(2131296404), i2);
    b((ImageView)findViewById(2131296413), i2);
    b((ImageView)findViewById(2131296408), i2);
    b((ImageView)findViewById(2131296410), i2);
    b((ImageView)findViewById(2131296399), i2);
    b((ImageView)findViewById(2131296400), i2);
    b((ImageView)findViewById(2131296401), i2);
    ((TextView)findViewById(2131296539)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296542)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296541)).setTextColor(getResources().getColor(2131099773));
    b((ImageView)findViewById(2131296409), i2);
    ((TextView)findViewById(2131296549)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296544)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296538)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296547)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296540)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296546)).setTextColor(getResources().getColor(2131099691));
    ((TextView)findViewById(2131296545)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296551)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296548)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296543)).setTextColor(getResources().getColor(2131099773));
    ((TextView)findViewById(2131296550)).setTextColor(getResources().getColor(2131099773));
    g(301);
    g(300);
    g(302);
    this.W = 2131296276;
    TouchRetouchLib.SetEditTool(3, w());
  }
  
  private int d(int paramInt)
  {
    return 0;
  }
  
  private void d(boolean paramBoolean)
  {
    g(302);
    g(301);
    g(300);
    ac();
  }
  
  private View e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 302: 
      return findViewById(2131296313);
    case 301: 
      return findViewById(2131296342);
    }
    return findViewById(2131296375);
  }
  
  private void e(boolean paramBoolean)
  {
    TouchRetouchLib.ZoomPicture(paramBoolean);
    h();
    a(TouchRetouchLib.GetZoom());
    g();
  }
  
  private String f(int paramInt)
  {
    if (paramInt >= 5120) {
      paramInt = 2131689562;
    }
    for (;;)
    {
      return getString(paramInt);
      if (paramInt >= 2560)
      {
        paramInt = 2131689561;
      }
      else if (paramInt >= 1280)
      {
        paramInt = 2131689564;
      }
      else
      {
        if (paramInt < 640) {
          break;
        }
        paramInt = 2131689563;
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
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Flip ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" selected");
    f.a(str, localStringBuilder.toString());
    int i1 = paramInt - 2131296270;
    g(300);
    this.V = paramInt;
    b(2131296400, paramInt);
    TouchRetouchLib.SetCloneStampType(this.U - 2131296263, i1);
    str = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("flip mode i = ");
    localStringBuilder.append(i1);
    localStringBuilder.append(" selected");
    f.a(str, localStringBuilder.toString());
  }
  
  private void j(int paramInt)
  {
    int i1 = paramInt - 2131296263;
    g(301);
    this.U = paramInt;
    b(2131296401, paramInt);
    TouchRetouchLib.SetCloneStampType(i1, this.V - 2131296270);
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("clone mode i = ");
    localStringBuilder.append(i1);
    localStringBuilder.append(" selected");
    f.a(str, localStringBuilder.toString());
  }
  
  private void k(int paramInt)
  {
    ((ImageView)findViewById(paramInt)).setEnabled(false);
  }
  
  private void l(int paramInt)
  {
    ((ImageView)findViewById(paramInt)).setEnabled(true);
  }
  
  private void m(int paramInt)
  {
    ((RelativeLayout)findViewById(paramInt)).setEnabled(false);
  }
  
  private void n(int paramInt)
  {
    ((RelativeLayout)findViewById(paramInt)).setEnabled(true);
  }
  
  private void o(int paramInt)
  {
    View localView = e(paramInt);
    if (localView != null) {
      localView.setVisibility(0);
    }
  }
  
  private void t()
  {
    m(2131296277);
    m(2131296329);
    m(2131296282);
    m(2131296279);
    m(2131296275);
    m(2131296258);
    m(2131296278);
    m(2131296276);
    m(2131296269);
    m(2131296274);
    m(2131296260);
    m(2131296280);
    k(2131296259);
    m(2131296268);
    m(2131296262);
    m(2131296261);
    k(2131296263);
    k(2131296264);
    k(2131296265);
    k(2131296266);
    k(2131296267);
    k(2131296270);
    k(2131296271);
    k(2131296272);
    k(2131296273);
  }
  
  private void u()
  {
    n(2131296277);
    n(2131296329);
    n(2131296282);
    n(2131296279);
    n(2131296275);
    n(2131296258);
    n(2131296278);
    n(2131296276);
    n(2131296269);
    n(2131296274);
    n(2131296260);
    n(2131296280);
    l(2131296259);
    n(2131296268);
    n(2131296262);
    n(2131296261);
    n(2131296343);
    n(2131296344);
    n(2131296345);
    n(2131296346);
    n(2131296347);
    n(2131296376);
    n(2131296377);
    n(2131296378);
    n(2131296379);
  }
  
  private int v()
  {
    return findViewById(2131296326).getHeight() + findViewById(2131296313).getHeight();
  }
  
  private int w()
  {
    return ((SeekBar)findViewById(2131296489)).getProgress();
  }
  
  private CharSequence[] x()
  {
    int i5;
    if (c > b) {
      i5 = c;
    } else {
      i5 = b;
    }
    int i1 = a(g);
    int i3 = 2;
    i1 %= 2;
    int i4 = 1;
    int i6;
    if (i1 == 1) {
      i6 = b;
    } else {
      i6 = c;
    }
    int i7;
    if (i1 == 1) {
      i7 = c;
    } else {
      i7 = b;
    }
    Object localObject = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bigSide = ");
    localStringBuilder.append(i5);
    f.a((String)localObject, localStringBuilder.toString());
    localObject = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("m_cur_image_width = ");
    localStringBuilder.append(c);
    localStringBuilder.append(" m_cur_image_height = ");
    localStringBuilder.append(b);
    f.a((String)localObject, localStringBuilder.toString());
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
    localObject = a;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("number of proposals = ");
    localStringBuilder.append(i2);
    f.a((String)localObject, localStringBuilder.toString());
    localObject = new CharSequence[i2];
    if (this.p)
    {
      i3 = 1;
      i4 = 0;
    }
    else
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(getString(2131689565));
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
      if (this.p)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(getString(2131689562));
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
  
  private void y()
  {
    showDialog(606);
  }
  
  private void z()
  {
    findViewById(2131296259).setVisibility(8);
    findViewById(2131296268).setVisibility(8);
    findViewById(2131296262).setVisibility(8);
    findViewById(2131296261).setVisibility(8);
  }
  
  public void ObjectClone(View paramView)
  {
    findViewById(2131296268).performClick();
    findViewById(2131296274).setVisibility(8);
    findViewById(2131296429).setVisibility(8);
    findViewById(2131296523).setVisibility(0);
    findViewById(2131296260).setVisibility(8);
    findViewById(2131296278).setVisibility(8);
    findViewById(2131296258).setVisibility(8);
    findViewById(2131296275).setVisibility(8);
    findViewById(2131296269).setVisibility(8);
    findViewById(2131296268).setVisibility(0);
    findViewById(2131296262).setVisibility(0);
    findViewById(2131296261).setVisibility(0);
    findViewById(2131296281).setVisibility(0);
    paramView = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131296523).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "CLONE_STAMP");
    paramView.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    paramView.apply();
  }
  
  public void OnBack(View paramView)
  {
    ad();
    a(new a(this, 4));
    g(301);
    g(300);
    g(302);
    findViewById(2131296429).setVisibility(0);
    findViewById(2131296523).setVisibility(8);
    findViewById(2131296260).setVisibility(8);
    findViewById(2131296278).setVisibility(8);
    findViewById(2131296258).setVisibility(8);
    findViewById(2131296275).setVisibility(8);
    findViewById(2131296269).setVisibility(8);
    findViewById(2131296281).setVisibility(8);
    findViewById(2131296274).setVisibility(8);
    findViewById(2131296276).performClick();
    paramView = new TranslateAnimation(1, -1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131296429).startAnimation(paramView);
  }
  
  public void QuickRepair(View paramView)
  {
    findViewById(2131296278).performClick();
    findViewById(2131296274).setVisibility(8);
    findViewById(2131296429).setVisibility(8);
    findViewById(2131296523).setVisibility(0);
    findViewById(2131296260).setVisibility(8);
    findViewById(2131296278).setVisibility(0);
    findViewById(2131296258).setVisibility(8);
    findViewById(2131296275).setVisibility(8);
    findViewById(2131296269).setVisibility(8);
    findViewById(2131296281).setVisibility(0);
    findViewById(2131296268).setVisibility(8);
    findViewById(2131296262).setVisibility(8);
    findViewById(2131296261).setVisibility(8);
    paramView = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131296523).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "QUICK_REPAIR");
    paramView.putString("OBJECT_SELECT", "OBJECT_QUICK_BRUSH");
    paramView.apply();
  }
  
  public void Settings(View paramView)
  {
    paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
    if (paramView.equals("OBJECT_REMOVAL_BRUSH"))
    {
      g(301);
      g(300);
      if ((h(302)) && (x == 1))
      {
        g(302);
        return;
      }
      z();
      o(302);
      x = 1;
      return;
    }
    if (paramView.equals("OBJECT_QUICK_BRUSH"))
    {
      g(301);
      g(300);
      if ((h(302)) && (x == 1))
      {
        g(302);
        return;
      }
      z();
      o(302);
      x = 1;
      return;
    }
    if (paramView.equals("OBJECT_CLONE_BRUSH"))
    {
      g(301);
      g(300);
      if ((h(302)) && (x == 1))
      {
        g(302);
        return;
      }
      o(302);
      x = 1;
      return;
    }
    if (paramView.equals("OBJECT_REMOVAL_ERASER"))
    {
      g(301);
      g(300);
      if ((h(302)) && (x == 2))
      {
        g(302);
      }
      else
      {
        z();
        o(302);
        x = 2;
      }
      R();
      return;
    }
    if (paramView.equals("OBJECT_CLONE_HARDNESS"))
    {
      g(300);
      g(302);
      if ((h(301)) && (x == 1))
      {
        g(301);
        return;
      }
      o(301);
      x = 1;
      return;
    }
    if (paramView.equals("OBJECT_CLONE_MIRRORING"))
    {
      if ((h(300)) && (x == 1))
      {
        g(300);
      }
      else
      {
        o(300);
        x = 1;
      }
      g(301);
      g(302);
    }
  }
  
  public a a()
  {
    try
    {
      a localA = (a)this.D.poll();
      return localA;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  protected void a(float paramFloat)
  {
    ImageView localImageView = (ImageView)findViewById(2131296564);
    Canvas localCanvas = new Canvas();
    f.a(a, "we really call drawZoomValue here!!!");
    f.a(a, "koef = 12.0");
    Bitmap localBitmap = Bitmap.createBitmap(144, 96, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(-1895825408);
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawRoundRect(new RectF(12.0F, 12.0F, 132.0F, 64.0F), 24.0F, 24.0F, localPaint);
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(-1);
    localPaint.setTextSize(33.0F);
    localPaint.setAntiAlias(true);
    localPaint.setTypeface(Typeface.defaultFromStyle(0));
    int i1 = (int)(paramFloat * 100.0F);
    StringBuilder localStringBuilder;
    if (i1 >= 100)
    {
      localStringBuilder = new StringBuilder(String.valueOf(i1));
      localStringBuilder.append("%");
      localCanvas.drawText(localStringBuilder.toString(), 33.0F, 48.0F, localPaint);
    }
    else
    {
      localStringBuilder = new StringBuilder(String.valueOf(i1));
      localStringBuilder.append("%");
      localCanvas.drawText(localStringBuilder.toString(), 42.0F, 48.0F, localPaint);
    }
    localImageView.setImageBitmap(localBitmap);
  }
  
  public void a(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {}
    try
    {
      if (this.H != null) {
        this.H.add(paramBitmap);
      }
    }
    finally {}
  }
  
  public void a(a paramA)
  {
    try
    {
      this.D.add(paramA);
      return;
    }
    finally
    {
      paramA = finally;
      throw paramA;
    }
  }
  
  public void a(s paramS)
  {
    try
    {
      this.I.add(paramS);
      return;
    }
    finally
    {
      paramS = finally;
      throw paramS;
    }
  }
  
  public void a(Integer paramInteger)
  {
    try
    {
      this.E.add(paramInteger);
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
    try
    {
      if (this.G != null) {
        this.G.add(paramString);
      }
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void a(boolean paramBoolean)
  {
    View localView1 = findViewById(2131296563);
    View localView2 = findViewById(2131296313);
    Object localObject = findViewById(2131296375);
    View localView3 = findViewById(2131296342);
    ((View)localObject).setVisibility(4);
    localView3.setVisibility(4);
    localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    ((Animation)localObject).setDuration(300L);
    if (this.W == 2131296260)
    {
      this.J = true;
      localView2.startAnimation((Animation)localObject);
    }
    if (!this.P)
    {
      this.C = new TranslateAnimation(1, 0.0F, 1, 1.0F, 1, 0.0F, 1, 0.0F);
      this.C.setDuration(300L);
      localView1.startAnimation(this.C);
      localView1.setVisibility(4);
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    ((RelativeLayout)findViewById(2131296282)).setEnabled(paramBoolean1);
    ((RelativeLayout)findViewById(2131296279)).setEnabled(paramBoolean2);
  }
  
  public Integer b()
  {
    try
    {
      Integer localInteger = (Integer)this.E.poll();
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void b(int paramInt)
  {
    ImageView localImageView = (ImageView)findViewById(2131296328);
    Canvas localCanvas = new Canvas();
    Bitmap localBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(getResources().getColor(2131099692));
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(getResources().getColor(2131099691));
    localPaint.setStyle(Paint.Style.FILL);
    double d1 = paramInt;
    Double.isNaN(d1);
    localCanvas.drawCircle(250.0F, 250.0F, (float)(d1 * 1.167D), localPaint);
    localImageView.setImageBitmap(localBitmap);
  }
  
  public void b(Integer paramInteger)
  {
    try
    {
      this.F.add(paramInteger);
      return;
    }
    finally
    {
      paramInteger = finally;
      throw paramInteger;
    }
  }
  
  public Integer c()
  {
    try
    {
      Integer localInteger = (Integer)this.F.poll();
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
      ad();
      if (j == 402)
      {
        localOptions = f;
        localOptions.inSampleSize *= 8;
        this.K.recycle();
        this.K = a(h, f);
        a(this.K);
        return;
      }
      if (j == 401)
      {
        i = 8;
        a(h);
        return;
      }
      return;
    case 2: 
      ad();
      if (j == 402)
      {
        localOptions = f;
        localOptions.inSampleSize *= 4;
        this.K.recycle();
        this.K = a(h, f);
        a(this.K);
        return;
      }
      if (j == 401)
      {
        i = 4;
        a(h);
        return;
      }
      return;
    case 1: 
      ad();
      if (j == 402)
      {
        localOptions = f;
        localOptions.inSampleSize *= 2;
        this.K.recycle();
        this.K = a(h, f);
        a(this.K);
        return;
      }
      if (j == 401)
      {
        i = 2;
        a(h);
        return;
      }
      return;
    }
    ad();
    if (j == 402)
    {
      a(this.K);
      return;
    }
    if (j == 401)
    {
      i = 1;
      a(h);
      return;
    }
  }
  
  public String d()
  {
    try
    {
      String str = (String)this.G.poll();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Bitmap e()
  {
    try
    {
      Bitmap localBitmap = (Bitmap)this.H.poll();
      return localBitmap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public s f()
  {
    try
    {
      s localS = (s)this.I.poll();
      return localS;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void g()
  {
    this.Z = false;
    new Handler().postDelayed(new k(this), 300L);
  }
  
  public void h()
  {
    findViewById(2131296290).setVisibility(0);
    this.Z = true;
  }
  
  public void i()
  {
    Intent localIntent = new Intent();
    localIntent.setType("image/*");
    localIntent.addCategory("android.intent.category.OPENABLE");
    if (Build.VERSION.SDK_INT >= 19) {
      localIntent.setAction("android.intent.action.OPEN_DOCUMENT");
    } else {
      localIntent.setAction("android.intent.action.GET_CONTENT");
    }
    startActivityForResult(Intent.createChooser(localIntent, getString(2131689579)), 301);
  }
  
  public void j()
  {
    f.a(a, "SetLastEditedTool");
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("lastSelectedTool = ");
    localStringBuilder.append(this.W);
    f.a(str, localStringBuilder.toString());
    switch (this.W)
    {
    default: 
      return;
    case 2131296278: 
      TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131296489)).getProgress());
      return;
    case 2131296275: 
      TouchRetouchLib.SetEditTool(0, ((SeekBar)findViewById(2131296489)).getProgress());
      return;
    case 2131296269: 
      TouchRetouchLib.SetEditTool(2, ((SeekBar)findViewById(2131296489)).getProgress());
      return;
    case 2131296260: 
      TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131296489)).getProgress());
      TouchRetouchLib.SetCloneStampType(this.U, this.V);
      return;
    }
    TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131296489)).getProgress());
  }
  
  public void k()
  {
    this.Q = false;
  }
  
  public void l()
  {
    if (!this.X)
    {
      if (this.t == null) {
        this.t = new Timer();
      }
      this.t.schedule(new m(this), 0L, 200L);
      this.X = true;
    }
  }
  
  public void m()
  {
    if ((this.X) && (this.t != null))
    {
      this.t.cancel();
      this.t.purge();
      this.t = null;
      this.X = false;
    }
  }
  
  public void n()
  {
    Toast.makeText(this, getString(2131689601), 1).show();
    Intent localIntent = new Intent(this, ShareActivity.class);
    localIntent.putExtra("imageSaveLocation", b.b);
    startActivity(localIntent);
  }
  
  protected void o()
  {
    this.s = ((ImageView)findViewById(2131296559));
    this.L = new BitmapFactory.Options();
    this.L.inPreferredConfig = Bitmap.Config.ARGB_4444;
    y = System.currentTimeMillis();
    b(2131296277, true);
    b(2131296329, true);
    b(2131296282, true);
    b(2131296279, true);
    b(2131296275, true);
    b(2131296258, true);
    b(2131296278, true);
    b(2131296276, true);
    b(2131296269, true);
    b(2131296274, true);
    b(2131296260, true);
    b(2131296280, true);
    a(2131296259, false);
    b(2131296268, false);
    b(2131296262, false);
    b(2131296261, false);
    b(2131296343, true);
    b(2131296344, true);
    b(2131296345, true);
    b(2131296346, true);
    b(2131296347, true);
    b(2131296376, true);
    b(2131296377, true);
    b(2131296378, true);
    b(2131296379, true);
    a(2131296284, true);
    a(2131296283, true);
    g(302);
    g(300);
    g(301);
    this.U = 2131296263;
    this.V = 2131296270;
    this.W = 2131296276;
    Object localObject = (SeekBar)findViewById(2131296489);
    ((SeekBar)localObject).setOnSeekBarChangeListener(this);
    b(((SeekBar)localObject).getProgress());
    this.o = ((GLSurfaceView)findViewById(2131296385));
    this.o.getHolder().setFormat(-3);
    this.o.setEGLContextFactory(new r(null));
    this.o.setEGLConfigChooser(new q(8, 8, 8, 8, 0, 0));
    this.o.setRenderer(new t(this));
    this.o.setRenderMode(1);
    this.o.setOnTouchListener(this);
    localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    int i1 = getWindowManager().getDefaultDisplay().getOrientation();
    String str = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("metrics.widthPixels = ");
    localStringBuilder.append(((DisplayMetrics)localObject).widthPixels);
    localStringBuilder.append("metrics.heightPixels = ");
    localStringBuilder.append(((DisplayMetrics)localObject).heightPixels);
    f.a(str, localStringBuilder.toString());
    switch (i1)
    {
    default: 
      break;
    case 1: 
    case 3: 
      A = ((DisplayMetrics)localObject).heightPixels;
      w = ((DisplayMetrics)localObject).widthPixels;
      break;
    case 0: 
    case 2: 
      A = ((DisplayMetrics)localObject).widthPixels;
      w = ((DisplayMetrics)localObject).heightPixels;
    }
    i1 = TouchRetouchLib.init(A, w, i1, Build.VERSION.SDK_INT);
    if (i1 != 0) {
      switch (i1)
      {
      default: 
        break;
      case 2: 
        m = 1;
        af();
        break;
      case 1: 
        f.a(a, "app is cracked!");
        m = 1;
      }
    }
    Q();
    this.I = new LinkedList();
    this.H = new LinkedList();
    this.G = new LinkedList();
    this.D = new LinkedList();
    this.E = new LinkedList();
    this.F = new LinkedList();
    f.a(a, "lined lists initialized");
    this.P = getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
    if (!this.P) {
      findViewById(2131296563).setVisibility(0);
    }
    f.a(a, "checked multitouch");
    f.a(a, "init controls finished");
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      k();
      return;
    case 101: 
      if (paramInt2 == -1)
      {
        m();
        String str = eraser.touch.photo.vn.touch.a.a.a().a(this, paramIntent.getData());
        paramInt2 = eraser.touch.photo.vn.touch.a.a.a().b(this, paramIntent.getData());
        paramInt1 = paramInt2;
        if (paramInt2 == 0) {
          paramInt1 = eraser.touch.photo.vn.touch.a.a.a().a(str);
        }
        a(str, paramInt1);
        l();
        D();
        return;
      }
      k();
      return;
    }
    if (paramInt2 == -1)
    {
      m();
      paramIntent = eraser.touch.photo.vn.touch.a.a.a().c();
      a(paramIntent, eraser.touch.photo.vn.touch.a.a.a().a(paramIntent));
      l();
      D();
      return;
    }
    k();
  }
  
  public void onBackPressed()
  {
    if (this.Q)
    {
      Toast.makeText(this, getString(2131689502), 1).show();
      startActivity(new Intent(this, StartActivity.class));
      return;
    }
    f.a(a, "stopping timer");
    m();
    v = false;
    setResult(0);
    c.a().a(new c.a()
    {
      public void a()
      {
        TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, StartActivity.class));
      }
    });
  }
  
  public void onClick(View paramView)
  {
    if (paramView == findViewById(2131296277))
    {
      c.a().a(new c.a()
      {
        public void a()
        {
          TouchRetouchActivity.j(TouchRetouchActivity.this);
        }
      });
      return;
    }
    if (paramView == findViewById(2131296329))
    {
      if (eraser.touch.photo.vn.touch.a.a.a().b(this)) {
        c.a().a(new c.a()
        {
          public void a()
          {
            eraser.touch.photo.vn.touch.a.a.a().a(TouchRetouchActivity.this);
          }
        });
      }
    }
    else
    {
      if (paramView == findViewById(2131296282))
      {
        O();
        return;
      }
      if (paramView == findViewById(2131296279))
      {
        N();
        return;
      }
      if (paramView == findViewById(2131296275))
      {
        L();
        return;
      }
      if (paramView == findViewById(2131296258))
      {
        D();
        return;
      }
      if (paramView == findViewById(2131296278))
      {
        M();
        return;
      }
      if (paramView == findViewById(2131296276))
      {
        c(true);
        return;
      }
      if (paramView == findViewById(2131296269))
      {
        J();
        return;
      }
      if (paramView == findViewById(2131296274))
      {
        K();
        return;
      }
      if (paramView == findViewById(2131296260))
      {
        G();
        return;
      }
      if (paramView == findViewById(2131296280))
      {
        paramView = new AlertDialog.Builder(this).create();
        paramView.setTitle(getString(2131689527));
        paramView.setMessage(getString(2131689576));
        paramView.setButton(-1, getString(2131689627), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.dismiss();
            TouchRetouchActivity.a(TouchRetouchActivity.this, true);
          }
        });
        paramView.setButton(-2, getString(2131689549), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            paramAnonymousDialogInterface.dismiss();
          }
        });
        paramView.show();
        return;
      }
      if (paramView == findViewById(2131296259))
      {
        I();
        return;
      }
      if (paramView == findViewById(2131296268))
      {
        H();
        return;
      }
      if (paramView == findViewById(2131296262))
      {
        F();
        return;
      }
      if (paramView == findViewById(2131296261))
      {
        E();
        return;
      }
      if (paramView == findViewById(2131296343))
      {
        j(2131296263);
        return;
      }
      if (paramView == findViewById(2131296344))
      {
        j(2131296264);
        return;
      }
      if (paramView == findViewById(2131296345))
      {
        j(2131296265);
        return;
      }
      if (paramView == findViewById(2131296346))
      {
        j(2131296266);
        return;
      }
      if (paramView == findViewById(2131296347))
      {
        j(2131296267);
        return;
      }
      if (paramView == findViewById(2131296376))
      {
        i(2131296270);
        return;
      }
      if (paramView == findViewById(2131296377))
      {
        i(2131296271);
        return;
      }
      if (paramView == findViewById(2131296378))
      {
        i(2131296272);
        return;
      }
      if (paramView == findViewById(2131296379))
      {
        i(2131296273);
        return;
      }
      if (paramView == findViewById(2131296284))
      {
        e(true);
        return;
      }
      if (paramView == findViewById(2131296283)) {
        e(false);
      }
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    f.a(a, "OnConfig changed!!!");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427360);
    f.a(a, "onCreate");
    o();
    this.s.setOnClickListener(new l(this));
    findViewById(2131296274).setVisibility(0);
    findViewById(2131296429).setVisibility(8);
    findViewById(2131296523).setVisibility(0);
    findViewById(2131296260).setVisibility(8);
    findViewById(2131296258).setVisibility(0);
    findViewById(2131296278).setVisibility(8);
    findViewById(2131296275).setVisibility(0);
    findViewById(2131296269).setVisibility(0);
    findViewById(2131296281).setVisibility(0);
    findViewById(2131296268).setVisibility(8);
    findViewById(2131296262).setVisibility(8);
    findViewById(2131296261).setVisibility(8);
    paramBundle = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramBundle.setDuration(300L);
    findViewById(2131296523).startAnimation(paramBundle);
    paramBundle = getSharedPreferences("WIPE_OUT", 0).edit();
    paramBundle.putString("PANEL_VISIBLE", "OBJECT_REMOVAL");
    paramBundle.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
    paramBundle.apply();
    this.n = ((RelativeLayout)findViewById(2131296274));
    d = IntBuffer.allocate(262144);
    paramBundle = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("extra uri = ");
    localStringBuilder.append(getIntent().getDataString());
    f.a(paramBundle, localStringBuilder.toString());
    p();
    D();
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 606: 
      return X();
    case 605: 
      return T();
    case 603: 
      return Z();
    case 602: 
      return aa();
    case 506: 
      return ab();
    case 504: 
      return W();
    case 502: 
      return A();
    }
    return B();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  protected void onDestroy()
  {
    f.a("touchetouch", "OnDestroy");
    TouchRetouchLib.destroyResources();
    f.a(a, "super.onDestroy()");
    System.gc();
    System.exit(0);
    super.onDestroy();
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.o != null) {
      this.o.onPause();
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
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if ((paramInt == 104) && (paramArrayOfInt.length > 1) && (paramArrayOfInt[0] == 0) && (paramArrayOfInt[1] == 0))
    {
      eraser.touch.photo.vn.touch.a.a.a().a(this);
      return;
    }
    if ((paramInt == 105) && (paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0))
    {
      paramArrayOfString = eraser.touch.photo.vn.touch.a.a.a().b();
      eraser.touch.photo.vn.touch.a.a.a().a(this, paramArrayOfString);
    }
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.o != null) {
      this.o.onResume();
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    f.a("touchretouch1", "Before check that is needed device");
    this.S = false;
    SeekBar localSeekBar = (SeekBar)findViewById(2131296489);
    if (this.S)
    {
      localSeekBar.setVisibility(8);
      return;
    }
    localSeekBar.setVisibility(0);
  }
  
  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.W != 2131296260) && (this.W != 2131296276)) {
      findViewById(2131296257).setVisibility(0);
    }
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.W != 2131296260) && (this.W != 2131296276))
    {
      findViewById(2131296257).setVisibility(8);
      S();
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (!this.Q)
    {
      paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
      if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 2) || (paramMotionEvent.getAction() == 1) || (paramMotionEvent.getAction() == 3) || (paramMotionEvent.getAction() == 261) || (paramMotionEvent.getAction() == 262) || (paramMotionEvent.getAction() == 6) || (paramMotionEvent.getAction() == 5))
      {
        int i2 = paramMotionEvent.getPointerCount();
        if (this.T != i2)
        {
          if (i2 == 2) {
            h();
          }
          if (i2 == 1) {
            g();
          }
          this.T = i2;
        }
        int i1 = 0;
        while (i1 < i2)
        {
          a(new s(this, paramMotionEvent.getPointerId(i1), paramMotionEvent.getAction(), (int)paramMotionEvent.getX(i1), (int)paramMotionEvent.getY(i1)));
          if ((paramMotionEvent.getAction() == 0) && (this.S)) {
            TouchRetouchLib.SetBrushSize(140);
          }
          if ((2 == paramMotionEvent.getAction()) && (h(302)) && ((int)paramMotionEvent.getY(i1) > w - v() - 15)) {
            g(302);
          }
          int i3 = paramMotionEvent.getAction();
          if (i3 != 1)
          {
            if (i3 != 6)
            {
              if (i3 == 262) {
                break label313;
              }
              break label348;
            }
          }
          else
          {
            i3 = findViewById(2131296290).getVisibility();
            if ((paramView != null) && (paramView.equals("OBJECT_QUICK_BRUSH")) && (i3 == 4))
            {
              K();
              break label348;
            }
          }
          label313:
          if (System.currentTimeMillis() - z < 500L)
          {
            h();
            a(TouchRetouchLib.GetZoom());
            g();
          }
          z = System.currentTimeMillis();
          label348:
          i1 += 1;
        }
      }
    }
    return true;
  }
  
  protected void p()
  {
    a(getIntent().getStringExtra("path"), getIntent().getIntExtra("orient", 0));
    l();
  }
  
  protected void q()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setFlags(268435456);
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "touchretouch@handyphotolab.com" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "can't found apk!");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Path to apk is ");
    localStringBuilder.append(this.O);
    localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
    startActivity(Intent.createChooser(localIntent, getString(2131689596)));
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
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt) {}
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
      paramDialogInterface = this.a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("http://market.android.com/details?id=");
      localStringBuilder.append(this.a.getPackageName());
      paramDialogInterface.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
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
      this.a.m();
      TouchRetouchActivity.b(false);
      if (this.a.r)
      {
        this.a.r = false;
        this.a.setResult(1);
        this.a.startActivity(new Intent(this.a, StartActivity.class));
        this.a.finish();
        return;
      }
      this.a.setResult(0);
      this.a.finish();
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
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.a.r = false;
      TouchRetouchActivity.a(this.a, false);
    }
  }
  
  class h
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    h(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return;
      case 2: 
        TouchRetouchActivity.b(this.a);
        this.a.a(new TouchRetouchActivity.a(TouchRetouchActivity.this, this.a, 6));
        return;
      case 1: 
        TouchRetouchActivity.b(this.a);
        this.a.a(new TouchRetouchActivity.a(TouchRetouchActivity.this, this.a, 6));
        return;
      }
      TouchRetouchActivity.a(this.a);
    }
  }
  
  class i
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    i(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt) {}
  }
  
  class j
    implements Runnable
  {
    final TouchRetouchActivity a;
    private int c;
    
    j(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      if (!TouchRetouchActivity.c(this.a))
      {
        if (this.a.q)
        {
          float f = TouchRetouchLib.GetZoom();
          if (TouchRetouchActivity.e != f)
          {
            TouchRetouchActivity.e = f;
            this.a.a(f);
          }
        }
        boolean bool1 = TouchRetouchActivity.c(this.a);
        boolean bool3 = false;
        if ((!bool1) && (TouchRetouchActivity.d(this.a)))
        {
          TouchRetouchActivity.e(this.a);
          this.a.findViewById(2131296460).setVisibility(4);
          this.a.findViewById(2131296461).setVisibility(4);
          TouchRetouchActivity.b(this.a, false);
        }
        if (this.a.q)
        {
          localObject = this.a;
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
          ((TouchRetouchActivity)localObject).a(bool1, bool2);
        }
        bool1 = PreferenceManager.getDefaultSharedPreferences(this.a).getBoolean("pref_check_hide_toolbars", true);
        if ((this.a.q) && (bool1))
        {
          int i = TouchRetouchLib.getPanelsVisible();
          if (i != this.c)
          {
            localObject = this.a;
            bool1 = bool3;
            if (i != 0) {
              bool1 = true;
            }
            ((TouchRetouchActivity)localObject).a(bool1);
          }
          this.c = i;
        }
        for (Object localObject = this.a.b(); localObject != null; localObject = this.a.b()) {
          c.a().a(new c.a()
          {
            public void a()
            {
              TouchRetouchActivity.this.n();
            }
          });
        }
        for (localObject = this.a.c(); localObject != null; localObject = this.a.c()) {
          this.a.showDialog(605);
        }
      }
    }
  }
  
  class k
    implements Runnable
  {
    final TouchRetouchActivity a;
    
    k(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      View localView = this.a.findViewById(2131296290);
      if (localView.getVisibility() == 0) {
        localView.setVisibility(4);
      }
    }
  }
  
  class l
    implements View.OnClickListener
  {
    final TouchRetouchActivity a;
    
    l(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(View paramView) {}
  }
  
  class m
    extends TimerTask
  {
    final TouchRetouchActivity a;
    
    m(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      TouchRetouchActivity.f(this.a);
    }
  }
  
  class n
    implements Runnable
  {
    final TouchRetouchActivity a;
    
    n(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void run()
    {
      View localView = this.a.findViewById(2131296257);
      if (localView.getVisibility() == 0) {
        localView.setVisibility(4);
      }
    }
  }
  
  class o
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    o(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt) {}
  }
  
  class p
    implements DialogInterface.OnClickListener
  {
    final TouchRetouchActivity a;
    
    p(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      this.a.q();
    }
  }
  
  private static class q
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
    
    q(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
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
  
  private static class r
    implements GLSurfaceView.EGLContextFactory
  {
    private static int a = 12440;
    
    private r() {}
    
    public EGLContext createContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      f.a(TouchRetouchActivity.r(), "creating OpenGL ES 2.0 context");
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
  
  private class s
  {
    int a;
    public int b;
    int c;
    final TouchRetouchActivity d;
    public int e;
    
    s(TouchRetouchActivity paramTouchRetouchActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.d = paramTouchRetouchActivity;
      this.e = paramInt1;
      this.b = paramInt2;
      this.a = paramInt3;
      this.c = paramInt4;
    }
  }
  
  private static class t
    implements GLSurfaceView.Renderer
  {
    private TouchRetouchActivity a;
    private int b;
    private int c;
    
    t(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.a = paramTouchRetouchActivity;
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
      paramGL10 = this.a.d();
      if (paramGL10 != null)
      {
        String str = TouchRetouchActivity.a;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("orient = ");
        localStringBuilder.append(TouchRetouchActivity.g);
        f.a(str, localStringBuilder.toString());
        str = TouchRetouchActivity.a;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("scale = ");
        localStringBuilder.append(TouchRetouchActivity.i);
        f.a(str, localStringBuilder.toString());
        TouchRetouchActivity.c = TouchRetouchActivity.a(TouchRetouchActivity.c, TouchRetouchActivity.i);
        TouchRetouchActivity.b = TouchRetouchActivity.a(TouchRetouchActivity.b, TouchRetouchActivity.i);
        int i = TouchRetouchActivity.a(TouchRetouchActivity.g);
        f.a(TouchRetouchActivity.a, "GetOrientation");
        TouchRetouchLib.splitImage3(paramGL10, TouchRetouchActivity.i, i);
        this.a.k();
        this.a.q = true;
      }
    }
    
    private void b(GL10 paramGL10)
    {
      Bitmap localBitmap = this.a.e();
      if (localBitmap != null)
      {
        a(paramGL10, localBitmap, 512);
        localBitmap.recycle();
        this.a.k();
        this.a.q = true;
      }
    }
    
    private void c()
    {
      for (TouchRetouchActivity.a localA = this.a.a(); localA != null; localA = this.a.a()) {
        switch (localA.a)
        {
        default: 
          break;
        case 6: 
          this.a.k();
          break;
        case 5: 
          a();
          this.a.k();
          break;
        case 4: 
          TouchRetouchLib.OnClearAllSelected();
          this.a.k();
          break;
        case 3: 
          if (!TouchRetouchLib.OnGoSelected()) {
            this.a.b(new Integer(1));
          }
          this.a.k();
          break;
        case 2: 
          TouchRetouchLib.OnUndoSelected();
          this.a.k();
          break;
        case 1: 
          TouchRetouchLib.OnRedoSelected();
          this.a.k();
        }
      }
    }
    
    private void c(GL10 paramGL10)
    {
      Object localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inPreferredConfig = Bitmap.Config.ARGB_8888;
      Bitmap localBitmap = BitmapFactory.decodeResource(this.a.getResources(), 2131230816, (BitmapFactory.Options)localObject);
      IntBuffer localIntBuffer = IntBuffer.allocate(localBitmap.getWidth() * localBitmap.getHeight());
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int i = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.a.getResources(), 2131230817, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int j = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.a.getResources(), 2131230818, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int k = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.a.getResources(), 2131230819, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int m = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.a.getResources(), 2131230820, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int n = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localObject = BitmapFactory.decodeResource(this.a.getResources(), 2131230821, (BitmapFactory.Options)localObject);
      ((Bitmap)localObject).getPixels(localIntBuffer.array(), 0, ((Bitmap)localObject).getWidth(), 0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      int i1 = a(paramGL10, localIntBuffer, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      ((Bitmap)localObject).recycle();
      TouchRetouchLib.setCloneTextures(new int[] { i, j, k, m, n }, i1);
    }
    
    private void d()
    {
      Object localObject = this.a.f();
      int i = 0;
      while (localObject != null)
      {
        TouchRetouchActivity.s localS = this.a.f();
        i += 1;
        if ((i < 2) || (i % 15 == 0) || (((TouchRetouchActivity.s)localObject).b != 2) || (localS == null) || (localS.b != 2)) {
          TouchRetouchLib.nativeTouch(((TouchRetouchActivity.s)localObject).a, ((TouchRetouchActivity.s)localObject).c, ((TouchRetouchActivity.s)localObject).b, ((TouchRetouchActivity.s)localObject).e);
        }
        localObject = localS;
      }
    }
    
    private void e()
    {
      long l1 = System.currentTimeMillis();
      long l2 = TouchRetouchActivity.s();
      TouchRetouchActivity.a(l1);
      double d = l1 - l2;
      Double.isNaN(d);
      TouchRetouchLib.step((float)(d / 1000.0D));
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
      File localFile = new File(f.b(), TouchRetouchActivity.g(this.a));
      String str = TouchRetouchActivity.a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Saving to folder ");
      localStringBuilder.append(localFile.getAbsolutePath());
      f.a(str, localStringBuilder.toString());
      int j = TouchRetouchActivity.a(TouchRetouchActivity.g) % 2;
      int i;
      if (j == 1) {
        i = TouchRetouchActivity.b;
      } else {
        i = TouchRetouchActivity.c;
      }
      if (j == 1) {
        j = TouchRetouchActivity.c;
      } else {
        j = TouchRetouchActivity.b;
      }
      f.a(TouchRetouchActivity.a, "save image func");
      TouchRetouchLib.SaveImage(localFile.getAbsolutePath(), i, j);
      str = TouchRetouchActivity.r();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("SaveImage: ");
      localStringBuilder.append(TouchRetouchActivity.a);
      Log.e(str, localStringBuilder.toString());
      f.a(TouchRetouchActivity.a, "before save func");
      new a(this, this.a, localFile);
      this.a.a(new Integer(1));
      TouchRetouchLib.ImageSaved();
      this.a.k();
    }
    
    void a(GL10 paramGL10, Bitmap paramBitmap, int paramInt)
    {
      int i6 = paramBitmap.getWidth();
      int i7 = paramBitmap.getHeight();
      TouchRetouchActivity.c = i6;
      TouchRetouchActivity.b = i7;
      int n = paramInt - 1;
      if (i6 % n > 0) {
        i = i6 / (paramInt - 1) + 1;
      } else {
        i = i6 / (paramInt - 1);
      }
      TouchRetouchActivity.l = i;
      if (i7 % n > 0) {
        i = i7 / (paramInt - 1) + 1;
      } else {
        i = i7 / (paramInt - 1);
      }
      TouchRetouchActivity.k = i;
      int[] arrayOfInt = new int[TouchRetouchActivity.l * TouchRetouchActivity.k];
      IntBuffer localIntBuffer = TouchRetouchActivity.d;
      int m;
      String str;
      StringBuilder localStringBuilder;
      int k;
      int i1;
      int i2;
      int j;
      if (TouchRetouchActivity.g == 90)
      {
        f.a(TouchRetouchActivity.a, "Orientation is vertical!!!");
        i = TouchRetouchActivity.k;
        m = paramInt - 1;
        i = paramInt - (i * m - i7 + 1);
        str = TouchRetouchActivity.a;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("offset_x = ");
        localStringBuilder.append(paramInt - (TouchRetouchActivity.l * m - i6 + 1));
        localStringBuilder.append(", offset_y = ");
        localStringBuilder.append(i);
        f.a(str, localStringBuilder.toString());
        k = 0;
        i1 = 0;
        while (k < TouchRetouchActivity.l)
        {
          i2 = TouchRetouchActivity.k - 1;
          while (i2 >= 0)
          {
            int i8 = k * m;
            int i3;
            if (i2 == 0) {
              i3 = 0;
            } else {
              i3 = (i2 - 1) * m + i - 1;
            }
            int i4;
            if (i2 == 0) {
              i4 = (paramInt - i) * paramInt;
            } else {
              i4 = 0;
            }
            j = k + 1;
            int i5;
            if (j * n >= i6) {
              i5 = paramInt - (j * m - i6 + 1);
            } else {
              i5 = paramInt;
            }
            if (i2 == 0) {
              j = i;
            } else {
              j = paramInt;
            }
            localIntBuffer.clear();
            str = TouchRetouchActivity.a;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("j = ");
            localStringBuilder.append(i2);
            localStringBuilder.append(", i = ");
            localStringBuilder.append(k);
            localStringBuilder.append("width = ");
            localStringBuilder.append(i6);
            localStringBuilder.append("height = ");
            localStringBuilder.append(i7);
            f.a(str, localStringBuilder.toString());
            str = TouchRetouchActivity.a;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(i8);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(i3);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(i5);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(j);
            f.a(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), 0, 512, i8, i3, i5, j);
            localIntBuffer.clear();
            str = TouchRetouchActivity.a;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(i8);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(i3);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(i5);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(j);
            f.a(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), i4, 512, i8, i3, i5, j);
            a(localIntBuffer, paramInt);
            arrayOfInt[i1] = a(paramGL10, localIntBuffer, paramInt, paramInt);
            i1 += 1;
            i2 -= 1;
          }
          k += 1;
        }
        TouchRetouchLib.splitImage2(arrayOfInt, i7, i6, TouchRetouchActivity.k, TouchRetouchActivity.l, paramInt);
        return;
      }
      int i = 0;
      while (i < TouchRetouchActivity.k)
      {
        for (j = 0; j < TouchRetouchActivity.l; j = i1)
        {
          m = paramInt - 1;
          this.b = (m * j);
          this.c = (m * i);
          i1 = j + 1;
          if (i1 * n >= i6) {
            k = paramInt - (i1 * m - i6 + 1);
          } else {
            k = paramInt;
          }
          i2 = i + 1;
          if (i2 * n >= i7) {
            m = paramInt - (i2 * m - i7 + 1);
          } else {
            m = paramInt;
          }
          localIntBuffer.clear();
          str = TouchRetouchActivity.a;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("start_x = ");
          localStringBuilder.append(this.b);
          localStringBuilder.append(" start_y = ");
          localStringBuilder.append(this.c);
          localStringBuilder.append(" draw_picture_size_x = ");
          localStringBuilder.append(k);
          localStringBuilder.append(" draw_picture_size_y = ");
          localStringBuilder.append(m);
          f.a(str, localStringBuilder.toString());
          paramBitmap.getPixels(localIntBuffer.array(), 0, 512, this.b, this.c, k, m);
          arrayOfInt[(TouchRetouchActivity.l * i + j)] = a(paramGL10, localIntBuffer, paramInt, paramInt);
        }
        i += 1;
      }
      TouchRetouchLib.splitImage2(arrayOfInt, i6, i7, TouchRetouchActivity.l, TouchRetouchActivity.k, paramInt);
    }
    
    void b()
    {
      TouchRetouchLib.loadResourcesDoubleTexFrag(f.a(2131623936, this.a));
      TouchRetouchLib.loadResourcesDoubleTexVert(f.a(2131623937, this.a));
      TouchRetouchLib.loadResourcesNoTexFrag(f.a(2131623938, this.a));
      TouchRetouchLib.loadResourcesNoTexVert(f.a(2131623939, this.a));
      TouchRetouchLib.loadResourcesSingleTexFrag(f.a(2131623940, this.a));
      TouchRetouchLib.loadResourcesSingleTexVert(f.a(2131623941, this.a));
    }
    
    public void onDrawFrame(GL10 paramGL10)
    {
      paramGL10.glClearColor(1.0F, 1.0F, 1.0F, 0.0F);
      b(paramGL10);
      a(paramGL10);
      c();
      d();
      e();
    }
    
    public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
    {
      f.a(TouchRetouchActivity.a, "Surface changed");
      TouchRetouchLib.surfaceChanged(this.a.getWindowManager().getDefaultDisplay().getOrientation());
      paramGL10 = TouchRetouchActivity.a;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("orientation = ");
      ((StringBuilder)localObject).append(this.a.getWindowManager().getDefaultDisplay().getOrientation());
      f.a(paramGL10, ((StringBuilder)localObject).toString());
      paramGL10 = this.a.findViewById(2131296525);
      localObject = this.a.findViewById(2131296326);
      View localView = this.a.findViewById(2131296430);
      switch (this.a.getWindowManager().getDefaultDisplay().getOrientation())
      {
      default: 
        break;
      case 1: 
      case 3: 
        TouchRetouchLib.SetScreenSize(localView.getHeight(), localView.getWidth());
        break;
      case 0: 
      case 2: 
        TouchRetouchLib.SetScreenSize(localView.getWidth(), localView.getHeight());
      }
      TouchRetouchLib.SetMargins(paramGL10.getHeight(), ((View)localObject).getHeight());
    }
    
    public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
    {
      try
      {
        f.a(TouchRetouchActivity.a, "Surface created");
        b();
        TouchRetouchLib.SetMargins(this.a.findViewById(2131296525).getHeight(), this.a.findViewById(2131296326).getHeight());
        TouchRetouchLib.initResources(this.a.getWindowManager().getDefaultDisplay().getOrientation());
        c(paramGL10);
        TouchRetouchActivity.h(this.a);
        this.a.j();
        return;
      }
      catch (NullPointerException paramGL10)
      {
        paramEGLConfig = new StringBuilder();
        paramEGLConfig.append("null when create surface: ");
        paramEGLConfig.append(paramGL10.getMessage());
        com.crashlytics.android.a.a(paramEGLConfig.toString());
      }
    }
    
    class a
      implements MediaScannerConnection.MediaScannerConnectionClient
    {
      final TouchRetouchActivity.t a;
      private File c;
      private MediaScannerConnection d;
      
      a(TouchRetouchActivity.t paramT, Context paramContext, File paramFile)
      {
        this.a = paramT;
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
}
