package com.objectremover.touchretouch.Activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
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
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Process;
import android.preference.PreferenceManager;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.advasoft.touchretouch.TouchRetouchLib;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.objectremover.touchretouch.Subfile.Glob;
import com.objectremover.touchretouch.Subfile.ImagePicker;
import com.objectremover.touchretouch.Subfile.ImagePickerInterface;
import com.objectremover.touchretouch.Subfile.SystemOperations;
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
  implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener, ImagePickerInterface
{
  private static final long BRUSH_SIZE_DISPLAY_LENGHT = 300L;
  public static final int CHOSE_FILE_SAVE_ERROR = 504;
  public static final int CHOSE_FILE_SAVE_EXISTS = 505;
  public static final int CHOSE_FILE_SAVE_OR_SEND = 506;
  public static final int CHOSE_OPEN_SOURCE_DIALOG = 502;
  public static final int CHOSE_RESOLUTION_DIALOG = 501;
  private static final String CLONE_STAMP = "CLONE_STAMP";
  private static final int DIALOG_BUY_FULL_VERSION = 605;
  private static final int DIALOG_HAVE_FOUND_APK = 606;
  private static final int DIALOG_NEED_TO_SAVE = 602;
  private static final int DIALOG_NEED_TO_SAVE_BEFORE_QUIT = 603;
  private static final int DIALOG_SAVE_OR_SEND = 604;
  private static final String MOVE = "MOVE";
  private static final String MY_PREFS_NAME = "WIPE_OUT";
  private static final String OBJECT_CLONE_BRUSH = "OBJECT_CLONE_BRUSH";
  private static final String OBJECT_CLONE_HARDNESS = "OBJECT_CLONE_HARDNESS";
  private static final String OBJECT_CLONE_MIRRORING = "OBJECT_CLONE_MIRRORING";
  private static final String OBJECT_QUICK_BRUSH = "OBJECT_QUICK_BRUSH";
  private static final String OBJECT_REMOVAL = "OBJECT_REMOVAL";
  private static final String OBJECT_REMOVAL_BRUSH = "OBJECT_REMOVAL_BRUSH";
  private static final String OBJECT_REMOVAL_ERASER = "OBJECT_REMOVAL_ERASER";
  private static final String OBJECT_REMOVAL_LASSO = "OBJECT_REMOVAL_LASSO";
  private static final String OBJECT_SELECT = "OBJECT_SELECT";
  private static final String PANEL_VISIBLE = "PANEL_VISIBLE";
  private static final int PIC_TYPE_JPG = 401;
  private static final int PIC_TYPE_OTHERS = 402;
  private static final int PRESSURE_BRUSH_TYPE_OFF = 3;
  private static final int PT_ADDED_PANEL = 302;
  private static final int PT_CLONE_MODE = 301;
  private static final int PT_FLIP_MODE = 300;
  private static final String QUICK_REPAIR = "QUICK_REPAIR";
  public static final int SAVED_IMAGE_CONT = 2;
  public static final int SAVED_IMAGE_SAVE = 0;
  public static final int SAVED_IMAGE_SEND = 1;
  private static String TAG = "MyTouchView";
  private static final int TEX_SIZE = 512;
  public static final String TOUCHRETOUCH = "touchretouch";
  public static final String TOUCHRETOUCH_FOLDER = Glob.Edit_Folder_name;
  public static final String TOUCHRETOUCH_TEMP_CACHE_FOLDER = ".cache";
  public static final String TOUCHRETOUCH_TEMP_FOLDER = ".temp";
  public static final String TOUCHRETOUCH_TEMP_SHARE_FOLDER = ".share";
  static int a;
  private static boolean atLeastOneImageLoaded = false;
  static int b;
  private static int m_height;
  public static IntBuffer m_ib;
  private static int m_lastPanelShown = 0;
  public static float m_lastZoomValue = 0.0F;
  private static long m_last_time = 0L;
  private static long m_last_touch_time;
  public static BitmapFactory.Options m_opt;
  public static int m_pic_orient;
  public static String m_pic_path;
  public static int m_pic_scale;
  public static int m_pic_type;
  public static int m_tex_num_height;
  public static int m_tex_num_width;
  public static int m_whyAppIsCracked = 0;
  private static int m_width;
  private Runnable Timer_Tick = new C11171();
  private TranslateAnimation animationZoom;
  RelativeLayout c;
  GLSurfaceView d;
  boolean e = false;
  protected boolean f = false;
  protected boolean g = false;
  ImageView h;
  private InterstitialAd interstitialAdFB;
  private Queue<ButtonsEvents> mButtonsEventQueue;
  private Queue<Integer> mFileSavedQueue;
  private Queue<Integer> mFullVersionQueue;
  private Queue<String> mJPGPictureQueue;
  private Queue<Bitmap> mPictureQueue;
  private Queue<MyMotionEvent> mQueue;
  private boolean m_addedPanel_was_visible = false;
  private Bitmap m_bm;
  private BitmapFactory.Options m_btnPreferedOpt;
  private String m_current_file_name;
  private ExifInterface m_exif;
  private String m_found_apk;
  private boolean m_hasMultitouch;
  private ImagePicker m_imgPicker;
  private boolean m_isLongOperationStarted;
  private boolean m_isLongOperationViewVisible;
  private boolean m_isUsePressureBrush = false;
  private int m_lastPointerCount = 0;
  private int m_lastSelectedCloneMode;
  private int m_lastSelectedFlipMode;
  private int m_lastSelectedTool = -1;
  private boolean m_myTimerStarted = false;
  private boolean m_went_from_first_view;
  private boolean m_zoom_started = false;
  public Timer myTimer;
  
  public TouchRetouchActivity() {}
  
  private Bitmap DecodeBitmapFile(String paramString, BitmapFactory.Options paramOptions)
  {
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "trying to decode bitmap file " + paramOptions.inSampleSize);
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeFile(paramString, paramOptions);
      return localBitmap;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Out of memory error :(");
      paramOptions.inSampleSize *= 2;
      this.e = true;
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Set it twice less with opt.inSampleSize " + paramOptions.inSampleSize);
    }
    return DecodeBitmapFile(paramString, paramOptions);
  }
  
  private void DisableAllButtons()
  {
    SetButtonLayoutDisabled(2131624227);
    SetButtonLayoutDisabled(2131624230);
    SetButtonLayoutDisabled(2131624233);
    SetButtonLayoutDisabled(2131624267);
    SetButtonLayoutDisabled(2131624252);
    SetButtonLayoutDisabled(2131624264);
    SetButtonLayoutDisabled(2131624248);
    SetButtonLayoutDisabled(2131624270);
    SetButtonLayoutDisabled(2131624304);
    SetButtonLayoutDisabled(2131624273);
    SetButtonLayoutDisabled(2131624236);
    SetButtonDisabled(2131624303);
    SetButtonLayoutDisabled(2131624255);
    SetButtonLayoutDisabled(2131624258);
    SetButtonLayoutDisabled(2131624261);
    SetButtonDisabled(2131624282);
    SetButtonDisabled(2131624284);
    SetButtonDisabled(2131624286);
    SetButtonDisabled(2131624288);
    SetButtonDisabled(2131624290);
    SetButtonDisabled(2131624293);
    SetButtonDisabled(2131624295);
    SetButtonDisabled(2131624297);
    SetButtonDisabled(2131624299);
  }
  
  private void EnableAllButtons()
  {
    SetButtonLayoutEnabled(2131624227);
    SetButtonLayoutEnabled(2131624230);
    SetButtonLayoutEnabled(2131624233);
    SetButtonLayoutEnabled(2131624267);
    SetButtonLayoutEnabled(2131624252);
    SetButtonLayoutEnabled(2131624264);
    SetButtonLayoutEnabled(2131624248);
    SetButtonLayoutEnabled(2131624270);
    SetButtonLayoutEnabled(2131624304);
    SetButtonLayoutEnabled(2131624273);
    SetButtonLayoutEnabled(2131624236);
    SetButtonEnabled(2131624303);
    SetButtonLayoutEnabled(2131624255);
    SetButtonLayoutEnabled(2131624258);
    SetButtonLayoutEnabled(2131624261);
    SetButtonLayoutEnabled(2131624281);
    SetButtonLayoutEnabled(2131624283);
    SetButtonLayoutEnabled(2131624285);
    SetButtonLayoutEnabled(2131624287);
    SetButtonLayoutEnabled(2131624289);
    SetButtonLayoutEnabled(2131624292);
    SetButtonLayoutEnabled(2131624294);
    SetButtonLayoutEnabled(2131624296);
    SetButtonLayoutEnabled(2131624298);
  }
  
  private int GetBottomPanelsHeight()
  {
    return findViewById(2131624246).getHeight() + findViewById(2131624300).getHeight();
  }
  
  private int GetBrushSize()
  {
    return ((SeekBar)findViewById(2131624302)).getProgress();
  }
  
  private ExifInterface GetCurrentExif(String paramString)
  {
    try
    {
      paramString = new ExifInterface(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "exif exception");
    }
    return null;
  }
  
  private int GetDrawableForBtn(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 0;
    case 2131624248: 
      return 2130837671;
    case 2131624252: 
    case 2131624264: 
      return 2130837598;
    case 2131624267: 
      return 2130837701;
    case 2131624270: 
      return 2130837653;
    case 2131624273: 
      return 2130837615;
    case 2131624282: 
      return 2130837599;
    case 2131624284: 
      return 2130837600;
    case 2131624286: 
      return 2130837601;
    case 2131624288: 
      return 2130837602;
    case 2131624290: 
      return 2130837603;
    case 2131624293: 
      return 2130837604;
    case 2131624295: 
      return 2130837605;
    case 2131624297: 
      return 2130837606;
    }
    return 2130837607;
  }
  
  public static int GetOrientation(int paramInt)
  {
    return paramInt / 90;
  }
  
  private View GetPanelByType(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 300: 
      return findViewById(2131624291);
    case 301: 
      return findViewById(2131624280);
    }
    return findViewById(2131624300);
  }
  
  private CharSequence[] GetSelectionItems()
  {
    int m = 1;
    int n;
    int i;
    int i1;
    label36:
    int i2;
    if (b > a)
    {
      n = b;
      i = GetOrientation(m_pic_orient);
      if (i % 2 != 1) {
        break label543;
      }
      i1 = a;
      if (i % 2 != 1) {
        break label551;
      }
      i2 = b;
      label48:
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "bigSide = " + n);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "m_cur_image_width = " + b + " m_cur_image_height = " + a);
      if (n < 640) {
        break label634;
      }
    }
    label543:
    label551:
    label559:
    label634:
    for (int j = 1;; j = 0)
    {
      i = j;
      if (n >= 1280) {
        i = j + 1;
      }
      j = i;
      if (n >= 2560) {
        j = i + 1;
      }
      i = j;
      if (n >= 5120) {
        i = j + 1;
      }
      j = i;
      if (i == 0) {
        j = i + 1;
      }
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "number of proposals = " + j);
      CharSequence[] arrayOfCharSequence = new CharSequence[j];
      int k;
      if (!this.e)
      {
        arrayOfCharSequence[0] = (getString(2131165287) + " (" + i1 + "x" + i2 + ")");
        k = 2;
      }
      for (;;)
      {
        i = k;
        j = m;
        if (correctWithDilnyk(n, k) >= 640)
        {
          if (!this.e) {
            break label559;
          }
          arrayOfCharSequence[m] = (getString(2131165284) + " (" + correctWithDilnyk(i1, k) + "x" + correctWithDilnyk(i2, k) + ")");
        }
        for (;;)
        {
          j = m + 1;
          i = k * 2;
          m = i;
          k = j;
          if (correctWithDilnyk(n, i) >= 640)
          {
            arrayOfCharSequence[j] = (GetStringForWidth(correctWithDilnyk(n, i)) + " (" + correctWithDilnyk(i1, i) + "x" + correctWithDilnyk(i2, i) + ")");
            k = j + 1;
            m = i * 2;
          }
          if (correctWithDilnyk(n, m) >= 640) {
            arrayOfCharSequence[k] = (GetStringForWidth(correctWithDilnyk(n, m)) + " (" + correctWithDilnyk(i1, m) + "x" + correctWithDilnyk(i2, m) + ")");
          }
          return arrayOfCharSequence;
          n = a;
          break;
          i1 = b;
          break label36;
          i2 = a;
          break label48;
          arrayOfCharSequence[m] = (GetStringForWidth(correctWithDilnyk(n, k)) + " (" + correctWithDilnyk(i1, k) + "x" + correctWithDilnyk(i2, k) + ")");
        }
        k = 1;
        m = 0;
      }
    }
  }
  
  private String GetStringForWidth(int paramInt)
  {
    if (paramInt >= 5120) {
      return getString(2131165284);
    }
    if (paramInt >= 2560) {
      return getString(2131165283);
    }
    if (paramInt >= 1280) {
      return getString(2131165286);
    }
    if (paramInt >= 640) {
      return getString(2131165285);
    }
    return null;
  }
  
  private void HaveFoundApk()
  {
    showDialog(606);
  }
  
  private void HideAllAddedButtons()
  {
    findViewById(2131624303).setVisibility(8);
    findViewById(2131624255).setVisibility(8);
    findViewById(2131624258).setVisibility(8);
    findViewById(2131624261).setVisibility(8);
  }
  
  private void HidePanel(int paramInt)
  {
    View localView = GetPanelByType(paramInt);
    if (localView != null) {
      localView.setVisibility(8);
    }
  }
  
  private Dialog InitChoseOpenSourceDialog()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131165301);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492876));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2131558401, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        }
        TouchRetouchActivity.this.OpenPictureFromLibrary();
      }
    }).create();
  }
  
  private Dialog InitChoseResolutionDialog()
  {
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "InitChoseResolutionDialog");
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131165302));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492964));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(GetSelectionItems(), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          TouchRetouchActivity.h(TouchRetouchActivity.this);
          TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.i(TouchRetouchActivity.this));
          return;
        case 1: 
          TouchRetouchActivity.h(TouchRetouchActivity.this);
          paramAnonymousDialogInterface = TouchRetouchActivity.m_opt;
          paramAnonymousDialogInterface.inSampleSize *= 2;
          TouchRetouchActivity.i(TouchRetouchActivity.this).recycle();
          TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.m_pic_path, TouchRetouchActivity.m_opt));
          TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.i(TouchRetouchActivity.this));
          return;
        case 2: 
          TouchRetouchActivity.h(TouchRetouchActivity.this);
          paramAnonymousDialogInterface = TouchRetouchActivity.m_opt;
          paramAnonymousDialogInterface.inSampleSize *= 4;
          TouchRetouchActivity.i(TouchRetouchActivity.this).recycle();
          TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.m_pic_path, TouchRetouchActivity.m_opt));
          TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.i(TouchRetouchActivity.this));
          return;
        }
        TouchRetouchActivity.h(TouchRetouchActivity.this);
        paramAnonymousDialogInterface = TouchRetouchActivity.m_opt;
        paramAnonymousDialogInterface.inSampleSize *= 8;
        TouchRetouchActivity.i(TouchRetouchActivity.this).recycle();
        TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.a(TouchRetouchActivity.this, TouchRetouchActivity.m_pic_path, TouchRetouchActivity.m_opt));
        TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.i(TouchRetouchActivity.this));
      }
    }).create();
  }
  
  private void InitPrefs()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
    TouchRetouchLib.SetRetouchAnimation(((SharedPreferences)localObject).getBoolean("pref_check_show_animation", true));
    localObject = ((SharedPreferences)localObject).getString("pref_list_show_hint", "1");
    if (((String)localObject).equals("1"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(0);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Auto set");
      return;
    }
    if (((String)localObject).equals("2"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(1);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Top Left");
      return;
    }
    if (((String)localObject).equals("3"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(2);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Top Right");
      return;
    }
    if (((String)localObject).equals("4"))
    {
      TouchRetouchLib.SetFingerMoveHintMode(3);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Off is set");
      return;
    }
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "The value is not allowed!!!!!!!!!!");
  }
  
  private boolean IsPanelShown(int paramInt)
  {
    View localView = GetPanelByType(paramInt);
    return (localView != null) && (localView.getVisibility() == 0);
  }
  
  private void OnBrushSelected()
  {
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
    localEditor.apply();
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), i);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624253), i);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    this.m_lastSelectedTool = 2131624252;
    TouchRetouchLib.SetEditTool(1, GetBrushSize());
    ShowBrushSizeWithFadeOut();
  }
  
  private void OnCloneFlipSelected()
  {
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), i);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_MIRRORING");
    localEditor.apply();
    HidePanel(300);
    HidePanel(301);
    HidePanel(302);
  }
  
  private void OnCloneFlipTypeSelected(int paramInt)
  {
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Flip " + paramInt + " selected");
    int i = paramInt - 2131624293;
    HidePanel(300);
    this.m_lastSelectedFlipMode = paramInt;
    SetImageForButton(2131624262, paramInt);
    TouchRetouchLib.SetCloneStampType(this.m_lastSelectedCloneMode - 2131624282, i);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "flip mode i = " + i + " selected");
  }
  
  private void OnCloneMaskSelected()
  {
    HidePanel(300);
    HidePanel(301);
    HidePanel(302);
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), i);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_HARDNESS");
    localEditor.apply();
  }
  
  private void OnCloneModeTypeSelected(int paramInt)
  {
    int i = paramInt - 2131624282;
    HidePanel(301);
    this.m_lastSelectedCloneMode = paramInt;
    SetImageForButton(2131624259, paramInt);
    TouchRetouchLib.SetCloneStampType(i, this.m_lastSelectedFlipMode - 2131624293);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "clone mode i = " + i + " selected");
  }
  
  private void OnCloneSelected()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    findViewById(2131624241).setVisibility(8);
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), i);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), i);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    HidePanel(300);
    HidePanel(301);
    findViewById(2131624303).setVisibility(8);
    if ((IsPanelShown(302)) && (m_lastPanelShown == 3))
    {
      HidePanel(302);
      HideAllAddedButtons();
    }
    for (;;)
    {
      this.m_lastSelectedTool = 2131624273;
      TouchRetouchLib.SetEditTool(5, GetBrushSize());
      ShowBrushSizeWithFadeOut();
      return;
      ShowCloneAddedButtons();
      ShowPanel(302);
      m_lastPanelShown = 3;
    }
  }
  
  private void OnCloneTargetSelected()
  {
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), i);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    HidePanel(300);
    HidePanel(301);
    TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131624302)).getProgress());
  }
  
  private void OnEraseAllSelected()
  {
    StartLongOperation();
    AddToButtonsEventQueue(new ButtonsEvents(4));
  }
  
  private void OnEraseSelected()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_ERASER");
    localEditor.apply();
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    findViewById(2131624241).setVisibility(8);
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), i);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    this.m_lastSelectedTool = 2131624270;
    TouchRetouchLib.SetEditTool(2, GetBrushSize());
    ShowBrushSizeWithFadeOut();
  }
  
  private void OnGoSelected()
  {
    AddToButtonsEventQueue(new ButtonsEvents(3));
    StartLongOperation();
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
  }
  
  private void OnLassoSelected()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_REMOVAL_LASSO");
    localEditor.apply();
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), i);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    this.m_lastSelectedTool = 2131624267;
    TouchRetouchLib.SetEditTool(0, GetBrushSize());
  }
  
  private void OnMoveSelected(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "MOVE");
    localEditor.apply();
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), i);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    changeTint((ImageView)findViewById(2131624277), j);
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    HidePanel(301);
    HidePanel(300);
    HidePanel(302);
    this.m_lastSelectedTool = 2131624248;
    TouchRetouchLib.SetEditTool(3, GetBrushSize());
  }
  
  private void OnOpenSelected()
  {
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    OpenPictureFromLibrary();
  }
  
  private boolean OnQuickBrushSelected()
  {
    findViewById(2131624304).setVisibility(8);
    int i = getResources().getColor(2131492894);
    int j = getResources().getColor(2131492964);
    changeTint((ImageView)findViewById(2131624268), j);
    changeTint((ImageView)findViewById(2131624253), j);
    changeTint((ImageView)findViewById(2131624265), i);
    changeTint((ImageView)findViewById(2131624274), j);
    changeTint((ImageView)findViewById(2131624249), j);
    changeTint((ImageView)findViewById(2131624271), j);
    changeTint((ImageView)findViewById(2131624228), j);
    changeTint((ImageView)findViewById(2131624231), j);
    changeTint((ImageView)findViewById(2131624234), j);
    changeTint((ImageView)findViewById(2131624237), j);
    changeTint((ImageView)findViewById(2131624277), j);
    changeTint((ImageView)findViewById(2131624256), j);
    changeTint((ImageView)findViewById(2131624262), j);
    changeTint((ImageView)findViewById(2131624259), j);
    ((TextView)findViewById(2131624257)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624260)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624263)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624278)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624269)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624266)).setTextColor(getResources().getColor(2131492894));
    ((TextView)findViewById(2131624254)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624275)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624250)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624229)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624232)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624235)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624272)).setTextColor(getResources().getColor(2131492964));
    ((TextView)findViewById(2131624238)).setTextColor(getResources().getColor(2131492964));
    this.m_lastSelectedTool = 2131624264;
    TouchRetouchLib.SetEditTool(1, GetBrushSize());
    ShowBrushSizeWithFadeOut();
    return true;
  }
  
  private void OnRedoSelected()
  {
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    AddToButtonsEventQueue(new ButtonsEvents(1));
    StartLongOperation();
  }
  
  private void OnSaveSelected(boolean paramBoolean)
  {
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    ShowSaveOrSendDialogActivity();
  }
  
  private void OnUndoSelected()
  {
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    AddToButtonsEventQueue(new ButtonsEvents(2));
    StartLongOperation();
  }
  
  private void OnZoomSelected(boolean paramBoolean)
  {
    TouchRetouchLib.ZoomPicture(paramBoolean);
    OnStartZoom();
    a(TouchRetouchLib.GetZoom());
    OnFinishZoom();
  }
  
  private void ProcessImagePath(String paramString1, String paramString2)
  {
    OnMoveSelected(false);
    this.m_exif = GetCurrentExif(paramString1);
    StartLongOperation();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "ProcessImagePath");
    m_pic_path = paramString1;
    if (paramString2 != null) {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "orient not null!!!");
    }
    for (m_pic_orient = Integer.parseInt(paramString2);; m_pic_orient = 0)
    {
      this.m_current_file_name = new File(paramString1).getName();
      ShowMemoryInfo();
      if (m_opt == null) {
        m_opt = new BitmapFactory.Options();
      }
      m_opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
      m_opt.inSampleSize = 1;
      this.e = false;
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "init config values");
      m_opt.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString1, m_opt);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "mime type = " + m_opt.outMimeType);
      if (m_opt.outMimeType != null) {
        break;
      }
      Toast.makeText(this, getString(2131165325), 1).show();
      setResult(0);
      finish();
      return;
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "orient is null!!!");
    }
    if (m_opt.outMimeType.equalsIgnoreCase("image/jpeg"))
    {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Processing jpeg image!");
      m_pic_type = 401;
      ProcessJPGImage();
      return;
    }
    m_pic_type = 402;
    m_opt.inJustDecodeBounds = false;
    this.m_bm = DecodeBitmapFile(m_pic_path, m_opt);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "opt.inSampleSize = " + m_opt.inSampleSize);
    b = m_opt.outWidth;
    a = m_opt.outHeight;
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "m_cur_image_width = " + b + ", m_cur_image_height = " + a);
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "alwaysMaxRes = " + bool);
    if (bool) {
      AddToPictureQueue(this.m_bm);
    }
    for (;;)
    {
      ShowMemoryInfo();
      return;
      this.m_went_from_first_view = false;
      ProcessChoseResolutionClick(0);
      atLeastOneImageLoaded = true;
    }
  }
  
  private void ProcessJPGImage()
  {
    b = m_opt.outWidth;
    a = m_opt.outHeight;
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "bm width = " + m_opt.outWidth + ", bm height = " + m_opt.outHeight + " m_opt.outMimeType = " + m_opt.outMimeType);
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "alwaysMaxRes = " + bool);
    if (bool)
    {
      m_pic_scale = 1;
      AddToJPGPictureQueue(m_pic_path);
      return;
    }
    this.m_went_from_first_view = false;
    ProcessChoseResolutionClick(0);
    atLeastOneImageLoaded = true;
  }
  
  private void SetButtonDisabled(int paramInt)
  {
    ((ImageView)findViewById(paramInt)).setEnabled(false);
  }
  
  private void SetButtonEnabled(int paramInt)
  {
    ((ImageView)findViewById(paramInt)).setEnabled(true);
  }
  
  private void SetButtonLayoutDisabled(int paramInt)
  {
    ((RelativeLayout)findViewById(paramInt)).setEnabled(false);
  }
  
  private void SetButtonLayoutEnabled(int paramInt)
  {
    ((RelativeLayout)findViewById(paramInt)).setEnabled(true);
  }
  
  private void SetCacheDirectory()
  {
    TouchRetouchLib.setUndoPath(SystemOperations.GetTempDirectory().getAbsolutePath());
  }
  
  private void SetClearAllVisible()
  {
    findViewById(2131624303).setVisibility(0);
  }
  
  private void SetImageForButton(int paramInt1, int paramInt2)
  {
    SetOriginalButton((ImageView)findViewById(paramInt1), GetDrawableForBtn(paramInt2));
  }
  
  private void SetListenerForButton(int paramInt, boolean paramBoolean)
  {
    ImageView localImageView = (ImageView)findViewById(paramInt);
    localImageView.setOnClickListener(this);
    localImageView.setTag(new Boolean(false));
    if (!paramBoolean) {
      localImageView.setVisibility(8);
    }
  }
  
  private void SetListenerLayoutForButton(int paramInt, boolean paramBoolean)
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(paramInt);
    localRelativeLayout.setOnClickListener(this);
    localRelativeLayout.setTag(new Boolean(false));
    if (!paramBoolean) {
      localRelativeLayout.setVisibility(8);
    }
  }
  
  private void SetOriginalButton(ImageView paramImageView, int paramInt)
  {
    paramImageView.setBackgroundResource(paramInt);
    changeTint(paramImageView, getResources().getColor(2131492894));
  }
  
  private void ShowBrushSizeWithFadeOut()
  {
    View localView = findViewById(2131624241);
    if (localView.getVisibility() == 4)
    {
      localView.setVisibility(0);
      new Handler().postDelayed(new C11237(), 300L);
    }
  }
  
  private Dialog ShowBuyFullVersionDialog()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131165327));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492964));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131165322)).setPositiveButton(getString(2131165320), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/details?id=" + TouchRetouchActivity.this.getPackageName())));
      }
    }).setNegativeButton(getString(2131165318), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private void ShowCloneAddedButtons()
  {
    findViewById(2131624255).setVisibility(0);
    findViewById(2131624258).setVisibility(0);
    findViewById(2131624261).setVisibility(0);
  }
  
  private Dialog ShowFileExist()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131165332));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492964));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131165333)).setPositiveButton(getString(2131165320), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.h(TouchRetouchActivity.this);
        TouchRetouchActivity.this.AddToButtonsEventQueue(new TouchRetouchActivity.ButtonsEvents(TouchRetouchActivity.this, 5));
      }
    }).setNegativeButton(getString(2131165318), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private void ShowFileNameEnterDialog()
  {
    Object localObject2 = Environment.getExternalStorageDirectory();
    new File(((File)localObject2).getAbsolutePath() + "/" + Glob.Edit_Folder_name).mkdirs();
    Object localObject1 = Environment.getExternalStorageDirectory() + File.separator + TOUCHRETOUCH_FOLDER;
    String str1 = "WipeOut" + new SimpleDateFormat("mm_dd_yyyy_hhmmss.SSSSSS").format(new Date()) + ".jpg";
    localObject1 = new File((String)localObject1, str1);
    String str2 = "file://" + ((File)localObject2).getAbsolutePath() + "/" + Glob.Edit_Folder_name + "/" + str1;
    Glob.edit_image_uri = ((File)localObject2).getAbsolutePath() + "/" + Glob.Edit_Folder_name + "/" + str1;
    try
    {
      localObject2 = new FileOutputStream((File)localObject1);
      ((FileOutputStream)localObject2).flush();
      ((FileOutputStream)localObject2).close();
      sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(new File(str2))));
      this.m_current_file_name = str1;
      if (((File)localObject1).exists()) {
        showDialog(505);
      }
      StartLongOperation();
      AddToButtonsEventQueue(new ButtonsEvents(5));
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
  
  private Dialog ShowFileSaveError()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131165325));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492964));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131165326)).setPositiveButton(getString(2131165320), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private Dialog ShowHaveFoundApkDialog()
  {
    return new AlertDialog.Builder(this).setTitle("Send email to developers").setMessage("We found a problem! Please press ok and send the mail we will compose").setPositiveButton(getString(2131165321), new C11259()).setNegativeButton(getString(2131165319), new C11248()).create();
  }
  
  private void ShowMemoryInfo()
  {
    ActivityManager localActivityManager = (ActivityManager)getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    localActivityManager.getMemoryInfo(localMemoryInfo);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, " memoryInfo.availMem " + localMemoryInfo.availMem + "\n");
  }
  
  private Dialog ShowNeedToSaveBeforeQuitDialog()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131165331);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492876));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131165334)).setPositiveButton(getString(2131165321), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.g = false;
        TouchRetouchActivity.b(TouchRetouchActivity.this, false);
      }
    }).setNegativeButton(getString(2131165319), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.SetStopTimer();
        TouchRetouchActivity.a(false);
        if (TouchRetouchActivity.this.g)
        {
          TouchRetouchActivity.this.g = false;
          TouchRetouchActivity.this.setResult(1);
          TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, MainActivity.class));
          TouchRetouchActivity.this.finish();
          return;
        }
        TouchRetouchActivity.this.setResult(0);
        TouchRetouchActivity.this.finish();
      }
    }).create();
  }
  
  private Dialog ShowNeedToSaveDialog()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131165331);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131492876));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131165334)).setPositiveButton(getString(2131165321), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.b(TouchRetouchActivity.this, false);
      }
    }).setNegativeButton(getString(2131165319), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.showDialog(502);
      }
    }).create();
  }
  
  private void ShowPanel(int paramInt)
  {
    View localView = GetPanelByType(paramInt);
    if (localView != null) {
      localView.setVisibility(0);
    }
  }
  
  private Dialog ShowSaveOrSendDialog()
  {
    int j = GetOrientation(m_pic_orient);
    TextView localTextView = new TextView(this);
    StringBuilder localStringBuilder = new StringBuilder().append(getString(2131165305)).append(" (");
    if (j % 2 == 1)
    {
      i = a;
      localStringBuilder = localStringBuilder.append(i).append("x");
      if (j % 2 != 1) {
        break label170;
      }
    }
    label170:
    for (int i = b;; i = a)
    {
      localTextView.setText(i + ")");
      localTextView.setPadding(0, 30, 0, 20);
      localTextView.setGravity(17);
      localTextView.setTextColor(getResources().getColor(2131492964));
      localTextView.setTextSize(20.0F);
      new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2131558402, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          default: 
            return;
          case 0: 
            TouchRetouchActivity.g(TouchRetouchActivity.this);
            return;
          case 1: 
            TouchRetouchActivity.h(TouchRetouchActivity.this);
            TouchRetouchActivity.this.AddToButtonsEventQueue(new TouchRetouchActivity.ButtonsEvents(TouchRetouchActivity.this, 6));
            return;
          }
          TouchRetouchActivity.h(TouchRetouchActivity.this);
          TouchRetouchActivity.this.AddToButtonsEventQueue(new TouchRetouchActivity.ButtonsEvents(TouchRetouchActivity.this, 6));
        }
      }).create();
      i = b;
      break;
    }
  }
  
  private void ShowSaveOrSendDialogActivity()
  {
    ShowFileNameEnterDialog();
    startActivity(new Intent(this, Image_Edite.class));
    SetLongOperationFinished();
  }
  
  private void StartLongOperation()
  {
    findViewById(2131624305).setVisibility(0);
    findViewById(2131624306).setVisibility(0);
    this.m_isLongOperationStarted = true;
    this.m_isLongOperationViewVisible = true;
    DisableAllButtons();
  }
  
  private void TimerMethod()
  {
    runOnUiThread(this.Timer_Tick);
  }
  
  private void TryToFindApk()
  {
    Object localObject1 = getPackageManager();
    List localList = ((PackageManager)localObject1).getInstalledPackages(1);
    localObject1 = ((PackageManager)localObject1).getInstalledApplications(0);
    int i = 0;
    while (i < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i);
      localObject2 = ((ApplicationInfo)((List)localObject1).get(i)).publicSourceDir;
      if (((String)localObject2).contains("com.advasoft.touchretouch"))
      {
        this.m_found_apk = ((String)localObject2);
        HaveFoundApk();
      }
      i += 1;
    }
  }
  
  protected static File c()
  {
    File localFile = new File(SystemOperations.GetTempFolderPath(), "share_t.jpg");
    String str;
    int i;
    if (localFile != null)
    {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Saving to folder " + localFile.getAbsolutePath());
      j = GetOrientation(m_pic_orient);
      str = localFile.getAbsolutePath();
      if (j % 2 != 1) {
        break label89;
      }
      i = a;
      if (j % 2 != 1) {
        break label96;
      }
    }
    label89:
    label96:
    for (int j = b;; j = a)
    {
      TouchRetouchLib.SaveImage(str, i, j);
      return localFile;
      i = b;
      break;
    }
  }
  
  private void changeTint(ImageView paramImageView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      paramImageView.getBackground().setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
      return;
    }
    Drawable localDrawable = DrawableCompat.wrap(paramImageView.getBackground());
    DrawableCompat.setTint(localDrawable, paramInt);
    paramImageView.setBackgroundDrawable(DrawableCompat.unwrap(localDrawable));
  }
  
  private static void checkEglError(String paramString, EGL10 paramEGL10)
  {
    while (paramEGL10.eglGetError() != 12288) {}
  }
  
  public static int correctWithDilnyk(int paramInt1, int paramInt2)
  {
    return (paramInt2 - 1 + paramInt1) / paramInt2;
  }
  
  private void init01(Context paramContext)
  {
    if (!paramContext.getPackageName().equals(new String(Base64.decode("Y29tLm9iamVjdHJlbW92ZXIudG91Y2hyZXRvdWNo", 0)))) {
      Process.killProcess(Process.myPid());
    }
  }
  
  private void loadFBInterstitialAd()
  {
    this.interstitialAdFB = new InterstitialAd(this, getResources().getString(2131165262));
    this.interstitialAdFB.setAdListener(new InterstitialAdListener()
    {
      public void onAdClicked(Ad paramAnonymousAd) {}
      
      public void onAdLoaded(Ad paramAnonymousAd) {}
      
      public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError) {}
      
      public void onInterstitialDismissed(Ad paramAnonymousAd) {}
      
      public void onInterstitialDisplayed(Ad paramAnonymousAd) {}
      
      public void onLoggingImpression(Ad paramAnonymousAd) {}
    });
    this.interstitialAdFB.loadAd();
  }
  
  private void showFBInterstitial()
  {
    if ((this.interstitialAdFB != null) && (this.interstitialAdFB.isAdLoaded())) {
      this.interstitialAdFB.show();
    }
  }
  
  private void showInterstitial() {}
  
  public void AddToButtonsEventQueue(ButtonsEvents paramButtonsEvents)
  {
    try
    {
      this.mButtonsEventQueue.add(paramButtonsEvents);
      return;
    }
    finally
    {
      paramButtonsEvents = finally;
      throw paramButtonsEvents;
    }
  }
  
  public void AddToFileSavedEventQueue(Integer paramInteger)
  {
    try
    {
      this.mFileSavedQueue.add(paramInteger);
      return;
    }
    finally
    {
      paramInteger = finally;
      throw paramInteger;
    }
  }
  
  public void AddToFullVersionEventQueue(Integer paramInteger)
  {
    try
    {
      this.mFullVersionQueue.add(paramInteger);
      return;
    }
    finally
    {
      paramInteger = finally;
      throw paramInteger;
    }
  }
  
  public void AddToJPGPictureQueue(String paramString)
  {
    try
    {
      this.mJPGPictureQueue.add(paramString);
      return;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public void AddToPictureQueue(Bitmap paramBitmap)
  {
    try
    {
      this.mPictureQueue.add(paramBitmap);
      return;
    }
    finally
    {
      paramBitmap = finally;
      throw paramBitmap;
    }
  }
  
  public void AddToQueue(MyMotionEvent paramMyMotionEvent)
  {
    try
    {
      this.mQueue.add(paramMyMotionEvent);
      return;
    }
    finally
    {
      paramMyMotionEvent = finally;
      throw paramMyMotionEvent;
    }
  }
  
  public void DrawBrushSizeView(int paramInt)
  {
    ImageView localImageView = (ImageView)findViewById(2131624242);
    Canvas localCanvas = new Canvas();
    Bitmap localBitmap = Bitmap.createBitmap(2400, 2400, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(getResources().getColor(2131492896));
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawRoundRect(new RectF(2.14748365E9F, 2.14748365E9F, -2.14748122E9F, -2.14748122E9F), 50.0F, 50.0F, localPaint);
    localPaint.setColor(getResources().getColor(2131492894));
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawCircle(1200.0F, 1200.0F, (float)(paramInt * 5.6D), localPaint);
    localImageView.setImageBitmap(localBitmap);
  }
  
  public ButtonsEvents GetFromButtonsEventQueue()
  {
    try
    {
      ButtonsEvents localButtonsEvents = (ButtonsEvents)this.mButtonsEventQueue.poll();
      return localButtonsEvents;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Integer GetFromFileSavedEventQueue()
  {
    try
    {
      Integer localInteger = (Integer)this.mFileSavedQueue.poll();
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Integer GetFromFullVersionEventQueue()
  {
    try
    {
      Integer localInteger = (Integer)this.mFullVersionQueue.poll();
      return localInteger;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public String GetFromJPGPictureQueue()
  {
    try
    {
      String str = (String)this.mJPGPictureQueue.poll();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Bitmap GetFromPictureQueue()
  {
    try
    {
      Bitmap localBitmap = (Bitmap)this.mPictureQueue.poll();
      return localBitmap;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public MyMotionEvent GetFromQueue()
  {
    try
    {
      MyMotionEvent localMyMotionEvent = (MyMotionEvent)this.mQueue.poll();
      return localMyMotionEvent;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void ObjectClone(View paramView)
  {
    findViewById(2131624255).performClick();
    findViewById(2131624304).setVisibility(8);
    findViewById(2131624247).setVisibility(8);
    findViewById(2131624251).setVisibility(0);
    findViewById(2131624273).setVisibility(8);
    findViewById(2131624264).setVisibility(8);
    findViewById(2131624252).setVisibility(8);
    findViewById(2131624267).setVisibility(8);
    findViewById(2131624270).setVisibility(8);
    findViewById(2131624255).setVisibility(0);
    findViewById(2131624258).setVisibility(0);
    findViewById(2131624261).setVisibility(0);
    findViewById(2131624276).setVisibility(0);
    paramView = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131624251).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "CLONE_STAMP");
    paramView.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    paramView.apply();
  }
  
  public void OnBack(View paramView)
  {
    StartLongOperation();
    AddToButtonsEventQueue(new ButtonsEvents(4));
    HidePanel(301);
    HidePanel(300);
    HidePanel(302);
    findViewById(2131624247).setVisibility(0);
    findViewById(2131624251).setVisibility(8);
    findViewById(2131624273).setVisibility(8);
    findViewById(2131624264).setVisibility(8);
    findViewById(2131624252).setVisibility(8);
    findViewById(2131624267).setVisibility(8);
    findViewById(2131624270).setVisibility(8);
    findViewById(2131624276).setVisibility(8);
    findViewById(2131624304).setVisibility(8);
    findViewById(2131624248).performClick();
    paramView = new TranslateAnimation(1, -1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131624247).startAnimation(paramView);
  }
  
  public void OnFinishZoom()
  {
    this.m_zoom_started = false;
    new Handler().postDelayed(new C11182(), 300L);
  }
  
  public void OnStartZoom()
  {
    findViewById(2131624243).setVisibility(0);
    this.m_zoom_started = true;
  }
  
  public void OpenPictureFromLibrary()
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("image/*");
    startActivityForResult(Intent.createChooser(localIntent, getString(2131165301)), 301);
  }
  
  public void ProcessChoseResolutionClick(int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      BitmapFactory.Options localOptions;
      do
      {
        do
        {
          do
          {
            return;
            StartLongOperation();
            if (m_pic_type == 402)
            {
              AddToPictureQueue(this.m_bm);
              return;
            }
          } while (m_pic_type != 401);
          m_pic_scale = 1;
          AddToJPGPictureQueue(m_pic_path);
          return;
          StartLongOperation();
          if (m_pic_type == 402)
          {
            localOptions = m_opt;
            localOptions.inSampleSize *= 2;
            this.m_bm.recycle();
            this.m_bm = DecodeBitmapFile(m_pic_path, m_opt);
            AddToPictureQueue(this.m_bm);
            return;
          }
        } while (m_pic_type != 401);
        m_pic_scale = 2;
        AddToJPGPictureQueue(m_pic_path);
        return;
        StartLongOperation();
        if (m_pic_type == 402)
        {
          localOptions = m_opt;
          localOptions.inSampleSize *= 4;
          this.m_bm.recycle();
          this.m_bm = DecodeBitmapFile(m_pic_path, m_opt);
          AddToPictureQueue(this.m_bm);
          return;
        }
      } while (m_pic_type != 401);
      m_pic_scale = 4;
      AddToJPGPictureQueue(m_pic_path);
      return;
      StartLongOperation();
      if (m_pic_type == 402)
      {
        localOptions = m_opt;
        localOptions.inSampleSize *= 8;
        this.m_bm.recycle();
        this.m_bm = DecodeBitmapFile(m_pic_path, m_opt);
        AddToPictureQueue(this.m_bm);
        return;
      }
    } while (m_pic_type != 401);
    m_pic_scale = 8;
    AddToJPGPictureQueue(m_pic_path);
  }
  
  public void QuickRepair(View paramView)
  {
    findViewById(2131624264).performClick();
    findViewById(2131624304).setVisibility(8);
    findViewById(2131624247).setVisibility(8);
    findViewById(2131624251).setVisibility(0);
    findViewById(2131624273).setVisibility(8);
    findViewById(2131624264).setVisibility(0);
    findViewById(2131624252).setVisibility(8);
    findViewById(2131624267).setVisibility(8);
    findViewById(2131624270).setVisibility(8);
    findViewById(2131624276).setVisibility(0);
    findViewById(2131624255).setVisibility(8);
    findViewById(2131624258).setVisibility(8);
    findViewById(2131624261).setVisibility(8);
    paramView = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramView.setDuration(300L);
    findViewById(2131624251).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "QUICK_REPAIR");
    paramView.putString("OBJECT_SELECT", "OBJECT_QUICK_BRUSH");
    paramView.apply();
  }
  
  public void SetLastEditedTool()
  {
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "SetLastEditedTool");
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "lastSelectedTool = " + this.m_lastSelectedTool);
    switch (this.m_lastSelectedTool)
    {
    default: 
      return;
    case 2131624252: 
      TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131624302)).getProgress());
      return;
    case 2131624264: 
      TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131624302)).getProgress());
      return;
    case 2131624267: 
      TouchRetouchLib.SetEditTool(0, ((SeekBar)findViewById(2131624302)).getProgress());
      return;
    case 2131624270: 
      TouchRetouchLib.SetEditTool(2, ((SeekBar)findViewById(2131624302)).getProgress());
      return;
    }
    TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131624302)).getProgress());
    TouchRetouchLib.SetCloneStampType(this.m_lastSelectedCloneMode, this.m_lastSelectedFlipMode);
  }
  
  public void SetLongOperationFinished()
  {
    this.m_isLongOperationStarted = false;
  }
  
  public void SetPanelsVisible(boolean paramBoolean)
  {
    View localView1 = findViewById(2131624224);
    View localView2 = findViewById(2131624300);
    Object localObject = findViewById(2131624291);
    View localView3 = findViewById(2131624280);
    ((View)localObject).setVisibility(4);
    localView3.setVisibility(4);
    localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    ((Animation)localObject).setDuration(300L);
    if (this.m_lastSelectedTool == 2131624273)
    {
      this.m_addedPanel_was_visible = true;
      localView2.startAnimation((Animation)localObject);
    }
    if (!this.m_hasMultitouch)
    {
      this.animationZoom = new TranslateAnimation(1, 0.0F, 1, 1.0F, 1, 0.0F, 1, 0.0F);
      this.animationZoom.setDuration(300L);
      localView1.startAnimation(this.animationZoom);
      localView1.setVisibility(4);
    }
  }
  
  public void SetStartTimer()
  {
    if (!this.m_myTimerStarted)
    {
      if (this.myTimer == null) {
        this.myTimer = new Timer();
      }
      this.myTimer.schedule(new C11226(), 0L, 200L);
      this.m_myTimerStarted = true;
    }
  }
  
  public void SetStopTimer()
  {
    if ((this.m_myTimerStarted) && (this.myTimer != null))
    {
      this.myTimer.cancel();
      this.myTimer.purge();
      this.myTimer = null;
      this.m_myTimerStarted = false;
    }
  }
  
  public void SetUndoRedoEnabled(boolean paramBoolean1, boolean paramBoolean2)
  {
    ((RelativeLayout)findViewById(2131624230)).setEnabled(paramBoolean1);
    ((RelativeLayout)findViewById(2131624233)).setEnabled(paramBoolean2);
  }
  
  public void Settings(View paramView)
  {
    paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
    if (paramView.equals("OBJECT_REMOVAL_BRUSH"))
    {
      HidePanel(301);
      HidePanel(300);
      if ((IsPanelShown(302)) && (m_lastPanelShown == 1)) {
        HidePanel(302);
      }
    }
    do
    {
      return;
      HideAllAddedButtons();
      ShowPanel(302);
      m_lastPanelShown = 1;
      return;
      if (paramView.equals("OBJECT_QUICK_BRUSH"))
      {
        HidePanel(301);
        HidePanel(300);
        if ((IsPanelShown(302)) && (m_lastPanelShown == 1))
        {
          HidePanel(302);
          return;
        }
        HideAllAddedButtons();
        ShowPanel(302);
        m_lastPanelShown = 1;
        return;
      }
      if (paramView.equals("OBJECT_CLONE_BRUSH"))
      {
        HidePanel(301);
        HidePanel(300);
        if ((IsPanelShown(302)) && (m_lastPanelShown == 1))
        {
          HidePanel(302);
          return;
        }
        ShowPanel(302);
        m_lastPanelShown = 1;
        return;
      }
      if (paramView.equals("OBJECT_REMOVAL_ERASER"))
      {
        HidePanel(301);
        HidePanel(300);
        if ((IsPanelShown(302)) && (m_lastPanelShown == 2)) {
          HidePanel(302);
        }
        for (;;)
        {
          SetClearAllVisible();
          return;
          HideAllAddedButtons();
          ShowPanel(302);
          m_lastPanelShown = 2;
        }
      }
      if (paramView.equals("OBJECT_CLONE_HARDNESS"))
      {
        HidePanel(300);
        HidePanel(302);
        if ((IsPanelShown(301)) && (m_lastPanelShown == 1))
        {
          HidePanel(301);
          return;
        }
        ShowPanel(301);
        m_lastPanelShown = 1;
        return;
      }
    } while (!paramView.equals("OBJECT_CLONE_MIRRORING"));
    if ((IsPanelShown(300)) && (m_lastPanelShown == 1)) {
      HidePanel(300);
    }
    for (;;)
    {
      HidePanel(301);
      HidePanel(302);
      return;
      ShowPanel(300);
      m_lastPanelShown = 1;
    }
  }
  
  public void ShowFileSaved()
  {
    Toast.makeText(this, getString(2131165329), 1).show();
  }
  
  protected void a()
  {
    this.h = ((ImageView)findViewById(2131624240));
    this.m_btnPreferedOpt = new BitmapFactory.Options();
    this.m_btnPreferedOpt.inPreferredConfig = Bitmap.Config.ARGB_4444;
    m_last_time = System.currentTimeMillis();
    SetListenerLayoutForButton(2131624227, true);
    SetListenerLayoutForButton(2131624230, true);
    SetListenerLayoutForButton(2131624233, true);
    SetListenerLayoutForButton(2131624267, true);
    SetListenerLayoutForButton(2131624252, true);
    SetListenerLayoutForButton(2131624264, true);
    SetListenerLayoutForButton(2131624248, true);
    SetListenerLayoutForButton(2131624270, true);
    SetListenerLayoutForButton(2131624304, true);
    SetListenerLayoutForButton(2131624273, true);
    SetListenerLayoutForButton(2131624236, true);
    SetListenerForButton(2131624303, false);
    SetListenerLayoutForButton(2131624255, false);
    SetListenerLayoutForButton(2131624258, false);
    SetListenerLayoutForButton(2131624261, false);
    SetListenerLayoutForButton(2131624281, true);
    SetListenerLayoutForButton(2131624283, true);
    SetListenerLayoutForButton(2131624285, true);
    SetListenerLayoutForButton(2131624287, true);
    SetListenerLayoutForButton(2131624289, true);
    SetListenerLayoutForButton(2131624292, true);
    SetListenerLayoutForButton(2131624294, true);
    SetListenerLayoutForButton(2131624296, true);
    SetListenerLayoutForButton(2131624298, true);
    SetListenerForButton(2131624225, true);
    SetListenerForButton(2131624226, true);
    HidePanel(302);
    HidePanel(300);
    HidePanel(301);
    this.m_lastSelectedCloneMode = 2131624282;
    this.m_lastSelectedFlipMode = 2131624293;
    this.m_lastSelectedTool = 2131624248;
    Object localObject = (SeekBar)findViewById(2131624302);
    ((SeekBar)localObject).setOnSeekBarChangeListener(this);
    DrawBrushSizeView(((SeekBar)localObject).getProgress());
    this.d = ((GLSurfaceView)findViewById(2131624223));
    this.d.getHolder().setFormat(-3);
    this.d.setEGLContextFactory(new ContextFactory(null));
    this.d.setEGLConfigChooser(new ConfigChooser(8, 8, 8, 8, 0, 0));
    this.d.setRenderer(new Renderer(this));
    this.d.setRenderMode(1);
    this.d.setOnTouchListener(this);
    localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    int i = getWindowManager().getDefaultDisplay().getOrientation();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "metrics.widthPixels = " + ((DisplayMetrics)localObject).widthPixels + "metrics.heightPixels = " + ((DisplayMetrics)localObject).heightPixels);
    switch (i)
    {
    default: 
      i = TouchRetouchLib.init(m_width, m_height, i, Build.VERSION.SDK_INT);
      if (i != 0) {
        switch (i)
        {
        }
      }
      break;
    }
    for (;;)
    {
      SetCacheDirectory();
      this.mQueue = new LinkedList();
      this.mPictureQueue = new LinkedList();
      this.mJPGPictureQueue = new LinkedList();
      this.mButtonsEventQueue = new LinkedList();
      this.mFileSavedQueue = new LinkedList();
      this.mFullVersionQueue = new LinkedList();
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "lined lists initialized");
      this.m_hasMultitouch = getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch");
      if (!this.m_hasMultitouch) {
        findViewById(2131624224).setVisibility(0);
      }
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "checked multitouch");
      this.m_imgPicker = new ImagePicker();
      this.m_imgPicker.SetImagePickerListener(this);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "init controls finished");
      return;
      m_width = ((DisplayMetrics)localObject).widthPixels;
      m_height = ((DisplayMetrics)localObject).heightPixels;
      break;
      m_width = ((DisplayMetrics)localObject).heightPixels;
      m_height = ((DisplayMetrics)localObject).widthPixels;
      break;
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "app is cracked!");
      m_whyAppIsCracked = 1;
      continue;
      m_whyAppIsCracked = 1;
      TryToFindApk();
    }
  }
  
  protected void a(float paramFloat)
  {
    ImageView localImageView = (ImageView)findViewById(2131624244);
    Canvas localCanvas = new Canvas();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "we really call drawZoomValue here!!!");
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "koef = 12.0");
    Bitmap localBitmap = Bitmap.createBitmap(1440, 960, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(-1895825408);
    localPaint.setStyle(Paint.Style.FILL);
    localCanvas.drawRoundRect(new RectF(120.0F, 120.0F, 1320.0F, 648.0F), 240.0F, 240.0F, localPaint);
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(-1);
    localPaint.setTextSize(336.0F);
    localPaint.setAntiAlias(true);
    localPaint.setTypeface(Typeface.defaultFromStyle(0));
    int i = (int)(100.0F * paramFloat);
    if (i >= 100) {
      localCanvas.drawText(i + "%", 336.0F, 480.0F, localPaint);
    }
    for (;;)
    {
      localImageView.setImageBitmap(localBitmap);
      return;
      localCanvas.drawText(i + "%", 420.0F, 480.0F, localPaint);
    }
  }
  
  protected void b()
  {
    if (getIntent().getIntExtra("picsource", 0) == 702)
    {
      str1 = getIntent().getStringExtra("picpath");
      String str2 = getIntent().getStringExtra("picorient");
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "processing image from camera");
      ProcessImagePath(str1, str2);
      SetStartTimer();
      return;
    }
    String str1 = getIntent().getDataString();
    if (str1 == null)
    {
      this.m_imgPicker.PickImageFromGallery((Uri)getIntent().getExtras().get("android.intent.extra.STREAM"));
      return;
    }
    this.m_imgPicker.PickImageFromGallery(Uri.parse(str1));
  }
  
  protected void d()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setFlags(268435456);
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "touchretouch@handyphotolab.com" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "can't found apk!");
    localIntent.putExtra("android.intent.extra.TEXT", "Path to apk is " + this.m_found_apk);
    startActivity(Intent.createChooser(localIntent, getString(2131165324)));
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      SetLongOperationFinished();
    }
    do
    {
      do
      {
        return;
        if (paramInt2 == -1)
        {
          SetStopTimer();
          this.m_imgPicker.PickImageFromGallery(paramIntent.getData());
          return;
        }
        SetLongOperationFinished();
        return;
      } while (paramInt2 != -1);
      switch (DialogSaveSendActivity.ClickedItem)
      {
      default: 
        return;
      case 0: 
        ShowFileNameEnterDialog();
        startActivity(new Intent(this, Image_Edite.class));
        return;
      case 1: 
        StartLongOperation();
        AddToButtonsEventQueue(new ButtonsEvents(6));
        return;
      }
      StartLongOperation();
    } while (c() == null);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "orient for contest = " + GetOrientation(m_pic_orient));
  }
  
  public void onBackPressed()
  {
    if (this.m_isLongOperationStarted)
    {
      Toast.makeText(this, getString(2131165328), 1).show();
      startActivity(new Intent(this, MainActivity.class));
      return;
    }
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "stopping timer");
    SetStopTimer();
    atLeastOneImageLoaded = false;
    setResult(0);
    startActivity(new Intent(this, MainActivity.class));
  }
  
  public void onClick(View paramView)
  {
    if (paramView == findViewById(2131624227)) {
      OnOpenSelected();
    }
    do
    {
      return;
      if (paramView == findViewById(2131624230))
      {
        OnUndoSelected();
        return;
      }
      if (paramView == findViewById(2131624233))
      {
        OnRedoSelected();
        return;
      }
      if (paramView == findViewById(2131624267))
      {
        OnLassoSelected();
        return;
      }
      if (paramView == findViewById(2131624252))
      {
        OnBrushSelected();
        return;
      }
      if (paramView == findViewById(2131624264))
      {
        OnQuickBrushSelected();
        return;
      }
      if (paramView == findViewById(2131624248))
      {
        OnMoveSelected(true);
        return;
      }
      if (paramView == findViewById(2131624270))
      {
        OnEraseSelected();
        return;
      }
      if (paramView == findViewById(2131624304))
      {
        OnGoSelected();
        return;
      }
      if (paramView == findViewById(2131624273))
      {
        OnCloneSelected();
        return;
      }
      if (paramView == findViewById(2131624236))
      {
        OnSaveSelected(true);
        return;
      }
      if (paramView == findViewById(2131624303))
      {
        OnEraseAllSelected();
        return;
      }
      if (paramView == findViewById(2131624255))
      {
        OnCloneTargetSelected();
        return;
      }
      if (paramView == findViewById(2131624258))
      {
        OnCloneMaskSelected();
        return;
      }
      if (paramView == findViewById(2131624261))
      {
        OnCloneFlipSelected();
        return;
      }
      if (paramView == findViewById(2131624281))
      {
        OnCloneModeTypeSelected(2131624282);
        return;
      }
      if (paramView == findViewById(2131624283))
      {
        OnCloneModeTypeSelected(2131624284);
        return;
      }
      if (paramView == findViewById(2131624285))
      {
        OnCloneModeTypeSelected(2131624286);
        return;
      }
      if (paramView == findViewById(2131624287))
      {
        OnCloneModeTypeSelected(2131624288);
        return;
      }
      if (paramView == findViewById(2131624289))
      {
        OnCloneModeTypeSelected(2131624290);
        return;
      }
      if (paramView == findViewById(2131624292))
      {
        OnCloneFlipTypeSelected(2131624293);
        return;
      }
      if (paramView == findViewById(2131624294))
      {
        OnCloneFlipTypeSelected(2131624295);
        return;
      }
      if (paramView == findViewById(2131624296))
      {
        OnCloneFlipTypeSelected(2131624297);
        return;
      }
      if (paramView == findViewById(2131624298))
      {
        OnCloneFlipTypeSelected(2131624299);
        return;
      }
      if (paramView == findViewById(2131624225))
      {
        OnZoomSelected(true);
        return;
      }
    } while (paramView != findViewById(2131624226));
    OnZoomSelected(false);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "OnConfig changed!!!");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968617);
    init01(this);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "onCreate");
    if (Glob.position == 1)
    {
      a();
      this.h.setOnClickListener(new C11193());
      findViewById(2131624304).setVisibility(0);
      findViewById(2131624247).setVisibility(8);
      findViewById(2131624251).setVisibility(0);
      findViewById(2131624273).setVisibility(8);
      findViewById(2131624252).setVisibility(0);
      findViewById(2131624264).setVisibility(8);
      findViewById(2131624267).setVisibility(0);
      findViewById(2131624270).setVisibility(0);
      findViewById(2131624276).setVisibility(0);
      findViewById(2131624255).setVisibility(8);
      findViewById(2131624258).setVisibility(8);
      findViewById(2131624261).setVisibility(8);
      paramBundle = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
      paramBundle.setDuration(300L);
      findViewById(2131624251).startAnimation(paramBundle);
      paramBundle = getSharedPreferences("WIPE_OUT", 0).edit();
      paramBundle.putString("PANEL_VISIBLE", "OBJECT_REMOVAL");
      paramBundle.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
      paramBundle.apply();
    }
    for (;;)
    {
      this.c = ((RelativeLayout)findViewById(2131624304));
      this.c.startAnimation(AnimationUtils.loadAnimation(this, 2131034135));
      m_ib = IntBuffer.allocate(262144);
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "extra uri = " + getIntent().getDataString());
      b();
      return;
      if (Glob.position == 2)
      {
        a();
        this.h.setOnClickListener(new C11204());
        findViewById(2131624247).setVisibility(0);
        findViewById(2131624304).setVisibility(8);
      }
      else if (Glob.position == 3)
      {
        a();
        this.h.setOnClickListener(new C11215());
        findViewById(2131624255).performClick();
        findViewById(2131624304).setVisibility(8);
        findViewById(2131624247).setVisibility(8);
        findViewById(2131624251).setVisibility(0);
        findViewById(2131624273).setVisibility(8);
        findViewById(2131624264).setVisibility(8);
        findViewById(2131624252).setVisibility(8);
        findViewById(2131624267).setVisibility(8);
        findViewById(2131624270).setVisibility(8);
        findViewById(2131624255).setVisibility(0);
        findViewById(2131624258).setVisibility(0);
        findViewById(2131624261).setVisibility(0);
        findViewById(2131624276).setVisibility(0);
        paramBundle = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
        paramBundle.setDuration(300L);
        findViewById(2131624251).startAnimation(paramBundle);
        paramBundle = getSharedPreferences("WIPE_OUT", 0).edit();
        paramBundle.putString("PANEL_VISIBLE", "CLONE_STAMP");
        paramBundle.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
        paramBundle.apply();
      }
    }
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 501: 
      return InitChoseResolutionDialog();
    case 502: 
      return InitChoseOpenSourceDialog();
    case 504: 
      return ShowFileSaveError();
    case 506: 
      return ShowSaveOrSendDialog();
    case 602: 
      return ShowNeedToSaveDialog();
    case 603: 
      return ShowNeedToSaveBeforeQuitDialog();
    case 605: 
      return ShowBuyFullVersionDialog();
    }
    return ShowHaveFoundApkDialog();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131689472, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    SystemOperations.ShowLog("touchetouch", "OnDestroy");
    TouchRetouchLib.destroyResources();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "super.onDestroy()");
    System.gc();
    System.exit(0);
    super.onDestroy();
  }
  
  public void onErrorWhilePick(String paramString)
  {
    Toast.makeText(this, getString(2131165325), 1).show();
    SetStartTimer();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    if (TouchRetouchLib.NeedToSave())
    {
      this.g = true;
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
    this.d.onPause();
  }
  
  public void onPicturePicked(String paramString1, String paramString2, int paramInt)
  {
    ProcessImagePath(paramString1, paramString2);
    SetStartTimer();
  }
  
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    int i = paramInt + 2;
    paramInt = i;
    if (i * 1.2D > 250.0D) {
      paramInt = 208;
    }
    DrawBrushSizeView(paramInt);
    TouchRetouchLib.SetBrushSize(paramInt);
  }
  
  protected void onRestart()
  {
    super.onRestart();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.d.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
    SystemOperations.ShowLog("touchretouch1", "Before check that is needed device");
    this.m_isUsePressureBrush = false;
    SeekBar localSeekBar = (SeekBar)findViewById(2131624302);
    if (this.m_isUsePressureBrush)
    {
      localSeekBar.setVisibility(8);
      return;
    }
    localSeekBar.setVisibility(0);
  }
  
  public void onStartPickFromCameraError(String paramString) {}
  
  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.m_lastSelectedTool != 2131624273) && (this.m_lastSelectedTool != 2131624248)) {
      findViewById(2131624241).setVisibility(0);
    }
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.m_lastSelectedTool != 2131624273) && (this.m_lastSelectedTool != 2131624248))
    {
      findViewById(2131624241).setVisibility(8);
      ShowBrushSizeWithFadeOut();
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (!this.m_isLongOperationStarted)
    {
      paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
      if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 2) || (paramMotionEvent.getAction() == 1) || (paramMotionEvent.getAction() == 3) || (paramMotionEvent.getAction() == 261) || (paramMotionEvent.getAction() == 262) || (paramMotionEvent.getAction() == 6) || (paramMotionEvent.getAction() == 5))
      {
        int j = paramMotionEvent.getPointerCount();
        if (this.m_lastPointerCount != j)
        {
          if (j == 2) {
            OnStartZoom();
          }
          if (j == 1) {
            OnFinishZoom();
          }
          this.m_lastPointerCount = j;
        }
        int i = 0;
        if (i < j)
        {
          AddToQueue(new MyMotionEvent(paramMotionEvent.getPointerId(i), paramMotionEvent.getAction(), (int)paramMotionEvent.getX(i), (int)paramMotionEvent.getY(i)));
          if ((paramMotionEvent.getAction() == 0) && (this.m_isUsePressureBrush)) {
            TouchRetouchLib.SetBrushSize(140);
          }
          if ((2 == paramMotionEvent.getAction()) && (IsPanelShown(302)) && ((int)paramMotionEvent.getY(i) > m_height - GetBottomPanelsHeight() - 15)) {
            HidePanel(302);
          }
          switch (paramMotionEvent.getAction())
          {
          }
          for (;;)
          {
            i += 1;
            break;
            int k = findViewById(2131624243).getVisibility();
            if ((paramView != null) && (paramView.equals("OBJECT_QUICK_BRUSH")) && (k == 4))
            {
              OnGoSelected();
            }
            else
            {
              if (System.currentTimeMillis() - m_last_touch_time < 500L)
              {
                OnStartZoom();
                a(TouchRetouchLib.GetZoom());
                OnFinishZoom();
              }
              m_last_touch_time = System.currentTimeMillis();
            }
          }
        }
      }
    }
    return true;
  }
  
  class ButtonsEvents
  {
    public static final int EVENT_REDO = 1;
    public static final int EVENT_SAVE_IMAGE = 5;
    public static final int EVENT_UNDO = 2;
    int a = 0;
    
    ButtonsEvents(int paramInt)
    {
      this.a = paramInt;
    }
  }
  
  class C11171
    implements Runnable
  {
    private int m_lastPanelVisibility;
    
    C11171() {}
    
    public void run()
    {
      boolean bool3 = false;
      Object localObject;
      boolean bool1;
      if (!TouchRetouchActivity.a(TouchRetouchActivity.this))
      {
        if (TouchRetouchActivity.this.f)
        {
          float f = TouchRetouchLib.GetZoom();
          if (TouchRetouchActivity.m_lastZoomValue != f)
          {
            TouchRetouchActivity.m_lastZoomValue = f;
            TouchRetouchActivity.this.a(f);
          }
        }
        if ((!TouchRetouchActivity.a(TouchRetouchActivity.this)) && (TouchRetouchActivity.b(TouchRetouchActivity.this)))
        {
          TouchRetouchActivity.c(TouchRetouchActivity.this);
          TouchRetouchActivity.this.findViewById(2131624306).setVisibility(4);
          TouchRetouchActivity.this.findViewById(2131624305).setVisibility(4);
          TouchRetouchActivity.a(TouchRetouchActivity.this, false);
        }
        if (TouchRetouchActivity.this.f)
        {
          localObject = TouchRetouchActivity.this;
          if (TouchRetouchLib.getUndoState() == 0) {
            break label249;
          }
          bool1 = true;
          if (TouchRetouchLib.getRedoState() == 0) {
            break label296;
          }
        }
      }
      label249:
      label291:
      label296:
      for (boolean bool2 = true;; bool2 = false)
      {
        ((TouchRetouchActivity)localObject).SetUndoRedoEnabled(bool1, bool2);
        bool1 = PreferenceManager.getDefaultSharedPreferences(TouchRetouchActivity.this).getBoolean("pref_check_hide_toolbars", true);
        int i;
        if ((TouchRetouchActivity.this.f) && (bool1))
        {
          i = TouchRetouchLib.getPanelsVisible();
          if (i != this.m_lastPanelVisibility)
          {
            localObject = TouchRetouchActivity.this;
            if (i != 0) {
              break label291;
            }
          }
        }
        for (bool1 = bool3;; bool1 = true)
        {
          ((TouchRetouchActivity)localObject).SetPanelsVisible(bool1);
          this.m_lastPanelVisibility = i;
          localObject = TouchRetouchActivity.this.GetFromFileSavedEventQueue();
          for (;;)
          {
            if (localObject != null)
            {
              TouchRetouchActivity.this.ShowFileSaved();
              localObject = TouchRetouchActivity.this.GetFromFileSavedEventQueue();
              continue;
              bool1 = false;
              break;
            }
          }
          for (localObject = TouchRetouchActivity.this.GetFromFullVersionEventQueue(); localObject != null; localObject = TouchRetouchActivity.this.GetFromFullVersionEventQueue()) {
            TouchRetouchActivity.this.showDialog(605);
          }
          return;
        }
      }
    }
  }
  
  class C11182
    implements Runnable
  {
    C11182() {}
    
    public void run()
    {
      View localView = TouchRetouchActivity.this.findViewById(2131624243);
      if (localView.getVisibility() == 0)
      {
        Animation localAnimation = AnimationUtils.loadAnimation(TouchRetouchActivity.this, 2131034127);
        localAnimation.setDuration(300L);
        localView.startAnimation(localAnimation);
        localView.setVisibility(4);
      }
    }
  }
  
  class C11193
    implements View.OnClickListener
  {
    C11193() {}
    
    public void onClick(View paramView)
    {
      TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, RemoveObjectTutorialsActivity.class));
    }
  }
  
  class C11204
    implements View.OnClickListener
  {
    C11204() {}
    
    public void onClick(View paramView)
    {
      TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, RemoveQuickTutorialsActivity.class));
    }
  }
  
  class C11215
    implements View.OnClickListener
  {
    C11215() {}
    
    public void onClick(View paramView)
    {
      TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, ClonetTutorialsActivity.class));
    }
  }
  
  class C11226
    extends TimerTask
  {
    C11226() {}
    
    public void run()
    {
      TouchRetouchActivity.d(TouchRetouchActivity.this);
    }
  }
  
  class C11237
    implements Runnable
  {
    C11237() {}
    
    public void run()
    {
      View localView = TouchRetouchActivity.this.findViewById(2131624241);
      if (localView.getVisibility() == 0)
      {
        Animation localAnimation = AnimationUtils.loadAnimation(TouchRetouchActivity.this, 2131034127);
        localAnimation.setDuration(300L);
        localView.startAnimation(localAnimation);
        localView.setVisibility(4);
      }
    }
  }
  
  class C11248
    implements DialogInterface.OnClickListener
  {
    C11248() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt) {}
  }
  
  class C11259
    implements DialogInterface.OnClickListener
  {
    C11259() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      TouchRetouchActivity.this.d();
    }
  }
  
  private static class ConfigChooser
    implements GLSurfaceView.EGLConfigChooser
  {
    private static int EGL_OPENGL_ES2_BIT = 4;
    private static int[] s_configAttribs2 = { 12324, 4, 12323, 4, 12322, 4, 12352, EGL_OPENGL_ES2_BIT, 12344 };
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    private int[] mValue = new int[1];
    
    ConfigChooser(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
      this.e = paramInt1;
      this.d = paramInt2;
      this.b = paramInt3;
      this.a = paramInt4;
      this.c = paramInt5;
      this.f = paramInt6;
    }
    
    private int findConfigAttrib(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig, int paramInt1, int paramInt2)
    {
      if (paramEGL10.eglGetConfigAttrib(paramEGLDisplay, paramEGLConfig, paramInt1, this.mValue)) {
        paramInt2 = this.mValue[0];
      }
      return paramInt2;
    }
    
    private void printConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      int[] arrayOfInt1 = new int[33];
      int[] tmp8_6 = arrayOfInt1;
      tmp8_6[0] = '〠';
      int[] tmp14_8 = tmp8_6;
      tmp14_8[1] = '〡';
      int[] tmp20_14 = tmp14_8;
      tmp20_14[2] = '〢';
      int[] tmp26_20 = tmp20_14;
      tmp26_20[3] = '〣';
      int[] tmp32_26 = tmp26_20;
      tmp32_26[4] = '〤';
      int[] tmp38_32 = tmp32_26;
      tmp38_32[5] = '〥';
      int[] tmp44_38 = tmp38_32;
      tmp44_38[6] = '〦';
      int[] tmp51_44 = tmp44_38;
      tmp51_44[7] = '〧';
      int[] tmp58_51 = tmp51_44;
      tmp58_51[8] = '〨';
      int[] tmp65_58 = tmp58_51;
      tmp65_58[9] = '〩';
      int[] tmp72_65 = tmp65_58;
      tmp72_65[10] = '〪';
      int[] tmp79_72 = tmp72_65;
      tmp79_72[11] = '〫';
      int[] tmp86_79 = tmp79_72;
      tmp86_79[12] = '〬';
      int[] tmp93_86 = tmp86_79;
      tmp93_86[13] = '〭';
      int[] tmp100_93 = tmp93_86;
      tmp100_93[14] = '〮';
      int[] tmp107_100 = tmp100_93;
      tmp107_100[15] = '〯';
      int[] tmp114_107 = tmp107_100;
      tmp114_107[16] = '〰';
      int[] tmp121_114 = tmp114_107;
      tmp121_114[17] = '〱';
      int[] tmp128_121 = tmp121_114;
      tmp128_121[18] = '〲';
      int[] tmp135_128 = tmp128_121;
      tmp135_128[19] = '〳';
      int[] tmp142_135 = tmp135_128;
      tmp142_135[20] = '〴';
      int[] tmp149_142 = tmp142_135;
      tmp149_142[21] = '〷';
      int[] tmp156_149 = tmp149_142;
      tmp156_149[22] = '〶';
      int[] tmp163_156 = tmp156_149;
      tmp163_156[23] = '〵';
      int[] tmp170_163 = tmp163_156;
      tmp170_163[24] = '〹';
      int[] tmp177_170 = tmp170_163;
      tmp177_170[25] = '〺';
      int[] tmp184_177 = tmp177_170;
      tmp184_177[26] = '〻';
      int[] tmp191_184 = tmp184_177;
      tmp191_184[27] = '〼';
      int[] tmp198_191 = tmp191_184;
      tmp198_191[28] = '〽';
      int[] tmp205_198 = tmp198_191;
      tmp205_198[29] = '〾';
      int[] tmp212_205 = tmp205_198;
      tmp212_205[30] = '〿';
      int[] tmp219_212 = tmp212_205;
      tmp219_212[31] = '぀';
      int[] tmp226_219 = tmp219_212;
      tmp226_219[32] = 'あ';
      tmp226_219;
      int[] arrayOfInt2 = new int[1];
      int i = 0;
      if (i < arrayOfInt1.length)
      {
        int j = arrayOfInt1[i];
        String str = new String[] { "EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT" }[i];
        if (paramEGL10.eglGetConfigAttrib(paramEGLDisplay, paramEGLConfig, j, arrayOfInt2)) {
          SystemOperations.ShowLog(TouchRetouchActivity.e(), String.format("  %s: %d\n", new Object[] { str, Integer.valueOf(arrayOfInt2[0]) }));
        }
        for (;;)
        {
          i += 1;
          break;
          while (paramEGL10.eglGetError() != 12288) {}
        }
      }
    }
    
    EGLConfig a(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig[] paramArrayOfEGLConfig)
    {
      int j = paramArrayOfEGLConfig.length;
      int i = 0;
      while (i < j)
      {
        EGLConfig localEGLConfig = paramArrayOfEGLConfig[i];
        int k = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12325, 0);
        int m = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12326, 0);
        if ((k >= this.c) && (m >= this.f))
        {
          k = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12324, 0);
          m = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12323, 0);
          int n = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12322, 0);
          int i1 = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12321, 0);
          if ((k == this.e) && (m == this.d) && (n == this.b) && (i1 == this.a)) {
            return localEGLConfig;
          }
        }
        i += 1;
      }
      return null;
    }
    
    public EGLConfig chooseConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay)
    {
      int[] arrayOfInt = new int[1];
      paramEGL10.eglChooseConfig(paramEGLDisplay, s_configAttribs2, null, 0, arrayOfInt);
      int i = arrayOfInt[0];
      if (i <= 0) {
        throw new IllegalArgumentException("No configs match configSpec");
      }
      EGLConfig[] arrayOfEGLConfig = new EGLConfig[i];
      paramEGL10.eglChooseConfig(paramEGLDisplay, s_configAttribs2, arrayOfEGLConfig, i, arrayOfInt);
      return a(paramEGL10, paramEGLDisplay, arrayOfEGLConfig);
    }
  }
  
  private static class ContextFactory
    implements GLSurfaceView.EGLContextFactory
  {
    private static int EGL_CONTEXT_CLIENT_VERSION = 12440;
    
    private ContextFactory() {}
    
    public EGLContext createContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      SystemOperations.ShowLog(TouchRetouchActivity.e(), "creating OpenGL ES 2.0 context");
      TouchRetouchActivity.a("Before eglCreateContext", paramEGL10);
      paramEGLDisplay = paramEGL10.eglCreateContext(paramEGLDisplay, paramEGLConfig, EGL10.EGL_NO_CONTEXT, new int[] { EGL_CONTEXT_CLIENT_VERSION, 2, 12344 });
      TouchRetouchActivity.a("After eglCreateContext", paramEGL10);
      return paramEGLDisplay;
    }
    
    public void destroyContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLContext paramEGLContext)
    {
      paramEGL10.eglDestroyContext(paramEGLDisplay, paramEGLContext);
    }
  }
  
  private class MyMotionEvent
  {
    int a;
    public int action;
    int b;
    public int id;
    
    MyMotionEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.id = paramInt1;
      this.action = paramInt2;
      this.a = paramInt3;
      this.b = paramInt4;
    }
  }
  
  private static class Renderer
    implements GLSurfaceView.Renderer
  {
    private TouchRetouchActivity mContext;
    private int start_x;
    private int start_y;
    
    Renderer(TouchRetouchActivity paramTouchRetouchActivity)
    {
      this.mContext = paramTouchRetouchActivity;
    }
    
    private void CheckButtonsEvents()
    {
      TouchRetouchActivity.ButtonsEvents localButtonsEvents = this.mContext.GetFromButtonsEventQueue();
      if (localButtonsEvents != null)
      {
        switch (localButtonsEvents.a)
        {
        }
        for (;;)
        {
          localButtonsEvents = this.mContext.GetFromButtonsEventQueue();
          break;
          TouchRetouchLib.OnRedoSelected();
          this.mContext.SetLongOperationFinished();
          continue;
          TouchRetouchLib.OnUndoSelected();
          this.mContext.SetLongOperationFinished();
          continue;
          if (!TouchRetouchLib.OnGoSelected()) {
            this.mContext.AddToFullVersionEventQueue(new Integer(1));
          }
          this.mContext.SetLongOperationFinished();
          continue;
          TouchRetouchLib.OnClearAllSelected();
          this.mContext.SetLongOperationFinished();
          continue;
          b();
          this.mContext.SetLongOperationFinished();
          continue;
          a();
          this.mContext.SetLongOperationFinished();
        }
      }
    }
    
    private void CheckMotionEvents()
    {
      Object localObject = this.mContext.GetFromQueue();
      int i = 0;
      while (localObject != null)
      {
        TouchRetouchActivity.MyMotionEvent localMyMotionEvent = this.mContext.GetFromQueue();
        i += 1;
        if ((i < 2) || (i % 15 == 0) || (((TouchRetouchActivity.MyMotionEvent)localObject).action != 2) || (localMyMotionEvent == null) || (localMyMotionEvent.action != 2)) {
          TouchRetouchLib.nativeTouch(((TouchRetouchActivity.MyMotionEvent)localObject).a, ((TouchRetouchActivity.MyMotionEvent)localObject).b, ((TouchRetouchActivity.MyMotionEvent)localObject).action, ((TouchRetouchActivity.MyMotionEvent)localObject).id);
        }
        localObject = localMyMotionEvent;
      }
    }
    
    private void CheckOpenJPGPictureEvent(GL10 paramGL10)
    {
      paramGL10 = this.mContext.GetFromJPGPictureQueue();
      if (paramGL10 != null)
      {
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "orient = " + TouchRetouchActivity.m_pic_orient);
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "scale = " + TouchRetouchActivity.m_pic_scale);
        TouchRetouchActivity.b = TouchRetouchActivity.correctWithDilnyk(TouchRetouchActivity.b, TouchRetouchActivity.m_pic_scale);
        TouchRetouchActivity.a = TouchRetouchActivity.correctWithDilnyk(TouchRetouchActivity.a, TouchRetouchActivity.m_pic_scale);
        int i = TouchRetouchActivity.GetOrientation(TouchRetouchActivity.m_pic_orient);
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "GetOrientation");
        TouchRetouchLib.splitImage3(paramGL10, TouchRetouchActivity.m_pic_scale, i);
        this.mContext.SetLongOperationFinished();
        this.mContext.f = true;
      }
    }
    
    private void CheckOpenPictureEvent(GL10 paramGL10)
    {
      Bitmap localBitmap = this.mContext.GetFromPictureQueue();
      if (localBitmap != null)
      {
        a(paramGL10, localBitmap, 512);
        localBitmap.recycle();
        this.mContext.SetLongOperationFinished();
        this.mContext.f = true;
      }
    }
    
    private void GLRedraw()
    {
      long l1 = System.currentTimeMillis();
      long l2 = TouchRetouchActivity.f();
      TouchRetouchActivity.a(l1);
      TouchRetouchLib.step((float)((l1 - l2) / 1000.0D));
    }
    
    private void InitCloneTextures(GL10 paramGL10)
    {
      Object localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inPreferredConfig = Bitmap.Config.ARGB_8888;
      Bitmap localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837616, (BitmapFactory.Options)localObject);
      IntBuffer localIntBuffer = IntBuffer.allocate(localBitmap.getWidth() * localBitmap.getHeight());
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int i = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837617, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int j = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837618, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int k = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837619, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int m = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837620, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int n = a(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localObject = BitmapFactory.decodeResource(this.mContext.getResources(), 2130837621, (BitmapFactory.Options)localObject);
      ((Bitmap)localObject).getPixels(localIntBuffer.array(), 0, ((Bitmap)localObject).getWidth(), 0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      int i1 = a(paramGL10, localIntBuffer, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      ((Bitmap)localObject).recycle();
      TouchRetouchLib.setCloneTextures(new int[] { i, j, k, m, n }, i1);
    }
    
    private void RotateArray(IntBuffer paramIntBuffer, int paramInt)
    {
      int i = 0;
      while (i < paramInt / 2)
      {
        int j = 0;
        while (j < paramInt / 2)
        {
          int k = i * paramInt + j;
          int m = j * paramInt + (paramInt - 1 - i);
          int n = (paramInt - 1 - i) * paramInt + (paramInt - 1 - j);
          int i1 = (paramInt - 1 - j) * paramInt + i;
          int i2 = paramIntBuffer.array()[k];
          paramIntBuffer.array()[k] = paramIntBuffer.array()[i1];
          paramIntBuffer.array()[i1] = paramIntBuffer.array()[n];
          paramIntBuffer.array()[n] = paramIntBuffer.array()[m];
          paramIntBuffer.array()[m] = i2;
          j += 1;
        }
        i += 1;
      }
    }
    
    int a(GL10 paramGL10, IntBuffer paramIntBuffer, int paramInt1, int paramInt2)
    {
      int[] arrayOfInt = new int[1];
      paramGL10.glGenTextures(1, arrayOfInt, 0);
      int j = arrayOfInt[0];
      paramGL10.glBindTexture(3553, j);
      paramGL10.glTexParameterf(3553, 10241, 9985.0F);
      paramGL10.glTexParameterf(3553, 10241, 9987.0F);
      paramGL10.glTexParameterf(3553, 10242, 10497.0F);
      paramGL10.glTexParameterf(3553, 10243, 10497.0F);
      int i = 0;
      while (i < paramIntBuffer.array().length)
      {
        int k = paramIntBuffer.get(i);
        paramIntBuffer.put(i, k >>> 16 & 0xFF | (k >>> 24 & 0xFF) << 24 | (k & 0xFF) << 16 | (k >>> 8 & 0xFF) << 8);
        i += 1;
      }
      paramGL10.glTexImage2D(3553, 0, 6408, paramInt1, paramInt2, 0, 6408, 5121, paramIntBuffer);
      return j;
    }
    
    void a()
    {
      Object localObject = TouchRetouchActivity.c();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setFlags(268435456);
      localIntent.setType("image/jpeg");
      localObject = Uri.fromFile((File)localObject);
      localIntent.putExtra("android.intent.extra.TEXT", Glob.app_name + " Create By : " + Glob.app_link);
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Uri = " + ((Uri)localObject).toString());
      localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject);
      this.mContext.startActivity(Intent.createChooser(localIntent, this.mContext.getResources().getString(2131165324)));
      TouchRetouchLib.ImageSaved();
      this.mContext.SetLongOperationFinished();
    }
    
    void a(GL10 paramGL10, Bitmap paramBitmap, int paramInt)
    {
      int i4 = paramBitmap.getWidth();
      int i5 = paramBitmap.getHeight();
      TouchRetouchActivity.b = i4;
      TouchRetouchActivity.a = i5;
      label65:
      int[] arrayOfInt;
      IntBuffer localIntBuffer;
      int i3;
      int j;
      if (i4 % (paramInt - 1) > 0)
      {
        i = i4 / (paramInt - 1) + 1;
        TouchRetouchActivity.m_tex_num_width = i;
        if (i5 % (paramInt - 1) <= 0) {
          break label549;
        }
        i = i5 / (paramInt - 1) + 1;
        TouchRetouchActivity.m_tex_num_height = i;
        arrayOfInt = new int[TouchRetouchActivity.m_tex_num_width * TouchRetouchActivity.m_tex_num_height];
        localIntBuffer = TouchRetouchActivity.m_ib;
        if (TouchRetouchActivity.m_pic_orient != 90) {
          break label622;
        }
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Orientation is vertical!!!");
        i3 = paramInt - (TouchRetouchActivity.m_tex_num_height * (paramInt - 1) - i5 + 1);
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "offset_x = " + (paramInt - (TouchRetouchActivity.m_tex_num_width * (paramInt - 1) - i4 + 1)) + ", offset_y = " + i3);
        i = 0;
        j = 0;
      }
      int k;
      label194:
      int m;
      for (;;)
      {
        if (j >= TouchRetouchActivity.m_tex_num_width) {
          break label605;
        }
        k = TouchRetouchActivity.m_tex_num_height;
        k -= 1;
        if (k >= 0)
        {
          int i6 = j * (paramInt - 1);
          label215:
          int n;
          label228:
          int i1;
          if (k == 0)
          {
            m = 0;
            if (k != 0) {
              break label578;
            }
            n = (paramInt - i3) * paramInt;
            if ((j + 1) * (paramInt - 1) < i4) {
              break label584;
            }
            i1 = paramInt - ((j + 1) * (paramInt - 1) - i4 + 1);
            label258:
            if (k != 0) {
              break label590;
            }
          }
          label549:
          label578:
          label584:
          label590:
          for (int i2 = i3;; i2 = paramInt)
          {
            localIntBuffer.clear();
            SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "j = " + k + ", i = " + j + "width = " + i4 + "height = " + i5);
            SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "start_x = " + i6 + " start_y = " + m + " draw_picture_size_x = " + i1 + " draw_picture_size_y = " + i2);
            paramBitmap.getPixels(localIntBuffer.array(), 0, 512, i6, m, i1, i2);
            localIntBuffer.clear();
            SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "start_x = " + i6 + " start_y = " + m + " draw_picture_size_x = " + i1 + " draw_picture_size_y = " + i2);
            paramBitmap.getPixels(localIntBuffer.array(), n, 512, i6, m, i1, i2);
            RotateArray(localIntBuffer, paramInt);
            arrayOfInt[i] = a(paramGL10, localIntBuffer, paramInt, paramInt);
            i += 1;
            k -= 1;
            break label194;
            i = i4 / (paramInt - 1);
            break;
            i = i5 / (paramInt - 1);
            break label65;
            m = (k - 1) * (paramInt - 1) + i3 - 1;
            break label215;
            n = 0;
            break label228;
            i1 = paramInt;
            break label258;
          }
        }
        j += 1;
      }
      label605:
      TouchRetouchLib.splitImage2(arrayOfInt, i5, i4, TouchRetouchActivity.m_tex_num_height, TouchRetouchActivity.m_tex_num_width, paramInt);
      return;
      label622:
      int i = 0;
      while (i < TouchRetouchActivity.m_tex_num_height)
      {
        j = 0;
        if (j < TouchRetouchActivity.m_tex_num_width)
        {
          this.start_x = ((paramInt - 1) * j);
          this.start_y = ((paramInt - 1) * i);
          if ((j + 1) * (paramInt - 1) >= i4)
          {
            k = paramInt - ((j + 1) * (paramInt - 1) - i4 + 1);
            label694:
            if ((i + 1) * (paramInt - 1) < i5) {
              break label855;
            }
          }
          label855:
          for (m = paramInt - ((i + 1) * (paramInt - 1) - i5 + 1);; m = paramInt)
          {
            localIntBuffer.clear();
            SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "start_x = " + this.start_x + " start_y = " + this.start_y + " draw_picture_size_x = " + k + " draw_picture_size_y = " + m);
            paramBitmap.getPixels(localIntBuffer.array(), 0, 512, this.start_x, this.start_y, k, m);
            arrayOfInt[(TouchRetouchActivity.m_tex_num_width * i + j)] = a(paramGL10, localIntBuffer, paramInt, paramInt);
            j += 1;
            break;
            k = paramInt;
            break label694;
          }
        }
        i += 1;
      }
      TouchRetouchLib.splitImage2(arrayOfInt, i4, i5, TouchRetouchActivity.m_tex_num_width, TouchRetouchActivity.m_tex_num_height, paramInt);
    }
    
    void b()
    {
      File localFile = new File(SystemOperations.GetTouchRetouchFolderPath(), TouchRetouchActivity.e(this.mContext));
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Saving to folder " + localFile.getAbsolutePath());
      int j = TouchRetouchActivity.GetOrientation(TouchRetouchActivity.m_pic_orient);
      int i;
      if (j % 2 == 1)
      {
        i = TouchRetouchActivity.a;
        if (j % 2 != 1) {
          break label179;
        }
      }
      label179:
      for (j = TouchRetouchActivity.b;; j = TouchRetouchActivity.a)
      {
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "save image func");
        TouchRetouchLib.SaveImage(localFile.getAbsolutePath(), i, j);
        Log.e(TouchRetouchActivity.e(), "SaveImage: " + TouchRetouchActivity.TOUCHRETOUCH_FOLDER);
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "before save func");
        new SingleMediaScanner(this.mContext, localFile);
        this.mContext.AddToFileSavedEventQueue(new Integer(1));
        TouchRetouchLib.ImageSaved();
        this.mContext.SetLongOperationFinished();
        return;
        i = TouchRetouchActivity.b;
        break;
      }
    }
    
    void c()
    {
      TouchRetouchLib.loadResourcesDoubleTexFrag(SystemOperations.GetStringForResource(2131099649, this.mContext));
      TouchRetouchLib.loadResourcesDoubleTexVert(SystemOperations.GetStringForResource(2131099650, this.mContext));
      TouchRetouchLib.loadResourcesNoTexFrag(SystemOperations.GetStringForResource(2131099651, this.mContext));
      TouchRetouchLib.loadResourcesNoTexVert(SystemOperations.GetStringForResource(2131099652, this.mContext));
      TouchRetouchLib.loadResourcesSingleTexFrag(SystemOperations.GetStringForResource(2131099655, this.mContext));
      TouchRetouchLib.loadResourcesSingleTexVert(SystemOperations.GetStringForResource(2131099656, this.mContext));
    }
    
    public void onDrawFrame(GL10 paramGL10)
    {
      paramGL10.glClearColor(1.0F, 1.0F, 1.0F, 0.0F);
      CheckOpenPictureEvent(paramGL10);
      CheckOpenJPGPictureEvent(paramGL10);
      CheckButtonsEvents();
      CheckMotionEvents();
      GLRedraw();
    }
    
    public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
    {
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Surface changed");
      TouchRetouchLib.surfaceChanged(this.mContext.getWindowManager().getDefaultDisplay().getOrientation());
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "orientation = " + this.mContext.getWindowManager().getDefaultDisplay().getOrientation());
      paramGL10 = this.mContext.findViewById(2131624037);
      View localView1 = this.mContext.findViewById(2131624246);
      View localView2 = this.mContext.findViewById(2131624222);
      switch (this.mContext.getWindowManager().getDefaultDisplay().getOrientation())
      {
      }
      for (;;)
      {
        TouchRetouchLib.SetMargins(paramGL10.getHeight(), localView1.getHeight());
        return;
        TouchRetouchLib.SetScreenSize(localView2.getWidth(), localView2.getHeight());
        continue;
        TouchRetouchLib.SetScreenSize(localView2.getHeight(), localView2.getWidth());
      }
    }
    
    public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
    {
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Surface created");
      c();
      TouchRetouchLib.SetMargins(this.mContext.findViewById(2131624037).getHeight(), this.mContext.findViewById(2131624246).getHeight());
      TouchRetouchLib.initResources(this.mContext.getWindowManager().getDefaultDisplay().getOrientation());
      InitCloneTextures(paramGL10);
      TouchRetouchActivity.f(this.mContext);
      this.mContext.SetLastEditedTool();
    }
    
    class SingleMediaScanner
      implements MediaScannerConnection.MediaScannerConnectionClient
    {
      private File mFile;
      private MediaScannerConnection mMs;
      
      SingleMediaScanner(Context paramContext, File paramFile)
      {
        this.mFile = paramFile;
        this.mMs = new MediaScannerConnection(paramContext, this);
        this.mMs.connect();
      }
      
      public void onMediaScannerConnected()
      {
        this.mMs.scanFile(this.mFile.getAbsolutePath(), null);
      }
      
      public void onScanCompleted(String paramString, Uri paramUri)
      {
        this.mMs.disconnect();
      }
    }
  }
}
