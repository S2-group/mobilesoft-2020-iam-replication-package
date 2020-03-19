/*
 * Copyright (C) 2013-2015 Jeffrey Rusterholz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.jalava.appostle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class AppListFragment extends Fragment {
    final static String TAG = "APPLIST";
    public final static String PREFS_APP_TYPE = "APP_TYPE"; // 0 = installed, 1 = system, 2 = all
    public final static String PREFS_APP_SORT = "APP_SORT"; // 0 = date, 1 = alpha
    public final static String PREFS_APP_SORT_DIRECTION = "APP_SORT_DIRECTION"; // 0 = desc, 1 = asc.
    public final static String PREFS_SEARCH_EDIT_OPEN = "PREFS_SEARCH_EDIT_OPEN";
    public final static String PREFS_SEARCH_EDIT_TEXT = "PREFS_SEARCH_EDIT_TEXT";
    public final static String PREFS_SEARCH_EDIT_POSITION = "PREFS_SEARCH_EDIT_POSITION";

    // Listener.
    private OnItemSelectedListener listener;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EditText mSearchText;
    private boolean mShowProgress;
    private RelativeLayout mSearchEdit;

    private ListView appList;
    private ProgressBar progress;
    private TextView progress_loading;
    private Context mContext;
    private PackageManager pm;
    private AppInfoAdapter mAppInfoAdapter;

    private SharedPreferences mPrefs;
    private int mCurrentAppType;              // Current app type.
    private int mCurrentSort;
    private int mCurrentSortDirection;
    private boolean mSearchEditOpen;
    private String mSearchEditText;
    private int mSearchEditTextPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPrefs = getActivity().getSharedPreferences("AppListFragment", Context.MODE_PRIVATE);
        mCurrentAppType = mPrefs.getInt(PREFS_APP_TYPE, 0); // Installed
        mCurrentSort = mPrefs.getInt(PREFS_APP_SORT, 0);    // Date
        mCurrentSortDirection = mPrefs.getInt(PREFS_APP_SORT_DIRECTION, 1); // Descending
        mSearchEditOpen = mPrefs.getBoolean(PREFS_SEARCH_EDIT_OPEN, false); // No search text visible.
        mSearchEditText = mPrefs.getString(PREFS_SEARCH_EDIT_TEXT, "");
        mSearchEditTextPosition = mPrefs.getInt(PREFS_SEARCH_EDIT_POSITION, 0);
        mShowProgress = true;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor ed = mPrefs.edit();

        ed.putInt(PREFS_APP_TYPE, mCurrentAppType)
        .putInt(PREFS_APP_SORT, mCurrentSort)
        .putInt(PREFS_APP_SORT_DIRECTION, mCurrentSortDirection)
        .putBoolean(PREFS_SEARCH_EDIT_OPEN, mSearchEditOpen)
        .putString(PREFS_SEARCH_EDIT_TEXT, mSearchEditText)
        .putInt(PREFS_SEARCH_EDIT_POSITION, mSearchText.getSelectionStart())
        .apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_list, container, false);
        appList = (ListView) view.findViewById(R.id.listView1);
        progress = (ProgressBar) view.findViewById(R.id.progressBar);
        progress_loading = (TextView) view.findViewById(R.id.progress_loading);

        progress.bringToFront();
        progress_loading.bringToFront();

        mContext = view.getContext();

        // Make list clickable and fast scrollable.
        appList.setClickable(true);
        appList.setFastScrollEnabled(true);

        // OnClick
        appList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo o = (AppInfo) appList.getItemAtPosition(position);
                listener.onAppSelected(o);
            }
        });

        // Swipe to refresh.
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_apps);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new UpdateAppList().execute();
            }
        });

        mSearchEdit = (RelativeLayout) view.findViewById(R.id.search_layout);

        // Restore the last visibility state of the text edit.
        if (mSearchEditOpen)
            mSearchEdit.setVisibility(View.VISIBLE);
        else
            mSearchEdit.setVisibility(View.GONE);

        mSearchText = (EditText) view.findViewById(R.id.search_app_edit);

        // Restore text and cursor position.
        mSearchText.setText(mSearchEditText);
        mSearchText.setSelection(mSearchEditTextPosition);

        mSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (mAppInfoAdapter != null) mAppInfoAdapter.getFilter().filter(s);
                mSearchEditText = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        // Activate keyboard if search text edit is opened.
        // Close if no focus.
        mSearchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // Open keyboard
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(v,
                            InputMethodManager.SHOW_FORCED);
                } else {
                    // Close keyboard.
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            v.getWindowToken(), 0);
                }
            }
        });

        // Clear text button.
        Button clear = (Button) view.findViewById(R.id.search_clear_button);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchText.setText("");
            }
        });

        // Close search.
        Button close = (Button) view.findViewById(R.id.search_close_button);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchEdit.setVisibility(View.GONE);
                mSearchEditOpen = false;
            }
         });

        // Prevent always refreshing when pulling listview down.
        appList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    mSwipeRefreshLayout.setEnabled(true);
                } else mSwipeRefreshLayout.setEnabled(false);
            }
        });

        pm = mContext.getPackageManager();
        doUpdate(-1);

        return view;
    }

    public void doUpdate(int currentType) {
        if (currentType >= 0) {
            mCurrentAppType = currentType;
        }
        new UpdateAppList().execute();
    }

    public void startSearch() {
        if (mSearchEdit.getVisibility() == View.GONE) {
            mSearchEdit.setVisibility(View.VISIBLE);
            mSearchText.requestFocus();
            mSearchEditOpen = true;
        } else {
            // Remove search edit. Reload the app list.
            mSearchEdit.setVisibility(View.GONE);
            mSearchEditOpen = false;
            //mSearchText.setText("");
            //doUpdate(-1);
        }
    }

    // Interface for communication with listener.
    public interface OnItemSelectedListener {
        void onAppSelected(AppInfo app);
    }

    public int getCurrentSort() {
        return mCurrentSort;
    }

    public void setCurrentSort(int CurrentSort) {
        this.mCurrentSort = CurrentSort;
    }

    public int getCurrentSortDirection() {
        return mCurrentSortDirection;
    }

    public void setCurrentSortDirection(int CurrentSortDirection) {
        this.mCurrentSortDirection = CurrentSortDirection;
    }

    public void sortAppList() {
        SortAppInfoAdapter();
    }

    private void updateApps() {

        List<PackageInfo> apps = pm.getInstalledPackages(0);
        Vector<AppInfo> app_data = new Vector<>();

        for (PackageInfo pi : apps) {
            ApplicationInfo ai;
            try {
                ai = pm.getApplicationInfo(pi.packageName, 0);
            } catch (NameNotFoundException e) {
                continue;
            }

            // Which apps?
            switch (mCurrentAppType) {
                case 0:
                    if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) != 0)
                        continue; // Skip system apps.
                    break;
                case 1:
                    if ((ai.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
                        continue; // Skip installed apps.
                    break;
            }

            String name = (String) pm.getApplicationLabel(ai);

            // Get last updated date.
            long updated = pi.lastUpdateTime;

            // Localized date.
            String dateString = android.text.format.DateFormat.getDateFormat(mContext).format(new Date(updated));

            // Get app info.
            AppInfo app = new AppInfo();
            app.name = name;
            app.date = dateString;
            app.version = pi.versionName;
            app.packageName = pi.packageName;
            app.lastUpdateTime = updated;
            app_data.add(app);
        }

        mAppInfoAdapter = new AppInfoAdapter(mContext, R.layout.app_row, app_data);
        SortAppInfoAdapter();
    }

    private void SortAppInfoAdapter() {
        // Sort array by date/name descending/ascending.
        // FIX: Also compare that date or name can be the same. JR, 2016-01-13.
        mAppInfoAdapter.sort(new Comparator<AppInfo>() {
            public int compare(AppInfo app1, AppInfo app2) {
                int comp;

                if (mCurrentSort == 0) {
                    if (app1.lastUpdateTime == app2.lastUpdateTime) {
                        comp = 0;
                    } else if (app1.lastUpdateTime > app2.lastUpdateTime) {
                        comp = mCurrentSortDirection == 0 ? 1 : -1;
                    } else {
                        comp = mCurrentSortDirection == 0 ? -1 : 1;
                    }
                } else {
                    if (app1.name.compareToIgnoreCase(app2.name) == 0) {
                        comp = 0;
                    } else if (app1.name.compareToIgnoreCase(app2.name) > 0) {
                        comp = mCurrentSortDirection == 0 ? 1 : -1;
                    } else {
                        comp = mCurrentSortDirection == 0 ? -1 : 1;
                    }
                }
                return comp;
            }
        });
    }

    private class UpdateAppList extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            updateApps();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mShowProgress) {
                progress.setVisibility(View.VISIBLE);
                progress_loading.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progress.setVisibility(View.GONE);
            progress_loading.setVisibility(View.GONE);
            appList.setAdapter(mAppInfoAdapter);

            // Fill the detail view if available.
            FragmentManager fm = getFragmentManager();
            if (fm != null) {
                AppDetailFragment det;
                det = (AppDetailFragment) fm.findFragmentById(R.id.app_detail);
                if ((det != null) && det.isInLayout()) {
                    if (mAppInfoAdapter.getCount() > 0) {
                        det.fillDetail(mAppInfoAdapter.mData.elementAt(0).packageName, mAppInfoAdapter.mData.elementAt(0).date);
                    }
                }
            }

            mSwipeRefreshLayout.setRefreshing(false);

            // Activate any search text/filter.
            if (!mSearchEditText.isEmpty()) {
                if (mAppInfoAdapter != null) mAppInfoAdapter.getFilter().filter(mSearchEditText);
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement AppListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
