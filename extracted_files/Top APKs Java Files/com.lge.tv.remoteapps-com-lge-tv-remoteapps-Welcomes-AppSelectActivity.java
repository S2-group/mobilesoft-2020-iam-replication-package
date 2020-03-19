package com.lge.tv.remoteapps.Welcomes;

import Util.PopupUtil;
import Util.SystemUtil;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.lge.tv.remoteapps.Base.BasePie;
import com.lge.tv.remoteapps.Base.LGBaseActivity;
import com.lge.tv.remoteapps.Base.ReleaseInfo;
import com.lge.tv.remoteapps.DeviceScans.AutoPairingActivity;
import com.lge.tv.remoteapps.DeviceScans.AutoPairingPadActivity;
import com.lge.tv.remoteapps.Utils.LogSavedOnFile;
import com.lge.tv.remoteapps.Utils.PreferenceUtil;
import java.util.List;

public class AppSelectActivity
  extends LGBaseActivity
{
  View.OnClickListener _clickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (paramAnonymousView.equals(AppSelectActivity.this._phoneButton)) {
        BasePie.isPad = false;
      }
      for (;;)
      {
        PreferenceUtil.saveDefaultAppType(BasePie.isPad);
        AppSelectActivity.this.goNext();
        return;
        if (paramAnonymousView.equals(AppSelectActivity.this._padButton))
        {
          BasePie.isPad = true;
        }
        else
        {
          Log.e("lg", "must not be shown! " + paramAnonymousView.getId());
          LogSavedOnFile.doLogSavedOnFile("must not be shown! " + paramAnonymousView.getId());
        }
      }
    }
  };
  Button _padButton;
  Button _phoneButton;
  
  public AppSelectActivity() {}
  
  private boolean IsInstalledNfcApp()
  {
    for (;;)
    {
      int i;
      try
      {
        localList = getPackageManager().getInstalledPackages(1);
        if (localList != null) {
          break label72;
        }
        Log.w("lg", "appinfo is null. so return false!");
        return false;
      }
      catch (Exception localException)
      {
        List localList;
        boolean bool;
        localException.printStackTrace();
      }
      if (i < localList.size())
      {
        bool = ((PackageInfo)localList.get(i)).packageName.equals("com.lge.tv.nfcapps");
        if (bool) {
          return true;
        }
        i += 1;
      }
      else
      {
        return false;
        label72:
        i = 0;
      }
    }
  }
  
  public void goNext()
  {
    if (!ReleaseInfo._isReleaseMode)
    {
      if (BasePie.isPad)
      {
        localObject = new String("LG TV Remote For Pad\n");
        String str = "version: " + SystemUtil.versionName();
        Toast.makeText(this, localObject + str, 1).show();
      }
    }
    else
    {
      if ((ReleaseInfo.majorVersion < 3.0D) || (!BasePie.isStartedByNFCLauncher)) {
        break label145;
      }
      if (!BasePie.isPad) {
        break label131;
      }
    }
    label131:
    for (Object localObject = new Intent(this, AutoPairingPadActivity.class);; localObject = new Intent(this, AutoPairingActivity.class))
    {
      ((Intent)localObject).setFlags(67108864);
      startActivityForResult((Intent)localObject);
      finish();
      return;
      localObject = new String("LG TV Remote For Phone\n");
      break;
    }
    label145:
    if (PreferenceUtil.getSavedDefaultDevice() == null)
    {
      showWelcomeActivity();
      return;
    }
    showDeviceScanActivity();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    Log.i("lg", "onConfigurationChanged");
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    boolean bool = PreferenceUtil.getFirstRun();
    Log.d("lg", "[checkNfcGuide] isFirstRun : " + bool);
    PreferenceUtil.setFirstRun(false);
    int i = PreferenceUtil.getSavedDefaultAppType();
    if (i == 1)
    {
      BasePie.isPad = false;
      goNext();
      return;
    }
    if (i == 2)
    {
      BasePie.isPad = true;
      goNext();
      return;
    }
    if (BasePie.isStartedByNFCLauncher)
    {
      if (BasePie.targetModeInfoforNFC != null) {
        break label253;
      }
      Log.e("lg", "BasePie.targetModeInfoforNFC is null. so show phone mode.");
      BasePie.isPad = false;
    }
    for (;;)
    {
      goNext();
      if (Build.VERSION.SDK_INT <= 10)
      {
        BasePie.isPad = false;
        PreferenceUtil.saveDefaultAppType(BasePie.isPad);
        goNext();
      }
      setContentView(2130903045);
      this._phoneButton = ((Button)findViewById(2131361811));
      this._padButton = ((Button)findViewById(2131361812));
      this._phoneButton.setOnClickListener(this._clickListener);
      this._padButton.setOnClickListener(this._clickListener);
      if ((!bool) || (BasePie.isStartedByNFCLauncher) || (!IsInstalledNfcApp())) {
        break;
      }
      PopupUtil.showMessageDialog(this, null, setPopupViewMsg(getResources().getString(2131231147) + "\n(" + getResources().getString(2131231148) + ")"), getResources().getString(2131230925), null);
      return;
      label253:
      if (BasePie.targetModeInfoforNFC.equalsIgnoreCase("phone"))
      {
        Log.d("lg", "BasePie.targetModeInfoforNFC is phone. so show phone mode.");
        BasePie.isPad = false;
      }
      else
      {
        Log.d("lg", "BasePie.targetModeInfoforNFC is pad. so show pad mode.");
        BasePie.isPad = true;
      }
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    Log.i("lg", "onOptionsItemSelected. but ignore! ");
    return true;
  }
}
