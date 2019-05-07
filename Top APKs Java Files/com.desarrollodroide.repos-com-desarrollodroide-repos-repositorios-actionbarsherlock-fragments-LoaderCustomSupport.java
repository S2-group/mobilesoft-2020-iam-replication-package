package com.desarrollodroide.repos.repositorios.actionbarsherlock.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SearchViewCompat;
import android.support.v4.widget.SearchViewCompat.OnQueryTextListenerCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import java.io.File;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class LoaderCustomSupport
  extends SherlockFragmentActivity
{
  public static final Comparator<AppEntry> ALPHA_COMPARATOR = new Comparator()
  {
    private final Collator sCollator = Collator.getInstance();
    
    public int compare(LoaderCustomSupport.AppEntry paramAnonymousAppEntry1, LoaderCustomSupport.AppEntry paramAnonymousAppEntry2)
    {
      return this.sCollator.compare(paramAnonymousAppEntry1.getLabel(), paramAnonymousAppEntry2.getLabel());
    }
  };
  
  public LoaderCustomSupport() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getSupportFragmentManager();
    if (paramBundle.findFragmentById(16908290) == null)
    {
      AppListFragment localAppListFragment = new AppListFragment();
      paramBundle.beginTransaction().add(16908290, localAppListFragment).commit();
    }
  }
  
  public static class AppEntry
  {
    private final File mApkFile;
    private Drawable mIcon;
    private final ApplicationInfo mInfo;
    private String mLabel;
    private final LoaderCustomSupport.AppListLoader mLoader;
    private boolean mMounted;
    
    public AppEntry(LoaderCustomSupport.AppListLoader paramAppListLoader, ApplicationInfo paramApplicationInfo)
    {
      this.mLoader = paramAppListLoader;
      this.mInfo = paramApplicationInfo;
      this.mApkFile = new File(paramApplicationInfo.sourceDir);
    }
    
    public ApplicationInfo getApplicationInfo()
    {
      return this.mInfo;
    }
    
    public Drawable getIcon()
    {
      if (this.mIcon == null)
      {
        if (this.mApkFile.exists())
        {
          this.mIcon = this.mInfo.loadIcon(this.mLoader.mPm);
          return this.mIcon;
        }
        this.mMounted = false;
      }
      do
      {
        return this.mLoader.getContext().getResources().getDrawable(17301651);
        if (this.mMounted) {
          break;
        }
      } while (!this.mApkFile.exists());
      this.mMounted = true;
      this.mIcon = this.mInfo.loadIcon(this.mLoader.mPm);
      return this.mIcon;
      return this.mIcon;
    }
    
    public String getLabel()
    {
      return this.mLabel;
    }
    
    void loadLabel(Context paramContext)
    {
      if ((this.mLabel == null) || (!this.mMounted))
      {
        if (!this.mApkFile.exists())
        {
          this.mMounted = false;
          this.mLabel = this.mInfo.packageName;
        }
      }
      else {
        return;
      }
      this.mMounted = true;
      paramContext = this.mInfo.loadLabel(paramContext.getPackageManager());
      if (paramContext != null) {}
      for (paramContext = paramContext.toString();; paramContext = this.mInfo.packageName)
      {
        this.mLabel = paramContext;
        return;
      }
    }
    
    public String toString()
    {
      return this.mLabel;
    }
  }
  
  public static class AppListAdapter
    extends ArrayAdapter<LoaderCustomSupport.AppEntry>
  {
    private final LayoutInflater mInflater;
    
    public AppListAdapter(Context paramContext)
    {
      super(17367044);
      this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null) {
        paramView = this.mInflater.inflate(2130903518, paramViewGroup, false);
      }
      for (;;)
      {
        paramViewGroup = (LoaderCustomSupport.AppEntry)getItem(paramInt);
        ((ImageView)paramView.findViewById(2131428201)).setImageDrawable(paramViewGroup.getIcon());
        ((TextView)paramView.findViewById(2131427877)).setText(paramViewGroup.getLabel());
        return paramView;
      }
    }
    
    public void setData(List<LoaderCustomSupport.AppEntry> paramList)
    {
      clear();
      if (paramList != null) {
        paramList = paramList.iterator();
      }
      for (;;)
      {
        if (!paramList.hasNext()) {
          return;
        }
        add((LoaderCustomSupport.AppEntry)paramList.next());
      }
    }
  }
  
  public static class AppListFragment
    extends SherlockListFragment
    implements LoaderManager.LoaderCallbacks<List<LoaderCustomSupport.AppEntry>>
  {
    LoaderCustomSupport.AppListAdapter mAdapter;
    String mCurFilter;
    SearchViewCompat.OnQueryTextListenerCompat mOnQueryTextListenerCompat;
    
    public AppListFragment() {}
    
    public void onActivityCreated(Bundle paramBundle)
    {
      super.onActivityCreated(paramBundle);
      setEmptyText("No applications");
      setHasOptionsMenu(true);
      this.mAdapter = new LoaderCustomSupport.AppListAdapter(getActivity());
      setListAdapter(this.mAdapter);
      setListShown(false);
      getLoaderManager().initLoader(0, null, this);
    }
    
    public Loader<List<LoaderCustomSupport.AppEntry>> onCreateLoader(int paramInt, Bundle paramBundle)
    {
      return new LoaderCustomSupport.AppListLoader(getActivity());
    }
    
    public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
    {
      paramMenu = paramMenu.add("Search");
      paramMenu.setIcon(17301583);
      paramMenu.setShowAsAction(1);
      paramMenuInflater = SearchViewCompat.newSearchView(getActivity());
      if (paramMenuInflater != null)
      {
        SearchViewCompat.setOnQueryTextListener(paramMenuInflater, new SearchViewCompat.OnQueryTextListenerCompat()
        {
          public boolean onQueryTextChange(String paramAnonymousString)
          {
            LoaderCustomSupport.AppListFragment localAppListFragment = LoaderCustomSupport.AppListFragment.this;
            if (!TextUtils.isEmpty(paramAnonymousString)) {}
            for (;;)
            {
              localAppListFragment.mCurFilter = paramAnonymousString;
              LoaderCustomSupport.AppListFragment.this.mAdapter.getFilter().filter(LoaderCustomSupport.AppListFragment.this.mCurFilter);
              return true;
              paramAnonymousString = null;
            }
          }
        });
        paramMenu.setActionView(paramMenuInflater);
      }
    }
    
    public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong)
    {
      Log.i("LoaderCustom", "Item clicked: " + paramLong);
    }
    
    public void onLoadFinished(Loader<List<LoaderCustomSupport.AppEntry>> paramLoader, List<LoaderCustomSupport.AppEntry> paramList)
    {
      this.mAdapter.setData(paramList);
      if (isResumed())
      {
        setListShown(true);
        return;
      }
      setListShownNoAnimation(true);
    }
    
    public void onLoaderReset(Loader<List<LoaderCustomSupport.AppEntry>> paramLoader)
    {
      this.mAdapter.setData(null);
    }
  }
  
  public static class AppListLoader
    extends AsyncTaskLoader<List<LoaderCustomSupport.AppEntry>>
  {
    List<LoaderCustomSupport.AppEntry> mApps;
    final LoaderCustomSupport.InterestingConfigChanges mLastConfig = new LoaderCustomSupport.InterestingConfigChanges();
    LoaderCustomSupport.PackageIntentReceiver mPackageObserver;
    final PackageManager mPm = getContext().getPackageManager();
    
    public AppListLoader(Context paramContext)
    {
      super();
    }
    
    public void deliverResult(List<LoaderCustomSupport.AppEntry> paramList)
    {
      if ((isReset()) && (paramList != null)) {
        onReleaseResources(paramList);
      }
      this.mApps = paramList;
      if (isStarted()) {
        super.deliverResult(paramList);
      }
      if (paramList != null) {
        onReleaseResources(paramList);
      }
    }
    
    public List<LoaderCustomSupport.AppEntry> loadInBackground()
    {
      Object localObject2 = this.mPm.getInstalledApplications(8704);
      Object localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new ArrayList();
      }
      localObject2 = getContext();
      ArrayList localArrayList = new ArrayList(((List)localObject1).size());
      int i = 0;
      for (;;)
      {
        if (i >= ((List)localObject1).size())
        {
          Collections.sort(localArrayList, LoaderCustomSupport.ALPHA_COMPARATOR);
          return localArrayList;
        }
        LoaderCustomSupport.AppEntry localAppEntry = new LoaderCustomSupport.AppEntry(this, (ApplicationInfo)((List)localObject1).get(i));
        localAppEntry.loadLabel((Context)localObject2);
        localArrayList.add(localAppEntry);
        i += 1;
      }
    }
    
    public void onCanceled(List<LoaderCustomSupport.AppEntry> paramList)
    {
      super.onCanceled(paramList);
      onReleaseResources(paramList);
    }
    
    protected void onReleaseResources(List<LoaderCustomSupport.AppEntry> paramList) {}
    
    protected void onReset()
    {
      super.onReset();
      onStopLoading();
      if (this.mApps != null)
      {
        onReleaseResources(this.mApps);
        this.mApps = null;
      }
      if (this.mPackageObserver != null)
      {
        getContext().unregisterReceiver(this.mPackageObserver);
        this.mPackageObserver = null;
      }
    }
    
    protected void onStartLoading()
    {
      if (this.mApps != null) {
        deliverResult(this.mApps);
      }
      if (this.mPackageObserver == null) {
        this.mPackageObserver = new LoaderCustomSupport.PackageIntentReceiver(this);
      }
      boolean bool = this.mLastConfig.applyNewConfig(getContext().getResources());
      if ((takeContentChanged()) || (this.mApps == null) || (bool)) {
        forceLoad();
      }
    }
    
    protected void onStopLoading()
    {
      cancelLoad();
    }
  }
  
  public static class InterestingConfigChanges
  {
    final Configuration mLastConfiguration = new Configuration();
    int mLastDensity;
    
    public InterestingConfigChanges() {}
    
    boolean applyNewConfig(Resources paramResources)
    {
      boolean bool = false;
      int j = this.mLastConfiguration.updateFrom(paramResources.getConfiguration());
      if (this.mLastDensity != paramResources.getDisplayMetrics().densityDpi) {}
      for (int i = 1;; i = 0)
      {
        if ((i != 0) || ((j & 0x304) != 0))
        {
          this.mLastDensity = paramResources.getDisplayMetrics().densityDpi;
          bool = true;
        }
        return bool;
      }
    }
  }
  
  public static class PackageIntentReceiver
    extends BroadcastReceiver
  {
    final LoaderCustomSupport.AppListLoader mLoader;
    
    public PackageIntentReceiver(LoaderCustomSupport.AppListLoader paramAppListLoader)
    {
      this.mLoader = paramAppListLoader;
      paramAppListLoader = new IntentFilter("android.intent.action.PACKAGE_ADDED");
      paramAppListLoader.addAction("android.intent.action.PACKAGE_REMOVED");
      paramAppListLoader.addAction("android.intent.action.PACKAGE_CHANGED");
      paramAppListLoader.addDataScheme("package");
      this.mLoader.getContext().registerReceiver(this, paramAppListLoader);
      paramAppListLoader = new IntentFilter();
      paramAppListLoader.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
      paramAppListLoader.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
      this.mLoader.getContext().registerReceiver(this, paramAppListLoader);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      this.mLoader.onContentChanged();
    }
  }
}
