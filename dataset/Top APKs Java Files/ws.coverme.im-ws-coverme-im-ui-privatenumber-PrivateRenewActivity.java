package ws.coverme.im.ui.privatenumber;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ws.coverme.im.JucoreAdp.CbImplement.MyClientInstCallback;
import ws.coverme.im.JucoreAdp.ClientInst.IClientInstance;
import ws.coverme.im.JucoreAdp.Jucore;
import ws.coverme.im.JucoreAdp.Types.DataStructs.Callplan;
import ws.coverme.im.JucoreAdp.VirtualNumber.IVirtualNumberInstance;
import ws.coverme.im.dll.PrivateNumberTableOperation;
import ws.coverme.im.dll.SharedPreferencesManager;
import ws.coverme.im.ga.CMGAFlow;
import ws.coverme.im.model.KexinData;
import ws.coverme.im.privatenumber.bean.CodeBean;
import ws.coverme.im.privatenumber.bean.PhoneBean;
import ws.coverme.im.privatenumber.bean.VirtualProductBean;
import ws.coverme.im.ui.interfaces.IBuyByAliPay;
import ws.coverme.im.ui.purchase.BrainTreePurchaseActivity;
import ws.coverme.im.ui.view.MyDialog;
import ws.coverme.im.util.CMProgressDialog;
import ws.coverme.im.util.CMTracer;
import ws.coverme.im.util.ClickTimeSpanUtil;
import ws.coverme.im.util.DateUtil;
import ws.coverme.im.util.OtherHelper;
import ws.coverme.im.util.PhoneNumberUtil;
import ws.coverme.im.util.StrUtil;
import ws.coverme.im.util.ToastUtil;
import ws.coverme.im.util.Utils;

public class PrivateRenewActivity
  extends BasePrivateActivity
  implements View.OnClickListener, IBuyByAliPay
{
  private static final String TAG = "PrivateRenewActivity";
  private final int REFRESH_BROAD = 2;
  private final int REFRESH_RETURN = 3;
  private final int REFRESH_THIS = 1;
  private int REQUEST_CODE_ALIPAY = 102;
  private int REQUEST_CODE_BRAINTREE = 101;
  private int REQUEST_CODE_PAYPAL = 103;
  private int REQUEST_CODE_SELECT_PACKET = 104;
  private int callPlanId;
  private Callplan callplan;
  private CodeBean codeBean;
  private String couponId;
  private int expiration = 0;
  private int fromDays = 0;
  private int fromMinutes = 0;
  private int fromTexts = 0;
  private boolean isFirst = true;
  private PrivateHandler mHandler;
  private boolean mIsMultiCountry = false;
  private String mPackageMinutes;
  private String mPackageName;
  private String mPackageTexts;
  private String mPackageValid;
  private String mPrice;
  private int maxMins;
  private int maxTotalMinutes = 0;
  private int maxTotalTexts = 0;
  private MyClientInstCallback mcb;
  private String money = "";
  private boolean needRecommemd = false;
  private boolean needTofinish = false;
  private DialogInterface.OnClickListener payMethodItemClick_En = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      default: 
        return;
      case 0: 
        PrivateRenewActivity.this.UserCreditCard();
        return;
      }
      PrivateRenewActivity.this.UsePayPal();
    }
  };
  private int payWay;
  private String paymentId;
  private PhoneBean phoneBean;
  private String planId;
  private String planName;
  private String productId;
  BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (!KexinData.getInstance().isOnline) {}
      String str;
      int j;
      label139:
      label205:
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              str = paramAnonymousIntent.getAction();
              if ("ws.coverme.im.model.constant.WAIT_ALIPAY".equals(str))
              {
                PrivateRenewActivity.this.dismiss();
                return;
              }
              if ("android.intent.action.LOCALE_CHANGED".equals(str))
              {
                PrivateRenewActivity.this.finish();
                return;
              }
              j = paramAnonymousIntent.getIntExtra("command_tag", 0);
            } while ((j != 5) && (j != 11));
            if ("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS".equals(str))
            {
              if (PrivateRenewActivity.this.needTofinish)
              {
                PrivateRenewActivity.this.dismiss();
                paramAnonymousContext = new Intent();
                PrivateRenewActivity.this.setResult(-1, paramAnonymousContext);
                PrivateRenewActivity.this.finish();
              }
              paramAnonymousContext = PrivateRenewActivity.this;
              boolean bool;
              if (j == 5)
              {
                bool = true;
                if (j != 5) {
                  break label205;
                }
              }
              for (i = 1;; i = 2)
              {
                paramAnonymousContext.refreshUI(bool, i, PrivateRenewActivity.this.mIsMultiCountry);
                if (j != 5) {
                  break;
                }
                PrivateRenewActivity.this.dismiss();
                paramAnonymousContext = new Intent();
                PrivateRenewActivity.this.setResult(-1, paramAnonymousContext);
                PrivateRenewActivity.this.finish();
                return;
                bool = false;
                break label139;
              }
            }
            if (!"ws.coverme.im.model.constant.GET_ORDER_RESULT".equals(str)) {
              break;
            }
          } while (j != 5);
          CMTracer.i("PrivateRenewActivity", "EXTRA_PHONE_NUMBER2 : " + PrivateRenewActivity.this.getIntent().getStringExtra("phone_number") + ", coupon : " + paramAnonymousIntent.getIntExtra("coupon", -1));
          PrivateRenewActivity.access$302(PrivateRenewActivity.this, paramAnonymousIntent.getStringExtra("coupon"));
          PrivateRenewActivity.access$402(PrivateRenewActivity.this, paramAnonymousIntent.getStringExtra("productId"));
          PrivateRenewActivity.access$502(PrivateRenewActivity.this, paramAnonymousIntent.getStringExtra("paymentId"));
          PrivateRenewActivity.access$602(PrivateRenewActivity.this, paramAnonymousIntent.getIntExtra("callPlanId", 0));
          PrivateRenewActivity.access$702(PrivateRenewActivity.this, paramAnonymousIntent.getLongExtra("orderNO", -1L));
          if (!StrUtil.isNull(PrivateRenewActivity.this.couponId))
          {
            PrivateRenewActivity.access$802(PrivateRenewActivity.this, 2);
            PrivateRenewActivity.this.insertPhone();
            PrivateRenewActivity.this.reselectPhoneNumber(String.valueOf(PrivateRenewActivity.this.couponId));
            return;
          }
          CMTracer.i("PrivateRenewActivity", "test_errorCode2 : " + paramAnonymousIntent.getBooleanExtra("result", false));
          PrivateRenewActivity.access$802(PrivateRenewActivity.this, paramAnonymousIntent.getIntExtra("orderStatus", -1));
          if (PrivateRenewActivity.this.status == 5)
          {
            PrivateRenewActivity.this.reapplay();
            PrivateRenewActivity.this.refresh(true, 1);
            PrivateRenewActivity.this.dismiss();
          }
          if (paramAnonymousIntent.getBooleanExtra("result", false))
          {
            PrivateRenewActivity.this.reapplay();
            if (PrivateRenewActivity.this.status == 1)
            {
              PrivateRenewActivity.this.insertTradeNo();
              paramAnonymousContext = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
              paramAnonymousContext.setPackage(PrivateRenewActivity.this.getPackageName());
              PrivateRenewActivity.this.sendBroadcast(paramAnonymousContext);
            }
            for (;;)
            {
              PrivateRenewActivity.this.getMyBalance();
              return;
              if (PrivateRenewActivity.this.status == 0) {
                PrivateRenewActivity.this.updatePhoneGetTime();
              }
            }
          }
          PrivateRenewActivity.this.dismiss();
          return;
          if (!"ws.coverme.im.model.constant.RETURN_FOR_PAYPAL".equals(str)) {
            break;
          }
        } while ((j != 5) || (!PrivateRenewActivity.this.getIntent().getStringExtra("phone_number").equals(paramAnonymousIntent.getStringExtra("phone_number"))));
        PrivateRenewActivity.this.refreshUI(true, 1, PrivateRenewActivity.this.mIsMultiCountry);
        PrivateRenewActivity.this.dismiss();
        return;
      } while (!"ws.coverme.im.model.constant.GET_VIRTUAL_PRODUCTLIST".equals(str));
      int i = paramAnonymousIntent.getIntExtra("errCode", -1);
      if (j == 11)
      {
        if (i != 0) {
          break label1088;
        }
        CMTracer.i("PrivateRenewActivity", "get virtual product list errorCode:" + i);
        paramAnonymousContext = paramAnonymousIntent.getParcelableArrayListExtra("product_list");
        if ((paramAnonymousContext != null) && (paramAnonymousContext.size() > 0))
        {
          paramAnonymousContext = paramAnonymousContext.iterator();
          while (paramAnonymousContext.hasNext())
          {
            paramAnonymousIntent = (VirtualProductBean)paramAnonymousContext.next();
            if (!PrivateRenewActivity.this.mIsMultiCountry) {
              break label983;
            }
            i = PrivateRenewActivity.this.findIndexbyCountryCode(PrivateRenewActivity.this.codeBean.countryCode, paramAnonymousIntent.countryCodeScope);
            if (i >= 0)
            {
              PrivateRenewActivity.access$1802(PrivateRenewActivity.this, Utils.getFormateFloatPrice(paramAnonymousIntent.price));
              PrivateRenewActivity.access$1902(PrivateRenewActivity.this, String.valueOf(paramAnonymousIntent.minutesArrays[i]));
              PrivateRenewActivity.access$2002(PrivateRenewActivity.this, String.valueOf(paramAnonymousIntent.textsArrays[i]));
              PrivateRenewActivity.access$2102(PrivateRenewActivity.this, String.valueOf(paramAnonymousIntent.expiration));
              if (PrivateRenewActivity.this.callplan.productId.equals(paramAnonymousIntent.productId))
              {
                if (paramAnonymousIntent.price > 0.0F)
                {
                  PrivateRenewActivity.access$2302(PrivateRenewActivity.this, String.valueOf(paramAnonymousIntent.price));
                  PrivateRenewActivity.access$2402(PrivateRenewActivity.this, PrivateRenewActivity.this.money);
                  ((TextView)PrivateRenewActivity.this.findViewById(2131235232)).setText(PrivateRenewActivity.this.getString(2131168265, new Object[] { PrivateRenewActivity.this.money }));
                }
                PrivateRenewActivity.this.refresh(true, 1, PrivateRenewActivity.this.mPrice, PrivateRenewActivity.this.mPackageMinutes, PrivateRenewActivity.this.mPackageTexts, PrivateRenewActivity.this.mPackageValid);
              }
            }
          }
        }
      }
      for (;;)
      {
        PrivateRenewActivity.this.dismiss();
        return;
        label983:
        if (!PrivateRenewActivity.this.callplan.productId.equals(paramAnonymousIntent.productId)) {
          break;
        }
        if (paramAnonymousIntent.price > 0.0F)
        {
          PrivateRenewActivity.access$2302(PrivateRenewActivity.this, String.valueOf(paramAnonymousIntent.price));
          PrivateRenewActivity.access$2402(PrivateRenewActivity.this, PrivateRenewActivity.this.money);
          ((TextView)PrivateRenewActivity.this.findViewById(2131235232)).setText(PrivateRenewActivity.this.getString(2131168265, new Object[] { PrivateRenewActivity.this.money }));
          continue;
          label1088:
          ToastUtil.showToast(paramAnonymousContext, 2131165327);
        }
      }
    }
  };
  private int status = 1;
  private int toDays = 0;
  private int toMinutes = 0;
  private String toMoney = "";
  private int toTexts = 0;
  private long tradeNo;
  private TextView tvDelay;
  
  public PrivateRenewActivity() {}
  
  private void UseAliPay()
  {
    show();
    this.payWay = 1;
    Jucore.getInstance().getClientInstance().GetNewProductReceipt(0L, 0, this.planId, OtherHelper.getRandomString(10), 1L, PhoneNumberUtil.countryCode2CountryShort(this.codeBean.countryCode), 0, Utils.getJsonAction((CodeBean)getIntent().getParcelableExtra("code_bean"), true));
    CMGAFlow.sendEventAndFlowEntry("Private Number Renew", "click_buy_btn_alipay", this.planId, 0L);
  }
  
  private void UsePayPal()
  {
    show();
    this.payWay = 2;
    Intent localIntent = new Intent(this, PrivatePackagePaypalActivity.class);
    localIntent.putExtra("planId", this.planId);
    if (Jucore.getInstance().getClientInstance().GetBuildType() == 1) {
      localIntent.putExtra("price", "0.01");
    }
    for (;;)
    {
      localIntent.putExtra("packageName", this.planName);
      localIntent.putExtra("orderAction", Utils.getJsonAction((CodeBean)getIntent().getParcelableExtra("code_bean"), true));
      localIntent.putExtra("tag", 5);
      localIntent.putExtra("is_renew", true);
      localIntent.putExtra("code_bean", getIntent().getParcelableExtra("code_bean"));
      localIntent.putExtra("receiver_user_id", "");
      startActivityForResult(localIntent, this.REQUEST_CODE_PAYPAL);
      CMGAFlow.sendEventAndFlowEntry("Private Number Renew", "click_buy_btn_paypal", this.planId, 0L);
      return;
      localIntent.putExtra("price", this.money);
    }
  }
  
  private void UserCreditCard()
  {
    show();
    Intent localIntent = new Intent(this, BrainTreePurchaseActivity.class);
    localIntent.putExtra("planId", this.planId);
    localIntent.putExtra("from", "PrivateRenewActivity");
    if (Jucore.getInstance().getClientInstance().GetBuildType() == 1) {
      localIntent.putExtra("price", "0.01");
    }
    for (;;)
    {
      localIntent.putExtra("packageName", this.planName);
      localIntent.putExtra("orderAction", Utils.getJsonAction((CodeBean)getIntent().getParcelableExtra("code_bean"), true));
      localIntent.putExtra("tag", 3);
      localIntent.putExtra("is_renew", true);
      localIntent.putExtra("code_bean", getIntent().getParcelableExtra("code_bean"));
      startActivityForResult(localIntent, this.REQUEST_CODE_BRAINTREE);
      CMGAFlow.sendEventAndFlowEntry("Private Number Renew", "click_buy_btn_creditcard", this.planId, 0L);
      return;
      localIntent.putExtra("price", this.money);
    }
  }
  
  private void buy()
  {
    this.mcb.registHandler(this.mHandler);
    if (KexinData.getInstance().isOnline)
    {
      if (Utils.showPhonerBuyResultDialog(this)) {
        return;
      }
      if (OtherHelper.isSimpleChineseLanguage(this))
      {
        UseAliPay();
        return;
      }
      localObject = getResources().getStringArray(2131623993);
      new AlertDialog.Builder(this).setTitle(2131166881).setItems((CharSequence[])localObject, this.payMethodItemClick_En).show();
      return;
    }
    Object localObject = new MyDialog(this);
    ((MyDialog)localObject).setTitle(2131165905);
    ((MyDialog)localObject).setMessage(2131165908);
    ((MyDialog)localObject).setSinglePositiveButton(2131165364, null);
    ((MyDialog)localObject).show();
  }
  
  private void checkAliPay()
  {
    try
    {
      this.mHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          if (PrivateRenewActivity.this.isFirst) {}
          int i;
          do
          {
            PrivateRenewActivity.access$2802(PrivateRenewActivity.this, false);
            do
            {
              return;
            } while (!OtherHelper.isSimpleChineseLanguage(PrivateRenewActivity.this));
            i = 0;
            Iterator localIterator = PrivateRenewActivity.this.getPackageManager().getInstalledPackages(0).iterator();
            while (localIterator.hasNext()) {
              if (((PackageInfo)localIterator.next()).applicationInfo.packageName.equals("com.alipay.android.app")) {
                i = 1;
              }
            }
          } while (i != 0);
          PrivateRenewActivity.this.getMyBalance();
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
  
  private int findIndexbyCountryCode(int paramInt, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
    {
      paramInt = -1;
      return paramInt;
    }
    int i = 0;
    for (;;)
    {
      if ((i >= paramArrayOfInt.length) || (paramArrayOfInt[i] == paramInt))
      {
        paramInt = i;
        if (i != paramArrayOfInt.length) {
          break;
        }
        return -1;
      }
      i += 1;
    }
  }
  
  private void getMyBalance()
  {
    this.callplan.endTime = (System.currentTimeMillis() / 1000L + this.callplan.expiration * 24 * 60 * 60);
    PrivateNumberTableOperation.insertCallPlan(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), this.callplan);
    try
    {
      Jucore.getInstance().getVirtualNumberInst().GetMyBalance(0L, 5);
      offLineDismiss();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CMTracer.e("PrivateRenewActivity", localException.getMessage());
        dismiss();
      }
    }
  }
  
  private void getPackageDetails()
  {
    show();
    if (KexinData.getInstance().isOnline) {
      if (!OtherHelper.isSimpleChineseLanguage(this)) {
        break label52;
      }
    }
    label52:
    for (String str = "CN";; str = "US")
    {
      Jucore.getInstance().getVirtualNumberInst().GetVirtualProductList(0L, 11, 2, 2, str, "com.coverme.covermeAdhoc", "");
      offLineDismiss();
      return;
    }
  }
  
  private void initData(int paramInt)
  {
    this.codeBean = ((CodeBean)getIntent().getParcelableExtra("code_bean"));
    this.mIsMultiCountry = this.codeBean.isMultiCountryNum();
    this.mHandler = Utils.buyPhoneNumber(String.valueOf(KexinData.getInstance(this).getCurrentAuthorityId()), this, this, this.codeBean, true, "");
    this.mHandler.setTag(5);
    this.mcb = new MyClientInstCallback(this);
    refresh(true, paramInt);
    getPackageDetails();
    smallPlanNotice();
  }
  
  private void initListener()
  {
    IntentFilter localIntentFilter = new IntentFilter("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS");
    localIntentFilter.addAction("ws.coverme.im.model.constant.WAIT_ALIPAY");
    localIntentFilter.addAction("ws.coverme.im.model.constant.RETURN_FOR_PAYPAL");
    localIntentFilter.addAction("ws.coverme.im.model.constant.GET_ORDER_RESULT");
    localIntentFilter.addAction("ws.coverme.im.model.constant.GET_VIRTUAL_PRODUCTLIST");
    localIntentFilter.addAction("android.intent.action.LOCALE_CHANGED");
    registerBroadcastReceiver(this.receiver, localIntentFilter);
    findViewById(2131235233).setOnClickListener(this);
    findViewById(2131235306).setOnClickListener(this);
  }
  
  private void initView()
  {
    this.tvDelay = ((TextView)findViewById(2131235285));
    this.progressDialog = new CMProgressDialog(this);
    this.progressDialog.setIndeterminate(true);
  }
  
  private void insertPhone()
  {
    PhoneBean localPhoneBean = PrivateNumberTableOperation.queryPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"));
    localPhoneBean.primaryFlag = false;
    localPhoneBean.status = this.status;
    localPhoneBean.tradeNo = this.tradeNo;
    localPhoneBean.couponId = this.couponId;
    localPhoneBean.productId = this.productId;
    localPhoneBean.paymentId = this.paymentId;
    localPhoneBean.payWay = this.payWay;
    localPhoneBean.provision = 15;
    localPhoneBean.getNumberTime = System.currentTimeMillis();
    localPhoneBean.callPlanId = this.callPlanId;
    localPhoneBean.readFlag = 0;
    localPhoneBean.isPrettyNumber = this.phoneBean.isPrettyNumber;
    localPhoneBean.providerId = this.phoneBean.providerId;
    localPhoneBean.packageServiceId = this.phoneBean.packageServiceId;
    PrivateNumberTableOperation.insertPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localPhoneBean);
  }
  
  private void insertTradeNo()
  {
    PhoneBean localPhoneBean = new PhoneBean();
    localPhoneBean.phoneNumber = getIntent().getStringExtra("phone_number");
    localPhoneBean.tradeNo = this.tradeNo;
    localPhoneBean.productId = this.productId;
    localPhoneBean.paymentId = this.paymentId;
    PrivateNumberTableOperation.insertTradeNo(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localPhoneBean);
  }
  
  private void reapplay()
  {
    if (this.status != 0)
    {
      PhoneBean localPhoneBean = PrivateNumberTableOperation.queryPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"));
      if ((localPhoneBean != null) && (localPhoneBean.status == 4)) {
        PrivateNumberTableOperation.updatePhoneNumberToReapply(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"));
      }
    }
  }
  
  private void refresh(boolean paramBoolean, int paramInt)
  {
    String str = String.valueOf(KexinData.getInstance().getCurrentAuthorityId());
    this.phoneBean = PrivateNumberTableOperation.queryPhoneNumber(str, getIntent().getStringExtra("phone_number"));
    if ((this.phoneBean == null) || ((this.phoneBean != null) && (StrUtil.isNull(this.phoneBean.phoneNumber)))) {
      finish();
    }
    this.callplan = PrivateNumberTableOperation.queryCallPlan(str, this.phoneBean.phoneNumber);
    if (this.callplan == null)
    {
      switch (paramInt)
      {
      default: 
        return;
      case 2: 
        this.callplan = new Callplan();
        return;
      case 1: 
        if ((this.phoneBean.status == 0) || (this.phoneBean.status == 4))
        {
          this.callplan = new Callplan();
          reselectPackage();
          return;
        }
        ToastUtil.showToast(this, 2131166985);
        finish();
        return;
      }
      finish();
      return;
    }
    Object localObject1 = new String[1];
    this.planId = this.callplan.productId;
    Object localObject2;
    label337:
    int k;
    int i;
    int j;
    if ("CM_AND_IAP_CALLINGPLAN_01".equals(this.callplan.productId))
    {
      this.money = getString(2131166865);
      localObject1 = getString(2131166859).split(",");
      this.planName = getString(2131166847);
      if (this.codeBean.isMultiCountryNum())
      {
        localObject1 = SharedPreferencesManager.getSharedPreferences(SharedPreferencesManager.MULTI_COUNTRY_PRIVATE_NUMBER + this.planId + this.codeBean.countryCode, this);
        if (StrUtil.isNull((String)localObject1)) {
          break label1567;
        }
        localObject2 = ((String)localObject1).split(",");
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          this.money = localObject2[0];
          localObject1 = localObject2;
        }
      }
      ((TextView)findViewById(2131234762)).setText(this.planName);
      CMTracer.i("PrivateRenewActivity", "elleray note : " + PrivateRenewActivity.class.getName() + "refresh");
      if ((localObject1 != null) && (localObject1.length >= 3))
      {
        this.maxTotalMinutes = Integer.parseInt(localObject1[1]);
        this.maxTotalTexts = Integer.parseInt(localObject1[2]);
        this.expiration = Integer.parseInt(localObject1[3]);
      }
      localObject1 = (TextView)findViewById(2131235235);
      localObject2 = (TextView)findViewById(2131235238);
      paramInt = this.callplan.maxTotalMinutes - this.callplan.usedMinutesIn - this.callplan.usedMinutesOut;
      k = this.callplan.maxTotalTexts - this.callplan.usedTextsIn - this.callplan.usedTextsOut;
      i = DateUtil.getDays(this.phoneBean.expireTime);
      if ((("CM_AND_IAP_CALLINGPLAN_01".equals(this.callplan.productId)) || ("CM_AND_IAP_NEW_CALLINGPLAN_01".equals(this.callplan.productId)) || ("CM_AND_IAP_CALLINGPLAN_03".equals(this.callplan.productId)) || ("CM_AND_IAP_CALLINGPLAN_06".equals(this.callplan.productId))) && (paramInt > this.callplan.maxTotalMinutes / 2) && (k < this.callplan.maxTotalTexts * 0.2D))
      {
        this.needRecommemd = true;
        ((TextView)findViewById(2131235308)).setVisibility(0);
      }
      CMTracer.i("PrivateRenewActivity", this.phoneBean.phoneNumber + ", maxMins:" + this.maxMins + ", remainMins, " + paramInt + ", remainDays:" + i);
      j = i;
      if (i < 0) {
        j = 0;
      }
      i = paramInt;
      if (paramInt < 0) {
        i = 0;
      }
      paramInt = k;
      if (k < 0) {
        paramInt = 0;
      }
      if ((this.maxMins > 0) && (this.maxMins < i)) {
        updatePhoneGetTime();
      }
      this.maxMins = i;
      k = Math.max(this.expiration, j);
      if (j > 0) {
        break label1614;
      }
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { this.money }));
      ((TextView)localObject2).setText(String.valueOf(this.maxTotalMinutes));
      ((TextView)localObject1).setText(String.valueOf(this.maxTotalTexts));
      this.callplan.maxTotalMinutes = 0;
      this.callplan.maxTotalTexts = 0;
      label834:
      if (("CM_AND_IAP_CALLINGPLAN_11".equals(this.callplan.productId)) || ("CM_AND_NEWCALLINGPLAN_01".equals(this.callplan.productId))) {
        ((TextView)localObject1).setText(2131167033);
      }
      localObject1 = this.callplan;
      ((Callplan)localObject1).maxTotalMinutes += this.maxTotalMinutes;
      localObject1 = this.callplan;
      ((Callplan)localObject1).maxTotalTexts += this.maxTotalTexts;
      if (this.callplan.expiration < k) {
        this.callplan.expiration = k;
      }
      localObject1 = this.tvDelay;
      if (!PrivateNumberTableOperation.queryHasTradeNo(str, this.phoneBean.phoneNumber)) {
        break label1675;
      }
      k = 0;
      label961:
      ((TextView)localObject1).setVisibility(k);
      if (i > 0) {
        break label1682;
      }
      k = 0;
      label975:
      this.fromMinutes = k;
      this.fromDays = j;
      this.toDays = Math.max(this.expiration, j);
      if (paramInt > 0) {
        break label1688;
      }
      k = 0;
      label1007:
      this.fromTexts = k;
      this.toTexts = this.maxTotalTexts;
      if (j > 0) {
        break label1694;
      }
      this.toMinutes = this.maxTotalMinutes;
    }
    for (this.toTexts = this.maxTotalTexts;; this.toTexts = (j + i))
    {
      this.toMoney = this.money;
      return;
      if ("CM_AND_IAP_NEW_CALLINGPLAN_01".equals(this.callplan.productId))
      {
        this.money = getString(2131166865);
        localObject1 = getString(2131166861).split(",");
        this.planName = getString(2131166847);
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_03".equals(this.callplan.productId))
      {
        this.money = getString(2131166866);
        localObject1 = getString(2131166862).split(",");
        this.planName = getString(2131166846);
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_06".equals(this.callplan.productId))
      {
        this.money = getString(2131166867);
        localObject1 = getString(2131166860).split(",");
        this.planName = getString(2131166848);
        break;
      }
      if ("CM_AND_IAP_NEW_CALLINGPLAN_06".equals(this.callplan.productId))
      {
        this.money = getString(2131166867);
        localObject1 = getString(2131166863).split(",");
        this.planName = getString(2131166849);
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_11".equals(this.callplan.productId))
      {
        ((RelativeLayout)findViewById(2131235304)).setVisibility(8);
        this.money = getString(2131166868);
        localObject1 = getString(2131166864).split(",");
        findViewById(2131235304).setVisibility(8);
        this.planName = getString(2131166850);
        break;
      }
      if ("CM_AND_NEWCALLINGPLAN_04".equals(this.callplan.productId))
      {
        this.planName = getString(2131166847);
        break;
      }
      if ("CM_AND_NEWCALLINGPLAN_03".equals(this.callplan.productId))
      {
        this.planName = getString(2131166848);
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_06".equals(this.callplan.productId))
      {
        this.planName = getString(2131166848);
        break;
      }
      if ("CM_AND_NEWCALLINGPLAN_02".equals(this.callplan.productId))
      {
        this.planName = getString(2131166849);
        break;
      }
      if ("CM_AND_NEWCALLINGPLAN_01".equals(this.callplan.productId))
      {
        findViewById(2131235304).setVisibility(8);
        this.planName = getString(2131166850);
        break;
      }
      this.money = getString(2131166865);
      localObject1 = getString(2131166861).split(",");
      this.planName = getString(2131166847);
      this.planId = "CM_AND_IAP_NEW_CALLINGPLAN_01";
      break;
      label1567:
      this.money = getString(2131166865);
      localObject1 = getString(2131166861).split(",");
      this.planName = getString(2131166847);
      this.planId = "CM_AND_IAP_NEW_CALLINGPLAN_01";
      break label337;
      label1614:
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { this.money }));
      ((TextView)localObject2).setText(String.valueOf(this.maxTotalMinutes));
      ((TextView)localObject1).setText(String.valueOf(this.maxTotalTexts));
      break label834;
      label1675:
      k = 8;
      break label961;
      label1682:
      k = i;
      break label975;
      label1688:
      k = paramInt;
      break label1007;
      label1694:
      k = this.maxTotalMinutes;
      j = i;
      if (i <= 0) {
        j = 0;
      }
      this.toMinutes = (k + j);
      j = this.maxTotalTexts;
      i = paramInt;
      if (paramInt <= 0) {
        i = 0;
      }
    }
  }
  
  private void refresh(boolean paramBoolean, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if ((StrUtil.isNull(paramString1)) || (StrUtil.isNull(paramString2)) || (StrUtil.isNull(paramString3)) || (StrUtil.isNull(paramString4))) {
      return;
    }
    String str = String.valueOf(KexinData.getInstance().getCurrentAuthorityId());
    this.phoneBean = PrivateNumberTableOperation.queryPhoneNumber(str, getIntent().getStringExtra("phone_number"));
    if ((this.phoneBean == null) || ((this.phoneBean != null) && (StrUtil.isNull(this.phoneBean.phoneNumber)))) {
      finish();
    }
    this.callplan = PrivateNumberTableOperation.queryCallPlan(str, this.phoneBean.phoneNumber);
    if (this.callplan == null)
    {
      switch (paramInt)
      {
      default: 
        return;
      case 1: 
        if ((this.phoneBean.status == 0) || (this.phoneBean.status == 4))
        {
          this.callplan = new Callplan();
          reselectPackage();
          return;
        }
      case 2: 
        this.callplan = new Callplan();
        return;
        ToastUtil.showToast(this, 2131166985);
        finish();
        return;
      }
      finish();
      return;
    }
    this.planId = this.callplan.productId;
    int k;
    int i;
    int j;
    if ("CM_AND_NEWCALLINGPLAN_04".equals(this.callplan.productId))
    {
      this.planName = getString(2131166847);
      ((TextView)findViewById(2131234762)).setText(this.planName);
      CMTracer.i("PrivateRenewActivity", "elleray note : " + PrivateRenewActivity.class.getName() + "refresh");
      this.maxTotalMinutes = Integer.parseInt(paramString2);
      this.maxTotalTexts = Integer.parseInt(paramString3);
      this.expiration = Integer.parseInt(paramString4);
      paramString2 = (TextView)findViewById(2131235235);
      paramString3 = (TextView)findViewById(2131235238);
      paramInt = this.callplan.maxTotalMinutes - this.callplan.usedMinutesIn - this.callplan.usedMinutesOut;
      k = this.callplan.maxTotalTexts - this.callplan.usedTextsIn - this.callplan.usedTextsOut;
      i = DateUtil.getDays(this.phoneBean.expireTime);
      if ((("CM_AND_IAP_CALLINGPLAN_01".equals(this.callplan.productId)) || ("CM_AND_IAP_NEW_CALLINGPLAN_01".equals(this.callplan.productId)) || ("CM_AND_IAP_CALLINGPLAN_03".equals(this.callplan.productId)) || ("CM_AND_IAP_CALLINGPLAN_06".equals(this.callplan.productId))) && (paramInt > this.callplan.maxTotalMinutes / 2) && (k < this.callplan.maxTotalTexts * 0.2D))
      {
        this.needRecommemd = true;
        ((TextView)findViewById(2131235308)).setVisibility(0);
      }
      CMTracer.i("PrivateRenewActivity", this.phoneBean.phoneNumber + ", maxMins:" + this.maxMins + ", remainMins, " + paramInt + ", remainDays:" + i);
      j = i;
      if (i < 0) {
        j = 0;
      }
      i = paramInt;
      if (paramInt < 0) {
        i = 0;
      }
      paramInt = k;
      if (k < 0) {
        paramInt = 0;
      }
      if ((this.maxMins > 0) && (this.maxMins < i)) {
        updatePhoneGetTime();
      }
      this.maxMins = i;
      k = Math.max(this.expiration, j);
      if (j > 0) {
        break label1101;
      }
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { paramString1 }));
      paramString3.setText(String.valueOf(this.maxTotalMinutes));
      paramString2.setText(String.valueOf(this.maxTotalTexts));
      this.callplan.maxTotalMinutes = 0;
      this.callplan.maxTotalTexts = 0;
      label733:
      if (("CM_AND_IAP_CALLINGPLAN_11".equals(this.callplan.productId)) || ("CM_AND_NEWCALLINGPLAN_01".equals(this.callplan.productId))) {
        paramString2.setText(2131167033);
      }
      paramString2 = this.callplan;
      paramString2.maxTotalMinutes += this.maxTotalMinutes;
      paramString2 = this.callplan;
      paramString2.maxTotalTexts += this.maxTotalTexts;
      if (this.callplan.expiration < k) {
        this.callplan.expiration = k;
      }
      paramString2 = this.tvDelay;
      if (!PrivateNumberTableOperation.queryHasTradeNo(str, this.phoneBean.phoneNumber)) {
        break label1159;
      }
      k = 0;
      label860:
      paramString2.setVisibility(k);
      if (i > 0) {
        break label1166;
      }
      k = 0;
      label875:
      this.fromMinutes = k;
      this.fromDays = j;
      this.toDays = Math.max(this.expiration, j);
      if (paramInt > 0) {
        break label1173;
      }
      k = 0;
      label907:
      this.fromTexts = k;
      this.toTexts = this.maxTotalTexts;
      if (j > 0) {
        break label1179;
      }
      this.toMinutes = this.maxTotalMinutes;
    }
    for (this.toTexts = this.maxTotalTexts;; this.toTexts = (j + i))
    {
      this.toMoney = paramString1;
      return;
      if ("CM_AND_NEWCALLINGPLAN_03".equals(this.callplan.productId))
      {
        this.planName = getString(2131166848);
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_06".equals(this.callplan.productId))
      {
        this.planName = getString(2131166848);
        break;
      }
      if ("CM_AND_NEWCALLINGPLAN_02".equals(this.callplan.productId))
      {
        this.planName = getString(2131166849);
        break;
      }
      if ("CM_AND_NEWCALLINGPLAN_01".equals(this.callplan.productId))
      {
        findViewById(2131235304).setVisibility(8);
        this.planName = getString(2131166850);
        break;
      }
      this.planName = getString(2131166847);
      this.planId = "CM_AND_IAP_NEW_CALLINGPLAN_01";
      break;
      label1101:
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { paramString1 }));
      paramString3.setText(String.valueOf(this.maxTotalMinutes));
      paramString2.setText(String.valueOf(this.maxTotalTexts));
      break label733;
      label1159:
      k = 8;
      break label860;
      label1166:
      k = i;
      break label875;
      label1173:
      k = paramInt;
      break label907;
      label1179:
      k = this.maxTotalMinutes;
      j = i;
      if (i <= 0) {
        j = 0;
      }
      this.toMinutes = (k + j);
      j = this.maxTotalTexts;
      i = paramInt;
      if (paramInt <= 0) {
        i = 0;
      }
    }
  }
  
  private void refreshUI(boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    if (paramBoolean2)
    {
      refresh(paramBoolean1, paramInt, this.mPrice, this.mPackageMinutes, this.mPackageTexts, this.mPackageValid);
      return;
    }
    refresh(paramBoolean1, paramInt);
  }
  
  private void reselectPackage()
  {
    Intent localIntent = new Intent();
    if (this.mIsMultiCountry) {
      localIntent.setClass(this, PrivateMultiCountrySelectPackageActivity.class);
    }
    for (;;)
    {
      localIntent.putExtras(getIntent());
      localIntent.putExtra("phone_number_format", this.phoneBean.getFormatPhoneNumber());
      localIntent.putExtra("phone_number", this.phoneBean.phoneNumber);
      localIntent.putExtra("is_renew", true);
      localIntent.putExtra("extra_private_number_renew_ex_planid", this.planId);
      localIntent.putExtra("select_package_need_recommend", this.needRecommemd);
      startActivityForResult(localIntent, this.REQUEST_CODE_SELECT_PACKET);
      return;
      localIntent.setClass(this, PrivateSelectPackageActivity.class);
    }
  }
  
  private void reselectPhoneNumber(String paramString)
  {
    Intent localIntent = new Intent(this, PrivateInterimActivity.class);
    localIntent.putExtra("coupon", paramString);
    localIntent.putExtra("callPlanId", this.callPlanId);
    localIntent.putExtra("phone_number", getIntent().getStringExtra("phone_number"));
    if ((this.phoneBean.isPrettyNumber) || ("CM_AND_IAP_CALLINGPLAN_06".equals(this.planId)) || ("CM_AND_IAP_NEW_CALLINGPLAN_06".equals(this.planId)) || ("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId))) {}
    for (boolean bool = true;; bool = false)
    {
      localIntent.putExtra("is_pretty_number", bool);
      startActivity(localIntent);
      finish();
      dismiss();
      return;
    }
  }
  
  private void smallPlanNotice()
  {
    if ((this.callplan != null) && (this.callplan.productId.equals("CM_AND_IAP_CALLINGPLAN_03")))
    {
      MyDialog localMyDialog = new MyDialog(this);
      localMyDialog.setTitle(2131165388);
      localMyDialog.setMessage(2131166915);
      localMyDialog.setSinglePositiveButton(2131165364, null);
      localMyDialog.show();
    }
  }
  
  private void toRenewDetial()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, PrivateRenewDetailActivity.class);
    localIntent.putExtra("phone_number", this.phoneBean.phoneNumber);
    localIntent.putExtra("code_bean", this.codeBean);
    localIntent.putExtra("extra_private_number_refill_min", this.fromMinutes);
    localIntent.putExtra("extra_private_number_refill_text", this.fromTexts);
    localIntent.putExtra("extra_private_number_refill_expiration", this.fromDays);
    localIntent.putExtra("extra_private_number_refill_min_total", this.toMinutes);
    localIntent.putExtra("extra_private_number_refill_text_total", this.toTexts);
    localIntent.putExtra("extra_private_number_refill_expiration_total", this.toDays);
    localIntent.putExtra("extra_private_number_refill_package_name", this.planName);
    localIntent.putExtra("extra_private_number_refill_package_money", this.toMoney);
    localIntent.putExtra("extra_private_number_refill_package_planid", this.planId);
    localIntent.putExtra("extra_private_number_refill_package_planname", this.planName);
    startActivity(localIntent);
  }
  
  private void updatePhoneGetTime()
  {
    CMTracer.i("PrivateRenewActivity", this.phoneBean.phoneNumber + ", updatePhoneGetTime");
    PrivateNumberTableOperation.updatePhoneNumberGetTime(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"));
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
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt2 != -1) || ((paramIntent != null) && (!paramIntent.getBooleanExtra("is_paypal", false)))) {
      dismiss();
    }
    if ((this.REQUEST_CODE_BRAINTREE == paramInt1) && (paramInt2 == -1) && (paramIntent != null))
    {
      Intent localIntent = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
      localIntent.setPackage(getPackageName());
      sendBroadcast(localIntent);
      show();
      this.needTofinish = paramIntent.getBooleanExtra("needToFinish", false);
      getMyBalance();
    }
    if ((paramInt1 == this.REQUEST_CODE_SELECT_PACKET) && (paramInt2 == -1))
    {
      setResult(-1, new Intent());
      finish();
    }
    refresh(true, 3);
    if ((this.REQUEST_CODE_PAYPAL == paramInt1) && (paramInt2 == -1) && (paramIntent != null))
    {
      paramIntent = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
      paramIntent.setPackage(getPackageName());
      sendBroadcast(paramIntent);
      getMyBalance();
    }
  }
  
  public void onClick(View paramView)
  {
    if (ClickTimeSpanUtil.isFastDoubleClick(400L)) {
      return;
    }
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131234776: 
      finish();
      return;
    case 2131235306: 
      buy();
      return;
    case 2131235304: 
      reselectPackage();
      return;
    }
    toRenewDetial();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903581);
    initView();
    initData(1);
    initListener();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    KexinData.getInstance().unLockInActivity = false;
    KexinData.getInstance().doNotKillApp = false;
    unregisterBroadcastReceiver(this.receiver);
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
