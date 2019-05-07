package com.appxy.planner.large.fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.CalendarContract.Calendars;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.planner.MyApplication;
import com.appxy.planner.activity.SettingPrefrence;
import com.appxy.planner.dao.DOFragmentNeed;
import com.appxy.planner.dao.Settingsdao;
import com.appxy.planner.db.CalenHelper;
import com.appxy.planner.db.PlannerDb;
import com.appxy.planner.floatingactionbutton.FloatingActionButton;
import com.appxy.planner.floatingactionbutton.FloatingActionsMenu;
import com.appxy.planner.helper.DateTrans;
import com.appxy.planner.helper.Utils;
import com.appxy.planner.helper.WeekHelper;
import com.appxy.planner.implement.FragmentInterface;
import com.appxy.planner.implement.ViewRefresh;
import com.appxy.planner.large.activity.CalenListActivity;
import com.appxy.planner.large.activity.NewEventActivity;
import com.appxy.planner.large.activity.NewTaskView;
import com.appxy.planner.large.activity.NoteView;
import com.appxy.planner.large.adapter.monthfragmentviewpager;
import com.appxy.planner.large.helper.Addmonthview;
import com.appxy.planner.large.helper.CalenDateHelper;
import com.appxy.planner.large.helper.Fragment2ActivityInterface;
import com.appxy.planner.large.helper.SearchHelper;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

@SuppressLint({"ValidFragment"})
public class MiniMonthFragment
  extends Fragment
  implements View.OnClickListener, ViewPager.OnPageChangeListener, ViewRefresh, FragmentInterface
{
  private static Handler mHandler2;
  private static Runnable mRunnable;
  private FloatingActionsMenu actionmenu;
  private FloatingActionButton addevent;
  private FloatingActionButton addnote;
  private FloatingActionButton addtask;
  private int calendarheight;
  private int calendarwidth;
  private Activity context;
  private DateTrans dateTrans;
  private PlannerDb db;
  private boolean firstcoming;
  private ImageButton m2TodayImageb = null;
  private int mActionBarHeight;
  private Context mContext;
  private DOFragmentNeed mDoFragmentNeed;
  private GregorianCalendar mFetureCalendar;
  private Fragment2ActivityInterface mFragment2ActivityInterface;
  private Handler mHandler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.getData().getInt("item");
      MiniMonthFragment.this.mItems.add(Integer.valueOf(i));
      paramAnonymousMessage = new GregorianCalendar(TimeZone.getTimeZone(MiniMonthFragment.this.settingsdao.getgTimeZone()));
      paramAnonymousMessage.add(2, i - MyApplication.Loop / 2);
      MyApplication.WithMonth = i - MyApplication.Loop / 2;
      if (MiniMonthFragment.this.mIsNew) {
        MiniMonthFragment.access$302(MiniMonthFragment.this, paramAnonymousMessage);
      }
      for (;;)
      {
        MiniMonthFragment.this.mFragment2ActivityInterface.getDate2Show(paramAnonymousMessage, MyApplication.viewpagertypemonth);
        MyApplication.FromMon = true;
        MyApplication.DelEventGre = (GregorianCalendar)paramAnonymousMessage.clone();
        MiniMonthFragment.this.WithWeek(paramAnonymousMessage);
        return false;
        paramAnonymousMessage = MiniMonthFragment.this.mNowCalendar;
        MiniMonthFragment.access$202(MiniMonthFragment.this, true);
      }
    }
  });
  private float mHeight;
  private boolean mIsNew = true;
  private ArrayList<Integer> mItems = new ArrayList();
  private ImageButton mNewEvent = null;
  private GregorianCalendar mNowCalendar;
  private GregorianCalendar mOldCalendar;
  private int mStateBarHeight;
  private int mSumDaysHeight;
  private TextView mTV1;
  private TextView mTV2;
  private TextView mTV3;
  private TextView mTV4;
  private TextView mTV5;
  private TextView mTV6;
  private TextView mTV7;
  private Date mToday = null;
  private ViewPager mViewPager = null;
  private int mWeekHeight;
  private String[] mWeekStr = new String[7];
  private LinearLayout mWeek_Layout = null;
  private View mWeeksView = null;
  private float mWidth;
  private RelativeLayout mainview_today_rl;
  private TextView mainview_today_tv;
  private TextView mianview_month_tv;
  private RelativeLayout mianview_more_rl;
  private TextView mianview_tasks_tv;
  private TextView mianview_year_tv;
  private RelativeLayout mianview_yearminus_rl;
  private RelativeLayout mianview_yearplus_rl;
  private monthfragmentviewpager monthadapter;
  private SearchHelper searchHelper;
  private Settingsdao settingsdao;
  private ArrayList<Settingsdao> settingsdaoslist;
  private RelativeLayout split_action_bar = null;
  private TextView task_over_num;
  private RelativeLayout task_over_num_rl;
  private Typeface typeface;
  private Typeface typeface1;
  private String userID;
  private boolean yaogengxin;
  
  public MiniMonthFragment() {}
  
  public MiniMonthFragment(Context paramContext, DOFragmentNeed paramDOFragmentNeed)
  {
    this.mContext = paramContext;
    this.mDoFragmentNeed = paramDOFragmentNeed;
    this.dateTrans = new DateTrans((Activity)this.mContext);
    this.mToday = paramDOFragmentNeed.getToday();
    this.db = new PlannerDb((Activity)this.mContext);
    this.settingsdaoslist = this.db.getAllsetting();
    this.settingsdao = ((Settingsdao)this.settingsdaoslist.get(0));
    this.mFetureCalendar = new GregorianCalendar(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
    this.mOldCalendar = new GregorianCalendar(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
    paramContext = (WindowManager)this.mContext.getSystemService("window");
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("first", 1);
    this.calendarheight = localSharedPreferences.getInt("calendarheight", 0);
    this.calendarwidth = localSharedPreferences.getInt("calendarwidth", 0);
    if (this.calendarwidth == 0) {
      this.mWidth = paramContext.getDefaultDisplay().getWidth();
    }
    for (this.mHeight = paramContext.getDefaultDisplay().getHeight();; this.mHeight = this.calendarheight)
    {
      MyApplication.calendarnotewidth = (int)this.mWidth;
      MyApplication.calendarnoteheight = (int)this.mHeight;
      this.mStateBarHeight = paramDOFragmentNeed.getStateBarHeight();
      this.mActionBarHeight = paramDOFragmentNeed.getActionBarHeight();
      this.mNowCalendar = new GregorianCalendar(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
      return;
      this.mWidth = this.calendarwidth;
    }
  }
  
  private void WithWeek(GregorianCalendar paramGregorianCalendar)
  {
    GregorianCalendar localGregorianCalendar1 = (GregorianCalendar)paramGregorianCalendar.clone();
    GregorianCalendar localGregorianCalendar2 = new GregorianCalendar(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
    int j = 0;
    int k = 0;
    int i = 0;
    localGregorianCalendar1.setFirstDayOfWeek(WeekHelper.getFirstDayOfWeek2Compute(MyApplication.weekstring[this.settingsdao.getgFirstDay().intValue()]));
    localGregorianCalendar2.setFirstDayOfWeek(WeekHelper.getFirstDayOfWeek2Compute(MyApplication.weekstring[this.settingsdao.getgFirstDay().intValue()]));
    if ((localGregorianCalendar2.get(1) == localGregorianCalendar1.get(1)) && (localGregorianCalendar2.get(2) == localGregorianCalendar1.get(2))) {
      MyApplication.mTempCurrentGre = (GregorianCalendar)paramGregorianCalendar.clone();
    }
    for (;;)
    {
      MyApplication.withWeek = i;
      return;
      int m = localGregorianCalendar1.get(1);
      int n = localGregorianCalendar1.get(2);
      int i1 = CalenDateHelper.getDaysOfMonth(localGregorianCalendar1.isLeapYear(m), n);
      i = CalenDateHelper.getWeekdayOfMonth1(m, n);
      int i2 = localGregorianCalendar1.getFirstDayOfWeek() - 1;
      if (i - i2 < 0) {
        i = 7 - (i2 - i);
      }
      for (;;)
      {
        CalenDateHelper.getWeekdayOfMonth2(m, n, i1);
        localGregorianCalendar1.set(5, 1);
        localGregorianCalendar1.add(5, -i);
        MyApplication.mTempCurrentGre = (GregorianCalendar)localGregorianCalendar1.clone();
        i = k;
        if (!localGregorianCalendar2.after(localGregorianCalendar1)) {
          break label287;
        }
        for (;;)
        {
          i = j;
          if (localGregorianCalendar1.get(3) == localGregorianCalendar2.get(3)) {
            break;
          }
          localGregorianCalendar1.add(5, 7);
          j -= 1;
        }
        i -= i2;
      }
      label287:
      while (localGregorianCalendar1.get(3) != localGregorianCalendar2.get(3))
      {
        localGregorianCalendar2.add(5, 7);
        i += 1;
      }
    }
  }
  
  public static void getnew()
  {
    if (mHandler2 != null) {
      mHandler2.postDelayed(mRunnable, 4L);
    }
  }
  
  private void handleSplitClick(int paramInt)
  {
    this.mNowCalendar.add(2, paramInt);
    this.mIsNew = false;
    this.mViewPager.setCurrentItem(((Integer)this.mItems.get(this.mItems.size() - 1)).intValue() + paramInt);
  }
  
  private void setWeeksHeight(int paramInt)
  {
    TextView localTextView = (TextView)this.mWeeksView.findViewById(2131625125);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setGravity(21);
    localTextView.setText(this.mWeekStr[0]);
    localTextView.setTypeface(this.typeface);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625126);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setText(this.mWeekStr[1]);
    localTextView.setTypeface(this.typeface);
    localTextView.setGravity(21);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625127);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setText(this.mWeekStr[2]);
    localTextView.setTypeface(this.typeface);
    localTextView.setGravity(21);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625128);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setText(this.mWeekStr[3]);
    localTextView.setTypeface(this.typeface);
    localTextView.setGravity(21);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625129);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setText(this.mWeekStr[4]);
    localTextView.setTypeface(this.typeface);
    localTextView.setGravity(21);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625130);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setText(this.mWeekStr[5]);
    localTextView.setTypeface(this.typeface);
    localTextView.setGravity(21);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625131);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setText(this.mWeekStr[6]);
    localTextView.setTypeface(this.typeface);
    localTextView.setGravity(21);
    localTextView = (TextView)this.mWeeksView.findViewById(2131625132);
    localTextView.setWidth((int)(this.mWidth / 8.0F));
    localTextView.setHeight(paramInt);
    localTextView.setVisibility(8);
    localTextView.setTypeface(this.typeface);
  }
  
  public void fragmentrefresh()
  {
    MyApplication.needupdate = true;
    mHandler2.postDelayed(mRunnable, 40L);
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mFragment2ActivityInterface = ((Fragment2ActivityInterface)paramActivity);
    this.context = paramActivity;
    mHandler2 = new Handler();
    this.firstcoming = true;
    paramActivity = this.context.getPackageName();
    paramActivity = this.context.getSharedPreferences(paramActivity + "_preferences", 0);
    this.db = new PlannerDb(this.context);
    this.userID = paramActivity.getString("userID", "");
    this.dateTrans = new DateTrans((Activity)this.mContext);
    this.typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/HELVETICANEUELTPRO-ROMAN.OTF");
    this.typeface1 = Typeface.createFromAsset(this.context.getAssets(), "fonts/HELVETICANEUELTPRO-MD.OTF");
    mRunnable = new Runnable()
    {
      public void run()
      {
        View localView = MiniMonthFragment.this.mViewPager.findViewWithTag(Integer.valueOf(MyApplication.oldnum));
        GregorianCalendar localGregorianCalendar = new GregorianCalendar();
        localGregorianCalendar.setTimeZone(TimeZone.getTimeZone(MiniMonthFragment.this.settingsdao.getgTimeZone()));
        localGregorianCalendar.setFirstDayOfWeek(WeekHelper.getFirstDayOfWeek2Compute(MyApplication.weekstring[MiniMonthFragment.this.settingsdao.getgFirstDay().intValue()]));
        localGregorianCalendar.add(2, MyApplication.oldnum - MyApplication.Loop / 2);
        MyApplication.whichyear = localGregorianCalendar.get(1);
        MyApplication.whichmonth = localGregorianCalendar.get(2) + 1;
        MyApplication.whichday = localGregorianCalendar.get(5);
        DOFragmentNeed localDOFragmentNeed = new DOFragmentNeed();
        if (localView != null)
        {
          TextView localTextView = (TextView)localView.findViewById(2131624351);
          if ((MyApplication.needupdate) || (!localTextView.getText().toString().equals("ishave")))
          {
            new Addmonthview(MiniMonthFragment.this.context, localGregorianCalendar, localDOFragmentNeed, MiniMonthFragment.this.db, MiniMonthFragment.this.dateTrans, localView, true, MyApplication.oldnum, MiniMonthFragment.this.actionmenu, MiniMonthFragment.this.settingsdao, MiniMonthFragment.this.typeface, MiniMonthFragment.this.userID);
            MyApplication.needupdate = false;
          }
        }
      }
    };
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 2131624346: 
      if (new CalenHelper().getshowcalendars(this.context, 500).size() > 0)
      {
        paramView = new Intent();
        localBundle = new Bundle();
        localObject = new StringBuilder().append(MyApplication.whichyear).append("-");
        DateTrans localDateTrans = this.dateTrans;
        localObject = ((StringBuilder)localObject).append(DateTrans.changedate(MyApplication.whichmonth)).append("-");
        localDateTrans = this.dateTrans;
        localBundle.putString("startdate", DateTrans.changedate(MyApplication.whichday));
        localBundle.putInt("update", 0);
        localBundle.putInt("newmode", 0);
        localObject = new GregorianCalendar(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
        ((GregorianCalendar)localObject).set(MyApplication.whichyear, MyApplication.whichmonth - 1, MyApplication.whichday);
        if (((GregorianCalendar)localObject).get(12) > 30)
        {
          ((GregorianCalendar)localObject).set(11, ((GregorianCalendar)localObject).get(11) + 1);
          ((GregorianCalendar)localObject).set(12, 0);
        }
        for (;;)
        {
          localBundle.putInt("allday", 0);
          localBundle.putInt("halfHour", 0);
          localBundle.putString("title", "");
          localBundle.putSerializable("calendar", (Serializable)localObject);
          localBundle.putInt("setting", Integer.parseInt(this.settingsdao.geteDefaultCalendarID()));
          paramView.putExtras(localBundle);
          paramView.setClass(this.context, NewEventActivity.class);
          startActivity(paramView);
          this.actionmenu.collapse();
          this.actionmenu.setVisibility(8);
          return;
          ((GregorianCalendar)localObject).set(11, ((GregorianCalendar)localObject).get(11));
          ((GregorianCalendar)localObject).set(12, 30);
        }
      }
      Toast.makeText(this.context, getString(2131165295), 0).show();
      return;
    case 2131624347: 
      paramView = new Intent();
      localBundle = new Bundle();
      localObject = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
      ((GregorianCalendar)localObject).set(MyApplication.whichyear, MyApplication.whichmonth - 1, MyApplication.whichday);
      localBundle.putLong("startdate", ((GregorianCalendar)localObject).getTimeInMillis());
      localBundle.putInt("update", 0);
      paramView.putExtras(localBundle);
      paramView.setClass(this.context, NewTaskView.class);
      startActivity(paramView);
      this.actionmenu.collapse();
      this.actionmenu.setVisibility(8);
      return;
    }
    paramView = new Intent();
    Bundle localBundle = new Bundle();
    Object localObject = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    ((GregorianCalendar)localObject).set(MyApplication.whichyear, MyApplication.whichmonth - 1, MyApplication.whichday);
    localBundle.putLong("startdate", ((GregorianCalendar)localObject).getTimeInMillis());
    localBundle.putInt("update", 0);
    paramView.putExtras(localBundle);
    paramView.setClass(this.context, NoteView.class);
    startActivity(paramView);
    this.actionmenu.collapse();
    this.actionmenu.setVisibility(8);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903148, paramViewGroup, false);
    this.actionmenu = ((FloatingActionsMenu)paramLayoutInflater.findViewById(2131624345));
    this.mWeek_Layout = ((LinearLayout)paramLayoutInflater.findViewById(2131624573));
    this.mWeeksView = LayoutInflater.from(this.context).inflate(2130903263, null);
    this.mWeek_Layout.addView(this.mWeeksView);
    this.mianview_month_tv = ((TextView)this.context.findViewById(2131624085));
    this.mianview_year_tv = ((TextView)this.context.findViewById(2131624086));
    this.mainview_today_tv = ((TextView)this.context.findViewById(2131624097));
    this.mainview_today_rl = ((RelativeLayout)this.context.findViewById(2131624096));
    this.mianview_more_rl = ((RelativeLayout)this.context.findViewById(2131624098));
    this.mianview_yearplus_rl = ((RelativeLayout)this.context.findViewById(2131624087));
    this.mianview_tasks_tv = ((TextView)this.context.findViewById(2131624088));
    this.task_over_num = ((TextView)this.context.findViewById(2131624094));
    this.task_over_num_rl = ((RelativeLayout)this.context.findViewById(2131624093));
    this.addevent = ((FloatingActionButton)paramLayoutInflater.findViewById(2131624346));
    this.addtask = ((FloatingActionButton)paramLayoutInflater.findViewById(2131624347));
    this.addnote = ((FloatingActionButton)paramLayoutInflater.findViewById(2131624348));
    this.mianview_month_tv.setVisibility(0);
    this.mianview_year_tv.setVisibility(8);
    this.mianview_tasks_tv.setVisibility(8);
    this.mianview_yearminus_rl = ((RelativeLayout)this.context.findViewById(2131624083));
    this.addevent.setbackIcon(2130837787);
    this.addtask.setbackIcon(2130837793);
    this.addnote.setbackIcon(2130837790);
    this.addevent.setOnClickListener(this);
    this.addtask.setOnClickListener(this);
    this.addnote.setOnClickListener(this);
    this.mianview_more_rl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final PopupWindow localPopupWindow;
        LinearLayout localLinearLayout1;
        LinearLayout localLinearLayout2;
        LinearLayout localLinearLayout3;
        LinearLayout localLinearLayout4;
        if (MyApplication.shoufei == 1)
        {
          paramAnonymousView = MiniMonthFragment.this.context.getLayoutInflater().inflate(2130903079, null);
          localPopupWindow = new PopupWindow(paramAnonymousView, -2, -2);
          localPopupWindow.setBackgroundDrawable(MiniMonthFragment.this.getResources().getDrawable(2130838003));
          localPopupWindow.setOutsideTouchable(true);
          localPopupWindow.update();
          localPopupWindow.setTouchable(true);
          localPopupWindow.setFocusable(true);
          if (!localPopupWindow.isShowing()) {
            localPopupWindow.showAsDropDown(MiniMonthFragment.this.mianview_more_rl);
          }
          localLinearLayout1 = (LinearLayout)paramAnonymousView.findViewById(2131624186);
          localLinearLayout2 = (LinearLayout)paramAnonymousView.findViewById(2131624184);
          localLinearLayout3 = (LinearLayout)paramAnonymousView.findViewById(2131624201);
          Object localObject = (LinearLayout)paramAnonymousView.findViewById(2131624188);
          localLinearLayout4 = (LinearLayout)paramAnonymousView.findViewById(2131624190);
          localLinearLayout4.setVisibility(0);
          ((LinearLayout)localObject).setVisibility(8);
          localLinearLayout1.setVisibility(8);
          localObject = (TextView)paramAnonymousView.findViewById(2131624189);
          TextView localTextView1 = (TextView)paramAnonymousView.findViewById(2131624187);
          TextView localTextView2 = (TextView)paramAnonymousView.findViewById(2131624185);
          TextView localTextView3 = (TextView)paramAnonymousView.findViewById(2131624191);
          TextView localTextView4 = (TextView)paramAnonymousView.findViewById(2131624200);
          TextView localTextView5 = (TextView)paramAnonymousView.findViewById(2131624202);
          ((TextView)localObject).setTypeface(MiniMonthFragment.this.typeface);
          localTextView1.setTypeface(MiniMonthFragment.this.typeface);
          localTextView2.setTypeface(MiniMonthFragment.this.typeface);
          localTextView3.setTypeface(MiniMonthFragment.this.typeface);
          localTextView5.setTypeface(MiniMonthFragment.this.typeface);
          localTextView4.setTypeface(MiniMonthFragment.this.typeface);
          localTextView1.setText("Calendars");
          localLinearLayout4.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              MiniMonthFragment.this.refreshCalendars();
              MyApplication.needupdate = true;
              Toast.makeText(MiniMonthFragment.this.context, "Syncing calendars...", 0).show();
              MiniMonthFragment.mHandler2.postDelayed(MiniMonthFragment.mRunnable, 40L);
            }
          });
          if (MyApplication.shoufei != 1) {
            break label405;
          }
          ((LinearLayout)paramAnonymousView.findViewById(2131624196)).setVisibility(8);
        }
        for (;;)
        {
          localLinearLayout1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = new Intent();
              paramAnonymous2View.setClass(MiniMonthFragment.this.context, CalenListActivity.class);
              MiniMonthFragment.this.startActivityForResult(paramAnonymous2View, 3);
            }
          });
          localLinearLayout2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              MiniMonthFragment.access$1602(MiniMonthFragment.this, new SearchHelper(MiniMonthFragment.this.context, MiniMonthFragment.this.mianview_more_rl, MiniMonthFragment.this.db, MiniMonthFragment.this.dateTrans, MiniMonthFragment.this.settingsdao, MiniMonthFragment.this));
            }
          });
          localLinearLayout3.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = new Intent();
              paramAnonymous2View.setClass(MiniMonthFragment.this.context, SettingPrefrence.class);
              MiniMonthFragment.this.startActivityForResult(paramAnonymous2View, 3);
            }
          });
          return;
          paramAnonymousView = MiniMonthFragment.this.context.getLayoutInflater().inflate(2130903080, null);
          break;
          label405:
          localLinearLayout4 = (LinearLayout)paramAnonymousView.findViewById(2131624203);
          localLinearLayout2.setVisibility(0);
          ((TextView)paramAnonymousView.findViewById(2131624204)).setTypeface(MiniMonthFragment.this.typeface);
          localLinearLayout4.setVisibility(8);
          localLinearLayout4.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              switch (MyApplication.banben)
              {
              default: 
                return;
              case 1: 
                Object localObject = MiniMonthFragment.this.context.getPackageManager().getInstalledApplications(0);
                int j = ((List)localObject).size();
                paramAnonymous2View = null;
                int i = 0;
                while (i < j)
                {
                  if (((ApplicationInfo)((List)localObject).get(i)).packageName.equals("com.android.vending")) {
                    paramAnonymous2View = (ApplicationInfo)((List)localObject).get(i);
                  }
                  i += 1;
                }
                if (paramAnonymous2View != null)
                {
                  localObject = new Intent("android.intent.action.VIEW");
                  ((Intent)localObject).setData(Uri.parse("market://details?id=com.appxy.plannerplus&hl=en"));
                  ((Intent)localObject).setPackage(paramAnonymous2View.packageName);
                  MiniMonthFragment.this.startActivity((Intent)localObject);
                  return;
                }
                MiniMonthFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appxy.plannerplus")));
                return;
              case 2: 
                try
                {
                  paramAnonymous2View = new Intent("android.intent.action.VIEW");
                  paramAnonymous2View.setData(Uri.parse("amzn://apps/android?asin=B00KA9WHMU"));
                  MiniMonthFragment.this.startActivity(paramAnonymous2View);
                  return;
                }
                catch (ActivityNotFoundException paramAnonymous2View)
                {
                  paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/Planner-Plus-Schedule-Personal-Organizer/dp/B00KA9WHMU/ref=zg_bs_2478859011_44"));
                  MiniMonthFragment.this.startActivity(paramAnonymous2View);
                  return;
                }
              }
              try
              {
                paramAnonymous2View = new Intent("android.intent.action.VIEW");
                paramAnonymous2View.setData(Uri.parse("samsungapps://ProductDetail/com.appxy.plannerplus"));
                MiniMonthFragment.this.startActivity(paramAnonymous2View);
                return;
              }
              catch (ActivityNotFoundException paramAnonymous2View)
              {
                paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://apps.samsung.com/mars/topApps/topAppsDetail.as?productId=000000846370"));
                MiniMonthFragment.this.startActivity(paramAnonymous2View);
              }
            }
          });
        }
      }
    });
    this.mainview_today_rl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyApplication.oldnum = 6000;
        paramAnonymousView = Calendar.getInstance();
        paramAnonymousView.setTimeZone(TimeZone.getTimeZone(MiniMonthFragment.this.settingsdao.getgTimeZone()));
        MyApplication.whichday = paramAnonymousView.get(5);
        MyApplication.whichweek = paramAnonymousView.get(3);
        MyApplication.whichmonth = paramAnonymousView.get(2) + 1;
        MyApplication.whichyear = paramAnonymousView.get(1);
        MyApplication.weekwhich = paramAnonymousView.get(7);
        MyApplication.whichdayofyear = paramAnonymousView.get(6);
        MiniMonthFragment.this.mianview_month_tv.setText(MyApplication.monthStringsall[(MyApplication.whichmonth - 1)] + MyApplication.whichyear);
        MiniMonthFragment.this.mianview_year_tv.setText(MyApplication.whichyear + "");
        MiniMonthFragment.this.mViewPager.setCurrentItem(MyApplication.Loop / 2);
      }
    });
    this.mianview_yearplus_rl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyApplication.whichyear += 1;
        MiniMonthFragment.this.setcurrentpage();
      }
    });
    this.mianview_yearminus_rl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyApplication.whichyear -= 1;
        MiniMonthFragment.this.setcurrentpage();
      }
    });
    paramViewGroup = this.mWeek_Layout.getLayoutParams();
    this.mWeekHeight = Utils.dip2px(this.context, 39.0F);
    paramViewGroup.height = this.mWeekHeight;
    this.settingsdaoslist = this.db.getAllsetting();
    this.settingsdao = ((Settingsdao)this.settingsdaoslist.get(0));
    this.mSumDaysHeight = ((int)(this.mHeight - this.mStateBarHeight - this.mActionBarHeight * 2 - this.mWeekHeight));
    this.mWeekStr = WeekHelper.getFirstDayOfWeek2Show(MyApplication.weekstring[this.settingsdao.getgFirstDay().intValue()]);
    setWeeksHeight(this.mWeekHeight);
    this.mDoFragmentNeed = new DOFragmentNeed();
    this.mDoFragmentNeed.setSumDaysHeight(this.mSumDaysHeight);
    this.mDoFragmentNeed.setWeekHeight(this.mWeekHeight);
    this.mViewPager = ((ViewPager)paramLayoutInflater.findViewById(2131624574));
    this.mViewPager.setOnPageChangeListener(this);
    this.mViewPager.setOffscreenPageLimit(1);
    this.monthadapter = new monthfragmentviewpager(this.context, this.db, this.dateTrans, this.settingsdao);
    this.mViewPager.setAdapter(this.monthadapter);
    paramViewGroup = Calendar.getInstance();
    paramViewGroup.setTimeZone(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
    int i = paramViewGroup.get(1);
    int j = paramViewGroup.get(2);
    MyApplication.oldnum = 6000;
    MyApplication.WithMonth = MyApplication.whichyear * 12 + MyApplication.whichmonth - i * 12 - (j + 1);
    MyApplication.oldnum += MyApplication.WithMonth;
    this.mViewPager.setCurrentItem(MyApplication.Loop / 2 + MyApplication.WithMonth);
    System.out.println(MyApplication.oldnum + "oldnum");
    return paramLayoutInflater;
  }
  
  public void onPageScrollStateChanged(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 2)) {}
    do
    {
      return;
      Log.e("mtest", "======>MiniMonthFragment======>  onPageScrollStateChanged  " + MyApplication.oldnum + "      " + this.yaogengxin);
    } while (!this.yaogengxin);
    mHandler2.postDelayed(mRunnable, 0L);
    this.yaogengxin = false;
  }
  
  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    MyApplication.noteupdate = false;
    MyApplication.isupdate = false;
    System.out.println(MyApplication.isupdate + "isupdate");
  }
  
  public void onPageSelected(int paramInt)
  {
    this.yaogengxin = true;
    System.out.println(paramInt + "arg0" + MyApplication.oldnum);
    if (MyApplication.oldnum > paramInt)
    {
      System.out.println("youhua");
      MyApplication.whichmonth -= 1;
      if (MyApplication.whichmonth < 1)
      {
        MyApplication.whichmonth = 12;
        MyApplication.whichyear -= 1;
      }
      if (MyApplication.whichday > this.dateTrans.getmaxday(MyApplication.whichmonth, MyApplication.whichyear)) {
        MyApplication.whichday = this.dateTrans.getmaxday(MyApplication.whichmonth, MyApplication.whichyear);
      }
    }
    for (;;)
    {
      MyApplication.oldnum = paramInt;
      System.out.println(paramInt + "arg0" + MyApplication.oldnum);
      this.mianview_month_tv.setText(MyApplication.monthStringsall[(MyApplication.whichmonth - 1)] + MyApplication.whichyear);
      this.mianview_year_tv.setText(MyApplication.whichyear + "");
      Message localMessage = this.mHandler.obtainMessage();
      Bundle localBundle = new Bundle();
      localBundle.putInt("item", paramInt);
      localMessage.setData(localBundle);
      this.mHandler.sendMessage(localMessage);
      return;
      if (paramInt > MyApplication.oldnum)
      {
        System.out.println("zuohua");
        MyApplication.whichmonth += 1;
        if (MyApplication.whichmonth > 12)
        {
          MyApplication.whichmonth = 1;
          MyApplication.whichyear += 1;
        }
        if (MyApplication.whichday > this.dateTrans.getmaxday(MyApplication.whichmonth, MyApplication.whichyear)) {
          MyApplication.whichday = this.dateTrans.getmaxday(MyApplication.whichmonth, MyApplication.whichyear);
        }
      }
    }
  }
  
  public void onResume()
  {
    super.onResume();
    if (this.searchHelper != null) {
      this.searchHelper.viewRefresh();
    }
    this.actionmenu.setVisibility(0);
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).setTimeZone(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
    int i = ((Calendar)localObject).get(5);
    int j = ((Calendar)localObject).get(2);
    int k = ((Calendar)localObject).get(1);
    localObject = new StringBuilder().append(k).append("-");
    DateTrans localDateTrans = this.dateTrans;
    localObject = ((StringBuilder)localObject).append(DateTrans.changedate(j + 1)).append("-");
    localDateTrans = this.dateTrans;
    localObject = DateTrans.changedate(i);
    localObject = this.db.getoverduenum((String)localObject, this.userID);
    this.task_over_num.setText(((ArrayList)localObject).size() + "");
    if (((ArrayList)localObject).size() == 0) {
      this.task_over_num_rl.setVisibility(4);
    }
    for (;;)
    {
      System.out.println("minimonthfragmentonresume");
      MyApplication.fragmentcreate = true;
      if ((MyApplication.needupdate) || (this.firstcoming))
      {
        this.settingsdao = ((Settingsdao)this.db.getAllsetting().get(0));
        mHandler2.postDelayed(mRunnable, 100L);
        this.firstcoming = false;
      }
      return;
      this.task_over_num_rl.setVisibility(0);
    }
  }
  
  public void refreshCalendars()
  {
    Account[] arrayOfAccount = AccountManager.get(this.context).getAccounts();
    String str = CalendarContract.Calendars.CONTENT_URI.getAuthority();
    int i = 0;
    while (i < arrayOfAccount.length)
    {
      if (arrayOfAccount[i].type.equals("com.google"))
      {
        Bundle localBundle = new Bundle();
        localBundle.putBoolean("force", true);
        ContentResolver.requestSync(arrayOfAccount[i], str, localBundle);
      }
      i += 1;
    }
  }
  
  public void setcurrentpage()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeZone(TimeZone.getTimeZone(this.settingsdao.getgTimeZone()));
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    MyApplication.oldnum = 6000;
    MyApplication.WithMonth = MyApplication.whichyear * 12 + MyApplication.whichmonth - i * 12 - (j + 1);
    MyApplication.oldnum += MyApplication.WithMonth;
    this.mViewPager.setCurrentItem(MyApplication.Loop / 2 + MyApplication.WithMonth);
  }
  
  public void viewRefresh()
  {
    mHandler2.postDelayed(mRunnable, 40L);
  }
}
