package com.andromania.MyVideoInputGallery;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Video.Media;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.andromania.MyAds.AdCode;
import com.andromania.outputGallery.MyGetterSetter;
import com.andromania.outputGallery.config.config;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterBuilder;
import com.karumi.dexter.DexterBuilder.Permission;
import com.karumi.dexter.DexterBuilder.SinglePermissionListener;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BucketActivity
  extends AppCompatActivity
{
  public final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 2;
  public long TotalTime = 0L;
  public List<VideoModel> allVideosList = new ArrayList();
  private final String[] bucketProjection = { "bucket_display_name", "_data" };
  public String camVideoFile;
  public File dir;
  FolderViewAdapter folderViewAdapter;
  ImageView imageViewSearch;
  ArrayList<BucketEntryNew> mBucketList = new ArrayList();
  RecyclerView recyclerView;
  MyGetterSetter setting;
  Toolbar toolbar;
  public String videoPath;
  public Uri videoUri;
  public ImageView videocaptureBtn;
  
  public BucketActivity() {}
  
  private void captureVideo()
  {
    this.dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getResources().getString(2131296329));
    if (!this.dir.exists()) {
      this.dir.mkdirs();
    }
    this.camVideoFile = (this.dir.getAbsolutePath() + "/" + getResources().getString(2131296330) + System.currentTimeMillis() + ".mp4");
    File localFile = new File(this.camVideoFile);
    try
    {
      Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
      if (Build.VERSION.SDK_INT > 23) {}
      for (this.videoUri = FileProvider.getUriForFile(this, "com.andromania.mutevideo.provider", localFile);; this.videoUri = Uri.fromFile(localFile))
      {
        localIntent.setPackage(getCamPackage());
        localIntent.putExtra("output", this.videoUri);
        startActivityForResult(localIntent, 2);
        return;
      }
      return;
    }
    catch (Exception localException) {}
  }
  
  private void initView()
  {
    this.imageViewSearch = ((ImageView)findViewById(2131689638));
    this.videocaptureBtn = ((ImageView)findViewById(2131689637));
    this.recyclerView = ((RecyclerView)findViewById(2131689639));
    this.folderViewAdapter = new FolderViewAdapter(this, this.mBucketList);
    Object localObject = new GridLayoutManager(this, 1);
    this.recyclerView.setLayoutManager((RecyclerView.LayoutManager)localObject);
    this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    this.recyclerView.setAdapter(this.folderViewAdapter);
    this.recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, this.recyclerView, new ClickListener()
    {
      public void onClick(View paramAnonymousView, int paramAnonymousInt)
      {
        if (!((BucketEntryNew)BucketActivity.this.mBucketList.get(paramAnonymousInt)).getFlag())
        {
          paramAnonymousView = new Intent(BucketActivity.this, VideoActivity.class);
          paramAnonymousView.putExtra("bucketName", ((BucketEntryNew)BucketActivity.this.mBucketList.get(paramAnonymousInt)).getalbum());
          BucketActivity.this.startActivity(paramAnonymousView);
        }
      }
      
      public void onLongClick(View paramAnonymousView, int paramAnonymousInt) {}
    }));
    this.imageViewSearch.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BucketActivity.this.startActivity(new Intent(BucketActivity.this, SearchVideoActivity.class));
      }
    });
    this.videocaptureBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Dexter.withActivity(BucketActivity.this).withPermission("android.permission.CAMERA").withListener(new PermissionListener()
        {
          public void onPermissionDenied(PermissionDeniedResponse paramAnonymous2PermissionDeniedResponse)
          {
            new android.support.v7.app.AlertDialog.Builder(BucketActivity.this).setTitle(2131296350).setMessage(2131296349).setNegativeButton(17039360, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                paramAnonymous3DialogInterface.dismiss();
              }
            }).setPositiveButton(17039370, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                paramAnonymous3DialogInterface.dismiss();
                paramAnonymous3DialogInterface = new Intent();
                paramAnonymous3DialogInterface.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                paramAnonymous3DialogInterface.setData(Uri.fromParts("package", BucketActivity.this.getPackageName(), null));
                BucketActivity.this.startActivity(paramAnonymous3DialogInterface);
              }
            }).setOnDismissListener(new DialogInterface.OnDismissListener()
            {
              public void onDismiss(DialogInterface paramAnonymous3DialogInterface) {}
            }).setCancelable(false).show();
          }
          
          public void onPermissionGranted(PermissionGrantedResponse paramAnonymous2PermissionGrantedResponse)
          {
            BucketActivity.this.captureVideo();
          }
          
          public void onPermissionRationaleShouldBeShown(PermissionRequest paramAnonymous2PermissionRequest, final PermissionToken paramAnonymous2PermissionToken)
          {
            new android.support.v7.app.AlertDialog.Builder(BucketActivity.this).setTitle(2131296350).setMessage(2131296349).setNegativeButton(17039360, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                paramAnonymous3DialogInterface.dismiss();
                paramAnonymous2PermissionToken.cancelPermissionRequest();
              }
            }).setPositiveButton(17039370, new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                paramAnonymous3DialogInterface.dismiss();
                paramAnonymous2PermissionToken.continuePermissionRequest();
              }
            }).setOnDismissListener(new DialogInterface.OnDismissListener()
            {
              public void onDismiss(DialogInterface paramAnonymous3DialogInterface)
              {
                paramAnonymous2PermissionToken.cancelPermissionRequest();
              }
            }).setCancelable(false).show();
          }
        }).check();
      }
    });
    localObject = new ArrayList();
    if (config.showfacebookAdsInputGallery(this.mBucketList.size(), this.setting, (List)localObject, this)) {
      this.folderViewAdapter.addNativeAdGrid((List)localObject);
    }
  }
  
  private void permissionSet()
  {
    if ((ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0) || (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE"))) {
      return;
    }
    ActivityCompat.requestPermissions(this, new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, 1);
  }
  
  private void selectSingleOrMulti()
  {
    appCode.selectOnevideobyCamera(this.videoPath, this);
  }
  
  private void setSupportedToolBar()
  {
    this.toolbar = ((Toolbar)findViewById(2131689630));
    setSupportActionBar(this.toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(2131296317);
    this.toolbar.setNavigationOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BucketActivity.this.finish();
      }
    });
  }
  
  public void alertDialogCamera()
  {
    Object localObject = new android.app.AlertDialog.Builder(this);
    ((android.app.AlertDialog.Builder)localObject).setTitle("Info");
    ((android.app.AlertDialog.Builder)localObject).setMessage("Pl.Record another video. Video should be more than 1 second.");
    ((android.app.AlertDialog.Builder)localObject).setCancelable(false);
    ((android.app.AlertDialog.Builder)localObject).setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Videditor_CamCapture";
        BucketActivity.this.dir = new File((String)localObject);
        if (!BucketActivity.this.dir.exists()) {
          BucketActivity.this.dir.mkdirs();
        }
        BucketActivity.this.camVideoFile = (BucketActivity.this.dir.getAbsolutePath() + "/Vid_Cam" + System.currentTimeMillis() + ".mp4");
        localObject = new File(BucketActivity.this.camVideoFile);
        Intent localIntent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (Build.VERSION.SDK_INT > 23) {}
        for (BucketActivity.this.videoUri = FileProvider.getUriForFile(BucketActivity.this, "com.andromania.mutevideo.provider", (File)localObject);; BucketActivity.this.videoUri = Uri.fromFile((File)localObject))
        {
          localIntent.putExtra("output", BucketActivity.this.videoUri);
          BucketActivity.this.startActivityForResult(localIntent, 2);
          paramAnonymousDialogInterface.cancel();
          return;
        }
      }
    });
    ((android.app.AlertDialog.Builder)localObject).setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localObject = ((android.app.AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setIcon(2130837646);
    ((AlertDialog)localObject).show();
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
  
  public void getVideoBuckets()
  {
    Cursor localCursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, this.bucketProjection, null, null, "date_added");
    new ArrayList(localCursor.getCount());
    new ArrayList(localCursor.getCount());
    HashSet localHashSet = new HashSet();
    if (localCursor.moveToLast()) {
      do
      {
        if (Thread.interrupted()) {
          return;
        }
        Object localObject = localCursor.getString(localCursor.getColumnIndex(this.bucketProjection[0]));
        String str = localCursor.getString(localCursor.getColumnIndex(this.bucketProjection[1]));
        if ((str != null) && (new File(str).exists()) && (!localHashSet.contains(localObject)))
        {
          localHashSet.add(localObject);
          localObject = new BucketEntryNew((String)localObject, str, false);
          this.mBucketList.add(localObject);
        }
      } while (localCursor.moveToPrevious());
    }
    localCursor.close();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    long l2;
    long l1;
    if (paramInt1 == 2)
    {
      if (paramInt2 == -1)
      {
        paramIntent = this.videoUri.getPath();
        this.videoPath = paramIntent.trim();
        l2 = 0L;
        Log.e("GalleryActivity", "u=" + paramIntent);
        Log.e("GalleryActivity", "videoPath=" + this.videoPath);
        l1 = l2;
      }
    }
    else
    {
      try
      {
        paramIntent = new MediaMetadataRetriever();
        l1 = l2;
        paramIntent.setDataSource(this.videoPath);
        l1 = l2;
        l2 = Long.parseLong(paramIntent.extractMetadata(9));
        l1 = l2;
        Log.e("GalleryActivity", "timeInmillisec=" + l2);
        l1 = l2;
        this.TotalTime += l2;
        l1 = l2;
      }
      catch (Exception paramIntent)
      {
        for (;;)
        {
          Log.e("GalleryActivity", "exception e1=" + paramIntent);
        }
        alertDialogCamera();
        return;
      }
      if (l1 >= 1000L)
      {
        selectSingleOrMulti();
        return;
      }
    }
    if (paramInt2 == 0)
    {
      Toast.makeText(this, getResources().getString(2131296331), 0).show();
      return;
    }
    Toast.makeText(this, getResources().getString(2131296332), 0).show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.setting = MyGetterSetter.getSettings(this);
    setContentView(2130968604);
    permissionSet();
    setSupportedToolBar();
    getVideoBuckets();
    initView();
    AdCode.ShowingAd(this, 140, true, "BucketVideo_Activity");
  }
  
  public static abstract interface ClickListener
  {
    public abstract void onClick(View paramView, int paramInt);
    
    public abstract void onLongClick(View paramView, int paramInt);
  }
  
  public static class RecyclerTouchListener
    implements RecyclerView.OnItemTouchListener
  {
    private BucketActivity.ClickListener clickListener;
    private GestureDetector gestureDetector;
    
    public RecyclerTouchListener(Context paramContext, final RecyclerView paramRecyclerView, final BucketActivity.ClickListener paramClickListener)
    {
      this.clickListener = paramClickListener;
      this.gestureDetector = new GestureDetector(paramContext, new GestureDetector.SimpleOnGestureListener()
      {
        public void onLongPress(MotionEvent paramAnonymousMotionEvent)
        {
          paramAnonymousMotionEvent = paramRecyclerView.findChildViewUnder(paramAnonymousMotionEvent.getX(), paramAnonymousMotionEvent.getY());
          if ((paramAnonymousMotionEvent != null) && (paramClickListener != null)) {
            paramClickListener.onLongClick(paramAnonymousMotionEvent, paramRecyclerView.getChildPosition(paramAnonymousMotionEvent));
          }
        }
        
        public boolean onSingleTapUp(MotionEvent paramAnonymousMotionEvent)
        {
          return true;
        }
      });
    }
    
    public boolean onInterceptTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent)
    {
      View localView = paramRecyclerView.findChildViewUnder(paramMotionEvent.getX(), paramMotionEvent.getY());
      if ((localView != null) && (this.clickListener != null) && (this.gestureDetector.onTouchEvent(paramMotionEvent))) {
        this.clickListener.onClick(localView, paramRecyclerView.getChildPosition(localView));
      }
      return false;
    }
    
    public void onRequestDisallowInterceptTouchEvent(boolean paramBoolean) {}
    
    public void onTouchEvent(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent) {}
  }
}
