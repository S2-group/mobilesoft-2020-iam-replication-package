package com.jrummy.root.browserfree;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.jrummy.root.browserfree.util.CMDProcessor;
import com.jrummy.root.browserfree.util.CMDProcessor.CommandResult;
import com.jrummy.root.browserfree.util.CMDProcessor.SH;
import com.jrummy.root.browserfree.util.Helpers;
import com.jrummy.root.browserfree.util.Reflection;
import com.jrummy.root.browserfree.views.LinearColorBar;
import com.jrummy.root.browserfree.views.PopupDialog;
import com.jrummy.root.browserfree.views.PopupDialog.Builder;
import com.jrummy.root.browserfree.views.quickaction.ActionItem;
import com.jrummy.root.browserfree.views.quickaction.QuickActionBar;
import com.jrummy.root.browserfree.views.quickaction.QuickActionBar.OnActionItemClickListener;
import com.jrummy.root.browserfree.views.quickaction.QuickActionList;
import com.jrummy.root.browserfree.views.quickaction.QuickActionList.OnActionItemClickListener;
import com.koushikdutta.rommanager.api.IROMManagerAPIService;
import com.koushikdutta.rommanager.api.IROMManagerAPIService.Stub;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileBrowser
  extends Activity
  implements AdapterView.OnItemClickListener, TextWatcher, AdapterView.OnItemLongClickListener, View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener
{
  private static final int DIALOG_CONFIRM_DELETE = 39;
  private static final int DIALOG_CONFIRM_MULTI_DELETE = 40;
  private static final int DIALOG_EDIT = 1;
  private static final int DIALOG_EXTRACT = 6;
  private static final int DIALOG_EXTRACT_FROM_ZIP = 35;
  private static final int DIALOG_FILE_INFO = 2;
  private static final int DIALOG_INFO = 34;
  private static final int DIALOG_LONG_CLICK = 22;
  private static final int DIALOG_NEED_PRO = 25;
  private static final int DIALOG_NEW_VERSION = 33;
  private static final int DIALOG_OVERWRITE_FILE = 37;
  private static final int DIALOG_OWNER_FAILED = 21;
  private static final int DIALOG_PERMS_FAILED = 20;
  private static final int DIALOG_PROGRESS = 5;
  private static final int DIALOG_ROOT_USER = 23;
  private static final int DIALOG_SCRIPT_DETAILS = 7;
  private static final int DIALOG_SEARCH_RESULTS = 9;
  private static final int DIALOG_SEND = 3;
  private static final int DIALOG_SYMLINK_FAILED = 36;
  private static final int DIALOG_TAR = 19;
  private static final int DIALOG_VIEW = 0;
  private static final int DIALOG_WELCOME = 32;
  private static final int DIALOG_ZIP = 18;
  private static final int DIALOG_ZIP_PERMS = 38;
  private static final String DO_COPY = "do_copy";
  private static final String DO_DELETE = "do_delete";
  private static final String DO_EXTRACT = "do_extract";
  private static final String DO_MOVE = "do_move";
  private static final String DO_SYMLINK = "do_symlink";
  private static final String DO_TAR = "do_tar";
  private static final String DO_ZIP_FILE = "do_zip_file";
  private static final String KEY_FOLDERS_FIRST = "fb_folders_first";
  public static final String KEY_HOME_FOLDER = "home_folder";
  private static final String KEY_IS_ROOT = "is_root_user";
  private static final String KEY_SAVE_SORT = "fb_save_sort_type";
  private static final String KEY_SCROLL_TO_SAME_POSITION = "scroll_to_same_position";
  private static final String KEY_SHOW_FOLDER_SIZE = "fb_show_folder_size";
  private static final String KEY_SHOW_HIDDEN = "fb_show_hidden";
  private static final String KEY_SHOW_INFO = "show_file_info";
  private static final String KEY_SHOW_NOTIFICATIONS = "fb_show_notifications";
  private static final String KEY_SORT_TYPE = "fb_sort_type";
  public static final String KEY_THEME = "theme";
  private static final String KEY_USE_FONTS = "use_custom_fonts";
  public static Typeface MAIN_FONT;
  private static final int MENU_HOME = 4;
  private static final int MENU_MULTI_SEL = 3;
  private static final int MENU_PREFS = 2;
  private static final int MENU_REFRESH = 1;
  private static final int MENU_SEARCH = 5;
  private static final int MENU_SORT = 0;
  private static final int MSG_ADD_FILE_INFO_HOLDER = 5;
  private static final int MSG_DISMISS_ALERT = 3;
  private static final int MSG_DISMISS_PBAR = 0;
  private static final int MSG_NOTIFY_CHANGE = 8;
  private static final int MSG_REMOVE_FILE_INFO_HOLDER = 4;
  private static final int MSG_SHOW_DIALOG = 7;
  private static final int MSG_UPDATE_LIST = 6;
  private static final int MSG_UPDATE_PROGRESS = 2;
  private static final int PROCESS_ID = 1820;
  private static final String SD = Environment.getExternalStorageDirectory().getAbsolutePath();
  private static final int SDK_INT = Build.VERSION.SDK_INT;
  private static ArrayList<String> SEARCH;
  private static final int SORT_BY_TYPE = 6;
  private static final int SORT_DATE_ASC = 2;
  private static final int SORT_DATE_DESC = 3;
  private static final int SORT_NAME_ASC = 0;
  private static final int SORT_NAME_DESC = 1;
  private static final int SORT_SIZE_ASC = 4;
  private static final int SORT_SIZE_DESC = 5;
  public static Typeface SUB_FONT;
  private static final String TAG = "FileBrowser";
  public static final int THEME_BLACK = 0;
  public static final int THEME_DEEP = 2;
  public static final int THEME_DEEP_LIGHT = 3;
  public static final int THEME_DEEP_TRANS = 6;
  public static final int THEME_LIGHT = 1;
  public static final int THEME_TRANS = 4;
  public static final int THEME_TRANS2 = 5;
  private static LinearLayout alertView;
  private static View.OnTouchListener gestureListener;
  private static boolean hasRomManager;
  private static boolean isExploringZip;
  private static boolean isInSelectionMode = false;
  private static boolean isRootDir;
  private static LinearLayout mActionBar;
  private static ImageButton mActionBarHome;
  private static ImageButton mActionBarSearch;
  private static List<ResolveInfo> mActivities;
  private static String mAdPackage;
  public static ListAdapter mAdapter;
  private static String mAlertMsg;
  public static List<FileInfoHolder> mAllFiles;
  private static RelativeLayout mBottomBar;
  private static LinearLayout mBtnCancel;
  private static Button mBtnCancelOperation;
  private static LinearLayout mBtnCopy;
  private static LinearLayout mBtnDelete;
  private static Button mBtnDoOperation;
  private static Button mBtnHidePbar;
  private static LinearLayout mBtnMove;
  private static LinearLayout mBtnSelect;
  private static LinearLayout mBtnSend;
  private static LinearLayout mBtnTar;
  private static LinearLayout mBtnUnSelect;
  private static LinearLayout mBtnZip;
  private static String mCmd;
  public static FileInfoHolder mCmdFile;
  private static LinearColorBar mColorBar;
  private static LinearLayout mColorBarLayout;
  private static DatabaseHelper mDatabaseHelper;
  private static int mDialog;
  private static String mDialogMsg;
  private static String mDialogTitle;
  private static LinearLayout mDirectoryButtons;
  private static boolean mDoBatch;
  private static boolean mDoExit;
  private static GestureDetector mDoubleTap;
  private static LinearLayout mEmptyList;
  private static TextView mEmptyText;
  private static String mExtractLocation;
  public static FileInfoHolder mFile;
  public static List<FileInfoHolder> mFiles;
  private static boolean mFoldersFirst;
  private static TextView mFreeStorageText;
  private static boolean mFreebie = false;
  private static String mGroup;
  private static boolean mHasSuperUser;
  private static String mHomeFolder;
  private static Drawable mImg;
  private static Intent mIntent;
  public static boolean mIsRootUser;
  private static boolean mIsSearchBarVisible;
  private static String mLastZipFolder;
  private static String mLinkFromFile;
  private static ListView mListView;
  private static String mNotificationMsg;
  private static String mNotificationTitle;
  private static LinearLayout mOperationLayout;
  private static String mOwner;
  public static File mPath;
  private static TextView mPbarCount;
  private static ProgressBar mPbarHorz;
  private static ImageView mPbarImage;
  private static LinearLayout mPbarLayout;
  private static TextView mPbarMsg;
  private static TextView mPbarPerc;
  private static ProgressBar mPbarSpinner;
  private static ProgressBar mPbarSpinner2;
  private static ImageView mPbarTitleImg;
  private static LinearLayout mPbarTitleLayout;
  private static TextView mPbarTitleMsg;
  private static Drawable mProgressImage;
  private static String mProgressMsg;
  private static String mProgressTitle;
  private static HashMap<String, Integer> mScrollPositions;
  private static boolean mScrollToSamePosition;
  private static EditText mSearchBar;
  public static List<FileInfoHolder> mSelectedFiles;
  private static boolean mShowFolderSize;
  private static boolean mShowHidden;
  private static boolean mShowInfo;
  private static boolean mShowNotifications;
  private static int mSortType;
  private static StatFs mStatFs;
  private static String mStderr;
  private static TextView mStorageChartLabel;
  private static int mTextColor;
  public static int mTheme;
  private static String mTickerMsg;
  private static TextView mTitleText;
  private static String mToastMsg;
  private static String mUrl;
  public static boolean mUseFonts;
  private static TextView mUsedStorageText;
  private static String mZipFile;
  private static String[] mZipFilesToExtract;
  private static File mZipLocation;
  public static final String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" };
  private static boolean pickFile = false;
  public static SharedPreferences preferences;
  private static int progress;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        return;
        FileBrowser.this.removeDialog(5);
        if ((FileBrowser.mPbarLayout.getVisibility() == 0) && (FileBrowser.mPbarHorz.getProgress() >= FileBrowser.mPbarHorz.getMax())) {
          FileBrowser.this.hideProgressHorizontal();
        }
        if (FileBrowser.mPbarSpinner.getVisibility() == 0) {
          FileBrowser.this.showProgressSpinner(false);
        }
        if (FileBrowser.mPbarSpinner2.getVisibility() == 0)
        {
          FileBrowser.mPbarSpinner2.startAnimation(AnimationUtils.loadAnimation(FileBrowser.this.getBaseContext(), 2130968579));
          FileBrowser.mPbarSpinner2.setVisibility(8);
        }
        if (FileBrowser.mToastMsg != null)
        {
          Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.mToastMsg);
          FileBrowser.mToastMsg = null;
        }
        if (FileBrowser.mAlertMsg != null) {
          FileBrowser.this.alertBar(FileBrowser.mAlertMsg, 5000);
        }
        if ((FileBrowser.mShowNotifications) && (FileBrowser.mTickerMsg != null) && (FileBrowser.mNotificationTitle != null) && (FileBrowser.mNotificationMsg != null))
        {
          paramAnonymousMessage = new Notification(2130837611, FileBrowser.mTickerMsg, System.currentTimeMillis());
          PendingIntent localPendingIntent = PendingIntent.getActivity(FileBrowser.this, 0, new Intent(), 0);
          paramAnonymousMessage.defaults = 2;
          paramAnonymousMessage.flags |= 0x10;
          paramAnonymousMessage.setLatestEventInfo(FileBrowser.this, FileBrowser.mNotificationTitle, FileBrowser.mNotificationMsg, localPendingIntent);
          ((NotificationManager)FileBrowser.this.getSystemService("notification")).notify(1969, paramAnonymousMessage);
          FileBrowser.mTickerMsg = null;
        }
        FileBrowser.mAdapter.notifyDataSetChanged();
        if (FileBrowser.mProgressImage != null) {}
        try
        {
          FileBrowser.mProgressImage = new BitmapDrawable(Reflection.main(((BitmapDrawable)FileBrowser.mProgressImage).getBitmap()));
          FileBrowser.mPbarImage.setBackgroundDrawable(FileBrowser.mProgressImage);
          FileBrowser.mPbarTitleImg.setBackgroundDrawable(FileBrowser.mProgressImage);
          FileBrowser.mPbarImage.setBackgroundDrawable(FileBrowser.mProgressImage);
          FileBrowser.mPbarTitleImg.setBackgroundDrawable(FileBrowser.mProgressImage);
          int i = FileBrowser.mPbarHorz.getMax();
          int j = FileBrowser.mPbarHorz.getProgress() + 1;
          int k = (int)Math.floor(j / i * 100.0D);
          FileBrowser.mPbarHorz.setProgress(j);
          FileBrowser.mPbarCount.setText(j + "/" + i);
          FileBrowser.mPbarPerc.setText(k + "%");
          FileBrowser.mPbarTitleMsg.setText(FileBrowser.mProgressTitle);
          FileBrowser.mPbarMsg.setText(FileBrowser.mProgressMsg);
          FileBrowser.mAdapter.notifyDataSetChanged();
          return;
          if (FileBrowser.alertView.getVisibility() != 0) {
            continue;
          }
          FileBrowser.alertView.startAnimation(AnimationUtils.loadAnimation(FileBrowser.this.getBaseContext(), 2130968577));
          FileBrowser.alertView.setVisibility(8);
          return;
          FileBrowser.mFiles.remove(FileBrowser.mFile);
          FileBrowser.mAllFiles.remove(FileBrowser.mFile);
          FileBrowser.mAdapter.notifyDataSetChanged();
          return;
          FileBrowser.mFiles.add(FileBrowser.mFile);
          FileBrowser.mAllFiles.add(FileBrowser.mFile);
          FileBrowser.this.sortFiles(FileBrowser.mSortType);
          FileBrowser.mAdapter.notifyDataSetChanged();
          return;
          FileBrowser.this.UpdateList(FileBrowser.mPath.toString(), false);
          return;
          FileBrowser.this.showDialog(FileBrowser.mDialog);
          return;
          FileBrowser.mAdapter.notifyDataSetChanged();
          return;
        }
        catch (ClassCastException paramAnonymousMessage)
        {
          for (;;) {}
        }
      }
    }
  };
  private long mLastFreeStorage;
  private long mLastUsedStorage;
  IROMManagerAPIService mService;
  private Notification notification;
  private NotificationManager notificationManager;
  private Resources res;
  
  public FileBrowser() {}
  
  private void addFileDialog(final boolean paramBoolean)
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903050);
    localDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(2130837533));
    Object localObject = (ImageView)localDialog.findViewById(2131361811);
    TextView localTextView1 = (TextView)localDialog.findViewById(2131361813);
    TextView localTextView2 = (TextView)localDialog.findViewById(2131361858);
    final EditText localEditText = (EditText)localDialog.findViewById(2131361859);
    Button localButton1 = (Button)localDialog.findViewById(2131361857);
    Button localButton2 = (Button)localDialog.findViewById(2131361856);
    localTextView2.setVisibility(8);
    localTextView1.setTypeface(MAIN_FONT);
    localEditText.setTypeface(SUB_FONT);
    localButton1.setTypeface(SUB_FONT);
    localButton2.setTypeface(SUB_FONT);
    int i;
    if (paramBoolean)
    {
      i = 2130837610;
      ((ImageView)localObject).setImageResource(i);
      if (!paramBoolean) {
        break label256;
      }
    }
    label256:
    for (localObject = getString(2131230911);; localObject = getString(2131230910))
    {
      localTextView1.setText((CharSequence)localObject);
      localEditText.setText("");
      localButton2.setText(getString(2131230766));
      localButton1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
        }
      });
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localDialog.dismiss();
          paramAnonymousView = new File(FileBrowser.mPath.toString() + "/" + localEditText.getText().toString());
          boolean bool = false;
          if (FileBrowser.mPath.canWrite()) {
            if (paramBoolean) {
              bool = paramAnonymousView.mkdirs();
            }
          }
          for (;;)
          {
            if (bool)
            {
              FileBrowser.this.UpdateList(FileBrowser.mPath.toString(), false);
              Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230858, new Object[] { paramAnonymousView.getName() }));
              return;
            }
            try
            {
              paramAnonymousView.createNewFile();
              bool = true;
            }
            catch (IOException localIOException) {}
            if (FileBrowser.mIsRootUser)
            {
              FileBrowser.getMounts("rw");
              if (paramBoolean) {}
              for (bool = new CMDProcessor().su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mkdir -p \"" + paramAnonymousView + "\"").success();; bool = new CMDProcessor().su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox touch \"" + paramAnonymousView + "\"").success())
              {
                FileBrowser.getMounts("ro");
                break;
              }
              Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230728, new Object[] { paramAnonymousView.getName() }));
              return;
            }
          }
        }
      });
      localDialog.show();
      return;
      i = 2130837542;
      break;
    }
  }
  
  private void alertBar(String paramString, int paramInt)
  {
    TextView localTextView = (TextView)findViewById(2131361886);
    if (alertView.getVisibility() == 8)
    {
      if (mColorBarLayout.getVisibility() == 0)
      {
        mColorBarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
        mColorBarLayout.setVisibility(8);
      }
      localTextView.setText(paramString);
      alertView.setVisibility(0);
      alertView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968576));
      this.handler.sendEmptyMessageDelayed(3, paramInt);
      return;
    }
    this.handler.removeMessages(3);
    localTextView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 17432577));
    localTextView.setText(paramString);
    localTextView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 17432576));
    this.handler.sendEmptyMessageDelayed(3, paramInt);
    updateStorageUsage();
  }
  
  private static File copyFile(String paramString1, String paramString2)
  {
    paramString1 = new File(paramString1);
    CMDProcessor localCMDProcessor = new CMDProcessor();
    int j = 0;
    String str2;
    if ((!paramString1.getParentFile().canRead()) && (mIsRootUser))
    {
      if (localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox ls \"" + paramString1.getAbsolutePath() + "\"").success()) {}
      for (i = 0;; i = 1)
      {
        str2 = paramString1.getName();
        if (i == 0) {
          break;
        }
        return paramString1;
      }
    }
    if (paramString1.exists()) {}
    for (int i = 0;; i = 1) {
      break;
    }
    String str1;
    if (j > 0)
    {
      str1 = " - " + paramString2 + " (" + (j + 1) + ")";
      label153:
      if (!paramString1.getName().contains(".")) {
        break label320;
      }
      String str3 = str2.substring(str2.lastIndexOf("."));
      String str4 = str2.replace(str3, "");
      paramString1 = new File(paramString1.getParent() + "/" + str4 + str1 + str3);
      label239:
      if ((paramString1.canRead()) || (!mIsRootUser)) {
        break label369;
      }
      if (!localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox ls \"" + paramString1 + "\"").success()) {
        break label364;
      }
    }
    label320:
    label364:
    for (i = 0;; i = 1)
    {
      j += 1;
      break;
      str1 = " - " + paramString2;
      break label153;
      paramString1 = new File(paramString1.getParent() + "/" + str2 + str1);
      break label239;
    }
    label369:
    if (paramString1.exists()) {}
    for (i = 0;; i = 1) {
      break;
    }
  }
  
  public static boolean copyFile(File paramFile1, File paramFile2)
  {
    try
    {
      paramFile1 = new FileInputStream(paramFile1);
      paramFile2 = new FileOutputStream(paramFile2);
      byte[] arrayOfByte = new byte['Ѐ'];
      for (;;)
      {
        int i = paramFile1.read(arrayOfByte);
        if (i <= 0)
        {
          paramFile1.close();
          paramFile2.close();
          return true;
        }
        paramFile2.write(arrayOfByte, 0, i);
      }
      return false;
    }
    catch (FileNotFoundException paramFile1)
    {
      return false;
    }
    catch (IOException paramFile1) {}
  }
  
  private void createSymlink(final String paramString1, final String paramString2)
  {
    mDialogTitle = getString(2131230752);
    mDialogMsg = getString(2131230816);
    showDialog(5);
    showProgressSpinner(true);
    new Thread()
    {
      public void run()
      {
        Looper.prepare();
        FileBrowser.getMounts("rw");
        CMDProcessor.CommandResult localCommandResult = new CMDProcessor().su.runWaitFor("ln -s \"" + paramString1 + "\" \"" + paramString2 + "\"");
        FileBrowser.getMounts("ro");
        FileBrowser.this.handler.sendEmptyMessage(6);
        if (localCommandResult.success()) {
          FileBrowser.mToastMsg = FileBrowser.this.getString(2131230873);
        }
        for (;;)
        {
          FileBrowser.this.handler.sendEmptyMessage(0);
          return;
          FileBrowser.mStderr = localCommandResult.stderr;
          FileBrowser.mDialog = 36;
          FileBrowser.this.handler.sendEmptyMessage(7);
        }
      }
    }.start();
  }
  
  private static boolean deleteFile(File paramFile)
  {
    if (paramFile.isDirectory()) {
      return Helpers.deleteDirectory(paramFile);
    }
    return paramFile.delete();
  }
  
  private void doCmd(String paramString)
  {
    if (mPbarLayout.getVisibility() == 0)
    {
      alertBar(getString(2131230725), 5000);
      return;
    }
    Object localObject1;
    label38:
    Object localObject2;
    int i;
    if (mDoBatch)
    {
      localObject1 = mSelectedFiles;
      mDoBatch = false;
      if (localObject1 == null) {
        break label206;
      }
      localObject2 = ((FileInfoHolder)((List)localObject1).get(0)).getFileName();
      i = ((List)localObject1).size();
      if (isInSelectionMode) {
        showSelectionMode(false);
      }
      if (!paramString.equals("do_copy")) {
        break label257;
      }
      showOperationMode("do_copy", false);
      if (i <= 1) {
        break label215;
      }
      showProgressHorizontal(i, 0, true, getString(2131230808, new Object[] { "..." }), true, 0);
      if (mShowNotifications) {
        showStatusBarProgress(i, 0, getString(2131230795), 2130837611, 1820);
      }
    }
    for (;;)
    {
      new RunCommand(null).execute((FileInfoHolder[])((List)localObject1).toArray(new FileInfoHolder[0]));
      return;
      localObject2 = new ArrayList();
      if (mCmdFile == null) {}
      for (localObject1 = mFile;; localObject1 = mCmdFile)
      {
        ((List)localObject2).add(localObject1);
        localObject1 = localObject2;
        break label38;
        label206:
        break;
      }
      label215:
      mDialogTitle = getString(2131230752);
      mDialogMsg = getString(2131230808, new Object[] { localObject2 });
      showDialog(5);
      showProgressSpinner(true);
      continue;
      label257:
      if (paramString.equals("do_extract"))
      {
        if (mOperationLayout.getVisibility() == 0) {
          showOperationMode("do_extract", false);
        }
        for (;;)
        {
          mDialogTitle = getString(2131230752);
          mDialogMsg = getString(2131230809, new Object[] { localObject2 });
          showDialog(5);
          showProgressSpinner(true);
          break;
          mCmd = "do_extract";
        }
      }
      if (paramString.equals("do_move"))
      {
        showOperationMode("do_move", false);
        if (i > 1)
        {
          showProgressHorizontal(i, 0, true, getString(2131230810, new Object[] { "..." }), true, 0);
          if (mShowNotifications) {
            showStatusBarProgress(i, 0, getString(2131230799), 2130837611, 1820);
          }
        }
        else
        {
          mDialogTitle = getString(2131230752);
          mDialogMsg = getString(2131230810, new Object[] { localObject2 });
          showDialog(5);
          showProgressSpinner(true);
        }
      }
      else if (paramString.equals("do_delete"))
      {
        mCmd = "do_delete";
        if (i > 1)
        {
          showProgressHorizontal(i, 0, true, getString(2131230811, new Object[] { "..." }), true, 0);
          if (mShowNotifications) {
            showStatusBarProgress(i, 0, getString(2131230803), 2130837611, 1820);
          }
        }
        else
        {
          mDialogTitle = getString(2131230752);
          mDialogMsg = getString(2131230811, new Object[] { localObject2 });
          showDialog(5);
          showProgressSpinner(true);
        }
      }
      else if (paramString.equals("do_zip_file"))
      {
        if ((!new File("/data/data/com.jrummy.root.browserfree/files/zip").exists()) && (mIsRootUser))
        {
          transferAsset("zip", "zip");
          new CMDProcessor().su.runWaitFor("chmod 755 /data/data/com.jrummy.root.browserfree/files/zip");
        }
        if (mOperationLayout.getVisibility() == 0) {
          showOperationMode("do_zip_file", false);
        }
        for (;;)
        {
          if (i <= 1) {
            break label698;
          }
          showProgressHorizontal(i, 0, true, getString(2131230812), true, 0);
          if (!mShowNotifications) {
            break;
          }
          showStatusBarProgress(i, 0, getString(2131230807), 2130837611, 1820);
          break;
          mCmd = "do_zip_file";
        }
        label698:
        mDialogTitle = getString(2131230752);
        mDialogMsg = getString(2131230814, new Object[] { localObject2 });
        showDialog(5);
        showProgressSpinner(true);
      }
      else
      {
        if (!paramString.equals("do_tar")) {
          break;
        }
        if (mOperationLayout.getVisibility() == 0) {
          showOperationMode("do_tar", false);
        }
        for (;;)
        {
          if (i <= 1) {
            break label822;
          }
          showProgressHorizontal(i, 0, true, getString(2131230813), true, 0);
          if (!mShowNotifications) {
            break;
          }
          showStatusBarProgress(i, 0, getString(2131230807), 2130837611, 1820);
          break;
          mCmd = "do_tar";
        }
        label822:
        mDialogTitle = getString(2131230752);
        mDialogMsg = getString(2131230814, new Object[] { localObject2 });
        showDialog(5);
        showProgressSpinner(true);
      }
    }
  }
  
  private void extractFromInsideZip(final String paramString1, final String paramString2, final String[] paramArrayOfString)
  {
    int j = paramArrayOfString.length;
    int i;
    if (paramArrayOfString.length < 7)
    {
      localObject = new StringBuilder();
      i = 0;
      if (i < j) {}
    }
    for (Object localObject = ((StringBuilder)localObject).toString();; localObject = "files")
    {
      mDialogTitle = getString(2131230752);
      mDialogMsg = getString(2131230809, new Object[] { localObject });
      showDialog(5);
      new Thread()
      {
        public void run()
        {
          Looper.prepare();
          String[] arrayOfString = paramArrayOfString;
          int j = arrayOfString.length;
          int i = 0;
          if (i >= j)
          {
            FileBrowser.mZipFilesToExtract = null;
            FileBrowser.mToastMsg = FileBrowser.this.getString(2131230872, new Object[] { this.val$msg });
            if (FileBrowser.isExploringZip) {
              break label122;
            }
            FileBrowser.this.handler.sendEmptyMessage(6);
          }
          for (;;)
          {
            FileBrowser.this.handler.sendEmptyMessage(0);
            return;
            String str = arrayOfString[i];
            try
            {
              Helpers.unzipSingleFile(paramString1, paramString2, str);
              i += 1;
            }
            catch (ZipException localZipException)
            {
              for (;;)
              {
                localZipException.printStackTrace();
              }
            }
            catch (IOException localIOException)
            {
              for (;;)
              {
                localIOException.printStackTrace();
              }
            }
            label122:
            FileBrowser.mToastMsg = FileBrowser.mToastMsg + " to " + paramString2;
          }
        }
      }.start();
      return;
      ((StringBuilder)localObject).append(paramArrayOfString[i].substring(paramArrayOfString[i].lastIndexOf("/") + 1));
      if ((i != 6) && (j > 1)) {
        ((StringBuilder)localObject).append(", ");
      }
      i += 1;
      break;
    }
  }
  
  public static Bitmap getBitmapFromZip(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new ZipInputStream(new FileInputStream(paramString1));
      ZipEntry localZipEntry;
      do
      {
        localZipEntry = paramString1.getNextEntry();
        if (localZipEntry == null) {
          return null;
        }
      } while (!localZipEntry.getName().equals(paramString2));
      paramString1 = BitmapFactory.decodeStream(paramString1);
      return paramString1;
    }
    catch (FileNotFoundException paramString1)
    {
      Log.e("FileBrowser", "Extracting file: Error opening zip file - FileNotFoundException: ", paramString1);
      return null;
    }
    catch (IOException paramString1)
    {
      Log.e("FileBrowser", "Extracting file: Error opening zip file - IOException: ", paramString1);
    }
    return null;
  }
  
  private static String getMD5Sum(String paramString)
  {
    CMDProcessor localCMDProcessor = new CMDProcessor();
    CMDProcessor.SH localSH = localCMDProcessor.sh;
    if (new File(paramString).canRead()) {
      if (mIsRootUser) {}
    }
    do
    {
      do
      {
        return null;
        localSH = localCMDProcessor.su;
        paramString = localSH.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox md5sum " + paramString).stdout;
      } while (paramString == null);
      paramString = paramString.split("\\s+");
    } while (paramString.length < 2);
    return paramString[0];
  }
  
  public static void getMounts(String paramString)
  {
    Helpers.getMount(paramString);
    if (isRootDir) {
      Helpers.getRootMount(paramString);
    }
  }
  
  public static String getPermStr(String paramString)
  {
    if (paramString != null) {
      return paramString.substring(1, paramString.length());
    }
    return "";
  }
  
  private static List<FileInfoHolder> getSelected(List<FileInfoHolder> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localArrayList;
      }
      FileInfoHolder localFileInfoHolder = (FileInfoHolder)paramList.next();
      if (localFileInfoHolder.isChecked()) {
        localArrayList.add(localFileInfoHolder);
      }
    }
  }
  
  private String getSizeStr(long paramLong)
  {
    return Formatter.formatFileSize(getBaseContext(), paramLong);
  }
  
  private void goBackAFolder()
  {
    if (isExploringZip)
    {
      setZipExplorerView(false);
      isExploringZip = false;
    }
    for (String str = mPath.getAbsolutePath();; str = mPath.getParent())
    {
      UpdateList(str, false);
      return;
    }
  }
  
  private boolean isNewerVersion()
  {
    return !getString(2131230724).equals(preferences.getString("ver", ""));
  }
  
  private static boolean isOnSD(File paramFile)
  {
    return (paramFile.getAbsolutePath().startsWith(SD)) || (paramFile.getAbsolutePath().startsWith("/sdcard"));
  }
  
  private Boolean mLockScreenRotation()
  {
    switch (this.res.getConfiguration().orientation)
    {
    default: 
      return null;
    case 1: 
      setRequestedOrientation(1);
      return Boolean.valueOf(true);
    }
    setRequestedOrientation(0);
    return Boolean.valueOf(false);
  }
  
  private void multiZipDialog(final String paramString, final File[] paramArrayOfFile)
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903050);
    localDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(2130837533));
    ImageView localImageView = (ImageView)localDialog.findViewById(2131361811);
    TextView localTextView1 = (TextView)localDialog.findViewById(2131361813);
    TextView localTextView2 = (TextView)localDialog.findViewById(2131361858);
    final EditText localEditText = (EditText)localDialog.findViewById(2131361859);
    Button localButton1 = (Button)localDialog.findViewById(2131361857);
    Button localButton2 = (Button)localDialog.findViewById(2131361856);
    localTextView1.setTypeface(MAIN_FONT);
    localEditText.setTypeface(SUB_FONT);
    localButton1.setTypeface(SUB_FONT);
    localButton2.setTypeface(SUB_FONT);
    localTextView2.setTypeface(SUB_FONT);
    localImageView.setImageDrawable(getResources().getDrawable(2130837557));
    localTextView1.setText("Zip Name");
    localTextView2.setText("Please enter the name of the zip:");
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        String str = localEditText.getText().toString();
        paramAnonymousView = str;
        if (!str.endsWith(".zip")) {
          paramAnonymousView = str + ".zip";
        }
        FileBrowser.mZipLocation = new File(paramString, paramAnonymousView);
        if (FileBrowser.isInSelectionMode) {
          FileBrowser.this.showSelectionMode(false);
        }
        FileBrowser.this.showOperationMode("do_zip_file", false);
        FileBrowser.this.zipMultipleFiles(paramArrayOfFile, FileBrowser.mZipLocation.getAbsolutePath());
      }
    });
    localDialog.show();
  }
  
  public static String permissionMode(String paramString)
  {
    int i5 = 0;
    int n = 0;
    int i2 = 0;
    int i1 = 0;
    int i4 = 0;
    int i6 = paramString.length();
    if (i4 >= i6)
    {
      paramString = Integer.toString(i5);
      String str1 = Integer.toString(n);
      String str2 = Integer.toString(i2);
      String str3 = Integer.toString(i1);
      return paramString + str1 + str2 + str3;
    }
    int m;
    int k;
    int j;
    int i;
    switch (i4)
    {
    default: 
      m = n;
      k = i5;
      j = i1;
      i = i2;
    }
    for (;;)
    {
      i4 += 1;
      i2 = i;
      i1 = j;
      i5 = k;
      n = m;
      break;
      i = i2;
      j = i1;
      k = i5;
      m = n;
      if (paramString.charAt(i4) != '-')
      {
        m = n + 4;
        i = i2;
        j = i1;
        k = i5;
        continue;
        i = i2;
        j = i1;
        k = i5;
        m = n;
        if (paramString.charAt(i4) != '-')
        {
          m = n + 2;
          i = i2;
          j = i1;
          k = i5;
          continue;
          int i3 = n;
          if (paramString.charAt(i4) != '-') {
            i3 = n + 1;
          }
          i = i2;
          j = i1;
          k = i5;
          m = i3;
          if (paramString.toLowerCase().charAt(i4) == 's')
          {
            k = i5 + 4;
            i = i2;
            j = i1;
            m = i3;
            continue;
            i = i2;
            j = i1;
            k = i5;
            m = n;
            if (paramString.charAt(i4) != '-')
            {
              i = i2 + 4;
              j = i1;
              k = i5;
              m = n;
              continue;
              i = i2;
              j = i1;
              k = i5;
              m = n;
              if (paramString.charAt(i4) != '-')
              {
                i = i2 + 2;
                j = i1;
                k = i5;
                m = n;
                continue;
                i3 = i2;
                if (paramString.charAt(i4) != '-') {
                  i3 = i2 + 1;
                }
                i = i3;
                j = i1;
                k = i5;
                m = n;
                if (paramString.toLowerCase().charAt(i4) == 's')
                {
                  k = i5 + 2;
                  i = i3;
                  j = i1;
                  m = n;
                  continue;
                  i = i2;
                  j = i1;
                  k = i5;
                  m = n;
                  if (paramString.charAt(i4) != '-')
                  {
                    j = i1 + 4;
                    i = i2;
                    k = i5;
                    m = n;
                    continue;
                    i = i2;
                    j = i1;
                    k = i5;
                    m = n;
                    if (paramString.charAt(i4) != '-')
                    {
                      j = i1 + 2;
                      i = i2;
                      k = i5;
                      m = n;
                      continue;
                      i3 = i1;
                      if (paramString.charAt(i4) != '-') {
                        i3 = i1 + 1;
                      }
                      i = i2;
                      j = i3;
                      k = i5;
                      m = n;
                      if (paramString.toLowerCase().charAt(i4) == 't')
                      {
                        k = i5 + 1;
                        i = i2;
                        j = i3;
                        m = n;
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
  
  private void renameDialog()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903050);
    localDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(2130837533));
    ImageView localImageView = (ImageView)localDialog.findViewById(2131361811);
    TextView localTextView1 = (TextView)localDialog.findViewById(2131361813);
    TextView localTextView2 = (TextView)localDialog.findViewById(2131361858);
    final EditText localEditText = (EditText)localDialog.findViewById(2131361859);
    Button localButton1 = (Button)localDialog.findViewById(2131361857);
    Button localButton2 = (Button)localDialog.findViewById(2131361856);
    localTextView1.setTypeface(MAIN_FONT);
    localEditText.setTypeface(SUB_FONT);
    localButton1.setTypeface(SUB_FONT);
    localButton2.setTypeface(SUB_FONT);
    localTextView2.setTypeface(SUB_FONT);
    localImageView.setImageDrawable(getResources().getDrawable(2130837551));
    localTextView1.setText(getString(2131230915));
    localTextView2.setText(getString(2131230915) + " " + mFile.getFileName() + ":");
    localEditText.setText(mFile.getFileName());
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
        Object localObject = localEditText.getText().toString();
        paramAnonymousView = new File(FileBrowser.mFile.getFilePath());
        localObject = new File(FileBrowser.mPath.getPath() + "/" + (String)localObject);
        boolean bool2 = paramAnonymousView.renameTo((File)localObject);
        boolean bool1 = bool2;
        if (!bool2)
        {
          bool1 = bool2;
          if (FileBrowser.mIsRootUser)
          {
            FileBrowser.getMounts("rw");
            bool1 = new CMDProcessor().su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mv \"" + paramAnonymousView + "\" \"" + localObject + "\"").success();
            FileBrowser.getMounts("ro");
          }
        }
        if (bool1)
        {
          FileBrowser.mFile.setFileName(((File)localObject).getName());
          FileBrowser.mFile.setFilePath(((File)localObject).getAbsolutePath());
          FileBrowser.mAdapter.notifyDataSetChanged();
          Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230912));
          return;
        }
        FileBrowser.this.alertBar(FileBrowser.this.getString(2131230726, new Object[] { FileBrowser.mFile.getFileName() }), 5000);
      }
    });
    localDialog.show();
  }
  
  private void searchDialog()
  {
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(2130903050);
    localDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(2130837533));
    ImageView localImageView = (ImageView)localDialog.findViewById(2131361811);
    TextView localTextView1 = (TextView)localDialog.findViewById(2131361813);
    TextView localTextView2 = (TextView)localDialog.findViewById(2131361858);
    final EditText localEditText = (EditText)localDialog.findViewById(2131361859);
    Button localButton1 = (Button)localDialog.findViewById(2131361857);
    Button localButton2 = (Button)localDialog.findViewById(2131361856);
    localTextView2.setVisibility(8);
    localTextView1.setTypeface(MAIN_FONT);
    localEditText.setTypeface(SUB_FONT);
    localButton1.setTypeface(SUB_FONT);
    localButton2.setTypeface(SUB_FONT);
    localImageView.setImageDrawable(getResources().getDrawable(2130837598));
    localTextView1.setText(getString(2131230901));
    localEditText.setText("");
    localEditText.setHint(getString(2131230901));
    localButton2.setText(getString(2131230901));
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localDialog.dismiss();
      }
    });
    localButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(final View paramAnonymousView)
      {
        localDialog.dismiss();
        paramAnonymousView = localEditText.getText().toString();
        FileBrowser.this.showProgressSpinner(true);
        FileBrowser.mDialogTitle = FileBrowser.this.getString(2131230752);
        FileBrowser.mDialogMsg = FileBrowser.this.getString(2131230782, new Object[] { paramAnonymousView });
        FileBrowser.this.showDialog(5);
        new Thread()
        {
          public void run()
          {
            
            if (FileBrowser.mPath.canRead()) {
              FileBrowser.SEARCH = FileBrowser.this.searchInDirectory(FileBrowser.mPath.toString(), paramAnonymousView);
            }
            for (;;)
            {
              FileBrowser.this.handler.sendEmptyMessage(0);
              FileBrowser.mDialog = 9;
              FileBrowser.this.handler.sendEmptyMessageDelayed(7, 500L);
              return;
              if (FileBrowser.mIsRootUser)
              {
                FileBrowser.SEARCH = new ArrayList();
                Object localObject1 = new CMDProcessor().su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox find \"" + FileBrowser.mPath + "\" -iname \"*" + paramAnonymousView + "*\"").stdout;
                if ((localObject1 != null) && (!((String)localObject1).equals("")))
                {
                  localObject1 = ((String)localObject1).split("[ \n]+");
                  if (localObject1 != null)
                  {
                    int j = localObject1.length;
                    int i = 0;
                    while (i < j)
                    {
                      Object localObject2 = localObject1[i];
                      FileBrowser.SEARCH.add(localObject2);
                      i += 1;
                    }
                  }
                }
              }
            }
          }
        }.start();
      }
    });
    localDialog.show();
  }
  
  private void search_file(String paramString1, String paramString2, ArrayList<String> paramArrayList)
  {
    File localFile = new File(paramString1);
    String[] arrayOfString = localFile.list();
    int i;
    if ((arrayOfString != null) && (localFile.canRead()))
    {
      int j = arrayOfString.length;
      i = 0;
      if (i < j) {}
    }
    else
    {
      return;
    }
    localFile = new File(paramString1 + "/" + arrayOfString[i]);
    String str = localFile.getName();
    if ((localFile.isFile()) && (str.toLowerCase().contains(paramString2.toLowerCase()))) {
      paramArrayList.add(localFile.getPath());
    }
    for (;;)
    {
      i += 1;
      break;
      if (localFile.isDirectory()) {
        if (str.toLowerCase().contains(paramString2.toLowerCase())) {
          paramArrayList.add(localFile.getPath());
        } else if ((localFile.canRead()) && (!paramString1.equals("/"))) {
          search_file(localFile.getAbsolutePath(), paramString2, paramArrayList);
        }
      }
    }
  }
  
  private void setDirectoryButtons()
  {
    String[] arrayOfString = mPath.getAbsolutePath().split("/");
    mDirectoryButtons.removeAllViews();
    Object localObject = new TextView(this);
    ((TextView)localObject).setText("/");
    ((TextView)localObject).setClickable(true);
    ((TextView)localObject).setTextColor(this.res.getColor(2131165190));
    ((TextView)localObject).setTypeface(MAIN_FONT);
    ((TextView)localObject).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    ((TextView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FileBrowser.this.UpdateList("/", true);
      }
    });
    mDirectoryButtons.addView((View)localObject);
    localObject = "";
    int i = 1;
    if (i >= arrayOfString.length) {
      return;
    }
    String str = localObject + "/" + arrayOfString[i];
    TextView localTextView = new TextView(this);
    localTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    if (i == 1) {}
    for (localObject = "";; localObject = "/")
    {
      localTextView.setText(localObject + arrayOfString[i]);
      localTextView.setClickable(true);
      localTextView.setTextColor(this.res.getColor(2131165190));
      localTextView.setTypeface(MAIN_FONT);
      localTextView.setTag(str);
      localTextView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          ((TextView)paramAnonymousView).setTextColor(-12676677);
          return false;
        }
      });
      localTextView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = (String)paramAnonymousView.getTag();
          FileBrowser.this.UpdateList(paramAnonymousView, true);
        }
      });
      mDirectoryButtons.addView(localTextView);
      i += 1;
      localObject = str;
      break;
    }
  }
  
  private void setFileInfo(File paramFile, FileInfoHolder paramFileInfoHolder)
  {
    long l1 = paramFile.lastModified();
    long l2 = paramFile.length();
    paramFileInfoHolder.setIsDirectory(paramFile.isDirectory());
    if (paramFileInfoHolder.isSymlink()) {
      paramFileInfoHolder.setFileSize(0L);
    }
    for (;;)
    {
      paramFileInfoHolder.setSize(getSizeStr(l2));
      paramFileInfoHolder.setLastUpdateTime(l1);
      paramFileInfoHolder.setFilePath(paramFile.getAbsolutePath());
      if (!mIsRootUser)
      {
        paramFileInfoHolder.setFileName(paramFile.getName());
        paramFileInfoHolder.setFilePath(paramFile.getAbsolutePath());
        paramFile = new SimpleDateFormat("dd").format(Long.valueOf(l1));
        String str1 = new SimpleDateFormat("yyyy").format(Long.valueOf(l1));
        String str2 = new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(l1));
        Object localObject = Calendar.getInstance();
        localObject = monthName[localObject.get(2)];
        paramFileInfoHolder.setDate(paramFile + " " + (String)localObject + " " + str1 + " " + str2);
        setFileType(paramFileInfoHolder);
      }
      return;
      paramFileInfoHolder.setFileSize(l2);
    }
  }
  
  private static void setFileType(FileInfoHolder paramFileInfoHolder)
  {
    if (paramFileInfoHolder.isDirectory()) {
      return;
    }
    String str1 = paramFileInfoHolder.getFileName();
    String[] arrayOfString2 = { "sh", "bsh", "rc" };
    String[] arrayOfString3 = { "png", "jpg", "jpeg", "bmp", "gif", "jfif", "tiff", "ico" };
    String[] arrayOfString4 = { "mp4", "3gp", "mpeg", "avi", "flv", "mpeg-4", "wmv" };
    String[] arrayOfString5 = { "wav", "flac", "wv", "wma", "mp2", "mp3", "ogg", "m4a" };
    String[] arrayOfString6 = { "db", "sqlite" };
    int i = 0;
    if (i >= 9)
    {
      if (str1.endsWith(".apk")) {
        paramFileInfoHolder.setIsApk(true);
      }
    }
    else
    {
      String[] arrayOfString1 = (String[])new Object[] { { "zip", "7z", "bz2" }, { "tar.gz", "tar.bz2", "tar.Z", "tar.lz", "tar.xz", "tba", "tgz", "tbz", "taz", "tlz", "txz" }, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, { "ttf", "otf" }, { "xml", "html", "xhtml", "mhtml", "php", "rss", "atom" } }[i];
      int k = arrayOfString1.length;
      int j = 0;
      for (;;)
      {
        if (j >= k)
        {
          i += 1;
          break;
        }
        String str2 = arrayOfString1[j];
        if (str1.toLowerCase().endsWith("." + str2))
        {
          switch (i)
          {
          default: 
            return;
          case 0: 
            paramFileInfoHolder.setIsZip(true);
            return;
          case 1: 
            paramFileInfoHolder.setIsTar(true);
            return;
          case 2: 
            paramFileInfoHolder.setIsScript(true);
            return;
          case 3: 
            paramFileInfoHolder.setIsImage(true);
            return;
          case 4: 
            paramFileInfoHolder.setIsVideo(true);
            return;
          case 5: 
            paramFileInfoHolder.setIsAudio(true);
            return;
          case 6: 
            paramFileInfoHolder.setIsDatabase(true);
            return;
          case 7: 
            paramFileInfoHolder.setIsFont(true);
            return;
          }
          paramFileInfoHolder.setIsWebpage(true);
          return;
        }
        j += 1;
      }
    }
    if (str1.endsWith(".prop"))
    {
      paramFileInfoHolder.setIsProp(true);
      return;
    }
    if (str1.endsWith(".jar"))
    {
      paramFileInfoHolder.setIsJar(true);
      return;
    }
    if (str1.endsWith(".csv"))
    {
      paramFileInfoHolder.setIsCsv(true);
      return;
    }
    paramFileInfoHolder.setIsTxt(true);
  }
  
  private static void setMimeType(FileInfoHolder paramFileInfoHolder)
  {
    if (!paramFileInfoHolder.isDirectory()) {
      paramFileInfoHolder.setMimeType(Helpers.getMimeType(paramFileInfoHolder.getFileName().toLowerCase()));
    }
  }
  
  private void setZipExplorerView(boolean paramBoolean)
  {
    int i = 0;
    int[] arrayOfInt = new int[6];
    int[] tmp10_8 = arrayOfInt;
    tmp10_8[0] = 2131361874;
    int[] tmp16_10 = tmp10_8;
    tmp16_10[1] = 2131361873;
    int[] tmp22_16 = tmp16_10;
    tmp22_16[2] = 2131361876;
    int[] tmp28_22 = tmp22_16;
    tmp28_22[3] = 2131361875;
    int[] tmp34_28 = tmp28_22;
    tmp34_28[4] = 2131361878;
    int[] tmp40_34 = tmp34_28;
    tmp40_34[5] = 2131361877;
    tmp40_34;
    int j;
    if (paramBoolean)
    {
      mTitleText.setText(mZipFile.substring(mZipFile.lastIndexOf("/") + 1));
      if (mColorBarLayout.getVisibility() != 8)
      {
        mColorBarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
        mColorBarLayout.setVisibility(8);
      }
      if (mDirectoryButtons.getVisibility() != 8)
      {
        mDirectoryButtons.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968579));
        mDirectoryButtons.setVisibility(8);
      }
      j = arrayOfInt.length;
      if (i < j) {}
    }
    for (;;)
    {
      return;
      View localView = findViewById(arrayOfInt[i]);
      if (localView.getVisibility() == 0)
      {
        localView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968596));
        localView.setVisibility(8);
      }
      i += 1;
      break;
      mTitleText.setText(getString(2131230720));
      mDirectoryButtons.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968578));
      mDirectoryButtons.setVisibility(0);
      j = arrayOfInt.length;
      i = 0;
      while (i < j)
      {
        localView = findViewById(arrayOfInt[i]);
        localView.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968597));
        localView.setVisibility(0);
        i += 1;
      }
    }
  }
  
  private void sortFiles(int paramInt)
  {
    mSortType = paramInt;
    Collections.sort(mFiles, new NameComparator(paramInt));
    Collections.sort(mAllFiles, new NameComparator(paramInt));
    Iterator localIterator;
    if ((mSortType == 2) || (mSortType == 3))
    {
      Collections.sort(mFiles, new DateComparator(paramInt));
      Collections.sort(mAllFiles, new DateComparator(paramInt));
      if ((mSortType == 6) || (mFoldersFirst))
      {
        mFiles.clear();
        localIterator = mAllFiles.iterator();
        label105:
        if (localIterator.hasNext()) {
          break label204;
        }
        localIterator = mAllFiles.iterator();
      }
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        mAllFiles.clear();
        mAllFiles.addAll(mFiles);
        mAdapter.notifyDataSetChanged();
        return;
        if ((mSortType != 4) && (mSortType != 5)) {
          break;
        }
        Collections.sort(mFiles, new SizeComparator(paramInt));
        Collections.sort(mAllFiles, new SizeComparator(paramInt));
        break;
        label204:
        localFileInfoHolder = (FileInfoHolder)localIterator.next();
        if (!localFileInfoHolder.isDirectory()) {
          break label105;
        }
        mFiles.add(localFileInfoHolder);
        break label105;
      }
      FileInfoHolder localFileInfoHolder = (FileInfoHolder)localIterator.next();
      if (!localFileInfoHolder.isDirectory()) {
        mFiles.add(localFileInfoHolder);
      }
    }
  }
  
  public void UpdateList(String paramString, boolean paramBoolean)
  {
    mPath = new File(paramString);
    mFiles.clear();
    mAllFiles.clear();
    Object localObject1;
    boolean bool1;
    Object localObject2;
    int i;
    int j;
    if (!mIsRootUser)
    {
      paramString = mPath.listFiles();
      if (paramString != null)
      {
        localObject1 = mPath.getParentFile();
        if (localObject1 == null)
        {
          bool1 = true;
          isRootDir = bool1;
          if (!isRootDir)
          {
            isRootDir = false;
            localObject2 = new FileInfoHolder();
            setFileInfo((File)localObject1, (FileInfoHolder)localObject2);
            ((FileInfoHolder)localObject2).setFileName("..");
            mFiles.add(localObject2);
            mAllFiles.add(localObject2);
          }
          i = 0;
          j = paramString.length;
          if (i < j) {
            break label225;
          }
        }
      }
      else
      {
        sortFiles(mSortType);
        new GetIcons(null).execute((FileInfoHolder[])mAllFiles.toArray(new FileInfoHolder[0]));
        updateStorageUsage();
        if (!paramBoolean) {
          break label2345;
        }
        mListView.setAdapter(mAdapter);
      }
    }
    for (;;)
    {
      label204:
      label225:
      label321:
      label383:
      label406:
      Object localObject3;
      File[] arrayOfFile;
      label512:
      label543:
      label551:
      label566:
      Object localObject14;
      boolean bool2;
      Object localObject4;
      Object localObject5;
      Object localObject9;
      Object localObject6;
      Object localObject7;
      Object localObject8;
      if (mFiles.isEmpty())
      {
        mEmptyList.setVisibility(0);
        setDirectoryButtons();
        if (mDoExit) {
          mDoExit = false;
        }
        String[] arrayOfString1;
        do
        {
          return;
          bool1 = false;
          break;
          if ((!mShowHidden) && (paramString[i].getName().startsWith("."))) {}
          for (;;)
          {
            i += 1;
            break;
            localObject1 = new FileInfoHolder();
            setFileInfo(paramString[i], (FileInfoHolder)localObject1);
            setMimeType((FileInfoHolder)localObject1);
            mFiles.add(localObject1);
            mAllFiles.add(localObject1);
          }
          localObject2 = new CMDProcessor();
          if (!mShowHidden) {
            break label543;
          }
          localObject1 = "-a";
          localObject1 = "/data/data/com.jrummy.root.browserfree/files/busybox ls -l -n -e " + (String)localObject1 + " \"" + mPath.getAbsolutePath() + "/\"";
          if (!mPath.canRead()) {
            break label551;
          }
          localObject1 = ((CMDProcessor)localObject2).sh.runWaitFor((String)localObject1);
          localObject1 = ((CMDProcessor.CommandResult)localObject1).stdout;
          localObject2 = mPath.getParentFile();
          if (localObject2 != null) {
            break label566;
          }
          bool1 = true;
          isRootDir = bool1;
          if (!isRootDir)
          {
            localObject3 = new FileInfoHolder();
            setFileInfo((File)localObject2, (FileInfoHolder)localObject3);
            ((FileInfoHolder)localObject3).setFileName("..");
            ((FileInfoHolder)localObject3).setIsDirectory(true);
            mFiles.add(localObject3);
            mAllFiles.add(localObject3);
          }
          if (localObject1 == null) {
            break label512;
          }
          arrayOfString1 = ((String)localObject1).split("\n");
        } while (arrayOfString1 == null);
        int i2 = arrayOfString1.length;
        arrayOfFile = new File[i2];
        j = 0;
        Object localObject10;
        int k;
        boolean bool4;
        String[] arrayOfString2;
        int m;
        for (;;)
        {
          if (j >= i2)
          {
            new GetSizes(null).execute((FileInfoHolder[])mAllFiles.toArray(new FileInfoHolder[0]));
            break;
            localObject1 = "";
            break label321;
            localObject1 = ((CMDProcessor)localObject2).su.runWaitFor((String)localObject1);
            break label383;
            bool1 = false;
            break label406;
          }
          localObject13 = "";
          localObject17 = "";
          localObject1 = "";
          localObject11 = "";
          localObject2 = "";
          localObject16 = "";
          localObject18 = "";
          localObject10 = "";
          localObject15 = "";
          k = 0;
          bool5 = false;
          bool3 = false;
          boolean bool6 = false;
          bool4 = false;
          arrayOfString2 = arrayOfString1[j].split("\\s+");
          i = k;
          localObject14 = localObject2;
          localObject12 = localObject10;
          localObject3 = localObject1;
          bool1 = bool5;
          bool2 = bool6;
          localObject4 = localObject11;
          localObject5 = localObject13;
          localObject9 = localObject15;
          localObject6 = localObject16;
          localObject7 = localObject17;
          localObject8 = localObject18;
          if (arrayOfString2 != null)
          {
            int i3 = arrayOfString2.length;
            i = k;
            localObject14 = localObject2;
            localObject12 = localObject10;
            localObject3 = localObject1;
            bool1 = bool5;
            bool2 = bool6;
            localObject4 = localObject11;
            localObject5 = localObject13;
            localObject9 = localObject15;
            localObject6 = localObject16;
            localObject7 = localObject17;
            localObject8 = localObject18;
            if (i3 >= 11)
            {
              i = 1;
              m = 0;
              k = 0;
              localObject8 = localObject18;
              localObject7 = localObject17;
              localObject6 = localObject16;
              localObject9 = localObject15;
              localObject5 = localObject13;
              localObject4 = localObject11;
              bool2 = bool4;
              bool1 = bool3;
              localObject3 = localObject1;
              if (k < i3) {
                break label828;
              }
              localObject12 = localObject10;
              localObject14 = localObject2;
            }
          }
          if (i != 0) {
            break label2005;
          }
          label819:
          j += 1;
        }
        label828:
        int n = i;
        int i1 = m;
        Object localObject11 = localObject2;
        localObject1 = localObject10;
        Object localObject12 = localObject3;
        boolean bool3 = bool1;
        boolean bool5 = bool2;
        Object localObject13 = localObject4;
        localObject14 = localObject5;
        Object localObject15 = localObject9;
        Object localObject16 = localObject6;
        Object localObject17 = localObject7;
        Object localObject18 = localObject8;
        switch (k)
        {
        default: 
          if (arrayOfString2[k].equals("->"))
          {
            localObject1 = ((String)localObject10).replace(arrayOfString1[j].substring(arrayOfString1[j].indexOf(arrayOfString2[k]) - 1), "");
            i1 = 1;
            localObject18 = localObject8;
            localObject17 = localObject7;
            localObject16 = localObject6;
            localObject15 = localObject9;
            localObject14 = localObject5;
            localObject13 = localObject4;
            bool5 = bool2;
            bool3 = bool1;
            localObject12 = localObject3;
            localObject11 = localObject2;
            n = i;
          }
          break;
        }
        for (;;)
        {
          k += 1;
          i = n;
          m = i1;
          localObject2 = localObject11;
          localObject10 = localObject1;
          localObject3 = localObject12;
          bool1 = bool3;
          bool2 = bool5;
          localObject4 = localObject13;
          localObject5 = localObject14;
          localObject9 = localObject15;
          localObject6 = localObject16;
          localObject7 = localObject17;
          localObject8 = localObject18;
          break;
          localObject14 = arrayOfString2[k];
          if (arrayOfString2[k].charAt(0) == 'd')
          {
            bool3 = true;
            label1117:
            if (arrayOfString2[k].charAt(0) != 'l') {
              break label1186;
            }
          }
          label1186:
          for (bool1 = true;; bool1 = false)
          {
            n = i;
            i1 = m;
            localObject11 = localObject2;
            localObject1 = localObject10;
            localObject12 = localObject3;
            bool5 = bool1;
            localObject13 = localObject4;
            localObject15 = localObject9;
            localObject16 = localObject6;
            localObject17 = localObject7;
            localObject18 = localObject8;
            break;
            bool3 = false;
            break label1117;
          }
          localObject17 = arrayOfString2[k];
          n = i;
          i1 = m;
          localObject11 = localObject2;
          localObject1 = localObject10;
          localObject12 = localObject3;
          bool3 = bool1;
          bool5 = bool2;
          localObject13 = localObject4;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject16 = localObject6;
          localObject18 = localObject8;
          continue;
          localObject12 = arrayOfString2[k];
          n = i;
          i1 = m;
          localObject11 = localObject2;
          localObject1 = localObject10;
          bool3 = bool1;
          bool5 = bool2;
          localObject13 = localObject4;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject16 = localObject6;
          localObject17 = localObject7;
          localObject18 = localObject8;
          continue;
          localObject13 = arrayOfString2[k];
          n = i;
          i1 = m;
          localObject11 = localObject2;
          localObject1 = localObject10;
          localObject12 = localObject3;
          bool3 = bool1;
          bool5 = bool2;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject16 = localObject6;
          localObject17 = localObject7;
          localObject18 = localObject8;
          continue;
          localObject11 = arrayOfString2[k];
          n = i;
          i1 = m;
          localObject1 = localObject10;
          localObject12 = localObject3;
          bool3 = bool1;
          bool5 = bool2;
          localObject13 = localObject4;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject16 = localObject6;
          localObject17 = localObject7;
          localObject18 = localObject8;
          continue;
          localObject16 = arrayOfString2[k];
          n = i;
          i1 = m;
          localObject11 = localObject2;
          localObject1 = localObject10;
          localObject12 = localObject3;
          bool3 = bool1;
          bool5 = bool2;
          localObject13 = localObject4;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject17 = localObject7;
          localObject18 = localObject8;
          continue;
          localObject18 = arrayOfString2[k];
          n = i;
          i1 = m;
          localObject11 = localObject2;
          localObject1 = localObject10;
          localObject12 = localObject3;
          bool3 = bool1;
          bool5 = bool2;
          localObject13 = localObject4;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject16 = localObject6;
          localObject17 = localObject7;
          continue;
          localObject10 = arrayOfString1[j].substring(arrayOfString1[j].lastIndexOf(arrayOfString2[k]));
          n = i;
          i1 = m;
          localObject11 = localObject2;
          localObject1 = localObject10;
          localObject12 = localObject3;
          bool3 = bool1;
          bool5 = bool2;
          localObject13 = localObject4;
          localObject14 = localObject5;
          localObject15 = localObject9;
          localObject16 = localObject6;
          localObject17 = localObject7;
          localObject18 = localObject8;
          if (mShowHidden)
          {
            if ((!((String)localObject10).equals(".")) && (!((String)localObject10).equals(".."))) {}
            for (i = 1;; i = 0)
            {
              n = i;
              i1 = m;
              localObject11 = localObject2;
              localObject1 = localObject10;
              localObject12 = localObject3;
              bool3 = bool1;
              bool5 = bool2;
              localObject13 = localObject4;
              localObject14 = localObject5;
              localObject15 = localObject9;
              localObject16 = localObject6;
              localObject17 = localObject7;
              localObject18 = localObject8;
              break;
            }
            n = i;
            i1 = m;
            localObject11 = localObject2;
            localObject1 = localObject10;
            localObject12 = localObject3;
            bool3 = bool1;
            bool5 = bool2;
            localObject13 = localObject4;
            localObject14 = localObject5;
            localObject15 = localObject9;
            localObject16 = localObject6;
            localObject17 = localObject7;
            localObject18 = localObject8;
            if (m != 0)
            {
              n = i;
              i1 = m;
              localObject11 = localObject2;
              localObject1 = localObject10;
              localObject12 = localObject3;
              bool3 = bool1;
              bool5 = bool2;
              localObject13 = localObject4;
              localObject14 = localObject5;
              localObject15 = localObject9;
              localObject16 = localObject6;
              localObject17 = localObject7;
              localObject18 = localObject8;
              if (bool2)
              {
                localObject9 = arrayOfString2[k];
                bool4 = bool1;
                if (!bool1) {
                  bool4 = new File((String)localObject9).isDirectory();
                }
                n = i;
                i1 = m;
                localObject11 = localObject2;
                localObject1 = localObject10;
                localObject12 = localObject3;
                bool3 = bool4;
                bool5 = bool2;
                localObject13 = localObject4;
                localObject14 = localObject5;
                localObject15 = localObject9;
                localObject16 = localObject6;
                localObject17 = localObject7;
                localObject18 = localObject8;
                if (((String)localObject9).contains((CharSequence)localObject10))
                {
                  localObject1 = arrayOfString1[j].substring(arrayOfString1[j].lastIndexOf("->") - 1);
                  localObject1 = arrayOfString1[j].replace((CharSequence)localObject1, "");
                  localObject1 = ((String)localObject1).substring(((String)localObject1).lastIndexOf(arrayOfString2[10]));
                  n = i;
                  i1 = m;
                  localObject11 = localObject2;
                  localObject12 = localObject3;
                  bool3 = bool4;
                  bool5 = bool2;
                  localObject13 = localObject4;
                  localObject14 = localObject5;
                  localObject15 = localObject9;
                  localObject16 = localObject6;
                  localObject17 = localObject7;
                  localObject18 = localObject8;
                }
              }
            }
          }
        }
        label2005:
        localObject2 = new FileInfoHolder();
        if (paramString.equals("/"))
        {
          localObject1 = "";
          label2029:
          arrayOfFile[j] = new File(paramString + (String)localObject1 + (String)localObject12);
          ((FileInfoHolder)localObject2).setFileName((String)localObject12);
          ((FileInfoHolder)localObject2).setIsDirectory(bool1);
          ((FileInfoHolder)localObject2).setFilePath(paramString + (String)localObject1 + (String)localObject12);
          ((FileInfoHolder)localObject2).setDate(localObject14 + " " + localObject4 + " " + localObject8 + " " + localObject6);
        }
      }
      try
      {
        localObject1 = localObject14 + "-" + localObject4 + "-" + localObject8 + "-" + localObject6;
        ((FileInfoHolder)localObject2).setLastUpdateTime(new SimpleDateFormat("dd-MMM-yy-HH:mm:ss").parse((String)localObject1).getTime());
        ((FileInfoHolder)localObject2).setGroup((String)localObject3);
        ((FileInfoHolder)localObject2).setUserId(localObject7);
        ((FileInfoHolder)localObject2).setPermissions(localObject5);
        ((FileInfoHolder)localObject2).setIsSymlink(bool2);
        ((FileInfoHolder)localObject2).setSymlink((String)localObject9);
        setFileType((FileInfoHolder)localObject2);
        setMimeType((FileInfoHolder)localObject2);
        if ((arrayOfFile[j].exists()) && (arrayOfFile[j].canRead())) {
          setFileInfo(arrayOfFile[j], (FileInfoHolder)localObject2);
        }
        mFiles.add(localObject2);
        mAllFiles.add(localObject2);
        break label819;
        localObject1 = "/";
        break label2029;
        label2345:
        mAdapter.notifyDataSetChanged();
        continue;
        if (mEmptyList.getVisibility() != 0) {
          break label204;
        }
        mEmptyList.setVisibility(8);
      }
      catch (ParseException localParseException)
      {
        for (;;) {}
      }
    }
  }
  
  public void afterTextChanged(Editable paramEditable) {}
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void copyDirectory(File paramFile1, File paramFile2)
    throws IOException
  {
    int i;
    if (paramFile1.isDirectory())
    {
      if (!paramFile2.exists()) {
        paramFile2.mkdir();
      }
      localObject = paramFile1.list();
      i = 0;
      for (;;)
      {
        if (i >= localObject.length) {
          return;
        }
        copyDirectory(new File(paramFile1, localObject[i]), new File(paramFile2, localObject[i]));
        i += 1;
      }
    }
    paramFile1 = new FileInputStream(paramFile1);
    paramFile2 = new FileOutputStream(paramFile2);
    Object localObject = new byte['Ѐ'];
    for (;;)
    {
      i = paramFile1.read((byte[])localObject);
      if (i <= 0)
      {
        paramFile1.close();
        paramFile2.close();
        return;
      }
      paramFile2.write((byte[])localObject, 0, i);
    }
  }
  
  /* Error */
  public Drawable getFileIcon(FileInfoHolder paramFileInfoHolder, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 1493	com/jrummy/root/browserfree/FileInfoHolder:isDirectory	()Z
    //   4: ifeq +62 -> 66
    //   7: aload_1
    //   8: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   11: getstatic 494	com/jrummy/root/browserfree/FileBrowser:SD	Ljava/lang/String;
    //   14: invokevirtual 1127	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   17: ifeq +14 -> 31
    //   20: aload_0
    //   21: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   24: ldc_w 1804
    //   27: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   30: areturn
    //   31: aload_1
    //   32: invokevirtual 1117	com/jrummy/root/browserfree/FileInfoHolder:getFileName	()Ljava/lang/String;
    //   35: ldc_w 1709
    //   38: invokevirtual 1127	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   41: ifeq +14 -> 55
    //   44: aload_0
    //   45: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   48: ldc_w 1805
    //   51: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   54: areturn
    //   55: aload_0
    //   56: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   59: ldc_w 887
    //   62: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   65: areturn
    //   66: aload_1
    //   67: invokevirtual 1808	com/jrummy/root/browserfree/FileInfoHolder:isZip	()Z
    //   70: ifeq +14 -> 84
    //   73: aload_0
    //   74: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   77: ldc_w 1331
    //   80: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   83: areturn
    //   84: aload_1
    //   85: invokevirtual 1811	com/jrummy/root/browserfree/FileInfoHolder:isImage	()Z
    //   88: ifeq +133 -> 221
    //   91: iload_2
    //   92: ifne +118 -> 210
    //   95: new 1813	android/graphics/BitmapFactory$Options
    //   98: dup
    //   99: invokespecial 1814	android/graphics/BitmapFactory$Options:<init>	()V
    //   102: astore 5
    //   104: aload 5
    //   106: iconst_1
    //   107: putfield 1817	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   110: aload_1
    //   111: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   114: aload 5
    //   116: invokestatic 1821	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   119: pop
    //   120: aload 5
    //   122: getfield 1824	android/graphics/BitmapFactory$Options:outHeight	I
    //   125: i2f
    //   126: ldc_w 1825
    //   129: fdiv
    //   130: f2d
    //   131: invokestatic 1831	java/lang/Math:ceil	(D)D
    //   134: d2i
    //   135: istore_3
    //   136: aload 5
    //   138: getfield 1834	android/graphics/BitmapFactory$Options:outWidth	I
    //   141: i2f
    //   142: ldc_w 1825
    //   145: fdiv
    //   146: f2d
    //   147: invokestatic 1831	java/lang/Math:ceil	(D)D
    //   150: d2i
    //   151: istore 4
    //   153: iload_3
    //   154: iconst_1
    //   155: if_icmpgt +9 -> 164
    //   158: iload 4
    //   160: iconst_1
    //   161: if_icmple +15 -> 176
    //   164: iload_3
    //   165: iload 4
    //   167: if_icmple +32 -> 199
    //   170: aload 5
    //   172: iload_3
    //   173: putfield 1837	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   176: aload 5
    //   178: iconst_0
    //   179: putfield 1817	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   182: new 1839	android/graphics/drawable/BitmapDrawable
    //   185: dup
    //   186: aload_1
    //   187: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   190: aload 5
    //   192: invokestatic 1821	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   195: invokespecial 1842	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   198: areturn
    //   199: aload 5
    //   201: iload 4
    //   203: putfield 1837	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   206: goto -30 -> 176
    //   209: astore_1
    //   210: aload_0
    //   211: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   214: ldc_w 1843
    //   217: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   220: areturn
    //   221: aload_1
    //   222: invokevirtual 1846	com/jrummy/root/browserfree/FileInfoHolder:isTar	()Z
    //   225: ifeq +14 -> 239
    //   228: aload_0
    //   229: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   232: ldc_w 1847
    //   235: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   238: areturn
    //   239: aload_1
    //   240: invokevirtual 1850	com/jrummy/root/browserfree/FileInfoHolder:isScript	()Z
    //   243: ifeq +14 -> 257
    //   246: aload_0
    //   247: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   250: ldc_w 1851
    //   253: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   256: areturn
    //   257: aload_1
    //   258: invokevirtual 1854	com/jrummy/root/browserfree/FileInfoHolder:isAudio	()Z
    //   261: ifeq +14 -> 275
    //   264: aload_0
    //   265: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   268: ldc_w 1855
    //   271: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   274: areturn
    //   275: aload_1
    //   276: invokevirtual 1858	com/jrummy/root/browserfree/FileInfoHolder:isVideo	()Z
    //   279: ifeq +42 -> 321
    //   282: iload_2
    //   283: ifne +27 -> 310
    //   286: getstatic 499	com/jrummy/root/browserfree/FileBrowser:SDK_INT	I
    //   289: bipush 8
    //   291: if_icmplt +19 -> 310
    //   294: new 1839	android/graphics/drawable/BitmapDrawable
    //   297: dup
    //   298: aload_1
    //   299: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   302: iconst_3
    //   303: invokestatic 1864	android/media/ThumbnailUtils:createVideoThumbnail	(Ljava/lang/String;I)Landroid/graphics/Bitmap;
    //   306: invokespecial 1842	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   309: areturn
    //   310: aload_0
    //   311: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   314: ldc_w 1865
    //   317: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   320: areturn
    //   321: aload_1
    //   322: invokevirtual 1868	com/jrummy/root/browserfree/FileInfoHolder:isProp	()Z
    //   325: ifeq +14 -> 339
    //   328: aload_0
    //   329: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   332: ldc_w 1869
    //   335: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   338: areturn
    //   339: aload_1
    //   340: invokevirtual 1872	com/jrummy/root/browserfree/FileInfoHolder:isDatabase	()Z
    //   343: ifeq +14 -> 357
    //   346: aload_0
    //   347: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   350: ldc_w 1873
    //   353: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   356: areturn
    //   357: aload_1
    //   358: invokevirtual 1876	com/jrummy/root/browserfree/FileInfoHolder:isWebpage	()Z
    //   361: ifeq +14 -> 375
    //   364: aload_0
    //   365: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   368: ldc_w 1877
    //   371: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   374: areturn
    //   375: aload_1
    //   376: invokevirtual 1880	com/jrummy/root/browserfree/FileInfoHolder:isJar	()Z
    //   379: ifeq +14 -> 393
    //   382: aload_0
    //   383: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   386: ldc_w 1881
    //   389: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   392: areturn
    //   393: aload_1
    //   394: invokevirtual 1884	com/jrummy/root/browserfree/FileInfoHolder:isFont	()Z
    //   397: ifeq +14 -> 411
    //   400: aload_0
    //   401: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   404: ldc_w 1885
    //   407: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   410: areturn
    //   411: aload_1
    //   412: invokevirtual 1888	com/jrummy/root/browserfree/FileInfoHolder:isCsv	()Z
    //   415: ifeq +14 -> 429
    //   418: aload_0
    //   419: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   422: ldc_w 1889
    //   425: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   428: areturn
    //   429: aload_1
    //   430: invokevirtual 1892	com/jrummy/root/browserfree/FileInfoHolder:isApk	()Z
    //   433: ifeq +138 -> 571
    //   436: iload_2
    //   437: ifne +123 -> 560
    //   440: aload_0
    //   441: invokevirtual 1896	com/jrummy/root/browserfree/FileBrowser:getPackageManager	()Landroid/content/pm/PackageManager;
    //   444: astore 6
    //   446: aload 6
    //   448: new 488	java/io/File
    //   451: dup
    //   452: aload_1
    //   453: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   456: invokespecial 962	java/io/File:<init>	(Ljava/lang/String;)V
    //   459: invokevirtual 492	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   462: iconst_0
    //   463: invokevirtual 1902	android/content/pm/PackageManager:getPackageArchiveInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   466: astore 5
    //   468: aload 5
    //   470: ifnull +90 -> 560
    //   473: aload 5
    //   475: getfield 1908	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   478: astore 7
    //   480: aload_1
    //   481: aload 5
    //   483: getfield 1911	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   486: invokevirtual 1914	com/jrummy/root/browserfree/FileInfoHolder:setPackageName	(Ljava/lang/String;)V
    //   489: getstatic 499	com/jrummy/root/browserfree/FileBrowser:SDK_INT	I
    //   492: bipush 8
    //   494: if_icmplt +21 -> 515
    //   497: aload 7
    //   499: aload_1
    //   500: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   503: putfield 1919	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   506: aload 7
    //   508: aload_1
    //   509: invokevirtual 1803	com/jrummy/root/browserfree/FileInfoHolder:getFilePath	()Ljava/lang/String;
    //   512: putfield 1922	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   515: aload 7
    //   517: ifnull +43 -> 560
    //   520: aload 7
    //   522: aload 6
    //   524: invokevirtual 1926	android/content/pm/ApplicationInfo:loadIcon	(Landroid/content/pm/PackageManager;)Landroid/graphics/drawable/Drawable;
    //   527: astore_1
    //   528: aload_1
    //   529: areturn
    //   530: astore_1
    //   531: ldc_w 328
    //   534: new 979	java/lang/StringBuilder
    //   537: dup
    //   538: ldc_w 1928
    //   541: invokespecial 982	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   544: aload 5
    //   546: getfield 1911	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   549: invokevirtual 986	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: invokevirtual 991	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: aload_1
    //   556: invokestatic 1229	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   559: pop
    //   560: aload_0
    //   561: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   564: ldc_w 1929
    //   567: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   570: areturn
    //   571: aload_0
    //   572: getfield 693	com/jrummy/root/browserfree/FileBrowser:res	Landroid/content/res/Resources;
    //   575: ldc_w 918
    //   578: invokevirtual 850	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   581: areturn
    //   582: astore_1
    //   583: goto -373 -> 210
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	586	0	this	FileBrowser
    //   0	586	1	paramFileInfoHolder	FileInfoHolder
    //   0	586	2	paramBoolean	boolean
    //   135	38	3	i	int
    //   151	51	4	j	int
    //   102	443	5	localObject	Object
    //   444	79	6	localPackageManager	PackageManager
    //   478	43	7	localApplicationInfo	ApplicationInfo
    // Exception table:
    //   from	to	target	type
    //   95	153	209	java/lang/OutOfMemoryError
    //   170	176	209	java/lang/OutOfMemoryError
    //   176	199	209	java/lang/OutOfMemoryError
    //   199	206	209	java/lang/OutOfMemoryError
    //   520	528	530	java/lang/OutOfMemoryError
    //   95	153	582	java/lang/Exception
    //   170	176	582	java/lang/Exception
    //   176	199	582	java/lang/Exception
    //   199	206	582	java/lang/Exception
  }
  
  public Drawable getIconFromApkInsideZip(ZipFile paramZipFile, ZipEntry paramZipEntry)
  {
    Object localObject = null;
    for (;;)
    {
      try
      {
        paramZipFile = new ZipInputStream(paramZipFile.getInputStream(paramZipEntry));
        paramZipEntry = paramZipFile.getNextEntry();
        if (paramZipEntry != null) {
          continue;
        }
        paramZipFile = localObject;
      }
      catch (FileNotFoundException paramZipFile)
      {
        Log.e("FileBrowser", "Extracting file: Error opening zip file - FileNotFoundException: ", paramZipFile);
        paramZipFile.printStackTrace();
        paramZipFile = localObject;
        continue;
      }
      catch (IOException paramZipFile)
      {
        Log.e("FileBrowser", "Extracting file: Error opening zip file - IOException: ", paramZipFile);
        paramZipFile.printStackTrace();
        paramZipFile = localObject;
        continue;
      }
      if (paramZipFile != null) {
        break;
      }
      return this.res.getDrawable(2130837605);
      if (paramZipEntry != null)
      {
        paramZipEntry = paramZipEntry.getName().split("/");
        if ((paramZipEntry != null) && (paramZipEntry.length == 3))
        {
          paramZipEntry = paramZipEntry[2];
          if ((paramZipEntry.endsWith(".png")) && ((paramZipEntry.startsWith("ic_launcher_")) || (paramZipEntry.startsWith("app_icon")) || (paramZipEntry.startsWith("appicon")) || (paramZipEntry.equals("icon.png")) || (paramZipEntry.startsWith("launchericon")) || (paramZipEntry.startsWith("launcher_icon")) || (paramZipEntry.startsWith("ic_main")) || (paramZipEntry.startsWith("ic_logo")) || (paramZipEntry.endsWith("appicon.png")) || (paramZipEntry.startsWith("default_icon")) || (paramZipEntry.startsWith("main_icon")) || (paramZipEntry.startsWith("bt_share")))) {
            paramZipFile = BitmapFactory.decodeStream(paramZipFile);
          }
        }
      }
    }
    return new BitmapDrawable(paramZipFile);
  }
  
  void hideProgressHorizontal()
  {
    mBtnHidePbar.setVisibility(8);
    mPbarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968579));
    mPbarLayout.setVisibility(8);
    mPbarImage.setBackgroundDrawable(this.res.getDrawable(2130837565));
    mPbarTitleImg.setBackgroundDrawable(this.res.getDrawable(2130837565));
    mActionBar.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968588));
    mActionBar.setVisibility(0);
  }
  
  public void installApk(File paramFile)
  {
    int i = 0;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
    if (0 == 0) {
      if (getPackageManager().queryIntentActivities(localIntent, 0).size() <= 0) {
        break label58;
      }
    }
    label58:
    for (i = 1; i != 0; i = 0)
    {
      startActivity(localIntent);
      return;
    }
    Log.d("FileBrowser", "No activity found to restore the apks.");
  }
  
  public void listZipFile(String paramString1, String paramString2)
  {
    new GetIconsInZip(null).cancel(true);
    mZipFile = paramString1;
    mLastZipFolder = paramString2;
    isRootDir = false;
    int j = 0;
    int i = 0;
    for (;;)
    {
      if (i >= paramString2.length()) {}
      try
      {
        ZipFile localZipFile = new ZipFile(paramString1);
        Enumeration localEnumeration = localZipFile.entries();
        mFiles.clear();
        mAllFiles.clear();
        for (;;)
        {
          bool1 = localEnumeration.hasMoreElements();
          if (!bool1)
          {
            sortFiles(mSortType);
            isExploringZip = true;
            mAdapter.notifyDataSetChanged();
            if (paramString2.equals("")) {
              setZipExplorerView(true);
            }
            new GetIconsInZip(null).execute((FileInfoHolder[])mAllFiles.toArray(new FileInfoHolder[0]));
            if (!mFiles.isEmpty()) {
              break label868;
            }
            mEmptyList.setVisibility(0);
            return;
            k = j;
            if (paramString2.charAt(i) == '/') {
              k = j + 1;
            }
            i += 1;
            j = k;
            break;
          }
          ZipEntry localZipEntry = (ZipEntry)localEnumeration.nextElement();
          bool2 = localZipEntry.isDirectory();
          localObject3 = localZipEntry.getName();
          m = 0;
          i = 0;
          n = 0;
          if (!paramString2.equals("")) {
            break label600;
          }
          localObject1 = localObject3;
          k = 0;
          label247:
          if (k < ((String)localObject3).length()) {
            break label577;
          }
          m = i;
          if ((!bool2) || (m != 1)) {
            break label910;
          }
          paramString1 = ((String)localObject1).replace("/", "");
          i = 1;
          localObject2 = localObject3;
          bool1 = bool2;
          label293:
          if (i != 0)
          {
            localObject1 = getSizeStr(localZipEntry.getSize());
            long l = localZipEntry.getTime();
            str = new SimpleDateFormat("dd").format(Long.valueOf(l));
            localObject3 = new SimpleDateFormat("yyyy").format(Long.valueOf(l));
            localObject4 = new SimpleDateFormat("HH:mm:ss").format(Long.valueOf(l));
            Object localObject5 = Calendar.getInstance();
            localObject5 = monthName[localObject5.get(2)];
            str = str + " " + (String)localObject5 + " " + (String)localObject3 + " " + (String)localObject4;
            localObject3 = new FileInfoHolder();
            ((FileInfoHolder)localObject3).setFilePath(localObject2);
            ((FileInfoHolder)localObject3).setFileName(paramString1);
            ((FileInfoHolder)localObject3).setSize((String)localObject1);
            ((FileInfoHolder)localObject3).setIsDirectory(bool1);
            ((FileInfoHolder)localObject3).setDate(str);
            setFileType((FileInfoHolder)localObject3);
            ((FileInfoHolder)localObject3).setIcon(getFileIcon((FileInfoHolder)localObject3, true));
            if (((FileInfoHolder)localObject3).isApk())
            {
              ((FileInfoHolder)localObject3).setZipEntry(localZipEntry);
              ((FileInfoHolder)localObject3).setZipFile(localZipFile);
            }
            mFiles.add(localObject3);
            mAllFiles.add(localObject3);
          }
        }
      }
      catch (IOException paramString1)
      {
        for (;;)
        {
          int k;
          boolean bool2;
          Object localObject3;
          int n;
          Object localObject1;
          String str;
          Object localObject4;
          Log.i("FileBrowser", "Error opening zip file" + paramString1);
          continue;
          label577:
          int m = i;
          label600:
          label630:
          label667:
          label770:
          if (((String)localObject3).charAt(k) == '/')
          {
            m = i + 1;
            break label886;
            if (!((String)localObject3).startsWith(paramString2)) {
              continue;
            }
            paramString1 = ((String)localObject3).substring(paramString2.length(), ((String)localObject3).length());
            k = 0;
            i = m;
            localObject1 = paramString1;
            m = i;
            if (k >= paramString1.length()) {
              continue;
            }
            m = i;
            if (paramString1.charAt(k) != '/') {
              break label898;
            }
            m = i + 1;
            break label898;
            bool1 = bool2;
            i = n;
            paramString1 = (String)localObject1;
            localObject2 = localObject3;
            if (bool2) {
              continue;
            }
            bool1 = bool2;
            i = n;
            paramString1 = (String)localObject1;
            localObject2 = localObject3;
            if (m < 1) {
              continue;
            }
            localObject4 = ((String)localObject3).split("/");
            bool1 = bool2;
            i = n;
            paramString1 = (String)localObject1;
            localObject2 = localObject3;
            if (localObject4 == null) {
              continue;
            }
            k = localObject4.length;
            bool1 = bool2;
            i = n;
            paramString1 = (String)localObject1;
            localObject2 = localObject3;
            if (k < j) {
              continue;
            }
            str = "";
            i = 0;
            break label936;
          }
          for (;;)
          {
            k = 1;
            paramString1 = mAllFiles.iterator();
            do
            {
              if (!paramString1.hasNext())
              {
                break label945;
                if (i > j) {
                  break;
                }
                str = str + localObject4[i] + "/";
                i += 1;
                break label936;
              }
              bool1 = ((FileInfoHolder)paramString1.next()).getFilePath().equals(str);
            } while (!bool1);
            k = 0;
            break label945;
            label868:
            if (mEmptyList.getVisibility() != 0) {
              break;
            }
            mEmptyList.setVisibility(8);
            return;
            label886:
            k += 1;
            i = m;
            break label247;
            label898:
            k += 1;
            i = m;
            break label630;
            label910:
            if ((bool2) || (m != 0)) {
              break label667;
            }
            i = 1;
            bool1 = bool2;
            paramString1 = (String)localObject1;
            localObject2 = localObject3;
            break label293;
            label936:
            if (i < k) {
              break label770;
            }
          }
          label945:
          boolean bool1 = bool2;
          i = n;
          paramString1 = (String)localObject1;
          Object localObject2 = localObject3;
          if (k != 0)
          {
            bool1 = true;
            i = 1;
            paramString1 = localObject4[j];
            localObject2 = str;
          }
        }
      }
    }
  }
  
  public void onAdClick(View paramView)
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + mAdPackage)));
  }
  
  public void onAddFile(View paramView)
  {
    QuickActionBar localQuickActionBar = new QuickActionBar(this);
    ActionItem localActionItem = new ActionItem();
    localActionItem.setTitle(getString(2131230911));
    localActionItem.setIcon(this.res.getDrawable(2130837610));
    localQuickActionBar.addActionItem(localActionItem);
    localActionItem.setTitle(getString(2131230910));
    localActionItem.setIcon(this.res.getDrawable(2130837542));
    localQuickActionBar.addActionItem(localActionItem);
    localQuickActionBar.setOnActionItemClickListener(new QuickActionBar.OnActionItemClickListener()
    {
      public void onItemClick(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 0) {}
        for (boolean bool = true;; bool = false)
        {
          FileBrowser.this.addFileDialog(bool);
          return;
        }
      }
    });
    localQuickActionBar.show(paramView);
  }
  
  public void onBackPressed()
  {
    if (mIsSearchBarVisible) {
      showSearchBar(false);
    }
    do
    {
      do
      {
        return;
        if ((isExploringZip) && (!mLastZipFolder.equals("")))
        {
          Object localObject = new File(mLastZipFolder);
          if (((File)localObject).getParent() == null) {}
          for (localObject = "";; localObject = ((File)localObject).getParent() + "/")
          {
            listZipFile(mZipFile, (String)localObject);
            return;
          }
        }
        if (isInSelectionMode)
        {
          showSelectionMode(false);
          return;
        }
        if (isRootDir) {
          break;
        }
        goBackAFolder();
      } while ((!mScrollToSamePosition) || (mScrollPositions.get(mPath.getAbsolutePath()) == null));
      final int i = ((Integer)mScrollPositions.get(mPath.getAbsolutePath())).intValue();
      mListView.clearFocus();
      mListView.post(new Runnable()
      {
        public void run()
        {
          FileBrowser.mListView.setSelection(i);
        }
      });
      return;
      if (mEmptyList.getVisibility() == 0)
      {
        UpdateList("/", true);
        return;
      }
    } while ((mPbarSpinner.getVisibility() == 0) && (mPbarHorz.getVisibility() == 0));
    if (!mDoExit)
    {
      Helpers.sendMsg(getApplicationContext(), getString(2131230856));
      mDoExit = true;
      return;
    }
    finish();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
    case 2131361895: 
    case 2131361897: 
    case 2131361899: 
    case 2131361901: 
    case 2131361903: 
    case 2131361905: 
    case 2131361907: 
    case 2131361909: 
    case 2131361911: 
    case 2131361914: 
      do
      {
        return;
        mDoBatch = true;
        mSelectedFiles = getSelected(mFiles);
        showSelectionMode(false);
        showOperationMode("do_copy", true);
        return;
        mDoBatch = true;
        mSelectedFiles = getSelected(mFiles);
        showSelectionMode(false);
        showOperationMode("do_move", true);
        return;
        if (preferences.getBoolean("confirm_delete_files", true))
        {
          showDialog(40);
          return;
        }
        mDoBatch = true;
        mSelectedFiles = getSelected(mFiles);
        showSelectionMode(false);
        doCmd("do_delete");
        return;
        mDoBatch = true;
        mSelectedFiles = getSelected(mFiles);
        showSelectionMode(false);
        showOperationMode("do_zip_file", true);
        return;
        mDoBatch = true;
        mSelectedFiles = getSelected(mFiles);
        showSelectionMode(false);
        showOperationMode("do_tar", true);
        return;
        mDoBatch = true;
        mSelectedFiles = getSelected(mFiles);
        showSelectionMode(false);
        showDialog(3);
        return;
        showSelectionMode(false);
        return;
        paramView = mAllFiles.iterator();
        for (;;)
        {
          if (!paramView.hasNext())
          {
            mAdapter.notifyDataSetChanged();
            return;
          }
          localObject = (FileInfoHolder)paramView.next();
          if (!((FileInfoHolder)localObject).getFileName().equals("..")) {
            ((FileInfoHolder)localObject).setIsChecked(true);
          }
        }
        paramView = mAllFiles.iterator();
        for (;;)
        {
          if (!paramView.hasNext())
          {
            mAdapter.notifyDataSetChanged();
            return;
          }
          ((FileInfoHolder)paramView.next()).setIsChecked(false);
        }
        int i;
        File localFile;
        if (mCmd.equals("do_copy"))
        {
          i = 0;
          paramView = mSelectedFiles.iterator();
          if (!paramView.hasNext()) {}
          for (;;)
          {
            if (i == 0) {
              break label496;
            }
            mCmd = "do_copy";
            showDialog(37);
            return;
            localObject = (FileInfoHolder)paramView.next();
            localFile = new File(mPath.getAbsolutePath(), ((FileInfoHolder)localObject).getFileName());
            if ((!localFile.exists()) || (((FileInfoHolder)localObject).getFilePath().equals(localFile.getAbsolutePath()))) {
              break;
            }
            i = 1;
          }
          doCmd("do_copy");
          return;
        }
        if (mCmd.equals("do_extract"))
        {
          if (mZipFilesToExtract != null)
          {
            showOperationMode("", false);
            extractFromInsideZip(mZipFile, mPath.getAbsolutePath(), mZipFilesToExtract);
            return;
          }
          mExtractLocation = mPath.toString();
          doCmd("do_extract");
          return;
        }
        if (mCmd.equals("do_move"))
        {
          i = 0;
          paramView = mSelectedFiles.iterator();
          if (!paramView.hasNext()) {}
          for (;;)
          {
            if (i == 0) {
              break label665;
            }
            mCmd = "do_move";
            showDialog(37);
            return;
            localObject = (FileInfoHolder)paramView.next();
            localFile = new File(mPath.getAbsolutePath(), ((FileInfoHolder)localObject).getFileName());
            if ((!localFile.exists()) || (((FileInfoHolder)localObject).getFilePath().equals(localFile.getAbsolutePath()))) {
              break;
            }
            i = 1;
          }
          doCmd("do_move");
          return;
        }
        if (mCmd.equals("do_zip_file"))
        {
          if (mPath.canWrite())
          {
            paramView = new File[mSelectedFiles.size()];
            i = 0;
            for (;;)
            {
              if (i >= mSelectedFiles.size())
              {
                multiZipDialog(mPath.getAbsolutePath(), paramView);
                return;
              }
              paramView[i] = new File(((FileInfoHolder)mSelectedFiles.get(i)).getFilePath());
              i += 1;
            }
          }
          showDialog(38);
          return;
        }
        if (mCmd.equals("do_tar"))
        {
          mZipLocation = mPath;
          doCmd("do_tar");
          return;
        }
      } while (!mCmd.equals("do_symlink"));
      showOperationMode("", false);
      paramView = mLinkFromFile.substring(mLinkFromFile.lastIndexOf("/") + 1);
      localObject = new File(mPath, paramView);
      paramView = (View)localObject;
      if (((File)localObject).exists()) {
        paramView = copyFile(((File)localObject).getAbsolutePath(), "Link");
      }
      createSymlink(mLinkFromFile, paramView.getAbsolutePath());
      return;
    case 2131361857: 
      label496:
      label665:
      mZipFilesToExtract = null;
      mDoBatch = false;
      showOperationMode("", false);
      return;
    }
    paramView = (LinearLayout)findViewById(2131361863);
    Object localObject = (LinearLayout)findViewById(2131361867);
    if (((LinearLayout)localObject).getVisibility() == 0)
    {
      mBtnHidePbar.setText(getString(2131230741));
      mPbarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968587));
      mPbarTitleLayout.setVisibility(8);
      paramView.setVisibility(8);
      ((LinearLayout)localObject).setVisibility(8);
      return;
    }
    mBtnHidePbar.setText(getString(2131230826));
    mPbarTitleLayout.setVisibility(0);
    paramView.setVisibility(0);
    ((LinearLayout)localObject).setVisibility(0);
    mPbarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968586));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    mIsRootUser = preferences.getBoolean("is_root_user", false);
    mShowInfo = preferences.getBoolean("show_file_info", true);
    Object localObject2 = preferences;
    Object localObject1;
    if ((mIsRootUser) || (!Helpers.isSdPresent())) {
      localObject1 = "/";
    }
    for (;;)
    {
      mHomeFolder = ((SharedPreferences)localObject2).getString("home_folder", (String)localObject1);
      mShowNotifications = preferences.getBoolean("fb_show_notifications", true);
      mShowHidden = preferences.getBoolean("fb_show_hidden", true);
      mFoldersFirst = preferences.getBoolean("fb_folders_first", false);
      mShowFolderSize = preferences.getBoolean("fb_show_folder_size", false);
      mScrollToSamePosition = preferences.getBoolean("scroll_to_same_position", true);
      mTheme = preferences.getInt("theme", 2);
      mUseFonts = preferences.getBoolean("use_custom_fonts", true);
      super.onCreate(paramBundle);
      setContentView(2130903051);
      this.res = getResources();
      mStatFs = new StatFs(SD);
      mDatabaseHelper = new DatabaseHelper(this);
      mAdapter = new ListAdapter(getApplicationContext());
      mFiles = new ArrayList();
      mAllFiles = new ArrayList();
      mSelectedFiles = new ArrayList();
      mScrollPositions = new HashMap();
      mBtnDoOperation = (Button)findViewById(2131361914);
      mBtnCancelOperation = (Button)findViewById(2131361857);
      mBtnHidePbar = (Button)findViewById(2131361870);
      mSearchBar = (EditText)findViewById(2131361871);
      mActionBarHome = (ImageButton)findViewById(2131361797);
      mActionBarSearch = (ImageButton)findViewById(2131361874);
      mPbarTitleImg = (ImageView)findViewById(2131361861);
      mPbarImage = (ImageView)findViewById(2131361868);
      mColorBar = (LinearColorBar)findViewById(2131361888);
      mColorBarLayout = (LinearLayout)findViewById(2131361881);
      mActionBar = (LinearLayout)findViewById(2131361794);
      mPbarLayout = (LinearLayout)findViewById(2131361795);
      mEmptyList = (LinearLayout)findViewById(2131361882);
      alertView = (LinearLayout)findViewById(2131361885);
      mBtnCopy = (LinearLayout)findViewById(2131361895);
      mBtnMove = (LinearLayout)findViewById(2131361897);
      mBtnDelete = (LinearLayout)findViewById(2131361899);
      mBtnZip = (LinearLayout)findViewById(2131361901);
      mBtnTar = (LinearLayout)findViewById(2131361903);
      mBtnSend = (LinearLayout)findViewById(2131361905);
      mBtnCancel = (LinearLayout)findViewById(2131361907);
      mBtnSelect = (LinearLayout)findViewById(2131361909);
      mBtnUnSelect = (LinearLayout)findViewById(2131361911);
      mPbarTitleLayout = (LinearLayout)findViewById(2131361860);
      mOperationLayout = (LinearLayout)findViewById(2131361913);
      mDirectoryButtons = (LinearLayout)findViewById(2131361880);
      mListView = (ListView)findViewById(2131361837);
      mPbarSpinner = (ProgressBar)findViewById(2131361796);
      mPbarSpinner2 = (ProgressBar)findViewById(2131361883);
      mPbarHorz = (ProgressBar)findViewById(2131361869);
      mBottomBar = (RelativeLayout)findViewById(2131361892);
      mPbarTitleMsg = (TextView)findViewById(2131361862);
      mPbarCount = (TextView)findViewById(2131361865);
      mPbarPerc = (TextView)findViewById(2131361864);
      mPbarMsg = (TextView)findViewById(2131361866);
      mTitleText = (TextView)findViewById(2131361942);
      mEmptyText = (TextView)findViewById(2131361884);
      mStorageChartLabel = (TextView)findViewById(2131361890);
      mUsedStorageText = (TextView)findViewById(2131361889);
      mFreeStorageText = (TextView)findViewById(2131361891);
      if (mUseFonts)
      {
        MAIN_FONT = Typeface.createFromAsset(getAssets(), "fonts/sonysketch.ttf");
        SUB_FONT = Typeface.createFromAsset(getAssets(), "fonts/default.ttf");
        label828:
        mDoubleTap = new GestureDetector(new DoubleTapHomeDetector());
        gestureListener = new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            return FileBrowser.mDoubleTap.onTouchEvent(paramAnonymousMotionEvent);
          }
        };
        mActionBarHome.setOnTouchListener(gestureListener);
        mActionBarHome.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            FileBrowser.this.finish();
            return false;
          }
        });
        preferences.registerOnSharedPreferenceChangeListener(this);
        mBtnDoOperation.setOnClickListener(this);
        mBtnCancelOperation.setOnClickListener(this);
        mBtnCopy.setOnClickListener(this);
        mBtnMove.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnZip.setOnClickListener(this);
        mBtnTar.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnSelect.setOnClickListener(this);
        mBtnUnSelect.setOnClickListener(this);
        mBtnHidePbar.setOnClickListener(this);
        mTitleText.setText(getString(2131230720));
        mEmptyText.setTypeface(SUB_FONT);
        mSearchBar.setTypeface(SUB_FONT);
        mTitleText.setTypeface(MAIN_FONT);
        mPbarCount.setTypeface(SUB_FONT);
        mPbarPerc.setTypeface(SUB_FONT);
        mPbarMsg.setTypeface(SUB_FONT);
        mPbarTitleMsg.setTypeface(MAIN_FONT);
        mStorageChartLabel.setTypeface(SUB_FONT);
        mUsedStorageText.setTypeface(SUB_FONT);
        mFreeStorageText.setTypeface(SUB_FONT);
        mBtnDoOperation.setTypeface(SUB_FONT);
        mBtnCancelOperation.setTypeface(SUB_FONT);
        mBtnHidePbar.setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361896)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361898)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361900)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361902)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361904)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361906)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361908)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361910)).setTypeface(SUB_FONT);
        ((TextView)findViewById(2131361912)).setTypeface(SUB_FONT);
        mSearchBar.setEnabled(true);
        mSearchBar.addTextChangedListener(this);
        mListView.setTextFilterEnabled(true);
        mListView.setOnItemClickListener(this);
        mListView.setLongClickable(true);
        mListView.setOnItemLongClickListener(this);
        mListView.setDivider(this.res.getDrawable(2130837534));
        mAdapter.setListItems(mFiles);
        mListView.setAdapter(mAdapter);
        if (!preferences.getBoolean("fb_save_sort_type", false)) {
          break label1652;
        }
        mSortType = preferences.getInt("fb_sort_type", 0);
        label1353:
        paramBundle = new Intent("com.koushikdutta.rommanager.api.BIND");
      }
      try
      {
        bindService(paramBundle, new ServiceConnection()
        {
          public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
          {
            FileBrowser.this.mService = IROMManagerAPIService.Stub.asInterface(paramAnonymousIBinder);
            FileBrowser.hasRomManager = true;
          }
          
          public void onServiceDisconnected(ComponentName paramAnonymousComponentName) {}
        }, 1);
        localObject1 = (ImageView)findViewById(2131361800);
        paramBundle = (LinearLayout)findViewById(2131361887);
        if ((new File("/system/etc/.product_of_liberty").exists()) || (new File("/system/etc/.root_browser").exists()))
        {
          mFreebie = true;
          paramBundle.setVisibility(8);
          paramBundle = new RelativeLayout.LayoutParams(-1, -2);
          paramBundle.addRule(12);
          mBottomBar.setLayoutParams(paramBundle);
          mOperationLayout.setLayoutParams(paramBundle);
          mColorBarLayout.setLayoutParams(paramBundle);
          if (!preferences.getBoolean("is_first_load", true)) {
            break label1879;
          }
          paramBundle = preferences.edit();
          paramBundle.putBoolean("is_first_load", false);
          paramBundle.putString("ver", getString(2131230724));
          paramBundle.commit();
          if (!new File("/system/xbin/su").exists()) {
            if (!new File("/system/bin/su").exists()) {
              break label1621;
            }
          }
        }
      }
      catch (Exception paramBundle)
      {
        try
        {
          for (;;)
          {
            paramBundle = getPackageManager().getPackageInfo("com.noshufou.android.su", 0).versionName;
            mHasSuperUser = true;
            Log.i("FileBrowser", "SuperUser version " + paramBundle + " exists");
            label1621:
            showDialog(32);
            return;
            localObject1 = SD;
            break;
            MAIN_FONT = Typeface.defaultFromStyle(0);
            SUB_FONT = Typeface.defaultFromStyle(0);
            break label828;
            label1652:
            mSortType = 0;
            break label1353;
            paramBundle = paramBundle;
            hasRomManager = false;
          }
          if (new File("/system/etc/hosts").length() > 500L)
          {
            boolean bool = preferences.getBoolean("toolbox_ad", false);
            if (bool)
            {
              ((ImageView)localObject1).setImageResource(2130837644);
              mAdPackage = "com.jrummy.liberty.toolboxpro";
              label1717:
              paramBundle = preferences.edit();
              if (!bool) {
                break label1767;
              }
            }
            label1767:
            for (bool = false;; bool = true)
            {
              paramBundle.putBoolean("toolbox_ad", bool);
              paramBundle.commit();
              ((ImageView)localObject1).setVisibility(0);
              break;
              mAdPackage = "com.jrummy.root.browser";
              break label1717;
            }
          }
          paramBundle.setVisibility(0);
          ((ImageView)localObject1).setVisibility(8);
          localObject1 = new AdView(this, AdSize.BANNER, "a14ea49069f398e");
          localObject2 = new AdRequest();
          paramBundle.addView((View)localObject1);
          ((AdView)localObject1).loadAd((AdRequest)localObject2);
          Log.e("FileBrowser", "ready: " + ((AdView)localObject1).isReady() + ", ref: " + ((AdView)localObject1).isRefreshing());
        }
        catch (PackageManager.NameNotFoundException paramBundle)
        {
          for (;;)
          {
            mHasSuperUser = false;
            Log.i("FileBrowser", "su exists but superuser app is missing");
          }
        }
        label1879:
        if (isNewerVersion())
        {
          paramBundle = getString(2131230724);
          localObject1 = preferences.edit();
          ((SharedPreferences.Editor)localObject1).putString("ver", paramBundle);
          ((SharedPreferences.Editor)localObject1).commit();
          showDialog(33);
          return;
        }
        paramBundle = preferences.getString("folder_shortcut_path", null);
        if (paramBundle == null) {
          break label1985;
        }
      }
    }
    mHomeFolder = paramBundle;
    paramBundle = preferences.edit();
    paramBundle.putString("folder_shortcut_path", null);
    paramBundle.commit();
    for (;;)
    {
      UpdateList(mHomeFolder, true);
      return;
      label1985:
      paramBundle = getIntent().getAction();
      if ((paramBundle != null) && (paramBundle.equals("android.intent.action.GET_CONTENT")))
      {
        pickFile = true;
        mHomeFolder = SD;
      }
    }
  }
  
  protected Dialog onCreateDialog(final int paramInt)
  {
    final int j;
    label303:
    label324:
    Object localObject5;
    label355:
    Object localObject7;
    Object localObject6;
    label555:
    label562:
    label824:
    label847:
    label924:
    label1096:
    label1151:
    label1668:
    label1685:
    label1824:
    label1831:
    label1838:
    int m;
    int k;
    label1953:
    label1991:
    final boolean bool1;
    int n;
    label2020:
    boolean bool2;
    int i1;
    switch (paramInt)
    {
    case 4: 
    case 8: 
    case 10: 
    case 11: 
    case 12: 
    case 13: 
    case 14: 
    case 15: 
    case 16: 
    case 17: 
    case 21: 
    case 24: 
    case 26: 
    case 27: 
    case 28: 
    case 29: 
    case 30: 
    case 31: 
    default: 
      return null;
    case 0: 
    case 1: 
      if (paramInt == 0)
      {
        localObject1 = "android.intent.action.VIEW";
        localObject3 = mFile.getMimeType();
        localObject2 = getPackageManager();
        localObject1 = new Intent((String)localObject1);
        ((Intent)localObject1).setDataAndType(Uri.parse("file://" + mFile.getFilePath()), (String)localObject3);
        localObject3 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject1, 0);
        j = ((List)localObject3).size();
        if (j != 0) {
          break label324;
        }
        if (paramInt != 0) {
          break label303;
        }
        startActivity(new Intent(getApplicationContext(), ViewFile.class));
      }
      for (;;)
      {
        return null;
        localObject1 = "android.intent.action.EDIT";
        break;
        startActivity(new Intent(getApplicationContext(), EditFile.class));
      }
      localObject4 = new String[j + 1];
      localObject5 = new Drawable[j + 1];
      i = 0;
      if (i >= j)
      {
        if (paramInt != 0) {
          break label555;
        }
        i = 2131230736;
        localObject4[j] = getString(i);
        localObject2 = this.res;
        if (paramInt != 0) {
          break label562;
        }
      }
      for (i = 2130837588;; i = 2130837535)
      {
        localObject5[j] = ((Resources)localObject2).getDrawable(i);
        new PopupDialog.Builder(this).setCancelable(false).setIcon(this.res.getDrawable(2130837535)).setCancelable(true).setTitle(getString(2131230753, new Object[] { mFile.getFileName() })).setMessage(getString(2131230776, new Object[] { mFile.getFileName() })).onBackPressed(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            FileBrowser.this.removeDialog(paramInt);
          }
        }).setItems((String[])localObject4, (Drawable[])localObject5, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            FileBrowser.this.removeDialog(paramInt);
            if (paramAnonymousInt == j) {
              if (paramInt == 0) {
                FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), ViewFile.class));
              }
            }
            for (;;)
            {
              return;
              FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), EditFile.class));
              return;
              int i = 0;
              while (i < j)
              {
                if (paramAnonymousInt == i)
                {
                  paramAnonymousDialogInterface = (ResolveInfo)this.val$activities.get(i);
                  String str = paramAnonymousDialogInterface.activityInfo.packageName;
                  this.val$intent.setClassName(str, paramAnonymousDialogInterface.activityInfo.name);
                  FileBrowser.this.startActivity(this.val$intent);
                  return;
                }
                i += 1;
              }
            }
          }
        }).create();
        localObject7 = (ResolveInfo)((List)localObject3).get(i);
        localObject6 = ((ResolveInfo)localObject7).loadLabel((PackageManager)localObject2).toString();
        localObject7 = ((ResolveInfo)localObject7).loadIcon((PackageManager)localObject2);
        localObject4[i] = localObject6;
        localObject5[i] = localObject7;
        i += 1;
        break;
        i = 2131230737;
        break label355;
      }
    case 2: 
      localObject3 = getMD5Sum(mFile.getFilePath());
      localObject2 = ((BitmapDrawable)mFile.getIcon()).getBitmap();
      localObject1 = mFile.getPermissions();
      localObject2 = new PopupDialog.Builder(this).setCancelable(false).setIcon(new BitmapDrawable(Reflection.main((Bitmap)localObject2))).setCancelable(true).setTitle(getString(2131230754, new Object[] { mFile.getFileName() }));
      localObject4 = mFile.getSize();
      localObject5 = mFile.getDate();
      if (localObject1 == null)
      {
        localObject1 = "N/A";
        localObject4 = new StringBuilder(String.valueOf(getString(2131230777, new Object[] { localObject4, localObject5, localObject1, mFile.getFilePath() })));
        if (!mIsRootUser) {
          break label847;
        }
        if (localObject3 != null) {
          break label824;
        }
        localObject1 = "";
      }
      for (;;)
      {
        ((PopupDialog.Builder)localObject2).setMessage((String)localObject1).onBackPressed(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            FileBrowser.this.removeDialog(paramInt);
          }
        }).create();
        localObject1 = getPermStr((String)localObject1) + " (" + permissionMode(getPermStr((String)localObject1)) + ")";
        break;
        localObject1 = "\n\nmd5sum: " + (String)localObject3;
        continue;
        localObject1 = "";
      }
    case 3: 
      if (mDoBatch)
      {
        localObject2 = "file/*";
        localObject1 = new Intent("android.intent.action.SEND_MULTIPLE");
        ((Intent)localObject1).setType((String)localObject2);
        localObject2 = getPackageManager();
        localObject3 = ((PackageManager)localObject2).queryIntentActivities((Intent)localObject1, 0);
        i = ((List)localObject3).size();
        localObject4 = new String[i];
        localObject5 = new Drawable[i];
        paramInt = 0;
        if (paramInt < i) {
          break label1096;
        }
        localObject6 = new PopupDialog.Builder(this).setCancelable(false).setIcon(this.res.getDrawable(2130837600)).setCancelable(true).setTitle(getString(2131230755));
        if (!mDoBatch) {
          break label1151;
        }
      }
      for (localObject2 = getString(2131230757);; localObject2 = getString(2131230756, new Object[] { mFile.getFileName() }))
      {
        ((PopupDialog.Builder)localObject6).setMessage((String)localObject2).onBackPressed(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            FileBrowser.this.removeDialog(3);
          }
        }).setItems((String[])localObject4, (Drawable[])localObject5, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            FileBrowser.this.removeDialog(3);
            int i = 0;
            if (i >= i) {
              return;
            }
            String str;
            Object localObject;
            Iterator localIterator;
            if (paramAnonymousInt == i)
            {
              paramAnonymousDialogInterface = (ResolveInfo)this.val$act.get(i);
              str = paramAnonymousDialogInterface.activityInfo.packageName;
              if (!FileBrowser.mDoBatch) {
                break label221;
              }
              this.val$intent.putExtra("android.intent.extra.TEXT", FileBrowser.this.getString(2131230779, new Object[] { Build.MODEL.toLowerCase(), FileBrowser.this.getString(2131230720) }));
              localObject = new ArrayList();
              localIterator = FileBrowser.mSelectedFiles.iterator();
              label116:
              if (localIterator.hasNext()) {
                break label177;
              }
              this.val$intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)localObject);
              FileBrowser.mDoBatch = false;
            }
            for (;;)
            {
              this.val$intent.setClassName(str, paramAnonymousDialogInterface.activityInfo.name);
              FileBrowser.this.startActivity(this.val$intent);
              i += 1;
              break;
              label177:
              FileInfoHolder localFileInfoHolder = (FileInfoHolder)localIterator.next();
              if (localFileInfoHolder.isDirectory()) {
                break label116;
              }
              ((ArrayList)localObject).add(Uri.fromFile(new File(localFileInfoHolder.getFilePath())));
              break label116;
              label221:
              this.val$intent.putExtra("android.intent.extra.SUBJECT", FileBrowser.mFile.getFileName());
              this.val$intent.putExtra("android.intent.extra.TEXT", FileBrowser.this.getString(2131230778, new Object[] { FileBrowser.mFile.getFileName(), FileBrowser.this.getString(2131230720), Build.MODEL.toLowerCase() }));
              localObject = Uri.parse("file://" + FileBrowser.mFile.getFilePath());
              this.val$intent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject);
            }
          }
        }).create();
        if ((!mFile.getFilePath().startsWith("/sdcard")) && (!mFile.getFilePath().startsWith(SD)))
        {
          Helpers.sendMsg(getApplicationContext(), getString(2131230857));
          return null;
        }
        localObject2 = mFile.getMimeType();
        localObject1 = new Intent("android.intent.action.SEND");
        break;
        localObject7 = (ResolveInfo)((List)localObject3).get(paramInt);
        localObject6 = ((ResolveInfo)localObject7).loadLabel((PackageManager)localObject2).toString();
        localObject7 = ((ResolveInfo)localObject7).loadIcon((PackageManager)localObject2);
        localObject4[paramInt] = localObject6;
        localObject5[paramInt] = localObject7;
        paramInt += 1;
        break label924;
      }
    case 5: 
      return new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837565)).setCancelable(false).showSpinner(true).setTitle(mDialogTitle).setMessage("\n" + mDialogMsg).create();
    case 6: 
      localObject1 = getString(2131230897);
      localObject2 = getString(2131230898);
      localObject3 = this.res.getDrawable(2130837541);
      localObject4 = new PopupDialog.Builder(this).setIcon(mFile.getIcon()).setCancelable(true).setTitle(getString(2131230877)).setMessage(getString(2131230780, new Object[] { mFile.getFileName() })).onBackPressed(new DialogInterface.OnDismissListener()
      {
        public void onDismiss(DialogInterface paramAnonymousDialogInterface)
        {
          FileBrowser.this.removeDialog(6);
        }
      });
      localObject5 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FileBrowser.this.removeDialog(6);
          if (paramAnonymousInt == 0)
          {
            FileBrowser.mExtractLocation = FileBrowser.mPath.toString();
            FileBrowser.this.doCmd("do_extract");
            return;
          }
          FileBrowser.this.showOperationMode("do_extract", true);
        }
      };
      return ((PopupDialog.Builder)localObject4).setItems(new String[] { localObject1, localObject2 }, new Drawable[] { localObject3, localObject3 }, (DialogInterface.OnClickListener)localObject5).create();
    case 7: 
      new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837568)).setTitle(getString(2131230817)).setMessage(mDialogMsg).onBackPressed(new DialogInterface.OnDismissListener()
      {
        public void onDismiss(DialogInterface paramAnonymousDialogInterface)
        {
          FileBrowser.this.removeDialog(7);
        }
      }).create();
    case 9: 
      j = SEARCH.size();
      if (j == 0)
      {
        alertBar(getString(2131230727), 5000);
        return null;
      }
      localObject1 = new String[j];
      localObject2 = new Drawable[j];
      i = 0;
      for (;;)
      {
        if (i >= j) {
          new PopupDialog.Builder(this).setCancelable(false).setIcon(this.res.getDrawable(2130837598)).setCancelable(true).setTitle(getString(2131230759)).setMessage(getString(2131230781)).onBackPressed(new DialogInterface.OnDismissListener()
          {
            public void onDismiss(DialogInterface paramAnonymousDialogInterface)
            {
              FileBrowser.this.removeDialog(paramInt);
            }
          }).setItems((String[])localObject1, (Drawable[])localObject2, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
              FileBrowser.this.removeDialog(paramInt);
              File localFile = new File(((String)FileBrowser.SEARCH.get(paramAnonymousInt)).toString());
              paramAnonymousDialogInterface = localFile.getParent();
              if (localFile.isDirectory()) {
                paramAnonymousDialogInterface = localFile.toString();
              }
              FileBrowser.this.UpdateList(paramAnonymousDialogInterface, true);
            }
          }).create();
        }
        localObject3 = ((String)SEARCH.get(i)).toString();
        localObject4 = new File((String)localObject3);
        localObject5 = new FileInfoHolder();
        ((FileInfoHolder)localObject5).setIsDirectory(((File)localObject4).isDirectory());
        ((FileInfoHolder)localObject5).setFilePath((String)localObject3);
        ((FileInfoHolder)localObject5).setFileName(((File)localObject4).getName());
        localObject1[i] = localObject3;
        localObject2[i] = getFileIcon((FileInfoHolder)localObject5, true);
        i += 1;
      }
    case 18: 
    case 19: 
      if (paramInt == 18)
      {
        i = 2130837557;
        if (paramInt != 18) {
          break label1824;
        }
        j = 2131230893;
        localObject2 = getString(j);
        if (paramInt != 18) {
          break label1831;
        }
        j = 2131230895;
        localObject3 = getString(j);
        localObject4 = this.res.getDrawable(i);
        localObject5 = new PopupDialog.Builder(this).setIcon(mFile.getIcon()).setCancelable(true);
        if (paramInt != 18) {
          break label1838;
        }
      }
      for (localObject1 = "Zip";; localObject1 = "Tar")
      {
        localObject1 = ((PopupDialog.Builder)localObject5).setTitle((String)localObject1).setMessage(getString(2131230783)).onBackPressed(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            FileBrowser.this.removeDialog(paramInt);
          }
        });
        localObject5 = new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            FileBrowser.this.removeDialog(paramInt);
            if (paramInt == 18) {}
            for (paramAnonymousDialogInterface = "do_zip_file"; paramAnonymousInt == 0; paramAnonymousDialogInterface = "do_tar")
            {
              FileBrowser.mZipLocation = FileBrowser.mPath;
              FileBrowser.this.doCmd(paramAnonymousDialogInterface);
              return;
            }
            FileBrowser.this.showOperationMode(paramAnonymousDialogInterface, true);
          }
        };
        return ((PopupDialog.Builder)localObject1).setItems(new String[] { localObject2, localObject3 }, new Drawable[] { localObject4, localObject4 }, (DialogInterface.OnClickListener)localObject5).create();
        i = 2130837554;
        break;
        j = 2131230894;
        break label1668;
        j = 2131230896;
        break label1685;
      }
    case 20: 
    case 22: 
      localObject2 = new HashMap();
      localObject1 = new HashMap();
      localObject3 = new HashMap();
      i = 0;
      if ((!mFile.isApk()) && (!mFile.isZip()) && (!mFile.isJar()))
      {
        m = 0;
        if ((!hasRomManager) || ((!mFile.getFilePath().startsWith(SD)) && (!mFile.getFilePath().startsWith("/sdcard"))) || (!mFile.isZip())) {
          break label3764;
        }
        k = 1;
        if ((mFile.isTxt()) || (mFile.isScript()) || (mFile.isWebpage()) || (mFile.isProp())) {
          break label3770;
        }
        j = 0;
        bool1 = mFile.isApk();
        if ((mFile.isZip()) || (mFile.isTar())) {
          break label3775;
        }
        n = 0;
        bool2 = mFile.isScript();
        if (mFile.isDatabase())
        {
          ((Map)localObject2).put(Integer.valueOf(0), getString(2131230902));
          ((Map)localObject3).put(Integer.valueOf(0), mFile.getIcon());
          ((Map)localObject1).put(Integer.valueOf(0), "view_database");
          i = 0 + 1;
        }
        paramInt = i;
        if (bool1)
        {
          ((Map)localObject2).put(Integer.valueOf(i), getString(2131230913));
          ((Map)localObject3).put(Integer.valueOf(i), mFile.getIcon());
          ((Map)localObject1).put(Integer.valueOf(i), "install_apk");
          i1 = i + 1;
          paramInt = i1;
          if (mFile.getPackageName() != null)
          {
            paramInt = i1;
            if (!mFile.getFilePath().startsWith("/system")) {
              i = 0;
            }
          }
        }
      }
      break;
    }
    try
    {
      if (!getPackageManager().getApplicationInfo(mFile.getPackageName(), 0).sourceDir.startsWith("/system")) {
        break label3781;
      }
      paramInt = 0;
      label2217:
      i = paramInt;
      Log.i("FileBrowser", "Package is installed: " + mFile.getPackageName());
      i = paramInt;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException2)
    {
      for (;;) {}
    }
    paramInt = i1;
    if (i != 0)
    {
      ((Map)localObject2).put(Integer.valueOf(i1), getString(2131230738));
      ((Map)localObject3).put(Integer.valueOf(i1), mFile.getIcon());
      ((Map)localObject1).put(Integer.valueOf(i1), "uninstall_apk");
      paramInt = i1 + 1;
    }
    final int i = paramInt;
    if (m != 0)
    {
      ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230774));
      ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837598));
      ((Map)localObject1).put(Integer.valueOf(paramInt), "explore_archive");
      i = paramInt + 1;
    }
    paramInt = i;
    if (n != 0)
    {
      ((Map)localObject2).put(Integer.valueOf(i), getString(2131230877));
      ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837541));
      ((Map)localObject1).put(Integer.valueOf(i), "extract");
      paramInt = i + 1;
    }
    i = paramInt;
    if (k != 0)
    {
      ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230772));
      ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837607));
      ((Map)localObject1).put(Integer.valueOf(paramInt), "flash");
      i = paramInt + 1;
    }
    paramInt = i;
    if (bool2)
    {
      paramInt = i;
      if (mIsRootUser)
      {
        ((Map)localObject2).put(Integer.valueOf(i), getString(2131230739));
        ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837601));
        ((Map)localObject1).put(Integer.valueOf(i), "run_script");
        paramInt = i + 1;
      }
    }
    ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230914));
    ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837585));
    ((Map)localObject1).put(Integer.valueOf(paramInt), "delete");
    paramInt += 1;
    ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230915));
    ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837551));
    ((Map)localObject1).put(Integer.valueOf(paramInt), "rename");
    paramInt += 1;
    ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230879));
    ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837538));
    ((Map)localObject1).put(Integer.valueOf(paramInt), "copy");
    paramInt += 1;
    ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230880));
    ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837547));
    ((Map)localObject1).put(Integer.valueOf(paramInt), "move");
    i = paramInt + 1;
    paramInt = i;
    if (mIsRootUser)
    {
      ((Map)localObject2).put(Integer.valueOf(i), getString(2131230882));
      ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837549));
      ((Map)localObject1).put(Integer.valueOf(i), "permissions");
      paramInt = i + 1;
    }
    ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230884));
    ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837568));
    ((Map)localObject1).put(Integer.valueOf(paramInt), "properties");
    i = paramInt + 1;
    paramInt = i;
    if (!mFile.isDirectory()) {
      if (!mFile.getFilePath().startsWith(SD))
      {
        paramInt = i;
        if (!mFile.getFilePath().startsWith("/sdcard")) {}
      }
      else
      {
        ((Map)localObject2).put(Integer.valueOf(i), getString(2131230740));
        ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837600));
        ((Map)localObject1).put(Integer.valueOf(i), "send");
        paramInt = i + 1;
      }
    }
    i = paramInt;
    if (j != 0)
    {
      ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230885));
      ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837588));
      ((Map)localObject1).put(Integer.valueOf(paramInt), "view_as_text");
      i = paramInt + 1;
    }
    paramInt = i;
    if (j != 0)
    {
      ((Map)localObject2).put(Integer.valueOf(i), getString(2131230886));
      ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837535));
      ((Map)localObject1).put(Integer.valueOf(i), "edit");
      paramInt = i + 1;
    }
    if (mFile.isDirectory())
    {
      i = 2131230887;
      label3209:
      ((Map)localObject2).put(Integer.valueOf(paramInt), getString(i));
      ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837557));
      ((Map)localObject1).put(Integer.valueOf(paramInt), "zip");
      i = paramInt + 1;
      paramInt = i;
      if (mIsRootUser)
      {
        ((Map)localObject2).put(Integer.valueOf(i), getString(2131230888));
        ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837554));
        ((Map)localObject1).put(Integer.valueOf(i), "tar");
        paramInt = i + 1;
      }
      i = paramInt;
      if (mFile.isDirectory())
      {
        ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230889));
        ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837593));
        ((Map)localObject1).put(Integer.valueOf(paramInt), "home");
        paramInt += 1;
        ((Map)localObject2).put(Integer.valueOf(paramInt), getString(2131230890));
        ((Map)localObject3).put(Integer.valueOf(paramInt), this.res.getDrawable(2130837537));
        ((Map)localObject1).put(Integer.valueOf(paramInt), "bookmark");
        i = paramInt + 1;
      }
      if (mIsRootUser)
      {
        ((Map)localObject2).put(Integer.valueOf(i), getString(2131230891));
        ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837592));
        ((Map)localObject1).put(Integer.valueOf(i), "owner");
        i += 1;
        if (!mFile.isDirectory()) {
          break label3793;
        }
        paramInt = 2131230918;
        label3545:
        ((Map)localObject2).put(Integer.valueOf(i), getString(paramInt));
        ((Map)localObject3).put(Integer.valueOf(i), this.res.getDrawable(2130837553));
        ((Map)localObject1).put(Integer.valueOf(i), "symlink");
      }
      i = ((Map)localObject2).size();
      localObject4 = new String[i];
      localObject5 = new Drawable[i];
      paramInt = 0;
    }
    for (;;)
    {
      if (paramInt >= i)
      {
        if (mFile.getIcon() == null) {
          mFile.setIcon(getFileIcon(mFile, true));
        }
        localObject2 = new BitmapDrawable(Reflection.main(((BitmapDrawable)mFile.getIcon()).getBitmap()));
        new PopupDialog.Builder(this).setCancelable(false).setCancelable(true).setIcon((Drawable)localObject2).setTitle(mFile.getFileName()).setMessage(getString(2131230786, new Object[] { mFile.getFileName() })).onBackPressed(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            FileBrowser.this.removeDialog(22);
          }
        }).setItems((String[])localObject4, (Drawable[])localObject5, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            FileBrowser.this.removeDialog(22);
            paramAnonymousDialogInterface = (String)this.val$keys.get(Integer.valueOf(paramAnonymousInt));
            if (paramAnonymousDialogInterface.equals("explore_archive")) {
              FileBrowser.this.listZipFile(FileBrowser.mFile.getFilePath(), "");
            }
            do
            {
              do
              {
                return;
                if (paramAnonymousDialogInterface.equals("delete"))
                {
                  if (FileBrowser.preferences.getBoolean("confirm_delete_files", true))
                  {
                    FileBrowser.this.showDialog(39);
                    return;
                  }
                  FileBrowser.this.doCmd("do_delete");
                  return;
                }
                if (paramAnonymousDialogInterface.equals("rename"))
                {
                  FileBrowser.this.renameDialog();
                  return;
                }
                if (paramAnonymousDialogInterface.equals("copy"))
                {
                  FileBrowser.this.showOperationMode("do_copy", true);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("move"))
                {
                  FileBrowser.this.showOperationMode("do_move", true);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("permissions"))
                {
                  FileBrowser.this.showPermissionsDialog();
                  return;
                }
                if (paramAnonymousDialogInterface.equals("properties"))
                {
                  FileBrowser.this.showDialog(2);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("send"))
                {
                  FileBrowser.this.showDialog(3);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("view_as_text"))
                {
                  FileBrowser.this.showDialog(0);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("edit"))
                {
                  FileBrowser.this.showDialog(1);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("zip"))
                {
                  FileBrowser.this.showDialog(18);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("tar"))
                {
                  FileBrowser.this.showDialog(19);
                  return;
                }
                if (paramAnonymousDialogInterface.equals("home"))
                {
                  paramAnonymousDialogInterface = FileBrowser.preferences.edit();
                  paramAnonymousDialogInterface.putString("home_folder", FileBrowser.mFile.getFilePath());
                  paramAnonymousDialogInterface.commit();
                  Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230852, new Object[] { FileBrowser.mFile.getFilePath() }));
                  return;
                }
                if (paramAnonymousDialogInterface.equals("owner"))
                {
                  FileBrowser.this.showChangeOwnerDialog();
                  return;
                }
                if (paramAnonymousDialogInterface.equals("install_apk"))
                {
                  FileBrowser.this.installApk(new File(FileBrowser.mFile.getFilePath()));
                  return;
                }
                if (!paramAnonymousDialogInterface.equals("uninstall_apk")) {
                  break;
                }
                paramAnonymousDialogInterface = new Intent("android.intent.action.DELETE", Uri.parse("package:" + FileBrowser.mFile.getPackageName()));
              } while (FileBrowser.this.getPackageManager().queryIntentActivities(paramAnonymousDialogInterface, 0).size() <= 0);
              FileBrowser.this.startActivity(paramAnonymousDialogInterface);
              return;
              if (paramAnonymousDialogInterface.equals("extract"))
              {
                FileBrowser.this.showDialog(6);
                return;
              }
              if (paramAnonymousDialogInterface.equals("flash"))
              {
                if (FileBrowser.this.mService == null)
                {
                  FileBrowser.this.alertBar(FileBrowser.this.getString(2131230733), 5000);
                  return;
                }
                String str = FileBrowser.mFile.getFilePath();
                paramAnonymousDialogInterface = str;
                if (str.startsWith("/mnt")) {
                  paramAnonymousDialogInterface = str.substring(4, str.length());
                }
                try
                {
                  FileBrowser.this.mService.installZip(paramAnonymousDialogInterface);
                  return;
                }
                catch (Exception paramAnonymousDialogInterface)
                {
                  FileBrowser.this.alertBar(FileBrowser.this.getString(2131230733), 5000);
                  return;
                }
              }
              if (paramAnonymousDialogInterface.equals("run_script"))
              {
                FileBrowser.this.runScript(FileBrowser.mFile);
                return;
              }
              if (paramAnonymousDialogInterface.equals("bookmark"))
              {
                FileBrowser.mDatabaseHelper.insert(FileBrowser.mFile.getFileName(), FileBrowser.mFile.getFilePath());
                return;
              }
              if (paramAnonymousDialogInterface.equals("view_database"))
              {
                DatabaseViewer.mDatabase = new Database();
                paramAnonymousDialogInterface = new File(FileBrowser.mFile.getFilePath()).getParent();
                DatabaseViewer.mDatabase.setDatabaseDir(paramAnonymousDialogInterface);
                DatabaseViewer.mDatabase.setTitle(FileBrowser.mFile.getFileName());
                DatabaseViewer.mSingleMode = true;
                FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), DatabaseViewer.class));
                return;
              }
            } while (!paramAnonymousDialogInterface.equals("symlink"));
            FileBrowser.mLinkFromFile = FileBrowser.mFile.getFilePath();
            FileBrowser.this.showOperationMode("do_symlink", true);
          }
        }).create();
        m = 1;
        break;
        label3764:
        k = 0;
        break label1953;
        label3770:
        j = 1;
        break label1991;
        label3775:
        n = 1;
        break label2020;
        label3781:
        paramInt = 1;
        break label2217;
        i = 2131230892;
        break label3209;
        label3793:
        paramInt = 2131230917;
        break label3545;
      }
      localObject4[paramInt] = ((String)((Map)localObject2).get(Integer.valueOf(paramInt)));
      localObject5[paramInt] = ((Drawable)((Map)localObject3).get(Integer.valueOf(paramInt)));
      paramInt += 1;
    }
    Object localObject1 = this.res.getDrawable(2130837606);
    Object localObject2 = getPackageManager();
    try
    {
      localObject2 = ((PackageManager)localObject2).getPackageInfo("com.noshufou.android.su", 0).applicationInfo.loadIcon((PackageManager)localObject2);
      localObject1 = localObject2;
      localObject2 = new BitmapDrawable(Reflection.main(((BitmapDrawable)localObject2).getBitmap()));
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException1)
    {
      for (;;) {}
    }
    new PopupDialog.Builder(this).setIcon((Drawable)localObject1).setCancelable(false).setTitle("Root User").setMessage("We have detected that you may have root access.\n\nWould you like to enable the root features in this app?").setPositiveButton(getString(2131230767), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(23);
        paramAnonymousDialogInterface = FileBrowser.preferences.edit();
        if (!Helpers.checkSu())
        {
          paramAnonymousDialogInterface.putBoolean("is_root_user", false);
          FileBrowser.this.alertBar(FileBrowser.this.getString(2131230734), 5000);
        }
        for (;;)
        {
          paramAnonymousDialogInterface.commit();
          FileBrowser.mHomeFolder = FileBrowser.preferences.getString("home_folder", "/");
          FileBrowser.this.UpdateList(FileBrowser.mHomeFolder, true);
          return;
          FileBrowser.mIsRootUser = true;
          paramAnonymousDialogInterface.putBoolean("is_root_user", true);
          FileBrowser.this.transferAsset("busybox", "busybox");
          FileBrowser.this.transferAsset("sqlite3", "sqlite3");
          CMDProcessor localCMDProcessor = new CMDProcessor();
          localCMDProcessor.su.runWaitFor("chmod 755 /data/data/com.jrummy.root.browserfree/files/busybox");
          localCMDProcessor.su.runWaitFor("chmod 755 /data/data/com.jrummy.root.browserfree/files/sqlite3");
        }
      }
    }).setNegativeButton(getString(2131230768), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(23);
        paramAnonymousDialogInterface = FileBrowser.preferences.edit();
        paramAnonymousDialogInterface.putBoolean("is_root_user", false);
        paramAnonymousDialogInterface.commit();
        FileBrowser.this.UpdateList(FileBrowser.mHomeFolder, true);
      }
    }).create();
    new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837506)).setTitle(getString(2131230761)).setMessage(getString(2131230787)).setCancelable(false).setPositiveButton(getString(2131230767), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(paramInt);
        FileBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.jrummy.root.browser")));
      }
    }).setNeutralButton(getString(2131230768), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(paramInt);
      }
    }).create();
    new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837506)).setCancelable(false).setTitle(getString(2131230762)).setWebView("file:///android_asset/html/welcome.html").setPositiveButton(getString(2131230773), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://market.android.com/details?id=" + FileBrowser.this.getApplicationContext().getPackageName())));
      }
    }).setNeutralButton("Go Pro", false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://market.android.com/details?id=com.jrummy.root.browser")));
      }
    }).setNegativeButton(getString(2131230771), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(32);
        if (FileBrowser.mHasSuperUser)
        {
          FileBrowser.this.showDialog(23);
          return;
        }
        FileBrowser.this.UpdateList(FileBrowser.mHomeFolder, true);
      }
    }).create();
    new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837506)).setCancelable(false).setTitle("New Version!").setWebView("file:///android_asset/html/new_version.html").setPositiveButton(getString(2131230773), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://market.android.com/details?id=" + FileBrowser.this.getApplicationContext().getPackageName())));
      }
    }).setNeutralButton(getString(2131230825), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.mDialogTitle = FileBrowser.this.getString(2131230825);
        FileBrowser.mUrl = "file:///android_asset/html/changelog.html";
        FileBrowser.mImg = FileBrowser.this.res.getDrawable(2130837530);
        FileBrowser.this.showDialog(34);
      }
    }).setNegativeButton(getString(2131230771), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(33);
        FileBrowser.this.UpdateList(FileBrowser.mHomeFolder, true);
      }
    }).create();
    new PopupDialog.Builder(this).setIcon(mImg).setCancelable(true).setTitle(mDialogTitle).setWebView(mUrl).onBackPressed(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        FileBrowser.this.removeDialog(34);
      }
    }).create();
    localObject1 = this.res.getDrawable(2130837541);
    Object localObject3 = new BitmapDrawable(Reflection.main(((BitmapDrawable)mFile.getIcon()).getBitmap()));
    localObject2 = getString(2131230898);
    localObject3 = new PopupDialog.Builder(this).setIcon((Drawable)localObject3).setCancelable(true).setTitle(getString(2131230877)).setMessage(getString(2131230780, new Object[] { mFile.getFileName() })).onBackPressed(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        FileBrowser.this.removeDialog(35);
        FileBrowser.mZipFilesToExtract = null;
      }
    });
    Object localObject4 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(35);
        if (paramAnonymousInt == 0)
        {
          paramAnonymousDialogInterface = new File(FileBrowser.SD + File.separator + "romtoolbox" + File.separator + "extracted");
          if (!paramAnonymousDialogInterface.exists()) {
            paramAnonymousDialogInterface.mkdirs();
          }
          FileBrowser.this.extractFromInsideZip(FileBrowser.mZipFile, paramAnonymousDialogInterface.getAbsolutePath(), FileBrowser.mZipFilesToExtract);
          return;
        }
        FileBrowser.this.goBackAFolder();
        FileBrowser.this.showOperationMode("do_extract", true);
      }
    };
    return ((PopupDialog.Builder)localObject3).setItems(new String[] { "Extract", localObject2 }, new Drawable[] { localObject1, localObject1 }, (DialogInterface.OnClickListener)localObject4).create();
    new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837568)).setTitle(getString(2131230760)).setWebView("<html><body TEXT=\"#ffffff\">Creating the link failed with the following error message:<br><br><b><FONT COLOR=\"#F70223\">" + mStderr + "</FONT></b><br><br><b>Note:</b> Most SD cards do <b>NOT</b> support links.</body></html>").onBackPressed(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        FileBrowser.this.removeDialog(paramInt);
      }
    }).setNeutralButton(getString(2131230766), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(paramInt);
      }
    }).create();
    if (mCmdFile == null)
    {
      i = 1;
      localObject2 = new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837606));
      if (i == 0) {
        break label4680;
      }
      localObject1 = "Overwrite files?";
      label4602:
      localObject2 = ((PopupDialog.Builder)localObject2).setTitle((String)localObject1);
      if (i == 0) {
        break label4713;
      }
    }
    label4680:
    label4713:
    for (localObject1 = "Some or all of the files already exist. Do you want to overwrite them?";; localObject1 = mCmdFile.getFileName() + " already exists. Do you want to overwrite it?")
    {
      ((PopupDialog.Builder)localObject2).setMessage((String)localObject1).setCancelable(false).setPositiveButton(getString(2131230767), false, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FileBrowser.this.removeDialog(paramInt);
          FileBrowser.this.doCmd(FileBrowser.mCmd);
        }
      }).setNeutralButton(getString(2131230768), false, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FileBrowser.this.removeDialog(paramInt);
        }
      }).create();
      i = 0;
      break;
      localObject1 = "Overwrite " + mCmdFile.getFileName() + "?";
      break label4602;
    }
    new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837606)).setTitle("File Permissions").setMessage("File permissions do not allow zip files to be created here.\n\nBy default the zip will be created on your SD card.\n\nWould you like to continue?").setCancelable(false).setPositiveButton(getString(2131230771), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(paramInt);
        paramAnonymousDialogInterface = new File[FileBrowser.mSelectedFiles.size()];
        paramAnonymousInt = 0;
        for (;;)
        {
          if (paramAnonymousInt >= FileBrowser.mSelectedFiles.size())
          {
            FileBrowser.this.multiZipDialog(Helpers.SD, paramAnonymousDialogInterface);
            return;
          }
          paramAnonymousDialogInterface[paramAnonymousInt] = new File(((FileInfoHolder)FileBrowser.mSelectedFiles.get(paramAnonymousInt)).getFilePath());
          paramAnonymousInt += 1;
        }
      }
    }).setNeutralButton(getString(2131230765), false, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        FileBrowser.this.removeDialog(paramInt);
      }
    }).create();
    if (paramInt == 40)
    {
      bool1 = true;
      localObject2 = new PopupDialog.Builder(this).setIcon(this.res.getDrawable(2130837606));
      if (!bool1) {
        break label4955;
      }
      localObject1 = "Multi-delete";
      label4866:
      localObject2 = ((PopupDialog.Builder)localObject2).setTitle((String)localObject1);
      if (!bool1) {
        break label4966;
      }
    }
    label4955:
    label4966:
    for (localObject1 = "Are you sure you want to delete the selected items?";; localObject1 = "Are you sure you want to delete this file?")
    {
      ((PopupDialog.Builder)localObject2).setMessage((String)localObject1).setCancelable(false).setCheckbox("Always confirm when deleting", true).setPositiveButton(getString(2131230768), false, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FileBrowser.this.removeDialog(paramInt);
          if (!PopupDialog.mIsChecked)
          {
            paramAnonymousDialogInterface = FileBrowser.preferences.edit();
            paramAnonymousDialogInterface.putBoolean("confirm_delete_files", false);
            paramAnonymousDialogInterface.commit();
          }
        }
      }).setNeutralButton(getString(2131230767), false, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          FileBrowser.this.removeDialog(paramInt);
          if (!PopupDialog.mIsChecked)
          {
            paramAnonymousDialogInterface = FileBrowser.preferences.edit();
            paramAnonymousDialogInterface.putBoolean("confirm_delete_files", false);
            paramAnonymousDialogInterface.commit();
          }
          if (bool1)
          {
            FileBrowser.mDoBatch = true;
            FileBrowser.mSelectedFiles = FileBrowser.getSelected(FileBrowser.mFiles);
            FileBrowser.this.showSelectionMode(false);
          }
          FileBrowser.mCmdFile = FileBrowser.mFile;
          FileBrowser.this.doCmd("do_delete");
        }
      }).create();
      bool1 = false;
      break;
      localObject1 = mFile.getFileName();
      break label4866;
    }
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add(0, 0, 0, getString(2131230744)).setIcon(2130837578);
    paramMenu.add(0, 1, 0, getString(2131230735)).setIcon(2130837574);
    paramMenu.add(0, 2, 0, getString(2131230750)).setIcon(2130837573);
    paramMenu.add(0, 3, 0, getString(2131230742)).setIcon(2130837572);
    paramMenu.add(0, 4, 0, getString(2131230899)).setIcon(2130837571);
    paramMenu.add(0, 5, 0, getString(2131230901)).setIcon(2130837575);
    return true;
  }
  
  protected void onDestroy()
  {
    isInSelectionMode = false;
    mFiles.clear();
    mAllFiles.clear();
    super.onDestroy();
  }
  
  public void onItemClick(final AdapterView<?> paramAdapterView, View paramView, final int paramInt, long paramLong)
  {
    paramAdapterView = (FileInfoHolder)paramAdapterView.getItemAtPosition(paramInt);
    if (isExploringZip)
    {
      if (paramAdapterView.isDirectory())
      {
        listZipFile(mZipFile, paramAdapterView.getFilePath());
        return;
      }
      mFile = paramAdapterView;
      mZipFilesToExtract = new String[] { paramAdapterView.getFilePath() };
      showDialog(35);
      return;
    }
    QuickActionList localQuickActionList;
    ActionItem localActionItem;
    String str;
    PackageManager localPackageManager;
    Object localObject;
    final int m;
    label347:
    final int i1;
    label376:
    boolean bool2;
    final int n;
    label423:
    final int i2;
    label471:
    final int i3;
    final int k;
    if (!isInSelectionMode)
    {
      if (paramAdapterView.isDirectory())
      {
        mScrollPositions.put(mPath.getAbsolutePath(), Integer.valueOf(mListView.getFirstVisiblePosition()));
        UpdateList(paramAdapterView.getFilePath(), true);
        return;
      }
      if (pickFile)
      {
        paramView = getIntent();
        paramView.setData(Uri.fromFile(new File(paramAdapterView.getFilePath())));
        setResult(-1, paramView);
        finish();
        pickFile = false;
        return;
      }
      mFile = paramAdapterView;
      localQuickActionList = new QuickActionList(this);
      localActionItem = new ActionItem();
      paramInt = 0;
      if ((paramAdapterView.isImage()) || (paramAdapterView.isVideo()) || (paramAdapterView.isAudio()) || (paramAdapterView.isApk()) || (paramAdapterView.isZip()))
      {
        str = mFile.getMimeType();
        localPackageManager = getPackageManager();
        mIntent = new Intent("android.intent.action.VIEW");
        localObject = Uri.parse("file://" + mFile.getFilePath());
        mIntent.setDataAndType((Uri)localObject, str);
        mActivities = localPackageManager.queryIntentActivities(mIntent, 0);
        i = mActivities.size();
        paramInt = 0;
        j = mActivities.size();
        if (paramInt >= j) {
          paramInt = i;
        }
      }
      else
      {
        if ((mFile.isApk()) || (mFile.isZip()) || (mFile.isJar())) {
          break label1642;
        }
        m = 0;
        bool1 = mFile.isApk();
        if ((mFile.isZip()) || (mFile.isTar())) {
          break label1648;
        }
        i1 = 0;
        bool2 = mFile.isScript();
        if ((mFile.isTxt()) || (mFile.isScript()) || (mFile.isWebpage()) || (mFile.isProp())) {
          break label1654;
        }
        n = 0;
        if ((!hasRomManager) || ((!mFile.getFilePath().startsWith(SD)) && (!mFile.getFilePath().startsWith("/sdcard"))) || (!mFile.isZip())) {
          break label1660;
        }
        i2 = 1;
        i3 = 0;
        i = 0;
        if (mFile.isDatabase())
        {
          localActionItem.setTitle(getString(2131230902));
          localActionItem.setIcon(mFile.getIcon());
          localQuickActionList.addActionItem(localActionItem);
        }
        if (!mFile.isDatabase()) {
          break label1666;
        }
        j = paramInt + 1;
        k = paramInt;
        paramInt = j;
        label536:
        j = i;
        if (bool1)
        {
          localActionItem.setTitle(getString(2131230913));
          localActionItem.setIcon(mFile.getIcon());
          localQuickActionList.addActionItem(localActionItem);
          j = i;
          if (mFile.getPackageName() != null)
          {
            j = i;
            if (!mFile.getFilePath().startsWith("/system"))
            {
              localPackageManager = getPackageManager();
              j = i3;
            }
          }
        }
      }
    }
    try
    {
      if (!localPackageManager.getApplicationInfo(mFile.getPackageName(), 0).sourceDir.startsWith("/system")) {
        break label1672;
      }
      i = 0;
      label644:
      j = i;
      Log.i("FileBrowser", "Package is installed: " + mFile.getPackageName());
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        final int i4;
        final int i5;
        final int i10;
        final int i11;
        final int i12;
        final int i13;
        final int i14;
        final int i6;
        final int i7;
        final int i8;
        final int i9;
        i = j;
      }
    }
    j = i;
    if (i != 0)
    {
      localActionItem.setTitle(getString(2131230738));
      localActionItem.setIcon(this.res.getDrawable(2130837585));
      localQuickActionList.addActionItem(localActionItem);
      j = i;
    }
    if (bool1)
    {
      i = paramInt;
      paramInt += 1;
      label736:
      if (j == 0) {
        break label1684;
      }
      j = paramInt;
      paramInt += 1;
      label748:
      if (m != 0)
      {
        localActionItem.setTitle(getString(2131230774));
        localActionItem.setIcon(this.res.getDrawable(2130837598));
        localQuickActionList.addActionItem(localActionItem);
      }
      if (m == 0) {
        break label1690;
      }
      m = paramInt;
      paramInt += 1;
      label799:
      if (i1 != 0)
      {
        localActionItem.setTitle(getString(2131230877));
        localActionItem.setIcon(this.res.getDrawable(2130837541));
        localQuickActionList.addActionItem(localActionItem);
      }
      if (i1 == 0) {
        break label1696;
      }
      i1 = paramInt;
      paramInt += 1;
      label850:
      if (i2 != 0)
      {
        localActionItem.setTitle(getString(2131230881));
        localActionItem.setIcon(this.res.getDrawable(2130837607));
        localQuickActionList.addActionItem(localActionItem);
      }
      if (i2 == 0) {
        break label1702;
      }
      i2 = paramInt;
      paramInt += 1;
      label901:
      if ((bool2) && (mIsRootUser))
      {
        localActionItem.setTitle(getString(2131230739));
        localActionItem.setIcon(this.res.getDrawable(2130837601));
        localQuickActionList.addActionItem(localActionItem);
      }
      if ((!bool2) || (!mIsRootUser)) {
        break label1708;
      }
      i3 = paramInt;
      paramInt += 1;
      label964:
      if (n != 0)
      {
        localActionItem.setTitle(getString(2131230902));
        localActionItem.setIcon(mFile.getIcon());
        localQuickActionList.addActionItem(localActionItem);
        localActionItem.setTitle(getString(2131230903));
        localActionItem.setIcon(this.res.getDrawable(2130837535));
        localQuickActionList.addActionItem(localActionItem);
      }
      if (n == 0) {
        break label1714;
      }
      i4 = paramInt;
      paramInt += 1;
      label1045:
      if (n == 0) {
        break label1720;
      }
      i5 = paramInt + 1;
      n = paramInt;
      label1058:
      localActionItem.setTitle(getString(2131230879));
      localActionItem.setIcon(this.res.getDrawable(2130837538));
      localQuickActionList.addActionItem(localActionItem);
      i10 = i5 + 1;
      localActionItem.setTitle(getString(2131230880));
      localActionItem.setIcon(this.res.getDrawable(2130837547));
      localQuickActionList.addActionItem(localActionItem);
      i11 = i10 + 1;
      localActionItem.setTitle(getString(2131230914));
      localActionItem.setIcon(this.res.getDrawable(2130837585));
      localQuickActionList.addActionItem(localActionItem);
      i12 = i11 + 1;
      localActionItem.setTitle(getString(2131230915));
      localActionItem.setIcon(this.res.getDrawable(2130837551));
      localQuickActionList.addActionItem(localActionItem);
      i13 = i12 + 1;
      localActionItem.setTitle(getString(2131230740));
      localActionItem.setIcon(this.res.getDrawable(2130837600));
      localQuickActionList.addActionItem(localActionItem);
      i14 = i13 + 1;
      localActionItem.setTitle("Zip");
      localActionItem.setIcon(this.res.getDrawable(2130837557));
      localQuickActionList.addActionItem(localActionItem);
      paramInt = i14 + 1;
      if (mIsRootUser)
      {
        localActionItem.setTitle("Tar");
        localActionItem.setIcon(this.res.getDrawable(2130837554));
        localQuickActionList.addActionItem(localActionItem);
        localActionItem.setTitle(getString(2131230883));
        localActionItem.setIcon(this.res.getDrawable(2130837549));
        localQuickActionList.addActionItem(localActionItem);
        localActionItem.setTitle(getString(2131230905));
        localActionItem.setIcon(this.res.getDrawable(2130837592));
        localQuickActionList.addActionItem(localActionItem);
        localActionItem.setTitle(getString(2131230919));
        localActionItem.setIcon(this.res.getDrawable(2130837553));
        localQuickActionList.addActionItem(localActionItem);
      }
      if (!mIsRootUser) {
        break label1729;
      }
      i6 = paramInt;
      paramInt += 1;
      label1444:
      if (!mIsRootUser) {
        break label1735;
      }
      i7 = paramInt;
      paramInt += 1;
      label1457:
      if (!mIsRootUser) {
        break label1741;
      }
      i8 = paramInt;
      paramInt += 1;
      label1470:
      if (!mIsRootUser) {
        break label1747;
      }
      i9 = paramInt;
      paramInt += 1;
    }
    for (;;)
    {
      localActionItem.setTitle(getString(2131230916));
      localActionItem.setIcon(this.res.getDrawable(2130837568));
      localQuickActionList.addActionItem(localActionItem);
      localQuickActionList.setOnActionItemClickListener(new QuickActionList.OnActionItemClickListener()
      {
        public void onItemClick(int paramAnonymousInt)
        {
          int i;
          if ((FileBrowser.mFile.isImage()) || (FileBrowser.mFile.isVideo()) || (FileBrowser.mFile.isAudio()) || (FileBrowser.mFile.isApk()) || (paramAdapterView.isZip()))
          {
            i = 0;
            if (i < FileBrowser.mActivities.size()) {}
          }
          else
          {
            if (paramAnonymousInt != m) {
              break label144;
            }
            FileBrowser.this.listZipFile(FileBrowser.mFile.getFilePath(), "");
          }
          label144:
          label599:
          do
          {
            Object localObject;
            String str2;
            do
            {
              return;
              if (paramAnonymousInt == i)
              {
                localObject = (ResolveInfo)FileBrowser.mActivities.get(i);
                str2 = ((ResolveInfo)localObject).activityInfo.packageName;
                FileBrowser.mIntent.setClassName(str2, ((ResolveInfo)localObject).activityInfo.name);
                FileBrowser.this.startActivity(FileBrowser.mIntent);
              }
              i += 1;
              break;
              if (paramAnonymousInt == i)
              {
                FileBrowser.this.installApk(new File(FileBrowser.mFile.getFilePath()));
                return;
              }
              if (paramAnonymousInt == i1)
              {
                FileBrowser.this.showDialog(6);
                return;
              }
              if (paramAnonymousInt == i3)
              {
                FileBrowser.this.runScript(FileBrowser.mFile);
                return;
              }
              if (paramAnonymousInt == i4)
              {
                if (new File(FileBrowser.mFile.getFilePath()).canRead())
                {
                  FileBrowser.this.showDialog(0);
                  return;
                }
                FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), ViewFile.class));
                return;
              }
              if (paramAnonymousInt == n)
              {
                if (new File(FileBrowser.mFile.getFilePath()).canWrite())
                {
                  FileBrowser.this.showDialog(1);
                  return;
                }
                FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), EditFile.class));
                return;
              }
              if (paramAnonymousInt == i5)
              {
                FileBrowser.this.showOperationMode("do_copy", true);
                return;
              }
              if (paramAnonymousInt == i10)
              {
                FileBrowser.this.showOperationMode("do_move", true);
                return;
              }
              if (paramAnonymousInt == i11)
              {
                if (FileBrowser.preferences.getBoolean("confirm_delete_files", true))
                {
                  FileBrowser.this.showDialog(39);
                  return;
                }
                FileBrowser.this.doCmd("do_delete");
                return;
              }
              if (paramAnonymousInt == i12)
              {
                FileBrowser.this.renameDialog();
                return;
              }
              if (paramAnonymousInt == i13)
              {
                FileBrowser.this.showDialog(3);
                return;
              }
              if (paramAnonymousInt == i14)
              {
                FileBrowser.this.showDialog(18);
                return;
              }
              if (paramAnonymousInt == i6)
              {
                FileBrowser.this.showDialog(19);
                return;
              }
              if (paramAnonymousInt == i7)
              {
                FileBrowser.this.showPermissionsDialog();
                return;
              }
              if (paramAnonymousInt == paramInt)
              {
                FileBrowser.this.showDialog(2);
                return;
              }
              if (paramAnonymousInt == i8)
              {
                FileBrowser.this.showChangeOwnerDialog();
                return;
              }
              if (paramAnonymousInt != j) {
                break label599;
              }
              localObject = new Intent("android.intent.action.DELETE", Uri.parse("package:" + FileBrowser.mFile.getPackageName()));
            } while (FileBrowser.this.getPackageManager().queryIntentActivities((Intent)localObject, 0).size() <= 0);
            FileBrowser.this.startActivity((Intent)localObject);
            return;
            if (paramAnonymousInt == i2)
            {
              if (FileBrowser.this.mService == null)
              {
                FileBrowser.this.alertBar(FileBrowser.this.getString(2131230733), 5000);
                return;
              }
              str2 = FileBrowser.mFile.getFilePath();
              localObject = str2;
              if (str2.startsWith("/mnt")) {
                localObject = str2.substring(4, str2.length());
              }
              try
              {
                FileBrowser.this.mService.installZip((String)localObject);
                return;
              }
              catch (Exception localException)
              {
                FileBrowser.this.alertBar(FileBrowser.this.getString(2131230733), 5000);
                return;
              }
            }
            if (paramAnonymousInt == k)
            {
              DatabaseViewer.mDatabase = new Database();
              String str1 = new File(paramAdapterView.getFilePath()).getParent();
              DatabaseViewer.mDatabase.setDatabaseDir(str1);
              DatabaseViewer.mDatabase.setTitle(paramAdapterView.getFileName());
              DatabaseViewer.mSingleMode = true;
              FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), DatabaseViewer.class));
              return;
            }
          } while (paramAnonymousInt != i9);
          FileBrowser.mLinkFromFile = FileBrowser.mFile.getFilePath();
          FileBrowser.this.showOperationMode("do_symlink", true);
        }
      });
      localQuickActionList.show(paramView);
      return;
      localObject = (ResolveInfo)mActivities.get(paramInt);
      str = ((ResolveInfo)localObject).loadLabel(localPackageManager).toString();
      localObject = ((ResolveInfo)localObject).loadIcon(localPackageManager);
      localActionItem.setTitle(str);
      localActionItem.setIcon((Drawable)localObject);
      localQuickActionList.addActionItem(localActionItem);
      paramInt += 1;
      break;
      label1642:
      m = 1;
      break label347;
      label1648:
      i1 = 1;
      break label376;
      label1654:
      n = 1;
      break label423;
      label1660:
      i2 = 0;
      break label471;
      label1666:
      k = -1;
      break label536;
      label1672:
      i = 1;
      break label644;
      i = -1;
      break label736;
      label1684:
      j = -1;
      break label748;
      label1690:
      m = -1;
      break label799;
      label1696:
      i1 = -1;
      break label850;
      label1702:
      i2 = -1;
      break label901;
      label1708:
      i3 = -1;
      break label964;
      label1714:
      i4 = -1;
      break label1045;
      label1720:
      n = -1;
      i5 = paramInt;
      break label1058;
      label1729:
      i6 = -1;
      break label1444;
      label1735:
      i7 = -1;
      break label1457;
      label1741:
      i8 = -1;
      break label1470;
      label1747:
      i9 = -1;
    }
    if ((!paramAdapterView.getFileName().equals("..")) && (!paramAdapterView.isChecked())) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      paramAdapterView.setIsChecked(bool1);
      mAdapter.notifyDataSetChanged();
      return;
    }
  }
  
  public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (isExploringZip) {
      return false;
    }
    mFile = (FileInfoHolder)paramAdapterView.getItemAtPosition(paramInt);
    if (!mFile.getFileName().equals("..")) {
      showDialog(22);
    }
    return true;
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((Build.VERSION.SDK_INT < 5) && (paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0)) {
      onBackPressed();
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      return super.onKeyUp(paramInt, paramKeyEvent);
    }
    if (isExploringZip)
    {
      if (mIsSearchBarVisible) {}
      for (boolean bool = false;; bool = true)
      {
        showSearchBar(bool);
        return true;
      }
    }
    searchDialog();
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    case 0: 
      if (mSortType == 6) {}
      for (int i = 0;; i = mSortType + 1)
      {
        mSortType = i;
        sortFiles(mSortType);
        if (preferences.getBoolean("fb_save_sort_type", false))
        {
          paramMenuItem = preferences.edit();
          paramMenuItem.putInt("fb_sort_type", mSortType);
          paramMenuItem.putString("fb_sort_type_string", Integer.toString(mSortType));
          paramMenuItem.commit();
        }
        return true;
      }
    case 1: 
      UpdateList(mPath.toString(), false);
      Helpers.sendMsg(getApplicationContext(), getString(2131230909));
      return true;
    case 2: 
      startActivity(new Intent(getApplicationContext(), FileBrowserPrefs.class));
      return true;
    case 3: 
      showSelectionMode(true);
      return true;
    case 4: 
      if (isExploringZip) {
        isExploringZip = false;
      }
      UpdateList(preferences.getString("home_folder", "/"), true);
      return true;
    }
    searchDialog();
    return true;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    boolean bool2 = false;
    int i;
    Object localObject;
    if (mSortType == 6)
    {
      i = 0;
      MenuItem localMenuItem = paramMenu.getItem(0);
      if (i != 0) {
        break label232;
      }
      localObject = getString(2131230743);
      label35:
      localMenuItem.setTitle((CharSequence)localObject);
      localObject = paramMenu.getItem(0);
      if (i != 0) {
        break label329;
      }
      i = 2130837577;
      label62:
      ((MenuItem)localObject).setIcon(i);
      localObject = paramMenu.getItem(3);
      if ((isInSelectionMode) || (isExploringZip)) {
        break label396;
      }
      bool1 = true;
      label94:
      ((MenuItem)localObject).setVisible(bool1);
      localObject = paramMenu.getItem(4);
      if ((mPath.toString().equals(mHomeFolder)) || (isExploringZip)) {
        break label401;
      }
      bool1 = true;
      label135:
      ((MenuItem)localObject).setVisible(bool1);
      localObject = paramMenu.getItem(5);
      if (!isExploringZip) {
        break label406;
      }
      bool1 = false;
      label161:
      ((MenuItem)localObject).setVisible(bool1);
      localObject = paramMenu.getItem(1);
      if (!isExploringZip) {
        break label411;
      }
      bool1 = false;
      label187:
      ((MenuItem)localObject).setVisible(bool1);
      paramMenu = paramMenu.getItem(2);
      if (!isExploringZip) {
        break label416;
      }
    }
    label232:
    label329:
    label396:
    label401:
    label406:
    label411:
    label416:
    for (boolean bool1 = bool2;; bool1 = true)
    {
      paramMenu.setVisible(bool1);
      return true;
      i = mSortType + 1;
      break;
      if (i == 1)
      {
        localObject = getString(2131230744);
        break label35;
      }
      if (i == 2)
      {
        localObject = getString(2131230745);
        break label35;
      }
      if (i == 3)
      {
        localObject = getString(2131230746);
        break label35;
      }
      if (i == 4)
      {
        localObject = getString(2131230747);
        break label35;
      }
      if (i == 5)
      {
        localObject = getString(2131230748);
        break label35;
      }
      localObject = getString(2131230749);
      break label35;
      if (i == 1)
      {
        i = 2130837578;
        break label62;
      }
      if (i == 2)
      {
        i = 2130837579;
        break label62;
      }
      if (i == 3)
      {
        i = 2130837576;
        break label62;
      }
      if (i == 4)
      {
        i = 2130837580;
        break label62;
      }
      if (i == 5)
      {
        i = 2130837581;
        break label62;
      }
      i = 2130837582;
      break label62;
      bool1 = false;
      break label94;
      bool1 = false;
      break label135;
      bool1 = true;
      break label161;
      bool1 = true;
      break label187;
    }
  }
  
  protected void onRestart()
  {
    super.onRestart();
    if (mPath == null) {
      mPath = new File("/");
    }
    UpdateList(mPath.getAbsolutePath(), false);
    mAdapter.notifyDataSetChanged();
  }
  
  public void onSearch(View paramView)
  {
    boolean bool = false;
    int i;
    if (mSearchBar.getVisibility() == 0)
    {
      i = 1;
      if (i == 0) {
        break label28;
      }
    }
    for (;;)
    {
      showSearchBar(bool);
      return;
      i = 0;
      break;
      label28:
      bool = true;
    }
  }
  
  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if ((paramString.equals("fb_save_sort_type")) || (paramString.equals("fb_sort_type_string"))) {
      paramSharedPreferences = preferences.getString("fb_sort_type_string", "0");
    }
    for (;;)
    {
      try
      {
        int i = Integer.parseInt(paramSharedPreferences);
        mSortType = i;
        paramSharedPreferences = preferences.edit();
        paramSharedPreferences.putInt("fb_sort_type", i);
        paramSharedPreferences.commit();
        return;
      }
      catch (NumberFormatException paramSharedPreferences) {}
      if (paramString.equals("fb_show_hidden"))
      {
        mShowHidden = preferences.getBoolean("fb_show_hidden", true);
        return;
      }
      if (paramString.equals("fb_folders_first"))
      {
        mFoldersFirst = preferences.getBoolean("fb_folders_first", true);
        return;
      }
      if (paramString.equals("home_folder"))
      {
        mHomeFolder = preferences.getString("home_folder", "/");
        return;
      }
      if (paramString.equals("fb_show_notifications"))
      {
        mShowNotifications = preferences.getBoolean("fb_show_notifications", true);
        return;
      }
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    setMyTheme(PreferenceManager.getDefaultSharedPreferences(this).getInt("theme", 2));
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    Iterator localIterator;
    if (mIsSearchBarVisible)
    {
      paramCharSequence = mSearchBar.getText().toString().toLowerCase();
      paramInt1 = paramCharSequence.length();
      mFiles.clear();
      localIterator = mAllFiles.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        mAdapter.notifyDataSetChanged();
        return;
      }
      FileInfoHolder localFileInfoHolder = (FileInfoHolder)localIterator.next();
      String str = localFileInfoHolder.getFileName();
      if ((paramInt1 <= str.length()) && (str.toLowerCase().contains(paramCharSequence))) {
        mFiles.add(localFileInfoHolder);
      }
    }
  }
  
  public void onToggleChecked(View paramView)
  {
    if (!isInSelectionMode)
    {
      showSelectionMode(true);
      return;
    }
    QuickActionBar localQuickActionBar = new QuickActionBar(this);
    ActionItem localActionItem = new ActionItem();
    localActionItem.setTitle(getString(2131230818));
    localActionItem.setIcon(this.res.getDrawable(2130837599));
    localQuickActionBar.addActionItem(localActionItem);
    localActionItem.setTitle(getString(2131230819));
    localActionItem.setIcon(this.res.getDrawable(2130837595));
    localQuickActionBar.addActionItem(localActionItem);
    localQuickActionBar.setOnActionItemClickListener(new QuickActionBar.OnActionItemClickListener()
    {
      public void onItemClick(int paramAnonymousInt)
      {
        boolean bool;
        Iterator localIterator;
        if (paramAnonymousInt == 0)
        {
          bool = true;
          localIterator = FileBrowser.mAllFiles.iterator();
        }
        for (;;)
        {
          if (!localIterator.hasNext())
          {
            FileBrowser.mAdapter.notifyDataSetChanged();
            return;
            bool = false;
            break;
          }
          FileInfoHolder localFileInfoHolder = (FileInfoHolder)localIterator.next();
          if (!localFileInfoHolder.getFileName().equals("..")) {
            localFileInfoHolder.setIsChecked(bool);
          }
        }
      }
    });
    localQuickActionBar.show(paramView);
  }
  
  void runScript(final FileInfoHolder paramFileInfoHolder)
  {
    mDialogTitle = getString(2131230752);
    mDialogMsg = getString(2131230815, new Object[] { paramFileInfoHolder.getFileName() });
    showProgressSpinner(true);
    showDialog(5);
    new Thread()
    {
      public void run()
      {
        Looper.prepare();
        Object localObject1 = new CMDProcessor();
        String str;
        Object localObject2;
        int i;
        if (paramFileInfoHolder.getFileName().endsWith(".sh"))
        {
          str = "sh ";
          localObject2 = ((CMDProcessor)localObject1).su.runWaitFor(str + paramFileInfoHolder.getFilePath());
          str = ((CMDProcessor.CommandResult)localObject2).stderr;
          localObject1 = ((CMDProcessor.CommandResult)localObject2).stdout;
          FileBrowser localFileBrowser = FileBrowser.this;
          if (!((CMDProcessor.CommandResult)localObject2).success()) {
            break label185;
          }
          i = 2131230789;
          label91:
          localObject2 = new StringBuilder(String.valueOf(localFileBrowser.getString(i))).append("\n\n");
          if (str != null) {
            break label191;
          }
          str = "";
          label121:
          localObject2 = ((StringBuilder)localObject2).append(str);
          if (localObject1 != null) {
            break label231;
          }
          str = "";
        }
        for (;;)
        {
          FileBrowser.mDialogMsg = str;
          FileBrowser.this.handler.sendEmptyMessage(0);
          FileBrowser.mDialog = 7;
          FileBrowser.this.handler.sendEmptyMessage(7);
          return;
          str = "";
          break;
          label185:
          i = 2131230790;
          break label91;
          label191:
          if (str.equals(""))
          {
            str = "";
            break label121;
          }
          str = "stderr:\n\n" + str + "\n\n";
          break label121;
          label231:
          if (((String)localObject1).equals("")) {
            str = "";
          } else {
            str = "stdout:\n\n" + (String)localObject1;
          }
        }
      }
    }.start();
  }
  
  public ArrayList<String> searchInDirectory(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    search_file(paramString1, paramString2, localArrayList);
    return localArrayList;
  }
  
  public void setMyTheme(int paramInt)
  {
    int i1 = -16777216;
    int n = -16777216;
    int m = -1;
    mTextColor = -1;
    int i = i1;
    int j = n;
    int k = m;
    switch (paramInt)
    {
    default: 
      k = m;
      j = n;
      i = i1;
    }
    for (;;)
    {
      ((RelativeLayout)findViewById(2131361850)).setBackgroundColor(i);
      ((LinearLayout)findViewById(2131361795)).setBackgroundColor(j);
      ((TextView)findViewById(2131361884)).setTextColor(k);
      ((HorizontalScrollView)findViewById(2131361879)).setBackgroundColor(j);
      mStorageChartLabel.setTextColor(mTextColor);
      ((HorizontalScrollView)findViewById(2131361893)).setBackgroundColor(j);
      mListView.setBackgroundColor(i);
      mActionBar.setBackgroundColor(j);
      mOperationLayout.setBackgroundColor(j);
      return;
      j = -7829368;
      i = i1;
      k = m;
      continue;
      j = -7829368;
      i = -1;
      k = -16777216;
      mTextColor = -16777216;
      continue;
      i = -1;
      k = -16777216;
      mTextColor = -16777216;
      j = n;
      continue;
      j = 0;
      i = 0;
      k = m;
      continue;
      j = 0;
      k = -1;
      i = 0;
      continue;
      i = 0;
      j = n;
      k = m;
    }
  }
  
  void showChangeOwnerDialog()
  {
    final CMDProcessor localCMDProcessor = new CMDProcessor();
    Object localObject4 = getPackageManager().getInstalledPackages(0);
    Object localObject3 = new ArrayList();
    int m = ((List)localObject4).size();
    int i = 0;
    int k;
    Object localObject6;
    int j;
    label296:
    Object localObject5;
    Object localObject2;
    Object localObject1;
    label388:
    final Spinner localSpinner;
    if (i >= m)
    {
      Collections.sort((List)localObject3);
      k = ((ArrayList)localObject3).size();
      localObject6 = new String[k + 29];
      localObject6[0] = "0 - root";
      localObject6[1] = "1000 - system";
      localObject6[2] = "1001 - radio";
      localObject6[3] = "1002 - bluetooth";
      localObject6[4] = "1003 - graphics";
      localObject6[5] = "1004 - input";
      localObject6[6] = "1005 - audio";
      localObject6[7] = "1006 - camera";
      localObject6[8] = "1007 - log";
      localObject6[9] = "1008 - compass";
      localObject6[10] = "1009 - mount";
      localObject6[11] = "1010 - wifi";
      localObject6[12] = "1011 - adb";
      localObject6[13] = "1012 - install";
      localObject6[14] = "1013 - media";
      localObject6[15] = "1014 - dhcp";
      localObject6[16] = "1015 - sdcard_rw";
      localObject6[17] = "1016 - vpn";
      localObject6[18] = "1017 - keystore";
      localObject6[19] = "2000 - shell";
      localObject6[20] = "2001 - cache";
      localObject6[21] = "2002 - diag";
      localObject6[22] = "3001 - net_bt_admin";
      localObject6[23] = "3002 - net_bt";
      localObject6[24] = "3003 - inet";
      localObject6[25] = "3004 - net_raw";
      localObject6[26] = "3005 - net_admin";
      localObject6[27] = "9998 - misc";
      localObject6[28] = "9999 - nobody";
      j = 29;
      i = 0;
      if (i < k) {
        break label901;
      }
      localObject5 = new Dialog(this);
      ((Dialog)localObject5).requestWindowFeature(3);
      ((Dialog)localObject5).setContentView(2130903043);
      ((Dialog)localObject5).setFeatureDrawable(3, mFile.getIcon());
      ((Dialog)localObject5).setTitle(mFile.getFileName());
      ((Dialog)localObject5).setCancelable(true);
      ((Dialog)localObject5).setCanceledOnTouchOutside(false);
      localObject2 = mFile.getUserId();
      localObject1 = mFile.getGroup();
      k = 0;
      j = 0;
      i = 0;
      m = localObject6.length;
      if (i < m) {
        break label925;
      }
      localObject3 = (TextView)((Dialog)localObject5).findViewById(2131361801);
      ((TextView)localObject3).setText(getString(2131230788, new Object[] { localObject2, localObject1 }));
      ((TextView)localObject3).setTypeface(SUB_FONT);
      ((TextView)((Dialog)localObject5).findViewById(2131361804)).setTypeface(SUB_FONT);
      ((TextView)((Dialog)localObject5).findViewById(2131361802)).setTypeface(SUB_FONT);
      localSpinner = (Spinner)((Dialog)localObject5).findViewById(2131361803);
      localObject3 = new ArrayAdapter(this, 17367048, (Object[])localObject6);
      ((ArrayAdapter)localObject3).setDropDownViewResource(17367049);
      localSpinner.setAdapter((SpinnerAdapter)localObject3);
      localSpinner.setSelection(k);
      i = 0;
      k = localObject6.length;
      label530:
      if (i < k) {
        break label1036;
      }
      localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (TextView)paramAnonymousAdapterView.getChildAt(0);
          paramAnonymousAdapterView.setTextColor(-1);
          paramAnonymousAdapterView.setTypeface(FileBrowser.SUB_FONT);
          FileBrowser.mOwner = localSpinner.getItemAtPosition(paramAnonymousInt).toString();
          if (FileBrowser.mOwner.contains(" - "))
          {
            paramAnonymousAdapterView = FileBrowser.mOwner.substring(FileBrowser.mOwner.lastIndexOf(" - "));
            FileBrowser.mOwner = FileBrowser.mOwner.replace(paramAnonymousAdapterView, "");
          }
        }
        
        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
      });
      localObject4 = (Spinner)((Dialog)localObject5).findViewById(2131361805);
      localObject2 = new ArrayAdapter(this, 17367048, (Object[])localObject6);
      ((ArrayAdapter)localObject2).setDropDownViewResource(17367049);
      ((Spinner)localObject4).setAdapter((SpinnerAdapter)localObject2);
      ((Spinner)localObject4).setSelection(j);
      i = 0;
      j = localObject6.length;
    }
    for (;;)
    {
      if (i >= j)
      {
        ((Spinner)localObject4).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
          public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            paramAnonymousAdapterView = (TextView)paramAnonymousAdapterView.getChildAt(0);
            paramAnonymousAdapterView.setTextColor(-1);
            paramAnonymousAdapterView.setTypeface(FileBrowser.SUB_FONT);
            FileBrowser.mGroup = localSpinner.getItemAtPosition(paramAnonymousInt).toString();
            if (FileBrowser.mGroup.contains(" - "))
            {
              paramAnonymousAdapterView = FileBrowser.mGroup.substring(FileBrowser.mGroup.lastIndexOf(" - "));
              FileBrowser.mGroup = FileBrowser.mGroup.replace(paramAnonymousAdapterView, "");
            }
          }
          
          public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
        });
        localObject1 = (Button)((Dialog)localObject5).findViewById(2131361806);
        ((Button)localObject1).setTypeface(SUB_FONT);
        ((Button)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            int j = 0;
            FileBrowser.getMounts("rw");
            localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox chown " + FileBrowser.mOwner + " " + FileBrowser.mFile.getFilePath());
            localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox chgrp " + FileBrowser.mGroup + " " + FileBrowser.mFile.getFilePath());
            FileBrowser.getMounts("ro");
            FileBrowser.this.UpdateList(FileBrowser.mPath.toString(), false);
            paramAnonymousView = FileBrowser.mGroup;
            String str = FileBrowser.mOwner;
            Iterator localIterator = FileBrowser.mAllFiles.iterator();
            if (!localIterator.hasNext())
            {
              label141:
              int i = j;
              if (paramAnonymousView.equals(FileBrowser.mGroup))
              {
                i = j;
                if (str.equals(FileBrowser.mOwner)) {
                  i = 1;
                }
              }
              if (i == 0) {
                break label244;
              }
              Helpers.msgShort(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230907));
            }
            for (;;)
            {
              this.val$dialog.dismiss();
              return;
              FileInfoHolder localFileInfoHolder = (FileInfoHolder)localIterator.next();
              if (!localFileInfoHolder.getFileName().equals(FileBrowser.mFile.getFileName())) {
                break;
              }
              paramAnonymousView = localFileInfoHolder.getGroup();
              str = localFileInfoHolder.getUserId();
              break label141;
              label244:
              if (FileBrowser.preferences.getBoolean("show_set_owner_warning", true)) {
                FileBrowser.this.showDialog(21);
              } else {
                Helpers.msgShort(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230861));
              }
            }
          }
        });
        localObject1 = (Button)((Dialog)localObject5).findViewById(2131361809);
        ((Button)localObject1).setTypeface(SUB_FONT);
        ((Button)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            this.val$dialog.dismiss();
          }
        });
        ((Dialog)localObject5).show();
        return;
        j = ((PackageInfo)((List)localObject4).get(i)).applicationInfo.uid;
        if ((j <= 1017) && (j >= 1000))
        {
          i += 1;
          break;
        }
        localObject5 = "app_" + Integer.toString(j - 10000);
        localObject6 = Integer.toString(j);
        k = 1;
        j = 0;
        for (;;)
        {
          if (j >= ((ArrayList)localObject3).size()) {}
          for (j = k;; j = 0)
          {
            if (j == 0) {
              break label892;
            }
            ((ArrayList)localObject3).add(localObject6 + " - " + (String)localObject5);
            break;
            localObject2 = (String)((ArrayList)localObject3).get(j);
            localObject1 = localObject2;
            if (((String)localObject2).contains(" - ")) {
              localObject1 = ((String)localObject2).replace(((String)localObject2).substring(((String)localObject2).lastIndexOf(" - ")), "");
            }
            if (!((String)localObject1).equals(localObject6)) {
              break label894;
            }
          }
          label892:
          break;
          label894:
          j += 1;
        }
        label901:
        localObject6[j] = ((String)((ArrayList)localObject3).get(i));
        j += 1;
        i += 1;
        break label296;
        label925:
        localObject3 = localObject6[i];
        if (localObject6[i].contains(" - "))
        {
          localObject3 = localObject6[i].substring(localObject6[i].lastIndexOf(" - "));
          localObject3 = localObject6[i].replace((CharSequence)localObject3, "");
        }
        localObject4 = localObject2;
        if (((String)localObject2).equals(localObject3))
        {
          localObject4 = localObject6[i];
          k = i;
        }
        localObject2 = localObject1;
        if (((String)localObject1).equals(localObject3))
        {
          localObject2 = localObject6[i];
          j = i;
        }
        i += 1;
        localObject1 = localObject2;
        localObject2 = localObject4;
        break label388;
        label1036:
        localObject4 = localObject6[i];
        localObject3 = localObject4;
        if (((String)localObject4).contains(" - ")) {
          localObject3 = ((String)localObject4).replace(((String)localObject4).substring(((String)localObject4).lastIndexOf(" - ")), "");
        }
        if (((String)localObject3).equals(localObject2)) {
          localSpinner.setSelection(i);
        }
        i += 1;
        break label530;
      }
      localObject3 = localObject6[i];
      localObject2 = localObject3;
      if (((String)localObject3).contains(" - ")) {
        localObject2 = ((String)localObject3).replace(((String)localObject3).substring(((String)localObject3).lastIndexOf(" - ")), "");
      }
      if (((String)localObject2).equals(localObject1)) {
        ((Spinner)localObject4).setSelection(i);
      }
      i += 1;
    }
  }
  
  void showOperationMode(String paramString, boolean paramBoolean)
  {
    if (mPbarLayout.getVisibility() == 0)
    {
      alertBar(getString(2131230725), 5000);
      return;
    }
    mCmd = paramString;
    mCmdFile = mFile;
    if (paramBoolean)
    {
      if (paramString.equals("do_extract"))
      {
        mBtnDoOperation.setText(getString(2131230877));
        mLockScreenRotation();
        if (!isInSelectionMode) {
          break label251;
        }
        showSelectionMode(false);
        label76:
        mOperationLayout.setVisibility(0);
        mOperationLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968598));
        paramString = new RelativeLayout.LayoutParams(-1, -1);
        if (!mFreebie) {
          break label287;
        }
      }
      label251:
      label287:
      for (int i = 2131361913;; i = 2131361800)
      {
        paramString.addRule(2, i);
        paramString.addRule(3, 2131361879);
        mListView.setLayoutParams(paramString);
        return;
        if ((paramString.equals("do_copy")) || (paramString.equals("do_move")))
        {
          mBtnDoOperation.setText(getString(2131230878));
          break;
        }
        if (paramString.equals("do_zip_file"))
        {
          mBtnDoOperation.setText(getString(2131230893));
          break;
        }
        if (paramString.equals("do_tar"))
        {
          mBtnDoOperation.setText(getString(2131230894));
          break;
        }
        if (!paramString.equals("do_symlink")) {
          break;
        }
        mBtnDoOperation.setText(getString(2131230920));
        break;
        if (mColorBarLayout.getVisibility() != 0) {
          break label76;
        }
        mColorBarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
        mColorBarLayout.setVisibility(8);
        break label76;
      }
    }
    setRequestedOrientation(4);
    mOperationLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
    mOperationLayout.setVisibility(8);
    paramString = new RelativeLayout.LayoutParams(-1, -1);
    paramString.addRule(2, 2131361881);
    paramString.addRule(3, 2131361879);
    mListView.setLayoutParams(paramString);
    updateStorageUsage();
  }
  
  void showPermissionsDialog()
  {
    mLockScreenRotation();
    final Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(3);
    localDialog.setContentView(2130903059);
    localDialog.setFeatureDrawableResource(3, 2130837549);
    localDialog.setTitle(mFile.getFileName());
    localDialog.setCancelable(true);
    localDialog.setCanceledOnTouchOutside(false);
    localDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        FileBrowser.this.setRequestedOrientation(4);
      }
    });
    localDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        FileBrowser.this.setRequestedOrientation(4);
      }
    });
    ((TextView)localDialog.findViewById(2131361948)).setTypeface(SUB_FONT);
    ((TextView)localDialog.findViewById(2131361952)).setTypeface(SUB_FONT);
    ((TextView)localDialog.findViewById(2131361807)).setTypeface(SUB_FONT);
    Object localObject1 = (CheckBox)localDialog.findViewById(2131361949);
    final CheckBox localCheckBox1 = (CheckBox)localDialog.findViewById(2131361950);
    final CheckBox localCheckBox2 = (CheckBox)localDialog.findViewById(2131361951);
    final CheckBox localCheckBox3 = (CheckBox)localDialog.findViewById(2131361953);
    final CheckBox localCheckBox4 = (CheckBox)localDialog.findViewById(2131361954);
    final CheckBox localCheckBox5 = (CheckBox)localDialog.findViewById(2131361955);
    final CheckBox localCheckBox6 = (CheckBox)localDialog.findViewById(2131361956);
    final CheckBox localCheckBox7 = (CheckBox)localDialog.findViewById(2131361957);
    final CheckBox localCheckBox8 = (CheckBox)localDialog.findViewById(2131361958);
    final CheckBox localCheckBox9 = (CheckBox)localDialog.findViewById(2131361808);
    final CheckBox localCheckBox10 = (CheckBox)localDialog.findViewById(2131361963);
    final CheckBox localCheckBox11 = (CheckBox)localDialog.findViewById(2131361965);
    Object localObject2 = new CheckBox[9];
    localObject2[0] = localObject1;
    localObject2[1] = localCheckBox1;
    localObject2[2] = localCheckBox2;
    localObject2[3] = localCheckBox3;
    localObject2[4] = localCheckBox4;
    localObject2[5] = localCheckBox5;
    localObject2[6] = localCheckBox6;
    localObject2[7] = localCheckBox7;
    localObject2[8] = localCheckBox8;
    String str = mFile.getPermissions();
    int j;
    int i;
    if (str != null)
    {
      j = str.length();
      if (j - 1 == localObject2.length) {
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= j)
      {
        localObject2 = (Button)localDialog.findViewById(2131361806);
        ((Button)localObject2).setTypeface(SUB_FONT);
        ((Button)localObject2).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            boolean bool1 = this.val$chkUserRead.isChecked();
            boolean bool2 = localCheckBox1.isChecked();
            boolean bool3 = localCheckBox2.isChecked();
            boolean bool4 = localCheckBox3.isChecked();
            boolean bool5 = localCheckBox4.isChecked();
            boolean bool6 = localCheckBox5.isChecked();
            boolean bool7 = localCheckBox6.isChecked();
            boolean bool8 = localCheckBox7.isChecked();
            boolean bool9 = localCheckBox8.isChecked();
            boolean bool10 = localCheckBox9.isChecked();
            boolean bool11 = localCheckBox10.isChecked();
            boolean bool12 = localCheckBox11.isChecked();
            int j = 0;
            int k = 0;
            int m = 0;
            int n = 0;
            if (bool10) {
              j = 0 + 4;
            }
            int i = j;
            if (bool11) {
              i = j + 2;
            }
            j = i;
            if (bool12) {
              j = i + 1;
            }
            label209:
            Object localObject;
            label240:
            label279:
            label308:
            label339:
            label378:
            label407:
            label437:
            Iterator localIterator;
            if (bool1)
            {
              i = 0 + 4;
              paramAnonymousView = "" + "r";
              if (!bool2) {
                break label700;
              }
              i += 2;
              paramAnonymousView = paramAnonymousView + "w";
              if (!bool3) {
                break label729;
              }
              k = i + 1;
              localObject = new StringBuilder(String.valueOf(paramAnonymousView));
              if (!bool10) {
                break label723;
              }
              paramAnonymousView = "s";
              paramAnonymousView = paramAnonymousView;
              if (!bool4) {
                break label772;
              }
              i = 0 + 4;
              paramAnonymousView = paramAnonymousView + "r";
              if (!bool5) {
                break label798;
              }
              i += 2;
              paramAnonymousView = paramAnonymousView + "w";
              if (!bool6) {
                break label827;
              }
              m = i + 1;
              localObject = new StringBuilder(String.valueOf(paramAnonymousView));
              if (!bool11) {
                break label821;
              }
              paramAnonymousView = "s";
              paramAnonymousView = paramAnonymousView;
              if (!bool7) {
                break label870;
              }
              i = 0 + 4;
              paramAnonymousView = paramAnonymousView + "r";
              if (!bool8) {
                break label896;
              }
              i += 2;
              paramAnonymousView = paramAnonymousView + "w";
              if (!bool9) {
                break label925;
              }
              i += 1;
              localObject = new StringBuilder(String.valueOf(paramAnonymousView));
              if (!bool12) {
                break label919;
              }
              paramAnonymousView = "t";
              paramAnonymousView = paramAnonymousView;
              localObject = new CMDProcessor();
              FileBrowser.getMounts("rw");
              Log.i("FileBrowser", "Setting " + FileBrowser.mFile.getFilePath() + " permissions. Mode: " + j + k + m + i + " Perms: " + paramAnonymousView);
              bool2 = ((CMDProcessor)localObject).su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox chmod " + j + k + m + i + " \"" + FileBrowser.mFile.getFilePath() + "\"").success();
              FileBrowser.getMounts("ro");
              bool1 = bool2;
              if (bool2)
              {
                FileBrowser.this.UpdateList(FileBrowser.mPath.toString(), false);
                localObject = paramAnonymousView;
                localIterator = FileBrowser.mAllFiles.iterator();
                label623:
                if (localIterator.hasNext()) {
                  break label965;
                }
                label633:
                bool1 = ((String)localObject).equals(paramAnonymousView);
              }
              if (!bool1) {
                break label1007;
              }
              Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230859));
            }
            for (;;)
            {
              localDialog.dismiss();
              return;
              paramAnonymousView = "" + "-";
              i = k;
              break;
              label700:
              paramAnonymousView = paramAnonymousView + "-";
              break label209;
              label723:
              paramAnonymousView = "x";
              break label240;
              label729:
              localObject = new StringBuilder(String.valueOf(paramAnonymousView));
              if (bool10) {}
              for (paramAnonymousView = "S";; paramAnonymousView = "-")
              {
                paramAnonymousView = paramAnonymousView;
                k = i;
                break;
              }
              label772:
              paramAnonymousView = paramAnonymousView + "-";
              i = m;
              break label279;
              label798:
              paramAnonymousView = paramAnonymousView + "-";
              break label308;
              label821:
              paramAnonymousView = "x";
              break label339;
              label827:
              localObject = new StringBuilder(String.valueOf(paramAnonymousView));
              if (bool11) {}
              for (paramAnonymousView = "S";; paramAnonymousView = "-")
              {
                paramAnonymousView = paramAnonymousView;
                m = i;
                break;
              }
              label870:
              paramAnonymousView = paramAnonymousView + "-";
              i = n;
              break label378;
              label896:
              paramAnonymousView = paramAnonymousView + "-";
              break label407;
              label919:
              paramAnonymousView = "x";
              break label437;
              label925:
              localObject = new StringBuilder(String.valueOf(paramAnonymousView));
              if (bool12) {}
              for (paramAnonymousView = "T";; paramAnonymousView = "-")
              {
                paramAnonymousView = paramAnonymousView;
                break;
              }
              label965:
              FileInfoHolder localFileInfoHolder = (FileInfoHolder)localIterator.next();
              if (!localFileInfoHolder.getFileName().equals(FileBrowser.mFile.getFileName())) {
                break label623;
              }
              localObject = FileBrowser.getPermStr(localFileInfoHolder.getPermissions());
              break label633;
              label1007:
              if (FileBrowser.preferences.getBoolean("show_permission_warning", true)) {
                FileBrowser.this.showDialog(20);
              } else {
                Helpers.sendMsg(FileBrowser.this.getApplicationContext(), FileBrowser.this.getString(2131230860));
              }
            }
          }
        });
        localObject1 = (Button)localDialog.findViewById(2131361809);
        ((Button)localObject1).setTypeface(SUB_FONT);
        ((Button)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            localDialog.dismiss();
          }
        });
        localDialog.show();
        return;
      }
      if (i != 0) {
        break;
      }
      i += 1;
    }
    if ((i == 3) && (str.toLowerCase().charAt(i) == 's')) {
      localCheckBox9.setChecked(true);
    }
    if ((i == 6) && (str.toLowerCase().charAt(i) == 's')) {
      localCheckBox10.setChecked(true);
    }
    if ((i == 9) && (str.toLowerCase().charAt(i) == 't')) {
      localCheckBox11.setChecked(true);
    }
    if ((str.charAt(i) != '-') && (str.charAt(i) != 'S') && (str.charAt(i) != 'T')) {}
    for (boolean bool = true;; bool = false)
    {
      localObject2[(i - 1)].setChecked(bool);
      break;
    }
  }
  
  void showProgressHorizontal(int paramInt1, int paramInt2, boolean paramBoolean1, String paramString, boolean paramBoolean2, int paramInt3)
  {
    mLockScreenRotation();
    int i = (int)Math.floor(paramInt2 / paramInt1 * 100.0D);
    mPbarHorz.setMax(paramInt1);
    mPbarHorz.setProgress(paramInt2);
    mPbarCount.setText(paramInt2 + "/" + paramInt1);
    mPbarPerc.setText(i + "%");
    mPbarMsg.setText("");
    if (paramBoolean1)
    {
      mProgressTitle = paramString;
      mPbarTitleMsg.setText(mProgressTitle);
      if (!paramBoolean2) {
        break label242;
      }
      switch (paramInt3)
      {
      default: 
        mPbarImage.setVisibility(8);
        mPbarTitleImg.setVisibility(8);
      }
    }
    for (;;)
    {
      mPbarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968588));
      mPbarLayout.setVisibility(0);
      mBtnHidePbar.setVisibility(0);
      return;
      mPbarTitleLayout.setVisibility(8);
      break;
      mPbarTitleImg.setVisibility(0);
      mPbarImage.setVisibility(8);
      continue;
      mPbarTitleImg.setVisibility(8);
      mPbarImage.setVisibility(0);
      continue;
      label242:
      mPbarTitleImg.setVisibility(8);
      mPbarImage.setVisibility(8);
    }
  }
  
  void showProgressSpinner(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      mEmptyText.setText(getString(2131230875));
      mActionBarHome.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968579));
      mActionBarHome.setVisibility(8);
      mPbarSpinner.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 17432576));
      mPbarSpinner.setVisibility(0);
      mPbarSpinner2.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 17432576));
      mPbarSpinner2.setVisibility(0);
      return;
    }
    mEmptyText.setText(getString(2131230874));
    mPbarSpinner.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968579));
    mPbarSpinner.setVisibility(8);
    mActionBarHome.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 17432576));
    mActionBarHome.setVisibility(0);
    mPbarSpinner2.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 17432577));
    mPbarSpinner2.setVisibility(8);
  }
  
  void showSearchBar(boolean paramBoolean)
  {
    mIsSearchBarVisible = paramBoolean;
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if (paramBoolean)
    {
      mTitleText.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968589));
      mTitleText.setVisibility(8);
      mSearchBar.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968576));
      mSearchBar.setVisibility(0);
      mSearchBar.requestFocus();
      mActionBarSearch.setImageDrawable(this.res.getDrawable(2130837562));
      mActionBarSearch.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968576));
      localInputMethodManager.showSoftInput(mSearchBar, 2);
      return;
    }
    mSearchBar.setText("");
    mFiles.clear();
    mFiles.addAll(mAllFiles);
    mAdapter.notifyDataSetChanged();
    localInputMethodManager.hideSoftInputFromWindow(mSearchBar.getWindowToken(), 0);
    mSearchBar.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968589));
    mSearchBar.setVisibility(8);
    mTitleText.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968576));
    mTitleText.setVisibility(0);
    mActionBarSearch.setImageDrawable(this.res.getDrawable(2130837566));
    mActionBarSearch.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968576));
  }
  
  void showSelectionMode(boolean paramBoolean)
  {
    if (!mFreebie)
    {
      showDialog(25);
      return;
    }
    isInSelectionMode = paramBoolean;
    if (paramBoolean)
    {
      localObject = mBtnSend;
      if (isOnSD(mPath)) {}
      for (int i = 0;; i = 8)
      {
        ((LinearLayout)localObject).setVisibility(i);
        if (mColorBarLayout.getVisibility() == 0)
        {
          mColorBarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
          mColorBarLayout.setVisibility(8);
        }
        mBottomBar.setVisibility(0);
        mBottomBar.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968598));
        localObject = new RelativeLayout.LayoutParams(-1, -1);
        ((RelativeLayout.LayoutParams)localObject).addRule(2, 2131361892);
        ((RelativeLayout.LayoutParams)localObject).addRule(3, 2131361879);
        mListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        mAdapter.notifyDataSetChanged();
        return;
      }
    }
    Object localObject = mAllFiles.iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        mAdapter.notifyDataSetChanged();
        if (mBottomBar.getVisibility() == 0)
        {
          mBottomBar.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
          mBottomBar.setVisibility(8);
        }
        updateStorageUsage();
        localObject = new RelativeLayout.LayoutParams(-1, -1);
        ((RelativeLayout.LayoutParams)localObject).addRule(2, 2131361881);
        ((RelativeLayout.LayoutParams)localObject).addRule(3, 2131361879);
        mListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        return;
      }
      ((FileInfoHolder)((Iterator)localObject).next()).setIsChecked(false);
    }
  }
  
  void showStatusBarProgress(int paramInt1, int paramInt2, String paramString, int paramInt3, int paramInt4)
  {
    progress = paramInt2;
    Object localObject = new Intent();
    localObject = PendingIntent.getActivity(getApplicationContext(), 0, (Intent)localObject, 0);
    this.notification = new Notification(17301633, paramString, System.currentTimeMillis());
    this.notification.flags |= 0x2;
    this.notification.contentView = new RemoteViews(getApplicationContext().getPackageName(), 2130903045);
    this.notification.contentIntent = ((PendingIntent)localObject);
    this.notification.contentView.setImageViewResource(2131361829, paramInt3);
    this.notification.contentView.setProgressBar(2131361833, paramInt1, progress, false);
    getApplicationContext();
    this.notificationManager = ((NotificationManager)getApplicationContext().getSystemService("notification"));
    this.notificationManager.notify(paramInt4, this.notification);
  }
  
  void transferAsset(String paramString1, String paramString2)
  {
    Log.i("FileBrowser", "Started copying: " + paramString1);
    Object localObject = new File("/data/data/com.jrummy.root.browserfree/files/" + paramString2);
    if (((File)localObject).exists())
    {
      Log.i("FileBrowser", "Deleting old : " + paramString2);
      ((File)localObject).delete();
    }
    try
    {
      localObject = this.res.getAssets().open(paramString1);
      paramString2 = openFileOutput(paramString2, 2);
      byte[] arrayOfByte = new byte[((InputStream)localObject).available()];
      ((InputStream)localObject).read(arrayOfByte);
      paramString2.write(arrayOfByte);
      ((InputStream)localObject).close();
      paramString2.close();
      Log.i("FileBrowser", "Transfered: " + paramString1);
      return;
    }
    catch (IOException paramString2)
    {
      Log.e("FileBrowser", "Error writing: " + paramString1);
    }
  }
  
  void updateStorageUsage()
  {
    l4 = 0L;
    long l3 = 0L;
    long l1 = 0L;
    Object localObject1 = null;
    if ((mPath.toString().startsWith(SD)) || ((mPath.toString().startsWith("/sdcard")) && (!mPath.toString().startsWith("/sdcard-ext"))))
    {
      localObject1 = getString(2131230849);
      mStatFs.restat(SD);
    }
    try
    {
      l2 = mStatFs.getBlockCount() * mStatFs.getBlockSize();
      l1 = l2;
      long l5 = mStatFs.getAvailableBlocks();
      l1 = l2;
      i = mStatFs.getBlockSize();
      l4 = l5 * i;
      l1 = l2;
      l2 = l4;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        int i;
        Object localObject2;
        label141:
        label496:
        label554:
        label557:
        label580:
        long l2 = l4;
      }
    }
    localObject2 = mAllFiles.iterator();
    if (!((Iterator)localObject2).hasNext())
    {
      if (localObject1 != null) {
        mStorageChartLabel.setText((CharSequence)localObject1);
      }
      if (l1 <= 0L) {
        break label580;
      }
      mColorBar.setRatios((float)(l1 - l2 - l3) / (float)l1, (float)l3 / (float)l1, (float)l2 / (float)l1);
      l1 -= l2;
      if (this.mLastUsedStorage != l1)
      {
        this.mLastUsedStorage = l1;
        localObject1 = Formatter.formatShortFileSize(this, l1);
        mUsedStorageText.setText(getString(2131230847, new Object[] { localObject1 }));
      }
      if (this.mLastFreeStorage != l2)
      {
        this.mLastFreeStorage = l2;
        localObject1 = Formatter.formatShortFileSize(this, l2);
        mFreeStorageText.setText(getString(2131230848, new Object[] { localObject1 }));
      }
      if ((mColorBarLayout.getVisibility() != 0) && (mOperationLayout.getVisibility() != 0) && (mBottomBar.getVisibility() != 0))
      {
        mColorBarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968598));
        mColorBarLayout.setVisibility(0);
      }
    }
    do
    {
      return;
      if (mPath.toString().startsWith("/sdcard-ext"))
      {
        localObject1 = getString(2131230849);
        mStatFs.restat("/sdcard-ext");
        break;
      }
      if (mPath.toString().startsWith("/system"))
      {
        localObject1 = getString(2131230850);
        mStatFs.restat("/system");
        break;
      }
      if (mPath.toString().startsWith("/data"))
      {
        localObject1 = getString(2131230851);
        mStatFs.restat("/data");
        break;
      }
      localObject2 = mPath.toString().substring(mPath.toString().indexOf("/"));
      int j = 0;
      i = 0;
      int m = ((String)localObject2).length();
      if (i >= m) {
        if (j != 1) {
          break label554;
        }
      }
      for (i = 1;; i = 0)
      {
        if (i == 0) {
          break label557;
        }
        localObject1 = localObject2;
        mStatFs.restat((String)localObject2);
        break;
        int k = j;
        if (((String)localObject2).charAt(i) == '/') {
          k = j + 1;
        }
        i += 1;
        j = k;
        break label496;
      }
      break;
      l3 += ((FileInfoHolder)((Iterator)localObject2).next()).fileSize();
      break label141;
      mColorBar.setRatios(0.0F, 0.0F, 0.0F);
      if (this.mLastUsedStorage != -1L)
      {
        this.mLastUsedStorage = -1L;
        mUsedStorageText.setText("");
      }
      if (this.mLastFreeStorage != -1L)
      {
        this.mLastFreeStorage = -1L;
        mFreeStorageText.setText("");
      }
    } while (mColorBarLayout.getVisibility() == 8);
    mColorBarLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2130968595));
    mColorBarLayout.setVisibility(8);
  }
  
  public void zipMultipleFiles(final File[] paramArrayOfFile, final String paramString)
  {
    mDoBatch = false;
    showProgressHorizontal(paramArrayOfFile.length, 0, true, getString(2131230812, new Object[] { "..." }), true, 0);
    new Thread()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        FileBrowser.this.hideProgressHorizontal();
        FileBrowser.this.UpdateList(FileBrowser.mPath.toString(), false);
      }
    }
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: invokestatic 41	android/os/Looper:prepare	()V
        //   3: new 43	java/util/zip/ZipOutputStream
        //   6: dup
        //   7: new 45	java/io/BufferedOutputStream
        //   10: dup
        //   11: new 47	java/io/FileOutputStream
        //   14: dup
        //   15: aload_0
        //   16: getfield 23	com/jrummy/root/browserfree/FileBrowser$62:val$out_file	Ljava/lang/String;
        //   19: invokespecial 50	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
        //   22: sipush 2048
        //   25: invokespecial 53	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;I)V
        //   28: invokespecial 56	java/util/zip/ZipOutputStream:<init>	(Ljava/io/OutputStream;)V
        //   31: astore_3
        //   32: aload_0
        //   33: getfield 25	com/jrummy/root/browserfree/FileBrowser$62:val$files	[Ljava/io/File;
        //   36: astore 4
        //   38: aload 4
        //   40: arraylength
        //   41: istore_2
        //   42: iconst_0
        //   43: istore_1
        //   44: iload_1
        //   45: iload_2
        //   46: if_icmplt +29 -> 75
        //   49: aload_3
        //   50: invokevirtual 59	java/util/zip/ZipOutputStream:close	()V
        //   53: aload_0
        //   54: getfield 27	com/jrummy/root/browserfree/FileBrowser$62:val$zipHandler	Landroid/os/Handler;
        //   57: iconst_0
        //   58: invokevirtual 65	android/os/Handler:sendEmptyMessage	(I)Z
        //   61: pop
        //   62: return
        //   63: astore_3
        //   64: ldc 67
        //   66: aload_3
        //   67: invokevirtual 71	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
        //   70: invokestatic 77	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   73: pop
        //   74: return
        //   75: aload 4
        //   77: iload_1
        //   78: aaload
        //   79: astore 5
        //   81: aload 5
        //   83: invokevirtual 82	java/io/File:getAbsolutePath	()Ljava/lang/String;
        //   86: astore 6
        //   88: ldc 84
        //   90: new 86	java/lang/StringBuilder
        //   93: dup
        //   94: ldc 88
        //   96: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   99: aload 6
        //   101: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   107: invokestatic 99	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   110: pop
        //   111: aload 5
        //   113: invokevirtual 103	java/io/File:canRead	()Z
        //   116: ifne +36 -> 152
        //   119: ldc 84
        //   121: new 86	java/lang/StringBuilder
        //   124: dup
        //   125: aload 6
        //   127: invokestatic 109	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
        //   130: invokespecial 89	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
        //   133: ldc 111
        //   135: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: invokevirtual 96	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   141: invokestatic 114	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
        //   144: pop
        //   145: iload_1
        //   146: iconst_1
        //   147: iadd
        //   148: istore_1
        //   149: goto -105 -> 44
        //   152: aload 6
        //   154: aload_3
        //   155: ldc 116
        //   157: invokestatic 122	com/jrummy/root/browserfree/util/Helpers:zipFile	(Ljava/lang/String;Ljava/util/zip/ZipOutputStream;Ljava/lang/String;)V
        //   160: aload 5
        //   162: invokevirtual 125	java/io/File:getName	()Ljava/lang/String;
        //   165: invokestatic 128	com/jrummy/root/browserfree/FileBrowser:access$38	(Ljava/lang/String;)V
        //   168: aload_0
        //   169: getfield 21	com/jrummy/root/browserfree/FileBrowser$62:this$0	Lcom/jrummy/root/browserfree/FileBrowser;
        //   172: invokestatic 132	com/jrummy/root/browserfree/FileBrowser:access$27	(Lcom/jrummy/root/browserfree/FileBrowser;)Landroid/os/Handler;
        //   175: iconst_2
        //   176: invokevirtual 65	android/os/Handler:sendEmptyMessage	(I)Z
        //   179: pop
        //   180: goto -35 -> 145
        //   183: astore_3
        //   184: goto -131 -> 53
        //   187: astore 6
        //   189: goto -29 -> 160
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	192	0	this	62
        //   43	106	1	i	int
        //   41	6	2	j	int
        //   31	19	3	localZipOutputStream	java.util.zip.ZipOutputStream
        //   63	92	3	localFileNotFoundException	FileNotFoundException
        //   183	1	3	localIOException1	IOException
        //   36	40	4	arrayOfFile	File[]
        //   79	82	5	localFile	File
        //   86	67	6	str	String
        //   187	1	6	localIOException2	IOException
        // Exception table:
        //   from	to	target	type
        //   3	32	63	java/io/FileNotFoundException
        //   49	53	183	java/io/IOException
        //   152	160	187	java/io/IOException
      }
    }.start();
  }
  
  private static final class DateComparator
    implements Comparator<FileInfoHolder>
  {
    int direction;
    
    DateComparator(int paramInt)
    {
      this.direction = paramInt;
    }
    
    public int compare(FileInfoHolder paramFileInfoHolder1, FileInfoHolder paramFileInfoHolder2)
    {
      if (paramFileInfoHolder1.getFileName().equals("..")) {}
      long l1;
      long l2;
      do
      {
        do
        {
          do
          {
            return 0;
          } while (paramFileInfoHolder2.getFileName().equals(".."));
          l1 = paramFileInfoHolder1.lastUpdateTime();
          l2 = paramFileInfoHolder2.lastUpdateTime();
          if (this.direction != 3) {
            break;
          }
        } while (l1 == l2);
        if (l1 < l2) {
          return -1;
        }
        return 1;
      } while (l1 == l2);
      if (l1 > l2) {
        return -1;
      }
      return 1;
    }
  }
  
  class DoubleTapHomeDetector
    extends GestureDetector.SimpleOnGestureListener
  {
    DoubleTapHomeDetector() {}
    
    public boolean onDoubleTap(MotionEvent paramMotionEvent)
    {
      if (FileBrowser.mTheme == 6) {}
      for (int i = 0;; i = FileBrowser.mTheme + 1)
      {
        FileBrowser.mTheme = i;
        FileBrowser.this.setMyTheme(FileBrowser.mTheme);
        paramMotionEvent = FileBrowser.preferences.edit();
        paramMotionEvent.putInt("theme", FileBrowser.mTheme);
        paramMotionEvent.putString("app_theme", Integer.toString(FileBrowser.mTheme));
        paramMotionEvent.commit();
        FileBrowser.mAdapter.notifyDataSetChanged();
        return false;
      }
    }
    
    public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
    {
      if (FileBrowser.isExploringZip)
      {
        FileBrowser.this.goBackAFolder();
        return false;
      }
      paramMotionEvent = new QuickActionBar(FileBrowser.this);
      ActionItem localActionItem = new ActionItem();
      boolean bool1 = new File(FileBrowser.SD + "/download").exists();
      boolean bool2 = new File(FileBrowser.SD + "/DCIM").exists();
      boolean bool3 = new File(FileBrowser.SD + "/media").exists();
      boolean bool4 = new File(FileBrowser.SD + "/documents").exists();
      localActionItem.setTitle(FileBrowser.this.getString(2131230722));
      localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837540));
      paramMotionEvent.addActionItem(localActionItem);
      final int i2 = 0 + 1;
      localActionItem.setTitle(FileBrowser.this.getString(2131230821));
      localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837537));
      paramMotionEvent.addActionItem(localActionItem);
      final int i3 = i2 + 1;
      localActionItem.setTitle(FileBrowser.this.getString(2131230899));
      localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837590));
      paramMotionEvent.addActionItem(localActionItem);
      final int i4 = i3 + 1;
      localActionItem.setTitle(FileBrowser.this.getString(2131230900));
      localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837552));
      paramMotionEvent.addActionItem(localActionItem);
      final int i = i4 + 1;
      if (bool1)
      {
        localActionItem.setTitle(FileBrowser.this.getString(2131230820));
        localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837583));
        paramMotionEvent.addActionItem(localActionItem);
      }
      final int k;
      final int j;
      label395:
      label449:
      final int m;
      label503:
      final int n;
      if (bool1)
      {
        k = i + 1;
        j = i;
        i = k;
        if (bool2)
        {
          localActionItem.setTitle(FileBrowser.this.getString(2131230823));
          localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837591));
          paramMotionEvent.addActionItem(localActionItem);
        }
        if (!bool2) {
          break label679;
        }
        k = i;
        i += 1;
        if (bool3)
        {
          localActionItem.setTitle(FileBrowser.this.getString(2131230822));
          localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837597));
          paramMotionEvent.addActionItem(localActionItem);
        }
        if (!bool3) {
          break label685;
        }
        m = i;
        i += 1;
        if (bool4)
        {
          localActionItem.setTitle(FileBrowser.this.getString(2131230824));
          localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837588));
          paramMotionEvent.addActionItem(localActionItem);
        }
        if (!bool4) {
          break label691;
        }
        n = i + 1;
      }
      for (;;)
      {
        localActionItem.setTitle(FileBrowser.this.getString(2131230901));
        localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837598));
        paramMotionEvent.addActionItem(localActionItem);
        final int i1 = n + 1;
        localActionItem.setTitle(FileBrowser.this.getString(2131230770));
        localActionItem.setIcon(FileBrowser.this.res.getDrawable(2130837595));
        paramMotionEvent.addActionItem(localActionItem);
        paramMotionEvent.setOnActionItemClickListener(new QuickActionBar.OnActionItemClickListener()
        {
          public void onItemClick(int paramAnonymousInt)
          {
            if (paramAnonymousInt == this.val$database_viewer) {
              FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), DatabaseViewer.class));
            }
            do
            {
              return;
              if (paramAnonymousInt == i2)
              {
                if (FileBrowser.preferences.getBoolean("bookmarks", true))
                {
                  FileBrowser.mDatabaseHelper.insert("Root Folder", "/");
                  FileBrowser.mDatabaseHelper.insert("SD Card", FileBrowser.SD);
                  SharedPreferences.Editor localEditor = FileBrowser.preferences.edit();
                  localEditor.putBoolean("bookmarks", false);
                  localEditor.commit();
                }
                FileBrowser.this.startActivity(new Intent(FileBrowser.this.getApplicationContext(), Bookmarks.class));
                return;
              }
              if (paramAnonymousInt == i3)
              {
                FileBrowser.this.UpdateList("/", true);
                return;
              }
              if (paramAnonymousInt == i4)
              {
                FileBrowser.this.UpdateList(FileBrowser.SD, true);
                return;
              }
              if (paramAnonymousInt == j)
              {
                FileBrowser.this.UpdateList(FileBrowser.SD + "/download", true);
                return;
              }
              if (paramAnonymousInt == k)
              {
                FileBrowser.this.UpdateList(FileBrowser.SD + "/DCIM", true);
                return;
              }
              if (paramAnonymousInt == m)
              {
                FileBrowser.this.UpdateList(FileBrowser.SD + "/media", true);
                return;
              }
              if (paramAnonymousInt == i)
              {
                FileBrowser.this.UpdateList(FileBrowser.SD + "/documents", true);
                return;
              }
              if (paramAnonymousInt == n)
              {
                FileBrowser.this.searchDialog();
                return;
              }
            } while (paramAnonymousInt != i1);
            FileBrowser.this.finish();
          }
        });
        paramMotionEvent.show(FileBrowser.mActionBarHome);
        break;
        j = -1;
        break label395;
        label679:
        k = -1;
        break label449;
        label685:
        m = -1;
        break label503;
        label691:
        i1 = -1;
        n = i;
        i = i1;
      }
    }
  }
  
  private class GetIcons
    extends AsyncTask<FileInfoHolder, Void, Void>
  {
    private GetIcons() {}
    
    protected Void doInBackground(FileInfoHolder... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          return null;
        }
        FileInfoHolder localFileInfoHolder = paramVarArgs[i];
        localFileInfoHolder.setIcon(FileBrowser.this.getFileIcon(localFileInfoHolder, false));
        FileBrowser.this.handler.sendEmptyMessage(8);
        i += 1;
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      FileBrowser.mAdapter.notifyDataSetChanged();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class GetIconsInZip
    extends AsyncTask<FileInfoHolder, Void, Void>
  {
    private GetIconsInZip() {}
    
    protected Void doInBackground(FileInfoHolder... paramVarArgs)
    {
      int j = paramVarArgs.length;
      int i = 0;
      if (i >= j) {
        return null;
      }
      FileInfoHolder localFileInfoHolder = paramVarArgs[i];
      Object localObject;
      if (localFileInfoHolder.isImage())
      {
        localObject = new BitmapDrawable(FileBrowser.getBitmapFromZip(FileBrowser.mZipFile, localFileInfoHolder.getFilePath()));
        if (localObject != null)
        {
          localFileInfoHolder.setIcon((Drawable)localObject);
          FileBrowser.this.handler.sendEmptyMessage(8);
        }
      }
      for (;;)
      {
        i += 1;
        break;
        if (localFileInfoHolder.isApk())
        {
          localObject = FileBrowser.this.getIconFromApkInsideZip(localFileInfoHolder.getZipFile(), localFileInfoHolder.getZipEntry());
          if (localObject != null)
          {
            localFileInfoHolder.setIcon((Drawable)localObject);
            FileBrowser.this.handler.sendEmptyMessage(8);
          }
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      FileBrowser.mAdapter.notifyDataSetChanged();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  private class GetSizes
    extends AsyncTask<FileInfoHolder, Void, Void>
  {
    private GetSizes() {}
    
    protected Void doInBackground(FileInfoHolder... paramVarArgs)
    {
      boolean bool2 = FileBrowser.mPath.canRead();
      CMDProcessor localCMDProcessor = new CMDProcessor();
      int k = paramVarArgs.length;
      int i = 0;
      if (i >= k) {
        return null;
      }
      FileInfoHolder localFileInfoHolder = paramVarArgs[i];
      if ((localFileInfoHolder.getFileName().equals("..")) || (localFileInfoHolder.getSize() != null)) {}
      for (;;)
      {
        i += 1;
        break;
        boolean bool1;
        label77:
        int j;
        if (localFileInfoHolder.isDirectory())
        {
          bool1 = FileBrowser.mShowFolderSize;
          if (!bool2) {
            break label224;
          }
          j = 0;
          label84:
          if (!bool1) {
            break label227;
          }
          if (j == 0) {
            break label229;
          }
        }
        label224:
        label227:
        label229:
        String str2;
        for (String str1 = localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox du -k -s \"" + localFileInfoHolder.getFilePath() + "\"").stdout;; str2 = localCMDProcessor.sh.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox du -k -s \"" + localFileInfoHolder.getFilePath() + "\"").stdout)
        {
          if ((str1 == null) || (localFileInfoHolder.getFileName().equals(".."))) {
            break label270;
          }
          str1 = str1.split("\\s+")[0];
          if ((str1 == null) || (str1.equals(""))) {
            break;
          }
          try
          {
            long l = Long.parseLong(str1.trim()) * 1024L;
            localFileInfoHolder.setFileSize(l);
            localFileInfoHolder.setSize(FileBrowser.this.getSizeStr(l));
          }
          catch (NumberFormatException localNumberFormatException) {}
          break;
          bool1 = true;
          break label77;
          j = 1;
          break label84;
          break;
        }
        label270:
        localFileInfoHolder.setFileSize(0L);
        localFileInfoHolder.setSize("0.00B");
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      FileBrowser.this.updateStorageUsage();
      FileBrowser.this.sortFiles(FileBrowser.mSortType);
      FileBrowser.mAdapter.notifyDataSetChanged();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
  
  public class ListAdapter
    extends BaseAdapter
  {
    private LayoutInflater mInflater;
    
    public ListAdapter(Context paramContext)
    {
      this.mInflater = LayoutInflater.from(paramContext);
    }
    
    public int getCount()
    {
      return FileBrowser.mFiles.size();
    }
    
    public Object getItem(int paramInt)
    {
      return FileBrowser.mFiles.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(2130903055, null);
        paramViewGroup = new ViewHolder();
        paramViewGroup.mFileName = ((TextView)paramView.findViewById(2131361922));
        paramViewGroup.mFileInfo = ((TextView)paramView.findViewById(2131361923));
        paramViewGroup.mIcon = ((ImageView)paramView.findViewById(2131361920));
        paramViewGroup.mCheckbox = ((CheckBox)paramView.findViewById(2131361843));
        paramView.setTag(paramViewGroup);
      }
      for (;;)
      {
        FileInfoHolder localFileInfoHolder = (FileInfoHolder)FileBrowser.mFiles.get(paramInt);
        boolean bool2 = localFileInfoHolder.getFileName().equals("..");
        String str;
        label116:
        StringBuilder localStringBuilder;
        label138:
        Drawable localDrawable;
        Typeface localTypeface3;
        Typeface localTypeface1;
        if (bool2)
        {
          str = "..";
          localStringBuilder = new StringBuilder();
          if (!bool2) {
            break label266;
          }
          localStringBuilder.append("Parent folder");
          localDrawable = localFileInfoHolder.getIcon();
          localTypeface3 = FileBrowser.SUB_FONT;
          localTypeface1 = localTypeface3;
          if (!localFileInfoHolder.isFont()) {}
        }
        try
        {
          localTypeface1 = Typeface.createFromFile(localFileInfoHolder.getFilePath());
          paramViewGroup.setFileName(str, localTypeface1);
          paramViewGroup.setFileInfo(localStringBuilder.toString());
          paramViewGroup.setIcon(localDrawable, localFileInfoHolder);
          paramViewGroup.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
          {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
            {
              ((FileInfoHolder)FileBrowser.mFiles.get(paramInt)).setIsChecked(paramAnonymousBoolean);
            }
          });
          boolean bool1;
          if (bool2)
          {
            bool1 = false;
            label221:
            if ((bool2) || (!FileBrowser.isInSelectionMode)) {
              break label445;
            }
          }
          label266:
          label445:
          for (bool2 = true;; bool2 = false)
          {
            paramViewGroup.setCheck(bool2, bool1);
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
            break;
            str = localFileInfoHolder.getFileName();
            break label116;
            localStringBuilder.append(localFileInfoHolder.getDate());
            if (FileBrowser.mIsRootUser)
            {
              if (localFileInfoHolder.getPermissions() != null) {
                localStringBuilder.append(" | " + FileBrowser.getPermStr(localFileInfoHolder.getPermissions()));
              }
              if (localFileInfoHolder.isSymlink()) {
                localStringBuilder.append(" -> " + localFileInfoHolder.getSymlink());
              }
            }
            if ((localFileInfoHolder.getSize() == null) || (((!localFileInfoHolder.isDirectory()) || (!FileBrowser.mShowFolderSize)) && ((!FileBrowser.isExploringZip) || (localFileInfoHolder.isDirectory())) && ((localFileInfoHolder.isDirectory()) || (localFileInfoHolder.isSymlink())))) {
              break label138;
            }
            localStringBuilder.append(" | " + localFileInfoHolder.getSize());
            break label138;
            bool1 = localFileInfoHolder.isChecked();
            break label221;
          }
        }
        catch (RuntimeException localRuntimeException)
        {
          for (;;)
          {
            Typeface localTypeface2 = localTypeface3;
          }
        }
      }
    }
    
    public void setListItems(List<FileInfoHolder> paramList)
    {
      FileBrowser.mFiles = paramList;
    }
    
    public class ViewHolder
    {
      private CheckBox mCheckbox;
      private TextView mFileInfo;
      private TextView mFileName;
      private ImageView mIcon;
      
      public ViewHolder() {}
      
      public void setCheck(boolean paramBoolean1, boolean paramBoolean2)
      {
        CheckBox localCheckBox = this.mCheckbox;
        int i;
        if (paramBoolean1)
        {
          i = 0;
          localCheckBox.setVisibility(i);
          localCheckBox = this.mCheckbox;
          if (!paramBoolean1) {
            break label41;
          }
        }
        for (;;)
        {
          localCheckBox.setChecked(paramBoolean2);
          return;
          i = 8;
          break;
          label41:
          paramBoolean2 = false;
        }
      }
      
      public void setFileInfo(String paramString)
      {
        TextView localTextView = this.mFileInfo;
        if (FileBrowser.mShowInfo) {}
        for (int i = 0;; i = 8)
        {
          localTextView.setVisibility(i);
          this.mFileInfo.setText(paramString);
          this.mFileInfo.setTextColor(FileBrowser.mTextColor);
          this.mFileName.setTypeface(FileBrowser.SUB_FONT);
          return;
        }
      }
      
      public void setFileName(String paramString, Typeface paramTypeface)
      {
        this.mFileName.setText(paramString);
        this.mFileName.setTypeface(paramTypeface);
        this.mFileName.setTextColor(FileBrowser.mTextColor);
      }
      
      public void setIcon(Drawable paramDrawable, FileInfoHolder paramFileInfoHolder)
      {
        Drawable localDrawable = paramDrawable;
        if (paramDrawable == null)
        {
          localDrawable = FileBrowser.this.getFileIcon(paramFileInfoHolder, true);
          paramFileInfoHolder.setIcon(localDrawable);
        }
        this.mIcon.setImageDrawable(localDrawable);
      }
    }
  }
  
  private static final class NameComparator
    implements Comparator<FileInfoHolder>
  {
    int direction;
    
    NameComparator(int paramInt)
    {
      this.direction = paramInt;
    }
    
    public int compare(FileInfoHolder paramFileInfoHolder1, FileInfoHolder paramFileInfoHolder2)
    {
      paramFileInfoHolder1 = paramFileInfoHolder1.getFileName().toLowerCase();
      paramFileInfoHolder2 = paramFileInfoHolder2.getFileName().toLowerCase();
      if (paramFileInfoHolder1.equals("..")) {}
      while (paramFileInfoHolder2.equals("..")) {
        return 0;
      }
      if (this.direction == 1) {
        return paramFileInfoHolder2.compareTo(paramFileInfoHolder1);
      }
      return paramFileInfoHolder1.compareTo(paramFileInfoHolder2);
    }
  }
  
  private class RunCommand
    extends AsyncTask<FileInfoHolder, Void, Integer>
  {
    private RunCommand() {}
    
    protected Integer doInBackground(FileInfoHolder... paramVarArgs)
    {
      CMDProcessor localCMDProcessor = new CMDProcessor();
      File localFile1 = FileBrowser.mPath;
      int k = paramVarArgs.length;
      Object localObject1 = null;
      if (FileBrowser.mIsRootUser)
      {
        Helpers.getMount("rw");
        if (FileBrowser.isRootDir) {
          Helpers.getRootMount("rw");
        }
      }
      int m = paramVarArgs.length;
      int i = 0;
      if (i >= m)
      {
        if (FileBrowser.mIsRootUser)
        {
          Helpers.getMount("ro");
          if (FileBrowser.isRootDir) {
            Helpers.getRootMount("ro");
          }
        }
        if ((localObject1 == null) || (k != 1)) {
          break label2008;
        }
        i = 1;
        label94:
        if (i == 0) {
          break label2013;
        }
      }
      for (;;)
      {
        FileBrowser.mToastMsg = (String)localObject1;
        return Integer.valueOf(k);
        FileInfoHolder localFileInfoHolder = paramVarArgs[i];
        File localFile2;
        if (FileBrowser.mCmd.equals("do_copy"))
        {
          localFile2 = new File(localFileInfoHolder.getFilePath());
          Object localObject2 = new File(localFile1.getAbsolutePath(), localFileInfoHolder.getFileName());
          localObject1 = localObject2;
          if (localFile2.getAbsolutePath().equals(((File)localObject2).getAbsolutePath())) {
            localObject1 = FileBrowser.copyFile(localFile1.getAbsolutePath() + "/" + localFileInfoHolder.getFileName(), "Copy");
          }
          bool2 = false;
          if (FileBrowser.mIsRootUser)
          {
            bool1 = bool2;
            if (localFile2.canRead())
            {
              bool1 = bool2;
              if (!localFile1.canWrite()) {}
            }
          }
          else
          {
            if (!localFileInfoHolder.isDirectory()) {
              break label593;
            }
          }
          try
          {
            FileBrowser.this.copyDirectory(localFile2, (File)localObject1);
            bool1 = true;
          }
          catch (IOException localIOException2)
          {
            for (;;)
            {
              label271:
              bool1 = false;
            }
          }
          bool2 = bool1;
          if (!bool1)
          {
            bool2 = bool1;
            if (FileBrowser.mIsRootUser)
            {
              if (!localFileInfoHolder.isDirectory()) {
                break label605;
              }
              localObject2 = "-R";
              label302:
              localObject1 = "/data/data/com.jrummy.root.browserfree/files/busybox cp -f " + (String)localObject2 + " \"" + localFile2 + "\" \"" + localObject1 + "\"";
              Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
              bool2 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
            }
          }
          localObject1 = FileBrowser.this;
          if (bool2)
          {
            j = 2131230868;
            label405:
            localObject1 = ((FileBrowser)localObject1).getString(j, new Object[] { localFileInfoHolder.getFileName() });
          }
        }
        label593:
        label605:
        label990:
        label1111:
        do
        {
          if (FileBrowser.mPbarLayout.getVisibility() == 0)
          {
            FileBrowser.mProgressImage = localFileInfoHolder.getIcon();
            FileBrowser.mProgressMsg = localFileInfoHolder.getFileName();
            FileBrowser.this.handler.sendEmptyMessage(2);
            if (FileBrowser.mShowNotifications)
            {
              FileBrowser.progress += 1;
              FileBrowser.this.notification.contentView.setProgressBar(2131361833, k, FileBrowser.progress, false);
              FileBrowser.this.notification.contentView.setTextViewText(2131361831, FileBrowser.progress + "/" + k);
              FileBrowser.this.notification.contentView.setTextViewText(2131361832, localFileInfoHolder.getFileName());
              FileBrowser.this.notificationManager.notify(1820, FileBrowser.this.notification);
            }
          }
          i += 1;
          break;
          bool1 = FileBrowser.copyFile(localFile2, (File)localObject1);
          break label271;
          Object localObject3 = "";
          break label302;
          j = 2131230862;
          break label405;
          if (FileBrowser.mCmd.equals("do_move"))
          {
            localFile2 = new File(localFileInfoHolder.getFilePath());
            localObject3 = new File(localFile1.getAbsolutePath(), localFileInfoHolder.getFileName());
            localObject1 = localObject3;
            if (localFile2.getAbsolutePath().equals(((File)localObject3).getAbsolutePath())) {
              localObject1 = FileBrowser.copyFile(localFile1.getAbsolutePath() + "/" + localFileInfoHolder.getFileName(), "Copy");
            }
            bool2 = false;
            if (FileBrowser.mIsRootUser)
            {
              bool1 = bool2;
              if (FileBrowser.isOnSD(localFile2))
              {
                bool1 = bool2;
                if (!FileBrowser.isOnSD((File)localObject1)) {}
              }
            }
            else
            {
              bool1 = localFile2.renameTo((File)localObject1);
            }
            bool2 = bool1;
            if (FileBrowser.mIsRootUser)
            {
              bool2 = bool1;
              if (!bool1)
              {
                localObject1 = "/data/data/com.jrummy.root.browserfree/files/busybox mv -f \"" + localFile2 + "\" \"" + localObject1 + "\"";
                Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
                bool2 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
              }
            }
            localObject1 = FileBrowser.this;
            if (bool2) {}
            for (j = 2131230869;; j = 2131230863)
            {
              localObject1 = ((FileBrowser)localObject1).getString(j, new Object[] { localFileInfoHolder.getFileName() });
              break;
            }
          }
          if (FileBrowser.mCmd.equals("do_delete"))
          {
            localObject1 = new File(localFileInfoHolder.getFilePath());
            bool1 = false;
            if ((!FileBrowser.mIsRootUser) || (FileBrowser.isOnSD((File)localObject1))) {
              bool1 = FileBrowser.deleteFile((File)localObject1);
            }
            bool2 = bool1;
            if (FileBrowser.mIsRootUser)
            {
              bool2 = bool1;
              if (!bool1)
              {
                if (!localFileInfoHolder.isDirectory()) {
                  break label1111;
                }
                localObject1 = "-R";
                localObject1 = "/data/data/com.jrummy.root.browserfree/files/busybox rm -f " + (String)localObject1 + " \"" + localFileInfoHolder.getFilePath() + "\"";
                Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
                bool2 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
              }
            }
            localObject1 = FileBrowser.this;
            if (bool2) {}
            for (j = 2131230870;; j = 2131230864)
            {
              localObject1 = ((FileBrowser)localObject1).getString(j, new Object[] { localFileInfoHolder.getFileName() });
              break;
              localObject1 = "";
              break label990;
            }
          }
          if (FileBrowser.mCmd.equals("do_extract"))
          {
            bool2 = false;
            bool1 = bool2;
            if (localFile1.canWrite()) {
              Log.i("FileBrowser", "EXTRACT - starting extract");
            }
          }
          try
          {
            Helpers.extractFolder(localFileInfoHolder.getFilePath(), FileBrowser.mExtractLocation);
            bool1 = true;
          }
          catch (IOException localIOException1)
          {
            for (;;)
            {
              bool1 = bool2;
            }
          }
          catch (ZipException localZipException)
          {
            for (;;)
            {
              bool1 = bool2;
            }
          }
          bool2 = bool1;
          if (!bool1)
          {
            bool2 = bool1;
            if (FileBrowser.mIsRootUser)
            {
              localObject1 = "/data/data/com.jrummy.root.browserfree/files/busybox unzip -o \"" + localFileInfoHolder.getFilePath() + "\" -d \"" + FileBrowser.mExtractLocation + "/\"";
              Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
              localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mkdir -p \"" + FileBrowser.mExtractLocation + "\"");
              bool1 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
              bool2 = bool1;
              if (!bool1)
              {
                localObject1 = "busybox unzip -o \"" + localFileInfoHolder.getFilePath() + "\" -d \"" + FileBrowser.mExtractLocation + "/\"";
                Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
                localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mkdir -p \"" + FileBrowser.mExtractLocation + "\"");
                bool2 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
              }
            }
          }
          localObject1 = FileBrowser.this;
          if (bool2) {}
          for (j = 2131230872;; j = 2131230866)
          {
            localObject1 = ((FileBrowser)localObject1).getString(j, new Object[] { localFileInfoHolder.getFileName() });
            break;
          }
          if (FileBrowser.mCmd.equals("do_zip_file"))
          {
            bool1 = false;
            localObject1 = new File(localFileInfoHolder.getFilePath());
            localObject3 = new File(FileBrowser.mZipLocation, localFileInfoHolder.getFileName() + ".zip");
            if ((!FileBrowser.mIsRootUser) || (FileBrowser.isOnSD((File)localObject1))) {
              bool1 = Helpers.createZipFile(localFileInfoHolder.getFilePath(), true, ((File)localObject3).getAbsolutePath());
            }
            bool2 = bool1;
            if (!bool1)
            {
              bool2 = bool1;
              if (FileBrowser.mIsRootUser)
              {
                if ((!FileBrowser.mZipLocation.exists()) && (!FileBrowser.mZipLocation.mkdirs())) {
                  localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mkdir -p " + FileBrowser.mZipLocation.getAbsolutePath());
                }
                localObject1 = "/data/data/com.jrummy.root.browserfree/files/zip -r \"" + ((File)localObject3).getAbsolutePath() + "\" \"" + localFileInfoHolder.getFilePath() + "\"";
                Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
                localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mkdir -p \"" + FileBrowser.mExtractLocation + "\"");
                bool2 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
              }
            }
            localObject1 = FileBrowser.this;
            if (bool2) {}
            for (j = 2131230871;; j = 2131230865)
            {
              localObject1 = ((FileBrowser)localObject1).getString(j, new Object[] { localFileInfoHolder.getFileName() });
              break;
            }
          }
        } while (!FileBrowser.mCmd.equals("do_tar"));
        localObject1 = "/data/data/com.jrummy.root.browserfree/files/busybox tar -czf \"" + FileBrowser.mZipLocation + "/" + localFileInfoHolder.getFileName() + ".tar.gz\"" + " \"" + localFileInfoHolder.getFilePath() + "\"";
        Helpers.writeNewFile(Helpers.TMP_SCRIPT.getAbsolutePath(), (String)localObject1);
        localCMDProcessor.su.runWaitFor("/data/data/com.jrummy.root.browserfree/files/busybox mkdir -p \"" + FileBrowser.mExtractLocation + "\"");
        bool1 = localCMDProcessor.su.runWaitFor("sh " + Helpers.TMP_SCRIPT.getAbsolutePath()).success();
        localObject1 = FileBrowser.this;
        if (bool1) {}
        for (int j = 2131230871;; j = 2131230867)
        {
          localObject1 = ((FileBrowser)localObject1).getString(j, new Object[] { localFileInfoHolder.getFileName() });
          break;
        }
        label2008:
        i = 0;
        break label94;
        label2013:
        localObject1 = null;
      }
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      if (FileBrowser.mCmd.equals("do_copy")) {
        if ((FileBrowser.mPbarLayout.getVisibility() == 0) && (FileBrowser.mShowNotifications))
        {
          FileBrowser.mNotificationTitle = FileBrowser.this.getString(2131230792);
          FileBrowser.mTickerMsg = FileBrowser.this.getString(2131230794, new Object[] { FileBrowser.this.getString(2131230720) });
          FileBrowser.mNotificationMsg = FileBrowser.this.getString(2131230793, new Object[] { paramInteger });
          FileBrowser.this.notificationManager.cancel(1820);
        }
      }
      for (;;)
      {
        FileBrowser.this.updateStorageUsage();
        FileBrowser.mAdapter.notifyDataSetChanged();
        FileBrowser.mCmdFile = null;
        FileBrowser.this.handler.sendEmptyMessage(6);
        FileBrowser.this.handler.sendEmptyMessage(0);
        return;
        if (FileBrowser.mToastMsg == null)
        {
          FileBrowser.mToastMsg = FileBrowser.this.getString(2131230792);
          continue;
          if (FileBrowser.mCmd.equals("do_move"))
          {
            if ((FileBrowser.mPbarLayout.getVisibility() == 0) && (FileBrowser.mShowNotifications))
            {
              FileBrowser.mNotificationTitle = FileBrowser.this.getString(2131230796);
              FileBrowser.mTickerMsg = FileBrowser.this.getString(2131230798, new Object[] { FileBrowser.this.getString(2131230720) });
              FileBrowser.mNotificationMsg = FileBrowser.this.getString(2131230797, new Object[] { paramInteger });
              FileBrowser.this.notificationManager.cancel(1820);
            }
            else if (FileBrowser.mToastMsg == null)
            {
              FileBrowser.mToastMsg = FileBrowser.this.getString(2131230796);
            }
          }
          else if (FileBrowser.mCmd.equals("do_delete"))
          {
            if ((FileBrowser.mPbarLayout.getVisibility() == 0) && (FileBrowser.mShowNotifications))
            {
              FileBrowser.mNotificationTitle = FileBrowser.this.getString(2131230800);
              FileBrowser.mTickerMsg = FileBrowser.this.getString(2131230802, new Object[] { FileBrowser.this.getString(2131230720) });
              FileBrowser.mNotificationMsg = FileBrowser.this.getString(2131230801, new Object[] { paramInteger });
              FileBrowser.this.notificationManager.cancel(1820);
            }
            else if (FileBrowser.mToastMsg == null)
            {
              FileBrowser.mToastMsg = FileBrowser.this.getString(2131230800);
            }
          }
          else if ((FileBrowser.mCmd.equals("do_zip_file")) || (FileBrowser.mCmd.equals("do_tar"))) {
            if ((FileBrowser.mPbarLayout.getVisibility() == 0) && (FileBrowser.mShowNotifications))
            {
              FileBrowser.mNotificationTitle = FileBrowser.this.getString(2131230804);
              FileBrowser.mTickerMsg = FileBrowser.this.getString(2131230806, new Object[] { FileBrowser.this.getString(2131230720) });
              FileBrowser.mNotificationMsg = FileBrowser.this.getString(2131230805, new Object[] { paramInteger });
              FileBrowser.this.notificationManager.cancel(1820);
            }
            else if (FileBrowser.mToastMsg == null)
            {
              FileBrowser.mToastMsg = FileBrowser.this.getString(2131230804);
            }
          }
        }
      }
    }
    
    protected void onPreExecute()
    {
      Log.i("FileBrowser", "Running command: " + FileBrowser.mCmd);
      super.onPreExecute();
    }
  }
  
  private static final class SizeComparator
    implements Comparator<FileInfoHolder>
  {
    int direction;
    
    SizeComparator(int paramInt)
    {
      this.direction = paramInt;
    }
    
    public int compare(FileInfoHolder paramFileInfoHolder1, FileInfoHolder paramFileInfoHolder2)
    {
      if (paramFileInfoHolder1.getFileName().equals("..")) {}
      long l1;
      long l2;
      do
      {
        do
        {
          do
          {
            return 0;
          } while (paramFileInfoHolder2.getFileName().equals(".."));
          l1 = paramFileInfoHolder1.fileSize();
          l2 = paramFileInfoHolder2.fileSize();
          if (this.direction != 5) {
            break;
          }
        } while (l1 == l2);
        if (l1 < l2) {
          return -1;
        }
        return 1;
      } while (l1 == l2);
      if (l1 > l2) {
        return -1;
      }
      return 1;
    }
  }
}
