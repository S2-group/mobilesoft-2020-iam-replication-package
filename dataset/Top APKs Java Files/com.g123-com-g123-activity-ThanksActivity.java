package com.g123.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
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
import com.facebook.widget.WebDialog.RequestsDialogBuilder;
import com.flurry.android.FlurryAgent;
import com.g123.activity.helper.CategoryKeeperClass;
import com.g123.activity.helper.CommonHelper;
import com.g123.activity.helper.EventsDataClass;
import com.g123.activity.helper.GetCardDetailsDataClass;
import com.g123.activity.helper.GetCardSendDetailsDataClass;
import com.g123.activity.helper.HandleClickEventsForMenu;
import com.g123.activity.helper.HandlingClickEvents;
import com.g123.activity.helper.ImageLoader;
import com.g123.activity.helper.JsonParser;
import com.g123.activity.helper.NetworkCalls;
import com.g123.activity.helper.ShareHelper;
import com.g123.activity.helper.SideMenuHelper;
import com.g123.activity.helper.URLStoregeClass;
import com.g123.adapter.SideBar;
import com.g123.adapter.ThanksDynamicViewAdapter;
import com.g123.adapter.ThanksDynamicViewAdapter.GetClickedEditText;
import com.g123.adapter.ThanksDynamicViewAdapter.SendWishNow;
import com.g123.calendar.CalendarView;
import com.g123.util.Alert;
import com.g123.util.Constant;
import com.g123.util.Constant.CountryListUtils;
import com.g123.util.Constant.ThanksUtils;
import com.g123.util.HttpClientFactory;
import com.g123.webservice.ConnectionForPostData;
import com.g123.widget.ViewFlow;
import com.google.analytics.tracking.android.Tracker;
import com.google.android.gms.plus.PlusShare.Builder;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThanksActivity
  extends BaseActivity
  implements View.OnClickListener
{
  public static final String APP_ID = "6268317308";
  private static String CARD_URL = "";
  private static final String[] PERMISSIONS = { "public_profile", "email", "user_friends", "user_birthday" };
  private static final int REQ_START_SHARE = 22;
  static final int SMS_REQ_CODE = 1234;
  public static String dateFive;
  public static boolean dateFiveSelect;
  public static String dateFour;
  public static boolean dateFourSelect;
  public static String dateOne;
  public static boolean dateOneSelect;
  public static String dateThree;
  public static boolean dateThreeSelect;
  public static String dateTwo;
  public static boolean dateTwoSelect;
  public static ScrollView mScrollView;
  public static int row = 0;
  public static EditText tempEditText;
  String StartDate = "";
  ArrayList<HashMap<String, Object>> all_contacts_fb = new ArrayList();
  View app;
  ArrayList<EventsDataClass> arrayListEventsDataClasses = new ArrayList();
  Button btn_cancel;
  Button btn_search;
  String cardId = "";
  String cardImageUrl = "";
  int card_count = 0;
  String cat_first_char = "";
  CategoryKeeperClass categoryKeeperClass;
  String category_name = "";
  boolean clickable = true;
  Dialog customDialogProgress;
  String displayMsg = "";
  TextView displayTextMsg;
  EditText edit_search;
  private SharedPreferences.Editor editor;
  ArrayList<GetCardSendDetailsDataClass> getCardSendDetailsArrList;
  HandleClickEventsForMenu handleClickEventsForMenu;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      ThanksActivity.this.customDialogProgress.dismiss();
      ThanksActivity.mScrollView.setVisibility(0);
      if (ThanksActivity.this.arrayListEventsDataClasses.size() > 0) {
        ThanksActivity.this.addDynamicViewsForThanks();
      }
      ThanksActivity.mScrollView.smoothScrollTo(0, 75);
    }
  };
  HandlingClickEvents handlingClickEvents;
  String imgUrl_wishnow = "";
  Intent intent;
  RelativeLayout layout_to_move;
  LinearLayout ll_dynamicview_container;
  private AsyncFacebookRunner mAsyncRunner;
  private Facebook mFacebook;
  View menu;
  View menu_right;
  private boolean open = false;
  private SharedPreferences pref;
  ArrayList<String[]> receiverDetailsArrList = new ArrayList();
  String receiverEmail = "";
  String receiverName = "";
  String response = "";
  RelativeLayout rlthanks;
  RelativeLayout rlviewcard;
  Button sendAnother;
  public Handler sendHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      ThanksActivity.this.customDialogProgress.dismiss();
      ThanksActivity.this.receiverDetailsArrList.clear();
      ThanksActivity.this.getCardSendDetailsArrList = JsonParser.parseGetCardSendDetailsJson(ThanksActivity.this.response);
      paramAnonymousMessage = ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getError();
      ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getDisplay_message();
      if (paramAnonymousMessage.equalsIgnoreCase("Yes")) {}
      for (;;)
      {
        try
        {
          paramAnonymousMessage = new JSONObject(ThanksActivity.this.response).getJSONArray("errorfields");
          paramAnonymousMessage.length();
          if (paramAnonymousMessage.getJSONObject(0).has("senderemail"))
          {
            Alert.display(ThanksActivity.this, "Sender email is invalid!");
            Constant.ThanksUtils.DELIVERY_DATE = "";
            ThanksActivity.this.response = "";
            return;
          }
          Alert.display(ThanksActivity.this, "Receiver email is invalid!");
          continue;
        }
        catch (JSONException paramAnonymousMessage)
        {
          paramAnonymousMessage.printStackTrace();
          continue;
        }
        paramAnonymousMessage = ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getCardurl();
        ThanksActivity.CARD_URL = paramAnonymousMessage;
        ThanksActivity.this.cardImageUrl = paramAnonymousMessage;
        Constant.ThanksUtils.CARD_ID = ThanksActivity.this.tempCardID;
        ThanksActivity.this.displayTextMsg.setText("Thank you for sending an ecard to " + ThanksActivity.this.receiverName + "!");
        ThanksActivity.mScrollView.smoothScrollTo(0, 0);
        CommonHelper.catname = ThanksActivity.this.category_name;
        CommonHelper.shareImage = ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getShareimgurl();
        CommonHelper.shareText = ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getSharetext();
        CommonHelper.shareUrl = ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getShareurl();
        CommonHelper.shareName = ((GetCardSendDetailsDataClass)ThanksActivity.this.getCardSendDetailsArrList.get(0)).getDisplay_message();
        CommonHelper.shareName = CommonHelper.shareText;
        CommonHelper.SHAREIMAGE = CommonHelper.shareImage;
        ThanksActivity.this.dowloadJsonForLatestEvents();
      }
    }
  };
  String send_type = "";
  String senderEmail = "";
  String senderName = "";
  StartCalender startCalender;
  String startDateForCalender = "";
  String tempCardID = "";
  public String thanks_sendviasms_cardid = "";
  RelativeLayout thanku;
  TextView thnkutext;
  String title = "";
  View view4;
  View view5;
  Button viewEcard;
  ViewFlow viewFlow = null;
  boolean view_not_change = true;
  TextView viewecardtext;
  RelativeLayout viewurcard;
  
  static
  {
    dateOne = "";
    dateTwo = "";
    dateThree = "";
    dateFour = "";
    dateFive = "";
    dateOneSelect = false;
    dateTwoSelect = false;
    dateThreeSelect = false;
    dateFourSelect = false;
    dateFiveSelect = false;
  }
  
  public ThanksActivity() {}
  
  private void addDynamicViewsForThanks()
  {
    if (this.ll_dynamicview_container.getChildCount() > 0) {
      this.ll_dynamicview_container.removeAllViews();
    }
    dateOne = Constant.API_DATE;
    dateTwo = Constant.API_DATE;
    dateThree = Constant.API_DATE;
    dateFour = Constant.API_DATE;
    dateFive = Constant.API_DATE;
    dateOneSelect = false;
    dateTwoSelect = false;
    dateThreeSelect = false;
    dateFourSelect = false;
    dateFiveSelect = false;
    LayoutInflater localLayoutInflater = (LayoutInflater)getSystemService("layout_inflater");
    ViewGroup localViewGroup = (ViewGroup)findViewById(2131099805);
    int i = 0;
    for (;;)
    {
      if (i >= this.arrayListEventsDataClasses.size())
      {
        if (this.category_name.length() <= 0) {
          break label361;
        }
        i = this.category_name.charAt(0);
        if ((i != 97) && (i != 65) && (i != 101) && (i != 69) && (i != 105) && (i != 73) && (i != 111) && (i != 79) && (i != 117) && (i != 85)) {
          break;
        }
        this.cat_first_char = "an";
        return;
      }
      localLayoutInflater.inflate(2130903104, localViewGroup);
      this.viewFlow = ((ViewFlow)localViewGroup.getChildAt(i).findViewById(2131100005));
      this.category_name = ((EventsDataClass)this.arrayListEventsDataClasses.get(i)).getEventwodate();
      ThanksDynamicViewAdapter localThanksDynamicViewAdapter = new ThanksDynamicViewAdapter(this, ((EventsDataClass)this.arrayListEventsDataClasses.get(i)).getArrayList(), ((EventsDataClass)this.arrayListEventsDataClasses.get(i)).getSenddatestart(), ((EventsDataClass)this.arrayListEventsDataClasses.get(i)).getEventid(), ((EventsDataClass)this.arrayListEventsDataClasses.get(i)).getEventtitle(), ((EventsDataClass)this.arrayListEventsDataClasses.get(i)).getSenddateend(), this.receiverName, this.receiverEmail, "", i, this.startCalender, this.StartDate, this.send_type);
      this.viewFlow.setAdapter(localThanksDynamicViewAdapter);
      i += 1;
    }
    this.cat_first_char = "a";
    return;
    label361:
    this.cat_first_char = "an";
  }
  
  private void dowloadJsonForLatestEvents()
  {
    this.customDialogProgress = new Dialog(this, 2131361798);
    this.customDialogProgress.requestWindowFeature(1);
    this.customDialogProgress.setContentView(2130903125);
    this.customDialogProgress.setCancelable(false);
    this.customDialogProgress.show();
    try
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            
          }
          catch (Exception localException1)
          {
            for (;;)
            {
              try
              {
                if (ThanksActivity.this.view_not_change)
                {
                  ThanksActivity.this.response = new NetworkCalls(ThanksActivity.this).downloadJesonFromUrl(URLStoregeClass.GET_LATEST_EVENTS);
                  String str = ThanksActivity.this.response;
                  ThanksActivity.this.arrayListEventsDataClasses.clear();
                  ThanksActivity.this.arrayListEventsDataClasses = new JsonParser().parseEventData(ThanksActivity.this.response);
                }
                ThanksActivity.this.handler.sendEmptyMessage(0);
                return;
              }
              catch (Exception localException2)
              {
                localException2.printStackTrace();
                ThanksActivity.this.showToast(ThanksActivity.this.getResources().getString(2131165268));
              }
              localException1 = localException1;
              localException1.printStackTrace();
            }
          }
        }
      }).start();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      showToast(getResources().getString(2131165268));
    }
  }
  
  private void getViews()
  {
    this.displayTextMsg = ((TextView)findViewById(2131099802));
    String str;
    if (this.send_type.equals("fb_"))
    {
      this.viewurcard.setVisibility(8);
      this.displayTextMsg.setText("Thank you for sending an ecard.");
      if (CommonHelper.track_fbismywall)
      {
        str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        new TrackSentTask().execute(new String[] { this.cardId, "Facebook_My_Timeline", "Success", "", str });
      }
    }
    for (;;)
    {
      this.viewEcard = ((Button)findViewById(2131099803));
      if ((this.send_type.equals("sms_")) || (this.send_type.equals("fb_"))) {
        this.viewEcard.setVisibility(4);
      }
      this.viewEcard.setOnClickListener(this);
      this.sendAnother = ((Button)findViewById(2131099804));
      this.sendAnother.setOnClickListener(this);
      com.g123.util.Constant.DateUtils.CURRENT_DEFERRED_DATETYPE = "current";
      return;
      if (CommonHelper.track_gplus)
      {
        CommonHelper.track_gplus = false;
        str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        new TrackSentTask().execute(new String[] { this.cardId, "Google_Plus_Post", "Success", "", str });
      }
      else
      {
        str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        new TrackSentTask().execute(new String[] { this.cardId, "Facebook_My_Friends_Timeline", "Success", "", str });
        continue;
        if (this.send_type.equals("sms_"))
        {
          this.viewurcard.setVisibility(8);
          this.displayTextMsg.setText("Thank you for sending an ecard.");
          str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
          new TrackSentTask().execute(new String[] { this.cardId, "SMS", "Success", "", str });
        }
        else
        {
          this.displayTextMsg.setText("Thank you for sending an ecard to " + this.receiverName + "! ");
        }
      }
    }
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
  
  public void initialize()
  {
    CommonHelper.LAST_CATEGORY = -1;
    this.pref = getApplicationContext().getSharedPreferences("com.g123", 0);
    this.editor = this.pref.edit();
    this.card_count = this.pref.getInt("card_count", 0);
    this.rlthanks = ((RelativeLayout)inflater.inflate(2130903055, null));
    this.rlviewcard = ((RelativeLayout)inflater.inflate(2130903057, null));
    ((RelativeLayout)findViewById(2131100137)).setVisibility(8);
    ((LinearLayout)findViewById(2131100146)).setVisibility(0);
    this.thanku = ((RelativeLayout)findViewById(2131100147));
    this.viewurcard = ((RelativeLayout)findViewById(2131100150));
    this.thnkutext = ((TextView)findViewById(2131100148));
    this.viewecardtext = ((TextView)findViewById(2131100151));
    this.thnkutext.setTypeface(null, 1);
    this.viewecardtext.setTypeface(null, 0);
    this.thanku.setOnClickListener(this);
    this.viewurcard.setOnClickListener(this);
    this.view4 = findViewById(2131100149);
    this.view5 = findViewById(2131100152);
    this.slidingMenu.setTouchModeAbove(0);
    this.slidingMenu.setOnOpenListener(new SlidingMenu.OnOpenListener()
    {
      public void onOpen() {}
    });
    this.slidingMenu.setSecondaryOnOpenListner(new SlidingMenu.OnOpenListener()
    {
      public void onOpen() {}
    });
    this.handlingClickEvents = new HandlingClickEvents(this);
    new ShareHelper(this, CommonHelper.shareText, CommonHelper.shareUrl, CommonHelper.shareImage, this.app, this.menu, this.slidingMenu);
    this.menu_right = findViewById(2131099694);
    this.handleClickEventsForMenu = new HandleClickEventsForMenu(this, this.app, this.menu, this.slidingMenu);
    this.view_not_change = true;
    this.intent = getIntent();
    CARD_URL = this.intent.getStringExtra("cardUrl");
    this.thanku.performClick();
    this.card_count += 1;
    if (this.pref.getString("already_rated", "no").equals("no"))
    {
      if (this.card_count == 1)
      {
        this.editor.putInt("card_count", 0);
        this.editor.commit();
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, 3);
        localBuilder.setTitle("Rate 123Greetings App");
        localBuilder.setMessage("If you have enjoyed using the 123Greetings App, would you mind taking a minute to rate it?  Thanks for your support!");
        localBuilder.setPositiveButton("Later", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
        });
        localBuilder.setNeutralButton("Rate App", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            try
            {
              ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.g123")));
              return;
            }
            catch (ActivityNotFoundException paramAnonymousDialogInterface)
            {
              ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.g123&hl=en")));
            }
          }
        });
        localBuilder.setNegativeButton("Already Rated", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            ThanksActivity.this.editor.putString("already_rated", "yes");
            ThanksActivity.this.editor.commit();
          }
        });
        localBuilder.show();
      }
    }
    else {
      return;
    }
    this.editor.putInt("card_count", this.card_count);
    this.editor.commit();
  }
  
  public void inviteFriendsViaFB()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("message", "Use 123Greetings eCards");
    localBundle.putString("title", "Invite Friends");
    ((WebDialog.RequestsDialogBuilder)new WebDialog.RequestsDialogBuilder(this, this.mFacebook.getSession(), localBundle).setOnCompleteListener(new WebDialog.OnCompleteListener()
    {
      public void onComplete(Bundle paramAnonymousBundle, FacebookException paramAnonymousFacebookException)
      {
        if (paramAnonymousFacebookException != null)
        {
          if ((paramAnonymousFacebookException instanceof FacebookOperationCanceledException))
          {
            Toast.makeText(ThanksActivity.this.getApplicationContext(), "Request cancelled", 0).show();
            return;
          }
          Toast.makeText(ThanksActivity.this.getApplicationContext(), "Network Error", 0).show();
          return;
        }
        if (paramAnonymousBundle.getString("request") != null)
        {
          Toast.makeText(ThanksActivity.this.getApplicationContext(), "Request sent", 0).show();
          return;
        }
        Toast.makeText(ThanksActivity.this.getApplicationContext(), "Request cancelled", 0).show();
      }
    })).build().show();
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
  
  public void loginToFacebook(final String paramString)
  {
    this.mFacebook = new Facebook("6268317308");
    this.mAsyncRunner = new AsyncFacebookRunner(this.mFacebook);
    if (new NetworkCalls(this).isNetworkAvalable())
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
      String str = localSharedPreferences.getString("access_token", null);
      long l = localSharedPreferences.getLong("access_expires", 0L);
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
            ThanksActivity.this.editor.putString("access_token", ThanksActivity.this.mFacebook.getAccessToken());
            ThanksActivity.this.editor.putLong("access_expires", ThanksActivity.this.mFacebook.getAccessExpires());
            ThanksActivity.this.editor.commit();
            if (paramString.equals("Invite_hit"))
            {
              ThanksActivity.this.inviteFriendsViaFB();
              return;
            }
            if (ThanksActivity.this.all_contacts_fb.size() > 0)
            {
              ThanksActivity.this.friendPickerdialogshow();
              return;
            }
            new ThanksActivity.ImportContactsTask(ThanksActivity.this).execute(new String[0]);
          }
          
          public void onError(DialogError paramAnonymousDialogError)
          {
            System.out.println("fbLogin onerror");
            Toast.makeText(ThanksActivity.this.getApplicationContext(), "FB Login Error", 1).show();
          }
          
          public void onFacebookError(FacebookError paramAnonymousFacebookError)
          {
            System.out.println("fbLogin onfberror" + paramAnonymousFacebookError);
            Toast.makeText(ThanksActivity.this.getApplicationContext(), "On FB Error", 1).show();
          }
        });
        return;
      }
      if (paramString.equals("Invite_hit"))
      {
        inviteFriendsViaFB();
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
  
  public void maintainClickableState()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ThanksActivity.this.clickable = true;
      }
    }, 500L);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1234)
    {
      this.tracker.sendEvent("ui-action", "button_press", "Sms_Send", Long.valueOf(0L));
      FlurryAgent.logEvent("Sms_Send");
      paramIntent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      new TrackSentTask().execute(new String[] { this.thanks_sendviasms_cardid, "SMS", "Success", "", paramIntent });
    }
    do
    {
      do
      {
        return;
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
            ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Send", Long.valueOf(0L));
            FlurryAgent.logEvent("Fb_Timeline_Send");
            ThanksActivity.this.showToastGreen(CommonHelper.facebookShareSuccess);
            paramAnonymousPendingCall = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            new ThanksActivity.TrackSentTask(ThanksActivity.this).execute(new String[] { ThanksActivity.this.cardId, "Facebook_My_Timeline", "Success", "", paramAnonymousPendingCall });
            return;
          }
          System.out.println("Facebook unsuccess");
          ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
          FlurryAgent.logEvent("Fb_Timeline_Cancel");
          ThanksActivity.this.showToast(CommonHelper.facebookShareFail);
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
    if (paramInt1 == 22)
    {
      paramIntent = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
      if (paramInt2 == -1)
      {
        CommonHelper.catname = this.category_name;
        this.tracker.sendEvent("ui-action", "button_press", "Google_Plus_Post", Long.valueOf(0L));
        FlurryAgent.logEvent("Google_Plus_Post");
        paramIntent = CommonHelper.card_url_for_sms;
        Intent localIntent = new Intent(this, ThanksActivity.class);
        localIntent.putExtra("DisplayMsg", "Your ecard has been posted successfully!");
        localIntent.putExtra("cardUrl", paramIntent);
        localIntent.putExtra("senderNameForThanks", "");
        localIntent.putExtra("senderEmailForThanks", "");
        localIntent.putExtra("receiverNameForThanks", "");
        localIntent.putExtra("receiverEmailForThanks", "");
        localIntent.putExtra("cardImgUrlForThanks", CommonHelper.card_image_url_fb);
        localIntent.putExtra("cardIdForThanks", this.cardId);
        localIntent.putExtra("CALENDER_STARTDATE", this.startDateForCalender);
        localIntent.putExtra("title", CommonHelper.shareName);
        localIntent.putExtra("send_type", "fb_");
        CommonHelper.track_gplus = true;
        startActivity(localIntent);
        finish();
        Constant.ThanksUtils.WISH_NOW = false;
        return;
      }
      new TrackSentTask().execute(new String[] { this.cardId, "Google_Plus_Post", "Failure", "", paramIntent });
      return;
    }
    this.mFacebook.authorizeCallback(paramInt1, paramInt2, paramIntent);
  }
  
  public void onBackPressed()
  {
    super.onBackPressed();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      if (paramView == this.btn_cancel) {
        this.edit_search.setText("");
      }
      break;
    }
    label454:
    label589:
    do
    {
      int i;
      do
      {
        do
        {
          return;
          this.tracker.sendEvent("ui-action", "button_press", "Thank you page  Tap view your ecard", Long.valueOf(0L));
          FlurryAgent.logEvent("Thank you page  Tap view your ecard");
          localObject = new Intent(this, ViewEcardActivity.class);
          ((Intent)localObject).putExtra("cardUrl_ViewCardScreen", CARD_URL);
          startActivity((Intent)localObject);
          break;
          this.tracker.sendEvent("ui-action", "button_press", "Thank you page Tap Send this card to someone else", Long.valueOf(0L));
          FlurryAgent.logEvent("Thank you page Tap Send this card to someone else");
          Constant.ThanksUtils.SENDTOANOTHER = true;
          if (!Constant.ThanksUtils.WISH_NOW) {
            Constant.ThanksUtils.CARD_ID = this.cardId;
          }
          CommonHelper.thanks_activity_calling = getLocalClassName();
          localObject = new Intent(this, ViewAndSendActivity.class);
          ((Intent)localObject).putExtra("senderName_VnS", this.senderName);
          ((Intent)localObject).putExtra("senderEmail_VnS", this.senderEmail);
          ((Intent)localObject).putExtra("cardImgUrl_VnS", this.cardImageUrl);
          ((Intent)localObject).putExtra("title", this.title);
          ((Intent)localObject).putExtra("Cat_name", CommonHelper.catname);
          startActivity((Intent)localObject);
          finish();
          break;
          if (paramView != this.btn_search) {
            break label454;
          }
          this.tracker.sendEvent("ui-action", "button_press", "Thank you page Tap Go", Long.valueOf(0L));
        } while (this.edit_search.getText().toString().trim().length() <= 0);
        paramView = new Intent(this, CategoryActivityNew.class);
        paramView.putExtra("CategoryId", this.edit_search.getText().toString());
        paramView.putExtra("URL", "http://www.123greetings.com/mobile_api/search_subcats?devicetype=android");
        paramView.putExtra("SEARCH", true);
        paramView.putExtra("MENU_OPTION", false);
        this.edit_search.setText("");
        Object localObject = CommonHelper.getInstance();
        j = ((CommonHelper)localObject).getActivityStack().size();
        i = 0;
        for (;;)
        {
          if (i >= j)
          {
            startActivity(paramView);
            return;
          }
          Activity localActivity = (Activity)((CommonHelper)localObject).getActivityStack().get(((CommonHelper)localObject).getActivityStack().size() - 1);
          if (!(localActivity instanceof HomeActivity))
          {
            ((CommonHelper)localObject).getActivityStack().remove(((CommonHelper)localObject).getActivityStack().size() - 1);
            localActivity.finish();
          }
          i += 1;
        }
        if (paramView != this.img_logo) {
          break label589;
        }
        this.tracker.sendEvent("ui-action", "button_press", "Thank you page Tap on logo", Long.valueOf(0L));
      } while (!CommonHelper.imglogoClick);
      CommonHelper.imglogoClick = false;
      paramView = CommonHelper.getInstance();
      int j = paramView.getActivityStack().size();
      if (j > 0) {
        i = 0;
      }
      for (;;)
      {
        if (i >= j)
        {
          startActivity(new Intent("com.app.greetings123.activity.GreetingsTabActivity"));
          finish();
          Runtime.getRuntime().gc();
          CommonHelper.maintainClickableStateForImagelogoclick();
          return;
        }
        ((Activity)paramView.getActivityStack().get(paramView.getActivityStack().size() - 1)).finish();
        paramView.getActivityStack().remove(paramView.getActivityStack().size() - 1);
        i += 1;
      }
      if (paramView == this.thanku)
      {
        this.llbasemiddle.removeAllViews();
        this.thnkutext.setTypeface(null, 1);
        this.viewecardtext.setTypeface(null, 0);
        this.view4.setVisibility(0);
        this.view5.setVisibility(4);
        thankYouWork();
        loadAdd();
        return;
      }
    } while (paramView != this.viewurcard);
    this.view_not_change = false;
    this.thnkutext.setTypeface(null, 0);
    this.viewecardtext.setTypeface(null, 1);
    this.llbasemiddle.removeAllViews();
    this.view4.setVisibility(4);
    this.view5.setVisibility(0);
    viewYourCard();
    loadAdd();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    CommonHelper localCommonHelper = CommonHelper.getInstance();
    int j = localCommonHelper.getActivityStack().size();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return;
      }
      if (((Activity)localCommonHelper.getActivityStack().get(localCommonHelper.getActivityStack().size() - 1) instanceof ThanksActivity)) {
        localCommonHelper.getActivityStack().remove(localCommonHelper.getActivityStack().size() - 1);
      }
      i += 1;
    }
  }
  
  protected void onPause()
  {
    super.onPause();
    this.uiHelper.onPause();
    stopADRefreshTask();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.tracker.sendView("Thank Screen");
    FlurryAgent.logEvent("Thank Screen");
    this.edit_search.setEnabled(true);
    CommonHelper.menuOut = false;
    CommonHelper.menuOutRight = false;
    if ((CommonHelper.arrEditTextContainer.size() > 0) && (!Constant.ThanksUtils.DELIVERY_DATE.equalsIgnoreCase("")))
    {
      if (!Constant.ThanksUtils.DELIVERY_DATE.equalsIgnoreCase("previous")) {
        break label99;
      }
      showToast(getResources().getString(2131165270));
    }
    for (;;)
    {
      CommonHelper.arrEditTextContainer.remove(0);
      CommonHelper.arrEditTextContainer_show_value.remove(0);
      return;
      label99:
      if (Constant.ThanksUtils.DELIVERY_DATE.equalsIgnoreCase("exceeded"))
      {
        showToast(getResources().getString(2131165269));
      }
      else
      {
        ((EditText)CommonHelper.arrEditTextContainer.get(0)).setText(Constant.ThanksUtils.DELIVERY_DATE);
        if (Constant.ThanksUtils.DELIVERY_DATE.equals(Constant.API_DATE)) {
          ((EditText)CommonHelper.arrEditTextContainer_show_value.get(0)).setText("Today");
        }
        for (;;)
        {
          if (!((EditText)CommonHelper.arrEditTextContainer.get(0)).getTag().equals(Integer.valueOf(0))) {
            break label227;
          }
          dateOne = Constant.ThanksUtils.DELIVERY_DATE;
          dateOneSelect = true;
          break;
          ((EditText)CommonHelper.arrEditTextContainer_show_value.get(0)).setText(Constant.ThanksUtils.DELIVERY_DATE);
        }
        label227:
        if (((EditText)CommonHelper.arrEditTextContainer.get(0)).getTag().equals(Integer.valueOf(1)))
        {
          dateTwo = Constant.ThanksUtils.DELIVERY_DATE;
          dateTwoSelect = true;
        }
        else if (((EditText)CommonHelper.arrEditTextContainer.get(0)).getTag().equals(Integer.valueOf(2)))
        {
          dateThree = Constant.ThanksUtils.DELIVERY_DATE;
          dateThreeSelect = true;
        }
        else if (((EditText)CommonHelper.arrEditTextContainer.get(0)).getTag().equals(Integer.valueOf(3)))
        {
          dateFour = Constant.ThanksUtils.DELIVERY_DATE;
          dateFourSelect = true;
        }
        else if (((EditText)CommonHelper.arrEditTextContainer.get(0)).getTag().equals(Integer.valueOf(4)))
        {
          dateFive = Constant.ThanksUtils.DELIVERY_DATE;
          dateFiveSelect = true;
        }
      }
    }
  }
  
  public void postToGplus(String paramString)
  {
    paramString = "";
    if (this.category_name.length() > 0) {
      paramString = " ";
    }
    startActivityForResult(new PlusShare.Builder(this).setType("text/plain").setText("I have sent you " + this.cat_first_char + paramString + this.category_name + " ecard.\nClick the link below to view the ecard.\n" + CommonHelper.shareUrl + "\n\nvia 123Greetings App.").setContentUrl(Uri.parse(CommonHelper.shareUrl)).getIntent(), 22);
  }
  
  public void postToWall(String paramString)
  {
    if (new NetworkCalls(this).isNetworkAvalable())
    {
      String str = "";
      if (this.category_name.length() > 0) {
        str = " ";
      }
      Bundle localBundle = new Bundle();
      localBundle.putString("app_id", "6268317308");
      localBundle.putString("to", paramString);
      localBundle.putString("name", "I have sent you " + this.cat_first_char + str + this.category_name + " ecard.");
      localBundle.putString("caption", "www.123greetings.com");
      localBundle.putString("description", CommonHelper.shareName);
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
              CommonHelper.catname = ThanksActivity.this.category_name;
              ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Send", Long.valueOf(0L));
              FlurryAgent.logEvent("Fb_Timeline_Send");
              paramAnonymousBundle = CommonHelper.card_url_for_sms;
              paramAnonymousFacebookException = new Intent(ThanksActivity.this, ThanksActivity.class);
              paramAnonymousFacebookException.putExtra("DisplayMsg", "Your ecard has been posted successfully!");
              paramAnonymousFacebookException.putExtra("cardUrl", paramAnonymousBundle);
              paramAnonymousFacebookException.putExtra("senderNameForThanks", "");
              paramAnonymousFacebookException.putExtra("senderEmailForThanks", "");
              paramAnonymousFacebookException.putExtra("receiverNameForThanks", "");
              paramAnonymousFacebookException.putExtra("receiverEmailForThanks", "");
              paramAnonymousFacebookException.putExtra("cardImgUrlForThanks", CommonHelper.card_image_url_fb);
              paramAnonymousFacebookException.putExtra("cardIdForThanks", ThanksActivity.this.cardId);
              paramAnonymousFacebookException.putExtra("CALENDER_STARTDATE", ThanksActivity.this.startDateForCalender);
              paramAnonymousFacebookException.putExtra("title", CommonHelper.shareName);
              paramAnonymousFacebookException.putExtra("send_type", "fb_");
              CommonHelper.track_fbismywall = false;
              ThanksActivity.this.startActivity(paramAnonymousFacebookException);
              ThanksActivity.this.finish();
              Constant.ThanksUtils.WISH_NOW = false;
              return;
            }
            Toast.makeText(ThanksActivity.this.getApplicationContext(), "Publish cancelled", 0).show();
            ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
            FlurryAgent.logEvent("Fb_Timeline_Cancel");
            return;
          }
          if ((paramAnonymousFacebookException instanceof FacebookOperationCanceledException))
          {
            Toast.makeText(ThanksActivity.this.getApplicationContext(), "Publish cancelled", 0).show();
            ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
            FlurryAgent.logEvent("Fb_Timeline_Cancel");
            return;
          }
          Toast.makeText(ThanksActivity.this.getApplicationContext(), "Error posting story", 0).show();
          ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline_Cancel", Long.valueOf(0L));
          FlurryAgent.logEvent("Fb_Timeline_Cancel");
          paramAnonymousBundle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
          new ThanksActivity.TrackSentTask(ThanksActivity.this).execute(new String[] { ThanksActivity.this.cardId, "Facebook_My_Friends_Timeline", "Failure", "Error posting story", paramAnonymousBundle });
        }
      })).build().show();
      return;
    }
    Toast.makeText(getApplicationContext(), 2131165268, 1).show();
  }
  
  public void sendFB(String paramString)
  {
    try
    {
      new FBDtask().execute(new String[] { paramString });
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      showToast(getResources().getString(2131165268));
    }
  }
  
  public void sendGplus(String paramString)
  {
    try
    {
      new Gtask().execute(new String[] { paramString });
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      showToast(getResources().getString(2131165268));
    }
  }
  
  public void sendLinkedIn(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      new Dtask().execute(new String[] { paramString1, paramString2, paramString3 });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      showToast(getResources().getString(2131165268));
    }
  }
  
  public void sendMore(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      new Dtask().execute(new String[] { paramString1, paramString2, paramString3 });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      showToast(getResources().getString(2131165268));
    }
  }
  
  public void sendSMS(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      new Dtask().execute(new String[] { paramString1, paramString2, paramString3 });
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
      showToast(getResources().getString(2131165268));
    }
  }
  
  public void sendWishNowCards(final String paramString1, final String paramString2, final String paramString3, String paramString4, String paramString5)
  {
    this.customDialogProgress = new Dialog(this, 2131361798);
    this.customDialogProgress.requestWindowFeature(1);
    this.customDialogProgress.setContentView(2130903125);
    this.customDialogProgress.setCancelable(false);
    this.customDialogProgress.show();
    this.receiverDetailsArrList.add(new String[] { paramString4, paramString5 });
    new Thread()
    {
      public void run()
      {
        try
        {
          
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            try
            {
              ThanksActivity.this.view_not_change = true;
              Object localObject = (TelephonyManager)ThanksActivity.this.getSystemService("phone");
              localObject = Constant.CountryListUtils.COUNTRY_ID_SELECTED;
              ThanksActivity.this.response = ConnectionForPostData.postJson(paramString1, paramString2, "true", (String)localObject, ThanksActivity.this.senderName, ThanksActivity.this.senderEmail, paramString3, ViewAndSendActivity.receiverDetailsArrList_thanks, "http://www.123greetings.com/mobile_api/thank?devicetype=android");
              localObject = ThanksActivity.this.response;
              ThanksActivity.this.sendHandler.sendEmptyMessage(0);
              return;
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
              ThanksActivity.this.showToast(ThanksActivity.this.getResources().getString(2131165268));
            }
            localException1 = localException1;
            localException1.printStackTrace();
          }
        }
      }
    }.start();
  }
  
  public void startActivityFromintent()
  {
    Intent localIntent = new Intent(this, CalendarView.class);
    localIntent.putExtra("PST_date", ((EditText)CommonHelper.arrEditTextContainer.get(0)).getText().toString());
    localIntent.putExtra("server_date", Constant.API_DATE);
    startActivity(localIntent);
  }
  
  public void thankYouWork()
  {
    this.llbasemiddle.addView(this.rlthanks);
    this.layout_to_move = ((RelativeLayout)this.rlthanks.findViewById(2131099695));
    this.layout_to_move.bringToFront();
    if (CommonHelper.bd != null) {
      this.layout_to_move.setBackgroundDrawable(CommonHelper.bd);
    }
    this.startCalender = new StartCalender();
    this.displayMsg = this.intent.getStringExtra("DisplayMsg");
    this.senderName = this.intent.getStringExtra("senderNameForThanks");
    this.senderEmail = this.intent.getStringExtra("senderEmailForThanks");
    this.cardImageUrl = this.intent.getStringExtra("cardImgUrlForThanks");
    this.cardId = this.intent.getStringExtra("cardIdForThanks");
    this.receiverName = this.intent.getStringExtra("receiverNameForThanks");
    this.receiverEmail = this.intent.getStringExtra("receiverEmailForThanks");
    this.title = this.intent.getStringExtra("title");
    this.send_type = this.intent.getStringExtra("send_type");
    this.ll_dynamicview_container = ((LinearLayout)this.rlthanks.findViewById(2131099805));
    this.StartDate = this.intent.getStringExtra("CALENDER_STARTDATE");
    getViews();
    this.edit_search = ((EditText)this.rlthanks.findViewById(2131099704));
    this.edit_search.setHint(Html.fromHtml("<small>Search ecards</small>"));
    try
    {
      this.categoryKeeperClass = new CategoryKeeperClass(this, this.handlingClickEvents);
      this.layout_to_move = ((RelativeLayout)this.rlthanks.findViewById(2131099695));
      mScrollView = (ScrollView)this.rlthanks.findViewById(2131099801);
      mScrollView.setVisibility(8);
      this.app = this.rlthanks.findViewById(2131099695);
      this.menu = this.rlthanks.findViewById(2131099693);
      this.btn_search = ((Button)this.rlthanks.findViewById(2131099701));
      this.btn_cancel = ((Button)this.rlthanks.findViewById(2131099702));
      this.btn_search.setOnClickListener(this);
      this.btn_cancel.setOnClickListener(this);
      new SideMenuHelper(this.handleClickEventsForMenu, this);
      this.edit_search.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable) {}
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
        {
          ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Enter search text", Long.valueOf(0L));
          if (ThanksActivity.this.edit_search.getText().toString().length() > 0)
          {
            ThanksActivity.this.btn_cancel.setVisibility(0);
            return;
          }
          ThanksActivity.this.btn_cancel.setVisibility(4);
        }
      });
      this.edit_search.setOnEditorActionListener(new TextView.OnEditorActionListener()
      {
        public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Thanks you page   Tap Go", Long.valueOf(0L));
          if (paramAnonymousInt == 3)
          {
            int i;
            if (ThanksActivity.this.edit_search.getText().toString().trim().length() > 0)
            {
              paramAnonymousTextView = new Intent(ThanksActivity.this, CategoryActivityNew.class);
              paramAnonymousTextView.putExtra("CategoryId", ThanksActivity.this.edit_search.getText().toString());
              paramAnonymousTextView.putExtra("URL", "http://www.123greetings.com/mobile_api/search_subcats?devicetype=android");
              paramAnonymousTextView.putExtra("SEARCH", true);
              paramAnonymousTextView.putExtra("MENU_OPTION", false);
              ThanksActivity.this.edit_search.setText("");
              paramAnonymousKeyEvent = CommonHelper.getInstance();
              i = paramAnonymousKeyEvent.getActivityStack().size();
              paramAnonymousInt = 0;
            }
            for (;;)
            {
              if (paramAnonymousInt >= i)
              {
                ThanksActivity.this.startActivity(paramAnonymousTextView);
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
      dowloadJsonForLatestEvents();
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void viewYourCard()
  {
    this.tracker.sendEvent("ui-action", "button_press", "Thank you page  Tap view your ecard", Long.valueOf(0L));
    FlurryAgent.logEvent("Thank you page  Tap view your ecard");
    CommonHelper.LAST_CATEGORY = -1;
    this.llbasemiddle.addView(this.rlviewcard);
    this.customDialogProgress = new Dialog(this, 2131361798);
    this.customDialogProgress.requestWindowFeature(1);
    this.customDialogProgress.setContentView(2130903125);
    this.customDialogProgress.setCancelable(true);
    this.customDialogProgress.show();
    WebView localWebView = (WebView)this.rlviewcard.findViewById(2131099849);
    localWebView.loadUrl("javascript:document.open();document.close();");
    localWebView.setWebViewClient(new WebViewClient()
    {
      public void onLoadResource(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.clearHistory();
        super.onLoadResource(paramAnonymousWebView, paramAnonymousString);
      }
      
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        paramAnonymousWebView.clearHistory();
        if ((ThanksActivity.this.customDialogProgress.isShowing()) && (ThanksActivity.this.customDialogProgress != null)) {
          ThanksActivity.this.customDialogProgress.dismiss();
        }
      }
    });
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.loadUrl(CARD_URL);
  }
  
  class Dtask
    extends AsyncTask<String, Void, Void>
  {
    String card_desc_asnch = "";
    String card_send_type = "";
    String cardid_asnch = "";
    Dialog customDialogProgress;
    ArrayList<GetCardDetailsDataClass> getCardDetailsArrList;
    String thanks_sms_shareimage = "";
    String thanks_sms_sharename = "";
    String thanks_sms_sharetxt = "";
    String thanks_sms_shareurl = "";
    
    Dtask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      this.cardid_asnch = paramVarArgs[0].trim();
      this.card_desc_asnch = paramVarArgs[1].trim();
      this.card_send_type = paramVarArgs[2].trim();
      paramVarArgs = "http://www.123greetings.com/mobile_api/get_card_details?devicetype=android&cardid=" + this.cardid_asnch;
      this.getCardDetailsArrList = JsonParser.parseGetCardDetailsJson(new NetworkCalls(ThanksActivity.this).downloadJesonFromUrl(paramVarArgs));
      ThanksActivity.this.thanks_sendviasms_cardid = this.cardid_asnch;
      this.thanks_sms_sharetxt = ((GetCardDetailsDataClass)this.getCardDetailsArrList.get(0)).getSharetext();
      this.thanks_sms_shareurl = ((GetCardDetailsDataClass)this.getCardDetailsArrList.get(0)).getShareurl();
      this.thanks_sms_sharename = ((GetCardDetailsDataClass)this.getCardDetailsArrList.get(0)).getCardtitle();
      this.thanks_sms_shareimage = ((GetCardDetailsDataClass)this.getCardDetailsArrList.get(0)).getShareimgurl();
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((this.customDialogProgress != null) && (this.customDialogProgress.isShowing())) {
        this.customDialogProgress.dismiss();
      }
      String str1 = "";
      if (ThanksActivity.this.category_name.length() > 0) {
        str1 = " ";
      }
      Intent localIntent2;
      if (this.card_send_type.equals("send_sms"))
      {
        ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Sms_Tab", Long.valueOf(0L));
        FlurryAgent.logEvent("Sms_Tab");
        if (this.getCardDetailsArrList.size() != 0)
        {
          localIntent2 = new Intent("android.intent.action.VIEW");
          localIntent2.setData(Uri.parse("smsto:"));
          localIntent2.putExtra("sms_body", "I have sent you " + ThanksActivity.this.cat_first_char + str1 + ThanksActivity.this.category_name + " ecard." + "\n\n" + this.thanks_sms_shareurl + "\n\n\nvia 123Greetings App");
        }
      }
      for (;;)
      {
        try
        {
          ThanksActivity.this.startActivityForResult(localIntent2, 1234);
          Log.i("Finished sending SMS...", "");
          super.onPostExecute(paramVoid);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException1)
        {
          Toast.makeText(ThanksActivity.this, "SMS faild, please try again later.", 0).show();
          continue;
        }
        if (this.card_send_type.equals("send_whatsapp"))
        {
          ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "thankyou_whatsapp_tab", Long.valueOf(0L));
          FlurryAgent.logEvent("thankyou_whatsapp_tab");
          if (this.getCardDetailsArrList.size() != 0) {
            if (ThanksActivity.this.isPackageExists("com.whatsapp"))
            {
              ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Whatsapp_Send_Thanks", Long.valueOf(0L));
              FlurryAgent.logEvent("Whatsapp_Tab");
              localIntent2 = new Intent("android.intent.action.SEND");
              localIntent2.setType("text/plain");
              String str2 = "I have sent you " + ThanksActivity.this.cat_first_char + localActivityNotFoundException1 + ThanksActivity.this.category_name + " ecard.\nClick the link below to view the ecard.\n" + this.thanks_sms_shareurl + "\n\nvia 123Greetings App.";
              localIntent2.setPackage("com.whatsapp");
              if (localIntent2 != null)
              {
                localIntent2.putExtra("android.intent.extra.TEXT", str2);
                ThanksActivity.this.startActivity(Intent.createChooser(localIntent2, "Share with"));
              }
            }
            else
            {
              try
              {
                ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.whatsapp")));
              }
              catch (ActivityNotFoundException localActivityNotFoundException2)
              {
                ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
              }
            }
          }
        }
        else
        {
          Intent localIntent1;
          if (this.card_send_type.equals("send_more"))
          {
            ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "thankyou_more_tab", Long.valueOf(0L));
            FlurryAgent.logEvent("thankyou_more_tab");
            if (this.getCardDetailsArrList.size() != 0)
            {
              localIntent1 = new Intent(ThanksActivity.this, MoreActivity.class);
              localIntent1.putExtra("Cat_name", ThanksActivity.this.category_name);
              localIntent1.putExtra("Shareurl", this.thanks_sms_shareurl);
              ThanksActivity.this.startActivity(localIntent1);
            }
          }
          else if (this.card_send_type.equals("send_messenger"))
          {
            ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "thankyou_messenger_tab", Long.valueOf(0L));
            FlurryAgent.logEvent("thankyou_messenger_tab");
            if (this.getCardDetailsArrList.size() != 0)
            {
              localIntent2 = new Intent();
              localIntent2.setAction("android.intent.action.SEND");
              localIntent2.putExtra("android.intent.extra.SUBJECT", this.thanks_sms_sharename);
              localIntent2.putExtra("android.intent.extra.TEXT", "I have sent you " + ThanksActivity.this.cat_first_char + localIntent1 + ThanksActivity.this.category_name + " ecard." + "\n" + this.thanks_sms_shareurl);
              localIntent2.setType("text/plain");
              localIntent2.setPackage("com.facebook.orca");
              try
              {
                ThanksActivity.this.startActivity(localIntent2);
              }
              catch (ActivityNotFoundException localActivityNotFoundException3)
              {
                try
                {
                  ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.facebook.orca")));
                }
                catch (ActivityNotFoundException localActivityNotFoundException4)
                {
                  ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.facebook.orca")));
                }
              }
            }
          }
          else if (this.card_send_type.equals("send_linkedin"))
          {
            ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "thankyou_line_tab", Long.valueOf(0L));
            FlurryAgent.logEvent("thankyou_line_tab");
            try
            {
              localIntent2 = new Intent("android.intent.action.SEND");
              localIntent2.setType("text/plain");
              localIntent2.putExtra("android.intent.extra.SUBJECT", "123greetings ecard");
              localIntent2.putExtra("android.intent.extra.TEXT", "I have sent you " + ThanksActivity.this.cat_first_char + localActivityNotFoundException4 + ThanksActivity.this.category_name + " ecard.\nClick the link below to view the ecard.\n" + this.thanks_sms_shareurl + "\n\nvia 123Greetings App.");
              localIntent2.setPackage("jp.naver.line.android");
              if (localIntent2 != null) {
                ThanksActivity.this.startActivity(localIntent2);
              }
            }
            catch (ActivityNotFoundException localActivityNotFoundException5)
            {
              try
              {
                ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=jp.naver.line.android")));
              }
              catch (ActivityNotFoundException localActivityNotFoundException6)
              {
                ThanksActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=jp.naver.line.android")));
              }
            }
          }
        }
      }
    }
    
    protected void onPreExecute()
    {
      this.customDialogProgress = new Dialog(ThanksActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
    }
  }
  
  class FBDtask
    extends AsyncTask<String, Void, Void>
  {
    String cardid_asnch = "";
    Dialog customDialogProgress;
    
    FBDtask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      this.cardid_asnch = paramVarArgs[0].trim();
      try
      {
        paramVarArgs = "http://www.123greetings.com/mobile_api/get_card_details?devicetype=android&cardid=" + this.cardid_asnch;
        paramVarArgs = JsonParser.parseGetCardDetailsJson(new NetworkCalls(ThanksActivity.this).downloadJesonFromUrl(paramVarArgs));
        CommonHelper.shareImage = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getShareimgurl();
        CommonHelper.shareText = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getSharetext();
        CommonHelper.shareUrl = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getShareurl();
        CommonHelper.shareName = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardtitle();
        CommonHelper.card_url_for_sms = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardmoviepreviewurl();
        CommonHelper.card_image_url_fb = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardlargeimage();
        CommonHelper.SHAREIMAGE = CommonHelper.shareImage;
        ThanksActivity.this.imgUrl_wishnow = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardlargeimage();
        ThanksActivity.this.cardImageUrl = ThanksActivity.this.imgUrl_wishnow;
        ThanksActivity.this.cardId = this.cardid_asnch;
        ThanksActivity.this.startDateForCalender = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getSenddatestart();
        return null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
          ThanksActivity.this.showToast(ThanksActivity.this.getResources().getString(2131165268));
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((this.customDialogProgress != null) && (this.customDialogProgress.isShowing())) {
        this.customDialogProgress.dismiss();
      }
      ThanksActivity.this.loginToFacebook(this.cardid_asnch);
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      this.customDialogProgress = new Dialog(ThanksActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
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
          ThanksActivity.this.tracker.sendEvent("ui-action", "button_press", "Fb_Timeline", Long.valueOf(0L));
          FlurryAgent.logEvent("Fb_Timeline");
          ThanksActivity.this.postToWall(str);
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
  
  class Gtask
    extends AsyncTask<String, Void, Void>
  {
    String cardid_asnch = "";
    Dialog customDialogProgress;
    
    Gtask() {}
    
    protected Void doInBackground(String... paramVarArgs)
    {
      this.cardid_asnch = paramVarArgs[0].trim();
      try
      {
        paramVarArgs = "http://www.123greetings.com/mobile_api/get_card_details?devicetype=android&cardid=" + this.cardid_asnch;
        paramVarArgs = JsonParser.parseGetCardDetailsJson(new NetworkCalls(ThanksActivity.this).downloadJesonFromUrl(paramVarArgs));
        CommonHelper.shareImage = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getShareimgurl();
        CommonHelper.shareText = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getSharetext();
        CommonHelper.shareUrl = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getShareurl();
        CommonHelper.shareName = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardtitle();
        CommonHelper.card_url_for_sms = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardmoviepreviewurl();
        CommonHelper.card_image_url_fb = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardlargeimage();
        CommonHelper.SHAREIMAGE = CommonHelper.shareImage;
        ThanksActivity.this.imgUrl_wishnow = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getCardlargeimage();
        ThanksActivity.this.cardImageUrl = ThanksActivity.this.imgUrl_wishnow;
        ThanksActivity.this.cardId = this.cardid_asnch;
        ThanksActivity.this.startDateForCalender = ((GetCardDetailsDataClass)paramVarArgs.get(0)).getSenddatestart();
        return null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          paramVarArgs.printStackTrace();
          ThanksActivity.this.showToast(ThanksActivity.this.getResources().getString(2131165268));
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      if ((this.customDialogProgress != null) && (this.customDialogProgress.isShowing())) {
        this.customDialogProgress.dismiss();
      }
      ThanksActivity.this.postToGplus(this.cardid_asnch);
      super.onPostExecute(paramVoid);
    }
    
    protected void onPreExecute()
    {
      this.customDialogProgress = new Dialog(ThanksActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
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
      //   10: getfield 18	com/g123/activity/ThanksActivity$ImportContactsTask:this$0	Lcom/g123/activity/ThanksActivity;
      //   13: invokestatic 107	com/g123/activity/ThanksActivity:access$3	(Lcom/g123/activity/ThanksActivity;)Lcom/facebook/android/Facebook;
      //   16: invokevirtual 112	com/facebook/android/Facebook:getAccessToken	()Ljava/lang/String;
      //   19: invokestatic 117	java/net/URLEncoder:encode	(Ljava/lang/String;)Ljava/lang/String;
      //   22: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   25: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   28: astore_3
      //   29: new 119	com/g123/activity/helper/NetworkCalls
      //   32: dup
      //   33: aload_0
      //   34: getfield 18	com/g123/activity/ThanksActivity$ImportContactsTask:this$0	Lcom/g123/activity/ThanksActivity;
      //   37: invokespecial 122	com/g123/activity/helper/NetworkCalls:<init>	(Landroid/app/Activity;)V
      //   40: invokevirtual 126	com/g123/activity/helper/NetworkCalls:isNetworkAvalable	()Z
      //   43: ifeq +184 -> 227
      //   46: new 128	org/json/JSONObject
      //   49: dup
      //   50: aload_0
      //   51: aload_3
      //   52: invokevirtual 130	com/g123/activity/ThanksActivity$ImportContactsTask:getJson	(Ljava/lang/String;)Ljava/lang/String;
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
      //   94: getfield 18	com/g123/activity/ThanksActivity$ImportContactsTask:this$0	Lcom/g123/activity/ThanksActivity;
      //   97: getfield 147	com/g123/activity/ThanksActivity:all_contacts_fb	Ljava/util/ArrayList;
      //   100: new 10	com/g123/activity/ThanksActivity$ImportContactsTask$1
      //   103: dup
      //   104: aload_0
      //   105: invokespecial 150	com/g123/activity/ThanksActivity$ImportContactsTask$1:<init>	(Lcom/g123/activity/ThanksActivity$ImportContactsTask;)V
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
      //   208: getfield 18	com/g123/activity/ThanksActivity$ImportContactsTask:this$0	Lcom/g123/activity/ThanksActivity;
      //   211: getfield 147	com/g123/activity/ThanksActivity:all_contacts_fb	Ljava/util/ArrayList;
      //   214: aload 5
      //   216: invokevirtual 193	java/util/ArrayList:add	(Ljava/lang/Object;)Z
      //   219: pop
      //   220: iload_2
      //   221: iconst_1
      //   222: iadd
      //   223: istore_2
      //   224: goto -136 -> 88
      //   227: aload_0
      //   228: getfield 18	com/g123/activity/ThanksActivity$ImportContactsTask:this$0	Lcom/g123/activity/ThanksActivity;
      //   231: invokevirtual 197	com/g123/activity/ThanksActivity:getApplicationContext	()Landroid/content/Context;
      //   234: ldc -58
      //   236: iconst_1
      //   237: invokestatic 204	android/widget/Toast:makeText	(Landroid/content/Context;II)Landroid/widget/Toast;
      //   240: invokevirtual 207	android/widget/Toast:show	()V
      //   243: return
      //   244: astore_3
      //   245: aload_3
      //   246: invokevirtual 210	java/lang/Exception:printStackTrace	()V
      //   249: aload_0
      //   250: getfield 18	com/g123/activity/ThanksActivity$ImportContactsTask:this$0	Lcom/g123/activity/ThanksActivity;
      //   253: invokevirtual 197	com/g123/activity/ThanksActivity:getApplicationContext	()Landroid/content/Context;
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
      //   72	6	4	localJSONArray	JSONArray
      //   112	1	4	localException2	Exception
      //   126	14	4	localObject2	Object
      //   63	152	5	localObject3	Object
      //   58	57	6	localJSONObject	JSONObject
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
      ThanksActivity.this.friendPickerdialogshow();
    }
    
    protected void onPreExecute()
    {
      this.customDialogProgress = new Dialog(ThanksActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
    }
  }
  
  public class StartCalender
    implements ThanksDynamicViewAdapter.GetClickedEditText, ThanksDynamicViewAdapter.SendWishNow
  {
    public StartCalender() {}
    
    public void getClickedEdit(EditText paramEditText1, EditText paramEditText2)
    {
      CommonHelper.arrEditTextContainer.add(paramEditText1);
      CommonHelper.arrEditTextContainer_show_value.add(paramEditText2);
      ThanksActivity.this.startActivityFromintent();
    }
    
    public void sendDetails(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    {
      ThanksActivity.this.tempCardID = paramString5;
      ThanksActivity.this.sendWishNowCards(paramString5, paramString4, paramString3, paramString1, paramString2);
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
      paramVarArgs = new NetworkCalls(ThanksActivity.this).downloadJesonFromUrl(paramVarArgs);
      System.out.println("track_sent_response" + paramVarArgs);
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
      this.customDialogProgress = new Dialog(ThanksActivity.this, 2131361798);
      this.customDialogProgress.requestWindowFeature(1);
      this.customDialogProgress.setContentView(2130903125);
      this.customDialogProgress.setCancelable(false);
      this.customDialogProgress.show();
      super.onPreExecute();
    }
  }
}
