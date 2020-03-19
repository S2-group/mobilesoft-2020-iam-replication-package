package ctrip.base.logical.component.commonview.pay;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Message;
import ctrip.base.logical.a.e;
import ctrip.base.logical.component.CtripBaseApplication;
import ctrip.base.logical.component.commonview.otherpay.OtherPayActivity;
import ctrip.base.logical.util.c;
import ctrip.business.database.aj;
import ctrip.business.enumclass.PaymentCardTypeCategoryEnum;
import ctrip.business.handle.PriceType;
import ctrip.business.util.DateUtil;
import ctrip.business.util.StringUtil;
import ctrip.business.viewmodel.BasicLocationDataModel;
import ctrip.business.viewmodel.CreditCardViewItemModel;
import ctrip.business.viewmodel.CreditCardViewPageModel;
import ctrip.business.viewmodel.IDCardChildModel;
import ctrip.model.DispatchModel;
import ctrip.model.OrderSubmitPaymentModel;
import ctrip.model.PayOrderInfoViewModel;
import ctrip.sender.h.bp;
import ctrip.viewcache.UserRecordUtil;
import ctrip.viewcache.widget.PaymentCacheBean;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ar
{
  private static z a(int paramInt, String paramString)
  {
    z localZ = new z();
    localZ.a = 17;
    if (StringUtil.emptyOrNull(paramString)) {
      localZ.b = 2131494155;
    }
    do
    {
      int i;
      do
      {
        do
        {
          return localZ;
          i = StringUtil.getSBCCaseLength(paramString);
          if (1 != paramInt) {
            break;
          }
          if (i == 15)
          {
            localZ.b = 2131494157;
            localZ.c = true;
            return localZ;
          }
        } while (bp.d(paramString) != 0);
        localZ.b = 2131494156;
        return localZ;
        if (4 != paramInt) {
          break;
        }
        if (i > 40)
        {
          localZ.b = 2131494156;
          return localZ;
        }
      } while (Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9\\(\\)]*$").matcher(paramString).matches());
      localZ.b = 2131494156;
      return localZ;
      if (i > 40)
      {
        localZ.b = 2131494156;
        return localZ;
      }
    } while (Pattern.compile("^[a-zA-Z0-9\\(\\)]*$").matcher(paramString).matches());
    localZ.b = 2131494156;
    return localZ;
  }
  
  private static z a(CreditCardViewItemModel paramCreditCardViewItemModel, String paramString)
  {
    z localZ = new z();
    if (paramCreditCardViewItemModel == null)
    {
      localZ.b = 2131494059;
      localZ.a = 1;
    }
    do
    {
      return localZ;
      if (paramCreditCardViewItemModel.cardTypeId == 0)
      {
        localZ.b = 2131494059;
        localZ.a = 1;
        return localZ;
      }
      if (StringUtil.emptyOrNull(paramString))
      {
        localZ.b = 2131494075;
        localZ.a = 2;
        return localZ;
      }
    } while (paramString.length() == 4);
    localZ.b = 2131493967;
    localZ.a = 2;
    return localZ;
  }
  
  private static z a(CreditCardViewItemModel paramCreditCardViewItemModel, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    z localZ = new z();
    if (paramCreditCardViewItemModel == null)
    {
      localZ.b = 2131494059;
      localZ.a = 12;
    }
    do
    {
      return localZ;
      if (paramCreditCardViewItemModel.cardTypeId == 0)
      {
        localZ.b = 2131494059;
        localZ.a = 12;
        return localZ;
      }
      if (StringUtil.emptyOrNull(paramString1))
      {
        localZ.b = 2131494064;
        localZ.a = 13;
        return localZ;
      }
      if (paramCreditCardViewItemModel.needCVV)
      {
        if (StringUtil.emptyOrNull(paramString2))
        {
          localZ.b = 2131492867;
          localZ.a = 11;
          return localZ;
        }
        if ((paramString2.length() != 3) && (paramString2.length() != 4))
        {
          localZ.b = 2131492868;
          localZ.a = 11;
          return localZ;
        }
      }
      if (paramCreditCardViewItemModel.needName)
      {
        if (StringUtil.emptyOrNull(paramString4))
        {
          localZ.b = 2131494073;
          localZ.a = 14;
          return localZ;
        }
        if (paramString4.length() > 50)
        {
          localZ.b = 2131492869;
          localZ.a = 14;
          return localZ;
        }
        if (!a(paramString4))
        {
          localZ.b = 2131492869;
          localZ.a = 14;
          return localZ;
        }
      }
      if (StringUtil.emptyOrNull(paramString3))
      {
        localZ.b = 2131494087;
        localZ.a = 15;
        return localZ;
      }
      paramString1 = DateUtil.getCurrentDate();
      paramCreditCardViewItemModel = paramString3;
      if (paramString3.length() < 8) {
        paramCreditCardViewItemModel = paramString3 + "01";
      }
    } while (DateUtil.firstDateStrAfterSecondDateStr(paramCreditCardViewItemModel, paramString1, 1));
    localZ.b = 2131494184;
    localZ.a = 15;
    return localZ;
  }
  
  private static z a(CreditCardViewItemModel paramCreditCardViewItemModel, String paramString1, String paramString2, String paramString3, String paramString4, IDCardChildModel paramIDCardChildModel, String paramString5)
  {
    z localZ = new z();
    if (paramCreditCardViewItemModel == null)
    {
      localZ.b = 2131494059;
      localZ.a = 12;
    }
    do
    {
      do
      {
        return localZ;
        if (paramCreditCardViewItemModel.cardTypeId == 0)
        {
          localZ.b = 2131494059;
          localZ.a = 12;
          return localZ;
        }
        if (StringUtil.emptyOrNull(paramString1))
        {
          localZ.b = 2131494064;
          localZ.a = 13;
          return localZ;
        }
        if (paramCreditCardViewItemModel.needCVV)
        {
          if (StringUtil.emptyOrNull(paramString2))
          {
            localZ.b = 2131496126;
            localZ.a = 11;
            return localZ;
          }
          if (paramString2.length() != 3)
          {
            localZ.b = 2131494021;
            localZ.a = 11;
            return localZ;
          }
        }
        if (paramCreditCardViewItemModel.needName)
        {
          if (StringUtil.emptyOrNull(paramString4))
          {
            localZ.b = 2131494073;
            localZ.a = 14;
            return localZ;
          }
          if (!StringUtil.isConSpeCharacters(paramString4))
          {
            localZ.b = 2131494090;
            localZ.a = 14;
            return localZ;
          }
          if (bp.k(paramString4) == 0)
          {
            localZ.b = 2131494089;
            localZ.a = 14;
            return localZ;
          }
          if (paramString4.length() < 2)
          {
            localZ.b = 2131494139;
            localZ.a = 14;
            return localZ;
          }
          if (paramString4.length() > 10)
          {
            localZ.b = 2131494133;
            localZ.a = 14;
            return localZ;
          }
        }
        if (StringUtil.emptyOrNull(paramString3))
        {
          localZ.b = 2131494087;
          localZ.a = 15;
          return localZ;
        }
        paramString2 = DateUtil.getCurrentDate();
        paramString1 = paramString3;
        if (paramString3.length() < 8) {
          paramString1 = paramString3 + "01";
        }
        if (!DateUtil.firstDateStrAfterSecondDateStr(paramString1, paramString2, 1))
        {
          localZ.b = 2131494184;
          localZ.a = 15;
          return localZ;
        }
        if ((paramCreditCardViewItemModel.needCardType) && (paramIDCardChildModel.iDCardType == 0))
        {
          localZ.b = 2131494159;
          localZ.a = 16;
          return localZ;
        }
      } while (!paramCreditCardViewItemModel.needCardNo);
      if (StringUtil.emptyOrNull(paramString5))
      {
        localZ.b = 2131494155;
        localZ.a = 17;
        return localZ;
      }
      if ((1 == paramIDCardChildModel.iDCardType) && (bp.d(paramString5) == 0))
      {
        localZ.b = 2131494156;
        localZ.a = 17;
        return localZ;
      }
    } while ((1 == paramIDCardChildModel.iDCardType) || (StringUtil.isValidStr(paramString5)));
    localZ.b = 2131494156;
    localZ.a = 17;
    return localZ;
  }
  
  private static z a(CreditCardViewPageModel paramCreditCardViewPageModel, boolean paramBoolean)
  {
    z localZ = new z();
    Object localObject = localZ;
    CreditCardViewItemModel localCreditCardViewItemModel;
    if (paramCreditCardViewPageModel != null)
    {
      localCreditCardViewItemModel = paramCreditCardViewPageModel.selectCreditCard;
      if (localCreditCardViewItemModel != null) {
        break label40;
      }
      localZ.b = 2131494059;
      localZ.a = 1;
      localObject = localZ;
    }
    label40:
    label374:
    do
    {
      do
      {
        PaymentCardTypeCategoryEnum localPaymentCardTypeCategoryEnum;
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          return localObject;
                          if (localCreditCardViewItemModel.cardTypeId == 0)
                          {
                            localZ.b = 2131494059;
                            localZ.a = 1;
                            return localZ;
                          }
                          localObject = localZ;
                        } while (localCreditCardViewItemModel == null);
                        localPaymentCardTypeCategoryEnum = localCreditCardViewItemModel.cardTypeCategory;
                        localObject = localZ;
                      } while (localPaymentCardTypeCategoryEnum == null);
                      if (PaymentCardTypeCategoryEnum.CCD != localPaymentCardTypeCategoryEnum) {
                        break;
                      }
                      if (StringUtil.emptyOrNull(paramCreditCardViewPageModel.creditCardNo))
                      {
                        localZ.b = 2131494075;
                        localZ.a = 2;
                        return localZ;
                      }
                      if (paramCreditCardViewPageModel.creditCardNo.length() != 4)
                      {
                        localZ.b = 2131493967;
                        localZ.a = 2;
                        return localZ;
                      }
                      localObject = localZ;
                    } while (!paramCreditCardViewPageModel.hasIchangedTheCardExpire);
                    if (!localCreditCardViewItemModel.needCVV) {
                      break;
                    }
                    localZ = a(paramCreditCardViewPageModel.cvvNo, paramBoolean, 3);
                    localObject = localZ;
                  } while (localZ.b != -1);
                  localObject = localZ;
                } while (!StringUtil.emptyOrNull(localCreditCardViewItemModel.expireDate));
                localZ.b = 2131494087;
                localZ.a = 4;
                return localZ;
                if (PaymentCardTypeCategoryEnum.CCY != localPaymentCardTypeCategoryEnum) {
                  break label374;
                }
                if (StringUtil.emptyOrNull(paramCreditCardViewPageModel.creditCardNo))
                {
                  localZ.b = 2131494075;
                  localZ.a = 2;
                  return localZ;
                }
                if (paramCreditCardViewPageModel.creditCardNo.length() != 4)
                {
                  localZ.b = 2131493967;
                  localZ.a = 2;
                  return localZ;
                }
                if (!localCreditCardViewItemModel.needCVV) {
                  break;
                }
                localZ = a(paramCreditCardViewPageModel.cvvNo, paramBoolean, 3);
                localObject = localZ;
              } while (localZ.b != -1);
              if ((paramCreditCardViewPageModel.hasIchangedTheCardExpire) && (StringUtil.emptyOrNull(localCreditCardViewItemModel.expireDate)))
              {
                localZ.b = 2131494087;
                localZ.a = 4;
                return localZ;
              }
              localObject = localZ;
            } while (!localCreditCardViewItemModel.needPhoneNo);
            paramCreditCardViewPageModel = localCreditCardViewItemModel.phoneNO;
            if (StringUtil.emptyOrNull(paramCreditCardViewPageModel))
            {
              localZ.b = 2131496111;
              localZ.a = 5;
              return localZ;
            }
            localObject = localZ;
          } while (paramCreditCardViewPageModel.length() == 11);
          localZ.b = 2131496099;
          localZ.a = 5;
          return localZ;
          localObject = localZ;
        } while (PaymentCardTypeCategoryEnum.DC != localPaymentCardTypeCategoryEnum);
        if (localCreditCardViewItemModel.needPhoneNo)
        {
          localObject = localCreditCardViewItemModel.phoneNO;
          if (StringUtil.emptyOrNull((String)localObject))
          {
            localZ.b = 2131496111;
            localZ.a = 6;
            return localZ;
          }
          if (((String)localObject).length() != 11)
          {
            localZ.b = 2131496099;
            localZ.a = 6;
            return localZ;
          }
        }
        localObject = localZ;
      } while (!localCreditCardViewItemModel.needVerfyCode);
      paramCreditCardViewPageModel = paramCreditCardViewPageModel.verifyNo;
      if (StringUtil.emptyOrNull(paramCreditCardViewPageModel))
      {
        localZ.b = 2131496112;
        localZ.a = 7;
        return localZ;
      }
      localObject = localZ;
    } while (paramCreditCardViewPageModel.length() == 6);
    localZ.b = 2131496100;
    localZ.a = 7;
    return localZ;
  }
  
  public static z a(PaymentCacheBean paramPaymentCacheBean, int paramInt, boolean paramBoolean)
  {
    Object localObject2 = null;
    if (paramPaymentCacheBean.isUseNewCreditCard) {}
    Object localObject1;
    for (CreditCardViewPageModel localCreditCardViewPageModel = paramPaymentCacheBean.creditViewModelOfNew;; localCreditCardViewPageModel = paramPaymentCacheBean.creditViewModelOfUesd)
    {
      localObject1 = localObject2;
      if (localCreditCardViewPageModel != null)
      {
        localObject1 = localObject2;
        if (localCreditCardViewPageModel.selectCreditCard != null)
        {
          localObject1 = localCreditCardViewPageModel.selectCreditCard;
          if (paramPaymentCacheBean.isUseNewCreditCard) {
            break label82;
          }
          if (!a(paramInt)) {
            break;
          }
          localObject1 = a(localCreditCardViewPageModel, paramBoolean);
        }
      }
      return localObject1;
    }
    return a((CreditCardViewItemModel)localObject1, localCreditCardViewPageModel.creditCardNo);
    label82:
    if (paramBoolean) {
      return a((CreditCardViewItemModel)localObject1, localCreditCardViewPageModel.creditCardNo, localCreditCardViewPageModel.cvvNo, ((CreditCardViewItemModel)localObject1).expireDate, localCreditCardViewPageModel.cardHolderName);
    }
    if (a(paramInt)) {
      return b(localCreditCardViewPageModel, paramBoolean);
    }
    return a((CreditCardViewItemModel)localObject1, localCreditCardViewPageModel.creditCardNo, localCreditCardViewPageModel.cvvNo, ((CreditCardViewItemModel)localObject1).expireDate, localCreditCardViewPageModel.cardHolderName, localCreditCardViewPageModel.idCard, localCreditCardViewPageModel.idCard.iDCardNo);
  }
  
  private static z a(String paramString, boolean paramBoolean, int paramInt)
  {
    z localZ = new z();
    localZ.a = paramInt;
    if (StringUtil.emptyOrNull(paramString)) {
      if (paramBoolean) {
        localZ.b = 2131493988;
      }
    }
    do
    {
      return localZ;
      localZ.b = 2131493987;
      return localZ;
      paramInt = paramString.length();
    } while ((paramInt >= 3) && (paramInt <= 4));
    if (paramBoolean)
    {
      localZ.b = 2131493990;
      return localZ;
    }
    localZ.b = 2131493989;
    return localZ;
  }
  
  public static OrderSubmitPaymentModel a(PaymentCacheBean paramPaymentCacheBean)
  {
    OrderSubmitPaymentModel localOrderSubmitPaymentModel = new OrderSubmitPaymentModel();
    if ((paramPaymentCacheBean.selectPayType & 0x1) == 1)
    {
      localOrderSubmitPaymentModel.isUseTravelMoney = true;
      localOrderSubmitPaymentModel.travelMoneyOfUsed.priceValue = paramPaymentCacheBean.travelMoneyOfUsedThisTime;
      localOrderSubmitPaymentModel.travelMoneyOfPassword = paramPaymentCacheBean.travelMoneyOfPassword;
      localOrderSubmitPaymentModel.travelMoneyOfPaymentWayID = paramPaymentCacheBean.travelMoneyOfPaymentWayID;
    }
    if ((paramPaymentCacheBean.selectPayType & 0x2) == 2) {
      localOrderSubmitPaymentModel.isUseCreditCard = true;
    }
    if ((paramPaymentCacheBean.selectPayType & 0x4) == 4)
    {
      localOrderSubmitPaymentModel.isUseThirdPay = true;
      localOrderSubmitPaymentModel.thirdPayMentWayID = paramPaymentCacheBean.thirdPayMentWayID;
      localOrderSubmitPaymentModel.selectDebitCardModel = paramPaymentCacheBean.selectDebitCardModel.clone();
      localOrderSubmitPaymentModel.selectDebitCardForPayment2 = paramPaymentCacheBean.selectDebitCardForPayment2.clone();
      localOrderSubmitPaymentModel.alipay_type = paramPaymentCacheBean.alipay_type;
    }
    if ((paramPaymentCacheBean.selectPayType & 0x1) == 1)
    {
      localOrderSubmitPaymentModel.isUseCoupon = false;
      localOrderSubmitPaymentModel.uesCouponAmount.priceValue = 0;
      localOrderSubmitPaymentModel.orderInfoModel = paramPaymentCacheBean.orderInfoModel.clone();
      localOrderSubmitPaymentModel.dispatchModel = paramPaymentCacheBean.dispatchModel.clone();
      if (!localOrderSubmitPaymentModel.isUseThirdPay)
      {
        if (!paramPaymentCacheBean.isUseNewCreditCard) {
          break label239;
        }
        localOrderSubmitPaymentModel.cardViewPageModel = paramPaymentCacheBean.creditViewModelOfNew.clone();
      }
    }
    for (localOrderSubmitPaymentModel.cardViewPageModel.isNewCard = true;; localOrderSubmitPaymentModel.cardViewPageModel.isNewCard = false)
    {
      localOrderSubmitPaymentModel.cardInfoID = paramPaymentCacheBean.cardInfoID;
      localOrderSubmitPaymentModel.referenceID = paramPaymentCacheBean.referenceID;
      return localOrderSubmitPaymentModel;
      localOrderSubmitPaymentModel.isUseCoupon = paramPaymentCacheBean.isUseCoupon;
      localOrderSubmitPaymentModel.uesCouponAmount.priceValue = paramPaymentCacheBean.couponAmountOfUsed.priceValue;
      break;
      label239:
      localOrderSubmitPaymentModel.cardViewPageModel = paramPaymentCacheBean.creditViewModelOfUesd.clone();
    }
  }
  
  public static void a(Message paramMessage, String paramString, as paramAs)
  {
    String str = (String)paramMessage.obj;
    switch (paramMessage.what)
    {
    default: 
      return;
    }
    for (;;)
    {
      try
      {
        int i = str.indexOf("resultStatus=");
        paramMessage = str.substring("resultStatus={".length() + i, str.indexOf("};memo="));
        if ((new e(str).a() == 1) || (paramAs == null)) {
          break;
        }
        if (paramMessage.equals("9000"))
        {
          paramAs.b(paramMessage);
          a(paramMessage, paramString);
          return;
        }
      }
      catch (Exception paramMessage)
      {
        paramMessage.printStackTrace();
        return;
      }
      if (paramMessage.equals("4000")) {
        paramAs.c(paramMessage);
      } else {
        paramAs.d(paramMessage);
      }
    }
  }
  
  public static void a(OrderSubmitPaymentModel paramOrderSubmitPaymentModel, String paramString, boolean paramBoolean, String... paramVarArgs)
  {
    if (paramOrderSubmitPaymentModel != null)
    {
      CreditCardViewPageModel localCreditCardViewPageModel = paramOrderSubmitPaymentModel.cardViewPageModel;
      if (localCreditCardViewPageModel != null)
      {
        if (!paramOrderSubmitPaymentModel.isUseCreditCard) {
          break label144;
        }
        if (!localCreditCardViewPageModel.isNewCard) {
          break label87;
        }
        if (!paramBoolean) {
          break label62;
        }
        c.b(paramString + "05037", new String[0]);
      }
    }
    label62:
    label87:
    label144:
    while (!paramOrderSubmitPaymentModel.isUseTravelMoney)
    {
      return;
      c.b(paramString + "05035", paramVarArgs);
      return;
      if (paramBoolean)
      {
        c.b(paramString + "01022", new String[0]);
        return;
      }
      c.b(paramString + "01020", paramVarArgs);
      return;
    }
    if (paramBoolean)
    {
      c.b(paramString + "04009", new String[0]);
      return;
    }
    c.b(paramString + "04010", paramVarArgs);
  }
  
  public static void a(PaymentCacheBean paramPaymentCacheBean, String paramString)
  {
    String str;
    if ((paramPaymentCacheBean != null) && (paramString.equals("location")))
    {
      str = "";
      if (!paramPaymentCacheBean.isUseNewCreditCard) {
        break label107;
      }
      paramString = str;
      if (paramPaymentCacheBean.creditViewModelOfNew != null)
      {
        paramString = str;
        if (paramPaymentCacheBean.creditViewModelOfNew.saveAsUsedCard)
        {
          paramString = str;
          if (paramPaymentCacheBean.creditViewModelOfNew.selectCreditCard != null) {
            paramString = paramPaymentCacheBean.creditViewModelOfNew.selectCreditCard.cardTypeId + "";
          }
        }
      }
    }
    for (;;)
    {
      if (!StringUtil.emptyOrNull(paramString)) {
        UserRecordUtil.getInstance().saveSelectRecord(paramPaymentCacheBean, aj.T, paramString);
      }
      return;
      label107:
      paramString = str;
      if (paramPaymentCacheBean.creditViewModelOfUesd != null)
      {
        paramString = str;
        if (paramPaymentCacheBean.creditViewModelOfUesd.selectCreditCard != null) {
          paramString = paramPaymentCacheBean.creditViewModelOfUesd.selectCreditCard.cardTypeId + "";
        }
      }
    }
  }
  
  public static void a(String paramString1, int paramInt, String paramString2, as paramAs)
  {
    Intent localIntent;
    if (paramInt == 0)
    {
      localIntent = new Intent(CtripBaseApplication.a(), OtherPayActivity.class);
      localIntent.putExtra("PAY_ACTION_CODE_PREFIX", paramString2);
      localIntent.putExtra(OtherPayActivity.b, paramString1);
      if (paramAs != null) {
        paramAs.a(localIntent);
      }
    }
    do
    {
      do
      {
        do
        {
          return;
        } while (1 != paramInt);
        if (!a()) {
          break;
        }
      } while ((StringUtil.emptyOrNull(paramString1)) || (paramAs == null));
      paramAs.a(paramString1);
      return;
      localIntent = new Intent(CtripBaseApplication.a(), OtherPayActivity.class);
      localIntent.putExtra("PAY_ACTION_CODE_PREFIX", paramString2);
      localIntent.putExtra(OtherPayActivity.b, paramString1);
    } while (paramAs == null);
    paramAs.a(localIntent);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    c.b(paramString2 + "02003", new String[] { paramString1 });
  }
  
  public static void a(String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      c.b(paramString + "01002", new String[0]);
      return;
    }
    c.b(paramString + "01003", new String[0]);
  }
  
  public static boolean a()
  {
    boolean bool2 = false;
    List localList = CtripBaseApplication.a().getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < localList.size())
      {
        if (((PackageInfo)localList.get(i)).packageName.equalsIgnoreCase("com.alipay.android.app")) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean a(int paramInt)
  {
    return (150 == paramInt) || (155 == paramInt) || (157 == paramInt) || (156 == paramInt);
  }
  
  public static boolean a(OrderSubmitPaymentModel paramOrderSubmitPaymentModel)
  {
    boolean bool2 = false;
    if (paramOrderSubmitPaymentModel != null) {}
    for (int i = paramOrderSubmitPaymentModel.alipay_type;; i = 0)
    {
      boolean bool1 = bool2;
      if (i == 0)
      {
        bool1 = bool2;
        if (!StringUtil.emptyOrNull(paramOrderSubmitPaymentModel.selectDebitCardModel.itemCode)) {
          bool1 = true;
        }
      }
      return bool1;
    }
  }
  
  public static boolean a(String paramString)
  {
    return Pattern.compile("^[一-龥a-zA-Z/ ]*$").matcher(paramString).matches();
  }
  
  private static z b(CreditCardViewItemModel paramCreditCardViewItemModel, String paramString)
  {
    z localZ = new z();
    localZ.a = 14;
    if ((paramCreditCardViewItemModel != null) && (paramCreditCardViewItemModel.needName))
    {
      if (!StringUtil.emptyOrNull(paramString)) {
        break label40;
      }
      localZ.b = 2131494073;
    }
    label40:
    do
    {
      return localZ;
      int i = StringUtil.getSBCCaseLength(paramString);
      if ((i < 4) || (i > 40))
      {
        localZ.b = 2131496124;
        localZ.c = true;
        return localZ;
      }
    } while (Pattern.compile("^[一-龥a-zA-Z/ ]*$").matcher(paramString).matches());
    localZ.b = 2131496124;
    localZ.c = true;
    return localZ;
  }
  
  private static z b(CreditCardViewPageModel paramCreditCardViewPageModel, boolean paramBoolean)
  {
    Object localObject2 = new z();
    Object localObject1 = localObject2;
    CreditCardViewItemModel localCreditCardViewItemModel;
    if (paramCreditCardViewPageModel != null)
    {
      localCreditCardViewItemModel = paramCreditCardViewPageModel.selectCreditCard;
      if (localCreditCardViewItemModel != null) {
        break label50;
      }
      ((z)localObject2).b = 2131496121;
      ((z)localObject2).a = 12;
      localObject1 = localObject2;
    }
    label50:
    do
    {
      do
      {
        do
        {
          z localZ;
          do
          {
            do
            {
              return localObject1;
              if (localCreditCardViewItemModel.cardTypeId == 0)
              {
                ((z)localObject2).b = 2131496121;
                ((z)localObject2).a = 12;
                return localObject2;
              }
              localObject1 = paramCreditCardViewPageModel.creditCardNo;
              if (StringUtil.emptyOrNull((String)localObject1))
              {
                localObject1 = localCreditCardViewItemModel.cardTypeCategory;
                if (localObject1 != null)
                {
                  if (PaymentCardTypeCategoryEnum.DC == localObject1)
                  {
                    ((z)localObject2).b = 2131496109;
                    ((z)localObject2).a = 13;
                    return localObject2;
                  }
                  ((z)localObject2).b = 2131494064;
                  ((z)localObject2).a = 13;
                  return localObject2;
                }
              }
              else
              {
                i = ((String)localObject1).length();
                localObject1 = localCreditCardViewItemModel.cardTypeCategory;
                if (localObject1 != null) {
                  if (PaymentCardTypeCategoryEnum.DC == localObject1)
                  {
                    if ((i < 12) || (i > 22))
                    {
                      ((z)localObject2).b = 2131496098;
                      ((z)localObject2).a = 13;
                      return localObject2;
                    }
                  }
                  else if ((i < 14) || (i > 16))
                  {
                    ((z)localObject2).b = 2131496123;
                    ((z)localObject2).a = 13;
                    return localObject2;
                  }
                }
              }
              if (!localCreditCardViewItemModel.needCVV) {
                break;
              }
              localObject2 = a(paramCreditCardViewPageModel.cvvNo, paramBoolean, 11);
              localObject1 = localObject2;
            } while (((z)localObject2).b != -1);
            localZ = b(localCreditCardViewItemModel, paramCreditCardViewPageModel.cardHolderName);
            localObject1 = localZ;
          } while (localZ.b != -1);
          int j = 0;
          localObject1 = localCreditCardViewItemModel.cardTypeCategory;
          int i = j;
          if (localObject1 != null)
          {
            i = j;
            if (PaymentCardTypeCategoryEnum.DC == localObject1) {
              i = 1;
            }
          }
          if ((i == 0) && (StringUtil.emptyOrNull(localCreditCardViewItemModel.expireDate)))
          {
            localZ.b = 2131496122;
            localZ.a = 15;
            return localZ;
          }
          localObject1 = paramCreditCardViewPageModel.idCard;
          localObject2 = localZ;
          if (localObject1 == null) {
            break;
          }
          localObject2 = localZ;
          if (!localCreditCardViewItemModel.needCardType) {
            break;
          }
          if (((IDCardChildModel)localObject1).iDCardType == 0)
          {
            localZ.b = 2131494159;
            localZ.a = 16;
            return localZ;
          }
          localObject2 = a(((IDCardChildModel)localObject1).iDCardType, ((IDCardChildModel)localObject1).iDCardNo);
          localObject1 = localObject2;
        } while (((z)localObject2).b != -1);
        if (localCreditCardViewItemModel.needPhoneNo)
        {
          localObject1 = localCreditCardViewItemModel.phoneNO;
          if (StringUtil.emptyOrNull((String)localObject1))
          {
            ((z)localObject2).b = 2131496111;
            ((z)localObject2).a = 19;
            return localObject2;
          }
          if (((String)localObject1).length() != 11)
          {
            ((z)localObject2).b = 2131496099;
            ((z)localObject2).a = 19;
            return localObject2;
          }
        }
        localObject1 = localObject2;
      } while (!localCreditCardViewItemModel.needVerfyCode);
      paramCreditCardViewPageModel = paramCreditCardViewPageModel.verifyNo;
      if (StringUtil.emptyOrNull(paramCreditCardViewPageModel))
      {
        ((z)localObject2).b = 2131496112;
        ((z)localObject2).a = 18;
        return localObject2;
      }
      localObject1 = localObject2;
    } while (paramCreditCardViewPageModel.length() == 6);
    ((z)localObject2).b = 2131496100;
    ((z)localObject2).a = 18;
    return localObject2;
  }
  
  public static void b(String paramString1, String paramString2)
  {
    c.b(paramString2 + "06003", new String[] { paramString1 });
  }
}
