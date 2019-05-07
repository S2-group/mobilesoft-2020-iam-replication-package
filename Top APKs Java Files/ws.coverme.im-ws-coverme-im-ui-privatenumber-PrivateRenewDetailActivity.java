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
import android.widget.Button;
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

public class PrivateRenewDetailActivity
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
  private String packageName = "";
  private DialogInterface.OnClickListener payMethodItemClick_En = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      default: 
        return;
      case 0: 
        PrivateRenewDetailActivity.this.UserCreditCard();
        return;
      }
      PrivateRenewDetailActivity.this.UsePayPal();
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
      label121:
      label187:
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
                PrivateRenewDetailActivity.this.dismiss();
                return;
              }
              j = paramAnonymousIntent.getIntExtra("command_tag", 0);
            } while ((j != 5) && (j != 11));
            if ("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS".equals(str))
            {
              if (PrivateRenewDetailActivity.this.needTofinish)
              {
                PrivateRenewDetailActivity.this.dismiss();
                paramAnonymousContext = new Intent();
                PrivateRenewDetailActivity.this.setResult(-1, paramAnonymousContext);
                PrivateRenewDetailActivity.this.finish();
              }
              paramAnonymousContext = PrivateRenewDetailActivity.this;
              boolean bool;
              if (j == 5)
              {
                bool = true;
                if (j != 5) {
                  break label187;
                }
              }
              for (i = 1;; i = 2)
              {
                paramAnonymousContext.refreshUI(bool, i, PrivateRenewDetailActivity.this.mIsMultiCountry);
                if (j != 5) {
                  break;
                }
                PrivateRenewDetailActivity.this.dismiss();
                paramAnonymousContext = new Intent();
                PrivateRenewDetailActivity.this.setResult(-1, paramAnonymousContext);
                PrivateRenewDetailActivity.this.finish();
                return;
                bool = false;
                break label121;
              }
            }
            if (!"ws.coverme.im.model.constant.GET_ORDER_RESULT".equals(str)) {
              break;
            }
          } while (j != 5);
          CMTracer.i("PrivateRenewActivity", "EXTRA_PHONE_NUMBER2 : " + PrivateRenewDetailActivity.this.getIntent().getStringExtra("phone_number") + ", coupon : " + paramAnonymousIntent.getIntExtra("coupon", -1));
          PrivateRenewDetailActivity.access$302(PrivateRenewDetailActivity.this, paramAnonymousIntent.getStringExtra("coupon"));
          PrivateRenewDetailActivity.access$402(PrivateRenewDetailActivity.this, paramAnonymousIntent.getStringExtra("productId"));
          PrivateRenewDetailActivity.access$502(PrivateRenewDetailActivity.this, paramAnonymousIntent.getStringExtra("paymentId"));
          PrivateRenewDetailActivity.access$602(PrivateRenewDetailActivity.this, paramAnonymousIntent.getIntExtra("callPlanId", 0));
          PrivateRenewDetailActivity.access$702(PrivateRenewDetailActivity.this, paramAnonymousIntent.getLongExtra("orderNO", -1L));
          if (!StrUtil.isNull(PrivateRenewDetailActivity.this.couponId))
          {
            PrivateRenewDetailActivity.access$802(PrivateRenewDetailActivity.this, 2);
            PrivateRenewDetailActivity.this.insertPhone();
            PrivateRenewDetailActivity.this.reselectPhoneNumber(String.valueOf(PrivateRenewDetailActivity.this.couponId));
            return;
          }
          CMTracer.i("PrivateRenewActivity", "test_errorCode2 : " + paramAnonymousIntent.getBooleanExtra("result", false));
          PrivateRenewDetailActivity.access$802(PrivateRenewDetailActivity.this, paramAnonymousIntent.getIntExtra("orderStatus", -1));
          if (PrivateRenewDetailActivity.this.status == 5)
          {
            PrivateRenewDetailActivity.this.reapplay();
            PrivateRenewDetailActivity.this.dismiss();
          }
          if (paramAnonymousIntent.getBooleanExtra("result", false))
          {
            PrivateRenewDetailActivity.this.reapplay();
            if (PrivateRenewDetailActivity.this.status == 1)
            {
              PrivateRenewDetailActivity.this.insertTradeNo();
              paramAnonymousContext = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
              paramAnonymousContext.setPackage(PrivateRenewDetailActivity.this.getPackageName());
              PrivateRenewDetailActivity.this.sendBroadcast(paramAnonymousContext);
            }
            for (;;)
            {
              PrivateRenewDetailActivity.this.getMyBalance();
              return;
              if (PrivateRenewDetailActivity.this.status == 0) {
                PrivateRenewDetailActivity.this.updatePhoneGetTime();
              }
            }
          }
          PrivateRenewDetailActivity.this.dismiss();
          return;
          if (!"ws.coverme.im.model.constant.RETURN_FOR_PAYPAL".equals(str)) {
            break;
          }
        } while ((j != 5) || (!PrivateRenewDetailActivity.this.getIntent().getStringExtra("phone_number").equals(paramAnonymousIntent.getStringExtra("phone_number"))));
        PrivateRenewDetailActivity.this.refreshUI(true, 1, PrivateRenewDetailActivity.this.mIsMultiCountry);
        PrivateRenewDetailActivity.this.dismiss();
        return;
      } while (!"ws.coverme.im.model.constant.GET_VIRTUAL_PRODUCTLIST".equals(str));
      int i = paramAnonymousIntent.getIntExtra("errCode", -1);
      if (j == 11)
      {
        if (i != 0) {
          break label908;
        }
        CMTracer.i("PrivateRenewActivity", "get virtual product list errorCode:" + i);
        paramAnonymousContext = paramAnonymousIntent.getParcelableArrayListExtra("product_list");
        if ((paramAnonymousContext != null) && (paramAnonymousContext.size() > 0))
        {
          paramAnonymousContext = paramAnonymousContext.iterator();
          while (paramAnonymousContext.hasNext())
          {
            paramAnonymousIntent = (VirtualProductBean)paramAnonymousContext.next();
            if (!PrivateRenewDetailActivity.this.mIsMultiCountry) {
              break label861;
            }
            i = PrivateRenewDetailActivity.this.findIndexbyCountryCode(PrivateRenewDetailActivity.this.codeBean.countryCode, paramAnonymousIntent.countryCodeScope);
            if (i >= 0)
            {
              PrivateRenewDetailActivity.access$1702(PrivateRenewDetailActivity.this, Utils.getFormateFloatPrice(paramAnonymousIntent.price));
              PrivateRenewDetailActivity.access$1802(PrivateRenewDetailActivity.this, String.valueOf(paramAnonymousIntent.minutesArrays[i]));
              PrivateRenewDetailActivity.access$1902(PrivateRenewDetailActivity.this, String.valueOf(paramAnonymousIntent.textsArrays[i]));
              PrivateRenewDetailActivity.access$2002(PrivateRenewDetailActivity.this, String.valueOf(paramAnonymousIntent.expiration));
              if (PrivateRenewDetailActivity.this.callplan.productId.equals(paramAnonymousIntent.productId)) {
                if (paramAnonymousIntent.price > 0.0F) {
                  PrivateRenewDetailActivity.access$2202(PrivateRenewDetailActivity.this, String.valueOf(paramAnonymousIntent.price));
                }
              }
            }
          }
        }
      }
      for (;;)
      {
        PrivateRenewDetailActivity.this.dismiss();
        return;
        label861:
        if (!PrivateRenewDetailActivity.this.callplan.productId.equals(paramAnonymousIntent.productId)) {
          break;
        }
        if (paramAnonymousIntent.price > 0.0F)
        {
          PrivateRenewDetailActivity.access$2202(PrivateRenewDetailActivity.this, String.valueOf(paramAnonymousIntent.price));
          continue;
          label908:
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
  
  public PrivateRenewDetailActivity() {}
  
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
      localIntent.putExtra("price", this.toMoney);
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
      localIntent.putExtra("price", this.toMoney);
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
          if (PrivateRenewDetailActivity.this.isFirst) {}
          int i;
          do
          {
            PrivateRenewDetailActivity.access$2502(PrivateRenewDetailActivity.this, false);
            do
            {
              return;
            } while (!OtherHelper.isSimpleChineseLanguage(PrivateRenewDetailActivity.this));
            i = 0;
            Iterator localIterator = PrivateRenewDetailActivity.this.getPackageManager().getInstalledPackages(0).iterator();
            while (localIterator.hasNext()) {
              if (((PackageInfo)localIterator.next()).applicationInfo.packageName.equals("com.alipay.android.app")) {
                i = 1;
              }
            }
          } while (i != 0);
          PrivateRenewDetailActivity.this.getMyBalance();
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
      if ((!OtherHelper.isSimpleChineseLanguage(this)) || (this.mIsMultiCountry)) {
        break label59;
      }
    }
    label59:
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
    this.fromTexts = getIntent().getIntExtra("extra_private_number_refill_text", 0);
    this.fromMinutes = getIntent().getIntExtra("extra_private_number_refill_min", 0);
    this.fromDays = getIntent().getIntExtra("extra_private_number_refill_expiration", 0);
    this.toTexts = getIntent().getIntExtra("extra_private_number_refill_text_total", 0);
    this.toMinutes = getIntent().getIntExtra("extra_private_number_refill_min_total", 0);
    this.toDays = getIntent().getIntExtra("extra_private_number_refill_expiration_total", 0);
    this.packageName = getIntent().getStringExtra("extra_private_number_refill_package_name");
    this.toMoney = getIntent().getStringExtra("extra_private_number_refill_package_money");
    this.planId = getIntent().getStringExtra("extra_private_number_refill_package_planid");
    this.planName = getIntent().getStringExtra("extra_private_number_refill_package_planname");
    this.mIsMultiCountry = this.codeBean.isMultiCountryNum();
    this.mHandler = Utils.buyPhoneNumber(String.valueOf(KexinData.getInstance(this).getCurrentAuthorityId()), this, this, this.codeBean, true, "");
    this.mHandler.setTag(5);
    this.mcb = new MyClientInstCallback(this);
    loadData();
  }
  
  private void initListener()
  {
    IntentFilter localIntentFilter = new IntentFilter("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS");
    localIntentFilter.addAction("ws.coverme.im.model.constant.WAIT_ALIPAY");
    localIntentFilter.addAction("ws.coverme.im.model.constant.RETURN_FOR_PAYPAL");
    localIntentFilter.addAction("ws.coverme.im.model.constant.GET_ORDER_RESULT");
    localIntentFilter.addAction("ws.coverme.im.model.constant.GET_VIRTUAL_PRODUCTLIST");
    registerBroadcastReceiver(this.receiver, localIntentFilter);
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
  
  private void loadData()
  {
    String str = String.valueOf(KexinData.getInstance().getCurrentAuthorityId());
    this.phoneBean = PrivateNumberTableOperation.queryPhoneNumber(str, getIntent().getStringExtra("phone_number"));
    if ((this.phoneBean == null) || ((this.phoneBean != null) && (StrUtil.isNull(this.phoneBean.phoneNumber)))) {
      finish();
    }
    this.callplan = PrivateNumberTableOperation.queryCallPlan(str, this.phoneBean.phoneNumber);
    TextView localTextView1 = (TextView)findViewById(2131235221);
    TextView localTextView2 = (TextView)findViewById(2131235226);
    TextView localTextView3 = (TextView)findViewById(2131235231);
    TextView localTextView4 = (TextView)findViewById(2131235219);
    TextView localTextView5 = (TextView)findViewById(2131235224);
    TextView localTextView6 = (TextView)findViewById(2131235229);
    localTextView4.setTextColor(getResources().getColor(2131361969));
    localTextView5.setTextColor(getResources().getColor(2131361969));
    localTextView6.setTextColor(getResources().getColor(2131361969));
    localTextView3.setText(String.valueOf(this.fromMinutes));
    localTextView2.setText(String.valueOf(this.fromTexts));
    localTextView1.setText(String.valueOf(this.fromDays));
    localTextView4.setText(getString(2131167000, new Object[] { this.toDays + "" }));
    localTextView5.setText(String.valueOf(this.toTexts));
    if (("CM_AND_IAP_CALLINGPLAN_11".equals(this.callplan.productId)) || ("CM_AND_NEWCALLINGPLAN_01".equals(this.callplan.productId)))
    {
      localTextView5.setText(2131167033);
      localTextView2.setText(2131167033);
    }
    localTextView6.setText(String.valueOf(this.toMinutes));
    localTextView1 = this.tvDelay;
    if (PrivateNumberTableOperation.queryHasTradeNo(str, this.phoneBean.phoneNumber)) {}
    for (int i = 0;; i = 8)
    {
      localTextView1.setVisibility(i);
      ((TextView)findViewById(2131235216)).setText(this.phoneBean.getFormatPhoneNumber());
      ((TextView)findViewById(2131235309)).setText(this.packageName);
      ((Button)findViewById(2131235310)).setText(getString(2131166912, new Object[] { this.toMoney }));
      return;
    }
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
    if ("CM_AND_IAP_CALLINGPLAN_01".equals(this.callplan.productId))
    {
      this.money = getString(2131166865);
      localObject1 = getString(2131166859).split(",");
      this.planName = getString(2131166847);
      if (this.codeBean.isMultiCountryNum())
      {
        localObject1 = SharedPreferencesManager.getSharedPreferences(SharedPreferencesManager.MULTI_COUNTRY_PRIVATE_NUMBER + this.planId + this.codeBean.countryCode, this);
        if (StrUtil.isNull((String)localObject1)) {
          break label1560;
        }
        localObject2 = ((String)localObject1).split(",");
        localObject1 = localObject2;
        if (localObject2 != null)
        {
          this.money = localObject2[0];
          localObject1 = localObject2;
        }
      }
      label337:
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
        break label1607;
      }
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { this.money }));
      ((TextView)localObject2).setText(String.valueOf(this.maxTotalMinutes));
      ((TextView)localObject1).setText(String.valueOf(this.maxTotalTexts));
      this.callplan.maxTotalMinutes = 0;
      this.callplan.maxTotalTexts = 0;
      label835:
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
        break label1668;
      }
      k = 0;
      label962:
      ((TextView)localObject1).setVisibility(k);
      if (i > 0) {
        break label1675;
      }
      k = 0;
      label976:
      this.fromMinutes = k;
      this.fromDays = j;
      this.toDays = Math.max(this.expiration, j);
      if (paramInt > 0) {
        break label1681;
      }
    }
    label1560:
    label1607:
    label1668:
    label1675:
    label1681:
    for (int k = 0;; k = paramInt)
    {
      this.fromTexts = k;
      this.toTexts = this.maxTotalTexts;
      if (j > 0) {
        break label1687;
      }
      this.toMinutes = this.maxTotalMinutes;
      this.toTexts = this.maxTotalTexts;
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
      this.money = getString(2131166865);
      localObject1 = getString(2131166861).split(",");
      this.planName = getString(2131166847);
      this.planId = "CM_AND_IAP_NEW_CALLINGPLAN_01";
      break label337;
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { this.money }));
      ((TextView)localObject2).setText(String.valueOf(this.maxTotalMinutes));
      ((TextView)localObject1).setText(String.valueOf(this.maxTotalTexts));
      break label835;
      k = 8;
      break label962;
      k = i;
      break label976;
    }
    label1687:
    k = this.maxTotalMinutes;
    int j = i;
    if (i <= 0) {
      j = 0;
    }
    this.toMinutes = (k + j);
    j = this.maxTotalTexts;
    int i = paramInt;
    if (paramInt <= 0) {
      i = 0;
    }
    this.toTexts = (j + i);
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
        break label1089;
      }
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { paramString1 }));
      paramString3.setText(String.valueOf(this.maxTotalMinutes));
      paramString2.setText(String.valueOf(this.maxTotalTexts));
      this.callplan.maxTotalMinutes = 0;
      this.callplan.maxTotalTexts = 0;
      label734:
      if (("CM_AND_IAP_CALLINGPLAN_11".equals(this.callplan.productId)) || ("CM_AND_NEWCALLINGPLAN_01".equals(this.callplan.productId))) {
        paramString2.setText(2131167033);
      }
      paramString1 = this.callplan;
      paramString1.maxTotalMinutes += this.maxTotalMinutes;
      paramString1 = this.callplan;
      paramString1.maxTotalTexts += this.maxTotalTexts;
      if (this.callplan.expiration < k) {
        this.callplan.expiration = k;
      }
      paramString1 = this.tvDelay;
      if (!PrivateNumberTableOperation.queryHasTradeNo(str, this.phoneBean.phoneNumber)) {
        break label1147;
      }
      k = 0;
      label854:
      paramString1.setVisibility(k);
      if (i > 0) {
        break label1154;
      }
      k = 0;
      label868:
      this.fromMinutes = k;
      this.fromDays = j;
      this.toDays = Math.max(this.expiration, j);
      if (paramInt > 0) {
        break label1161;
      }
    }
    label1089:
    label1147:
    label1154:
    label1161:
    for (int k = 0;; k = paramInt)
    {
      this.fromTexts = k;
      this.toTexts = this.maxTotalTexts;
      if (j > 0) {
        break label1167;
      }
      this.toMinutes = this.maxTotalMinutes;
      this.toTexts = this.maxTotalTexts;
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
      ((TextView)findViewById(2131235232)).setText(getString(2131168265, new Object[] { paramString1 }));
      paramString3.setText(String.valueOf(this.maxTotalMinutes));
      paramString2.setText(String.valueOf(this.maxTotalTexts));
      break label734;
      k = 8;
      break label854;
      k = i;
      break label868;
    }
    label1167:
    k = this.maxTotalMinutes;
    int j = i;
    if (i <= 0) {
      j = 0;
    }
    this.toMinutes = (k + j);
    j = this.maxTotalTexts;
    int i = paramInt;
    if (paramInt <= 0) {
      i = 0;
    }
    this.toTexts = (j + i);
  }
  
  private void refreshUI(boolean paramBoolean1, int paramInt, boolean paramBoolean2) {}
  
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
  
  private void updateBeanData(VirtualProductBean paramVirtualProductBean)
  {
    if (this.mIsMultiCountry)
    {
      i = findIndexbyCountryCode(this.codeBean.countryCode, paramVirtualProductBean.countryCodeScope);
      if (i >= 0) {}
    }
    while ((!this.callplan.productId.equals(paramVirtualProductBean.productId)) || (paramVirtualProductBean.price <= 0.0F))
    {
      do
      {
        int i;
        return;
        this.mPrice = Utils.getFormateFloatPrice(paramVirtualProductBean.price);
        this.mPackageMinutes = String.valueOf(paramVirtualProductBean.minutesArrays[i]);
        this.mPackageTexts = String.valueOf(paramVirtualProductBean.textsArrays[i]);
        this.mPackageValid = String.valueOf(paramVirtualProductBean.expiration);
      } while (!this.callplan.productId.equals(paramVirtualProductBean.productId));
      if (paramVirtualProductBean.price > 0.0F)
      {
        this.money = String.valueOf(paramVirtualProductBean.price);
        ((Button)findViewById(2131235310)).setText(getString(2131166971, new Object[] { this.mPrice }));
      }
      refresh(true, 1, this.mPrice, this.mPackageMinutes, this.mPackageTexts, this.mPackageValid);
      return;
    }
    this.money = String.valueOf(paramVirtualProductBean.price);
    ((Button)findViewById(2131235310)).setText(getString(2131166971, new Object[] { this.money }));
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
    case 2131235310: 
      buy();
      return;
    }
    reselectPackage();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903582);
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
    checkAliPay();
  }
}
