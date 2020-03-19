package com.fleetmatics.redbull.ui.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import br.com.simplepass.loading_button_lib.interfaces.OnAnimationEndListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.fleetmatics.redbull.RedbullApplication;
import com.fleetmatics.redbull.bluetooth.common.DisconnectionAlertPrefState;
import com.fleetmatics.redbull.certification.async.GetCertificationAlertsTask;
import com.fleetmatics.redbull.certification.async.GetCertificationAlertsTask.GetCertificationAlertsListener;
import com.fleetmatics.redbull.database.DiagnosticDataDbAccessor;
import com.fleetmatics.redbull.eventbus.EventBusCodes.Codes;
import com.fleetmatics.redbull.model.Alert;
import com.fleetmatics.redbull.model.DiagnosticData;
import com.fleetmatics.redbull.model.Driver;
import com.fleetmatics.redbull.model.roles.ActiveDrivers;
import com.fleetmatics.redbull.model.roles.DriverUser;
import com.fleetmatics.redbull.preferences.LogbookPreferences;
import com.fleetmatics.redbull.receivers.BluetoothReceiver;
import com.fleetmatics.redbull.receivers.BluetoothReceiver.BluetoothObservable;
import com.fleetmatics.redbull.receivers.NetworkReceiver;
import com.fleetmatics.redbull.receivers.NetworkReceiver.NetworkObservable;
import com.fleetmatics.redbull.services.ConfigDownloadDelegator;
import com.fleetmatics.redbull.services.LogUploadService;
import com.fleetmatics.redbull.services.RestartBluetoothService;
import com.fleetmatics.redbull.ui.activities.FaqActivity;
import com.fleetmatics.redbull.ui.activities.NavigationActivity;
import com.fleetmatics.redbull.ui.activities.TrainingActivity;
import com.fleetmatics.redbull.ui.contracts.MoreContract.Presenter;
import com.fleetmatics.redbull.ui.contracts.MoreContract.View;
import com.fleetmatics.redbull.ui.dialogs.DriverSelectionDialog;
import com.fleetmatics.redbull.ui.listeners.ConfirmDialogListener;
import com.fleetmatics.redbull.ui.listeners.DriverSelectionDialogListener;
import com.fleetmatics.redbull.ui.usecase.AlertUseCase;
import com.fleetmatics.redbull.utilities.AnalyticsUtils;
import com.fleetmatics.redbull.utilities.AnalyticsUtils.EVENT_TYPES;
import com.fleetmatics.redbull.utilities.DateUtils;
import com.fleetmatics.redbull.utilities.IntercomUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import timber.log.Timber;

public class MoreFragment
  extends UserFragment
  implements MoreContract.View, ConfirmDialogListener, DriverSelectionDialogListener, Observer, GetCertificationAlertsTask.GetCertificationAlertsListener
{
  public static final String DIAGNOSTIC_DATA_TAG = "DIAGNOSTIC_DATA_TAG";
  private static final String DRIVER_SELECTION = "DRIVER_SELECTION";
  public static final String EDIT_ACTIVE_REGULATION_ID = "EDIT_ACTIVE_REGULATION_ID";
  public static final String PRIVACY_POLICY_URL = "http://www.verizon.com/about/privacy/privacy-policy-summary/";
  public static final String REFRESH_DATA = "Refresh data";
  public static final String REFRESH_DATA_ID = "REFRESH_DATA_ID";
  public static final String TAG = "MoreFragment";
  public static final String UPLOAD_FILE_ID = "UPLOAD_FILE_ID";
  private String aboutText;
  @Inject
  ActiveDrivers activeDrivers;
  @BindView(2131362396)
  View activeRegulation;
  @BindView(2131362397)
  View activeRegulationDivider;
  @BindView(2131362395)
  TextView activeRegulationName;
  @Inject
  AlertUseCase alertUseCase;
  private boolean deviceOnline;
  @Inject
  DiagnosticDataFragment diagnosticDataFrag;
  @BindView(2131362383)
  SwitchCompat disconnectAlertValue;
  @Inject
  IntercomUtils intercomUtils;
  @Inject
  LogbookPreferences logbookPreferences;
  @BindView(2131362384)
  View logout;
  @BindView(2131362391)
  AppCompatButton moreRestartBluetooth;
  @Inject
  MoreContract.Presenter presenter;
  @BindView(2131362386)
  AppCompatButton privacyPolicy;
  @BindView(2131362387)
  TextView privacyPolicyGoOnline;
  @BindView(2131362388)
  CircularProgressButton refreshConfig;
  @BindView(2131362389)
  TextView refreshConfigDate;
  @BindView(2131362390)
  SwitchCompat remainLoggedIn;
  private boolean retryConfigDownload;
  private boolean retryUpload;
  @BindView(2131362392)
  AppCompatButton sendToSupport;
  @BindView(2131362393)
  TextView sendToSupportGoOnline;
  @BindView(2131362399)
  SwitchCompat shippingReference;
  @BindView(2131362400)
  SwitchCompat syncCheck;
  @BindView(2131362401)
  SwitchCompat trailerReference;
  
  public MoreFragment() {}
  
  private void changeDisconnectionAlertSound()
  {
    DisconnectionAlertPrefState.setDisconnectAlertPreference(this.disconnectAlertValue.isChecked(), getContext());
    if (this.disconnectAlertValue.isPressed())
    {
      String str;
      if (this.disconnectAlertValue.isChecked()) {
        str = "SoundOn";
      } else {
        str = "SoundOff";
      }
      AnalyticsUtils.log("More", str, AnalyticsUtils.EVENT_TYPES.SELECT);
    }
  }
  
  private void createFragments()
  {
    if (getChildFragmentManager().findFragmentByTag("DIAGNOSTIC_DATA_TAG") == null)
    {
      this.diagnosticDataFrag = new DiagnosticDataFragment();
      localFragmentTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.replace(2131361986, this.diagnosticDataFrag, "DIAGNOSTIC_DATA_TAG");
      localFragmentTransaction.commit();
      return;
    }
    FragmentTransaction localFragmentTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
    localFragmentTransaction.replace(2131361986, this.diagnosticDataFrag, "DIAGNOSTIC_DATA_TAG");
    localFragmentTransaction.show(this.diagnosticDataFrag);
    localFragmentTransaction.commit();
  }
  
  public static MoreFragment newInstance()
  {
    Bundle localBundle = new Bundle();
    MoreFragment localMoreFragment = new MoreFragment();
    localMoreFragment.setRetainInstance(true);
    localMoreFragment.setArguments(localBundle);
    return localMoreFragment;
  }
  
  private void populateDiagnosticData()
  {
    int i = this.activeDrivers.getSelectedDriverId();
    int j = this.activeDrivers.getAccountId(i);
    Iterator localIterator = new DiagnosticDataDbAccessor().getLastDiagnosticData(j, i).iterator();
    while (localIterator.hasNext()) {
      Timber.i(((DiagnosticData)localIterator.next()).toString(), new Object[0]);
    }
  }
  
  private void postDelayedOnUI(final Runnable paramRunnable, long paramLong)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        MoreFragment.this.mActivity.runOnUiThread(paramRunnable);
      }
    }, paramLong);
  }
  
  private void processRunningProcesses()
  {
    PackageManager localPackageManager = this.mActivity.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        try
        {
          String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
          if (arrayOfString != null)
          {
            int j = arrayOfString.length;
            int i = 0;
            while (i < j)
            {
              String str = arrayOfString[i];
              if (str.contains("BLUETOOTH")) {
                Timber.i("%s :%s", new Object[] { localApplicationInfo.packageName, str });
              }
              i += 1;
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          Timber.i(localNameNotFoundException, "processRunningProcesses", new Object[0]);
        }
      }
    }
  }
  
  private void removeFragments()
  {
    if (this.diagnosticDataFrag.isAdded())
    {
      FragmentTransaction localFragmentTransaction = this.mActivity.getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.remove(this.diagnosticDataFrag);
      localFragmentTransaction.commitNow();
    }
  }
  
  private void resetSendToSupport()
  {
    if (this.deviceOnline) {
      this.sendToSupport.setEnabled(true);
    }
    this.sendToSupport.setText(2131821227);
  }
  
  private void showLoader()
  {
    this.refreshConfig.startAnimation();
  }
  
  private void updateDeviceConnectedViews(boolean paramBoolean, int paramInt)
  {
    this.refreshConfig.setEnabled(this.deviceOnline);
    this.sendToSupport.setEnabled(paramBoolean);
    this.sendToSupportGoOnline.setVisibility(paramInt);
    this.privacyPolicy.setEnabled(paramBoolean);
    this.privacyPolicyGoOnline.setVisibility(paramInt);
  }
  
  @OnClick({2131362381})
  public void aboutClick()
  {
    new MaterialDialog.Builder(this.mActivity).title(2131821449).content(this.aboutText).positiveText(2131821458).onPositive(new MaterialDialog.SingleButtonCallback()
    {
      public void onClick(@NonNull MaterialDialog paramAnonymousMaterialDialog, @NonNull DialogAction paramAnonymousDialogAction)
      {
        AnalyticsUtils.log("Dialogue", "About_Dismiss", AnalyticsUtils.EVENT_TYPES.SELECT);
      }
    }).show();
    AnalyticsUtils.log("More", "About", AnalyticsUtils.EVENT_TYPES.SELECT);
  }
  
  @OnClick({2131362533})
  public void diagnosticDataClick()
  {
    createFragments();
    AnalyticsUtils.log("More", "AppDiag", AnalyticsUtils.EVENT_TYPES.SELECT);
  }
  
  public void dismissLoader(boolean paramBoolean, @NonNull final String paramString)
  {
    if ((isVisible()) && (this.refreshConfig.isAnimating().booleanValue()))
    {
      if (paramBoolean)
      {
        postDelayedOnUI(new Runnable()
        {
          public void run()
          {
            MoreFragment.this.refreshConfig.doneLoadingAnimation(ContextCompat.getColor(MoreFragment.this.getContext(), 2131100025), BitmapFactory.decodeResource(MoreFragment.this.getResources(), 2131230937));
          }
        }, 1000L);
        postDelayedOnUI(new Runnable()
        {
          public void run()
          {
            MoreFragment.this.refreshConfig.revertAnimation(new OnAnimationEndListener()
            {
              public void onAnimationEnd()
              {
                MoreFragment.this.setRefreshDate(MoreFragment.7.this.val$lastRefreshDate);
                MoreFragment.this.mActivity.refreshConfig();
              }
            });
          }
        }, 3000L);
        return;
      }
      this.refreshConfig.revertAnimation();
    }
  }
  
  @OnClick({2131362394})
  public void editActiveRegulationClick()
  {
    AnalyticsUtils.log("More", "Edit", AnalyticsUtils.EVENT_TYPES.SELECT);
    showConfirmDialog(getResources().getString(2131821249), getResources().getString(2131821248, new Object[] { this.activeDrivers.getSubstituteRulesetTitle(this.activeDrivers.getSelectedDriverId()) }), "EDIT_ACTIVE_REGULATION_ID", this, true);
  }
  
  protected int getFragmentLayout()
  {
    return 2131492966;
  }
  
  public int getStackLevel()
  {
    return FIRST_LEVEL;
  }
  
  @NonNull
  public String getTitle()
  {
    DriverUser localDriverUser = this.activeDrivers.getDriver(this.activeDrivers.getSelectedDriverId());
    if (localDriverUser != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localDriverUser.getDriverInfo().getFullName());
      localStringBuilder.append(" ");
      localStringBuilder.append(getString(2131820772));
      return localStringBuilder.toString();
    }
    return getString(2131820772);
  }
  
  public void getViewContext()
  {
    this.presenter.setViewContext(getActivity());
  }
  
  public void onCertificationAlertsReturned(@NonNull List<Alert> paramList)
  {
    if (paramList.size() > 0)
    {
      this.mActivity.showCertificationDialog(true);
      return;
    }
    this.presenter.logoutClick();
  }
  
  public void onDetach()
  {
    super.onDetach();
    try
    {
      Field localField = Fragment.class.getDeclaredField("mChildFragmentManager");
      localField.setAccessible(true);
      localField.set(this, null);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new RuntimeException(localIllegalAccessException);
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      throw new RuntimeException(localNoSuchFieldException);
    }
  }
  
  public void onDriverClick(int paramInt)
  {
    this.presenter.setDriverSelection(paramInt, getActivity());
  }
  
  @OnClick({2131362534})
  public void onFaqClicked(View paramView)
  {
    if (getActivity() != null) {
      getActivity().startActivity(FaqActivity.newIntent(getActivity()));
    }
  }
  
  @OnClick({2131362384})
  public void onLogoutClick()
  {
    AnalyticsUtils.log("More", "Logout", AnalyticsUtils.EVENT_TYPES.SELECT);
    new GetCertificationAlertsTask(this.alertUseCase, this).execute(new Void[0]);
  }
  
  public void onNegativeClick(@NonNull String paramString)
  {
    int i = paramString.hashCode();
    if (i != -1603682452)
    {
      if (i != -1340156288)
      {
        if ((i == 1843284044) && (paramString.equals("REFRESH_DATA_ID")))
        {
          i = 1;
          break label73;
        }
      }
      else if (paramString.equals("UPLOAD_FILE_ID"))
      {
        i = 0;
        break label73;
      }
    }
    else if (paramString.equals("EDIT_ACTIVE_REGULATION_ID"))
    {
      i = 2;
      break label73;
    }
    i = -1;
    switch (i)
    {
    default: 
      return;
    case 1: 
      label73:
      Timber.i("User clicked NO in refresh config dialog", new Object[0]);
      return;
    }
    Timber.i("User clicked NO in upload to support dialog", new Object[0]);
  }
  
  public void onPause()
  {
    super.onPause();
    BluetoothReceiver.getObservable().deleteObserver(this);
    NetworkReceiver.getObservable().deleteObserver(this);
  }
  
  public void onPositiveClick(@NonNull String paramString)
  {
    int i = paramString.hashCode();
    if (i != -1603682452)
    {
      if (i != -1340156288)
      {
        if ((i == 1843284044) && (paramString.equals("REFRESH_DATA_ID")))
        {
          i = 1;
          break label73;
        }
      }
      else if (paramString.equals("UPLOAD_FILE_ID"))
      {
        i = 0;
        break label73;
      }
    }
    else if (paramString.equals("EDIT_ACTIVE_REGULATION_ID"))
    {
      i = 2;
      break label73;
    }
    i = -1;
    switch (i)
    {
    default: 
      return;
    case 2: 
      this.presenter.changeActiveRegulation();
      return;
    case 1: 
      label73:
      Timber.i("User clicked YES in refresh config dialog", new Object[0]);
      ConfigDownloadDelegator.enqueueWork(getContext());
      showLoader();
      return;
    }
    Timber.i("User clicked YES in upload to support dialog", new Object[0]);
    this.sendToSupport.setEnabled(false);
    this.sendToSupport.setText(2131821474);
    populateDiagnosticData();
    processRunningProcesses();
    LogUploadService.enqueueWork(getContext());
  }
  
  public void onResume()
  {
    super.onResume();
    this.presenter.start(this);
    this.presenter.setActivity(getActivity());
    removeFragments();
    this.retryConfigDownload = this.mActivity.getRetryConfigDownload();
    this.retryUpload = this.mActivity.getRetryUpload();
    BluetoothReceiver.getObservable().addObserver(this);
    NetworkReceiver.getObservable().addObserver(this);
  }
  
  @OnClick({2131362535})
  public void onTrainingClicked(View paramView)
  {
    startActivity(TrainingActivity.newIntent(getContext()));
  }
  
  @OnClick({2131362536})
  public void onUserGuideClick()
  {
    if (this.intercomUtils.isEnabled()) {
      this.intercomUtils.displayHelpCenter();
    }
  }
  
  public void onViewCreated(@NonNull View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.disconnectAlertValue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        MoreFragment.this.changeDisconnectionAlertSound();
      }
    });
    int i = this.activeDrivers.getDrivers().size();
    boolean bool = true;
    if (i != 1) {
      bool = false;
    }
    this.remainLoggedIn.setChecked(((Boolean)this.logbookPreferences.isRemainLoggedInEnabled().blockingFirst()).booleanValue());
    this.remainLoggedIn.setEnabled(bool);
    this.syncCheck.setChecked(RedbullApplication.get().showSyncReferenceAlert());
    this.trailerReference.setChecked(RedbullApplication.get().showTrailerReferenceAlert());
    this.shippingReference.setChecked(RedbullApplication.get().showShippingReferenceAlert());
    this.remainLoggedIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        MoreFragment.this.logbookPreferences.setRemainLoggedInEnabled(paramAnonymousBoolean);
        if (paramAnonymousBoolean) {
          paramAnonymousCompoundButton = "Prompt_RemainLoggedInOn";
        } else {
          paramAnonymousCompoundButton = "Prompt_RemainLoggedInOff";
        }
        AnalyticsUtils.log("More", paramAnonymousCompoundButton, AnalyticsUtils.EVENT_TYPES.SELECT);
      }
    });
    this.syncCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        RedbullApplication.get().setShowSyncReferenceAlert(paramAnonymousBoolean);
        if (paramAnonymousBoolean) {
          paramAnonymousCompoundButton = "Prompt_SyncOn";
        } else {
          paramAnonymousCompoundButton = "Prompt_SyncOff";
        }
        AnalyticsUtils.log("More", paramAnonymousCompoundButton, AnalyticsUtils.EVENT_TYPES.SELECT);
      }
    });
    this.trailerReference.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        RedbullApplication.get().setShowTrailerReferenceAlert(paramAnonymousBoolean);
        EventBus.getDefault().post(EventBusCodes.Codes.TRAILER_REF_UPDATE);
        if (paramAnonymousBoolean) {
          paramAnonymousCompoundButton = "Prompt_TrailerOn";
        } else {
          paramAnonymousCompoundButton = "Prompt_TrailerOff";
        }
        AnalyticsUtils.log("More", paramAnonymousCompoundButton, AnalyticsUtils.EVENT_TYPES.SELECT);
      }
    });
    this.shippingReference.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        RedbullApplication.get().setShowShippingReferenceAlert(paramAnonymousBoolean);
        EventBus.getDefault().post(EventBusCodes.Codes.SHIPPING_REFERENCES_UPDATE);
        if (paramAnonymousBoolean) {
          paramAnonymousCompoundButton = "Prompt_ShipOn";
        } else {
          paramAnonymousCompoundButton = "Prompt_ShipOff";
        }
        AnalyticsUtils.log("More", paramAnonymousCompoundButton, AnalyticsUtils.EVENT_TYPES.SELECT);
      }
    });
    this.deviceOnline = NetworkReceiver.hasDataConnection();
    if (!this.deviceOnline) {
      updateDeviceConnectedViews(false, 0);
    }
    if (this.retryConfigDownload)
    {
      this.mActivity.setRetryConfigDownload(false);
      this.retryConfigDownload = false;
      onPositiveClick("REFRESH_DATA_ID");
    }
    if (this.retryUpload)
    {
      this.mActivity.setRetryUpload(false);
      this.retryUpload = false;
      onPositiveClick("UPLOAD_FILE_ID");
    }
  }
  
  public void populateAbout(@NonNull String paramString)
  {
    if (isVisible()) {
      this.aboutText = paramString;
    }
  }
  
  @OnClick({2131362386})
  public void privacyPolicyClick()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.verizon.com/about/privacy/privacy-policy-summary/"));
    if (localIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivity(localIntent);
    } else {
      Toast.makeText(getActivity(), getString(2131821368), 1).show();
    }
    AnalyticsUtils.log("More", "Privacy", AnalyticsUtils.EVENT_TYPES.SELECT);
  }
  
  @OnClick({2131362388})
  public void refreshClick()
  {
    Timber.i("User pressed refresh config", new Object[0]);
    AnalyticsUtils.log("More", "Refresh", AnalyticsUtils.EVENT_TYPES.SELECT);
    showConfirmDialog("Refresh data", getResources().getString(2131821253), "REFRESH_DATA_ID", this, true);
  }
  
  @OnClick({2131362391})
  public void restartBluetoothClick()
  {
    Timber.i("User restart bluetooth from More screen", new Object[0]);
    RestartBluetoothService.enqueueWork(getContext());
    this.moreRestartBluetooth.setEnabled(false);
    this.moreRestartBluetooth.setText(2131821471);
    AnalyticsUtils.log("More", "Restart", AnalyticsUtils.EVENT_TYPES.SELECT);
  }
  
  public void setActiveRegulation(@NonNull String paramString)
  {
    this.activeRegulationName.setText(paramString);
  }
  
  public void setActiveRegulationVisibility(boolean paramBoolean)
  {
    View localView = this.activeRegulationDivider;
    int j = 8;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 8;
    }
    localView.setVisibility(i);
    localView = this.activeRegulation;
    int i = j;
    if (paramBoolean) {
      i = 0;
    }
    localView.setVisibility(i);
  }
  
  public void setDisconnectionSoundValue(boolean paramBoolean)
  {
    if (isVisible()) {
      this.disconnectAlertValue.setChecked(paramBoolean);
    }
  }
  
  public void setLogoutButtonState(boolean paramBoolean)
  {
    this.logout.setEnabled(paramBoolean);
  }
  
  public void setRefreshDate(@NonNull String paramString)
  {
    if (isVisible()) {
      this.refreshConfigDate.setText(paramString);
    }
  }
  
  public void showDriverSelectionDialog(@NonNull ArrayList<DriverUser> paramArrayList)
  {
    paramArrayList = DriverSelectionDialog.getInstance(getString(2131820771), paramArrayList, this, "DRIVER_SELECTION");
    if (!paramArrayList.isVisible()) {
      paramArrayList.show(getFragmentManager(), "driverSelectionDialog");
    }
  }
  
  public void showErrorRefreshConfig(@NonNull String paramString)
  {
    Snackbar.make(this.mActivity.findViewById(2131361986), paramString, -2).setAction(2131821472, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!MoreFragment.this.isVisible())
        {
          MoreFragment.this.mActivity.setRetryConfigDownload(true);
          EventBus.getDefault().post(EventBusCodes.Codes.NAVIGATE_TO_MORE);
          MoreFragment.access$102(MoreFragment.this, true);
          return;
        }
        MoreFragment.this.onPositiveClick("REFRESH_DATA_ID");
      }
    }).show();
  }
  
  public void showErrorUploadFiles(@NonNull String paramString)
  {
    Snackbar.make(this.mActivity.findViewById(2131361986), paramString, -2).setAction(2131821472, new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!MoreFragment.this.isVisible())
        {
          MoreFragment.this.mActivity.setRetryUpload(true);
          EventBus.getDefault().post(EventBusCodes.Codes.NAVIGATE_TO_MORE);
          MoreFragment.access$202(MoreFragment.this, true);
          return;
        }
        MoreFragment.this.onPositiveClick("UPLOAD_FILE_ID");
      }
    }).show();
    if (isVisible()) {
      resetSendToSupport();
    }
  }
  
  public void update(java.util.Observable paramObservable, Object paramObject)
  {
    if ((paramObservable instanceof NetworkReceiver.NetworkObservable))
    {
      this.deviceOnline = NetworkReceiver.hasDataConnection();
      if (this.deviceOnline)
      {
        updateDeviceConnectedViews(true, 8);
        return;
      }
      updateDeviceConnectedViews(false, 0);
      return;
    }
    if ((paramObservable instanceof BluetoothReceiver.BluetoothObservable))
    {
      this.moreRestartBluetooth.setEnabled(true);
      this.moreRestartBluetooth.setText(2131821470);
    }
  }
  
  @OnClick({2131362392})
  public void uploadClick()
  {
    Object localObject;
    if (!NetworkReceiver.hasDataConnection())
    {
      localObject = new AlertDialog.Builder(getContext());
      ((AlertDialog.Builder)localObject).setMessage(getResources().getString(2131821127)).setCancelable(false).setPositiveButton(getResources().getText(2131821467), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
      ((AlertDialog.Builder)localObject).create().show();
    }
    else
    {
      String str1 = getResources().getString(2131821030);
      String str2 = getResources().getString(2131821029);
      if (this.logbookPreferences.getLastLogUploadTime() != 0L) {
        localObject = DateUtils.getTimeForDisplay(this.logbookPreferences.getLastLogUploadTime(), getDriverId());
      } else {
        localObject = getResources().getString(2131821465);
      }
      showConfirmDialog(str1, String.format(str2, new Object[] { localObject }), "UPLOAD_FILE_ID", getResources().getString(2131821380), getResources().getString(2131820631), this, true);
    }
    AnalyticsUtils.log("More", "File", AnalyticsUtils.EVENT_TYPES.SELECT);
  }
  
  public void uploadSuccess()
  {
    if (isVisible()) {
      resetSendToSupport();
    }
  }
}
