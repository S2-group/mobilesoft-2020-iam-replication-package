package com.alc.filemanager.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.alc.filemanager.activities.MainActivity;
import com.alc.filemanager.adapters.AppsAdapter;
import com.alc.filemanager.ui.Layoutelements;
import com.alc.filemanager.ui.icons.IconHolder;
import com.alc.filemanager.utils.FileListSorter;
import com.alc.filemanager.utils.provider.UtilitiesProviderInterface;
import com.alc.filemanager.utils.theme.AppTheme;
import com.github.clans.fab.FloatingActionMenu;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppsList
  extends ListFragment
{
  public SharedPreferences Sp;
  ArrayList<Layoutelements> a = new ArrayList();
  AppsAdapter adapter;
  AppsList app = this;
  int asc;
  BroadcastReceiver br = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent != null) {
        AppsList.this.loadlist(true);
      }
    }
  };
  public ArrayList<PackageInfo> c = new ArrayList();
  public IconHolder ic;
  int index = 0;
  private MainActivity mainActivity;
  private IntentFilter packageFilter;
  int sortby;
  int top = 0;
  UtilitiesProviderInterface utilsProvider;
  ListView vl;
  
  public AppsList() {}
  
  public static int getToolbarHeight(Context paramContext)
  {
    paramContext = paramContext.getTheme().obtainStyledAttributes(new int[] { 16843499 });
    int i = (int)paramContext.getDimension(0, 0.0F);
    paramContext.recycle();
    return i;
  }
  
  public void getSortModes()
  {
    int i = Integer.parseInt(this.Sp.getString("sortbyApps", "0"));
    if (i <= 2)
    {
      this.sortby = i;
      this.asc = 1;
    }
    while (i <= 2) {
      return;
    }
    this.asc = -1;
    this.sortby = (i - 3);
  }
  
  public void loadlist(boolean paramBoolean)
  {
    View localView;
    if (paramBoolean)
    {
      this.index = this.vl.getFirstVisiblePosition();
      localView = this.vl.getChildAt(0);
      if (localView != null) {
        break label61;
      }
    }
    label61:
    for (int i = 0;; i = localView.getTop())
    {
      this.top = i;
      new LoadListTask(paramBoolean, this.top, this.index).execute(new Void[0]);
      return;
    }
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    setRetainInstance(true);
    this.mainActivity = ((MainActivity)getActivity());
    this.mainActivity.setActionBarTitle(getResources().getString(2131296351));
    this.mainActivity.floatingActionButton.hideMenuButton(true);
    this.mainActivity.buttonBarFrame.setVisibility(8);
    this.mainActivity.supportInvalidateOptionsMenu();
    this.vl = getListView();
    this.Sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
    getSortModes();
    ListView localListView = getListView();
    localListView.setDivider(null);
    if (this.utilsProvider.getAppTheme().equals(AppTheme.DARK)) {
      getActivity().getWindow().getDecorView().setBackgroundColor(getResources().getColor(2131624022));
    }
    if (paramBundle == null) {
      loadlist(false);
    }
    for (;;)
    {
      setHasOptionsMenu(true);
      return;
      localListView.setSelectionFromTop(paramBundle.getInt("index"), paramBundle.getInt("top"));
      localListView.setSelectionFromTop(paramBundle.getInt("index"), paramBundle.getInt("top"));
      loadlist(false);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.utilsProvider = ((UtilitiesProviderInterface)getActivity());
    setHasOptionsMenu(false);
    this.ic = new IconHolder(getActivity(), true, true);
  }
  
  public void onCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    paramMenuInflater.inflate(2131820546, paramMenu);
    super.onCreateOptionsMenu(paramMenu, paramMenuInflater);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return super.onOptionsItemSelected(paramMenuItem);
      Toast.makeText(getActivity(), getResources().getText(2131296506), 0).show();
      loadlist(false);
    }
  }
  
  public void onPause()
  {
    super.onPause();
    getActivity().unregisterReceiver(this.br);
  }
  
  public void onResume()
  {
    super.onResume();
    this.packageFilter = new IntentFilter();
    this.packageFilter.addAction("android.intent.action.PACKAGE_ADDED");
    this.packageFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    this.packageFilter.addDataScheme("package");
    getActivity().registerReceiver(this.br, this.packageFilter);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    int i = 0;
    super.onSaveInstanceState(paramBundle);
    int j;
    View localView;
    if (this.vl != null)
    {
      j = this.vl.getFirstVisiblePosition();
      localView = this.vl.getChildAt(0);
      if (localView != null) {
        break label52;
      }
    }
    for (;;)
    {
      paramBundle.putInt("index", j);
      paramBundle.putInt("top", i);
      return;
      label52:
      i = localView.getTop();
    }
  }
  
  public boolean unin(String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.DELETE");
      localIntent.setData(Uri.parse("package:" + paramString));
      startActivity(localIntent);
      return true;
    }
    catch (Exception paramString)
    {
      Toast.makeText(getActivity(), "" + paramString, 0).show();
      paramString.printStackTrace();
    }
    return false;
  }
  
  class LoadListTask
    extends AsyncTask<Void, Void, ArrayList<Layoutelements>>
  {
    int index;
    boolean save;
    int top;
    
    public LoadListTask(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      this.save = paramBoolean;
      this.index = paramInt2;
      this.top = paramInt1;
    }
    
    protected ArrayList<Layoutelements> doInBackground(Void[] paramArrayOfVoid)
    {
      try
      {
        paramArrayOfVoid = AppsList.this.getActivity().getPackageManager();
        Object localObject = paramArrayOfVoid.getInstalledPackages(128);
        AppsList.this.a = new ArrayList();
        AppsList.this.c = new ArrayList();
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          File localFile = new File(localPackageInfo.applicationInfo.publicSourceDir);
          AppsList.this.a.add(new Layoutelements(new BitmapDrawable(AppsList.this.getActivity().getResources(), BitmapFactory.decodeResource(AppsList.this.getActivity().getResources(), 2130837689)), localPackageInfo.applicationInfo.loadLabel(paramArrayOfVoid).toString(), localPackageInfo.applicationInfo.publicSourceDir, localPackageInfo.packageName, localPackageInfo.versionName, Formatter.formatFileSize(AppsList.this.getContext(), localFile.length()), localFile.length(), false, localFile.lastModified() + "", false));
          AppsList.this.c.add(localPackageInfo);
          continue;
          return AppsList.this.a;
        }
      }
      catch (Exception paramArrayOfVoid) {}
      for (;;)
      {
        Collections.sort(AppsList.this.a, new FileListSorter(0, AppsList.this.sortby, AppsList.this.asc, false));
      }
    }
    
    protected void onPostExecute(ArrayList<Layoutelements> paramArrayList)
    {
      if (isCancelled()) {
        paramArrayList = null;
      }
      if (paramArrayList != null) {}
      try
      {
        AppsList.this.adapter = new AppsAdapter(AppsList.this.getActivity(), AppsList.this.utilsProvider, 2130968688, paramArrayList, AppsList.this.app, AppsList.this.c);
        AppsList.this.setListAdapter(AppsList.this.adapter);
        if ((this.save) && (AppsList.this.getListView() != null)) {
          AppsList.this.getListView().setSelectionFromTop(this.index, this.top);
        }
        return;
      }
      catch (Exception paramArrayList) {}
    }
    
    protected void onPreExecute() {}
  }
}
