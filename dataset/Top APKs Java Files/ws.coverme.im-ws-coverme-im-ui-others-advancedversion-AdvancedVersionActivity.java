package ws.coverme.im.ui.others.advancedversion;

import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.app.PayTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ws.coverme.im.JucoreAdp.CbImplement.MyClientInstCallback;
import ws.coverme.im.JucoreAdp.ClientInst.IClientInstance;
import ws.coverme.im.JucoreAdp.Jucore;
import ws.coverme.im.JucoreAdp.VirtualNumber.IVirtualNumberInstance;
import ws.coverme.im.dll.PurchaseTableOperation;
import ws.coverme.im.ga.CMGAPremiumFeaturesFlow;
import ws.coverme.im.model.ActivityStack;
import ws.coverme.im.model.KexinData;
import ws.coverme.im.model.friends.FriendList;
import ws.coverme.im.model.gift.GiftConstants;
import ws.coverme.im.model.others.AdvancedVersionPurchaseBean;
import ws.coverme.im.model.purchase.PayMainActivity;
import ws.coverme.im.model.purchase.alixpay.Base64;
import ws.coverme.im.model.purchase.alixpay.Result;
import ws.coverme.im.model.purchase.utils.ParameterUtil;
import ws.coverme.im.model.purchase.utils.ProductID;
import ws.coverme.im.model.purchase.utils.RSACoder;
import ws.coverme.im.model.user.Profile;
import ws.coverme.im.privatenumber.bean.VirtualProductBean;
import ws.coverme.im.ui.KexinApp;
import ws.coverme.im.ui.gift.ChooseFriendToUseActivity;
import ws.coverme.im.ui.interfaces.IBuyByAliPay;
import ws.coverme.im.ui.login_registe.SignInActivity;
import ws.coverme.im.ui.others.advancedversion.util.PremiumUtil;
import ws.coverme.im.ui.privatenumber.BasePrivateActivity;
import ws.coverme.im.ui.purchase.BrainTreePurchaseActivity;
import ws.coverme.im.ui.view.MyDialog;
import ws.coverme.im.util.AppInstalledUtil;
import ws.coverme.im.util.CMTracer;
import ws.coverme.im.util.ClickTimeSpanUtil;
import ws.coverme.im.util.OtherHelper;
import ws.coverme.im.util.StrUtil;
import ws.coverme.im.util.ToastUtil;
import ws.coverme.im.util.Utils;

public class AdvancedVersionActivity
  extends BasePrivateActivity
  implements View.OnClickListener, IBuyByAliPay
{
  private static final int REQUEST_CODE_ADVANCEVER_PAY_RESULT = 101;
  public static String TAG = "AdvancedVersionActivity";
  private static boolean mbDestroyed = false;
  private Button backButton;
  private RelativeLayout buyBottomLayout;
  private RelativeLayout cmnRelativeLayout;
  private RelativeLayout decoyPasswordRelativeLayout;
  private RelativeLayout discreetLockScreenRelativeLayout;
  private boolean hasLogout = false;
  private boolean isCreate = true;
  private Button mBuyBtn;
  private Button mGiftBtn;
  Handler mHandler = new Handler()
  {
    public void handleMessage(final Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what != 21000) {
        switch (paramAnonymousMessage.what)
        {
        }
      }
      label449:
      do
      {
        do
        {
          return;
          KexinData.getInstance().unLockInActivity = false;
          AdvancedVersionActivity.this.setLockScreen();
          KexinData.getInstance().doNotKillApp = false;
          paramAnonymousMessage = new Result((String)paramAnonymousMessage.obj);
          paramAnonymousMessage.parseResult();
          String str;
          try
          {
            long l = Long.parseLong(paramAnonymousMessage.numberStatus);
            if ((l == 9000L) && (paramAnonymousMessage.isSignOk))
            {
              str = RSACoder.encryptBASE64(paramAnonymousMessage.getAllResult().getBytes());
              Jucore.getInstance().getClientInstance().NotifyAlixpayPurchaseResult(new ProductID().toInt(AdvancedVersionActivity.this.m_ProductId), 0, str, l, AdvancedVersionActivity.this.tradeNo);
              PurchaseTableOperation.insertAlipayPurchase(AdvancedVersionActivity.this.tradeNo, 96125487, str, AdvancedVersionActivity.this.m_ProductId, "", AdvancedVersionActivity.this);
              CMGAPremiumFeaturesFlow.sendEventAndFlowEntry("Advanced Version", "purchase_advanced_version_success_payway_alipay", "", 0L);
            }
            paramAnonymousMessage = paramAnonymousMessage.resultStatus;
            CMTracer.i("Alipay - get resultStatus", "resultStatus = " + paramAnonymousMessage + ", tradeNo:" + AdvancedVersionActivity.this.tradeNo);
            return;
          }
          catch (Exception paramAnonymousMessage)
          {
            CMTracer.e("Alipay - get numberStatus", "Exception! " + paramAnonymousMessage.getMessage());
            return;
          }
          switch (paramAnonymousMessage.arg1)
          {
          default: 
            return;
          case 101: 
            paramAnonymousMessage = paramAnonymousMessage.getData();
            str = paramAnonymousMessage.getString("dataString");
            AdvancedVersionActivity.access$702(AdvancedVersionActivity.this, paramAnonymousMessage.getString("productId"));
            paramAnonymousMessage = new String(Base64.decode(str));
            AdvancedVersionActivity.access$802(AdvancedVersionActivity.this, ParameterUtil.getParameter(paramAnonymousMessage, "out_trade_no"));
            KexinData.getInstance().unLockInActivity = true;
            KexinData.getInstance().doNotKillApp = true;
            new Thread()
            {
              public void run()
              {
                String str = new PayTask(AdvancedVersionActivity.this).pay(paramAnonymousMessage);
                CMTracer.i("UseAliPay", "result = " + str);
                Message localMessage = AdvancedVersionActivity.this.mHandler.obtainMessage();
                localMessage.what = 1;
                localMessage.obj = str;
                AdvancedVersionActivity.this.mHandler.sendMessage(localMessage);
              }
            }.start();
            return;
          case 102: 
            AdvancedVersionActivity.access$702(AdvancedVersionActivity.this, (String)paramAnonymousMessage.obj);
            if (!StrUtil.isNull(AdvancedVersionActivity.this.m_ProductId))
            {
              if (!AdvancedVersionActivity.this.m_ProductId.equals("CM_AND_IAP_PREMIUM_FEATURES")) {
                break label449;
              }
              PremiumUtil.setPremiumFeaturesPurchaseState(true, AdvancedVersionActivity.this, "premiunFeatursPurchase");
            }
            while (!AdvancedVersionActivity.mbDestroyed)
            {
              AdvancedVersionActivity.this.initData();
              return;
              if (AdvancedVersionActivity.this.m_ProductId.equals("CM_AND_IAP_NEW_PREMIUM_FEATURES")) {
                PremiumUtil.setPremiumFeaturesPurchaseState(true, AdvancedVersionActivity.this, "NewPremiunFeatursPurchase");
              } else if (AdvancedVersionActivity.this.m_ProductId.equals("CM_AND_IAP_NEW_PREMIUM_FEATURES_EXT")) {
                PremiumUtil.setPremiumFeaturesPurchaseState(true, AdvancedVersionActivity.this, "NewPremiunExtFeatursPurchase");
              } else if (AdvancedVersionActivity.this.m_ProductId.equals("CM_AND_IAP_PREMIUM_FEATURES_PART2")) {
                PremiumUtil.setPremiumFeaturesPurchaseState(true, AdvancedVersionActivity.this, "PremiumPart2FeatursPurchase");
              }
            }
          }
        } while (AdvancedVersionActivity.mbDestroyed);
        AdvancedVersionActivity.this.initData();
        return;
      } while (AdvancedVersionActivity.mbDestroyed);
      AdvancedVersionActivity.this.initData();
    }
  };
  private TextView mTipTextView;
  private String m_ProductId = "";
  private RelativeLayout magicNotificationRelativeLayout;
  MyClientInstCallback mcb;
  private boolean needCloseTextView;
  private DialogInterface.OnClickListener payMethodItemClick = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      default: 
        return;
      case 0: 
        AdvancedVersionActivity.this.UseCreditCard(AdvancedVersionActivity.this.price, AdvancedVersionActivity.this.purchaseProductId);
        return;
      }
      AdvancedVersionActivity.this.UsePayPal(AdvancedVersionActivity.this.price, AdvancedVersionActivity.this.purchaseProductId);
    }
  };
  private String price = "";
  private RelativeLayout privateCallRelativeLayout;
  private String purchaseProductId = "";
  BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("ws.coverme.im.model.constant.QUERY_MYSELF_PREMIUM")) {
        if ((paramAnonymousIntent.getIntExtra("command_tag", 0) == 3) && (paramAnonymousIntent.getBooleanExtra("result", false))) {
          AdvancedVersionActivity.this.initData();
        }
      }
      while ((!paramAnonymousIntent.getAction().equals("ws.coverme.im.model.constant.GET_VIRTUAL_PRODUCTLIST")) || (paramAnonymousIntent.getIntExtra("command_tag", 0) != 10)) {
        return;
      }
      int i = paramAnonymousIntent.getIntExtra("errCode", -1);
      boolean bool2;
      boolean bool3;
      if (i == 0)
      {
        CMTracer.i(AdvancedVersionActivity.TAG, "get virtual product list errorCode:" + i);
        paramAnonymousContext = paramAnonymousIntent.getParcelableArrayListExtra("product_list");
        if ((paramAnonymousContext != null) && (paramAnonymousContext.size() > 0))
        {
          boolean bool1 = PremiumUtil.isPremiumFeaturesPurchased();
          bool2 = PremiumUtil.isPremiumPart1Purchased();
          bool3 = PremiumUtil.isPremiumPurchasedByPrePurStateType("NewPremiumPart1FeatursPurchase");
          if (!bool1) {
            break label150;
          }
          break label377;
          break label271;
          break label160;
        }
      }
      for (;;)
      {
        AdvancedVersionActivity.this.dismiss();
        return;
        label150:
        if (bool2)
        {
          paramAnonymousContext = paramAnonymousContext.iterator();
          label160:
          if (!paramAnonymousContext.hasNext()) {
            continue;
          }
          paramAnonymousIntent = (VirtualProductBean)paramAnonymousContext.next();
          if (!paramAnonymousIntent.productId.equals("CM_AND_IAP_PREMIUM_FEATURES_PART2")) {
            break;
          }
          if (paramAnonymousIntent.price <= 0.0F) {
            continue;
          }
          AdvancedVersionActivity.access$102(AdvancedVersionActivity.this, Utils.getFormateFloatPrice(paramAnonymousIntent.price));
          AdvancedVersionActivity.access$202(AdvancedVersionActivity.this, "CM_AND_IAP_PREMIUM_FEATURES_PART2");
          AdvancedVersionActivity.this.mBuyBtn.setText(AdvancedVersionActivity.this.getString(2131168270, new Object[] { AdvancedVersionActivity.this.price }));
          continue;
        }
        if (bool3)
        {
          paramAnonymousContext = paramAnonymousContext.iterator();
          label271:
          if (!paramAnonymousContext.hasNext()) {
            continue;
          }
          paramAnonymousIntent = (VirtualProductBean)paramAnonymousContext.next();
          if (!paramAnonymousIntent.productId.equals("CM_AND_IAP_PREMIUM_FEATURES_PART2")) {
            break;
          }
          if (paramAnonymousIntent.price <= 0.0F) {
            continue;
          }
          AdvancedVersionActivity.access$102(AdvancedVersionActivity.this, Utils.getFormateFloatPrice(paramAnonymousIntent.price));
          AdvancedVersionActivity.access$202(AdvancedVersionActivity.this, "CM_AND_IAP_PREMIUM_FEATURES_PART2");
          AdvancedVersionActivity.this.mBuyBtn.setText(AdvancedVersionActivity.this.getString(2131168270, new Object[] { AdvancedVersionActivity.this.price }));
          continue;
        }
        paramAnonymousContext = paramAnonymousContext.iterator();
        label377:
        if (paramAnonymousContext.hasNext())
        {
          paramAnonymousIntent = (VirtualProductBean)paramAnonymousContext.next();
          if (!paramAnonymousIntent.productId.equals("CM_AND_IAP_NEW_PREMIUM_FEATURES_EXT")) {
            break;
          }
          if (paramAnonymousIntent.price > 0.0F)
          {
            AdvancedVersionActivity.access$102(AdvancedVersionActivity.this, Utils.getFormateFloatPrice(paramAnonymousIntent.price));
            AdvancedVersionActivity.access$202(AdvancedVersionActivity.this, "CM_AND_IAP_NEW_PREMIUM_FEATURES_EXT");
            AdvancedVersionActivity.this.mBuyBtn.setText(AdvancedVersionActivity.this.getString(2131168270, new Object[] { AdvancedVersionActivity.this.price }));
            continue;
            ToastUtil.showToast(AdvancedVersionActivity.this, 2131165327);
          }
        }
      }
    }
  };
  private RelativeLayout storageRelativeLayout;
  private TextView topCloseTextview;
  private String tradeNo = "";
  private TextView tv_title;
  
  public AdvancedVersionActivity() {}
  
  private void UseAliPay(String paramString)
  {
    if (StrUtil.isNull(paramString)) {
      return;
    }
    Jucore.getInstance().getClientInstance().GetNewProductReceipt(0L, 0, paramString, OtherHelper.getRandomString(10), 1L, "CN", 0, "");
    CMGAPremiumFeaturesFlow.sendEventAndFlowEntry("Advanced Version", "click_buy_btn_alipay", paramString, 0L);
  }
  
  private void UseCreditCard(String paramString1, String paramString2)
  {
    if ((StrUtil.isNull(paramString1)) || (StrUtil.isNull(paramString2))) {
      return;
    }
    Intent localIntent = new Intent(this, BrainTreePurchaseActivity.class);
    localIntent.putExtra("planId", paramString2);
    if (Jucore.getInstance().getClientInstance().GetBuildType() == 1) {
      localIntent.putExtra("price", "0.01");
    }
    for (;;)
    {
      localIntent.putExtra("packageName", "Premium features");
      localIntent.putExtra("receiver_user_id", "");
      localIntent.putExtra("orderAction", "");
      localIntent.putExtra("tag", 16);
      localIntent.putExtra("from", TAG);
      startActivityForResult(localIntent, 101);
      CMGAPremiumFeaturesFlow.sendEventAndFlowEntry("Advanced Version", "click_buy_btn_creditcard", paramString2, 0L);
      return;
      localIntent.putExtra("price", paramString1);
    }
  }
  
  private void UsePayPal(String paramString1, String paramString2)
  {
    if ((StrUtil.isNull(paramString1)) || (StrUtil.isNull(paramString2))) {
      return;
    }
    Intent localIntent = new Intent(this, PayMainActivity.class);
    localIntent.putExtra("planId", paramString2);
    if (Jucore.getInstance().getClientInstance().GetBuildType() == 1) {
      localIntent.putExtra("price", "0.01");
    }
    for (;;)
    {
      localIntent.putExtra("receiver_user_id", "");
      localIntent.putExtra("tag", 16);
      localIntent.putExtra("orderAction", "");
      startActivity(localIntent);
      CMGAPremiumFeaturesFlow.sendEventAndFlowEntry("Advanced Version", "click_buy_btn_paypal", paramString2, 0L);
      return;
      localIntent.putExtra("price", paramString1);
    }
  }
  
  private void checkAliPay()
  {
    try
    {
      this.mHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          if (AdvancedVersionActivity.this.isCreate) {}
          int i;
          do
          {
            AdvancedVersionActivity.access$1002(AdvancedVersionActivity.this, false);
            do
            {
              return;
            } while ((!OtherHelper.isSimpleChineseLanguage(AdvancedVersionActivity.this)) || (PremiumUtil.isPremiumFeaturesPurchased()));
            i = 0;
            Iterator localIterator = AdvancedVersionActivity.this.getPackageManager().getInstalledPackages(0).iterator();
            while (localIterator.hasNext()) {
              if (((PackageInfo)localIterator.next()).applicationInfo.packageName.equals("com.alipay.android.app")) {
                i = 1;
              }
            }
          } while (i != 0);
          AdvancedVersionActivity.this.checkMySelfPremiumPurchase();
        }
      }, 200L);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  private void checkPremium(boolean paramBoolean) {}
  
  private void getAdvanceDetails()
  {
    if (!this.isCreate) {
      return;
    }
    show();
    if (KexinData.getInstance().isOnline) {
      if (!OtherHelper.isSimpleChineseLanguage(this)) {
        break label60;
      }
    }
    label60:
    for (String str = "CN";; str = "US")
    {
      Jucore.getInstance().getVirtualNumberInst().GetVirtualProductList(0L, 10, 2, 100, str, "com.coverme.covermeAdhoc", "");
      offLineDismiss();
      return;
    }
  }
  
  private void gotoGiftActivity()
  {
    startActivity(new Intent(this, ChooseFriendToUseActivity.class));
  }
  
  private void gotoPremiumFeaturesItemView(String paramString)
  {
    Intent localIntent = new Intent(this, PremiumFeaturesChlidActivity.class);
    localIntent.putExtra("Premium_feature_guide_type", "ClickItem");
    localIntent.putExtra("Premium_feature_guide_child_type", paramString);
    startActivity(localIntent);
  }
  
  private void initData()
  {
    this.needCloseTextView = getIntent().getBooleanExtra("needCloseTextView", false);
    boolean bool1;
    if (this.needCloseTextView)
    {
      this.topCloseTextview.setVisibility(0);
      this.backButton.setVisibility(8);
      bool1 = updateUI();
      checkPremium(bool1);
      if (!bool1) {
        break label74;
      }
    }
    label74:
    int j;
    int i;
    label355:
    label487:
    do
    {
      return;
      this.topCloseTextview.setVisibility(8);
      this.backButton.setVisibility(0);
      break;
      bool1 = PremiumUtil.isPremiumPurchasedByPrePurStateType("premiunFeatursPurchase");
      boolean bool2 = PremiumUtil.isPremiumPurchasedByPrePurStateType("NewPremiunFeatursPurchase");
      boolean bool3 = PremiumUtil.isPremiumPurchasedByPrePurStateType("NewPremiunExtFeatursPurchase");
      boolean bool4 = PremiumUtil.isPremiumPurchasedByPrePurStateType("PremiumPart1FeatursPurchase");
      boolean bool5 = PremiumUtil.isPremiumPurchasedByPrePurStateType("NewPremiumPart1FeatursPurchase");
      boolean bool6 = PremiumUtil.isPremiumPurchasedByPrePurStateType("PremiumPart2FeatursPurchase");
      j = -1;
      i = 0;
      Object localObject;
      if ((bool1) || (bool2) || (bool6) || (bool3))
      {
        new AdvancedVersionPurchaseBean();
        localObject = PremiumUtil.getPremiumPurchaseBean("CM_AND_IAP_PREMIUM_FEATURES");
        if ((((AdvancedVersionPurchaseBean)localObject).check_state != 7) && (((AdvancedVersionPurchaseBean)localObject).check_state != 96125487)) {
          break label355;
        }
        j = ((AdvancedVersionPurchaseBean)localObject).check_state;
        i = ((AdvancedVersionPurchaseBean)localObject).payMethod;
      }
      for (;;)
      {
        if (j != 4) {
          break label487;
        }
        localObject = new MyDialog(this);
        ((MyDialog)localObject).setTitle(2131166715);
        ((MyDialog)localObject).setMessage(2131166716);
        ((MyDialog)localObject).setSinglePositiveButton(2131165364, null);
        ((MyDialog)localObject).show();
        this.mBuyBtn.setVisibility(0);
        return;
        if ((bool4) || (bool5))
        {
          this.price = getResources().getString(2131166697);
          this.purchaseProductId = "CM_AND_IAP_PREMIUM_FEATURES_PART2";
          this.mBuyBtn.setText(getString(2131168270, new Object[] { this.price }));
          break;
        }
        this.price = getResources().getString(2131166698);
        this.purchaseProductId = "CM_AND_IAP_NEW_PREMIUM_FEATURES_EXT";
        this.mBuyBtn.setText(getString(2131168270, new Object[] { this.price }));
        break;
        localObject = PremiumUtil.getPremiumPurchaseBean("CM_AND_IAP_NEW_PREMIUM_FEATURES_EXT");
        if ((((AdvancedVersionPurchaseBean)localObject).check_state == 7) || (((AdvancedVersionPurchaseBean)localObject).check_state == 96125487))
        {
          j = ((AdvancedVersionPurchaseBean)localObject).check_state;
          i = ((AdvancedVersionPurchaseBean)localObject).payMethod;
        }
        else
        {
          localObject = PremiumUtil.getPremiumPurchaseBean("CM_AND_IAP_NEW_PREMIUM_FEATURES");
          if ((((AdvancedVersionPurchaseBean)localObject).check_state == 7) || (((AdvancedVersionPurchaseBean)localObject).check_state == 96125487))
          {
            j = ((AdvancedVersionPurchaseBean)localObject).check_state;
            i = ((AdvancedVersionPurchaseBean)localObject).payMethod;
          }
          else
          {
            localObject = PremiumUtil.getPremiumPurchaseBean("CM_AND_IAP_PREMIUM_FEATURES_PART2");
            if ((((AdvancedVersionPurchaseBean)localObject).check_state == 7) || (((AdvancedVersionPurchaseBean)localObject).check_state == 96125487))
            {
              j = ((AdvancedVersionPurchaseBean)localObject).check_state;
              i = ((AdvancedVersionPurchaseBean)localObject).payMethod;
            }
          }
        }
      }
    } while ((j != 7) && (j != 96125487));
    if (this.isCreate) {
      Utils.showPendingTransactionDialog(this, i);
    }
    this.mBuyBtn.setVisibility(8);
    this.mTipTextView.setVisibility(0);
    this.mTipTextView.setText(2131166717);
    this.mTipTextView.setTextColor(-65536);
  }
  
  private void initListener()
  {
    IntentFilter localIntentFilter = new IntentFilter("ws.coverme.im.model.constant.QUERY_MYSELF_PREMIUM");
    localIntentFilter.addAction("ws.coverme.im.model.constant.GET_VIRTUAL_PRODUCTLIST");
    registerReceiver(this.receiver, localIntentFilter);
  }
  
  private void initView()
  {
    this.mTipTextView = ((TextView)findViewById(2131230912));
    this.topCloseTextview = ((TextView)findViewById(2131230903));
    this.backButton = ((Button)findViewById(2131230901));
    this.topCloseTextview.setOnClickListener(this);
    this.backButton.setOnClickListener(this);
    this.cmnRelativeLayout = ((RelativeLayout)findViewById(2131230913));
    this.cmnRelativeLayout.setOnClickListener(this);
    this.discreetLockScreenRelativeLayout = ((RelativeLayout)findViewById(2131230917));
    this.discreetLockScreenRelativeLayout.setOnClickListener(this);
    this.magicNotificationRelativeLayout = ((RelativeLayout)findViewById(2131230921));
    this.magicNotificationRelativeLayout.setOnClickListener(this);
    this.decoyPasswordRelativeLayout = ((RelativeLayout)findViewById(2131230925));
    this.decoyPasswordRelativeLayout.setOnClickListener(this);
    this.privateCallRelativeLayout = ((RelativeLayout)findViewById(2131230929));
    this.privateCallRelativeLayout.setOnClickListener(this);
    this.storageRelativeLayout = ((RelativeLayout)findViewById(2131230933));
    this.storageRelativeLayout.setOnClickListener(this);
    if (AppInstalledUtil.isCnKexinApp(this)) {
      this.privateCallRelativeLayout.setVisibility(8);
    }
    for (;;)
    {
      this.mBuyBtn = ((Button)findViewById(2131230910));
      this.mGiftBtn = ((Button)findViewById(2131230911));
      this.mGiftBtn.setOnClickListener(this);
      this.mBuyBtn.setOnClickListener(this);
      this.tv_title = ((TextView)findViewById(2131230902));
      this.buyBottomLayout = ((RelativeLayout)findViewById(2131230909));
      return;
      if (AppInstalledUtil.isVaultApp(this)) {
        this.privateCallRelativeLayout.setVisibility(8);
      }
    }
  }
  
  private boolean updateUI()
  {
    boolean bool = PremiumUtil.isPremiumFeaturesPurchased();
    if (bool)
    {
      CMTracer.i(TAG, "has premium");
      this.mBuyBtn.setVisibility(8);
      if (KexinData.getInstance().getFriendsList().size() > 0)
      {
        this.mGiftBtn.setVisibility(0);
        CMTracer.i(TAG, "show gift friend btn");
      }
    }
    for (;;)
    {
      if (AppInstalledUtil.isCoverMeApp(this)) {
        this.cmnRelativeLayout.setVisibility(0);
      }
      return bool;
      this.buyBottomLayout.setVisibility(8);
      continue;
      getAdvanceDetails();
    }
  }
  
  public void buyCancel()
  {
    dismiss();
  }
  
  public void buyFailure()
  {
    dismiss();
  }
  
  public void buyProgress()
  {
    show();
    offLineDismiss();
  }
  
  public void buySuccess() {}
  
  public void checkMySelfPremiumPurchase()
  {
    Jucore.getInstance().getClientInstance().QueryFriendProductPurchased(0L, 3, 6754958L, KexinData.getInstance().getMyProfile(true).userId, GiftConstants.productIds);
  }
  
  public void finish()
  {
    if (this.hasLogout)
    {
      ActivityStack.getInstance().popAll();
      startActivity(new Intent(this, SignInActivity.class));
    }
    CMGAPremiumFeaturesFlow.setEmptyCharsEntry();
    super.finish();
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    }
    do
    {
      return;
      if (paramInt2 == -1) {
        initData();
      }
    } while ((65283 != paramInt1) || (paramIntent == null));
    this.hasLogout = paramIntent.getBooleanExtra("hasLogout", false);
  }
  
  public void onClick(View paramView)
  {
    if (ClickTimeSpanUtil.isFastDoubleClick()) {}
    do
    {
      return;
      switch (paramView.getId())
      {
      default: 
        return;
      case 2131230901: 
      case 2131230903: 
        finish();
        return;
      }
    } while (ClickTimeSpanUtil.isFastDoubleClick(4000L, 2131230910));
    if (KexinData.getInstance().isOnline)
    {
      CMTracer.i("AdvancedVersionActivity", "pay btn clicked!");
      if ((OtherHelper.isSimpleChineseLanguage(this)) || (AppInstalledUtil.isCnKexinApp(this)))
      {
        UseAliPay(this.purchaseProductId);
        return;
      }
      paramView = getResources().getStringArray(2131623993);
      new AlertDialog.Builder(this).setTitle(2131166881).setItems(paramView, this.payMethodItemClick).show();
      return;
    }
    CMTracer.i("AdvancedVersionActivity", "kexin is not online");
    paramView = new MyDialog(this);
    paramView.setTitle(2131165905);
    paramView.setMessage(2131165908);
    paramView.setSinglePositiveButton(2131165364, null);
    paramView.show();
    return;
    gotoGiftActivity();
    return;
    paramView = new Intent(this, PremiumFeaturesChlidActivity.class);
    paramView.putExtra("Premium_feature_guide_type", "ClickItem");
    paramView.putExtra("Premium_feature_guide_child_type", PremiumUtil.Premium_Feature_Click_Item_Array[0]);
    startActivityForResult(paramView, 65283);
    return;
    gotoPremiumFeaturesItemView(PremiumUtil.Premium_Feature_Click_Item_Array[1]);
    return;
    gotoPremiumFeaturesItemView(PremiumUtil.Premium_Feature_Click_Item_Array[2]);
    return;
    gotoPremiumFeaturesItemView(PremiumUtil.Premium_Feature_Click_Item_Array[3]);
    return;
    gotoPremiumFeaturesItemView(PremiumUtil.Premium_Feature_Click_Item_Array[4]);
    return;
    gotoPremiumFeaturesItemView(PremiumUtil.Premium_Feature_Click_Item_Array[5]);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    mbDestroyed = false;
    setContentView(2130903057);
    initView();
    initListener();
    initData();
    ((KexinApp)getApplication()).setHandler(this.mHandler);
    this.mcb = new MyClientInstCallback(this);
  }
  
  public void onDestroy()
  {
    mbDestroyed = true;
    KexinData.getInstance().unLockInActivity = false;
    KexinData.getInstance().doNotKillApp = false;
    super.onDestroy();
    unregisterReceiver(this.receiver);
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.mcb.registHandler(this.mHandler);
    checkAliPay();
  }
  
  protected void onStart()
  {
    super.onStart();
    CMGAPremiumFeaturesFlow.sendEventAndFlowEntry("Advanced Version", "AdvancedVersionActivity", null, 0L);
  }
  
  protected void onStop()
  {
    super.onStop();
    ClickTimeSpanUtil.resetlastClickBtnId();
  }
}
