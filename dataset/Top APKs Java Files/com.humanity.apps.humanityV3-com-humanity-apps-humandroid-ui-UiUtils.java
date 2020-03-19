package com.humanity.apps.humandroid.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.humanity.app.core.client.logging.Dump;
import com.humanity.app.core.content.GsonProvider;
import com.humanity.app.core.content.response.AdminBusinessResponse;
import com.humanity.app.core.deserialization.InnerObject;
import com.humanity.app.core.interfaces.BaseObjectLoadListener;
import com.humanity.app.core.model.DTRObject;
import com.humanity.app.core.model.Employee;
import com.humanity.app.core.model.EmployeeItem;
import com.humanity.app.core.model.Event;
import com.humanity.app.core.model.Event.Data;
import com.humanity.app.core.model.Location;
import com.humanity.app.core.model.Position;
import com.humanity.app.core.model.ScheduleBreak;
import com.humanity.app.core.model.TimeClock;
import com.humanity.app.core.model.TradeReceiver;
import com.humanity.app.core.util.DateUtil;
import com.humanity.app.core.util.PrefHelper;
import com.humanity.app.core.util.UIUtil;
import com.humanity.apps.humandroid.activity.settings.WidgetSettingsActivity.WidgetOrder;
import com.humanity.apps.humandroid.fragment.training.SignatureDialogFragment;
import com.humanity.apps.humandroid.presenter.PositionPresenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.State;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView.StateBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class UiUtils
{
  public static Employee CURRENT_EMPLOYEE = PrefHelper.getCurrentEmployee();
  private static final SimpleDateFormat DATE_FORMAT_12_HOURS = new SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.US);
  private static final SimpleDateFormat DATE_FORMAT_24_HOURS = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.US);
  private static final SimpleDateFormat DATE_FORMAT_DAY_MONTH;
  private static final SimpleDateFormat DATE_FORMAT_DAY_MONTH_YEAR;
  private static final SimpleDateFormat DATE_FORMAT_EVERYTHING;
  public static final SimpleDateFormat DAY_NAME_FORMAT;
  public static final SimpleDateFormat DAY_NUMBER_FORMAT;
  public static final SimpleDateFormat TIME_FORMAT_12_HOURS = new SimpleDateFormat("h:mm a", Locale.US);
  public static final SimpleDateFormat TIME_FORMAT_24_HOURS = new SimpleDateFormat("HH:mm", Locale.US);
  
  static
  {
    DATE_FORMAT_DAY_MONTH = new SimpleDateFormat("MMM dd", Locale.US);
    DATE_FORMAT_DAY_MONTH_YEAR = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
    DATE_FORMAT_EVERYTHING = new SimpleDateFormat("EEE MMM dd, yyyy", Locale.US);
    DAY_NAME_FORMAT = new SimpleDateFormat("EEE", Locale.US);
    DAY_NUMBER_FORMAT = new SimpleDateFormat("d", Locale.US);
  }
  
  public UiUtils() {}
  
  public static void animateIn(View paramView)
  {
    paramView.setVisibility(0);
    ViewCompat.animate(paramView).scaleX(1.0F).scaleY(1.0F).alpha(1.0F).setInterpolator(new FastOutSlowInInterpolator()).withLayer().setListener(new ViewPropertyAnimatorListener()
    {
      public void onAnimationCancel(View paramAnonymousView) {}
      
      public void onAnimationEnd(View paramAnonymousView) {}
      
      public void onAnimationStart(View paramAnonymousView) {}
    }).start();
  }
  
  public static void animateOut(View paramView)
  {
    ViewCompat.animate(paramView).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(new FastOutSlowInInterpolator()).withLayer().setListener(new ViewPropertyAnimatorListener()
    {
      public void onAnimationCancel(View paramAnonymousView) {}
      
      public void onAnimationEnd(View paramAnonymousView)
      {
        paramAnonymousView.setVisibility(8);
      }
      
      public void onAnimationStart(View paramAnonymousView) {}
    }).start();
  }
  
  public static void applySwipeColors(SwipeRefreshLayout paramSwipeRefreshLayout)
  {
    paramSwipeRefreshLayout.setColorSchemeResources(new int[] { 2131099717, 2131099696, 2131099700, 2131099701 });
  }
  
  private static boolean areThereMockPermissionApps(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    boolean bool1 = false;
    int i = 0;
    if (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      for (;;)
      {
        try
        {
          String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
          if (arrayOfString == null) {
            break;
          }
          int k = 0;
          j = i;
          try
          {
            if (k < arrayOfString.length)
            {
              j = i;
              if (arrayOfString[k].equals("android.permission.ACCESS_MOCK_LOCATION"))
              {
                boolean bool2 = localApplicationInfo.packageName.equals(paramContext.getPackageName());
                j = i;
                if (!bool2) {
                  j = i + 1;
                }
              }
              k += 1;
              i = j;
              continue;
            }
            i = j;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException1) {}
          Dump.error(localNameNotFoundException2.getLocalizedMessage());
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2) {}
        int j = i;
      }
    }
    if (i > 0) {
      bool1 = true;
    }
    return bool1;
  }
  
  @TargetApi(21)
  public static void circularHideView(View paramView, final boolean paramBoolean, final AppCompatActivity paramAppCompatActivity)
  {
    int i = paramView.getWidth();
    int j = paramView.getHeight();
    Animator localAnimator = ViewAnimationUtils.createCircularReveal(paramView, i, j, (float)Math.hypot(i, j), 0.0F);
    localAnimator.addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        super.onAnimationEnd(paramAnonymousAnimator);
        this.val$view.setVisibility(4);
        if (paramBoolean) {
          paramAppCompatActivity.finish();
        }
      }
    });
    localAnimator.setDuration(500L);
    localAnimator.start();
  }
  
  @TargetApi(21)
  public static void circularRevealView(View paramView)
  {
    int i = paramView.getWidth();
    int j = paramView.getHeight();
    Animator localAnimator = ViewAnimationUtils.createCircularReveal(paramView, i, j, 0.0F, (float)Math.hypot(i, j));
    paramView.setVisibility(0);
    localAnimator.setDuration(500L);
    localAnimator.start();
  }
  
  private static boolean contains(ArrayList<WidgetSettingsActivity.WidgetOrder> paramArrayList, int paramInt)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (((WidgetSettingsActivity.WidgetOrder)paramArrayList.get(i)).getType() == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static AlertDialog createInformAlert(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(new ContextThemeWrapper(paramContext, 2131820550));
    paramContext.setMessage(paramString2).setCancelable(false).setPositiveButton(paramString1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    return paramContext.create();
  }
  
  public static AlertDialog createYesCancelAlertDialog(Context paramContext, String paramString1, String paramString2, DialogListener paramDialogListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(new ContextThemeWrapper(paramContext, 2131820550));
    localBuilder.setMessage(paramString2).setCancelable(false).setPositiveButton(paramString1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        this.val$listener.onPositive();
      }
    }).setNegativeButton(paramContext.getString(2131755144), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    return localBuilder.create();
  }
  
  public static AlertDialog createYesCancelAlertDialog(Context paramContext, String paramString1, String paramString2, String paramString3, DialogListener paramDialogListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(new ContextThemeWrapper(paramContext, 2131820550));
    localBuilder.setMessage(paramString2).setCancelable(false).setTitle(paramString3).setPositiveButton(paramString1, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        this.val$listener.onPositive();
      }
    }).setNegativeButton(paramContext.getString(2131755144), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    return localBuilder.create();
  }
  
  @NonNull
  public static String ellipsizeText(String paramString, int paramInt)
  {
    if (paramString == null) {
      return "";
    }
    Object localObject = paramString;
    if (paramString.length() > paramInt)
    {
      paramString = paramString.substring(0, paramInt - 4);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("...");
      localObject = ((StringBuilder)localObject).toString();
    }
    return localObject;
  }
  
  public static void enableTransitions(ViewGroup paramViewGroup)
  {
    LayoutTransition localLayoutTransition = new LayoutTransition();
    localLayoutTransition.enableTransitionType(4);
    localLayoutTransition.setDuration(300L);
    paramViewGroup.setLayoutTransition(localLayoutTransition);
  }
  
  public static String generateEmployeesText(Context paramContext, Employee paramEmployee, AdminBusinessResponse paramAdminBusinessResponse, List<EmployeeItem> paramList)
  {
    int i;
    if ((paramAdminBusinessResponse != null) && (!paramAdminBusinessResponse.canSeeCoworkersOnShift().booleanValue())) {
      i = 1;
    } else {
      i = 0;
    }
    int k = 0;
    paramAdminBusinessResponse = "";
    int j = k;
    while (j < paramList.size())
    {
      if (((EmployeeItem)paramList.get(j)).getEmployee().equals(paramEmployee)) {
        k = 1;
      }
      Object localObject = paramAdminBusinessResponse;
      if (j > 0)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramAdminBusinessResponse);
        ((StringBuilder)localObject).append(", ");
        localObject = ((StringBuilder)localObject).toString();
      }
      paramAdminBusinessResponse = new StringBuilder();
      paramAdminBusinessResponse.append((String)localObject);
      paramAdminBusinessResponse.append(((EmployeeItem)paramList.get(j)).getEmployee().getDisplayFirstLast());
      paramAdminBusinessResponse = paramAdminBusinessResponse.toString();
      j += 1;
    }
    if (i != 0)
    {
      i = paramList.size() - 1;
      paramContext = paramContext.getResources().getQuantityString(2131623955, i, new Object[] { Integer.valueOf(i) });
      if (k == 0) {
        return paramAdminBusinessResponse;
      }
      if (i == 0) {
        return paramEmployee.getDisplayFirstLast();
      }
      paramAdminBusinessResponse = new StringBuilder();
      paramAdminBusinessResponse.append(paramEmployee.getDisplayFirstLast());
      paramAdminBusinessResponse.append(", ");
      paramAdminBusinessResponse.append(paramContext);
      return paramAdminBusinessResponse.toString();
    }
    return paramAdminBusinessResponse;
  }
  
  public static View getBreakEventView(Context paramContext, Date paramDate, long paramLong)
  {
    View localView = View.inflate(paramContext, 2131427614, null);
    ImageView localImageView = (ImageView)localView.findViewById(2131296763);
    TextView localTextView = (TextView)localView.findViewById(2131296762);
    String str = "";
    if (paramDate != null) {
      str = getTimeFormatted(paramContext, paramDate.getTime() / 1000L);
    }
    ((TextView)localView.findViewById(2131296764)).setText(str);
    localImageView.setImageResource(2131230830);
    if (paramLong == 0L)
    {
      localTextView.setText(paramContext.getString(2131755633));
      return localView;
    }
    paramLong = (paramLong - paramDate.getTime()) / 1000L;
    long l = paramLong / 3600L;
    if (l > 0L)
    {
      paramDate = new StringBuilder();
      paramDate.append("");
      paramDate.append(l);
      paramDate.append(paramContext.getString(2131755444));
      str = paramDate.toString();
      paramLong = (paramLong - l * 3600L) / 60L;
      paramDate = str;
      if (paramLong > 0L)
      {
        paramDate = new StringBuilder();
        paramDate.append(str);
        paramDate.append(" ");
        paramDate.append(paramLong);
        paramDate.append(paramContext.getString(2131755542));
        paramDate = paramDate.toString();
      }
    }
    else
    {
      int i = (int)paramLong / 60;
      paramDate = new StringBuilder();
      paramDate.append("");
      paramDate.append(String.valueOf(i));
      paramDate.append(paramContext.getString(2131755542));
      paramDate = paramDate.toString();
    }
    localTextView.setText(String.format(paramContext.getString(2131755135), new Object[] { paramDate }));
    return localView;
  }
  
  public static String getCurrencyString(Context paramContext)
  {
    Object localObject = "";
    long l = PrefHelper.getBusinessPrefs().getCurrencyId();
    if (l != 99L)
    {
      paramContext = paramContext.getResources().getStringArray(2130903042);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(" ");
      ((StringBuilder)localObject).append(paramContext[((int)(l - 1L))]);
      localObject = ((StringBuilder)localObject).toString();
    }
    return localObject;
  }
  
  public static View getCustomFieldEditText(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = View.inflate(paramContext, 2131427450, null);
    ((TextInputLayout)paramContext.findViewById(2131296855)).setHint(paramString1);
    ((EditText)paramContext.findViewById(2131296583)).setText(paramString2);
    return paramContext;
  }
  
  public static View getCustomFieldOption(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = View.inflate(paramContext, 2131427452, null);
    TextView localTextView1 = (TextView)paramContext.findViewById(2131296582);
    TextView localTextView2 = (TextView)paramContext.findViewById(2131296586);
    localTextView1.setText(paramString1);
    localTextView2.setText(paramString2);
    return paramContext;
  }
  
  public static View getCustomFieldSwitch(Context paramContext, String paramString, boolean paramBoolean)
  {
    paramContext = View.inflate(paramContext, 2131427453, null);
    ((TextView)paramContext.findViewById(2131296582)).setText(paramString);
    ((SwitchCompat)paramContext.findViewById(2131296585)).setChecked(paramBoolean);
    return paramContext;
  }
  
  public static View getCustomFieldView(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = View.inflate(paramContext, 2131427451, null);
    TextView localTextView1 = (TextView)paramContext.findViewById(2131296582);
    TextView localTextView2 = (TextView)paramContext.findViewById(2131296586);
    localTextView1.setText(paramString1);
    localTextView2.setText(paramString2);
    return paramContext;
  }
  
  public static Date getDateFrom12HoursString(String paramString)
  {
    try
    {
      paramString = DATE_FORMAT_12_HOURS.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static Date getDateFrom24HoursString(String paramString)
  {
    try
    {
      paramString = DATE_FORMAT_24_HOURS.parse(paramString);
      return paramString;
    }
    catch (ParseException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getDayMonthFormatted(long paramLong)
  {
    return getLocalizedTimeForDateFormat(DATE_FORMAT_DAY_MONTH, paramLong);
  }
  
  public static String getDayMonthFormattedInPhoneTimezone(long paramLong)
  {
    DATE_FORMAT_DAY_MONTH.setTimeZone(TimeZone.getDefault());
    return DATE_FORMAT_DAY_MONTH.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getDayMonthYearFormatted(long paramLong)
  {
    return getLocalizedTimeForDateFormat(DATE_FORMAT_DAY_MONTH_YEAR, paramLong);
  }
  
  public static String getDayMonthYearFormattedInPhoneTimezone(long paramLong)
  {
    DATE_FORMAT_DAY_MONTH_YEAR.setTimeZone(TimeZone.getDefault());
    return DATE_FORMAT_DAY_MONTH_YEAR.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getDayNameFormat(int paramInt, long paramLong)
  {
    return getLocalizedTimeForDateFormat(paramInt, DAY_NAME_FORMAT, paramLong);
  }
  
  public static String getDayNameFormat(long paramLong)
  {
    return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), DAY_NAME_FORMAT, paramLong);
  }
  
  public static String getDayNumberFormat(int paramInt, long paramLong)
  {
    return getLocalizedTimeForDateFormat(paramInt, DAY_NUMBER_FORMAT, paramLong);
  }
  
  public static String getDayNumberFormat(long paramLong)
  {
    return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), DAY_NUMBER_FORMAT, paramLong);
  }
  
  public static int getDeviceWidth(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext = (WindowManager)paramContext.getApplicationContext().getSystemService("window");
    if (paramContext == null) {
      return 0;
    }
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static String getEverythingFormatted(long paramLong)
  {
    return getLocalizedTimeForDateFormat(DATE_FORMAT_EVERYTHING, paramLong);
  }
  
  public static String getEverythingFormattedInPhoneTimezone(long paramLong)
  {
    DATE_FORMAT_EVERYTHING.setTimeZone(TimeZone.getDefault());
    return DATE_FORMAT_EVERYTHING.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getEverythingFormattedInUTC(long paramLong)
  {
    DATE_FORMAT_EVERYTHING.setTimeZone(TimeZone.getTimeZone("UTC"));
    return DATE_FORMAT_EVERYTHING.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static ColorStateList getIconTintList(int paramInt)
  {
    return new ColorStateList(new int[][] { { 16842910 }, { -16842910 }, { -16842912 }, { 16842919 } }, new int[] { paramInt, paramInt, paramInt, paramInt });
  }
  
  private static String getLocalizedTimeForDateFormat(int paramInt, SimpleDateFormat paramSimpleDateFormat, long paramLong)
  {
    TimeZone localTimeZone;
    if (paramInt == 415) {
      localTimeZone = TimeZone.getDefault();
    } else {
      localTimeZone = TimeZone.getTimeZone(com.humanity.app.core.util.TimeZoneUtils.timezones[paramInt]);
    }
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    localGregorianCalendar.setTimeInMillis(paramLong * 1000L);
    paramSimpleDateFormat.setTimeZone(localTimeZone);
    return paramSimpleDateFormat.format(localGregorianCalendar.getTime());
  }
  
  private static String getLocalizedTimeForDateFormat(SimpleDateFormat paramSimpleDateFormat, long paramLong)
  {
    return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), paramSimpleDateFormat, paramLong);
  }
  
  public static View getLocationEventView(final Context paramContext, com.humanity.app.core.manager.LocationManager paramLocationManager, Event paramEvent)
  {
    View localView = View.inflate(paramContext, 2131427614, null);
    ImageView localImageView = (ImageView)localView.findViewById(2131296763);
    TextView localTextView = (TextView)localView.findViewById(2131296762);
    Date localDate = paramEvent.getDate();
    String str = "";
    if (localDate != null) {
      str = getTimeFormatted(paramContext, localDate.getTime() / 1000L);
    }
    ((TextView)localView.findViewById(2131296764)).setText(str);
    localImageView.setImageResource(2131230816);
    paramLocationManager.getLocationAsync(paramEvent.getData().getLocation(), new BaseObjectLoadListener()
    {
      public void onEntityLoaded(Location paramAnonymousLocation)
      {
        TextView localTextView = this.val$eventDescription;
        String str = paramContext.getString(2131755485);
        if (paramAnonymousLocation != null)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("");
          localStringBuilder.append(paramAnonymousLocation.getName());
          paramAnonymousLocation = localStringBuilder.toString();
        }
        else
        {
          paramAnonymousLocation = paramContext.getString(2131755599);
        }
        localTextView.setText(String.format(str, new Object[] { paramAnonymousLocation }));
      }
      
      public void onError(String paramAnonymousString)
      {
        this.val$eventDescription.setText(paramContext.getString(2131755599));
      }
    });
    return localView;
  }
  
  public static String getNotLocalizedTimeFormatted(Context paramContext, long paramLong)
  {
    TIME_FORMAT_24_HOURS.setTimeZone(TimeZone.getDefault());
    TIME_FORMAT_12_HOURS.setTimeZone(TimeZone.getDefault());
    if (paramContext == null) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    if (is24HourFormat(paramContext)) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    return TIME_FORMAT_12_HOURS.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getNotLocalizedTimeFormattedInPhoneTimezone(Context paramContext, long paramLong)
  {
    if (paramContext == null) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    if (is24HourFormat(paramContext)) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    return TIME_FORMAT_12_HOURS.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static View getNoteEventView(Context paramContext, Event paramEvent)
  {
    View localView = View.inflate(paramContext, 2131427614, null);
    ImageView localImageView = (ImageView)localView.findViewById(2131296763);
    TextView localTextView = (TextView)localView.findViewById(2131296762);
    Date localDate = paramEvent.getDate();
    String str = "";
    if (localDate != null) {
      str = getTimeFormatted(paramContext, localDate.getTime() / 1000L);
    }
    paramContext = (TextView)localView.findViewById(2131296764);
    int i = getViewHeight(paramContext);
    paramContext.setText(str);
    localImageView.setImageResource(2131230817);
    localTextView.setText(paramEvent.getData().getNotes());
    paramContext = localView.findViewById(2131296752);
    int j = getViewHeight(localTextView);
    int k = getViewHeight(paramContext);
    if (j > i) {
      paramContext.setMinimumHeight(k + (j - i) + 4);
    }
    return localView;
  }
  
  public static View getPositionEventView(Context paramContext, PositionPresenter paramPositionPresenter, Event paramEvent)
  {
    View localView = View.inflate(paramContext, 2131427614, null);
    ImageView localImageView = (ImageView)localView.findViewById(2131296763);
    final TextView localTextView = (TextView)localView.findViewById(2131296762);
    Date localDate = paramEvent.getDate();
    String str = "";
    if (localDate != null) {
      str = getTimeFormatted(paramContext, localDate.getTime() / 1000L);
    }
    ((TextView)localView.findViewById(2131296764)).setText(str);
    paramPositionPresenter.getPositionAsync(paramEvent.getData().getPosition(), new BaseObjectLoadListener()
    {
      public void onEntityLoaded(Position paramAnonymousPosition)
      {
        if (paramAnonymousPosition == null) {
          paramAnonymousPosition = this.val$context.getString(2131755689);
        } else {
          paramAnonymousPosition = paramAnonymousPosition.getName();
        }
        TextView localTextView = localTextView;
        String str = this.val$context.getString(2131755226);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousPosition);
        localTextView.setText(String.format(str, new Object[] { localStringBuilder.toString() }));
      }
      
      public void onError(String paramAnonymousString)
      {
        paramAnonymousString = this.val$context.getString(2131755689);
        TextView localTextView = localTextView;
        String str = this.val$context.getString(2131755226);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("");
        localStringBuilder.append(paramAnonymousString);
        localTextView.setText(String.format(str, new Object[] { localStringBuilder.toString() }));
      }
    });
    localImageView.setImageResource(2131230818);
    return localView;
  }
  
  public static ProgressDialog getProgressDialog(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramContext = new ProgressDialog(paramContext, 2131820550);
    } else {
      paramContext = new ProgressDialog(paramContext);
    }
    paramContext.setMax(100);
    paramContext.setProgressStyle(0);
    paramContext.setIndeterminate(true);
    paramContext.setMessage(paramString);
    paramContext.requestWindowFeature(1);
    paramContext.setCanceledOnTouchOutside(false);
    paramContext.setCancelable(false);
    return paramContext;
  }
  
  public static View getShiftBreakView(Context paramContext, ScheduleBreak paramScheduleBreak)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2131427598, null);
    TextView localTextView1 = (TextView)localView.findViewById(2131296413);
    TextView localTextView2 = (TextView)localView.findViewById(2131296409);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getTimeFormatted(paramContext, paramScheduleBreak.getStartTimestamp()));
    localStringBuilder.append(" - ");
    localStringBuilder.append(getTimeFormatted(paramContext, paramScheduleBreak.getEndTimestamp()));
    localTextView1.setText(localStringBuilder.toString());
    localTextView2.setText(DateUtil.getReadableDurationString(paramScheduleBreak.getStartTimestamp() * 1000L, paramScheduleBreak.getEndTimestamp() * 1000L));
    return localView;
  }
  
  public static SignatureDialogFragment getSignatureDialog()
  {
    return SignatureDialogFragment.newInstance();
  }
  
  public static View getTimeClockInEventView(Context paramContext, TimeClock paramTimeClock)
  {
    View localView = View.inflate(paramContext, 2131427615, null);
    localView.findViewById(2131297508).setVisibility(4);
    ((ImageView)localView.findViewById(2131296763)).setImageResource(2131230840);
    TextView localTextView1 = (TextView)localView.findViewById(2131296762);
    TextView localTextView2 = (TextView)localView.findViewById(2131296764);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(Long.valueOf(paramTimeClock.getInTStamp() * 1000L).longValue());
    paramTimeClock = getTimeFormatted(paramContext, localCalendar.getTime().getTime() / 1000L);
    localTextView1.setText(paramContext.getString(2131755216));
    localTextView2.setText(paramTimeClock);
    return localView;
  }
  
  public static View getTimeClockOutEventView(Context paramContext, TimeClock paramTimeClock)
  {
    View localView = View.inflate(paramContext, 2131427615, null);
    localView.findViewById(2131296752).setVisibility(4);
    ((ImageView)localView.findViewById(2131296763)).setImageResource(2131230841);
    TextView localTextView1 = (TextView)localView.findViewById(2131296762);
    TextView localTextView2 = (TextView)localView.findViewById(2131296764);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(Long.valueOf(paramTimeClock.getOutTStamp() * 1000L).longValue());
    paramTimeClock = getTimeFormatted(paramContext, localCalendar.getTime().getTime() / 1000L);
    localTextView1.setText(paramContext.getString(2131755222));
    localTextView2.setText(paramTimeClock);
    return localView;
  }
  
  public static View getTimeClockPicturesView(Context paramContext, TimeClock paramTimeClock)
  {
    Object localObject = paramTimeClock.getInPictureUrl();
    paramTimeClock = paramTimeClock.getOutPictureUrl();
    if ((TextUtils.isEmpty((CharSequence)localObject)) && (TextUtils.isEmpty(paramTimeClock))) {
      return null;
    }
    View localView = View.inflate(paramContext, 2131427616, null);
    if (!TextUtils.isEmpty((CharSequence)localObject))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://s3.amazonaws.com/uf.shiftplanning.com/");
      localStringBuilder.append((String)localObject);
      UIUtil.setImageWithPlaceHolder(paramContext, localStringBuilder.toString(), (ImageView)localView.findViewById(2131296849), 2131230886);
    }
    if (!TextUtils.isEmpty(paramTimeClock))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("https://s3.amazonaws.com/uf.shiftplanning.com/");
      ((StringBuilder)localObject).append(paramTimeClock);
      UIUtil.setImageWithPlaceHolder(paramContext, ((StringBuilder)localObject).toString(), (ImageView)localView.findViewById(2131297143), 2131230886);
    }
    return localView;
  }
  
  public static String getTimeDayFormatted(Context paramContext, int paramInt, long paramLong)
  {
    if (paramContext == null) {
      return getLocalizedTimeForDateFormat(paramInt, DATE_FORMAT_24_HOURS, paramLong);
    }
    if (is24HourFormat(paramContext)) {
      return getLocalizedTimeForDateFormat(paramInt, DATE_FORMAT_24_HOURS, paramLong);
    }
    return getLocalizedTimeForDateFormat(paramInt, DATE_FORMAT_12_HOURS, paramLong);
  }
  
  public static String getTimeDayFormatted(Context paramContext, long paramLong)
  {
    if (paramContext == null) {
      return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), DATE_FORMAT_24_HOURS, paramLong);
    }
    if (is24HourFormat(paramContext)) {
      return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), DATE_FORMAT_24_HOURS, paramLong);
    }
    return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), DATE_FORMAT_12_HOURS, paramLong);
  }
  
  public static String getTimeDayFormattedInPhoneTimezone(Context paramContext, long paramLong)
  {
    DATE_FORMAT_24_HOURS.setTimeZone(TimeZone.getDefault());
    DATE_FORMAT_12_HOURS.setTimeZone(TimeZone.getDefault());
    if (paramContext == null) {
      return DATE_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    if (is24HourFormat(paramContext)) {
      return DATE_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    return DATE_FORMAT_12_HOURS.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getTimeDayFormattedInUTC(Context paramContext, long paramLong)
  {
    DATE_FORMAT_24_HOURS.setTimeZone(TimeZone.getTimeZone("UTC"));
    DATE_FORMAT_12_HOURS.setTimeZone(TimeZone.getTimeZone("UTC"));
    if (paramContext == null) {
      return DATE_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    if (is24HourFormat(paramContext)) {
      return DATE_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    return DATE_FORMAT_12_HOURS.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getTimeFormatted(Context paramContext, int paramInt, long paramLong)
  {
    if (paramContext == null) {
      return getLocalizedTimeForDateFormat(paramInt, TIME_FORMAT_24_HOURS, paramLong);
    }
    if (is24HourFormat(paramContext)) {
      return getLocalizedTimeForDateFormat(paramInt, TIME_FORMAT_24_HOURS, paramLong);
    }
    return getLocalizedTimeForDateFormat(paramInt, TIME_FORMAT_12_HOURS, paramLong);
  }
  
  public static String getTimeFormatted(Context paramContext, long paramLong)
  {
    if (paramContext == null) {
      return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), TIME_FORMAT_24_HOURS, paramLong);
    }
    if (is24HourFormat(paramContext)) {
      return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), TIME_FORMAT_24_HOURS, paramLong);
    }
    return getLocalizedTimeForDateFormat(CURRENT_EMPLOYEE.getTimeZoneId(), TIME_FORMAT_12_HOURS, paramLong);
  }
  
  public static String getTimeFormattedInPhoneTimezone(Context paramContext, long paramLong)
  {
    TIME_FORMAT_24_HOURS.setTimeZone(TimeZone.getDefault());
    TIME_FORMAT_12_HOURS.setTimeZone(TimeZone.getDefault());
    if (paramContext == null) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    if (is24HourFormat(paramContext)) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    return TIME_FORMAT_12_HOURS.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static String getTimeFormattedInUTC(Context paramContext, long paramLong)
  {
    TIME_FORMAT_24_HOURS.setTimeZone(TimeZone.getTimeZone("UTC"));
    TIME_FORMAT_12_HOURS.setTimeZone(TimeZone.getTimeZone("UTC"));
    if (paramContext == null) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    if (is24HourFormat(paramContext)) {
      return TIME_FORMAT_24_HOURS.format(Long.valueOf(paramLong * 1000L));
    }
    return TIME_FORMAT_12_HOURS.format(Long.valueOf(paramLong * 1000L));
  }
  
  public static View getTipEventView(Context paramContext, Event paramEvent)
  {
    View localView = View.inflate(paramContext, 2131427614, null);
    Object localObject = (ImageView)localView.findViewById(2131296763);
    TextView localTextView = (TextView)localView.findViewById(2131296762);
    Date localDate = paramEvent.getDate();
    String str = "";
    if (localDate != null) {
      str = getTimeFormatted(paramContext, localDate.getTime() / 1000L);
    }
    ((TextView)localView.findViewById(2131296764)).setText(str);
    ((ImageView)localObject).setImageResource(2131230819);
    str = paramContext.getString(2131755922);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("");
    ((StringBuilder)localObject).append(paramEvent.getData().getTips());
    ((StringBuilder)localObject).append(getCurrencyString(paramContext));
    localTextView.setText(String.format(str, new Object[] { ((StringBuilder)localObject).toString() }));
    return localView;
  }
  
  public static long getTypeOfAll(List<DTRObject> paramList)
  {
    if (CURRENT_EMPLOYEE != null)
    {
      if (paramList == null) {
        return -1L;
      }
      long l1 = -1L;
      int i = 0;
      while (i < paramList.size())
      {
        if (((DTRObject)paramList.get(i)).getInitiator().getId() == CURRENT_EMPLOYEE.getId()) {
          return ((DTRObject)paramList.get(i)).getTransactionType();
        }
        if (((DTRObject)paramList.get(i)).getTradeReceivers() != null)
        {
          List localList = ((DTRObject)paramList.get(i)).getTradeReceivers();
          int j = 0;
          for (;;)
          {
            l2 = l1;
            if (j >= localList.size()) {
              break;
            }
            if ((((TradeReceiver)localList.get(j)).getEmployee().getId() == CURRENT_EMPLOYEE.getId()) && (!((TradeReceiver)localList.get(j)).isRejected())) {
              return ((DTRObject)paramList.get(i)).getTransactionType();
            }
            j += 1;
          }
        }
        long l2 = l1;
        if (((DTRObject)paramList.get(i)).getTradeReceivers() == null)
        {
          l2 = l1;
          if (((DTRObject)paramList.get(i)).getTransactionType() == 0L) {
            l2 = 0L;
          }
        }
        i += 1;
        l1 = l2;
      }
      return l1;
    }
    return -1L;
  }
  
  private static int getViewHeight(View paramView)
  {
    Display localDisplay = ((WindowManager)paramView.getContext().getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    localDisplay.getSize(localPoint);
    paramView.measure(View.MeasureSpec.makeMeasureSpec(localPoint.x, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
    return paramView.getMeasuredHeight();
  }
  
  public static ArrayList<WidgetSettingsActivity.WidgetOrder> getWidgetsOrder(Context paramContext)
  {
    AdminBusinessResponse localAdminBusinessResponse = PrefHelper.getBusinessPrefs();
    Object localObject1 = PrefHelper.getString("prefs:widget_settings");
    boolean bool = TextUtils.isEmpty((CharSequence)localObject1);
    int m = 0;
    int k = 0;
    Object localObject2;
    int i;
    int j;
    if (bool)
    {
      localObject2 = new ArrayList();
      if (!Employee.restrictAccount(CURRENT_EMPLOYEE)) {
        ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755560), 0, 0));
      }
      if ((localAdminBusinessResponse != null) && ((localAdminBusinessResponse.getEmployeeCanRelease().booleanValue()) || (localAdminBusinessResponse.getEmployeeCanTrade().booleanValue()) || (localAdminBusinessResponse.getEmployeeCanDrop().booleanValue())) && (!Employee.restrictAccount(CURRENT_EMPLOYEE))) {
        ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755626), 1, 1));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.getTimeClockEnabled().booleanValue()) && ((localAdminBusinessResponse.getOnNow().booleanValue()) || (Employee.checkForManagerPermission(CURRENT_EMPLOYEE)))) {
        i = 1;
      } else {
        i = 0;
      }
      j = k;
      if (localAdminBusinessResponse != null)
      {
        j = k;
        if (localAdminBusinessResponse.getLeaveEnabled().booleanValue())
        {
          j = k;
          if (Employee.checkModifyOrSupervise(CURRENT_EMPLOYEE)) {
            j = 1;
          }
        }
      }
      if ((i != 0) || (j != 0)) {
        ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755352), 2, 2));
      }
      if ((localAdminBusinessResponse != null) && (!Employee.restrictAccount(CURRENT_EMPLOYEE))) {
        ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755648), 4, 3));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.getMessageWallEnabled().booleanValue()) && (!Employee.restrictAccount(CURRENT_EMPLOYEE))) {
        ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755534), 5, 4));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.getTrainingEnabled().booleanValue()) && (!Employee.restrictAccount(CURRENT_EMPLOYEE))) {
        ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755945), 6, 5));
      }
      localObject1 = localObject2;
      if (localAdminBusinessResponse != null)
      {
        localObject1 = localObject2;
        if (localAdminBusinessResponse.showUpcomingBirthdays().booleanValue())
        {
          ((ArrayList)localObject2).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755129), 7, 6));
          return localObject2;
        }
      }
    }
    else
    {
      localObject2 = (List)GsonProvider.getInstance().getNullNotSerialized().fromJson((String)localObject1, new TypeToken() {}.getType());
      localObject1 = new ArrayList();
      ((ArrayList)localObject1).addAll((Collection)localObject2);
      if ((localAdminBusinessResponse != null) && ((localAdminBusinessResponse.getEmployeeCanRelease().booleanValue()) || (localAdminBusinessResponse.getEmployeeCanTrade().booleanValue()) || (localAdminBusinessResponse.getEmployeeCanDrop().booleanValue())) && (!contains((ArrayList)localObject1, 1)) && (!Employee.restrictAccount(CURRENT_EMPLOYEE))) {
        ((ArrayList)localObject1).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755626), 1, 1));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.getTimeClockEnabled().booleanValue()) && ((localAdminBusinessResponse.getOnNow().booleanValue()) || (Employee.checkForManagerPermission(CURRENT_EMPLOYEE)))) {
        i = 1;
      } else {
        i = 0;
      }
      j = m;
      if (localAdminBusinessResponse != null)
      {
        j = m;
        if (localAdminBusinessResponse.getLeaveEnabled().booleanValue())
        {
          j = m;
          if (Employee.checkModifyOrSupervise(CURRENT_EMPLOYEE)) {
            j = 1;
          }
        }
      }
      if (((i != 0) || (j != 0)) && (!contains((ArrayList)localObject1, 2))) {
        ((ArrayList)localObject1).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755352), 2, 2));
      }
      if ((localAdminBusinessResponse != null) && (!Employee.restrictAccount(CURRENT_EMPLOYEE)) && (!contains((ArrayList)localObject1, 3))) {
        ((ArrayList)localObject1).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755648), 4, 3));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.getMessageWallEnabled().booleanValue()) && (!Employee.restrictAccount(CURRENT_EMPLOYEE)) && (!contains((ArrayList)localObject1, 4))) {
        ((ArrayList)localObject1).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755534), 5, 4));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.getTrainingEnabled().booleanValue()) && (!contains((ArrayList)localObject1, 5)) && (!Employee.restrictAccount(CURRENT_EMPLOYEE))) {
        ((ArrayList)localObject1).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755945), 6, 5));
      }
      if ((localAdminBusinessResponse != null) && (localAdminBusinessResponse.showUpcomingBirthdays().booleanValue()) && (!contains((ArrayList)localObject1, 6))) {
        ((ArrayList)localObject1).add(new WidgetSettingsActivity.WidgetOrder(paramContext.getString(2131755129), 7, 6));
      }
      Collections.sort((List)localObject1, new Comparator()
      {
        public int compare(WidgetSettingsActivity.WidgetOrder paramAnonymousWidgetOrder1, WidgetSettingsActivity.WidgetOrder paramAnonymousWidgetOrder2)
        {
          return Integer.compare(paramAnonymousWidgetOrder1.getIndex(), paramAnonymousWidgetOrder2.getIndex());
        }
      });
    }
    return localObject1;
  }
  
  public static void hideSoftKeyboard(Activity paramActivity, View paramView)
  {
    paramActivity = (InputMethodManager)paramActivity.getSystemService("input_method");
    if (paramView == null) {
      return;
    }
    paramActivity.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
  }
  
  public static void initCalendar(MaterialCalendarView paramMaterialCalendarView, CalendarDay paramCalendarDay)
  {
    MaterialCalendarView.setupCalendar(PrefHelper.getStartOfWeek());
    paramMaterialCalendarView.state().edit().setFirstDayOfWeek(PrefHelper.getStartOfWeek()).setMinimumDate(paramCalendarDay).setMaximumDate(CalendarDay.from(2030, 1, 1)).setCalendarDisplayMode(CalendarMode.WEEKS).commit();
  }
  
  public static boolean is24HourFormat(Context paramContext)
  {
    return DateFormat.is24HourFormat(paramContext);
  }
  
  public static boolean isGpsEnabled(Context paramContext)
  {
    boolean bool1 = false;
    if (paramContext == null) {
      return false;
    }
    android.location.LocationManager localLocationManager = (android.location.LocationManager)paramContext.getSystemService("location");
    try
    {
      boolean bool2 = localLocationManager.isProviderEnabled("gps");
      bool1 = bool2;
    }
    catch (Exception localException)
    {
      Dump.error(localException.getMessage());
    }
    if (!bool1)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext, 2131820550);
      localBuilder.setMessage(paramContext.getString(2131755430));
      localBuilder.setPositiveButton(paramContext.getString(2131755644), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Intent localIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
          this.val$context.startActivity(localIntent);
          paramAnonymousDialogInterface.dismiss();
        }
      });
      localBuilder.setNegativeButton(paramContext.getString(17039360), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
        }
      });
      localBuilder.show();
    }
    return bool1;
  }
  
  private static boolean isMockSettingsOn(Context paramContext)
  {
    return Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0") ^ true;
  }
  
  public static boolean isThereChanceThatLocationIsFake(Context paramContext)
  {
    return (isMockSettingsOn(paramContext)) && (areThereMockPermissionApps(paramContext));
  }
  
  public static void setBackgroundColor(View paramView, int paramInt)
  {
    ((GradientDrawable)paramView.getBackground()).setColor(paramInt);
  }
  
  public static void setNewEmployee()
  {
    CURRENT_EMPLOYEE = PrefHelper.getCurrentEmployee();
  }
  
  public static boolean shouldStartActivityOrSendClockOut(TimeClock paramTimeClock)
  {
    AdminBusinessResponse localAdminBusinessResponse = PrefHelper.getBusinessPrefs();
    paramTimeClock = paramTimeClock.getEvents();
    boolean bool = false;
    int m = 0;
    int i = m;
    int j = i;
    int k = j;
    int n = j;
    j = i;
    i = m;
    while (i < paramTimeClock.size())
    {
      m = n;
      if (((Event)paramTimeClock.get(i)).getType() == 6L) {
        m = 1;
      }
      if (((Event)paramTimeClock.get(i)).getType() == 4L) {
        k = 1;
      }
      if (((Event)paramTimeClock.get(i)).getType() == 3L) {
        j = 1;
      }
      i += 1;
      n = m;
    }
    if (((localAdminBusinessResponse.getRequireNotes().booleanValue()) && (j == 0)) || ((localAdminBusinessResponse.getRequirePosition().booleanValue()) && (n == 0)) || ((localAdminBusinessResponse.getRequireRemote().booleanValue()) && (k == 0)) || (localAdminBusinessResponse.getRequireTimeClockGPS().booleanValue()) || (localAdminBusinessResponse.getCameraEnabled().booleanValue())) {
      bool = true;
    }
    return bool;
  }
  
  public static abstract interface DialogListener
  {
    public abstract void onPositive();
  }
  
  public static abstract interface DialogTextListener
  {
    public abstract void onTextEntered(String paramString);
  }
}
