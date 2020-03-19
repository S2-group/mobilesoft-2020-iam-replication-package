package com.willblaschko.android.abertosonorus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.willblaschko.android.lambdacommunicator.AndroidApplication;
import com.willblaschko.android.lambdacommunicator.ImplCallback;
import com.willblaschko.android.lambdacommunicator.LambdaCommunicator;
import com.willblaschko.android.lambdacommunicator.UserDevice;
import com.willblaschko.android.lambdacommunicator.Utility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends AbstractGetAccountActivity
{
  ApplicationAdapter adapter;
  View allDevices;
  TextView deviceName;
  LambdaCommunicator lambdaCommunicator;
  View loadingBackground;
  String[] playerPackages = { "com.plexapp.android", "com.pandora.android", "com.netflix.mediaclient", "com.netflix.ninja", "com.amazon.avod.thirdpartyclient", "com.google.android.youtube.tv", "com.google.android.music", "com.amazon.amazonvideo.livingroom.nvidia", "com.google.android.tv", "com.google.android.videos", "com.rhapsody", "com.spotify.tv.android", "com.spotify.music", "air.com.vudu.air.DownloaderTablet", "com.hbo.go", "com.HBO", "com.pandora.android.atv", "com.willblaschko.android.primelauncher" };
  String[] playerUris = new String[0];
  RecyclerView recyclerView;
  UserDevice user;
  
  public MainActivity() {}
  
  private void checkInstalledPackages()
  {
    new Intent("android.intent.action.MAIN", null);
    Object localObject1 = getPackageManager().getInstalledPackages(0);
    int i = this.user.getApplications().size() - 1;
    while (i >= 0)
    {
      if (TextUtils.isEmpty(AndroidApplication.getAppNameFromPkgName(this, ((AndroidApplication)this.user.getApplications().get(i)).getPackageName()))) {
        this.user.getApplications().remove(i);
      }
      i -= 1;
    }
    Object localObject2 = this.playerUris;
    int j = localObject2.length;
    i = 0;
    Object localObject3;
    while (i < j)
    {
      localObject3 = Uri.parse(localObject2[i]);
      if (!TextUtils.isEmpty(AndroidApplication.getAppNameFromUri(this, (Uri)localObject3)))
      {
        localObject3 = new AndroidApplication(this, (Uri)localObject3);
        if (!this.user.getApplications().contains(localObject3)) {
          this.user.getApplications().add(localObject3);
        }
      }
      i += 1;
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      localObject3 = this.playerPackages;
      j = localObject3.length;
      i = 0;
      while (i < j)
      {
        if (TextUtils.equals(localObject3[i], ((PackageInfo)localObject2).packageName))
        {
          AndroidApplication localAndroidApplication = new AndroidApplication(this, ((PackageInfo)localObject2).packageName);
          if (!this.user.getApplications().contains(localAndroidApplication)) {
            this.user.getApplications().add(localAndroidApplication);
          }
        }
        i += 1;
      }
    }
    if (this.adapter != null) {
      this.adapter.notifyDataSetChanged();
    }
    this.lambdaCommunicator.setUserDevice(this.user, null);
  }
  
  private void updateUI()
  {
    Object localObject = Utility.getPreferences(this).getString("device", null);
    this.deviceName.setText((CharSequence)localObject);
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      startActivity(new Intent(this, DeviceNameActivity.class));
      return;
    }
    if ((this.lambdaCommunicator != null) && (this.user != null))
    {
      this.user.setDeviceName((String)localObject);
      this.lambdaCommunicator.setUserDevice(this.user, null);
    }
    this.adapter = new ApplicationAdapter(this, this.lambdaCommunicator, this.user);
    localObject = new GridLayoutManager(this, getResources().getInteger(2131296261), 1, false);
    this.recyclerView.setLayoutManager((RecyclerView.LayoutManager)localObject);
    this.recyclerView.setAdapter(this.adapter);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361821);
    this.loadingBackground = findViewById(2131230827);
    this.deviceName = ((TextView)findViewById(2131230789));
    this.recyclerView = ((RecyclerView)findViewById(2131230854));
    this.recyclerView.addItemDecoration(new EqualSpacingItemDecoration(12, 2));
    this.allDevices = findViewById(2131230751);
    this.allDevices.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(MainActivity.this, AllDevicesActivity.class);
        MainActivity.this.startActivity(paramAnonymousView);
      }
    });
  }
  
  protected void useAccount(String paramString)
  {
    this.lambdaCommunicator = new LambdaCommunicator(this, paramString);
    this.lambdaCommunicator.getUserDevice(new ImplCallback()
    {
      public void onFailure(Exception paramAnonymousException)
      {
        super.onFailure(paramAnonymousException);
      }
      
      public void onSuccess(UserDevice paramAnonymousUserDevice)
      {
        super.onSuccess(paramAnonymousUserDevice);
        MainActivity.this.user = paramAnonymousUserDevice;
        String str = FirebaseInstanceId.getInstance().getToken();
        if (!paramAnonymousUserDevice.getPushTargets().contains(str)) {
          paramAnonymousUserDevice.getPushTargets().add(str);
        }
        MainActivity.this.lambdaCommunicator.setUserDevice(paramAnonymousUserDevice, null);
        MainActivity.this.loadingBackground.setVisibility(8);
        MainActivity.this.updateUI();
        MainActivity.this.checkInstalledPackages();
      }
    });
  }
}
