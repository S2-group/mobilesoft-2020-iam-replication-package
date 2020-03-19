package com.labs.merlinbirdid.app;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.labs.merlinbirdid.orm.dao.PackDao;
import com.labs.merlinbirdid.packs.Pack;
import com.labs.merlinbirdid.packs.PackDataProvider;
import com.labs.merlinbirdid.packs.PackInstallListener;
import com.labs.merlinbirdid.service.packs.PackInstallService;
import com.labs.merlinbirdid.service.packs.PackInstallStateManager;
import com.labs.merlinbirdid.taxonomy.TaxonomyUpdateListener;
import com.labs.merlinbirdid.util.AppTracking;
import com.labs.merlinbirdid.util.FileUtil;
import edu.cornell.birds.android.commons.util.DialogUtils;
import edu.cornell.birds.ebirdcore.models.Taxonomy;
import edu.cornell.birds.ebirdcore.services.TaxonomyUpdateService;
import edu.cornell.birds.ebirdcore.services.TaxonomyUpdateService.TaxonomyUpdateNotificationType;
import java.io.File;
import java.util.List;

public class PackDetailActivity
  extends BaseActivity
  implements PackDetailFragment.PackageActionListener
{
  public static final String EXTRA_PACKAGE = "package";
  public static final String TAG = "PackDetailActivity";
  private Pack mBirdPackage;
  private boolean mCameFromChoosePackActivity;
  private long mDownloadId;
  private DownloadManager mDownloadManager;
  private PackInstallListener mInstallListener;
  private BroadcastReceiver mPackageInstallMessageReceiver = new BroadcastReceiver()
  {
    private boolean isMyPackage(String paramAnonymousString, long paramAnonymousLong)
    {
      return (TextUtils.equals(PackDetailActivity.this.mBirdPackage.getId(), paramAnonymousString)) || (PackDetailActivity.this.mDownloadId == paramAnonymousLong);
    }
    
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = paramAnonymousIntent.getIntExtra("com.labs.merlinbirdid.service.PackInstallIntentService.EXTRA_INSTALL_STATUS", -99);
      paramAnonymousContext = paramAnonymousIntent.getStringExtra("com.labs.merlinbirdid.service.PackInstallIntentService.EXTRA_PKGID");
      long l = paramAnonymousIntent.getLongExtra("downloadId", -99L);
      if (isMyPackage(paramAnonymousContext, l)) {
        Log.d("PackDetailActivity", "onReceive(): pkgid=" + paramAnonymousContext + ", result=" + i + ", downloadId=" + l);
      }
      switch (paramAnonymousIntent.getIntExtra("action", -99))
      {
      default: 
        return;
      case 1: 
        if (i == 0)
        {
          PackDetailActivity.this.handleInstallSuccess();
          return;
        }
        PackDetailActivity.this.handleInstallFail();
        return;
      }
      PackDetailActivity.this.handleUninstallComplete();
    }
  };
  private TaxonomyUpdateListener mTaxonUpdateListener;
  private BroadcastReceiver mTaxonomyMessageReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (PackDetailActivity.this.mTaxonUpdateListener != null)
      {
        paramAnonymousContext = (TaxonomyUpdateService.TaxonomyUpdateNotificationType)paramAnonymousIntent.getSerializableExtra("TAXONOMY_UPDATE_NOTIFICATION_TYPE");
        if (paramAnonymousContext != TaxonomyUpdateService.TaxonomyUpdateNotificationType.UPDATE_IN_PROGRESS) {
          Log.d("PackDetailActivity", "Notification: " + paramAnonymousContext);
        }
      }
      switch (PackDetailActivity.6.$SwitchMap$edu$cornell$birds$ebirdcore$services$TaxonomyUpdateService$TaxonomyUpdateNotificationType[paramAnonymousContext.ordinal()])
      {
      default: 
        Log.d("PackDetailActivity", "mTaxonomyMessageReceiver.onReceive(): unknown status " + paramAnonymousContext);
      case 4: 
        return;
      case 1: 
        PackDetailActivity.this.mTaxonUpdateListener.onWillLoadFullTaxonomy();
        return;
      case 2: 
        PackDetailActivity.this.mTaxonUpdateListener.onWillCheckTaxonomyUpdates();
        return;
      case 3: 
        PackDetailActivity.this.mTaxonUpdateListener.onWillNotCheckTaxonomyUpdates();
        return;
      case 5: 
        PackDetailActivity.this.mTaxonUpdateListener.onTaxonomyVersionCheckFailed();
        PackDetailActivity.this.taxonomyUpdateFailed();
        return;
      case 6: 
        PackDetailActivity.this.mTaxonUpdateListener.onWillDownloadTaxonomy();
        return;
      case 7: 
        PackDetailActivity.this.mTaxonUpdateListener.onTaxonomyDownloadCompleted();
        return;
      case 8: 
        PackDetailActivity.this.mTaxonUpdateListener.onTaxonomyDownloadFailed();
        PackDetailActivity.this.taxonomyUpdateFailed();
        return;
      case 9: 
        PackDetailActivity.this.mTaxonUpdateListener.onWillUpdateFullTaxonomy();
        return;
      case 10: 
        long l1 = paramAnonymousIntent.getLongExtra("TAXONOMY_UPDATE_PROGRESS_CURRENT_COUNT", 0L);
        long l2 = paramAnonymousIntent.getLongExtra("TAXONOMY_UPDATE_PROGRESS_TOTAL_COUNT", 0L);
        int i = (int)((float)l1 / (float)l2 * 100.0F);
        PackDetailActivity.this.mTaxonUpdateListener.onTaxonomyUpdateProgress(i);
        return;
      case 11: 
        PackDetailActivity.this.mTaxonUpdateListener.onTaxonomyUpdateCompleted();
        PackDetailActivity.this.taxonomyUpdateSucceeded();
        return;
      }
      PackDetailActivity.this.mTaxonUpdateListener.onTaxonomyUpdateFailed();
      PackDetailActivity.this.taxonomyUpdateFailed();
    }
  };
  
  public PackDetailActivity() {}
  
  private boolean checkDiskSpace()
  {
    return FileUtil.hasEnoughSpaceForPackageExtraction(this.mBirdPackage);
  }
  
  private void confirmUninstall()
  {
    List localList = new PackDao().getInstalledPackages();
    if ((localList != null) && (localList.size() == 1))
    {
      alert(2131165384, 2131165360, 2131165586, 2131165250, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          default: 
            if (PackDetailActivity.this.mInstallListener != null) {
              PackDetailActivity.this.mInstallListener.onUninstallCancel();
            }
            return;
          }
          if (PackDetailActivity.this.mInstallListener != null) {
            PackDetailActivity.this.mInstallListener.onUninstallStart();
          }
          PackDetailActivity.this.uninstall();
        }
      });
      return;
    }
    alert(2131165384, 2131165352, 2131165586, 2131165250, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          if (PackDetailActivity.this.mInstallListener != null) {
            PackDetailActivity.this.mInstallListener.onUninstallCancel();
          }
          return;
        }
        if (PackDetailActivity.this.mInstallListener != null) {
          PackDetailActivity.this.mInstallListener.onUninstallStart();
        }
        PackDetailActivity.this.uninstall();
      }
    });
  }
  
  private DownloadManager.Request createDownloadRequest()
  {
    DownloadManager.Request localRequest = new DownloadManager.Request(Uri.parse(getDownloadUrl()));
    localRequest.setTitle(String.format(getString(2131165353), new Object[] { this.mBirdPackage.getName() }));
    localRequest.setDescription(getString(2131165386));
    Uri localUri = determineBestDownloadLocation();
    if (localUri != null)
    {
      Log.d("PackDetailActivity", "createDownloadRequest(): download should be saved to = " + localUri);
      File localFile = new File(localUri.getPath());
      if (localFile != null) {
        localFile.mkdirs();
      }
      Log.d("PackDetailActivity", "createDownloadRequest():file = " + localFile.getAbsolutePath());
      localRequest.setDestinationUri(localUri);
    }
    return localRequest;
  }
  
  private Uri determineBestDownloadLocation()
  {
    Object localObject2 = null;
    Object localObject1 = getExternalFilesDir(null);
    if ((localObject1 != null) && (FileUtil.hasEnoughSpaceForPackageDownload(this.mBirdPackage, (File)localObject1))) {
      localObject1 = Uri.fromFile((File)localObject1);
    }
    File localFile;
    do
    {
      do
      {
        return localObject1;
        localObject1 = getFilesDir();
        if ((localObject1 != null) && (FileUtil.hasEnoughSpaceForPackageDownload(this.mBirdPackage, (File)localObject1))) {
          return Uri.fromFile((File)localObject1);
        }
        localFile = getCacheDir();
        localObject1 = localObject2;
      } while (localFile == null);
      localObject1 = localObject2;
    } while (!FileUtil.hasEnoughSpaceForPackageDownload(this.mBirdPackage, localFile));
    return Uri.fromFile(localFile);
  }
  
  private void displayCannotUpdateTaxonomy()
  {
    DialogUtils.alert(this, 2131165382, 2131165351);
  }
  
  private void displayNoSpaceAlert()
  {
    DialogUtils.alert(this, 2131165344, 2131165358);
  }
  
  private void doTaxonomyUpdate()
  {
    if (PackInstallStateManager.getInstance().isAnyPackBeingInstalled())
    {
      displayCannotUpdateTaxonomy();
      return;
    }
    PackInstallStateManager.getInstance().markOngoingTaxonomyUpdate();
    if (this.mTaxonUpdateListener != null) {
      this.mTaxonUpdateListener.onTaxonomyUpdateStart();
    }
    startTaxonomyUpdateService();
  }
  
  private void downloadPackage()
  {
    DownloadManager.Request localRequest = createDownloadRequest();
    this.mDownloadId = this.mDownloadManager.enqueue(localRequest);
    if (this.mInstallListener != null) {
      this.mInstallListener.onDownloadRequested(this.mDownloadId);
    }
    PackInstallStateManager.getInstance().storeDownloadId(this.mBirdPackage, this.mDownloadId);
    AppTracking.onPackInstallRequested(this.mBirdPackage);
  }
  
  private Pack getBirdPackage(Bundle paramBundle)
  {
    if (paramBundle != null) {
      return (Pack)paramBundle.getParcelable("package");
    }
    return (Pack)getIntent().getParcelableExtra("package");
  }
  
  private String getDownloadUrl()
  {
    String str = PackDataProvider.getBaseDownloadUrl(this) + getZipFileName();
    Log.d("PackDetailActivity", "getDownloadUrl(): url = " + str);
    return str;
  }
  
  private String getZipFileName()
  {
    return this.mBirdPackage.getDownloadFileName(this.mBirdPackage.isUpdate());
  }
  
  private void handleInstallFail()
  {
    if (this.mInstallListener != null) {
      this.mInstallListener.onInstallFail();
    }
  }
  
  private void handleInstallSuccess()
  {
    if (this.mInstallListener != null) {
      this.mInstallListener.onInstallSuccess();
    }
    if (this.mCameFromChoosePackActivity) {
      startActivity(new Intent(this, PacksActivity.class));
    }
    finish();
  }
  
  private void handleUninstallComplete()
  {
    if (this.mInstallListener != null) {
      this.mInstallListener.onUninstallSuccess();
    }
  }
  
  private boolean isTaxonomyUpdateRequired()
  {
    String str = this.mBirdPackage.getTaxonomyVersion();
    Log.d("PackDetailActivity", "taxonomyVersion = " + str);
    Object localObject = Taxonomy.getActiveVersion();
    if (localObject != null) {}
    try
    {
      Log.d("PackDetailActivity", "active taxonomy version = " + ((Taxonomy)localObject).version);
      localObject = Double.valueOf(((Taxonomy)localObject).version);
      int i = Double.valueOf(str).compareTo((Double)localObject);
      return i > 0;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Log.w("PackDetailActivity", "isTaxonomyUpdateRequired()", localNumberFormatException);
    }
    return true;
  }
  
  private void onAfterTaxonomyUpdateCheck()
  {
    if (checkDiskSpace())
    {
      downloadPackage();
      return;
    }
    displayNoSpaceAlert();
  }
  
  private void promptForTaxonomyUpdate()
  {
    if (!isFinishing())
    {
      DialogInterface.OnClickListener local1 = new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          switch (paramAnonymousInt)
          {
          default: 
            return;
          }
          PackDetailActivity.this.doTaxonomyUpdate();
        }
      };
      new AlertDialog.Builder(this).setTitle(2131165382).setMessage(2131165381).setPositiveButton(2131165480, local1).setNegativeButton(2131165507, local1).create().show();
    }
  }
  
  private void registerMessageReceivers()
  {
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mPackageInstallMessageReceiver, new IntentFilter("com.labs.merlinbirdid.service.PackInstallIntentService.BROADCAST_ACTION_FINISH"));
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mTaxonomyMessageReceiver, new IntentFilter("edu.cornell.birds.ebird.notifications.taxonomy_update"));
  }
  
  private void startTaxonomyUpdateService()
  {
    Intent localIntent = new Intent(this, TaxonomyUpdateService.class);
    localIntent.putExtra("edu.cornell.birds.ebird.locale_change", true);
    localIntent.putExtra("edu.cornell.birds.ebird.taxonomy.categories", "species,hybrid");
    startService(localIntent);
  }
  
  private void startUninstallService()
  {
    Intent localIntent = new Intent(this, PackInstallService.class);
    localIntent.putExtra("package", this.mBirdPackage);
    localIntent.putExtra("action", 2);
    startService(localIntent);
  }
  
  private void taxonomyUpdateFailed()
  {
    Log.d("PackDetailActivity", "taxonomyUpdateFailed()");
    PackInstallStateManager.getInstance().clearOngoingTaxonomyUpdate();
  }
  
  private void taxonomyUpdateSucceeded()
  {
    Log.d("PackDetailActivity", "taxonomyUpdateSucceded()");
    PackInstallStateManager.getInstance().clearOngoingTaxonomyUpdate();
    onAfterTaxonomyUpdateCheck();
  }
  
  private void uninstall()
  {
    startUninstallService();
  }
  
  private void unregisterMessageReceivers()
  {
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mPackageInstallMessageReceiver);
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mTaxonomyMessageReceiver);
  }
  
  public void alert(int paramInt1, int paramInt2, int paramInt3, int paramInt4, DialogInterface.OnClickListener paramOnClickListener)
  {
    if (!isFinishing()) {
      new AlertDialog.Builder(this).setTitle(getString(paramInt1) + this.mBirdPackage.getName()).setMessage(paramInt2).setPositiveButton(paramInt3, paramOnClickListener).setNegativeButton(paramInt4, paramOnClickListener).create().show();
    }
  }
  
  public Fragment getContentFragment()
  {
    PackDetailFragment localPackDetailFragment = new PackDetailFragment();
    setInstallListener(localPackDetailFragment);
    setTaxonUpdateListener(localPackDetailFragment);
    return localPackDetailFragment;
  }
  
  public void init()
  {
    super.init();
    setTitle(this.mBirdPackage.getName());
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    this.mBirdPackage = getBirdPackage(paramBundle);
    this.mDownloadManager = ((DownloadManager)getSystemService("download"));
    this.mCameFromChoosePackActivity = getIntent().getBooleanExtra("choosePack", false);
    super.onCreate(paramBundle);
    AppTracking.onPackDetailsViewed(this.mBirdPackage);
  }
  
  public void onInstallPackageClicked(View paramView)
  {
    if (isTaxonomyUpdateRequired())
    {
      promptForTaxonomyUpdate();
      return;
    }
    onAfterTaxonomyUpdateCheck();
  }
  
  public void onPackageDownloadCancelClicked()
  {
    Log.d("PackDetailActivity", "onPackageDownloadCancelClicked() - removing download " + this.mDownloadId);
    this.mDownloadManager.remove(new long[] { this.mDownloadId });
    AppTracking.onPackInstallCancelled(this.mBirdPackage);
  }
  
  public void onPause()
  {
    super.onPause();
    unregisterMessageReceivers();
  }
  
  public void onResume()
  {
    super.onResume();
    registerMessageReceivers();
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putParcelable("package", this.mBirdPackage);
  }
  
  public void onUninstallPackageClicked(View paramView)
  {
    confirmUninstall();
  }
  
  public void onUpdatePackageClicked(View paramView)
  {
    onInstallPackageClicked(paramView);
  }
  
  public void setInstallListener(PackInstallListener paramPackInstallListener)
  {
    this.mInstallListener = paramPackInstallListener;
  }
  
  public void setTaxonUpdateListener(TaxonomyUpdateListener paramTaxonomyUpdateListener)
  {
    this.mTaxonUpdateListener = paramTaxonomyUpdateListener;
  }
}
