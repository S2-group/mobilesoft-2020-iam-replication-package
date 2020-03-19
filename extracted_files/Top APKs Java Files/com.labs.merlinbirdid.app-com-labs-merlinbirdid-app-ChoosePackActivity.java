package com.labs.merlinbirdid.app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.labs.merlinbirdid.packs.Pack;
import com.labs.merlinbirdid.packs.PackDataProvider;
import com.labs.merlinbirdid.packs.Partner;
import com.labs.merlinbirdid.service.packs.NearbyPacksIntentService;
import com.labs.merlinbirdid.service.packs.PackInstallStateManager;
import com.labs.merlinbirdid.webservice.Callback;
import com.labs.merlinbirdid.webservice.PacksClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import edu.cornell.birds.android.commons.util.DialogUtils;
import edu.cornell.birds.android.commons.util.Utilities;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChoosePackActivity
  extends AppCompatActivity
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{
  private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
  public static final String EXTRA_CHOOSE_PACK = "choosePack";
  private static final String PREF_KEY_ONBOARDING_DISPLAYED = "onboarding_displayed";
  private static final String TAG = "ChoosePackActivity";
  private GoogleApiClient mGoogleApiClient;
  private Location mLastLocation;
  private LocationRequest mLocationRequest;
  private TextView mMainText;
  private PacksClient mPkgClient;
  private ProgressDialog mProgressDialog;
  private NearbyPackagesResultReceiver mResultReceiver;
  
  public ChoosePackActivity() {}
  
  private void cachePackImages(Pack paramPack)
  {
    if (paramPack != null)
    {
      if (!TextUtils.isEmpty(paramPack.getImageUrl())) {
        Picasso.with(this).load(paramPack.getImageUrl()).fetch();
      }
      paramPack = paramPack.getPartners();
      if (paramPack != null)
      {
        int j = paramPack.length;
        int i = 0;
        while (i < j)
        {
          Object localObject = paramPack[i];
          if (!TextUtils.isEmpty(localObject.getLogoUrl())) {
            Picasso.with(this).load(localObject.getLogoUrl()).fetch();
          }
          i += 1;
        }
      }
    }
  }
  
  private Pack getSelectedPack(List<Pack> paramList)
  {
    if (paramList.size() > 1) {
      Collections.sort(paramList, new Comparator()
      {
        public int compare(Pack paramAnonymousPack1, Pack paramAnonymousPack2)
        {
          return Long.valueOf(paramAnonymousPack2.getSize()).compareTo(Long.valueOf(paramAnonymousPack1.getSize()));
        }
      });
    }
    Log.d("ChoosePackActivity", "getSelectedPack(): sorted packages = " + paramList);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      Pack localPack = (Pack)paramList.next();
      if (!localPack.isInstalled()) {
        return localPack;
      }
    }
    return null;
  }
  
  private void initButtons()
  {
    Button localButton1 = (Button)findViewById(2131624111);
    Button localButton2 = (Button)findViewById(2131624112);
    List localList = new PackDataProvider().getInstalledPackages();
    if ((localList != null) && (!localList.isEmpty()))
    {
      localButton2.setVisibility(0);
      return;
    }
    localButton1.setVisibility(0);
  }
  
  private void initGoogleApiClient()
  {
    if (this.mGoogleApiClient == null) {
      this.mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }
  }
  
  private void initLocationRequest()
  {
    this.mLocationRequest = LocationRequest.create().setNumUpdates(1).setPriority(102).setInterval(10000L).setFastestInterval(1000L);
  }
  
  private boolean isBasePackInstalled()
  {
    List localList = new PackDataProvider().getInstalledPackages();
    return (localList != null) && (!localList.isEmpty());
  }
  
  private void markOnBoardingShown()
  {
    Object localObject = Utilities.getPrefs(this);
    if (localObject != null)
    {
      Log.d("ChoosePackActivity", "markOnBoardingShown() setting flag to true");
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putBoolean("onboarding_displayed", true);
      ((SharedPreferences.Editor)localObject).apply();
    }
  }
  
  public static boolean mustBeDisplayed(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = Utilities.getPrefs(paramContext);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (!paramContext.getBoolean("onboarding_displayed", false)) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void onLocationNotAvailable()
  {
    DialogUtils.alert(this, 2131165506, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ChoosePackActivity.this.openBrowsePacks();
      }
    });
  }
  
  private void onNearbyPackagesFound(List<Pack> paramList)
  {
    Log.d("ChoosePackActivity", "onNearbyPackagesFound(): packages = " + paramList);
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = getSelectedPack(paramList);
      Log.d("ChoosePackActivity", "onNearbyPackagesFound(): pkg = " + paramList);
      if (paramList != null)
      {
        cachePackImages(paramList);
        openPackDetails(paramList);
        return;
      }
      onNoNearbyPackages();
      return;
    }
    openBrowsePacks();
  }
  
  private void onNoNearbyPackages()
  {
    DialogUtils.alert(this, 2131165505, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ChoosePackActivity.this.openBrowsePacks();
      }
    });
  }
  
  private void openBrowsePacks()
  {
    startActivity(new Intent(this, PacksActivity.class));
  }
  
  private void openHome()
  {
    Intent localIntent = new Intent(this, HomeActivity.class);
    localIntent.setFlags(268468224);
    startActivity(localIntent);
  }
  
  private void openPackDetails(Pack paramPack)
  {
    Intent localIntent = new Intent(this, PackDetailActivity.class);
    localIntent.putExtra("package", paramPack);
    localIntent.putExtra("choosePack", true);
    startActivity(localIntent);
  }
  
  private void savePackagesFile(final String paramString)
  {
    new Thread()
    {
      public void run()
      {
        PackInstallStateManager.getInstance().savePackManifest(paramString);
      }
    }.start();
  }
  
  private void startNearbyPackageService(String paramString)
  {
    Log.d("ChoosePackActivity", "startNearbyPackageService()");
    Intent localIntent = new Intent(this, NearbyPacksIntentService.class);
    localIntent.putExtra("manifest", paramString);
    localIntent.putExtra("receiver", this.mResultReceiver);
    if (this.mLastLocation == null) {}
    try
    {
      Log.d("ChoosePackActivity", "startNearbyPackageService(): waiting 5 seconds");
      Thread.sleep(5000L);
      localIntent.putExtra("location", this.mLastLocation);
      startService(localIntent);
      return;
    }
    catch (InterruptedException paramString)
    {
      for (;;)
      {
        Log.d("ChoosePackActivity", "startNearbyPackageService()", paramString);
      }
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 9000) && (paramInt2 == -1)) {
      this.mGoogleApiClient.connect();
    }
  }
  
  public void onBrowsePacksButtonClicked(View paramView)
  {
    openBrowsePacks();
  }
  
  public void onChoosePackButtonClicked(View paramView)
  {
    this.mProgressDialog.show();
    this.mPkgClient.getAvailablePackages(new Callback()
    {
      public void onFailure(IOException paramAnonymousIOException)
      {
        Log.w("ChoosePackActivity", "onFailure()", paramAnonymousIOException);
        ChoosePackActivity.this.startNearbyPackageService(null);
      }
      
      public void onResponse(String paramAnonymousString)
      {
        Log.d("ChoosePackActivity", "onResponse()");
        ChoosePackActivity.this.savePackagesFile(paramAnonymousString);
        ChoosePackActivity.this.startNearbyPackageService(paramAnonymousString);
      }
    });
  }
  
  public void onConnected(@Nullable Bundle paramBundle)
  {
    this.mLastLocation = LocationServices.FusedLocationApi.getLastLocation(this.mGoogleApiClient);
    Log.d("ChoosePackActivity", "onConnected(): mLastLocation = " + this.mLastLocation);
    if (this.mLastLocation == null) {
      LocationServices.FusedLocationApi.requestLocationUpdates(this.mGoogleApiClient, this.mLocationRequest, this);
    }
  }
  
  public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.hasResolution()) {
      try
      {
        paramConnectionResult.startResolutionForResult(this, 9000);
        return;
      }
      catch (IntentSender.SendIntentException paramConnectionResult)
      {
        Log.e("ChoosePackActivity", "onConnectionFailed(): failed to try a resolution", paramConnectionResult);
        return;
      }
    }
    Log.d("ChoosePackActivity", "onConnectionFailed(): location services connection failed with code " + paramConnectionResult.getErrorCode());
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    Log.d("ChoosePackActivity", "onConnectionSuspended(): cause = " + paramInt);
    this.mGoogleApiClient.connect();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903069);
    initGoogleApiClient();
    initLocationRequest();
    this.mResultReceiver = new NearbyPackagesResultReceiver(new Handler());
    this.mPkgClient = PacksClient.getInstance(this);
    this.mMainText = ((TextView)findViewById(2131624110));
    if (isBasePackInstalled()) {
      this.mMainText.setText(2131165407);
    }
    this.mProgressDialog = DialogUtils.makeIndeterminateProgressDialog(this, "", getString(2131165405));
    markOnBoardingShown();
    initButtons();
  }
  
  public void onGotItButtonClicked(View paramView)
  {
    openHome();
  }
  
  public void onLocationChanged(Location paramLocation)
  {
    Log.d("ChoosePackActivity", "onLocationChanged(): location = " + paramLocation);
    this.mLastLocation = paramLocation;
  }
  
  protected void onPause()
  {
    if (this.mGoogleApiClient.isConnected())
    {
      LocationServices.FusedLocationApi.removeLocationUpdates(this.mGoogleApiClient, this);
      this.mGoogleApiClient.disconnect();
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    this.mGoogleApiClient.connect();
    super.onResume();
  }
  
  @SuppressLint({"ParcelCreator"})
  class NearbyPackagesResultReceiver
    extends ResultReceiver
  {
    public NearbyPackagesResultReceiver(Handler paramHandler)
    {
      super();
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      Log.d("ChoosePackActivity", "onReceiveResult(): resultCode = " + paramInt);
      ChoosePackActivity.this.mProgressDialog.dismiss();
      switch (paramInt)
      {
      default: 
        ChoosePackActivity.this.onLocationNotAvailable();
        return;
      case 1: 
        paramBundle = paramBundle.getParcelableArrayList("result");
        Log.d("ChoosePackActivity", "onReceiveResult(): packages = " + paramBundle);
        ChoosePackActivity.this.onNearbyPackagesFound(paramBundle);
        return;
      }
      ChoosePackActivity.this.onNoNearbyPackages();
    }
  }
}
