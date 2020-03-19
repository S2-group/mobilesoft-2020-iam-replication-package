package com.hiddenapps.hiddenscreenrecoder.preferences;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.DialogPreference;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.hiddenapps.hiddenscreenrecoder.adapter.Apps;
import com.hiddenapps.hiddenscreenrecoder.adapter.AppsListFragmentAdapter;
import com.hiddenapps.hiddenscreenrecoder.adapter.AppsListFragmentAdapter.OnItemClicked;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppPickerPreference
  extends DialogPreference
  implements AppsListFragmentAdapter.OnItemClicked
{
  private ArrayList<Apps> apps;
  private ProgressBar progressBar;
  private RecyclerView recyclerView;
  
  public AppPickerPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setPersistent(true);
    setDialogLayoutResource(2131361853);
  }
  
  private void init()
  {
    LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getContext());
    this.recyclerView.setLayoutManager(localLinearLayoutManager);
    new GetApps().execute(new Void[0]);
  }
  
  protected void onBindDialogView(View paramView)
  {
    super.onBindDialogView(paramView);
    this.progressBar = ((ProgressBar)paramView.findViewById(2131230758));
    this.recyclerView = ((RecyclerView)paramView.findViewById(2131230759));
    init();
  }
  
  protected View onCreateDialogView()
  {
    return super.onCreateDialogView();
  }
  
  public void onItemClick(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Closing dialog. received result. Pos:");
    localStringBuilder.append(paramInt);
    Log.d("SCREENRECORDER_LOG", localStringBuilder.toString());
    persistString(((Apps)this.apps.get(paramInt)).getPackageName());
    getDialog().dismiss();
  }
  
  protected void onPrepareDialogBuilder(AlertDialog.Builder paramBuilder)
  {
    super.onPrepareDialogBuilder(paramBuilder);
    paramBuilder.setPositiveButton(null, null);
  }
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    super.onSetInitialValue(paramBoolean, paramObject);
  }
  
  class GetApps
    extends AsyncTask<Void, Void, ArrayList<Apps>>
  {
    GetApps() {}
    
    protected ArrayList<Apps> doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = AppPickerPreference.this.getContext().getPackageManager();
      AppPickerPreference.access$202(AppPickerPreference.this, new ArrayList());
      Iterator localIterator = paramVarArgs.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((!AppPickerPreference.this.getContext().getPackageName().equals(localPackageInfo.packageName)) && (paramVarArgs.getLaunchIntentForPackage(localPackageInfo.packageName) != null))
        {
          Apps localApps = new Apps(localPackageInfo.applicationInfo.loadLabel(AppPickerPreference.this.getContext().getPackageManager()).toString(), localPackageInfo.packageName, localPackageInfo.applicationInfo.loadIcon(AppPickerPreference.this.getContext().getPackageManager()));
          localApps.setSelectedApp(AppPickerPreference.this.getPersistedString("none").equals(localPackageInfo.packageName));
          if (paramVarArgs.getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
            Log.d("SCREENRECORDER_LOG", localPackageInfo.packageName);
          }
          AppPickerPreference.this.apps.add(localApps);
        }
        Collections.sort(AppPickerPreference.this.apps);
      }
      return AppPickerPreference.this.apps;
    }
    
    protected void onPostExecute(ArrayList<Apps> paramArrayList)
    {
      super.onPostExecute(paramArrayList);
      AppPickerPreference.this.progressBar.setVisibility(8);
      paramArrayList = new AppsListFragmentAdapter(paramArrayList);
      AppPickerPreference.this.recyclerView.setAdapter(paramArrayList);
      paramArrayList.setOnClick(AppPickerPreference.this);
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
  }
}
