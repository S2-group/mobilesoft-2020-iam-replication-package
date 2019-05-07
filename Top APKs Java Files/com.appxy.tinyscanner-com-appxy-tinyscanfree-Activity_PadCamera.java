package com.appxy.tinyscanfree;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.tools.BitmapTools;
import com.appxy.tools.IAPBuy;
import com.appxy.tools.LibImgFun;
import com.appxy.tools.MyApp;
import com.appxy.tools.Util;
import com.appxy.tools.tool;
import com.appxy.views.FocusView;
import com.flurry.android.FlurryAgent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import jp.co.cyberagent.android.gpuimage.extension.GPUImageWrapper;

public class Activity_PadCamera
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  public static final int TIME = 5000;
  public static Activity_PadCamera instance = null;
  private static int max = 8000000;
  private static int maxperm = 130000;
  Activity ac;
  protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Activity_PadCamera.this.finish();
    }
  };
  private File cacheLocation;
  int cameraId = 0;
  boolean cantake = true;
  ArrayList<Integer> clicktime;
  Comparator<String> comparator3 = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      return paramAnonymousString1.substring(paramAnonymousString1.length() - 7, paramAnonymousString1.length() - 4).compareTo(paramAnonymousString2.substring(paramAnonymousString2.length() - 7, paramAnonymousString2.length() - 4));
    }
  };
  private Context context;
  private int curryitem = 0;
  private SharedPreferences.Editor editor;
  private FrameLayout fl;
  FocusView focusview;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private Animation mAnimation;
    private Bitmap previre_bitmap;
    
    @SuppressLint({"InlinedApi", "InflateParams"})
    public void handleMessage(Message paramAnonymousMessage)
    {
      try
      {
        switch (paramAnonymousMessage.what)
        {
        }
        for (;;)
        {
          super.handleMessage(paramAnonymousMessage);
          return;
          if ((Activity_PadCamera.this.progressDialog != null) && (Activity_PadCamera.this.progressDialog.isShowing())) {
            Activity_PadCamera.this.progressDialog.dismiss();
          }
          if (Activity_PadCamera.this.focusview != null) {
            Activity_PadCamera.this.focusview.setVisibility(0);
          }
          Activity_PadCamera.access$902(Activity_PadCamera.this, null);
          Activity_PadCamera.this.mThread = null;
          Activity_PadCamera.this.previewnum.setVisibility(0);
          Activity_PadCamera.this.previewnum.setText(Activity_PadCamera.this.picturepath.size() + "");
          Activity_PadCamera.this.mCamera.startPreview();
          if (Activity_PadCamera.this.picturepath.size() > 0)
          {
            Activity_PadCamera.this.preview_save.setVisibility(0);
            Activity_PadCamera.this.preview_single.setVisibility(8);
            Activity_PadCamera.this.preview_batch.setVisibility(8);
            if ((this.previre_bitmap != null) && (!this.previre_bitmap.isRecycled())) {
              this.previre_bitmap.recycle();
            }
            this.previre_bitmap = null;
            this.previre_bitmap = BitmapTools.getPhoto2((String)Activity_PadCamera.this.picturepath.get(Activity_PadCamera.this.picturepath.size() - 1), 100, 100);
            Object localObject = new Matrix();
            ((Matrix)localObject).postScale(4.0F, 4.0F);
            ((Matrix)localObject).postRotate(Activity_PadCamera.this.getImageOrientation((String)Activity_PadCamera.this.picturepath.get(Activity_PadCamera.this.picturepath.size() - 1)));
            try
            {
              this.previre_bitmap = Bitmap.createBitmap(this.previre_bitmap, 0, 0, this.previre_bitmap.getWidth(), this.previre_bitmap.getHeight(), (Matrix)localObject, true);
              Activity_PadCamera.this.preview_imagephoto.setVisibility(0);
              Activity_PadCamera.this.preview_imagephoto.setImageBitmap(this.previre_bitmap);
              this.mAnimation = AnimationUtils.loadAnimation(Activity_PadCamera.this.context, 2130968590);
              Activity_PadCamera.this.preview_imagephoto.setAnimation(this.mAnimation);
              Activity_PadCamera.this.previewnum.setAnimation(this.mAnimation);
              this.mAnimation.start();
            }
            catch (Exception localException)
            {
              for (;;)
              {
                localException.printStackTrace();
                ((Matrix)localObject).postScale(6.0F, 6.0F);
                ((Matrix)localObject).postRotate(Activity_PadCamera.this.getImageOrientation((String)Activity_PadCamera.this.picturepath.get(Activity_PadCamera.this.picturepath.size() - 1)));
                this.previre_bitmap = Bitmap.createBitmap(this.previre_bitmap, 0, 0, this.previre_bitmap.getWidth(), this.previre_bitmap.getHeight(), (Matrix)localObject, true);
              }
            }
            float f = 0.0F;
            Activity_PadCamera.this.oldrotation = Activity_PadCamera.this.oritation;
            int i = Activity_PadCamera.this.mtool.getRotation() - Activity_PadCamera.this.natualrotation;
            if (i == -1)
            {
              Activity_PadCamera.this.oritation = 3;
              label601:
              if (Activity_PadCamera.this.oritation != 0) {
                break label1018;
              }
              if (Activity_PadCamera.this.oldrotation != 3) {
                break label984;
              }
              f = Activity_PadCamera.this.takepicture.getRotation() + 90.0F;
            }
            for (;;)
            {
              localObject = ObjectAnimator.ofFloat(Activity_PadCamera.this.takepicture, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(Activity_PadCamera.this.light, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(Activity_PadCamera.this.preview_batch, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(Activity_PadCamera.this.preview_single, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(Activity_PadCamera.this.preview_imagephoto, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(Activity_PadCamera.this.previewnum, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(Activity_PadCamera.this.preview_save, "rotation", new float[] { Activity_PadCamera.this.takepicture.getRotation(), f });
              if ((localObject == null) || (localObjectAnimator1 == null)) {
                break;
              }
              AnimatorSet localAnimatorSet = new AnimatorSet();
              localAnimatorSet.play((Animator)localObject).with(localObjectAnimator1).with(localObjectAnimator2).with(localObjectAnimator3).with(localObjectAnimator4).with(localObjectAnimator5).with(localObjectAnimator6);
              localAnimatorSet.start();
              break;
              if (i == -2)
              {
                Activity_PadCamera.this.oritation = 2;
                break label601;
              }
              if (i == -3)
              {
                Activity_PadCamera.this.oritation = 1;
                break label601;
              }
              Activity_PadCamera.this.oritation = i;
              break label601;
              label984:
              f = Activity_PadCamera.this.takepicture.getRotation() - (Activity_PadCamera.this.oldrotation - Activity_PadCamera.this.oritation) * 90;
              continue;
              label1018:
              if (Activity_PadCamera.this.oritation == 1) {
                f = Activity_PadCamera.this.takepicture.getRotation() - (Activity_PadCamera.this.oldrotation - Activity_PadCamera.this.oritation) * 90;
              } else if (Activity_PadCamera.this.oritation == 2) {
                f = Activity_PadCamera.this.takepicture.getRotation() - (Activity_PadCamera.this.oldrotation - Activity_PadCamera.this.oritation) * 90;
              } else if (Activity_PadCamera.this.oritation == 3) {
                if (Activity_PadCamera.this.oldrotation == 0) {
                  f = Activity_PadCamera.this.takepicture.getRotation() - 90.0F;
                } else {
                  f = Activity_PadCamera.this.takepicture.getRotation() - (Activity_PadCamera.this.oldrotation - Activity_PadCamera.this.oritation) * 90;
                }
              }
            }
            if (Activity_PadCamera.this.mCamera != null) {
              if (Activity_PadCamera.this.isFinish)
              {
                Activity_PadCamera.this.mCamera.stopPreview();
                Activity_PadCamera.this.mCamera.setPreviewCallback(null);
                Activity_PadCamera.this.mCamera.release();
                Activity_PadCamera.access$602(Activity_PadCamera.this, null);
              }
              else
              {
                Activity_PadCamera.access$1502(Activity_PadCamera.this, new Activity_PadCamera.Preview(Activity_PadCamera.this, Activity_PadCamera.this.context, Activity_PadCamera.this.mCamera, Activity_PadCamera.this.mcamerawidth));
                Activity_PadCamera.this.fl.addView(Activity_PadCamera.this.mPreview);
                Activity_PadCamera.this.focusview = new FocusView(Activity_PadCamera.this.context);
                Activity_PadCamera.this.fl.addView(Activity_PadCamera.this.focusview);
                Activity_PadCamera.this.focusview.setVisibility(4);
                localObject = Activity_PadCamera.this.mCamera.getParameters();
                if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("auto"))
                {
                  ((Camera.Parameters)localObject).setFocusMode("auto");
                  Activity_PadCamera.this.mCamera.setParameters((Camera.Parameters)localObject);
                  Activity_PadCamera.this.mPreview.setOnClickListener(new View.OnClickListener()
                  {
                    @SuppressLint({"NewApi"})
                    public void onClick(View paramAnonymous2View)
                    {
                      try
                      {
                        List localList;
                        if (Activity_PadCamera.this.mCamera != null)
                        {
                          paramAnonymous2View = Activity_PadCamera.this.mCamera.getParameters();
                          if (paramAnonymous2View.getSupportedFocusModes().contains("auto")) {
                            paramAnonymous2View.setFocusMode("auto");
                          }
                          localList = paramAnonymous2View.getSupportedFlashModes();
                          if (localList != null)
                          {
                            if (!Activity_PadCamera.this.isflashon) {
                              break label122;
                            }
                            if (localList.contains("on")) {
                              paramAnonymous2View.setFlashMode("on");
                            }
                          }
                        }
                        for (;;)
                        {
                          Activity_PadCamera.this.mCamera.setParameters(paramAnonymous2View);
                          Activity_PadCamera.this.mCamera.autoFocus(new Camera.AutoFocusCallback()
                          {
                            public void onAutoFocus(boolean paramAnonymous3Boolean, Camera paramAnonymous3Camera)
                            {
                              Activity_PadCamera.this.hasFocus = paramAnonymous3Boolean;
                              Activity_PadCamera.this.focusview.setFocus(true, paramAnonymous3Boolean);
                            }
                          });
                          return;
                          label122:
                          if (localList.contains("off")) {
                            paramAnonymous2View.setFlashMode("off");
                          }
                        }
                        return;
                      }
                      catch (Exception paramAnonymous2View)
                      {
                        paramAnonymous2View.printStackTrace();
                      }
                    }
                  });
                }
                localObject = Activity_PadCamera.this.getExternalCacheDir().listFiles();
                i = 0;
                while (i < localObject.length)
                {
                  localObject[i].delete();
                  i += 1;
                }
                Activity_PadCamera.this.takepicture.setEnabled(true);
                Activity_PadCamera.this.takepicture2.setEnabled(true);
                Activity_PadCamera.this.takepicture3.setEnabled(true);
                Activity_PadCamera.this.takepicture4.setEnabled(true);
                continue;
                if (Activity_PadCamera.this.focusview != null) {
                  Activity_PadCamera.this.focusview.setVisibility(4);
                }
                if (Activity_PadCamera.this.mCamera != null)
                {
                  localObject = Activity_PadCamera.this.mCamera.getParameters();
                  if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("continuous-picture")) {
                    ((Camera.Parameters)localObject).setFocusMode("continuous-picture");
                  }
                  Activity_PadCamera.this.mCamera.setParameters((Camera.Parameters)localObject);
                  continue;
                  if (Activity_PadCamera.this.clicktime != null)
                  {
                    Activity_PadCamera.this.clicktime.remove(0);
                    if (Activity_PadCamera.this.clicktime.isEmpty())
                    {
                      Activity_PadCamera.this.takepicture.setEnabled(true);
                      Activity_PadCamera.this.takepicture2.setEnabled(true);
                      Activity_PadCamera.this.takepicture3.setEnabled(true);
                      Activity_PadCamera.this.takepicture4.setEnabled(true);
                      if (Activity_PadCamera.this.focusview != null) {
                        Activity_PadCamera.this.focusview.setVisibility(4);
                      }
                      if (Activity_PadCamera.this.mCamera != null)
                      {
                        localObject = Activity_PadCamera.this.mCamera.getParameters();
                        if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("continuous-picture")) {
                          ((Camera.Parameters)localObject).setFocusMode("continuous-picture");
                        }
                        Activity_PadCamera.this.mCamera.setParameters((Camera.Parameters)localObject);
                      }
                      Activity_PadCamera.this.hasFocus = false;
                      continue;
                      if ((Activity_PadCamera.this.progressDialog != null) && (Activity_PadCamera.this.progressDialog.isShowing())) {
                        Activity_PadCamera.this.progressDialog.dismiss();
                      }
                      Activity_PadCamera.access$902(Activity_PadCamera.this, null);
                      Activity_PadCamera.this.mThread = null;
                      localObject = new Intent(Activity_PadCamera.this.context, Activity_Detect.class);
                      ((Intent)localObject).setFlags(67108864);
                      Activity_PadCamera.this.startActivity((Intent)localObject);
                    }
                  }
                }
              }
            }
          }
        }
        return;
      }
      catch (Exception paramAnonymousMessage) {}
    }
  };
  boolean hasFocus = false;
  private IAPBuy iapBuy;
  private boolean isBatch = false;
  boolean isFinish = false;
  boolean isflashon = false;
  Camera.PictureCallback jpegCallback = new Camera.PictureCallback()
  {
    public void onPictureTaken(final byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      Activity_PadCamera localActivity_PadCamera = Activity_PadCamera.this;
      if (!Activity_PadCamera.this.cantake) {}
      for (boolean bool = true;; bool = false)
      {
        localActivity_PadCamera.cantake = bool;
        paramAnonymousCamera.setPreviewCallback(null);
        paramAnonymousCamera.stopPreview();
        Activity_PadCamera.this.takepicture.setEnabled(true);
        Activity_PadCamera.this.takepicture2.setEnabled(true);
        Activity_PadCamera.this.takepicture3.setEnabled(true);
        Activity_PadCamera.this.takepicture4.setEnabled(true);
        Activity_PadCamera.this.myApplication.setPhotofrom(true);
        Activity_PadCamera.this.myApplication.setOritation(-Activity_PadCamera.this.oritation * 90 + Activity_PadCamera.this.mapp.getOritation());
        if (Activity_PadCamera.this.isBatch) {
          break;
        }
        if (Activity_PadCamera.this.focusview != null) {
          Activity_PadCamera.this.focusview.setVisibility(4);
        }
        Activity_PadCamera.this.photodate = paramAnonymousArrayOfByte;
        Activity_PadCamera.this.getCropImage();
        return;
      }
      if (Activity_PadCamera.this.focusview != null)
      {
        Activity_PadCamera.this.focusview.setFocus(false, false);
        Activity_PadCamera.this.focusview.setVisibility(4);
      }
      Activity_PadCamera.access$902(Activity_PadCamera.this, GPUImageWrapper.createLoadingDialog(Activity_PadCamera.this.context, "sf"));
      Activity_PadCamera.this.progressDialog.show();
      Activity_PadCamera.this.mThread = new Thread(new Runnable()
      {
        @SuppressLint({"SimpleDateFormat"})
        public void run()
        {
          try
          {
            Object localObject = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            Activity_PadCamera.access$2402(Activity_PadCamera.this, localSimpleDateFormat.format((Date)localObject));
            localObject = new FileOutputStream(Activity_PadCamera.this.cacheLocation.getPath() + "/" + Activity_PadCamera.this.name + ".jpg");
            ((FileOutputStream)localObject).write(paramAnonymousArrayOfByte);
            ((FileOutputStream)localObject).flush();
            ((FileOutputStream)localObject).close();
            Activity_PadCamera.this.picturepath.add(Activity_PadCamera.this.cacheLocation.getPath() + "/" + Activity_PadCamera.this.name + ".jpg");
            localObject = new Message();
            ((Message)localObject).what = 6;
            Activity_PadCamera.this.handler.sendMessage((Message)localObject);
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
      });
      Activity_PadCamera.this.mThread.start();
    }
  };
  private ImageView light;
  private ImageView light2;
  private ImageView light3;
  private ImageView light4;
  private ArrayList<String> listPath = new ArrayList();
  MyApp mApp;
  private Camera mCamera;
  AlertDialog mDialog;
  private Preview mPreview;
  Thread mThread;
  public int mcameraheight;
  public int mcamerawidth;
  View.OnClickListener mlistener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      }
      label213:
      List localList;
      label452:
      label515:
      do
      {
        return;
        try
        {
          Activity_PadCamera.this.takePicture();
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
          return;
        }
        if (Activity_PadCamera.this.isflashon)
        {
          if (Activity_PadCamera.this.myApplication.isPad())
          {
            Activity_PadCamera.this.light.setImageResource(2130837775);
            if (Activity_PadCamera.this.light2 != null) {
              Activity_PadCamera.this.light2.setImageResource(2130837775);
            }
            Activity_PadCamera.this.light3.setImageResource(2130837775);
            Activity_PadCamera.this.light4.setImageResource(2130837775);
          }
          for (;;)
          {
            Activity_PadCamera.this.editor.putBoolean("isflashon", false);
            Activity_PadCamera.this.editor.commit();
            Activity_PadCamera.this.isflashon = false;
            if (Activity_PadCamera.this.mCamera == null) {
              break label452;
            }
            paramAnonymousView = Activity_PadCamera.this.mCamera.getParameters();
            localList = paramAnonymousView.getSupportedFlashModes();
            if (localList == null) {
              break;
            }
            if (!Activity_PadCamera.this.isflashon) {
              break label515;
            }
            if (!localList.contains("on")) {
              break;
            }
            paramAnonymousView.setFlashMode("on");
            Activity_PadCamera.this.mCamera.setParameters(paramAnonymousView);
            return;
            Activity_PadCamera.this.light.setImageResource(2130837775);
            if (Activity_PadCamera.this.light2 != null) {
              Activity_PadCamera.this.light2.setImageResource(2130837775);
            }
            Activity_PadCamera.this.light3.setImageResource(2130837775);
            Activity_PadCamera.this.light4.setImageResource(2130837775);
          }
        }
        if (Activity_PadCamera.this.myApplication.isPad())
        {
          Activity_PadCamera.this.light.setImageResource(2130837776);
          if (Activity_PadCamera.this.light2 != null) {
            Activity_PadCamera.this.light2.setImageResource(2130837776);
          }
          Activity_PadCamera.this.light3.setImageResource(2130837776);
          Activity_PadCamera.this.light4.setImageResource(2130837776);
        }
        for (;;)
        {
          Activity_PadCamera.this.editor.putBoolean("isflashon", true);
          Activity_PadCamera.this.editor.commit();
          Activity_PadCamera.this.isflashon = true;
          break label213;
          break;
          Activity_PadCamera.this.light.setImageResource(2130837776);
          Activity_PadCamera.this.light3.setImageResource(2130837776);
          if (Activity_PadCamera.this.light2 != null) {
            Activity_PadCamera.this.light2.setImageResource(2130837776);
          }
          Activity_PadCamera.this.light4.setImageResource(2130837776);
        }
      } while (!localList.contains("off"));
      paramAnonymousView.setFlashMode("off");
      Activity_PadCamera.this.mCamera.setParameters(paramAnonymousView);
    }
  };
  tool mtool;
  private MyApplication myApplication;
  private String name;
  int natualrotation;
  ImageView oldimg;
  int oldrotation = 0;
  int oritation = 0;
  String path = Environment.getExternalStorageDirectory() + "/MyTinyScan/Documents";
  byte[] photodate;
  private LinearLayout picturelay;
  private ArrayList<String> picturepath;
  private SharedPreferences preferences;
  private ImageView preview_batch;
  private ImageView preview_imagephoto;
  private ImageView preview_save;
  private ImageView preview_single;
  private TextView previewnum;
  private Dialog progressDialog;
  RelativeLayout r1;
  RelativeLayout r2;
  RelativeLayout r3;
  RelativeLayout r4;
  HorizontalScrollView scrollView;
  private int selectitem = 0;
  private ImageView takepicture;
  private ImageView takepicture2;
  private ImageView takepicture3;
  private ImageView takepicture4;
  private ImageView viewsee;
  
  public Activity_PadCamera() {}
  
  private boolean ExistSDCard()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  @SuppressLint({"InflateParams"})
  private int findFile(String paramString)
  {
    String[] arrayOfString = new File(this.path).list();
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (arrayOfString[i].equals(paramString)) {
        return 1;
      }
      i += 1;
    }
    return -1;
  }
  
  private Bitmap getImageBitmap(String paramString)
    throws FileNotFoundException, IOException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = BitmapTools.computeSampleSize(localOptions, -1, 480000);
    localOptions.inJustDecodeBounds = false;
    try
    {
      paramString = BitmapFactory.decodeFile(paramString, localOptions);
      return paramString;
    }
    catch (OutOfMemoryError paramString) {}
    return null;
  }
  
  private String getRealPathFromURI(Uri paramUri)
  {
    paramUri = new CursorLoader(this.context, paramUri, new String[] { "_data" }, null, null, null).loadInBackground();
    int i = paramUri.getColumnIndexOrThrow("_data");
    paramUri.moveToFirst();
    return paramUri.getString(i);
  }
  
  @SuppressLint({"InflateParams"})
  private void showProIapBuyDialog()
  {
    Object localObject = getLayoutInflater().inflate(2130903104, null);
    final AlertDialog localAlertDialog = new AlertDialog.Builder(this.context).setTitle(getString(2131296433)).setView((View)localObject).create();
    localAlertDialog.show();
    ImageView localImageView = (ImageView)((View)localObject).findViewById(2131755431);
    localObject = (ImageView)((View)localObject).findViewById(2131755433);
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
        Activity_PadCamera.this.iapBuy.IAP_Buy();
      }
    });
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
        Object localObject = Activity_PadCamera.this.getPackageManager().getInstalledApplications(0);
        int j = ((List)localObject).size();
        paramAnonymousView = null;
        int i = 0;
        while (i < j)
        {
          if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
            paramAnonymousView = (ApplicationInfo)((List)localObject).get(i);
          }
          i += 1;
        }
        try
        {
          localObject = new Intent("android.intent.action.VIEW");
          ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.tinyscan&hl=en"));
          ((Intent)localObject).setPackage(paramAnonymousView.packageName);
          Activity_PadCamera.this.startActivity((Intent)localObject);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          Activity_PadCamera.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscan&hl=en")));
        }
      }
    });
  }
  
  private void thankBuy(String paramString)
  {
    new AlertDialog.Builder(this.context).setMessage(paramString).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    }).create().show();
  }
  
  public Camera getCameraInstance()
  {
    Object localObject = null;
    try
    {
      Camera localCamera = Camera.open();
      localObject = localCamera;
      if (localCamera == null)
      {
        localObject = localCamera;
        localCamera = Camera.open(Camera.getNumberOfCameras() - 1);
        localObject = localCamera;
        if (localCamera != null)
        {
          localObject = localCamera;
          this.cameraId = (Camera.getNumberOfCameras() - 1);
          localObject = localCamera;
          this.myApplication.setFront(true);
          localObject = localCamera;
        }
      }
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      setResult(1, new Intent(this.context, Activity_Main.class));
      finish();
    }
    return localObject;
  }
  
  public void getCropImage()
  {
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, (this.mtool.getRotation() + 1) * 90);
    this.progressDialog.show();
    this.mThread = new Thread(new Runnable()
    {
      @SuppressLint({"SimpleDateFormat"})
      public void run()
      {
        if ((Activity_PadCamera.this.mThread != null) && (!Activity_PadCamera.this.mThread.isInterrupted())) {}
        for (;;)
        {
          try
          {
            i = ((ActivityManager)Activity_PadCamera.this.context.getSystemService("activity")).getLargeMemoryClass();
            i = Activity_PadCamera.maxperm * i / 8;
            if (i > Activity_PadCamera.max) {
              continue;
            }
            localObject1 = BitmapFactory.decodeByteArray(Activity_PadCamera.this.photodate, 0, Activity_PadCamera.this.photodate.length);
            localObject2 = new Message();
            ((Message)localObject2).what = 1010;
            Activity_PadCamera.this.handler.sendMessage((Message)localObject2);
            f = ((Bitmap)localObject1).getWidth() * ((Bitmap)localObject1).getHeight();
            if (f >= i) {
              continue;
            }
            localObject2 = new Matrix();
            ((Matrix)localObject2).postRotate(Activity_PadCamera.this.mapp.getOritation());
            localObject1 = Bitmap.createBitmap((Bitmap)localObject1, 0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight(), (Matrix)localObject2, true);
            Activity_PadCamera.this.mapp.clearphotodata();
            localObject2 = new Timestamp(System.currentTimeMillis());
            localObject2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject2);
            localObject2 = new File(Activity_PadCamera.this.cacheLocation.getPath() + "/" + (String)localObject2 + ".temp");
            Object localObject3 = new BufferedOutputStream(new FileOutputStream((File)localObject2));
            Bitmap localBitmap1 = BitmapTools.resizeImage((Bitmap)localObject1);
            Bitmap localBitmap2 = GPUImageWrapper.processRGBClosing(Activity_PadCamera.this.context, 4, localBitmap1);
            Bitmap localBitmap3 = GPUImageWrapper.processSharpen(Activity_PadCamera.this.context, 4.0F, localBitmap2);
            Bitmap localBitmap4 = GPUImageWrapper.processCannyEdgeDetection(Activity_PadCamera.this.context, localBitmap3);
            localBitmap4.compress(Bitmap.CompressFormat.JPEG, 85, (OutputStream)localObject3);
            ((OutputStream)localObject3).flush();
            ((OutputStream)localObject3).close();
            localObject3 = ((File)localObject2).getPath();
            Activity_PadCamera.this.mapp.setNewData(LibImgFun.ImgFunInt((String)localObject3));
            if (((File)localObject2).exists()) {
              ((File)localObject2).delete();
            }
            Activity_PadCamera.this.mApp.setSavebitmap((Bitmap)localObject1);
            localBitmap1.recycle();
            localBitmap2.recycle();
            localBitmap3.recycle();
            localBitmap4.recycle();
          }
          catch (Error localError)
          {
            int i;
            Object localObject1;
            Object localObject2;
            float f;
            localError.printStackTrace();
            Activity_PadCamera.this.finish();
            continue;
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
            continue;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            Activity_PadCamera.this.finish();
            continue;
          }
          if ((Activity_PadCamera.this.mThread != null) && (!Activity_PadCamera.this.mThread.isInterrupted()))
          {
            localObject1 = new Message();
            ((Message)localObject1).what = 5;
            Activity_PadCamera.this.handler.sendMessage((Message)localObject1);
          }
          return;
          i = Activity_PadCamera.max;
          continue;
          f = (float)Math.sqrt(i / f);
          localObject2 = new Matrix();
          ((Matrix)localObject2).postScale(f, f);
          localObject1 = Bitmap.createBitmap((Bitmap)localObject1, 0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight(), (Matrix)localObject2, true);
        }
      }
    });
    this.mThread.start();
  }
  
  public int getImageOrientation(String paramString)
  {
    try
    {
      int i = new ExifInterface(paramString).getAttributeInt("Orientation", 1);
      switch (i)
      {
      case 2: 
      case 4: 
      case 5: 
      case 7: 
      default: 
        return 0;
      case 1: 
        return 0;
      case 6: 
        return 90;
      case 3: 
        return 180;
      }
      return 270;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  public void makefolder()
  {
    if ("mounted".equals(Environment.getExternalStorageState())) {}
    for (this.cacheLocation = new File(Environment.getExternalStorageDirectory() + "/MyTinyScan_PDF/picture");; this.cacheLocation = new File(getFilesDir() + "/MyTinyScan_PDF/picture"))
    {
      this.cacheLocation.mkdirs();
      if ((!this.cacheLocation.exists()) || (!this.cacheLocation.isDirectory())) {
        break;
      }
      File[] arrayOfFile = this.cacheLocation.listFiles();
      int i = 0;
      while (i < arrayOfFile.length)
      {
        arrayOfFile[i].delete();
        i += 1;
      }
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
    do
    {
      do
      {
        return;
      } while (paramInt2 != -1);
      this.myApplication.setPhotofrom(false);
      this.myApplication.setPhotopath(getRealPathFromURI(paramIntent.getData()));
      if (this.myApplication.getPhotopath() != null)
      {
        if (new File(this.myApplication.getPhotopath()).exists()) {
          try
          {
            Thread.sleep(500L);
            startActivity(new Intent(this.context, Activity_Detect.class));
            return;
          }
          catch (InterruptedException paramIntent)
          {
            for (;;)
            {
              paramIntent.printStackTrace();
            }
          }
        }
        paramIntent = Toast.makeText(this.context, getString(2131296438), 0);
        paramIntent.setGravity(17, 0, 0);
        paramIntent.show();
        return;
      }
      paramIntent = Toast.makeText(this.context, getString(2131296439), 0);
      paramIntent.setGravity(17, 0, 0);
      paramIntent.show();
      return;
    } while ((paramInt2 != -1) || (paramInt1 != 10001));
    paramIntent = this.context.getSharedPreferences("TinyScanPro", 0);
    if ((paramIntent.getInt("newversion_1.2.7_first", -1) == 1) && (!"".equals(Util.FileName())) && (Util.FileName().length() > 11)) {
      FlurryAgent.logEvent("8_D" + ((new Date().getTime() - Util.strToDate(Util.FileName().substring(1, 11).toString()).getTime()) / 24L * 60L * 60L * 1000L + 1L));
    }
    if (!paramIntent.getString("newversion_firsttime", "").equals(""))
    {
      paramInt2 = (int)((new Date().getTime() - Util.stringToDate1(paramIntent.getString("newversion_firsttime", "")).getTime()) / 86400000L);
      if (paramInt2 > 50) {
        FlurryAgent.logEvent("A_IAP_50");
      }
    }
    else
    {
      if (this.mapp.getAdvOrChargeOrNormal() != 1) {
        break label517;
      }
      FlurryAgent.logEvent("7_UserAds_IAP");
    }
    for (;;)
    {
      paramIntent = getSharedPreferences("msp", 0).edit();
      paramIntent.putBoolean("GOOGLE_IAP", true);
      this.mapp.setIsBuyGoogleAds(true);
      this.mapp.setAdvOrChargeOrNormal(3);
      paramIntent.commit();
      paramIntent = new Message();
      paramIntent.what = 10020;
      this.handler.sendMessage(paramIntent);
      thankBuy("Thank you for upgrading to pro! ");
      return;
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        FlurryAgent.logEvent("A_IAP_" + paramInt1);
        paramInt1 += 1;
      }
      break;
      label517:
      if (this.mapp.getAdvOrChargeOrNormal() == 2) {
        FlurryAgent.logEvent("7_UserDoc_IAP");
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(2130903156);
    makefolder();
    this.mApp = MyApp.getApp();
    this.context = this;
    instance = this;
    this.ac = this;
    this.myApplication = MyApplication.getApplication(this.context);
    paramBundle = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("ExitApp");
    registerReceiver(this.broadcastReceiver, localIntentFilter);
    this.preferences = getSharedPreferences("TinyScanPro", 0);
    this.natualrotation = getWindowManager().getDefaultDisplay().getRotation();
    this.editor = this.preferences.edit();
    this.fl = ((FrameLayout)findViewById(2131755594));
    this.picturelay = ((LinearLayout)findViewById(2131755607));
    this.viewsee = ((ImageView)findViewById(2131755595));
    this.mcamerawidth = paramBundle.heightPixels;
    this.mcameraheight = (paramBundle.widthPixels - 100);
    this.scrollView = ((HorizontalScrollView)findViewById(2131755606));
    this.clicktime = new ArrayList();
    this.light = ((ImageView)findViewById(2131755096));
    this.light.setOnClickListener(this.mlistener);
    this.light2 = ((ImageView)findViewById(2131755599));
    if (this.light2 != null) {
      this.light2.setOnClickListener(this.mlistener);
    }
    this.light3 = ((ImageView)findViewById(2131755602));
    this.light3.setOnClickListener(this.mlistener);
    this.light4 = ((ImageView)findViewById(2131755605));
    this.light4.setOnClickListener(this.mlistener);
    this.isflashon = this.preferences.getBoolean("isflashon", false);
    this.r1 = ((RelativeLayout)findViewById(2131755596));
    this.r2 = ((RelativeLayout)findViewById(2131755597));
    this.r3 = ((RelativeLayout)findViewById(2131755600));
    this.r4 = ((RelativeLayout)findViewById(2131755603));
    int j;
    int i;
    if (this.isflashon) {
      if (this.myApplication.isPad())
      {
        this.light.setImageResource(2130837775);
        if (this.light2 != null) {
          this.light2.setImageResource(2130837775);
        }
        this.light3.setImageResource(2130837775);
        this.light4.setImageResource(2130837775);
        this.takepicture = ((ImageView)findViewById(2131755586));
        this.takepicture.setEnabled(false);
        this.takepicture.setOnClickListener(this.mlistener);
        this.takepicture2 = ((ImageView)findViewById(2131755598));
        this.takepicture2.setEnabled(false);
        this.takepicture2.setOnClickListener(this.mlistener);
        this.takepicture3 = ((ImageView)findViewById(2131755601));
        this.takepicture3.setEnabled(false);
        this.takepicture3.setOnClickListener(this.mlistener);
        this.takepicture4 = ((ImageView)findViewById(2131755604));
        this.takepicture4.setEnabled(false);
        this.takepicture4.setOnClickListener(this.mlistener);
        this.preview_single = ((ImageView)findViewById(2131755588));
        this.preview_batch = ((ImageView)findViewById(2131755587));
        this.preview_save = ((ImageView)findViewById(2131755591));
        this.preview_imagephoto = ((ImageView)findViewById(2131755589));
        this.previewnum = ((TextView)findViewById(2131755590));
        this.preview_single.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (Activity_PadCamera.this.isBatch)
            {
              Activity_PadCamera.this.preview_single.setImageResource(2130837618);
              Activity_PadCamera.this.preview_batch.setImageResource(2130837606);
              Activity_PadCamera.access$002(Activity_PadCamera.this, false);
              paramAnonymousView = Toast.makeText(Activity_PadCamera.this.context, Activity_PadCamera.this.getResources().getString(2131296495), 0);
              paramAnonymousView.setGravity(17, 0, 0);
              paramAnonymousView.show();
            }
          }
        });
        this.preview_batch.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if ((Activity_PadCamera.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_PadCamera.this.mapp.getAdvOrChargeOrNormal() == 1))
            {
              if (Activity_PadCamera.this.isBatch) {
                return;
              }
              Activity_PadCamera.this.preview_single.setImageResource(2130837617);
              Activity_PadCamera.this.preview_batch.setImageResource(2130837607);
              Activity_PadCamera.access$002(Activity_PadCamera.this, true);
              paramAnonymousView = Toast.makeText(Activity_PadCamera.this.context, Activity_PadCamera.this.getResources().getString(2131296365), 0);
              paramAnonymousView.setGravity(17, 0, 0);
              paramAnonymousView.show();
              return;
            }
            Activity_PadCamera.this.showProIapBuyDialog();
          }
        });
        this.preview_save.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if ((Activity_PadCamera.this.picturepath != null) && (Activity_PadCamera.this.picturepath.size() > 0) && (Activity_PadCamera.this.isBatch))
            {
              if (Activity_PadCamera.this.mCamera != null) {
                Activity_PadCamera.this.mCamera.stopPreview();
              }
              Activity_PadCamera.this.myApplication.setPicturepath(Activity_PadCamera.this.picturepath);
              Activity_PadCamera.this.startActivity(new Intent(Activity_PadCamera.this.context, Activity_MoreProcess1.class));
            }
          }
        });
        if (!this.mapp.getIsShowBatch())
        {
          this.previewnum.setVisibility(8);
          this.preview_single.setVisibility(8);
          this.preview_batch.setVisibility(8);
          this.preview_save.setVisibility(8);
          this.preview_imagephoto.setVisibility(8);
        }
        if (this.myApplication.isPad()) {
          this.mtool = new tool(this.context, this.handler);
        }
        if ((Build.MANUFACTURER.equals("Amazon")) && ((Build.MODEL.equals("KFAPWI")) || (Build.MODEL.equals("KFAPWA")) || (Build.MODEL.equals("KFTHWA")) || (Build.MODEL.equals("KFTHWI")) || (Build.MODEL.equals("KFSOWI")) || (Build.MODEL.equals("KFJWA")) || (Build.MODEL.equals("KFJWI")) || (Build.MODEL.equals("KFTT")))) {
          this.myApplication.setAmazon(true);
        }
        paramBundle = getPackageManager().getSystemAvailableFeatures();
        j = paramBundle.length;
        i = 0;
      }
    }
    for (;;)
    {
      if (i < j)
      {
        if ("android.hardware.camera.flash".equals(paramBundle[i].name))
        {
          this.light.setVisibility(0);
          if (this.light2 != null) {
            this.light2.setVisibility(0);
          }
          this.light3.setVisibility(0);
          this.light4.setVisibility(0);
        }
      }
      else
      {
        if (this.mapp.getAdvOrChargeOrNormal() != 3)
        {
          this.iapBuy = new IAPBuy(this);
          this.iapBuy.buyGoogleAdvPro();
        }
        return;
        this.light.setImageResource(2130837776);
        this.light3.setImageResource(2130837776);
        this.light4.setImageResource(2130837776);
        if (this.light2 == null) {
          break;
        }
        this.light2.setImageResource(2130837776);
        break;
        if (this.myApplication.isPad())
        {
          this.light.setImageResource(2130837775);
          if (this.light2 != null) {
            this.light2.setImageResource(2130837775);
          }
          this.light3.setImageResource(2130837775);
          this.light4.setImageResource(2130837775);
          break;
        }
        this.light.setImageResource(2130837776);
        if (this.light2 != null) {
          this.light2.setImageResource(2130837776);
        }
        this.light3.setImageResource(2130837776);
        this.light4.setImageResource(2130837776);
        break;
      }
      if (this.light2 != null) {
        this.light2.setVisibility(4);
      }
      this.light3.setVisibility(4);
      this.light4.setVisibility(4);
      this.light.setVisibility(4);
      i += 1;
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.broadcastReceiver);
    this.photodate = null;
    this.mPreview = null;
    this.focusview = null;
    this.mCamera = null;
    this.mtool = null;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if ((this.picturepath != null) && (this.picturepath.size() > 0)) {
        new AlertDialog.Builder(this.context).setTitle(getResources().getString(2131296383)).setMessage(getResources().getString(2131296384)).setNegativeButton(getResources().getString(2131296372), null).setNeutralButton(getResources().getString(2131296464), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            Activity_PadCamera.this.finish();
          }
        }).create().show();
      }
      for (;;)
      {
        return true;
        finish();
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.isFinish = true;
    if (this.mCamera != null)
    {
      this.mCamera.stopPreview();
      this.mCamera.setPreviewCallback(null);
      this.mCamera.release();
      this.mCamera = null;
    }
    if (this.myApplication.isPad()) {
      this.mtool.disable();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    this.isFinish = false;
    if (this.myApplication.isPad()) {
      this.mtool.enable();
    }
    this.picturepath = new ArrayList();
    this.takepicture.setEnabled(false);
    this.takepicture2.setEnabled(false);
    this.takepicture3.setEnabled(false);
    this.takepicture4.setEnabled(false);
    this.preview_batch.setVisibility(0);
    this.preview_single.setVisibility(0);
    this.preview_imagephoto.setVisibility(8);
    this.preview_save.setVisibility(8);
    this.previewnum.setVisibility(8);
    if (!this.mapp.getIsShowBatch())
    {
      this.previewnum.setVisibility(8);
      this.preview_single.setVisibility(8);
      this.preview_batch.setVisibility(8);
      this.preview_save.setVisibility(8);
      this.preview_imagephoto.setVisibility(8);
    }
    this.mApp.clearData();
    this.fl.removeAllViews();
    if (this.mCamera != null)
    {
      this.mCamera.stopPreview();
      this.mCamera.setPreviewCallback(null);
      this.mCamera.release();
      this.mCamera = null;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        Activity_PadCamera.access$602(Activity_PadCamera.this, Activity_PadCamera.this.getCameraInstance());
        Message localMessage = new Message();
        localMessage.what = 4;
        Activity_PadCamera.this.handler.sendMessage(localMessage);
      }
    }).start();
  }
  
  /* Error */
  @SuppressLint({"SimpleDateFormat"})
  public void save(Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 1035	com/appxy/tinyscanfree/Activity_PadCamera:ExistSDCard	()Z
    //   4: ifeq +435 -> 439
    //   7: aload_0
    //   8: getfield 210	com/appxy/tinyscanfree/Activity_PadCamera:path	Ljava/lang/String;
    //   11: astore 4
    //   13: new 353	java/io/File
    //   16: dup
    //   17: aload 4
    //   19: invokespecial 356	java/io/File:<init>	(Ljava/lang/String;)V
    //   22: invokevirtual 360	java/io/File:list	()[Ljava/lang/String;
    //   25: astore_3
    //   26: new 179	java/util/ArrayList
    //   29: dup
    //   30: invokespecial 180	java/util/ArrayList:<init>	()V
    //   33: astore 5
    //   35: iconst_0
    //   36: istore_2
    //   37: iload_2
    //   38: aload_3
    //   39: arraylength
    //   40: if_icmpge +33 -> 73
    //   43: aload_3
    //   44: iload_2
    //   45: aaload
    //   46: ldc_w 1037
    //   49: invokevirtual 1041	java/lang/String:matches	(Ljava/lang/String;)Z
    //   52: ifeq +14 -> 66
    //   55: aload 5
    //   57: aload_3
    //   58: iload_2
    //   59: aaload
    //   60: invokeinterface 1046 2 0
    //   65: pop
    //   66: iload_2
    //   67: iconst_1
    //   68: iadd
    //   69: istore_2
    //   70: goto -33 -> 37
    //   73: aload 5
    //   75: invokeinterface 1047 1 0
    //   80: ifle +284 -> 364
    //   83: aload 5
    //   85: aload_0
    //   86: getfield 231	com/appxy/tinyscanfree/Activity_PadCamera:comparator3	Ljava/util/Comparator;
    //   89: invokestatic 1053	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   92: aload 5
    //   94: aload 5
    //   96: invokeinterface 1047 1 0
    //   101: iconst_1
    //   102: isub
    //   103: invokeinterface 1057 2 0
    //   108: checkcast 240	java/lang/String
    //   111: bipush 15
    //   113: bipush 18
    //   115: invokevirtual 665	java/lang/String:substring	(II)Ljava/lang/String;
    //   118: invokestatic 1062	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   121: iconst_1
    //   122: iadd
    //   123: istore_2
    //   124: aload 5
    //   126: iconst_0
    //   127: invokeinterface 1057 2 0
    //   132: checkcast 240	java/lang/String
    //   135: iconst_0
    //   136: bipush 14
    //   138: invokevirtual 665	java/lang/String:substring	(II)Ljava/lang/String;
    //   141: astore_3
    //   142: iload_2
    //   143: bipush 10
    //   145: if_icmpge +119 -> 264
    //   148: new 188	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   155: aload_3
    //   156: iconst_0
    //   157: bipush 14
    //   159: invokevirtual 665	java/lang/String:substring	(II)Ljava/lang/String;
    //   162: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: aload_0
    //   166: getfield 699	com/appxy/tinyscanfree/Activity_PadCamera:mapp	Lcom/appxy/tinyscanfree/MyApplication;
    //   169: invokevirtual 1065	com/appxy/tinyscanfree/MyApplication:getSizeid	()I
    //   172: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   175: ldc_w 1067
    //   178: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: iload_2
    //   182: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   185: ldc_w 1069
    //   188: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: astore_3
    //   195: new 353	java/io/File
    //   198: dup
    //   199: new 188	java/lang/StringBuilder
    //   202: dup
    //   203: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   206: aload 4
    //   208: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   211: ldc_w 1071
    //   214: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: aload_3
    //   218: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   224: invokespecial 356	java/io/File:<init>	(Ljava/lang/String;)V
    //   227: astore_3
    //   228: new 1073	java/io/BufferedOutputStream
    //   231: dup
    //   232: new 1075	java/io/FileOutputStream
    //   235: dup
    //   236: aload_3
    //   237: invokespecial 1078	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   240: invokespecial 1081	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   243: astore_3
    //   244: aload_1
    //   245: getstatic 1087	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   248: bipush 85
    //   250: aload_3
    //   251: invokevirtual 1093	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   254: pop
    //   255: aload_3
    //   256: invokevirtual 1098	java/io/OutputStream:flush	()V
    //   259: aload_3
    //   260: invokevirtual 1101	java/io/OutputStream:close	()V
    //   263: return
    //   264: iload_2
    //   265: bipush 100
    //   267: if_icmpge +53 -> 320
    //   270: new 188	java/lang/StringBuilder
    //   273: dup
    //   274: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   277: aload_3
    //   278: iconst_0
    //   279: bipush 14
    //   281: invokevirtual 665	java/lang/String:substring	(II)Ljava/lang/String;
    //   284: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: aload_0
    //   288: getfield 699	com/appxy/tinyscanfree/Activity_PadCamera:mapp	Lcom/appxy/tinyscanfree/MyApplication;
    //   291: invokevirtual 1065	com/appxy/tinyscanfree/MyApplication:getSizeid	()I
    //   294: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   297: ldc_w 1103
    //   300: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: iload_2
    //   304: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   307: ldc_w 1069
    //   310: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   313: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   316: astore_3
    //   317: goto -122 -> 195
    //   320: new 188	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   327: aload_3
    //   328: iconst_0
    //   329: bipush 14
    //   331: invokevirtual 665	java/lang/String:substring	(II)Ljava/lang/String;
    //   334: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: aload_0
    //   338: getfield 699	com/appxy/tinyscanfree/Activity_PadCamera:mapp	Lcom/appxy/tinyscanfree/MyApplication;
    //   341: invokevirtual 1065	com/appxy/tinyscanfree/MyApplication:getSizeid	()I
    //   344: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   347: iload_2
    //   348: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   351: ldc_w 1069
    //   354: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: astore_3
    //   361: goto -166 -> 195
    //   364: new 1105	java/sql/Timestamp
    //   367: dup
    //   368: invokestatic 1110	java/lang/System:currentTimeMillis	()J
    //   371: invokespecial 1112	java/sql/Timestamp:<init>	(J)V
    //   374: astore_3
    //   375: new 1114	java/text/SimpleDateFormat
    //   378: dup
    //   379: ldc_w 1116
    //   382: getstatic 1122	java/util/Locale:US	Ljava/util/Locale;
    //   385: invokespecial 1125	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   388: aload_3
    //   389: invokevirtual 1129	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   392: astore_3
    //   393: new 188	java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial 189	java/lang/StringBuilder:<init>	()V
    //   400: aload_3
    //   401: iconst_0
    //   402: bipush 14
    //   404: invokevirtual 665	java/lang/String:substring	(II)Ljava/lang/String;
    //   407: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: aload_0
    //   411: getfield 699	com/appxy/tinyscanfree/Activity_PadCamera:mapp	Lcom/appxy/tinyscanfree/MyApplication;
    //   414: invokevirtual 1065	com/appxy/tinyscanfree/MyApplication:getSizeid	()I
    //   417: invokevirtual 750	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   420: ldc_w 1131
    //   423: invokevirtual 204	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   429: astore_3
    //   430: goto -235 -> 195
    //   433: astore_1
    //   434: aload_1
    //   435: invokevirtual 558	java/io/IOException:printStackTrace	()V
    //   438: return
    //   439: aload_0
    //   440: getfield 320	com/appxy/tinyscanfree/Activity_PadCamera:context	Landroid/content/Context;
    //   443: aload_0
    //   444: invokevirtual 985	com/appxy/tinyscanfree/Activity_PadCamera:getResources	()Landroid/content/res/Resources;
    //   447: ldc_w 1132
    //   450: invokevirtual 989	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   453: iconst_0
    //   454: invokestatic 621	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   457: invokevirtual 626	android/widget/Toast:show	()V
    //   460: return
    //   461: astore_1
    //   462: goto -28 -> 434
    //   465: astore_1
    //   466: return
    //   467: astore_1
    //   468: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	469	0	this	Activity_PadCamera
    //   0	469	1	paramBitmap	Bitmap
    //   36	312	2	i	int
    //   25	405	3	localObject	Object
    //   11	196	4	str	String
    //   33	92	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   228	244	433	java/io/IOException
    //   244	263	461	java/io/IOException
    //   228	244	465	java/io/FileNotFoundException
    //   244	263	467	java/io/FileNotFoundException
  }
  
  public int setCameraDisplayOrientation(Activity paramActivity, int paramInt, Camera paramCamera)
  {
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    Camera.getCameraInfo(paramInt, localCameraInfo);
    int i = paramActivity.getWindowManager().getDefaultDisplay().getRotation();
    paramInt = 0;
    switch (i)
    {
    default: 
      if (localCameraInfo.facing != 1) {
        break;
      }
    }
    for (paramInt = (360 - (localCameraInfo.orientation + paramInt) % 360) % 360;; paramInt = (localCameraInfo.orientation - paramInt + 360) % 360)
    {
      paramCamera.setDisplayOrientation(paramInt);
      return paramInt;
      paramInt = 0;
      break;
      paramInt = 90;
      break;
      paramInt = 180;
      break;
      paramInt = 270;
      break;
    }
  }
  
  public void takePicture()
  {
    Object localObject;
    List localList;
    if (this.mCamera != null)
    {
      localObject = this.mCamera.getParameters();
      localList = ((Camera.Parameters)localObject).getSupportedFlashModes();
      if (localList != null)
      {
        if (!this.isflashon) {
          break label251;
        }
        if (!localList.contains("torch")) {
          break label229;
        }
        ((Camera.Parameters)localObject).setFlashMode("torch");
      }
      this.mCamera.setParameters((Camera.Parameters)localObject);
      this.takepicture.setEnabled(false);
      this.takepicture2.setEnabled(false);
      this.takepicture3.setEnabled(false);
      this.takepicture4.setEnabled(false);
      if ((this.clicktime == null) || (!this.clicktime.isEmpty()) || (this.hasFocus)) {
        break label273;
      }
      this.focusview.setPosition(this.focusview.getWidth() / 2, this.focusview.getHeight() / 2);
      this.focusview.setFocus(false, false);
      this.focusview.setVisibility(0);
      localObject = new Message();
      ((Message)localObject).what = 2;
      this.handler.sendMessageDelayed((Message)localObject, 4000L);
      this.mCamera.autoFocus(new Camera.AutoFocusCallback()
      {
        public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
        {
          Activity_PadCamera.this.focusview.setFocus(true, paramAnonymousBoolean);
          try
          {
            paramAnonymousCamera.takePicture(null, null, Activity_PadCamera.this.jpegCallback);
            Activity_PadCamera.this.takepicture.setEnabled(true);
            Activity_PadCamera.this.takepicture2.setEnabled(true);
            Activity_PadCamera.this.takepicture3.setEnabled(true);
            Activity_PadCamera.this.takepicture4.setEnabled(true);
            return;
          }
          catch (Exception paramAnonymousCamera)
          {
            for (;;)
            {
              paramAnonymousCamera.printStackTrace();
            }
          }
        }
      });
    }
    for (;;)
    {
      this.takepicture.setEnabled(true);
      this.takepicture2.setEnabled(true);
      this.takepicture3.setEnabled(true);
      this.takepicture4.setEnabled(true);
      return;
      label229:
      if (!localList.contains("on")) {
        break;
      }
      ((Camera.Parameters)localObject).setFlashMode("on");
      break;
      label251:
      if (!localList.contains("off")) {
        break;
      }
      ((Camera.Parameters)localObject).setFlashMode("off");
      break;
      label273:
      this.mCamera.takePicture(null, null, this.jpegCallback);
    }
  }
  
  class Preview
    extends ViewGroup
    implements SurfaceHolder.Callback
  {
    int camerawidth;
    Comparator<Camera.Size> comparator = new Comparator()
    {
      public int compare(Camera.Size paramAnonymousSize1, Camera.Size paramAnonymousSize2)
      {
        return paramAnonymousSize1.width * paramAnonymousSize1.height - paramAnonymousSize2.width * paramAnonymousSize2.height;
      }
    };
    Camera mCamera;
    SurfaceHolder mHolder;
    List<Camera.Size> mSupportedPreviewSizes;
    SurfaceView mSurfaceView;
    MyApplication myApplication;
    int photosize = 0;
    int pictureheight;
    int picturewidth;
    int previewheight;
    int previewwidth;
    
    Preview(Context paramContext, Camera paramCamera, int paramInt)
    {
      super();
      this.mCamera = paramCamera;
      this.camerawidth = paramInt;
      this.myApplication = MyApplication.getApplication(paramContext);
      this.mSurfaceView = new SurfaceView(paramContext);
      addView(this.mSurfaceView);
      this.mHolder = this.mSurfaceView.getHolder();
      this.mHolder.addCallback(this);
      this.mHolder.setType(3);
      if (this.mCamera != null)
      {
        this.mSupportedPreviewSizes = this.mCamera.getParameters().getSupportedPreviewSizes();
        this$1 = new ArrayList();
        paramContext = this.mCamera.getParameters().getSupportedPictureSizes();
        int i = paramContext.size();
        paramInt = 0;
        while (paramInt < i)
        {
          Activity_PadCamera.this.add(paramContext.get(paramInt));
          paramInt += 1;
        }
        if (Activity_PadCamera.this.size() > 0)
        {
          Collections.sort(Activity_PadCamera.this, this.comparator);
          this.picturewidth = ((Camera.Size)Activity_PadCamera.this.get(Activity_PadCamera.this.size() - 1)).width;
          this.pictureheight = ((Camera.Size)Activity_PadCamera.this.get(Activity_PadCamera.this.size() - 1)).height;
        }
      }
    }
    
    private Camera.Size getOptimalPreviewSize(List<Camera.Size> paramList, int paramInt1, int paramInt2)
    {
      double d2 = paramInt1 / paramInt2;
      Object localObject2;
      if (paramList == null) {
        localObject2 = null;
      }
      Object localObject1;
      do
      {
        return localObject2;
        localObject1 = null;
        d1 = Double.MAX_VALUE;
        localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          localObject2 = (Camera.Size)localIterator.next();
          if ((Math.abs(((Camera.Size)localObject2).width / ((Camera.Size)localObject2).height - d2) <= 0.1D) && (Math.abs(((Camera.Size)localObject2).height - paramInt2) < d1))
          {
            localObject1 = localObject2;
            d1 = Math.abs(((Camera.Size)localObject2).height - paramInt2);
          }
        }
        localObject2 = localObject1;
      } while (localObject1 != null);
      double d1 = Double.MAX_VALUE;
      Iterator localIterator = paramList.iterator();
      for (;;)
      {
        localObject2 = localObject1;
        if (!localIterator.hasNext()) {
          break;
        }
        paramList = (Camera.Size)localIterator.next();
        if (Math.abs(paramList.height - paramInt2) < d1)
        {
          localObject1 = paramList;
          d1 = Math.abs(paramList.height - paramInt2);
        }
      }
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      View localView;
      int i;
      int j;
      if ((paramBoolean) && (getChildCount() > 0))
      {
        localView = getChildAt(0);
        i = paramInt3 - paramInt1;
        j = paramInt4 - paramInt2;
        paramInt2 = i;
        paramInt3 = j;
        paramInt4 = paramInt3;
        paramInt1 = paramInt2;
        if (this.previewwidth != 0)
        {
          paramInt4 = paramInt3;
          paramInt1 = paramInt2;
          if (this.previewheight != 0)
          {
            paramInt1 = this.previewwidth;
            paramInt4 = this.previewheight;
          }
        }
        if (i * paramInt4 > j * paramInt1)
        {
          paramInt1 = paramInt1 * j / paramInt4;
          localView.layout((i - paramInt1) / 2, 0, (i + paramInt1) / 2, j);
        }
      }
      else
      {
        return;
      }
      paramInt1 = paramInt4 * i / paramInt1;
      localView.layout(0, (j - paramInt1) / 2, i, (j + paramInt1) / 2);
    }
    
    protected void onMeasure(int paramInt1, int paramInt2)
    {
      if ((this.picturewidth != 0) && (this.pictureheight != 0))
      {
        paramInt2 = this.camerawidth;
        paramInt1 = this.camerawidth * this.picturewidth / this.pictureheight;
      }
      for (;;)
      {
        setMeasuredDimension(paramInt1, paramInt2);
        if (this.mSupportedPreviewSizes != null)
        {
          Camera.Size localSize = getOptimalPreviewSize(this.mSupportedPreviewSizes, paramInt1, paramInt2);
          this.previewwidth = localSize.width;
          this.previewheight = localSize.height;
        }
        return;
      }
    }
    
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      Activity_PadCamera.this.focusview.setPosition(Activity_PadCamera.this.focusview.getWidth() / 2, Activity_PadCamera.this.focusview.getHeight() / 2);
      Activity_PadCamera.this.focusview.setFocus(false, false);
      Activity_PadCamera.this.focusview.setVisibility(0);
      switch (paramMotionEvent.getAction())
      {
      }
      for (;;)
      {
        return super.onTouchEvent(paramMotionEvent);
        Activity_PadCamera.this.hasFocus = false;
        Activity_PadCamera.this.clicktime.add(Integer.valueOf(0));
        Message localMessage = new Message();
        localMessage.what = 3;
        Activity_PadCamera.this.handler.sendMessageDelayed(localMessage, 5000L);
      }
    }
    
    public void setCamera(Camera paramCamera)
    {
      this.mCamera = paramCamera;
    }
    
    @SuppressLint({"InlinedApi"})
    public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
    {
      if (this.mCamera != null)
      {
        paramSurfaceHolder = this.mCamera.getParameters();
        if ((this.previewwidth != 0) && (this.previewheight != 0)) {
          paramSurfaceHolder.setPreviewSize(this.previewwidth, this.previewheight);
        }
        Activity_PadCamera.this.focusview.setLayoutParams(new FrameLayout.LayoutParams(paramInt2, paramInt3));
        paramSurfaceHolder.setPictureFormat(256);
        if ((this.picturewidth != 0) && (this.pictureheight != 0))
        {
          this.myApplication.setPhotowidth(this.pictureheight);
          this.myApplication.setPhotoheight(this.picturewidth);
          paramSurfaceHolder.setPictureSize(this.picturewidth, this.pictureheight);
        }
        if ((this.myApplication.isAmazon()) && (!this.myApplication.isFront())) {
          break label211;
        }
      }
      for (;;)
      {
        if (paramSurfaceHolder.getSupportedFocusModes().contains("continuous-picture")) {
          paramSurfaceHolder.setFocusMode("continuous-picture");
        }
        Activity_PadCamera.this.mapp.setOritation(Activity_PadCamera.this.setCameraDisplayOrientation(Activity_PadCamera.this.ac, Activity_PadCamera.this.cameraId, this.mCamera));
        this.mCamera.setParameters(paramSurfaceHolder);
        this.mCamera.startPreview();
        return;
        label211:
        this.mCamera.setDisplayOrientation(180);
      }
    }
    
    public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
    {
      try
      {
        if (this.mCamera != null) {
          this.mCamera.setPreviewDisplay(paramSurfaceHolder);
        }
        return;
      }
      catch (Exception paramSurfaceHolder) {}catch (IOException paramSurfaceHolder) {}
    }
    
    public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {}
  }
}
