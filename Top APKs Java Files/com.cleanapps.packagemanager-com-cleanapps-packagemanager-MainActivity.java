package com.cleanapps.packagemanager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MainActivity
  extends Activity
{
  ActivityAdapter activityAdapter;
  ArrayList<ActivityInfo> activityINFOList;
  ArrayList<ActivityInfo> activityINFOListBack;
  EditText editSearch;
  ListView listViewActivity;
  List<PackageInfo> pInfos;
  ProgressDialog progressDialog;
  
  public MainActivity() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    this.pInfos = getPackageManager().getInstalledPackages(1);
    this.activityINFOList = new ArrayList();
    this.activityINFOListBack = new ArrayList();
    this.listViewActivity = ((ListView)findViewById(2131230725));
    this.activityAdapter = new ActivityAdapter(this);
    this.listViewActivity.setAdapter(this.activityAdapter);
    new getAllActivities(this).execute(new Void[] { null, null, null });
    this.listViewActivity.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = ((ActivityInfo)MainActivity.this.activityINFOList.get(paramAnonymousInt)).packageName + " / " + ((ActivityInfo)MainActivity.this.activityINFOList.get(paramAnonymousInt)).name;
        paramAnonymousView = new Intent("android.intent.action.SEND");
        paramAnonymousView.setType("text/plain");
        paramAnonymousView.putExtra("android.intent.extra.SUBJECT", "Subject Here");
        paramAnonymousView.putExtra("android.intent.extra.TEXT", paramAnonymousAdapterView);
        MainActivity.this.startActivity(Intent.createChooser(paramAnonymousView, MainActivity.this.getResources().getString(2131034117)));
      }
    });
    this.editSearch = ((EditText)findViewById(2131230724));
    this.editSearch.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        MainActivity.this.activityAdapter.getFilter().filter(paramAnonymousEditable);
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131165184, paramMenu);
    return true;
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return super.onOptionsItemSelected(paramMenuItem);
    }
    openAboutus();
    return true;
  }
  
  void openAboutus()
  {
    startActivity(new Intent(this, AboutUsActivity.class));
  }
  
  class ActivityAdapter
    extends BaseAdapter
    implements Filterable
  {
    MainActivity.FilterApps apps;
    Context context;
    LayoutInflater layoutInflater;
    PackageManager manager;
    
    public ActivityAdapter(Context paramContext)
    {
      this.context = paramContext;
      this.layoutInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
      this.manager = paramContext.getPackageManager();
    }
    
    public int getCount()
    {
      return MainActivity.this.activityINFOList.size();
    }
    
    public Filter getFilter()
    {
      if (this.apps == null) {
        this.apps = new MainActivity.FilterApps(MainActivity.this, this.manager);
      }
      return this.apps;
    }
    
    public ActivityInfo getItem(int paramInt)
    {
      return (ActivityInfo)MainActivity.this.activityINFOList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return 0L;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = this.layoutInflater.inflate(2130903041, null);
        paramViewGroup = new MainActivity.Holder(MainActivity.this);
        paramViewGroup.textView = ((TextView)paramView.findViewById(2131230723));
        paramViewGroup.imgView = ((ImageView)paramView.findViewById(2131230722));
        paramView.setTag(paramViewGroup);
      }
      try
      {
        paramViewGroup.imgView.setImageDrawable(this.manager.getApplicationIcon(((ActivityInfo)MainActivity.this.activityINFOList.get(paramInt)).packageName));
        ApplicationInfo localApplicationInfo = this.manager.getApplicationInfo(((ActivityInfo)MainActivity.this.activityINFOList.get(paramInt)).packageName, 0);
        TextView localTextView = paramViewGroup.textView;
        if (localApplicationInfo != null) {}
        for (paramViewGroup = this.manager.getApplicationLabel(localApplicationInfo);; paramViewGroup = "(unknown)")
        {
          localTextView.setText((String)paramViewGroup + " / " + ((ActivityInfo)MainActivity.this.activityINFOList.get(paramInt)).packageName + " / " + ((ActivityInfo)MainActivity.this.activityINFOList.get(paramInt)).name);
          return paramView;
          paramViewGroup = (MainActivity.Holder)paramView.getTag();
          break;
        }
        return paramView;
      }
      catch (PackageManager.NameNotFoundException paramViewGroup)
      {
        paramViewGroup.printStackTrace();
      }
    }
  }
  
  public class CustomComparator
    implements Comparator<ActivityInfo>
  {
    Context context;
    PackageManager manager;
    
    CustomComparator(Context paramContext)
    {
      this.context = paramContext;
      this.manager = paramContext.getPackageManager();
    }
    
    public int compare(ActivityInfo paramActivityInfo1, ActivityInfo paramActivityInfo2)
    {
      for (;;)
      {
        try
        {
          paramActivityInfo1 = this.manager.getApplicationInfo(paramActivityInfo1.packageName, 0);
          paramActivityInfo2 = this.manager.getApplicationInfo(paramActivityInfo2.packageName, 0);
          if (paramActivityInfo1 != null)
          {
            paramActivityInfo1 = this.manager.getApplicationLabel(paramActivityInfo1);
            String str = ((String)paramActivityInfo1).toUpperCase();
            if (paramActivityInfo2 != null)
            {
              paramActivityInfo1 = this.manager.getApplicationLabel(paramActivityInfo2);
              return str.compareTo(((String)paramActivityInfo1).toUpperCase());
            }
            paramActivityInfo1 = "(unknown)";
            continue;
          }
          paramActivityInfo1 = "(unknown)";
        }
        catch (Exception paramActivityInfo1)
        {
          return 0;
        }
      }
    }
  }
  
  public class FilterApps
    extends Filter
  {
    PackageManager mngr;
    
    public FilterApps(PackageManager paramPackageManager)
    {
      this.mngr = paramPackageManager;
    }
    
    protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
    {
      Filter.FilterResults localFilterResults = new Filter.FilterResults();
      MainActivity.this.activityINFOList = MainActivity.this.activityINFOListBack;
      if ((paramCharSequence == null) || (paramCharSequence.length() == 0))
      {
        localFilterResults.values = MainActivity.this.activityINFOListBack;
        localFilterResults.count = MainActivity.this.activityINFOListBack.size();
        return localFilterResults;
      }
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = MainActivity.this.activityINFOList.iterator();
      if (!localIterator.hasNext())
      {
        localFilterResults.values = localArrayList;
        localFilterResults.count = localArrayList.size();
        return localFilterResults;
      }
      ActivityInfo localActivityInfo = (ActivityInfo)localIterator.next();
      for (;;)
      {
        try
        {
          Object localObject = this.mngr.getApplicationInfo(localActivityInfo.packageName, 0);
          if (localObject == null) {
            break label194;
          }
          localObject = this.mngr.getApplicationLabel((ApplicationInfo)localObject);
          if (!((String)localObject).toUpperCase().startsWith(paramCharSequence.toString().toUpperCase())) {
            break;
          }
          localArrayList.add(localActivityInfo);
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
        break;
        label194:
        String str = "(unknown)";
      }
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
    {
      if (paramFilterResults.count == 0)
      {
        MainActivity.this.activityAdapter.notifyDataSetInvalidated();
        return;
      }
      MainActivity.this.activityINFOList = ((ArrayList)paramFilterResults.values);
      MainActivity.this.activityAdapter.notifyDataSetChanged();
      MainActivity.this.listViewActivity.setSelection(0);
    }
  }
  
  class Holder
  {
    ImageView imgView;
    TextView textView;
    
    Holder() {}
  }
  
  public class getAllActivities
    extends AsyncTask<Void, Void, Void>
  {
    Context context;
    
    public getAllActivities(Context paramContext)
    {
      this.context = paramContext;
    }
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = MainActivity.this.pInfos.iterator();
      for (;;)
      {
        if (!paramVarArgs.hasNext())
        {
          Collections.sort(MainActivity.this.activityINFOList, new MainActivity.CustomComparator(MainActivity.this, this.context));
          return null;
        }
        ActivityInfo[] arrayOfActivityInfo = ((PackageInfo)paramVarArgs.next()).activities;
        if (arrayOfActivityInfo != null)
        {
          int j = arrayOfActivityInfo.length;
          int i = 0;
          while (i < j)
          {
            ActivityInfo localActivityInfo = arrayOfActivityInfo[i];
            MainActivity.this.activityINFOList.add(localActivityInfo);
            i += 1;
          }
        }
      }
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      super.onPostExecute(paramVoid);
      MainActivity.this.activityINFOListBack = MainActivity.this.activityINFOList;
      MainActivity.this.activityAdapter.notifyDataSetChanged();
      MainActivity.this.progressDialog.dismiss();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      MainActivity.this.progressDialog = new ProgressDialog(MainActivity.this);
      MainActivity.this.progressDialog.setMessage("Sorting Apps Alphabetically");
      MainActivity.this.progressDialog.setCancelable(false);
      MainActivity.this.progressDialog.show();
    }
  }
}
