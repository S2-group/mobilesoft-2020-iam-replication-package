package com.earthcam.webcams.activities.live_camera;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cocosw.bottomsheet.BottomSheet.Builder;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.earthcam.core.objects.CameraDetailsObject;
import com.earthcam.core.objects.CameraObject;
import com.earthcam.core.objects.HofImage;
import com.earthcam.core.utils.ChromeCastUtil;
import com.earthcam.core.utils.ContextProvider;
import com.earthcam.core.utils.FormatUtil;
import com.earthcam.core.utils.UiTimer.UiTimerImpl;
import com.earthcam.webcams.Webcams;
import com.earthcam.webcams.WebcamsPreferences;
import com.earthcam.webcams.activities.CameraDetails;
import com.earthcam.webcams.activities.WebCamsMainActivity;
import com.earthcam.webcams.activities.hof_sharing.HofSharingActivity.IntentBuilder;
import com.earthcam.webcams.adapters.HOFGridAdapter;
import com.earthcam.webcams.chromecast.ChromeCastController;
import com.earthcam.webcams.chromecast.ChromeCastController.ChromeCastListener;
import com.earthcam.webcams.chromecast.ChromeCastControllerImpl;
import com.earthcam.webcams.database.DBManager;
import com.earthcam.webcams.dialogs.ErrorDialogFragment;
import com.earthcam.webcams.fragments.CameraViewFragment;
import com.earthcam.webcams.network.camera_details_repo.CameraDetailsRepoImpl;
import com.earthcam.webcams.network.hof_repo.HofImagesRepo;
import com.earthcam.webcams.network.hof_repo.HofImagesRepoImpl;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LiveCamera
  extends AppCompatActivity
  implements LiveCameraView, View.OnClickListener
{
  private static CameraDetailsObject camInfo;
  public static ArrayList<HofImage> hofImages = new ArrayList();
  final int REQUEST_CODE_HOF_TIMELINE = 500;
  final int REQUEST_WRITE_STORAGE = 10;
  private ActionBar actionBar;
  private CameraObject cam = new CameraObject();
  private AsyncHttpClient client = new AsyncHttpClient();
  private FrameLayout container;
  private DBManager dbManager;
  private DisplayMetrics displayMetrics;
  private ImageView displayToggle;
  private CameraViewFragment fragment;
  private boolean gridToggle = false;
  private boolean hasLiked = false;
  private ImageView heartIcon;
  private HOFGridAdapter hofGridAdapter;
  private PorterDuffColorFilter likedColor = new PorterDuffColorFilter(Color.rgb(29, 99, 162), PorterDuff.Mode.SRC_ATOP);
  private TextView likes;
  private GridView mGridView;
  private LinearLayout main_layout;
  public LinearLayout overLayActions;
  private WebcamsPreferences preferences;
  private LiveCameraPresenter presenter;
  private ProgressBar progressBarLoader;
  private ProgressDialog progressDialog;
  private ImageView shareIcon;
  private SwipeRefreshLayout swipeRefreshLayout;
  private TextView time;
  private final Handler uiHandler = new Handler();
  private PorterDuffColorFilter unlikedColor = new PorterDuffColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  private TextView weather;
  
  public LiveCamera() {}
  
  private void dismissProgressDialog()
  {
    try
    {
      while (this.progressDialog != null)
      {
        this.progressDialog.dismiss();
        this.progressDialog = null;
      }
      return;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
  }
  
  private void displayErrorMessage(final String paramString)
  {
    this.uiHandler.post(new Runnable()
    {
      public void run()
      {
        ErrorDialogFragment localErrorDialogFragment = new ErrorDialogFragment();
        Bundle localBundle = new Bundle();
        localBundle.putString("error", paramString);
        localErrorDialogFragment.setArguments(localBundle);
        localErrorDialogFragment.show(LiveCamera.this.getSupportFragmentManager(), "error");
      }
    });
  }
  
  private void getData()
  {
    Bundle localBundle = getIntent().getExtras();
    if (localBundle != null)
    {
      this.cam.setCamId(localBundle.getString(Webcams.CamId));
      this.cam.setTitle(localBundle.getString(Webcams.CameraName));
      this.cam.setLatitude(localBundle.getString(Webcams.Latitude));
      this.cam.setLongitude(localBundle.getString(Webcams.Longitude));
      this.cam.setType(localBundle.getString(Webcams.CamType));
      this.cam.setBeautyShot(localBundle.getString(Webcams.Thumbnail));
    }
  }
  
  private void handleHofClick(String paramString)
  {
    startActivity(new HofSharingActivity.IntentBuilder().setContext(this).setUrl(paramString).setGroup(camInfo.getGroup()).setHofPath(camInfo.getHofPath()).setHofLabel(camInfo.getHofLabel()).setTimeZoneName(camInfo.getTimeZone()).setCameraType("streaming").build());
  }
  
  @TargetApi(19)
  private void hideSystemUI()
  {
    getWindow().getDecorView().setSystemUiVisibility(5894);
  }
  
  private void initChromeCast()
  {
    ChromeCastControllerImpl.getInstance().addChromeCastListener(new ChromeCastController.ChromeCastListener()
    {
      public void onCastConnected()
      {
        if (LiveCamera.this.shareIcon != null) {
          LiveCamera.this.shareIcon.setVisibility(8);
        }
      }
      
      public void onCastDisconnected()
      {
        if (LiveCamera.this.shareIcon != null) {
          LiveCamera.this.shareIcon.setVisibility(0);
        }
      }
      
      public void onStreamFailed() {}
      
      public void onStreamStarted() {}
      
      public void onStreamSuccess() {}
    });
  }
  
  private void initViews()
  {
    this.actionBar.setTitle(this.cam.getTitle());
    this.actionBar.setDisplayHomeAsUpEnabled(true);
    this.displayMetrics = getResources().getDisplayMetrics();
    this.main_layout = ((LinearLayout)findViewById(2131296569));
    this.mGridView = ((GridView)findViewById(2131296465));
    this.progressBarLoader = ((ProgressBar)findViewById(2131296576));
    this.container = ((FrameLayout)findViewById(2131296377));
    this.container.setOnClickListener(this);
    if (this.displayMetrics.widthPixels > this.displayMetrics.heightPixels) {
      this.container.getLayoutParams().height = ((int)(this.displayMetrics.heightPixels * 0.56D));
    } else {
      this.container.getLayoutParams().height = ((int)(this.displayMetrics.widthPixels * 0.56D));
    }
    this.overLayActions = ((LinearLayout)findViewById(2131296562));
    this.overLayActions.setVisibility(4);
    this.weather = ((TextView)findViewById(2131296749));
    this.time = ((TextView)findViewById(2131296678));
    this.likes = ((TextView)findViewById(2131296501));
    this.heartIcon = ((ImageView)findViewById(2131296469));
    this.heartIcon.setColorFilter(this.unlikedColor);
    this.swipeRefreshLayout = ((SwipeRefreshLayout)findViewById(2131296647));
    this.swipeRefreshLayout.setOnRefreshListener(new LiveCamera..Lambda.0(this));
    findViewById(2131296394).setOnClickListener(this);
    this.likes.setOnClickListener(this);
    this.heartIcon.setOnClickListener(this);
    this.shareIcon = ((ImageView)findViewById(2131296621));
    this.shareIcon.setOnClickListener(this);
    this.displayToggle = ((ImageView)findViewById(2131296683));
    ImageView localImageView = this.displayToggle;
    Resources localResources = getResources();
    int i;
    if (isTablet(this)) {
      i = 2131231261;
    } else {
      i = 2131231375;
    }
    localImageView.setImageDrawable(localResources.getDrawable(i));
    this.displayToggle.setOnClickListener(this);
    this.progressDialog = new ProgressDialog(this);
    this.progressDialog.setMessage("Loading...");
    this.progressDialog.show();
  }
  
  public static boolean isImmersiveModeCapable()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  private boolean isInstalled(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledPackages(8).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.contentEquals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  private void logSnapshot(String paramString)
  {
    Answers.getInstance().logCustom((CustomEvent)((CustomEvent)((CustomEvent)new CustomEvent("Took A Snapshot").putCustomAttribute("Camera Name", this.cam.getTitle())).putCustomAttribute("Share Type", paramString)).putCustomAttribute("Platform", "Android"));
  }
  
  private void makeBottomSheetBuilder(String paramString)
  {
    new BottomSheet.Builder(this).sheet(2131558407).listener(new LiveCamera..Lambda.2(this, paramString)).show();
  }
  
  private void requestPermission()
  {
    if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)
    {
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 10);
      return;
    }
    this.fragment.getBitmap();
  }
  
  private void setLiveCamera()
  {
    if (this.fragment == null)
    {
      Bundle localBundle = new Bundle();
      localBundle.putString(Webcams.CamId, this.cam.getCamId());
      localBundle.putString(Webcams.CameraName, this.cam.getTitle());
      this.fragment = new CameraViewFragment();
      this.fragment.setArguments(localBundle);
    }
    this.fragment.setImageDownloadedListener(new LiveCamera..Lambda.1(this));
    try
    {
      getSupportFragmentManager().beginTransaction().replace(2131296377, this.fragment, "Camera").commit();
      return;
    }
    catch (Exception localException)
    {
      Crashlytics.logException(localException);
    }
  }
  
  private void setOverlayPadding(int paramInt)
  {
    if (isImmersiveModeCapable())
    {
      this.overLayActions.setPadding(10, 0, paramInt + 20, paramInt);
      return;
    }
    this.overLayActions.setPadding(10, 0, 10, paramInt);
  }
  
  @TargetApi(19)
  private void showSystemUI()
  {
    getWindow().getDecorView().setSystemUiVisibility(1792);
  }
  
  private void updateLikes()
  {
    String str = Settings.Secure.getString(getContentResolver(), "android_id");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://www.earthcam.com/mobile/appfiles/common/rateCam.php?d=");
    localStringBuilder.append(str);
    localStringBuilder.append("&id=");
    localStringBuilder.append(this.cam.getCamId());
    localStringBuilder.append("&rating=10&p=Webcams&device=android");
    str = localStringBuilder.toString();
    this.client.get(str, new JsonHttpResponseHandler() {});
  }
  
  public void bringOverlayToFront()
  {
    this.overLayActions.bringToFront();
  }
  
  public void cameraFailure(String paramString)
  {
    dismissProgressDialog();
    displayErrorMessage(paramString);
  }
  
  public void doneGettingHofImages()
  {
    if (this.swipeRefreshLayout.isRefreshing())
    {
      this.swipeRefreshLayout.setRefreshing(false);
      return;
    }
    dismissProgressDialog();
  }
  
  public void getCameraData_Success(CameraDetailsObject paramCameraDetailsObject)
  {
    camInfo = paramCameraDetailsObject;
    setLiveCamera();
    int i = camInfo.getLikes();
    if (this.dbManager.isLike(this.cam.getTitle().replaceAll("'", "'")))
    {
      this.hasLiked = true;
      this.heartIcon.setColorFilter(this.likedColor);
    }
    this.likes.setText(String.format("%s Likes", new Object[] { FormatUtil.likesCount(i) }));
    this.weather.setText(FormatUtil.temp(camInfo.getTemp(), this.preferences.getTempSetting()));
    this.overLayActions.setVisibility(0);
  }
  
  public String getCameraId()
  {
    if (hofImages.isEmpty()) {
      return "";
    }
    return ((HofImage)hofImages.get(hofImages.size() - 1)).getId();
  }
  
  public ContextProvider getContextProvider()
  {
    return null;
  }
  
  public void getDetails()
  {
    Intent localIntent = new Intent(this, CameraDetails.class);
    localIntent.putExtra("id", this.cam.getCamId());
    localIntent.putExtra("name", this.cam.getTitle());
    localIntent.putExtra("long", this.cam.getLongitude());
    localIntent.putExtra("lat", this.cam.getLatitude());
    startActivity(localIntent);
  }
  
  public int getHofColumnCount()
  {
    return this.mGridView.getNumColumns();
  }
  
  public HofImagesRepo getHofImageRepo()
  {
    if ((camInfo != null) && (!camInfo.isEmpty())) {
      return HofImagesRepoImpl.createHofImagesRepoImpl(this.client, camInfo.getHofLabel(), this);
    }
    return null;
  }
  
  public void getHofImages_Success()
  {
    this.hofGridAdapter = new HOFGridAdapter(hofImages, this);
    this.mGridView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        LiveCamera.this.presenter.onGridViewScroll(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
      }
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        LiveCamera.this.presenter.onGridViewScrollStateChanged();
      }
    });
    this.mGridView.setOnItemClickListener(new LiveCamera..Lambda.3(this));
    this.mGridView.setAdapter(this.hofGridAdapter);
  }
  
  public void getMoreImages_Success(List<HofImage> paramList)
  {
    hofImages.addAll(paramList);
    this.hofGridAdapter.notifyDataSetChanged();
  }
  
  public boolean is12HrTime()
  {
    return this.preferences.getTimeSetting();
  }
  
  public void likeToggled()
  {
    if (camInfo != null)
    {
      int i = camInfo.getLikes();
      if (!this.hasLiked)
      {
        this.dbManager.insertLike(this.cam);
        this.heartIcon.setColorFilter(this.likedColor);
        i += 1;
      }
      else
      {
        this.dbManager.deleteLike(this.cam.getTitle());
        this.heartIcon.setColorFilter(this.unlikedColor);
        i -= 1;
        Intent localIntent = new Intent();
        localIntent.putExtra("camera_id", this.cam.getCamId());
        setResult(-1, localIntent);
      }
      this.hasLiked ^= true;
      this.likes.setText(String.format("%s Likes", new Object[] { FormatUtil.likesCount(i) }));
      updateLikes();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 500) && (paramInt2 == -1) && (paramIntent.getBooleanExtra("closeActivity", false))) {
      finish();
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131296683: 
      this.presenter.onGridToggleClicked();
      return;
    case 2131296621: 
      this.presenter.onShareClicked();
      return;
    case 2131296469: 
    case 2131296501: 
      this.presenter.onLikeToggleClicked();
      return;
    case 2131296394: 
      this.presenter.onDetailsClicked();
      return;
    }
    this.presenter.onLiveStreamClicked();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getData();
    if (this.presenter == null) {
      this.presenter = new LiveCameraPresenterImpl(CameraDetailsRepoImpl.createCameraDetailsRepoImpl(this.cam.getCamId(), this.client), UiTimerImpl.createUiTimerImpl(new Handler()));
    }
    this.presenter.onCreate(this);
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getMenuInflater().inflate(2131558404, paramMenu);
    if (ChromeCastUtil.getCastContext(this) != null) {
      CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), paramMenu, 2131296520);
    }
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    this.client.cancelAllRequests(true);
    hofImages = new ArrayList();
    if (this.presenter != null)
    {
      this.presenter.onDestroy(this);
      this.presenter = null;
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      finish();
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }
  
  protected void onPause()
  {
    super.onPause();
    this.presenter.onPause(this);
  }
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if (paramInt != 10) {
      return;
    }
    if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0)) {
      this.fragment.getBitmap();
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    this.presenter.onResume(this);
  }
  
  public void setTime(String paramString)
  {
    this.time.setText(paramString);
  }
  
  public void setupActivity()
  {
    getWindow().addFlags(16777216);
    getWindow().addFlags(128);
    setContentView(2131492947);
    this.preferences = new WebcamsPreferences(this);
    this.dbManager = new DBManager(this);
    this.preferences.addInteraction();
    WebCamsMainActivity.CURRENT_SESSION_INTERACTIONS += 1;
    this.actionBar = getSupportActionBar();
    this.actionBar.setBackgroundDrawable(getResources().getDrawable(2131230813));
    if (this.cam.getTitle() == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("mCamId: ");
      localStringBuilder.append(this.cam.getCamId());
      localStringBuilder.append(", mType: ");
      localStringBuilder.append(this.cam.getType());
      localStringBuilder.append(", data: ");
      localStringBuilder.append(getIntent().toString());
      Crashlytics.log(1, "LiveCamera.java", localStringBuilder.toString());
    }
    else
    {
      Answers.getInstance().logCustom((CustomEvent)new CustomEvent("Camera Watched").putCustomAttribute("Camera Name", this.cam.getTitle()));
    }
    initViews();
    if (getResources().getConfiguration().orientation == 2)
    {
      this.container.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
      getWindow().addFlags(1024);
      hideSystemUI();
      this.mGridView.setVisibility(4);
      this.main_layout.removeView(this.overLayActions);
      this.overLayActions.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 80));
      this.overLayActions.setBackground(getResources().getDrawable(2131230818));
      if (!this.actionBar.isShowing()) {
        this.overLayActions.setVisibility(4);
      }
      setOverlayPadding((int)(this.displayMetrics.heightPixels * 0.1D));
      this.container.addView(this.overLayActions);
      if (this.overLayActions.getVisibility() == 0) {
        this.overLayActions.setVisibility(8);
      }
    }
    this.actionBar.hide();
    initChromeCast();
  }
  
  public void share()
  {
    if (!camInfo.getCamType().contentEquals("streaming"))
    {
      makeBottomSheetBuilder(camInfo.getLiveImagePath());
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      requestPermission();
      return;
    }
    this.fragment.getBitmap();
  }
  
  public void showProgressBarLoader(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.progressBarLoader.setVisibility(0);
      return;
    }
    this.progressBarLoader.setVisibility(8);
  }
  
  public void toggleActionBar()
  {
    int i = getResources().getConfiguration().orientation;
    if (this.actionBar.isShowing())
    {
      this.actionBar.hide();
      if (i == 2)
      {
        this.overLayActions.setVisibility(4);
        hideSystemUI();
      }
    }
    else
    {
      this.actionBar.show();
      if (i == 2)
      {
        this.overLayActions.setVisibility(0);
        showSystemUI();
      }
    }
  }
  
  public void toggleGrid()
  {
    int j = this.mGridView.getFirstVisiblePosition();
    if (this.gridToggle)
    {
      this.gridToggle = false;
      Object localObject = this.mGridView;
      int i;
      if (isTablet(this)) {
        i = 3;
      } else {
        i = 2;
      }
      ((GridView)localObject).setNumColumns(i);
      this.mGridView.setAdapter(this.hofGridAdapter);
      localObject = this.displayToggle;
      Resources localResources = getResources();
      if (isTablet(this)) {
        i = 2131231261;
      } else {
        i = 2131231375;
      }
      ((ImageView)localObject).setImageDrawable(localResources.getDrawable(i));
      this.mGridView.setSelection(j);
      return;
    }
    this.gridToggle = true;
    this.mGridView.setNumColumns(1);
    this.mGridView.setAdapter(this.hofGridAdapter);
    this.displayToggle.setImageDrawable(getResources().getDrawable(2131231253));
    this.mGridView.setSelection(j);
  }
}
