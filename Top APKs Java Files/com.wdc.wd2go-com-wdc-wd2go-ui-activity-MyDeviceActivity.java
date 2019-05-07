package com.wdc.wd2go.ui.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Audio.Media;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video.Media;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.SearchView.OnCloseListener;
import android.support.v7.widget.SearchView.OnSuggestionListener;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.lapism.searchview.SearchView.OnOpenCloseListener;
import com.wdc.musicplayer.MusicPlayerActivity;
import com.wdc.wd2go.AsyncLoader;
import com.wdc.wd2go.ResponseException;
import com.wdc.wd2go.UrlConstant;
import com.wdc.wd2go.UrlConstant.JpUrl;
import com.wdc.wd2go.WdFilesApplication;
import com.wdc.wd2go.analytics.WDAnalytics;
import com.wdc.wd2go.autobackup.service.AutoBackupJobSchedulerService;
import com.wdc.wd2go.autobackup.service.AutoBackupService;
import com.wdc.wd2go.chromecast.CastDeviceDialog;
import com.wdc.wd2go.chromecast.CastManager;
import com.wdc.wd2go.core.AbstractProgressBarListener;
import com.wdc.wd2go.core.DatabaseAgent;
import com.wdc.wd2go.core.NetworkManager;
import com.wdc.wd2go.core.ReleasableList;
import com.wdc.wd2go.core.ScreenRefreshListener;
import com.wdc.wd2go.core.WdFileManager;
import com.wdc.wd2go.core.WdFileSystem;
import com.wdc.wd2go.core.impl.DeviceManager;
import com.wdc.wd2go.core.impl.LocalCreateDeviceUserAccountRunnable;
import com.wdc.wd2go.media.MediaFragmentManager;
import com.wdc.wd2go.media.fragment.AbstractFragment;
import com.wdc.wd2go.media.fragment.MusicPlayerFragment;
import com.wdc.wd2go.media.fragment.PhotoTabFragment;
import com.wdc.wd2go.media.fragment.PhotoViewerFragment;
import com.wdc.wd2go.media.model.WdFileMedia;
import com.wdc.wd2go.model.ActionDoingSet;
import com.wdc.wd2go.model.CloudMediaData;
import com.wdc.wd2go.model.DatabaseBean;
import com.wdc.wd2go.model.Device;
import com.wdc.wd2go.model.DeviceType;
import com.wdc.wd2go.model.DeviceUpgradeInfo;
import com.wdc.wd2go.model.MediaList;
import com.wdc.wd2go.model.WdActivity;
import com.wdc.wd2go.model.WdFile;
import com.wdc.wd2go.model.camera.CameraFolders;
import com.wdc.wd2go.model.camera.CameraRecord;
import com.wdc.wd2go.photoviewer.app.PhotoPage;
import com.wdc.wd2go.photoviewer.app.PhotoViewerActivity;
import com.wdc.wd2go.photoviewer.app.StateManager;
import com.wdc.wd2go.push.MyCloudRegistrationIntentService;
import com.wdc.wd2go.service.ScanDeviceService;
import com.wdc.wd2go.ui.activity.share.ShareInfoActivity;
import com.wdc.wd2go.ui.activity.share.SharePrivateActivity.ShareIntentData;
import com.wdc.wd2go.ui.adapter.BreadcrumbListAdapter;
import com.wdc.wd2go.ui.adapter.CloudAdapter;
import com.wdc.wd2go.ui.adapter.DeviceListAdapter;
import com.wdc.wd2go.ui.adapter.MediaBreadcrumbListAdapter;
import com.wdc.wd2go.ui.adapter.ViewPageAdapter;
import com.wdc.wd2go.ui.fragment.FolderListFragment;
import com.wdc.wd2go.ui.fragment.SearchBarFragment;
import com.wdc.wd2go.ui.fragment.SharePrivateFragment;
import com.wdc.wd2go.ui.fragment.SharePrivateFragment.SharePrivateFragmentListener;
import com.wdc.wd2go.ui.fragment.ShareWithMeFragment;
import com.wdc.wd2go.ui.fragment.TitleEditFragment;
import com.wdc.wd2go.ui.fragment.TitleSecondaryFragment;
import com.wdc.wd2go.ui.fragment.ViewFlowFragment;
import com.wdc.wd2go.ui.helper.MyDeviceActivityHelper;
import com.wdc.wd2go.ui.helper.RateAppHelper;
import com.wdc.wd2go.ui.loader.ClearAllWdActivitiesLoader;
import com.wdc.wd2go.ui.loader.CopyFilesLoader;
import com.wdc.wd2go.ui.loader.DeleteDeviceLoader;
import com.wdc.wd2go.ui.loader.DeleteDownloadsLoader;
import com.wdc.wd2go.ui.loader.DeleteFilesLoader;
import com.wdc.wd2go.ui.loader.DeleteWdActivitiesLoader;
import com.wdc.wd2go.ui.loader.DeviceListLoader;
import com.wdc.wd2go.ui.loader.DeviceLoginLoader;
import com.wdc.wd2go.ui.loader.DeviceLoginLoader.OnLoginResultListener;
import com.wdc.wd2go.ui.loader.DownLoadFilesLoader;
import com.wdc.wd2go.ui.loader.EMailFilesLoader;
import com.wdc.wd2go.ui.loader.GetDeviceInfoLoader;
import com.wdc.wd2go.ui.loader.MoveAcrossDevicesLoader;
import com.wdc.wd2go.ui.loader.MoveFilesLoader;
import com.wdc.wd2go.ui.loader.OpenCloudFolderFromCacheLoader;
import com.wdc.wd2go.ui.loader.OpenCloudFolderLoader;
import com.wdc.wd2go.ui.loader.OpenFileLoader;
import com.wdc.wd2go.ui.loader.OpenLocalFolderLoader;
import com.wdc.wd2go.ui.loader.OpenWdActivityLoader;
import com.wdc.wd2go.ui.loader.PushShareMeLoader;
import com.wdc.wd2go.ui.loader.SaveAsLoader;
import com.wdc.wd2go.ui.loader.ShareFileLoader;
import com.wdc.wd2go.ui.loader.UploadFilesLoader;
import com.wdc.wd2go.ui.loader.VerifyOpenedFileLoader;
import com.wdc.wd2go.ui.loader.WdActivityFolderLoader;
import com.wdc.wd2go.ui.loader.WdFileLoader;
import com.wdc.wd2go.ui.loader.avatar.GetCurrentWiFiConnectionLoader;
import com.wdc.wd2go.ui.loader.avatar.GetDateTimeLoader;
import com.wdc.wd2go.ui.thumbs.ThumbnailWorker;
import com.wdc.wd2go.ui.widget.AddItemDialog;
import com.wdc.wd2go.ui.widget.BadgeView;
import com.wdc.wd2go.ui.widget.CustomAppChooser;
import com.wdc.wd2go.ui.widget.CustomViewPager;
import com.wdc.wd2go.ui.widget.DeviceUpgradeDialog;
import com.wdc.wd2go.ui.widget.FileItemView;
import com.wdc.wd2go.ui.widget.FilePropertyDialog;
import com.wdc.wd2go.ui.widget.MoveFileDialog;
import com.wdc.wd2go.ui.widget.MoveFileWindow;
import com.wdc.wd2go.ui.widget.NewFolderDialog;
import com.wdc.wd2go.ui.widget.RenameDialog;
import com.wdc.wd2go.ui.widget.SearchView.SearchFilter;
import com.wdc.wd2go.ui.widget.ShareDialog;
import com.wdc.wd2go.ui.widget.ShareTitleBar;
import com.wdc.wd2go.ui.widget.SlideDrawer;
import com.wdc.wd2go.ui.widget.UploadItemDialog;
import com.wdc.wd2go.ui.widget.WdActivityItemView;
import com.wdc.wd2go.util.AesCryptoUtils;
import com.wdc.wd2go.util.CompareUtils;
import com.wdc.wd2go.util.DialogUtils;
import com.wdc.wd2go.util.FileUtils;
import com.wdc.wd2go.util.Log;
import com.wdc.wd2go.util.MimeTypeUtils;
import com.wdc.wd2go.util.NotificationUtils;
import com.wdc.wd2go.util.PermissionsUtils;
import com.wdc.wd2go.util.StringUtils;
import com.wdc.wd2go.util.ThreadPool;
import java.io.File;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

public class MyDeviceActivity
  extends AbstractActivity
  implements SearchView.OnCloseListener, android.support.v7.widget.SearchView.OnQueryTextListener, SearchView.OnSuggestionListener, View.OnClickListener, ScreenRefreshListener, SharePrivateFragment.SharePrivateFragmentListener
{
  public static final int SELECT_ALL_ITEMS_WARN_LIMIT = 100;
  private static final String tag = Log.getTag(MyDeviceActivity.class);
  public ColorStateList COLORSTATELIST_FONT1;
  public ColorStateList COLORSTATELIST_FONT2;
  public int WHITE_COLOR;
  private long delayTime = 2000L;
  private boolean isEditMode;
  boolean isJumpTo = false;
  boolean isJumpToShareMe = false;
  int lastSelectedMediaIndex = 0;
  private long lastTime = 0L;
  private Activity mActivityToShowDialogOn = null;
  private AddItemDialog mAddFileDialog;
  private AppBarLayout mAppBarLayout;
  private CustomAppChooser mAppChooseDialog;
  private View mBackingCompleteLayout;
  private Animation mBackingUpIn;
  private View mBackingUpLayout;
  private Animation mBackingUpOut;
  private View mBackingUpStatusPanel;
  private BadgeView mBadgeView;
  private BreadcrumbListAdapter mBreadcrumbListAdapter;
  private CastDeviceDialog mCastDialog;
  private CastManager mCastManager;
  private MenuItem mCastMenuItem;
  private CoordinatorLayout mCoordinatorLayout;
  private String mCurrentDeviceFirmwareVersion;
  protected int mCursorWidth = 0;
  private Dialog mDeleteDeviceDialog;
  private Dialog mDeleteDialog;
  private Device mDevice;
  private Dialog mDeviceInfoDialog;
  private Dialog mDeviceVersionDialog;
  private MenuItem mDownloadMenuItem;
  private DrawerLayout mDrawerLayout;
  private TitleEditFragment mEditFragment;
  private FolderListFragment mFileFragment;
  public volatile boolean mFileOpened = false;
  private LinearLayout mFragmentContainer;
  public AtomicBoolean mFromOtherApp = new AtomicBoolean(false);
  private MyDeviceActivityHelper mHelper;
  private Intent mIntent;
  private boolean mIsInPrivateSharing;
  private volatile boolean mIsListBusy = false;
  boolean mIsRefreshing = false;
  int mJumpTo = 0;
  private com.lapism.searchview.SearchView mLapismSearchView;
  private int mLastPageIndex = 0;
  private DeviceLoginLoader.OnLoginResultListener mLoginListener = new DeviceLoginLoader.OnLoginResultListener()
  {
    public void on401Exception(ResponseException paramAnonymousResponseException, boolean paramAnonymousBoolean)
    {
      Runnable local1 = new Runnable()
      {
        public void run()
        {
          MyDeviceActivity.this.getDeviceListLoader().execute(new Boolean[0]);
        }
      };
      if (paramAnonymousBoolean)
      {
        MyDeviceActivity.this.showResponseError(paramAnonymousResponseException, local1);
        return;
      }
      DialogUtils.showDialogForUNAUTHORIZED(MyDeviceActivity.this, Integer.valueOf(2130838050), MyDeviceActivity.this.getString(2131297598), paramAnonymousResponseException.getMessage(), local1);
    }
    
    public void onLoginSuccess(int paramAnonymousInt, String paramAnonymousString, List<Device> paramAnonymousList, ReleasableList<WdFile> paramAnonymousReleasableList)
    {
      boolean bool = true;
      if (paramAnonymousInt == 0)
      {
        MyDeviceActivity.access$2002(MyDeviceActivity.this, paramAnonymousString);
        MyDeviceActivity.access$2202(MyDeviceActivity.this, MyDeviceActivity.this.mWdFileManager.getRestoreDevice());
        if (MyDeviceActivity.this.mDevice.isKorraDevice())
        {
          MyDeviceActivity.this.mDevice.firmwareVersion = MyDeviceActivity.this.mCurrentDeviceFirmwareVersion;
          MimeTypeUtils.addRawMimeType();
          MyDeviceActivity.this.mBreadcrumbListAdapter.clearList();
          MyDeviceActivity.this.mMediaFragmentManager.reSetMediaData(false);
          MyDeviceActivity.this.mViewPager.setCurrentItem(0);
          if ((MyDeviceActivity.this.mDevice.isKorraDevice()) || (MyDeviceActivity.this.mDevice.isAvatarDevice())) {
            new GetDateTimeLoader(MyDeviceActivity.this).execute(new Integer[0]);
          }
          label145:
          MyDeviceActivity.this.mFileFragment.onLoginFinished(paramAnonymousInt, paramAnonymousList, paramAnonymousReleasableList);
          paramAnonymousString = MyDeviceActivity.this;
          if ((MyDeviceActivity.this.mDevice == null) || (MyDeviceActivity.this.mDevice.isSDCard())) {
            break label250;
          }
        }
      }
      for (;;)
      {
        paramAnonymousString.showCastLayout(bool);
        MyDeviceActivity.this.changeOverflowMenuItemVisibility();
        return;
        MimeTypeUtils.removeRawMimeType();
        break;
        if ((paramAnonymousInt != 1) || ("android.intent.action.GET_CONTENT".equals(MyDeviceActivity.this.mIntent.getAction()))) {
          break label145;
        }
        MyDeviceActivity.this.showResponseError(new ResponseException(668));
        break label145;
        label250:
        bool = false;
      }
    }
  };
  private RelativeLayout mMainLayout;
  private MediaBreadcrumbListAdapter mMediaBreadcrumbListAdapter;
  private MediaFragmentManager mMediaFragmentManager;
  private Dialog mMoveFileDialog;
  private WdFileSystem mMoveFileSystem;
  private MoveFileWindow mMoveFileWindow;
  Spinner mNavigationSpinner;
  private volatile boolean mNeedDownLoad = false;
  private NewFolderDialog mNewFolderDialog;
  private MenuItem mOverflowMenuItem;
  private ViewPageAdapter mPageAdapter;
  private int mPageIndex = 0;
  private AbstractProgressBarListener mProgressBarListener = new AbstractProgressBarListener()
  {
    public boolean onCompleted(WdActivity paramAnonymousWdActivity)
    {
      int j = 1;
      if (Log.DEBUG.get()) {}
      int i = j;
      if (paramAnonymousWdActivity.isMove()) {
        if (paramAnonymousWdActivity.downloadSize == paramAnonymousWdActivity.size)
        {
          i = j;
          if (paramAnonymousWdActivity.uploadSize == paramAnonymousWdActivity.size) {}
        }
        else
        {
          if ((paramAnonymousWdActivity.downloadStatus != 0) || (paramAnonymousWdActivity.uploadStatus != 0)) {
            break label106;
          }
        }
      }
      for (i = j;; i = 0)
      {
        try
        {
          Message localMessage = new Message();
          localMessage.what = 1;
          localMessage.obj = paramAnonymousWdActivity;
          if (i != 0) {
            MyDeviceActivity.this.mHandler.sendMessage(localMessage);
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            label106:
            Log.e(MyDeviceActivity.tag, localException.getMessage(), localException);
          }
        }
        return super.onCompleted(paramAnonymousWdActivity);
      }
    }
    
    public boolean onFailed(WdActivity paramAnonymousWdActivity)
    {
      try
      {
        Message localMessage = Message.obtain(MyDeviceActivity.this.mHandler, 659);
        localMessage.obj = paramAnonymousWdActivity;
        localMessage.sendToTarget();
        return super.onFailed(paramAnonymousWdActivity);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e(MyDeviceActivity.tag, localException.getMessage(), localException);
        }
      }
    }
    
    public boolean onPrepare(WdActivity paramAnonymousWdActivity)
    {
      int j = 1;
      if (Log.DEBUG.get()) {}
      int i = j;
      if (paramAnonymousWdActivity.isMove()) {
        if (paramAnonymousWdActivity.downloadSize == 0L)
        {
          i = j;
          if (paramAnonymousWdActivity.uploadSize == 0L) {}
        }
        else
        {
          if ((paramAnonymousWdActivity.downloadStatus != -2) || (paramAnonymousWdActivity.uploadStatus != -2)) {
            break label90;
          }
        }
      }
      label90:
      for (i = j;; i = 0)
      {
        Message localMessage = Message.obtain(MyDeviceActivity.this.mHandler, 23, paramAnonymousWdActivity);
        if (i != 0) {
          localMessage.sendToTarget();
        }
        return super.onPrepare(paramAnonymousWdActivity);
      }
    }
    
    public boolean onProgress(WdActivity paramAnonymousWdActivity, long paramAnonymousLong)
    {
      if (Log.DEBUG.get()) {}
      l1 = paramAnonymousLong;
      l2 = paramAnonymousLong;
      for (;;)
      {
        try
        {
          if (paramAnonymousWdActivity.isMove())
          {
            l2 = paramAnonymousLong;
            l1 = paramAnonymousLong / 2L;
          }
          l2 = l1;
          Bundle localBundle = new Bundle();
          l2 = l1;
          localBundle.putLong("update_progress", l1);
          l2 = l1;
          Message localMessage = Message.obtain(MyDeviceActivity.this.mHandler, 0, paramAnonymousWdActivity);
          l2 = l1;
          i = Math.round(FileUtils.toProgress(l1, paramAnonymousWdActivity.size));
          if (i <= 0) {
            continue;
          }
          l2 = l1;
          localMessage.arg1 = i;
          l2 = l1;
          localMessage.setData(localBundle);
          l2 = l1;
          localMessage.sendToTarget();
        }
        catch (Exception localException)
        {
          int i;
          Log.e(MyDeviceActivity.tag, localException.getMessage(), localException);
          l1 = l2;
          continue;
        }
        return super.onProgress(paramAnonymousWdActivity, l1);
        i = 1;
      }
    }
    
    public void onTaskCountUpdate(int paramAnonymousInt, WdActivity paramAnonymousWdActivity)
    {
      try
      {
        Message localMessage = Message.obtain(MyDeviceActivity.this.mHandler, 70);
        localMessage.obj = paramAnonymousWdActivity;
        localMessage.arg1 = paramAnonymousInt;
        localMessage.sendToTarget();
        return;
      }
      catch (Exception paramAnonymousWdActivity)
      {
        Log.e(MyDeviceActivity.tag, paramAnonymousWdActivity.getMessage(), paramAnonymousWdActivity);
      }
    }
    
    public boolean onWarning(WdActivity paramAnonymousWdActivity)
    {
      try
      {
        Message localMessage = Message.obtain(MyDeviceActivity.this.mHandler, 666);
        localMessage.obj = paramAnonymousWdActivity;
        localMessage.sendToTarget();
        return super.onWarning(paramAnonymousWdActivity);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.e(MyDeviceActivity.tag, localException.getMessage(), localException);
        }
      }
    }
  };
  private Dialog mPropertyDialog;
  private RenameDialog mRenameDialog;
  private List<String> mRequiredPermissions = new ArrayList();
  private Dialog mResponseErrorDialog;
  private final AtomicBoolean mSDCardEjecting = new AtomicBoolean(false);
  private SearchBarFragment mSearchBarFragment;
  private MenuItem mSearchMenuItem;
  private com.wdc.wd2go.ui.widget.SearchView mSearchView;
  private View mSearchViewLayout;
  private MenuItem mSendMenuItem;
  private SharePrivateActivity.ShareIntentData mShareIntentData;
  private MenuItem mShareMenuItem;
  private SharePrivateFragment mSharePrivateFragment;
  private TitleSecondaryFragment mShareTitleFragment;
  private View mShareViewLayout;
  private ShareWithMeFragment mShareWithMeFragment;
  private boolean mShowCastMenuItem;
  private boolean mShowOverflowMenuItem;
  private boolean mShowSearchMenuItem;
  private boolean mShowShareMenuItem;
  private SlideDrawer mSlideDrawer;
  float mStart = -937.0F;
  private View.OnClickListener mTabClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        paramAnonymousView = MyDeviceActivity.this.mViewPager;
        if (paramAnonymousView == null) {
          return;
        }
      }
      catch (Exception paramAnonymousView)
      {
        Log.e(MyDeviceActivity.tag, "mTabClickListener exception ", paramAnonymousView);
        MyDeviceActivity.this.lastSelectedMediaIndex = MyDeviceActivity.this.mViewPager.getCurrentItem();
      }
    }
  };
  private TabLayout mTabLayout;
  private ActionMode mToolBarActionMode;
  private ActionMode mToolBarShareActionMode;
  private final AtomicBoolean mUSBEjecting = new AtomicBoolean(false);
  private Dialog mUploadContextMenu;
  private CustomViewPager mViewPager;
  private Dialog mWarningDialog;
  private WdFileSystem mWdFileSystem;
  private WifiManager.WifiLock mWifiLock;
  private NavigationView navigationView;
  private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    private void fireAnalyticEvent(int paramAnonymousInt)
    {
      HashMap localHashMap = new HashMap();
      String str = "All";
      switch (paramAnonymousInt)
      {
      }
      for (;;)
      {
        localHashMap.put("Tabs", str);
        WDAnalytics.logEvent("Media", localHashMap);
        return;
        str = "All";
        continue;
        str = "Photo";
        continue;
        str = "Music";
        continue;
        str = "Video";
      }
    }
    
    public void onPageScrollStateChanged(int paramAnonymousInt) {}
    
    public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
    {
      if (paramAnonymousInt2 != 0) {}
      for (boolean bool = true;; bool = false)
      {
        if (MyDeviceActivity.this.mFileFragment != null) {
          MyDeviceActivity.this.mFileFragment.showDivider(bool);
        }
        if (MyDeviceActivity.this.mMediaFragmentManager != null) {
          MyDeviceActivity.this.mMediaFragmentManager.showDivider(bool);
        }
        return;
      }
    }
    
    public void onPageSelected(int paramAnonymousInt)
    {
      boolean bool2 = true;
      for (;;)
      {
        try
        {
          MyDeviceActivity.this.mMainLayout.postDelayed(new Runnable()
          {
            public void run()
            {
              MyDeviceActivity.this.setEditEnable(false);
            }
          }, 350L);
          if (paramAnonymousInt == 0)
          {
            MyDeviceActivity.this.mFileFragment.onPageChange();
            MyDeviceActivity.this.mNavigationSpinner.setAdapter(MyDeviceActivity.this.mBreadcrumbListAdapter);
            MyDeviceActivity.this.mNavigationSpinner.setOnItemSelectedListener(MyDeviceActivity.this.mBreadcrumbListAdapter);
            if (MyDeviceActivity.this.getSupportActionBar() != null)
            {
              localObject = MyDeviceActivity.this.getSupportActionBar();
              if (paramAnonymousInt != 0) {
                break label323;
              }
              bool1 = true;
              ((ActionBar)localObject).setDisplayShowTitleEnabled(bool1);
            }
            MyDeviceActivity.this.checkAndUpdateClickabilityOfSpinner();
            Object localObject = MyDeviceActivity.this;
            if ((paramAnonymousInt != 0) || (MyDeviceActivity.this.getEditEnable())) {
              break label328;
            }
            bool1 = bool2;
            ((MyDeviceActivity)localObject).showShare(bool1);
            if (!MyDeviceActivity.this.isJumpTo) {
              break label270;
            }
            MyDeviceActivity.this.updateTitleBarIndicator(0);
            MyDeviceActivity.this.isJumpTo = false;
            MyDeviceActivity.access$3202(MyDeviceActivity.this, 0);
            MyDeviceActivity.this.updateRightPanelView();
            return;
          }
          if (MyDeviceActivity.this.isJumpToShareMe)
          {
            MyDeviceActivity.this.isJumpToShareMe = false;
            return;
          }
        }
        catch (Exception localException)
        {
          Log.e(MyDeviceActivity.tag, "onPageSelected exception ", localException);
          return;
        }
        MyDeviceActivity.this.showShare(false);
        MyDeviceActivity.this.getMediaFragmentManager().onPageChanged(paramAnonymousInt);
        MyDeviceActivity.this.mNavigationSpinner.setAdapter(MyDeviceActivity.this.mMediaBreadcrumbListAdapter);
        MyDeviceActivity.this.mNavigationSpinner.setOnItemSelectedListener(MyDeviceActivity.this.mMediaBreadcrumbListAdapter);
        continue;
        label270:
        MyDeviceActivity.this.updateTitleBarIndicator(paramAnonymousInt);
        MyDeviceActivity.this.stopRefresh();
        MyDeviceActivity.access$3202(MyDeviceActivity.this, paramAnonymousInt);
        MyDeviceActivity.this.updateRightPanelView();
        MyDeviceActivity.this.showTabs(true);
        MyDeviceActivity.this.lastSelectedMediaIndex = paramAnonymousInt;
        fireAnalyticEvent(paramAnonymousInt);
        return;
        label323:
        boolean bool1 = false;
        continue;
        label328:
        bool1 = false;
      }
    }
  };
  private Toolbar toolbar;
  
  public MyDeviceActivity() {}
  
  private void _checkAndShare(WdFile paramWdFile, WdActivity paramWdActivity)
  {
    if (!this.mNeedDownLoad)
    {
      if (paramWdActivity != null)
      {
        doShareFile(paramWdActivity);
        return;
      }
      paramWdActivity = paramWdFile.getWdActivity();
      WdActivity localWdActivity = paramWdFile.getWdActivityDownload();
      if (((paramWdActivity != null) && (localWdActivity != null) && (localWdActivity.isFileDownloaded())) || (paramWdFile.isFolder))
      {
        doShareFile(paramWdActivity);
        return;
      }
      getWdFileLoader(2131297328).execute(new WdFile[] { paramWdFile });
      return;
    }
    getWdFileLoader(2131297328).execute(new WdFile[] { paramWdFile });
  }
  
  private void changeOverflowMenuItemVisibility()
  {
    if ((this.mOverflowMenuItem != null) && (this.mWdFileSystem != null) && (this.mWdFileSystem.getCurrentFolder() != null) && (this.mWdFileSystem.getCurrentFolder().isRoot()) && (getCurrentTabIndex() == 0)) {
      this.mOverflowMenuItem.setVisible(false);
    }
  }
  
  private void checkDeviceFirmware()
  {
    Device localDevice = DeviceManager.getInstance().getHostDevice();
    if ((localDevice == null) || (localDevice.deviceType == null) || (localDevice.deviceType.typeName == null)) {}
    do
    {
      return;
      i = this.mCurrentDeviceFirmwareVersion.lastIndexOf("-");
      if (i < 0) {
        break;
      }
      this.mCurrentDeviceFirmwareVersion = ((String)this.mCurrentDeviceFirmwareVersion.subSequence(0, i));
    } while (this.mCurrentDeviceFirmwareVersion.trim().length() == 0);
    int i = -1;
    String str = localDevice.deviceType.typeName;
    int j = 1;
    if (str.equalsIgnoreCase("My Book Live")) {
      if (compareVersions(this.mCurrentDeviceFirmwareVersion, "02.11.09") >= 0)
      {
        j = 1;
        i = 2131297117;
      }
    }
    while (!str.equalsIgnoreCase("My Book Live Duo")) {
      for (;;)
      {
        this.mCurrentDeviceFirmwareVersion = null;
        if ((j != 0) || (i == -1)) {
          break;
        }
        getFirmwareAlert(localDevice, i).show();
        return;
        j = 0;
      }
    }
    if (compareVersions(this.mCurrentDeviceFirmwareVersion, "02.31.08") >= 0) {}
    for (j = 1;; j = 0)
    {
      i = 2131297118;
      break;
    }
  }
  
  private boolean checkIsHaveMusicFileInMediaStore()
  {
    boolean bool3 = false;
    boolean bool1 = false;
    bool2 = bool3;
    try
    {
      Cursor localCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
      if (localCursor != null)
      {
        bool2 = bool3;
        if (localCursor.getCount() <= 0) {
          break label52;
        }
      }
      label52:
      for (bool1 = true;; bool1 = false)
      {
        bool2 = bool1;
        localCursor.close();
        return bool1;
      }
      return bool2;
    }
    catch (Exception localException)
    {
      Log.e(tag, "query media store exception: " + localException.getMessage(), localException);
    }
  }
  
  private boolean checkSDcardAvabile()
  {
    boolean bool = true;
    try
    {
      if (!this.mWdFileManager.isExternalStorageAvailable())
      {
        loadLocalFileSystem(new WdActivity[0]);
        bool = false;
      }
      return bool;
    }
    catch (Exception localException)
    {
      Log.e(tag, "checkSdCardAvabile exception: " + localException.getMessage(), localException);
    }
    return true;
  }
  
  private void checkUserChoiceFor3G(final int paramInt, final WdFile paramWdFile)
  {
    boolean bool = this.mWdFileManager.getConfiguration().showDataPlanWarn();
    if ((this.mWdFileManager.isMobileNetwork()) && (!paramWdFile.isFileDownLoaded()) && (paramWdFile.size > 10485760L) && (bool))
    {
      DialogUtils.makeConfirmDialogExt(this, getString(2131296786), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyDeviceActivity.this.getWdFileLoader(paramInt).execute(new WdFile[] { paramWdFile });
        }
      }, null).show();
      return;
    }
    getWdFileLoader(paramInt).execute(new WdFile[] { paramWdFile });
  }
  
  private void clearLastestAdd()
  {
    getSelectedSDCardFiles().clear();
    this.mWdFileManager.setLatestAddDate(-1L, -1);
  }
  
  private void delayedReload(final long paramLong)
  {
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (MyDeviceActivity.this.lastTime > paramLong) {
          return;
        }
        long l = System.currentTimeMillis();
        if (l - MyDeviceActivity.this.lastTime > MyDeviceActivity.this.delayTime)
        {
          MyDeviceActivity.access$1502(MyDeviceActivity.this, l);
          MyDeviceActivity.this.reload();
          return;
        }
        MyDeviceActivity.this.delayedReload(paramLong);
      }
    }, this.delayTime);
  }
  
  private void dismissAddFileDialog()
  {
    if (this.mAddFileDialog != null) {
      this.mAddFileDialog.dismiss();
    }
  }
  
  private void dismissChooserAppDilaog()
  {
    if (this.mAppChooseDialog != null) {
      this.mAppChooseDialog.dismiss();
    }
  }
  
  private void dismissDeleteDialog()
  {
    if ((this.mDeleteDialog != null) && (this.mDeleteDialog.isShowing())) {
      this.mDeleteDialog.dismiss();
    }
  }
  
  private void dismissDeviceDeleteDialog()
  {
    if (this.mDeleteDeviceDialog != null) {
      this.mDeleteDeviceDialog.dismiss();
    }
  }
  
  private void dismissDeviceInfoDialog()
  {
    if (this.mDeviceInfoDialog != null) {
      this.mDeviceInfoDialog.dismiss();
    }
  }
  
  private void dissmissShowResponseErrorDialog()
  {
    if (this.mResponseErrorDialog != null) {
      this.mResponseErrorDialog.dismiss();
    }
  }
  
  private void dissmissWaringDialog()
  {
    if (this.mWarningDialog != null) {
      this.mWarningDialog.dismiss();
    }
  }
  
  private void extractIntent()
  {
    this.mIntent = getIntent();
    if (this.mIntent == null) {
      return;
    }
    Object localObject1 = this.mIntent.getStringExtra("back2welcome");
    if (this.mIntent.getBooleanExtra("music_player_intent", false))
    {
      localObject1 = new Intent();
      ((Intent)localObject1).setClass(this, MusicPlayerActivity.class);
      startActivity((Intent)localObject1);
      finish();
      return;
    }
    if (StringUtils.isEquals((String)localObject1, "back2welcome"))
    {
      gotoWelcomeActivity();
      return;
    }
    String str = this.mIntent.getAction();
    localObject1 = this.mIntent.getExtras();
    Object localObject2 = null;
    label156:
    int i;
    if (!TextUtils.isEmpty(this.mWdFileManager.getConfiguration().getSecurityCode()))
    {
      localObject1 = localObject2;
      if (!this.mApplication.loginFlag.get())
      {
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
        localObject1 = localObject2;
      }
      if (localObject1 == null) {
        break label302;
      }
      i = 0;
      label162:
      if (i < ((ArrayList)localObject1).size())
      {
        localObject2 = (Uri)((ArrayList)localObject1).get(i);
        if (localObject2 != null) {
          break label304;
        }
      }
    }
    for (;;)
    {
      i += 1;
      break label162;
      break;
      if ((str != null) && ("android.intent.action.SEND".equals(str)) && (localObject1 != null) && (!((Bundle)localObject1).isEmpty()))
      {
        localObject2 = (Uri)((Bundle)localObject1).get("android.intent.extra.STREAM");
        localObject1 = new ArrayList();
        ((ArrayList)localObject1).add(localObject2);
        showSecurityCodeForThirdPartApp();
        break label156;
      }
      if ("android.intent.action.SEND_MULTIPLE".equals(str))
      {
        localObject1 = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        showSecurityCodeForThirdPartApp();
        break label156;
      }
      localObject1 = localObject2;
      if (!StringUtils.isEquals(EnterSecurityCodeActivity.Action, str)) {
        break label156;
      }
      this.mFromOtherApp.set(true);
      localObject1 = localObject2;
      break label156;
      label302:
      break;
      label304:
      this.mFromOtherApp.set(true);
      localObject2 = FileUtils.getSharePath(this, (Uri)localObject2);
      if (localObject2 != null)
      {
        localObject2 = new WdActivity((String)localObject2, new File((String)localObject2), "Download", -5);
        ((WdActivity)localObject2).mDatabaseAgent = this.mWdFileManager.getDatabaseAgent();
        this.mWdFileSystem.addToWdActivity((WdActivity)localObject2);
      }
    }
  }
  
  private void finishActionMode()
  {
    if (this.mToolBarActionMode != null)
    {
      this.mToolBarActionMode.setTag(Boolean.TRUE);
      this.mToolBarActionMode.finish();
    }
  }
  
  private static void fireAnalyticEventSharesInfoIconClicked()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Interaction", "Info Click");
    WDAnalytics.logEvent("Shares Info", localHashMap);
  }
  
  private static void fireAnalyticEventSharesInfoLearnMoreClicked()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Interaction", "Learn More");
    WDAnalytics.logEvent("Shares Info", localHashMap);
  }
  
  private SearchView.SearchFilter getCurrentMediaPath()
  {
    if (this.mMediaFragmentManager != null) {
      return this.mMediaFragmentManager.getCurrentFragment().getSearchFilter();
    }
    return new SearchView.SearchFilter();
  }
  
  private DeleteFilesLoader getDeleteFilesLoader(List<WdFile> paramList)
  {
    return new DeleteFilesLoader(this, paramList);
  }
  
  private DeleteDeviceLoader getDeviceDeleteLoader()
  {
    return new DeleteDeviceLoader(this);
  }
  
  private DownLoadFilesLoader getDownloadFilesLoader(List<WdFile> paramList)
  {
    return new DownLoadFilesLoader(this, paramList);
  }
  
  private OpenWdActivityLoader getOpenWdActivityLoader()
  {
    return new OpenWdActivityLoader(this, getTabIndex());
  }
  
  private ShareFileLoader getShareLoader()
  {
    return new ShareFileLoader(this);
  }
  
  private WdFileLoader getWdFileLoader(int paramInt)
  {
    return new WdFileLoader(this, getString(paramInt), getTabIndex());
  }
  
  private boolean haveImagesInDCIM()
  {
    try
    {
      Object localObject = CameraRecord.getCameraFolders(getBaseContext());
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          CameraFolders localCameraFolders = (CameraFolders)((Iterator)localObject).next();
          int i = CameraRecord.getCameraGridData(getBaseContext(), localCameraFolders).size();
          if (i > 0) {
            return true;
          }
        }
      }
    }
    catch (Exception localException)
    {
      Log.e(tag, "haveImagesInDCIM", localException);
    }
    return false;
  }
  
  private void initBadgeView()
  {
    int i;
    try
    {
      View localView = this.mSlideDrawer.getActivityView();
      if (localView == null) {
        return;
      }
      if (this.mBadgeView == null)
      {
        this.mBadgeView = new BadgeView(this, localView);
        localView.setDuplicateParentStateEnabled(true);
      }
      if (this.mBadgeView == null) {
        return;
      }
      this.mBadgeView.setText("0");
      this.mBadgeView.setBadgePosition(2);
      i = 0;
      if (this.mWdFileManager != null) {
        i = this.mWdFileManager.getWdActivityCountInProgress();
      }
      if (i > 0) {
        this.mBadgeView.setNumValue(i);
      }
      if (this.mBadgeView.isShown())
      {
        if (i != 0) {
          return;
        }
        this.mBadgeView.hide();
        return;
      }
    }
    catch (Exception localException)
    {
      Log.e(tag, localException.getMessage(), localException);
      return;
    }
    if (i > 0) {
      this.mBadgeView.show();
    }
  }
  
  private void initTextColor()
  {
    this.COLORSTATELIST_FONT1 = getResources().getColorStateList(2130838754);
    this.COLORSTATELIST_FONT2 = getResources().getColorStateList(2130838755);
    this.WHITE_COLOR = getResources().getColor(2131755252);
  }
  
  private boolean isAnyDialogShown()
  {
    return ((getDeviceUpgradeDialog() != null) && (getDeviceUpgradeDialog().isShowing())) || ((this.mDeviceVersionDialog != null) && (this.mDeviceVersionDialog.isShowing())) || ((this.mPropertyDialog != null) && (this.mPropertyDialog.isShowing())) || ((this.mUploadContextMenu != null) && (this.mUploadContextMenu.isShowing())) || ((this.mMoveFileDialog != null) && (this.mMoveFileDialog.isShowing())) || ((this.mNewFolderDialog != null) && (this.mNewFolderDialog.getDialog() != null) && (this.mNewFolderDialog.getDialog().isShowing())) || ((this.mRenameDialog != null) && (this.mRenameDialog.getDialog() != null) && (this.mRenameDialog.getDialog().isShowing())) || ((this.mDeleteDialog != null) && (this.mDeleteDialog.isShowing())) || ((this.mAppChooseDialog != null) && (this.mAppChooseDialog.isShowing())) || ((this.mAddFileDialog != null) && (this.mAddFileDialog.isShowing())) || ((this.mDeleteDeviceDialog != null) && (this.mDeleteDeviceDialog.isShowing())) || ((this.mDeviceInfoDialog != null) && (this.mDeviceInfoDialog.isShowing())) || ((this.mResponseErrorDialog != null) && (this.mResponseErrorDialog.isShowing())) || ((this.mWarningDialog != null) && (this.mWarningDialog.isShowing()));
  }
  
  private boolean isPermissionGranted()
  {
    if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0)
    {
      this.mRequiredPermissions.remove("android.permission.WRITE_EXTERNAL_STORAGE");
      return true;
    }
    return false;
  }
  
  private boolean isPermissionsCheckDone(int paramInt)
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 23)
    {
      bool1 = bool2;
      if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0)
      {
        checkAndRequestPermission(paramInt);
        bool1 = false;
      }
    }
    return bool1;
  }
  
  private void openFile()
  {
    try
    {
      switch (getTabIndex())
      {
      case 0: 
        Object localObject1 = mergerSelectedWdFiles();
        if ((localObject1 == null) || (((List)localObject1).size() != 1)) {
          return;
        }
        localObject1 = (WdFile)((List)localObject1).get(0);
        setCurrentAction((DatabaseBean)localObject1, 33);
        openWdFile((WdFile)localObject1);
        return;
      }
    }
    catch (ResponseException localResponseException)
    {
      Log.e(tag, localResponseException.getMessage(), localResponseException);
      return;
    }
    Object localObject2 = mergeSelectedLocals();
    if ((localObject2 != null) && (((List)localObject2).size() == 1))
    {
      localObject2 = (WdActivity)((List)localObject2).get(0);
      setCurrentAction((DatabaseBean)localObject2, 33);
      openWdFile(((WdActivity)localObject2).getWdFile());
      return;
      localObject2 = getSelectedWdActivities();
      if ((localObject2 != null) && (((List)localObject2).size() == 1))
      {
        openWdFile(((WdActivity)((List)localObject2).get(0)).getWdFile());
        return;
      }
    }
  }
  
  private void saveAs()
  {
    this.mWdFileManager.getCurrentList().set(959);
    this.mWdFileManager.setCurrentSDCardFullPath(null);
    Device localDevice = this.mWdFileManager.getDeviceById("SDCard");
    DeviceManager.getInstance().setGuestDevice(localDevice);
    showMoveFileWindow(539, true);
  }
  
  private void setupLapismSearchView()
  {
    this.mLapismSearchView = ((com.lapism.searchview.SearchView)findViewById(2131821613));
    if (this.mLapismSearchView != null)
    {
      this.mLapismSearchView.setVersion(1002);
      this.mLapismSearchView.setTheme(3000, true);
      this.mLapismSearchView.setDivider(false);
      this.mLapismSearchView.setHint(2131297231);
      this.mLapismSearchView.setTextSize(getResources().getInteger(2131689489));
      this.mLapismSearchView.setTextColor(ContextCompat.getColor(this, 2131755022));
      this.mLapismSearchView.setVoice(true);
      this.mLapismSearchView.setAnimationDuration(300);
      this.mLapismSearchView.setShadowColor(ContextCompat.getColor(this, 17170445));
      this.mLapismSearchView.setOnQueryTextListener(new com.lapism.searchview.SearchView.OnQueryTextListener()
      {
        public boolean onQueryTextChange(String paramAnonymousString)
        {
          return false;
        }
        
        public boolean onQueryTextSubmit(String paramAnonymousString)
        {
          MyDeviceActivity.this.startQuery(paramAnonymousString);
          return true;
        }
      });
      this.mLapismSearchView.setOnOpenCloseListener(new SearchView.OnOpenCloseListener()
      {
        public void onClose()
        {
          MyDeviceActivity.this.closeSearchView();
        }
        
        public void onOpen()
        {
          MyDeviceActivity.this.onSearchRequested();
        }
      });
    }
  }
  
  private void shareFile()
  {
    switch (getTabIndex())
    {
    }
    Object localObject;
    do
    {
      do
      {
        do
        {
          return;
          localObject = mergerSelectedWdFiles();
        } while ((localObject == null) || (((List)localObject).size() <= 0));
        localObject = (WdFile)((List)localObject).get(0);
        setCurrentAction((DatabaseBean)localObject, 30);
        checkAndShare((WdFile)localObject, null);
        return;
        localObject = mergeSelectedLocals();
      } while ((localObject == null) || (((List)localObject).size() != 1));
      localObject = (WdActivity)((List)localObject).get(0);
      setCurrentAction((DatabaseBean)localObject, 30);
      checkAndShare(((WdActivity)localObject).getWdFile(), (WdActivity)localObject);
      return;
      localObject = getSelectedWdActivities();
    } while ((localObject == null) || (((List)localObject).size() != 1));
    checkAndShare(((WdActivity)((List)localObject).get(0)).getWdFile(), null);
  }
  
  private void showChooserDialog(final WdActivity paramWdActivity, final boolean paramBoolean)
  {
    dismissChooserAppDilaog();
    SharedPreferences localSharedPreferences = getSharedPreferences("VIDEO_PLAYER_WARNING", 0);
    if ((localSharedPreferences != null) && (paramWdActivity.getMemeType() == 11) && (!localSharedPreferences.getBoolean("DISPLAYED", false)))
    {
      DialogUtils.info(this, getString(2131296957), getString(2131297486), new Runnable()
      {
        public void run()
        {
          MyDeviceActivity.this.mAppChooseDialog.showChooseAppList(paramWdActivity, paramBoolean);
        }
      });
      localSharedPreferences.edit().putBoolean("DISPLAYED", true).apply();
      return;
    }
    this.mAppChooseDialog.showChooseAppList(paramWdActivity, paramBoolean);
  }
  
  private void showOperationTips()
  {
    Toast.makeText(this, 2131296763, 1).show();
  }
  
  private void showProperty(boolean paramBoolean)
  {
    switch (getTabIndex())
    {
    }
    do
    {
      do
      {
        do
        {
          return;
          localObject = mergerSelectedWdFiles();
        } while ((localObject == null) || (((List)localObject).size() != 1));
        localObject = (WdFile)((List)localObject).get(0);
        setCurrentAction((DatabaseBean)localObject, 36);
        showProperty((WdFile)localObject, paramBoolean);
        return;
        localObject = mergeSelectedLocals();
      } while ((localObject == null) || (((List)localObject).size() != 1));
      localObject = (WdActivity)((List)localObject).get(0);
      setCurrentAction((DatabaseBean)localObject, 36);
      showProperty(((WdActivity)localObject).getWdFile(), paramBoolean);
      return;
      localObject = getSelectedWdActivities();
    } while ((localObject == null) || (((List)localObject).size() != 1));
    Object localObject = (WdActivity)((List)localObject).get(0);
    setCurrentAction((DatabaseBean)localObject, 36);
    showProperty(((WdActivity)localObject).getWdFile(), paramBoolean);
  }
  
  private void showSearchViewBar()
  {
    this.mLapismSearchView.open(true);
  }
  
  private void showSecurityCodeForThirdPartApp()
  {
    try
    {
      if ((!StringUtils.isEmpty(this.mWdFileManager.getConfiguration().getSecurityCode())) && (!this.mApplication.loginFlag.get()))
      {
        startActivity(new Intent(this, EnterSecurityCodeActivity.class));
        finish();
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, localException.getMessage(), localException);
      finish();
    }
  }
  
  private void showTabs(boolean paramBoolean)
  {
    this.mAppBarLayout.setExpanded(true, paramBoolean);
  }
  
  private void startActionMode()
  {
    this.mToolBarActionMode = startSupportActionMode(new ActionMode.Callback()
    {
      public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
      {
        return false;
      }
      
      public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
      {
        MyDeviceActivity.this.getFolderListFragment().initMenu(MyDeviceActivity.this.findViewById(2131821901));
        MyDeviceActivity.access$402(MyDeviceActivity.this, new TitleEditFragment());
        MyDeviceActivity.this.mEditFragment.init(MyDeviceActivity.this);
        paramAnonymousActionMode.setCustomView(MyDeviceActivity.this.mEditFragment.onCreateView(MyDeviceActivity.this.getLayoutInflater(), null, null));
        return true;
      }
      
      public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
      {
        if ((paramAnonymousActionMode.getTag() == null) && (!MyDeviceActivity.this.mIsInPrivateSharing)) {
          MyDeviceActivity.this.mEditFragment.onBackClick();
        }
      }
      
      public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
      {
        return false;
      }
    });
  }
  
  private void startActionModeForPrivateShare()
  {
    this.mToolBarShareActionMode = startSupportActionMode(new ActionMode.Callback()
    {
      public boolean onActionItemClicked(ActionMode paramAnonymousActionMode, MenuItem paramAnonymousMenuItem)
      {
        return false;
      }
      
      public boolean onCreateActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
      {
        MyDeviceActivity.access$602(MyDeviceActivity.this, new TitleSecondaryFragment());
        MyDeviceActivity.this.mShareTitleFragment.init(MyDeviceActivity.this);
        paramAnonymousMenu = MyDeviceActivity.this.mShareTitleFragment.onCreateView(MyDeviceActivity.this.getLayoutInflater(), null, null);
        MyDeviceActivity.this.mShareTitleFragment.showCloseIcon(MyDeviceActivity.this.isPhone());
        paramAnonymousActionMode.setCustomView(paramAnonymousMenu);
        MyDeviceActivity.this.mShareTitleFragment.showSendEmailIcon(true);
        return true;
      }
      
      public void onDestroyActionMode(ActionMode paramAnonymousActionMode)
      {
        MyDeviceActivity.access$502(MyDeviceActivity.this, false);
        MyDeviceActivity.this.setEditEnable(false);
      }
      
      public boolean onPrepareActionMode(ActionMode paramAnonymousActionMode, Menu paramAnonymousMenu)
      {
        return false;
      }
    });
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }
  
  private void startDownloadFile()
  {
    List localList = mergerSelectedWdFiles();
    if ((localList != null) && (!localList.isEmpty()))
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        WdFile localWdFile = (WdFile)localIterator.next();
        if (!isFileDownloadSuccessful(localWdFile)) {
          break;
        }
        localArrayList.add(localWdFile);
      }
      if (localArrayList.size() == localList.size())
      {
        DialogUtils.warnExt(this, getString(2131297494), getString(2131297496), null);
        setEditEnable(false);
      }
    }
    else
    {
      return;
    }
    getDownloadFilesLoader(localList).execute(new Integer[0]);
  }
  
  private void startMoveByQueryMediaStore()
  {
    for (;;)
    {
      Object localObject5;
      String str1;
      Object localObject6;
      try
      {
        if (this.mWdFileManager.getAddFileType() == -1) {
          return;
        }
        localObject5 = null;
        Object localObject1 = null;
        str1 = null;
        Object localObject4 = null;
        long l1 = this.mWdFileManager.getLatestAddDate();
        if (-1L == l1) {
          break;
        }
        ContentResolver localContentResolver = getContentResolver();
        long l2 = System.currentTimeMillis() / 1000L;
        localObject3 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        localObject6 = "date_added" + ">? and " + "date_added" + "<=? ";
        String str2 = "date_added" + " desc";
        localObject6 = localContentResolver.query((Uri)localObject3, null, (String)localObject6, new String[] { String.valueOf(l1), String.valueOf(l2) }, str2);
        if ((localObject6 != null) && (((Cursor)localObject6).moveToFirst()))
        {
          localObject3 = ((Cursor)localObject6).getString(((Cursor)localObject6).getColumnIndex("_data"));
          if (!StringUtils.isEmpty((String)localObject3)) {
            localObject1 = new File((String)localObject3);
          }
          ((Cursor)localObject6).close();
          localObject3 = localObject1;
          localObject1 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
          localObject5 = "date_added" + ">? and " + "date_added" + "<=? ";
          localObject6 = "date_added" + " desc";
          localObject5 = localContentResolver.query((Uri)localObject1, null, (String)localObject5, new String[] { String.valueOf(l1), String.valueOf(l2) }, (String)localObject6);
          if ((localObject5 == null) || (!((Cursor)localObject5).moveToFirst())) {
            break label428;
          }
          str1 = ((Cursor)localObject5).getString(((Cursor)localObject5).getColumnIndex("_data"));
          localObject1 = localObject4;
          if (!StringUtils.isEmpty(str1)) {
            localObject1 = new File(str1);
          }
          ((Cursor)localObject5).close();
          verifyAddedFile((File)localObject3, (File)localObject1, null);
          return;
        }
      }
      catch (Exception localException)
      {
        Log.e(tag, "query MediaStore for upload create file exception ", localException);
        return;
      }
      Object localObject3 = localObject5;
      if (localObject6 != null)
      {
        ((Cursor)localObject6).close();
        localObject3 = localObject5;
        continue;
        label428:
        Object localObject2 = str1;
        if (localObject5 != null)
        {
          ((Cursor)localObject5).close();
          localObject2 = str1;
        }
      }
    }
  }
  
  private void startMoveBySpecificFile(Uri paramUri)
  {
    for (;;)
    {
      String str2;
      Object localObject2;
      Cursor localCursor;
      try
      {
        if (this.mWdFileManager.getAddFileType() == -1) {
          return;
        }
        str2 = null;
        File localFile = null;
        localObject2 = null;
        localObject1 = null;
        localCursor = getContentResolver().query(paramUri, null, null, null, null);
        if ((localCursor != null) && (localCursor.moveToFirst()))
        {
          str2 = localCursor.getString(localCursor.getColumnIndex("_data"));
          if (!StringUtils.isEmpty(str2)) {
            localFile = new File(str2);
          }
          str2 = localCursor.getString(localCursor.getColumnIndex("_data"));
          if (!StringUtils.isEmpty(str2)) {
            localObject1 = new File(str2);
          }
          localCursor.close();
          verifyAddedFile(localFile, (File)localObject1, null);
          return;
        }
      }
      catch (Exception localException)
      {
        Log.e(tag, "get " + paramUri + " specific file from mediaStore exception ", localException);
        return;
      }
      String str1 = str2;
      Object localObject1 = localObject2;
      if (localCursor != null)
      {
        localCursor.close();
        str1 = str2;
        localObject1 = localObject2;
      }
    }
  }
  
  private void startMoveFile(final int paramInt)
  {
    switch (getTabIndex())
    {
    }
    int i;
    boolean bool;
    do
    {
      this.mWdFileManager.getCurrentList().set(919);
      showMoveFileWindow(paramInt, true);
      do
      {
        do
        {
          return;
          localObject = mergerSelectedWdFiles();
        } while ((localObject == null) || (((List)localObject).isEmpty()));
        if (((List)localObject).size() > 100)
        {
          DialogUtils.makeConfirmDialogExt(this, getString(2131297497, new Object[] { Integer.valueOf(100) }), new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              MyDeviceActivity.this.mWdFileManager.getCurrentList().set(919);
              MyDeviceActivity.this.showMoveFileWindow(paramInt, true);
            }
          }, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              MyDeviceActivity.this.setEditEnable(false);
            }
          }).show();
          return;
        }
        this.mWdFileManager.getCurrentList().set(919);
        showMoveFileWindow(paramInt, true);
        break;
        localObject = mergeSelectedLocals();
      } while ((localObject == null) || (((List)localObject).isEmpty()));
      i = 0;
      Object localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        WdActivity localWdActivity = (WdActivity)((Iterator)localObject).next();
        i = (int)(i + localWdActivity.size);
      }
      bool = this.mWdFileManager.getConfiguration().showDataPlanWarn();
    } while ((!this.mWdFileManager.isMobileNetwork()) || (i <= 10485760) || (!bool));
    DialogUtils.makeConfirmDialogExt(this, getString(2131296786), new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MyDeviceActivity.this.mWdFileManager.getCurrentList().set(919);
        MyDeviceActivity.this.showMoveFileWindow(paramInt, true);
      }
    }, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MyDeviceActivity.this.setEditEnable(false);
      }
    }).show();
  }
  
  private void updateImportProgress(WdActivity paramWdActivity)
  {
    int i = Math.round(FileUtils.toProgress(paramWdActivity.downloadSize, paramWdActivity.size));
    if (getWdFileSystem().getCurrentFolder().getDevice().isKorraDevice()) {}
    try
    {
      Message localMessage = new Message();
      localMessage.what = 0;
      localMessage.obj = paramWdActivity;
      this.mHandler.sendMessage(localMessage);
      if ((i >= 100) && (paramWdActivity.status != -1)) {
        reloadAfterEjectStorage();
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(tag, localException.getMessage(), localException);
      }
    }
  }
  
  private void updateTitleBarIndicator(int paramInt)
  {
    int i = this.mCursorWidth;
    int j = this.mCursorWidth * 2;
    int k = this.mCursorWidth * 3;
    int m = this.mPageIndex;
    TranslateAnimation localTranslateAnimation = null;
    if (paramInt == 0) {
      if (m == 1) {
        localTranslateAnimation = new TranslateAnimation(i, 0.0F, 0.0F, 0.0F);
      }
    }
    for (;;)
    {
      if (localTranslateAnimation != null)
      {
        localTranslateAnimation.setFillAfter(true);
        localTranslateAnimation.setDuration('Ĭ');
      }
      return;
      if (m == 2)
      {
        localTranslateAnimation = new TranslateAnimation(j, 0.0F, 0.0F, 0.0F);
      }
      else if (m == 3)
      {
        localTranslateAnimation = new TranslateAnimation(k, 0.0F, 0.0F, 0.0F);
      }
      else
      {
        localTranslateAnimation = new TranslateAnimation(0.0F, 0.0F, 0.0F, 0.0F);
        continue;
        if (paramInt == 1)
        {
          if (m == 0) {
            localTranslateAnimation = new TranslateAnimation(0.0F, i, 0.0F, 0.0F);
          } else if (m == 2) {
            localTranslateAnimation = new TranslateAnimation(j, i, 0.0F, 0.0F);
          } else if (m == 3) {
            localTranslateAnimation = new TranslateAnimation(k, i, 0.0F, 0.0F);
          } else {
            localTranslateAnimation = new TranslateAnimation(i, i, 0.0F, 0.0F);
          }
        }
        else if (paramInt == 2)
        {
          if (m == 0) {
            localTranslateAnimation = new TranslateAnimation(0.0F, j, 0.0F, 0.0F);
          } else if (m == 1) {
            localTranslateAnimation = new TranslateAnimation(i, j, 0.0F, 0.0F);
          } else if (m == 3) {
            localTranslateAnimation = new TranslateAnimation(k, j, 0.0F, 0.0F);
          } else {
            localTranslateAnimation = new TranslateAnimation(j, j, 0.0F, 0.0F);
          }
        }
        else if (paramInt == 3) {
          if (m == 0) {
            localTranslateAnimation = new TranslateAnimation(0.0F, k, 0.0F, 0.0F);
          } else if (m == 1) {
            localTranslateAnimation = new TranslateAnimation(i, k, 0.0F, 0.0F);
          } else if (m == 2) {
            localTranslateAnimation = new TranslateAnimation(j, k, 0.0F, 0.0F);
          } else {
            localTranslateAnimation = new TranslateAnimation(k, k, 0.0F, 0.0F);
          }
        }
      }
    }
  }
  
  private void uploadAddedFile(WdActivity paramWdActivity)
  {
    getSelectedSDCardFiles().clear();
    getSelectedSDCardFiles().add(paramWdActivity);
    this.mWdFileManager.setLatestAddDate(-1L, -1);
    boolean bool = this.mWdFileManager.getConfiguration().showDataPlanWarn();
    if ((this.mWdFileManager.isMobileNetwork()) && (paramWdActivity.size > 10485760L) && (bool))
    {
      DialogUtils.makeConfirmDialogExt(this, getString(2131296786), new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyDeviceActivity.this.uploadToCurrentFolder(969, true);
        }
      }, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyDeviceActivity.this.setEditEnable(false);
        }
      }).show();
      return;
    }
    uploadToCurrentFolder(969, true);
  }
  
  private void verifyAddedFile(File paramFile1, File paramFile2, File paramFile3)
  {
    int j = 1;
    int i;
    File localFile;
    if ((paramFile2 != null) && (paramFile2.exists()) && (paramFile2.isFile()))
    {
      paramFile3 = paramFile2;
      j = 0;
      i = j;
      localFile = paramFile3;
      if (paramFile1 != null)
      {
        i = j;
        localFile = paramFile3;
        if (paramFile1.exists())
        {
          i = j;
          localFile = paramFile3;
          if (paramFile1.isFile())
          {
            i = j;
            localFile = paramFile3;
            if (paramFile1.lastModified() > paramFile2.lastModified())
            {
              localFile = paramFile1;
              i = j;
            }
          }
        }
      }
      if (localFile != null)
      {
        uploadAddedFile(new WdActivity(localFile.getAbsolutePath(), localFile, "Upload", 0));
        paramFile1 = FileUtils.getFileRange(localFile.length());
        paramFile2 = new HashMap();
        if (i == 0) {
          break label222;
        }
        paramFile2.put("File Type Added", "Photo");
        paramFile2.put("Photo Viewed File Size Range", paramFile1);
      }
    }
    for (;;)
    {
      FlurryAgent.logEvent("Add", paramFile2);
      return;
      i = j;
      localFile = paramFile3;
      if (paramFile1 == null) {
        break;
      }
      i = j;
      localFile = paramFile3;
      if (!paramFile1.exists()) {
        break;
      }
      i = j;
      localFile = paramFile3;
      if (!paramFile1.isFile()) {
        break;
      }
      i = j;
      localFile = paramFile1;
      break;
      label222:
      paramFile2.put("File Type Added", "Video");
      paramFile2.put("Video Streamed File Size Range", paramFile1);
    }
  }
  
  public boolean IsShowOperationTips()
  {
    boolean bool2 = false;
    WdFile localWdFile = this.mWdFileManager.getWdFileSystem(null).getCurrentFolder();
    boolean bool1 = bool2;
    if (localWdFile != null)
    {
      bool1 = bool2;
      if (localWdFile.getDevice() != null)
      {
        if ((localWdFile.getDevice().isOrionDevice()) || (isDemo())) {
          break label54;
        }
        bool1 = true;
      }
    }
    return bool1;
    label54:
    return true;
  }
  
  public void addShares()
  {
    for (;;)
    {
      try
      {
        List localList = getSelectedWdFiles();
        if (localList == null) {
          break;
        }
        if (localList.isEmpty()) {
          return;
        }
        int i = 0;
        int j = 0;
        long l = 0L;
        Object localObject1 = "";
        Object localObject2 = localList.iterator();
        if (((Iterator)localObject2).hasNext())
        {
          localObject1 = (WdFile)((Iterator)localObject2).next();
          if (((WdFile)localObject1).isFolder)
          {
            i += 1;
            localObject1 = ((WdFile)localObject1).fullPath + '|';
            continue;
          }
          j += 1;
          l += ((WdFile)localObject1).size;
          continue;
        }
        String str2 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
        Device localDevice = getWdFileManager().getCurrentDevice();
        localObject2 = getWdFileManager().getWdFileSystem(localDevice);
        if ((localObject2 == null) || (((WdFileSystem)localObject2).getCurrentFolder() == null)) {
          break;
        }
        if ((i == 1) && (j == 0))
        {
          k = 1;
          if (k == 0) {
            break label337;
          }
          localObject1 = "Share_Collaborative";
          localObject1 = new WdActivity(localDevice, str2, (String)localObject1);
          ((WdActivity)localObject1).status = -6;
          ((WdActivity)localObject1).fileCount = j;
          ((WdActivity)localObject1).folderCount = i;
          ((WdActivity)localObject1).size = l;
          ((WdActivity)localObject1).name = (getString(2131297323) + ": " + ShareTitleBar.getFileText((WdActivity)localObject1, this));
          if (k != 0) {
            ((WdActivity)localObject1).name = ((WdFile)localList.get(0)).name;
          }
          ((WdFileSystem)localObject2).addToWdActivity((WdActivity)localObject1);
          setEditEnable(false);
          return;
        }
      }
      catch (Exception localException)
      {
        Log.e(tag, localException.getMessage(), localException);
        return;
      }
      int k = 0;
      continue;
      label337:
      String str1 = "Share_Private";
    }
  }
  
  public void addWdActivityTask(WdActivity paramWdActivity)
  {
    try
    {
      this.mWdFileManager.addTask(paramWdActivity);
      return;
    }
    catch (Exception paramWdActivity)
    {
      Log.e(tag, paramWdActivity.getMessage(), paramWdActivity);
    }
  }
  
  public void adjustTitleMaxWidth() {}
  
  public void back2Default()
  {
    int i = 0;
    while (i < this.mPageAdapter.getCount())
    {
      Fragment localFragment = this.mPageAdapter.getItem(i);
      if ((localFragment instanceof PhotoTabFragment)) {
        ((PhotoTabFragment)localFragment).cleanPhotoTabOpened();
      }
      i += 1;
    }
    if (!isPhone())
    {
      if (this.mFileFragment != null) {
        this.mFileFragment.cleanSelection();
      }
      replaceViewFlow();
    }
    updateRightPanelView();
  }
  
  public void back2Default4Search()
  {
    if ((this.mSearchView != null) && (this.mLapismSearchView.isShown()))
    {
      this.mSearchView.onBackClick();
      this.mSearchView.hiddenInputMethod();
      this.mLapismSearchView.close(true);
    }
  }
  
  public void castVideo(WdActivity paramWdActivity)
  {
    getWdFileManager().setCastVideoActivity(paramWdActivity);
    String str = paramWdActivity.streamUrl;
    if (!StringUtils.isEmpty(paramWdActivity.streamUrl)) {
      str = paramWdActivity.streamUrl.replaceFirst("https://", "http://");
    }
    this.mCastManager.sendVideo(paramWdActivity.name, str);
    if ((isLandscapePad()) || ((isPortraitPad()) && (!isLargePad())))
    {
      this.mMediaFragmentManager.showRightCastVideo(this);
      return;
    }
    if ((isPortraitPad()) && (isLargePad())) {
      back2Default();
    }
    startActivity(new Intent(this, CastVideoActivity.class));
  }
  
  public void changeEditTitleButtons(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    changeEditTitleButtons(paramInt, paramBoolean1, paramBoolean2, false);
  }
  
  public void changeEditTitleButtons(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (this.mEditFragment == null) {
      return;
    }
    this.mEditFragment.showDownload(paramBoolean1);
    this.mEditFragment.showShare(paramBoolean2);
    this.mEditFragment.showEmail(paramBoolean3);
    this.mEditFragment.showMenu(true);
    if (paramInt > 0)
    {
      this.mEditFragment.showTitle(true);
      this.mEditFragment.setTitle(getResources().getString(2131297251, new Object[] { Integer.valueOf(paramInt) }));
      return;
    }
    this.mEditFragment.showTitle(false);
    this.mEditFragment.showMenu(false);
  }
  
  public void changeMusicPlayerNotification(boolean paramBoolean)
  {
    if ((!isPhone()) && (this.mMediaFragmentManager.getMusicPlayFragment() != null)) {
      this.mMediaFragmentManager.getMusicPlayFragment().notificationFullScreen(paramBoolean);
    }
  }
  
  public void changeShadowBackground(int paramInt)
  {
    if ((getTabIndex() == 0) && (isTabCursorLayoutShow())) {}
  }
  
  public void checkAndRefreshShareFragment()
  {
    if ((isSharingCurrentFolder()) || (isSharingFromPhotoViewer()))
    {
      back2Default();
      return;
    }
    refreshShareFragment();
  }
  
  public void checkAndRequestPermission(int paramInt)
  {
    if (!this.mRequiredPermissions.contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
      this.mRequiredPermissions.add("android.permission.WRITE_EXTERNAL_STORAGE");
    }
    ActivityCompat.requestPermissions(this, (String[])this.mRequiredPermissions.toArray(new String[this.mRequiredPermissions.size()]), paramInt);
  }
  
  public void checkAndShare(final WdFile paramWdFile, final WdActivity paramWdActivity)
  {
    boolean bool = this.mWdFileManager.getConfiguration().showDataPlanWarn();
    if (paramWdFile.isDynamicVolume)
    {
      DialogUtils.makeConfirmDialogExt(this, getString(2131297470), getString(2131297469), null, null).show();
      return;
    }
    if ((this.mWdFileManager.isMobileNetwork()) && (paramWdFile.size > 10485760L) && (bool))
    {
      DialogUtils.makeConfirmDialogExt(this, getString(2131296786), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyDeviceActivity.this._checkAndShare(paramWdFile, paramWdActivity);
        }
      }, null).show();
      return;
    }
    _checkAndShare(paramWdFile, paramWdActivity);
  }
  
  public void checkAndUpdateClickabilityOfSpinner()
  {
    boolean bool = true;
    Spinner localSpinner;
    if ((this.mNavigationSpinner != null) && (this.mNavigationSpinner.getAdapter() != null))
    {
      localSpinner = this.mNavigationSpinner;
      if (this.mNavigationSpinner.getAdapter().getCount() <= 1) {
        break label46;
      }
    }
    for (;;)
    {
      localSpinner.setEnabled(bool);
      return;
      label46:
      bool = false;
    }
  }
  
  public void clearCheckList()
  {
    this.mFileFragment.reset();
  }
  
  public void clearCurrentAction()
  {
    this.mWdFileManager.setCurrentAction(null);
  }
  
  public void clearPlayList() {}
  
  public void closeDrawer()
  {
    if (this.mSlideDrawer != null) {
      this.mSlideDrawer.closeDrawer();
    }
  }
  
  public void closeSearchView()
  {
    try
    {
      this.mDrawerLayout.setDrawerLockMode(0);
      if ((this.mLapismSearchView != null) && (this.mLapismSearchView.isShown())) {
        this.mLapismSearchView.close(true);
      }
      this.mPageIndex = this.mLastPageIndex;
      this.mLastPageIndex = 0;
      if (this.mSearchView != null) {
        this.mSearchView.closeSearchView();
      }
      if (this.mPageIndex == 0) {
        this.mFileFragment.onResume();
      }
      for (;;)
      {
        ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return;
        if ((this.mPageIndex == 2) || (this.mPageIndex == 1) || (this.mPageIndex == 3)) {
          this.mMediaFragmentManager.getCurrentFragment().onPageChanged();
        }
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, "closeSearchView exception ", localException);
    }
  }
  
  public int compareVersions(String paramString1, String paramString2)
  {
    return StringUtils.compareVersions(paramString1, paramString2);
  }
  
  public void deleteFile()
  {
    int i = 0;
    Object localObject12 = new ArrayList();
    Object localObject13 = new ArrayList();
    int j = getTabIndex();
    switch (j)
    {
    }
    for (;;)
    {
      Object localObject7;
      Object localObject9;
      Object localObject8;
      Object localObject10;
      Object localObject11;
      Object localObject5;
      Object localObject4;
      Object localObject6;
      Object localObject3;
      try
      {
        DialogInterface.OnClickListener local21 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            for (;;)
            {
              try
              {
                switch (MyDeviceActivity.this.getTabIndex())
                {
                case 0: 
                  MyDeviceActivity.this.setEditEnable(false);
                  MyDeviceActivity.this.clearCurrentAction();
                  paramAnonymousDialogInterface.dismiss();
                  return;
                }
              }
              catch (Exception paramAnonymousDialogInterface)
              {
                Log.w(MyDeviceActivity.tag, "showConfirmation->ok", paramAnonymousDialogInterface);
                return;
              }
              MyDeviceActivity.this.getDeleteFilesLoader(this.val$copySelectedWdFile).execute(new Integer[0]);
              continue;
              new DeleteDownloadsLoader(MyDeviceActivity.this, this.val$finalCopySelectWdActivity).execute(new String[0]);
              continue;
              new DeleteWdActivitiesLoader(MyDeviceActivity.this, this.val$finalCopySelectWdActivity).execute(new String[0]);
            }
          }
        };
        DialogInterface.OnClickListener local22 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            try
            {
              MyDeviceActivity.this.setEditEnable(false);
              MyDeviceActivity.this.clearCurrentAction();
              paramAnonymousDialogInterface.dismiss();
              return;
            }
            catch (Exception paramAnonymousDialogInterface)
            {
              Log.w(MyDeviceActivity.tag, "showConfirmation->cancel", paramAnonymousDialogInterface);
            }
          }
        };
        localObject7 = null;
        localObject1 = null;
        localObject9 = null;
        localObject8 = null;
        localObject10 = null;
        localObject11 = new ArrayList();
        localObject5 = new ArrayList();
        localObject4 = new ArrayList();
        localObject6 = new ArrayList();
        localObject3 = null;
        if (i == 0) {
          break label1371;
        }
        switch (j)
        {
        case 0: 
          dismissDeleteDialog();
          this.mDeleteDialog = DialogUtils.makeConfirmDialogExt(this, getString(2131296550), (String)localObject1, local21, local22);
          this.mDeleteDialog.show();
          return;
        }
      }
      catch (Exception localException)
      {
        Object localObject1;
        Toast.makeText(this, localException.getMessage(), 0).show();
        return;
      }
      ((List)localObject12).addAll(mergerSelectedWdFiles());
      if (((List)localObject12).size() > 1)
      {
        i = 1;
        if (!((List)localObject12).isEmpty()) {}
      }
      else
      {
        i = 0;
        continue;
        ((List)localObject13).addAll(mergeSelectedLocals());
        if (((List)localObject13).size() > 1)
        {
          i = 1;
          if (!((List)localObject13).isEmpty()) {}
        }
        else
        {
          i = 0;
          continue;
          ((List)localObject13).addAll(getSelectedWdActivities());
          if (((List)localObject13).size() > 1)
          {
            i = 1;
            if (!((List)localObject13).isEmpty()) {}
          }
          else
          {
            i = 0;
            continue;
          }
          continue;
          localObject1 = getString(2131296622, new Object[] { ((WdFile)((List)localObject12).get(0)).getDevice().deviceName });
          continue;
          localObject1 = ((List)localObject13).iterator();
          if (((Iterator)localObject1).hasNext())
          {
            localObject12 = (WdActivity)((Iterator)localObject1).next();
            localObject13 = ((WdActivity)localObject12).getDevice().deviceName;
            if ((!((WdActivity)localObject12).getDevice().isOrionDevice()) || (((WdActivity)localObject12).status == -4))
            {
              if (!((List)localObject4).contains(((WdActivity)localObject12).name)) {
                ((List)localObject4).add(((WdActivity)localObject12).name);
              }
            }
            else if (StringUtils.isEquals("root", ((WdActivity)localObject12).parentId))
            {
              if (!((List)localObject5).contains(((WdActivity)localObject12).name)) {
                ((List)localObject5).add(((WdActivity)localObject12).name);
              }
              if (!((List)localObject11).contains(localObject13)) {
                ((List)localObject11).add(localObject13);
              }
            }
            else
            {
              if (!((List)localObject6).contains(((WdActivity)localObject12).name)) {
                ((List)localObject6).add(((WdActivity)localObject12).name);
              }
              localObject3 = this.mWdFileManager.getDatabaseAgent().getRootWdActivity(((WdActivity)localObject12).parentId);
            }
          }
          else
          {
            localObject11 = StringUtils.listToString((List)localObject11, "/");
            if (((List)localObject5).size() > 2)
            {
              localObject5 = (String)((List)localObject5).get(0) + "/" + (String)((List)localObject5).get(1) + "...";
              label662:
              if (((List)localObject4).size() <= 2) {
                break label1071;
              }
              localObject4 = (String)((List)localObject4).get(0) + "/" + (String)((List)localObject4).get(1) + "...";
              label725:
              if (((List)localObject6).size() <= 2) {
                break label1084;
              }
              localObject6 = (String)((List)localObject6).get(0) + "/" + (String)((List)localObject6).get(1) + "...";
              label788:
              localObject2 = localObject10;
              if (localObject4 != null)
              {
                localObject2 = localObject10;
                if (((String)localObject4).length() > 0) {
                  localObject2 = getString(2131296624, new Object[] { localObject4 });
                }
              }
              localObject4 = localObject9;
              if (localObject5 != null)
              {
                localObject4 = localObject9;
                if (((String)localObject5).length() > 0)
                {
                  localObject4 = localObject9;
                  if (localObject11 != null)
                  {
                    localObject4 = localObject9;
                    if (((String)localObject11).length() > 0) {
                      localObject4 = getString(2131297077, new Object[] { localObject5, localObject11 });
                    }
                  }
                }
              }
              localObject5 = localObject8;
              if (localObject6 != null)
              {
                localObject5 = localObject8;
                if (((String)localObject6).length() > 0)
                {
                  localObject5 = localObject8;
                  if (localObject3 != null) {
                    localObject5 = getString(2131296732, new Object[] { localObject6, ((WdActivity)localObject3).name });
                  }
                }
              }
              if ((localObject2 == null) || (((String)localObject2).length() <= 0)) {
                break label1097;
              }
              if ((localObject4 == null) || (((String)localObject4).length() <= 0)) {
                break label1359;
              }
              localObject3 = (String)localObject2 + "\n\n" + (String)localObject4;
            }
            for (;;)
            {
              localObject2 = localObject3;
              if (localObject5 == null) {
                break;
              }
              localObject2 = localObject3;
              if (((String)localObject5).length() <= 0) {
                break;
              }
              if ((localObject3 == null) || (((String)localObject3).length() <= 0)) {
                break label1365;
              }
              localObject2 = (String)localObject3 + "\n\n" + (String)localObject5;
              break;
              localObject5 = StringUtils.listToString((List)localObject5, "/");
              break label662;
              label1071:
              localObject4 = StringUtils.listToString((List)localObject4, "/");
              break label725;
              label1084:
              localObject6 = StringUtils.listToString((List)localObject6, "/");
              break label788;
              label1097:
              localObject3 = localObject7;
              if (localObject4 != null)
              {
                localObject3 = localObject7;
                if (((String)localObject4).length() > 0)
                {
                  localObject3 = localObject4;
                  continue;
                  localObject2 = getString(2131296623);
                  break;
                  localObject2 = getString(2131296621, new Object[] { ((WdFile)((List)localObject12).get(0)).getDevice().deviceName });
                  break;
                  if ((!((WdActivity)((List)localObject13).get(0)).getDevice().isOrionDevice()) || (((WdActivity)((List)localObject13).get(0)).status == -4))
                  {
                    localObject2 = getString(2131296620, new Object[] { ((WdActivity)((List)localObject13).get(0)).name });
                    break;
                  }
                  if (((WdActivity)((List)localObject13).get(0)).isRoot())
                  {
                    localObject2 = getString(2131297076, new Object[] { ((WdActivity)((List)localObject13).get(0)).getDevice().deviceName });
                    break;
                  }
                  localObject2 = (WdActivity)((List)localObject13).get(0);
                  localObject2 = getString(2131296731, new Object[] { this.mWdFileManager.getDatabaseAgent().getRootWdActivity(((WdActivity)localObject2).parentId).name });
                  break;
                  localObject2 = getString(2131296619);
                  break;
                  break;
                  label1359:
                  localObject3 = localObject2;
                }
              }
            }
            label1365:
            Object localObject2 = localObject5;
            continue;
            label1371:
            switch (j)
            {
            }
          }
        }
      }
    }
  }
  
  public void dismissUploadContextMenu()
  {
    if (this.mUploadContextMenu != null) {
      this.mUploadContextMenu.dismiss();
    }
    this.mUploadContextMenu = null;
  }
  
  public void doDeleteDevice(final Device paramDevice)
  {
    if (paramDevice != null) {}
    try
    {
      this.mDeleteDeviceDialog = DialogUtils.makeConfirmDialogExt(this, getString(2131296330), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyDeviceActivity.this.getSelectedDeviceList().remove(paramDevice);
          MyDeviceActivity.this.getDeviceDeleteLoader().execute(new Device[] { paramDevice });
        }
      }, null);
      this.mDeleteDeviceDialog.dismiss();
      this.mDeleteDeviceDialog.show();
      return;
    }
    catch (Exception paramDevice)
    {
      Log.e(tag, "doDeleteDevice exception  ", paramDevice);
    }
  }
  
  public void doOpenFile(WdActivity paramWdActivity)
  {
    WdActivity localWdActivity = new WdActivity();
    localWdActivity.deviceId = paramWdActivity.deviceId;
    localWdActivity.fullPath = paramWdActivity.fullPath;
    localWdActivity.downloadPath = paramWdActivity.downloadPath;
    localWdActivity.name = paramWdActivity.name;
    localWdActivity.size = paramWdActivity.size;
    localWdActivity.downloadSize = paramWdActivity.size;
    localWdActivity.createdDate = paramWdActivity.createdDate;
    localWdActivity.modifiedDate = paramWdActivity.modifiedDate;
    localWdActivity.isFolder = paramWdActivity.isFolder;
    localWdActivity.streamUrl = paramWdActivity.streamUrl;
    localWdActivity.status = 0;
    localWdActivity.downloadStatus = 0;
    localWdActivity.setDevice(paramWdActivity.getDevice());
    localWdActivity.mDatabaseAgent = this.mWdFileManager.getDatabaseAgent();
    localWdActivity.activityType = "View";
    localWdActivity.parentId = "root";
    localWdActivity.parentObjectId = paramWdActivity.parentObjectId;
    localWdActivity.objectId = paramWdActivity.objectId;
    localWdActivity.uploadPathObjectId = paramWdActivity.uploadPathObjectId;
    localWdActivity.id = localWdActivity.getId();
    this.mWdFileManager.loadWdFileForFlurry(paramWdActivity.getWdFile());
    if (this.mShareWithMeFragment != null) {
      this.mShareWithMeFragment.dismissWindow();
    }
    String str = MimeTypeUtils.getMimeType(paramWdActivity.fullPath);
    Uri localUri = Uri.fromFile(paramWdActivity.getDownloadedFile());
    if ((MimeTypeUtils.isImage(str)) && ((MimeTypeUtils.isAndroidBuildInImageType(paramWdActivity.fullPath)) || (MimeTypeUtils.isMediaCrawlerSupportedRawImageType(paramWdActivity.fullPath))))
    {
      if ((isLandscapePad()) || ((isPortraitPad()) && (!isLargePad())))
      {
        paramWdActivity = getMediaFragmentManager().getMediaFragment(1);
        if (paramWdActivity != null) {
          paramWdActivity.cleanPhotoTabOpened();
        }
        this.mMediaFragmentManager.showRightPhoto(this, localUri, str, PhotoViewerActivity.UID, this.mFileFragment.getCurrentKeyForSelectionVerfiy(1));
      }
      for (;;)
      {
        clearCurrentAction();
        return;
        if ((isPortraitPad()) && (isLargePad())) {
          back2Default();
        }
        paramWdActivity = new Intent();
        paramWdActivity.setDataAndType(localUri, str);
        paramWdActivity.setClass(this, PhotoViewerActivity.class);
        paramWdActivity.putExtra("PhotoBrowserActivity.uid", PhotoViewerActivity.UID);
        startActivityForResult(paramWdActivity, 1);
      }
    }
    if (MimeTypeUtils.isAudio(str))
    {
      if (((isJellyBeanOrAbove()) && (MimeTypeUtils.isJellyBeanOrAboveSupportAudioType(paramWdActivity.downloadPath))) || (StringUtils.isEquals(FileUtils.getExtName(paramWdActivity.fullPath).toLowerCase(Locale.getDefault()), "mp3")))
      {
        if ((isLandscapePad()) || ((isPortraitPad()) && (!isLargePad()))) {
          this.mMediaFragmentManager.showRightMusic(this, this.mFileFragment.getCurrentKeyForSelectionVerfiy(2));
        }
        for (;;)
        {
          clearCurrentAction();
          return;
          if ((isPortraitPad()) && (isLargePad())) {
            back2Default();
          }
          this.mMediaFragmentManager.showMusicActivity(this, this.mFileFragment.getCurrentKeyForSelectionVerfiy(2));
        }
      }
      this.mWdFileManager.setFileOpening(paramWdActivity);
      this.mWdFileManager.getWdFileSystem(paramWdActivity.getDevice()).addToWdActivity(localWdActivity);
      openWithIntent(localWdActivity);
      return;
    }
    this.mWdFileManager.setFileOpening(paramWdActivity);
    this.mWdFileManager.getWdFileSystem(paramWdActivity.getDevice()).addToWdActivity(localWdActivity);
    openWithIntent(localWdActivity);
  }
  
  public void doOpenWith(WdActivity paramWdActivity)
  {
    WdActivity localWdActivity = new WdActivity();
    localWdActivity.deviceId = paramWdActivity.deviceId;
    localWdActivity.fullPath = paramWdActivity.fullPath;
    localWdActivity.downloadPath = paramWdActivity.downloadPath;
    localWdActivity.name = paramWdActivity.name;
    localWdActivity.size = paramWdActivity.size;
    localWdActivity.downloadSize = paramWdActivity.size;
    localWdActivity.createdDate = paramWdActivity.createdDate;
    localWdActivity.modifiedDate = paramWdActivity.modifiedDate;
    localWdActivity.isFolder = paramWdActivity.isFolder;
    localWdActivity.status = 0;
    localWdActivity.downloadStatus = 0;
    localWdActivity.setDevice(paramWdActivity.getDevice());
    localWdActivity.mDatabaseAgent = this.mWdFileManager.getDatabaseAgent();
    localWdActivity.activityType = "View";
    localWdActivity.parentId = "root";
    localWdActivity.id = localWdActivity.getId();
    this.mWdFileManager.getWdFileSystem(paramWdActivity.getDevice()).addToWdActivity(localWdActivity);
    this.mWdFileManager.loadWdFileForFlurry(paramWdActivity.getWdFile());
    String str = MimeTypeUtils.getMimeType(paramWdActivity.fullPath);
    new ArrayList().add(localWdActivity);
    if (MimeTypeUtils.isMediaType(str)) {}
    this.mWdFileManager.setFileOpening(paramWdActivity);
    openWithIntent(localWdActivity);
    setEditEnable(false);
  }
  
  public void doOperation(int paramInt)
  {
    int i;
    try
    {
      if ((getCurrentDevice() == null) || (!getCurrentDevice().isSDCard())) {
        break label1564;
      }
      i = 1;
      this.mFromOtherApp.set(false);
      this.mIsListBusy = false;
      if ((!this.mWdFileManager.getNetworkManager().hasConnectivity()) && (paramInt != 6) && (i == 0)) {
        if (getTabIndex() == 0)
        {
          if ((paramInt != 0) && (paramInt != 5) && (paramInt != 6)) {
            showResponseError(new ResponseException(668));
          }
        }
        else if (paramInt == 0)
        {
          showResponseError(new ResponseException(668));
          return;
        }
      }
    }
    catch (Exception localException)
    {
      Log.e(tag, "doOperation operationID: " + paramInt + " tabIndex: " + getTabIndex());
      return;
    }
    label484:
    Object localObject2;
    Object localObject1;
    switch (paramInt)
    {
    case 2: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 18: 
    case 19: 
    case 24: 
    case 25: 
    case 26: 
    case 27: 
    case 28: 
    case 29: 
    case 32: 
    default: 
      return;
    case 0: 
      changeMusicPlayerNotification(true);
      startActivityForResult(new Intent(this, AddDeviceActivity.class), 11);
      overridePendingTransition(2131034173, 2131034151);
      return;
    case 31: 
      if (isPermissionsCheckDone(772))
      {
        startDownloadFile();
        return;
      }
      break;
    case 38: 
      if (isPermissionsCheckDone(775))
      {
        startMoveFile(717);
        return;
      }
      break;
    case 39: 
      startMoveFile(664);
      return;
    case 44: 
      if ((getCurrentDevice() != null) && (!getCurrentDevice().isSDCard()))
      {
        showExportDialog();
        return;
      }
      break;
    case 37: 
      i = 0;
      switch (getTabIndex())
      {
      case 0: 
      case 5: 
        while (i > 100)
        {
          DialogUtils.makeConfirmDialogExt(this, getString(2131297497, new Object[] { Integer.valueOf(100) }), new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              MyDeviceActivity.this.clearCurrentAction();
              MyDeviceActivity.this.deleteFile();
            }
          }, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              MyDeviceActivity.this.setEditEnable(false);
            }
          }).show();
          return;
          i = mergerSelectedWdFiles().size();
          continue;
          i = mergeSelectedLocals().size();
        }
        clearCurrentAction();
        deleteFile();
        return;
      }
    case 34: 
      localObject2 = null;
      if (getTabIndex() == 0)
      {
        localObject1 = localObject2;
        if (getSelectedWdFiles().size() > 0) {
          localObject1 = ((WdFile)getSelectedWdFiles().get(0)).getWdActivity();
        }
      }
    case 1: 
    case 6: 
    case 21: 
    case 20: 
    case 23: 
    case 22: 
    case 4: 
    case 5: 
    case 3: 
    case 33: 
    case 30: 
    case 35: 
    case 36: 
    case 7: 
    case 8: 
    case 10: 
    case 40: 
      while (localObject1 != null)
      {
        doRename((WdActivity)localObject1, null);
        return;
        localObject1 = localObject2;
        if (getTabIndex() == 5)
        {
          List localList = mergeSelectedLocals();
          localObject1 = localObject2;
          if (localList != null)
          {
            localObject1 = localObject2;
            if (!localList.isEmpty())
            {
              localObject1 = (WdActivity)localList.get(0);
              continue;
              changeMusicPlayerNotification(true);
              if ((getCurrentDevice() != null) && (getCurrentDevice().isAvatarDevice()))
              {
                new GetCurrentWiFiConnectionLoader(this).execute(new Integer[0]);
                return;
              }
              loadTipsInBrowser();
              return;
              changeMusicPlayerNotification(true);
              startActivityForResult(new Intent(this, SettingPreferenceActivity.class), 0);
              return;
              this.mFileFragment.reSort(21);
              return;
              this.mFileFragment.reSort(20);
              return;
              this.mFileFragment.reSort(23);
              return;
              this.mFileFragment.reSort(22);
              return;
              if (isPermissionsCheckDone(771))
              {
                showUploadContextMenu();
                return;
                newFolder(null);
                return;
                if (isPermissionsCheckDone(781))
                {
                  showAddFileDialog();
                  return;
                  changeMusicPlayerNotification(true);
                  openFile();
                  return;
                  changeMusicPlayerNotification(true);
                  if (isAccessLocalResource())
                  {
                    DialogUtils.error(this, null, getString(2131296421), null);
                    return;
                  }
                  shareFile();
                  return;
                  if (isPermissionsCheckDone(773))
                  {
                    saveAs();
                    return;
                    if (getEditEnable())
                    {
                      showProperty(false);
                      setEditEnable(false);
                      return;
                    }
                    this.mMediaFragmentManager.checkAndToggleImageInfo();
                    return;
                    showOperationTips();
                    return;
                    if ((DeviceManager.getInstance().getHostDevice().isAvatarDevice()) || (DeviceManager.getInstance().getHostDevice().isKorraDevice())) {}
                    for (localObject1 = new Intent(this, AvatarSettingsActivity.class);; localObject1 = new Intent(this, DeviceSettingActivity.class))
                    {
                      startActivityForResult((Intent)localObject1, 12);
                      overridePendingTransition(2131034173, 2131034175);
                      changeMusicPlayerNotification(true);
                      return;
                    }
                    if (getTabIndex() == 6)
                    {
                      clearCurrentAction();
                      new ClearAllWdActivitiesLoader(this).execute(new String[0]);
                      return;
                      setEditEnable(true);
                      getSelectedWdFiles().clear();
                      localObject1 = this.mMediaFragmentManager.getCurrentFragment();
                      if (getTabIndex() == 0) {
                        switch (getCurrentPageIndex())
                        {
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      getFolderListFragment().changeEditTitleButtons();
      return;
      getSelectedWdFiles().addAll(this.mFileFragment.getFileSystemAdapter().getWdFiles());
      getFolderListFragment().notifyDataSetChange();
      continue;
      if (localObject1 != null)
      {
        getSelectedWdFiles().addAll(((AbstractFragment)localObject1).getWdFiles());
        ((AbstractFragment)localObject1).notifyAdapter();
        continue;
        getSelectedWdFiles().clear();
        setEditEnable(true);
        changeEditTitleButtons(getSelectedWdFiles().size(), false, false);
        return;
        if (!isDemo())
        {
          if (!haveImagesInDCIM())
          {
            Toast.makeText(this, 2131297089, 1).show();
            return;
          }
          this.mWdFileManager.getCurrentList().set(939);
          showMoveFileWindow(447, true);
          return;
          if (!isDemo())
          {
            if (!checkIsHaveMusicFileInMediaStore())
            {
              Toast.makeText(this, 2131297080, 1).show();
              return;
            }
            this.mWdFileManager.getCurrentList().set(949);
            showMoveFileWindow(448, true);
            return;
            if (!isDemo())
            {
              if (!checkSDcardAvabile())
              {
                Toast.makeText(this, 2131296422, 1).show();
                return;
              }
              this.mWdFileManager.getCurrentList().set(959);
              this.mWdFileManager.setCurrentSDCardFullPath(null);
              localObject1 = this.mWdFileManager.getDeviceById("SDCard");
              DeviceManager.getInstance().setGuestDevice((Device)localObject1);
              showMoveFileWindow(449, true);
              return;
              changeMusicPlayerNotification(true);
              if (this.mWdFileManager.getAccounts() == null)
              {
                DialogUtils.alert(this, null, getString(2131297054), null);
                return;
              }
              if (getTabIndex() == 0) {
                getEMailFilesLoader(mergerSelectedWdFiles()).execute(new WdFile[0]);
              }
              for (;;)
              {
                setCurrentAction(null, 45);
                return;
                if (getTabIndex() == 5)
                {
                  localObject1 = new ArrayList();
                  localObject2 = mergeSelectedLocals();
                  if ((localObject2 != null) && (((List)localObject2).size() > 0))
                  {
                    localObject2 = ((List)localObject2).iterator();
                    while (((Iterator)localObject2).hasNext()) {
                      ((List)localObject1).add(((WdActivity)((Iterator)localObject2).next()).getWdFile());
                    }
                  }
                  getEMailFilesLoader((List)localObject1).execute(new WdFile[0]);
                }
              }
              if ((DeviceManager.getInstance().getHostDevice().isAvatarDevice()) || (DeviceManager.getInstance().getHostDevice().isKorraDevice()))
              {
                localObject1 = new Intent(this, AvatarSettingsActivity.class);
                ((Intent)localObject1).putExtra("com.wdc.wd2go.extra.EXTRA_WIFI_PAGE", true);
                startActivityForResult((Intent)localObject1, 16);
                overridePendingTransition(2131034173, 2131034175);
              }
            }
          }
        }
        return;
        label1564:
        i = 0;
        break;
        break label484;
      }
    }
  }
  
  public void doRefresh()
  {
    if (this.mIsRefreshing) {
      return;
    }
    this.mIsRefreshing = true;
    this.mStart = -937.0F;
  }
  
  public void doRename(WdActivity paramWdActivity, String paramString)
  {
    setCurrentAction(paramWdActivity, 34);
    this.mRenameDialog = new RenameDialog(this, paramWdActivity, paramString);
    if ((this.mRenameDialog != null) && (this.mRenameDialog.getDialog() != null) && (!this.mRenameDialog.getDialog().isShowing())) {
      this.mRenameDialog.getDialog().show();
    }
    setEditEnable(false);
  }
  
  public void doSaveAs(String paramString)
  {
    List localList2 = null;
    List localList1 = null;
    for (;;)
    {
      try
      {
        switch (getTabIndex())
        {
        case 0: 
          if (((localList2 == null) || (localList2.isEmpty())) && ((localList1 == null) || (localList1.isEmpty()))) {
            return;
          }
          new SaveAsLoader(this, localList1, localList2).execute(new String[] { paramString });
          clearCurrentAction();
          return;
        }
      }
      catch (Exception paramString)
      {
        Log.i(tag, paramString.getMessage(), paramString);
        return;
      }
      localList2 = mergerSelectedWdFiles();
      continue;
      localList1 = mergeSelectedLocals();
    }
  }
  
  public void doSelect(WdFile paramWdFile, View paramView)
  {
    if (this.mFileFragment != null) {
      this.mFileFragment.doSelect(paramWdFile, paramView);
    }
    refreshShareFragment();
  }
  
  public void doShareFile(WdActivity paramWdActivity)
  {
    boolean bool3 = false;
    boolean bool1;
    boolean bool2;
    if (getEditEnable()) {
      if ((!getSelectedWdFiles().isEmpty()) && (getSelectedWdFiles().size() == 1) && (getSelectedWdFiles().get(0) != null) && (((WdFile)getSelectedWdFiles().get(0)).isFolder))
      {
        bool1 = true;
        paramWdActivity = new ArrayList(getSelectedWdFiles());
        bool2 = bool1;
        bool1 = bool3;
      }
    }
    for (;;)
    {
      new ShareDialog(this, this, paramWdActivity, bool1, bool2, false).showDialog();
      return;
      bool1 = false;
      break;
      bool1 = true;
      bool2 = true;
      getWdFileManager().setSharePhotoFile(paramWdActivity.getWdFile());
      paramWdActivity = Arrays.asList(new WdFile[] { paramWdActivity.getWdFile() });
    }
  }
  
  public void enableBreadcrumbButton(boolean paramBoolean) {}
  
  public void enableMediaBreadcrumb(boolean paramBoolean)
  {
    if (this.mMediaBreadcrumbListAdapter != null)
    {
      this.mNavigationSpinner.setAdapter(this.mMediaBreadcrumbListAdapter);
      this.mNavigationSpinner.setOnItemSelectedListener(this.mMediaBreadcrumbListAdapter);
      this.mMediaBreadcrumbListAdapter.enableBreadcrumbButton(paramBoolean);
    }
  }
  
  public void enableViewPageScroll(boolean paramBoolean)
  {
    this.mViewPager.setScrollable(paramBoolean);
  }
  
  public void generatePlayList(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    if (this.mViewPager.getCurrentItem() == 0) {
      this.mFileFragment.generatePlayList(paramString1, paramString2, paramInt1);
    }
    while (this.mViewPager.getCurrentItem() != 4) {
      return;
    }
    this.mShareWithMeFragment.generatePlayList(paramString1, paramString2, paramInt1);
  }
  
  public void generateSinglePlayList(List<WdActivity> paramList, String paramString1, String paramString2)
  {
    if ((paramList == null) || (paramList.size() != 1)) {}
    String str;
    do
    {
      return;
      str = FileUtils.getParent(paramString1);
      clearPlayList();
    } while (!MimeTypeUtils.isMediaType(paramString2));
    paramString1 = new MediaList(this.mWdFileManager, MimeTypeUtils.getOpenType(paramString2), this);
    paramList = new CloudMediaData((WdActivity)paramList.get(0), paramString2, (WdFilesApplication)getApplication());
    paramString2 = new ArrayList();
    paramString2.add(paramList);
    paramString1.setList(paramString2);
    paramString1.setCurrentIndex(paramList);
    this.mWdFileManager.getDatabaseAgent().clearMusicInfo();
    paramString1.setListPath(str);
    paramString1.setTabIndex(getTabIndex());
    paramList = this.mApplication;
    if (paramString1.getCurrentMediaType() == 12) {}
    for (boolean bool = true;; bool = false)
    {
      paramList.setMediaList(paramString1, bool);
      paramString1.showList();
      return;
    }
  }
  
  public List<WdActivity> getAssignTaskList()
  {
    return this.mWdFileManager.getAssignTaskList();
  }
  
  public MoveAcrossDevicesLoader getCopyAcrossDevicesLoader()
  {
    return new MoveAcrossDevicesLoader(this, mergerSelectedWdFiles(), "Copy");
  }
  
  public CopyFilesLoader getCopyFilesLoader()
  {
    return new CopyFilesLoader(this, mergerSelectedWdFiles());
  }
  
  public ActionDoingSet getCurrentAction()
  {
    return this.mWdFileManager.getCurrentAction();
  }
  
  public Device getCurrentDevice()
  {
    return this.mDevice;
  }
  
  public Device[] getCurrentMediaDevice()
  {
    if (this.mMediaFragmentManager != null) {
      return this.mMediaFragmentManager.getMediaDevice();
    }
    return null;
  }
  
  public int getCurrentPageIndex()
  {
    return this.mPageIndex;
  }
  
  public int getCurrentTabIndex()
  {
    if (this.mViewPager != null) {
      return this.mViewPager.getCurrentItem();
    }
    return 0;
  }
  
  public DeviceListAdapter getDeviceListAdapter()
  {
    return this.mFileFragment.getDeviceListAdapter();
  }
  
  public DeviceListLoader getDeviceListLoader()
  {
    return new DeviceListLoader(this);
  }
  
  public DeviceLoginLoader getDeviceLoginLoader()
  {
    return new DeviceLoginLoader(this, this.mLoginListener, 2131296979);
  }
  
  public EMailFilesLoader getEMailFilesLoader(List<WdFile> paramList)
  {
    return new EMailFilesLoader(this, paramList);
  }
  
  public boolean getEditEnable()
  {
    return this.isEditMode;
  }
  
  public Dialog getFirmwareAlert(final Device paramDevice, int paramInt)
  {
    if ((paramDevice == null) || (StringUtils.isEmpty(paramDevice.deviceName))) {
      return null;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(2130838050);
    localBuilder.setTitle(paramDevice.deviceName + " " + getString(2131297116));
    View localView = ((LayoutInflater)getBaseContext().getSystemService("layout_inflater")).inflate(2130968824, null);
    ((TextView)localView.findViewById(2131821667)).setText(paramInt);
    final CheckBox localCheckBox = (CheckBox)localView.findViewById(2131821668);
    localBuilder.setPositiveButton(getString(2131297104), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          paramAnonymousDialogInterface = MyDeviceActivity.this.mWdFileManager.getDatabaseAgent();
          if (paramAnonymousDialogInterface != null)
          {
            DeviceUpgradeInfo localDeviceUpgradeInfo = paramAnonymousDialogInterface.getDeviceUpgradeInfoById(paramDevice.getId());
            if (localDeviceUpgradeInfo == null)
            {
              localDeviceUpgradeInfo = new DeviceUpgradeInfo(paramDevice.id, paramDevice.deviceName, System.currentTimeMillis(), paramDevice.firmwareVersion);
              localDeviceUpgradeInfo.setDeclineCheck(localCheckBox.isChecked());
              paramAnonymousDialogInterface.insert(localDeviceUpgradeInfo);
              return;
            }
            localDeviceUpgradeInfo.setDeclineCheck(localCheckBox.isChecked());
            localDeviceUpgradeInfo.setFirmwareVersion(paramDevice.firmwareVersion);
            localDeviceUpgradeInfo.setLastCheckTime(System.currentTimeMillis());
            paramAnonymousDialogInterface.updateDeviceUpgradeInfo(localDeviceUpgradeInfo);
            return;
          }
        }
        catch (Exception paramAnonymousDialogInterface)
        {
          Log.e(MyDeviceActivity.tag, "declineUpgradeFirwmare exception ", paramAnonymousDialogInterface);
        }
      }
    });
    localBuilder.setView(localView);
    this.mDeviceVersionDialog = localBuilder.create();
    this.mDeviceVersionDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        MyDeviceActivity.access$2002(MyDeviceActivity.this, null);
        MyDeviceActivity.access$2102(MyDeviceActivity.this, null);
      }
    });
    return this.mDeviceVersionDialog;
  }
  
  public FolderListFragment getFolderListFragment()
  {
    return this.mFileFragment;
  }
  
  public View getItemView(String paramString)
  {
    if (this.mViewPager.getCurrentItem() == 0) {
      return this.mFileFragment.getItemView(paramString);
    }
    return null;
  }
  
  public ListView getListView()
  {
    if (this.mViewPager.getCurrentItem() == 0) {
      return this.mFileFragment.getListView();
    }
    return null;
  }
  
  public MediaFragmentManager getMediaFragmentManager()
  {
    return this.mMediaFragmentManager;
  }
  
  public MoveAcrossDevicesLoader getMoveAcrossDevicesLoader()
  {
    return new MoveAcrossDevicesLoader(this, mergerSelectedWdFiles(), "Cut");
  }
  
  public WdFileSystem getMoveFileSystem()
  {
    try
    {
      this.mMoveFileSystem = this.mWdFileManager.getMoveFileSystem(DeviceManager.getInstance().getGuestDevice());
      WdFileSystem localWdFileSystem = this.mMoveFileSystem;
      return localWdFileSystem;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public MoveFileWindow getMoveFileWindow()
  {
    return this.mMoveFileWindow;
  }
  
  public MoveFilesLoader getMoveFilesLoader()
  {
    return new MoveFilesLoader(this, mergerSelectedWdFiles());
  }
  
  public OpenFileLoader getOpenFileLoader(int paramInt)
  {
    return new OpenFileLoader(this, getString(paramInt), getTabIndex());
  }
  
  public OpenFileLoader getOpenFileLoader(String paramString, int paramInt)
  {
    return new OpenFileLoader(this, paramString, paramInt);
  }
  
  public OpenLocalFolderLoader getOpenLocalFolderLoader(boolean paramBoolean)
  {
    return new OpenLocalFolderLoader(this, paramBoolean);
  }
  
  public WdActivity getSDCardFolder(String paramString)
  {
    String str1 = null;
    String str2 = paramString;
    if (paramString == null)
    {
      str2 = Environment.getExternalStorageDirectory().getAbsolutePath();
      str1 = this.mResources.getString(2131297465);
    }
    paramString = new WdActivity(str2, new File(str2), "Upload", 0);
    paramString.isFolder = true;
    paramString.mDatabaseAgent = this.mWdFileManager.getDatabaseAgent();
    paramString.setDevice(this.mWdFileManager.getDatabaseAgent().getDeviceById("Local"));
    if (str1 != null) {
      paramString.name = str1;
    }
    return paramString;
  }
  
  public com.lapism.searchview.SearchView getSearchView()
  {
    return this.mLapismSearchView;
  }
  
  public List<Device> getSelectedDeviceList()
  {
    return this.mFileFragment.getSelectDevices();
  }
  
  public List<WdActivity> getSelectedLocals()
  {
    return this.mWdFileManager.getSelectedLocals();
  }
  
  public List<WdActivity> getSelectedSDCardFiles()
  {
    return this.mWdFileManager.getSelectedSDCardFiles();
  }
  
  public List<WdActivity> getSelectedWdActivities()
  {
    return this.mWdFileManager.getSelectedWdActivities();
  }
  
  public List<WdFile> getSelectedWdFiles()
  {
    return this.mWdFileManager.getSelectedWdFiles();
  }
  
  public int getTabIndex()
  {
    if (this.mFileFragment != null) {
      return this.mFileFragment.getTabIndex();
    }
    return 0;
  }
  
  public View getTitleBarLine()
  {
    return findViewById(2131821901);
  }
  
  public UploadFilesLoader getUploadFilesLoader(int paramInt)
  {
    if ((getCurrentDevice() != null) && (getCurrentDevice().isSDCard()))
    {
      localObject = mergerSelectedWdFiles();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = ((List)localObject).iterator();
      for (;;)
      {
        localObject = localArrayList;
        if (!localIterator.hasNext()) {
          break;
        }
        localArrayList.add(((WdFile)localIterator.next()).getWdActivity());
      }
    }
    Object localObject = mergeSelectedLocals();
    return new UploadFilesLoader(this, (List)localObject, paramInt);
  }
  
  public WdActivityFolderLoader getWdActivityFolderLoader(boolean paramBoolean)
  {
    return new WdActivityFolderLoader(this, paramBoolean);
  }
  
  public WdFileSystem getWdFileSystem()
  {
    try
    {
      this.mWdFileSystem = this.mWdFileManager.getWdFileSystem(null);
      WdFileSystem localWdFileSystem = this.mWdFileSystem;
      return localWdFileSystem;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void goToMusicList()
  {
    try
    {
      this.mViewPager.setCurrentItem(2, false);
      this.mPageIndex = 2;
      return;
    }
    catch (Exception localException)
    {
      Log.i(tag, localException.getMessage(), localException);
    }
  }
  
  public void gotoDeviceList()
  {
    try
    {
      this.mViewPager.setCurrentItem(0, false);
      this.mFileFragment.gotoDeviceList();
      this.mPageIndex = 0;
      return;
    }
    catch (Exception localException)
    {
      Log.i(tag, localException.getMessage(), localException);
    }
  }
  
  public void gotoParent()
  {
    this.mFileFragment.gotoParent(false);
  }
  
  public void gotoShareFolder()
  {
    try
    {
      stopRefresh();
      setEditEnable(false);
      WdFileSystem localWdFileSystem = getWdFileSystem();
      WdFile localWdFile = localWdFileSystem.getCurrentFolder();
      if ((localWdFile == null) || (!localWdFile.isRoot()))
      {
        localWdFile = getWdFileSystem().getDevice().getRootFile();
        localWdFileSystem.setCurrentFolder(localWdFile);
        localWdFileSystem.getBreadcrumDataForCloudTab().clear();
        localWdFileSystem.buildOrUpdateBreadcrumbForCloudTab(localWdFile, this);
        updateBreadcrumbAdapterData();
        this.mFileFragment.setBreadscrumb();
        loadCloudFileSystem(false, false, true, new WdFile[] { localWdFile, localWdFile });
      }
      openDrawer();
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, "gotoShareFolder exception ", localException);
    }
  }
  
  public void hideBackingUpStatusPanel()
  {
    if ((this.mBackingUpStatusPanel != null) && (this.mBackingUpStatusPanel.getVisibility() == 0)) {}
    for (int i = 1;; i = 0)
    {
      setVisibility(this.mBackingUpStatusPanel, 8);
      if (i != 0) {
        this.mBackingUpStatusPanel.startAnimation(this.mBackingUpOut);
      }
      return;
    }
  }
  
  public void hidePopupMenu()
  {
    try
    {
      switch (getTabIndex())
      {
      case 0: 
        if ((getEditEnable()) || (this.mPageIndex == 0))
        {
          this.mFileFragment.hidePopupMenu();
          return;
        }
        break;
      }
    }
    catch (Exception localException)
    {
      Log.i(tag, localException.getMessage(), localException);
      return;
    }
    this.mMediaFragmentManager.showPopupMenu(false);
    return;
    this.mFileFragment.hidePopupMenu();
    return;
  }
  
  public void initMediaTab()
  {
    this.mMediaFragmentManager.getMediaFragment(1).loadData(true);
    this.mMediaFragmentManager.getMediaFragment(2).loadData(true);
    this.mMediaFragmentManager.getMediaFragment(3).loadData(true);
  }
  
  public void initSearchView()
  {
    if (this.mSearchView == null) {
      this.mSearchView = new com.wdc.wd2go.ui.widget.SearchView(this.mSearchViewLayout, this, null);
    }
  }
  
  protected void initTabCursorWidth()
  {
    int j = getResources().getDisplayMetrics().widthPixels;
    int i = 4;
    if (!isPhone())
    {
      if (!isLandscapePad()) {
        break label38;
      }
      i = 12;
    }
    for (;;)
    {
      this.mCursorWidth = (j / i);
      return;
      label38:
      if (isLargePad()) {
        i = 4;
      } else {
        i = 10;
      }
    }
  }
  
  void initViewPager()
  {
    this.mFileFragment = new FolderListFragment();
    this.mMediaFragmentManager = new MediaFragmentManager(this);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.mFileFragment);
    localArrayList.add(this.mMediaFragmentManager.getMediaFragment(1));
    localArrayList.add(this.mMediaFragmentManager.getMediaFragment(2));
    localArrayList.add(this.mMediaFragmentManager.getMediaFragment(3));
    if (this.mMainLayout != null)
    {
      this.mViewPager = ((CustomViewPager)this.mMainLayout.findViewById(2131821608));
      this.mTabLayout = ((TabLayout)this.mMainLayout.findViewById(2131821607));
      this.mAppBarLayout = ((AppBarLayout)this.mMainLayout.findViewById(2131821606));
      this.mCoordinatorLayout = ((CoordinatorLayout)this.mMainLayout.findViewById(2131821605));
      initTabCursorWidth();
    }
    this.mPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), localArrayList);
    this.mViewPager.setAdapter(this.mPageAdapter);
    this.mViewPager.setOffscreenPageLimit(4);
    this.mViewPager.setOnPageChangeListener(this.onPageChangeListener);
    this.mViewPager.setScrollable(false);
    this.mTabLayout.setupWithViewPager(this.mViewPager);
    int j = 0;
    if (j < this.mTabLayout.getTabCount())
    {
      int i = 2130838739;
      if (j == 1) {
        i = 2130838743;
      }
      for (;;)
      {
        this.mTabLayout.getTabAt(j).setIcon(i);
        j += 1;
        break;
        if (j == 2) {
          i = 2130838741;
        } else if (j == 3) {
          i = 2130838746;
        }
      }
    }
    this.mBackingUpStatusPanel = findViewById(2131821604);
    this.mBackingUpLayout = findViewById(2131821200);
    this.mBackingCompleteLayout = findViewById(2131821201);
    this.mBackingUpIn = AnimationUtils.loadAnimation(this, 2131034165);
    this.mBackingUpOut = AnimationUtils.loadAnimation(this, 2131034167);
  }
  
  public boolean isAccessLocalResource()
  {
    boolean bool2 = true;
    if ((getCurrentDevice() != null) && (getCurrentDevice().isSDCard())) {
      return false;
    }
    if (this.mWdFileManager != null)
    {
      bool1 = bool2;
      if (this.mWdFileManager.getLoginStatus() != 1) {
        if (this.mNetworkConnected.get()) {
          break label57;
        }
      }
    }
    label57:
    for (boolean bool1 = bool2;; bool1 = false) {
      return bool1;
    }
  }
  
  public boolean isAllSelect()
  {
    boolean bool = false;
    if (this.mViewPager.getCurrentItem() == 0) {
      bool = this.mFileFragment.isAllSelect();
    }
    return bool;
  }
  
  public boolean isDemo()
  {
    return this.mApplication.mIsDemo.get();
  }
  
  public boolean isDeviceListShow()
  {
    return this.mFileFragment.isDeviceListShow();
  }
  
  public boolean isDeviceSelected(Device paramDevice)
  {
    return this.mFileFragment.isDeviceSelect(paramDevice);
  }
  
  public boolean isDownLoadWorking()
  {
    return this.mWdFileManager.isDownLoading();
  }
  
  public boolean isDrawerOpen()
  {
    return this.mSlideDrawer.isDrawerOpen();
  }
  
  public boolean isFileDownloadSuccessful(WdFile paramWdFile)
  {
    paramWdFile = paramWdFile.getWdActivityDownload();
    return (paramWdFile != null) && (paramWdFile.status != -3) && (paramWdFile.status != -1) && (paramWdFile.status != -6);
  }
  
  public boolean isListBusy()
  {
    return this.mIsListBusy;
  }
  
  public boolean isLocalSelect(WdActivity paramWdActivity)
  {
    if ((getSelectedLocals() == null) || (getSelectedLocals().isEmpty())) {
      return false;
    }
    return getSelectedLocals().contains(paramWdActivity);
  }
  
  public boolean isMoveBreadcrumbDialogShowing()
  {
    if ((isMoveFileWindowShowing()) && (this.mMoveFileWindow.getMoveBreadcrumbDialog() != null)) {
      return this.mMoveFileWindow.getMoveBreadcrumbDialog().isShowing();
    }
    return false;
  }
  
  public boolean isMoveFileDialogShowing()
  {
    if (this.mMoveFileDialog != null) {
      return this.mMoveFileDialog.isShowing();
    }
    return false;
  }
  
  public boolean isMoveFileWindowShowing()
  {
    if ((this.mMoveFileWindow != null) && (this.mMoveFileWindow.getPopupWindow() != null)) {
      return this.mMoveFileWindow.getPopupWindow().isShowing();
    }
    return false;
  }
  
  public boolean isNewFolderDialogShowing()
  {
    if ((this.mNewFolderDialog != null) && (this.mNewFolderDialog.getDialog() != null)) {
      return this.mNewFolderDialog.getDialog().isShowing();
    }
    return false;
  }
  
  public boolean isPhotoViewerDisplay()
  {
    if ((!isPhone()) && (this.mMediaFragmentManager != null)) {
      return this.mMediaFragmentManager.isPhotosShowing();
    }
    return false;
  }
  
  public boolean isRenameDialogShowing()
  {
    if ((this.mRenameDialog != null) && (this.mRenameDialog.getDialog() != null)) {
      return this.mRenameDialog.getDialog().isShowing();
    }
    return false;
  }
  
  public boolean isRightFragmentShow()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!isPhone())
    {
      bool1 = bool2;
      if (this.mMediaFragmentManager != null) {
        if (!this.mMediaFragmentManager.isPhotosShowing())
        {
          bool1 = bool2;
          if (this.mMediaFragmentManager.getMusicPlayFragment() == null) {}
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public boolean isSDCardEjecting()
  {
    return this.mSDCardEjecting.get();
  }
  
  public boolean isSDCardFileSelected(WdActivity paramWdActivity)
  {
    if ((getSelectedSDCardFiles() == null) || (getSelectedSDCardFiles().isEmpty())) {}
    Iterator localIterator;
    do
    {
      while (!localIterator.hasNext())
      {
        return false;
        localIterator = getSelectedSDCardFiles().iterator();
      }
    } while (!((WdActivity)localIterator.next()).id.equals(paramWdActivity.id));
    return true;
  }
  
  public boolean isSharingCurrentFolder()
  {
    return (this.mShareIntentData != null) && (this.mShareIntentData.mIsSharingCurrentFolder);
  }
  
  public boolean isSharingFromPhotoViewer()
  {
    return (this.mShareIntentData != null) && (this.mShareIntentData.mIsSharingFromPhotoViewer);
  }
  
  public boolean isTabCursorLayoutShow()
  {
    return this.mTabLayout.getVisibility() == 0;
  }
  
  public boolean isUSBEjecting()
  {
    return this.mUSBEjecting.get();
  }
  
  public boolean isUploadContextMenuShowing()
  {
    if (this.mUploadContextMenu != null) {
      return this.mUploadContextMenu.isShowing();
    }
    return false;
  }
  
  public boolean isWdActivitySelect(WdActivity paramWdActivity)
  {
    if ((getSelectedWdActivities() == null) || (getSelectedWdActivities().isEmpty())) {}
    Iterator localIterator;
    do
    {
      while (!localIterator.hasNext())
      {
        return false;
        localIterator = getSelectedWdActivities().iterator();
      }
    } while (!((WdActivity)localIterator.next()).id.equals(paramWdActivity.id));
    return true;
  }
  
  public boolean isWdFileSelect(WdFile paramWdFile)
  {
    if ((getSelectedWdFiles() == null) || (getSelectedWdFiles().isEmpty())) {
      return false;
    }
    return getSelectedWdFiles().contains(paramWdFile);
  }
  
  public void jumpTo(int paramInt)
  {
    jumpTo(paramInt, false);
  }
  
  public void jumpTo(int paramInt, boolean paramBoolean)
  {
    jumpTo(paramInt, paramBoolean, true, true);
  }
  
  public void jumpTo(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    jumpTo(paramInt, paramBoolean1, paramBoolean2, true);
  }
  
  public void jumpTo(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((paramBoolean1) && (this.mFileFragment.getTabIndex() == paramInt)) {
      return;
    }
    setVisibility(this.mFragmentContainer, 8);
    setVisibility(this.mMainLayout, 0);
    this.mOverflowMenuItem.setVisible(true);
    toggleNavigationSpinner(false);
    this.isJumpToShareMe = false;
    if (paramInt == 10)
    {
      this.mPageAdapter.notifyDataSetChanged();
      this.isJumpToShareMe = true;
      if ((this.mShareWithMeFragment != null) && (this.mShareWithMeFragment.isAdded()))
      {
        showMenuIcon(false);
        showShare(false);
      }
      this.mMainLayout.setVisibility(8);
      this.mFragmentContainer.setVisibility(0);
      if (!this.mShareWithMeFragment.isAdded())
      {
        FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
        localFragmentTransaction.add(2131821611, this.mShareWithMeFragment);
        localFragmentTransaction.commit();
      }
      this.mShareWithMeFragment.setActivity(this);
      this.mShareWithMeFragment.loadData(null);
      if (getSupportActionBar() != null)
      {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle(2131297324);
        this.mOverflowMenuItem.setVisible(false);
      }
    }
    for (;;)
    {
      this.mFileFragment.switchTab(paramInt);
      if ((paramBoolean1) && (paramInt == 0) && (this.lastSelectedMediaIndex != 0) && (!isAccessLocalResource()) && (this.lastSelectedMediaIndex != 4)) {
        this.mViewPager.setCurrentItem(this.lastSelectedMediaIndex, paramBoolean2);
      }
      if ((!paramBoolean3) || (this.mSlideDrawer == null)) {
        break;
      }
      this.mSlideDrawer.setDefaultSelect(paramInt);
      return;
      if (this.mViewPager.getCurrentItem() != 0)
      {
        this.isJumpTo = true;
        this.mViewPager.setCurrentItem(0, paramBoolean2);
        if ((paramInt != 0) && (paramInt != 1) && (paramInt != 2) && (paramInt != 3)) {
          toggleNavigationSpinner(false);
        }
      }
      else if (paramInt == 8)
      {
        this.mOverflowMenuItem.setVisible(false);
      }
    }
  }
  
  public void loadCloudFileSystem(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, WdFile... paramVarArgs)
  {
    back2Default4Search();
    this.mIsListBusy = false;
    startLoading(paramBoolean2, paramBoolean3);
    new OpenCloudFolderLoader(false, this, paramBoolean2).execute(paramVarArgs);
  }
  
  public void loadCloudFileSystemFromCache(boolean paramBoolean, WdFile... paramVarArgs)
  {
    this.mIsListBusy = false;
    startLoading(true, paramBoolean);
    new OpenCloudFolderFromCacheLoader(false, this).execute(paramVarArgs);
  }
  
  public void loadFromPush()
  {
    Object localObject1 = getIntent().getExtras();
    if (localObject1 == null) {}
    for (;;)
    {
      return;
      localObject1 = (String)((Bundle)localObject1).get("push_message");
      Log.d(tag, "## Push message " + (String)localObject1);
      if (!StringUtils.isEmpty((String)localObject1)) {
        try
        {
          Object localObject2 = new JSONObject((String)localObject1);
          localObject1 = ((JSONObject)localObject2).optString("share_name");
          localObject2 = ((JSONObject)localObject2).optString("device_serial");
          if ((localObject2 != null) && (localObject1 != null))
          {
            new PushShareMeLoader(this, (String)localObject2, (String)localObject1)
            {
              public void showShareContentWindow(Device paramAnonymousDevice, WdFile paramAnonymousWdFile)
              {
                try
                {
                  if (MyDeviceActivity.this.mShareWithMeFragment != null) {
                    MyDeviceActivity.this.mShareWithMeFragment.showShareContentWindow(MyDeviceActivity.this.mMainLayout, paramAnonymousDevice, paramAnonymousWdFile);
                  }
                  return;
                }
                catch (ResponseException paramAnonymousDevice)
                {
                  MyDeviceActivity.this.showResponseError(paramAnonymousDevice);
                }
              }
            }.execute(new String[0]);
            return;
          }
        }
        catch (Exception localException)
        {
          Log.e(tag, localException.getMessage(), localException);
        }
      }
    }
  }
  
  public void loadLocalFileSystem(WdActivity... paramVarArgs)
  {
    getOpenLocalFolderLoader(true).execute(paramVarArgs);
  }
  
  public void loadMoveFileWindow(WdFile paramWdFile)
  {
    try
    {
      if ((this.mMoveFileWindow != null) && (getMoveFileSystem() != null))
      {
        this.mMoveFileWindow.loadFileList(false, 0, new WdFile[] { paramWdFile });
        if ((paramWdFile != null) && (getWdFileSystem() != null))
        {
          getWdFileSystem().setDirty(paramWdFile);
          paramWdFile = paramWdFile.getParent();
          if (paramWdFile != null) {
            getWdFileSystem().setDirty(paramWdFile);
          }
        }
      }
      return;
    }
    catch (Exception paramWdFile)
    {
      Log.e(tag, "loadMoveFileWindow", paramWdFile);
      reloadMoveFileWindow();
    }
  }
  
  public void loadTipsInBrowser()
  {
    Object localObject1 = null;
    try
    {
      localObject2 = this.mWdFileManager.getConfiguration().mionetServer;
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject2;
        Log.i(tag, "loadTipsInBrowser", localException);
      }
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "wdphotos.senvid.net:443";
    }
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(UrlConstant.format("https://%s/wd2go/faq.jsp?" + UrlConstant.fixedKeyCode + UrlConstant.JpUrl.BRAND_PARAMETER_FOR_CATALOG_WITH_AMP, new Object[] { localObject2 }))));
  }
  
  public void loadWdActivity(WdActivity... paramVarArgs)
  {
    getWdActivityFolderLoader(true).execute(paramVarArgs);
  }
  
  public void loginDeviceIfNeed()
  {
    new DeviceLoginLoader(this, this.mLoginListener).execute(new Device[0]);
  }
  
  public List<WdActivity> mergeSelectedLocals()
  {
    ArrayList localArrayList = new ArrayList();
    if ((isAllSelect()) || (!isLandscapePad())) {
      if ((getSelectedLocals() != null) && (!getSelectedLocals().isEmpty())) {
        localArrayList.addAll(getSelectedLocals());
      }
    }
    while ((getSelectedLocals() == null) || (getSelectedLocals().isEmpty())) {
      return localArrayList;
    }
    localArrayList.addAll(getSelectedLocals());
    return localArrayList;
  }
  
  public List<WdFile> mergerSelectedWdFiles()
  {
    ArrayList localArrayList = new ArrayList();
    if (((isAllSelect()) && (!getSelectedWdFiles().isEmpty())) || (!isLandscapePad())) {
      if ((getSelectedWdFiles() != null) && (!getSelectedWdFiles().isEmpty())) {
        localArrayList.addAll(getSelectedWdFiles());
      }
    }
    while ((getSelectedWdFiles() == null) || (getSelectedWdFiles().isEmpty())) {
      return localArrayList;
    }
    localArrayList.addAll(getSelectedWdFiles());
    return localArrayList;
  }
  
  public boolean needShowSelection()
  {
    return true;
  }
  
  public void newFolder(String paramString)
  {
    setCurrentAction(null, 5);
    this.mNewFolderDialog = new NewFolderDialog(this, paramString);
    if ((this.mNewFolderDialog != null) && (this.mNewFolderDialog.getDialog() != null) && (!this.mNewFolderDialog.getDialog().isShowing())) {
      this.mNewFolderDialog.getDialog().show();
    }
  }
  
  public void notifyBadgeChanged(int paramInt)
  {
    try
    {
      if (this.mBadgeView == null) {
        return;
      }
      if (this.mWdFileManager == null)
      {
        this.mBadgeView.hide();
        return;
      }
    }
    catch (Exception localException)
    {
      Log.e(tag, localException.getMessage(), localException);
      return;
    }
    if (paramInt == 0)
    {
      this.mBadgeView.hide();
      return;
    }
    if (!this.mBadgeView.isShown()) {
      this.mBadgeView.show();
    }
    this.mBadgeView.setNumValue(paramInt);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 0)
    {
      switch (paramInt2)
      {
      case 3: 
      default: 
        ;;
      }
    }
    else
    {
      label32:
      do
      {
        do
        {
          do
          {
            do
            {
              try
              {
                notifyBadgeChanged(0);
                this.mFileFragment.cleanActivityTab();
                this.mWdFileManager.setCurrentActivityId("root", false);
                if ((this.mViewPager != null) && (this.mViewPager.getCurrentItem() != 0)) {
                  this.mViewPager.setCurrentItem(0);
                }
                back2Default();
                setEditEnable(false);
                reload();
                return;
              }
              catch (Exception paramIntent)
              {
                try
                {
                  Log.e(tag, paramIntent.getMessage(), paramIntent);
                  return;
                }
                catch (Exception paramIntent)
                {
                  Log.e(tag, paramIntent.getMessage(), paramIntent);
                  return;
                }
              }
              onRefreshDevices();
              paramIntent = this.mWdFileManager.getDatabaseAgent().getAllDevices();
              if ((paramIntent == null) || (paramIntent.size() <= 0) || ((paramIntent.size() == 1) && (((Device)paramIntent.get(0)).isSDCard())))
              {
                paramIntent = new Intent(this, AddDeviceActivity.class);
                paramIntent.setFlags(67108864);
                startActivity(paramIntent);
                finish();
                return;
              }
              CompareUtils.sortDeviceList(paramIntent);
              paramIntent = (Device)paramIntent.get(0);
            } while (paramIntent == null);
            resetLastSelectedMediaIndex();
            this.mFileFragment.loginDevice(paramIntent);
            this.mSlideDrawer.setSelectedStatus4Device(true);
            closeDrawer();
            return;
            if (paramInt1 == 1)
            {
              try2Save();
              RateAppHelper.showRateDialogIfNeeded(this);
              return;
            }
            if (paramInt1 != 21) {
              break;
            }
          } while (this.mPageIndex != 2);
          reload();
          return;
          if (paramInt1 == 2)
          {
            hideSoftInput();
            clearCurrentAction();
            return;
          }
          if (paramInt1 == 3)
          {
            hideSoftInput();
            clearCurrentAction();
            setEditEnable(false);
            return;
          }
          if (paramInt1 != 11) {
            break;
          }
        } while ((paramInt2 != 11) || (!isDeviceListShow()));
        this.mFileFragment.cleanDeviceListAdapter();
        return;
        if (paramInt1 != 12) {
          break;
        }
      } while (paramInt2 != 11);
      paramIntent = this.mWdFileManager.getDatabaseAgent().getAllDevices();
      this.mSlideDrawer.onDeviceListLoad(paramIntent);
      return;
    }
    for (;;)
    {
      try2Add(paramIntent);
      if (Build.VERSION.SDK_INT >= 24)
      {
        AutoBackupJobSchedulerService.openManuallyUpload(this);
        return;
      }
      AutoBackupService.openManuallyUpload(this);
      return;
      if ((paramInt1 == 14) || (paramInt2 == -1)) {
        break label32;
      }
      if (paramInt1 == 13)
      {
        boolean bool = getSharedPreferences("RELOAD_MEDIA_AFTER_DELETE", 0).getBoolean("RELOAD_PHOTO", false);
        if ((this.mPageIndex == 1) && (bool))
        {
          paramIntent = this.mApplication.getPlayList(true).getCurrentWdActivity();
          if ((paramIntent == null) || ((!isLandscapePad()) && ((!isPortraitPad()) || (isLargePad())))) {
            break label32;
          }
          doOpenFile(paramIntent);
          this.mMediaFragmentManager.clearMediaSelection();
          paramIntent = getMediaFragmentManager().getPhotoViewerFragment();
          if (paramIntent == null) {
            break label32;
          }
          paramIntent = (PhotoPage)paramIntent.getStateManager().getTopState();
          this.mMediaFragmentManager.getCurrentFragment().setSelectItemId(paramIntent.getMediaList().getCurrentIndex());
          return;
        }
        paramIntent = this.mPageAdapter.getItem(this.mPageIndex);
        if (!(paramIntent instanceof PhotoTabFragment)) {
          break label32;
        }
        ((PhotoTabFragment)paramIntent).cleanPhotoTabOpened();
        return;
      }
      if (paramInt1 == 15)
      {
        Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
        String str = AesCryptoUtils.decrypt(((SharedPreferences)localObject).getString("wdmycloud.input1", ""));
        localObject = AesCryptoUtils.decrypt(((SharedPreferences)localObject).getString("wdmycloud.input2", ""));
        if ((paramInt2 == 20) || (isPhone()) || ((isLargePad()) && (isPortraitPad())) || (getTabIndex() != 0) || (TextUtils.isEmpty((CharSequence)localObject)) || (TextUtils.isEmpty(str)))
        {
          setEditEnable(false);
          return;
        }
        if ((paramInt2 != 15) || (TextUtils.isEmpty((CharSequence)localObject)) || (TextUtils.isEmpty(str))) {
          break label32;
        }
        showPrivateShareFragment((SharePrivateActivity.ShareIntentData)paramIntent.getSerializableExtra("com.wdc.wd2go.ui.activity.extra.EXTRA_SHARE_INTENT_DATA"));
        return;
      }
      if (paramInt1 == 20)
      {
        paramIntent = new Thread(new Runnable()
        {
          public void run()
          {
            InputMethodManager localInputMethodManager = (InputMethodManager)MyDeviceActivity.this.getSystemService("input_method");
            if (MyDeviceActivity.this.getCurrentFocus() != null) {
              localInputMethodManager.hideSoftInputFromWindow(MyDeviceActivity.this.getCurrentFocus().getWindowToken(), 0);
            }
          }
        });
        this.mHandler.postDelayed(paramIntent, 500L);
        RateAppHelper.showRateDialogIfNeeded(3000, this);
        return;
      }
      if (paramInt1 == 16)
      {
        ThreadPool.excuteShortTask(new Runnable()
        {
          public void run()
          {
            List localList = MyDeviceActivity.this.mWdFileManager.showDialogForUpgradeDevices(MyDeviceActivity.this.mWdFileManager.getDevices(), false);
            if ((localList != null) && (!localList.isEmpty())) {
              MyDeviceActivity.this.showDeviceUpgradeDialog(localList);
            }
          }
        });
        return;
      }
      if (!isPermissionGranted()) {
        break label32;
      }
      if (paramInt1 == 772)
      {
        startDownloadFile();
        return;
      }
      if (paramInt1 == 771)
      {
        showUploadContextMenu();
        return;
      }
      if (paramInt1 == 773)
      {
        saveAs();
        return;
      }
      if (paramInt1 == 774)
      {
        this.mMoveFileWindow.loadFileList(false, 0, new WdFile[0]);
        return;
      }
      if (paramInt1 == 775)
      {
        startMoveFile(717);
        return;
      }
      if (paramInt1 == 776)
      {
        this.mSlideDrawer.initializeDrawerClick(this.mDevice);
        return;
      }
      if (paramInt1 != 781) {
        break label32;
      }
      showAddFileDialog();
      return;
      if (paramInt1 != 4) {
        if (paramInt1 != 5) {
          break;
        }
      }
    }
  }
  
  public void onBackClick()
  {
    try
    {
      if (this.isEditMode)
      {
        setEditEnable(false);
        return;
      }
      if (this.mSlideDrawer != null)
      {
        this.mSlideDrawer.openOrCloseDrawer();
        return;
      }
    }
    catch (Exception localException)
    {
      Log.e(tag, "backButtonClick exception ", localException);
      return;
    }
    onBackPressed();
  }
  
  public void onBackPressed()
  {
    if ((isMoveFileWindowShowing()) && (this.mMoveFileWindow != null)) {
      this.mMoveFileWindow.onBackPressed();
    }
    MusicPlayerFragment localMusicPlayerFragment;
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            try
            {
              if (getEditEnable())
              {
                setEditEnable(false);
                return;
              }
            }
            catch (Exception localException)
            {
              Log.e(tag, "onBackPressed", localException);
              goToSignInOrFinish();
              return;
            }
            if (this.mFromOtherApp.get())
            {
              this.mApplication.loginFlag.set(false);
              this.mFromOtherApp.set(false);
              super.onBackPressed();
              finish();
              return;
            }
            if (this.mViewPager == null)
            {
              goToSignInOrFinish();
              return;
            }
            if ((this.mLapismSearchView != null) && (this.mLapismSearchView.isShown()))
            {
              this.mSearchView.onBackClick();
              return;
            }
            if (this.mViewPager.getCurrentItem() != 0) {
              break;
            }
          } while ((this.mFileFragment.onBackPressed()) || (this.mMediaFragmentManager == null));
          localMusicPlayerFragment = this.mMediaFragmentManager.getMusicPlayFragment();
        } while (localMusicPlayerFragment == null);
        localMusicPlayerFragment.notificationFullScreen(true);
        return;
        if (this.mViewPager.getCurrentItem() != 4) {
          break;
        }
      } while (!this.mShareWithMeFragment.onBackPressed());
      goToSignInOrFinish();
      return;
    } while ((onMediaBackPressed()) || (this.mViewPager == null));
    if (!isDrawerOpen())
    {
      if (this.mMediaFragmentManager != null)
      {
        localMusicPlayerFragment = this.mMediaFragmentManager.getMusicPlayFragment();
        if (localMusicPlayerFragment != null) {
          localMusicPlayerFragment.notificationFullScreen(true);
        }
      }
      if ((getCurrentDevice() != null) && (!getCurrentDevice().isAvatarDevice()))
      {
        this.mViewPager.setCurrentItem(0);
        this.mFileFragment.onBackPressed();
        return;
      }
      goToSignInOrFinish();
      return;
    }
    closeDrawer();
  }
  
  public void onCastClick()
  {
    if (this.mCastDialog != null) {
      this.mCastDialog.show();
    }
  }
  
  public void onClick(View paramView)
  {
    onSearchRequested();
  }
  
  public boolean onClose()
  {
    return false;
  }
  
  public void onConfigurationChanged(android.content.res.Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    onConfigurationChangedFullScreen(paramConfiguration.orientation);
    adjustTitleMaxWidth();
    if (this.mSharePrivateFragment != null) {
      this.mSharePrivateFragment.onConfigurationChanged(paramConfiguration);
    }
  }
  
  public void onConfigurationChangedFullScreen(int paramInt)
  {
    try
    {
      initTabCursorWidth();
      if (this.mSlideDrawer != null) {
        this.mSlideDrawer.updateDrawerWidth();
      }
      updateTitleBarIndicator(this.mViewPager.getCurrentItem());
      if (isMoveFileWindowShowing()) {
        this.mMoveFileWindow.updateWindow();
      }
      getMediaFragmentManager().onConfigChange();
      super.onConfigurationChangedFullScreen(paramInt);
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, "screenOrientationChanged", localException);
    }
  }
  
  public void onConnectionChanged(final Boolean paramBoolean, final String paramString)
  {
    super.onConnectionChanged(paramBoolean, paramString);
    if ((paramBoolean != null) && (paramBoolean.booleanValue()) && (StringUtils.isEquals(this.mApplication.getNeedConnectWifi(), paramString)))
    {
      this.mApplication.cleanNeedConnectWifi();
      paramString = getCurrentDevice();
      if ((paramString == null) || ((!paramString.isAvatarDevice()) && (!paramString.isKorraDevice()))) {
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          Object localObject3 = null;
          Object localObject1 = null;
          int j = 0;
          try
          {
            ScanDeviceService localScanDeviceService = ScanDeviceService.getInstance();
            int i = j;
            if (localScanDeviceService != null)
            {
              localObject1 = localScanDeviceService;
              localObject3 = localScanDeviceService;
              localScanDeviceService.openDmc(MyDeviceActivity.this.mApplication);
              i = j;
            }
            while (i <= 5)
            {
              localObject1 = localScanDeviceService;
              localObject3 = localScanDeviceService;
              Thread.sleep(5000L);
              i += 1;
              localObject1 = localScanDeviceService;
              localObject3 = localScanDeviceService;
              String str = localScanDeviceService.getAvatarNewIPAddress(paramString.localUUID);
              if (str != null)
              {
                localObject1 = localScanDeviceService;
                localObject3 = localScanDeviceService;
                paramString.localAddress = str;
                localObject1 = localScanDeviceService;
                localObject3 = localScanDeviceService;
                MyDeviceActivity.this.mApplication.getWdFileManager().getDatabaseAgent().update(paramString);
              }
            }
            return;
          }
          catch (Exception localException)
          {
            localObject3 = localObject1;
            Log.i(MyDeviceActivity.tag, localException.getMessage());
            return;
          }
          finally
          {
            if (localObject3 != null) {
              localObject3.closeDmc();
            }
          }
        }
      }).start();
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        if ((paramBoolean != null) && (paramBoolean.booleanValue()))
        {
          MyDeviceActivity.this.showBackingUpLayout();
          return;
        }
        MyDeviceActivity.this.hideBackingUpStatusPanel();
      }
    });
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    for (;;)
    {
      try
      {
        super.onCreate(paramBundle);
        paramBundle = getIntent().getExtras();
        if ((paramBundle != null) && (paramBundle.getBoolean("NotificationClicked"))) {
          WDAnalytics.logEvent("AutoBackup Notification Clicked");
        }
        this.mCastManager = CastManager.getInstance();
        this.mApplication.setMyDeviceActivity(this);
        this.mWdFileManager.setScreenRefreshListener(this);
        this.mWdFileSystem = this.mWdFileManager.getWdFileSystem(null);
        this.mIntent = getIntent();
        extractIntent();
        setContentViewX(2130968807);
        this.toolbar = ((Toolbar)findViewById(2131820725));
        setSupportActionBar(this.toolbar);
        this.toolbar.setTitle(2131297598);
        this.mNavigationSpinner = ((Spinner)findViewById(2131821854));
        this.mNavigationSpinner.setVisibility(0);
        this.mMainLayout = ((RelativeLayout)findViewById(2131821286));
        this.mFragmentContainer = ((LinearLayout)findViewById(2131821611));
        this.mSearchViewLayout = findViewById(2131821614);
        this.mShareViewLayout = findViewById(2131821841);
        if (this.mShareViewLayout != null) {
          this.mShareViewLayout.setVisibility(0);
        }
        this.mDrawerLayout = ((DrawerLayout)findViewById(2131821610));
        this.navigationView = ((NavigationView)findViewById(2131821612));
        paramBundle = new ActionBarDrawerToggle(this, this.mDrawerLayout, this.toolbar, 2131297598, 2131297598)
        {
          public void onDrawerClosed(View paramAnonymousView)
          {
            super.onDrawerClosed(paramAnonymousView);
          }
          
          public void onDrawerOpened(View paramAnonymousView)
          {
            super.onDrawerOpened(paramAnonymousView);
          }
        };
        this.mDrawerLayout.setDrawerListener(paramBundle);
        paramBundle.syncState();
        this.mSearchBarFragment = new SearchBarFragment();
        this.mShareWithMeFragment = ShareWithMeFragment.newInstance();
        this.mShareWithMeFragment.setActivity(this);
        initViewPager();
        this.mMoveFileWindow = new MoveFileWindow(this);
        this.mAppChooseDialog = new CustomAppChooser(this);
        this.mAddFileDialog = new AddItemDialog(this);
        onConfigurationChangedFullScreen(this.mOrientation);
        initTextColor();
        loadFromPush();
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) != 0) {
          continue;
        }
        startService(new Intent(this, MyCloudRegistrationIntentService.class));
        paramBundle = getSharedPreferences("APP_ON_BOARDING", 0);
        if ((paramBundle != null) && (!paramBundle.getBoolean("APP_ON_BOARDING_ACCEPTED_23", false))) {
          paramBundle.edit().putBoolean("APP_ON_BOARDING_ACCEPTED_23", true).apply();
        }
        this.mHelper = new MyDeviceActivityHelper();
      }
      catch (Exception paramBundle)
      {
        Log.e(tag, paramBundle.getMessage(), paramBundle);
        goToSignInOrFinish();
        continue;
      }
      setupLapismSearchView();
      if (!RateAppHelper.getOptOut(this)) {
        ThreadPool.schedule(new Runnable()
        {
          public void run()
          {
            if ((!MyDeviceActivity.this.isAnyDialogShown()) && (MyDeviceActivity.this.mWdFileManager != null))
            {
              Object localObject = MyDeviceActivity.this.mWdFileManager.getDatabaseAgent();
              if (localObject != null)
              {
                localObject = ((DatabaseAgent)localObject).getAllDevices();
                if ((localObject != null) && (((List)localObject).size() > 2)) {
                  MyDeviceActivity.this.handler.post(new Runnable()
                  {
                    public void run()
                    {
                      RateAppHelper.showRateDialogIfNeeded(MyDeviceActivity.this);
                    }
                  });
                }
              }
            }
          }
        }, 5L, TimeUnit.SECONDS);
      }
      return;
      Log.e(tag, "Compatible Google Play service is not available for Push");
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131886086, paramMenu);
    this.mShareMenuItem = paramMenu.findItem(2131821899);
    this.mSearchMenuItem = paramMenu.findItem(2131821903);
    this.mOverflowMenuItem = paramMenu.findItem(2131821901);
    this.mCastMenuItem = paramMenu.findItem(2131821902);
    this.mSendMenuItem = paramMenu.findItem(2131821904);
    this.mDownloadMenuItem = paramMenu.findItem(2131821898);
    this.mCastDialog = new CastDeviceDialog(this, this.mCastMenuItem);
    CastManager.getInstance().startScan();
    return true;
  }
  
  public void onDeleteDeviceSuccessful(List<Device> paramList, Device paramDevice, boolean paramBoolean)
  {
    try
    {
      this.mFileFragment.onDeviceDelete(paramList, paramDevice, paramBoolean);
      if ((this.mSlideDrawer != null) && (this.mWdFileManager != null) && (this.mFileFragment != null))
      {
        paramList = this.mWdFileManager.getDevices();
        if ((paramList != null) && (!paramList.isEmpty()))
        {
          CompareUtils.sortDeviceList(paramList);
          paramList = (Device)paramList.get(0);
          if (paramList != null)
          {
            resetLastSelectedMediaIndex();
            this.mFileFragment.loginDevice(paramList);
            this.mSlideDrawer.setSelectedStatus4Device(true);
            closeDrawer();
          }
        }
      }
      return;
    }
    catch (Exception paramList)
    {
      Log.e(tag, "refreshAfterDeviceListLoad", paramList);
    }
  }
  
  protected void onDestroy()
  {
    for (;;)
    {
      try
      {
        super.onDestroy();
        getWdApplication().mLoadShareFromServer.set(true);
        removeSaveAsOrPropertyDialog();
        removeNoSdCardDialog();
        if (this.mDeviceVersionDialog != null)
        {
          this.mDeviceVersionDialog.dismiss();
          this.mDeviceVersionDialog = null;
        }
        NotificationUtils.setActivity(null);
        if (LocalCreateDeviceUserAccountRunnable.scRefreshListener == this) {
          LocalCreateDeviceUserAccountRunnable.scRefreshListener = null;
        }
        if (this.mWdFileManager != null) {
          this.mWdFileManager.setScreenRefreshListener(null);
        }
        if (this.mApplication != null)
        {
          this.mApplication.setMyDeviceActivity(null);
          if ((this.mApplication.getCurrentActivity() instanceof MyDeviceActivity)) {
            this.mApplication.setCurrentActivity(null);
          }
        }
        DialogUtils.removeDialog(this);
        if (this.mMoveFileDialog != null)
        {
          this.mMoveFileDialog.dismiss();
          this.mMoveFileDialog = null;
        }
        if (this.mMoveFileWindow != null)
        {
          if (this.mMoveFileWindow.getMoveBreadcrumbDialog() != null) {
            this.mMoveFileWindow.getMoveBreadcrumbDialog().dismiss();
          }
          this.mMoveFileWindow.dismissWhenRotate();
        }
        dismissChooserAppDilaog();
        dismissDeleteDialog();
        dismissAddFileDialog();
        dismissUploadContextMenu();
        if (this.mNewFolderDialog != null) {
          this.mNewFolderDialog.forceDismissWhenActivityDestroy();
        }
        if (this.mRenameDialog != null) {
          this.mRenameDialog.forceDismissWhenActivityDestory();
        }
        dismissDeleteDialog();
        dismissDeviceUpgradeDialog();
        dismissDeviceDeleteDialog();
        dismissDeviceInfoDialog();
        dissmissShowResponseErrorDialog();
        dissmissWaringDialog();
        RateAppHelper.dismiss();
      }
      catch (Exception localException2)
      {
        localException2 = localException2;
        Log.e(tag, localException2.getMessage(), localException2);
        try
        {
          if ((this.mWifiLock == null) || (!this.mWifiLock.isHeld())) {
            continue;
          }
          this.mWifiLock.release();
          return;
        }
        catch (Exception localException3)
        {
          Log.e(tag, localException3.getMessage(), localException3);
          return;
        }
      }
      finally {}
      try
      {
        if ((this.mWifiLock != null) && (this.mWifiLock.isHeld())) {
          this.mWifiLock.release();
        }
        return;
      }
      catch (Exception localException1)
      {
        Log.e(tag, localException1.getMessage(), localException1);
        return;
      }
    }
    try
    {
      if ((this.mWifiLock != null) && (this.mWifiLock.isHeld())) {
        this.mWifiLock.release();
      }
      throw localObject;
    }
    catch (Exception localException4)
    {
      for (;;)
      {
        Log.e(tag, localException4.getMessage(), localException4);
      }
    }
  }
  
  public void onDismissMoveFileWindow() {}
  
  public void onException(ResponseException paramResponseException)
  {
    showResponseError(paramResponseException);
  }
  
  public void onFileIconClick(View paramView, WdFile paramWdFile, WdActivity paramWdActivity)
  {
    if (this.mPageIndex == 0) {
      this.mFileFragment.onFileIconClick(paramView, paramWdFile, paramWdActivity);
    }
    do
    {
      return;
      if ((this.mPageIndex == 1) || (this.mPageIndex == 2) || (this.mPageIndex == 3))
      {
        this.mMediaFragmentManager.onFileIconClick(paramView, (WdFileMedia)paramWdFile);
        return;
      }
    } while (this.mPageIndex != 4);
  }
  
  public void onGetDeviceInfo(String paramString1, String paramString2)
  {
    if (this.mDeleteDeviceDialog != null) {
      this.mDeleteDeviceDialog.dismiss();
    }
    this.mDeviceInfoDialog = DialogUtils.info(this, getString(2131296707), paramString1 + "\n\n" + paramString2, null);
  }
  
  protected void onHandleMessage(Message paramMessage)
  {
    int k;
    Object localObject1;
    int i;
    try
    {
      k = getTabIndex();
      switch (paramMessage.what)
      {
      case 0: 
        localObject1 = (WdActivity)paramMessage.obj;
        if (localObject1 == null) {
          break label3974;
        }
        i = paramMessage.arg1;
        long l = 0L;
        paramMessage = paramMessage.getData();
        if (paramMessage != null) {
          l = paramMessage.getLong("update_progress");
        }
        if (k == 6)
        {
          paramMessage = (WdActivityItemView)getItemView(((WdActivity)localObject1).id);
          if (paramMessage != null) {
            paramMessage.updateProgress(i, l);
          }
          this.mFileFragment.updateActivityData((WdActivity)localObject1);
          return;
        }
        break;
      }
    }
    catch (Exception paramMessage)
    {
      Log.e(tag, paramMessage.getMessage(), paramMessage);
      return;
    }
    Object localObject3;
    int j;
    Object localObject2;
    label2982:
    label3064:
    int m;
    if (k == 0)
    {
      paramMessage = (FileItemView)getItemView(FileUtils.generateWdFileItemId(((WdActivity)localObject1).fullPath));
      if ((getCurrentDevice() == null) || (!getCurrentDevice().isSDCard())) {
        break label3975;
      }
      i = 1;
      if ((paramMessage != null) && (i == 0))
      {
        paramMessage.showActivityIconBar(true);
        if (getCurrentDevice().isKorraDevice())
        {
          paramMessage.updateProgress((WdActivity)localObject1);
          if (((WdActivity)localObject1).status == 0)
          {
            this.mFileFragment.removeKorraImportActivity((WdActivity)localObject1);
            return;
          }
          if (((WdActivity)localObject1).status == -2)
          {
            this.mFileFragment.addKorraImportActivity((WdActivity)localObject1);
            return;
          }
          if (((WdActivity)localObject1).status == -1)
          {
            this.mFileFragment.removeAllKorraImportActivities();
            return;
            paramMessage = (WdActivity)paramMessage.obj;
            if (paramMessage != null)
            {
              if ("Cut".equals(paramMessage.activityType)) {
                this.mFileFragment.removeItem(paramMessage.fullPath);
              }
              if ("Upload".equals(paramMessage.activityType)) {
                RateAppHelper.showRateDialogIfNeeded(this);
              }
              if (k == 6)
              {
                localObject1 = (WdActivityItemView)getItemView(paramMessage.id);
                if (localObject1 != null) {
                  ((WdActivityItemView)localObject1).showCompleted(paramMessage);
                }
                this.mFileFragment.updateActivityData(paramMessage);
                return;
              }
              if (k == 0)
              {
                localObject1 = (FileItemView)getItemView(FileUtils.generateWdFileItemId(paramMessage.fullPath));
                if (localObject1 != null)
                {
                  ((FileItemView)localObject1).showActivityIconBar(false);
                  ((FileItemView)localObject1).setActivityIcon(paramMessage.activityType);
                }
                this.mFileFragment.notifyDataSetChange();
                return;
                WdActivity localWdActivity = (WdActivity)paramMessage.obj;
                if (localWdActivity != null) {}
                for (;;)
                {
                  Object localObject4;
                  String str;
                  try
                  {
                    if ((StringUtils.isEquals(localWdActivity.activityType, "Delete")) || (StringUtils.isEquals("Rename", localWdActivity.activityType)))
                    {
                      i = localWdActivity.status;
                      if (i != 0) {}
                    }
                  }
                  catch (Exception localException2)
                  {
                    Log.e(tag, "HANDLE_UPDATE_BADGE", localException2);
                    continue;
                    if (!MimeTypeUtils.isVideo((String)localObject3)) {
                      continue;
                    }
                    localException2.edit().putBoolean("RELOAD_VIDEO", true).apply();
                    continue;
                    if ((localWdActivity.uploadStatus != 0) || (localWdActivity.downloadStatus != 0) || (getWdFileSystem().getDevice() == null)) {
                      continue;
                    }
                    localObject4 = getWdFileSystem().getDevice();
                    if (!StringUtils.isEquals(localWdActivity.deviceId, localWdActivity.uploadDeviceId)) {
                      continue;
                    }
                    localObject2 = FileUtils.getParent(localWdActivity.uploadPath);
                    localObject3 = FileUtils.getParent(localWdActivity.fullPath);
                    localObject4 = FileUtils.removeObjectId(getWdFileSystem().getCurrentFolder().fullPath);
                    if (!((String)localObject4).equals(localObject2)) {
                      continue;
                    }
                    reload();
                    continue;
                    if ((!((String)localObject4).equals(localObject3)) || (!StringUtils.isEquals("Cut", localWdActivity.activityType))) {
                      continue;
                    }
                    reload();
                    continue;
                    if (!isLandscapePad()) {
                      continue;
                    }
                    localObject4 = getWdFileSystem().getCurrentChildFolder();
                    if (localObject4 == null) {
                      continue;
                    }
                    localObject4 = FileUtils.removeObjectId(((WdFile)localObject4).fullPath);
                    if (!((String)localObject4).equals(localObject2)) {
                      continue;
                    }
                    reload();
                    continue;
                    if ((!((String)localObject4).equals(localObject3)) || (!StringUtils.isEquals("Cut", localWdActivity.activityType))) {
                      continue;
                    }
                    reload();
                    continue;
                    localObject2 = FileUtils.getParent(localWdActivity.fullPath);
                    localObject3 = FileUtils.getParent(localWdActivity.uploadPath);
                    str = FileUtils.removeObjectId(getWdFileSystem().getCurrentFolder().fullPath);
                    if (!StringUtils.isEquals(((Device)localObject4).getId(), localWdActivity.deviceId)) {
                      continue;
                    }
                    if ((!str.equals(localObject2)) || (!StringUtils.isEquals("Cut", localWdActivity.activityType))) {
                      continue;
                    }
                    reload();
                    continue;
                    if (!isLandscapePad()) {
                      continue;
                    }
                    localObject3 = getWdFileSystem().getCurrentChildFolder();
                    if ((localObject3 == null) || (!StringUtils.isEquals(FileUtils.removeObjectId(((WdFile)localObject3).fullPath), (String)localObject2)) || (!StringUtils.isEquals("Cut", localWdActivity.activityType))) {
                      continue;
                    }
                    reload();
                    continue;
                    if (!StringUtils.isEquals(((Device)localObject4).getId(), localWdActivity.uploadDeviceId)) {
                      continue;
                    }
                    if (!str.equals(localObject3)) {
                      continue;
                    }
                    reload();
                    continue;
                    if (!isLandscapePad()) {
                      continue;
                    }
                    localObject2 = getWdFileSystem().getCurrentChildFolder();
                    if ((localObject2 == null) || (!StringUtils.isEquals(FileUtils.removeObjectId(((WdFile)localObject2).fullPath), (String)localObject3))) {
                      continue;
                    }
                    reload();
                    continue;
                    if (((!StringUtils.isEquals(localWdActivity.activityType, "Delete")) && (!StringUtils.isEquals("Rename", localWdActivity.activityType))) || (localWdActivity.status != 0)) {
                      continue;
                    }
                    localObject2 = localWdActivity.fullPath;
                    localObject3 = localObject2;
                    if (!localWdActivity.getDevice().isGoogleDrive()) {
                      continue;
                    }
                    localObject3 = FileUtils.removeObjectId((String)localObject2);
                    localObject4 = ((String)localObject3).substring(0, ((String)localObject3).lastIndexOf("/"));
                    localObject2 = localObject4;
                    if (!StringUtils.isEquals((String)localObject4, "")) {
                      continue;
                    }
                    localObject2 = FileUtils.getPath((String)localObject3);
                    if (localObject2 == null) {
                      continue;
                    }
                    localObject4 = FileUtils.removeObjectId(getWdFileSystem().getCurrentFolder().fullPath);
                    localObject3 = null;
                    if (getWdFileSystem().getCurrentChildFolder() == null) {
                      continue;
                    }
                    localObject3 = FileUtils.removeObjectId(getWdFileSystem().getCurrentChildFolder().fullPath);
                    if (this.mPageIndex != 4) {
                      continue;
                    }
                    reloadSearch();
                    if ((!StringUtils.isEquals((String)localObject2, (String)localObject4)) && ((getWdFileSystem().getCurrentChildFolder() == null) || (!StringUtils.isEquals((String)localObject2, (String)localObject3)))) {
                      continue;
                    }
                    reload();
                    continue;
                    getWdFileSystem().setDirty(getWdFileSystem().getCurrentFolder());
                    if (getWdFileSystem().getCurrentChildFolder() == null) {
                      continue;
                    }
                    getWdFileSystem().setDirty(getWdFileSystem().getCurrentChildFolder());
                    continue;
                    if (k != 5) {
                      continue;
                    }
                    localObject2 = this.mFileFragment.getCurrentClipped();
                    if (localObject2 == null) {
                      continue;
                    }
                    if (!"Download".equals(localWdActivity.activityType)) {
                      continue;
                    }
                    localObject3 = localWdActivity.fullPath.substring(0, localWdActivity.fullPath.lastIndexOf("/"));
                    if ((localObject3 == null) || (!StringUtils.isEquals((String)localObject3, ((WdActivity)localObject2).fullPath)) || (!StringUtils.isEquals(((WdActivity)localObject2).deviceId, localWdActivity.deviceId))) {
                      continue;
                    }
                    reload();
                    continue;
                    localObject2 = localWdActivity.parentId;
                    if ((!"Download".equals(localWdActivity.activityType)) || (localObject2 == null) || (!StringUtils.isEquals((String)localObject2, "root"))) {
                      continue;
                    }
                    reload();
                    continue;
                    if (k != 6) {
                      continue;
                    }
                    if ((!StringUtils.isEquals(localWdActivity.activityType, "Delete")) && (!StringUtils.isEquals(localWdActivity.activityType, "Copy")) && (!StringUtils.isEquals(localWdActivity.activityType, "Cut"))) {
                      continue;
                    }
                    if ((localWdActivity.status != 0) && (localWdActivity.status != -3)) {
                      continue;
                    }
                    delayedReload(System.currentTimeMillis());
                    continue;
                    if ((!StringUtils.isEquals(localWdActivity.activityType, "Upload")) || (localWdActivity.uploadStatus != 0)) {
                      continue;
                    }
                    delayedReload(System.currentTimeMillis());
                    continue;
                    localObject3 = localWdActivity.fullPath.substring(0, localWdActivity.fullPath.lastIndexOf("/"));
                    localObject4 = new WdFile();
                    ((WdFile)localObject4).fullPath = ((String)localObject3);
                    ((WdFile)localObject4).name = FileUtils.getName((String)localObject3);
                    ((WdFile)localObject4).isFolder = true;
                    ((WdFile)localObject4).modifiedDate = System.currentTimeMillis();
                    ((WdFile)localObject4).size = 0L;
                    ((WdFile)localObject4).mDevice = getWdFileSystem().getCurrentFolder().getDevice();
                    getWdFileSystem().setDirty((WdFile)localObject4);
                    ((WdFile)localObject4).fullPath = ((String)localObject2);
                    ((WdFile)localObject4).name = FileUtils.getName((String)localObject2);
                    getWdFileSystem().setDirty((WdFile)localObject4);
                    continue;
                    if ((!"Save as".equals(localWdActivity.activityType)) || (localWdActivity.uploadStatus != 0)) {
                      continue;
                    }
                    localObject2 = getWdFileSystem().getCurrentFolder();
                    localObject3 = ((WdFile)localObject2).getDevice();
                    if (localObject3 == null) {
                      break;
                    }
                  }
                  try
                  {
                    localObject1 = localWdActivity.fullPath.substring(0, localWdActivity.fullPath.lastIndexOf("/"));
                    localObject3 = new WdFile();
                    ((WdFile)localObject3).fullPath = ((String)localObject1);
                    ((WdFile)localObject3).name = FileUtils.getName((String)localObject1);
                    ((WdFile)localObject3).isFolder = true;
                    ((WdFile)localObject3).modifiedDate = System.currentTimeMillis();
                    ((WdFile)localObject3).size = 0L;
                    ((WdFile)localObject3).mDevice = getWdFileSystem().getCurrentFolder().getDevice();
                    getWdFileSystem().setDirty((WdFile)localObject3);
                    localObject1 = getSharedPreferences("RELOAD_MEDIA_AFTER_DELETE", 0);
                    localObject3 = MimeTypeUtils.getMimeType(localWdActivity.fullPath);
                    if (MimeTypeUtils.isImage((String)localObject3))
                    {
                      ((SharedPreferences)localObject1).edit().putBoolean("RELOAD_PHOTO", true).apply();
                      if ((k != 0) && (this.mPageIndex != 4)) {
                        continue;
                      }
                      if (!localWdActivity.isMove()) {
                        continue;
                      }
                      if (localWdActivity.status == -3)
                      {
                        if ((StringUtils.isEquals("Cut", localWdActivity.activityType)) && (localWdActivity.status == 0) && (localWdActivity.uploadStatus == 0) && (localWdActivity.downloadStatus == 0)) {
                          this.mFileFragment.removeItem(localWdActivity.fullPath);
                        }
                        if ((!"Upload".equals(localWdActivity.activityType)) || (localWdActivity.uploadStatus != 0)) {
                          continue;
                        }
                        localObject4 = getWdFileSystem().getCurrentFolder().fullPath;
                        str = FileUtils.getParent(localWdActivity.uploadPath);
                        localObject3 = localObject4;
                        localObject1 = str;
                        if (localWdActivity.getUploadDevice() != null)
                        {
                          localObject3 = localObject4;
                          localObject1 = str;
                          if (localWdActivity.getUploadDevice().isGoogleDrive())
                          {
                            localObject3 = FileUtils.removeObjectId((String)localObject4);
                            localObject1 = FileUtils.removeObjectId(localWdActivity.uploadPath);
                            j = ((String)localObject1).length() - localWdActivity.name.length() - 1;
                            i = j;
                            if (j < 1) {
                              i = 1;
                            }
                            localObject1 = ((String)localObject1).substring(0, i);
                          }
                        }
                        if (!((String)localObject3).equals(localObject1)) {
                          continue;
                        }
                        localObject1 = FileUtils.getName(localWdActivity.fullPath);
                        if ((!StringUtils.isEquals((String)localObject1, localWdActivity.name)) && (this.mFileFragment != null) && (this.mThumbnailWorker != null) && (this.mFileFragment.getFileSystemAdapter() != null))
                        {
                          localObject1 = FileUtils.getPath(localWdActivity.uploadPath) + (String)localObject1;
                          localObject1 = this.mFileFragment.getFileSystemAdapter().getWdFile((String)localObject1);
                          this.mThumbnailWorker.generateDuplicateThumb((WdFile)localObject1, localWdActivity);
                        }
                        this.mFileFragment.reloadCloudTab();
                        if ((k == 5) && (StringUtils.isEquals(localWdActivity.uploadPath, localWdActivity.fullPath))) {
                          reload();
                        }
                        localObject1 = getWdFileSystem().getCurrentFolder();
                        if ((getWdFileManager().getMoveType().get() == 717) && (localWdActivity.getDevice() != null) && (localWdActivity.getDevice().isSDCard()) && (((WdFile)localObject1).getDevice().isSDCard()) && (TextUtils.equals(((WdFile)localObject1).fullPath, FileUtils.getParent(localWdActivity.fullPath)))) {
                          this.mFileFragment.reloadCloudTab();
                        }
                        notifyBadgeChanged(paramMessage.arg1);
                      }
                    }
                    else if (MimeTypeUtils.isAudio((String)localObject3))
                    {
                      ((SharedPreferences)localObject1).edit().putBoolean("RELOAD_MUSIC", true).apply();
                      continue;
                    }
                  }
                  catch (Exception localException1)
                  {
                    Log.i(tag, localException1.getMessage(), localException1);
                    continue;
                  }
                  if (((Device)localObject3).isSDCard())
                  {
                    if (TextUtils.equals(((WdFile)localObject2).fullPath, FileUtils.getParent(localWdActivity.downloadPath)))
                    {
                      this.mFileFragment.reloadCloudTab();
                    }
                    else
                    {
                      localObject2 = localWdActivity.downloadPath.substring(0, localWdActivity.downloadPath.lastIndexOf("/"));
                      localObject3 = new WdFile();
                      ((WdFile)localObject3).fullPath = ((String)localObject2);
                      ((WdFile)localObject3).name = FileUtils.getName((String)localObject2);
                      ((WdFile)localObject3).isFolder = true;
                      ((WdFile)localObject3).modifiedDate = System.currentTimeMillis();
                      ((WdFile)localObject3).size = 0L;
                      ((WdFile)localObject3).mDevice = getWdFileSystem().getCurrentFolder().getDevice();
                      getWdFileSystem().setDirty((WdFile)localObject3);
                    }
                  }
                  else if ((TextUtils.equals(((Device)localObject3).id, localWdActivity.deviceId)) && (getWdFileManager().getMoveType().get() == 717))
                  {
                    localObject4 = ((WdFile)localObject2).fullPath;
                    str = FileUtils.getParent(localWdActivity.fullPath);
                    localObject3 = localObject4;
                    localObject2 = str;
                    if (localWdActivity.getDevice() != null)
                    {
                      localObject3 = localObject4;
                      localObject2 = str;
                      if (localWdActivity.getDevice().isGoogleDrive())
                      {
                        localObject3 = FileUtils.removeObjectId((String)localObject4);
                        localObject2 = FileUtils.removeObjectId(localWdActivity.fullPath);
                        j = ((String)localObject2).length() - localWdActivity.name.length() - 1;
                        i = j;
                        if (j < 1) {
                          i = 1;
                        }
                        localObject2 = ((String)localObject2).substring(0, i);
                      }
                    }
                    if (((String)localObject3).equals(localObject2))
                    {
                      this.mFileFragment.reloadCloudTab();
                    }
                    else
                    {
                      localObject2 = localWdActivity.fullPath.substring(0, localWdActivity.fullPath.lastIndexOf("/"));
                      localObject3 = new WdFile();
                      ((WdFile)localObject3).fullPath = ((String)localObject2);
                      ((WdFile)localObject3).name = FileUtils.getName((String)localObject2);
                      ((WdFile)localObject3).isFolder = true;
                      ((WdFile)localObject3).modifiedDate = System.currentTimeMillis();
                      ((WdFile)localObject3).size = 0L;
                      ((WdFile)localObject3).mDevice = getWdFileSystem().getCurrentFolder().getDevice();
                      getWdFileSystem().setDirty((WdFile)localObject3);
                    }
                  }
                }
                paramMessage = (WdActivity)paramMessage.obj;
                if (paramMessage != null) {
                  if (k == 6)
                  {
                    paramMessage = (WdActivityItemView)getItemView(paramMessage.id);
                    if (paramMessage != null) {
                      paramMessage.showProgressBar(true);
                    }
                  }
                  else if (k == 0)
                  {
                    paramMessage = (FileItemView)getItemView(FileUtils.generateWdFileItemId(paramMessage.fullPath));
                    if (paramMessage != null)
                    {
                      paramMessage.showActivityIconBar(true);
                      return;
                      paramMessage = (WdActivity)paramMessage.obj;
                      if (paramMessage != null)
                      {
                        loadLocalFileSystem(new WdActivity[] { paramMessage });
                        return;
                        switch (getTabIndex())
                        {
                        case 0: 
                          reload();
                          return;
                        case 5: 
                          loadLocalFileSystem(new WdActivity[0]);
                          return;
                        case 6: 
                          loadWdActivity(new WdActivity[0]);
                          return;
                          paramMessage = (WdActivity)paramMessage.obj;
                          if (paramMessage != null)
                          {
                            if (paramMessage != null)
                            {
                              if (("Cut".equals(paramMessage.activityType)) && (paramMessage.status == 0)) {
                                this.mFileFragment.removeItem(paramMessage.fullPath);
                              }
                              if (k != 6) {
                                break label2982;
                              }
                              localObject2 = (WdActivityItemView)getItemView(paramMessage.id);
                              if (localObject2 != null) {
                                ((WdActivityItemView)localObject2).showCompleted(paramMessage);
                              }
                              this.mFileFragment.updateActivityStatus(paramMessage);
                              reload();
                            }
                            for (;;)
                            {
                              NotificationUtils.showFailedNotification(this);
                              return;
                              if (k == 0)
                              {
                                paramMessage = (FileItemView)getItemView(FileUtils.generateWdFileItemId(paramMessage.fullPath));
                                if (paramMessage != null)
                                {
                                  paramMessage.showActivityIconBar(false);
                                  if (getCurrentDevice().isKorraDevice()) {
                                    paramMessage.updateFailed();
                                  }
                                }
                              }
                            }
                            localObject3 = (WdActivity)paramMessage.obj;
                            if (localObject3 != null)
                            {
                              this.mWdFileManager.setShowWarningActivity((WdActivity)localObject3);
                              if (((WdActivity)localObject3).errorCode == 702) {}
                              for (;;)
                              {
                                if (getTabIndex() == 6)
                                {
                                  reload();
                                  return;
                                  if (((WdActivity)localObject3).errorCode == 706)
                                  {
                                    Toast.makeText(this.mApplication, getString(2131296700, new Object[] { ((WdActivity)localObject3).name }), 1).show();
                                  }
                                  else if (((WdActivity)localObject3).errorCode == 701)
                                  {
                                    if (this.mWdFileManager.getConfiguration().showDataPlanWarn())
                                    {
                                      this.mWarningDialog = DialogUtils.makeConfirmDialogExt(this, getString(2131296786), new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
                                      {
                                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                                        {
                                          List localList = MyDeviceActivity.this.getAssignTaskList();
                                          int j = 0;
                                          int i = 0;
                                          int k = 0;
                                          paramAnonymousInt = 0;
                                          if (!localList.isEmpty())
                                          {
                                            paramAnonymousDialogInterface = localList.iterator();
                                            for (;;)
                                            {
                                              j = i;
                                              k = paramAnonymousInt;
                                              if (!paramAnonymousDialogInterface.hasNext()) {
                                                break;
                                              }
                                              if (((WdActivity)paramAnonymousDialogInterface.next()).isFolder) {
                                                paramAnonymousInt += 1;
                                              } else {
                                                i += 1;
                                              }
                                            }
                                          }
                                          paramAnonymousDialogInterface = new WdActivity(this.val$wdActivity1.getDevice(), "Download");
                                          if (StringUtils.isEquals(this.val$wdActivity1.activityType, "Save as")) {
                                            paramAnonymousDialogInterface = new WdActivity(this.val$wdActivity1.getDevice(), "Save as");
                                          }
                                          for (;;)
                                          {
                                            paramAnonymousDialogInterface.fileCount = j;
                                            paramAnonymousDialogInterface.folderCount = k;
                                            paramAnonymousDialogInterface.isFolder = true;
                                            paramAnonymousInt = j + k;
                                            paramAnonymousDialogInterface.name = String.format(MyDeviceActivity.this.getResources().getQuantityString(2131427328, paramAnonymousInt), new Object[] { Integer.valueOf(paramAnonymousInt) });
                                            if ((localList == null) || (localList.isEmpty()) || (localList.size() <= 0)) {
                                              break;
                                            }
                                            paramAnonymousDialogInterface = localList.iterator();
                                            while (paramAnonymousDialogInterface.hasNext())
                                            {
                                              WdActivity localWdActivity = (WdActivity)paramAnonymousDialogInterface.next();
                                              MyDeviceActivity.this.mWdFileManager.getDatabaseAgent().insertOrUpdateWdActivity(localWdActivity);
                                              MyDeviceActivity.this.addWdActivityTask(localWdActivity);
                                            }
                                            if (this.val$wdActivity1.isMove()) {
                                              paramAnonymousDialogInterface = new WdActivity(this.val$wdActivity1.getDevice(), "Cut");
                                            }
                                          }
                                          localList.clear();
                                        }
                                      }, new DialogInterface.OnClickListener()
                                      {
                                        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                                        {
                                          paramAnonymousDialogInterface = MyDeviceActivity.this.getAssignTaskList();
                                          if ((paramAnonymousDialogInterface != null) && (!paramAnonymousDialogInterface.isEmpty()) && (paramAnonymousDialogInterface.size() > 0))
                                          {
                                            Iterator localIterator = paramAnonymousDialogInterface.iterator();
                                            while (localIterator.hasNext())
                                            {
                                              WdActivity localWdActivity = (WdActivity)localIterator.next();
                                              MyDeviceActivity.this.mWdFileManager.getDatabaseAgent().delete(localWdActivity);
                                            }
                                            MyDeviceActivity.this.mWdFileManager.showBadge(false);
                                          }
                                          paramAnonymousDialogInterface.clear();
                                        }
                                      });
                                      this.mWarningDialog.show();
                                      continue;
                                    }
                                    localObject2 = getAssignTaskList();
                                    k = 0;
                                    j = 0;
                                    m = 0;
                                    i = 0;
                                    if (!((List)localObject2).isEmpty()) {
                                      paramMessage = ((List)localObject2).iterator();
                                    }
                                  }
                                }
                              }
                            }
                          }
                          break;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    for (;;)
    {
      k = j;
      m = i;
      if (paramMessage.hasNext())
      {
        if (((WdActivity)paramMessage.next()).isFolder) {
          i += 1;
        }
      }
      else
      {
        paramMessage = new WdActivity(((WdActivity)localObject3).getDevice(), "Download");
        if (StringUtils.isEquals(((WdActivity)localObject3).activityType, "Save as")) {
          paramMessage = new WdActivity(((WdActivity)localObject3).getDevice(), "Save as");
        }
        for (;;)
        {
          paramMessage.fileCount = k;
          paramMessage.folderCount = m;
          paramMessage.isFolder = true;
          i = k + m;
          paramMessage.name = String.format(getResources().getQuantityString(2131427328, i), new Object[] { Integer.valueOf(i) });
          if ((localObject2 == null) || (((List)localObject2).isEmpty()) || (((List)localObject2).size() <= 0)) {
            break;
          }
          paramMessage = ((List)localObject2).iterator();
          while (paramMessage.hasNext())
          {
            localObject3 = (WdActivity)paramMessage.next();
            this.mWdFileManager.getDatabaseAgent().insertOrUpdateWdActivity((WdActivity)localObject3);
            addWdActivityTask((WdActivity)localObject3);
          }
          if (((WdActivity)localObject3).isMove()) {
            paramMessage = new WdActivity(((WdActivity)localObject3).getDevice(), "Cut");
          }
        }
        ((List)localObject2).clear();
        break label3064;
        if (((WdActivity)localObject3).errorCode == 404)
        {
          this.mWarningDialog = DialogUtils.error(this, getString(2131296780), getString(2131296866, new Object[] { ((WdActivity)localObject3).name }), null);
          break label3064;
        }
        if (((WdActivity)localObject3).errorCode == 715)
        {
          this.mWarningDialog = DialogUtils.warn(this, getString(2131296950), getString(2131296949), null);
          break label3064;
        }
        if (((WdActivity)localObject3).errorCode == 703)
        {
          paramMessage = ((WdActivity)localObject3).getUploadDevice();
          if (paramMessage == null) {
            break label3974;
          }
          if (paramMessage.isSkyDrive())
          {
            this.mWarningDialog = DialogUtils.error(this, getString(2131296780), getString(2131297367), null);
            break label3064;
          }
          if (paramMessage.isDropbox())
          {
            this.mWarningDialog = DialogUtils.error(this, getString(2131296780), getString(2131296754), null);
            break label3064;
          }
          if (paramMessage.isBaiduNetdisk())
          {
            this.mWarningDialog = DialogUtils.error(this, getString(2131296780), getString(2131296535), null);
            break label3064;
          }
          if ((paramMessage.isGoogleDrive()) || (paramMessage.isBox())) {
            break label3064;
          }
          this.mWarningDialog = DialogUtils.error(this, getString(2131296780), getString(2131296820), null);
          break label3064;
        }
        if (((WdActivity)localObject3).errorCode == 704)
        {
          paramMessage = ((WdActivity)localObject3).getUploadDevice();
          if ((paramMessage == null) || (paramMessage.deviceName == null)) {
            break label3974;
          }
          this.mWarningDialog = DialogUtils.error(this, getString(2131296344), getString(2131297069, new Object[] { paramMessage.deviceName }), null);
          break label3064;
        }
        if (((WdActivity)localObject3).errorCode == 705)
        {
          localObject2 = ((WdActivity)localObject3).getDevice();
          if ((localObject2 != null) && (((Device)localObject2).deviceName != null))
          {
            paramMessage = ((Device)localObject2).deviceType;
            if (paramMessage != null)
            {
              localObject3 = paramMessage.typeName;
              if (localObject3 != null)
              {
                paramMessage = "02.32.05-044";
                if (StringUtils.isEquals((String)localObject3, "My Book Live")) {
                  paramMessage = "02.32.05-044";
                }
                for (;;)
                {
                  showResponseError(new ResponseException(3, getString(2131296819, new Object[] { ((Device)localObject2).deviceName, paramMessage })));
                  break;
                  if (StringUtils.isEquals((String)localObject3, "My Book Live Duo")) {
                    paramMessage = "02.31.08-068";
                  } else if (StringUtils.isEquals((String)localObject3, "My Net N900 Central")) {
                    paramMessage = "1.05.12";
                  }
                }
              }
            }
          }
        }
        else
        {
          if (((WdActivity)localObject3).errorCode == 1)
          {
            doRename((WdActivity)localObject3, ((WdActivity)localObject3).name);
            break label3064;
          }
          showResponseError(new ResponseException(((WdActivity)localObject3).errorCode));
          break label3064;
        }
        label3974:
        return;
        label3975:
        i = 0;
        break;
        return;
      }
      j += 1;
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    try
    {
      if (this.mMediaFragmentManager.getMusicPlayFragment() != null) {
        this.mMediaFragmentManager.getMusicPlayFragment().onKeyDown(paramInt, paramKeyEvent);
      }
      return super.onKeyDown(paramInt, paramKeyEvent);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(tag, "onKeyDown for MusicPlayerFragmentException ", localException);
      }
    }
  }
  
  public void onLowMemory()
  {
    try
    {
      super.onLowMemory();
      WdFileSystem localWdFileSystem = getWdFileSystem();
      if (localWdFileSystem != null) {
        localWdFileSystem.freeMemory();
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, localException.getMessage(), localException);
    }
  }
  
  public boolean onMediaBackPressed()
  {
    if (this.mMediaFragmentManager != null) {
      return this.mMediaFragmentManager.onBackPressed();
    }
    return false;
  }
  
  public void onMenuClick()
  {
    if (this.mSlideDrawer.isDrawerAnimationing()) {
      return;
    }
    this.mFromOtherApp.set(false);
    showPopupMenu(false);
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    try
    {
      super.onNewIntent(paramIntent);
      setIntent(paramIntent);
      if (!isPhone())
      {
        if ((this.mMediaFragmentManager != null) && (this.mMediaFragmentManager.getMusicPlayFragment() != null)) {
          return;
        }
        if (!getIntent().getBooleanExtra("music_player_intent", false)) {
          return;
        }
        paramIntent = new Intent();
        paramIntent.setClass(this, MusicPlayerActivity.class);
        startActivity(paramIntent);
        return;
      }
    }
    catch (Exception paramIntent)
    {
      Log.e(tag, "onNewIntent exception ", paramIntent);
      return;
    }
    if (getIntent().getBooleanExtra("music_player_intent", false))
    {
      paramIntent = new Intent();
      paramIntent.setClass(this, MusicPlayerActivity.class);
      startActivity(paramIntent);
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 2131821901)
    {
      getFolderListFragment().initMenu(findViewById(2131821901));
      showPopupMenu(false);
      return true;
    }
    if (paramMenuItem.getItemId() == 2131821903)
    {
      showSearchViewBar();
      return true;
    }
    if (paramMenuItem.getItemId() == 2131821899)
    {
      onShareClick();
      return true;
    }
    if (paramMenuItem.getItemId() == 2131821902)
    {
      onCastClick();
      return true;
    }
    try
    {
      doOperation(paramMenuItem.getItemId());
      return true;
    }
    catch (Exception paramMenuItem)
    {
      Log.e(tag, paramMenuItem.getMessage(), paramMenuItem);
    }
    return true;
  }
  
  protected void onPause()
  {
    try
    {
      super.onPause();
      LocalCreateDeviceUserAccountRunnable.scRefreshListener = null;
      this.mWdFileManager.removeCacheListener(this.mProgressBarListener);
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, localException.getMessage(), localException);
    }
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    super.onPrepareOptionsMenu(paramMenu);
    if ((this.mShareMenuItem != null) && ((this.mShareMenuItem.isVisible() ^ this.mShowShareMenuItem))) {
      this.mShareMenuItem.setVisible(this.mShowShareMenuItem);
    }
    if ((this.mSearchMenuItem != null) && ((this.mSearchMenuItem.isVisible() ^ this.mShowSearchMenuItem))) {
      this.mSearchMenuItem.setVisible(this.mShowSearchMenuItem);
    }
    if ((this.mOverflowMenuItem != null) && ((this.mOverflowMenuItem.isVisible() ^ this.mShowOverflowMenuItem))) {
      this.mOverflowMenuItem.setVisible(this.mShowOverflowMenuItem);
    }
    if ((this.mCastMenuItem != null) && ((this.mCastMenuItem.isVisible() ^ this.mShowCastMenuItem))) {
      this.mCastMenuItem.setVisible(this.mShowCastMenuItem);
    }
    return true;
  }
  
  public boolean onQueryTextChange(String paramString)
  {
    return false;
  }
  
  public boolean onQueryTextSubmit(String paramString)
  {
    startQuery(paramString);
    return false;
  }
  
  public void onRecipientsButtonClicked() {}
  
  public void onRefresh(String paramString)
  {
    Object localObject;
    if (getTabIndex() == 5)
    {
      localObject = this.mFileFragment.getCurrentClipped();
      if (localObject == null) {
        this.mHandler.sendEmptyMessage(11);
      }
    }
    do
    {
      do
      {
        do
        {
          return;
          if (this.mWdFileManager.getClippedByPath((WdActivity)localObject) == null)
          {
            this.mHandler.sendEmptyMessage(11);
            return;
          }
        } while ((!((WdActivity)localObject).fullPath.equals(paramString)) && (!((WdActivity)localObject).fullPath.startsWith(paramString + "/")));
        localObject = new Message();
        ((Message)localObject).what = 10;
        ((Message)localObject).obj = paramString;
        this.mHandler.sendMessage((Message)localObject);
        return;
      } while (isAccessLocalResource());
      if (this.mWdFileSystem != null) {
        this.mWdFileSystem = this.mWdFileManager.getWdFileSystem(null);
      }
      localObject = this.mWdFileSystem.getCurrentFolder();
    } while ((localObject == null) || ((!paramString.startsWith(((WdFile)localObject).fullPath + "/")) && (!paramString.equals(((WdFile)localObject).fullPath))));
    this.mHandler.sendEmptyMessage(12);
  }
  
  public void onRefreshDevices()
  {
    List localList = this.mWdFileManager.getDatabaseAgent().getAllDevices();
    this.mFileFragment.refreshAfterDeviceListLoad(localList, true);
  }
  
  public void onRequestPermissionsResult(final int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
    if (paramArrayOfInt.length > 0)
    {
      int i = 0;
      if (i < paramArrayOfInt.length)
      {
        switch (paramArrayOfInt[i])
        {
        }
        for (;;)
        {
          i += 1;
          break;
          this.mRequiredPermissions.remove(paramArrayOfString[i]);
          if (paramArrayOfString[i].equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
            switch (paramInt)
            {
            case 777: 
            case 778: 
            case 779: 
            case 780: 
            default: 
              break;
            case 771: 
              showUploadContextMenu();
              break;
            case 772: 
              startDownloadFile();
              break;
            case 773: 
              saveAs();
              break;
            case 774: 
              this.mMoveFileWindow.loadFileList(false, 0, new WdFile[0]);
              break;
            case 775: 
              startMoveFile(717);
              break;
            case 776: 
              this.mSlideDrawer.initializeDrawerClick(this.mDevice);
              break;
            case 781: 
              showAddFileDialog();
              continue;
              if (ActivityCompat.shouldShowRequestPermissionRationale(this, paramArrayOfString[i]))
              {
                if (paramArrayOfString[i].equals("android.permission.WRITE_EXTERNAL_STORAGE"))
                {
                  CharSequence localCharSequence = PermissionsUtils.getMessage(this, paramInt);
                  new AlertDialog.Builder(this).setTitle(2131297136).setMessage(localCharSequence).setPositiveButton(getString(2131297104), new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                      ActivityCompat.requestPermissions(MyDeviceActivity.this, (String[])MyDeviceActivity.this.mRequiredPermissions.toArray(new String[MyDeviceActivity.this.mRequiredPermissions.size()]), paramInt);
                    }
                  }).setNegativeButton(getString(2131296557), new DialogInterface.OnClickListener()
                  {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                    {
                      paramAnonymousDialogInterface.dismiss();
                    }
                  }).show();
                }
              }
              else {
                new AlertDialog.Builder(this).setTitle(2131297136).setMessage(getText(2131297033)).setPositiveButton(getString(2131297282), new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                  {
                    paramAnonymousDialogInterface = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    paramAnonymousDialogInterface.setData(Uri.fromParts("package", MyDeviceActivity.this.getPackageName(), null));
                    MyDeviceActivity.this.startActivityForResult(paramAnonymousDialogInterface, paramInt);
                  }
                }).setNegativeButton(getString(2131296557), new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
                  {
                    paramAnonymousDialogInterface.dismiss();
                  }
                }).show();
              }
              break;
            }
          }
        }
      }
    }
  }
  
  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    this.mViewPager.setOnPageChangeListener(null);
    super.onRestoreInstanceState(paramBundle);
    this.mViewPager.setOnPageChangeListener(this.onPageChangeListener);
    if (this.mViewPager != null) {
      this.mViewPager.setCurrentItem(0);
    }
  }
  
  protected void onResume()
  {
    for (;;)
    {
      try
      {
        super.onResume();
        this.mApplication.launchMediaStoreObserverService();
        if (this.mSlideDrawer == null) {
          this.mSlideDrawer = new SlideDrawer(this, this.mDrawerLayout, null);
        }
        if (this.mBreadcrumbListAdapter == null) {
          this.mBreadcrumbListAdapter = new BreadcrumbListAdapter(this, null);
        }
        if (this.mPageIndex != 0) {
          continue;
        }
        this.mNavigationSpinner.setAdapter(this.mBreadcrumbListAdapter);
        this.mNavigationSpinner.setOnItemSelectedListener(this.mBreadcrumbListAdapter);
        this.mWdFileManager.clearProxyClient();
        this.mWdFileManager.addCacheListener(this.mProgressBarListener);
        if (this.mWdFileManager.getFileOpening() != null)
        {
          clearCurrentAction();
          if (this.mWdFileManager.isOpenedFileChanged()) {
            try2Save();
          }
        }
        try2Add(null);
        if (this.mWdFileManager.isExternalStorageWriteable()) {
          continue;
        }
        showNoSdCardDialog();
      }
      catch (Exception localException1)
      {
        Log.e(tag, localException1.getMessage(), localException1);
        try
        {
          gotoDeviceList();
          return;
        }
        catch (Exception localException2)
        {
          Log.d(tag, localException2.getMessage(), localException2);
          goToSignInOrFinish();
          return;
        }
        removeNoSdCardDialog();
        continue;
      }
      LocalCreateDeviceUserAccountRunnable.scRefreshListener = this;
      this.mWdFileManager.showBadge(true);
      changeMusicPlayerNotification(false);
      return;
      if ((this.mPageIndex == 1) || (this.mPageIndex == 2) || (this.mPageIndex == 3))
      {
        this.mNavigationSpinner.setAdapter(this.mMediaBreadcrumbListAdapter);
        this.mNavigationSpinner.setOnItemSelectedListener(this.mMediaBreadcrumbListAdapter);
      }
    }
  }
  
  public boolean onSearchRequested()
  {
    Object localObject = getWdFileManager().getCurrentDevice();
    String str;
    if ((localObject != null) && (((Device)localObject).isMediaSupported()))
    {
      localObject = null;
      if (this.mPageIndex != 0) {
        break label116;
      }
      SearchView.SearchFilter localSearchFilter = new SearchView.SearchFilter();
      str = this.mFileFragment.getCurrentFolderName();
      if (!str.equals("/")) {
        break label94;
      }
      localObject = new String();
      localSearchFilter.title = ((String)localObject);
      localSearchFilter.path = getWdFileSystem().getCurrentFolder().fullPath;
      localObject = localSearchFilter;
    }
    for (;;)
    {
      showSearchView((SearchView.SearchFilter)localObject);
      return false;
      label94:
      localObject = str;
      if (!str.startsWith("/Public")) {
        break;
      }
      localObject = str.substring(7);
      break;
      label116:
      if (this.mPageIndex == 1)
      {
        localObject = getCurrentMediaPath();
        ((SearchView.SearchFilter)localObject).title = getResources().getString(2131297141);
      }
      else if (this.mPageIndex == 2)
      {
        localObject = getCurrentMediaPath();
        ((SearchView.SearchFilter)localObject).title = getResources().getString(2131297014);
      }
      else if (this.mPageIndex == 3)
      {
        localObject = getCurrentMediaPath();
        ((SearchView.SearchFilter)localObject).title = getResources().getString(2131297488);
      }
    }
  }
  
  public void onSendShareClick()
  {
    if (this.mSharePrivateFragment != null) {
      this.mSharePrivateFragment.postShare();
    }
  }
  
  public void onSendShareResult(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      setEditEnable(false);
      if (this.mToolBarShareActionMode != null) {
        this.mToolBarShareActionMode.finish();
      }
      this.mSharePrivateFragment = null;
    }
  }
  
  public void onShareClick()
  {
    changeMusicPlayerNotification(true);
    if (isAccessLocalResource()) {
      DialogUtils.error(this, null, getString(2131296421), null);
    }
    WdFile localWdFile;
    do
    {
      return;
      localWdFile = this.mWdFileSystem.getCurrentFolder();
    } while (localWdFile == null);
    checkAndShare(localWdFile, null);
  }
  
  public void onSlideDrawerChange(boolean paramBoolean)
  {
    invalidateOptionsMenu();
    if ((this.mViewPager.getCurrentItem() == 4) || (this.isJumpToShareMe))
    {
      this.mShareWithMeFragment.onSlideDrawerChanged(paramBoolean);
      return;
    }
    if (this.mPageIndex == 0)
    {
      this.mFileFragment.onSlideDrawerChange(paramBoolean);
      return;
    }
    this.mMediaFragmentManager.onSildeDrawerChanged(paramBoolean);
  }
  
  protected void onStart()
  {
    WifiManager localWifiManager = (WifiManager)getSystemService("wifi");
    if ((localWifiManager != null) && (localWifiManager.isWifiEnabled()))
    {
      if (this.mWifiLock == null)
      {
        this.mWifiLock = localWifiManager.createWifiLock("WifiService");
        this.mWifiLock.setReferenceCounted(true);
      }
      if (!this.mWifiLock.isHeld()) {
        this.mWifiLock.acquire();
      }
      Log.i(tag, "MyDeviceActivity.acquire lock");
    }
    super.onStart();
    this.mSearchBarFragment.init(this);
    if (!RateAppHelper.getOptOut(this)) {
      ThreadPool.excuteShortTask(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  public boolean onSuggestionClick(int paramInt)
  {
    return false;
  }
  
  public boolean onSuggestionSelect(int paramInt)
  {
    return false;
  }
  
  public void onValidateShareInputResult(boolean paramBoolean) {}
  
  public void openDrawer()
  {
    if (this.mSlideDrawer != null) {
      this.mSlideDrawer.openDrawer();
    }
  }
  
  public void openFolder(WdFile paramWdFile)
    throws ResponseException
  {
    if (this.mFileFragment != null) {
      this.mFileFragment.openFolder(paramWdFile);
    }
  }
  
  public void openWdActivity(final WdActivity paramWdActivity)
  {
    if (paramWdActivity == null) {
      return;
    }
    boolean bool = this.mWdFileManager.getConfiguration().showDataPlanWarn();
    if ((this.mWdFileManager.isMobileNetwork()) && (!paramWdActivity.isFileDownloaded()) && (paramWdActivity.size > 10485760L) && (bool)) {}
    for (int i = 1; i != 0; i = 0)
    {
      DialogUtils.makeConfirmDialogExt(this, getString(2131296786), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyDeviceActivity.this.getOpenWdActivityLoader().execute(new WdActivity[] { paramWdActivity });
        }
      }, null).show();
      return;
    }
    getOpenWdActivityLoader().execute(new WdActivity[] { paramWdActivity });
  }
  
  public void openWdFile(WdFile paramWdFile)
    throws ResponseException
  {
    Object localObject2 = paramWdFile.fullPath;
    Object localObject1 = localObject2;
    if (paramWdFile.getDevice().isGoogleDrive()) {
      localObject1 = FileUtils.removeObjectId((String)localObject2);
    }
    if (!isIntentAvailable(paramWdFile.getWdActivity()))
    {
      openWithMarketApp((String)localObject1);
      clearCurrentAction();
      return;
    }
    localObject1 = this.mIntent.getExtras();
    localObject2 = paramWdFile.getWdActivityDownload();
    if ((localObject1 != null) && (localObject2 != null) && ("TRUE".equals(((Bundle)localObject1).get("ATTACHMENT"))))
    {
      paramWdFile = FileUtils.createTempFile((WdActivity)localObject2);
      localObject1 = new Intent();
      ((Intent)localObject1).setData(Uri.fromFile(paramWdFile));
      setResult(-1, (Intent)localObject1);
      finish();
      return;
    }
    if ((isAccessLocalResource()) && (localObject2 == null))
    {
      Toast.makeText(this, 2131296421, 1).show();
      return;
    }
    checkUserChoiceFor3G(2131297114, paramWdFile);
  }
  
  public void openWithIntent(WdActivity paramWdActivity)
  {
    showChooserDialog(paramWdActivity, false);
  }
  
  public void refresh()
  {
    doRefresh();
    loadCloudFileSystem(false, true, false, new WdFile[0]);
  }
  
  public void refreshActivityAfterLoad(List<WdActivity> paramList, WdActivity paramWdActivity1, WdActivity paramWdActivity2)
  {
    this.mFileFragment.refreshActivityAfterLoad(paramList, paramWdActivity1);
  }
  
  public void refreshActivityTab()
  {
    if (getTabIndex() == 6) {
      this.mHandler.sendEmptyMessage(12);
    }
  }
  
  public void refreshAfterDeviceListLoad(List<Device> paramList, boolean paramBoolean)
  {
    try
    {
      this.mFileFragment.refreshAfterDeviceListLoad(paramList, paramBoolean);
      return;
    }
    catch (Exception paramList)
    {
      Log.e(tag, "refreshAfterDeviceListLoad", paramList);
    }
  }
  
  public void refreshCloudAfterLoad(ReleasableList<? extends WdFile> paramReleasableList, WdFile paramWdFile, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mFileFragment.refreshAfterCloudLoad(paramReleasableList, paramWdFile, paramInt, paramBoolean1, paramBoolean2);
    if (this.mCurrentDeviceFirmwareVersion != null) {
      checkDeviceFirmware();
    }
    setBreadscrumb();
    if ((this.mDevice != null) && (!this.mDevice.isSDCard())) {}
    for (paramBoolean1 = true;; paramBoolean1 = false)
    {
      showCastLayout(paramBoolean1);
      return;
    }
  }
  
  public void refreshLocalAfterLoad(List<WdActivity> paramList, WdActivity paramWdActivity1, WdActivity paramWdActivity2)
  {
    this.mFileFragment.refreshLocalAfterLoad(paramList, paramWdActivity1);
  }
  
  public void refreshShareFragment()
  {
    if (this.mSharePrivateFragment != null) {
      this.mSharePrivateFragment.refreshGridView();
    }
    if (this.mShareTitleFragment != null)
    {
      if (!isSharingCurrentFolder()) {
        break label43;
      }
      this.mShareTitleFragment.setTitle(getString(2131297289));
    }
    label43:
    List localList;
    for (;;)
    {
      return;
      if (isSharingFromPhotoViewer()) {}
      for (localList = Arrays.asList(new WdFile[] { this.mWdFileManager.getSharePhotoFile() }); (localList != null) && (!localList.isEmpty()); localList = getSelectedWdFiles())
      {
        if ((localList.size() != 1) || (!((WdFile)localList.get(0)).isFolder)) {
          break label132;
        }
        this.mShareTitleFragment.setTitle(getString(2131297289));
        return;
      }
    }
    label132:
    this.mShareTitleFragment.setTitle(getString(2131297316, new Object[] { getResources().getQuantityString(2131427328, localList.size(), new Object[] { Integer.valueOf(localList.size()) }).toLowerCase() }));
  }
  
  public void reload()
  {
    for (boolean bool = true;; bool = false) {
      try
      {
        Object localObject = getMediaFragmentManager();
        int i = this.mViewPager.getCurrentItem();
        if (i == 0)
        {
          this.mFileFragment.reload();
          return;
        }
        localObject = ((MediaFragmentManager)localObject).getMediaFragment(i);
        if (localObject == null) {
          break;
        }
        if (i == 2)
        {
          ((AbstractFragment)localObject).loadData(true, null, bool, false);
          Log.d(tag, "fragmentToReloadContentsOf.loadData()");
          return;
        }
      }
      catch (Exception localException)
      {
        Log.w(tag, localException.getMessage(), localException);
        return;
      }
    }
  }
  
  public void reloadAfterEjectStorage()
  {
    WdFile localWdFile = getWdFileSystem().getCurrentFolder();
    Device localDevice = localWdFile.getDevice();
    if ((localDevice.isAvatarDevice()) || (localDevice.isKorraDevice()))
    {
      if ((localWdFile != null) && ((localWdFile.isRoot()) || (localWdFile.isAvatarSDCardPath()))) {
        this.mFileFragment.reloadCloudTab();
      }
    }
    else {
      return;
    }
    getWdFileSystem().setDirty(localDevice.getSDCardFolder());
    getWdFileSystem().setDirty(localDevice.getRootFile());
  }
  
  public void reloadMoveFileWindow()
  {
    try
    {
      if ((this.mMoveFileWindow != null) && (getMoveFileSystem() != null)) {
        this.mMoveFileWindow.loadFileList(true, 0, new WdFile[] { getMoveFileSystem().getCurrentFolder() });
      }
      return;
    }
    catch (Exception localException)
    {
      Log.e(tag, "reloadMoveFileWindow", localException);
    }
  }
  
  public void reloadSearch()
  {
    if (this.mPageIndex == 4) {}
  }
  
  public void removeSaveAsOrPropertyDialog()
  {
    try
    {
      if (this.mPropertyDialog != null)
      {
        if (this.mPropertyDialog.isShowing()) {
          this.mPropertyDialog.dismiss();
        }
        this.mPropertyDialog = null;
      }
      return;
    }
    catch (Exception localException)
    {
      Log.i(tag, localException.getMessage(), localException);
    }
  }
  
  public void replaceViewFlow()
  {
    super.replaceViewFlow();
    if (this.mViewFlowFragment != null)
    {
      Bundle localBundle = new Bundle();
      int i = this.mPageIndex;
      if (this.mPageIndex == 4) {
        i = this.mLastPageIndex;
      }
      localBundle.putInt("tabIndex", i);
      localBundle.putBoolean("isPortraitPad", isPortraitPad());
      this.mViewFlowFragment.setArguments(localBundle);
    }
    if (this.mMediaFragmentManager != null) {
      this.mMediaFragmentManager.clearFragment();
    }
  }
  
  public void resetLastSelectedMediaIndex()
  {
    this.lastSelectedMediaIndex = 0;
  }
  
  public void setActivityToShowDialogOn(Activity paramActivity)
  {
    this.mActivityToShowDialogOn = paramActivity;
  }
  
  public void setBreadscrumb()
  {
    if (this.mFileFragment != null) {
      this.mFileFragment.setBreadscrumb();
    }
  }
  
  public void setBreadscrumb(String paramString)
  {
    SpinnerAdapter localSpinnerAdapter = this.mNavigationSpinner.getAdapter();
    int m;
    int k;
    int i;
    if (localSpinnerAdapter != null)
    {
      m = localSpinnerAdapter.getCount();
      k = -1;
      i = 0;
    }
    for (;;)
    {
      int j = k;
      if (i < m)
      {
        WdFile localWdFile = (WdFile)localSpinnerAdapter.getItem(i);
        if ((localWdFile != null) && (StringUtils.isEquals(localWdFile.name, paramString))) {
          j = i;
        }
      }
      else
      {
        if (j >= 0) {
          this.mNavigationSpinner.setSelection(j);
        }
        return;
      }
      i += 1;
    }
  }
  
  public void setCurrentAction(DatabaseBean paramDatabaseBean, int paramInt)
  {
    if (paramDatabaseBean == null) {
      this.mWdFileManager.setCurrentAction(new ActionDoingSet(paramInt, getTabIndex()));
    }
    this.mWdFileManager.setCurrentAction(new ActionDoingSet(paramDatabaseBean, paramInt, getTabIndex()));
  }
  
  public void setCurrentDevice(Device paramDevice)
  {
    this.mDevice = paramDevice;
  }
  
  public void setDrawerTitle(int paramInt)
  {
    setDrawerTitle(getString(paramInt));
  }
  
  public void setDrawerTitle(String paramString)
  {
    this.toolbar.setTitle(paramString);
  }
  
  public void setEditEnable(boolean paramBoolean)
  {
    if (this.isEditMode == paramBoolean) {}
    boolean bool;
    label75:
    do
    {
      return;
      this.isEditMode = paramBoolean;
      if ((this.mShareTitleFragment != null) && (!this.mShareTitleFragment.isHidden()))
      {
        back2Default();
        if (this.mToolBarShareActionMode != null) {
          this.mToolBarShareActionMode.finish();
        }
      }
      if (paramBoolean) {
        break;
      }
      bool = true;
      enableBreadcrumbButton(bool);
      if (!paramBoolean) {
        break label128;
      }
      startActionMode();
      changeShadowBackground(2130837825);
      if (isDrawerOpen()) {
        onSlideDrawerChange(true);
      }
    } while (Build.VERSION.SDK_INT < 21);
    Window localWindow = getWindow();
    if (this.isEditMode) {}
    for (int i = 2131755233;; i = getStatusBarColor())
    {
      localWindow.setStatusBarColor(ContextCompat.getColor(this, i));
      return;
      bool = false;
      break;
      label128:
      changeShadowBackground(2130837823);
      getSelectedWdFiles().clear();
      getSelectedLocals().clear();
      getSelectedWdActivities().clear();
      getSelectedDeviceList().clear();
      this.mMediaFragmentManager.reset();
      this.mSlideDrawer.updateDeviceListStatus();
      if (this.mPageIndex == 4) {
        if (this.mSearchView != null) {
          this.mSearchView.reset();
        }
      }
      for (;;)
      {
        if (this.mPageIndex != 4) {
          break label281;
        }
        this.mFileFragment.notifyDataSetChange();
        this.mMediaFragmentManager.notifyAdapter();
        break;
        if (this.mViewPager.getCurrentItem() == 0)
        {
          this.mFileFragment.reset();
          this.mFileFragment.setBreadscrumb();
        }
        else
        {
          enableMediaBreadcrumb(true);
          this.mMediaFragmentManager.resetMenuIconAndTitle();
          this.mMediaFragmentManager.notifyAdapter();
        }
      }
      label281:
      finishActionMode();
      break label75;
    }
  }
  
  public void setFileFragment(FolderListFragment paramFolderListFragment)
  {
    this.mFileFragment = paramFolderListFragment;
  }
  
  public void setListBusy(boolean paramBoolean)
  {
    this.mIsListBusy = paramBoolean;
  }
  
  public void setMediaList(MediaList paramMediaList)
  {
    WdFilesApplication localWdFilesApplication = this.mApplication;
    if (paramMediaList.getCurrentMediaType() == 12) {}
    for (boolean bool = true;; bool = false)
    {
      localWdFilesApplication.setMediaList(paramMediaList, bool);
      return;
    }
  }
  
  public void setSDCardEjecting(boolean paramBoolean)
  {
    this.mSDCardEjecting.set(paramBoolean);
  }
  
  public void setUSBEjecting(boolean paramBoolean)
  {
    this.mUSBEjecting.set(paramBoolean);
  }
  
  public void showAddFileDialog()
  {
    if (Build.VERSION.SDK_INT >= 24)
    {
      AutoBackupJobSchedulerService.closeManuallyUpload(this);
      getWdFileManager().setLatestAddDate(System.currentTimeMillis() / 1000L, 4);
      if (FileUtils.isHtcDevice()) {
        break label58;
      }
      startActivityForResult(new Intent("android.media.action.STILL_IMAGE_CAMERA"), 4);
    }
    label58:
    do
    {
      do
      {
        return;
        AutoBackupService.closeManuallyUpload(this);
        break;
        localObject2 = null;
        localObject3 = getPackageManager().getInstalledPackages(0).iterator();
        do
        {
          localObject1 = localObject2;
          if (!((Iterator)localObject3).hasNext()) {
            break;
          }
          localObject1 = (PackageInfo)((Iterator)localObject3).next();
        } while (((((PackageInfo)localObject1).applicationInfo.flags & 0x1) <= 0) || ((!((PackageInfo)localObject1).packageName.contains("Camera")) && (!((PackageInfo)localObject1).packageName.contains("camera"))));
      } while (localObject1 == null);
      localObject2 = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
      ((Intent)localObject2).setPackage(((PackageInfo)localObject1).packageName);
      localObject2 = (ResolveInfo)getPackageManager().queryIntentActivities((Intent)localObject2, 0).iterator().next();
    } while (localObject2 == null);
    Object localObject1 = ((ResolveInfo)localObject2).activityInfo.packageName;
    Object localObject2 = ((ResolveInfo)localObject2).activityInfo.name;
    Object localObject3 = new Intent("android.intent.action.MAIN");
    ((Intent)localObject3).addCategory("android.intent.category.LAUNCHER");
    ((Intent)localObject3).setComponent(new ComponentName((String)localObject1, (String)localObject2));
    startActivityForResult((Intent)localObject3, 4);
  }
  
  public void showBackingCompleteLayout()
  {
    if (getTabIndex() != 0)
    {
      hideBackingUpStatusPanel();
      return;
    }
    if (this.mBackingUpStatusPanel.getVisibility() == 8) {}
    for (int i = 1;; i = 0)
    {
      setVisibility(this.mBackingUpLayout, 8);
      setVisibility(this.mBackingCompleteLayout, 0);
      setVisibility(this.mBackingUpStatusPanel, 0);
      if (i != 0) {
        this.mBackingUpStatusPanel.startAnimation(this.mBackingUpIn);
      }
      Runnable local44 = new Runnable()
      {
        public void run()
        {
          MyDeviceActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              MyDeviceActivity.this.hideBackingUpStatusPanel();
            }
          });
        }
      };
      this.mHandler.postDelayed(local44, 10000L);
      LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("actionDownloadFinished"));
      Log.d(tag, "Broadcasting onJobFinished from showBackingCompleteLayout");
      return;
    }
  }
  
  public void showBackingUpLayout()
  {
    int j = 1;
    if (getTabIndex() == 0)
    {
      i = 1;
      if ((i == 0) || (!AutoBackupService.isDeviceBackingUp(this, getCurrentDevice()))) {
        break label94;
      }
      if (this.mBackingUpStatusPanel.getVisibility() != 8) {
        break label89;
      }
    }
    label89:
    for (int i = j;; i = 0)
    {
      setVisibility(this.mBackingUpLayout, 0);
      setVisibility(this.mBackingCompleteLayout, 8);
      setVisibility(this.mBackingUpStatusPanel, 0);
      if (i != 0) {
        this.mBackingUpStatusPanel.startAnimation(this.mBackingUpIn);
      }
      return;
      i = 0;
      break;
    }
    label94:
    hideBackingUpStatusPanel();
  }
  
  public void showCastLayout(boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mCastDialog != null) && (this.mCastDialog.canShowMenu())) {}
    for (boolean bool = true;; bool = false)
    {
      this.mShowCastMenuItem = bool;
      if ((this.mCastMenuItem != null) && ((this.mCastMenuItem.isVisible() ^ paramBoolean)))
      {
        this.mCastMenuItem.setVisible(paramBoolean);
        invalidateOptionsMenu();
      }
      return;
    }
  }
  
  public void showDeviceInfo(Device paramDevice)
  {
    new GetDeviceInfoLoader(this).execute(new Device[] { paramDevice });
  }
  
  public boolean showEmailLink(Device paramDevice)
  {
    if ((paramDevice != null) && (paramDevice.isOrionDevice()) && (this.mDevice.deviceType != null) && (this.mDevice.deviceType.version != null) && ("1.0".equalsIgnoreCase(this.mDevice.deviceType.version))) {}
    while ((paramDevice != null) && ((paramDevice.isBaiduNetdisk()) || (paramDevice.isAvatarDevice()) || (paramDevice.isKorraDevice()) || (paramDevice.isSDCard()))) {
      return false;
    }
    return true;
  }
  
  public void showExportDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, 3);
    localBuilder.setTitle(getResources().getString(2131296760));
    ExportDialogAdapter localExportDialogAdapter = new ExportDialogAdapter(this, 2130968879);
    localExportDialogAdapter.add(new AbstractMap.SimpleEntry(Integer.valueOf(2130838570), getString(2131297208)));
    localExportDialogAdapter.add(new AbstractMap.SimpleEntry(Integer.valueOf(2130838573), getString(2131297207)));
    localBuilder.setAdapter(localExportDialogAdapter, new DialogAdapterOnClickListener(null));
    localBuilder.show();
  }
  
  public void showMainLayout(boolean paramBoolean)
  {
    int j = 0;
    Object localObject = this.mSearchViewLayout;
    if (!paramBoolean)
    {
      i = 0;
      setVisibility((View)localObject, i);
      localObject = this.mMainLayout;
      if (!paramBoolean) {
        break label47;
      }
    }
    label47:
    for (int i = j;; i = 8)
    {
      setVisibility((View)localObject, i);
      return;
      i = 8;
      break;
    }
  }
  
  public void showMenuIcon(boolean paramBoolean)
  {
    showOverflowMenuItem(paramBoolean);
  }
  
  public void showMoveFileDialog()
  {
    if (this.mMoveFileDialog != null)
    {
      if (this.mMoveFileDialog.isShowing()) {
        this.mMoveFileDialog.dismiss();
      }
      this.mMoveFileDialog = null;
    }
    this.mMoveFileDialog = new MoveFileDialog(this);
    this.mMoveFileDialog.setCancelable(true);
    this.mMoveFileDialog.setCanceledOnTouchOutside(true);
    this.mMoveFileDialog.show();
  }
  
  public void showMoveFileWindow(int paramInt, boolean paramBoolean)
  {
    if ((getCurrentDevice() != null) && (getCurrentDevice().isSDCard())) {}
    for (int i = 1; (!this.mNetworkConnected.get()) && (i == 0); i = 0)
    {
      Toast.makeText(this, 2131296363, 0).show();
      return;
    }
    if (this.mMoveFileWindow == null) {
      this.mMoveFileWindow = new MoveFileWindow(this);
    }
    this.mMoveFileWindow.updateWindow();
    this.mWdFileManager.getMoveType().set(paramInt);
    this.mMoveFileWindow.showMoveWindow(this.mMainLayout, paramInt, paramBoolean);
  }
  
  public boolean showNewFileFolder()
  {
    boolean bool2 = false;
    WdFile localWdFile = this.mWdFileManager.getWdFileSystem(null).getCurrentFolder();
    boolean bool1 = bool2;
    if (!isDemo())
    {
      bool1 = bool2;
      if (localWdFile != null)
      {
        bool1 = bool2;
        if (localWdFile.getDevice() != null)
        {
          if (!localWdFile.getDevice().isAvatarDevice()) {
            break label60;
          }
          bool1 = true;
        }
      }
    }
    label60:
    do
    {
      boolean bool3;
      do
      {
        return bool1;
        if (localWdFile.getDevice().isOrionDevice()) {
          break;
        }
        bool3 = true;
        bool1 = bool3;
      } while (!localWdFile.getDevice().isSDCard());
      if (!FileUtils.isSamsungDevice()) {}
      for (bool2 = true;; bool2 = false)
      {
        bool1 = bool3;
        if (FileUtils.getSdcards(bool2, FileUtils.isSamsungDevice()).length != 2) {
          break;
        }
        bool1 = bool3;
        if (!localWdFile.isRoot()) {
          break;
        }
        return false;
      }
      bool1 = bool2;
    } while (localWdFile.isRoot());
    return true;
  }
  
  public void showOverflowMenuItem(boolean paramBoolean)
  {
    this.mShowOverflowMenuItem = paramBoolean;
    if ((this.mOverflowMenuItem != null) && ((this.mOverflowMenuItem.isVisible() ^ paramBoolean)))
    {
      this.mOverflowMenuItem.setVisible(paramBoolean);
      invalidateOptionsMenu();
    }
  }
  
  public void showPopupMenu(boolean paramBoolean)
  {
    try
    {
      switch (getTabIndex())
      {
      case 0: 
        if ((getEditEnable()) || (this.mPageIndex == 0))
        {
          this.mFileFragment.showPopupMenu(paramBoolean);
          return;
        }
        break;
      }
    }
    catch (Exception localException)
    {
      Log.i(tag, localException.getMessage(), localException);
      return;
    }
    this.mMediaFragmentManager.showPopupMenu(true);
    return;
    this.mFileFragment.showPopupMenu(paramBoolean);
    return;
  }
  
  public void showPrivateShareFragment(SharePrivateActivity.ShareIntentData paramShareIntentData)
  {
    this.mIsInPrivateSharing = true;
    this.mShareIntentData = paramShareIntentData;
    startActionModeForPrivateShare();
    this.mSharePrivateFragment = SharePrivateFragment.getInstance(paramShareIntentData);
    this.mSharePrivateFragment.setStateListener(this);
    replaceFragment(2131820729, this.mSharePrivateFragment);
    refreshShareFragment();
  }
  
  public void showProperty(WdFile paramWdFile, boolean paramBoolean)
  {
    try
    {
      if ((paramWdFile.isFolder) && (paramBoolean))
      {
        Intent localIntent = new Intent(this, ShareInfoActivity.class);
        getWdFileManager().setCurrentShareFolder(paramWdFile.getWdActivity());
        startActivity(localIntent);
        return;
      }
      this.mPropertyDialog = new FilePropertyDialog(this, paramWdFile).getDialog();
      return;
    }
    catch (Exception paramWdFile)
    {
      Log.e(tag, paramWdFile.getMessage(), paramWdFile);
    }
  }
  
  public void showResponseError(Device paramDevice, ResponseException paramResponseException)
  {
    if (paramResponseException.getStatusCode() == 401)
    {
      this.mWdFileManager.removeLocalDevice(paramDevice);
      showResponseError(paramResponseException, new Runnable()
      {
        public void run()
        {
          MyDeviceActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              try
              {
                MyDeviceActivity.this.gotoDeviceList();
                return;
              }
              catch (Exception localException)
              {
                Log.w(MyDeviceActivity.tag, localException.getMessage(), localException);
              }
            }
          });
        }
      });
      return;
    }
    if (paramResponseException.getStatusCode() == 404)
    {
      Runnable local33 = new Runnable()
      {
        public void run()
        {
          new AsyncLoader(MyDeviceActivity.this)
          {
            protected WdFile doInBackground(Boolean... paramAnonymous2VarArgs)
            {
              try
              {
                if (MyDeviceActivity.this.mWdFileSystem != null)
                {
                  paramAnonymous2VarArgs = MyDeviceActivity.this.mWdFileSystem.getVerifiedCurrentFolder();
                  return paramAnonymous2VarArgs;
                }
              }
              catch (Exception paramAnonymous2VarArgs)
              {
                Log.i(MyDeviceActivity.tag, paramAnonymous2VarArgs.getMessage(), paramAnonymous2VarArgs);
              }
              return null;
            }
            
            protected void onPostExecute(WdFile paramAnonymous2WdFile)
            {
              if (paramAnonymous2WdFile != null) {
                MyDeviceActivity.this.reload();
              }
            }
          }.execute(new Boolean[0]);
        }
      };
      if (paramResponseException.getFileName() == null) {}
      for (paramDevice = getString(2131296866);; paramDevice = getString(2131296866, new Object[] { paramResponseException.getFileName() }))
      {
        DialogUtils.error(this, null, paramDevice, local33);
        return;
      }
    }
    super.showResponseError(paramResponseException);
  }
  
  public void showResponseError(Device paramDevice, Map<Integer, String> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty())) {
      return;
    }
    this.mWdFileManager.setResponseErrorInfo(paramMap);
    ArrayList localArrayList = new ArrayList(paramMap.values());
    String[] arrayOfString = new String[localArrayList.size()];
    final boolean bool1 = paramMap.containsKey(Integer.valueOf(401));
    final boolean bool2 = paramMap.containsKey(Integer.valueOf(404));
    if (bool1) {
      this.mWdFileManager.removeLocalDevice(paramDevice);
    }
    paramDevice = new Runnable()
    {
      public void run()
      {
        try
        {
          if (bool1)
          {
            MyDeviceActivity.this.gotoDeviceList();
            return;
          }
          if (!bool2) {
            return;
          }
          if (MyDeviceActivity.this.mWdFileSystem.getCurrentFolder().isRoot())
          {
            MyDeviceActivity.this.gotoDeviceList();
            return;
          }
        }
        catch (Exception localException)
        {
          Log.e(MyDeviceActivity.tag, localException.getMessage(), localException);
          return;
        }
        new AsyncLoader(MyDeviceActivity.this)
        {
          protected WdFile doInBackground(Boolean... paramAnonymous2VarArgs)
          {
            try
            {
              paramAnonymous2VarArgs = MyDeviceActivity.this.mWdFileSystem.getVerifiedCurrentFolder();
              return paramAnonymous2VarArgs;
            }
            catch (Exception paramAnonymous2VarArgs)
            {
              Log.i(MyDeviceActivity.tag, paramAnonymous2VarArgs.getMessage(), paramAnonymous2VarArgs);
            }
            return null;
          }
          
          protected void onPostExecute(WdFile paramAnonymous2WdFile)
          {
            if (paramAnonymous2WdFile != null) {
              MyDeviceActivity.this.reload();
            }
          }
        }.execute(new Boolean[0]);
      }
    };
    paramMap = (String[])localArrayList.toArray(arrayOfString);
    if (this.mActivityToShowDialogOn == null) {}
    for (this.mResponseErrorDialog = DialogUtils.makeExceptionsDialog(this, null, paramMap, paramDevice);; this.mResponseErrorDialog = DialogUtils.makeExceptionsDialog(this.mActivityToShowDialogOn, null, paramMap, paramDevice))
    {
      this.mResponseErrorDialog.show();
      return;
    }
  }
  
  public void showSearch(boolean paramBoolean)
  {
    this.mShowSearchMenuItem = paramBoolean;
    View localView = this.mSearchViewLayout;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      setVisibility(localView, i);
      if ((this.mSearchMenuItem != null) && ((this.mSearchMenuItem.isVisible() ^ paramBoolean)))
      {
        this.mSearchMenuItem.setVisible(paramBoolean);
        invalidateOptionsMenu();
      }
      return;
    }
  }
  
  public void showSearchView(SearchView.SearchFilter paramSearchFilter)
  {
    try
    {
      if (this.mSearchView != null)
      {
        this.mLastPageIndex = this.mPageIndex;
        this.mPageIndex = 4;
        if ((this.mMainLayout != null) && (this.mSearchViewLayout != null))
        {
          this.mDrawerLayout.setDrawerLockMode(1);
          this.mSearchView.showSearchView(paramSearchFilter);
        }
      }
      return;
    }
    catch (Exception paramSearchFilter)
    {
      Log.e(tag, "show SearchBarFragment exception ", paramSearchFilter);
    }
  }
  
  public void showShare(boolean paramBoolean)
  {
    this.mShowShareMenuItem = paramBoolean;
    if ((this.mShareMenuItem != null) && ((this.mShareMenuItem.isVisible() ^ paramBoolean)))
    {
      this.mShareMenuItem.setVisible(paramBoolean);
      invalidateOptionsMenu();
    }
  }
  
  public void showTabCursorLayout(boolean paramBoolean)
  {
    TabLayout localTabLayout = this.mTabLayout;
    int i;
    int j;
    if (paramBoolean)
    {
      i = 0;
      localTabLayout.setVisibility(i);
      if (!paramBoolean) {
        return;
      }
      if (this.mShareWithMeFragment != null) {
        this.mShareWithMeFragment.cleanAll();
      }
      if (this.mPageAdapter != null)
      {
        this.mPageAdapter.setMaxCount(4);
        this.mPageAdapter.notifyDataSetChanged();
      }
      j = 0;
      label60:
      if (j >= this.mTabLayout.getTabCount()) {
        break label134;
      }
      i = 2130838739;
      if (j != 1) {
        break label110;
      }
      i = 2130838743;
    }
    for (;;)
    {
      this.mTabLayout.getTabAt(j).setIcon(i);
      j += 1;
      break label60;
      i = 8;
      break;
      label110:
      if (j == 2) {
        i = 2130838741;
      } else if (j == 3) {
        i = 2130838746;
      }
    }
    label134:
    showTabs(false);
  }
  
  public void showUploadContextMenu()
  {
    dismissUploadContextMenu();
    this.mUploadContextMenu = new UploadItemDialog(this);
    if (this.mUploadContextMenu != null) {
      this.mUploadContextMenu.show();
    }
  }
  
  public void startLoading(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mFileFragment.startLoading(paramBoolean1, paramBoolean2);
  }
  
  public void startQuery(String paramString)
  {
    if (this.mSearchView != null) {
      this.mSearchView.search(paramString);
    }
  }
  
  public void stopRefresh()
  {
    this.mIsRefreshing = false;
    this.mStart = -937.0F;
    this.mFileFragment.stopRefresh();
    this.mMediaFragmentManager.stopRefresh();
  }
  
  public void toggleNavigationSpinner(boolean paramBoolean)
  {
    Spinner localSpinner;
    if (this.mNavigationSpinner != null)
    {
      localSpinner = this.mNavigationSpinner;
      if (!paramBoolean) {
        break label24;
      }
    }
    label24:
    for (int i = 0;; i = 8)
    {
      localSpinner.setVisibility(i);
      return;
    }
  }
  
  public void try2Add(Intent paramIntent)
  {
    if ((this.mWdFileManager.getAddFileType() != -1) && (this.mWdFileManager.getLatestAddDate() != -1L))
    {
      if ((paramIntent != null) && (paramIntent.getData() != null)) {
        startMoveBySpecificFile(paramIntent.getData());
      }
    }
    else {
      return;
    }
    startMoveByQueryMediaStore();
  }
  
  public void try2Save()
  {
    if (isDemo()) {}
    boolean bool;
    do
    {
      File localFile;
      do
      {
        do
        {
          return;
          localWdActivity = this.mWdFileManager.getFileOpening();
          clearCurrentAction();
        } while (localWdActivity == null);
        if (!localWdActivity.isLocalFile()) {
          break;
        }
        localFile = new File(localWdActivity.downloadPath);
      } while (!localFile.exists());
      localWdActivity.size = localFile.length();
      localWdActivity.downloadSize = localFile.length();
      localWdActivity.modifiedDate = (localFile.lastModified() / 1000L);
      this.mWdFileManager.getDatabaseAgent().insertOrUpdateWdActivity(localWdActivity);
      return;
      if (!localWdActivity.getCurrentCacheFile().exists())
      {
        getWdFileSystem().deleteWdActivity(localWdActivity, 5);
        reload();
      }
      bool = false;
      if (localWdActivity != null) {
        bool = this.mWdFileManager.isOpenedFileChanged();
      }
    } while (!bool);
    WdActivity localWdActivity = this.mWdFileManager.getFileOpening();
    reload();
    Toast.makeText(this, getString(2131297217), 1).show();
    new VerifyOpenedFileLoader(this).execute(new WdActivity[] { localWdActivity });
    this.mWdFileManager.setFileOpening(null);
  }
  
  public void updateBreadcrumbAdapterData()
  {
    this.mBreadcrumbListAdapter.notifyDataSetChanged();
    checkAndUpdateClickabilityOfSpinner();
  }
  
  /* Error */
  public void updateMediaBreadcrumbAdapterData(List<WdFileMedia> paramList, com.wdc.wd2go.media.fragment.AbstractFragment.OnMediaBreadcrumbItemClickListener paramOnMediaBreadcrumbItemClickListener)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 493	com/wdc/wd2go/ui/activity/MyDeviceActivity:mMediaBreadcrumbListAdapter	Lcom/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter;
    //   6: ifnonnull +40 -> 46
    //   9: aload_0
    //   10: new 2265	com/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter
    //   13: dup
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 3203	com/wdc/wd2go/ui/activity/MyDeviceActivity:toolbar	Landroid/support/v7/widget/Toolbar;
    //   19: aload_2
    //   20: invokespecial 4338	com/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter:<init>	(Lcom/wdc/wd2go/ui/activity/MyDeviceActivity;Landroid/view/View;Lcom/wdc/wd2go/media/fragment/AbstractFragment$OnMediaBreadcrumbItemClickListener;)V
    //   23: putfield 493	com/wdc/wd2go/ui/activity/MyDeviceActivity:mMediaBreadcrumbListAdapter	Lcom/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter;
    //   26: aload_0
    //   27: getfield 493	com/wdc/wd2go/ui/activity/MyDeviceActivity:mMediaBreadcrumbListAdapter	Lcom/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter;
    //   30: aload_1
    //   31: invokevirtual 4341	com/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter:setBreadcrumbData	(Ljava/util/List;)V
    //   34: aload_0
    //   35: iconst_1
    //   36: invokevirtual 4044	com/wdc/wd2go/ui/activity/MyDeviceActivity:enableMediaBreadcrumb	(Z)V
    //   39: aload_0
    //   40: invokevirtual 4333	com/wdc/wd2go/ui/activity/MyDeviceActivity:checkAndUpdateClickabilityOfSpinner	()V
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: aload_0
    //   47: getfield 493	com/wdc/wd2go/ui/activity/MyDeviceActivity:mMediaBreadcrumbListAdapter	Lcom/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter;
    //   50: aload_2
    //   51: invokevirtual 4345	com/wdc/wd2go/ui/adapter/MediaBreadcrumbListAdapter:setMediaListener	(Lcom/wdc/wd2go/media/fragment/AbstractFragment$OnMediaBreadcrumbItemClickListener;)V
    //   54: goto -28 -> 26
    //   57: astore_1
    //   58: aload_0
    //   59: monitorexit
    //   60: aload_1
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	62	0	this	MyDeviceActivity
    //   0	62	1	paramList	List<WdFileMedia>
    //   0	62	2	paramOnMediaBreadcrumbItemClickListener	com.wdc.wd2go.media.fragment.AbstractFragment.OnMediaBreadcrumbItemClickListener
    // Exception table:
    //   from	to	target	type
    //   2	26	57	finally
    //   26	43	57	finally
    //   46	54	57	finally
  }
  
  public void updateRightPanelView()
  {
    if (isPhone()) {
      return;
    }
    if (this.mViewFlowFragment != null)
    {
      this.mViewFlowFragment.showViewFlow(this.mPageIndex, isPortraitPad());
      return;
    }
    this.mMediaFragmentManager.updateCleanScreenButton(this.mPageIndex);
  }
  
  public void updateSDCardFailed(WdActivity paramWdActivity)
  {
    try
    {
      Message localMessage = Message.obtain(this.mHandler, 659);
      localMessage.obj = paramWdActivity;
      localMessage.sendToTarget();
      return;
    }
    catch (Exception paramWdActivity)
    {
      Log.e(tag, paramWdActivity.getMessage(), paramWdActivity);
    }
  }
  
  public void updateSDCardProgress(WdActivity paramWdActivity)
  {
    updateImportProgress(paramWdActivity);
  }
  
  public void updateUSBFailed(WdActivity paramWdActivity)
  {
    updateSDCardFailed(paramWdActivity);
  }
  
  public void updateUSBProgress(WdActivity paramWdActivity)
  {
    updateImportProgress(paramWdActivity);
  }
  
  public void updateWdFile(WdFile paramWdFile)
  {
    FileItemView localFileItemView = (FileItemView)getItemView(FileUtils.generateWdFileItemId(paramWdFile.fullPath));
    if (getTabIndex() == 0)
    {
      this.mWdFileSystem.updateFileCaches(paramWdFile);
      localFileItemView.setWdFile(paramWdFile, this.mIsListBusy, getEditEnable());
    }
  }
  
  public void upgradeFirmware(WdActivity paramWdActivity, long paramLong)
  {
    if (isDrawerOpen()) {
      this.mSlideDrawer.upgradeFirmware(paramWdActivity, paramLong);
    }
  }
  
  public void uploadToCurrentFolder(int paramInt, boolean paramBoolean)
  {
    for (;;)
    {
      String str3;
      try
      {
        WdFileSystem localWdFileSystem = getWdFileSystem();
        if (localWdFileSystem == null) {
          return;
        }
        WdFile localWdFile = localWdFileSystem.getCurrentFolder();
        if (localWdFile == null) {
          break;
        }
        String str1;
        if (paramBoolean)
        {
          str3 = localWdFile.fullPath;
          if (!localWdFile.getDevice().isBaiduNetdisk()) {
            break label188;
          }
          if (StringUtils.isEquals("/", str3))
          {
            str1 = "Shares";
            Toast.makeText(this, getString(2131297356, new Object[] { FileUtils.removeObjectId(str1) }), 0).show();
          }
        }
        else
        {
          str1 = localWdFile.fullPath;
          str3 = localWdFile.objectId;
          String str4 = localWdFile.googleNameLength;
          getUploadFilesLoader(paramInt).execute(new String[] { str1, str3, str4 });
          localWdFileSystem.setDirty(localWdFile);
          return;
        }
      }
      catch (Exception localException)
      {
        Log.i(tag, localException.getMessage(), localException);
        return;
      }
      String str2 = str3;
      if (str3.startsWith("/"))
      {
        str2 = str3.replaceFirst("/", "");
        continue;
        label188:
        str2 = str3;
        if (StringUtils.isEquals("/", str3)) {
          str2 = "Shares";
        }
      }
    }
  }
  
  private class DialogAdapterOnClickListener
    implements DialogInterface.OnClickListener
  {
    private DialogAdapterOnClickListener() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (paramInt == 0) {
        MyDeviceActivity.this.startDownloadFile();
      }
      while (paramInt != 1) {
        return;
      }
      MyDeviceActivity.this.saveAs();
    }
  }
  
  public class ExampleAdapter
    extends CursorAdapter
  {
    private List<String> items;
    private TextView text;
    
    public ExampleAdapter(Cursor paramCursor, List<String> paramList)
    {
      super(paramList, false);
      Object localObject;
      this.items = localObject;
    }
    
    public void bindView(View paramView, Context paramContext, Cursor paramCursor)
    {
      this.text.setText((CharSequence)this.items.get(paramCursor.getPosition()));
    }
    
    public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
    {
      this.text = ((TextView)((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130968887, paramViewGroup, false).findViewById(2131821839));
      return this.text;
    }
  }
  
  private class ExportDialogAdapter
    extends ArrayAdapter<Map.Entry<Integer, String>>
  {
    public ExportDialogAdapter(Context paramContext, int paramInt)
    {
      super(paramInt);
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null) {
        paramView = MyDeviceActivity.this.getLayoutInflater().inflate(2130968879, paramViewGroup, false);
      }
      for (;;)
      {
        try
        {
          paramViewGroup = (TextView)paramView.findViewById(2131821628);
          paramView.findViewById(2131821666).setVisibility(8);
          ImageView localImageView = (ImageView)paramView.findViewById(2131820672);
          Map.Entry localEntry = (Map.Entry)getItem(paramInt);
          localImageView.setImageResource(((Integer)localEntry.getKey()).intValue());
          paramViewGroup.setText((CharSequence)localEntry.getValue());
          return paramView;
        }
        catch (ClassCastException paramView) {}
      }
      return null;
    }
  }
}
