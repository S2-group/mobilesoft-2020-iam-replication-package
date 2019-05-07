package com.g123.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.preference.PreferenceManager;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Profile;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.amazon.device.ads.AdTargetingOptions;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.Session.Builder;
import com.facebook.Session.OpenRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.widget.FacebookDialog.Callback;
import com.facebook.widget.FacebookDialog.PendingCall;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.FeedDialogBuilder;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.flurry.android.FlurryAgent;
import com.g123.activity.helper.CommonHelper;
import com.g123.activity.helper.GetCardDetailsDataClass;
import com.g123.activity.helper.GetCardSendDetailsDataClass;
import com.g123.activity.helper.HandleClickEventsForMenu;
import com.g123.activity.helper.HandlingClickEvents;
import com.g123.activity.helper.ImageLoader;
import com.g123.activity.helper.JsonParser;
import com.g123.activity.helper.NetworkCalls;
import com.g123.activity.helper.ShareHelper;
import com.g123.activity.helper.SideMenuHelper;
import com.g123.adapter.SideBar;
import com.g123.adapter.SideBarAdapter;
import com.g123.calendar.CalendarView;
import com.g123.util.Alert;
import com.g123.util.Constant.CountryListUtils;
import com.g123.util.Constant.DateUtils;
import com.g123.util.Constant.ReceiverDetails;
import com.g123.util.Constant.SenderDetails;
import com.g123.util.Constant.ThanksUtils;
import com.g123.util.CountryList;
import com.g123.util.CustomAlertDialog;
import com.g123.util.DateValidation;
import com.g123.util.EmailValidation;
import com.g123.util.FacebookHelper;
import com.g123.util.HttpClientFactory;
import com.g123.util.ImgUtils;
import com.g123.webservice.ConnectionForPostData;
import com.google.analytics.tracking.android.Tracker;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class ViewAndSendActivity
  extends BaseActivity
  implements View.OnClickListener, View.OnKeyListener, AbsListView.OnScrollListener
{
  public static final String APP_ID = "6268317308";
  private static String CARD_DESC;
  private static String CARD_DOWNLOAD_URL;
  private static String CARD_ICON;
  private static String CARD_ID = "";
  private static int CARD_STACK_POSITION = 0;
  public static int CONTACT_TAG = 0;
  private static String Category_Name;
  private static String IMAGE_URL;
  private static final String[] PERMISSIONS = { "public_profile", "email", "user_friends", "user_birthday" };
  static final int PICK_CONTACT = 1;
  private static final int REQ_START_SHARE = 22;
  static final int SMS_REQ_CODE = 1234;
  public static ArrayList<String[]> receiverDetailsArrList;
  public static ArrayList<String[]> receiverDetailsArrList_thanks;
  private static String response;
  public static Boolean response_send_error;
  private ImageButton AddReceiver_ViewNSendScreen;
  private ImageButton Calendar_ViewNSendScreen;
  private ImageView CardImage_ViewNSendScreen;
  private TextView CardTitle_ViewNSendScreen;
  private LinearLayout CountryListFrame;
  String DEBUG_TAG = "--contacts--";
  private EditText DeliveryDate_ViewNSendScreen;
  private EditText DeliveryDate_ViewNSendScreen_show_value;
  private String IMAGE_FOR_RESEND_URL = "";
  private EditText Message_ViewNSendScreen;
  private Button NotifyMe_ViewNSendScreen;
  private Button Preview_ViewNSendScreen;
  private LinearLayout ReceiverMainLinear;
  private ImageButton RemoveReceiver_ViewNSendScreen;
  private String SENDER_EMAIL = "";
  private String SENDER_NAME = "";
  private ScrollView ScrollView_ViewNSendScreen;
  private Button Send_ViewNSendScreen;
  private TextView SenderEcardHeading_ViewNSendScreen;
  private EditText SenderEmail_ViewNSendScreen;
  private ImageButton SenderEmail_btn_cancel;
  private ImageButton SenderEmail_profile;
  private EditText SenderName_ViewNSendScreen;
  private ImageButton SenderName_btn_cancel;
  private ImageButton SenderName_profile;
  private String TITLE = "";
  private TextView TextView01;
  private LinearLayout TopLinear_ViewAndSendScreen;
  private String VIDEO_URL = "";
  private Button VideoPlay_ViewNSendScreen;
  private LinearLayout ad_viewcontainer;
  ArrayList<HashMap<String, Object>> all_contacts_fb = new ArrayList();
  private View app;
  int bday_anniv_remain_day = 0;
  private Button btemail;
  private Button btfacebook;
  private Button btfbmessangerpost;
  private Button btgplus;
  private Button btmore;
  private Button btmsn;
  private Button btmyfriendstimeline;
  private Button btmytimeline;
  private Button btn_cancel;
  private Button btn_search;
  private Button btsms;
  private Button btwhatsapp;
  String cat_first_char = "";
  private String country = "";
  private Dialog countryDialog;
  private Dialog customDialogProgress;
  private EditText edit_search;
  private SharedPreferences.Editor editor;
  private FrameLayout flcalender;
  private ArrayList<GetCardDetailsDataClass> getCardDetailsArrList;
  ArrayList<GetCardSendDetailsDataClass> getCardSendDetailsArrList;
  private HandleClickEventsForMenu handleClickEventsForMenu;
  private HandlingClickEvents handlingClickEvents;
  TextView header_txt;
  private String imageURL;
  private Intent intent;
  private boolean iswhatsAppexist;
  RelativeLayout l_searchbox;
  LinearLayout layout_to_move;
  private LinearLayout ll_NotifyMe_ViewNSendScreen;
  private LinearLayout llecards;
  private LinearLayout llemail;
  private LinearLayout llfacebook;
  private AsyncFacebookRunner mAsyncRunner;
  private int mDay = 0;
  private Facebook mFacebook;
  private int mMonth = 0;
  private int mYear = 0;
  private View menu;
  private View menu_right;
  private LinearLayout message_block;
  private ProgressBar pbvideo;
  private SharedPreferences pref;
  private boolean receiverEmailHasFocus = false;
  private boolean receiverNameHasFocus = false;
  private View receiverView;
  private ReceiverList receiverlist;
  private RelativeLayout rlViewandSend;
  private boolean senderEmailHasFocus = false;
  private boolean senderNameHasFocus = false;
  String server_date = "";
  private TextView spinner_SetCountry;
  private String startDateForCalender = "";
  private Session.StatusCallback statusCallback = new SessionStatusCallback(null);
  private View tmpView;
  private TextView tvposton;
  private TextView txt_holder;
  private String whatsAppText;
  
  static
  {
    CARD_DESC = "";
    CARD_ICON = "";
    CARD_DOWNLOAD_URL = "";
    response = "";
    IMAGE_URL = "";
    Category_Name = "";
    CARD_STACK_POSITION = 0;
    CONTACT_TAG = 0;
    receiverDetailsArrList = new ArrayList();
    receiverDetailsArrList_thanks = new ArrayList();
    response_send_error = Boolean.valueOf(false);
  }
  
  public ViewAndSendActivity() {}
  
  private void bindControls()
  {
    this.ad_viewcontainer = ((LinearLayout)this.rlViewandSend.findViewById(2131099690));
    this.llecards = ((LinearLayout)this.rlViewandSend.findViewById(2131099808));
    this.TopLinear_ViewAndSendScreen = ((LinearLayout)this.rlViewandSend.findViewById(2131099813));
    this.llfacebook = ((LinearLayout)this.rlViewandSend.findViewById(2131099822));
    this.llemail = ((LinearLayout)this.rlViewandSend.findViewById(2131099827));
    this.ReceiverMainLinear = ((LinearLayout)this.rlViewandSend.findViewById(2131099834));
    this.CountryListFrame = ((LinearLayout)this.rlViewandSend.findViewById(2131099840));
    this.ll_NotifyMe_ViewNSendScreen = ((LinearLayout)this.rlViewandSend.findViewById(2131099842));
    this.message_block = ((LinearLayout)this.rlViewandSend.findViewById(2131099845));
    this.menu = this.rlViewandSend.findViewById(2131099693);
    this.menu_right = this.rlViewandSend.findViewById(2131099694);
    this.app = this.rlViewandSend.findViewById(2131099695);
    this.layout_to_move = ((LinearLayout)this.rlViewandSend.findViewById(2131099695));
    this.ScrollView_ViewNSendScreen = ((ScrollView)this.rlViewandSend.findViewById(2131099806));
    this.btn_search = ((Button)this.rlViewandSend.findViewById(2131099701));
    this.btn_cancel = ((Button)this.rlViewandSend.findViewById(2131099702));
    this.VideoPlay_ViewNSendScreen = ((Button)this.rlViewandSend.findViewById(2131099812));
    this.pbvideo = ((ProgressBar)this.rlViewandSend.findViewById(2131099811));
    this.btemail = ((Button)this.rlViewandSend.findViewById(2131099815));
    this.btsms = ((Button)this.rlViewandSend.findViewById(2131099819));
    this.btfacebook = ((Button)this.rlViewandSend.findViewById(2131099816));
    this.btgplus = ((Button)this.rlViewandSend.findViewById(2131099820));
    this.btwhatsapp = ((Button)this.rlViewandSend.findViewById(2131099817));
    this.btmore = ((Button)this.rlViewandSend.findViewById(2131099821));
    this.btmsn = ((Button)this.rlViewandSend.findViewById(2131099818));
    this.btmytimeline = ((Button)this.rlViewandSend.findViewById(2131099826));
    this.btfbmessangerpost = ((Button)this.rlViewandSend.findViewById(2131099824));
    this.btmyfriendstimeline = ((Button)this.rlViewandSend.findViewById(2131099825));
    this.NotifyMe_ViewNSendScreen = ((Button)this.rlViewandSend.findViewById(2131099843));
    this.Preview_ViewNSendScreen = ((Button)this.rlViewandSend.findViewById(2131099847));
    this.Send_ViewNSendScreen = ((Button)this.rlViewandSend.findViewById(2131099848));
    this.txt_holder = ((TextView)this.rlViewandSend.findViewById(2131099703));
    this.SenderEcardHeading_ViewNSendScreen = ((TextView)this.rlViewandSend.findViewById(2131099809));
    this.CardTitle_ViewNSendScreen = ((TextView)this.rlViewandSend.findViewById(2131099814));
    this.tvposton = ((TextView)this.rlViewandSend.findViewById(2131099823));
    this.spinner_SetCountry = ((TextView)this.rlViewandSend.findViewById(2131099841));
    this.TextView01 = ((TextView)this.rlViewandSend.findViewById(2131099844));
    this.edit_search = ((EditText)this.rlViewandSend.findViewById(2131099704));
    this.SenderName_ViewNSendScreen = ((EditText)this.rlViewandSend.findViewById(2131099828));
    this.SenderEmail_ViewNSendScreen = ((EditText)this.rlViewandSend.findViewById(2131099831));
    this.Message_ViewNSendScreen = ((EditText)this.rlViewandSend.findViewById(2131099846));
    this.DeliveryDate_ViewNSendScreen = ((EditText)this.rlViewandSend.findViewById(2131099837));
    this.DeliveryDate_ViewNSendScreen_show_value = ((EditText)this.rlViewandSend.findViewById(2131099838));
    this.CardImage_ViewNSendScreen = ((ImageView)this.rlViewandSend.findViewById(2131099810));
    this.SenderName_btn_cancel = ((ImageButton)this.rlViewandSend.findViewById(2131099829));
    this.SenderEmail_btn_cancel = ((ImageButton)this.rlViewandSend.findViewById(2131099832));
    this.SenderName_profile = ((ImageButton)this.rlViewandSend.findViewById(2131099830));
    this.SenderEmail_profile = ((ImageButton)this.rlViewandSend.findViewById(2131099833));
    this.RemoveReceiver_ViewNSendScreen = ((ImageButton)this.rlViewandSend.findViewById(2131099835));
    this.AddReceiver_ViewNSendScreen = ((ImageButton)this.rlViewandSend.findViewById(2131099836));
    this.Calendar_ViewNSendScreen = ((ImageButton)this.rlViewandSend.findViewById(2131099839));
    this.flcalender = ((FrameLayout)this.rlViewandSend.findViewById(2131099682));
    this.l_searchbox = ((RelativeLayout)this.rlViewandSend.findViewById(2131099700));
  }
  
  private View inflateReceiverDetails(int paramInt)
  {
    Object localObject1 = (LayoutInflater)getSystemService("layout_inflater");
    new View(this);
    localObject1 = ((LayoutInflater)localObject1).inflate(getResources().getLayout(2130903120), null);
    Object localObject2 = (LinearLayout)((View)localObject1).findViewById(2131100063);
    final EditText localEditText1 = (EditText)((View)localObject1).findViewById(2131100064);
    final EditText localEditText2 = (EditText)((View)localObject1).findViewById(2131100067);
    final ImageButton localImageButton1 = (ImageButton)((View)localObject1).findViewById(2131100068);
    final ImageButton localImageButton2 = (ImageButton)((View)localObject1).findViewById(2131100065);
    ((LinearLayout)localObject2).setTag(paramInt + ",Linear");
    localEditText1.setTag(paramInt + ",RName");
    localEditText1.setHint(Html.fromHtml("<small>Enter Recipient's Name</small>"));
    localEditText2.setTag(paramInt + ",REmail");
    localEditText2.setHint(Html.fromHtml("<small>Enter Recipient's Email</small>"));
    localImageButton1.setVisibility(4);
    localImageButton2.setVisibility(4);
    localObject2 = (ImageButton)((View)localObject1).findViewById(2131100066);
    ((ImageButton)localObject2).setTag(paramInt + ",Contacts");
    ((ImageButton)localObject2).setOnClickListener(this);
    this.ReceiverMainLinear.addView((View)localObject1);
    localImageButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localEditText1.setText("");
      }
    });
    localEditText1.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        ViewAndSendActivity.this.receiverNameHasFocus = paramAnonymousBoolean;
        if (ViewAndSendActivity.this.receiverNameHasFocus)
        {
          localImageButton2.setVisibility(0);
          return;
        }
        localImageButton2.setVisibility(4);
      }
    });
    localEditText1.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((ViewAndSendActivity.this.receiverNameHasFocus) && (localEditText1.getText().toString().length() > 0))
        {
          localImageButton2.setVisibility(0);
          return;
        }
        localImageButton2.setVisibility(4);
      }
    });
    localImageButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localEditText2.setText("");
      }
    });
    localEditText2.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        ViewAndSendActivity.this.receiverEmailHasFocus = paramAnonymousBoolean;
        if ((ViewAndSendActivity.this.receiverEmailHasFocus) && (localEditText2.getText().toString().length() > 0))
        {
          localImageButton1.setVisibility(0);
          return;
        }
        localImageButton1.setVisibility(4);
      }
    });
    localEditText2.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((ViewAndSendActivity.this.receiverEmailHasFocus) && (localEditText2.getText().toString().length() > 0)) {
          localImageButton1.setVisibility(0);
        }
        while (EmailValidation.isEmailValid(localEditText2.getText().toString()))
        {
          ViewAndSendActivity.this.AddReceiver_ViewNSendScreen.setBackgroundResource(2130837812);
          return;
          localImageButton1.setVisibility(4);
        }
        ViewAndSendActivity.this.AddReceiver_ViewNSendScreen.setBackgroundResource(2130837811);
      }
    });
    return localObject1;
  }
  
  private void initializedata()
  {
    Constant.ReceiverDetails.RECEIVER_COUNT = 1;
    if (CommonHelper.bd != null) {
      this.layout_to_move.setBackgroundDrawable(CommonHelper.bd);
    }
    this.handlingClickEvents = new HandlingClickEvents(this);
    this.edit_search.setOnKeyListener(this);
    this.edit_search.setHint(Html.fromHtml("<small>Search ecards</small>"));
    this.handleClickEventsForMenu = new HandleClickEventsForMenu(this, this.app, this.menu, this.slidingMenu);
    this.mFacebook = new Facebook("6268317308");
    this.mAsyncRunner = new AsyncFacebookRunner(this.mFacebook);
    this.btn_search.setOnClickListener(this);
    this.btn_cancel.setOnClickListener(this);
    this.CountryListFrame.setVisibility(8);
    this.CountryListFrame.setOnClickListener(this);
    this.header_txt = ((TextView)findViewById(2131100048));
    this.header_txt.setText("Send this ecard");
    new SideMenuHelper(this.handleClickEventsForMenu, this);
    this.btemail.setOnClickListener(this);
    this.btsms.setOnClickListener(this);
    this.btfacebook.setOnClickListener(this);
    this.btgplus.setOnClickListener(this);
    this.btwhatsapp.setOnClickListener(this);
    this.btmore.setOnClickListener(this);
    this.btmsn.setOnClickListener(this);
    this.btmytimeline.setOnClickListener(this);
    this.btmyfriendstimeline.setOnClickListener(this);
    this.btfbmessangerpost.setOnClickListener(this);
    this.edit_search.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Card Send  Enter search text", Long.valueOf(0L));
        if (ViewAndSendActivity.this.edit_search.getText().toString().length() > 0)
        {
          ViewAndSendActivity.this.btn_cancel.setVisibility(0);
          return;
        }
        ViewAndSendActivity.this.btn_cancel.setVisibility(4);
      }
    });
    this.edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 3)
        {
          ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Cardsend  Tap Go", Long.valueOf(0L));
          int i;
          if (ViewAndSendActivity.this.edit_search.getText().toString().trim().length() > 0)
          {
            paramAnonymousTextView = new Intent(ViewAndSendActivity.this, CategoryActivityNew.class);
            paramAnonymousTextView.putExtra("CategoryId", ViewAndSendActivity.this.edit_search.getText().toString());
            paramAnonymousTextView.putExtra("URL", "http://www.123greetings.com/mobile_api/search_subcats?devicetype=android");
            paramAnonymousTextView.putExtra("SEARCH", true);
            paramAnonymousTextView.putExtra("MENU_OPTION", false);
            ViewAndSendActivity.this.edit_search.setText("");
            paramAnonymousKeyEvent = CommonHelper.getInstance();
            i = paramAnonymousKeyEvent.getActivityStack().size();
            paramAnonymousInt = 0;
          }
          for (;;)
          {
            if (paramAnonymousInt >= i)
            {
              ViewAndSendActivity.this.startActivity(paramAnonymousTextView);
              return true;
            }
            Activity localActivity = (Activity)paramAnonymousKeyEvent.getActivityStack().get(paramAnonymousKeyEvent.getActivityStack().size() - 1);
            if (!(localActivity instanceof HomeActivity))
            {
              paramAnonymousKeyEvent.getActivityStack().remove(paramAnonymousKeyEvent.getActivityStack().size() - 1);
              localActivity.finish();
            }
            paramAnonymousInt += 1;
          }
        }
        return false;
      }
    });
    if (!Constant.ThanksUtils.SENDTOANOTHER)
    {
      CommonHelper.card_desc_fb = getIntent().getStringExtra("description");
      CARD_ID = getIntent().getStringExtra("cardId");
      CARD_DOWNLOAD_URL = getIntent().getStringExtra("cardUrl");
      CARD_STACK_POSITION = getIntent().getIntExtra("position", 0);
      CARD_DESC = getIntent().getStringExtra("description");
      CARD_ICON = getIntent().getStringExtra("icon");
    }
    for (;;)
    {
      Category_Name = getIntent().getStringExtra("Cat_name");
      CommonHelper.catname = Category_Name;
      int i;
      label473:
      Object localObject1;
      Object localObject4;
      Object localObject2;
      Object localObject3;
      if (Category_Name.length() > 0)
      {
        i = Category_Name.charAt(0);
        if ((i == 97) || (i == 65) || (i == 101) || (i == 69) || (i == 105) || (i == 73) || (i == 111) || (i == 79) || (i == 117) || (i == 85))
        {
          this.cat_first_char = "an";
          if (getIntent().getExtras().containsKey("bdayvalues"))
          {
            ((LinearLayout)findViewById(2131099696)).setVisibility(0);
            localObject1 = (ImageView)findViewById(2131099967);
            localObject4 = (TextView)findViewById(2131099976);
            localObject2 = (TextView)findViewById(2131099977);
            localObject3 = (LinearLayout)findViewById(2131099974);
            ((TextView)findViewById(2131099978)).setVisibility(8);
            localObject3 = (HashMap)getIntent().getSerializableExtra("bdayvalues");
            ((TextView)localObject4).setText(((HashMap)localObject3).get("name").toString());
            localObject4 = ((HashMap)localObject3).get("age_turning").toString();
          }
        }
      }
      try
      {
        ((TextView)localObject2).setText(localObject4 + " on " + DateValidation.bday_show_format_without_day(((HashMap)localObject3).get("birthday").toString()));
        String str = ((HashMap)localObject3).get("remain_d").toString();
        if (str.equals("Today"))
        {
          ((TextView)localObject2).setText(localObject4 + " " + "Today");
          if (!((HashMap)localObject3).get("source").toString().equals("fb")) {
            break label1231;
          }
          localObject2 = new ImageLoader(getApplicationContext());
          if (((HashMap)localObject3).get("picture_url").toString().equals("")) {
            break label1221;
          }
          ((ImageLoader)localObject2).DisplayImage(((HashMap)localObject3).get("picture_url").toString(), (ImageView)localObject1);
          setViews();
          this.receiverlist = new ReceiverList(this, this.ReceiverMainLinear, this.AddReceiver_ViewNSendScreen, this.RemoveReceiver_ViewNSendScreen, this.ScrollView_ViewNSendScreen);
          this.receiverView = this.receiverlist.inflateReceiverDetails(Constant.ReceiverDetails.RECEIVER_COUNT);
          if (!Category_Name.equals("Birthday")) {
            break label1289;
          }
          this.Message_ViewNSendScreen.setText(CommonHelper.birthday_default_message);
          if (getIntent().getExtras().containsKey("bdayvalues"))
          {
            localObject1 = (HashMap)getIntent().getSerializableExtra("bdayvalues");
            setNameAndEmail(0, ((HashMap)localObject1).get("name").toString(), ((HashMap)localObject1).get("email").toString());
            this.AddReceiver_ViewNSendScreen.setVisibility(4);
            this.btmytimeline.setVisibility(8);
            if (!((HashMap)localObject1).get("source").toString().equals("fb")) {
              break label1314;
            }
            this.btfacebook.performClick();
            this.btmyfriendstimeline.setText(((HashMap)localObject1).get("name").toString() + "'s Timeline");
            if (!((HashMap)localObject1).get("type").toString().equals("birth_")) {
              break label1325;
            }
            this.Message_ViewNSendScreen.setText(CommonHelper.birthday_default_message);
          }
          return;
          if (!Constant.ThanksUtils.SENDTOANOTHER) {
            continue;
          }
          CARD_DESC = CommonHelper.card_desc_fb;
          IMAGE_URL = CommonHelper.card_image_url_fb;
          this.SENDER_NAME = getIntent().getStringExtra("senderName_VnS");
          this.SENDER_EMAIL = getIntent().getStringExtra("senderEmail_VnS");
          this.IMAGE_FOR_RESEND_URL = getIntent().getStringExtra("cardImgUrl_VnS");
          this.TITLE = getIntent().getStringExtra("title");
          continue;
          this.cat_first_char = "a";
          break label473;
          this.cat_first_char = "an";
        }
      }
      catch (Exception localException)
      {
        label1221:
        label1231:
        label1289:
        label1314:
        label1325:
        do
        {
          for (;;)
          {
            localException.printStackTrace();
            continue;
            if (!localException.equals(""))
            {
              i = Integer.parseInt(localException.split(" ")[1]);
              this.bday_anniv_remain_day = i;
              if (i == 365)
              {
                ((TextView)localObject2).setText(localObject4 + " " + "Yesterday");
              }
              else if (i == 1)
              {
                ((TextView)localObject2).setText(localObject4 + " " + "Tomorrow");
                continue;
                ((ImageView)localObject1).setBackgroundResource(2130837691);
                continue;
                if ((!((HashMap)localObject3).get("source").toString().equals("")) && (((HashMap)localObject3).get("picture_bmap") != null))
                {
                  ((ImageView)localObject1).setImageBitmap((Bitmap)((HashMap)localObject3).get("picture_bmap"));
                }
                else
                {
                  ((ImageView)localObject1).setBackgroundResource(2130837691);
                  continue;
                  if (Category_Name.equals("Anniversary"))
                  {
                    this.Message_ViewNSendScreen.setText(CommonHelper.anniversary_default_message);
                    continue;
                    this.btemail.performClick();
                  }
                }
              }
            }
          }
        } while (!((HashMap)localObject1).get("type").toString().equals("anniv_"));
        this.Message_ViewNSendScreen.setText(CommonHelper.anniversary_default_message);
      }
    }
  }
  
  private void popUpforlist()
  {
    final CustomAlertDialog localCustomAlertDialog = new CustomAlertDialog(this);
    localCustomAlertDialog.show();
    SideBarAdapter localSideBarAdapter = new SideBarAdapter(this, Constant.CountryListUtils.COUNTRY_NAMES);
    ListView localListView = (ListView)localCustomAlertDialog.findViewById(2131100176);
    ((SideBar)localCustomAlertDialog.findViewById(2131100045)).setListView(localListView);
    localListView.setOnScrollListener(this);
    localListView.setAdapter(localSideBarAdapter);
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        localCustomAlertDialog.dismiss();
        if (Constant.CountryListUtils.COUNTRY_NAMES.get(paramAnonymousInt) != null)
        {
          ViewAndSendActivity.this.spinner_SetCountry.setText((CharSequence)Constant.CountryListUtils.COUNTRY_NAMES.get(paramAnonymousInt));
          Constant.CountryListUtils.COUNTRY_ID_SELECTED = (String)Constant.CountryListUtils.COUNTRY_ID.get(paramAnonymousInt);
        }
      }
    });
  }
  
  private void setNameAndEmail(int paramInt, String paramString1, String paramString2)
  {
    if (paramString1.matches("[a-zA-Z.? ]*"))
    {
      this.receiverlist.setReceiverNameEmail(paramInt, paramString1, paramString2);
      return;
    }
    this.receiverlist.setReceiverNameEmail(paramInt, paramString1, paramString2);
  }
  
  private void setViews()
  {
    this.pref = getApplicationContext().getSharedPreferences("com.g123", 0);
    this.editor = this.pref.edit();
    String str2 = this.pref.getString("sender_name", "");
    Object localObject3 = this.pref.getString("sender_email", "");
    Object localObject2 = localObject3;
    Object localObject1 = localObject3;
    String str1 = str2;
    for (;;)
    {
      int j;
      int i;
      try
      {
        if (((String)localObject3).equals(""))
        {
          localObject1 = localObject3;
          str1 = str2;
          localObject4 = Patterns.EMAIL_ADDRESS;
          localObject1 = localObject3;
          str1 = str2;
          arrayOfAccount = AccountManager.get(this).getAccountsByType("com.google");
          localObject1 = localObject3;
          str1 = str2;
          j = arrayOfAccount.length;
          i = 0;
          localObject2 = localObject3;
          break label986;
        }
        localObject4 = localObject2;
        localObject3 = str2;
        localObject1 = localObject2;
        str1 = str2;
        if (str2.equals(""))
        {
          localObject1 = localObject2;
          str1 = str2;
          localObject3 = getApplication().getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
          localObject1 = localObject2;
          str1 = str2;
          ((Cursor)localObject3).moveToFirst();
          localObject1 = localObject2;
          str1 = str2;
          str2 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("display_name"));
          localObject1 = localObject2;
          str1 = str2;
          ((Cursor)localObject3).close();
          localObject4 = localObject2;
          localObject3 = str2;
          localObject1 = localObject2;
          str1 = str2;
          if (str2.matches(".*\\d+.*"))
          {
            localObject3 = "";
            localObject4 = localObject2;
          }
        }
      }
      catch (Exception localException)
      {
        Account[] arrayOfAccount;
        Object localObject5;
        localException.printStackTrace();
        Object localObject4 = localObject1;
        localObject3 = str1;
        continue;
        ((GetCardDetailsAsync)localObject1).execute(new String[0]);
        continue;
        if (!Constant.ThanksUtils.WISH_NOW) {
          continue;
        }
        CARD_ID = Constant.ThanksUtils.CARD_ID;
        localObject1 = new GetCardDetailsAsync(null);
        if (Build.VERSION.SDK_INT < 11) {
          continue;
        }
        ((GetCardDetailsAsync)localObject1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
        continue;
        ((GetCardDetailsAsync)localObject1).execute(new String[0]);
        continue;
        CARD_ID = Constant.ThanksUtils.CARD_ID;
        this.ScrollView_ViewNSendScreen.setVisibility(0);
        this.DeliveryDate_ViewNSendScreen.setText(CommonHelper.send_someoneelse_cur_date);
        this.server_date = CommonHelper.send_someoneelse_cur_date;
        this.DeliveryDate_ViewNSendScreen_show_value.setText("Today");
        this.CardImage_ViewNSendScreen.setImageResource(2130837602);
        new DownloadImage(null).execute(new String[] { this.IMAGE_FOR_RESEND_URL });
        continue;
      }
      this.TopLinear_ViewAndSendScreen.setOnClickListener(this);
      this.AddReceiver_ViewNSendScreen.setOnClickListener(this);
      this.RemoveReceiver_ViewNSendScreen.setOnClickListener(this);
      this.Preview_ViewNSendScreen.setOnClickListener(this);
      this.Send_ViewNSendScreen.setOnClickListener(this);
      this.VideoPlay_ViewNSendScreen.setOnClickListener(this);
      this.Calendar_ViewNSendScreen.setOnClickListener(this);
      this.flcalender.setOnClickListener(this);
      this.ll_NotifyMe_ViewNSendScreen.setOnClickListener(this);
      this.DeliveryDate_ViewNSendScreen.setOnClickListener(this);
      this.DeliveryDate_ViewNSendScreen_show_value.setOnClickListener(this);
      this.SenderName_btn_cancel.setOnClickListener(this);
      this.SenderEmail_btn_cancel.setOnClickListener(this);
      this.SenderName_profile.setOnClickListener(this);
      this.SenderEmail_profile.setOnClickListener(this);
      this.AddReceiver_ViewNSendScreen.setBackgroundResource(2130837811);
      this.NotifyMe_ViewNSendScreen.setBackgroundResource(2130837613);
      this.NotifyMe_ViewNSendScreen.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ViewAndSendActivity.this.ll_NotifyMe_ViewNSendScreen.performClick();
        }
      });
      this.AddReceiver_ViewNSendScreen.setClickable(false);
      this.Preview_ViewNSendScreen.setClickable(true);
      this.SenderEcardHeading_ViewNSendScreen.setTypeface(Typeface.createFromAsset(getAssets(), "Candara.ttf"));
      this.ScrollView_ViewNSendScreen.setVisibility(4);
      this.RemoveReceiver_ViewNSendScreen.setVisibility(8);
      this.VideoPlay_ViewNSendScreen.setVisibility(4);
      this.ll_NotifyMe_ViewNSendScreen.setTag("true");
      this.SenderName_ViewNSendScreen.setHint(Html.fromHtml("<small>Enter Your Name</small>"));
      this.SenderName_ViewNSendScreen.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          ViewAndSendActivity.this.senderNameHasFocus = paramAnonymousBoolean;
          if ((ViewAndSendActivity.this.senderNameHasFocus) && (ViewAndSendActivity.this.SenderName_ViewNSendScreen.getText().toString().length() > 0)) {
            ViewAndSendActivity.this.SenderName_btn_cancel.setVisibility(0);
          }
          while (!CommonHelper.thanks_activity_calling.contains("ThanksActivity"))
          {
            ViewAndSendActivity.this.ScrollView_ViewNSendScreen.smoothScrollTo(0, ViewAndSendActivity.this.SenderName_ViewNSendScreen.getBottom() + ViewAndSendActivity.this.llecards.getBottom() - 10);
            return;
            ViewAndSendActivity.this.SenderName_btn_cancel.setVisibility(4);
          }
          CommonHelper.thanks_activity_calling = "";
        }
      });
      this.SenderName_ViewNSendScreen.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          if ((ViewAndSendActivity.this.senderNameHasFocus) && (ViewAndSendActivity.this.SenderName_ViewNSendScreen.getText().toString().length() > 0))
          {
            ViewAndSendActivity.this.SenderName_btn_cancel.setVisibility(0);
            return;
          }
          ViewAndSendActivity.this.SenderName_btn_cancel.setVisibility(4);
        }
      });
      this.SenderEmail_ViewNSendScreen.setHint(Html.fromHtml("<small>Enter Your Email</small>"));
      this.SenderEmail_ViewNSendScreen.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          ViewAndSendActivity.this.senderEmailHasFocus = paramAnonymousBoolean;
          if ((ViewAndSendActivity.this.senderEmailHasFocus) && (ViewAndSendActivity.this.SenderEmail_ViewNSendScreen.getText().toString().length() > 0)) {
            ViewAndSendActivity.this.SenderEmail_btn_cancel.setVisibility(0);
          }
          for (;;)
          {
            ViewAndSendActivity.this.ScrollView_ViewNSendScreen.smoothScrollTo(0, ViewAndSendActivity.this.SenderEmail_ViewNSendScreen.getBottom() + ViewAndSendActivity.this.llecards.getBottom());
            return;
            ViewAndSendActivity.this.SenderEmail_btn_cancel.setVisibility(4);
          }
        }
      });
      this.SenderEmail_ViewNSendScreen.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          if ((ViewAndSendActivity.this.senderEmailHasFocus) && (ViewAndSendActivity.this.SenderEmail_ViewNSendScreen.getText().toString().length() > 0))
          {
            ViewAndSendActivity.this.SenderEmail_btn_cancel.setVisibility(0);
            return;
          }
          ViewAndSendActivity.this.SenderEmail_btn_cancel.setVisibility(4);
        }
      });
      this.Message_ViewNSendScreen.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
      this.Message_ViewNSendScreen.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousView.getId() == 2131099846) {
            paramAnonymousView.getParent().requestDisallowInterceptTouchEvent(true);
          }
          switch (paramAnonymousMotionEvent.getAction() & 0xFF)
          {
          default: 
            return false;
          }
          paramAnonymousView.getParent().requestDisallowInterceptTouchEvent(false);
          return false;
        }
      });
      this.DeliveryDate_ViewNSendScreen.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
      this.DeliveryDate_ViewNSendScreen.setFocusableInTouchMode(false);
      this.DeliveryDate_ViewNSendScreen.setFocusable(false);
      this.DeliveryDate_ViewNSendScreen_show_value.setFocusableInTouchMode(false);
      this.DeliveryDate_ViewNSendScreen_show_value.setFocusable(false);
      this.CardTitle_ViewNSendScreen.setText(this.TITLE);
      if (!Constant.ThanksUtils.SENDTOANOTHER)
      {
        localObject1 = new GetCardDetailsAsync(null);
        if (Build.VERSION.SDK_INT >= 11)
        {
          ((GetCardDetailsAsync)localObject1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
          Constant.ThanksUtils.SENDTOANOTHER = false;
          this.SenderName_ViewNSendScreen.setText((CharSequence)localObject3);
          this.SenderEmail_ViewNSendScreen.setText((CharSequence)localObject4);
          this.spinner_SetCountry = ((TextView)this.rlViewandSend.findViewById(2131099841));
          return;
          localObject5 = arrayOfAccount[i];
          localObject3 = localObject2;
          localObject1 = localObject2;
          str1 = str2;
          if (((Pattern)localObject4).matcher(((Account)localObject5).name).matches())
          {
            localObject1 = localObject2;
            str1 = str2;
            localObject3 = ((Account)localObject5).name;
          }
          localObject1 = localObject3;
          str1 = str2;
          localObject5 = ((Account)localObject5).name;
          localObject2 = localObject3;
          if (localObject5 != "") {
            continue;
          }
          i += 1;
          localObject2 = localObject3;
          break label986;
        }
      }
      label986:
      if (i < j) {}
    }
  }
  
  private void shareInfo()
  {
    new FacebookHelper(this, this.uiHelper).shareOnFacebook();
  }
  
  public void friendPickerdialogshow()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, 3);
    View localView = getLayoutInflater().inflate(2130903116, null);
    localBuilder.setView(localView);
    ListView localListView = (ListView)localView.findViewById(2131100044);
    ((SideBar)localView.findViewById(2131100045)).setListView(localListView);
    localListView.setAdapter(new FriendAdapter(this, this.all_contacts_fb));
    localBuilder.show();
  }
  
  public int[] getScreenSize()
  {
    Display localDisplay = getWindowManager().getDefaultDisplay();
    return new int[] { localDisplay.getWidth(), localDisplay.getHeight() };
  }
  
  public void goToFacebookFriendsList()
  {
    startActivity(new Intent(this, FacebookFriendsList.class));
  }
  
  @SuppressLint({"NewApi"})
  public void initialize()
  {
    try
    {
      CommonHelper.LAST_CATEGORY = -1;
      this.rlViewandSend = ((RelativeLayout)inflater.inflate(2130903056, null));
      this.llbasemiddle.addView(this.rlViewandSend);
      if (Build.VERSION.SDK_INT > 9) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
      }
      CommonHelper.getInstance().addToActivityStack(this);
      CommonHelper.getInstance().getActivityStack().size();
      bindControls();
      initializedata();
      return;
    }
    catch (Exception localException)
    {
      showToast("No result Found");
    }
  }
  
  public boolean isPackageExists(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.equals(paramString));
    return true;
  }
  
  public void loadAdd()
  {
    try
    {
      final LinearLayout localLinearLayout = (LinearLayout)findViewById(2131099807);
      localLinearLayout.removeAllViews();
      final AdLayout localAdLayout = new AdLayout(this, com.amazon.device.ads.AdSize.SIZE_AUTO);
      localLinearLayout.addView(localAdLayout, new LinearLayout.LayoutParams(-1, -2));
      AdTargetingOptions localAdTargetingOptions = new AdTargetingOptions().enableGeoLocation(true);
      localAdLayout.setListener(new AdListener()
      {
        public void onAdCollapsed(Ad paramAnonymousAd) {}
        
        public void onAdDismissed(Ad paramAnonymousAd) {}
        
        public void onAdExpanded(Ad paramAnonymousAd) {}
        
        public void onAdFailedToLoad(Ad paramAnonymousAd, AdError paramAnonymousAdError)
        {
          ViewAndSendActivity.this.stopADRefreshTask();
          localLinearLayout.removeView(localAdLayout);
          ViewAndSendActivity.this.show_dfp_ad();
        }
        
        public void onAdLoaded(Ad paramAnonymousAd, AdProperties paramAnonymousAdProperties)
        {
          ViewAndSendActivity.this.stopADRefreshTask();
          ViewAndSendActivity.this.getAmazonHandaler().postDelayed(ViewAndSendActivity.this.amazonADrefresher, CommonHelper.AMAZON_AD_REFRESH_INTERVAL);
        }
      });
      localAdLayout.loadAd(localAdTargetingOptions);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void loginToFacebook()
  {
    if (new NetworkCalls(this).isNetworkAvalable())
    {
      Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
      String str = ((SharedPreferences)localObject).getString("access_token", null);
      long l = ((SharedPreferences)localObject).getLong("access_expires", 0L);
      if (str != null) {
        this.mFacebook.setAccessToken(str);
      }
      if (l != 0L) {
        this.mFacebook.setAccessExpires(l);
      }
      this.mFacebook.setAccessExpires(5L);
      if (!this.mFacebook.isSessionValid())
      {
        this.mFacebook.authorize(this, PERMISSIONS, new Facebook.DialogListener()
        {
          public void onCancel() {}
          
          public void onComplete(Bundle paramAnonymousBundle)
          {
            System.out.println("fbLogin Successfull");
            ViewAndSendActivity.this.editor.putString("access_token", ViewAndSendActivity.this.mFacebook.getAccessToken());
            ViewAndSendActivity.this.editor.putLong("access_expires", ViewAndSendActivity.this.mFacebook.getAccessExpires());
            ViewAndSendActivity.this.editor.commit();
            if (ViewAndSendActivity.this.isMyWall)
            {
              ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline", Long.valueOf(0L));
              FlurryAgent.logEvent("Fb_Timeline");
              ViewAndSendActivity.this.postToWall("");
              return;
            }
            if (ViewAndSendActivity.this.getIntent().getExtras().containsKey("bdayvalues"))
            {
              paramAnonymousBundle = (HashMap)ViewAndSendActivity.this.getIntent().getSerializableExtra("bdayvalues");
              if (paramAnonymousBundle.get("source").toString().equals("fb"))
              {
                ViewAndSendActivity.this.postToWall(paramAnonymousBundle.get("FB_ID").toString());
                return;
              }
              if (ViewAndSendActivity.this.all_contacts_fb.size() > 0)
              {
                ViewAndSendActivity.this.friendPickerdialogshow();
                return;
              }
              new ViewAndSendActivity.ImportContactsTask(ViewAndSendActivity.this).execute(new String[0]);
              return;
            }
            if (ViewAndSendActivity.this.all_contacts_fb.size() > 0)
            {
              ViewAndSendActivity.this.friendPickerdialogshow();
              return;
            }
            new ViewAndSendActivity.ImportContactsTask(ViewAndSendActivity.this).execute(new String[0]);
          }
          
          public void onError(DialogError paramAnonymousDialogError)
          {
            System.out.println("fbLogin onerror");
            Toast.makeText(ViewAndSendActivity.this.getApplicationContext(), "FB Login Error", 1).show();
          }
          
          public void onFacebookError(FacebookError paramAnonymousFacebookError)
          {
            System.out.println("fbLogin onfberror" + paramAnonymousFacebookError);
            Toast.makeText(ViewAndSendActivity.this.getApplicationContext(), "On FB Error", 1).show();
          }
        });
        return;
      }
      if (this.isMyWall)
      {
        this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline", Long.valueOf(0L));
        FlurryAgent.logEvent("Fb_Timeline");
        postToWall("");
        return;
      }
      if (getIntent().getExtras().containsKey("bdayvalues"))
      {
        localObject = (HashMap)getIntent().getSerializableExtra("bdayvalues");
        if (((HashMap)localObject).get("source").toString().equals("fb"))
        {
          postToWall(((HashMap)localObject).get("FB_ID").toString());
          return;
        }
        if (this.all_contacts_fb.size() > 0)
        {
          friendPickerdialogshow();
          return;
        }
        new ImportContactsTask().execute(new String[0]);
        return;
      }
      if (this.all_contacts_fb.size() > 0)
      {
        friendPickerdialogshow();
        return;
      }
      new ImportContactsTask().execute(new String[0]);
      return;
    }
    Toast.makeText(getApplicationContext(), 2131165268, 1).show();
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1234)
    {
      this.tracker.sendEvent("ui-action", "button_press", "Sms_Send", Long.valueOf(0L));
      FlurryAgent.logEvent("Sms_Send");
      paramIntent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      new TrackSentTask().execute(new String[] { CARD_ID, "SMS", "Success", "", paramIntent });
    }
    do
    {
      do
      {
        return;
        Object localObject1;
        if (paramInt1 == 22)
        {
          paramIntent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
          if (paramInt2 == -1)
          {
            this.tracker.sendEvent("ui-action", "button_press", "Google_Plus_Post", Long.valueOf(0L));
            FlurryAgent.logEvent("Google_Plus_Post");
            paramIntent = CommonHelper.card_url_for_sms;
            localObject1 = new Intent(this, ThanksActivity.class);
            ((Intent)localObject1).putExtra("DisplayMsg", "Your ecard has been posted successfully!");
            ((Intent)localObject1).putExtra("cardUrl", paramIntent);
            ((Intent)localObject1).putExtra("senderNameForThanks", "");
            ((Intent)localObject1).putExtra("senderEmailForThanks", "");
            ((Intent)localObject1).putExtra("receiverNameForThanks", "");
            ((Intent)localObject1).putExtra("receiverEmailForThanks", "");
            ((Intent)localObject1).putExtra("cardImgUrlForThanks", CommonHelper.card_image_url_fb);
            ((Intent)localObject1).putExtra("cardIdForThanks", CARD_ID);
            ((Intent)localObject1).putExtra("CALENDER_STARTDATE", this.startDateForCalender);
            ((Intent)localObject1).putExtra("title", CommonHelper.shareName);
            ((Intent)localObject1).putExtra("send_type", "fb_");
            CommonHelper.track_gplus = true;
            startActivity((Intent)localObject1);
            finish();
            Constant.ThanksUtils.WISH_NOW = false;
            return;
          }
          new TrackSentTask().execute(new String[] { CARD_ID, "Google_Plus_Post", "Failure", "", paramIntent });
          return;
        }
        if (paramInt1 == 1)
        {
          if (paramInt2 == -1)
          {
            Object localObject4 = null;
            Cursor localCursor2 = null;
            String str4 = "";
            String str3 = "";
            Cursor localCursor1 = localCursor2;
            Object localObject3 = str4;
            String str2 = str3;
            localObject1 = localObject4;
            Object localObject2 = str4;
            String str1 = str3;
            try
            {
              paramIntent = paramIntent.getData();
              localCursor1 = localCursor2;
              localObject3 = str4;
              str2 = str3;
              localObject1 = localObject4;
              localObject2 = str4;
              str1 = str3;
              Log.v(this.DEBUG_TAG, "Got a contact result: " + paramIntent.toString());
              localCursor1 = localCursor2;
              localObject3 = str4;
              str2 = str3;
              localObject1 = localObject4;
              localObject2 = str4;
              str1 = str3;
              paramIntent = paramIntent.getLastPathSegment();
              localCursor1 = localCursor2;
              localObject3 = str4;
              str2 = str3;
              localObject1 = localObject4;
              localObject2 = str4;
              str1 = str3;
              localCursor2 = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id=?", new String[] { paramIntent }, null);
              localCursor1 = localCursor2;
              localObject3 = str4;
              str2 = str3;
              localObject1 = localCursor2;
              localObject2 = str4;
              str1 = str3;
              paramInt1 = localCursor2.getColumnIndex("display_name");
              localCursor1 = localCursor2;
              localObject3 = str4;
              str2 = str3;
              localObject1 = localCursor2;
              localObject2 = str4;
              str1 = str3;
              paramInt2 = localCursor2.getColumnIndex("data1");
              localCursor1 = localCursor2;
              localObject3 = str4;
              str2 = str3;
              localObject1 = localCursor2;
              localObject2 = str4;
              str1 = str3;
              if (localCursor2.moveToFirst())
              {
                localCursor1 = localCursor2;
                localObject3 = str4;
                str2 = str3;
                localObject1 = localCursor2;
                localObject2 = str4;
                str1 = str3;
                paramIntent = localCursor2.getString(paramInt2);
                localCursor1 = localCursor2;
                localObject3 = paramIntent;
                str2 = str3;
                localObject1 = localCursor2;
                localObject2 = paramIntent;
                str1 = str3;
                str3 = localCursor2.getString(paramInt1);
                localCursor1 = localCursor2;
                localObject3 = paramIntent;
                str2 = str3;
                localObject1 = localCursor2;
                localObject2 = paramIntent;
                str1 = str3;
                Log.v(this.DEBUG_TAG, "Got email: " + paramIntent);
              }
              for (;;)
              {
                if (localCursor2 != null) {
                  localCursor2.close();
                }
                if (paramIntent.length() > 2) {
                  break;
                }
                showToast("No Email Id Found For Selected Contact");
                return;
                localCursor1 = localCursor2;
                localObject3 = str4;
                str2 = str3;
                localObject1 = localCursor2;
                localObject2 = str4;
                str1 = str3;
                Log.w(this.DEBUG_TAG, "No results");
                paramIntent = str4;
              }
              Log.w(this.DEBUG_TAG, "Warning: activity result not ok");
            }
            catch (Exception paramIntent)
            {
              localObject1 = localCursor1;
              localObject2 = localObject3;
              str1 = str2;
              Log.e(this.DEBUG_TAG, "Failed to get email data", paramIntent);
              if (localCursor1 != null) {
                localCursor1.close();
              }
              if (((String)localObject3).length() <= 2)
              {
                showToast("No Email Id Found For Selected Contact");
                return;
              }
              setNameAndEmail(CONTACT_TAG, str2, (String)localObject3);
              return;
            }
            finally
            {
              if (localObject1 != null) {
                ((Cursor)localObject1).close();
              }
              if (((String)localObject2).length() <= 2) {
                showToast("No Email Id Found For Selected Contact");
              }
              for (;;)
              {
                throw paramIntent;
                setNameAndEmail(CONTACT_TAG, str1, (String)localObject2);
              }
              setNameAndEmail(CONTACT_TAG, str3, paramIntent);
              return;
            }
          }
          return;
        }
        if (paramInt1 != 64207) {
          break;
        }
      } while (paramInt2 != -1);
      this.uiHelper.onActivityResult(paramInt1, paramInt2, paramIntent, new FacebookDialog.Callback()
      {
        public void onComplete(FacebookDialog.PendingCall paramAnonymousPendingCall, Bundle paramAnonymousBundle)
        {
          if ((paramAnonymousBundle != null) && (paramAnonymousBundle.getString("completionGesture").equals("post")))
          {
            ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Send", Long.valueOf(0L));
            FlurryAgent.logEvent("Fb_Timeline_Send");
            ViewAndSendActivity.this.showToastGreen(CommonHelper.facebookShareSuccess);
            paramAnonymousPendingCall = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            new ViewAndSendActivity.TrackSentTask(ViewAndSendActivity.this).execute(new String[] { ViewAndSendActivity.CARD_ID, "Facebook_My_Timeline", "Success", "", paramAnonymousPendingCall });
            return;
          }
          System.out.println("Facebook unsuccess");
          ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
          FlurryAgent.logEvent("Fb_Timeline_Cancel");
          ViewAndSendActivity.this.showToast(CommonHelper.facebookShareFail);
        }
        
        public void onError(FacebookDialog.PendingCall paramAnonymousPendingCall, Exception paramAnonymousException, Bundle paramAnonymousBundle)
        {
          Log.e("Activity", String.format("Error: %s", new Object[] { paramAnonymousException.toString() }));
        }
      });
      return;
      if (paramInt1 != 64206) {
        break;
      }
      if (paramInt2 == -1)
      {
        Session.getActiveSession().onActivityResult(this, paramInt1, paramInt2, paramIntent);
        return;
      }
    } while (paramInt2 != 0);
    this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
    FlurryAgent.logEvent("Fb_Timeline_Cancel");
    Log.e("Facebook", "logincanceld");
    return;
    this.mFacebook.authorizeCallback(paramInt1, paramInt2, paramIntent);
  }
  
  public void onClick(View paramView)
  {
    super.onClick(paramView);
    Object localObject1 = "";
    if (Category_Name.length() > 0) {
      localObject1 = " ";
    }
    switch (paramView.getId())
    {
    }
    Object localObject2;
    label710:
    do
    {
      for (;;)
      {
        return;
        this.tracker.sendEvent("ui-action", "button_press", "Tap Add More", Long.valueOf(0L));
        FlurryAgent.logEvent("Tap Add More");
        paramView = this.receiverlist.getLastNameAndEmail();
        if (paramView != null)
        {
          Constant.ReceiverDetails.RECEIVER_COUNT += 1;
          this.receiverView = this.receiverlist.inflateReceiverDetails(Constant.ReceiverDetails.RECEIVER_COUNT);
          int j;
          int i;
          if (Constant.ReceiverDetails.RECEIVER_COUNT > 1)
          {
            this.RemoveReceiver_ViewNSendScreen.setVisibility(0);
            j = 0;
            i = 0;
          }
          for (;;)
          {
            if (i >= receiverDetailsArrList.size())
            {
              if (j == 0) {
                receiverDetailsArrList.add(paramView);
              }
              this.AddReceiver_ViewNSendScreen.setBackgroundResource(2130837811);
              this.AddReceiver_ViewNSendScreen.setClickable(false);
              return;
              this.RemoveReceiver_ViewNSendScreen.setVisibility(8);
              break;
            }
            if (((String[])receiverDetailsArrList.get(i))[1].equalsIgnoreCase(paramView[1])) {
              j = 1;
            }
            i += 1;
          }
          ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
          if (receiverDetailsArrList.size() == Constant.ReceiverDetails.RECEIVER_COUNT) {
            receiverDetailsArrList.remove(receiverDetailsArrList.size() - 1);
          }
          Constant.ReceiverDetails.RECEIVER_COUNT -= 1;
          this.ReceiverMainLinear.removeViewAt(Constant.ReceiverDetails.RECEIVER_COUNT);
          this.receiverView = this.receiverlist.removeReceiver(Constant.ReceiverDetails.RECEIVER_COUNT);
          if (Constant.ReceiverDetails.RECEIVER_COUNT > 1) {
            this.RemoveReceiver_ViewNSendScreen.setVisibility(0);
          }
          for (;;)
          {
            this.AddReceiver_ViewNSendScreen.setBackgroundResource(2130837812);
            this.AddReceiver_ViewNSendScreen.setClickable(true);
            return;
            this.RemoveReceiver_ViewNSendScreen.setVisibility(8);
          }
          paramView = this.SenderName_ViewNSendScreen.getText().toString();
          localObject1 = this.SenderEmail_ViewNSendScreen.getText().toString().trim();
          if (paramView.equalsIgnoreCase(""))
          {
            this.SenderEmail_ViewNSendScreen.requestFocus();
            setErrorMessage(false, "Please enter sender name.");
            return;
          }
          if (((String)localObject1).equalsIgnoreCase(""))
          {
            this.SenderName_ViewNSendScreen.requestFocus();
            setErrorMessage(true, "Please enter sender email.");
            return;
          }
          if (EmailValidation.isEmailValid((String)localObject1))
          {
            i = 0;
            localObject2 = this.receiverlist.getLastNameAndEmail();
            if (localObject2 != null) {
              i = 1;
            }
            if (i != 0)
            {
              if (response_send_error.booleanValue())
              {
                receiverDetailsArrList.clear();
                new ArrayList();
                ArrayList localArrayList = this.receiverlist.getAll();
                i = 0;
                for (;;)
                {
                  if (i >= localArrayList.size())
                  {
                    response_send_error = Boolean.valueOf(false);
                    if (localObject2 == null) {
                      break;
                    }
                    Constant.ThanksUtils.SENDER_NAME = paramView;
                    Constant.ThanksUtils.SENDER_EMAIL = (String)localObject1;
                    Constant.ThanksUtils.RECEIVER_NAME = localObject2[0];
                    Constant.ThanksUtils.RECEIVER_EMAIL = localObject2[1];
                    Constant.ThanksUtils.MESSAGE = this.Message_ViewNSendScreen.getText().toString();
                    Constant.ThanksUtils.NOTIFY = this.ll_NotifyMe_ViewNSendScreen.getTag().toString();
                    Constant.ThanksUtils.TITLE = this.CardTitle_ViewNSendScreen.getText().toString();
                    Constant.SenderDetails.SENDER_NAME = paramView;
                    Constant.SenderDetails.SENDER_EMAIL = (String)localObject1;
                    if (Constant.CountryListUtils.COUNTRY_ID_SELECTED.equalsIgnoreCase("")) {
                      Constant.CountryListUtils.COUNTRY_ID_SELECTED = this.country;
                    }
                    paramView = new Intent(this, PreviewActivity.class);
                    paramView.putExtra("sender_name", Constant.ThanksUtils.SENDER_NAME);
                    paramView.putExtra("sender_email", Constant.ThanksUtils.SENDER_EMAIL);
                    paramView.putExtra("receiver_name", Constant.ThanksUtils.RECEIVER_NAME);
                    paramView.putExtra("receiver_email", Constant.ThanksUtils.RECEIVER_EMAIL);
                    paramView.putExtra("delivery_date", this.DeliveryDate_ViewNSendScreen.getText().toString());
                    paramView.putExtra("notify", Constant.ThanksUtils.NOTIFY);
                    paramView.putExtra("card_id", CARD_ID);
                    paramView.putExtra("card_url_preview", IMAGE_URL);
                    paramView.putExtra("card_video_preview", this.VIDEO_URL);
                    paramView.putExtra("message", Constant.ThanksUtils.MESSAGE);
                    paramView.putExtra("title", Constant.ThanksUtils.TITLE);
                    startActivity(paramView);
                    return;
                  }
                  receiverDetailsArrList.add((String[])localArrayList.get(i));
                  i += 1;
                }
              }
              j = 0;
              i = 0;
              for (;;)
              {
                if (i >= receiverDetailsArrList.size())
                {
                  if (j != 0) {
                    break label710;
                  }
                  receiverDetailsArrList.add(localObject2);
                  break label710;
                  break;
                }
                if (((String[])receiverDetailsArrList.get(i))[1].equalsIgnoreCase(localObject2[1])) {
                  j = 1;
                }
                i += 1;
              }
            }
          }
          else
          {
            this.SenderName_ViewNSendScreen.requestFocus();
            setErrorMessage(true, "Please enter valid sender email.");
            return;
            this.tracker.sendEvent("ui-action", "button_press", "Card send  Tap Send", Long.valueOf(0L));
            FlurryAgent.logEvent("Card send  Tap Send");
            paramView = this.SenderName_ViewNSendScreen.getText().toString();
            localObject1 = this.SenderEmail_ViewNSendScreen.getText().toString().trim();
            if (paramView.equalsIgnoreCase(""))
            {
              this.SenderEmail_ViewNSendScreen.requestFocus();
              setErrorMessage(false, "Please enter sender name.");
              return;
            }
            if (((String)localObject1).equalsIgnoreCase(""))
            {
              this.SenderName_ViewNSendScreen.requestFocus();
              setErrorMessage(true, "Please enter sender email.");
              return;
            }
            if (EmailValidation.isEmailValid((String)localObject1))
            {
              i = 0;
              localObject2 = this.receiverlist.getLastNameAndEmail();
              if (localObject2 != null) {
                i = 1;
              }
              if (i != 0)
              {
                if (response_send_error.booleanValue())
                {
                  receiverDetailsArrList.clear();
                  new ArrayList();
                  localObject2 = this.receiverlist.getAll();
                  i = 0;
                  for (;;)
                  {
                    if (i >= ((ArrayList)localObject2).size())
                    {
                      response_send_error = Boolean.valueOf(false);
                      if (!new NetworkCalls(this).isNetworkAvalable()) {
                        break;
                      }
                      Constant.SenderDetails.SENDER_NAME = paramView;
                      Constant.SenderDetails.SENDER_EMAIL = (String)localObject1;
                      this.editor.putString("sender_name", Constant.SenderDetails.SENDER_NAME);
                      this.editor.putString("sender_email", Constant.SenderDetails.SENDER_EMAIL);
                      this.editor.commit();
                      new SendAsyncTask(null).execute(new String[] { "" });
                      return;
                    }
                    receiverDetailsArrList.add((String[])((ArrayList)localObject2).get(i));
                    i += 1;
                  }
                }
                j = 0;
                i = 0;
                for (;;)
                {
                  if (i >= receiverDetailsArrList.size())
                  {
                    if (j != 0) {
                      break;
                    }
                    receiverDetailsArrList.add(localObject2);
                    break;
                  }
                  if (((String[])receiverDetailsArrList.get(i))[1].equalsIgnoreCase(localObject2[1])) {
                    j = 1;
                  }
                  i += 1;
                }
                showToast(getResources().getString(2131165268));
              }
            }
            else
            {
              this.SenderName_ViewNSendScreen.requestFocus();
              setErrorMessage(true, "Please enter valid sender email.");
              return;
              if (this.VIDEO_URL.equalsIgnoreCase(""))
              {
                Alert.display(this, "No Video Available Now");
                return;
              }
              if (this.VIDEO_URL.contains("youtube"))
              {
                paramView = new Intent("android.intent.action.VIEW");
                paramView.setData(Uri.parse(this.VIDEO_URL));
                Log.e("youtube url", this.VIDEO_URL);
                startActivity(paramView);
                return;
              }
              paramView = new Intent(this, VideoPlayActivity.class);
              paramView.putExtra("video_url_viewnsend", this.VIDEO_URL);
              startActivity(paramView);
              return;
              this.tracker.sendEvent("ui-action", "button_press", "Card Tap Schedule", Long.valueOf(0L));
              FlurryAgent.logEvent("Card Tap Schedule");
              paramView = new Intent(this, CalendarView.class);
              paramView.putExtra("PST_date", this.DeliveryDate_ViewNSendScreen.getText().toString());
              paramView.putExtra("server_date", this.server_date);
              startActivity(paramView);
              return;
              this.tracker.sendEvent("ui-action", "button_press", "Card Tap Schedule", Long.valueOf(0L));
              FlurryAgent.logEvent("Card Tap Schedule");
              paramView = new Intent(this, CalendarView.class);
              paramView.putExtra("PST_date", this.DeliveryDate_ViewNSendScreen.getText().toString());
              paramView.putExtra("server_date", this.server_date);
              startActivity(paramView);
              return;
              if ((this.edit_search.getText().toString().trim().length() > 0) && (this.edit_search.getText().toString().trim().length() > 0))
              {
                paramView = new Intent(this, CategoryActivityNew.class);
                paramView.putExtra("CategoryId", this.edit_search.getText().toString());
                paramView.putExtra("URL", "http://www.123greetings.com/mobile_api/search_subcats?devicetype=android");
                paramView.putExtra("SEARCH", true);
                paramView.putExtra("MENU_OPTION", false);
                this.edit_search.setText("");
                localObject1 = CommonHelper.getInstance();
                j = ((CommonHelper)localObject1).getActivityStack().size();
                i = 0;
                for (;;)
                {
                  if (i >= j)
                  {
                    startActivity(paramView);
                    return;
                  }
                  localObject2 = (Activity)((CommonHelper)localObject1).getActivityStack().get(((CommonHelper)localObject1).getActivityStack().size() - 1);
                  if (!(localObject2 instanceof HomeActivity))
                  {
                    ((CommonHelper)localObject1).getActivityStack().remove(((CommonHelper)localObject1).getActivityStack().size() - 1);
                    ((Activity)localObject2).finish();
                  }
                  i += 1;
                }
                ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
                return;
                this.SenderName_ViewNSendScreen.setText("");
                return;
                this.SenderEmail_ViewNSendScreen.setText("");
                return;
                startActivity(new Intent(this, ProfileActivity.class));
                return;
                startActivity(new Intent(this, ProfileActivity.class));
                return;
                popUpforlist();
                return;
                CONTACT_TAG = Integer.parseInt(((ImageButton)paramView).getTag().toString().split(",")[0]) - 1;
                this.intent = new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(this.intent, 1);
                return;
                this.btemail.setBackgroundResource(2130837708);
                this.btfacebook.setBackgroundResource(2130837711);
                this.llemail.setVisibility(0);
                this.llfacebook.setVisibility(8);
                this.tracker.sendEvent("ui-action", "button_press", "Email_Tab", Long.valueOf(0L));
                FlurryAgent.logEvent("Email_Tab");
                this.message_block.setVisibility(0);
                return;
                ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
                this.llemail.setVisibility(8);
                this.llfacebook.setVisibility(8);
                this.tracker.sendEvent("ui-action", "button_press", "Sms_Tab", Long.valueOf(0L));
                FlurryAgent.logEvent("Sms_Tab");
                paramView = new Intent("android.intent.action.VIEW");
                paramView.setData(Uri.parse("smsto:"));
                paramView.putExtra("sms_body", "I have sent you " + this.cat_first_char + (String)localObject1 + Category_Name + " ecard." + "\n\n" + CommonHelper.shareUrl + "\n\n\nvia 123Greetings App");
                try
                {
                  startActivityForResult(paramView, 1234);
                  Log.i("Finished sending SMS...", "");
                  this.btemail.performClick();
                  return;
                }
                catch (ActivityNotFoundException paramView)
                {
                  for (;;)
                  {
                    Toast.makeText(this, "SMS faild, please try again later.", 0).show();
                  }
                }
                ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
                this.btemail.setBackgroundResource(2130837707);
                this.btfacebook.setBackgroundResource(2130837712);
                this.tracker.sendEvent("ui-action", "button_press", "Facebook_Tab", Long.valueOf(0L));
                FlurryAgent.logEvent("Facebook_Tab");
                this.llemail.setVisibility(8);
                this.llfacebook.setVisibility(0);
                this.message_block.setVisibility(8);
                return;
                this.tracker.sendEvent("ui-action", "button_press", "Line_tab", Long.valueOf(0L));
                FlurryAgent.logEvent("Line_tab");
                try
                {
                  paramView = new Intent("android.intent.action.SEND");
                  paramView.setType("text/plain");
                  paramView.putExtra("android.intent.extra.SUBJECT", "123greetings ecard");
                  paramView.putExtra("android.intent.extra.TEXT", "I have sent you " + this.cat_first_char + (String)localObject1 + Category_Name + " ecard.\nClick the link below to view the ecard.\n" + CommonHelper.shareUrl + "\n\nvia 123Greetings App.");
                  paramView.setPackage("jp.naver.line.android");
                  if (paramView != null)
                  {
                    startActivity(paramView);
                    return;
                  }
                }
                catch (ActivityNotFoundException paramView)
                {
                  try
                  {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=jp.naver.line.android")));
                    return;
                  }
                  catch (ActivityNotFoundException paramView)
                  {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=jp.naver.line.android")));
                    return;
                  }
                }
              }
            }
          }
        }
      }
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
      this.llemail.setVisibility(8);
      this.llfacebook.setVisibility(8);
      this.iswhatsAppexist = isPackageExists("com.whatsapp");
      if (this.iswhatsAppexist)
      {
        this.tracker.sendEvent("ui-action", "button_press", "Whatsapp_Tab", Long.valueOf(0L));
        FlurryAgent.logEvent("Whatsapp_Tab");
        paramView = new Intent("android.intent.action.SEND");
        paramView.setType("text/plain");
        this.whatsAppText = ("I have sent you " + this.cat_first_char + (String)localObject1 + Category_Name + " ecard.\nClick the link below to view the ecard.\n" + CommonHelper.shareUrl + "\n\nvia 123Greetings App.");
        paramView.setPackage("com.whatsapp");
        if (paramView != null)
        {
          paramView.putExtra("android.intent.extra.TEXT", this.whatsAppText);
          startActivity(Intent.createChooser(paramView, "Share with"));
        }
      }
      for (;;)
      {
        this.btemail.performClick();
        return;
        try
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.whatsapp")));
        }
        catch (ActivityNotFoundException paramView)
        {
          startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
        }
      }
      ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(paramView.getWindowToken(), 0);
      this.llemail.setVisibility(8);
      this.llfacebook.setVisibility(8);
      this.tracker.sendEvent("ui-action", "button_press", "More", Long.valueOf(0L));
      FlurryAgent.logEvent("More");
      this.intent = new Intent(this, MoreActivity.class);
      this.intent.putExtra("Cat_name", Category_Name);
      this.intent.putExtra("Shareurl", CommonHelper.shareUrl);
      startActivity(this.intent);
      this.btemail.performClick();
      return;
      sendMessagetoFriends();
      return;
      this.isMyWall = true;
      loginToFacebook();
      return;
      this.isMyWall = false;
      loginToFacebook();
      return;
      sendMessagetoFriends();
      return;
      paramView = new Intent(this, CalendarView.class);
      paramView.putExtra("PST_date", this.DeliveryDate_ViewNSendScreen.getText().toString());
      paramView.putExtra("server_date", this.server_date);
      startActivity(paramView);
      return;
      paramView = new Intent(this, CalendarView.class);
      paramView.putExtra("PST_date", this.DeliveryDate_ViewNSendScreen.getText().toString());
      paramView.putExtra("server_date", this.server_date);
      startActivity(paramView);
      return;
      localObject1 = (Button)paramView.findViewById(2131099843);
      localObject2 = paramView.getTag().toString();
      if (((String)localObject2).equalsIgnoreCase("true"))
      {
        paramView.setTag("false");
        ((Button)localObject1).setBackgroundResource(2130837878);
        return;
      }
    } while (!((String)localObject2).equalsIgnoreCase("false"));
    paramView.setTag("true");
    ((Button)localObject1).setBackgroundResource(2130837613);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    int j;
    int i;
    do
    {
      try
      {
        if (this.customDialogProgress.isShowing()) {
          this.customDialogProgress.dismiss();
        }
        this.uiHelper.onDestroy();
        localCommonHelper = CommonHelper.getInstance();
        j = localCommonHelper.getActivityStack().size();
        i = 0;
      }
      catch (Exception localException)
      {
        CommonHelper localCommonHelper;
        return;
      }
      if (((Activity)localCommonHelper.getActivityStack().get(localCommonHelper.getActivityStack().size() - 1) instanceof ThanksActivity)) {
        localCommonHelper.getActivityStack().remove(localCommonHelper.getActivityStack().size() - 1);
      }
      i += 1;
    } while (i < j);
  }
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramInt == 66))
    {
      paramView = new Intent(this, CategoryActivityNew.class);
      paramView.putExtra("CategoryId", this.edit_search.getText().toString());
      paramView.putExtra("URL", "http://www.123greetings.com/mobile_api/search_subcats?devicetype=android");
      paramView.putExtra("SEARCH", true);
      paramView.putExtra("MENU_OPTION", false);
      this.edit_search.setText("");
      startActivity(paramView);
      return true;
    }
    return false;
  }
  
  protected void onResume()
  {
    super.onResume();
    for (;;)
    {
      try
      {
        this.tracker.sendView("View & Send");
        FlurryAgent.logEvent("View & Send");
        if (this.edit_search != null)
        {
          this.edit_search.setEnabled(true);
          CommonHelper.menuOut = false;
          CommonHelper.menuOutRight = false;
        }
        if (Constant.ThanksUtils.DELIVERY_DATE.equalsIgnoreCase("")) {
          break;
        }
        if (Constant.ThanksUtils.DELIVERY_DATE.equalsIgnoreCase("previous"))
        {
          showToast(getResources().getString(2131165270));
          this.ScrollView_ViewNSendScreen.post(new Runnable()
          {
            public void run()
            {
              ViewAndSendActivity.this.ScrollView_ViewNSendScreen.fullScroll(130);
            }
          });
          Constant.ThanksUtils.DELIVERY_DATE = "";
          return;
        }
        if (Constant.ThanksUtils.DELIVERY_DATE.equalsIgnoreCase("exceeded"))
        {
          showToast(getResources().getString(2131165269));
          continue;
        }
        Log.e("come", "casa");
      }
      catch (Exception localException)
      {
        showToast("No result Found");
        System.out.println("Cardsubcategories memory error!");
        return;
      }
      this.DeliveryDate_ViewNSendScreen.setText(Constant.ThanksUtils.DELIVERY_DATE);
      this.DeliveryDate_ViewNSendScreen_show_value.setText(Constant.ThanksUtils.DELIVERY_DATE);
      if (Constant.ThanksUtils.DELIVERY_DATE.equals(this.server_date)) {
        this.DeliveryDate_ViewNSendScreen_show_value.setText("Today");
      }
      if (Constant.DateUtils.CURRENT_DEFERRED_DATETYPE.equalsIgnoreCase("deferred"))
      {
        this.CountryListFrame.setVisibility(0);
        String str = "";
        if (Constant.CountryListUtils.COUNTRY_ID_SELECTED.equalsIgnoreCase("")) {
          str = this.country;
        }
        int i = Constant.CountryListUtils.COUNTRY_ID.indexOf(str.toLowerCase(Locale.US));
        if ((i != -1) && (i < Constant.CountryListUtils.COUNTRY_NAMES.size())) {
          this.spinner_SetCountry.setText((CharSequence)Constant.CountryListUtils.COUNTRY_NAMES.get(i));
        }
      }
      else
      {
        this.CountryListFrame.setVisibility(8);
        Constant.CountryListUtils.COUNTRY_ID_SELECTED = "";
      }
    }
  }
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {}
  
  public void postToWall(String paramString)
  {
    if (new NetworkCalls(this).isNetworkAvalable())
    {
      String str = "";
      if (Category_Name.length() > 0) {
        str = " ";
      }
      Bundle localBundle = new Bundle();
      localBundle.putString("app_id", "6268317308");
      if (!this.isMyWall)
      {
        localBundle.putString("to", paramString);
        localBundle.putString("description", CommonHelper.shareName);
        localBundle.putString("name", "I have sent you " + this.cat_first_char + str + Category_Name + " ecard.");
      }
      for (;;)
      {
        localBundle.putString("caption", "www.123greetings.com");
        localBundle.putString("link", CommonHelper.shareUrl);
        localBundle.putString("picture", CommonHelper.shareImage);
        ((WebDialog.FeedDialogBuilder)new WebDialog.FeedDialogBuilder(this, this.mFacebook.getSession(), localBundle).setOnCompleteListener(new WebDialog.OnCompleteListener()
        {
          public void onComplete(Bundle paramAnonymousBundle, FacebookException paramAnonymousFacebookException)
          {
            if (paramAnonymousFacebookException == null)
            {
              if (paramAnonymousBundle.getString("post_id") != null)
              {
                ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Send", Long.valueOf(0L));
                FlurryAgent.logEvent("Fb_Timeline_Send");
                paramAnonymousBundle = CommonHelper.card_url_for_sms;
                paramAnonymousFacebookException = new Intent(ViewAndSendActivity.this, ThanksActivity.class);
                paramAnonymousFacebookException.putExtra("DisplayMsg", "Your ecard has been posted successfully!");
                paramAnonymousFacebookException.putExtra("cardUrl", paramAnonymousBundle);
                paramAnonymousFacebookException.putExtra("senderNameForThanks", "");
                paramAnonymousFacebookException.putExtra("senderEmailForThanks", "");
                paramAnonymousFacebookException.putExtra("receiverNameForThanks", "");
                paramAnonymousFacebookException.putExtra("receiverEmailForThanks", "");
                paramAnonymousFacebookException.putExtra("cardImgUrlForThanks", CommonHelper.card_image_url_fb);
                paramAnonymousFacebookException.putExtra("cardIdForThanks", ViewAndSendActivity.CARD_ID);
                paramAnonymousFacebookException.putExtra("CALENDER_STARTDATE", ViewAndSendActivity.this.startDateForCalender);
                paramAnonymousFacebookException.putExtra("title", CommonHelper.shareName);
                paramAnonymousFacebookException.putExtra("send_type", "fb_");
                if (ViewAndSendActivity.this.isMyWall) {}
                for (CommonHelper.track_fbismywall = true;; CommonHelper.track_fbismywall = false)
                {
                  ViewAndSendActivity.this.startActivity(paramAnonymousFacebookException);
                  ViewAndSendActivity.this.finish();
                  Constant.ThanksUtils.WISH_NOW = false;
                  return;
                }
              }
              Toast.makeText(ViewAndSendActivity.this.getApplicationContext(), "Publish cancelled", 0).show();
              ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
              FlurryAgent.logEvent("Fb_Timeline_Cancel");
              return;
            }
            if ((paramAnonymousFacebookException instanceof FacebookOperationCanceledException))
            {
              Toast.makeText(ViewAndSendActivity.this.getApplicationContext(), "Publish cancelled", 0).show();
              ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
              FlurryAgent.logEvent("Fb_Timeline_Cancel");
              return;
            }
            Toast.makeText(ViewAndSendActivity.this.getApplicationContext(), "Error posting story", 0).show();
            ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
            FlurryAgent.logEvent("Fb_Timeline_Cancel");
            if (ViewAndSendActivity.this.isMyWall)
            {
              paramAnonymousBundle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
              new ViewAndSendActivity.TrackSentTask(ViewAndSendActivity.this).execute(new String[] { ViewAndSendActivity.CARD_ID, "Facebook_My_Timeline", "Failure", "Error posting story", paramAnonymousBundle });
              return;
            }
            paramAnonymousBundle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            new ViewAndSendActivity.TrackSentTask(ViewAndSendActivity.this).execute(new String[] { ViewAndSendActivity.CARD_ID, "Facebook_My_Friends_Timeline", "Failure", "Error posting story", paramAnonymousBundle });
          }
        })).build().show();
        return;
        localBundle.putString("description", CommonHelper.shareName);
        localBundle.putString("name", "I have shared " + this.cat_first_char + str + Category_Name + " ecard.");
      }
    }
    Toast.makeText(getApplicationContext(), 2131165268, 1).show();
  }
  
  public void sendMessagetoFriends()
  {
    String str = "";
    if (Category_Name.length() > 0) {
      str = " ";
    }
    FlurryAgent.logEvent("FB_Messenger_Post");
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.SUBJECT", CommonHelper.shareName);
    localIntent.putExtra("android.intent.extra.TEXT", "I have sent you " + this.cat_first_char + str + Category_Name + " ecard." + "\n" + CommonHelper.shareUrl);
    localIntent.setType("text/plain");
    localIntent.setPackage("com.facebook.orca");
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException1)
    {
      try
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.facebook.orca")));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException2)
      {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.facebook.orca")));
      }
    }
  }
  
  public void setErrorMessage(boolean paramBoolean, String paramString)
  {
    EditText localEditText;
    if (paramBoolean)
    {
      localEditText = (EditText)this.rlViewandSend.findViewById(2131099831);
      localEditText.requestFocus();
    }
    for (this.tmpView = localEditText;; this.tmpView = localEditText)
    {
      if ((this.tmpView != null) && ((this.tmpView instanceof EditText))) {
        ((EditText)this.tmpView).setError(paramString);
      }
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if ((ViewAndSendActivity.this.tmpView != null) && ((ViewAndSendActivity.this.tmpView instanceof EditText)))
          {
            ((EditText)ViewAndSendActivity.this.tmpView).setError(null);
            return;
          }
          Log.d("removeError", "else");
        }
      }, 3000L);
      return;
      localEditText = (EditText)this.rlViewandSend.findViewById(2131099828);
      localEditText.requestFocus();
    }
  }
  
  public void shareOnFacebook()
  {
    Object localObject = Session.getActiveSession();
    if ((localObject != null) && (((Session)localObject).isOpened()))
    {
      this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline", Long.valueOf(0L));
      FlurryAgent.logEvent("Fb_Timeline");
      if (this.isMyWall)
      {
        shareInfo();
        return;
      }
      goToFacebookFriendsList();
      return;
    }
    localObject = new Session.OpenRequest(this);
    ((Session.OpenRequest)localObject).setLoginBehavior(SessionLoginBehavior.SSO_WITH_FALLBACK);
    ((Session.OpenRequest)localObject).setCallback(this.statusCallback);
    ((Session.OpenRequest)localObject).setPermissions(CommonHelper.getPermissions());
    Session localSession = new Session.Builder(this).build();
    Session.setActiveSession(localSession);
    localSession.openForPublish((Session.OpenRequest)localObject);
  }
  
  public void show_dfp_ad()
  {
    PublisherAdView localPublisherAdView = new PublisherAdView(this);
    localPublisherAdView.setAdUnitId(CommonHelper.publisherid);
    localPublisherAdView.setAdSizes(new com.google.android.gms.ads.AdSize[] { com.google.android.gms.ads.AdSize.SMART_BANNER });
    LinearLayout localLinearLayout = (LinearLayout)findViewById(2131099807);
    localLinearLayout.removeAllViews();
    localLinearLayout.addView(localPublisherAdView);
    localPublisherAdView.loadAd(new PublisherAdRequest.Builder().build());
  }
  
  private class DownloadImage
    extends AsyncTask<String, Void, Bitmap>
  {
    private DownloadImage() {}
    
    protected Bitmap doInBackground(String... paramVarArgs)
    {
      ViewAndSendActivity.this.imageURL = paramVarArgs[0];
      Log.e("image", ViewAndSendActivity.this.imageURL);
      return ImgUtils.getBitmap(ViewAndSendActivity.this.imageURL);
    }
    
    protected void onPostExecute(Bitmap paramBitmap)
    {
      ViewAndSendActivity.this.CardImage_ViewNSendScreen.setImageBitmap(paramBitmap);
      if ((ViewAndSendActivity.this.VIDEO_URL.equalsIgnoreCase("")) || (ViewAndSendActivity.this.VIDEO_URL == null)) {
        ViewAndSendActivity.this.VideoPlay_ViewNSendScreen.setVisibility(4);
      }
      for (;;)
      {
        ViewAndSendActivity.this.pbvideo.setVisibility(8);
        if (Constant.ReceiverDetails.RECEIVER_COUNT > 1) {
          ViewAndSendActivity.this.RemoveReceiver_ViewNSendScreen.setVisibility(0);
        }
        return;
        ViewAndSendActivity.this.VideoPlay_ViewNSendScreen.setVisibility(0);
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      ViewAndSendActivity.this.customDialogProgress = new Dialog(ViewAndSendActivity.this, 2131361798);
      ViewAndSendActivity.this.customDialogProgress.requestWindowFeature(1);
      ViewAndSendActivity.this.customDialogProgress.setContentView(2130903125);
      ViewAndSendActivity.this.customDialogProgress.setCancelable(false);
    }
  }
  
  public class FriendAdapter
    extends BaseAdapter
    implements SectionIndexer
  {
    private Activity activity;
    private ArrayList<HashMap<String, Object>> data = null;
    public ImageLoader imageLoader;
    private LayoutInflater inflater = null;
    
    public FriendAdapter(ArrayList<HashMap<String, Object>> paramArrayList)
    {
      this.activity = paramArrayList;
      Object localObject;
      this.data = localObject;
      this.inflater = ((LayoutInflater)this.activity.getSystemService("layout_inflater"));
      this.imageLoader = new ImageLoader(this.activity.getApplicationContext());
    }
    
    public int getCount()
    {
      return this.data.size();
    }
    
    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public int getPositionForSection(int paramInt)
    {
      int i = 0;
      for (;;)
      {
        int j;
        if (i >= this.data.size()) {
          j = -1;
        }
        do
        {
          return j;
          j = i;
        } while (((HashMap)this.data.get(i)).get("fullName").toString().toUpperCase().charAt(0) == paramInt);
        i += 1;
      }
    }
    
    public int getSectionForPosition(int paramInt)
    {
      return 0;
    }
    
    public Object[] getSections()
    {
      return null;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = paramView;
      HashMap localHashMap = (HashMap)this.data.get(paramInt);
      if (paramView == null)
      {
        paramViewGroup = this.inflater.inflate(2130903093, null);
        paramViewGroup.setTag(new Holder((ImageView)paramViewGroup.findViewById(2131099967), (TextView)paramViewGroup.findViewById(2131099976), null));
      }
      paramView = (Holder)paramViewGroup.getTag();
      paramView.contact_name.setText(localHashMap.get("fullName").toString());
      final String str = localHashMap.get("fbId").toString();
      if (!localHashMap.get("profilePic").toString().equals("")) {
        this.imageLoader.DisplayImage(localHashMap.get("profilePic").toString(), paramView.contact_image);
      }
      paramViewGroup.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ViewAndSendActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline", Long.valueOf(0L));
          FlurryAgent.logEvent("Fb_Timeline");
          ViewAndSendActivity.this.postToWall(str);
        }
      });
      return paramViewGroup;
    }
    
    private class Holder
    {
      public ImageView contact_image;
      public final TextView contact_name;
      
      private Holder(ImageView paramImageView, TextView paramTextView)
      {
        this.contact_name = paramTextView;
        this.contact_image = paramImageView;
      }
    }
  }
  
  private class GetCardDetailsAsync
    extends AsyncTask<String, Void, Void>
  {
    private GetCardDetailsAsync() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      try
      {
        
      }
      catch (Exception paramVarArgs)
      {
        try
        {
          for (;;)
          {
            paramVarArgs = "http://www.123greetings.com/mobile_api/get_card_details?devicetype=android&cardid=" + ViewAndSendActivity.CARD_ID;
            ViewAndSendActivity.response = new NetworkCalls(ViewAndSendActivity.this).downloadJesonFromUrl(paramVarArgs);
            ViewAndSendActivity.this.getCardDetailsArrList = JsonParser.parseGetCardDetailsJson(ViewAndSendActivity.response);
            return null;
            paramVarArgs = paramVarArgs;
            paramVarArgs.printStackTrace();
          }
        }
        catch (Exception paramVarArgs)
        {
          for (;;)
          {
            paramVarArgs.printStackTrace();
            ViewAndSendActivity.this.showToast(ViewAndSendActivity.this.getResources().getString(2131165268));
          }
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      ViewAndSendActivity.this.customDialogProgress.dismiss();
      Object localObject;
      int i;
      if (ViewAndSendActivity.this.getCardDetailsArrList.size() != 0)
      {
        new ShareHelper(ViewAndSendActivity.this, ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getSharetext(), ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getShareurl(), ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getShareimgurl(), ViewAndSendActivity.this.app, ViewAndSendActivity.this.menu, ViewAndSendActivity.this.slidingMenu);
        CommonHelper.shareImage = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getShareimgurl();
        CommonHelper.shareText = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getSharetext();
        CommonHelper.shareUrl = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getShareurl();
        CommonHelper.shareName = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getCardtitle();
        ViewAndSendActivity.this.editor.putString(CommonHelper.SHAREIMAGE, CommonHelper.shareImage);
        ViewAndSendActivity.this.editor.commit();
        paramVoid = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getCardlargeimage();
        ViewAndSendActivity.this.startDateForCalender = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getSenddatestart();
        com.g123.util.Constant.API_DATE = ViewAndSendActivity.this.startDateForCalender;
        ViewAndSendActivity.IMAGE_URL = paramVoid;
        ViewAndSendActivity.this.country = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getCountry();
        localObject = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getCardtitle();
        CommonHelper.card_title_for_fb = (String)localObject;
        CommonHelper.card_image_url_fb = ViewAndSendActivity.IMAGE_URL;
        CommonHelper.card_url_for_sms = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getCardmoviepreviewurl();
        ViewAndSendActivity.this.CardTitle_ViewNSendScreen.setText(Html.fromHtml((String)localObject));
        ViewAndSendActivity.this.VIDEO_URL = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getCardmoviepreviewurl();
        ViewAndSendActivity.this.ScrollView_ViewNSendScreen.setVisibility(0);
        ViewAndSendActivity.this.DeliveryDate_ViewNSendScreen.setText(((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getSenddatestart());
        CommonHelper.send_someoneelse_cur_date = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getSenddatestart();
        ViewAndSendActivity.this.server_date = ((GetCardDetailsDataClass)ViewAndSendActivity.this.getCardDetailsArrList.get(0)).getSenddatestart();
        ViewAndSendActivity.this.DeliveryDate_ViewNSendScreen_show_value.setText("Today");
        int j = CountryList.countryName.length;
        Constant.CountryListUtils.COUNTRY_NAMES.clear();
        Constant.CountryListUtils.COUNTRY_ID.clear();
        i = 0;
        if (i >= j)
        {
          ViewAndSendActivity.this.CardImage_ViewNSendScreen.setImageResource(2130837602);
          new ViewAndSendActivity.DownloadImage(ViewAndSendActivity.this, null).execute(new String[] { paramVoid });
          label549:
          ViewAndSendActivity.response = "";
          Log.d("end", "2");
          if (ViewAndSendActivity.this.getIntent().getExtras().containsKey("bdayvalues"))
          {
            paramVoid = Calendar.getInstance();
            if (ViewAndSendActivity.this.bday_anniv_remain_day <= 60)
            {
              paramVoid.add(5, ViewAndSendActivity.this.bday_anniv_remain_day);
              paramVoid = new SimpleDateFormat("yyyy-MM-dd").format(paramVoid.getTime());
              ViewAndSendActivity.this.DeliveryDate_ViewNSendScreen.setText(paramVoid);
              if (!paramVoid.equals(ViewAndSendActivity.this.server_date)) {
                break label839;
              }
              ViewAndSendActivity.this.DeliveryDate_ViewNSendScreen_show_value.setText("Today");
            }
          }
        }
      }
      for (;;)
      {
        if (ViewAndSendActivity.this.bday_anniv_remain_day > 0)
        {
          if (ViewAndSendActivity.this.bday_anniv_remain_day <= 60)
          {
            ViewAndSendActivity.this.CountryListFrame.setVisibility(0);
            paramVoid = "";
            if (Constant.CountryListUtils.COUNTRY_ID_SELECTED.equalsIgnoreCase("")) {
              paramVoid = ViewAndSendActivity.this.country;
            }
            i = Constant.CountryListUtils.COUNTRY_ID.indexOf(paramVoid.toLowerCase(Locale.US));
            if ((i != -1) && (i < Constant.CountryListUtils.COUNTRY_NAMES.size())) {
              ViewAndSendActivity.this.spinner_SetCountry.setText((CharSequence)Constant.CountryListUtils.COUNTRY_NAMES.get(i));
            }
            return;
            localObject = CountryList.countryName[i].split(";");
            Constant.CountryListUtils.COUNTRY_NAMES.add(localObject[0]);
            Constant.CountryListUtils.COUNTRY_ID.add(localObject[1]);
            i += 1;
            break;
            ViewAndSendActivity.this.showToast(ViewAndSendActivity.this.getResources().getString(2131165268));
            break label549;
            label839:
            ViewAndSendActivity.this.DeliveryDate_ViewNSendScreen_show_value.setText(paramVoid);
            continue;
          }
          ViewAndSendActivity.this.CountryListFrame.setVisibility(8);
          Constant.CountryListUtils.COUNTRY_ID_SELECTED = "";
          return;
        }
      }
      ViewAndSendActivity.this.CountryListFrame.setVisibility(8);
      Constant.CountryListUtils.COUNTRY_ID_SELECTED = "";
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      try
      {
        ViewAndSendActivity.this.customDialogProgress = new Dialog(ViewAndSendActivity.this, 2131361798);
        ViewAndSendActivity.this.customDialogProgress.requestWindowFeature(1);
        ViewAndSendActivity.this.customDialogProgress.setContentView(2130903125);
        ViewAndSendActivity.this.customDialogProgress.setCancelable(false);
        ViewAndSendActivity.this.customDialogProgress.show();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  class ImportContactsTask
    extends AsyncTask<String, Void, Void>
  {
    Dialog customDialogProgress;
    
    ImportContactsTask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      getProfileInformation();
      return null;
    }
    
    public String getJson(String paramString)
      throws ClientProtocolException, IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      paramString = HttpClientFactory.getThreadSafeClient().execute(new HttpGet(paramString)).getEntity().getContent();
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramString));
      for (;;)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
        {
          localBufferedReader.close();
          paramString.close();
          return localStringBuilder.toString();
        }
        localStringBuilder.append(str);
      }
    }
    
    /* Error */
    public void getProfileInformation()
    {
      // Byte code:
      //   0: new 40	java/lang/StringBuilder
      //   3: dup
      //   4: ldc 102
      //   6: invokespecial 103	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   9: aload_0
      //   10: getfield 18	com/g123/activity/ViewAndSendActivity$ImportContactsTask:this$0	Lcom/g123/activity/ViewAndSendActivity;
      //   13: invokestatic 107	com/g123/activity/ViewAndSendActivity:access$35	(Lcom/g123/activity/ViewAndSendActivity;)Lcom/facebook/android/Facebook;
      //   16: invokevirtual 112	com/facebook/android/Facebook:getAccessToken	()Ljava/lang/String;
      //   19: invokestatic 117	java/net/URLEncoder:encode	(Ljava/lang/String;)Ljava/lang/String;
      //   22: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   25: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   28: astore_3
      //   29: new 119	com/g123/activity/helper/NetworkCalls
      //   32: dup
      //   33: aload_0
      //   34: getfield 18	com/g123/activity/ViewAndSendActivity$ImportContactsTask:this$0	Lcom/g123/activity/ViewAndSendActivity;
      //   37: invokespecial 122	com/g123/activity/helper/NetworkCalls:<init>	(Landroid/app/Activity;)V
      //   40: invokevirtual 126	com/g123/activity/helper/NetworkCalls:isNetworkAvalable	()Z
      //   43: ifeq +184 -> 227
      //   46: new 128	org/json/JSONObject
      //   49: dup
      //   50: aload_0
      //   51: aload_3
      //   52: invokevirtual 130	com/g123/activity/ViewAndSendActivity$ImportContactsTask:getJson	(Ljava/lang/String;)Ljava/lang/String;
      //   55: invokespecial 131	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   58: astore 6
      //   60: aconst_null
      //   61: astore_3
      //   62: aconst_null
      //   63: astore 5
      //   65: aload 6
      //   67: ldc -123
      //   69: invokevirtual 137	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   72: astore 4
      //   74: aload 4
      //   76: astore_3
      //   77: aload 4
      //   79: invokevirtual 143	org/json/JSONArray:length	()I
      //   82: istore_1
      //   83: aload 5
      //   85: astore_3
      //   86: iconst_0
      //   87: istore_2
      //   88: iload_2
      //   89: iload_1
      //   90: if_icmplt +44 -> 134
      //   93: aload_0
      //   94: getfield 18	com/g123/activity/ViewAndSendActivity$ImportContactsTask:this$0	Lcom/g123/activity/ViewAndSendActivity;
      //   97: getfield 147	com/g123/activity/ViewAndSendActivity:all_contacts_fb	Ljava/util/ArrayList;
      //   100: new 10	com/g123/activity/ViewAndSendActivity$ImportContactsTask$1
      //   103: dup
      //   104: aload_0
      //   105: invokespecial 150	com/g123/activity/ViewAndSendActivity$ImportContactsTask$1:<init>	(Lcom/g123/activity/ViewAndSendActivity$ImportContactsTask;)V
      //   108: invokestatic 156	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
      //   111: return
      //   112: astore 4
      //   114: aload 6
      //   116: ldc -123
      //   118: invokevirtual 160	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   121: astore 5
      //   123: iconst_1
      //   124: istore_1
      //   125: aload_3
      //   126: astore 4
      //   128: aload 5
      //   130: astore_3
      //   131: goto -45 -> 86
      //   134: iload_1
      //   135: iconst_1
      //   136: if_icmple +10 -> 146
      //   139: aload 4
      //   141: iload_2
      //   142: invokevirtual 163	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
      //   145: astore_3
      //   146: new 165	java/util/HashMap
      //   149: dup
      //   150: invokespecial 166	java/util/HashMap:<init>	()V
      //   153: astore 5
      //   155: aload 5
      //   157: ldc -88
      //   159: aload_3
      //   160: ldc -86
      //   162: invokevirtual 173	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   165: invokevirtual 177	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   168: pop
      //   169: aload 5
      //   171: ldc -77
      //   173: aload_3
      //   174: ldc -75
      //   176: invokevirtual 173	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   179: invokevirtual 177	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   182: pop
      //   183: aload 5
      //   185: ldc -73
      //   187: aload_3
      //   188: ldc -71
      //   190: invokevirtual 160	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   193: ldc -123
      //   195: invokevirtual 160	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   198: ldc -69
      //   200: invokevirtual 173	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   203: invokevirtual 177	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   206: pop
      //   207: aload_0
      //   208: getfield 18	com/g123/activity/ViewAndSendActivity$ImportContactsTask:this$0	Lcom/g123/activity/ViewAndSendActivity;
      //   211: getfield 147	com/g123/activity/ViewAndSendActivity:all_contacts_fb	Ljava/util/ArrayList;
      //   214: aload 5
      //   216: invokevirtual 193	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   219: pop
      //   220: iload_2
      //   221: iconst_1
      //   222: iadd
      //   223: istore_2
      //   224: goto -136 -> 88
      //   227: aload_0
      //   228: getfield 18	com/g123/activity/ViewAndSendActivity$ImportContactsTask:this$0	Lcom/g123/activity/ViewAndSendActivity;
      //   231: invokevirtual 197	com/g123/activity/ViewAndSendActivity:getApplicationContext	()Landroid/content/Context;
      //   234: ldc -58
      //   236: iconst_1
      //   237: invokestatic 204	android/widget/Toast:makeText	(Landroid/content/Context;II)Landroid/widget/Toast;
      //   240: invokevirtual 207	android/widget/Toast:show	()V
      //   243: return
      //   244: astore_3
      //   245: aload_3
      //   246: invokevirtual 210	java/lang/Exception:printStackTrace	()V
      //   249: aload_0
      //   250: getfield 18	com/g123/activity/ViewAndSendActivity$ImportContactsTask:this$0	Lcom/g123/activity/ViewAndSendActivity;
      //   253: invokevirtual 197	com/g123/activity/ViewAndSendActivity:getApplicationContext	()Landroid/content/Context;
      //   256: ldc -58
      //   258: iconst_1
      //   259: invokestatic 204	android/widget/Toast:makeText	(Landroid/content/Context;II)Landroid/widget/Toast;
      //   262: invokevirtual 207	android/widget/Toast:show	()V
      //   265: return
      //   266: astore 6
      //   268: goto -61 -> 207
      //   271: astore 6
      //   273: goto -90 -> 183
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	276	0	this	ImportContactsTask
      //   82	55	1	i	int
      //   87	137	2	j	int
      //   28	160	3	localObject1	Object
      //   244	2	3	localException1	Exception
      //   72	6	4	localJSONArray	org.json.JSONArray
      //   112	1	4	localException2	Exception
      //   126	14	4	localObject2	Object
      //   63	152	5	localObject3	Object
      //   58	57	6	localJSONObject	org.json.JSONObject
      //   266	1	6	localException3	Exception
      //   271	1	6	localException4	Exception
      // Exception table:
      //   from	to	target	type
      //   65	74	112	java/lang/Exception
      //   77	83	112	java/lang/Exception
      //   29	60	244	java/lang/Exception
      //   93	111	244	java/lang/Exception
      //   114	123	244	java/lang/Exception
      //   139	146	244	java/lang/Exception
      //   146	169	244	java/lang/Exception
      //   207	220	244	java/lang/Exception
      //   227	243	244	java/lang/Exception
      //   183	207	266	java/lang/Exception
      //   169	183	271	java/lang/Exception
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      if ((this.customDialogProgress != null) && (this.customDialogProgress.isShowing())) {
        this.customDialogProgress.dismiss();
      }
      ViewAndSendActivity.this.friendPickerdialogshow();
    }
    
    protected void onPreExecute()
    {
      this.customDialogProgress = new Dialog(ViewAndSendActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
    }
  }
  
  private class SendAsyncTask
    extends AsyncTask<String, Void, Void>
  {
    private SendAsyncTask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      try
      {
        
        try
        {
          paramVarArgs = ViewAndSendActivity.this.ll_NotifyMe_ViewNSendScreen.getTag().toString();
          if (Constant.CountryListUtils.COUNTRY_ID_SELECTED.equalsIgnoreCase("")) {
            Constant.CountryListUtils.COUNTRY_ID_SELECTED = ViewAndSendActivity.this.country;
          }
          ViewAndSendActivity.response = ConnectionForPostData.postJson(ViewAndSendActivity.CARD_ID, ViewAndSendActivity.this.DeliveryDate_ViewNSendScreen.getText().toString(), paramVarArgs, Constant.CountryListUtils.COUNTRY_ID_SELECTED, ViewAndSendActivity.this.SenderName_ViewNSendScreen.getText().toString(), ViewAndSendActivity.this.SenderEmail_ViewNSendScreen.getText().toString(), ViewAndSendActivity.this.Message_ViewNSendScreen.getText().toString(), ViewAndSendActivity.receiverDetailsArrList, "http://www.123greetings.com/mobile_api/thank?devicetype=android");
        }
        catch (Exception paramVarArgs)
        {
          for (;;)
          {
            paramVarArgs.printStackTrace();
            ViewAndSendActivity.this.showToast(ViewAndSendActivity.this.getResources().getString(2131165268));
          }
        }
        return null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      ViewAndSendActivity.this.getCardSendDetailsArrList = JsonParser.parseGetCardSendDetailsJson(ViewAndSendActivity.response);
      paramVoid = ((GetCardSendDetailsDataClass)ViewAndSendActivity.this.getCardSendDetailsArrList.get(0)).getError();
      String str1 = ((GetCardSendDetailsDataClass)ViewAndSendActivity.this.getCardSendDetailsArrList.get(0)).getDisplay_message();
      if (paramVoid.equalsIgnoreCase("Yes"))
      {
        ViewAndSendActivity.this.showToast(str1);
        ViewAndSendActivity.receiverDetailsArrList.clear();
        ViewAndSendActivity.response_send_error = Boolean.valueOf(true);
        ViewAndSendActivity.this.customDialogProgress.dismiss();
        ViewAndSendActivity.response = "";
        return;
      }
      int j = ViewAndSendActivity.receiverDetailsArrList.size();
      ViewAndSendActivity.receiverDetailsArrList_thanks.clear();
      int i = 0;
      label117:
      String[] arrayOfString;
      String str2;
      if (i >= ViewAndSendActivity.receiverDetailsArrList.size())
      {
        Constant.ReceiverDetails.RECEIVER_COUNT = 1;
        arrayOfString = (String[])ViewAndSendActivity.receiverDetailsArrList.get(0);
        ViewAndSendActivity.receiverDetailsArrList.clear();
        Constant.CountryListUtils.COUNTRY_ID_SELECTED = "";
        str2 = ((GetCardSendDetailsDataClass)ViewAndSendActivity.this.getCardSendDetailsArrList.get(0)).getCardurl();
        if (j <= 1) {
          break label497;
        }
      }
      label497:
      for (paramVoid = arrayOfString[0].trim() + " and Others";; paramVoid = arrayOfString[0].trim())
      {
        Intent localIntent = new Intent(ViewAndSendActivity.this, ThanksActivity.class);
        localIntent.putExtra("DisplayMsg", str1);
        localIntent.putExtra("cardUrl", str2);
        localIntent.putExtra("senderNameForThanks", ViewAndSendActivity.this.SenderName_ViewNSendScreen.getText().toString().trim());
        localIntent.putExtra("senderEmailForThanks", ViewAndSendActivity.this.SenderEmail_ViewNSendScreen.getText().toString().trim());
        localIntent.putExtra("receiverNameForThanks", paramVoid);
        localIntent.putExtra("receiverEmailForThanks", arrayOfString[1].trim());
        localIntent.putExtra("cardImgUrlForThanks", ViewAndSendActivity.IMAGE_URL);
        localIntent.putExtra("cardIdForThanks", ViewAndSendActivity.CARD_ID);
        localIntent.putExtra("CALENDER_STARTDATE", ViewAndSendActivity.this.startDateForCalender);
        localIntent.putExtra("title", ViewAndSendActivity.this.CardTitle_ViewNSendScreen.getText().toString());
        localIntent.putExtra("send_type", "email_");
        CommonHelper.shareImage = ((GetCardSendDetailsDataClass)ViewAndSendActivity.this.getCardSendDetailsArrList.get(0)).getShareimgurl();
        CommonHelper.shareText = ((GetCardSendDetailsDataClass)ViewAndSendActivity.this.getCardSendDetailsArrList.get(0)).getSharetext();
        CommonHelper.shareUrl = ((GetCardSendDetailsDataClass)ViewAndSendActivity.this.getCardSendDetailsArrList.get(0)).getShareurl();
        ViewAndSendActivity.this.startActivity(localIntent);
        ViewAndSendActivity.this.finish();
        Constant.ThanksUtils.WISH_NOW = false;
        break;
        paramVoid = (String[])ViewAndSendActivity.receiverDetailsArrList.get(i);
        ViewAndSendActivity.receiverDetailsArrList_thanks.add(paramVoid);
        i += 1;
        break label117;
      }
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      ViewAndSendActivity.this.customDialogProgress = new Dialog(ViewAndSendActivity.this, 2131361798);
      ViewAndSendActivity.this.customDialogProgress.requestWindowFeature(1);
      ViewAndSendActivity.this.customDialogProgress.setContentView(2130903125);
      ViewAndSendActivity.this.customDialogProgress.setCancelable(false);
      ViewAndSendActivity.this.customDialogProgress.show();
    }
  }
  
  private class SessionStatusCallback
    implements Session.StatusCallback
  {
    private SessionStatusCallback() {}
    
    public void call(Session paramSession, SessionState paramSessionState, Exception paramException)
    {
      if (paramSession.isOpened())
      {
        Log.e("Login : ", "Success");
        if (ViewAndSendActivity.this.isMyWall) {
          ViewAndSendActivity.this.shareInfo();
        }
      }
      else
      {
        return;
      }
      ViewAndSendActivity.this.goToFacebookFriendsList();
    }
  }
  
  class TrackSentTask
    extends AsyncTask<String, Void, Void>
  {
    Dialog customDialogProgress;
    String track_DateTime = "";
    String track_ErrorDescription = "";
    String track_ShareResult = "";
    String track_ShareType = "";
    String track_cardid = "";
    
    TrackSentTask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      this.track_cardid = paramVarArgs[0].trim();
      this.track_ShareType = paramVarArgs[1].trim();
      this.track_ShareResult = paramVarArgs[2].trim();
      this.track_ErrorDescription = paramVarArgs[3].trim();
      this.track_DateTime = paramVarArgs[4].trim();
      paramVarArgs = "http://www.123greetings.com/mobile_api/track_sent?cardid=" + this.track_cardid + "&ShareType=" + this.track_ShareType + "&ShareResult=" + this.track_ShareResult + "&ErrorDescription=" + this.track_ErrorDescription + "&DateTime=" + URLEncoder.encode(this.track_DateTime);
      new NetworkCalls(ViewAndSendActivity.this).downloadJesonFromUrl(paramVarArgs);
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((this.customDialogProgress != null) && (this.customDialogProgress.isShowing())) {
        this.customDialogProgress.dismiss();
      }
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      this.customDialogProgress = new Dialog(ViewAndSendActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
    }
  }
}
