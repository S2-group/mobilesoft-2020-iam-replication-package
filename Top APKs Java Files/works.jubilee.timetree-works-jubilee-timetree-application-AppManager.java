package works.jubilee.timetree.application;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.WindowManager;
import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import de.greenrobot.event.EventBus;
import io.reactivex.Completable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;
import timber.log.Timber.Tree;
import works.jubilee.timetree.constant.MemorialdayLocaleType;
import works.jubilee.timetree.constant.ReminderMenu;
import works.jubilee.timetree.constant.eventbus.EBKey;
import works.jubilee.timetree.db.DatabaseOpenHelper;
import works.jubilee.timetree.db.OvenCalendar;
import works.jubilee.timetree.db.OvenProperty;
import works.jubilee.timetree.model.CalendarUserModel;
import works.jubilee.timetree.model.DeviceModel;
import works.jubilee.timetree.model.LocalUser;
import works.jubilee.timetree.model.LocalUserModel;
import works.jubilee.timetree.model.MergedCalendarModel;
import works.jubilee.timetree.model.Models;
import works.jubilee.timetree.model.NewsInfo;
import works.jubilee.timetree.model.OvenCalendarModel;
import works.jubilee.timetree.model.OvenPropertyModel;
import works.jubilee.timetree.net.RequestManager;
import works.jubilee.timetree.repository.setting.SettingLocalDataSource;
import works.jubilee.timetree.repository.setting.SettingRepository;
import works.jubilee.timetree.ui.common.SelectTabView.TabType;
import works.jubilee.timetree.ui.globalsetting.LanguageSettingPresenter;
import works.jubilee.timetree.util.AndroidCompatUtils;
import works.jubilee.timetree.util.EventUtils;
import works.jubilee.timetree.util.WidgetUtils;

public class AppManager
{
  private static final List<Integer> DEFAULT_CALENDAR_COLORS = Arrays.asList(new Integer[] { Integer.valueOf(3067015), Integer.valueOf(4047560), Integer.valueOf(4698871), Integer.valueOf(9732216), Integer.valueOf(3553597), Integer.valueOf(15153979), Integer.valueOf(15949708), Integer.valueOf(16482167), Integer.valueOf(16629805), Integer.valueOf(11766748) });
  private static final int UPDATE_NOTICE_VERSION = 16;
  private static AppManager mInstance;
  private List<Integer> mCalendarColors = DEFAULT_CALENDAR_COLORS;
  private boolean mIsReviewEnabled = false;
  private String mLatestAppVersion = getVersionName();
  private NewsInfo mNewsInfo = new NewsInfo();
  private long mNotificationUpdatedAt;
  private long mPublicCalendarBlogUpdatedAt;
  
  private AppManager()
  {
    EventBus.getDefault().register(this);
  }
  
  private String a()
  {
    String str = getInstance().getLanguage();
    if (str.equals(Locale.KOREA.getLanguage())) {
      return str;
    }
    if (str.equals(Locale.JAPAN.getLanguage())) {
      return str;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Locale.TAIWAN.getLanguage());
    localStringBuilder.append("-");
    localStringBuilder.append(Locale.TAIWAN.getCountry());
    if (str.equals(localStringBuilder.toString())) {
      return str;
    }
    return Locale.ENGLISH.getLanguage();
  }
  
  private void a(FragmentActivity paramFragmentActivity, boolean paramBoolean)
  {
    Models.devices().delete(new AppManager.2(this, true, paramFragmentActivity, paramBoolean));
  }
  
  private SharedPreferencesHelper b()
  {
    return OvenApplication.getInstance().getPreferences();
  }
  
  private void c()
  {
    b().apply(PreferencesKeySet.reviewBadge, false);
    b().apply(PreferencesKeySet.receivedReviewEnabled, false);
    if ((getVersionName().equals("1.1.3")) || (b().getInt(PreferencesKeySet.reviewRequestStatus, 0) != 1)) {
      b().apply(PreferencesKeySet.reviewRequestStatus, 0);
    }
  }
  
  public static int compareVersion(String paramString1, String paramString2)
  {
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    int i = 0;
    while ((i < paramString1.length) && (i < paramString2.length) && (paramString1[i].equals(paramString2[i]))) {
      i += 1;
    }
    if ((i < paramString1.length) && (i < paramString2.length)) {
      return Integer.signum(Integer.valueOf(paramString1[i]).compareTo(Integer.valueOf(paramString2[i])));
    }
    return Integer.signum(paramString1.length - paramString2.length);
  }
  
  private void d()
  {
    if (!b().contains(PreferencesKeySet.oldCalendarEnabled))
    {
      String str = getLocale().getLanguage();
      if ((!Locale.KOREA.getLanguage().equals(str)) && (!Locale.CHINA.getLanguage().equals(str)))
      {
        setOldCalendarEnabled(false);
        return;
      }
      setOldCalendarEnabled(true);
    }
  }
  
  private void e()
  {
    RequestManager.getInstance().cancelAllRequest();
    try
    {
      Thread.sleep(3500L);
    }
    catch (InterruptedException localInterruptedException)
    {
      Timber.e(localInterruptedException);
    }
    Models.clearCache();
    Models.clearInstance();
    OvenApplication.getInstance().initAppComponent();
    DatabaseOpenHelper.getInstance().resetAllTables();
    OvenApplication.getInstance().getPreferences().clearPreferences();
    WidgetUtils.refresh();
  }
  
  public static AppManager getInstance()
  {
    if (mInstance == null) {
      try
      {
        if (mInstance == null) {
          mInstance = new AppManager();
        }
      }
      finally {}
    }
    return mInstance;
  }
  
  public Completable clearAllLocalDataCompletable()
  {
    return Completable.fromAction(new -..Lambda.AppManager.6-AxRu4Zv0jNeT2iza0QwkPLn6A(this));
  }
  
  public void clearNotificationNewBadge()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(PreferencesKeySet.notificationUpdatedAt);
    ((StringBuilder)localObject).append("_");
    ((StringBuilder)localObject).append(a());
    localObject = ((StringBuilder)localObject).toString();
    b().apply((String)localObject, this.mNotificationUpdatedAt);
    EventBus.getDefault().post(EBKey.NOTICE_STATUS_UPDATED);
  }
  
  public void clearPublicCalendarBlogNewBadge()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("public_calendar_blog_updated_");
    ((StringBuilder)localObject).append(a());
    localObject = ((StringBuilder)localObject).toString();
    b().apply((String)localObject, this.mPublicCalendarBlogUpdatedAt);
  }
  
  public void clearReviewEventCreateCount()
  {
    b().apply(PreferencesKeySet.reviewEventCreateCount, 0);
  }
  
  public void completeTooltipInvite()
  {
    setTooltipInviteStatus(32);
  }
  
  public void completeTooltipReturnToday()
  {
    setTooltipReturnTodayStatus(8);
  }
  
  public void disableTooltipInvite()
  {
    if (getTooltipInviteStatus() != 16) {
      return;
    }
    setTooltipInviteStatus(32);
  }
  
  public void disableTooltipReturnToday()
  {
    int i = getTooltipReturnTodayStatus();
    if (i != 1)
    {
      if (i != 4) {
        return;
      }
      setTooltipReturnTodayStatus(8);
      return;
    }
    setTooltipReturnTodayStatus(2);
  }
  
  public void doUpgrade()
  {
    String str = b().getString(PreferencesKeySet.lastUsedAppVersion, null);
    if ((str == null) || (compareVersion(getVersionName(), str) > 0))
    {
      c();
      d();
      initializeTodayAlarm();
    }
    if ((str != null) && (compareVersion(getVersionName(), str) > 0))
    {
      if ((compareVersion("1.4.4", str) > 0) && (b().getString(PreferencesKeySet.mergedLocalCalendarDisplayIds, null) == null)) {
        Models.mergedCalendars().initDisplayAllLocalCalendarIds();
      }
      if (compareVersion("2.3.0", str) > 0) {
        OvenApplication.getInstance().getRequestQueue().getCache().clear();
      }
      if (compareVersion("2.3.0", str) > 0)
      {
        this.mNotificationUpdatedAt = b().getLong(PreferencesKeySet.notificationUpdatedAt, 0L);
        clearNotificationNewBadge();
        Models.properties().initSince();
      }
      if (compareVersion("2.5.0", str) > 0)
      {
        Iterator localIterator = Models.ovenCalendars().loadAll().iterator();
        while (localIterator.hasNext())
        {
          OvenCalendar localOvenCalendar = (OvenCalendar)localIterator.next();
          IconBadgeManager.getInstance().setSince(localOvenCalendar.getId().longValue());
        }
      }
      if (compareVersion("3.0.3", str) > 0) {
        Models.calendarUsers().fetchUpdatedObjectsAllForce();
      }
    }
    b().apply(PreferencesKeySet.lastUsedAppVersion, getVersionName());
  }
  
  public void enableDeviceNotificationHelp(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.deviceNotificationHelp, paramBoolean);
  }
  
  public boolean enableTodayPush()
  {
    return b().getInt(PreferencesKeySet.todayPushTime, -1) >= 0;
  }
  
  public boolean enableTooltipInvite()
  {
    int i = getTooltipInviteStatus();
    if (i != 0)
    {
      if (i != 16) {
        return false;
      }
    }
    else {
      setTooltipInviteStatus(16);
    }
    return true;
  }
  
  public boolean enableTooltipReturnToday(boolean paramBoolean)
  {
    if ((!paramBoolean) && (getTooltipInviteStatus() == 16)) {
      return false;
    }
    int i = getTooltipReturnTodayStatus();
    if (i != 4)
    {
      switch (i)
      {
      default: 
        return false;
      case 2: 
        setTooltipReturnTodayStatus(4);
        return true;
      case 0: 
        setTooltipReturnTodayStatus(1);
      }
      return true;
    }
    return true;
  }
  
  public String getAppName()
  {
    return OvenApplication.getInstance().getLocaleString(2131689617);
  }
  
  public List<Integer> getCalendarColors()
  {
    return this.mCalendarColors;
  }
  
  public int getCommentCount()
  {
    return b().getInt(PreferencesKeySet.commentCount, 0);
  }
  
  public String getCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  public DateTimeZone getDateTimeZone()
  {
    return DateTimeZone.getDefault();
  }
  
  public String getDisplaySize(Activity paramActivity)
  {
    paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
    Point localPoint = new Point();
    paramActivity.getSize(localPoint);
    return String.format("%sx%s", new Object[] { Integer.valueOf(localPoint.x), Integer.valueOf(localPoint.y) });
  }
  
  public int getFirstDayOfWeekSetting()
  {
    int i;
    if ((!isLanguageJp()) && (!isLanguageDe())) {
      i = 7;
    } else {
      i = 1;
    }
    return b().getInt(PreferencesKeySet.firstDayOfWeek, i);
  }
  
  public String getFirstUsedVersion()
  {
    return b().getString(PreferencesKeySet.firstVersionName, "1.4.0");
  }
  
  public String getHeaderLanguage()
  {
    Locale localLocale = getLocale();
    String str = localLocale.getLanguage();
    StringBuilder localStringBuilder = new StringBuilder(str);
    int i = str.hashCode();
    if (i != 3365)
    {
      if (i != 3588)
      {
        if ((i == 3886) && (str.equals("zh")))
        {
          i = 1;
          break label96;
        }
      }
      else if (str.equals("pt"))
      {
        i = 2;
        break label96;
      }
    }
    else if (str.equals("in"))
    {
      i = 0;
      break label96;
    }
    i = -1;
    switch (i)
    {
    default: 
      break;
    case 1: 
    case 2: 
      localStringBuilder.append("-");
      if (localLocale.getCountry().equals("HK")) {
        localStringBuilder.append("TW");
      } else {
        localStringBuilder.append(localLocale.getCountry());
      }
      break;
    case 0: 
      label96:
      return "id";
    }
    return localStringBuilder.toString();
  }
  
  public int getImportTimes(long paramLong)
  {
    return b().getInt(PreferencesKeySet.getImportTimes(paramLong), 0);
  }
  
  public String getLanguage()
  {
    Locale localLocale = getLocale();
    String str = localLocale.getLanguage();
    StringBuilder localStringBuilder = new StringBuilder(str);
    int i;
    if ((str.hashCode() == 3886) && (str.equals("zh"))) {
      i = 0;
    } else {
      i = -1;
    }
    if (i == 0)
    {
      localStringBuilder.append("-");
      localStringBuilder.append(localLocale.getCountry());
    }
    return localStringBuilder.toString();
  }
  
  public boolean getLastUsedAllDay()
  {
    return b().getBoolean(PreferencesKeySet.lastUsedAllDay, false);
  }
  
  public long getLastUsedLabelId(long paramLong)
  {
    return b().getLong(PreferencesKeySet.getLastUsedLabelIdKey(paramLong), -1L);
  }
  
  public ArrayList<Integer> getLastUsedReminders(boolean paramBoolean)
  {
    Object localObject;
    if (paramBoolean) {
      localObject = b().getString(PreferencesKeySet.lastUsedRemindersAllDay, null);
    } else {
      localObject = b().getString(PreferencesKeySet.lastUsedReminders, null);
    }
    if (StringUtils.isEmpty((CharSequence)localObject))
    {
      if (paramBoolean) {
        localObject = EventUtils.REMINDER_DEFAULT_ALL_DAY;
      } else {
        localObject = EventUtils.REMINDER_DEFAULT;
      }
      return new ArrayList(Collections.singletonList(Integer.valueOf(((ReminderMenu)localObject).getMinutes())));
    }
    return (ArrayList)new Gson().fromJson((String)localObject, new AppManager.1(this).getType());
  }
  
  public SelectTabView.TabType getLastUsedTab()
  {
    return SelectTabView.TabType.get(b().getInt(PreferencesKeySet.lastUsedTabType, SelectTabView.TabType.MONTHLY.getId()));
  }
  
  public String getLatestVersionName()
  {
    return this.mLatestAppVersion;
  }
  
  public Locale getLocale()
  {
    return LanguageSettingPresenter.getApplicationLocale(OvenApplication.getInstance().getApplicationContext());
  }
  
  public NewsInfo getNewsInfo()
  {
    return this.mNewsInfo;
  }
  
  public int getNpsDay()
  {
    return b().getInt(PreferencesKeySet.npsDay, 0);
  }
  
  public int getOvenDetailEventCount()
  {
    return b().getInt(PreferencesKeySet.showOvenDetailEventCount, 0);
  }
  
  public PackageManager getPackageManager()
  {
    return OvenApplication.getInstance().getApplicationContext().getPackageManager();
  }
  
  public String getPackageName()
  {
    return OvenApplication.getInstance().getApplicationContext().getPackageName();
  }
  
  public int getReviewStatus()
  {
    return b().getInt(PreferencesKeySet.reviewRequestStatus, 0);
  }
  
  public long getSyncAlarmTime()
  {
    return b().getLong(PreferencesKeySet.syncAlarmTime, 0L);
  }
  
  public boolean getTodayPush()
  {
    return b().getBoolean(PreferencesKeySet.todayPush, false);
  }
  
  public boolean getTodayPushNoEvent()
  {
    return b().getBoolean(PreferencesKeySet.todayPushNoEvent, false);
  }
  
  public int getTodayPushTime()
  {
    return b().getInt(PreferencesKeySet.todayPushTime, 360);
  }
  
  public int getTooltipInviteStatus()
  {
    return b().getInt(PreferencesKeySet.tooltip, 0) & 0xF0;
  }
  
  public int getTooltipReturnTodayStatus()
  {
    return b().getInt(PreferencesKeySet.tooltip, 0) & 0xF;
  }
  
  public int getTooltipTerm()
  {
    int i = getTooltipReturnTodayStatus();
    if (i != 4) {
      switch (i)
      {
      default: 
        return Integer.MAX_VALUE;
      case 0: 
      case 1: 
        return 3;
      }
    }
    return 6;
  }
  
  public String getVersionName()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Timber.e(localNameNotFoundException);
    }
    return null;
  }
  
  public boolean hasNewNotification()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(PreferencesKeySet.notificationUpdatedAt);
    ((StringBuilder)localObject).append("_");
    ((StringBuilder)localObject).append(a());
    localObject = ((StringBuilder)localObject).toString();
    return b().getLong((String)localObject, 0L) < this.mNotificationUpdatedAt;
  }
  
  public boolean hasNewPublicCalendarBlog()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("public_calendar_blog_updated_");
    ((StringBuilder)localObject).append(a());
    localObject = ((StringBuilder)localObject).toString();
    return b().getLong((String)localObject, 0L) < this.mPublicCalendarBlogUpdatedAt;
  }
  
  public boolean hasNewVersion()
  {
    return compareVersion(getVersionName(), getLatestVersionName()) < 0;
  }
  
  public boolean hasPackage(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (StringUtils.equals(paramString, ((ApplicationInfo)localIterator.next()).processName)) {
        return true;
      }
    }
    return false;
  }
  
  public int incrementCommentCount()
  {
    int i = b().getInt(PreferencesKeySet.commentCount, 0) + 1;
    b().apply(PreferencesKeySet.commentCount, i);
    return i;
  }
  
  public int incrementOvenDetailEventCount()
  {
    int i = b().getInt(PreferencesKeySet.showOvenDetailEventCount, 0) + 1;
    b().apply(PreferencesKeySet.showOvenDetailEventCount, i);
    return i;
  }
  
  public int incrementReviewEventCreateCount()
  {
    int i = b().getInt(PreferencesKeySet.reviewEventCreateCount, 0) + 1;
    b().apply(PreferencesKeySet.reviewEventCreateCount, i);
    return i;
  }
  
  public void initializeMemorialday()
  {
    String str = getCountry();
    MemorialdayLocaleType[] arrayOfMemorialdayLocaleType = MemorialdayLocaleType.values();
    int j = arrayOfMemorialdayLocaleType.length;
    int i = 0;
    while (i < j)
    {
      MemorialdayLocaleType localMemorialdayLocaleType = arrayOfMemorialdayLocaleType[i];
      if (str.equals(localMemorialdayLocaleType.getCountryIso()))
      {
        setMemorialdayEnabled(localMemorialdayLocaleType.getCountryIso(), true);
        return;
      }
      i += 1;
    }
  }
  
  public void initializeTodayAlarm()
  {
    int i = b().getInt(PreferencesKeySet.todayPushTime, 0);
    Random localRandom;
    if (i == -1)
    {
      setTodayPush(false);
      localRandom = new Random(System.currentTimeMillis());
      b().apply(PreferencesKeySet.todayPushTime, localRandom.nextInt(7) * 30 + 480);
    }
    if (!b().contains(PreferencesKeySet.todayPush))
    {
      setTodayPush(true);
      if (i == 0)
      {
        localRandom = new Random(System.currentTimeMillis());
        b().apply(PreferencesKeySet.todayPushTime, localRandom.nextInt(7) * 30 + 480);
      }
    }
  }
  
  public boolean isCheeringBadgeEnabled()
  {
    return b().getBoolean(PreferencesKeySet.cheeringBadgeEnabled, true);
  }
  
  public boolean isCheeringCompleted()
  {
    return b().getBoolean(PreferencesKeySet.cheeringCompleted, false);
  }
  
  public boolean isCompleteTooltip()
  {
    SharedPreferencesHelper localSharedPreferencesHelper = b();
    String str = PreferencesKeySet.tooltip;
    boolean bool = false;
    if (localSharedPreferencesHelper.getInt(str, 0) == 40) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isCompleteTooltipInvite()
  {
    return getTooltipInviteStatus() == 32;
  }
  
  public boolean isCompleteTooltipReturnToday()
  {
    return getTooltipReturnTodayStatus() == 8;
  }
  
  public boolean isDeviceNotificationHelpEnabled()
  {
    return b().getBoolean(PreferencesKeySet.deviceNotificationHelp, true);
  }
  
  public boolean isEventHistoryEnabled()
  {
    return b().getBoolean(PreferencesKeySet.eventHistoryEnabled, AndroidCompatUtils.getEventHistoryDefault());
  }
  
  public boolean isLanguageDe()
  {
    return Locale.GERMANY.getLanguage().equals(getLanguage());
  }
  
  public boolean isLanguageEn()
  {
    return Locale.ENGLISH.getLanguage().equals(getLanguage());
  }
  
  public boolean isLanguageHk()
  {
    return getLanguage().equals("zh-HK");
  }
  
  public boolean isLanguageJp()
  {
    return Locale.JAPAN.getLanguage().equals(getLanguage());
  }
  
  public boolean isLanguageKr()
  {
    return Locale.KOREA.getLanguage().equals(getLanguage());
  }
  
  public boolean isLanguageTw()
  {
    String str = getLanguage();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Locale.TAIWAN.getLanguage());
    localStringBuilder.append("-");
    localStringBuilder.append(Locale.TAIWAN.getCountry());
    return str.equals(localStringBuilder.toString());
  }
  
  public boolean isLocalLastCreatedCalendarType()
  {
    return b().getBoolean(PreferencesKeySet.isLocalLastUsedCalendarType, false);
  }
  
  public boolean isMemorialdayEnabled(String paramString)
  {
    SharedPreferencesHelper localSharedPreferencesHelper = b();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(PreferencesKeySet.memorialdayEnabled);
    localStringBuilder.append("_");
    localStringBuilder.append(paramString);
    return localSharedPreferencesHelper.getBoolean(localStringBuilder.toString(), false);
  }
  
  public boolean isMemorialdayTitleEnabled()
  {
    return b().getBoolean("memorialday_title_enabled", true);
  }
  
  public boolean isOldCalendarEnabled()
  {
    return b().getBoolean(PreferencesKeySet.oldCalendarEnabled, false);
  }
  
  public boolean isPackageEnabled(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (StringUtils.equals(paramString, localApplicationInfo.processName)) {
        return localApplicationInfo.enabled;
      }
    }
    return false;
  }
  
  public boolean isPicSuggestEnabled()
  {
    return b().getBoolean("pic_suggest_enabled", true);
  }
  
  public boolean isPicSuggestPermissionRequestedInEventDetail()
  {
    boolean bool = b().getBoolean("pic_suggest_permission_requested_in_event_detail", false);
    Timber.tag("Permission").d("予定詳細で権限要求済み?: %s", new Object[] { Boolean.valueOf(bool) });
    return bool;
  }
  
  public boolean isPicSuggestPermissionRequestedInFeed()
  {
    boolean bool = b().getBoolean("pic_suggest_permission_requested_in_feed", false);
    Timber.tag("Permission").d("フィードで権限要求済み?: %s", new Object[] { Boolean.valueOf(bool) });
    return bool;
  }
  
  public boolean isPushAlertEnabled()
  {
    return b().getBoolean(PreferencesKeySet.pushAlert, true);
  }
  
  public boolean isReviewBadgeEnabled()
  {
    return b().getBoolean(PreferencesKeySet.reviewBadge, false);
  }
  
  public boolean isReviewBannerEnabled()
  {
    return b().getBoolean(PreferencesKeySet.reviewBadgeBanner, false);
  }
  
  public boolean isReviewEnabled()
  {
    return this.mIsReviewEnabled;
  }
  
  public boolean isRokuyoCalendarEnabled()
  {
    return b().getBoolean(PreferencesKeySet.rokuyoCalendarEnabled, false);
  }
  
  public boolean isShowProfilePromo()
  {
    SharedPreferencesHelper localSharedPreferencesHelper = b();
    String str = PreferencesKeySet.showProfilePromoDone;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (!localSharedPreferencesHelper.getBoolean(str, false))
    {
      bool1 = bool2;
      if (Models.localUsers().getUser().isProfileEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public boolean isShowReviewBanner()
  {
    if (!isReviewEnabled()) {
      return false;
    }
    return isReviewBannerEnabled();
  }
  
  public boolean isShowReviewNotice()
  {
    if (hasNewNotification()) {
      return false;
    }
    if (!isReviewBadgeEnabled()) {
      return false;
    }
    return getReviewStatus() == 0;
  }
  
  public boolean isShowReviewRequest()
  {
    if (!isReviewEnabled()) {
      return false;
    }
    if (getReviewStatus() != 0) {
      return false;
    }
    return b().getInt(PreferencesKeySet.reviewEventCreateCount, 0) >= 10L;
  }
  
  public boolean isSupportedVersion(String paramString)
  {
    return compareVersion(getVersionName(), paramString) >= 0;
  }
  
  public boolean isTargetLanguage(String paramString)
  {
    return StringUtils.equals(paramString, getLanguage());
  }
  
  public boolean isTargetLanguage(List<String> paramList)
  {
    if (paramList.size() == 0) {
      return true;
    }
    return paramList.contains(getLanguage());
  }
  
  public boolean isUpdateNoticeCompleted()
  {
    SharedPreferencesHelper localSharedPreferencesHelper = b();
    String str = PreferencesKeySet.updateNoticeVersion;
    boolean bool = false;
    if (localSharedPreferencesHelper.getInt(str, 0) == 16) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isVibrationEnabled()
  {
    return b().getBoolean(PreferencesKeySet.vibrationEnabled, true);
  }
  
  public boolean isWeeknumCalendarEnabled()
  {
    return b().getBoolean("weeknum_calendar_enabled", isLanguageDe());
  }
  
  public void loadProperties()
  {
    Iterator localIterator = Models.properties().loadAll().iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = (OvenProperty)localIterator.next();
    } while (!isTargetLanguage(((OvenProperty)localObject).getTargetLangs()));
    for (;;)
    {
      int i;
      try
      {
        long l = ((OvenProperty)localObject).getId().longValue();
        i = 0;
        if (l == 1L)
        {
          localObject = ((OvenProperty)localObject).getValue().getJSONArray("calendar_colors");
          ArrayList localArrayList = new ArrayList();
          if (i < ((JSONArray)localObject).length())
          {
            localArrayList.add(Integer.valueOf(((JSONArray)localObject).getInt(i)));
            i += 1;
            continue;
          }
          if (localArrayList.size() <= 0) {
            break;
          }
          this.mCalendarColors = localArrayList;
          break;
        }
        if (((OvenProperty)localObject).getId().longValue() == 2L)
        {
          if (((OvenProperty)localObject).getValue().isNull("android")) {
            break;
          }
          this.mLatestAppVersion = ((OvenProperty)localObject).getValue().getString("android");
          break;
        }
        if (((OvenProperty)localObject).getId().longValue() == 3L)
        {
          this.mIsReviewEnabled = false;
          if (!((OvenProperty)localObject).getValue().isNull("android"))
          {
            localObject = ((OvenProperty)localObject).getValue().getJSONArray("android");
            i = 0;
            if (i < ((JSONArray)localObject).length())
            {
              if (compareVersion(((JSONArray)localObject).getString(i), getVersionName()) != 0) {
                break label590;
              }
              this.mIsReviewEnabled = true;
              if (!b().getBoolean(PreferencesKeySet.receivedReviewEnabled, false))
              {
                if (getInstance().getReviewStatus() == 0) {
                  setReviewBadgeEnabled(true);
                }
                setReviewBannerEnabled(true);
                b().apply(PreferencesKeySet.receivedReviewEnabled, true);
                EventBus.getDefault().post(EBKey.NOTICE_STATUS_UPDATED);
              }
            }
          }
          if (this.mIsReviewEnabled) {
            break;
          }
          setReviewBadgeEnabled(false);
          setReviewBannerEnabled(false);
          break;
        }
        if (((OvenProperty)localObject).getId().longValue() == 4L)
        {
          localObject = ((OvenProperty)localObject).getValue().getJSONObject(a());
          if ((localObject == null) || (((JSONObject)localObject).isNull("updated_at"))) {
            break;
          }
          this.mNotificationUpdatedAt = ((JSONObject)localObject).getLong("updated_at");
          EventBus.getDefault().post(EBKey.NOTICE_STATUS_UPDATED);
          new SettingRepository(new SettingLocalDataSource(b(), this)).setNotificationUpdatedAt(this.mNotificationUpdatedAt);
          break;
        }
        if (((OvenProperty)localObject).getId().longValue() == 5L)
        {
          this.mNewsInfo.applyTemplateProperty(((OvenProperty)localObject).getValue());
          break;
        }
        if (((OvenProperty)localObject).getId().longValue() == 6L)
        {
          this.mNewsInfo.applyDialogProperty(((OvenProperty)localObject).getValue());
          break;
        }
        if (((OvenProperty)localObject).getId().longValue() == 7L)
        {
          localObject = ((OvenProperty)localObject).getValue().getJSONObject(a());
          if ((localObject == null) || (((JSONObject)localObject).isNull("updated_at"))) {
            break;
          }
          this.mPublicCalendarBlogUpdatedAt = ((JSONObject)localObject).getLong("updated_at");
          break;
        }
        Timber.i("unsupported property. property id: %s", new Object[] { ((OvenProperty)localObject).getId() });
      }
      catch (JSONException localJSONException)
      {
        Timber.e(localJSONException);
      }
      break;
      return;
      label590:
      i += 1;
    }
  }
  
  public void logoutAndGoToTitle(FragmentActivity paramFragmentActivity)
  {
    a(paramFragmentActivity, false);
  }
  
  public void onEvent(EBKey paramEBKey)
  {
    if (AppManager.3.$SwitchMap$works$jubilee$timetree$constant$eventbus$EBKey[paramEBKey.ordinal()] != 1) {
      return;
    }
    loadProperties();
  }
  
  public boolean onTooltip()
  {
    return (onTooltipReturnToday()) || (onTooltipInvite());
  }
  
  public boolean onTooltipInvite()
  {
    return getTooltipInviteStatus() == 16;
  }
  
  public boolean onTooltipReturnToday()
  {
    int i = getTooltipReturnTodayStatus();
    return (i == 1) || (i == 4);
  }
  
  public void resetTooltip()
  {
    b().apply(PreferencesKeySet.tooltip, 0);
  }
  
  public void reviewAppDone()
  {
    b().apply(PreferencesKeySet.reviewRequestStatus, 1);
  }
  
  public void reviewAppLater()
  {
    b().apply(PreferencesKeySet.reviewRequestStatus, 2);
  }
  
  public void reviewAppNever()
  {
    b().apply(PreferencesKeySet.reviewRequestStatus, 3);
  }
  
  public void setCheeringBadgeEnabled(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.cheeringBadgeEnabled, paramBoolean);
  }
  
  public void setCheeringCompleted(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.cheeringCompleted, paramBoolean);
  }
  
  public void setEventHistoryEnabled(boolean paramBoolean)
  {
    if (isEventHistoryEnabled() == paramBoolean) {
      return;
    }
    b().apply(PreferencesKeySet.eventHistoryEnabled, paramBoolean);
    EventBus.getDefault().post(EBKey.CALENDAR_DISPLAY_SETTING_UPDATED);
  }
  
  public void setFirstDayOfWeekSetting(int paramInt)
  {
    if (getFirstDayOfWeekSetting() == paramInt) {
      return;
    }
    b().apply(PreferencesKeySet.firstDayOfWeek, paramInt);
    EventBus.getDefault().post(EBKey.FIRST_DAY_OF_WEEK_CHANGED);
  }
  
  public void setFirstUsedVersion(String paramString)
  {
    b().apply(PreferencesKeySet.firstVersionName, paramString);
  }
  
  public void setImportTimes(long paramLong, int paramInt)
  {
    b().apply(PreferencesKeySet.getImportTimes(paramLong), paramInt);
  }
  
  public void setLastCreatedCalendarTypeLocal(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.isLocalLastUsedCalendarType, paramBoolean);
  }
  
  public void setLastUsedAllDay(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.lastUsedAllDay, paramBoolean);
  }
  
  public void setLastUsedLabelId(long paramLong1, long paramLong2)
  {
    b().apply(PreferencesKeySet.getLastUsedLabelIdKey(paramLong1), paramLong2);
  }
  
  public void setLastUsedReminders(ArrayList<Integer> paramArrayList, boolean paramBoolean)
  {
    paramArrayList = new Gson().toJson(paramArrayList);
    if (paramBoolean)
    {
      b().apply(PreferencesKeySet.lastUsedRemindersAllDay, paramArrayList);
      return;
    }
    b().apply(PreferencesKeySet.lastUsedReminders, paramArrayList);
  }
  
  public void setLastUsedTab(SelectTabView.TabType paramTabType)
  {
    b().apply(PreferencesKeySet.lastUsedTabType, paramTabType.getId());
  }
  
  public void setMemorialdayEnabled(String paramString, boolean paramBoolean)
  {
    if (isMemorialdayEnabled(paramString) == paramBoolean) {
      return;
    }
    SharedPreferencesHelper localSharedPreferencesHelper = b();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(PreferencesKeySet.memorialdayEnabled);
    localStringBuilder.append("_");
    localStringBuilder.append(paramString);
    localSharedPreferencesHelper.apply(localStringBuilder.toString(), paramBoolean);
    if (!paramBoolean) {
      EventBus.getDefault().post(EBKey.CALENDAR_DISPLAY_SETTING_UPDATED);
    }
  }
  
  public void setMemorialdayTitleEnabled(boolean paramBoolean)
  {
    b().apply("memorialday_title_enabled", paramBoolean);
  }
  
  public void setNpsDay(int paramInt)
  {
    b().apply(PreferencesKeySet.npsDay, paramInt);
  }
  
  public void setOldCalendarEnabled(boolean paramBoolean)
  {
    if (isOldCalendarEnabled() == paramBoolean) {
      return;
    }
    b().apply(PreferencesKeySet.oldCalendarEnabled, paramBoolean);
    EventBus.getDefault().post(EBKey.CALENDAR_DISPLAY_SETTING_UPDATED);
  }
  
  public void setPicSuggestEnabled(boolean paramBoolean)
  {
    b().apply("pic_suggest_enabled", paramBoolean);
  }
  
  public void setPicSuggestPermissionRequestedInEventDetail()
  {
    b().edit().putBoolean("pic_suggest_permission_requested_in_event_detail", true).apply();
  }
  
  public void setPicSuggestPermissionRequestedInFeed()
  {
    b().edit().putBoolean("pic_suggest_permission_requested_in_feed", true).apply();
  }
  
  public void setPushAlertEnabled(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.pushAlert, paramBoolean);
  }
  
  public void setReviewBadgeEnabled(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.reviewBadge, paramBoolean);
  }
  
  public void setReviewBannerEnabled(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.reviewBadgeBanner, paramBoolean);
  }
  
  public void setRokuyoCalendarEnabled(boolean paramBoolean)
  {
    if (isRokuyoCalendarEnabled() == paramBoolean) {
      return;
    }
    b().apply(PreferencesKeySet.rokuyoCalendarEnabled, paramBoolean);
    EventBus.getDefault().post(EBKey.CALENDAR_DISPLAY_SETTING_UPDATED);
  }
  
  public void setSyncAlarmTime(long paramLong)
  {
    b().apply(PreferencesKeySet.syncAlarmTime, paramLong);
  }
  
  public void setTodayPush(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.todayPush, paramBoolean);
  }
  
  public void setTodayPushNoEvent(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.todayPushNoEvent, paramBoolean);
  }
  
  public void setTodayPushTime(int paramInt)
  {
    b().apply(PreferencesKeySet.todayPushTime, paramInt);
  }
  
  public void setTooltipInviteStatus(int paramInt)
  {
    int i = b().getInt(PreferencesKeySet.tooltip, 0);
    b().apply(PreferencesKeySet.tooltip, paramInt | i & 0xFF0F);
  }
  
  public void setTooltipReturnTodayStatus(int paramInt)
  {
    int i = b().getInt(PreferencesKeySet.tooltip, 0);
    b().apply(PreferencesKeySet.tooltip, paramInt | i & 0xFFFFFFF0);
  }
  
  public void setUpdateNoticeComplete()
  {
    b().apply(PreferencesKeySet.updateNoticeVersion, 16);
  }
  
  public void setVibrationEnabled(boolean paramBoolean)
  {
    b().apply(PreferencesKeySet.vibrationEnabled, paramBoolean);
  }
  
  public void setWeeknumCalendarEnabled(boolean paramBoolean)
  {
    if (isWeeknumCalendarEnabled() == paramBoolean) {
      return;
    }
    b().apply("weeknum_calendar_enabled", paramBoolean);
    EventBus.getDefault().post(EBKey.CALENDAR_DISPLAY_SETTING_UPDATED);
  }
  
  public void showProfilePromoDone()
  {
    b().apply(PreferencesKeySet.showProfilePromoDone, true);
  }
}
