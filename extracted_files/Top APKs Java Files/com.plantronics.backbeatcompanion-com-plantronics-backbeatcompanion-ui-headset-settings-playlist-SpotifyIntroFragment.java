package com.plantronics.backbeatcompanion.ui.headset.settings.playlist;

import android.bluetooth.BluetoothDevice;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.plantronics.backbeatcompanion.databinding.FragmentSpotifyIntroBinding;
import com.plantronics.backbeatcompanion.ui.BaseFragment;
import com.plantronics.backbeatcompanion.util.AnalyticsUtil;
import com.plantronics.backbeatcompanion.util.ColorUtil;
import com.plantronics.backbeatcompanion.util.DialogHelper;
import com.plantronics.backbeatcompanion.util.DialogHelper.ConfirmDialogCallback;
import com.plantronics.backbeatcompanion.util.PreferenceHelper;
import com.plantronics.services.sdk.PltDevice;
import com.plantronics.services.sdk.PltServices;
import java.util.Iterator;
import java.util.List;

public class SpotifyIntroFragment
  extends BaseFragment
{
  public static final String SPOTIFY_PACKAGE = "com.spotify.music";
  private FragmentSpotifyIntroBinding mBinding;
  
  public SpotifyIntroFragment() {}
  
  private boolean isPackagePresent(String paramString)
  {
    Iterator localIterator = getActivity().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static SpotifyIntroFragment newInstance()
  {
    return new SpotifyIntroFragment();
  }
  
  private void openPlayStore(String paramString)
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(paramString);
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString())));
  }
  
  private void showSpotifyNotPresentDialog()
  {
    DialogHelper.showConfirmDialog(getContext(), 2131886321, 2131886320, 2131886322, 2131886183, ColorUtil.resolveColor(getContext(), 2130968741, ContextCompat.getColor(getContext(), 2131099746)), new DialogHelper.ConfirmDialogCallback()
    {
      public void onNegative() {}
      
      public void onPositive()
      {
        SpotifyIntroFragment.this.openPlayStore("com.spotify.music");
      }
    });
  }
  
  protected String[] getPageHierarchy()
  {
    return new String[] { "My Headset", "Headset Settings", "My Tap", "Spotify" };
  }
  
  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    getActivity().setTitle("");
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mBinding = FragmentSpotifyIntroBinding.inflate(paramLayoutInflater, paramViewGroup, false);
    this.mBinding.setHandler(this);
    return this.mBinding.getRoot();
  }
  
  public void onStartClicked()
  {
    if (isPackagePresent("com.spotify.music"))
    {
      PreferenceHelper.getInstance(getActivity()).storeMusicServiceTypeEnabled(MusicServiceType.Spotify, true);
      replaceFragment(PlayListFragment.newInstance(MusicServiceType.Spotify));
      AnalyticsUtil.trackEvent("Custom Button", "Music Service Updated", "Spotify", PltServices.getInstance(getContext()).getConnectedDevice().getBluetoothDevice().getAddress());
      return;
    }
    showSpotifyNotPresentDialog();
  }
}
