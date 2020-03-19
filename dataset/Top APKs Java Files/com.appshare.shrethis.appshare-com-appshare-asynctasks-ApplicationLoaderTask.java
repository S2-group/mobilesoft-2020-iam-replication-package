package com.appshare.asynctasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import com.appshare.adapters.CustomGrid;
import com.appshare.model.ApplicationBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ApplicationLoaderTask
  extends AsyncTask<Void, Void, List<ApplicationBean>>
{
  List<ApplicationBean> AppBeansList = new ArrayList();
  Activity activity;
  Context context_Task;
  EditText et_Search;
  GridView lv_Packages;
  int mobilecore_timer = 0;
  ProgressDialog pg_AppLoader;
  PackageManager pm;
  
  public ApplicationLoaderTask(Context paramContext, Activity paramActivity, EditText paramEditText, GridView paramGridView, List<ApplicationBean> paramList)
  {
    this.context_Task = paramContext;
    this.activity = paramActivity;
    this.lv_Packages = paramGridView;
    this.AppBeansList = paramList;
    this.et_Search = paramEditText;
  }
  
  private boolean isSystemPackage(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  protected List<ApplicationBean> doInBackground(Void... paramVarArgs)
  {
    this.pm = this.context_Task.getPackageManager();
    paramVarArgs = this.pm.getInstalledApplications(128);
    boolean bool = PreferenceManager.getDefaultSharedPreferences(this.context_Task).getBoolean("preference_show_systemapps", false);
    int i = 0;
    for (;;)
    {
      if (i >= paramVarArgs.size()) {
        return this.AppBeansList;
      }
      try
      {
        PackageInfo localPackageInfo = this.pm.getPackageInfo(((ApplicationInfo)paramVarArgs.get(i)).packageName, 0);
        ApplicationBean localApplicationBean;
        if (bool)
        {
          localApplicationBean = new ApplicationBean();
          localApplicationBean.setAppName(this.pm.getApplicationLabel((ApplicationInfo)paramVarArgs.get(i)).toString());
          localApplicationBean.setAppVersion("Version : " + localPackageInfo.versionName);
          localApplicationBean.setAppPackage(((ApplicationInfo)paramVarArgs.get(i)).packageName);
          localApplicationBean.setAppDrawable(((ApplicationInfo)paramVarArgs.get(i)).icon);
          localApplicationBean.setAppIcon(((ApplicationInfo)paramVarArgs.get(i)).loadIcon(this.pm));
          localApplicationBean.setAppApkPath(((ApplicationInfo)paramVarArgs.get(i)).publicSourceDir);
          this.AppBeansList.add(localApplicationBean);
        }
        else if (!isSystemPackage(localPackageInfo))
        {
          localApplicationBean = new ApplicationBean();
          localApplicationBean.setAppName(this.pm.getApplicationLabel((ApplicationInfo)paramVarArgs.get(i)).toString());
          localApplicationBean.setAppVersion("Version : " + localPackageInfo.versionName);
          localApplicationBean.setAppPackage(((ApplicationInfo)paramVarArgs.get(i)).packageName);
          localApplicationBean.setAppDrawable(((ApplicationInfo)paramVarArgs.get(i)).icon);
          localApplicationBean.setAppIcon(((ApplicationInfo)paramVarArgs.get(i)).loadIcon(this.pm));
          localApplicationBean.setAppApkPath(((ApplicationInfo)paramVarArgs.get(i)).publicSourceDir);
          this.AppBeansList.add(localApplicationBean);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      i += 1;
    }
  }
  
  protected void onPostExecute(final List<ApplicationBean> paramList)
  {
    this.pg_AppLoader.dismiss();
    paramList = new CustomGrid(this.context_Task, this.activity, paramList, this.lv_Packages, this.et_Search);
    this.lv_Packages.setAdapter(paramList);
    this.et_Search.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable) {}
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        paramAnonymousCharSequence = ApplicationLoaderTask.this.et_Search.getText().toString().toLowerCase(Locale.getDefault());
        paramList.filter(paramAnonymousCharSequence);
      }
    });
  }
  
  protected void onPreExecute()
  {
    String str = this.context_Task.getString(2131492941);
    this.pg_AppLoader = ProgressDialog.show(this.context_Task, "", str, false, false);
  }
}
