package com.docusign.common;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionEntry;
import android.content.RestrictionsManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.app.ShareCompat.IntentBuilder;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.docusign.bizobj.AccessToken;
import com.docusign.bizobj.AccessToken.Error;
import com.docusign.bizobj.User;
import com.docusign.ink.DSOAuthActivity;
import com.docusign.ink.DSPinActivity;
import com.docusign.ink.GenericConfirmationDSDialogFragment;
import com.docusign.ink.HomeActivity;
import com.docusign.ink.IPurchaseHelper;
import com.docusign.ink.PurchaseProductHelper;
import com.docusign.ink.UserSettingsService;
import com.docusign.ink.utils.DSLog;
import com.docusign.ink.utils.DSUiUtils;
import com.docusign.persistence.ICorrectAccess;
import com.docusign.persistence.ILogin;
import com.docusign.persistence.IPinAccess;
import com.docusign.persistence.IRestrictions;
import com.docusign.persistence.IScreenshot;
import com.docusign.persistence.ISharing;
import com.docusign.persistence.ITimeout;
import com.docusign.persistence.ObjectPersistenceFactory;
import com.github.orangegangsters.lollipin.lib.PinFragmentActivity;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DSActivity
  extends PinFragmentActivity
  implements ResumableLoader, ActivityCompat.OnRequestPermissionsResultCallback
{
  private static final int DIALOG_ERROR = -2;
  private static final int DIALOG_ERROR_DETAILS = -1;
  private static final Set<String> EMAIL_CLIENTS;
  private static final String ERROR_CLOSEONFINISH_KEY = "closeOnFinish";
  private static final String ERROR_DETAILS_KEY = "details";
  private static final String ERROR_MESSAGE_KEY = "message";
  public static final String EXTRA_DOCUMENT = "com.docusign.ink.DSApplication.document";
  public static final String EXTRA_ENVELOPE = "com.docusign.ink.DSApplication.envelope";
  public static final String EXTRA_EVERNOTE = "com.evernote.note_data";
  public static final String EXTRA_EVERNOTE_ATTACHMENT = "com.evernote.note_attachment_index";
  public static final String EXTRA_GMAIL_DOCUMENT_NAME = "com.docsign.ink.DSApplication.GMailDocumentName";
  public static final String EXTRA_ONE_CLOUD = "one_cloud_data";
  public static final String EXTRA_UPGRADABLE_ERROR = "com.docusign.ink.DSApplication.UpgradableError";
  private static final Set<String> FACEBOOK_CLIENTS;
  public static final String FILE_PROVIDER_AUTHORITY = "com.docusign.ink.fileprovider";
  private static final String GOOGLE_PLUS = "com.google.android.apps.plus";
  public static final int PERMISSION_ACCESS_CAMERA = 2;
  public static final int PERMISSION_ACCESS_FINE_LOCATION = 3;
  public static final int PERMISSION_CAMERA_STORAGE_ACCESS = 6;
  public static final int PERMISSION_GET_ACCOUNTS_ACCESS = 7;
  public static final int PERMISSION_LOCATION_CONTACTS_ACCESS = 5;
  public static final int PERMISSION_READ_CONTACTS = 1;
  public static final int PERMISSION_READ_EXTERNAL_STORAGE = 4;
  protected static final int REQUEST_FIRST_USER = 4;
  protected static final int REQUEST_OAUTH = 3;
  public static final int REQUEST_PURCHASE = 2;
  protected static final int REQUEST_SHARE = 1;
  private static final String TAG = DSActivity.class.getSimpleName();
  public static final String TAG_DIALOG_UPGRADE_APPLE = ".upgradeApple";
  private static final Set<String> TWITTER_CLIENTS;
  private String cameraPackageName;
  protected boolean mDestroyedCompat;
  private ProgressDialog mDismissProgress;
  private final BroadcastReceiver mDismissProgressBarReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (DSActivity.this.mDismissProgress != null) {
        DSActivity.this.mDismissProgress.dismiss();
      }
    }
  };
  public boolean mIsErrorDialogShown = false;
  private DSLoaderManager mLoaderManager;
  private AlertDialog mLoginDialog;
  private final BroadcastReceiver mLoginReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      DSActivity.this.startService(new Intent(DSActivity.this, UserSettingsService.class));
    }
  };
  private final BroadcastReceiver mLogoutReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      DSActivity.this.mLoaderManager.destroyAllLoaders();
      DSActivity.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (!DSActivity.this.mDestroyedCompat) {
            DSActivity.this.onLogout();
          }
        }
      });
    }
  };
  private final BroadcastReceiver mOAuthReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      DSActivity.this.handleOAuth();
    }
  };
  private WorkProfileManager mProfileManager = null;
  private ArrayList<ProgressDialog> mProgressDialogs;
  private IPurchaseHelper mPurchaseHelper;
  private final BroadcastReceiver mReLoginReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      DSActivity.this.showReLoginDialog();
    }
  };
  private Bundle mRestrictions = null;
  private final BroadcastReceiver mRestrictionsReceiver = new BroadcastReceiver()
  {
    @TargetApi(21)
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = (RestrictionsManager)DSActivity.this.getSystemService("restrictions");
      DSActivity.access$202(DSActivity.this, paramAnonymousContext.getApplicationRestrictions());
      paramAnonymousContext = paramAnonymousContext.getManifestRestrictions(DSActivity.this.getApplicationContext().getPackageName());
      DSActivity.this.applyRestrictions(DSActivity.this.mRestrictions, paramAnonymousContext);
    }
  };
  private LinkedList<DSActivityTask<?, ?, ?>> m_CurrentTasks = new LinkedList();
  private Object m_Interface;
  private Intent m_StartingActivity;
  
  static
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add("com.yahoo.mobile.client.android.mail");
    localHashSet.add("com.fsck.k9");
    localHashSet.add("com.mail.mobile.android.mail");
    localHashSet.add("ru.yandex.mail");
    localHashSet.add("com.emtrace.hermes");
    localHashSet.add("com.nitrodesk.droid20.nitroid");
    localHashSet.add("com.nitrodesk.honey.nitroid");
    localHashSet.add("com.maildroid");
    localHashSet.add("com.maildroid.pro");
    localHashSet.add("com.kaitenmail");
    localHashSet.add("com.qs.enhancedemail");
    localHashSet.add("com.ghostpattern.gwmail");
    localHashSet.add("com.tallpeaks.emailplus");
    localHashSet.add("com.exmailfree");
    localHashSet.add("com.exmail");
    localHashSet.add("com.voicegenesis.roid1");
    localHashSet.add("com.voicegenesis.roid1ad");
    localHashSet.add("com.hotmail.Z7");
    localHashSet.add("com.pollotech.hotmail");
    localHashSet.add("com.pollotech.hpro");
    localHashSet.add("net.pocketmob.hotmail");
    localHashSet.add("net.pocketmob.hotmailpro");
    localHashSet.add("com.azuredroid.hotmail");
    localHashSet.add("app.hmail");
    localHashSet.add("best.mail");
    localHashSet.add("com.bansalstudios.fasthotmail");
    localHashSet.add("com.bansalstudios.fasthotmailPro");
    localHashSet.add("hotmail.coss.team");
    localHashSet.add("com.google.android.gm");
    localHashSet.add("com.google.android.email");
    localHashSet.add("com.sfr.android.sfrmail");
    localHashSet.add("com.htc.android.mail");
    localHashSet.add("com.motorola.blur.email");
    localHashSet.add("com.android.email");
    localHashSet.add("com.lge.email");
    EMAIL_CLIENTS = Collections.unmodifiableSet(localHashSet);
    localHashSet = new HashSet();
    localHashSet.add("com.twitter.android");
    localHashSet.add("com.handmark.tweetcaster");
    localHashSet.add("com.handmark.tweetcaster.premium");
    localHashSet.add("com.handmark.tweetcaster.pink.premium");
    localHashSet.add("com.twidroid");
    localHashSet.add("com.levelup.touiteur");
    localHashSet.add("com.levelup.touiteurpremium");
    localHashSet.add("com.seesmic");
    localHashSet.add("com.twitter.android.tv");
    localHashSet.add("com.thedeck.android.app");
    localHashSet.add("com.kook.tweetblackgray.free.app");
    localHashSet.add("jp.r246.twicca");
    localHashSet.add("com.alanjeon.twitplus");
    localHashSet.add("jp.ne.biglobe.twipple");
    localHashSet.add("com.hotflag.android.twitpal");
    localHashSet.add("tice.twitterwalk");
    localHashSet.add("to.tanimo.android.twitterride");
    localHashSet.add("com.hootsuite.droid.full");
    localHashSet.add("com.chriswstewart.twitter");
    localHashSet.add("com.iappventures.twitprolite");
    localHashSet.add("com.locomolabs.socialblast");
    localHashSet.add("com.htc.friendstream");
    localHashSet.add("com.htc.htctwitter");
    TWITTER_CLIENTS = Collections.unmodifiableSet(localHashSet);
    localHashSet = new HashSet();
    localHashSet.add("com.facebook.katana");
    localHashSet.add("com.facebook.orca");
    localHashSet.add("com.spartancoders.gtok");
    localHashSet.add("uk.co.senab.blueNotifyFree");
    localHashSet.add("uk.co.senab.blueNotify");
    localHashSet.add("com.locomolabs.facebook");
    FACEBOOK_CLIENTS = Collections.unmodifiableSet(localHashSet);
  }
  
  public DSActivity() {}
  
  private boolean _wasPermissionGranted(int paramInt)
  {
    return paramInt == 0;
  }
  
  @TargetApi(21)
  private void getAppRestrictions()
  {
    this.mProfileManager = ((DSApplication)getApplication()).getWorkProfileManager();
    if ((this.mRestrictions == null) && (this.mProfileManager != null))
    {
      Object localObject = (RestrictionsManager)getSystemService("restrictions");
      this.mRestrictions = ((RestrictionsManager)localObject).getApplicationRestrictions();
      localObject = ((RestrictionsManager)localObject).getManifestRestrictions(getApplicationContext().getPackageName());
      applyRestrictions(this.mRestrictions, (List)localObject);
    }
  }
  
  private Object getInterface()
  {
    return this.m_Interface;
  }
  
  private List<DSActivityTask<?, ?, ?>> getLastCurrentTasksInstance()
  {
    Object[] arrayOfObject = (Object[])getLastCustomNonConfigurationInstance();
    if (arrayOfObject != null) {
      return (List)arrayOfObject[0];
    }
    return null;
  }
  
  private void handleAccessCameraAndStorageResult(String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (paramArrayOfString == null) {
      return;
    }
    int i = 0;
    label7:
    String str;
    boolean bool;
    if (i < paramArrayOfString.length)
    {
      str = paramArrayOfString[i];
      bool = _wasPermissionGranted(paramArrayOfInt[i]);
      if (!str.equalsIgnoreCase("android.permission.CAMERA")) {
        break label51;
      }
      handleAccessCameraResult(bool);
    }
    for (;;)
    {
      i += 1;
      break label7;
      break;
      label51:
      if (str.equalsIgnoreCase("android.permission.READ_EXTERNAL_STORAGE")) {
        handleAccessReadExternalStorageResult(bool);
      }
    }
  }
  
  private void handleAccessCameraResult(boolean paramBoolean)
  {
    if (isValidCameraInterface())
    {
      if (!paramBoolean) {
        showCameraAccessDeniedToast();
      }
      ((ICameraAccess)getInterface()).cameraAccessGranted(paramBoolean);
    }
  }
  
  private void handleAccessFineLocationResult(boolean paramBoolean)
  {
    if (isValidLocationInterface())
    {
      if (paramBoolean) {
        ((ILocationAccess)getInterface()).locationAccessGranted(paramBoolean);
      }
    }
    else {
      return;
    }
    showLocationAccessDeniedToast();
  }
  
  private void handleAccessGetAccountsResult(boolean paramBoolean)
  {
    if (isValidGetAccountsInterface()) {
      ((IGetAccountsAccess)getInterface()).getAccountsAccessGranted(paramBoolean);
    }
  }
  
  private void handleAccessLocationAndContactsResult(String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (paramArrayOfString == null) {
      return;
    }
    int i = 0;
    label7:
    String str;
    boolean bool;
    if (i < paramArrayOfString.length)
    {
      str = paramArrayOfString[i];
      bool = _wasPermissionGranted(paramArrayOfInt[i]);
      if (!str.equalsIgnoreCase("android.permission.READ_CONTACTS")) {
        break label51;
      }
      handleAccessReadContactsResult(bool);
    }
    for (;;)
    {
      i += 1;
      break label7;
      break;
      label51:
      if (str.equalsIgnoreCase("android.permission.ACCESS_FINE_LOCATION")) {
        handleAccessFineLocationResult(bool);
      }
    }
  }
  
  private void handleAccessReadContactsResult(boolean paramBoolean)
  {
    if (isValidContactsInterface())
    {
      if (paramBoolean) {
        ((IContactsAccess)getInterface()).contactsAccessGranted(paramBoolean);
      }
    }
    else {
      return;
    }
    showContactsAccessDeniedToast();
  }
  
  private void handleAccessReadExternalStorageResult(boolean paramBoolean)
  {
    if (isValidStorageInterface())
    {
      if (!paramBoolean) {
        showStorageAccessDeniedToast();
      }
      ((IStorageAccess)getInterface()).storageAccessGranted(paramBoolean);
    }
  }
  
  private boolean isValidCameraInterface()
  {
    return (getInterface() != null) && ((getInterface() instanceof ICameraAccess));
  }
  
  private boolean isValidContactsInterface()
  {
    return (getInterface() != null) && ((getInterface() instanceof IContactsAccess));
  }
  
  private boolean isValidGetAccountsInterface()
  {
    return (getInterface() != null) && ((getInterface() instanceof IGetAccountsAccess));
  }
  
  private boolean isValidLocationInterface()
  {
    return (getInterface() != null) && ((getInterface() instanceof ILocationAccess));
  }
  
  private boolean isValidStorageInterface()
  {
    return (getInterface() != null) && ((getInterface() instanceof IStorageAccess));
  }
  
  @TargetApi(21)
  private void registerAppRestrictionReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.APPLICATION_RESTRICTIONS_CHANGED");
    localIntentFilter.setPriority(1);
    registerReceiver(this.mRestrictionsReceiver, localIntentFilter);
  }
  
  private void showPermissionDeniedToast(String paramString)
  {
    paramString = new StringBuilder(paramString);
    paramString.append('\n');
    paramString.append(getString(2131100494));
    Toast.makeText(this, paramString, 1).show();
  }
  
  private boolean wasPermissionGranted(int[] paramArrayOfInt)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramArrayOfInt.length > 0)
    {
      bool1 = bool2;
      if (paramArrayOfInt[0] == 0) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public void addTask(DSActivityTask<?, ?, ?> paramDSActivityTask)
  {
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext())
    {
      DSActivityTask localDSActivityTask = (DSActivityTask)localIterator.next();
      if (localDSActivityTask.getClass().isInstance(paramDSActivityTask)) {
        DSLog.w(TAG, "WARNING: Possible duplication of DSActivityTasks! Adding " + paramDSActivityTask + ", " + this + " already has " + localDSActivityTask);
      }
    }
    this.m_CurrentTasks.add(paramDSActivityTask);
  }
  
  protected void applyRestrictions(Bundle paramBundle, List<RestrictionEntry> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      RestrictionEntry localRestrictionEntry = (RestrictionEntry)paramList.next();
      String str = localRestrictionEntry.getKey();
      if (str.equals("WhitelistImportingDocuments")) {
        AppRestrictionsUtil.updateDocumentImportRestrictions(this, localRestrictionEntry, paramBundle);
      } else if (str.equals("WhitelistSharingApplication")) {
        AppRestrictionsUtil.updateAppShareRestrictions(this, localRestrictionEntry, paramBundle);
      } else if (str.equals("WhitelistSharingDocuments")) {
        AppRestrictionsUtil.updateDocumentShareRestrictions(this, localRestrictionEntry, paramBundle);
      }
    }
  }
  
  public void checkAndRequestContactsAndLocationAccess(Object paramObject)
  {
    this.m_Interface = paramObject;
    boolean bool1 = isLocationPermissionAlreadyGranted();
    boolean bool2 = isContactsPermissionAlreadyGranted();
    if ((bool1) && (!bool2))
    {
      requestContactsAccess(this);
      return;
    }
    if ((!bool1) && (bool2))
    {
      requestLocationAccess(this);
      return;
    }
    requestContactsAndLocationAccess(this);
  }
  
  public int checkSelfPermission(String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return -1;
      try
      {
        int i = ContextCompat.checkSelfPermission(this, paramString);
        if ((i != -1) && (i == 0)) {
          return 0;
        }
      }
      catch (Throwable paramString) {}
    }
    return -1;
  }
  
  protected void displayAppleUpgrade()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString(GenericConfirmationDSDialogFragment.PARAM_SPECIFIC_TAG, ".upgradeApple");
    localBundle.putString(GenericConfirmationDSDialogFragment.PARAM_TITLE, getString(2131099904));
    localBundle.putString(GenericConfirmationDSDialogFragment.PARAM_MESSAGE, getString(2131099903));
    localBundle.putString(GenericConfirmationDSDialogFragment.PARAM_POSITIVE_CTA, getString(2131100225));
    GenericConfirmationDSDialogFragment.newInstance(localBundle).showAllowingStateLoss(getSupportFragmentManager());
  }
  
  protected void finishedSharingDocuSign(String paramString) {}
  
  public String getAdvertisingId()
  {
    AdvertisingIdClient.Info localInfo = ((DSApplication)getApplication()).getAdvertisingInfo();
    if (localInfo != null) {
      return localInfo.getId();
    }
    return null;
  }
  
  public String getCameraPackageName()
  {
    if (this.cameraPackageName == null) {
      this.cameraPackageName = new Intent("android.media.action.IMAGE_CAPTURE").resolveActivity(getPackageManager()).getPackageName();
    }
    return this.cameraPackageName;
  }
  
  protected final Object getLastRetainedDSNonConfigurationInstance()
  {
    Object[] arrayOfObject = (Object[])getLastCustomNonConfigurationInstance();
    if ((arrayOfObject != null) && (arrayOfObject.length == 2)) {
      return arrayOfObject[1];
    }
    return null;
  }
  
  protected LoaderManager.LoaderCallbacks getLoaderCallbacks(int paramInt)
  {
    return null;
  }
  
  public IPurchaseHelper getPurchaseHelper()
  {
    return this.mPurchaseHelper;
  }
  
  public DSLoaderManager getSupportLoaderManager()
  {
    return this.mLoaderManager;
  }
  
  public int getTaskCount()
  {
    return this.m_CurrentTasks.size();
  }
  
  public List<DSActivityTask<?, ?, ?>> getTasks()
  {
    return Collections.unmodifiableList(this.m_CurrentTasks);
  }
  
  protected void handleOAuth()
  {
    User localUser = ((DSApplication)getApplication()).getCurrentUser();
    if (localUser != null) {
      handleOAuth(localUser.getOAuthToken());
    }
  }
  
  protected void handleOAuth(@Nullable AccessToken paramAccessToken)
  {
    DSApplication localDSApplication = (DSApplication)getApplication();
    if ((!localDSApplication.isOAuthInProgress()) && (paramAccessToken != null) && (paramAccessToken.getErrorCode() != null))
    {
      if ((paramAccessToken.getErrorCode() != AccessToken.Error.unrecoverable) && (paramAccessToken.getErrorCode() != AccessToken.Error.invalid_grant)) {
        break label134;
      }
      localDSApplication.setOAuthInProgress(true);
      if (paramAccessToken.getErrorCode() == AccessToken.Error.invalid_grant)
      {
        DSAnalyticsUtil.getTrackerInstance(getApplicationContext()).sendLoginEvent();
        DSAnalyticsUtil.getTrackerInstance(getApplicationContext()).sendEvent("login", "refresh_token_expired", "account_server_refresh_access_token", null);
      }
      if (localDSApplication.getCurrentUser() != null)
      {
        if (paramAccessToken.getErrorMessage() != null) {
          Toast.makeText(this, paramAccessToken.getErrorMessage(), 1).show();
        }
        ((DSApplication)getApplication()).logout(this);
      }
      localDSApplication.setOAuthInProgress(false);
    }
    label134:
    while (this.mLoginDialog.isShowing()) {
      return;
    }
    DSUiUtils.setAnonymousAlertDialogWidth(this, this.mLoginDialog);
    this.mLoginDialog.show();
  }
  
  public boolean hasPermission(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 4096);
        if ((paramContext == null) || (paramContext.requestedPermissions == null)) {
          continue;
        }
        paramContext = paramContext.requestedPermissions;
        int j = paramContext.length;
        int i = 0;
        while (i < j)
        {
          boolean bool = paramContext[i].equals(paramString);
          if (bool) {
            return true;
          }
          i += 1;
        }
        return false;
      }
      catch (Throwable paramContext) {}
    }
  }
  
  protected void hideKeyboard()
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    View localView = getCurrentFocus();
    if (localView != null) {
      localInputMethodManager.hideSoftInputFromWindow(localView.getWindowToken(), 0);
    }
  }
  
  public boolean isCameraAvailable()
  {
    return getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public boolean isCameraPermissionAlreadyGranted()
  {
    return isPermissionAlreadyGranted("android.permission.CAMERA");
  }
  
  public boolean isConnectedThrowToast()
  {
    if (!((DSApplication)getApplication()).isConnected())
    {
      Toast.makeText(getApplication(), getString(2131100867), 0).show();
      return false;
    }
    return true;
  }
  
  public boolean isContactsPermissionAlreadyGranted()
  {
    return isPermissionAlreadyGranted("android.permission.READ_CONTACTS");
  }
  
  public boolean isExternalStorageReadable()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) || ("mounted_ro".equals(str));
  }
  
  public boolean isGetAccountsPermissionAlreadyGranted()
  {
    return isPermissionAlreadyGranted("android.permission.GET_ACCOUNTS");
  }
  
  public boolean isLocationPermissionAlreadyGranted()
  {
    return isPermissionAlreadyGranted("android.permission.ACCESS_FINE_LOCATION");
  }
  
  public boolean isPermissionAlreadyGranted(String paramString)
  {
    return checkSelfPermission(paramString) == 0;
  }
  
  public boolean isStoragePermissionAlreadyGranted()
  {
    return isPermissionAlreadyGranted("android.permission.READ_EXTERNAL_STORAGE");
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    case 1: 
    case 2: 
      do
      {
        return;
        finishedSharingDocuSign(null);
        return;
      } while (this.mPurchaseHelper == null);
      try
      {
        this.mPurchaseHelper.handlePurchaseResult(paramIntent);
        return;
      }
      catch (RemoteException paramIntent)
      {
        showErrorDialog(getString(2131100160), paramIntent.getMessage());
        return;
      }
    }
    ((DSApplication)getApplication()).setOAuthInProgress(false);
    if (paramInt2 == -1)
    {
      paramIntent = (User)paramIntent.getParcelableExtra(DSOAuthActivity.EXTRA_USER);
      ((DSApplication)getApplication()).setCurrentUser(paramIntent, true);
      return;
    }
    if (paramIntent != null) {}
    for (paramIntent = (AccessToken)paramIntent.getParcelableExtra(DSOAuthActivity.EXTRA_ERROR);; paramIntent = new AccessToken(AccessToken.Error.unrecoverable, null))
    {
      handleOAuth(paramIntent);
      return;
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.mPurchaseHelper = new PurchaseProductHelper(this);
    super.onCreate(paramBundle);
    if (!Boolean.valueOf(ObjectPersistenceFactory.buildIScreenshot(this).isScreenshotEnabled()).booleanValue()) {
      getWindow().addFlags(8192);
    }
    for (;;)
    {
      paramBundle = this.m_CurrentTasks.iterator();
      while (paramBundle.hasNext()) {
        ((DSActivityTask)paramBundle.next()).onActivityCreated();
      }
      getWindow().clearFlags(8192);
    }
    paramBundle = getLastCurrentTasksInstance();
    if (paramBundle != null) {
      this.m_CurrentTasks.addAll(paramBundle);
    }
    this.mProgressDialogs = new ArrayList();
    this.mLoaderManager = new DSLoaderManager(super.getSupportLoaderManager());
    paramBundle = new IntentFilter("com.docusign.ink.ACTION_LOGOUT");
    paramBundle.setPriority(2);
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mLogoutReceiver, paramBundle);
    paramBundle = new IntentFilter("com.docusign.ink.ACTION_LOGIN");
    paramBundle.setPriority(2);
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mLoginReceiver, paramBundle);
    paramBundle = new IntentFilter("com.docusign.ink.ACTION_OAUTH");
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mOAuthReceiver, paramBundle);
    startService(new Intent(this, UserSettingsService.class));
    registerAppRestrictionReceiver();
    paramBundle = new IntentFilter("com.docusign.ink.ACTION_DISMISS_PROGRESS_BAR");
    paramBundle.setPriority(3);
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mDismissProgressBarReceiver, paramBundle);
    if ((((DSApplication)getApplication()).getCurrentUser() != null) && (((DSApplication)getApplication()).getCurrentUser().getAccountID() == null))
    {
      this.mDismissProgress = ProgressDialog.show(this, null, getString(2131100869));
      this.mDismissProgress.setCanceledOnTouchOutside(false);
      this.mDismissProgress.setCancelable(false);
    }
    this.mLoginDialog = new AlertDialog.Builder(this).setTitle(getString(2131100425)).setMessage(getString(2131100424)).setPositiveButton(getString(2131100361), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        ((DSApplication)DSActivity.this.getApplication()).setOAuthInProgress(true);
        paramAnonymousDialogInterface = ObjectPersistenceFactory.buildILogin(DSActivity.this.getApplication()).getEmailAddress();
        paramAnonymousDialogInterface = DSOAuthActivity.getAuthIntent(DSActivity.this, paramAnonymousDialogInterface, true);
        paramAnonymousDialogInterface.putExtra("forceOverrideHeight", DSActivity.this.getResources().getDimensionPixelSize(2131362043));
        DSActivity.this.startActivityForResult(paramAnonymousDialogInterface, 3);
      }
    }).setCancelable(false).create();
  }
  
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default: 
      return super.onCreateDialog(paramInt, paramBundle);
    case -2: 
      paramBundle = new AlertDialog.Builder(this);
      paramBundle.setPositiveButton(17039370, null);
      paramBundle.setMessage("");
      return paramBundle.create();
    }
    paramBundle = new AlertDialog.Builder(this);
    paramBundle.setPositiveButton(17039370, null);
    paramBundle.setNegativeButton(2131100041, null);
    paramBundle.setMessage("");
    return paramBundle.create();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if ((Toolbar)findViewById(2131624886) == null)
    {
      ActionBar localActionBar = getSupportActionBar();
      if (localActionBar != null)
      {
        localActionBar.setDisplayHomeAsUpEnabled(true);
        localActionBar.setElevation(0.0F);
      }
    }
    return super.onCreateOptionsMenu(paramMenu);
  }
  
  protected void onDestroy()
  {
    this.mPurchaseHelper.destroy();
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext())
    {
      DSActivityTask localDSActivityTask = (DSActivityTask)localIterator.next();
      localDSActivityTask.onActivityDestroyed();
      if (!localDSActivityTask.isCancelled()) {
        localDSActivityTask.cancel(true);
      }
      localIterator.remove();
    }
    localIterator = this.mProgressDialogs.iterator();
    while (localIterator.hasNext()) {
      ((ProgressDialog)localIterator.next()).dismiss();
    }
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mLogoutReceiver);
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mLoginReceiver);
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mOAuthReceiver);
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDismissProgressBarReceiver);
    unregisterReceiver(this.mRestrictionsReceiver);
    this.mDestroyedCompat = true;
    super.onDestroy();
  }
  
  protected void onLogout()
  {
    if (shouldFinishOnLogout()) {
      finish();
    }
  }
  
  protected void onPause()
  {
    this.m_StartingActivity = null;
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext()) {
      ((DSActivityTask)localIterator.next()).onActivityPaused();
    }
    ObjectPersistenceFactory.buildITimeout(getApplicationContext()).logActivityTimestamp();
    ObjectPersistenceFactory.buildICorrectAccess(getApplicationContext()).setAppPaused(true);
    super.onPause();
  }
  
  protected void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    paramBundle = getLastCurrentTasksInstance();
    if (paramBundle != null)
    {
      paramBundle = paramBundle.iterator();
      while (paramBundle.hasNext()) {
        ((DSActivityTask)paramBundle.next()).setActivity(this);
      }
    }
  }
  
  protected void onPrepareDialog(int paramInt, Dialog paramDialog, Bundle paramBundle)
  {
    switch (paramInt)
    {
    default: 
      super.onPrepareDialog(paramInt, paramDialog, paramBundle);
      return;
    case -1: 
      localObject = (AlertDialog)paramDialog;
      final CharSequence localCharSequence = paramBundle.getCharSequence("details");
      bool = paramBundle.getBoolean("closeOnFinish");
      if (localCharSequence != null)
      {
        ((AlertDialog)localObject).setButton(-2, getString(2131100041), new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            this.val$myDialog.setOnDismissListener(null);
            paramAnonymousDialogInterface.dismiss();
            DSActivity.this.showErrorDialog(localCharSequence, null, bool);
          }
        });
        ((AlertDialog)localObject).setOnDismissListener(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            if (bool) {
              DSActivity.this.finish();
            }
          }
        });
      }
      break;
    }
    paramDialog = (AlertDialog)paramDialog;
    Object localObject = paramBundle.getCharSequence("message");
    final boolean bool = paramBundle.getBoolean("closeOnFinish");
    paramDialog.setButton(-1, getString(17039370), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        if (bool) {
          DSActivity.this.finish();
        }
      }
    });
    paramDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (bool) {
          DSActivity.this.finish();
        }
      }
    });
    paramDialog.setMessage((CharSequence)localObject);
  }
  
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    boolean bool = wasPermissionGranted(paramArrayOfInt);
    switch (paramInt)
    {
    default: 
      return;
    case 1: 
      handleAccessReadContactsResult(bool);
      return;
    case 2: 
      handleAccessCameraResult(bool);
      return;
    case 3: 
      handleAccessFineLocationResult(bool);
      return;
    case 4: 
      handleAccessReadExternalStorageResult(bool);
      return;
    case 5: 
      handleAccessLocationAndContactsResult(paramArrayOfString, paramArrayOfInt);
      return;
    case 6: 
      handleAccessCameraAndStorageResult(paramArrayOfString, paramArrayOfInt);
      return;
    }
    handleAccessGetAccountsResult(bool);
  }
  
  protected void onRestart()
  {
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext()) {
      ((DSActivityTask)localIterator.next()).onActivityRestarted();
    }
    super.onRestart();
  }
  
  protected void onResume()
  {
    this.m_StartingActivity = null;
    Object localObject = this.m_CurrentTasks.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((DSActivityTask)((Iterator)localObject).next()).onActivityResumed();
    }
    getAppRestrictions();
    if (ObjectPersistenceFactory.buildITimeout(getApplicationContext()).hasTimedOut())
    {
      DSAnalyticsUtil.getTrackerInstance(getApplicationContext()).sendEvent("timeout", "logout", null, null);
      ((DSApplication)getApplication()).setCurrentUser(null);
      startActivity(new Intent(this, HomeActivity.class));
      Toast.makeText(this, 2131100777, 1).show();
    }
    localObject = new IntentFilter("com.docusign.ink.ACTION_RELOGIN");
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mReLoginReceiver, (IntentFilter)localObject);
    super.onResume();
    ObjectPersistenceFactory.buildICorrectAccess(getApplicationContext()).setAppPaused(false);
    if (ObjectPersistenceFactory.buildIPinAccess(getApplicationContext()).getScreenLocked())
    {
      localObject = new Intent(getApplicationContext(), DSPinActivity.class);
      ((Intent)localObject).putExtra("type", 4);
      ((Intent)localObject).addFlags(335544320);
      getApplication().startActivity((Intent)localObject);
    }
  }
  
  public final Object onRetainCustomNonConfigurationInstance()
  {
    Object localObject = onRetainDSNonConfigurationInstance();
    ArrayList localArrayList = new ArrayList(this.m_CurrentTasks.size());
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext())
    {
      DSActivityTask localDSActivityTask = (DSActivityTask)localIterator.next();
      if (localDSActivityTask.shouldRetainAcrossConfigurationChange())
      {
        localArrayList.add(localDSActivityTask);
        localDSActivityTask.setActivity(null);
        localIterator.remove();
      }
    }
    if (localObject == null) {
      return new Object[] { localArrayList };
    }
    return new Object[] { localArrayList, localObject };
  }
  
  protected Object onRetainDSNonConfigurationInstance()
  {
    return null;
  }
  
  protected void onStart()
  {
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext()) {
      ((DSActivityTask)localIterator.next()).onActivityStarted();
    }
    super.onStart();
    DSAnalyticsUtil.getTrackerInstance(this).activityStarted(this);
    handleOAuth();
  }
  
  protected void onStop()
  {
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext()) {
      ((DSActivityTask)localIterator.next()).onActivityStopped();
    }
    super.onStop();
    DSAnalyticsUtil.getTrackerInstance(this).activityStopped(this);
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mReLoginReceiver);
  }
  
  public void removeAllTasks(boolean paramBoolean1, boolean paramBoolean2)
  {
    Iterator localIterator = this.m_CurrentTasks.iterator();
    while (localIterator.hasNext())
    {
      DSActivityTask localDSActivityTask = (DSActivityTask)localIterator.next();
      if ((paramBoolean1) && (!localDSActivityTask.isCancelled())) {
        localDSActivityTask.cancel(paramBoolean2);
      }
      localIterator.remove();
    }
  }
  
  public void removeTask(DSActivityTask<?, ?, ?> paramDSActivityTask, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((paramBoolean1) && (!paramDSActivityTask.isCancelled())) {
      paramDSActivityTask.cancel(paramBoolean2);
    }
    this.m_CurrentTasks.remove(paramDSActivityTask);
  }
  
  public void requestCameraAccess(Object paramObject)
  {
    if (isCameraAvailable())
    {
      requestPermission(paramObject, "android.permission.CAMERA", 2);
      return;
    }
    onRequestPermissionsResult(2, new String[] { "android.permission.CAMERA" }, new int[] { -1 });
  }
  
  public void requestContactsAccess(Object paramObject)
  {
    requestPermission(paramObject, "android.permission.READ_CONTACTS", 1);
  }
  
  public void requestContactsAndLocationAccess(Object paramObject)
  {
    this.m_Interface = paramObject;
    ActivityCompat.requestPermissions(this, new String[] { "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_CONTACTS" }, 5);
  }
  
  public void requestLocationAccess(Object paramObject)
  {
    requestPermission(paramObject, "android.permission.ACCESS_FINE_LOCATION", 3);
  }
  
  public void requestPermission(Object paramObject, String paramString, int paramInt)
  {
    if ((paramObject == null) || (paramString == null)) {
      return;
    }
    this.m_Interface = paramObject;
    try
    {
      int i = checkSelfPermission(paramString);
      if (i != 0)
      {
        ActivityCompat.requestPermissions(this, new String[] { paramString }, paramInt);
        return;
      }
      onRequestPermissionsResult(paramInt, new String[] { paramString }, new int[] { i });
      return;
    }
    catch (Throwable paramObject) {}
  }
  
  public void requestStorageAccess(Object paramObject)
  {
    if (isExternalStorageReadable())
    {
      requestPermission(paramObject, "android.permission.READ_EXTERNAL_STORAGE", 4);
      return;
    }
    onRequestPermissionsResult(4, new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, new int[] { -1 });
  }
  
  public void requestStorageAndCameraAccess(Object paramObject)
  {
    this.m_Interface = paramObject;
    if ((isExternalStorageReadable()) && (isCameraAvailable()))
    {
      ActivityCompat.requestPermissions(this, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA" }, 6);
      return;
    }
    onRequestPermissionsResult(2, new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA" }, new int[] { -1, -1 });
  }
  
  public void shareDocuSign()
  {
    String[] arrayOfString = getResources().getStringArray(2131230732);
    ISharing localISharing = ObjectPersistenceFactory.buildISharing(getApplication());
    Object localObject1 = new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", "");
    Intent localIntent = null;
    Object localObject2 = ObjectPersistenceFactory.buildIRestrictions(this).getAppShareRestrictions();
    if (Build.VERSION.SDK_INT > 19)
    {
      localObject1 = AppRestrictionsUtil.getAllowedIntents(getPackageManager().queryIntentActivities((Intent)localObject1, 0), (String[])localObject2, getPackageName(), (Intent)localObject1, arrayOfString[new java.util.Random().nextInt(arrayOfString.length)]);
      if (((List)localObject1).size() < 1) {
        Toast.makeText(getApplicationContext(), getString(2131100647), 1).show();
      }
    }
    for (;;)
    {
      if (localIntent != null)
      {
        startActivityForResult(localIntent, 1);
        localISharing.setTimesShared(localISharing.getTimesShared() + 1);
        localISharing.setSharingRequestCount(Integer.MIN_VALUE);
      }
      return;
      localIntent = Intent.createChooser((Intent)((List)localObject1).remove(0), getString(2131100638)).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject1).toArray(new Intent[((List)localObject1).size()])).putExtra("android.intent.extra.SUBJECT", getString(2131100646)).putExtra("android.intent.extra.TEXT", arrayOfString[new java.util.Random().nextInt(arrayOfString.length)]);
      continue;
      Object localObject3 = getPackageManager();
      localObject2 = new HashSet();
      localObject3 = ((PackageManager)localObject3).getInstalledPackages(0).iterator();
      while (((Iterator)localObject3).hasNext()) {
        ((HashSet)localObject2).add(((PackageInfo)((Iterator)localObject3).next()).packageName);
      }
      localObject3 = new ArrayList();
      Iterator localIterator = EMAIL_CLIENTS.iterator();
      String str;
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        if (((HashSet)localObject2).contains(str)) {
          ((List)localObject3).add(new Intent((Intent)localObject1).setPackage(str).putExtra("android.intent.extra.SUBJECT", getString(2131100646)).putExtra("android.intent.extra.TEXT", Html.fromHtml(String.format(getString(2131100645), new Object[] { Integer.valueOf(localISharing.getTimesShared()) }))));
        }
      }
      localIterator = TWITTER_CLIENTS.iterator();
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        if (((HashSet)localObject2).contains(str)) {
          ((List)localObject3).add(new Intent((Intent)localObject1).setPackage(str).putExtra("android.intent.extra.TEXT", String.format(arrayOfString[new java.util.Random().nextInt(arrayOfString.length)], new Object[] { Integer.valueOf(localISharing.getTimesShared()) })));
        }
      }
      localIterator = FACEBOOK_CLIENTS.iterator();
      while (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        if (((HashSet)localObject2).contains(str)) {
          ((List)localObject3).add(new Intent((Intent)localObject1).setPackage(str).putExtra("android.intent.extra.TEXT", String.format(arrayOfString[new java.util.Random().nextInt(arrayOfString.length)], new Object[] { Integer.valueOf(localISharing.getTimesShared()) })));
        }
      }
      if (((HashSet)localObject2).contains("com.google.android.apps.plus")) {
        ((List)localObject3).add(ShareCompat.IntentBuilder.from(this).setType("text/plain").setText(String.format(arrayOfString[new java.util.Random().nextInt(arrayOfString.length)], new Object[] { Integer.valueOf(localISharing.getTimesShared()) })).getIntent().setPackage("com.google.android.apps.plus"));
      }
      if (((List)localObject3).size() < 1) {
        Toast.makeText(getApplicationContext(), getString(2131100647), 1).show();
      } else {
        localIntent = Intent.createChooser((Intent)((List)localObject3).remove(0), getString(2131100638)).putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])((List)localObject3).toArray(new Intent[((List)localObject3).size()]));
      }
    }
  }
  
  protected boolean shouldFinishOnLogout()
  {
    return true;
  }
  
  public void showCameraAccessDeniedToast()
  {
    showPermissionDeniedToast(getString(2131100489));
  }
  
  public void showContactsAccessDeniedToast()
  {
    showPermissionDeniedToast(getString(2131100490));
  }
  
  public void showErrorDialog(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
  {
    showErrorDialog(paramCharSequence1, paramCharSequence2, false);
  }
  
  public void showErrorDialog(CharSequence paramCharSequence1, final CharSequence paramCharSequence2, final boolean paramBoolean)
  {
    final AlertDialog[] arrayOfAlertDialog = new AlertDialog[2];
    arrayOfAlertDialog[0] = null;
    arrayOfAlertDialog[1] = null;
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramCharSequence1);
    localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        DSAnalyticsUtil.getTrackerInstance(DSActivity.this.getApplicationContext()).sendEvent("error_dialog", "ok", null, null);
        paramAnonymousDialogInterface.dismiss();
      }
    });
    if (paramCharSequence2 != null) {
      localBuilder.setNeutralButton(getString(2131100041), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          DSAnalyticsUtil.getTrackerInstance(DSActivity.this.getApplicationContext()).sendEvent("error_dialog", "detials", null, null);
          AlertDialog.Builder localBuilder = new AlertDialog.Builder(DSActivity.this);
          localBuilder.setMessage(paramCharSequence2);
          localBuilder.setPositiveButton(17039370, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              DSAnalyticsUtil.getTrackerInstance(DSActivity.this.getApplicationContext()).sendEvent("error_dialog_details", "ok", null, null);
              paramAnonymous2DialogInterface.dismiss();
            }
          });
          arrayOfAlertDialog[1] = localBuilder.create();
          arrayOfAlertDialog[1].setCanceledOnTouchOutside(false);
          arrayOfAlertDialog[1].setOnDismissListener(new DialogInterface.OnDismissListener()
          {
            public void onDismiss(DialogInterface paramAnonymous2DialogInterface)
            {
              DSActivity.this.mIsErrorDialogShown = false;
              if (DSActivity.15.this.val$closeOnFinish) {
                DSActivity.this.finish();
              }
            }
          });
          DSUiUtils.setAnonymousAlertDialogWidth(DSActivity.this, arrayOfAlertDialog[1]);
          arrayOfAlertDialog[1].show();
          paramAnonymousDialogInterface.dismiss();
        }
      });
    }
    arrayOfAlertDialog[0] = localBuilder.create();
    arrayOfAlertDialog[0].setCanceledOnTouchOutside(false);
    arrayOfAlertDialog[0].setOnDismissListener(new DialogInterface.OnDismissListener()
    {
      public void onDismiss(DialogInterface paramAnonymousDialogInterface)
      {
        if (arrayOfAlertDialog[1] == null)
        {
          DSActivity.this.mIsErrorDialogShown = false;
          if (paramBoolean) {
            DSActivity.this.finish();
          }
        }
      }
    });
    DSUiUtils.setAnonymousAlertDialogWidth(this, arrayOfAlertDialog[0]);
    arrayOfAlertDialog[0].show();
    this.mIsErrorDialogShown = true;
  }
  
  public void showLocationAccessDeniedToast()
  {
    showPermissionDeniedToast(getString(2131100493));
  }
  
  protected void showReLoginDialog()
  {
    new Handler(Looper.getMainLooper()).post(new Runnable()
    {
      public void run()
      {
        DSApplication localDSApplication = (DSApplication)DSActivity.this.getApplication();
        if ((!DSActivity.this.mLoginDialog.isShowing()) && (!localDSApplication.isOAuthInProgress()) && (localDSApplication.getCurrentUser() != null) && (localDSApplication.getCurrentUser().getOAuthToken() != null))
        {
          DSUiUtils.setAnonymousAlertDialogWidth(DSActivity.this, DSActivity.this.mLoginDialog);
          DSActivity.this.mLoginDialog.show();
        }
      }
    });
  }
  
  public void showStorageAccessDeniedToast()
  {
    showPermissionDeniedToast(getString(2131100491));
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt)
  {
    startActivityForResult(paramIntent, paramInt, false);
  }
  
  public void startActivityForResult(Intent paramIntent, int paramInt, boolean paramBoolean)
  {
    if ((this.m_StartingActivity != null) && (!paramBoolean))
    {
      DSLog.w(TAG, "Ignoring request to start intent " + paramIntent + " because " + this + " is already starting " + this.m_StartingActivity);
      if (DSApplication.isDebuggable()) {
        runOnUiThread(new Runnable()
        {
          public void run()
          {
            Toast.makeText(DSActivity.this, "Ignoring request to start activity, already starting a different one.", 0).show();
          }
        });
      }
      return;
    }
    this.m_StartingActivity = paramIntent;
    Intent localIntent = paramIntent;
    if (paramIntent.getClass() != Intent.class) {
      localIntent = new Intent(paramIntent);
    }
    super.startActivityForResult(localIntent, paramInt);
  }
  
  public void startOrResumeLoader(int paramInt)
  {
    Loader localLoader = this.mLoaderManager.getLoader(paramInt);
    LoaderManager.LoaderCallbacks localLoaderCallbacks = getLoaderCallbacks(paramInt);
    if (localLoaderCallbacks == null) {
      return;
    }
    if ((localLoader != null) && (localLoader.isStarted()))
    {
      this.mLoaderManager.initLoader(paramInt, null, localLoaderCallbacks);
      return;
    }
    this.mLoaderManager.restartLoader(paramInt, null, localLoaderCallbacks);
  }
  
  protected <D> LoaderManager.LoaderCallbacks<D> wrapLoaderDialog(final int paramInt, final String paramString, final DialogInterface.OnCancelListener paramOnCancelListener, final LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
  {
    paramString = ProgressDialog.show(this, null, paramString);
    paramString.setCanceledOnTouchOutside(false);
    paramString.setCancelable(true);
    paramString.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        DSActivity.this.getSupportLoaderManager().destroyLoader(paramInt);
        if (paramOnCancelListener != null) {
          paramOnCancelListener.onCancel(paramAnonymousDialogInterface);
        }
      }
    });
    paramString.setIndeterminateDrawable(getResources().getDrawable(2130837760));
    this.mProgressDialogs.add(paramString);
    new LoaderManager.LoaderCallbacks()
    {
      public Loader<D> onCreateLoader(int paramAnonymousInt, Bundle paramAnonymousBundle)
      {
        return paramLoaderCallbacks.onCreateLoader(paramAnonymousInt, paramAnonymousBundle);
      }
      
      public void onLoadFinished(Loader<D> paramAnonymousLoader, D paramAnonymousD)
      {
        paramString.dismiss();
        DSActivity.this.mProgressDialogs.remove(paramString);
        paramLoaderCallbacks.onLoadFinished(paramAnonymousLoader, paramAnonymousD);
      }
      
      public void onLoaderReset(Loader<D> paramAnonymousLoader)
      {
        paramString.dismiss();
        DSActivity.this.mProgressDialogs.remove(paramString);
        paramLoaderCallbacks.onLoaderReset(paramAnonymousLoader);
      }
    };
  }
  
  protected <D> LoaderManager.LoaderCallbacks<D> wrapLoaderDialog(int paramInt, String paramString, LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks)
  {
    return wrapLoaderDialog(paramInt, paramString, null, paramLoaderCallbacks);
  }
  
  public static abstract interface ICameraAccess
  {
    public abstract void cameraAccessGranted(boolean paramBoolean);
  }
  
  public static abstract interface IContactsAccess
  {
    public abstract void contactsAccessGranted(boolean paramBoolean);
  }
  
  public static abstract interface IGetAccountsAccess
  {
    public abstract void getAccountsAccessGranted(boolean paramBoolean);
  }
  
  public static abstract interface ILocationAccess
  {
    public abstract void locationAccessGranted(boolean paramBoolean);
  }
  
  public static abstract interface IStorageAccess
  {
    public abstract void storageAccessGranted(boolean paramBoolean);
  }
}
