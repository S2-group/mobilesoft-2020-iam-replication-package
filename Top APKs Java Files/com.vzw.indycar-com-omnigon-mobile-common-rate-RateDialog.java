package com.omnigon.mobile.common.rate;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import java.util.Iterator;
import java.util.List;

public class RateDialog
  extends DialogFragment
  implements DialogInterface.OnClickListener
{
  RateDialogOptions dialogOptions;
  
  public RateDialog() {}
  
  private Intent createIntentForGooglePlay(Context paramContext)
  {
    Object localObject2 = this.dialogOptions.getPackageName();
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = paramContext.getPackageName();
    }
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("https://play.google.com/store/apps/details?id=");
    ((StringBuilder)localObject2).append((String)localObject1);
    localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject2).toString()));
    if (isPackageExists(paramContext, "com.android.vending")) {
      ((Intent)localObject1).setPackage("com.android.vending");
    }
    return localObject1;
  }
  
  private boolean isPackageExists(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    super.onCancel(paramDialogInterface);
    boolean bool;
    if ((getParentFragment() instanceof OnRateButtonClickListener)) {
      bool = ((OnRateButtonClickListener)getParentFragment()).handleCancelRate();
    } else if ((getActivity() instanceof OnRateButtonClickListener)) {
      bool = ((OnRateButtonClickListener)getActivity()).handleCancelRate();
    } else {
      bool = false;
    }
    if (!bool) {
      AppRate.completeRateDialog(0);
    }
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    int i = this.dialogOptions.getButtonType(paramInt);
    boolean bool;
    if ((getParentFragment() instanceof OnRateButtonClickListener)) {
      bool = ((OnRateButtonClickListener)getParentFragment()).handleRateButtonClick(i, paramInt);
    } else if ((getActivity() instanceof OnRateButtonClickListener)) {
      bool = ((OnRateButtonClickListener)getActivity()).handleRateButtonClick(i, paramInt);
    } else {
      bool = false;
    }
    if (!bool)
    {
      switch (i)
      {
      default: 
        return;
      case 3: 
        AppRate.completeRateDialog(1);
        return;
      case 2: 
        AppRate.completeRateDialog(0);
        return;
      }
      getActivity().startActivity(createIntentForGooglePlay(getActivity()));
      AppRate.completeRateDialog(1);
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    this.dialogOptions = AppRate.getDialogOptions();
    FragmentActivity localFragmentActivity = getActivity();
    paramBundle = localFragmentActivity;
    if (this.dialogOptions.getThemeId() != 0) {
      paramBundle = new ContextThemeWrapper(localFragmentActivity, this.dialogOptions.getThemeId());
    }
    paramBundle = new AlertDialog.Builder(paramBundle);
    if (!TextUtils.isEmpty(this.dialogOptions.getTitle())) {
      paramBundle.setTitle(this.dialogOptions.getTitle());
    } else if (this.dialogOptions.getTitleResId() != 0) {
      paramBundle.setTitle(this.dialogOptions.getTitleResId());
    }
    if (!TextUtils.isEmpty(this.dialogOptions.getMessage())) {
      paramBundle.setMessage(this.dialogOptions.getMessage());
    } else if (this.dialogOptions.getMessageResId() != 0) {
      paramBundle.setMessage(this.dialogOptions.getMessageResId());
    }
    setCancelable(this.dialogOptions.isCancelable());
    if (this.dialogOptions.getButtonType(-1) != 0) {
      if (!TextUtils.isEmpty(this.dialogOptions.getButtonTexts(-1))) {
        paramBundle.setPositiveButton(this.dialogOptions.getButtonTexts(-1), this);
      } else if (this.dialogOptions.getButtonTextIds(-1) != 0) {
        paramBundle.setPositiveButton(this.dialogOptions.getButtonTextIds(-1), this);
      }
    }
    if (this.dialogOptions.getButtonType(-2) != 0) {
      if (!TextUtils.isEmpty(this.dialogOptions.getButtonTexts(-2))) {
        paramBundle.setNegativeButton(this.dialogOptions.getButtonTexts(-2), this);
      } else if (this.dialogOptions.getButtonTextIds(-2) != 0) {
        paramBundle.setNegativeButton(this.dialogOptions.getButtonTextIds(-2), this);
      }
    }
    if (this.dialogOptions.getButtonType(-3) != 0) {
      if (!TextUtils.isEmpty(this.dialogOptions.getButtonTexts(-3))) {
        paramBundle.setNeutralButton(this.dialogOptions.getButtonTexts(-3), this);
      } else if (this.dialogOptions.getButtonTextIds(-3) != 0) {
        paramBundle.setNeutralButton(this.dialogOptions.getButtonTextIds(-3), this);
      }
    }
    return paramBundle.create();
  }
  
  public static abstract interface OnRateButtonClickListener
  {
    public abstract boolean handleCancelRate();
    
    public abstract boolean handleRateButtonClick(int paramInt1, int paramInt2);
  }
}
