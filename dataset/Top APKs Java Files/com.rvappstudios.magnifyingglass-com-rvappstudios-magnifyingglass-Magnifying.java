package com.rvappstudios.magnifyingglass;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.preference.PreferenceManager;
import android.provider.Settings.System;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.plus.PlusOneButton.OnPlusOneClickListener;
import com.rvappstudios.inapp.INAPP;
import com.rvappstudios.inapp.IabHelper.QueryInventoryFinishedListener;
import com.rvappstudios.inapp.IabResult;
import com.rvappstudios.inapp.Inventory;
import com.rvappstudios.template.AppRater;
import com.rvappstudios.template.Constants;
import com.rvappstudios.template.ExceptionHandler;
import com.rvappstudios.template.FacebookHelper;
import com.rvappstudios.template.FlurryHelper;
import com.rvappstudios.template.Mopub;
import com.rvappstudios.template.Mopub.MopubInterstitialListener;
import com.rvappstudios.template.PlusOne;
import com.rvappstudios.template.TwitterHelper;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class Magnifying
  extends Activity
  implements View.OnClickListener, Mopub.MopubInterstitialListener, CameraPreview.OnLongClickListener
{
  final int ANIMATIONDURATION = 1000;
  final int RC_REQUEST = 10001;
  Button btnAutoFocus = null;
  Button btnChangeCamera = null;
  Button btnContrast = null;
  Button btnMagnifyingOnOff = null;
  Button btnOpenGallery = null;
  Button btnSettings = null;
  Button btnToggleFlash = null;
  CameraPreview cameraPreview = null;
  Constants constants = Constants.getInstance();
  int currentCameraId = 0;
  Animation enterAnimation = null;
  List<Integer> excludedIds = new ArrayList();
  Animation exitAnimation = null;
  FreezePhotoHandler freezePhotoHandler = null;
  FullAdBanner fullAdBanner = null;
  int[] ids = { 2131099673, 2131099672, 2131099682, 2131099671, 2131099677, 2131099678, 2131099681, 2131099679, 2131099680 };
  LinearLayout linearAutoFocus = null;
  LinearLayout linearNoPreview = null;
  LinearLayout linearTxtPreviewLayout = null;
  IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener()
  {
    public void onQueryInventoryFinished(IabResult paramAnonymousIabResult, Inventory paramAnonymousInventory) {}
  };
  TwitterHelper mTwitterHelper = TwitterHelper.getInstance();
  Mopub mopub = Mopub.getInstance();
  boolean negative = true;
  boolean onPauseFromPlusOne = false;
  Camera.Parameters originalParameters = null;
  PhotoHandler phototHandler = null;
  PlusOne plusOne = null;
  PlusOne plusOne_Layout = null;
  ProgressBar progressBrightness = null;
  ScrollView scroll = null;
  SettingsDialog setting = null;
  boolean showAd = false;
  TranslateAnimation translate = null;
  TextView txtAutoFocus = null;
  
  public Magnifying() {}
  
  private void animatingMagnifyingOnOff(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Animation localAnimation = AnimationUtils.loadAnimation(this, 2130968578);
      this.linearNoPreview.startAnimation(localAnimation);
      if (this.constants.checkOsVersion(11))
      {
        this.translate = new TranslateAnimation(0.0F, 0.0F, this.linearTxtPreviewLayout.getY() - this.constants.screenHeight * 90 / 480, this.linearTxtPreviewLayout.getY() + this.constants.screenHeight * 5 / 480);
        this.translate.setDuration(500L);
        this.translate.setFillAfter(true);
        this.linearTxtPreviewLayout.startAnimation(this.translate);
        topAnimation(this.btnContrast);
        if (this.constants.isCameraAvailable) {
          topAnimation(this.btnChangeCamera);
        }
        bottomAnimation(this.linearAutoFocus);
        if (this.constants.isCameraAvailable) {
          bottomAnimation(this.constants.btnTakePicture);
        }
        this.linearNoPreview.setVisibility(0);
        this.btnContrast.setVisibility(4);
      }
    }
    for (;;)
    {
      if (!this.constants.isCameraAvailable)
      {
        this.btnChangeCamera.setVisibility(4);
        this.constants.btnTakePicture.setVisibility(4);
      }
      return;
      this.translate = new TranslateAnimation(0.0F, 0.0F, 0 - this.constants.screenHeight * 90 / 480, this.constants.screenHeight * 5 / 480 + 0);
      break;
      reverseTopAnimation(this.btnContrast);
      if (this.constants.isCameraAvailable) {
        reverseTopAnimation(this.btnChangeCamera);
      }
      reverseBottomAnimation(this.linearAutoFocus);
      if (this.constants.isCameraAvailable) {
        reverseBottomAnimation(this.constants.btnTakePicture);
      }
      this.linearNoPreview.setVisibility(8);
      this.btnContrast.setVisibility(0);
      enablingButtons();
    }
  }
  
  private void disablingButtons()
  {
    this.constants.disableAllButtons(this.ids, new ArrayList());
    if (this.txtAutoFocus != null) {
      this.txtAutoFocus.setVisibility(8);
    }
    if (this.linearAutoFocus != null) {
      this.linearAutoFocus.setVisibility(8);
    }
    if (this.btnContrast != null) {
      this.btnContrast.setVisibility(8);
    }
  }
  
  private void enablingButtons()
  {
    this.constants.enableAllButtons(this.ids);
    if (this.txtAutoFocus != null) {
      this.txtAutoFocus.setVisibility(0);
    }
    if (this.linearAutoFocus != null) {
      this.linearAutoFocus.setVisibility(0);
    }
    if (this.btnContrast != null) {
      this.btnContrast.setVisibility(0);
    }
  }
  
  private void findReferences()
  {
    this.cameraPreview = ((CameraPreview)findViewById(2131099663));
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131099674);
    TextView localTextView = (TextView)findViewById(2131099675);
    this.cameraPreview.setMoveZoomLayout(localLinearLayout, localTextView);
    this.btnChangeCamera = ((Button)findViewById(2131099673));
    if ((Camera.getNumberOfCameras() == 1) || (!this.constants.isCameraAvailable))
    {
      this.btnChangeCamera.setVisibility(4);
      this.btnToggleFlash = ((Button)findViewById(2131099682));
      this.btnToggleFlash.setOnClickListener(this);
      this.constants.btnTakePicture = ((Button)findViewById(2131099680));
      if (!this.constants.isCameraAvailable) {
        break label383;
      }
      this.constants.btnTakePicture.setOnClickListener(this);
    }
    for (;;)
    {
      this.linearAutoFocus = ((LinearLayout)findViewById(2131099677));
      this.txtAutoFocus = ((TextView)findViewById(2131099679));
      this.btnAutoFocus = ((Button)findViewById(2131099678));
      this.linearAutoFocus.setOnClickListener(this);
      this.btnSettings = ((Button)findViewById(2131099671));
      this.btnSettings.setOnClickListener(this);
      this.btnContrast = ((Button)findViewById(2131099672));
      this.btnContrast.setOnClickListener(this);
      this.progressBrightness = ((ProgressBar)findViewById(2131099664));
      this.constants.progress = this.progressBrightness;
      this.btnMagnifyingOnOff = ((Button)findViewById(2131099681));
      this.btnMagnifyingOnOff.setOnClickListener(this);
      this.linearNoPreview = ((LinearLayout)findViewById(2131099665));
      this.linearNoPreview.setGravity(1);
      this.constants.linearHelp_1 = ((LinearLayout)findViewById(2131099684));
      this.constants.linearHelp_2 = ((LinearLayout)findViewById(2131099689));
      this.constants.linearHelp_3 = ((LinearLayout)findViewById(2131099695));
      ((RelativeLayout)findViewById(2131099662)).setOnClickListener(this);
      this.cameraPreview.setOnLongClickListener(this);
      return;
      this.btnChangeCamera.setOnClickListener(this);
      break;
      label383:
      this.constants.btnTakePicture.setVisibility(4);
    }
  }
  
  private void pictureTaken()
  {
    if ((this.cameraPreview.CameraBack) && (this.constants.isStabilizerSupported) && (this.constants.isStabilizerOn))
    {
      this.cameraPreview.parameters.setSceneMode("auto");
      this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
    }
    if ((this.constants.isColorEffectSupported) && (this.btnContrast.isSelected()))
    {
      this.cameraPreview.parameters.setColorEffect("negative");
      this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
    }
    if ((this.constants.isFlashSupported) && (this.cameraPreview.CameraBack) && (this.constants.isAutoLightMode) && (!this.constants.isFlashModeTourch))
    {
      this.cameraPreview.parameters.setFlashMode("off");
      this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
    }
    enablingButtons();
  }
  
  private void setImages()
    throws IOException
  {
    Object localObject1 = (RelativeLayout)findViewById(2131099676);
    Object localObject2 = (RelativeLayout.LayoutParams)((RelativeLayout)localObject1).getLayoutParams();
    ((RelativeLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenHeight * 430 / 480, 0, 0);
    ((RelativeLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localObject1 = new WeakReference(this.constants.getImagesFromAssets("front-camera.png"));
    if (this.constants.checkOsVersion(16))
    {
      this.btnChangeCamera.setBackground(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject1).get()));
      localObject1 = (RelativeLayout.LayoutParams)this.btnChangeCamera.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 23 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 30 / 320);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(0, this.constants.screenHeight * 2 / 480, this.constants.screenWidth * 2 / 320, 0);
      this.btnChangeCamera.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (Bitmap)new WeakReference(this.constants.getImagesFromAssets("flash_off.png")).get();
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("flash_on.png"));
      if (Build.VERSION.SDK_INT <= 16) {
        break label3800;
      }
      this.btnToggleFlash.setBackground(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      label260:
      localObject1 = (RelativeLayout.LayoutParams)this.btnToggleFlash.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 40 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = ((RelativeLayout.LayoutParams)localObject1).height;
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 5 / 320, 0, 0, this.constants.screenHeight * 10 / 480);
      this.btnToggleFlash.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      if ((!this.constants.isCameraAvailable) || (!this.constants.isFlashSupported)) {
        this.btnToggleFlash.setSelected(true);
      }
      localObject1 = (Bitmap)new WeakReference(this.constants.getImagesFromAssets("take_picture_1.png")).get();
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("take_picture.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3825;
      }
      this.constants.btnTakePicture.setBackground(this.constants.effectSelector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      label445:
      localObject1 = (RelativeLayout.LayoutParams)this.constants.btnTakePicture.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 40 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = ((RelativeLayout.LayoutParams)localObject1).height;
      ((RelativeLayout.LayoutParams)localObject1).setMargins(0, 0, this.constants.screenWidth * 2 / 320, this.constants.screenHeight * 5 / 480);
      this.constants.btnTakePicture.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (Bitmap)new WeakReference(this.constants.getImagesFromAssets("bg_1.png")).get();
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("bg.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3853;
      }
      this.linearAutoFocus.setBackground(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      label604:
      localObject1 = (RelativeLayout.LayoutParams)this.linearAutoFocus.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 40 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 80 / 320);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 2 / 320, 0, 0, this.constants.screenHeight * 5 / 480);
      this.linearAutoFocus.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (Bitmap)new WeakReference(this.constants.getImagesFromAssets("Auto-icon-active.png")).get();
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("Auto-icon-deactive.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3878;
      }
      this.btnAutoFocus.setBackground(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      label767:
      localObject1 = (LinearLayout.LayoutParams)this.btnAutoFocus.getLayoutParams();
      ((LinearLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 24 / 480);
      ((LinearLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 23 / 320);
      ((LinearLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 6 / 320, 0, 0, 0);
      this.btnAutoFocus.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (LinearLayout.LayoutParams)this.txtAutoFocus.getLayoutParams();
      ((LinearLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 6 / 320, 0, 0, 0);
      this.txtAutoFocus.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.txtAutoFocus.setTextSize(this.constants.scaleX * 15.0F / 320.0F);
      localObject1 = new WeakReference(this.constants.getImagesFromAssets("settings.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3903;
      }
      this.btnSettings.setBackground(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject1).get()));
      label960:
      localObject1 = (RelativeLayout.LayoutParams)this.btnSettings.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 30 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = ((RelativeLayout.LayoutParams)localObject1).height;
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 5 / 320, this.constants.screenHeight * 2 / 480, 0, 0);
      this.btnSettings.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (Bitmap)new WeakReference(this.constants.getImagesFromAssets("brightness-contrast.png")).get();
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("brightness-contrast-deactivated.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3931;
      }
      this.btnContrast.setBackground(this.constants.Selector((Bitmap)((WeakReference)localObject2).get(), (Bitmap)localObject1));
      label1113:
      localObject1 = (RelativeLayout.LayoutParams)this.btnContrast.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 30 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = ((RelativeLayout.LayoutParams)localObject1).height;
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 5 / 320, this.constants.screenHeight * 2 / 480, 0, 0);
      this.btnContrast.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.progressBrightness.setProgressDrawable(getResources().getDrawable(2130837578));
      localObject1 = (RelativeLayout.LayoutParams)this.progressBrightness.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 220 / 320);
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 20 / 480);
      this.progressBrightness.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      Settings.System.putInt(getContentResolver(), "screen_brightness_mode", 0);
      localObject1 = (Bitmap)new WeakReference(this.constants.getImagesFromAssets("magnifying-glass-active.png")).get();
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("magnifying-glass-deactive.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3956;
      }
      this.btnMagnifyingOnOff.setBackground(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      label1351:
      localObject1 = (RelativeLayout.LayoutParams)this.btnMagnifyingOnOff.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 40 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenHeight * 40 / 480);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(0, 0, this.constants.screenWidth * 15 / 320, 0);
      this.btnMagnifyingOnOff.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.btnMagnifyingOnOff.setSelected(this.constants.isMagnifyingOn);
      this.linearTxtPreviewLayout = ((LinearLayout)findViewById(2131099666));
      localObject1 = (TextView)findViewById(2131099667);
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 11.0F / 320.0F);
      ((TextView)localObject1).setTextColor(-1);
      ((TextView)localObject1).setText("Magnifier Turned Off, Click ");
      localObject2 = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(this.constants.screenWidth * 28 / 320, this.constants.screenHeight * 6 / 480, 0, 0);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (ImageView)findViewById(2131099668);
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("magnifying-glass-active.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label3981;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject2).get()));
      label1607:
      localObject2 = (LinearLayout.LayoutParams)((ImageView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).height = (this.constants.screenHeight * 20 / 480);
      ((LinearLayout.LayoutParams)localObject2).width = (this.constants.screenHeight * 20 / 480);
      ((LinearLayout.LayoutParams)localObject2).setMargins(this.constants.screenWidth * 5 / 320, 0, 0, 0);
      ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      ((ImageView)localObject1).setSelected(this.constants.isMagnifyingOn);
      localObject1 = (TextView)findViewById(2131099669);
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 11.0F / 320.0F);
      ((TextView)localObject1).setTextColor(-1);
      ((TextView)localObject1).setText("  To Turn Back On");
      localObject2 = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenHeight * 6 / 480, 0, 0);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (RelativeLayout.LayoutParams)this.constants.linearHelp_1.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 234 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 233 / 320);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 5 / 320, 0, 0, 0);
      this.constants.linearHelp_1.getBackground().setAlpha(178);
      this.constants.linearHelp_1.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (ImageView)findViewById(2131099685);
      if (!this.constants.checkOsVersion(16)) {
        break label4006;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("up_arrow.png")));
      label1910:
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 61 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenWidth * 15 / 320);
      localObject1 = (ImageView)findViewById(2131099686);
      if (!this.constants.checkOsVersion(16)) {
        break label4034;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), rotateImage(this.constants.getImagesFromAssets("hand.png"), 315.0F)));
      label2007:
      localObject2 = (LinearLayout.LayoutParams)((ImageView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).height = (this.constants.screenHeight * 52 / 480);
      ((LinearLayout.LayoutParams)localObject2).width = (this.constants.screenWidth * 39 / 320);
      ((LinearLayout.LayoutParams)localObject2).setMargins(this.constants.screenWidth * 10 / 320, 0, 0, 0);
      ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (ImageView)findViewById(2131099687);
      if (!this.constants.checkOsVersion(16)) {
        break label4069;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("bottom_arrow.png")));
      label2125:
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 61 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenWidth * 15 / 320);
      localObject1 = (TextView)findViewById(2131099688);
      if (!this.constants.isCameraAvailable) {
        break label4097;
      }
      ((TextView)localObject1).setText("Drag Up & Down To Adjust\nMagnifying Zoom Level");
      label2195:
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 16.0F / 320.0F);
      localObject2 = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenWidth * 10 / 480, 0, 0);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (RelativeLayout.LayoutParams)this.constants.linearHelp_2.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 200 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 233 / 320);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 5 / 320, 0, 0, 0);
      this.constants.linearHelp_2.getBackground().setAlpha(178);
      this.constants.linearHelp_2.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (LinearLayout)findViewById(2131099690);
      localObject2 = (LinearLayout.LayoutParams)((LinearLayout)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenHeight * 50 / 480, 0, 0);
      ((LinearLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (ImageView)findViewById(2131099691);
      if (!this.constants.checkOsVersion(16)) {
        break label4107;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("right_arrow.png")));
      label2440:
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 15 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenWidth * 61 / 320);
      localObject1 = (ImageView)findViewById(2131099692);
      if (!this.constants.checkOsVersion(16)) {
        break label4135;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("hand.png")));
      label2530:
      localObject2 = (LinearLayout.LayoutParams)((ImageView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).height = (this.constants.screenHeight * 52 / 480);
      ((LinearLayout.LayoutParams)localObject2).width = (this.constants.screenWidth * 39 / 320);
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenHeight * 5 / 480, 0, 0);
      ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (ImageView)findViewById(2131099693);
      if (!this.constants.checkOsVersion(16)) {
        break label4163;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("left_arrow.png")));
      label2647:
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 15 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenWidth * 61 / 320);
      localObject1 = (TextView)findViewById(2131099694);
      if (!this.constants.isCameraAvailable) {
        break label4191;
      }
      ((TextView)localObject1).setText("Drag Right & Left To\nAdjust Screen Brightness Level");
      label2717:
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 16.0F / 320.0F);
      localObject2 = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenWidth * 20 / 480, 0, 0);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (RelativeLayout.LayoutParams)this.constants.linearHelp_3.getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject1).height = (this.constants.screenHeight * 200 / 480);
      ((RelativeLayout.LayoutParams)localObject1).width = (this.constants.screenWidth * 233 / 320);
      ((RelativeLayout.LayoutParams)localObject1).setMargins(this.constants.screenWidth * 5 / 320, 0, 0, 0);
      this.constants.linearHelp_3.getBackground().setAlpha(178);
      this.constants.linearHelp_3.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = (LinearLayout)findViewById(2131099696);
      localObject2 = (LinearLayout.LayoutParams)((LinearLayout)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenHeight * 30 / 480, 0, 0);
      ((LinearLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (ImageView)findViewById(2131099697);
      if (!this.constants.checkOsVersion(16)) {
        break label4201;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("freeze_rays.png")));
      label2962:
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 47 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenWidth * 72 / 320);
      localObject1 = (ImageView)findViewById(2131099698);
      if (!this.constants.checkOsVersion(16)) {
        break label4229;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("hand.png")));
      label3052:
      localObject2 = (LinearLayout.LayoutParams)((ImageView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).height = (this.constants.screenHeight * 52 / 480);
      ((LinearLayout.LayoutParams)localObject2).width = (this.constants.screenWidth * 39 / 320);
      ((LinearLayout.LayoutParams)localObject2).setMargins(this.constants.screenWidth * 20 / 320, 0, 0, 0);
      ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (TextView)findViewById(2131099699);
      ((TextView)localObject1).setText("Tap And Hold To Capture Image");
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 16.0F / 320.0F);
      localObject2 = (LinearLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
      ((LinearLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenWidth * 20 / 480, 0, 0);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      ((RelativeLayout)findViewById(2131099702)).getBackground().setAlpha(204);
      localObject1 = (ImageView)findViewById(2131099703);
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 142 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenWidth * 288 / 320);
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("plus_one/bg.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label4257;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject2).get()));
      label3319:
      localObject1 = (ImageView)findViewById(2131099705);
      ((ImageView)localObject1).getLayoutParams().height = (this.constants.screenHeight * 29 / 480);
      ((ImageView)localObject1).getLayoutParams().width = (this.constants.screenHeight * 29 / 480);
      localObject2 = new WeakReference(this.constants.getImagesFromAssets("plus_one/arrow.png"));
      if (!this.constants.checkOsVersion(16)) {
        break label4282;
      }
      ((ImageView)localObject1).setBackground(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject2).get()));
    }
    for (;;)
    {
      localObject2 = (RelativeLayout.LayoutParams)((ImageView)localObject1).getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject2).setMargins(0, this.constants.screenHeight * 3 / 480, this.constants.screenWidth * 25 / 320, 0);
      ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject2 = new TranslateAnimation(0, 0.0F, 0, 20.0F, 2, 0.0F, 2, 0.0F);
      ((TranslateAnimation)localObject2).setDuration(500L);
      ((TranslateAnimation)localObject2).setRepeatCount(-1);
      ((TranslateAnimation)localObject2).setRepeatMode(2);
      ((TranslateAnimation)localObject2).setInterpolator(new LinearInterpolator());
      ((ImageView)localObject1).startAnimation((Animation)localObject2);
      localObject1 = (TextView)findViewById(2131099706);
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 16.0F / 320.0F);
      ((TextView)localObject1).setTextColor(Color.rgb(25, 25, 25));
      localObject2 = (RelativeLayout.LayoutParams)((TextView)localObject1).getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject2).setMargins(this.constants.screenWidth * 40 / 320, 0, 0, this.constants.screenHeight * 20 / 480);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject1 = (LinearLayout)findViewById(2131099707);
      ((LinearLayout)localObject1).getLayoutParams().height = (this.constants.screenHeight * 30 / 480);
      ((LinearLayout)localObject1).getLayoutParams().width = (this.constants.screenWidth * 282 / 320);
      localObject2 = (RelativeLayout.LayoutParams)((LinearLayout)localObject1).getLayoutParams();
      ((RelativeLayout.LayoutParams)localObject2).setMargins(this.constants.screenWidth * 24 / 320, this.constants.screenHeight * 30 / 480, 0, 0);
      ((LinearLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      ((LinearLayout)localObject1).setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 1)
          {
            if (Magnifying.this.constants.allowTouch())
            {
              if (Magnifying.this.exitAnimation == null)
              {
                Magnifying.this.exitAnimation = AnimationUtils.loadAnimation(Magnifying.this.constants.currentActivity, 2130968577);
                Magnifying.this.exitAnimation.setDuration(1000L);
              }
              Magnifying.this.constants.relativePlusOne.startAnimation(Magnifying.this.exitAnimation);
              Magnifying.this.constants.relativePlusOne.setVisibility(4);
              Magnifying.this.constants.popShown = false;
            }
            return true;
          }
          return false;
        }
      });
      localObject1 = (TextView)findViewById(2131099708);
      ((TextView)localObject1).setTextSize(this.constants.scaleX * 20.0F / 320.0F);
      ((TextView)localObject1).setTextColor(Color.rgb(0, 122, 255));
      settingPlusOneLayoutButton();
      return;
      this.btnChangeCamera.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject1).get()));
      break;
      label3800:
      this.btnToggleFlash.setBackgroundDrawable(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      break label260;
      label3825:
      this.constants.btnTakePicture.setBackgroundDrawable(this.constants.effectSelector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      break label445;
      label3853:
      this.linearAutoFocus.setBackgroundDrawable(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      break label604;
      label3878:
      this.btnAutoFocus.setBackgroundDrawable(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      break label767;
      label3903:
      this.btnSettings.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject1).get()));
      break label960;
      label3931:
      this.btnContrast.setBackgroundDrawable(this.constants.Selector((Bitmap)((WeakReference)localObject2).get(), (Bitmap)localObject1));
      break label1113;
      label3956:
      this.btnMagnifyingOnOff.setBackgroundDrawable(this.constants.Selector((Bitmap)localObject1, (Bitmap)((WeakReference)localObject2).get()));
      break label1351;
      label3981:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject2).get()));
      break label1607;
      label4006:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("up_arrow.png")));
      break label1910;
      label4034:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), rotateImage(this.constants.getImagesFromAssets("hand.png"), 315.0F)));
      break label2007;
      label4069:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("bottom_arrow.png")));
      break label2125;
      label4097:
      ((TextView)localObject1).setText("Drag Up & Down To Adjust Screen Color");
      break label2195;
      label4107:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("right_arrow.png")));
      break label2440;
      label4135:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("hand.png")));
      break label2530;
      label4163:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("left_arrow.png")));
      break label2647;
      label4191:
      ((TextView)localObject1).setText("Drag Left & Right To Adjust Brightness");
      break label2717;
      label4201:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("freeze_rays.png")));
      break label2962;
      label4229:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), this.constants.getImagesFromAssets("hand.png")));
      break label3052;
      label4257:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject2).get()));
      break label3319;
      label4282:
      ((ImageView)localObject1).setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap)((WeakReference)localObject2).get()));
    }
  }
  
  private void showingPlusOnePopUp()
  {
    this.plusOne = new PlusOne(getApplicationContext());
    this.plusOne.onCreate((PlusOneButton)findViewById(2131099704));
    final long l = this.constants.preference.getLong("plus_one", 0L) + 1L;
    this.constants.editor.putLong("plus_one", l);
    if ((l > 0L) && (l % 10L == 0L)) {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (Magnifying.this.constants.popShown)
          {
            Magnifying.this.constants.editor.putLong("plus_one", l - 1L);
            return;
          }
          Magnifying.this.constants.popShown = true;
          if (Magnifying.this.enterAnimation == null)
          {
            Magnifying.this.enterAnimation = AnimationUtils.loadAnimation(Magnifying.this.constants.currentActivity, 2130968576);
            Magnifying.this.enterAnimation.setDuration(1000L);
          }
          Magnifying.this.constants.relativePlusOne.startAnimation(Magnifying.this.enterAnimation);
          Magnifying.this.constants.relativePlusOne.setVisibility(0);
        }
      }, 24000L);
    }
  }
  
  public void OnLongClick()
  {
    if ((this.constants.allowTouch()) && (!this.constants.isMagnifyingOn) && (this.constants.isPhotoFreezOn) && (!this.constants.isFreezeModeRunning) && (this.constants.isCameraAvailable))
    {
      if (this.constants.getExternalStorageAvailableSpace() > 0L)
      {
        if ((this.cameraPreview.CameraBack) && (this.constants.isStabilizerSupported) && (this.constants.isStabilizerOn)) {
          this.cameraPreview.parameters.setSceneMode("auto");
        }
        if ((this.constants.isFlashSupported) && (this.cameraPreview.CameraBack) && (this.constants.isAutoLightMode) && (!this.constants.isFlashModeTourch)) {
          this.cameraPreview.parameters.setFlashMode("auto");
        }
        this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
        if (this.freezePhotoHandler == null) {
          this.freezePhotoHandler = new FreezePhotoHandler(this.constants.context);
        }
        disablingButtons();
      }
    }
    else {
      try
      {
        this.cameraPreview.mCamera.takePicture(null, null, this.freezePhotoHandler);
        Button localButton = (Button)findViewById(2131099700);
        if (localButton != null) {
          localButton.setVisibility(0);
        }
        localButton = (Button)findViewById(2131099701);
        if (localButton != null) {
          localButton.setVisibility(0);
        }
        this.constants.isFreezeModeRunning = true;
        this.freezePhotoHandler.setOnFreezeOver(new FreezePhotoHandler.OnFreezeOver()
        {
          public void FreezeOver()
          {
            Magnifying.this.freezeOver();
          }
        });
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;)
        {
          localRuntimeException.printStackTrace();
        }
      }
    }
    this.constants.showDialog("Alert", "Not Enough Space Available On Your Device", true);
  }
  
  public void bottomAnimation(View paramView)
  {
    if (this.constants.checkOsVersion(11)) {}
    for (TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, paramView.getY() + this.constants.screenHeight * 50 / 480);; localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, this.constants.screenHeight * 50 / 480 + 0))
    {
      localTranslateAnimation.setDuration(500L);
      localTranslateAnimation.setFillAfter(true);
      paramView.startAnimation(localTranslateAnimation);
      return;
    }
  }
  
  public void freezeOver()
  {
    Object localObject = (Button)findViewById(2131099700);
    if (localObject != null) {
      ((Button)localObject).setVisibility(8);
    }
    localObject = (Button)findViewById(2131099701);
    if (localObject != null) {
      ((Button)localObject).setVisibility(8);
    }
    enablingButtons();
    if (this.showAd)
    {
      this.showAd = false;
      this.mopub.showInterstitialAd();
    }
    this.cameraPreview.reverseTopAnimation();
    this.cameraPreview.reverseBottomAnimation();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if ((Magnifying.this.cameraPreview != null) && (Magnifying.this.cameraPreview.parameters != null) && (Magnifying.this.cameraPreview.mCamera != null))
        {
          if ((!Magnifying.this.constants.isFlashSupported) || (!Magnifying.this.cameraPreview.CameraBack) || (!Magnifying.this.constants.isAutoLightMode) || (Magnifying.this.constants.isFlashModeTourch)) {
            break label292;
          }
          Magnifying.this.cameraPreview.parameters.setFlashMode("off");
          Magnifying.this.cameraPreview.mCamera.setParameters(Magnifying.this.cameraPreview.parameters);
        }
        for (;;)
        {
          if ((Magnifying.this.cameraPreview.CameraBack) && (Magnifying.this.constants.isStabilizerSupported) && (Magnifying.this.constants.isStabilizerOn)) {
            Magnifying.this.cameraPreview.parameters.setSceneMode("auto");
          }
          if ((Magnifying.this.constants.isColorEffectSupported) && (Magnifying.this.btnContrast.isSelected()))
          {
            Magnifying.this.cameraPreview.parameters.setColorEffect("negative");
            Magnifying.this.cameraPreview.mCamera.setParameters(Magnifying.this.cameraPreview.parameters);
          }
          try
          {
            if ((Magnifying.this.cameraPreview != null) && (Magnifying.this.cameraPreview.mCamera != null)) {
              Magnifying.this.cameraPreview.mCamera.startPreview();
            }
            Magnifying.this.constants.isFreezeModeRunning = false;
            return;
            label292:
            if ((!Magnifying.this.constants.isFlashSupported) || (!Magnifying.this.cameraPreview.CameraBack) || (!Magnifying.this.constants.isAutoLightMode) || (!Magnifying.this.constants.isFlashModeTourch)) {
              continue;
            }
            Magnifying.this.cameraPreview.parameters.setFlashMode("torch");
            Magnifying.this.cameraPreview.mCamera.setParameters(Magnifying.this.cameraPreview.parameters);
          }
          catch (RuntimeException localRuntimeException)
          {
            for (;;)
            {
              localRuntimeException.printStackTrace();
            }
          }
        }
      }
    }, 500L);
    if ((this.constants.linearPlusOneLayout != null) && (this.constants.isShowingPlusOneButton))
    {
      localObject = AnimationUtils.loadAnimation(this.constants.context, 2130968578);
      this.constants.linearPlusOneLayout.startAnimation((Animation)localObject);
      ((Animation)localObject).setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          Magnifying.this.constants.linearPlusOneLayout.setVisibility(0);
        }
        
        public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
        
        public void onAnimationStart(Animation paramAnonymousAnimation) {}
      });
    }
  }
  
  public boolean isAppInstalled(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    return true;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    FacebookHelper.getInstance().onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 10001) {
      INAPP.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onBackPressed()
  {
    if ((this.fullAdBanner == null) || (!this.fullAdBanner.isShowing()))
    {
      if (this.constants.relativePlusOne.getVisibility() == 0)
      {
        if (this.exitAnimation == null)
        {
          this.exitAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968577);
          this.exitAnimation.setDuration(1000L);
        }
        this.constants.relativePlusOne.startAnimation(this.exitAnimation);
        this.constants.relativePlusOne.setVisibility(4);
        this.constants.popShown = false;
      }
    }
    else {
      return;
    }
    Object localObject;
    if (this.constants.helpShown == 1)
    {
      localObject = this.constants;
      ((Constants)localObject).helpShown += 1;
      this.constants.linearHelp_1.setVisibility(8);
      this.constants.linearHelp_2.setVisibility(0);
      return;
    }
    if (this.constants.helpShown == 2)
    {
      localObject = this.constants;
      ((Constants)localObject).helpShown += 1;
      this.constants.linearHelp_2.setVisibility(8);
      if (this.constants.isCameraAvailable)
      {
        localObject = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968578);
        this.constants.linearHelp_3.startAnimation((Animation)localObject);
        this.constants.linearHelp_3.setVisibility(0);
        return;
      }
      this.constants.helpShown = 4;
      return;
    }
    if (this.constants.helpShown == 3)
    {
      localObject = this.constants;
      ((Constants)localObject).helpShown += 1;
      this.constants.linearHelp_3.setVisibility(8);
      return;
    }
    if (this.constants.isFreezeModeRunning)
    {
      freezeOver();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Button localButton = (Button)Magnifying.this.findViewById(2131099700);
          if (localButton != null) {
            localButton.setVisibility(8);
          }
          localButton = (Button)Magnifying.this.findViewById(2131099701);
          if (localButton != null) {
            localButton.setVisibility(8);
          }
        }
      }, 500L);
      return;
    }
    super.onBackPressed();
    Process.killProcess(Process.myPid());
  }
  
  public void onClick(View paramView)
  {
    boolean bool = false;
    Animation localAnimation;
    if (this.constants.helpShown == 1)
    {
      this.constants.helpShown = 2;
      this.constants.linearHelp_1.setVisibility(8);
      localAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968578);
      this.constants.linearHelp_2.startAnimation(localAnimation);
      this.constants.linearHelp_2.setVisibility(0);
      if (this.constants.relativePlusOne.getVisibility() != 0) {
        switch (paramView.getId())
        {
        }
      }
    }
    label450:
    label1239:
    label1254:
    label1537:
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
                      for (;;)
                      {
                        return;
                        if (this.constants.helpShown == 2)
                        {
                          this.constants.helpShown = 3;
                          localAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968579);
                          this.constants.linearHelp_2.startAnimation(localAnimation);
                          this.constants.linearHelp_2.setVisibility(8);
                          if (this.constants.isCameraAvailable)
                          {
                            localAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968578);
                            this.constants.linearHelp_3.startAnimation(localAnimation);
                            this.constants.linearHelp_3.setVisibility(0);
                            break;
                          }
                          this.constants.helpShown = 4;
                          break;
                        }
                        if (this.constants.helpShown != 3) {
                          break;
                        }
                        this.constants.helpShown = 4;
                        localAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968579);
                        this.constants.linearHelp_3.startAnimation(localAnimation);
                        this.constants.linearHelp_3.setVisibility(8);
                        break;
                        if ((!this.constants.isMagnifyingOn) && (this.constants.allowTouch()))
                        {
                          if (!this.constants.isCameraAvailable) {
                            break label450;
                          }
                          this.btnToggleFlash.setSelected(true);
                          this.cameraPreview.changeCamera();
                          if (this.cameraPreview.CameraBack) {
                            this.constants.flurryHelper.sendLog("Display Screen", "3_Camera_Mode", "Back_Camera");
                          }
                          while (this.btnContrast.isSelected())
                          {
                            this.btnContrast.setSelected(false);
                            return;
                            this.constants.flurryHelper.sendLog("Display Screen", "3_Camera_Mode", "Front_Camera");
                          }
                        }
                      }
                      this.constants.showCameraUnavailabeDialog();
                      return;
                      if (this.constants.context == null) {
                        this.constants.context = this;
                      }
                    } while (!this.constants.allowTouch());
                    if (this.constants.isCameraAvailable)
                    {
                      if (this.cameraPreview.CameraBack)
                      {
                        if (this.constants.hasFlash(this.cameraPreview.mCamera))
                        {
                          if (this.cameraPreview.toggleFlash())
                          {
                            this.btnToggleFlash.setSelected(true);
                            this.constants.flurryHelper.sendLog("Display Screen", "4_Flash", "OFF");
                            return;
                          }
                          this.btnToggleFlash.setSelected(false);
                          this.constants.flurryHelper.sendLog("Display Screen", "4_Flash", "ON");
                          return;
                        }
                        this.constants.showDialog("Alert", "No LED/ Flash Detected.", true);
                        return;
                      }
                      this.constants.showDialog("Alert", "This Feature Can Be Used Only With Back Camera.", true);
                      return;
                    }
                    this.constants.showCameraUnavailabeDialog();
                    return;
                  } while ((this.constants.isMagnifyingOn) || (!this.constants.allowTouch()));
                  if (this.constants.isCameraAvailable)
                  {
                    if (this.linearAutoFocus.isSelected())
                    {
                      this.linearAutoFocus.setSelected(false);
                      this.btnAutoFocus.setSelected(false);
                      this.cameraPreview.setAutoFocus(false);
                      this.constants.flurryHelper.sendLog("Display Screen", "5_Auto_Focus", "OFF");
                      return;
                    }
                    this.linearAutoFocus.setSelected(true);
                    this.btnAutoFocus.setSelected(true);
                    this.cameraPreview.setAutoFocus(true);
                    this.constants.flurryHelper.sendLog("Display Screen", "5_Auto_Focus", "ON");
                    return;
                  }
                  this.constants.showCameraUnavailabeDialog();
                  return;
                } while ((this.constants.isMagnifyingOn) || (!this.constants.allowTouch()) || (this.constants.isFreezeModeRunning));
                if (this.constants.isCameraAvailable)
                {
                  if (this.constants.getExternalStorageAvailableSpace() > 0L)
                  {
                    if ((this.cameraPreview.CameraBack) && (this.constants.isStabilizerSupported) && (this.constants.isStabilizerOn))
                    {
                      this.cameraPreview.parameters.setSceneMode("auto");
                      this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
                    }
                    if ((this.constants.isFlashSupported) && (this.cameraPreview.CameraBack) && (this.constants.isAutoLightMode) && (!this.constants.isFlashModeTourch))
                    {
                      this.cameraPreview.parameters.setFlashMode("auto");
                      this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
                    }
                    if (this.phototHandler == null) {
                      this.phototHandler = new PhotoHandler(this.constants.context);
                    }
                    this.constants.flurryHelper.sendLog("Display Screen", "2_Click_Camera", "Count");
                    disablingButtons();
                    shootSound();
                    try
                    {
                      if ((this.cameraPreview != null) && (this.cameraPreview.mCamera != null)) {
                        this.cameraPreview.mCamera.takePicture(null, null, this.phototHandler);
                      }
                      for (;;)
                      {
                        this.phototHandler.setOnPictureTakenListener(new PhotoHandler.PictureTaken()
                        {
                          public void onPictureTaken()
                          {
                            Magnifying.this.pictureTaken();
                          }
                        });
                        return;
                        pictureTaken();
                      }
                    }
                    catch (RuntimeException paramView)
                    {
                      for (;;)
                      {
                        paramView.printStackTrace();
                      }
                    }
                  }
                  this.constants.showDialog("Alert", "Not Enough Space Available On Your Device", true);
                  return;
                }
                this.constants.showCameraUnavailabeDialog();
                return;
              } while ((this.constants.isMagnifyingOn) || (!this.constants.allowTouch()));
              if (!this.constants.isCameraAvailable) {
                break label1254;
              }
              if (!this.constants.isColorEffectSupported) {
                break label1239;
              }
            } while (this.cameraPreview.parameters == null);
            this.cameraPreview.mCamera.stopPreview();
            if (this.cameraPreview.setColorEffec())
            {
              this.btnContrast.setSelected(true);
              this.constants.flurryHelper.sendLog("Display Screen", "6_Contrast", "ON");
            }
            for (;;)
            {
              this.cameraPreview.mCamera.setParameters(this.cameraPreview.parameters);
              this.cameraPreview.mCamera.startPreview();
              return;
              this.btnContrast.setSelected(false);
              this.constants.flurryHelper.sendLog("Display Screen", "6_Contrast", "OFF");
            }
            this.constants.showDialog("Alert", "Due To Hardware Limitations Of Device Certain Features Will Not Be Available.", true);
            return;
            this.constants.showCameraUnavailabeDialog();
            return;
          } while (!this.constants.allowTouch());
          if (this.setting == null)
          {
            this.setting = new SettingsDialog(this.constants.context, 2131361796, this.cameraPreview);
            this.setting.setCancelable(false);
          }
        } while (this.setting.isShowing());
        this.setting.show();
        this.constants.flurryHelper.sendLog("Display Screen", "10_Click_On_Settings", "Count");
        return;
      } while (!this.constants.allowTouch());
      this.constants.btnTakePicture.setEnabled(false);
      if (this.constants.isMagnifyingOn)
      {
        this.constants.isMagnifyingOn = false;
        animatingMagnifyingOnOff(this.constants.isMagnifyingOn);
        this.constants.flurryHelper.sendLog("Display Screen", "9_Magnify", "OFF");
        this.constants.editor.putBoolean("Maginfying", this.constants.isMagnifyingOn);
        this.constants.editor.commit();
        paramView = this.constants.btnTakePicture;
        if (!this.constants.isMagnifyingOn) {
          break label1537;
        }
      }
      for (;;)
      {
        paramView.setEnabled(bool);
        this.btnMagnifyingOnOff.setSelected(this.constants.isMagnifyingOn);
        settingPlusOneLayoutButton();
        return;
        this.constants.isMagnifyingOn = true;
        animatingMagnifyingOnOff(this.constants.isMagnifyingOn);
        this.constants.flurryHelper.sendLog("Display Screen", "9_Magnify", "ON");
        break;
        bool = true;
      }
    } while (!this.constants.allowTouch());
    if (this.exitAnimation == null)
    {
      this.exitAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968577);
      this.exitAnimation.setDuration(1000L);
    }
    this.constants.relativePlusOne.startAnimation(this.exitAnimation);
    this.constants.relativePlusOne.setVisibility(4);
    this.constants.popShown = false;
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.constants.context = this;
    setContentView(2130903040);
    this.constants.currentActivity = this;
    if (this.constants.preference == null)
    {
      this.constants.preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
      this.constants.editor = this.constants.preference.edit();
    }
    this.excludedIds.add(Integer.valueOf(2131099681));
    this.excludedIds.add(Integer.valueOf(2131099682));
    this.excludedIds.add(Integer.valueOf(2131099671));
    if (!this.constants.DEBUG_BUILD) {
      Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    }
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (Magnifying.this.constants.previousActivity != null) {
          Magnifying.this.constants.previousActivity.finish();
        }
        ((LinearLayout)Magnifying.this.findViewById(2131099683)).setVisibility(8);
        if (!Magnifying.this.constants.preference.getBoolean("HelpShown", false))
        {
          Magnifying.this.constants.editor.putBoolean("HelpShown", true);
          Magnifying.this.constants.editor.commit();
          Magnifying.this.constants.helpShown = 1;
          ((LinearLayout)Magnifying.this.findViewById(2131099684)).setVisibility(0);
        }
        for (;;)
        {
          if (Magnifying.this.constants.checkInternetConnection()) {
            new AppRater().app_launched(Magnifying.this);
          }
          if (Magnifying.this.constants.isMagnifyingOn)
          {
            if (Magnifying.this.linearNoPreview == null) {
              Magnifying.this.linearNoPreview = ((LinearLayout)Magnifying.this.findViewById(2131099665));
            }
            Magnifying.this.linearNoPreview.setVisibility(0);
            Magnifying.this.animatingMagnifyingOnOff(Magnifying.this.constants.isMagnifyingOn);
            if (Magnifying.this.constants.btnTakePicture == null) {
              Magnifying.this.constants.btnTakePicture = ((Button)Magnifying.this.findViewById(2131099680));
            }
            Magnifying.this.constants.btnTakePicture.setEnabled(false);
          }
          Magnifying.this.constants.relativePlusOne = ((RelativeLayout)Magnifying.this.findViewById(2131099702));
          Magnifying.this.fullAdBanner = new FullAdBanner(Magnifying.this, 2131361796);
          if ((!Magnifying.this.constants.removeAds) && (Magnifying.this.constants.checkInternetConnection()))
          {
            int i = Magnifying.this.constants.preference.getInt("mopub_frequency", 1);
            Magnifying.this.constants.editor.putInt("mopub_frequency", i + 1);
            Magnifying.this.constants.editor.commit();
            if ((i == 3) || (i == 7) || ((i > 7) && (i % 2 != 0))) {
              Magnifying.this.mopub.createInterstitialAd();
            }
          }
          return;
          Magnifying.this.constants.helpShown = 4;
        }
      }
    }, 500L);
    INAPP.init(this, true, this.mGotInventoryListener);
    if (this.constants.flurryHelper == null) {
      this.constants.flurryHelper = new FlurryHelper(getApplicationContext());
    }
    this.constants.flurryHelper.start();
    this.constants.flurryHelper.sendLog("Display Screen", "1_Open_App", "Count");
    showingPlusOnePopUp();
    this.plusOne_Layout = new PlusOne(getApplicationContext());
    this.plusOne_Layout.onCreate((PlusOneButton)findViewById(2131099710));
    this.constants.linearPlusOneLayout = ((LinearLayout)findViewById(2131099709));
    if (this.constants.checkInternetConnection())
    {
      long l = this.constants.preference.getLong("plus_one_layout", 0L) + 1L;
      this.constants.editor.putLong("plus_one_layout", l);
      if ((l > 0L) && (l % 4L == 0L))
      {
        this.constants.isShowingPlusOneButton = true;
        this.constants.linearPlusOneLayout.setVisibility(0);
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.constants.isSplashShown = false;
    if (this.constants.flurryHelper != null) {
      this.constants.flurryHelper.stop();
    }
  }
  
  public void onInterstitialClicked() {}
  
  public void onInterstitialDismissed() {}
  
  public void onInterstitialFailed(String paramString)
  {
    if (this.fullAdBanner == null) {
      this.fullAdBanner = new FullAdBanner(this, 2131361796);
    }
    if (isAppInstalled("com.rvappstudios.flashlight"))
    {
      this.mopub.loadInterstitialAd();
      return;
    }
    this.fullAdBanner.show();
  }
  
  public void onInterstitialLoaded()
  {
    if (this.constants.isFreezeModeRunning) {
      this.showAd = true;
    }
    while ((this.constants.popShown) || (this.constants.removeAds)) {
      return;
    }
    this.mopub.showInterstitialAd();
  }
  
  public void onInterstitialShown() {}
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    this.mTwitterHelper.onNewIntent(paramIntent);
  }
  
  protected void onPause()
  {
    super.onPause();
    if (this.cameraPreview != null) {
      this.cameraPreview.destroyCamera();
    }
    this.mopub.setOnInterstitalAdListener(null);
    Object localObject = (RelativeLayout)findViewById(2131099662);
    if (localObject != null) {
      ((RelativeLayout)localObject).setKeepScreenOn(false);
    }
    localObject = (Button)findViewById(2131099700);
    if ((localObject != null) && (((Button)localObject).getVisibility() == 0)) {
      ((Button)localObject).setVisibility(4);
    }
    localObject = (Button)findViewById(2131099701);
    if ((localObject != null) && (((Button)localObject).getVisibility() == 0)) {
      ((Button)localObject).setVisibility(4);
    }
    if ((this.setting != null) && (this.setting.isShowing())) {
      this.constants.btnTakePicture.setEnabled(false);
    }
    for (;;)
    {
      if (!this.constants.isCameraAvailable) {
        this.btnChangeCamera.setVisibility(4);
      }
      if (this.constants.isFreezeModeRunning) {
        freezeOver();
      }
      if (!this.constants.isCameraAvailable)
      {
        this.btnChangeCamera.setVisibility(4);
        this.constants.btnTakePicture.setVisibility(4);
      }
      if (this.constants.isMagnifyingOn) {
        this.btnContrast.setVisibility(4);
      }
      return;
      enablingButtons();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if ((this.cameraPreview != null) && (this.cameraPreview.mCamera == null)) {}
    try
    {
      this.cameraPreview.resumeCamera();
      this.mopub.setOnInterstitalAdListener(this);
      RelativeLayout localRelativeLayout = (RelativeLayout)findViewById(2131099662);
      if (localRelativeLayout != null) {
        localRelativeLayout.setKeepScreenOn(true);
      }
      this.plusOne.onResume();
      this.plusOne_Layout.onResume();
      if (this.onPauseFromPlusOne)
      {
        this.onPauseFromPlusOne = false;
        if (this.exitAnimation == null)
        {
          this.exitAnimation = AnimationUtils.loadAnimation(this.constants.currentActivity, 2130968577);
          this.exitAnimation.setDuration(1000L);
        }
        this.constants.relativePlusOne.startAnimation(this.exitAnimation);
        this.constants.relativePlusOne.setVisibility(4);
        this.constants.popShown = false;
      }
      if (this.constants.isMagnifyingOn)
      {
        if (this.btnContrast == null) {
          this.btnContrast = ((Button)findViewById(2131099672));
        }
        this.btnContrast.setVisibility(4);
        if (this.constants.btnTakePicture == null) {
          this.constants.btnTakePicture = ((Button)findViewById(2131099680));
        }
        this.constants.btnTakePicture.setEnabled(false);
      }
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    this.constants.setScreenSize(this);
    if (this.constants.checkInternetConnection()) {
      this.mTwitterHelper.onCreate();
    }
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        Magnifying.this.findReferences();
        try
        {
          Magnifying.this.setImages();
          return;
        }
        catch (IOException localIOException)
        {
          localIOException.printStackTrace();
        }
      }
    }, 150L);
    this.plusOne.onStart();
    this.plusOne_Layout.onStart();
    this.plusOne.mPlusOneButton.setOnPlusOneClickListener(new PlusOneButton.OnPlusOneClickListener()
    {
      public void onPlusOneClick(Intent paramAnonymousIntent)
      {
        Magnifying.this.onPauseFromPlusOne = true;
        if (paramAnonymousIntent != null) {
          Magnifying.this.startActivityForResult(paramAnonymousIntent, 111);
        }
      }
    });
  }
  
  protected void onStop()
  {
    super.onStop();
    this.plusOne.onStop();
    this.plusOne_Layout.onStop();
  }
  
  public void reverseBottomAnimation(View paramView)
  {
    if (this.constants.checkOsVersion(11)) {}
    for (TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, paramView.getY() + this.constants.screenHeight * 30 / 480, 0.0F);; localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, this.constants.screenHeight * 30 / 480 + 0, 0.0F))
    {
      localTranslateAnimation.setDuration(500L);
      localTranslateAnimation.setFillAfter(true);
      paramView.startAnimation(localTranslateAnimation);
      return;
    }
  }
  
  public void reverseTopAnimation(View paramView)
  {
    if (this.constants.checkOsVersion(11)) {}
    for (TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, paramView.getY() - this.constants.screenHeight * 30 / 480, 0.0F);; localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0 - this.constants.screenHeight * 30 / 480, 0.0F))
    {
      localTranslateAnimation.setDuration(500L);
      localTranslateAnimation.setFillAfter(true);
      paramView.startAnimation(localTranslateAnimation);
      return;
    }
  }
  
  public Bitmap rotateImage(Bitmap paramBitmap, float paramFloat)
  {
    Matrix localMatrix = new Matrix();
    localMatrix.postRotate(paramFloat);
    return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
  }
  
  public void settingPlusOneLayoutButton()
  {
    if (this.constants.linearPlusOneLayout == null) {
      this.constants.linearPlusOneLayout = ((LinearLayout)findViewById(2131099709));
    }
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)this.constants.linearPlusOneLayout.getLayoutParams();
    if (this.constants.isMagnifyingOn) {
      localLayoutParams.setMargins(0, this.constants.screenHeight * 40 / 480, this.constants.screenWidth * 290 / 320, 0);
    }
    for (;;)
    {
      this.constants.linearPlusOneLayout.setLayoutParams(localLayoutParams);
      return;
      if (this.constants.isCameraAvailable) {
        localLayoutParams.setMargins(0, this.constants.screenHeight * 5 / 480, this.constants.screenWidth * 40 / 320, 0);
      } else {
        localLayoutParams.setMargins(0, this.constants.screenHeight * 5 / 480, this.constants.screenWidth * 5 / 320, 0);
      }
    }
  }
  
  public void shootSound()
  {
    int i = ((AudioManager)this.constants.context.getSystemService("audio")).getStreamVolume(5);
    MediaPlayer localMediaPlayer = null;
    if (i != 0)
    {
      if (0 == 0) {
        localMediaPlayer = MediaPlayer.create(this.constants.context, Uri.parse("file:///system/media/audio/ui/camera_click.ogg"));
      }
      if (localMediaPlayer != null) {
        localMediaPlayer.start();
      }
    }
  }
  
  public void topAnimation(View paramView)
  {
    if (this.constants.checkOsVersion(11)) {}
    for (TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, paramView.getY() - this.constants.screenHeight * 40 / 480);; localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, 0 - this.constants.screenHeight * 40 / 480))
    {
      localTranslateAnimation.setDuration(500L);
      localTranslateAnimation.setFillAfter(true);
      paramView.startAnimation(localTranslateAnimation);
      return;
    }
  }
}
