package com.didi.sdk.pay.sign.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.didi.sdk.login.view.DialogHelper;
import com.didi.sdk.pay.base.PayCommonParamsUtil;
import com.didi.sdk.pay.sign.CreditCardDetailActivity;
import com.didi.sdk.pay.sign.PaypalDetailActivity;
import com.didi.sdk.pay.sign.QQDetailActivity;
import com.didi.sdk.pay.sign.SignAlipayActivity;
import com.didi.sdk.pay.sign.SignBankActivity;
import com.didi.sdk.pay.sign.SignWechatActivity;
import com.didi.sdk.pay.sign.model.SignInfo;
import com.didi.sdk.payment.R.color;
import com.didi.sdk.payment.R.drawable;
import com.didi.sdk.payment.R.string;
import com.didi.sdk.util.TextUtil;
import com.didi.sdk.view.dialog.AlertController.IconType;
import com.didi.sdk.view.dialog.AlertDialogFragment;
import com.didi.sdk.view.dialog.AlertDialogFragment.Builder;
import com.didi.sdk.view.dialog.AlertDialogFragment.OnClickListener;
import java.util.List;

@Deprecated
public class SignUtil
{
  public static final String ACTIVITY_MSG = "activity_msg";
  
  public SignUtil() {}
  
  public static boolean isQQClientAvailable(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (((PackageInfo)paramContext.get(i)).packageName.equals("com.tencent.mobileqq")) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static void promptLoginDialog(FragmentActivity paramFragmentActivity, String paramString)
  {
    if (TextUtil.isEmpty(paramString)) {
      return;
    }
    new AlertDialogFragment.Builder(paramFragmentActivity).setNeutralButtonDefault().setIcon(AlertController.IconType.INFO).setMessage(paramString).setNeutralButton(R.string.one_payment_confirm, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        this.val$activity.finish();
        PayCommonParamsUtil.getInstance().startLogin(this.val$activity);
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  @Deprecated
  public static void showAlipayActivity(Activity paramActivity)
  {
    showAlipayActivity(paramActivity, "");
  }
  
  @Deprecated
  public static void showAlipayActivity(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, SignAlipayActivity.class);
    localIntent.putExtra("channel", 134);
    localIntent.putExtra("activity_msg", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  @Deprecated
  public static void showBankCardActivity(Activity paramActivity)
  {
    showBankCardActivity(paramActivity, "");
  }
  
  @Deprecated
  public static void showBankCardActivity(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, SignBankActivity.class);
    localIntent.putExtra("channel", 136);
    localIntent.putExtra("activity_msg", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  public static void showCancelSignDialog(FragmentActivity paramFragmentActivity, String paramString, final View.OnClickListener paramOnClickListener)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setMessage(paramString).setIcon(AlertController.IconType.INFO).setPositiveButtonDefault().setDefaultButtonTxtColor(R.color.one_payment_orange).setNegativeButton(R.string.one_payment_cancel, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
      }
    }).setPositiveButton(R.string.one_payment_confirm, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        DialogHelper.loadingDialog(this.val$activity, this.val$activity.getString(R.string.one_payment_driver_info_loading_txt), false, null);
        if (paramOnClickListener != null) {
          paramOnClickListener.onClick(null);
        }
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  public static void showCancelSignFailDialog(FragmentActivity paramFragmentActivity, String paramString1, String paramString2, final View.OnClickListener paramOnClickListener)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setTitle(paramString1).setMessage(paramString2).setIcon(AlertController.IconType.INFO).setPositiveButtonDefault().setDefaultButtonTxtColor(R.color.one_payment_orange).setNegativeButton(R.string.one_payment_pay_close_txt, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
      }
    }).setPositiveButton(R.string.one_payment_wxagent_binded_fail_retry, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        DialogHelper.loadingDialog(this.val$activity, this.val$activity.getString(R.string.one_payment_driver_info_loading_txt), false, null);
        if (paramOnClickListener != null) {
          paramOnClickListener.onClick(null);
        }
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  @Deprecated
  public static void showCreditCardDetailActivity(Activity paramActivity, String paramString, SignInfo paramSignInfo)
  {
    Intent localIntent = new Intent(paramActivity, CreditCardDetailActivity.class);
    localIntent.putExtra("channel", 150);
    localIntent.putExtra("SIGN_INFO", paramSignInfo);
    localIntent.putExtra("activity_msg", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  public static void showOneButtonCompletedDialog(final FragmentActivity paramFragmentActivity, String paramString, boolean paramBoolean)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setNeutralButtonDefault().setIcon(R.drawable.one_payment_pay_dialog_icon_correct).setMessage(paramString).setNeutralButton(R.string.guide_i_know, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        if (this.val$isClose) {
          paramFragmentActivity.finish();
        }
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  public static void showOneButtonDialog(FragmentActivity paramFragmentActivity, String paramString)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setIcon(AlertController.IconType.INFO).setNeutralButtonDefault().setMessage(paramString).setNeutralButton(R.string.confirm, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  public static void showOneButtonDialog(final FragmentActivity paramFragmentActivity, String paramString, boolean paramBoolean)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setNeutralButtonDefault().setIcon(AlertController.IconType.INFO).setMessage(paramString).setNeutralButton(R.string.guide_i_know, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        if (this.val$isClose) {
          paramFragmentActivity.finish();
        }
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  public static void showOneButtonDialogBrazil(final FragmentActivity paramFragmentActivity, AlertController.IconType paramIconType, String paramString1, String paramString2, boolean paramBoolean)
  {
    paramString2 = new AlertDialogFragment.Builder(paramFragmentActivity).setDefaultButtonTxtColor(R.color.one_payment_guarana_blue).setNeutralButtonDefault().setMessage(paramString2).setNeutralButton(R.string.one_payment_known_it, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        if (this.val$isClose) {
          paramFragmentActivity.finish();
        }
      }
    });
    if (paramIconType == AlertController.IconType.RIGHT) {
      paramString2.setIcon(R.drawable.one_payment_pay_dialog_icon_correct);
    } else {
      paramString2.setIcon(AlertController.IconType.INFO);
    }
    if (!TextUtil.isEmpty(paramString1)) {
      paramString2.setTitle(paramString1);
    }
    paramString2.create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  public static void showOneButtonDialogByCallBack(FragmentActivity paramFragmentActivity, String paramString, View.OnClickListener paramOnClickListener)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setNeutralButtonDefault().setIcon(R.drawable.one_payment_pay_dialog_icon_correct).setMessage(paramString).setNeutralButton(R.string.guide_i_know, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        if (this.val$listener != null) {
          this.val$listener.onClick(null);
        }
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  @Deprecated
  public static void showPaypalDetailActivity(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, PaypalDetailActivity.class);
    localIntent.putExtra("channel", 152);
    localIntent.putExtra("activity_msg", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  @Deprecated
  public static void showQQDetailActivity(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, QQDetailActivity.class);
    localIntent.putExtra("channel", 144);
    localIntent.putExtra("activity_msg", paramString);
    paramActivity.startActivity(localIntent);
  }
  
  public static void showSetDefaultPaySuccessDialog(FragmentActivity paramFragmentActivity, String paramString, View.OnClickListener paramOnClickListener)
  {
    new AlertDialogFragment.Builder(paramFragmentActivity).setTitle(paramFragmentActivity.getString(R.string.one_payment_str_save_success)).setMessage(paramString).setIcon(R.drawable.one_payment_pay_dialog_icon_correct).setNeutralButtonDefault().setMessage(paramString).setNeutralButton(R.string.one_payment_me_known, new AlertDialogFragment.OnClickListener()
    {
      public void onClick(AlertDialogFragment paramAnonymousAlertDialogFragment, View paramAnonymousView)
      {
        paramAnonymousAlertDialogFragment.dismiss();
        if (this.val$listener != null) {
          this.val$listener.onClick(null);
        }
      }
    }).create().show(paramFragmentActivity.getSupportFragmentManager(), "tag");
  }
  
  public static void showWXLowVersionDialog(FragmentActivity paramFragmentActivity)
  {
    showOneButtonDialog(paramFragmentActivity, paramFragmentActivity.getResources().getString(R.string.one_payment_pay_wexin_low_version_txt));
  }
  
  public static void showWXUnstalledDialog(FragmentActivity paramFragmentActivity)
  {
    showOneButtonDialog(paramFragmentActivity, paramFragmentActivity.getResources().getString(R.string.one_payment_wexin_uninstall_tip));
  }
  
  @Deprecated
  public static void showWeChatActivity(Activity paramActivity)
  {
    showWeChatActivity(paramActivity, "");
  }
  
  @Deprecated
  public static void showWeChatActivity(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, SignWechatActivity.class);
    localIntent.putExtra("channel", 133);
    localIntent.putExtra("activity_msg", paramString);
    paramActivity.startActivity(localIntent);
  }
}
