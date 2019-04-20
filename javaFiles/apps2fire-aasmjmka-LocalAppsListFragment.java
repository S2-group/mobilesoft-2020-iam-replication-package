package mobi.koni.appstofiretv;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mobi.koni.appstofiretv.adb.InstallAppToFireTv;
import mobi.koni.appstofiretv.common.AdMob;
import mobi.koni.appstofiretv.common.AnalyticsUtil;
import mobi.koni.appstofiretv.common.App;
import mobi.koni.appstofiretv.common.Utils;
import mobi.koni.appstofiretv.network.NetUtils;

public class LocalAppsListFragment extends ListFragment {

    private static final String TAG = "LocalAppsListFragment";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainActivity activity;
    private PackageManager packageManager = null;
    private List<ApplicationInfo> appList;
    private LocalAppsAdapter listadaptor = null;
    private LoadApplications loadApplicationsTask;
    private ProgressDialog progress = null;

    public LocalAppsListFragment() {
    }

    public static LocalAppsListFragment newInstance() {
        return new LocalAppsListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_local_apps_list, container, false);
        activity = (MainActivity) getActivity();

        setHasOptionsMenu(true);

        // Do not load list again if it is in cache
        if (App.getAppListCache() != null && App.getAppListCache().size() > 0) {
            appList = App.getAppListCache();
            listadaptor = new LocalAppsAdapter(activity, R.layout.apps_list_item, appList);
            setListAdapter(listadaptor);
        }

        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Utils.log(TAG, "onRefresh");
                reloadAppList(activity);
            }
        });

        return rootView;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.search_item).setVisible(true).setEnabled(true);
    }

    @Override
    public void onStart() {
        Utils.log(TAG, "onStart()");
        AnalyticsUtil.sendScreen("Apps List Local");
        if (listadaptor == null || listadaptor.isEmpty()) {
            packageManager = activity.getPackageManager();
            loadApplicationsTask = new LoadApplications();
            loadApplicationsTask.execute();
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        try {
            AlertDialogUtil.dismissProgressDialog(activity, progress, TAG);
            if (loadApplicationsTask != null) {
                loadApplicationsTask.cancel(true);
            }
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        } catch (Exception e) {
            Log.e(TAG, "Cannot cancel loadApplicationsTask. This is OK here...", e);
        }
        super.onDestroy();
    }

    @Override
    public void onResume() {
        Utils.log(TAG, "onResume()");
        if (activity == null) {
            activity = (MainActivity) getActivity();
        }
        AdMob adMob = new AdMob();
        adMob.showRemoveAds(activity);
        super.onResume();
    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        // no ip
        if (!Utils.isIpDefined(activity)) {
            Utils.showAlertDialogRunOnUiThread(activity, R.string.info, R.string.noFireIpDefined);
            // no wifi
        } else if (!NetUtils.isWifiConnected()) {
            Utils.showAlertDialogRunOnUiThread(activity, R.string.info, R.string.wifiNotConnected);
            // OK InstallAppToFireTv
        } else {
            try {
                ApplicationInfo appInfo = appList.get(position);
                if (appInfo == null) {
                    // do nothing
                    return;
                }
                final String filePath = appInfo.publicSourceDir;
                final String packageName = appInfo.packageName;
                final String appName = "" + appInfo.loadLabel(activity.getPackageManager());

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(appName).setCancelable(true);
                builder.setPositiveButton(activity.getString(R.string.install), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        new InstallAppToFireTv(activity, appName, filePath).execute();
                    }
                });
                builder.setNeutralButton(activity.getString(R.string.appInfo), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Utils.showAppInfo(activity, packageName);
                    }
                });
                builder.create().show();
            } catch (Exception e) {
                Utils.log(TAG, "Exception: appList=" + appList);
                Utils.log(TAG, "Exception: " + e);
                Utils.showToast(activity, "Ooops - " + activity.getString(R.string.pleaseReloadList) + "!");
            }
        }
    }

    @SuppressLint("NewApi")
    private List<ApplicationInfo> getAppInfoList(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<>();
        App.getPackageIcons().clear();
        for (ApplicationInfo info : list) {
            try {
                if (loadApplicationsTask != null && loadApplicationsTask.isCancelled()) {
                    return applist;
                }
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                    if (Utils.isPlatformBelow_5_1()) {
                        App.getPackageIcons().put(info.packageName, info.loadIcon(packageManager));
                    } else {
                        App.getPackageIcons().put(info.packageName, info.loadUnbadgedIcon(packageManager));
                    }
                }
            } catch (OutOfMemoryError e) {
                try {
                    Utils.showToast(activity, "OutOfMemoryError");
                } catch (Exception e1) {
                    // all lost. do nothing
                }
            } catch (Exception e) {
                Log.e(TAG, "Error getAppInfoList ", e);
            }
        }
        return applist;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        SearchView searchView = new SearchView(activity.getSupportActionBar().getThemedContext());
        MenuItem item = menu.findItem(R.id.search_item);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // When user changed the Text
                try {
                    listadaptor.getFilter().filter(newText);
                } catch (Exception e) {
                    Utils.showToast(activity, "Ooops can not filter...");
                    Utils.log(TAG, "Error wile filtering");
                    // Analyze crashes
                    AnalyticsUtil.sendEvent(activity, AnalyticsUtil.ERROR, "Filter " + TAG, "Text entered=" + newText);
                    AnalyticsUtil.sendEvent(activity, AnalyticsUtil.ERROR, "Filter " + TAG, "listadaptor=" + listadaptor);
                    if (listadaptor != null) {
                        AnalyticsUtil.sendEvent(activity, AnalyticsUtil.ERROR, "Filter " + TAG, "listadaptor.getFilter()=" + listadaptor.getFilter());
                    }
                }

                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void reloadAppList(MainActivity activity) {
        this.activity = activity;
        if (this.packageManager == null) {
            this.packageManager = activity.getPackageManager();
        }
        // reset list
        appList = new ArrayList<>();
        // load list
        loadApplicationsTask = new LoadApplications();
        loadApplicationsTask.execute();
    }

    // --------------------------------------------------------
    // LoadApplications
    // --------------------------------------------------------
    private class LoadApplications extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            if (appList == null || appList.isEmpty()) {
                appList = getAppInfoList(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
                Collections.sort(appList, new ApplicationInfoComparator(packageManager));
                App.setAppListCache(appList);
                listadaptor = new LocalAppsAdapter(activity, R.layout.apps_list_item, appList);
            }
            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {
            setListAdapter(listadaptor);
            listadaptor.notifyDataSetChanged();
            if (isAdded()) {
                AlertDialogUtil.dismissProgressDialog(activity, progress, TAG);
            }
            mSwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(activity);
            progress.setMessage(activity.getString(R.string.loadingLocalAppList));
            progress.setCancelable(false);
            progress.setButton(DialogInterface.BUTTON_NEGATIVE, activity.getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (loadApplicationsTask != null) {
                        loadApplicationsTask.cancel(true);
                    }
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }
            });
            progress.show();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
