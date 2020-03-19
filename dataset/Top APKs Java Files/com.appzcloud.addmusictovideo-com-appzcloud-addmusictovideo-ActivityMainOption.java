package com.appzcloud.addmusictovideo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.appzcloud.addmusictovideo.medialibraryvideo.BucketGridAdapterVideo;
import com.appzcloud.addmusictovideo.medialibraryvideo.GridViewAdapterVideo;
import com.appzcloud.addmusictovideo.medialibraryvideo.NavigationActivity;
import com.appzcloud.outpulist.medialibraryvideo.OutputAdapterVideo;
import com.appzcloud.outpulist.medialibraryvideo.OutputVideoActivity;
import com.appzcloud.outpulist.medialibraryvideo.mediachooser.OutputMediaModel;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAd.Image;
import com.facebook.ads.NativeAdsManager;
import com.facebook.ads.NativeAdsManager.Listener;
import com.firebase.client.Firebase;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.GoogleAnalytics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class ActivityMainOption
  extends Activity
  implements NativeAdsManager.Listener, com.facebook.ads.AdListener
{
  private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 2;
  public static ActivityMainOption OutputVideonavigation;
  private static final int RESULT_ACTIVITY = 1111;
  private static final int RESULT_LOAD_VIDEO = 1;
  public static boolean activityRunning = true;
  public static NativeAd ad = null;
  public static View adViewExitLayout = null;
  public static ActivityMainOption context;
  static int count;
  static TextView emptyMsgText;
  public static boolean facebookAdsFlag;
  static ListView imagegridOutput;
  public static InterstitialAd interstitial;
  public static int isBucketload;
  public static int listFirstPos;
  public static NativeAdsManager listNativeAdsManager;
  public static OutputAdapterVideo mGridAdapterVideoOutput;
  public static ActivityMainOption navigation = null;
  public static ProgressBar pbarH;
  static Cursor videoCursorOutput;
  static List<String> videouriOutput;
  LinearLayout BtnCapture;
  LinearLayout BtnGallery;
  LinearLayout BtnVideoList;
  View DialogViewInapp;
  AdView adView;
  public boolean adflag = true;
  Button btnBack;
  RelativeLayout btnCapture;
  RelativeLayout btnGallery;
  RelativeLayout btnVideoList;
  String camVideoFile;
  Thread checkProcessing;
  Dialog dialog;
  Dialog dialogFirst;
  public View dialogView;
  File dir;
  boolean flagVisible = false;
  public int flag_isshowallbucekets = 1;
  public int flag_isshowallbuceketsOutput = 1;
  int id1;
  int id2;
  int id3;
  int id4;
  int id5;
  public GridView imagegrid;
  Button inappBtn;
  int inappViewFlag = 0;
  LinearLayout layout;
  LinearLayout llbtn;
  public BucketGridAdapterVideo mBucketAdaptervideo;
  public GridViewAdapterVideo mGridAdapterVideo;
  HomeWatcher mHomeWatcher;
  public Timer mTimer = null;
  public TextView pText;
  public TextView pTextMsg;
  public RelativeLayout primaryLayout;
  Settings settings;
  Timer timer2;
  public List<Integer> videoBucketList = new ArrayList();
  List<Integer> videoBucketListOutput = new ArrayList();
  public Cursor videoCursor;
  private String videoPath;
  private Uri videoUri;
  public List<String> videouri = new ArrayList();
  
  static
  {
    isBucketload = 2;
    facebookAdsFlag = false;
    OutputVideonavigation = null;
    videouriOutput = new ArrayList();
    listFirstPos = 0;
  }
  
  public ActivityMainOption() {}
  
  static void DeleteRecursive(File paramFile)
  {
    File[] arrayOfFile;
    int j;
    int i;
    if (paramFile.isDirectory())
    {
      arrayOfFile = paramFile.listFiles();
      j = arrayOfFile.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        paramFile.delete();
        return;
      }
      DeleteRecursive(arrayOfFile[i]);
      i += 1;
    }
  }
  
  private void buttonEffect(View paramView)
  {
    paramView.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        }
        for (;;)
        {
          return false;
          paramAnonymousView.getBackground().setColorFilter(1291845632, PorterDuff.Mode.SRC_ATOP);
          paramAnonymousView.invalidate();
          continue;
          paramAnonymousView.getBackground().clearColorFilter();
          paramAnonymousView.invalidate();
        }
      }
    });
  }
  
  private static int convertToDp(int paramInt)
  {
    float f = context.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  private void createHandler()
  {
    new Thread()
    {
      public void run()
      {
        Looper.prepare();
        final Handler localHandler = new Handler();
        localHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            Thread.currentThread().setPriority(10);
            Log.e("handler", "kkkkkk");
            if ((!ActivityMainOption.this.settings.getPurchaseFlag()) && (ActivityMainOption.this.isOnline()) && ((ActivityMainOption.this.settings.get_ActivityAudioList_activity_native_ads_1()) || (ActivityMainOption.this.settings.get_navigation_activity_native_ads_1())))
            {
              ActivityMainOption.facebookAdsFlag = ActivityMainOption.this.isAvailable().booleanValue();
              if ((ActivityMainOption.listNativeAdsManager != null) && (ActivityMainOption.facebookAdsFlag))
              {
                ActivityMainOption.listNativeAdsManager.loadAds();
                Log.e("handler", "kkkkkk" + ActivityMainOption.facebookAdsFlag);
              }
            }
            localHandler.removeCallbacks(this);
            Looper.myLooper().quit();
          }
        }, 100L);
        Looper.loop();
      }
    }.start();
  }
  
  public static String getRealPathFromURI(Context paramContext, Uri paramUri)
  {
    Context localContext2 = null;
    Context localContext1 = null;
    try
    {
      paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      localContext1 = paramContext;
      localContext2 = paramContext;
      int i = paramContext.getColumnIndexOrThrow("_data");
      localContext1 = paramContext;
      localContext2 = paramContext;
      paramContext.moveToFirst();
      localContext1 = paramContext;
      localContext2 = paramContext;
      paramUri = paramContext.getString(i);
      return paramUri;
    }
    catch (Exception paramContext)
    {
      return null;
    }
    finally
    {
      if (localContext2 != null) {
        localContext2.close();
      }
    }
  }
  
  public static void inflateAd(NativeAd paramNativeAd, View paramView, Context paramContext)
  {
    paramContext = (ImageView)paramView.findViewById(2131492925);
    TextView localTextView1 = (TextView)paramView.findViewById(2131492926);
    TextView localTextView2 = (TextView)paramView.findViewById(2131492929);
    MediaView localMediaView = (MediaView)paramView.findViewById(2131492932);
    Button localButton = (Button)paramView.findViewById(2131492927);
    localButton.setText(paramNativeAd.getAdCallToAction());
    localButton.setVisibility(0);
    localTextView1.setText(paramNativeAd.getAdTitle());
    localTextView2.setText(paramNativeAd.getAdBody());
    setBitmap(paramNativeAd, paramContext);
    localMediaView.setNativeAd(paramNativeAd);
    paramNativeAd.registerViewForInteraction(paramView);
  }
  
  public static void inflateAdExitPopup(NativeAd paramNativeAd, View paramView, Context paramContext)
  {
    Object localObject = (ImageView)paramView.findViewById(2131492925);
    TextView localTextView1 = (TextView)paramView.findViewById(2131492926);
    TextView localTextView2 = (TextView)paramView.findViewById(2131492929);
    MediaView localMediaView = (MediaView)paramView.findViewById(2131492932);
    Button localButton = (Button)paramView.findViewById(2131492927);
    localButton.setText(paramNativeAd.getAdCallToAction());
    localButton.setVisibility(0);
    localTextView1.setText(paramNativeAd.getAdTitle());
    localTextView2.setText(paramNativeAd.getAdBody());
    setBitmap(ad, (ImageView)localObject);
    localObject = paramNativeAd.getAdCoverImage();
    int i = ((NativeAd.Image)localObject).getWidth();
    int j = ((NativeAd.Image)localObject).getHeight();
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    localObject = new DisplayMetrics();
    paramContext.getMetrics((DisplayMetrics)localObject);
    int k = ((DisplayMetrics)localObject).widthPixels;
    int m = ((DisplayMetrics)localObject).heightPixels;
    localMediaView.setLayoutParams(new RelativeLayout.LayoutParams(k - convertToDp(20), Math.min((int)((k - convertToDp(20)) / i * j), m / 3)));
    localMediaView.setNativeAd(paramNativeAd);
    paramNativeAd.registerViewForInteraction(paramView);
  }
  
  private static void initPhoneImages(String paramString, int paramInt, Cursor paramCursor)
  {
    try
    {
      new StringBuilder("bucket_display_name = \"").append(paramString).append("\" And bucket_id = ").append(paramInt).toString();
      videoCursorOutput = paramCursor;
      setAdapter(videoCursorOutput, paramString, paramInt);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  private void initVideo()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokevirtual 437	com/appzcloud/addmusictovideo/ActivityMainOption:getContentResolver	()Landroid/content/ContentResolver;
    //   5: getstatic 442	android/provider/MediaStore$Video$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   8: iconst_3
    //   9: anewarray 274	java/lang/String
    //   12: dup
    //   13: iconst_0
    //   14: ldc_w 444
    //   17: aastore
    //   18: dup
    //   19: iconst_1
    //   20: ldc_w 446
    //   23: aastore
    //   24: dup
    //   25: iconst_2
    //   26: ldc_w 276
    //   29: aastore
    //   30: aconst_null
    //   31: aconst_null
    //   32: ldc_w 448
    //   35: invokevirtual 282	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   38: putfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   41: new 166	java/util/ArrayList
    //   44: dup
    //   45: invokespecial 169	java/util/ArrayList:<init>	()V
    //   48: astore_1
    //   49: aload_0
    //   50: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   53: ifnull +15 -> 68
    //   56: aload_0
    //   57: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   60: invokeinterface 453 1 0
    //   65: ifne +78 -> 143
    //   68: aload_0
    //   69: iconst_1
    //   70: putfield 185	com/appzcloud/addmusictovideo/ActivityMainOption:flag_isshowallbucekets	I
    //   73: aload_0
    //   74: ldc_w 454
    //   77: invokevirtual 455	com/appzcloud/addmusictovideo/ActivityMainOption:findViewById	(I)Landroid/view/View;
    //   80: checkcast 457	android/widget/GridView
    //   83: astore_2
    //   84: aload_0
    //   85: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   88: ifnull +38 -> 126
    //   91: aload_0
    //   92: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   95: invokeinterface 460 1 0
    //   100: ifle +26 -> 126
    //   103: aload_0
    //   104: new 462	com/appzcloud/addmusictovideo/medialibraryvideo/BucketGridAdapterVideo
    //   107: dup
    //   108: aload_0
    //   109: iconst_0
    //   110: aload_1
    //   111: iconst_1
    //   112: invokespecial 465	com/appzcloud/addmusictovideo/medialibraryvideo/BucketGridAdapterVideo:<init>	(Landroid/content/Context;ILjava/util/ArrayList;Z)V
    //   115: putfield 467	com/appzcloud/addmusictovideo/ActivityMainOption:mBucketAdaptervideo	Lcom/appzcloud/addmusictovideo/medialibraryvideo/BucketGridAdapterVideo;
    //   118: aload_2
    //   119: aload_0
    //   120: getfield 467	com/appzcloud/addmusictovideo/ActivityMainOption:mBucketAdaptervideo	Lcom/appzcloud/addmusictovideo/medialibraryvideo/BucketGridAdapterVideo;
    //   123: invokevirtual 470	android/widget/GridView:setAdapter	(Landroid/widget/ListAdapter;)V
    //   126: aload_0
    //   127: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   130: ifnull +12 -> 142
    //   133: aload_0
    //   134: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   137: invokeinterface 298 1 0
    //   142: return
    //   143: new 472	com/appzcloud/addmusictovideo/medialibraryvideo/BucketEntry
    //   146: dup
    //   147: aload_0
    //   148: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   151: iconst_0
    //   152: invokeinterface 475 2 0
    //   157: aload_0
    //   158: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   161: iconst_1
    //   162: invokeinterface 295 2 0
    //   167: aload_0
    //   168: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   171: iconst_2
    //   172: invokeinterface 295 2 0
    //   177: iconst_0
    //   178: invokespecial 478	com/appzcloud/addmusictovideo/medialibraryvideo/BucketEntry:<init>	(ILjava/lang/String;Ljava/lang/String;Z)V
    //   181: astore_2
    //   182: aload_1
    //   183: aload_2
    //   184: invokevirtual 482	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   187: ifne -138 -> 49
    //   190: aload_1
    //   191: aload_2
    //   192: invokevirtual 485	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   195: pop
    //   196: goto -147 -> 49
    //   199: astore_1
    //   200: aload_0
    //   201: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   204: ifnull +12 -> 216
    //   207: aload_0
    //   208: getfield 450	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursor	Landroid/database/Cursor;
    //   211: invokeinterface 298 1 0
    //   216: aload_1
    //   217: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	218	0	this	ActivityMainOption
    //   48	143	1	localArrayList	ArrayList
    //   199	18	1	localObject1	Object
    //   83	109	2	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   49	68	199	finally
    //   68	126	199	finally
    //   143	196	199	finally
  }
  
  /* Error */
  private static void initVideoOutPut()
  {
    // Byte code:
    //   0: iconst_0
    //   1: putstatic 488	com/appzcloud/addmusictovideo/ActivityMainOption:count	I
    //   4: new 490	android/support/v4/content/CursorLoader
    //   7: dup
    //   8: getstatic 164	com/appzcloud/addmusictovideo/ActivityMainOption:OutputVideonavigation	Lcom/appzcloud/addmusictovideo/ActivityMainOption;
    //   11: getstatic 442	android/provider/MediaStore$Video$Media:EXTERNAL_CONTENT_URI	Landroid/net/Uri;
    //   14: iconst_3
    //   15: anewarray 274	java/lang/String
    //   18: dup
    //   19: iconst_0
    //   20: ldc_w 444
    //   23: aastore
    //   24: dup
    //   25: iconst_1
    //   26: ldc_w 446
    //   29: aastore
    //   30: dup
    //   31: iconst_2
    //   32: ldc_w 276
    //   35: aastore
    //   36: ldc_w 492
    //   39: iconst_2
    //   40: anewarray 274	java/lang/String
    //   43: dup
    //   44: iconst_0
    //   45: ldc_w 494
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: ldc_w 496
    //   54: aastore
    //   55: ldc_w 448
    //   58: invokespecial 499	android/support/v4/content/CursorLoader:<init>	(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)V
    //   61: invokevirtual 503	android/support/v4/content/CursorLoader:loadInBackground	()Landroid/database/Cursor;
    //   64: putstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   67: new 166	java/util/ArrayList
    //   70: dup
    //   71: invokespecial 169	java/util/ArrayList:<init>	()V
    //   74: astore_0
    //   75: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   78: ifnull +14 -> 92
    //   81: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   84: invokeinterface 453 1 0
    //   89: ifne +89 -> 178
    //   92: getstatic 164	com/appzcloud/addmusictovideo/ActivityMainOption:OutputVideonavigation	Lcom/appzcloud/addmusictovideo/ActivityMainOption;
    //   95: ldc_w 504
    //   98: invokevirtual 455	com/appzcloud/addmusictovideo/ActivityMainOption:findViewById	(I)Landroid/view/View;
    //   101: checkcast 506	android/widget/ListView
    //   104: putstatic 508	com/appzcloud/addmusictovideo/ActivityMainOption:imagegridOutput	Landroid/widget/ListView;
    //   107: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   110: ifnull +53 -> 163
    //   113: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   116: invokeinterface 460 1 0
    //   121: ifle +42 -> 163
    //   124: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   127: invokeinterface 460 1 0
    //   132: putstatic 488	com/appzcloud/addmusictovideo/ActivityMainOption:count	I
    //   135: aload_0
    //   136: iconst_0
    //   137: invokevirtual 512	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   140: checkcast 514	com/appzcloud/outpulist/medialibraryvideo/OutPutBucketEntry
    //   143: getfield 517	com/appzcloud/outpulist/medialibraryvideo/OutPutBucketEntry:bucketName	Ljava/lang/String;
    //   146: aload_0
    //   147: iconst_0
    //   148: invokevirtual 512	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   151: checkcast 514	com/appzcloud/outpulist/medialibraryvideo/OutPutBucketEntry
    //   154: getfield 520	com/appzcloud/outpulist/medialibraryvideo/OutPutBucketEntry:bucketId	I
    //   157: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   160: invokestatic 522	com/appzcloud/addmusictovideo/ActivityMainOption:initPhoneImages	(Ljava/lang/String;ILandroid/database/Cursor;)V
    //   163: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   166: ifnull +11 -> 177
    //   169: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   172: invokeinterface 298 1 0
    //   177: return
    //   178: new 514	com/appzcloud/outpulist/medialibraryvideo/OutPutBucketEntry
    //   181: dup
    //   182: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   185: iconst_0
    //   186: invokeinterface 475 2 0
    //   191: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   194: iconst_1
    //   195: invokeinterface 295 2 0
    //   200: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   203: iconst_2
    //   204: invokeinterface 295 2 0
    //   209: invokespecial 525	com/appzcloud/outpulist/medialibraryvideo/OutPutBucketEntry:<init>	(ILjava/lang/String;Ljava/lang/String;)V
    //   212: astore_1
    //   213: aload_0
    //   214: aload_1
    //   215: invokevirtual 482	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   218: ifne -143 -> 75
    //   221: aload_0
    //   222: aload_1
    //   223: invokevirtual 485	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   226: pop
    //   227: goto -152 -> 75
    //   230: astore_0
    //   231: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   234: ifnull +11 -> 245
    //   237: getstatic 428	com/appzcloud/addmusictovideo/ActivityMainOption:videoCursorOutput	Landroid/database/Cursor;
    //   240: invokeinterface 298 1 0
    //   245: aload_0
    //   246: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   74	148	0	localArrayList	ArrayList
    //   230	16	0	localObject	Object
    //   212	11	1	localOutPutBucketEntry	com.appzcloud.outpulist.medialibraryvideo.OutPutBucketEntry
    // Exception table:
    //   from	to	target	type
    //   75	92	230	finally
    //   92	163	230	finally
    //   178	227	230	finally
  }
  
  private boolean isOnline()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  private void restartFirstActivity()
  {
    Intent localIntent = getApplicationContext().getPackageManager().getLaunchIntentForPackage(getApplicationContext().getPackageName());
    localIntent.addFlags(335544320);
    startActivity(localIntent);
  }
  
  private static void setAdapter(Cursor paramCursor, String paramString, int paramInt)
  {
    if (paramCursor.getCount() > 0)
    {
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      for (;;)
      {
        if (i >= paramCursor.getCount())
        {
          count = localArrayList.size();
          mGridAdapterVideoOutput = new OutputAdapterVideo(OutputVideonavigation, 0, localArrayList, false, paramString, paramInt);
          imagegridOutput.setAdapter(mGridAdapterVideoOutput);
          return;
        }
        paramCursor.moveToPosition(i);
        localArrayList.add(new OutputMediaModel(paramCursor.getString(paramCursor.getColumnIndex("_data")).toString(), false));
        i += 1;
      }
    }
    Toast.makeText(OutputVideonavigation, "no_media_file_available", 0).show();
  }
  
  public static void setBitmap(NativeAd paramNativeAd, final ImageView paramImageView)
  {
    new AsyncTask()
    {
      protected NativeAd.Image doInBackground(Void... paramAnonymousVarArgs)
      {
        try
        {
          paramAnonymousVarArgs = ActivityMainOption.this.getAdIcon();
          return paramAnonymousVarArgs;
        }
        catch (Exception paramAnonymousVarArgs) {}
        return null;
      }
      
      protected void onPostExecute(NativeAd.Image paramAnonymousImage)
      {
        super.onPostExecute(paramAnonymousImage);
        NativeAd.downloadAndDisplayImage(paramAnonymousImage, paramImageView);
      }
    }.execute(new Void[0]);
  }
  
  public void alertDialoGallery()
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Info");
    ((AlertDialog.Builder)localObject).setMessage("Pl.Select another video. Video should be more than 1 second.");
    ((AlertDialog.Builder)localObject).setCancelable(false);
    ((AlertDialog.Builder)localObject).setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent(ActivityMainOption.this, NavigationActivity.class);
        ActivityMainOption.this.startActivityForResult(paramAnonymousDialogInterface, 1);
      }
    });
    ((AlertDialog.Builder)localObject).setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setIcon(2130837639);
    ((AlertDialog)localObject).show();
  }
  
  public void alertDialogCamera()
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Info");
    ((AlertDialog.Builder)localObject).setMessage("Pl.Record another video. Video should be more than 1 second.");
    ((AlertDialog.Builder)localObject).setCancelable(false);
    ((AlertDialog.Builder)localObject).setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = Environment.getExternalStorageDirectory().getAbsolutePath() + "/VidMusic_CamCapture";
        ActivityMainOption.this.dir = new File((String)localObject);
        if (!ActivityMainOption.this.dir.exists()) {
          ActivityMainOption.this.dir.mkdirs();
        }
        ActivityMainOption.this.camVideoFile = (ActivityMainOption.this.dir.getAbsolutePath() + "/Vid_Cam" + System.currentTimeMillis() + ".mp4");
        localObject = new File(ActivityMainOption.this.camVideoFile);
        try
        {
          Intent localIntent1 = new Intent("android.media.action.VIDEO_CAPTURE");
          ActivityMainOption.this.videoUri = Uri.fromFile((File)localObject);
          localIntent1.setPackage(ActivityMainOption.this.getCamPackage());
          localIntent1.putExtra("output", ActivityMainOption.this.videoUri);
          ActivityMainOption.this.startActivityForResult(localIntent1, 2);
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
              ActivityMainOption.this.videoUri = Uri.fromFile((File)localObject);
              localIntent2.putExtra("output", ActivityMainOption.this.videoUri);
              ActivityMainOption.this.startActivityForResult(localIntent2, 2);
            }
            catch (Exception localException1)
            {
              Toast.makeText(ActivityMainOption.this, "There appears to be some issue with Camera. Pl. Check", 1).show();
            }
          }
        }
      }
    });
    ((AlertDialog.Builder)localObject).setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setIcon(2130837639);
    ((AlertDialog)localObject).show();
  }
  
  public void finish()
  {
    super.finish();
  }
  
  public String getCamPackage()
  {
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledApplications(8192);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return "";
      }
      if (((((ApplicationInfo)localList.get(i)).flags & 0x1) == 1) && (((ApplicationInfo)localList.get(i)).loadLabel(localPackageManager).toString().equalsIgnoreCase("Camera"))) {
        return ((ApplicationInfo)localList.get(i)).packageName;
      }
      i += 1;
    }
  }
  
  public Boolean isAvailable()
  {
    try
    {
      if (Runtime.getRuntime().exec("ping -c 1    www.google.com").waitFor() == 0) {}
      for (boolean bool = true; bool; bool = false) {
        return Boolean.valueOf(bool);
      }
      return Boolean.valueOf(false);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void moreAppOptions(View paramView)
  {
    if (this.DialogViewInapp != null)
    {
      this.primaryLayout.removeView(this.DialogViewInapp);
      this.DialogViewInapp = null;
    }
    this.primaryLayout = ((RelativeLayout)findViewById(2131492887));
    this.DialogViewInapp = getLayoutInflater().inflate(2130903060, null);
    paramView = new RelativeLayout.LayoutParams(-1, -2);
    paramView.addRule(11);
    paramView.topMargin = convertToDp(10);
    paramView.rightMargin = convertToDp(5);
    paramView.width = convertToDp(155);
    paramView.height = convertToDp(134);
    this.primaryLayout.addView(this.DialogViewInapp, paramView);
    paramView = AnimationUtils.makeInAnimation(this, false);
    paramView.setDuration(300L);
    this.DialogViewInapp.startAnimation(paramView);
    paramView = (Button)this.DialogViewInapp.findViewById(2131492977);
    Button localButton1 = (Button)this.DialogViewInapp.findViewById(2131492975);
    Button localButton2 = (Button)this.DialogViewInapp.findViewById(2131492976);
    buttonEffect(paramView);
    buttonEffect(localButton1);
    buttonEffect(localButton2);
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.SENDTO");
        paramAnonymousView.setType("text/plain");
        paramAnonymousView.putExtra("android.intent.extra.SUBJECT", "Feedback for Music Video Editor - Android");
        paramAnonymousView.setData(Uri.parse("mailto:support@appzcloud.com"));
        paramAnonymousView.addFlags(268435456);
        ActivityMainOption.this.startActivity(paramAnonymousView);
      }
    });
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.SEND");
        paramAnonymousView.setType("text/plain");
        paramAnonymousView.putExtra("android.intent.extra.TEXT", "Add your Favorite Music to your videos! Get it today.\n\nhttps://play.google.com/store/apps/details?id=com.appzcloud.addmusictovideo");
        ActivityMainOption.this.startActivity(Intent.createChooser(paramAnonymousView, "Share Music Video Editor with your friends."));
      }
    });
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ActivityMainOption.this.primaryLayout.removeView(ActivityMainOption.this.DialogViewInapp);
        try
        {
          ActivityMainOption.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:AppzCloud+Technologies")));
          return;
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          ActivityMainOption.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/search?q=pub:AppzCloud+Technologies")));
        }
      }
    });
  }
  
  /* Error */
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_0
    //   1: iload_1
    //   2: iload_2
    //   3: aload_3
    //   4: invokespecial 797	android/app/Activity:onActivityResult	(IILandroid/content/Intent;)V
    //   7: iload_1
    //   8: iconst_2
    //   9: if_icmpne +86 -> 95
    //   12: iload_2
    //   13: iconst_m1
    //   14: if_icmpne +197 -> 211
    //   17: aload_0
    //   18: aload_0
    //   19: getfield 216	com/appzcloud/addmusictovideo/ActivityMainOption:videoUri	Landroid/net/Uri;
    //   22: invokevirtual 802	android/net/Uri:getPath	()Ljava/lang/String;
    //   25: invokevirtual 805	java/lang/String:trim	()Ljava/lang/String;
    //   28: putfield 807	com/appzcloud/addmusictovideo/ActivityMainOption:videoPath	Ljava/lang/String;
    //   31: new 809	android/media/MediaMetadataRetriever
    //   34: dup
    //   35: invokespecial 810	android/media/MediaMetadataRetriever:<init>	()V
    //   38: astore_3
    //   39: aload_3
    //   40: aload_0
    //   41: getfield 807	com/appzcloud/addmusictovideo/ActivityMainOption:videoPath	Ljava/lang/String;
    //   44: invokevirtual 813	android/media/MediaMetadataRetriever:setDataSource	(Ljava/lang/String;)V
    //   47: aload_3
    //   48: bipush 9
    //   50: invokevirtual 816	android/media/MediaMetadataRetriever:extractMetadata	(I)Ljava/lang/String;
    //   53: invokestatic 822	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   56: ldc2_w 823
    //   59: lcmp
    //   60: ifle +140 -> 200
    //   63: new 560	android/content/Intent
    //   66: dup
    //   67: aload_0
    //   68: ldc_w 826
    //   71: invokespecial 829	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   74: astore_3
    //   75: aload_3
    //   76: ldc_w 831
    //   79: aload_0
    //   80: getfield 807	com/appzcloud/addmusictovideo/ActivityMainOption:videoPath	Ljava/lang/String;
    //   83: invokevirtual 835	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   86: pop
    //   87: aload_0
    //   88: aload_3
    //   89: sipush 1111
    //   92: invokevirtual 839	com/appzcloud/addmusictovideo/ActivityMainOption:startActivityForResult	(Landroid/content/Intent;I)V
    //   95: iload_1
    //   96: iconst_1
    //   97: if_icmpne +79 -> 176
    //   100: iload_2
    //   101: iconst_m1
    //   102: if_icmpne +74 -> 176
    //   105: aload_0
    //   106: getstatic 843	com/appzcloud/addmusictovideo/medialibraryvideo/NavigationActivity:videoUri	Ljava/lang/String;
    //   109: putfield 807	com/appzcloud/addmusictovideo/ActivityMainOption:videoPath	Ljava/lang/String;
    //   112: new 809	android/media/MediaMetadataRetriever
    //   115: dup
    //   116: invokespecial 810	android/media/MediaMetadataRetriever:<init>	()V
    //   119: astore_3
    //   120: aload_3
    //   121: aload_0
    //   122: getfield 807	com/appzcloud/addmusictovideo/ActivityMainOption:videoPath	Ljava/lang/String;
    //   125: invokevirtual 813	android/media/MediaMetadataRetriever:setDataSource	(Ljava/lang/String;)V
    //   128: aload_3
    //   129: bipush 9
    //   131: invokevirtual 816	android/media/MediaMetadataRetriever:extractMetadata	(I)Ljava/lang/String;
    //   134: invokestatic 822	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   137: ldc2_w 823
    //   140: lcmp
    //   141: ifle +102 -> 243
    //   144: new 560	android/content/Intent
    //   147: dup
    //   148: aload_0
    //   149: ldc_w 826
    //   152: invokespecial 829	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   155: astore_3
    //   156: aload_3
    //   157: ldc_w 831
    //   160: aload_0
    //   161: getfield 807	com/appzcloud/addmusictovideo/ActivityMainOption:videoPath	Ljava/lang/String;
    //   164: invokevirtual 835	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   167: pop
    //   168: aload_0
    //   169: aload_3
    //   170: sipush 1111
    //   173: invokevirtual 839	com/appzcloud/addmusictovideo/ActivityMainOption:startActivityForResult	(Landroid/content/Intent;I)V
    //   176: iload_1
    //   177: sipush 1111
    //   180: if_icmpne +3 -> 183
    //   183: iload_1
    //   184: sipush 3231
    //   187: if_icmpne +12 -> 199
    //   190: iload_2
    //   191: iconst_m1
    //   192: if_icmpne +7 -> 199
    //   195: aload_0
    //   196: invokevirtual 844	com/appzcloud/addmusictovideo/ActivityMainOption:finish	()V
    //   199: return
    //   200: aload_0
    //   201: invokevirtual 846	com/appzcloud/addmusictovideo/ActivityMainOption:alertDialogCamera	()V
    //   204: goto -109 -> 95
    //   207: astore_3
    //   208: goto -113 -> 95
    //   211: iload_2
    //   212: ifne +17 -> 229
    //   215: aload_0
    //   216: ldc_w 848
    //   219: iconst_1
    //   220: invokestatic 600	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   223: invokevirtual 603	android/widget/Toast:show	()V
    //   226: goto -131 -> 95
    //   229: aload_0
    //   230: ldc_w 850
    //   233: iconst_1
    //   234: invokestatic 600	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   237: invokevirtual 603	android/widget/Toast:show	()V
    //   240: goto -145 -> 95
    //   243: aload_0
    //   244: invokevirtual 852	com/appzcloud/addmusictovideo/ActivityMainOption:alertDialoGallery	()V
    //   247: goto -71 -> 176
    //   250: astore_3
    //   251: aload_0
    //   252: ldc_w 854
    //   255: iconst_1
    //   256: invokestatic 600	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   259: invokevirtual 603	android/widget/Toast:show	()V
    //   262: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	263	0	this	ActivityMainOption
    //   0	263	1	paramInt1	int
    //   0	263	2	paramInt2	int
    //   0	263	3	paramIntent	Intent
    // Exception table:
    //   from	to	target	type
    //   31	95	207	java/lang/Exception
    //   200	204	207	java/lang/Exception
    //   112	176	250	java/lang/Exception
    //   243	247	250	java/lang/Exception
  }
  
  public void onAdClicked(Ad paramAd) {}
  
  public void onAdError(AdError paramAdError) {}
  
  public void onAdLoaded(Ad paramAd) {}
  
  public void onAdsLoaded()
  {
    ad = listNativeAdsManager.nextNativeAd();
    ad.setAdListener(this);
    preloadExitAds();
  }
  
  public void onBackPressed()
  {
    if ((!this.settings.getPurchaseFlag()) && (isOnline()) && (this.settings.get_dialog_exitApp_native_ads()) && (ad != null) && (adViewExitLayout != null) && (ad.getAdTitle() != null) && (ad.getAdCallToAction() != null))
    {
      startActivityForResult(new Intent(this, ActivityExitApp.class), 3231);
      return;
    }
    super.onBackPressed();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Firebase.setAndroidContext(this);
    context = this;
    setContentView(2130903063);
    activityRunning = true;
    navigation = this;
    OutputVideonavigation = this;
    MyResources.activity = true;
    this.settings = Settings.getSettings(this);
    if ((this.settings.getPurchaseFlag()) || (!isOnline()))
    {
      startActivity(new Intent(this, FirstScreenActivity.class));
      Log.d("hiiiiiiiiii", "1111111111111111111111111111111");
    }
    if ((!this.settings.getPurchaseFlag()) && (isOnline()) && ((this.settings.get_ActivityAudioList_activity_native_ads_1()) || (this.settings.get_navigation_activity_native_ads_1()) || (this.settings.get_dialog_exitApp_native_ads()) || (this.settings.get_dialog_genration_video_native_ads()) || (this.settings.get_ActivityAudioGallery_native_ads_1())))
    {
      this.imagegrid = ((GridView)findViewById(2131492896));
      initVideo();
      imagegridOutput = (ListView)findViewById(2131492920);
      initVideoOutPut();
      listNativeAdsManager = new NativeAdsManager(this, "973568396048110_973569272714689", 1);
      listNativeAdsManager.setListener(this);
      createHandler();
      Log.e("hiiiiiiiiii", "22222222222222222222222222222222222" + isOnline());
      openDialogprogress();
    }
    this.inappBtn = ((Button)findViewById(2131492981));
    MyResources.myFirebaseRef = new Firebase("https://android-add-audio-to-video.firebaseio.com/");
    MyResources.doAuthorizeUser("https://android-add-audio-to-video.firebaseio.com/", "RfrIFM5nPfvyggBqgJjnGsuturw00LJuHGwsjfkf", this);
    this.inappBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ActivityMainOption.this.startActivity(new Intent(ActivityMainOption.this, InAppActivity.class));
      }
    });
    setRateInappPlan();
    if (this.settings.getVersionCodeSetUnset()) {
      paramBundle = getPackageManager();
    }
    try
    {
      paramBundle = paramBundle.getPackageInfo(getPackageName(), 0).versionName;
      this.settings.setPrevVersionCode(paramBundle);
      this.settings.setVersionCodeSetUnset(false);
      paramBundle = getPackageManager();
      try
      {
        paramBundle = paramBundle.getPackageInfo(getPackageName(), 0).versionName;
        this.settings.setNewVersionCode(paramBundle);
        try
        {
          DeleteRecursive(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/VidsAudio"));
          try
          {
            if (Build.VERSION.SDK_INT < 19) {
              DeleteRecursive(new File(System.getenv("SECONDARY_STORAGE") + "/VidsAudio"));
            }
            if (MyResources.activity)
            {
              MyResources.setQueryFireTime(this);
              MyResources.activity = false;
            }
            if (this.settings.get_FirstScreen_activity_interstitial_counter_app() <= 100000) {
              this.settings.set_FirstScreen_activity_interstitial_counter_app(this.settings.get_FirstScreen_activity_interstitial_counter_app() + 1);
            }
            if (this.settings.get_FirstScreen_activity_init_interstitial_app() <= 1000) {
              this.settings.set_FirstScreen_activity_init_interstitial_app(this.settings.get_FirstScreen_activity_init_interstitial_app() + 1);
            }
            if (this.settings.get_FirstScreen_activity_init_banner_app() <= 1000) {
              this.settings.set_FirstScreen_activity_init_banner_app(this.settings.get_FirstScreen_activity_init_banner_app() + 1);
            }
            if (!this.settings.getPurchaseFlag())
            {
              interstitial = new InterstitialAd(this);
              interstitial.setAdUnitId(getString(2131165238));
              interstitial.loadAd(new AdRequest.Builder().build());
              interstitial.setAdListener(new com.google.android.gms.ads.AdListener()
              {
                public void onAdClosed()
                {
                  ActivityMainOption.interstitial.loadAd(new AdRequest.Builder().build());
                }
                
                public void onAdFailedToLoad(int paramAnonymousInt) {}
                
                public void onAdLeftApplication() {}
                
                public void onAdLoaded()
                {
                  if (ActivityMainOption.this.adflag)
                  {
                    MyResources.adsDisplayFlag(ActivityMainOption.this.settings.get_FirstScreen_activity_interstitial(), ActivityMainOption.this.settings.get_FirstScreen_activity_interstitial_counter_app(), ActivityMainOption.this.settings.get_FirstScreen_activity_interstitial_counter_parse(), ActivityMainOption.this.settings.get_FirstScreen_activity_init_interstitial_app(), ActivityMainOption.this.settings.get_FirstScreen_activity_init_interstitial_parse(), ActivityMainOption.this.settings.get_FirstScreen_activity_interstitial_app_only_once(), ActivityMainOption.this, 102);
                    ActivityMainOption.this.adflag = false;
                  }
                }
                
                public void onAdOpened() {}
              });
              if ((this.settings.get_FirstScreen_activity_banner()) && (this.settings.get_FirstScreen_activity_init_banner_app() >= this.settings.get_FirstScreen_activity_init_banner_parse()))
              {
                this.adView = ((AdView)findViewById(2131492891));
                if (!isOnline()) {
                  break label890;
                }
                this.adView.setVisibility(0);
                this.adView.loadAd(new AdRequest.Builder().build());
              }
            }
            for (;;)
            {
              ((MyTracker)getApplication()).getTracker(MyTracker.TrackerName.APP_TRACKER);
              this.btnCapture = ((RelativeLayout)findViewById(2131492984));
              this.btnGallery = ((RelativeLayout)findViewById(2131492982));
              this.btnVideoList = ((RelativeLayout)findViewById(2131492987));
              buttonEffect(this.btnCapture);
              buttonEffect(this.btnGallery);
              buttonEffect(this.btnVideoList);
              this.btnCapture.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (MyTempResources.isVideoConversionProgress)
                  {
                    if (ActivityMainOption.this.settings.getVideoCount() == 0)
                    {
                      Toast.makeText(ActivityMainOption.this, "Video is under processing. It may take some time.\nPl. check your notification.", 1).show();
                      return;
                    }
                    Toast.makeText(ActivityMainOption.this, "Video is under processing. It may take some time.\nPl. check your notification. In the meantime, You can watch your earlier videos from Video List.", 1).show();
                    return;
                  }
                  paramAnonymousView = Environment.getExternalStorageDirectory().getAbsolutePath() + "/VidMusic_CamCapture";
                  ActivityMainOption.this.dir = new File(paramAnonymousView);
                  if (!ActivityMainOption.this.dir.exists()) {
                    ActivityMainOption.this.dir.mkdirs();
                  }
                  ActivityMainOption.this.camVideoFile = (ActivityMainOption.this.dir.getAbsolutePath() + "/Vid_Cam" + System.currentTimeMillis() + ".mp4");
                  paramAnonymousView = new File(ActivityMainOption.this.camVideoFile);
                  try
                  {
                    Intent localIntent1 = new Intent("android.media.action.VIDEO_CAPTURE");
                    ActivityMainOption.this.videoUri = Uri.fromFile(paramAnonymousView);
                    localIntent1.setPackage(ActivityMainOption.this.getCamPackage());
                    localIntent1.putExtra("output", ActivityMainOption.this.videoUri);
                    ActivityMainOption.this.startActivityForResult(localIntent1, 2);
                    return;
                  }
                  catch (Exception localException)
                  {
                    try
                    {
                      Intent localIntent2 = new Intent("android.media.action.VIDEO_CAPTURE");
                      ActivityMainOption.this.videoUri = Uri.fromFile(paramAnonymousView);
                      localIntent2.putExtra("output", ActivityMainOption.this.videoUri);
                      ActivityMainOption.this.startActivityForResult(localIntent2, 2);
                      return;
                    }
                    catch (Exception paramAnonymousView)
                    {
                      Toast.makeText(ActivityMainOption.this, "There appears to be some issue with Camera. Pl. Check", 1).show();
                    }
                  }
                }
              });
              this.btnGallery.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  
                  if ((!ActivityMainOption.this.settings.getPurchaseFlag()) && ((ActivityMainOption.this.settings.get_ActivityAudioList_activity_native_ads_1()) || (ActivityMainOption.this.settings.get_navigation_activity_native_ads_1())) && (ActivityMainOption.listNativeAdsManager != null) && (!ActivityMainOption.listNativeAdsManager.isLoaded()))
                  {
                    ActivityMainOption.listNativeAdsManager.setListener(null);
                    ActivityMainOption.listNativeAdsManager = null;
                    System.gc();
                    ActivityMainOption.listNativeAdsManager = new NativeAdsManager(ActivityMainOption.this, "973568396048110_973569272714689", 1);
                    ActivityMainOption.listNativeAdsManager.setListener(ActivityMainOption.this);
                  }
                  if (MyTempResources.isVideoConversionProgress)
                  {
                    if (ActivityMainOption.this.settings.getVideoCount() == 0)
                    {
                      Toast.makeText(ActivityMainOption.this, "Video is under processing. It may take some time.\nPl. check your notification.", 1).show();
                      return;
                    }
                    Toast.makeText(ActivityMainOption.this, "Video is under processing. It may take some time.\nPl. check your notification. In the meantime, You can watch your earlier videos from Video List.", 1).show();
                    return;
                  }
                  paramAnonymousView = new Intent(ActivityMainOption.this, NavigationActivity.class);
                  ActivityMainOption.this.startActivityForResult(paramAnonymousView, 1);
                }
              });
              this.btnVideoList.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if ((!ActivityMainOption.this.settings.getPurchaseFlag()) && ((ActivityMainOption.this.settings.get_ActivityAudioList_activity_native_ads_1()) || (ActivityMainOption.this.settings.get_navigation_activity_native_ads_1())) && (ActivityMainOption.listNativeAdsManager != null) && (!ActivityMainOption.listNativeAdsManager.isLoaded()))
                  {
                    ActivityMainOption.listNativeAdsManager.setListener(null);
                    ActivityMainOption.listNativeAdsManager = null;
                    System.gc();
                    ActivityMainOption.listNativeAdsManager = new NativeAdsManager(ActivityMainOption.this, "973568396048110_973569272714689", 1);
                    ActivityMainOption.listNativeAdsManager.setListener(ActivityMainOption.this);
                  }
                  paramAnonymousView = new Intent(ActivityMainOption.this, OutputVideoActivity.class);
                  ActivityMainOption.this.startActivity(paramAnonymousView);
                }
              });
              this.primaryLayout = ((RelativeLayout)findViewById(2131492887));
              this.primaryLayout.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymousView)
                {
                  if (ActivityMainOption.this.DialogViewInapp != null)
                  {
                    ActivityMainOption.this.primaryLayout.removeView(ActivityMainOption.this.DialogViewInapp);
                    ActivityMainOption.this.DialogViewInapp = null;
                  }
                }
              });
              if (!this.settings.getPurchaseFlag())
              {
                this.mHomeWatcher = null;
                this.mHomeWatcher = new HomeWatcher(this);
                this.mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener()
                {
                  public void onHomeLongPressed() {}
                  
                  public void onHomePressed()
                  {
                    MyResources.activity = true;
                  }
                });
                this.mHomeWatcher.startWatch();
              }
              return;
              label890:
              this.adView.setVisibility(8);
            }
          }
          catch (Exception paramBundle)
          {
            for (;;) {}
          }
        }
        catch (Exception paramBundle)
        {
          for (;;) {}
        }
      }
      catch (PackageManager.NameNotFoundException paramBundle)
      {
        for (;;) {}
      }
    }
    catch (PackageManager.NameNotFoundException paramBundle)
    {
      for (;;) {}
    }
  }
  
  protected void onDestroy()
  {
    activityRunning = false;
    try
    {
      if (this.adView != null) {
        this.adView.destroy();
      }
      try
      {
        if ((!this.settings.getPurchaseFlag()) && (this.mHomeWatcher != null)) {
          this.mHomeWatcher.stopWatch();
        }
        super.onDestroy();
        return;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public void onError(Ad paramAd, AdError paramAdError) {}
  
  protected void onPause()
  {
    if (this.adView != null) {
      this.adView.pause();
    }
    this.adflag = false;
    super.onPause();
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    if (paramBundle != null) {}
    try
    {
      this.videoUri = Uri.parse(paramBundle.getString("videoUri"));
      super.onRestoreInstanceState(paramBundle);
      return;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.adView != null) {
      this.adView.resume();
    }
    if ((this.adView != null) && (this.settings.getPurchaseFlag())) {
      this.adView.setVisibility(8);
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (this.videoUri != null) {
      paramBundle.putString("videoUri", this.videoUri.toString());
    }
  }
  
  protected void onStart()
  {
    if (MyResources.activity)
    {
      MyResources.setQueryFireTime(this);
      MyResources.activity = false;
    }
    super.onStart();
    GoogleAnalytics.getInstance(this).reportActivityStart(this);
  }
  
  protected void onStop()
  {
    GoogleAnalytics.getInstance(this).reportActivityStop(this);
    super.onStop();
  }
  
  public void openDialog()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903070);
    localDialog.setCancelable(false);
    localDialog.show();
    ((Button)localDialog.findViewById(2131493019)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    ((Button)localDialog.findViewById(2131493018)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ActivityMainOption.this.settings.setRatingFlag(true);
        try
        {
          ActivityMainOption.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.appzcloud.addmusictovideo")));
          localDialog.dismiss();
          return;
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          for (;;)
          {
            ActivityMainOption.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.appzcloud.addmusictovideo")));
          }
        }
      }
    });
    ((Button)localDialog.findViewById(2131493020)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
  }
  
  public void openDialogprogress()
  {
    startActivity(new Intent(this, FirstScreenActivity.class));
  }
  
  public void preloadExitAds()
  {
    if ((!this.settings.getPurchaseFlag()) && (isOnline()) && (this.settings.get_dialog_exitApp_native_ads()) && (ad != null))
    {
      adViewExitLayout = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130903052, null);
      RelativeLayout localRelativeLayout = (RelativeLayout)adViewExitLayout.findViewById(2131492931);
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams.addRule(11);
      localLayoutParams.leftMargin = (getResources().getDisplayMetrics().widthPixels - convertToDp(40));
      if ((ad != null) && (ad.getAdTitle() != null) && (ad.getAdCallToAction() != null))
      {
        inflateAdExitPopup(ad, adViewExitLayout, this);
        if (0 == 0) {
          localRelativeLayout.addView(new AdChoicesView(this, ad, true), localLayoutParams);
        }
      }
    }
  }
  
  public void setRateInappPlan()
  {
    if ((!this.settings.getRatingFlag()) && (this.settings.get_Rating_counter() == this.settings.getRatingParsecount()))
    {
      this.settings.set_Rating_counter(0);
      openDialog();
    }
    for (;;)
    {
      if (this.settings.getVideoCount() >= this.settings.getIappParsecount()) {
        this.settings.set_InappCounter(0);
      }
      return;
      if ((!this.settings.getPurchaseFlag()) && (this.settings.get_InappCounter() >= this.settings.getIappParsecount())) {
        startActivity(new Intent(this, InAppActivity.class));
      }
    }
  }
  
  public void videoProblemAlert()
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setTitle("Info");
    ((AlertDialog.Builder)localObject).setMessage("It seems that there is some issue with this Video, Please try another Video.");
    ((AlertDialog.Builder)localObject).setCancelable(false);
    ((AlertDialog.Builder)localObject).setPositiveButton("OK", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MyTempResources.isVideoConversionProgress = false;
        MyTempResources.isVideoDeleteConversionProgress = false;
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setIcon(2130837639);
    ((AlertDialog)localObject).show();
  }
}
