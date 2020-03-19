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

public class TileFragment
  extends BaseFragment
{
  private static final String TILE_PACKAGE = "com.thetileapp.tile";
  private AppSpotSettingController mAppSpotSettingController;
  private AppStateResponse mAppStateResponse = new AppStateResponse()
  {
    public void onSettingStateRead(AppSpot paramAnonymousAppSpot)
    {
      LogUtilities.d(this, "actve: " + paramAnonymousAppSpot.isEnabled() + ", supported: " + paramAnonymousAppSpot.isSupported());
      TileFragment.access$002(TileFragment.this, paramAnonymousAppSpot.isSupported());
      TileFragment.access$102(TileFragment.this, paramAnonymousAppSpot.isEnabled());
      TileFragment localTileFragment = TileFragment.this;
      if (!paramAnonymousAppSpot.isPolicyEnabled()) {}
      for (boolean bool = true;; bool = false)
      {
        TileFragment.access$202(localTileFragment, bool);
        TileFragment.this.updateUIOnMain();
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
  private boolean mTileDisabledByIT;
  private boolean mTileEnabled;
  private boolean mTileInstalled;
  private boolean mTileSupported;
  private TextView mTitle1;
  private TextView mTitle2;
  private TextView mTitle3;
  private TextView mTitle4;
  private ToggleButton mToggleButton;
  private HubDialog mUpdateRequiredDialog;
  private Handler uiHandler;
  
  public TileFragment() {}
  
  private void connectedTileDisabledByEIT()
  {
    LogUtilities.d(this, "Headset connected, Tile disabled by EIT");
    this.mTitle4.setVisibility(8);
    this.mLinearLayout4.setVisibility(8);
    this.mTitle3.setVisibility(0);
    this.mLinearLayout3.setVisibility(0);
    this.mTitle3.setText(MasterListUtilities.getString(2131165910));
    this.mDescription3.setText(MasterListUtilities.getString(2131165909));
  }
  
  private void connectedTileEnabledTileInstalled()
  {
    LogUtilities.d(this, "Headset connected, Tile enabled in Hub");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(true);
    this.mButton.setText(MasterListUtilities.getString(2131165873));
    this.mButton.setEnabled(true);
    this.mButton.setTextColor(getResources().getColor(2131558477));
    this.mButton.getBackground().setColorFilter(getResources().getColor(2131558477), PorterDuff.Mode.SRC_ATOP);
    this.mToggleButton.getBackground().clearColorFilter();
  }
  
  private void connectedTileEnabledTileNotInstalled()
  {
    LogUtilities.d(this, "Headset connected, Tile enabled in Hub");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(true);
    this.mButton.setText(MasterListUtilities.getString(2131165790));
    this.mButton.setEnabled(true);
    this.mButton.setTextColor(getResources().getColor(2131558477));
    this.mButton.getBackground().setColorFilter(getResources().getColor(2131558477), PorterDuff.Mode.SRC_ATOP);
    this.mToggleButton.getBackground().clearColorFilter();
  }
  
  private void connectedTileNotEnabledTileInstalled()
  {
    LogUtilities.d(this, "Headset connected, Tile not enabled in Hub, Tile app installed");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(false);
    this.mButton.setText(MasterListUtilities.getString(2131165873));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private void connectedTileNotEnabledTileNotInstalled()
  {
    LogUtilities.d(this, "Headset connected, Tile not enabled in Hub, Tile app not installed");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setTextColor(-16777216);
    this.mToggleButton.setEnabled(true);
    this.mToggleButton.setChecked(false);
    this.mButton.setText(MasterListUtilities.getString(2131165790));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private AppSpotViewParameters createOffDialog()
  {
    return new AppSpotViewParameters(MasterListUtilities.getString(2131165712), MasterListUtilities.getString(2131165711), MasterListUtilities.getString(2131165710), MasterListUtilities.getString(2131165713));
  }
  
  private AppSpotViewParameters createOnDialog()
  {
    return new AppSpotViewParameters(MasterListUtilities.getString(2131165720), MasterListUtilities.getString(2131165722), MasterListUtilities.getString(2131165347));
  }
  
  private void disconnectedTileDisabledTileInstalled()
  {
    LogUtilities.d(this, "Headset not connected, Tile Disabled");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setTextColor(-7829368);
    this.mToggleButton.setEnabled(false);
    this.mButton.setText(MasterListUtilities.getString(2131165873));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
  }
  
  private void disconnectedTileDisabledTileNotInstalled()
  {
    LogUtilities.d(this, "Headset not connected, Tile Disabled");
    this.mTitle3.setVisibility(8);
    this.mLinearLayout3.setVisibility(8);
    this.mTitle4.setVisibility(0);
    this.mLinearLayout4.setVisibility(0);
    this.mTitle4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setText(MasterListUtilities.getString(2131165720));
    this.mDescription4.setTextColor(-7829368);
    this.mToggleButton.setEnabled(false);
    this.mButton.setText(MasterListUtilities.getString(2131165790));
    this.mButton.setEnabled(false);
    this.mButton.setTextColor(-7829368);
    this.mButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
    this.mToggleButton.getBackground().setColorFilter(-7829368, PorterDuff.Mode.SRC_ATOP);
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
  
  private void displayTileDialog(final boolean paramBoolean, AppSpotViewParameters paramAppSpotViewParameters)
  {
    AppSpotSettingController localAppSpotSettingController = this.mAppSpotSettingController;
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      displayDialog(localAppSpotSettingController.executeWithConfirmation(paramBoolean, paramAppSpotViewParameters, bool, new AppSpotExecutionResponse()
      {
        public void onCanceled()
        {
          ToggleButton localToggleButton = TileFragment.this.mToggleButton;
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
  
  private boolean isSelectedHeadsetConnected()
  {
    Headset localHeadset = ApplicationObject.getAppInstance().getSelectedHeadset();
    return (localHeadset != null) && (localHeadset.getRuntimeStateBean() != null) && (localHeadset.getRuntimeStateBean().isConnected());
  }
  
  private boolean isTileInstalled()
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.thetileapp.tile"))
      {
        LogUtilities.d(this, "Tile is already installed.");
        return true;
      }
    }
    LogUtilities.d(this, "Tile is not installed!");
    return false;
  }
  
  private void launchGooglePlay()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.thetileapp.tile")));
  }
  
  private void launchTile()
  {
    Intent localIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.thetileapp.tile");
    if (localIntent != null) {
      startActivity(localIntent);
    }
  }
  
  private void startOrInstallTile()
  {
    if (isTileInstalled())
    {
      launchTile();
      return;
    }
    launchGooglePlay();
  }
  
  private void updateUI()
  {
    if (this.mTileDisabledByIT)
    {
      connectedTileDisabledByEIT();
      return;
    }
    if (this.mTileSupported)
    {
      if (this.mHeadsetConnected)
      {
        if (this.mTileEnabled)
        {
          if (this.mTileInstalled)
          {
            connectedTileEnabledTileInstalled();
            return;
          }
          connectedTileEnabledTileNotInstalled();
          return;
        }
        if (this.mTileInstalled)
        {
          connectedTileNotEnabledTileInstalled();
          return;
        }
        connectedTileNotEnabledTileNotInstalled();
        return;
      }
      if (this.mTileInstalled)
      {
        disconnectedTileDisabledTileInstalled();
        return;
      }
      disconnectedTileDisabledTileNotInstalled();
      return;
    }
    if ((this.mHeadsetConnected) && (!doesSelectedHeadsetContainAppSpot()))
    {
      if (this.mTileEnabled)
      {
        if (this.mTileInstalled)
        {
          connectedTileEnabledTileInstalled();
          return;
        }
        connectedTileEnabledTileNotInstalled();
        return;
      }
      if (this.mTileInstalled)
      {
        connectedTileNotEnabledTileInstalled();
        return;
      }
      connectedTileNotEnabledTileNotInstalled();
      return;
    }
    if (this.mTileInstalled)
    {
      disconnectedTileDisabledTileInstalled();
      return;
    }
    disconnectedTileDisabledTileNotInstalled();
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
        TileFragment.this.updateUI();
      }
    });
  }
  
  public String getActionBarHeading()
  {
    return MasterListUtilities.getString(2131165908);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    LogUtilities.d(this, "onCreateView");
    paramLayoutInflater = paramLayoutInflater.inflate(2130903156, paramViewGroup, false);
    this.mHeadset = ApplicationObject.getAppInstance().getSelectedHeadset();
    this.uiHandler = new Handler(Looper.getMainLooper());
    this.mTitle1 = ((TextView)paramLayoutInflater.findViewById(2131624428));
    this.mTitle1.setText(MasterListUtilities.getString(2131165913));
    this.mDescription1 = ((TextView)paramLayoutInflater.findViewById(2131624431));
    this.mDescription1.setText(MasterListUtilities.getString(2131165911));
    this.mTitle2 = ((TextView)paramLayoutInflater.findViewById(2131624433));
    this.mTitle2.setText(MasterListUtilities.getString(2131165914));
    this.mDescription2 = ((TextView)paramLayoutInflater.findViewById(2131624436));
    this.mDescription2.setText(MasterListUtilities.getString(2131165912));
    this.mTitle3 = ((TextView)paramLayoutInflater.findViewById(2131624438));
    this.mDescription3 = ((TextView)paramLayoutInflater.findViewById(2131624441));
    this.mToggleButton = ((ToggleButton)paramLayoutInflater.findViewById(2131624447));
    this.mToggleButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Log.d("toggle_item_click", "Clicked: " + TileFragment.this.mToggleButton.isChecked());
        boolean bool = TileFragment.this.mToggleButton.isChecked();
        if (!bool)
        {
          paramAnonymousView = TileFragment.this.createOffDialog();
          TileFragment.this.displayTileDialog(bool, paramAnonymousView);
        }
        do
        {
          return;
          if (TileFragment.this.doesSelectedHeadsetContainAppSpot())
          {
            Log.d("toggle_item_click", "selected headset contains appspot");
            paramAnonymousView = TileFragment.this.createOnDialog();
            TileFragment.this.displayTileDialog(bool, paramAnonymousView);
            return;
          }
          paramAnonymousView = ((MainFragmentActivity)TileFragment.this.getActivity()).getOTA();
          TileFragment.this.mToggleButton.setChecked(false);
        } while ((TileFragment.this.mHeadset == null) || (!paramAnonymousView.hasUpdate()));
        Log.d("toggle_item_click", "selected headset DOES NOT contain appspot");
        TileFragment.this.mToggleButton.setChecked(false);
        String str3 = MasterListUtilities.getString(2131165918);
        paramAnonymousView = MasterListUtilities.getString(2131165919);
        String str1 = MasterListUtilities.getString(2131165689);
        String str2 = MasterListUtilities.getString(2131165651);
        str3 = String.format(str3, new Object[] { TileFragment.this.mHeadset.getDisplayName(), MasterListUtilities.getString(2131165908) });
        TileFragment.access$1002(TileFragment.this, HubDialog.createInstance(paramAnonymousView, str3, str1, str2));
        TileFragment.this.mUpdateRequiredDialog.initCallback(new TileFragment.FirmwareUpdateRequiredDialogCallback(TileFragment.this));
        paramAnonymousView = TileFragment.this.getActivity().getSupportFragmentManager().beginTransaction();
        paramAnonymousView.add(TileFragment.this.mUpdateRequiredDialog, HubDialog.class.getSimpleName());
        paramAnonymousView.commitAllowingStateLoss();
      }
    });
    this.mButton = ((Button)paramLayoutInflater.findViewById(2131624448));
    this.mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TileFragment.this.startOrInstallTile();
      }
    });
    this.mLinearLayout3 = ((LinearLayout)paramLayoutInflater.findViewById(2131624439));
    this.mTitle4 = ((TextView)paramLayoutInflater.findViewById(2131624443));
    this.mDescription4 = ((TextView)paramLayoutInflater.findViewById(2131624446));
    this.mLinearLayout4 = ((LinearLayout)paramLayoutInflater.findViewById(2131624444));
    this.mAppSpotSettingController = new AppSpotSettingController(AppSpotAppModeCommand.AppValidityMask.App_Type_Tile, SettingsConstants.publicAppSpotController);
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
    this.mTileInstalled = isTileInstalled();
    if (!this.mAppSpotSettingController.isAppSpotPolicyEnabled(AppSpotAppModeCommand.AppValidityMask.App_Type_Tile)) {}
    for (boolean bool = true;; bool = false)
    {
      this.mTileDisabledByIT = bool;
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
      TileFragment.access$1002(TileFragment.this, null);
      TileFragment.this.getFragmentsHandler().goToFragment(FirmwareUpdateAvailableFragment.class, null);
    }
    
    public void rejected()
    {
      TileFragment.access$1002(TileFragment.this, null);
    }
  }
}
