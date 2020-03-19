package com.feelingk.iap.gui.parser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.Spanned;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.feelingk.iap.IAPLib;
import com.feelingk.iap.gui.data.PurchaseItem;
import com.feelingk.iap.net.ItemInfoConfirm;
import com.feelingk.iap.net.SellerInfoConfirm;
import com.feelingk.iap.util.CommonF;
import com.feelingk.iap.util.CommonF.LOGGER;
import com.feelingk.iap.util.CommonString;
import com.feelingk.iap.util.CommonString.Index;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.Assert;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParserXML
  extends Activity
{
  static final String TAG = "ParserXML";
  private static String mCultureLandID = null;
  private static boolean mCultureLoginFlag;
  private static LinearLayout mJumiLlView;
  private static Button mLguSmsAuthBtn;
  private static Boolean mLguSmsAuthClickFlag;
  private static Button mLguSmsAuthOkBtn;
  private static EditText mLguSmsAuthTv;
  private static Boolean mOCBPointQueryFlag;
  public static String mOTPNumber;
  private static StateListDrawable mReClaimDrawables;
  private static StateListDrawable mSmsAuthDrawables;
  private static Boolean nextStep;
  static View.OnClickListener okLguSmsAuthBtn = new View.OnClickListener()
  {
    public void onClick(final View paramAnonymousView)
    {
      paramAnonymousView.setEnabled(false);
      new Handler().post(new Runnable()
      {
        public void run()
        {
          paramAnonymousView.setEnabled(true);
        }
      });
      ParserXML.onLguSmsAuthCallback.onLguSmsAuthOK();
      ParserXML.mLguSmsAuthClickFlag = Boolean.valueOf(false);
    }
  };
  private static ParserLguSmsAuthCallback onLguSmsAuthCallback = null;
  private static boolean sendDotoriSmsFlag;
  private String RES_VERT_FILE_PATH = "/res/";
  private String XML_FILE_NAME = "purchase";
  private String XML_FILE_PATH = "/xml";
  private String XML_FILE_PATH_KTLG = "/xml_kt_lg";
  View.OnClickListener autoPurchaseFormBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ParserXML.this.mPurchaseCheckList[0] != 0)
      {
        ParserXML.this.onAutoPurchaseFormResultCallback.onAutoPurchaseFormDialogButtonClick(true);
        return;
      }
      ParserXML.this.onAutoPurchaseFormResultCallback.onAutoPurchaseFormDialogButtonClick(false);
    }
  };
  View.OnClickListener cancelAuthBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ParserXML.this.onAuthResultCallback != null) {
        ParserXML.this.onAuthResultCallback.onAuthDialogCancelButtonClick();
      }
      do
      {
        return;
        if (ParserXML.this.onOcbCallback != null)
        {
          ParserXML.this.onOcbCallback.onOCBPWDCancelButtonClick();
          return;
        }
        if (ParserXML.this.onCultureLandCallback != null)
        {
          ParserXML.this.onCultureLandCallback.onCultureLandCancelButtonClick();
          return;
        }
      } while (ParserXML.this.onDotoriSmsAuthCallback == null);
      ParserXML.this.onDotoriSmsAuthCallback.onDotoriSmsAuthCancelButtonClick();
    }
  };
  View.OnClickListener cancelAutoPurchaseFormBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onAutoPurchaseFormResultCallback.onAutoPurchaseFormDialogCancelButtonClick();
    }
  };
  View.OnClickListener cancelBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.chargeAmountInit();
      ParserXML.this.onResultCallback.onPurchaseCancelButtonClick();
    }
  };
  View.OnClickListener cancelJoinBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onJoinResultCallback.onJoinDialogCancelButtonClick();
    }
  };
  View.OnClickListener cancelLguSmsAuthBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.onLguSmsAuthCallback.onLguSmsAuthCancer();
      ParserXML.mLguSmsAuthClickFlag = Boolean.valueOf(false);
    }
  };
  View.OnClickListener cancelYesNoBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onYesNoResultCallback.onYesNoDialogCancelButtonClick();
    }
  };
  CompoundButton.OnCheckedChangeListener changeCheckBox = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
    {
      paramAnonymousCompoundButton = (String)paramAnonymousCompoundButton.getTag();
      if ("info_checkbox1".equals(paramAnonymousCompoundButton))
      {
        ParserXML.this.mJoinCheckList[0] = paramAnonymousBoolean;
        if ("purchase_checkbox".equals(paramAnonymousCompoundButton))
        {
          CommonF.LOGGER.i("ParserXML", "autoPurchase flag: " + paramAnonymousBoolean);
          if (!ParserXML.this.mItemPurchaseItemInfo.AfterAutoPurchaseInfoAgree) {
            break label371;
          }
          if (!ParserXML.this.mAfterAutoPurchaseInfoAgree) {
            break label340;
          }
          ParserXML.this.mPurchaseCheckList[0] = 0;
          ParserXML.this.mAfterAutoPurchaseInfoAgree = false;
        }
        label98:
        if ("auto_purchase_checkbox".equals(paramAnonymousCompoundButton))
        {
          CommonF.LOGGER.i("ParserXML", "autoPurchaseForm flag: " + paramAnonymousBoolean);
          ParserXML.this.mPurchaseCheckList[0] = paramAnonymousBoolean;
        }
        if ("imei_auth_checkbox".equals(paramAnonymousCompoundButton))
        {
          CommonF.LOGGER.i("ParserXML", "imei_auth_checkbox flag: " + paramAnonymousBoolean);
          ParserXML.this.mIMEICheckList[0] = paramAnonymousBoolean;
          if (ParserXML.this.mIMEICheckList[0] == 0) {
            break label397;
          }
          ParserXML.this.mIMEIOkBtn.setBackgroundDrawable(ParserXML.this.mIMEICheckedDrawbles);
          ParserXML.this.mIMEIOkBtn.setOnClickListener(ParserXML.this.imeiAuthBtn);
        }
        label224:
        if ("otp_checkbox".equals(paramAnonymousCompoundButton))
        {
          CommonF.LOGGER.i("ParserXML", "setOTPAgree flag: " + paramAnonymousBoolean);
          if (!paramAnonymousBoolean) {
            break label428;
          }
          IAPLib.setOTPAgree(true);
        }
      }
      for (;;)
      {
        if ("culture_login_checkbox".equals(paramAnonymousCompoundButton))
        {
          CommonF.LOGGER.i("ParserXML", "culture_login_checkbox flag: " + paramAnonymousBoolean);
          if (!paramAnonymousBoolean) {
            break label439;
          }
          ParserXML.this.mCultureCheckFlag = true;
        }
        return;
        if ("info_checkbox2".equals(paramAnonymousCompoundButton))
        {
          ParserXML.this.mJoinCheckList[1] = paramAnonymousBoolean;
          break;
        }
        ParserXML.this.mJoinCheckList[2] = paramAnonymousBoolean;
        break;
        label340:
        if (ParserXML.this.mAfterAutoPurchaseInfoAgree) {
          break label98;
        }
        ParserXML.this.mPurchaseCheckList[0] = 1;
        ParserXML.this.mAfterAutoPurchaseInfoAgree = true;
        break label98;
        label371:
        if (ParserXML.this.mItemPurchaseItemInfo.AfterAutoPurchaseInfoAgree) {
          break label98;
        }
        ParserXML.this.mPurchaseCheckList[0] = paramAnonymousBoolean;
        break label98;
        label397:
        ParserXML.this.mIMEIOkBtn.setBackgroundDrawable(ParserXML.this.mIMEInotCheckedDrawble);
        ParserXML.this.mIMEIOkBtn.setOnClickListener(null);
        break label224;
        label428:
        if (!paramAnonymousBoolean) {
          IAPLib.setOTPAgree(false);
        }
      }
      label439:
      ParserXML.this.mCultureCheckFlag = false;
    }
  };
  View.OnClickListener commonBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ParserXML.this.onOcbCallback != null)
      {
        ParserXML.this.mOCBCardNum = (ParserXML.this.m_OCBRegText1.getText().toString() + ParserXML.this.m_OCBRegText2.getText().toString() + ParserXML.this.m_OCBRegText3.getText().toString() + ParserXML.this.m_OCBRegText4.getText().toString());
        ParserXML.this.onOcbCallback.onOCBRegistrationButtonClick(ParserXML.this.mOCBCardNum);
      }
      while (ParserXML.this.onDotoriSmsAuthCallback == null) {
        return;
      }
      ParserXML.sendDotoriSmsFlag = true;
      ParserXML.this.onDotoriSmsAuthCallback.onDotoriSmsAuthSMSReceiveButtonClick();
    }
  };
  private Context context = null;
  boolean cursorFlag = true;
  private StateListDrawable dotoriInactive;
  private int foreignInputCarrier = 0;
  View.OnClickListener getlguSmsAuthBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.mLguSmsAuthBtn.setText(CommonString.getString(CommonString.Index.MENT_NUMBER_RECLAIMED));
      ParserXML.mLguSmsAuthBtn.setTextColor(Color.parseColor("#999999"));
      ParserXML.mLguSmsAuthClickFlag = Boolean.valueOf(true);
      if (ParserXML.onLguSmsAuthCallback.onGetLguSmsAuthTime() != null)
      {
        long l = System.currentTimeMillis();
        paramAnonymousView = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(l));
        String str = ParserXML.onLguSmsAuthCallback.onGetLguSmsAuthTime();
        CommonF.LOGGER.i("ParserXML", "currTime: " + paramAnonymousView);
        CommonF.LOGGER.i("ParserXML", "oldTime: " + str);
        if (CommonF.getTimeDifference(str, paramAnonymousView) < 180L)
        {
          ParserXML.onLguSmsAuthCallback.onErrorPopup();
          CommonF.LOGGER.e("ParserXML", "LguSmsAuthNumberReq Fail");
          return;
        }
        ParserXML.onLguSmsAuthCallback.onLguSmsAuthNumberReq();
        CommonF.LOGGER.i("ParserXML", "LguSmsAuthNumberReq Flow1 Start ");
        ParserXML.onLguSmsAuthCallback.onSetLguSmsAuthTime(true);
        return;
      }
      ParserXML.onLguSmsAuthCallback.onLguSmsAuthNumberReq();
      CommonF.LOGGER.i("ParserXML", "LguSmsAuthNumberReq Flow2 Start ");
      ParserXML.onLguSmsAuthCallback.onSetLguSmsAuthTime(true);
    }
  };
  private int idg;
  private Hashtable<String, Integer> ids = null;
  View.OnClickListener imageBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onImageResultCallback.onImageDialogButtonClick();
    }
  };
  View.OnClickListener imeiAuthBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onImeiAuthCallback.onIMEIAuthDialogOKButtonClick();
    }
  };
  View.OnClickListener imeiAuthCancelBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onImeiAuthCallback.onIMEIAuthDialogCancelButtonClick();
    }
  };
  private Stack<ViewGroup> layoutStack = null;
  private StateListDrawable mActiveCultureDrawables;
  private StateListDrawable mActiveDotoriDrawables;
  private StateListDrawable mActiveDrawables;
  private StateListDrawable mActiveOCBRegDrawables;
  Drawable mActiveOver;
  private StateListDrawable mActiveTcashDrawables;
  private boolean mAfterAutoPurchaseInfoAgree = true;
  InputStream mAuthOkStream = null;
  Drawable mAuthOkbtOn = null;
  Drawable mAuthOkbtOver = null;
  private boolean mAutoPurchaseFormPopupMode = false;
  private Button mBtn;
  private Button mCultureBtn;
  private boolean mCultureCheckFlag = false;
  private Boolean mCultureFlag = Boolean.valueOf(false);
  private boolean mCultureLandLoginPopupMode = false;
  private CultureLandTextWatcher mCultureLandTextWatcher = new CultureLandTextWatcher(null);
  private Button mCultureOKButton;
  private TextView mCultureText;
  private int mCultureUse = 0;
  private Button mDotoriBtn;
  private Boolean mDotoriFlag = Boolean.valueOf(false);
  private boolean mDotoriQueryFlag = false;
  private boolean mDotoriSmsAuthPopupMode = false;
  private TextView mDotoriText;
  private int mDotoriUse = 0;
  private StateListDrawable mDrawables;
  boolean mFlag = false;
  private ForeignDevelopeTextLengthWatcher mForeignDevelopeTextLengthWatcher = new ForeignDevelopeTextLengthWatcher(null);
  private String mFormName = null;
  private boolean mIMEIAuthPopupMode = false;
  private boolean[] mIMEICheckList = new boolean[1];
  private StateListDrawable mIMEICheckedDrawbles;
  private Button mIMEIOkBtn = null;
  private StateListDrawable mIMEInotCheckedDrawble;
  private boolean mImageConfirmPopupMode = false;
  private StateListDrawable mInactiveCultureDrawables;
  private StateListDrawable mInactiveDotoriDrawables;
  private StateListDrawable mInactiveDrawables;
  private StateListDrawable mInactiveOCBRegDrawables;
  Drawable mInactiveOn = null;
  private StateListDrawable mInactiveTcashDrawables;
  private String mInfoMessage = null;
  private ItemInfoConfirm mItemInfoConfirm = null;
  private PurchaseItem mItemPurchaseItemInfo = null;
  private boolean[] mJoinCheckList = new boolean[3];
  private boolean mJoinPopupMode = false;
  private boolean mJuminPopupMode = false;
  private boolean mLGUSmsAuthPopupMode = false;
  private StateListDrawable mLiminExcessDrawables;
  InputStream mLiminExcessStream;
  Drawable mLimitExcessbtOn = null;
  Drawable mLimitExcessbtOver = null;
  private Button mOCBBtn;
  private String mOCBCardNum;
  private Boolean mOCBFlag = Boolean.valueOf(false);
  private Button mOCBRegBtn = null;
  private boolean mOCBRegPopupMode = false;
  private TextView mOCBText;
  private int mOCBUse = 0;
  private Button mOKCashbackOKButton;
  private Button mOkBtn;
  private StateListDrawable mOkDrawbles;
  private boolean mOtpPopupMode = false;
  private int mPaymentTotal = 0;
  private View.OnClickListener mPopupClickListener = null;
  private int mPostPay = 0;
  private boolean[] mPurchaseCheckList = new boolean[1];
  InputStream mReClaimStream;
  InputStream mStream;
  private int mTCashUse = 0;
  private Button mTcashBtn;
  private Boolean mTcashFlag = Boolean.valueOf(false);
  private TextView mTcashText = null;
  private int mTotalBalance = 0;
  private boolean mYesNoPopupMode = false;
  private TextView m_AccountPriceTextView = null;
  private EditText m_CultureLandID = null;
  private EditText m_CultureLandPW = null;
  private EditText m_DotoriSMSAuthNum = null;
  private EditText m_JuminText1 = null;
  private EditText m_JuminText2 = null;
  private EditText m_MDN1 = null;
  private EditText m_MDN2 = null;
  private EditText m_MDN3 = null;
  private EditText m_OCBPWText = null;
  private EditText m_OCBRegText1 = null;
  private EditText m_OCBRegText2 = null;
  private EditText m_OCBRegText3 = null;
  private EditText m_OCBRegText4 = null;
  private TextView m_discountTextView = null;
  Drawable mbtOn = null;
  Drawable mbtOver = null;
  View.OnClickListener moreInfo = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onResultCallback.onAutoPurchaseInfoClick(CommonString.getString(CommonString.Index.WORK_AUTO_PURCHASE_INFONAME), CommonString.getString(CommonString.Index.WORK_AUTO_FORM_STRING));
    }
  };
  View.OnClickListener moreInfoFormBtn1 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onJoinResultCallback.onJoinFormDialogPopupClick(1);
    }
  };
  View.OnClickListener moreInfoFormBtn2 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onJoinResultCallback.onJoinFormDialogPopupClick(2);
    }
  };
  View.OnClickListener moreInfoFormBtn3 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.onJoinResultCallback.onJoinFormDialogPopupClick(3);
    }
  };
  private boolean mrForeignInputMDNMode = false;
  private Button octChange;
  private Button octDel;
  View.OnClickListener okAuthBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ParserXML.this.onAuthResultCallback != null)
      {
        int i = ParserXML.this.m_JuminText1.getText().length();
        int j = ParserXML.this.m_JuminText2.getText().length();
        if ((i == 6) && (j == 7)) {
          ParserXML.this.onAuthResultCallback.onAuthDialogOKButtonClick(ParserXML.this.m_JuminText1.getText().toString(), ParserXML.this.m_JuminText2.getText().toString());
        }
      }
      do
      {
        do
        {
          return;
          Toast.makeText(ParserXML.this.context, CommonString.getString(CommonString.Index.ERROR_JUMIN_NUMBER_LENGTH), 0).show();
          return;
          if (ParserXML.this.onOcbCallback != null)
          {
            paramAnonymousView = CommonF.getSTRFilter(ParserXML.this.m_OCBPWText.getText().toString());
            ParserXML.this.onOcbCallback.onOCBPWDOKButtonClick(paramAnonymousView);
            return;
          }
          if (ParserXML.this.onCultureLandCallback == null) {
            break;
          }
          paramAnonymousView = CommonF.getSTRFilter(ParserXML.this.m_CultureLandID.getText().toString());
          str = CommonF.getSTRFilter(ParserXML.this.m_CultureLandPW.getText().toString());
          ParserXML.this.onCultureLandCallback.onCultureLandButtonClick(paramAnonymousView, str);
          if (ParserXML.this.mCultureCheckFlag) {
            ParserXML.mCultureLoginFlag = true;
          }
        } while (!ParserXML.mCultureLoginFlag);
        ParserXML.mCultureLandID = ParserXML.this.m_CultureLandID.getText().toString();
        return;
        if (ParserXML.this.onDotoriSmsAuthCallback != null)
        {
          IAPLib.setDotoriSmsNumber(ParserXML.this.m_DotoriSMSAuthNum.getText().toString());
          ParserXML.this.onDotoriSmsAuthCallback.onDotoriSmsAuthOKButtonClick();
          ParserXML.sendDotoriSmsFlag = false;
          return;
        }
      } while (ParserXML.this.onForeignInputMDNResultCallback == null);
      paramAnonymousView = ParserXML.this.m_MDN1.getText().toString() + ParserXML.this.m_MDN2.getText().toString() + ParserXML.this.m_MDN3.getText().toString();
      String str = ParserXML.this.m_JuminText1.getText().toString() + ParserXML.this.m_JuminText2.getText().toString();
      ParserXML.this.onForeignInputMDNResultCallback.onForeignInputMDNOKButtonClick(ParserXML.this.foreignInputCarrier, paramAnonymousView, str);
    }
  };
  View.OnClickListener okBtn = new View.OnClickListener()
  {
    public void onClick(final View paramAnonymousView)
    {
      paramAnonymousView.setEnabled(false);
      new Handler().post(new Runnable()
      {
        public void run()
        {
          paramAnonymousView.setEnabled(true);
        }
      });
      if (ParserXML.this.mItemPurchaseItemInfo.AutoPurchaseCheck)
      {
        if (ParserXML.this.mPurchaseCheckList[0] != 0)
        {
          ParserXML.this.onResultCallback.onPurchaseAutoButtonClick();
          ParserXML.this.mPurchaseCheckList = new boolean[1];
          ParserXML.this.mItemPurchaseItemInfo.AfterAutoPurchaseInfoAgree = false;
          return;
        }
        ParserXML.this.onResultCallback.onPurchaseAutoCancelButtonClick("error");
        return;
      }
      ParserXML.this.onResultCallback.onPurchaseAutoButtonClick();
      IAPLib.setPayments(ParserXML.this.mOCBUse, ParserXML.this.mDotoriUse, ParserXML.this.mCultureUse, ParserXML.this.mTCashUse);
      ParserXML.this.chargeAmountInit();
    }
  };
  View.OnClickListener okJoinBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((ParserXML.this.mJoinCheckList[0] != 0) && (ParserXML.this.mJoinCheckList[1] != 0) && (ParserXML.this.mJoinCheckList[2] != 0))
      {
        ParserXML.this.onJoinResultCallback.onJoinDialogOKButtonClick("join");
        ParserXML.this.mJoinCheckList = new boolean[3];
        return;
      }
      ParserXML.this.onJoinResultCallback.onJoinDialogOKButtonClick("error");
    }
  };
  View.OnClickListener okMessageBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ParserXML.this.mPopupClickListener.onClick(paramAnonymousView);
    }
  };
  View.OnClickListener okOtpBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      paramAnonymousView = ParserXML.this.context.getPackageManager().getInstalledApplications(0);
      int j = paramAnonymousView.size();
      int i = 0;
      for (;;)
      {
        if (i >= j)
        {
          if (!ParserXML.this.tStoreFlag) {
            break label272;
          }
          paramAnonymousView = null;
        }
        try
        {
          localObject = ParserXML.this.context.getPackageManager().getPackageInfo("com.skt.skaf.A000Z00040", 0);
          paramAnonymousView = (View)localObject;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            Object localObject;
            localNameNotFoundException.printStackTrace();
          }
          paramAnonymousView = new Intent();
          paramAnonymousView.addFlags(536870912);
          paramAnonymousView.setClassName("com.skt.skaf.A000Z00040", "com.skt.skaf.A000Z00040.A000Z00040");
          paramAnonymousView.setAction("COLLAB_ACTION");
          paramAnonymousView.putExtra("com.skt.skaf.COL.URI", "SETTING_VIEW".getBytes());
          paramAnonymousView.putExtra("com.skt.skaf.COL.REQUESTER", "A000Z00040");
          ParserXML.this.context.startActivity(paramAnonymousView);
          return;
        }
        localObject = paramAnonymousView.versionName;
        i = paramAnonymousView.versionCode;
        CommonF.LOGGER.i("ParserXML", "application versionName : " + (String)localObject);
        CommonF.LOGGER.i("ParserXML", "application versionCode : " + i);
        if (i >= 19) {
          break;
        }
        ParserXML.this.onOtpCallback.onOtpErrorPopup();
        return;
        if (((ApplicationInfo)paramAnonymousView.get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0)
        {
          ParserXML.this.tStoreFlag = true;
          CommonF.LOGGER.i("ParserXML", "티스토어 설치여부: " + ParserXML.this.tStoreFlag);
        }
        i += 1;
      }
      label272:
      ParserXML.this.onOtpCallback.onOtpTstoreButtonClick();
    }
  };
  View.OnClickListener okYesNoBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ParserXML.this.tStoreFlag)
      {
        ParserXML.this.onJoinResultCallback.onJoinDialogOKButtonClick("error");
        return;
      }
      ParserXML.this.onYesNoResultCallback.onYesNoDialogOKButtonClick();
    }
  };
  private ParserAuthResultCallback onAuthResultCallback = null;
  private ParserAutoPurchaseFormResultCallback onAutoPurchaseFormResultCallback = null;
  private ParserCultureLandCallback onCultureLandCallback = null;
  private ParserDotoriSmsAuthCallback onDotoriSmsAuthCallback = null;
  OnClickForeignInputMDN onForeignInputMDNClickLisener = new OnClickForeignInputMDN();
  private ParserForeignInputMDNResultCallback onForeignInputMDNResultCallback = null;
  private ParserImageResultCallback onImageResultCallback = null;
  private ParserIMEIAuthCallback onImeiAuthCallback = null;
  private ParserJoinResultCallback onJoinResultCallback = null;
  private ParserOCBCallback onOcbCallback = null;
  private ParserOtpCallback onOtpCallback = null;
  private ParserResultCallback onResultCallback = null;
  private ParserYesNoResultCallback onYesNoResultCallback = null;
  private int orientation = 0;
  private String otpAuthNumber = "";
  private int strNum;
  private int strNumOCBRegText1;
  private int strNumOCBRegText2;
  private int strNumOCBRegText3;
  private boolean tStoreFlag = false;
  View.OnClickListener tStoreInfoBtn = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ParserXML.this.tStoreFlag)
      {
        if (ParserXML.this.onResultCallback != null)
        {
          ParserXML.this.onResultCallback.onEnterTstore();
          return;
        }
        ParserXML.onLguSmsAuthCallback.onEnterTstore();
        return;
      }
      if (ParserXML.this.onResultCallback != null)
      {
        ParserXML.this.onResultCallback.onTstoreLockError(CommonString.getString(CommonString.Index.DLG_TSTORE_NOT_INSTALLED_STRING));
        return;
      }
      ParserXML.onLguSmsAuthCallback.onTstoreLockError(CommonString.getString(CommonString.Index.DLG_TSTORE_NOT_INSTALLED_STRING));
    }
  };
  boolean xperiacheckbox = false;
  
  static
  {
    mJumiLlView = null;
    mLguSmsAuthTv = null;
    mLguSmsAuthBtn = null;
    mLguSmsAuthOkBtn = null;
    mOTPNumber = null;
    mLguSmsAuthClickFlag = Boolean.valueOf(false);
    nextStep = Boolean.valueOf(false);
    mOCBPointQueryFlag = Boolean.valueOf(false);
    sendDotoriSmsFlag = false;
    mCultureLoginFlag = false;
  }
  
  public ParserXML(Context paramContext)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
  }
  
  public ParserXML(Context paramContext, ParserAuthResultCallback paramParserAuthResultCallback, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onAuthResultCallback = paramParserAuthResultCallback;
    this.mJuminPopupMode = paramBoolean;
  }
  
  public ParserXML(Context paramContext, ParserAutoPurchaseFormResultCallback paramParserAutoPurchaseFormResultCallback, int paramInt, String paramString, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onAutoPurchaseFormResultCallback = paramParserAutoPurchaseFormResultCallback;
    if ("AutoPurchaseForm".equals(paramString)) {
      this.mAutoPurchaseFormPopupMode = paramBoolean;
    }
  }
  
  public ParserXML(Context paramContext, ParserCultureLandCallback paramParserCultureLandCallback, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onCultureLandCallback = paramParserCultureLandCallback;
    this.mCultureLandLoginPopupMode = paramBoolean;
  }
  
  public ParserXML(Context paramContext, ParserDotoriSmsAuthCallback paramParserDotoriSmsAuthCallback, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onDotoriSmsAuthCallback = paramParserDotoriSmsAuthCallback;
    this.mDotoriSmsAuthPopupMode = paramBoolean;
  }
  
  public ParserXML(Context paramContext, ParserForeignInputMDNResultCallback paramParserForeignInputMDNResultCallback)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onForeignInputMDNResultCallback = paramParserForeignInputMDNResultCallback;
    this.mrForeignInputMDNMode = true;
  }
  
  public ParserXML(Context paramContext, ParserIMEIAuthCallback paramParserIMEIAuthCallback, String paramString, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onImeiAuthCallback = paramParserIMEIAuthCallback;
    if ("IMEIAuthForm".equals(paramString)) {
      this.mIMEIAuthPopupMode = paramBoolean;
    }
  }
  
  public ParserXML(Context paramContext, ParserImageResultCallback paramParserImageResultCallback, int paramInt, String paramString, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onImageResultCallback = paramParserImageResultCallback;
    if ("PermissionPopup".equals(paramString)) {
      this.mImageConfirmPopupMode = paramBoolean;
    }
  }
  
  public ParserXML(Context paramContext, ParserJoinResultCallback paramParserJoinResultCallback, int paramInt, String paramString, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onJoinResultCallback = paramParserJoinResultCallback;
    if ("Join".equals(paramString)) {
      this.mJoinPopupMode = paramBoolean;
    }
  }
  
  public ParserXML(Context paramContext, ParserLguSmsAuthCallback paramParserLguSmsAuthCallback, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    onLguSmsAuthCallback = paramParserLguSmsAuthCallback;
    this.mLGUSmsAuthPopupMode = paramBoolean;
  }
  
  public ParserXML(Context paramContext, ParserOCBCallback paramParserOCBCallback, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onOcbCallback = paramParserOCBCallback;
    this.mOCBRegPopupMode = paramBoolean;
  }
  
  public ParserXML(Context paramContext, ParserOtpCallback paramParserOtpCallback, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onOtpCallback = paramParserOtpCallback;
    this.mOtpPopupMode = paramBoolean;
  }
  
  public ParserXML(Context paramContext, ParserResultCallback paramParserResultCallback)
  {
    this(paramContext);
    this.onResultCallback = paramParserResultCallback;
  }
  
  public ParserXML(Context paramContext, ParserResultCallback paramParserResultCallback, int paramInt, boolean paramBoolean)
  {
    this(paramContext);
    this.onResultCallback = paramParserResultCallback;
  }
  
  public ParserXML(Context paramContext, ParserResultCallback paramParserResultCallback, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext);
    this.onResultCallback = paramParserResultCallback;
    this.mJuminPopupMode = paramBoolean2;
  }
  
  public ParserXML(Context paramContext, ParserYesNoResultCallback paramParserYesNoResultCallback, int paramInt, String paramString, boolean paramBoolean)
  {
    this.context = paramContext;
    this.layoutStack = new Stack();
    this.ids = new Hashtable();
    this.onYesNoResultCallback = paramParserYesNoResultCallback;
    if ("YesNo".equals(paramString)) {
      this.mYesNoPopupMode = paramBoolean;
    }
  }
  
  /* Error */
  private View Start(String paramString)
  {
    // Byte code:
    //   0: invokestatic 719	org/xmlpull/v1/XmlPullParserFactory:newInstance	()Lorg/xmlpull/v1/XmlPullParserFactory;
    //   3: invokevirtual 723	org/xmlpull/v1/XmlPullParserFactory:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   6: astore_3
    //   7: aload_0
    //   8: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   11: aload_1
    //   12: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   15: astore_2
    //   16: aload_3
    //   17: aload_2
    //   18: ldc_w 737
    //   21: invokeinterface 743 3 0
    //   26: aload_0
    //   27: getfield 459	com/feelingk/iap/gui/parser/ParserXML:mJuminPopupMode	Z
    //   30: ifne +24 -> 54
    //   33: aload_0
    //   34: getfield 475	com/feelingk/iap/gui/parser/ParserXML:mOCBRegPopupMode	Z
    //   37: ifne +17 -> 54
    //   40: aload_0
    //   41: getfield 477	com/feelingk/iap/gui/parser/ParserXML:mCultureLandLoginPopupMode	Z
    //   44: ifne +10 -> 54
    //   47: aload_0
    //   48: getfield 479	com/feelingk/iap/gui/parser/ParserXML:mDotoriSmsAuthPopupMode	Z
    //   51: ifeq +15 -> 66
    //   54: aload_0
    //   55: aload_3
    //   56: invokespecial 747	com/feelingk/iap/gui/parser/ParserXML:inflateAuthPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   59: astore_1
    //   60: aload_2
    //   61: invokevirtual 752	java/io/InputStream:close	()V
    //   64: aload_1
    //   65: areturn
    //   66: aload_0
    //   67: getfield 481	com/feelingk/iap/gui/parser/ParserXML:mrForeignInputMDNMode	Z
    //   70: ifeq +12 -> 82
    //   73: aload_0
    //   74: aload_3
    //   75: invokespecial 747	com/feelingk/iap/gui/parser/ParserXML:inflateAuthPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   78: astore_1
    //   79: goto -19 -> 60
    //   82: aload_0
    //   83: getfield 461	com/feelingk/iap/gui/parser/ParserXML:mYesNoPopupMode	Z
    //   86: ifeq +16 -> 102
    //   89: aload_0
    //   90: aload_3
    //   91: aload_0
    //   92: getfield 451	com/feelingk/iap/gui/parser/ParserXML:mInfoMessage	Ljava/lang/String;
    //   95: invokespecial 756	com/feelingk/iap/gui/parser/ParserXML:inflateYesNoPopup	(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;)Landroid/view/View;
    //   98: astore_1
    //   99: goto -39 -> 60
    //   102: aload_0
    //   103: getfield 463	com/feelingk/iap/gui/parser/ParserXML:mImageConfirmPopupMode	Z
    //   106: ifeq +16 -> 122
    //   109: aload_0
    //   110: aload_3
    //   111: aload_0
    //   112: getfield 451	com/feelingk/iap/gui/parser/ParserXML:mInfoMessage	Ljava/lang/String;
    //   115: invokespecial 759	com/feelingk/iap/gui/parser/ParserXML:inflateImagePopup	(Lorg/xmlpull/v1/XmlPullParser;Ljava/lang/String;)Landroid/view/View;
    //   118: astore_1
    //   119: goto -59 -> 60
    //   122: aload_0
    //   123: getfield 465	com/feelingk/iap/gui/parser/ParserXML:mAutoPurchaseFormPopupMode	Z
    //   126: ifeq +12 -> 138
    //   129: aload_0
    //   130: aload_3
    //   131: invokespecial 762	com/feelingk/iap/gui/parser/ParserXML:inflateAutoPurchaseFormPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   134: astore_1
    //   135: goto -75 -> 60
    //   138: aload_0
    //   139: getfield 473	com/feelingk/iap/gui/parser/ParserXML:mIMEIAuthPopupMode	Z
    //   142: ifeq +12 -> 154
    //   145: aload_0
    //   146: aload_3
    //   147: invokespecial 765	com/feelingk/iap/gui/parser/ParserXML:inflatIMEIAuthPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   150: astore_1
    //   151: goto -91 -> 60
    //   154: aload_0
    //   155: getfield 467	com/feelingk/iap/gui/parser/ParserXML:mJoinPopupMode	Z
    //   158: ifeq +12 -> 170
    //   161: aload_0
    //   162: aload_3
    //   163: invokespecial 768	com/feelingk/iap/gui/parser/ParserXML:inflateJoinPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   166: astore_1
    //   167: goto -107 -> 60
    //   170: aload_0
    //   171: getfield 469	com/feelingk/iap/gui/parser/ParserXML:mOtpPopupMode	Z
    //   174: ifeq +12 -> 186
    //   177: aload_0
    //   178: aload_3
    //   179: invokespecial 771	com/feelingk/iap/gui/parser/ParserXML:inflateOtpPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   182: astore_1
    //   183: goto -123 -> 60
    //   186: aload_0
    //   187: getfield 471	com/feelingk/iap/gui/parser/ParserXML:mLGUSmsAuthPopupMode	Z
    //   190: ifeq +12 -> 202
    //   193: aload_0
    //   194: aload_3
    //   195: invokespecial 774	com/feelingk/iap/gui/parser/ParserXML:inflateLguSmsAuthPopup	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   198: astore_1
    //   199: goto -139 -> 60
    //   202: aload_0
    //   203: aload_3
    //   204: invokespecial 777	com/feelingk/iap/gui/parser/ParserXML:inflate	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/view/View;
    //   207: astore_1
    //   208: goto -148 -> 60
    //   211: astore_1
    //   212: aload_1
    //   213: invokevirtual 780	org/xmlpull/v1/XmlPullParserException:printStackTrace	()V
    //   216: aconst_null
    //   217: areturn
    //   218: astore_1
    //   219: aload_1
    //   220: invokevirtual 781	java/io/IOException:printStackTrace	()V
    //   223: goto -7 -> 216
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	226	0	this	ParserXML
    //   0	226	1	paramString	String
    //   15	46	2	localInputStream	InputStream
    //   6	198	3	localXmlPullParser	XmlPullParser
    // Exception table:
    //   from	to	target	type
    //   0	54	211	org/xmlpull/v1/XmlPullParserException
    //   54	60	211	org/xmlpull/v1/XmlPullParserException
    //   60	64	211	org/xmlpull/v1/XmlPullParserException
    //   66	79	211	org/xmlpull/v1/XmlPullParserException
    //   82	99	211	org/xmlpull/v1/XmlPullParserException
    //   102	119	211	org/xmlpull/v1/XmlPullParserException
    //   122	135	211	org/xmlpull/v1/XmlPullParserException
    //   138	151	211	org/xmlpull/v1/XmlPullParserException
    //   154	167	211	org/xmlpull/v1/XmlPullParserException
    //   170	183	211	org/xmlpull/v1/XmlPullParserException
    //   186	199	211	org/xmlpull/v1/XmlPullParserException
    //   202	208	211	org/xmlpull/v1/XmlPullParserException
    //   0	54	218	java/io/IOException
    //   54	60	218	java/io/IOException
    //   60	64	218	java/io/IOException
    //   66	79	218	java/io/IOException
    //   82	99	218	java/io/IOException
    //   102	119	218	java/io/IOException
    //   122	135	218	java/io/IOException
    //   138	151	218	java/io/IOException
    //   154	167	218	java/io/IOException
    //   170	183	218	java/io/IOException
    //   186	199	218	java/io/IOException
    //   202	208	218	java/io/IOException
  }
  
  private View createView(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label212;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.context);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label212:
      localObject1 = null;
      Object localObject3 = Boolean.valueOf(false);
      localObject2 = localObject3;
      Object localObject4;
      if (this.mItemPurchaseItemInfo != null)
      {
        this.mTotalBalance = this.mItemPurchaseItemInfo.itemPrice;
        this.mPostPay = this.mTotalBalance;
        localObject4 = IAPLib.getSellerInfo();
        localObject1 = localObject4;
        localObject2 = localObject3;
        if (localObject4 != null)
        {
          localObject1 = localObject4;
          localObject2 = localObject3;
          if (((SellerInfoConfirm)localObject4).getmSellerPhoneNumber() != null)
          {
            localObject1 = localObject4;
            localObject2 = localObject3;
            if (!((SellerInfoConfirm)localObject4).getmSellerPhoneNumber().equals("null"))
            {
              localObject2 = Boolean.valueOf(true);
              localObject1 = localObject4;
            }
          }
        }
      }
      this.mPaymentTotal = 0;
      this.mOCBUse = 0;
      this.mCultureUse = 0;
      this.mDotoriUse = 0;
      this.mTCashUse = 0;
      this.mOCBFlag = Boolean.valueOf(false);
      this.mDotoriFlag = Boolean.valueOf(false);
      this.mCultureFlag = Boolean.valueOf(false);
      this.mTcashFlag = Boolean.valueOf(false);
      localObject3 = Build.MODEL;
      Object localObject5;
      label425:
      Object localObject7;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject4 = (LinearLayout)paramXmlPullParser;
        localObject5 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject5 != null)
        {
          if (!((String)localObject5).equals("horizontal")) {
            break label2566;
          }
          ((LinearLayout)localObject4).setOrientation(0);
        }
        localObject5 = findAttribute(localAttributeSet, "a:background");
        if (localObject5 != null)
        {
          if (!((String)localObject5).equals("dot_line")) {
            break label2586;
          }
          localObject7 = getClass().getResourceAsStream(getResourcePath() + "line_dot_01.png");
          localObject5 = Drawable.createFromStream((InputStream)localObject7, null);
        }
      }
      try
      {
        ((InputStream)localObject7).close();
        ((BitmapDrawable)localObject5).setTileModeX(Shader.TileMode.REPEAT);
        ((BitmapDrawable)localObject5).setTileModeY(Shader.TileMode.REPEAT);
        ((LinearLayout)localObject4).setBackgroundDrawable((Drawable)localObject5);
        label526:
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject4).setBackgroundColor(-65536);
        }
        localObject5 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject5 != null)
        {
          if (((String)localObject5).equals("center")) {
            ((LinearLayout)localObject4).setGravity(17);
          }
        }
        else
        {
          label580:
          localObject5 = findAttribute(localAttributeSet, "a:padding");
          if (localObject5 != null)
          {
            i = readDPSize((String)localObject5);
            ((LinearLayout)localObject4).setPadding(i, i, i, i);
          }
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject4).setFocusableInTouchMode(true);
          }
          localObject5 = findAttribute(localAttributeSet, "a:paddingTop");
          localObject7 = findAttribute(localAttributeSet, "a:paddingBottom");
          localObject8 = findAttribute(localAttributeSet, "a:paddingLeft");
          localObject9 = findAttribute(localAttributeSet, "a:paddingRight");
          i = 0;
          j = 0;
          int k = 0;
          int m = 0;
          if (localObject5 != null) {
            i = readSize((String)localObject5);
          }
          if (localObject7 != null) {
            j = readSize((String)localObject7);
          }
          if (localObject8 != null) {
            k = readSize((String)localObject8);
          }
          if (localObject9 != null) {
            m = readSize((String)localObject9);
          }
          ((LinearLayout)localObject4).setPadding(k, i, m, j);
          localObject5 = findAttribute(localAttributeSet, "a:id");
          if ((localObject5 != null) && (this.mItemPurchaseItemInfo != null))
          {
            if ((!this.mItemPurchaseItemInfo.ocbRegisterInfo.equals("Y")) || (!((String)localObject5).equals("unregistered"))) {
              break label2677;
            }
            ((LinearLayout)localObject4).setVisibility(8);
          }
          label802:
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject4 = (ImageView)paramXmlPullParser;
            localObject5 = findAttribute(localAttributeSet, "a:src");
            if (localObject5 != null)
            {
              localObject7 = getClass().getResourceAsStream(getResourcePath() + (String)localObject5 + ".png");
              ((ImageView)localObject4).setImageDrawable(Drawable.createFromStream((InputStream)localObject7, (String)localObject5));
            }
          }
        }
      }
      catch (IOException localIOException5)
      {
        try
        {
          ((InputStream)localObject7).close();
          label885:
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject4 = (TextView)paramXmlPullParser;
            localObject7 = findAttribute(localAttributeSet, "a:id");
            localObject10 = findAttribute(localAttributeSet, "a:text");
            String str3 = findAttribute(localAttributeSet, "a:infotext");
            String str4 = findAttribute(localAttributeSet, "a:loctbtntext");
            localObject8 = findAttribute(localAttributeSet, "a:otptext");
            localObject9 = findAttribute(localAttributeSet, "a:textcontent");
            String str1 = findAttribute(localAttributeSet, "a:textSize");
            String str2 = findAttribute(localAttributeSet, "a:textColor");
            localObject5 = findAttribute(localAttributeSet, "a:gravity");
            if (localObject10 != null) {
              ((TextView)localObject4).setText(((String)localObject10).replace("\\n", "\n"));
            }
            if (str4 != null) {
              ((TextView)localObject4).setText(CommonString.getString(CommonString.Index.MENT_SECURE_PAYMENTS));
            }
            if (str3 != null)
            {
              localObject10 = this.context.getPackageManager().getInstalledApplications(0);
              j = ((List)localObject10).size();
              i = 0;
              label1062:
              if (i < j) {}
            }
            else
            {
              if (localObject8 != null) {
                ((TextView)localObject4).setText(CommonString.getString(CommonString.Index.DO_NOT_USE_AUTHENITACTION));
              }
              if (localObject9 != null)
              {
                ((String)localObject9).replace("\\n", "\n");
                ((TextView)localObject4).setText("<" + CommonString.getString(CommonString.Index.MONTH_AUTO_PAYMENT_INFO) + ">\n월별 자동결제 상품이란,");
              }
              if (str1 != null) {
                ((TextView)localObject4).setTextSize(readFontSize(str1));
              }
              if (str2 != null) {
                ((TextView)localObject4).setTextColor(Color.parseColor(str2));
              }
              if (localObject7 != null)
              {
                if (!((String)localObject7).equals("ItemName")) {
                  break label2749;
                }
                ((TextView)localObject4).setText(this.mItemPurchaseItemInfo.itemName);
                ((TextView)localObject4).setEllipsize(TextUtils.TruncateAt.END);
                ((TextView)localObject4).setSingleLine();
              }
              label1206:
              if (localObject5 != null)
              {
                if (!((String)localObject5).equals("right")) {
                  break label3845;
                }
                ((TextView)localObject4).setGravity(5);
              }
              label1228:
              ((TextView)localObject4).setLineSpacing(0.0F, 1.15F);
            }
          }
          else if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject4 = findAttribute(localAttributeSet, "a:background");
            if (localObject4 != null)
            {
              localObject2 = getClass().getResourceAsStream(getResourcePath() + (String)localObject4 + ".png");
              localObject4 = Drawable.createFromStream((InputStream)localObject2, (String)localObject4);
            }
          }
        }
        catch (IOException localIOException5)
        {
          try
          {
            ((InputStream)localObject2).close();
            label1317:
            ((ImageView)localObject1).setBackgroundDrawable((Drawable)localObject4);
            if ((paramXmlPullParser instanceof Button))
            {
              localObject2 = findAttribute(localAttributeSet, "a:offImage");
              localObject1 = findAttribute(localAttributeSet, "a:onImage");
              localObject4 = findAttribute(localAttributeSet, "a:id");
              if (localObject2 == null) {
                break label4936;
              }
              this.mBtn = ((Button)paramXmlPullParser);
              this.mDrawables = new StateListDrawable();
              this.mInactiveDrawables = new StateListDrawable();
              this.mInactiveDotoriDrawables = new StateListDrawable();
              this.mInactiveCultureDrawables = new StateListDrawable();
              this.mInactiveTcashDrawables = new StateListDrawable();
              this.mActiveDrawables = new StateListDrawable();
              this.mActiveDotoriDrawables = new StateListDrawable();
              this.mActiveCultureDrawables = new StateListDrawable();
              this.mActiveTcashDrawables = new StateListDrawable();
              this.mStream = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              this.mbtOn = Drawable.createFromStream(this.mStream, (String)localObject2);
            }
          }
          catch (IOException localIOException5)
          {
            try
            {
              int i;
              Object localObject8;
              Object localObject9;
              int j;
              Object localObject10;
              this.mStream.close();
              this.mStream = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
              this.mbtOver = Drawable.createFromStream(this.mStream, (String)localObject1);
              try
              {
                this.mStream.close();
                if (((String)localObject2).equals("bt_01_nor.9"))
                {
                  this.mInactiveOn = this.mbtOn;
                  this.mActiveOver = this.mbtOver;
                }
                localObject3 = this.mDrawables;
                localObject5 = this.mbtOver;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mDrawables;
                localObject5 = this.mbtOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mInactiveDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mInactiveDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mInactiveDotoriDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mInactiveDotoriDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mInactiveCultureDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mInactiveCultureDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mInactiveTcashDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mInactiveTcashDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mActiveDrawables;
                localObject5 = this.mActiveOver;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mActiveDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mActiveDotoriDrawables;
                localObject5 = this.mActiveOver;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mActiveDotoriDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mActiveCultureDrawables;
                localObject5 = this.mActiveOver;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mActiveCultureDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                localObject3 = this.mActiveTcashDrawables;
                localObject5 = this.mActiveOver;
                ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                localObject3 = this.mActiveTcashDrawables;
                localObject5 = this.mInactiveOn;
                ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                this.mLiminExcessDrawables = new StateListDrawable();
                this.mLiminExcessStream = getClass().getResourceAsStream(getResourcePath() + "bt_buy_dim_e" + ".png");
                this.mLimitExcessbtOn = Drawable.createFromStream(this.mLiminExcessStream, "bt_buy_dim_e");
                try
                {
                  this.mLiminExcessStream.close();
                  this.mLiminExcessStream = null;
                  this.mLiminExcessStream = getClass().getResourceAsStream(getResourcePath() + "bt_buy_dim_e" + ".png");
                  this.mLimitExcessbtOver = Drawable.createFromStream(this.mLiminExcessStream, "bt_buy_dim_e");
                  try
                  {
                    this.mLiminExcessStream.close();
                    this.mLiminExcessStream = null;
                    localObject3 = this.mLiminExcessDrawables;
                    localObject5 = this.mLimitExcessbtOver;
                    ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                    localObject3 = this.mLiminExcessDrawables;
                    localObject5 = this.mLimitExcessbtOn;
                    ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject5);
                    if ((((String)localObject2).equals("btn_buy_nor_h_e")) && (IAPLib.getLimitExcess()))
                    {
                      this.mOkBtn = this.mBtn;
                      this.mOkDrawbles = this.mDrawables;
                      this.mFlag = true;
                      label2302:
                      if ((!((String)localObject2).equals("btn_buy_nor_h_e")) || (!this.mFlag)) {
                        break label3904;
                      }
                      this.mBtn.setBackgroundDrawable(this.mLiminExcessDrawables);
                      label2331:
                      if ((((String)localObject2).equals("bt_01_nor.9")) && (localObject4 != null))
                      {
                        this.mBtn.setTextColor(Color.parseColor("#CFCFCF"));
                        if (!((String)localObject4).equals("OCBBtn")) {
                          break label4035;
                        }
                        this.mOCBBtn = this.mBtn;
                        this.mOCBBtn.setPadding(0, 0, 0, 0);
                        if (!this.mItemPurchaseItemInfo.ocbRegisterInfo.equals("Y")) {
                          break label3987;
                        }
                        if (!IAPLib.getOCBPointInfo()) {
                          break label3956;
                        }
                        localObject2 = IAPLib.getOCBPoint();
                        if ((localObject2 != null) && (((String)localObject2).length() > 0) && (Integer.parseInt((String)localObject2) >= 10)) {
                          break label3918;
                        }
                        mOCBPointQueryFlag = Boolean.valueOf(true);
                        this.mOCBBtn.setText(CommonString.getString(CommonString.Index.USE));
                        this.mOCBBtn.setBackgroundDrawable(this.mInactiveDrawables);
                        this.mOCBBtn.setTextColor(Color.parseColor("#8B8B8B"));
                        this.mOCBBtn.setClickable(false);
                        this.mOCBBtn.setPadding(0, 0, 0, 0);
                      }
                      label2503:
                      if ((!((String)localObject1).equals("btn_buy_sel_h_e")) || (IAPLib.getLimitExcess())) {
                        break label4773;
                      }
                      this.mBtn.setOnClickListener(this.okBtn);
                    }
                    for (;;)
                    {
                      label2531:
                      localObject1 = paramXmlPullParser;
                      if (this.layoutStack.size() <= 0) {
                        break;
                      }
                      paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                      return paramXmlPullParser;
                      label2566:
                      if (!((String)localObject5).equals("vertical")) {
                        break label425;
                      }
                      ((LinearLayout)localObject4).setOrientation(1);
                      break label425;
                      label2586:
                      localObject7 = getClass().getResourceAsStream(getResourcePath() + (String)localObject5 + ".png");
                      ((LinearLayout)localObject4).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject7, (String)localObject5));
                      try
                      {
                        ((InputStream)localObject7).close();
                      }
                      catch (IOException localIOException13) {}
                      break label526;
                      if (localIOException13.equals("left"))
                      {
                        ((LinearLayout)localObject4).setGravity(3);
                        break label580;
                      }
                      ((LinearLayout)localObject4).setGravity(5);
                      break label580;
                      label2677:
                      if ((!this.mItemPurchaseItemInfo.ocbRegisterInfo.equals("N")) || (!localIOException13.equals("registered"))) {
                        break label802;
                      }
                      ((LinearLayout)localObject4).setVisibility(8);
                      break label802;
                      if (((ApplicationInfo)((List)localObject10).get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                        this.tStoreFlag = true;
                      }
                      i += 1;
                      break label1062;
                      label2749:
                      if (((String)localObject7).equals("ItemUseDate"))
                      {
                        ((TextView)localObject4).setText(this.mItemPurchaseItemInfo.itemUseDate);
                        break label1206;
                      }
                      if (((String)localObject7).equals("ItemPrice"))
                      {
                        ((TextView)localObject4).setText(this.mItemPurchaseItemInfo.itemPrice + CommonString.getString(CommonString.Index.WON));
                        break label1206;
                      }
                      if (((String)localObject7).equals("ItemOCB"))
                      {
                        this.mOCBText = ((TextView)localObject4);
                        if (IAPLib.getOCBPointInfo())
                        {
                          this.mOCBText.setText(IAPLib.getOCBPoint() + " P");
                          break label1206;
                        }
                        this.mOCBText.setText("0 P");
                        break label1206;
                      }
                      if (((String)localObject7).equals("ItemDotori"))
                      {
                        this.mDotoriText = ((TextView)localObject4);
                        if (this.mItemPurchaseItemInfo.dotoriLinkInfo.equals("Y"))
                        {
                          ((TextView)localObject4).setText(this.mItemPurchaseItemInfo.dotoriBalance / 100 + CommonString.getString(CommonString.Index.DOTORI_COUNT));
                          break label1206;
                        }
                        ((TextView)localObject4).setText("0" + CommonString.getString(CommonString.Index.DOTORI_COUNT));
                        break label1206;
                      }
                      if (((String)localObject7).equals("ItemCultureCash"))
                      {
                        this.mCultureText = ((TextView)localObject4);
                        if ((IAPLib.getCultureCashQueryInfo()) && (IAPLib.getCultureLandCashPoint() != null))
                        {
                          ((TextView)localObject4).setText(IAPLib.getCultureLandCashPoint() + CommonString.getString(CommonString.Index.WON));
                          break label1206;
                        }
                        ((TextView)localObject4).setText("0" + CommonString.getString(CommonString.Index.WON));
                        break label1206;
                      }
                      if (((String)localObject7).equals("ItemCash"))
                      {
                        this.mTcashText = ((TextView)localObject4);
                        ((TextView)localObject4).setText(this.mItemPurchaseItemInfo.itemTCash + " P");
                        break label1206;
                      }
                      if (((String)localObject7).equals("Discount"))
                      {
                        this.m_discountTextView = ((TextView)localObject4);
                        this.m_discountTextView.setText(this.mPaymentTotal + CommonString.getString(CommonString.Index.WON));
                        break label1206;
                      }
                      if (((String)localObject7).equals("Payments"))
                      {
                        this.m_AccountPriceTextView = ((TextView)localObject4);
                        this.m_AccountPriceTextView.setText(this.mTotalBalance + CommonString.getString(CommonString.Index.WON));
                        break label1206;
                      }
                      if (((String)localObject7).equals("xperiaCash"))
                      {
                        if (((String)localObject3).endsWith("LT15i"))
                        {
                          ((TextView)localObject4).setText(CommonString.getString(CommonString.Index.USING) + "  ");
                          break label1206;
                        }
                        ((TextView)localObject4).setText("");
                        break label1206;
                      }
                      if (((String)localObject7).equals("commonMessage"))
                      {
                        ((TextView)localObject4).setText(this.mInfoMessage);
                        break label1206;
                      }
                      if (((String)localObject7).equals("Version"))
                      {
                        if ("iapdev.tstore.co.kr".equals("iapdev.tstore.co.kr"))
                        {
                          ((TextView)localObject4).setText("V 12.07.02(" + CommonString.getString(CommonString.Index.DEVELOPMENT) + ")");
                          break label1206;
                        }
                        ((TextView)localObject4).setText("V 12.07.02");
                        break label1206;
                      }
                      if ((((String)localObject7).equals("HeaderMessage")) || (((String)localObject7).equals("FooterMessage")))
                      {
                        if (!this.mItemPurchaseItemInfo.FinalVersionCheck) {
                          break label1206;
                        }
                        return null;
                      }
                      if (((String)localObject7).equals("OCBCardNumber"))
                      {
                        ((TextView)localObject4).setText(this.mItemPurchaseItemInfo.ocbCardNumber);
                        break label1206;
                      }
                      if (((String)localObject7).equals("infoText"))
                      {
                        if (CommonF.getCarrier(this.context) == 1)
                        {
                          ((TextView)localObject4).setText(CommonString.getString(CommonString.Index.MENT_SUM_PAYMENTS));
                          break label1206;
                        }
                        ((TextView)localObject4).setText(CommonString.getString(CommonString.Index.MENT_DANAL_PAYMENTS));
                        break label1206;
                      }
                      if (((String)localObject7).equals("finalVersion_Item"))
                      {
                        if (this.mItemPurchaseItemInfo.FinalVersionCheck)
                        {
                          ((TextView)localObject4).setVisibility(8);
                          break label1206;
                        }
                        ((TextView)localObject4).setVisibility(0);
                        break label1206;
                      }
                      if ((((String)localObject7).equals("seller_tel_num")) || (((String)localObject7).equals("seller_tel_num_view")))
                      {
                        if (!((Boolean)localObject2).booleanValue())
                        {
                          ((TextView)localObject4).setVisibility(8);
                          break label1206;
                        }
                        if (!((String)localObject7).equals("seller_tel_num_view")) {
                          break label1206;
                        }
                        localObject1 = ((SellerInfoConfirm)localObject1).getmSellerPhoneNumber();
                        if (((String)localObject1).equals("null")) {
                          break label1206;
                        }
                        ((TextView)localObject4).setText(PhoneNumberUtils.formatNumber((String)localObject1));
                        break label1206;
                      }
                      if ((localObject1 != null) && (((String)localObject7).equals("seller_name_view")))
                      {
                        if ((((SellerInfoConfirm)localObject1).getmSellerName().equals("null")) && (((SellerInfoConfirm)localObject1).getmCompanyName().equals("null"))) {
                          localObject1 = "";
                        }
                        for (;;)
                        {
                          ((TextView)localObject4).setSingleLine(true);
                          ((TextView)localObject4).setFocusable(true);
                          ((TextView)localObject4).setEllipsize(TextUtils.TruncateAt.MARQUEE);
                          ((TextView)localObject4).setFocusableInTouchMode(true);
                          ((TextView)localObject4).setSelected(true);
                          ((TextView)localObject4).setText((CharSequence)localObject1);
                          break;
                          if (!((SellerInfoConfirm)localObject1).getmSellerName().equals("null")) {
                            localObject1 = ((SellerInfoConfirm)localObject1).getmSellerName();
                          } else {
                            localObject1 = ((SellerInfoConfirm)localObject1).getmCompanyName();
                          }
                        }
                      }
                      if ((localObject1 == null) || (!((String)localObject7).equals("seller_mail_view"))) {
                        break label1206;
                      }
                      if (((SellerInfoConfirm)localObject1).getmSellerEmail().equals("null"))
                      {
                        ((TextView)localObject4).setText("");
                        break label1206;
                      }
                      ((TextView)localObject4).setSingleLine(true);
                      ((TextView)localObject4).setFocusable(true);
                      ((TextView)localObject4).setEllipsize(TextUtils.TruncateAt.MARQUEE);
                      ((TextView)localObject4).setFocusableInTouchMode(true);
                      ((TextView)localObject4).setSelected(true);
                      ((TextView)localObject4).setText(((SellerInfoConfirm)localObject1).getmSellerEmail());
                      break label1206;
                      label3845:
                      if (localIOException13.equals("left"))
                      {
                        ((TextView)localObject4).setGravity(3);
                        break label1228;
                      }
                      if (localIOException13.equals("center"))
                      {
                        ((TextView)localObject4).setGravity(17);
                        break label1228;
                      }
                      ((TextView)localObject4).setGravity(19);
                      break label1228;
                      this.mFlag = false;
                      break label2302;
                      label3904:
                      this.mBtn.setBackgroundDrawable(this.mDrawables);
                      break label2331;
                      label3918:
                      mOCBPointQueryFlag = Boolean.valueOf(true);
                      this.mOCBBtn.setText(CommonString.getString(CommonString.Index.USE));
                      this.mOCBBtn.setOnClickListener(new View.OnClickListener()
                      {
                        public void onClick(View paramAnonymousView)
                        {
                          if ((ParserXML.this.octDel != null) && (ParserXML.this.octChange != null))
                          {
                            ParserXML.this.octDel.setBackgroundDrawable(ParserXML.this.octChange.getBackground());
                            ParserXML.this.octDel.setPadding(0, 0, 0, 0);
                          }
                          if (ParserXML.this.mOCBFlag.booleanValue())
                          {
                            ParserXML.this.mOCBBtn.setText(CommonString.getString(CommonString.Index.USE));
                            ParserXML.this.mOCBBtn.setPadding(0, 0, 0, 0);
                            ParserXML.this.mOCBFlag = Boolean.valueOf(false);
                            ParserXML.this.del_payments_amount("OCB");
                            return;
                          }
                          ParserXML.this.mOCBBtn.setText(CommonString.getString(CommonString.Index.USE_CANCEL));
                          ParserXML.this.mOCBBtn.setPadding(0, 0, 0, 0);
                          ParserXML.this.mOCBFlag = Boolean.valueOf(true);
                          ParserXML.this.add_payments_amount("OCB");
                        }
                      });
                      break label2503;
                      label3956:
                      this.mOCBBtn.setText(CommonString.getString(CommonString.Index.OCB_INQUIRY));
                      this.mOCBBtn.setOnClickListener(new View.OnClickListener()
                      {
                        public void onClick(View paramAnonymousView)
                        {
                          ParserXML.this.onResultCallback.onShowOCBPWDDialog();
                          ParserXML.this.chargeAmountInit();
                        }
                      });
                      break label2503;
                      label3987:
                      this.mOCBBtn.setText(CommonString.getString(CommonString.Index.OCB_REGIST_SMALL));
                      if (this.mOCBText != null) {
                        this.mOCBText.setText("0 P");
                      }
                      this.mOCBBtn.setOnClickListener(new View.OnClickListener()
                      {
                        public void onClick(View paramAnonymousView)
                        {
                          ParserXML.this.onResultCallback.onShowOCBRegPopup("C");
                          ParserXML.this.chargeAmountInit();
                        }
                      });
                      break label2503;
                      label4035:
                      if (((String)localObject4).equals("DotoriBtn"))
                      {
                        this.mDotoriBtn = this.mBtn;
                        this.mDotoriBtn.setPadding(0, 0, 0, 0);
                        if (this.mItemPurchaseItemInfo.dotoriLinkInfo.equals("Y"))
                        {
                          this.mDotoriBtn.setText(CommonString.getString(CommonString.Index.USE));
                          if (this.mItemPurchaseItemInfo.IsExistDotoriSmsAuthLogFile)
                          {
                            if (this.mItemPurchaseItemInfo.dotoriBalance != 0)
                            {
                              this.mDotoriBtn.setOnClickListener(new View.OnClickListener()
                              {
                                public void onClick(View paramAnonymousView)
                                {
                                  if ((ParserXML.this.octDel != null) && (ParserXML.this.octChange != null))
                                  {
                                    ParserXML.this.octDel.setBackgroundDrawable(ParserXML.this.octChange.getBackground());
                                    ParserXML.this.octDel.setPadding(0, 0, 0, 0);
                                  }
                                  if (ParserXML.this.mDotoriFlag.booleanValue())
                                  {
                                    ParserXML.this.mDotoriBtn.setText(CommonString.getString(CommonString.Index.USE));
                                    ParserXML.this.mDotoriBtn.setPadding(0, 0, 0, 0);
                                    ParserXML.this.mDotoriFlag = Boolean.valueOf(false);
                                    ParserXML.this.del_payments_amount("DOTORI");
                                    return;
                                  }
                                  if ((ParserXML.this.mPostPay < 100) && (ParserXML.this.mDotoriUse == 0))
                                  {
                                    ParserXML.this.mDotoriBtn.setBackgroundDrawable(ParserXML.this.mInactiveDotoriDrawables);
                                    ParserXML.this.mDotoriBtn.setTextColor(Color.parseColor("#8B8B8B"));
                                    ParserXML.this.mDotoriBtn.setClickable(false);
                                    ParserXML.this.mDotoriBtn.setPadding(0, 0, 0, 0);
                                    return;
                                  }
                                  ParserXML.this.mDotoriBtn.setText(CommonString.getString(CommonString.Index.USE_CANCEL));
                                  ParserXML.this.mDotoriBtn.setPadding(0, 0, 0, 0);
                                  ParserXML.this.mDotoriFlag = Boolean.valueOf(true);
                                  ParserXML.this.add_payments_amount("DOTORI");
                                }
                              });
                              break label2503;
                            }
                            this.mDotoriBtn.setBackgroundDrawable(this.mInactiveDrawables);
                            this.mDotoriBtn.setTextColor(Color.parseColor("#8B8B8B"));
                            this.mDotoriBtn.setClickable(false);
                            this.mDotoriBtn.setPadding(0, 0, 0, 0);
                            break label2503;
                          }
                          this.mDotoriBtn.setText(CommonString.getString(CommonString.Index.USE));
                          if (this.mItemPurchaseItemInfo.dotoriBalance != 0)
                          {
                            this.mDotoriBtn.setOnClickListener(new View.OnClickListener()
                            {
                              public void onClick(View paramAnonymousView)
                              {
                                ParserXML.this.onResultCallback.onShowDotoriQueryBtnClick(true);
                                ParserXML.this.chargeAmountInit();
                              }
                            });
                            break label2503;
                          }
                          this.mDotoriBtn.setBackgroundDrawable(this.mInactiveDotoriDrawables);
                          this.mDotoriBtn.setTextColor(Color.parseColor("#8B8B8B"));
                          this.mDotoriBtn.setClickable(false);
                          this.mDotoriBtn.setPadding(0, 0, 0, 0);
                          break label2503;
                        }
                        this.mDotoriBtn.setText(CommonString.getString(CommonString.Index.DOTORI_INQUIRY));
                        this.mDotoriBtn.setOnClickListener(new View.OnClickListener()
                        {
                          public void onClick(View paramAnonymousView)
                          {
                            ParserXML.this.onResultCallback.onShowDotoriQueryBtnClick(false);
                            ParserXML.this.chargeAmountInit();
                          }
                        });
                        break label2503;
                      }
                      if (((String)localObject4).equals("CultureBtn"))
                      {
                        this.mCultureBtn = this.mBtn;
                        this.mCultureBtn.setPadding(0, 0, 0, 0);
                        if (IAPLib.getCultureCashQueryInfo())
                        {
                          this.mCultureBtn.setText(CommonString.getString(CommonString.Index.USE));
                          localObject2 = IAPLib.getCultureLandCashPoint();
                          if ((localObject2 == null) || (((String)localObject2).length() <= 0) || (Integer.parseInt((String)localObject2) < 10))
                          {
                            this.mCultureBtn.setBackgroundDrawable(this.mInactiveDrawables);
                            this.mCultureBtn.setTextColor(Color.parseColor("#8B8B8B"));
                            this.mCultureBtn.setClickable(false);
                            this.mCultureBtn.setPadding(0, 0, 0, 0);
                            break label2503;
                          }
                          this.mCultureBtn.setOnClickListener(new View.OnClickListener()
                          {
                            public void onClick(View paramAnonymousView)
                            {
                              if ((ParserXML.this.octDel != null) && (ParserXML.this.octChange != null))
                              {
                                ParserXML.this.octDel.setBackgroundDrawable(ParserXML.this.octChange.getBackground());
                                ParserXML.this.octDel.setPadding(0, 0, 0, 0);
                              }
                              if (ParserXML.this.mCultureFlag.booleanValue())
                              {
                                ParserXML.this.mCultureBtn.setText(CommonString.getString(CommonString.Index.USE));
                                ParserXML.this.mCultureBtn.setPadding(0, 0, 0, 0);
                                ParserXML.this.mCultureFlag = Boolean.valueOf(false);
                                ParserXML.this.del_payments_amount("CULTURE");
                                return;
                              }
                              ParserXML.this.mCultureBtn.setText(CommonString.getString(CommonString.Index.USE_CANCEL));
                              ParserXML.this.mCultureBtn.setPadding(0, 0, 0, 0);
                              ParserXML.this.mCultureFlag = Boolean.valueOf(true);
                              ParserXML.this.add_payments_amount("CULTURE");
                            }
                          });
                          break label2503;
                        }
                        this.mCultureBtn.setText(CommonString.getString(CommonString.Index.CUTURE_INQUIRY));
                        this.mCultureBtn.setPadding(0, 0, 0, 0);
                        this.mCultureBtn.setOnClickListener(new View.OnClickListener()
                        {
                          public void onClick(View paramAnonymousView)
                          {
                            ParserXML.this.onResultCallback.onShowCultureLandDialog();
                            ParserXML.this.chargeAmountInit();
                          }
                        });
                        break label2503;
                      }
                      if (((String)localObject4).equals("TcashBtn"))
                      {
                        this.mTcashBtn = this.mBtn;
                        this.mTcashBtn.setPadding(0, 0, 0, 0);
                        this.mTcashBtn.setText(CommonString.getString(CommonString.Index.USE));
                        if (this.mItemPurchaseItemInfo.itemTCash >= 10)
                        {
                          this.mTcashBtn.setOnClickListener(new View.OnClickListener()
                          {
                            public void onClick(View paramAnonymousView)
                            {
                              if ((ParserXML.this.octDel != null) && (ParserXML.this.octChange != null))
                              {
                                ParserXML.this.octDel.setBackgroundDrawable(ParserXML.this.octChange.getBackground());
                                ParserXML.this.octDel.setPadding(0, 0, 0, 0);
                              }
                              if (ParserXML.this.mTcashFlag.booleanValue())
                              {
                                ParserXML.this.mTcashBtn.setText(CommonString.getString(CommonString.Index.USE));
                                ParserXML.this.mTcashBtn.setPadding(0, 0, 0, 0);
                                ParserXML.this.mTcashFlag = Boolean.valueOf(false);
                                ParserXML.this.del_payments_amount("TCASH");
                                return;
                              }
                              ParserXML.this.mTcashBtn.setText(CommonString.getString(CommonString.Index.USE_CANCEL));
                              ParserXML.this.mTcashBtn.setPadding(0, 0, 0, 0);
                              ParserXML.this.mTcashFlag = Boolean.valueOf(true);
                              ParserXML.this.add_payments_amount("TCASH");
                            }
                          });
                          break label2503;
                        }
                        this.mTcashBtn.setClickable(false);
                        this.mTcashBtn.setBackgroundDrawable(this.mInactiveDrawables);
                        this.mTcashBtn.setPadding(0, 0, 0, 0);
                        this.mTcashBtn.setTextColor(Color.parseColor("#8B8B8B"));
                        break label2503;
                      }
                      if (((String)localObject4).equals("OCBRegister"))
                      {
                        localObject2 = this.mBtn;
                        ((Button)localObject2).setText(CommonString.getString(CommonString.Index.OCB_REGIST));
                        ((Button)localObject2).setPadding(0, 0, 0, 0);
                        ((Button)localObject2).setOnClickListener(new View.OnClickListener()
                        {
                          public void onClick(View paramAnonymousView)
                          {
                            ParserXML.this.onResultCallback.onShowOCBRegPopup("C");
                            ParserXML.this.chargeAmountInit();
                          }
                        });
                        break label2503;
                      }
                      if (((String)localObject4).equals("OCB_Card_Change"))
                      {
                        this.octChange = this.mBtn;
                        this.octChange.setText(CommonString.getString(CommonString.Index.OCB_CHANGE_CARD));
                        this.octChange.setPadding(0, 0, 0, 0);
                        this.octChange.setOnClickListener(new View.OnClickListener()
                        {
                          public void onClick(View paramAnonymousView)
                          {
                            ParserXML.this.onResultCallback.onShowOCBRegPopup("U");
                            ParserXML.this.chargeAmountInit();
                          }
                        });
                        break label2503;
                      }
                      if (!((String)localObject4).equals("OCB_Card_Del")) {
                        break label2503;
                      }
                      this.octDel = this.mBtn;
                      this.octDel.setText(CommonString.getString(CommonString.Index.OCB_DELETE_CARD));
                      this.octDel.setPadding(0, 0, 0, 0);
                      this.octDel.setOnClickListener(new View.OnClickListener()
                      {
                        public void onClick(View paramAnonymousView)
                        {
                          ParserXML.this.onResultCallback.onShowOCBRegPopup("D");
                          ParserXML.this.chargeAmountInit();
                        }
                      });
                      break label2503;
                      label4773:
                      if (((String)localObject1).equals("pop_btn_sel_ok_e"))
                      {
                        this.mBtn.setOnClickListener(this.okMessageBtn);
                      }
                      else if (((String)localObject1).equals("btn_info01_sel_e"))
                      {
                        this.mBtn.setOnClickListener(this.moreInfo);
                      }
                      else
                      {
                        if (((String)localObject1).equals("btn_locking_sel_e"))
                        {
                          localObject1 = this.context.getPackageManager().getInstalledApplications(0);
                          j = ((List)localObject1).size();
                          i = 0;
                          for (;;)
                          {
                            if (i >= j)
                            {
                              this.mBtn.setOnClickListener(this.tStoreInfoBtn);
                              break;
                            }
                            if (((ApplicationInfo)((List)localObject1).get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                              this.tStoreFlag = true;
                            }
                            i += 1;
                          }
                        }
                        if (((String)localObject1).equals("btn_cancel_sel_h_e")) {
                          this.mBtn.setOnClickListener(this.cancelBtn);
                        }
                      }
                    }
                    label4936:
                    if (this.mItemPurchaseItemInfo != null) {
                      if (!this.mItemPurchaseItemInfo.AutoPurchaseCheck)
                      {
                        localObject2 = (CheckBox)paramXmlPullParser;
                        i = 0;
                        if (((String)localObject3).endsWith("LT15i"))
                        {
                          i = 1;
                          this.xperiacheckbox = true;
                        }
                        if (i == 0)
                        {
                          localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
                          localObject1 = Drawable.createFromStream((InputStream)localObject3, (String)localObject1);
                        }
                      }
                    }
                    try
                    {
                      ((InputStream)localObject3).close();
                      localObject4 = getClass().getResourceAsStream(getResourcePath() + "btn_check_ok_nor" + ".png");
                      localObject3 = Drawable.createFromStream((InputStream)localObject4, "btn_check_ok_nor");
                    }
                    catch (IOException localIOException5)
                    {
                      try
                      {
                        ((InputStream)localObject4).close();
                        localObject6 = getClass().getResourceAsStream(getResourcePath() + "btn_check_no_sel" + ".png");
                        localObject4 = Drawable.createFromStream((InputStream)localObject6, "btn_check_no_sel");
                      }
                      catch (IOException localIOException5)
                      {
                        try
                        {
                          ((InputStream)localObject6).close();
                          localObject7 = getClass().getResourceAsStream(getResourcePath() + "btn_check_ok_sel" + ".png");
                          localObject6 = Drawable.createFromStream((InputStream)localObject7, "btn_check_ok_sel");
                        }
                        catch (IOException localIOException5)
                        {
                          try
                          {
                            ((InputStream)localObject7).close();
                            localObject8 = getClass().getResourceAsStream(getResourcePath() + "btn_check_dim" + ".png");
                            localObject7 = Drawable.createFromStream((InputStream)localObject8, "btn_check_dim");
                          }
                          catch (IOException localIOException5)
                          {
                            try
                            {
                              ((InputStream)localObject8).close();
                              localObject8 = new StateListDrawable();
                              localObject9 = new StateListDrawable();
                              ((StateListDrawable)localObject8).addState(new int[] { -16842910, -16842908 }, (Drawable)localObject7);
                              ((StateListDrawable)localObject8).addState(new int[] { -16842912, 16842919 }, (Drawable)localObject4);
                              ((StateListDrawable)localObject8).addState(new int[] { 16842912, 16842919 }, (Drawable)localObject6);
                              ((StateListDrawable)localObject8).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject1);
                              ((StateListDrawable)localObject8).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
                              ((StateListDrawable)localObject9).addState(new int[] { -16842910, -16842908 }, null);
                              ((StateListDrawable)localObject9).addState(new int[] { -16842912, 16842919 }, null);
                              ((StateListDrawable)localObject9).addState(new int[] { 16842912, 16842919 }, null);
                              ((StateListDrawable)localObject9).addState(new int[] { -16842912, -16842908 }, null);
                              ((StateListDrawable)localObject9).addState(new int[] { 16842912, -16842908 }, null);
                              ((CheckBox)localObject2).setButtonDrawable((Drawable)localObject9);
                              ((CheckBox)localObject2).setBackgroundDrawable((Drawable)localObject8);
                              ((CheckBox)localObject2).setChecked(false);
                              if ((this.mItemPurchaseItemInfo.itemTCash != 0) && (this.mItemPurchaseItemInfo.itemTCash - this.mItemPurchaseItemInfo.itemPrice >= 0)) {
                                ((CheckBox)localObject2).setEnabled(true);
                              }
                              for (;;)
                              {
                                ((CheckBox)localObject2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
                                {
                                  public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
                                  {
                                    ParserXML.this.UseTCash(Boolean.valueOf(paramAnonymousBoolean));
                                    ParserXML.this.onResultCallback.onUseTCashCheckChanged(paramAnonymousBoolean);
                                    if ((paramAnonymousBoolean) && (IAPLib.getLimitExcess()))
                                    {
                                      ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
                                      ParserXML.this.mFlag = false;
                                      ParserXML.this.mOkBtn.setOnClickListener(ParserXML.this.okBtn);
                                    }
                                    while ((paramAnonymousBoolean) || (!IAPLib.getLimitExcess())) {
                                      return;
                                    }
                                    ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mLiminExcessDrawables);
                                    ParserXML.this.mFlag = true;
                                    ParserXML.this.mOkBtn.setOnClickListener(null);
                                  }
                                });
                                break;
                                ((CheckBox)localObject2).setEnabled(false);
                              }
                              if ((!this.mItemPurchaseItemInfo.AutoPurchaseCheck) || (!(paramXmlPullParser instanceof CheckBox))) {
                                break label2531;
                              }
                              localObject2 = null;
                              localObject1 = findAttribute(localAttributeSet, "a:checkid");
                              localObject4 = (CheckBox)paramXmlPullParser;
                              ((CheckBox)localObject4).setTag(localObject1);
                              localObject1 = null;
                              if (this.mItemPurchaseItemInfo.AfterAutoPurchaseInfoAgree)
                              {
                                localObject2 = "checkbox_y";
                                localObject1 = "checkbox_n";
                                this.mPurchaseCheckList[0] = true;
                                label5632:
                                ((CheckBox)localObject4).setChecked(false);
                                i = 0;
                                if (((String)localObject3).endsWith("LT15i"))
                                {
                                  i = 1;
                                  this.xperiacheckbox = true;
                                }
                                if (i != 0) {
                                  break label5980;
                                }
                                localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
                                localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
                              }
                            }
                            catch (IOException localIOException5)
                            {
                              try
                              {
                                ((InputStream)localObject3).close();
                                localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
                                localObject1 = Drawable.createFromStream((InputStream)localObject3, (String)localObject1);
                              }
                              catch (IOException localIOException5)
                              {
                                try
                                {
                                  ((InputStream)localObject3).close();
                                  localObject3 = new StateListDrawable();
                                  localObject6 = new StateListDrawable();
                                  ((StateListDrawable)localObject3).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
                                  ((StateListDrawable)localObject3).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject1);
                                  ((StateListDrawable)localObject6).addState(new int[] { -16842910, -16842908 }, null);
                                  ((StateListDrawable)localObject6).addState(new int[] { -16842912, 16842919 }, null);
                                  ((StateListDrawable)localObject6).addState(new int[] { 16842912, 16842919 }, null);
                                  ((StateListDrawable)localObject6).addState(new int[] { -16842912, -16842908 }, null);
                                  ((StateListDrawable)localObject6).addState(new int[] { 16842912, -16842908 }, null);
                                  ((CheckBox)localObject4).setButtonDrawable((Drawable)localObject6);
                                  ((CheckBox)localObject4).setBackgroundDrawable((Drawable)localObject3);
                                  for (;;)
                                  {
                                    ((CheckBox)localObject4).setOnCheckedChangeListener(this.changeCheckBox);
                                    break;
                                    if (this.mItemPurchaseItemInfo.AfterAutoPurchaseInfoAgree) {
                                      break label5632;
                                    }
                                    localObject2 = "checkbox_n";
                                    localObject1 = "checkbox_y";
                                    break label5632;
                                    label5980:
                                    if ((i != 0) && (this.mItemPurchaseItemInfo.AfterAutoPurchaseInfoAgree)) {
                                      ((CheckBox)localObject4).setChecked(true);
                                    }
                                  }
                                  localObject2 = (CheckBox)paramXmlPullParser;
                                  ((CheckBox)localObject2).setTag(findAttribute(localAttributeSet, "a:checkid"));
                                  i = 0;
                                  if (((String)localObject3).endsWith("LT15i"))
                                  {
                                    i = 1;
                                    this.xperiacheckbox = true;
                                  }
                                  if (i == 0)
                                  {
                                    localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
                                    localObject1 = Drawable.createFromStream((InputStream)localObject3, (String)localObject1);
                                  }
                                }
                                catch (IOException localIOException5)
                                {
                                  try
                                  {
                                    ((InputStream)localObject3).close();
                                    localObject4 = getClass().getResourceAsStream(getResourcePath() + "checkbox_y" + ".png");
                                    localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
                                  }
                                  catch (IOException localIOException5)
                                  {
                                    try
                                    {
                                      for (;;)
                                      {
                                        ((InputStream)localObject4).close();
                                        localObject4 = new StateListDrawable();
                                        Object localObject6 = new StateListDrawable();
                                        ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject1);
                                        ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
                                        ((StateListDrawable)localObject6).addState(new int[] { -16842912, 16842919 }, null);
                                        ((StateListDrawable)localObject6).addState(new int[] { 16842912, 16842919 }, null);
                                        ((StateListDrawable)localObject6).addState(new int[] { -16842912, -16842908 }, null);
                                        ((StateListDrawable)localObject6).addState(new int[] { 16842912, -16842908 }, null);
                                        ((CheckBox)localObject2).setButtonDrawable((Drawable)localObject6);
                                        ((CheckBox)localObject2).setBackgroundDrawable((Drawable)localObject4);
                                        ((CheckBox)localObject2).setOnCheckedChangeListener(this.changeCheckBox);
                                        break label2531;
                                        localIOException15 = localIOException15;
                                        break;
                                        localIOException10 = localIOException10;
                                        break label885;
                                        localIOException1 = localIOException1;
                                        break label1317;
                                        localIOException2 = localIOException2;
                                        continue;
                                        localIOException11 = localIOException11;
                                        continue;
                                        localIOException14 = localIOException14;
                                        continue;
                                        localIOException16 = localIOException16;
                                        continue;
                                        localIOException17 = localIOException17;
                                        continue;
                                        localIOException3 = localIOException3;
                                        continue;
                                        localIOException4 = localIOException4;
                                      }
                                      localIOException5 = localIOException5;
                                    }
                                    catch (IOException localIOException12)
                                    {
                                      for (;;) {}
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                  catch (IOException localIOException6)
                  {
                    for (;;) {}
                  }
                }
                catch (IOException localIOException7)
                {
                  for (;;) {}
                }
              }
              catch (IOException localIOException8)
              {
                for (;;) {}
              }
            }
            catch (IOException localIOException9)
            {
              for (;;) {}
            }
          }
        }
      }
    }
  }
  
  /* Error */
  private View createViewAuthPopup(XmlPullParser paramXmlPullParser)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface 959 1 0
    //   6: astore 6
    //   8: aconst_null
    //   9: astore 4
    //   11: aload_1
    //   12: invokestatic 965	android/util/Xml:asAttributeSet	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   15: astore 5
    //   17: aload 6
    //   19: ldc_w 967
    //   22: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   25: ifeq +25 -> 50
    //   28: new 969	android/widget/LinearLayout
    //   31: dup
    //   32: aload_0
    //   33: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   36: invokespecial 970	android/widget/LinearLayout:<init>	(Landroid/content/Context;)V
    //   39: astore_1
    //   40: aload_1
    //   41: ifnonnull +223 -> 264
    //   44: aconst_null
    //   45: astore 4
    //   47: aload 4
    //   49: areturn
    //   50: aload 6
    //   52: ldc_w 972
    //   55: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +18 -> 76
    //   61: new 974	android/widget/TextView
    //   64: dup
    //   65: aload_0
    //   66: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   69: invokespecial 975	android/widget/TextView:<init>	(Landroid/content/Context;)V
    //   72: astore_1
    //   73: goto -33 -> 40
    //   76: aload 6
    //   78: ldc_w 982
    //   81: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   84: ifeq +18 -> 102
    //   87: new 984	android/widget/Button
    //   90: dup
    //   91: aload_0
    //   92: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   95: invokespecial 985	android/widget/Button:<init>	(Landroid/content/Context;)V
    //   98: astore_1
    //   99: goto -59 -> 40
    //   102: aload 6
    //   104: ldc_w 1626
    //   107: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   110: ifeq +18 -> 128
    //   113: new 1628	android/widget/EditText
    //   116: dup
    //   117: aload_0
    //   118: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   121: invokespecial 1629	android/widget/EditText:<init>	(Landroid/content/Context;)V
    //   124: astore_1
    //   125: goto -85 -> 40
    //   128: aload 6
    //   130: ldc_w 987
    //   133: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   136: ifeq +23 -> 159
    //   139: new 989	android/widget/ScrollView
    //   142: dup
    //   143: aload_0
    //   144: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   147: invokespecial 990	android/widget/ScrollView:<init>	(Landroid/content/Context;)V
    //   150: astore_1
    //   151: aload_1
    //   152: iconst_0
    //   153: invokevirtual 995	android/view/View:setScrollbarFadingEnabled	(Z)V
    //   156: goto -116 -> 40
    //   159: aload 6
    //   161: ldc_w 997
    //   164: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   167: ifeq +18 -> 185
    //   170: new 999	android/widget/CheckBox
    //   173: dup
    //   174: aload_0
    //   175: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   178: invokespecial 1000	android/widget/CheckBox:<init>	(Landroid/content/Context;)V
    //   181: astore_1
    //   182: goto -142 -> 40
    //   185: aload 6
    //   187: ldc_w 1631
    //   190: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   193: ifeq +18 -> 211
    //   196: new 1633	android/widget/RadioGroup
    //   199: dup
    //   200: aload_0
    //   201: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   204: invokespecial 1634	android/widget/RadioGroup:<init>	(Landroid/content/Context;)V
    //   207: astore_1
    //   208: goto -168 -> 40
    //   211: aload 6
    //   213: ldc_w 1636
    //   216: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   219: ifeq +18 -> 237
    //   222: new 1638	android/widget/RadioButton
    //   225: dup
    //   226: aload_0
    //   227: getfield 405	com/feelingk/iap/gui/parser/ParserXML:context	Landroid/content/Context;
    //   230: invokespecial 1639	android/widget/RadioButton:<init>	(Landroid/content/Context;)V
    //   233: astore_1
    //   234: goto -194 -> 40
    //   237: new 1002	java/lang/StringBuilder
    //   240: dup
    //   241: ldc_w 1004
    //   244: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   247: aload 6
    //   249: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: invokestatic 1018	junit/framework/Assert:fail	(Ljava/lang/String;)V
    //   258: aload 4
    //   260: astore_1
    //   261: goto -221 -> 40
    //   264: aload_1
    //   265: instanceof 1633
    //   268: ifeq +1160 -> 1428
    //   271: aload_1
    //   272: checkcast 1633	android/widget/RadioGroup
    //   275: astore 4
    //   277: aload_0
    //   278: aload 5
    //   280: ldc_w 1043
    //   283: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   286: astore 6
    //   288: aload 6
    //   290: ifnull +20 -> 310
    //   293: aload 6
    //   295: ldc_w 1049
    //   298: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   301: ifeq +1098 -> 1399
    //   304: aload 4
    //   306: iconst_0
    //   307: invokevirtual 1640	android/widget/RadioGroup:setOrientation	(I)V
    //   310: aload_0
    //   311: aload 5
    //   313: ldc_w 1098
    //   316: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   319: astore 6
    //   321: aload 6
    //   323: ifnull +21 -> 344
    //   326: aload 6
    //   328: ldc_w 1100
    //   331: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   334: ifeq +1085 -> 1419
    //   337: aload 4
    //   339: bipush 17
    //   341: invokevirtual 1641	android/widget/RadioGroup:setGravity	(I)V
    //   344: aload_1
    //   345: instanceof 974
    //   348: ifeq +162 -> 510
    //   351: aload_1
    //   352: checkcast 974	android/widget/TextView
    //   355: astore 4
    //   357: aload_0
    //   358: aload 5
    //   360: ldc_w 1131
    //   363: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   366: astore 6
    //   368: aload_0
    //   369: aload 5
    //   371: ldc_w 1150
    //   374: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   377: astore 7
    //   379: aload_0
    //   380: aload 5
    //   382: ldc_w 1160
    //   385: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   388: astore 8
    //   390: aload_0
    //   391: aload 5
    //   393: ldc_w 1162
    //   396: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   399: astore 9
    //   401: aload_0
    //   402: aload 5
    //   404: ldc_w 1098
    //   407: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   410: astore 10
    //   412: aload 7
    //   414: ifnull +19 -> 433
    //   417: aload 4
    //   419: aload 7
    //   421: ldc_w 1164
    //   424: ldc_w 1166
    //   427: invokevirtual 1170	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   430: invokevirtual 1174	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   433: aload 8
    //   435: ifnull +15 -> 450
    //   438: aload 4
    //   440: aload_0
    //   441: aload 8
    //   443: invokespecial 1217	com/feelingk/iap/gui/parser/ParserXML:readFontSize	(Ljava/lang/String;)I
    //   446: i2f
    //   447: invokevirtual 1221	android/widget/TextView:setTextSize	(F)V
    //   450: aload 9
    //   452: ifnull +13 -> 465
    //   455: aload 4
    //   457: aload 9
    //   459: invokestatic 1226	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   462: invokevirtual 1229	android/widget/TextView:setTextColor	(I)V
    //   465: aload 6
    //   467: ifnull +22 -> 489
    //   470: aload 6
    //   472: ldc_w 1446
    //   475: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   478: ifeq +11 -> 489
    //   481: aload 4
    //   483: ldc_w 1457
    //   486: invokevirtual 1174	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   489: aload 10
    //   491: ifnull +1229 -> 1720
    //   494: aload 4
    //   496: bipush 17
    //   498: invokevirtual 1250	android/widget/TextView:setGravity	(I)V
    //   501: aload 4
    //   503: fconst_0
    //   504: ldc_w 1251
    //   507: invokevirtual 1255	android/widget/TextView:setLineSpacing	(FF)V
    //   510: aload_1
    //   511: instanceof 999
    //   514: ifeq +1216 -> 1730
    //   517: aload_0
    //   518: aload 5
    //   520: ldc_w 1260
    //   523: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   526: astore 9
    //   528: aload_0
    //   529: aload 5
    //   531: ldc_w 1612
    //   534: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   537: astore 4
    //   539: aload_1
    //   540: checkcast 999	android/widget/CheckBox
    //   543: astore 6
    //   545: aload 6
    //   547: aload 4
    //   549: invokevirtual 1616	android/widget/CheckBox:setTag	(Ljava/lang/Object;)V
    //   552: getstatic 1041	android/os/Build:MODEL	Ljava/lang/String;
    //   555: astore 10
    //   557: iconst_0
    //   558: istore_2
    //   559: new 1264	android/graphics/drawable/StateListDrawable
    //   562: dup
    //   563: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   566: astore 7
    //   568: new 1264	android/graphics/drawable/StateListDrawable
    //   571: dup
    //   572: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   575: astore 8
    //   577: aload 10
    //   579: ldc_w 1433
    //   582: invokevirtual 1437	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   585: ifeq +10 -> 595
    //   588: iconst_1
    //   589: istore_2
    //   590: aload_0
    //   591: iconst_1
    //   592: putfield 487	com/feelingk/iap/gui/parser/ParserXML:xperiacheckbox	Z
    //   595: iload_2
    //   596: ifne +270 -> 866
    //   599: aload_0
    //   600: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   603: new 1002	java/lang/StringBuilder
    //   606: dup
    //   607: aload_0
    //   608: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   611: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   614: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   617: aload 9
    //   619: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   622: ldc_w 1145
    //   625: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   631: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   634: astore 10
    //   636: aload 10
    //   638: aload 9
    //   640: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   643: astore 9
    //   645: aload 10
    //   647: invokevirtual 752	java/io/InputStream:close	()V
    //   650: aload_0
    //   651: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   654: new 1002	java/lang/StringBuilder
    //   657: dup
    //   658: aload_0
    //   659: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   662: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   665: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   668: ldc_w 1621
    //   671: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   674: ldc_w 1145
    //   677: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   683: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   686: astore 11
    //   688: aload 11
    //   690: ldc_w 1621
    //   693: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   696: astore 10
    //   698: aload 11
    //   700: invokevirtual 752	java/io/InputStream:close	()V
    //   703: aload 7
    //   705: iconst_2
    //   706: newarray int
    //   708: dup
    //   709: iconst_0
    //   710: ldc_w 1594
    //   713: iastore
    //   714: dup
    //   715: iconst_1
    //   716: ldc_w 1593
    //   719: iastore
    //   720: aload 9
    //   722: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   725: aload 7
    //   727: iconst_2
    //   728: newarray int
    //   730: dup
    //   731: iconst_0
    //   732: ldc_w 1595
    //   735: iastore
    //   736: dup
    //   737: iconst_1
    //   738: ldc_w 1593
    //   741: iastore
    //   742: aload 10
    //   744: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   747: aload 8
    //   749: iconst_2
    //   750: newarray int
    //   752: dup
    //   753: iconst_0
    //   754: ldc_w 1592
    //   757: iastore
    //   758: dup
    //   759: iconst_1
    //   760: ldc_w 1593
    //   763: iastore
    //   764: aconst_null
    //   765: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   768: aload 8
    //   770: iconst_2
    //   771: newarray int
    //   773: dup
    //   774: iconst_0
    //   775: ldc_w 1594
    //   778: iastore
    //   779: dup
    //   780: iconst_1
    //   781: ldc_w 1288
    //   784: iastore
    //   785: aconst_null
    //   786: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   789: aload 8
    //   791: iconst_2
    //   792: newarray int
    //   794: dup
    //   795: iconst_0
    //   796: ldc_w 1595
    //   799: iastore
    //   800: dup
    //   801: iconst_1
    //   802: ldc_w 1288
    //   805: iastore
    //   806: aconst_null
    //   807: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   810: aload 8
    //   812: iconst_2
    //   813: newarray int
    //   815: dup
    //   816: iconst_0
    //   817: ldc_w 1594
    //   820: iastore
    //   821: dup
    //   822: iconst_1
    //   823: ldc_w 1593
    //   826: iastore
    //   827: aconst_null
    //   828: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   831: aload 8
    //   833: iconst_2
    //   834: newarray int
    //   836: dup
    //   837: iconst_0
    //   838: ldc_w 1595
    //   841: iastore
    //   842: dup
    //   843: iconst_1
    //   844: ldc_w 1593
    //   847: iastore
    //   848: aconst_null
    //   849: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   852: aload 6
    //   854: aload 8
    //   856: invokevirtual 1598	android/widget/CheckBox:setButtonDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   859: aload 6
    //   861: aload 7
    //   863: invokevirtual 1599	android/widget/CheckBox:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   866: aload 6
    //   868: iconst_0
    //   869: invokevirtual 1602	android/widget/CheckBox:setChecked	(Z)V
    //   872: aload 6
    //   874: aload_0
    //   875: getfield 629	com/feelingk/iap/gui/parser/ParserXML:changeCheckBox	Landroid/widget/CompoundButton$OnCheckedChangeListener;
    //   878: invokevirtual 1610	android/widget/CheckBox:setOnCheckedChangeListener	(Landroid/widget/CompoundButton$OnCheckedChangeListener;)V
    //   881: aload 4
    //   883: ldc_w 1643
    //   886: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   889: ifeq +22 -> 911
    //   892: getstatic 389	com/feelingk/iap/gui/parser/ParserXML:mCultureLoginFlag	Z
    //   895: ifeq +16 -> 911
    //   898: aload 6
    //   900: iconst_1
    //   901: invokevirtual 1602	android/widget/CheckBox:setChecked	(Z)V
    //   904: aload 6
    //   906: aload 7
    //   908: invokevirtual 1599	android/widget/CheckBox:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   911: aload_1
    //   912: instanceof 1628
    //   915: ifeq +449 -> 1364
    //   918: aload_1
    //   919: checkcast 1628	android/widget/EditText
    //   922: astore 4
    //   924: aload 4
    //   926: ldc_w 527
    //   929: invokevirtual 1644	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   932: aload_0
    //   933: getfield 409	com/feelingk/iap/gui/parser/ParserXML:onAuthResultCallback	Lcom/feelingk/iap/gui/parser/ParserXML$ParserAuthResultCallback;
    //   936: ifnonnull +10 -> 946
    //   939: aload_0
    //   940: getfield 429	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNResultCallback	Lcom/feelingk/iap/gui/parser/ParserXML$ParserForeignInputMDNResultCallback;
    //   943: ifnull +9 -> 952
    //   946: aload 4
    //   948: iconst_2
    //   949: invokevirtual 1647	android/widget/EditText:setInputType	(I)V
    //   952: new 1649	java/util/ArrayList
    //   955: dup
    //   956: invokespecial 1650	java/util/ArrayList:<init>	()V
    //   959: astore 6
    //   961: aload_0
    //   962: aload 5
    //   964: ldc_w 1652
    //   967: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   970: astore 7
    //   972: aload 7
    //   974: ifnull +35 -> 1009
    //   977: aload 7
    //   979: ldc_w 1654
    //   982: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   985: ifeq +1895 -> 2880
    //   988: aload 4
    //   990: bipush 17
    //   992: invokevirtual 1647	android/widget/EditText:setInputType	(I)V
    //   995: aload 6
    //   997: new 151	com/feelingk/iap/gui/parser/ParserXML$filterAlphaNum
    //   1000: dup
    //   1001: aload_0
    //   1002: invokespecial 1655	com/feelingk/iap/gui/parser/ParserXML$filterAlphaNum:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;)V
    //   1005: invokevirtual 1658	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1008: pop
    //   1009: aload_0
    //   1010: aload 5
    //   1012: ldc_w 1660
    //   1015: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1018: astore 7
    //   1020: aload 7
    //   1022: ifnull +26 -> 1048
    //   1025: aload 4
    //   1027: invokevirtual 1661	android/widget/EditText:setSingleLine	()V
    //   1030: aload 6
    //   1032: new 1663	android/text/InputFilter$LengthFilter
    //   1035: dup
    //   1036: aload 7
    //   1038: invokestatic 1322	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   1041: invokespecial 1665	android/text/InputFilter$LengthFilter:<init>	(I)V
    //   1044: invokevirtual 1658	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1047: pop
    //   1048: aload 6
    //   1050: invokevirtual 1666	java/util/ArrayList:size	()I
    //   1053: ifle +20 -> 1073
    //   1056: aload 4
    //   1058: aload 6
    //   1060: iconst_0
    //   1061: anewarray 1668	android/text/InputFilter
    //   1064: invokevirtual 1672	java/util/ArrayList:toArray	([Ljava/lang/Object;)[Ljava/lang/Object;
    //   1067: checkcast 1674	[Landroid/text/InputFilter;
    //   1070: invokevirtual 1678	android/widget/EditText:setFilters	([Landroid/text/InputFilter;)V
    //   1073: aload_0
    //   1074: aload 5
    //   1076: ldc_w 1680
    //   1079: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1082: ifnull +15 -> 1097
    //   1085: aload 4
    //   1087: new 1682	android/text/method/PasswordTransformationMethod
    //   1090: dup
    //   1091: invokespecial 1683	android/text/method/PasswordTransformationMethod:<init>	()V
    //   1094: invokevirtual 1687	android/widget/EditText:setTransformationMethod	(Landroid/text/method/TransformationMethod;)V
    //   1097: aload_0
    //   1098: aload 5
    //   1100: ldc_w 1689
    //   1103: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1106: astore 6
    //   1108: aload 6
    //   1110: ifnull +22 -> 1132
    //   1113: aload 6
    //   1115: ldc_w 1691
    //   1118: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1121: ifeq +11 -> 1132
    //   1124: aload 4
    //   1126: ldc_w 1693
    //   1129: invokevirtual 1696	android/widget/EditText:setPrivateImeOptions	(Ljava/lang/String;)V
    //   1132: aload_0
    //   1133: aload 5
    //   1135: ldc_w 1131
    //   1138: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1141: astore 6
    //   1143: aload 6
    //   1145: ifnull +38 -> 1183
    //   1148: aload 6
    //   1150: ldc_w 1698
    //   1153: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1156: ifeq +1737 -> 2893
    //   1159: aload_0
    //   1160: aload 4
    //   1162: putfield 499	com/feelingk/iap/gui/parser/ParserXML:m_JuminText1	Landroid/widget/EditText;
    //   1165: aload_0
    //   1166: getfield 429	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNResultCallback	Lcom/feelingk/iap/gui/parser/ParserXML$ParserForeignInputMDNResultCallback;
    //   1169: ifnull +14 -> 1183
    //   1172: aload_0
    //   1173: getfield 499	com/feelingk/iap/gui/parser/ParserXML:m_JuminText1	Landroid/widget/EditText;
    //   1176: aload_0
    //   1177: getfield 591	com/feelingk/iap/gui/parser/ParserXML:mForeignDevelopeTextLengthWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$ForeignDevelopeTextLengthWatcher;
    //   1180: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1183: aload_0
    //   1184: aload 5
    //   1186: ldc_w 1055
    //   1189: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1192: astore 6
    //   1194: aload 6
    //   1196: ifnull +57 -> 1253
    //   1199: aload_0
    //   1200: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1203: new 1002	java/lang/StringBuilder
    //   1206: dup
    //   1207: aload_0
    //   1208: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   1211: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1214: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1217: aload 6
    //   1219: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1222: ldc_w 1145
    //   1225: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1228: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1231: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1234: astore 7
    //   1236: aload 4
    //   1238: aload 7
    //   1240: aload 6
    //   1242: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1245: invokevirtual 1703	android/widget/EditText:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1248: aload 7
    //   1250: invokevirtual 752	java/io/InputStream:close	()V
    //   1253: aload_0
    //   1254: aload 5
    //   1256: ldc_w 1705
    //   1259: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1262: ifnull +62 -> 1324
    //   1265: ldc_w 1707
    //   1268: invokestatic 1226	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   1271: istore_2
    //   1272: ldc_w 1709
    //   1275: invokestatic 1226	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   1278: istore_3
    //   1279: aload 4
    //   1281: new 1711	android/content/res/ColorStateList
    //   1284: dup
    //   1285: iconst_2
    //   1286: anewarray 1713	[I
    //   1289: dup
    //   1290: iconst_0
    //   1291: iconst_1
    //   1292: newarray int
    //   1294: dup
    //   1295: iconst_0
    //   1296: ldc_w 1288
    //   1299: iastore
    //   1300: aastore
    //   1301: dup
    //   1302: iconst_1
    //   1303: iconst_0
    //   1304: newarray int
    //   1306: aastore
    //   1307: iconst_2
    //   1308: newarray int
    //   1310: dup
    //   1311: iconst_0
    //   1312: iload_2
    //   1313: iastore
    //   1314: dup
    //   1315: iconst_1
    //   1316: iload_3
    //   1317: iastore
    //   1318: invokespecial 1716	android/content/res/ColorStateList:<init>	([[I[I)V
    //   1321: invokevirtual 1719	android/widget/EditText:setTextColor	(Landroid/content/res/ColorStateList;)V
    //   1324: aload_0
    //   1325: getfield 517	com/feelingk/iap/gui/parser/ParserXML:m_DotoriSMSAuthNum	Landroid/widget/EditText;
    //   1328: ifnull +18 -> 1346
    //   1331: aload_0
    //   1332: getfield 517	com/feelingk/iap/gui/parser/ParserXML:m_DotoriSMSAuthNum	Landroid/widget/EditText;
    //   1335: new 74	com/feelingk/iap/gui/parser/ParserXML$39
    //   1338: dup
    //   1339: aload_0
    //   1340: invokespecial 1720	com/feelingk/iap/gui/parser/ParserXML$39:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;)V
    //   1343: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1346: aload_0
    //   1347: getfield 523	com/feelingk/iap/gui/parser/ParserXML:m_MDN3	Landroid/widget/EditText;
    //   1350: ifnull +14 -> 1364
    //   1353: aload_0
    //   1354: getfield 523	com/feelingk/iap/gui/parser/ParserXML:m_MDN3	Landroid/widget/EditText;
    //   1357: aload_0
    //   1358: getfield 591	com/feelingk/iap/gui/parser/ParserXML:mForeignDevelopeTextLengthWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$ForeignDevelopeTextLengthWatcher;
    //   1361: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   1364: aload_1
    //   1365: astore 4
    //   1367: aload_0
    //   1368: getfield 401	com/feelingk/iap/gui/parser/ParserXML:layoutStack	Ljava/util/Stack;
    //   1371: invokevirtual 1338	java/util/Stack:size	()I
    //   1374: ifle -1327 -> 47
    //   1377: aload_1
    //   1378: aload_0
    //   1379: aload 5
    //   1381: aload_0
    //   1382: getfield 401	com/feelingk/iap/gui/parser/ParserXML:layoutStack	Ljava/util/Stack;
    //   1385: invokevirtual 1342	java/util/Stack:peek	()Ljava/lang/Object;
    //   1388: checkcast 1344	android/view/ViewGroup
    //   1391: invokespecial 1348	com/feelingk/iap/gui/parser/ParserXML:loadLayoutParams	(Landroid/util/AttributeSet;Landroid/view/ViewGroup;)Landroid/view/ViewGroup$LayoutParams;
    //   1394: invokevirtual 1352	android/view/View:setLayoutParams	(Landroid/view/ViewGroup$LayoutParams;)V
    //   1397: aload_1
    //   1398: areturn
    //   1399: aload 6
    //   1401: ldc_w 1354
    //   1404: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1407: ifeq -1097 -> 310
    //   1410: aload 4
    //   1412: iconst_1
    //   1413: invokevirtual 1640	android/widget/RadioGroup:setOrientation	(I)V
    //   1416: goto -1106 -> 310
    //   1419: aload 4
    //   1421: iconst_5
    //   1422: invokevirtual 1641	android/widget/RadioGroup:setGravity	(I)V
    //   1425: goto -1081 -> 344
    //   1428: aload_1
    //   1429: instanceof 969
    //   1432: ifeq -1088 -> 344
    //   1435: aload_1
    //   1436: checkcast 969	android/widget/LinearLayout
    //   1439: astore 4
    //   1441: aload_0
    //   1442: aload 5
    //   1444: ldc_w 1131
    //   1447: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1450: astore 6
    //   1452: aload 6
    //   1454: ifnull +27 -> 1481
    //   1457: aload 6
    //   1459: ldc_w 1722
    //   1462: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1465: ifeq +16 -> 1481
    //   1468: aload 4
    //   1470: putstatic 365	com/feelingk/iap/gui/parser/ParserXML:mJumiLlView	Landroid/widget/LinearLayout;
    //   1473: getstatic 365	com/feelingk/iap/gui/parser/ParserXML:mJumiLlView	Landroid/widget/LinearLayout;
    //   1476: bipush 8
    //   1478: invokevirtual 1141	android/widget/LinearLayout:setVisibility	(I)V
    //   1481: aload_0
    //   1482: aload 5
    //   1484: ldc_w 1043
    //   1487: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1490: astore 6
    //   1492: aload 6
    //   1494: ifnull +20 -> 1514
    //   1497: aload 6
    //   1499: ldc_w 1049
    //   1502: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1505: ifeq +186 -> 1691
    //   1508: aload 4
    //   1510: iconst_0
    //   1511: invokevirtual 1053	android/widget/LinearLayout:setOrientation	(I)V
    //   1514: aload_0
    //   1515: aload 5
    //   1517: ldc_w 1055
    //   1520: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1523: astore 6
    //   1525: aload 6
    //   1527: ifnull +57 -> 1584
    //   1530: aload_0
    //   1531: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1534: new 1002	java/lang/StringBuilder
    //   1537: dup
    //   1538: aload_0
    //   1539: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   1542: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1545: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1548: aload 6
    //   1550: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1553: ldc_w 1145
    //   1556: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1559: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1562: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1565: astore 7
    //   1567: aload 4
    //   1569: aload 7
    //   1571: aload 6
    //   1573: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1576: invokevirtual 1090	android/widget/LinearLayout:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   1579: aload 7
    //   1581: invokevirtual 752	java/io/InputStream:close	()V
    //   1584: aload_0
    //   1585: aload 5
    //   1587: ldc_w 1092
    //   1590: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1593: ifnull +11 -> 1604
    //   1596: aload 4
    //   1598: ldc_w 1093
    //   1601: invokevirtual 1096	android/widget/LinearLayout:setBackgroundColor	(I)V
    //   1604: aload_0
    //   1605: aload 5
    //   1607: ldc_w 1098
    //   1610: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1613: astore 6
    //   1615: aload 6
    //   1617: ifnull +21 -> 1638
    //   1620: aload 6
    //   1622: ldc_w 1100
    //   1625: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1628: ifeq +83 -> 1711
    //   1631: aload 4
    //   1633: bipush 17
    //   1635: invokevirtual 1103	android/widget/LinearLayout:setGravity	(I)V
    //   1638: aload_0
    //   1639: aload 5
    //   1641: ldc_w 1105
    //   1644: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1647: astore 6
    //   1649: aload 6
    //   1651: ifnull +19 -> 1670
    //   1654: aload_0
    //   1655: aload 6
    //   1657: invokespecial 1109	com/feelingk/iap/gui/parser/ParserXML:readDPSize	(Ljava/lang/String;)I
    //   1660: istore_2
    //   1661: aload 4
    //   1663: iload_2
    //   1664: iload_2
    //   1665: iload_2
    //   1666: iload_2
    //   1667: invokevirtual 1113	android/widget/LinearLayout:setPadding	(IIII)V
    //   1670: aload_0
    //   1671: aload 5
    //   1673: ldc_w 1115
    //   1676: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1679: ifnull -1335 -> 344
    //   1682: aload 4
    //   1684: iconst_1
    //   1685: invokevirtual 1118	android/widget/LinearLayout:setFocusableInTouchMode	(Z)V
    //   1688: goto -1344 -> 344
    //   1691: aload 6
    //   1693: ldc_w 1354
    //   1696: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1699: ifeq -185 -> 1514
    //   1702: aload 4
    //   1704: iconst_1
    //   1705: invokevirtual 1053	android/widget/LinearLayout:setOrientation	(I)V
    //   1708: goto -194 -> 1514
    //   1711: aload 4
    //   1713: iconst_5
    //   1714: invokevirtual 1103	android/widget/LinearLayout:setGravity	(I)V
    //   1717: goto -79 -> 1638
    //   1720: aload 4
    //   1722: bipush 19
    //   1724: invokevirtual 1250	android/widget/TextView:setGravity	(I)V
    //   1727: goto -1226 -> 501
    //   1730: aload_1
    //   1731: instanceof 1638
    //   1734: ifeq +437 -> 2171
    //   1737: aload_1
    //   1738: checkcast 1638	android/widget/RadioButton
    //   1741: astore 4
    //   1743: aload_0
    //   1744: aload 5
    //   1746: ldc_w 1131
    //   1749: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1752: astore 6
    //   1754: aload_0
    //   1755: aload 5
    //   1757: ldc_w 1260
    //   1760: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1763: astore 9
    //   1765: aload_0
    //   1766: aload 5
    //   1768: ldc_w 1724
    //   1771: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   1774: astore 8
    //   1776: aload 6
    //   1778: ifnull +51 -> 1829
    //   1781: aload 6
    //   1783: ldc_w 1726
    //   1786: invokevirtual 1374	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   1789: iconst_m1
    //   1790: if_icmpeq +39 -> 1829
    //   1793: aload 6
    //   1795: ldc_w 1728
    //   1798: invokevirtual 1731	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1801: ifeq +324 -> 2125
    //   1804: aload_0
    //   1805: getfield 626	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNClickLisener	Lcom/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN;
    //   1808: aload 4
    //   1810: invokevirtual 1735	com/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN:setRadioButton1	(Landroid/widget/RadioButton;)V
    //   1813: aload 4
    //   1815: aload 6
    //   1817: invokevirtual 1736	android/widget/RadioButton:setTag	(Ljava/lang/Object;)V
    //   1820: aload 4
    //   1822: aload_0
    //   1823: getfield 626	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNClickLisener	Lcom/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN;
    //   1826: invokevirtual 1737	android/widget/RadioButton:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   1829: aload 9
    //   1831: ifnull -920 -> 911
    //   1834: aload 8
    //   1836: ifnull -925 -> 911
    //   1839: new 1264	android/graphics/drawable/StateListDrawable
    //   1842: dup
    //   1843: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   1846: astore 6
    //   1848: new 1264	android/graphics/drawable/StateListDrawable
    //   1851: dup
    //   1852: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   1855: astore 7
    //   1857: aload_0
    //   1858: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1861: new 1002	java/lang/StringBuilder
    //   1864: dup
    //   1865: aload_0
    //   1866: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   1869: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1872: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1875: aload 9
    //   1877: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1880: ldc_w 1145
    //   1883: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1886: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1889: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1892: astore 10
    //   1894: aload 10
    //   1896: aload 9
    //   1898: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1901: astore 9
    //   1903: aload 10
    //   1905: invokevirtual 752	java/io/InputStream:close	()V
    //   1908: aload_0
    //   1909: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1912: new 1002	java/lang/StringBuilder
    //   1915: dup
    //   1916: aload_0
    //   1917: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   1920: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   1923: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1926: aload 8
    //   1928: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1931: ldc_w 1145
    //   1934: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1937: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1940: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   1943: astore 10
    //   1945: aload 10
    //   1947: aload 8
    //   1949: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   1952: astore 8
    //   1954: aload 10
    //   1956: invokevirtual 752	java/io/InputStream:close	()V
    //   1959: aload 6
    //   1961: iconst_2
    //   1962: newarray int
    //   1964: dup
    //   1965: iconst_0
    //   1966: ldc_w 1594
    //   1969: iastore
    //   1970: dup
    //   1971: iconst_1
    //   1972: ldc_w 1593
    //   1975: iastore
    //   1976: aload 9
    //   1978: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   1981: aload 6
    //   1983: iconst_2
    //   1984: newarray int
    //   1986: dup
    //   1987: iconst_0
    //   1988: ldc_w 1595
    //   1991: iastore
    //   1992: dup
    //   1993: iconst_1
    //   1994: ldc_w 1593
    //   1997: iastore
    //   1998: aload 8
    //   2000: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2003: aload 7
    //   2005: iconst_2
    //   2006: newarray int
    //   2008: dup
    //   2009: iconst_0
    //   2010: ldc_w 1592
    //   2013: iastore
    //   2014: dup
    //   2015: iconst_1
    //   2016: ldc_w 1593
    //   2019: iastore
    //   2020: aconst_null
    //   2021: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2024: aload 7
    //   2026: iconst_2
    //   2027: newarray int
    //   2029: dup
    //   2030: iconst_0
    //   2031: ldc_w 1594
    //   2034: iastore
    //   2035: dup
    //   2036: iconst_1
    //   2037: ldc_w 1288
    //   2040: iastore
    //   2041: aconst_null
    //   2042: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2045: aload 7
    //   2047: iconst_2
    //   2048: newarray int
    //   2050: dup
    //   2051: iconst_0
    //   2052: ldc_w 1595
    //   2055: iastore
    //   2056: dup
    //   2057: iconst_1
    //   2058: ldc_w 1288
    //   2061: iastore
    //   2062: aconst_null
    //   2063: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2066: aload 7
    //   2068: iconst_2
    //   2069: newarray int
    //   2071: dup
    //   2072: iconst_0
    //   2073: ldc_w 1594
    //   2076: iastore
    //   2077: dup
    //   2078: iconst_1
    //   2079: ldc_w 1593
    //   2082: iastore
    //   2083: aconst_null
    //   2084: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2087: aload 7
    //   2089: iconst_2
    //   2090: newarray int
    //   2092: dup
    //   2093: iconst_0
    //   2094: ldc_w 1595
    //   2097: iastore
    //   2098: dup
    //   2099: iconst_1
    //   2100: ldc_w 1593
    //   2103: iastore
    //   2104: aconst_null
    //   2105: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2108: aload 4
    //   2110: aload 7
    //   2112: invokevirtual 1738	android/widget/RadioButton:setButtonDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2115: aload 4
    //   2117: aload 6
    //   2119: invokevirtual 1739	android/widget/RadioButton:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2122: goto -1211 -> 911
    //   2125: aload 6
    //   2127: ldc_w 1741
    //   2130: invokevirtual 1731	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2133: ifeq +15 -> 2148
    //   2136: aload_0
    //   2137: getfield 626	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNClickLisener	Lcom/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN;
    //   2140: aload 4
    //   2142: invokevirtual 1744	com/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN:setRadioButton2	(Landroid/widget/RadioButton;)V
    //   2145: goto -332 -> 1813
    //   2148: aload 6
    //   2150: ldc_w 1746
    //   2153: invokevirtual 1731	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   2156: ifeq -343 -> 1813
    //   2159: aload_0
    //   2160: getfield 626	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNClickLisener	Lcom/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN;
    //   2163: aload 4
    //   2165: invokevirtual 1749	com/feelingk/iap/gui/parser/ParserXML$OnClickForeignInputMDN:setRadioButton3	(Landroid/widget/RadioButton;)V
    //   2168: goto -355 -> 1813
    //   2171: aload_1
    //   2172: instanceof 984
    //   2175: ifeq -1264 -> 911
    //   2178: aload_0
    //   2179: aload 5
    //   2181: ldc_w 1258
    //   2184: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2187: astore 8
    //   2189: aload_0
    //   2190: aload 5
    //   2192: ldc_w 1260
    //   2195: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2198: astore 4
    //   2200: aload_0
    //   2201: aload 5
    //   2203: ldc_w 1131
    //   2206: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2209: astore 6
    //   2211: aload_1
    //   2212: checkcast 984	android/widget/Button
    //   2215: astore 7
    //   2217: aload_0
    //   2218: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2221: new 1002	java/lang/StringBuilder
    //   2224: dup
    //   2225: aload_0
    //   2226: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   2229: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2232: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2235: aload 8
    //   2237: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2240: ldc_w 1145
    //   2243: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2246: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2249: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2252: astore 9
    //   2254: aload 9
    //   2256: aload 8
    //   2258: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2261: astore 8
    //   2263: aload 9
    //   2265: invokevirtual 752	java/io/InputStream:close	()V
    //   2268: aload_0
    //   2269: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2272: new 1002	java/lang/StringBuilder
    //   2275: dup
    //   2276: aload_0
    //   2277: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   2280: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2283: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2286: ldc_w 1751
    //   2289: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2292: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2295: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2298: astore 10
    //   2300: aload 10
    //   2302: ldc_w 1753
    //   2305: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2308: astore 9
    //   2310: aload 10
    //   2312: invokevirtual 752	java/io/InputStream:close	()V
    //   2315: aload_0
    //   2316: invokevirtual 729	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2319: new 1002	java/lang/StringBuilder
    //   2322: dup
    //   2323: aload_0
    //   2324: invokespecial 1060	com/feelingk/iap/gui/parser/ParserXML:getResourcePath	()Ljava/lang/String;
    //   2327: invokestatic 1063	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   2330: invokespecial 1006	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   2333: aload 4
    //   2335: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2338: ldc_w 1145
    //   2341: invokevirtual 1010	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2344: invokevirtual 1013	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2347: invokevirtual 735	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   2350: astore 11
    //   2352: aload 11
    //   2354: aload 4
    //   2356: invokestatic 1071	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   2359: astore 10
    //   2361: aload 11
    //   2363: invokevirtual 752	java/io/InputStream:close	()V
    //   2366: new 1264	android/graphics/drawable/StateListDrawable
    //   2369: dup
    //   2370: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2373: astore 11
    //   2375: aload_0
    //   2376: new 1264	android/graphics/drawable/StateListDrawable
    //   2379: dup
    //   2380: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2383: putfield 875	com/feelingk/iap/gui/parser/ParserXML:dotoriInactive	Landroid/graphics/drawable/StateListDrawable;
    //   2386: aload_0
    //   2387: new 1264	android/graphics/drawable/StateListDrawable
    //   2390: dup
    //   2391: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2394: putfield 906	com/feelingk/iap/gui/parser/ParserXML:mInactiveOCBRegDrawables	Landroid/graphics/drawable/StateListDrawable;
    //   2397: aload_0
    //   2398: new 1264	android/graphics/drawable/StateListDrawable
    //   2401: dup
    //   2402: invokespecial 1265	android/graphics/drawable/StateListDrawable:<init>	()V
    //   2405: putfield 903	com/feelingk/iap/gui/parser/ParserXML:mActiveOCBRegDrawables	Landroid/graphics/drawable/StateListDrawable;
    //   2408: aload 11
    //   2410: iconst_1
    //   2411: newarray int
    //   2413: dup
    //   2414: iconst_0
    //   2415: ldc_w 1288
    //   2418: iastore
    //   2419: aload 10
    //   2421: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2424: aload 11
    //   2426: iconst_0
    //   2427: newarray int
    //   2429: aload 8
    //   2431: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2434: aload_0
    //   2435: getfield 875	com/feelingk/iap/gui/parser/ParserXML:dotoriInactive	Landroid/graphics/drawable/StateListDrawable;
    //   2438: iconst_1
    //   2439: newarray int
    //   2441: dup
    //   2442: iconst_0
    //   2443: ldc_w 1288
    //   2446: iastore
    //   2447: aload 9
    //   2449: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2452: aload_0
    //   2453: getfield 875	com/feelingk/iap/gui/parser/ParserXML:dotoriInactive	Landroid/graphics/drawable/StateListDrawable;
    //   2456: iconst_0
    //   2457: newarray int
    //   2459: aload 9
    //   2461: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2464: aload 6
    //   2466: ifnull +53 -> 2519
    //   2469: aload 6
    //   2471: ldc_w 1755
    //   2474: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2477: ifne +36 -> 2513
    //   2480: aload 6
    //   2482: ldc_w 1757
    //   2485: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2488: ifne +25 -> 2513
    //   2491: aload 6
    //   2493: ldc_w 1759
    //   2496: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2499: ifne +14 -> 2513
    //   2502: aload 6
    //   2504: ldc_w 1761
    //   2507: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2510: ifeq +9 -> 2519
    //   2513: aload_0
    //   2514: aload 11
    //   2516: putfield 878	com/feelingk/iap/gui/parser/ParserXML:mOkDrawbles	Landroid/graphics/drawable/StateListDrawable;
    //   2519: aload 7
    //   2521: aload 11
    //   2523: invokevirtual 1302	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2526: aload_0
    //   2527: aload 5
    //   2529: ldc_w 1105
    //   2532: invokespecial 1047	com/feelingk/iap/gui/parser/ParserXML:findAttribute	(Landroid/util/AttributeSet;Ljava/lang/String;)Ljava/lang/String;
    //   2535: astore 9
    //   2537: aload 9
    //   2539: ifnull +19 -> 2558
    //   2542: aload_0
    //   2543: aload 9
    //   2545: invokespecial 1109	com/feelingk/iap/gui/parser/ParserXML:readDPSize	(Ljava/lang/String;)I
    //   2548: istore_2
    //   2549: aload 7
    //   2551: iload_2
    //   2552: iload_2
    //   2553: iload_2
    //   2554: iload_2
    //   2555: invokevirtual 1308	android/widget/Button:setPadding	(IIII)V
    //   2558: aload 4
    //   2560: ldc_w 1763
    //   2563: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2566: ifeq +178 -> 2744
    //   2569: aload 7
    //   2571: aload_0
    //   2572: getfield 596	com/feelingk/iap/gui/parser/ParserXML:okAuthBtn	Landroid/view/View$OnClickListener;
    //   2575: invokevirtual 1337	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2578: aload 6
    //   2580: ifnull -1669 -> 911
    //   2583: aload_0
    //   2584: aload 11
    //   2586: putfield 903	com/feelingk/iap/gui/parser/ParserXML:mActiveOCBRegDrawables	Landroid/graphics/drawable/StateListDrawable;
    //   2589: aload_0
    //   2590: getfield 906	com/feelingk/iap/gui/parser/ParserXML:mInactiveOCBRegDrawables	Landroid/graphics/drawable/StateListDrawable;
    //   2593: iconst_1
    //   2594: newarray int
    //   2596: dup
    //   2597: iconst_0
    //   2598: ldc_w 1288
    //   2601: iastore
    //   2602: aload 8
    //   2604: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2607: aload_0
    //   2608: getfield 906	com/feelingk/iap/gui/parser/ParserXML:mInactiveOCBRegDrawables	Landroid/graphics/drawable/StateListDrawable;
    //   2611: iconst_0
    //   2612: newarray int
    //   2614: aload 8
    //   2616: invokevirtual 1292	android/graphics/drawable/StateListDrawable:addState	([ILandroid/graphics/drawable/Drawable;)V
    //   2619: aload 6
    //   2621: ldc_w 1765
    //   2624: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2627: ifeq +152 -> 2779
    //   2630: aload_0
    //   2631: aload 7
    //   2633: putfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2636: aload_0
    //   2637: getfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2640: aload_0
    //   2641: getfield 906	com/feelingk/iap/gui/parser/ParserXML:mInactiveOCBRegDrawables	Landroid/graphics/drawable/StateListDrawable;
    //   2644: invokevirtual 1302	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2647: aload_0
    //   2648: getfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2651: iconst_0
    //   2652: invokevirtual 1331	android/widget/Button:setClickable	(Z)V
    //   2655: aload_0
    //   2656: getfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2659: ldc_w 1328
    //   2662: invokestatic 1226	android/graphics/Color:parseColor	(Ljava/lang/String;)I
    //   2665: invokevirtual 1305	android/widget/Button:setTextColor	(I)V
    //   2668: aload_0
    //   2669: getfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2672: iconst_0
    //   2673: iconst_0
    //   2674: iconst_0
    //   2675: iconst_0
    //   2676: invokevirtual 1308	android/widget/Button:setPadding	(IIII)V
    //   2679: aload_0
    //   2680: getfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2683: ifnull +11 -> 2694
    //   2686: aload_0
    //   2687: getfield 551	com/feelingk/iap/gui/parser/ParserXML:mOCBRegBtn	Landroid/widget/Button;
    //   2690: iconst_0
    //   2691: invokevirtual 1331	android/widget/Button:setClickable	(Z)V
    //   2694: aload 6
    //   2696: ldc_w 1755
    //   2699: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2702: ifne +14 -> 2716
    //   2705: aload 6
    //   2707: ldc_w 1757
    //   2710: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2713: ifeq -1802 -> 911
    //   2716: aload_0
    //   2717: aload 7
    //   2719: putfield 886	com/feelingk/iap/gui/parser/ParserXML:mOkBtn	Landroid/widget/Button;
    //   2722: aload_0
    //   2723: getfield 886	com/feelingk/iap/gui/parser/ParserXML:mOkBtn	Landroid/widget/Button;
    //   2726: aload_0
    //   2727: getfield 875	com/feelingk/iap/gui/parser/ParserXML:dotoriInactive	Landroid/graphics/drawable/StateListDrawable;
    //   2730: invokevirtual 1302	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2733: aload_0
    //   2734: getfield 886	com/feelingk/iap/gui/parser/ParserXML:mOkBtn	Landroid/widget/Button;
    //   2737: iconst_0
    //   2738: invokevirtual 1331	android/widget/Button:setClickable	(Z)V
    //   2741: goto -1830 -> 911
    //   2744: aload 4
    //   2746: ldc_w 1767
    //   2749: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2752: ifeq +15 -> 2767
    //   2755: aload 7
    //   2757: aload_0
    //   2758: getfield 602	com/feelingk/iap/gui/parser/ParserXML:commonBtn	Landroid/view/View$OnClickListener;
    //   2761: invokevirtual 1337	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2764: goto -186 -> 2578
    //   2767: aload 7
    //   2769: aload_0
    //   2770: getfield 599	com/feelingk/iap/gui/parser/ParserXML:cancelAuthBtn	Landroid/view/View$OnClickListener;
    //   2773: invokevirtual 1337	android/widget/Button:setOnClickListener	(Landroid/view/View$OnClickListener;)V
    //   2776: goto -198 -> 2578
    //   2779: aload 6
    //   2781: ldc_w 1769
    //   2784: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2787: ifeq +15 -> 2802
    //   2790: aload 7
    //   2792: iconst_0
    //   2793: iconst_0
    //   2794: iconst_0
    //   2795: iconst_0
    //   2796: invokevirtual 1308	android/widget/Button:setPadding	(IIII)V
    //   2799: goto -120 -> 2679
    //   2802: aload 6
    //   2804: ldc_w 1759
    //   2807: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2810: ifeq +31 -> 2841
    //   2813: aload_0
    //   2814: aload 7
    //   2816: putfield 881	com/feelingk/iap/gui/parser/ParserXML:mCultureOKButton	Landroid/widget/Button;
    //   2819: aload_0
    //   2820: getfield 881	com/feelingk/iap/gui/parser/ParserXML:mCultureOKButton	Landroid/widget/Button;
    //   2823: aload_0
    //   2824: getfield 875	com/feelingk/iap/gui/parser/ParserXML:dotoriInactive	Landroid/graphics/drawable/StateListDrawable;
    //   2827: invokevirtual 1302	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2830: aload_0
    //   2831: getfield 881	com/feelingk/iap/gui/parser/ParserXML:mCultureOKButton	Landroid/widget/Button;
    //   2834: iconst_0
    //   2835: invokevirtual 1331	android/widget/Button:setClickable	(Z)V
    //   2838: goto -159 -> 2679
    //   2841: aload 6
    //   2843: ldc_w 1761
    //   2846: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2849: ifeq -170 -> 2679
    //   2852: aload_0
    //   2853: aload 7
    //   2855: putfield 872	com/feelingk/iap/gui/parser/ParserXML:mOKCashbackOKButton	Landroid/widget/Button;
    //   2858: aload_0
    //   2859: getfield 872	com/feelingk/iap/gui/parser/ParserXML:mOKCashbackOKButton	Landroid/widget/Button;
    //   2862: aload_0
    //   2863: getfield 875	com/feelingk/iap/gui/parser/ParserXML:dotoriInactive	Landroid/graphics/drawable/StateListDrawable;
    //   2866: invokevirtual 1302	android/widget/Button:setBackgroundDrawable	(Landroid/graphics/drawable/Drawable;)V
    //   2869: aload_0
    //   2870: getfield 872	com/feelingk/iap/gui/parser/ParserXML:mOKCashbackOKButton	Landroid/widget/Button;
    //   2873: iconst_0
    //   2874: invokevirtual 1331	android/widget/Button:setClickable	(Z)V
    //   2877: goto -198 -> 2679
    //   2880: aload 4
    //   2882: aload 7
    //   2884: invokestatic 1322	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   2887: invokevirtual 1647	android/widget/EditText:setInputType	(I)V
    //   2890: goto -1881 -> 1009
    //   2893: aload 6
    //   2895: ldc_w 1771
    //   2898: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2901: ifeq +30 -> 2931
    //   2904: aload_0
    //   2905: aload 4
    //   2907: putfield 501	com/feelingk/iap/gui/parser/ParserXML:m_JuminText2	Landroid/widget/EditText;
    //   2910: aload_0
    //   2911: getfield 429	com/feelingk/iap/gui/parser/ParserXML:onForeignInputMDNResultCallback	Lcom/feelingk/iap/gui/parser/ParserXML$ParserForeignInputMDNResultCallback;
    //   2914: ifnull -1731 -> 1183
    //   2917: aload_0
    //   2918: getfield 501	com/feelingk/iap/gui/parser/ParserXML:m_JuminText2	Landroid/widget/EditText;
    //   2921: aload_0
    //   2922: getfield 591	com/feelingk/iap/gui/parser/ParserXML:mForeignDevelopeTextLengthWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$ForeignDevelopeTextLengthWatcher;
    //   2925: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2928: goto -1745 -> 1183
    //   2931: aload 6
    //   2933: ldc_w 1773
    //   2936: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2939: ifeq +30 -> 2969
    //   2942: aload_0
    //   2943: aload 4
    //   2945: putfield 503	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText1	Landroid/widget/EditText;
    //   2948: aload_0
    //   2949: getfield 503	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText1	Landroid/widget/EditText;
    //   2952: new 100	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher
    //   2955: dup
    //   2956: aload_0
    //   2957: aload 4
    //   2959: aconst_null
    //   2960: invokespecial 1776	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;Lcom/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher;)V
    //   2963: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   2966: goto -1783 -> 1183
    //   2969: aload 6
    //   2971: ldc_w 1778
    //   2974: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2977: ifeq +30 -> 3007
    //   2980: aload_0
    //   2981: aload 4
    //   2983: putfield 505	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText2	Landroid/widget/EditText;
    //   2986: aload_0
    //   2987: getfield 505	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText2	Landroid/widget/EditText;
    //   2990: new 100	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher
    //   2993: dup
    //   2994: aload_0
    //   2995: aload 4
    //   2997: aconst_null
    //   2998: invokespecial 1776	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;Lcom/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher;)V
    //   3001: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3004: goto -1821 -> 1183
    //   3007: aload 6
    //   3009: ldc_w 1780
    //   3012: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3015: ifeq +30 -> 3045
    //   3018: aload_0
    //   3019: aload 4
    //   3021: putfield 507	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText3	Landroid/widget/EditText;
    //   3024: aload_0
    //   3025: getfield 507	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText3	Landroid/widget/EditText;
    //   3028: new 100	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher
    //   3031: dup
    //   3032: aload_0
    //   3033: aload 4
    //   3035: aconst_null
    //   3036: invokespecial 1776	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;Lcom/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher;)V
    //   3039: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3042: goto -1859 -> 1183
    //   3045: aload 6
    //   3047: ldc_w 1782
    //   3050: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3053: ifeq +12 -> 3065
    //   3056: aload_0
    //   3057: aload 4
    //   3059: putfield 509	com/feelingk/iap/gui/parser/ParserXML:m_OCBRegText4	Landroid/widget/EditText;
    //   3062: goto -1879 -> 1183
    //   3065: aload 6
    //   3067: ldc_w 1784
    //   3070: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3073: ifeq +28 -> 3101
    //   3076: aload_0
    //   3077: aload 4
    //   3079: putfield 511	com/feelingk/iap/gui/parser/ParserXML:m_OCBPWText	Landroid/widget/EditText;
    //   3082: aload_0
    //   3083: getfield 511	com/feelingk/iap/gui/parser/ParserXML:m_OCBPWText	Landroid/widget/EditText;
    //   3086: new 103	com/feelingk/iap/gui/parser/ParserXML$OKCashbackTextWatcher
    //   3089: dup
    //   3090: aload_0
    //   3091: aconst_null
    //   3092: invokespecial 1787	com/feelingk/iap/gui/parser/ParserXML$OKCashbackTextWatcher:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Lcom/feelingk/iap/gui/parser/ParserXML$OKCashbackTextWatcher;)V
    //   3095: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3098: goto -1915 -> 1183
    //   3101: aload 6
    //   3103: ldc_w 1789
    //   3106: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3109: ifeq +39 -> 3148
    //   3112: aload_0
    //   3113: aload 4
    //   3115: putfield 513	com/feelingk/iap/gui/parser/ParserXML:m_CultureLandID	Landroid/widget/EditText;
    //   3118: getstatic 389	com/feelingk/iap/gui/parser/ParserXML:mCultureLoginFlag	Z
    //   3121: ifeq +13 -> 3134
    //   3124: aload_0
    //   3125: getfield 513	com/feelingk/iap/gui/parser/ParserXML:m_CultureLandID	Landroid/widget/EditText;
    //   3128: getstatic 391	com/feelingk/iap/gui/parser/ParserXML:mCultureLandID	Ljava/lang/String;
    //   3131: invokevirtual 1644	android/widget/EditText:setText	(Ljava/lang/CharSequence;)V
    //   3134: aload_0
    //   3135: getfield 513	com/feelingk/iap/gui/parser/ParserXML:m_CultureLandID	Landroid/widget/EditText;
    //   3138: aload_0
    //   3139: getfield 586	com/feelingk/iap/gui/parser/ParserXML:mCultureLandTextWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$CultureLandTextWatcher;
    //   3142: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3145: goto -1962 -> 1183
    //   3148: aload 6
    //   3150: ldc_w 1791
    //   3153: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3156: ifeq +23 -> 3179
    //   3159: aload_0
    //   3160: aload 4
    //   3162: putfield 515	com/feelingk/iap/gui/parser/ParserXML:m_CultureLandPW	Landroid/widget/EditText;
    //   3165: aload_0
    //   3166: getfield 515	com/feelingk/iap/gui/parser/ParserXML:m_CultureLandPW	Landroid/widget/EditText;
    //   3169: aload_0
    //   3170: getfield 586	com/feelingk/iap/gui/parser/ParserXML:mCultureLandTextWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$CultureLandTextWatcher;
    //   3173: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3176: goto -1993 -> 1183
    //   3179: aload 6
    //   3181: ldc_w 1793
    //   3184: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3187: ifeq +12 -> 3199
    //   3190: aload_0
    //   3191: aload 4
    //   3193: putfield 517	com/feelingk/iap/gui/parser/ParserXML:m_DotoriSMSAuthNum	Landroid/widget/EditText;
    //   3196: goto -2013 -> 1183
    //   3199: aload 6
    //   3201: ldc_w 1795
    //   3204: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3207: ifeq +41 -> 3248
    //   3210: aload_0
    //   3211: aload 4
    //   3213: putfield 519	com/feelingk/iap/gui/parser/ParserXML:m_MDN1	Landroid/widget/EditText;
    //   3216: aload_0
    //   3217: getfield 519	com/feelingk/iap/gui/parser/ParserXML:m_MDN1	Landroid/widget/EditText;
    //   3220: new 100	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher
    //   3223: dup
    //   3224: aload_0
    //   3225: aload 4
    //   3227: aconst_null
    //   3228: invokespecial 1776	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;Lcom/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher;)V
    //   3231: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3234: aload_0
    //   3235: getfield 519	com/feelingk/iap/gui/parser/ParserXML:m_MDN1	Landroid/widget/EditText;
    //   3238: aload_0
    //   3239: getfield 591	com/feelingk/iap/gui/parser/ParserXML:mForeignDevelopeTextLengthWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$ForeignDevelopeTextLengthWatcher;
    //   3242: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3245: goto -2062 -> 1183
    //   3248: aload 6
    //   3250: ldc_w 1797
    //   3253: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3256: ifeq +41 -> 3297
    //   3259: aload_0
    //   3260: aload 4
    //   3262: putfield 521	com/feelingk/iap/gui/parser/ParserXML:m_MDN2	Landroid/widget/EditText;
    //   3265: aload_0
    //   3266: getfield 521	com/feelingk/iap/gui/parser/ParserXML:m_MDN2	Landroid/widget/EditText;
    //   3269: new 100	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher
    //   3272: dup
    //   3273: aload_0
    //   3274: aload 4
    //   3276: aconst_null
    //   3277: invokespecial 1776	com/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher:<init>	(Lcom/feelingk/iap/gui/parser/ParserXML;Landroid/widget/EditText;Lcom/feelingk/iap/gui/parser/ParserXML$NextFocusTextWatcher;)V
    //   3280: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3283: aload_0
    //   3284: getfield 521	com/feelingk/iap/gui/parser/ParserXML:m_MDN2	Landroid/widget/EditText;
    //   3287: aload_0
    //   3288: getfield 591	com/feelingk/iap/gui/parser/ParserXML:mForeignDevelopeTextLengthWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$ForeignDevelopeTextLengthWatcher;
    //   3291: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3294: goto -2111 -> 1183
    //   3297: aload 6
    //   3299: ldc_w 1799
    //   3302: invokevirtual 684	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   3305: ifeq -2122 -> 1183
    //   3308: aload_0
    //   3309: aload 4
    //   3311: putfield 523	com/feelingk/iap/gui/parser/ParserXML:m_MDN3	Landroid/widget/EditText;
    //   3314: aload_0
    //   3315: getfield 523	com/feelingk/iap/gui/parser/ParserXML:m_MDN3	Landroid/widget/EditText;
    //   3318: aload_0
    //   3319: getfield 591	com/feelingk/iap/gui/parser/ParserXML:mForeignDevelopeTextLengthWatcher	Lcom/feelingk/iap/gui/parser/ParserXML$ForeignDevelopeTextLengthWatcher;
    //   3322: invokevirtual 1702	android/widget/EditText:addTextChangedListener	(Landroid/text/TextWatcher;)V
    //   3325: goto -2142 -> 1183
    //   3328: astore 6
    //   3330: goto -1746 -> 1584
    //   3333: astore 10
    //   3335: goto -2685 -> 650
    //   3338: astore 11
    //   3340: goto -2637 -> 703
    //   3343: astore 10
    //   3345: goto -1437 -> 1908
    //   3348: astore 10
    //   3350: goto -1391 -> 1959
    //   3353: astore 9
    //   3355: goto -1087 -> 2268
    //   3358: astore 10
    //   3360: goto -1045 -> 2315
    //   3363: astore 11
    //   3365: goto -999 -> 2366
    //   3368: astore 6
    //   3370: goto -2117 -> 1253
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3373	0	this	ParserXML
    //   0	3373	1	paramXmlPullParser	XmlPullParser
    //   558	1997	2	i	int
    //   1278	39	3	j	int
    //   9	3301	4	localObject1	Object
    //   15	2513	5	localAttributeSet	AttributeSet
    //   6	3292	6	localObject2	Object
    //   3328	1	6	localIOException1	IOException
    //   3368	1	6	localIOException2	IOException
    //   377	2506	7	localObject3	Object
    //   388	2227	8	localObject4	Object
    //   399	2145	9	localObject5	Object
    //   3353	1	9	localIOException3	IOException
    //   410	2010	10	localObject6	Object
    //   3333	1	10	localIOException4	IOException
    //   3343	1	10	localIOException5	IOException
    //   3348	1	10	localIOException6	IOException
    //   3358	1	10	localIOException7	IOException
    //   686	1899	11	localObject7	Object
    //   3338	1	11	localIOException8	IOException
    //   3363	1	11	localIOException9	IOException
    // Exception table:
    //   from	to	target	type
    //   1579	1584	3328	java/io/IOException
    //   645	650	3333	java/io/IOException
    //   698	703	3338	java/io/IOException
    //   1903	1908	3343	java/io/IOException
    //   1954	1959	3348	java/io/IOException
    //   2263	2268	3353	java/io/IOException
    //   2310	2315	3358	java/io/IOException
    //   2361	2366	3363	java/io/IOException
    //   1248	1253	3368	java/io/IOException
  }
  
  private View createViewAutoPurchaseFormPopup(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label212;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.context);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label212:
      label258:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1036;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = findAttribute(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = findAttribute(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i = readDPSize((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          localObject2 = findAttribute(localAttributeSet, "a:paddingleft");
          localObject3 = findAttribute(localAttributeSet, "a:paddingTop");
          localObject4 = findAttribute(localAttributeSet, "a:paddingRight");
          localObject5 = findAttribute(localAttributeSet, "a:paddingBottom");
          i = 0;
          int j = 0;
          int k = 0;
          int m = 0;
          if (localObject2 != null) {
            i = readDPSize((String)localObject2);
          }
          if (localObject3 != null) {
            j = readDPSize((String)localObject3);
          }
          if (localObject4 != null) {
            k = readDPSize((String)localObject4);
          }
          if (localObject5 != null) {
            m = readDPSize((String)localObject5);
          }
          ((LinearLayout)localObject1).setPadding(i, j, k, m);
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException5)
      {
        try
        {
          int i;
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof CheckBox))
          {
            localObject2 = findAttribute(localAttributeSet, "a:onImage");
            localObject3 = findAttribute(localAttributeSet, "a:checkid");
            localObject1 = (CheckBox)paramXmlPullParser;
            ((CheckBox)localObject1).setTag(localObject3);
            localObject3 = Build.MODEL;
            i = 0;
            if (((String)localObject3).endsWith("LT15i"))
            {
              i = 1;
              this.xperiacheckbox = true;
            }
            if (i == 0)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
            }
          }
        }
        catch (IOException localIOException5)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(getResourcePath() + "checkbox_y" + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
          }
          catch (IOException localIOException5)
          {
            try
            {
              for (;;)
              {
                ((InputStream)localObject4).close();
                localObject4 = new StateListDrawable();
                localObject5 = new StateListDrawable();
                ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
                ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
                ((StateListDrawable)localObject5).addState(new int[] { -16842910, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, -16842908 }, null);
                ((CheckBox)localObject1).setButtonDrawable((Drawable)localObject5);
                ((CheckBox)localObject1).setBackgroundDrawable((Drawable)localObject4);
                ((CheckBox)localObject1).setChecked(false);
                ((CheckBox)localObject1).setOnCheckedChangeListener(this.changeCheckBox);
                localObject1 = paramXmlPullParser;
                if (this.layoutStack.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                return paramXmlPullParser;
                label1036:
                if (!((String)localObject2).equals("vertical")) {
                  break label258;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label258;
                if (((String)localObject2).equals("right")) {
                  ((LinearLayout)localObject1).setGravity(5);
                } else if (((String)localObject2).equals("left")) {
                  ((LinearLayout)localObject1).setGravity(3);
                }
              }
              if ((paramXmlPullParser instanceof Button))
              {
                localObject3 = findAttribute(localAttributeSet, "a:offImage");
                localObject1 = findAttribute(localAttributeSet, "a:onImage");
                localObject2 = (Button)paramXmlPullParser;
                localObject4 = getClass().getResourceAsStream(getResourcePath() + (String)localObject3 + ".png");
                localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
              }
            }
            catch (IOException localIOException5)
            {
              try
              {
                ((InputStream)localObject4).close();
                localObject5 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
                localObject4 = Drawable.createFromStream((InputStream)localObject5, (String)localObject1);
              }
              catch (IOException localIOException5)
              {
                try
                {
                  for (;;)
                  {
                    Object localObject4;
                    ((InputStream)localObject5).close();
                    Object localObject5 = new StateListDrawable();
                    ((StateListDrawable)localObject5).addState(new int[] { 16842919 }, (Drawable)localObject4);
                    ((StateListDrawable)localObject5).addState(new int[0], (Drawable)localObject3);
                    ((Button)localObject2).setBackgroundDrawable((Drawable)localObject5);
                    if (((String)localObject1).equals("btn_con_sel_e"))
                    {
                      ((Button)localObject2).setOnClickListener(this.autoPurchaseFormBtn);
                    }
                    else if (((String)localObject1).equals("btn_buycancel_sel_e"))
                    {
                      ((Button)localObject2).setOnClickListener(this.cancelAutoPurchaseFormBtn);
                      continue;
                      if ((paramXmlPullParser instanceof TextView))
                      {
                        localObject1 = (TextView)paramXmlPullParser;
                        localObject2 = findAttribute(localAttributeSet, "a:nortext");
                        localObject3 = findAttribute(localAttributeSet, "a:nametext");
                        localObject4 = findAttribute(localAttributeSet, "a:centertext");
                        localObject5 = findAttribute(localAttributeSet, "a:agreetext");
                        String str1 = findAttribute(localAttributeSet, "a:textSize");
                        String str2 = findAttribute(localAttributeSet, "a:textColor");
                        String str3 = findAttribute(localAttributeSet, "a:gravity");
                        findAttribute(localAttributeSet, "a:id");
                        if (localObject2 != null) {
                          ((TextView)localObject1).setText((CharSequence)localObject2);
                        }
                        if (localObject3 != null) {
                          ((TextView)localObject1).setText(CommonString.getString(CommonString.Index.MENT_MONT_AUTO_PAYMENTS));
                        }
                        if (localObject4 != null)
                        {
                          ((String)localObject4).replace("\\n", "\n");
                          ((TextView)localObject1).setText(CommonString.getString(CommonString.Index.WORK_AUTO_FORM_STRING));
                        }
                        if (localObject5 != null) {
                          ((TextView)localObject1).setText((CharSequence)localObject5);
                        }
                        if (str1 != null) {
                          ((TextView)localObject1).setTextSize(readFontSize(str1));
                        }
                        if (str2 != null) {
                          ((TextView)localObject1).setTextColor(Color.parseColor(str2));
                        }
                        if (str3 != null)
                        {
                          if (!str3.equals("center")) {
                            break label1556;
                          }
                          ((TextView)localObject1).setGravity(17);
                        }
                        for (;;)
                        {
                          ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
                          break;
                          label1556:
                          if (str3.equals("right")) {
                            ((TextView)localObject1).setGravity(5);
                          } else if (str3.equals("left")) {
                            ((TextView)localObject1).setGravity(3);
                          } else {
                            ((TextView)localObject1).setGravity(19);
                          }
                        }
                        localIOException2 = localIOException2;
                        continue;
                        localIOException1 = localIOException1;
                        continue;
                        localIOException3 = localIOException3;
                        continue;
                        localIOException4 = localIOException4;
                      }
                    }
                  }
                  localIOException5 = localIOException5;
                }
                catch (IOException localIOException6)
                {
                  for (;;) {}
                }
              }
            }
          }
        }
      }
    }
  }
  
  private View createViewIMEIPopup(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label212;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.context);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label212:
      label258:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1036;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = findAttribute(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = findAttribute(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i = readDPSize((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          localObject2 = findAttribute(localAttributeSet, "a:paddingleft");
          localObject3 = findAttribute(localAttributeSet, "a:paddingTop");
          localObject4 = findAttribute(localAttributeSet, "a:paddingRight");
          localObject5 = findAttribute(localAttributeSet, "a:paddingBottom");
          i = 0;
          int j = 0;
          int k = 0;
          int m = 0;
          if (localObject2 != null) {
            i = readDPSize((String)localObject2);
          }
          if (localObject3 != null) {
            j = readDPSize((String)localObject3);
          }
          if (localObject4 != null) {
            k = readDPSize((String)localObject4);
          }
          if (localObject5 != null) {
            m = readDPSize((String)localObject5);
          }
          ((LinearLayout)localObject1).setPadding(i, j, k, m);
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException7)
      {
        try
        {
          int i;
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof CheckBox))
          {
            localObject2 = findAttribute(localAttributeSet, "a:onImage");
            localObject3 = findAttribute(localAttributeSet, "a:checkid");
            localObject1 = (CheckBox)paramXmlPullParser;
            ((CheckBox)localObject1).setTag(localObject3);
            localObject3 = Build.MODEL;
            i = 0;
            if (((String)localObject3).endsWith("LT15i"))
            {
              i = 1;
              this.xperiacheckbox = true;
            }
            if (i == 0)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
            }
          }
        }
        catch (IOException localIOException7)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(getResourcePath() + "checkbox_y" + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
          }
          catch (IOException localIOException7)
          {
            try
            {
              for (;;)
              {
                ((InputStream)localObject4).close();
                localObject4 = new StateListDrawable();
                localObject5 = new StateListDrawable();
                ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
                ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
                ((StateListDrawable)localObject5).addState(new int[] { -16842910, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, 16842919 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { -16842912, -16842908 }, null);
                ((StateListDrawable)localObject5).addState(new int[] { 16842912, -16842908 }, null);
                ((CheckBox)localObject1).setButtonDrawable((Drawable)localObject5);
                ((CheckBox)localObject1).setBackgroundDrawable((Drawable)localObject4);
                ((CheckBox)localObject1).setChecked(false);
                ((CheckBox)localObject1).setOnCheckedChangeListener(this.changeCheckBox);
                localObject1 = paramXmlPullParser;
                if (this.layoutStack.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                return paramXmlPullParser;
                label1036:
                if (!((String)localObject2).equals("vertical")) {
                  break label258;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label258;
                if (((String)localObject2).equals("right")) {
                  ((LinearLayout)localObject1).setGravity(5);
                } else if (((String)localObject2).equals("left")) {
                  ((LinearLayout)localObject1).setGravity(3);
                }
              }
              if ((paramXmlPullParser instanceof Button))
              {
                localObject4 = findAttribute(localAttributeSet, "a:offImage");
                localObject1 = findAttribute(localAttributeSet, "a:onImage");
                localObject2 = (Button)paramXmlPullParser;
                if (((String)localObject1).equals("bt_confirm_dim_e")) {
                  this.mIMEIOkBtn = ((Button)localObject2);
                }
                localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject4 + ".png");
                localObject4 = Drawable.createFromStream((InputStream)localObject3, (String)localObject4);
              }
            }
            catch (IOException localIOException7)
            {
              try
              {
                ((InputStream)localObject3).close();
                localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
                localObject5 = Drawable.createFromStream((InputStream)localObject3, (String)localObject1);
              }
              catch (IOException localIOException7)
              {
                try
                {
                  ((InputStream)localObject3).close();
                  localObject3 = new StateListDrawable();
                  ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject5);
                  ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                  if (((String)localObject1).equals("bt_confirm_dim_e"))
                  {
                    this.mIMEInotCheckedDrawble = ((StateListDrawable)localObject3);
                    this.mIMEICheckedDrawbles = new StateListDrawable();
                    localObject5 = getClass().getResourceAsStream(getResourcePath() + "btn_con_nor_e" + ".png");
                    localObject4 = Drawable.createFromStream((InputStream)localObject5, "btn_con_nor_e");
                  }
                }
                catch (IOException localIOException7)
                {
                  try
                  {
                    ((InputStream)localObject5).close();
                    localObject6 = getClass().getResourceAsStream(getResourcePath() + "btn_con_sel_e" + ".png");
                    localObject5 = Drawable.createFromStream((InputStream)localObject6, "btn_con_sel_e");
                  }
                  catch (IOException localIOException7)
                  {
                    try
                    {
                      for (;;)
                      {
                        Object localObject4;
                        Object localObject5;
                        Object localObject6;
                        ((InputStream)localObject6).close();
                        this.mIMEICheckedDrawbles.addState(new int[] { 16842919 }, (Drawable)localObject5);
                        this.mIMEICheckedDrawbles.addState(new int[0], (Drawable)localObject4);
                        if (this.mIMEICheckList[0] != 0)
                        {
                          this.mIMEIOkBtn.setBackgroundDrawable(this.mIMEICheckedDrawbles);
                          this.mIMEIOkBtn.setOnClickListener(this.imeiAuthBtn);
                        }
                        while (((String)localObject1).equals("btn_cancel_sel_h_e"))
                        {
                          ((Button)localObject2).setOnClickListener(this.imeiAuthCancelBtn);
                          break;
                          this.mIMEIOkBtn.setBackgroundDrawable((Drawable)localObject3);
                          continue;
                          ((Button)localObject2).setBackgroundDrawable((Drawable)localObject3);
                        }
                        if ((paramXmlPullParser instanceof TextView))
                        {
                          localObject1 = (TextView)paramXmlPullParser;
                          localObject2 = findAttribute(localAttributeSet, "a:nortext");
                          localObject3 = findAttribute(localAttributeSet, "a:nametext");
                          localObject4 = findAttribute(localAttributeSet, "a:centertext");
                          localObject5 = findAttribute(localAttributeSet, "a:agreetext");
                          localObject6 = findAttribute(localAttributeSet, "a:textSize");
                          String str1 = findAttribute(localAttributeSet, "a:textColor");
                          String str2 = findAttribute(localAttributeSet, "a:gravity");
                          findAttribute(localAttributeSet, "a:id");
                          if (localObject2 != null) {
                            ((TextView)localObject1).setText((CharSequence)localObject2);
                          }
                          if (localObject3 != null) {
                            ((TextView)localObject1).setText(CommonString.getString(CommonString.Index.MENT_PRIVACY_POLICY));
                          }
                          if (localObject4 != null)
                          {
                            ((String)localObject4).replace("\\n", "\n");
                            ((TextView)localObject1).setText(CommonString.getString(CommonString.Index.WORK_IMEIAUTH_STRING));
                          }
                          if (localObject5 != null) {
                            ((TextView)localObject1).setText((CharSequence)localObject5);
                          }
                          if (localObject6 != null) {
                            ((TextView)localObject1).setTextSize(readFontSize((String)localObject6));
                          }
                          if (str1 != null) {
                            ((TextView)localObject1).setTextColor(Color.parseColor(str1));
                          }
                          if (str2 != null)
                          {
                            if (!str2.equals("center")) {
                              break label1760;
                            }
                            ((TextView)localObject1).setGravity(17);
                          }
                          for (;;)
                          {
                            ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
                            break;
                            label1760:
                            if (str2.equals("right")) {
                              ((TextView)localObject1).setGravity(5);
                            } else if (str2.equals("left")) {
                              ((TextView)localObject1).setGravity(3);
                            } else {
                              ((TextView)localObject1).setGravity(19);
                            }
                          }
                          localIOException2 = localIOException2;
                          continue;
                          localIOException1 = localIOException1;
                          continue;
                          localIOException3 = localIOException3;
                          continue;
                          localIOException6 = localIOException6;
                          continue;
                          localIOException4 = localIOException4;
                          continue;
                          localIOException5 = localIOException5;
                        }
                      }
                      localIOException7 = localIOException7;
                    }
                    catch (IOException localIOException8)
                    {
                      for (;;) {}
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private View createViewImagePopup(XmlPullParser paramXmlPullParser, String paramString)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label153;
      }
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label153:
      label199:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label838;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = findAttribute(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = findAttribute(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            int i = readDPSize((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException3)
      {
        try
        {
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject1 = (TextView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:id");
            localObject3 = findAttribute(localAttributeSet, "a:text");
            localObject4 = findAttribute(localAttributeSet, "a:textSize");
            String str1 = findAttribute(localAttributeSet, "a:textColor");
            String str2 = findAttribute(localAttributeSet, "a:gravity");
            if (localObject3 != null) {
              ((TextView)localObject1).setText(((String)localObject3).replace("\\n", "\n"));
            }
            if (localObject4 != null) {
              ((TextView)localObject1).setTextSize(readFontSize((String)localObject4));
            }
            if (str1 != null) {
              ((TextView)localObject1).setTextColor(Color.parseColor(str1));
            }
            if (localObject2 != null) {
              ((TextView)localObject1).setText(paramString);
            }
            if (str2 != null)
            {
              ((TextView)localObject1).setGravity(17);
              ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
            }
          }
          else if ((paramXmlPullParser instanceof Button))
          {
            localObject2 = findAttribute(localAttributeSet, "a:offImage");
            paramString = findAttribute(localAttributeSet, "a:onImage");
            localObject1 = (Button)paramXmlPullParser;
            localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
            localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
          }
        }
        catch (IOException localIOException3)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(getResourcePath() + paramString + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, paramString);
          }
          catch (IOException localIOException3)
          {
            try
            {
              for (;;)
              {
                ((InputStream)localObject4).close();
                Object localObject4 = new StateListDrawable();
                ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject3);
                ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject2);
                ((Button)localObject1).setBackgroundDrawable((Drawable)localObject4);
                if (paramString.equals("btn_con_sel_e")) {
                  ((Button)localObject1).setOnClickListener(this.imageBtn);
                }
                paramString = paramXmlPullParser;
                if (this.layoutStack.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                return paramXmlPullParser;
                label838:
                if (!((String)localObject2).equals("vertical")) {
                  break label199;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label199;
                ((LinearLayout)localObject1).setGravity(5);
                continue;
                ((TextView)localObject1).setGravity(19);
                continue;
                localIOException2 = localIOException2;
                continue;
                localIOException1 = localIOException1;
              }
              localIOException3 = localIOException3;
            }
            catch (IOException localIOException4)
            {
              for (;;) {}
            }
          }
        }
      }
    }
  }
  
  private View createViewJoinPopup(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label212;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      if (((String)localObject2).equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.context);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      if (((String)localObject2).equals("CheckBox"))
      {
        paramXmlPullParser = new CheckBox(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label212:
      label258:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1225;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = findAttribute(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("dot_line")) {
            break label1245;
          }
          localObject3 = getClass().getResourceAsStream(getResourcePath() + "line_dot_01.png");
          localObject2 = Drawable.createFromStream((InputStream)localObject3, null);
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        ((BitmapDrawable)localObject2).setTileModeX(Shader.TileMode.REPEAT);
        ((BitmapDrawable)localObject2).setTileModeY(Shader.TileMode.REPEAT);
        ((LinearLayout)localObject1).setBackgroundDrawable((Drawable)localObject2);
        label359:
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          label413:
          localObject2 = findAttribute(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i = readDPSize((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          localObject2 = findAttribute(localAttributeSet, "a:paddingleft");
          localObject3 = findAttribute(localAttributeSet, "a:paddingTop");
          localObject4 = findAttribute(localAttributeSet, "a:paddingRight");
          localObject5 = findAttribute(localAttributeSet, "a:paddingBottom");
          i = 0;
          int j = 0;
          int k = 0;
          int m = 0;
          if (localObject2 != null) {
            i = readDPSize((String)localObject2);
          }
          if (localObject3 != null) {
            j = readDPSize((String)localObject3);
          }
          if (localObject4 != null) {
            k = readDPSize((String)localObject4);
          }
          if (localObject5 != null) {
            m = readDPSize((String)localObject5);
          }
          ((LinearLayout)localObject1).setPadding(i, j, k, m);
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException6)
      {
        try
        {
          int i;
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject3 = (TextView)paramXmlPullParser;
            localObject4 = findAttribute(localAttributeSet, "a:id");
            localObject2 = findAttribute(localAttributeSet, "a:text");
            localObject5 = findAttribute(localAttributeSet, "a:textSize");
            String str1 = findAttribute(localAttributeSet, "a:textColor");
            String str2 = findAttribute(localAttributeSet, "a:gravity");
            localObject1 = localObject2;
            if (localObject2 != null)
            {
              localObject1 = ((String)localObject2).replace("\\n", "\n");
              ((TextView)localObject3).setText((CharSequence)localObject1);
            }
            if (localObject5 != null) {
              ((TextView)localObject3).setTextSize(readFontSize((String)localObject5));
            }
            if (str1 != null) {
              ((TextView)localObject3).setTextColor(Color.parseColor(str1));
            }
            if (localObject4 != null) {
              ((TextView)localObject3).setText((CharSequence)localObject1);
            }
            if (str2 != null)
            {
              ((TextView)localObject3).setGravity(17);
              label814:
              ((TextView)localObject3).setLineSpacing(0.0F, 1.15F);
            }
          }
          else
          {
            if (!(paramXmlPullParser instanceof CheckBox)) {
              break label1346;
            }
            localObject1 = (CheckBox)paramXmlPullParser;
            ((CheckBox)localObject1).setTag(findAttribute(localAttributeSet, "a:checkid"));
            localObject2 = Build.MODEL;
            i = 0;
            if (((String)localObject2).endsWith("LT15i"))
            {
              i = 1;
              this.xperiacheckbox = true;
            }
            if (i == 0)
            {
              localObject2 = findAttribute(localAttributeSet, "a:onImage");
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
            }
          }
        }
        catch (IOException localIOException6)
        {
          try
          {
            ((InputStream)localObject3).close();
            localObject4 = getClass().getResourceAsStream(getResourcePath() + "checkbox_y" + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, "checkbox_y");
          }
          catch (IOException localIOException6)
          {
            try
            {
              ((InputStream)localObject4).close();
              localObject4 = new StateListDrawable();
              localObject5 = new StateListDrawable();
              ((StateListDrawable)localObject4).addState(new int[] { -16842912, -16842908 }, (Drawable)localObject2);
              ((StateListDrawable)localObject4).addState(new int[] { 16842912, -16842908 }, (Drawable)localObject3);
              ((StateListDrawable)localObject5).addState(new int[] { -16842910, -16842908 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { -16842912, 16842919 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { 16842912, 16842919 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { -16842912, -16842908 }, null);
              ((StateListDrawable)localObject5).addState(new int[] { 16842912, -16842908 }, null);
              ((CheckBox)localObject1).setBackgroundDrawable((Drawable)localObject4);
              ((CheckBox)localObject1).setButtonDrawable((Drawable)localObject5);
              ((CheckBox)localObject1).setChecked(false);
              ((CheckBox)localObject1).setOnCheckedChangeListener(this.changeCheckBox);
              label1225:
              label1245:
              label1346:
              do
              {
                do
                {
                  localObject1 = paramXmlPullParser;
                  if (this.layoutStack.size() <= 0) {
                    break;
                  }
                  paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                  return paramXmlPullParser;
                  if (!((String)localObject2).equals("vertical")) {
                    break label258;
                  }
                  ((LinearLayout)localObject1).setOrientation(1);
                  break label258;
                  localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
                  ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
                  try
                  {
                    ((InputStream)localObject3).close();
                  }
                  catch (IOException localIOException2) {}
                  break label359;
                  if (localIOException2.equals("left"))
                  {
                    ((LinearLayout)localObject1).setGravity(3);
                    break label413;
                  }
                  ((LinearLayout)localObject1).setGravity(5);
                  break label413;
                  ((TextView)localObject3).setGravity(19);
                  break label814;
                } while (!(paramXmlPullParser instanceof Button));
                localObject3 = findAttribute(localAttributeSet, "a:offImage");
                localObject1 = findAttribute(localAttributeSet, "a:onImage");
                findAttribute(localAttributeSet, "a:gravity");
              } while (localObject3 == null);
              localButton = (Button)paramXmlPullParser;
              localObject4 = getClass().getResourceAsStream(getResourcePath() + (String)localObject3 + ".png");
              localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
            }
            catch (IOException localIOException6)
            {
              try
              {
                ((InputStream)localObject4).close();
                localObject5 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
                localObject4 = Drawable.createFromStream((InputStream)localObject5, (String)localObject1);
              }
              catch (IOException localIOException6)
              {
                try
                {
                  for (;;)
                  {
                    Object localObject4;
                    Button localButton;
                    ((InputStream)localObject5).close();
                    Object localObject5 = new StateListDrawable();
                    ((StateListDrawable)localObject5).addState(new int[] { 16842919 }, (Drawable)localObject4);
                    ((StateListDrawable)localObject5).addState(new int[0], (Drawable)localObject3);
                    localButton.setBackgroundDrawable((Drawable)localObject5);
                    if (((String)localObject1).equals("btn_con_sel_e"))
                    {
                      localButton.setOnClickListener(this.okJoinBtn);
                    }
                    else if (((String)localObject1).equals("btn_cancel_sel_h_e"))
                    {
                      localButton.setOnClickListener(this.cancelJoinBtn);
                    }
                    else if (((String)localObject1).equals("btn_terms_sel_b_e"))
                    {
                      localButton.setOnClickListener(this.moreInfoFormBtn1);
                    }
                    else if (((String)localObject1).equals("btn_terms_sel_b2_e"))
                    {
                      localButton.setOnClickListener(this.moreInfoFormBtn2);
                    }
                    else if (((String)localObject1).equals("btn_policy_sel_b_e"))
                    {
                      localButton.setOnClickListener(this.moreInfoFormBtn3);
                      continue;
                      localIOException3 = localIOException3;
                      continue;
                      localIOException1 = localIOException1;
                      continue;
                      localIOException4 = localIOException4;
                      continue;
                      localIOException5 = localIOException5;
                    }
                  }
                  localIOException6 = localIOException6;
                }
                catch (IOException localIOException7)
                {
                  for (;;) {}
                }
              }
            }
          }
        }
      }
    }
  }
  
  private View createViewLguSmsAuthPopup(XmlPullParser paramXmlPullParser)
  {
    String str = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (str.equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label212;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (str.equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (str.equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (str.equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      if (str.equals("EditText"))
      {
        paramXmlPullParser = new EditText(this.context);
        break;
      }
      if (str.equals("ScrollView"))
      {
        paramXmlPullParser = new ScrollView(this.context);
        paramXmlPullParser.setScrollbarFadingEnabled(false);
        break;
      }
      Assert.fail("# UnSupported tag:" + str);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label212:
      label258:
      Object localObject2;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        str = findAttribute(localAttributeSet, "a:orientation");
        if (str != null)
        {
          if (!str.equals("horizontal")) {
            break label1719;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        str = findAttribute(localAttributeSet, "a:background");
        if (str != null)
        {
          localObject2 = getClass().getResourceAsStream(getResourcePath() + str + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject2, str));
        }
      }
      try
      {
        ((InputStream)localObject2).close();
        label328:
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        str = findAttribute(localAttributeSet, "a:gravity");
        if (str != null)
        {
          if (str.equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          label382:
          str = findAttribute(localAttributeSet, "a:padding");
          if (str != null)
          {
            i = readDPSize(str);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof EditText))
          {
            localObject1 = (EditText)paramXmlPullParser;
            ((EditText)localObject1).setGravity(5);
            ((EditText)localObject1).setInputType(2);
            str = findAttribute(localAttributeSet, "a:background");
            if (str != null)
            {
              localObject2 = getClass().getResourceAsStream(getResourcePath() + str + ".png");
              ((EditText)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject2, str));
            }
          }
        }
      }
      catch (IOException localIOException2)
      {
        try
        {
          ((InputStream)localObject2).close();
          if (findAttribute(localAttributeSet, "a:lgu_auth_text") != null)
          {
            mLguSmsAuthTv = (EditText)localObject1;
            if (IAPLib.getOTPNumber() != null)
            {
              ((EditText)localObject1).setText(IAPLib.getOTPNumber());
              ((EditText)localObject1).setTextColor(Color.parseColor("#FF6F00"));
              ((EditText)localObject1).setTextSize(30.0F);
              mLguSmsAuthBtn.setBackgroundDrawable(mReClaimDrawables);
              label586:
              mLguSmsAuthTv.addTextChangedListener(new TextWatcher()
              {
                String inputStr;
                
                public void afterTextChanged(Editable paramAnonymousEditable)
                {
                  if (this.inputStr.length() != 6) {
                    ParserXML.nextStep = Boolean.valueOf(true);
                  }
                  if ((ParserXML.nextStep.booleanValue()) && (this.inputStr.length() == 6))
                  {
                    ParserXML.nextStep = Boolean.valueOf(false);
                    IAPLib.setOTPNumber(this.inputStr);
                  }
                }
                
                public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
                
                public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
                {
                  this.inputStr = paramAnonymousCharSequence.toString();
                }
              });
            }
          }
          else
          {
            if ((paramXmlPullParser instanceof TextView))
            {
              localObject1 = (TextView)paramXmlPullParser;
              str = findAttribute(localAttributeSet, "a:text");
              findAttribute(localAttributeSet, "a:lgu_auth_text");
              findAttribute(localAttributeSet, "a:lgu_info_text");
              localObject2 = findAttribute(localAttributeSet, "a:textSize");
              localObject3 = findAttribute(localAttributeSet, "a:textColor");
              localObject4 = findAttribute(localAttributeSet, "a:gravity");
              if (str != null) {
                ((TextView)localObject1).setText(str.replace("\\n", "\n"));
              }
              if (localObject2 != null) {
                ((TextView)localObject1).setTextSize(readFontSize((String)localObject2));
              }
              if (localObject3 != null) {
                ((TextView)localObject1).setTextColor(Color.parseColor((String)localObject3));
              }
              if (localObject4 == null) {
                break label1762;
              }
              ((TextView)localObject1).setGravity(17);
              label742:
              ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
              if (IAPLib.getOTPNumber() != null)
              {
                mLguSmsAuthTv.setText(IAPLib.getOTPNumber());
                mLguSmsAuthTv.setTextColor(Color.parseColor("#FF6F00"));
                mLguSmsAuthTv.setTextSize(30.0F);
                mLguSmsAuthBtn.setText(CommonString.getString(CommonString.Index.MENT_NUMBER_RECLAIMED));
                mLguSmsAuthBtn.setTextColor(Color.parseColor("#999999"));
              }
            }
            if ((paramXmlPullParser instanceof Button))
            {
              localObject1 = findAttribute(localAttributeSet, "a:offImage");
              str = findAttribute(localAttributeSet, "a:onImage");
              localObject2 = findAttribute(localAttributeSet, "a:id");
              this.mBtn = ((Button)paramXmlPullParser);
              localObject3 = findAttribute(localAttributeSet, "a:padding");
              if (localObject3 != null)
              {
                i = readDPSize((String)localObject3);
                this.mBtn.setPadding(i, i, i, i);
              }
              if (localObject2 != null)
              {
                if (((String)localObject2).equals("lgu_smsAuth_btn")) {
                  this.mBtn.setPadding(0, 0, 0, 0);
                }
                this.mBtn.setText(CommonString.getString(CommonString.Index.MENT_NUMBER_REQUEST));
              }
              if (((String)localObject1).equals("bt_01_nor.9"))
              {
                mLguSmsAuthBtn = this.mBtn;
                mLguSmsAuthBtn.setPadding(0, 0, 0, 0);
                mLguSmsAuthBtn.setText(CommonString.getString(CommonString.Index.RECEIVE_CODE));
              }
              if (((String)localObject1).equals("bt_confirm_dim_e")) {
                mLguSmsAuthOkBtn = this.mBtn;
              }
              this.mStream = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
              this.mbtOn = Drawable.createFromStream(this.mStream, (String)localObject1);
            }
          }
        }
        catch (IOException localIOException2)
        {
          try
          {
            int i;
            Object localObject3;
            Object localObject4;
            this.mStream.close();
            this.mStream = getClass().getResourceAsStream(getResourcePath() + str + ".png");
            this.mbtOver = Drawable.createFromStream(this.mStream, str);
            try
            {
              this.mStream.close();
              this.mDrawables = new StateListDrawable();
              localObject3 = this.mDrawables;
              localObject4 = this.mbtOver;
              ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
              localObject3 = this.mDrawables;
              localObject4 = this.mbtOn;
              ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
              this.mReClaimStream = getClass().getResourceAsStream(getResourcePath() + "bt_01_reclaim_nor_e" + ".png");
              this.mLimitExcessbtOn = Drawable.createFromStream(this.mReClaimStream, "bt_01_reclaim_nor_e");
              try
              {
                this.mReClaimStream.close();
                this.mReClaimStream = getClass().getResourceAsStream(getResourcePath() + "bt_01_reclaim_sel_e" + ".png");
                this.mLimitExcessbtOver = Drawable.createFromStream(this.mReClaimStream, "bt_01_reclaim_sel_e");
                try
                {
                  this.mReClaimStream.close();
                  mReClaimDrawables = new StateListDrawable();
                  localObject3 = mReClaimDrawables;
                  localObject4 = this.mLimitExcessbtOver;
                  ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
                  localObject3 = mReClaimDrawables;
                  localObject4 = this.mLimitExcessbtOn;
                  ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                  this.mAuthOkStream = getClass().getResourceAsStream(getResourcePath() + "btn_con_nor_e" + ".png");
                  this.mAuthOkbtOn = Drawable.createFromStream(this.mAuthOkStream, "btn_con_nor_e");
                  try
                  {
                    this.mAuthOkStream.close();
                    this.mAuthOkStream = getClass().getResourceAsStream(getResourcePath() + "btn_con_sel_e" + ".png");
                    this.mAuthOkbtOver = Drawable.createFromStream(this.mAuthOkStream, "btn_con_sel_e");
                    try
                    {
                      this.mAuthOkStream.close();
                      mSmsAuthDrawables = new StateListDrawable();
                      localObject3 = mSmsAuthDrawables;
                      localObject4 = this.mAuthOkbtOver;
                      ((StateListDrawable)localObject3).addState(new int[] { 16842919 }, (Drawable)localObject4);
                      localObject3 = mSmsAuthDrawables;
                      localObject4 = this.mAuthOkbtOn;
                      ((StateListDrawable)localObject3).addState(new int[0], (Drawable)localObject4);
                      this.mBtn.setBackgroundDrawable(this.mDrawables);
                      if ((mLguSmsAuthClickFlag.booleanValue()) && (((String)localObject1).equals("bt_01_sel.9")))
                      {
                        this.mBtn.setText(CommonString.getString(CommonString.Index.MENT_NUMBER_RECLAIMED));
                        this.mBtn.setTextColor(Color.parseColor("#999999"));
                      }
                      if (IAPLib.getOTPNumber() != null)
                      {
                        mLguSmsAuthBtn.setText(CommonString.getString(CommonString.Index.MENT_NUMBER_RECLAIMED));
                        mLguSmsAuthBtn.setTextColor(Color.parseColor("#999999"));
                        mLguSmsAuthOkBtn.setBackgroundDrawable(mSmsAuthDrawables);
                        mLguSmsAuthOkBtn.setOnClickListener(okLguSmsAuthBtn);
                      }
                      if ((localObject2 != null) && (((String)localObject2).equals("lgu_smsAuth_btn"))) {
                        this.mBtn.setPadding(0, 0, 0, 0);
                      }
                      if (str.equals("bt_01_sel.9")) {
                        this.mBtn.setOnClickListener(this.getlguSmsAuthBtn);
                      }
                      label1719:
                      label1762:
                      label1821:
                      do
                      {
                        for (;;)
                        {
                          localObject1 = paramXmlPullParser;
                          if (this.layoutStack.size() <= 0) {
                            break;
                          }
                          paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                          return paramXmlPullParser;
                          if (!str.equals("vertical")) {
                            break label258;
                          }
                          ((LinearLayout)localObject1).setOrientation(1);
                          break label258;
                          ((LinearLayout)localObject1).setGravity(5);
                          break label382;
                          ((EditText)localObject1).setText(CommonString.getString(CommonString.Index.MENT_REQUEST_AUTHORIZATION));
                          break label586;
                          ((TextView)localObject1).setGravity(19);
                          break label742;
                          if (str.equals("btn_con_sel_e"))
                          {
                            this.mBtn.setOnClickListener(okLguSmsAuthBtn);
                          }
                          else
                          {
                            if (!str.equals("btn_cancel_sel_h_e")) {
                              break label1821;
                            }
                            this.mBtn.setOnClickListener(this.cancelLguSmsAuthBtn);
                          }
                        }
                      } while (!str.equals("btn_locking_sel_e"));
                      localObject1 = this.context.getPackageManager().getInstalledApplications(0);
                      int j = ((List)localObject1).size();
                      i = 0;
                      for (;;)
                      {
                        if (i >= j)
                        {
                          this.mBtn.setOnClickListener(this.tStoreInfoBtn);
                          break;
                        }
                        if (((ApplicationInfo)((List)localObject1).get(i)).packageName.indexOf("com.skt.skaf.A000Z00040") == 0) {
                          this.tStoreFlag = true;
                        }
                        i += 1;
                      }
                      localIOException1 = localIOException1;
                      break label328;
                      localIOException2 = localIOException2;
                    }
                    catch (IOException localIOException3)
                    {
                      for (;;) {}
                    }
                  }
                  catch (IOException localIOException4)
                  {
                    for (;;) {}
                  }
                }
                catch (IOException localIOException5)
                {
                  for (;;) {}
                }
              }
              catch (IOException localIOException6)
              {
                for (;;) {}
              }
            }
            catch (IOException localIOException7)
            {
              for (;;) {}
            }
          }
          catch (IOException localIOException8)
          {
            for (;;) {}
          }
        }
      }
    }
  }
  
  private View createViewOtpPopup(XmlPullParser paramXmlPullParser)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label181;
      }
      localObject1 = null;
    }
    for (;;)
    {
      return localObject1;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      if (((String)localObject2).equals("ImageView"))
      {
        paramXmlPullParser = new ImageView(this.context);
        break;
      }
      if (((String)localObject2).equals("EditText"))
      {
        paramXmlPullParser = new EditText(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label181:
      label227:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label1083;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = findAttribute(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          localObject2 = findAttribute(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            i = readDPSize((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof ImageView))
          {
            localObject1 = (ImageView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:src");
            if (localObject2 != null)
            {
              localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
              ((ImageView)localObject1).setImageDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
            }
          }
        }
      }
      catch (IOException localIOException3)
      {
        try
        {
          ((InputStream)localObject3).close();
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject1 = (TextView)paramXmlPullParser;
            findAttribute(localAttributeSet, "a:id");
            localObject2 = findAttribute(localAttributeSet, "a:text");
            localObject3 = findAttribute(localAttributeSet, "a:otpnumbertext");
            localObject4 = findAttribute(localAttributeSet, "a:infotext");
            localObject5 = findAttribute(localAttributeSet, "a:textSize");
            String str1 = findAttribute(localAttributeSet, "a:textColor");
            String str2 = findAttribute(localAttributeSet, "a:gravity");
            if (localObject2 != null) {
              ((TextView)localObject1).setText(((String)localObject2).replace("\\n", "\n"));
            }
            if (localObject3 != null)
            {
              j = 0;
              i = 1;
              if (i > 20)
              {
                ((String)localObject3).replace("\\n", "\n");
                ((TextView)localObject1).setText(j);
                this.otpAuthNumber = String.valueOf(j);
              }
            }
            else
            {
              if (localObject4 != null) {
                ((TextView)localObject1).setText(((String)localObject4).replace("\\n", "\n"));
              }
              if (localObject5 != null) {
                ((TextView)localObject1).setTextSize(readFontSize((String)localObject5));
              }
              if (str1 != null) {
                ((TextView)localObject1).setTextColor(Color.parseColor(str1));
              }
              if (str2 == null) {
                break label1134;
              }
              ((TextView)localObject1).setGravity(17);
              ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
            }
          }
          else if ((paramXmlPullParser instanceof Button))
          {
            localObject3 = findAttribute(localAttributeSet, "a:offImage");
            localObject1 = findAttribute(localAttributeSet, "a:onImage");
            localObject2 = (Button)paramXmlPullParser;
            localObject4 = getClass().getResourceAsStream(getResourcePath() + (String)localObject3 + ".png");
            localObject3 = Drawable.createFromStream((InputStream)localObject4, (String)localObject3);
          }
        }
        catch (IOException localIOException3)
        {
          try
          {
            ((InputStream)localObject4).close();
            localObject5 = getClass().getResourceAsStream(getResourcePath() + (String)localObject1 + ".png");
            localObject4 = Drawable.createFromStream((InputStream)localObject5, (String)localObject1);
          }
          catch (IOException localIOException3)
          {
            try
            {
              for (;;)
              {
                int i;
                Object localObject4;
                ((InputStream)localObject5).close();
                Object localObject5 = new StateListDrawable();
                ((StateListDrawable)localObject5).addState(new int[] { 16842919 }, (Drawable)localObject4);
                ((StateListDrawable)localObject5).addState(new int[0], (Drawable)localObject3);
                ((Button)localObject2).setBackgroundDrawable((Drawable)localObject5);
                if (((String)localObject1).equals("btn_locking_sel_e")) {
                  ((Button)localObject2).setOnClickListener(this.okOtpBtn);
                }
                if ((paramXmlPullParser instanceof EditText))
                {
                  localObject1 = (EditText)paramXmlPullParser;
                  ((EditText)localObject1).setHint(CommonString.getString(CommonString.Index.HINT_INPUT_SECURITY));
                  ((EditText)localObject1).setFocusable(true);
                  ((EditText)localObject1).setBackgroundDrawable(null);
                  ((EditText)localObject1).setInputType(2);
                  localObject2 = findAttribute(localAttributeSet, "a:maxLength");
                  if (localObject2 != null)
                  {
                    ((EditText)localObject1).setSingleLine();
                    ((EditText)localObject1).setFilters(new InputFilter[] { new InputFilter.LengthFilter(Integer.parseInt((String)localObject2)) });
                  }
                  findAttribute(localAttributeSet, "a:id");
                  ((EditText)localObject1).addTextChangedListener(new TextWatcher()
                  {
                    String inputStr;
                    
                    public void afterTextChanged(Editable paramAnonymousEditable)
                    {
                      if (this.inputStr.length() == 4)
                      {
                        if (ParserXML.this.otpAuthNumber.equals(this.inputStr)) {
                          ParserXML.this.onOtpCallback.onOtpDialogOK();
                        }
                      }
                      else {
                        return;
                      }
                      this.inputStr = "";
                      this.val$editText.setText("");
                      this.val$editText.setHint("잘못된 인증번호를 입력하셨습니다.");
                    }
                    
                    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
                    
                    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
                    {
                      this.inputStr = paramAnonymousCharSequence.toString();
                    }
                  });
                  ((EditText)localObject1).setOnClickListener(new View.OnClickListener()
                  {
                    public void onClick(View paramAnonymousView)
                    {
                      if (ParserXML.this.cursorFlag) {
                        this.val$editText.setHint("");
                      }
                      ParserXML.this.cursorFlag = false;
                    }
                  });
                }
                localObject1 = paramXmlPullParser;
                if (this.layoutStack.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                return paramXmlPullParser;
                label1083:
                if (!((String)localObject2).equals("vertical")) {
                  break label227;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label227;
                ((LinearLayout)localObject1).setGravity(5);
                continue;
                int j = (int)(Math.random() * '⌨' + 'Ϩ');
                i += 1;
                continue;
                label1134:
                ((TextView)localObject1).setGravity(19);
                continue;
                localIOException2 = localIOException2;
                continue;
                localIOException1 = localIOException1;
              }
              localIOException3 = localIOException3;
            }
            catch (IOException localIOException4)
            {
              for (;;) {}
            }
          }
        }
      }
    }
  }
  
  private View createViewYesNoPopup(XmlPullParser paramXmlPullParser, String paramString)
  {
    Object localObject2 = paramXmlPullParser.getName();
    Object localObject1 = null;
    AttributeSet localAttributeSet = Xml.asAttributeSet(paramXmlPullParser);
    if (((String)localObject2).equals("LinearLayout"))
    {
      paramXmlPullParser = new LinearLayout(this.context);
      if (paramXmlPullParser != null) {
        break label127;
      }
      paramString = null;
    }
    for (;;)
    {
      return paramString;
      if (((String)localObject2).equals("TextView"))
      {
        paramXmlPullParser = new TextView(this.context);
        break;
      }
      if (((String)localObject2).equals("Button"))
      {
        paramXmlPullParser = new Button(this.context);
        break;
      }
      Assert.fail("# UnSupported tag:" + (String)localObject2);
      paramXmlPullParser = (XmlPullParser)localObject1;
      break;
      label127:
      label173:
      Object localObject3;
      if ((paramXmlPullParser instanceof LinearLayout))
      {
        localObject1 = (LinearLayout)paramXmlPullParser;
        localObject2 = findAttribute(localAttributeSet, "a:orientation");
        if (localObject2 != null)
        {
          if (!((String)localObject2).equals("horizontal")) {
            break label729;
          }
          ((LinearLayout)localObject1).setOrientation(0);
        }
        localObject2 = findAttribute(localAttributeSet, "a:background");
        if (localObject2 != null)
        {
          localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
          ((LinearLayout)localObject1).setBackgroundDrawable(Drawable.createFromStream((InputStream)localObject3, (String)localObject2));
        }
      }
      try
      {
        ((InputStream)localObject3).close();
        if (findAttribute(localAttributeSet, "a:backgroudcolor") != null) {
          ((LinearLayout)localObject1).setBackgroundColor(-65536);
        }
        localObject2 = findAttribute(localAttributeSet, "a:gravity");
        if (localObject2 != null)
        {
          if (((String)localObject2).equals("center")) {
            ((LinearLayout)localObject1).setGravity(17);
          }
        }
        else
        {
          label297:
          localObject2 = findAttribute(localAttributeSet, "a:padding");
          if (localObject2 != null)
          {
            int i = readDPSize((String)localObject2);
            ((LinearLayout)localObject1).setPadding(i, i, i, i);
          }
          if (findAttribute(localAttributeSet, "a:focusableInTouchMode") != null) {
            ((LinearLayout)localObject1).setFocusableInTouchMode(true);
          }
          if ((paramXmlPullParser instanceof TextView))
          {
            localObject1 = (TextView)paramXmlPullParser;
            localObject2 = findAttribute(localAttributeSet, "a:id");
            localObject3 = findAttribute(localAttributeSet, "a:text");
            localObject4 = findAttribute(localAttributeSet, "a:textSize");
            String str1 = findAttribute(localAttributeSet, "a:textColor");
            String str2 = findAttribute(localAttributeSet, "a:gravity");
            if (localObject3 != null) {
              ((TextView)localObject1).setText(((String)localObject3).replace("\\n", "\n"));
            }
            if (localObject4 != null) {
              ((TextView)localObject1).setTextSize(readFontSize((String)localObject4));
            }
            if (str1 != null) {
              ((TextView)localObject1).setTextColor(Color.parseColor(str1));
            }
            if (localObject2 != null) {
              ((TextView)localObject1).setText(paramString);
            }
            if (str2 == null) {
              break label758;
            }
            ((TextView)localObject1).setGravity(17);
            label491:
            ((TextView)localObject1).setLineSpacing(0.0F, 1.15F);
          }
          if ((paramXmlPullParser instanceof Button))
          {
            localObject2 = findAttribute(localAttributeSet, "a:offImage");
            paramString = findAttribute(localAttributeSet, "a:onImage");
            localObject1 = (Button)paramXmlPullParser;
            localObject3 = getClass().getResourceAsStream(getResourcePath() + (String)localObject2 + ".png");
            localObject2 = Drawable.createFromStream((InputStream)localObject3, (String)localObject2);
          }
        }
      }
      catch (IOException localIOException2)
      {
        try
        {
          ((InputStream)localObject3).close();
          localObject4 = getClass().getResourceAsStream(getResourcePath() + paramString + ".png");
          localObject3 = Drawable.createFromStream((InputStream)localObject4, paramString);
        }
        catch (IOException localIOException2)
        {
          try
          {
            for (;;)
            {
              ((InputStream)localObject4).close();
              Object localObject4 = new StateListDrawable();
              ((StateListDrawable)localObject4).addState(new int[] { 16842919 }, (Drawable)localObject3);
              ((StateListDrawable)localObject4).addState(new int[0], (Drawable)localObject2);
              ((Button)localObject1).setBackgroundDrawable((Drawable)localObject4);
              if (paramString.equals("btn_con_sel_e")) {
                ((Button)localObject1).setOnClickListener(this.okYesNoBtn);
              }
              for (;;)
              {
                paramString = paramXmlPullParser;
                if (this.layoutStack.size() <= 0) {
                  break;
                }
                paramXmlPullParser.setLayoutParams(loadLayoutParams(localAttributeSet, (ViewGroup)this.layoutStack.peek()));
                return paramXmlPullParser;
                label729:
                if (!((String)localObject2).equals("vertical")) {
                  break label173;
                }
                ((LinearLayout)localObject1).setOrientation(1);
                break label173;
                ((LinearLayout)localObject1).setGravity(5);
                break label297;
                label758:
                ((TextView)localObject1).setGravity(19);
                break label491;
                ((Button)localObject1).setOnClickListener(this.cancelYesNoBtn);
              }
              localIOException1 = localIOException1;
            }
            localIOException2 = localIOException2;
          }
          catch (IOException localIOException3)
          {
            for (;;) {}
          }
        }
      }
    }
  }
  
  private int dipToInt(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return (int)TypedValue.applyDimension(1, paramFloat, this.context.getResources().getDisplayMetrics());
  }
  
  private String findAttribute(AttributeSet paramAttributeSet, String paramString)
  {
    String str = null;
    if (paramString.equals("a:text")) {
      str = findAttribute(paramAttributeSet, "a:text_eng");
    }
    while (str != null)
    {
      return str;
      if (paramString.equals("a:offImage")) {
        str = findAttribute(paramAttributeSet, "a:offImage_eng");
      } else if (paramString.equals("a:onImage")) {
        str = findAttribute(paramAttributeSet, "a:onImage_eng");
      } else if (paramString.equals("a:src")) {
        str = findAttribute(paramAttributeSet, "a:src_eng");
      } else if (paramString.equals("a:background")) {
        str = findAttribute(paramAttributeSet, "a:background_eng");
      } else if (paramString.equals("a:nortext")) {
        str = findAttribute(paramAttributeSet, "a:nortext_eng");
      } else if (paramString.equals("a:agreetext")) {
        str = findAttribute(paramAttributeSet, "a:agreetext_eng");
      } else if (paramString.equals("a:infotext")) {
        str = findAttribute(paramAttributeSet, "a:infotext_eng");
      } else if (paramString.equals("a:layout_width")) {
        str = findAttribute(paramAttributeSet, "a:layout_width_eng");
      } else if (paramString.equals("a:layout_height")) {
        str = findAttribute(paramAttributeSet, "a:layout_height_eng");
      } else if (paramString.equals("a:textSize")) {
        str = findAttribute(paramAttributeSet, "a:textSize_eng");
      }
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramAttributeSet.getAttributeCount())
      {
        i = paramString.indexOf(":");
        if (i == -1) {
          break;
        }
        return paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", paramString.substring(i + 1));
      }
      if (paramAttributeSet.getAttributeName(i).equals(paramString)) {
        return paramAttributeSet.getAttributeValue(i);
      }
      i += 1;
    }
    return null;
  }
  
  private String getResourcePath()
  {
    return this.RES_VERT_FILE_PATH;
  }
  
  private String getResourceXMLPath()
  {
    if (CommonF.getCarrier(this.context) == 1) {
      return String.format("%s", new Object[] { this.XML_FILE_PATH });
    }
    return String.format("%s", new Object[] { this.XML_FILE_PATH_KTLG });
  }
  
  private View inflatIMEIAuthPopup(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewIMEIPopup(paramXmlPullParser);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      if (isLayout(paramXmlPullParser.getName())) {
        this.layoutStack.pop();
      }
      localObject2 = localObject1;
      if (isScrollView(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflate(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createView(paramXmlPullParser);
      localObject2 = localObject1;
      if (localView != null)
      {
        if (localObject1 == null) {
          localObject1 = localView;
        }
        for (;;)
        {
          localObject2 = localObject1;
          if (!(localView instanceof ViewGroup)) {
            break;
          }
          this.layoutStack.push((ViewGroup)localView);
          localObject2 = localObject1;
          break;
          ((ViewGroup)this.layoutStack.peek()).addView(localView);
        }
        ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
        localObject2 = localObject1;
        continue;
        localStack.pop();
        if (isLayout(paramXmlPullParser.getName())) {
          this.layoutStack.pop();
        }
        localObject2 = localObject1;
        if (isScrollView(paramXmlPullParser.getName()))
        {
          this.layoutStack.pop();
          localObject2 = localObject1;
        }
      }
    }
  }
  
  private View inflateAuthPopup(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewAuthPopup(paramXmlPullParser);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      if (isLayout(paramXmlPullParser.getName())) {
        this.layoutStack.pop();
      }
      localObject2 = localObject1;
      if (isScrollView(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflateAutoPurchaseFormPopup(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewAutoPurchaseFormPopup(paramXmlPullParser);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      if (isLayout(paramXmlPullParser.getName())) {
        this.layoutStack.pop();
      }
      localObject2 = localObject1;
      if (isScrollView(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflateImagePopup(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewImagePopup(paramXmlPullParser, paramString);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      localObject2 = localObject1;
      if (isLayout(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflateJoinPopup(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewJoinPopup(paramXmlPullParser);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      if (isLayout(paramXmlPullParser.getName())) {
        this.layoutStack.pop();
      }
      localObject2 = localObject1;
      if (isScrollView(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflateLguSmsAuthPopup(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewLguSmsAuthPopup(paramXmlPullParser);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      if (isLayout(paramXmlPullParser.getName())) {
        this.layoutStack.pop();
      }
      localObject2 = localObject1;
      if (isScrollView(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflateOtpPopup(XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewOtpPopup(paramXmlPullParser);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      if (isLayout(paramXmlPullParser.getName())) {
        this.layoutStack.pop();
      }
      localObject2 = localObject1;
      if (isScrollView(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private View inflateYesNoPopup(XmlPullParser paramXmlPullParser, String paramString)
    throws XmlPullParserException, IOException
  {
    this.layoutStack.clear();
    this.ids.clear();
    Stack localStack = new Stack();
    int i = paramXmlPullParser.getEventType();
    Object localObject1 = null;
    if (i == 1) {
      return localObject1;
    }
    Object localObject2 = localObject1;
    switch (i)
    {
    default: 
      localObject2 = localObject1;
    }
    for (;;)
    {
      i = paramXmlPullParser.next();
      localObject1 = localObject2;
      break;
      localStack.clear();
      localObject2 = localObject1;
      continue;
      localStack.push(new StringBuffer());
      View localView = createViewYesNoPopup(paramXmlPullParser, paramString);
      if (localView == null) {
        break;
      }
      if (localObject1 == null) {
        localObject1 = localView;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (!(localView instanceof ViewGroup)) {
          break;
        }
        this.layoutStack.push((ViewGroup)localView);
        localObject2 = localObject1;
        break;
        ((ViewGroup)this.layoutStack.peek()).addView(localView);
      }
      ((StringBuffer)localStack.peek()).append(paramXmlPullParser.getText());
      localObject2 = localObject1;
      continue;
      localStack.pop();
      localObject2 = localObject1;
      if (isLayout(paramXmlPullParser.getName()))
      {
        this.layoutStack.pop();
        localObject2 = localObject1;
      }
    }
  }
  
  private boolean isLayout(String paramString)
  {
    return paramString.endsWith("Layout");
  }
  
  private boolean isScrollView(String paramString)
  {
    return paramString.endsWith("ScrollView");
  }
  
  private ViewGroup.LayoutParams loadLayoutParams(AttributeSet paramAttributeSet, ViewGroup paramViewGroup)
  {
    Object localObject = null;
    String str1 = findAttribute(paramAttributeSet, "a:layout_width");
    String str2 = findAttribute(paramAttributeSet, "a:layout_height");
    String str3 = findAttribute(paramAttributeSet, "a:xlayout_width");
    String str4 = findAttribute(paramAttributeSet, "a:xlayout_height");
    int j;
    int i;
    if ((Build.MODEL.endsWith("LT15i")) && (this.xperiacheckbox))
    {
      j = readSize(str3);
      i = readSize(str4);
      this.xperiacheckbox = false;
      if ((paramViewGroup instanceof LinearLayout)) {
        localObject = new LinearLayout.LayoutParams(j, i);
      }
      if ((paramViewGroup instanceof ScrollView)) {
        localObject = new LinearLayout.LayoutParams(j, i);
      }
      paramViewGroup = (ViewGroup)localObject;
      if ((localObject instanceof LinearLayout.LayoutParams))
      {
        paramViewGroup = (LinearLayout.LayoutParams)localObject;
        localObject = findAttribute(paramAttributeSet, "a:layout_gravity");
        if (localObject != null)
        {
          if (!((String)localObject).equals("center")) {
            break label310;
          }
          paramViewGroup.gravity = 17;
        }
      }
    }
    for (;;)
    {
      localObject = findAttribute(paramAttributeSet, "a:layout_weight");
      if (localObject != null) {
        paramViewGroup.weight = Float.parseFloat((String)localObject);
      }
      localObject = findAttribute(paramAttributeSet, "a:layout_marginTop");
      str1 = findAttribute(paramAttributeSet, "a:layout_marginLeft");
      str2 = findAttribute(paramAttributeSet, "a:layout_marginRight");
      paramAttributeSet = findAttribute(paramAttributeSet, "a:layout_marginBottom");
      if (localObject != null) {
        paramViewGroup.topMargin = readDPSize((String)localObject);
      }
      if (str1 != null) {
        paramViewGroup.leftMargin = readDPSize(str1);
      }
      if (paramAttributeSet != null) {
        paramViewGroup.bottomMargin = readSize(paramAttributeSet);
      }
      if (str2 != null) {
        paramViewGroup.rightMargin = readDPSize(str2);
      }
      return paramViewGroup;
      j = readSize(str1);
      i = readSize(str2);
      break;
      label310:
      if (((String)localObject).equals("left")) {
        paramViewGroup.gravity = 3;
      } else {
        paramViewGroup.gravity = 5;
      }
    }
  }
  
  private int lookupId(String paramString)
  {
    int j = -1;
    int k = paramString.indexOf("/");
    int i = j;
    if (k != -1)
    {
      String str = paramString.substring(k + 1);
      Integer localInteger2 = (Integer)this.ids.get(str);
      Integer localInteger1 = localInteger2;
      if (localInteger2 == null)
      {
        localInteger1 = localInteger2;
        if (paramString.startsWith("@+"))
        {
          i = this.idg;
          this.idg = (i + 1);
          localInteger1 = new Integer(i);
          this.ids.put(str, localInteger1);
        }
      }
      i = j;
      if (localInteger1 != null) {
        i = localInteger1.intValue();
      }
    }
    return i;
  }
  
  private int readDPSize(String paramString)
  {
    try
    {
      float f = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      if (paramString.endsWith("dp")) {
        return dipToInt(f);
      }
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private int readFontSize(String paramString)
  {
    try
    {
      float f = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      return (int)(float)(f / 1.5D);
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  private int readSize(String paramString)
  {
    if ("wrap_content".equals(paramString)) {}
    do
    {
      return -2;
      if ("fill_parent".equals(paramString)) {
        return -1;
      }
    } while (paramString == null);
    try
    {
      float f = Float.parseFloat(paramString.substring(0, paramString.length() - 2));
      if (paramString.endsWith("dp")) {
        return dipToInt(f);
      }
      if (paramString.endsWith("pt")) {
        return (int)((float)(f / 1.5D) * 1.0F);
      }
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException paramString) {}
    return -1;
  }
  
  public static void setOTPNumber(String paramString)
  {
    if ((paramString != null) && (mLguSmsAuthTv != null))
    {
      mOTPNumber = paramString;
      mLguSmsAuthTv.setTextColor(Color.parseColor("#FF6F00"));
      mLguSmsAuthTv.setTextSize(30.0F);
      mLguSmsAuthTv.setText(paramString);
      mLguSmsAuthTv.setPadding(0, 0, 0, 0);
      paramString = new InputFilter.LengthFilter(6);
      mLguSmsAuthTv.setFilters(new InputFilter[] { paramString });
      mLguSmsAuthOkBtn.setBackgroundDrawable(mSmsAuthDrawables);
      mLguSmsAuthOkBtn.setOnClickListener(okLguSmsAuthBtn);
    }
  }
  
  public void ReleaseResource()
  {
    if (this.layoutStack != null)
    {
      this.layoutStack.clear();
      this.layoutStack = null;
    }
    if (this.ids != null)
    {
      this.ids.clear();
      this.ids = null;
    }
    this.context = null;
    this.onResultCallback = null;
  }
  
  public View Start(int paramInt, Object paramObject)
  {
    this.orientation = paramInt;
    CommonF.LOGGER.e("ParserXML", "# purchase Start !! GUI-rotate =" + paramInt);
    this.mItemPurchaseItemInfo = ((PurchaseItem)paramObject);
    if (this.mItemPurchaseItemInfo.AutoPurchaseCheck) {
      if ((this.orientation == 0) || (this.orientation == 2)) {
        paramObject = getResourceXMLPath() + "/" + this.XML_FILE_NAME + "W_Auto.xml";
      }
    }
    for (;;)
    {
      return Start(paramObject);
      paramObject = getResourceXMLPath() + "/" + this.XML_FILE_NAME + "H_Auto.xml";
      continue;
      if ((this.orientation == 0) || (this.orientation == 2)) {
        paramObject = getResourceXMLPath() + "/" + this.XML_FILE_NAME + "_w.xml";
      } else {
        paramObject = getResourceXMLPath() + "/" + this.XML_FILE_NAME + "_h.xml";
      }
    }
  }
  
  public View Start(String paramString, Object paramObject, int paramInt)
  {
    this.mPopupClickListener = ((View.OnClickListener)paramObject);
    this.orientation = paramInt;
    CommonF.LOGGER.e("ParserXML", "# Dlg Start !! GUI-rotate =" + paramInt);
    if ((this.orientation == 0) || (this.orientation == 2)) {}
    for (paramString = paramString + "w.xml";; paramString = paramString + "h.xml") {
      return Start(paramString);
    }
  }
  
  public View Start(String paramString1, String paramString2, Object paramObject)
  {
    this.mInfoMessage = paramString2;
    this.mPopupClickListener = ((View.OnClickListener)paramObject);
    return Start(paramString1);
  }
  
  public View Start(String paramString1, String paramString2, Object paramObject, int paramInt)
  {
    this.mInfoMessage = paramString2;
    this.mPopupClickListener = ((View.OnClickListener)paramObject);
    this.orientation = paramInt;
    CommonF.LOGGER.e("ParserXML", "# Dlg Start !! GUI-rotate =" + paramInt);
    if ((this.orientation == 0) || (this.orientation == 2)) {}
    for (paramString1 = paramString1 + "w.xml";; paramString1 = paramString1 + "h.xml") {
      return Start(paramString1);
    }
  }
  
  public View Start(String paramString1, String paramString2, String paramString3, Object paramObject, int paramInt)
  {
    this.mFormName = paramString2;
    this.mInfoMessage = paramString3;
    this.mPopupClickListener = ((View.OnClickListener)paramObject);
    this.orientation = paramInt;
    CommonF.LOGGER.e("ParserXML", "# AutoPurchaseForm용 Start !! GUI-rotate =" + paramInt);
    if ((this.orientation == 0) || (this.orientation == 2)) {}
    for (paramString1 = paramString1 + "W.xml";; paramString1 = paramString1 + "H.xml") {
      return Start(paramString1);
    }
  }
  
  public View StartIMEIAuth(String paramString, Object paramObject, int paramInt)
  {
    this.mPopupClickListener = ((View.OnClickListener)paramObject);
    this.orientation = paramInt;
    CommonF.LOGGER.e("ParserXML", "# IMEIAuthDialog Start !! GUI-rotate =" + paramInt);
    if ((this.orientation == 0) || (this.orientation == 2)) {}
    for (paramString = paramString + "w.xml";; paramString = paramString + "h.xml") {
      return Start(paramString);
    }
  }
  
  public void UseTCash(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue())
    {
      int i = this.mItemPurchaseItemInfo.itemPrice - this.mItemPurchaseItemInfo.itemTCash;
      if (i > 0)
      {
        paramBoolean = new DecimalFormat("###,###,###").format(i);
        this.m_AccountPriceTextView.setText(paramBoolean + CommonString.getString(CommonString.Index.WON));
        return;
      }
      paramBoolean = new DecimalFormat("###,###,###").format(this.mItemPurchaseItemInfo.itemPrice);
      this.m_AccountPriceTextView.setText(paramBoolean + CommonString.getString(CommonString.Index.WON) + "-" + paramBoolean + "P=0" + CommonString.getString(CommonString.Index.WON));
      return;
    }
    paramBoolean = new DecimalFormat("###,###,###").format(this.mItemPurchaseItemInfo.itemPrice);
    this.m_AccountPriceTextView.setText(paramBoolean + CommonString.getString(CommonString.Index.WON));
  }
  
  public void add_payments_amount(String paramString)
  {
    CommonF.LOGGER.i("ParserXML", "add_payments_amount: " + paramString);
    int j;
    int i;
    if (paramString.equals("OCB"))
    {
      j = Integer.parseInt(IAPLib.getOCBPoint());
      i = this.mPostPay - (j - j % 10);
      if (i > 0)
      {
        this.mPostPay = i;
        this.mOCBUse = j;
        this.mOCBUse -= this.mOCBUse % 10;
        if (this.mOCBUse <= 0)
        {
          i = 0;
          this.mOCBUse = i;
          this.mOCBText.setText(j - this.mOCBUse + " P");
          this.m_AccountPriceTextView.setText(String.valueOf(this.mPostPay) + CommonString.getString(CommonString.Index.WON));
          label163:
          this.mPaymentTotal = (this.mOCBUse + this.mDotoriUse + this.mCultureUse + this.mTCashUse);
          this.m_discountTextView.setText(this.mPaymentTotal + CommonString.getString(CommonString.Index.WON));
          if ((this.mPaymentTotal != this.mTotalBalance) || (!IAPLib.getLimitExcess())) {
            break label2071;
          }
          this.mOkBtn.setBackgroundDrawable(this.mOkDrawbles);
          this.mFlag = false;
          this.mOkBtn.setOnClickListener(this.okBtn);
        }
      }
    }
    label2071:
    while ((this.mPaymentTotal >= this.mTotalBalance) || (!IAPLib.getLimitExcess()))
    {
      return;
      i = this.mOCBUse;
      break;
      this.mOCBUse = this.mPostPay;
      this.mOCBUse -= this.mOCBUse % 10;
      if (this.mOCBUse <= 0) {}
      for (i = 0;; i = this.mOCBUse)
      {
        this.mOCBUse = i;
        CommonF.LOGGER.i("ParserXML", "OCB 사용액: " + this.mOCBUse + " P");
        this.mPostPay = 0;
        this.m_AccountPriceTextView.setText(this.mOCBUse % 10 + CommonString.getString(CommonString.Index.WON));
        this.mOCBText.setText(j - this.mOCBUse + " P");
        if (this.mDotoriUse == 0)
        {
          this.mDotoriBtn.setBackgroundDrawable(this.mInactiveDotoriDrawables);
          this.mDotoriBtn.setTextColor(Color.parseColor("#8B8B8B"));
          this.mDotoriBtn.setClickable(false);
          this.mDotoriBtn.setPadding(0, 0, 0, 0);
        }
        if (this.mCultureUse == 0)
        {
          this.mCultureBtn.setBackgroundDrawable(this.mInactiveCultureDrawables);
          this.mCultureBtn.setTextColor(Color.parseColor("#8B8B8B"));
          this.mCultureBtn.setClickable(false);
          this.mCultureBtn.setPadding(0, 0, 0, 0);
        }
        if (this.mTCashUse != 0) {
          break;
        }
        this.mTcashBtn.setBackgroundDrawable(this.mInactiveTcashDrawables);
        this.mTcashBtn.setTextColor(Color.parseColor("#8B8B8B"));
        this.mTcashBtn.setClickable(false);
        this.mTcashBtn.setPadding(0, 0, 0, 0);
        break;
      }
      if (paramString.equals("DOTORI"))
      {
        i = this.mPostPay - (this.mItemPurchaseItemInfo.dotoriBalance - this.mItemPurchaseItemInfo.dotoriBalance % 100);
        if (i > 0)
        {
          this.mPostPay = i;
          this.mDotoriUse = this.mItemPurchaseItemInfo.dotoriBalance;
          this.mDotoriUse -= this.mDotoriUse % 100;
          if (this.mDotoriUse <= 0) {}
          for (i = 0;; i = this.mDotoriUse)
          {
            this.mDotoriUse = i;
            this.mDotoriText.setText((this.mItemPurchaseItemInfo.dotoriBalance + this.mDotoriUse) % 100 + CommonString.getString(CommonString.Index.DOTORI_COUNT));
            this.mDotoriText.setText("0" + CommonString.getString(CommonString.Index.DOTORI_COUNT));
            this.m_AccountPriceTextView.setText(String.valueOf(this.mPostPay) + CommonString.getString(CommonString.Index.WON));
            break;
          }
        }
        this.mDotoriUse = this.mPostPay;
        this.mDotoriUse -= this.mDotoriUse % 100;
        if (this.mDotoriUse <= 0) {}
        for (i = 0;; i = this.mDotoriUse)
        {
          this.mDotoriUse = i;
          CommonF.LOGGER.i("ParserXML", "도토리 사용액: " + this.mDotoriUse + CommonString.getString(CommonString.Index.WON));
          this.mDotoriUse = (this.mDotoriUse / 100 * 100);
          this.m_AccountPriceTextView.setText(this.mPostPay - this.mDotoriUse + CommonString.getString(CommonString.Index.WON));
          this.mPostPay -= this.mDotoriUse;
          i = (this.mItemPurchaseItemInfo.dotoriBalance - this.mDotoriUse) / 100;
          this.mDotoriText.setText(i + CommonString.getString(CommonString.Index.DOTORI_COUNT));
          if (this.mPostPay >= 10) {
            break;
          }
          if (this.mOCBUse == 0)
          {
            this.mOCBBtn.setBackgroundDrawable(this.mInactiveDrawables);
            this.mOCBBtn.setTextColor(Color.parseColor("#8B8B8B"));
            this.mOCBBtn.setClickable(false);
            this.mOCBBtn.setPadding(0, 0, 0, 0);
          }
          if (this.mCultureUse == 0)
          {
            this.mCultureBtn.setBackgroundDrawable(this.mInactiveCultureDrawables);
            this.mCultureBtn.setTextColor(Color.parseColor("#8B8B8B"));
            this.mCultureBtn.setClickable(false);
            this.mCultureBtn.setPadding(0, 0, 0, 0);
          }
          if (this.mTCashUse != 0) {
            break;
          }
          this.mTcashBtn.setBackgroundDrawable(this.mInactiveTcashDrawables);
          this.mTcashBtn.setTextColor(Color.parseColor("#8B8B8B"));
          this.mTcashBtn.setClickable(false);
          this.mTcashBtn.setPadding(0, 0, 0, 0);
          break;
        }
      }
      if (paramString.equals("CULTURE"))
      {
        j = Integer.parseInt(IAPLib.getCultureLandCashPoint());
        i = this.mPostPay - (j - j % 10);
        if (i > 0)
        {
          this.mPostPay = i;
          this.mCultureUse = j;
          this.mCultureUse -= this.mCultureUse % 10;
          if (this.mCultureUse <= 0) {}
          for (i = 0;; i = this.mCultureUse)
          {
            this.mCultureUse = i;
            this.mCultureText.setText(j - this.mCultureUse + CommonString.getString(CommonString.Index.WON));
            this.m_AccountPriceTextView.setText(String.valueOf(this.mPostPay) + CommonString.getString(CommonString.Index.WON));
            break;
          }
        }
        this.mCultureUse = this.mPostPay;
        this.mCultureUse -= this.mCultureUse % 10;
        if (this.mCultureUse <= 0) {}
        for (i = 0;; i = this.mCultureUse)
        {
          this.mCultureUse = i;
          CommonF.LOGGER.i("ParserXML", "CULTURE 사용액: " + this.mCultureUse + " P");
          this.mPostPay = 0;
          this.m_AccountPriceTextView.setText(this.mCultureUse % 10 + CommonString.getString(CommonString.Index.WON));
          this.mCultureText.setText(j - this.mCultureUse + CommonString.getString(CommonString.Index.WON));
          if (this.mOCBUse == 0)
          {
            this.mOCBBtn.setBackgroundDrawable(this.mInactiveDrawables);
            this.mOCBBtn.setTextColor(Color.parseColor("#8B8B8B"));
            this.mOCBBtn.setClickable(false);
            this.mOCBBtn.setPadding(0, 0, 0, 0);
          }
          if (this.mDotoriUse == 0)
          {
            this.mDotoriBtn.setBackgroundDrawable(this.mInactiveDotoriDrawables);
            this.mDotoriBtn.setTextColor(Color.parseColor("#8B8B8B"));
            this.mDotoriBtn.setClickable(false);
            this.mDotoriBtn.setPadding(0, 0, 0, 0);
          }
          if (this.mTCashUse != 0) {
            break;
          }
          this.mTcashBtn.setBackgroundDrawable(this.mInactiveTcashDrawables);
          this.mTcashBtn.setTextColor(Color.parseColor("#8B8B8B"));
          this.mTcashBtn.setClickable(false);
          this.mTcashBtn.setPadding(0, 0, 0, 0);
          break;
        }
      }
      if (!paramString.equals("TCASH")) {
        break label163;
      }
      i = this.mPostPay - (this.mItemPurchaseItemInfo.itemTCash - this.mItemPurchaseItemInfo.itemTCash % 10);
      if (i > 0)
      {
        this.mPostPay = i;
        this.mTCashUse = this.mItemPurchaseItemInfo.itemTCash;
        this.mTCashUse -= this.mTCashUse % 10;
        if (this.mTCashUse <= 0) {}
        for (i = 0;; i = this.mTCashUse)
        {
          this.mTCashUse = i;
          this.mTcashText.setText(this.mItemPurchaseItemInfo.itemTCash - this.mTCashUse + " P");
          this.m_AccountPriceTextView.setText(String.valueOf(this.mPostPay) + CommonString.getString(CommonString.Index.WON));
          break;
        }
      }
      this.mTCashUse = this.mPostPay;
      this.mTCashUse -= this.mTCashUse % 10;
      if (this.mTCashUse <= 0) {}
      for (i = 0;; i = this.mTCashUse)
      {
        this.mTCashUse = i;
        CommonF.LOGGER.i("ParserXML", "티캐쉬 사용액: " + this.mTCashUse + "P");
        this.mPostPay = 0;
        this.m_AccountPriceTextView.setText(this.mTCashUse % 10 + CommonString.getString(CommonString.Index.WON));
        this.mTcashText.setText(this.mItemPurchaseItemInfo.itemTCash - this.mTCashUse + " P");
        if (this.mOCBUse == 0)
        {
          this.mOCBBtn.setBackgroundDrawable(this.mInactiveDrawables);
          this.mOCBBtn.setTextColor(Color.parseColor("#8B8B8B"));
          this.mOCBBtn.setClickable(false);
          this.mOCBBtn.setPadding(0, 0, 0, 0);
        }
        if (this.mDotoriUse == 0)
        {
          this.mDotoriBtn.setBackgroundDrawable(this.mInactiveDotoriDrawables);
          this.mDotoriBtn.setTextColor(Color.parseColor("#8B8B8B"));
          this.mDotoriBtn.setClickable(false);
          this.mDotoriBtn.setPadding(0, 0, 0, 0);
        }
        if (this.mCultureUse != 0) {
          break;
        }
        this.mCultureBtn.setBackgroundDrawable(this.mInactiveCultureDrawables);
        this.mCultureBtn.setTextColor(Color.parseColor("#8B8B8B"));
        this.mCultureBtn.setClickable(false);
        this.mCultureBtn.setPadding(0, 0, 0, 0);
        break;
      }
    }
    this.mOkBtn.setBackgroundDrawable(this.mLiminExcessDrawables);
    this.mFlag = true;
    this.mOkBtn.setOnClickListener(null);
  }
  
  public void chargeAmountInit()
  {
    this.mPaymentTotal = 0;
    this.mTotalBalance = 0;
    this.mPostPay = 0;
    this.mOCBUse = 0;
    this.mDotoriUse = 0;
    this.mCultureUse = 0;
    this.mTCashUse = 0;
  }
  
  public void del_payments_amount(String paramString)
  {
    CommonF.LOGGER.i("ParserXML", "del_payments_amount: " + paramString);
    if (paramString.equals("OCB"))
    {
      this.mPostPay += this.mOCBUse;
      this.mOCBUse = 0;
      this.m_AccountPriceTextView.setText(this.mPostPay + CommonString.getString(CommonString.Index.WON));
      this.mOCBText.setText(IAPLib.getOCBPoint() + " P");
      if (this.mItemPurchaseItemInfo.dotoriBalance != 0)
      {
        this.mDotoriBtn.setBackgroundDrawable(this.mActiveDotoriDrawables);
        this.mDotoriBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mDotoriBtn.setClickable(true);
        this.mDotoriBtn.setPadding(0, 0, 0, 0);
      }
      if (!IAPLib.getCultureLandCashPoint().equals("0"))
      {
        this.mCultureBtn.setBackgroundDrawable(this.mActiveCultureDrawables);
        this.mCultureBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mCultureBtn.setClickable(true);
        this.mCultureBtn.setPadding(0, 0, 0, 0);
      }
      if (this.mItemPurchaseItemInfo.itemTCash > 0)
      {
        this.mTcashBtn.setBackgroundDrawable(this.mActiveTcashDrawables);
        this.mTcashBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mTcashBtn.setClickable(true);
        this.mTcashBtn.setPadding(0, 0, 0, 0);
      }
      this.mPaymentTotal = (this.mOCBUse + this.mDotoriUse + this.mCultureUse + this.mTCashUse);
      this.m_discountTextView.setText(this.mPaymentTotal + CommonString.getString(CommonString.Index.WON));
      if ((this.mPaymentTotal != this.mTotalBalance) || (!IAPLib.getLimitExcess())) {
        break label1123;
      }
      this.mOkBtn.setBackgroundDrawable(this.mOkDrawbles);
      this.mFlag = false;
      this.mOkBtn.setOnClickListener(this.okBtn);
    }
    label1123:
    while ((this.mPaymentTotal >= this.mTotalBalance) || (!IAPLib.getLimitExcess()))
    {
      return;
      if (paramString.equals("DOTORI"))
      {
        this.mPostPay += this.mDotoriUse;
        this.mDotoriUse = 0;
        this.m_AccountPriceTextView.setText(this.mPostPay + CommonString.getString(CommonString.Index.WON));
        int i = this.mItemPurchaseItemInfo.dotoriBalance / 100;
        this.mDotoriText.setText(i + CommonString.getString(CommonString.Index.DOTORI_COUNT));
        this.mOCBBtn.setBackgroundDrawable(this.mActiveDrawables);
        this.mOCBBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mOCBBtn.setClickable(true);
        this.mOCBBtn.setPadding(0, 0, 0, 0);
        if (!IAPLib.getCultureLandCashPoint().equals("0"))
        {
          this.mCultureBtn.setBackgroundDrawable(this.mActiveCultureDrawables);
          this.mCultureBtn.setTextColor(Color.parseColor("#CFCFCF"));
          this.mCultureBtn.setClickable(true);
          this.mCultureBtn.setPadding(0, 0, 0, 0);
        }
        if (this.mItemPurchaseItemInfo.itemTCash <= 0) {
          break;
        }
        this.mTcashBtn.setBackgroundDrawable(this.mActiveTcashDrawables);
        this.mTcashBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mTcashBtn.setClickable(true);
        this.mTcashBtn.setPadding(0, 0, 0, 0);
        break;
      }
      if (paramString.equals("CULTURE"))
      {
        this.mPostPay += this.mCultureUse;
        this.mCultureUse = 0;
        this.m_AccountPriceTextView.setText(this.mPostPay + CommonString.getString(CommonString.Index.WON));
        this.mCultureText.setText(IAPLib.getCultureLandCashPoint() + CommonString.getString(CommonString.Index.WON));
        this.mOCBBtn.setBackgroundDrawable(this.mActiveDrawables);
        this.mOCBBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mOCBBtn.setClickable(true);
        this.mOCBBtn.setPadding(0, 0, 0, 0);
        if (this.mItemPurchaseItemInfo.dotoriBalance != 0)
        {
          this.mDotoriBtn.setBackgroundDrawable(this.mActiveDotoriDrawables);
          this.mDotoriBtn.setTextColor(Color.parseColor("#CFCFCF"));
          this.mDotoriBtn.setClickable(true);
          this.mDotoriBtn.setPadding(0, 0, 0, 0);
        }
        if (this.mItemPurchaseItemInfo.itemTCash <= 0) {
          break;
        }
        this.mTcashBtn.setBackgroundDrawable(this.mActiveTcashDrawables);
        this.mTcashBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mTcashBtn.setClickable(true);
        this.mTcashBtn.setPadding(0, 0, 0, 0);
        break;
      }
      if (!paramString.equals("TCASH")) {
        break;
      }
      this.mPostPay += this.mTCashUse;
      this.mTCashUse = 0;
      this.m_AccountPriceTextView.setText(this.mPostPay + CommonString.getString(CommonString.Index.WON));
      this.mTcashText.setText(this.mItemPurchaseItemInfo.itemTCash + " P");
      this.mOCBBtn.setBackgroundDrawable(this.mActiveDrawables);
      this.mOCBBtn.setTextColor(Color.parseColor("#CFCFCF"));
      this.mOCBBtn.setClickable(true);
      this.mOCBBtn.setPadding(0, 0, 0, 0);
      if (this.mItemPurchaseItemInfo.dotoriBalance != 0)
      {
        this.mDotoriBtn.setBackgroundDrawable(this.mActiveDotoriDrawables);
        this.mDotoriBtn.setTextColor(Color.parseColor("#CFCFCF"));
        this.mDotoriBtn.setClickable(true);
        this.mDotoriBtn.setPadding(0, 0, 0, 0);
      }
      if (IAPLib.getCultureLandCashPoint().equals("0")) {
        break;
      }
      this.mCultureBtn.setBackgroundDrawable(this.mActiveCultureDrawables);
      this.mCultureBtn.setTextColor(Color.parseColor("#CFCFCF"));
      this.mCultureBtn.setClickable(true);
      this.mCultureBtn.setPadding(0, 0, 0, 0);
      break;
    }
    this.mOkBtn.setBackgroundDrawable(this.mLiminExcessDrawables);
    this.mFlag = true;
    this.mOkBtn.setOnClickListener(null);
  }
  
  private class CultureLandTextWatcher
    implements TextWatcher
  {
    private CultureLandTextWatcher() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      if ((ParserXML.this.m_CultureLandID.getText().length() <= 0) || (ParserXML.this.m_CultureLandPW.getText().length() <= 0))
      {
        ParserXML.this.mCultureOKButton.setBackgroundDrawable(ParserXML.this.dotoriInactive);
        ParserXML.this.mCultureOKButton.setClickable(false);
        return;
      }
      ParserXML.this.mCultureOKButton.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
      ParserXML.this.mCultureOKButton.setClickable(true);
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private class ForeignDevelopeTextLengthWatcher
    implements TextWatcher
  {
    private ForeignDevelopeTextLengthWatcher() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      if (ParserXML.this.onForeignInputMDNClickLisener.getCurrentSelected() == 1) {
        if (ParserXML.this.m_MDN1.getText().length() + ParserXML.this.m_MDN2.getText().length() + ParserXML.this.m_MDN3.getText().length() >= 10)
        {
          ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
          ParserXML.this.mOkBtn.setClickable(true);
        }
      }
      while ((ParserXML.this.onForeignInputMDNClickLisener.getCurrentSelected() != 2) && (ParserXML.this.onForeignInputMDNClickLisener.getCurrentSelected() != 3))
      {
        return;
        ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.dotoriInactive);
        ParserXML.this.mOkBtn.setClickable(false);
        return;
      }
      if ((ParserXML.this.m_MDN1.getText().length() + ParserXML.this.m_MDN2.getText().length() + ParserXML.this.m_MDN3.getText().length() >= 10) && (ParserXML.this.m_JuminText1.getText().length() + ParserXML.this.m_JuminText2.getText().length() == 13))
      {
        ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
        ParserXML.this.mOkBtn.setClickable(true);
        return;
      }
      ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.dotoriInactive);
      ParserXML.this.mOkBtn.setClickable(false);
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private class NextFocusTextWatcher
    implements TextWatcher
  {
    private EditText mEditText;
    private int strNum;
    
    private NextFocusTextWatcher(EditText paramEditText)
    {
      this.mEditText = paramEditText;
    }
    
    public void afterTextChanged(Editable paramEditable)
    {
      if (ParserXML.this.mOCBRegBtn != null)
      {
        ParserXML.this.mOCBRegBtn.setPadding(0, 0, 0, 0);
        if (paramEditable.length() < 4) {
          break label293;
        }
        if (this.mEditText == ParserXML.this.m_OCBRegText1)
        {
          ParserXML.this.m_OCBRegText2.requestFocus();
          ParserXML.this.strNumOCBRegText1 = paramEditable.length();
          this.strNum = (ParserXML.this.strNumOCBRegText1 + ParserXML.this.strNumOCBRegText2 + ParserXML.this.strNumOCBRegText3);
          if (this.strNum < 12) {
            break label383;
          }
          ParserXML.this.mOCBRegBtn.setBackgroundDrawable(ParserXML.this.mActiveOCBRegDrawables);
          ParserXML.this.mOCBRegBtn.setClickable(true);
          ParserXML.this.mOCBRegBtn.setTextColor(Color.parseColor("#DDDDDD"));
          ParserXML.this.mOCBRegBtn.setPadding(0, 0, 0, 0);
        }
      }
      else
      {
        label165:
        if (ParserXML.this.onForeignInputMDNResultCallback != null)
        {
          if ((this.mEditText != ParserXML.this.m_MDN1) || (paramEditable.length() < 3)) {
            break label443;
          }
          ParserXML.this.m_MDN2.requestFocus();
        }
      }
      label293:
      label383:
      label443:
      while ((this.mEditText != ParserXML.this.m_MDN2) || (paramEditable.length() < 4))
      {
        return;
        if (this.mEditText == ParserXML.this.m_OCBRegText2)
        {
          ParserXML.this.m_OCBRegText3.requestFocus();
          ParserXML.this.strNumOCBRegText2 = paramEditable.length();
          break;
        }
        if (this.mEditText != ParserXML.this.m_OCBRegText3) {
          break;
        }
        ParserXML.this.m_OCBRegText4.requestFocus();
        ParserXML.this.strNumOCBRegText3 = paramEditable.length();
        break;
        if (this.mEditText == ParserXML.this.m_OCBRegText1)
        {
          ParserXML.this.strNumOCBRegText1 = paramEditable.length();
          break;
        }
        if (this.mEditText == ParserXML.this.m_OCBRegText2)
        {
          ParserXML.this.strNumOCBRegText2 = paramEditable.length();
          break;
        }
        if (this.mEditText != ParserXML.this.m_OCBRegText3) {
          break;
        }
        ParserXML.this.strNumOCBRegText3 = paramEditable.length();
        break;
        ParserXML.this.mOCBRegBtn.setBackgroundDrawable(ParserXML.this.mInactiveOCBRegDrawables);
        ParserXML.this.mOCBRegBtn.setClickable(false);
        ParserXML.this.mOCBRegBtn.setTextColor(Color.parseColor("#8B8B8B"));
        ParserXML.this.mOCBRegBtn.setPadding(0, 0, 0, 0);
        break label165;
      }
      ParserXML.this.m_MDN3.requestFocus();
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  private class OKCashbackTextWatcher
    implements TextWatcher
  {
    private OKCashbackTextWatcher() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      if (ParserXML.this.m_OCBPWText.getText().length() < 6)
      {
        ParserXML.this.mOKCashbackOKButton.setBackgroundDrawable(ParserXML.this.dotoriInactive);
        ParserXML.this.mOKCashbackOKButton.setClickable(false);
        return;
      }
      ParserXML.this.mOKCashbackOKButton.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
      ParserXML.this.mOKCashbackOKButton.setClickable(true);
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
  
  public class OnClickForeignInputMDN
    implements View.OnClickListener
  {
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    
    public OnClickForeignInputMDN() {}
    
    public int getCurrentSelected()
    {
      if (this.mRadioButton1.isChecked())
      {
        this.mRadioButton1.addTextChangedListener(ParserXML.this.mForeignDevelopeTextLengthWatcher);
        return 1;
      }
      if (this.mRadioButton2.isChecked())
      {
        this.mRadioButton2.addTextChangedListener(ParserXML.this.mForeignDevelopeTextLengthWatcher);
        return 2;
      }
      if (this.mRadioButton3.isChecked())
      {
        this.mRadioButton3.addTextChangedListener(ParserXML.this.mForeignDevelopeTextLengthWatcher);
        return 3;
      }
      return 0;
    }
    
    public void onClick(View paramView)
    {
      if (paramView == this.mRadioButton1)
      {
        this.mRadioButton1.setChecked(true);
        this.mRadioButton2.setChecked(false);
        this.mRadioButton3.setChecked(false);
        ParserXML.mJumiLlView.setVisibility(8);
        ParserXML.this.foreignInputCarrier = 1;
        ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.dotoriInactive);
        ParserXML.this.mOkBtn.setClickable(false);
        if (ParserXML.this.m_MDN1.getText().length() + ParserXML.this.m_MDN2.getText().length() + ParserXML.this.m_MDN3.getText().length() >= 10)
        {
          ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
          ParserXML.this.mOkBtn.setClickable(true);
        }
      }
      do
      {
        do
        {
          do
          {
            return;
            if (paramView != this.mRadioButton2) {
              break;
            }
            this.mRadioButton1.setChecked(false);
            this.mRadioButton2.setChecked(true);
            this.mRadioButton3.setChecked(false);
            ParserXML.mJumiLlView.setVisibility(0);
            ParserXML.this.foreignInputCarrier = 2;
            ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.dotoriInactive);
            ParserXML.this.mOkBtn.setClickable(false);
          } while ((ParserXML.this.m_MDN1.getText().length() + ParserXML.this.m_MDN2.getText().length() + ParserXML.this.m_MDN3.getText().length() < 10) || (ParserXML.this.m_JuminText1.getText().length() + ParserXML.this.m_JuminText2.getText().length() != 13));
          ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
          ParserXML.this.mOkBtn.setClickable(true);
          return;
        } while (paramView != this.mRadioButton3);
        this.mRadioButton1.setChecked(false);
        this.mRadioButton2.setChecked(false);
        this.mRadioButton3.setChecked(true);
        ParserXML.mJumiLlView.setVisibility(0);
        ParserXML.this.foreignInputCarrier = 3;
        ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.dotoriInactive);
        ParserXML.this.mOkBtn.setClickable(false);
      } while ((ParserXML.this.m_MDN1.getText().length() + ParserXML.this.m_MDN2.getText().length() + ParserXML.this.m_MDN3.getText().length() < 10) || (ParserXML.this.m_JuminText1.getText().length() + ParserXML.this.m_JuminText2.getText().length() != 13));
      ParserXML.this.mOkBtn.setBackgroundDrawable(ParserXML.this.mOkDrawbles);
      ParserXML.this.mOkBtn.setClickable(true);
    }
    
    public void setRadioButton1(RadioButton paramRadioButton)
    {
      this.mRadioButton1 = paramRadioButton;
      this.mRadioButton1.setChecked(true);
      ParserXML.this.foreignInputCarrier = 1;
    }
    
    public void setRadioButton2(RadioButton paramRadioButton)
    {
      this.mRadioButton2 = paramRadioButton;
    }
    
    public void setRadioButton3(RadioButton paramRadioButton)
    {
      this.mRadioButton3 = paramRadioButton;
    }
  }
  
  public static abstract interface ParserAuthResultCallback
  {
    public abstract void onAuthDialogCancelButtonClick();
    
    public abstract void onAuthDialogOKButtonClick(String paramString1, String paramString2);
  }
  
  public static abstract interface ParserAutoPurchaseFormResultCallback
  {
    public abstract void onAutoPurchaseFormDialogButtonClick(boolean paramBoolean);
    
    public abstract void onAutoPurchaseFormDialogCancelButtonClick();
  }
  
  public static abstract interface ParserCultureLandCallback
  {
    public abstract void onCultureLandButtonClick(String paramString1, String paramString2);
    
    public abstract void onCultureLandCancelButtonClick();
  }
  
  public static abstract interface ParserDotoriSmsAuthCallback
  {
    public abstract void onDotoriSmsAuthCancelButtonClick();
    
    public abstract void onDotoriSmsAuthOKButtonClick();
    
    public abstract void onDotoriSmsAuthSMSReceiveButtonClick();
  }
  
  public static abstract interface ParserForeignInputMDNResultCallback
  {
    public abstract void onForeignInputMDNOKButtonClick(int paramInt, String paramString1, String paramString2);
  }
  
  public static abstract interface ParserIMEIAuthCallback
  {
    public abstract void onIMEIAuthDialogCancelButtonClick();
    
    public abstract void onIMEIAuthDialogOKButtonClick();
  }
  
  public static abstract interface ParserImageResultCallback
  {
    public abstract void onImageDialogButtonClick();
  }
  
  public static abstract interface ParserJoinResultCallback
  {
    public abstract void onJoinDialogCancelButtonClick();
    
    public abstract void onJoinDialogOKButtonClick(String paramString);
    
    public abstract void onJoinFormDialogPopupClick(int paramInt);
  }
  
  public static abstract interface ParserLguSmsAuthCallback
  {
    public abstract void onEnterTstore();
    
    public abstract void onErrorPopup();
    
    public abstract String onGetLguSmsAuthTime();
    
    public abstract void onLguSmsAuthCancer();
    
    public abstract void onLguSmsAuthNumberReq();
    
    public abstract void onLguSmsAuthOK();
    
    public abstract void onSetLguSmsAuthTime(boolean paramBoolean);
    
    public abstract void onTstoreLockError(String paramString);
  }
  
  public static abstract interface ParserOCBCallback
  {
    public abstract void onOCBPWDCancelButtonClick();
    
    public abstract void onOCBPWDOKButtonClick(String paramString);
    
    public abstract void onOCBRegistrationButtonClick(String paramString);
    
    public abstract void onOCBRegistrationCancelClick();
  }
  
  public static abstract interface ParserOtpCallback
  {
    public abstract void onOtpDialogCancelButtonClick();
    
    public abstract void onOtpDialogOK();
    
    public abstract void onOtpErrorPopup();
    
    public abstract void onOtpTstoreButtonClick();
  }
  
  public static abstract interface ParserPopupDlgResultCallback
  {
    public abstract void onDlgButtonClick();
    
    public abstract void onErrorDlgBtnClick();
  }
  
  public static abstract interface ParserResultCallback
  {
    public abstract void onAutoPurchaseInfoClick(String paramString1, String paramString2);
    
    public abstract void onEnterTstore();
    
    public abstract void onPurchaseAutoButtonClick();
    
    public abstract void onPurchaseAutoCancelButtonClick(String paramString);
    
    public abstract void onPurchaseButtonClick();
    
    public abstract void onPurchaseCancelButtonClick();
    
    public abstract void onShowCultureLandDialog();
    
    public abstract void onShowDotoriQueryBtnClick(boolean paramBoolean);
    
    public abstract void onShowOCBPWDDialog();
    
    public abstract void onShowOCBRegPopup(String paramString);
    
    public abstract void onTstoreLockError(String paramString);
    
    public abstract void onUseTCashCheckChanged(boolean paramBoolean);
  }
  
  public static abstract interface ParserYesNoResultCallback
  {
    public abstract void onYesNoDialogCancelButtonClick();
    
    public abstract void onYesNoDialogOKButtonClick();
  }
  
  public class filterAlphaNum
    implements InputFilter
  {
    public filterAlphaNum() {}
    
    public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      paramSpanned = Pattern.compile("^[a-zA-Z0-9]*$");
      if ((paramCharSequence != null) && (!paramSpanned.matcher(paramCharSequence).matches())) {
        return "";
      }
      return null;
    }
  }
}
