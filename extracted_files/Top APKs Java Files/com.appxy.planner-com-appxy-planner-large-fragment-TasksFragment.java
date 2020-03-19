package com.appxy.planner.large.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.appxy.planner.MyApplication;
import com.appxy.planner.activity.GoldActivity;
import com.appxy.planner.activity.SettingPrefrence;
import com.appxy.planner.adapter.EventChoiceItemAdapter;
import com.appxy.planner.adapter.ProjectAdapter;
import com.appxy.planner.dao.Settingsdao;
import com.appxy.planner.dao.Tasksdao;
import com.appxy.planner.db.PlannerDb;
import com.appxy.planner.helper.DateTrans;
import com.appxy.planner.helper.LongClickDialog;
import com.appxy.planner.implement.FragmentInterface;
import com.appxy.planner.implement.ViewRefresh;
import com.appxy.planner.large.activity.CalenListActivity;
import com.appxy.planner.large.activity.NewTaskView;
import com.appxy.planner.large.adapter.TaskExpendAdapter;
import com.appxy.planner.large.helper.SearchHelper;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.fourmob.datetimepicker.date.DatePickerDialog.OnDateSetListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.UUID;

public class TasksFragment
  extends Fragment
  implements View.OnClickListener, ViewRefresh, FragmentInterface, DatePickerDialog.OnDateSetListener
{
  private ProjectAdapter adapter;
  private ImageView add_iv;
  private ImageView com_iv;
  Comparator<Tasksdao> comparator = new Comparator()
  {
    public int compare(Tasksdao paramAnonymousTasksdao1, Tasksdao paramAnonymousTasksdao2)
    {
      switch (TasksFragment.this.cur_order)
      {
      default: 
        return 0;
      case 1: 
        if (paramAnonymousTasksdao1.getTpPriority().equals(paramAnonymousTasksdao2.getTpPriority()))
        {
          if (paramAnonymousTasksdao1.getTpDueDateNo() == paramAnonymousTasksdao2.getTpDueDateNo())
          {
            if (paramAnonymousTasksdao1.getTpDueDate() == paramAnonymousTasksdao2.getTpDueDate())
            {
              if (paramAnonymousTasksdao1.getTpTitle().equals(paramAnonymousTasksdao2.getTpTitle()))
              {
                if (paramAnonymousTasksdao1.getTpRecordDate() > paramAnonymousTasksdao2.getTpRecordDate()) {
                  return 1;
                }
                return -1;
              }
              return paramAnonymousTasksdao1.getTpTitle().compareTo(paramAnonymousTasksdao2.getTpTitle());
            }
            if (paramAnonymousTasksdao1.getTpDueDate() > paramAnonymousTasksdao2.getTpDueDate()) {
              return 1;
            }
            return -1;
          }
          if (paramAnonymousTasksdao1.getTpDueDateNo() > paramAnonymousTasksdao2.getTpDueDateNo()) {
            return -1;
          }
          return 1;
        }
        return paramAnonymousTasksdao1.getTpPriority().compareTo(paramAnonymousTasksdao2.getTpPriority());
      case 2: 
        if (paramAnonymousTasksdao1.getTpTitle().equals(paramAnonymousTasksdao2.getTpTitle()))
        {
          if (paramAnonymousTasksdao1.getTpDueDateNo() == paramAnonymousTasksdao2.getTpDueDateNo())
          {
            if (paramAnonymousTasksdao1.getTpDueDate() == paramAnonymousTasksdao2.getTpDueDate())
            {
              if (paramAnonymousTasksdao1.getTpRecordDate() > paramAnonymousTasksdao2.getTpRecordDate()) {
                return 1;
              }
              return -1;
            }
            if (paramAnonymousTasksdao1.getTpDueDate() > paramAnonymousTasksdao2.getTpDueDate()) {
              return 1;
            }
            return -1;
          }
          if (paramAnonymousTasksdao1.getTpDueDateNo() > paramAnonymousTasksdao2.getTpDueDateNo()) {
            return -1;
          }
          return 1;
        }
        return paramAnonymousTasksdao1.getTpTitle().compareTo(paramAnonymousTasksdao2.getTpTitle());
      }
      if (paramAnonymousTasksdao1.getTpDueDateNo() == paramAnonymousTasksdao2.getTpDueDateNo())
      {
        if (paramAnonymousTasksdao1.getTpDueDate() == paramAnonymousTasksdao2.getTpDueDate())
        {
          if (paramAnonymousTasksdao1.getTpTitle().equals(paramAnonymousTasksdao2.getTpTitle()))
          {
            if (paramAnonymousTasksdao1.getTpRecordDate() > paramAnonymousTasksdao2.getTpRecordDate()) {
              return 1;
            }
            return -1;
          }
          return paramAnonymousTasksdao1.getTpTitle().compareTo(paramAnonymousTasksdao2.getTpTitle());
        }
        if (paramAnonymousTasksdao1.getTpDueDate() > paramAnonymousTasksdao2.getTpDueDate()) {
          return 1;
        }
        return -1;
      }
      if (paramAnonymousTasksdao1.getTpDueDateNo() > paramAnonymousTasksdao2.getTpDueDateNo()) {
        return 1;
      }
      return -1;
    }
  };
  private Activity context;
  private int cur_order;
  DatePickerDialog datePickerDialog = null;
  private DateTrans dateTrans;
  private PlannerDb db;
  private ImageView delete_iv;
  private Settingsdao doSetting;
  private ImageView done_iv;
  private ImageView due_iv;
  private TextView due_num;
  private RelativeLayout due_rl;
  private ArrayList<Tasksdao> duetasks = new ArrayList();
  private TaskExpendAdapter eadapter;
  private TextView inbox_num;
  private RelativeLayout inbox_rl;
  private ArrayList<Tasksdao> inboxtasks = new ArrayList();
  private boolean isedit;
  private ExpandableListView item_lv;
  private ArrayList<String> mGroupdata = new ArrayList();
  private Handler mHandler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      do
      {
        return false;
      } while (TasksFragment.this.adapter == null);
      int i;
      switch (TasksFragment.this.selwhich)
      {
      default: 
        if (!TasksFragment.this.isedit) {
          break label465;
        }
        TasksFragment.this.mGroupdata.clear();
        if (TasksFragment.this.malltask.containsKey("Uncompleted")) {
          TasksFragment.this.mGroupdata.add("Uncompleted");
        }
        TasksFragment.this.malltask.clear();
        i = 0;
        label132:
        if (i >= TasksFragment.this.seltasks.size()) {
          break label348;
        }
        if (((Tasksdao)TasksFragment.this.seltasks.get(i)).getTpStatus() == 4)
        {
          paramAnonymousMessage = "Completed";
          label170:
          if (!TasksFragment.this.malltask.containsKey(paramAnonymousMessage)) {
            break label337;
          }
        }
        break;
      }
      label337:
      for (ArrayList localArrayList = (ArrayList)TasksFragment.this.malltask.get(paramAnonymousMessage);; localArrayList = new ArrayList())
      {
        localArrayList.add(TasksFragment.this.seltasks.get(i));
        TasksFragment.this.malltask.put(paramAnonymousMessage, localArrayList);
        i += 1;
        break label132;
        TasksFragment.access$2702(TasksFragment.this, (ArrayList)TasksFragment.this.projectDaos.clone());
        break;
        TasksFragment.access$2702(TasksFragment.this, (ArrayList)TasksFragment.this.inboxtasks.clone());
        break;
        TasksFragment.access$2702(TasksFragment.this, (ArrayList)TasksFragment.this.todaytasks.clone());
        break;
        TasksFragment.access$2702(TasksFragment.this, (ArrayList)TasksFragment.this.duetasks.clone());
        break;
        paramAnonymousMessage = "Uncompleted";
        break label170;
      }
      label348:
      if (TasksFragment.this.malltask.size() == 0)
      {
        TasksFragment.this.no_iv.setVisibility(0);
        TasksFragment.this.mGroupdata.clear();
      }
      for (;;)
      {
        Log.e("mtest", "======>TasksFragment======> " + TasksFragment.this.mGroupdata.size() + "    " + TasksFragment.this.malltask.size());
        TasksFragment.this.eadapter.setdata(TasksFragment.this.mGroupdata, TasksFragment.this.malltask, TasksFragment.this.isedit);
        label465:
        TasksFragment.this.setenable();
        return false;
        TasksFragment.this.no_iv.setVisibility(8);
      }
    }
  });
  private TreeMap<String, ArrayList<Tasksdao>> malltask = new TreeMap();
  private ImageView mianview_add_new_iv;
  private TextView mianview_month_tv;
  private RelativeLayout mianview_more_rl;
  private TextView mianview_tasks_tv;
  private TextView mianview_year_tv;
  private ImageView move_iv;
  private int movewhich;
  private ImageView no_iv;
  private ArrayList<Tasksdao> projectDaos = new ArrayList();
  private SearchHelper searchHelper;
  private String selectpk;
  private ArrayList<Tasksdao> seltasks = new ArrayList();
  private int selwhich;
  private CharacterStyle span_1;
  private ArrayList<Tasksdao> taskDaos;
  private RelativeLayout taskLongClickLayout;
  private ListView task_lv;
  private TextView task_over_num;
  private RelativeLayout task_over_num_rl;
  private TextView title_tv;
  private TextView today_num;
  private RelativeLayout today_rl;
  private ArrayList<Tasksdao> todaytasks = new ArrayList();
  private Typeface typeface;
  private Typeface typeface1;
  private String userID;
  private int whichored;
  private RelativeLayout year_lin;
  
  public TasksFragment() {}
  
  private void addnewlist()
  {
    Object localObject1 = LayoutInflater.from(this.context).inflate(2130903093, null);
    Object localObject2 = new AlertDialog.Builder(this.context);
    ((AlertDialog.Builder)localObject2).setView((View)localObject1).setPositiveButton(2131165283, null).setNegativeButton(2131165282, null);
    localObject2 = ((AlertDialog.Builder)localObject2).create();
    ((AlertDialog)localObject2).setCanceledOnTouchOutside(true);
    ((AlertDialog)localObject2).show();
    final EditText localEditText = (EditText)((View)localObject1).findViewById(2131624223);
    localObject1 = (TextView)((View)localObject1).findViewById(2131624143);
    ((TextView)localObject1).setText("New Project");
    ((TextView)localObject1).setTypeface(this.typeface1);
    localEditText.setHint("Project Name");
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        ((InputMethodManager)localEditText.getContext().getSystemService("input_method")).showSoftInput(localEditText, 0);
      }
    }, 200L);
    ((AlertDialog)localObject2).getButton(-2).setTypeface(this.typeface1);
    ((AlertDialog)localObject2).getButton(-1).setTypeface(this.typeface1);
    ((AlertDialog)localObject2).getButton(-1).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (localEditText.getText().toString().equals("")) {
          Toast.makeText(TasksFragment.this.context, "Project name is needed.", 0).show();
        }
        do
        {
          return;
          this.val$dialog.dismiss();
          TasksFragment.this.saveproject(localEditText.getText().toString());
          TasksFragment.this.getdata();
          TasksFragment.this.setselect();
        } while (TasksFragment.this.adapter == null);
        TasksFragment.this.adapter.setdata(TasksFragment.this.projectDaos, TasksFragment.this.selwhich);
      }
    });
  }
  
  private void completed()
  {
    int i = 0;
    while (i < this.mGroupdata.size())
    {
      if (this.malltask != null)
      {
        ArrayList localArrayList = (ArrayList)this.malltask.get(this.mGroupdata.get(i));
        if (localArrayList != null)
        {
          int j = 0;
          while (j < localArrayList.size())
          {
            Tasksdao localTasksdao = (Tasksdao)localArrayList.get(j);
            if ((localTasksdao.isIsedit()) && (localTasksdao.getTpStatus() != 4)) {
              this.db.statuschange(localTasksdao, this.doSetting);
            }
            j += 1;
          }
        }
      }
      i += 1;
    }
    getdata();
    this.mHandler.sendEmptyMessage(1);
  }
  
  private void delete()
  {
    View localView = LayoutInflater.from(this.context).inflate(2130903132, null);
    Object localObject = new AlertDialog.Builder(this.context);
    ((AlertDialog.Builder)localObject).setView(localView);
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
    ((AlertDialog)localObject).show();
    TextView localTextView1 = (TextView)localView.findViewById(2131624368);
    RelativeLayout localRelativeLayout1 = (RelativeLayout)localView.findViewById(2131624172);
    RelativeLayout localRelativeLayout2 = (RelativeLayout)localView.findViewById(2131624174);
    TextView localTextView2 = (TextView)localView.findViewById(2131624175);
    ((TextView)localView.findViewById(2131624173)).setTypeface(this.typeface1);
    localTextView2.setTypeface(this.typeface1);
    localRelativeLayout1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.val$alertDialog1.dismiss();
      }
    });
    localTextView1.setText("Are you sure to delete these tasks ?");
    localRelativeLayout2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.val$alertDialog1.dismiss();
        paramAnonymousView = new ArrayList();
        int i = 0;
        while (i < TasksFragment.this.mGroupdata.size())
        {
          if (TasksFragment.this.malltask != null)
          {
            ArrayList localArrayList = (ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(i));
            if (localArrayList != null)
            {
              int j = 0;
              while (j < localArrayList.size())
              {
                Log.e("mtest", "======>TasksFragment======>  delete  " + ((Tasksdao)localArrayList.get(j)).getTpTitle() + "     " + ((Tasksdao)localArrayList.get(j)).isIsedit());
                if (((Tasksdao)localArrayList.get(j)).isIsedit())
                {
                  Tasksdao localTasksdao = (Tasksdao)localArrayList.get(j);
                  TasksFragment.this.db.updatedeletetask((Tasksdao)localArrayList.get(j), false);
                  localTasksdao.setIsDelete(1);
                  paramAnonymousView.add(localTasksdao);
                }
                j += 1;
              }
            }
          }
          i += 1;
        }
        if (!TasksFragment.this.userID.equals("")) {
          TasksFragment.this.db.updatedatelist(paramAnonymousView);
        }
        TasksFragment.this.getdata();
        TasksFragment.this.mHandler.sendEmptyMessage(1);
      }
    });
  }
  
  private void duedate()
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone(this.doSetting.getgTimeZone()));
    this.datePickerDialog = DatePickerDialog.newInstance(this, localGregorianCalendar.get(1), localGregorianCalendar.get(2), localGregorianCalendar.get(5), false);
    this.datePickerDialog.setFirstDayOfWeek(this.doSetting.getgFirstDay().intValue() + 1);
    this.datePickerDialog.setYearRange(1970, 2036);
    this.datePickerDialog.setCloseOnSingleTapDay(false);
    this.datePickerDialog.show(this.context.getFragmentManager(), "");
  }
  
  private void getdata()
  {
    this.doSetting = ((Settingsdao)this.db.getAllsetting().get(0));
    this.taskDaos = this.db.getalltasks(this.userID, this.doSetting.gettShowCompleted().intValue());
    this.projectDaos.clear();
    this.todaytasks.clear();
    this.inboxtasks.clear();
    this.duetasks.clear();
    int m = 0;
    int j = 0;
    int k = 0;
    int i1 = 0;
    int i = 0;
    int i8 = 0;
    Object localObject1;
    int n;
    Object localObject2;
    while (i8 < this.taskDaos.size())
    {
      localObject1 = (Tasksdao)this.taskDaos.get(i8);
      if (((Tasksdao)localObject1).getTplsProject() == 1)
      {
        this.projectDaos.add(localObject1);
        n = i;
        i = i1;
        i8 += 1;
        i1 = i;
        i = n;
      }
      else
      {
        int i5 = k;
        int i2 = i;
        int i3 = m;
        int i4 = j;
        GregorianCalendar localGregorianCalendar;
        int i7;
        int i6;
        if (((Tasksdao)localObject1).getTpDueDateNo() == 1)
        {
          localGregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone(this.doSetting.getgTimeZone()));
          localObject2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
          ((GregorianCalendar)localObject2).set(1, localGregorianCalendar.get(1));
          ((GregorianCalendar)localObject2).set(2, localGregorianCalendar.get(2));
          ((GregorianCalendar)localObject2).set(5, localGregorianCalendar.get(5));
          ((GregorianCalendar)localObject2).set(10, 0);
          ((GregorianCalendar)localObject2).set(11, 0);
          ((GregorianCalendar)localObject2).set(12, 0);
          ((GregorianCalendar)localObject2).set(13, 0);
          ((GregorianCalendar)localObject2).set(14, 0);
          i7 = i;
          i6 = m;
          n = j;
          if (((Tasksdao)localObject1).getTpDueDate() <= ((GregorianCalendar)localObject2).getTimeInMillis())
          {
            if (((Tasksdao)localObject1).getTpDueDate() >= ((GregorianCalendar)localObject2).getTimeInMillis()) {
              break label574;
            }
            i7 = i;
            i6 = m;
            n = j;
            if (((Tasksdao)localObject1).getTpStatus() != 4)
            {
              i6 = m + 1;
              n = j + 1;
              i7 = i;
              if (((Tasksdao)localObject1).getTplsProject() != 2) {
                i7 = i + 1;
              }
              this.todaytasks.add(localObject1);
            }
          }
        }
        for (;;)
        {
          localGregorianCalendar = (GregorianCalendar)((GregorianCalendar)localObject2).clone();
          localGregorianCalendar.add(5, this.doSetting.gettNextDay().intValue() + 1);
          i5 = k;
          i2 = i7;
          i3 = i6;
          i4 = n;
          if (((Tasksdao)localObject1).getTpDueDate() >= ((GregorianCalendar)localObject2).getTimeInMillis())
          {
            i5 = k;
            i2 = i7;
            i3 = i6;
            i4 = n;
            if (((Tasksdao)localObject1).getTpDueDate() <= localGregorianCalendar.getTimeInMillis())
            {
              i5 = k;
              if (((Tasksdao)localObject1).getTpStatus() != 4) {
                i5 = k + 1;
              }
              this.duetasks.add(localObject1);
              i4 = n;
              i3 = i6;
              i2 = i7;
            }
          }
          k = i5;
          i = i1;
          n = i2;
          m = i3;
          j = i4;
          if (((Tasksdao)localObject1).getTplsProject() == 2) {
            break;
          }
          i = i1;
          if (((Tasksdao)localObject1).getTpStatus() != 4) {
            i = i1 + 1;
          }
          this.inboxtasks.add(localObject1);
          k = i5;
          n = i2;
          m = i3;
          j = i4;
          break;
          label574:
          n = j;
          if (((Tasksdao)localObject1).getTpStatus() != 4) {
            n = j + 1;
          }
          this.todaytasks.add(localObject1);
          i7 = i;
          i6 = m;
        }
      }
    }
    if (this.selwhich < 0) {}
    label906:
    for (;;)
    {
      localObject2 = i + "/" + i1;
      localObject1 = m + "/" + j;
      i = ((String)localObject2).indexOf("/");
      localObject2 = new SpannableString((CharSequence)localObject2);
      ((SpannableString)localObject2).setSpan(this.span_1, 0, i, 33);
      this.inbox_num.setText((CharSequence)localObject2);
      i = ((String)localObject1).indexOf("/");
      localObject1 = new SpannableString((CharSequence)localObject1);
      ((SpannableString)localObject1).setSpan(this.span_1, 0, i, 33);
      this.today_num.setText((CharSequence)localObject1);
      this.due_num.setText(k + "");
      this.task_over_num.setText(m + "");
      if (m != 0) {
        break;
      }
      this.task_over_num_rl.setVisibility(4);
      return;
      this.selwhich = -1;
      if ((this.selectpk != null) && (!this.selectpk.equals("")))
      {
        n = 0;
        for (;;)
        {
          if (n >= this.projectDaos.size()) {
            break label906;
          }
          if (((Tasksdao)this.projectDaos.get(n)).getTpLocalPK().equals(this.selectpk))
          {
            this.selwhich = n;
            break;
          }
          n += 1;
        }
      }
    }
    this.task_over_num_rl.setVisibility(0);
  }
  
  private void initdata()
  {
    getdata();
    this.adapter = new ProjectAdapter(this.context, this.projectDaos, this.selwhich, true);
    this.task_lv.setAdapter(this.adapter);
    this.task_lv.setDivider(null);
    setselect();
    this.eadapter = new TaskExpendAdapter(this.context, this.mGroupdata, this.malltask, this.dateTrans, this.doSetting, this.db, this, this.item_lv);
    this.item_lv.setAdapter(this.eadapter);
    this.item_lv.setGroupIndicator(null);
    int i = 0;
    while (i < this.mGroupdata.size())
    {
      this.item_lv.expandGroup(i);
      i += 1;
    }
    this.item_lv.setDivider(null);
  }
  
  private void initviews(View paramView)
  {
    this.title_tv = ((TextView)paramView.findViewById(2131624143));
    this.taskLongClickLayout = ((RelativeLayout)paramView.findViewById(2131625051));
    this.done_iv = ((ImageView)paramView.findViewById(2131625052));
    this.com_iv = ((ImageView)paramView.findViewById(2131625053));
    this.move_iv = ((ImageView)paramView.findViewById(2131625054));
    this.delete_iv = ((ImageView)paramView.findViewById(2131625055));
    this.due_iv = ((ImageView)paramView.findViewById(2131625056));
    this.done_iv.setOnClickListener(this);
    this.com_iv.setOnClickListener(this);
    this.move_iv.setOnClickListener(this);
    this.delete_iv.setOnClickListener(this);
    this.due_iv.setOnClickListener(this);
    this.add_iv = ((ImageView)paramView.findViewById(2131625050));
    this.today_num = ((TextView)paramView.findViewById(2131624699));
    this.inbox_num = ((TextView)paramView.findViewById(2131624698));
    this.due_num = ((TextView)paramView.findViewById(2131624701));
    this.no_iv = ((ImageView)paramView.findViewById(2131624889));
    this.today_rl = ((RelativeLayout)paramView.findViewById(2131624638));
    this.inbox_rl = ((RelativeLayout)paramView.findViewById(2131624697));
    this.due_rl = ((RelativeLayout)paramView.findViewById(2131624700));
    this.task_lv = ((ListView)paramView.findViewById(2131624703));
    this.item_lv = ((ExpandableListView)paramView.findViewById(2131625057));
    this.mianview_add_new_iv = ((ImageView)paramView.findViewById(2131625058));
    this.due_rl.setOnClickListener(this);
    this.inbox_rl.setOnClickListener(this);
    this.today_rl.setOnClickListener(this);
    this.add_iv.setOnClickListener(this);
    this.task_lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        TasksFragment.access$002(TasksFragment.this, paramAnonymousInt);
        TasksFragment.this.setselect();
        if (TasksFragment.this.isedit)
        {
          TasksFragment.access$202(TasksFragment.this, false);
          TasksFragment.this.title_tv.setVisibility(0);
          TasksFragment.this.taskLongClickLayout.setVisibility(8);
        }
      }
    });
    this.task_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        new LongClickDialog(TasksFragment.this.context, (Tasksdao)TasksFragment.this.projectDaos.get(paramAnonymousInt), null, true, TasksFragment.this, TasksFragment.this.db, TasksFragment.this.doSetting);
        return true;
      }
    });
    this.item_lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
    {
      public boolean onChildClick(ExpandableListView paramAnonymousExpandableListView, View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, long paramAnonymousLong)
      {
        if (TasksFragment.this.isedit)
        {
          if (((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(paramAnonymousInt1))).get(paramAnonymousInt2)).isIsedit()) {
            ((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(paramAnonymousInt1))).get(paramAnonymousInt2)).setIsedit(false);
          }
          for (;;)
          {
            if (TasksFragment.this.eadapter != null) {
              TasksFragment.this.eadapter.setdata(TasksFragment.this.mGroupdata, TasksFragment.this.malltask, TasksFragment.this.isedit);
            }
            TasksFragment.this.setenable();
            return false;
            ((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(paramAnonymousInt1))).get(paramAnonymousInt2)).setIsedit(true);
          }
        }
        paramAnonymousExpandableListView = new Intent();
        paramAnonymousExpandableListView.setClass(TasksFragment.this.context, NewTaskView.class);
        paramAnonymousView = new Bundle();
        paramAnonymousView.putString("tpLocalPK", ((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(paramAnonymousInt1))).get(paramAnonymousInt2)).getTpLocalPK());
        paramAnonymousView.putInt("update", 1);
        paramAnonymousExpandableListView.putExtras(paramAnonymousView);
        TasksFragment.this.startActivity(paramAnonymousExpandableListView);
        return false;
      }
    });
    this.item_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
    {
      public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (TasksFragment.this.isedit) {}
        do
        {
          do
          {
            return true;
          } while ((TasksFragment.this.malltask.get("Uncompleted") == null) || (((ArrayList)TasksFragment.this.malltask.get("Uncompleted")).size() <= paramAnonymousInt - 1));
          TasksFragment.access$202(TasksFragment.this, true);
          TasksFragment.this.title_tv.setVisibility(8);
          TasksFragment.this.taskLongClickLayout.setVisibility(0);
          TasksFragment.this.mGroupdata.clear();
          if (TasksFragment.this.malltask.containsKey("Uncompleted")) {
            TasksFragment.this.mGroupdata.add("Uncompleted");
          }
          TasksFragment.this.eadapter.setdata(TasksFragment.this.mGroupdata, TasksFragment.this.malltask, TasksFragment.this.isedit);
        } while (!TasksFragment.this.isedit);
        if (((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(0))).get(paramAnonymousInt - 1)).isIsedit()) {
          ((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(0))).get(paramAnonymousInt - 1)).setIsedit(false);
        }
        for (;;)
        {
          if (TasksFragment.this.eadapter != null) {
            TasksFragment.this.eadapter.setdata(TasksFragment.this.mGroupdata, TasksFragment.this.malltask, TasksFragment.this.isedit);
          }
          TasksFragment.this.setenable();
          return true;
          ((Tasksdao)((ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(0))).get(paramAnonymousInt - 1)).setIsedit(true);
        }
      }
    });
    this.typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/HELVETICANEUELTPRO-ROMAN.OTF");
    this.typeface1 = Typeface.createFromAsset(this.context.getAssets(), "fonts/HELVETICANEUELTPRO-MD.OTF");
    this.mianview_more_rl = ((RelativeLayout)this.context.findViewById(2131624098));
    this.mianview_month_tv = ((TextView)this.context.findViewById(2131624085));
    this.mianview_year_tv = ((TextView)this.context.findViewById(2131624086));
    this.mianview_tasks_tv = ((TextView)this.context.findViewById(2131624088));
    this.task_over_num = ((TextView)this.context.findViewById(2131624094));
    this.task_over_num_rl = ((RelativeLayout)this.context.findViewById(2131624093));
    this.year_lin = ((RelativeLayout)this.context.findViewById(2131624084));
    this.mianview_month_tv.setVisibility(8);
    this.mianview_year_tv.setVisibility(8);
    this.mianview_tasks_tv.setVisibility(0);
    this.mianview_tasks_tv.setText("Tasks");
    this.year_lin.setVisibility(8);
    MyApplication.fragmentcreate = true;
    this.mianview_add_new_iv.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TasksFragment.this.mianview_add_new_iv.setVisibility(8);
        paramAnonymousView = new Intent();
        Bundle localBundle = new Bundle();
        if (TasksFragment.this.selwhich < 0)
        {
          localBundle.putInt("update", 0);
          localBundle.putInt("duedate", 0);
        }
        for (;;)
        {
          localBundle.putLong("startdate", new GregorianCalendar(TimeZone.getTimeZone(TasksFragment.this.doSetting.getgTimeZone())).getTimeInMillis());
          localBundle.putInt("ishidder", 1);
          paramAnonymousView.setClass(TasksFragment.this.context, NewTaskView.class);
          paramAnonymousView.putExtras(localBundle);
          TasksFragment.this.startActivity(paramAnonymousView);
          return;
          localBundle.putInt("duedate", 1);
          localBundle.putInt("update", 3);
          localBundle.putString("pk", ((Tasksdao)TasksFragment.this.projectDaos.get(TasksFragment.this.selwhich)).getTpLocalPK());
        }
      }
    });
    this.mianview_more_rl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final PopupWindow localPopupWindow;
        LinearLayout localLinearLayout1;
        LinearLayout localLinearLayout2;
        LinearLayout localLinearLayout3;
        LinearLayout localLinearLayout4;
        Object localObject1;
        if (MyApplication.shoufei == 1)
        {
          paramAnonymousView = TasksFragment.this.context.getLayoutInflater().inflate(2130903079, null);
          localPopupWindow = new PopupWindow(paramAnonymousView, -2, -2);
          localPopupWindow.setBackgroundDrawable(TasksFragment.this.getResources().getDrawable(2130838003));
          localPopupWindow.setOutsideTouchable(true);
          localPopupWindow.update();
          localPopupWindow.setTouchable(true);
          localPopupWindow.setFocusable(true);
          if (!localPopupWindow.isShowing()) {
            localPopupWindow.showAsDropDown(TasksFragment.this.mianview_more_rl);
          }
          localLinearLayout1 = (LinearLayout)paramAnonymousView.findViewById(2131624186);
          localLinearLayout2 = (LinearLayout)paramAnonymousView.findViewById(2131624184);
          localLinearLayout3 = (LinearLayout)paramAnonymousView.findViewById(2131624201);
          LinearLayout localLinearLayout5 = (LinearLayout)paramAnonymousView.findViewById(2131624198);
          localLinearLayout4 = (LinearLayout)paramAnonymousView.findViewById(2131624188);
          Object localObject2 = (LinearLayout)paramAnonymousView.findViewById(2131624190);
          localObject1 = (TextView)paramAnonymousView.findViewById(2131624189);
          Object localObject3 = (TextView)paramAnonymousView.findViewById(2131624187);
          Object localObject4 = (TextView)paramAnonymousView.findViewById(2131624185);
          TextView localTextView1 = (TextView)paramAnonymousView.findViewById(2131624191);
          localLinearLayout1.setVisibility(8);
          TextView localTextView2 = (TextView)paramAnonymousView.findViewById(2131624200);
          TextView localTextView3 = (TextView)paramAnonymousView.findViewById(2131624202);
          ((TextView)localObject1).setTypeface(TasksFragment.this.typeface);
          ((TextView)localObject3).setTypeface(TasksFragment.this.typeface);
          ((TextView)localObject4).setTypeface(TasksFragment.this.typeface);
          localTextView1.setTypeface(TasksFragment.this.typeface);
          localTextView3.setTypeface(TasksFragment.this.typeface);
          localTextView2.setTypeface(TasksFragment.this.typeface);
          localLinearLayout4.setVisibility(0);
          ((TextView)localObject3).setText("Calendars");
          localLinearLayout5.setVisibility(8);
          localObject1 = (RelativeLayout)paramAnonymousView.findViewById(2131624199);
          ((LinearLayout)localObject2).setVisibility(8);
          localLinearLayout5.setVisibility(8);
          localLinearLayout4.setVisibility(0);
          ((LinearLayout)localObject2).setVisibility(8);
          if (MyApplication.shoufei != 1) {
            break label561;
          }
          localLinearLayout5 = (LinearLayout)paramAnonymousView.findViewById(2131624196);
          localObject2 = (TextView)paramAnonymousView.findViewById(2131624197);
          localObject3 = (LinearLayout)paramAnonymousView.findViewById(2131624192);
          localObject3 = (TextView)paramAnonymousView.findViewById(2131624193);
          localObject4 = (LinearLayout)paramAnonymousView.findViewById(2131624194);
          paramAnonymousView = (TextView)paramAnonymousView.findViewById(2131624195);
          ((TextView)localObject2).setTypeface(TasksFragment.this.typeface);
          ((TextView)localObject3).setTypeface(TasksFragment.this.typeface);
          paramAnonymousView.setTypeface(TasksFragment.this.typeface);
          localLinearLayout5.setVisibility(8);
        }
        for (;;)
        {
          ((RelativeLayout)localObject1).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = LayoutInflater.from(TasksFragment.this.context).inflate(2130903132, null);
              Object localObject = new AlertDialog.Builder(TasksFragment.this.context);
              ((AlertDialog.Builder)localObject).setView(paramAnonymous2View);
              localObject = ((AlertDialog.Builder)localObject).create();
              ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
              ((AlertDialog)localObject).show();
              TextView localTextView1 = (TextView)paramAnonymous2View.findViewById(2131624368);
              RelativeLayout localRelativeLayout1 = (RelativeLayout)paramAnonymous2View.findViewById(2131624172);
              RelativeLayout localRelativeLayout2 = (RelativeLayout)paramAnonymous2View.findViewById(2131624174);
              TextView localTextView2 = (TextView)paramAnonymous2View.findViewById(2131624175);
              ((TextView)paramAnonymous2View.findViewById(2131624173)).setTypeface(TasksFragment.this.typeface1);
              localTextView2.setTypeface(TasksFragment.this.typeface1);
              localTextView1.setText("Are you sure to delete all completed tasks ?");
              localRelativeLayout1.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous3View)
                {
                  this.val$alertDialog.dismiss();
                }
              });
              localRelativeLayout2.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous3View)
                {
                  this.val$alertDialog.dismiss();
                }
              });
            }
          });
          localLinearLayout1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = new Intent();
              paramAnonymous2View.setClass(TasksFragment.this.context, CalenListActivity.class);
              TasksFragment.this.context.startActivity(paramAnonymous2View);
            }
          });
          localLinearLayout4.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = LayoutInflater.from(TasksFragment.this.context);
              TasksFragment.access$1702(TasksFragment.this, TasksFragment.this.cur_order);
              paramAnonymous2View = paramAnonymous2View.inflate(2130903212, null);
              Object localObject = new AlertDialog.Builder(TasksFragment.this.context);
              ((AlertDialog.Builder)localObject).setView(paramAnonymous2View);
              localObject = ((AlertDialog.Builder)localObject).create();
              ((AlertDialog)localObject).setCanceledOnTouchOutside(true);
              ((AlertDialog)localObject).show();
              final ListView localListView = (ListView)paramAnonymous2View.findViewById(2131624963);
              RelativeLayout localRelativeLayout = (RelativeLayout)paramAnonymous2View.findViewById(2131624174);
              TextView localTextView1 = (TextView)paramAnonymous2View.findViewById(2131624964);
              TextView localTextView2 = (TextView)paramAnonymous2View.findViewById(2131624175);
              TextView localTextView3 = (TextView)paramAnonymous2View.findViewById(2131624173);
              final ArrayList localArrayList = new ArrayList();
              localArrayList.add("Due Date");
              localArrayList.add("Priority");
              localArrayList.add("Alphabet");
              localTextView1.setText("Order by");
              localTextView1.setTypeface(TasksFragment.this.typeface1);
              localTextView2.setTypeface(TasksFragment.this.typeface1);
              localTextView3.setTypeface(TasksFragment.this.typeface1);
              localListView.setAdapter(new EventChoiceItemAdapter(TasksFragment.this.context, TasksFragment.this.whichored, localArrayList));
              localListView.setDivider(null);
              ((RelativeLayout)paramAnonymous2View.findViewById(2131624172)).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous3View)
                {
                  this.val$alertDialog.dismiss();
                }
              });
              localRelativeLayout.setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramAnonymous3View)
                {
                  this.val$alertDialog.dismiss();
                  paramAnonymous3View = new Settingsdao();
                  TasksFragment.access$1802(TasksFragment.this, TasksFragment.this.whichored);
                  paramAnonymous3View.settOrderBy(Integer.valueOf(TasksFragment.this.whichored));
                  TasksFragment.this.db.updateSettingorderby(paramAnonymous3View, "1");
                  TasksFragment.this.sortaction();
                }
              });
              localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
              {
                public void onItemClick(AdapterView<?> paramAnonymous3AdapterView, View paramAnonymous3View, int paramAnonymous3Int, long paramAnonymous3Long)
                {
                  TasksFragment.access$1702(TasksFragment.this, paramAnonymous3Int);
                  paramAnonymous3AdapterView = new EventChoiceItemAdapter(TasksFragment.this.context, TasksFragment.this.whichored, localArrayList);
                  localListView.setAdapter(paramAnonymous3AdapterView);
                }
              });
            }
          });
          localLinearLayout2.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              TasksFragment.access$2002(TasksFragment.this, new SearchHelper(TasksFragment.this.context, TasksFragment.this.mianview_more_rl, TasksFragment.this.db, TasksFragment.this.dateTrans, TasksFragment.this.doSetting, TasksFragment.this));
            }
          });
          localLinearLayout3.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localPopupWindow.dismiss();
              paramAnonymous2View = new Intent();
              paramAnonymous2View.setClass(TasksFragment.this.context, SettingPrefrence.class);
              TasksFragment.this.startActivity(paramAnonymous2View);
            }
          });
          return;
          paramAnonymousView = TasksFragment.this.context.getLayoutInflater().inflate(2130903080, null);
          break;
          label561:
          ((TextView)paramAnonymousView.findViewById(2131624204)).setTypeface(TasksFragment.this.typeface);
          localLinearLayout2.setVisibility(0);
          paramAnonymousView = (LinearLayout)paramAnonymousView.findViewById(2131624203);
          paramAnonymousView.setVisibility(8);
          paramAnonymousView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              switch (MyApplication.banben)
              {
              default: 
                return;
              case 1: 
                Object localObject = TasksFragment.this.context.getPackageManager().getInstalledApplications(0);
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
                  TasksFragment.this.startActivity((Intent)localObject);
                  return;
                }
                TasksFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.appxy.plannerplus")));
                return;
              case 2: 
                try
                {
                  paramAnonymous2View = new Intent("android.intent.action.VIEW");
                  paramAnonymous2View.setData(Uri.parse("amzn://apps/android?asin=B00KA9WHMU"));
                  TasksFragment.this.startActivity(paramAnonymous2View);
                  return;
                }
                catch (ActivityNotFoundException paramAnonymous2View)
                {
                  paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/Planner-Plus-Schedule-Personal-Organizer/dp/B00KA9WHMU/ref=zg_bs_2478859011_44"));
                  TasksFragment.this.startActivity(paramAnonymous2View);
                  return;
                }
              }
              try
              {
                paramAnonymous2View = new Intent("android.intent.action.VIEW");
                paramAnonymous2View.setData(Uri.parse("samsungapps://ProductDetail/com.appxy.plannerplus"));
                TasksFragment.this.startActivity(paramAnonymous2View);
                return;
              }
              catch (ActivityNotFoundException paramAnonymous2View)
              {
                paramAnonymous2View = new Intent("android.intent.action.VIEW", Uri.parse("http://apps.samsung.com/mars/topApps/topAppsDetail.as?productId=000000846370"));
                TasksFragment.this.startActivity(paramAnonymous2View);
              }
            }
          });
        }
      }
    });
  }
  
  private void move()
  {
    Object localObject3 = LayoutInflater.from(this.context).inflate(2130903211, null);
    Object localObject1 = new AlertDialog.Builder(this.context);
    ((AlertDialog.Builder)localObject1).setView((View)localObject3).setPositiveButton(2131165283, null).setNegativeButton(2131165282, null);
    localObject1 = ((AlertDialog.Builder)localObject1).create();
    ((AlertDialog)localObject1).show();
    Object localObject2 = new ArrayList();
    int i = 0;
    while (i < this.projectDaos.size())
    {
      ((ArrayList)localObject2).add(((Tasksdao)this.projectDaos.get(i)).getTpTitle());
      i += 1;
    }
    ListView localListView = (ListView)((View)localObject3).findViewById(2131624963);
    localObject3 = (TextView)((View)localObject3).findViewById(2131624230);
    ((TextView)localObject3).setText("Move");
    ((TextView)localObject3).setTypeface(this.typeface1);
    localObject3 = ((AlertDialog)localObject1).getButton(-1);
    ((Button)localObject3).setTypeface(this.typeface1);
    ((AlertDialog)localObject1).getButton(-2).setTypeface(this.typeface1);
    localObject2 = new ArrayAdapter(this.context, 2130903182, (List)localObject2);
    ((Button)localObject3).setEnabled(false);
    localListView.setAdapter((ListAdapter)localObject2);
    localListView.setDivider(null);
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        TasksFragment.access$2202(TasksFragment.this, paramAnonymousInt);
        this.val$ok4.setEnabled(true);
      }
    });
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        this.val$dialog.dismiss();
        paramAnonymousView = new ArrayList();
        int i = 0;
        while (i < TasksFragment.this.mGroupdata.size())
        {
          if (TasksFragment.this.malltask != null)
          {
            ArrayList localArrayList = (ArrayList)TasksFragment.this.malltask.get(TasksFragment.this.mGroupdata.get(i));
            if (localArrayList != null)
            {
              int j = 0;
              while (j < localArrayList.size())
              {
                Tasksdao localTasksdao = (Tasksdao)localArrayList.get(j);
                Log.e("mtest", "======>TasksFragment======>  move  " + ((Tasksdao)localArrayList.get(j)).getTpTitle() + "     " + ((Tasksdao)localArrayList.get(j)).isIsedit());
                if (localTasksdao.isIsedit())
                {
                  TasksFragment.this.db.updateTasksmoveproject(localTasksdao, ((Tasksdao)TasksFragment.this.projectDaos.get(TasksFragment.this.movewhich)).getTpLocalPK());
                  localTasksdao.setTplsProject(2);
                  localTasksdao.setTpLocalFK(((Tasksdao)TasksFragment.this.projectDaos.get(TasksFragment.this.movewhich)).getTpLocalPK());
                  paramAnonymousView.add(localTasksdao);
                }
                j += 1;
              }
            }
          }
          i += 1;
        }
        if (!TasksFragment.this.userID.equals("")) {
          TasksFragment.this.db.updatedatelist(paramAnonymousView);
        }
        TasksFragment.this.getdata();
        TasksFragment.this.mHandler.sendEmptyMessage(1);
      }
    });
  }
  
  private void saveproject(String paramString)
  {
    System.currentTimeMillis();
    Tasksdao localTasksdao = new Tasksdao();
    localTasksdao.setIsDelete(0);
    localTasksdao.setTpLocalPK(UUID.randomUUID().toString());
    localTasksdao.setTplsProject(1);
    localTasksdao.setTpTitle(paramString);
    this.db.insertTasks(localTasksdao, true, false);
  }
  
  private void setenable()
  {
    int k = 0;
    int j = k;
    int i;
    if (this.mGroupdata != null)
    {
      j = k;
      if (this.mGroupdata.size() > 0)
      {
        j = k;
        if (this.malltask.get(this.mGroupdata.get(0)) != null) {
          i = 0;
        }
      }
    }
    for (;;)
    {
      j = k;
      if (i < ((ArrayList)this.malltask.get(this.mGroupdata.get(0))).size())
      {
        if ((this.malltask.get(this.mGroupdata.get(0)) != null) && (((ArrayList)this.malltask.get(this.mGroupdata.get(0))).size() > 0) && (((Tasksdao)((ArrayList)this.malltask.get(this.mGroupdata.get(0))).get(i)).isIsedit())) {
          j = 1;
        }
      }
      else
      {
        if (j == 0) {
          break;
        }
        this.com_iv.setBackgroundResource(2130837640);
        this.due_iv.setBackgroundResource(2130837709);
        this.move_iv.setBackgroundResource(2130837890);
        this.delete_iv.setBackgroundResource(2130837702);
        return;
      }
      i += 1;
    }
    this.com_iv.setBackgroundResource(2130837641);
    this.due_iv.setBackgroundResource(2130837710);
    this.move_iv.setBackgroundResource(2130837891);
    this.delete_iv.setBackgroundResource(2130837703);
  }
  
  private void setselect()
  {
    this.inbox_rl.setBackgroundResource(2130837965);
    this.today_rl.setBackgroundResource(2130837965);
    this.due_rl.setBackgroundResource(2130837965);
    int i = 0;
    while (i < this.projectDaos.size())
    {
      ((Tasksdao)this.projectDaos.get(i)).setIsselected(false);
      i += 1;
    }
    if (this.selwhich < 0)
    {
      this.selectpk = "";
      switch (this.selwhich)
      {
      }
    }
    for (;;)
    {
      i = 0;
      while (i < this.seltasks.size())
      {
        ((Tasksdao)this.seltasks.get(i)).setIsedit(false);
        i += 1;
      }
      this.inbox_rl.setBackgroundResource(2130837988);
      this.title_tv.setText("Inbox");
      this.seltasks = ((ArrayList)this.inboxtasks.clone());
      continue;
      this.today_rl.setBackgroundResource(2130837988);
      this.title_tv.setText("Today");
      this.seltasks = ((ArrayList)this.todaytasks.clone());
      continue;
      this.due_rl.setBackgroundResource(2130837988);
      this.title_tv.setText("Due Soon");
      this.seltasks = ((ArrayList)this.duetasks.clone());
      continue;
      ((Tasksdao)this.projectDaos.get(this.selwhich)).setIsselected(true);
      this.selectpk = ((Tasksdao)this.projectDaos.get(this.selwhich)).getTpLocalPK();
      this.seltasks.clear();
      this.title_tv.setText(((Tasksdao)this.projectDaos.get(this.selwhich)).getTpTitle());
      i = 0;
      while (i < this.taskDaos.size())
      {
        localObject1 = (Tasksdao)this.taskDaos.get(i);
        if ((((Tasksdao)localObject1).getTplsProject() == 2) && (((Tasksdao)localObject1).getTpLocalFK() != null) && (((Tasksdao)localObject1).getTpLocalFK().equals(((Tasksdao)this.projectDaos.get(this.selwhich)).getTpLocalPK()))) {
          this.seltasks.add(localObject1);
        }
        i += 1;
      }
    }
    this.adapter.setdata(this.projectDaos, this.selwhich);
    Collections.sort(this.seltasks, this.comparator);
    this.mGroupdata.clear();
    this.malltask.clear();
    i = 0;
    label482:
    Object localObject2;
    if (i < this.seltasks.size())
    {
      if (((Tasksdao)this.seltasks.get(i)).getTpStatus() == 4)
      {
        localObject1 = "Completed";
        if (!this.malltask.containsKey(localObject1)) {
          break label542;
        }
      }
      label542:
      for (localObject2 = (ArrayList)this.malltask.get(localObject1);; localObject2 = new ArrayList())
      {
        ((ArrayList)localObject2).add(this.seltasks.get(i));
        this.malltask.put(localObject1, localObject2);
        i += 1;
        break;
        localObject1 = "Uncompleted";
        break label482;
      }
    }
    Object localObject1 = this.malltask.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Map.Entry)((Iterator)localObject1).next()).getKey();
      this.mGroupdata.add(localObject2);
    }
    if (this.mGroupdata.size() == 2)
    {
      this.mGroupdata.clear();
      this.mGroupdata.add("Uncompleted");
      this.mGroupdata.add("Completed");
    }
    if (this.seltasks.size() == 0)
    {
      this.no_iv.setVisibility(0);
      this.mGroupdata.clear();
    }
    for (;;)
    {
      if (this.eadapter != null) {
        this.eadapter.setdata(this.mGroupdata, this.malltask, false);
      }
      return;
      this.no_iv.setVisibility(8);
    }
  }
  
  private void sortaction()
  {
    Collections.sort(this.seltasks, this.comparator);
    this.mGroupdata.clear();
    this.malltask.clear();
    int i = 0;
    label60:
    Object localObject2;
    if (i < this.seltasks.size())
    {
      if (((Tasksdao)this.seltasks.get(i)).getTpStatus() == 4)
      {
        localObject1 = "Completed";
        if (!this.malltask.containsKey(localObject1)) {
          break label120;
        }
      }
      label120:
      for (localObject2 = (ArrayList)this.malltask.get(localObject1);; localObject2 = new ArrayList())
      {
        ((ArrayList)localObject2).add(this.seltasks.get(i));
        this.malltask.put(localObject1, localObject2);
        i += 1;
        break;
        localObject1 = "Uncompleted";
        break label60;
      }
    }
    Object localObject1 = this.malltask.entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (String)((Map.Entry)((Iterator)localObject1).next()).getKey();
      this.mGroupdata.add(localObject2);
    }
    if (this.mGroupdata.size() == 2)
    {
      this.mGroupdata.clear();
      this.mGroupdata.add("Uncompleted");
      this.mGroupdata.add("Completed");
    }
    if (this.eadapter != null) {
      this.eadapter.setdata(this.mGroupdata, this.malltask, this.isedit);
    }
  }
  
  public void fragmentrefresh()
  {
    viewRefresh();
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
    case 2131624697: 
    case 2131624638: 
    case 2131624700: 
    case 2131625050: 
    case 2131625052: 
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              this.selwhich = -1;
              setselect();
            } while (!this.isedit);
            this.isedit = false;
            this.title_tv.setVisibility(0);
            this.taskLongClickLayout.setVisibility(8);
            return;
            this.selwhich = -2;
            setselect();
          } while (!this.isedit);
          this.isedit = false;
          this.title_tv.setVisibility(0);
          this.taskLongClickLayout.setVisibility(8);
          return;
          this.selwhich = -3;
          setselect();
        } while (!this.isedit);
        this.isedit = false;
        this.title_tv.setVisibility(0);
        this.taskLongClickLayout.setVisibility(8);
        return;
        if (MyApplication.shoufei == 2)
        {
          paramView = new Intent();
          paramView.setClass(this.context, GoldActivity.class);
          startActivity(paramView);
          return;
        }
        addnewlist();
        return;
      } while (!this.isedit);
      this.isedit = false;
      int i = 0;
      while (i < this.mGroupdata.size())
      {
        paramView = (ArrayList)this.malltask.get(this.mGroupdata.get(i));
        if (paramView != null)
        {
          int j = 0;
          while (j < paramView.size())
          {
            ((Tasksdao)((ArrayList)this.malltask.get(this.mGroupdata.get(i))).get(j)).setIsedit(false);
            j += 1;
          }
        }
        i += 1;
      }
      this.mGroupdata.clear();
      if (this.malltask.containsKey("Uncompleted")) {
        this.mGroupdata.add("Uncompleted");
      }
      if (this.malltask.containsKey("Completed")) {
        this.mGroupdata.add("Completed");
      }
      this.eadapter.setdata(this.mGroupdata, this.malltask, this.isedit);
      this.title_tv.setVisibility(0);
      setenable();
      this.taskLongClickLayout.setVisibility(8);
      return;
    case 2131625053: 
      completed();
      return;
    case 2131625054: 
      move();
      return;
    case 2131625055: 
      delete();
      return;
    }
    duedate();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.db = new PlannerDb(this.context);
    this.dateTrans = new DateTrans(this.context);
    this.doSetting = ((Settingsdao)this.db.getAllsetting().get(0));
    this.cur_order = this.doSetting.gettOrderBy().intValue();
    paramBundle = this.context.getPackageName();
    paramBundle = this.context.getSharedPreferences(paramBundle + "_preferences", 0);
    this.span_1 = new ForegroundColorSpan(Color.rgb(204, 72, 72));
    this.userID = paramBundle.getString("userID", "");
    this.selwhich = -1;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2130903244, paramViewGroup, false);
    initviews(paramLayoutInflater);
    initdata();
    return paramLayoutInflater;
  }
  
  public void onDateSet(DatePickerDialog paramDatePickerDialog, int paramInt1, int paramInt2, int paramInt3)
  {
    paramDatePickerDialog = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    paramDatePickerDialog.set(1, paramInt1);
    paramDatePickerDialog.set(2, paramInt2);
    paramDatePickerDialog.set(5, paramInt3);
    paramDatePickerDialog.set(10, 0);
    paramDatePickerDialog.set(11, 0);
    paramDatePickerDialog.set(12, 0);
    paramDatePickerDialog.set(13, 0);
    paramDatePickerDialog.set(14, 0);
    ArrayList localArrayList1 = new ArrayList();
    paramInt1 = 0;
    while (paramInt1 < this.mGroupdata.size())
    {
      if (this.malltask != null)
      {
        ArrayList localArrayList2 = (ArrayList)this.malltask.get(this.mGroupdata.get(paramInt1));
        if (localArrayList2 != null)
        {
          paramInt2 = 0;
          while (paramInt2 < localArrayList2.size())
          {
            Log.e("mtest", "======>TasksFragment======>  duedate  " + ((Tasksdao)localArrayList2.get(paramInt2)).getTpTitle() + "     " + ((Tasksdao)localArrayList2.get(paramInt2)).isIsedit());
            if (((Tasksdao)localArrayList2.get(paramInt2)).isIsedit())
            {
              this.db.updateTasksduedate((Tasksdao)localArrayList2.get(paramInt2), paramDatePickerDialog.getTimeInMillis());
              Tasksdao localTasksdao = (Tasksdao)localArrayList2.get(paramInt2);
              localTasksdao.setTpDueDate(paramDatePickerDialog.getTimeInMillis());
              localTasksdao.setTpDueDateNo(1);
              localArrayList1.add(localArrayList2.get(paramInt2));
            }
            paramInt2 += 1;
          }
        }
      }
      paramInt1 += 1;
    }
    if (!this.userID.equals("")) {
      this.db.updatedatelist(localArrayList1);
    }
    getdata();
    this.mHandler.sendEmptyMessage(1);
  }
  
  public void onResume()
  {
    super.onResume();
    viewRefresh();
    if (this.searchHelper != null) {
      this.searchHelper.viewRefresh();
    }
    this.mianview_add_new_iv.setVisibility(0);
  }
  
  public void viewRefresh()
  {
    getdata();
    if (this.adapter != null) {
      this.adapter.setdata(this.projectDaos, this.selwhich);
    }
    setselect();
  }
}
