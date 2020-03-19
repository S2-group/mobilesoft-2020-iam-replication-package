package com.rajpriya.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;
import com.google.gson.Gson;
import com.rajpriya.home.utils.AppFilter;
import com.rajpriya.home.utils.ImageHelper;
import com.rajpriya.home.utils.PInfo;
import com.rajpriya.home.utils.Utils;
import com.rajpriya.home.utils.WebAppAdatper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InstalledAppsFragment extends Fragment implements Utils.AppUninstall {

    private static final String NUM_COLUMNS = "number_of_columns_in_gridView";

    private EditText mSearchBox;
    private LinearLayout mSearchPane;
    private TextView mButtonClose;
    private GridView mV;
    private LinearLayout mT;
    private Context mContext;
    private int mGridColumns;
    private AsyncTask mTask;


    private  static ArrayList<PInfo>  mApps;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //mApps = getPackages();
        mApps=new ArrayList<PInfo>();
        //Lower end devices dont support getNumColumns

        if(savedInstanceState != null) {
            mGridColumns = savedInstanceState.getInt(NUM_COLUMNS, 3);
        } else {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mGridColumns = sp.getInt(NUM_COLUMNS, 3);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();

        final View rootView = inflater.inflate(R.layout.fragment_installed_apps, container, false);
        final EditText box = ((EditText)rootView.findViewById(R.id.search_box));
        mV = ((GridView)rootView.findViewById(R.id.appgrid));
        mV.setAdapter(new AppAdapter(getActivity() , mApps, this));
        mV.setNumColumns(mGridColumns);

        mTask = new FetchAppListTask(getActivity(), mV, this).execute();


        mSearchBox = (EditText)rootView.findViewById(R.id.search_box);
        mSearchPane = (LinearLayout)rootView.findViewById(R.id.search_panel);
        mButtonClose  = (TextView)rootView.findViewById(R.id.btn_close);


        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getCurrentFocus().getWindowToken(), 0);
                mSearchBox.clearFocus();
                mSearchBox.setText("");
                mSearchPane.setVisibility(View.GONE);

            }
        });

        mSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                ((AppAdapter) mV.getAdapter()).getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        mSearchBox.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            in.hideSoftInputFromWindow(mSearchBox.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                            return true;
                        }
                        return false;
                    }
                }
        );

        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(((ActionBarActivity)activity).getSupportActionBar() != null) {
            ((ActionBarActivity)activity).getSupportActionBar().setTitle("Apps on Device");
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        // May return null if EasyTracker has not yet been initialized with a property ID.
        Tracker easyTracker = EasyTracker.getInstance(getActivity());
        if (easyTracker != null) {
            // This screen name value will remain set on the tracker and sent with
            // hits until it is set to a new value or to null.
            easyTracker.set(Fields.SCREEN_NAME, "InstalledAppsFragment");
            easyTracker.send(MapBuilder
                            .createAppView()
                            .build()
            );

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUM_COLUMNS, mGridColumns);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.installed_apps, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id) {
            case R.id.action_search:
                displaySearchBox();
                return true;
            case R.id.action_sort:
                 showSortDlg();
                return true;
            case R.id.action_grid_size:
                showGridSizeDlg();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showGridSizeDlg() {
        final CharSequence[] items = {" Increase "," Decrease "};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("App Grid Size");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch(item)
                {
                    case 0:
                        if(mGridColumns > 1) {
                            mV.setNumColumns(--mGridColumns);
                        }
                        break;
                    case 1:
                        mV.setNumColumns(++mGridColumns);
                        break;
                }
                dialog.dismiss();
            }
        });
        AlertDialog sortDialog = builder.create();
        sortDialog.show();

    }

    private void showSortDlg() {

        final CharSequence[] items = {" Alphabetically ",
                " Alphabetically Reverse",
                " App Size ",
                " App Size Reverse"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sort Apps By");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch(item)
                {
                    case 0:
                        Collections.sort(mApps, new Comparator<PInfo>() {
                            public int compare(PInfo result1, PInfo result2) {
                                return result1.appname.compareTo(result2.appname);
                            }
                        });
                        ((AppAdapter)mV.getAdapter()).notifyDataSetChanged();
                        break;
                    case 1:
                        Collections.sort(mApps, new Comparator<PInfo>() {
                            public int compare(PInfo result1, PInfo result2) {
                                return result2.appname.compareTo(result1.appname);
                            }
                        });
                        ((AppAdapter)mV.getAdapter()).notifyDataSetChanged();
                        break;
                    case 2:
                        Collections.sort(mApps, new Comparator<PInfo>() {
                            public int compare(PInfo result1, PInfo result2) {
                                return result1.size >  result2.size ? 1 : -1;
                            }
                        });
                        ((AppAdapter)mV.getAdapter()).notifyDataSetChanged();
                        break;
                    case 3:
                        Collections.sort(mApps, new Comparator<PInfo>() {
                            public int compare(PInfo result1, PInfo result2) {
                                return result1.size >  result2.size ? -1 : 1;
                            }
                        });
                        ((AppAdapter)mV.getAdapter()).notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
        AlertDialog sortDialog = builder.create();
        sortDialog.show();
    }

    private ArrayList<PInfo> getPackages() {
        ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
        final int max = apps.size();
        for (int i=0; i<max; i++) {
            apps.get(i).prettyPrint();
        }
        return apps;
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<PInfo>();
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }

            if(mContext.getPackageManager().getLaunchIntentForPackage(p.packageName) == null){
                //If you're here, then this is a not launch-able app
                continue;
            }


            PInfo newInfo = new PInfo();
            newInfo.appname = p.applicationInfo.loadLabel(mContext.getPackageManager()).toString();
            newInfo.pname = p.packageName;
            newInfo.versionName = p.versionName;
            newInfo.versionCode = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(mContext.getPackageManager());
            File file = new File(p.applicationInfo.sourceDir);
            double size = file.length();  // size in Byte
            newInfo.size = size;


            res.add(newInfo);
        }
        return res;
    }

    private void displaySearchBox() {
        if (mSearchPane.getVisibility() == View.VISIBLE) {
            return;
        }

        mSearchPane.setVisibility(View.VISIBLE);
        mSearchBox.requestFocus();
        InputMethodManager inputMethodManager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(mSearchBox.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

    }

    @Override
    public void onAppDeleted() {
        ((AppAdapter) mV.getAdapter()).someAppRemoved();
    }

    public class AppAdapter extends BaseAdapter implements Filterable{
        private Context context;
        public ArrayList<PInfo> mApps;
        private Filter appFilter;
        private InstalledAppsFragment parentFrag;


        public AppAdapter(Context context, ArrayList<PInfo> apps, InstalledAppsFragment f) {
            this.context = context;
            parentFrag = f;
            mApps = apps;
            appFilter = new AppFilter(mApps, this);

        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;

            //if (convertView == null) {

                gridView = new View(context);

                // get layout from mobile.xml
                gridView = inflater.inflate(R.layout.app, null);

                // set value into textview
                TextView textView = (TextView) gridView
                        .findViewById(R.id.name);
                textView.setText(mApps.get(position).appname);

                // set image based on selected text
                ImageView imageView = (ImageView) gridView
                        .findViewById(R.id.icon);
            imageView.setImageDrawable(mApps.get(position).icon);

            //} else {
              //  gridView = (View) convertView;
            //}

            gridView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.showActionDialog(context, mApps.get(position).pname, parentFrag);
                }
            });

            return gridView;
        }

        @Override
        public int getCount() {
            return mApps.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Filter getFilter() {
            if(appFilter == null)
            return  new AppFilter(mApps, this);
            else return appFilter;
        }

        public void someAppRemoved() {
            for (int i=0; i< mApps.size(); i++) {
                if (!Utils.isAppPresent(mApps.get(i).pname, getActivity())) {
                    mApps.remove(i);
                    ((AppFilter)appFilter).getApps().remove(i);
                    break;

                }
            }
            notifyDataSetChanged();
        }

        public void removeApp(String packageName) {
            for (int i=0; i< mApps.size(); i++) {
                if(packageName.equals(mApps.get(i).pname)) {
                    mApps.remove(i);
                    ((AppFilter)appFilter).getApps().remove(i);
                    break;
                }
            }
            notifyDataSetChanged();
        }
    }


    public class FetchAppListTask extends AsyncTask<String, Void, Boolean> {

        private ProgressDialog dialog;
        private Context mContext;
        private GridView mV;
        private InstalledAppsFragment iaf;

        public FetchAppListTask(Context context, GridView v, InstalledAppsFragment f) {
            mContext = context;
            dialog = new ProgressDialog(mContext);
            mV=v;
            iaf = f;
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            mApps = getPackages();
            return true;
        }
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Loading apps from device...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (dialog.isShowing())
                dialog.dismiss();

            mV.setAdapter(new AppAdapter(mContext , mApps, iaf));
            ((AppAdapter)mV.getAdapter()).notifyDataSetChanged();
        }

    }
    @Override
    public void onPause() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sp.edit().putInt(NUM_COLUMNS, mGridColumns).commit();
        super.onPause();
    }

}
