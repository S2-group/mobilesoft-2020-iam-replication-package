package com.mmguardian.parentapp.fragment.d.a;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.analytics.HitBuilders.ScreenViewBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mmguardian.parentapp.MyApplication;
import com.mmguardian.parentapp.e.a.e;
import com.mmguardian.parentapp.e.a.i;
import com.mmguardian.parentapp.e.ad;
import com.mmguardian.parentapp.e.s;
import com.mmguardian.parentapp.fragment.b.a;
import com.mmguardian.parentapp.fragment.d.g;
import com.mmguardian.parentapp.table.ReportWebLogRecordTable;
import com.mmguardian.parentapp.vo.ReportWebLogDayRecords;
import com.mmguardian.parentapp.vo.ReportWebLogDaysData;
import com.mmguardian.parentapp.vo.ReportWebLogDomainSumDataVO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class j
  extends a
{
  private static final String a = "j";
  private TextView b;
  private TextView c;
  private ListView d;
  private String e;
  private String f;
  private long g;
  private int h;
  private int i;
  private long j;
  private int k = -1;
  private int l;
  private List<ReportWebLogRecordTable> m;
  private List<ReportWebLogDomainSumDataVO> n;
  private List<ReportWebLogRecordTable> o;
  
  public j() {}
  
  private void a()
  {
    if (this.d != null)
    {
      this.k = this.d.getFirstVisiblePosition();
      Object localObject = this.d;
      int i1 = 0;
      localObject = ((ListView)localObject).getChildAt(0);
      if (localObject != null) {
        i1 = ((View)localObject).getTop();
      }
      this.l = i1;
    }
  }
  
  private void a(int paramInt)
  {
    String str = ((ReportWebLogRecordTable)this.o.get(paramInt)).f();
    Object localObject1 = getActivity().getPackageManager();
    paramInt = 0;
    Object localObject2 = ((PackageManager)localObject1).getInstalledApplications(0).iterator();
    label41:
    ApplicationInfo localApplicationInfo;
    for (localObject1 = null; ((Iterator)localObject2).hasNext(); localObject1 = localApplicationInfo.packageName)
    {
      localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
      if ((!localApplicationInfo.packageName.contains("chrome")) || (localApplicationInfo.packageName.contains("chromecast"))) {
        break label41;
      }
      paramInt = 1;
    }
    localObject2 = new Intent();
    if ((paramInt != 0) && (localObject1 != null)) {
      ((Intent)localObject2).setPackage((String)localObject1);
    }
    ((Intent)localObject2).setAction("android.intent.action.VIEW");
    ((Intent)localObject2).setData(Uri.parse(str));
    if ((str != null) && (Patterns.WEB_URL.matcher(str).matches())) {}
    try
    {
      startActivity((Intent)localObject2);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException) {}
  }
  
  private void a(ReportWebLogDaysData paramReportWebLogDaysData)
  {
    if ((paramReportWebLogDaysData != null) && (paramReportWebLogDaysData.a() != null))
    {
      if (paramReportWebLogDaysData.a().isEmpty()) {
        return;
      }
      paramReportWebLogDaysData = paramReportWebLogDaysData.a();
      boolean bool2 = false;
      paramReportWebLogDaysData = (ReportWebLogDayRecords)paramReportWebLogDaysData.get(0);
      this.n = paramReportWebLogDaysData.c();
      this.m = paramReportWebLogDaysData.l();
      paramReportWebLogDaysData = new ArrayList();
      Object localObject = this.m.iterator();
      while (((Iterator)localObject).hasNext())
      {
        ReportWebLogRecordTable localReportWebLogRecordTable = (ReportWebLogRecordTable)((Iterator)localObject).next();
        if (localReportWebLogRecordTable.i().booleanValue()) {
          paramReportWebLogDaysData.add(localReportWebLogRecordTable);
        }
      }
      boolean bool1 = bool2;
      if (this.e != null) {
        if (this.e.equalsIgnoreCase(getString(2131689595)))
        {
          this.o = this.m;
          bool1 = bool2;
        }
        else if (this.e.equalsIgnoreCase(getString(2131689709)))
        {
          this.o = paramReportWebLogDaysData;
          bool1 = bool2;
        }
        else
        {
          bool2 = true;
          this.o = new ArrayList();
          paramReportWebLogDaysData = this.m.iterator();
          for (;;)
          {
            bool1 = bool2;
            if (!paramReportWebLogDaysData.hasNext()) {
              break;
            }
            localObject = (ReportWebLogRecordTable)paramReportWebLogDaysData.next();
            if (((ReportWebLogRecordTable)localObject).h().equalsIgnoreCase(this.e)) {
              this.o.add(localObject);
            }
          }
        }
      }
      if (this.o != null)
      {
        Collections.sort(this.o, new e());
        paramReportWebLogDaysData = new a(this.o, bool1);
        this.d.setAdapter(paramReportWebLogDaysData);
        this.d.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            j.a(j.this, paramAnonymousInt);
          }
        });
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getArguments();
    if (paramBundle != null)
    {
      this.j = paramBundle.getLong("PHONE_ID_KEY");
      this.f = paramBundle.getString("SITE_NAME_KEY");
      this.e = paramBundle.getString("DOMAIN_KEY");
      this.g = paramBundle.getLong("REPORT_BASELINE_DATE_TODAY_KEY");
      this.h = paramBundle.getInt("DAY_OVERVIEW_POSITION_KEY");
      this.i = paramBundle.getInt("DAY_TOTAL_DAYS_KEY");
    }
  }
  
  public Dialog onCreateDialog(Bundle paramBundle)
  {
    paramBundle = super.onCreateDialog(paramBundle);
    paramBundle.requestWindowFeature(1);
    return paramBundle;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2131427523, paramViewGroup, false);
  }
  
  public void onPause()
  {
    super.onPause();
    a();
  }
  
  public void onStart()
  {
    super.onStart();
    Object localObject = MyApplication.b();
    ((Tracker)localObject).a("Report_Web_URL_List");
    ((Tracker)localObject).a(new HitBuilders.ScreenViewBuilder().a());
    localObject = getClass().getSimpleName();
    FirebaseAnalytics.getInstance(getActivity()).setCurrentScreen(getActivity(), (String)localObject, null);
    if (this.e != null) {
      this.b.setText(this.e);
    }
    if (s.a(getActivity()) != null) {
      localObject = s.a(getActivity());
    } else {
      localObject = TimeZone.getDefault();
    }
    TextView localTextView = this.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(" (");
    localStringBuilder.append(com.mmguardian.parentapp.e.a.j.a(getActivity(), this.g, this.h, this.i, (TimeZone)localObject));
    localStringBuilder.append(")");
    localTextView.setText(localStringBuilder.toString());
    new Thread(new Runnable()
    {
      public void run()
      {
        final ReportWebLogDaysData localReportWebLogDaysData = i.g(j.this.getActivity(), Long.valueOf(j.a(j.this)), Long.valueOf(this.a[0]), Long.valueOf(this.a[1]));
        this.b.post(new Runnable()
        {
          public void run()
          {
            if ((j.this.isAdded()) && (j.this.isVisible()) && (localReportWebLogDaysData != null) && (localReportWebLogDaysData.a() != null) && (!localReportWebLogDaysData.a().isEmpty()))
            {
              j.a(j.this, localReportWebLogDaysData);
              if (j.b(j.this) != -1) {
                j.d(j.this).setSelectionFromTop(j.b(j.this), j.c(j.this));
              }
            }
          }
        });
      }
    }).start();
  }
  
  public void onStop()
  {
    if (getActivity() != null)
    {
      String str = g.class.getSimpleName();
      FirebaseAnalytics.getInstance(getActivity()).setCurrentScreen(getActivity(), str, null);
    }
    super.onStop();
  }
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.b = ((TextView)paramView.findViewById(2131297195));
    this.c = ((TextView)paramView.findViewById(2131296656));
    this.d = ((ListView)paramView.findViewById(2131297276));
  }
  
  private class a
    extends BaseAdapter
  {
    private List<ReportWebLogRecordTable> b;
    private boolean c;
    private TimeZone d;
    
    public a(boolean paramBoolean)
    {
      this.b = paramBoolean;
      boolean bool;
      this.c = bool;
      if (s.a(j.this.getActivity()) != null)
      {
        this.d = s.a(j.this.getActivity());
        return;
      }
      this.d = TimeZone.getDefault();
    }
    
    public boolean areAllItemsEnabled()
    {
      return true;
    }
    
    public int getCount()
    {
      return this.b.size();
    }
    
    public Object getItem(int paramInt)
    {
      return this.b.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      paramViewGroup = (LayoutInflater)j.this.getActivity().getSystemService("layout_inflater");
      if (paramView == null)
      {
        paramView = new a(null);
        paramViewGroup = paramViewGroup.inflate(2131427524, null);
        paramView.a = ((LinearLayout)paramViewGroup.findViewById(2131296830));
        paramView.d = ((TextView)paramViewGroup.findViewById(2131296845));
        paramView.c = ((TextView)paramViewGroup.findViewById(2131296268));
        paramView.b = ((TextView)paramViewGroup.findViewById(2131296688));
        paramView.f = ((TextView)paramViewGroup.findViewById(2131297247));
        paramView.e = ((TextView)paramViewGroup.findViewById(2131296267));
        paramViewGroup.setTag(paramView);
      }
      else
      {
        localObject = (a)paramView.getTag();
        paramViewGroup = paramView;
        paramView = (View)localObject;
      }
      paramView.a.setBackgroundResource(2131231023);
      paramView.e.setTextColor(j.this.getActivity().getResources().getColor(2131099649));
      paramView.b.setTextColor(j.this.getActivity().getResources().getColor(2131099649));
      if ((((ReportWebLogRecordTable)this.b.get(paramInt)).g() != null) && (((ReportWebLogRecordTable)this.b.get(paramInt)).g().length() >= 1)) {
        paramView.d.setText(((ReportWebLogRecordTable)this.b.get(paramInt)).g());
      } else {
        paramView.d.setText(((ReportWebLogRecordTable)this.b.get(paramInt)).h());
      }
      paramView.c.setText(((ReportWebLogRecordTable)this.b.get(paramInt)).f());
      Object localObject = Calendar.getInstance();
      ((Calendar)localObject).setTimeInMillis(((ReportWebLogRecordTable)this.b.get(paramInt)).e().longValue());
      localObject = com.mmguardian.parentapp.e.a.j.a(j.this.getActivity(), ((Calendar)localObject).getTimeInMillis(), this.d);
      TextView localTextView;
      StringBuilder localStringBuilder;
      if (((ReportWebLogRecordTable)this.b.get(paramInt)).i().booleanValue())
      {
        paramView.e.setTextColor(j.this.getResources().getColor(2131099788));
        localTextView = paramView.e;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(j.this.getString(2131689707));
        localStringBuilder.append(": ");
        localStringBuilder.append((String)localObject);
        localTextView.setText(localStringBuilder.toString());
      }
      else
      {
        localTextView = paramView.e;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(j.this.getString(2131689506));
        localStringBuilder.append(": ");
        localStringBuilder.append((String)localObject);
        localTextView.setText(localStringBuilder.toString());
      }
      if (this.c) {
        paramView.b.setVisibility(4);
      } else {
        paramView.b.setText(((ReportWebLogRecordTable)this.b.get(paramInt)).h());
      }
      if ((((ReportWebLogRecordTable)this.b.get(paramInt)).j() == null) && (((ReportWebLogRecordTable)this.b.get(paramInt)).j().longValue() == 0L))
      {
        paramView.f.setVisibility(8);
        return paramViewGroup;
      }
      paramView.f.setText(com.mmguardian.parentapp.e.a.j.b(j.this.getActivity(), Long.valueOf(((ReportWebLogRecordTable)this.b.get(paramInt)).j().longValue() * 1000L)));
      return paramViewGroup;
    }
    
    public boolean isEnabled(int paramInt)
    {
      return true;
    }
    
    private class a
    {
      LinearLayout a;
      TextView b;
      TextView c;
      TextView d;
      TextView e;
      TextView f;
      
      private a() {}
    }
  }
}
