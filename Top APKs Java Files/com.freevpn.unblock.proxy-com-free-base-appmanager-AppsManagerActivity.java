package com.free.base.appmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout.b;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import com.b.a.f;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.free.base.BaseActivity;
import com.free.base.R.id;
import com.free.base.R.layout;
import com.free.base.appmanager.b.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AppsManagerActivity
  extends BaseActivity
  implements SwipeRefreshLayout.b, View.OnClickListener
{
  private boolean allowedAllFlag;
  private BaseQuickAdapter allowedAppAdapter;
  List<String> allowedAppList = new ArrayList();
  List<com.free.base.appmanager.a.a> appInfoList = new ArrayList();
  private CheckBox checkAllAppCheckBox;
  private PackageManager packageManager;
  private RecyclerView recyclerView;
  
  public AppsManagerActivity()
  {
    super(R.layout.activity_manager_apps);
  }
  
  private boolean checkAllSelected()
  {
    Iterator localIterator = this.appInfoList.iterator();
    while (localIterator.hasNext()) {
      if (!((com.free.base.appmanager.a.a)localIterator.next()).e()) {
        return false;
      }
    }
    return true;
  }
  
  private boolean checkIfInAllowedAppList(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (this.allowedAppList.indexOf(paramString) != -1);
  }
  
  private void doCheckAllApps()
  {
    if (this.checkAllAppCheckBox.isChecked()) {}
    for (boolean bool = true;; bool = false)
    {
      this.allowedAllFlag = bool;
      doSelectAllOrNot(bool);
      this.checkAllAppCheckBox.setChecked(bool);
      return;
    }
  }
  
  private void doSelectAllOrNot(boolean paramBoolean)
  {
    Iterator localIterator = this.appInfoList.iterator();
    while (localIterator.hasNext()) {
      ((com.free.base.appmanager.a.a)localIterator.next()).b(paramBoolean);
    }
    this.allowedAppAdapter.notifyDataSetChanged();
  }
  
  private void doSelectSingleOrNot(com.free.base.appmanager.a.a paramA)
  {
    paramA.b(paramA.e() ^ true);
    if (!paramA.e())
    {
      this.checkAllAppCheckBox.setChecked(false);
      this.allowedAllFlag = false;
    }
    else if (checkAllSelected())
    {
      this.checkAllAppCheckBox.setChecked(true);
      this.allowedAllFlag = true;
    }
    this.allowedAppAdapter.notifyDataSetChanged();
  }
  
  private static com.free.base.appmanager.a.a getAppInfoBean(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    if (paramPackageManager != null)
    {
      if (paramPackageInfo == null) {
        return null;
      }
      ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
      String str1 = paramPackageInfo.packageName;
      if (TextUtils.equals(AppUtils.getAppPackageName(), str1)) {
        return null;
      }
      if (localApplicationInfo.icon == 0) {
        return null;
      }
      paramPackageManager = localApplicationInfo.loadLabel(paramPackageManager).toString();
      String str2 = localApplicationInfo.sourceDir;
      String str3 = paramPackageInfo.versionName;
      int i = paramPackageInfo.versionCode;
      int j = localApplicationInfo.flags;
      boolean bool = true;
      if ((j & 0x1) == 0) {
        bool = false;
      }
      paramPackageInfo = new com.free.base.appmanager.a.a();
      paramPackageInfo.a(localApplicationInfo);
      paramPackageInfo.b(str1);
      paramPackageInfo.a(paramPackageManager);
      paramPackageInfo.c(str2);
      paramPackageInfo.d(str3);
      paramPackageInfo.a(i);
      paramPackageInfo.a(bool);
      if (!bool) {
        paramPackageInfo.b(10000);
      }
      return paramPackageInfo;
    }
    return null;
  }
  
  private void initAllowedAppList()
  {
    try
    {
      Object localObject = SPUtils.getInstance().getString("key_allow_app_list");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localObject = ((String)localObject).split(",");
        if (localObject.length > 0)
        {
          localObject = Arrays.asList((Object[])localObject);
          this.allowedAppList.addAll((Collection)localObject);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("allow app list = ");
          localStringBuilder.append(localObject);
          f.a(localStringBuilder.toString());
          return;
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void initData()
  {
    new a(null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Context[] { this });
  }
  
  private void saveDataAndExit()
  {
    if (this.allowedAllFlag != SPUtils.getInstance().getBoolean("key_if_allowed_all_apps", true)) {
      setResult(-1);
    }
    SPUtils.getInstance().put("key_if_allowed_all_apps", this.allowedAllFlag);
    if (this.allowedAllFlag) {
      SPUtils.getInstance().remove("key_allow_app_list");
    }
    for (;;)
    {
      finish();
      return;
      Object localObject1 = new StringBuilder();
      Object localObject2 = this.appInfoList.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        com.free.base.appmanager.a.a localA = (com.free.base.appmanager.a.a)((Iterator)localObject2).next();
        if (localA.e())
        {
          ((StringBuilder)localObject1).append(localA.c());
          ((StringBuilder)localObject1).append(",");
          if (!checkIfInAllowedAppList(localA.c())) {
            setResult(-1);
          }
        }
      }
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("allowAppList = ");
      ((StringBuilder)localObject2).append((String)localObject1);
      f.a(((StringBuilder)localObject2).toString());
      SPUtils.getInstance().put("key_allow_app_list", (String)localObject1);
    }
  }
  
  public static void startActivity(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent(paramActivity, AppsManagerActivity.class));
  }
  
  protected void initViews()
  {
    Toolbar localToolbar = (Toolbar)findViewById(R.id.toolbar);
    setSupportActionBar(localToolbar);
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
    {
      localActionBar.setDisplayHomeAsUpEnabled(true);
      localActionBar.setDisplayShowHomeEnabled(true);
    }
    localToolbar.setNavigationOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AppsManagerActivity.this.saveDataAndExit();
      }
    });
    this.checkAllAppCheckBox = ((CheckBox)findViewById(R.id.check_all_app));
    this.checkAllAppCheckBox.setOnClickListener(this);
    this.allowedAllFlag = SPUtils.getInstance().getBoolean("key_if_allowed_all_apps", true);
    this.checkAllAppCheckBox.setChecked(this.allowedAllFlag);
    this.recyclerView = ((RecyclerView)findViewById(R.id.recycler_view));
    this.allowedAppAdapter = new BaseQuickAdapter(R.layout.proxy_app_item, this.appInfoList)
    {
      protected void a(BaseViewHolder paramAnonymousBaseViewHolder, com.free.base.appmanager.a.a paramAnonymousA)
      {
        paramAnonymousBaseViewHolder.setImageDrawable(R.id.iv_app_icon, paramAnonymousA.a().loadIcon(AppsManagerActivity.this.packageManager));
        paramAnonymousBaseViewHolder.setText(R.id.tv_app_name, paramAnonymousA.b());
        paramAnonymousBaseViewHolder.setChecked(R.id.switch_proxy, paramAnonymousA.e());
      }
    };
    this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    this.recyclerView.setAdapter(this.allowedAppAdapter);
    this.recyclerView.addOnItemTouchListener(new OnItemClickListener()
    {
      public void onSimpleItemClick(BaseQuickAdapter paramAnonymousBaseQuickAdapter, View paramAnonymousView, int paramAnonymousInt)
      {
        paramAnonymousBaseQuickAdapter = (com.free.base.appmanager.a.a)AppsManagerActivity.this.appInfoList.get(paramAnonymousInt);
        AppsManagerActivity.this.doSelectSingleOrNot(paramAnonymousBaseQuickAdapter);
      }
    });
  }
  
  public void onBackPressed()
  {
    saveDataAndExit();
  }
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == R.id.check_all_app) {
      doCheckAllApps();
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.packageManager = getPackageManager();
    initAllowedAppList();
    initData();
  }
  
  public void onRefresh()
  {
    initData();
  }
  
  private class a
    extends AsyncTask<Context, com.free.base.appmanager.a.a, List<com.free.base.appmanager.a.a>>
  {
    private a() {}
    
    protected List<com.free.base.appmanager.a.a> a(Context... paramVarArgs)
    {
      ArrayList localArrayList1 = new ArrayList();
      ArrayList localArrayList2 = new ArrayList();
      try
      {
        String str = SPUtils.getInstance().getString("key_allow_app_list");
        if (!TextUtils.isEmpty(str)) {
          localArrayList1.addAll(Arrays.asList(str.split(",")));
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      paramVarArgs = paramVarArgs[0].getPackageManager();
      Iterator localIterator = paramVarArgs.getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        com.free.base.appmanager.a.a localA = AppsManagerActivity.getAppInfoBean(paramVarArgs, (PackageInfo)localIterator.next());
        if (localA != null)
        {
          if (!AppsManagerActivity.this.allowedAllFlag) {
            if (localArrayList1.indexOf(localA.c()) != -1)
            {
              localA.b(true);
              localA.b(999999);
            }
            else
            {
              localA.b(false);
            }
          }
          localArrayList2.add(localA);
        }
      }
      com.free.base.appmanager.b.a.a(localArrayList2);
      b.a(Utils.getApp(), localArrayList2);
      return localArrayList2;
    }
    
    protected void a(List<com.free.base.appmanager.a.a> paramList)
    {
      super.onPostExecute(paramList);
      AppsManagerActivity.this.appInfoList.clear();
      AppsManagerActivity.this.appInfoList.addAll(paramList);
      AppsManagerActivity.this.allowedAppAdapter.notifyDataSetChanged();
      AppsManagerActivity.this.dismissProgressDialog();
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
      AppsManagerActivity.this.appInfoList.clear();
      AppsManagerActivity.this.showProgressDialog();
    }
  }
}
