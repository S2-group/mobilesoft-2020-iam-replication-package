package com.tdr3.hs.android2.core.fragments;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.crashlytics.android.core.CrashlyticsCore;
import com.squareup.otto.Bus;
import com.squareup.otto.d;
import com.tdr3.hs.android.HSApp;
import com.tdr3.hs.android.HSApp.TrackerType;
import com.tdr3.hs.android.data.api.AuthenticationModel;
import com.tdr3.hs.android.data.api.SeasonedRepository;
import com.tdr3.hs.android.data.db.clientData.Store;
import com.tdr3.hs.android.data.db.seasoned.SeasonedClientInfo;
import com.tdr3.hs.android.data.db.seasoned.SeasonedCrossroadsInfo;
import com.tdr3.hs.android.data.dto.seasoned.SeasonedClientInfoDTO;
import com.tdr3.hs.android.data.local.login.LoginData;
import com.tdr3.hs.android.ui.BaseActivity;
import com.tdr3.hs.android.ui.main.MainActivity;
import com.tdr3.hs.android.ui.main.MainActivity.MenuChangedListener;
import com.tdr3.hs.android.ui.seasoned.SeasonedInfoDialogView;
import com.tdr3.hs.android.ui.seasoned.SeasonedSignUpActivity;
import com.tdr3.hs.android.ui.seasoned.SeasonedSignUpActivity.Companion;
import com.tdr3.hs.android.ui.settings.SettingsActivity;
import com.tdr3.hs.android2.core.ApplicationActivity;
import com.tdr3.hs.android2.core.ApplicationCache;
import com.tdr3.hs.android2.core.ApplicationData;
import com.tdr3.hs.android2.core.ApplicationData.ListItemType;
import com.tdr3.hs.android2.core.CoreSherlockListFragment;
import com.tdr3.hs.android2.core.HsLog;
import com.tdr3.hs.android2.core.SharedPreferencesManager;
import com.tdr3.hs.android2.core.api.HSApi;
import com.tdr3.hs.android2.models.EmailStatus;
import com.tdr3.hs.android2.models.PartnerApplication;
import com.tdr3.hs.android2.models.ScrollEventOtto;
import com.tdr3.hs.android2.models.UserProfile;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.realm.Realm;
import io.realm.ad;
import io.realm.ae;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import retrofit2.Response;

public class MainMenuFragment
  extends CoreSherlockListFragment
  implements AbsListView.OnScrollListener
{
  private static String ARG_IS_SECONDARY = "arg_is_secondary";
  private static String TAG = "MainMenuFragment";
  private static MainActivity.MenuChangedListener mMenuChangedListener;
  @Inject
  HSApi api;
  @Inject
  AuthenticationModel authenticationModel;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  ListView mListView = null;
  public MenuAdapter mMenuAdapter = null;
  private Realm realm;
  private SeasonedClientInfo seasonedClientInfo = null;
  private SeasonedCrossroadsInfo seasonedCrossroadsInfo = null;
  @Inject
  SeasonedRepository seasonedRepository;
  
  public MainMenuFragment() {}
  
  private void UpdateInstalledPartnerApps(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    Iterator localIterator1 = ApplicationData.getInstance().getPartnerApplications().values().iterator();
    while (localIterator1.hasNext())
    {
      PartnerApplication localPartnerApplication = (PartnerApplication)localIterator1.next();
      Iterator localIterator2 = paramContext.iterator();
      while (localIterator2.hasNext()) {
        if (((ApplicationInfo)localIterator2.next()).packageName.equals(localPartnerApplication.getPackageName())) {
          localPartnerApplication.setIsInstalled(true);
        }
      }
    }
  }
  
  private void checkEmailForSeasoned()
  {
    if (ApplicationData.getInstance().getUserProfile().getEmailStatusEnum() != EmailStatus.Confirmed) {
      new AlertDialog.Builder(getActivity()).setTitle(2131821616).setMessage(2131821615).setPositiveButton(2131821614, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
      }).setNegativeButton(17039360, null).show();
    }
  }
  
  private void deepLinkToCrossroads()
  {
    PackageManager localPackageManager = getActivity().getPackageManager();
    if (localPackageManager != null)
    {
      Intent localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse("seasoned-community://app/"));
      Intent localIntent2 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=co.seasoned.seasonedMessaging"));
      Intent localIntent3 = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=co.seasoned.seasonedMessaging"));
      if (localIntent1.resolveActivity(localPackageManager) != null)
      {
        startActivity(localIntent1);
        return;
      }
      if (localIntent2.resolveActivity(localPackageManager) != null)
      {
        startActivity(localIntent2);
        return;
      }
      if (localIntent3.resolveActivity(localPackageManager) != null) {
        startActivity(localIntent3);
      }
    }
  }
  
  private void navigateToNextActivity()
  {
    this.baseActivity.hideProgress();
    switchFragment(ApplicationData.getInstance().getHomeActivity());
  }
  
  public static MainMenuFragment newInstance(boolean paramBoolean, MainActivity.MenuChangedListener paramMenuChangedListener)
  {
    mMenuChangedListener = paramMenuChangedListener;
    paramMenuChangedListener = new MainMenuFragment();
    Bundle localBundle = new Bundle();
    localBundle.putBoolean(ARG_IS_SECONDARY, paramBoolean);
    paramMenuChangedListener.setArguments(localBundle);
    return paramMenuChangedListener;
  }
  
  private void showSeasonedInfoDialog(boolean paramBoolean)
  {
    SeasonedInfoDialogView localSeasonedInfoDialogView = new SeasonedInfoDialogView(getContext(), paramBoolean);
    AlertDialog localAlertDialog = new AlertDialog.Builder(getContext()).setView(localSeasonedInfoDialogView).setCancelable(true).create();
    localSeasonedInfoDialogView.getImageClose().setOnClickListener(new -..Lambda.MainMenuFragment.-Ojo4xyQMx2t5rH5PI91duITL8Y(localAlertDialog));
    localAlertDialog.show();
  }
  
  private void switchFragment(ApplicationActivity paramApplicationActivity)
  {
    if (getActivity() == null) {
      return;
    }
    ApplicationData.getInstance().setCurrentActivity(paramApplicationActivity);
    if ((getActivity() instanceof MainActivity)) {
      ((MainActivity)getActivity()).switchContent(paramApplicationActivity);
    }
  }
  
  private void switchStores(int paramInt)
  {
    this.baseActivity.showProgress();
    if (this.realm.j()) {
      this.realm = Realm.m();
    }
    Object localObject1 = this.realm.a(Store.class).b("name").d();
    Object localObject2 = String.valueOf(((Store)((ae)localObject1).get(paramInt)).getId());
    SharedPreferencesManager.setPreference("PREF_ASC_SELECTED_STORE_ID", localObject2);
    if (!TextUtils.isEmpty((CharSequence)localObject2))
    {
      localObject1 = ((ae)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (Store)((Iterator)localObject1).next();
        if (((Store)localObject3).getId() == Long.parseLong((String)localObject2))
        {
          SharedPreferencesManager.setPreference("PREF_STORE_CLIENT_ID", String.valueOf(((Store)localObject3).getId()));
          SharedPreferencesManager.setPreference("PREF_STORE_CLIENT_NAME", ((Store)localObject3).getName());
        }
      }
    }
    Object localObject3 = SharedPreferencesManager.getStringPreference("PREF_TOKEN");
    localObject1 = Observable.b();
    if (!TextUtils.isEmpty((CharSequence)localObject3)) {
      localObject1 = this.authenticationModel.authenticateWithToken((String)localObject3, (String)localObject2);
    }
    localObject2 = new DisposableObserver()
    {
      public void onComplete()
      {
        MainMenuFragment.this.baseActivity.hideProgress();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        new AlertDialog.Builder(MainMenuFragment.this.getContext()).setTitle(2131821191).setMessage(paramAnonymousThrowable.getMessage()).show();
        MainMenuFragment.mMenuChangedListener.reloadMenu();
      }
      
      public void onNext(LoginData paramAnonymousLoginData)
      {
        ApplicationCache.getInstance().getRosterMap().clear();
        paramAnonymousLoginData = MainMenuFragment.this;
        paramAnonymousLoginData.UpdateInstalledPartnerApps(paramAnonymousLoginData.baseActivity);
        paramAnonymousLoginData = SharedPreferencesManager.getStringPreference("PREF_HOME_ACTIVITY");
        if (paramAnonymousLoginData == null)
        {
          ApplicationData.getInstance().setCurrentActivity(ApplicationActivity.MySchedule);
        }
        else
        {
          paramAnonymousLoginData = ApplicationActivity.getFromStringName(paramAnonymousLoginData);
          int i = 0;
          Iterator localIterator = ApplicationData.getInstance().getApplicationActivities().iterator();
          while (localIterator.hasNext()) {
            if ((ApplicationActivity)localIterator.next() == paramAnonymousLoginData) {
              i = 1;
            }
          }
          if (i == 0) {
            paramAnonymousLoginData = ApplicationActivity.MySchedule;
          }
          ApplicationData.getInstance().setCurrentActivity(paramAnonymousLoginData);
        }
        MainMenuFragment.this.navigateToNextActivity();
        MainMenuFragment.mMenuChangedListener.reloadMenu();
      }
    };
    this.compositeDisposable.a((Disposable)((Observable)localObject1).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).b((Observer)localObject2));
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 7327) && (paramInt2 == -1)) {
      this.compositeDisposable.a((Disposable)this.seasonedRepository.loadClientInformation(ApplicationData.getInstance().getUserIdLong()).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).b(new DisposableObserver()
      {
        public void onComplete() {}
        
        public void onError(Throwable paramAnonymousThrowable)
        {
          Log.e(MainMenuFragment.TAG, paramAnonymousThrowable.getMessage());
        }
        
        public void onNext(SeasonedClientInfoDTO paramAnonymousSeasonedClientInfoDTO)
        {
          if (MainMenuFragment.this.realm.j())
          {
            MainMenuFragment.access$102(MainMenuFragment.this, Realm.m());
            paramAnonymousSeasonedClientInfoDTO = MainMenuFragment.this;
            MainMenuFragment.access$202(paramAnonymousSeasonedClientInfoDTO, (SeasonedClientInfo)paramAnonymousSeasonedClientInfoDTO.realm.a(SeasonedClientInfo.class).a("id", Long.valueOf(ApplicationData.getInstance().getUserIdLong())).e());
          }
          MainMenuFragment.this.populateMainMenu();
        }
      }));
    }
  }
  
  public void onAttach(Context paramContext)
  {
    dagger.android.support.a.a(this);
    super.onAttach(paramContext);
    this.baseActivity = ((BaseActivity)paramContext);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    HSApp.getEventBus().register(this);
    this.realm = Realm.m();
    this.seasonedClientInfo = ((SeasonedClientInfo)this.realm.a(SeasonedClientInfo.class).a("id", Long.valueOf(ApplicationData.getInstance().getUserIdLong())).e());
    populateMainMenu();
    paramLayoutInflater = paramLayoutInflater.inflate(2131493063, paramViewGroup, false);
    this.mListView = ((ListView)paramLayoutInflater.findViewById(16908298));
    this.mListView.setBackgroundColor(getResources().getColor(17170443));
    this.mListView.setDividerHeight(1);
    this.mListView.setOnScrollListener(this);
    return paramLayoutInflater;
  }
  
  public void onDestroyView()
  {
    super.onDestroyView();
    HSApp.getEventBus().unregister(this);
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
    paramListView = ((RowItem)this.mMenuAdapter.getItem(paramInt)).getApplicationActivity();
    if (this.realm.j())
    {
      this.realm = Realm.m();
      this.seasonedClientInfo = ((SeasonedClientInfo)this.realm.a(SeasonedClientInfo.class).a("id", Long.valueOf(ApplicationData.getInstance().getUserIdLong())).e());
    }
    if ((paramListView != ApplicationActivity.SeasonedCrossroadsJoin) && (paramListView != ApplicationActivity.SeasonedCrossroadsCommunity))
    {
      if (paramListView != ApplicationActivity.SeasonedManagerTitle)
      {
        if (paramListView == ApplicationActivity.SeasonedEmployeeTitle) {
          return;
        }
        paramView = this.seasonedClientInfo;
        if ((paramView != null) && (!paramView.getProvisioned()) && ((paramListView == ApplicationActivity.SeasonedManagerApplicants) || (paramListView == ApplicationActivity.SeasonedManagerInterviews) || (paramListView == ApplicationActivity.SeasonedManagerGetReferrals))) {
          return;
        }
        if (paramListView == ApplicationActivity.SeasonedEmployeeReferAFriend)
        {
          paramView = this.seasonedClientInfo;
          if ((paramView != null) && ((!paramView.getProvisioned()) || (this.seasonedClientInfo.getOpenJobCount() < 1))) {
            return;
          }
        }
        if ((paramListView == ApplicationActivity.SeasonedManagerOpenJobs) || (paramListView == ApplicationActivity.SeasonedManagerStoreProfile) || (paramListView == ApplicationActivity.SeasonedManagerGetReferrals) || (paramListView == ApplicationActivity.SeasonedManagerInterviews) || (paramListView == ApplicationActivity.SeasonedManagerApplicants) || (paramListView == ApplicationActivity.SeasonedEmployeeDeliciousContent) || (paramListView == ApplicationActivity.SeasonedEmployeeReferAFriend) || (paramListView == ApplicationActivity.SeasonedEmployeePerks) || (paramListView == ApplicationActivity.SeasonedEmployeeProfile)) {
          if (ApplicationData.getInstance().getUserProfile() != null)
          {
            if (TextUtils.isEmpty(ApplicationData.getInstance().getUserProfile().getEmail())) {
              new AlertDialog.Builder(getActivity()).setTitle(2131821616).setMessage(2131821615).setPositiveButton(2131821614, new -..Lambda.MainMenuFragment.kHqs0pe0cWVeDjbuwwazO0bIT4U(this)).setNegativeButton(17039360, null).show();
            }
          }
          else
          {
            ((BaseActivity)Objects.requireNonNull(getActivity())).showProgress();
            this.compositeDisposable.a((Disposable)this.api.getUserProfile().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).b(new DisposableObserver()
            {
              public void onComplete() {}
              
              public void onError(Throwable paramAnonymousThrowable)
              {
                ((BaseActivity)MainMenuFragment.this.getActivity()).hideProgress();
                Log.e(MainMenuFragment.TAG, paramAnonymousThrowable.getLocalizedMessage(), paramAnonymousThrowable);
              }
              
              public void onNext(Response<UserProfile> paramAnonymousResponse)
              {
                ((BaseActivity)MainMenuFragment.this.getActivity()).hideProgress();
                ApplicationData.getInstance().setUserProfile((UserProfile)paramAnonymousResponse.e());
                if (TextUtils.isEmpty(ApplicationData.getInstance().getUserProfile().getEmail())) {
                  new AlertDialog.Builder(MainMenuFragment.this.getActivity()).setTitle(2131821616).setCancelable(false).setMessage(2131821615).setPositiveButton(2131821614, new -..Lambda.MainMenuFragment.2.8qUoqDKUHLrOaZ4nDgbT59sg1BU(this)).setNegativeButton(17039360, null).show();
                }
              }
            }));
          }
        }
        paramView = this.seasonedClientInfo;
        if ((paramView != null) && (!paramView.getProvisioned()) && ((paramListView == ApplicationActivity.SeasonedManagerStoreProfile) || (paramListView == ApplicationActivity.SeasonedManagerOpenJobs)))
        {
          startActivityForResult(SeasonedSignUpActivity.Companion.newIntent(getActivity()), 7327);
          return;
        }
        if ((ApplicationData.getInstance().getApplicationActivities().size() > paramInt) && (paramListView != ApplicationActivity.Stores))
        {
          if (paramListView == ApplicationActivity.Settings)
          {
            startActivity(new Intent(getActivity().getBaseContext(), SettingsActivity.class));
            return;
          }
          if ((paramListView == ApplicationData.getInstance().getCurrentActivity()) && ((getActivity() instanceof MainActivity))) {
            ((MainActivity)getActivity()).toggle();
          }
          if (paramListView == ApplicationActivity.WebClock)
          {
            HSApp.sendGAEvent(HSApp.TrackerType.HS, 2131821068, 2131821150, 2131821151);
            this.baseActivity.showProgress();
            this.api.getWebClockSSOInfo().b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).a(new -..Lambda.MainMenuFragment.hgiHiBafwmtZCTqeJAFiHpVu_s4(this), new -..Lambda.MainMenuFragment.Zh6WaUAzmoD6NODIscGURX5x64E(this));
            return;
          }
          if (paramListView == ApplicationActivity.WebDashboard)
          {
            paramListView = new StringBuilder();
            paramListView.append(ApplicationData.getInstance().getRestHostAddress());
            paramListView.append("/intra-day-dashboard.hs?t=");
            paramListView.append(SharedPreferencesManager.getStringPreference("PREF_TOKEN"));
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramListView.toString())));
            return;
          }
          if (paramListView != ApplicationActivity.Divider)
          {
            ApplicationData.getInstance().setCurrentActivity(paramListView);
            switchFragment(paramListView);
          }
          return;
        }
        paramView = SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_ID");
        Object localObject = this.realm.a(Store.class).b("name").d();
        paramListView = new CharSequence[((ae)localObject).size()];
        paramInt = 0;
        int j;
        for (int i = 0; paramInt < ((ae)localObject).size(); i = j)
        {
          paramListView[paramInt] = ((Store)((ae)localObject).get(paramInt)).getName();
          j = i;
          if (!TextUtils.isEmpty(paramView))
          {
            j = i;
            if (Long.parseLong(paramView) == ((Store)((ae)localObject).get(paramInt)).getId()) {
              j = paramInt;
            }
          }
          paramInt += 1;
        }
        paramView = (LinearLayout)getLayoutInflater().inflate(2131493180, null);
        localObject = (TextView)paramView.findViewById(2131297326);
        ((TextView)localObject).setText(getString(2131821623, new Object[] { SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_NAME") }));
        new AlertDialog.Builder(getContext()).setCustomTitle(paramView).setSingleChoiceItems(paramListView, i, new -..Lambda.MainMenuFragment.zdxKbNC1PpPaXii-AjaYfxbjfeE(this, (TextView)localObject, paramListView)).setCancelable(false).setPositiveButton(17039370, new -..Lambda.MainMenuFragment.efgMldnvJIArXhd1fRqfRWB9h-g(this)).setNegativeButton(17039360, null).show();
        return;
      }
      return;
    }
    deepLinkToCrossroads();
  }
  
  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (paramInt == 0)
    {
      paramAbsListView = new ScrollEventOtto();
      paramAbsListView.setListView(this.mListView);
      HSApp.getEventBus().post(paramAbsListView);
    }
  }
  
  public void onStop()
  {
    super.onStop();
    this.compositeDisposable.a();
    if (!this.realm.j()) {
      this.realm.close();
    }
  }
  
  public void populateMainMenu()
  {
    try
    {
      this.mMenuAdapter = new MenuAdapter(this.baseActivity, androidx.core.content.a.c(getContext(), 2131099861));
      ApplicationActivity localApplicationActivity1 = ApplicationData.getInstance().getCurrentActivity();
      String str = SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_NAME");
      List localList = ApplicationData.getInstance().getApplicationActivities();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ApplicationActivity localApplicationActivity2 = (ApplicationActivity)localIterator.next();
        if (localApplicationActivity2 == ApplicationActivity.Stores) {
          this.mMenuAdapter.add(new RowItem(localApplicationActivity2, str, ApplicationData.ListItemType.Switcher));
        } else if (localApplicationActivity2 == ApplicationActivity.Training)
        {
          if (localApplicationActivity2 == localApplicationActivity1) {
            this.mMenuAdapter.add(new RowItem(localApplicationActivity1, str, ApplicationData.ListItemType.TrainingSelected));
          } else {
            this.mMenuAdapter.add(new RowItem(localApplicationActivity2, str, ApplicationData.ListItemType.TrainingUnselected));
          }
        }
        else if ((localApplicationActivity2 != ApplicationActivity.SeasonedManagerTitle) && (localApplicationActivity2 != ApplicationActivity.SeasonedEmployeeTitle))
        {
          if (localApplicationActivity2 == ApplicationActivity.SeasonedCrossroadsJoin) {
            this.mMenuAdapter.add(new RowItem(ApplicationActivity.SeasonedCrossroadsJoin, ApplicationData.getActivityIconResourceId(ApplicationActivity.SeasonedCrossroadsJoin), ApplicationData.ListItemType.SeasonedCrossroadsJoin));
          } else if (localApplicationActivity2 == localApplicationActivity1) {
            this.mMenuAdapter.add(new RowItem(localApplicationActivity1, ApplicationData.getActivityIconResourceId(localApplicationActivity1), ApplicationData.ListItemType.Selected));
          } else {
            this.mMenuAdapter.add(new RowItem(localApplicationActivity2, ApplicationData.getActivityIconResourceId(localApplicationActivity2), ApplicationData.ListItemType.Unselected));
          }
        }
        else {
          this.mMenuAdapter.add(new RowItem(localApplicationActivity2, ApplicationData.getActivityIconResourceId(localApplicationActivity2), ApplicationData.ListItemType.SeasonedTitle));
        }
      }
      if (!localList.isEmpty()) {
        setListAdapter(this.mMenuAdapter);
      }
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      if (CrashlyticsCore.getInstance() != null) {
        CrashlyticsCore.getInstance().log(6, TAG, localIllegalStateException.getLocalizedMessage());
      }
    }
  }
  
  public void refreshList()
  {
    if (this.mMenuAdapter == null) {
      return;
    }
    ArrayList localArrayList = ((MenuAdapter)getListAdapter()).getItems();
    ApplicationActivity localApplicationActivity = ApplicationData.getInstance().getCurrentActivity();
    int i = 0;
    while (i < localArrayList.size())
    {
      ApplicationData.ListItemType localListItemType = ((RowItem)((MenuAdapter)getListAdapter()).getItem(i)).getItemType();
      if ((localApplicationActivity != null) && (((RowItem)localArrayList.get(i)).getApplicationActivity() != null) && (localApplicationActivity.equals(((RowItem)localArrayList.get(i)).getApplicationActivity())))
      {
        if (localListItemType == ApplicationData.ListItemType.Unselected) {
          ((RowItem)this.mMenuAdapter.getItem(i)).setItemType(ApplicationData.ListItemType.Selected);
        } else if (localListItemType == ApplicationData.ListItemType.TrainingUnselected) {
          ((RowItem)this.mMenuAdapter.getItem(i)).setItemType(ApplicationData.ListItemType.TrainingSelected);
        }
      }
      else if (localListItemType == ApplicationData.ListItemType.Selected) {
        ((RowItem)this.mMenuAdapter.getItem(i)).setItemType(ApplicationData.ListItemType.Unselected);
      } else if (localListItemType == ApplicationData.ListItemType.TrainingSelected) {
        ((RowItem)this.mMenuAdapter.getItem(i)).setItemType(ApplicationData.ListItemType.TrainingUnselected);
      }
      i += 1;
    }
    this.mMenuAdapter.notifyDataSetInvalidated();
  }
  
  @d
  public void scrollEvent(ScrollEventOtto paramScrollEventOtto)
  {
    if (paramScrollEventOtto.getListView() == this.mListView) {
      return;
    }
    int[] arrayOfInt = new int[2];
    paramScrollEventOtto.getListView().getLocationInWindow(arrayOfInt);
    int i = arrayOfInt[1];
    paramScrollEventOtto.getListView().getLocationInWindow(arrayOfInt);
    int j = arrayOfInt[1];
    this.mListView.setSelectionFromTop(paramScrollEventOtto.getListView().getFirstVisiblePosition(), paramScrollEventOtto.getListView().getChildAt(0).getTop() + (i - j));
  }
  
  public class MenuAdapter
    extends ArrayAdapter<MainMenuFragment.RowItem>
  {
    static final int TYPE_SEASONED_CROSSROADS_COMMUNITY = 8;
    static final int TYPE_SEASONED_CROSSROADS_JOIN = 7;
    static final int TYPE_SEASONED_TITLE = 6;
    private int mSelectedActivityBackground;
    private LayoutInflater mViewInflater;
    private ArrayList<MainMenuFragment.RowItem> rowItems = new ArrayList();
    
    MenuAdapter(Context paramContext, int paramInt)
    {
      super(0);
      this.mSelectedActivityBackground = paramInt;
    }
    
    public void add(MainMenuFragment.RowItem paramRowItem)
    {
      super.add(paramRowItem);
      this.rowItems.add(paramRowItem);
    }
    
    public int getItemViewType(int paramInt)
    {
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.Selected) {
        return 1;
      }
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.Switcher) {
        return 3;
      }
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.TrainingUnselected) {
        return 4;
      }
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.TrainingSelected) {
        return 5;
      }
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.SeasonedTitle) {
        return 6;
      }
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.SeasonedCrossroadsJoin) {
        return 7;
      }
      if (((MainMenuFragment.RowItem)getItem(paramInt)).getItemType() == ApplicationData.ListItemType.SeasonedCrossroadsCommunity) {
        return 8;
      }
      return 2;
    }
    
    public ArrayList<MainMenuFragment.RowItem> getItems()
    {
      return this.rowItems;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      MainMenuFragment.ViewHolder localViewHolder = new MainMenuFragment.ViewHolder(null);
      int i = getItemViewType(paramInt);
      MainMenuFragment.RowItem localRowItem = (MainMenuFragment.RowItem)getItem(paramInt);
      Object localObject1;
      ImageView localImageView;
      LinearLayout localLinearLayout;
      Object localObject2;
      label576:
      try
      {
        if (this.mViewInflater != null) {
          break label1312;
        }
        this.mViewInflater = ((LayoutInflater)MainMenuFragment.this.baseActivity.getSystemService("layout_inflater"));
      }
      catch (Exception paramViewGroup) {}
      if (i == 3)
      {
        paramViewGroup = this.mViewInflater.inflate(2131493066, paramViewGroup, false);
        paramView = paramViewGroup;
        try
        {
          localViewHolder.clientName = ((TextView)paramViewGroup.findViewById(2131296458));
          paramView = paramViewGroup;
          paramViewGroup.setTag(localViewHolder);
          paramView = paramViewGroup;
        }
        catch (Exception paramViewGroup)
        {
          break label1203;
        }
      }
      else
      {
        if ((i != 4) && (i != 5))
        {
          if ((i != 6) && (i != 7) && (i != 8)) {
            break label1214;
          }
          if (i == 7) {
            paramInt = 2131493029;
          } else {
            paramInt = 2131493030;
          }
          paramViewGroup = this.mViewInflater.inflate(paramInt, paramViewGroup, false);
          try
          {
            paramView = (TextView)paramViewGroup.findViewById(2131297425);
            localObject1 = (ImageView)paramViewGroup.findViewById(2131296720);
            localImageView = (ImageView)paramViewGroup.findViewById(2131296727);
            localViewHolder.title = paramView;
            localViewHolder.icon = ((ImageView)localObject1);
            if (localRowItem != null)
            {
              localViewHolder.title.setText(localRowItem.getTitle());
              localViewHolder.icon.setImageResource(localRowItem.getIconResId());
              localViewHolder.secondaryIcon = localImageView;
              localViewHolder.secondaryIcon.setOnClickListener(new -..Lambda.MainMenuFragment.MenuAdapter.SphZcYB3Jw2Y3NVU96UP_rTAGsQ(this, i));
            }
            localViewHolder.title.setTypeface(null, 1);
            if (i == 8) {
              localViewHolder.secondaryIcon.setVisibility(4);
            }
            paramViewGroup.setTag(localViewHolder);
            paramView = paramViewGroup;
          }
          catch (Exception localException1)
          {
            paramView = paramViewGroup;
            paramViewGroup = localException1;
            break label1203;
          }
        }
        paramViewGroup = this.mViewInflater.inflate(2131493065, paramViewGroup, false);
        paramView = paramViewGroup;
        localLinearLayout = (LinearLayout)paramViewGroup.findViewById(2131296830);
        paramView = paramViewGroup;
        localObject1 = (TextView)paramViewGroup.findViewById(2131296827);
        paramView = paramViewGroup;
        localImageView = (ImageView)paramViewGroup.findViewById(2131296825);
        paramView = paramViewGroup;
        localObject2 = paramViewGroup.findViewById(2131297552);
        paramView = paramViewGroup;
        localViewHolder.clientName = ((TextView)localObject1);
        paramView = paramViewGroup;
        localViewHolder.layout = localLinearLayout;
        paramView = paramViewGroup;
        localViewHolder.icon = localImageView;
        if (i == 5)
        {
          paramView = paramViewGroup;
          localLinearLayout.setBackgroundColor(this.mSelectedActivityBackground);
        }
        if (i == 5)
        {
          paramView = paramViewGroup;
          ((View)localObject2).setVisibility(0);
        }
        else
        {
          paramView = paramViewGroup;
          ((View)localObject2).setVisibility(4);
        }
        if (i == 4)
        {
          paramView = paramViewGroup;
          paramInt = MainMenuFragment.this.getResources().getColor(2131099802);
        }
        else
        {
          paramView = paramViewGroup;
          paramInt = MainMenuFragment.this.getResources().getColor(2131099746);
        }
        paramView = paramViewGroup;
        localImageView.setColorFilter(paramInt, PorterDuff.Mode.SRC_IN);
        paramView = paramViewGroup;
        paramViewGroup.setTag(localViewHolder);
        paramView = paramViewGroup;
        if (MainMenuFragment.this.getArguments() != null)
        {
          paramView = paramViewGroup;
          if (MainMenuFragment.this.getArguments().getBoolean(MainMenuFragment.ARG_IS_SECONDARY))
          {
            paramView = paramViewGroup;
            paramViewGroup.findViewById(2131296827).setVisibility(4);
            paramView = paramViewGroup;
            break label1214;
          }
        }
        paramView = paramViewGroup;
        paramViewGroup.findViewById(2131296827).setVisibility(0);
        paramView = paramViewGroup;
        break label1214;
        paramViewGroup = this.mViewInflater.inflate(2131493064, paramViewGroup, false);
      }
      for (;;)
      {
        try
        {
          paramView = (LinearLayout)paramViewGroup.findViewById(2131296826);
          localImageView = (ImageView)paramViewGroup.findViewById(2131296825);
          localObject1 = paramViewGroup.findViewById(2131297552);
          localObject2 = (TextView)paramViewGroup.findViewById(2131296827);
          localViewHolder.layout = paramView;
          localViewHolder.icon = localImageView;
          localViewHolder.title = ((TextView)localObject2);
          if ((MainMenuFragment.this.getArguments() != null) && (MainMenuFragment.this.getArguments().getBoolean(MainMenuFragment.ARG_IS_SECONDARY))) {
            localViewHolder.title.setVisibility(4);
          } else {
            localViewHolder.title.setVisibility(0);
          }
          paramView = "";
          if (localLinearLayout == null) {
            break label1336;
          }
          if ((MainMenuFragment.RowItem.access$600(localLinearLayout) != ApplicationActivity.SeasonedManagerOpenJobs) && (MainMenuFragment.RowItem.access$600(localLinearLayout) != ApplicationActivity.SeasonedManagerApplicants) && (MainMenuFragment.RowItem.access$600(localLinearLayout) != ApplicationActivity.SeasonedManagerInterviews) && (MainMenuFragment.RowItem.access$600(localLinearLayout) != ApplicationActivity.SeasonedManagerGetReferrals) && (MainMenuFragment.RowItem.access$600(localLinearLayout) != ApplicationActivity.SeasonedEmployeeReferAFriend))
          {
            paramView = localLinearLayout.getTitle();
            bool = true;
          }
          else
          {
            if (MainMenuFragment.this.realm.j())
            {
              MainMenuFragment.access$102(MainMenuFragment.this, Realm.m());
              MainMenuFragment.access$202(MainMenuFragment.this, (SeasonedClientInfo)MainMenuFragment.this.realm.a(SeasonedClientInfo.class).a("id", Long.valueOf(ApplicationData.getInstance().getUserIdLong())).e());
            }
            if (MainMenuFragment.this.seasonedClientInfo == null) {
              break label1336;
            }
          }
          switch (MainMenuFragment.5.$SwitchMap$com$tdr3$hs$android2$core$ApplicationActivity[MainMenuFragment.RowItem.access$600(localLinearLayout).ordinal()])
          {
          case 4: 
            paramView = localLinearLayout.getTitle();
            continue;
            paramView = localLinearLayout.getTitle();
            if ((!MainMenuFragment.this.seasonedClientInfo.getProvisioned()) || (MainMenuFragment.this.seasonedClientInfo.getOpenJobCount() <= 0)) {
              break label1330;
            }
            bool = true;
            break;
          case 3: 
            paramView = localLinearLayout.getTitleWithValue(MainMenuFragment.this.seasonedClientInfo.getInterviewCount());
            bool = MainMenuFragment.this.seasonedClientInfo.getProvisioned();
            break;
          case 2: 
            paramView = localLinearLayout.getTitleWithValue(MainMenuFragment.this.seasonedClientInfo.getApplicantCount());
            bool = MainMenuFragment.this.seasonedClientInfo.getProvisioned();
            break;
          case 1: 
            paramView = localLinearLayout.getTitleWithValue(MainMenuFragment.this.seasonedClientInfo.getOpenJobCount());
            bool = true;
            continue;
            bool = MainMenuFragment.this.seasonedClientInfo.getProvisioned();
            localViewHolder.title.setText(paramView);
            if (bool) {
              localViewHolder.title.setTextColor(androidx.core.content.a.c(localViewHolder.title.getContext(), 17170444));
            } else {
              localViewHolder.title.setTextColor(androidx.core.content.a.c(localViewHolder.title.getContext(), 2131099812));
            }
            if (localLinearLayout != null) {
              localViewHolder.icon.setImageResource(localLinearLayout.getIconResId());
            }
            localViewHolder.icon.setColorFilter(MainMenuFragment.this.getResources().getColor(2131099802), PorterDuff.Mode.SRC_IN);
            if (i == 1)
            {
              ((View)localObject1).setVisibility(0);
              localViewHolder.title.setTypeface(null, 1);
            }
            else
            {
              ((View)localObject1).setVisibility(4);
              localViewHolder.title.setTypeface(null, 0);
            }
            paramViewGroup.setTag(localViewHolder);
            paramView = paramViewGroup;
          }
        }
        catch (Exception localException2)
        {
          paramView = paramViewGroup;
          paramViewGroup = localException2;
        }
        label1203:
        Log.e(MainMenuFragment.TAG, paramViewGroup.getMessage());
        label1214:
        if (i == 1) {}
        try
        {
          localViewHolder.layout.setBackgroundColor(this.mSelectedActivityBackground);
          localViewHolder.icon.setColorFilter(MainMenuFragment.this.getResources().getColor(2131099746), PorterDuff.Mode.SRC_IN);
          localViewHolder.title.setTextColor(MainMenuFragment.this.getResources().getColor(2131099746));
          return paramView;
        }
        catch (Exception paramViewGroup)
        {
          for (;;) {}
        }
        if (i == 3)
        {
          localViewHolder.clientName.setText(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_NAME"));
          return paramView;
          Log.e(MainMenuFragment.TAG, paramViewGroup.getMessage());
        }
        return paramView;
        label1312:
        if (i == 1) {
          break label576;
        }
        if (i != 2) {
          break;
        }
        break label576;
        continue;
        label1330:
        boolean bool = false;
        continue;
        label1336:
        bool = true;
      }
    }
    
    public int getViewTypeCount()
    {
      return 8;
    }
  }
  
  private static class RowItem
  {
    private String deepLinkData;
    private ApplicationActivity mApplicationActivity;
    private int mIconRes;
    private ApplicationData.ListItemType mItemType;
    private String overrideTitle;
    private String subtitle;
    
    public RowItem(ApplicationActivity paramApplicationActivity, int paramInt, ApplicationData.ListItemType paramListItemType)
    {
      this.mIconRes = paramInt;
      this.mItemType = paramListItemType;
      this.mApplicationActivity = paramApplicationActivity;
    }
    
    public RowItem(ApplicationActivity paramApplicationActivity, int paramInt, ApplicationData.ListItemType paramListItemType, String paramString1, String paramString2, String paramString3)
    {
      this(paramApplicationActivity, paramInt, paramListItemType);
      this.overrideTitle = paramString1;
      this.subtitle = paramString2;
      this.deepLinkData = paramString3;
    }
    
    public RowItem(ApplicationActivity paramApplicationActivity, String paramString, ApplicationData.ListItemType paramListItemType)
    {
      this.mItemType = paramListItemType;
      this.mApplicationActivity = paramApplicationActivity;
    }
    
    public ApplicationActivity getApplicationActivity()
    {
      return this.mApplicationActivity;
    }
    
    public String getDeepLinkData()
    {
      return this.deepLinkData;
    }
    
    public int getIconResId()
    {
      return this.mIconRes;
    }
    
    public ApplicationData.ListItemType getItemType()
    {
      return this.mItemType;
    }
    
    public String getSubtitle()
    {
      return this.subtitle;
    }
    
    public String getTitle()
    {
      try
      {
        if (this.overrideTitle == null) {
          return ApplicationActivity.getPrettyString(this.mApplicationActivity);
        }
        String str = this.overrideTitle;
        return str;
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Main Menu Fragment: Error getting title");
        localStringBuilder.append(localException.getMessage());
        HsLog.e(localStringBuilder.toString());
        localException.printStackTrace();
      }
      return "Unknown";
    }
    
    public String getTitleWithValue(int paramInt)
    {
      try
      {
        String str = ApplicationActivity.getPrettyStringWithArg(this.mApplicationActivity, paramInt);
        return str;
      }
      catch (Exception localException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Main Menu Fragment: Error getting title");
        localStringBuilder.append(localException.getMessage());
        HsLog.e(localStringBuilder.toString());
        localException.printStackTrace();
      }
      return "Unknown";
    }
    
    public void setItemType(ApplicationData.ListItemType paramListItemType)
    {
      this.mItemType = paramListItemType;
    }
  }
  
  private static class ViewHolder
  {
    public TextView clientName;
    public ImageView icon;
    public LinearLayout layout;
    public ImageView secondaryIcon;
    public TextView title;
    
    private ViewHolder() {}
  }
}
