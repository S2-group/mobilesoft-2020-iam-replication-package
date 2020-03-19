package com.ilike.cartoon.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.ad;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.b;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ilike.cartoon.adapter.RechargeAdapter;
import com.ilike.cartoon.adapter.RechargeAdapter.ITEM_TYPE;
import com.ilike.cartoon.adapter.RechargeAdapter.f;
import com.ilike.cartoon.base.BaseActivity;
import com.ilike.cartoon.base.ManhuarenApplication;
import com.ilike.cartoon.bean.AmountBean;
import com.ilike.cartoon.bean.GetRechargeInfoBean;
import com.ilike.cartoon.bean.GetUserInfoBean;
import com.ilike.cartoon.bean.OwntradeBean;
import com.ilike.cartoon.bean.RechargeInfoBean;
import com.ilike.cartoon.common.utils.an;
import com.ilike.cartoon.module.http.callback.MHRCallbackListener;
import com.ilike.cartoon.module.save.l;
import com.ilike.cartoon.module.save.p;
import com.johnny.http.exception.HttpException;
import com.johnny.http.util.FastJsonTools;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.wxh.manhuaren.R.layout;
import com.wxh.manhuaren.R.string;
import com.yingqidm.pay.adyen.AdyenParameter;
import com.yingqidm.pay.alipay.AliPayParameter;
import com.yingqidm.pay.config.YQPayStatus;
import com.yingqidm.pay.gangupay.GanguPayParameter;
import com.yingqidm.pay.paypal.PaypalParameter;
import com.yingqidm.pay.webpay.WebPayParameter;
import com.yingqidm.pay.wxpay.WXPayParameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import onepaidpaymentsdk.gangutech.com.onepaidpaymentsdk.f;
import org.json.JSONException;
import org.json.JSONObject;

public class RechargeActivity
  extends BaseActivity
  implements RechargeAdapter.f
{
  private static final String A = "onepaid-credit";
  private static final String B = "adyen";
  private static final String C = "googlepay";
  private static final String s = "paypal";
  private static final String t = "alipay";
  private static final String u = "wxpay";
  private static final String v = "onepaid-";
  private static final String w = "onepaid-sqcode";
  private static final String x = "onepaid-sqbarcode";
  private static final String y = "onepaid-netatm";
  private static final String z = "onepaid-etatm";
  com.ilike.cartoon.common.dialog.r a;
  com.ilike.cartoon.common.dialog.r b;
  private RecyclerView c;
  private RechargeAdapter d;
  private ImageView e;
  private TextView f;
  private View g;
  private View h;
  private TextView i;
  private com.yingqidm.pay.b j;
  private boolean k = false;
  private int l;
  private com.ilike.cartoon.module.pay.a m;
  private List<com.ilike.cartoon.util.c> n;
  private boolean p;
  private com.ilike.cartoon.module.pay.a.a q = new com.ilike.cartoon.module.pay.a.a()
  {
    public void a()
    {
      RechargeActivity.this.t();
      RechargeActivity.this.g("订单校验成功...");
      RechargeActivity.a(RechargeActivity.this, null);
      if (RechargeActivity.a(RechargeActivity.this) != null)
      {
        RechargeActivity.a(RechargeActivity.this).a(RechargeActivity.b(RechargeActivity.this));
        RechargeActivity.a(RechargeActivity.this).d();
      }
    }
    
    public void a(YQPayStatus paramAnonymousYQPayStatus)
    {
      RechargeActivity.this.t();
      if (paramAnonymousYQPayStatus == YQPayStatus.PAY_AGAIN)
      {
        RechargeActivity.this.g("您有未完成的商品,请点击重试按钮...");
        return;
      }
      if (paramAnonymousYQPayStatus == YQPayStatus.PAY_SUBMIT_ORDER)
      {
        RechargeActivity.this.g("获取次元币失败,请点击重试...");
        return;
      }
      RechargeActivity.h(RechargeActivity.this).a(paramAnonymousYQPayStatus);
    }
    
    public void a(List<com.ilike.cartoon.util.c> paramAnonymousList)
    {
      RechargeActivity.a(RechargeActivity.this, paramAnonymousList);
      if (RechargeActivity.a(RechargeActivity.this) != null)
      {
        RechargeActivity.a(RechargeActivity.this).a(RechargeActivity.b(RechargeActivity.this));
        RechargeActivity.a(RechargeActivity.this).d();
      }
    }
    
    public void b()
    {
      RechargeActivity.this.s();
    }
  };
  private com.yingqidm.pay.listener.a r = new com.yingqidm.pay.listener.a()
  {
    public void a(AdyenParameter paramAnonymousAdyenParameter, com.yingqidm.pay.adyen.b paramAnonymousB)
    {
      Object localObject = new HashMap();
      ((HashMap)localObject).put("tradeCode", paramAnonymousAdyenParameter.getTradeCode());
      ((HashMap)localObject).put("cardEncryptedJson", paramAnonymousAdyenParameter.getCardEncryptedJson());
      HashMap localHashMap = new HashMap();
      localHashMap.put("cardNumber", paramAnonymousAdyenParameter.getCardNumber());
      localHashMap.put("cardHolderName", paramAnonymousAdyenParameter.getCardHolderName());
      localHashMap.put("cvc", paramAnonymousAdyenParameter.getCvc());
      localHashMap.put("expireMonth", paramAnonymousAdyenParameter.getExpireMonth());
      localHashMap.put("expireYear", paramAnonymousAdyenParameter.getExpireYear());
      ((HashMap)localObject).put("cardJson", localHashMap);
      paramAnonymousAdyenParameter = com.ilike.cartoon.common.utils.ae.b(FastJsonTools.a(localObject), paramAnonymousAdyenParameter.getV2());
      localObject = new com.johnny.http.core.b();
      ((com.johnny.http.core.b)localObject).a("encryptedOrderJson", paramAnonymousAdyenParameter);
      com.ilike.cartoon.module.http.a.a("adyen/api/authorise", (com.johnny.http.core.b)localObject, new RechargeActivity.10.2(this, paramAnonymousB));
    }
    
    public void a(AliPayParameter paramAnonymousAliPayParameter, String paramAnonymousString) {}
    
    public void a(YQPayStatus paramAnonymousYQPayStatus)
    {
      RechargeActivity.a(RechargeActivity.this, false);
      switch (RechargeActivity.13.a[paramAnonymousYQPayStatus.ordinal()])
      {
      default: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
        do
        {
          return;
          RechargeActivity.this.g("支付取消");
          return;
          RechargeActivity.this.g("支付失败");
          return;
          RechargeActivity.this.g("支付订单成功,正在获取次元币...");
          l.a();
          return;
        } while ((RechargeActivity.a(RechargeActivity.this).e() == null) || (RechargeActivity.a(RechargeActivity.this).e().size() <= 0));
        RechargeActivity.this.a((com.ilike.cartoon.entity.a)RechargeActivity.a(RechargeActivity.this).e().get(RechargeActivity.a(RechargeActivity.this).e().size() - 1));
        return;
      }
      l.a();
      RechargeActivity.i(RechargeActivity.this);
    }
    
    public void a(GanguPayParameter paramAnonymousGanguPayParameter, f paramAnonymousF, String paramAnonymousString, ProgressDialog paramAnonymousProgressDialog, Activity paramAnonymousActivity)
    {
      l.a();
      RechargeActivity.a(RechargeActivity.this, paramAnonymousGanguPayParameter.getNotifyUri(), paramAnonymousF, paramAnonymousString, paramAnonymousGanguPayParameter.getExtra(), paramAnonymousProgressDialog, paramAnonymousActivity);
    }
    
    public void a(PaypalParameter paramAnonymousPaypalParameter, PaymentConfirmation paramAnonymousPaymentConfirmation)
    {
      try
      {
        l.a();
        JSONObject localJSONObject = paramAnonymousPaymentConfirmation.b().q();
        paramAnonymousPaymentConfirmation = paramAnonymousPaymentConfirmation.d();
        if (paramAnonymousPaymentConfirmation == null) {
          return;
        }
        paramAnonymousPaymentConfirmation = paramAnonymousPaymentConfirmation.getJSONObject("response");
        if (paramAnonymousPaymentConfirmation != null)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("amount", localJSONObject.getString("amount"));
          localHashMap.put("currency_code", localJSONObject.getString("currency_code"));
          localHashMap.put("short_description", localJSONObject.getString("short_description"));
          localHashMap.put("intent", localJSONObject.getString("intent"));
          localHashMap.put("extra", an.c(paramAnonymousPaypalParameter.getExtra()));
          RechargeActivity.a(RechargeActivity.this, an.c(paramAnonymousPaypalParameter.getNotifyUri()), localHashMap, paramAnonymousPaymentConfirmation.getString("id"), an.c(paramAnonymousPaypalParameter.getTradeCode()));
          return;
        }
      }
      catch (JSONException paramAnonymousPaypalParameter)
      {
        a(YQPayStatus.PAY_FAIL);
      }
    }
    
    public void a(WXPayParameter paramAnonymousWXPayParameter)
    {
      try
      {
        l.a();
        com.johnny.http.core.b localB = new com.johnny.http.core.b();
        localB.a("transaction", an.c(paramAnonymousWXPayParameter.getTransaction()));
        com.ilike.cartoon.module.http.a.b(paramAnonymousWXPayParameter.getNotifyUri(), localB, new RechargeActivity.10.1(this));
        return;
      }
      catch (Exception paramAnonymousWXPayParameter) {}
    }
  };
  
  public RechargeActivity() {}
  
  private String a(OwntradeBean paramOwntradeBean)
  {
    com.johnny.http.core.b localB = new com.johnny.http.core.b();
    localB.a("partner", paramOwntradeBean.getPartner());
    localB.a("seller_id", paramOwntradeBean.getSeller_id());
    localB.a("out_trade_no", paramOwntradeBean.getOut_trade_no());
    localB.a("subject", paramOwntradeBean.getSubject());
    localB.a("body", paramOwntradeBean.getBody());
    localB.a("total_fee", paramOwntradeBean.getTotal_fee());
    localB.a("notify_url", paramOwntradeBean.getNotify_url());
    localB.a("service", paramOwntradeBean.getService());
    localB.a("payment_type", paramOwntradeBean.getPayment_type());
    localB.a("_input_charset", paramOwntradeBean.getInput_charset());
    localB.a("sign", paramOwntradeBean.getSign());
    localB.a("sign_type", paramOwntradeBean.getSign_type());
    return localB.p();
  }
  
  @android.support.annotation.ae
  private List<RechargeInfoBean> a(GetRechargeInfoBean paramGetRechargeInfoBean)
  {
    if (paramGetRechargeInfoBean == null) {}
    do
    {
      return null;
      paramGetRechargeInfoBean = paramGetRechargeInfoBean.getChannels();
    } while (paramGetRechargeInfoBean == null);
    return paramGetRechargeInfoBean;
  }
  
  private void a(OwntradeBean paramOwntradeBean, String paramString)
  {
    com.ilike.cartoon.module.pay.b localB = new com.ilike.cartoon.module.pay.b();
    localB.b(paramString);
    localB.d(an.c(paramOwntradeBean.getDeveloperPayload()));
    this.m.a(localB);
    this.m.a(this);
    a(false);
  }
  
  private void a(final String paramString1, final Map<String, String> paramMap, final String paramString2, final String paramString3)
  {
    com.johnny.http.core.b localB = new com.johnny.http.core.b();
    localB.a("extra", paramMap);
    localB.a("payCode", paramString2);
    localB.a("tradeCode", paramString3);
    com.ilike.cartoon.module.http.a.b(paramString1, localB, new MHRCallbackListener()
    {
      public void onCustomException(String paramAnonymousString1, String paramAnonymousString2)
      {
        RechargeActivity.this.t();
        RechargeActivity.b(RechargeActivity.this, false);
        RechargeActivity.this.g(an.c(paramAnonymousString2));
        RechargeActivity.a(RechargeActivity.this, paramMap, paramString1, paramString2, paramString3);
      }
      
      public void onFailure(HttpException paramAnonymousHttpException)
      {
        RechargeActivity.this.t();
        RechargeActivity.this.g(an.c("网络出错啦"));
        RechargeActivity.b(RechargeActivity.this, false);
        RechargeActivity.a(RechargeActivity.this, paramMap, paramString1, paramString2, paramString3);
      }
      
      public void onPreExecute()
      {
        RechargeActivity.this.s();
        super.onPreExecute();
      }
      
      public void onSuccess(HashMap<String, Object> paramAnonymousHashMap)
      {
        RechargeActivity.this.t();
        RechargeActivity.b(RechargeActivity.this, false);
        if ((paramAnonymousHashMap != null) && (an.a(paramAnonymousHashMap.get("status"), 0) == 1))
        {
          p.b(19);
          RechargeActivity.i(RechargeActivity.this);
        }
        do
        {
          do
          {
            return;
            if ((paramAnonymousHashMap == null) || (an.a(paramAnonymousHashMap.get("status"), 0) != 2)) {
              break;
            }
            RechargeActivity.this.g("错误订单信息,已经移除");
            p.b(19);
          } while ((RechargeActivity.a(RechargeActivity.this).e() == null) || (RechargeActivity.a(RechargeActivity.this).e().size() <= 0));
          paramAnonymousHashMap = (com.ilike.cartoon.entity.a)RechargeActivity.a(RechargeActivity.this).e().get(RechargeActivity.a(RechargeActivity.this).e().size() - 1);
        } while (paramAnonymousHashMap.e() != RechargeAdapter.ITEM_TYPE.TYPE_CONFIRM);
        paramAnonymousHashMap.l("");
        paramAnonymousHashMap.k("");
        RechargeActivity.a(RechargeActivity.this).d();
        return;
        RechargeActivity.a(RechargeActivity.this, paramMap, paramString1, paramString2, paramString3);
      }
    });
  }
  
  private void a(String paramString1, final f paramF, final String paramString2, String paramString3, final ProgressDialog paramProgressDialog, final Activity paramActivity)
  {
    com.johnny.http.core.b localB = new com.johnny.http.core.b();
    localB.a("extra", paramString3);
    localB.a("tradeCode", paramString2);
    com.ilike.cartoon.module.http.a.b(paramString1, localB, new MHRCallbackListener()
    {
      private void ganguFailure()
      {
        if (paramProgressDialog != null) {
          paramProgressDialog.dismiss();
        }
        if (paramActivity != null) {
          paramActivity.finish();
        }
      }
      
      public void onCustomException(String paramAnonymousString1, String paramAnonymousString2)
      {
        ganguFailure();
      }
      
      public void onFailure(HttpException paramAnonymousHttpException)
      {
        ganguFailure();
      }
      
      public void onSuccess(HashMap<String, Boolean> paramAnonymousHashMap)
      {
        if (((Boolean)paramAnonymousHashMap.get("isSuccess")).booleanValue())
        {
          paramAnonymousHashMap = new a.a();
          paramAnonymousHashMap.a = paramString2;
          paramF.a(paramAnonymousHashMap);
          return;
        }
        ganguFailure();
        RechargeActivity.this.g("获取次元币失败...请联系客服");
      }
    });
  }
  
  private void a(final Map<String, String> paramMap, final String paramString1, String paramString2, String paramString3)
  {
    paramMap.put("notifyUri", paramString1);
    paramMap.put("tradeCode", paramString3);
    paramString3 = FastJsonTools.a(paramMap);
    p.a(FastJsonTools.a(paramMap), 19, paramString2);
    paramString1 = null;
    paramMap = paramString1;
    if (this.d.e() != null)
    {
      paramMap = paramString1;
      if (this.d.e().size() > 0)
      {
        paramString1 = (com.ilike.cartoon.entity.a)this.d.e().get(this.d.e().size() - 1);
        paramMap = paramString1;
        if (paramString1.e() == RechargeAdapter.ITEM_TYPE.TYPE_CONFIRM)
        {
          paramString1.l(paramString3);
          paramString1.k(paramString2);
          this.d.d();
          paramMap = paramString1;
        }
      }
    }
    paramString1 = new com.ilike.cartoon.common.dialog.r(this);
    paramString1.b("很抱歉,订单提交失败");
    paramString1.b(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramString1.dismiss();
      }
    });
    paramString1.b("重试", new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RechargeActivity.this.b(paramMap);
        paramString1.dismiss();
      }
    });
    if (paramMap != null) {
      paramString1.show();
    }
  }
  
  private void a(boolean paramBoolean)
  {
    this.k = paramBoolean;
    if (paramBoolean)
    {
      localRechargeAdapter = this.d;
      str = com.ilike.cartoon.config.b.k;
      localRechargeAdapter.f(2131231643);
      return;
    }
    RechargeAdapter localRechargeAdapter = this.d;
    R.string str = com.ilike.cartoon.config.b.k;
    localRechargeAdapter.f(2131231646);
  }
  
  public static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if (paramContext != null)
      {
        int i1 = 0;
        while (i1 < paramContext.size())
        {
          boolean bool = ((PackageInfo)paramContext.get(i1)).packageName.equals("com.tencent.mm");
          if (bool) {
            return true;
          }
          i1 += 1;
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private boolean a(String paramString)
  {
    return ("alipay".equals(paramString)) || ("paypal".equals(paramString)) || (paramString.startsWith("onepaid-")) || ("onepaid-sqcode".equals(paramString)) || ("onepaid-sqbarcode".equals(paramString)) || ("onepaid-etatm".equals(paramString)) || ("onepaid-credit".equals(paramString)) || ("wxpay".equals(paramString)) || (paramString.startsWith("adyen")) || ("googlepay".equals(paramString));
  }
  
  private void b(OwntradeBean paramOwntradeBean)
  {
    String str = a(paramOwntradeBean);
    AliPayParameter localAliPayParameter = new AliPayParameter();
    localAliPayParameter.setTitle(paramOwntradeBean.getDisplayName());
    localAliPayParameter.setPayInfo(str);
    this.j.a(this, localAliPayParameter);
  }
  
  private void c(OwntradeBean paramOwntradeBean)
  {
    WXPayParameter localWXPayParameter = new WXPayParameter();
    localWXPayParameter.setAppid(paramOwntradeBean.getAppid());
    localWXPayParameter.setTitle(paramOwntradeBean.getDisplayName());
    localWXPayParameter.set_package(paramOwntradeBean.getPackageStr());
    localWXPayParameter.setPartnerid(paramOwntradeBean.getPartnerid());
    localWXPayParameter.setPrepayid(paramOwntradeBean.getPrepayid());
    localWXPayParameter.setNoncestr(paramOwntradeBean.getNoncestr());
    localWXPayParameter.setTimestamp(paramOwntradeBean.getTimestamp());
    localWXPayParameter.setSign(paramOwntradeBean.getSign());
    localWXPayParameter.setNotifyUri(paramOwntradeBean.getNotifyUri());
    this.j.a(this, localWXPayParameter);
    a(false);
  }
  
  private boolean c(com.ilike.cartoon.entity.a paramA)
  {
    if (this.k)
    {
      g("正在发起支付,请稍后...");
      return true;
    }
    if (paramA.p() == null)
    {
      g("请选择支付金额");
      return true;
    }
    return false;
  }
  
  private void d(OwntradeBean paramOwntradeBean)
  {
    AdyenParameter localAdyenParameter = new AdyenParameter();
    localAdyenParameter.setSubject(paramOwntradeBean.getSubject());
    localAdyenParameter.setAmount(paramOwntradeBean.getAmount());
    localAdyenParameter.setTradeCode(paramOwntradeBean.getTradeCode());
    localAdyenParameter.setV1(paramOwntradeBean.getV1());
    localAdyenParameter.setV2(paramOwntradeBean.getV2());
    this.j.a(this, localAdyenParameter);
    a(false);
  }
  
  private void d(final com.ilike.cartoon.entity.a paramA)
  {
    final com.ilike.cartoon.entity.a localA = paramA.p();
    Object localObject2 = an.c(paramA.c());
    if (("wxpay".equals(localObject2)) && (!a(this)))
    {
      g("请安装微信");
      return;
    }
    a(true);
    int i1 = paramA.u();
    Object localObject1 = new com.johnny.http.core.b();
    ((com.johnny.http.core.b)localObject1).a("deviceType", Integer.valueOf(1));
    ((com.johnny.http.core.b)localObject1).a("bizName", "coin");
    paramA = new HashMap();
    paramA.put("platformId", Integer.valueOf(4));
    paramA.put("areaId", Integer.valueOf(this.l));
    paramA.put("appChannel", ManhuarenApplication.b);
    paramA.put("activityId", localA.i());
    ((com.johnny.http.core.b)localObject1).a("bizArgs", paramA);
    ((com.johnny.http.core.b)localObject1).a("channel", localObject2);
    if ((i1 & 0x1) == 1) {
      if (a((String)localObject2))
      {
        i1 = 0;
        int i2 = i1;
        if ("paypal".equals(localObject2))
        {
          i2 = i1;
          if (Build.VERSION.SDK_INT < 16) {
            i2 = 1;
          }
        }
        i1 = i2;
        if (((String)localObject2).startsWith("onepaid-"))
        {
          i1 = i2;
          if (Build.VERSION.SDK_INT < 15) {
            i1 = 1;
          }
        }
      }
    }
    label226:
    label242:
    label635:
    label681:
    for (;;)
    {
      if (i1 != 0)
      {
        ((com.johnny.http.core.b)localObject1).a("proxyType", Integer.valueOf(0));
        paramA = an.c(localA.h());
        ((com.johnny.http.core.b)localObject1).a("pid", paramA);
        ((com.johnny.http.core.b)localObject1).a("pname", an.c(localA.s()));
        ((com.johnny.http.core.b)localObject1).a("quantity", Integer.valueOf(1));
        ((com.johnny.http.core.b)localObject1).a("userId", an.c(Integer.valueOf(com.ilike.cartoon.module.save.r.m())));
        localObject3 = new HashMap();
        ((HashMap)localObject3).put("value", Float.valueOf(localA.o()));
        ((HashMap)localObject3).put("unit", an.c(localA.t()));
        ((com.johnny.http.core.b)localObject1).a("price", localObject3);
        if (i1 == 0) {
          break label635;
        }
        localObject2 = new WebPayParameter();
        paramA = com.ilike.cartoon.module.http.a.c.o + "owntrade" + "?";
      }
      try
      {
        localObject3 = new com.johnny.http.core.b();
        ((com.johnny.http.core.b)localObject3).a("body", com.ilike.cartoon.common.utils.ae.a(((com.johnny.http.core.b)localObject1).j(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzQAvTOu/+74XbeqaPFfzdYhRGjgjW5Agvd5XdQ0OHPA75kNpgQbpvOrxq7rNNAz62SHo0TKu2W1puUR/iDcsxomJe5r6lFJm7N/tm/Q6ViK2PtRW8G4ZonjXjytwqx9H8zsQsRuU94MnndRtza6yGdhU3H7/86yH4An9Awh+hxQIDAQAB"));
        localObject1 = paramA + ((com.johnny.http.core.b)localObject3).p();
        paramA = (com.ilike.cartoon.entity.a)localObject1;
      }
      catch (Exception localException)
      {
        for (;;) {}
        i1 = 1;
      }
      Object localObject3 = new HashMap();
      ((HashMap)localObject3).put("X-Yq-Yqci", ManhuarenApplication.k().c());
      ((HashMap)localObject3).put("X-Yq-Key", com.ilike.cartoon.module.save.r.b() + "");
      ((HashMap)localObject3).put("Authorization", com.ilike.cartoon.module.save.r.c());
      if (com.ilike.cartoon.module.save.r.a() == 0) {}
      for (localObject1 = "1";; localObject1 = "0")
      {
        ((HashMap)localObject3).put("yq_is_anonymous", localObject1);
        ((HashMap)localObject3).put("x-request-id", an.c(UUID.randomUUID()));
        ((WebPayParameter)localObject2).setWebHead((HashMap)localObject3);
        ((WebPayParameter)localObject2).setTitle(localA.a());
        ((WebPayParameter)localObject2).setWebUrl(an.c(paramA));
        this.j.a(this, (WebPayParameter)localObject2);
        return;
        i1 = 1;
        break;
        if ((i1 & 0x2) != 2) {
          break label681;
        }
        i1 = 1;
        break label226;
        ((com.johnny.http.core.b)localObject1).a("proxyType", Integer.valueOf(1));
        break label242;
      }
      try
      {
        ((com.johnny.http.core.b)localObject1).a(com.ilike.cartoon.common.utils.ae.a(((com.johnny.http.core.b)localObject1).j(), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzQAvTOu/+74XbeqaPFfzdYhRGjgjW5Agvd5XdQ0OHPA75kNpgQbpvOrxq7rNNAz62SHo0TKu2W1puUR/iDcsxomJe5r6lFJm7N/tm/Q6ViK2PtRW8G4ZonjXjytwqx9H8zsQsRuU94MnndRtza6yGdhU3H7/86yH4An9Awh+hxQIDAQAB"));
        com.ilike.cartoon.module.http.a.a((com.johnny.http.core.b)localObject1, new MHRCallbackListener()
        {
          public void onCustomException(String paramAnonymousString1, String paramAnonymousString2)
          {
            RechargeActivity.this.t();
            RechargeActivity.g(RechargeActivity.this);
          }
          
          public void onFailure(HttpException paramAnonymousHttpException)
          {
            RechargeActivity.this.t();
            RechargeActivity.g(RechargeActivity.this);
          }
          
          public void onPreExecute()
          {
            RechargeActivity.this.s();
            super.onPreExecute();
          }
          
          public void onSuccess(HashMap<String, String> paramAnonymousHashMap)
          {
            RechargeActivity.this.t();
            if (paramAnonymousHashMap == null)
            {
              RechargeActivity.g(RechargeActivity.this);
              return;
            }
            if (com.ilike.cartoon.common.utils.ae.a((String)paramAnonymousHashMap.get("introducer"), (String)paramAnonymousHashMap.get("sign"), "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzQAvTOu/+74XbeqaPFfzdYhRGjgjW5Agvd5XdQ0OHPA75kNpgQbpvOrxq7rNNAz62SHo0TKu2W1puUR/iDcsxomJe5r6lFJm7N/tm/Q6ViK2PtRW8G4ZonjXjytwqx9H8zsQsRuU94MnndRtza6yGdhU3H7/86yH4An9Awh+hxQIDAQAB"))
            {
              paramAnonymousHashMap = (OwntradeBean)FastJsonTools.a((String)paramAnonymousHashMap.get("introducer"), OwntradeBean.class);
              if (paramAnonymousHashMap == null)
              {
                RechargeActivity.g(RechargeActivity.this);
                return;
              }
              paramAnonymousHashMap.setDisplayName(localA.a());
              paramAnonymousHashMap.setAmountBean((AmountBean)FastJsonTools.a(paramAnonymousHashMap.getAmount(), AmountBean.class));
              if (("paypal".equals(this.val$payChannel)) && (paramAnonymousHashMap.getAmountBean() != null))
              {
                RechargeActivity.a(RechargeActivity.this, paramAnonymousHashMap);
                return;
              }
              if ("alipay".equals(this.val$payChannel))
              {
                RechargeActivity.b(RechargeActivity.this, paramAnonymousHashMap);
                return;
              }
              if ("wxpay".equals(this.val$payChannel))
              {
                RechargeActivity.c(RechargeActivity.this, paramAnonymousHashMap);
                return;
              }
              if ((this.val$payChannel.startsWith("onepaid-")) || ("onepaid-sqcode".equals(this.val$payChannel)) || ("onepaid-sqbarcode".equals(this.val$payChannel)) || ("onepaid-etatm".equals(this.val$payChannel)) || ("onepaid-credit".equals(this.val$payChannel)))
              {
                RechargeActivity.d(RechargeActivity.this, paramAnonymousHashMap);
                return;
              }
              if (this.val$payChannel.startsWith("adyen"))
              {
                RechargeActivity.e(RechargeActivity.this, paramAnonymousHashMap);
                return;
              }
              if ("googlepay".equals(this.val$payChannel))
              {
                RechargeActivity.a(RechargeActivity.this, paramAnonymousHashMap, paramA);
                return;
              }
              RechargeActivity.g(RechargeActivity.this);
              return;
            }
            RechargeActivity.g(RechargeActivity.this);
          }
        });
        return;
      }
      catch (HttpException localHttpException)
      {
        for (;;) {}
      }
    }
  }
  
  private void e()
  {
    com.ilike.cartoon.module.http.a.v(new MHRCallbackListener()
    {
      public List<com.ilike.cartoon.entity.a> onAsyncCustomData(GetRechargeInfoBean paramAnonymousGetRechargeInfoBean, boolean paramAnonymousBoolean)
      {
        List localList = RechargeActivity.a(RechargeActivity.this, paramAnonymousGetRechargeInfoBean);
        if (localList == null) {
          return null;
        }
        RechargeActivity.a(RechargeActivity.this, paramAnonymousGetRechargeInfoBean.getAreaId());
        paramAnonymousGetRechargeInfoBean = "";
        ArrayList localArrayList = new ArrayList();
        int i = 0;
        int j = 0;
        String str1 = null;
        String str2 = null;
        Object localObject1;
        for (Object localObject2 = null; i < localList.size(); localObject2 = localObject1)
        {
          RechargeInfoBean localRechargeInfoBean = (RechargeInfoBean)localList.get(i);
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            str2 = localRechargeInfoBean.getName();
            str1 = localRechargeInfoBean.getDisplayName();
            j = localRechargeInfoBean.getSupportProxyType();
            localObject1 = localRechargeInfoBean.getRechargeGoods();
            paramAnonymousGetRechargeInfoBean = localRechargeInfoBean.getDescription();
          }
          localObject2 = new com.ilike.cartoon.entity.a();
          ((com.ilike.cartoon.entity.a)localObject2).a(RechargeAdapter.ITEM_TYPE.TYPE_PAY_CHANNEL);
          ((com.ilike.cartoon.entity.a)localObject2).a(i);
          ((com.ilike.cartoon.entity.a)localObject2).g(localRechargeInfoBean.getIconUrl());
          ((com.ilike.cartoon.entity.a)localObject2).q(localRechargeInfoBean.getSubscript());
          ((com.ilike.cartoon.entity.a)localObject2).a(localRechargeInfoBean.getDisplayName());
          ((com.ilike.cartoon.entity.a)localObject2).b(j);
          ((com.ilike.cartoon.entity.a)localObject2).c(str2);
          ((com.ilike.cartoon.entity.a)localObject2).b(localRechargeInfoBean.getName());
          ((com.ilike.cartoon.entity.a)localObject2).p(localRechargeInfoBean.getDescription());
          ((com.ilike.cartoon.entity.a)localObject2).c(localRechargeInfoBean.getSupportProxyType());
          ((com.ilike.cartoon.entity.a)localObject2).a(localRechargeInfoBean.getRechargeGoods());
          localArrayList.add(localObject2);
          i += 1;
        }
        RechargeActivity.a(RechargeActivity.this).a(RechargeActivity.b(RechargeActivity.this));
        RechargeActivity.a(RechargeActivity.this).a(localArrayList, (List)localObject2, str2, str1, j, paramAnonymousGetRechargeInfoBean);
        return localArrayList;
      }
      
      public void onCustomData(Object paramAnonymousObject, boolean paramAnonymousBoolean)
      {
        RechargeActivity.this.t();
        if (paramAnonymousObject == null)
        {
          RechargeActivity.c(RechargeActivity.this);
          return;
        }
        paramAnonymousObject = (List)paramAnonymousObject;
        RechargeActivity.a(RechargeActivity.this).f();
        RechargeActivity.a(RechargeActivity.this).b(paramAnonymousObject);
        RechargeActivity.a(RechargeActivity.this).d();
      }
      
      public void onCustomException(String paramAnonymousString1, String paramAnonymousString2)
      {
        RechargeActivity.c(RechargeActivity.this);
        RechargeActivity.this.t();
      }
      
      public void onFailure(HttpException paramAnonymousHttpException)
      {
        RechargeActivity.c(RechargeActivity.this);
        RechargeActivity.this.t();
      }
      
      public void onPreExecute()
      {
        RechargeActivity.this.s();
        super.onPreExecute();
      }
    });
  }
  
  private void e(OwntradeBean paramOwntradeBean)
  {
    PaypalParameter localPaypalParameter = new PaypalParameter();
    localPaypalParameter.setEnvironment(paramOwntradeBean.getEnvironmentTxt());
    localPaypalParameter.setTitle(paramOwntradeBean.getDisplayName());
    localPaypalParameter.setClientId(paramOwntradeBean.getClientId());
    localPaypalParameter.setNotifyUri(an.c(paramOwntradeBean.getNotifyUri()));
    localPaypalParameter.setAmount(paramOwntradeBean.getAmountBean().getValue());
    localPaypalParameter.setUnit(an.c(paramOwntradeBean.getAmountBean().getUnit()));
    localPaypalParameter.setSubject(an.c(paramOwntradeBean.getSubject()) + "");
    localPaypalParameter.setTradeCode(an.c(paramOwntradeBean.getTradeCode()));
    localPaypalParameter.setExtra(an.c(paramOwntradeBean.getExtra()));
    localPaypalParameter.setMerchantName(an.c(paramOwntradeBean.getMerchantName()));
    localPaypalParameter.setMerchantPrivacyPolicyUri(an.c(paramOwntradeBean.getMerchantPrivacyPolicyUri()));
    localPaypalParameter.setMerchantUserAgreementUri(an.c(paramOwntradeBean.getMerchantUserAgreementUri()));
    this.j.a(this, localPaypalParameter);
  }
  
  @ad
  private View.OnClickListener f()
  {
    new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = paramAnonymousView.getId();
        paramAnonymousView = com.ilike.cartoon.config.b.g;
        if (i == 2131624164) {
          RechargeActivity.this.finish();
        }
        do
        {
          return;
          paramAnonymousView = com.ilike.cartoon.config.b.g;
        } while (i != 2131624445);
        RechargeActivity.this.startActivity(new Intent(RechargeActivity.this, PhoneBindingActivity.class));
      }
    };
  }
  
  private void f(OwntradeBean paramOwntradeBean)
  {
    GanguPayParameter localGanguPayParameter = new GanguPayParameter();
    localGanguPayParameter.setMerID(paramOwntradeBean.getMerId());
    localGanguPayParameter.setMerKey(paramOwntradeBean.getHashKey());
    localGanguPayParameter.setTitle(paramOwntradeBean.getDisplayName());
    localGanguPayParameter.setUnit(an.a(paramOwntradeBean.getAmountBean().getUnit(), 1));
    localGanguPayParameter.setTradeCode(paramOwntradeBean.getTradeCode());
    localGanguPayParameter.setNotifyUri(paramOwntradeBean.getNotifyUri());
    localGanguPayParameter.setIntroduction(paramOwntradeBean.getIntroduction());
    localGanguPayParameter.setAmount(paramOwntradeBean.getAmountBean().getValue());
    localGanguPayParameter.setiPayType(paramOwntradeBean.getPaywayType());
    if (paramOwntradeBean.getEnvironment() == 1) {
      localGanguPayParameter.setbSet(true);
    }
    for (;;)
    {
      localGanguPayParameter.setSubject(paramOwntradeBean.getSubject());
      localGanguPayParameter.setExtra(paramOwntradeBean.getExtra());
      localGanguPayParameter.setPaymentType(paramOwntradeBean.getPaymentType());
      localGanguPayParameter.setRemark(paramOwntradeBean.getRemark());
      this.j.a(this, localGanguPayParameter);
      return;
      if (paramOwntradeBean.getEnvironment() == 2) {
        localGanguPayParameter.setbSet(false);
      }
    }
  }
  
  private void g()
  {
    if (this.a == null) {
      this.a = new com.ilike.cartoon.common.dialog.r(this);
    }
    this.a.b("您已成功购买次元币\n若状态未更新，请耐心等待。");
    this.a.b("知道了", new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RechargeActivity.this.a.dismiss();
        RechargeActivity.c(RechargeActivity.this, true);
        RechargeActivity.this.finish();
      }
    });
    if (!isFinishing()) {
      this.a.show();
    }
  }
  
  private void h()
  {
    if (this.b == null) {
      this.b = new com.ilike.cartoon.common.dialog.r(this);
    }
    com.ilike.cartoon.common.dialog.r localR = this.b;
    R.string str = com.ilike.cartoon.config.b.k;
    localR.b(getString(2131231650));
    localR = this.b;
    str = com.ilike.cartoon.config.b.k;
    localR.a(getString(2131230992), new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RechargeActivity.this.b.dismiss();
        RechargeActivity.this.finish();
      }
    });
    localR = this.b;
    str = com.ilike.cartoon.config.b.k;
    localR.b(getString(2131231368), new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RechargeActivity.this.b.dismiss();
        RechargeActivity.j(RechargeActivity.this);
      }
    });
    if (!isFinishing()) {
      this.b.show();
    }
  }
  
  private void i()
  {
    a(false);
    R.string str = com.ilike.cartoon.config.b.k;
    c(2131231648);
  }
  
  protected int a()
  {
    R.layout localLayout = com.ilike.cartoon.config.b.h;
    return 2130968667;
  }
  
  protected void a(Bundle paramBundle)
  {
    this.j = com.yingqidm.pay.b.a();
    this.m = new com.ilike.cartoon.module.pay.a(this, this.q);
    this.j.a(this.r);
  }
  
  public void a(final com.ilike.cartoon.entity.a paramA)
  {
    if (c(paramA)) {
      return;
    }
    if (!an.e(paramA.q()))
    {
      final com.ilike.cartoon.common.dialog.r localR = new com.ilike.cartoon.common.dialog.r(this);
      localR.b("您还有充值未到帐的订单，请点击“继续”完成订单处理");
      localR.b("继续", new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          localR.dismiss();
          RechargeActivity.this.b(paramA);
        }
      });
      localR.show();
      return;
    }
    if (!an.a(this.n))
    {
      paramA = new com.ilike.cartoon.common.dialog.r(this);
      paramA.b("您还有充值未到帐的订单，请点击“继续”完成订单处理");
      paramA.b("继续", new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramA.dismiss();
          RechargeActivity.d(RechargeActivity.this).b(RechargeActivity.b(RechargeActivity.this));
        }
      });
      paramA.show();
      return;
    }
    d(paramA);
  }
  
  protected void b()
  {
    Object localObject1 = com.ilike.cartoon.config.b.g;
    this.g = findViewById(2131624444);
    localObject1 = com.ilike.cartoon.config.b.g;
    this.h = findViewById(2131624446);
    localObject1 = com.ilike.cartoon.config.b.g;
    this.i = ((TextView)findViewById(2131624445));
    localObject1 = com.ilike.cartoon.config.b.g;
    this.e = ((ImageView)findViewById(2131624164));
    localObject1 = com.ilike.cartoon.config.b.g;
    this.f = ((TextView)findViewById(2131624500));
    this.f.setVisibility(0);
    localObject1 = this.f;
    Object localObject2 = com.ilike.cartoon.config.b.k;
    ((TextView)localObject1).setText(2131231484);
    localObject1 = this.e;
    localObject2 = com.ilike.cartoon.config.b.j;
    ((ImageView)localObject1).setImageResource(2130903222);
    localObject1 = com.ilike.cartoon.config.b.g;
    this.c = ((RecyclerView)findViewById(2131624443));
    localObject1 = new GridLayoutManager(this, 3);
    this.c.setLayoutManager((RecyclerView.LayoutManager)localObject1);
    this.d = new RechargeAdapter(this);
    this.d.a(this);
    this.c.setAdapter(this.d);
    ((GridLayoutManager)localObject1).a(new GridLayoutManager.b()
    {
      public int a(int paramAnonymousInt)
      {
        if ((RechargeActivity.a(RechargeActivity.this).a(paramAnonymousInt) == RechargeAdapter.ITEM_TYPE.TYPE_DIVISION.ordinal()) || (RechargeActivity.a(RechargeActivity.this).a(paramAnonymousInt) == RechargeAdapter.ITEM_TYPE.TYPE_CONFIRM.ordinal())) {
          return this.b.c();
        }
        return 1;
      }
    });
    e();
  }
  
  public void b(final com.ilike.cartoon.entity.a paramA)
  {
    if (c(paramA)) {}
    do
    {
      return;
      if (!an.e(paramA.q()))
      {
        a(true);
        String str1 = paramA.q();
        paramA = FastJsonTools.a(paramA.r());
        if ((paramA == null) || (an.e((String)paramA.get("notifyUri"))))
        {
          paramA = com.ilike.cartoon.config.b.k;
          c(2131231653);
          return;
        }
        String str2 = (String)paramA.get("notifyUri");
        String str3 = (String)paramA.get("tradeCode");
        paramA.remove("notifyUri");
        paramA.remove("tradeCode");
        a(an.c(str2), paramA, str1, str3);
        return;
      }
    } while (an.a(this.n));
    paramA = new com.ilike.cartoon.common.dialog.r(this);
    paramA.b("您还有充值未到帐的订单，请点击“继续”完成订单处理");
    paramA.b("继续", new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramA.dismiss();
        RechargeActivity.d(RechargeActivity.this).b(RechargeActivity.b(RechargeActivity.this));
      }
    });
    paramA.show();
  }
  
  protected void c()
  {
    this.e.setOnClickListener(f());
    this.i.setOnClickListener(f());
  }
  
  public void d()
  {
    com.ilike.cartoon.module.http.a.j(new MHRCallbackListener()
    {
      public GetUserInfoBean onAsyncPreRequest()
      {
        GetUserInfoBean localGetUserInfoBean = (GetUserInfoBean)l.o("my_material" + com.ilike.cartoon.module.save.r.b());
        if (localGetUserInfoBean != null) {
          return localGetUserInfoBean;
        }
        return null;
      }
      
      public void onAsyncPreSuccess(GetUserInfoBean paramAnonymousGetUserInfoBean)
      {
        if (paramAnonymousGetUserInfoBean != null) {
          l.b(paramAnonymousGetUserInfoBean, "my_material" + com.ilike.cartoon.module.save.r.b());
        }
      }
      
      public void onCustomException(String paramAnonymousString1, String paramAnonymousString2) {}
      
      public void onFailure(HttpException paramAnonymousHttpException) {}
      
      public void onSuccess(GetUserInfoBean paramAnonymousGetUserInfoBean)
      {
        if (paramAnonymousGetUserInfoBean == null) {
          return;
        }
        if (an.e(paramAnonymousGetUserInfoBean.getTelephone()))
        {
          RechargeActivity.e(RechargeActivity.this).setVisibility(0);
          RechargeActivity.f(RechargeActivity.this).setVisibility(0);
          return;
        }
        RechargeActivity.e(RechargeActivity.this).setVisibility(8);
        RechargeActivity.f(RechargeActivity.this).setVisibility(8);
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((this.m == null) || (!this.m.a(paramInt1, paramInt2, paramIntent))) {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.m != null) {
      this.m.b();
    }
    if (com.ilike.cartoon.module.a.a.a() != null)
    {
      if (this.p) {
        com.ilike.cartoon.module.a.a.a().a();
      }
    }
    else {
      return;
    }
    com.ilike.cartoon.module.a.a.a().b();
  }
  
  protected void onResume()
  {
    super.onResume();
    d();
  }
}
