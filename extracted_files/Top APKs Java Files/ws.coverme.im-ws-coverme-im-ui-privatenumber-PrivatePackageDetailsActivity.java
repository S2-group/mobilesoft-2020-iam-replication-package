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
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;
import ws.coverme.im.JucoreAdp.CbImplement.MyClientInstCallback;
import ws.coverme.im.JucoreAdp.ClientInst.IClientInstance;
import ws.coverme.im.JucoreAdp.Jucore;
import ws.coverme.im.JucoreAdp.Types.DataStructs.Callplan;
import ws.coverme.im.JucoreAdp.Types.DataStructs.PrivateNumberSettingParam;
import ws.coverme.im.JucoreAdp.VirtualNumber.IVirtualNumberInstance;
import ws.coverme.im.appsflyer.AppsFlyer;
import ws.coverme.im.dll.PrivateNumberTableOperation;
import ws.coverme.im.dll.SharedPreferencesManager;
import ws.coverme.im.ga.CMGAFlow;
import ws.coverme.im.model.KexinData;
import ws.coverme.im.privatenumber.bean.CodeBean;
import ws.coverme.im.privatenumber.bean.PhoneBean;
import ws.coverme.im.ui.chat.secretary.SecretaryLocalManager;
import ws.coverme.im.ui.interfaces.IBuyByAliPay;
import ws.coverme.im.ui.purchase.BrainTreePurchaseActivity;
import ws.coverme.im.ui.view.MyDialog;
import ws.coverme.im.util.CMProgressDialog;
import ws.coverme.im.util.CMTracer;
import ws.coverme.im.util.ClickTimeSpanUtil;
import ws.coverme.im.util.DateUtil;
import ws.coverme.im.util.OtherHelper;
import ws.coverme.im.util.StrUtil;
import ws.coverme.im.util.Utils;

public class PrivatePackageDetailsActivity
  extends BasePrivateActivity
  implements View.OnClickListener, IBuyByAliPay
{
  private static int REQUEST_CODE_BRAINTREE = 101;
  private static final String TAG = "PrivatePackageDetailsActivity";
  private int callPlanId;
  private CodeBean codeBean;
  private String couponId;
  private boolean isBrainTree = false;
  private boolean isFirst = true;
  private boolean isRenew;
  private PrivateHandler mHandler;
  private ImageView mUltimateIcon;
  private MyClientInstCallback mcb;
  private DialogInterface.OnClickListener payMethodItemClick_En = new DialogInterface.OnClickListener()
  {
    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      default: 
        return;
      case 0: 
        PrivatePackageDetailsActivity.this.UserCreditCard();
        return;
      }
      PrivatePackageDetailsActivity.this.UsePayPal();
    }
  };
  private int payWay;
  private String paymentId;
  private String planId;
  private String price;
  private boolean primaryFlag;
  private String productId;
  BroadcastReceiver receiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (!KexinData.getInstance().isOnline) {}
      label9:
      do
      {
        do
        {
          do
          {
            break label9;
            do
            {
              return;
            } while (!PrivatePackageDetailsActivity.this.isVersionValid());
            paramAnonymousContext = paramAnonymousIntent.getAction();
            if ("ws.coverme.im.model.constant.WAIT_ALIPAY".equals(paramAnonymousContext))
            {
              PrivatePackageDetailsActivity.this.dismiss();
              return;
            }
            if ("android.intent.action.LOCALE_CHANGED".equals(paramAnonymousContext))
            {
              CMTracer.i("PrivatePackageDetailsActivity", "ACTION_LOCALE_CHANGED");
              PrivatePackageDetailsActivity.this.finish();
              return;
            }
          } while (paramAnonymousIntent.getIntExtra("command_tag", 0) != 3);
          if ("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS".equals(paramAnonymousContext))
          {
            if (PrivatePackageDetailsActivity.this.isRenew)
            {
              PrivatePackageDetailsActivity.this.renew();
              return;
            }
            PrivatePackageDetailsActivity.this.setPhoneAttrs();
            return;
          }
          if (!"ws.coverme.im.model.constant.RETURN_FOR_PAYPAL".equals(paramAnonymousContext)) {
            break;
          }
        } while (!PrivatePackageDetailsActivity.this.getIntent().getStringExtra("phone_number").equals(paramAnonymousIntent.getStringExtra("phone_number")));
        PrivatePackageDetailsActivity.this.returnPhoneNumber();
        return;
        if ("ws.coverme.im.model.constant.SET_PRIVATE_NUMBER_PARAM".equals(paramAnonymousContext))
        {
          PrivatePackageDetailsActivity.this.managerPhoneNumber(paramAnonymousIntent.getBooleanExtra("param_staus", false));
          return;
        }
      } while (!"ws.coverme.im.model.constant.GET_ORDER_RESULT".equals(paramAnonymousContext));
      PrivatePackageDetailsActivity.access$502(PrivatePackageDetailsActivity.this, paramAnonymousIntent.getStringExtra("coupon"));
      PrivatePackageDetailsActivity.access$602(PrivatePackageDetailsActivity.this, paramAnonymousIntent.getStringExtra("productId"));
      PrivatePackageDetailsActivity.access$702(PrivatePackageDetailsActivity.this, paramAnonymousIntent.getStringExtra("paymentId"));
      PrivatePackageDetailsActivity.access$802(PrivatePackageDetailsActivity.this, paramAnonymousIntent.getIntExtra("callPlanId", 0));
      PrivatePackageDetailsActivity.access$902(PrivatePackageDetailsActivity.this, paramAnonymousIntent.getLongExtra("orderNO", -1L));
      CMTracer.i("PrivatePackageDetailsActivity", "EXTRA_PHONE_NUMBER : " + PrivatePackageDetailsActivity.this.getIntent().getStringExtra("phone_number") + ", coupon : " + PrivatePackageDetailsActivity.this.couponId);
      if (!StrUtil.isNull(PrivatePackageDetailsActivity.this.couponId))
      {
        PrivatePackageDetailsActivity.access$1002(PrivatePackageDetailsActivity.this, 2);
        PrivatePackageDetailsActivity.this.insertCallplan();
        PrivatePackageDetailsActivity.this.insertPhone();
        PrivatePackageDetailsActivity.this.reselectPhoneNumber(String.valueOf(PrivatePackageDetailsActivity.this.couponId));
        return;
      }
      PrivatePackageDetailsActivity.access$1002(PrivatePackageDetailsActivity.this, paramAnonymousIntent.getIntExtra("orderStatus", -1));
      if (PrivatePackageDetailsActivity.this.status == 5)
      {
        if (PrivatePackageDetailsActivity.this.isRenew) {
          PrivatePackageDetailsActivity.this.renew();
        }
      }
      else
      {
        CMTracer.i("PrivatePackageDetailsActivity", "test_errorCode : " + paramAnonymousIntent.getBooleanExtra("result", false) + " = isRenew:" + PrivatePackageDetailsActivity.this.isRenew);
        if (!paramAnonymousIntent.getBooleanExtra("result", false)) {
          break label619;
        }
        if (!PrivatePackageDetailsActivity.this.isRenew) {
          PrivatePackageDetailsActivity.this.insertPhone();
        }
        if (PrivatePackageDetailsActivity.this.status != 1) {
          break label556;
        }
        if (PrivatePackageDetailsActivity.this.isRenew) {
          PrivatePackageDetailsActivity.this.insertTradeNo();
        }
        paramAnonymousContext = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
        paramAnonymousContext.setPackage(PrivatePackageDetailsActivity.this.getPackageName());
        PrivatePackageDetailsActivity.this.sendBroadcast(paramAnonymousContext);
      }
      for (;;)
      {
        PrivatePackageDetailsActivity.this.getMyBalance();
        return;
        PrivatePackageDetailsActivity.this.managerPhoneNumber(false);
        break;
        label556:
        if ((PrivatePackageDetailsActivity.this.isRenew) && (PrivatePackageDetailsActivity.this.status == 0))
        {
          PrivatePackageDetailsActivity.this.updatePhoneGetTime();
          if ((!StrUtil.isNull(PrivatePackageDetailsActivity.this.planId)) && (PrivatePackageDetailsActivity.this.planId.equals("CM_TRIAL_CALLINGPLAN"))) {
            AppsFlyer.addEvent("freetopay_phonenumber");
          }
        }
      }
      label619:
      PrivatePackageDetailsActivity.this.dismiss();
    }
  };
  private int status = 1;
  private long tradeNo;
  
  public PrivatePackageDetailsActivity() {}
  
  private void UseAliPay()
  {
    show();
    this.payWay = 1;
    Jucore.getInstance().getClientInstance().GetNewProductReceipt(0L, 0, this.planId, OtherHelper.getRandomString(10), 1L, "CN", 0, Utils.getJsonAction(this.codeBean, this.isRenew));
    CMGAFlow.sendEventAndFlowEntry("Private Number", "click_buy_btn_alipay", this.planId, 0L);
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
      localIntent.putExtra("packageName", ((TextView)findViewById(2131230802)).getText().toString());
      localIntent.putExtra("orderAction", Utils.getJsonAction(this.codeBean, this.isRenew));
      localIntent.putExtra("tag", 3);
      localIntent.putExtra("is_renew", this.isRenew);
      localIntent.putExtra("code_bean", this.codeBean);
      localIntent.putExtra("receiver_user_id", "");
      startActivityForResult(localIntent, 3);
      CMGAFlow.sendEventAndFlowEntry("Private Number", "click_buy_btn_paypal", this.planId, 0L);
      return;
      localIntent.putExtra("price", this.price);
    }
  }
  
  private void UserCreditCard()
  {
    Intent localIntent = new Intent(this, BrainTreePurchaseActivity.class);
    localIntent.putExtra("planId", this.planId);
    localIntent.putExtra("from", "PrivatePackageDetailsActivity");
    if (Jucore.getInstance().getClientInstance().GetBuildType() == 1) {
      localIntent.putExtra("price", "0.01");
    }
    for (;;)
    {
      localIntent.putExtra("packageName", ((TextView)findViewById(2131230802)).getText().toString());
      localIntent.putExtra("orderAction", Utils.getJsonAction(this.codeBean, this.isRenew));
      localIntent.putExtra("tag", 3);
      localIntent.putExtra("is_renew", this.isRenew);
      localIntent.putExtra("code_bean", this.codeBean);
      startActivityForResult(localIntent, REQUEST_CODE_BRAINTREE);
      CMGAFlow.sendEventAndFlowEntry("Private Number", "click_buy_btn_creditcard", this.planId, 0L);
      return;
      localIntent.putExtra("price", this.price);
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
      localObject = getResources().getStringArray(2131623993);
      if (OtherHelper.isSimpleChineseLanguage(this))
      {
        UseAliPay();
        return;
      }
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
          if (PrivatePackageDetailsActivity.this.isFirst) {}
          do
          {
            int i;
            do
            {
              PrivatePackageDetailsActivity.access$2002(PrivatePackageDetailsActivity.this, false);
              do
              {
                return;
              } while (!OtherHelper.isSimpleChineseLanguage(PrivatePackageDetailsActivity.this));
              i = 0;
              Iterator localIterator = PrivatePackageDetailsActivity.this.getPackageManager().getInstalledPackages(0).iterator();
              while (localIterator.hasNext()) {
                if (((PackageInfo)localIterator.next()).applicationInfo.packageName.equals("com.alipay.android.app")) {
                  i = 1;
                }
              }
            } while (i != 0);
            PrivatePackageDetailsActivity.this.getMyBalance();
          } while (PrivatePackageDetailsActivity.this.isRenew);
          PrivatePackageDetailsActivity.this.getMyPhoneNumberList(0L, 0);
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
  
  private void getMyBalance()
  {
    try
    {
      Jucore.getInstance().getVirtualNumberInst().GetMyBalance(0L, 3);
      offLineDismiss();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CMTracer.e("PrivatePackageDetailsActivity", localException.getMessage());
        dismiss();
      }
    }
  }
  
  private void getMyPhoneNumberList(long paramLong, int paramInt)
  {
    try
    {
      Jucore.getInstance().getVirtualNumberInst().GetPrivateNumberList(paramLong, paramInt);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      CMTracer.e("PrivatePackageDetailsActivity", localException.getMessage());
    }
  }
  
  private void getOrderResult()
  {
    if (this.tradeNo == -1L) {
      return;
    }
    show();
    this.progressDialog.setCancelable(false);
    try
    {
      Jucore.getInstance().getClientInstance().QueryAlixpayReceipt(0L, 0, this.tradeNo);
      offLineDismiss();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        CMTracer.e("PrivatePackageDetailsActivity", localException.getMessage());
        dismiss();
      }
    }
  }
  
  private void initData()
  {
    this.isRenew = getIntent().getBooleanExtra("is_renew", false);
    this.mcb = new MyClientInstCallback(this);
    this.planId = getIntent().getStringExtra("planId");
    this.codeBean = ((CodeBean)getIntent().getParcelableExtra("code_bean"));
    this.codeBean.displayName = PrivateNumberTableOperation.getDisplayName();
    if (!PrivateNumberTableOperation.queryHasPrimaryFlag()) {}
    TextView localTextView1;
    TextView localTextView2;
    TextView localTextView3;
    TextView localTextView4;
    TextView localTextView5;
    TextView localTextView6;
    PhoneBean localPhoneBean;
    for (boolean bool = true;; bool = false)
    {
      this.primaryFlag = bool;
      ((TextView)findViewById(2131235216)).setText(getIntent().getStringExtra("phone_number_format"));
      ((TextView)findViewById(2131235244)).setText(getIntent().getIntExtra("title", -1));
      ((TextView)findViewById(2131235244)).getPaint().setFakeBoldText(true);
      ((TextView)findViewById(2131235246)).getPaint().setFakeBoldText(true);
      localTextView1 = (TextView)findViewById(2131235221);
      localTextView2 = (TextView)findViewById(2131235226);
      localTextView3 = (TextView)findViewById(2131235231);
      localTextView4 = (TextView)findViewById(2131235219);
      localTextView5 = (TextView)findViewById(2131235224);
      localTextView6 = (TextView)findViewById(2131235229);
      this.mUltimateIcon = ((ImageView)findViewById(2131235243));
      if (!this.isRenew) {
        break label1010;
      }
      localObject = getIntent().getStringExtra("phone_number");
      localPhoneBean = PrivateNumberTableOperation.queryPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), (String)localObject);
      if (localPhoneBean != null) {
        break;
      }
      finish();
      return;
    }
    if (StrUtil.isNull(localPhoneBean.phoneNumber))
    {
      finish();
      return;
    }
    Callplan localCallplan = PrivateNumberTableOperation.queryCallPlan(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localPhoneBean.phoneNumber);
    if (localCallplan == null)
    {
      finish();
      return;
    }
    Object localObject = new String[1];
    int i1;
    int n;
    int i2;
    int j;
    int m;
    int i;
    int k;
    if ("CM_AND_IAP_CALLINGPLAN_01".equals(this.planId))
    {
      localObject = getString(2131166859).split(",");
      i1 = Integer.parseInt(localObject[1]);
      n = Integer.parseInt(localObject[2]);
      i2 = Integer.parseInt(localObject[3]);
      j = localCallplan.maxTotalMinutes - localCallplan.usedMinutesIn - localCallplan.usedMinutesOut;
      m = localCallplan.maxTotalTexts - localCallplan.usedTextsIn - localCallplan.usedTextsOut;
      i = DateUtil.getDays(localPhoneBean.expireTime);
      CMTracer.i("PrivatePackageDetailsActivity", localPhoneBean.phoneNumber + ", remainMins:" + j + ", remainSmss, " + m + ", remainDays:" + i);
      k = i;
      if (i < 0) {
        k = 0;
      }
      i = j;
      if (j < 0) {
        i = 0;
      }
      j = m;
      if (m < 0) {
        j = 0;
      }
      i2 = Math.max(i2, k);
      if (i > 0) {
        break label955;
      }
      m = 0;
      label546:
      localTextView3.setText(String.valueOf(m));
      if (j > 0) {
        break label961;
      }
      m = 0;
      label563:
      localTextView2.setText(String.valueOf(m));
      localTextView1.setText(String.valueOf(k));
      if (k > 0) {
        break label967;
      }
      localTextView6.setText(String.valueOf(i1));
      localTextView5.setText(String.valueOf(n));
      localCallplan.maxTotalMinutes = 0;
      localCallplan.maxTotalTexts = 0;
    }
    for (;;)
    {
      localTextView4.setText(getString(2131167000, new Object[] { i2 + "" }));
      if ("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId))
      {
        localTextView5.setText(2131167033);
        this.mUltimateIcon.setVisibility(0);
      }
      ((TextView)findViewById(2131235247)).setText(2131166914);
      localTextView4.setTextColor(getResources().getColor(2131361969));
      localTextView5.setTextColor(getResources().getColor(2131361969));
      localTextView6.setTextColor(getResources().getColor(2131361969));
      this.price = getIntent().getStringExtra("price");
      if (!StrUtil.isNull(this.price)) {
        break label1231;
      }
      finish();
      return;
      if ("CM_AND_IAP_NEW_CALLINGPLAN_01".equals(this.planId))
      {
        localObject = getString(2131166861).split(",");
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_03".equals(this.planId))
      {
        localObject = getString(2131166862).split(",");
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_06".equals(this.planId))
      {
        localObject = getString(2131166860).split(",");
        break;
      }
      if ("CM_AND_IAP_NEW_CALLINGPLAN_06".equals(this.planId))
      {
        localObject = getString(2131166863).split(",");
        break;
      }
      if ("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId))
      {
        localObject = getString(2131166864).split(",");
        break;
      }
      localObject = getString(2131166861).split(",");
      this.planId = "CM_AND_IAP_NEW_CALLINGPLAN_01";
      break;
      label955:
      m = i;
      break label546;
      label961:
      m = j;
      break label563;
      label967:
      k = i;
      if (i <= 0) {
        k = 0;
      }
      localTextView6.setText(String.valueOf(i1 + k));
      i = j;
      if (j <= 0) {
        i = 0;
      }
      localTextView5.setText(String.valueOf(n + i));
    }
    label1010:
    localTextView1.setVisibility(8);
    ((TextView)findViewById(2131235220)).setVisibility(8);
    localTextView2.setVisibility(8);
    ((TextView)findViewById(2131235225)).setVisibility(8);
    localTextView3.setVisibility(8);
    ((TextView)findViewById(2131235230)).setVisibility(8);
    localTextView4.setTextColor(getResources().getColor(2131361969));
    localTextView5.setTextColor(getResources().getColor(2131361969));
    localTextView6.setTextColor(getResources().getColor(2131361969));
    localTextView4.setText(getString(2131167000, new Object[] { getIntent().getStringExtra("valid") }));
    localTextView6.setText(getIntent().getStringExtra("tel_time"));
    if ("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId))
    {
      localTextView5.setText(2131167033);
      this.mUltimateIcon.setVisibility(0);
    }
    for (;;)
    {
      ((TextView)findViewById(2131235247)).setText(2131166911);
      break;
      localTextView5.setText(getIntent().getStringExtra("sms_count"));
    }
    label1231:
    ((Button)findViewById(2131235106)).setText(getString(2131166912, new Object[] { this.price }));
  }
  
  private void initListener()
  {
    this.mHandler = Utils.buyPhoneNumber(String.valueOf(KexinData.getInstance(this).getCurrentAuthorityId()), this, this, this.codeBean, this.isRenew, "");
    this.mHandler.setTag(3);
    findViewById(2131235106).setOnClickListener(this);
    IntentFilter localIntentFilter = new IntentFilter("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS");
    localIntentFilter.addAction("ws.coverme.im.model.constant.SET_PRIVATE_NUMBER_PARAM");
    localIntentFilter.addAction("ws.coverme.im.model.constant.RETURN_FOR_PAYPAL");
    localIntentFilter.addAction("ws.coverme.im.model.constant.GET_ORDER_RESULT");
    localIntentFilter.addAction("ws.coverme.im.model.constant.WAIT_ALIPAY");
    localIntentFilter.addAction("android.intent.action.LOCALE_CHANGED");
    registerBroadcastReceiver(this.receiver, localIntentFilter);
  }
  
  private void initView()
  {
    this.progressDialog = new CMProgressDialog(this);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setCancelable(false);
  }
  
  private void insertCallplan()
  {
    if (PrivateNumberTableOperation.updateCallPlan(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"), this.planId)) {
      return;
    }
    Callplan localCallplan = new Callplan();
    localCallplan.phoneNum = getIntent().getStringExtra("phone_number");
    String[] arrayOfString = new String[1];
    if ("CM_AND_IAP_CALLINGPLAN_01".equals(this.planId))
    {
      arrayOfString = getString(2131166859).split(",");
      localCallplan.planName = getString(2131166847);
    }
    for (;;)
    {
      localCallplan.productId = this.planId;
      localCallplan.maxTotalMinutes = Integer.parseInt(arrayOfString[1]);
      localCallplan.maxTotalTexts = Integer.parseInt(arrayOfString[2]);
      localCallplan.expiration = Integer.parseInt(arrayOfString[3]);
      localCallplan.endTime = (System.currentTimeMillis() / 1000L + Integer.parseInt(arrayOfString[3]) * 24 * 60 * 60);
      PrivateNumberTableOperation.insertCallPlan(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localCallplan);
      return;
      if ("CM_AND_IAP_NEW_CALLINGPLAN_01".equals(this.planId))
      {
        arrayOfString = getString(2131166861).split(",");
        localCallplan.planName = getString(2131166847);
      }
      else if ("CM_AND_IAP_CALLINGPLAN_03".equals(this.planId))
      {
        arrayOfString = getString(2131166862).split(",");
        localCallplan.planName = getString(2131166846);
      }
      else if ("CM_AND_IAP_CALLINGPLAN_06".equals(this.planId))
      {
        arrayOfString = getString(2131166860).split(",");
        localCallplan.planName = getString(2131166848);
      }
      else if ("CM_AND_IAP_NEW_CALLINGPLAN_06".equals(this.planId))
      {
        arrayOfString = getString(2131166863).split(",");
        localCallplan.planName = getString(2131166849);
      }
      else if ("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId))
      {
        arrayOfString = getString(2131166864).split(",");
        localCallplan.planName = getString(2131166850);
      }
      else
      {
        arrayOfString = getString(2131166861).split(",");
        localCallplan.planName = getString(2131166847);
      }
    }
  }
  
  private void insertPhone()
  {
    PhoneBean localPhoneBean = new PhoneBean();
    localPhoneBean.countryCode = this.codeBean.countryCode;
    localPhoneBean.areaCode = this.codeBean.areaCode;
    localPhoneBean.phoneNumber = this.codeBean.phoneNumber;
    localPhoneBean.payType = 1;
    localPhoneBean.primaryFlag = this.codeBean.primaryFlag;
    localPhoneBean.displayName = this.codeBean.displayName;
    localPhoneBean.status = this.status;
    localPhoneBean.tradeNo = this.tradeNo;
    localPhoneBean.couponId = this.couponId;
    localPhoneBean.productId = this.productId;
    localPhoneBean.paymentId = this.paymentId;
    localPhoneBean.payWay = this.payWay;
    localPhoneBean.provision = 15;
    localPhoneBean.callPlanId = this.callPlanId;
    localPhoneBean.isPrettyNumber = this.codeBean.isPrettyNumber;
    localPhoneBean.getNumberTime = System.currentTimeMillis();
    localPhoneBean.readFlag = 0;
    localPhoneBean.providerId = this.codeBean.providerId;
    if ((this.codeBean.isoCountryName != null) && (this.codeBean.isoCountryName.equals("CA") == true)) {}
    for (localPhoneBean.packageServiceId = "CM00001";; localPhoneBean.packageServiceId = "")
    {
      PrivateNumberTableOperation.insertPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localPhoneBean);
      CMTracer.i("insertPhone", "packetDetail:" + localPhoneBean.phoneNumber + "primaryFlag:" + this.codeBean.primaryFlag + ", success:" + this.status + ", tradeNo:" + this.tradeNo + ",productId:" + this.productId + ", paymentId:" + this.paymentId);
      return;
    }
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
  
  private void managerPhoneNumber(boolean paramBoolean)
  {
    if (paramBoolean) {
      this.codeBean.primaryFlag = this.primaryFlag;
    }
    Object localObject = new PhoneBean();
    ((PhoneBean)localObject).countryCode = this.codeBean.countryCode;
    ((PhoneBean)localObject).areaCode = this.codeBean.areaCode;
    ((PhoneBean)localObject).phoneNumber = this.codeBean.phoneNumber;
    ((PhoneBean)localObject).payType = 1;
    ((PhoneBean)localObject).primaryFlag = this.codeBean.primaryFlag;
    ((PhoneBean)localObject).displayName = this.codeBean.displayName;
    ((PhoneBean)localObject).status = this.status;
    ((PhoneBean)localObject).tradeNo = this.tradeNo;
    ((PhoneBean)localObject).productId = this.productId;
    ((PhoneBean)localObject).paymentId = this.paymentId;
    ((PhoneBean)localObject).payWay = this.payWay;
    ((PhoneBean)localObject).provision = 15;
    ((PhoneBean)localObject).isPrettyNumber = this.codeBean.isPrettyNumber;
    ((PhoneBean)localObject).getNumberTime = System.currentTimeMillis();
    ((PhoneBean)localObject).callPlanId = this.callPlanId;
    ((PhoneBean)localObject).readFlag = 0;
    ((PhoneBean)localObject).providerId = this.codeBean.providerId;
    if ((this.codeBean.isoCountryName != null) && (this.codeBean.isoCountryName.equals("CA") == true)) {}
    for (((PhoneBean)localObject).packageServiceId = "CM00001";; ((PhoneBean)localObject).packageServiceId = "")
    {
      if (this.status != 5) {
        PrivateNumberTableOperation.insertPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), (PhoneBean)localObject);
      }
      CMTracer.i("PrivatePackageDetailsActivity", String.valueOf(KexinData.getInstance().getCurrentAuthorityId()) + "====11status:" + this.status + ", phone:" + ((PhoneBean)localObject).phoneNumber);
      insertCallplan();
      CMTracer.i("PrivatePackageDetailsActivity", "phoneNumber:" + ((PhoneBean)localObject).phoneNumber + ", primaryFlag:" + ((PhoneBean)localObject).primaryFlag);
      if ((((PhoneBean)localObject).primaryFlag) && (this.status == 0)) {
        KexinData.getInstance().calculateime(((PhoneBean)localObject).phoneNumber, ((PhoneBean)localObject).provision);
      }
      localObject = new Intent(this, PrivateIntroductionActivity.class);
      if (this.status == 0)
      {
        ((Intent)localObject).putExtra("phone_number", getIntent().getStringExtra("phone_number"));
        ((Intent)localObject).putExtra("go_to", 1);
      }
      startActivity((Intent)localObject);
      finish();
      if (this.status != 5)
      {
        SecretaryLocalManager.sendPrivateNumberPurchaseSuccessMsg(this);
        PrivateNumberReadyUtils.setSecretaryReady(this);
        SharedPreferencesManager.setSharedIntPreferences(SharedPreferencesManager.PRIVATE_NUMBER_HELP_BUY_TIME, (int)System.currentTimeMillis() / 1000, this);
        CMTracer.d("elleray", "set PRIVATE_NUMBER_HELP_ALREADY_NOTICE false");
        SharedPreferencesManager.setSharedBooleanPreferences(SharedPreferencesManager.PRIVATE_NUMBER_HELP_ALREADY_NOTICE, false, this);
      }
      dismiss();
      return;
    }
  }
  
  private void renew()
  {
    if (this.status != 0)
    {
      localObject = PrivateNumberTableOperation.queryPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"));
      if ((localObject != null) && (((PhoneBean)localObject).status == 4)) {
        PrivateNumberTableOperation.updatePhoneNumberToReapply(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), getIntent().getStringExtra("phone_number"));
      }
    }
    dismiss();
    Object localObject = new Intent(this, PrivateSelectPackageActivity.class);
    ((Intent)localObject).putExtra("go_to", 1);
    setResult(-1, (Intent)localObject);
    finish();
  }
  
  private void reselectPhoneNumber(String paramString)
  {
    Intent localIntent = new Intent(this, PrivateInterimActivity.class);
    localIntent.putExtra("coupon", paramString);
    localIntent.putExtra("callPlanId", this.callPlanId);
    localIntent.putExtra("phone_number", getIntent().getStringExtra("phone_number"));
    if ((this.codeBean.isPrettyNumber) || ("CM_AND_IAP_CALLINGPLAN_06".equals(this.planId)) || ("CM_AND_IAP_NEW_CALLINGPLAN_06".equals(this.planId)) || ("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId))) {}
    for (boolean bool = true;; bool = false)
    {
      localIntent.putExtra("is_pretty_number", bool);
      startActivity(localIntent);
      finish();
      dismiss();
      return;
    }
  }
  
  private void returnPhoneNumber()
  {
    startActivity(new Intent(this, PrivateInterimActivity.class));
    finish();
    dismiss();
  }
  
  private void setPhoneAttrs()
  {
    PrivateNumberSettingParam localPrivateNumberSettingParam = new PrivateNumberSettingParam();
    localPrivateNumberSettingParam.phoneNumber = getIntent().getStringExtra("phone_number");
    localPrivateNumberSettingParam.displayName = this.codeBean.displayName;
    int i;
    if (this.primaryFlag) {
      i = 1;
    }
    for (;;)
    {
      localPrivateNumberSettingParam.primaryFlag = i;
      try
      {
        Jucore.getInstance().getVirtualNumberInst().PrivateNumberSetting(0L, 3, localPrivateNumberSettingParam);
        offLineDismiss();
        return;
        i = 0;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          CMTracer.e("PrivatePackageDetailsActivity", localException.getMessage());
          dismiss();
        }
      }
    }
  }
  
  private void updatePhoneGetTime()
  {
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
    Object localObject = new StringBuilder().append("onActivityResult, resultCode:").append(paramInt2).append("data == null:");
    if (paramIntent == null) {}
    for (boolean bool = true;; bool = false)
    {
      CMTracer.i("PrivatePackageDetailsActivity", bool);
      if ((paramInt2 != -1) || (!paramIntent.getBooleanExtra("is_paypal", false))) {
        dismiss();
      }
      if ((REQUEST_CODE_BRAINTREE == paramInt1) && (paramInt2 == -1) && (paramIntent != null))
      {
        show();
        if ((!StrUtil.isNull(this.planId)) && (this.planId.equals("CM_TRIAL_CALLINGPLAN"))) {
          AppsFlyer.addEvent("freetopay_phonenumber");
        }
        localObject = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
        ((Intent)localObject).setPackage(getPackageName());
        sendBroadcast((Intent)localObject);
        this.status = paramIntent.getIntExtra("orderStatus", 0);
        this.isBrainTree = paramIntent.getBooleanExtra("is_braintree", false);
        getMyBalance();
      }
      return;
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    }
    do
    {
      do
      {
        return;
      } while (ClickTimeSpanUtil.isFastDoubleClick());
      finish();
      return;
    } while (ClickTimeSpanUtil.isFastDoubleClick(3000L, 2131235106));
    CMTracer.i("PrivatePackageDetailsActivity", "pay btn clicked!");
    buy();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903569);
    initView();
    initData();
    initListener();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    KexinData.getInstance().unLockInActivity = false;
    KexinData.getInstance().doNotKillApp = false;
    unregisterBroadcastReceiver(this.receiver);
  }
  
  protected void onPause()
  {
    super.onPause();
    ClickTimeSpanUtil.resetlastClickBtnId();
  }
  
  protected void onResume()
  {
    super.onResume();
    checkAliPay();
  }
  
  protected void onStart()
  {
    super.onStart();
    CMGAFlow.sendEventAndFlowEntry("Private Number", "PrivatePackageDetailsActivity", null, 0L);
  }
}
