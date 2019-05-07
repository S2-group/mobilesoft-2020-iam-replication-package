package co.madseven.launcher.shortcuts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import co.madseven.launcher.http.smartfolders.ISmartFolders;
import co.madseven.launcher.http.smartfolders.SmartFoldersService;
import co.madseven.launcher.http.smartfolders.beans.Category;
import co.madseven.launcher.http.smartfolders.beans.SmartFoldersResponse;
import co.madseven.launcher.settings.preferences.Preferences;
import co.madseven.launcher.shortcuts.listeners.OnCustomShortcutsParserListener;
import co.madseven.launcher.shortcuts.objects.CustomShortcutsParser;
import co.madseven.launcher.tracking.ApoloTracker;
import co.madseven.launcher.utils.Utils;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.IconCache;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.LauncherModel;
import com.android.launcher3.ShortcutInfo;
import com.android.launcher3.Utilities;
import com.android.launcher3.Workspace;
import com.android.launcher3.compat.UserHandleCompat;
import com.android.launcher3.folder.FolderIcon;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartFolderManager
{
  public static String TAG = "SmartFolderManager";
  private static String mCurrentParsingRef = "";
  private static long mCurrentScreenId = -1L;
  boolean mCellFound = false;
  private int[] mCurrentCellXY = new int[2];
  private CellLayout mCurrentLayout;
  private JSONObject mDeviceInfo;
  private Handler mHandler = new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage) {}
  };
  Runnable mInstallShortcutRunnable = new Runnable()
  {
    public void run()
    {
      Object localObject;
      StringBuilder localStringBuilder;
      if ((!SmartFolderManager.this.mLauncher.isWorkspaceLocked()) && (!SmartFolderManager.this.mLauncher.isWorkspaceLoading()))
      {
        if (SmartFolderManager.this.mSortedShortcuts.size() > 0)
        {
          if (SmartFolderManager.mCurrentScreenId == -1L)
          {
            SmartFolderManager.this.mLauncher.getWorkspace().addExtraEmptyScreen();
            SmartFolderManager.access$1002(SmartFolderManager.this, SmartFolderManager.this.mLauncher.getWorkspace().getScreenWithId(-201L));
            SmartFolderManager.access$902(SmartFolderManager.this.mLauncher.getWorkspace().commitExtraEmptyScreen());
            if ((SmartFolderManager.mCurrentScreenId == -1L) && (SmartFolderManager.this.onCompletion != null))
            {
              SmartFolderManager.access$702(SmartFolderManager.this, false);
              SmartFolderManager.this.onCompletion.run();
              return;
            }
          }
          localObject = (SmartFolderManager.Shortcut)SmartFolderManager.this.mSortedShortcuts.peek();
          if (((SmartFolderManager.Shortcut)localObject).cat == null) {
            ((SmartFolderManager.Shortcut)localObject).cat = "";
          }
          if ((!((SmartFolderManager.Shortcut)localObject).cat.equals(SmartFolderManager.this.mLastCategory)) && (SmartFolderManager.this.mCurrentLayout != null))
          {
            SmartFolderManager.this.mCellFound = SmartFolderManager.this.mCurrentLayout.findCellForSpan(SmartFolderManager.this.mCurrentCellXY, 1, 1);
            if (!SmartFolderManager.this.mCellFound)
            {
              SmartFolderManager.access$902(SmartFolderManager.this.createScreenIfNeeded(SmartFolderManager.mCurrentScreenId + 1L));
              SmartFolderManager.access$1002(SmartFolderManager.this, SmartFolderManager.this.mLauncher.getWorkspace().getScreenWithId(SmartFolderManager.mCurrentScreenId));
              if (SmartFolderManager.this.mCurrentLayout != null) {
                SmartFolderManager.this.mCellFound = SmartFolderManager.this.mCurrentLayout.findCellForSpan(SmartFolderManager.this.mCurrentCellXY, 1, 1);
              }
            }
          }
          if (SmartFolderManager.this.mCellFound)
          {
            SmartFolderManager.this.mSortedShortcuts.poll();
            SmartFolderManager.this.createShortcutWithFolderIfNecessary(SmartFolderManager.mCurrentScreenId, SmartFolderManager.this.mCurrentCellXY, ((SmartFolderManager.Shortcut)localObject).pkg, ((SmartFolderManager.Shortcut)localObject).cat, new Runnable()
            {
              public void run()
              {
                SmartFolderManager.this.mLastCategory = this.val$s.cat;
                SmartFolderManager.this.mHandler.post(SmartFolderManager.this.mInstallShortcutRunnable);
              }
            });
            return;
          }
          if (SmartFolderManager.this.mNbRetry < 3)
          {
            SmartFolderManager.this.mHandler.postDelayed(SmartFolderManager.this.mInstallShortcutRunnable, 1000L);
            SmartFolderManager.access$1608(SmartFolderManager.this);
            localObject = SmartFolderManager.TAG;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Could not find a place, retry(");
            localStringBuilder.append(SmartFolderManager.this.mNbRetry);
            localStringBuilder.append(") in 1s");
            Log.d((String)localObject, localStringBuilder.toString());
            return;
          }
          SmartFolderManager.access$1602(SmartFolderManager.this, 0);
          if (SmartFolderManager.this.onCompletion != null)
          {
            SmartFolderManager.access$702(SmartFolderManager.this, false);
            SmartFolderManager.this.onCompletion.run();
          }
        }
        else if (SmartFolderManager.this.onCompletion != null)
        {
          SmartFolderManager.access$1602(SmartFolderManager.this, 0);
          SmartFolderManager.access$702(SmartFolderManager.this, false);
          SmartFolderManager.this.onCompletion.run();
        }
      }
      else
      {
        if (SmartFolderManager.this.mNbRetry < 5)
        {
          SmartFolderManager.this.mHandler.postDelayed(SmartFolderManager.this.mInstallShortcutRunnable, 1000L);
          SmartFolderManager.access$1608(SmartFolderManager.this);
          localObject = SmartFolderManager.TAG;
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Workspace not yet ready retry(");
          localStringBuilder.append(SmartFolderManager.this.mNbRetry);
          localStringBuilder.append(") in 1s");
          Log.d((String)localObject, localStringBuilder.toString());
          return;
        }
        SmartFolderManager.access$1602(SmartFolderManager.this, 0);
        if (SmartFolderManager.this.onCompletion != null)
        {
          SmartFolderManager.access$702(SmartFolderManager.this, false);
          SmartFolderManager.this.onCompletion.run();
        }
      }
    }
  };
  private JSONArray mInstalledPackages;
  String mLastCategory;
  private Launcher mLauncher;
  private JSONArray mLauncherApps;
  private int mNbRetry = 0;
  private PackageManager mPackageManager;
  private boolean mRequesting = false;
  private SharedPreferences mSharedPrefs;
  HashMap<String, ArrayList<String>> mShortcutsByCategory = new HashMap();
  private Queue<Shortcut> mSortedShortcuts = new LinkedList();
  private Runnable onCompletion;
  
  public SmartFolderManager(Launcher paramLauncher)
  {
    this.mLauncher = paramLauncher;
    this.mPackageManager = paramLauncher.getPackageManager();
    this.mSharedPrefs = Utilities.getPrefs(this.mLauncher);
  }
  
  private long createScreenIfNeeded(long paramLong)
  {
    return this.mLauncher.ensurePendingDropLayoutExists(paramLong);
  }
  
  private void createShortcut(long paramLong, int[] paramArrayOfInt, String paramString)
  {
    ItemInfo localItemInfo = new ItemInfo();
    localItemInfo.container = -100L;
    localItemInfo.screenId = paramLong;
    localItemInfo.cellX = paramArrayOfInt[0];
    localItemInfo.cellY = paramArrayOfInt[1];
    localItemInfo.spanX = 1;
    localItemInfo.spanY = 1;
    localItemInfo.itemType = 1;
    Intent localIntent = this.mPackageManager.getLaunchIntentForPackage(paramString);
    if (localIntent != null) {}
    for (;;)
    {
      try
      {
        paramArrayOfInt = this.mPackageManager.getApplicationInfo(paramString, 128);
        paramString = paramArrayOfInt.loadLabel(this.mPackageManager).toString();
        paramArrayOfInt = paramArrayOfInt.loadIcon(this.mPackageManager);
        IconCache localIconCache = LauncherAppState.getInstance(this.mLauncher).getIconCache();
        if (paramArrayOfInt == null) {
          paramArrayOfInt = localIconCache.getDefaultIcon(UserHandleCompat.myUserHandle());
        } else {
          paramArrayOfInt = Utilities.createScaledBitmapWithoutShadow(paramArrayOfInt, this.mLauncher.getApplicationContext());
        }
      }
      catch (Exception paramArrayOfInt)
      {
        Log.e(TAG, "NameNotFoundException");
        paramArrayOfInt.printStackTrace();
      }
      paramArrayOfInt = new ShortcutInfo(localIntent, paramString, "", paramArrayOfInt, localItemInfo.user);
      paramArrayOfInt.copyFrom(localItemInfo);
      paramArrayOfInt.screenId = localItemInfo.screenId;
      LauncherModel.addItemToDatabase(this.mLauncher, paramArrayOfInt, paramArrayOfInt.container, paramArrayOfInt.screenId, paramArrayOfInt.cellX, paramArrayOfInt.cellY);
      paramString = this.mLauncher.createShortcut(paramArrayOfInt);
      this.mLauncher.getWorkspace().addInScreenFromBind(paramString, paramArrayOfInt.container, paramArrayOfInt.screenId, paramArrayOfInt.cellX, paramArrayOfInt.cellY, paramArrayOfInt.spanX, paramArrayOfInt.spanY);
      return;
      return;
    }
  }
  
  private void createShortcutWithFolderIfNecessary(long paramLong, final int[] paramArrayOfInt, String paramString1, String paramString2, final Runnable paramRunnable)
  {
    ItemInfo localItemInfo = new ItemInfo();
    localItemInfo.container = -100L;
    localItemInfo.screenId = paramLong;
    localItemInfo.cellX = paramArrayOfInt[0];
    localItemInfo.cellY = paramArrayOfInt[1];
    localItemInfo.spanX = 1;
    localItemInfo.spanY = 1;
    localItemInfo.itemType = 1;
    Object localObject2 = this.mPackageManager.getLaunchIntentForPackage(paramString1);
    if (localObject2 != null) {}
    for (;;)
    {
      try
      {
        localObject1 = this.mPackageManager.getApplicationInfo(paramString1, 128);
        localObject3 = ((ApplicationInfo)localObject1).loadLabel(this.mPackageManager).toString();
        localObject1 = ((ApplicationInfo)localObject1).loadIcon(this.mPackageManager);
        IconCache localIconCache = LauncherAppState.getInstance(this.mLauncher).getIconCache();
        if (localObject1 == null) {
          localObject1 = localIconCache.getDefaultIcon(UserHandleCompat.myUserHandle());
        } else {
          localObject1 = Utilities.createScaledBitmapWithoutShadow((Drawable)localObject1, this.mLauncher.getApplicationContext());
        }
      }
      catch (Exception paramArrayOfInt)
      {
        Object localObject1;
        Object localObject3;
        Log.e(TAG, "NameNotFoundException");
        paramArrayOfInt.printStackTrace();
        if (paramRunnable == null) {
          break label615;
        }
      }
      localObject1 = new ShortcutInfo((Intent)localObject2, (CharSequence)localObject3, "", (Bitmap)localObject1, UserHandleCompat.myUserHandle());
      ((ShortcutInfo)localObject1).copyFrom(localItemInfo);
      ((ShortcutInfo)localObject1).screenId = paramLong;
      localObject3 = this.mLauncher.getWorkspace().getScreenWithId(((ShortcutInfo)localObject1).screenId);
      localObject2 = ((CellLayout)localObject3).getChildAt(localItemInfo.cellX, localItemInfo.cellY);
      if ((localObject2 instanceof FolderIcon))
      {
        paramArrayOfInt = (FolderIcon)localObject2;
        ((ShortcutInfo)localObject1).cellX = -1;
        ((ShortcutInfo)localObject1).cellY = -1;
        paramArrayOfInt.addItem((ShortcutInfo)localObject1);
        LauncherModel.addItemToDatabase(this.mLauncher, (ItemInfo)localObject1, ((ShortcutInfo)localObject1).container, ((ShortcutInfo)localObject1).screenId, ((ShortcutInfo)localObject1).cellX, ((ShortcutInfo)localObject1).cellY);
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
      else if ((localObject2 instanceof BubbleTextView))
      {
        paramString1 = (ShortcutInfo)((View)localObject2).getTag();
        ((CellLayout)localObject3).removeView((View)localObject2);
        if ((paramString2 != null) && (paramString2.length() > 0)) {
          paramArrayOfInt = this.mLauncher.addFolderWithTitle((CellLayout)localObject3, ((ShortcutInfo)localObject1).container, ((ShortcutInfo)localObject1).screenId, ((ShortcutInfo)localObject1).cellX, ((ShortcutInfo)localObject1).cellY, paramString2);
        } else {
          paramArrayOfInt = this.mLauncher.addFolder((CellLayout)localObject3, ((ShortcutInfo)localObject1).container, ((ShortcutInfo)localObject1).screenId, ((ShortcutInfo)localObject1).cellX, ((ShortcutInfo)localObject1).cellY);
        }
        paramString1.cellX = -1;
        paramString1.cellY = -1;
        ((ShortcutInfo)localObject1).cellX = -1;
        ((ShortcutInfo)localObject1).cellY = -1;
        paramArrayOfInt.prepareCreate((View)localObject2);
        paramArrayOfInt.addItem(paramString1);
        paramArrayOfInt.addItem((ShortcutInfo)localObject1);
        LauncherModel.addOrMoveItemInDatabase(this.mLauncher, paramString1, paramArrayOfInt.getFolderInfo().id, paramArrayOfInt.getFolderInfo().screenId, -1, -1);
        LauncherModel.addItemToDatabase(this.mLauncher, (ItemInfo)localObject1, paramArrayOfInt.getFolderInfo().id, paramArrayOfInt.getFolderInfo().screenId, -1, -1);
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
      else
      {
        createShortcut(((ShortcutInfo)localObject1).screenId, paramArrayOfInt, paramString1);
        paramArrayOfInt = ((CellLayout)localObject3).getChildAt(localItemInfo.cellX, localItemInfo.cellY);
        if (paramArrayOfInt != null)
        {
          paramArrayOfInt.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
          {
            public void onGlobalLayout()
            {
              paramArrayOfInt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
              if (paramRunnable != null) {
                paramRunnable.run();
              }
            }
          });
          return;
        }
        if (paramRunnable != null)
        {
          paramRunnable.run();
          return;
          paramRunnable.run();
          return;
          if (paramRunnable != null) {
            paramRunnable.run();
          }
        }
      }
      label615:
      return;
    }
  }
  
  private void findAffiliateSubstitute(ItemInfo paramItemInfo)
  {
    if ((paramItemInfo instanceof ShortcutInfo))
    {
      Object localObject = (ShortcutInfo)paramItemInfo;
      paramItemInfo = ((ShortcutInfo)localObject).findAndReplacePkg;
      if ((paramItemInfo != null) && (((ShortcutInfo)localObject).intent != null) && ("android.intent.action.VIEW".equals(((ShortcutInfo)localObject).intent.getAction())) && (((ShortcutInfo)localObject).intent.getData() != null))
      {
        localObject = ((ShortcutInfo)localObject).intent.getData().toString();
        if (Patterns.WEB_URL.matcher((CharSequence)localObject).matches())
        {
          SharedPreferences.Editor localEditor = Preferences.getInstance().getPrefs(this.mLauncher).edit();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("affilate:");
          localStringBuilder.append(paramItemInfo);
          localEditor.putString(localStringBuilder.toString(), (String)localObject).apply();
        }
      }
    }
  }
  
  private JSONObject getDeviceInfo()
  {
    int i = this.mLauncher.getDeviceProfile().inv.numColumns;
    int j = this.mLauncher.getDeviceProfile().inv.numRows;
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("manufacturer", this.mSharedPrefs.getString("deviceManufacturer", ""));
      localJSONObject.put("marketName", this.mSharedPrefs.getString("deviceMarketName", ""));
      localJSONObject.put("model", this.mSharedPrefs.getString("deviceModel", ""));
      localJSONObject.put("codeName", this.mSharedPrefs.getString("deviceCodeName", ""));
      localJSONObject.put("deviceName", this.mSharedPrefs.getString("deviceDeviceName", ""));
      localJSONObject.put("countX", i);
      localJSONObject.put("countY", j);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      Log.e(TAG, "JSONException");
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
  
  private String getFolderLocalisation(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramContext != null)) {
      try
      {
        Resources localResources1 = paramContext.getResources();
        Resources localResources2 = paramContext.getResources();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("folder_");
        localStringBuilder.append(paramString);
        paramContext = localResources1.getString(localResources2.getIdentifier(localStringBuilder.toString(), "string", paramContext.getPackageName()));
        if (paramContext != null)
        {
          int i = paramContext.length();
          if (i > 0) {
            return paramContext;
          }
        }
      }
      catch (Resources.NotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return paramString;
  }
  
  private String getFolderRefForPackage(String paramString)
  {
    Object localObject = (String)Launcher.sLocalRefByPackage.get(paramString);
    if (localObject != null) {
      return localObject;
    }
    localObject = Launcher.sLocalRefByEditor.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (paramString.contains(str)) {
        return (String)Launcher.sLocalRefByEditor.get(str);
      }
    }
    return null;
  }
  
  private JSONArray getInstalledPackages()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.mPackageManager.getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("applicationLabel", this.mPackageManager.getApplicationLabel(localApplicationInfo));
        localJSONObject.put("packageName", localApplicationInfo.packageName);
      }
      catch (Exception localException)
      {
        Log.e(TAG, "Exception");
        localException.printStackTrace();
      }
      localJSONArray.put(localJSONObject);
    }
    return localJSONArray;
  }
  
  /* Error */
  private JSONArray getLauncherApps()
  {
    // Byte code:
    //   0: new 637	org/json/JSONArray
    //   3: dup
    //   4: invokespecial 638	org/json/JSONArray:<init>	()V
    //   7: astore 28
    //   9: getstatic 671	com/android/launcher3/LauncherSettings$Favorites:CONTENT_URI	Landroid/net/Uri;
    //   12: astore 26
    //   14: aload_0
    //   15: getfield 117	co/madseven/launcher/shortcuts/SmartFolderManager:mLauncher	Lcom/android/launcher3/Launcher;
    //   18: invokevirtual 675	com/android/launcher3/Launcher:getContentResolver	()Landroid/content/ContentResolver;
    //   21: aload 26
    //   23: aconst_null
    //   24: aconst_null
    //   25: aconst_null
    //   26: aconst_null
    //   27: invokevirtual 681	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   30: astore 27
    //   32: aload 27
    //   34: astore 29
    //   36: aload 27
    //   38: ldc_w 683
    //   41: invokeinterface 689 2 0
    //   46: istore 15
    //   48: aload 27
    //   50: astore 29
    //   52: aload 27
    //   54: ldc_w 690
    //   57: invokeinterface 689 2 0
    //   62: istore 13
    //   64: aload 27
    //   66: astore 29
    //   68: aload 27
    //   70: ldc_w 691
    //   73: invokeinterface 689 2 0
    //   78: istore 11
    //   80: aload 27
    //   82: astore 29
    //   84: aload 27
    //   86: ldc_w 692
    //   89: invokeinterface 689 2 0
    //   94: istore 12
    //   96: aload 27
    //   98: astore 29
    //   100: aload 27
    //   102: ldc_w 694
    //   105: invokeinterface 689 2 0
    //   110: istore 10
    //   112: aload 27
    //   114: astore 29
    //   116: aload 27
    //   118: ldc_w 696
    //   121: invokeinterface 689 2 0
    //   126: istore 8
    //   128: aload 27
    //   130: astore 29
    //   132: aload 27
    //   134: ldc_w 698
    //   137: invokeinterface 689 2 0
    //   142: istore 7
    //   144: aload 27
    //   146: astore 29
    //   148: aload 27
    //   150: ldc_w 699
    //   153: invokeinterface 689 2 0
    //   158: istore 6
    //   160: aload 27
    //   162: astore 29
    //   164: aload 27
    //   166: ldc_w 700
    //   169: invokeinterface 689 2 0
    //   174: istore 5
    //   176: aload 27
    //   178: astore 29
    //   180: aload 27
    //   182: ldc_w 701
    //   185: invokeinterface 689 2 0
    //   190: istore 4
    //   192: aload 27
    //   194: astore 29
    //   196: aload 27
    //   198: ldc_w 702
    //   201: invokeinterface 689 2 0
    //   206: istore_3
    //   207: aload 27
    //   209: astore 29
    //   211: aload 27
    //   213: ldc_w 704
    //   216: invokeinterface 689 2 0
    //   221: istore_1
    //   222: aload 27
    //   224: astore 29
    //   226: aload 27
    //   228: ldc_w 706
    //   231: invokeinterface 689 2 0
    //   236: istore 9
    //   238: aload 28
    //   240: astore 26
    //   242: aload 27
    //   244: astore 30
    //   246: aload 26
    //   248: astore 28
    //   250: aload 27
    //   252: astore 29
    //   254: aload 27
    //   256: ldc_w 708
    //   259: invokeinterface 689 2 0
    //   264: istore 14
    //   266: aload 27
    //   268: astore 30
    //   270: aload 26
    //   272: astore 28
    //   274: aload 27
    //   276: astore 29
    //   278: aload 27
    //   280: ldc_w 710
    //   283: invokeinterface 689 2 0
    //   288: istore_2
    //   289: aload 27
    //   291: astore 30
    //   293: aload 26
    //   295: astore 28
    //   297: aload 27
    //   299: astore 29
    //   301: aload 27
    //   303: invokeinterface 713 1 0
    //   308: ifeq +827 -> 1135
    //   311: aload 27
    //   313: astore 30
    //   315: aload 26
    //   317: astore 28
    //   319: aload 27
    //   321: astore 29
    //   323: new 537	org/json/JSONObject
    //   326: dup
    //   327: invokespecial 538	org/json/JSONObject:<init>	()V
    //   330: astore 34
    //   332: aload 27
    //   334: astore 30
    //   336: aload 26
    //   338: astore 28
    //   340: aload 27
    //   342: astore 29
    //   344: aload 27
    //   346: iload 15
    //   348: invokeinterface 717 2 0
    //   353: istore 16
    //   355: aload 27
    //   357: astore 30
    //   359: aload 26
    //   361: astore 28
    //   363: aload 27
    //   365: astore 29
    //   367: aload 27
    //   369: iload 13
    //   371: invokeinterface 718 2 0
    //   376: astore 35
    //   378: aconst_null
    //   379: astore 33
    //   381: aload 35
    //   383: ifnull +840 -> 1223
    //   386: aload 27
    //   388: astore 30
    //   390: aload 26
    //   392: astore 28
    //   394: aload 27
    //   396: astore 29
    //   398: new 450	android/content/Intent
    //   401: dup
    //   402: invokespecial 719	android/content/Intent:<init>	()V
    //   405: astore 32
    //   407: aload 27
    //   409: astore 30
    //   411: aload 26
    //   413: astore 28
    //   415: aload 27
    //   417: astore 29
    //   419: aload 35
    //   421: iconst_0
    //   422: invokestatic 723	android/content/Intent:parseUri	(Ljava/lang/String;I)Landroid/content/Intent;
    //   425: astore 31
    //   427: aload 31
    //   429: astore 32
    //   431: goto +44 -> 475
    //   434: astore 31
    //   436: aload 27
    //   438: astore 30
    //   440: aload 26
    //   442: astore 28
    //   444: aload 27
    //   446: astore 29
    //   448: getstatic 348	co/madseven/launcher/shortcuts/SmartFolderManager:TAG	Ljava/lang/String;
    //   451: ldc_w 725
    //   454: invokestatic 356	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   457: pop
    //   458: aload 27
    //   460: astore 30
    //   462: aload 26
    //   464: astore 28
    //   466: aload 27
    //   468: astore 29
    //   470: aload 31
    //   472: invokevirtual 726	java/net/URISyntaxException:printStackTrace	()V
    //   475: aload 33
    //   477: astore 31
    //   479: aload 32
    //   481: ifnull +52 -> 533
    //   484: aload 33
    //   486: astore 31
    //   488: aload 27
    //   490: astore 30
    //   492: aload 26
    //   494: astore 28
    //   496: aload 27
    //   498: astore 29
    //   500: aload 32
    //   502: invokevirtual 730	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   505: ifnull +28 -> 533
    //   508: aload 27
    //   510: astore 30
    //   512: aload 26
    //   514: astore 28
    //   516: aload 27
    //   518: astore 29
    //   520: aload 32
    //   522: invokevirtual 730	android/content/Intent:getComponent	()Landroid/content/ComponentName;
    //   525: invokevirtual 733	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   528: astore 31
    //   530: goto +3 -> 533
    //   533: aload 31
    //   535: astore 32
    //   537: aload 31
    //   539: ifnonnull +26 -> 565
    //   542: aload 27
    //   544: astore 30
    //   546: aload 26
    //   548: astore 28
    //   550: aload 27
    //   552: astore 29
    //   554: aload 27
    //   556: iload 9
    //   558: invokeinterface 718 2 0
    //   563: astore 32
    //   565: aload 32
    //   567: ifnull +565 -> 1132
    //   570: aload 27
    //   572: astore 30
    //   574: aload 26
    //   576: astore 28
    //   578: aload 27
    //   580: astore 29
    //   582: aload 27
    //   584: iload 14
    //   586: invokeinterface 718 2 0
    //   591: pop
    //   592: aload 27
    //   594: astore 30
    //   596: aload 26
    //   598: astore 28
    //   600: aload 27
    //   602: astore 29
    //   604: aload 27
    //   606: iload_2
    //   607: invokeinterface 718 2 0
    //   612: astore 31
    //   614: aload 27
    //   616: astore 30
    //   618: aload 26
    //   620: astore 28
    //   622: aload 27
    //   624: astore 29
    //   626: aload 27
    //   628: iload 12
    //   630: invokeinterface 717 2 0
    //   635: istore 17
    //   637: aload 27
    //   639: astore 30
    //   641: aload 26
    //   643: astore 28
    //   645: aload 27
    //   647: astore 29
    //   649: aload 27
    //   651: iload 10
    //   653: invokeinterface 717 2 0
    //   658: istore 18
    //   660: aload 27
    //   662: astore 30
    //   664: aload 26
    //   666: astore 28
    //   668: aload 27
    //   670: astore 29
    //   672: aload 27
    //   674: iload 8
    //   676: invokeinterface 718 2 0
    //   681: astore 33
    //   683: aload 27
    //   685: astore 30
    //   687: aload 26
    //   689: astore 28
    //   691: aload 27
    //   693: astore 29
    //   695: aload 27
    //   697: iload 11
    //   699: invokeinterface 717 2 0
    //   704: istore 19
    //   706: aload 27
    //   708: astore 30
    //   710: aload 26
    //   712: astore 28
    //   714: aload 27
    //   716: astore 29
    //   718: aload 27
    //   720: iload 7
    //   722: invokeinterface 717 2 0
    //   727: istore 20
    //   729: aload 27
    //   731: astore 30
    //   733: aload 26
    //   735: astore 28
    //   737: aload 27
    //   739: astore 29
    //   741: aload 27
    //   743: iload 6
    //   745: invokeinterface 717 2 0
    //   750: istore 21
    //   752: aload 27
    //   754: astore 30
    //   756: aload 26
    //   758: astore 28
    //   760: aload 27
    //   762: astore 29
    //   764: aload 27
    //   766: iload 5
    //   768: invokeinterface 717 2 0
    //   773: istore 22
    //   775: aload 27
    //   777: astore 30
    //   779: aload 26
    //   781: astore 28
    //   783: aload 27
    //   785: astore 29
    //   787: aload 27
    //   789: iload 4
    //   791: invokeinterface 717 2 0
    //   796: istore 23
    //   798: aload 27
    //   800: astore 30
    //   802: aload 26
    //   804: astore 28
    //   806: aload 27
    //   808: astore 29
    //   810: aload 27
    //   812: iload_3
    //   813: invokeinterface 717 2 0
    //   818: istore 24
    //   820: aload 27
    //   822: astore 30
    //   824: aload 26
    //   826: astore 28
    //   828: aload 27
    //   830: astore 29
    //   832: aload 27
    //   834: iload_1
    //   835: invokeinterface 717 2 0
    //   840: istore 25
    //   842: aload 27
    //   844: astore 28
    //   846: aload 28
    //   848: astore 29
    //   850: aload 34
    //   852: ldc_w 734
    //   855: iload 16
    //   857: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   860: pop
    //   861: aload 28
    //   863: astore 29
    //   865: aload 34
    //   867: ldc_w 736
    //   870: aload 35
    //   872: invokevirtual 550	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   875: pop
    //   876: aload 28
    //   878: astore 29
    //   880: aload 34
    //   882: ldc_w 653
    //   885: aload 32
    //   887: invokevirtual 550	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   890: pop
    //   891: aload 28
    //   893: astore 29
    //   895: aload 34
    //   897: ldc_w 710
    //   900: aload 31
    //   902: invokevirtual 550	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   905: pop
    //   906: aload 28
    //   908: astore 29
    //   910: aload 34
    //   912: ldc_w 692
    //   915: iload 17
    //   917: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   920: pop
    //   921: aload 28
    //   923: astore 29
    //   925: aload 34
    //   927: ldc_w 694
    //   930: iload 18
    //   932: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   935: pop
    //   936: aload 28
    //   938: astore 29
    //   940: aload 34
    //   942: ldc_w 696
    //   945: aload 33
    //   947: invokevirtual 550	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   950: pop
    //   951: aload 28
    //   953: astore 29
    //   955: aload 34
    //   957: ldc_w 738
    //   960: iload 19
    //   962: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   965: pop
    //   966: aload 28
    //   968: astore 29
    //   970: aload 34
    //   972: ldc_w 739
    //   975: iload 20
    //   977: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   980: pop
    //   981: aload 28
    //   983: astore 29
    //   985: aload 34
    //   987: ldc_w 699
    //   990: iload 21
    //   992: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   995: pop
    //   996: aload 28
    //   998: astore 29
    //   1000: aload 34
    //   1002: ldc_w 700
    //   1005: iload 22
    //   1007: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1010: pop
    //   1011: aload 28
    //   1013: astore 29
    //   1015: aload 34
    //   1017: ldc_w 701
    //   1020: iload 23
    //   1022: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1025: pop
    //   1026: aload 28
    //   1028: astore 29
    //   1030: aload 34
    //   1032: ldc_w 702
    //   1035: iload 24
    //   1037: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1040: pop
    //   1041: aload 28
    //   1043: astore 29
    //   1045: aload 34
    //   1047: ldc_w 704
    //   1050: iload 25
    //   1052: invokevirtual 571	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   1055: pop
    //   1056: goto +44 -> 1100
    //   1059: astore 30
    //   1061: aload 26
    //   1063: astore 27
    //   1065: aload 28
    //   1067: astore 26
    //   1069: goto +87 -> 1156
    //   1072: astore 30
    //   1074: goto +3 -> 1077
    //   1077: aload 28
    //   1079: astore 29
    //   1081: getstatic 348	co/madseven/launcher/shortcuts/SmartFolderManager:TAG	Ljava/lang/String;
    //   1084: ldc_w 575
    //   1087: invokestatic 356	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1090: pop
    //   1091: aload 28
    //   1093: astore 29
    //   1095: aload 30
    //   1097: invokevirtual 576	org/json/JSONException:printStackTrace	()V
    //   1100: aload 26
    //   1102: astore 31
    //   1104: aload 28
    //   1106: astore 29
    //   1108: aload 31
    //   1110: aload 34
    //   1112: invokevirtual 660	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   1115: pop
    //   1116: goto +16 -> 1132
    //   1119: astore 30
    //   1121: aload 31
    //   1123: astore 27
    //   1125: aload 28
    //   1127: astore 26
    //   1129: goto +45 -> 1174
    //   1132: goto -843 -> 289
    //   1135: aload 27
    //   1137: astore 28
    //   1139: goto +66 -> 1205
    //   1142: astore 29
    //   1144: aload 30
    //   1146: astore 26
    //   1148: aload 28
    //   1150: astore 27
    //   1152: aload 29
    //   1154: astore 30
    //   1156: goto +18 -> 1174
    //   1159: astore 26
    //   1161: goto +54 -> 1215
    //   1164: astore 30
    //   1166: aload 27
    //   1168: astore 26
    //   1170: aload 28
    //   1172: astore 27
    //   1174: aload 26
    //   1176: astore 29
    //   1178: getstatic 348	co/madseven/launcher/shortcuts/SmartFolderManager:TAG	Ljava/lang/String;
    //   1181: ldc_w 741
    //   1184: invokestatic 356	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   1187: pop
    //   1188: aload 26
    //   1190: astore 29
    //   1192: aload 30
    //   1194: invokevirtual 742	java/lang/IllegalArgumentException:printStackTrace	()V
    //   1197: aload 26
    //   1199: astore 28
    //   1201: aload 27
    //   1203: astore 26
    //   1205: aload 28
    //   1207: invokestatic 746	com/android/launcher3/Utilities:closeSilently	(Ljava/io/Closeable;)V
    //   1210: aload 26
    //   1212: areturn
    //   1213: astore 26
    //   1215: aload 29
    //   1217: invokestatic 746	com/android/launcher3/Utilities:closeSilently	(Ljava/io/Closeable;)V
    //   1220: aload 26
    //   1222: athrow
    //   1223: aload 33
    //   1225: astore 31
    //   1227: goto -694 -> 533
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1230	0	this	SmartFolderManager
    //   221	614	1	i	int
    //   288	319	2	j	int
    //   206	607	3	k	int
    //   190	600	4	m	int
    //   174	593	5	n	int
    //   158	586	6	i1	int
    //   142	579	7	i2	int
    //   126	549	8	i3	int
    //   236	321	9	i4	int
    //   110	542	10	i5	int
    //   78	620	11	i6	int
    //   94	535	12	i7	int
    //   62	308	13	i8	int
    //   264	321	14	i9	int
    //   46	301	15	i10	int
    //   353	503	16	i11	int
    //   635	281	17	i12	int
    //   658	273	18	i13	int
    //   704	257	19	i14	int
    //   727	249	20	i15	int
    //   750	241	21	i16	int
    //   773	233	22	i17	int
    //   796	225	23	i18	int
    //   818	218	24	i19	int
    //   840	211	25	i20	int
    //   12	1135	26	localObject1	Object
    //   1159	1	26	localObject2	Object
    //   1168	43	26	localObject3	Object
    //   1213	8	26	localObject4	Object
    //   30	1172	27	localObject5	Object
    //   7	1199	28	localObject6	Object
    //   34	1073	29	localObject7	Object
    //   1142	11	29	localIllegalArgumentException1	IllegalArgumentException
    //   1176	40	29	localObject8	Object
    //   244	579	30	localObject9	Object
    //   1059	1	30	localIllegalArgumentException2	IllegalArgumentException
    //   1072	24	30	localJSONException	JSONException
    //   1119	26	30	localIllegalArgumentException3	IllegalArgumentException
    //   1154	1	30	localIllegalArgumentException4	IllegalArgumentException
    //   1164	29	30	localIllegalArgumentException5	IllegalArgumentException
    //   425	3	31	localIntent	Intent
    //   434	37	31	localURISyntaxException	java.net.URISyntaxException
    //   477	749	31	localObject10	Object
    //   405	481	32	localObject11	Object
    //   379	845	33	str1	String
    //   330	781	34	localJSONObject	JSONObject
    //   376	495	35	str2	String
    // Exception table:
    //   from	to	target	type
    //   419	427	434	java/net/URISyntaxException
    //   850	861	1059	java/lang/IllegalArgumentException
    //   865	876	1059	java/lang/IllegalArgumentException
    //   880	891	1059	java/lang/IllegalArgumentException
    //   895	906	1059	java/lang/IllegalArgumentException
    //   910	921	1059	java/lang/IllegalArgumentException
    //   925	936	1059	java/lang/IllegalArgumentException
    //   940	951	1059	java/lang/IllegalArgumentException
    //   955	966	1059	java/lang/IllegalArgumentException
    //   970	981	1059	java/lang/IllegalArgumentException
    //   985	996	1059	java/lang/IllegalArgumentException
    //   1000	1011	1059	java/lang/IllegalArgumentException
    //   1015	1026	1059	java/lang/IllegalArgumentException
    //   1030	1041	1059	java/lang/IllegalArgumentException
    //   1045	1056	1059	java/lang/IllegalArgumentException
    //   1081	1091	1059	java/lang/IllegalArgumentException
    //   1095	1100	1059	java/lang/IllegalArgumentException
    //   850	861	1072	org/json/JSONException
    //   865	876	1072	org/json/JSONException
    //   880	891	1072	org/json/JSONException
    //   895	906	1072	org/json/JSONException
    //   910	921	1072	org/json/JSONException
    //   925	936	1072	org/json/JSONException
    //   940	951	1072	org/json/JSONException
    //   955	966	1072	org/json/JSONException
    //   970	981	1072	org/json/JSONException
    //   985	996	1072	org/json/JSONException
    //   1000	1011	1072	org/json/JSONException
    //   1015	1026	1072	org/json/JSONException
    //   1030	1041	1072	org/json/JSONException
    //   1045	1056	1072	org/json/JSONException
    //   1108	1116	1119	java/lang/IllegalArgumentException
    //   254	266	1142	java/lang/IllegalArgumentException
    //   278	289	1142	java/lang/IllegalArgumentException
    //   301	311	1142	java/lang/IllegalArgumentException
    //   323	332	1142	java/lang/IllegalArgumentException
    //   344	355	1142	java/lang/IllegalArgumentException
    //   367	378	1142	java/lang/IllegalArgumentException
    //   398	407	1142	java/lang/IllegalArgumentException
    //   419	427	1142	java/lang/IllegalArgumentException
    //   448	458	1142	java/lang/IllegalArgumentException
    //   470	475	1142	java/lang/IllegalArgumentException
    //   500	508	1142	java/lang/IllegalArgumentException
    //   520	530	1142	java/lang/IllegalArgumentException
    //   554	565	1142	java/lang/IllegalArgumentException
    //   582	592	1142	java/lang/IllegalArgumentException
    //   604	614	1142	java/lang/IllegalArgumentException
    //   626	637	1142	java/lang/IllegalArgumentException
    //   649	660	1142	java/lang/IllegalArgumentException
    //   672	683	1142	java/lang/IllegalArgumentException
    //   695	706	1142	java/lang/IllegalArgumentException
    //   718	729	1142	java/lang/IllegalArgumentException
    //   741	752	1142	java/lang/IllegalArgumentException
    //   764	775	1142	java/lang/IllegalArgumentException
    //   787	798	1142	java/lang/IllegalArgumentException
    //   810	820	1142	java/lang/IllegalArgumentException
    //   832	842	1142	java/lang/IllegalArgumentException
    //   36	48	1159	finally
    //   52	64	1159	finally
    //   68	80	1159	finally
    //   84	96	1159	finally
    //   100	112	1159	finally
    //   116	128	1159	finally
    //   132	144	1159	finally
    //   148	160	1159	finally
    //   164	176	1159	finally
    //   180	192	1159	finally
    //   196	207	1159	finally
    //   211	222	1159	finally
    //   226	238	1159	finally
    //   254	266	1159	finally
    //   278	289	1159	finally
    //   301	311	1159	finally
    //   323	332	1159	finally
    //   344	355	1159	finally
    //   367	378	1159	finally
    //   398	407	1159	finally
    //   419	427	1159	finally
    //   448	458	1159	finally
    //   470	475	1159	finally
    //   500	508	1159	finally
    //   520	530	1159	finally
    //   554	565	1159	finally
    //   582	592	1159	finally
    //   604	614	1159	finally
    //   626	637	1159	finally
    //   649	660	1159	finally
    //   672	683	1159	finally
    //   695	706	1159	finally
    //   718	729	1159	finally
    //   741	752	1159	finally
    //   764	775	1159	finally
    //   787	798	1159	finally
    //   810	820	1159	finally
    //   832	842	1159	finally
    //   36	48	1164	java/lang/IllegalArgumentException
    //   52	64	1164	java/lang/IllegalArgumentException
    //   68	80	1164	java/lang/IllegalArgumentException
    //   84	96	1164	java/lang/IllegalArgumentException
    //   100	112	1164	java/lang/IllegalArgumentException
    //   116	128	1164	java/lang/IllegalArgumentException
    //   132	144	1164	java/lang/IllegalArgumentException
    //   148	160	1164	java/lang/IllegalArgumentException
    //   164	176	1164	java/lang/IllegalArgumentException
    //   180	192	1164	java/lang/IllegalArgumentException
    //   196	207	1164	java/lang/IllegalArgumentException
    //   211	222	1164	java/lang/IllegalArgumentException
    //   226	238	1164	java/lang/IllegalArgumentException
    //   850	861	1213	finally
    //   865	876	1213	finally
    //   880	891	1213	finally
    //   895	906	1213	finally
    //   910	921	1213	finally
    //   925	936	1213	finally
    //   940	951	1213	finally
    //   955	966	1213	finally
    //   970	981	1213	finally
    //   985	996	1213	finally
    //   1000	1011	1213	finally
    //   1015	1026	1213	finally
    //   1030	1041	1213	finally
    //   1045	1056	1213	finally
    //   1081	1091	1213	finally
    //   1095	1100	1213	finally
    //   1108	1116	1213	finally
    //   1178	1188	1213	finally
    //   1192	1197	1213	finally
  }
  
  private JSONObject getParameters()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("userId", this.mSharedPrefs.getString("ID", ""));
      localJSONObject.put("packagesCount", this.mInstalledPackages.length());
      localJSONObject.put("device", this.mDeviceInfo);
      localJSONObject.put("packages", this.mInstalledPackages);
      localJSONObject.put("shortcuts", this.mLauncherApps);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      Log.e(TAG, "JSONException");
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
  
  private void installDefaultSortcuts(ArrayList<ItemInfo> paramArrayList, boolean paramBoolean, Runnable paramRunnable)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
    {
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        ItemInfo localItemInfo = (ItemInfo)paramArrayList.next();
        if (localItemInfo != null)
        {
          Object localObject;
          if ((localItemInfo instanceof FolderInfo))
          {
            localObject = (FolderInfo)localItemInfo;
            if ((((FolderInfo)localObject).contents != null) && (((FolderInfo)localObject).contents.size() > 0))
            {
              localObject = ((FolderInfo)localObject).contents.iterator();
              while (((Iterator)localObject).hasNext()) {
                findAffiliateSubstitute((ShortcutInfo)((Iterator)localObject).next());
              }
            }
          }
          else if ((localItemInfo instanceof ShortcutInfo))
          {
            findAffiliateSubstitute(localItemInfo);
          }
          if (!paramBoolean)
          {
            localObject = this.mLauncher.getWorkspace().getScreenWithId(localItemInfo.screenId);
            installItemInfo(localItemInfo, localItemInfo.container, localItemInfo.screenId, localItemInfo.cellX, localItemInfo.cellY, (CellLayout)localObject);
          }
        }
      }
    }
    if (paramRunnable != null) {
      paramRunnable.run();
    }
  }
  
  private void installItemInfo(ItemInfo paramItemInfo, long paramLong1, long paramLong2, int paramInt1, int paramInt2, CellLayout paramCellLayout)
  {
    if (paramCellLayout != null)
    {
      Object localObject1 = paramCellLayout.getChildAt(paramInt1, paramInt2);
      if (localObject1 != null)
      {
        paramCellLayout.removeView((View)localObject1);
        if ((((View)localObject1).getTag() instanceof ItemInfo)) {
          LauncherModel.deleteItemFromDatabase(this.mLauncher, (ItemInfo)((View)localObject1).getTag());
        }
      }
      if ((paramItemInfo instanceof FolderInfo))
      {
        Object localObject2 = (FolderInfo)paramItemInfo;
        String str = ((FolderInfo)localObject2).title.toString();
        localObject1 = ((FolderInfo)localObject2).contents;
        if (((ArrayList)localObject1).size() > 1)
        {
          if (str.length() > 0) {
            paramCellLayout = this.mLauncher.addFolderWithTitle(paramCellLayout, ((FolderInfo)localObject2).container, ((FolderInfo)localObject2).screenId, ((FolderInfo)localObject2).cellX, ((FolderInfo)localObject2).cellY, str);
          } else {
            paramCellLayout = this.mLauncher.addFolder(paramCellLayout, ((FolderInfo)localObject2).container, ((FolderInfo)localObject2).screenId, ((FolderInfo)localObject2).cellX, ((FolderInfo)localObject2).cellY);
          }
          localObject1 = ((ArrayList)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = (ShortcutInfo)((Iterator)localObject1).next();
            paramCellLayout.addItem((ShortcutInfo)localObject2);
            LauncherModel.addItemToDatabase(this.mLauncher, (ItemInfo)localObject2, paramCellLayout.getFolderInfo().id, paramCellLayout.getFolderInfo().screenId, -1, -1);
          }
          this.mLauncher.getWorkspace().addInScreen(paramCellLayout, paramLong1, paramLong2, paramInt1, paramInt2, paramItemInfo.spanX, paramItemInfo.spanY);
          paramCellLayout.onItemsChanged(false);
          return;
        }
        if (((ArrayList)localObject1).size() == 1)
        {
          paramItemInfo = (ShortcutInfo)((ArrayList)localObject1).get(0);
          LauncherModel.addItemToDatabase(this.mLauncher, paramItemInfo, paramLong1, paramLong2, paramInt1, paramInt2);
          paramCellLayout = this.mLauncher.createShortcut(paramItemInfo);
          this.mLauncher.getWorkspace().addInScreen(paramCellLayout, paramLong1, paramLong2, paramInt1, paramInt2, paramItemInfo.spanX, paramItemInfo.spanY);
        }
      }
      else if ((paramItemInfo instanceof ShortcutInfo))
      {
        LauncherModel.addItemToDatabase(this.mLauncher, paramItemInfo, paramLong1, paramLong2, paramInt1, paramInt2);
        paramCellLayout = this.mLauncher.createShortcut((ShortcutInfo)paramItemInfo);
        this.mLauncher.getWorkspace().addInScreen(paramCellLayout, paramLong1, paramLong2, paramInt1, paramInt2, paramItemInfo.spanX, paramItemInfo.spanY);
      }
    }
  }
  
  private void installSortcuts(HashMap<String, ArrayList<String>> paramHashMap)
  {
    mCurrentScreenId = -1L;
    this.mLauncher.getWorkspace().removeAllExtraScreens();
    if (sortShortcuts(paramHashMap))
    {
      this.mHandler.post(this.mInstallShortcutRunnable);
      return;
    }
    if (this.onCompletion != null)
    {
      this.mRequesting = false;
      this.onCompletion.run();
    }
  }
  
  protected static void parseSmartFolderXml(Context paramContext)
  {
    Launcher.sLocalRefByPackage.clear();
    Launcher.sLocalRefByEditor.clear();
    Object localObject = paramContext.getResources();
    for (;;)
    {
      int i;
      try
      {
        i = ((Resources)localObject).getIdentifier("smartfolders_mapping", "xml", paramContext.getPackageName());
        if (i > 0) {
          paramContext = ((Resources)localObject).getXml(i);
        }
      }
      catch (IOException paramContext)
      {
        paramContext.printStackTrace();
        return;
        Log.d(TAG, "Cannot parse smartfolders_mapping.xml");
        return;
      }
      catch (XmlPullParserException paramContext)
      {
        label94:
        continue;
      }
      try
      {
        localObject = ((Resources)localObject).getAssets().open("smartfolders_mapping.xml");
        paramContext = XmlPullParserFactory.newInstance();
        paramContext.setNamespaceAware(true);
        paramContext = paramContext.newPullParser();
      }
      catch (IOException paramContext)
      {
        continue;
      }
      try
      {
        paramContext.setInput((InputStream)localObject, "utf-8");
      }
      catch (IOException localIOException)
      {
        continue;
        i += 1;
        continue;
        i += 1;
      }
    }
    break label94;
    paramContext = null;
    Log.d(TAG, "No appfilter.xml file");
    if (paramContext != null) {
      for (i = paramContext.getEventType(); i != 1; i = paramContext.next()) {
        if (i == 2)
        {
          boolean bool = paramContext.getName().equals("folder");
          int j = 0;
          i = 0;
          if (bool)
          {
            if (i < paramContext.getAttributeCount())
            {
              if (!paramContext.getAttributeName(i).equals("ref")) {
                break label332;
              }
              mCurrentParsingRef = paramContext.getAttributeValue(i);
              break label332;
            }
          }
          else if (paramContext.getName().equals("item"))
          {
            i = j;
            if (i < paramContext.getAttributeCount())
            {
              if (paramContext.getAttributeName(i).equals("package"))
              {
                localObject = paramContext.getAttributeValue(i);
                Launcher.sLocalRefByPackage.put(localObject, mCurrentParsingRef);
                break label339;
              }
              if (!paramContext.getAttributeName(i).equals("editor")) {
                break label339;
              }
              localObject = paramContext.getAttributeValue(i);
              Launcher.sLocalRefByEditor.put(localObject, mCurrentParsingRef);
              break label339;
            }
          }
        }
      }
    }
  }
  
  @SuppressLint({"StaticFieldLeak"})
  public static void parseSmartFolders(Context paramContext, final Runnable paramRunnable)
  {
    if (Launcher.sLocalRefByPackage.isEmpty())
    {
      new AsyncTask()
      {
        public Void doInBackground(Void... paramAnonymousVarArgs)
        {
          SmartFolderManager.parseSmartFolderXml(this.val$context);
          return null;
        }
        
        protected void onPostExecute(Void paramAnonymousVoid)
        {
          if (paramRunnable != null) {
            paramRunnable.run();
          }
        }
      }.executeOnExecutor(Utilities.THREAD_POOL_EXECUTOR, new Void[0]);
      return;
    }
    if (paramRunnable != null) {
      paramRunnable.run();
    }
  }
  
  private void processCategories(List<Category> paramList)
  {
    try
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (Category)paramList.next();
        String str = ((Category)localObject).getPackageName();
        localObject = ((Category)localObject).getCategories();
        if ((localObject != null) && (str != null) && (str.length() > 0) && (!str.equals("co.madseven.launcher"))) {
          if (this.mShortcutsByCategory.containsKey(localObject))
          {
            ((ArrayList)this.mShortcutsByCategory.get(localObject)).add(str);
          }
          else
          {
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(str);
            this.mShortcutsByCategory.put(localObject, localArrayList);
          }
        }
      }
      return;
    }
    catch (Exception paramList) {}
  }
  
  private void processDefaultShortcuts(byte[] paramArrayOfByte, final boolean paramBoolean, final Runnable paramRunnable)
  {
    CustomShortcutsParser localCustomShortcutsParser = new CustomShortcutsParser(this.mLauncher);
    if (paramArrayOfByte != null) {
      paramArrayOfByte = new StringReader(new String(paramArrayOfByte));
    } else {
      paramArrayOfByte = null;
    }
    localCustomShortcutsParser.parseLayout(paramArrayOfByte, new OnCustomShortcutsParserListener()
    {
      public void onParseError(String paramAnonymousString)
      {
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
      
      public void onParseFinished(ArrayList<ItemInfo> paramAnonymousArrayList)
      {
        SmartFolderManager.this.installDefaultSortcuts(paramAnonymousArrayList, paramBoolean, paramRunnable);
      }
    });
  }
  
  private void requestRemoteCategoryMapping(final Runnable paramRunnable)
  {
    if (!this.mRequesting)
    {
      this.mRequesting = true;
      RequestBody localRequestBody = RequestBody.create(MediaType.parse("text/plain"), getParameters().toString());
      SmartFoldersService.getInstance(this.mLauncher).fetchFolders(localRequestBody).enqueue(new Callback()
      {
        public void onFailure(Call<SmartFoldersResponse> paramAnonymousCall, Throwable paramAnonymousThrowable)
        {
          if (paramRunnable != null)
          {
            SmartFolderManager.access$702(SmartFolderManager.this, false);
            paramRunnable.run();
          }
        }
        
        public void onResponse(Call<SmartFoldersResponse> paramAnonymousCall, Response<SmartFoldersResponse> paramAnonymousResponse)
        {
          if (paramAnonymousResponse != null)
          {
            paramAnonymousCall = (SmartFoldersResponse)paramAnonymousResponse.body();
            if ((paramAnonymousCall != null) && (paramAnonymousCall.getCategories() != null)) {
              SmartFolderManager.this.processCategories(paramAnonymousCall.getCategories());
            }
          }
          if (paramRunnable != null)
          {
            SmartFolderManager.access$702(SmartFolderManager.this, false);
            paramRunnable.run();
          }
        }
      });
    }
  }
  
  private boolean screenExists(long paramLong)
  {
    return this.mLauncher.getWorkspace().getScreenWithId(paramLong) != null;
  }
  
  @SuppressLint({"StaticFieldLeak"})
  private void sortAppsByCategories(final Runnable paramRunnable)
  {
    new AsyncTask()
    {
      public Void doInBackground(Void... paramAnonymousVarArgs)
      {
        if (SmartFolderManager.this.mLauncher != null)
        {
          Iterator localIterator = SmartFolderManager.this.mPackageManager.getInstalledApplications(128).iterator();
          while (localIterator.hasNext())
          {
            paramAnonymousVarArgs = (ApplicationInfo)localIterator.next();
            if (paramAnonymousVarArgs.packageName != null)
            {
              String str = paramAnonymousVarArgs.packageName;
              boolean bool = Preferences.getInstance().isAppHidden(SmartFolderManager.this.mLauncher, str);
              paramAnonymousVarArgs = SmartFolderManager.this.getFolderLocalisation(SmartFolderManager.this.mLauncher, SmartFolderManager.access$400(SmartFolderManager.this, str));
              if ((!bool) && (paramAnonymousVarArgs != null) && (paramAnonymousVarArgs.length() > 0))
              {
                ArrayList localArrayList;
                if (SmartFolderManager.this.mShortcutsByCategory.containsKey(paramAnonymousVarArgs))
                {
                  localArrayList = (ArrayList)SmartFolderManager.this.mShortcutsByCategory.get(paramAnonymousVarArgs);
                  paramAnonymousVarArgs = localArrayList;
                  if (localArrayList == null) {
                    paramAnonymousVarArgs = new ArrayList();
                  }
                  paramAnonymousVarArgs.add(str);
                }
                else
                {
                  localArrayList = new ArrayList();
                  localArrayList.add(str);
                  SmartFolderManager.this.mShortcutsByCategory.put(paramAnonymousVarArgs, localArrayList);
                }
              }
            }
          }
        }
        paramAnonymousVarArgs = Utils.getAppsByUsage(SmartFolderManager.this.mLauncher, 12, true);
        if ((paramAnonymousVarArgs != null) && (paramAnonymousVarArgs.size() > 0)) {
          SmartFolderManager.this.mShortcutsByCategory.put(SmartFolderManager.this.getFolderLocalisation(SmartFolderManager.this.mLauncher, "used"), paramAnonymousVarArgs);
        }
        return null;
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        if (paramRunnable != null) {
          paramRunnable.run();
        }
      }
    }.executeOnExecutor(Utilities.THREAD_POOL_EXECUTOR, new Void[0]);
  }
  
  private boolean sortShortcuts(HashMap<String, ArrayList<String>> paramHashMap)
  {
    this.mSortedShortcuts.clear();
    Object localObject1 = new TreeSet(paramHashMap.keySet());
    Iterator localIterator = ((SortedSet)localObject1).iterator();
    Object localObject2;
    while (localIterator.hasNext())
    {
      localObject2 = (String)localIterator.next();
      Object localObject3 = (ArrayList)paramHashMap.get(localObject2);
      if ((((String)localObject2).length() > 0) && (((ArrayList)localObject3).size() > 1))
      {
        localObject3 = ((ArrayList)localObject3).iterator();
        while (((Iterator)localObject3).hasNext())
        {
          Shortcut localShortcut = new Shortcut((String)((Iterator)localObject3).next(), (String)localObject2);
          this.mSortedShortcuts.add(localShortcut);
        }
        localIterator.remove();
      }
    }
    localObject1 = ((SortedSet)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localIterator = ((ArrayList)paramHashMap.get((String)((Iterator)localObject1).next())).iterator();
      while (localIterator.hasNext())
      {
        localObject2 = new Shortcut((String)localIterator.next(), getFolderLocalisation(this.mLauncher, "other"));
        this.mSortedShortcuts.add(localObject2);
      }
    }
    return this.mSortedShortcuts.size() != 0;
  }
  
  public void createSmartFolders(Runnable paramRunnable)
  {
    this.onCompletion = paramRunnable;
    this.mShortcutsByCategory.clear();
    parseSmartFolders(this.mLauncher, new Runnable()
    {
      public void run()
      {
        SmartFolderManager.this.sortAppsByCategories(new Runnable()
        {
          public void run()
          {
            SmartFolderManager.this.installSortcuts(SmartFolderManager.this.mShortcutsByCategory);
          }
        });
      }
    });
  }
  
  @SuppressLint({"StaticFieldLeak"})
  public void initDefaultSortcut(final boolean paramBoolean, final Runnable paramRunnable)
  {
    ApoloTracker.getInstance(this.mLauncher).sentEvent("Shortcut", "Installation des raccourcis spéciaux", null);
    if (Utils.isNetworkAvailable(this.mLauncher))
    {
      FirebaseStorage.getInstance().getReference("shortcuts_mapping.xml").getBytes(1048576L).addOnSuccessListener(new OnSuccessListener()
      {
        public void onSuccess(byte[] paramAnonymousArrayOfByte)
        {
          SmartFolderManager.this.processDefaultShortcuts(paramAnonymousArrayOfByte, paramBoolean, paramRunnable);
        }
      }).addOnFailureListener(new OnFailureListener()
      {
        public void onFailure(@NonNull Exception paramAnonymousException)
        {
          SmartFolderManager.this.processDefaultShortcuts(null, paramBoolean, paramRunnable);
        }
      });
      return;
    }
    processDefaultShortcuts(null, paramBoolean, paramRunnable);
  }
  
  class Shortcut
  {
    String cat = "";
    String pkg;
    
    public Shortcut(String paramString1, String paramString2)
    {
      this.pkg = paramString1;
      this.cat = paramString2;
    }
  }
}
