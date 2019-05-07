package com.plantronics.headsetservice.ui.fragments;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.plantronics.appcore.bluetooth.beans.RuntimeHeadsetStateBean;
import com.plantronics.appcore.ui.fragments.IFragmentsHandler;
import com.plantronics.headsetservice.ApplicationObject;
import com.plantronics.headsetservice.activities.MainFragmentActivity;
import com.plantronics.headsetservice.masterlist.MasterListUtilities;
import com.plantronics.headsetservice.masterlist.beans.Headset;
import com.plantronics.headsetservice.settings.SettingsConstants;
import com.plantronics.headsetservice.settings.controllers.appspot.AppSpotExecutionResponse;
import com.plantronics.headsetservice.settings.controllers.appspot.AppSpotSettingController;
import com.plantronics.headsetservice.settings.controllers.appspot.AppSpotViewParameters;
import com.plantronics.headsetservice.settings.controllers.appspot.AppStateResponse;
import com.plantronics.headsetservice.settings.controllers.appspot.core.AppSpot;
import com.plantronics.headsetservice.ui.adapters.SettingsScreenListAdapter.IDialogCallback;
import com.plantronics.headsetservice.ui.dialogs.HeadsetDisconnectedDialog;
import com.plantronics.headsetservice.ui.dialogs.HubDialog;
import com.plantronics.headsetservice.ui.fragments.update.FirmwareUpdateAvailableFragment;
import com.plantronics.headsetservice.utilities.firmwareupdate.OTA;
import com.plantronics.headsetservice.utilities.log.LogUtilities;
import com.plantronics.pdp.model.device.PDPDevice;
import com.plantronics.pdp.model.device.PDPDeviceManager;
import com.plantronics.pdp.protocol.command.AppSpotAppModeCommand.AppValidityMask;
import com.plantronics.pdp.protocol.command.CommandEnum;
import java.util.Iterator;
import java.util.List;

public class AlexaFragment
  extends BaseFragment
{
  private static final String ALEXA_PACKAGE = "com.amazon.dee.app";
  private boolean mAlexaDisabledByIT;
  private boolean mAlexaEnabled;
  private boolean mAlexaInstalled;
  private boolean mAlexaSupported;
  private AppSpotSettingController mAppSpotSettingController;
  private AppStateResponse mAppStateResponse = new AppStateResponse()
  {
    public void onSettingStateRead(AppSpot paramAnonymousAppSpot)
    {
      LogUtilities.d(this, "actve: " + paramAnonymousAppSpot.isEnabled() + ", supported: " + paramAnonymousAppSpot.isSupported());
      AlexaFragment localAlexaFragment = AlexaFragment.this;
      if (!paramAnonymousAppSpot.isPolicyEnabled()) {}
      for (boolean bool = true;; bool = false)
      {
        AlexaFragment.access$002(localAlexaFragment, bool);
        AlexaFragment.access$102(AlexaFragment.this, paramAnonymousAppSpot.isEnabled());
        AlexaFragment.access$202(AlexaFragment.this, paramAnonymousAppSpot.isSupported());
        AlexaFragment.this.updateUIOnMain();
        return;
      }
    }
  };
  private Button mButton;
  private TextView mDescription1;
  private TextView mDescription2;
  private TextView mDescription3;
  private TextView mDescription4;
  private Headset mHeadset;
  private boolean mHeadsetConnected;
  private LinearLayout mLinearLayout3;
  private LinearLayout mLinearLayout4;
  private TextView mTitle1;
  private TextView mTitle2;
  private TextView mTitle3;
  private TextView mTitle4;
  private ToggleButton mToggleButton;
  private HubDialog mUpdateRequiredDialog;
  private Handler uiHandler;
  
  public AlexaFragment() {}
  
  private void connectedAlexaDisabledByEIT()
  {
    LogUtilities.d(this, "Headset connected, Alexa disabled by EIT");
    this.mTitle4.setVisibility(8);
    this.mLinearLayout4.setVisibility(8);
    this.mTitle3.setVisibility(0);
    this.mLinearLayout3.setVisibility(0);
    this.mTitle3.setText(MasterListUtilities.getString(2131165671));
    this.mDescription3.setText(MasterListUtilities.getString(2131165670));
  }
  
  private void connectedAlexaEnabledAlexaInstalled()
  {
    LogUtilities.d(this, "Headset connected, Alexa enabled in Hub");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(true);
    this.mButton.setText(MasterListUtilities.getString(2131165872));
    this.mButton.setEnabled(true);
    this.mButton.setTextColor(getResources().getColor(2131558477));
    this.mButton.getBackground().setColorFilter(getResources().getColor(2131558477), PorterDuff.Mode.SRC_ATOP);
    this.mToggleButton.getBackground().clearColorFilter();
  }
  
  private void connectedAlexaEnabledAlexaNotInstalled()
  {
    LogUtilities.d(this, "Headset connected, Alexa enabled in Hub");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(true);
    this.mButton.setText(MasterListUtilities.getString(2131165789));
    this.mButton.setEnabled(true);
    this.mButton.setTextColor(getResources().getColor(2131558477));
    this.mButton.getBackground().setColorFilter(getResources().getColor(2131558477), PorterDuff.Mode.SRC_ATOP);
    this.mToggleButton.getBackground().clearColorFilter();
  }
  
  private void connectedAlexaNotEnabledAlexaInstalled()
  {
    LogUtilities.d(this, "Headset connected, Alexa not enabled in Hub, Alexa app installed");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(false);
    this.mButton.setText(MasterListUtilities.getString(2131165872));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private void connectedAlexaNotEnabledAlexaNotInstalled()
  {
    LogUtilities.d(this, "Headset connected, Alexa not enabled in Hub, Alexa app not installed");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(false);
    this.mButton.setText(MasterListUtilities.getString(2131165789));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private AppSpotViewParameters createOffDialog()
  {
    return new AppSpotViewParameters(MasterListUtilities.getString(2131165708), MasterListUtilities.getString(2131165707), MasterListUtilities.getString(2131165706), MasterListUtilities.getString(2131165709));
  }
  
  private AppSpotViewParameters createOnDialog()
  {
    return new AppSpotViewParameters(MasterListUtilities.getString(2131165719), MasterListUtilities.getString(2131165718), MasterListUtilities.getString(2131165347));
  }
  
  private void disconnectedAlexaDisabledAlexaInstalled()
  {
    LogUtilities.d(this, "Headset not connected, Alexa Disabled");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setTextColor(-7829368);
    this.mToggleButton.setEnabled(false);
    this.mButton.setText(MasterListUtilities.getString(2131165872));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private void disconnectedAlexaDisabledAlexaNotInstalled()
  {
    LogUtilities.d(this, "Headset not connected, Alexa Disabled");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setText(MasterListUtilities.getString(2131165716));
    this.mDescription4.setTextColor(-7829368);
    this.mToggleButton.setEnabled(false);
    this.mButton.setText(MasterListUtilities.getString(2131165789));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
    this.mToggleButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private void displayAlexaDialog(final boolean paramBoolean, AppSpotViewParameters paramAppSpotViewParameters)
  {
    AppSpotSettingController localAppSpotSettingController = this.mAppSpotSettingController;
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      displayDialog(localAppSpotSettingController.executeWithConfirmation(paramBoolean, paramAppSpotViewParameters, bool, new AppSpotExecutionResponse()
      {
        public void onCanceled()
        {
          ToggleButton localToggleButton = AlexaFragment.this.mToggleButton;
          if (!paramBoolean) {}
          for (boolean bool = true;; bool = false)
          {
            localToggleButton.setChecked(bool);
            return;
          }
        }
      }));
      return;
    }
  }
  
  private void displayDialog(DialogFragment paramDialogFragment)
  {
    if ((DialogFragment)getActivity().getSupportFragmentManager().findFragmentByTag(paramDialogFragment.getClass().getSimpleName()) == null)
    {
      ((MainFragmentActivity)getFragmentsHandler()).dismissAllVisibleDialogs();
      FragmentTransaction localFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
      localFragmentTransaction.add(paramDialogFragment, paramDialogFragment.getClass().getSimpleName());
      localFragmentTransaction.commitAllowingStateLoss();
    }
  }
  
  private boolean doesSelectedHeadsetContainAppSpot()
  {
    Object localObject;
    if ((this.mHeadset != null) && (this.mHeadset.getRuntimeStateBean() != null))
    {
      localObject = this.mHeadset.getRuntimeStateBean().getBluetoothDeviceObject();
      if (localObject != null) {
        break label34;
      }
    }
    label34:
    do
    {
      return false;
      localObject = PDPDeviceManager.getInstance().getPDPDevice((BluetoothDevice)localObject);
    } while ((localObject == null) || (!((PDPDevice)localObject).checkSupportForCommand(CommandEnum.APP_SPOT_APP_MODE)));
    return true;
  }
  
  private boolean isAlexaInstalled()
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.amazon.dee.app"))
      {
        LogUtilities.d(this, "Alexa is already installed.");
        return true;
      }
    }
    LogUtilities.d(this, "Alexa is not installed!");
    return false;
  }
  
  private boolean isSelectedHeadsetConnected()
  {
    Headset localHeadset = ApplicationObject.getAppInstance().getSelectedHeadset();
    return (localHeadset != null) && (localHeadset.getRuntimeStateBean() != null) && (localHeadset.getRuntimeStateBean().isConnected());
  }
  
  private void launchAlexa()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("https://alexa.amazon.com/?fragment=v2/device-settings"));
    localIntent.setFlags(268435456);
    if (localIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivity(localIntent);
    }
  }
  
  private void launchGooglePlay()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.amazon.dee.app")));
  }
  
  private void startOrInstallAlexa()
  {
    if (isAlexaInstalled())
    {
      launchAlexa();
      return;
    }
    launchGooglePlay();
  }
  
  private void updateUI()
  {
    if (this.mAlexaDisabledByIT)
    {
      connectedAlexaDisabledByEIT();
      return;
    }
    if (this.mAlexaSupported)
    {
      if (this.mHeadsetConnected)
      {
        if (this.mAlexaEnabled)
        {
          if (this.mAlexaInstalled)
          {
            connectedAlexaEnabledAlexaInstalled();
            return;
          }
          connectedAlexaEnabledAlexaNotInstalled();
          return;
        }
        if (this.mAlexaInstalled)
        {
          connectedAlexaNotEnabledAlexaInstalled();
          return;
        }
        connectedAlexaNotEnabledAlexaNotInstalled();
        return;
      }
      if (this.mAlexaInstalled)
      {
        disconnectedAlexaDisabledAlexaInstalled();
        return;
      }
      disconnectedAlexaDisabledAlexaNotInstalled();
      return;
    }
    if ((this.mHeadsetConnected) && (!doesSelectedHeadsetContainAppSpot()))
    {
      if (this.mAlexaEnabled)
      {
        if (this.mAlexaInstalled)
        {
          connectedAlexaEnabledAlexaInstalled();
          return;
        }
        connectedAlexaEnabledAlexaNotInstalled();
        return;
      }
      if (this.mAlexaInstalled)
      {
        connectedAlexaNotEnabledAlexaInstalled();
        return;
      }
      connectedAlexaNotEnabledAlexaNotInstalled();
      return;
    }
    if (this.mAlexaInstalled)
    {
      disconnectedAlexaDisabledAlexaInstalled();
      return;
    }
    disconnectedAlexaDisabledAlexaNotInstalled();
  }
  
  private void updateUIOnMain()
  {
    if (this.uiHandler.getLooper().getThread() == Thread.currentThread())
    {
      updateUI();
      return;
    }
    this.uiHandler.post(new Runnable()
    {
      public void run()
      {
        AlexaFragment.this.updateUI();
      }
    });
  }
  
  public String getActionBarHeading()
  {
    return MasterListUtilities.getString(2131165669);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    LogUtilities.d(this, "onCreateView");
    paramLayoutInflater = paramLayoutInflater.inflate(2130903063, paramViewGroup, false);
    this.mHeadset = ApplicationObject.getAppInstance().getSelectedHeadset();
    this.uiHandler = new Handler(Looper.getMainLooper());
    this.mTitle1 = ((TextView)paramLayoutInflater.findViewById(2131624039));
    this.mTitle1.setText(MasterListUtilities.getString(2131165674));
    this.mDescription1 = ((TextView)paramLayoutInflater.findViewById(2131624042));
    this.mDescription1.setText(MasterListUtilities.getString(2131165672));
    this.mTitle2 = ((TextView)paramLayoutInflater.findViewById(2131624044));
    this.mTitle2.setText(MasterListUtilities.getString(2131165675));
    this.mDescription2 = ((TextView)paramLayoutInflater.findViewById(2131624047));
    this.mDescription2.setText(MasterListUtilities.getString(2131165673));
    this.mTitle3 = ((TextView)paramLayoutInflater.findViewById(2131624049));
    this.mDescription3 = ((TextView)paramLayoutInflater.findViewById(2131624052));
    this.mToggleButton = ((ToggleButton)paramLayoutInflater.findViewById(2131624058));
    this.mToggleButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.d("toggle_item_click", "Clicked: " + AlexaFragment.this.mToggleButton.isChecked());
        boolean bool = AlexaFragment.this.mToggleButton.isChecked();
        if (!bool)
        {
          paramAnonymousView = AlexaFragment.this.createOffDialog();
          AlexaFragment.this.displayAlexaDialog(bool, paramAnonymousView);
        }
        do
        {
          return;
          if (AlexaFragment.this.doesSelectedHeadsetContainAppSpot())
          {
            Log.d("toggle_item_click", "selected headset contains appspot");
            paramAnonymousView = AlexaFragment.this.createOnDialog();
            AlexaFragment.this.displayAlexaDialog(bool, paramAnonymousView);
            return;
          }
          paramAnonymousView = ((MainFragmentActivity)AlexaFragment.this.getActivity()).getOTA();
          AlexaFragment.this.mToggleButton.setChecked(false);
        } while ((AlexaFragment.this.mHeadset == null) || (!paramAnonymousView.hasUpdate()));
        Log.d("toggle_item_click", "selected headset DOES NOT contain appspot");
        AlexaFragment.this.mToggleButton.setChecked(false);
        String str3 = MasterListUtilities.getString(2131165918);
        paramAnonymousView = MasterListUtilities.getString(2131165919);
        String str1 = MasterListUtilities.getString(2131165689);
        String str2 = MasterListUtilities.getString(2131165651);
        str3 = String.format(str3, new Object[] { AlexaFragment.this.mHeadset.getDisplayName(), MasterListUtilities.getString(2131165669) });
        AlexaFragment.access$1002(AlexaFragment.this, HubDialog.createInstance(paramAnonymousView, str3, str1, str2));
        AlexaFragment.this.mUpdateRequiredDialog.initCallback(new AlexaFragment.FirmwareUpdateRequiredDialogCallback(AlexaFragment.this));
        paramAnonymousView = AlexaFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
        paramAnonymousView.add(AlexaFragment.this.mUpdateRequiredDialog, HubDialog.class.getSimpleName());
        paramAnonymousView.commitAllowingStateLoss();
      }
    });
    this.mButton = ((Button)paramLayoutInflater.findViewById(2131624059));
    this.mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AlexaFragment.this.startOrInstallAlexa();
      }
    });
    this.mLinearLayout3 = ((LinearLayout)paramLayoutInflater.findViewById(2131624050));
    this.mTitle4 = ((TextView)paramLayoutInflater.findViewById(2131624054));
    this.mDescription4 = ((TextView)paramLayoutInflater.findViewById(2131624057));
    this.mLinearLayout4 = ((LinearLayout)paramLayoutInflater.findViewById(2131624055));
    this.mAppSpotSettingController = new AppSpotSettingController(AppSpotAppModeCommand.AppValidityMask.App_Type_Alexa_Voice, SettingsConstants.publicAppSpotController);
    this.mAppSpotSettingController.registerListener(this.mAppStateResponse);
    this.mAppSpotSettingController.register();
    return paramLayoutInflater;
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    this.mAppSpotSettingController.unregister();
  }
  
  public void onResume()
  {
    super.onResume();
    this.mHeadsetConnected = isSelectedHeadsetConnected();
    this.mAlexaInstalled = isAlexaInstalled();
    if (!this.mAppSpotSettingController.isAppSpotPolicyEnabled(AppSpotAppModeCommand.AppValidityMask.App_Type_Alexa_Voice)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mAlexaDisabledByIT = bool;
      updateUI();
      if (this.mHeadsetConnected) {
        this.mAppSpotSettingController.getSettingValue(this.mAppStateResponse);
      }
      return;
    }
  }
  
  public void refreshUi()
  {
    super.refreshUi();
    LogUtilities.d(this, "refresh UI");
    this.mHeadsetConnected = isSelectedHeadsetConnected();
    updateUI();
    if (!this.mHeadsetConnected) {
      displayDialog(new HeadsetDisconnectedDialog());
    }
  }
  
  class FirmwareUpdateRequiredDialogCallback
    implements SettingsScreenListAdapter.IDialogCallback
  {
    FirmwareUpdateRequiredDialogCallback() {}
    
    public void confirmed()
    {
      AlexaFragment.access$1002(AlexaFragment.this, null);
      AlexaFragment.this.getFragmentsHandler().goToFragment(FirmwareUpdateAvailableFragment.class, null);
    }
    
    public void rejected()
    {
      AlexaFragment.access$1002(AlexaFragment.this, null);
    }
  }
}
