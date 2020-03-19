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
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
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

public class Activity_CameraPreview
  extends BaseActivity
{
  private static final int RC_REQUEST = 10001;
  public static final int TIME = 5000;
  public static int height = 0;
  public static Activity_CameraPreview instance = null;
  private static int max = 8000000;
  private static int maxperm = 130000;
  public static int width = 0;
  private Activity ac;
  protected BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Activity_CameraPreview.this.finish();
    }
  };
  private File cacheLocation;
  int cameraId = 0;
  ArrayList<Integer> clicktime;
  Comparator<Camera.Size> comparator = new Comparator()
  {
    public int compare(Camera.Size paramAnonymousSize1, Camera.Size paramAnonymousSize2)
    {
      return paramAnonymousSize1.width * paramAnonymousSize1.height - paramAnonymousSize2.width * paramAnonymousSize2.height;
    }
  };
  private Context context;
  private SharedPreferences.Editor editor;
  private RelativeLayout fl;
  private FocusView focusview;
  @SuppressLint({"HandlerLeak"})
  Handler handler = new Handler()
  {
    private Animation mAnimation;
    private Bitmap previre_bitmap;
    
    @SuppressLint({"InlinedApi"})
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
          Object localObject = null;
          ObjectAnimator localObjectAnimator = null;
          Activity_CameraPreview.this.oldrotation = Activity_CameraPreview.this.oritation;
          int i = 0;
          try
          {
            int j = Activity_CameraPreview.this.mtool.getRotation();
            int k = Activity_CameraPreview.this.natualrotation;
            i = j - k;
          }
          catch (Exception localException2)
          {
            label114:
            label312:
            label441:
            for (;;) {}
          }
          if (i == -1)
          {
            Activity_CameraPreview.this.oritation = 3;
            if (Activity_CameraPreview.this.oritation != 0) {
              break label441;
            }
            if (Activity_CameraPreview.this.oldrotation != 3) {
              break label312;
            }
            localObject = ObjectAnimator.ofFloat(Activity_CameraPreview.this.takepicture, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() + 90.0F });
            localObjectAnimator = ObjectAnimator.ofFloat(Activity_CameraPreview.this.light, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() + 90.0F });
          }
          while ((localObject != null) && (localObjectAnimator != null))
          {
            AnimatorSet localAnimatorSet = new AnimatorSet();
            localAnimatorSet.play((Animator)localObject).with(localObjectAnimator);
            localAnimatorSet.start();
            break;
            if (i == -2)
            {
              Activity_CameraPreview.this.oritation = 2;
              break label114;
            }
            if (i == -3)
            {
              Activity_CameraPreview.this.oritation = 1;
              break label114;
            }
            Activity_CameraPreview.this.oritation = i;
            break label114;
            localObject = ObjectAnimator.ofFloat(Activity_CameraPreview.this.takepicture, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
            localObjectAnimator = ObjectAnimator.ofFloat(Activity_CameraPreview.this.light, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
            continue;
            if (Activity_CameraPreview.this.oritation == 1)
            {
              localObject = ObjectAnimator.ofFloat(Activity_CameraPreview.this.takepicture, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
              localObjectAnimator = ObjectAnimator.ofFloat(Activity_CameraPreview.this.light, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
            }
            else if (Activity_CameraPreview.this.oritation == 2)
            {
              localObject = ObjectAnimator.ofFloat(Activity_CameraPreview.this.takepicture, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
              localObjectAnimator = ObjectAnimator.ofFloat(Activity_CameraPreview.this.light, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
            }
            else if (Activity_CameraPreview.this.oritation == 3)
            {
              if (Activity_CameraPreview.this.oldrotation == 0)
              {
                localObject = ObjectAnimator.ofFloat(Activity_CameraPreview.this.takepicture, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - 90.0F });
                localObjectAnimator = ObjectAnimator.ofFloat(Activity_CameraPreview.this.light, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - 90.0F });
              }
              else
              {
                localObject = ObjectAnimator.ofFloat(Activity_CameraPreview.this.takepicture, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
                localObjectAnimator = ObjectAnimator.ofFloat(Activity_CameraPreview.this.light, "rotation", new float[] { Activity_CameraPreview.this.takepicture.getRotation(), Activity_CameraPreview.this.takepicture.getRotation() - (Activity_CameraPreview.this.oldrotation - Activity_CameraPreview.this.oritation) * 90 });
                continue;
                if (Activity_CameraPreview.this.mCamera == null) {
                  break;
                }
                if (Activity_CameraPreview.this.isFinish)
                {
                  if (Activity_CameraPreview.this.mCamera != null) {
                    Activity_CameraPreview.this.mCamera.release();
                  }
                  Activity_CameraPreview.access$502(Activity_CameraPreview.this, null);
                  break;
                }
                Activity_CameraPreview.access$1002(Activity_CameraPreview.this, new Activity_CameraPreview.CameraView(Activity_CameraPreview.this, Activity_CameraPreview.this.context));
                localObject = new RelativeLayout.LayoutParams(-2, -2);
                Activity_CameraPreview.this.fl.addView(Activity_CameraPreview.this.mPreview, (ViewGroup.LayoutParams)localObject);
                Activity_CameraPreview.access$1202(Activity_CameraPreview.this, new FocusView(Activity_CameraPreview.this.context));
                Activity_CameraPreview.this.fl.addView(Activity_CameraPreview.this.focusview, (ViewGroup.LayoutParams)localObject);
                Activity_CameraPreview.this.focusview.setVisibility(4);
                localObject = Activity_CameraPreview.this.mCamera.getParameters();
                if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("auto"))
                {
                  if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("auto")) {
                    ((Camera.Parameters)localObject).setFocusMode("auto");
                  }
                  Activity_CameraPreview.this.mCamera.setParameters((Camera.Parameters)localObject);
                  Activity_CameraPreview.this.mPreview.setOnClickListener(new View.OnClickListener()
                  {
                    public void onClick(View paramAnonymous2View)
                    {
                      try
                      {
                        paramAnonymous2View = Activity_CameraPreview.this.mCamera.getParameters();
                        if (paramAnonymous2View.getSupportedFocusModes().contains("auto")) {
                          paramAnonymous2View.setFocusMode("auto");
                        }
                        List localList = paramAnonymous2View.getSupportedFlashModes();
                        if (localList != null)
                        {
                          if (!Activity_CameraPreview.this.isflashon) {
                            break label109;
                          }
                          if (localList.contains("on")) {
                            paramAnonymous2View.setFlashMode("on");
                          }
                        }
                        for (;;)
                        {
                          Activity_CameraPreview.this.mCamera.setParameters(paramAnonymous2View);
                          Activity_CameraPreview.this.mCamera.autoFocus(new Camera.AutoFocusCallback()
                          {
                            public void onAutoFocus(boolean paramAnonymous3Boolean, Camera paramAnonymous3Camera)
                            {
                              Activity_CameraPreview.this.hasFocus = paramAnonymous3Boolean;
                              Activity_CameraPreview.this.focusview.setFocus(true, paramAnonymous3Boolean);
                            }
                          });
                          return;
                          label109:
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
                localObject = Activity_CameraPreview.this.getExternalCacheDir().listFiles();
                i = 0;
                while (i < localObject.length)
                {
                  localObject[i].delete();
                  i += 1;
                }
                Activity_CameraPreview.this.takepicture.setEnabled(true);
                Activity_CameraPreview.this.takepicture.setClickable(true);
                break;
                Activity_CameraPreview.this.takepicture.setEnabled(true);
                if ((Activity_CameraPreview.this.progressDialog != null) && (Activity_CameraPreview.this.progressDialog.isShowing())) {
                  Activity_CameraPreview.this.progressDialog.dismiss();
                }
                Activity_CameraPreview.access$1302(Activity_CameraPreview.this, null);
                Activity_CameraPreview.access$1402(Activity_CameraPreview.this, null);
                if (Activity_CameraPreview.this.isBatch.booleanValue()) {
                  break;
                }
                Activity_CameraPreview.this.myApplication.setBatch(false);
                localObject = new Intent(Activity_CameraPreview.this.context, Activity_Detect.class);
                ((Intent)localObject).setFlags(67108864);
                Activity_CameraPreview.this.startActivity((Intent)localObject);
                break;
                if ((Activity_CameraPreview.this.progressDialog != null) && (Activity_CameraPreview.this.progressDialog.isShowing())) {
                  Activity_CameraPreview.this.progressDialog.dismiss();
                }
                Activity_CameraPreview.access$1302(Activity_CameraPreview.this, null);
                Activity_CameraPreview.access$1402(Activity_CameraPreview.this, null);
                Activity_CameraPreview.this.numbtn.setVisibility(0);
                Activity_CameraPreview.this.numbtn.setText(Activity_CameraPreview.this.picturepath.size() + "");
                Activity_CameraPreview.this.mCamera.startPreview();
                Activity_CameraPreview.this.takepicture.setEnabled(true);
                if (Activity_CameraPreview.this.picturepath.size() <= 0) {
                  break;
                }
                Activity_CameraPreview.this.preview_save.setVisibility(0);
                Activity_CameraPreview.this.preview_single.setVisibility(8);
                Activity_CameraPreview.this.preview_batch.setVisibility(8);
                if ((this.previre_bitmap != null) && (!this.previre_bitmap.isRecycled())) {
                  this.previre_bitmap.recycle();
                }
                this.previre_bitmap = null;
                this.previre_bitmap = BitmapTools.getPhoto2((String)Activity_CameraPreview.this.picturepath.get(Activity_CameraPreview.this.picturepath.size() - 1), 100, 100);
                localObject = new Matrix();
                ((Matrix)localObject).postScale(4.0F, 4.0F);
                ((Matrix)localObject).postRotate(Activity_CameraPreview.this.getImageOrientation((String)Activity_CameraPreview.this.picturepath.get(Activity_CameraPreview.this.picturepath.size() - 1)));
                try
                {
                  this.previre_bitmap = Bitmap.createBitmap(this.previre_bitmap, 0, 0, this.previre_bitmap.getWidth(), this.previre_bitmap.getHeight(), (Matrix)localObject, true);
                  Activity_CameraPreview.this.preview_imagephoto.setVisibility(0);
                  Activity_CameraPreview.this.preview_imagephoto.setImageBitmap(this.previre_bitmap);
                  this.mAnimation = AnimationUtils.loadAnimation(Activity_CameraPreview.this.context, 2130968590);
                  Activity_CameraPreview.this.preview_imagephoto.setAnimation(this.mAnimation);
                  Activity_CameraPreview.this.numbtn.setAnimation(this.mAnimation);
                  this.mAnimation.start();
                }
                catch (Exception localException1)
                {
                  for (;;)
                  {
                    localException1.printStackTrace();
                    ((Matrix)localObject).postScale(6.0F, 6.0F);
                    ((Matrix)localObject).postRotate(Activity_CameraPreview.this.getImageOrientation((String)Activity_CameraPreview.this.picturepath.get(Activity_CameraPreview.this.picturepath.size() - 1)));
                    this.previre_bitmap = Bitmap.createBitmap(this.previre_bitmap, 0, 0, this.previre_bitmap.getWidth(), this.previre_bitmap.getHeight(), (Matrix)localObject, true);
                  }
                }
                Activity_CameraPreview.this.takepicture.setEnabled(true);
                if (Activity_CameraPreview.this.focusview != null) {
                  Activity_CameraPreview.this.focusview.setVisibility(4);
                }
                if (Activity_CameraPreview.this.mCamera == null) {
                  break;
                }
                localObject = Activity_CameraPreview.this.mCamera.getParameters();
                if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("continuous-picture")) {
                  ((Camera.Parameters)localObject).setFocusMode("continuous-picture");
                }
                Activity_CameraPreview.this.mCamera.setParameters((Camera.Parameters)localObject);
                break;
                if (Activity_CameraPreview.this.clicktime == null) {
                  break;
                }
                Activity_CameraPreview.this.clicktime.remove(0);
                if (!Activity_CameraPreview.this.clicktime.isEmpty()) {
                  break;
                }
                Activity_CameraPreview.this.takepicture.setEnabled(true);
                if (Activity_CameraPreview.this.focusview != null) {
                  Activity_CameraPreview.this.focusview.setVisibility(4);
                }
                if (Activity_CameraPreview.this.mCamera != null)
                {
                  localObject = Activity_CameraPreview.this.mCamera.getParameters();
                  if (((Camera.Parameters)localObject).getSupportedFocusModes().contains("continuous-picture")) {
                    ((Camera.Parameters)localObject).setFocusMode("continuous-picture");
                  }
                  Activity_CameraPreview.this.mCamera.setParameters((Camera.Parameters)localObject);
                }
                Activity_CameraPreview.this.hasFocus = false;
                break;
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
  private Boolean isBatch = Boolean.valueOf(false);
  boolean isFinish = false;
  boolean isflashon = false;
  Camera.PictureCallback jpegCallback = new Camera.PictureCallback()
  {
    public void onPictureTaken(final byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      paramAnonymousCamera.setPreviewCallback(null);
      paramAnonymousCamera.stopPreview();
      Activity_CameraPreview.this.myApplication.setPhotofrom(true);
      if (!Activity_CameraPreview.this.isBatch.booleanValue())
      {
        if (Activity_CameraPreview.this.focusview != null) {
          Activity_CameraPreview.this.focusview.setVisibility(4);
        }
        Activity_CameraPreview.this.getCropImage(paramAnonymousArrayOfByte);
        return;
      }
      Activity_CameraPreview.access$1302(Activity_CameraPreview.this, GPUImageWrapper.createLoadingDialog(Activity_CameraPreview.this.context, "sf"));
      Activity_CameraPreview.this.progressDialog.show();
      Activity_CameraPreview.access$1402(Activity_CameraPreview.this, new Thread(new Runnable()
      {
        @SuppressLint({"SimpleDateFormat"})
        public void run()
        {
          try
          {
            Object localObject = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            Activity_CameraPreview.access$1902(Activity_CameraPreview.this, localSimpleDateFormat.format((Date)localObject));
            localObject = new FileOutputStream(Activity_CameraPreview.this.cacheLocation.getPath() + "/" + Activity_CameraPreview.this.name + ".jpg");
            ((FileOutputStream)localObject).write(paramAnonymousArrayOfByte);
            ((FileOutputStream)localObject).flush();
            ((FileOutputStream)localObject).close();
            Activity_CameraPreview.this.picturepath.add(Activity_CameraPreview.this.cacheLocation.getPath() + "/" + Activity_CameraPreview.this.name + ".jpg");
            localObject = new Message();
            ((Message)localObject).what = 6;
            Activity_CameraPreview.this.handler.sendMessage((Message)localObject);
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
      }));
      Activity_CameraPreview.this.mThread.start();
    }
  };
  private ImageView light;
  private MyApp mApp;
  private Camera mCamera;
  private CameraView mPreview;
  private Thread mThread;
  public int mcameraheight;
  public int mcamerawidth;
  View.OnClickListener mlistener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return;
      case 2131755586: 
        try
        {
          Activity_CameraPreview.this.takePicture();
          return;
        }
        catch (Exception paramAnonymousView)
        {
          paramAnonymousView.printStackTrace();
          return;
        }
      }
      if (Activity_CameraPreview.this.isflashon)
      {
        if (Activity_CameraPreview.this.myApplication.isPad()) {
          Activity_CameraPreview.this.light.setImageResource(2130837775);
        }
        for (;;)
        {
          Activity_CameraPreview.this.editor.putBoolean("isflashon", false);
          Activity_CameraPreview.this.editor.commit();
          Activity_CameraPreview.this.isflashon = false;
          return;
          Activity_CameraPreview.this.light.setImageResource(2130837775);
        }
      }
      if (Activity_CameraPreview.this.myApplication.isPad()) {
        Activity_CameraPreview.this.light.setImageResource(2130837776);
      }
      for (;;)
      {
        Activity_CameraPreview.this.editor.putBoolean("isflashon", true);
        Activity_CameraPreview.this.editor.commit();
        Activity_CameraPreview.this.isflashon = true;
        return;
        Activity_CameraPreview.this.light.setImageResource(2130837776);
      }
    }
  };
  tool mtool;
  private MyApplication myApplication;
  Camera.PictureCallback myPictureCallback_JPG = new Camera.PictureCallback()
  {
    public void onPictureTaken(byte[] paramAnonymousArrayOfByte, Camera paramAnonymousCamera)
    {
      paramAnonymousArrayOfByte = new BitmapFactory.Options();
      paramAnonymousArrayOfByte.inTempStorage = new byte['䀀'];
      paramAnonymousCamera = paramAnonymousCamera.getParameters().getPictureSize();
      int i = paramAnonymousCamera.height;
      float f = paramAnonymousCamera.width * i / 1024000;
      if (f > 4.0F) {
        paramAnonymousArrayOfByte.inSampleSize = 4;
      }
      while (f <= 3.0F) {
        return;
      }
      paramAnonymousArrayOfByte.inSampleSize = 2;
    }
  };
  private String name;
  int natualrotation;
  private TextView numbtn;
  int oldrotation = 0;
  int oritation = 0;
  ArrayList<String> picturepath;
  private SharedPreferences preferences;
  private ImageView preview_batch;
  private ImageView preview_imagephoto;
  private ImageView preview_save;
  private ImageView preview_single;
  private Dialog progressDialog;
  private ImageView takepicture;
  
  public Activity_CameraPreview() {}
  
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
        if ((Math.abs(((Camera.Size)localObject2).width / ((Camera.Size)localObject2).height - d2) <= 0.1D) && (Math.abs(((Camera.Size)localObject2).height - paramInt1) < d1))
        {
          localObject1 = localObject2;
          d1 = Math.abs(((Camera.Size)localObject2).height - paramInt1);
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
      if (Math.abs(paramList.height - paramInt1) < d1)
      {
        localObject1 = paramList;
        d1 = Math.abs(paramList.height - paramInt1);
      }
    }
  }
  
  private String getRealPathFromURI(Uri paramUri)
  {
    paramUri = new CursorLoader(this.context, paramUri, new String[] { "_data" }, null, null, null).loadInBackground();
    int i = paramUri.getColumnIndexOrThrow("_data");
    paramUri.moveToFirst();
    return paramUri.getString(i);
  }
  
  @SuppressLint({"InlinedApi"})
  private void initCamera()
  {
    Camera.Parameters localParameters = this.mCamera.getParameters();
    Object localObject1 = this.mCamera.getParameters().getSupportedPreviewSizes();
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = this.mCamera.getParameters().getSupportedPictureSizes();
    int j = ((List)localObject2).size();
    int i = 0;
    while (i < j)
    {
      localArrayList.add(((List)localObject2).get(i));
      i += 1;
    }
    j = 0;
    i = 0;
    int m = 0;
    int k = 0;
    localObject2 = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics((DisplayMetrics)localObject2);
    if (localArrayList.size() > 0)
    {
      Collections.sort(localArrayList, this.comparator);
      j = ((Camera.Size)localArrayList.get(localArrayList.size() - 1)).width;
      i = ((Camera.Size)localArrayList.get(localArrayList.size() - 1)).height;
    }
    if ((j != 0) && (i != 0))
    {
      localParameters.setPictureSize(j, i);
      this.editor.putInt("picturewidth", j);
      this.editor.putInt("pictureheight", i);
      this.editor.commit();
    }
    if ((j != 0) && (i != 0))
    {
      height = ((DisplayMetrics)localObject2).widthPixels;
      width = height * j / i;
    }
    i = m;
    j = k;
    if (localObject1 != null)
    {
      localObject1 = getOptimalPreviewSize((List)localObject1, width, height);
      i = ((Camera.Size)localObject1).width;
      j = ((Camera.Size)localObject1).height;
    }
    if ((i != 0) && (j != 0))
    {
      localParameters.setPreviewSize(i, j);
      this.editor.putInt("previewwidth", i);
      this.editor.putInt("prviewheight", j);
      this.editor.commit();
      this.mPreview.setLayoutParams(new RelativeLayout.LayoutParams(height, width));
      this.focusview.setLayoutParams(new RelativeLayout.LayoutParams(height, width));
    }
    localObject1 = getPackageManager().getSystemAvailableFeatures();
    j = localObject1.length;
    i = 0;
    for (;;)
    {
      if (i < j)
      {
        if ("android.hardware.camera.flash".equals(localObject1[i].name)) {
          this.light.setVisibility(0);
        }
      }
      else
      {
        localParameters.setPictureFormat(256);
        localObject1 = localParameters.getSupportedFocusModes();
        if (((List)localObject1).contains("auto")) {
          localParameters.setFocusMode("auto");
        }
        if (((List)localObject1).contains("continuous-picture")) {
          localParameters.setFocusMode("continuous-picture");
        }
        localParameters.setRotation(setCameraDisplayOrientation(this.ac, this.cameraId, this.mCamera));
        this.mCamera.setParameters(localParameters);
        return;
      }
      this.light.setVisibility(4);
      i += 1;
    }
  }
  
  public static Bitmap resizeImage(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k = (int)(i * 0.8D);
    int m = (int)(j * 0.8D);
    float f1 = k / i;
    float f2 = m / j;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
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
        Activity_CameraPreview.this.iapBuy.IAP_Buy();
      }
    });
    ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localAlertDialog.dismiss();
        Object localObject = Activity_CameraPreview.this.getPackageManager().getInstalledApplications(0);
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
          Activity_CameraPreview.this.startActivity((Intent)localObject);
          return;
        }
        catch (Exception paramAnonymousView)
        {
          Activity_CameraPreview.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appxy.tinyscan&hl=en")));
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
  
  /* Error */
  public Bitmap decodeImg(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore 4
    //   8: aload_3
    //   9: astore_2
    //   10: new 596	android/graphics/BitmapFactory$Options
    //   13: dup
    //   14: invokespecial 597	android/graphics/BitmapFactory$Options:<init>	()V
    //   17: astore 6
    //   19: aload_3
    //   20: astore_2
    //   21: aload 6
    //   23: iconst_1
    //   24: putfield 600	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   27: aload_3
    //   28: astore_2
    //   29: new 602	java/io/ByteArrayInputStream
    //   32: dup
    //   33: aload_1
    //   34: invokespecial 605	java/io/ByteArrayInputStream:<init>	([B)V
    //   37: astore_3
    //   38: new 607	java/lang/ref/SoftReference
    //   41: dup
    //   42: aload_3
    //   43: aconst_null
    //   44: aload 6
    //   46: invokestatic 613	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   49: invokespecial 616	java/lang/ref/SoftReference:<init>	(Ljava/lang/Object;)V
    //   52: invokevirtual 618	java/lang/ref/SoftReference:get	()Ljava/lang/Object;
    //   55: checkcast 506	android/graphics/Bitmap
    //   58: invokestatic 620	com/appxy/tinyscanfree/Activity_CameraPreview:resizeImage	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
    //   61: astore_2
    //   62: aload_1
    //   63: ifnull +3 -> 66
    //   66: aload_3
    //   67: ifnull +92 -> 159
    //   70: aload_3
    //   71: invokevirtual 625	java/io/InputStream:close	()V
    //   74: aload_2
    //   75: areturn
    //   76: astore_1
    //   77: aload_1
    //   78: invokevirtual 628	java/io/IOException:printStackTrace	()V
    //   81: aload_2
    //   82: areturn
    //   83: astore_2
    //   84: aload 4
    //   86: astore_3
    //   87: aload_2
    //   88: astore 4
    //   90: aload_3
    //   91: astore_2
    //   92: aload 4
    //   94: invokevirtual 629	java/lang/Exception:printStackTrace	()V
    //   97: aload_1
    //   98: ifnull +3 -> 101
    //   101: aload 5
    //   103: astore_2
    //   104: aload_3
    //   105: ifnull -31 -> 74
    //   108: aload_3
    //   109: invokevirtual 625	java/io/InputStream:close	()V
    //   112: aconst_null
    //   113: areturn
    //   114: astore_1
    //   115: aload_1
    //   116: invokevirtual 628	java/io/IOException:printStackTrace	()V
    //   119: aconst_null
    //   120: areturn
    //   121: astore_3
    //   122: aload_1
    //   123: ifnull +3 -> 126
    //   126: aload_2
    //   127: ifnull +7 -> 134
    //   130: aload_2
    //   131: invokevirtual 625	java/io/InputStream:close	()V
    //   134: aload_3
    //   135: athrow
    //   136: astore_1
    //   137: aload_1
    //   138: invokevirtual 628	java/io/IOException:printStackTrace	()V
    //   141: goto -7 -> 134
    //   144: astore 4
    //   146: aload_3
    //   147: astore_2
    //   148: aload 4
    //   150: astore_3
    //   151: goto -29 -> 122
    //   154: astore 4
    //   156: goto -66 -> 90
    //   159: aload_2
    //   160: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	161	0	this	Activity_CameraPreview
    //   0	161	1	paramArrayOfByte	byte[]
    //   9	73	2	localObject1	Object
    //   83	5	2	localException1	Exception
    //   91	69	2	localObject2	Object
    //   4	105	3	localObject3	Object
    //   121	26	3	localObject4	Object
    //   150	1	3	localObject5	Object
    //   6	87	4	localObject6	Object
    //   144	5	4	localObject7	Object
    //   154	1	4	localException2	Exception
    //   1	101	5	localObject8	Object
    //   17	28	6	localOptions	BitmapFactory.Options
    // Exception table:
    //   from	to	target	type
    //   70	74	76	java/io/IOException
    //   10	19	83	java/lang/Exception
    //   21	27	83	java/lang/Exception
    //   29	38	83	java/lang/Exception
    //   108	112	114	java/io/IOException
    //   10	19	121	finally
    //   21	27	121	finally
    //   29	38	121	finally
    //   92	97	121	finally
    //   130	134	136	java/io/IOException
    //   38	62	144	finally
    //   38	62	154	java/lang/Exception
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
  
  public void getCropImage(final byte[] paramArrayOfByte)
  {
    this.progressDialog = GPUImageWrapper.createLoadingDialog(this.context, "sf");
    this.progressDialog.show();
    this.mThread = new Thread(new Runnable()
    {
      @SuppressLint({"SimpleDateFormat"})
      public void run()
      {
        if ((Activity_CameraPreview.this.mThread != null) && (!Activity_CameraPreview.this.mThread.isInterrupted())) {}
        for (;;)
        {
          try
          {
            i = ((ActivityManager)Activity_CameraPreview.this.context.getSystemService("activity")).getLargeMemoryClass();
            i = Activity_CameraPreview.maxperm * i / 8;
            int j = Activity_CameraPreview.max;
            if (i > j) {}
          }
          catch (Exception localException2)
          {
            int i;
            Object localObject1;
            float f;
            Object localObject3;
            Object localObject2;
            double d;
            localException2.printStackTrace();
            Activity_CameraPreview.this.finish();
            continue;
          }
          try
          {
            localObject1 = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
            f = ((Bitmap)localObject1).getWidth() * ((Bitmap)localObject1).getHeight();
            if (f < i)
            {
              localObject3 = localObject1;
              if (((Bitmap)localObject1).getWidth() > ((Bitmap)localObject1).getHeight())
              {
                localObject3 = new Matrix();
                ((Matrix)localObject3).postRotate(90.0F);
                localObject3 = Bitmap.createBitmap((Bitmap)localObject1, 0, 0, ((Bitmap)localObject1).getWidth(), ((Bitmap)localObject1).getHeight(), (Matrix)localObject3, true);
              }
              Activity_CameraPreview.this.mapp.clearphotodata();
              localObject1 = new Timestamp(System.currentTimeMillis());
              localObject1 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format((Date)localObject1);
              localObject1 = new File(Activity_CameraPreview.this.cacheLocation.getPath() + "/" + (String)localObject1 + ".temp");
              Object localObject4 = new BufferedOutputStream(new FileOutputStream((File)localObject1));
              Bitmap localBitmap = BitmapTools.resizeImage((Bitmap)localObject3);
              localBitmap = GPUImageWrapper.processRGBClosing(Activity_CameraPreview.this.context, 4, localBitmap);
              localBitmap = GPUImageWrapper.processSharpen(Activity_CameraPreview.this.context, 4.0F, localBitmap);
              GPUImageWrapper.processCannyEdgeDetection(Activity_CameraPreview.this.context, localBitmap).compress(Bitmap.CompressFormat.JPEG, 85, (OutputStream)localObject4);
              ((OutputStream)localObject4).flush();
              ((OutputStream)localObject4).close();
              localObject4 = ((File)localObject1).getPath();
              Activity_CameraPreview.this.mapp.setNewData(LibImgFun.ImgFunInt((String)localObject4));
              if (((File)localObject1).exists()) {
                ((File)localObject1).delete();
              }
              Activity_CameraPreview.this.mApp.setSavebitmap((Bitmap)localObject3);
              if ((Activity_CameraPreview.this.mThread != null) && (!Activity_CameraPreview.this.mThread.isInterrupted()))
              {
                localObject1 = new Message();
                ((Message)localObject1).what = 5;
                Activity_CameraPreview.this.handler.sendMessage((Message)localObject1);
              }
              return;
              i = Activity_CameraPreview.max;
              continue;
            }
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
            localObject2 = Activity_CameraPreview.this.decodeImg(paramArrayOfByte);
            continue;
            d = Math.sqrt(i / f);
            f = (float)d;
            try
            {
              localObject3 = new Matrix();
              ((Matrix)localObject3).postScale(f, f);
              localObject3 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight(), (Matrix)localObject3, true);
              localObject2 = localObject3;
            }
            catch (Exception localException3)
            {
              localException3.printStackTrace();
              Matrix localMatrix = new Matrix();
              localMatrix.postScale(2.0F, 2.0F);
              localObject2 = Bitmap.createBitmap((Bitmap)localObject2, 0, 0, ((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight(), localMatrix, true);
            }
          }
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
      return;
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
        paramIntent = Toast.makeText(this.context, getResources().getString(2131296438), 0);
        paramIntent.setGravity(17, 0, 0);
        paramIntent.show();
        return;
      }
      paramIntent = Toast.makeText(this.context, getResources().getString(2131296439), 0);
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
        break label523;
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
      label523:
      if (this.mapp.getAdvOrChargeOrNormal() == 2) {
        FlurryAgent.logEvent("7_UserDoc_IAP");
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903156);
    this.context = this;
    this.ac = this;
    instance = this;
    this.myApplication = MyApplication.getApplication(this.context);
    this.picturepath = new ArrayList();
    this.mApp = MyApp.getApp();
    paramBundle = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
    height = paramBundle.widthPixels;
    width = paramBundle.heightPixels;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("ExitApp");
    registerReceiver(this.broadcastReceiver, localIntentFilter);
    this.preferences = getSharedPreferences("TinyScanPro", 0);
    this.natualrotation = getWindowManager().getDefaultDisplay().getRotation();
    this.editor = this.preferences.edit();
    this.fl = ((RelativeLayout)findViewById(2131755593));
    this.mcamerawidth = paramBundle.widthPixels;
    this.mcameraheight = paramBundle.heightPixels;
    this.clicktime = new ArrayList();
    this.myApplication.setPhotowidth(this.mcameraheight);
    this.myApplication.setPhotoheight(this.mcamerawidth);
    this.light = ((ImageView)findViewById(2131755096));
    this.light.setOnClickListener(this.mlistener);
    this.isflashon = this.preferences.getBoolean("isflashon", false);
    if (this.isflashon) {
      if (this.myApplication.isPad()) {
        this.light.setImageResource(2130837776);
      }
    }
    for (;;)
    {
      this.numbtn = ((TextView)findViewById(2131755590));
      this.preview_single = ((ImageView)findViewById(2131755588));
      this.preview_batch = ((ImageView)findViewById(2131755587));
      this.preview_save = ((ImageView)findViewById(2131755591));
      this.preview_imagephoto = ((ImageView)findViewById(2131755589));
      this.preview_single.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Activity_CameraPreview.this.isBatch.booleanValue())
          {
            Activity_CameraPreview.this.preview_single.setImageResource(2130837618);
            Activity_CameraPreview.this.preview_batch.setImageResource(2130837606);
            Activity_CameraPreview.access$002(Activity_CameraPreview.this, Boolean.valueOf(false));
            paramAnonymousView = Toast.makeText(Activity_CameraPreview.this.context, Activity_CameraPreview.this.getResources().getString(2131296495), 1);
            paramAnonymousView.setGravity(17, 0, 0);
            paramAnonymousView.show();
          }
        }
      });
      this.preview_batch.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((Activity_CameraPreview.this.mapp.getAdvOrChargeOrNormal() == 3) || (Activity_CameraPreview.this.mapp.getAdvOrChargeOrNormal() == 1))
          {
            if (Activity_CameraPreview.this.isBatch.booleanValue()) {
              return;
            }
            Activity_CameraPreview.this.preview_single.setImageResource(2130837617);
            Activity_CameraPreview.this.preview_batch.setImageResource(2130837607);
            Activity_CameraPreview.access$002(Activity_CameraPreview.this, Boolean.valueOf(true));
            paramAnonymousView = Toast.makeText(Activity_CameraPreview.this.context, Activity_CameraPreview.this.getResources().getString(2131296365), 1);
            paramAnonymousView.setGravity(17, 0, 0);
            paramAnonymousView.show();
            return;
          }
          Activity_CameraPreview.this.showProIapBuyDialog();
        }
      });
      this.preview_save.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((Activity_CameraPreview.this.picturepath != null) && (Activity_CameraPreview.this.picturepath.size() > 0) && (Activity_CameraPreview.this.isBatch.booleanValue()))
          {
            if (Activity_CameraPreview.this.mCamera != null) {
              Activity_CameraPreview.this.mCamera.stopPreview();
            }
            Activity_CameraPreview.this.myApplication.setPicturepath(Activity_CameraPreview.this.picturepath);
            Activity_CameraPreview.this.startActivity(new Intent(Activity_CameraPreview.this.context, Activity_MoreProcess1.class));
            Activity_CameraPreview.this.finish();
          }
        }
      });
      if (!this.mapp.getIsShowBatch())
      {
        this.numbtn.setVisibility(8);
        this.preview_single.setVisibility(8);
        this.preview_batch.setVisibility(8);
        this.preview_save.setVisibility(8);
        this.preview_imagephoto.setVisibility(8);
      }
      this.takepicture = ((ImageView)findViewById(2131755586));
      this.takepicture.setOnClickListener(this.mlistener);
      if (this.myApplication.isPad()) {
        this.mtool = new tool(this.context, this.handler);
      }
      if ((Build.MANUFACTURER.equals("Amazon")) && ((Build.MODEL.equals("KFAPWI")) || (Build.MODEL.equals("KFAPWA")) || (Build.MODEL.equals("KFTHWA")) || (Build.MODEL.equals("KFTHWI")) || (Build.MODEL.equals("KFSOWI")) || (Build.MODEL.equals("KFJWA")) || (Build.MODEL.equals("KFJWI")) || (Build.MODEL.equals("KFTT")))) {
        this.myApplication.setAmazon(true);
      }
      if (this.mapp.getAdvOrChargeOrNormal() != 3)
      {
        this.iapBuy = new IAPBuy(this);
        this.iapBuy.buyGoogleAdvPro();
      }
      return;
      this.light.setImageResource(2130837776);
      continue;
      if (this.myApplication.isPad()) {
        this.light.setImageResource(2130837775);
      } else {
        this.light.setImageResource(2130837775);
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.broadcastReceiver);
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
            Activity_CameraPreview.this.finish();
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
    this.fl.removeAllViews();
    if (this.mCamera != null)
    {
      this.mCamera.release();
      this.mCamera = null;
    }
    if ((this.picturepath != null) && (this.picturepath.size() > 0) && (this.isBatch.booleanValue()))
    {
      this.preview_single.setImageResource(2130837617);
      this.preview_batch.setImageResource(2130837607);
      this.preview_imagephoto.setVisibility(0);
      this.preview_save.setVisibility(0);
      this.numbtn.setVisibility(0);
    }
    for (;;)
    {
      this.mCamera = getCameraInstance();
      Message localMessage = new Message();
      localMessage.what = 4;
      this.handler.sendMessage(localMessage);
      makefolder();
      return;
      this.preview_batch.setVisibility(0);
      this.preview_single.setVisibility(0);
      this.preview_imagephoto.setVisibility(8);
      this.preview_save.setVisibility(8);
      this.numbtn.setVisibility(8);
      if (!this.mapp.getIsShowBatch())
      {
        this.numbtn.setVisibility(8);
        this.preview_single.setVisibility(8);
        this.preview_batch.setVisibility(8);
        this.preview_save.setVisibility(8);
        this.preview_imagephoto.setVisibility(8);
      }
    }
  }
  
  public int setCameraDisplayOrientation(Activity paramActivity, int paramInt, Camera paramCamera)
  {
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    Camera.getCameraInfo(paramInt, localCameraInfo);
    paramInt = paramActivity.getWindowManager().getDefaultDisplay().getRotation();
    if (this.myApplication.getRate() != -1)
    {
      paramInt = this.myApplication.getRate();
      int i = 0;
      switch (paramInt)
      {
      default: 
        paramInt = i;
        label83:
        if (localCameraInfo.facing != 1) {
          break;
        }
      }
    }
    for (paramInt = (360 - (localCameraInfo.orientation + paramInt) % 360) % 360;; paramInt = (localCameraInfo.orientation - paramInt + 360) % 360)
    {
      paramCamera.setDisplayOrientation(paramInt);
      return paramInt;
      this.myApplication.setRate(paramInt);
      break;
      paramInt = 0;
      break label83;
      paramInt = 90;
      break label83;
      paramInt = 180;
      break label83;
      paramInt = 270;
      break label83;
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
          break label173;
        }
        if (localList.contains("on")) {
          ((Camera.Parameters)localObject).setFlashMode("on");
        }
      }
      this.mCamera.setParameters((Camera.Parameters)localObject);
      if ((this.clicktime == null) || (!this.clicktime.isEmpty()) || (this.hasFocus)) {
        break label195;
      }
      this.focusview.setPosition(this.focusview.getWidth() / 2, this.focusview.getHeight() / 2);
      this.focusview.setFocus(false, false);
      this.focusview.setVisibility(0);
      localObject = new Message();
      ((Message)localObject).what = 2;
      this.handler.sendMessageDelayed((Message)localObject, 5000L);
      this.mCamera.autoFocus(new Camera.AutoFocusCallback()
      {
        public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
        {
          Activity_CameraPreview.this.focusview.setFocus(true, paramAnonymousBoolean);
          try
          {
            paramAnonymousCamera.takePicture(null, null, Activity_CameraPreview.this.jpegCallback);
            return;
          }
          catch (Exception paramAnonymousCamera)
          {
            paramAnonymousCamera.printStackTrace();
          }
        }
      });
    }
    for (;;)
    {
      this.takepicture.setEnabled(true);
      return;
      label173:
      if (!localList.contains("off")) {
        break;
      }
      ((Camera.Parameters)localObject).setFlashMode("off");
      break;
      label195:
      this.mCamera.takePicture(null, null, this.jpegCallback);
    }
  }
  
  class CameraView
    extends SurfaceView
    implements SurfaceHolder.Callback
  {
    private SurfaceHolder holder = null;
    
    public CameraView(Context paramContext)
    {
      super();
      this.holder.setType(3);
      this.holder.addCallback(this);
    }
    
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      if (Activity_CameraPreview.this.focusview != null)
      {
        Activity_CameraPreview.this.focusview.setPosition(Activity_CameraPreview.this.focusview.getWidth() / 2, Activity_CameraPreview.this.focusview.getHeight() / 2);
        Activity_CameraPreview.this.focusview.setFocus(false, false);
      }
      try
      {
        if (Activity_CameraPreview.this.mCamera.getParameters().getSupportedFocusModes().contains("auto")) {
          Activity_CameraPreview.this.focusview.setVisibility(0);
        }
        switch (paramMotionEvent.getAction())
        {
        }
        for (;;)
        {
          return super.onTouchEvent(paramMotionEvent);
          Activity_CameraPreview.this.hasFocus = false;
          Activity_CameraPreview.this.clicktime.add(Integer.valueOf(0));
          Message localMessage = new Message();
          localMessage.what = 3;
          Activity_CameraPreview.this.handler.sendMessageDelayed(localMessage, 5000L);
        }
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    
    public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
    {
      try
      {
        Activity_CameraPreview.this.mCamera.startPreview();
        return;
      }
      catch (Exception paramSurfaceHolder)
      {
        paramSurfaceHolder.printStackTrace();
      }
    }
    
    public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
    {
      try
      {
        Activity_CameraPreview.this.initCamera();
        Activity_CameraPreview.this.mCamera.setPreviewDisplay(paramSurfaceHolder);
        return;
      }
      catch (Exception paramSurfaceHolder)
      {
        paramSurfaceHolder.printStackTrace();
      }
    }
    
    public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {}
  }
}
