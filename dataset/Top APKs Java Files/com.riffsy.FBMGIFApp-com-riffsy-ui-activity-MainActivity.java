package com.riffsy.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.deeplinkdispatch.DeepLink;
import com.evernote.android.job.JobManager;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.riffsy.analytic.ActionAE.Builder;
import com.riffsy.analytic.ActionAE.IBuilder;
import com.riffsy.analytic.TenorAnalyticEvent.IBuilder;
import com.riffsy.font.Roboto;
import com.riffsy.gifdetail.GifDetailsFragment;
import com.riffsy.i18n.LocaleChecker;
import com.riffsy.job.ScheduleTryUploadNotificationJob;
import com.riffsy.model.VodafoneResult;
import com.riffsy.model.event.FbReplyEvent;
import com.riffsy.model.event.ReplyEvent;
import com.riffsy.model.event.UploadEvent;
import com.riffsy.model.response.Partner;
import com.riffsy.ui.fragment.GifSearchFragment;
import com.riffsy.ui.fragment.PartnerSearchFragment;
import com.riffsy.ui.fragment.autocompletesearchfragment.AutocompleteSearchFragment;
import com.riffsy.ui.fragment.collectionfragment.CollectionFragment;
import com.riffsy.ui.fragment.homefragment.HomeFragment;
import com.riffsy.ui.fragment.privilege.PrivilegeChecker;
import com.riffsy.ui.fragment.privilege.TokenPair;
import com.riffsy.ui.fragment.selectcollectionfragment.SelectCollectionFragment;
import com.riffsy.ui.fragment.sendfirstgif.SendFirstGifFragment;
import com.riffsy.util.AnimatorUtils;
import com.riffsy.util.CrashlyticsHelper;
import com.riffsy.util.FbConstants;
import com.riffsy.util.NavigationUtils;
import com.riffsy.util.PermissionUtils;
import com.riffsy.util.SessionUtils;
import com.riffsy.util.SnackbarUtils;
import com.riffsy.util.TenorEventTracker;
import com.riffsy.util.UIUtils;
import com.riffsy.uxfragbase.BundleHelper;
import com.riffsy.uxfragbase.IFragmentTransmittable;
import com.riffsy.vodafone.ui.activity.VodafoneExtensionCreationActivity;
import com.squareup.otto.Subscribe;
import com.tenor.android.analytics.v2.AnalyticEvent.IBuilder;
import com.tenor.android.analytics.v2.AnalyticEventManager;
import com.tenor.android.core.constant.StringConstant;
import com.tenor.android.core.constant.SupportMessenger.Value;
import com.tenor.android.core.extension.network.ConnectivityChangeReceiver;
import com.tenor.android.core.extension.storage.LocalStorageClient;
import com.tenor.android.core.extension.util.ContentFormatUtils;
import com.tenor.android.core.model.impl.Result;
import com.tenor.android.core.util.CoreLogUtils;
import com.tenor.android.core.view.IBaseView;
import com.tenor.android.ots.model.requests.metadata.FBComposeReplyData;
import com.tenor.android.sdk.job.TenorJobManager;
import com.tenor.android.sdk.util.AbstractStringUtils;
import com.tenor.android.sdk.widget.tsv.EnhancedEditText;
import com.tenor.android.sdk.widget.tsv.TopSearchBar;
import com.tenor.android.sdk.widget.tsv.TopSearchBar.OnClickListener;
import com.tenor.android.vodafone.model.VodafoneReplyData;
import java.util.Locale;
import java.util.Set;

@DeepLink({"riffsy://launch_tab/{tab_name}"})
public class MainActivity
  extends RiffsyActivity
  implements IBaseView
{
  public static final String FRAGMENT_ACTIVITY = "FRAGMENT_ACTIVITY";
  public static final String FRAGMENT_AUTOCOMPLETE_SEARCH_SUGGESTIONS = "FRAGMENT_AUTOCOMPLETE_SEARCH_SUGGESTIONS";
  public static final String FRAGMENT_COLLECTION = "FRAGMENT_COLLECTION";
  public static final String FRAGMENT_FIRST_SEND = "FRAGMENT_FIRST_SEND";
  public static final String FRAGMENT_GIF_DETAILS = "FRAGMENT_GIF_DETAILS";
  public static final String FRAGMENT_HOME = "FRAGMENT_HOME";
  public static final String FRAGMENT_PARTNER_DETAILS = "FRAGMENT_PARTNER_DETAILS";
  public static final String FRAGMENT_PROFILE = "FRAGMENT_PROFILE";
  public static final String FRAGMENT_SEARCH = "FRAGMENT_SEARCH";
  public static final String FRAGMENT_SELECT_COLLECTION = "FRAGMENT_SELECT_COLLECTION";
  public static final String FRAGMENT_UPLOAD = "FRAGMENT_UPLOAD";
  public static boolean isFbReply;
  public static boolean isFbReplyOrCompose;
  public static String mSendApp;
  @SupportMessenger.Value
  public static String sReplyFlowType = "";
  @BindView(2131361909)
  BottomNavigationViewEx mBottomNavigationView;
  private String mFbReplyToken;
  @BindView(2131361910)
  View mFrame;
  private boolean mPendingResetToHomeFragment;
  private int mProfileTabLastPosition = 0;
  @BindView(2131362416)
  View mRootView;
  @BindView(2131361912)
  TopSearchBar mTopSearchBar;
  private TopSearchBar.OnClickListener mTopSearchBarOnClickListener;
  private String mUploadResultCopyText = "";
  
  public MainActivity() {}
  
  private void checkForReplyFlow()
  {
    sReplyFlowType = "";
    isFbReplyOrCompose = false;
    if (getIntent() == null) {
      return;
    }
    String str;
    label42:
    int i;
    if (((Boolean)BundleHelper.get(getIntent(), "vodafone_reply_intent_extra_key", Boolean.valueOf(false))).booleanValue())
    {
      str = "com.vodafone.messaging";
      i = -1;
      switch (str.hashCode())
      {
      }
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
      case 0: 
        parseFbData(new FBComposeReplyData(getIntent()));
        return;
        str = "com.facebook.orca";
        break label42;
        if (str.equals("com.facebook.orca"))
        {
          i = 0;
          continue;
          if (str.equals("com.vodafone.messaging"))
          {
            i = 1;
            continue;
            if (str.equals("")) {
              i = 2;
            }
          }
        }
        break;
      }
    }
    parseVodafoneData(new VodafoneReplyData(getIntent(), VodafoneExtensionCreationActivity.sCalledByVodafoneReceiver));
  }
  
  private void clearTextAndResetTopSearchBarState(int paramInt)
  {
    this.mTopSearchBar.updateViews(paramInt);
    this.mTopSearchBar.getEditText().setText("");
  }
  
  private void initFirstFragment(@Nullable Bundle paramBundle, @NonNull String paramString)
  {
    if (paramBundle != null) {}
    do
    {
      return;
      localFragment = getSupportFragmentManager().findFragmentByTag("FRAGMENT_HOME");
      paramBundle = localFragment;
      if (localFragment == null) {
        paramBundle = new HomeFragment();
      }
      if (paramBundle.isAdded()) {
        break;
      }
      getSupportFragmentManager().beginTransaction().add(2131361910, paramBundle, "FRAGMENT_HOME").commit();
    } while (("com.vodafone.messaging".equals(sReplyFlowType)) || (!SessionUtils.getInstalledPackages().contains("com.facebook.orca")));
    Fragment localFragment = getSupportFragmentManager().findFragmentByTag("FRAGMENT_FIRST_SEND");
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
      }
      paramBundle = localFragment;
      if (localFragment == null) {
        paramBundle = new SendFirstGifFragment();
      }
      getSupportFragmentManager().beginTransaction().replace(2131361910, paramBundle, "FRAGMENT_FIRST_SEND").addToBackStack(paramString).commit();
      return;
      getSupportFragmentManager().popBackStackImmediate(null, 1);
      getSupportFragmentManager().beginTransaction().replace(2131361910, paramBundle, "FRAGMENT_HOME").commit();
      break;
      if (paramString.equals("FRAGMENT_FIRST_SEND"))
      {
        i = 0;
        continue;
        if (paramString.equals("FRAGMENT_HOME")) {
          i = 1;
        }
      }
    }
  }
  
  private void injectAutocompleteSearchFragment(@NonNull String paramString)
  {
    Object localObject = getSupportFragmentManager().findFragmentByTag("FRAGMENT_AUTOCOMPLETE_SEARCH_SUGGESTIONS");
    if ((localObject instanceof AutocompleteSearchFragment))
    {
      if (!((Fragment)localObject).isAdded()) {
        performFragmentTransition(2131361910, (Fragment)localObject, "FRAGMENT_AUTOCOMPLETE_SEARCH_SUGGESTIONS", -1, false);
      }
      ((AutocompleteSearchFragment)localObject).updateAutocompleteSearch(paramString);
      return;
    }
    localObject = new Bundle();
    ((Bundle)localObject).putString("EXTRA_SEARCH_TERM", paramString);
    performFragmentTransition(2131361910, AutocompleteSearchFragment.newInstance((Bundle)localObject), "FRAGMENT_AUTOCOMPLETE_SEARCH_SUGGESTIONS", -1, true);
  }
  
  private static boolean isInReplyFlow()
  {
    return !"".equals(sReplyFlowType);
  }
  
  private void parseFbData(@Nullable FBComposeReplyData paramFBComposeReplyData)
  {
    if (paramFBComposeReplyData == null) {
      return;
    }
    this.mFbReplyToken = paramFBComposeReplyData.getToken();
    if ((paramFBComposeReplyData.isReply()) || (paramFBComposeReplyData.isCompose())) {}
    for (boolean bool = true;; bool = false)
    {
      isFbReplyOrCompose = bool;
      isFbReply = paramFBComposeReplyData.isReply();
      if (paramFBComposeReplyData.isReply())
      {
        sReplyFlowType = "com.facebook.orca";
        AnalyticEventManager.push(this, new ActionAE.Builder("fbm_reply").action("click").component("containing_app").build());
      }
      if (!paramFBComposeReplyData.isCompose()) {
        break;
      }
      sReplyFlowType = "com.facebook.orca";
      AnalyticEventManager.push(this, new ActionAE.Builder("fbm_compose").action("click").component("containing_app").build());
      return;
    }
  }
  
  private void parseVodafoneData(@Nullable VodafoneReplyData paramVodafoneReplyData)
  {
    if (paramVodafoneReplyData == null) {}
    while (!paramVodafoneReplyData.isComposeReply()) {
      return;
    }
    sReplyFlowType = paramVodafoneReplyData.getComposeReplyType();
    VodafoneExtensionCreationActivity.sCalledByVodafoneReceiver = false;
  }
  
  private void replyToFbMessenger(@NonNull Uri paramUri, @NonNull String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setFlags(1);
    localIntent.setDataAndType(paramUri, ContentFormatUtils.getUrlContentType(paramUri.getPath()));
    localIntent.setPackage("com.facebook.orca");
    localIntent.putExtra("com.facebook.orca.extra.PROTOCOL_VERSION", 20150314);
    localIntent.putExtra("com.facebook.orca.extra.APPLICATION_ID", FbConstants.FB_RIFFSY_APP_ID);
    localIntent.putExtra("com.facebook.orca.extra.THREAD_TOKEN", this.mFbReplyToken);
    localIntent.putExtra("com.facebook.orca.extra.METADATA", paramString);
    setResult(-1, localIntent);
    finish();
  }
  
  private void replyToFbMessenger(@NonNull ReplyEvent paramReplyEvent)
  {
    if (paramReplyEvent.getType() != 1) {
      throw new IllegalArgumentException("event must be a TYPE_FACEBOOK type ReplyEvent");
    }
    String str = ((FbReplyEvent)paramReplyEvent).getUriPath();
    replyToFbMessenger(LocalStorageClient.get().getUriForPathCompat(str), paramReplyEvent.getResult().getId());
  }
  
  private void replyToVodafone(@NonNull Result paramResult)
  {
    setResult(-1, new Intent().putExtra("EXRTA_KEY_VODAFONE_RESULT", new VodafoneResult(paramResult)));
    finish();
  }
  
  private void resetToHomeFragment()
  {
    this.mBottomNavigationView.setSelectedItemId(2131362262);
    SnackbarUtils.makeDefaultSnackbar(this.mRootView, this.mUploadResultCopyText, 0).show();
  }
  
  private void transferToActivityBaseOnNotificationId()
  {
    if (getIntent().hasExtra("EXTRA_NOTIFICATION_ID")) {}
    for (String str = getIntent().getStringExtra("EXTRA_NOTIFICATION_ID"); TextUtils.isEmpty(str); str = "") {
      return;
    }
    getIntent().putExtra("EXTRA_NOTIFICATION_ID", "");
    getIntent().removeExtra("EXTRA_NOTIFICATION_ID");
    int i = -1;
    switch (str.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
      case 0: 
        if (!isLoggedIn()) {
          break label191;
        }
        if (!ConnectivityChangeReceiver.isOnline(getContext())) {
          break label175;
        }
        this.mBottomNavigationView.setSelectedItemId(2131362265);
        return;
        if (str.equals("try_an_upload"))
        {
          i = 0;
          continue;
          if (str.equals("show_survey_to_keyboard_users")) {
            i = 1;
          }
        }
        break;
      }
    }
    label175:
    Toast.makeText(this, getString(2131821023), 1).show();
    return;
    label191:
    startActivity(new Intent(this, SignInActivity.class));
    AnimatorUtils.overridePendingTransition(this);
    return;
    startActivity(new Intent(this, KeyboardSurveyActivity.class));
    AnimatorUtils.overridePendingTransition(this);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
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
              return;
            } while (paramInt2 == 0);
            localObject = paramIntent.getData();
            paramIntent = paramIntent.getStringExtra("com.facebook.orca.extra.METADATA");
          } while (!isFbReplyOrCompose);
          replyToFbMessenger((Uri)localObject, paramIntent);
          isFbReplyOrCompose = false;
          isFbReply = false;
          return;
        } while ((paramInt2 != -1) || (paramIntent == null));
        paramIntent = paramIntent.getData();
        localObject = new Intent(this, EditProfilePicActivity.class);
        ((Intent)localObject).putExtra("EXTRA_IMAGE_URI", paramIntent.toString());
        startActivity((Intent)localObject);
        return;
      } while (paramInt2 != -1);
      paramIntent = SessionUtils.getNewProfilePhotoUri();
      Object localObject = new Intent(this, EditProfilePicActivity.class);
      ((Intent)localObject).putExtra("EXTRA_IMAGE_URI", paramIntent);
      startActivity((Intent)localObject);
      return;
    } while (paramInt2 != 0);
    finish();
  }
  
  public void onBackPressed()
  {
    try
    {
      int i = getSupportFragmentManager().getBackStackEntryCount();
      if (i > 0)
      {
        FragmentManager.BackStackEntry localBackStackEntry = getSupportFragmentManager().getBackStackEntryAt(i - 1);
        AnalyticEventManager.push(getActivity(), new ActionAE.Builder("back").action("click").component("containing_app").category(StringConstant.getOrEmpty(localBackStackEntry.getName()).toLowerCase(Locale.US)).build());
      }
      super.onBackPressed();
      return;
    }
    finally {}
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    checkForReplyFlow();
    mSendApp = TenorEventTracker.getClaimInstallApp();
    boolean bool = getIntent().getBooleanExtra("is_deep_link_flag", false);
    if (bool)
    {
      localObject = getIntent().getExtras();
      if (localObject != null) {
        AnalyticEventManager.push(getActivity(), new ActionAE.Builder("launch").info(StringConstant.getOrEmpty(((Bundle)localObject).getString("EXTRA_NOTIFICATION_ID"))).action("view").component("containing_app").category("deeplink").build());
      }
    }
    if (!bool) {
      AnalyticEventManager.push(this, new ActionAE.Builder("launch").action("view").component("containing_app").build());
    }
    setContentView(2131492902);
    ButterKnife.bind(this);
    this.mTopSearchBar.getEditText().addTextChangedListener(new MainActivity.1(this));
    this.mTopSearchBar.setTypeface(Roboto.get().getTypeface("Roboto-Bold"));
    this.mTopSearchBarOnClickListener = new MainActivity.2(this, this.mTopSearchBar);
    this.mTopSearchBar.addOnClickListener(this.mTopSearchBarOnClickListener);
    this.mTopSearchBar.getEditText().addOnFocusChangeListener(new MainActivity.3(this));
    getSupportFragmentManager().addOnBackStackChangedListener(new MainActivity.4(this));
    setTitle(2131820608);
    int i;
    if ((!SessionUtils.isFirstSendShown()) && (!SessionUtils.isKeyboardFtueShown(this)))
    {
      i = 1;
      if (i == 0) {
        break label396;
      }
    }
    label396:
    for (Object localObject = "FRAGMENT_FIRST_SEND";; localObject = "FRAGMENT_HOME")
    {
      initFirstFragment(paramBundle, (String)localObject);
      this.mBottomNavigationView.enableAnimation(false);
      this.mBottomNavigationView.enableShiftingMode(false);
      this.mBottomNavigationView.enableItemShiftingMode(false);
      this.mBottomNavigationView.setTextVisibility(false);
      this.mBottomNavigationView.setItemBackgroundResource(2131099900);
      this.mBottomNavigationView.setOnNavigationItemSelectedListener(new MainActivity.5(this, this));
      if (SessionUtils.isTryUploadNotificationAvailable()) {
        TenorJobManager.getInstance(getApplication()).schedule(ScheduleTryUploadNotificationJob.getJobRequest());
      }
      if (!SessionUtils.isNavigateBackFromActivity()) {
        transferToActivityBaseOnNotificationId();
      }
      if (!ConnectivityChangeReceiver.isOnline(this)) {
        Toast.makeText(this, 2131821023, 1).show();
      }
      return;
      i = 0;
      break;
    }
  }
  
  protected void onDestroy()
  {
    isFbReplyOrCompose = false;
    isFbReply = false;
    super.onDestroy();
  }
  
  protected void onPostResume()
  {
    super.onPostResume();
    Object localObject = TenorEventTracker.getUnauthorizedUserTokenPair(this);
    if (localObject != null) {
      PrivilegeChecker.notifyServerOnPrivilegeChanged(this, (TokenPair)localObject);
    }
    if (LocaleChecker.checkSelfLocale(this) == 0) {
      CoreLogUtils.e(this, "==> LOCALE changed 2");
    }
    try
    {
      localObject = getPackageManager().getLaunchIntentForPackage(getPackageName());
      if (localObject != null)
      {
        ((Intent)localObject).addFlags(67108864);
        startActivity((Intent)localObject);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        CrashlyticsHelper.logException(localException);
      }
    }
    finish();
  }
  
  public void onReceivedFragmentTransmittable(@NonNull IFragmentTransmittable paramIFragmentTransmittable)
  {
    Object localObject1 = paramIFragmentTransmittable.from();
    int i = -1;
    switch (((String)localObject1).hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    label612:
    label776:
    label978:
    label1070:
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
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              return;
                              if (!((String)localObject1).equals("FRAGMENT_HOME")) {
                                break;
                              }
                              i = 0;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_ACTIVITY")) {
                                break;
                              }
                              i = 1;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_GIF_DETAILS")) {
                                break;
                              }
                              i = 2;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_PROFILE")) {
                                break;
                              }
                              i = 3;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_SEARCH")) {
                                break;
                              }
                              i = 4;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_COLLECTION")) {
                                break;
                              }
                              i = 5;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_PARTNER_DETAILS")) {
                                break;
                              }
                              i = 6;
                              break;
                              if (!((String)localObject1).equals("FRAGMENT_AUTOCOMPLETE_SEARCH_SUGGESTIONS")) {
                                break;
                              }
                              i = 7;
                              break;
                              if ("FRAGMENT_SEARCH".equals(paramIFragmentTransmittable.to()))
                              {
                                paramIFragmentTransmittable = paramIFragmentTransmittable.data();
                                performFragmentTransition(2131361910, GifSearchFragment.newInstance(paramIFragmentTransmittable), "FRAGMENT_SEARCH", -1, true);
                                this.mTopSearchBar.updateViews(3);
                                this.mTopSearchBar.getEditText().setText(paramIFragmentTransmittable.getString("KEY_QUERY", ""));
                                return;
                              }
                              if ("FRAGMENT_GIF_DETAILS".equals(paramIFragmentTransmittable.to()))
                              {
                                performFragmentTransition(2131361910, GifDetailsFragment.newInstance(paramIFragmentTransmittable.data()), "FRAGMENT_GIF_DETAILS", -1, true);
                                return;
                              }
                            } while (!"FRAGMENT_SELECT_COLLECTION".equals(paramIFragmentTransmittable.to()));
                            localObject2 = getSupportFragmentManager().findFragmentByTag("FRAGMENT_SELECT_COLLECTION");
                            localObject1 = localObject2;
                            if (localObject2 == null) {
                              localObject1 = SelectCollectionFragment.newInstance(paramIFragmentTransmittable.data());
                            }
                            performFragmentTransition(2131361910, (Fragment)localObject1, "FRAGMENT_SELECT_COLLECTION", -1, true);
                            return;
                            if ("FRAGMENT_GIF_DETAILS".equals(paramIFragmentTransmittable.to()))
                            {
                              performFragmentTransition(2131361910, GifDetailsFragment.newInstance(paramIFragmentTransmittable.data()), paramIFragmentTransmittable.to(), 4099, true);
                              return;
                            }
                          } while ((!"FRAGMENT_SEARCH".equals(paramIFragmentTransmittable.to())) || (!paramIFragmentTransmittable.data().containsKey("KEY_QUERY")));
                          paramIFragmentTransmittable = paramIFragmentTransmittable.data().getString("KEY_QUERY");
                          localObject1 = new Bundle();
                          ((Bundle)localObject1).putString("KEY_QUERY", paramIFragmentTransmittable);
                          performFragmentTransition(2131361910, GifSearchFragment.newInstance((Bundle)localObject1), "FRAGMENT_SEARCH", 4099, true);
                          this.mTopSearchBar.updateViews(3);
                          this.mTopSearchBar.getEditText().setText(paramIFragmentTransmittable);
                          return;
                          if (!"FRAGMENT_COLLECTION".equals(paramIFragmentTransmittable.to())) {
                            break label612;
                          }
                        } while (!paramIFragmentTransmittable.data().containsKey("EXTRA_COLLECTION_NAME"));
                        paramIFragmentTransmittable = paramIFragmentTransmittable.data().getString("EXTRA_COLLECTION_NAME");
                        localObject1 = new Bundle();
                        ((Bundle)localObject1).putString("EXTRA_COLLECTION_NAME", paramIFragmentTransmittable);
                        performFragmentTransition(2131361910, CollectionFragment.newInstance((Bundle)localObject1), "FRAGMENT_COLLECTION", 4099, true);
                        return;
                      } while (!paramIFragmentTransmittable.data().containsKey("EXTRA_CURRENT_TAB_POSITION"));
                      this.mProfileTabLastPosition = paramIFragmentTransmittable.data().getInt("EXTRA_CURRENT_TAB_POSITION", this.mProfileTabLastPosition);
                      return;
                      if (!"FRAGMENT_SEARCH".equals(paramIFragmentTransmittable.to())) {
                        break label776;
                      }
                    } while (!paramIFragmentTransmittable.data().containsKey("FRAG_BNDL_KEY_SEARCH_QUERY"));
                    localObject1 = paramIFragmentTransmittable.data().getString("FRAG_BNDL_KEY_SEARCH_QUERY", "");
                    paramIFragmentTransmittable = paramIFragmentTransmittable.data().getString("FRAG_BNDL_KEY_SEARCH_TITLE", "");
                    localObject2 = new Bundle();
                    ((Bundle)localObject2).putString("KEY_QUERY", (String)localObject1);
                    performFragmentTransition(2131361910, GifSearchFragment.newInstance((Bundle)localObject2), "FRAGMENT_SEARCH", 4099, true);
                    this.mTopSearchBar.updateViews(3);
                    this.mTopSearchBar.getEditText().setText(AbstractStringUtils.getFirstNonEmpty(new String[] { paramIFragmentTransmittable, localObject1 }));
                    return;
                  } while (!"FRAGMENT_GIF_DETAILS".equals(paramIFragmentTransmittable.to()));
                  performFragmentTransition(2131361910, GifDetailsFragment.newInstance(paramIFragmentTransmittable.data()), "FRAGMENT_GIF_DETAILS", 4099, true);
                  return;
                } while (!"FRAGMENT_GIF_DETAILS".equals(paramIFragmentTransmittable.to()));
                performFragmentTransition(2131361910, GifDetailsFragment.newInstance(paramIFragmentTransmittable.data()), paramIFragmentTransmittable.to(), 4099, true);
                return;
                if (!"FRAGMENT_SEARCH".equals(paramIFragmentTransmittable.to())) {
                  break label978;
                }
              } while (!paramIFragmentTransmittable.data().containsKey("FRAG_BNDL_KEY_SEARCH_QUERY"));
              localObject1 = paramIFragmentTransmittable.data().getString("FRAG_BNDL_KEY_SEARCH_TITLE", "");
              paramIFragmentTransmittable = paramIFragmentTransmittable.data().getString("FRAG_BNDL_KEY_SEARCH_QUERY", "");
              Object localObject2 = new Bundle();
              ((Bundle)localObject2).putString("KEY_QUERY", paramIFragmentTransmittable);
              performFragmentTransition(2131361910, GifSearchFragment.newInstance((Bundle)localObject2), "FRAGMENT_SEARCH", -1, false);
              this.mTopSearchBar.updateViews(3);
              this.mTopSearchBar.getEditText().setText(AbstractStringUtils.getFirstNonEmpty(new String[] { localObject1, paramIFragmentTransmittable }));
              return;
            } while (!"FRAGMENT_GIF_DETAILS".equals(paramIFragmentTransmittable.to()));
            performFragmentTransition(2131361910, GifDetailsFragment.newInstance(paramIFragmentTransmittable.data()), "FRAGMENT_GIF_DETAILS", -1, false);
            return;
          } while (!TextUtils.isEmpty(paramIFragmentTransmittable.to()));
          if (!paramIFragmentTransmittable.data().containsKey("FRAG_BNDL_KEY_AUTOCOMPLETE_SUGGESTION")) {
            break label1070;
          }
          paramIFragmentTransmittable = paramIFragmentTransmittable.data().getString("FRAG_BNDL_KEY_AUTOCOMPLETE_SUGGESTION", "");
        } while (TextUtils.isEmpty(paramIFragmentTransmittable));
        this.mTopSearchBar.onSearch(paramIFragmentTransmittable);
        return;
      } while (!paramIFragmentTransmittable.data().containsKey("FRAG_BNDL_KEY_AUTOCOMPLETE_PARTNER"));
      UIUtils.hideInputMethod(this);
      paramIFragmentTransmittable = (Partner)BundleHelper.get(paramIFragmentTransmittable.data(), "FRAG_BNDL_KEY_AUTOCOMPLETE_PARTNER", new Partner());
    } while (paramIFragmentTransmittable.isStub());
    localObject1 = new Bundle();
    ((Bundle)localObject1).putSerializable("KEY_PARTNER", paramIFragmentTransmittable);
    performFragmentTransition(2131361910, PartnerSearchFragment.newInstance((Bundle)localObject1), "FRAGMENT_PARTNER_DETAILS", -1, false);
  }
  
  public void onReceivedTopSearchBarVisibility(int paramInt)
  {
    this.mTopSearchBar.setVisibility(paramInt);
  }
  
  @Subscribe
  public void onReplyToMessenger(ReplyEvent paramReplyEvent)
  {
    if ("com.facebook.orca".equals(sReplyFlowType)) {
      replyToFbMessenger(paramReplyEvent);
    }
    while (!"com.vodafone.messaging".equals(sReplyFlowType)) {
      return;
    }
    replyToVodafone(paramReplyEvent.getResult());
  }
  
  @TargetApi(23)
  public void onRequestPermissionsResult(int paramInt, @NonNull String[] paramArrayOfString, @NonNull int[] paramArrayOfInt)
  {
    if (paramInt == 846)
    {
      if ((paramArrayOfInt.length > 0) && (paramArrayOfInt[0] == 0)) {
        NavigationUtils.openPhoneImageCamera(this, 846);
      }
      while (shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
        return;
      }
      Snackbar.make(this.mRootView, getString(2131821054), 0).setAction(getString(2131821223), new MainActivity.6(this)).show();
      return;
    }
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.mPendingResetToHomeFragment)
    {
      resetToHomeFragment();
      this.mPendingResetToHomeFragment = false;
    }
    if (SessionUtils.isNavigateBackFromActivity())
    {
      SessionUtils.setNavigateBackFromActivity(false);
      SessionUtils.setContainingSearchTag("");
    }
  }
  
  public void onStart()
  {
    super.onStart();
    int i;
    if ((Build.VERSION.SDK_INT >= 23) && (!PermissionUtils.hasWriteExternalStoragePermission(this)))
    {
      i = 1;
      if ((i == 0) || ("com.vodafone.messaging".equals(sReplyFlowType))) {
        break label60;
      }
      startActivityForResult(new Intent(this, PermissionsPrimerActivity.class), 1916);
    }
    label60:
    while (("com.vodafone.messaging".equals(sReplyFlowType)) || (!"com.facebook.orca".equals(sReplyFlowType)))
    {
      return;
      i = 0;
      break;
    }
  }
  
  @Subscribe
  public void onUploadEventReceived(UploadEvent paramUploadEvent)
  {
    this.mUploadResultCopyText = paramUploadEvent.getUploadCopyText();
    if (isInstanceStateRestored())
    {
      resetToHomeFragment();
      return;
    }
    this.mPendingResetToHomeFragment = true;
  }
}
