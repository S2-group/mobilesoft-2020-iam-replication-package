package com.genie9.gallery.UI.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cocosw.bottomsheet.BottomSheet;
import com.genie9.gallery.Entity.FileInfo;
import com.genie9.gallery.Entity.GalleryFilter;
import com.genie9.gallery.Entity.TimelineOperations;
import com.genie9.gallery.Libraries.Material.app.Dialog;
import com.genie9.gallery.Libraries.Material.app.DialogFragment;
import com.genie9.gallery.Libraries.Material.app.SimpleDialog.Builder;
import com.genie9.gallery.Libraries.Material.widget.Button;
import com.genie9.gallery.Loader.FilesGalleryLoader;
import com.genie9.gallery.LockPattern.ConfirmPatternActivity;
import com.genie9.gallery.LockPattern.utils.PatternLockUtils;
import com.genie9.gallery.Provider.DatabaseProvider;
import com.genie9.gallery.Provider.G9SharedPreferencesProvider;
import com.genie9.gallery.Provider.LocalMediaDBHandler;
import com.genie9.gallery.Provider.TagsDBHandler;
import com.genie9.gallery.UI.Adapter.MediaViewerAdapter;
import com.genie9.gallery.UI.Fragment.MediaViewerFrag;
import com.genie9.gallery.UI.MyApplication;
import com.genie9.gallery.Utility.DataStorage;
import com.genie9.gallery.Utility.G9SharedPreferences;
import com.genie9.gallery.Utility.G9Utility;
import com.genie9.gallery.Utility.GSUtilities;
import com.genie9.gallery.Utility.GaTracker;
import com.genie9.gallery.Utility.IntentUtil;
import com.genie9.gallery.Utility.InterstitialAdUtility;
import com.genie9.gallery.Utility.LockUtil;
import com.genie9.gallery.Utility.ThemeUtil;
import com.genie9.gallery.Utility.UIUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.DiscCacheUtil;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class BaseActivity
  extends ActionBarActivity
{
  public static boolean AppWentToBackground = false;
  public static final int REQUEST_CODE_LOCK = 981;
  public static final int REQ_CHANGE_SECURITY_QUESTION = 2;
  public static final int REQ_CREATE_PATTERN = 1;
  public static boolean isForgotPatternMode;
  public static boolean isToolbarShow;
  public static boolean keepBottomSheet;
  public static boolean returningFromShare;
  public static boolean shouldActivateLockPattern = true;
  public static boolean showAds = false;
  public static boolean showMore = false;
  public boolean bIsTablet;
  public boolean bIsTablet7;
  public boolean bIsTabletGeneral;
  public BottomSheet bottomSheet;
  private LinearLayout deleteOption;
  private LinearLayout detailsOptoin;
  private LinearLayout downloadOption;
  private LinearLayout editOption;
  private LinearLayout encryptOption;
  private LinearLayout favOption;
  private LinearLayout firstLayout;
  public MediaViewerFrag fragmentSelected;
  private View imgOptionLayout;
  public MediaViewerAdapter mAdapter;
  public MyApplication mApplication;
  public Toolbar mBottomToolbar;
  protected BaseActivity mContext;
  public FileInfo mCurrentFileInfo;
  public int mCurrentPosition;
  public DataStorage mDataStorage;
  public DatabaseProvider mDatabaseProvider;
  public DisplayImageOptions mDisplayImageOptions;
  public ImageLoader mImageLoader;
  public LayoutInflater mInflater;
  public DialogFragment mInfoDialog;
  public InterstitialAdUtility mInterstitialAdUtility;
  public boolean mIsUserMakeAction = false;
  public LocalMediaDBHandler mLocalMediaDBHandler;
  private String mScreenName;
  public G9SharedPreferences mSharedPreferences;
  public G9SharedPreferencesProvider mSharedPreferencesProvider;
  public TagsDBHandler mTagsDBHandler;
  public TimelineOperations mTimelineOperations;
  public Toolbar mToolbar;
  public GaTracker mTracker;
  public G9Utility mUtility;
  private LinearLayout moreOption;
  private ImageView moreOptionImg;
  private TextView moreOptionText;
  private HashMap<Integer, Boolean> pendingCouldHighlited;
  private LinearLayout printOption;
  private LinearLayout rotateLeftOption;
  private LinearLayout rotateRightOption;
  private LinearLayout secondLayout;
  private LinearLayout shareOption;
  private LinearLayout tagOption;
  private LinearLayout thirdLayout;
  private LinearLayout useAsOption;
  private LinearLayout vDeleteOption;
  private LinearLayout vDetailsOptoin;
  private LinearLayout vDownloadOption;
  private LinearLayout vEncryptOption;
  private LinearLayout vFavOption;
  private LinearLayout vFirstLayout;
  private LinearLayout vMoreOption;
  private ImageView vMoreOptionImg;
  private TextView vMoreOptionText;
  private LinearLayout vSecondLayout;
  private LinearLayout vShareOption;
  private LinearLayout vTagOption;
  private View videoOptionLayout;
  
  static
  {
    isToolbarShow = true;
    isForgotPatternMode = false;
    returningFromShare = false;
  }
  
  public BaseActivity() {}
  
  private void AddRemoveFavourite()
  {
    boolean bool2 = true;
    boolean bool3 = this.mCurrentFileInfo.isHighlited();
    this.mTimelineOperations.highlight();
    Object localObject = this.mCurrentFileInfo;
    int i;
    if (!bool3)
    {
      bool1 = true;
      ((FileInfo)localObject).setIsHighlited(bool1);
      this.mIsUserMakeAction = true;
      if (this.mContext.mApplication.isTabCloud())
      {
        localObject = this.pendingCouldHighlited;
        i = this.mCurrentFileInfo.getFileID();
        if (bool3) {
          break label100;
        }
      }
    }
    label100:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      ((HashMap)localObject).put(Integer.valueOf(i), Boolean.valueOf(bool1));
      isFavourite();
      return;
      bool1 = false;
      break;
    }
  }
  
  private void HideAndEncrypt()
  {
    if (isPackageInstalled("com.genie9.glock", this.mContext))
    {
      new File((String)GSUtilities.getStoragePaths().get(0) + "/G Cloud/disabled.glock");
      Intent localIntent = getPackageManager().getLaunchIntentForPackage("com.genie9.glock");
      if (localIntent != null)
      {
        localIntent.addFlags(268435456);
        startActivity(localIntent);
      }
      return;
    }
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.genie9.glock")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.genie9.glock")));
    }
  }
  
  private void RotateToLeft()
  {
    startTrackEvent(2131165463);
    int j = this.mCurrentFileInfo.getRotation() - 90;
    int i = j;
    if (j < 0) {
      i = 270;
    }
    this.mCurrentFileInfo.setRotation(i);
    this.mLocalMediaDBHandler.updateFilesRotationValue(this.mCurrentFileInfo);
    if (rotateImage(false))
    {
      HashMap localHashMap = this.mAdapter.getmFilesRotationMap();
      localHashMap.put(Integer.valueOf(this.mCurrentFileInfo.getFileID()), Integer.valueOf(this.mCurrentFileInfo.getRotation()));
      this.mAdapter.setmFilesRotationMap(localHashMap);
    }
  }
  
  private void RotateToRight()
  {
    startTrackEvent(2131165464);
    int j = this.mCurrentFileInfo.getRotation() + 90;
    int i = j;
    if (j > 270) {
      i = 0;
    }
    this.mCurrentFileInfo.setRotation(i);
    this.mLocalMediaDBHandler.updateFilesRotationValue(this.mCurrentFileInfo);
    if (rotateImage(true))
    {
      HashMap localHashMap = this.mAdapter.getmFilesRotationMap();
      localHashMap.put(Integer.valueOf(this.mCurrentFileInfo.getFileID()), Integer.valueOf(this.mCurrentFileInfo.getRotation()));
      this.mAdapter.setmFilesRotationMap(localHashMap);
    }
  }
  
  private void UseImageAs()
  {
    Uri localUri = Uri.fromFile(new File(this.mCurrentFileInfo.getFilePath()));
    Intent localIntent = new Intent("android.intent.action.ATTACH_DATA");
    localIntent.setDataAndType(localUri, "image/*");
    this.mContext.startActivity(Intent.createChooser(localIntent, getString(2131165433)));
  }
  
  private void editMediaFile()
  {
    Uri localUri = Uri.parse(this.mCurrentFileInfo.getLargeThumbURL());
    if (this.mContext.mApplication.isTabCloud()) {
      localUri = Uri.fromFile(DiscCacheUtil.findInCache(this.mCurrentFileInfo.getLargeThumbURL(), this.mImageLoader.getDiscCache()));
    }
    Intent localIntent = new Intent("android.intent.action.EDIT");
    localIntent.setDataAndType(localUri, "image/*");
    this.mContext.startActivity(Intent.createChooser(localIntent, null));
  }
  
  private Point getScreenDimensions()
  {
    Display localDisplay = this.mContext.getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    return localPoint;
  }
  
  private void isFavourite()
  {
    ImageView localImageView = (ImageView)findViewById(2131624322);
    if (this.mCurrentFileInfo.isVideo()) {
      localImageView = (ImageView)findViewById(2131624343);
    }
    boolean bool2 = this.mCurrentFileInfo.isHighlited();
    boolean bool1 = bool2;
    if (this.mContext.mApplication.isTabCloud())
    {
      bool1 = bool2;
      if (this.pendingCouldHighlited.containsKey(Integer.valueOf(this.mCurrentFileInfo.getFileID()))) {
        bool1 = ((Boolean)this.pendingCouldHighlited.get(Integer.valueOf(this.mCurrentFileInfo.getFileID()))).booleanValue();
      }
    }
    if (bool1)
    {
      localImageView.setImageDrawable(UIUtil.getDrawable(this.mContext, 2130837668, 2130772610));
      return;
    }
    localImageView.setImageResource(2130837705);
  }
  
  public static void minimizeApp(Context paramContext)
  {
    shouldActivateLockPattern = true;
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  private void shareMediaFile(FileInfo paramFileInfo)
  {
    try
    {
      paramFileInfo = IntentUtil.getShareIntent(this.mContext, paramFileInfo, this.mImageLoader);
      try
      {
        this.mContext.startActivity(Intent.createChooser(paramFileInfo, getResources().getString(2131165248)));
        return;
      }
      catch (ActivityNotFoundException paramFileInfo)
      {
        paramFileInfo.printStackTrace();
        return;
      }
      return;
    }
    catch (Exception paramFileInfo)
    {
      Toast.makeText(this.mContext.getApplicationContext(), this.mContext.getResources().getString(2131165319), 0).show();
    }
  }
  
  public boolean CheckPackageExistance(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public void hideToolbar()
  {
    if (showMore)
    {
      if ((this.vSecondLayout != null) && (this.vSecondLayout.getVisibility() == 0) && (!this.bIsTabletGeneral))
      {
        this.vSecondLayout.setVisibility(8);
        if (!this.mUtility.isTabletInGeneral()) {
          this.vMoreOptionImg.setImageResource(2130837683);
        }
        this.vMoreOptionText.setText(2131165255);
        showMore = false;
        return;
      }
      if ((this.secondLayout != null) && (this.secondLayout.getVisibility() == 0))
      {
        this.secondLayout.setVisibility(8);
        this.thirdLayout.setVisibility(8);
        this.moreOptionImg.setImageResource(2130837683);
        this.moreOptionText.setText(2131165255);
        showMore = false;
        return;
      }
    }
    this.mToolbar.animate().translationY(-this.mToolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
    this.mBottomToolbar.animate().translationY(this.mBottomToolbar.getBottom()).setInterpolator(new AccelerateInterpolator()).start();
    isToolbarShow = false;
  }
  
  public void imageBottomOptions()
  {
    this.secondLayout = ((LinearLayout)findViewById(2131624327));
    this.firstLayout = ((LinearLayout)findViewById(2131624319));
    this.thirdLayout = ((LinearLayout)findViewById(2131624334));
    if (!showMore)
    {
      this.secondLayout.setVisibility(8);
      this.thirdLayout.setVisibility(8);
    }
    this.moreOption = ((LinearLayout)findViewById(2131624324));
    this.moreOptionImg = ((ImageView)findViewById(2131624325));
    this.moreOptionText = ((TextView)findViewById(2131624326));
    this.moreOption.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (BaseActivity.showMore)
        {
          BaseActivity.this.secondLayout.setVisibility(8);
          BaseActivity.this.thirdLayout.setVisibility(8);
          BaseActivity.this.moreOptionImg.setImageResource(2130837683);
          BaseActivity.this.moreOptionText.setText(2131165255);
          BaseActivity.showMore = false;
          return;
        }
        BaseActivity.this.secondLayout.setVisibility(0);
        BaseActivity.this.thirdLayout.setVisibility(0);
        BaseActivity.this.secondLayout.startLayoutAnimation();
        BaseActivity.this.thirdLayout.startLayoutAnimation();
        BaseActivity.this.moreOptionImg.setImageResource(2130837674);
        BaseActivity.this.moreOptionText.setText(2131165321);
        BaseActivity.showMore = true;
      }
    });
    this.shareOption = ((LinearLayout)findViewById(2131624320));
    this.shareOption.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.shareMediaFile(BaseActivity.this.mCurrentFileInfo);
      }
    });
    this.favOption = ((LinearLayout)findViewById(2131624321));
    if ((FilesGalleryLoader.isIOSDevice) || (!this.mContext.mUtility.isMainDevice())) {
      this.favOption.setVisibility(8);
    }
    this.favOption.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.AddRemoveFavourite();
      }
    });
    this.deleteOption = ((LinearLayout)findViewById(2131624323));
    this.deleteOption.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.mTimelineOperations.delete();
        BaseActivity.this.mIsUserMakeAction = true;
      }
    });
    this.tagOption = ((LinearLayout)findViewById(2131624328));
    this.tagOption.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.mTimelineOperations.tag();
        BaseActivity.this.mIsUserMakeAction = true;
      }
    });
    this.encryptOption = ((LinearLayout)findViewById(2131624331));
    if (this.mContext.mApplication.isTabCloud())
    {
      this.encryptOption.setVisibility(8);
      this.detailsOptoin = ((LinearLayout)findViewById(2131624339));
      this.detailsOptoin.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.showMore = false;
          BaseActivity.this.hideToolbar();
          BaseActivity.showMore = true;
          BaseActivity.this.startTrackEvent(2131165455);
          BaseActivity.this.showLocalImageInfo();
        }
      });
      this.useAsOption = ((LinearLayout)findViewById(2131624335));
      if (!this.mContext.mApplication.isTabCloud()) {
        break label560;
      }
      this.useAsOption.setVisibility(8);
      label368:
      this.editOption = ((LinearLayout)findViewById(2131624336));
      if (!this.mContext.mApplication.isTabCloud()) {
        break label578;
      }
      this.editOption.setVisibility(8);
    }
    for (;;)
    {
      this.rotateLeftOption = ((LinearLayout)findViewById(2131624332));
      this.rotateLeftOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.RotateToLeft();
        }
      });
      this.rotateRightOption = ((LinearLayout)findViewById(2131624333));
      this.rotateRightOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.RotateToRight();
        }
      });
      this.printOption = ((LinearLayout)findViewById(2131624338));
      this.printOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.hideToolbar();
          BaseActivity.this.mTimelineOperations.printImg(BaseActivity.this.mCurrentFileInfo);
        }
      });
      this.downloadOption = ((LinearLayout)findViewById(2131624329));
      if (this.mContext.mApplication.isTabCloud()) {
        this.downloadOption.setVisibility(0);
      }
      this.downloadOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.showMore = false;
          BaseActivity.this.hideToolbar();
          Toast.makeText(BaseActivity.this.mContext.getApplicationContext(), BaseActivity.this.mContext.getResources().getString(2131165347), 0).show();
          BaseActivity.this.mTimelineOperations.restore();
          BaseActivity.this.mIsUserMakeAction = true;
        }
      });
      return;
      this.encryptOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.HideAndEncrypt();
        }
      });
      break;
      label560:
      this.useAsOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.UseImageAs();
        }
      });
      break label368;
      label578:
      this.editOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.editMediaFile();
        }
      });
    }
  }
  
  public boolean isPackageInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public boolean isToolbarShown()
  {
    return isToolbarShow;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    setTheme(ThemeUtil.sThemeId);
    super.onCreate(paramBundle);
    this.mContext = this;
    this.mInterstitialAdUtility = new InterstitialAdUtility(this.mContext);
    this.mInterstitialAdUtility.publishAds();
    this.mApplication = ((MyApplication)getApplication());
    this.mUtility = G9Utility.getInstance(this.mContext);
    this.mImageLoader = this.mUtility.getImageLoader();
    this.mDisplayImageOptions = new DisplayImageOptions.Builder().cacheOnDisc(true).cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY_STRETCHED).bitmapConfig(Bitmap.Config.ARGB_8888).build();
    this.bIsTablet = this.mUtility.bIsTablet();
    this.bIsTablet7 = this.mUtility.isTablet7Inch();
    this.bIsTabletGeneral = this.mUtility.isTabletInGeneral();
    this.mDatabaseProvider = DatabaseProvider.getInstance(this.mContext);
    this.mTracker = new GaTracker(this.mContext);
    this.mLocalMediaDBHandler = new LocalMediaDBHandler(this.mContext);
    this.mTagsDBHandler = new TagsDBHandler(this.mContext);
    this.pendingCouldHighlited = new HashMap();
    this.mDataStorage = DataStorage.getInstance(this.mContext);
    this.mInflater = LayoutInflater.from(this.mContext);
    this.mSharedPreferences = G9SharedPreferences.getInstance(this.mContext);
    this.mSharedPreferencesProvider = G9SharedPreferencesProvider.getInstance(this.mContext);
    setPortraitOrientationForNotTablet();
  }
  
  protected void onPause()
  {
    super.onPause();
    new WebView(this.mContext).pauseTimers();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mContext = this;
    if (this.mInterstitialAdUtility == null)
    {
      this.mInterstitialAdUtility = new InterstitialAdUtility(this.mContext);
      this.mInterstitialAdUtility.publishAds();
    }
    G9SharedPreferences localG9SharedPreferences = G9SharedPreferences.getInstance(this.mContext);
    String str = localG9SharedPreferences.GetStringPreferences("lock_pattern_error", "");
    boolean bool = localG9SharedPreferences.GetBooleanPreferences("new_user", true);
    if ((!str.equals("")) && (!bool))
    {
      Toast.makeText(this.mContext, 2131165324, 1).show();
      localG9SharedPreferences.setStringPreferences("lock_pattern_error", "");
    }
    if (LockUtil.isEnable())
    {
      AppWentToBackground = false;
      if (returningFromShare)
      {
        returningFromShare = false;
        shouldActivateLockPattern = false;
      }
      if ((shouldActivateLockPattern) && (LockUtil.isEnabledAndEmpty()) && (PatternLockUtils.hasPattern(this.mContext)))
      {
        shouldActivateLockPattern = false;
        startActivity(new Intent(this.mContext, ConfirmPatternActivity.class));
      }
    }
    new WebView(this.mContext).resumeTimers();
  }
  
  public void refreshBottomBar()
  {
    if (this.mCurrentFileInfo == null) {
      return;
    }
    if ((this.mContext.mApplication.isTabLocal()) && (!new File(this.mCurrentFileInfo.getFilePath()).exists()))
    {
      Intent localIntent = new Intent();
      localIntent.putExtra("user_make_action", this.mIsUserMakeAction);
      localIntent.putExtra("item_position", this.mCurrentPosition);
      setResult(-1, localIntent);
      showMore = false;
      finish();
    }
    if (this.mCurrentFileInfo.isPhoto())
    {
      this.mTimelineOperations.setImageSelected(this.mCurrentFileInfo);
      this.imgOptionLayout = findViewById(2131624143);
      this.videoOptionLayout = findViewById(2131624144);
      if (!this.mCurrentFileInfo.isVideo()) {
        break label190;
      }
      this.imgOptionLayout.setVisibility(8);
      this.videoOptionLayout.setVisibility(0);
      videoBottomOptions();
    }
    for (;;)
    {
      isFavourite();
      return;
      if (!this.mCurrentFileInfo.isVideo()) {
        break;
      }
      this.mTimelineOperations.setVideoSelected(this.mCurrentFileInfo);
      break;
      label190:
      if (this.mCurrentFileInfo.isPhoto())
      {
        this.imgOptionLayout.setVisibility(0);
        this.videoOptionLayout.setVisibility(8);
        imageBottomOptions();
      }
    }
  }
  
  public boolean rotateImage(boolean paramBoolean)
  {
    if (this.mCurrentFileInfo.isPhoto()) {
      return this.mAdapter.getFragmentSelected().rotateImage(paramBoolean);
    }
    return false;
  }
  
  protected void setBackForToolbar()
  {
    this.mToolbar.setNavigationIcon(2130837654);
    this.mToolbar.setNavigationOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BaseActivity.this.onBackPressed();
      }
    });
  }
  
  protected void setBottomToolbarTitle(int paramInt)
  {
    setToolbarTitle("");
  }
  
  protected void setBottomToolbarTitle(String paramString)
  {
    this.mToolbar.postDelayed(new Runnable()
    {
      public void run()
      {
        BaseActivity.this.mBottomToolbar.setTitle("");
      }
    }, 100L);
  }
  
  protected void setPortraitOrientationForNotTablet()
  {
    if (this.mUtility.isTabletInGeneral()) {
      return;
    }
    setRequestedOrientation(1);
  }
  
  protected void setToolbarTitle(int paramInt)
  {
    setToolbarTitle("");
  }
  
  protected void setToolbarTitle(String paramString)
  {
    this.mToolbar.postDelayed(new Runnable()
    {
      public void run()
      {
        BaseActivity.this.mToolbar.setTitle("");
      }
    }, 10L);
  }
  
  protected void setUpBottomToolbar()
  {
    this.mBottomToolbar = ((Toolbar)findViewById(2131624142));
    setSupportActionBar(this.mBottomToolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    this.mBottomToolbar.setTitleTextColor(getResources().getColor(2131558573));
  }
  
  protected void setUpToolbar()
  {
    this.mToolbar = ((Toolbar)findViewById(2131624121));
    this.mBottomToolbar = ((Toolbar)findViewById(2131624142));
    setSupportActionBar(this.mToolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayShowCustomEnabled(true);
  }
  
  public void showBottomToolbar()
  {
    this.mBottomToolbar.animate().translationY(0.0F).setInterpolator(new DecelerateInterpolator()).start();
  }
  
  public void showLocalImageInfo()
  {
    this.mInfoDialog = DialogFragment.newInstance(new SimpleDialog.Builder(2131427633)
    {
      protected Dialog onBuild(Context paramAnonymousContext, int paramAnonymousInt)
      {
        final Dialog localDialog = new Dialog(BaseActivity.this.mContext, 2131427633);
        localDialog.layoutParams(BaseActivity.this.getScreenDimensions().x, BaseActivity.this.getScreenDimensions().y);
        if (BaseActivity.this.mCurrentFileInfo.isPhoto())
        {
          paramAnonymousContext = BaseActivity.this.mContext.mInflater.inflate(2130903147, null);
          ((TextView)paramAnonymousContext.findViewById(2131624294)).setText(GSUtilities.mFormatType(BaseActivity.this.mCurrentFileInfo.getFilePath()));
          if (BaseActivity.this.mContext.mApplication.isTabLocal())
          {
            ((TextView)paramAnonymousContext.findViewById(2131624299)).setText(String.valueOf(GSUtilities.sFormatWidthHeight(BaseActivity.this.mCurrentFileInfo.getFilePath(), "width")));
            ((TextView)paramAnonymousContext.findViewById(2131624302)).setText(String.valueOf(GSUtilities.sFormatWidthHeight(BaseActivity.this.mCurrentFileInfo.getFilePath(), "height")));
            ((TextView)paramAnonymousContext.findViewById(2131624305)).setText(String.valueOf(BaseActivity.this.mCurrentFileInfo.getRotation()));
          }
        }
        for (;;)
        {
          ((TextView)paramAnonymousContext.findViewById(2131624218)).setText(BaseActivity.this.mCurrentFileInfo.getFileName());
          ((TextView)paramAnonymousContext.findViewById(2131624219)).setText(GSUtilities.sFleTimeToDate(BaseActivity.this.mCurrentFileInfo.getLastDateModified(), 0));
          ((TextView)paramAnonymousContext.findViewById(2131624198)).setText(GSUtilities.sFormatSize(BaseActivity.this.mCurrentFileInfo.getFileSize()));
          if (BaseActivity.this.mContext.mApplication.isTabLocal()) {
            ((TextView)paramAnonymousContext.findViewById(2131624296)).setText(BaseActivity.this.mCurrentFileInfo.getFilePath());
          }
          ((Button)paramAnonymousContext.findViewById(2131624306)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              BaseActivity.showMore = true;
              BaseActivity.this.hideToolbar();
              BaseActivity.this.showToolbar();
              localDialog.dismiss();
            }
          });
          localDialog.contentView(paramAnonymousContext);
          localDialog.cancelable(true);
          localDialog.backgroundColor(2131558423);
          localDialog.positiveAction(BaseActivity.this.mContext.getString(17039379));
          return localDialog;
          ((LinearLayout)paramAnonymousContext.findViewById(2131624295)).setVisibility(8);
          ((LinearLayout)paramAnonymousContext.findViewById(2131624297)).setVisibility(8);
          ((LinearLayout)paramAnonymousContext.findViewById(2131624300)).setVisibility(8);
          ((LinearLayout)paramAnonymousContext.findViewById(2131624303)).setVisibility(8);
          continue;
          paramAnonymousContext = BaseActivity.this.mContext.mInflater.inflate(2130903147, null);
          ((TextView)paramAnonymousContext.findViewById(2131624293)).setVisibility(8);
          ((TextView)paramAnonymousContext.findViewById(2131624298)).setVisibility(8);
          ((TextView)paramAnonymousContext.findViewById(2131624301)).setVisibility(8);
          ((TextView)paramAnonymousContext.findViewById(2131624304)).setVisibility(8);
          ((LinearLayout)paramAnonymousContext.findViewById(2131624295)).setVisibility(8);
        }
      }
    });
    this.mInfoDialog.setCancelable(true);
    this.mInfoDialog.show(this.mContext.getSupportFragmentManager(), null);
  }
  
  public void showToast(int paramInt)
  {
    showToast(getString(paramInt));
  }
  
  public void showToast(int paramInt, boolean paramBoolean)
  {
    showToast(getString(paramInt), paramBoolean);
  }
  
  public void showToast(String paramString)
  {
    showToast(paramString, true);
  }
  
  public void showToast(String paramString, boolean paramBoolean)
  {
    BaseActivity localBaseActivity = this.mContext;
    if (paramBoolean) {}
    for (int i = 0;; i = 1)
    {
      Toast.makeText(localBaseActivity, paramString, i).show();
      return;
    }
  }
  
  public void showToolbar()
  {
    if (isToolbarShown()) {
      return;
    }
    this.mToolbar.animate().translationY(0.0F).setInterpolator(new DecelerateInterpolator()).start();
    this.mBottomToolbar.animate().translationY(0.0F).setInterpolator(new DecelerateInterpolator()).start();
    isToolbarShow = true;
  }
  
  public void startTrack()
  {
    this.mScreenName = this.mApplication.getGalleryFilter().getAnalyticsScreen(this.mContext);
    startTrack(this.mScreenName);
  }
  
  public void startTrack(int paramInt)
  {
    startTrack(getString(paramInt));
  }
  
  public void startTrack(String paramString)
  {
    this.mContext.mTracker.StartScreen(paramString);
  }
  
  public void startTrackEvent(int paramInt)
  {
    startTrackEvent(getString(paramInt));
  }
  
  public void startTrackEvent(int paramInt, boolean paramBoolean)
  {
    startTrackEvent(getString(paramInt), paramBoolean);
  }
  
  public void startTrackEvent(String paramString)
  {
    this.mTracker.ButtonPressed(this.mScreenName, paramString);
  }
  
  public void startTrackEvent(String paramString, boolean paramBoolean)
  {
    this.mTracker.SettingsChecked(this.mScreenName, paramString, paramBoolean);
  }
  
  public void toggleToolbar()
  {
    if (isToolbarShown())
    {
      hideToolbar();
      return;
    }
    showToolbar();
  }
  
  public void videoBottomOptions()
  {
    this.vSecondLayout = ((LinearLayout)findViewById(2131624348));
    this.vFirstLayout = ((LinearLayout)findViewById(2131624340));
    if (!showMore) {
      this.vSecondLayout.setVisibility(8);
    }
    this.vMoreOption = ((LinearLayout)findViewById(2131624345));
    if (!this.bIsTabletGeneral)
    {
      this.vMoreOptionImg = ((ImageView)findViewById(2131624346));
      this.vMoreOptionText = ((TextView)findViewById(2131624347));
      this.vMoreOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (BaseActivity.showMore)
          {
            BaseActivity.this.vSecondLayout.setVisibility(8);
            BaseActivity.this.vMoreOptionImg.setImageResource(2130837683);
            BaseActivity.this.vMoreOptionText.setText(2131165255);
            BaseActivity.showMore = false;
            return;
          }
          BaseActivity.showMore = true;
          BaseActivity.this.vSecondLayout.setVisibility(0);
          BaseActivity.this.vSecondLayout.startLayoutAnimation();
          BaseActivity.this.vMoreOptionImg.setImageResource(2130837674);
          BaseActivity.this.vMoreOptionText.setText(2131165321);
        }
      });
    }
    for (;;)
    {
      this.vShareOption = ((LinearLayout)findViewById(2131624341));
      this.vShareOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.shareMediaFile(BaseActivity.this.mCurrentFileInfo);
        }
      });
      this.vFavOption = ((LinearLayout)findViewById(2131624342));
      if ((FilesGalleryLoader.isIOSDevice) || (!this.mContext.mUtility.isMainDevice())) {
        this.vFavOption.setVisibility(8);
      }
      this.vFavOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.AddRemoveFavourite();
        }
      });
      this.vDeleteOption = ((LinearLayout)findViewById(2131624344));
      this.vDeleteOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.mTimelineOperations.delete();
          BaseActivity.this.mIsUserMakeAction = true;
        }
      });
      this.vTagOption = ((LinearLayout)findViewById(2131624349));
      this.vTagOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.mTimelineOperations.tag();
          BaseActivity.this.mIsUserMakeAction = true;
        }
      });
      this.vEncryptOption = ((LinearLayout)findViewById(2131624350));
      if (this.mContext.mApplication.isTabCloud()) {
        this.vEncryptOption.setVisibility(8);
      }
      this.vEncryptOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.this.HideAndEncrypt();
        }
      });
      this.vDetailsOptoin = ((LinearLayout)findViewById(2131624352));
      this.vDetailsOptoin.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.showMore = false;
          BaseActivity.this.hideToolbar();
          BaseActivity.showMore = true;
          BaseActivity.this.startTrackEvent(2131165455);
          BaseActivity.this.showLocalImageInfo();
        }
      });
      this.vDownloadOption = ((LinearLayout)findViewById(2131624351));
      if (this.mContext.mApplication.isTabCloud()) {
        this.vDownloadOption.setVisibility(0);
      }
      this.vDownloadOption.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          BaseActivity.showMore = false;
          BaseActivity.this.hideToolbar();
          Toast.makeText(BaseActivity.this.mContext.getApplicationContext(), BaseActivity.this.mContext.getResources().getString(2131165347), 0).show();
          BaseActivity.this.mTimelineOperations.restore();
          BaseActivity.this.mIsUserMakeAction = true;
        }
      });
      return;
      this.vMoreOption.setVisibility(8);
    }
  }
}
