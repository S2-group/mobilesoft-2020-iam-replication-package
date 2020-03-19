package com.andromania.audiovideomixer.Activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.andromania.Network.AdFlags;
import com.andromania.Network.AdRequest;
import com.andromania.audiovideomixer.InAppActivity;
import com.andromania.audiovideomixer.MyVideoInputGallery.BucketActivity;
import com.andromania.audiovideomixer.outout.OutputActivity;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdSettings;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.NativeAdsManager.Listener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity
  extends AppCompatActivity
  implements NativeAdsManager.Listener
{
  public static String[] Activity_name = { "Output_Activity", "ViewVideo_Activity", "home_Activity", "video_Activity", "bucket_Activity", "Audio_Activity", "segment_Activity", "PreviewAddSelectedAudio_Activity", "searchvideo_Activity", "PreviewOriginalPlusSelectedAudio_Activity" };
  private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 2;
  private static final int RESULT_ACTIVITY = 1111;
  private static final int RESULT_LOAD_VIDEO = 1;
  public static NativeAdsManager nativeAdsManager;
  private View adView;
  String camVideoFile;
  File dir;
  String fullscreenadId;
  private LinearLayout nativeAdContainer;
  private String videoPath;
  private Uri videoUri;
  
  public HomeActivity() {}
  
  private void OpenRateDialogOnCounter()
  {
    if ((AdRequest.toBoolean(AdRequest.getPreferencesCustom(this, AdFlags.RatingAleart_Flag_ON_OFF, "Rating_dialog_show"))) && (AdRequest.stringToint(AdRequest.getPreferencesCustom(this, AdFlags.Rating_InappCounter, "firstactivity")) >= AdRequest.stringToint(AdRequest.getPreferencesCustom(this, AdFlags.RatingAleart_ParseCounter, "Rating_dialog_show"))))
    {
      AdRequest.setPreferencesCustom(this, AdFlags.Rating_InappCounter, "0", "firstactivity");
      new CustomDialog().show(getSupportFragmentManager(), "Tag");
    }
  }
  
  private void firstScreenFacebookNative(NativeAd paramNativeAd)
  {
    paramNativeAd.unregisterView();
    this.nativeAdContainer = ((LinearLayout)findViewById(2131230875));
    this.adView = ((LinearLayout)LayoutInflater.from(this).inflate(2131361855, this.nativeAdContainer, false));
    if (this.nativeAdContainer.getChildCount() > 0) {
      this.nativeAdContainer.removeAllViews();
    }
    this.nativeAdContainer.addView(this.adView);
    ((LinearLayout)findViewById(2131230755)).addView(new AdChoicesView(this, paramNativeAd, true), 0);
    AdIconView localAdIconView = (AdIconView)this.adView.findViewById(2131230876);
    TextView localTextView1 = (TextView)this.adView.findViewById(2131230879);
    MediaView localMediaView = (MediaView)this.adView.findViewById(2131230877);
    Object localObject = (TextView)this.adView.findViewById(2131230874);
    TextView localTextView2 = (TextView)this.adView.findViewById(2131230872);
    TextView localTextView3 = (TextView)this.adView.findViewById(2131230878);
    Button localButton = (Button)this.adView.findViewById(2131230873);
    localTextView1.setText(paramNativeAd.getAdvertiserName());
    localTextView2.setText(paramNativeAd.getAdBodyText());
    ((TextView)localObject).setText(paramNativeAd.getAdSocialContext());
    if (paramNativeAd.hasCallToAction()) {}
    for (int i = 0;; i = 4)
    {
      localButton.setVisibility(i);
      localButton.setText(paramNativeAd.getAdCallToAction());
      localTextView3.setText(paramNativeAd.getSponsoredTranslation());
      localObject = new ArrayList();
      ((List)localObject).add(localTextView1);
      ((List)localObject).add(localButton);
      paramNativeAd.registerViewForInteraction(this.adView, localMediaView, localAdIconView, (List)localObject);
      return;
    }
  }
  
  public void alertDialogCamera()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Info");
    localBuilder.setMessage("Pl.Record another video. Video should be more than 1 second.");
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audioVideoMixer_CamCapture";
        HomeActivity.this.dir = new File((String)localObject);
        if (!HomeActivity.this.dir.exists()) {
          HomeActivity.this.dir.mkdirs();
        }
        HomeActivity.this.camVideoFile = (HomeActivity.this.dir.getAbsolutePath() + "/audioVideoMixer_Cam" + System.currentTimeMillis() + ".mp4");
        localObject = new File(HomeActivity.this.camVideoFile);
        try
        {
          Intent localIntent1 = new Intent("android.media.action.VIDEO_CAPTURE");
          HomeActivity.access$002(HomeActivity.this, Uri.fromFile((File)localObject));
          localIntent1.setPackage(HomeActivity.this.getCamPackage());
          localIntent1.putExtra("output", HomeActivity.this.videoUri);
          HomeActivity.this.startActivityForResult(localIntent1, 2);
          paramAnonymousDialogInterface.cancel();
          return;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            try
            {
              Intent localIntent2 = new Intent("android.media.action.VIDEO_CAPTURE");
              HomeActivity.access$002(HomeActivity.this, Uri.fromFile((File)localObject));
              localIntent2.putExtra("output", HomeActivity.this.videoUri);
              HomeActivity.this.startActivityForResult(localIntent2, 2);
            }
            catch (Exception localException1)
            {
              Toast.makeText(HomeActivity.this, "There appears to be some issue with Camera. Pl. Check", 1).show();
            }
          }
        }
      }
    });
    localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }
  
  public String getCamPackage()
  {
    PackageManager localPackageManager = getPackageManager();
    String str2 = "";
    List localList = localPackageManager.getInstalledApplications(8192);
    int i = 0;
    for (;;)
    {
      String str1 = str2;
      if (i < localList.size())
      {
        if (((((ApplicationInfo)localList.get(i)).flags & 0x1) == 1) && (((ApplicationInfo)localList.get(i)).loadLabel(localPackageManager).toString().equalsIgnoreCase("Camera"))) {
          str1 = ((ApplicationInfo)localList.get(i)).packageName;
        }
      }
      else {
        return str1;
      }
      i += 1;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 2)
    {
      if (paramInt2 != -1) {
        break label120;
      }
      if (this.videoUri == null) {
        Toast.makeText(this, "Something went wrong, please try again!", 1).show();
      }
    }
    else
    {
      return;
    }
    this.videoPath = this.videoUri.getPath().trim();
    label120:
    try
    {
      paramIntent = new MediaMetadataRetriever();
      paramIntent.setDataSource(this.videoPath);
      if (Long.parseLong(paramIntent.extractMetadata(9)) > 1500L)
      {
        paramIntent = new Intent(this, PlayerActivity.class);
        paramIntent.putExtra("path", this.videoPath);
        startActivityForResult(paramIntent, 1111);
        return;
      }
      alertDialogCamera();
      return;
    }
    catch (Exception paramIntent) {}
    if (paramInt2 == 0)
    {
      Toast.makeText(this, "Video recording cancelled.", 1).show();
      return;
    }
    Toast.makeText(this, "Failed to record video. Pl. try again.", 1).show();
    return;
  }
  
  public void onAdError(AdError paramAdError)
  {
    Log.e("facebook", "facebook adsError");
  }
  
  public void onAdsLoaded()
  {
    Log.e("facebook", "facebook ads loaded");
    NativeAd localNativeAd = nativeAdsManager.nextNativeAd();
    if ((!AdRequest.toBoolean(AdRequest.getPreferencesCustom(this, AdFlags.purchaseFlag, "InApp"))) && (AdRequest.isOnline(this)) && (AdRequest.toBoolean(AdRequest.getPreferencesCustom(this, AdFlags.Facebook_Flag, "Facebook_Flag"))) && (localNativeAd != null) && (localNativeAd.getAdvertiserName() != null) && (localNativeAd.getAdCallToAction() != null)) {
      firstScreenFacebookNative(localNativeAd);
    }
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361822);
    this.fullscreenadId = getString(2131558400);
    AdRequest.showFirstscreenAdd(this, 112, "main_Activity", this.fullscreenadId);
    if ((!AdRequest.toBoolean(AdRequest.getPreferencesCustom(this, AdFlags.purchaseFlag, "InApp"))) && (AdRequest.isOnline(this)) && (AdRequest.toBoolean(AdRequest.getPreferencesCustom(this, AdFlags.Facebook_Flag, "Facebook_Flag"))))
    {
      nativeAdsManager = new NativeAdsManager(this, getString(2131558473), 1);
      nativeAdsManager.setListener(this);
      AdSettings.addTestDevice("56f70401-dbb5-448e-841c-8b0826a9d108");
      nativeAdsManager.setListener(this);
      if (nativeAdsManager != null) {
        nativeAdsManager.loadAds();
      }
    }
  }
  
  public void onMoreApps(View paramView)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:AndroTechMania")));
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/search?q=pub:AndroTechMania")));
    }
  }
  
  public void onMyStudio(View paramView)
  {
    paramView = new Intent(this, OutputActivity.class);
    paramView.putExtra("bucketName", "AddAudioToVideo");
    startActivity(paramView);
  }
  
  public void onPurchage(View paramView)
  {
    startActivity(new Intent(this, InAppActivity.class));
  }
  
  public void onRateUs(View paramView)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.andromania.audiovideomixer")));
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.andromania.audiovideomixer")));
    }
  }
  
  public void onSelectCamera(View paramView)
  {
    this.dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audioVideoMixer_CamCapture");
    if (!this.dir.exists()) {
      this.dir.mkdirs();
    }
    this.camVideoFile = (this.dir.getAbsolutePath() + "/audioVideoMixer_Cam" + System.currentTimeMillis() + ".mp4");
    paramView = new File(this.camVideoFile);
    try
    {
      Intent localIntent1 = new Intent("android.media.action.VIDEO_CAPTURE");
      this.videoUri = Uri.fromFile(paramView);
      localIntent1.setPackage(getCamPackage());
      localIntent1.putExtra("output", this.videoUri);
      startActivityForResult(localIntent1, 2);
      return;
    }
    catch (Exception localException)
    {
      try
      {
        Intent localIntent2 = new Intent("android.media.action.VIDEO_CAPTURE");
        this.videoUri = Uri.fromFile(paramView);
        localIntent2.putExtra("output", this.videoUri);
        startActivityForResult(localIntent2, 2);
        return;
      }
      catch (Exception paramView)
      {
        Toast.makeText(this, "There appears to be some issue with Camera. Pl. Check", 1).show();
      }
    }
  }
  
  public void onSelectVideo(View paramView)
  {
    startActivity(new Intent(this, BucketActivity.class));
  }
  
  public void onShare(View paramView)
  {
    paramView = new Intent("android.intent.action.SEND");
    paramView.setType("text/plain");
    paramView.putExtra("android.intent.extra.TEXT", getResources().getString(2131558497) + "https://play.google.com/store/apps/details?id=com.andromania.audiovideomixer");
    startActivity(Intent.createChooser(paramView, getResources().getString(2131558496)));
  }
  
  protected void onStart()
  {
    super.onStart();
    OpenRateDialogOnCounter();
    AdRequest.setQueryFire(this, Activity_name);
  }
  
  public static class CustomDialog
    extends AppCompatDialogFragment
  {
    View.OnClickListener CancelAction = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HomeActivity.CustomDialog.this.dismiss();
      }
    };
    View.OnClickListener doneAction = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          HomeActivity.CustomDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.andromania.audiovideomixer")));
          HomeActivity.CustomDialog.this.dismiss();
          return;
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          for (;;)
          {
            HomeActivity.CustomDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.andromania.audiovideomixer")));
          }
        }
      }
    };
    
    public CustomDialog() {}
    
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
    {
      paramLayoutInflater = paramLayoutInflater.inflate(2131361851, paramViewGroup, false);
      getDialog().setTitle("Sample");
      paramViewGroup = (Button)paramLayoutInflater.findViewById(2131230723);
      paramBundle = (Button)paramLayoutInflater.findViewById(2131230722);
      paramViewGroup.setOnClickListener(this.doneAction);
      paramBundle.setOnClickListener(this.CancelAction);
      return paramLayoutInflater;
    }
  }
}
