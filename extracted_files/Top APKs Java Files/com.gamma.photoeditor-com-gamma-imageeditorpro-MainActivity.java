package com.gamma.imageeditorpro;

import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.adobe.creativesdk.aviary.AdobeImageIntent.Builder;
import com.alex.games.utils.AdmobLoader;
import com.alex.games.utils.AdmobLoaderManager;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.appturbo.AppPromoManager;
import com.customdialogs.Utils;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.ads.AdView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity
  extends AppCompatActivity
{
  static String proPackageId = "pro_package";
  boolean HAS_PRO = false;
  AdmobLoader admobLoader;
  Uri captureUri;
  ViewPager customGalleryPager;
  Uri lastEditedUri;
  IInAppBillingService mService;
  ServiceConnection mServiceConn = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      MainActivity.this.mService = IInAppBillingService.Stub.asInterface(paramAnonymousIBinder);
      MainActivity.this.queryPruchases();
      if (MainActivity.this.HAS_PRO) {
        MainActivity.this.hideAddViews();
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      MainActivity.this.mService = null;
    }
  };
  MenuItem noAdsMenuItem;
  GalleryPagerAdapter pagerAdapter;
  ArrayList<Uri> paths;
  File targetFile;
  
  public MainActivity() {}
  
  public void DeleteCurrentImage()
  {
    if (this.paths.size() > 0)
    {
      Uri localUri = (Uri)this.paths.get(this.customGalleryPager.getCurrentItem());
      new File(localUri.getPath()).delete();
      MediaScannerConnection.scanFile(this, new String[] { localUri.getPath() }, new String[] { "image/jpeg" }, null);
      this.paths.remove(localUri);
      this.pagerAdapter.notifyDataSetChanged();
    }
  }
  
  public void SetupIabService()
  {
    Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
    localIntent.setPackage("com.android.vending");
    bindService(localIntent, this.mServiceConn, 1);
  }
  
  void ShareImage(Uri paramUri, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("image/jpeg");
    localIntent.putExtra("android.intent.extra.STREAM", paramUri);
    if (localIntent != null) {
      localIntent.setPackage(paramString);
    }
    startActivity(Intent.createChooser(localIntent, "Photo"));
  }
  
  public void ShowDeletePopUp(FragmentActivity paramFragmentActivity)
  {
    new AlertDialog.Builder(paramFragmentActivity).setTitle("Delete").setMessage("Image will be deleted.").setPositiveButton("DELETE", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.DeleteCurrentImage();
      }
    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    }).show();
  }
  
  public void ShowInterstetial()
  {
    if ((!this.HAS_PRO) && (AdmobLoaderManager.getInstance() != null) && (PreferenceManager.getDefaultSharedPreferences(this).getInt("show_rate_counter", 0) > 0) && (AdmobLoaderManager.getInstance().ShowInterstetial())) {}
  }
  
  public void ShowRate()
  {
    if (PreferenceManager.getDefaultSharedPreferences(this).getInt("show_rate_counter", 0) < 1)
    {
      PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("show_rate_counter", 1).commit();
      Utils.ShowRatePopUp(this, new View.OnClickListener()new View.OnClickListener
      {
        public void onClick(View paramAnonymousView)
        {
          if ((!MainActivity.this.HAS_PRO) && (AdmobLoaderManager.getInstance() != null)) {
            AdmobLoaderManager.getInstance().ResetInterstetial();
          }
        }
      }, new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ((!MainActivity.this.HAS_PRO) && (AdmobLoaderManager.getInstance() != null))
          {
            AdmobLoaderManager.getInstance().SetInterstetial();
            AdmobLoaderManager.getInstance().ShowInterstetial();
          }
          while (!MainActivity.this.HAS_PRO) {
            return;
          }
        }
      });
    }
  }
  
  public void buyPro(View paramView)
  {
    try
    {
      paramView = (PendingIntent)this.mService.getBuyIntent(3, getPackageName(), proPackageId, "inapp", "wawadasdWAdasgkasflskalfdkasdfasg!12313").getParcelable("BUY_INTENT");
      if (paramView != null)
      {
        startIntentSenderForResult(paramView.getIntentSender(), 1020, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        return;
      }
      Toast.makeText(getApplicationContext(), "Failed to contact google play!", 0).show();
      return;
    }
    catch (RemoteException paramView)
    {
      paramView.printStackTrace();
      return;
    }
    catch (IntentSender.SendIntentException paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public void editImage(Uri paramUri)
  {
    File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString());
    localFile = new File(localFile.getAbsolutePath() + "/" + "PhotoEditor");
    if (!localFile.exists()) {
      localFile.mkdir();
    }
    this.targetFile = new File(localFile.getAbsolutePath() + "/img_" + System.currentTimeMillis() + ".jpg");
    startActivityForResult(new AdobeImageIntent.Builder(this).setData(paramUri).withOutput(this.targetFile).build(), 1014);
  }
  
  public void hideAddViews()
  {
    findViewById(2131886238).setVisibility(8);
    View localView = findViewById(2131886237);
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)localView.getLayoutParams();
    localLayoutParams.addRule(12, -1);
    localLayoutParams.addRule(2, 0);
    localView.setLayoutParams(localLayoutParams);
    if (this.noAdsMenuItem != null) {
      this.noAdsMenuItem.setVisible(false);
    }
  }
  
  public boolean isPackageInstalled(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = getApplicationContext().getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((PackageInfo)localIterator.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  public void loadAds()
  {
    if ((!AppPromoManager.getInstance().hasPromo()) && (!this.HAS_PRO))
    {
      this.admobLoader = new AdmobLoader((AdView)findViewById(2131886243), this);
      this.admobLoader.loadBanner();
      if ((AdmobLoaderManager.getInstance() != null) && (!AdmobLoaderManager.getInstance().isInitiated)) {
        AdmobLoaderManager.getInstance().init(getApplicationContext(), getString(2131362113));
      }
      return;
    }
    hideAddViews();
  }
  
  public void loadGallleryImages()
  {
    Cursor localCursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { "_data", "_id" }, null, null, "_id DESC");
    int j = localCursor.getCount();
    this.paths = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localCursor.moveToPosition(i);
      int k = localCursor.getColumnIndex("_data");
      this.paths.add(Uri.fromFile(new File(localCursor.getString(k))));
      i += 1;
    }
    this.pagerAdapter = new GalleryPagerAdapter(getApplicationContext(), this.paths);
    this.customGalleryPager.setAdapter(this.pagerAdapter);
    this.customGalleryPager.setPageTransformer(true, new DepthPageTransformer());
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == -1) {
      switch (paramInt1)
      {
      }
    }
    for (;;)
    {
      if (paramInt1 == 1014)
      {
        ShowInterstetial();
        ShowRate();
      }
      return;
      editImage(paramIntent.getData());
      continue;
      editImage(this.captureUri);
      continue;
      paramIntent = paramIntent.getData();
      if (this.targetFile != null)
      {
        MediaScannerConnection.scanFile(this, new String[] { this.targetFile.getPath() }, new String[] { "image/jpeg" }, null);
        getApplicationContext().sendBroadcast(new Intent("android.hardware.action.NEW_PICTURE", paramIntent));
        getApplicationContext().sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", paramIntent));
        paramIntent = Uri.fromFile(this.targetFile);
        this.lastEditedUri = paramIntent;
        this.paths.add(0, paramIntent);
      }
      this.pagerAdapter.notifyDataSetChanged();
      this.customGalleryPager.setCurrentItem(0);
      continue;
      paramIntent.getIntExtra("RESPONSE_CODE", 0);
      String str = paramIntent.getStringExtra("INAPP_PURCHASE_DATA");
      paramIntent.getStringExtra("INAPP_DATA_SIGNATURE");
      if (paramInt2 == -1) {
        try
        {
          new JSONObject(str).getString("productId");
          this.HAS_PRO = true;
          hideAddViews();
        }
        catch (JSONException paramIntent)
        {
          paramIntent.printStackTrace();
        }
      }
    }
  }
  
  public void onCameraPickClick(View paramView)
  {
    paramView = new Intent("android.media.action.IMAGE_CAPTURE");
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("title", "img_" + System.currentTimeMillis() + ".jpg");
    this.captureUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, localContentValues);
    paramView.putExtra("output", this.captureUri);
    startActivityForResult(paramView, 1011);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968601);
    EasyTracker.getInstance(this).activityStart(this);
    paramBundle = new HashMap();
    paramBundle.put("camerascreen", "camerascreenview");
    EasyTracker.getInstance(this).send(paramBundle);
    SetupIabService();
    AppPromoManager.getInstance().CheckPromoPeriod(getApplicationContext(), "05 12 00:00:00 GMT+02:00 2014", "05 27 19:00:00 GMT+02:00 2020");
    loadAds();
    this.customGalleryPager = ((ViewPager)findViewById(2131886398));
    setSupportActionBar((Toolbar)findViewById(2131886117));
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    loadGallleryImages();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131951616, paramMenu);
    this.noAdsMenuItem = paramMenu.findItem(2131886425);
    if ((this.HAS_PRO) || (AppPromoManager.getInstance().hasPromo())) {
      this.noAdsMenuItem.setVisible(false);
    }
    return true;
  }
  
  public void onEditClick(View paramView)
  {
    if (this.paths.size() > 0) {
      editImage((Uri)this.paths.get(this.customGalleryPager.getCurrentItem()));
    }
  }
  
  public void onGalleryPickClick(View paramView)
  {
    paramView = new Intent();
    paramView.setType("image/*");
    paramView.setAction("android.intent.action.GET_CONTENT");
    startActivityForResult(Intent.createChooser(paramView, "Select Picture"), 1001);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      tryShowCollage();
      return true;
      ShowDeletePopUp(this);
      return true;
      buyPro(null);
    }
  }
  
  public void onShareClick(View paramView)
  {
    if (this.paths.size() > 0) {
      ShareImage((Uri)this.paths.get(this.customGalleryPager.getCurrentItem()), null);
    }
  }
  
  protected void onStart()
  {
    super.onStart();
  }
  
  public void queryPruchases()
  {
    Object localObject = new ArrayList();
    ((ArrayList)localObject).add(proPackageId);
    new Bundle().putStringArrayList("ITEM_ID_LIST", (ArrayList)localObject);
    try
    {
      if (this.mService != null)
      {
        localObject = this.mService.getPurchases(3, getPackageName(), "inapp", null);
        if (((Bundle)localObject).getInt("RESPONSE_CODE") == 0)
        {
          localObject = ((Bundle)localObject).getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
          int i = 0;
          while (i < ((ArrayList)localObject).size())
          {
            if (((String)((ArrayList)localObject).get(i)).equals(proPackageId)) {
              this.HAS_PRO = true;
            }
            i += 1;
          }
        }
      }
      return;
    }
    catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }
  
  public void tryShowCollage()
  {
    if (!isPackageInstalled("com.gamma.photocollage")) {
      try
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "com.gamma.photocollage")));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + "com.gamma.photocollage")));
        return;
      }
    }
    try
    {
      startActivity(getPackageManager().getLaunchIntentForPackage("com.gamma.photocollage"));
      return;
    }
    catch (Error localError) {}catch (Exception localException) {}
  }
  
  public class DepthPageTransformer
    implements ViewPager.PageTransformer
  {
    public DepthPageTransformer() {}
    
    public void transformPage(View paramView, float paramFloat)
    {
      int i = paramView.getWidth();
      if (paramFloat < -1.0F)
      {
        paramView.setAlpha(0.0F);
        return;
      }
      if (paramFloat <= 0.0F)
      {
        paramView.setAlpha(1.0F);
        paramView.setTranslationX(0.0F);
        paramView.setScaleX(1.0F);
        paramView.setScaleY(1.0F);
        return;
      }
      if (paramFloat <= 1.0F)
      {
        paramView.setAlpha(1.0F - paramFloat);
        paramView.setTranslationX(i * -paramFloat);
        paramFloat = 0.75F + 0.25F * (1.0F - Math.abs(paramFloat));
        paramView.setScaleX(paramFloat);
        paramView.setScaleY(paramFloat);
        return;
      }
      paramView.setAlpha(0.0F);
    }
  }
}
