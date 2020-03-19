package cornera.touchretouch.Activity;

import android.annotation.SuppressLint;
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
import android.preference.PreferenceManager;
import android.support.v4.graphics.drawable.DrawableCompat;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import cornera.touchretouch.Subfile.Glob;
import cornera.touchretouch.Subfile.ImagePicker;
import cornera.touchretouch.Subfile.ImagePickerInterface;
import cornera.touchretouch.Subfile.SystemOperations;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.IntBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
  static final int HIGHEST_QUALITY_WIDTH = 5120;
  static final int HIGH_QUALITY_WIDTH = 2560;
  static final int LOW_QALITY_WIDTH = 640;
  static final int MEDIUN_QUALITY_WIDTH = 1280;
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
  private static boolean atLeastOneImageLoaded = false;
  static int m_cur_image_height;
  static int m_cur_image_width;
  private static int m_height;
  public static IntBuffer m_ib;
  private static int m_lastPanelShown;
  public static float m_lastZoomValue;
  private static long m_last_time;
  private static long m_last_touch_time;
  public static BitmapFactory.Options m_opt;
  public static int m_pic_orient;
  public static String m_pic_path;
  public static int m_pic_scale;
  public static int m_pic_type;
  public static int m_tex_num_height;
  public static int m_tex_num_width;
  public static int m_whyAppIsCracked;
  private static int m_width;
  RelativeLayout BtnGo;
  private Runnable Timer_Tick = new Runnable()
  {
    private int m_lastPanelVisibility;
    
    public void run()
    {
      if (!TouchRetouchActivity.this.m_isLongOperationStarted)
      {
        if (TouchRetouchActivity.this.m_isPictureLoaded)
        {
          float f = TouchRetouchLib.GetZoom();
          if (TouchRetouchActivity.m_lastZoomValue != f)
          {
            TouchRetouchActivity.m_lastZoomValue = f;
            TouchRetouchActivity.this.DrawZoomValue(f);
          }
        }
        boolean bool1 = TouchRetouchActivity.this.m_isLongOperationStarted;
        boolean bool3 = false;
        if ((!bool1) && (TouchRetouchActivity.this.m_isLongOperationViewVisible))
        {
          TouchRetouchActivity.this.EnableAllButtons();
          TouchRetouchActivity.this.findViewById(2131296583).setVisibility(4);
          TouchRetouchActivity.this.findViewById(2131296584).setVisibility(4);
          TouchRetouchActivity.access$102(TouchRetouchActivity.this, false);
        }
        if (TouchRetouchActivity.this.m_isPictureLoaded)
        {
          localObject = TouchRetouchActivity.this;
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
          ((TouchRetouchActivity)localObject).SetUndoRedoEnabled(bool1, bool2);
        }
        bool1 = PreferenceManager.getDefaultSharedPreferences(TouchRetouchActivity.this).getBoolean("pref_check_hide_toolbars", true);
        if ((TouchRetouchActivity.this.m_isPictureLoaded) && (bool1))
        {
          int i = TouchRetouchLib.getPanelsVisible();
          if (i != this.m_lastPanelVisibility)
          {
            localObject = TouchRetouchActivity.this;
            if (i == 0) {
              bool1 = bool3;
            } else {
              bool1 = true;
            }
            ((TouchRetouchActivity)localObject).SetPanelsVisible(bool1);
          }
          this.m_lastPanelVisibility = i;
        }
        for (Object localObject = TouchRetouchActivity.this.GetFromFileSavedEventQueue(); localObject != null; localObject = TouchRetouchActivity.this.GetFromFileSavedEventQueue()) {
          TouchRetouchActivity.this.ShowFileSaved();
        }
        for (localObject = TouchRetouchActivity.this.GetFromFullVersionEventQueue(); localObject != null; localObject = TouchRetouchActivity.this.GetFromFullVersionEventQueue()) {
          TouchRetouchActivity.this.showDialog(605);
        }
      }
    }
  };
  private TranslateAnimation animationZoom;
  private Queue<ButtonsEvents> mButtonsEventQueue;
  private Queue<Integer> mFileSavedQueue;
  private Queue<Integer> mFullVersionQueue;
  private InterstitialAd mInterstitialAdMob;
  private Queue<String> mJPGPictureQueue;
  private Queue<Bitmap> mPictureQueue;
  private Queue<MyMotionEvent> mQueue;
  GLSurfaceView mView;
  private boolean m_addedPanel_was_visible = false;
  private Bitmap m_bm;
  private BitmapFactory.Options m_btnPreferedOpt;
  boolean m_cannot_open_original = false;
  int m_curToolTipId;
  private String m_current_file_name;
  private ExifInterface m_exif;
  private String m_found_apk;
  private boolean m_hasMultitouch;
  private ImagePicker m_imgPicker;
  private boolean m_isLongOperationStarted;
  private boolean m_isLongOperationViewVisible;
  protected boolean m_isPictureLoaded = false;
  private boolean m_isUsePressureBrush = false;
  private int m_lastPointerCount = 0;
  private int m_lastSelectedCloneMode;
  private int m_lastSelectedFlipMode;
  private int m_lastSelectedTool = -1;
  private boolean m_myTimerStarted = false;
  protected boolean m_quit_pressed = false;
  private boolean m_went_from_first_view;
  private boolean m_zoom_started = false;
  public Timer myTimer;
  ImageView vv_info;
  
  public TouchRetouchActivity() {}
  
  private Bitmap DecodeBitmapFile(String paramString, BitmapFactory.Options paramOptions)
  {
    Object localObject = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("trying to decode bitmap file ");
    localStringBuilder.append(paramOptions.inSampleSize);
    SystemOperations.ShowLog((String)localObject, localStringBuilder.toString());
    try
    {
      localObject = BitmapFactory.decodeFile(paramString, paramOptions);
      return localObject;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      for (;;) {}
    }
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "Out of memory error :(");
    paramOptions.inSampleSize *= 2;
    this.m_cannot_open_original = true;
    localObject = TOUCHRETOUCH_FOLDER;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Set it twice less with opt.inSampleSize ");
    localStringBuilder.append(paramOptions.inSampleSize);
    SystemOperations.ShowLog((String)localObject, localStringBuilder.toString());
    return DecodeBitmapFile(paramString, paramOptions);
  }
  
  private void DisableAllButtons()
  {
    SetButtonLayoutDisabled(2131296277);
    SetButtonLayoutDisabled(2131296282);
    SetButtonLayoutDisabled(2131296279);
    SetButtonLayoutDisabled(2131296275);
    SetButtonLayoutDisabled(2131296258);
    SetButtonLayoutDisabled(2131296278);
    SetButtonLayoutDisabled(2131296276);
    SetButtonLayoutDisabled(2131296269);
    SetButtonLayoutDisabled(2131296274);
    SetButtonLayoutDisabled(2131296260);
    SetButtonLayoutDisabled(2131296280);
    SetButtonDisabled(2131296259);
    SetButtonLayoutDisabled(2131296268);
    SetButtonLayoutDisabled(2131296262);
    SetButtonLayoutDisabled(2131296261);
    SetButtonDisabled(2131296263);
    SetButtonDisabled(2131296264);
    SetButtonDisabled(2131296265);
    SetButtonDisabled(2131296266);
    SetButtonDisabled(2131296267);
    SetButtonDisabled(2131296270);
    SetButtonDisabled(2131296271);
    SetButtonDisabled(2131296272);
    SetButtonDisabled(2131296273);
  }
  
  private void EnableAllButtons()
  {
    SetButtonLayoutEnabled(2131296277);
    SetButtonLayoutEnabled(2131296282);
    SetButtonLayoutEnabled(2131296279);
    SetButtonLayoutEnabled(2131296275);
    SetButtonLayoutEnabled(2131296258);
    SetButtonLayoutEnabled(2131296278);
    SetButtonLayoutEnabled(2131296276);
    SetButtonLayoutEnabled(2131296269);
    SetButtonLayoutEnabled(2131296274);
    SetButtonLayoutEnabled(2131296260);
    SetButtonLayoutEnabled(2131296280);
    SetButtonEnabled(2131296259);
    SetButtonLayoutEnabled(2131296268);
    SetButtonLayoutEnabled(2131296262);
    SetButtonLayoutEnabled(2131296261);
    SetButtonLayoutEnabled(2131296371);
    SetButtonLayoutEnabled(2131296372);
    SetButtonLayoutEnabled(2131296373);
    SetButtonLayoutEnabled(2131296374);
    SetButtonLayoutEnabled(2131296375);
    SetButtonLayoutEnabled(2131296441);
    SetButtonLayoutEnabled(2131296442);
    SetButtonLayoutEnabled(2131296443);
    SetButtonLayoutEnabled(2131296444);
  }
  
  private int GetBottomPanelsHeight()
  {
    return findViewById(2131296349).getHeight() + findViewById(2131296329).getHeight();
  }
  
  private int GetBrushSize()
  {
    return ((SeekBar)findViewById(2131296620)).getProgress();
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
      for (;;) {}
    }
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "exif exception");
    return null;
  }
  
  private int GetDrawableForBtn(int paramInt)
  {
    switch (paramInt)
    {
    case 2131296259: 
    case 2131296261: 
    case 2131296262: 
    case 2131296268: 
    case 2131296274: 
    case 2131296277: 
    default: 
      return 0;
    case 2131296278: 
      return 2131230827;
    case 2131296276: 
      return 2131230880;
    case 2131296275: 
      return 2131230902;
    case 2131296273: 
      return 2131230839;
    case 2131296272: 
      return 2131230838;
    case 2131296271: 
      return 2131230837;
    case 2131296270: 
      return 2131230836;
    case 2131296269: 
      return 2131230868;
    case 2131296267: 
      return 2131230834;
    case 2131296266: 
      return 2131230833;
    case 2131296265: 
      return 2131230832;
    case 2131296264: 
      return 2131230831;
    case 2131296263: 
      return 2131230830;
    case 2131296260: 
      return 2131230847;
    }
    return 2131230827;
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
    case 302: 
      return findViewById(2131296329);
    case 301: 
      return findViewById(2131296370);
    }
    return findViewById(2131296440);
  }
  
  private CharSequence[] GetSelectionItems()
  {
    int n;
    if (m_cur_image_width > m_cur_image_height) {
      n = m_cur_image_width;
    } else {
      n = m_cur_image_height;
    }
    int i = GetOrientation(m_pic_orient);
    int k = 2;
    i %= 2;
    int m = 1;
    int i1;
    if (i == 1) {
      i1 = m_cur_image_height;
    } else {
      i1 = m_cur_image_width;
    }
    int i2;
    if (i == 1) {
      i2 = m_cur_image_width;
    } else {
      i2 = m_cur_image_height;
    }
    Object localObject = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bigSide = ");
    localStringBuilder.append(n);
    SystemOperations.ShowLog((String)localObject, localStringBuilder.toString());
    localObject = TOUCHRETOUCH_FOLDER;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("m_cur_image_width = ");
    localStringBuilder.append(m_cur_image_width);
    localStringBuilder.append(" m_cur_image_height = ");
    localStringBuilder.append(m_cur_image_height);
    SystemOperations.ShowLog((String)localObject, localStringBuilder.toString());
    if (n >= 640) {
      j = 1;
    } else {
      j = 0;
    }
    i = j;
    if (n >= 1280) {
      i = j + 1;
    }
    int j = i;
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
    localObject = TOUCHRETOUCH_FOLDER;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("number of proposals = ");
    localStringBuilder.append(j);
    SystemOperations.ShowLog((String)localObject, localStringBuilder.toString());
    localObject = new CharSequence[j];
    if (!this.m_cannot_open_original)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(getString(2131755090));
      localStringBuilder.append(" (");
      localStringBuilder.append(i1);
      localStringBuilder.append("x");
      localStringBuilder.append(i2);
      localStringBuilder.append(")");
      localObject[0] = localStringBuilder.toString();
    }
    else
    {
      k = 1;
      m = 0;
    }
    i = k;
    j = m;
    if (correctWithDilnyk(n, k) >= 640)
    {
      if (this.m_cannot_open_original)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(getString(2131755087));
        localStringBuilder.append(" (");
        localStringBuilder.append(correctWithDilnyk(i1, k));
        localStringBuilder.append("x");
        localStringBuilder.append(correctWithDilnyk(i2, k));
        localStringBuilder.append(")");
        localObject[m] = localStringBuilder.toString();
      }
      else
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(GetStringForWidth(correctWithDilnyk(n, k)));
        localStringBuilder.append(" (");
        localStringBuilder.append(correctWithDilnyk(i1, k));
        localStringBuilder.append("x");
        localStringBuilder.append(correctWithDilnyk(i2, k));
        localStringBuilder.append(")");
        localObject[m] = localStringBuilder.toString();
      }
      j = m + 1;
      i = k * 2;
    }
    m = i;
    k = j;
    if (correctWithDilnyk(n, i) >= 640)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(GetStringForWidth(correctWithDilnyk(n, i)));
      localStringBuilder.append(" (");
      localStringBuilder.append(correctWithDilnyk(i1, i));
      localStringBuilder.append("x");
      localStringBuilder.append(correctWithDilnyk(i2, i));
      localStringBuilder.append(")");
      localObject[j] = localStringBuilder.toString();
      k = j + 1;
      m = i * 2;
    }
    if (correctWithDilnyk(n, m) >= 640)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(GetStringForWidth(correctWithDilnyk(n, m)));
      localStringBuilder.append(" (");
      localStringBuilder.append(correctWithDilnyk(i1, m));
      localStringBuilder.append("x");
      localStringBuilder.append(correctWithDilnyk(i2, m));
      localStringBuilder.append(")");
      localObject[k] = localStringBuilder.toString();
    }
    return localObject;
  }
  
  private String GetStringForWidth(int paramInt)
  {
    if (paramInt >= 5120) {
      return getString(2131755087);
    }
    if (paramInt >= 2560) {
      return getString(2131755086);
    }
    if (paramInt >= 1280) {
      return getString(2131755089);
    }
    if (paramInt >= 640) {
      return getString(2131755088);
    }
    return null;
  }
  
  private void HaveFoundApk()
  {
    showDialog(606);
  }
  
  private void HideAllAddedButtons()
  {
    findViewById(2131296259).setVisibility(8);
    findViewById(2131296268).setVisibility(8);
    findViewById(2131296262).setVisibility(8);
    findViewById(2131296261).setVisibility(8);
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
    localTextView.setText(2131755112);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099678));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2130903040, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (paramAnonymousInt != 0) {
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
    localTextView.setText(getString(2131755113));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099784));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(GetSelectionItems(), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 3: 
          TouchRetouchActivity.this.StartLongOperation();
          paramAnonymousDialogInterface = TouchRetouchActivity.m_opt;
          paramAnonymousDialogInterface.inSampleSize *= 8;
          TouchRetouchActivity.this.m_bm.recycle();
          TouchRetouchActivity.access$1402(TouchRetouchActivity.this, TouchRetouchActivity.this.DecodeBitmapFile(TouchRetouchActivity.m_pic_path, TouchRetouchActivity.m_opt));
          TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.this.m_bm);
          return;
        case 2: 
          TouchRetouchActivity.this.StartLongOperation();
          paramAnonymousDialogInterface = TouchRetouchActivity.m_opt;
          paramAnonymousDialogInterface.inSampleSize *= 4;
          TouchRetouchActivity.this.m_bm.recycle();
          TouchRetouchActivity.access$1402(TouchRetouchActivity.this, TouchRetouchActivity.this.DecodeBitmapFile(TouchRetouchActivity.m_pic_path, TouchRetouchActivity.m_opt));
          TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.this.m_bm);
          return;
        case 1: 
          TouchRetouchActivity.this.StartLongOperation();
          paramAnonymousDialogInterface = TouchRetouchActivity.m_opt;
          paramAnonymousDialogInterface.inSampleSize *= 2;
          TouchRetouchActivity.this.m_bm.recycle();
          TouchRetouchActivity.access$1402(TouchRetouchActivity.this, TouchRetouchActivity.this.DecodeBitmapFile(TouchRetouchActivity.m_pic_path, TouchRetouchActivity.m_opt));
          TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.this.m_bm);
          return;
        }
        TouchRetouchActivity.this.StartLongOperation();
        TouchRetouchActivity.this.AddToPictureQueue(TouchRetouchActivity.this.m_bm);
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
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), i);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    this.m_lastSelectedTool = 2131296258;
    TouchRetouchLib.SetEditTool(1, GetBrushSize());
    ShowBrushSizeWithFadeOut();
  }
  
  private void OnCloneFlipSelected()
  {
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), i);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_MIRRORING");
    localEditor.apply();
    HidePanel(300);
    HidePanel(301);
    HidePanel(302);
  }
  
  private void OnCloneFlipTypeSelected(int paramInt)
  {
    String str = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Flip ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" selected");
    SystemOperations.ShowLog(str, localStringBuilder.toString());
    int i = paramInt - 2131296270;
    HidePanel(300);
    this.m_lastSelectedFlipMode = paramInt;
    SetImageForButton(2131296475, paramInt);
    TouchRetouchLib.SetCloneStampType(this.m_lastSelectedCloneMode - 2131296263, i);
    str = TOUCHRETOUCH_FOLDER;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("flip mode i = ");
    localStringBuilder.append(i);
    localStringBuilder.append(" selected");
    SystemOperations.ShowLog(str, localStringBuilder.toString());
  }
  
  private void OnCloneMaskSelected()
  {
    HidePanel(300);
    HidePanel(301);
    HidePanel(302);
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), i);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_HARDNESS");
    localEditor.apply();
  }
  
  private void OnCloneModeTypeSelected(int paramInt)
  {
    int i = paramInt - 2131296263;
    HidePanel(301);
    this.m_lastSelectedCloneMode = paramInt;
    SetImageForButton(2131296476, paramInt);
    TouchRetouchLib.SetCloneStampType(i, this.m_lastSelectedFlipMode - 2131296270);
    String str = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("clone mode i = ");
    localStringBuilder.append(i);
    localStringBuilder.append(" selected");
    SystemOperations.ShowLog(str, localStringBuilder.toString());
  }
  
  private void OnCloneSelected()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    findViewById(2131296257).setVisibility(8);
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), i);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), i);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    HidePanel(300);
    HidePanel(301);
    findViewById(2131296259).setVisibility(8);
    if ((IsPanelShown(302)) && (m_lastPanelShown == 3))
    {
      HidePanel(302);
      HideAllAddedButtons();
    }
    else
    {
      ShowCloneAddedButtons();
      ShowPanel(302);
      m_lastPanelShown = 3;
    }
    this.m_lastSelectedTool = 2131296260;
    TouchRetouchLib.SetEditTool(5, GetBrushSize());
    ShowBrushSizeWithFadeOut();
  }
  
  private void OnCloneTargetSelected()
  {
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), i);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
    localEditor.apply();
    HidePanel(300);
    HidePanel(301);
    TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131296620)).getProgress());
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
    findViewById(2131296257).setVisibility(8);
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), i);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    this.m_lastSelectedTool = 2131296269;
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
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), i);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    this.m_lastSelectedTool = 2131296275;
    TouchRetouchLib.SetEditTool(0, GetBrushSize());
  }
  
  private void OnMoveSelected(boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = getSharedPreferences("WIPE_OUT", 0).edit();
    localEditor.putString("OBJECT_SELECT", "MOVE");
    localEditor.apply();
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), j);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), i);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    changeTint((ImageView)findViewById(2131296485), j);
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    HidePanel(301);
    HidePanel(300);
    HidePanel(302);
    this.m_lastSelectedTool = 2131296276;
    TouchRetouchLib.SetEditTool(3, GetBrushSize());
  }
  
  private void OnOpenSelected()
  {
    HidePanel(302);
    HidePanel(301);
    HidePanel(300);
    if (TouchRetouchLib.NeedToSave())
    {
      showDialog(602);
      return;
    }
    showDialog(502);
  }
  
  private boolean OnQuickBrushSelected()
  {
    findViewById(2131296274).setVisibility(8);
    int i = getResources().getColor(2131099692);
    int j = getResources().getColor(2131099784);
    changeTint((ImageView)findViewById(2131296480), j);
    changeTint((ImageView)findViewById(2131296472), i);
    changeTint((ImageView)findViewById(2131296483), i);
    changeTint((ImageView)findViewById(2131296473), j);
    changeTint((ImageView)findViewById(2131296482), j);
    changeTint((ImageView)findViewById(2131296478), j);
    changeTint((ImageView)findViewById(2131296481), j);
    changeTint((ImageView)findViewById(2131296489), j);
    changeTint((ImageView)findViewById(2131296484), j);
    changeTint((ImageView)findViewById(2131296486), j);
    changeTint((ImageView)findViewById(2131296485), j);
    changeTint((ImageView)findViewById(2131296474), j);
    changeTint((ImageView)findViewById(2131296475), j);
    changeTint((ImageView)findViewById(2131296476), j);
    ((TextView)findViewById(2131296688)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296691)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296690)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296698)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296693)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296696)).setTextColor(getResources().getColor(2131099692));
    ((TextView)findViewById(2131296687)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296689)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296695)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296694)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296700)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296697)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296692)).setTextColor(getResources().getColor(2131099784));
    ((TextView)findViewById(2131296699)).setTextColor(getResources().getColor(2131099784));
    this.m_lastSelectedTool = 2131296278;
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
    DrawZoomValue(TouchRetouchLib.GetZoom());
    OnFinishZoom();
  }
  
  private void ProcessImagePath(String paramString1, String paramString2)
  {
    OnMoveSelected(false);
    this.m_exif = GetCurrentExif(paramString1);
    StartLongOperation();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "ProcessImagePath");
    m_pic_path = paramString1;
    if (paramString2 != null)
    {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "orient not null!!!");
      m_pic_orient = Integer.parseInt(paramString2);
    }
    else
    {
      SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "orient is null!!!");
      m_pic_orient = 0;
    }
    this.m_current_file_name = new File(paramString1).getName();
    ShowMemoryInfo();
    if (m_opt == null) {
      m_opt = new BitmapFactory.Options();
    }
    m_opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
    m_opt.inSampleSize = 1;
    this.m_cannot_open_original = false;
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "init config values");
    m_opt.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString1, m_opt);
    paramString1 = TOUCHRETOUCH_FOLDER;
    paramString2 = new StringBuilder();
    paramString2.append("mime type = ");
    paramString2.append(m_opt.outMimeType);
    SystemOperations.ShowLog(paramString1, paramString2.toString());
    if (m_opt.outMimeType == null)
    {
      Toast.makeText(this, getString(2131755132), 1).show();
      setResult(0);
      finish();
      return;
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
    paramString1 = TOUCHRETOUCH_FOLDER;
    paramString2 = new StringBuilder();
    paramString2.append("opt.inSampleSize = ");
    paramString2.append(m_opt.inSampleSize);
    SystemOperations.ShowLog(paramString1, paramString2.toString());
    m_cur_image_width = m_opt.outWidth;
    m_cur_image_height = m_opt.outHeight;
    paramString1 = TOUCHRETOUCH_FOLDER;
    paramString2 = new StringBuilder();
    paramString2.append("m_cur_image_width = ");
    paramString2.append(m_cur_image_width);
    paramString2.append(", m_cur_image_height = ");
    paramString2.append(m_cur_image_height);
    SystemOperations.ShowLog(paramString1, paramString2.toString());
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    paramString1 = TOUCHRETOUCH_FOLDER;
    paramString2 = new StringBuilder();
    paramString2.append("alwaysMaxRes = ");
    paramString2.append(bool);
    SystemOperations.ShowLog(paramString1, paramString2.toString());
    if (bool)
    {
      AddToPictureQueue(this.m_bm);
    }
    else
    {
      this.m_went_from_first_view = false;
      ProcessChoseResolutionClick(0);
      atLeastOneImageLoaded = true;
    }
    ShowMemoryInfo();
  }
  
  private void ProcessJPGImage()
  {
    m_cur_image_width = m_opt.outWidth;
    m_cur_image_height = m_opt.outHeight;
    String str = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bm width = ");
    localStringBuilder.append(m_opt.outWidth);
    localStringBuilder.append(", bm height = ");
    localStringBuilder.append(m_opt.outHeight);
    localStringBuilder.append(" m_opt.outMimeType = ");
    localStringBuilder.append(m_opt.outMimeType);
    SystemOperations.ShowLog(str, localStringBuilder.toString());
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_check_always_max_res", false);
    str = TOUCHRETOUCH_FOLDER;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("alwaysMaxRes = ");
    localStringBuilder.append(bool);
    SystemOperations.ShowLog(str, localStringBuilder.toString());
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
  
  protected static File SaveImageToTempFolder()
  {
    File localFile = new File(SystemOperations.GetTempFolderPath(), "share_t.jpg");
    if (localFile != null)
    {
      String str = TOUCHRETOUCH_FOLDER;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Saving to folder ");
      localStringBuilder.append(localFile.getAbsolutePath());
      SystemOperations.ShowLog(str, localStringBuilder.toString());
      int i = GetOrientation(m_pic_orient);
      str = localFile.getAbsolutePath();
      int j = i % 2;
      if (j == 1) {
        i = m_cur_image_height;
      } else {
        i = m_cur_image_width;
      }
      if (j == 1) {
        j = m_cur_image_width;
      } else {
        j = m_cur_image_height;
      }
      TouchRetouchLib.SaveImage(str, i, j);
    }
    return localFile;
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
    findViewById(2131296259).setVisibility(0);
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
    changeTint(paramImageView, getResources().getColor(2131099692));
  }
  
  private void ShowBrushSizeWithFadeOut()
  {
    View localView = findViewById(2131296257);
    if (localView.getVisibility() == 4)
    {
      localView.setVisibility(0);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          View localView = TouchRetouchActivity.this.findViewById(2131296257);
          if (localView.getVisibility() == 0)
          {
            Animation localAnimation = AnimationUtils.loadAnimation(TouchRetouchActivity.this, 2130771986);
            localAnimation.setDuration(300L);
            localView.startAnimation(localAnimation);
            localView.setVisibility(4);
          }
        }
      }, 300L);
    }
  }
  
  private Dialog ShowBuyFullVersionDialog()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131755134));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099784));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131755129)).setPositiveButton(getString(2131755127), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = TouchRetouchActivity.this;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("http://market.android.com/details?id=");
        localStringBuilder.append(TouchRetouchActivity.this.getPackageName());
        paramAnonymousDialogInterface.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      }
    }).setNegativeButton(getString(2131755125), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private void ShowCloneAddedButtons()
  {
    findViewById(2131296268).setVisibility(0);
    findViewById(2131296262).setVisibility(0);
    findViewById(2131296261).setVisibility(0);
  }
  
  private Dialog ShowFileExist()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131755139));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099784));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131755140)).setPositiveButton(getString(2131755127), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.StartLongOperation();
        TouchRetouchActivity.this.AddToButtonsEventQueue(new TouchRetouchActivity.ButtonsEvents(TouchRetouchActivity.this, 5));
      }
    }).setNegativeButton(getString(2131755125), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private void ShowFileNameEnterDialog()
  {
    Object localObject1 = Environment.getExternalStorageDirectory();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(((File)localObject1).getAbsolutePath());
    ((StringBuilder)localObject2).append("/");
    ((StringBuilder)localObject2).append(Glob.Edit_Folder_name);
    new File(((StringBuilder)localObject2).toString()).mkdirs();
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append(Environment.getExternalStorageDirectory());
    ((StringBuilder)localObject2).append(File.separator);
    ((StringBuilder)localObject2).append(TOUCHRETOUCH_FOLDER);
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
    ((StringBuilder)localObject4).append(Glob.Edit_Folder_name);
    ((StringBuilder)localObject4).append("/");
    ((StringBuilder)localObject4).append((String)localObject2);
    localObject4 = ((StringBuilder)localObject4).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(((File)localObject1).getAbsolutePath());
    localStringBuilder.append("/");
    localStringBuilder.append(Glob.Edit_Folder_name);
    localStringBuilder.append("/");
    localStringBuilder.append((String)localObject2);
    Glob.edit_image_uri = localStringBuilder.toString();
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
    this.m_current_file_name = ((String)localObject2);
    if (((File)localObject3).exists()) {
      showDialog(505);
    }
    StartLongOperation();
    AddToButtonsEventQueue(new ButtonsEvents(5));
  }
  
  private Dialog ShowFileSaveError()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(getString(2131755132));
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099784));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131755133)).setPositiveButton(getString(2131755127), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private Dialog ShowHaveFoundApkDialog()
  {
    new AlertDialog.Builder(this).setTitle("Send email to developers").setMessage("We found a problem! Please press ok and send the mail we will compose").setPositiveButton(getString(2131755128), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.OnComposeMailToDevs();
      }
    }).setNegativeButton(getString(2131755126), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).create();
  }
  
  private void ShowMemoryInfo()
  {
    Object localObject = (ActivityManager)getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)localObject).getMemoryInfo(localMemoryInfo);
    localObject = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" memoryInfo.availMem ");
    localStringBuilder.append(localMemoryInfo.availMem);
    localStringBuilder.append("\n");
    SystemOperations.ShowLog((String)localObject, localStringBuilder.toString());
  }
  
  private Dialog ShowNeedToSaveBeforeQuitDialog()
  {
    TextView localTextView = new TextView(this);
    localTextView.setText(2131755138);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099678));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131755141)).setPositiveButton(getString(2131755128), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.m_quit_pressed = false;
        TouchRetouchActivity.this.OnSaveSelected(false);
      }
    }).setNegativeButton(getString(2131755126), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.SetStopTimer();
        TouchRetouchActivity.access$1102(false);
        if (TouchRetouchActivity.this.m_quit_pressed)
        {
          TouchRetouchActivity.this.m_quit_pressed = false;
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
    localTextView.setText(2131755138);
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099678));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setMessage(getString(2131755141)).setPositiveButton(getString(2131755128), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        TouchRetouchActivity.this.OnSaveSelected(false);
      }
    }).setNegativeButton(getString(2131755126), new DialogInterface.OnClickListener()
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
    int i = GetOrientation(m_pic_orient);
    TextView localTextView = new TextView(this);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getString(2131755116));
    localStringBuilder.append(" (");
    int j = i % 2;
    if (j == 1) {
      i = m_cur_image_height;
    } else {
      i = m_cur_image_width;
    }
    localStringBuilder.append(i);
    localStringBuilder.append("x");
    if (j == 1) {
      i = m_cur_image_width;
    } else {
      i = m_cur_image_height;
    }
    localStringBuilder.append(i);
    localStringBuilder.append(")");
    localTextView.setText(localStringBuilder.toString());
    localTextView.setPadding(0, 30, 0, 20);
    localTextView.setGravity(17);
    localTextView.setTextColor(getResources().getColor(2131099784));
    localTextView.setTextSize(20.0F);
    new AlertDialog.Builder(this).setCustomTitle(localTextView).setItems(2130903041, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 2: 
          TouchRetouchActivity.this.StartLongOperation();
          TouchRetouchActivity.this.AddToButtonsEventQueue(new TouchRetouchActivity.ButtonsEvents(TouchRetouchActivity.this, 6));
          return;
        case 1: 
          TouchRetouchActivity.this.StartLongOperation();
          TouchRetouchActivity.this.AddToButtonsEventQueue(new TouchRetouchActivity.ButtonsEvents(TouchRetouchActivity.this, 6));
          return;
        }
        TouchRetouchActivity.this.ShowFileNameEnterDialog();
      }
    }).create();
  }
  
  private void ShowSaveOrSendDialogActivity()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, DialogSaveSendActivity.class);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getString(2131755114));
    localArrayList.add(getString(2131755115));
    localIntent.putExtra("save_items", localArrayList);
    int j = GetOrientation(m_pic_orient) % 2;
    int i;
    if (j == 1) {
      i = m_cur_image_height;
    } else {
      i = m_cur_image_width;
    }
    if (j == 1) {
      j = m_cur_image_width;
    } else {
      j = m_cur_image_height;
    }
    localIntent.putExtra("width", i);
    localIntent.putExtra("height", j);
    startActivityForResult(localIntent, 604);
  }
  
  private void StartLongOperation()
  {
    findViewById(2131296584).setVisibility(0);
    findViewById(2131296583).setVisibility(0);
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
    int i = 0;
    localObject1 = ((PackageManager)localObject1).getInstalledApplications(0);
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
  
  private void loadAdmobAd()
  {
    this.mInterstitialAdMob.loadAd(new AdRequest.Builder().build());
  }
  
  private InterstitialAd showAdmobFullAd()
  {
    InterstitialAd localInterstitialAd = new InterstitialAd(this);
    localInterstitialAd.setAdUnitId(getString(2131755068));
    localInterstitialAd.setAdListener(new AdListener()
    {
      public void onAdClosed()
      {
        TouchRetouchActivity.this.loadAdmobAd();
      }
      
      public void onAdLoaded() {}
      
      public void onAdOpened() {}
    });
    return localInterstitialAd;
  }
  
  private void showAdmobInterstitial()
  {
    if ((this.mInterstitialAdMob != null) && (this.mInterstitialAdMob.isLoaded())) {
      this.mInterstitialAdMob.show();
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
    ImageView localImageView = (ImageView)findViewById(2131296352);
    Canvas localCanvas = new Canvas();
    Bitmap localBitmap = Bitmap.createBitmap(2400, 2400, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(getResources().getColor(2131099693));
    localPaint.setStyle(Paint.Style.FILL);
    float f1 = Integer.MAX_VALUE;
    float f2 = -2147481247;
    localCanvas.drawRoundRect(new RectF(f1, f1, f2, f2), 50.0F, 50.0F, localPaint);
    localPaint.setColor(getResources().getColor(2131099692));
    localPaint.setStyle(Paint.Style.FILL);
    f1 = 'Ұ';
    localCanvas.drawCircle(f1, f1, (float)(paramInt * 5.6D), localPaint);
    localImageView.setImageBitmap(localBitmap);
  }
  
  protected void DrawZoomValue(float paramFloat)
  {
    ImageView localImageView = (ImageView)findViewById(2131296721);
    Canvas localCanvas = new Canvas();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "we really call drawZoomValue here!!!");
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "koef = 12.0");
    double d1 = 'π';
    double d2 = 1.5D * d1;
    Bitmap localBitmap = Bitmap.createBitmap((int)d2, 960, Bitmap.Config.ARGB_4444);
    localCanvas.setBitmap(localBitmap);
    Paint localPaint = new Paint();
    localPaint.setColor(-1895825408);
    localPaint.setStyle(Paint.Style.FILL);
    float f = 120;
    double d3 = 120;
    localCanvas.drawRoundRect(new RectF(f, f, (float)(d2 - d3), (float)(d1 * 0.8D - d3)), 240.0F, 240.0F, localPaint);
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setColor(-1);
    localPaint.setTextSize(336.0F);
    localPaint.setAntiAlias(true);
    localPaint.setTypeface(Typeface.defaultFromStyle(0));
    int i = (int)(100.0F * paramFloat);
    StringBuilder localStringBuilder;
    if (i >= 100)
    {
      localStringBuilder = new StringBuilder(String.valueOf(i));
      localStringBuilder.append("%");
      localCanvas.drawText(localStringBuilder.toString(), 336.0F, 480.0F, localPaint);
    }
    else
    {
      localStringBuilder = new StringBuilder(String.valueOf(i));
      localStringBuilder.append("%");
      localCanvas.drawText(localStringBuilder.toString(), 420.0F, 480.0F, localPaint);
    }
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
  
  protected void InitControls()
  {
    this.vv_info = ((ImageView)findViewById(2131296717));
    this.m_btnPreferedOpt = new BitmapFactory.Options();
    this.m_btnPreferedOpt.inPreferredConfig = Bitmap.Config.ARGB_4444;
    m_last_time = System.currentTimeMillis();
    SetListenerLayoutForButton(2131296277, true);
    SetListenerLayoutForButton(2131296282, true);
    SetListenerLayoutForButton(2131296279, true);
    SetListenerLayoutForButton(2131296275, true);
    SetListenerLayoutForButton(2131296258, true);
    SetListenerLayoutForButton(2131296278, true);
    SetListenerLayoutForButton(2131296276, true);
    SetListenerLayoutForButton(2131296269, true);
    SetListenerLayoutForButton(2131296274, true);
    SetListenerLayoutForButton(2131296260, true);
    SetListenerLayoutForButton(2131296280, true);
    SetListenerForButton(2131296259, false);
    SetListenerLayoutForButton(2131296268, false);
    SetListenerLayoutForButton(2131296262, false);
    SetListenerLayoutForButton(2131296261, false);
    SetListenerLayoutForButton(2131296371, true);
    SetListenerLayoutForButton(2131296372, true);
    SetListenerLayoutForButton(2131296373, true);
    SetListenerLayoutForButton(2131296374, true);
    SetListenerLayoutForButton(2131296375, true);
    SetListenerLayoutForButton(2131296441, true);
    SetListenerLayoutForButton(2131296442, true);
    SetListenerLayoutForButton(2131296443, true);
    SetListenerLayoutForButton(2131296444, true);
    SetListenerForButton(2131296284, true);
    SetListenerForButton(2131296283, true);
    HidePanel(302);
    HidePanel(300);
    HidePanel(301);
    this.m_lastSelectedCloneMode = 2131296263;
    this.m_lastSelectedFlipMode = 2131296270;
    this.m_lastSelectedTool = 2131296276;
    Object localObject = (SeekBar)findViewById(2131296620);
    ((SeekBar)localObject).setOnSeekBarChangeListener(this);
    DrawBrushSizeView(((SeekBar)localObject).getProgress());
    this.mView = ((GLSurfaceView)findViewById(2131296457));
    this.mView.getHolder().setFormat(-3);
    this.mView.setEGLContextFactory(new ContextFactory(null));
    this.mView.setEGLConfigChooser(new ConfigChooser(8, 8, 8, 8, 0, 0));
    this.mView.setRenderer(new Renderer(this));
    this.mView.setRenderMode(1);
    this.mView.setOnTouchListener(this);
    localObject = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
    int i = getWindowManager().getDefaultDisplay().getOrientation();
    String str = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("metrics.widthPixels = ");
    localStringBuilder.append(((DisplayMetrics)localObject).widthPixels);
    localStringBuilder.append("metrics.heightPixels = ");
    localStringBuilder.append(((DisplayMetrics)localObject).heightPixels);
    SystemOperations.ShowLog(str, localStringBuilder.toString());
    switch (i)
    {
    default: 
      break;
    case 1: 
    case 3: 
      m_width = ((DisplayMetrics)localObject).heightPixels;
      m_height = ((DisplayMetrics)localObject).widthPixels;
      break;
    case 0: 
    case 2: 
      m_width = ((DisplayMetrics)localObject).widthPixels;
      m_height = ((DisplayMetrics)localObject).heightPixels;
    }
    i = TouchRetouchLib.init(m_width, m_height, i, Build.VERSION.SDK_INT);
    if (i != 0) {
      switch (i)
      {
      default: 
        break;
      case 2: 
        m_whyAppIsCracked = 1;
        TryToFindApk();
        break;
      case 1: 
        SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "app is cracked!");
        m_whyAppIsCracked = 1;
      }
    }
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
      findViewById(2131296720).setVisibility(0);
    }
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "checked multitouch");
    this.m_imgPicker = new ImagePicker();
    this.m_imgPicker.SetImagePickerListener(this);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "init controls finished");
  }
  
  public void ObjectClone(View paramView)
  {
    findViewById(2131296268).performClick();
    findViewById(2131296274).setVisibility(8);
    findViewById(2131296548).setVisibility(8);
    findViewById(2131296666).setVisibility(0);
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
    findViewById(2131296666).startAnimation(paramView);
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
    findViewById(2131296548).setVisibility(0);
    findViewById(2131296666).setVisibility(8);
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
    findViewById(2131296548).startAnimation(paramView);
  }
  
  @SuppressLint({"WrongConstant"})
  protected void OnComposeMailToDevs()
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setFlags(268435456);
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "touchretouch@handyphotolab.com" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "can't found apk!");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Path to apk is ");
    localStringBuilder.append(this.m_found_apk);
    localIntent.putExtra("android.intent.extra.TEXT", localStringBuilder.toString());
    startActivity(Intent.createChooser(localIntent, getString(2131755131)));
  }
  
  public void OnFinishZoom()
  {
    this.m_zoom_started = false;
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        View localView = TouchRetouchActivity.this.findViewById(2131296304);
        if (localView.getVisibility() == 0)
        {
          Animation localAnimation = AnimationUtils.loadAnimation(TouchRetouchActivity.this, 2130771986);
          localAnimation.setDuration(300L);
          localView.startAnimation(localAnimation);
          localView.setVisibility(4);
        }
      }
    }, 300L);
  }
  
  public void OnStartZoom()
  {
    findViewById(2131296304).setVisibility(0);
    this.m_zoom_started = true;
  }
  
  public void OpenPictureFromLibrary()
  {
    Intent localIntent = new Intent("android.intent.action.GET_CONTENT");
    localIntent.setType("image/*");
    startActivityForResult(Intent.createChooser(localIntent, getString(2131755112)), 301);
  }
  
  public void ProcessChoseResolutionClick(int paramInt)
  {
    BitmapFactory.Options localOptions;
    switch (paramInt)
    {
    default: 
      return;
    case 3: 
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
      if (m_pic_type == 401)
      {
        m_pic_scale = 8;
        AddToJPGPictureQueue(m_pic_path);
        return;
      }
      return;
    case 2: 
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
      if (m_pic_type == 401)
      {
        m_pic_scale = 4;
        AddToJPGPictureQueue(m_pic_path);
        return;
      }
      return;
    case 1: 
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
      if (m_pic_type == 401)
      {
        m_pic_scale = 2;
        AddToJPGPictureQueue(m_pic_path);
        return;
      }
      return;
    }
    StartLongOperation();
    if (m_pic_type == 402)
    {
      AddToPictureQueue(this.m_bm);
      return;
    }
    if (m_pic_type == 401)
    {
      m_pic_scale = 1;
      AddToJPGPictureQueue(m_pic_path);
      return;
    }
  }
  
  protected void ProcessImagePathFromExtras()
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
  
  public void QuickRepair(View paramView)
  {
    findViewById(2131296278).performClick();
    findViewById(2131296274).setVisibility(8);
    findViewById(2131296548).setVisibility(8);
    findViewById(2131296666).setVisibility(0);
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
    findViewById(2131296666).startAnimation(paramView);
    paramView = getSharedPreferences("WIPE_OUT", 0).edit();
    paramView.putString("PANEL_VISIBLE", "QUICK_REPAIR");
    paramView.putString("OBJECT_SELECT", "OBJECT_QUICK_BRUSH");
    paramView.apply();
  }
  
  public void SetLastEditedTool()
  {
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "SetLastEditedTool");
    String str = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("lastSelectedTool = ");
    localStringBuilder.append(this.m_lastSelectedTool);
    SystemOperations.ShowLog(str, localStringBuilder.toString());
    switch (this.m_lastSelectedTool)
    {
    default: 
      return;
    case 2131296278: 
      TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131296620)).getProgress());
      return;
    case 2131296275: 
      TouchRetouchLib.SetEditTool(0, ((SeekBar)findViewById(2131296620)).getProgress());
      return;
    case 2131296269: 
      TouchRetouchLib.SetEditTool(2, ((SeekBar)findViewById(2131296620)).getProgress());
      return;
    case 2131296260: 
      TouchRetouchLib.SetEditTool(5, ((SeekBar)findViewById(2131296620)).getProgress());
      TouchRetouchLib.SetCloneStampType(this.m_lastSelectedCloneMode, this.m_lastSelectedFlipMode);
      return;
    }
    TouchRetouchLib.SetEditTool(1, ((SeekBar)findViewById(2131296620)).getProgress());
  }
  
  public void SetLongOperationFinished()
  {
    this.m_isLongOperationStarted = false;
  }
  
  public void SetPanelsVisible(boolean paramBoolean)
  {
    View localView1 = findViewById(2131296720);
    View localView2 = findViewById(2131296329);
    Object localObject = findViewById(2131296440);
    View localView3 = findViewById(2131296370);
    ((View)localObject).setVisibility(4);
    localView3.setVisibility(4);
    localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, 1.0F);
    ((Animation)localObject).setDuration(300L);
    if (this.m_lastSelectedTool == 2131296260)
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
      this.myTimer.schedule(new TimerTask()
      {
        public void run()
        {
          TouchRetouchActivity.this.TimerMethod();
        }
      }, 0L, 200L);
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
    ((RelativeLayout)findViewById(2131296282)).setEnabled(paramBoolean1);
    ((RelativeLayout)findViewById(2131296279)).setEnabled(paramBoolean2);
  }
  
  public void Settings(View paramView)
  {
    paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
    if (paramView.equals("OBJECT_REMOVAL_BRUSH"))
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
      if ((IsPanelShown(302)) && (m_lastPanelShown == 2))
      {
        HidePanel(302);
      }
      else
      {
        HideAllAddedButtons();
        ShowPanel(302);
        m_lastPanelShown = 2;
      }
      SetClearAllVisible();
      return;
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
    if (paramView.equals("OBJECT_CLONE_MIRRORING"))
    {
      if ((IsPanelShown(300)) && (m_lastPanelShown == 1))
      {
        HidePanel(300);
      }
      else
      {
        ShowPanel(300);
        m_lastPanelShown = 1;
      }
      HidePanel(301);
      HidePanel(302);
    }
  }
  
  public void ShowFileSaved()
  {
    Toast.makeText(this, getString(2131755136), 1).show();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 != 301)
    {
      if (paramInt1 != 604)
      {
        SetLongOperationFinished();
        return;
      }
      if (paramInt2 == -1)
      {
        switch (DialogSaveSendActivity.ClickedItem)
        {
        default: 
          return;
        case 2: 
          StartLongOperation();
          if (SaveImageToTempFolder() != null)
          {
            paramInt1 = GetOrientation(m_pic_orient);
            paramIntent = TOUCHRETOUCH_FOLDER;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("orient for contest = ");
            localStringBuilder.append(paramInt1);
            SystemOperations.ShowLog(paramIntent, localStringBuilder.toString());
            return;
          }
          return;
        case 1: 
          StartLongOperation();
          AddToButtonsEventQueue(new ButtonsEvents(6));
          return;
        }
        ShowFileNameEnterDialog();
        startActivity(new Intent(this, Image_Edite.class));
        showAdmobInterstitial();
        return;
      }
      return;
    }
    if (paramInt2 == -1)
    {
      SetStopTimer();
      this.m_imgPicker.PickImageFromGallery(paramIntent.getData());
      return;
    }
    SetLongOperationFinished();
  }
  
  public void onBackPressed()
  {
    if (this.m_isLongOperationStarted)
    {
      Toast.makeText(this, getString(2131755135), 1).show();
      startActivity(new Intent(this, MainActivity.class));
      finish();
      return;
    }
    if (TouchRetouchLib.NeedToSave())
    {
      showDialog(603);
      return;
    }
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "stopping timer");
    SetStopTimer();
    atLeastOneImageLoaded = false;
    setResult(0);
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }
  
  public void onClick(View paramView)
  {
    if (paramView == findViewById(2131296277))
    {
      OnOpenSelected();
      return;
    }
    if (paramView == findViewById(2131296282))
    {
      OnUndoSelected();
      return;
    }
    if (paramView == findViewById(2131296279))
    {
      OnRedoSelected();
      return;
    }
    if (paramView == findViewById(2131296275))
    {
      OnLassoSelected();
      return;
    }
    if (paramView == findViewById(2131296258))
    {
      OnBrushSelected();
      return;
    }
    if (paramView == findViewById(2131296278))
    {
      OnQuickBrushSelected();
      return;
    }
    if (paramView == findViewById(2131296276))
    {
      OnMoveSelected(true);
      return;
    }
    if (paramView == findViewById(2131296269))
    {
      OnEraseSelected();
      return;
    }
    if (paramView == findViewById(2131296274))
    {
      OnGoSelected();
      return;
    }
    if (paramView == findViewById(2131296260))
    {
      OnCloneSelected();
      return;
    }
    if (paramView == findViewById(2131296280))
    {
      OnSaveSelected(true);
      return;
    }
    if (paramView == findViewById(2131296259))
    {
      OnEraseAllSelected();
      return;
    }
    if (paramView == findViewById(2131296268))
    {
      OnCloneTargetSelected();
      return;
    }
    if (paramView == findViewById(2131296262))
    {
      OnCloneMaskSelected();
      return;
    }
    if (paramView == findViewById(2131296261))
    {
      OnCloneFlipSelected();
      return;
    }
    if (paramView == findViewById(2131296371))
    {
      OnCloneModeTypeSelected(2131296263);
      return;
    }
    if (paramView == findViewById(2131296372))
    {
      OnCloneModeTypeSelected(2131296264);
      return;
    }
    if (paramView == findViewById(2131296373))
    {
      OnCloneModeTypeSelected(2131296265);
      return;
    }
    if (paramView == findViewById(2131296374))
    {
      OnCloneModeTypeSelected(2131296266);
      return;
    }
    if (paramView == findViewById(2131296375))
    {
      OnCloneModeTypeSelected(2131296267);
      return;
    }
    if (paramView == findViewById(2131296441))
    {
      OnCloneFlipTypeSelected(2131296270);
      return;
    }
    if (paramView == findViewById(2131296442))
    {
      OnCloneFlipTypeSelected(2131296271);
      return;
    }
    if (paramView == findViewById(2131296443))
    {
      OnCloneFlipTypeSelected(2131296272);
      return;
    }
    if (paramView == findViewById(2131296444))
    {
      OnCloneFlipTypeSelected(2131296273);
      return;
    }
    if (paramView == findViewById(2131296284))
    {
      OnZoomSelected(true);
      return;
    }
    if (paramView == findViewById(2131296283)) {
      OnZoomSelected(false);
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "OnConfig changed!!!");
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131492906);
    this.mInterstitialAdMob = showAdmobFullAd();
    loadAdmobAd();
    SystemOperations.ShowLog(TOUCHRETOUCH_FOLDER, "onCreate");
    if (Glob.position == 1)
    {
      InitControls();
      this.vv_info.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, RemoveObjectTutorialsActivity.class));
        }
      });
      findViewById(2131296274).setVisibility(0);
      findViewById(2131296548).setVisibility(8);
      findViewById(2131296666).setVisibility(0);
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
      findViewById(2131296666).startAnimation(paramBundle);
      paramBundle = getSharedPreferences("WIPE_OUT", 0).edit();
      paramBundle.putString("PANEL_VISIBLE", "OBJECT_REMOVAL");
      paramBundle.putString("OBJECT_SELECT", "OBJECT_REMOVAL_BRUSH");
      paramBundle.apply();
    }
    else if (Glob.position == 2)
    {
      InitControls();
      this.vv_info.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, RemoveQuickTutorialsActivity.class));
        }
      });
      findViewById(2131296548).setVisibility(0);
      findViewById(2131296274).setVisibility(8);
    }
    else if (Glob.position == 3)
    {
      InitControls();
      this.vv_info.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          TouchRetouchActivity.this.startActivity(new Intent(TouchRetouchActivity.this, ClonetTutorialsActivity.class));
        }
      });
      findViewById(2131296268).performClick();
      findViewById(2131296274).setVisibility(8);
      findViewById(2131296548).setVisibility(8);
      findViewById(2131296666).setVisibility(0);
      findViewById(2131296260).setVisibility(8);
      findViewById(2131296278).setVisibility(8);
      findViewById(2131296258).setVisibility(8);
      findViewById(2131296275).setVisibility(8);
      findViewById(2131296269).setVisibility(8);
      findViewById(2131296268).setVisibility(0);
      findViewById(2131296262).setVisibility(0);
      findViewById(2131296261).setVisibility(0);
      findViewById(2131296281).setVisibility(0);
      paramBundle = new TranslateAnimation(1, 1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
      paramBundle.setDuration(300L);
      findViewById(2131296666).startAnimation(paramBundle);
      paramBundle = getSharedPreferences("WIPE_OUT", 0).edit();
      paramBundle.putString("PANEL_VISIBLE", "CLONE_STAMP");
      paramBundle.putString("OBJECT_SELECT", "OBJECT_CLONE_BRUSH");
      paramBundle.apply();
    }
    this.BtnGo = ((RelativeLayout)findViewById(2131296274));
    paramBundle = AnimationUtils.loadAnimation(this, 2130771996);
    this.BtnGo.startAnimation(paramBundle);
    m_ib = IntBuffer.allocate(262144);
    paramBundle = TOUCHRETOUCH_FOLDER;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("extra uri = ");
    localStringBuilder.append(getIntent().getDataString());
    SystemOperations.ShowLog(paramBundle, localStringBuilder.toString());
    ProcessImagePathFromExtras();
  }
  
  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 606: 
      return ShowHaveFoundApkDialog();
    case 605: 
      return ShowBuyFullVersionDialog();
    case 603: 
      return ShowNeedToSaveBeforeQuitDialog();
    case 602: 
      return ShowNeedToSaveDialog();
    case 506: 
      return ShowSaveOrSendDialog();
    case 505: 
      return null;
    case 504: 
      return ShowFileSaveError();
    case 502: 
      return InitChoseOpenSourceDialog();
    }
    return InitChoseResolutionDialog();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131558400, paramMenu);
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
    Toast.makeText(this, getString(2131755132), 1).show();
    SetStartTimer();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() != 2131296555) {
      return super.onOptionsItemSelected(paramMenuItem);
    }
    if (TouchRetouchLib.NeedToSave())
    {
      this.m_quit_pressed = true;
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
    this.mView.onPause();
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
    this.mView.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
    SystemOperations.ShowLog("touchretouch1", "Before check that is needed device");
    this.m_isUsePressureBrush = false;
    SeekBar localSeekBar = (SeekBar)findViewById(2131296620);
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
    if ((this.m_lastSelectedTool != 2131296260) && (this.m_lastSelectedTool != 2131296276)) {
      findViewById(2131296257).setVisibility(0);
    }
  }
  
  protected void onStop()
  {
    super.onStop();
  }
  
  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    if ((this.m_lastSelectedTool != 2131296260) && (this.m_lastSelectedTool != 2131296276))
    {
      findViewById(2131296257).setVisibility(8);
      ShowBrushSizeWithFadeOut();
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (this.m_isLongOperationStarted) {
      return true;
    }
    int j = 0;
    paramView = getSharedPreferences("WIPE_OUT", 0).getString("OBJECT_SELECT", null);
    if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 2) || (paramMotionEvent.getAction() == 1) || (paramMotionEvent.getAction() == 3) || (paramMotionEvent.getAction() == 261) || (paramMotionEvent.getAction() == 262) || (paramMotionEvent.getAction() == 6) || (paramMotionEvent.getAction() == 5))
    {
      int k = paramMotionEvent.getPointerCount();
      int i = j;
      if (this.m_lastPointerCount != k)
      {
        if (k == 2) {
          OnStartZoom();
        }
        if (k == 1) {
          OnFinishZoom();
        }
        this.m_lastPointerCount = k;
        i = j;
      }
      while (i < k)
      {
        AddToQueue(new MyMotionEvent(paramMotionEvent.getPointerId(i), paramMotionEvent.getAction(), (int)paramMotionEvent.getX(i), (int)paramMotionEvent.getY(i)));
        if ((paramMotionEvent.getAction() == 0) && (this.m_isUsePressureBrush)) {
          TouchRetouchLib.SetBrushSize(140);
        }
        if ((2 == paramMotionEvent.getAction()) && (IsPanelShown(302)) && ((int)paramMotionEvent.getY(i) > m_height - GetBottomPanelsHeight() - 15)) {
          HidePanel(302);
        }
        j = paramMotionEvent.getAction();
        if (j != 1)
        {
          if ((j != 6) && (j != 262)) {
            break label350;
          }
        }
        else
        {
          j = findViewById(2131296304).getVisibility();
          if ((paramView != null) && (paramView.equals("OBJECT_QUICK_BRUSH")) && (j == 4))
          {
            OnGoSelected();
            break label350;
          }
        }
        if (System.currentTimeMillis() - m_last_touch_time < 500L)
        {
          OnStartZoom();
          DrawZoomValue(TouchRetouchLib.GetZoom());
          OnFinishZoom();
        }
        m_last_touch_time = System.currentTimeMillis();
        label350:
        i += 1;
      }
    }
    return true;
  }
  
  class ButtonsEvents
  {
    static final int EVENT_CLEAR_ALL = 4;
    static final int EVENT_GO = 3;
    public static final int EVENT_REDO = 1;
    public static final int EVENT_SAVE_IMAGE = 5;
    static final int EVENT_SEND_IMAGE = 6;
    public static final int EVENT_UNDO = 2;
    int m_EventType = 0;
    
    ButtonsEvents(int paramInt)
    {
      this.m_EventType = paramInt;
    }
  }
  
  private static class ConfigChooser
    implements GLSurfaceView.EGLConfigChooser
  {
    private static int EGL_OPENGL_ES2_BIT = 4;
    private static int[] s_configAttribs2 = { 12324, 4, 12323, 4, 12322, 4, 12352, EGL_OPENGL_ES2_BIT, 12344 };
    int mAlphaSize;
    int mBlueSize;
    int mDepthSize;
    int mGreenSize;
    int mRedSize;
    int mStencilSize;
    private int[] mValue = new int[1];
    
    ConfigChooser(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
      this.mRedSize = paramInt1;
      this.mGreenSize = paramInt2;
      this.mBlueSize = paramInt3;
      this.mAlphaSize = paramInt4;
      this.mDepthSize = paramInt5;
      this.mStencilSize = paramInt6;
    }
    
    private int findConfigAttrib(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig, int paramInt1, int paramInt2)
    {
      if (paramEGL10.eglGetConfigAttrib(paramEGLDisplay, paramEGLConfig, paramInt1, this.mValue)) {
        return this.mValue[0];
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
      while (i < arrayOfInt1.length)
      {
        int j = arrayOfInt1[i];
        String str = new String[] { "EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT" }[i];
        if (paramEGL10.eglGetConfigAttrib(paramEGLDisplay, paramEGLConfig, j, arrayOfInt2)) {
          SystemOperations.ShowLog(TouchRetouchActivity.TAG, String.format("  %s: %d\n", new Object[] { str, Integer.valueOf(arrayOfInt2[0]) }));
        } else {
          while (paramEGL10.eglGetError() != 12288) {}
        }
        i += 1;
      }
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
      return chooseConfig(paramEGL10, paramEGLDisplay, arrayOfEGLConfig);
    }
    
    EGLConfig chooseConfig(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig[] paramArrayOfEGLConfig)
    {
      int j = paramArrayOfEGLConfig.length;
      int i = 0;
      while (i < j)
      {
        EGLConfig localEGLConfig = paramArrayOfEGLConfig[i];
        int k = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12325, 0);
        int m = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12326, 0);
        if ((k >= this.mDepthSize) && (m >= this.mStencilSize))
        {
          k = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12324, 0);
          m = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12323, 0);
          int n = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12322, 0);
          int i1 = findConfigAttrib(paramEGL10, paramEGLDisplay, localEGLConfig, 12321, 0);
          if ((k == this.mRedSize) && (m == this.mGreenSize) && (n == this.mBlueSize) && (i1 == this.mAlphaSize)) {
            return localEGLConfig;
          }
        }
        i += 1;
      }
      return null;
    }
  }
  
  private static class ContextFactory
    implements GLSurfaceView.EGLContextFactory
  {
    private static int EGL_CONTEXT_CLIENT_VERSION = 12440;
    
    private ContextFactory() {}
    
    public EGLContext createContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLConfig paramEGLConfig)
    {
      SystemOperations.ShowLog(TouchRetouchActivity.TAG, "creating OpenGL ES 2.0 context");
      TouchRetouchActivity.checkEglError("Before eglCreateContext", paramEGL10);
      paramEGLDisplay = paramEGL10.eglCreateContext(paramEGLDisplay, paramEGLConfig, EGL10.EGL_NO_CONTEXT, new int[] { EGL_CONTEXT_CLIENT_VERSION, 2, 12344 });
      TouchRetouchActivity.checkEglError("After eglCreateContext", paramEGL10);
      return paramEGLDisplay;
    }
    
    public void destroyContext(EGL10 paramEGL10, EGLDisplay paramEGLDisplay, EGLContext paramEGLContext)
    {
      paramEGL10.eglDestroyContext(paramEGLDisplay, paramEGLContext);
    }
  }
  
  private class MyMotionEvent
  {
    public int action;
    public int id;
    int x;
    int y;
    
    MyMotionEvent(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.id = paramInt1;
      this.action = paramInt2;
      this.x = paramInt3;
      this.y = paramInt4;
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
      for (TouchRetouchActivity.ButtonsEvents localButtonsEvents = this.mContext.GetFromButtonsEventQueue(); localButtonsEvents != null; localButtonsEvents = this.mContext.GetFromButtonsEventQueue()) {
        switch (localButtonsEvents.m_EventType)
        {
        default: 
          break;
        case 6: 
          SendImage();
          this.mContext.SetLongOperationFinished();
          break;
        case 5: 
          SaveImage();
          this.mContext.SetLongOperationFinished();
          break;
        case 4: 
          TouchRetouchLib.OnClearAllSelected();
          this.mContext.SetLongOperationFinished();
          break;
        case 3: 
          if (!TouchRetouchLib.OnGoSelected()) {
            this.mContext.AddToFullVersionEventQueue(new Integer(1));
          }
          this.mContext.SetLongOperationFinished();
          break;
        case 2: 
          TouchRetouchLib.OnUndoSelected();
          this.mContext.SetLongOperationFinished();
          break;
        case 1: 
          TouchRetouchLib.OnRedoSelected();
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
          TouchRetouchLib.nativeTouch(((TouchRetouchActivity.MyMotionEvent)localObject).x, ((TouchRetouchActivity.MyMotionEvent)localObject).y, ((TouchRetouchActivity.MyMotionEvent)localObject).action, ((TouchRetouchActivity.MyMotionEvent)localObject).id);
        }
        localObject = localMyMotionEvent;
      }
    }
    
    private void CheckOpenJPGPictureEvent(GL10 paramGL10)
    {
      paramGL10 = this.mContext.GetFromJPGPictureQueue();
      if (paramGL10 != null)
      {
        String str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("orient = ");
        localStringBuilder.append(TouchRetouchActivity.m_pic_orient);
        SystemOperations.ShowLog(str, localStringBuilder.toString());
        str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("scale = ");
        localStringBuilder.append(TouchRetouchActivity.m_pic_scale);
        SystemOperations.ShowLog(str, localStringBuilder.toString());
        TouchRetouchActivity.m_cur_image_width = TouchRetouchActivity.correctWithDilnyk(TouchRetouchActivity.m_cur_image_width, TouchRetouchActivity.m_pic_scale);
        TouchRetouchActivity.m_cur_image_height = TouchRetouchActivity.correctWithDilnyk(TouchRetouchActivity.m_cur_image_height, TouchRetouchActivity.m_pic_scale);
        int i = TouchRetouchActivity.GetOrientation(TouchRetouchActivity.m_pic_orient);
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "GetOrientation");
        TouchRetouchLib.splitImage3(paramGL10, TouchRetouchActivity.m_pic_scale, i);
        this.mContext.SetLongOperationFinished();
        this.mContext.m_isPictureLoaded = true;
      }
    }
    
    private void CheckOpenPictureEvent(GL10 paramGL10)
    {
      Bitmap localBitmap = this.mContext.GetFromPictureQueue();
      if (localBitmap != null)
      {
        SplitImage(paramGL10, localBitmap, 512);
        localBitmap.recycle();
        this.mContext.SetLongOperationFinished();
        this.mContext.m_isPictureLoaded = true;
      }
    }
    
    private void GLRedraw()
    {
      long l1 = System.currentTimeMillis();
      long l2 = TouchRetouchActivity.m_last_time;
      TouchRetouchActivity.access$502(l1);
      TouchRetouchLib.step((float)((l1 - l2) / 1000.0D));
    }
    
    private void InitCloneTextures(GL10 paramGL10)
    {
      Object localObject = new BitmapFactory.Options();
      ((BitmapFactory.Options)localObject).inPreferredConfig = Bitmap.Config.ARGB_8888;
      Bitmap localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230848, (BitmapFactory.Options)localObject);
      IntBuffer localIntBuffer = IntBuffer.allocate(localBitmap.getWidth() * localBitmap.getHeight());
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int i = LoadTexture(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230849, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int j = LoadTexture(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230850, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int k = LoadTexture(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230851, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int m = LoadTexture(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230852, (BitmapFactory.Options)localObject);
      localBitmap.getPixels(localIntBuffer.array(), 0, localBitmap.getWidth(), 0, 0, localBitmap.getWidth(), localBitmap.getHeight());
      int n = LoadTexture(paramGL10, localIntBuffer, localBitmap.getWidth(), localBitmap.getHeight());
      localBitmap.recycle();
      localIntBuffer.clear();
      localObject = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230853, (BitmapFactory.Options)localObject);
      ((Bitmap)localObject).getPixels(localIntBuffer.array(), 0, ((Bitmap)localObject).getWidth(), 0, 0, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      int i1 = LoadTexture(paramGL10, localIntBuffer, ((Bitmap)localObject).getWidth(), ((Bitmap)localObject).getHeight());
      ((Bitmap)localObject).recycle();
      TouchRetouchLib.setCloneTextures(new int[] { i, j, k, m, n }, i1);
    }
    
    private void RotateArray(IntBuffer paramIntBuffer, int paramInt)
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
    
    int LoadTexture(GL10 paramGL10, IntBuffer paramIntBuffer, int paramInt1, int paramInt2)
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
        paramIntBuffer.put(i, k >>> 16 & 0xFF | (k >>> 24 & 0xFF) << 24 | (k & 0xFF) << 16 | (k >>> 8 & 0xFF) << 8);
        i += 1;
      }
      paramGL10.glTexImage2D(3553, 0, 6408, paramInt1, paramInt2, 0, 6408, 5121, paramIntBuffer);
      return j;
    }
    
    void ReadResources()
    {
      TouchRetouchLib.loadResourcesDoubleTexFrag(SystemOperations.GetStringForResource(2131689473, this.mContext));
      TouchRetouchLib.loadResourcesDoubleTexVert(SystemOperations.GetStringForResource(2131689474, this.mContext));
      TouchRetouchLib.loadResourcesNoTexFrag(SystemOperations.GetStringForResource(2131689475, this.mContext));
      TouchRetouchLib.loadResourcesNoTexVert(SystemOperations.GetStringForResource(2131689476, this.mContext));
      TouchRetouchLib.loadResourcesSingleTexFrag(SystemOperations.GetStringForResource(2131689479, this.mContext));
      TouchRetouchLib.loadResourcesSingleTexVert(SystemOperations.GetStringForResource(2131689480, this.mContext));
    }
    
    void SaveImage()
    {
      File localFile = new File(SystemOperations.GetTouchRetouchFolderPath(), this.mContext.m_current_file_name);
      String str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Saving to folder ");
      localStringBuilder.append(localFile.getAbsolutePath());
      SystemOperations.ShowLog(str, localStringBuilder.toString());
      int j = TouchRetouchActivity.GetOrientation(TouchRetouchActivity.m_pic_orient) % 2;
      int i;
      if (j == 1) {
        i = TouchRetouchActivity.m_cur_image_height;
      } else {
        i = TouchRetouchActivity.m_cur_image_width;
      }
      if (j == 1) {
        j = TouchRetouchActivity.m_cur_image_width;
      } else {
        j = TouchRetouchActivity.m_cur_image_height;
      }
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "save image func");
      TouchRetouchLib.SaveImage(localFile.getAbsolutePath(), i, j);
      str = TouchRetouchActivity.TAG;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("SaveImage: ");
      localStringBuilder.append(TouchRetouchActivity.TOUCHRETOUCH_FOLDER);
      Log.e(str, localStringBuilder.toString());
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "before save func");
      new SingleMediaScanner(this.mContext, localFile);
      this.mContext.AddToFileSavedEventQueue(new Integer(1));
      TouchRetouchLib.ImageSaved();
      this.mContext.SetLongOperationFinished();
    }
    
    @SuppressLint({"WrongConstant"})
    void SendImage()
    {
      Object localObject1 = TouchRetouchActivity.SaveImageToTempFolder();
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setFlags(268435456);
      localIntent.setType("image/jpeg");
      localObject1 = Uri.fromFile((File)localObject1);
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(Glob.app_name);
      ((StringBuilder)localObject2).append(" Create By : ");
      ((StringBuilder)localObject2).append(Glob.app_link);
      localIntent.putExtra("android.intent.extra.TEXT", ((StringBuilder)localObject2).toString());
      localObject2 = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Uri = ");
      localStringBuilder.append(((Uri)localObject1).toString());
      SystemOperations.ShowLog((String)localObject2, localStringBuilder.toString());
      localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject1);
      this.mContext.startActivity(Intent.createChooser(localIntent, this.mContext.getResources().getString(2131755131)));
      TouchRetouchLib.ImageSaved();
      this.mContext.SetLongOperationFinished();
    }
    
    void SplitImage(GL10 paramGL10, Bitmap paramBitmap, int paramInt)
    {
      int i5 = paramBitmap.getWidth();
      int i6 = paramBitmap.getHeight();
      TouchRetouchActivity.m_cur_image_width = i5;
      TouchRetouchActivity.m_cur_image_height = i6;
      if (i5 % (paramInt - 1) > 0) {
        i = i5 / (paramInt - 1) + 1;
      } else {
        i = i5 / (paramInt - 1);
      }
      TouchRetouchActivity.m_tex_num_width = i;
      int n = paramInt - 1;
      if (i6 % n > 0) {
        i = i6 / n + 1;
      } else {
        i = i6 / n;
      }
      TouchRetouchActivity.m_tex_num_height = i;
      int[] arrayOfInt = new int[TouchRetouchActivity.m_tex_num_width * TouchRetouchActivity.m_tex_num_height];
      IntBuffer localIntBuffer = TouchRetouchActivity.m_ib;
      String str;
      StringBuilder localStringBuilder;
      int j;
      int m;
      int k;
      int i1;
      if (TouchRetouchActivity.m_pic_orient == 90)
      {
        SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Orientation is vertical!!!");
        i = paramInt - (TouchRetouchActivity.m_tex_num_height * n - i6 + 1);
        str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("offset_x = ");
        localStringBuilder.append(paramInt - (TouchRetouchActivity.m_tex_num_width * n - i5 + 1));
        localStringBuilder.append(", offset_y = ");
        localStringBuilder.append(i);
        SystemOperations.ShowLog(str, localStringBuilder.toString());
        j = 0;
        m = 0;
        k = n;
        while (j < TouchRetouchActivity.m_tex_num_width)
        {
          n = TouchRetouchActivity.m_tex_num_height - 1;
          while (n >= 0)
          {
            int i7 = j * k;
            if (n == 0) {
              i1 = 0;
            } else {
              i1 = (n - 1) * k + i - 1;
            }
            int i2;
            if (n == 0) {
              i2 = (paramInt - i) * paramInt;
            } else {
              i2 = 0;
            }
            int i3 = (j + 1) * k;
            if (i3 >= i5) {
              i3 = paramInt - (i3 - i5 + 1);
            } else {
              i3 = paramInt;
            }
            int i4;
            if (n == 0) {
              i4 = i;
            } else {
              i4 = paramInt;
            }
            localIntBuffer.clear();
            str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("j = ");
            localStringBuilder.append(n);
            localStringBuilder.append(", i = ");
            localStringBuilder.append(j);
            localStringBuilder.append("width = ");
            localStringBuilder.append(i5);
            localStringBuilder.append("height = ");
            localStringBuilder.append(i6);
            SystemOperations.ShowLog(str, localStringBuilder.toString());
            str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(i7);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(i1);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(i3);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(i4);
            SystemOperations.ShowLog(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), 0, 512, i7, i1, i3, i4);
            localIntBuffer.clear();
            str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("start_x = ");
            localStringBuilder.append(i7);
            localStringBuilder.append(" start_y = ");
            localStringBuilder.append(i1);
            localStringBuilder.append(" draw_picture_size_x = ");
            localStringBuilder.append(i3);
            localStringBuilder.append(" draw_picture_size_y = ");
            localStringBuilder.append(i4);
            SystemOperations.ShowLog(str, localStringBuilder.toString());
            paramBitmap.getPixels(localIntBuffer.array(), i2, 512, i7, i1, i3, i4);
            RotateArray(localIntBuffer, paramInt);
            arrayOfInt[m] = LoadTexture(paramGL10, localIntBuffer, paramInt, paramInt);
            n -= 1;
            m += 1;
          }
          j += 1;
        }
        TouchRetouchLib.splitImage2(arrayOfInt, i6, i5, TouchRetouchActivity.m_tex_num_height, TouchRetouchActivity.m_tex_num_width, paramInt);
        return;
      }
      int i = 0;
      while (i < TouchRetouchActivity.m_tex_num_height)
      {
        for (j = 0; j < TouchRetouchActivity.m_tex_num_width; j = i1)
        {
          this.start_x = (n * j);
          this.start_y = (n * i);
          i1 = j + 1;
          k = i1 * n;
          if (k >= i5) {
            k = paramInt - (k - i5 + 1);
          } else {
            k = paramInt;
          }
          m = (i + 1) * n;
          if (m >= i6) {
            m = paramInt - (m - i6 + 1);
          } else {
            m = paramInt;
          }
          localIntBuffer.clear();
          str = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("start_x = ");
          localStringBuilder.append(this.start_x);
          localStringBuilder.append(" start_y = ");
          localStringBuilder.append(this.start_y);
          localStringBuilder.append(" draw_picture_size_x = ");
          localStringBuilder.append(k);
          localStringBuilder.append(" draw_picture_size_y = ");
          localStringBuilder.append(m);
          SystemOperations.ShowLog(str, localStringBuilder.toString());
          paramBitmap.getPixels(localIntBuffer.array(), 0, 512, this.start_x, this.start_y, k, m);
          arrayOfInt[(TouchRetouchActivity.m_tex_num_width * i + j)] = LoadTexture(paramGL10, localIntBuffer, paramInt, paramInt);
        }
        i += 1;
      }
      TouchRetouchLib.splitImage2(arrayOfInt, i5, i6, TouchRetouchActivity.m_tex_num_width, TouchRetouchActivity.m_tex_num_height, paramInt);
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
      paramGL10 = TouchRetouchActivity.TOUCHRETOUCH_FOLDER;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("orientation = ");
      ((StringBuilder)localObject).append(this.mContext.getWindowManager().getDefaultDisplay().getOrientation());
      SystemOperations.ShowLog(paramGL10, ((StringBuilder)localObject).toString());
      paramGL10 = this.mContext.findViewById(2131296668);
      localObject = this.mContext.findViewById(2131296349);
      View localView = this.mContext.findViewById(2131296549);
      switch (this.mContext.getWindowManager().getDefaultDisplay().getOrientation())
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
      SystemOperations.ShowLog(TouchRetouchActivity.TOUCHRETOUCH_FOLDER, "Surface created");
      ReadResources();
      TouchRetouchLib.SetMargins(this.mContext.findViewById(2131296668).getHeight(), this.mContext.findViewById(2131296349).getHeight());
      TouchRetouchLib.initResources(this.mContext.getWindowManager().getDefaultDisplay().getOrientation());
      InitCloneTextures(paramGL10);
      this.mContext.InitPrefs();
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
