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
import ws.coverme.im.model.KexinData;
import ws.coverme.im.privatenumber.bean.CodeBean;
import ws.coverme.im.privatenumber.bean.PhoneBean;
import ws.coverme.im.ui.chat.secretary.SecretaryLocalManager;
import ws.coverme.im.ui.chat.virtualnumber.PhoneNumberFormatUtil;
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
import ws.coverme.im.util.Utils;

public class PrivateMultiCountryPackageDetailsActivity
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
  private String mSMSCount;
  private String mTeltime;
  private ImageView mUltimateIcon;
  private String mValit;
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
        PrivateMultiCountryPackageDetailsActivity.this.UserCreditCard();
        return;
      }
      PrivateMultiCountryPackageDetailsActivity.this.UsePayPal();
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
            } while (!PrivateMultiCountryPackageDetailsActivity.this.isVersionValid());
            paramAnonymousContext = paramAnonymousIntent.getAction();
            if ("ws.coverme.im.model.constant.WAIT_ALIPAY".equals(paramAnonymousContext))
            {
              PrivateMultiCountryPackageDetailsActivity.this.dismiss();
              return;
            }
            if ("android.intent.action.LOCALE_CHANGED".equals(paramAnonymousContext))
            {
              PrivateMultiCountryPackageDetailsActivity.this.finish();
              return;
            }
          } while (paramAnonymousIntent.getIntExtra("command_tag", 0) != 3);
          if ("ws.coverme.im.model.constant.GET_PRIVATE_NUMBER_PACKAGE_DETAILS".equals(paramAnonymousContext))
          {
            if (PrivateMultiCountryPackageDetailsActivity.this.isRenew)
            {
              PrivateMultiCountryPackageDetailsActivity.this.renew();
              return;
            }
            PrivateMultiCountryPackageDetailsActivity.this.setPhoneAttrs();
            return;
          }
          if (!"ws.coverme.im.model.constant.RETURN_FOR_PAYPAL".equals(paramAnonymousContext)) {
            break;
          }
        } while (!PrivateMultiCountryPackageDetailsActivity.this.getIntent().getStringExtra("phone_number").equals(paramAnonymousIntent.getStringExtra("phone_number")));
        PrivateMultiCountryPackageDetailsActivity.this.returnPhoneNumber();
        return;
        if ("ws.coverme.im.model.constant.SET_PRIVATE_NUMBER_PARAM".equals(paramAnonymousContext))
        {
          PrivateMultiCountryPackageDetailsActivity.this.managerPhoneNumber(paramAnonymousIntent.getBooleanExtra("param_staus", false));
          return;
        }
      } while (!"ws.coverme.im.model.constant.GET_ORDER_RESULT".equals(paramAnonymousContext));
      PrivateMultiCountryPackageDetailsActivity.access$502(PrivateMultiCountryPackageDetailsActivity.this, paramAnonymousIntent.getStringExtra("coupon"));
      PrivateMultiCountryPackageDetailsActivity.access$602(PrivateMultiCountryPackageDetailsActivity.this, paramAnonymousIntent.getStringExtra("productId"));
      PrivateMultiCountryPackageDetailsActivity.access$702(PrivateMultiCountryPackageDetailsActivity.this, paramAnonymousIntent.getStringExtra("paymentId"));
      PrivateMultiCountryPackageDetailsActivity.access$802(PrivateMultiCountryPackageDetailsActivity.this, paramAnonymousIntent.getIntExtra("callPlanId", 0));
      PrivateMultiCountryPackageDetailsActivity.access$902(PrivateMultiCountryPackageDetailsActivity.this, paramAnonymousIntent.getLongExtra("orderNO", -1L));
      CMTracer.i("PrivatePackageDetailsActivity", "EXTRA_PHONE_NUMBER : " + PrivateMultiCountryPackageDetailsActivity.this.getIntent().getStringExtra("phone_number") + ", coupon : " + PrivateMultiCountryPackageDetailsActivity.this.couponId);
      if (!StrUtil.isNull(PrivateMultiCountryPackageDetailsActivity.this.couponId))
      {
        PrivateMultiCountryPackageDetailsActivity.access$1002(PrivateMultiCountryPackageDetailsActivity.this, 2);
        PrivateMultiCountryPackageDetailsActivity.this.insertCallplan();
        PrivateMultiCountryPackageDetailsActivity.this.insertPhone();
        PrivateMultiCountryPackageDetailsActivity.this.reselectPhoneNumber(String.valueOf(PrivateMultiCountryPackageDetailsActivity.this.couponId));
        return;
      }
      PrivateMultiCountryPackageDetailsActivity.access$1002(PrivateMultiCountryPackageDetailsActivity.this, paramAnonymousIntent.getIntExtra("orderStatus", -1));
      if (PrivateMultiCountryPackageDetailsActivity.this.status == 5)
      {
        if (PrivateMultiCountryPackageDetailsActivity.this.isRenew) {
          PrivateMultiCountryPackageDetailsActivity.this.renew();
        }
      }
      else
      {
        CMTracer.i("PrivatePackageDetailsActivity", "test_errorCode : " + paramAnonymousIntent.getBooleanExtra("result", false) + " = isRenew:" + PrivateMultiCountryPackageDetailsActivity.this.isRenew);
        if (!paramAnonymousIntent.getBooleanExtra("result", false)) {
          break label612;
        }
        if (!PrivateMultiCountryPackageDetailsActivity.this.isRenew) {
          PrivateMultiCountryPackageDetailsActivity.this.insertPhone();
        }
        if (PrivateMultiCountryPackageDetailsActivity.this.status != 1) {
          break label549;
        }
        if (PrivateMultiCountryPackageDetailsActivity.this.isRenew) {
          PrivateMultiCountryPackageDetailsActivity.this.insertTradeNo();
        }
        paramAnonymousContext = new Intent("ws.coverme.im.model.constant.REFRESH_PHONE_NUMBER");
        paramAnonymousContext.setPackage(PrivateMultiCountryPackageDetailsActivity.this.getPackageName());
        PrivateMultiCountryPackageDetailsActivity.this.sendBroadcast(paramAnonymousContext);
      }
      for (;;)
      {
        PrivateMultiCountryPackageDetailsActivity.this.getMyBalance();
        return;
        PrivateMultiCountryPackageDetailsActivity.this.managerPhoneNumber(false);
        break;
        label549:
        if ((PrivateMultiCountryPackageDetailsActivity.this.isRenew) && (PrivateMultiCountryPackageDetailsActivity.this.status == 0))
        {
          PrivateMultiCountryPackageDetailsActivity.this.updatePhoneGetTime();
          if ((!StrUtil.isNull(PrivateMultiCountryPackageDetailsActivity.this.planId)) && (PrivateMultiCountryPackageDetailsActivity.this.planId.equals("CM_TRIAL_CALLINGPLAN"))) {
            AppsFlyer.addEvent("freetopay_phonenumber");
          }
        }
      }
      label612:
      PrivateMultiCountryPackageDetailsActivity.this.dismiss();
    }
  };
  private int status = 1;
  private long tradeNo;
  
  public PrivateMultiCountryPackageDetailsActivity() {}
  
  private void UseAliPay()
  {
    show();
    this.payWay = 1;
    String str = PhoneNumberUtil.countryCode2CountryShort(this.codeBean.countryCode);
    CMTracer.d("elleray", "alipay countryshort: " + str);
    Jucore.getInstance().getClientInstance().GetNewProductReceipt(0L, 0, this.planId, OtherHelper.getRandomString(10), 1L, str, 0, Utils.getMultiCountryJsonAction(this.codeBean, this.isRenew));
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
      localIntent.putExtra("tel_time", this.mTeltime);
      localIntent.putExtra("sms_count", this.mSMSCount);
      localIntent.putExtra("valid", this.mValit);
      startActivityForResult(localIntent, REQUEST_CODE_BRAINTREE);
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
          if (PrivateMultiCountryPackageDetailsActivity.this.isFirst) {}
          do
          {
            int i;
            do
            {
              PrivateMultiCountryPackageDetailsActivity.access$2002(PrivateMultiCountryPackageDetailsActivity.this, false);
              do
              {
                return;
              } while (!OtherHelper.isSimpleChineseLanguage(PrivateMultiCountryPackageDetailsActivity.this));
              i = 0;
              Iterator localIterator = PrivateMultiCountryPackageDetailsActivity.this.getPackageManager().getInstalledPackages(0).iterator();
              while (localIterator.hasNext()) {
                if (((PackageInfo)localIterator.next()).applicationInfo.packageName.equals("com.alipay.android.app")) {
                  i = 1;
                }
              }
            } while (i != 0);
            PrivateMultiCountryPackageDetailsActivity.this.getMyBalance();
          } while (PrivateMultiCountryPackageDetailsActivity.this.isRenew);
          PrivateMultiCountryPackageDetailsActivity.this.getMyPhoneNumberList(0L, 0);
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
    this.mSMSCount = getIntent().getStringExtra("sms_count");
    this.mTeltime = getIntent().getStringExtra("tel_time");
    this.mValit = getIntent().getStringExtra("valid");
    this.codeBean = ((CodeBean)getIntent().getParcelableExtra("code_bean"));
    this.codeBean.displayName = PrivateNumberTableOperation.getDisplayName();
    if (!PrivateNumberTableOperation.queryHasPrimaryFlag()) {}
    TextView localTextView1;
    TextView localTextView2;
    TextView localTextView3;
    TextView localTextView4;
    TextView localTextView5;
    Object localObject2;
    for (boolean bool = true;; bool = false)
    {
      this.primaryFlag = bool;
      ((TextView)findViewById(2131235216)).setText(getIntent().getStringExtra("phone_number_format"));
      ((TextView)findViewById(2131235244)).setText(getIntent().getIntExtra("title", -1));
      ((TextView)findViewById(2131235244)).getPaint().setFakeBoldText(true);
      ((TextView)findViewById(2131235246)).getPaint().setFakeBoldText(true);
      localObject1 = (TextView)findViewById(2131235221);
      localTextView1 = (TextView)findViewById(2131235226);
      localTextView2 = (TextView)findViewById(2131235231);
      localTextView3 = (TextView)findViewById(2131235219);
      localTextView4 = (TextView)findViewById(2131235224);
      localTextView5 = (TextView)findViewById(2131235229);
      this.mUltimateIcon = ((ImageView)findViewById(2131235243));
      if (!this.isRenew) {
        break label884;
      }
      localObject2 = getIntent().getStringExtra("phone_number");
      localObject2 = PrivateNumberTableOperation.queryPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), (String)localObject2);
      if (localObject2 != null) {
        break;
      }
      finish();
      return;
    }
    if (StrUtil.isNull(((PhoneBean)localObject2).phoneNumber))
    {
      finish();
      return;
    }
    Callplan localCallplan = PrivateNumberTableOperation.queryCallPlan(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), ((PhoneBean)localObject2).phoneNumber);
    if (localCallplan == null)
    {
      finish();
      return;
    }
    CMTracer.i("PrivatePackageDetailsActivity", "elleray note : " + PrivateMultiCountrySelectPackageActivity.class.getName() + "renew");
    int i1 = Integer.parseInt(this.mTeltime);
    int n = Integer.parseInt(this.mSMSCount);
    int i2 = Integer.parseInt(this.mValit);
    int j = localCallplan.maxTotalMinutes - localCallplan.usedMinutesIn - localCallplan.usedMinutesOut;
    int m = localCallplan.maxTotalTexts - localCallplan.usedTextsIn - localCallplan.usedTextsOut;
    int i = DateUtil.getDays(((PhoneBean)localObject2).expireTime);
    CMTracer.i("PrivatePackageDetailsActivity", ((PhoneBean)localObject2).phoneNumber + ", remainMins:" + j + ", remainSmss, " + m + ", remainDays:" + i);
    int k = i;
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
    if (i <= 0)
    {
      m = 0;
      localTextView2.setText(String.valueOf(m));
      if (j > 0) {
        break label835;
      }
      m = 0;
      label604:
      localTextView1.setText(String.valueOf(m));
      ((TextView)localObject1).setText(String.valueOf(k));
      if (k > 0) {
        break label841;
      }
      localTextView5.setText(String.valueOf(i1));
      localTextView4.setText(String.valueOf(n));
      localCallplan.maxTotalMinutes = 0;
      localCallplan.maxTotalTexts = 0;
    }
    for (;;)
    {
      localTextView3.setText(getString(2131167000, new Object[] { i2 + "" }));
      if (("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId)) || ("CM_AND_NEWCALLINGPLAN_01".equals(this.planId)))
      {
        localTextView4.setText(2131167033);
        this.mUltimateIcon.setVisibility(0);
      }
      ((TextView)findViewById(2131235247)).setText(2131166914);
      localTextView3.setTextColor(getResources().getColor(2131361969));
      localTextView4.setTextColor(getResources().getColor(2131361969));
      localTextView5.setTextColor(getResources().getColor(2131361969));
      this.price = getIntent().getStringExtra("price");
      if (!StrUtil.isNull(this.price)) {
        break label1163;
      }
      finish();
      return;
      m = i;
      break;
      label835:
      m = j;
      break label604;
      label841:
      k = i;
      if (i <= 0) {
        k = 0;
      }
      localTextView5.setText(String.valueOf(i1 + k));
      i = j;
      if (j <= 0) {
        i = 0;
      }
      localTextView4.setText(String.valueOf(n + i));
    }
    label884:
    ((TextView)localObject1).setVisibility(8);
    ((TextView)findViewById(2131235220)).setVisibility(8);
    localTextView1.setVisibility(8);
    ((TextView)findViewById(2131235225)).setVisibility(8);
    localTextView2.setVisibility(8);
    ((TextView)findViewById(2131235230)).setVisibility(8);
    localTextView3.setTextColor(getResources().getColor(2131361969));
    localTextView4.setTextColor(getResources().getColor(2131361969));
    localTextView5.setTextColor(getResources().getColor(2131361969));
    localTextView3.setText(getString(2131167000, new Object[] { getIntent().getStringExtra("valid") }));
    localTextView5.setText(this.mTeltime);
    if (("CM_AND_IAP_CALLINGPLAN_11".equals(this.planId)) || ("CM_AND_NEWCALLINGPLAN_01".equals(this.planId)))
    {
      localTextView4.setText(2131167033);
      this.mUltimateIcon.setVisibility(0);
      label1074:
      localTextView1 = (TextView)findViewById(2131235247);
      if (!this.codeBean.isMultiCountryNum()) {
        break label1151;
      }
    }
    label1151:
    for (Object localObject1 = getResources().getString(2131168262, new Object[] { getString(PhoneNumberFormatUtil.countryCode2Country(this.codeBean.countryCode)) });; localObject1 = getString(2131166911))
    {
      localTextView1.setText((CharSequence)localObject1);
      break;
      localTextView4.setText(this.mSMSCount);
      break label1074;
    }
    label1163:
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
    if ("CM_AND_IAP_CALLINGPLAN_01".equals(this.planId)) {
      localCallplan.planName = getString(2131166847);
    }
    for (;;)
    {
      localCallplan.productId = this.planId;
      localCallplan.maxTotalMinutes = Integer.parseInt(this.mTeltime);
      localCallplan.maxTotalTexts = Integer.parseInt(this.mSMSCount);
      localCallplan.expiration = Integer.parseInt(this.mValit);
      localCallplan.endTime = (System.currentTimeMillis() / 1000L + Integer.parseInt(this.mValit) * 24 * 60 * 60);
      PrivateNumberTableOperation.insertCallPlan(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localCallplan);
      return;
      if ("CM_AND_IAP_NEW_CALLINGPLAN_01".equals(this.planId)) {
        localCallplan.planName = getString(2131166847);
      } else if ("CM_AND_NEWCALLINGPLAN_04".equals(this.planId)) {
        localCallplan.planName = getString(2131166847);
      } else if ("CM_AND_NEWCALLINGPLAN_03".equals(this.planId)) {
        localCallplan.planName = getString(2131166848);
      } else if ("CM_AND_NEWCALLINGPLAN_02".equals(this.planId)) {
        localCallplan.planName = getString(2131166849);
      } else if ("CM_AND_NEWCALLINGPLAN_01".equals(this.planId)) {
        localCallplan.planName = getString(2131166850);
      } else {
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
    if ((this.codeBean.isoCountryName != null) && (this.codeBean.isoCountryName.equals("CA") == true))
    {
      localPhoneBean.packageServiceId = "CM00001";
      PrivateNumberTableOperation.insertPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localPhoneBean);
      CMTracer.i("insertPhone", "packetDetail:" + localPhoneBean.phoneNumber + "primaryFlag:" + this.codeBean.primaryFlag + ", success:" + this.status + ", tradeNo:" + this.tradeNo + ",productId:" + this.productId + ", paymentId:" + this.paymentId);
      return;
    }
    if (this.codeBean.packageServiceId != null) {}
    for (String str = this.codeBean.packageServiceId;; str = "")
    {
      localPhoneBean.packageServiceId = str;
      break;
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
    PhoneBean localPhoneBean = new PhoneBean();
    localPhoneBean.countryCode = this.codeBean.countryCode;
    localPhoneBean.areaCode = this.codeBean.areaCode;
    localPhoneBean.phoneNumber = this.codeBean.phoneNumber;
    localPhoneBean.payType = 1;
    localPhoneBean.primaryFlag = this.codeBean.primaryFlag;
    localPhoneBean.displayName = this.codeBean.displayName;
    localPhoneBean.status = this.status;
    localPhoneBean.tradeNo = this.tradeNo;
    localPhoneBean.productId = this.productId;
    localPhoneBean.paymentId = this.paymentId;
    localPhoneBean.payWay = this.payWay;
    localPhoneBean.provision = 15;
    localPhoneBean.isPrettyNumber = this.codeBean.isPrettyNumber;
    localPhoneBean.getNumberTime = System.currentTimeMillis();
    localPhoneBean.callPlanId = this.callPlanId;
    localPhoneBean.readFlag = 0;
    localPhoneBean.providerId = this.codeBean.providerId;
    if ((this.codeBean.isoCountryName != null) && (this.codeBean.isoCountryName.equals("CA") == true))
    {
      localPhoneBean.packageServiceId = "CM00001";
      if (this.status != 5) {
        PrivateNumberTableOperation.insertPhoneNumber(String.valueOf(KexinData.getInstance().getCurrentAuthorityId()), localPhoneBean);
      }
      CMTracer.i("PrivatePackageDetailsActivity", String.valueOf(KexinData.getInstance().getCurrentAuthorityId()) + "====11status:" + this.status + ", phone:" + localPhoneBean.phoneNumber);
      insertCallplan();
      CMTracer.i("PrivatePackageDetailsActivity", "phoneNumber:" + localPhoneBean.phoneNumber + ", primaryFlag:" + localPhoneBean.primaryFlag);
      if ((localPhoneBean.primaryFlag) && (this.status == 0)) {
        KexinData.getInstance().calculateime(localPhoneBean.phoneNumber, localPhoneBean.provision);
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
        SharedPreferencesManager.setSharedBooleanPreferences(SharedPreferencesManager.PRIVATE_NUMBER_HELP_ALREADY_NOTICE, false, this);
      }
      dismiss();
      return;
    }
    if (this.codeBean.packageServiceId != null) {}
    for (Object localObject = this.codeBean.packageServiceId;; localObject = "")
    {
      localPhoneBean.packageServiceId = ((String)localObject);
      break;
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
    requestWindowFeature(1);
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
}
