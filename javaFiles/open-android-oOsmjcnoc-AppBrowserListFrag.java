package com.nickstephen.openandroid.components;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nickstephen.lib.gui.FragmentUtil;
import com.nickstephen.lib.gui.ListFragment;
import com.nickstephen.lib.misc.Comparators;
import com.nickstephen.lib.misc.StatMethods;
import com.nickstephen.openandroid.R;
import com.nickstephen.openandroid.tasks.LazyLogoLoader;
import com.nickstephen.openandroid.util.Constants;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.ArrayAdapter;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Browser for the list of Apps, system or otherwise
 */
public class AppBrowserListFrag extends ListFragment {
    public static final String FRAG_TAG = "com.nickstephen.openandroid.components.AppBrowserListFrag";

    private List<String> mAppLabels;
    private Map<String, ApplicationInfo> mAppMap;
    private PackageManager mPackManager;
    private int mAppCount;
    private AppBrowserAdapter mAdapter;
    private DataLoader mDataWorker;
    private LinearLayout mLoadingLayout;
    private boolean mBrowseSystemApps;

    public AppBrowserListFrag() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = this.getArguments();
        if (args == null) {
            this.popFragment();
            StatMethods.hotBread(this.getActivity(), "Null arguments passed", Toast.LENGTH_SHORT);
            return;
        }
        mBrowseSystemApps = args.getBoolean(Constants.SYSTEM_LIST_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.isExiting()) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View listView = inflater.inflate(R.layout.app_listfrag);

        mLoadingLayout = (LinearLayout) listView.findViewById(R.id.loading_container);

        if (mAdapter == null) {
            mDataWorker = (DataLoader) new DataLoader(this.getActivity()).execute();
        } else {
            mLoadingLayout.setVisibility(View.GONE);
            this.setListAdapter(mAdapter);
        }

        return listView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mLoadingLayout = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mDataWorker != null && mDataWorker.getStatus() != AsyncTask.Status.FINISHED) {
            mDataWorker.cancel(true);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Bundle args = new Bundle();
        args.putString(Constants.PACKAGE_KEY, mAppMap.get(mAdapter.getItem(position)).packageName);
        args.putString(Constants.TEST_PACKAGE_KEY, mAppMap.get(mAdapter.getItem(position)).publicSourceDir);

        PackageBrowserFrag frag = new PackageBrowserFrag();
        frag.setArguments(args);

        this.getFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out)
                .replace(FragmentUtil.getContentViewCompat(), frag, PackageBrowserFrag.FRAG_TAG)
                .addToBackStack(PackageBrowserFrag.FRAG_TAG).commit();
    }

    private class AppBrowserAdapter extends ArrayAdapter<String> {
        private final LayoutInflater mInflater;

        public AppBrowserAdapter(Context context) {
            super(context, 0);

            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mAppCount;
        }

        @Override
        public String getItem(int position) {
            return mAppLabels.get(position);
        }

        @Override
        public View getView(int viewPosition, View convertView, ViewGroup parent) {
            ImageView img;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.app_list_layout);
                img = (ImageView) convertView.findViewById(R.id.logo_img);
            } else {
                img = (ImageView) convertView.findViewById(R.id.logo_img);
                img.setVisibility(View.INVISIBLE);
            }

            TextView txt = (TextView) convertView.findViewById(R.id.app_name_text);
            String label = getItem(viewPosition);
            txt.setText(label);

            new LazyLogoLoader(img, mAppMap.get(label), mPackManager).execute();

            return convertView;
        }
    }

    private class DataLoader extends AsyncTask<Void, Void, Integer> {
        private final Context mContext;

        public DataLoader(Context context) {
            mContext = context;

            mPackManager = context.getPackageManager();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if (mLoadingLayout != null && mLoadingLayout.getVisibility() == View.VISIBLE) {
                mLoadingLayout.setVisibility(View.GONE);
            }

            AppBrowserListFrag.this.setListAdapter(mAdapter = new AppBrowserAdapter(mContext));
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            List<ApplicationInfo> allApps = mPackManager.getInstalledApplications(PackageManager.GET_META_DATA);

            if (!mBrowseSystemApps) {
                List<ApplicationInfo> apps = new ArrayList<ApplicationInfo>();
                for (ApplicationInfo app : allApps) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                        apps.add(app);
                    }
                }
                allApps = apps;
            } else {
                List<ApplicationInfo> apps = new ArrayList<ApplicationInfo>();
                for (ApplicationInfo app : allApps) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
                        apps.add(app);
                    }
                }
                allApps = apps;
            }

            mAppCount = allApps.size();

            mAppLabels = new ArrayList<String>();
            for (ApplicationInfo app : allApps) {
                mAppLabels.add(mPackManager.getApplicationLabel(app).toString());
            }

            mAppMap = new HashMap<String, ApplicationInfo>();
            for (int i = 0; i < mAppCount; i++) {
                mAppMap.put(mAppLabels.get(i), allApps.get(i));
            }

            Collections.sort(mAppLabels, new Comparators.StringCompareCaseIgnore());
            return mAppCount;
        }
    }
}
