package com.tdr3.hs.android2.fragments.proforma;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.b;
import butterknife.BindView;
import com.tdr3.hs.android.HSApp;
import com.tdr3.hs.android.HSApp.TrackerType;
import com.tdr3.hs.android.data.api.AuthenticationModel;
import com.tdr3.hs.android.data.api.ScheduleModel;
import com.tdr3.hs.android.data.db.clientData.Store;
import com.tdr3.hs.android.data.local.login.LoginData;
import com.tdr3.hs.android.data.local.proforma.pojo.ProformaResponse;
import com.tdr3.hs.android.data.local.schedule.EmployeeSchedule;
import com.tdr3.hs.android.data.local.schedule.Week;
import com.tdr3.hs.android.ui.BaseActivity;
import com.tdr3.hs.android.ui.main.MainActivity;
import com.tdr3.hs.android2.abstraction.HSFragment;
import com.tdr3.hs.android2.core.ApplicationActivity;
import com.tdr3.hs.android2.core.ApplicationCache;
import com.tdr3.hs.android2.core.ApplicationData;
import com.tdr3.hs.android2.core.ScheduleData;
import com.tdr3.hs.android2.core.SharedPreferencesManager;
import com.tdr3.hs.android2.core.Util;
import com.tdr3.hs.android2.core.api.HSApi;
import com.tdr3.hs.android2.models.PartnerApplication;
import com.tdr3.hs.android2.models.Proforma;
import com.tdr3.hs.android2.models.ProformaCategoryData;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subscribers.DisposableSubscriber;
import io.realm.Realm;
import io.realm.ad;
import io.realm.ae;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@SuppressLint({"ResourceAsColor"})
public class ProformaFragment
  extends HSFragment
  implements DatePickerDialog.OnDateSetListener
{
  private static final String TAG = "ProformaFragment";
  static boolean mHasScheduleData = false;
  private static boolean mIsDateRangeDirty = false;
  @Inject
  HSApi api;
  @Inject
  AuthenticationModel authenticationModel;
  private CompositeDisposable compositeDisposable;
  private DateTimeFormatter dateFormatter;
  private LocalDate endDateTime;
  boolean isStartDatePicker;
  DatePickerDialog mDatePickerDialog;
  @BindView(2131296595)
  TextView mEndDate;
  @BindView(2131296596)
  LinearLayout mEndDateLayout;
  boolean mProcessingDataUpdate = false;
  private Proforma mProformaSearch;
  private View mRootView = null;
  @BindView(2131297233)
  TextView mStartDate;
  @BindView(2131297234)
  LinearLayout mStartDateLayout;
  @BindView(2131297240)
  LinearLayout mStoreLayout;
  @BindView(2131297239)
  TextView mStoreName;
  @BindView(2131297251)
  SwipeRefreshLayout mSwipeRefreshLayout;
  private Realm realm;
  @Inject
  ScheduleModel scheduleModel;
  private LocalDate startDateTime;
  
  public ProformaFragment() {}
  
  private void getScheduleData(LocalDate paramLocalDate)
  {
    this.mProcessingDataUpdate = true;
    this.compositeDisposable.a((Disposable)this.scheduleModel.loadEmployeeScheduleData(paramLocalDate, paramLocalDate.plusMonths(6), ApplicationData.getInstance().getUserIdLong()).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).c(new DisposableSubscriber()
    {
      public void onComplete() {}
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if ((!(paramAnonymousThrowable instanceof ConnectException)) && (!(paramAnonymousThrowable instanceof SocketTimeoutException)))
        {
          ProformaFragment.this.showErrorDialog(2131820898, paramAnonymousThrowable.getLocalizedMessage());
          return;
        }
        ProformaFragment.this.showErrorDialog(2131821307, 2131821305);
      }
      
      public void onNext(EmployeeSchedule paramAnonymousEmployeeSchedule)
      {
        ProformaFragment.mHasScheduleData = true;
        ProformaFragment.access$302(ProformaFragment.this, new LocalDate().minusDays(1));
        ProformaFragment.access$402(ProformaFragment.this, new LocalDate());
        paramAnonymousEmployeeSchedule = ScheduleData.getInstance().getWeeks().iterator();
        while (paramAnonymousEmployeeSchedule.hasNext())
        {
          Week localWeek = (Week)paramAnonymousEmployeeSchedule.next();
          ProformaFragment.mHasScheduleData = true;
          if (localWeek.getLastDate().isAfter(ProformaFragment.this.startDateTime))
          {
            ProformaFragment.access$302(ProformaFragment.this, localWeek.getWeekStart());
            ProformaFragment.access$402(ProformaFragment.this, localWeek.getLastDate());
          }
        }
        if ((ProformaFragment.this.mStartDate != null) && (ProformaFragment.this.mEndDate != null))
        {
          ProformaFragment.this.setStartDate();
          ProformaFragment.this.setEndDate();
          paramAnonymousEmployeeSchedule = ProformaFragment.this;
          ProformaFragment.access$702(paramAnonymousEmployeeSchedule, new Proforma(paramAnonymousEmployeeSchedule.startDateTime, ProformaFragment.this.endDateTime, Long.parseLong(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_ID"))));
          paramAnonymousEmployeeSchedule = ProformaFragment.this;
          paramAnonymousEmployeeSchedule.loadLaborReport(paramAnonymousEmployeeSchedule.mProformaSearch);
        }
        ProformaFragment.this.mProcessingDataUpdate = false;
      }
    }));
  }
  
  private void initSwipeLayout()
  {
    this.mSwipeRefreshLayout.setColorSchemeColors(getResources().getIntArray(2130903063));
    this.mSwipeRefreshLayout.a(false, 0, (int)TypedValue.applyDimension(1, 24.0F, getResources().getDisplayMetrics()));
    this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.b()
    {
      public void onRefresh()
      {
        if (!Util.haveNetworkConnection(ProformaFragment.this.mContext))
        {
          ProformaFragment.this.mSwipeRefreshLayout.setRefreshing(false);
          Util.updateErrorUI(ProformaFragment.this.mContext, ProformaFragment.this.getView(), true);
          return;
        }
        ProformaFragment.this.loadData();
      }
    });
  }
  
  private void initView()
  {
    this.mRootView = getView();
    if ((this.startDateTime == null) || (this.endDateTime == null))
    {
      this.startDateTime = new LocalDate().minusDays(1);
      this.endDateTime = new LocalDate();
      Iterator localIterator = ScheduleData.getInstance().getWeeks().iterator();
      while (localIterator.hasNext())
      {
        Week localWeek = (Week)localIterator.next();
        mHasScheduleData = true;
        if (localWeek.getLastDate().isAfter(this.startDateTime))
        {
          this.startDateTime = localWeek.getWeekStart();
          this.endDateTime = localWeek.getLastDate();
        }
      }
      if (!mHasScheduleData) {
        getScheduleData(new LocalDate());
      }
    }
    this.mProformaSearch = new Proforma(this.startDateTime, this.endDateTime, Long.parseLong(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_ID")));
    setStartDate();
    setEndDate();
    this.mStoreName.setText(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_NAME"));
    if (SharedPreferencesManager.getBooleanPreference("PREF_IS_MULTI_STORE_USER", false)) {
      this.mStoreLayout.setVisibility(0);
    } else {
      this.mStoreLayout.setVisibility(8);
    }
    this.mStoreLayout.setOnClickListener(new -..Lambda.ProformaFragment.bOU-rkKZ3TX3-TyEIDAZZe45o3A(this));
    this.mStartDateLayout.setOnClickListener(new -..Lambda.ProformaFragment.TFpUFmgebUDft9o6DYdyi5A1stE(this));
    this.mEndDateLayout.setOnClickListener(new -..Lambda.ProformaFragment.2d5SvZkFfEdhyaFsYPNMRh6Qnlk(this));
    mIsDateRangeDirty = true;
  }
  
  private void loadData()
  {
    this.mProformaSearch = new Proforma(this.startDateTime, this.endDateTime, Long.parseLong(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_ID")));
    loadLaborReport(this.mProformaSearch);
  }
  
  private void loadLaborReport(Proforma paramProforma)
  {
    this.mSwipeRefreshLayout.setRefreshing(true);
    DateTimeFormatter localDateTimeFormatter = DateTimeFormat.forPattern("MM.dd.YYYY");
    this.compositeDisposable.a((Disposable)this.api.getProformaLaborReport(paramProforma.getClientId(), localDateTimeFormatter.print(paramProforma.getStartDate()), localDateTimeFormatter.print(paramProforma.getEndDate())).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).b(new DisposableObserver()
    {
      public void onComplete() {}
      
      public void onError(Throwable paramAnonymousThrowable) {}
      
      public void onNext(ProformaResponse paramAnonymousProformaResponse)
      {
        ProformaFragment.access$1302(false);
        Proforma localProforma = paramAnonymousProformaResponse.getProforma(ProformaFragment.this.getString(2131821875));
        paramAnonymousProformaResponse = SharedPreferencesManager.getStringPreference("PREF_USER_CURRENCY_CODE");
        if (paramAnonymousProformaResponse != null) {
          paramAnonymousProformaResponse = Currency.getInstance(paramAnonymousProformaResponse);
        } else {
          paramAnonymousProformaResponse = Currency.getInstance(Locale.getDefault());
        }
        NumberFormat localNumberFormat = NumberFormat.getInstance(Locale.getDefault());
        Object localObject2 = NumberFormat.getCurrencyInstance(Locale.getDefault());
        ((NumberFormat)localObject2).setCurrency(paramAnonymousProformaResponse);
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131296973)).setText(((NumberFormat)localObject2).format(localProforma.getProjectedSales()));
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131296299)).setText(((NumberFormat)localObject2).format(localProforma.getActualSales()));
        Object localObject1 = (TextView)ProformaFragment.this.mRootView.findViewById(2131297544);
        if (localProforma.getVarianceSales() >= 0.0D)
        {
          localObject1 = (TextView)ProformaFragment.this.mRootView.findViewById(2131297544);
        }
        else
        {
          ((TextView)localObject1).setVisibility(8);
          localObject1 = (TextView)ProformaFragment.this.mRootView.findViewById(2131297543);
        }
        ((TextView)localObject1).setText(((NumberFormat)localObject2).format(localProforma.getVarianceSales()));
        ((TextView)localObject1).setVisibility(0);
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131297324)).setText(paramAnonymousProformaResponse.getCurrencyCode());
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131297099)).setText(localNumberFormat.format(localProforma.getScheduledHours()));
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131297098)).setText(((NumberFormat)localObject2).format(localProforma.getScheduledDollars()));
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131296298)).setText(localNumberFormat.format(localProforma.getActualHours()));
        ((TextView)ProformaFragment.this.mRootView.findViewById(2131296297)).setText(((NumberFormat)localObject2).format(localProforma.getActualDollars()));
        paramAnonymousProformaResponse = (TextView)ProformaFragment.this.mRootView.findViewById(2131297540);
        localObject1 = (TextView)ProformaFragment.this.mRootView.findViewById(2131297538);
        if (localProforma.getVarianceHours() >= 0.0D)
        {
          paramAnonymousProformaResponse = (TextView)ProformaFragment.this.mRootView.findViewById(2131297540);
        }
        else
        {
          paramAnonymousProformaResponse.setVisibility(8);
          paramAnonymousProformaResponse = (TextView)ProformaFragment.this.mRootView.findViewById(2131297539);
        }
        paramAnonymousProformaResponse.setText(localNumberFormat.format(localProforma.getVarianceHours()));
        paramAnonymousProformaResponse.setVisibility(0);
        if (localProforma.getVarianceDollars() >= 0.0D)
        {
          paramAnonymousProformaResponse = (TextView)ProformaFragment.this.mRootView.findViewById(2131297538);
        }
        else
        {
          ((TextView)localObject1).setVisibility(8);
          paramAnonymousProformaResponse = (TextView)ProformaFragment.this.mRootView.findViewById(2131297537);
        }
        paramAnonymousProformaResponse.setText(((NumberFormat)localObject2).format(localProforma.getVarianceDollars()));
        paramAnonymousProformaResponse.setVisibility(0);
        LayoutInflater localLayoutInflater = ProformaFragment.this.getActivity().getLayoutInflater();
        localObject1 = (TableLayout)ProformaFragment.this.mRootView.findViewById(2131296770);
        ((TableLayout)localObject1).removeViews(1, ((TableLayout)localObject1).getChildCount() - 1);
        paramAnonymousProformaResponse = localProforma.getProformaLaborPercentSummary().iterator();
        while (paramAnonymousProformaResponse.hasNext())
        {
          Object localObject3 = (ProformaCategoryData)paramAnonymousProformaResponse.next();
          TableRow localTableRow1 = (TableRow)localLayoutInflater.inflate(2131493101, null);
          TableRow localTableRow2 = (TableRow)localLayoutInflater.inflate(2131493100, null);
          Object localObject5 = (TextView)localTableRow1.findViewById(2131297465);
          Object localObject4 = (TextView)localTableRow1.findViewById(2131297100);
          TextView localTextView = (TextView)localTableRow1.findViewById(2131296300);
          double d = ((ProformaCategoryData)localObject3).getVariance();
          localObject2 = (TextView)localTableRow1.findViewById(2131297542);
          if (d < 0.0D)
          {
            ((TextView)localObject2).setVisibility(8);
            localObject2 = (TextView)localTableRow1.findViewById(2131297541);
          }
          ((TextView)localObject5).setText(((ProformaCategoryData)localObject3).getName());
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append(localNumberFormat.format(((ProformaCategoryData)localObject3).getScheduledLaborPercent()));
          ((StringBuilder)localObject5).append("%");
          ((TextView)localObject4).setText(((StringBuilder)localObject5).toString());
          localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append(localNumberFormat.format(((ProformaCategoryData)localObject3).getActualLaborPercent()));
          ((StringBuilder)localObject4).append("%");
          localTextView.setText(((StringBuilder)localObject4).toString());
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(localNumberFormat.format(d));
          ((StringBuilder)localObject3).append("%");
          ((TextView)localObject2).setText(((StringBuilder)localObject3).toString());
          ((TextView)localObject2).setVisibility(0);
          ((TableLayout)localObject1).addView(localTableRow1);
          ((TableLayout)localObject1).addView(localTableRow2);
        }
        if (localProforma.getProformaLaborPercentSummary().size() != 0)
        {
          ProformaFragment.this.mSwipeRefreshLayout.setRefreshing(false);
          Util.updateErrorUI(ProformaFragment.this.mContext, ProformaFragment.this.mRootView, false);
        }
      }
    }));
  }
  
  private void openStoreSelectorDialog()
  {
    Object localObject1 = SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_ID");
    if (this.realm.j()) {
      this.realm = Realm.m();
    }
    Object localObject2 = this.realm.a(Store.class).b("name").d();
    CharSequence[] arrayOfCharSequence = new CharSequence[((ae)localObject2).size()];
    int i = 0;
    int k;
    for (int j = 0; i < ((ae)localObject2).size(); j = k)
    {
      arrayOfCharSequence[i] = ((Store)((ae)localObject2).get(i)).getName();
      k = j;
      if (!TextUtils.isEmpty((CharSequence)localObject1))
      {
        k = j;
        if (Long.parseLong((String)localObject1) == ((Store)((ae)localObject2).get(i)).getId()) {
          k = i;
        }
      }
      i += 1;
    }
    localObject1 = (LinearLayout)getLayoutInflater().inflate(2131493180, null);
    localObject2 = (TextView)((LinearLayout)localObject1).findViewById(2131297326);
    ((TextView)localObject2).setText(getString(2131821623, new Object[] { SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_NAME") }));
    new AlertDialog.Builder(getContext()).setCustomTitle((View)localObject1).setSingleChoiceItems(arrayOfCharSequence, j, new -..Lambda.ProformaFragment.IaGn2mtspShMK3Xz_-Vw0zwph68(this, (TextView)localObject2, arrayOfCharSequence)).setCancelable(false).setPositiveButton(17039370, new -..Lambda.ProformaFragment.-NYwNQ9Kv1dkudw8aESl5dIfz38(this)).setNegativeButton(17039360, null).show();
  }
  
  private void setEndDate()
  {
    this.mEndDate.setText(this.dateFormatter.print(this.endDateTime.toDateTimeAtStartOfDay(Util.getStoreTimeZone())));
  }
  
  private void setStartDate()
  {
    this.mStartDate.setText(this.dateFormatter.print(this.startDateTime.toDateTimeAtStartOfDay(Util.getStoreTimeZone())));
  }
  
  private void switchStores(int paramInt)
  {
    ApplicationCache.getInstance().setSelectedMessagesFolder(0);
    DisposableObserver local3 = new DisposableObserver()
    {
      public void onComplete()
      {
        ProformaFragment.this.mSwipeRefreshLayout.setRefreshing(false);
        ProformaFragment.this.baseActivity.hideProgress();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (ProformaFragment.this.mSwipeRefreshLayout.b()) {
          ProformaFragment.this.mSwipeRefreshLayout.setRefreshing(false);
        }
        ProformaFragment.this.baseActivity.hideProgress();
        new AlertDialog.Builder(ProformaFragment.this.mContext).setTitle(2131821191).setMessage(paramAnonymousThrowable.getMessage()).show();
      }
      
      public void onNext(LoginData paramAnonymousLoginData)
      {
        paramAnonymousLoginData = ProformaFragment.this;
        paramAnonymousLoginData.updateInstalledPartnerApps(paramAnonymousLoginData.mContext);
        paramAnonymousLoginData = SharedPreferencesManager.getStringPreference("PREF_HOME_ACTIVITY");
        if (paramAnonymousLoginData == null) {
          ApplicationData.getInstance().setCurrentActivity(ApplicationActivity.MySchedule);
        }
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
        if ((ProformaFragment.this.getActivity() instanceof MainActivity)) {
          ((MainActivity)ProformaFragment.this.getActivity()).reloadMenu();
        }
        paramAnonymousLoginData = ProformaFragment.this;
        ProformaFragment.access$702(paramAnonymousLoginData, new Proforma(paramAnonymousLoginData.startDateTime, ProformaFragment.this.endDateTime, Long.parseLong(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_ID"))));
        paramAnonymousLoginData = ProformaFragment.this;
        paramAnonymousLoginData.loadLaborReport(paramAnonymousLoginData.mProformaSearch);
        ProformaFragment.this.mStoreName.setText(SharedPreferencesManager.getStringPreference("PREF_STORE_CLIENT_NAME"));
      }
    };
    this.baseActivity.showProgress();
    if (this.realm.j()) {
      this.realm = Realm.m();
    }
    Object localObject1 = this.realm.a(Store.class).b("name").d();
    String str = String.valueOf(((Store)((ae)localObject1).get(paramInt)).getId());
    SharedPreferencesManager.setPreference("PREF_ASC_SELECTED_STORE_ID", str);
    if (!TextUtils.isEmpty(str))
    {
      localObject1 = ((ae)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Store)((Iterator)localObject1).next();
        if (((Store)localObject2).getId() == Long.parseLong(str))
        {
          SharedPreferencesManager.setPreference("PREF_STORE_CLIENT_ID", String.valueOf(((Store)localObject2).getId()));
          SharedPreferencesManager.setPreference("PREF_STORE_CLIENT_NAME", ((Store)localObject2).getName());
        }
      }
    }
    Object localObject2 = SharedPreferencesManager.getStringPreference("PREF_TOKEN");
    localObject1 = Observable.b();
    if (!TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = this.authenticationModel.authenticateWithToken((String)localObject2, str);
    }
    this.compositeDisposable.a((Disposable)((Observable)localObject1).b(io.reactivex.e.a.b()).a(io.reactivex.a.b.a.a()).b(local3));
  }
  
  DatePickerDialog getEndDatePickerDialog(int paramInt1, int paramInt2, int paramInt3)
  {
    DatePickerDialog localDatePickerDialog = new DatePickerDialog(this.mContext, this, paramInt1, paramInt2, paramInt3);
    localDatePickerDialog.setTitle("");
    this.isStartDatePicker = false;
    return localDatePickerDialog;
  }
  
  DatePickerDialog getStartDatePickerDialog(int paramInt1, int paramInt2, int paramInt3)
  {
    DatePickerDialog localDatePickerDialog = new DatePickerDialog(this.mContext, this, paramInt1, paramInt2, paramInt3);
    localDatePickerDialog.setTitle("");
    this.isStartDatePicker = true;
    return localDatePickerDialog;
  }
  
  public void onAttach(Context paramContext)
  {
    dagger.android.support.a.a(this);
    super.onAttach(paramContext);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = this.mDatePickerDialog;
    if ((paramConfiguration != null) && (paramConfiguration.isShowing()))
    {
      paramConfiguration = this.mDatePickerDialog.getDatePicker();
      int i = paramConfiguration.getYear();
      int j = paramConfiguration.getMonth();
      int k = paramConfiguration.getDayOfMonth();
      this.mDatePickerDialog.dismiss();
      if (this.isStartDatePicker) {
        this.mDatePickerDialog = getStartDatePickerDialog(i, j, k);
      } else {
        this.mDatePickerDialog = getEndDatePickerDialog(i, j, k);
      }
      this.mDatePickerDialog.show();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    HSApp.sendGAScreenView(HSApp.TrackerType.HS, "Proforma Screen");
    this.compositeDisposable = new CompositeDisposable();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.realm = Realm.m();
    return paramLayoutInflater.inflate(2131493102, paramViewGroup, false);
  }
  
  public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDatePicker = new LocalDate(paramInt1, paramInt2 + 1, paramInt3);
    if (this.isStartDatePicker)
    {
      if (paramDatePicker.compareTo(this.startDateTime) != 0) {
        mIsDateRangeDirty = true;
      }
      if (paramDatePicker.isAfter(this.endDateTime)) {
        this.endDateTime = paramDatePicker;
      }
      this.startDateTime = paramDatePicker;
    }
    else
    {
      if (paramDatePicker.compareTo(this.endDateTime) != 0) {
        mIsDateRangeDirty = true;
      }
      if (paramDatePicker.isBefore(this.startDateTime)) {
        this.startDateTime = paramDatePicker;
      }
      this.endDateTime = paramDatePicker;
    }
    if ((mIsDateRangeDirty) && (mHasScheduleData))
    {
      loadData();
      setStartDate();
      setEndDate();
    }
  }
  
  public void onPause()
  {
    super.onPause();
    mIsDateRangeDirty = false;
  }
  
  public void onResume()
  {
    super.onResume();
    if ((this.mStartDate != null) && (this.startDateTime != null)) {
      setStartDate();
    }
    if ((this.mEndDate != null) && (this.endDateTime != null)) {
      setEndDate();
    }
    if ((mIsDateRangeDirty) && (mHasScheduleData)) {
      loadData();
    }
    ApplicationData.getInstance().pushFragmentVisitedHistory(Util.getFragment(ApplicationActivity.Proforma, this.mContext), ApplicationActivity.Proforma);
  }
  
  public void onStop()
  {
    super.onStop();
    this.compositeDisposable.a();
    if (!this.realm.j()) {
      this.realm.close();
    }
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.dateFormatter = DateTimeFormat.forPattern(getString(2131820824, new Object[] { DateTimeFormat.patternForStyle("M-", Locale.getDefault()) }));
    initView();
    initSwipeLayout();
  }
  
  public void showErrorDialog(int paramInt1, int paramInt2)
  {
    new AlertDialog.Builder(getContext()).setTitle(paramInt1).setMessage(paramInt2).setPositiveButton(17039370, null).show();
  }
  
  public void showErrorDialog(int paramInt, String paramString)
  {
    new AlertDialog.Builder(getContext()).setTitle(paramInt).setMessage(paramString).setPositiveButton(17039370, null).show();
  }
  
  public void updateInstalledPartnerApps(Context paramContext)
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
}
